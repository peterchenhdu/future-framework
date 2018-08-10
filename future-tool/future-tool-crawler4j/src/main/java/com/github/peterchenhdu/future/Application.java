/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * Application
 *
 * @author chenpi
 * @since 2018/8/3 22:57
 */
@SpringBootApplication
@MapperScan(basePackages = "com.github.peterchenhdu.future.tool.crawler4j.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
