package com.lilithsthrone.world;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.world.places.AbstractPlaceType;

/**
 * @since 0.2.12
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractWorldType {

	private WorldRegion worldRegion;
	
	private final String name;
	private final String fileLocation;
	private Colour colour;
	private int timeToTransition;
	
	private boolean loiteringEnabled;
	private boolean flightEnabled;
	
	private int tileSetRowNumber;
	private int moveCost;
	private AbstractPlaceType standardPlace;
	private AbstractPlaceType cutOffZone;
	private List<AbstractPlaceType> places;
	private List<AbstractPlaceType> dangerousPlaces;
	
	private TeleportPermissions teleportPermissions;
	
	private boolean usesFile;
	private AbstractPlaceType globalMapLocation;
	private AbstractPlaceType entryFromGlobalMapLocation;
	private Map<Color, AbstractPlaceType> placesMap;
	
	AbstractWorldType(WorldRegion worldRegion,
			String name,
			Colour colour,
			int timeToTransition,
			boolean loiteringEnabled,
			boolean flightEnabled,
			TeleportPermissions teleportPermissions,
			String fileLocation,
			AbstractPlaceType globalMapLocation,
			AbstractPlaceType entryFromGlobalMapLocation,
			Map<Color, AbstractPlaceType> placesMap) {
		this.worldRegion = worldRegion;
		
		this.name = name;
		this.colour = colour;
		this.timeToTransition=timeToTransition;
		moveCost = 5;

		standardPlace = null;
		cutOffZone = null;

		this.globalMapLocation = globalMapLocation;
		this.entryFromGlobalMapLocation = entryFromGlobalMapLocation;
		
		places = null;
		dangerousPlaces = null;
		
		this.loiteringEnabled = loiteringEnabled;
		this.flightEnabled = flightEnabled;
		
		this.teleportPermissions = teleportPermissions;
		
		this.fileLocation = fileLocation;
		this.usesFile = true;
		this.placesMap = placesMap;
	}
	
	@Override
	public boolean equals(Object o) { // Just placesMap and fileLocation should be enough to check for equality.
		if(super.equals(o)){
			if(o instanceof AbstractWorldType){
				if(((AbstractWorldType)o).getPlacesMap().equals(getPlacesMap())
						&& ((AbstractWorldType)o).getFileLocation().equals(getFileLocation())){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() { // Just placesMap and fileLocation should be enough to check for equality.
		int result = 17;
		result = 31 * result + getFileLocation().hashCode();
		result = 31 * result + getPlacesMap().hashCode();
		return result;
	}
	
	@Override
	public String toString() {
//		throw new IllegalAccessError();
		System.err.println("Warning: AbstractWorldType's toString() method is being called!");
		return super.toString();
	}

	public int getTileSetRowNumber() {
		return tileSetRowNumber;
	}

	public WorldRegion getWorldRegion() {
		return worldRegion;
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
	
	public boolean isLoiteringEnabled() {
		return loiteringEnabled;
	}
	
	/**
	 * Reveals all tiles as though the player knows about them, but has not travelled to them. Behaviour may be overridden by isRevealedOnStart().
	 */
	public boolean isDiscoveredOnStart() {
		return false;
	}
	
	/**
	 * Reveals all tiles as though the player has already travelled to them.
	 */
	public boolean isRevealedOnStart() {
		return false;
	}

	public AbstractPlaceType getStandardPlace() {
		return standardPlace;
	}

	public AbstractPlaceType getCutOffZone() {
		return cutOffZone;
	}

	public List<AbstractPlaceType> getPlaces() {
		return places;
	}

	public List<AbstractPlaceType> getDangerousPlaces() {
		return dangerousPlaces;
	}

	public AbstractPlaceType getGlobalMapLocation() {
		return globalMapLocation;
	}

	public AbstractPlaceType getEntryFromGlobalMapLocation() {
		return entryFromGlobalMapLocation;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public boolean isUsesFile() {
		return usesFile;
	}

	public Map<Color, AbstractPlaceType> getPlacesMap() {
		return placesMap;
	}

	public TeleportPermissions getTeleportPermissions() {
		return teleportPermissions;
	}
	
	public boolean isFlightEnabled() {
		return flightEnabled;
	}
	
	public boolean isSexBlocked(GameCharacter character) {
		return getSexBlockedReason(character)!=null && !getSexBlockedReason(character).isEmpty();
	}
	
	public String getSexBlockedReason(GameCharacter character) {
		return "";
	}

	/**
	 * @return true if over-desk and on chair sex positions are available in this location.
	 */
	public boolean isFurniturePresent() {
		return false;
	}

}
