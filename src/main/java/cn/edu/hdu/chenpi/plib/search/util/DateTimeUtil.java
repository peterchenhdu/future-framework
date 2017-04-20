/*
 * File Name: DateTimeUtil.java
 * Description: 
 * Author: PiChen
 * Create Date: 2017年4月16日

 */
package net.jforum.search.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author PiChen
 * @version forum4j V1.0.0, 2017年4月16日 下午4:29:25
 * @see       
 * @since forum4j V1.0.0
 */

public class DateTimeUtil
{

    public static String formatDateTime(Date date)
    {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }
}
