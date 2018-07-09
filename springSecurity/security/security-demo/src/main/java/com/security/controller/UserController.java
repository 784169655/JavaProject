package com.security.controller;

import com.security.dto.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  邱伟
 * 2018/5/14 18:43
 */

@RestController
public class UserController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<User> query() {

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }
}