package com.lilithsthrone.game.settings;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public enum DifficultyLevel {
	
	NORMAL("Human", "The way the game is meant to be played. No level scaling and no damage modifications.", PresetColour.RACE_HUMAN, false, 1f, 1f, 1f),

	LEVEL_SCALING("Morph", "Enemies level up alongside your character, but do normal damage.", PresetColour.RACE_CAT_MORPH, true, 1f, 1f, 1f),
	
	HARD("Demon", "Enemies level up alongside your character with 130% mod and do 200% damage.", PresetColour.RACE_DEMON, true, 1.3f, 2f, 1f),
	
	NIGHTMARE("Lilin", "Enemies level up alongside your character with 150% mod, do 200% damage, and take only 50% damage from all sources.", PresetColour.BASE_PURPLE, true, 1.5f, 2f, 0.5f),
	
	HELL("Lilith", "Enemies level up alongside your character with 170% mod, do 400% damage, and take only 25% damage from all sources. Be prepared to lose. A lot.", PresetColour.BASE_CRIMSON, true, 1.7f, 2f, 0.25f);

	private String name;
	private String description;
	private Colour colour;
	private boolean isNPCLevelScaling;
	private float levelScalingNPC;
	private float damageModifierNPC;
	private float damageModifierPlayer;
	
	private DifficultyLevel(String name, String description, Colour colour, boolean isNPCLevelScaling, float levelScalingNPC, float damageModifierNPC, float damageModifierPlayer) {
		this.name = name;
		this.description = description;
		this.colour = colour;
		this.isNPCLevelScaling = isNPCLevelScaling;
		this.levelScalingNPC = levelScalingNPC;
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
	
	public float getNPCLevelScaling()
	{
		return levelScalingNPC;
	}

	public float getDamageModifierNPC() {
		return damageModifierNPC;
	}

	public float getDamageModifierPlayer() {
		return damageModifierPlayer;
	}
}
