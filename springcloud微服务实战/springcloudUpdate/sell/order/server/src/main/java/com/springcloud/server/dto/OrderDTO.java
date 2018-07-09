package com.springcloud.server.dto;

import com.springcloud.server.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by  邱伟
 * 2018/4/4 10:36
 */

@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态，默认为0新下单
     */
    private Integer orderStatus;

    /**
     * 支付状态，默认为0未支付
     */
    private Integer payStatus;

//    @JsonSerialize(using = Date2LongSerializer.class)
//    private Date createTime;
//
//    @JsonSerialize(using = Date2LongSerializer.class)
//    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    /* 如果某人不小心把该对象 以 rest 接口时  把对象返回出去  则json会多出以下两个字段 用以下注解就能忽略掉该字段 */
//    @JsonIgnore
//    public OrderStatusEnum getOrderStatusEnum() {
//        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
//    }
//
//    @JsonIgnore
//    public PayStatusEnum getPayStatusEnum() {
//        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
//    }
}