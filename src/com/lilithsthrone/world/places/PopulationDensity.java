package com.lilithsthrone.world.places;

/**
 * @since 0.2.12
 * @version 0.3.6.2
 * @author Innoxia
 */
public enum PopulationDensity {

	OCCASIONAL("an occasional"),
	
	COUPLE("one or two"),
	
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
