/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.controller;

import com.github.peterchenhdu.future.common.dto.ApiResponse;
import com.github.peterchenhdu.future.common.enums.ResponseEnum;
import com.github.peterchenhdu.future.example.crawler4j.dto.DayPriceDto;
import com.github.peterchenhdu.future.example.crawler4j.service.HousePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author PiChen
 * @since 2018/8/18 22:53
 */
@RestController
@RequestMapping("/house/price")
public class HousePriceController {
    @Autowired
    private HousePriceService housePriceService;

    @PostMapping("/getLast30DayPrice")
    public ApiResponse<List<DayPriceDto>> getLast30Day() {
        List<DayPriceDto> list = housePriceService.getLast30DayPriceDto();
        Collections.sort(list);
        return new ApiResponse<>(ResponseEnum.SUCCESS, list);
    }
}
