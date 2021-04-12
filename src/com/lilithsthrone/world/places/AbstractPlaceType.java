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
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.EntranceType;
import com.lilithsthrone.world.TeleportPermissions;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.population.Population;

/**
 * @since 0.3.1
 * @version 0.4
 * @author Innoxia
 */
public class AbstractPlaceType {

	protected WorldRegion worldRegion;
	
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
	
	protected Aquatic aquatic;
	protected Darkness darkness;
	protected TeleportPermissions teleportPermissions;
	
	protected List<Weather> weatherImmunities;
	protected static List<Weather> allWeatherImmunities = new ArrayList<>(Arrays.asList(Weather.values()));
	
	protected String virginityLossDescription;

	protected static Map<String, String> SVGOverrides = new HashMap<>(); 
	
	protected static int colourReplacementId = 0;
	
	public AbstractPlaceType(WorldRegion worldRegion,
			String name,
			String tooltipDescription,
			String SVGPath,
			Colour colour,
			DialogueNode dialogue,
			Darkness darkness,
			Encounter encounterType,
			String virginityLossDescription) {
		this.worldRegion = worldRegion;
		
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
		
		this.aquatic = Aquatic.LAND;
		this.darkness = darkness;
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
	
	public AbstractPlaceType initAquatic(Aquatic aquatic) {
		this.aquatic = aquatic;
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
	
	public WorldRegion getWorldRegion() {
		return worldRegion;
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
	
	protected DialogueNode getBaseDialogue(Cell cell) {
		return dialogue;
	}

	public DialogueNode getDialogue(boolean withRandomEncounter) {
		return getDialogue(null, withRandomEncounter, false);
	}
	
	public DialogueNode getDialogue(Cell cell, boolean withRandomEncounter) {
		return getDialogue(cell, withRandomEncounter, false);
	}
	
	public DialogueNode getDialogue(Cell cell, boolean withRandomEncounter, boolean forceEncounter) {
		if(getEncounterType()!=null && withRandomEncounter) {
			DialogueNode dn = getEncounterType().getRandomEncounter(forceEncounter);
			if (dn != null) {
				return dn;
			}
		}
		return getBaseDialogue(cell);
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
		return getAquatic().isLand();
	}

	public boolean isWater() {
		return getAquatic().isWater();
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

	public Aquatic getAquatic() {
		return aquatic;
	}

	public Darkness getDarkness() {
		return darkness;
	}
	
	public static String getSVGOverride(String pathName, Colour colour) {
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
	
	public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
		for(AbstractPlaceUpgrade upgrade : upgrades) {
			String s = upgrade.getSVGOverride();
			if(s!=null) {
				return s;
			}
		}
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
	
	public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
		return new ArrayList<>();
	}
	
	public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
		return new ArrayList<>();
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
