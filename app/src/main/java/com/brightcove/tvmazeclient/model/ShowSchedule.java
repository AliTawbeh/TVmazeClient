package com.brightcove.tvmazeclient.model;

import java.util.Arrays;

/**
 * Created by Ali on 12-Apr-18.
 */

public class ShowSchedule {
    private String time;
    private String[] days;

    public String getTime() {
        return time;
    }

    public String[] getDays() {
        return days;
    }

    @Override
    public String toString() {
        return "ShowSchedule{" +
                "time='" + time + '\'' +
                ", days=" + Arrays.toString(days) +
                '}';
    }
}
