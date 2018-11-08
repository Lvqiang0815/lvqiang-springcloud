package com.lvqiang.springcloud.practice.server.controller;

import com.lvqiang.springcloud.practice.server.dto.OrderDTO;
import com.lvqiang.springcloud.practice.server.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

//    @GetMapping("/sendMessage")
//    public void sendMessage() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String message = "now is " + format.format(new Date());
//        streamClient.output().send(MessageBuilder.withPayload(message).build());
//    }

    @GetMapping("/sendMessage")
    public String sendMessage() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("00000000000001");
        orderDTO.setOrderAmount(new BigDecimal(3));
        orderDTO.setBuyerName("xxxxxxxxx");

        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());

        return "ok";
    }
}
