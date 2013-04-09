package com.application.servlet.caching;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: CacheHttpServlet
 * 
 */
public abstract class CacheHttpServlet extends HttpServlet {
	CacheHttpServletResponse cacheResponse;

	long cacheLastMod = -1;

	String cacheQueryString = null;

	String cachePathInfo = null;

	String cacheServletPath = null;

	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// Only do caching for GET requests
		String method = req.getMethod();
		if (!method.equals("GET")) {
			super.service(req, res);
			return;
		}

		// Check the last modified time for this servlet
		long servletLastMod = getLastModified(req);

		// A last modified of -1 means we shouldn't use any cache logic
		if (servletLastMod == -1) {
			super.service(req, res);
		}
		// If the client sent an If-Modified-Since header equal or after the
		// servlet's last modified time, send a short "Not Modified" status code
		// Round down to the nearest second since client headers are in seconds
		else if ((servletLastMod / 1000 * 1000) <= req
				.getDateHeader("If-Modified-Since")) {
			res.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
		}
		// Use the existing cache if it's current and valid
		else if (servletLastMod <= cacheLastMod && cacheResponse.isValid()
				&& equal(cacheQueryString, req.getQueryString())
				&& equal(cachePathInfo, req.getPathInfo())
				&& equal(cacheServletPath, req.getServletPath())) {
			cacheResponse.writeTo(res);
		}
		// Otherwise make a new cache to capture the response
		else {
			cacheResponse = new CacheHttpServletResponse(res);
			cacheLastMod = servletLastMod;
			cacheQueryString = req.getQueryString();
			cachePathInfo = req.getPathInfo();
			cacheServletPath = req.getServletPath();
			super.service(req, cacheResponse);
		}
	}

	private boolean equal(String s1, String s2) {
		if (s1 == null && s2 == null) {
			return true;
		} else if (s1 == null || s2 == null) {
			return false;
		} else {
			return s1.equals(s2);
		}
	}
}
