package com.wxdc.service.impl;

import com.wxdc.dto.OrderDTO;
import com.wxdc.service.OrderService;
import com.wxdc.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() throws Exception {

        OrderDTO dto = orderService.findOne("1524394070436661038");
        pushMessageService.orderStatus(dto);
    }

}