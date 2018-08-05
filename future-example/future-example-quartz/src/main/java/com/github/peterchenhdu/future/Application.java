/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application放到future包的目的是能够扫描到future包下的配置类
 * 如com.github.peterchenhdu.future.config.webswagger.Swagger2的配置
 * 启动类如果在com.github.peterchenhdu.future.example.quartz是扫描不到的
 *
 * @author chenpi
 * @since 2018/7/28 15:42
 */
@SpringBootApplication
@MapperScan(basePackages = "com.github.peterchenhdu.future.example.quartz.dao.jobdemo")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
