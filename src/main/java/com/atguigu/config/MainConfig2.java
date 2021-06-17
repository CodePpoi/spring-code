package com.atguigu.config;

import com.atguigu.bean.Color;
import com.atguigu.bean.ColorFactoryBean;
import com.atguigu.bean.Person;
import com.atguigu.bean.Red;
import com.atguigu.condition.LinuxCondition;
import com.atguigu.condition.MyImportBeanDefinitionRegistrar;
import com.atguigu.condition.MyImportSelector;
import com.atguigu.condition.WindowsCondition;
import org.springframework.context.annotation.*;

//满足windowscondition时，才会注册这个配置类里面所有的bean
@Configuration
@Conditional({WindowsCondition.class})
//import快速导入组件，ID默认是组件的全类名
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig2 {


    //prototype 多实例 ioc启动时不会调用该方法，每次获取的时候才会调用方法创建对象
    //singleton (默认值) ioc启动的时候会调用该方法创建对象, 放到ioc容器中,以后每次获取就是直接从容器(map.get())中拿
    //request 同一次请求创建一个 基本不用
    //session 同一个session创建一个实例 基本不用
    //默认是单实例的
//    @Scope("prototype")
    @Bean("person")
    @Lazy
    public Person person() {
        System.out.println("给容器中添加person...");
        return new Person("张三", 25);
    }

//    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gates", 62);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linux")
    public Person person02() {
        return new Person("linus", 48);
    }

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
