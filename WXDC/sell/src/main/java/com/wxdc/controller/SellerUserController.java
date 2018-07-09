package com.wxdc.controller;

import com.wxdc.config.ProjectUrlConfig;
import com.wxdc.constant.CookieConstant;
import com.wxdc.constant.RedisConstant;
import com.wxdc.entity.SellerInfo;
import com.wxdc.enums.ResultEnum;
import com.wxdc.service.SellerService;
import com.wxdc.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户
 * Created by  邱伟
 * 2018/4/17 10:58
 */
@Controller
@RequestMapping("/sell/seller")
@Slf4j
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {

        //1. openid 去和数据库里的数据做匹配 不匹配返回失败
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error");
        }

        //2. 设置token 至 redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        //3. 设置token 至 cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,CookieConstant.EXPIRE);

//        return new ModelAndView("redirect:                            /seller/order/list");   这个是相对地址，最好用绝对地址
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/seller/order/list");
    }


    //注意：登录时用域名登录，如果用127.0.0.1或者localhost访问则会跳转到域名所在地址  出现跨域问题，即cookie失效
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. 清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //3. 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}