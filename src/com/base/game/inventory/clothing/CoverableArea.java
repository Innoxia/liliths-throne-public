package com.base.game.inventory.clothing;

/**
 * @since 0.1.0
 * @version 0.1.69
 * @author Innoxia
 */
public enum CoverableArea {
	ANUS("asshole"),
	VAGINA("pussy"),
	MOUND("mound"),
	PENIS("cock"),
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
