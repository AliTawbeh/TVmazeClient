package com.brightcove.tvmazeclient.model;

import com.google.gson.JsonObject;

/**
 * Created by Ali on 12-Apr-18.
 */

public class Schedule {
    private int id;
    private String url;
    private String name;
    private int season;
    private int number;
    private String airdate;
    private String airtime;
    private String airstamp;
    private int runtime;
    private ImageRef image;
    private String summary;
    private Show show;
    private ScheduleLinks _links;

    public int getId(){
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public int getSeason() {
        return season;
    }

    public int getNumber() {
        return number;
    }

    public String getAirdate() {
        return airdate;
    }

    public String getAirtime() {
        return airtime;
    }

    public String getAirstamp() {
        return airstamp;
    }

    public int getRuntime() {
        return runtime;
    }

    public ImageRef getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }

    public Show getShow() {
        return show;
    }

    public ScheduleLinks get_links() {
        return _links;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", season=" + season +
                ", number=" + number +
                ", airdate='" + airdate + '\'' +
                ", airtime='" + airtime + '\'' +
                ", airstamp='" + airstamp + '\'' +
                ", runtime=" + runtime +
                ", image='" + image + '\'' +
                ", summary='" + summary + '\'' +
                ", show=" + show +
                ", _links=" + _links +
                '}';
    }
}
