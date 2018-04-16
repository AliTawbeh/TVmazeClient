package com.brightcove.tvmazeclient.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableList;

import com.brightcove.tvmazeclient.BR;
import com.brightcove.tvmazeclient.datamanager.RemoteScheduleManager;
import com.brightcove.tvmazeclient.model.Schedule;
import com.brightcove.tvmazeclient.recyclerviewAdapter.StringGenericComparator;
import com.brightcove.tvmazeclient.utils.DateUtil;

/**
 * Created by Ali on 14-Apr-18.
 */
public class ScheduleViewModel extends BaseObservable{
    //RecyclerView's items
    @Bindable
    private ObservableList<Schedule> scheduleList;

    private String scheduleDate=DateUtil.getTodaysDate();

    public ScheduleViewModel(){
        scheduleList= RemoteScheduleManager.getInstance().getScheduleList();
    }

    @Bindable
    public String getScheduleDate(){
        return scheduleDate;
    }

    public void setScheduleDate(int year, int month, int day){
        scheduleDate = DateUtil.formatDate(year,month,day);
        notifyPropertyChanged(BR.scheduleDate);
        RemoteScheduleManager.getInstance().fetchSchedulesByDate(DateUtil.toAPIFormatDate(year,month,day));
    }

    public ObservableList<Schedule> getScheduleList(){
        return scheduleList;
    }

    public StringGenericComparator<Schedule> getStringComparator(){
        return (s, schedule) -> schedule.getName().toLowerCase().contains(s.toLowerCase())
                || schedule.getShow().getName().toLowerCase().contains(s.toLowerCase());
    }
}
