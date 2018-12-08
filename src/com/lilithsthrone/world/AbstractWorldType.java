package com.lilithsthrone.world;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.12
 * @version 0.2.12
 * @author Innoxia
 */
public abstract class AbstractWorldType {

	private String name;
	private String fileLocation;
	private Colour colour;
	private int worldSize;
	private int timeToTransition;
	
	private int tileSetRowNumber;
	private int moveCost;
	private PlaceType standardPlace;
	private PlaceType cutOffZone;
	private List<PlaceType> places;
	private List<PlaceType> dangerousPlaces;
	
	private boolean usesFile;
	private Map<Color, PlaceType> placesMap;
	
	@Deprecated
	AbstractWorldType(int worldSize,
			String name,
			Colour colour,
			int timeToTransition,
			PlaceType standardPlace,
			PlaceType cutOffZone,
			List<PlaceType> places,
			List<PlaceType> dangerousPlaces) {
		this.worldSize=worldSize;
		
		this.name = name;
		this.colour = colour;
		this.timeToTransition=timeToTransition;
		this.moveCost = 5;

		this.standardPlace = standardPlace;
		this.cutOffZone = cutOffZone;

		this.places = places;
		this.dangerousPlaces = dangerousPlaces;
		
		fileLocation = null;
		usesFile = false;
	}
	
	protected AbstractWorldType(String name,
			Colour colour,
			int timeToTransition,
			String fileLocation,
			Map<Color, PlaceType> placesMap) {
		this.name = name;
		this.colour = colour;
		this.timeToTransition=timeToTransition;
		moveCost = 5;

		standardPlace = null;
		cutOffZone = null;

		places = null;
		dangerousPlaces = null;
		
		this.fileLocation = fileLocation;
		usesFile = true;
		this.placesMap=placesMap;
	}
	
	@Override
	public boolean equals (Object o) { // I know it doesn't include everything, but this should be enough to check for equality.
		if(super.equals(o)){
			if(o instanceof AbstractWorldType){
				if(((AbstractWorldType)o).getName().equals(getName())
						&& ((AbstractWorldType)o).getName().equals(getName())
						&& ((AbstractWorldType)o).getColour() == getColour()
						&& ((AbstractWorldType)o).getColour() == getColour()
						&& ((AbstractWorldType)o).getPlacesMap().equals(getPlacesMap())
						){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() { // I know it doesn't include everything, but this should be enough to check for equality.
		int result = super.hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + getColour().hashCode();
		if(getFileLocation()!=null) {
			result = 31 * result + getFileLocation().hashCode();
		}
		result = 31 * result + getPlacesMap().hashCode();
		return result;
	}
	
	public int getTileSetRowNumber() {
		return tileSetRowNumber;
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}

	public int getTimeToTransition() {
		return timeToTransition;
	}

	public int getMoveCost() {
		return moveCost;
	}
	
	public boolean isRevealedOnStart() {
		return false;
	}

	public PlaceType getStandardPlace() {
		return standardPlace;
	}

	public PlaceType getCutOffZone() {
		return cutOffZone;
	}

	public List<PlaceType> getPlaces() {
		return places;
	}

	public List<PlaceType> getDangerousPlaces() {
		return dangerousPlaces;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public boolean isUsesFile() {
		return usesFile;
	}

	public Map<Color, PlaceType> getPlacesMap() {
		return placesMap;
	}

	public int getWorldSize() {
		return worldSize;
	}
	
	public boolean isCompanionSexBlocked(GameCharacter companion) {
		return getCompanionSexBlockedReason(companion)!=null || getCompanionSexBlockedReason(companion).isEmpty();
	}
	
	public abstract String getCompanionSexBlockedReason(GameCharacter companion);

}
