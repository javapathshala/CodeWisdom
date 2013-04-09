/**
 * 
 */
package com.application.spring.search;

import java.util.List;

import com.jp.application.common.AbstractDTO;
import com.jp.framework.lucene.RunLucene;


/**
 * @author dimit
 * 
 */
public class CustomerSearchServiceImpl implements CustomerSearchService {

	private RunLucene runLucene = new RunLucene();

	public void storeResultsInCache(List<AbstractDTO> customerList) {

		runLucene.storeInCache(customerList);
	}

	public List<AbstractDTO> passResultsFromCache(String searchQuery) {
		return runLucene.findFromCache(searchQuery);
	}

//	public void removeResultsFromCache() {
//		luceneImpl.removeResultsFromCache();
//	}

}
