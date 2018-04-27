package com.brightcove.tvmazeclient.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableList;

import com.brightcove.tvmazeclient.BR;
import com.brightcove.tvmazeclient.Injection;
import com.brightcove.tvmazeclient.data.model.Schedule;
import com.brightcove.tvmazeclient.data.source.ScheduleRepository;
import com.brightcove.tvmazeclient.recyclerviewAdapter.StringGenericComparator;
import com.brightcove.tvmazeclient.utils.DateUtil;

import timber.log.Timber;

/**
 * Created by Ali on 14-Apr-18.
 */
public class ScheduleViewModel extends BaseObservable{
    //RecyclerView's items
    @Bindable
    private ObservableList<Schedule> scheduleList;

    private String scheduleDate=DateUtil.getTodayDateUIFormat();
    private String scheduleDateAPIFormat=DateUtil.getTodayDateAPIFormat();
    private final ScheduleRepository scheduleRepository;

    public ScheduleViewModel(Context context){
        scheduleRepository = Injection.provideScheduleRepository(context);
        scheduleList= scheduleRepository.getScheduleList();
    }

    @Bindable
    public String getScheduleDate(){
        return scheduleDate;
    }

    public String getScheduleDateAPIFormat(){
        return scheduleDateAPIFormat;
    }

    public void setScheduleDate(int year, int month, int day){
        scheduleDate = DateUtil.formatDate(year,month,day);
        scheduleDateAPIFormat = DateUtil.toAPIFormatDate(year,month,day);
        notifyPropertyChanged(BR.scheduleDate);
        Timber.i("Picked year, month and date %d %d %d", year,month,day);
        scheduleList = scheduleRepository.getScheduleListByDate(scheduleDateAPIFormat);
        notifyPropertyChanged(BR.scheduleList);
    }

    public ObservableList<Schedule> getScheduleList(){
        return scheduleList;
    }

    public StringGenericComparator<Schedule> getStringComparator(){
        return (s, schedule) -> schedule.getName().toLowerCase().contains(s.toLowerCase())
                || schedule.getShow().getName().toLowerCase().contains(s.toLowerCase());
    }
}
