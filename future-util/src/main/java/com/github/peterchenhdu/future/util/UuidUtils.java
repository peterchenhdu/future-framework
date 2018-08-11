/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.util;

import java.util.UUID;

/**
 * @author PiChen
 * @since 2018/8/10 22:51
 */
public class UuidUtils {
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
