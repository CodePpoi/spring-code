package com.atguigu.test;


import com.atguigu.config.MainConfig;
import com.atguigu.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest {

    @Test
    @SuppressWarnings("resources")
    public void test02() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            System.out.println(definitionName);
        }

        System.out.println("ioc容器创建完成...");
        //默认是单实例的
        Object bean = applicationContext.getBean("person");


        Object bean2 = applicationContext.getBean("person");
        System.out.println(bean == bean2);
    }



        @Test
    @SuppressWarnings("resources")
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for(String definitionName: definitionNames) {
            System.out.println(definitionName);
        }

    }
}
