package com.wxdc.constant;

/**
 * redis常量
 * Created by  邱伟
 * 2018/4/18 19:22
 */

public interface RedisConstant {

    /** token 前缀 */
    String TOKEN_PREFIX = "token_%s";

    /** 过期时间 2小时 2*3600秒  */
    Integer EXPIRE = 7200;
}