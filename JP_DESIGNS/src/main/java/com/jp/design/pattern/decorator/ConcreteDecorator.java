/**
 * 
 */
package com.jp.design.pattern.decorator;

/**
 * @author dimit.chadha
 */
public class ConcreteDecorator implements Decorator {

	IComponent component;

	public ConcreteDecorator(IComponent component) {
		super();
		this.component = component;
	}

	public void doStuff() {
		addedBehavior();
		System.out.println("######");
		component.doStuff();
	}

	public void addedBehavior() {
		System.out.println("Decorator does some stuff too");

	}

}
