package com.example.demo.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class DateUtils {
    private static Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static final String NORM_DATE_PATTERN ="YYYY-MM-dd";
    public static final String NORM_TIME_PATTERN ="HH:mm:ss";
    public static final String NORM_DATETIME_PATTERN="YYYY-MM-DD HH:mm:ss";


    public static Date parseDate(String dateString){
        try{
            return new SimpleDateFormat(NORM_DATE_PATTERN).parse(dateString);

        }catch (Exception e){
            log.info("parse "+dateString +" with format " + NORM_DATE_PATTERN + "error !",e);
        }
        return null;
    }
    public static Date parseDateTime(String dateTimeStr){
        try{
            return new SimpleDateFormat(NORM_DATETIME_PATTERN).parse(dateTimeStr);

        }catch (Exception e){
            log.info("parse " + dateTimeStr + " with format" + new SimpleDateFormat(NORM_DATETIME_PATTERN).toPattern() +"error!" , e);
        }
        return null;
    }

    public static  Date parseTime(String timeStr){
        try {
            return new SimpleDateFormat(NORM_TIME_PATTERN).parse(timeStr);

        }catch (Exception e){
            log.info("parse " + timeStr + " with format " + NORM_TIME_PATTERN +"error !" , e);
        }
        return null;
    }

    public static Date parse(String dateStr){
        int length = dateStr.length();
        try{
            if(length == DateUtils.NORM_DATE_PATTERN.length()){
                return parseDate(dateStr);
            } else if( length == DateUtils.NORM_DATETIME_PATTERN.length()){
                return parseDateTime(dateStr);
            } else if( length == DateUtils.NORM_TIME_PATTERN.length()){
                return parseTime(dateStr);
            }
        }catch (Exception e){
            log.info("parse "+dateStr+" with format normal error!",e);
        }
        return null;
    }
    private static LocalDateTime dateToLocalDateTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date localDateTimeToDate (LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date atDateOfBirth(Date date){
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOFday = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOFday);
    }


    public static Instant parseDateOfBirth(String dateStr){
        Date date = parse(dateStr);
        if(date != null){
            date = atDateOfBirth(date);
            return dateToLocalDateTime(date).toInstant(ZoneOffset.ofHours(+7));
        }
        return null;
    }
}
