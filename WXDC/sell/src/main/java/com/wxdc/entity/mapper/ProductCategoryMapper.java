package com.wxdc.entity.mapper;

import com.wxdc.entity.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface ProductCategoryMapper {


    //通过 map 的方式写入
    @Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    //通过 对象 的方式写入    注意对象里的字段要对应起来                                      字段                              字段
    @Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryTyep}")
    @Results({
            @Result(column = "category_id", property ="categoryId"),
            @Result(column = "category_name", property ="categoryName"),
            @Result(column = "category_tyep", property ="categoryTyep"),
    })
    ProductCategory findByCategoryType(Integer categoryTyep);

    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_tyep", property = "categoryTyep"),
    })
//    ProductCategory findByCategoryName(String categoryName); 注意当jpa这样查 会返回第一条，由于mabatis比较严格，这样查会报错
    List<ProductCategory> findByCategoryName(String categoryName);


    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,
                             @Param("categoryType") Integer categoryType);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete form product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);


    //使用xml方式  写好xml 后在yml 文件里配置好mapper路径
    ProductCategory selectByCategroyType(Integer categoryType);
}
