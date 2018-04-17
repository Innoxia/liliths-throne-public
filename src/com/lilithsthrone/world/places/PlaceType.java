package com.lilithsthrone.world.places;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.places.JunglePlaces;
import com.lilithsthrone.game.dialogue.places.dominion.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.CityPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.DemonHome;
import com.lilithsthrone.game.dialogue.places.dominion.EnforcerHQDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.LilithsTower;
import com.lilithsthrone.game.dialogue.places.dominion.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.RedLightDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestAlexa;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestBimbo;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestDominant;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestNympho;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestsDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Library;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ArcaneArts;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ClothingEmporium;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.DreamLover;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.PixsPlayground;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.RalphsSnacks;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ShoppingArcadeDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SupplierDepot;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeFirstFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeFirstFloorRepeat;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.places.submission.BatCaverns;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Bearing;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.world.EntranceType;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.0
 * @version 0.1.99
 * @author Innoxia
 */
public enum PlaceType {
	
	
	GENERIC_IMPASSABLE(null, null, null, Colour.MAP_BACKGROUND, null, null, false, false, false, true, ""),
	
	GENERIC_EMPTY_TILE("Empty", "dominion/slaverAlleyIcon",  BaseColour.CRIMSON, Colour.MAP_BACKGROUND, null, null, true, false, false, true, ""),
	
	GENERIC_MUSEUM("Museum", "dominion/slaverAlleyIcon",  BaseColour.TAN, Colour.MAP_BACKGROUND, null, null, true, false, true, false, "in Lily's Museum"),
	
	DOMINION_PLAZA("Lilith's Plaza", "dominion/statue",  BaseColour.PINK_DEEP, Colour.MAP_BACKGROUND_PINK, CityPlaces.DOMINION_PLAZA, null, true, false, false, true, "in Dominion's central plaza"),
	
	DOMINION_STREET("Dominion Streets", null, null, Colour.MAP_BACKGROUND, CityPlaces.STREET, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_BOULEVARD("Dominion Boulevard", null, null, Colour.MAP_BACKGROUND_PINK, CityPlaces.BOULEVARD, null, true, false, false, true, "in the streets of Dominion"),
	
	DOMINION_LILITHS_TOWER("Lilith's Tower", "dominion/lilithsTowerIcon", BaseColour.PURPLE, Colour.MAP_BACKGROUND_PINK, LilithsTower.OUTSIDE, null, true, false, false, true, "in the streets of Dominion"),
	
	DOMINION_ENFORCER_HQ("Enforcer HQ", "dominion/enforcerHQIcon", BaseColour.BLUE, Colour.MAP_BACKGROUND, EnforcerHQDialogue.EXTERIOR, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_DEMON_HOME_GATE("Demon Home Gates", "dominion/gate", BaseColour.PINK_LIGHT, Colour.MAP_BACKGROUND_PINK, DemonHome.DEMON_HOME_GATE, null, true, false, false, true, "in the streets of Demon Home"),
	
	DOMINION_DEMON_HOME("Demon Home", null, null, Colour.MAP_BACKGROUND_PINK, DemonHome.DEMON_HOME_STREET, null, true, false, false, true, "in the streets of Demon Home"),
	
	DOMINION_DEMON_HOME_ARTHUR("Demon Home", "dominion/demonHomeIcon", BaseColour.PINK, Colour.MAP_BACKGROUND_PINK, DemonHome.DEMON_HOME_STREET_ARTHUR, null, true, false, false, true, "in the streets of Demon Home"),
	
	DOMINION_SHOPPING_ARCADE("Shopping Arcade", "dominion/shoppingArcadeIcon", BaseColour.GOLD, Colour.MAP_BACKGROUND, ShoppingArcadeDialogue.OUTSIDE, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_STREET_HARPY_NESTS("Harpy Nests", null, null, Colour.MAP_BACKGROUND_DARK, CityPlaces.STREET_SHADED, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_HARPY_NESTS_ENTRANCE("Harpy Nests", "dominion/harpyNestIcon", BaseColour.MAGENTA, Colour.MAP_BACKGROUND, HarpyNestsDialogue.OUTSIDE, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_NIGHTLIFE_DISTRICT("Nightlife District", "dominion/nightlifeIcon", BaseColour.PINK_LIGHT, Colour.MAP_BACKGROUND, NightlifeDistrict.OUTSIDE, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_CITY_HALL("City Hall", "dominion/townHallIcon",  BaseColour.LILAC, Colour.MAP_BACKGROUND, CityHall.OUTSIDE, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_AUNTS_HOME("Lilaya's Home", "dominion/homeIcon", BaseColour.BLUE_LIGHT, Colour.MAP_BACKGROUND, LilayaHomeGeneric.OUTSIDE, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_SLAVER_ALLEY("Slaver Alley", "dominion/slaverAlleyIcon",  BaseColour.CRIMSON, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.OUTSIDE, null, true, false, false, true, "in the alleyways near Slaver's Alley") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},

	DOMINION_RED_LIGHT_DISTRICT("Red Light District", "dominion/brothel", BaseColour.MAGENTA, Colour.MAP_BACKGROUND_DARK, RedLightDistrict.OUTSIDE, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},

	DOMINION_PARK("Park", "dominion/park", BaseColour.GREEN, Colour.MAP_BACKGROUND, CityPlaces.PARK, Encounter.DOMINION_STREET, true, false, false, true, "in one of Dominion's parks") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	// Alleyways:
	
	DOMINION_BACK_ALLEYS("Dominion Alleyways", "dominion/alleysIcon",  BaseColour.BLACK, Colour.MAP_BACKGROUND, CityPlaces.BACK_ALLEYS, Encounter.DOMINION_ALLEY, false, true, false, true, "in one of Dominion's backalleys"),

	DOMINION_DARK_ALLEYS("Dark Alleyways", "dominion/alleysDarkIcon",  BaseColour.PURPLE, Colour.MAP_BACKGROUND, CityPlaces.DARK_ALLEYS, Encounter.DOMINION_DARK_ALLEY, false, true, false, true, "in one of Dominion's dark alleyways"),
	
	DOMINION_ALLEYS_CANAL_CROSSING("Canal Crossing", "dominion/bridge",  BaseColour.BLUE_LIGHT, Colour.MAP_BACKGROUND, CityPlaces.BACK_ALLEYS_CANAL, Encounter.DOMINION_ALLEY, false, true, false, true, "in one of Dominion's backalleys"),
	
	// Canals:
	
	DOMINION_CANAL("Dominion Canal", "dominion/canalIcon",  BaseColour.BLUE_LIGHT, Colour.MAP_BACKGROUND, CityPlaces.CANAL, Encounter.DOMINION_CANAL, false, true, false, true, "beside one of Dominion's canals"),
	
	DOMINION_CANAL_END("Dominion Canal", "dominion/canalEndIcon",  BaseColour.BLUE, Colour.MAP_BACKGROUND, CityPlaces.CANAL_END, Encounter.DOMINION_CANAL, false, true, false, true, "beside one of Dominion's canals"),
	
	// Exits & entrances:
	
	DOMINION_EXIT_TO_SUBMISSION("Submission Entrance", "dominion/submissionExit",  BaseColour.TEAL, Colour.MAP_BACKGROUND, CityPlaces.CITY_EXIT_SEWERS, null, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		
		@Override
		public Bearing getBearing() {
			return Bearing.RANDOM;
		}
	},
	
	DOMINION_EXIT_TO_JUNGLE("Jungle Entrance", "dominion/JungleExit",  BaseColour.GREEN_LIME, Colour.MAP_BACKGROUND_PINK, CityPlaces.CITY_EXIT_JUNGLE, null, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public Bearing getBearing() {
			return Bearing.NORTH;
		}
	},
	
	DOMINION_EXIT_TO_FIELDS("Fields Entrance", "dominion/fieldsExit",  BaseColour.GREEN_LIGHT, Colour.MAP_BACKGROUND_PINK, CityPlaces.CITY_EXIT_FIELDS, null, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public Bearing getBearing() {
			return Bearing.WEST;
		}
	},
	
	DOMINION_EXIT_TO_SEA("Endless Sea Entrance", "dominion/endlessSeaExit",  BaseColour.TEAL, Colour.MAP_BACKGROUND_PINK, CityPlaces.CITY_EXIT_SEA, null, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public Bearing getBearing() {
			return Bearing.EAST;
		}
	},
	
	DOMINION_EXIT_TO_DESERT("Desert Entrance", "dominion/desertExit", BaseColour.YELLOW, Colour.MAP_BACKGROUND_PINK, CityPlaces.CITY_EXIT_DESERT, null, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public Bearing getBearing() {
			return Bearing.SOUTH;
		}
	},
	
	
	
	
	ENFORCER_HQ_CORRIDOR("Corridor", null, BaseColour.BLACK, Colour.MAP_BACKGROUND, EnforcerHQDialogue.CORRIDOR, null, true, false, true, true, "in the Enforcer HQ"),

	ENFORCER_HQ_WAITING_AREA("Waiting area", "dominion/enforcerHQ/waitingRoom", BaseColour.BROWN, Colour.MAP_BACKGROUND, EnforcerHQDialogue.WAITING_AREA, null, true, false, true, true, "in the Enforcer HQ"),
	
	ENFORCER_HQ_RECEPTION_DESK("Reception desk", "dominion/enforcerHQ/receptionDesk", BaseColour.BLUE_LIGHT, Colour.MAP_BACKGROUND, EnforcerHQDialogue.RECEPTION_DESK, null, true, false, true, true, "in Candi's office"),
	
	ENFORCER_HQ_GUARDED_DOOR("Guarded door", "dominion/enforcerHQ/guardedDoor", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, EnforcerHQDialogue.GUARDED_DOOR, null, true, false, true, true, "in the Enforcer HQ"),
	
	ENFORCER_HQ_BRAXS_OFFICE("Brax's Office", "dominion/enforcerHQ/braxsOffice", BaseColour.BLUE_STEEL, Colour.MAP_BACKGROUND, EnforcerHQDialogue.INTERIOR_BRAX, null, true, false, true, true, "in his office") {
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.braxEncountered)) {
				return EnforcerHQDialogue.INTERIOR_BRAX_REPEAT;
				
			} else {
				return EnforcerHQDialogue.INTERIOR_BRAX;
			}
		}
	},

	ENFORCER_HQ_ENTRANCE("Entranceway", "dominion/enforcerHQ/exit", BaseColour.RED, Colour.MAP_BACKGROUND, EnforcerHQDialogue.ENTRANCE, null, true, false, true, true, ""),
	
	
	
	
	// Standard tiles:
	HARPY_NESTS_WALKWAYS("Walkway", null, BaseColour.BLACK, Colour.MAP_BACKGROUND, HarpyNestsDialogue.WALKWAY, Encounter.HARPY_NEST_WALKWAYS, false, true, false, true, "in the Harpy Nests") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
	},
	
	HARPY_NESTS_WALKWAYS_BRIDGE("Walkway Bridge", "dominion/harpyNests/bridge", BaseColour.GREY, Colour.MAP_BACKGROUND, HarpyNestsDialogue.WALKWAY_BRIDGE, Encounter.HARPY_NEST_WALKWAYS, false, true, false, true, "in the Harpy Nests") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
	},

	// Places:
	HARPY_NESTS_ENTRANCE_ENFORCER_POST("Enforcer post", "dominion/harpyNests/exit", BaseColour.RED, Colour.MAP_BACKGROUND, HarpyNestsDialogue.ENTRANCE_ENFORCER_POST, null, true, false, true, true, "in the Harpy Nests"),
	
	HARPY_NESTS_ALEXAS_NEST("Alexa's nest", "dominion/harpyNests/nestAlexa", BaseColour.GOLD, Colour.MAP_BACKGROUND, HarpyNestAlexa.ALEXAS_NEST_EXTERIOR, null, true, false, false, true, "in Alexa's nest"),
	
	HARPY_NESTS_HARPY_NEST_RED("Harpy nest", "dominion/harpyNests/nestRed", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, HarpyNestDominant.HARPY_NEST_DOMINANT, null, true, false, false, true, "in Diana's nest"),
	
	HARPY_NESTS_HARPY_NEST_PINK("Harpy nest", "dominion/harpyNests/nestPink", BaseColour.PINK_LIGHT, Colour.MAP_BACKGROUND, HarpyNestNympho.HARPY_NEST_NYMPHO, null, true, false, false, true, "in Lexi's nest"),
	
	HARPY_NESTS_HARPY_NEST_YELLOW("Harpy nest", "dominion/harpyNests/nestYellow", BaseColour.YELLOW_LIGHT, Colour.MAP_BACKGROUND, HarpyNestBimbo.HARPY_NEST_BIMBO, null, true, false, false, true, "in Brittany's nest"),
		
		
	
	
	
	// Standard tiles:
	JUNGLE_PATH("Jungle Path", null, BaseColour.GREEN, Colour.MAP_BACKGROUND, JunglePlaces.PATH, null, false, false, false, true, "in the jungle"),
	
	JUNGLE_DENSE_JUNGLE("Dense Jungle", null, BaseColour.GREEN, Colour.MAP_BACKGROUND, JunglePlaces.DENSE_JUNGLE, null, false, true, false, true, "in the jungle"),

	// Safe places:
	JUNGLE_CLUB("Club", null, BaseColour.GREEN, Colour.MAP_BACKGROUND, JunglePlaces.CLUB, null, true, false, false, true, "in the jungle"),
	
	JUNGLE_BROTHEL("Brothel", null, BaseColour.GREEN, Colour.MAP_BACKGROUND, JunglePlaces.BROTHEL, null, true, false, false, true, "in the jungle"),

	// Dangerous places:
	JUNGLE_TENTACLE_QUEENS_LAIR("Tentacle Queen's Lair", null, BaseColour.GREEN, Colour.MAP_BACKGROUND, JunglePlaces.TENTACLE_QUEENS_LAIR, null, true, false, false, true, "in the jungle"),

	// Exits & entrances:
	JUNGLE_ENTRANCE("Jungle Entrance", null, BaseColour.GREEN, Colour.MAP_BACKGROUND, JunglePlaces.JUNGLE_ENTRANCE, null, true, false, false, true, "in the jungle"){
//		@Override
//		public WorldType getParentWorldType() {
//			return WorldType.DOMINION;
//		}
//		@Override
//		public PlaceType getParentPlaceType() {
//			return PlaceType.DOMINION_EXIT_TO_JUNGLE;
//		}
//		@Override
//		public EntranceType getParentAlignment() {
//			return EntranceType.ALIGNED_FLIP_VERTICAL;
//		}
	},
	
	
	
	// Ground floor:
	
	LILAYA_HOME_CORRIDOR("Corridor", null, BaseColour.GREY, Colour.MAP_BACKGROUND, LilayaHomeGeneric.CORRIDOR, Encounter.LILAYAS_HOME_CORRIDOR, true, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR("Room", "dominion/lilayasHome/room", BaseColour.GREY, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_WINDOW, null, true, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.LILAYA_EMPTY_ROOM));
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.getCoreRoomUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " G-"+String.format("%02d", count);
		}
	},
	
	LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR("Garden Room", "dominion/lilayasHome/room", BaseColour.GREY, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR, null, true, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.LILAYA_EMPTY_ROOM));
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.getCoreRoomUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " GG-"+String.format("%02d", count);
		}
	},
	
	LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR("Room", "dominion/lilayasHome/room", BaseColour.GREY, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_WINDOW, null, true, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.LILAYA_EMPTY_ROOM));
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.getCoreRoomUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " F-"+String.format("%02d", count);
		}
	},
	
	LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR("Garden Room", "dominion/lilayasHome/room", BaseColour.GREY, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_GARDEN, null, true, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.LILAYA_EMPTY_ROOM));
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.getCoreRoomUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " FG-"+String.format("%02d", count);
		}
	},
	
	LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_SLAVE("Slave's Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_WINDOW_SLAVE, null, true, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.getSlaveQuartersUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE)) {
				return PlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveDouble", Colour.BASE_MAGENTA);
			} else {
				return SVGString;
			}
		}
	},
	
	LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE("Slave's Garden Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR_SLAVE, null, true, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.getSlaveQuartersUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE)) {
				return PlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveDouble", Colour.BASE_MAGENTA);
			} else {
				return SVGString;
			}
		}
	},
	
	LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_SLAVE("Slave's Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_WINDOW_SLAVE, null, true, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.getSlaveQuartersUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE)) {
				return PlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveDouble", Colour.BASE_MAGENTA);
			} else {
				return SVGString;
			}
		}
	},
	
	LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_SLAVE("Slave's Garden Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_GARDEN_SLAVE, null, true, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.getSlaveQuartersUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE)) {
				return PlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveDouble", Colour.BASE_MAGENTA);
			} else {
				return SVGString;
			}
		}
	},
	
	LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_MILKING("Slave's Room", "dominion/lilayasHome/roomMilking", BaseColour.ORANGE, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_WINDOW_MILKING, null, true, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.getMilkingUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
	},
	
	LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_MILKING("Slave's Garden Room", "dominion/lilayasHome/roomMilking", BaseColour.ORANGE, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR_MILKING, null, true, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.getMilkingUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
	},
	
	LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_MILKING("Slave's Room", "dominion/lilayasHome/roomMilking", BaseColour.ORANGE, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_WINDOW_MILKING, null, true, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.getMilkingUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
	},
	
	LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_MILKING("Slave's Garden Room", "dominion/lilayasHome/roomMilking", BaseColour.ORANGE, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_GARDEN_MILKING, null, true, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.getMilkingUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
	},
	
	LILAYA_HOME_ARTHUR_ROOM("Arthur's Room", "dominion/lilayasHome/roomArthur", BaseColour.BLUE_STEEL, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_ARTHUR, null, true, false, true, false, "in Arthur's Room"),
	
	LILAYA_HOME_BIRTHING_ROOM("Room", "dominion/lilayasHome/roomBirthing", BaseColour.PINK, Colour.MAP_BACKGROUND, LilayaHomeGeneric.BIRTHING_ROOM, null, true, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_KITCHEN("Kitchen", "dominion/lilayasHome/kitchen", BaseColour.TAN, Colour.MAP_BACKGROUND, LilayaHomeGeneric.KITCHEN, null, true, false, true, false, "in Lilaya's kitchen"),
	
	LILAYA_HOME_LIBRARY("Library", "dominion/lilayasHome/library", BaseColour.TEAL, Colour.MAP_BACKGROUND, Library.LIBRARY, null, true, false, true, false, "in Lilaya's library"),
	
	LILAYA_HOME_STAIR_UP("Staircase", "dominion/lilayasHome/stairsUp", BaseColour.GREEN_LIGHT, Colour.MAP_BACKGROUND, LilayaHomeGeneric.STAIRCASE_UP, null, true, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ENTRANCE_HALL("Entrance Hall", "dominion/lilayasHome/entranceHall", BaseColour.RED, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ENTRANCE_HALL, null, true, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_LAB("Lilaya's Lab", "dominion/lilayasHome/lab", BaseColour.ORANGE, Colour.MAP_BACKGROUND, Lab.LAB, null, true, false, true, false, "in Lilaya's lab") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			inventory.addClothing(AbstractClothingType.generateClothing(ClothingType.SCIENTIST_EYES_SAFETY_GOGGLES, Colour.CLOTHING_BLACK, false));
		}
	},
	
	LILAYA_HOME_GARDEN("Garden", "dominion/lilayasHome/garden", BaseColour.GREEN, Colour.MAP_BACKGROUND, LilayaHomeGeneric.GARDEN, null, true, false, false, false, "in Lilaya's garden"),
	
	LILAYA_HOME_FOUNTAIN("Fountain", "dominion/lilayasHome/fountain", BaseColour.BLUE_LIGHT, Colour.MAP_BACKGROUND, LilayaHomeGeneric.FOUNTAIN, null, true, false, false, false, "in Lilaya's garden"),
	

	// First floor:

	LILAYA_HOME_ROOM_LILAYA("Lilaya's Room", "dominion/lilayasHome/roomLilaya", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_LILAYA, null, true, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ROOM_ROSE("Rose's Room", "dominion/lilayasHome/roomRose", BaseColour.PINK, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_ROSE, null, true, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ROOM_PLAYER("Your Room", "dominion/lilayasHome/roomPlayer", BaseColour.AQUA, Colour.MAP_BACKGROUND, RoomPlayer.ROOM, null, true, false, true, false, "in your room"),
	
	LILAYA_HOME_STAIR_DOWN("Staircase", "dominion/lilayasHome/stairsDown", BaseColour.RED, Colour.MAP_BACKGROUND, LilayaHomeGeneric.STAIRCASE_DOWN, null, true, false, true, false, "in Lilaya's Home"),
	
	

	// Zaranix:
	// Ground floor:
	
	ZARANIX_GF_CORRIDOR("Corridor", null, BaseColour.GREY, Colour.MAP_BACKGROUND, ZaranixHomeGroundFloor.CORRIDOR, null, false, false, true, false, "in Zaranix's home"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeGroundFloor.CORRIDOR;
			}
		}
	},
	
	ZARANIX_GF_STAIRS("Staircase", "dominion/zaranixHome/stairsDown", BaseColour.GREEN_LIGHT, Colour.MAP_BACKGROUND, ZaranixHomeGroundFloor.STAIRS, null, false, false, true, false, "in Zaranix's home"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.STAIRS;
				
			} else {
				return ZaranixHomeGroundFloor.STAIRS;
			}
		}
	},
	
	ZARANIX_GF_ENTRANCE("Entrance", "dominion/zaranixHome/entranceHall", BaseColour.RED, Colour.MAP_BACKGROUND, ZaranixHomeGroundFloor.ENTRANCE, null, false, false, true, false, "in Zaranix's home"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.ENTRANCE;
				
			} else {
				return ZaranixHomeGroundFloor.ENTRANCE;
			}
		}
	},
	
	ZARANIX_GF_LOUNGE("Lounge", "dominion/zaranixHome/lounge", BaseColour.ORANGE, Colour.MAP_BACKGROUND, ZaranixHomeGroundFloor.LOUNGE, null, false, false, true, false, "in Zaranix's home"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.LOUNGE;
				
			} else {
				return ZaranixHomeGroundFloor.LOUNGE;
			}
		}
	},
	
	ZARANIX_GF_ROOM("Room", "dominion/zaranixHome/room", BaseColour.GREY, Colour.MAP_BACKGROUND, ZaranixHomeGroundFloor.ROOM, null, false, false, true, false, "in a room in Zaranix's home"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.ROOM;
				
			} else {
				return ZaranixHomeGroundFloor.ROOM;
			}
		}
	},
	
	ZARANIX_GF_MAID("Corridor", null, BaseColour.GREY, Colour.MAP_BACKGROUND, ZaranixHomeGroundFloor.CORRIDOR_MAID, null, true, true, true, false, "in Zaranix's Home"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeGroundFloor.CORRIDOR_MAID;
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	},
	
	ZARANIX_GF_GARDEN_ROOM("Room", "dominion/zaranixHome/room", BaseColour.GREY, Colour.MAP_BACKGROUND, ZaranixHomeGroundFloor.GARDEN_ROOM, null, false, false, true, false, "in a room in Zaranix's home"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN_ROOM;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN_ROOM;
			}
		}
	},
	
	ZARANIX_GF_GARDEN("Garden", "dominion/zaranixHome/garden", BaseColour.GREEN, Colour.MAP_BACKGROUND, ZaranixHomeGroundFloor.GARDEN, null, false, false, true, false, "in Zaranix's garden"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN;
			}
		}
	},
	
	ZARANIX_GF_GARDEN_ENTRY("Garden", "dominion/zaranixHome/entranceHall", BaseColour.GREEN, Colour.MAP_BACKGROUND, ZaranixHomeGroundFloor.GARDEN_ENTRY, null, false, false, true, false, "in Zaranix's garden"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN_ENTRY;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN_ENTRY;
			}
		}
	},
	
	// First floor:
	
	ZARANIX_FF_CORRIDOR("Corridor", null, BaseColour.GREY, Colour.MAP_BACKGROUND, ZaranixHomeFirstFloor.CORRIDOR, null, false, false, true, false, "in Zaranix's home"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeFirstFloor.CORRIDOR;
			}
		}
	},
	
	ZARANIX_FF_STAIRS("Staircase", "dominion/zaranixHome/stairsDown", BaseColour.RED, Colour.MAP_BACKGROUND, ZaranixHomeFirstFloor.STAIRS, null, false, false, true, false, "in Zaranix's home"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.STAIRS;
				
			} else {
				return ZaranixHomeFirstFloor.STAIRS;
			}
		}
	},
	
	ZARANIX_FF_OFFICE("Zaranix's Room", "dominion/zaranixHome/roomZaranix", BaseColour.PINK_DEEP, Colour.MAP_BACKGROUND, ZaranixHomeFirstFloor.ZARANIX_ROOM, null, true, true, true, false, "in Zaranix's home"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.ZARANIX_ROOM;
				
			} else {
				return ZaranixHomeFirstFloor.ZARANIX_ROOM;
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	},
	
	ZARANIX_FF_ROOM("Room", "dominion/zaranixHome/room", BaseColour.GREY, Colour.MAP_BACKGROUND, ZaranixHomeFirstFloor.ROOM, null, false, false, true, false, "in a room in Zaranix's home"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.ROOM;
				
			} else {
				return ZaranixHomeFirstFloor.ROOM;
			}
		}
	},
	
	ZARANIX_FF_MAID("Corridor", null, BaseColour.RED, Colour.MAP_BACKGROUND, ZaranixHomeFirstFloor.CORRIDOR_MAID, null, true, true, true, false, "in Zaranix's Home"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeFirstFloor.CORRIDOR_MAID;
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	},
	
	
	// Angel's Kiss:

	ANGELS_KISS_CORRIDOR("Corridor", null, BaseColour.GREY, Colour.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_CORRIDOR, null, true, false, true, false, "in Angel's Kiss"),
	ANGELS_KISS_ENTRANCE("Entrance Hall", "dominion/angelsKiss/entrance", BaseColour.RED, Colour.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_ENTRANCE, null, true, false, true, false, "in Angel's Kiss"),
	ANGELS_KISS_STAIRCASE_UP("Entrance Hall", "dominion/angelsKiss/stairsUp", BaseColour.GREEN_LIGHT, Colour.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_STAIRS_UP, null, true, false, true, false, "in Angel's Kiss"),
	ANGELS_KISS_STAIRCASE_DOWN("Entrance Hall", "dominion/angelsKiss/stairsDown", BaseColour.RED, Colour.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_STAIRS_DOWN, null, true, false, true, false, "in Angel's Kiss"),
	ANGELS_KISS_BEDROOM("Bedroom", "dominion/angelsKiss/bedroom", BaseColour.PINK, Colour.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_BEDROOM, null, true, false, true, false, "in Angel's Kiss"),
	ANGELS_KISS_BEDROOM_BUNNY("Bunny's Bedroom", "dominion/angelsKiss/bedroomBunny", BaseColour.PINK_LIGHT, Colour.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_BEDROOM_BUNNY, null, true, false, true, false, "in Bunny's Bedroom"),
	ANGELS_KISS_BEDROOM_LOPPY("Loppy's Bedroom", "dominion/angelsKiss/bedroomLoppy", BaseColour.PURPLE, Colour.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_BEDROOM_LOPPY, null, true, false, true, false, "in Loppy's Bedroom"),
	ANGELS_KISS_OFFICE("Angel's Office", "dominion/angelsKiss/office", BaseColour.BLUE_LIGHT, Colour.MAP_BACKGROUND, RedLightDistrict.ANGELS_KISS_OFFICE, null, true, false, true, false, "in Angel's Office"),
	
	
	// Shopping arcade:
	
	SHOPPING_ARCADE_PATH("Arcade", null, BaseColour.BLACK, Colour.MAP_BACKGROUND, ShoppingArcadeDialogue.ARCADE, null, true, false, true, true, "in the Shopping Arcade"),

	SHOPPING_ARCADE_GENERIC_SHOP("Shop", "dominion/shoppingArcade/genericShop", BaseColour.BLACK, Colour.MAP_BACKGROUND, ShoppingArcadeDialogue.GENERIC_SHOP, null, true, false, true, true, "in the Shopping Arcade"),
	
	SHOPPING_ARCADE_RALPHS_SHOP("Ralph's Snacks", "dominion/shoppingArcade/ralphShop", BaseColour.TEAL, Colour.MAP_BACKGROUND, RalphsSnacks.EXTERIOR, null, true, false, true, true, "in his store"),
	
	SHOPPING_ARCADE_NYANS_SHOP("Nyan's Clothing Emporium", "dominion/shoppingArcade/nyanShop", BaseColour.ROSE, Colour.MAP_BACKGROUND, ClothingEmporium.EXTERIOR, null, true, false, true, true, "in her store"),
	
	SHOPPING_ARCADE_VICKYS_SHOP("Arcane Arts", "dominion/shoppingArcade/vickyShop", BaseColour.MAGENTA, Colour.MAP_BACKGROUND, ArcaneArts.EXTERIOR, null, true, false, true, true, "in her store"),

	SHOPPING_ARCADE_KATES_SHOP("Succubi's Secrets", "dominion/shoppingArcade/kateShop", BaseColour.PINK, Colour.MAP_BACKGROUND, SuccubisSecrets.EXTERIOR, null, true, false, true, true, "in her beauty salon"),

	SHOPPING_ARCADE_ASHLEYS_SHOP("Dream Lover", "dominion/shoppingArcade/ashleyShop", BaseColour.LILAC_LIGHT, Colour.MAP_BACKGROUND, DreamLover.EXTERIOR, null, true, false, true, true, "in their store"),
	
	SHOPPING_ARCADE_SUPPLIER_DEPOT("Supplier Depot", "dominion/shoppingArcade/supplierDepot", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, SupplierDepot.EXTERIOR, null, true, false, true, true, "in the supplier depot") {
		@Override
		public BaseColour getColour() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_NYAN_HELP)) {
				return BaseColour.GREEN;
			} else {
				return BaseColour.CRIMSON;
			}
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_NYAN_HELP)) {
				return PlaceType.getSVGOverride("dominion/shoppingArcade/supplierDepot", Colour.BASE_GREEN);
			} else {
				return SVGString;
			}
		}
	},
	
	SHOPPING_ARCADE_PIXS_GYM("Pix's Playground", "dominion/shoppingArcade/gym", BaseColour.GOLD, Colour.MAP_BACKGROUND, PixsPlayground.GYM_EXTERIOR, null, true, false, true, true, "in her gym"),

	// Exits & entrances:
	SHOPPING_ARCADE_ENTRANCE("Exit", "dominion/shoppingArcade/exit", BaseColour.RED, Colour.MAP_BACKGROUND, ShoppingArcadeDialogue.ENTRY, null, true, false, true, true, "in the Shopping Arcade"),
	
	
	// Supplier Depot:
	
	SUPPLIER_DEPOT_CORRIDOR("Corridor", null, BaseColour.BLACK, Colour.MAP_BACKGROUND, SupplierDepot.SUPPLIER_DEPOT_CORRIDOR, null, false, false, true, false, "in the supplier depot"),
	
	SUPPLIER_DEPOT_ENTRANCE("Reception Area", "dominion/shoppingArcade/exit", BaseColour.GREY, Colour.MAP_BACKGROUND, SupplierDepot.SUPPLIER_DEPOT_RECEPTION, null, false, false, true, false, "in the supplier depot") {
		@Override
		public boolean isPopulated() {
			return Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_NYAN_HELP);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_NYAN_HELP)) {
				return PlaceType.getSVGOverride("dominion/shoppingArcade/exit", Colour.BASE_GREEN);
			} else {
				return SVGString;
			}
		}
	},
	
	SUPPLIER_DEPOT_STORAGE_ROOM("Storage Room", "dominion/shoppingArcade/supplierStorageRoom", BaseColour.ORANGE, Colour.MAP_BACKGROUND, SupplierDepot.SUPPLIER_DEPOT_STORAGE_ROOM, null, false, false, true, false, "in the supplier depot"),
	
	SUPPLIER_DEPOT_OFFICE("Office", "dominion/shoppingArcade/supplierOffice", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, SupplierDepot.SUPPLIER_DEPOT_OFFICE, null, true, true, true, false, "in the supplier depot") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_NYAN_HELP);
		}
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.suppliersEncountered)) {
				return SupplierDepot.SUPPLIER_DEPOT_OFFICE_REPEAT;
				
			} else {
				return SupplierDepot.SUPPLIER_DEPOT_OFFICE;
			}
		}
	},
	
	
	// Slaver Alley:
	
	SLAVER_ALLEY_PATH("Alleyway", null, BaseColour.BLACK, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.ALLEYWAY, null, true, false, true, true, "in Slaver's Alley"),

	SLAVER_ALLEY_MARKET_STALL("Slaver's Shop", "dominion/slaverAlley/marketStall", BaseColour.BLACK, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.MARKET_STALL, null, true, false, true, true, "in Slaver's Alley"),
	
	SLAVER_ALLEY_AUCTIONING_BLOCK("Auctioning Block", "dominion/slaverAlley/auctionBlock", BaseColour.GOLD, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.AUCTION_BLOCK, null, true, false, true, true, "in Slaver's Alley"),

	SLAVER_ALLEY_PUBLIC_STOCKS("Public Stocks", "dominion/slaverAlley/stocks", BaseColour.TAN, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.PUBLIC_STOCKS, null, true, false, true, true, "in the stocks at Slaver's Alley"),

	SLAVER_ALLEY_SLAVERY_ADMINISTRATION("Slavery Administration", "dominion/slaverAlley/slaveryAdministration", BaseColour.PURPLE, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.SLAVERY_ADMINISTRATION_EXTERIOR, null, true, false, true, true, "in Slaver's Alley"){
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.SLAVERY_ADMINISTRATION_CELLS));
		}
	},
	
	SLAVER_ALLEY_SCARLETTS_SHOP("Scarlett's Shop", "dominion/slaverAlley/scarlettsStall", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, ScarlettsShop.SCARLETTS_SHOP_EXTERIOR, null, true, false, true, true, "in Slaver's Alley"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) { // Scarlett owns the shop:
				return ScarlettsShop.SCARLETTS_SHOP_EXTERIOR;
				
			} else { // Alexa owns the shop:
				return ScarlettsShop.ALEXAS_SHOP_EXTERIOR;
			}
		}	
	},
	
	SLAVER_ALLEY_ENTRANCE("Gateway", "dominion/slaverAlley/exit", BaseColour.RED, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.GATEWAY, null, true, false, true, true, "in Slaver's Alley"),
	
	
	// Submission:
	
	SUBMISSION_WALKWAYS("Walkways", null, BaseColour.BLACK, Colour.MAP_BACKGROUND, SubmissionGenericPlaces.WALKWAYS, null, true, false, true, true, "in Submission"),

	SUBMISSION_TUNNELS("Tunnels", "submission/tunnelsIcon", BaseColour.BLACK, Colour.MAP_BACKGROUND, SubmissionGenericPlaces.TUNNEL, Encounter.SUBMISSION_TUNNELS, false, true, true, true, "in Submission"),
	
	SUBMISSION_BAT_CAVERNS("Bat Caverns", "submission/batCaverns", BaseColour.BLUE, Colour.MAP_BACKGROUND, SubmissionGenericPlaces.BAT_CAVERNS, null, true, false, true, true, "in Submission"), // Insert batman reference here.
	
	SUBMISSION_RAT_WARREN("Rat Warren", "submission/ratWarren", BaseColour.BROWN_DARK, Colour.MAP_BACKGROUND, SubmissionGenericPlaces.RAT_WARREN, null, true, false, true, true, "in Submission"),
	
	SUBMISSION_GAMBLING_DEN("Gambling Den", "submission/gamblingDen", BaseColour.GOLD, Colour.MAP_BACKGROUND, SubmissionGenericPlaces.GAMBLING_DEN, null, true, false, true, true, "in Submission"),
	
	SUBMISSION_LILIN_PALACE("Lyssieth's Palace", "submission/lilinPalace", BaseColour.PURPLE, Colour.MAP_BACKGROUND_DARK, SubmissionGenericPlaces.LILIN_PALACE, null, true, false, true, true, "in Submission"),
	SUBMISSION_LILIN_PALACE_GATE("Lyssieth's Palace Gate", "submission/gate", BaseColour.PURPLE_LIGHT, Colour.MAP_BACKGROUND_DARK, SubmissionGenericPlaces.LILIN_PALACE_GATE, null, true, false, true, true, "in Submission"),
	SUBMISSION_LILIN_PALACE_CAVERN("Cavern", null, null, Colour.MAP_BACKGROUND_DARK, SubmissionGenericPlaces.LILIN_PALACE_CAVERN, null, true, false, true, true, "in Submission"),

	SUBMISSION_IMP_FORTRESS_1("Imp Fortress", "submission/impFortress1", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, SubmissionGenericPlaces.IMP_FORTRESS_1, null, true, false, true, true, "in Submission"),
	SUBMISSION_IMP_FORTRESS_2("Imp Fortress", "submission/impFortress2", BaseColour.MAGENTA, Colour.MAP_BACKGROUND, SubmissionGenericPlaces.IMP_FORTRESS_2, null, true, false, true, true, "in Submission"),
	SUBMISSION_IMP_FORTRESS_3("Imp Fortress", "submission/impFortress3", BaseColour.PINK, Colour.MAP_BACKGROUND, SubmissionGenericPlaces.IMP_FORTRESS_3, null, true, false, true, true, "in Submission"),
	SUBMISSION_IMP_FORTRESS_4("Imp Fortress", "submission/impFortress4", BaseColour.PINK_LIGHT, Colour.MAP_BACKGROUND, SubmissionGenericPlaces.IMP_FORTRESS_4, null, true, false, true, true, "in Submission"),
	SUBMISSION_IMP_FORTRESS_5("Imp Fortress", "submission/impFortress5", BaseColour.PURPLE, Colour.MAP_BACKGROUND, SubmissionGenericPlaces.IMP_FORTRESS_5, null, true, false, true, true, "in Submission"),
	SUBMISSION_IMP_FORTRESS_6("Imp Fortress", "submission/impFortress6", BaseColour.PURPLE_LIGHT, Colour.MAP_BACKGROUND, SubmissionGenericPlaces.IMP_FORTRESS_6, null, true, false, true, true, "in Submission"),

	SUBMISSION_ENTRANCE("Enforcer Checkpoint", "submission/submissionExit", BaseColour.BROWN, Colour.MAP_BACKGROUND, SubmissionGenericPlaces.SEWER_ENTRANCE, null, true, false, true, true, "in Submission"),
	
	// Bat caverns:

	BAT_CAVERN_ENTRANCE("Winding Staircase", "submission/cavernStaircase", BaseColour.GREEN, Colour.MAP_BACKGROUND, BatCaverns.STAIRCASE, null, false, true, true, true, "in the Bat Caverns"),
	BAT_CAVERN_DARK("Dark Cavern", null, null, Colour.MAP_BACKGROUND, BatCaverns.CAVERN_DARK, Encounter.BAT_CAVERN, true, false, true, true, "in the Bat Caverns"),
	BAT_CAVERN_LIGHT("Bioluminescent Cavern", "submission/cavernBioluminescent", BaseColour.AQUA, Colour.MAP_BACKGROUND, BatCaverns.CAVERN_LIGHT, Encounter.BAT_CAVERN, false, true, true, true, "in the Bat Caverns"),
	BAT_CAVERN_RIVER("Underground River", "submission/cavernRiver", BaseColour.BLUE, Colour.MAP_BACKGROUND, BatCaverns.RIVER, Encounter.BAT_CAVERN, false, true, true, true, "in the Bat Caverns"),
	BAT_CAVERN_RIVER_CROSSING("Mushroom Bridge", "submission/cavernBridge", BaseColour.TEAL, Colour.MAP_BACKGROUND, BatCaverns.RIVER_BRIDGE, Encounter.BAT_CAVERN, false, true, true, true, "in the Bat Caverns"),
	BAT_CAVERN_RIVER_END("Underground River End", "submission/cavernRiver", BaseColour.BROWN, Colour.MAP_BACKGROUND, BatCaverns.RIVER_END, Encounter.BAT_CAVERN, false, true, true, true, "in the Bat Caverns"),
	BAT_CAVERN_SLIME_QUEEN_LAIR("Slime Lake", "submission/cavernLake", BaseColour.PINK_LIGHT, Colour.MAP_BACKGROUND, BatCaverns.SLIME_LAKE, Encounter.BAT_CAVERN, false, true, true, true, "beside Slime Lake");

	
	private String name;
	protected String SVGString;
	private BaseColour colour;
	private Colour backgroundColour;
	protected DialogueNodeOld dialogue;
	private Encounter encounterType;
	private boolean populated, dangerous, stormImmune, itemsDisappear;
	private String virgintyLossDescription;

	private static Map<String, String> SVGOverrides = new HashMap<>(); 
	
	private PlaceType(String name,
			String SVGPath,
			BaseColour colour,
			Colour backgroundColour,
			DialogueNodeOld dialogue,
			Encounter encounterType,
			boolean populated,
			boolean dangerous,
			boolean stormImmune,
			boolean itemsDisappear,
			String virgintyLossDescription) {
		
		this.name = name;
		this.colour = colour;
		this.backgroundColour = backgroundColour;
		this.dialogue = dialogue;
		this.encounterType = encounterType;
		this.populated = populated;
		this.dangerous = dangerous;
		this.stormImmune = stormImmune;
		this.itemsDisappear = itemsDisappear;
		this.virgintyLossDescription = virgintyLossDescription;
		
		if(SVGPath!=null) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/" + SVGPath + ".svg");
				String s = Util.inputStreamToString(is);
				
				if(colour!=null) {
					s = s.replaceAll("#ff2a2a", this.colour.getShades()[0]);
					s = s.replaceAll("#ff5555", this.colour.getShades()[1]);
					s = s.replaceAll("#ff8080", this.colour.getShades()[2]);
					s = s.replaceAll("#ffaaaa", this.colour.getShades()[3]);
					s = s.replaceAll("#ffd5d5", this.colour.getShades()[4]);
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

	public String getName() {
		return name;
	}

	public BaseColour getColour() {
		return colour;
	}

	public Colour getBackgroundColour() {
		return backgroundColour;
	}

	public DialogueNodeOld getDialogue(boolean withRandomEncounter) {
		return getDialogue(withRandomEncounter, false);
	}
	
	public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
		if (encounterType != null && withRandomEncounter) {
			DialogueNodeOld dn = encounterType.getRandomEncounter(forceEncounter);
			if (dn != null)
				return dn;
		}

		return dialogue;
	}

	public boolean isPopulated() {
		return populated;
	}

	public boolean isDangerous() {
		return dangerous;
	}

	public boolean isStormImmune() {
		return stormImmune;
	}
	
	public boolean isItemsDisappear() {
		return itemsDisappear;
	}
	
	private static String getSVGOverride(String pathName, Colour colour) {
		if(!SVGOverrides.keySet().contains(pathName+colour)) {
			try {
				InputStream is = colour.getClass().getResourceAsStream("/com/lilithsthrone/res/map/" + pathName + ".svg");
				String s = Util.inputStreamToString(is);
				
				if(colour!=null) {
					s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
					s = s.replaceAll("#ff5555", colour.getShades()[1]);
					s = s.replaceAll("#ff8080", colour.getShades()[2]);
					s = s.replaceAll("#ffaaaa", colour.getShades()[3]);
					s = s.replaceAll("#ffd5d5", colour.getShades()[4]);
				}
				SVGOverrides.put(pathName+colour, s);
	
				is.close();
	
			} catch (Exception e1) {
				System.err.println("Eeeeeek! PlaceType.getSVGOverride()");
				e1.printStackTrace();
				return "";
			}
		}
		
		return SVGOverrides.get(pathName+colour);
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
	
	public PlaceType getParentPlaceType() {
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
	
	public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
		return new ArrayList<>();
	}

	public String getVirgintyLossDescription() {
		return virgintyLossDescription;
	}
}
