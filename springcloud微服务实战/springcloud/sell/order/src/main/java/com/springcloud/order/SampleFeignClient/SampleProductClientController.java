package com.springcloud.order.SampleFeignClient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  多服务之间的调用
 * Created by  邱伟
 * 2018/7/2 17:08
 */

@RestController
@Slf4j
public class SampleProductClientController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductClientMsg")
    public String getProductMsg() {

        String response = productClient.productMsg();
        log.info("response={}",response);
        return response;
    }


}