package com.wxdc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by  邱伟
 * 2018/5/23 08:42
 */

@RestController
@RequestMapping("/connection")
public class ConnectionTestController {

    @GetMapping
    public String success() {
        return "Connection Success!";
    }
}