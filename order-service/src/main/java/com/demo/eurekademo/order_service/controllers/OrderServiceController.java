package com.demo.eurekademo.order_service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderServiceController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceController.class);

    @GetMapping("/demo/{name}")
    public String showOrderServiceMessage(@PathVariable String name) {
        return "Item service : " + name + ", this message is from Order Service running on port: " + port;
    }

    @GetMapping("/demo/orderMsg")
    public String showOrderMessageString() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("team");
        String url = "http://localhost:8083/item/demo/msg";

        return circuitBreaker.run(() -> {
            logger.info("Attempting to call item-service.");
            return restTemplate.getForObject(url, String.class);
        }, throwable -> {
            logger.warn("Fallback triggered for item-service. Error {}", throwable.getMessage());
            return "Fallback triggered for item-service.";
        });
    }

    @GetMapping("/ping")
    public String pingItem() {
        return "Order service is running on port : " + port;
    }
}
