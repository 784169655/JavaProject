package com.wxdc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 微信帐号相关配置
 * Created by  邱伟
 * 2018/4/6 22:27
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     *  appId
     */
    private String mpAppId;

    /**
     *  appSecret
     */
    private String mpAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 异步通知路径
     */
    private String notifyUrl;

    /**
     * 开放平台openid
     */
    private String openAppId;

    /**
     * 开放平台密钥
     */
    private String openAppSecret;

    /**
     * 微信模版id
     */
    private Map<String, String> templateId;
}