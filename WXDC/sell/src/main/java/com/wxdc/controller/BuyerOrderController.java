package com.wxdc.controller;

import com.wxdc.converter.OrderForm2OrderDTOConverter;
import com.wxdc.dto.OrderDTO;
import com.wxdc.enums.ResultEnum;
import com.wxdc.exception.SellException;
import com.wxdc.form.OrderForm;
import com.wxdc.service.BuyerService;
import com.wxdc.service.OrderService;
import com.wxdc.utils.ResultVoUtil;
import com.wxdc.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  邱伟
 * 2018/4/4 16:29
 */
@RestController
@RequestMapping("/sell/buyer/order")
@Slf4j
@Api("买家商品订单")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    @ApiOperation(value = "创建订单", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Validated OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO dto = OrderForm2OrderDTOConverter.convert(orderForm);

        if (CollectionUtils.isEmpty(dto.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(dto);
        return ResultVoUtil.success(new HashMap<String, String>() {{
            put("orderId", createResult.getOrderId());
        }});
    }


    @ApiOperation(value = "订单列表",notes = "使用场景:用户分页查询订单")
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list(@RequestParam String openid,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);

        return ResultVoUtil.success(orderDTOPage.getContent());
    }

    @ApiOperation("订单详情")
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam String openid,
                                     @ApiParam("订单id") @RequestParam String orderId) {

        OrderDTO dto = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtil.success(dto);
    }

    @ApiOperation(value = "取消订单",notes = "使用场景:用户取消订单")
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam String openid,
                           @RequestParam String orderId) {

        buyerService.cancelOrder(openid, orderId);
        return ResultVoUtil.success();
    }


}