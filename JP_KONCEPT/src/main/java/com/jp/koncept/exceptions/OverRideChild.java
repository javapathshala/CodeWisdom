package com.jp.koncept.exceptions;

public class OverRideChild extends OverRideParent {

	public void divideByZero(int num) throws ExceptionClass {
		System.out.println("Child Method");
		if (num == 0) {
			throw new ExceptionClass();
		} else {
			int x = num / 0;
			System.out.println(x);
		}

	}

	public static void main(String[] args) {
		int num = 0;
		try {
			OverRideParent su = new OverRideChild();
			su.divideByZero(num);
		} catch (Exception ex) {
			throw new ExceptionClass(" Not Possible", num);
		}

	}
}
