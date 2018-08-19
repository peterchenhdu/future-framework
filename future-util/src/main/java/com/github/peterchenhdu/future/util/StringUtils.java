/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author PiChen
 * @since 2018/8/6 23:14
 */
public class StringUtils {

    /**
     * clearBlank
     *
     * @param str str
     * @return String
     */
    public static String clearBlank(String str) {
        String dest = "";
        if (ObjectUtils.isNotEmpty(str)) {
            Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
            Matcher matcher = pattern.matcher(str);
            dest = matcher.replaceAll("");
        }
        return dest;
    }

    public static boolean isBlank(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isBlank(cs);
    }

    public static String limitLength(String target, int maxLength) {
        return target.length() > maxLength ? target.substring(0, maxLength) : target;
    }

    public static String getFrontString(String target, String split) {
        if (!target.contains(split)) {
            return target;
        }
        return target.substring(0, target.indexOf(split));
    }

    public static void main(String[] args) {
        System.out.println(getFrontString("40820元/㎡", "元/㎡"));
    }
}
