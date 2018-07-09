package com.security.validate;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by  邱伟
 * 2018/5/14 19:28
 */
                                                                //声明的注解类    放在什么类型上用
public class MyConstrainValidator implements ConstraintValidator<MyConstraint, Object> {

//    @Autowired  可以注入任何类来实现 校验逻辑

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("初始化");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println("第一个是你传进来的值，第二个是上下文，返回true表示校验成功");
        return false;
    }
}