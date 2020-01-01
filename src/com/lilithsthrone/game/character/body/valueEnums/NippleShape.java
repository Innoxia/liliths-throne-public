package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.3.5.5
 * @author Innoxia
 */
public enum NippleShape {

	NORMAL("normal"),
	
	INVERTED("inverted"),
	
	VAGINA("nipple-cunts"),
	
	LIPS("lipples");
	
	private String descriptor;

	private NippleShape(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
