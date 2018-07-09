package com.wxdc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by  邱伟
 * 2018/4/3 19:49
 */

@Data
public class ProductVo {

//    private static final long serialVersionUID = 644581191188426371L;

    //该注解  返回  前端为  name
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}