package com.lvqiang.springcloud.practice.server.repository;

import com.lvqiang.springcloud.practice.server.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
