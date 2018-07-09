package com.wxdc.controller;

import com.wxdc.config.ProjectUrlConfig;
import com.wxdc.enums.ResultEnum;
import com.wxdc.exception.SellException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * Created by  邱伟
 * 2018/4/6 22:14
 */

@Controller
@RequestMapping("/sell/wechat")
@Slf4j
@Api("微信")
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam String returnUrl) {

        //2. 调用方法
//        String url = "http://xiaowei.nat300.top/sell/wechat/userInfo";
//                                                                                                                state，此处填链接，因为链接所以encode一下
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(projectUrlConfig.getWechatUserInfo(), WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl));

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam String code,
                         @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }

        String openid = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + openid;
    }

    @GetMapping("/qrAuthorize")
    public String qrAuthorizeTest(@RequestParam String returnUrl) {

//        returnUrl = "http://xiaowei.nat300.top/sell/seller/order/list";
        String url = projectUrlConfig.getWechatOpenAuthorize();
        String redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    /////       公众号服务号使用的       //////
/*
    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam String returnUrl) {

        //2. 调用方法
        String url = "http://xiaowei.nat300.top/sell/wechat/qrUserInfo";
        String redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN, URLEncoder.encode(returnUrl));

        return "redirect:" + redirectUrl;
    }


        @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam String code,
                             @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }

        String openid = wxMpOAuth2AccessToken.getOpenId();

//        String returnUrl = "http:www.imooc.com";
        return "redirect:" + returnUrl + "?openid=" + openid;
    }
*/
    /////       公众号服务号使用的       //////
}