package com.wxdc.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by  邱伟
 * 2018/4/16 17:31
 */

@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    @ApiModelProperty("类目名字")
    private String categoryName;

    /** 类目编号. */
    @ApiModelProperty("类目编号")
    private Integer categoryType;

}