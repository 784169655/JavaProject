package com.springcloud.order.controller;


import com.springcloud.order.converter.OrderForm2OrderDTOConverter;
import com.springcloud.order.dto.OrderDTO;
import com.springcloud.order.enums.ResultEnum;
import com.springcloud.order.exception.SellException;
import com.springcloud.order.form.OrderForm;
import com.springcloud.order.service.OrderService;
import com.springcloud.order.vo.ResultVo;
import com.springcloud.order.vo.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  买家商品订单
 * Created by  邱伟
 * 2018/4/4 16:29
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

//    @Autowired
//    private BuyerService buyerService;

    /**
     * 1. 参数检验
     * 2. 查询商品信息(调用商品服务)
     * 3. 计算总价
     * 4. 扣库存(调用商品服务)
     * 5. 订单入库
     */
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Validated OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        // orderForm -> orderDTO
        OrderDTO dto = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(dto.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(dto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ResultVoUtil.success(map);
    }


//    @ApiOperation(value = "订单列表",notes = "使用场景:用户分页查询订单")
//    @GetMapping("/list")
//    public ResultVo<List<OrderDTO>> list(@RequestParam String openid,
//                                         @RequestParam(defaultValue = "0") int page,
//                                         @RequestParam(defaultValue = "10") int size) {
//        if (StringUtils.isEmpty(openid)) {
//            log.error("【查询订单列表】openid为空");
//            throw new SellException(ResultEnum.PARAM_ERROR);
//        }
//
//        PageRequest request = new PageRequest(page, size);
//        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);
//
//        return ResultVoUtil.success(orderDTOPage.getContent());
//    }
//
//    @ApiOperation("订单详情")
//    @GetMapping("/detail")
//    public ResultVo<OrderDTO> detail(@RequestParam String openid,
//                                     @ApiParam("订单id") @RequestParam String orderId) {
//
//        OrderDTO dto = buyerService.findOrderOne(openid, orderId);
//        return ResultVoUtil.success(dto);
//    }
//
//    @ApiOperation(value = "取消订单",notes = "使用场景:用户取消订单")
//    @PostMapping("/cancel")
//    public ResultVo cancel(@RequestParam String openid,
//                           @RequestParam String orderId) {
//
//        buyerService.cancelOrder(openid, orderId);
//        return ResultVoUtil.success();
//    }


}