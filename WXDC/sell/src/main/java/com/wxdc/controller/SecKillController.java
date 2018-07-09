package com.wxdc.controller;

import com.wxdc.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒杀活动
 * Created by  邱伟
 * 2018/4/25 09:57
 */
@RestController
@RequestMapping("sell/skill")
@Slf4j
public class SecKillController {

    @Autowired
    private SeckillService seckillService;

    /**
     * 查询秒杀活动特价商品的信息
     * @param productId
     * @return
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) throws Exception {
        return seckillService.querySeckillProductInfo(productId);
    }

    /**
     * 秒杀，没有抢到获得"哎呦喂,xxxxx",抢到了会返回剩余的库存量
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId) throws Exception {
        log.info("@skill request,productId:" + productId);

        //秒杀的方法
        seckillService.orderProductMockDiffUser(productId);

        //查询库存的方法
        return seckillService.querySeckillProductInfo(productId);
    }
}