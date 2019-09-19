package com.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Date;

/**
 * @author Mr.LB
 * @description: 日期、时间 工具类
 * @date 2019/7/12 10:48
 */
public class DateUtil {

    //年月日时分秒毫秒
    public final static String YYYYMMDDHHMMSSSSS = "yyyy-MM-dd HH:mm:ss.SSS";

    //年月日时分秒
    public final static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    //年月日时分
    public final static String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";

    //年月日
    public final static String YYYYMMDD = "yyyy-MM-dd";

    //年月
    public final static String YYYYMM = "yyyy-MM";

    //时分秒
    public final static String HHMMSS = "HH:mm:ss";

    //时分
    public final static String HHMM = "HH:mm";

    /**
     * 将  日期-时间  格式的字符串转化为  LocalDateTime
     * @param dateTimeStr       日期-时间 字符串
     * @param dateTimeFormat    需要转化的格式类型
     * @return
     */
    public static LocalDateTime parseStringToDateTime(String dateTimeStr,String dateTimeFormat){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);

        if(StringUtils.isEmpty(dateTimeFormat)){
            throw new RuntimeException("未填写时间解析格式!");
        }

        if(dateTimeFormat.equals(YYYYMMDDHHMMSSSSS) || dateTimeFormat.equals(YYYYMMDDHHMMSS) || dateTimeFormat.equals(YYYYMMDDHHMM)){

            //解析为【日期+时间】的格式
            return LocalDateTime.parse(dateTimeStr,DateTimeFormatter.ofPattern(dateTimeFormat));

        }else if(dateTimeFormat.equals(YYYYMMDD) || dateTimeFormat.equals(YYYYMM)){

            //解析为【日期】的格式
            LocalDate.parse(dateTimeStr,dateTimeFormatter);

        }else if(dateTimeFormat.equals(HHMMSS) || dateTimeFormat.equals(HHMM)){

            //解析为【时间】的格式
            LocalTime.parse(dateTimeStr,dateTimeFormatter);

        }else{
            throw new RuntimeException("解析格式不合法!");
        }

        return null;
    }

    /**
     * 时间戳字符串解析为Date
     * @param dateStr       2019-01-01 12:00:00
     * @param dateFormat    yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static LocalDateTime parseStringToDate(String dateStr,String dateFormat){

        LocalDateTime localDateTime = LocalDateTime.parse(dateStr);
        System.out.println(localDateTime.toString());

        return localDateTime;
    }

    /**
     * Date格式化为时间戳字符串
     * @param date
     * @param dateFormat
     * @return
     */
    public static String formatDateToString(Date date,String dateFormat){
        try{
            String dateStr = new SimpleDateFormat(dateFormat).format(date);

            return dateStr;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
//        System.out.println(parseStringToDate("2015-02-20T07:00",YYYYMMDDHHMMSSSSS));
//        System.out.println(formatDateToString(new Date(),YYYYMMDDHHMMSS));

        LocalDateTime localDateTime = LocalDateTime.parse("2015-02-20T07:00:00.900");
        System.out.println(localDateTime);
    }

}
