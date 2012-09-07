package com.application.data.load.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.application.data.load.framework.LoadDataHelper;


/**
 * @author dimit
 *s
 */
public class DataLoaderImpl {
	
	public static void main(String[] args) {
		try{
			DataLoaderImpl dataloader=new DataLoaderImpl();
			List<CustomerDAO> customerList=dataloader.loadData();
			printCustList(customerList);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private static void printCustList(List<CustomerDAO> customerList) {
		Iterator<CustomerDAO> itr=customerList.iterator();
		while(itr.hasNext()){
			CustomerDAO dao = itr.next();
			LocationDAO locationDAO = dao.getLocationDAO();
			System.out.println(dao.getCustId()+","+dao.getCustomerName()+","+locationDAO.getAddress()+","+locationDAO.getCity()+","+locationDAO.getCountry());
			System.out.println("-----------------------------------------------------------------------------------");
		}
		
	}

	public List<CustomerDAO> loadData() throws Exception {
		LoadDataHelper csvLoader = new LoadDataHelper();
		Object[] resultList = null;
		resultList = csvLoader.getCSVData("TestData.csv", CustomerDAO.class);
		CustomerDAO[] customers = (CustomerDAO[]) resultList;
		List<String> attributeList = csvLoader.getAttributeList();
		csvLoader.printAttributes(attributeList);
		List<CustomerDAO> custList = Arrays.asList(customers);
		return custList;
	}
}
