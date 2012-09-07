package com.jp.koncept.core;

public class StaticMethod {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StaticMethod staticMethod = null;
		staticMethod.TestMethod();
	}

	private static void TestMethod() {
		System.out.println("Test Method");
	}

}
