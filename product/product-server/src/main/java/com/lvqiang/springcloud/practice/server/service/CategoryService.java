package com.lvqiang.springcloud.practice.server.service;

import com.lvqiang.springcloud.practice.server.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
