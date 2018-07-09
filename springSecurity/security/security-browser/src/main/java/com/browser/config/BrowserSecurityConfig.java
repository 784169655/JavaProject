package com.browser.config;

import com.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by  邱伟
 * 2018/5/18 17:17
 */

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SecurityConfig securityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        super.configure(http);

        /** 表单登录 **/
        //登录方式
        http.formLogin()
//        http.httpBasic()
                .loginPage("/login.html")
                .and()

                //授权
                .authorizeRequests()
                //任何请求
                .anyRequest()
                //都需要身份认证
                .authenticated()
                .antMatchers("/login.html", securityConfig.getBrowserConfig().getLoginPage()).permitAll()
                .and().csrf().disable();

    }
}