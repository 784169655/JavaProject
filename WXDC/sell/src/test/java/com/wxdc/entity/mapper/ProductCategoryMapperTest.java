package com.wxdc.entity.mapper;

import com.wxdc.entity.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception {

        int result = mapper.insertByMap(new HashMap() {{
            put("categoryName", "我的最爱");
            put("category_type", 202);
        }});

        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("我的最不爱");
        productCategory.setCategoryType(203);

        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryTypeTest() {
        ProductCategory categoryType = mapper.findByCategoryType(203);
        Assert.assertNotNull(categoryType);
    }

    @Test
    public void findByCategoryNameTest() {
        List<ProductCategory> result = mapper.findByCategoryName("我的最不爱");
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void updateByCategoryType() {
        int result = mapper.updateByCategoryType("我的最不爱的分类", 203);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("我的最不爱");
        productCategory.setCategoryType(203);

        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteByCategoryType() {
        int result = mapper.deleteByCategoryType(203);
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectByCategroyType() {
        ProductCategory productCategory = mapper.selectByCategroyType(203);
        Assert.assertNotNull(productCategory);
    }

}