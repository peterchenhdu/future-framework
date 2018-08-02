/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.config.webexception;

import com.github.peterchenhdu.future.common.dto.ApiResponse;
import com.github.peterchenhdu.future.common.enums.ResponseEnum;
import com.github.peterchenhdu.future.common.exception.FutureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author chenpi
 * @since 2018/8/2 23:40
 */
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    /**
     * 业务异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FutureException.class)
    public ApiResponse handleHttpMediaTypeNotSupportedException(FutureException e) {
        logger.error(e.toString(), e);
        return new ApiResponse(e.getCodeEnum());
    }

    /**
     * 系统异常
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(Exception e) {
        logger.error(e.toString(), e);
        return new ApiResponse(ResponseEnum.SYS_EXCEPTION);
    }
}