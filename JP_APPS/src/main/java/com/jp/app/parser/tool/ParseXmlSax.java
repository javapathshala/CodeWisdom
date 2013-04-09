package com.jp.app.parser.tool;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseXmlSax extends DefaultHandler {

	protected static final String XML_FILE_NAME = "BankUserMaintainDetailsPage.xml";

	private String s;
	static Map<String, String> attrMap=new LinkedHashMap<String, String>();
	private String key;
	private String value;
	public static void main(String argv[]) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(new File(XML_FILE_NAME), new ParseXmlSax());
			printData();
		} catch (Throwable t) {
		}
		System.exit(0);
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(key!=null && value!=null){
			attrMap.put(key, value);
			key=value=null;
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase("jp:name")) {
				key=s;	
		} else if (qName.equalsIgnoreCase("jp:type")) {
				value=s;	
		} 

	}

	public void characters(char buf[], int offset, int len) throws SAXException {
		s = new String(buf, offset, len);
	}
	
	private static void printData() {
		Set<String> keySet = attrMap.keySet();
		Iterator<String> itr=keySet.iterator();
		while(itr.hasNext()){
			String key = itr.next();
				String name = attrMap.get(key);
					System.out.println("Key : "+key+"   "+"type :: "+name);	
		}
	}
}
