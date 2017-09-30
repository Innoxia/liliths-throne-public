package com.lilithsthrone.game.inventory.enchanting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum TFPotency {
	
	MAJOR_DRAIN("Major Drain", Colour.GENERIC_TERRIBLE, 4),
	DRAIN("Drain", Colour.GENERIC_BAD, 2),
	MINOR_DRAIN("Minor Drain", Colour.GENERIC_MINOR_BAD, 1),
	MINOR_BOOST("Minor Boost", Colour.GENERIC_MINOR_GOOD, 1),
	BOOST("Boost", Colour.GENERIC_GOOD, 2),
	MAJOR_BOOST("Major Boost", Colour.GENERIC_EXCELLENT, 4);
	
	private static List<TFPotency> allPotencies = new ArrayList<>();

	static {
		Collections.addAll(allPotencies, TFPotency.values());
	}
	
	private String name;
	private Colour colour;
	private int value;
	
	private TFPotency(String name, Colour colour, int value) {
		this.name = name;
		this.colour = colour;
		this.value = value;
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

	public static List<TFPotency> getAllPotencies() {
		return allPotencies;
	}
	
}
