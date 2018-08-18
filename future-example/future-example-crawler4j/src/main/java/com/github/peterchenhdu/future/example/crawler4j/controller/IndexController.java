/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
class IndexController {
    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        System.out.println(request.getRequestURL().toString());
        return "index.html";
    }

    @RequestMapping("/love")
    public String love() {
        return "love.html";
    }
}