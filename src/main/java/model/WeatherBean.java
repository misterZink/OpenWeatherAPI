package model;

import java.text.DecimalFormat;

public class WeatherBean {
	
	private String cityString, countryString, cloudsStr, temperature;



	public WeatherBean(String cityString, String countryString) {
		
		this.cityString = cityString;
		this.countryString = countryString;
		
	}
	
	public String getCityString() {
		return cityString;
	}

	public String getCountryString() {
		return countryString;
	}
	
	public String getcloudsStr() {
		return cloudsStr;
	}
	
	public String getTemperatureInCelcius() {
		return temperature;
	}
	
	public void setAttributes(String attribute, String xmlString) {
		if (attribute.equals("clouds")) {
			setCloudsStr(xmlString);
		} else if (attribute.equals("temperature")) {
			setTemperatureInCelcius(xmlString);
		}
	}

	public void setCloudsStr(String xMLClouds) {
		cloudsStr = xMLClouds.substring(0, 1).toUpperCase() + xMLClouds.substring(1);
	}
	
	public void setTemperatureInCelcius(String xMLTemp) {
		int temperatureInCelsius = (int)(Double.valueOf(xMLTemp) - 273.15);

		temperature = String.valueOf(temperatureInCelsius);
	}
	
}
