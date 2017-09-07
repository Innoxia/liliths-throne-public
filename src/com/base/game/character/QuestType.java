package com.base.game.character;

import com.base.utils.Colour;

/**
 * @since 0.1.1
 * @version 0.1.69
 * @author Innoxia
 */
public enum QuestType {
	MAIN(Colour.QUEST_MAIN),
	SIDE(Colour.QUEST_SIDE),
	ROMANCE(Colour.QUEST_ROMANCE);

	private Colour colour;

	private QuestType(Colour colour) {
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}
}
