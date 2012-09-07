/*
 * Created on Oct 31, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.jp.koncept.equal.override;;

/**
 * @author dimit
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestClass {
	private int testValue;

	TestClass(int val) {
		testValue = val;
	}

	public int getMoofValue() {
		return testValue;
	}

	public boolean equals(Object o) {
		if ((o instanceof TestClass)
				&& (((TestClass) o).getMoofValue() == this.testValue)) {
			return true;
		} else {
			return false;
		}
	}
}
