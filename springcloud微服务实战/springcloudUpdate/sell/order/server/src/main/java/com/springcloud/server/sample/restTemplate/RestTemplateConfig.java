package com.springcloud.server.sample.restTemplate;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by  邱伟
 * 2018/7/2 17:58
 */

@Component
public class RestTemplateConfig {

    @Bean
    @LoadBalanced  //注意这个注解
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}