export interface SynchPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
