package com.brightcove.tvmazeclient.data.source.local;

import com.brightcove.tvmazeclient.data.model.Schedule;
import com.brightcove.tvmazeclient.data.source.ScheduleDataSource;
import com.brightcove.tvmazeclient.utils.DateUtil;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Ali on 19-Apr-18.
 */

public class LocalScheduleDataSource implements ScheduleDataSource {
    private static LocalScheduleDataSource INSTANCE;
    private ScheduleDao mScheduleDao;

    private LocalScheduleDataSource(ScheduleDao scheduleDao){
        mScheduleDao = scheduleDao;
    }

    public static LocalScheduleDataSource getInstance(ScheduleDao scheduleDao){
        if(INSTANCE==null)
            INSTANCE=new LocalScheduleDataSource(scheduleDao);
        return INSTANCE;
    }
    @Override
    public void getScheduleList(LoadSchedulesCallback loadSchedulesCallback) {
        fetchScheduleList(DateUtil.getTodayDateAPIFormat(), loadSchedulesCallback);
    }

    @Override
    public void getScheduleListByDate(String date, LoadSchedulesCallback loadSchedulesCallback) {
        fetchScheduleList(date, loadSchedulesCallback);
    }

    private void fetchScheduleList(String date, LoadSchedulesCallback loadSchedulesCallback){
        mScheduleDao.getScheduleListByDate(date)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loadSchedulesCallback::onScheduleListLoaded, throwable -> {
                    Timber.d("***" + throwable.getMessage() + "**" + throwable.getClass().getSimpleName());
                    loadSchedulesCallback.onDataNotAvailable();
                });
    }

    public void saveScheduleList(List<Schedule> scheduleList){
        Completable.fromAction(() -> mScheduleDao.insertSchedule(scheduleList))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() {
                        Timber.i("Data inserted successfully in the database");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e);
                    }
                });
    }

    @Override
    public void onDestroy() {

    }
}
