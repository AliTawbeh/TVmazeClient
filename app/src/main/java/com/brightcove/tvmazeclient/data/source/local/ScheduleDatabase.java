package com.brightcove.tvmazeclient.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.brightcove.tvmazeclient.data.model.Schedule;
import com.brightcove.tvmazeclient.utils.CustomTypeConverters;

/**
 * Created by Ali on 23-Apr-18.
 */

@Database(entities = {Schedule.class}, version = 1)
@TypeConverters({CustomTypeConverters.class})
public abstract class ScheduleDatabase extends RoomDatabase {
    private static ScheduleDatabase INSTANCE;
    private static final String DATABASE_NAME = "Schedule.db";
    public abstract ScheduleDao scheduleDao();
    public static ScheduleDatabase getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ScheduleDatabase.class,DATABASE_NAME)
                    .build();
        }
        return INSTANCE;
    }
}
