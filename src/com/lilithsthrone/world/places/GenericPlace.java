package com.lilithsthrone.world.places;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Bearing;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.EntranceType;
import com.lilithsthrone.world.WorldType;

public class GenericPlace implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private PlaceInterface placeType;
	private Set<PlaceUpgrade> placeUpgrades;
	
	public static Map<PlaceInterface, Integer> placeCountMap = new HashMap<>();

	public GenericPlace(PlaceInterface placeType) {
		this.placeType=placeType;
		placeUpgrades = new HashSet<>();
		
		if(placeCountMap.containsKey(placeType)) {
			placeCountMap.put(placeType, placeCountMap.get(placeType)+1);
		} else {
			placeCountMap.put(placeType, 1);
		}
		
		if(placeType!=null) {
			this.name = placeType.getName() + placeType.getPlaceNameAppendFormat(placeCountMap.get(placeType));
			for(PlaceUpgrade pu : placeType.getStartingPlaceUpgrades()) {
				placeUpgrades.add(pu);
			}
		} else {
			this.name = "-";
		}
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		return null;
	}
	
	public static GenericPlace loadFromXML(Element parentElement, Document doc) {
		return null;
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
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public boolean isAbleToBeUpgraded() {
		return placeType.isAbleToBeUpgraded();
	}
	
	public Set<PlaceUpgrade> getPlaceUpgrades() {
		return placeUpgrades;
	}
	
	public int getCapacity() {
		int c = 0;
		for(PlaceUpgrade pu : placeUpgrades) {
			c+=pu.getCapacity();
		}
		return c;
	}
	
	public int getUpkeep() {
		int upkeep = 0;
		for(PlaceUpgrade pu : placeUpgrades) {
			upkeep+=pu.getUpkeep();
		}
		if(upkeep<0) {
			return 0;
		}
		return upkeep;
	}
	
	public float getAffectionChange() {
		float affectionChange = 0;
		for(PlaceUpgrade pu : placeUpgrades) {
			affectionChange+=pu.getAffectionGain();
		}
		return affectionChange;
	}
	
	public float getObedienceChange() {
		float obedienceChange = 0;
		for(PlaceUpgrade pu : placeUpgrades) {
			obedienceChange+=pu.getObedienceGain();
		}
		return obedienceChange;
	}
	

	public PlaceInterface getPlaceType() {
		return placeType;
	}

	public void setPlaceType(PlaceInterface placeType) {
		this.placeType = placeType;
	}

}
