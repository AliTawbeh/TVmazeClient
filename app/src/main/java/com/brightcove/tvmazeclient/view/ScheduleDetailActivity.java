package com.brightcove.tvmazeclient.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brightcove.tvmazeclient.R;
import com.brightcove.tvmazeclient.databinding.ActivityScheduleDetailBinding;
import com.brightcove.tvmazeclient.viewModel.ScheduleDetailViewModel;

public class ScheduleDetailActivity extends AppCompatActivity {

    public static final String SCHEDULE_ID = "SCHEDULE_ID";
    public static final int SCHEDULE_ID_DEFAULT_VALUE = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityScheduleDetailBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_schedule_detail);
        Intent intent = getIntent();
        if(intent.hasExtra(SCHEDULE_ID)){
            int scheduleID=intent.getIntExtra(SCHEDULE_ID, SCHEDULE_ID_DEFAULT_VALUE);
            ScheduleDetailViewModel scheduleDetailViewModel = new ScheduleDetailViewModel(scheduleID);
            binding.setScheduleDetailViewModel(scheduleDetailViewModel);
        }
    }
}
