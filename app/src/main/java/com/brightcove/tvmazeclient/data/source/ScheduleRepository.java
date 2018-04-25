package com.brightcove.tvmazeclient.data.source;

import android.databinding.ObservableList;

import com.brightcove.tvmazeclient.data.model.Schedule;
import com.brightcove.tvmazeclient.utils.DateUtil;
import com.brightcove.tvmazeclient.utils.StringUtil;

import java.util.List;

import timber.log.Timber;

/**
 * Created by Ali on 19-Apr-18.
 */

public class ScheduleRepository {
    private static ScheduleRepository INSTANCE;
    private ScheduleDataSource mRemoteScheduleDataSource;
    private ScheduleDataSource mLocalScheduleDataSource;
    private ScheduleRepositoryCache mScheduleRepositoryCache;

    private ScheduleRepository(ScheduleDataSource remoteScheduleDataSource,
                               ScheduleDataSource localScheduleDataSource) {
        mRemoteScheduleDataSource = remoteScheduleDataSource;
        mLocalScheduleDataSource = localScheduleDataSource;
        mScheduleRepositoryCache = new ScheduleRepositoryCache();
    }

    public static ScheduleRepository getInstance(ScheduleDataSource remoteScheduleDataSource,
                                                 ScheduleDataSource localScheduleDataSource) {
        if (INSTANCE == null)
            INSTANCE = new ScheduleRepository(remoteScheduleDataSource, localScheduleDataSource);
        return INSTANCE;
    }

    public ObservableList<Schedule> getScheduleList(){
        if(mScheduleRepositoryCache.todayScheduleIsEmpty() || mScheduleRepositoryCache.ismIsCacheDirty()){
            if (mScheduleRepositoryCache.ismIsCacheDirty()){
                //cache is dirty, means force update, so we need to fetch data from the remote source and update the cache and the DB
                mRemoteScheduleDataSource.getScheduleList(new ScheduleDataSource.LoadSchedulesCallback() {
                    @Override
                    public void onScheduleListLoaded(List<Schedule> scheduleList) {
                        mScheduleRepositoryCache.setmTodayObservableScheduleList(scheduleList);
                        mScheduleRepositoryCache.setmIsCacheDirty(false);
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });
            }else {
                mLocalScheduleDataSource.getScheduleList(new ScheduleDataSource.LoadSchedulesCallback() {
                    @Override
                    public void onScheduleListLoaded(List<Schedule> scheduleList) {

                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });
            }
        }
        return mScheduleRepositoryCache.getmTodayObservableScheduleList();
    }

    public ObservableList<Schedule> getScheduleListByDate(String date){
        ObservableList<Schedule> cachedScheduleList = mScheduleRepositoryCache.getOtherDaysObservableScheduleListMap(date);
        if(!mScheduleRepositoryCache.isScheduleAvailable(date) || mScheduleRepositoryCache.ismIsCacheDirty()){
            if (mScheduleRepositoryCache.ismIsCacheDirty()){
                //cache is dirty, means force update, so we need to fetch data from the remote source and update the cache and the DB
                mRemoteScheduleDataSource.getScheduleListByDate(date, new ScheduleDataSource.LoadSchedulesCallback() {
                    @Override
                    public void onScheduleListLoaded(List<Schedule> scheduleList) {
                        //mScheduleRepositoryCache.addOtherDaysObservableScheduleListMap(date,scheduleList);
                        cachedScheduleList.clear();
                        cachedScheduleList.addAll(scheduleList);
                        Timber.d("onScheduleListLoaded");
                        mScheduleRepositoryCache.setmIsCacheDirty(false);
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });
            }else {
                mLocalScheduleDataSource.getScheduleList(new ScheduleDataSource.LoadSchedulesCallback() {
                    @Override
                    public void onScheduleListLoaded(List<Schedule> scheduleList) {

                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });
            }
        }
        return cachedScheduleList;
    }

    public Schedule getScheduleByIDAndDate(int scheduleID, String date) throws IllegalArgumentException{
        if(StringUtil.isNullOrEmpty(date) || date.equals(DateUtil.getTodayDateAPIFormat()))
            return mScheduleRepositoryCache.getTodaySchedule(scheduleID);

        return mScheduleRepositoryCache.getScheduleByIDAndDate(scheduleID,date);
    }

    public void refreshCache(){
        mScheduleRepositoryCache.setmIsCacheDirty(true);
    }
}
