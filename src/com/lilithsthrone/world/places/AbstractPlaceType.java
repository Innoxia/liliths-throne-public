package com.lilithsthrone.world.places;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Bearing;
import com.lilithsthrone.world.EntranceType;
import com.lilithsthrone.world.TeleportPermissions;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.population.Population;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class AbstractPlaceType {

	protected String name;
	protected String tooltipDescription;
	protected String SVGString;
	protected Colour colour;
	protected Colour backgroundColour;
	protected DialogueNode dialogue;
	protected Encounter encounterType;

	protected boolean globalMapTile;
	protected boolean dangerous;
	protected boolean itemsDisappear;
	
	protected TeleportPermissions teleportPermissions;
	
	protected List<Weather> weatherImmunities;
	protected static List<Weather> allWeatherImmunities = new ArrayList<>(Arrays.asList(Weather.values()));
	
	protected String virginityLossDescription;

	protected static Map<String, String> SVGOverrides = new HashMap<>(); 
	
	protected static int colourReplacementId = 0;
	
	public AbstractPlaceType(String name,
			String tooltipDescription,
			String SVGPath,
			Colour colour,
			DialogueNode dialogue,
			Encounter encounterType,
			String virginityLossDescription) {
		
		this.name = name;
		this.tooltipDescription = tooltipDescription;
		this.colour = colour;

		this.backgroundColour = PresetColour.MAP_BACKGROUND;
		
		this.dialogue = dialogue;
		this.encounterType = encounterType;
		this.weatherImmunities = new ArrayList<>();
		this.virginityLossDescription = virginityLossDescription;

		this.dangerous = false;
		this.itemsDisappear = true;
		this.globalMapTile = false;
		this.teleportPermissions = TeleportPermissions.BOTH;
		
		if(SVGPath!=null) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/" + SVGPath + ".svg");
				if(is==null) {
					System.err.println("Error! PlaceType icon file does not exist (Trying to read from '"+SVGPath+"')! (Code 1)");
				}
				String s = Util.inputStreamToString(is);
				
				try {
					s = SvgUtil.colourReplacement("placeColour"+colourReplacementId, colour, s);
					colourReplacementId++;
				} catch(Exception ex) {
					System.err.println(SVGPath+" error!");
				}
				
				SVGString = s;
	
				is.close();
	
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			SVGString = null;
		}
	}
	
	public AbstractPlaceType initDangerous() {
		this.dangerous = true;
		return this;
	}
	
	public AbstractPlaceType initItemsPersistInTile() {
		this.itemsDisappear = false;
		return this;
	}
	
	public AbstractPlaceType initMapBackgroundColour(Colour backgroundColour) {
		this.backgroundColour = backgroundColour;
		return this;
	}
	
	/**
	 * Sets weather immunity to all Weather values.
	 */
	public AbstractPlaceType initWeatherImmune() {
		this.weatherImmunities = allWeatherImmunities;
		return this;
	}

	/**
	 * Define weather immunity for this place.
	 */
	public AbstractPlaceType initWeatherImmune(Weather... weatherImmunities) {
		this.weatherImmunities = new ArrayList<>(Arrays.asList(weatherImmunities));
		return this;
	}

	/**
	 * Define teleport permissions for this tile.
	 * TeleportPermissions are also defined in WorldType, so this will only work in special cases where a world allows teleporting, but not on some tiles (such as Lyssieth's palace in Submission).
	 */
	public AbstractPlaceType initTeleportPermissions(TeleportPermissions teleportPermissions) {
		this.teleportPermissions = teleportPermissions;
		return this;
	}
	
	public String getName() {
		return name;
	}

	public String getTooltipDescription() {
		return tooltipDescription;
	}

	public Colour getColour() {
		return colour;
	}

	public Colour getBackgroundColour() {
		if(backgroundColour==PresetColour.MAP_BACKGROUND && this.isDangerous()) {
			return PresetColour.MAP_BACKGROUND_DANGEROUS;
		}
		return backgroundColour;
	}

	public Encounter getEncounterType() {
		return encounterType;
	}

	public DialogueNode getDialogue(boolean withRandomEncounter) {
		return getDialogue(withRandomEncounter, false);
	}
	
	public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
		if(getEncounterType()!=null && withRandomEncounter) {
			DialogueNode dn = getEncounterType().getRandomEncounter(forceEncounter);
			if (dn != null) {
				return dn;
			}
		}

		return dialogue;
	}
	
	public List<Population> getPopulation() {
		return new ArrayList<>();
	}
	
	public boolean isPopulated() {
		if(getPopulation()!=null) {
			for(Population pop : getPopulation()) {
				if(!pop.getSpecies().isEmpty()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isLand() {
		return true;
	}
	
	public boolean isDangerous() {
		return dangerous;
	}

	public boolean isStormImmune() {
		return weatherImmunities.contains(Weather.MAGIC_STORM);
	}
	
	public boolean isItemsDisappear() {
		return itemsDisappear;
	}
	
	protected static String getSVGOverride(String pathName, Colour colour) {
		if(!SVGOverrides.keySet().contains(pathName+colour.getId())) {
			try {
				InputStream is = colour.getClass().getResourceAsStream("/com/lilithsthrone/res/map/" + pathName + ".svg");
				if(is==null) {
					System.err.println("Error! PlaceType icon file does not exist (Trying to read from '"+pathName+"')! (Code 2)");
				}
				String s = Util.inputStreamToString(is);
				

				try {
					s = SvgUtil.colourReplacement("placeColour"+colourReplacementId, colour, s);
					colourReplacementId++;
				} catch(Exception ex) {
					System.err.println(pathName+" error!");
				}
				
				SVGOverrides.put(pathName+colour.getId(), s);
	
				is.close();
	
			} catch (Exception e1) {
				System.err.println("Error! AbstractPlaceType: PlaceType.getSVGOverride()");
				e1.printStackTrace();
				return "";
			}
		}
		
		return SVGOverrides.get(pathName+colour.getId());
	}
	
	public String getSVGString(Set<PlaceUpgrade> upgrades) {
		return SVGString;
	}
	
	public void applyInventoryInit(CharacterInventory inventory) {
		
	}
	
	// For determining where this place should be placed:
	
	public Bearing getBearing() {
		return null;
	}
	
	public WorldType getParentWorldType() {
		return null;
	}
	
	public AbstractPlaceType getParentPlaceType() {
		return null;
	}
	
	public EntranceType getParentAlignment() {
		return null;
	}
	
	public String getPlaceNameAppendFormat(int count) {
		return "";
	}
	
	public boolean isAbleToBeUpgraded() {
		return false;
	}
	
	public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
		return new ArrayList<>();
	}
	
	public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
		return new ArrayList<>();
	}

	//TODO This should be in PlaceUpgrade...
	public static ArrayList<PlaceUpgrade> getAvailableLilayaRoomPlaceUpgrades(Set<PlaceUpgrade> upgrades) {
		if(upgrades.contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
			return PlaceUpgrade.getGuestRoomUpgrades();
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM)) {
			return PlaceUpgrade.getSlaveQuartersUpgradesSingle();
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE)) {
			return PlaceUpgrade.getSlaveQuartersUpgradesDouble();
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE)) {
			return PlaceUpgrade.getSlaveQuartersUpgradesQuadruple();
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
			return PlaceUpgrade.getMilkingUpgrades();
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_OFFICE)) {
			return PlaceUpgrade.getOfficeUpgrades();
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SPA)) {
			return PlaceUpgrade.getSpaUpgrades();
		}
		
		return PlaceUpgrade.getCoreRoomUpgrades();
	}

	//TODO This should be in PlaceUpgrade...
	public String getLilayaRoomSVGString(Set<PlaceUpgrade> upgrades) {
		if(upgrades.contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
			return getSVGOverride("dominion/lilayasHome/roomGuest", PresetColour.BASE_GREEN_LIGHT);
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SPA)) {
			return getSVGOverride("dominion/lilayasHome/roomSpa", PresetColour.BASE_AQUA);
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM)) {
			return getSVGOverride("dominion/lilayasHome/roomSlave", PresetColour.BASE_CRIMSON);
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
			return getSVGOverride("dominion/lilayasHome/roomMilking", PresetColour.BASE_YELLOW_LIGHT);
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_OFFICE)) {
			return getSVGOverride("dominion/lilayasHome/roomOffice", PresetColour.BASE_LILAC);
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE)) {
			return getSVGOverride("dominion/lilayasHome/roomSlaveDouble", PresetColour.BASE_MAGENTA);
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE)) {
			return getSVGOverride("dominion/lilayasHome/roomSlaveQuadruple", PresetColour.BASE_MAGENTA);
			
		} else {
			return SVGString;
		}
	}

	public String getVirginityLossDescription() {
		return virginityLossDescription;
	}

	public boolean isGlobalMapTile() {
		return globalMapTile;
	}

	/**
	 * TeleportPermissions are also defined in WorldType, so this will only work in special cases where a world allows teleporting, but not on some tiles (such as Lyssieth's palace in Submission).
	 */
	public TeleportPermissions getTeleportPermissions() {
		return teleportPermissions;
	}
}
