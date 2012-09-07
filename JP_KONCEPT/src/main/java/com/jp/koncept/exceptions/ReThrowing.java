package com.jp.koncept.exceptions;

public class ReThrowing {
	public static void main(String[] args) throws Throwable {
		ReThrowing ret = new ReThrowing();
		try {
			ret.g(ret);
		} catch (Exception e) {
			System.err.println("Caught in main, e.printStackTrace()");
			e.printStackTrace();
		}
	}

	private void g(ReThrowing ret) throws Throwable {
		try {
			ret.f();
		} catch (Exception e) {
			System.err.println("Inside g(),e.printStackTrace()");
			e.printStackTrace();
			throw e;
		}
	}

	private void f() throws Exception {
		System.out.println("originating the exception in f()");
		throw new Exception("thrown from f()");
	}

}