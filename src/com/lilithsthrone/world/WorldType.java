package com.lilithsthrone.world;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.Dominion;
import com.lilithsthrone.world.places.EnforcerHQ;
import com.lilithsthrone.world.places.GenericPlaces;
import com.lilithsthrone.world.places.HarpyNests;
import com.lilithsthrone.world.places.Jungle;
import com.lilithsthrone.world.places.LilayasHome;
import com.lilithsthrone.world.places.PlaceInterface;
import com.lilithsthrone.world.places.ShoppingArcade;
import com.lilithsthrone.world.places.SlaverAlley;
import com.lilithsthrone.world.places.Submission;

/**
 * @since 0.1.0
 * @version 0.1.85
 * @author Innoxia
 */
public enum WorldType {
	
	// Dominion:
	
	DOMINION(6,
			"Dominion",
			Colour.BASE_PURPLE,
			1,
			Dominion.CITY_STREET,
			Dominion.CITY_BACK_ALLEYS,
			Util.newArrayListOfValues(
					new ListValue<PlaceInterface>(Dominion.CITY_LILITHS_TOWER),
					new ListValue<PlaceInterface>(Dominion.CITY_ENFORCER_HQ),
					new ListValue<PlaceInterface>(Dominion.CITY_DEMON_HOME),
					new ListValue<PlaceInterface>(Dominion.CITY_HARPY_NESTS),
					new ListValue<PlaceInterface>(Dominion.CITY_SHOPPING_ARCADE),
					new ListValue<PlaceInterface>(Dominion.CITY_NIGHTLIFE_DISTRICT),
					new ListValue<PlaceInterface>(Dominion.CITY_HALL),
					new ListValue<PlaceInterface>(Dominion.CITY_AUNTS_HOME),
					new ListValue<PlaceInterface>(Dominion.CITY_EXIT_TO_DESERT),
					new ListValue<PlaceInterface>(Dominion.CITY_EXIT_TO_FIELDS),
					new ListValue<PlaceInterface>(Dominion.CITY_EXIT_TO_JUNGLE),
					new ListValue<PlaceInterface>(Dominion.CITY_EXIT_TO_SEA),
					new ListValue<PlaceInterface>(Dominion.CITY_EXIT_TO_SEWERS)),

			Util.newArrayListOfValues(
					new ListValue<PlaceInterface>(Dominion.CITY_SLAVER_ALLEY),
					new ListValue<PlaceInterface>(Dominion.CITY_DARK_ALLEYS),
					new ListValue<PlaceInterface>(Dominion.CITY_DARK_ALLEYS))),

	// Empty:
//	EMPTY("Empty",
//			Colour.BASE_YELLOW,
//			1,
//			"/com/lilithsthrone/res/map/empty.png",
//			Util.newHashMapOfValues(
//					new Value<>(new Color(0xff0000), GenericPlaces.EMPTY_TILE),
//					new Value<>(new Color(0x0080ff), GenericPlaces.MUSEUM)),
//			
//			Dominion.CITY_EXIT_TO_SEA,
//			WorldType.DOMINION),
	
	LILAYAS_HOUSE_GROUND_FLOOR("Lilaya's Home",
			Colour.BASE_BLUE_LIGHT,
			1,
			"/com/lilithsthrone/res/map/dominion/lilayasHome/lilayas_home_ground_floor.png",
			Util.newHashMapOfValues(
					
					new Value<>(new Color(0xFFFFFF), GenericPlaces.IMPASSABLE),
					new Value<>(new Color(0x808080), LilayasHome.LILAYA_HOME_CORRIDOR),
					new Value<>(new Color(0xff0000), LilayasHome.LILAYA_HOME_ENTRANCE_HALL),
					new Value<>(new Color(0x008000), LilayasHome.LILAYA_HOME_GARDEN),
					new Value<>(new Color(0xff8000), LilayasHome.LILAYA_HOME_LAB),
					new Value<>(new Color(0xffff00), LilayasHome.LILAYA_HOME_BIRTHING_ROOM),
					new Value<>(new Color(0xff80ff), LilayasHome.LILAYA_HOME_KITCHEN),
					new Value<>(new Color(0x00ffff), LilayasHome.LILAYA_HOME_LIBRARY),
//					new Value<>(new Color(0x8000ff), LilayasHome.LILAYA_HOME_ROOM),
					new Value<>(new Color(0x8000ff), LilayasHome.LILAYA_HOME_FOUNTAIN),
					new Value<>(new Color(0xff0080), LilayasHome.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR),
					new Value<>(new Color(0xff00ff), LilayasHome.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR),
					new Value<>(new Color(0x00ff00), LilayasHome.LILAYA_HOME_STAIR_UP)),
			
			Dominion.CITY_AUNTS_HOME,
			WorldType.DOMINION){
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
					new Value<>(new Color(0xFFFFFF), GenericPlaces.IMPASSABLE),
					new Value<>(new Color(0x808080), LilayasHome.LILAYA_HOME_CORRIDOR),
					new Value<>(new Color(0xff00ff), LilayasHome.LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR),
					new Value<>(new Color(0xff0080), LilayasHome.LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR),
//					new Value<>(new Color(0x8000ff), LilayasHome.LILAYA_HOME_ROOM),
					new Value<>(new Color(0xff80ff), LilayasHome.LILAYA_HOME_ROOM_LILAYA),
					new Value<>(new Color(0x0080ff), LilayasHome.LILAYA_HOME_ROOM_ROSE),
					new Value<>(new Color(0x00ffff), LilayasHome.LILAYA_HOME_ROOM_PLAYER),
					new Value<>(new Color(0xff0000), LilayasHome.LILAYA_HOME_STAIR_DOWN)),
			
			LilayasHome.LILAYA_HOME_STAIR_UP,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},

	HARPY_NEST(4,
			"Harpy Nests",
			Colour.BASE_CRIMSON,
			5,
			HarpyNests.WALKWAYS,
			GenericPlaces.IMPASSABLE,
			Util.newArrayListOfValues(
					new ListValue<PlaceInterface>(HarpyNests.ENTRANCE_ENFORCER_POST),
					new ListValue<PlaceInterface>(HarpyNests.HARPY_NEST_RED),
					new ListValue<PlaceInterface>(HarpyNests.HARPY_NEST_PINK),
					new ListValue<PlaceInterface>(HarpyNests.HARPY_NEST_YELLOW),
					new ListValue<PlaceInterface>(HarpyNests.ALEXAS_NEST)),
			null),
	
	SLAVER_ALLEY("Slaver Alley",
			Colour.BASE_RED,
			1,
			"/com/lilithsthrone/res/map/dominion/slaverAlley/slaverAlley.png",
			Util.newHashMapOfValues(
					
					new Value<>(new Color(0xFFFFFF), GenericPlaces.IMPASSABLE),
					new Value<>(new Color(0x808080), SlaverAlley.ALLEY),
					new Value<>(new Color(0xff0000), SlaverAlley.ALLEY_ENTRANCE),
					new Value<>(new Color(0xff00ff), SlaverAlley.MARKET_STALL),
					new Value<>(new Color(0x0000ff), SlaverAlley.SLAVERY_ADMINISTRATION),
					new Value<>(new Color(0xff0080), SlaverAlley.SCARLETTS_SHOP),
					new Value<>(new Color(0xffff00), SlaverAlley.AUCTIONING_BLOCK),
					new Value<>(new Color(0x00ff00), SlaverAlley.PUBLIC_STOCKS),
					new Value<>(new Color(0x0080ff), SlaverAlley.BROTHEL)
					),
			
			Dominion.CITY_SLAVER_ALLEY,
			WorldType.DOMINION),
	
	SHOPPING_ARCADE("Shopping Arcade",
			Colour.BASE_YELLOW,
			1,
			"/com/lilithsthrone/res/map/dominion/shoppingArcade/shoppingArcade.png",
			Util.newHashMapOfValues(
					
					new Value<>(new Color(0xFFFFFF), GenericPlaces.IMPASSABLE),
					new Value<>(new Color(0x808080), ShoppingArcade.ARCADE),
					new Value<>(new Color(0xff0000), ShoppingArcade.ARCADE_ENTRANCE),
					new Value<>(new Color(0x00ffff), ShoppingArcade.RALPHS_SHOP_ITEMS),
					new Value<>(new Color(0xffff00), ShoppingArcade.NYANS_SHOP_CLOTHING),
					new Value<>(new Color(0x0080ff), ShoppingArcade.VICKYS_SHOP_WEAPONS),
					new Value<>(new Color(0x8000ff), ShoppingArcade.PIXS_GYM),
					new Value<>(new Color(0xff8000), ShoppingArcade.KATES_SHOP_BEAUTY),
					new Value<>(new Color(0xff00ff), ShoppingArcade.GENERIC_SHOP)),
			
			Dominion.CITY_SHOPPING_ARCADE,
			WorldType.DOMINION){
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
					
					new Value<>(new Color(0xFFFFFF), GenericPlaces.IMPASSABLE),
					new Value<>(new Color(0x808080), EnforcerHQ.CORRIDOR),
					new Value<>(new Color(0xff0000), EnforcerHQ.ENTRANCE),
					new Value<>(new Color(0xffff00), EnforcerHQ.GUARDED_DOOR),
					new Value<>(new Color(0x8000ff), EnforcerHQ.WAITING_AREA),
					new Value<>(new Color(0x0080ff), EnforcerHQ.RECEPTION_DESK),
					new Value<>(new Color(0xff8000), EnforcerHQ.BRAXS_OFFICE)),
			
			Dominion.CITY_ENFORCER_HQ,
			WorldType.DOMINION),
	
	// Other:
	
	SEWERS(6,
			"Submission",
			Colour.BASE_GREEN,
			5,
			Submission.SEWER_WALKWAYS,
			GenericPlaces.IMPASSABLE,
			Util.newArrayListOfValues(
					new ListValue<PlaceInterface>(Submission.SEWER_RAT_TUNNELS),
					new ListValue<PlaceInterface>(Submission.SEWER_IMP_PALACE),
					new ListValue<PlaceInterface>(Submission.SEWER_GAMBLING_DEN),
					new ListValue<PlaceInterface>(Submission.SEWER_ENTRANCE)),
			null),

	JUNGLE(6,
			"jungle",
			Colour.BASE_GREEN_LIME,
			240,
			Jungle.JUNGLE_PATH,
			Jungle.JUNGLE_DENSE_JUNGLE,
			Util.newArrayListOfValues(
					new ListValue<PlaceInterface>(Jungle.JUNGLE_BROTHEL),
					new ListValue<PlaceInterface>(Jungle.JUNGLE_CLUB),
					new ListValue<PlaceInterface>(Jungle.JUNGLE_ENTRANCE)),

			Util.newArrayListOfValues(new ListValue<PlaceInterface>(Jungle.JUNGLE_TENTACLE_QUEENS_LAIR)));

	
	private final String name, fileLocation;
	private Colour colour;
	private int worldSize, timeToTransition;
	
	private int tileSetRowNumber, moveCost;
	private PlaceInterface standardPlace, cutOffZone;
	private List<PlaceInterface> places, dangerousPlaces;
	
	private boolean usesFile;
	private Map<Color, PlaceInterface> placesMap;
	private PlaceInterface previousWorldExitType;
	private WorldType previousWorldType;
	

	WorldType(int worldSize, String name, Colour colour, int timeToTransition, PlaceInterface standardPlace, PlaceInterface cutOffZone, List<PlaceInterface> places, List<PlaceInterface> dangerousPlaces) {
		
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
		previousWorldExitType = null;
		previousWorldType = null;
		
	}
	
	WorldType(String name, Colour colour, int timeToTransition, String fileLocation, Map<Color, PlaceInterface> placesMap, PlaceInterface previousWorldExitType, WorldType previousWorldType) {

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
		
		this.previousWorldExitType=previousWorldExitType;
		this.previousWorldType=previousWorldType;
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

	public PlaceInterface getStandardPlace() {
		return standardPlace;
	}

	public PlaceInterface getCutOffZone() {
		return cutOffZone;
	}

	public List<PlaceInterface> getPlaces() {
		return places;
	}

	public List<PlaceInterface> getDangerousPlaces() {
		return dangerousPlaces;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public boolean isUsesFile() {
		return usesFile;
	}

	public Map<Color, PlaceInterface> getPlacesMap() {
		return placesMap;
	}

	public PlaceInterface getPreviousWorldExitType() {
		return previousWorldExitType;
	}

	public WorldType getPreviousWorldType() {
		return previousWorldType;
	}

	public int getWorldSize() {
		return worldSize;
	}

}
