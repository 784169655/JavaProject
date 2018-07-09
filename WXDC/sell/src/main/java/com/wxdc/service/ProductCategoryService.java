package com.wxdc.service;

import com.wxdc.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    /**
     * 根据 categoryId 查询类目
     * @param categoryId
     * @return
     */
    ProductCategory findOne(Integer categoryId);

    /**
     * 查询 所有的类目
     * @return
     */
    List<ProductCategory> findAll();

    /**
     *  根据  类目编号集合  查询类目列表
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     *  新增类目
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
