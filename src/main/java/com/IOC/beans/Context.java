package com.IOC.beans;

import java.util.HashMap;
import java.util.Map;

public class Context {

    /**
     * 根据Bean类型存储Bean的Map对象
     */
    private Map<Class, Object> typeBeanMap = new HashMap<>();


    public Object getBean(Class clasz) {
        return typeBeanMap.get(clasz);
    }

    public void putBean(Class beanType, Object bean) {
        typeBeanMap.put(beanType, bean);
    }
}
