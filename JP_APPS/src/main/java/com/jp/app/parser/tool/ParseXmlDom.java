package com.jp.app.parser.tool;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseXmlDom {
	static Document dom;

	private static Map<String, String> nameType = new LinkedHashMap<String, String>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			// parse using builder to get DOM representation of the XML file
			dom = db.parse("BankUserMaintainDetailsPage.xml");
			Element docEle = dom.getDocumentElement();
			String pageId = docEle.getAttribute("id");
			System.out.println("Page ID is :: " + pageId);
			parseDocument("jp:page", docEle);
			parseDocument("jp:textinput", docEle);
			parseDocument("jp:radiobutton", docEle);
			parseDocument("jp:checkbox", docEle);
			printData();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	private static void printData() {
		Set<String> keySet = nameType.keySet();
		Iterator<String> itr = keySet.iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			System.out.println("Key : " + key + "   " + "name :: "
					+ nameType.get(key));
		}

	}

	private static void parseDocument(String tagName, Element docEle) {
		NodeList nl = docEle.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);
				String name = getTextValue(el, "jp:name");
				String type = getTextValue(el, "jp:type");
				nameType.put(name, type);
			}
		}
	}

	/**
	 * I take a xml element and the tag name, look for the tag and get the text
	 * content i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is 'name' I will return John
	 */
	private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

}
