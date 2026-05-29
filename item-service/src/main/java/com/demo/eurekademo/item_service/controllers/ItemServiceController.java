package com.demo.eurekademo.item_service.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/item")
public class ItemServiceController {
    @Value("${server.port}")
    private String port;

    // @GetMapping("/demo/{name}")
    // public String showItemServiceMessage(@PathVariable String name) {
    // return "Item service : " + name + ", this message is from Item Service
    // running on port: " + port;
    // }

    @GetMapping("/demo")
    public String showItemServiceMessage() {
        return "This message is from Item Service running on port: " + port;
    }

    @GetMapping("/demo/msg")
    public String showItemServiceMsg() {
        return "This message is from Item Service /demo/msg running on port: " + port;
    }

    @GetMapping("/ping")
    public String pingItem() {
        return "Item service is running on port : " + port;
    }
}
