package com.lvqiang.springcloud.practice.server.message;

import com.lvqiang.springcloud.practice.server.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@EnableBinding(StreamClient.class)
@Component
public class StreamMsgReceiver {

//    @StreamListener(StreamClient.OUTPUT)
//    //@SendTo(StreamClient.OUTPUT)
//    public void receiveMsg(Object message) {
//        log.info("Received stream message: {}", message);
//    }

    @StreamListener(StreamClient.OUTPUT)
    public void receiveObject(OrderDTO orderDTO) {
        log.info("orderDTO {}", orderDTO);
    }
}
