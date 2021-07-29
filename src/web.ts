import { WebPlugin } from '@capacitor/core';
import type { PluginListenerHandle } from '@capacitor/core';

import type {
  CancelOptions, ConnectionStatus, ConnectionStatusChangeListener,
  GetResult, PendingResult, Position,
  ScheduleResult, SetOptions, SynchPluginPlugin
} from './definitions';

export class SynchPluginWeb extends WebPlugin implements SynchPluginPlugin {

  getPending(): Promise<PendingResult> {
    throw this.unavailable('Notification does not support in Browser.');
  }

  cancel(_options: CancelOptions): Promise<void> {
    throw this.unavailable('Notification does not support in Browser.');
  }

  clear(): Promise<void> {
    throw this.unavailable('Storage does not support in Browser.');
  }

  set(_options: SetOptions): Promise<void> {
    throw this.unavailable('Storage does not support in Browser.');
  }

  async get(_options: any): Promise<GetResult> {
    throw this.unavailable('Storage does not support in Browser.');
  }

  getStatus(): Promise<ConnectionStatus> {
    throw this.unavailable('Browser does not support the Network Information API');
  }

  addNetworkListener(_eventName: 'networkStatusChange', _listenerFunc: ConnectionStatusChangeListener): Promise<PluginListenerHandle> & PluginListenerHandle {
    throw this.unavailable('Browser does not support the Network Information API');
  }

  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async getCurrentPosition(options: any): Promise<Position> {
    return new Promise((resolve, reject) => {
      navigator.geolocation.getCurrentPosition(pos => {
        return resolve(pos);
      }, err => {
        reject(err);
      }, Object.assign({ enableHighAccuracy: false, timeout: 10000, maximumAge: 0 }, options));
    });
  }

  async schedule(options: { notifications: any[]; }): Promise<ScheduleResult> {
    return options;
  }
}
