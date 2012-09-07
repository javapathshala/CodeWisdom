package com.jp.koncept.innerclass;

public class OuterClass {

	private String str = "Outer-String";

	public static void main(String[] args) {
		OuterClass outerClass = new OuterClass();
		outerClass.printStr();
		InnerClass innerClass = outerClass.new InnerClass();
		innerClass.innerMethod();
	}

	private void printStr() {
		System.out.println(str);
	}

	class InnerClass {
		public void innerMethod() {
			str = "Inner-String";
			System.out.println(str);
		}
	}

}

interface A {
	public void InterA();
}

class Outer {
	A a = new A() {
		public void InterA() {
			System.out.println("OKKKK");
		}
	};
}