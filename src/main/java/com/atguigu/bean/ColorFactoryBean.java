package com.atguigu.bean;

import org.springframework.beans.factory.FactoryBean;

public class ColorFactoryBean implements FactoryBean<Color> {

    //返回一个Color对象，这个对象会添加到容器中
    @Override
    public Color getObject() throws Exception {
        System.out.println("color factory");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return  Color.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
