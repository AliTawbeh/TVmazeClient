package com.brightcove.tvmazeclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;

import com.brightcove.tvmazeclient.api.TVmazeAPIClient;
import com.brightcove.tvmazeclient.model.Schedule;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ScheduleActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = ScheduleActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Timber.tag(ACTIVITY_NAME);

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

                });
    }
}
