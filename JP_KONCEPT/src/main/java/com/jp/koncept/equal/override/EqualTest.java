/*
 * Created on Oct 31, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.jp.koncept.equal.override;


/**
 * @author dimit
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EqualTest {

	public static void main(String[] args) {
		TestClass one = new TestClass(8);
		TestClass two = new TestClass(7);
		if (one.equals(two)) {
			System.out.println("one and two are equal");
		} else {
			System.out.println("one and two are not equal");
		}
	}
}
