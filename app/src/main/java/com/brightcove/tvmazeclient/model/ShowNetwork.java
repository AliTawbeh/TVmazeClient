package com.brightcove.tvmazeclient.model;

/**
 * Created by Ali on 12-Apr-18.
 */

public class ShowNetwork {
    private int id;
    private String name;
    private NetworkCountry country;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public NetworkCountry getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "ShowNetwork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
