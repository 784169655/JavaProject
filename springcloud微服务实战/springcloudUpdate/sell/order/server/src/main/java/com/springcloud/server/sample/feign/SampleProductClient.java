package com.springcloud.server.sample.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**             // 解开入口类的   @EnableFeignClients  注解可用
 * 在此定义好 我们要调用 PRODUCT 服务的哪些接口
 * Created by  邱伟
 * 2018/7/2 19:45
 */

// (注意: 主类上 @EnableFeignClients 后面加了 basePackages
//      SampleProductClient @FeignClient 里的 name 就别定义了)
@FeignClient(name = "product") //name 为访问哪个应用?
public interface SampleProductClient {

    @GetMapping("/msg") //访问 porduct 下面的 /msg 这个接口
    String getProductMsg();
}