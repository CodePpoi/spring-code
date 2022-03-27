package com.AOP.bean;

import com.AOP.annotation.Aspect;
import com.AOP.interceptor.MethodInterceptorChain;
import com.AOP.interceptor.MyMethodInterceptor;
import com.AOP.proxy.BizAImpl;
import com.AOP.proxy.Logging;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AfterAdvice extends Advice implements MyMethodInterceptor {
    
    public AfterAdvice(Method adviceMethod, Aspect aspect) {
        super(adviceMethod, aspect);
        // TODO Auto-generated constructor stub
    }

    public void after(final Object target, final Method method, final Object[] args) {
        this.invokeAspectMethod(target, method, args);
    }

    @Override
    public Object intercept(Method method, Object[] arguments, Object target, MethodInterceptorChain chain) {
        Object obj = chain.intercept(method, arguments, target);
        try {
            adviceMethod.invoke(new Logging(), arguments);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }
}