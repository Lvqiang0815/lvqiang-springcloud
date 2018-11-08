package com.lvqiang.springcloud.practice.server.controller;

import com.lvqiang.springcloud.practice.client.ProductClient;
import com.lvqiang.springcloud.practice.common.DecreaseStockDTO;
import com.lvqiang.springcloud.practice.common.ProductInfoOutput;
import com.lvqiang.springcloud.practice.server.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

//
//import com.lvqiang.springcloud.practice.client.ProductClient;
//import com.lvqiang.springcloud.practice.common.ProductInfoOutput;
//import com.lvqiang.springcloud.practice.server.dto.CartDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Arrays;
//import java.util.List;
//
@RestController
@Slf4j
public class ClientController {
//
//    //@Autowired
//    //private LoadBalancerClient loadBalancerClient;
//
//    //@Autowired
//    //RestTemplate restTemplate;
//
    @Autowired
    ProductClient productClient;
//
//    @GetMapping("/getProductMsg")
//    public String getProductMsg() {
//
//        //1.第一种方式
//        //RestTemplate restTemplate = new RestTemplate();
//        //String response = restTemplate.getForObject("http://localhost:8010/productInfo", String.class);
//        //log.info("response={}", response);
//        //return response;
//
//        //2.第二种方式
//        //RestTemplate restTemplate = new RestTemplate();
//        //ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        //String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/productInfo";
//        //String response = restTemplate.getForObject(url, String.class);
//        //log.info("response={}",response);
//        //return response;
//
        //3.第三种方式
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://product/productInfo", String.class);
//        log.info("response={}", response);
//        return response;
//
//
        //4.第四种方式  use feign
//        String response = productClient.productInfomation();
//        log.info("response={}", response);
//        return response;
//    }
//
    @GetMapping("getProductList")
    public String getProductList() {
        List<ProductInfoOutput> list= productClient.listForOrder(Arrays.asList("157875196366160022"));
        log.info("response={}", list);
        return "ok";
    }

    @GetMapping("productDecreaseProductStock")
    public String decreaseProductStock() {
        productClient.decreaseProductStock(Arrays.asList(new DecreaseStockDTO("164103465734242707", 7)));
        return "ok";
    }
}
