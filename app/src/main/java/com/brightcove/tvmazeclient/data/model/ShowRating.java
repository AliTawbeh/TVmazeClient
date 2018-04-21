package com.brightcove.tvmazeclient.data.model;

/**
 * Created by Ali on 12-Apr-18.
 */

public class ShowRating {
    private double average;

    public double getAverage() {
        return average;
    }

    @Override
    public String toString() {
        return "ShowRating{" +
                "average=" + average +
                '}';
    }
}
