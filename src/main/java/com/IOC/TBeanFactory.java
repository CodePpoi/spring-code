package com.IOC;

import com.IOC.beans.*;

import java.lang.reflect.Field;
import java.util.*;

public class TBeanFactory {

    private Map<Class, Object> typeBeanMap = new HashMap<>();

    private String basePackage = "com.IOC.beans";

    public <T> T getBean(Class clazz) {
        return (T) typeBeanMap.get(clazz);
    }

    public static void main(String[] args) {
        TBeanFactory beanFactory = new TBeanFactory();
        beanFactory.scanPackageAndLoadBeans();
        beanFactory.injectBeans(beanFactory.typeBeanMap);
        UserController userController = beanFactory.getBean(UserController.class);

        //����Bean�еķ���
        User user = userController.getUserById(1L);
        System.out.println(user);
    }



    // inject ��������ע��AutoInject ����ֵ
    private void injectBeans(Map<Class, Object> typeBeanMap) {
        for(Object bean : typeBeanMap.values()) {
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

    private void scanPackageAndLoadBeans() {
        //�ҵ�����������
        Set<String> classNames = ClassUtils.getClassName(basePackage, true);
        for (String className : classNames) {
            try {
                //������
                Class clasz = Class.forName(className);

                //�ж������Ƿ����MyBeanע��
                if (clasz.isAnnotationPresent(MyBean.class)) {
                    typeBeanMap.put(clasz, clasz.newInstance());
                }
            } catch (Exception exception) {

            }
        }
    }



}
