package com.jp.koncept.exceptions;

public class GenExceptions {

	public static void main(String[] args) {
		try {
			Error error = new Error();
			Exception exception = new Exception();
			System.out.println((exception instanceof Throwable) + ",");
			System.out.println(error instanceof Throwable);

			throw new Exception("My Exception");
		} catch (Exception e) {

			System.err.println("Caught Exception");
			System.err.println("getMessage():" + e.getMessage());
			System.err.println("getLocalizedMessage():"
					+ e.getLocalizedMessage());
			System.err.println("toString():" + e);
			System.err.println("printStackTrace():");
			e.printStackTrace();
		}
	}
}
