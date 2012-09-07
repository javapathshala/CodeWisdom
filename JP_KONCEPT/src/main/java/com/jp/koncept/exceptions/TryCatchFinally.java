/*
 * Created on May 18, 2006
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.jp.koncept.exceptions;

/**
 * @author Dimit_Chadha
 * 
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TryCatchFinally {

	public static void main(String[] args) {
		TryCatchFinally t = new TryCatchFinally();
		int value = t.test();
		System.out.println(value);

	}

	/**
	 * @return
	 */
	@SuppressWarnings("finally")
	private int test() {
		try {
			System.out.println("try");
			throw new Exception();
			// return 2;
		} catch (Exception e) {
			System.out.println("catch");
			return 3;
		} finally {
			System.out.println("final");
			return 45;
		}
		// return 5;
	}
}
