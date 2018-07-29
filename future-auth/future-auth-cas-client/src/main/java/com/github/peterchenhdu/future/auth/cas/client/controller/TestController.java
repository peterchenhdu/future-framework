/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenpi
 * @since 2018/7/29 23:13
 */
@RestController
public class TestController {

    @GetMapping("/home")
    @ResponseBody
    public String getJob() {

        return "welcome to future cas client.";
    }
}
