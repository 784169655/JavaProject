package com.wxdc.controller;

import com.wxdc.dto.OrderDTO;
import com.wxdc.enums.OrderStatusEnum;
import com.wxdc.enums.ResultEnum;
import com.wxdc.exception.SellException;
import com.wxdc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 *  买家端订单
 * Created by  邱伟
 * 2018/4/10 20:04
 */
@Controller
@RequestMapping("/sell/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page  第几页，从1页开始
     * @param size  一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size,
                             Map<String,Object> map) {

        Page<OrderDTO> orderDTOPage = orderService.findList(new PageRequest(page - 1, size));
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("order/list", map);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam String orderId,
                               Map<String,Object> map) {
        OrderDTO dto = null;
        try {
            dto = orderService.findOne(orderId);
            orderService.cancel(dto);
        } catch (SellException s) {
            log.error("【卖家端取消订单】发生异常{}", s.getMessage());

            //提示的信息
            map.put("msg", s.getMessage());
            //跳转的url
            map.put("url", "/sell/seller/order/list");

            return new ModelAndView("common/error", map);
        }

        //提示的信息
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        //跳转的url
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 订单详情
     *
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam String orderId,
                               Map<String, Object> map) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SellException s) {
            log.error("【卖家端查询订单详情】发生异常{}", s.getMessage());

            //提示的信息
            map.put("msg", s.getMessage());
            //跳转的url
            map.put("url", "/sell/seller/order/list");

            return new ModelAndView("common/error", map);
        }

        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/detail", map);
    }

    /**
     *  完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam String orderId,
                                 Map<String, Object> map) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端完结订单】发生异常{}", e.getMessage());

            //提示的信息
            map.put("msg", e.getMessage());
            //跳转的url
            map.put("url", "/sell/seller/order/list");

            return new ModelAndView("common/error", map);
        }

        //提示的信息
        map.put("msg", ResultEnum.ORDER_FINISHED_SUCCESS.getMessage());
        //跳转的url
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success");
    }
}