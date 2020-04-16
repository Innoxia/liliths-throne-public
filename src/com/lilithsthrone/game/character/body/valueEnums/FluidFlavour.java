package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.83
 * @version 0.3.5.5
 * @author Innoxia
 */
public enum FluidFlavour {
	
	CUM("cum", PresetColour.BASE_WHITE,
			Util.newArrayListOfValues(
					"salty")),
	
	MILK("milk", PresetColour.BASE_WHITE,
			Util.newArrayListOfValues(
					"creamy")),
	
	GIRL_CUM("girl-cum", PresetColour.BASE_WHITE,
			Util.newArrayListOfValues(
					"sweet")),

	BUBBLEGUM("bubblegum", PresetColour.BASE_PINK_LIGHT,
			Util.newArrayListOfValues(
					"sweet")),
	
	
	BEER("beer", PresetColour.BASE_TAN,
			Util.newArrayListOfValues(
					"yeasty",
					"beer-flavoured")),
	
	VANILLA("vanilla", PresetColour.BASE_YELLOW_LIGHT,
			Util.newArrayListOfValues(
					"sweet",
					"vanilla-flavoured")),
	
	STRAWBERRY("strawberries", PresetColour.BASE_RED,
			Util.newArrayListOfValues(
					"sweet",
					"strawberry-flavoured")),
	
	CHOCOLATE("chocolate", PresetColour.BASE_BROWN,
			Util.newArrayListOfValues(
					"chocolatey",
					"chocolate-flavoured")),
	
	PINEAPPLE("pineapple", PresetColour.BASE_YELLOW_LIGHT,
			Util.newArrayListOfValues(
					"tart",
					"sour",
					"tangy",
					"pineapple-flavoured")),
	
	HONEY("honey", PresetColour.BASE_YELLOW,
			Util.newArrayListOfValues(
					"sweet",
					"honey-flavoured")),
	
	MINT("mint", PresetColour.BASE_GREEN_LIME,
			Util.newArrayListOfValues(
					"minty")),
	
	CHERRY("cherry", PresetColour.BASE_CRIMSON,
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
