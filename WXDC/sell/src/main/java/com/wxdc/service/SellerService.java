package com.wxdc.service;

import com.wxdc.entity.SellerInfo;

/**
 * 卖家端
 * Created by  邱伟
 * 2018/4/16 19:29
 */

public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}