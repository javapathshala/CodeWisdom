package com.jp.koncept.core;

class A {
	String s1 = "A";

	void m2() {
		System.out.println("M2 in A");
	}

}

class B extends A {
	String s1 = "B";

	void m2() {
		System.out.println("M2 in B");
	}

}

class C extends B {
	String s1 = "C";

	void m2() {
		System.out.println("M2 in C");
	}

	C() {
		System.out.println("Super class Constructor");
	}

	static {
		System.out.println("Super class static block");
	}
}

class BaseChild extends C {
	String s1 = "D";

	BaseChild() {
		System.out.println("Child class Constructor");

	}

	static {
		System.out.println("Child class static block");
	}

	void m1() {
		System.out.println(this.s1 + ","); // 1
		System.out.println(((C) this).s1 + ","); // 2
		System.out.println(((B) this).s1 + ","); // 3
		System.out.println(((A) this).s1); // 4
		System.out.println(super.s1);

	}

	void m2() {
		System.out.println("M2 in D");
	}

	public static void main(String[] args) {
		new BaseChild().m1(); // 5
		new BaseChild().m3();
		BaseChild bc = new BaseChild();
	}

	private void m3() {
		System.out.println("");
		this.m2(); // 1
		((C) this).m2();
		((B) this).m2();
		((A) this).m2();
	}

}
