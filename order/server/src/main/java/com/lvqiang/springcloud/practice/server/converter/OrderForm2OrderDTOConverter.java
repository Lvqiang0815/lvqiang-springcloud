package com.lvqiang.springcloud.practice.server.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lvqiang.springcloud.practice.server.dataobject.OrderDetail;
import com.lvqiang.springcloud.practice.server.dto.OrderDTO;
import com.lvqiang.springcloud.practice.server.enums.ResultEnum;
import com.lvqiang.springcloud.practice.server.exceptions.OrderException;
import com.lvqiang.springcloud.practice.server.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO converter(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        Gson gson = new Gson();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("【json转换】错误, string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
