package com.brightcove.tvmazeclient.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableList;

import com.brightcove.tvmazeclient.datamanager.RemoteScheduleManager;
import com.brightcove.tvmazeclient.model.Schedule;

/**
 * Created by Ali on 14-Apr-18.
 */
public class ScheduleViewModel extends BaseObservable{
    //RecyclerView's items
    @Bindable
    private ObservableList<Schedule> scheduleList;

    public ScheduleViewModel(){
        scheduleList= RemoteScheduleManager.getInstance().getScheduleList();
    }

    public ObservableList<Schedule> getScheduleList(){
        return scheduleList;
    }
}
