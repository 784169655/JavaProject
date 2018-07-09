package com.wxdc.service.impl;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.wxdc.config.WechatAccountConfig;
import com.wxdc.dto.OrderDTO;
import com.wxdc.enums.ResultEnum;
import com.wxdc.exception.SellException;
import com.wxdc.service.OrderService;
import com.wxdc.service.PayService;
import com.wxdc.utils.JsonUtil;
import com.wxdc.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.xml.PayloadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 * Created by  邱伟
 * 2018/4/9 16:43
 */

@Service
@Transactional
@Slf4j
public class PayServiceImpl implements PayService{

    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());

        //转成double型的
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.ALIPAY_APP);

        log.info("【微信支付】发起支付，request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付，payResponse={}",JsonUtil.toJson(payResponse));

        return payResponse;
    }

    /**
     * 异步通知
     * @param notifyData
     * @return
     */
    @Override
    public PayResponse notify(String notifyData) {

//        异步订单注意事项
        //1. 验证签名   bestPay已做
        //2. 支付状态   bestPay已做
        //3. 支付金额
        //4. 支付人(下单人  ==  支付人)


        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知，payResponse={}", JsonUtil.toJson(payResponse));

        //查询订单
        OrderDTO dto = orderService.findOne(payResponse.getOrderId());
        if (dto == null) {
            log.error("【微信支付】异步通知，orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //判断金额是否一致   注意(0.1 与 0.10)
//        if (dto.getOrderAmount().equals(payResponse.getOrderAmount())) {
        // dto.orderAmout  BigDecimal 类型  payResponse.orderAmount  Double 类型 不能这样做比较，更不能double与double 用equals比较
//        if (dto.getOrderAmount().compareTo(new BigDecimal(payResponse.getOrderAmount())) == 0) {
        //  两个 double 类型  比较  不一致进入该方法
        if (!MathUtil.equals(dto.getOrderAmount().doubleValue(),payResponse.getOrderAmount())) {
            log.error("微信支付】异步通知，订单金额不一致，orderId={}，微信通知金额={}，系统金额={}",
                    payResponse.getOrderId(),payResponse.getOrderAmount(),dto.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        //修改订单的支付状态
        orderService.paid(dto);

        return payResponse;
    }

    /**
     * 退款
     * @param dto
     */
    @Override
    public RefundResponse refund(OrderDTO dto) {

        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(dto.getOrderId());
        refundRequest.setOrderAmount(dto.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("【微信退款】refundRequest={}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】refundResponse={}", JsonUtil.toJson(refundResponse));

        return refundResponse;
    }
}