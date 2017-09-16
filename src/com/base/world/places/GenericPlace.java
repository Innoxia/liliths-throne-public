package com.base.world.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.base.game.dialogue.DialogueNodeOld;
import com.base.utils.BaseColour;
import com.base.utils.Bearing;
import com.base.world.EntranceType;
import com.base.world.WorldType;

public class GenericPlace implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PlaceInterface placeType;
	
	private List<PlaceUpgrade> placeUpgrades;

	GenericPlace(PlaceInterface placeType) {
		this.placeType=placeType;
		placeUpgrades = new ArrayList<>();
	}

	public String getName() {
		return placeType.getName();
	}

	public BaseColour getColour() {
		return placeType.getColour();
	}

	public DialogueNodeOld getDialogue(boolean withRandomEncounter) {
		return placeType.getDialogue(withRandomEncounter);
	}

	public boolean isPopulated() {
		return placeType.isPopulated();
	}

	public boolean isDangerous() {
		return placeType.isDangerous();
	}

	public boolean isStormImmune() {
		return placeType.isStormImmune();
	}
	
	public boolean isItemsDisappear() {
		return placeType.isItemsDisappear();
	}

	public String getSVGString() {
		return placeType.getSVGString();
	}
	
	
	// For determining where this place should be placed:
	
	public Bearing getBearing() {
		return placeType.getBearing();
	}
	
	public WorldType getParentWorldType() {
		return placeType.getParentWorldType();
	}
	
	public PlaceInterface getParentPlaceInterface() {
		return placeType.getParentPlaceInterface();
	}
	
	public EntranceType getParentAlignment() {
		return placeType.getParentAlignment();
	}
	
	
	// For porting to another world:
	
	public WorldType getLinkedWorldType() {
		return placeType.getLinkedWorldType();
	}
	
	public PlaceInterface getLinkedPlaceInterface() {
		return placeType.getLinkedPlaceInterface();
	}

	public List<PlaceUpgrade> getPlaceUpgrades() {
		return placeUpgrades;
	}

}
