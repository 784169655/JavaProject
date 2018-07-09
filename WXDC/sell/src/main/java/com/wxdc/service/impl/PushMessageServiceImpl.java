package com.wxdc.service.impl;

import com.wxdc.config.WechatAccountConfig;
import com.wxdc.config.WechatMpAccoutTestConfig;
import com.wxdc.dto.OrderDTO;
import com.wxdc.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * Created by  邱伟
 * 2018/4/19 18:52
 */

@Service
@Transactional
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

//    @Autowired
//    private WxMpService wxMpService;

    @Autowired
    private WechatMpAccoutTestConfig accoutTestConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();

        //模版ID
//        templateMessage.setTemplateId("cqwieNKSar2QGzf0-TOOiCK_Xqgdau-LJpOqXq_C7vA");
        templateMessage.setTemplateId(accoutTestConfig.getTestTemplateId().get("orderStatus"));

        //推送给谁 openid  用户相对于公众号的openid
        templateMessage.setToUser("odUzS03upHGJj9LSjQvhOfbikdVg");
//        templateMessage.setToUser(orderDTO.getBuyerOpenid());

        //配置微信订单模版消息
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "亲记得收货"),
                new WxMpTemplateData("keyword1", "微信点餐","blue"),
                new WxMpTemplateData("keyword2", orderDTO.getBuyerPhone()),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword5", "¥" + orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark", "欢迎再次光临")
        );
        templateMessage.setData(data);

        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());

        try {
            //推送失败后立即catch掉  是为了防止仅仅发送模版这个小业务而影响  订单大业务的回滚
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("【微信模版消息】发送失败,{}", e);
        }

        log.info("【微信模版消息】发送成功！");
    }

    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(accoutTestConfig.getTestMpAppId());
        wxMpConfigStorage.setSecret(accoutTestConfig.getTestMpAppSecret());
        return wxMpConfigStorage;
    }
}