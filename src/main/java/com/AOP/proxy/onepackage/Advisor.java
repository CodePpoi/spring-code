package com.AOP.proxy.onepackage;

import com.AOP.annotation.Aspect;
import com.AOP.bean.Advice;
import com.AOP.bean.Pointcut;

public class Advisor {

    private Advice advice;
    private Pointcut pointcut;
    private Aspect aspect;

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public Pointcut getPointcut() {
        return pointcut;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }

    public Aspect getAspect() {
        return aspect;
    }

    public void setAspect(Aspect aspect) {
        this.aspect = aspect;
    }
    

    

}