package com.lilithsthrone.utils.time;

import com.lilithsthrone.utils.Colour;

/**
 * https://en.wikipedia.org/wiki/Twilight
 * 
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public enum DayPeriod {
	
	DAY("day-time", Colour.BASE_BLUE_LIGHT),
	
	CIVIL_TWILIGHT("civil twilight", Colour.BASE_PURPLE_LIGHT),
	
	NAUTICAL_TWILIGHT("nautical twilight", Colour.BASE_PURPLE),
	
	ASTRONOMICAL_TWILIGHT("astronomical twilight", Colour.BASE_BLUE_STEEL),
	
	NIGHT("night-time", Colour.BASE_BLUE_DARK);
	
	
	private String name;
	private Colour colour;
	
	private DayPeriod(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
	}
	
	public String getName() {
		return name;
	}
	
	public Colour getColour() {
		return colour;
	}
}
