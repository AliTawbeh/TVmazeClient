package com.brightcove.tvmazeclient.data.model;

/**
 * Created by Ali on 12-Apr-18.
 */

public class ShowExternals {
    private int tvrage;
    private int thetvdb;
    private String imdb;

    public int getTvrage() {
        return tvrage;
    }

    public int getThetvdb() {
        return thetvdb;
    }

    public String getImdb() {
        return imdb;
    }

    @Override
    public String toString() {
        return "ShowExternals{" +
                "tvrage=" + tvrage +
                ", thetvdb=" + thetvdb +
                ", imdb='" + imdb + '\'' +
                '}';
    }
}
