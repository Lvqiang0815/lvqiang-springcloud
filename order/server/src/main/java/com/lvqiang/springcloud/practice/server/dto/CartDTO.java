package com.lvqiang.springcloud.practice.server.dto;

import lombok.Data;

@Data
public class CartDTO {

    private String productId;
    private Integer productQuqntity;

    public CartDTO(String productId, Integer productQuqntity) {
        this.productId = productId;
        this.productQuqntity = productQuqntity;
    }
}
