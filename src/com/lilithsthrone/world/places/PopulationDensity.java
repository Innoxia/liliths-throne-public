package com.lilithsthrone.world.places;

/**
 * @since 0.2.12
 * @version 0.2.12
 * @author Innoxia
 */
public enum PopulationDensity {

	FEW("few"),
	
	SPARSE("sparse"),
	
	SEVERAL("several"),
	
	NUMEROUS("numerous"),
	
	DENSE("dense");

	String name;
	
	private PopulationDensity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
