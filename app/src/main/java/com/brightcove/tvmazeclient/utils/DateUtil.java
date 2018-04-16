package com.brightcove.tvmazeclient.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Ali on 16-Apr-18.
 */

public final class DateUtil {
    public static String getTodaysDate(){
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MM dd, yyyy", Locale.US);
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
        //month are indexed starting 0
        String monthStr = (++month<10)? "0"+month:month+"";
        String dayStr = day<10? "0" + day:day+"";
        return year + "-" + monthStr + "-" + dayStr;
    }
}
