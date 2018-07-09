package com.wxdc.service.impl;

import com.wxdc.dto.OrderDTO;
import com.wxdc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayServiceImpl payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void createTest() throws Exception {
        OrderDTO dto = orderService.findOne("1522938909126298121");
        payService.create(dto);
    }

    @Test
    public void refundTest() {
        String orderId = "1524386602805260573";
        OrderDTO dto = orderService.findOne(orderId);
        payService.refund(dto);
    }

}