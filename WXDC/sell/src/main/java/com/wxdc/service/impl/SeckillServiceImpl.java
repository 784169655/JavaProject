package com.wxdc.service.impl;

import com.wxdc.exception.SellException;
import com.wxdc.service.RedisLock;
import com.wxdc.service.SeckillService;
import com.wxdc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * 秒杀活动
 * Created by  邱伟
 * 2018/4/25 10:00
 */
@Service
@Transactional
@Slf4j
public class SeckillServiceImpl implements SeckillService{

    private static final int TIMEOUT = 10 * 1000;  //超时时间 10s

    @Autowired
    private RedisLock redisLock;

    /**
     * 国庆活动，皮蛋粥特价，限量100000份
     */
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;

    static {

        /**
         * 模拟多个表，商品信息表，库存表，秒杀成功订单表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();

        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    private String queryMap(String productId) {
        return "国庆活动，皮蛋粥特价，限量份"
                + products.get(productId)
                +" 还剩：" + stock.get(productId)+" 份"
                +" 该商品成功下单用户数目："
                +  orders.size() +" 人" ;
    }


    /**
     * 查询秒杀活动特价商品的信息
     * @param productId
     * @return
     */
    @Override
    public String querySeckillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    /**
     *  需求是在内存里进行    这一段代码保证单线程的访问
     * 模拟不同用户秒杀同一商品的请求
     * @param productId
     */
    @Override
    public void orderProductMockDiffUser(String productId) {

        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId, String.valueOf(time))) {
            throw new SellException(101, "人太多了，换个姿势再试试！");
        }

        //1.查询该商品库存，为0则活动结束
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "活动结束");
        } else {
            //2.下单(模拟不同用户openid不同)
            orders.put(KeyUtil.genUniqueKey(), productId);

            //3.减库存
            stockNum = stockNum - 1;
            try {
                //因为减库存可能涉及一些IO操作 所以处理的时间设为 100毫秒线程休眠
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }

        //解锁
        redisLock.unlock(productId, String.valueOf(time));
    }
}