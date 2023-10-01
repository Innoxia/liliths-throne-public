package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.83
 * @version 0.3.9
 * @author Innoxia
 */
public enum FluidFlavour {
	
	CUM("cum", PresetColour.CUM,
			Util.newArrayListOfValues(
					"salty")),
	
	MILK("milk", PresetColour.MILK,
			Util.newArrayListOfValues(
					"creamy")),
	
	GIRL_CUM("girlcum", PresetColour.GIRLCUM,
			Util.newArrayListOfValues(
					"sweet")),
	
	FLAVOURLESS("flavourless", PresetColour.BASE_GREY,
			Util.newArrayListOfValues(
					"flavourless",
					"tasteless")),

	BUBBLEGUM("bubblegum", PresetColour.BASE_PINK_LIGHT,
			Util.newArrayListOfValues(
					"sweet")),
	
	BEER("beer", PresetColour.BASE_TAN,
			Util.newArrayListOfValues(
					"yeasty",
					"beer-flavoured")),
	
	VANILLA("vanilla", PresetColour.BASE_YELLOW_PALE,
			Util.newArrayListOfValues(
					"sweet",
					"vanilla-flavoured")),
	
	STRAWBERRY("strawberry", PresetColour.BASE_CRIMSON,
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
	
	CHERRY("cherry", PresetColour.BASE_RED_DARK,
			Util.newArrayListOfValues(
					"sweet",
					"cherry-flavoured")),
	
	// ------ Icons for these made by 'Charisma is my Dump Stat': ------ //
	
	COFFEE("coffee", PresetColour.BASE_BROWN_DARK,
			Util.newArrayListOfValues(
					"bitter",
					"coffee-flavoured")),
	
	TEA("tea", PresetColour.BASE_GREEN,
			Util.newArrayListOfValues(
					"tea-flavoured")),
	
	MAPLE("maple", PresetColour.BASE_RED,
			Util.newArrayListOfValues(
					"sweet",
					"maple-flavoured")),
	
	CINNAMON("cinnamon", PresetColour.BASE_BROWN,
			Util.newArrayListOfValues(
					"cinnamon-flavoured")),

	LEMON("lemon", PresetColour.BASE_YELLOW,
			Util.newArrayListOfValues(
					"sour",
					"lemon-flavoured")),
	
	// ------------ //
	
	// ------ Icons for these made by 'DSG': ------ //
	
	ORANGE("orange", PresetColour.BASE_ORANGE,
			Util.newArrayListOfValues(
					"orange-flavoured")),
	
	GRAPE("grape", PresetColour.BASE_PURPLE,
			Util.newArrayListOfValues(
					"grape-flavoured")),
	
	MELON("melon", PresetColour.BASE_GREEN_LIGHT,
			Util.newArrayListOfValues(
					"melon-flavoured")),
	
	COCONUT("coconut", PresetColour.BASE_BROWN_DARK,
			Util.newArrayListOfValues(
					"coconut-flavoured")),
	
	BLUEBERRY("blueberry", PresetColour.BASE_BLUE_DARK,
			Util.newArrayListOfValues(
					"blueberry-flavoured")),
	
	BANANA("banana", PresetColour.BASE_YELLOW_LIGHT,
			Util.newArrayListOfValues(
					"banana-flavoured"))
	
	// ------------ //
	
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
