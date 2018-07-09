package com.springcloud.order.SampleRestTemplateConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *  多服务之间的调用
 * Created by  邱伟
 * 2018/7/2 17:08
 */

@RestController
@Slf4j
public class SampleClientController {

    //第二种方式
    @Autowired
    private LoadBalancerClient loadBalancerClient;

//    @Autowired
//    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //第一种方式(直接使用 restTemplate, url 写死)
//        RestTemplate restTemplate = new RestTemplate();
//        String s = restTemplate.getForObject("http://localhost:8080/msg", String.class);
//        log.info("response={}", s);

        //第二种方式  (利用 loadBalancerClient 通过应用名获取 url , 然后再使用 restemplate)
        //  serviceId就是 产品服务 yml 文件里配置的名字
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
        RestTemplate restTemplate = new RestTemplate();
        String s = restTemplate.getForObject(url, String.class);
        log.info("response={}", s);
        return s;

        //第三种方式  url 为 http:// 加上 serviceId
//        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//        log.info("response={}", response);
//        return response;

    }


}