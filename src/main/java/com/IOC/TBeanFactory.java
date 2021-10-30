package com.IOC;

import com.IOC.beans.*;

import java.lang.reflect.Field;
import java.util.*;

public class TBeanFactory {
    List<BeanInfo> beans = new ArrayList<BeanInfo>();

    private Map<Class, Object> typeBeanMap = new HashMap<>();

    private String basePackage = "com.IOC.beans";

    public <T> T getBean(Class clazz) {
        return (T) typeBeanMap.get(clazz);
    }

    public static void main(String[] args) {
        TBeanFactory beanFactory = new TBeanFactory();
        beanFactory.scanPackageAndLoadBeans();
        beanFactory.injectBeans(beanFactory.beans);
        UserController userController = beanFactory.getBean(UserController.class);

        //调用Bean中的方法
        User user = userController.getUserById(1L);
        System.out.println(user);
    }



    // inject 用来给标注了AutoInject 设置值
    private void injectBeans(List<BeanInfo> beans) {
        for(BeanInfo beanInfo : beans) {
            Object bean = beanInfo.getBean();
            Class clasz = bean.getClass();
            Field[] fields = clasz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(AutoInject.class)) {
                    field.setAccessible(true);
                    Class beanType = field.getType();

                    Object proxyBean = typeBeanMap.get(beanType);
                    try {
                        field.set(bean, proxyBean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private List<BeanInfo> scanPackageAndLoadBeans() {
        //找到包下所有类
        Set<String> classNames = ClassUtils.getClassName(basePackage, true);
        for (String className : classNames) {
            try {
                //查找类
                Class clasz = Class.forName(className);

                //判断类上是否存在MyBean注解
                if (clasz.isAnnotationPresent(MyBean.class)) {
                    BeanInfo beanInfo = new BeanInfo();
                    beanInfo.setBean(clasz.newInstance());
                    beanInfo.setBeanType(clasz);
                    beans.add(beanInfo);
                    typeBeanMap.put(clasz, beanInfo.getBean());
                }
            } catch (Exception exception) {

            }
        }
        return beans;
    }



}
