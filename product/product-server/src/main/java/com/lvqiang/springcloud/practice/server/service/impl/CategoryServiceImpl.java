package com.lvqiang.springcloud.practice.server.service.impl;

import com.lvqiang.springcloud.practice.server.dataobject.ProductCategory;
import com.lvqiang.springcloud.practice.server.repository.ProductCategoryRepository;
import com.lvqiang.springcloud.practice.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
