package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mr.LB
 * @description: 日期、时间 工具类
 * @date 2019/7/12 10:48
 */
public class DateUtil {

    //年与日时分秒毫秒
    private final static String YYYYMMDDHHMMSSSSS = "yyyy-MM-dd HH:mm:ss.SSS";

    //年与日时分秒
    private final static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    //年与日时分
    private final static String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";

    //年与日时
    private final static String YYYYMMDDHH = "yyyy-MM-dd HH";

    //年与日
    private final static String YYYYMMDD = "yyyy-MM-dd";

    //年与
    private final static String YYYYMM = "yyyy-MM";

    //时分秒
    private final static String HHMMSS = "HH:mm:ss";

    //年与日时分秒
    private final static String HHMM = "HH:mm";

    /**
     * 时间戳字符串解析为Date
     * @param dateStr       2019-01-01 12:00:00
     * @param dateFormat    yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date parseStringToDate(String dateStr,String dateFormat){
        try {
            Date date = new SimpleDateFormat(dateFormat).parse(dateStr);

            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
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
        System.out.println(parseStringToDate("2019-07-12 14:00:00",YYYYMMDDHHMMSS));
        System.out.println(formatDateToString(new Date(),YYYYMMDDHHMMSS));
    }

}
