/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.quartz.exception;

/**
 * @author chenpi
 * @since 2018/7/28 10:56
 */
public class ServiceException extends RuntimeException {

    private Throwable throwable;

    public ServiceException(String msg, Throwable throwable) {
        super(msg);
        this.throwable = throwable;
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
