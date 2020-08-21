package com.lilithsthrone.world;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.7.2
 * @author Innoxia
 */
public class WorldType {
	
	// Dominion:
	
	public static AbstractWorldType WORLD_MAP = new AbstractWorldType("Lilith's Realm",
			PresetColour.BASE_TAN,
			1,
			true,
			true,
			TeleportPermissions.BOTH,
			"/com/lilithsthrone/res/map/global/world_map.png", null, null, Util.newHashMapOfValues(
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
	};
	
	
	public static AbstractWorldType DOMINION = new AbstractWorldType("Dominion",
			PresetColour.BASE_PURPLE,
			1,
			true,
			true,
			TeleportPermissions.BOTH,
			"/com/lilithsthrone/res/map/dominion/dominion.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.DOMINION_PLAZA, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					
					new Value<>(new Color(0x808080), PlaceType.DOMINION_STREET),
					new Value<>(new Color(0x404040), PlaceType.DOMINION_BOULEVARD),
					new Value<>(new Color(0x808000), PlaceType.DOMINION_EXIT_TO_SUBMISSION),
					
					new Value<>(new Color(0xbf8000), PlaceType.DOMINION_BACK_ALLEYS_SAFE),
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
					new Value<>(new Color(0xff4000), PlaceType.DOMINION_RED_LIGHT_DISTRICT),
					new Value<>(new Color(0xffbf00), PlaceType.DOMINION_HOME_IMPROVEMENT),
					new Value<>(new Color(0xff0080), PlaceType.DOMINION_WAREHOUSES))) {
	};
	
	public static AbstractWorldType EMPTY = new AbstractWorldType("Empty (Holding world)",
			PresetColour.BASE_BROWN,
			1,
			false,
			true,
			TeleportPermissions.NONE,
			"/com/lilithsthrone/res/map/empty.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.GENERIC_EMPTY_TILE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0xff0000), PlaceType.GENERIC_EMPTY_TILE),
					new Value<>(new Color(0xffff00), PlaceType.GENERIC_HOLDING_CELL),
					new Value<>(new Color(0x0080ff), PlaceType.GENERIC_MUSEUM))) {
	};

	public static AbstractWorldType MUSEUM = new AbstractWorldType("Museum",
			PresetColour.BASE_BROWN,
			1,
			false,
			true,
			TeleportPermissions.NONE,
			"/com/lilithsthrone/res/map/prologue/museum.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.MUSEUM_ENTRANCE, Util.newHashMapOfValues(
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
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};

	public static AbstractWorldType MUSEUM_LOST = new AbstractWorldType("Museum",
			PresetColour.BASE_BROWN,
			1,
			false,
			true,
			TeleportPermissions.NONE,
			"/com/lilithsthrone/res/map/prologue/museum_lost.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.MUSEUM_MIRROR, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0xff8000), PlaceType.MUSEUM_ROOM),
					new Value<>(new Color(0x00ff00), PlaceType.MUSEUM_MIRROR),
					new Value<>(new Color(0x808080), PlaceType.MUSEUM_CORRIDOR))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	public static AbstractWorldType LILAYAS_HOUSE_GROUND_FLOOR = new AbstractWorldType("Lilaya's Home GF",
			PresetColour.BASE_BLUE_LIGHT,
			1,
			true,
			false,
			TeleportPermissions.BOTH,
			"/com/lilithsthrone/res/map/dominion/lilayasHome/lilayas_home_ground_floor.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.LILAYA_HOME_ENTRANCE_HALL, Util.newHashMapOfValues(
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
					new Value<>(new Color(0x00ff00), PlaceType.LILAYA_HOME_STAIR_UP),
					new Value<>(new Color(0x00ff80), PlaceType.LILAYA_HOME_STAIR_UP_SECONDARY))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	public static AbstractWorldType LILAYAS_HOUSE_FIRST_FLOOR = new AbstractWorldType("Lilaya's Home 1F",
			PresetColour.BASE_BLUE_LIGHT,
			1,
			true,
			false,
			TeleportPermissions.BOTH,
			"/com/lilithsthrone/res/map/dominion/lilayasHome/lilayas_home_first_floor.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.LILAYA_HOME_STAIR_DOWN, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.LILAYA_HOME_CORRIDOR),
					new Value<>(new Color(0xff00ff), PlaceType.LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR),
					new Value<>(new Color(0xff0080), PlaceType.LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR),
					new Value<>(new Color(0xff80ff), PlaceType.LILAYA_HOME_ROOM_LILAYA),
					new Value<>(new Color(0x0080ff), PlaceType.LILAYA_HOME_ROOM_ROSE),
					new Value<>(new Color(0x00ffff), PlaceType.LILAYA_HOME_ROOM_PLAYER),
					new Value<>(new Color(0xff0000), PlaceType.LILAYA_HOME_STAIR_DOWN),
					new Value<>(new Color(0xff8000), PlaceType.LILAYA_HOME_STAIR_DOWN_SECONDARY))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	public static AbstractWorldType ZARANIX_HOUSE_FIRST_FLOOR = new AbstractWorldType("Zaranix's Home 1F",
			PresetColour.BASE_CRIMSON,
			1,
			false,
			false,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/dominion/zaranixHome/first_floor.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.ZARANIX_FF_STAIRS, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.ZARANIX_FF_CORRIDOR),
					new Value<>(new Color(0x00ff00), PlaceType.ZARANIX_FF_STAIRS),
					new Value<>(new Color(0xff80ff), PlaceType.ZARANIX_FF_OFFICE),
					new Value<>(new Color(0xff00ff), PlaceType.ZARANIX_FF_ROOM),
					new Value<>(new Color(0x8000ff), PlaceType.ZARANIX_FF_MAID))) {
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "You can't have sex while in Zaranix's house!";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	public static AbstractWorldType ZARANIX_HOUSE_GROUND_FLOOR = new AbstractWorldType("Zaranix's Home GF",
			PresetColour.BASE_CRIMSON,
			1,
			false,
			false,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/dominion/zaranixHome/ground_floor.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.ZARANIX_GF_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.ZARANIX_GF_CORRIDOR),
					new Value<>(new Color(0x00ff00), PlaceType.ZARANIX_GF_STAIRS),
					new Value<>(new Color(0xff0000), PlaceType.ZARANIX_GF_ENTRANCE),
					new Value<>(new Color(0xffff00), PlaceType.ZARANIX_GF_LOUNGE),
					new Value<>(new Color(0xff00ff), PlaceType.ZARANIX_GF_ROOM),
					new Value<>(new Color(0x8000ff), PlaceType.ZARANIX_GF_MAID),
					new Value<>(new Color(0x00ffff), PlaceType.ZARANIX_GF_GARDEN_ROOM),
					new Value<>(new Color(0x008000), PlaceType.ZARANIX_GF_GARDEN),
					new Value<>(new Color(0xff8000), PlaceType.ZARANIX_GF_GARDEN_ENTRY))) {
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "You can't have sex while in Zaranix's house!";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};

	public static AbstractWorldType HARPY_NEST = new AbstractWorldType("Harpy Nests",
			PresetColour.BASE_CRIMSON,
			1,
			true,
			true,
			TeleportPermissions.BOTH,
			"/com/lilithsthrone/res/map/dominion/harpyNests/harpyNests.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.HARPY_NESTS_ENTRANCE_ENFORCER_POST, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					
					new Value<>(new Color(0x808080), PlaceType.HARPY_NESTS_WALKWAYS),
					new Value<>(new Color(0x404040), PlaceType.HARPY_NESTS_WALKWAYS_BRIDGE),
					
					new Value<>(new Color(0x00ff80), PlaceType.HARPY_NESTS_ENTRANCE_ENFORCER_POST),
					new Value<>(new Color(0xff0000), PlaceType.HARPY_NESTS_HARPY_NEST_RED),
					new Value<>(new Color(0xff00ff), PlaceType.HARPY_NESTS_HARPY_NEST_PINK),
					new Value<>(new Color(0xffff00), PlaceType.HARPY_NESTS_HARPY_NEST_YELLOW),
					new Value<>(new Color(0xff9100), PlaceType.HARPY_NESTS_HELENAS_NEST))) {
	};
	
	public static AbstractWorldType SLAVER_ALLEY = new AbstractWorldType("Slaver Alley",
			PresetColour.BASE_RED,
			1,
			true,
			true,
			TeleportPermissions.BOTH,
			"/com/lilithsthrone/res/map/dominion/slaverAlley/slaverAlley.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.SLAVER_ALLEY_ENTRANCE, Util.newHashMapOfValues(
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
					
					new Value<>(new Color(0xbfff00), PlaceType.SLAVER_ALLEY_BOUNTY_HUNTERS),
					
					new Value<>(new Color(0x0000ff), PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION),
					new Value<>(new Color(0xff0080), PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP),
					
					new Value<>(new Color(0xffff00), PlaceType.SLAVER_ALLEY_AUCTIONING_BLOCK),
					new Value<>(new Color(0x00ff00), PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS))) {
	};
	
	public static AbstractWorldType SHOPPING_ARCADE = new AbstractWorldType("Shopping Arcade",
			PresetColour.BASE_YELLOW,
			1,
			true,
			true,
			TeleportPermissions.BOTH,
			"/com/lilithsthrone/res/map/dominion/shoppingArcade/shoppingArcade.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.SHOPPING_ARCADE_ENTRANCE, Util.newHashMapOfValues(
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
					new Value<>(new Color(0x00ff00), PlaceType.SHOPPING_ARCADE_SUPPLIER_DEPOT),
					new Value<>(new Color(0x808000), PlaceType.SHOPPING_ARCADE_ANTIQUES)
					)){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			if((character != null) && !character.getLocationPlace().getPlaceType().equals(PlaceType.SHOPPING_ARCADE_PATH)) {
				return "This isn't a suitable place in which to be having sex!";
			}
			return "";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	public static AbstractWorldType SUPPLIER_DEN = new AbstractWorldType("Supplier Depot",
			PresetColour.BASE_CRIMSON,
			1,
			false,
			false,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/dominion/shoppingArcade/supplierDen.png",
			PlaceType.WORLD_MAP_DOMINION, PlaceType.SUPPLIER_DEPOT_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.SUPPLIER_DEPOT_CORRIDOR),
					new Value<>(new Color(0xff0000), PlaceType.SUPPLIER_DEPOT_ENTRANCE),
					new Value<>(new Color(0xff00ff), PlaceType.SUPPLIER_DEPOT_STORAGE_ROOM),
					new Value<>(new Color(0x00ff00), PlaceType.SUPPLIER_DEPOT_OFFICE))) {
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "This isn't a suitable place in which to be having sex!";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	public static AbstractWorldType ENFORCER_HQ = new AbstractWorldType("Enforcer HQ",
			PresetColour.BASE_BLUE,
			1,
			false,
			false,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/dominion/enforcerHQ/enforcerHQ.png",
			PlaceType.WORLD_MAP_DOMINION, PlaceType.ENFORCER_HQ_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.ENFORCER_HQ_CORRIDOR),
					new Value<>(new Color(0xb9b9b9), PlaceType.ENFORCER_HQ_CELLS_CORRIDOR),
					
					new Value<>(new Color(0xff0000), PlaceType.ENFORCER_HQ_ENTRANCE),
					new Value<>(new Color(0x8000ff), PlaceType.ENFORCER_HQ_WAITING_AREA),
					new Value<>(new Color(0x0080ff), PlaceType.ENFORCER_HQ_RECEPTION_DESK),
					
					new Value<>(new Color(0xffff00), PlaceType.ENFORCER_HQ_GUARDED_DOOR),
					new Value<>(new Color(0xff0080), PlaceType.ENFORCER_HQ_LOCKED_DOOR),
					

					new Value<>(new Color(0xff8000), PlaceType.ENFORCER_HQ_BRAXS_OFFICE),
					new Value<>(new Color(0x00ffff), PlaceType.ENFORCER_HQ_OFFICE),
					new Value<>(new Color(0xff8080), PlaceType.ENFORCER_HQ_CELLS_OFFICE),
					new Value<>(new Color(0x3b3b3b), PlaceType.ENFORCER_HQ_CELL))) {
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "You can't have sex in the Enforcer HQ!";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};

	public static AbstractWorldType ENFORCER_WAREHOUSE = new AbstractWorldType("SWORD Warehouse",
			PresetColour.BASE_BLUE,
			1,
			false,
			false,
			TeleportPermissions.NONE,
			"/com/lilithsthrone/res/map/dominion/enforcerWarehouse/enforcerWarehouse.png",
			PlaceType.WORLD_MAP_DOMINION, PlaceType.ENFORCER_WAREHOUSE_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0xff0000), PlaceType.ENFORCER_WAREHOUSE_ENTRANCE),
					new Value<>(new Color(0x808080), PlaceType.ENFORCER_WAREHOUSE_CORRIDOR),
					new Value<>(new Color(0xff8080), PlaceType.ENFORCER_WAREHOUSE_CLAIRE_WARNING),

					new Value<>(new Color(0x404040), PlaceType.ENFORCER_WAREHOUSE_ENCLOSURE),
					new Value<>(new Color(0x00ff00), PlaceType.ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_PADS),
					new Value<>(new Color(0x00ffff), PlaceType.ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_SHELVING),
					
					new Value<>(new Color(0xff0080), PlaceType.ENFORCER_WAREHOUSE_ENFORCER_GUARD_POST),
					
					new Value<>(new Color(0xff8000), PlaceType.ENFORCER_WAREHOUSE_CRATES),
					new Value<>(new Color(0xffff00), PlaceType.ENFORCER_WAREHOUSE_CRATES_ARK),
					new Value<>(new Color(0xff00ff), PlaceType.ENFORCER_WAREHOUSE_CRATES_LUST_WEAPON),
					new Value<>(new Color(0x8000ff), PlaceType.ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK))) {
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "You can't have sex in such a dangerous place!";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	public static AbstractWorldType CITY_HALL = new AbstractWorldType("City Hall",
			PresetColour.BASE_PURPLE,
			1,
			false,
			false,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/dominion/cityHall/city_hall.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.CITY_HALL_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.CITY_HALL_CORRIDOR),
					new Value<>(new Color(0xff0000), PlaceType.CITY_HALL_ENTRANCE),
					new Value<>(new Color(0xffff00), PlaceType.CITY_HALL_INFORMATION_DESK),
					new Value<>(new Color(0x8000ff), PlaceType.CITY_HALL_WAITING_AREA),
					new Value<>(new Color(0xff8000), PlaceType.CITY_HALL_OFFICE),
					new Value<>(new Color(0x00ff00), PlaceType.CITY_HALL_STAIRS),
					new Value<>(new Color(0xff0080), PlaceType.CITY_HALL_BUREAU_OF_DEMOGRAPHICS),
					new Value<>(new Color(0xff00ff), PlaceType.CITY_HALL_ARCHIVES),
					new Value<>(new Color(0xffff80), PlaceType.CITY_HALL_BUREAU_OF_PROPERTY_RIGHTS_AND_COMMERCE))) {
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "This isn't a suitable place in which to be having sex!";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	

	public static AbstractWorldType HOME_IMPROVEMENTS = new AbstractWorldType("Argus's DIY Depot",
			PresetColour.BASE_ORANGE,
			1,
			false,
			false,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/dominion/homeImprovements/homeImprovements.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.HOME_IMPROVEMENTS_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.HOME_IMPROVEMENTS_CORRIDOR),
					new Value<>(new Color(0x00ff00), PlaceType.HOME_IMPROVEMENTS_ENTRANCE),
					new Value<>(new Color(0xffff00), PlaceType.HOME_IMPROVEMENTS_SHELVING_PREMIUM),
					new Value<>(new Color(0xff80ff), PlaceType.HOME_IMPROVEMENTS_SHELVING_STANDARD),
					new Value<>(new Color(0xff8000), PlaceType.HOME_IMPROVEMENTS_BUILDING_SUPPLIES),
					new Value<>(new Color(0xff0080), PlaceType.HOME_IMPROVEMENTS_OFFICE),
					new Value<>(new Color(0x00ffff), PlaceType.HOME_IMPROVEMENTS_TOILETS))) {
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "This isn't a suitable place in which to be having sex!";
		}
	};

	
	public static AbstractWorldType DOMINION_EXPRESS = new AbstractWorldType("Dominion Express",
			PresetColour.BASE_BROWN,
			1,
			false,
			false,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/dominion/dominionExpress/dominionExpress.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.HOME_IMPROVEMENTS_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.DOMINION_EXPRESS_CORRIDOR),
					new Value<>(new Color(0xff0000), PlaceType.DOMINION_EXPRESS_EXIT),
					new Value<>(new Color(0xff00ff), PlaceType.DOMINION_EXPRESS_STORAGE),
					new Value<>(new Color(0x00ffff), PlaceType.DOMINION_EXPRESS_OFFICE),
					new Value<>(new Color(0x0000ff), PlaceType.DOMINION_EXPRESS_FILLY_STATION),
					new Value<>(new Color(0xffff00), PlaceType.DOMINION_EXPRESS_OFFICE_STABLE),
					new Value<>(new Color(0xff8000), PlaceType.DOMINION_EXPRESS_STABLES))) {
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "This isn't a suitable place in which to be having sex!";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	
	public static AbstractWorldType ANGELS_KISS_GROUND_FLOOR = new AbstractWorldType("Angel's Kiss GF",
			PresetColour.BASE_MAGENTA,
			1,
			false,
			false,
			TeleportPermissions.BOTH,
			"/com/lilithsthrone/res/map/dominion/angelsKiss/angelsKissGroundFloor.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.ANGELS_KISS_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.ANGELS_KISS_CORRIDOR),
					new Value<>(new Color(0x00ff00), PlaceType.ANGELS_KISS_ENTRANCE),
					new Value<>(new Color(0xff0000), PlaceType.ANGELS_KISS_STAIRCASE_UP),
					new Value<>(new Color(0x00ffff), PlaceType.ANGELS_KISS_OFFICE),
					new Value<>(new Color(0xff00ff), PlaceType.ANGELS_KISS_BEDROOM))) {
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	public static AbstractWorldType ANGELS_KISS_FIRST_FLOOR = new AbstractWorldType("Angel's Kiss 1F",
			PresetColour.BASE_MAGENTA,
			1,
			false,
			false,
			TeleportPermissions.BOTH,
			"/com/lilithsthrone/res/map/dominion/angelsKiss/angelsKissFirstFloor.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.ANGELS_KISS_STAIRCASE_DOWN, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.ANGELS_KISS_CORRIDOR),
					new Value<>(new Color(0xff0000), PlaceType.ANGELS_KISS_STAIRCASE_DOWN),
					new Value<>(new Color(0xff00ff), PlaceType.ANGELS_KISS_BEDROOM),
					new Value<>(new Color(0xffff00), PlaceType.ANGELS_KISS_BEDROOM_BUNNY),
					new Value<>(new Color(0xff8000), PlaceType.ANGELS_KISS_BEDROOM_LOPPY))) {
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	public static AbstractWorldType NIGHTLIFE_CLUB = new AbstractWorldType("The Watering Hole",
			PresetColour.BASE_BLUE,
			1,
			false,
			false,
			TeleportPermissions.NONE,
			"/com/lilithsthrone/res/map/dominion/nightLife/wateringHole.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.WATERING_HOLE_ENTRANCE, Util.newHashMapOfValues(
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
	};
	
	public static AbstractWorldType DADDYS_APARTMENT = new AbstractWorldType("Daddy's apartment",
			PresetColour.RACE_DEMON,
			1,
			false,
			false,
			TeleportPermissions.NONE,
			"/com/lilithsthrone/res/map/dominion/daddy/apartment.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.DOMINION_DEMON_HOME_DADDY, Util.newHashMapOfValues(
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
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "You can't have sex while in [daddy.namePos] apartment!";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	public static AbstractWorldType HELENAS_APARTMENT = new AbstractWorldType("Helena's apartment",
			PresetColour.BASE_GOLD,
			1,
			false,
			false,
			TeleportPermissions.NONE,
			"/com/lilithsthrone/res/map/dominion/helenaApartment/apartment.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.DOMINION_HELENA_HOTEL, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					
					new Value<>(new Color(0x808080), PlaceType.HELENA_APARTMENT_HALLWAY),
					new Value<>(new Color(0x00ffff), PlaceType.HELENA_APARTMENT_BALCONY),
					
					new Value<>(new Color(0x00ff00), PlaceType.HELENA_APARTMENT_ENTRANCE),
					new Value<>(new Color(0xff00ff), PlaceType.HELENA_APARTMENT_HELENA_BEDROOM),
					new Value<>(new Color(0xff0080), PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM),
					new Value<>(new Color(0xff80ff), PlaceType.HELENA_APARTMENT_BEDROOM),
					new Value<>(new Color(0x0000ff), PlaceType.HELENA_APARTMENT_BATHROOM),
					new Value<>(new Color(0x8080ff), PlaceType.HELENA_APARTMENT_OFFICE),
					new Value<>(new Color(0xff8000), PlaceType.HELENA_APARTMENT_KITCHEN),
					new Value<>(new Color(0x008000), PlaceType.HELENA_APARTMENT_DINING_ROOM),
					new Value<>(new Color(0xffff00), PlaceType.HELENA_APARTMENT_LOUNGE),
					new Value<>(new Color(0x8000ff), PlaceType.HELENA_APARTMENT_HOT_TUB)
					)){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "You can't have sex while in Helena's apartment!";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	// Other:

	public static AbstractWorldType SUBMISSION = new AbstractWorldType("Submission",
			PresetColour.BASE_GREEN,
			1,
			true,
			true,
			TeleportPermissions.BOTH,
			"/com/lilithsthrone/res/map/submission/submission.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.SUBMISSION_ENTRANCE, Util.newHashMapOfValues(
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
					new Value<>(new Color(0x65b0c9), PlaceType.SUBMISSION_IMP_TUNNELS_MALES))) {
	};


	public static AbstractWorldType LYSSIETH_PALACE = new AbstractWorldType("Lyssieth's Palace",
			PresetColour.BASE_PURPLE,
			1,
			false,
			false,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/submission/lyssiethsPalace/groundFloor.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.LYSSIETH_PALACE_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.LYSSIETH_PALACE_CORRIDOR),
					new Value<>(new Color(0x404040), PlaceType.LYSSIETH_PALACE_WINDOWS),

					new Value<>(new Color(0x00ff00), PlaceType.LYSSIETH_PALACE_ENTRANCE),
					new Value<>(new Color(0xff80ff), PlaceType.LYSSIETH_PALACE_ROOM),
					new Value<>(new Color(0xff8000), PlaceType.LYSSIETH_PALACE_HALL),
					new Value<>(new Color(0x8000ff), PlaceType.LYSSIETH_PALACE_OFFICE),
					new Value<>(new Color(0xff0080), PlaceType.LYSSIETH_PALACE_SIREN_OFFICE),
					
					new Value<>(new Color(0xff0000), PlaceType.LYSSIETH_PALACE_STAIRS_1),
					new Value<>(new Color(0x0000ff), PlaceType.LYSSIETH_PALACE_STAIRS_2))) {
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "You can't have sex while in Lyssieth's Palace!";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	public static AbstractWorldType IMP_FORTRESS_ALPHA = new AbstractWorldType("Imp Fortress A",
			PresetColour.BASE_CRIMSON,
			1,
			false,
			true,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/submission/impFortress/fortress1Map.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.FORTRESS_ALPHA_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.FORTRESS_ALPHA_COURTYARD),
					new Value<>(new Color(0x00ff00), PlaceType.FORTRESS_ALPHA_ENTRANCE),
					new Value<>(new Color(0xff8000), PlaceType.FORTRESS_ALPHA_KEEP))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "This isn't a suitable place in which to be having sex!";
		}
	};

	public static AbstractWorldType IMP_FORTRESS_DEMON = new AbstractWorldType("Imp Citadel",
			PresetColour.BASE_PURPLE,
			1,
			false,
			true,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/submission/impFortress/fortress2Map.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.FORTRESS_DEMON_ENTRANCE, Util.newHashMapOfValues(
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
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "This isn't a suitable place in which to be having sex!";
		}
	};

	public static AbstractWorldType IMP_FORTRESS_FEMALES = new AbstractWorldType("Imp Fortress F",
			PresetColour.BASE_PINK,
			1,
			false,
			true,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/submission/impFortress/fortress3Map.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.FORTRESS_FEMALES_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.FORTRESS_FEMALES_COURTYARD),
					new Value<>(new Color(0x00ff00), PlaceType.FORTRESS_FEMALES_ENTRANCE),
					new Value<>(new Color(0xff8000), PlaceType.FORTRESS_FEMALES_KEEP))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "This isn't a suitable place in which to be having sex!";
		}
	};

	public static AbstractWorldType IMP_FORTRESS_MALES = new AbstractWorldType("Imp Fortress M",
			PresetColour.BASE_BLUE,
			1,
			false,
			true,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/submission/impFortress/fortress4Map.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.FORTRESS_MALES_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.FORTRESS_MALES_COURTYARD),
					new Value<>(new Color(0x00ff00), PlaceType.FORTRESS_MALES_ENTRANCE),
					new Value<>(new Color(0xff8000), PlaceType.FORTRESS_MALES_KEEP))){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "This isn't a suitable place in which to be having sex!";
		}
	};

	public static AbstractWorldType BAT_CAVERNS = new AbstractWorldType("Bat Caverns",
			PresetColour.BASE_BLACK,
			1,
			true,
			true,
			TeleportPermissions.BOTH,
			"/com/lilithsthrone/res/map/submission/batCaverns/batCaverns.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.BAT_CAVERN_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),

					new Value<>(new Color(0x00ff00), PlaceType.BAT_CAVERN_ENTRANCE),
					
					new Value<>(new Color(0x008080), PlaceType.BAT_CAVERN_DARK),
					new Value<>(new Color(0x808080), PlaceType.BAT_CAVERN_LIGHT),
					
					new Value<>(new Color(0x0080ff), PlaceType.BAT_CAVERN_RIVER),
					new Value<>(new Color(0x40b4ff), PlaceType.BAT_CAVERN_RIVER_CROSSING),
					new Value<>(new Color(0x004080), PlaceType.BAT_CAVERN_RIVER_END),
					
					new Value<>(new Color(0xff80ff), PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR))) {
	};

	public static AbstractWorldType SLIME_QUEENS_LAIR_GROUND_FLOOR = new AbstractWorldType("Slime Queen's Tower GF",
			PresetColour.BASE_PINK,
			1,
			false,
			false,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/submission/slimeQueensLair/slimeQueensLairGroundFloor.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.SLIME_QUEENS_LAIR_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),

					new Value<>(new Color(0x808080), PlaceType.SLIME_QUEENS_LAIR_CORRIDOR),
					
					new Value<>(new Color(0x00ff00), PlaceType.SLIME_QUEENS_LAIR_ENTRANCE),
					new Value<>(new Color(0xff0000), PlaceType.SLIME_QUEENS_LAIR_STAIRS_UP),
					
					new Value<>(new Color(0xff8000), PlaceType.SLIME_QUEENS_LAIR_STORAGE_VATS),
					new Value<>(new Color(0x40b4ff), PlaceType.SLIME_QUEENS_LAIR_ROOM),
					
					new Value<>(new Color(0xff80ff), PlaceType.SLIME_QUEENS_LAIR_ENTRANCE_GUARDS),
					
					new Value<>(new Color(0xffff00), PlaceType.SLIME_QUEENS_LAIR_SLIME_QUEEN))) {
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "This isn't a suitable place in which to be having sex!";
		}
	};

	public static AbstractWorldType SLIME_QUEENS_LAIR_FIRST_FLOOR = new AbstractWorldType("Slime Queen's Tower 1F",
			PresetColour.BASE_PINK,
			1,
			false,
			false,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/submission/slimeQueensLair/slimeQueensLairFirstFloor.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.SLIME_QUEENS_LAIR_STAIRS_DOWN, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),

					new Value<>(new Color(0x808080), PlaceType.SLIME_QUEENS_LAIR_CORRIDOR),
					
					new Value<>(new Color(0x00ff00), PlaceType.SLIME_QUEENS_LAIR_STAIRS_DOWN),
					
					new Value<>(new Color(0x40b4ff), PlaceType.SLIME_QUEENS_LAIR_ROOM),
					
					new Value<>(new Color(0xff00ff), PlaceType.SLIME_QUEENS_LAIR_ROYAL_GUARD),
					new Value<>(new Color(0xffff00), PlaceType.SLIME_QUEENS_LAIR_SLIME_QUEEN))) {
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "This isn't a suitable place in which to be having sex!";
		}
	};

	public static AbstractWorldType GAMBLING_DEN = new AbstractWorldType("Gambling Den",
			PresetColour.BASE_GOLD,
			1,
			false,
			false,
			TeleportPermissions.OUTGOING_ONLY,
			"/com/lilithsthrone/res/map/submission/gamblingDen/gamblingDen.png", PlaceType.WORLD_MAP_DOMINION, PlaceType.GAMBLING_DEN_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.GAMBLING_DEN_CORRIDOR),
					
					new Value<>(new Color(0x00ff00), PlaceType.GAMBLING_DEN_ENTRANCE),
					
					new Value<>(new Color(0x4bb1d0), PlaceType.GAMBLING_DEN_OFFICE),
					
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
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "This isn't a suitable place in which to be having sex!";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	public static AbstractWorldType RAT_WARRENS = new AbstractWorldType("Rat Warrens",
			PresetColour.BASE_BROWN,
			1,
			false,
			false,
			TeleportPermissions.NONE,
			"/com/lilithsthrone/res/map/submission/ratWarrens/ratWarrens.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.RAT_WARRENS_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.RAT_WARRENS_CORRIDOR_LEFT),
					new Value<>(new Color(0xb9b9b9), PlaceType.RAT_WARRENS_CORRIDOR),
					new Value<>(new Color(0x3b3b3b), PlaceType.RAT_WARRENS_CORRIDOR_RIGHT),
					new Value<>(new Color(0x00ff00), PlaceType.RAT_WARRENS_ENTRANCE),

					new Value<>(new Color(0x00ffff), PlaceType.RAT_WARRENS_CHECKPOINT_LEFT),
					new Value<>(new Color(0x80ffff), PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT),
					
					new Value<>(new Color(0xff8080), PlaceType.RAT_WARRENS_DORMITORY_LEFT),
					new Value<>(new Color(0xff8000), PlaceType.RAT_WARRENS_DORMITORY_RIGHT),
					
					new Value<>(new Color(0x0000ff), PlaceType.RAT_WARRENS_DICE_DEN),
					new Value<>(new Color(0xffff00), PlaceType.RAT_WARRENS_MILKING_ROOM),
					new Value<>(new Color(0xffbf00), PlaceType.RAT_WARRENS_MILKING_STORAGE),
					new Value<>(new Color(0x8000ff), PlaceType.RAT_WARRENS_VENGARS_HALL),
					new Value<>(new Color(0x800080), PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS))) {
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "This isn't a suitable place in which to be having sex!";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
        public static AbstractWorldType REBEL_BASE = new AbstractWorldType("Mysterious Artificial Cave",
			PresetColour.BASE_GREY,
			1,
			false,
			false,
			TeleportPermissions.BOTH,
			"/com/lilithsthrone/res/map/submission/rebelBase/rebelBase.png",
			PlaceType.WORLD_MAP_DOMINION,
			PlaceType.REBEL_BASE_ENTRANCE, Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0xed1c24), PlaceType.REBEL_BASE_ENTRANCE),
					new Value<>(new Color(0x22b14c), PlaceType.REBEL_BASE_CORRIDOR),
					new Value<>(new Color(0xf8941d), PlaceType.REBEL_BASE_SLEEPING_AREA),
					new Value<>(new Color(0x662d91), PlaceType.REBEL_BASE_COMMON_AREA),
                                        new Value<>(new Color(0x6dd0f7), PlaceType.REBEL_BASE_ARMORY),
                                        new Value<>(new Color(0x3f48cc), PlaceType.REBEL_BASE_CAVED_IN_ROOM))) {
		@Override
		public String getSexBlockedReason(GameCharacter character) {
			return "A structurally superfluous cave is hardly the place for sex!";
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	};
	
	private static List<AbstractWorldType> allWorldTypes = new ArrayList<>();
	private static Map<AbstractWorldType, String> worldToIdMap = new HashMap<>();
	private static Map<String, AbstractWorldType> idToWorldMap = new HashMap<>();

	public static List<AbstractWorldType> getAllWorldTypes() {
		return allWorldTypes;
	}
	
	public static AbstractWorldType getWorldTypeFromId(String id) {
		id.replaceAll("SEWERS", "SUBMISSION");
		id = Util.getClosestStringMatch(id, idToWorldMap.keySet());
		return idToWorldMap.get(id);
	}

	public static String getIdFromWorldType(AbstractWorldType placeType) {
		return worldToIdMap.get(placeType);
	}
	
	static {
		Field[] fields = WorldType.class.getFields();
		
		for(Field f : fields) {
			if(AbstractWorldType.class.isAssignableFrom(f.getType())) {
				AbstractWorldType worldType;
				try {
					worldType = ((AbstractWorldType) f.get(null));

					worldToIdMap.put(worldType, f.getName());
					idToWorldMap.put(f.getName(), worldType);
					allWorldTypes.add(worldType);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
