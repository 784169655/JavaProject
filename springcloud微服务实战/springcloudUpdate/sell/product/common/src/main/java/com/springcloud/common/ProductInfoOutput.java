package com.springcloud.common;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by  邱伟
 * 2018/7/4 19:59
 */

@Data
public class ProductInfoOutput {

    private String productId;

    /** 产品名称 */
    private String productName;

    /** 产品价格 */
    private BigDecimal productPrice;

    /** 库存 */
    private Integer productStock;

    /** 产品描述 */
    private String productDescription;

    /** 小图(链接地址) */
    private String productIcon;

    /** 产品状态(0上架、1下架) */
    private Integer productStatus;

    /** 类目编号 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

}