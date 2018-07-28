package com.github.peterchenhdu.future.demo.dao.jobdemo;

import com.github.peterchenhdu.future.demo.model.jobdemo.ScheduleJob;
import com.github.peterchenhdu.future.demo.model.jobdemo.ScheduleJobExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ScheduleJobMapper {
    long countByExample(ScheduleJobExample example);

    int deleteByExample(ScheduleJobExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ScheduleJob record);

    int insertSelective(ScheduleJob record);

    List<ScheduleJob> selectByExample(ScheduleJobExample example);

    ScheduleJob selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ScheduleJob record, @Param("example") ScheduleJobExample example);

    int updateByExample(@Param("record") ScheduleJob record, @Param("example") ScheduleJobExample example);

    int updateByPrimaryKeySelective(ScheduleJob record);

    int updateByPrimaryKey(ScheduleJob record);
}