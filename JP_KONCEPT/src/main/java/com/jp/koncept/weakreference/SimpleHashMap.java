/**
 * 
 */
package com.jp.koncept.weakreference;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dimit.chadha
 * 
 */
public class SimpleHashMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> hashMap = new HashMap<String, String>();
		// Create a key for the map, keep the strong reference
		String strongRef = new String("key");
		hashMap.put(strongRef, "value");
		// Run the GC and check if the key is still there.
		System.gc();
		System.out.println(hashMap.get("key"));

		// Now, null out the strong reference and try again the same above.
		strongRef = null;
		System.gc();
		System.out.println(hashMap.get("key"));
	}

}
