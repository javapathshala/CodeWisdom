package com.application.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Test {
	
	private String successView;
	
	@RequestMapping(value = "/Test.do", method = RequestMethod.GET)
	public ModelAndView test() throws Exception {
		return new ModelAndView(getSuccessView());
	}

	public String getSuccessView() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}
	
	

}
