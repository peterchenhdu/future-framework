/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.common.dto;

import com.github.peterchenhdu.future.common.enums.ResponseEnum;

/**
 * @author chenpi
 * @since 2018/8/2 23:40
 */
public class ApiResponse<T> {
    private String code;
    private String msg;
    private T data;

    public ApiResponse() {

    }

    public ApiResponse(ResponseEnum errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getDescription();
    }

    public ApiResponse(ResponseEnum errorCode, T data) {
        this(errorCode);
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
