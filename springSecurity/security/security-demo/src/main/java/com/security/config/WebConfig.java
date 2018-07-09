package com.security.config;

import com.security.filter.TimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 *  第三方过滤器  配置
 * Created by  邱伟
 * 2018/5/15 20:19
 */

@Configuration
public class WebConfig {

    @Bean   //注册 filter 的 bean
    public FilterRegistrationBean timeFilter() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        //配自己写的过滤器      此时第三方的 timeFilter 的 @Component 已经注掉了
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        //这里就可以设置黑、白名单进行拦截
        List<String> urls = new ArrayList<>();

        //这里设置所有都起做用
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);

        return registrationBean;
    }
}