package com.atguigu.config;

import com.atguigu.bean.Person;
import com.atguigu.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

//配置类==配置文件
@Configuration // 告诉Spring这是一个配置类
//@ComponentScan(value = "com.atguigu", excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
//})
@ComponentScan(value = "com.atguigu", includeFilters = {
        //这两种FilterType最常用
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
}, useDefaultFilters = false)
//excludeFilters 指定扫描的时候按照什么规则排除哪些组件
//includeFilters 只包含某些组件
//ComponentScan是可重复注解,ComponentScans则是复数形式
public class MainConfig {

    //给容器中注册一个Bean 类型为返回值的类型, id默认是用方法名作为id
    @Bean("person") //优先用注解标明的id作为bean的id
    public Person person01() {
        return new Person("lisi", 20);
    }
}
