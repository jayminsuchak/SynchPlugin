package com.synch.ionic.plugin.GeoLocation;

import android.content.Context;

import com.google.android.gms.location.LocationServices;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SyncPluginTest {


    SynchPlugin synchPlugin;
    @Mock
    Context context;
    @Mock
    LocationResultCallback locationResultCallback;
    @Mock
    LocationServices locationServices;
    @Before
    public void setUp(){
        synchPlugin = new SynchPlugin(context);
    }

    @Test
    public void testSendLocation(){
//        LocationServices.
//        doReturn().when(locationServices).
//        synchPlugin.sendLocation(true, 2000, true,locationResultCallback);
    }
}
