/**
 * 
 */
package com.tech.framework.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author dimit
 * 
 */
public class PsuedoCache implements Caching {

	// private IndexFactory indexFactory;
	private final CacheManager manager;

	public PsuedoCache() {
		this.manager = CacheManager.create();
		// this.indexFactory = new IndexFactory(CacheManager.create());
	}

	public synchronized void store(String cacheName, String key, Object value) {
		Cache testCache = manager.getCache(cacheName);
		testCache.putIfAbsent(new Element(key, value));
	}

	public synchronized Object find(String cacheName, String key) {
		Cache cache = manager.getCache(cacheName);
		Object objectValue = cache.get(key).getObjectValue();
		return objectValue;

	}

}
