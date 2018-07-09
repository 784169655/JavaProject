package com.wxdc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by  邱伟
 * 2018/4/17 10:41
 */

@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {


    /**
     * 微信公众平台帐号授权url
     */
    public String wechatMpAuthorize;

    /**
     * 微信开放平台帐号授权url
     */
    public String wechatOpenAuthorize;

    /**
     * 点餐系统url
     */
    public String sell;

    /**
     * 调转 sell/wechat/userInfo
     */
    public String wechatUserInfo;
}