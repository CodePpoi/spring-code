package com.AOP.proxy.onepackage;

import com.AOP.proxy.BizAImpl;
import com.AOP.proxy.IBizA;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {
    /**
     * 创建target的代理对象
     * @param target
     * @return
     */
    public static Object createProxy(final Object target) {
        return Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), target.getClass().getInterfaces(),new MyHandler(target));
    }


    //调用时获得的是代理对象
    //x执行实际方法d
    //h执行代理对象的方法 before + 1 d
    //把代理对象改为JDK代理 d
    //调用测试通过 d
    //把before和after移动到beforeAdvice和After d
    //调用BeforeAdvice和AfterAdvice d
    //调用BeforeAdvice和AfterAdvice interceptor d
    //用Chain调用 intercept d
    //用代理调用chain
    public static void main(final String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        final BizAImpl bizAImpl = new BizAImpl();
        final IBizA newBizA = (IBizA) createProxy(bizAImpl);
        newBizA.doSomething();
    }
    
}

/**
 * 在代理的接口调用时的处理器类
 */
class MyHandler implements InvocationHandler {
    private final Object target;
    public MyHandler(final Object target) {
        this.target = target;
    }


    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        //before((BizAImpl ) this.target);
        final Object o = method.invoke(this.target, args);
        return o;
    }
}