/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


/**
 * @version $Revision$ $Date$
 * @since 3.3.6
 */
@Aspect
public class LogAspect {

    @Around("(execution (public * com.github.peterchenhdu.future.auth.cas..*.*(..))) && !(execution( * com.github.peterchenhdu.future.auth.cas..*.set*(..)))")
    public Object traceMethod(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnVal = null;
        final Logger log = getLog(proceedingJoinPoint);
        final String methodName = proceedingJoinPoint.getSignature().getName();

        try {
            if (log.isTraceEnabled()) {
                final Object[] args = proceedingJoinPoint.getArgs();
                final String arguments;
                if (args == null || args.length == 0) {
                    arguments = "";
                } else {
                    arguments = Arrays.deepToString(args);
                }
                log.trace("Entering method [" + methodName + " with arguments [" + arguments + "]");
            }
            returnVal = proceedingJoinPoint.proceed();
            return returnVal;
        } finally {
            if (log.isTraceEnabled()) {
                log.trace("Leaving method [" + methodName + "] with return value [" + (returnVal != null ? returnVal.toString() : "null") + "].");
            }
        }
    }

    protected Logger getLog(final JoinPoint joinPoint) {
        final Object target = joinPoint.getTarget();

        if (target != null) {
            return LoggerFactory.getLogger(target.getClass());
        }

        return LoggerFactory.getLogger(getClass());
    }

}
