package com.springcloud.common.exceptions;

import com.springcloud.common.utils.ResultEnum;
import lombok.Getter;

/**
 * Created by  邱伟
 * 2018/4/4 10:52
 */

@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}