package com.lilithsthrone.world.places;

/**
 * @since 0.2.12
 * @version 0.3.1
 * @author Innoxia
 */
public enum PopulationType {

	PEOPLE("people", true),
	
	HARPIES("harpies", true),
	
	CROWD("crowd", false),
	
	CROWDS("crowds", true),
	
	ENFORCERS("enforcers", true),
	
	SHOPPERS("shoppers", true),

	VIPS("VIPs", true),
	
	GUARDS("guards", true),

	MAIDS("maids", true);

	private String name;
	private boolean plural;
	
	private PopulationType(String name, boolean plural) {
		this.name = name;
		this.plural = plural;
	}

	public String getName() {
		return name;
	}

	public boolean isPlural() {
		return plural;
	}
}
