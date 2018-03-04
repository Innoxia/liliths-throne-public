package com.lilithsthrone.world.places;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.Quest;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.places.JunglePlaces;
import com.lilithsthrone.game.dialogue.places.SewerPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.CityPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.DemonHome;
import com.lilithsthrone.game.dialogue.places.dominion.EnforcerHQDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.LilithsTower;
import com.lilithsthrone.game.dialogue.places.dominion.NightlifeDistrict;
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
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.PixsPlayground;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.RalphsSnacks;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ShoppingArcadeDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeFirstFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
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
 * @version 0.1.90
 * @author Innoxia
 * @see Comment by LightC
 * This contain a list of cell declaration
 * -cell name
 * -SVGpath
 * -cell color
 * -dialogue node***decripit?***
 * -encounter
 * -is populated
 * -is dangerous
 * -is storm immune
 * -does item disappear
 * -virginity lost description
 */
public enum PlaceType {
	
	
	GENERIC_IMPASSABLE(null, null, null, null, null, false, false, false, true, ""),
	
	GENERIC_EMPTY_TILE("Empty", "slaverAlleyIcon",  BaseColour.CRIMSON, null, null, true, false, false, true, ""),
	
	GENERIC_MUSEUM("Museum", "slaverAlleyIcon",  BaseColour.TAN, null, null, true, false, true, false, "in the restroom of Lily's Museum"),
	
	
	DOMINION_STREET("Dominion Streets", null, null, CityPlaces.STREET, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_LILITHS_TOWER("Lilith's Tower", "lilithsTowerIcon", BaseColour.PURPLE, LilithsTower.OUTSIDE, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_ENFORCER_HQ("Enforcer HQ", "enforcerHQIcon", BaseColour.BLUE, EnforcerHQDialogue.EXTERIOR, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_DEMON_HOME("Demon Home", "demonHomeIcon", BaseColour.PINK, DemonHome.DEMON_HOME_STREET, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_SHOPPING_ARCADE("Shopping Arcade", "shoppingArcadeIcon", BaseColour.GOLD, ShoppingArcadeDialogue.OUTSIDE, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_HARPY_NESTS("Harpy Nests", "harpyNestIcon", BaseColour.MAGENTA, HarpyNestsDialogue.OUTSIDE, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_NIGHTLIFE_DISTRICT("Nightlife District", "nightlifeIcon", BaseColour.PINK_LIGHT, NightlifeDistrict.OUTSIDE, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_CITY_HALL("City Hall", "townHallIcon",  BaseColour.LILAC, CityHall.OUTSIDE, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_AUNTS_HOME("Lilaya's Home", "homeIcon", BaseColour.BLUE_LIGHT, LilayaHomeGeneric.OUTSIDE, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {

		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	DOMINION_SLAVER_ALLEY("Slaver Alley", "slaverAlleyIcon",  BaseColour.CRIMSON, SlaverAlleyDialogue.OUTSIDE, null, true, false, false, true, "in the alleyways near Slaver's Alley") {

		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},

	// Dangerous tiles:
	
	DOMINION_BACK_ALLEYS("Dominion Alleyways", "alleysIcon",  BaseColour.BLACK, CityPlaces.BACK_ALLEYS, Encounter.DOMINION_ALLEY, false, true, false, true, "in one of Dominion's backalleys"),

	DOMINION_DARK_ALLEYS("Dark Alleyways", "alleysDarkIcon",  BaseColour.PURPLE, CityPlaces.DARK_ALLEYS, Encounter.DOMINION_DARK_ALLEY, false, true, false, true, "in one of Dominion's dark alleyways"),
	
	// Exits & entrances:
	
	DOMINION_EXIT_TO_SEWERS("Submission Entrance", "submissionExit",  BaseColour.BROWN, CityPlaces.CITY_EXIT_SEWERS, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		
		@Override
		public Bearing getBearing() {
			return Bearing.RANDOM;
		}
	},
	
	DOMINION_EXIT_TO_JUNGLE("Jungle Entrance", "JungleExit",  BaseColour.GREEN_LIME, CityPlaces.CITY_EXIT_JUNGLE, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		
		@Override
		public Bearing getBearing() {
			return Bearing.NORTH;
		}
	},
	
	DOMINION_EXIT_TO_FIELDS("Fields Entrance", "fieldsExit",  BaseColour.GREEN_LIGHT, CityPlaces.CITY_EXIT_FIELDS, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		
		@Override
		public Bearing getBearing() {
			return Bearing.WEST;
		}
	},
	
	DOMINION_EXIT_TO_SEA("Endless Sea Entrance", "endlessSeaExit",  BaseColour.TEAL, CityPlaces.CITY_EXIT_SEA, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		
		@Override
		public Bearing getBearing() {
			return Bearing.EAST;
		}
	},
	
	DOMINION_EXIT_TO_DESERT("Desert Entrance", "desertExit", BaseColour.YELLOW, CityPlaces.CITY_EXIT_DESERT, Encounter.DOMINION_STREET, true, false, false, true, "in the streets of Dominion") {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		
		@Override
		public Bearing getBearing() {
			return Bearing.SOUTH;
		}
	},
	
	
	
	
	ENFORCER_HQ_CORRIDOR("Corridor", null, BaseColour.BLACK, EnforcerHQDialogue.CORRIDOR, null, true, false, true, true, "in the Enforcer HQ"),

	ENFORCER_HQ_WAITING_AREA("Waiting area", "dominion/enforcerHQ/waitingRoom", BaseColour.BROWN, EnforcerHQDialogue.WAITING_AREA, null, true, false, true, true, "in the Enforcer HQ"),
	
	ENFORCER_HQ_RECEPTION_DESK("Reception desk", "dominion/enforcerHQ/receptionDesk", BaseColour.BLUE_LIGHT, EnforcerHQDialogue.RECEPTION_DESK, null, true, false, true, true, "in Candi's office"),
	
	ENFORCER_HQ_GUARDED_DOOR("Guarded door", "dominion/enforcerHQ/guardedDoor", BaseColour.CRIMSON, EnforcerHQDialogue.GUARDED_DOOR, null, true, false, true, true, "in the Enforcer HQ"),
	
	ENFORCER_HQ_BRAXS_OFFICE("Brax's Office", "dominion/enforcerHQ/braxsOffice", BaseColour.BLUE_STEEL, EnforcerHQDialogue.INTERIOR_BRAX, null, true, false, true, true, "in his office") {
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.braxEncountered)) {
				return EnforcerHQDialogue.INTERIOR_BRAX_REPEAT;
				
			} else {
				return EnforcerHQDialogue.INTERIOR_BRAX;
			}
		}
	},

	ENFORCER_HQ_ENTRANCE("Entranceway", "dominion/enforcerHQ/exit", BaseColour.RED, EnforcerHQDialogue.ENTRANCE, null, true, false, true, true, ""),
	
	
	
	
	
	// Standard tiles:
	HARPY_NESTS_WALKWAYS("Walkway", null, BaseColour.BLACK, HarpyNestsDialogue.WALKWAY, Encounter.HARPY_NEST_WALKWAYS, false, true, false, true, "in the Harpy Nests") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
	},

	// Places:
	HARPY_NESTS_ENTRANCE_ENFORCER_POST("Enforcer post", "dominion/harpyNests/exit", BaseColour.RED, HarpyNestsDialogue.ENTRANCE_ENFORCER_POST, null, true, false, true, true, "in the Harpy Nests"),
	
	HARPY_NESTS_ALEXAS_NEST("Alexa's nest", "dominion/harpyNests/nestAlexa", BaseColour.GOLD, HarpyNestAlexa.ALEXAS_NEST_EXTERIOR, null, true, false, false, true, "in Alexa's nest"),
	
	HARPY_NESTS_HARPY_NEST_RED("Harpy nest", "dominion/harpyNests/nestRed", BaseColour.CRIMSON, HarpyNestDominant.HARPY_NEST_DOMINANT, null, true, false, false, true, "in Diana's nest"),
	
	HARPY_NESTS_HARPY_NEST_PINK("Harpy nest", "dominion/harpyNests/nestPink", BaseColour.PINK_LIGHT, HarpyNestNympho.HARPY_NEST_NYMPHO, null, true, false, false, true, "in Lexi's nest"),
	
	HARPY_NESTS_HARPY_NEST_YELLOW("Harpy nest", "dominion/harpyNests/nestYellow", BaseColour.YELLOW_LIGHT, HarpyNestBimbo.HARPY_NEST_BIMBO, null, true, false, false, true, "in Brittany's nest"),
		
		
	
	
	
	// Standard tiles:
	JUNGLE_PATH("Jungle Path", null, BaseColour.GREEN, JunglePlaces.PATH, null, false, false, false, true, "in the jungle"),
	
	JUNGLE_DENSE_JUNGLE("Dense Jungle", null, BaseColour.GREEN, JunglePlaces.DENSE_JUNGLE, null, false, true, false, true, "in the jungle"),

	// Safe places:
	JUNGLE_CLUB("Club", null, BaseColour.GREEN, JunglePlaces.CLUB, null, true, false, false, true, "in the jungle"),
	
	JUNGLE_BROTHEL("Brothel", null, BaseColour.GREEN, JunglePlaces.BROTHEL, null, true, false, false, true, "in the jungle"),

	// Dangerous places:
	JUNGLE_TENTACLE_QUEENS_LAIR("Tentacle Queen's Lair", null, BaseColour.GREEN, JunglePlaces.TENTACLE_QUEENS_LAIR, null, true, false, false, true, "in the jungle"),

	// Exits & entrances:
	JUNGLE_ENTRANCE("Jungle Entrance", null, BaseColour.GREEN, JunglePlaces.JUNGLE_ENTRANCE, null, true, false, false, true, "in the jungle"){
		@Override
		public WorldType getParentWorldType() {
			return WorldType.DOMINION;
		}
		@Override
		public PlaceType getParentPlaceType() {
			return PlaceType.DOMINION_EXIT_TO_JUNGLE;
		}
		@Override
		public EntranceType getParentAlignment() {
			return EntranceType.ALIGNED_FLIP_VERTICAL;
		}
	},
	
	
	
	// Ground floor:
	
	LILAYA_HOME_CORRIDOR("Corridor", null, BaseColour.GREY, LilayaHomeGeneric.CORRIDOR, Encounter.LILAYAS_HOME_CORRIDOR, true, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR("Room", "dominion/lilayasHome/room", BaseColour.GREY, LilayaHomeGeneric.ROOM_WINDOW, null, true, false, true, false, "in Lilaya's Home") {
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
	
	LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR("Garden Room", "dominion/lilayasHome/room", BaseColour.GREY, LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR, null, true, false, true, false, "in Lilaya's Home") {
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
	
	LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR("Room", "dominion/lilayasHome/room", BaseColour.GREY, LilayaHomeGeneric.ROOM_WINDOW, null, true, false, true, false, "in Lilaya's Home") {
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
	
	LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR("Garden Room", "dominion/lilayasHome/room", BaseColour.GREY, LilayaHomeGeneric.ROOM_GARDEN, null, true, false, true, false, "in Lilaya's Home") {
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
	
	LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_SLAVE("Slave's Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_WINDOW_SLAVE, null, true, false, true, false, "in Lilaya's Home") {
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
	
	LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE("Slave's Garden Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR_SLAVE, null, true, false, true, false, "in Lilaya's Home") {
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
	
	LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_SLAVE("Slave's Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_WINDOW_SLAVE, null, true, false, true, false, "in Lilaya's Home") {
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
	
	LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_SLAVE("Slave's Garden Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_GARDEN_SLAVE, null, true, false, true, false, "in Lilaya's Home") {
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
	
	LILAYA_HOME_ARTHUR_ROOM("Arthur's Room", "dominion/lilayasHome/roomArthur", BaseColour.BLUE_STEEL, LilayaHomeGeneric.ROOM_ARTHUR, null, true, false, true, false, "in Arthur's Room"),
	
	LILAYA_HOME_BIRTHING_ROOM("Room", "dominion/lilayasHome/roomBirthing", BaseColour.PINK, LilayaHomeGeneric.BIRTHING_ROOM, null, true, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_KITCHEN("Kitchen", "dominion/lilayasHome/kitchen", BaseColour.TAN, LilayaHomeGeneric.KITCHEN, null, true, false, true, false, "in Lilaya's kitchen"),
	
	LILAYA_HOME_LIBRARY("Library", "dominion/lilayasHome/library", BaseColour.TEAL, Library.LIBRARY, null, true, false, true, false, "in Lilaya's library"),
	
	LILAYA_HOME_STAIR_UP("Staircase", "dominion/lilayasHome/stairsUp", BaseColour.GREEN_LIGHT, LilayaHomeGeneric.STAIRCASE_UP, null, true, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ENTRANCE_HALL("Entrance Hall", "dominion/lilayasHome/entranceHall", BaseColour.RED, LilayaHomeGeneric.ENTRANCE_HALL, null, true, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_LAB("Lilaya's Lab", "dominion/lilayasHome/lab", BaseColour.ORANGE, Lab.LAB, null, true, false, true, false, "in Lilaya's lab"),
	
	LILAYA_HOME_GARDEN("Garden", "dominion/lilayasHome/garden", BaseColour.GREEN, LilayaHomeGeneric.GARDEN, null, true, false, false, false, "in Lilaya's garden"),
	
	LILAYA_HOME_FOUNTAIN("Fountain", "dominion/lilayasHome/fountain", BaseColour.BLUE_LIGHT, LilayaHomeGeneric.FOUNTAIN, null, true, false, false, false, "in Lilaya's garden"),
	

	// First floor:

	LILAYA_HOME_ROOM_LILAYA("Lilaya's Room", "dominion/lilayasHome/roomLilaya", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_LILAYA, null, true, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ROOM_ROSE("Rose's Room", "dominion/lilayasHome/roomRose", BaseColour.PINK, LilayaHomeGeneric.ROOM_ROSE, null, true, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ROOM_PLAYER("Your Room", "dominion/lilayasHome/roomPlayer", BaseColour.AQUA, RoomPlayer.ROOM, null, true, false, true, false, "in your room"),
	
	LILAYA_HOME_STAIR_DOWN("Staircase", "dominion/lilayasHome/stairsDown", BaseColour.RED, LilayaHomeGeneric.STAIRCASE_DOWN, null, true, false, true, false, "in Lilaya's Home"),
	
	

	// Zaranix: TODO

	ZARANIX_GF_CORRIDOR("Corridor", null, BaseColour.GREY, ZaranixHomeGroundFloor.CORRIDOR, null, false, false, true, false, "in Zaranix's home"),
	ZARANIX_GF_STAIRS("Staircase", "dominion/zaranixHome/stairsDown", BaseColour.GREEN_LIGHT, ZaranixHomeGroundFloor.STAIRS, null, false, false, true, false, "in Zaranix's home"),
	ZARANIX_GF_ENTRANCE("Entrance", "dominion/zaranixHome/entranceHall", BaseColour.RED, ZaranixHomeGroundFloor.ENTRANCE, null, false, false, true, false, "in Zaranix's home"),
	ZARANIX_GF_LOUNGE("Lounge", "dominion/zaranixHome/lounge", BaseColour.ORANGE, ZaranixHomeGroundFloor.LOUNGE, null, false, false, true, false, "in Zaranix's home"),
	ZARANIX_GF_ROOM("Room", "dominion/zaranixHome/room", BaseColour.GREY, ZaranixHomeGroundFloor.ROOM, null, false, false, true, false, "in a room in Zaranix's home"),
	ZARANIX_GF_MAID("Corridor", null, BaseColour.GREY, ZaranixHomeGroundFloor.CORRIDOR_MAID, null, true, true, true, false, "in Zaranix's Home"),
	ZARANIX_GF_GARDEN_ROOM("Room", "dominion/zaranixHome/room", BaseColour.GREY, ZaranixHomeGroundFloor.GARDEN_ROOM, null, false, false, true, false, "in a room in Zaranix's home"),
	ZARANIX_GF_GARDEN("Garden", "dominion/zaranixHome/garden", BaseColour.GREEN, ZaranixHomeGroundFloor.GARDEN, null, false, false, true, false, "in Zaranix's garden"),
	ZARANIX_GF_GARDEN_ENTRY("Garden", "dominion/zaranixHome/entranceHall", BaseColour.GREEN, ZaranixHomeGroundFloor.GARDEN_ENTRY, null, false, false, true, false, "in Zaranix's garden"),

	ZARANIX_FF_CORRIDOR("Corridor", null, BaseColour.GREY, ZaranixHomeFirstFloor.CORRIDOR, null, false, false, true, false, "in Zaranix's home"),
	ZARANIX_FF_STAIRS("Staircase", "dominion/zaranixHome/stairsDown", BaseColour.RED, ZaranixHomeFirstFloor.STAIRS, null, false, false, true, false, "in Zaranix's home"),
	ZARANIX_FF_OFFICE("Zaranix's Room", "dominion/zaranixHome/roomZaranix", BaseColour.PINK_DEEP, ZaranixHomeFirstFloor.ZARANIX_ROOM, null, true, true, true, false, "in Zaranix's home"),
	ZARANIX_FF_ROOM("Room", "dominion/zaranixHome/room", BaseColour.GREY, ZaranixHomeFirstFloor.ROOM, null, false, false, true, false, "in a room in Zaranix's home"),
	ZARANIX_FF_MAID("Corridor", null, BaseColour.RED, ZaranixHomeFirstFloor.CORRIDOR_MAID, null, true, true, true, false, "in Zaranix's Home"),
	
	
	// Standard tiles:
	SHOPPING_ARCADE_PATH("Arcade", null, BaseColour.BLACK, ShoppingArcadeDialogue.ARCADE, null, true, false, true, true, "in the Shopping Arcade"),

	// Places:
	SHOPPING_ARCADE_GENERIC_SHOP("Shop", "dominion/shoppingArcade/genericShop", BaseColour.BLACK, ShoppingArcadeDialogue.GENERIC_SHOP, null, true, false, true, true, "in the Shopping Arcade"),
	
	SHOPPING_ARCADE_RALPHS_SHOP("Ralph's Snacks", "dominion/shoppingArcade/ralphShop", BaseColour.TEAL, RalphsSnacks.EXTERIOR, null, true, false, true, true, "in his store"),
	
	SHOPPING_ARCADE_NYANS_SHOP("Nyan's Clothing Emporium", "dominion/shoppingArcade/nyanShop", BaseColour.ROSE, ClothingEmporium.EXTERIOR, null, true, false, true, true, "in her store"),
	
	SHOPPING_ARCADE_VICKYS_SHOP("Arcane Arts", "dominion/shoppingArcade/vickyShop", BaseColour.MAGENTA, ArcaneArts.EXTERIOR, null, true, false, true, true, "in her store"),

	SHOPPING_ARCADE_KATES_SHOP("Succubi's Secrets", "dominion/shoppingArcade/kateShop", BaseColour.PINK, SuccubisSecrets.EXTERIOR, null, true, false, true, true, "in her beauty salon"),

	SHOPPING_ARCADE_PIXS_GYM("City Gym", "dominion/shoppingArcade/gym", BaseColour.GOLD, PixsPlayground.GYM_EXTERIOR, null, true, false, true, true, "in her gym"),

	// Exits & entrances:
	SHOPPING_ARCADE_ENTRANCE("Exit", "dominion/shoppingArcade/exit", BaseColour.RED, ShoppingArcadeDialogue.ENTRY, null, true, false, true, true, "in the Shopping Arcade"),
	
	
	
	
	SLAVER_ALLEY_PATH("Alleyway", null, BaseColour.BLACK, SlaverAlleyDialogue.ALLEYWAY, null, true, false, true, true, "in Slaver's Alley"),

	SLAVER_ALLEY_MARKET_STALL("Slaver's Shop", "dominion/slaverAlley/marketStall", BaseColour.BLACK, SlaverAlleyDialogue.MARKET_STALL, null, true, false, true, true, "in Slaver's Alley"),
	
	SLAVER_ALLEY_AUCTIONING_BLOCK("Auctioning Block", "dominion/slaverAlley/auctionBlock", BaseColour.GOLD, SlaverAlleyDialogue.AUCTION_BLOCK, null, true, false, true, true, "in Slaver's Alley"),

	SLAVER_ALLEY_PUBLIC_STOCKS("Public Stocks", "dominion/slaverAlley/stocks", BaseColour.TAN, SlaverAlleyDialogue.PUBLIC_STOCKS, null, true, false, true, true, "in the stocks at Slaver's Alley"),

	SLAVER_ALLEY_BROTHEL("Brothel", "dominion/slaverAlley/brothel", BaseColour.MAGENTA, SlaverAlleyDialogue.BROTHEL, null, true, false, true, true, "at the brothel Angel's Kiss"),

	SLAVER_ALLEY_SLAVERY_ADMINISTRATION("Slavery Administration", "dominion/slaverAlley/slaveryAdministration", BaseColour.PURPLE, SlaverAlleyDialogue.SLAVERY_ADMINISTRATION_EXTERIOR, null, true, false, true, true, "in Slaver's Alley"){
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.SLAVERY_ADMINISTRATION_CELLS));
		}
	},
	
	SLAVER_ALLEY_SCARLETTS_SHOP("Scarlett's Shop", "dominion/slaverAlley/scarlettsStall", BaseColour.CRIMSON, ScarlettsShop.SCARLETTS_SHOP_EXTERIOR, null, true, false, true, true, "in Slaver's Alley"){
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter) {
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) { // Scarlett owns the shop:
				return ScarlettsShop.SCARLETTS_SHOP_EXTERIOR;
				
			} else { // Alexa owns the shop:
				return ScarlettsShop.ALEXAS_SHOP_EXTERIOR;
			}
		}	
	},
	
	SLAVER_ALLEY_ENTRANCE("Gateway", "dominion/slaverAlley/exit", BaseColour.RED, SlaverAlleyDialogue.GATEWAY, null, true, false, true, true, "in Slaver's Alley"),
	
	
	
	
	SUBMISSION_WALKWAYS("Submission Walkways", null, BaseColour.BLACK, SewerPlaces.WALKWAYS, null, false, true, true, true, "in Submission"),

	SUBMISSION_RAT_TUNNELS("Rat Tunnels", null, BaseColour.BLACK, SewerPlaces.RAT_TUNNELS, null, true, false, true, true, "in Submission"),
	
	SUBMISSION_GAMBLING_DEN("Gambling Den", null, BaseColour.BLACK, SewerPlaces.GAMBLING_DEN, null, true, false, true, true, "in Submission"),
	
	SUBMISSION_IMP_PALACE("Imp Palace", null, BaseColour.BLACK, SewerPlaces.IMP_PALACE, null, true, false, true, true, "in Submission"),

	SUBMISSION_ENTRANCE("Submission Entrance", null, BaseColour.BLACK, SewerPlaces.SEWER_ENTRANCE, null, true, false, true, true, "in Submission"){
		@Override
		public WorldType getParentWorldType() {
			return WorldType.DOMINION;
		}
		@Override
		public PlaceType getParentPlaceType() {
			return PlaceType.DOMINION_EXIT_TO_SEWERS;
		}
		@Override
		public EntranceType getParentAlignment() {
			return EntranceType.ALIGNED;
		}
	};

	
	private String name;
	protected String SVGString;
	private BaseColour colour;
	private DialogueNodeOld dialogue;
	private Encounter encounterType;
	private boolean populated, dangerous, stormImmune, itemsDisappear;
	private String virgintyLossDescription;

	private static Map<String, String> SVGOverrides = new HashMap<>(); 
	
	private PlaceType(String name,
			String SVGPath,
			BaseColour colour,
			DialogueNodeOld dialogue,
			Encounter encounterType,
			boolean populated,
			boolean dangerous,
			boolean stormImmune,
			boolean itemsDisappear,
			String virgintyLossDescription) {
		
		this.name = name;
		this.colour = colour;
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

	public DialogueNodeOld getDialogue(boolean withRandomEncounter) {
		if (encounterType != null && withRandomEncounter) {
			DialogueNodeOld dn = encounterType.getRandomEncounter();
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
