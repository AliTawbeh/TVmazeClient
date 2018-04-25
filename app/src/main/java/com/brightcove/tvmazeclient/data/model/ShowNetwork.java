package com.brightcove.tvmazeclient.data.model;

import android.arch.persistence.room.Embedded;

/**
 * Created by Ali on 12-Apr-18.
 */

public class ShowNetwork {
    private int id;
    private String name;
    @Embedded(prefix = "NetworkCountry")
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(NetworkCountry country) {
        this.country = country;
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
