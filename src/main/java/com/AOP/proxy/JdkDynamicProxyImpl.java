package com.AOP.proxy;


import com.AOP.AnnotationAOPProxyCreator;
import com.AOP.annotation.Aspect;
import com.AOP.bean.Advisor;
import com.AOP.bean.AfterAdvice;
import com.AOP.bean.BeforeAdvice;
import com.AOP.helper.AdvisorHelper;
import com.AOP.helper.ReflectHelper;
import com.AOP.interceptor.MethodInterceptorChain;
import com.AOP.interceptor.MyMethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * JDK Dynamic Proxy Implementions
 */
public class JdkDynamicProxyImpl extends AOPProxy implements InvocationHandler {


    public static void main(String[] args) throws Throwable {

        AnnotationAOPProxyCreator  a = new AnnotationAOPProxyCreator();
        final BizAImpl bizAImpl = new BizAImpl();
        List<Advisor> advisors = a.buildAdvisors();
                //buildAdvisors(BizAImpl.class.getDeclaredMethod("doSomething"));
        JdkDynamicProxyImpl jdkDynamicProxy = new JdkDynamicProxyImpl(advisors, bizAImpl);
        jdkDynamicProxy.invoke(bizAImpl, BizAImpl.class.getDeclaredMethod("doSomething"), null);

        //final IBizA newBizA = (IBizA) createProxy(bizAImpl);
        //newBizA.doSomething();
    }

    //调用时获得的是代理对象
    //先执行实际方法d
    //後执行代理对象的方法 before + 1 d
    //把代理对象改为JDK代理 d
    //调用测试通过 d
    //把before和after移动到beforeAdvice和After d
    //调用BeforeAdvice和AfterAdvice d
    //调用BeforeAdvice和AfterAdvice interceptor d
    //用Chain调用 interceptor d
    //用JDK代理调用chain d
    //使用注解的方式添加advisior buildAdvisor
    //添加Before和After注解

    //before advice和 before联系起来
    //advisior和before advice联系起来 add
    //advice和intecep 联系起来 d
    static List<Advisor> buildAdvisors(Method method) throws NoSuchMethodException {
        List<Advisor> advisors = new ArrayList<>();
        Advisor advisor = new Advisor();
        advisors.add(advisor);
        advisor.setAdvice(new BeforeAdvice(method, BizAImpl.class.getAnnotation(Aspect.class)));

        Advisor advisor0 = new Advisor();
        advisors.add(advisor0);
        advisor0.setAdvice(new AfterAdvice(method, BizAImpl.class.getAnnotation(Aspect.class)));
        return advisors;
    }

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