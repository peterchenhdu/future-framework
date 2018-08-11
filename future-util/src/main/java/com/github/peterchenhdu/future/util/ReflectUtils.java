
/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.util;


import com.github.peterchenhdu.future.common.enums.ResponseEnum;
import com.github.peterchenhdu.future.common.exception.FutureException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 反射工具类.
 *
 * @author chenpi
 * @since 2018/03/24
 */
public class ReflectUtils {

    /**
     * 根据方法名执行方法
     *
     * @param obj            obj
     * @param methodName     methodName
     * @param parameterTypes parameterTypes
     * @param args           args
     * @return Object
     */
    public static Object executeByMethodName(Object obj, String methodName, Class<?>[]
            parameterTypes, Object[] args) {
        try {
            Method func = obj.getClass().getMethod(methodName, parameterTypes);
            return func.invoke(obj, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new FutureException(ResponseEnum.SYS_EXCEPTION, "executeByMethodName exception");
        }
    }


    private ReflectUtils() {
        throw new IllegalStateException("Utility class");
    }
}
