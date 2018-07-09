package com.springcloud.product.service;

import com.springcloud.product.dataobject.ProductInfo;
import com.springcloud.product.dto.CartDTO;

import java.util.List;

/**
 * Created by  邱伟
 * 2018/7/2 10:04
 */

public interface ProductService {

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询 商品列表
     *
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 扣库存
     */
    void decreaseStock(List<CartDTO> cartDTOList);
}