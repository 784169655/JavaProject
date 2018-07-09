package com.wxdc.controller;

import com.wxdc.entity.ProductCategory;
import com.wxdc.entity.ProductInfo;
import com.wxdc.service.ProductCategoryService;
import com.wxdc.service.ProductService;
import com.wxdc.utils.ResultVoUtil;
import com.wxdc.vo.ProductInfoVo;
import com.wxdc.vo.ProductVo;
import com.wxdc.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * Created by  邱伟
 * 2018/4/3 19:37
 */
@RestController
@RequestMapping("/sell/buyer/product")
@Api("买家商品列表")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService categoryService;

    @ApiOperation(value = "查询所有上架的商品",notes = "使用场景:用于app端展示")
    @GetMapping("/list")
//    @Cacheable(cacheNames = "product",key = "123",unless = "#result.getCode() !=0")   //缓存  第一次执行下面的方法，把返回的resultVo放入redis里，第二次就不执行下面的方法了，直接读取缓存。
    public ResultVo list() {

        //1. 查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2. 查询 所有上架商品  所对应的 类目(一次性查询)
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(productInfo -> productInfo.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3. 数据拼装
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo info : productInfoList) {
                if (info.getCategoryType().equals(productCategory.getCategoryType())) {

                    ProductInfoVo vo = new ProductInfoVo();
                    // spring 提供一个拷贝  一个对象属性的值 到 另个对象属性里
                    BeanUtils.copyProperties(info,vo);
                    productInfoVoList.add(vo);
                }

            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }

        return ResultVoUtil.success(productVoList);
    }
}