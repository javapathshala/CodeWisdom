/*
 * File: IndexCache.java
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.store.RAMDirectory;

import com.tech.application.common.AbstractDTO;

/**
 * Description : To cache the index value
 * 
 * @author dimit.chadha
 * 
 */

public class IndexedCache {

	private Map<String, Index> indices;
	private Map<String, AbstractDTO> objectStore;
	private IndexFactory indexFactory;

	public IndexedCache() {
		this.indexFactory = new IndexFactory(new RAMDirectory());
		indices = new Hashtable<String, Index>();
		objectStore = new Hashtable<String, AbstractDTO>();
		BooleanQuery.setMaxClauseCount(Integer.MAX_VALUE);
	}

	public synchronized void store(String key, List<AbstractDTO> list, IndexSpecifier indexSpecifier) {
		List<IndexSpecification> specs = new ArrayList<IndexSpecification>();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			AbstractDTO customerBasic = (AbstractDTO) list.get(i);
			IndexSpecification spec = indexSpecifier.get(customerBasic);
			specs.add(spec);
			objectStore.put(spec.getKey(), customerBasic);
		}
		if (size != 0) {
			//removeFromCache();
			Index index = indexFactory.create(key, specs, indexSpecifier);
			indices.put(key, index);
		}
	}

	public List<AbstractDTO> find(String key, String query) throws IOException {
		Index index = (Index) indices.get(key);
		if (index != null) {
			Set<String> keys = index.query(query);
			List<AbstractDTO> values = new ArrayList<AbstractDTO>();
			for (Iterator<?> iterator = keys.iterator(); iterator.hasNext();) {
				values.add(objectStore.get(iterator.next()));
			}
			return values;
		}
		return new ArrayList<AbstractDTO>();
	}

	public void removeFromCache() {
		if (!objectStore.isEmpty()) {
			objectStore.clear();
		}
	}
}
