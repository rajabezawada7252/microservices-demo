package com.example.Payment_service.kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    @KafkaListener(topics = "order-topic", groupId = "payment-group")
    public void processOrder(String message) {

        System.out.println("Received Order Event: " + message);
        System.out.println("Processing payment...");

    }
}