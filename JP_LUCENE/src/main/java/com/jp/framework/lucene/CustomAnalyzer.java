/*
 * File: CustomeAnalyzer.java
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

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WhitespaceTokenizer;
import org.apache.lucene.util.Version;

/**
 * Description : To analyze the cached value
 * 
 * @author dimit.chadha
 * 
 */
public class CustomAnalyzer extends Analyzer {
	public TokenStream tokenStream(String fieldName, Reader reader) {
		// TokenStream result = new CustomTokenizer(reader);
		TokenStream result = new WhitespaceTokenizer(Version.LUCENE_35, reader);
		result = new LowerCaseFilter(Version.LUCENE_35, result);
		return result;
	}
}
