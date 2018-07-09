package com.browser.service;

import lombok.extern.java.Log;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by  邱伟
 * 2018/5/18 19:08
 */

@Component
@Log
public class MyUserDetailsService implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("登录用户名:" + username);

        //根据用户查找用户信息    授权
//        return new user(username,"123456",
//                //  认证
//                authorityutils.commaseparatedstringtoauthoritylist("admin"));


        //根据查找到的用户信息判断用户是否被冻结
        return new User("qiuwei","123456",
                true,true,true,true,
                //  认证
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }
}