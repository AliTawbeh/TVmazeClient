package com.brightcove.tvmazeclient.data.model;

/**
 * Created by Ali on 13-Apr-18.
 */

public class ShowWebChannel {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ShowWebChannel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
