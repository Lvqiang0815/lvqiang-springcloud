package com.lvqiang.springcloud.practice.server.controller;

import com.lvqiang.springcloud.practice.common.DecreaseStockDTO;
import com.lvqiang.springcloud.practice.common.ProductInfoOutput;
import com.lvqiang.springcloud.practice.server.dataobject.ProductCategory;
import com.lvqiang.springcloud.practice.server.dataobject.ProductInfo;
import com.lvqiang.springcloud.practice.server.service.CategoryService;
import com.lvqiang.springcloud.practice.server.service.ProductService;
import com.lvqiang.springcloud.practice.server.utils.ResutlVOUtils;
import com.lvqiang.springcloud.practice.server.vo.ProductInfoVO;
import com.lvqiang.springcloud.practice.server.vo.ProductVO;
import com.lvqiang.springcloud.practice.server.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    //@CrossOrigin()
    public ResultVO<ProductVO> list() {

//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        List<ProductInfo> productInfoList = productService.findUpAll();

        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVo = new ProductVO();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVo.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVo);
        }

        return ResutlVOUtils.success(productInfoList);
    }

    /**
     * 获取商品列表(给订单服务调用的)
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
        return productService.findList(productIdList);
    }

    @PostMapping("decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockDTO> cartDTOList) {
        productService.decreaseStock(cartDTOList);
    }
}
