/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.util;

import com.github.peterchenhdu.future.common.enums.CalendarFieldEnum;
import com.github.peterchenhdu.future.common.enums.ResponseEnum;
import com.github.peterchenhdu.future.common.exception.FutureException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author PiChen
 * @since 2018/8/6 23:19
 */
public class DateTimeUtils {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public String toString(Date dateTime) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        return formatter.format(dateTime);
    }

    public Date toDate(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date resultDate;
        try {
            resultDate = sdf.parse(dateTime);
        } catch (ParseException e) {
            throw new FutureException(ResponseEnum.SYS_EXCEPTION, "DateTime ParseException");

        }
        return resultDate;
    }

    /**
     * @param comparedDate
     * @param field
     * @param amount
     * @return
     */
    public static String add(String comparedDate, CalendarFieldEnum field, int amount) {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        String dateTime;
        try {
            dateTime = add(sdf.parse(comparedDate), field, amount);
        } catch (ParseException e) {
            throw new FutureException(ResponseEnum.SYS_EXCEPTION, "DateTime ParseException");

        }
        return dateTime;
    }

    /**
     * @param comparedDate
     * @param field
     * @param amount
     * @return
     */
    public static String add(Date comparedDate, CalendarFieldEnum field, int amount) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(comparedDate);
        if (field == CalendarFieldEnum.YEAR) {
            calendar.add(Calendar.YEAR, amount);
        } else if (field == CalendarFieldEnum.MONTH) {
            calendar.add(Calendar.MONTH, amount);
        } else if (field == CalendarFieldEnum.DATE) {
            calendar.add(Calendar.DATE, amount);
        } else if (field == CalendarFieldEnum.HOUR) {
            calendar.add(Calendar.HOUR, amount);
        } else if (field == CalendarFieldEnum.MINUTE) {
            calendar.add(Calendar.MINUTE, amount);
        } else if (field == CalendarFieldEnum.SECOND) {
            calendar.add(Calendar.SECOND, amount);
        } else {
            throw new FutureException(ResponseEnum.PARAM_ERROR, "invalid param field");
        }
        Date resultDate = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        return formatter.format(resultDate);
    }

    public static void main(String[] args) {
        System.out.println(add(new Date(), CalendarFieldEnum.MONTH, 10));
    }
}
