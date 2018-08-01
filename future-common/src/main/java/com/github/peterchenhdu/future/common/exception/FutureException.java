/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.common.exception;

/**
 * @author chenpi
 * @since 2018/08/01 23:01
 */
public class FutureException extends RuntimeException {

    private ErrorCodeEnum codeEnum;

    private String msg;

    public FutureException() {
        super();
    }

    /**
     * @param codeEnum
     * @param msg
     */
    public FutureException(ErrorCodeEnum codeEnum, String msg) {
        super(codeEnum.getCode() + ":" + msg);
        this.codeEnum = codeEnum;
        this.msg = msg;
    }

    public ErrorCodeEnum getCodeEnum() {
        return codeEnum;
    }

    public void setCodeEnum(ErrorCodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}