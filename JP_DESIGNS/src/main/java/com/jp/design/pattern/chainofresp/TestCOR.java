/*
 * File: TestCOR.java
 * Date: 02-Apr-2013
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any 
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 */
package com.jp.design.pattern.chainofresp;

/**
 * @author dimit.chadha
 */
public class TestCOR {

	public static void main(String[] args) {
		// configure Chain of Responsibility
		Chain c1 = new NegativeProcessor();
		Chain c2 = new ZeroProcessor();
		Chain c3 = new PositiveProcessor();
		c1.setNext(c2);
		c2.setNext(c3);
		// calling chain of responsibility
		c1.process(new NumberRequest(99));
		c1.process(new NumberRequest(-30));
		c1.process(new NumberRequest(0));
		c1.process(new NumberRequest(100));
	}
}
