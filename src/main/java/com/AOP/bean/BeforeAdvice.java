package com.AOP.bean;


import com.AOP.annotation.Aspect;
import com.AOP.interceptor.MethodInterceptorChain;
import com.AOP.interceptor.MyMethodInterceptor;
import com.AOP.proxy.BizAImpl;
import com.AOP.proxy.Logging;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeforeAdvice extends Advice implements MyMethodInterceptor {
    public BeforeAdvice(Method adviceMethod, Aspect aspect) {
        super(adviceMethod, aspect);
        // TODO Auto-generated constructor stub
    }



    public void before(final Object target, final Method method, final Object[] args) {
        this.invokeAspectMethod(target, method, args);
    }

    @Override
    public Object intercept(Method method, Object[] arguments, Object target, MethodInterceptorChain chain) {
        //before(target);
        //this.before(target, method, arguments);
        try {
            adviceMethod.invoke(new Logging(), arguments);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return chain.intercept(method, arguments, target);
    }
}