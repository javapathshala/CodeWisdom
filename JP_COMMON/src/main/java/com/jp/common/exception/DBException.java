package com.jp.common.exception;

public class DBException extends Exception {

	public final static long serialVersionUID = 123445566;

	public DBException(Throwable throwable) {
		super(throwable);
	}

	public DBException() {
		super();

	}

	public DBException(String key, Throwable str) {
		super(key, str);
	}

	public DBException(String key) {
		super(key);
	}

}
