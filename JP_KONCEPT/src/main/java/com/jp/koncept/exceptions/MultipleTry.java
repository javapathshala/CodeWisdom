package com.jp.koncept.exceptions;

public class MultipleTry {

	public static void main(String[] args) {

		try {
			int a = args.length;
			int b = 42 / a;
			System.out.println("a= " + a);
			System.out.println("b= " + b);
			try {
				if (a == 1) {
					a = a / (a - a);
				}
				if (a == 2) {
					int c[] = { 1 };
					c[42] = 99;
				}
			} catch (ArrayIndexOutOfBoundsException arrInd) {
				System.out.println("Array Index out of Bound");
			} catch (ArithmeticException arth) { // if not here then outer
				// catch block is called.
				System.out.println("Nested Divide By Zero");
			}

		} catch (ArithmeticException arth) {
			System.out.println("Divide By Zero");
		}
	}
}
