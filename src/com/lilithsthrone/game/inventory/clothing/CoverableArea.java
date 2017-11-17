package com.lilithsthrone.game.inventory.clothing;

/**
 * @since 0.1.0
 * @version 0.1.89
 * @author Innoxia
 */
public enum CoverableArea {
	
	ASS("ass"),
	ANUS("asshole"),
	
	VAGINA("pussy"),
	MOUND("mound"),
	PENIS("cock"),
	TESTICLES("balls"),
	
	BREASTS("breasts"),
	NIPPLES("nipples"),
	
	MOUTH("mouth");

	private String name;

	private CoverableArea(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
