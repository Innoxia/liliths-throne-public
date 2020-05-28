package com.lilithsthrone.world.population;

/**
 * @since 0.3.7.7
 * @version 0.3.7.7
 * @author Innoxia
 */
public abstract class AbstractPopulationType {

	private String name;
	private String namePlural;
	
	public AbstractPopulationType(String name, String namePlural) {
		this.name = name;
		this.namePlural = namePlural;
	}

	public String getName() {
		return name;
	}

	public String getNamePlural() {
		return namePlural;
	}
}
