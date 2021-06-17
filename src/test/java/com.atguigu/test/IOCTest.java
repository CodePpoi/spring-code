package com.atguigu.test;


import com.atguigu.bean.Blue;
import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;
import com.atguigu.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

public class IOCTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
    @Test
    @SuppressWarnings("resources")
    public void testImport() {
        pringBean(applicationContext);
        Blue blue = applicationContext.getBean(Blue.class);
        System.out.println(blue);

        //工厂Bean获取的是调用getObject创建的对象
        Object bean2 = applicationContext.getBean("colorFactoryBean");
        Object bean3 = applicationContext.getBean("colorFactoryBean");
        System.out.println("bean的类型:" + bean2.getClass());
        System.out.println(bean3 == bean2);

        //要获取本身，需要在id前面加一个&标识
        Object bean4 = applicationContext.getBean("&colorFactoryBean");

        System.out.println("bean4的类型:" + bean4.getClass());
    }
    private void pringBean(AnnotationConfigApplicationContext applicationContext) {
        String[] definnitionNames =applicationContext.getBeanDefinitionNames();
        for(String name: definnitionNames) {
            System.out.println(name);
        }
    }

    @Test
    @SuppressWarnings("resources")
    public void test03() {
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);
        for(String name: namesForType) {
            System.out.println(name);
        }

        Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);
        System.out.println(persons);
    }

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
