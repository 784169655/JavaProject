package com.springcloud.common;

import lombok.Data;

/**
 * Created by  邱伟
 * 2018/7/4 19:59
 */

@Data
public class DecreaseStockInput {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}