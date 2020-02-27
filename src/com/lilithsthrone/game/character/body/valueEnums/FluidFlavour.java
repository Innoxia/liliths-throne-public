package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.5.5
 * @author Innoxia
 */
public enum FluidFlavour {
	
	CUM("cum", Colour.BASE_WHITE,
			Util.newArrayListOfValues(
					"salty")),
	
	MILK("milk", Colour.BASE_WHITE,
			Util.newArrayListOfValues(
					"creamy")),
	
	GIRL_CUM("girl-cum", Colour.BASE_WHITE,
			Util.newArrayListOfValues(
					"sweet")),

	BUBBLEGUM("bubblegum", Colour.BASE_PINK_LIGHT,
			Util.newArrayListOfValues(
					"sweet")),
	
	
	BEER("beer", Colour.BASE_TAN,
			Util.newArrayListOfValues(
					"yeasty",
					"beer-flavoured")),
	
	VANILLA("vanilla", Colour.BASE_YELLOW_LIGHT,
			Util.newArrayListOfValues(
					"sweet",
					"vanilla-flavoured")),
	
	STRAWBERRY("strawberries", Colour.BASE_RED,
			Util.newArrayListOfValues(
					"sweet",
					"strawberry-flavoured")),
	
	CHOCOLATE("chocolate", Colour.BASE_BROWN,
			Util.newArrayListOfValues(
					"chocolatey",
					"chocolate-flavoured")),
	
	PINEAPPLE("pineapple", Colour.BASE_YELLOW_LIGHT,
			Util.newArrayListOfValues(
					"tart",
					"sour",
					"tangy",
					"pineapple-flavoured")),
	
	HONEY("honey", Colour.BASE_YELLOW,
			Util.newArrayListOfValues(
					"sweet",
					"honey-flavoured")),
	
	MINT("mint", Colour.BASE_GREEN_LIME,
			Util.newArrayListOfValues(
					"minty")),
	
	CHERRY("cherry", Colour.BASE_CRIMSON,
			Util.newArrayListOfValues(
					"sweet",
					"cherry-flavoured"))
	;
	
	private String name;
	private Colour colour;
	private List<String> flavourDescriptors;

	private FluidFlavour(String name, Colour colour, List<String> flavourDescriptors) {
		this.name = name;
		this.colour=colour;
		this.flavourDescriptors = flavourDescriptors;
	}
	
	/**
	 * To go into: "You can't get the rich strawberry taste out of your mouth."<br/>
	 * Or: "Strawberry-flavoured"
	 */
	public String getName() {
		return name;
	}
	
	public Colour getColour() {
		return colour;
	}

	public List<String> getFlavourDescriptors() {
		return flavourDescriptors;
	}
	
	public String getRandomFlavourDescriptor() {
		return flavourDescriptors.get(Util.random.nextInt(flavourDescriptors.size()));
	}
	
	public static List<FluidFlavour> getUnnaturalFlavourings() {
		List<FluidFlavour> list = new ArrayList<>(Arrays.asList(FluidFlavour.values()));
		list.remove(CUM);
		list.remove(MILK);
		list.remove(GIRL_CUM);
		return list;
	}
}
