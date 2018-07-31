/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.quartz.service;

import com.github.peterchenhdu.future.component.quartz.util.ScheduleJobDto;
import com.github.peterchenhdu.future.component.quartz.util.ScheduleUtil;
import com.github.peterchenhdu.future.example.quartz.dao.jobdemo.ScheduleJobMapper;
import com.github.peterchenhdu.future.example.quartz.exception.ServiceException;

import com.github.peterchenhdu.future.example.quartz.model.jobdemo.ScheduleJob;
import com.github.peterchenhdu.future.example.quartz.model.jobdemo.ScheduleJobExample;
import com.github.peterchenhdu.future.example.quartz.util.IdGenerateFactory;
import org.quartz.Scheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

/**
 * @author chenpi
 * @since 2018/7/28 15:42
 */
@Service
public class JobService {
    private static Logger logger = LoggerFactory.getLogger(JobService.class);

    @Autowired
    private ScheduleJobMapper ScheduleJobMapper;

    @Autowired
    private Scheduler scheduler;

    /**
     * 查找所有任务
     *
     * @return List
     */
    public List<ScheduleJob> getAllJob() {
        ScheduleJobExample example = new ScheduleJobExample();
        example.createCriteria();
        return ScheduleJobMapper.selectByExample(example);
    }

    /**
     * 查找任务
     *
     * @param jobId jobId
     * @return ScheduleJob
     * @throws ServiceException ServiceException
     */
    public ScheduleJob findById(Long jobId) throws ServiceException {
        return ScheduleJobMapper.selectByPrimaryKey(jobId);
    }

    /**
     * 更新任务
     *
     * @param jobId       jobId
     * @param scheduleJob scheduleJob
     * @return ScheduleJob
     * @throws ServiceException ServiceException
     */
    @Transactional(rollbackFor = DataAccessException.class)
    public ScheduleJob update(Long jobId, ScheduleJob scheduleJob) throws ServiceException {
        scheduleJob.setLastUpdateTime(new Date());
        ScheduleJobMapper.updateByPrimaryKey(scheduleJob);

        ScheduleUtil.updateScheduleJob(scheduler, getScheduleJobDto(scheduleJob));
        return scheduleJob;
    }

    /**
     * 新增任务
     *
     * @param scheduleJob scheduleJob
     * @return boolean
     * @throws ServiceException ServiceException
     */
    @Transactional(rollbackFor = DataAccessException.class)
    public boolean add(ScheduleJob scheduleJob) throws ServiceException {
        Long id = IdGenerateFactory.nextId();
        scheduleJob.setId(id);
        //设置创建时间
        scheduleJob.setCreateTime(new Date());
        //设置最后更新时间
        scheduleJob.setLastUpdateTime(new Date());
        //若pause没有值，则默认是启动中
        if (scheduleJob.getPause() == null) {
            scheduleJob.setPause(false);
        }
        ScheduleJobMapper.insert(scheduleJob);
        ScheduleUtil.createScheduleJob(scheduler, getScheduleJobDto(scheduleJob));
        return true;
    }

    /**
     * 删除任务
     *
     * @param jobId jobId
     * @return boolean
     * @throws ServiceException ServiceException
     */
    @Transactional(rollbackFor = DataAccessException.class)
    public boolean delete(Long jobId) throws ServiceException {
        ScheduleJob scheduleJob = findById(jobId);
        if (jobId == null) {
            logger.error("Delete product: {} failed", jobId);
            throw new ServiceException("Delete product:" + jobId + "failed");
        }
        ScheduleJobMapper.deleteByPrimaryKey(jobId);
        ScheduleUtil.deleteJob(scheduler, getScheduleJobDto(scheduleJob));
        return true;
    }

    /**
     * 恢复任务
     *
     * @param jobId jobId
     * @return boolean
     * @throws ServiceException ServiceException
     */
    public boolean resume(Long jobId) throws ServiceException {
        ScheduleJob scheduleJob = findById(jobId);
        scheduleJob.setPause(false);
        scheduleJob.setLastUpdateTime(new Date());
        //更新数据库记录
        ScheduleJobMapper.updateByPrimaryKey(scheduleJob);
        ScheduleUtil.resumeJob(scheduler, getScheduleJobDto(scheduleJob));
        return true;
    }

    /**
     * 暂停任务
     *
     * @param jobId jobId
     * @return boolean
     * @throws ServiceException ServiceException
     */
    @Transactional(rollbackFor = DataAccessException.class)
    public boolean pause(Long jobId) throws ServiceException {
        ScheduleJob scheduleJob = findById(jobId);
        scheduleJob.setPause(true);
        scheduleJob.setLastUpdateTime(new Date());
        //更新数据库记录
        ScheduleJobMapper.updateByPrimaryKey(scheduleJob);

        //暂停任务
        ScheduleUtil.pauseJob(scheduler, getScheduleJobDto(scheduleJob));
        return true;
    }

    /**
     * 运行任务
     *
     * @param jobId jobId
     * @return boolean
     * @throws ServiceException ServiceException
     */
    public boolean run(Long jobId) throws ServiceException {
        ScheduleJob scheduleJob = findById(jobId);
        ScheduleUtil.run(scheduler, getScheduleJobDto(scheduleJob));
        return true;
    }

    private ScheduleJobDto getScheduleJobDto(ScheduleJob scheduleJob) {
        ScheduleJobDto dto = new ScheduleJobDto();
        BeanUtils.copyProperties(scheduleJob, dto);
        return dto;
    }
}
