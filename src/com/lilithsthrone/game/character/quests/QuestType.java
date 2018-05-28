package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.1
 * @version 0.1.69
 * @author Innoxia
 */
public enum QuestType {
	MAIN(Colour.QUEST_MAIN),
	SIDE(Colour.QUEST_SIDE),
	RELATIONSHIP(Colour.QUEST_RELATIONSHIP);

	private Colour colour;

	private QuestType(Colour colour) {
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}
}
