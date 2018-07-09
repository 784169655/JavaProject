package com.wxdc.dataObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类目表
 *
 * Created by  邱伟
 * 2018/4/3 15:19
 *   表名  sell_product_category   可以用下面这个注解
 */
//@Table(name = "sell_product_category")
@Entity
@Data
public class ProductCategory {

    /** 类目id. */
    @Id
    @GeneratedValue
    private String categoryName;

    /** 类目名字. */
    private Integer categoryId;

    /** 类目编号. */
    private Integer categoryType;
}