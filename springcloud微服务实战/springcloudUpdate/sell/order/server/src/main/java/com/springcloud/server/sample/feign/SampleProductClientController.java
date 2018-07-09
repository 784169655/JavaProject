package com.springcloud.server.sample.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// 解开入口类的   @EnableFeignClients  注解可用
/**
 *  feign  使用方法
 *
 *  1. 添加依赖  spring-cloud-starter-openfeign
 *  2. 在启动的主类上加注解   @EnableFeignClients
 *      (注意: 主类上 @EnableFeignClients 后面加了 basePackages 的调用方式
 *          为 被调用的服务上定义了 client接口 ,此时才能用 basePackages 去用被调用方的 ProductClient 接口)
 *  3. 新建 SampleProductClient 声明要调的  哪个服务 的 哪个方法
 *
 *
 * Created by  邱伟
 * 2018/7/2 17:08
 */

@RestController
@Slf4j
@RequestMapping("/feign")
public class SampleProductClientController {

//    @Autowired
//    private SampleProductClient productClient;

//    @GetMapping("/getProductMsg/4")
//    public String getProductMsg() {
//
//        String response = productClient.getProductMsg();
//        log.info("response = {}",response);
//        return response;
//    }


}