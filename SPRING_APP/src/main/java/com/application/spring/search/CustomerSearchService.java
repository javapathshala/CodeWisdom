/**
 * 
 */
package com.application.spring.search;

import java.util.List;

import com.application.data.load.impl.CustomerDAO;
import com.jp.application.common.AbstractDTO;


/**
 * @author dimit
 *
 */
public interface CustomerSearchService {
	
	void storeResultsInCache(List<AbstractDTO> customerList);
	
	List<AbstractDTO> passResultsFromCache(String searchQuery);
    
	//void removeResultsFromCache();

}
