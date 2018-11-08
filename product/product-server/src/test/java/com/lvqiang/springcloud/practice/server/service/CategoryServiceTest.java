package com.lvqiang.springcloud.practice.server.service;

import com.lvqiang.springcloud.practice.server.ProductServerApplicationTests;
import com.lvqiang.springcloud.practice.server.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class CategoryServiceTest extends ProductServerApplicationTests{

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = categoryService.findByCategoryTypeIn(Arrays.asList(11,22));
        Assert.assertTrue(list.size() > 0);
    }
}