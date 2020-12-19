package com.lilithsthrone.game.character.effects;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public enum StatusEffectCategory {
	
	/** The standard category which all StatusEffects are set to by default. It has no special effects. */
	DEFAULT,

	/** This defines the Status Effect as only needing an update check after the character's attributes have been modified. */
	ATTRIBUTE,
	
	/** This defines the Status Effect as only needing an update check after the character's inventory has been modified. */
	INVENTORY;
}
