package com.lilithsthrone.game.inventory.enchanting;
import java.io.IOException;
import java.io.InputStream;

import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.SvgUtil;
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
			s = SvgUtil.colourReplacement(this.toString(), Colour.BASE_GREY, s);
			this.SVGStringUncoloured = s;

			String s2 = base;
			s2 = SvgUtil.colourReplacement(this.toString(), colour, s2);
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
