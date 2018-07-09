package com.springcloud.product.dto;

import lombok.Data;

/**
 * 购物车
 * Created by  邱伟
 * 2018/4/4 11:27
 */

@Data
public class CartDTO {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}