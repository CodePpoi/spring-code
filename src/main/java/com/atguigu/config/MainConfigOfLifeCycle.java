package com.atguigu.config;

import com.atguigu.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//bean的创建(构造函数，比如Car里面的无参构造函数) 初始化(对象创建完成，并赋值好，调用初始化方法) 销毁(容器关闭的时候)
/**
 * 1) 指定初始化和销毁方法:
 *  通过@Bean指定init-method和destroy-method
 * 2) 通过让Bean实现InitializingBean(定义初始化逻辑),
 * DisposableBean(定义销毁逻辑)接口
 * 3) 使用@PostConstruct: 在bean创建完成并完成属性赋值以后 完成初始化
 * @PreDestroy: 在容器销毁bean之前通知我们进行清理工作
 * 4) BeanPostProcessor: bean的后置处理器
 * postProcessBeforeInitialization： 在初始化之前，比如InitializingBean的afterPropertySet方法, bean的init-method方法之前
 * postProcessAfterInitialization: 在初始化之后，比如InitializingBean的afterPropertySet方法, bean的init-method方法之后
 *
 * */
@ComponentScan("com.atguigu.bean")
@Configuration
public class MainConfigOfLifeCycle {
//    @Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }
}
