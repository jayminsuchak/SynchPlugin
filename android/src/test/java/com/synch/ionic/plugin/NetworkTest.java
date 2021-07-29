package com.synch.ionic.plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.mockito.stubbing.OngoingStubbing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class NetworkTest {
    Network network;
    BroadcastReceiver receiver;
    @Mock Context context;
    @Mock ConnectivityManager connectivityManager;
    @Mock NetworkInfo networkInfo;
    @Mock AppCompatActivity appCompatActivity;
    @Mock Network.NetworkStatusChangeListener networkStatusChangeListener;
    
    @Before
    public void setUp() {
        when((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager);
        network = new Network(context);

        receiver =
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        networkStatusChangeListener.onNetworkStatusChanged();
                    }
                };

    }

    @Test
    public void testGetNetworkStatus(){
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);
        network.getNetworkStatus();
        verify(connectivityManager).getActiveNetworkInfo();
    }

    @Test
    public void testGetStatusChangeListener(){
        network.setStatusChangeListener(networkStatusChangeListener);
        assertEquals(network.getStatusChangeListener(), networkStatusChangeListener);
    }

    @Test
    public void testStartMonitoring(){
        network.startMonitoring(appCompatActivity);
        verify(appCompatActivity).registerReceiver(any(), any());
    }

    @Test
    public void testStopMonitoring(){
        network.stopMonitoring(appCompatActivity);
        verify(appCompatActivity).unregisterReceiver(any());
    }

}
