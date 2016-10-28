package com.own.authority.domain.util;

import org.springframework.util.StringUtils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016-04-18.
 */
public class DateUtil {

    public static final String DATE_SHORT_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_SHORT_PALLTTRN = "HH:mm";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String TIME_PATTERN_EN = "ddMMMyyyy";
    private static final String TIME_PATTERN_EN2 = "dd/MM/yyyy";

    private static final String LONG_DATE_PATTERN = "yyyyMMddHHmmss";
    public static final String DATE_SHORT = "yyyy年MM月dd日";


    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date StringToDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dayDiffer(Date from,int day,String format){
       long to = (from.getTime()/1000) + 60*60*24*day;
        Date toDate = new Date();
        toDate.setTime(to*1000);
        return dateToString(toDate,format);
    }


    public static Time stringToTime(String stringDate,String format) {
        if(StringUtils.isEmpty(format)){
            format = DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Time(date.getTime());
    }
}
