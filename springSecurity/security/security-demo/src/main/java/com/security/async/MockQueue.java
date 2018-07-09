package com.security.async;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

/**
 * Created by  邱伟
 * 2018/5/16 20:42
 */

@Component
@Log
public class MockQueue {

    /** 下单的消息 */
    private String placeOrder;

    /** 订单完成的消息 */
    private String completeOrder;



    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {

        new Thread(() ->{

            System.out.println("接到下单请求");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.completeOrder = placeOrder;
            log.info("下单请求处理完成");

        }).start();

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}