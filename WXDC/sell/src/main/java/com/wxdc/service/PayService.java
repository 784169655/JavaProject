package com.wxdc.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.wxdc.dto.OrderDTO;

/**
 * 支付
 */

public interface PayService {

    /**
     * 创建预支付订单
     * @param orderDTO
     */
    PayResponse create(OrderDTO orderDTO);

    /**
     * 支付异步通知
     *
     * @param notifyData
     * @return
     */
    PayResponse notify(String notifyData);

    /**
     * 退款
     * @param dto
     */
    RefundResponse refund(OrderDTO dto);
}
