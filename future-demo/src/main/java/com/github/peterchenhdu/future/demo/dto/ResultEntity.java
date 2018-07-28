/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.demo.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenpi
 * @since 2018/7/28 15:42
 */
public class ResultEntity implements Serializable {


    private static final long serialVersionUID = -2431333863007594548L;

    /**
     * 返回失败的结果
     *
     * @param errorCode errorCode
     * @param msg       msg
     * @return Map
     */
    public static Map<String, Object> fail(int errorCode, String msg) {
        return returnResult(false, msg, errorCode, null);
    }

    /**
     * 返回失败的结果
     *
     * @param errorCode errorCode
     * @param msg       msg
     * @return Map
     */
    public static Map<String, Object> fail(int errorCode, String msg, Map<String, Object> map) {
        return returnResult(false, msg, errorCode, map);
    }

    /**
     * 返回成功的结果
     *
     * @param msg msg
     * @return Map
     */
    public static Map<String, Object> success(String msg) {
        return returnResult(true, msg, null, null);
    }


    /**
     * 返回成功的结果
     *
     * @param msg msg
     * @param map map
     * @return Map
     */
    public static Map<String, Object> success(String msg, Map<String, Object> map) {
        return returnResult(true, msg, null, map);
    }


    private static Map<String, Object> returnResult(boolean success, String msg, Integer errorCode, Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);
        if (map != null) {
            result.putAll(map);
        }
        if (errorCode != null) {
            result.put("errorCode", errorCode);
        }
        return result;
    }
}
