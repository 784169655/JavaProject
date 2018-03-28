package com.spring2.encounter.UserController;

import com.spring2.encounter.domain.User;
import com.spring2.encounter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by  邱伟
 * 2018/3/27 10:14
 */

@RestController
public class UserController {

    private final UserRepository userRepository;

    /**
     * 构造器的注入方式     缺点：不能修改     优点：可以提早初始化      首先定义一个final字段，然后构造器传参。
     *
     * @param userRepository
     */
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/person/save")
    public User save(@RequestParam String name) {
        User user = new User();
        user.setName(name);

        if (userRepository.save(user)){
            System.out.printf("用户对象: %s 保存成功 \n",user);
        }

        return user;
    }
}