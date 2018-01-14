package com.lilithsthrone.game;

import java.time.Month;

/**
 * @since 0.1.96
 * @version 0.1.96
 * @author Innoxia
 */
public enum Season {
	
	SPRING("spring"),
	SUMMER("summer"),
	AUTUMN("autumn"),
	WINTER("winter");
	
	private String name;

	private Season(String name) {
		this.name = name;
	}
	
	public static Season getSeasonFromMonth(Month month) {
		switch(month) {
			case DECEMBER: case JANUARY: case FEBRUARY:
				return Season.WINTER;
				
			case MARCH: case APRIL: case MAY:
				return Season.SPRING;
			
			case JUNE: case JULY: case AUGUST:
				return Season.SUMMER;
				
			case SEPTEMBER: case OCTOBER: case NOVEMBER:
				return Season.AUTUMN;
		}
		
		return Season.SUMMER;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
