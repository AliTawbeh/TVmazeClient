package com.brightcove.tvmazeclient.data.source.local;

import com.brightcove.tvmazeclient.data.source.ScheduleDataSource;

/**
 * Created by Ali on 19-Apr-18.
 */

public class LocalScheduleDataSource implements ScheduleDataSource {
    private static LocalScheduleDataSource INSTANCE;

    private LocalScheduleDataSource(){

    }

    public static LocalScheduleDataSource getInstance(){
        if(INSTANCE==null)
            INSTANCE=new LocalScheduleDataSource();
        return INSTANCE;
    }
    @Override
    public void getScheduleList(LoadSchedulesCallback loadSchedulesCallback) {
        //TODO
        loadSchedulesCallback.onDataNotAvailable();
    }

    @Override
    public void getScheduleListByDate(String date, LoadSchedulesCallback loadSchedulesCallback) {
        //TODO
        loadSchedulesCallback.onDataNotAvailable();
    }

    @Override
    public void onDestroy() {

    }
}
