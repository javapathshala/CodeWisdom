/**
 * 
 */
package com.jp.design.pattern.strategy;

/**
 * @author dimit.chadha
 * 
 */
public class SubStrategy implements Strategy {

	
	public int operation(int a, int b) {
		System.out.println("Calling Sub Strategy Operation ()");
		return a - b;
	}

}
