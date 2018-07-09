package com.security.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * 过滤器
 * Created by  邱伟
 * 2018/5/15 20:03
 */

//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("time filter start");

        long start = new Date().getTime();
        //请求下一个过滤器  后续的处理都是在这一句完成的
        chain.doFilter(request,response);

        System.out.println("time filter finish  耗时:" + (new Date().getTime() - start));
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}