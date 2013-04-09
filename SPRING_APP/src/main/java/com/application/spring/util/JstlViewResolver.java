package com.application.spring.util;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class JstlViewResolver extends UrlBasedViewResolver implements Ordered {
	
	/**
	 * Overrides loadView in UrlBasedViewResolver to be able to make a
	 * difference when letting the other views in the chaining take control of the
	 * request
	 * 
	 * @see org.springframework.web.servlet.view.UrlBasedViewResolver#loadView(String,java.util.Locale)
	 */
	protected View loadView(String viewName, Locale locale) throws Exception {
		AbstractUrlBasedView view = buildView(viewName);
		View viewObj = (View) getApplicationContext().getAutowireCapableBeanFactory().initializeBean(view, viewName);
		if (viewObj instanceof JstlView) {
			JstlView jv = (JstlView) viewObj;
			if (jv.getBeanName().indexOf(".layout") != -1) {
				return null;
			}
		}
		return viewObj;
	}
}