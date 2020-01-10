package com.qianfeng.smsplatform.webmaster.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: Administrator
 * Thanks for Everything
 */
public class DateUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String dateToString(Date date) {
        String format = sdf.format(date);
        return format;
    }

    public static Date stringToDate(String date) throws ParseException {
        Date parse = sdf.parse(date);
        return parse;
    }

    public static Long dateToLong(Date date) {
        long time = date.getTime();
        return time;
    }

    public static Date longToDate(Long date) {
        Date date1 = new Date(date);
        return date1;
    }

    public static String longToStr(Long date) {
        Date date1 = new Date(date);
        String format = sdf.format(date1);
        return format;
    }

    public static Long strToLong(String date) throws ParseException {
        Date parse = sdf.parse(date);
        long time = parse.getTime();
        return time;
    }

    public static void main(String[] args) {

    }

}
