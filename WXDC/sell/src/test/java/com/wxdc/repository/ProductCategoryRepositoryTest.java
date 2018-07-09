package com.wxdc.repository;

import com.wxdc.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory);
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        System.out.println(repository.save(productCategory));
    }

    @Test
    public void updateTest() {
        ProductCategory productCategory = repository.findOne(2);
        productCategory.setCategoryType(4);
        // Assert  需要  无参的构造方法才行
        Assert.assertNotNull(repository.save(productCategory));
    }

    @Test
    public void findByCategoryTypeList() {
        List<ProductCategory> result = repository.findByCategoryTypeIn(Arrays.asList(2, 4));
        Assert.assertNotEquals(0,result.size());
    }
}