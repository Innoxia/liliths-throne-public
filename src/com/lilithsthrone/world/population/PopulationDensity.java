package com.lilithsthrone.world.population;

/**
 * @since 0.2.12
 * @version 0.4.4.5
 * @author Innoxia
 */
public enum PopulationDensity {

	ONE("a single"),
	
	OCCASIONAL("an occasional"),
	
	COUPLE("couple of"),
	
	FEW("few"),
	
	SPARSE("sparse"),
	
	SEVERAL("several"),

	MANY("many"),
	
	NUMEROUS("numerous"),
	
	DENSE("dense"),
	
	HUNDREDS("hundreds of"),
	
	THOUSANDS("thousands of");

	String name;
	
	private PopulationDensity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
