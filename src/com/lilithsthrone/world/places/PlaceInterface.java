package com.lilithsthrone.world.places;

import java.util.ArrayList;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Bearing;
import com.lilithsthrone.world.EntranceType;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.75
 * @version 0.1.85
 * @author Innoxia
 */
public interface PlaceInterface {
	
	public String getName();
	
	public BaseColour getColour();

	public DialogueNodeOld getDialogue(boolean withRandomEncounter);

	public boolean isPopulated();

	public boolean isDangerous();
	
	public boolean isStormImmune();

	public boolean isItemsDisappear();

	public String getSVGString();
	
	public default ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
		return new ArrayList<>();
	}
	
	public default ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
		return new ArrayList<>();
	}
	
	// For determining where this place should be placed:
	
	/**
	 * The returned bearing defines where in this randomly generated worth this placeType should be situated.
	 */
	public Bearing getBearing();
	
	/**
	 * The world that leads to this place.
	 */
	public WorldType getParentWorldType();
	
	/**
	 * The place that led to this place.
	 */
	public PlaceInterface getParentPlaceInterface();
	
	/**
	 * How this place should be aligned in relation to its parent place.
	 */
	public EntranceType getParentAlignment();
	
	
	// For porting to another world:
	
	/**
	 * The world type that this place links to.
	 */
	public WorldType getLinkedWorldType();
	
	/**
	 * The place type that this place links to.
	 */
	public PlaceInterface getLinkedPlaceInterface();
}
