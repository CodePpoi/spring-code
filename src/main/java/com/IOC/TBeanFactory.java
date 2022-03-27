package com.IOC;

import com.AOP2.annoation.MyAspect;
import com.AOP2.annoation.MyPointcut;
import com.IOC.aop.Advisor;
import com.IOC.aop.MyAdvisedSupport;
import com.IOC.aop.ObjectCglibAopProxy;
import com.IOC.beans.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TBeanFactory {

    private Map<Class, Object> typeBeanMap = new HashMap<>();

    private String basePackage = "com.IOC.beans";

    private final Map<Method, List<Advisor>> advisorsCache = new ConcurrentHashMap<>();

    private volatile List<String> aspectBeanNames;

    //private final Map<String, Boolean> advisedBeans = new ConcurrentHashMap<>(256);



    public <T> T getBean(Class clazz) {
        return (T) typeBeanMap.get(clazz);
    }

    protected Object wrap(Object obj, String name) {
        //if (targetSourcedBeans.contains(name)) {
        //    return obj;
        //}
        //if (Boolean.FALSE.equals(this.advisedBeans.get(name))){
        //    return obj;
        //}
        Object[] specificIntef = getAdvicesAndAdvisorsForBean(obj.getClass(), name);
        if (specificIntef != null && specificIntef.length > 0) {
            for (Map.Entry<Method, List<Advisor>> methodListEntry : this.advisorsCache.entrySet()) {
                List<Advisor> value = methodListEntry.getValue();
                for (Advisor advisor : value) {
                    if (advisor.getAspect() == null){
                        Object bean = getBean(advisor.getClazz());
                        advisor.setAspect(bean);
                    }
                }
            }
            MyAdvisedSupport myAdvisedSupport = new MyAdvisedSupport(obj.getClass() , obj , this.advisorsCache);
            return createAopProxy(myAdvisedSupport);
        }
        //this.advisedBeans.put(name, Boolean.FALSE);
        return obj;
    }

    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> aClass, String name) {
        List<Advisor> advisors = findEligibleAdvisors(aClass, name);
        if (advisors.isEmpty()) {
            return null;
        }
        return advisors.toArray();
    }

    private List<Advisor> findEligibleAdvisors(Class<?> aClass, String name) {
        List<Advisor> advisors = findCandidateAdvisors();
        List<Advisor> advisorsThatCanApply = findAdvisorsThatCanApply(advisors, aClass);
        return advisorsThatCanApply;
    }

    protected  List<Advisor> findAdvisorsThatCanApply(List<Advisor> advisors , Class<?> clazz){
        if (advisors.isEmpty()){
            return advisors;
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        List<Advisor> list = new ArrayList<>();
        for (Method declaredMethod : declaredMethods) {
//            OperLog annotation = declaredMethod.getAnnotation(OperLog.class);
            Annotation[] annotations = declaredMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().getSimpleName().equals(advisors.get(0).getExpression())) {
                    list.addAll(advisors);
                    advisorsCache.put(declaredMethod, advisors);
                }
            }
        }
        return list;
    }

    protected List<Advisor> findCandidateAdvisors() {
        List<Advisor> advisors = new ArrayList<>();
        advisors.addAll(this.buildAspectJAdvisors());
        return advisors;
    }

    private boolean isAspect(Class<?> clazz) {
        if (clazz == null){
            return Boolean.FALSE;
        }
        MyAspect annoation = clazz.getDeclaredAnnotation(MyAspect.class);
        if (annoation != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private List<Method> getAdvisorMethods(Class<?> clazz, String beanName) {
//        List<Method> advisorsMethod = getAdvisorsMethod(clazz);
//        final List<Method> methods = new ArrayList<>();
        Method[] methodss = clazz.getDeclaredMethods();
//        for (Method method : methodss) {
//            if (null == method.getAnnotation(MyPointcut.class)) {
//                methods.add(method);
//            }
//        }
        return Arrays.asList(methodss);
    }

    private List<Method> getAdvisorsMethod(Class<?> clazz) {
        final List<Method> methods = new ArrayList<>();
        Method[] methodss = clazz.getDeclaredMethods();
        for (Method method : methodss) {
            if (null == method.getAnnotation(MyPointcut.class)) {
                methods.add(method);
            }
        }
        return methods;
    }

    private List<Advisor> getAdvisors(Class<?> clazz, String beanName ) {
        List<Advisor> advisors = new ArrayList<>();
        String pointcut = null;
        for (Method advisorMethod : getAdvisorMethods(clazz, beanName)) {
            String point = getPointcut(advisorMethod);
            if (point != null){
                pointcut = point;
            }

        }
        for (Method advisorMethod : getAdvisorsMethod(clazz)) {

            Advisor advisor = getAdvisor(advisorMethod, beanName, clazz , pointcut);
            if (advisor != null) {
                advisors.add(advisor);
            }
        }
//        this.advisorsCache.put(beanName, advisors);
        return advisors;
    }

    protected Advisor getAdvisor(Method method, String beanName, Class<?> clazz ,String pointcut) {
        return  new Advisor(method , beanName , clazz , pointcut);
    }

    private String getPointcut(Method advisorMethod) {
        if (null != advisorMethod.getAnnotation(MyPointcut.class)) {
            MyPointcut annotation = advisorMethod.getAnnotation(MyPointcut.class);
            String value = annotation.value();
            int start = value.indexOf("(");
            int end = value.indexOf(")");
            return value.substring(start+1, end);
        }
        return null;
    }

    private List<Advisor> buildAspectJAdvisors() {
        List<String> aspectNames = this.aspectBeanNames;
        // 双重检验锁 保证线程安全
        if (aspectNames == null) {
            synchronized (this) {
                aspectNames = this.aspectBeanNames;

                if (aspectNames == null) {

                    List<Advisor> advisors = new ArrayList<>();
                    aspectNames = new ArrayList<>();
                    for(Object bean: typeBeanMap.values()) {
                        Class clazz = bean.getClass();
                        String beanName = clazz.getName();
                        if (isAspect(clazz)) {
                            aspectNames.add(beanName);
                            List<Advisor> advisorsList = getAdvisors(clazz, beanName );
                            advisors.addAll(advisorsList);
                        }
                    }
                    this.aspectBeanNames = aspectNames;
                    return advisors;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TBeanFactory beanFactory = new TBeanFactory();
        beanFactory.scanPackageAndLoadBeans();
        beanFactory.injectBeans(beanFactory.typeBeanMap);
        UserController userController = beanFactory.getBean(UserController.class);

        //调用Bean中的方法
        User user = userController.getUserById(1L);
        System.out.println(user);
    }



    // inject 用来给标注了AutoInject 设置值
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

    private Class getBeanType(Class clasz, boolean canJdkProxyBean) {
        Class beanType = null;
        if (canJdkProxyBean) {
            //可以使用jdk动态代理，则bean类型取bean的接口类型
            beanType = clasz.getInterfaces()[0];
        } else {
            //不可以使用jdk动态代理，bean类型就取当前类类型
            beanType = clasz;
        }
        return beanType;
    }

    private void scanPackageAndLoadBeans() {
        //找到包下所有类
        Set<String> classNames = ClassUtils.getClassName(basePackage, true);
        for (String className : classNames) {
            try {
                //查找类
                Class clasz = Class.forName(className);



                //判断类上是否存在MyBean注解
                if (clasz.isAnnotationPresent(MyBean.class)) {

                    Class[] interfaces = clasz.getInterfaces();
                    boolean canJdkProxyBean = interfaces != null && interfaces.length > 0;
                    Object bean = clasz.newInstance();
                    Object iocBean = bean;
                    Class beanType = getBeanType(clasz, canJdkProxyBean);
                    //if(canJdkProxyBean) {
                    //    Object proxyBean = createJdkProxy(bean);
                    //    iocBean = proxyBean;
                    //} else{

                    //}
                    typeBeanMap.put(beanType, iocBean);



                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        for (String className : classNames) {
            try {
                //查找类
                Class clasz = Class.forName(className);
                Object bean = typeBeanMap.get(clasz);

                //判断类上是否存在MyBean注解
                if (clasz.isAnnotationPresent(MyBean.class)) {

                    Object proxyBean = wrap(bean, bean.getClass().getName());
                    //createCglibProxy(bean.getClass(), bean);

                    typeBeanMap.put(clasz, proxyBean);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private Object createJdkProxy(Object bean) {
        InvocationHandler invocationHandler = new BeanProxy(bean);
        Object proxyBean = Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(), invocationHandler);
        return proxyBean;
    }

    protected Object createCglibProxy(Class<?> aClass, Object obj) {
        MyAdvisedSupport support =new MyAdvisedSupport(aClass , obj , this.advisorsCache);
        return getProxy(support);
    }

    public Object getProxy(MyAdvisedSupport config) {
        return createAopProxy(config);
    }

    private Object createAopProxy(MyAdvisedSupport config) {
        if (config.getTargetClass() == null){
            throw new IllegalArgumentException("targetClass not null !");
        }

        return new ObjectCglibAopProxy(config).getProxy();
    }



}
