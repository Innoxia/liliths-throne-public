package com.base.world.places;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.base.game.dialogue.DialogueNodeOld;
import com.base.utils.BaseColour;
import com.base.utils.Bearing;
import com.base.world.EntranceType;
import com.base.world.WorldType;

public class GenericPlace implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PlaceInterface placeType;
	
	private Set<PlaceUpgrade> placeUpgrades;

	public GenericPlace(PlaceInterface placeType) {
		this.placeType=placeType;
		placeUpgrades = new HashSet<>();
	}
	
	@Override
	public boolean equals (Object o) {
		if(o instanceof GenericPlace){
			if(((GenericPlace)o).getPlaceType() == placeType
				&& ((GenericPlace)o).getPlaceUpgrades().equals(placeUpgrades)){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + placeType.hashCode();
		result = 31 * result + placeUpgrades.hashCode();
		return result;
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
	
	public boolean addPlaceUpgrade(PlaceUpgrade upgrade) {
		if(placeUpgrades.add(upgrade)) {
			upgrade.applyInstallationEffects(this);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removePlaceUpgrade(PlaceUpgrade upgrade) {
		if(placeUpgrades.remove(upgrade)) {
			upgrade.applyRemovalEffects(this);
			return true;
		} else {
			return false;
		}
	}
	
	public Set<PlaceUpgrade> getPlaceUpgrades() {
		return placeUpgrades;
	}

	public PlaceInterface getPlaceType() {
		return placeType;
	}

	public void setPlaceType(PlaceInterface placeType) {
		this.placeType = placeType;
	}

}
