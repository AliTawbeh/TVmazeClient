package com.brightcove.tvmazeclient.data.model;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
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
