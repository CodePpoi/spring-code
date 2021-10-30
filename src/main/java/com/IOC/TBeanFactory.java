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

        //����Bean�еķ���
        User user = userController.getUserById(1L);
        System.out.println(user);
    }



    // inject ��������ע��AutoInject ����ֵ
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
        //�ҵ�����������
        Set<String> classNames = ClassUtils.getClassName(basePackage, true);
        for (String className : classNames) {
            try {
                //������
                Class clasz = Class.forName(className);

                //�ж������Ƿ����MyBeanע��
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
