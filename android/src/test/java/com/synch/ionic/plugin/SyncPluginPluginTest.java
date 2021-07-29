package com.synch.ionic.plugin;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.getcapacitor.Bridge;
import com.getcapacitor.JSArray;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginHandle;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class SyncPluginPluginTest {

    SynchPluginPlugin synchPluginPlugin;
    @Mock PluginCall pluginCall;
    @Mock Bridge bridge;
    @Mock AppCompatActivity context;
    @Mock NotificationManager notificationManager;
    @Mock CapacitorPlugin capacitorPlugin;
    @Mock PluginHandle pluginHandle;
    @Mock SharedPreferences sharedPreferences;
    @Mock SharedPreferences.Editor editor;
    @Mock JSArray jsArray;

    @Before
    public void setUp(){
        initMocks(this);
        synchPluginPlugin = spy(SynchPluginPlugin.class);
        synchPluginPlugin.setBridge(bridge);
        synchPluginPlugin.setPluginHandle(pluginHandle);
        doReturn(context).when(synchPluginPlugin).getContext();
        doReturn(context).when(synchPluginPlugin).getActivity();
        doReturn(sharedPreferences).when(context).getSharedPreferences("CapacitorStorage", Activity.MODE_PRIVATE);
        doReturn(notificationManager).when(context).getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Test
    public void testLoad() {
        synchPluginPlugin.load();
        verify(context).getSystemService(Context.NOTIFICATION_SERVICE);
    }


    @Test(expected = NullPointerException.class)
    public void testGetCurrentPosition(){
        Permission[] permission  = new Permission[1];
        String[] strings = new String[1];
        strings[0] = "new Value";
        doReturn(capacitorPlugin).when(pluginHandle).getPluginAnnotation();
        doReturn(permission).when(capacitorPlugin).permissions();
        synchPluginPlugin.getCurrentPosition(pluginCall);
    }

    @Test
    public void testGet(){
        doReturn("value").when(sharedPreferences).getString("value", null);
        doReturn("value").when(pluginCall).getString("key");
        testLoad();
        synchPluginPlugin.get(pluginCall);
        verify(pluginCall).getString("key");
        verify(pluginCall).resolve(any());
    }

    @Test
    public void testGetWhenKeyNull(){
        synchPluginPlugin.get(pluginCall);
        verify(pluginCall).reject(anyString());
    }

    @Test
    public void testClear(){
        testLoad();
        doReturn(editor).when(sharedPreferences).edit();
        synchPluginPlugin.clear(pluginCall);
        verify(pluginCall).resolve();
    }


    @Test
    public void testSetWithNull(){
        synchPluginPlugin.set(pluginCall);
        verify(pluginCall).reject(anyString());
    }

    @Test
    public void testCancelReject() throws JSONException {
        testLoad();
        doReturn(jsArray).when(pluginCall).getArray("notifications");
        doReturn(new ArrayList()).when(jsArray).toList();
        synchPluginPlugin.cancel(pluginCall);
        verify(pluginCall).reject(anyString());
    }

    @Test(expected = NullPointerException.class)
    public void testCancelResolve() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",1);
        ArrayList<JSONObject> jsonObjectArrayList = new ArrayList<>();
        jsonObjectArrayList.add(jsonObject);
        testLoad();
        doReturn(jsArray).when(pluginCall).getArray("notifications");
        doReturn(jsonObjectArrayList).when(jsArray).toList();
        synchPluginPlugin.cancel(pluginCall);
        verify(pluginCall).getArray("notification");
    }

    @Test
    public void testScheduleNoNotification(){
        synchPluginPlugin.schedule(pluginCall);
        verify(pluginCall, never()).getArray("notification");
    }
}
