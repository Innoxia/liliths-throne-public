package com.lilithsthrone.world;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.6
 * @author Innoxia
 */
public enum WorldType {
	
	// Dominion:
	
//	DOMINION(6,
//			"Dominion",
//			Colour.BASE_PURPLE,
//			1,
//			PlaceType.DOMINION_STREET,
//			PlaceType.DOMINION_BACK_ALLEYS,
//			Util.newArrayListOfValues(
//					PlaceType.,
//					PlaceType.,
//					PlaceType.,
//					PlaceType.,
//					PlaceType.,
//					PlaceType.,
//					PlaceType.,
//					PlaceType.,
//
//			Util.newArrayListOfValues(
//					PlaceType.,
//					PlaceType.DOMINION_DARK_ALLEYS,
//					PlaceType.DOMINION_DARK_ALLEYS)),

	DOMINION("Dominion",
			Colour.BASE_PURPLE,
			1,
			"/com/lilithsthrone/res/map/dominion/dominion.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					
					new Value<>(new Color(0x808080), PlaceType.DOMINION_STREET),
					new Value<>(new Color(0x404040), PlaceType.DOMINION_BOULEVARD),
					new Value<>(new Color(0x808000), PlaceType.DOMINION_EXIT_TO_SUBMISSION),
					
					new Value<>(new Color(0xc10000), PlaceType.DOMINION_BACK_ALLEYS),
					new Value<>(new Color(0x5b0000), PlaceType.DOMINION_DARK_ALLEYS),
					new Value<>(new Color(0x40b4ff), PlaceType.DOMINION_ALLEYS_CANAL_CROSSING),
					
					new Value<>(new Color(0x0080ff), PlaceType.DOMINION_CANAL),
					new Value<>(new Color(0x004080), PlaceType.DOMINION_CANAL_END),
					
					new Value<>(new Color(0x000000), PlaceType.DOMINION_DEMON_HOME_GATE),
					new Value<>(new Color(0xff80ff), PlaceType.DOMINION_DEMON_HOME),
					new Value<>(new Color(0xff9100), PlaceType.DOMINION_DEMON_HOME_ARTHUR),
					new Value<>(new Color(0x8000ff), PlaceType.DOMINION_CITY_HALL),
					new Value<>(new Color(0xff00ff), PlaceType.DOMINION_LILITHS_TOWER),

					new Value<>(new Color(0x8080ff), PlaceType.DOMINION_EXIT_TO_SEA),
					new Value<>(new Color(0xff4a00), PlaceType.DOMINION_EXIT_TO_FIELDS),
					new Value<>(new Color(0x008040), PlaceType.DOMINION_EXIT_TO_JUNGLE),
					new Value<>(new Color(0xffff80), PlaceType.DOMINION_EXIT_TO_DESERT),
					
					new Value<>(new Color(0x008080), PlaceType.DOMINION_STREET_HARPY_NESTS),
					new Value<>(new Color(0x00ff80), PlaceType.DOMINION_HARPY_NESTS_ENTRANCE),

					new Value<>(new Color(0x004000), PlaceType.DOMINION_PLAZA),
					new Value<>(new Color(0x00ffff), PlaceType.DOMINION_AUNTS_HOME),
					new Value<>(new Color(0xffff00), PlaceType.DOMINION_SHOPPING_ARCADE),
					new Value<>(new Color(0x0000ff), PlaceType.DOMINION_ENFORCER_HQ),
					new Value<>(new Color(0x000080), PlaceType.DOMINION_NIGHTLIFE_DISTRICT),
					new Value<>(new Color(0xff0000), PlaceType.DOMINION_SLAVER_ALLEY),
					new Value<>(new Color(0x4bff00), PlaceType.DOMINION_PARK),
					new Value<>(new Color(0xff4000), PlaceType.DOMINION_RED_LIGHT_DISTRICT)
					
					)),
	

	// Empty:
	EMPTY("City",
			Colour.BASE_BROWN,
			1,
			"/com/lilithsthrone/res/map/empty.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0xff0000), PlaceType.GENERIC_EMPTY_TILE),
					new Value<>(new Color(0xffff00), PlaceType.GENERIC_HOLDING_CELL),
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

	HARPY_NEST("Harpy Nests",
			Colour.BASE_CRIMSON,
			1,
			"/com/lilithsthrone/res/map/dominion/harpyNests/harpyNests.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					
					new Value<>(new Color(0x808080), PlaceType.HARPY_NESTS_WALKWAYS),
					new Value<>(new Color(0x404040), PlaceType.HARPY_NESTS_WALKWAYS_BRIDGE),
					
					new Value<>(new Color(0x00ff80), PlaceType.HARPY_NESTS_ENTRANCE_ENFORCER_POST),
					new Value<>(new Color(0xff0000), PlaceType.HARPY_NESTS_HARPY_NEST_RED),
					new Value<>(new Color(0xff00ff), PlaceType.HARPY_NESTS_HARPY_NEST_PINK),
					new Value<>(new Color(0xffff00), PlaceType.HARPY_NESTS_HARPY_NEST_YELLOW),
					new Value<>(new Color(0xff9100), PlaceType.HARPY_NESTS_ALEXAS_NEST))),
	
	SLAVER_ALLEY("Slaver Alley",
			Colour.BASE_RED,
			1,
			"/com/lilithsthrone/res/map/dominion/slaverAlley/slaverAlley.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.SLAVER_ALLEY_PATH),
					new Value<>(new Color(0xff0000), PlaceType.SLAVER_ALLEY_ENTRANCE),

					new Value<>(new Color(0xff80ff), PlaceType.SLAVER_ALLEY_STALL_FEMALES),
					new Value<>(new Color(0x0080ff), PlaceType.SLAVER_ALLEY_STALL_MALES),
					
					new Value<>(new Color(0xff8000), PlaceType.SLAVER_ALLEY_STALL_ANAL),
					new Value<>(new Color(0xff00ff), PlaceType.SLAVER_ALLEY_STALL_VAGINAL),
					new Value<>(new Color(0xff8080), PlaceType.SLAVER_ALLEY_STALL_ORAL),
					new Value<>(new Color(0x404040), PlaceType.SLAVER_ALLEY_STATUE),
					
					new Value<>(new Color(0x21bfc5), PlaceType.SLAVER_ALLEY_MARKET_STALL_EXCLUSIVE),
					new Value<>(new Color(0x004080), PlaceType.SLAVER_ALLEY_MARKET_STALL_BULK),
					new Value<>(new Color(0x008080), PlaceType.SLAVER_ALLEY_CAFE),
					
					new Value<>(new Color(0x0000ff), PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION),
					new Value<>(new Color(0xff0080), PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP),
					
					new Value<>(new Color(0xffff00), PlaceType.SLAVER_ALLEY_AUCTIONING_BLOCK),
					new Value<>(new Color(0x00ff00), PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS))),
	
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
					new Value<>(new Color(0xff00ff), PlaceType.SHOPPING_ARCADE_GENERIC_SHOP),
					new Value<>(new Color(0x008000), PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP),
					new Value<>(new Color(0x00ff00), PlaceType.SHOPPING_ARCADE_SUPPLIER_DEPOT)
					)){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},
	
	SUPPLIER_DEN("Supplier Depot",
			Colour.BASE_CRIMSON,
			1,
			"/com/lilithsthrone/res/map/dominion/shoppingArcade/supplierDen.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.SUPPLIER_DEPOT_CORRIDOR),
					new Value<>(new Color(0xff0000), PlaceType.SUPPLIER_DEPOT_ENTRANCE),
					new Value<>(new Color(0xff00ff), PlaceType.SUPPLIER_DEPOT_STORAGE_ROOM),
					new Value<>(new Color(0x00ff00), PlaceType.SUPPLIER_DEPOT_OFFICE))),
	
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

	ANGELS_KISS_GROUND_FLOOR("Angel's Kiss",
			Colour.BASE_MAGENTA,
			1,
			"/com/lilithsthrone/res/map/dominion/angelsKiss/angelsKissGroundFloor.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.ANGELS_KISS_CORRIDOR),
					new Value<>(new Color(0x00ff00), PlaceType.ANGELS_KISS_ENTRANCE),
					new Value<>(new Color(0xff0000), PlaceType.ANGELS_KISS_STAIRCASE_UP),
					new Value<>(new Color(0x00ffff), PlaceType.ANGELS_KISS_OFFICE),
					new Value<>(new Color(0xff00ff), PlaceType.ANGELS_KISS_BEDROOM))),
	
	ANGELS_KISS_FIRST_FLOOR("Angel's Kiss",
			Colour.BASE_MAGENTA,
			1,
			"/com/lilithsthrone/res/map/dominion/angelsKiss/angelsKissFirstFloor.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.ANGELS_KISS_CORRIDOR),
					new Value<>(new Color(0xff0000), PlaceType.ANGELS_KISS_STAIRCASE_DOWN),
					new Value<>(new Color(0xff00ff), PlaceType.ANGELS_KISS_BEDROOM),
					new Value<>(new Color(0xffff00), PlaceType.ANGELS_KISS_BEDROOM_BUNNY),
					new Value<>(new Color(0xff8000), PlaceType.ANGELS_KISS_BEDROOM_LOPPY))),
	

	NIGHTLIFE_CLUB("The Watering Hole",
			Colour.BASE_BLUE,
			1,
			"/com/lilithsthrone/res/map/dominion/nightLife/wateringHole.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					
					new Value<>(new Color(0x00ff00), PlaceType.WATERING_HOLE_ENTRANCE),
					
					new Value<>(new Color(0x808080), PlaceType.WATERING_HOLE_MAIN_AREA),
					
					new Value<>(new Color(0x0080ff), PlaceType.WATERING_HOLE_SEATING_AREA),
					new Value<>(new Color(0xff00ff), PlaceType.WATERING_HOLE_VIP_AREA),
					new Value<>(new Color(0xff8000), PlaceType.WATERING_HOLE_BAR),
					new Value<>(new Color(0xffff00), PlaceType.WATERING_HOLE_DANCE_FLOOR),
					new Value<>(new Color(0xff0000), PlaceType.WATERING_HOLE_TOILETS)
					)){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},
	
	// Other:

	SUBMISSION("Submission",
			Colour.BASE_GREEN,
			1,
			"/com/lilithsthrone/res/map/submission/submission.png",
			Util.newHashMapOfValues(
					
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),

					new Value<>(new Color(0x808000), PlaceType.SUBMISSION_ENTRANCE),
					
					new Value<>(new Color(0x808080), PlaceType.SUBMISSION_WALKWAYS),
					new Value<>(new Color(0xc10000), PlaceType.SUBMISSION_TUNNELS),
					
					new Value<>(new Color(0x008080), PlaceType.SUBMISSION_BAT_CAVERNS),
					new Value<>(new Color(0xff9100), PlaceType.SUBMISSION_RAT_WARREN),
					new Value<>(new Color(0xffff00), PlaceType.SUBMISSION_GAMBLING_DEN),
					
					new Value<>(new Color(0xff00ff), PlaceType.SUBMISSION_LILIN_PALACE),
					new Value<>(new Color(0x000000), PlaceType.SUBMISSION_LILIN_PALACE_GATE),
					new Value<>(new Color(0x404040), PlaceType.SUBMISSION_LILIN_PALACE_CAVERN),
					
					new Value<>(new Color(0x00ff00), PlaceType.SUBMISSION_IMP_FORTRESS_1),
					new Value<>(new Color(0x32ff00), PlaceType.SUBMISSION_IMP_FORTRESS_2),
					new Value<>(new Color(0x64ff00), PlaceType.SUBMISSION_IMP_FORTRESS_3),
					new Value<>(new Color(0x96ff00), PlaceType.SUBMISSION_IMP_FORTRESS_4),
					new Value<>(new Color(0xc8ff00), PlaceType.SUBMISSION_IMP_FORTRESS_5),
					new Value<>(new Color(0xfaff00), PlaceType.SUBMISSION_IMP_FORTRESS_6))),

	BAT_CAVERNS("Bat Caverns",
			Colour.BASE_BLACK,
			1,
			"/com/lilithsthrone/res/map/submission/batCaverns/batCaverns.png",
			Util.newHashMapOfValues(
					
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),

					new Value<>(new Color(0x00ff00), PlaceType.BAT_CAVERN_ENTRANCE),
					
					new Value<>(new Color(0x008080), PlaceType.BAT_CAVERN_DARK),
					new Value<>(new Color(0x808080), PlaceType.BAT_CAVERN_LIGHT),
					
					new Value<>(new Color(0x0080ff), PlaceType.BAT_CAVERN_RIVER),
					new Value<>(new Color(0x40b4ff), PlaceType.BAT_CAVERN_RIVER_CROSSING),
					new Value<>(new Color(0x004080), PlaceType.BAT_CAVERN_RIVER_END),
					
					new Value<>(new Color(0xff80ff), PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR))),

	SLIME_QUEENS_LAIR_GROUND_FLOOR("Slime Queen's Tower",
			Colour.BASE_PINK,
			1,
			"/com/lilithsthrone/res/map/submission/slimeQueensLair/slimeQueensLairGroundFloor.png",
			Util.newHashMapOfValues(
					
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),

					new Value<>(new Color(0x808080), PlaceType.SLIME_QUEENS_LAIR_CORRIDOR),
					
					new Value<>(new Color(0x00ff00), PlaceType.SLIME_QUEENS_LAIR_ENTRANCE),
					new Value<>(new Color(0xff0000), PlaceType.SLIME_QUEENS_LAIR_STAIRS_UP),
					
					new Value<>(new Color(0xff8000), PlaceType.SLIME_QUEENS_LAIR_STORAGE_VATS),
					new Value<>(new Color(0x40b4ff), PlaceType.SLIME_QUEENS_LAIR_ROOM),
					
					new Value<>(new Color(0xff80ff), PlaceType.SLIME_QUEENS_LAIR_ENTRANCE_GUARDS),
					
					new Value<>(new Color(0xffff00), PlaceType.SLIME_QUEENS_LAIR_SLIME_QUEEN))),

	SLIME_QUEENS_LAIR_FIRST_FLOOR("Slime Queen's Tower",
			Colour.BASE_PINK,
			1,
			"/com/lilithsthrone/res/map/submission/slimeQueensLair/slimeQueensLairFirstFloor.png",
			Util.newHashMapOfValues(
					
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),

					new Value<>(new Color(0x808080), PlaceType.SLIME_QUEENS_LAIR_CORRIDOR),
					
					new Value<>(new Color(0x00ff00), PlaceType.SLIME_QUEENS_LAIR_STAIRS_DOWN),
					
					new Value<>(new Color(0x40b4ff), PlaceType.SLIME_QUEENS_LAIR_ROOM),
					
					new Value<>(new Color(0xff00ff), PlaceType.SLIME_QUEENS_LAIR_ROYAL_GUARD),
					new Value<>(new Color(0xffff00), PlaceType.SLIME_QUEENS_LAIR_SLIME_QUEEN))),

	GAMBLING_DEN("Gambling Den",
			Colour.BASE_GOLD,
			1,
			"/com/lilithsthrone/res/map/submission/gamblingDen/gamblingDen.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.GAMBLING_DEN_CORRIDOR),
					
					new Value<>(new Color(0x00ff00), PlaceType.GAMBLING_DEN_ENTRANCE),
					
					new Value<>(new Color(0xffff00), PlaceType.GAMBLING_DEN_TRADER),
					
					new Value<>(new Color(0x0080ff), PlaceType.GAMBLING_DEN_GAMBLING),
					
					new Value<>(new Color(0xff80ff), PlaceType.GAMBLING_DEN_PREGNANCY),
					new Value<>(new Color(0xff00ff), PlaceType.GAMBLING_DEN_FUTA_PREGNANCY),
					new Value<>(new Color(0xff8000), PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE)
					)){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},
	
	JUNGLE(6,
			"jungle",
			Colour.BASE_GREEN_LIME,
			240,
			PlaceType.JUNGLE_PATH,
			PlaceType.JUNGLE_DENSE_JUNGLE,
			Util.newArrayListOfValues(
					PlaceType.JUNGLE_BROTHEL,
					PlaceType.JUNGLE_CLUB,
					PlaceType.JUNGLE_ENTRANCE),

			Util.newArrayListOfValues(PlaceType.JUNGLE_TENTACLE_QUEENS_LAIR));

	
	private final String name, fileLocation;
	private Colour colour;
	private int worldSize, timeToTransition;
	
	private int tileSetRowNumber, moveCost;
	private PlaceType standardPlace, cutOffZone;
	private List<PlaceType> places, dangerousPlaces;
	
	private boolean usesFile;
	private Map<Color, PlaceType> placesMap;
	

	WorldType(int worldSize,
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
