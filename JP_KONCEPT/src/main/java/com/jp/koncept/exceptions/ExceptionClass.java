package com.jp.koncept.exceptions;

public class ExceptionClass extends ArithmeticException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2967219690400008882L;
	private int num;

	public ExceptionClass() {
	}

	public ExceptionClass(String msg) {
		super(msg);
	}

	public ExceptionClass(String msg, int num) {
		super(msg);
		this.num = num;
	}

//	public String getMessage() {
//		return "Detail Message: Divison of " + num + " by 0"
//				+ super.getMessage() + "class   : " + getClass().getName();
//	}
	
	public String getMessage() {
		return "Detail Message: Divison of " + num + " by 0"
				+  "class   : " + getClass().getName();
	}

}
