/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.quartz.controller;

import com.github.peterchenhdu.future.example.quartz.dto.ResultEntity;
import com.github.peterchenhdu.future.example.quartz.exception.ServiceException;
import com.github.peterchenhdu.future.example.quartz.model.jobdemo.ScheduleJob;
import com.github.peterchenhdu.future.example.quartz.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenpi
 * @since 2018/7/28 15:42
 */
@Api(value = "任务API", tags = {"任务操作接口"})
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @ApiOperation(value = "获取所有任务", tags = {"获取所有任务"}, notes = "注意问题点")
    @GetMapping
    public List<ScheduleJob> getAllJob() {
        return jobService.getAllJob();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Object getJob(@PathVariable("id") Long jobId) throws ServiceException {
        Map<String, Object> map = new HashMap<>();
        ScheduleJob scheduleJob = jobService.findById(jobId);
        map.put("data", scheduleJob);
        if (scheduleJob != null) {
            return ResultEntity.success("查询成功!", map);
        }
        return ResultEntity.fail(500, "查询失败!");
    }

    @PostMapping("/add")
    @ResponseBody
    public Object saveJob(@RequestBody ScheduleJob newScheduleJob) throws ServiceException {
        boolean isSave = jobService.add(newScheduleJob);
        if (isSave) {
            return ResultEntity.success("新增任务成功!");
        }
        return ResultEntity.fail(500, "新增任务失败!");
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public Object updateJob(@PathVariable("id") Long jobId, String cronExpression) throws ServiceException {
        ScheduleJob scheduleJob = jobService.findById(jobId);
        if (scheduleJob != null) {
            scheduleJob.setCronExpression(cronExpression);
        }
        ScheduleJob update = jobService.update(jobId, scheduleJob);
        if (update != null) {
            return ResultEntity.success("任务更新成功!");
        }
        return ResultEntity.fail(500, "任务更新失败!");
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Object deleteJob(@PathVariable("id") Long jobId) throws ServiceException {
        boolean isDelete = jobService.delete(jobId);
        if (isDelete) {
            return ResultEntity.success("任务移除成功!");
        }
        return ResultEntity.fail(500, "任务移除失败!");
    }

    @GetMapping("/trigger/{id}")
    @ResponseBody
    public Object runJob(@PathVariable("id") Long jobId) throws ServiceException {
        boolean isRun = jobService.run(jobId);
        if (isRun) {
            return ResultEntity.success("任务触发成功!");
        }
        return ResultEntity.fail(500, "任务触发异常，请联系管理员!");
    }


    @GetMapping("/pause/{id}")
    @ResponseBody
    public Object pauseJob(@PathVariable("id") Long jobId) throws ServiceException {
        boolean isPause = jobService.pause(jobId);
        if (isPause) {
            return ResultEntity.success("任务暂停成功!");
        }
        return ResultEntity.fail(500, "任务暂停异常，请联系管理员!");
    }

    @GetMapping("/resume/{id}")
    @ResponseBody
    public Object resumeJob(@PathVariable("id") Long jobId) throws ServiceException {
        boolean isResume = jobService.resume(jobId);
        if (isResume) {
            return ResultEntity.success("任务恢复成功!");
        }
        return ResultEntity.fail(500, "任务恢复异常，请联系管理员!");
    }
}
