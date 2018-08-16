/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class IndexController {
    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/love")
    public String love() {
        return "love.html";
    }
}