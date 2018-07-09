package com.security.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * Created by  邱伟
 * 2018/5/15 20:34
 */

@Component  //这里光声明并不能让拦截器起做用   还需要配置
public class TimeInterceptor implements HandlerInterceptor {

    @Override       //接口也就是Controller调用前调用该方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        System.out.println("preHandle");

        //类名
        System.out.println(((HandlerMethod) o).getBean().getClass().getName());
        //方法名
        System.out.println(((HandlerMethod) o).getMethod().getName());

        request.setAttribute("startTime",System.currentTimeMillis());

        //返回false 表示后面的方法不执行
        return true;
    }

    @Override       //接口也就是Controller调用后调用该方法 如果接口抛出异常，则该方法不被调用
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

        System.out.println("postHandle");
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println("time interceptor 耗时:" + (System.currentTimeMillis() - startTime));

    }

    @Override       //接口也就是Controller调用后调用该方法 不管接口是否抛出异常，该方法都被调用
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        System.out.println("ex is" + e);
    }
}