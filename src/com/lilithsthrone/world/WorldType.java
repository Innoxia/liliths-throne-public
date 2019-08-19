package com.lilithsthrone.world;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.1
 * @author Innoxia
 */
public enum WorldType {
	
	// Dominion:
	
	WORLD_MAP("Lilith's Realm",
			Colour.BASE_TAN,
			1,
			"/com/lilithsthrone/res/map/global/world_map.png",
			null,
			null,
			Util.newHashMapOfValues(
					new Value<>(new Color(0x61997e), PlaceType.WORLD_MAP_THICK_JUNGLE), // thick jungle
					new Value<>(new Color(0x81cca8), PlaceType.WORLD_MAP_JUNGLE), // jungle
					new Value<>(new Color(0xb377b0), PlaceType.WORLD_MAP_JUNGLE_CITY), // jungle city
					
					new Value<>(new Color(0x696969), PlaceType.WORLD_MAP_FOOTHILLS), // foothills
					new Value<>(new Color(0xc1c1c1), PlaceType.WORLD_MAP_MOUNTAINS), // low mountains
					new Value<>(new Color(0xe0e0e0), PlaceType.WORLD_MAP_SNOWY_MOUNTAINS), // snowy mountains
					
					new Value<>(new Color(0xffffff), PlaceType.WORLD_MAP_SNOWY_VALLEY), // snowy valley
					new Value<>(new Color(0xadffff), PlaceType.WORLD_MAP_GLACIAL_LAKE), // glacial lake
					
					new Value<>(new Color(0x8500ff), PlaceType.WORLD_MAP_DOMINION), // dominion

					new Value<>(new Color(0xcbf1d5), PlaceType.WORLD_MAP_GRASSLANDS), // wild grasslands
					new Value<>(new Color(0xe2ffd7), PlaceType.WORLD_MAP_FIELDS), // foloi fields
					new Value<>(new Color(0xb4c490), PlaceType.WORLD_MAP_FOREST), // forest
					new Value<>(new Color(0xd544ae), PlaceType.WORLD_MAP_FIELDS_CITY), // Elis
					
					new Value<>(new Color(0x98c488), PlaceType.WORLD_MAP_YOUKO_FOREST), // shinrin highland
					
					new Value<>(new Color(0x62e6d3), PlaceType.WORLD_MAP_WILD_RIVER), // dangerous river
					new Value<>(new Color(0xa7fce8), PlaceType.WORLD_MAP_RIVER), // river
					
					new Value<>(new Color(0xc4fcff), PlaceType.WORLD_MAP_SEA), // endless sea
					new Value<>(new Color(0x8264b0), PlaceType.WORLD_MAP_SEA_CITY), // sea city
					
					new Value<>(new Color(0xebffc4), PlaceType.WORLD_MAP_ARID_GRASSLAND), // arid grassland
					new Value<>(new Color(0xd3e6b0), PlaceType.WORLD_MAP_ARID_SAVANNAH), // savannah
					
					new Value<>(new Color(0xffefc4), PlaceType.WORLD_MAP_DESERT), // desert
					new Value<>(new Color(0xffce4a), PlaceType.WORLD_MAP_SAND_DUNES), // sand dunes
					new Value<>(new Color(0xd5445e), PlaceType.WORLD_MAP_DESERT_CITY), // desert city
					
					new Value<>(new Color(0xff8100), PlaceType.WORLD_MAP_VOLCANO), // volcano
					new Value<>(new Color(0x3b3b3b), PlaceType.WORLD_MAP_LAVA_FLOWS) // lava flows
					)) {
		@Override
		public boolean isDiscoveredOnStart() {
			return true;
		}
		
	},
	
	
	DOMINION("Dominion",
			Colour.BASE_PURPLE,
			1,
			"/com/lilithsthrone/res/map/dominion/dominion.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.DOMINION_PLAZA,
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

					new Value<>(new Color(0x8080ff), PlaceType.DOMINION_EXIT_WEST),
					new Value<>(new Color(0xff4a00), PlaceType.DOMINION_EXIT_NORTH),
					new Value<>(new Color(0x008040), PlaceType.DOMINION_EXIT_EAST),
					new Value<>(new Color(0xffff80), PlaceType.DOMINION_EXIT_SOUTH),
					
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
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.GENERIC_EMPTY_TILE,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0xff0000), PlaceType.GENERIC_EMPTY_TILE),
					new Value<>(new Color(0xffff00), PlaceType.GENERIC_HOLDING_CELL),
					new Value<>(new Color(0x0080ff), PlaceType.GENERIC_MUSEUM))),

	MUSEUM("Museum",
			Colour.BASE_BROWN,
			1,
			"/com/lilithsthrone/res/map/prologue/museum.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.MUSEUM_ENTRANCE,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0xff0000), PlaceType.MUSEUM_ENTRANCE),
					new Value<>(new Color(0x8000ff), PlaceType.MUSEUM_CROWDS),
					new Value<>(new Color(0x0080ff), PlaceType.MUSEUM_OFFICE),
					new Value<>(new Color(0xffff00), PlaceType.MUSEUM_STAGE),
					new Value<>(new Color(0xff8000), PlaceType.MUSEUM_ROOM),
					new Value<>(new Color(0x00ff00), PlaceType.MUSEUM_STAIRS),
					new Value<>(new Color(0x808080), PlaceType.MUSEUM_LOBBY))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},

	MUSEUM_LOST("Museum",
			Colour.BASE_BROWN,
			1,
			"/com/lilithsthrone/res/map/prologue/museum_lost.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.MUSEUM_MIRROR,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0xff8000), PlaceType.MUSEUM_ROOM),
					new Value<>(new Color(0x00ff00), PlaceType.MUSEUM_MIRROR),
					new Value<>(new Color(0x808080), PlaceType.MUSEUM_CORRIDOR))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},
	
	LILAYAS_HOUSE_GROUND_FLOOR("Lilaya's Home GF",
			Colour.BASE_BLUE_LIGHT,
			1,
			"/com/lilithsthrone/res/map/dominion/lilayasHome/lilayas_home_ground_floor.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.LILAYA_HOME_ENTRANCE_HALL,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.LILAYA_HOME_CORRIDOR),
					new Value<>(new Color(0xff0000), PlaceType.LILAYA_HOME_ENTRANCE_HALL),
					new Value<>(new Color(0x008000), PlaceType.LILAYA_HOME_GARDEN),
					new Value<>(new Color(0xff8000), PlaceType.LILAYA_HOME_LAB),
					new Value<>(new Color(0xffff00), PlaceType.LILAYA_HOME_BIRTHING_ROOM),
					new Value<>(new Color(0xff80ff), PlaceType.LILAYA_HOME_KITCHEN),
					new Value<>(new Color(0x00ffff), PlaceType.LILAYA_HOME_LIBRARY),
					new Value<>(new Color(0x8000ff), PlaceType.LILAYA_HOME_FOUNTAIN),
					new Value<>(new Color(0xff0080), PlaceType.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR),
					new Value<>(new Color(0xff00ff), PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR),
					new Value<>(new Color(0x00ff00), PlaceType.LILAYA_HOME_STAIR_UP))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},
	
	LILAYAS_HOUSE_FIRST_FLOOR("Lilaya's Home 1F",
			Colour.BASE_BLUE_LIGHT,
			1,
			"/com/lilithsthrone/res/map/dominion/lilayasHome/lilayas_home_first_floor.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.LILAYA_HOME_STAIR_DOWN,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.LILAYA_HOME_CORRIDOR),
					new Value<>(new Color(0xff00ff), PlaceType.LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR),
					new Value<>(new Color(0xff0080), PlaceType.LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR),
					new Value<>(new Color(0xff80ff), PlaceType.LILAYA_HOME_ROOM_LILAYA),
					new Value<>(new Color(0x0080ff), PlaceType.LILAYA_HOME_ROOM_ROSE),
					new Value<>(new Color(0x00ffff), PlaceType.LILAYA_HOME_ROOM_PLAYER),
					new Value<>(new Color(0xff0000), PlaceType.LILAYA_HOME_STAIR_DOWN))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},
	
	ZARANIX_HOUSE_FIRST_FLOOR("Zaranix's Home 1F",
			Colour.BASE_CRIMSON,
			1,
			"/com/lilithsthrone/res/map/dominion/zaranixHome/first_floor.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.ZARANIX_FF_STAIRS,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.ZARANIX_FF_CORRIDOR),
					new Value<>(new Color(0x00ff00), PlaceType.ZARANIX_FF_STAIRS),
					new Value<>(new Color(0xff80ff), PlaceType.ZARANIX_FF_OFFICE),
					new Value<>(new Color(0xff00ff), PlaceType.ZARANIX_FF_ROOM),
					new Value<>(new Color(0x8000ff), PlaceType.ZARANIX_FF_MAID))),
	
	ZARANIX_HOUSE_GROUND_FLOOR("Zaranix's Home GF",
			Colour.BASE_CRIMSON,
			1,
			"/com/lilithsthrone/res/map/dominion/zaranixHome/ground_floor.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.ZARANIX_GF_ENTRANCE,
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
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.HARPY_NESTS_ENTRANCE_ENFORCER_POST,
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
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.SLAVER_ALLEY_ENTRANCE,
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
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.SHOPPING_ARCADE_ENTRANCE,
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
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.SUPPLIER_DEPOT_ENTRANCE,
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
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.ENFORCER_HQ_ENTRANCE,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.ENFORCER_HQ_CORRIDOR),
					new Value<>(new Color(0xff0000), PlaceType.ENFORCER_HQ_ENTRANCE),
					new Value<>(new Color(0xffff00), PlaceType.ENFORCER_HQ_GUARDED_DOOR),
					new Value<>(new Color(0x8000ff), PlaceType.ENFORCER_HQ_WAITING_AREA),
					new Value<>(new Color(0x0080ff), PlaceType.ENFORCER_HQ_RECEPTION_DESK),
					new Value<>(new Color(0xff8000), PlaceType.ENFORCER_HQ_BRAXS_OFFICE))),

	CITY_HALL("City Hall",
			Colour.BASE_PURPLE,
			1,
			"/com/lilithsthrone/res/map/dominion/cityHall/city_hall.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.CITY_HALL_ENTRANCE,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.CITY_HALL_CORRIDOR),
					new Value<>(new Color(0xff0000), PlaceType.CITY_HALL_ENTRANCE),
					new Value<>(new Color(0xffff00), PlaceType.CITY_HALL_INFORMATION_DESK),
					new Value<>(new Color(0x8000ff), PlaceType.CITY_HALL_WAITING_AREA),
					new Value<>(new Color(0xff8000), PlaceType.CITY_HALL_OFFICE),
					new Value<>(new Color(0x00ff00), PlaceType.CITY_HALL_STAIRS),
					new Value<>(new Color(0xff0080), PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS),
					new Value<>(new Color(0xff00ff), PlaceType.CITY_HALL_ARCHIVES),
					new Value<>(new Color(0xffff80), PlaceType.CITY_HALL_BUREAU_OF_PROPERTY_RIGHTS_AND_COMMERCE))),
	
	ANGELS_KISS_GROUND_FLOOR("Angel's Kiss GF",
			Colour.BASE_MAGENTA,
			1,
			"/com/lilithsthrone/res/map/dominion/angelsKiss/angelsKissGroundFloor.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.ANGELS_KISS_ENTRANCE,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.ANGELS_KISS_CORRIDOR),
					new Value<>(new Color(0x00ff00), PlaceType.ANGELS_KISS_ENTRANCE),
					new Value<>(new Color(0xff0000), PlaceType.ANGELS_KISS_STAIRCASE_UP),
					new Value<>(new Color(0x00ffff), PlaceType.ANGELS_KISS_OFFICE),
					new Value<>(new Color(0xff00ff), PlaceType.ANGELS_KISS_BEDROOM))),
	
	ANGELS_KISS_FIRST_FLOOR("Angel's Kiss 1F",
			Colour.BASE_MAGENTA,
			1,
			"/com/lilithsthrone/res/map/dominion/angelsKiss/angelsKissFirstFloor.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.ANGELS_KISS_STAIRCASE_DOWN,
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
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.WATERING_HOLE_ENTRANCE,
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
	
	DADDYS_APARTMENT("Daddy's apartment",
			Colour.RACE_DEMON,
			1,
			"/com/lilithsthrone/res/map/dominion/daddy/apartment.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.DOMINION_DEMON_HOME_DADDY,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					
					new Value<>(new Color(0x00ff00), PlaceType.DADDY_APARTMENT_ENTRANCE),
					new Value<>(new Color(0xffff00), PlaceType.DADDY_APARTMENT_LOUNGE),
					new Value<>(new Color(0x00ffff), PlaceType.DADDY_APARTMENT_KITCHEN),
					new Value<>(new Color(0xff00ff), PlaceType.DADDY_APARTMENT_BEDROOM)
					)){
		@Override
		public String getName() {
			return UtilText.parse("[daddy.NamePos] apartment");
		}
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
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.SUBMISSION_ENTRANCE,
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
					
					new Value<>(new Color(0x004fc9), PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA),
					new Value<>(new Color(0x658cc9), PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA),
					
					new Value<>(new Color(0x6928c9), PlaceType.SUBMISSION_IMP_FORTRESS_DEMON),
					new Value<>(new Color(0x8d65c9), PlaceType.SUBMISSION_IMP_TUNNELS_DEMON),
					
					new Value<>(new Color(0xa228c9), PlaceType.SUBMISSION_IMP_FORTRESS_FEMALES),
					new Value<>(new Color(0xb065c9), PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES),
					
					new Value<>(new Color(0x0096c9), PlaceType.SUBMISSION_IMP_FORTRESS_MALES),
					new Value<>(new Color(0x65b0c9), PlaceType.SUBMISSION_IMP_TUNNELS_MALES)
					)),


	LYSSIETH_PALACE("Lyssieth's Palace",
			Colour.BASE_PURPLE,
			1,
			"/com/lilithsthrone/res/map/submission/lyssiethsPalace/groundFloor.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.LYSSIETH_PALACE_ENTRANCE,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.LYSSIETH_PALACE_CORRIDOR),
					new Value<>(new Color(0x404040), PlaceType.LYSSIETH_PALACE_WINDOWS),

					new Value<>(new Color(0x00ff00), PlaceType.LYSSIETH_PALACE_ENTRANCE),
					new Value<>(new Color(0xff80ff), PlaceType.LYSSIETH_PALACE_ROOM),
					new Value<>(new Color(0xff8000), PlaceType.LYSSIETH_PALACE_HALL),
					new Value<>(new Color(0x8000ff), PlaceType.LYSSIETH_PALACE_OFFICE),
					new Value<>(new Color(0xff0080), PlaceType.LYSSIETH_PALACE_SIREN_OFFICE),
					
					new Value<>(new Color(0xff0000), PlaceType.LYSSIETH_PALACE_STAIRS_1),
					new Value<>(new Color(0x0000ff), PlaceType.LYSSIETH_PALACE_STAIRS_2)
					
					)),
	
	IMP_FORTRESS_ALPHA("Imp Fortress A",
			Colour.BASE_CRIMSON,
			1,
			"/com/lilithsthrone/res/map/submission/impFortress/fortress1Map.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.FORTRESS_ALPHA_ENTRANCE,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.FORTRESS_ALPHA_COURTYARD),
					new Value<>(new Color(0x00ff00), PlaceType.FORTRESS_ALPHA_ENTRANCE),
					new Value<>(new Color(0xff8000), PlaceType.FORTRESS_ALPHA_KEEP))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},

	IMP_FORTRESS_DEMON("Imp Citadel",
			Colour.BASE_PURPLE,
			1,
			"/com/lilithsthrone/res/map/submission/impFortress/fortress2Map.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.FORTRESS_DEMON_ENTRANCE,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.FORTRESS_DEMON_COURTYARD),
					new Value<>(new Color(0x00ff00), PlaceType.FORTRESS_DEMON_ENTRANCE),
					new Value<>(new Color(0x65b0c9), PlaceType.FORTRESS_DEMON_WELL),
					new Value<>(new Color(0xff8000), PlaceType.FORTRESS_DEMON_KEEP),
					new Value<>(new Color(0x8000ff), PlaceType.FORTRESS_DEMON_CELLS),
					
					new Value<>(new Color(0x80ff00), PlaceType.FORTRESS_LAB),
					
					new Value<>(new Color(0xff00ff), PlaceType.FORTRESS_DEMON_TREASURY))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},

	IMP_FORTRESS_FEMALES("Imp Fortress F",
			Colour.BASE_PINK,
			1,
			"/com/lilithsthrone/res/map/submission/impFortress/fortress3Map.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.FORTRESS_FEMALES_ENTRANCE,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.FORTRESS_FEMALES_COURTYARD),
					new Value<>(new Color(0x00ff00), PlaceType.FORTRESS_FEMALES_ENTRANCE),
					new Value<>(new Color(0xff8000), PlaceType.FORTRESS_FEMALES_KEEP))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},

	IMP_FORTRESS_MALES("Imp Fortress M",
			Colour.BASE_BLUE,
			1,
			"/com/lilithsthrone/res/map/submission/impFortress/fortress4Map.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.FORTRESS_MALES_ENTRANCE,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.FORTRESS_MALES_COURTYARD),
					new Value<>(new Color(0x00ff00), PlaceType.FORTRESS_MALES_ENTRANCE),
					new Value<>(new Color(0xff8000), PlaceType.FORTRESS_MALES_KEEP))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},

	BAT_CAVERNS("Bat Caverns",
			Colour.BASE_BLACK,
			1,
			"/com/lilithsthrone/res/map/submission/batCaverns/batCaverns.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.BAT_CAVERN_ENTRANCE,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),

					new Value<>(new Color(0x00ff00), PlaceType.BAT_CAVERN_ENTRANCE),
					
					new Value<>(new Color(0x008080), PlaceType.BAT_CAVERN_DARK),
					new Value<>(new Color(0x808080), PlaceType.BAT_CAVERN_LIGHT),
					
					new Value<>(new Color(0x0080ff), PlaceType.BAT_CAVERN_RIVER),
					new Value<>(new Color(0x40b4ff), PlaceType.BAT_CAVERN_RIVER_CROSSING),
					new Value<>(new Color(0x004080), PlaceType.BAT_CAVERN_RIVER_END),
					
					new Value<>(new Color(0xff80ff), PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR))),

	SLIME_QUEENS_LAIR_GROUND_FLOOR("Slime Queen's Tower GF",
			Colour.BASE_PINK,
			1,
			"/com/lilithsthrone/res/map/submission/slimeQueensLair/slimeQueensLairGroundFloor.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.SLIME_QUEENS_LAIR_ENTRANCE,
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),

					new Value<>(new Color(0x808080), PlaceType.SLIME_QUEENS_LAIR_CORRIDOR),
					
					new Value<>(new Color(0x00ff00), PlaceType.SLIME_QUEENS_LAIR_ENTRANCE),
					new Value<>(new Color(0xff0000), PlaceType.SLIME_QUEENS_LAIR_STAIRS_UP),
					
					new Value<>(new Color(0xff8000), PlaceType.SLIME_QUEENS_LAIR_STORAGE_VATS),
					new Value<>(new Color(0x40b4ff), PlaceType.SLIME_QUEENS_LAIR_ROOM),
					
					new Value<>(new Color(0xff80ff), PlaceType.SLIME_QUEENS_LAIR_ENTRANCE_GUARDS),
					
					new Value<>(new Color(0xffff00), PlaceType.SLIME_QUEENS_LAIR_SLIME_QUEEN))),

	SLIME_QUEENS_LAIR_FIRST_FLOOR("Slime Queen's Tower 1F",
			Colour.BASE_PINK,
			1,
			"/com/lilithsthrone/res/map/submission/slimeQueensLair/slimeQueensLairFirstFloor.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.SLIME_QUEENS_LAIR_STAIRS_DOWN,
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
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.GAMBLING_DEN_ENTRANCE,
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
	};
	
//	JUNGLE(6,
//			"jungle",
//			Colour.BASE_GREEN_LIME,
//			240,
//			PlaceType.WORLD_MAP_DOMINION,
//			PlaceType.JUNGLE_ENTRANCE,
//			PlaceType.JUNGLE_PATH,
//			PlaceType.JUNGLE_DENSE_JUNGLE,
//			Util.newArrayListOfValues(
//					PlaceType.JUNGLE_BROTHEL,
//					PlaceType.JUNGLE_CLUB,
//					PlaceType.JUNGLE_ENTRANCE),
//
//			Util.newArrayListOfValues(PlaceType.JUNGLE_TENTACLE_QUEENS_LAIR));

	
	private final String name;
	private final String fileLocation;
	private Colour colour;
	private int worldSize;
	private int timeToTransition;
	
	private int tileSetRowNumber;
	private int moveCost;
	private AbstractPlaceType standardPlace, cutOffZone;
	private List<AbstractPlaceType> places, dangerousPlaces;
	
	private boolean usesFile;
	private AbstractPlaceType globalMapLocation;
	private AbstractPlaceType entryFromGlobalMapLocation;
	private Map<Color, AbstractPlaceType> placesMap;
	

	WorldType(int worldSize,
			String name,
			Colour colour,
			int timeToTransition,
			AbstractPlaceType globalMapLocation,
			AbstractPlaceType entryFromGlobalMapLocation,
			AbstractPlaceType standardPlace,
			AbstractPlaceType cutOffZone,
			List<AbstractPlaceType> places,
			List<AbstractPlaceType> dangerousPlaces) {
		this.worldSize=worldSize;
		
		this.name = name;
		this.colour = colour;
		this.timeToTransition=timeToTransition;
		this.moveCost = 5;

		this.standardPlace = standardPlace;
		this.cutOffZone = cutOffZone;

		this.globalMapLocation = globalMapLocation;
		this.entryFromGlobalMapLocation = entryFromGlobalMapLocation;
		
		this.places = places;
		this.dangerousPlaces = dangerousPlaces;
		
		fileLocation = null;
		usesFile = false;
		
	}
	
	WorldType(String name,
			Colour colour,
			int timeToTransition,
			String fileLocation,
			AbstractPlaceType globalMapLocation,
			AbstractPlaceType entryFromGlobalMapLocation,
			Map<Color, AbstractPlaceType> placesMap) {
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

	public int getWorldSize() {
		return worldSize;
	}

}
