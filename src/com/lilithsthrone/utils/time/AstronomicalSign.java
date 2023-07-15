package com.lilithsthrone.utils.time;

import java.time.LocalDateTime;
import java.time.Month;

public enum AstronomicalSign {
	ARIES("aries", "&#9800;", Month.MARCH, 21, Month.APRIL, 19),
	TAURUS("taurus", "&#9801;", Month.APRIL, 20, Month.MAY, 20),
	GEMINI("gemini", "&#9802;", Month.MAY, 21, Month.JUNE, 20),
	CANCER("cancer", "&#9803;", Month.JUNE, 21, Month.JULY, 22),
	LEO("leo", "&#9804;", Month.JULY, 23, Month.AUGUST, 22),
	VIRGO("virgo", "&#9805;", Month.AUGUST, 23, Month.SEPTEMBER, 22),
	LIBRA("libra", "&#9806;", Month.SEPTEMBER, 23, Month.OCTOBER, 21),
	SCORPIO("scorpio", "&#9807;", Month.OCTOBER, 22, Month.NOVEMBER, 21),
	SAGITTARIUS("sagittarius", "&#9808;", Month.NOVEMBER, 22, Month.DECEMBER, 21),
	CAPRICORN("capricorn", "&#9809;", Month.DECEMBER, 22, Month.JANUARY, 19),
	AQUARIUS("aquarius", "&#9810;", Month.JANUARY, 20, Month.FEBRUARY, 18),
	PISCES("pisces", "&#9811;", Month.FEBRUARY, 19, Month.MARCH, 20);
	
	private final String name;
	private final String htmlDisplay;
	private final Month startMonth;
	private final int startDay;
	private final Month endMonth;
	private final int endDay;
	
	AstronomicalSign(String name, String htmlDisplay, Month startMonth, int startDay, Month endMonth, int endDay) {
		this.name = name;
		this.htmlDisplay = htmlDisplay;
		this.startMonth = startMonth;
		this.startDay = startDay;
		this.endMonth = endMonth;
		this.endDay = endDay;
	}
	
	public String getName() {
		return name;
	}
	
	public String getHtmlDisplay() {
		return htmlDisplay;
	}
	
	public Month getStartMonth() {
		return startMonth;
	}
	
	public int getStartDay() {
		return startDay;
	}
	
	public Month getEndMonth() {
		return endMonth;
	}
	
	public int getEndDay() {
		return endDay;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public AstronomicalSign getSignFromDate(LocalDateTime dateTime) {
		int day = dateTime.getDayOfMonth();
		Month month = dateTime.getMonth();
		
		for (AstronomicalSign sign : AstronomicalSign.values()) {
			if ((month == getStartMonth() && day>=getStartDay())
					|| (month == getEndMonth() && day<=getEndDay())) {
				return sign;
			}
		}
		return null; // This should be impossible...
	}
}
