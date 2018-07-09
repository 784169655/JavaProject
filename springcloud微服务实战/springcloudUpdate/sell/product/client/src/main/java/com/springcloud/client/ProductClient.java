package com.springcloud.client;

import com.springcloud.common.DecreaseStockInput;
import com.springcloud.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by  邱伟
 * 2018/7/3 09:30
 */

@FeignClient(name = "product")
public interface ProductClient {

    /**
     * 获取商品列表（给订单服务用的）
     *
     * @param productInfoOutputList
     * @return
     */
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(List<String> productInfoOutputList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}