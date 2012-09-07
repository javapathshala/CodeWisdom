/*
 * File: QueryStringParser.java
 * Date: May 4, 2011
 *
 * This application is part of COCC banking solutions.
 * Its unauthorized use is explicitly prohibited as is any
 * alteration or addition made by any of its users without due written
 * consent from COCC.
 * This program is protected by copyright law and by
 * international conventions of intellectual property.  Its unauthorized
 * use gives COCC the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 */
package com.jp.framework.lucene;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

/**
 * Description : To parse the query for the cached value
 * 
 * @author dimit.chadha
 * 
 */
public class QueryStringParser {
	private Analyzer analyzer;

	public QueryStringParser(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	public ArrayList<String> parse(String queryString) {

		ArrayList<String> tokensList = new ArrayList<String>();

		Pattern pattern = Pattern.compile("(\\d{2})\\-*(\\d{2})\\-*(\\d{2})");

		Matcher matcher = pattern.matcher(queryString);

		queryString = matcher.replaceAll("$1$2$3");

		TokenStream tokenStream = analyzer.tokenStream("dummy",
				new StringReader(queryString));

//		OffsetAttribute offsetAttribute = tokenStream
//				.getAttribute(OffsetAttribute.class);
		CharTermAttribute charTermAttribute = tokenStream
				.getAttribute(CharTermAttribute.class);

		try {
			while (tokenStream.incrementToken()) {
				// int startOffset = offsetAttribute.startOffset();
				// int endOffset = offsetAttribute.endOffset();
				String term = charTermAttribute.toString();
				if (term == null) {
					break;
				}
				tokensList.add(term);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return tokensList;
	}

	// @SuppressWarnings("deprecation")
	// public ArrayList<Token> parse(String queryString) {
	//
	// ArrayList<Token> tokensList = new ArrayList<Token>();
	//
	// Pattern pattern = Pattern.compile("(\\d{2})\\-*(\\d{2})\\-*(\\d{2})");
	//
	// Matcher matcher = pattern.matcher(queryString);
	//
	// queryString = matcher.replaceAll("$1$2$3");
	//
	// TokenStream tokenStream = analyzer.tokenStream("dummy",
	// new StringReader(queryString));
	// try {
	// while (tokenStream.incrementToken()) {
	// Token token = tokenStream.getAttribute(Token.class);
	// // Token token = (Token)tokenStream.incrementToken();
	// if (token == null) {
	// break;
	// }
	// tokensList.add(token);
	// }
	// } catch (IOException e) {
	// throw new RuntimeException(e);
	// }
	// return tokensList;
	// }
}
