package com.demo.eurekademo.consumer_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.demo.eurekademo.consumer_service.client.ProducerServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ConsumerController {

    private final ProducerServiceClient producerServiceClient;

    @Autowired
    public ConsumerController(ProducerServiceClient producerServiceClient) {
        this.producerServiceClient = producerServiceClient;
    }

    @GetMapping("/consumer/demo")
    public String sendMsg(@RequestParam String name) {
        return "Consumer: " + producerServiceClient.getMsgFromProducer(name);
    }
}
