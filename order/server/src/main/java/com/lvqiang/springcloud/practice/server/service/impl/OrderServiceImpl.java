package com.lvqiang.springcloud.practice.server.service.impl;

import com.lvqiang.springcloud.practice.client.ProductClient;
import com.lvqiang.springcloud.practice.common.DecreaseStockDTO;
import com.lvqiang.springcloud.practice.common.ProductInfoOutput;
import com.lvqiang.springcloud.practice.server.dataobject.OrderDetail;
import com.lvqiang.springcloud.practice.server.dataobject.OrderMaster;
import com.lvqiang.springcloud.practice.server.dto.OrderDTO;
import com.lvqiang.springcloud.practice.server.enums.OrderStatusEnum;
import com.lvqiang.springcloud.practice.server.enums.PayStatusEnum;
import com.lvqiang.springcloud.practice.server.repository.OrderDetailRepository;
import com.lvqiang.springcloud.practice.server.repository.OrderMasterRepository;
import com.lvqiang.springcloud.practice.server.service.OrderService;
import com.lvqiang.springcloud.practice.server.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        //1.查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail:: getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        //2.计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        //3.扣库存(调用商品服务)
        List<DecreaseStockDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseProductStock(cartDTOList);

        //4.订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
