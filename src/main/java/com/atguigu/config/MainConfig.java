package com.atguigu.config;

import com.atguigu.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//配置类==配置文件
@Configuration // 告诉Spring这是一个配置类
public class MainConfig {

    //给容器中注册一个Bean 类型为返回值的类型, id默认是用方法名作为id
    @Bean("person") //优先用注解标明的id作为bean的id
    public Person person01() {
        return new Person("lisi", 20);
    }
}
