package com.lvqiang.springcloud.practice.server.controller;

import com.lvqiang.springcloud.practice.server.config.PretyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("girl")
public class GirlController {

    @Autowired
    private PretyConfig pretyConfig;

    @GetMapping("/prety")
    public String showPrety() {
        return "name: " + pretyConfig.getName() + ",age: " + pretyConfig.getAge();
    }
}
