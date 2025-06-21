package com.lilithsthrone.game.settings;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public enum DifficultyLevel {
	
	NORMAL("Human", "The way the game is meant to be played. No level scaling and no damage modifications.", PresetColour.RACE_HUMAN, false, 1, 1),
	
	LEVEL_SCALING("Lesser Morph", "Enemies level up alongside your character, but do normal damage.", PresetColour.RACE_CAT_MORPH, true, 1, 1),
	
	HARD("Greater Morph", "Enemies level up alongside your character and do 200% damage.", PresetColour.TRANSFORMATION_GREATER, true, 2, 1),
	
	NIGHTMARE("Imp", "Enemies level up alongside your character, do 200% damage, and take only 50% damage from all sources.", PresetColour.BASE_YELLOW, true, 2, 0.5f),
	
	ELITE("Demon", "Enemies are always 2x your character's level, do 200% damage, and take only 50% damage from all sources.", PresetColour.BASE_GOLD, true, 2, 0.5f),
	
	MASTER("Lilin", "Enemies are always 2x your character's level, do 400% damage, and take only 50% damage from all sources.", PresetColour.BASE_PURPLE, true, 4, 0.5f),
	
	HELL("Lilith", "Enemies are always 2x your character's level, do 400% damage, and take only 25% damage from all sources.", PresetColour.BASE_CRIMSON, true, 4, 0.25f),
	
	BALANCED("My preference", "Enemies are always 2x your character's level, and do 150% damage.", PresetColour.BASE_BLUE, true, 1.5f, 1);
	

	private String name;
	private String description;
	private Colour colour;
	private boolean isNPCLevelScaling;
	private float damageModifierNPC;
	private float damageModifierPlayer;
	
	private DifficultyLevel(String name, String description, Colour colour, boolean isNPCLevelScaling, float damageModifierNPC, float damageModifierPlayer) {
		this.name = name;
		this.description = description;
		this.colour = colour;
		this.isNPCLevelScaling = isNPCLevelScaling;
		this.damageModifierNPC = damageModifierNPC;
		this.damageModifierPlayer = damageModifierPlayer;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public Colour getColour() {
		return colour;
	}

	public boolean isNPCLevelScaling() {
		return isNPCLevelScaling;
	}

	public float getDamageModifierNPC() {
		return damageModifierNPC;
	}

	public float getDamageModifierPlayer() {
		return damageModifierPlayer;
	}
}
