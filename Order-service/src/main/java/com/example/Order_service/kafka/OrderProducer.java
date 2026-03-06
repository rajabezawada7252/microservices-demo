package com.example.Order_service.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final String TOPIC = "order-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void sendOrderEvent(String message) {

        kafkaTemplate.send(TOPIC, message);

        System.out.println("Order event sent to Kafka: " + message);
    }
}