package com.brightcove.tvmazeclient;

import android.content.Context;

import com.brightcove.tvmazeclient.data.source.ScheduleRepository;
import com.brightcove.tvmazeclient.data.source.local.LocalScheduleDataSource;
import com.brightcove.tvmazeclient.data.source.local.ScheduleDatabase;
import com.brightcove.tvmazeclient.data.source.remote.RemoteScheduleDataSource;

/**
 * Created by Ali on 20-Apr-18.
 */

public class Injection {
    public static ScheduleRepository provideScheduleRepository(Context context){
        ScheduleDatabase db = ScheduleDatabase.getInstance(context);
        return ScheduleRepository.getInstance(RemoteScheduleDataSource.getInstance(),
                LocalScheduleDataSource.getInstance(db.scheduleDao()));
    }
}
