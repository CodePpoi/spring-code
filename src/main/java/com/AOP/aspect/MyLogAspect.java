package com.AOP.aspect;

import com.AOP.bean.MyBefore;

import java.lang.reflect.Method;

public class MyLogAspect {
    
    //@Pointcut("com.aop.biz.*.*")
    public void pointcut() {

    }

    @MyBefore("pointcut")
    public void beforeAdvice(Method method,Object object) {
        System.out.println("-------before------");
        System.out.println(object.getClass().getName());
    }

}