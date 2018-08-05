/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.tool.mb.crawler4j.common.base;


import com.github.peterchenhdu.future.tool.mb.crawler4j.common.log.Logger;
import com.google.gson.Gson;

/**
 * @author Pi Chen
 * @version infosys V1.0.0, 2016年5月23日
 * @see
 * @since infosys V1.0.0
 */

public class BaseController {
    public static final String FAILD = "faild";
    public static final String SUCCESS = "success";
    protected static final String MAX_LONG_AS_STRING = "9223372036854775807";
    /**
     * gson是线程安全的
     */
    protected static final Gson gson = new Gson();
    /**
     * 日志
     */
    protected Logger logger = Logger.getLogger(this.getClass());

}
