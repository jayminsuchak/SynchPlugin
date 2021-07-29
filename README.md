# synch-plugin

This plugin is responsible for getting cureent location of device.

## Install

```bash
npm install synch-plugin
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`getCurrentPosition(...)`](#getcurrentposition)
* [`schedule(...)`](#schedule)
* [`getPending()`](#getpending)
* [`cancel(...)`](#cancel)
* [`addListener(...)`](#addlistener)
* [`getStatus()`](#getstatus)
* [`addNetworkListener(...)`](#addnetworklistener)
* [`removeAllListeners()`](#removealllisteners)
* [`get(...)`](#get)
* [`set(...)`](#set)
* [`clear()`](#clear)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => any
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>any</code>

--------------------


### getCurrentPosition(...)

```typescript
getCurrentPosition(options?: any) => any
```

Get the current GPS location of the device

| Param         | Type             |
| ------------- | ---------------- |
| **`options`** | <code>any</code> |

**Returns:** <code>any</code>

**Since:** 1.0.0

--------------------


### schedule(...)

```typescript
schedule(options: ScheduleOptions) => any
```

<a href="#schedule">Schedule</a> one or more local notifications.

| Param         | Type                                                        |
| ------------- | ----------------------------------------------------------- |
| **`options`** | <code><a href="#scheduleoptions">ScheduleOptions</a></code> |

**Returns:** <code>any</code>

**Since:** 1.0.0

--------------------


### getPending()

```typescript
getPending() => any
```

Get a list of pending notifications.

**Returns:** <code>any</code>

**Since:** 1.0.0

--------------------


### cancel(...)

```typescript
cancel(options: CancelOptions) => any
```

Cancel pending notifications.

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code><a href="#canceloptions">CancelOptions</a></code> |

**Returns:** <code>any</code>

**Since:** 1.0.0

--------------------


### addListener(...)

```typescript
addListener(eventName: 'localNotificationReceived', listenerFunc: (notification: LocalNotificationSchema) => void) => Promise<PluginListenerHandle> & PluginListenerHandle
```

Listen for when notifications are displayed.

| Param              | Type                                                                                                   |
| ------------------ | ------------------------------------------------------------------------------------------------------ |
| **`eventName`**    | <code>"localNotificationReceived"</code>                                                               |
| **`listenerFunc`** | <code>(notification: <a href="#localnotificationschema">LocalNotificationSchema</a>) =&gt; void</code> |

**Returns:** <code>any</code>

**Since:** 1.0.0

--------------------


### getStatus()

```typescript
getStatus() => any
```

Query the current status of the network connection.

**Returns:** <code>any</code>

**Since:** 1.0.0

--------------------


### addNetworkListener(...)

```typescript
addNetworkListener(eventName: 'networkStatusChange', listenerFunc: ConnectionStatusChangeListener) => Promise<PluginListenerHandle> & PluginListenerHandle
```

Listen for changes in the network connection.

| Param              | Type                                                                               |
| ------------------ | ---------------------------------------------------------------------------------- |
| **`eventName`**    | <code>"networkStatusChange"</code>                                                 |
| **`listenerFunc`** | <code>(status: <a href="#connectionstatus">ConnectionStatus</a>) =&gt; void</code> |

**Returns:** <code>any</code>

**Since:** 1.0.0

--------------------


### removeAllListeners()

```typescript
removeAllListeners() => any
```

Remove all listeners (including the network status changes) for this plugin.

**Returns:** <code>any</code>

**Since:** 1.0.0

--------------------


### get(...)

```typescript
get(options: GetOptions) => any
```

Get the value from storage of a given key.

| Param         | Type                                              |
| ------------- | ------------------------------------------------- |
| **`options`** | <code><a href="#getoptions">GetOptions</a></code> |

**Returns:** <code>any</code>

**Since:** 1.0.0

--------------------


### set(...)

```typescript
set(options: SetOptions) => any
```

Set the value in storage for a given key.

| Param         | Type                                              |
| ------------- | ------------------------------------------------- |
| **`options`** | <code><a href="#setoptions">SetOptions</a></code> |

**Returns:** <code>any</code>

**Since:** 1.0.0

--------------------


### clear()

```typescript
clear() => any
```

Clear keys and values from storage.

**Returns:** <code>any</code>

**Since:** 1.0.0

--------------------


### Interfaces


#### Position

| Prop            | Type                                                                                                                                                                                | Description                                             | Since |
| --------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------- | ----- |
| **`timestamp`** | <code>number</code>                                                                                                                                                                 | Creation timestamp for coords                           | 1.0.0 |
| **`coords`**    | <code>{ latitude: number; longitude: number; accuracy: number; altitudeAccuracy: number \| null; altitude: number \| null; speed: number \| null; heading: number \| null; }</code> | The GPS coordinates along with the accuracy of the data | 1.0.0 |


#### ScheduleOptions

| Prop                | Type            | Description                            | Since |
| ------------------- | --------------- | -------------------------------------- | ----- |
| **`notifications`** | <code>{}</code> | The list of notifications to schedule. | 1.0.0 |


#### LocalNotificationSchema

| Prop                   | Type                                          | Description                                                                                                                                                                                                                                                                                                                                                                                                 | Since |
| ---------------------- | --------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----- |
| **`title`**            | <code>string</code>                           | The title of the notification.                                                                                                                                                                                                                                                                                                                                                                              | 1.0.0 |
| **`body`**             | <code>string</code>                           | The body of the notification, shown below the title.                                                                                                                                                                                                                                                                                                                                                        | 1.0.0 |
| **`largeBody`**        | <code>string</code>                           | Sets a multiline text block for display in a big text notification style.                                                                                                                                                                                                                                                                                                                                   | 1.0.0 |
| **`summaryText`**      | <code>string</code>                           | Used to set the summary text detail in inbox and big text notification styles. Only available for Android.                                                                                                                                                                                                                                                                                                  | 1.0.0 |
| **`id`**               | <code>number</code>                           | The notification identifier.                                                                                                                                                                                                                                                                                                                                                                                | 1.0.0 |
| **`schedule`**         | <code><a href="#schedule">Schedule</a></code> | <a href="#schedule">Schedule</a> this notification for a later time.                                                                                                                                                                                                                                                                                                                                        | 1.0.0 |
| **`sound`**            | <code>string</code>                           | Name of the audio file to play when this notification is displayed. Include the file extension with the filename. On iOS, the file should be in the app bundle. On Android, the file should be in res/raw folder. Recommended format is `.wav` because is supported by both iOS and Android. Only available for iOS and Android 26+.                                                                        | 1.0.0 |
| **`smallIcon`**        | <code>string</code>                           | Set a custom status bar icon. If set, this overrides the `smallIcon` option from Capacitor configuration. Icons should be placed in your app's `res/drawable` folder. The value for this option should be the drawable resource ID, which is the filename without an extension. Only available for Android.                                                                                                 | 1.0.0 |
| **`largeIcon`**        | <code>string</code>                           | Set a large icon for notifications. Icons should be placed in your app's `res/drawable` folder. The value for this option should be the drawable resource ID, which is the filename without an extension. Only available for Android.                                                                                                                                                                       | 1.0.0 |
| **`iconColor`**        | <code>string</code>                           | Set the color of the notification icon. Only available for Android.                                                                                                                                                                                                                                                                                                                                         | 1.0.0 |
| **`actionTypeId`**     | <code>string</code>                           | Associate an action type with this notification.                                                                                                                                                                                                                                                                                                                                                            | 1.0.0 |
| **`extra`**            | <code>any</code>                              | Set extra data to store within this notification.                                                                                                                                                                                                                                                                                                                                                           | 1.0.0 |
| **`threadIdentifier`** | <code>string</code>                           | Used to group multiple notifications. Sets `threadIdentifier` on the [`UNMutableNotificationContent`](https://developer.apple.com/documentation/usernotifications/unmutablenotificationcontent). Only available for iOS.                                                                                                                                                                                    | 1.0.0 |
| **`summaryArgument`**  | <code>string</code>                           | The string this notification adds to the category's summary format string. Sets `summaryArgument` on the [`UNMutableNotificationContent`](https://developer.apple.com/documentation/usernotifications/unmutablenotificationcontent). Only available for iOS 12+.                                                                                                                                            | 1.0.0 |
| **`group`**            | <code>string</code>                           | Used to group multiple notifications. Calls `setGroup()` on [`NotificationCompat.Builder`](https://developer.android.com/reference/androidx/core/app/NotificationCompat.Builder) with the provided value. Only available for Android.                                                                                                                                                                       | 1.0.0 |
| **`groupSummary`**     | <code>boolean</code>                          | If true, this notification becomes the summary for a group of notifications. Calls `setGroupSummary()` on [`NotificationCompat.Builder`](https://developer.android.com/reference/androidx/core/app/NotificationCompat.Builder) with the provided value. Only available for Android when using `group`.                                                                                                      | 1.0.0 |
| **`channelId`**        | <code>string</code>                           | Specifies the channel the notification should be delivered on. If channel with the given name does not exist then the notification will not fire. If not provided, it will use the default channel. Calls `setChannelId()` on [`NotificationCompat.Builder`](https://developer.android.com/reference/androidx/core/app/NotificationCompat.Builder) with the provided value. Only available for Android 26+. | 1.0.0 |
| **`ongoing`**          | <code>boolean</code>                          | If true, the notification can't be swiped away. Calls `setOngoing()` on [`NotificationCompat.Builder`](https://developer.android.com/reference/androidx/core/app/NotificationCompat.Builder) with the provided value. Only available for Android.                                                                                                                                                           | 1.0.0 |
| **`autoCancel`**       | <code>boolean</code>                          | If true, the notification is canceled when the user clicks on it. Calls `setAutoCancel()` on [`NotificationCompat.Builder`](https://developer.android.com/reference/androidx/core/app/NotificationCompat.Builder) with the provided value. Only available for Android.                                                                                                                                      | 1.0.0 |
| **`inboxList`**        | <code>{}</code>                               | Sets a list of strings for display in an inbox style notification. Up to 5 strings are allowed. Only available for Android.                                                                                                                                                                                                                                                                                 | 1.0.0 |


#### Schedule

Represents a schedule for a notification.

Use either `at`, `on`, or `every` to schedule notifications.

| Prop                 | Type                                                                                               | Description                                                                                                                                                                                                                                                                                                                             | Since |
| -------------------- | -------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----- |
| **`at`**             | <code>any</code>                                                                                   | <a href="#schedule">Schedule</a> a notification at a specific date and time.                                                                                                                                                                                                                                                            | 1.0.0 |
| **`repeats`**        | <code>boolean</code>                                                                               | Repeat delivery of this notification at the date and time specified by `at`. Only available for iOS and Android.                                                                                                                                                                                                                        | 1.0.0 |
| **`allowWhileIdle`** | <code>boolean</code>                                                                               | Allow this notification to fire while in [Doze](https://developer.android.com/training/monitoring-device-state/doze-standby) Only available for Android 23+. Note that these notifications can only fire [once per 9 minutes, per app](https://developer.android.com/training/monitoring-device-state/doze-standby#assessing_your_app). | 1.0.0 |
| **`on`**             | <code><a href="#scheduleon">ScheduleOn</a></code>                                                  | <a href="#schedule">Schedule</a> a notification on particular interval(s). This is similar to scheduling [cron](https://en.wikipedia.org/wiki/Cron) jobs. Only available for iOS and Android.                                                                                                                                           | 1.0.0 |
| **`every`**          | <code>"year" \| "month" \| "two-weeks" \| "week" \| "day" \| "hour" \| "minute" \| "second"</code> | <a href="#schedule">Schedule</a> a notification on a particular interval.                                                                                                                                                                                                                                                               | 1.0.0 |
| **`count`**          | <code>number</code>                                                                                | Limit the number times a notification is delivered by the interval specified by `every`.                                                                                                                                                                                                                                                | 1.0.0 |


#### ScheduleOn

| Prop         | Type                |
| ------------ | ------------------- |
| **`year`**   | <code>number</code> |
| **`month`**  | <code>number</code> |
| **`day`**    | <code>number</code> |
| **`hour`**   | <code>number</code> |
| **`minute`** | <code>number</code> |
| **`second`** | <code>number</code> |


#### ScheduleResult

| Prop                | Type            | Description                          | Since |
| ------------------- | --------------- | ------------------------------------ | ----- |
| **`notifications`** | <code>{}</code> | The list of scheduled notifications. | 1.0.0 |


#### LocalNotificationDescriptor

The object that describes a local notification.

| Prop     | Type                | Description                  | Since |
| -------- | ------------------- | ---------------------------- | ----- |
| **`id`** | <code>number</code> | The notification identifier. | 1.0.0 |


#### PendingResult

| Prop                | Type            | Description                        | Since |
| ------------------- | --------------- | ---------------------------------- | ----- |
| **`notifications`** | <code>{}</code> | The list of pending notifications. | 1.0.0 |


#### PendingLocalNotificationSchema

| Prop           | Type                                          | Description                                                          | Since |
| -------------- | --------------------------------------------- | -------------------------------------------------------------------- | ----- |
| **`title`**    | <code>string</code>                           | The title of the notification.                                       | 1.0.0 |
| **`body`**     | <code>string</code>                           | The body of the notification, shown below the title.                 | 1.0.0 |
| **`id`**       | <code>number</code>                           | The notification identifier.                                         | 1.0.0 |
| **`schedule`** | <code><a href="#schedule">Schedule</a></code> | <a href="#schedule">Schedule</a> this notification for a later time. | 1.0.0 |
| **`extra`**    | <code>any</code>                              | Set extra data to store within this notification.                    | 1.0.0 |


#### CancelOptions

| Prop                | Type            | Description                          | Since |
| ------------------- | --------------- | ------------------------------------ | ----- |
| **`notifications`** | <code>{}</code> | The list of notifications to cancel. | 1.0.0 |


#### PluginListenerHandle

| Prop         | Type                      |
| ------------ | ------------------------- |
| **`remove`** | <code>() =&gt; any</code> |


#### ConnectionStatus

Represents the state and type of the network connection.

| Prop                 | Type                                                     | Description                                                                                                                   | Since |
| -------------------- | -------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------- | ----- |
| **`connected`**      | <code>boolean</code>                                     | Whether there is an active connection or not.                                                                                 | 1.0.0 |
| **`connectionType`** | <code>"wifi" \| "cellular" \| "none" \| "unknown"</code> | The type of network connection currently in use. If there is no active network connection, `connectionType` will be `'none'`. | 1.0.0 |


#### GetOptions

| Prop      | Type                | Description                                   | Since |
| --------- | ------------------- | --------------------------------------------- | ----- |
| **`key`** | <code>string</code> | The key whose value to retrieve from storage. | 1.0.0 |


#### GetResult

| Prop        | Type                        | Description                                                                                                                   | Since |
| ----------- | --------------------------- | ----------------------------------------------------------------------------------------------------------------------------- | ----- |
| **`value`** | <code>string \| null</code> | The value from storage associated with the given key. If a value was not previously set or was removed, value will be `null`. | 1.0.0 |


#### SetOptions

| Prop        | Type                | Description                                               | Since |
| ----------- | ------------------- | --------------------------------------------------------- | ----- |
| **`key`**   | <code>string</code> | The key to associate with the value being set in storage. | 1.0.0 |
| **`value`** | <code>string</code> | The value to set in storage with the associated key.      | 1.0.0 |

</docgen-api>
