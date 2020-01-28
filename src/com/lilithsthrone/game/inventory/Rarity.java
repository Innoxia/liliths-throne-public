package com.lilithsthrone.game.inventory;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.3.5.1
 * @author Innoxia
 */
public enum Rarity {

	COMMON("common", Colour.RARITY_COMMON, Colour.RARITY_COMMON_BACKGROUND),
	
	UNCOMMON("uncommon", Colour.RARITY_UNCOMMON, Colour.RARITY_UNCOMMON_BACKGROUND),
	
	RARE("rare", Colour.RARITY_RARE, Colour.RARITY_RARE_BACKGROUND),
	
	EPIC("epic", Colour.RARITY_EPIC, Colour.RARITY_EPIC_BACKGROUND),
	
	LEGENDARY("legendary", Colour.RARITY_LEGENDARY, Colour.RARITY_LEGENDARY_BACKGROUND),
	
	QUEST("unique", Colour.RARITY_QUEST, Colour.RARITY_QUEST_BACKGROUND),
	
	JINXED("jinxed", Colour.RARITY_JINXED, Colour.RARITY_JINXED_BACKGROUND);

	private String name;
	private Colour colour;
	private Colour backgroundColour;
	
	private Rarity(String name, Colour colour, Colour backgroundColour) {
		this.name = name;
		this.colour = colour;
		this.backgroundColour = backgroundColour;
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}

	public Colour getBackgroundColour() {
		return backgroundColour;
	}
}
