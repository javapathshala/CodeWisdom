package com.application.servlet.Filter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	protected FilterConfig filterConfig;

	List<String> revokedList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		// read revoked user list
		revokedList = new ArrayList<String>();
		readConfig();
	}

	/**
	 * Reads revoked user list file & created a revoked user list
	 */
	private void readConfig() {
		if (filterConfig != null) {
			BufferedReader in;
			try {
				String fileName = filterConfig.getInitParameter("RevokedUsers");
				in = new BufferedReader(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				return;
			}
			// read all users & add to revoke list
			String userName;
			try {
				while ((userName = in.readLine()) != null) {
					revokedList.add(userName);
				}
			} catch (IOException e) {
			}
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		readConfig();
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// pre login action
		String userName = req.getParameter("txtMsg");
		// if user is in revoked list, send error
		if (revokedList.contains(userName)) {
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		// call the next filter in chain
		chain.doFilter(request, response);
	}

	public void destroy() {
		this.filterConfig = null;
		revokedList = null;
	}

}
