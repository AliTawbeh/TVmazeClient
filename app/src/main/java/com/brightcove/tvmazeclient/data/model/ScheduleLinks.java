package com.brightcove.tvmazeclient.data.model;

import android.arch.persistence.room.Embedded;

/**
 * Created by Ali on 12-Apr-18.
 */

public class ScheduleLinks {
    @Embedded(prefix = "LinkRef")
    private LinkRef self;

    public LinkRef getSelf() {
        return self;
    }

    public void setSelf(LinkRef self) {
        this.self = self;
    }

    @Override
    public String toString() {
        return "ScheduleLinks{" +
                "self=" + self +
                '}';
    }
}
