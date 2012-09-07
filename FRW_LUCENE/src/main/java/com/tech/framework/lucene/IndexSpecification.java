/*
 * File: IndexSpecification.java
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

/**
 * Description : To specify the cached value
 * 
 * @author dimit.chadha
 * 
 */
public class IndexSpecification {
	 private final String key;

	    public String getKey() {
	        return key;
	    }

	    public String[] getIndexFields() {
	        return indexFields;
	    }

	    private final String[] indexFields;

	    public IndexSpecification(String key, String[] indexFields) {
	        this.key = key;
	        this.indexFields = indexFields;
	    }
	    
}
