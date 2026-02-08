package com.example.space.ingestion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class IngestionService {

    private static final Logger log =
            LoggerFactory.getLogger(IngestionService.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    // minuteStart -> unique IDs
    private final ConcurrentHashMap<Instant, Set<String>> minuteBuckets =
            new ConcurrentHashMap<>();

    private final WebClient webClient;

    public IngestionService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Processes a single request.
     * Throws exception if HTTP call fails (so controller returns "failed").
     */
    public void processRequest(String id, String endpoint) {

        // 1. Determine calendar minute
        Instant minute = Instant.now().truncatedTo(ChronoUnit.MINUTES);
        
		
		  boolean isNew = recordIdWithRedis(id, minute);
		 
        if (isNew) {
            // optionally increment local counters
        

        // 2. Record ID (deduplicated per minute) 
        minuteBuckets
                .computeIfAbsent(minute, k -> ConcurrentHashMap.newKeySet())
                .add(id);
        }
        // 3. Optional external HTTP call
        if (endpoint != null && !endpoint.isEmpty()) {

            try {
                webClient.get()
                        .uri(endpoint)
                        .retrieve()
                        .toBodilessEntity()
                        .doOnSuccess(response ->
                                log.info("Called endpoint={} status={}",
                                        endpoint,
                                        response.getStatusCode()))
                        .doOnError(error ->
                                log.error("Error calling endpoint={}", endpoint, error))
                        .block(); // bounded by timeout

            } catch (WebClientResponseException ex) {
                // HTTP error status (4xx/5xx)
                log.error("HTTP error calling endpoint={} status={}",
                        endpoint, ex.getStatusCode());
                throw ex;

            } catch (Exception ex) {
                // Timeout / connection / unexpected error
                log.error("Unexpected error calling endpoint={}", endpoint, ex);
                throw ex;
            }
        }
    }
 
    public boolean recordIdWithRedis(String id, Instant minuteStart) {

        String redisKey = "seen:" + minuteStart.toString();

        Long added = redisTemplate.opsForSet().add(redisKey, id);

        // Set TTL only once
        redisTemplate.expire(redisKey, Duration.ofMinutes(2));

        // added == 1 → new ID
        // added == 0 → duplicate
        return added != null && added == 1;
    }


    public ConcurrentHashMap<Instant, Set<String>> getMinuteBuckets() {
        return minuteBuckets;
    }
}
