package com.lilithsthrone.world.places;

/**
 * @since 0.2.12
 * @version 0.3.6.2
 * @author Innoxia
 */
public enum PopulationType {

	PEOPLE("people", true),
	
	HARPIES("harpies", true),

	HARPIES_SILLY("birbs", true),
	
	CROWD("crowd", false),
	
	CROWDS("crowds", true),

	PRIVATE_SECURITY_GUARD("private security guard", false),
	PRIVATE_SECURITY_GUARDS("private security guards", true),
	
	ENFORCERS("Enforcers", true),
	
	SHOPPERS("shoppers", true),
	
	DINERS("diners", true),

	VIPS("VIPs", true),
	
	GUARDS("guards", true),

	MAIDS("maids", true),

	OFFICE_WORKERS("office-workers", true),

	GANG_MEMBERS("gang members", true),
	;

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
