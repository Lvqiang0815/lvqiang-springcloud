package com.lvqiang.springcloud.practice.server.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MsgReceiver {

    //@RabbitListener(queues = "myQueue") 1.手动创建queue
    //@RabbitListener(queuesToDeclare = @Queue("myQueue")) // 2. auto create Queue
    //3. auto create Queue and Queue binding
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void msgReceive(String message) {
        log.info("received message: {}", message);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("computerQueue"),
            key = "computer",
            exchange = @Exchange("computerOrder")
    ))
    public void computerReceive(String message) {
        log.info("received computer message: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fruitQueue"),
            key = "fruit",
            exchange = @Exchange("fruitOrder")
    ))
    public void fruitReceive(String message) {
        log.info("received fruit message: {}", message);
    }

}
