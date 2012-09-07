/*
 * File: IndexFactory.java
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

import java.util.List;

import org.apache.lucene.store.RAMDirectory;

/**
 * Description : To make factory the cached value
 * 
 * @author dimit.chadha
 * 
 */
public class IndexFactory {

    private final RAMDirectory ramDirectory;

    public IndexFactory(RAMDirectory ramDirectory) {

        this.ramDirectory = ramDirectory;
    }

    @SuppressWarnings("rawtypes")
	public Index create(String key, List values, IndexSpecifier indexSpecifier) {
        return new Index(ramDirectory, key, values, indexSpecifier);
    }
}
