package com.lilithsthrone.game.occupantManagement.slave;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.8.6
 * @version 0.3.8.6
 * @author Innoxia
 */
public enum SlaveJobFlag {

	EXPERIENCE_GAINS(PresetColour.GENERIC_EXPERIENCE, "Experience gains", "Slaves have a 25% chance to gain 5 experience every hour."),
	
	INTERACTION_SEX(PresetColour.GENERIC_SEX, "Slave sex", "Slaves who are assigned to this job are able to have sex with other slaves."),
	
	INTERACTION_BONDING(PresetColour.AFFECTION, "Slave bonding", "Slaves who are assigned to this job are able to socialise with other slaves."),
	
	GUEST_CAN_WORK(PresetColour.GENERIC_NEUTRAL, "Guest worker", "Guests can be assigned to this job.");
	
	private Colour colour;
	private String name;
	private String description;
	
	private SlaveJobFlag(Colour colour, String name, String description) {
		this.colour = colour;
		this.name = name;
		this.description = description;
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
