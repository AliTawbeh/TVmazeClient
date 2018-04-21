package com.brightcove.tvmazeclient.data.source.remote.api;

import com.brightcove.tvmazeclient.data.model.Schedule;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ali on 13-Apr-18.
 */

public class TVmazeAPIClient {
    private static final String TVMAZE_API_BASE_URL = "http://api.tvmaze.com/";

    private static TVmazeAPIClient instance;
    private TVmazeAPIService tvMazeAPIService;

    private TVmazeAPIClient() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(TVMAZE_API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        tvMazeAPIService = retrofit.create(TVmazeAPIService.class);
    }

    public static TVmazeAPIClient getInstance(){
        if(instance == null)
             instance = new TVmazeAPIClient();
        return instance;
    }

    public Observable<List<Schedule>> getUSSchedule(){
        return tvMazeAPIService.getSchedule();
    }

    public Observable<List<Schedule>> getUSScheduleByDate(String date){
        return tvMazeAPIService.getScheduleByDate(date);
    }
}
