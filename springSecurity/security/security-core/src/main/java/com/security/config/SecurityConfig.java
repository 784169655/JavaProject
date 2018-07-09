package com.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by  邱伟
 * 2018/5/19 10:38
 */

@ConfigurationProperties(prefix = "imooc.security")
public class SecurityConfig {

   private BrowserConfig browserConfig = new BrowserConfig();

    public BrowserConfig getBrowserConfig() {
        return browserConfig;
    }

    public void setBrowserConfig(BrowserConfig browserConfig) {
        this.browserConfig = browserConfig;
    }
}