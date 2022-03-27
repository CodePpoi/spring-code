package com.AOP.proxy.onepackage;

import com.AOP.annotation.Aspect;
import com.AOP.bean.Advice;
import com.AOP.interceptor.MethodInterceptorChain;
import com.AOP.interceptor.MyMethodInterceptor;
import com.AOP.proxy.BizAImpl;

import java.lang.reflect.Method;

public class AfterAdvice extends Advice implements MyMethodInterceptor {
    
    public AfterAdvice(Method adviceMethod, Aspect aspect) {
        super(adviceMethod, aspect);
        // TODO Auto-generated constructor stub
    }

    public void after(final Object target, final Method method, final Object[] args) {
        this.invokeAspectMethod(target, method, args);
    }

    public void after(Object add) {
        System.out.println("after " + ++ ((BizAImpl) add).c);
    }

    @Override
    public Object intercept(Method method, Object[] arguments, Object target, MethodInterceptorChain chain) {
        Object obj = chain.intercept(method, arguments, target);
        this.after(target, method, arguments);
        return obj;
    }
}