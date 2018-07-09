package com.wxdc.service.impl;

import com.wxdc.entity.SellerInfo;
import com.wxdc.repository.SellerInfoRepository;
import com.wxdc.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by  邱伟
 * 2018/4/16 19:31
 */

@Service
@Transactional
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }

}