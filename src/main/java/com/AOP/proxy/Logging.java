package com.AOP.proxy;


import com.AOP.annotation.Aspect;
import com.AOP.proxy.onepackage.MyAfter;
import com.AOP.proxy.onepackage.MyBefore;

@Aspect
public class Logging {

    @MyBefore
    public void before() {
        System.out.println("before " );
    }

    @MyBefore
    public void before2() {
        System.out.println("before 2 " );
    }

    @MyAfter
    public void after() {
        System.out.println("after " );
    }


}
