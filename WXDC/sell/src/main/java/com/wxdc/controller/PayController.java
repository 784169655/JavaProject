package com.wxdc.controller;

import com.lly835.bestpay.model.PayResponse;
import com.wxdc.dto.OrderDTO;
import com.wxdc.enums.ResultEnum;
import com.wxdc.exception.SellException;
import com.wxdc.service.OrderService;
import com.wxdc.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by  邱伟
 * 2018/4/9 16:37
 */

@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;


    @GetMapping
    public ModelAndView pay(@RequestParam String openid,
                            @RequestParam String orderId,
                            @RequestParam String returnUrl,
                            Map<String, Object> map) {
        log.info("openid = {}", openid);

        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2.发起支付
        PayResponse payResponse = payService.create(orderDTO);

        map.put("payResponse", payResponse);

        returnUrl = URLDecoder.decode(returnUrl);
        map.put("returnUrl", returnUrl);

        return new ModelAndView("pay/create", map);
    }

//    @GetMapping("/create")
//    public ModelAndView create(@RequestParam String orderId,
//                               @RequestParam String returnUrl,
//                               Map<String,Object> map) {
//
//        //1.查询订单
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        if (orderDTO == null) {
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }
//
//        //2.发起支付
//        PayResponse payResponse = payService.create(orderDTO);
////        map.put("orderId", "orderId");
//
//        map.put("payResponse", payResponse);
//        map.put("returnUrl", returnUrl);
//
////        return new ModelAndView("pay/create");
//        return new ModelAndView("pay/create",map);
//    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);

        //返回给微信处理结果
        log.info("【异步通知】 返回异步通知给微信");
        return new ModelAndView("pay/success");
    }
}