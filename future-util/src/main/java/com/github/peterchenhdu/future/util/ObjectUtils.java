/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @author chenpi
 * @since 2018/7/30 23:06
 */
public class ObjectUtils {
    private ObjectUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * if null , size = 0 or length = 0
     *
     * @param obj obj
     * @return boolean
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        // else
        return false;
    }

    /**
     * if not null , size != 0 or length != 0
     *
     * @param obj obj
     * @return boolean
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
