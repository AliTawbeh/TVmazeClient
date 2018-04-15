package com.brightcove.tvmazeclient.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.brightcove.tvmazeclient.api.TVmazeAPIClient;
import com.brightcove.tvmazeclient.model.Schedule;

import java.net.UnknownHostException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import timber.log.Timber;

/**
 * Created by Ali on 14-Apr-18.
 */
//TODO create an interface for this guy
public class ScheduleViewModel extends BaseObservable{
    //RecyclerView's items
    @Bindable
    public ObservableArrayList<Schedule> mScheduleList;

    public ScheduleViewModel(){
        mScheduleList = new ObservableArrayList<>();
        getSchedule();

    }

    private void getSchedule(){
        TVmazeAPIClient.getInstance()
                .getUSSchedule()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(scheduleList -> {
                    for(Schedule schedule:scheduleList)
                        Timber.d(schedule.getId()+"");
                        mScheduleList.clear();
                        mScheduleList.addAll(scheduleList);
                }, throwable -> {
                    Timber.d("***" +throwable.getMessage() + "**" + throwable.getClass().getSimpleName());
                    if(throwable instanceof UnknownHostException){
                        UnknownHostException unknownHostException = (UnknownHostException) throwable;
                        Timber.d("***" +unknownHostException.getMessage());
                    }

                });
    }
}
