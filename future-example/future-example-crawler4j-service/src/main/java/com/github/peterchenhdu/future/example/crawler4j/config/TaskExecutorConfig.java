/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chenpi
 * @since 2018/8/18 15:42
 */
@Configuration
public class TaskExecutorConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(6);
        taskExecutor.setMaxPoolSize(50);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setRejectedExecutionHandler((Runnable r, ThreadPoolExecutor executor) -> {
                    if (!executor.isShutdown()) {
                        try {
                            executor.getQueue().put(r);
                        } catch (InterruptedException e) {
                            logger.error(e.toString(), e);
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        taskExecutor.initialize();
        return taskExecutor;
    }


}