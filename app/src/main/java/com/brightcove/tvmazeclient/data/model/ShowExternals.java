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

    public void setTvrage(int tvrage) {
        this.tvrage = tvrage;
    }

    public void setThetvdb(int thetvdb) {
        this.thetvdb = thetvdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
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
