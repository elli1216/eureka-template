package com.demo.eurekademo.producer_service.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/demo/{name}")
    public String shsowProducerServiceMessage(@PathVariable String name) {
        return "Producer service: " + name + ", this message is from Producer Service running on port: " + port;
    }

}
