package com.synch.ionic.plugin.Storage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class StorageConfigurationTest {

    StorageConfiguration storageConfiguration;
    @Before
    public void setUp(){
        storageConfiguration = new StorageConfiguration();
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        assertNotNull(storageConfiguration.clone());
    }
}
