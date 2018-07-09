package com.springcloud.server.controller;

import com.springcloud.server.client.ProductClient;
import com.springcloud.server.dataobject.ProductInfo;
import com.springcloud.server.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**         // 解开入口类的   @EnableFeignClients  注解可用
 * Created by  邱伟
 * 2018/7/3 11:31
 */

@RestController
@Slf4j
public class ClientController {

//    @Autowired
//    private ProductClient productClient;
//
//    @GetMapping("getProductList")
//    public String getProductList() {
//        List<ProductInfo> productInfoList = productClient.listForOrder(Arrays.asList("123", "1234"));
//        log.info("response={}", productInfoList);
//        return "ok";
//    }
//
//    @GetMapping("/productDecreaseStock")
//    public String productDecreaseStock() {
//        productClient.decreaseStock(Arrays.asList(new CartDTO("123",3)));
//        return "ok";
//    }
}