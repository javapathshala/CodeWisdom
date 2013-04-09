/**
 * 
 */
package com.application.actions;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * @author dimit
 * 
 */
@UrlBinding("/com/application/actions")
public class CalculatorActionBean implements ActionBean {
	private ActionBeanContext context;

	private int numberOne;

	private int numberTwo;

	private int result;

	public ActionBeanContext getContext() {
		return context;
	}

	public void setContext(ActionBeanContext context) {
		this.context = context;
	}

	public int getNumberOne() {
		return numberOne;
	}

	public void setNumberOne(int numberOne) {
		this.numberOne = numberOne;
	}

	public int getNumberTwo() {
		return numberTwo;
	}

	public void setNumberTwo(int numberTwo) {
		this.numberTwo = numberTwo;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	/** An event handler method that adds number one to number two. */
	@DefaultHandler
	public Resolution addition() {
		result = numberOne + numberTwo;
		return new ForwardResolution("/index.jsp");
	}

	public Resolution showScreen() {
		// String str=req.getParameter("comp");
		HttpServletRequest request = getContext().getRequest();
		String str = request.getParameter("comp");
		return new ForwardResolution("/index1.jsp");
	}

	/** An event handler method that divides number one by number two. */
	public Resolution division() {
		result = numberOne / numberTwo;
		return new ForwardResolution("/index.jsp");
	}

	/**
	 * An example of a custom validation that checks that division operations
	 * are not dividing by zero.
	 */
	// @ValidationMethod(on="division")
	// public void avoidDivideByZero(ValidationErrors errors) {
	// if (this.numberTwo == 0) {
	// errors.add("numberTwo", new SimpleError("Dividing by zero is not
	// allowed."));
	// }
	// }
}
