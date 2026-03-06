package com.example.Order_service;

import org.springframework.web.bind.annotation.*;

import com.example.Order_service.client.PaymentClient;
import com.example.Order_service.dto.PaymentRequest;
import com.example.Order_service.kafka.OrderProducer;

import org.springframework.beans.factory.annotation.Autowired;
@RestController
@RequestMapping("/orders")
public class OrderController {
	  @Autowired
	    private PaymentClient paymentClient;
	  
	  @Autowired
	    private OrderProducer producer;

	    @PostMapping("/create")
	    public String createOrder(@RequestBody PaymentRequest request) {
	       // return paymentClient.makePayment(request); feign client example
	        
	        producer.sendOrderEvent("New Order Created");

	        return "Order created successfully";
	    }


    @GetMapping
    public String getOrders() {
        return "Order Service Working";
    }
    
   
    
    }

