/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.common.exception;

import com.github.peterchenhdu.future.common.enums.ResponseEnum;

/**
 * @author chenpi
 * @since 2018/08/01 23:01
 */
public class FutureException extends RuntimeException {

    private ResponseEnum codeEnum;


    public FutureException() {
        super();
    }

    /**
     * @param codeEnum
     */
    public FutureException(ResponseEnum codeEnum) {
        super(codeEnum.getDescription());
        this.codeEnum = codeEnum;
    }

    public ResponseEnum getCodeEnum() {
        return codeEnum;
    }

    public void setCodeEnum(ResponseEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

}