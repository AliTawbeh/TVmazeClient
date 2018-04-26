package com.brightcove.tvmazeclient.data.source;

import android.databinding.ObservableList;

import com.brightcove.tvmazeclient.data.model.Schedule;
import com.brightcove.tvmazeclient.utils.DateUtil;
import com.brightcove.tvmazeclient.utils.StringUtil;

import java.util.List;

/**
 * Created by Ali on 19-Apr-18.
 */
//TODO implement a strategy to wipe the old data in the DB, or it will keep growing
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
                getDataFromRemoteSource();
            }else {
                mLocalScheduleDataSource.getScheduleList(new ScheduleDataSource.LoadSchedulesCallback() {
                    @Override
                    public void onScheduleListLoaded(List<Schedule> scheduleList) {
                        if(scheduleList.isEmpty() ||  scheduleList.size()<10){
                            //Call the remote server
                            getDataFromRemoteSource();
                        }else
                            updateCache(mScheduleRepositoryCache.getTodayObservableScheduleList(),scheduleList);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        //Call the remote server
                        getDataFromRemoteSource();
                    }
                });
            }
        }
        return mScheduleRepositoryCache.getTodayObservableScheduleList();
    }

    public ObservableList<Schedule> getScheduleListByDate(String date){
        ObservableList<Schedule> cachedScheduleList = mScheduleRepositoryCache.getOtherDaysObservableScheduleListMap(date);
        if(!mScheduleRepositoryCache.isScheduleAvailable(date) || mScheduleRepositoryCache.ismIsCacheDirty()){
            if (mScheduleRepositoryCache.ismIsCacheDirty()){
                //cache is dirty, means force update, so we need to fetch data from the remote source and update the cache and the DB
                getDataFromRemoteSourceByDate(date,cachedScheduleList);
            }else {
                mLocalScheduleDataSource.getScheduleListByDate(date,new ScheduleDataSource.LoadSchedulesCallback() {
                    @Override
                    public void onScheduleListLoaded(List<Schedule> scheduleList) {
                        if(scheduleList.isEmpty() || scheduleList.size()<10){
                            //Call the remote server
                            getDataFromRemoteSourceByDate(date,cachedScheduleList);
                        }else
                            updateCache(cachedScheduleList,scheduleList);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        getDataFromRemoteSourceByDate(date,cachedScheduleList);
                    }
                });
            }
        }
        return cachedScheduleList;
    }

    private void getDataFromRemoteSource(){
        mRemoteScheduleDataSource.getScheduleList(new ScheduleDataSource.LoadSchedulesCallback() {
            @Override
            public void onScheduleListLoaded(List<Schedule> scheduleList) {
                updateCache(mScheduleRepositoryCache.getTodayObservableScheduleList(), scheduleList);
                mScheduleRepositoryCache.setmIsCacheDirty(false);
                mLocalScheduleDataSource.saveScheduleList(scheduleList);
            }

            @Override
            public void onDataNotAvailable() {
                //TODO show a message that an error has occurred
            }
        });
    }

    private void getDataFromRemoteSourceByDate(String date, ObservableList<Schedule> cachedScheduleList){
        mRemoteScheduleDataSource.getScheduleListByDate(date, new ScheduleDataSource.LoadSchedulesCallback() {
            @Override
            public void onScheduleListLoaded(List<Schedule> scheduleList) {
                //mScheduleRepositoryCache.addOtherDaysObservableScheduleListMap(date,scheduleList);
                updateCache(cachedScheduleList,scheduleList);
                mScheduleRepositoryCache.setmIsCacheDirty(false);
                mLocalScheduleDataSource.saveScheduleList(scheduleList);
            }

            @Override
            public void onDataNotAvailable() {
                //TODO show a message that an error has occurred
            }
        });
    }

    private void updateCache(ObservableList<Schedule> cachedScheduleList,List<Schedule> scheduleList){
        cachedScheduleList.clear();
        cachedScheduleList.addAll(scheduleList);
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
