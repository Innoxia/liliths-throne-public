package com.lilithsthrone.game.inventory;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.3.5
 * @author Innoxia
 */
public enum Rarity {

	COMMON("common", Colour.RARITY_COMMON),
	UNCOMMON("uncommon", Colour.RARITY_UNCOMMON),
	RARE("rare", Colour.RARITY_RARE),
	EPIC("epic", Colour.RARITY_EPIC),
	LEGENDARY("legendary", Colour.RARITY_LEGENDARY),
	QUEST("quest", Colour.RARITY_QUEST),
	
	JINXED("jinxed", Colour.RARITY_JINXED);

	private String name;
	private Colour colour;

	private Rarity(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}
}
