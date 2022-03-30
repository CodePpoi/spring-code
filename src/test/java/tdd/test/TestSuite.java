package tdd.test;

import tdd.test.annotation.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestSuite {
    List<TestCase> tests = new ArrayList<>();

    public void run(TestResult result) throws InvocationTargetException, IllegalAccessException {
        for (TestCase test : tests) {
            test.run(result);
        }
    }

    public void addTests(Class<? extends TestCase> testClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Method[] methods = testClass.getDeclaredMethods();
        for(Method method: methods) {
            for(Annotation annotation: method.getDeclaredAnnotations()) {
                if( Test.class.equals(annotation.annotationType())) {
                    Constructor constructor = testClass.getConstructors()[0];
                    TestCase test = (TestCase) constructor.newInstance(method.getName());
                    add(test);
                }
            }
        }
    }

    public void add(TestCase test) {
        tests.add(test);
    }

}
