/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenpi
 * @since 2018/7/28 15:42
 */
@SpringBootApplication
public class CasServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasServerApplication.class, args);
    }
}
