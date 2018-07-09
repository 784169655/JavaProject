package com.springcloud.product.repository;

import com.springcloud.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 通过商品上下架状态查询商品列表
     *
     * @param prodcutStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer prodcutStatus);

    /**
     * 通过 产品 id 查询
     * @param productIdList
     * @return
     */
    List<ProductInfo> findByProductIdIn(List<String> productIdList);

}
