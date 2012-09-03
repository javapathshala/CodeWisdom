/**
 * 
 */
package com.jp.design.pattern.strategy;

/**
 * @author dimit.chadha
 *
 */
public class StrategyRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	     Context context;
	     context = new Context(new AddStrategy());
	     System.out.println(context.executeStrategy(8,5));
	     
	     
	     context = new Context(new SubStrategy());
	     System.out.println(context.executeStrategy(8,5));
	     
	     
	     context = new Context(new MulStrategy());
	     System.out.println(context.executeStrategy(8,5));
	     

	}

}
