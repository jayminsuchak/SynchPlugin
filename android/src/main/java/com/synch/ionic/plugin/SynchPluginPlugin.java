package com.synch.ionic.plugin;

import android.Manifest;
import android.location.Location;
import android.net.NetworkInfo;
import android.os.Build;

import com.getcapacitor.Bridge;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginHandle;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.synch.ionic.plugin.GeoLocation.SynchPlugin;
import com.synch.ionic.plugin.LocalNotification.LocalNotification;
import com.synch.ionic.plugin.LocalNotification.LocalNotificationManager;
import com.synch.ionic.plugin.GeoLocation.LocationResultCallback;
import com.synch.ionic.plugin.LocalNotification.NotificationStorage;
import com.synch.ionic.plugin.Storage.Storage;
import com.synch.ionic.plugin.Storage.StorageConfiguration;

import org.json.JSONArray;

import java.util.List;

@CapacitorPlugin(name = "SynchPlugin",
        permissions = {
        @Permission(strings = { Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION }, alias = "location")
})
public class SynchPluginPlugin extends Plugin {

    private SynchPlugin implementation;

    private static Bridge staticBridge = null;
    private LocalNotificationManager manager;
    private NotificationStorage notificationStorage;

    private Network network;
    public static final String NETWORK_CHANGE_EVENT = "networkStatusChange";

    private Storage storage;

    @Override
    public void load() {
        implementation = new SynchPlugin(getContext());

        notificationStorage = new NotificationStorage(getContext());
        manager = new LocalNotificationManager(notificationStorage, getActivity(), getContext(), this.bridge.getConfig());
        manager.createNotificationChannel();
        staticBridge = this.bridge;

        network = new Network(getContext());
        network.setStatusChangeListener(this::updateNetworkStatus);

        storage = new Storage(getContext(), StorageConfiguration.DEFAULTS);

    }

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    /**
     * Gets a snapshot of the current device position if permission is granted. The call continues
     * in the {@link #completeCurrentPosition(PluginCall)} method if a permission request is required.
     *
     * @param call Plugin call
     */
    @PluginMethod
    public void getCurrentPosition(final PluginCall call) {
        if (getPermissionState("location") != PermissionState.GRANTED) {
            requestAllPermissions(call, "completeCurrentPosition");
        } else {
            getPosition(call);
        }
    }

    /**
     * Completes the getCurrentPosition plugin call after a permission request
     * @see #getCurrentPosition(PluginCall)
     * @param call the plugin call
     */
    @PermissionCallback
    private void completeCurrentPosition(PluginCall call) {
        if (getPermissionState("location") == PermissionState.GRANTED) {
           getPosition(call);
        } else {
            call.reject("Location permission was denied");
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getPosition(PluginCall call) {
        boolean enableHighAccuracy = call.getBoolean("enableHighAccuracy", false);
        int timeout = call.getInt("timeout", 10000);

        implementation.sendLocation(
                enableHighAccuracy,
                timeout,
                true,
                new LocationResultCallback() {
                    @Override
                    public void success(Location location) {
                        call.resolve(getJSObjectForLocation(location));
                    }

                    @Override
                    public void error(String message) {
                        call.reject(message);
                    }
                }
        );
    }

     /**
     * Schedule a notification call from JavaScript
     * Creates local notification in system.
     */
    @PluginMethod
    public void schedule(PluginCall call) {
        List<LocalNotification> localNotifications = LocalNotification.buildNotificationList(call);
        if (localNotifications == null) {
            return;
        }
        JSONArray ids = manager.schedule(call, localNotifications);
        if (ids != null) {
            notificationStorage.appendNotifications(localNotifications);
            JSObject result = new JSObject();
            JSArray jsArray = new JSArray();
            for (int i = 0; i < ids.length(); i++) {
                try {
                    JSObject notification = new JSObject().put("id", ids.getInt(i));
                    jsArray.put(notification);
                } catch (Exception ex) {}
            }
            result.put("notifications", jsArray);
            call.resolve(result);
        }
    }

    @PluginMethod
    public void cancel(PluginCall call) {
        manager.cancel(call);
    }

    @PluginMethod
    public void getPending(PluginCall call) {
        List<LocalNotification> notifications = notificationStorage.getSavedNotifications();
        JSObject result = LocalNotification.buildLocalNotificationPendingList(notifications);
        call.resolve(result);
    }

    @PluginMethod
    public void get(PluginCall call) {
        String key = call.getString("key");
        if (key == null) {
            call.reject("Must provide key");
            return;
        }

        String value = storage.get(key);

        JSObject ret = new JSObject();
        ret.put("value", value == null ? JSObject.NULL : value);
        call.resolve(ret);
    }

    @PluginMethod
    public void set(PluginCall call) {
        String key = call.getString("key");
        if (key == null) {
            call.reject("Must provide key");
            return;
        }

        String value = call.getString("value");
        storage.set(key, value);

        call.resolve();
    }

    @PluginMethod
    public void clear(PluginCall call) {
        storage.clear();
        call.resolve();
    }

    private JSObject getJSObjectForLocation(Location location) {
        JSObject ret = new JSObject();
        JSObject coords = new JSObject();
        ret.put("coords", coords);
        ret.put("timestamp", location.getTime());
        coords.put("latitude", location.getLatitude());
        coords.put("longitude", location.getLongitude());
        coords.put("accuracy", location.getAccuracy());
        coords.put("altitude", location.getAltitude());
        if (Build.VERSION.SDK_INT >= 26) {
            coords.put("altitudeAccuracy", location.getVerticalAccuracyMeters());
        }
        coords.put("speed", location.getSpeed());
        coords.put("heading", location.getBearing());
        return ret;
    }

    public static void fireReceived(JSObject notification) {
        SynchPluginPlugin localNotificationsPlugin = SynchPluginPlugin.getLocalNotificationsInstance();
        if (localNotificationsPlugin != null) {
            localNotificationsPlugin.notifyListeners("localNotificationReceived", notification, true);
        }
    }

    public static SynchPluginPlugin getLocalNotificationsInstance() {
        if (staticBridge != null && staticBridge.getWebView() != null) {
            PluginHandle handle = staticBridge.getPlugin("SynchPlugin");
            if (handle == null) {
                return null;
            }
            return (SynchPluginPlugin) handle.getInstance();
        }
        return null;
    }


    /**
     * Clean up callback to prevent leaks.
     */
    @Override
    protected void handleOnDestroy() {
        network.setStatusChangeListener(null);
    }

    /**
     * Get current network status information.
     * @param call
     */
    @PluginMethod
    public void getStatus(PluginCall call) {
        call.resolve(getStatusJSObject(network.getNetworkStatus()));
    }

    /**
     * Register the IntentReceiver on resume
     */
    @Override
    protected void handleOnResume() {
        network.startMonitoring(getActivity());
    }

    /**
     * Unregister the IntentReceiver on pause to avoid leaking it
     */
    @Override
    protected void handleOnPause() {
        network.stopMonitoring(getActivity());
    }

    private void updateNetworkStatus() {
        notifyListeners(NETWORK_CHANGE_EVENT, getStatusJSObject(network.getNetworkStatus()));
    }

    /**
     * Transform a NetworkInfo object into our JSObject for returning to client
     * @param info
     * @return
     */
    private JSObject getStatusJSObject(NetworkInfo info) {
        JSObject ret = new JSObject();
        if (info == null) {
            ret.put("connected", false);
            ret.put("connectionType", "none");
        } else {
            ret.put("connected", info.isConnected());
            ret.put("connectionType", getNormalizedTypeName(info));
        }
        return ret;
    }

    /**
     * Convert the Android-specific naming for network types into our cross-platform type
     * @param info
     * @return
     */
    private String getNormalizedTypeName(NetworkInfo info) {
        String typeName = info.getTypeName();
        if (typeName.equals("WIFI")) {
            return "wifi";
        }
        if (typeName.equals("MOBILE")) {
            return "cellular";
        }
        return "none";
    }




}
