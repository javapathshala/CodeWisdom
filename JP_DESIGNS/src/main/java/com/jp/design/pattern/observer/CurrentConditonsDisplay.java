package com.jp.design.pattern.observer;

public class CurrentConditonsDisplay implements Observer,DisplayElement{
	
	private float temperature;
	private float humidity;
	private Subject weatherData;
	
	public CurrentConditonsDisplay (Subject weatherDataTemp){
		this.weatherData=weatherDataTemp;
		weatherData.registerObserver(this);
	}

	public void update(float temperature, float humidity, float pressure) {
		this.temperature=temperature;
		this.humidity=humidity;
		Display();
	}

	public void Display() {
		System.out.println("Current Conditions  :" + temperature + "F degrees and "+humidity + " % humidity");
	}
}
