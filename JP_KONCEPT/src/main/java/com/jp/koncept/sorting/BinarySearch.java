/*
 * File: BinarySearch.java
 * Date: 28-Feb-2013
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any 
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 */
package com.jp.koncept.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author dimit.chadha
 */
public class BinarySearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] sa = { "one", "two", "three", "four" };
		Arrays.sort(sa); // #1
		System.out.println("Sorted Array Elements");
		for (String s : sa) {
			System.out.println(s + " ");
		}
		System.out.println("#############");
		System.out.println("\none = " + Arrays.binarySearch(sa, "three")); // #2
		System.out.println("now reverse sort");
		ReSortComparator rs = new ReSortComparator(); // #3
		Arrays.sort(sa, rs);
		for (String s : sa) {
			System.out.print(s + " ");
		}
		System.out.println("\none = " + Arrays.binarySearch(sa, "one")); // #4
		System.out.println("one = " + Arrays.binarySearch(sa, "one", rs)); // #5
	}

	static class ReSortComparator implements Comparator<String> { // #6

		public int compare(String a, String b) {
			return b.compareTo(a); // #7
		}

	}
}
