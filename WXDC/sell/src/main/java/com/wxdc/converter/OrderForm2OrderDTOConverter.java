package com.wxdc.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wxdc.dto.OrderDTO;
import com.wxdc.entity.OrderDetail;
import com.wxdc.enums.ResultEnum;
import com.wxdc.exception.SellException;
import com.wxdc.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  邱伟
 * 2018/4/4 16:49
 */

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm form) {
        Gson gson = new Gson();

        OrderDTO dto = new OrderDTO();

        dto.setBuyerName(form.getName());
        dto.setBuyerPhone(form.getPhone());
        dto.setBuyerAddress(form.getAddress());
        dto.setBuyerOpenid(form.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(form.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, jsonString={}", form.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        dto.setOrderDetailList(orderDetailList);

        return dto;
    }
}