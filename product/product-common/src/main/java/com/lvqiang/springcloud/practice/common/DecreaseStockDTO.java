package com.lvqiang.springcloud.practice.common;

import lombok.Data;

@Data
public class DecreaseStockDTO {

    private String productId;
    private Integer productQuantity;

    public DecreaseStockDTO(String productId, Integer productQuqntity) {
        this.productId = productId;
        this.productQuantity = productQuqntity;
    }
}
