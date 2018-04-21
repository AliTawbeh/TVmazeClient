package com.brightcove.tvmazeclient.data.source;

import com.brightcove.tvmazeclient.data.model.Schedule;
import java.util.List;

/**
 * Created by Ali on 19-Apr-18.
 */

public interface ScheduleDataSource {
    interface LoadSchedulesCallback {
        void onScheduleListLoaded(List<Schedule> scheduleList);
        void onDataNotAvailable();
    }

    void getScheduleList(LoadSchedulesCallback loadSchedulesCallback);

    void getScheduleListByDate(String date, LoadSchedulesCallback loadSchedulesCallback);

    void onDestroy();
}
