package com.lvqiang.springcloud.practice.server.service.impl;


import com.lvqiang.springcloud.practice.common.DecreaseStockDTO;
import com.lvqiang.springcloud.practice.common.ProductInfoOutput;
import com.lvqiang.springcloud.practice.server.dataobject.ProductInfo;
import com.lvqiang.springcloud.practice.server.enums.ProductStatusEnum;
import com.lvqiang.springcloud.practice.server.enums.ResultEnum;
import com.lvqiang.springcloud.practice.server.exception.ProductException;
import com.lvqiang.springcloud.practice.server.repository.ProductInfoRepository;
import com.lvqiang.springcloud.practice.server.service.ProductService;
import com.lvqiang.springcloud.practice.server.utils.JsonUtil;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList).stream()
        .map(e -> {
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e, output);
            return output;
        })
                .collect(Collectors.toList());
    }

    @Override
    public void decreaseStock(List<DecreaseStockDTO> cartDTOList) {

        List<ProductInfo> productInfoList = decreaseStockProcess(cartDTOList);

        productInfoList.stream().map(
            e -> {
                ProductInfoOutput output = new ProductInfoOutput();
                BeanUtils.copyProperties(e, output);
                return output;
            }
        ).collect(Collectors.toList());

        // Send MQ message
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoList));
    }

    @Transactional
    protected List<ProductInfo> decreaseStockProcess(List<DecreaseStockDTO> cartDTOList) {
        List<ProductInfo> productInfos = new ArrayList<>();

        for (DecreaseStockDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> optionalProductInfo = productInfoRepository.findById(cartDTO.getProductId());
            if (!optionalProductInfo.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = optionalProductInfo.get();
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfos.add(productInfo);

        }
        return productInfos;
    }
}
