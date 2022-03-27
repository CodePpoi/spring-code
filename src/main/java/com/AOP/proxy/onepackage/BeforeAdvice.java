package com.AOP.proxy.onepackage;


import com.AOP.annotation.Aspect;
import com.AOP.bean.Advice;
import com.AOP.interceptor.MethodInterceptorChain;
import com.AOP.interceptor.MyMethodInterceptor;
import com.AOP.proxy.BizAImpl;

import java.lang.reflect.Method;

public class BeforeAdvice extends Advice implements MyMethodInterceptor {
    public BeforeAdvice(Method adviceMethod, Aspect aspect) {
        super(adviceMethod, aspect);
        // TODO Auto-generated constructor stub
    }

    public void before(Object add) {
        System.out.println("before " + ++ ((BizAImpl) add).c);
    }

    public void before(final Object target, final Method method, final Object[] args) {
        this.invokeAspectMethod(target, method, args);
        ;
    }

    @Override
    public Object intercept(Method method, Object[] arguments, Object target, MethodInterceptorChain chain) {
        before((BizAImpl)target);
        this.before(target, method, arguments);
        return chain.intercept(method, arguments, target);
    }
}