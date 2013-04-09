package com.application.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.application.data.load.impl.CustomerDAO;
import com.application.data.load.impl.DataLoaderImpl;
import com.application.data.load.impl.LocationDAO;
import com.application.spring.comparator.CustomerListIdentifierComparator;
import com.application.spring.forms.CustomerListForm;
import com.application.spring.forms.ViewCustomerForm;
import com.application.spring.search.CustomerSearchService;
import com.jp.application.common.AbstractDTO;


@Controller
public class CustomerListViewDisplayController {

	private static final long serialVersionUID = 1384771688058566146L;

	private String successView;

	private String failureView;

	@Resource
	private CustomerSearchService customerSearchService;

	private String searchQuery;

	private List<CustomerDAO> customerList = new ArrayList<CustomerDAO>();

	/**
	 * Shows the customer List when the user clicks on the Get the Customer List
	 * Link pannel.
	 * 
	 * @param viewCustomerForm
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/CustomerListViewDisplay.do", params = "show", method = RequestMethod.GET)
	public ModelAndView showList(
			@ModelAttribute("viewCustomerForm") ViewCustomerForm viewCustomerForm)
			throws Exception {
		populateFromRequestForm(viewCustomerForm);
		getCustomerList();
		populateToSuccessForm(viewCustomerForm, new ArrayList<AbstractDTO>(
				customerList));
		return new ModelAndView(getSuccessView(), "viewCustomerForm",
				viewCustomerForm);
	}

	/**
	 * Ajax Implementation
	 * 
	 * @param viewCustomerForm
	 * @param request
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/CustomerListViewDisplay.do", params = "showAjax", method = RequestMethod.GET)
	public ModelAndView showAjaxList(
			@ModelAttribute("viewCustomerForm") ViewCustomerForm viewCustomerForm,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		populateFromRequestForm(viewCustomerForm);
		String str = req.getParameter("str");
		List<AbstractDTO> listSearch = getCustomerFomSearch(str);
		populateToSuccessFormAjax(viewCustomerForm, res, listSearch);
		return new ModelAndView(getSuccessView(), "viewCustomerForm",
				viewCustomerForm);
	}

	@RequestMapping(value = "/CustomerListViewDisplay.do", params = "showCustButton", method = RequestMethod.POST)
	public ModelAndView showCustButton(
			@ModelAttribute("viewCustomerForm") ViewCustomerForm viewCustomerForm,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		populateFromRequestForm(viewCustomerForm);
		List<AbstractDTO> listSearch = getCustomerFomSearch(searchQuery);
		populateToSuccessForm(viewCustomerForm, listSearch);
		return new ModelAndView(getSuccessView(), "viewCustomerForm",
				viewCustomerForm);
	}

	/**
	 * For Customer Id Sorting
	 * 
	 * @param viewCustomerForm
	 * @param errors
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/CustomerListViewDisplay.do", params = "showSort", method = RequestMethod.GET)
	public ModelAndView showSortScreen(
			@ModelAttribute("viewCustomerForm") ViewCustomerForm viewCustomerForm,
			HttpServletRequest request) throws Exception {
		populateFromRequestForm(viewCustomerForm);
		searchQuery = request.getParameter("sea");
		List<AbstractDTO> listSearch = getCustomerFomSearch(searchQuery);
		// CustomerBasic cus[]=(CustomerBasic[])(custList.toArray(new
		// CustomerBasic[custList.size()]));
		// List<CustomerBasic> asList = Arrays.asList(cus);
		String sortOrder = request.getParameter("sort");
		// asList=sortListByIden(asList,sortOrder);
		// CustomerList newcustList=new CustomerList(asList);
		listSearch = sortListByIden(listSearch, sortOrder);
		populateToSuccessForm(viewCustomerForm, listSearch);
		return new ModelAndView(getSuccessView(), "viewCustomerForm",
				viewCustomerForm);
	}

	/**
	 * Set the builder properties from the form bean
	 * 
	 * @param viewCustomerForm
	 * @throws FrameworkSystemException
	 */
	public void populateFromRequestForm(ViewCustomerForm viewCustomerForm) {
		searchQuery = viewCustomerForm.getCustomerNameFilter();
	}

	/**
	 * Get the customer list & store the list in lucene cache
	 * 
	 * @throws FrameworkSystemException
	 * @throws FrameworkUserException
	 */
	private void getCustomerList() throws Exception {
		DataLoaderImpl dataloader = new DataLoaderImpl();
		customerList = dataloader.loadData();
		// Store the results in lucene cache
		customerSearchService.storeResultsInCache(new ArrayList<AbstractDTO>(
				customerList));
	}

	/**
	 * Display the results. Populate the form bean from the builder.
	 * 
	 * @param viewCustomerForm
	 * @param listSearch
	 * @throws FrameworkSystemException
	 */
	public void populateToSuccessForm(ViewCustomerForm viewCustomerForm,
			List<AbstractDTO> listSearch) {
		// CustomerList customerList = builder.getCustomerList();
		if (null != listSearch) {
			// populating the table
			Iterator<AbstractDTO> customers = listSearch.iterator();
			while (customers.hasNext()) {
				CustomerDAO customer = (CustomerDAO) customers.next();
				CustomerListForm customerForm = new CustomerListForm();
				customerForm.setCustomerName(customer.getCustomerName());
				customerForm.setCustId(String.valueOf(customer.getCustId()));
				LocationDAO locationDAO = customer.getLocationDAO();
				customerForm.setAddress(locationDAO.getAddress());
				customerForm.setCity(locationDAO.getCity());
				customerForm.setCountry(locationDAO.getCountry());
				viewCustomerForm.addCustomer(customerForm);
			}
		}
		viewCustomerForm.setCustomerNameFilter(searchQuery);
	}

	/**
	 * Get the customerList from the lucene cache
	 */
	private List<AbstractDTO> getCustomerFomSearch(String searchQuery) {
		return customerSearchService.passResultsFromCache(searchQuery);
	}

	public void populateToSuccessFormAjax(ViewCustomerForm viewCustomerForm,
			HttpServletResponse res, List<AbstractDTO> listSearch) {
		String responseText = "";
		if (null != listSearch) {
			// populating the table
			Iterator<AbstractDTO> customers = listSearch.iterator();
			int i = 0;
			while (customers.hasNext()) {
				CustomerDAO customer = (CustomerDAO) customers.next();
				CustomerListForm customerForm = new CustomerListForm();
				String customerName = customer.getCustomerName();
				if (i == 0) {
					responseText = customerName + ",";
				} else if (i == 1) {
					responseText = responseText + customerName;
				} else {
					responseText = responseText + "," + customerName;
				}
				i++;
				customerForm.setCustomerName(customerName);
				customerForm.setCustId(String.valueOf(customer.getCustId()));
				LocationDAO locationDAO = customer.getLocationDAO();
				customerForm.setAddress(locationDAO.getAddress());
				customerForm.setCity(locationDAO.getCity());
				customerForm.setCountry(locationDAO.getCountry());
				viewCustomerForm.addCustomer(customerForm);
			}
		}
		// retaining the filters values
		viewCustomerForm.setCustomerNameFilter(searchQuery);
		PrintWriter out;
		try {
			out = res.getWriter();
			out.println(responseText);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getFailureView() {
		return failureView;
	}

	public void setFailureView(String failureView) {
		this.failureView = failureView;
	}

	public String getSuccessView() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	@SuppressWarnings("unchecked")
	private List<AbstractDTO> sortListByIden(List<AbstractDTO> custList,
			String sortOrder) {
		CustomerListIdentifierComparator customerListIdentifierComparator = new CustomerListIdentifierComparator(
				sortOrder);
		Collections.sort(custList, customerListIdentifierComparator);
		return custList;
	}

	@Deprecated
	public void setServletURI(javax.servlet.http.HttpServletRequest request) {
		String scheme = request.getScheme();
		String host = request.getServerName();
		String port = Integer.toString(request.getServerPort());
		String requestURI = request.getContextPath();

		StringBuffer url = new StringBuffer();

		if (scheme.length() > 0) {
			url.append(scheme).append("://");
		}

		url.append(host);

		if (port.length() > 0) {
			url.append(":");
		}

		url.append(port);

		if ((port.length() > 0) & !(requestURI.startsWith("/"))) {
			url.append("/");
		}

		url.append(requestURI + "" + "/");

		String servletURI = url.toString();
		System.out.println(servletURI);
	}
}
