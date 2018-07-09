package com.springcloud.server.exception;

import com.springcloud.server.enums.ResultEnum;

/**
 * Created by  邱伟
 * 2018/7/3 13:21
 */

public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}