package com.jp.koncept.core;

public class ArrayDeclaration {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Valid Declarations
		int keys[];
		int[] keys2;
		Thread threads[];
		Thread[] threads2;
		int[] keysSize = new int[4];
		int[][] twoDem = new int[4][];
		int[][] scores = { { 5, 2, 4, 7 }, { 9, 2 }, { 3, 4 } };

		int[] testScores = new int[] { 4, 7, 2 };

		// Invalid Declarations
		// int keys[4];
		// int[3] keys2;
		// int[] keysSize = new int[];
		// int[][] twoDem2=new int[][4];

	}

}
