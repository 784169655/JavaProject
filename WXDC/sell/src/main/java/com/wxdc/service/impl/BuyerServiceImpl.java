package com.wxdc.service.impl;

import com.wxdc.dto.OrderDTO;
import com.wxdc.enums.ResultEnum;
import com.wxdc.exception.SellException;
import com.wxdc.service.BuyerService;
import com.wxdc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by  邱伟
 * 2018/4/4 18:36
 */

@Service
@Transactional
@Slf4j
public class BuyerServiceImpl implements BuyerService{

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO dto = checkOrderOwner(openid, orderId);
        if (dto == null) {
            log.error("【取消订单】查不到该订单, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(dto);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO dto = orderService.findOne((orderId));
        if (dto == null) {
            return null;
        }

        //如果买家id 和 传过来的 id 不相等  说明不是本人
        if (!dto.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致. openid={},orderDTO={}", openid, dto);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return dto;
    }
}