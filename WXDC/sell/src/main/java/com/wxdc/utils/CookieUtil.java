package com.wxdc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie 工具类
 * Created by  邱伟
 * 2018/4/18 19:35
 */

public class CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param key
     * @param value
     * @param maxAge  过期时间
     */
    public static void set(HttpServletResponse response,
                           String key,
                           String value,
                           int maxAge) {

        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        //默认单位是秒
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);

    }

    /**
     * 获取 cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie get(HttpServletRequest request,
                           String keyName) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(keyName)) {
            return cookieMap.get(keyName);
        } else {
            return null;
        }
    }

    /**
     * 将cookie 封装成map
     * @param request
     * @return
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}