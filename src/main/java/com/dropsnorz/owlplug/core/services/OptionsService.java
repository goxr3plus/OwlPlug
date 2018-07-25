package com.dropsnorz.owlplug.core.services;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dropsnorz.owlplug.ApplicationDefaults;
import com.dropsnorz.owlplug.auth.dao.GoogleCredentialDAO;
import com.dropsnorz.owlplug.auth.dao.UserAccountDAO;
import com.dropsnorz.owlplug.core.dao.PluginDAO;
import com.dropsnorz.owlplug.core.dao.PluginRepositoryDAO;

@Service
public class OptionsService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Preferences prefs;
	@Autowired
	private PluginRepositoryDAO pluginRepositoryDAO;
	@Autowired
	private PluginDAO pluginDAO;
	@Autowired
	private UserAccountDAO userAccountDAO;
	@Autowired
	private GoogleCredentialDAO googleCredentialDAO;


	@PostConstruct
	public void initialize() {

		//Init default options
		if(prefs.get(ApplicationDefaults.VST_DIRECTORY_KEY, null) == null) {
			prefs.put(ApplicationDefaults.VST_DIRECTORY_KEY, ApplicationDefaults.DEFAULT_VST_DIRECTORY);
		}
		if(prefs.get(ApplicationDefaults.VST2_DISCOVERY_ENABLED_KEY, null) == null) {
			prefs.putBoolean(ApplicationDefaults.VST2_DISCOVERY_ENABLED_KEY, Boolean.TRUE);
		}
		if(prefs.get(ApplicationDefaults.VST3_DISCOVERY_ENABLED_KEY, null) == null) {
			prefs.putBoolean(ApplicationDefaults.VST3_DISCOVERY_ENABLED_KEY, Boolean.FALSE);
		}
		if(prefs.get(ApplicationDefaults.SELECTED_ACCOUNT_KEY, null) == null) {
			prefs.putBoolean(ApplicationDefaults.SELECTED_ACCOUNT_KEY, Boolean.FALSE);
		}
	}


	public boolean clearAllUserData() {

		try {
			prefs.clear();
			pluginDAO.deleteAll();
			pluginRepositoryDAO.deleteAll();

			googleCredentialDAO.deleteAll();
			userAccountDAO.deleteAll();
			
			return true;


		} catch (BackingStoreException e) {
			log.error("Preferences cannot be updated", e);
			return false;
		}
	}

}
