package com.springcloud.server.service.impl;

import com.springcloud.server.dataobject.ProductCategory;
import com.springcloud.server.repository.ProductCategoryRepository;
import com.springcloud.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by  邱伟
 * 2018/7/2 11:26
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}