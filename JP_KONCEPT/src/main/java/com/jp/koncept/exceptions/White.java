package com.jp.koncept.exceptions;

public class White {

	public static void main(String[] args) {
		White white = new White();
		int a, b, d, f;
		a = b = d = f = 0;
		try {
			white.m1();
			a++;//0
		} catch (ColorException e) {
			b++;//1
		}
		try {
			white.m2();
			d++;//1
		} catch (WhiteException e) {
			f++;//0
			
		}
		System.out.print(a + "," + b + "," + d + "," + f);

	}

	void m1() throws ColorException {
		throw new ColorException();
	}

	void m2() throws WhiteException {
		throw new WhiteException();
	}

}

class ColorException extends Exception {
	private static final long serialVersionUID = 1L;
}

class WhiteException extends ColorException {
	private static final long serialVersionUID = 1L;
}
