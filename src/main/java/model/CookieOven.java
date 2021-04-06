package model;

import javax.servlet.http.Cookie;

public class CookieOven {

	public static Cookie bakeCookie(WeatherBean wBean) {
		int minute = 60;
		Cookie ck = new Cookie(wBean.getCityString().toLowerCase()+wBean.getCountryString().toLowerCase() ,
				wBean.getCityString() + "/" + wBean.getcloudsStr().replaceAll(" ", "_") + "/" + wBean.getTemperatureInCelcius());
		
		ck.setMaxAge(minute * 20);
		return ck;
		}
}
