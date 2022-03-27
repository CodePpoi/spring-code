package com.AOP.bean;

import com.AOP.annotation.Aspect;
import com.AOP.proxy.Logging;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Advice {
    public Method adviceMethod;
    public Aspect aspect;

    public void invokeAspectMethod(final Object target, final Method method, final Object[] args) {
        // 假设advice的参数固定为target、method和args
        try {
            //adviceMethod.invoke( new Logging(), method, args);
            adviceMethod.invoke(aspect, target, method, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Advice(Method adviceMethod, Aspect aspect) {
        this.adviceMethod = adviceMethod;
        this.aspect = aspect;
    }

}