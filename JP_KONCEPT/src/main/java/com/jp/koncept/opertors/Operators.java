package com.jp.koncept.opertors;

public class Operators {

	public static void main(String args[]) {
		int i1 = 0xffffffff;
		int i2 = i1 << 1;
		int i3 = i1 >> 1;
		int i4 = i1 >>> 1;
		System.out.print(Integer.toHexString(i2) + ",");
		System.out.print(Integer.toHexString(i3) + ",");
		System.out.print(Integer.toHexString(i4));

	}
}
