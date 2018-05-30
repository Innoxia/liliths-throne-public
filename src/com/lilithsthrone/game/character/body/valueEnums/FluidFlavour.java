package com.lilithsthrone.game.character.body.valueEnums;

import java.util.List;

import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum FluidFlavour {
	
	CUM("cum", BaseColour.WHITE,
			Util.newArrayListOfValues(
					"salty")),
	
	MILK("milk", BaseColour.WHITE,
			Util.newArrayListOfValues(
					"creamy")),
	
	GIRL_CUM("girl-cum", BaseColour.WHITE,
			Util.newArrayListOfValues(
					"sweet")),

	SLIME("slime", BaseColour.GREEN_LIGHT,
			Util.newArrayListOfValues(
					"sweet")),
	
	
	BEER("beer", BaseColour.TAN,
			Util.newArrayListOfValues(
					"yeasty",
					"beer-flavoured")),
	
	VANILLA("vanilla", BaseColour.YELLOW_LIGHT,
			Util.newArrayListOfValues(
					"sweet",
					"vanilla-flavoured")),
	
	STRAWBERRY("strawberries", BaseColour.ROSE,
			Util.newArrayListOfValues(
					"sweet",
					"strawberry-flavoured")),
	
	CHOCOLATE("chocolate", BaseColour.BROWN,
			Util.newArrayListOfValues(
					"chocolatey",
					"chocolate-flavoured")),
	
	PINEAPPLE("pineapple", BaseColour.YELLOW_LIGHT,
			Util.newArrayListOfValues(
					"tart",
					"sour",
					"tangy",
					"pineapple-flavoured")),
	
	HONEY("honey", BaseColour.YELLOW,
			Util.newArrayListOfValues(
					"sweet",
					"honey-flavoured")),
	
	MINT("mint", BaseColour.GREEN_LIME,
			Util.newArrayListOfValues(
					"minty"));
	
	private String name;
	private BaseColour colour;
	private List<String> flavourDescriptors;

	private FluidFlavour(String name, BaseColour colour, List<String> flavourDescriptors) {
		this.name = name;
		this.colour=colour;
		this.flavourDescriptors = flavourDescriptors;
	}
	
	/**
	 * To go into: "You can't get the rich strawberry taste out of your mouth."</br>
	 * Or: "Strawberry-flavoured"
	 */
	public String getName() {
		return name;
	}
	
	public BaseColour getColour() {
		return colour;
	}

	public List<String> getFlavourDescriptors() {
		return flavourDescriptors;
	}
	
	public String getRandomFlavourDescriptor() {
		return flavourDescriptors.get(Util.random.nextInt(flavourDescriptors.size()));
	}
}
