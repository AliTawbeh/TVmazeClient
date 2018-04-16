package com.brightcove.tvmazeclient.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.brightcove.tvmazeclient.BR;
import com.brightcove.tvmazeclient.R;
import com.brightcove.tvmazeclient.model.Schedule;
import com.brightcove.tvmazeclient.recyclerviewAdapter.RecyclerViewAdapter;
import com.brightcove.tvmazeclient.viewModel.ScheduleViewModel;
import com.brightcove.tvmazeclient.databinding.ActivityScheduleBinding;
import com.brightcove.tvmazeclient.recyclerviewAdapter.ClickHandler;
import com.brightcove.tvmazeclient.recyclerviewAdapter.ItemBinder;

import timber.log.Timber;
//TODO create an interface for this guy
public class ScheduleActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = ScheduleActivity.class.getName();
    ActivityScheduleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule);
        //TODO use and interface instead of concrete class
        ScheduleViewModel scheduleViewModel = new ScheduleViewModel();
        binding.setScheduleViewModel(scheduleViewModel);
        binding.setScheduleView(this);
        binding.rvSchedule.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterRecyclerView(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterRecyclerView(newText);
                return false;
            }
        });

        return true;
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

    public ClickHandler<Schedule> clickHandler = schedule -> {
        Timber.d(schedule.getId()+"");
        Intent intent = new Intent(this,ScheduleDetailActivity.class);
        intent.putExtra(ScheduleDetailActivity.SCHEDULE_ID,schedule.getId());
        startActivity(intent);
    };

    private void filterRecyclerView(String query) {
        RecyclerView.Adapter adapter = binding.rvSchedule.getAdapter();
        if(adapter instanceof RecyclerViewAdapter)
            ((RecyclerViewAdapter) adapter).getFilter().filter(query);
    }
}
