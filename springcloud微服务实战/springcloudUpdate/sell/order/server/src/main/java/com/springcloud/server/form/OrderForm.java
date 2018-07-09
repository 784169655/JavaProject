package com.springcloud.server.form;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by  邱伟
 * 2018/4/4 16:32
 */

@Data
public class OrderForm {

    /** 买家姓名 */
//    @NotEmpty(message = "姓名必填")
//    @ApiModelProperty("姓名")
    private String name;

    /** 买家手机号 */
//    @NotEmpty(message = "手机号必填")
//    @ApiModelProperty("手机号")
    private String phone;

    /** 买家地址 */
//    @NotEmpty(message = "地址必填")
//    @ApiModelProperty("地址")
    private String address;

    /** 买家微信openid */
//    @NotEmpty(message = "openid必填")
//    @ApiModelProperty("openid")
    private String openid;

    /** 购物车 */
//    @NotEmpty(message = "购物车不能为空")
//    @ApiModelProperty("购物车")
    private String items;

}