package com.AOP;


import com.AOP.bean.Advisor;
import com.AOP.config.AnnotationParser;
import com.AOP.config.ConfigParser;
import com.AOP.helper.AdvisorHelper;
import com.AOP.proxy.AOPProxyFactory;

import java.util.List;

public class AnnotationAOPProxyCreator extends AbstractAOPProxyCreator {

    private ConfigParser configParser = new AnnotationParser();
    private AOPProxyFactory aopProxyFactory;

    
    @Override
    public List<Advisor> buildAdvisors() {
        return configParser.parse();
    }

    @Override
    public Object createProxy(List<Advisor> advisors, Object bean) {
        return aopProxyFactory.getProxyObject(advisors, bean);
    }

    @Override
    public List<Advisor> getMatchedAdvisors() {
        return AdvisorHelper.getMatchedAdvisors(this.getClass(),this.buildAdvisors());
    }

    
}