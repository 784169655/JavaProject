package com.springcloud.server.sample.restTemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 多服务之间的调用    restemplate 方式
 * Created by  邱伟
 * 2018/7/2 17:08
 */

@RestController
@Slf4j
@RequestMapping("/restTemplate")
public class SampleClientController {

    // ========>  1. 第一种方式(直接使用 restTemplate, 缺点 url 写死)  <====
    @GetMapping("/getProductMsg/1")
    public String getProductMsg1() {

        //1. 第一种方式(直接使用 restTemplate, 缺点 url 写死)

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:8080/msg", String.class);
        log.info("response = {}", result);
        return result;
    }
    // ========>  1. 第一种方式(直接使用 restTemplate, 缺点 url 写死)  <====


    // ========>> 2. 第二种方式  (利用 loadBalancerClient 通过应用名获取 url(host 和 port), 然后再使用 restemplate) <<====
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/getProductMsg/2")
    public String getProductMsg2() {

        //拿地址  serviceId就是 产品服务 yml 文件里配置的名字
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        log.info("response = {}", result);
        return result;
    }
    // ========>> 2. 第二种方式  (利用 loadBalancerClient 通过应用名获取 url(host 和 port), 然后再使用 restemplate) <<====


    //>>>>>>>>>>>>>>>>>>>>>>   3. 第三种方式  url 为 http:// 加上 serviceId   <<<<<<<<<<<<<<<<<<
    @Autowired  // 新建 RestTemplateConfig 使用 @Bean @LoadBalanced 注解
    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg/3")
    public String getProductMsg3() {

        //拿地址  serviceId就是 产品服务 yml 文件里配置的名字
        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
        log.info("response={}", response);
        return response;
    }
    //>>>>>>>>>>>>>>>>>>>>>>   3. 第三种方式  url 为 http:// 加上 serviceId   <<<<<<<<<<<<<<<<<<

}

