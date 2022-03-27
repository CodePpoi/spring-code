package com.AOP.proxy.onepackage;

import com.AOP.interceptor.MethodInterceptorChain;
import com.AOP.interceptor.MyMethodInterceptor;

import java.lang.reflect.Method;


public class AfterMethodInterceptor implements MyMethodInterceptor {

    private AfterAdvice afterAdvice;

    @Override
    public Object intercept(Method method, Object[] arguments, Object target, MethodInterceptorChain chain) {
        Object obj = chain.intercept(method, arguments, target);
        afterAdvice.after(target, method, arguments);
        afterAdvice.after(target);
        return obj;
    }

    public AfterMethodInterceptor(AfterAdvice afterAdvice) {
        this.afterAdvice = afterAdvice;
    }


    
}