package com.lvqiang.springcloud.practice.common;

import lombok.Data;


import java.math.BigDecimal;

//@Table(name="T_proxxxx") // when the class name does not same table name
@Data
public class ProductInfoOutput {

    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer productStatus;
    private Integer categoryType;

}
