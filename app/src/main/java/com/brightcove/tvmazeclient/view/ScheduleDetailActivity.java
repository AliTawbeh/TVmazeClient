package com.brightcove.tvmazeclient.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.brightcove.tvmazeclient.R;
import com.brightcove.tvmazeclient.databinding.ActivityScheduleDetailBinding;
import com.brightcove.tvmazeclient.viewModel.ScheduleDetailViewModel;

public class ScheduleDetailActivity extends AppCompatActivity {

    public static final String SHOW_POS = "SHOW_POS";
    public static final int SHOW_POS_DEFAULT_VAL = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityScheduleDetailBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_schedule_detail);
        Intent intent = getIntent();
        if(intent.hasExtra(SHOW_POS)){
            int showPosition=intent.getIntExtra(SHOW_POS, SHOW_POS_DEFAULT_VAL);
            ScheduleDetailViewModel scheduleDetailViewModel = new ScheduleDetailViewModel(showPosition);
            binding.setScheduleDetailViewModel(scheduleDetailViewModel);
        }
    }
}
