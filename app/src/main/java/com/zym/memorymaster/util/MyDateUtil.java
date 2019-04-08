package com.zym.memorymaster.util;


import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 12390 on 2017/8/30.
 */
public class MyDateUtil {


    public static String[] weekDays = {"", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};

    public static int getDistanceTime(String from, String end) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long minute = 0;
        try {
            one = df.parse(from);
            two = df.parse(end);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }

            minute = (diff / (60 * 1000));


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) minute;

    }

    public static int compareDate(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static Integer getIndOfDay(String day) {
        for (int i = 0; i <= 7; i++) {
            if (weekDays[i].equals(day))
                return i;
        }
        return -1;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date stringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getWeekOfDate(Date dt) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        if (w == 0)
            w = 7;
        return weekDays[w];
    }

    public static String getNextTHTime() {
        Date now = new Date();
        return dateToString(new Date(now.getTime() + 2 * 60 * 60 * 1000));
    }

    public static String getNextDay(long base) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("mm");
        Integer hour = Integer.valueOf(df2.format(date));
        String time = "00:00:00";
        if(hour>=12){
            time = "12:00:00";
        }
        return df.format(date.getTime()+base*60*60*1000)+  time;
    }
}
