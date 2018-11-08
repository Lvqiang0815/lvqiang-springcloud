package com.lvqiang.springcloud.practice.server.service;

import com.lvqiang.springcloud.practice.server.dto.OrderDTO;

public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);
}
