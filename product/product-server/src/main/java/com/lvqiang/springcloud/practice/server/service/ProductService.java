package com.lvqiang.springcloud.practice.server.service;

import com.lvqiang.springcloud.practice.common.DecreaseStockDTO;
import com.lvqiang.springcloud.practice.common.ProductInfoOutput;
import com.lvqiang.springcloud.practice.server.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {
    List<ProductInfo> findUpAll();

    List<ProductInfoOutput> findList(List<String> productIdList);

    void decreaseStock(List<DecreaseStockDTO> cartDTOList);
}
