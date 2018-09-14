package com.dropsnorz.owlplug.core.tasks.plugins.discovery;

import com.dropsnorz.owlplug.core.model.OSType;

public class PluginSyncTaskParameters {
	
	private OSType platform;
	private String pluginDirectory;
	private boolean findVST2;
	private boolean findVST3;
	
	public OSType getPlatform() {
		return platform;
	}
	
	public void setPlatform(OSType platform) {
		this.platform = platform;
	}
	
	public String getPluginDirectory() {
		return pluginDirectory;
	}
	
	public void setPluginDirectory(String pluginDirectory) {
		this.pluginDirectory = pluginDirectory;
	}
	
	public boolean isFindVST2() {
		return findVST2;
	}
	
	public void setFindVST2(boolean findVST2) {
		this.findVST2 = findVST2;
	}
	
	public boolean isFindVST3() {
		return findVST3;
	}
	
	public void setFindVST3(boolean findVST3) {
		this.findVST3 = findVST3;
	}
	
	
	

}