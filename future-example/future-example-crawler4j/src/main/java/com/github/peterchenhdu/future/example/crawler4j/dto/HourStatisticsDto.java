/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.dto;

import com.github.peterchenhdu.future.example.crawler4j.entity.HourStatistics;

/**
 * @author PiChen
 * @since 2018/8/14 23:36
 */
public class HourStatisticsDto extends HourStatistics {
    private String hour;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
