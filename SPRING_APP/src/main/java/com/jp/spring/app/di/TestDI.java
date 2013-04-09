/**
 * Copywrite @ Dimit Chadha
 */
package com.jp.spring.app.di;

/**
 * @author Dimit Chadha
 * 
 */
public class TestDI {

	InsertDI insertDI;
	InsertDI2 insertDI2;
	String message="ok";

	public TestDI(InsertDI insertDI, InsertDI2 insertDI2) {
		this.insertDI = insertDI;
		this.insertDI2 = insertDI2;
		System.out.println(message);
	}
	
	public TestDI(){
		System.out.println("dsad");
	}

	public void test() {
		insertDI.getMessage();
		insertDI2.getMessage();

	}

}
