/**
 * Copywrite @ Dimit Chadha
 */
package com.jp.koncept.dto.conversion;

import java.math.BigDecimal;


/**
 * @author Dimit Chadha
 * 
 */
public class TestDtoConversion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LocationDTO locationDTO = new LocationDTO();
		locationDTO.setAddress(null);
		locationDTO.setCity("city");
		locationDTO.setCountry("India");
		locationDTO.setActive(true);
		CustomerDTO customer = new CustomerDTO();
		customer.setAge(451);
		customer.setName("dimit");
		customer.setAmount(new BigDecimal(4));
		locationDTO.setCustomerDTO(customer);
		LocationWebDTO locationWebDTO = new LocationWebDTO();
		try {
			DataMapper dtoConversion = new DataMapper();
			locationWebDTO = (LocationWebDTO) dtoConversion.mapData(locationDTO, locationWebDTO);
			System.out.println("locationWebDTO locationWebDTO");
			System.out.println(locationWebDTO.getAddress());
			System.out.println(locationWebDTO.getCity());
			System.out.println(locationWebDTO.getCountry());
			System.out.println(locationWebDTO.isActive());
			//CustomerDTO cust = locationWebDTO.getCustomerDTO();
//			System.out.println(cust.getAge());
//			System.out.println(cust.getName());
//			System.out.println(cust.getAmount());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// System.out.println("locationDTO locationDTO");
	// System.out.println(locationDTO.getAddress());
	// System.out.println(locationDTO.getCity());
	// System.out.println(locationDTO.getCountry());
	// CustomerDTO cust=locationDTO.getCustomerDTO();
	// System.out.println(cust.getAge());
	// System.out.println(cust.getName());
}
