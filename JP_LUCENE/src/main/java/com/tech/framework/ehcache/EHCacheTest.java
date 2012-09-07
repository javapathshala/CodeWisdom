package com.tech.framework.ehcache;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.application.data.load.impl.CustomerDAO;
import com.application.data.load.impl.DataLoaderImpl;

/**
 * @author dimit
 * 
 */
public class EHCacheTest {

	List<CustomerDAO> custList;

	// private final String keyComponent = "CustomerList";
	// private static IndexedCache indexedCache = new IndexedCache(); // for
	// spring
	// // use
	// // @Resource
	// // & beanid
	// // in config
	// // file

	public static void main(String[] args) throws Exception {
		EHCacheTest ehCacheTest = new EHCacheTest();
		List<CustomerDAO> custList = ehCacheTest.createData();
		ehCacheTest.storeInCache(custList);

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CustomerDAO> createData() throws Exception {
		DataLoaderImpl dataloader = new DataLoaderImpl();
		custList = dataloader.loadData();
		System.out.println(custList.size());
		return custList;
	}

	/**
	 * 
	 * @param custList
	 */
	public void storeInCache(List<CustomerDAO> custList) {

		CacheManager manager = CacheManager.create();
		Cache testCache = manager.getCache("demo");

		Element element = new Element("key1", "value1");

		
		testCache.put(element);
		
		Element element2 = new Element("key2", custList);
		testCache.put(element2);
		testCache.put(element2);

		test(manager);
		// List<CustomerDAO> objectValue = (List<CustomerDAO>)
		// (testCache.get("key2").getObjectValue());
		//
		// System.out.println(objectValue.size());
	}

	private void test(CacheManager manager) {
		Cache cache = manager.getCache("demo");
		System.out.println(cache.get("key2").toString());
	}
}
