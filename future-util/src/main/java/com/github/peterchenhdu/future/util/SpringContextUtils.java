/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author chenpi
 * @since 2018/8/07 22:06
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext context = null;

    private SpringContextUtils() {
        super();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * getApplicationContext
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 根据名称获取bean
     *
     * @param beanName beanName
     * @return Object
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * 根据bean名称获取指定类型bean
     *
     * @param beanName bean名称
     * @param clazz    返回的bean类型,若类型不匹配,将抛出异常
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return context.getBean(beanName, clazz);
    }

    /**
     * 根据类型获取bean
     *
     * @param clazz clazz
     * @return T
     */
    public static <T> T getBean(Class<T> clazz) {
        T t = null;
        Map<String, T> map = context.getBeansOfType(clazz);
        for (Map.Entry<String, T> entry : map.entrySet()) {
            t = entry.getValue();
        }
        return t;
    }

    /**
     * 是否包含bean
     *
     * @param beanName beanName
     * @return boolean
     */
    public static boolean containsBean(String beanName) {
        return context.containsBean(beanName);
    }

    /**
     * 是否是单例
     *
     * @param beanName beanName
     * @return boolean
     */
    public static boolean isSingleton(String beanName) {
        return context.isSingleton(beanName);
    }

    /**
     * bean的类型
     *
     * @param beanName beanName
     * @return Class
     */
    public static Class getType(String beanName) {
        return context.getType(beanName);
    }

}