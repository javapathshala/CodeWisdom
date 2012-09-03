package com.jp.design.pattern.factory;


public class ParserFactory {

	private ParserFactory() {

	}

	public static ParserFactory newInstance() {
		return new ParserFactory();
	}

	public SuperParser newParser(String fileFormat) {
		SuperParser parser = null;
		if (fileFormat.equalsIgnoreCase("xml")) {
			parser = new xml();
		} else if (fileFormat.equalsIgnoreCase("sif")) {
			parser = new Sif();
		} else {
			throw new IllegalArgumentException("No parser for file format");
		}
		return parser;
	}
}
