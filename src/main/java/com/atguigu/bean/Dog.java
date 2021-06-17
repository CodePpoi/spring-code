package com.atguigu.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog {
    public Dog() {
        System.out.println("dog construct");
    }

    //对象创建并赋值之后调用
    @PostConstruct
    public void init() {
        System.out.println("dog post construct");
    }

    //容器移除对象之前
    @PreDestroy
    public void destroy() {
        System.out.println("dog pre destroy");
    }
}
