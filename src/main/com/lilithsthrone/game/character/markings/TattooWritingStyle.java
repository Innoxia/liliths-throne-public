package com.lilithsthrone.game.character.markings;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum TattooWritingStyle {

	ITALICISED("italicised"),
	
	BOLD("bold");
	
	private String name;

	private TattooWritingStyle(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
