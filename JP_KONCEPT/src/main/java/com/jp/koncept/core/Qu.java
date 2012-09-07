package com.jp.koncept.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Transport {
}

class Plain extends Transport {
}

class Car extends Transport {
}

public class Qu {

	public static void handle(String[] a) {
		System.out.println("a:" + (a[0]));
		int[] c = new int[] { 1, 2 };
		int[] b = new int[] { 1, 2 };
		System.out.println(c.equals(b)); // false
		System.out.println(Arrays.equals(c, b)); // true

		Number[] na = new Long[3];
		System.out.println(na[0]);

		String[] sa = new String[] { "the", "thing", "that", "should", "not",
				"be" };
		List<String> asList = Arrays.asList(sa);
		asList.set(1, "4");
		sa[2] = "5";
		for (String s : sa) {
			System.out.print(s + " ");
		}
		for (String s : asList) {
			System.out.print(s + " ");
		}

	}

	public static void main(String... args) {
		handle(new String['a']);
	}
interface t{
	void m2() throws IOException;
	
}

class k implements t{

	

	public void m2() throws IOException{
		// TODO Auto-generated method stub
		
	}
	
}
}
