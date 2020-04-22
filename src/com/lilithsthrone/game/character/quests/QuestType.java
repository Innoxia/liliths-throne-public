package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.1
 * @version 0.1.69
 * @author Innoxia
 */
public enum QuestType {
	MAIN(PresetColour.QUEST_MAIN),
	SIDE(PresetColour.QUEST_SIDE),
	RELATIONSHIP(PresetColour.QUEST_RELATIONSHIP);

	private Colour colour;

	private QuestType(Colour colour) {
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}
}
