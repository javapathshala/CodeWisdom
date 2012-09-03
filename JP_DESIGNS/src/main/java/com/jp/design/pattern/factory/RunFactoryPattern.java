package com.jp.design.pattern.factory;

public class RunFactoryPattern {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParserFactory factory = ParserFactory.newInstance();
		SuperParser parser = factory.newParser("sif");
		System.out.println(parser.getClass().toString());
		parser.parse();
	}

}
