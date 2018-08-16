/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.job;

import com.github.peterchenhdu.future.example.crawler4j.crawler.HourStatisticsCrawlerService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author chenpi
 * @since 2018/7/28 16:01
 */
@DisallowConcurrentExecution
public class TestJob implements Job, Serializable {

    @Autowired
    private HourStatisticsCrawlerService hourStatisticsCrawlerService;

    private final static Logger logger = LoggerFactory.getLogger(TestJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("开始运行任务 - hourStatisticsCrawlerService...");
        try {
            hourStatisticsCrawlerService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("hourStatisticsCrawlerService - 任务运行结束!");
    }
}
