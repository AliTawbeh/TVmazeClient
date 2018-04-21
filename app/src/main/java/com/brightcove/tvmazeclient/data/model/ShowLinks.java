package com.brightcove.tvmazeclient.data.model;

/**
 * Created by Ali on 12-Apr-18.
 */

public class ShowLinks {
    private LinkRef self;
    private LinkRef previousepisode;
    private LinkRef nextepisode;

    public LinkRef getSelf() {
        return self;
    }

    public LinkRef getPreviousepisode() {
        return previousepisode;
    }

    public LinkRef getNextepisode() {
        return nextepisode;
    }

    @Override
    public String toString() {
        return "ShowLinks{" +
                "self=" + self +
                ", previousepisode=" + previousepisode +
                ", nextepisode=" + nextepisode +
                '}';
    }
}
