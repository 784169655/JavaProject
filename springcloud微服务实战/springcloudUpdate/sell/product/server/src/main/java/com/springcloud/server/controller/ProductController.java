package com.springcloud.server.controller;

import com.springcloud.common.DecreaseStockInput;
import com.springcloud.common.ProductInfoOutput;
import com.springcloud.common.utils.ResultVo;
import com.springcloud.common.utils.ResultVoUtil;
import com.springcloud.server.dataobject.ProductCategory;
import com.springcloud.server.dataobject.ProductInfo;
import com.springcloud.server.service.CategoryService;
import com.springcloud.server.service.ProductService;
import com.springcloud.server.vo.ProductInfoVo;
import com.springcloud.server.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品
 * Created by  邱伟
 * 2018/7/1 17:35
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据dataobject
     */
    @GetMapping("/list")
    public ResultVo<ProductVo> list() {
        //1. 查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2. 获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType).collect(Collectors.toList());

        //3. 从数据库查询类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //4. 构造数据
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
                    BeanUtils.copyProperties(info, vo);
                    productInfoVoList.add(vo);
                }

            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }

        return ResultVoUtil.success(productVoList);

    }

    /**
     * 获取商品列表（给订单服务用的）
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
        return productService.findList(productIdList);
    }

    /**
     *  减库存
     * @param DecreaseStockInputList
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> DecreaseStockInputList) {
        productService.decreaseStock(DecreaseStockInputList);
    }
}