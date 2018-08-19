/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.dto;

import com.github.peterchenhdu.future.util.DateTimeUtils;

/**
 * @author PiChen
 * @since 2018/8/18 23:04
 */
public class DayPriceDto implements Comparable {
    private String day;
    private int price;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int compareTo(Object o) {
        DayPriceDto target = (DayPriceDto) o;
        return DateTimeUtils.toDate("2000-" + this.getDay()).compareTo(DateTimeUtils.toDate("2000-" + target.getDay()));
    }
}
