package com.lilithsthrone.game.inventory.enchanting;

import java.io.IOException;
import java.io.InputStream;

import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.7
 * @version 0.1.83
 * @author Innoxia
 */
public enum TFEssence {
	
	ARCANE("Arcane",
			"An exceedingly rare pure arcane essence.",
			"arcane",
			"essenceArcane",
			Colour.GENERIC_ARCANE,
			Rarity.LEGENDARY);
	

	private String name, description, descriptor, SVGString, SVGStringUncoloured;
	private Colour colour;
	private Rarity rarity;

	private TFEssence(String name, String description, String descriptor, String SVGString, Colour colour, Rarity rarity) {
		this.name = name;
		this.description = description;
		this.descriptor = descriptor;
		this.SVGString = SVGString;
		this.SVGStringUncoloured = SVGString;
		this.colour = colour;
		this.rarity = rarity;
		
		// Set this item's file image:
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/crafting/" + SVGString + ".svg");
			if(is==null) {
				System.err.println("Error! TFEssence icon file does not exist (Trying to read from '"+SVGString+"')!");
			}
			String base = Util.inputStreamToString(is);
			
			String s = base;
			s = s.replaceAll("#ff2a2a", Colour.BASE_GREY.getShades()[0]);
			s = s.replaceAll("#ff5555|#f55", Colour.BASE_GREY.getShades()[1]);
			s = s.replaceAll("#ff8080", Colour.BASE_GREY.getShades()[2]);
			s = s.replaceAll("#ffaaaa|#faa", Colour.BASE_GREY.getShades()[3]);
			s = s.replaceAll("#ffd5d5", Colour.BASE_GREY.getShades()[4]);
			this.SVGStringUncoloured = s;

			String s2 = base;
			s2 = s2.replaceAll("#ff2a2a", this.colour.getShades()[0]);
			s2 = s2.replaceAll("#ff5555|#f55", this.colour.getShades()[1]);
			s2 = s2.replaceAll("#ff8080", this.colour.getShades()[2]);
			s2 = s2.replaceAll("#ffaaaa|#faa", this.colour.getShades()[3]);
			s2 = s2.replaceAll("#ffd5d5", this.colour.getShades()[4]);
			this.SVGString = s2;

			is.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public String getSVGString() {
		return SVGString;
	}
	
	public String getSVGStringUncoloured() {
		return SVGStringUncoloured;
	}

	public Colour getColour() {
		return colour;
	}

	public Rarity getRarity() {
		return rarity;
	}
	
	public static AbstractItemType essenceToItem(TFEssence essence) {
		return ItemType.BOTTLED_ESSENCE_ARCANE;
	}
}
