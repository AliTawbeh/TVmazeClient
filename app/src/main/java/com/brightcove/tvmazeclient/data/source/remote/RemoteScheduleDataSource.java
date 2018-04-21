package com.brightcove.tvmazeclient.data.source.remote;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.brightcove.tvmazeclient.data.model.Schedule;
import com.brightcove.tvmazeclient.data.source.ScheduleDataSource;
import com.brightcove.tvmazeclient.data.source.remote.api.TVmazeAPIClient;
import com.brightcove.tvmazeclient.utils.StringUtil;

import java.net.UnknownHostException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Ali on 15-Apr-18.
 */

public class RemoteScheduleDataSource implements ScheduleDataSource {
    private static RemoteScheduleDataSource INSTANCE;
    private Disposable disposable;

    private RemoteScheduleDataSource() {
    }

    public static RemoteScheduleDataSource getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RemoteScheduleDataSource();
        return INSTANCE;
    }

    @Override
    public void getScheduleList(LoadSchedulesCallback loadSchedulesCallback) {
        fetchSchedules("",loadSchedulesCallback);
    }

    @Override
    public void getScheduleListByDate(String date, LoadSchedulesCallback loadSchedulesCallback) {
        fetchSchedules(date,loadSchedulesCallback);
    }

    @Override
    public void onDestroy() {
        if(disposable !=null && !disposable.isDisposed())
            disposable.dispose();
    }

    private void fetchSchedules(String date, LoadSchedulesCallback callback) {
        Observable<List<Schedule>> rxObservableScheduleList;

        if (StringUtil.isNullOrEmpty(date))
            rxObservableScheduleList = TVmazeAPIClient.getInstance().getUSSchedule();
        else
            rxObservableScheduleList = TVmazeAPIClient.getInstance().getUSScheduleByDate(date);

        disposable = rxObservableScheduleList
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(scheduleList -> {
                    ObservableList<Schedule> observableScheduleList = new ObservableArrayList<>();
                    observableScheduleList.addAll(scheduleList);
                    Timber.d("fetchSchedule");
                    callback.onScheduleListLoaded(observableScheduleList);
                }, throwable -> {
                    Timber.d("***" + throwable.getMessage() + "**" + throwable.getClass().getSimpleName());
                    if (throwable instanceof UnknownHostException) {
                        UnknownHostException unknownHostException = (UnknownHostException) throwable;
                        Timber.d("***" + unknownHostException.getMessage());
                    }
                    callback.onDataNotAvailable();
                });
    }
}
