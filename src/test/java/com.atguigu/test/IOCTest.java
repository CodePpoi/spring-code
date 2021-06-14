package com.atguigu.test;


import com.atguigu.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest {

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
