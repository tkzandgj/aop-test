package com.cncnc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    public static SimpleDateFormat getSimpleDateFormat(){
        SimpleDateFormat sdf = threadLocal.get();
        if (sdf == null){
            sdf = new SimpleDateFormat(DATE_FORMAT);
            threadLocal.set(sdf);
        }
        return sdf;
    }

    public static String formatDate(Date date) {
        return getSimpleDateFormat().format(date);
    }


    public static Date parse(String time) throws ParseException{
        return getSimpleDateFormat().parse(time);
    }
}
