package com.AOP.proxy;

public class BizAImpl implements IBizA{


    public int c = 0;


    @Override
    public void doSomething() {
        // TODO Auto-generated method stub
        c++;
        System.out.println("in " + c);
    }
    
}