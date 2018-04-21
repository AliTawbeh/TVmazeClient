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
    public static final String SCHEDULE_DATE = "SCHEDULE_DATE";
    public static final int SCHEDULE_ID_DEFAULT_VALUE = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityScheduleDetailBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_schedule_detail);
        Intent intent = getIntent();
        if(intent.hasExtra(SCHEDULE_ID)){
            int scheduleID = intent.getIntExtra(SCHEDULE_ID, SCHEDULE_ID_DEFAULT_VALUE);
            String scheduleDate = intent.getStringExtra(SCHEDULE_DATE);
            ScheduleDetailViewModel scheduleDetailViewModel =
                    new ScheduleDetailViewModel(scheduleID, scheduleDate);
            binding.setScheduleDetailViewModel(scheduleDetailViewModel);
        }else
            this.finish();
    }

    //I added this to prevent the system from recreating the parent activity when up button is clicked
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
