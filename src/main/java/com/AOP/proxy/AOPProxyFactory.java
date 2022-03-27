package com.AOP.proxy;


import com.AOP.bean.Advisor;

import java.util.List;

public class AOPProxyFactory {

    public Object getProxyObject(List<Advisor> advisors, Object bean) {
        if(isInterface()) {
           return new CglibProxyImpl(advisors,bean).getProxyObject();
        } else {
            return new JdkDynamicProxyImpl(advisors,bean).getProxyObject();
        }
    }

    private boolean isInterface() {
        return false;
    }
    
}