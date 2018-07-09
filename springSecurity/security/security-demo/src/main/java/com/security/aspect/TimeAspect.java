package com.security.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by  邱伟
 * 2018/5/16 19:22
 */

@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.security.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");

        //拿参数
        Object[] args = pjp.getArgs();
        for (Object arg:args){
            System.out.println("arg is:" + arg);
        }

        long start = System.currentTimeMillis();

        //相当于调控制器链的方法 chain.doFilter(request,reponse)       返回的是方法返回的内容
        Object o = pjp.proceed();
        System.out.println("time aspect 耗时:" + (System.currentTimeMillis() - start));

        System.out.println("time aspect end");
        return o;
    }
}