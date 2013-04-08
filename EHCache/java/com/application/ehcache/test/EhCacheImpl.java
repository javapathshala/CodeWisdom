package com.application.ehcache.test;

import java.util.List;

import com.application.data.load.impl.CustomerDAO;
import com.application.data.load.impl.DataLoaderImpl;
import com.ecobis.framework.ehcache.Caching;
import com.ecobis.framework.ehcache.CobisCache;

/**
 * @author dimit
 * 
 */
public class EhCacheImpl {

	List<CustomerDAO> custList;
	private Caching caching = new CobisCache();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		EhCacheImpl ehCacheImpl = new EhCacheImpl();
		List<CustomerDAO> cacheData = ehCacheImpl.createData();
		ehCacheImpl.storeInCache("demo2", "key222", cacheData);
		List<CustomerDAO> objectValue = (List<CustomerDAO>) ehCacheImpl.findFromCache("demo2", "key222");
		//String  findFromCache = (String)ehCacheImpl.findFromCache("demo2", "key222");
//System.out.println(findFromCache);
		System.out.println(objectValue.get(0).getCustomerName());
	}

	public List<CustomerDAO> createData() throws Exception {
		DataLoaderImpl dataloader = new DataLoaderImpl();
		custList = dataloader.loadData();
		return custList;
	}

	public void storeInCache(String cacheName, String key, Object cacheData) {
		caching.store(cacheName, key, cacheData);
	}

	public Object findFromCache(String cacheName, String key) {
		return caching.find(cacheName, key);
	}
}
