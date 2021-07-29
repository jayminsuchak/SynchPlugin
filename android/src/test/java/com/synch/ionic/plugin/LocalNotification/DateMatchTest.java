package com.synch.ionic.plugin.LocalNotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.getcapacitor.Bridge;
import com.getcapacitor.Plugin;
import com.synch.ionic.plugin.Network;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;


public class DateMatchTest {

    DateMatch dateMatch;
//    @Mock
//    Calendar calendar;

    @Before
    public void setUp() {
        dateMatch = new DateMatch();
    }

    @Test
    public void testSetYear(){
        dateMatch.setYear(2021);
        assertEquals("2021", dateMatch.getYear().toString());
    }

    @Test
    public void testSetMonth(){
        dateMatch.setMonth(1);
        assertEquals("1", dateMatch.getMonth().toString());
    }

    @Test
    public void testSetDay(){
        dateMatch.setDay(1);
        assertEquals("1", dateMatch.getDay().toString());
    }

    @Test
    public void testSetHour(){
        dateMatch.setHour(22);
        assertEquals("22", dateMatch.getHour().toString());
    }

    @Test
    public void testSetMinute(){
        dateMatch.setMinute(12);
        assertEquals("12", dateMatch.getMinute().toString());
    }

    @Test
    public void testSetSecond(){
        dateMatch.setSecond(40);
        assertEquals("40", dateMatch.getSecond().toString());
    }

    @Test
    public void testNextTriggerWithYear(){
        Date date = new Date();
        dateMatch.setUnit(Calendar.YEAR);
        assertNotNull(dateMatch.nextTrigger(date));
    }

    @Test
    public void testNextTriggerWithMonth(){
        Date date = new Date();
        dateMatch.setUnit(Calendar.MONTH);
        assertNotNull(dateMatch.nextTrigger(date));
    }

    @Test
    public void testNextTriggerWithDay(){
        Date date = new Date();
        dateMatch.setUnit(Calendar.DATE);
        assertNotNull(dateMatch.nextTrigger(date));
    }

    @Test
    public void testToString(){
        dateMatch.setYear(2021);
        dateMatch.setMonth(1);
       assertEquals("DateMatch{year=2021, month=1, day=null, hour=null, minute=null, second=null}",dateMatch.toString());
    }

}
