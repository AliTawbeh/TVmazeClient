package com.brightcove.tvmazeclient.utils;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Created by Ali on 22-Apr-18.
 */

public final class CustomTypeConverters {
    @TypeConverter
    public static String arrayToJsonString(String[] arrayStr){
        String result = new GsonBuilder().create().toJson(arrayStr);
        return result;
    }

    @TypeConverter
    public static String[] jsonStringToArray(String array){
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(array);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        String[] result = new String[jsonArray.size()];
        for(int i=0; i<jsonArray.size(); i++)
            result[i]= jsonArray.get(i).getAsString();
        return result;
    }
}
