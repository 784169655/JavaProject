package com.wxdc.repository;

import com.wxdc.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by  邱伟
 * 2018/4/16 19:20
 */

public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String openid);


}