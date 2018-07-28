/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware{
    private static ApplicationContext context = null;

    public static ApplicationContext getApplicationContext() {
        return context;
    }
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }
}