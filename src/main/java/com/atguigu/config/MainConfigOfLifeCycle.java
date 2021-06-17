package com.atguigu.config;

import com.atguigu.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

//bean的创建(构造函数，比如Car里面的无参构造函数) 初始化(对象创建完成，并赋值好，调用初始化方法) 销毁(容器关闭的时候)
/**
 * 1) 指定初始化和销毁方法:
 *  通过@Bean指定init-method和destroy-method
 * 2) 通过让Bean实现InitializingBean(定义初始化逻辑),
 * DisposableBean(定义销毁逻辑)
 * */
public class MainConfigOfLifeCycle {
    @Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }
}
