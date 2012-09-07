/**
 * 
 */
package com.jp.app.compareCSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author dimit.chadha
 * 
 */
public class CompareCSV {

	FCFLogger log = FCFLogger.getLogger(CompareCSV.class);

	Map<String, UpDTO> upMap = new HashMap<String, UpDTO>();
	Map<String, CustomerDTO> custMap = new HashMap<String, CustomerDTO>();
	Map<String, PaymentDTO> payMap = new HashMap<String, PaymentDTO>();
	Map<String, SubsriptionDTO> subMap = new HashMap<String, SubsriptionDTO>();
	Map<String, ContactDTO> contMap = new HashMap<String, ContactDTO>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CompareCSV compareCSV = new CompareCSV();
		compareCSV.readFile();
		compareCSV.compareFiles();

	}

	private void compareFiles() {
		Set<String> keySet = upMap.keySet();
		Iterator<String> emails = keySet.iterator();
		while (emails.hasNext()) {
			String email = emails.next();
			// match email of upload file with username of customer
			if (custMap.containsKey(email)) {
				log.info("Email " + email + " found in Customer Account File");
				CustomerDTO customerDTO = custMap.get(email);
				UpDTO upDTO = upMap.get(email);
				boolean flag = compareUpWithCust(customerDTO, upDTO);
				if (flag) {
					log.info("Upload File & Customer Account File Record Matches");
				}
				// customer & payment matcher

				PaymentDTO payDto = payMap.get(customerDTO.getCustId());
				boolean flagUpPay = compareUpWithPay(upDTO, payDto);
				if (flagUpPay) {
					log.info("Upload File & Payment File Record Matches");
				}
				String accountNumber = customerDTO.getAccountNumber();
				SubsriptionDTO subsriptionDTO = subMap.get(accountNumber);
				boolean flagUpSub = compareUpWithSub(upDTO, subsriptionDTO);
				if (flagUpSub) {
					log.info("Upload File & Subscription File Record Matches");
				}

				ContactDTO contDto = contMap.get(customerDTO.getCustId());
				boolean flagUpContact = compareUpWithContact(upDTO, contDto);
				if (flagUpPay) {
					log.info("Upload File & Payment File Record Matches");
				}

			} else {
				log.error("Email " + email
						+ " not found in Customer Account File");
			}

		}
	}

	private boolean compareUpWithCust(CustomerDTO customerDTO, UpDTO upDTO) {
		boolean flag = true;
		if (!upDTO.getCompany().equals(customerDTO.getCompany())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upDTO.getUserName().equals(customerDTO.getUserName())) {
			log.error("userName doesnot match");
			flag = false;
		}
		if (upDTO.getPassword().equals(customerDTO.getPassword())) {
			log.error("Password doesnot match");
			flag = false;
		}
		if (upDTO.getUpComments().equals(customerDTO.getCustComments())) {
			log.error("Comments doesnot match");
			flag = false;
		}
		if (upDTO.getAutorenewalDisabled().equals(customerDTO.getAutoPay())) {
			log.error("Auto Renewable & Auto Pay doesnot match");
			flag = false;
		}
		return flag;
	}

	private boolean compareUpWithPay(UpDTO upDTO, PaymentDTO payDto) {
		boolean flag = true;
		NameDTO upName = upDTO.getUpName();
		NameDTO payName = payDto.getPayName();
		if (!upName.getfName().equals(payName.getfName())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upName.getLname().equals(payName.getLname())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		AddressDTO upAddress = upDTO.getUpAddress();
		AddressDTO payAddress = payDto.getPayAddress();
		if (!upAddress.getAddress1().equals(payAddress.getAddress1())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upAddress.getAddress2().equals(payAddress.getAddress2())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upAddress.getCity().equals(payAddress.getCity())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upAddress.getState().equals(payAddress.getState())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upAddress.getPostCode().equals(payAddress.getPostCode())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upAddress.getCountry().equals(payAddress.getCountry())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upAddress.getPhone().equals(payAddress.getPhone())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upAddress.getEmail().equals(payAddress.getEmail())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		CreditCardExpiry upCreditCardExpiry = upDTO.getUpCreditCardExpiry();
		CreditCardExpiry payCreditCardExpiry = payDto.getPayCreditCardExpiry();

		if (!upCreditCardExpiry.getCreditCardMasked().equals(
				payCreditCardExpiry.getCreditCardMasked())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upCreditCardExpiry.getCreditCardType().equals(
				payCreditCardExpiry.getCreditCardType())) {
			log.error("Company Doesnot match");
			flag = false;
		}

		if (!upCreditCardExpiry.getExpirationDate().equals(
				payCreditCardExpiry.getExpirationDate())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		return flag;
	}

	private boolean compareUpWithSub(UpDTO upDTO, SubsriptionDTO subsriptionDTO) {
		boolean flag = true;

		SubDateDTO upSubDateDTO = upDTO.getUpSubDateDTO();
		if (!upSubDateDTO.getSubFirstIssueDate().equals(
				subsriptionDTO.getSubEffectiveStart())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upSubDateDTO.getSubExpireDate().equals(
				subsriptionDTO.getSubRenewalDate())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upSubDateDTO.getSubTerm().equals(
				subsriptionDTO.getSubInitialTerm())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upDTO.getSubStatus().equals(subsriptionDTO.getSubStatus())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		if (!upDTO.getSubAmount().equals(subsriptionDTO.getSubAmount())) {
			log.error("Company Doesnot match");
			flag = false;
		}
		return flag;
	}

	private boolean compareUpWithContact(UpDTO upDTO, ContactDTO contDto) {
		boolean flag = true;
		NameDTO upName = upDTO.getUpName();
		NameDTO conName = contDto.getConName();
		if (!upName.getfName().equals(conName.getfName())) {
			log.error("Contact File : Firstname Doesnot match");
			flag = false;
		}
		if (!upName.getLname().equals(conName.getLname())) {
			log.error("Contact File :  Lastname Doesnot match");
			flag = false;
		}
		AddressDTO upAddress = upDTO.getUpAddress();
		AddressDTO conAddress = contDto.getConAddress();
		if (!upAddress.getAddress1().equals(conAddress.getAddress1())) {
			log.error("Contact File :  Address1 Doesnot match");
			flag = false;
		}
		if (!upAddress.getAddress2().equals(conAddress.getAddress2())) {
			log.error("Contact File :  Address2 Doesnot match");
			flag = false;
		}
		if (!upAddress.getCity().equals(conAddress.getCity())) {
			log.error("Contact File :  City Doesnot match");
			flag = false;
		}
		if (!upAddress.getState().equals(conAddress.getState())) {
			log.error("Contact File :  State Doesnot match");
			flag = false;
		}
		if (!upAddress.getPostCode().equals(conAddress.getPostCode())) {
			log.error("Contact File :  Postal Code Doesnot match");
			flag = false;
		}
		if (!upAddress.getCountry().equals(conAddress.getCountry())) {
			log.error("Contact File :  Country Doesnot match");
			flag = false;
		}
		if (!upAddress.getPhone().equals(conAddress.getPhone())) {
			log.error("Phone Doesnot match");
			flag = false;
		}
		if (!upAddress.getEmail().equals(conAddress.getEmail())) {
			log.error("Email Doesnot match");
			flag = false;
		}
		if (!upAddress.getFax().equals(conAddress.getFax())) {
			log.error("Contact File :  Fax Doesnot match");
			flag = false;
		}

		return flag;
	}

	private void readFile() {
		UpDTO upDTO = null;
		try {

			// csv file containing data
			String uploadFile = "UploadedData-in-zuora.csv";
			// create BufferedReader to read csv file
			BufferedReader brUpload = new BufferedReader(new FileReader(
					uploadFile));
			String strLine = "";
			int lineNumber = 0;
			// Map<String, UpDTO> upMap = new HashMap<String, UpDTO>();

			// read comma separated file line by line
			while ((strLine = brUpload.readLine()) != null) {
				upDTO = new UpDTO();
				lineNumber++;
				if (lineNumber == 1) {
					continue;
				}

				String[] tuta = strLine.split("~");

				NameDTO nameDTO = new NameDTO();
				nameDTO.setfName(tuta[0]);
				nameDTO.setLname(tuta[1]);
				AddressDTO addressDTO = new AddressDTO();
				addressDTO.setState(tuta[6]);
				addressDTO.setPostCode(tuta[7]);
				addressDTO.setCountry(tuta[8]);
				addressDTO.setPhone(tuta[9]);
				addressDTO.setEmail(tuta[10]);
				addressDTO.setFax(tuta[13]);
				upDTO.setUpAddress(addressDTO);
				upDTO.setCompany(tuta[2]);
				upDTO.setPassword(tuta[16]);
				upDTO.setSubAmount(tuta[40]);
				upDTO.setUpName(nameDTO);
				AddressDTO addressDTO2 = new AddressDTO();
				addressDTO2.setAddress1(tuta[3]);
				addressDTO2.setAddress2(tuta[4]);
				addressDTO2.setCity(tuta[5]);
				upDTO.setSubStatus(tuta[39]);
				upDTO.setUpComments(tuta[30]);
				CreditCardExpiry creditCardExpiry = new CreditCardExpiry();
				creditCardExpiry.setCreditCardMasked(tuta[47]);
				creditCardExpiry.setCreditCardType(tuta[46]);
				creditCardExpiry.setExpirationDate(tuta[48]);
				upDTO.setUpCreditCardExpiry(creditCardExpiry);
				SubDateDTO subDateDTO = new SubDateDTO();
				subDateDTO.setSubCancelDate(tuta[35]);
				subDateDTO.setSubCreationDate(tuta[32]);
				subDateDTO.setSubExpireDate(tuta[34]);
				subDateDTO.setSubFirstIssueDate(tuta[33]);
				subDateDTO.setUpPubCode(tuta[31]);
				subDateDTO.setSubComment(tuta[41]);
				subDateDTO.setSubTerm(tuta[36]);
				upDTO.setUpSubDateDTO(subDateDTO);
				upDTO.setAutorenewalDisabled(tuta[13]);
				upDTO.setUserName(tuta[13]);
				upMap.put(tuta[10], upDTO);

			}

			System.out.println("ok");

			String custAccountFile = "CustomerAccount2.csv";
			// create BufferedReader to read csv file
			BufferedReader brCustAccount = new BufferedReader(new FileReader(
					custAccountFile));
			String brCustAccountLine = "";
			lineNumber = 0;
			Map<String, CustomerDTO> custAccountMap = new HashMap<String, CustomerDTO>();

			// read comma separated file line by line
			while ((brCustAccountLine = brCustAccount.readLine()) != null) {
				CustomerDTO customerDTO = new CustomerDTO();
				lineNumber++;
				if (lineNumber == 1) {
					continue;
				}

				String[] customerDTOArr = brCustAccountLine.split("~", 36);
				customerDTO.setCustId(customerDTOArr[0]);
				customerDTO.setAccountNumber(customerDTOArr[2]);
				customerDTO.setAutoPay(customerDTOArr[6]);
				customerDTO.setCompany(customerDTOArr[28]);
				customerDTO.setCustComments(customerDTOArr[26]);
				customerDTO.setPassword(customerDTOArr[30]);
				customerDTO.setUserName(customerDTOArr[29]);
				custAccountMap.put(customerDTOArr[0], customerDTO);

			}

			String paymentFile = "PaymentMethod2.csv";
			// create BufferedReader to read csv file
			BufferedReader paymentAccount = new BufferedReader(new FileReader(
					paymentFile));
			String paymentLine = "";

			lineNumber = 0;
			Map<String, PaymentDTO> paymentMap = new HashMap<String, PaymentDTO>();

			// read comma separated file line by line
			while ((paymentLine = paymentAccount.readLine()) != null) {
				PaymentDTO paymentDTO = new PaymentDTO();
				lineNumber++;
				if (lineNumber == 1) {
					continue;
				}

				String[] paymentDTOArr = paymentLine.split("~");

				paymentDTO.setCustomerAccount(paymentDTOArr[1]);
				NameDTO nameDTO = new NameDTO();
				nameDTO.setfName(paymentDTOArr[8]);
				nameDTO.setLname(paymentDTOArr[8]);
				paymentDTO.setPayName(nameDTO);
				AddressDTO addressDTO = new AddressDTO();
				addressDTO.setAddress1(paymentDTOArr[9]);
				addressDTO.setAddress2(paymentDTOArr[10]);
				addressDTO.setCity(paymentDTOArr[13]);
				addressDTO.setState(paymentDTOArr[12]);
				addressDTO.setPostCode(paymentDTOArr[14]);
				addressDTO.setCountry(paymentDTOArr[11]);
				// addressDTO.setPhone(paymentDTOArrtuta[9]);
				addressDTO.setEmail(paymentDTOArr[23]);
				// addressDTO.setFax(paymentDTOArr);
				paymentDTO.setPayAddress(addressDTO);
				paymentMap.put(paymentDTOArr[1], paymentDTO);

			}

			System.out.println("ok1");

			String subscriptionFile = "SubscriptionDetails2.csv";
			// create BufferedReader to read csv file
			BufferedReader subscriptionAccount = new BufferedReader(
					new FileReader(subscriptionFile));
			String subscriptionLine = "";
			StringTokenizer subscriptionToken = null;
			lineNumber = 0;
			Map<String, SubsriptionDTO> subscriptionMap = new HashMap<String, SubsriptionDTO>();

			// read comma separated file line by line
			while ((subscriptionLine = subscriptionAccount.readLine()) != null) {
				SubsriptionDTO subscriptionDTO = new SubsriptionDTO();
				lineNumber++;
				if (lineNumber == 1) {
					continue;
				}

				String[] subscriptionDTOArr = subscriptionLine.split("~");
				subscriptionDTO.setAccountNumber(subscriptionDTOArr[4]);
				subscriptionDTO.setSubAmount(subscriptionDTOArr[17]);
				subscriptionDTO.setSubEffectiveStart(subscriptionDTOArr[20]);
				subscriptionDTO.setSubInitialTerm(subscriptionDTOArr[7]);
				subscriptionDTO.setSubRenewalDate(subscriptionDTOArr[8]);
				subscriptionDTO.setSubStatus(subscriptionDTOArr[5]);

				subscriptionMap.put(subscriptionDTOArr[4], subscriptionDTO);

			}

			String contactFile = "Contact2.csv";
			// create BufferedReader to read csv file
			BufferedReader contactAccount = new BufferedReader(new FileReader(
					subscriptionFile));
			String contactLine = "";
			StringTokenizer contactToken = null;
			lineNumber = 0;

			Map<String, ContactDTO> contactMap = new HashMap<String, ContactDTO>();

			// read comma separated file line by line
			while ((contactLine = contactAccount.readLine()) != null) {
				ContactDTO contactDTO = new ContactDTO();
				lineNumber++;
				if (lineNumber == 1) {
					continue;
				}

				String[] contactDTOArr = contactLine.split("~");
				NameDTO nameDTO = new NameDTO();
				nameDTO.setfName(contactDTOArr[1]);
				nameDTO.setLname(contactDTOArr[2]);
				contactDTO.setConName(nameDTO);
				AddressDTO addressDTO = new AddressDTO();
				addressDTO.setAddress1(contactDTOArr[13]);
				addressDTO.setAddress2(contactDTOArr[14]);
				addressDTO.setCity(contactDTOArr[15]);
				addressDTO.setState(contactDTOArr[16]);
				addressDTO.setPostCode(contactDTOArr[17]);
				addressDTO.setCountry(contactDTOArr[12]);
				addressDTO.setPhone(contactDTOArr[6]);
				addressDTO.setEmail(contactDTOArr[4]);
				addressDTO.setFax(contactDTOArr[11]);
				contactDTO.setConAddress(addressDTO);
				contactDTO.setCustomerAccount(contactDTOArr[18]);

				contactMap.put(contactDTOArr[18], contactDTO);

			}

		} catch (Exception e) {
			System.out.println("Exception while reading csv file: " + e);
		}

	}

	// try
	// {
	//
	// //csv file containing data
	// String strFile = "UploadedData-in-zuora.csv";
	//
	// //create BufferedReader to read csv file
	// BufferedReader br = new BufferedReader( new FileReader(strFile));
	// String strLine = "";
	// StringTokenizer st = null;
	// int lineNumber = 0, tokenNumber = 0;
	// List<String> rows = new ArrayList();
	// //read comma separated file line by line
	// UpDTO upDTO=null;
	// while( (strLine = br.readLine()) != null)
	// {
	// lineNumber++;
	//
	// //break comma separated line using ","
	// st = new StringTokenizer(strLine, ",");
	// upDTO=new UpDTO();
	// while(st.hasMoreTokens())
	// {
	// //display csv values
	// tokenNumber++;
	// String str=st.nextToken();
	// System.out.println("Line # " + lineNumber +
	// ", Token # " + tokenNumber
	// + ", Token : "+ str);
	// rows.add(str);
	//
	// }
	// //reset token number
	// tokenNumber = 0;
	// }

	// tokenNumber = 0;
	// lineNumber = 0;
	// strLine = "";
	// UpDTO upDTO=new UpDTO();
	// for(int i=0;i < rows.size();i++){
	//
	// strLine=(String)rows.get(i);
	// StringTokenizer stringTokenizer = new StringTokenizer(strLine, "~");
	//
	// while(stringTokenizer.hasMoreTokens())
	// {
	// //display csv values
	// tokenNumber++;
	// System.out.print("Line # " + lineNumber +
	// ", Token # " + tokenNumber
	// + ", Token : "+ stringTokenizer.nextToken()+"\n");
	//
	// }
	//
	// strLine="";
	//
	// }

	// }
	// catch(Exception e)
	// {
	// System.out.println("Exception while reading csv file: " + e);
	// }
	// }

}
