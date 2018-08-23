/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.runner;


import com.github.peterchenhdu.future.component.quartz.util.ScheduleJobDto;
import com.github.peterchenhdu.future.component.quartz.util.ScheduleUtil;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author chenpi
 * @since 2018/7/28 15:42
 */
@Component
public class QuartzRunner implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(QuartzRunner.class);
    @Autowired
    private Scheduler scheduler;


    @Override
    public void run(String... args) throws Exception {
        //每小时爬取挂牌 签约数据
        ScheduleJobDto scheduleJob = new ScheduleJobDto();
        scheduleJob.setPause(Boolean.FALSE);
        scheduleJob.setJobName("HouseSignListingJobJob");
        scheduleJob.setJobGroup("HouseSignListingJobJobGroup");
        scheduleJob.setTriggerName("HouseSignListingJobTrigger");
        scheduleJob.setTriggerGroup("HouseSignListingJobTriggerGroup");
        scheduleJob.setDescription("This is a test job");
        scheduleJob.setClassName("com.github.peterchenhdu.future.example.crawler4j.job.HouseSignListingJob");
//        //每5秒执行一次
////        scheduleJob.setCronExpression("0/5 * * * * ?");
//
        scheduleJob.setCronExpression("0 0 0/1 * * ?");
        try {
            ScheduleUtil.createScheduleJob(scheduler, scheduleJob);
            logger.info("初始化测试任务完成");
        } catch (Exception e) {
            logger.error("初始化测试任务异常!MSG:{}", e.getMessage());
        }



        //每天1点爬取二手房简要信息任务
        ScheduleJobDto scheduleJob2 = new ScheduleJobDto();
        scheduleJob2.setPause(Boolean.FALSE);
        scheduleJob2.setJobName("HouseSimpleJob");
        scheduleJob2.setJobGroup("HouseSimpleJob");
        scheduleJob2.setTriggerName("HouseSimpleJobTrigger");
        scheduleJob2.setTriggerGroup("HouseSimpleJobTriggerGroup");
        scheduleJob2.setDescription("This is a test job");
        scheduleJob2.setClassName("com.github.peterchenhdu.future.example.crawler4j.job.HouseSimpleJob");
        scheduleJob2.setCronExpression("0 */30 * * * ?");
        ScheduleUtil.createScheduleJob(scheduler, scheduleJob2);

        //每天1点爬取二手房简要信息任务
        ScheduleJobDto scheduleJob3 = new ScheduleJobDto();
        scheduleJob3.setPause(Boolean.FALSE);
        scheduleJob3.setJobName("HouseDetailCrawlerJob");
        scheduleJob3.setJobGroup("HouseDetailCrawlerJob");
        scheduleJob3.setTriggerName("HouseDetailCrawlerJobTrigger");
        scheduleJob3.setTriggerGroup("HouseDetailCrawlerJobTriggerGroup");
        scheduleJob3.setDescription("This is a test job");
        scheduleJob3.setClassName("com.github.peterchenhdu.future.example.crawler4j.job.HouseDetailCrawlerJob");
        scheduleJob3.setCronExpression("0 */30 * * * ?");
        ScheduleUtil.createScheduleJob(scheduler, scheduleJob3);
    }
}
