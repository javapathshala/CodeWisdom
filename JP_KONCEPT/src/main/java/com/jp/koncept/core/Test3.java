package com.jp.koncept.core;

class Pa {
	static int i = 10;
}

public class Test3 extends Pa {

	Test3() {
		i = 19;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// new Test3();
		System.out.println(i);
		// Float i = new Float(10);
		// System.out.println(i);
		// String red = "10";
		// String green = "20";
		// String blue = "1a";
		// int r = Integer.parseInt(red, 16);
		// int g = Integer.parseInt(green);
		// int b = Integer.parseInt(blue, 16);
		// System.out.println("Red = " + r + " Green =" + g + " Blue=" + b);
		// String str = "ss";
		// StringBuffer strb = new StringBuffer(str);
		//
		// if (!str.equals(strb)) {
		// System.out.println("OK");
		// }
		//
		// int j = 012;
		// System.out.println("dsad,444 ---- " + j);
		// System.out.println(j);
		// String str1="One Man,One Vote";
		// System.out.println(str1.lastIndexOf("One",10));
		//		
		// Boolean t=new Boolean("true");
		// Boolean t1=new Boolean("sda");
		// System.out.println(""+t+t1);
	}
}
