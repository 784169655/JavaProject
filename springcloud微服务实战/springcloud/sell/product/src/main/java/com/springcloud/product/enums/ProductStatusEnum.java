package com.springcloud.product.enums;

import lombok.Getter;

/**
 * 商品状态
 * Created by  邱伟
 * 2018/4/3 19:06
 */
@Getter
public enum ProductStatusEnum implements CodeEnum<Integer>{

    UP(0,"上架"),
    DOWN(1,"下架"),
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}