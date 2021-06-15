package com.atguigu.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //获取当前的类加载器
        ClassLoader classLoader = context.getClassLoader();
        //获取环境变量
        Environment environment =context.getEnvironment();

        //获取bean的注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        String name = environment.getProperty("os.name");

        //判断容器中的bean注册情况，也可以给容器中注册bean
        boolean definition = registry.containsBeanDefinition("person");
        if (name.contains("linux")) {
            return true;
        }
        return false;
    }
}
