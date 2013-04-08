/**
 * Copywrite @ Dimit Chadha
 */
package com.ecobis.framework.ehcache;

/**
 * @author Dimit Chadha
 * 
 */
public interface Caching {

	public void store(String cacheName, String key, Object value);

	public Object find(String cacheName, String key);

}
