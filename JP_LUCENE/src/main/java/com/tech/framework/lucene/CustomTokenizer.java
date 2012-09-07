/*
 * File: CustomTokenizer.java
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
package com.tech.framework.lucene;

import java.io.Reader;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.WhitespaceTokenizer;
/**
 * Description : To customize the cached value
 * 
 * @author dimit.chadha
 * 
 */
class CustomTokenizer{// extends WhitespaceTokenizer {
//    public CustomTokenizer(Reader in) {
//        super(in);
//    }
//
//    protected boolean isTokenChar(char c) {
//        return Pattern.matches("[a-zA-Z0-9\\*\\?]", Character.toString(c));
//    }
}
