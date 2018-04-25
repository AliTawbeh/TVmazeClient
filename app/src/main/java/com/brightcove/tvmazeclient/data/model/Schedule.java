package com.brightcove.tvmazeclient.data.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ali on 12-Apr-18.
 */
@Entity(tableName = "Schedule")
public class Schedule {
    @PrimaryKey
    private int id;
    private String url;
    private String name;
    private int season;
    private int number;
    private String airdate;
    private String airtime;
    private String airstamp;
    private int runtime;
    @Embedded(prefix = "ImageRef")
    private ImageRef image;
    private String summary;
    @Embedded(prefix = "Show")
    private Show show;
    @Embedded(prefix = "ScheduleLinks")
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

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setAirdate(String airdate) {
        this.airdate = airdate;
    }

    public void setAirtime(String airtime) {
        this.airtime = airtime;
    }

    public void setAirstamp(String airstamp) {
        this.airstamp = airstamp;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setImage(ImageRef image) {
        this.image = image;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public void set_links(ScheduleLinks _links) {
        this._links = _links;
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
