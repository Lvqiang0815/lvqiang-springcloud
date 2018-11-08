package com.lvqiang.springcloud.practice.server.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    //@HystrixCommand(fallbackMethod = "fallback")
    // Timeout config
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    // circuitBreaker config
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
//    })
    @HystrixCommand
    @GetMapping("/getProductInfoList")
    public String getProductInfoList() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8010/product/listForOrder",
                Arrays.asList("157875196366160022"), String.class);
    }

    private String fallback() {
        return "oh!, try again later please~~~~";
    }

    private String defaultFallback() {
        return "~~~~oh!, try again later please~~~~";
    }
}
