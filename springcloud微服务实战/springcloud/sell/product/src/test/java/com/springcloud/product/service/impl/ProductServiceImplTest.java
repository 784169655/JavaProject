package com.springcloud.product.service.impl;

import com.springcloud.product.ProductApplicationTests;
import com.springcloud.product.dataobject.ProductInfo;
import com.springcloud.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductServiceImplTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfos = productService.findUpAll();
        Assert.assertTrue(productInfos.size() > 0);
    }

    @Test
    public void findList() throws Exception {
        List<ProductInfo> productInfos = productService.findList(Arrays.asList("123","1234"));
        Assert.assertTrue(productInfos.size() > 0);
    }

}