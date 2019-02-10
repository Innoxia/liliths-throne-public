package com.lilithsthrone.game.inventory.clothing;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.utils.Colour;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class OutfitPotential {

	private List<AbstractClothingType> types;

	private List<Colour> primaryColours;

	private List<Colour> secondaryColours;

	private List<Colour> tertiaryColours;
	
	public OutfitPotential(List<AbstractClothingType> types, List<Colour> primaryColours, List<Colour> secondaryColours, List<Colour> tertiaryColours) {
		if(types!=null) {
			this.types = types;
		} else {
			types = new ArrayList<>();
		}
		if(primaryColours!=null) {
			this.primaryColours = primaryColours;
		} else {
			primaryColours = new ArrayList<>();
		}
		if(secondaryColours!=null) {
			this.secondaryColours = secondaryColours;
		} else {
			secondaryColours = new ArrayList<>();
		}
		if(tertiaryColours!=null) {
			this.tertiaryColours = tertiaryColours;
		} else {
			tertiaryColours = new ArrayList<>();
		}
	}
	

	public List<AbstractClothingType> getTypes() {
		return types;
	}

	public List<Colour> getPrimaryColours() {
		return primaryColours;
	}

	public List<Colour> getSecondaryColours() {
		return secondaryColours;
	}

	public List<Colour> getTertiaryColours() {
		return tertiaryColours;
	}
}
