package com.lilithsthrone.game.inventory.outfit;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;

/**
 * @since 0.3.1
 * @version 0.3.7.7
 * @author Innoxia
 */
public class OutfitPotential {

	private List<AbstractClothingType> types;

	private List<List<Colour>> colours;
	
	public OutfitPotential(List<AbstractClothingType> types, List<List<Colour>> colours) {
		if(types!=null) {
			this.types = types;
		} else {
			types = new ArrayList<>();
		}
		this.colours = colours;
	}
	

	public List<AbstractClothingType> getTypes() {
		return types;
	}

	public List<Colour> getColoursForClothingGeneration() {
		List<Colour> coloursForGeneration = new ArrayList<>();
		for(List<Colour> c : getColourLists()) {
			coloursForGeneration.add(Util.randomItemFrom(c));
		}
		return coloursForGeneration;
	}
	
	public List<List<Colour>> getColourLists() {
		return colours;
	}

	public List<Colour> getColours(int index) {
		try {
			return getColourLists().get(index);
		} catch(Exception ex) {
			return new ArrayList<>();
		}
	}
}
