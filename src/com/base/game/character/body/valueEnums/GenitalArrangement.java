package com.base.game.character.body.valueEnums;

/**
 * @since 0.1.84
 * @version 0.1.84
 * @author Innoxia
 */
public enum GenitalArrangement {
	
	NORMAL("normal"),
	CLOACA("cloaca");
	
	private String descriptor;

	private GenitalArrangement(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
	
}
