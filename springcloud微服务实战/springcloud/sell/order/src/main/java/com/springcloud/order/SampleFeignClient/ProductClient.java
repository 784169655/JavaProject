package com.springcloud.order.SampleFeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 在此定义好 我们要调用 PRODUCT 服务的哪些接口
 * Created by  邱伟
 * 2018/7/2 19:45
 */

@FeignClient(name = "product") //name 为访问哪个应用?
public interface ProductClient {

    @GetMapping("/msg") //访问 porduct 下面的 /msg 这个接口
    String productMsg();
}