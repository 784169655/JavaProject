package com.springcloud.server.converter;

import com.springcloud.server.dataobject.OrderMaster;
import com.springcloud.server.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by  邱伟
 * 2018/4/4 14:29
 */

public class OrderMaster2OrderDtoConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO dto = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, dto);
        return dto;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}