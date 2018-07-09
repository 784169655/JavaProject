package com.wxdc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by  邱伟
 * 2018/4/20 19:57
 */

@ConfigurationProperties(prefix = "wechatMpTest")
@Component
@Data
public class WechatMpAccoutTestConfig {

    /**
     * 公众平台测试号appId
     */

    private String testMpAppId;
    /**
     * 公众平台测试号appSecret
     */

    private String testMpAppSecret;
    /**
     * 公众平台测试号TemplateId
     */

    private Map<String, String> testTemplateId;

}