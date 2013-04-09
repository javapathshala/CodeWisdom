package com.application.servlet.Filter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: FilterServlet
 * 
 */
public class FilterServlet extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9183401227909988009L;

	
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	private void handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String textMessage=(String)request.getParameter("txtMsg");
		System.out.println(textMessage);

	}

}