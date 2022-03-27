package com.AOP.helper;

import com.AOP.bean.Advisor;
import com.AOP.bean.AfterAdvice;
import com.AOP.bean.BeforeAdvice;
import com.AOP.interceptor.MyMethodInterceptor;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AdvisorHelper {

    public static MyMethodInterceptor[] getMethodInterceptors(List<Advisor> advisors, Method method) {
        List<MyMethodInterceptor> list = new ArrayList<>();
        for (Advisor advisor : advisors) {
            if(isMatch(advisor, method)) {
                if(advisor.getAdvice() instanceof BeforeAdvice) {
                    list.add((BeforeAdvice) advisor.getAdvice());
                }

                if(advisor.getAdvice() instanceof AfterAdvice) {
                    list.add((AfterAdvice) advisor.getAdvice());
                }
            }
        }
        return list.toArray(new MyMethodInterceptor[list.size()]);
    }

    public static boolean isMatch(Advisor advisor, Method method) {
        return true;
        //return advisor.getPointcut().buildPointcutExpression().matchesMethodExecution(method).alwaysMatches();
    }

    /**
     * 从所有的Advisor中获取匹配的
     * @param advisors
     * @return
     */
    public static List<Advisor> getMatchedAdvisors(Class cls, List<Advisor> advisors) {
        List<Advisor> aList = new ArrayList<>();
        for (Method method : cls.getDeclaredMethods()) {
            for (Advisor advisor : advisors) {
                ShadowMatch match = advisor.getPointcut()
                                    .buildPointcutExpression()
                                    .matchesMethodExecution(method);
                if(match.alwaysMatches()) {
                    aList.add(advisor);
                }
            }
        }
        return aList;
    }
    
}