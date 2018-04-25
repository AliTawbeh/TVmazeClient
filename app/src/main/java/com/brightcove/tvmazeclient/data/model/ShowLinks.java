package com.brightcove.tvmazeclient.data.model;

import android.arch.persistence.room.Embedded;

/**
 * Created by Ali on 12-Apr-18.
 */

public class ShowLinks {
    @Embedded(prefix = "LinkRef_Self")
    private LinkRef self;
    @Embedded(prefix = "LinkRef_PreviousEpisode")
    private LinkRef previousepisode;
    @Embedded(prefix = "LinkRef_NextEpisode")
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

    public void setSelf(LinkRef self) {
        this.self = self;
    }

    public void setPreviousepisode(LinkRef previousepisode) {
        this.previousepisode = previousepisode;
    }

    public void setNextepisode(LinkRef nextepisode) {
        this.nextepisode = nextepisode;
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
