/**
 * 
 */
package com.jp.design.pattern.decorator;

/**
 * @author dimit.chadha
 *
 */
public class Component implements IComponent {


	/* (non-Javadoc)
	 * @see com.jp.application.pattern.decorator.IComponent#doStuff()
	 */

	public void doStuff() {
		System.out.println("Normal Do Suff");
	}

}
