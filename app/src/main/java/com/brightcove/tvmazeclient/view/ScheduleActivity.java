package com.brightcove.tvmazeclient.view;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;

import com.brightcove.tvmazeclient.BR;
import com.brightcove.tvmazeclient.R;
import com.brightcove.tvmazeclient.model.Schedule;
import com.brightcove.tvmazeclient.recyclerviewAdapter.RecyclerViewAdapter;
import com.brightcove.tvmazeclient.viewModel.ScheduleViewModel;
import com.brightcove.tvmazeclient.databinding.ActivityScheduleBinding;
import com.brightcove.tvmazeclient.recyclerviewAdapter.ClickHandler;
import com.brightcove.tvmazeclient.recyclerviewAdapter.ItemBinder;

import java.util.Calendar;

import timber.log.Timber;
//TODO create an interface for this guy
public class ScheduleActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener{
    private static final String ACTIVITY_NAME = ScheduleActivity.class.getName();
    ActivityScheduleBinding mBinding;
    ScheduleViewModel mScheduleViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_schedule);
        //TODO use and interface instead of concrete class
        mScheduleViewModel = new ScheduleViewModel();
        mBinding.setScheduleViewModel(mScheduleViewModel);
        mBinding.setScheduleView(this);
        mBinding.rvSchedule.setLayoutManager(new LinearLayoutManager(this));
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

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void filterRecyclerView(String query) {
        RecyclerView.Adapter adapter = mBinding.rvSchedule.getAdapter();
        if(adapter instanceof RecyclerViewAdapter)
            ((RecyclerViewAdapter) adapter).getFilter().filter(query);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mScheduleViewModel.setScheduleDate(year,month,dayOfMonth);
    }

    public static class DatePickerFragment  extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            if(getActivity() instanceof DatePickerDialog.OnDateSetListener){
                ((DatePickerDialog.OnDateSetListener) getActivity()).onDateSet(view,year,month,day);
            } else
                throw new IllegalArgumentException(getString(R.string.implement_date_set_listener_exception));
        }
    }
}
