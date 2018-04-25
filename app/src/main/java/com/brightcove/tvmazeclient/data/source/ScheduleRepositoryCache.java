package com.brightcove.tvmazeclient.data.source;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableList;
import android.databinding.ObservableMap;

import com.brightcove.tvmazeclient.data.model.Schedule;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Ali on 19-Apr-18.
 */

class ScheduleRepositoryCache {
    private static final int CACHE_CAPACITY=14;

    private ObservableList<Schedule> mTodayObservableScheduleList;
    private ObservableMap<String,ObservableList<Schedule>> mOtherDaysObservableScheduleListMap;
    private Queue<String> mSchedulesDatesCached;
    private boolean mIsCacheDirty;

    ScheduleRepositoryCache(){
        mTodayObservableScheduleList = new ObservableArrayList<>();
        mOtherDaysObservableScheduleListMap = new ObservableArrayMap<>();
        mSchedulesDatesCached = new LinkedList<>();
        mIsCacheDirty = true;
    }

        ObservableList<Schedule> getmTodayObservableScheduleList() {
        return mTodayObservableScheduleList;
    }

    void setmTodayObservableScheduleList(List<Schedule> mTodayObservableScheduleList) {
        this.mTodayObservableScheduleList.clear();
        this.mTodayObservableScheduleList.addAll(mTodayObservableScheduleList);
        //this.mTodayObservableScheduleList = mTodayObservableScheduleList;
    }

    ObservableList<Schedule> getOtherDaysObservableScheduleListMap(String date){
        if(mOtherDaysObservableScheduleListMap.containsKey(date))
            return mOtherDaysObservableScheduleListMap.get(date);
        setmIsCacheDirty(true);
        return addOtherDaysObservableScheduleListMap(date);

    }

    private ObservableList<Schedule> addOtherDaysObservableScheduleListMap(String date){
        ObservableList<Schedule> tmp =  new ObservableArrayList<>();
        if(mOtherDaysObservableScheduleListMap.size()==CACHE_CAPACITY){
            //Free the oldest list
            tmp = mOtherDaysObservableScheduleListMap.remove(mSchedulesDatesCached.poll());
            tmp.clear();
        }
        mOtherDaysObservableScheduleListMap.put(date, tmp);

        mSchedulesDatesCached.add(date);
        return tmp;
    }

    boolean ismIsCacheDirty() {
        return mIsCacheDirty;
    }

    void setmIsCacheDirty(boolean mIsCacheDirty) {
        this.mIsCacheDirty = mIsCacheDirty;
    }
    
    boolean todayScheduleIsEmpty(){
        return mTodayObservableScheduleList.isEmpty();
    }

    boolean isScheduleAvailable(String date) {
        return mOtherDaysObservableScheduleListMap.containsKey(date);
    }

    Schedule getTodaySchedule(int scheduleID) throws IllegalArgumentException{
        return findSchedule(this.mTodayObservableScheduleList,scheduleID);
    }

    Schedule getScheduleByIDAndDate(int scheduleID, String date) {
        ObservableList<Schedule> scheduleList = mOtherDaysObservableScheduleListMap.get(date);
        if(scheduleList != null){
            return findSchedule(scheduleList,scheduleID);
        }
        throw new IllegalArgumentException();
    }

    public void clearCache(){
        //TODO implement a clear cache method
    }

    private Schedule findSchedule(ObservableList<Schedule> scheduleList, int scheduleID)
    throws IllegalArgumentException{
        for (Schedule schedule: scheduleList)
            if (schedule.getId()== scheduleID)
                return schedule;
        throw new IllegalArgumentException();
    }
}
