package com.lvqiang.springcloud.practice.server.repository;

import com.lvqiang.springcloud.practice.server.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
