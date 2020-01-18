package com.lilithsthrone.game.inventory.enchanting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.83
 * @version 0.2.11
 * @author Innoxia
 */
public enum TFPotency {
	
	MAJOR_DRAIN("Major Drain", Colour.GENERIC_TERRIBLE, 8, -3),
	DRAIN("Drain", Colour.GENERIC_BAD, 4, -2),
	MINOR_DRAIN("Minor Drain", Colour.GENERIC_MINOR_BAD, 1, -1),
	MINOR_BOOST("Minor Boost", Colour.GENERIC_MINOR_GOOD, 1, 1),
	BOOST("Boost", Colour.GENERIC_GOOD, 4, 2),
	MAJOR_BOOST("Major Boost", Colour.GENERIC_EXCELLENT, 8, 3);
	
	private static List<TFPotency> allPotencies = new ArrayList<>();

	static {
		Collections.addAll(allPotencies, TFPotency.values());
	}
	
	private String name;
	private Colour colour;
	private int value;
	private int clothingBonusValue;
	
	private TFPotency(String name, Colour colour, int value, int clothingBonusValue) {
		this.name = name;
		this.colour = colour;
		this.value = value;
		this.clothingBonusValue = clothingBonusValue;
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
