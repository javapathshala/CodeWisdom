package com.jp.spring.app.di;



public class RunTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ContainerDI containerDI = new ContainerDI();
		RequestConfigurator requestConfigurator = new RequestConfigurator();
		requestConfigurator.setupContainer(containerDI);
		TestDI testDI = (TestDI) containerDI.get(TestDI.class);
		testDI.test();
	}
}
