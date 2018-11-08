package com.lvqiang.springcloud.practice.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.lvqiang.springcloud.practice.client"})
//@EnableCircuitBreaker
@SpringCloudApplication
//@ComponentScan(basePackages = {"com.lvqiang.springcloud.practice"})
public class OrderServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServerApplication.class, args);
	}
}
