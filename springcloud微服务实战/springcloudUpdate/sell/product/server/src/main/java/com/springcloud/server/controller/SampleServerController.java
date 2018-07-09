package com.springcloud.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**     示例  //      order服务(clent) 调用 product服务(server)     //
 * Created by  邱伟
 * 2018/7/2 16:54
 */

@RestController
public class SampleServerController {

    @GetMapping("/msg")
    public String msg() {
        return "Msg: this is product server ";
    }
}