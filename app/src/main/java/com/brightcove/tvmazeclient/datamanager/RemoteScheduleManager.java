package com.brightcove.tvmazeclient.datamanager;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.brightcove.tvmazeclient.api.TVmazeAPIClient;
import com.brightcove.tvmazeclient.model.Schedule;

import java.net.UnknownHostException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Ali on 15-Apr-18.
 */

public class RemoteScheduleManager {
    private static RemoteScheduleManager instance;
    private ObservableList<Schedule> mScheduleList;
    private RemoteScheduleManager(){
        mScheduleList=new ObservableArrayList<>();
        fetchSchedules();
    }

    public static RemoteScheduleManager getInstance(){
        if (instance == null)
            instance = new RemoteScheduleManager();
        return instance;
    }

    public ObservableList<Schedule> getScheduleList(){
        return mScheduleList;
    }

    private void fetchSchedules() {
        TVmazeAPIClient.getInstance()
                .getUSSchedule()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(scheduleList -> {
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

    public Schedule getScheduleAt(int scheduleIndex) throws IndexOutOfBoundsException{
        if(scheduleIndex>=mScheduleList.size() || scheduleIndex<0)
            throw new IndexOutOfBoundsException();
        return mScheduleList.get(scheduleIndex);
    }
}
