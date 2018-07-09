package com.wxdc.handler;

import com.wxdc.config.ProjectUrlConfig;
import com.wxdc.exception.ResponseBankException;
import com.wxdc.exception.SellException;
import com.wxdc.exception.SellerAuthorizeException;
import com.wxdc.utils.ResultVoUtil;
import com.wxdc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截登录异常
 * Created by  邱伟
 * 2018/4/18 20:30
 */
//@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    //http://xiaowei.nat300.top/sell/wechat/qrAuthorize?returnUrl=http://xiaowei.nat300.top/sell/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getSell())
                .concat("/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody //由于是上面并不是restController
    public ResultVo handlerSellerException(SellException e) {
        return ResultVoUtil.error(e.getCode(), e.getMessage());
    }

    //    假如现在要返回403
    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerSellerException() {

    }
}