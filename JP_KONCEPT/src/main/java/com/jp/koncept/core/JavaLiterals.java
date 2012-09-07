package com.jp.koncept.core;

public class JavaLiterals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int decimal = 5;
		int octa = 011;
		int hexa = 0xA;
		System.out.println("Decimal :: " + decimal);
		System.out.println("Octa :: " + octa);
		System.out.println("Hexa :: " + hexa);

		char letterN = '\u004e';
		System.out.println("Character Unicode :: " + letterN);

		// writing a double quote
		char quote = '\"';
		System.out.println("Quote :: " + quote);

	}
}