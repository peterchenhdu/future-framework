/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.component.quartz.config;

import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenpi
 * @since 2018/7/28 16:01
 */
@Configuration
public class ScheduleConfig {
    @Bean
    public SchedulerFactoryBeanCustomizer customizer() {
        return (schedulerFactoryBean) -> {
            schedulerFactoryBean.setStartupDelay(10);
            schedulerFactoryBean.setOverwriteExistingJobs(true);
            schedulerFactoryBean.setSchedulerName("TASK_EXECUTOR");
        };
    }
}
