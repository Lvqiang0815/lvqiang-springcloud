package com.lvqiang.springcloud.practice.server;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MqSenderTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMsgToMq() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        amqpTemplate.convertAndSend("myQueue", "now is " + format.format(new Date()));
    }

    @Test(expected = Exception.class)
    public void sendMsgToMq1() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        amqpTemplate.convertAndSend("computerOrder", "computer", "now is " + format.format(new Date()) + "computer");
    }
}
