package com.demo.eurekademo.order_service.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderServiceController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/demo/${name}")
    public String showOrderServiceMessage(@PathVariable String name) {
        return "Item service : " + name + ", this message is from Order Service running on port: " + port;
    }

    @GetMapping("/ping")
    public String pingItem() {
        return "Order service is running on port : " + port;
    }
}
