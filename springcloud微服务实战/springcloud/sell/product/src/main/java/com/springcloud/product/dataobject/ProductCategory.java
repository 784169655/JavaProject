package com.springcloud.product.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by  邱伟
 * 2018/7/2 09:56
 */
//@Table(name = "sell_product_category")
@Entity
@Data
@DynamicUpdate   //监听数据更新，更新后 updateTime 会自动更新
public class ProductCategory {

    /** 类目id. */
    @Id
    @GeneratedValue //自增
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}