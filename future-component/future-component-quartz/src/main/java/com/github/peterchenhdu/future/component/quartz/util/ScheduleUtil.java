/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.component.quartz.util;


import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenpi
 * @since 2018/7/28 16:01
 */
public class ScheduleUtil {
    private static Logger logger = LoggerFactory.getLogger(ScheduleUtil.class);

    /**
     * 创建任务
     *
     * @param scheduler
     * @param scheduleJob
     * @throws RuntimeException
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJobDto scheduleJob) throws RuntimeException {
        //校验Cron表达式
        validateCronExpression(scheduleJob);
        try {
            // 要执行的 Job 的类
            Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(scheduleJob.getClassName()).newInstance().getClass();
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup())
                    .withDescription(scheduleJob.getDescription())
                    .build();
            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    //——不触发立即执行
                    //——等待下次Cron触发频率到达时刻开始按照Cron频率依次执行
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(scheduleJob.getTriggerName(), scheduleJob.getTriggerGroup())
                    .withDescription(scheduleJob.getDescription())
                    .withSchedule(cronScheduleBuilder)
                    .startNow()
                    .build();
            if (scheduleJob.getPause()) {
                //暂停任务
                pauseJob(scheduler, scheduleJob);
            }
            //交由Scheduler安排触发
            scheduler.scheduleJob(jobDetail, cronTrigger);
            logger.info("Create schedule job {}-{} success", scheduleJob.getJobGroup(), scheduleJob.getJobName());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Execute schedule job failed");
            throw new RuntimeException("Execute schedule job failed", e);
        }
    }


    /**
     * 更新任务
     *
     * @param scheduler
     * @param scheduleJob
     * @throws RuntimeException
     */
    public static void updateScheduleJob(Scheduler scheduler, ScheduleJobDto scheduleJob) throws RuntimeException {
        validateCronExpression(scheduleJob);
        try {
            //获取key
            TriggerKey triggerKey = getTriggerKey(scheduleJob);
            // 表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = getCronTrigger(scheduler, scheduleJob);
            // 按新的cronExpression表达式重新构建trigger
            cronTrigger = cronTrigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withSchedule(cronScheduleBuilder)
                    .withDescription(scheduleJob.getDescription())
                    .build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, cronTrigger);
            logger.info("Update schedule job {}-{} success", scheduleJob.getJobGroup(), scheduleJob.getJobName());
            if (scheduleJob.getPause()) {
                pauseJob(scheduler, scheduleJob);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("Update schedule job failed");
            throw new RuntimeException("Update schedule job failed", e);
        }
    }

    /**
     * 执行任务
     *
     * @param scheduler
     * @param scheduleJob
     * @throws RuntimeException
     */
    public static void run(Scheduler scheduler, ScheduleJobDto scheduleJob) throws RuntimeException {
        try {
            scheduler.triggerJob(getJobKey(scheduleJob));
            logger.info("Run schedule job {}-{} success", scheduleJob.getJobGroup(), scheduleJob.getJobName());
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("Run schedule job failed");
            throw new RuntimeException("Run schedule job failed", e);
        }
    }

    /**
     * 暂停任务
     *
     * @param scheduler
     * @param scheduleJob
     */
    public static void pauseJob(Scheduler scheduler, ScheduleJobDto scheduleJob) throws RuntimeException {
        try {
            scheduler.pauseJob(getJobKey(scheduleJob));
            logger.info("Pause schedule job {}-{} success", scheduleJob.getJobGroup(), scheduleJob.getJobName());
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("Pause schedule job failed");
            throw new RuntimeException("Pause job failed", e);
        }
    }

    /**
     * 继续执行任务
     *
     * @param scheduler
     * @param scheduleJob
     * @throws RuntimeException
     */
    public static void resumeJob(Scheduler scheduler, ScheduleJobDto scheduleJob) throws RuntimeException {
        try {
            scheduler.resumeJob(getJobKey(scheduleJob));
            logger.info("Resume schedule job {}-{} success", scheduleJob.getJobGroup(), scheduleJob.getJobName());
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("Resume schedule job failed");
            throw new RuntimeException("Resume job failed", e);
        }
    }

    /**
     * 删除任务
     *
     * @param scheduler
     * @param scheduleJob
     * @throws RuntimeException
     */
    public static void deleteJob(Scheduler scheduler, ScheduleJobDto scheduleJob) throws RuntimeException {
        try {
            TriggerKey triggerKey = getTriggerKey(scheduleJob);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(getJobKey(scheduleJob));
            logger.info("Delete schedule job {}-{} success", scheduleJob.getJobGroup(), scheduleJob.getJobName());
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("Delete schedule job failed");
            throw new RuntimeException("Delete job failed", e);
        }
    }

    /**
     * 校验Cron表达式
     */
    public static void validateCronExpression(ScheduleJobDto scheduleJob) throws RuntimeException {
        if (!CronExpression.isValidExpression(scheduleJob.getCronExpression())) {
            throw new RuntimeException(String.format("Job %s expression %s is not correct!", scheduleJob.getClassName(), scheduleJob.getCronExpression()));
        }
    }

    /**
     * 获取 Trigger Key
     *
     * @param scheduleJob
     * @return
     */
    public static TriggerKey getTriggerKey(ScheduleJobDto scheduleJob) {
        return TriggerKey.triggerKey(scheduleJob.getTriggerName(), scheduleJob.getTriggerGroup());
    }

    /**
     * 获取 Job Key
     *
     * @param scheduleJob
     * @return
     */
    public static JobKey getJobKey(ScheduleJobDto scheduleJob) {
        return JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

    /**
     * 获取 Cron Trigger
     *
     * @param scheduler
     * @param scheduleJob
     * @return
     * @throws RuntimeException
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, ScheduleJobDto scheduleJob) throws RuntimeException {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(scheduleJob));
        } catch (SchedulerException e) {
            throw new RuntimeException("Get Cron trigger failed", e);
        }
    }
}
