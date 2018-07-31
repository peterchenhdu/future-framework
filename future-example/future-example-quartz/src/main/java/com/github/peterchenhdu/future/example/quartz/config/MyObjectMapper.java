/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.quartz.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author chenpi
 * @since 2018/7/28 15:42
 */
public class MyObjectMapper extends ObjectMapper {

    public MyObjectMapper() {
        SimpleModule simpleModule = new SimpleModule();
        //Long型传到前端会丢失精度
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        registerModule(simpleModule);
    }

}