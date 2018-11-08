package com.lvqiang.springcloud.practice.client;

import com.lvqiang.springcloud.practice.common.DecreaseStockDTO;
import com.lvqiang.springcloud.practice.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product", fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/productInfo")
    String productInfomation();

    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseProductStock(@RequestBody List<DecreaseStockDTO> decreaseStockDTOList);

    @Component
    static class ProductClientFallback implements ProductClient {

        @Override
        public String productInfomation() {
            System.out.println("productInfo....productInfo....");
            return "productInfo........";
        }

        @Override
        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
            return null;
        }

        @Override
        public void decreaseProductStock(List<DecreaseStockDTO> decreaseStockDTOList) {

        }

    }
}
