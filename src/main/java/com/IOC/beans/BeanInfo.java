package com.IOC.beans;

public class BeanInfo {

    /**
     * Bean�������
     */
    private Class clasz;

    /**
     * ������ioc�����е�Bean����
     */
    private String beanName;

    /**
     * ������ioc�����е�Bean����
     */
    private Class beanType;

    /**
     * ������ioc�����е�bean����ʵ��
     */
    private Object bean;

    /**
     * ������ioc�����е�bean�Ĵ������ʵ��
     */
    private Object proxyBean;

    public Class getClasz() {
        return clasz;
    }

    public void setClasz(Class clasz) {
        this.clasz = clasz;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Class getBeanType() {
        return beanType;
    }

    public void setBeanType(Class beanType) {
        this.beanType = beanType;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Object getProxyBean() {
        return proxyBean;
    }

    public void setProxyBean(Object proxyBean) {
        this.proxyBean = proxyBean;
    }
}
