package com.jp.app.parser.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlFileReader extends DefaultHandler {

	private String s;

	static Map<String, String> attrMap;

	private static final String COMMA = ",";

	private static StringBuffer outBuf;

	public static final char[] LINE_SEPARATOR = "\r\n".toCharArray();

	private static String pageId = null;

	private static String pageAlias = null;

	private static String fieldId = null;

	private static String type = null;

	private static int seq = 1;

	public XmlFileReader() {

	}

	/**
	 * @param fileList
	 * @param resultDir
	 * @return
	 * @throws IOException
	 */
	public String createFile(List<File> fileList, File resultDir)
			throws IOException {
		String reportFilePath = "";
		reportFilePath = resultDir.toString() + "\\" + "HelpContents"+ ".csv";
		createData(fileList, reportFilePath);
		return reportFilePath;
	}

	private void createData(List<File> fileList, String reportFilePath) {
		try {
			Iterator<File> files = fileList.iterator();
			PrintWriter printRecs = new PrintWriter(new FileWriter(new File(
					reportFilePath)));
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			seq = 1;
			while (files.hasNext()) {
				attrMap = null;
				attrMap = new LinkedHashMap<String, String>();
				File file = files.next();
				saxParser.parse(file, new XmlFileReader());
				outBuf = null;
				outBuf = new StringBuffer();
				printData(pageId, pageAlias, seq);
				seq=1;
				printRecs.print(outBuf.toString());
				printRecs.flush();
			}
			printRecs.close();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("jp:page")) {
			pageId = attributes.getValue("id");
		}else if((qName.equalsIgnoreCase("jp:staticselect"))){
			fieldId=attributes.getValue("name");
			System.out.println("Page id ::" +pageId +" static text:   "+ fieldId);
			type="text";
		}
		if (fieldId != null && type != null) {
			if ("text".equalsIgnoreCase(type)) {
				fieldId = "simple_" + fieldId + "_text";
			} else if ("boolean".equalsIgnoreCase(type)) {
				fieldId = "simple_" + fieldId + "_boolean";
			} else if ("file".equalsIgnoreCase(type)) {
				fieldId = "simple_" + fieldId + "_file";
			} else if ("INTEGER".equalsIgnoreCase(type)) {
				fieldId = "simple_" + fieldId + "_integer";
			}
			attrMap.put(fieldId, type);
			fieldId = type = null;
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase("jp:location")) {
			pageAlias = s;
		} 
		else if (qName.equalsIgnoreCase("jp:name")) {
			fieldId = s;
		} else if (qName.equalsIgnoreCase("jp:type")) {
			type = s;
		}
	}

	public void characters(char buf[], int offset, int len) throws SAXException {
		s = new String(buf, offset, len);
	}

	public void printData(String pageId, String pageAlias, int seq) {
		Set<String> keySet = attrMap.keySet();
		Iterator<String> itr = keySet.iterator();		
		while (itr.hasNext()) {
			String fieldId = itr.next();
			outBuf.append(pageId);// pageId
			outBuf.append(COMMA);
			outBuf.append(pageAlias); // page_alias
			outBuf.append(COMMA);
			outBuf.append(fieldId);// field Id
			outBuf.append(COMMA);
			outBuf.append(fieldId+ ": alias ");
			outBuf.append(COMMA); // field_alias
			outBuf.append("<p>Sample Help  of " + fieldId+" </p>");// existing help
			outBuf.append(COMMA);
			outBuf.append(COMMA);
			outBuf.append("2009-09-20-08.46.32.674003");// last changed
			outBuf.append(COMMA);
			outBuf.append("90220");
			outBuf.append(COMMA);
			outBuf.append("90190");
			outBuf.append(COMMA);
			outBuf.append("A");
			outBuf.append(COMMA);
			outBuf.append("Mr Bat101 Batman101");
			outBuf.append(COMMA);
			outBuf.append("Mr Bat102 Batman102");
			outBuf.append(COMMA);
			outBuf.append(seq);// seq no.
			outBuf.append(LINE_SEPARATOR);
			seq++;
		}
	}
}