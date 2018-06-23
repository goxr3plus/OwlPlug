package com.dropsnorz.owlplug.store.model.json;

import com.dropsnorz.owlplug.store.model.StaticPluginStore;
import com.dropsnorz.owlplug.store.model.StaticStoreProduct;

public class StoreModelConverter {
	
	public static StaticPluginStore fromTO(PluginStoreTO storeTO){
	
		StaticPluginStore store = new StaticPluginStore();
		store.setName(storeTO.getName());
		store.setUrl(storeTO.getUrl());
		
		return store;
		
	}
	
	public static StaticStoreProduct fromTO(ProductTO productTO){
		
		StaticStoreProduct product = new StaticStoreProduct();
		product.setName(productTO.getName());
		product.setDownloadUrl(productTO.getDownloadUrl());
		product.setIconUrl(productTO.getIconUrl());
		product.setDescription(productTO.getDescription());
		
		return product;
	}

}