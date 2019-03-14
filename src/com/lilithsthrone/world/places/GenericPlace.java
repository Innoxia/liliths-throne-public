package com.lilithsthrone.world.places;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.FileUtils;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.EntranceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.*;

/**
 * @since 0.1.?
 * @version 0.3
 * @author Innoxia
 */
public class GenericPlace implements XMLSaving {
	
	private String name;
	private AbstractPlaceType placeType;
	private Set<PlaceUpgrade> placeUpgrades;
	
	public static Map<AbstractPlaceType, Integer> placeCountMap = new HashMap<>();

	public GenericPlace(AbstractPlaceType placeType) {
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
		
		if(!this.getName().equals(this.getPlaceType().getName())) {
			CharacterUtils.addAttribute(doc, element, "name", this.getName());
		}
		CharacterUtils.addAttribute(doc, element, "type", PlaceType.getIdFromPlaceType(this.getPlaceType()));
		
		if(!this.getPlaceUpgrades().isEmpty()) {
			Element innerElement = doc.createElement("placeUpgrades");
			element.appendChild(innerElement);
			for(PlaceUpgrade upgrade : this.getPlaceUpgrades()) {
				Element e = doc.createElement("upgrade");
				innerElement.appendChild(e);
				
				CharacterUtils.addAttribute(doc, e, "type", upgrade.toString());
			}
		}
		return element;
	}
	
	public static GenericPlace loadFromXML(Element parentElement, Document doc, Cell c) {
		String placeType = parentElement.getAttribute("type");
		
		if(placeType.equals("ZARANIX_FF_BEDROOM")) {
			placeType = "ZARANIX_FF_OFFICE";
			
		} else if(placeType.equals("LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_SLAVE")
				|| placeType.equals("LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_MILKING")) {
			placeType = "LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR";
			
		} else if(placeType.equals("LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE")
				|| placeType.equals("LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_MILKING")) {
			placeType = "LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR";
			
		} else if(placeType.equals("LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_SLAVE")
				|| placeType.equals("LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_MILKING")) {
			placeType = "LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR";
			
		} else if(placeType.equals("LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_SLAVE")
				|| placeType.equals("LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_MILKING")) {
			placeType = "LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR";
			
		}
		else if(placeType.equals("DOMINION_EXIT_TO_JUNGLE")) {
			placeType = "DOMINION_EXIT_EAST";
		}
		else if(placeType.equals("DOMINION_EXIT_TO_DESERT")) {
			placeType = "DOMINION_EXIT_SOUTH";
		}
		else if(placeType.equals("DOMINION_EXIT_TO_FIELDS")) {
			placeType = "DOMINION_EXIT_NORTH";
		}
		else if(placeType.equals("DOMINION_EXIT_TO_SEA")) {
			placeType = "DOMINION_EXIT_WEST";
		}

		GenericPlace place = new GenericPlace(PlaceType.getPlaceTypeFromId(placeType));
		if(!parentElement.getAttribute("name").isEmpty()
				&& (!Main.isVersionOlderThan(Game.loadingVersion, "0.3")
					|| (!place.getPlaceType().equals(PlaceType.DOMINION_EXIT_NORTH)
						&& !place.getPlaceType().equals(PlaceType.DOMINION_EXIT_SOUTH)
						&& !place.getPlaceType().equals(PlaceType.DOMINION_EXIT_EAST)
						&& !place.getPlaceType().equals(PlaceType.DOMINION_EXIT_WEST)))) {
			place.setName(FileUtils.validate(parentElement.getAttribute("name")));
		}
		
		try {
			if(parentElement.getElementsByTagName("placeUpgrades").getLength()>0 && ((Element) parentElement.getElementsByTagName("placeUpgrades").item(0)).getElementsByTagName("upgrade").getLength()>0) {
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
				
				place.getPlaceUpgrades().clear();
				
				// Add core upgrades first:
				for(PlaceUpgrade coreUpgrade : coreUpgrades) {
					if(coreUpgrade==PlaceUpgrade.LILAYA_EMPTY_ROOM && coreUpgrades.size()>1) {
						continue;
					}
					if(!place.getPlaceUpgrades().contains(coreUpgrade)) {
						if(!place.addPlaceUpgrade(c, coreUpgrade)) { // This line attempts to add the upgrade
							System.err.println("WARNING: Import of GenericPlace ("+place.getPlaceType()+") was unable to add core upgrade: "+coreUpgrade.getName());
						} else {
							break; // There should only be one core upgrade added.
						}
					}
				}
				for(PlaceUpgrade upgrade : upgrades) {
					if(!place.getPlaceUpgrades().contains(upgrade)) {
						if(!place.addPlaceUpgrade(c, upgrade)) { // This line attempts to add the upgrade
							System.err.println("WARNING: Import of GenericPlace ("+place.getPlaceType()+") was unable to add upgrade: "+upgrade.getName());
						}
					}
				}
			}
		} catch(Exception ex) {
			System.err.println("GenericPlace import error 1");
		}
		
		return place;
	}
	
	@Override
	public boolean equals(Object o) {
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

	public String getColourString() {
		return placeType.getColourString();
	}

	public DialogueNode getDialogue(boolean withRandomEncounter) {
		return getDialogue(withRandomEncounter, false);
	}
	
	public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
		return placeType.getDialogue(withRandomEncounter, forceEncounter);
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
	
	public AbstractPlaceType getParentPlaceType() {
		return placeType.getParentPlaceType();
	}
	
	public EntranceType getParentAlignment() {
		return placeType.getParentAlignment();
	}
	
	public boolean addPlaceUpgrade(Cell c, PlaceUpgrade upgrade) {
		if(placeUpgrades.add(upgrade)) {
			upgrade.applyInstallationEffects(c);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removePlaceUpgrade(Cell c, PlaceUpgrade upgrade) {
		if(placeUpgrades.remove(upgrade)) {
			upgrade.applyRemovalEffects(c);
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
	
	public boolean isSlaveCell() {
		for(PlaceUpgrade upgrade : placeUpgrades) {
			if(upgrade.isCoreRoomUpgrade() && upgrade.isSlaverUpgrade()) {
				return true;
			}
		}
		return false;
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
	

	public AbstractPlaceType getPlaceType() {
		return placeType;
	}

	public void setPlaceType(AbstractPlaceType placeType) {
		this.placeType = placeType;
	}

}
