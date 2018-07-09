package com.wxdc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wxdc.enums.ProductStatusEnum;
import com.wxdc.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * Created by  邱伟
 * 2018/4/3 17:20
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
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
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 类目编号 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}