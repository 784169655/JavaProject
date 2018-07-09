package com.example.controller;

import com.example.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by  邱伟
 * 2018/3/28 15:24
 */

@RestController
@RequestMapping("/girl")
public class GirlController {

    @Value("${cupSize}")
    private String cupSize1;

    @Value("${age}")
    private Integer age;

    @Value("${content}")
    private String content;

    @Autowired
    private GirlProperties girlProperties;

    @GetMapping(value = {"/get","get1"})
    public String get() {
        return "girl age:" + age + ",cupSize1" + cupSize1 + "; content:" + content;
    }

    @GetMapping("/getGirl")
    public String getGirlProperties() {
        return girlProperties.toString();
    }


    //  链接：http://localhost:8081/girl/get/2
    @GetMapping(value = {"/say/{id}","/{id}/say"})
    public String say(@PathVariable String id) {
        return "id: " + id+"afdda";
    }

    //  链接：http://localhost:8081/girl/say?id=2      @RequestParam("id")中的id要与链接问号后的id  变量名一致
    @GetMapping("/say")
    public String say1(@RequestParam(value = "id",required = false,defaultValue = "0") Integer sayId) {
        return "sayId: " + sayId+"adfafadfad";
    }

    //  链接：http://localhost:8080/girl/girl/say12?sayId=2      @RequestParam("id")中的id要与链接问号后的id  变量名一致
    @GetMapping("/say12")
    public String say12(@RequestParam(required = false,defaultValue = "0") Integer sayId) {
        return "sayId: " + sayId;
    }
}