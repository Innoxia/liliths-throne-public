package com.lilithsthrone.game.inventory.enchanting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.83
 * @version 0.4.11.2
 * @author Innoxia
 */
public enum TFPotency {
	
	MAJOR_DRAIN("Major Drain", PresetColour.GENERIC_TERRIBLE, 8, -3, true),
	DRAIN("Drain", PresetColour.GENERIC_BAD, 4, -2, true),
	MINOR_DRAIN("Minor Drain", PresetColour.GENERIC_MINOR_BAD, 1, -1, true),
	MINOR_BOOST("Minor Boost", PresetColour.GENERIC_MINOR_GOOD, 1, 1, true),
	BOOST("Boost", PresetColour.GENERIC_GOOD, 4, 2, true),
	MAJOR_BOOST("Major Boost", PresetColour.GENERIC_EXCELLENT, 8, 3, true),

	/**
	 *  SPECIAL is used for item effects which require a special effect; this TFPotency is not available to the player and is only used in modded or specially-defined clothing.
	 *  <br/>
	 *  <br/>
	 *  <u>Currently implemented uses of SPECIAL potency:</u>
	 *  <br/><b>Clothing seals:</b>
	 *  <br/>- Clothing which is sealed (primary modifier="CLOTHING_SPECIAL" and secondary modifier="CLOTHING_SEALING") will be unable to be unsealed outside of special scenes which force an unequip of clothing.
	 *  <br/>- You can force the clothing to be unsealed by using its setSealed(false) method, for example: <code>[#pc.getClothingInSlot(IS_GROIN).setSealed(false)]</code>
	 *  <br/>- You can force the clothing to be unequipped by using the owner's forceUnequipClothingIntoVoid() method or by using unequipAllClothing() with the 'removeSeals' argument set to true.
	 *  <br/>- So there are many ways the clothing can be safely unsealed and removed by using code, but the player will not have access to any way to unseal the clothing outside of triggering special scenes in which the clothing is forcibly removed
	 *   (such as when submitting to Natalya, in many bad ends, when locked into the stocks in slaver alley, etc. etc.).
	 */
	SPECIAL("Special", PresetColour.ATTRIBUTE_CORRUPTION, 8, 3, false);
	
	private static List<TFPotency> allPotencies = new ArrayList<>();

	static {
		Collections.addAll(allPotencies, TFPotency.values());
		allPotencies.removeIf(p->!p.isAvailableForEnchanting());
	}
	
	private String name;
	private Colour colour;
	private int value;
	private int clothingBonusValue;
	private boolean availableForEnchanting;
	
	private TFPotency(String name, Colour colour, int value, int clothingBonusValue, boolean availableForEnchanting) {
		this.name = name;
		this.colour = colour;
		this.value = value;
		this.clothingBonusValue = clothingBonusValue;
		this.availableForEnchanting = availableForEnchanting;
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}

	public int getValue() {
		return value;
	}
	
	public int getClothingBonusValue() {
		return clothingBonusValue;
	}

	public boolean isAvailableForEnchanting() {
		return availableForEnchanting;
	}

	public boolean isNegative() {
		return this==TFPotency.MINOR_DRAIN || this==TFPotency.DRAIN || this==TFPotency.MAJOR_DRAIN;
	}

	public static List<TFPotency> getAllPotencies() {
		return allPotencies;
	}
	
	public static TFPotency getRandomWeightedPositivePotency() {
		double rnd = Math.random();
		
		if(rnd<0.6) {
			return TFPotency.MINOR_BOOST;
		} else if (rnd<0.9) {
			return TFPotency.BOOST;
		} else {
			return TFPotency.MAJOR_BOOST;
		}
	}
	
	public static TFPotency getRandomWeightedNegativePotency() {
		double rnd = Math.random();
		
		if(rnd<0.6) {
			return TFPotency.MAJOR_DRAIN;
		} else if (rnd<0.9) {
			return TFPotency.DRAIN;
		} else {
			return TFPotency.MAJOR_DRAIN;
		}
	}
	
}
