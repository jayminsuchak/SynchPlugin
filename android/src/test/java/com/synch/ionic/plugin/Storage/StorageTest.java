package com.synch.ionic.plugin.Storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.getcapacitor.Bridge;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class StorageTest {

    Storage storage;
    @Mock Context context;
    @Mock SharedPreferences sharedPreferences;
    @Mock SharedPreferences.Editor editor;

    @Before
    public void setUp(){
        initMocks(this);
        Bridge bridge = mock(Bridge.class);
        StorageConfiguration storageConfiguration = mock(StorageConfiguration.class);
        storageConfiguration.group = "CapacitorStorage";
        doReturn(context).when(bridge).getContext();
        doReturn(sharedPreferences).when(context).getSharedPreferences(storageConfiguration.group,Activity.MODE_PRIVATE);
        storage = new Storage(context, storageConfiguration);
    }

    @Test
    public void testSet(){
        doReturn(editor).when(sharedPreferences).edit();
        storage.set("key","value");
        verify(sharedPreferences).edit();
        verify(editor).apply();
    }

    @Test
    public void testGet(){
        doReturn("value").when(sharedPreferences).getString("key", null);
        assertEquals(storage.get("key"), "value");
        verify(sharedPreferences).getString(any(), any());
    }


    @Test
    public void testClear(){
        doReturn(editor).when(sharedPreferences).edit();
        storage.clear();
        verify(sharedPreferences).edit();
        verify(editor).apply();
    }
}




