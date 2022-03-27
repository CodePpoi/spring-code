package com.AOP.proxy.onepackage;


import java.lang.reflect.Method;


public class BeforeMethodInterceptor implements MyMethodInterceptor {

    private BeforeAdvice beforeAdvice;

    @Override
    public Object intercept(Method method, Object[] arguments, Object target, MethodInterceptorChain chain) {
        beforeAdvice.before(target);
        beforeAdvice.before(target, method, arguments);
        return chain.intercept(method, arguments, target);
    }

    public BeforeMethodInterceptor(BeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }

    
    
}