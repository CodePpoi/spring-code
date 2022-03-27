package com.IOC.beans;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BeanProxy implements InvocationHandler {

    /**
     * �������bean����
     */
    private Object bean;

    public BeanProxy(Object bean) {
        this.bean = bean;
    }

    /**
     * ����Ŀ��bean����ط���
     *
     * @param proxy  �������
     * @param method ����
     * @param args   ����
     * @return ��������ֵ
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before call method: " + method.getName());
        Object result = method.invoke(bean, args);

        System.out.println("after call method: " + method.getName());
        return result;
    }
}