package com.demo.eurekademo.item_client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemClientService {
    @Autowired
    private RestTemplate loadBalancedRestTemplate;

    public String showItemClientMessage(String name) {
        String url = "http://item-service/item/demo";
        String response = loadBalancedRestTemplate.getForObject(url, String.class);
        return response;
    }
}
