package com.jp.koncept.core;

public class Loops {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] x = { 1, 2, 3 };
		SimpleFor(x);
		AdvFor(x);
		ExtraLoops();
	}

	static void AdvFor(int... x) {
		System.out.println("Advance For");
		for (int i : x) {
			System.out.println(i);
		}

	}

	static void SimpleFor(int[] x) {
		int len = x.length;
		System.out.println("Simple For");
		for (int i = 0; i < len; i++) {
			System.out.println(x[i]);
		}
	}

	private static void ExtraLoops() {
		whileLoop();
		dowhileLoop();
		withoutParaFor();
		switchCase();
		whileDoWhile();
		labeledLoops();
	}

	private static void whileLoop() {
		System.out.println("whileLoop()");
		int x = 0;
		while (x < 4) {
			System.out.println(x);
			x++;
		}
		x = 0;
		while (x < 4) {
			System.out.println(x);
			++x;
		}
	}

	private static void dowhileLoop() {
		System.out.println("dowhileLoop()");
		int x = 2;
		do {
			System.out.println(x);
		} while (x > 4);
	}

	private static void withoutParaFor() {
		System.out.println("withoutParaFor()");
		int i = 0;
		for (; i < 10; i++) {
			System.out.println(i);
		}

	}

	private static void switchCase() {

		final int x2 = 7;
		final int x3 = 8;
		final Integer x4 = 8;

		final Integer x1 = 5;
		String s = "a";

		if (x1 < 9)
			s += "b";
		switch (x1) {
		case 5:
			s += "c";
		case x2:
			s += "d";
			// case x4:
		case x3:
			s += "e";
			int x = 6;
			x -= 5;
			System.out.println(x);
		}
		System.out.println(s);

	}

	private static void whileDoWhile() {
		int i = 0, j = 0;
		while (i++ < 3)
			do
				System.out.print(j);
			while (j++ < 3);

		// /
		System.out.println("Second loop");
		int j1 = 0;
		do
			for (int i1 = 0; i1++ < 2;)
				System.out.print(i1);
		while (j1++ < 2);

	}

	private static void labeledLoops() {
		int i1 = 0, j1 = 9;
		do {
			if (j1 < 4) {
				break;
			} else if (j1-- < 7) {
				continue;
			}
			i1++;
		} while (i1++ < 7);
		System.out.print(i1 + "," + j1);
	}
}
