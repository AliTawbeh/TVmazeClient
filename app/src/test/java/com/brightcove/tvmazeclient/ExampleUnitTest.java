package com.brightcove.tvmazeclient;

import android.util.Log;

import com.brightcove.tvmazeclient.utils.CustomTypeConverters;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final String TAG = "ExampleInstrumentedTest";
    @Test
    public void addition_isCorrect() throws Exception {
        Calendar c = Calendar.getInstance();
        Log.d(TAG, "calendar long value " + c.getTimeInMillis());
        c.set(2018,4,20);
        Log.d(TAG, "calendar long value after set" + c.getTimeInMillis());
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testingTypeConverters() throws Exception {
        int[] array = {1,2,3,4};
        String s = CustomTypeConverters.arrayToJsonString(array);
        System.out.println(s);
        int[] result = CustomTypeConverters.jsonStringToArray(s);
        System.out.println(Arrays.toString(result));
    }
}