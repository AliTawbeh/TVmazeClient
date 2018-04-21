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

    private ObservableList<Schedule> todayObservableScheduleList;
    private ObservableMap<String,ObservableList<Schedule>> otherDaysObservableScheduleListMap;
    private Queue<String> schedulesDatesCached;
    private boolean isCacheDirty;

    ScheduleRepositoryCache(){
        todayObservableScheduleList = new ObservableArrayList<>();
        otherDaysObservableScheduleListMap = new ObservableArrayMap<>();
        schedulesDatesCached = new LinkedList<>();
        isCacheDirty = true;
    }

    ObservableList<Schedule> getTodayObservableScheduleList() {
        return todayObservableScheduleList;
    }

    void setTodayObservableScheduleList(List<Schedule> todayObservableScheduleList) {
        this.todayObservableScheduleList.clear();
        this.todayObservableScheduleList.addAll(todayObservableScheduleList);
        //this.todayObservableScheduleList = todayObservableScheduleList;
    }

    ObservableList<Schedule> getOtherDaysObservableScheduleListMap(String date){
        if(otherDaysObservableScheduleListMap.containsKey(date))
            return otherDaysObservableScheduleListMap.get(date);
        setCacheDirty(true);
        return addOtherDaysObservableScheduleListMap(date);

    }

    private ObservableList<Schedule> addOtherDaysObservableScheduleListMap(String date){
        ObservableList<Schedule> tmp =  new ObservableArrayList<>();
        if(otherDaysObservableScheduleListMap.size()==CACHE_CAPACITY){
            //Free the oldest list
            tmp = otherDaysObservableScheduleListMap.remove(schedulesDatesCached.poll());
            tmp.clear();
        }
        otherDaysObservableScheduleListMap.put(date, tmp);

        schedulesDatesCached.add(date);
        return tmp;
    }

    boolean isCacheDirty() {
        return isCacheDirty;
    }

    void setCacheDirty(boolean cacheDirty) {
        isCacheDirty = cacheDirty;
    }
    
    boolean todayScheduleIsEmpty(){
        return todayObservableScheduleList.isEmpty();
    }

    boolean isScheduleAvailable(String date) {
        return otherDaysObservableScheduleListMap.containsKey(date);
    }

    Schedule getTodaySchedule(int scheduleID) throws IllegalArgumentException{
        return findSchedule(this.todayObservableScheduleList,scheduleID);
    }

    Schedule getScheduleByIDAndDate(int scheduleID, String date) {
        ObservableList<Schedule> scheduleList = otherDaysObservableScheduleListMap.get(date);
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
