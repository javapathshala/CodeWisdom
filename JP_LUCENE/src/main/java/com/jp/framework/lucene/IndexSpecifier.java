/*
 * File: IndexSpecifier.java
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

/**
 * Description : To index specify the cached value
 * 
 * @author dimit.chadha
 * 
 */

public interface IndexSpecifier {
	 IndexSpecification get(Object value);
	
}
