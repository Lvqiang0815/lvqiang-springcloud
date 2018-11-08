package com.lvqiang.springcloud.practice.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @GetMapping("productInfo")
    public String productInfo() {
        return "this is product info!!!!!!!!!!!!";
    }
}
