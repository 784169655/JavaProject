package com.wxdc.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by  邱伟
 * 2018/4/16 14:57
 */
@Data
public class ProductForm {

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

    /** 类目编号 */
    private Integer categoryType;

}