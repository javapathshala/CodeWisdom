package com.jp.design.pattern.observer;


public class ForCastDisplay implements Observer,DisplayElement{

	private float temperature;
	private float humidity;
	private float pressure;
	private Subject weatherData;
	
	public ForCastDisplay (Subject weatherData1){
		this.weatherData=weatherData1;
		weatherData.registerObserver(this);
	}

	public void update(float temperature, float humidity, float pressure) {
		this.temperature=temperature;
		this.humidity=humidity;
		this.pressure=pressure;
		Display();
	}

	public void Display() {
		System.out.println("ForeCast Conditions : Watch out for cooler,rainy Weather :" + temperature + " F degrees ,"+humidity + " % humidity and Pressure is "+pressure);
	}
}
