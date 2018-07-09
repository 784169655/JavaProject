package com.springcloud.server.repository;

import com.springcloud.server.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    /**
     *  通过一系列类目编号   查询   一系列类目          只会查出它包含的，in里多了的不会报错，例数据库里只有2，3  传入2，3，4 不会报错
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
