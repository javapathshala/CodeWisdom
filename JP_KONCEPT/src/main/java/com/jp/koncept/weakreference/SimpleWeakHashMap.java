/**
 * 
 */
package com.jp.koncept.weakreference;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author dimit.chadha
 * 
 */
public class SimpleWeakHashMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> weakHashMap = new WeakHashMap<String, String>();
		// Create a key for the map, keep the strong reference
		String strongRef = new String("key");
		weakHashMap.put(strongRef, "value");
		// Run the GC and check if the key is still there.
		System.gc();
		System.out.println(weakHashMap.get("key"));

		// Now, null out the strong reference and try again the same above.
		strongRef = null;
		System.gc();
		System.out.println(weakHashMap.get("key"));
	}

}
