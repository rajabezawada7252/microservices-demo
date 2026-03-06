package com.example.Order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Order_service.dto.PaymentRequest;

@FeignClient(name = "payment-service", url = "http://localhost:8082")
public interface PaymentClient {

    @PostMapping("/payment/pay")
   public String makePayment(@RequestBody PaymentRequest request);

}