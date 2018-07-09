package com.wxdc.utils;

import com.wxdc.enums.CodeEnum;

/**
 * 枚举工具类
 * Created by  邱伟
 * 2018/4/12 14:46
 */

public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}