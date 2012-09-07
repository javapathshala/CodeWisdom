package com.tech.framework.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.application.data.load.impl.CustomerDAO;
import com.application.data.load.impl.DataLoaderImpl;
import com.application.data.load.impl.LocationDAO;
import com.jp.application.common.AbstractDTO;

/**
 * @author dimit
 * 
 */
public class RunLucene {

	List<CustomerDAO> custList;

	private final String keyComponent = "CustomerList";
	private static IndexedCache indexedCache = new IndexedCache(); // for spring
																	// use
																	// @Resource
																	// & beanid
																	// in config
																	// file

	public static void main(String[] args) throws Exception {
		RunLucene luceneImpl = new RunLucene();
		List<CustomerDAO> custList = luceneImpl.createData();
		luceneImpl.storeInCache(new ArrayList<AbstractDTO>(custList));
		String searchQuery = "dimit40";
		List<AbstractDTO> results = luceneImpl.findFromCache(searchQuery);
		Iterator<AbstractDTO> itr = results.iterator();
		System.out.println("Search Result for Query : " + searchQuery);
		while (itr.hasNext()) {
			CustomerDAO dao = (CustomerDAO) itr.next();
			System.out.println(dao.getCustomerName());
			System.out
					.println("##########################################################################################");
			LocationDAO locationDAO = dao.getLocationDAO();
			System.out.println(dao.getCustId() + "," + dao.getCustomerName()
					+ "," + locationDAO.getAddress() + ","
					+ locationDAO.getCity() + "," + locationDAO.getCountry());
			System.out
					.println("-----------------------------------------------------------------------------------");
		}
	}

	public List<CustomerDAO> createData() throws Exception {
		DataLoaderImpl dataloader = new DataLoaderImpl();
		custList = dataloader.loadData();
		return custList;
	}

	public void storeInCache(List<AbstractDTO> list) {
		// List<AbstractDTO> custList = new ArrayList<AbstractDTO>(list);
		removeResultsFromCache();
		indexedCache.store(keyComponent, list, new IndexSpecifier() {
			public IndexSpecification get(Object value) {
				if (value instanceof CustomerDAO) {
					CustomerDAO result = (CustomerDAO) value;
					return new IndexSpecification(result.getCustomerName(),
							getIndexFields(result));
				} else {
					throw new RuntimeException();
				}
			}

			private String[] getIndexFields(CustomerDAO customer) {
				return new String[] { customer.getCustomerName() };
			}
		});
	}

	public List<AbstractDTO> findFromCache(String searchQuery) {
		List<AbstractDTO> results = new ArrayList<AbstractDTO>();
		try {
			results = indexedCache.find(keyComponent, searchQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}

	public void removeResultsFromCache() {
		indexedCache.removeFromCache();

	}
}
