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
	
	TRIO("trio of"),
	
	SPARSE("sparse"),
	
	SEVERAL("several"),
	
	HALF_DOZEN("half a dozen"),
	
	DOZEN("a dozen"),

	DOZENS("dozens of"),
	
	MANY("many"),
	
	NUMEROUS("numerous"),
	
	DENSE("dense"),
	
	SMALL("a small"),
	
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
