/**
 * 
 */
package com.jp.design.pattern.decorator;

/**
 * @author dimit.chadha
 *
 */
public class RunDecoratorPattern {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IComponent comp = new Component();
		comp.doStuff();
	
		Decorator decorator = new ConcreteDecorator(comp);
		decorator.doStuff();

	}
	


}
