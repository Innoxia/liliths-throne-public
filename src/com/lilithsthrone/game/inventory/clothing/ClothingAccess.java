package com.lilithsthrone.game.inventory.clothing;

/**
 * The types of possible access you'd need in order to equip a piece of clothing.
 * 
 * @since 0.1.64
 * @version 0.2.10
 * @author Innoxia
 */
public enum ClothingAccess {

	/** Placing something onto your eyes, like glasses. */
	EYES("place onto eyes"),

	/** Placing something into your mouth, like a ball-gag. */
	MOUTH("place onto mouth"),

	/** Pulling something down over your head, like a t-shirt. */
	HEAD("pull down over head"),

	/**
	 * Sliding something up your arms to rest on your shoulders, like the loops in a bra.
	 */
	ARMS_UP_TO_SHOULDER("slide over arms"),

	/** Sliding something up your wrists, like elbow-length gloves. */
	WRISTS("slide up wrists"),

	/** Sliding something down your fingers, like a ring. */
	FINGERS("slide down fingers"),

	/** Sliding something over your chest, like the cups of a bra. */
	CHEST("slide over chest"),

	/** Sliding something over your waist, like a corset or swimsuit. */
	WAIST("slide over waist"),

	/** Pulling something up your legs, like a pair of panties. */
	LEGS_UP_TO_GROIN_LOW_LEVEL("pull up between legs"),

	/** Sliding something up your legs, like the holes in a pair of trousers. */
	LEGS_UP_TO_GROIN("slide up legs"),

	/** Sliding something over your groin, like a pair of panties.*/
	GROIN("slide over groin"),

	/** Sliding something onto your penis, like a condom. */
	PENIS("slide onto penis"), // This gets automatically added to 'clothingAccessBlocked' values if clothing has 'PENIS' in 'blockedBodyParts'.

	/** Inserting something into your vagina, like a dildo. */
	VAGINA("insert into vagina"), // This gets automatically added to 'clothingAccessBlocked' values if clothing has 'VAGINA' in 'blockedBodyParts'.

	/** Inserting something into your anus, like a butt-plug. */
	ANUS("insert into anus"),

	/** Sliding something over your calves, like a pair of knee-high socks. */
	CALVES("slide over calves"),

	/** Sliding something over your feet, like a pair of socks. */
	FEET("slide over feet");

	String descriptor;

	private ClothingAccess(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}
}
