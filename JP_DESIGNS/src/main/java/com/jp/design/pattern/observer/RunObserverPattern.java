package com.jp.design.pattern.observer;

public class RunObserverPattern {

	public static void main(String[] args) {

		WeatherData weatherData = new WeatherData();

		CurrentConditonsDisplay currentDisplay = new CurrentConditonsDisplay(weatherData);
		System.out.println(currentDisplay);
		ForCastDisplay foreCastDisplay = new ForCastDisplay(weatherData);
		System.out.println(foreCastDisplay);
		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(100, 90, 30.4f);

	}
}
