package com.lvqiang.springcloud.practice.server.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lvqiang.springcloud.practice.common.ProductInfoOutput;
import com.lvqiang.springcloud.practice.server.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductInfoReceiver {

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void processProductInfo(String message) {
        //message => ProductInfoOutput
        //List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>) JsonUtil.fromJson(message, new TypeReference<List<ProductInfoOutput>>() {});

        log.info("从队列【{}】接收到消息：{}", "productInfo", message);
    }
}
