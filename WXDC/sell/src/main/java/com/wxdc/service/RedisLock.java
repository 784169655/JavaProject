package com.wxdc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 基于 redis 的分布式锁
 * Created by  邱伟
 * 2018/4/25 10:05
 */

@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     *  加锁
     * @param key  productId
     * @param value  当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value) {
        //如果返回true说明是第一个线程      setNX
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        //currentValue=A 这两个线程的value都是B  只有其中一个线程拿到锁
        String currentValue = redisTemplate.opsForValue().get(key);
        //如果锁过期           两个线程同时到这里            用来防止加锁执行过程中，IO、网络异常等而解不了锁
        if (!StringUtils.isEmpty(currentValue)
                //currentValue为String类型 转 long类型
                && Long.parseLong(currentValue)<System.currentTimeMillis()){
            //获取上一个锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁异常,{}", e);
            e.printStackTrace();
        }
    }

}