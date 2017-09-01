package com.base.game.character.body.valueEnums;

import java.util.List;

import com.base.utils.BaseColour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum FluidFlavour {
	
	CUM("cum", BaseColour.WHITE,
			Util.newArrayListOfValues(
					new ListValue<>("salty"))),
	
	MILK("milk", BaseColour.WHITE,
			Util.newArrayListOfValues(
					new ListValue<>("creamy"))),
	
	GIRL_CUM("girl-cum", BaseColour.WHITE,
			Util.newArrayListOfValues(
					new ListValue<>("sweet"))),

	SLIME("slime", BaseColour.GREEN_LIGHT,
			Util.newArrayListOfValues(
					new ListValue<>("sweet"))),
	
	
	BEER("beer", BaseColour.TAN,
			Util.newArrayListOfValues(
					new ListValue<>("yeasty"),
					new ListValue<>("beer-flavoured"))),
	
	VANILLA("vanilla", BaseColour.YELLOW_LIGHT,
			Util.newArrayListOfValues(
					new ListValue<>("sweet"),
					new ListValue<>("vanilla-flavoured"))),
	
	STRAWBERRY("strawberries", BaseColour.ROSE,
			Util.newArrayListOfValues(
					new ListValue<>("sweet"),
					new ListValue<>("strawberry-flavoured"))),
	
	CHOCOLATE("chocolate", BaseColour.BROWN,
			Util.newArrayListOfValues(
					new ListValue<>("chocolatey"),
					new ListValue<>("chocolate-flavoured"))),
	
	PINEAPPLE("pineapple", BaseColour.YELLOW_LIGHT,
			Util.newArrayListOfValues(
					new ListValue<>("tart"),
					new ListValue<>("sour"),
					new ListValue<>("tangy"),
					new ListValue<>("pineapple-flavoured"))),
	
	HONEY("honey", BaseColour.YELLOW,
			Util.newArrayListOfValues(
					new ListValue<>("sweet"),
					new ListValue<>("honey-flavoured"))),
	
	MINT("mint", BaseColour.GREEN_LIME,
			Util.newArrayListOfValues(
					new ListValue<>("minty")));
	
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
