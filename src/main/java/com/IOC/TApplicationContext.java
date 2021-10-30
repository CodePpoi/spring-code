package com.IOC;

import java.util.List;

public class TApplicationContext extends TBeanFactory {

    public void refresh() {
//        BeanA beanA = (BeanA)
        List<Object> objects = (List<Object>)findClassWithBeanAnnotation("C:\\SpringCode\\src\\main\\java\\com\\IOC\\beans");
//        beanA.print();
    }

    public static void main(String[] args) {
        TApplicationContext applicationContext = new TApplicationContext();
        applicationContext.refresh();
    }

}
