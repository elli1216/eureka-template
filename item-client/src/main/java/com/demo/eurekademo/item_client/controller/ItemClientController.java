package com.demo.eurekademo.item_client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.demo.eurekademo.item_client.service.ItemClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ItemClientController {
    @Autowired
    private ItemClientService itemClientService;

    @GetMapping("/demo/itemMsg")
    public String getItemMsg(@RequestParam(defaultValue = "User") String name) {
        return itemClientService.showItemClientMessage(name);
    }
}
