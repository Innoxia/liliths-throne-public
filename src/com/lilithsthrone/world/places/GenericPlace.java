package com.lilithsthrone.world.places;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.EntranceType;

/**
 * @since 0.1.?
 * @version 0.3.9
 * @author Innoxia
 */
public class GenericPlace implements XMLSaving {
	
	private String name;
	private AbstractPlaceType placeType;
	private Set<AbstractPlaceUpgrade> placeUpgrades;
	
	public static Map<AbstractPlaceType, Integer> placeCountMap = new ConcurrentHashMap<>();

	public GenericPlace(AbstractPlaceType placeType) {
		this.placeType=placeType;
		this.placeUpgrades = new HashSet<>();
		
		if(placeType!=null) {
			placeCountMap.put(placeType, placeCountMap.getOrDefault(placeType, 0) + 1);

			String placeNameExtra = placeType.getPlaceNameAppendFormat(placeCountMap.get(placeType));
			if (placeNameExtra != null && !placeNameExtra.isEmpty()) {
				this.name = placeType.getName() + placeNameExtra;
			}

			placeUpgrades.addAll(placeType.getStartingPlaceUpgrades());
			
		} else {
			this.name = "";
		}
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("place");
		parentElement.appendChild(element);
		
		if(!this.getName().equals(this.getPlaceType().getName())) {
			XMLUtil.addAttribute(doc, element, "name", this.getName());
		}
		XMLUtil.addAttribute(doc, element, "type", PlaceType.getIdFromPlaceType(this.getPlaceType()));
		
		if(!this.getPlaceUpgrades().isEmpty()) {
			Element innerElement = doc.createElement("placeUpgrades");
			element.appendChild(innerElement);
			for(AbstractPlaceUpgrade upgrade : this.getPlaceUpgrades()) {
				Element e = doc.createElement("upgrade");
				innerElement.appendChild(e);
				
				XMLUtil.addAttribute(doc, e, "type", PlaceUpgrade.getIdFromPlaceUpgrade(upgrade));
			}
		}
		return element;
	}
	
	public static GenericPlace loadFromXML(Element parentElement, Document doc, Cell c) {
		String placeType = parentElement.getAttribute("type");
		
		AbstractPlaceType pt = PlaceType.getPlaceTypeFromId(placeType);
		GenericPlace place = new GenericPlace(pt);
		
		if(!parentElement.getAttribute("name").isEmpty()
				&& (!Main.isVersionOlderThan(Game.loadingVersion, "0.3")
					|| (!place.getPlaceType().equals(PlaceType.DOMINION_EXIT_NORTH)
						&& !place.getPlaceType().equals(PlaceType.DOMINION_EXIT_SOUTH)
						&& !place.getPlaceType().equals(PlaceType.DOMINION_EXIT_EAST)
						&& !place.getPlaceType().equals(PlaceType.DOMINION_EXIT_WEST)))) {
			String name = parentElement.getAttribute("name");
			if(!pt.getName().equals(name)) {
				place.setName(name);
			}
		}
		
		try {
			if(parentElement.getElementsByTagName("placeUpgrades").getLength()>0 && ((Element) parentElement.getElementsByTagName("placeUpgrades").item(0)).getElementsByTagName("upgrade").getLength()>0) {
				List<AbstractPlaceUpgrade> coreUpgrades = new ArrayList<>();
				List<AbstractPlaceUpgrade> upgrades = new ArrayList<>();
				for(int i=0; i<((Element) parentElement.getElementsByTagName("placeUpgrades").item(0)).getElementsByTagName("upgrade").getLength(); i++){
					Element e = (Element) ((Element) parentElement.getElementsByTagName("placeUpgrades").item(0)).getElementsByTagName("upgrade").item(i);
					String placeUpgradeId = e.getAttribute("type");
					if(placeUpgradeId.equals("LILAYA_PLAYER_ROOM_BATH")) {
						Cell.refundMoney += 300_000;
						
					} else {
						AbstractPlaceUpgrade upgrade = PlaceUpgrade.getPlaceUpgradeFromId(placeUpgradeId);
						
						if(upgrade.isCoreRoomUpgrade()) {
							coreUpgrades.add(upgrade);
						} else {
							upgrades.add(upgrade);
						}
					}
				}
				
				place.getPlaceUpgrades().clear();
				
				// Add core upgrades first:
				for(AbstractPlaceUpgrade coreUpgrade : coreUpgrades) {
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
				for(AbstractPlaceUpgrade upgrade : upgrades) {
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
		if((name==null || name.isEmpty()) && this.getPlaceType()!=null) {
			return this.getPlaceType().getName();
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Colour getColour() {
		if(this.getPlaceUpgrades().stream().anyMatch(up->up.isCoreRoomUpgrade())) {
			return this.getPlaceUpgrades().stream().filter(up -> up.isCoreRoomUpgrade()).collect(Collectors.toList()).get(0).getColour();
		}
		return placeType.getColour();
	}

	public DialogueNode getDialogue(Cell c, boolean withRandomEncounter) {
		return getDialogue(c, withRandomEncounter, false);
	}
	
	public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
		return placeType.getDialogue(c, withRandomEncounter, forceEncounter);
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
	
	public boolean addPlaceUpgrade(Cell c, AbstractPlaceUpgrade upgrade) {
		if(placeUpgrades.add(upgrade)) {
			upgrade.applyInstallationEffects(c);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removePlaceUpgrade(Cell c, AbstractPlaceUpgrade upgrade) {
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
	
	public Set<AbstractPlaceUpgrade> getPlaceUpgrades() {
		return placeUpgrades;
	}
	
	public boolean isSlaveCell() {
		for(AbstractPlaceUpgrade upgrade : placeUpgrades) {
			if(upgrade.isCoreRoomUpgrade() && upgrade.isSlaverUpgrade()) {
				return true;
			}
		}
		return false;
	}
	
	public int getCapacity() {
		int c = 0;
		for(AbstractPlaceUpgrade pu : placeUpgrades) {
			c+=pu.getCapacity();
		}
		return c;
	}
	
	public int getUpkeep() {
		int upkeep = 0;
		for(AbstractPlaceUpgrade pu : placeUpgrades) {
			upkeep+=pu.getUpkeep();
		}
		if(upkeep<0) {
			return 0;
		}
		return upkeep;
	}
	
	public float getHourlyAffectionChange() {
		float affectionChange = 0;
		for(AbstractPlaceUpgrade pu : placeUpgrades) {
			affectionChange+=pu.getHourlyAffectionGain();
		}
		return affectionChange;
	}
	
	public float getHourlyObedienceChange() {
		float obedienceChange = 0;
		for(AbstractPlaceUpgrade pu : placeUpgrades) {
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
