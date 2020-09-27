package com.lilithsthrone.game.character.race;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public enum RacialClass {
	
	MAMMAL(true, true),
	BIRD(true, true),
	REPTILE(false, false),
	AMPHIBIAN(false, false),
	FISH(false, false),
	
	OTHER(true, true); // 'OTHER' could be things like slimes or elementals
	
	private boolean anthroHair;
	private boolean anthroBreasts;

	private RacialClass(boolean anthroHair, boolean anthroBreasts) {
		this.anthroHair = anthroHair;
		this.anthroBreasts = anthroBreasts;
	}

	public boolean isAnthroHair() {
		return anthroHair;
	}

	public boolean isAnthroBreasts() {
		return anthroBreasts;
	}
}
