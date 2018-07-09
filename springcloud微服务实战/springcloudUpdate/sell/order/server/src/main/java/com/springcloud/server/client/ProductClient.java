package com.springcloud.server.client;

import com.springcloud.server.dataobject.ProductInfo;
import com.springcloud.server.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**     // 解开入口类的   @EnableFeignClients  注解可用
 * Created by  邱伟
 * 2018/7/3 09:30
 */

@FeignClient(name = "product")
public interface ProductClient {

//    /**
//     * 获取商品列表（给订单服务用的）
//     *
//     * @param productIdList
//     * @return
//     */
//    @PostMapping("/product/listForOrder")
//    List<ProductInfo> listForOrder(List<String> productIdList);
//
//    /**
//     *  减库存
//     * @param cartDTOList
//     */
//    @PostMapping("/product/decreaseStock")
//    void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
}