/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.common.enums;

/**
 * @author chenpi
 * @since 2018/8/1 23:27
 */
public enum ResponseEnum {
    SUCCESS("0000", "成功"),
    SYS_EXCEPTION("0001", "系统异常"),
    PARAM_ERROR("0002", "参数错误");
    private String code;
    private String description;

    ResponseEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
