package com.brightcove.tvmazeclient.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Ali on 16-Apr-18.
 */

public final class DateUtil {
    public static String getTodayDateUIFormat(){
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MM dd, yyyy", Locale.US);
        return sdf.format(date);
    }

    public static String getTodayDateAPIFormat(){
        return getDateAPIFormat(System.currentTimeMillis());
    }

    private static String getDateAPIFormat(long date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return sdf.format(date);
    }

    public static String formatDate(int year, int month, int day){
        Calendar c = Calendar.getInstance();
        c.set(year,month,day);
        long date = c.getTimeInMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MM dd, yyyy", Locale.US);
        return sdf.format(date);
    }

    public static String toAPIFormatDate(int year, int month, int day){
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        return getDateAPIFormat(c.getTimeInMillis());
    }
}
