/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Application
 *
 * @author chenpi
 * @since 2018/8/3 22:57
 */
@SpringBootApplication
@MapperScan(basePackages = "com.github.peterchenhdu.future.tool.mb.crawler4j.crawler4j.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
