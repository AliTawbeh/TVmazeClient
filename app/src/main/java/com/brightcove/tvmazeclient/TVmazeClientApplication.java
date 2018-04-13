package com.brightcove.tvmazeclient;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Ali on 13-Apr-18.
 */

public class TVmazeClientApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
    }
}
