package tdd.test;

import tdd.test.annotation.After;
import tdd.test.annotation.Before;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestCase {



    public TestResult result;

    public String methodName;

    public TestCase(String methodName) {
        this.methodName = methodName;
    }

    public void runBefore() {
        getAnnotationMethod(Before.class).forEach(method -> {
            try {
                method.invoke(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public void runAfter() {
        getAnnotationMethod(After.class).forEach(method -> {
            try {
                method.invoke(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    List<Method> getAnnotationMethod(Class clazz) {
        List<Method> methodList = new ArrayList<>();
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method: methods) {
            if (method.isAnnotationPresent(clazz)) {
                methodList.add(method);
            }
        }
        return methodList;
    }

    //通过test注解来添加方法
    //获取test注解的方法
    public TestResult run(TestResult result) throws InvocationTargetException, IllegalAccessException {

        result.testStarted();
        try {
            Method[] methods = this.getClass().getDeclaredMethods();
            for (Method method: methods) {
                if (method.getName().equals(methodName)) {
                    runBefore();
                    method.invoke(this);
                    runAfter();
                }
            }
        } catch (Exception e) {
            result.testFailed();
        }
        return result;
    }
}
