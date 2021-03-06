/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.service;


import com.github.peterchenhdu.future.common.enums.CalendarFieldEnum;
import com.github.peterchenhdu.future.example.crawler4j.entity.HourStatistics;
import com.github.peterchenhdu.future.example.crawler4j.mapper.HourStatisticsMapper;
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
 * @since 2018/8/13 23:31
 */
@CacheConfig(cacheNames = {"lemonCache"})
@Service
public class HourStatisticsService {

    @Autowired
    private HourStatisticsMapper hourStatisticsMapper;

    public int save(HourStatistics hourStatistics) {
        return hourStatisticsMapper.insert(hourStatistics);
    }

    public List<HourStatistics> findAll() {
        return hourStatisticsMapper.selectAll();
    }

    @Cacheable(key="#root.targetClass + #root.methodName")
    public List<HourStatistics> getLast24Hour() {
        Date now = new Date();
        Example example = new Example(HourStatistics.class);
        example.setOrderByClause("create_date ASC");
        example.createCriteria()
                .andGreaterThan("createDate", DateTimeUtils.toDateTime(DateTimeUtils.add(now, CalendarFieldEnum.HOUR,
                        -26)))
                .andLessThanOrEqualTo("createDate",now);
        return hourStatisticsMapper.selectByExample(example);
    }
}
