/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author PiChen
 * @since 2018/8/8 23:26
 */
public class RestTemplateUtilsTest {
    @Test
    public void getRequest() throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("partnerId", "njs");
        param.put("goodsId", "AG");
        param.put("v", System.currentTimeMillis());
        String result = RestTemplateUtils.getRequest
                ("http://fa.163.com/interfaces/ngxcache/priceinfo/min/getDay5PriceList.do" +
                                "?partnerId={partnerId}&goodsId={goodsId}&v={v}",
                        param);
        System.out.println(result);
    }

}