/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.service;

import com.github.peterchenhdu.future.common.enums.CalendarFieldEnum;
import com.github.peterchenhdu.future.example.crawler4j.entity.DayStatistics;
import com.github.peterchenhdu.future.example.crawler4j.entity.HourStatistics;
import com.github.peterchenhdu.future.example.crawler4j.mapper.DayStatisticsMapper;
import com.github.peterchenhdu.future.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author PiChen
 * @since 2018/8/16 22:51
 */
@CacheConfig(cacheNames = {"lemonCache"})
@Service
public class DayStatisticsService {
    @Autowired
    private DayStatisticsMapper dayStatisticsMapper;

    public int save(DayStatistics dayStatistics) {
        return dayStatisticsMapper.insert(dayStatistics);
    }

    public List<DayStatistics> findAll() {
        return dayStatisticsMapper.selectAll();
    }

    @Cacheable(key="#root.targetClass + #root.methodName")
    public List<DayStatistics> getLast30Day() {
        Date now = new Date();
        Example example = new Example(HourStatistics.class);
        example.setOrderByClause("create_date ASC");
        example.createCriteria()
                .andGreaterThan("createDate", DateTimeUtils.toDateTime(DateTimeUtils.add(now, CalendarFieldEnum.DATE, -30)))
                .andLessThanOrEqualTo("createDate",now);
        return dayStatisticsMapper.selectByExample(example);
    }
}
