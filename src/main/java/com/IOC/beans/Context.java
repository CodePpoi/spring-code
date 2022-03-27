package com.IOC.beans;

import java.util.HashMap;
import java.util.Map;

public class Context {

    /**
     * ����Bean���ʹ洢Bean��Map����
     */
    private Map<Class, Object> typeBeanMap = new HashMap<>();


    public Object getBean(Class clasz) {
        return typeBeanMap.get(clasz);
    }

    public void putBean(Class beanType, Object bean) {
        typeBeanMap.put(beanType, bean);
    }
}
