package com.AOP.interceptor;

import com.AOP.bean.AfterAdvice;

import java.lang.reflect.Method;


public class AfterMethodInterceptor implements MyMethodInterceptor {

    private AfterAdvice afterAdvice;

    @Override
    public Object intercept(Method method, Object[] arguments, Object target, MethodInterceptorChain chain) {
        Object obj = chain.intercept(method, arguments, target);
        afterAdvice.after(target, method, arguments);
        return obj;
    }

    public AfterMethodInterceptor(AfterAdvice afterAdvice) {
        this.afterAdvice = afterAdvice;
    }


    
}