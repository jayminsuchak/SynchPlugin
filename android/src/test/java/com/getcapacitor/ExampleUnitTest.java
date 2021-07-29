package com.getcapacitor;

import android.content.Context;

import com.synch.ionic.plugin.SynchPluginPlugin;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    SynchPluginPlugin synchPluginPlugin;
    @Mock
    Context context;
    @Before
    public void setUp(){
        synchPluginPlugin = new SynchPluginPlugin();
    }
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void loadTest() throws Exception {
        when(synchPluginPlugin.getContext()).thenReturn(context);

        synchPluginPlugin.load();
    }

}
