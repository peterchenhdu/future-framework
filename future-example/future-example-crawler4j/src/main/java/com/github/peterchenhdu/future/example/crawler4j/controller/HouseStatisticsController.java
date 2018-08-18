/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.controller;

import com.github.peterchenhdu.future.common.dto.ApiResponse;
import com.github.peterchenhdu.future.common.enums.ResponseEnum;
import com.github.peterchenhdu.future.example.crawler4j.dto.DayStatisticsDto;
import com.github.peterchenhdu.future.example.crawler4j.dto.HourStatisticsDto;
import com.github.peterchenhdu.future.example.crawler4j.entity.DayStatistics;
import com.github.peterchenhdu.future.example.crawler4j.entity.HourStatistics;
import com.github.peterchenhdu.future.example.crawler4j.service.DayStatisticsService;
import com.github.peterchenhdu.future.example.crawler4j.service.HourStatisticsService;
import com.github.peterchenhdu.future.util.DateTimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @author chenpi
 * @since 2018/7/28 15:42
 */
@Api(value = "房产统计API", tags = {"房产统计API"})
@RestController
@RequestMapping("/house")
public class HouseStatisticsController {

    @Autowired
    private HourStatisticsService hourStatisticsService;
    @Autowired
    private DayStatisticsService dayStatisticsService;

    @ApiOperation(value = "获取所有", tags = {"获取所有"}, notes = "注意问题点")
    @GetMapping("/hourStatistics")
    public ApiResponse<List<HourStatistics>> getAll() {
        return new ApiResponse<>(ResponseEnum.SUCCESS, hourStatisticsService.findAll());
    }


    @PostMapping("/getLast24Hour")
    public ApiResponse<List<HourStatisticsDto>> getLast24Hour() {
        List<HourStatisticsDto> rstList = new ArrayList<>();
        List<HourStatistics> list = hourStatisticsService.getLast24Hour();
        list.forEach(s->{
            HourStatisticsDto dto = new HourStatisticsDto();
            BeanUtils.copyProperties(s, dto);
            dto.setHour(DateTimeUtils.getHour(s.getCreateDate()) + "点");
            rstList.add(dto);
        });
        return new ApiResponse<>(ResponseEnum.SUCCESS, rstList);
    }

    @PostMapping("/getLast30Day")
    public ApiResponse<List<DayStatisticsDto>> getLast30Day() {
        List<DayStatisticsDto> rstList = new ArrayList<>();
        List<DayStatistics> list = dayStatisticsService.getLast30Day();
        list.forEach(s->{
            DayStatisticsDto dto = new DayStatisticsDto();
            BeanUtils.copyProperties(s, dto);
            dto.setHour(DateTimeUtils.getDay(s.getCreateDate()) + "日");
            rstList.add(dto);
        });
        return new ApiResponse<>(ResponseEnum.SUCCESS, rstList);
    }

}
