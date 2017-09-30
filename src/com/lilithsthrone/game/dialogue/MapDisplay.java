package com.lilithsthrone.game.dialogue;

/**
 * @since 0.1.0
 * @version 0.1.67
 * @author Innoxia
 */
public enum MapDisplay {
	NORMAL("normal"),
	STATUS_EFFECT_MESSAGE("statusEffectMessage"),
	INVENTORY("inventory"),
	PHONE("phone"),
	CHARACTERS_PRESENT("characters present"),
	OPTIONS("options");

	private String name;

	private MapDisplay(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
