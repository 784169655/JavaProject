package com.wxdc.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wxdc.config.ProjectUrlConfig;
import com.wxdc.config.WechatAccountConfig;
import com.wxdc.config.WechatMpAccoutTestConfig;
import com.wxdc.entity.OrderDetail;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  邱伟
 * 2018/4/6 18:17
 *
 * 1、引导用户进入授权页面同意授权，获取code
 *
 * 2、通过code换取网页授权access_token（与基础支持中的access_token不同）
 *
 * 3、如果需要，开发者可以刷新网页授权access_token，避免过期
 *
 * 4、通过网页授权access_token和openid获取用户基本信息（支持UnionID机制）
 */

@Controller
@RequestMapping("/sell /weixin")
@Slf4j
public class WeixinController {

    @Autowired
    private WechatMpAccoutTestConfig accountConfig;

    @Autowired
    private ProjectUrlConfig urlConfig;

    @GetMapping("/getCode")
    public String getCode() {
        String redirectAuthUrl = urlConfig.getSell() + "/weixin/auth";
        String redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + accountConfig.getTestMpAppId() + "&" +
                "redirect_uri=" + redirectAuthUrl + "&" +
                "response_type=code&" +
                "scope=snsapi_base&" +
                "state=STATE#wechat_redirect";
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/auth")
    public void auth(@RequestParam String code) {
        log.info("进入微信方法");
        log.info("code = {}",code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + accountConfig.getTestMpAppId() + "&secret=" + accountConfig.getTestMpAppSecret() + "&code=" + code + "&grant_type=authorization_code";

        String s = new RestTemplate().getForObject(url, String.class);

        System.out.println(s);
        log.info("response={}",s);
    }

    @GetMapping("/authTest")
    public String authTest(@RequestParam String code,
                         @RequestParam("state") String returnUrl) {
        log.info("进入微信方法");
        log.info("code = {}",code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + accountConfig.getTestMpAppId() + "&secret=" + accountConfig.getTestMpAppSecret() + "&code=" + code + "&grant_type=authorization_code";


        Gson gson = new Gson();
        Map map = gson.fromJson(new RestTemplate().getForObject(url, String.class),
                new TypeToken<HashMap<String,String>>() {
                }.getType());

//        System.out.println(map.get("openid"));
        return "redirect:" + returnUrl + "?openId=" + map.get("openid");
    }
}