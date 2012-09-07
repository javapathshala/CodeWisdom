package com.jp.koncept.innerclass;

public class AnonymousInnerClass {

	public void pop() {
		System.out.println("m1");
	}
}

class Food {
	AnonymousInnerClass in = new AnonymousInnerClass() {
		public void pop() {
			System.out.println("m2");
		}
	};
}
