/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.tool.crawler4j.service.hourse;

import com.github.peterchenhdu.future.tool.crawler4j.dao.HourStatisticsMapper;
import com.github.peterchenhdu.future.tool.crawler4j.model.HourStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author PiChen
 * @since 2018/8/13 23:31
 */
@Service
public class HourStatisticsService {

    @Autowired
    private HourStatisticsMapper hourStatisticsMapper;

    public int save(HourStatistics hourStatistics) {
        return hourStatisticsMapper.insert(hourStatistics);
    }
}
