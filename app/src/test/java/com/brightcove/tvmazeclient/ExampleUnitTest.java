package com.brightcove.tvmazeclient;

import android.util.Log;

import org.junit.Test;

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
}