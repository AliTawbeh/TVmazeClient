package com.brightcove.tvmazeclient.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.brightcove.tvmazeclient.BR;
import com.brightcove.tvmazeclient.Injection;
import com.brightcove.tvmazeclient.data.source.ScheduleRepository;
import com.brightcove.tvmazeclient.data.source.remote.RemoteScheduleDataSource;
import com.brightcove.tvmazeclient.data.model.Schedule;

/**
 * Created by Ali on 15-Apr-18.
 */

public class ScheduleDetailViewModel extends BaseObservable{
    private Schedule schedule;
    public ScheduleDetailViewModel(Context context, int scheduleID, String date){
        try {
            schedule= Injection.provideScheduleRepository(context).getScheduleByIDAndDate(scheduleID,date);
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
