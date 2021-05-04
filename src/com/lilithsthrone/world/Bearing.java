package com.lilithsthrone.world;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public enum Bearing {
	NORTH("north"),
	NORTH_EAST("north-east"),
	EAST("east"),
	SOUTH_EAST("south-east"),
	SOUTH("south"),
	SOUTH_WEST("south-west"),
	WEST("west"),
	NORTH_WEST("north-west"),
	RANDOM("random");
	
	private String name;
	
	private Bearing(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	
}
