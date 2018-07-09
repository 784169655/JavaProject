package com.security.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})//可以把这个注解标注在哪里
@Retention(RetentionPolicy.RUNTIME)//运行时的注解
@Constraint(validatedBy = MyConstrainValidator.class)//指定业务逻辑所在的类
public @interface MyConstraint {

    //无论哪个注解,这三行必写
    String message() default "{default message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
