package com.wxdc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *  Json 格式化工具
 * Created by  邱伟
 * 2018/4/9 20:21
 */

public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}