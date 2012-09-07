package com.jp.koncept.exceptions;

public class ExceptionImpl {
	static int num = 5;

	public static void main(String args[]) {
		ExceptionImpl impl = new ExceptionImpl();
		try {
			impl.divideByZero(num);
		} catch (ExceptionClass ex) {
			System.out.println(ex.getLocalizedMessage());
			ex.printStackTrace();
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}
	}

	private void divideByZero(int num) {
		if (num == 0) {
			throw new ArithmeticException();
		} else {
			throw new ExceptionClass();
		}

	}
}
