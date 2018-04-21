package com.brightcove.tvmazeclient.data.source.remote.api;

import com.brightcove.tvmazeclient.data.model.Schedule;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ali on 13-Apr-18.
 */

public interface TVmazeAPIService {
    //This defaults to current US date
    @GET("schedule")
    Observable<List<Schedule>> getSchedule();
    //This defaults to US
    @GET("schedule")
    Observable<List<Schedule>> getScheduleByDate(@Query("date") String airingDate);

    @GET("schedule")
    Observable<List<Schedule>> getScheduleByCountry(@Query("country") String countryCode);

    @GET("schedule")
    Observable<List<Schedule>> getScheduleByCountryAndDate(@Query("country") String countryCode,@Query("date") String airingDate);
}
