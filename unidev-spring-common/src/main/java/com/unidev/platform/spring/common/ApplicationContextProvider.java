package com.unidev.platform.spring.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * Static application context provider
 * <bean class="com.unidev.platform.spring.common.ApplicationContextProvider" factory-method="getInstance" />
 * @author denis
 */
public class ApplicationContextProvider implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private static ApplicationContextProvider applicationContextProvider;
    private ApplicationContextProvider() {
    }
    public static ApplicationContextProvider getInstance() {
        if (applicationContextProvider == null) {
            applicationContextProvider = new ApplicationContextProvider();
        }
        return applicationContextProvider;
    }
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public <T>  T getBean(String beanId, Class<T> type) {
        return applicationContext.getBean(beanId, type);
    }
    public <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }
    public Object getBean(String name) {
        return applicationContext.getBean(name);
    }
    public Object getBean(String name, Object... args) {
        return applicationContext.getBean(name, args);
    }
}