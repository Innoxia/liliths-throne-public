package com.lilithsthrone.world.population;

/**
 * @since 0.2.12
 * @version 0.3.7
 * @author Innoxia
 */
public enum PopulationDensity {

	ONE("a single"),
	
	OCCASIONAL("an occasional"),
	
	COUPLE("couple of"),
	
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
