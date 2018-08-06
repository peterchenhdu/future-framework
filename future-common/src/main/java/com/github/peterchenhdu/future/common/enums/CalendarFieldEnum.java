/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.common.enums;

import java.util.Calendar;

/**
 * @author PiChen
 * @since 2018/8/6 23:42
 */
public enum CalendarFieldEnum {
    YEAR(Calendar.YEAR, "年"),
    MONTH(Calendar.MONTH, "月"),
    DATE(Calendar.DATE, "日"),
    HOUR(Calendar.HOUR, "时"),
    MINUTE(Calendar.MINUTE, "分"),
    SECOND(Calendar.SECOND, "秒");

    private int code;
    private String description;

    CalendarFieldEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
