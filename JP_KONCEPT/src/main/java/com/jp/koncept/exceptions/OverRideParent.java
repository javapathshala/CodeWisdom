package com.jp.koncept.exceptions;

public class OverRideParent {

	public void divideByZero(int num) throws ExceptionClass {
		System.out.println("Super Method");
		if (num == 0) {
			throw new ExceptionClass();
		} else {
			System.out.println(num / 0);
		}
	}

}
