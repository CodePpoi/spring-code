package com.AOP.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.AOP.annotation.Aspect;
import com.AOP.bean.*;
import com.AOP.proxy.BizAImpl;
import com.AOP.proxy.Logging;
import com.google.common.collect.Lists;

public class AnnotationParser implements ConfigParser {

    private final Map<String, List<Advisor>> cache = new ConcurrentHashMap<>();

    @Override
    public List<Advisor> parse() {
        //if(cache != null) {
        //    return getAdvisorsFromCache();
        //}

        List<Class> allClasses = getAllAspectClasses();
        for (Class class1 : allClasses) {
            cache.putIfAbsent(class1.getName(), getAdvisorsByAspect(class1));
        }

        return cache.get(allClasses.get(0).getName());
    }

    /**
     * 根据Aspect类生成Advisor类
     * @param class1
     * @return
     */
    private List<Advisor> getAdvisorsByAspect(Class class1) {
        List<Advisor> advisors = new ArrayList<>();
        for (Method method : getAdvisorMethods(class1)) {
            Advisor advisor = null;
            try {
                advisor = getAdvisor(method, class1.getAnnotation(Aspect.class));
            } catch (Exception e) {
                //e.printStackTrace();
            }
            if (advisor != null) {
                advisors.add(advisor);
            }
        }
        return advisors;
    }

    private List<Advisor> getAdvisorsFromCache() {
        return new ArrayList<>();
    }

    private List<Class> getAllAspectClasses() {
        List<Class> clazz = new ArrayList<>();
        clazz.add(Logging.class);
        return clazz;
    }

    private List<Method> getAdvisorMethods(Class class1)  {
        List<Method> methods = Lists.newArrayList(class1.getDeclaredMethods());
        //try {
        //    methods.add(BizAImpl.class.getDeclaredMethod("doSomething"));
        //} catch (NoSuchMethodException e) {
        //    e.printStackTrace();
        //}
        return methods;
    }

    private Advisor getAdvisor(Method method, Object aspect) {
        Advisor advisor = null;
        Advice advice = getAdvice(method, aspect);
        if(advice == null) return advisor;
        advisor = new Advisor();
        advisor.setAspect((Aspect)aspect);
        advisor.setPointcut(getPointcut(method));
        advisor.setAdvice(advice);
        return advisor;
    }

    private Advice getAdvice(Method method, Object aspect) {
        if(isBefore(method)) {
            return new BeforeAdvice(method, (Aspect) aspect);
        }

        if(isAfter(method)) {
            return new AfterAdvice(method, (Aspect) aspect);
        }

        return null;
    }

    /**
     * 根据方法找到Annotation,进一步定位到Advice的类型
     * @param method
     * @return
     */
    private boolean isBefore(Method method) {
        for(Annotation annotation :method.getDeclaredAnnotations()) {
            if(annotation.annotationType().getName().contains("MyBefore")) {
                return true;
            }
        }
        return false;
    }

    private boolean isAfter(Method method) {
        for(Annotation annotation :method.getDeclaredAnnotations()) {
            if(annotation.annotationType().getName().contains("MyAfter")) {
                return true;
            }
        }
        return false;
    }

    private Pointcut getPointcut(Method method) {
        return null;
    }
    
}