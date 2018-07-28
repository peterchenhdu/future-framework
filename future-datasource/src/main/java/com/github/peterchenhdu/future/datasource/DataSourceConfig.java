/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.datasource;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author chenpi
 * @since 2018/7/27 21:42
 */
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource druidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
