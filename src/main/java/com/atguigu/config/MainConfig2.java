package com.atguigu.config;

import com.atguigu.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MainConfig2 {


    //prototype 多实例 ioc启动时不会调用该方法，每次获取的时候才会调用方法创建对象
    //singleton (默认值) ioc启动的时候会调用该方法创建对象, 放到ioc容器中,以后每次获取就是直接从容器(map.get())中拿
    //request 同一次请求创建一个 基本不用
    //session 同一个session创建一个实例 基本不用
    //默认是单实例的
    @Scope("prototype")
    @Bean("person")
    public Person person() {
        System.out.println("给容器中添加person...");
        return new Person("张三", 25);
    }
}
