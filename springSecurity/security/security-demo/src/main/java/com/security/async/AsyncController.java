package com.security.async;

import lombok.extern.java.Log;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * 异步处理控制器
 * Created by  邱伟
 * 2018/5/16 20:22
 */

@RestController
@Log
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {

        log.info("主线程开始");

//        Thread.sleep(1000);

        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult();
        deferredResultHolder.getMap().put(orderNumber, result);

//        Callable<String> result = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                log.info("副线程开始");
//                Thread.sleep(1000);
//                log.info("副线程返回");
//                return "success";
//            }
//        };



        log.info("主线程返回");
        return result;
    }
}