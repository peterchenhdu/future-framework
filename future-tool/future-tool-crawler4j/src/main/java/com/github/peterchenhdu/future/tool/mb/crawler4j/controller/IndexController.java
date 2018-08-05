/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.tool.mb.crawler4j.controller;

import com.github.peterchenhdu.future.tool.mb.crawler4j.service.news.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Pi Chen
 * @version infosys V1.0.0, 2016年12月13日
 * @see
 * @since infosys V1.0.0
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private INewsService newsService;

    /**
     * 总记录数
     *
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<Map<String, Object>> getYearCount() {
        Map<String, Object> data = new HashMap<String, Object>();
        Calendar calendar = new GregorianCalendar();

        long totalCount = 0;
        for (int i = 2000; i <= calendar.get(Calendar.YEAR); i++) {
            totalCount += newsService.getYearCount(i);
        }
        data.put("totalCount", totalCount);
        return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
    }

}
