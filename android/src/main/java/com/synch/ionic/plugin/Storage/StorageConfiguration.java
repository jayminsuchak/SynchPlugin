package com.synch.ionic.plugin.Storage;

import androidx.annotation.VisibleForTesting;

public class StorageConfiguration implements Cloneable {

    public static final StorageConfiguration DEFAULTS;

    static {
        DEFAULTS = new StorageConfiguration();
        DEFAULTS.group = "CapacitorStorage";
    }

    String group;

    @Override
    public StorageConfiguration clone() throws CloneNotSupportedException {
        return (StorageConfiguration) super.clone();
    }

//    @VisibleForTesting
//    public void setGroup(String group){
//        this.group = group;
//    }

    @VisibleForTesting
    public String getGroup(){
        return "CapacitorStorage";
    }

}
