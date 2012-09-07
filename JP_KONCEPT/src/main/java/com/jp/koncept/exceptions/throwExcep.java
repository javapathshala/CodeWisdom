package com.jp.koncept.exceptions;

public class throwExcep {

	public static void main(String[] args) {
		try {
			throwOne();
		} catch (IllegalAccessException e) {
			System.out.println("Caught : " + e);
		}
	}

	private static void throwOne() throws IllegalAccessException {
		System.out.println("Inside ThrowOne() ");
		throw new IllegalAccessException("demo");
	}
}
