package com.atguigu.condition;

import com.atguigu.bean.Rainbow;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

//    AnnotationMetadata: 当前类的注解信息
//    BeanDefinitionRegistry: BeanDefinition的注册类
//    需要添加到容器中的bean，调用 BeanDefinitionRegistry.registerBeanDefinition 手工注册进来

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {

        boolean definition0 = registry.containsBeanDefinition("com.atguigu.bean.Red");
        boolean definition1 = registry.containsBeanDefinition("com.atguigu.bean.Blue");
        if(definition0 && definition1) {
            //指定Bean定义信息，(Bean的类型，Bean的scope )
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Rainbow.class);
            //通过registry方法注册一个bean,并且指定bean名
            registry.registerBeanDefinition("rainBow", beanDefinition);
        }
    }
}
