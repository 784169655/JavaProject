package com.springcloud.common.utils;

import lombok.Data;

/** http 请求返回的最外层对象
 * Created by  邱伟
 * 2018/4/3 19:42
 */

@Data
public class ResultVo<T>{

//    private static final long serialVersionUID = 4074247747825523309L;

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 返回具体内容 */
    private T data;
}