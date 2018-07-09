package com.springcloud.common.utils;

import java.util.Random;

/**
 * 生成随机数
 * Created by  邱伟
 * 2018/4/4 11:11
 */

public class KeyUtil {

    /**
     * 生成唯一的主键
     *      格式：时间+随机数    synchronized 防止多线程并发
     * @return
     */
    public static synchronized String getUniqueKey() {

        Random random = new Random();
        //生成随机的 2 位数
//        Integer number = random.nextInt(90) + 10;
        //生成随机的 6 位数
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }

}