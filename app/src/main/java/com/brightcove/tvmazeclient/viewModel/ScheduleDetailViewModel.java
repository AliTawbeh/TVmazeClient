package com.brightcove.tvmazeclient.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;

import com.brightcove.tvmazeclient.BR;
import com.brightcove.tvmazeclient.datamanager.RemoteScheduleManager;
import com.brightcove.tvmazeclient.model.Schedule;

/**
 * Created by Ali on 15-Apr-18.
 */

public class ScheduleDetailViewModel extends BaseObservable{
    private Schedule schedule;
    public ScheduleDetailViewModel(int scheduleID){
        try {
            schedule= RemoteScheduleManager.getInstance().getScheduleByID(scheduleID);
            notifyPropertyChanged(BR.schedule);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Bindable
    public Schedule getSchedule(){
        return schedule;
    }

}
