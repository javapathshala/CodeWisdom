package com.jp.koncept.core;

class AA {
	int x = 5;
}

class BB extends AA {
	int x = 6;
}

public class DD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DD d = new EE();

		AA object = d.getObject();
		System.out.println(object.x);

	}

	public AA getObject() {
		System.out.println("DD");
		return new AA();
	}

}

class EE extends DD {
	public BB getObject() {
		System.out.println("EE");
		return new BB();
	}
}
