package com.example.space.ingestion.service;

import com.example.space.ingestion.model.MinuteStats;
import com.example.space.ingestion.repository.MinuteStatsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Component
public class AggregationScheduler {

    private final IngestionService ingestionService;
    private final MinuteStatsRepository repository;

    public AggregationScheduler(IngestionService ingestionService,
                                MinuteStatsRepository repository) {
        this.ingestionService = ingestionService;
        this.repository = repository;
    }
    @Transactional
    @Scheduled(cron = "0 * * * * *")
    public void aggregate() {

        Instant previousMinute =
                Instant.now().minusSeconds(60).truncatedTo(ChronoUnit.MINUTES);

        Set<String> ids =
                ingestionService.getMinuteBuckets().remove(previousMinute);

        if (ids != null) {
            int count = ids.size();

            System.out.println(previousMinute + " -> " + count + " unique ids"   +"local time :::"  +previousMinute.atZone(ZoneId.of("Asia/Kolkata")));

            repository.saveAndFlush(new MinuteStats(previousMinute, count));
            //  System.out.println();
            
        }
    }
}
