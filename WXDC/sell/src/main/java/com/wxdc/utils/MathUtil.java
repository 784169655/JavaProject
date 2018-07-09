package com.wxdc.utils;

/**
 * Created by  邱伟
 * 2018/4/11 19:50
 */

public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    /**
     *  比较2个 double 类型 金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2) {
        double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }
}