package com.jp.koncept.exceptions;

public class CaseExceptions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a, b, c, d, f, g, x;
		a = b = c = d = f = g = 0;
		x = 5;
		try {
			try {
				switch (x) {
				case 1:
					throw new Level1Exception();
				case 2:
					throw new Level2Exception();
				case 3:
					throw new Level3Exception();
				case 4:
					throw new Exception();
				}
				a++;// 1
			} catch (Level2Exception l2) {
				b++;// 0
			} finally {
				c++;// 1
			}

		} catch (Level1Exception l1) {
			d++;// 0
		} catch (Exception e) {
			f++;// 0
		} finally {
			g++;// 1
		}
		System.out.print(a + "," + b + "," + c + "," + d + "," + f + "," + g);
	}
}

class Level1Exception extends Exception {
	private static final long serialVersionUID = 1L;
}

class Level2Exception extends Level1Exception {
	private static final long serialVersionUID = 1L;
}

class Level3Exception extends Level2Exception {
	private static final long serialVersionUID = 1L;
}
