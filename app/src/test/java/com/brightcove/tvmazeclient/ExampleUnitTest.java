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
        String[] array = {"aa","bb","cc","dd"};
        String s = CustomTypeConverters.arrayToJsonString(array);
        System.out.println(s);
        String[] result = CustomTypeConverters.jsonStringToArray(s);
        System.out.println(Arrays.toString(result));
    }

    class MyPoint{
        int x;
        int y;
    }

    public void messThePoint(MyPoint p){
        p = new MyPoint();
        p.x=0;
        p.y=0;
    }

    @Test
    public void pointersTest(){
        MyPoint p = new MyPoint();
        p.x=3;
        p.y=4;
        messThePoint(p);
        assertTrue(p.x==3 && p.y==4);
    }
}