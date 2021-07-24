import { registerPlugin } from '@capacitor/core';

import type { SynchPluginPlugin } from './definitions';

const SynchPlugin = registerPlugin<SynchPluginPlugin>('SynchPlugin', {
  web: () => import('./web').then(m => new m.SynchPluginWeb()),
});

export * from './definitions';
export { SynchPlugin };
