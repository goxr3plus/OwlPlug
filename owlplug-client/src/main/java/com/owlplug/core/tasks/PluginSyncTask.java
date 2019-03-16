package com.owlplug.core.tasks;

import com.owlplug.core.dao.PluginDAO;
import com.owlplug.core.model.Plugin;
import com.owlplug.core.model.PluginFormat;
import com.owlplug.core.tasks.plugins.discovery.PluginFileCollector;
import com.owlplug.core.tasks.plugins.discovery.PluginSyncTaskParameters;
import com.owlplug.core.tasks.plugins.discovery.fileformats.PluginFile;
import com.owlplug.host.NativeHostJNI;
import com.owlplug.host.NativePlugin;
import java.util.ArrayList;

public class PluginSyncTask extends AbstractTask {

  protected PluginDAO pluginDAO;
  private PluginSyncTaskParameters parameters;

  /**
   * Creates a new SyncPluginTask.
   * 
   * @param parameters Task Parameters
   * @param pluginDAO  pluginDAO
   */
  public PluginSyncTask(PluginSyncTaskParameters parameters, PluginDAO pluginDAO) {
    this.parameters = parameters;
    this.pluginDAO = pluginDAO;

    setName("Sync Plugins");
  }

  @Override
  protected TaskResult call() throws Exception {

    this.updateMessage("Syncing Plugins...");
    this.updateProgress(0, 2);

    try {
      
      ArrayList<PluginFile> collectedPluginFiles = new ArrayList<>();
      
      PluginFileCollector pluginCollector = new PluginFileCollector(parameters.getPlatform());
      String vstDirectory = parameters.getVstDirectory();
      String vst3Directory = parameters.getVst3Directory();

      this.updateProgress(1, 3);

      
      if (parameters.isFindVst2()) {
        collectedPluginFiles.addAll(pluginCollector.collect(vstDirectory, PluginFormat.VST2));
      }

      if (parameters.isFindVst3()) {
        collectedPluginFiles.addAll(pluginCollector.collect(vst3Directory, PluginFormat.VST3));
      }
      this.updateProgress(2, 3);
      
      ArrayList<Plugin> discoveredPlugins = new ArrayList<>();
      for (PluginFile pluginFile : collectedPluginFiles) {
        Plugin plugin = pluginFile.toPlugin();
        if (plugin != null) {
          discoveredPlugins.add(plugin);
        }
        
        System.out.println("Exploring: " + plugin.getPath());
        NativeHostJNI jni = new NativeHostJNI();
        NativePlugin nativePlugin = jni.loadPlugin(plugin.getPath());
        
        if(nativePlugin != null) {
          plugin.setVersion(nativePlugin.getVersion());
        }
        
      }

      pluginDAO.deleteAll();
      pluginDAO.saveAll(discoveredPlugins);

      this.updateProgress(3, 3);
      this.updateMessage("Plugins synchronized");

      return success();

    } catch (Exception e) {
      this.updateMessage("Plugins synchronization failed. Check your plugin directory.");
      throw new TaskException("Plugins synchronization failed");

    }

  }
}
