package com.brightcove.tvmazeclient.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.brightcove.tvmazeclient.data.model.Schedule;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Ali on 24-Apr-18.
 */
@Dao
public interface ScheduleDao {
    @Query("SELECT * FROM Schedule WHERE Schedule.airdate = :date")
    Single<List<Schedule>> getScheduleListByDate(String date);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSchedule(List<Schedule> scheduleList);

    @Query("DELETE FROM Schedule WHERE Schedule.airdate = :date")
    int deleteScheduleListByDate(String date);

    @Query("DELETE FROM Schedule")
    void deleteSchedules();
}
