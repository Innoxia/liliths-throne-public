package com.lilithsthrone.world;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.1.89
 * @author Innoxia
 * @see Comment by LightC
 * This is a list of the world type as an enum and each contain information like
 *	-World size
 *	-world name
 *	-map color
 *	-time to travel between cell'
 *	-Standard cell
 *	-"CutOffZone" (danger zone)
 *	-list of unique location
 *	-list of unique dangerous place
 * 
 */
public enum WorldType {
	
	// Dominion:
	
	DOMINION(6,
			"Dominion",
			Colour.BASE_PURPLE,
			1,
			PlaceType.DOMINION_STREET,
			PlaceType.DOMINION_BACK_ALLEYS,
			Util.newArrayListOfValues(
					new ListValue<PlaceType>(PlaceType.DOMINION_LILITHS_TOWER),
					new ListValue<PlaceType>(PlaceType.DOMINION_ENFORCER_HQ),
					new ListValue<PlaceType>(PlaceType.DOMINION_DEMON_HOME),
					new ListValue<PlaceType>(PlaceType.DOMINION_HARPY_NESTS),
					new ListValue<PlaceType>(PlaceType.DOMINION_SHOPPING_ARCADE),
					new ListValue<PlaceType>(PlaceType.DOMINION_NIGHTLIFE_DISTRICT),
					new ListValue<PlaceType>(PlaceType.DOMINION_CITY_HALL),
					new ListValue<PlaceType>(PlaceType.DOMINION_AUNTS_HOME),
					new ListValue<PlaceType>(PlaceType.DOMINION_EXIT_TO_DESERT),
					new ListValue<PlaceType>(PlaceType.DOMINION_EXIT_TO_FIELDS),
					new ListValue<PlaceType>(PlaceType.DOMINION_EXIT_TO_JUNGLE),
					new ListValue<PlaceType>(PlaceType.DOMINION_EXIT_TO_SEA),
					new ListValue<PlaceType>(PlaceType.DOMINION_EXIT_TO_SEWERS)),

			Util.newArrayListOfValues(
					new ListValue<PlaceType>(PlaceType.DOMINION_SLAVER_ALLEY),
					new ListValue<PlaceType>(PlaceType.DOMINION_DARK_ALLEYS),
					new ListValue<PlaceType>(PlaceType.DOMINION_DARK_ALLEYS))),

	// Empty:
	EMPTY("City",
			Colour.BASE_BROWN,
			1,
			"/com/lilithsthrone/res/map/empty.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0xff0000), PlaceType.GENERIC_EMPTY_TILE),
					new Value<>(new Color(0x0080ff), PlaceType.GENERIC_MUSEUM))),
	
	LILAYAS_HOUSE_GROUND_FLOOR("Lilaya's Home",
			Colour.BASE_BLUE_LIGHT,
			1,
			"/com/lilithsthrone/res/map/dominion/lilayasHome/lilayas_home_ground_floor.png",
			Util.newHashMapOfValues(
					
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.LILAYA_HOME_CORRIDOR),
					new Value<>(new Color(0xff0000), PlaceType.LILAYA_HOME_ENTRANCE_HALL),
					new Value<>(new Color(0x008000), PlaceType.LILAYA_HOME_GARDEN),
					new Value<>(new Color(0xff8000), PlaceType.LILAYA_HOME_LAB),
					new Value<>(new Color(0xffff00), PlaceType.LILAYA_HOME_BIRTHING_ROOM),
					new Value<>(new Color(0xff80ff), PlaceType.LILAYA_HOME_KITCHEN),
					new Value<>(new Color(0x00ffff), PlaceType.LILAYA_HOME_LIBRARY),
//					new Value<>(new Color(0x8000ff), LilayasHome.LILAYA_HOME_ROOM),
					new Value<>(new Color(0x8000ff), PlaceType.LILAYA_HOME_FOUNTAIN),
					new Value<>(new Color(0xff0080), PlaceType.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR),
					new Value<>(new Color(0xff00ff), PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR),
					new Value<>(new Color(0x00ff00), PlaceType.LILAYA_HOME_STAIR_UP))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},
	
	LILAYAS_HOUSE_FIRST_FLOOR("Lilaya's Home",
			Colour.BASE_BLUE_LIGHT,
			1,
			"/com/lilithsthrone/res/map/dominion/lilayasHome/lilayas_home_first_floor.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.LILAYA_HOME_CORRIDOR),
					new Value<>(new Color(0xff00ff), PlaceType.LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR),
					new Value<>(new Color(0xff0080), PlaceType.LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR),
//					new Value<>(new Color(0x8000ff), LilayasHome.LILAYA_HOME_ROOM),
					new Value<>(new Color(0xff80ff), PlaceType.LILAYA_HOME_ROOM_LILAYA),
					new Value<>(new Color(0x0080ff), PlaceType.LILAYA_HOME_ROOM_ROSE),
					new Value<>(new Color(0x00ffff), PlaceType.LILAYA_HOME_ROOM_PLAYER),
					new Value<>(new Color(0xff0000), PlaceType.LILAYA_HOME_STAIR_DOWN))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},
	
	ZARANIX_HOUSE_FIRST_FLOOR("Zaranix's Home",
			Colour.BASE_CRIMSON,
			1,
			"/com/lilithsthrone/res/map/dominion/zaranixHome/first_floor.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.ZARANIX_FF_CORRIDOR),
					new Value<>(new Color(0x00ff00), PlaceType.ZARANIX_FF_STAIRS),
					new Value<>(new Color(0xff80ff), PlaceType.ZARANIX_FF_OFFICE),
					new Value<>(new Color(0xff00ff), PlaceType.ZARANIX_FF_ROOM),
					new Value<>(new Color(0x8000ff), PlaceType.ZARANIX_FF_MAID))),
	
	ZARANIX_HOUSE_GROUND_FLOOR("Zaranix's Home",
			Colour.BASE_CRIMSON,
			1,
			"/com/lilithsthrone/res/map/dominion/zaranixHome/ground_floor.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.ZARANIX_GF_CORRIDOR),
					new Value<>(new Color(0x00ff00), PlaceType.ZARANIX_GF_STAIRS),
					new Value<>(new Color(0xff0000), PlaceType.ZARANIX_GF_ENTRANCE),
					new Value<>(new Color(0xffff00), PlaceType.ZARANIX_GF_LOUNGE),
					new Value<>(new Color(0xff00ff), PlaceType.ZARANIX_GF_ROOM),
					new Value<>(new Color(0x8000ff), PlaceType.ZARANIX_GF_MAID),
					new Value<>(new Color(0x00ffff), PlaceType.ZARANIX_GF_GARDEN_ROOM),
					new Value<>(new Color(0x008000), PlaceType.ZARANIX_GF_GARDEN),
					new Value<>(new Color(0xff8000), PlaceType.ZARANIX_GF_GARDEN_ENTRY))),

	HARPY_NEST(4,
			"Harpy Nests",
			Colour.BASE_CRIMSON,
			5,
			PlaceType.HARPY_NESTS_WALKWAYS,
			PlaceType.GENERIC_IMPASSABLE,
			Util.newArrayListOfValues(
					new ListValue<PlaceType>(PlaceType.HARPY_NESTS_ENTRANCE_ENFORCER_POST),
					new ListValue<PlaceType>(PlaceType.HARPY_NESTS_HARPY_NEST_RED),
					new ListValue<PlaceType>(PlaceType.HARPY_NESTS_HARPY_NEST_PINK),
					new ListValue<PlaceType>(PlaceType.HARPY_NESTS_HARPY_NEST_YELLOW),
					new ListValue<PlaceType>(PlaceType.HARPY_NESTS_ALEXAS_NEST)),
			null),
	
	SLAVER_ALLEY("Slaver Alley",
			Colour.BASE_RED,
			1,
			"/com/lilithsthrone/res/map/dominion/slaverAlley/slaverAlley.png",
			Util.newHashMapOfValues(
					
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.SLAVER_ALLEY_PATH),
					new Value<>(new Color(0xff0000), PlaceType.SLAVER_ALLEY_ENTRANCE),
					new Value<>(new Color(0xff00ff), PlaceType.SLAVER_ALLEY_MARKET_STALL),
					new Value<>(new Color(0x0000ff), PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION),
					new Value<>(new Color(0xff0080), PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP),
					new Value<>(new Color(0xffff00), PlaceType.SLAVER_ALLEY_AUCTIONING_BLOCK),
					new Value<>(new Color(0x00ff00), PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS),
					new Value<>(new Color(0x0080ff), PlaceType.SLAVER_ALLEY_BROTHEL))),
	
	SHOPPING_ARCADE("Shopping Arcade",
			Colour.BASE_YELLOW,
			1,
			"/com/lilithsthrone/res/map/dominion/shoppingArcade/shoppingArcade.png",
			Util.newHashMapOfValues(
					
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.SHOPPING_ARCADE_PATH),
					new Value<>(new Color(0xff0000), PlaceType.SHOPPING_ARCADE_ENTRANCE),
					new Value<>(new Color(0x00ffff), PlaceType.SHOPPING_ARCADE_RALPHS_SHOP),
					new Value<>(new Color(0xffff00), PlaceType.SHOPPING_ARCADE_NYANS_SHOP),
					new Value<>(new Color(0x0080ff), PlaceType.SHOPPING_ARCADE_VICKYS_SHOP),
					new Value<>(new Color(0x8000ff), PlaceType.SHOPPING_ARCADE_PIXS_GYM),
					new Value<>(new Color(0xff8000), PlaceType.SHOPPING_ARCADE_KATES_SHOP),
					new Value<>(new Color(0xff00ff), PlaceType.SHOPPING_ARCADE_GENERIC_SHOP))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},
	
	ENFORCER_HQ("Enforcer HQ",
			Colour.BASE_BLUE,
			1,
			"/com/lilithsthrone/res/map/dominion/enforcerHQ/enforcerHQ.png",
			Util.newHashMapOfValues(
					
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.ENFORCER_HQ_CORRIDOR),
					new Value<>(new Color(0xff0000), PlaceType.ENFORCER_HQ_ENTRANCE),
					new Value<>(new Color(0xffff00), PlaceType.ENFORCER_HQ_GUARDED_DOOR),
					new Value<>(new Color(0x8000ff), PlaceType.ENFORCER_HQ_WAITING_AREA),
					new Value<>(new Color(0x0080ff), PlaceType.ENFORCER_HQ_RECEPTION_DESK),
					new Value<>(new Color(0xff8000), PlaceType.ENFORCER_HQ_BRAXS_OFFICE))),
	
	// Other:
	
	SEWERS(6,
			"Submission",
			Colour.BASE_GREEN,
			5,
			PlaceType.SUBMISSION_WALKWAYS,
			PlaceType.GENERIC_IMPASSABLE,
			Util.newArrayListOfValues(
					new ListValue<PlaceType>(PlaceType.SUBMISSION_RAT_TUNNELS),
					new ListValue<PlaceType>(PlaceType.SUBMISSION_IMP_PALACE),
					new ListValue<PlaceType>(PlaceType.SUBMISSION_GAMBLING_DEN),
					new ListValue<PlaceType>(PlaceType.SUBMISSION_ENTRANCE)),
			null),

	JUNGLE(6,
			"jungle",
			Colour.BASE_GREEN_LIME,
			240,
			PlaceType.JUNGLE_PATH,
			PlaceType.JUNGLE_DENSE_JUNGLE,
			Util.newArrayListOfValues(
					new ListValue<PlaceType>(PlaceType.JUNGLE_BROTHEL),
					new ListValue<PlaceType>(PlaceType.JUNGLE_CLUB),
					new ListValue<PlaceType>(PlaceType.JUNGLE_ENTRANCE)),

			Util.newArrayListOfValues(new ListValue<PlaceType>(PlaceType.JUNGLE_TENTACLE_QUEENS_LAIR)));

	
	private final String name, fileLocation;
	private Colour colour;
	private int worldSize, timeToTransition;
	
	private int tileSetRowNumber, moveCost;
	private PlaceType standardPlace, cutOffZone;
	private List<PlaceType> places, dangerousPlaces;
	
	private boolean usesFile;
	private Map<Color, PlaceType> placesMap;
	

	WorldType(int worldSize, String name, Colour colour, int timeToTransition, PlaceType standardPlace, PlaceType cutOffZone, List<PlaceType> places, List<PlaceType> dangerousPlaces) {
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
	
	WorldType(String name, Colour colour, int timeToTransition, String fileLocation, Map<Color, PlaceType> placesMap) {
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

}
