package com.brightcove.tvmazeclient.model;

/**
 * Created by Ali on 12-Apr-18.
 */

public class NetworkCountry {
    private String name;
    private String code;
    private String timezone;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getTimezone() {
        return timezone;
    }

    @Override
    public String toString() {
        return "NetworkCountry{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
