package com.demo.eurekademo.consumer_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producer-service")
public interface ProducerServiceClient {
    @GetMapping("/producer/demo/{name}")
    String getMsgFromProducer(@PathVariable("name") String name);
}
