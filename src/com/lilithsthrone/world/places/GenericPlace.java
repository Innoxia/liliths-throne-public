package com.lilithsthrone.world.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Bearing;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.EntranceType;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.?
 * @version 0.1.89
 * @author Innoxia
 */
public class GenericPlace implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private PlaceType placeType;
	private Set<PlaceUpgrade> placeUpgrades;
	
	public static Map<PlaceType, Integer> placeCountMap = new HashMap<>();

	public GenericPlace(PlaceType placeType) {
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
		Element element = doc.createElement("place");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "name", this.getName());
		CharacterUtils.addAttribute(doc, element, "type", this.getPlaceType().toString());
		
		Element innerElement = doc.createElement("placeUpgrades");
		element.appendChild(innerElement);
		
		for(PlaceUpgrade upgrade : this.getPlaceUpgrades()) {
			Element e = doc.createElement("upgrade");
			innerElement.appendChild(e);
			
			CharacterUtils.addAttribute(doc, e, "type", upgrade.toString());
		}
		
		return element;
	}
	
	public static GenericPlace loadFromXML(Element parentElement, Document doc) {
		String placeType = parentElement.getAttribute("type");
		
		if(placeType.equals("ZARANIX_FF_BEDROOM")) {
			placeType = "ZARANIX_FF_OFFICE";
		}

//		placeType = placeType.replace("SUPPLIERS_DEN", "SUPPLIER_DEPOT");
//		placeType = placeType.replace("SUPPLIER_DEN", "SUPPLIER_DEPOT");
		
		
		GenericPlace place = new GenericPlace(PlaceType.valueOf(placeType));
		place.setName(parentElement.getAttribute("name"));
		
		if(((Element) parentElement.getElementsByTagName("placeUpgrades").item(0)).getElementsByTagName("upgrade").getLength()>0) {
			List<PlaceUpgrade> coreUpgrades = new ArrayList<>();
			List<PlaceUpgrade> upgrades = new ArrayList<>();
			for(int i=0; i<((Element) parentElement.getElementsByTagName("placeUpgrades").item(0)).getElementsByTagName("upgrade").getLength(); i++){
				Element e = (Element) ((Element) parentElement.getElementsByTagName("placeUpgrades").item(0)).getElementsByTagName("upgrade").item(i);
				PlaceUpgrade upgrade = PlaceUpgrade.valueOf(e.getAttribute("type"));
				
				if(upgrade.isCoreRoomUpgrade()) {
					coreUpgrades.add(upgrade);
				} else {
					upgrades.add(upgrade);
				}
			}
			
			// Add core upgrades first:
			for(PlaceUpgrade coreUpgrade : coreUpgrades) {
				if(!place.getPlaceUpgrades().contains(coreUpgrade)) {
					if(!place.addPlaceUpgrade(coreUpgrade)) { // This line attempts to add the upgrade
						System.err.println("WARNING: Import of GenericPlace ("+place.getPlaceType()+") was unable to add core upgrade: "+coreUpgrade.getName());
					}
				}
			}
			for(PlaceUpgrade upgrade : upgrades) {
				if(!place.getPlaceUpgrades().contains(upgrade)) {
					if(!place.addPlaceUpgrade(upgrade)) { // This line attempts to add the upgrade
						System.err.println("WARNING: Import of GenericPlace ("+place.getPlaceType()+") was unable to add upgrade: "+upgrade.getName());
					}
				}
			}
		}
		
		return place;
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
		return placeType.getSVGString(placeUpgrades);
	}
	
	
	// For determining where this place should be placed:
	
	public Bearing getBearing() {
		return placeType.getBearing();
	}
	
	public WorldType getParentWorldType() {
		return placeType.getParentWorldType();
	}
	
	public PlaceType getParentPlaceType() {
		return placeType.getParentPlaceType();
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
	
	public float getHourlyAffectionChange() {
		float affectionChange = 0;
		for(PlaceUpgrade pu : placeUpgrades) {
			affectionChange+=pu.getHourlyAffectionGain();
		}
		return affectionChange;
	}
	
	public float getHourlyObedienceChange() {
		float obedienceChange = 0;
		for(PlaceUpgrade pu : placeUpgrades) {
			obedienceChange+=pu.getHourlyObedienceGain();
		}
		return obedienceChange;
	}
	

	public PlaceType getPlaceType() {
		return placeType;
	}

	public void setPlaceType(PlaceType placeType) {
		this.placeType = placeType;
	}

}
