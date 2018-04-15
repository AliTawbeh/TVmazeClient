package com.brightcove.tvmazeclient.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.brightcove.tvmazeclient.BR;
import com.brightcove.tvmazeclient.R;
import com.brightcove.tvmazeclient.viewModel.ScheduleViewModel;
import com.brightcove.tvmazeclient.databinding.ActivityScheduleBinding;
import com.brightcove.tvmazeclient.model.Schedule;
import com.brightcove.tvmazeclient.recyclerviewAdapter.ClickHandler;
import com.brightcove.tvmazeclient.recyclerviewAdapter.ItemBinder;

import timber.log.Timber;
//TODO create an interface for this guy
public class ScheduleActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = ScheduleActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityScheduleBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule);
        //TODO use and interface instead of concrete class
        ScheduleViewModel scheduleViewModel = new ScheduleViewModel();
        binding.setScheduleViewModel(scheduleViewModel);
        binding.setScheduleView(this);
        binding.rvSchedule.setLayoutManager(new LinearLayoutManager(this));
        Timber.tag(ACTIVITY_NAME);
    }

    //ItemBinder defines the layout and the variable for the viewHolder
    public ItemBinder itemBinder = new ItemBinder() {
        @Override
        public int getBindingVariable() {
            return BR.schedule;
        }

        @Override
        public int getLayoutRes() {
            return R.layout.schedule_layout;
        }
    };

    public ClickHandler<Schedule> clickHandler = schedule -> Timber.d(schedule.getId()+"");
}
