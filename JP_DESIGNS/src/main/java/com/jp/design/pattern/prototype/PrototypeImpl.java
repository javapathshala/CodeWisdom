/**
 * 
 */
package com.jp.design.pattern.prototype;

/**
 * @author dimit.chadha
 * 
 */
public class PrototypeImpl extends PrototypeFactory {

	int x;

	public PrototypeImpl(int x) {
		this.x = x;
	}

	@Override
	void prototypeFactory(int x) {
		this.x = x;

	}

	@Override
	void printValue() {
		System.out.println("Value :" + x);

	}

}
