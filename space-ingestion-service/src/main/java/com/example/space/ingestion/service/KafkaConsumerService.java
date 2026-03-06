package com.example.space.ingestion.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "space-requests", groupId = "space-group")
    public void consume(String id) {
        // Dedup logic
        // Redis logic
        // DB persistence
        // External API call
    	
    	
    	  
    }
}