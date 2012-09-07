package com.jp.koncept.exceptions;

public class ThowsException {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			method();
		} catch (Exception e) {
			System.out.println("exception in main");
		}finally{
			System.out.println("finally in main");
		}
		System.out.println("main finished");
	}

	public static void method() throws Exception {
		try {
			throw new Exception();
		} finally {
			System.out.println("finally in method");

		}

	}

}
