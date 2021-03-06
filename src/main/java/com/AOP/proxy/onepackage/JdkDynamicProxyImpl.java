package com.AOP.proxy.onepackage;


import com.AOP.bean.Advisor;
import com.AOP.helper.AdvisorHelper;
import com.AOP.helper.ReflectHelper;
import com.AOP.interceptor.MethodInterceptorChain;
import com.AOP.interceptor.MyMethodInterceptor;
import com.AOP.proxy.AOPProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * JDK Dynamic Proxy Implementions
 */
public class JdkDynamicProxyImpl extends AOPProxy implements InvocationHandler {

    public JdkDynamicProxyImpl(List<Advisor> advisors, Object bean) {
        super(advisors, bean);
    }

    @Override
    protected Object getProxyObject() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), ReflectHelper.getInterfaces(this.getTarget().getClass()), this);
    }

    /**
     * 实现InvocationHandler的接口方法,将具体的调用委托给拦截器链MethodInterceptorChain
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MyMethodInterceptor[] iterceptors =
            AdvisorHelper.getMethodInterceptors(this.getAdvisors(), method);
        
        Object obj = new MethodInterceptorChain(iterceptors)
                        .intercept(method,args,proxy);
        return obj;
    }
    
     /**
     * 简单实现
     */
    // @Override
    // public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //     MyMethodInterceptor[] iterceptors =
    //         AdvisorHelper.getMethodInterceptors(this.getAdvisors(), method);
    //     Object obj = new MethodInterceptorChain(iterceptors)
    //                     .intercept(method,args,proxy);


    //     BeforeAdvice beforeAdvice = getBeforeAdvice(method);
    //     beforeAdvice.before(proxy, method, args);

    //     return obj;
    // }

    // /**
    //  * 以返回BeforeAdvice为例
    //  * @return
    //  */
    // private BeforeAdvice getBeforeAdvice(Method method) {
    //     for (Advisor advisor : this.getAdvisors()) {
    //         if(AdvisorHelper.isMatch(advisor, method) && advisor.getAdvice() instanceof BeforeAdvice) {
    //             return (BeforeAdvice) advisor.getAdvice();
    //         }
    //     }
    //     return null;
    // }

}