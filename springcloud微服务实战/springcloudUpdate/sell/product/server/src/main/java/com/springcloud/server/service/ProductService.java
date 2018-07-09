package com.springcloud.server.service;


import com.springcloud.common.DecreaseStockInput;
import com.springcloud.common.ProductInfoOutput;
import com.springcloud.server.dataobject.ProductInfo;

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
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     */
    void decreaseStock(List<DecreaseStockInput> cartDTOList);
}