package com.github.peterchenhdu.future.example.crawler4j.config;/*
 * Copyright (c) 2011-2025 PiChen
 */


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenpi
 * @since 2018/7/28 15:42
 */
@Configuration
public class JacksonConfig extends ObjectMapper {

    @Bean
    public ObjectMapper objectMapper() {
        return new MyObjectMapper();
    }
}