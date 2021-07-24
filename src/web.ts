import { WebPlugin } from '@capacitor/core';

import type { SynchPluginPlugin } from './definitions';

export class SynchPluginWeb extends WebPlugin implements SynchPluginPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
