package com.brightcove.tvmazeclient.data.model;

/**
 * Created by Ali on 13-Apr-18.
 */

class ShowWebChannel {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ShowWebChannel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
