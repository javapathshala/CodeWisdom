package com.jp.koncept.opertors;

public class ObjectCompare {

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		Integer i = new Integer(0);
		Float f = new Float(0);
		System.out.println(i);
		System.out.println(f);
		System.out.println(i.equals(f));// false
		System.out.println(0 == 0.0);// true
	}
}
