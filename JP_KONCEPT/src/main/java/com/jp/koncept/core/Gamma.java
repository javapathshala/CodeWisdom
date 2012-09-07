package com.jp.koncept.core;

interface Foo {
}

class Alpha implements Foo {
}

class Beta extends Alpha {
}

class Gamma extends Beta {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Gamma g = new Gamma();
		Beta beta = new Beta(); // child Class Object
		Beta b1 = new Gamma();
		Alpha a = new Alpha();

		Foo f1 = beta;
		Foo f2 = a;

		Foo f3 = (Beta) g;

		// Beta b = (Beta)(Alpha)beta;

		// Foo f= (Gamma)beta; //class cast

	}

}
