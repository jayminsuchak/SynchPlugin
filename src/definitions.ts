import type { PluginListenerHandle } from '@capacitor/core';

export interface SynchPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;

  /**
  * Get the current GPS location of the device
  *
  * @since 1.0.0
  */
  getCurrentPosition(options?: PositionOptions): Promise<Position>;

  /**
   * Schedule one or more local notifications.
   *
   * @since 1.0.0
   */
  schedule(options: ScheduleOptions): Promise<ScheduleResult>;

  /**
     * Get a list of pending notifications.
     *
     * @since 1.0.0
     */
  getPending(): Promise<PendingResult>;

  /**
   * Cancel pending notifications.
   *
   * @since 1.0.0
   */
  cancel(options: CancelOptions): Promise<void>;

  /**
   * Listen for when notifications are displayed.
   *
   * @since 1.0.0
   */
  addListener(eventName: 'localNotificationReceived', listenerFunc: (notification: LocalNotificationSchema) => void): Promise<PluginListenerHandle> & PluginListenerHandle;
  /**
    * Query the current status of the network connection.
    *
    * @since 1.0.0
    */
  getStatus(): Promise<ConnectionStatus>;
  /**
   * Listen for changes in the network connection.
   *
   * @since 1.0.0
   */
  addNetworkListener(eventName: 'networkStatusChange', listenerFunc: ConnectionStatusChangeListener): Promise<PluginListenerHandle> & PluginListenerHandle;
  /**
   * Remove all listeners (including the network status changes) for this plugin.
   *
   * @since 1.0.0
   */
  removeAllListeners(): Promise<void>;

  /**
    * Get the value from storage of a given key.
    *
    * @since 1.0.0
    */
  get(options: GetOptions): Promise<GetResult>;
  /**
   * Set the value in storage for a given key.
   *
   * @since 1.0.0
   */
  set(options: SetOptions): Promise<void>;

  /**
    * Clear keys and values from storage.
    *
    * @since 1.0.0
    */
  clear(): Promise<void>;
}

export interface Position {
  /**
   * Creation timestamp for coords
   *
   * @since 1.0.0
   */
  timestamp: number;
  /**
   * The GPS coordinates along with the accuracy of the data
   *
   * @since 1.0.0
   */
  coords: {
    /**
     * Latitude in decimal degrees
     *
     * @since 1.0.0
     */
    latitude: number;
    /**
     * longitude in decimal degrees
     *
     * @since 1.0.0
     */
    longitude: number;
    /**
     * Accuracy level of the latitude and longitude coordinates in meters
     *
     * @since 1.0.0
     */
    accuracy: number;
    /**
     * Accuracy level of the altitude coordinate in meters, if available.
     *
     * Available on all iOS versions and on Android 8.0+.
     *
     * @since 1.0.0
     */
    altitudeAccuracy: number | null | undefined;
    /**
     * The altitude the user is at (if available)
     *
     * @since 1.0.0
     */
    altitude: number | null;
    /**
     * The speed the user is traveling (if available)
     *
     * @since 1.0.0
     */
    speed: number | null;
    /**
     * The heading the user is facing (if available)
     *
     * @since 1.0.0
     */
    heading: number | null;
  };
}

export interface ScheduleResult {
  /**
   * The list of scheduled notifications.
   *
   * @since 1.0.0
   */
  notifications: LocalNotificationDescriptor[];
}

export interface PendingResult {
  /**
   * The list of pending notifications.
   *
   * @since 1.0.0
   */
  notifications: PendingLocalNotificationSchema[];
}

export interface CancelOptions {
  /**
   * The list of notifications to cancel.
   *
   * @since 1.0.0
   */
  notifications: LocalNotificationDescriptor[];
}

/**
 * The object that describes a local notification.
 *
 * @since 1.0.0
 */
export interface LocalNotificationDescriptor {
  /**
   * The notification identifier.
   *
   * @since 1.0.0
   */
  id: number;
}
export interface ScheduleOptions {
  /**
   * The list of notifications to schedule.
   *
   * @since 1.0.0
   */
  notifications: LocalNotificationSchema[];
}

export interface PendingLocalNotificationSchema {
  /**
   * The title of the notification.
   *
   * @since 1.0.0
   */
  title: string;
  /**
   * The body of the notification, shown below the title.
   *
   * @since 1.0.0
   */
  body: string;
  /**
   * The notification identifier.
   *
   * @since 1.0.0
   */
  id: number;
  /**
   * Schedule this notification for a later time.
   *
   * @since 1.0.0
   */
  schedule?: Schedule;
  /**
   * Set extra data to store within this notification.
   *
   * @since 1.0.0
   */
  extra?: any;
}
export interface LocalNotificationSchema {
  /**
   * The title of the notification.
   *
   * @since 1.0.0
   */
  title: string;
  /**
   * The body of the notification, shown below the title.
   *
   * @since 1.0.0
   */
  body: string;
  /**
   * Sets a multiline text block for display in a big text notification style.
   *
   * @since 1.0.0
   */
  largeBody?: string;
  /**
   * Used to set the summary text detail in inbox and big text notification styles.
   *
   * Only available for Android.
   *
   * @since 1.0.0
   */
  summaryText?: string;
  /**
   * The notification identifier.
   *
   * @since 1.0.0
   */
  id: number;
  /**
   * Schedule this notification for a later time.
   *
   * @since 1.0.0
   */
  schedule?: Schedule;
  /**
   * Name of the audio file to play when this notification is displayed.
   *
   * Include the file extension with the filename.
   *
   * On iOS, the file should be in the app bundle.
   * On Android, the file should be in res/raw folder.
   *
   * Recommended format is `.wav` because is supported by both iOS and Android.
   *
   * Only available for iOS and Android 26+.
   *
   * @since 1.0.0
   */
  sound?: string;
  /**
   * Set a custom status bar icon.
   *
   * If set, this overrides the `smallIcon` option from Capacitor
   * configuration.
   *
   * Icons should be placed in your app's `res/drawable` folder. The value for
   * this option should be the drawable resource ID, which is the filename
   * without an extension.
   *
   * Only available for Android.
   *
   * @since 1.0.0
   */
  smallIcon?: string;
  /**
   * Set a large icon for notifications.
   *
   * Icons should be placed in your app's `res/drawable` folder. The value for
   * this option should be the drawable resource ID, which is the filename
   * without an extension.
   *
   * Only available for Android.
   *
   * @since 1.0.0
   */
  largeIcon?: string;
  /**
   * Set the color of the notification icon.
   *
   * Only available for Android.
   *
   * @since 1.0.0
   */
  iconColor?: string;
  /**
   * Associate an action type with this notification.
   *
   * @since 1.0.0
   */
  actionTypeId?: string;
  /**
   * Set extra data to store within this notification.
   *
   * @since 1.0.0
   */
  extra?: any;
  /**
   * Used to group multiple notifications.
   *
   * Sets `threadIdentifier` on the
   * [`UNMutableNotificationContent`](https://developer.apple.com/documentation/usernotifications/unmutablenotificationcontent).
   *
   * Only available for iOS.
   *
   * @since 1.0.0
   */
  threadIdentifier?: string;
  /**
   * The string this notification adds to the category's summary format string.
   *
   * Sets `summaryArgument` on the
   * [`UNMutableNotificationContent`](https://developer.apple.com/documentation/usernotifications/unmutablenotificationcontent).
   *
   * Only available for iOS 12+.
   *
   * @since 1.0.0
   */
  summaryArgument?: string;
  /**
   * Used to group multiple notifications.
   *
   * Calls `setGroup()` on
   * [`NotificationCompat.Builder`](https://developer.android.com/reference/androidx/core/app/NotificationCompat.Builder)
   * with the provided value.
   *
   * Only available for Android.
   *
   * @since 1.0.0
   */
  group?: string;
  /**
   * If true, this notification becomes the summary for a group of
   * notifications.
   *
   * Calls `setGroupSummary()` on
   * [`NotificationCompat.Builder`](https://developer.android.com/reference/androidx/core/app/NotificationCompat.Builder)
   * with the provided value.
   *
   * Only available for Android when using `group`.
   *
   * @since 1.0.0
   */
  groupSummary?: boolean;
  /**
   * Specifies the channel the notification should be delivered on.
   *
   * If channel with the given name does not exist then the notification will
   * not fire. If not provided, it will use the default channel.
   *
   * Calls `setChannelId()` on
   * [`NotificationCompat.Builder`](https://developer.android.com/reference/androidx/core/app/NotificationCompat.Builder)
   * with the provided value.
   *
   * Only available for Android 26+.
   *
   * @since 1.0.0
   */
  channelId?: string;
  /**
   * If true, the notification can't be swiped away.
   *
   * Calls `setOngoing()` on
   * [`NotificationCompat.Builder`](https://developer.android.com/reference/androidx/core/app/NotificationCompat.Builder)
   * with the provided value.
   *
   * Only available for Android.
   *
   * @since 1.0.0
   */
  ongoing?: boolean;
  /**
   * If true, the notification is canceled when the user clicks on it.
   *
   * Calls `setAutoCancel()` on
   * [`NotificationCompat.Builder`](https://developer.android.com/reference/androidx/core/app/NotificationCompat.Builder)
   * with the provided value.
   *
   * Only available for Android.
   *
   * @since 1.0.0
   */
  autoCancel?: boolean;
  /**
   * Sets a list of strings for display in an inbox style notification.
   *
   * Up to 5 strings are allowed.
   *
   * Only available for Android.
   *
   * @since 1.0.0
   */
  inboxList?: string[];
}

/**
 * Represents a schedule for a notification.
 *
 * Use either `at`, `on`, or `every` to schedule notifications.
 *
 * @since 1.0.0
 */
export interface Schedule {
  /**
   * Schedule a notification at a specific date and time.
   *
   * @since 1.0.0
   */
  at?: Date;
  /**
   * Repeat delivery of this notification at the date and time specified by
   * `at`.
   *
   * Only available for iOS and Android.
   *
   * @since 1.0.0
   */
  repeats?: boolean;
  /**
   * Allow this notification to fire while in [Doze](https://developer.android.com/training/monitoring-device-state/doze-standby)
   *
   * Only available for Android 23+.
   *
   * Note that these notifications can only fire [once per 9 minutes, per app](https://developer.android.com/training/monitoring-device-state/doze-standby#assessing_your_app).
   *
   * @since 1.0.0
   */
  allowWhileIdle?: boolean;
  /**
   * Schedule a notification on particular interval(s).
   *
   * This is similar to scheduling [cron](https://en.wikipedia.org/wiki/Cron)
   * jobs.
   *
   * Only available for iOS and Android.
   *
   * @since 1.0.0
   */
  on?: ScheduleOn;
  /**
   * Schedule a notification on a particular interval.
   *
   * @since 1.0.0
   */
  every?: ScheduleEvery;
  /**
   * Limit the number times a notification is delivered by the interval
   * specified by `every`.
   *
   * @since 1.0.0
   */
  count?: number;
}

export interface ScheduleOn {
  year?: number;
  month?: number;
  day?: number;
  hour?: number;
  minute?: number;
  second?: number;
}
export declare type ScheduleEvery = 'year' | 'month' | 'two-weeks' | 'week' | 'day' | 'hour' | 'minute' | 'second';


/**
 * Represents the state and type of the network connection.
 *
 * @since 1.0.0
 */
export interface ConnectionStatus {
  /**
   * Whether there is an active connection or not.
   *
   * @since 1.0.0
   */
  connected: boolean;
  /**
   * The type of network connection currently in use.
   *
   * If there is no active network connection, `connectionType` will be `'none'`.
   *
   * @since 1.0.0
   */
  connectionType: ConnectionType;
}


/**
 * Callback to receive the status change notifications.
 *
 * @since 1.0.0
 */
export declare type ConnectionStatusChangeListener = (status: ConnectionStatus) => void;
/**
 * The type of network connection that a device might have.
 *
 * @since 1.0.0
 */
export declare type ConnectionType = 'wifi' | 'cellular' | 'none' | 'unknown';
/**
 * @deprecated Use `ConnectionStatus`.
 * @since 1.0.0
 */
export declare type NetworkStatus = ConnectionStatus;
/**
 * @deprecated Use `ConnectionStatusChangeListener`.
 * @since 1.0.0
 */
export declare type NetworkStatusChangeCallback = ConnectionStatusChangeListener;

export interface GetOptions {
  /**
   * The key whose value to retrieve from storage.
   *
   * @since 1.0.0
   */
  key: string;
}
export interface GetResult {
  /**
   * The value from storage associated with the given key.
   *
   * If a value was not previously set or was removed, value will be `null`.
   *
   * @since 1.0.0
   */
  value: string | null;
}
export interface SetOptions {
  /**
   * The key to associate with the value being set in storage.
   *
   * @since 1.0.0
   */
  key: string;
  /**
   * The value to set in storage with the associated key.
   *
   * @since 1.0.0
   */
  value: string;
}