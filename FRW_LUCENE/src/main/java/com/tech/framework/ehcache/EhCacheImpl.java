package com.tech.framework.ehcache;

import java.util.List;

import com.application.data.load.impl.CustomerDAO;
import com.application.data.load.impl.DataLoaderImpl;

/**
 * @author dimit
 * 
 */
public class EhCacheImpl {

	List<CustomerDAO> custList;
	private Caching caching = new PsuedoCache();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		EhCacheImpl ehCacheImpl = new EhCacheImpl();
		List<CustomerDAO> cacheData = ehCacheImpl.createData();
		ehCacheImpl.storeInCache("demo2", "key222", "dimit");
		List<CustomerDAO> objectValue = (List<CustomerDAO>) ehCacheImpl.findFromCache("demo2", "key222");

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
