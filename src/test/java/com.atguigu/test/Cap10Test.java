package com.atguigu.test;

import com.enjoy.cap10.aop.Calculator;
import com.enjoy.cap10.config.Cap10MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Cap10Test {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap10MainConfig.class);

        //Calculator c = new Calculator();
        Calculator c = app.getBean(Calculator.class);
        int result = c.div(4, 3);
        System.out.println(result);
        app.close();


    }
}

