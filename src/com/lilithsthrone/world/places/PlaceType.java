package com.lilithsthrone.world.places;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.DemonHome;
import com.lilithsthrone.game.dialogue.places.dominion.DominionExpress;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.EnforcerWarehouse;
import com.lilithsthrone.game.dialogue.places.dominion.HomeImprovements;
import com.lilithsthrone.game.dialogue.places.dominion.LilithsTower;
import com.lilithsthrone.game.dialogue.places.dominion.RedLightDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHallDemographics;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHallProperty;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.BraxOffice;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.EnforcerHQDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestBimbo;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestDominant;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestHelena;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestNympho;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestsDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaApartment;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Library;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.places.dominion.nightlife.NightlifeDistrict;
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
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaveryAdministration;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeFirstFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeFirstFloorRepeat;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.places.global.GlobalFoloiFields;
import com.lilithsthrone.game.dialogue.places.submission.BatCaverns;
import com.lilithsthrone.game.dialogue.places.submission.LyssiethPalaceDialogue;
import com.lilithsthrone.game.dialogue.places.submission.SlimeQueensLair;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.places.submission.gamblingDen.GamblingDenDialogue;
import com.lilithsthrone.game.dialogue.places.submission.gamblingDen.PregnancyRoulette;
import com.lilithsthrone.game.dialogue.places.submission.gamblingDen.RoxysShop;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensCaptiveDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.VengarCaptiveDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Bearing;
import com.lilithsthrone.world.TeleportPermissions;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class PlaceType {
	
	// Generic holding map:
	
	public static final AbstractPlaceType GENERIC_IMPASSABLE = new AbstractPlaceType(
			"Impassable Tile", "", null, PresetColour.BASE_GREY, null, null, "");
	
	public static final AbstractPlaceType GENERIC_EMPTY_TILE = new AbstractPlaceType(
			"Empty", "", "dominion/slaverAlleyIcon", PresetColour.BASE_CRIMSON, null, null, "");

	public static final AbstractPlaceType GENERIC_HOLDING_CELL = new AbstractPlaceType(
			"Unknown", "", "dominion/slaverAlleyIcon", PresetColour.BASE_GREY, null, null, "");
	
	public static final AbstractPlaceType GENERIC_MUSEUM = new AbstractPlaceType(
			"Museum", "", "dominion/slaverAlleyIcon", PresetColour.BASE_TAN, null, null, "in Lily's Museum"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	
	
	// Museum:
	
	public static final AbstractPlaceType MUSEUM_ENTRANCE = new AbstractPlaceType(
			"Entrance",
			"The main entrance to the museum in which your aunt Lily works.",
			"prologue/exit",
			PresetColour.BASE_RED,
			null,
			null,
			"in Lily's Museum") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_CROWDS = new AbstractPlaceType(
			"Crowds",
			"This part of the museum's main lobby is currently filled with a large crowd of visitors who, much like you, are here for the exhibition's opening event.",
			"prologue/crowd",
			PresetColour.BASE_YELLOW,
			null,
			null,
			"in Lily's Museum") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_OFFICE = new AbstractPlaceType(
			"Office",
			"A large, executive office. It looks sort of familiar, and in the back of your mind you think that it might be your aunt Lily's...",
			"prologue/office",
			PresetColour.BASE_BLUE_LIGHT,
			null,
			null,
			"in Lily's Museum"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_STAGE = new AbstractPlaceType(
			"Stage",
			"A large stage has been erected in the middle of the lobby, and it's from here that your aunt Lily is due to give her speech.",
			"prologue/stage",
			PresetColour.BASE_ORANGE,
			null,
			null,
			"in Lily's Museum") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_ROOM = new AbstractPlaceType(
			"Exhibit Room",
			"One of the many rooms in the museum that's dedicated to exhibiting ancient relics and precious artifacts.",
			"prologue/room",
			PresetColour.BASE_TAN,
			null,
			null,
			"in Lily's Museum"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_STAIRS = new AbstractPlaceType(
			"Stairs",
			"The museum's staircase connects the ground and first floors.",
			"prologue/stairsUp",
			PresetColour.BASE_GREEN,
			null,
			null,
			"in Lily's Museum"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_LOBBY = new AbstractPlaceType(
			"Lobby",
			"The main, double-height lobby of the museum. Banners celebrating the new exhibition have been hung from the upper floor railings.",
			 null,
			PresetColour.BASE_TAN,
			null,
			null,
			"in Lily's Museum") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_CORRIDOR = new AbstractPlaceType(
			"Corridor",
			"The museum's upper floor corridors are alarmingly maze-like in their layout.",
			 null,
			PresetColour.BASE_TAN,
			null,
			null,
			"in Lily's Museum"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_MIRROR = new AbstractPlaceType(
			"Mirror Room",
			"This particular room has a huge, ceiling-height mirror as its main attraction.",
			"prologue/mirror",
			PresetColour.BASE_PINK,
			null,
			null,
			"in Lily's Museum"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	
	
	// Dominion:
	
	public static final AbstractPlaceType DOMINION_PLAZA = new AbstractPlaceType(
			"Lilith's Plaza",
			"In the very centre of Dominion there is an expansive plaza, where news of Lilith's Domain is read out by full-time criers.",
			"dominion/statue",
			PresetColour.BASE_PINK_DEEP,
			DominionPlaces.DOMINION_PLAZA,
			null,
			"in Dominion's central plaza") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
				pop.add(new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getDominionStormImmuneSpecies(true, Subspecies.HUMAN)));
			} else {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
				pop.add(new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, true, Subspecies.HUMAN)));
			}
			
			return pop;
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_STREET = new AbstractPlaceType(
			"Dominion Streets",
			"The wide streets of Dominion are lined with well-kept town houses, and are entirely pedestrianised.",
			null,
			PresetColour.BASE_GREY,
			DominionPlaces.STREET,
			Encounter.DOMINION_STREET,
			"in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
				List<Population> pop = Util.newArrayListOfValues(new Population(true, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
				pop.add(new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, true, Subspecies.HUMAN)));
				pop.add(new Population(true, PopulationType.CENTAUR_CARTS, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.FOUR_COMMON))));
				return pop;
				
			} else {
				return new ArrayList<>();
			}
		}
	};
	
	public static final AbstractPlaceType DOMINION_BOULEVARD = new AbstractPlaceType(
			"Dominion Boulevard",
			"The main boulevards which lead to the centre of Dominion are extremely wide and well-travelled, and are very regularly patrolled by Enforcers.",
			null,
			PresetColour.BASE_PINK_LIGHT,
			DominionPlaces.BOULEVARD,
			Encounter.DOMINION_BOULEVARD,
			"in the streets of Dominion") {

		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_LILITHS_TOWER = new AbstractPlaceType(
			"Lilith's Tower",
			"Visible from many miles away, the colossal, dark-stone tower in which Lilith resides is a constant visual reminder to the city's population of who's in charge.",
			"dominion/lilithsTowerIcon",
			PresetColour.BASE_PURPLE,
			LilithsTower.OUTSIDE,
			null,
			"in the streets of Dominion") {

		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_ENFORCER_HQ = new AbstractPlaceType(
			"Enforcer HQ",
			"The Enforcer HQ is one of the more modern-looking buildings in Dominion, and it's from here that all of Dominion's law enforcement personnel are commanded.",
			"dominion/enforcerHQIcon",
			PresetColour.BASE_BLUE,
			EnforcerHQDialogue.EXTERIOR,
			Encounter.DOMINION_STREET,
			"in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_GATE = new AbstractPlaceType(
			"Demon Home Gates",
			"There are only a couple of entrances to the area known as 'Demon Home', and both are very heavily guarded by numerous Enforcers.",
			"dominion/gate",
			PresetColour.BASE_PINK_LIGHT,
			DemonHome.DEMON_HOME_GATE,
			null,
			"in the streets of Demon Home") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME = new AbstractPlaceType(
			"Demon Home",
			"The area surrounding Lilith's tower is known as 'Demon Home', but despite that name, the residents are of all manner of different races.",
			null,
			PresetColour.BASE_PINK,
			DemonHome.DEMON_HOME_STREET,
			null,
			"in the streets of Demon Home") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_ARTHUR = new AbstractPlaceType(
			"Demon Home (Arthur)",
			"The area surrounding Lilith's tower is known as 'Demon Home', but despite that name, the residents are of all manner of different races.",
			"dominion/demonHomeArthurIcon",
			PresetColour.BASE_PINK,
			DemonHome.DEMON_HOME_STREET_ARTHUR,
			null,
			"in the streets of Demon Home") {
		@Override
		public String getName() {
			if(Main.game.isStarted()) {
				return UtilText.parse("Demon Home ([arthur.Name])");
			}
			return name;
		}
		@Override
		public String getTooltipDescription() {
			return tooltipDescription+" Arthur's apartment is located in this particular area.";
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_ZARANIX = new AbstractPlaceType(
			"Demon Home (Zaranix)",
			"The area surrounding Lilith's tower is known as 'Demon Home', but despite that name, the residents are of all manner of different races.",
			"dominion/demonHomeZaranixIcon",
			PresetColour.BASE_PINK,
			DemonHome.DEMON_HOME_STREET_ZARANIX,
			null,
			"in the streets of Demon Home") {
		@Override
		public String getName() {
			if(Main.game.isStarted()) {
				return UtilText.parse("Demon Home ([zaranix.Name])");
			}
			return name;
		}
		@Override
		public String getTooltipDescription() {
			return tooltipDescription+UtilText.parse(" [zaranix.NamePos] apartment is located in this particular area.");
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_DADDY = new AbstractPlaceType(
			"Demon Home (Daddy)",
			"The area surrounding Lilith's tower is known as 'Demon Home', but despite that name, the residents are of all manner of different races.",
			"dominion/demonHomeDaddyIcon",
			PresetColour.BASE_PINK,
			DemonHome.DEMON_HOME_STREET_DADDY,
			null,
			"in the streets of Demon Home") {
		@Override
		public String getName() {
			if(Main.game.isStarted()) {
				return UtilText.parse("Demon Home ([daddy.Name])");
			}
			return name;
		}
		@Override
		public String getTooltipDescription() {
			return tooltipDescription+UtilText.parse(" [daddy.NamePos] apartment is located in this particular area.");
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Daddy.class))) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.DINER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
			}
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_SHOPPING_ARCADE = new AbstractPlaceType(
			"Shopping Arcade",
			"Although there are countless stores scattered all throughout Dominion, this arcade is well-known as the best place for shopping.",
			"dominion/shoppingArcadeIcon",
			PresetColour.BASE_GOLD,
			ShoppingArcadeDialogue.OUTSIDE,
			Encounter.DOMINION_STREET,
			"in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_STREET_HARPY_NESTS = new AbstractPlaceType(
			"Dominion Streets",
			"The Harpy Nests' intricate series of walkways and bridges criss-cross between the rooftops in this area, casting their shadows onto the streets below.",
			null,
			PresetColour.BASE_GREY,
			DominionPlaces.STREET_SHADED,
			Encounter.DOMINION_STREET,
			"in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK);
	
	public static final AbstractPlaceType DOMINION_HARPY_NESTS_ENTRANCE = new AbstractPlaceType(
			"Harpy Nests Entrance",
			"A large building, containing numerous elevators and winding stairs, links the Harpy Nests to the streets below.",
			"dominion/harpyNestIcon",
			PresetColour.BASE_MAGENTA,
			HarpyNestsDialogue.OUTSIDE,
			Encounter.DOMINION_STREET,
			"in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK);

	public static final AbstractPlaceType DOMINION_HELENA_HOTEL = new AbstractPlaceType(
			"The Golden Feather Hotel",
			"One of the most prestigious and well-known of all the hotels in Dominion, 'The Golden Feather' is owned by the harpy matriarch, Helena.",
			"dominion/demonHomeHelenaIcon",
			PresetColour.BASE_GOLD,
			DominionPlaces.HELENAS_HOTEL,
			null,
			"in the streets of Demon Home") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK);
	
	public static final AbstractPlaceType DOMINION_NIGHTLIFE_DISTRICT = new AbstractPlaceType(
			"Nightlife District",
			"While there are clubs, bars, and other such establishments found all throughout Dominion, the very best nightlife that Dominion can offer is found here.",
			"dominion/nightlifeIcon",
			PresetColour.BASE_PINK_LIGHT,
			NightlifeDistrict.OUTSIDE,
			Encounter.DOMINION_STREET,
			"in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_CITY_HALL = new AbstractPlaceType(
			"City hall",
			"Acting as a centre for regional government, the city hall handles the administrative affairs of not only Dominion, but of all of Lilith's Domain.",
			"dominion/townHallIcon",
			PresetColour.BASE_INDIGO,
			CityHall.OUTSIDE,
			Encounter.DOMINION_STREET,
			"in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_AUNTS_HOME = new AbstractPlaceType(
			"Lilaya's Home",
			"Lilaya's home is more of a mansion than a town house, and, due to its impressive size, stands out as a particularly impressive building in this area.",
			"dominion/homeIcon",
			PresetColour.BASE_BLUE_LIGHT,
			LilayaHomeGeneric.OUTSIDE,
			Encounter.DOMINION_STREET,
			"in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_SLAVER_ALLEY = new AbstractPlaceType(
			"Slaver Alley",
			"Although slavery is completely legal in Dominion, this main hub for all slave transactions is located in a very shady part of the city, being surrounded by dangerous back alleys.",
			"dominion/slaverAlleyIcon",
			PresetColour.BASE_CRIMSON,
			SlaverAlleyDialogue.OUTSIDE,
			null,
			"in the alleyways near Slaver Alley") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				return new ArrayList<>();
			}
			return SLAVER_ALLEY_ENTRANCE.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_RED_LIGHT_DISTRICT = new AbstractPlaceType(
			"Red Light District",
			"As easy as sex is to come by in Dominion, there's no guarantee that a partner has any skill in the bedroom. It's in this area that professional, obligation-free lovers can be hired.",
			"dominion/brothel",
			PresetColour.BASE_MAGENTA,
			RedLightDistrict.OUTSIDE,
			Encounter.DOMINION_STREET,
			"in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK);

	public static final AbstractPlaceType DOMINION_PARK = new AbstractPlaceType(
			"Park",
			"There are several large parks found throughout Dominion, all of which are fully open to the public.",
			"dominion/park",
			PresetColour.BASE_GREEN,
			DominionPlaces.PARK,
			Encounter.DOMINION_STREET,
			"in one of Dominion's parks") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_HOME_IMPROVEMENT = new AbstractPlaceType(
			"Argus's DIY Depot",
			"Supplying both DIY enthusiasts and professional construction firms alike, 'Argus's DIY Depot' takes the form of a pair of huge warehouses, situated in the middle of an expansive lumber yard.",
			"dominion/construction",
			PresetColour.BASE_ORANGE,
			HomeImprovements.OUTSIDE,
			Encounter.DOMINION_STREET,
			"in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_WAREHOUSES = new AbstractPlaceType(
			"Warehouse District",
			"While there are countless industrial buildings scattered throughout Dominion, the area with the largest concentration of them is in this dedicated warehouse district.",
			"dominion/warehouse",
			PresetColour.BASE_BROWN,
			DominionPlaces.WAREHOUSE_DISTRICT,
			Encounter.DOMINION_STREET,
			"in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	
	
	// Alleyways:
	
	public static final AbstractPlaceType DOMINION_BACK_ALLEYS_SAFE = new AbstractPlaceType(
			"Alleyways (Patrolled)",
			"While the vast majority of Dominion's alleyways are very dangerous, this particular section is very heavily patrolled by Enforcers, who ensure that muggers are of no threat to the general population.",
			"dominion/alleysIcon",
			PresetColour.BASE_GREY,
			DominionPlaces.BACK_ALLEYS_SAFE,
			Encounter.DOMINION_STREET,
			"in one of Dominion's backalleys"
			) {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				return new ArrayList<>();
			}
			return Util.newArrayListOfValues(
					new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, true)),
					new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
		}
	};
	
	public static final AbstractPlaceType DOMINION_BACK_ALLEYS = new AbstractPlaceType(
			"Alleyways",
			"Although offering shortcuts between main streets, the city's labyrinthine alleyways are very seldom travelled by anyone, as they're well-known to be inhabited by dangerous elements of society.",
			"dominion/alleysIcon",
			PresetColour.BASE_BLACK,
			DominionPlaces.BACK_ALLEYS,
			Encounter.DOMINION_ALLEY,
			"in one of Dominion's backalleys"
			).initDangerous();

	public static final AbstractPlaceType DOMINION_DARK_ALLEYS = new AbstractPlaceType(
			"Dark Alleyways",
			"The darkest depths of Dominion's twisting, maze-like alleyways are avoided even by the muggers who would usually call such places home...",
			"dominion/alleysDarkIcon",
			PresetColour.BASE_PURPLE,
			DominionPlaces.DARK_ALLEYS,
			Encounter.DOMINION_DARK_ALLEY,
			"in one of Dominion's dark alleyways"
			).initDangerous();
	
	public static final AbstractPlaceType DOMINION_ALLEYS_CANAL_CROSSING = new AbstractPlaceType(
			"Canal Crossing",
			"These alleyway crossings over the city's canal are considered to be very dangerous, and have the reputation as being the favourite haunts of dangerous desperados.",
			"dominion/bridge",
			PresetColour.BASE_BLUE_LIGHT,
			DominionPlaces.BACK_ALLEYS_CANAL,
			Encounter.DOMINION_ALLEY,
			"in one of Dominion's backalleys"
			).initDangerous();
	
	// Canals:
	
	public static final AbstractPlaceType DOMINION_CANAL = new AbstractPlaceType(
			"Canal",
			"Dominion's canal is safe enough for anyone on a barge or boat, but for those walking along the often-deserted and Enforcer-free towpaths, it's a different story entirely...",
			"dominion/canalIcon",
			PresetColour.BASE_BLUE_LIGHT,
			DominionPlaces.CANAL,
			Encounter.DOMINION_CANAL,
			"beside one of Dominion's canals"
			).initDangerous();
	
	public static final AbstractPlaceType DOMINION_CANAL_END = new AbstractPlaceType(
			"Canal",
			"The towpaths which run alongside Dominion's canal come to an abrupt end at the city outskirts.",
			"dominion/canalEndIcon",
			PresetColour.BASE_BLUE,
			DominionPlaces.CANAL_END,
			Encounter.DOMINION_CANAL,
			"beside one of Dominion's canals"
			).initDangerous();
	
	// Exits & entrances:
	
	public static final AbstractPlaceType DOMINION_EXIT_TO_SUBMISSION = new AbstractPlaceType(
			"Submission Entrance",
			"At each crossing between Dominion's canal and main streets, there exist Enforcer-guarded entrances to the undercity of Submission.",
			"dominion/submissionExit",
			PresetColour.BASE_TEAL,
			DominionPlaces.CITY_EXIT_SEWERS,
			null,
			"in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.RANDOM;
		}
	};
	
	public static final AbstractPlaceType DOMINION_EXIT_EAST = new AbstractPlaceType(
			"Dominion Exit",
			"Dominion's wide boulevards turn back into regular streets as they trail off into the city's surprisingly-small suburbs.",
			"dominion/exitEast",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			null,
			"in the streets of Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.NORTH;
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_EXIT_NORTH = new AbstractPlaceType(
			"Dominion Exit",
			"Dominion's wide boulevards turn back into regular streets as they trail off into the city's surprisingly-small suburbs.",
			"dominion/exitNorth",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			null,
			"in the streets of Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_EXIT_WEST = new AbstractPlaceType(
			"Dominion Exit",
			"Dominion's wide boulevards turn back into regular streets as they trail off into the city's surprisingly-small suburbs.",
			"dominion/exitWest",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			null,
			"in the streets of Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_EXIT_SOUTH = new AbstractPlaceType(
			"Dominion Exit",
			"Dominion's wide boulevards turn back into regular streets as they trail off into the city's surprisingly-small suburbs.",
			"dominion/exitSouth",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			null,
			"in the streets of Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	
	
	// Enforcer HQ:
	
	public static final AbstractPlaceType ENFORCER_HQ_CORRIDOR = new AbstractPlaceType(
			"Corridor",
			"Many doors line either side of this rather ordinary-looking corridor, each one marked with a different Enforcer division's name and speciality.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerHQDialogue.CORRIDOR,
			null,
			"in the Enforcer HQ") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, true, Subspecies.HUMAN)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_CELLS_CORRIDOR = new AbstractPlaceType(
			"Corridor",
			"This particular corridor doesn't have any distinguishing features to it.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerHQDialogue.CORRIDOR,//TODO
			null,
			"in the Enforcer HQ")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_WAITING_AREA = new AbstractPlaceType(
			"Waiting area",
			"Several low sofas, a few potted plants, and an air of tedious boredom make up this waiting area.",
			"dominion/enforcerHQ/waitingRoom",
			PresetColour.BASE_BROWN,
			EnforcerHQDialogue.WAITING_AREA,
			null,
			"in the Enforcer HQ")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_RECEPTION_DESK = new AbstractPlaceType(
			"Reception desk",
			"The Enforcer HQ's front desk is staffed by the bimbo cat-girl, Candi.",
			"dominion/enforcerHQ/receptionDesk",
			PresetColour.BASE_BLUE_LIGHT,
			EnforcerHQDialogue.RECEPTION_DESK,
			null,
			"in Candi's office")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_GUARDED_DOOR = new AbstractPlaceType(
			"Guarded door",
			"The door connecting the public waiting room to the rest of the Enforcer HQ is guarded by a particularly buff horse-boy.",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_CRIMSON,
			EnforcerHQDialogue.GUARDED_DOOR,
			null,
			"in the Enforcer HQ")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_LOCKED_DOOR = new AbstractPlaceType(
			"Locked door",
			"This internal door is firmly locked, barring passage to anyone not in possession of the required key.",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_CRIMSON,
			EnforcerHQDialogue.LOCKED_DOOR,
			null,
			"in the Enforcer HQ")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_BRAXS_OFFICE = new AbstractPlaceType(
			"Brax's Office",
			"Enforcers of the rank 'Inspector' are allowed their own office, and are permitted to decorate them as they see fit.",
			"dominion/enforcerHQ/braxsOffice",
			PresetColour.BASE_BLUE_DARK,
			BraxOffice.INTERIOR_BRAX,
			null,
			"in his office") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			inventory.addClothing(AbstractClothingType.generateClothing("dsg_eep_uniques_enfdjacket_brax", PresetColour.CLOTHING_BLACK, false));
			inventory.addClothing(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false));
			inventory.addClothing(AbstractClothingType.generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false));
		}
		@Override
		public boolean isItemsDisappear() {
			return false;
		}
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.braxEncountered)) {
				return BraxOffice.INTERIOR_BRAX_REPEAT;
			} else {
				return BraxOffice.INTERIOR_BRAX;
			}
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_OFFICE = new AbstractPlaceType(
			"Locked Office",
			"The door to this particular office is locked, leaving you wondering as to what could be contained within.",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_GREY,
			EnforcerHQDialogue.OFFICE,
			null,
			"in the Enforcer HQ")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_CELLS_OFFICE = new AbstractPlaceType(
			"Cells Office",
			"This small office is the place where all prisoners are checked in and out of the cells.",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_PURPLE,
			EnforcerHQDialogue.CELLS_OFFICE,
			null,
			"in the Enforcer HQ")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_CELL = new AbstractPlaceType(
			"Cell",
			"The cells in the Enforcer Headquarters are where prisoners are temporarily held until such time as they can be properly processed.",
			"dominion/enforcerHQ/cell",
			PresetColour.BASE_BROWN_DARK,
			EnforcerHQDialogue.CELL,
			null,
			"in the cells of the Enforcer HQ")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_ENTRANCE = new AbstractPlaceType(
			"Entranceway",
			"The entrance to the Enforcer HQ consists of a pair of soundproof glass doors.",
			"dominion/enforcerHQ/exit",
			PresetColour.BASE_RED,
			EnforcerHQDialogue.ENTRANCE,
			null,
			"")
			.initWeatherImmune();
	
	
	// Enforcer warehouse:
	
	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENTRANCE = new AbstractPlaceType(
			"Entrance",
			"The only entrance to the warhouse is guarded by a small, Enforcer-manned booth.",
			"dominion/enforcerWarehouse/exit",
			PresetColour.BASE_RED,
			EnforcerWarehouse.ENTRANCE,
			null,
			"in the Enforcer warehouse")
			.initDangerous()
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CORRIDOR = new AbstractPlaceType(
			"Corridor",
			"Numerous corridors, lined on both sides by stacked wooden crates, snake their way through the warehouse.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerWarehouse.CORRIDOR,
			null,
			"in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CLAIRE_WARNING = new AbstractPlaceType(
			"Corridor",
			"Numerous corridors, lined on both sides by stacked wooden crates, snake their way through the warehouse.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerWarehouse.CLAIRE_WARNING,
			null,
			"in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENCLOSURE = new AbstractPlaceType(
			"Enclosure",
			"A forgotten corner of the warehouse, enclosed on all sides by towering stacks of wooden crates.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerWarehouse.ENCLOSURE,
			null,
			"in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_PADS = new AbstractPlaceType(
			"Teleportation pad",
			"The teleportation pad which you and Claire arrived on is in this area.",
			"dominion/enforcerWarehouse/teleportPads",
			PresetColour.BASE_MAGENTA,
			EnforcerWarehouse.ENCLOSURE_TELEPORT_PADS,
			null,
			"in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_SHELVING = new AbstractPlaceType(
			"Shelves",
			"Several free-standing shelving units have been stacked up in this corner.",
			"dominion/enforcerWarehouse/shelving",
			PresetColour.BASE_PURPLE_LIGHT,
			EnforcerWarehouse.ENCLOSURE_SHELVING,
			null,
			"in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENFORCER_GUARD_POST = new AbstractPlaceType(
			"Enforcer guard post",
			"Scattered throughout the warehouse are several Enforcer guard posts, which consist of little more than a chair and table.",
			"dominion/enforcerWarehouse/enforcerGuardPost",
			PresetColour.BASE_BLUE_STEEL,
			EnforcerWarehouse.ENFORCER_GUARD_POST,
			null,
			"in the Enforcer warehouse")
			.initDangerous()
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES = new AbstractPlaceType(
			"Crates",
			"One or two of the crates in this area of the warehouse have not yet been sealed shut.",
			"dominion/enforcerWarehouse/crates",
			PresetColour.BASE_ORANGE,
			EnforcerWarehouse.CRATES,
			null,
			"in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_SEARCHED = new AbstractPlaceType(
			"Crates (searched)",
			"One or two of the crates in this area of the warehouse had not yet been sealed shut, which allowed you to search through them.",
			"dominion/enforcerWarehouse/cratesSearched",
			PresetColour.BASE_GREY,
			EnforcerWarehouse.CRATES,
			null,
			"in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_ARK = new AbstractPlaceType(
			"Crates",
			"One or two of the crates in this area of the warehouse have not yet been sealed shut.",
			"dominion/enforcerWarehouse/crates",
			PresetColour.BASE_ORANGE,
			EnforcerWarehouse.CRATES_ARK,
			null,
			"in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_ARK_SEARCHED = new AbstractPlaceType(
			"Crates (searched)",
			"One or two of the crates in this area of the warehouse had not yet been sealed shut, which allowed you to search through them.",
			"dominion/enforcerWarehouse/cratesSearched",
			PresetColour.BASE_GREY,
			EnforcerWarehouse.CRATES_ARK,
			null,
			"in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_LUST_WEAPON = new AbstractPlaceType(
			"'Top Secret' Crate",
			"One of the crates in this area has been marked as 'Top Secret'.",
			"dominion/enforcerWarehouse/cratesLustWeapon",
			PresetColour.BASE_PINK_DEEP,
			EnforcerWarehouse.CRATES_LUST_WEAPON,
			null,
			"in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK = new AbstractPlaceType(
			"Shelving",
			"Instead of the usual crates found all throughout the warehouse, this particular area contains shelves full of banned books and other such contraband writings.",
			"dominion/enforcerWarehouse/shelvingSpellBook",
			PresetColour.BASE_MAGENTA,
			EnforcerWarehouse.SHELVES_SPELL_BOOK,
			null,
			"in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK_SEARCHED = new AbstractPlaceType(
			"Shelving (searched)",
			"Instead of the usual crates found all throughout the warehouse, this particular area contains shelves full of banned books and other such contraband writings. You've already searched through them and found a spell book.",
			"dominion/enforcerWarehouse/shelvingSearched",
			PresetColour.BASE_GREY,
			EnforcerWarehouse.SHELVES_SPELL_BOOK,
			null,
			"in the Enforcer warehouse")
			.initWeatherImmune();
	
	
	// City hall:
	
	public static final AbstractPlaceType CITY_HALL_CORRIDOR = new AbstractPlaceType(
			"Corridor",
			"The marble corridors of Dominion's city hall allow the resident bureaucrats to easily stride from one office to another.",
			null,
			PresetColour.BASE_BLACK,
			CityHall.CITY_HALL_CORRIDOR,
			null,
			"in Dominion's city hall") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.OFFICE_WORKER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_ENTRANCE = new AbstractPlaceType(
			"Entrance",
			"The entrance to city hall takes the form of a pair of revolving glass doors; one of them is marked 'Exit', and the other, 'Entrance: no access'.",
			"dominion/cityHall/exit",
			PresetColour.BASE_RED,
			CityHall.CITY_HALL_FOYER,
			null,
			"in Dominion's city hall") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getDominionStormImmuneSpecies(true)));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType CITY_HALL_INFORMATION_DESK = new AbstractPlaceType(
			"Information Desk",
			"A circular desk, staffed by several receptionists, sits in the middle of the large entrance hall.",
			"dominion/cityHall/front_desk",
			PresetColour.BASE_BLUE_LIGHT,
			CityHall.CITY_HALL_INFORMATION_DESK,
			null,
			"in Dominion's city hall") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType CITY_HALL_WAITING_AREA = new AbstractPlaceType(
			"Waiting Room",
			"A large analogue clock hangs on the far side of the open-plan waiting room; the slow ticking of the second hand a constant reminder to all those who are present of the crippling inefficiency of the bureaucracy.",
			"dominion/cityHall/waiting_area",
			PresetColour.BASE_PURPLE_LIGHT,
			CityHall.CITY_HALL_WAITING_AREA,
			null,
			"in Dominion's city hall") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_OFFICE = new AbstractPlaceType(
			"Office",
			"The door to this particular office is marked 'Private', letting everybody know that absolutely nothing of importance happens inside.",
			"dominion/cityHall/office",
			PresetColour.BASE_ORANGE,
			CityHall.CITY_HALL_OFFICE,
			null,
			"in Dominion's city hall") {
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_STAIRS = new AbstractPlaceType(
			"Staircase",
			"The staircases to city hall's upper floors are marked as private, and are cordoned off by means of red rope barriers.",
			"dominion/cityHall/stairs",
			PresetColour.BASE_GREY,
			CityHall.CITY_HALL_STAIRS,
			null,
			"in Dominion's city hall") {
	}.initWeatherImmune();
	
	public static final AbstractPlaceType CITY_HALL_BUREAU_OF_DEMOGRAPHICS = new AbstractPlaceType(
			"Bureau of Demographics",
			"The 'Bureau of Demographics' consists of a small office adjoining a vast, library-like storage area.",
			"dominion/cityHall/officeDemographics",
			PresetColour.BASE_TEAL,
			CityHallDemographics.CITY_HALL_DEMOGRAPHICS_ENTRANCE,
			null,
			"in Dominion's city hall") {
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_ARCHIVES = new AbstractPlaceType(
			"Bureau of Demographics (Archives)",
			"The 'Bureau of Demographics' consists of a small office adjoining a vast, library-like storage area.",
			"dominion/cityHall/officeDemographicsArchives",
			PresetColour.BASE_BLUE,
			CityHallDemographics.CITY_HALL_DEMOGRAPHICS_ENTRANCE, // Player cannot enter this tile.
			null,
			"in Dominion's city hall") {
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_BUREAU_OF_PROPERTY_RIGHTS_AND_COMMERCE = new AbstractPlaceType(
			"Corridor",
			"The 'Bureau of Property and Commerce' is one of the largest and most well-funded departments in city hall, and consists of numerous interlinked offices and meeting rooms.",
			"dominion/cityHall/officeProperty",
			PresetColour.BASE_GOLD,
			CityHallProperty.CITY_HALL_PROPERTY_ENTRANCE,
			null,
			"in Dominion's city hall") {
	}.initWeatherImmune();

	
	// Home Improvements:
	
	public static final AbstractPlaceType HOME_IMPROVEMENTS_CORRIDOR = new AbstractPlaceType(
			"Aisles",
			"Wide, concrete-floored aisles run throughout the warehouse, giving customers plenty of room in which to wheel their trolleys past one another.",
			null,
			PresetColour.BASE_BLACK,
			HomeImprovements.CORRIDOR,
			null,
			"in 'Argus's DIY Depot'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_ENTRANCE = new AbstractPlaceType(
			"Entrance",
			"The entrance to city hall takes the form of a pair of revolving glass doors; one of them is marked 'Exit', and the other, 'Entrance: no access'.",
			"dominion/homeImprovements/exit",
			PresetColour.BASE_RED,
			HomeImprovements.ENTRANCE,
			null,
			"in 'Argus's DIY Depot'") {
		@Override
		public List<Population> getPopulation() {
			return HOME_IMPROVEMENTS_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_SHELVING_PREMIUM = new AbstractPlaceType(
			"Shelving (Premium)",
			"The shelving facing the main entrance is stocked full of 'premium-grade' paint, tools, and other miscellaneous DIY supplies.",
			"dominion/homeImprovements/shelving",
			PresetColour.BASE_GOLD,
			HomeImprovements.SHELVING_PREMIUM,
			null,
			"in 'Argus's DIY Depot'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_SHELVING_STANDARD = new AbstractPlaceType(
			"Shelving (Standard)",
			"The shelving which runs alongside the main aisles is stocked full of 'standard-grade' paint, tools, and other miscellaneous DIY supplies.",
			"dominion/homeImprovements/shelving",
			PresetColour.BASE_BROWN,
			HomeImprovements.SHELVING_STANDARD,
			null,
			"in 'Argus's DIY Depot'") {
		@Override
		public List<Population> getPopulation() {
			return HOME_IMPROVEMENTS_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_BUILDING_SUPPLIES = new AbstractPlaceType(
			"Building supplies",
			"Near the back of the warehouse, there are numerous shelving units stocked full of timber, tiles, piping, and all sorts of other building materials.",
			"dominion/homeImprovements/crates",
			PresetColour.BASE_ORANGE,
			HomeImprovements.BUILDING_SUPPLIES,
			null,
			"in 'Argus's DIY Depot'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_OFFICE = new AbstractPlaceType(
			"Manager's Office",
			"Argus, the owner and manager of this business, has his office situated close to the warehouse's entrance.",
			"dominion/homeImprovements/office",
			PresetColour.BASE_MAGENTA,
			HomeImprovements.OFFICE,
			null,
			"in Argus's office")
	.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_TOILETS = new AbstractPlaceType(
			"Toilets",
			"At the back corner of the warehouse, there are some toilets available for customers to use.",
			"dominion/homeImprovements/toilets",
			PresetColour.BASE_GREEN_DARK,
			HomeImprovements.TOILETS,
			null,
			"in the toilets at 'Argus's DIY Depot'")
	.initWeatherImmune();

	
	// Dominion Express:
	
	public static final AbstractPlaceType DOMINION_EXPRESS_CORRIDOR = new AbstractPlaceType(
			"Corridor",
			"This corridor runs the entire length of the warehouse, and effectively splits the open-plan storage area from the business's numerous offices.",
			null,
			PresetColour.BASE_BLACK,
			DominionExpress.CORRIDOR,
			null,
			"in the 'Dominion Express' warehouse")  {
		@Override
		public List<Population> getPopulation() {//TODO
			return Util.newArrayListOfValues(new Population(true, PopulationType.OFFICE_WORKER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_EXIT = new AbstractPlaceType(
			"Entrance",
			"A solitary reception desk is positioned to one side of the warehouse's entrance, with the secretary sitting behind it making sure that any visitors have a reason to be in here.",
			"dominion/dominionExpress/exit",
			PresetColour.BASE_RED,
			DominionExpress.ENTRANCE,
			null,
			"in the 'Dominion Express' warehouse")
	.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_STORAGE = new AbstractPlaceType(
			"Storage",
			"Half of the warehouse's floor space is reserved for the temporary storage of goods.",
			"dominion/dominionExpress/crates",
			PresetColour.BASE_ORANGE,
			DominionExpress.STORAGE,
			null,
			"in the 'Dominion Express' warehouse")
	.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_OFFICE = new AbstractPlaceType(
			"Office",
			"The day-to-day running of 'Dominion Express' is handled within the offices which line one side of the warehouse's corridor.",
			"dominion/dominionExpress/office",
			PresetColour.BASE_BLUE_LIGHT,
			DominionExpress.OFFICE,
			null,
			"in the 'Dominion Express' warehouse")
	.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_OFFICE_STABLE = new AbstractPlaceType(
			"Stable Mistress's office",
			"The office responsible for the care and management of the centaur slaves is, quite logically, located adjacent to their stables.",
			"dominion/dominionExpress/officeStable",
			PresetColour.BASE_TAN,
			DominionExpress.OFFICE_STABLE,
			null,
			"in the Stable Mistress's office at the 'Dominion Express' warehouse")
	.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_STABLES = new AbstractPlaceType(
			"Stables",
			"This large, single-story annex contains a vast array of stalls, each one being large enough to comfortably house a centaur slave.",
			"dominion/dominionExpress/stables",
			PresetColour.BASE_BROWN,
			DominionExpress.STABLES,
			null,
			"in the stables at the 'Dominion Express' warehouse")
	.initWeatherImmune();
	
	
	
	// Harpy Nests:
	
	public static final AbstractPlaceType HARPY_NESTS_WALKWAYS = new AbstractPlaceType(
			"Walkway",
			"The harpy nests are connected to one another by means of narrow wooden walkways built on top of Dominion's residential buildings.",
			null,
			PresetColour.BASE_BLACK,
			HarpyNestsDialogue.WALKWAY,
			Encounter.HARPY_NEST_WALKWAYS,
			"in the Harpy Nests") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				return super.getPopulation();
			} else {
				return Util.newArrayListOfValues(new Population(true, PopulationType.HARPY, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.HARPY_NEST, true)));
			}
		}
	};
	
	public static final AbstractPlaceType HARPY_NESTS_WALKWAYS_BRIDGE = new AbstractPlaceType(
			"Walkway Bridge",
			"Here and there, bridges span over the streets below, connecting one set of walkways with another.",
			"dominion/harpyNests/bridge",
			PresetColour.BASE_GREY,
			HarpyNestsDialogue.WALKWAY_BRIDGE,
			Encounter.HARPY_NEST_WALKWAYS,
			"in the Harpy Nests") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	};

	public static final AbstractPlaceType HARPY_NESTS_ENTRANCE_ENFORCER_POST = new AbstractPlaceType(
			"Enforcer post",
			"A well-staffed Enforcer outpost is required in order to keep the peace between countless quarrelsome harpies.",
			"dominion/harpyNests/exit",
			PresetColour.BASE_RED,
			HarpyNestsDialogue.ENTRANCE_ENFORCER_POST,
			null,
			"in the Harpy Nests") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.ENFORCER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, true, Subspecies.HUMAN)));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HARPY_NESTS_HELENAS_NEST = new AbstractPlaceType(
			"Helena's nest",
			"The stunningly beautiful harpy matriarch, Helena, rules over the largest of all the harpy nests.",
			"dominion/harpyNests/nestHelena",
			PresetColour.BASE_GOLD,
			HarpyNestHelena.HELENAS_NEST_EXTERIOR,
			null,
			"in Helena's nest"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	};
	
	public static final AbstractPlaceType HARPY_NESTS_HARPY_NEST_RED = new AbstractPlaceType(
			"Harpy nest",
			"Diana's nest consists primarily of angry red harpies; their feather colour an attempt to mimic the appearance of their sadistic leader.",
			"dominion/harpyNests/nestRed",
			PresetColour.BASE_CRIMSON,
			HarpyNestDominant.HARPY_NEST_DOMINANT,
			null,
			"in Diana's nest"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	};
	
	public static final AbstractPlaceType HARPY_NESTS_HARPY_NEST_PINK = new AbstractPlaceType(
			"Harpy nest",
			"Lexi's nest contains a disproportionate amount of harpy males, each of whom hangs around in the hopes of getting to fuck their sex-loving matriarch.",
			"dominion/harpyNests/nestPink",
			PresetColour.BASE_PINK_LIGHT,
			HarpyNestNympho.HARPY_NEST_NYMPHO,
			null,
			"in Lexi's nest"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	};
	
	public static final AbstractPlaceType HARPY_NESTS_HARPY_NEST_YELLOW = new AbstractPlaceType(
			"Harpy nest",
			"Brittany's nest has a considerable population of bleach-blonde-feathered, big-busted, bimbo harpies.",
			"dominion/harpyNests/nestYellow",
			PresetColour.BASE_YELLOW_LIGHT,
			HarpyNestBimbo.HARPY_NEST_BIMBO,
			null,
			"in Brittany's nest"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	};
	
			
	// Lilaya's home (ground floor):
	
	public static final AbstractPlaceType LILAYA_HOME_CORRIDOR = new AbstractPlaceType(
			"Corridor",
			"Immaculately-clean red carpet runs down the centre of each of the corridors in Lilaya's home, while fine paintings and masterfully-carved marble busts line the walls.",
			null,
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.CORRIDOR,
			Encounter.LILAYAS_HOME_CORRIDOR,
			"in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR = new AbstractPlaceType(
			"Room",
			"The outer rooms on the ground floor, including this one, have windows looking down onto either the streets of Dominion, or the private alleyways running around the house.",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_WINDOW,
			null,
			"in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
			return getAvailableLilayaRoomPlaceUpgrades(upgrades);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			return getLilayaRoomSVGString(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " G-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR = new AbstractPlaceType(
			"Garden Room",
			"The inner rooms on the ground floor are all connected to the private garden by means of patio doors.",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR,
			null,
			"in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
			return getAvailableLilayaRoomPlaceUpgrades(upgrades);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			return getLilayaRoomSVGString(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " GG-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR = new AbstractPlaceType(
			"Room",
			"The outer rooms on the first floor, including this one, have windows looking down onto either the streets of Dominion, or the private alleyways running around the house.",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_WINDOW,
			null,
			"in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
			return getAvailableLilayaRoomPlaceUpgrades(upgrades);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			return getLilayaRoomSVGString(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " F-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR = new AbstractPlaceType(
			"Garden Room",
			"The inner rooms on the first floor, including this one, have windows looking down onto the private garden.",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_GARDEN,
			null,
			"in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
			return getAvailableLilayaRoomPlaceUpgrades(upgrades);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			return getLilayaRoomSVGString(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " FG-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ARTHUR_ROOM = new AbstractPlaceType(
			"Arthur's Room",
			"As chosen by you, this room houses Lilaya's one-time lover and colleague, Arthur.",
			"dominion/lilayasHome/roomArthur",
			PresetColour.BASE_BLUE_STEEL,
			LilayaHomeGeneric.ROOM_ARTHUR,
			null,
			"in Arthur's Room"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_BIRTHING_ROOM = new AbstractPlaceType(
			"Room",
			"You aren't quite sure why Lilaya has a dedicated delivery room, but you assume that it must at one point have been for arcane research purposes.",
			"dominion/lilayasHome/roomBirthing",
			PresetColour.BASE_PINK,
			LilayaHomeGeneric.BIRTHING_ROOM,
			null,
			"in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_KITCHEN = new AbstractPlaceType(
			"Kitchen",
			"At the back of the house, there's a huge, well-equipped kitchen.",
			"dominion/lilayasHome/kitchen",
			PresetColour.BASE_TAN,
			LilayaHomeGeneric.KITCHEN,
			null,
			"in Lilaya's kitchen"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_LIBRARY = new AbstractPlaceType(
			"Library",
			"A huge library, containing row after row of book-filled shelving, is situated in one corner of the ground floor.",
			"dominion/lilayasHome/library",
			PresetColour.BASE_TEAL,
			Library.LIBRARY,
			null,
			"in Lilaya's library") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			inventory.addItem(AbstractItemType.generateItem(ItemType.getLoreBook(Subspecies.HALF_DEMON)));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_STAIR_UP = new AbstractPlaceType(
			"Staircase",
			"This staircase connects the ground and first floor of Lilaya's home, and has a small, secondary landing area half-way up.",
			"dominion/lilayasHome/stairsUp",
			PresetColour.BASE_GREEN_LIGHT,
			LilayaHomeGeneric.STAIRCASE_UP,
			null,
			"in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ENTRANCE_HALL = new AbstractPlaceType(
			"Entrance Hall",
			"Fine paintings and marble busts line the walls of this grand entrance hall, while a huge crystal chandelier hangs from the double-height ceiling overhead.",
			"dominion/lilayasHome/entranceHall",
			PresetColour.BASE_RED,
			LilayaHomeGeneric.ENTRANCE_HALL,
			null,
			"in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_LAB = new AbstractPlaceType(
			"Lilaya's Lab",
			"A room in one corner of the ground floor has been converted into a dedicated laboratory, in which Lilaya spends almost all of her time.",
			"dominion/lilayasHome/lab",
			PresetColour.BASE_GREEN_LIME,
			Lab.LAB,
			null,
			"in Lilaya's lab") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			inventory.addClothing(AbstractClothingType.generateClothing(ClothingType.SCIENTIST_EYES_SAFETY_GOGGLES, PresetColour.CLOTHING_BLACK, false));
		}
	}.initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_GARDEN = new AbstractPlaceType(
			"Garden",
			"This private garden is surrounded on all four side by the walls of Lilaya's house.",
			"dominion/lilayasHome/garden",
			PresetColour.BASE_GREEN,
			LilayaHomeGeneric.GARDEN,
			null,
			"in Lilaya's garden"
			).initItemsPersistInTile();
	
	public static final AbstractPlaceType LILAYA_HOME_FOUNTAIN = new AbstractPlaceType(
			"Fountain",
			"In the very centre of the garden, a huge, ornate water fountain happily bubbles away with a mind of its own.",
			"dominion/lilayasHome/fountain",
			PresetColour.BASE_BLUE_LIGHT,
			LilayaHomeGeneric.FOUNTAIN,
			null,
			"in Lilaya's garden"
			).initItemsPersistInTile();
	

	
	
	// Lilaya's home (first floor):

	public static final AbstractPlaceType LILAYA_HOME_ROOM_LILAYA = new AbstractPlaceType(
			"Lilaya's Room",
			"Lilaya's room is situated in a corner of the first floor, and is noticeably adjacent to Rose's...",
			"dominion/lilayasHome/roomLilaya",
			PresetColour.BASE_CRIMSON,
			LilayasRoom.ROOM_LILAYA,
			null,
			"in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_ROSE = new AbstractPlaceType(
			"Rose's Room",
			"Rose's room is situated in a corner of the first floor, and is noticeably adjacent to Lilaya's...",
			"dominion/lilayasHome/roomRose",
			PresetColour.BASE_PINK,
			LilayaHomeGeneric.ROOM_ROSE,
			null,
			"in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_PLAYER = new AbstractPlaceType(
			"Your Room",
			"The room given freely to you by Lilaya; this is the once place in Dominion in which you can truly have a care-free rest.",
			"dominion/lilayasHome/roomPlayer",
			PresetColour.BASE_AQUA,
			RoomPlayer.ROOM,
			null,
			"in your room"
			) {
				@Override
				public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
					return Util.newArrayListOfValues(
							PlaceUpgrade.LILAYA_PLAYER_ROOM_BED,
							PlaceUpgrade.LILAYA_PLAYER_ROOM_BATH);
				}
				@Override
				public boolean isAbleToBeUpgraded() {
					return true;
				}
			}.initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_STAIR_DOWN = new AbstractPlaceType(
			"Staircase",
			"This staircase connects the first and ground floor of Lilaya's home, and has a small, secondary landing area half-way down.",
			"dominion/lilayasHome/stairsDown",
			PresetColour.BASE_RED,
			LilayaHomeGeneric.STAIRCASE_DOWN,
			null,
			"in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	

	
	
	// Zaranix's home (ground floor):
	
	public static final AbstractPlaceType ZARANIX_GF_CORRIDOR = new AbstractPlaceType(
			"Corridor",
			"Numerous fine paintings, cushioned chairs, and well-crafted cabinets line the corridors of Zaranix's home.",
			null,
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.CORRIDOR,
			null,
			"in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeGroundFloor.CORRIDOR;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_STAIRS = new AbstractPlaceType(
			"Staircase",
			"This staircase connects the ground and first floor of Zaranix's home.",
			"dominion/zaranixHome/stairsDown",
			PresetColour.BASE_GREEN_LIGHT,
			ZaranixHomeGroundFloor.STAIRS,
			null,
			"in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.STAIRS;
				
			} else {
				return ZaranixHomeGroundFloor.STAIRS;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_ENTRANCE = new AbstractPlaceType(
			"Entrance",
			"A huge, crystal chandelier casts its bright, arcane-powered light over the entrance hall, and fine paintings in golden frames hang on each of the surrounding walls.",
			"dominion/zaranixHome/entranceHall",
			PresetColour.BASE_RED,
			ZaranixHomeGroundFloor.ENTRANCE,
			null,
			"in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.ENTRANCE;
				
			} else {
				return ZaranixHomeGroundFloor.ENTRANCE;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_LOUNGE = new AbstractPlaceType(
			"Lounge",
			"Several sofas and chairs are tastefully arranged around a low table in the centre of the room, while numerous bookcases and cabinets line the floral-wallpapered walls.",
			"dominion/zaranixHome/lounge",
			PresetColour.BASE_ORANGE,
			ZaranixHomeGroundFloor.LOUNGE,
			null,
			"in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.LOUNGE;
				
			} else {
				return ZaranixHomeGroundFloor.LOUNGE;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_ROOM = new AbstractPlaceType(
			"Room",
			"The door to this room is locked, and there's no sound of anyone within.",
			"dominion/zaranixHome/room",
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.ROOM,
			null,
			"in a room in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.ROOM;
				
			} else {
				return ZaranixHomeGroundFloor.ROOM;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_MAID = new AbstractPlaceType(
			"Corridor",
			"An ivory-skinned succubus, wearing a light pink maid's uniform, is busily dusting this area.",
			null,
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.CORRIDOR_MAID,
			null,
			"in Zaranix's Home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.CORRIDOR;
			} else {
				return ZaranixHomeGroundFloor.CORRIDOR_MAID;
			}
		}
		@Override
		public String getTooltipDescription() {
			if(isDangerous()) {
				return tooltipDescription;
			} else {
				return ZARANIX_GF_CORRIDOR.getTooltipDescription();
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_GARDEN_ROOM = new AbstractPlaceType(
			"Room",
			"A rather uninteresting room which links the garden to the rest of Zaranix's house.",
			"dominion/zaranixHome/room",
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.GARDEN_ROOM,
			null,
			"in a room in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN_ROOM;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN_ROOM;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_GARDEN = new AbstractPlaceType(
			"Garden",
			"A garden in which all sorts of strange and exotic-looking plants are being grown.",
			"dominion/zaranixHome/garden",
			PresetColour.BASE_GREEN,
			ZaranixHomeGroundFloor.GARDEN,
			null,
			"in Zaranix's garden"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN;
			}
		}
	};
	
	public static final AbstractPlaceType ZARANIX_GF_GARDEN_ENTRY = new AbstractPlaceType(
			"Garden",
			"This particular area is next to the fence separating Zaranix's garden from Dominion's streets.",
			"dominion/zaranixHome/entranceHall",
			PresetColour.BASE_GREEN,
			ZaranixHomeGroundFloor.GARDEN_ENTRY,
			null,
			"in Zaranix's garden"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN_ENTRY;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN_ENTRY;
			}
		}
	};
	
	
	
	// Zaranix's home (first floor):
	
	public static final AbstractPlaceType ZARANIX_FF_CORRIDOR = new AbstractPlaceType(
			"Corridor",
			"Numerous fine paintings, cushioned chairs, and well-crafted cabinets line the corridors of Zaranix's home.",
			null,
			PresetColour.BASE_GREY,
			ZaranixHomeFirstFloor.CORRIDOR,
			null,
			"in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeFirstFloor.CORRIDOR;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_STAIRS = new AbstractPlaceType(
			"Staircase",
			"This staircase connects the first and ground floor of Zaranix's home.",
			"dominion/zaranixHome/stairsDown",
			PresetColour.BASE_RED,
			ZaranixHomeFirstFloor.STAIRS,
			null,
			"in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.STAIRS;
				
			} else {
				return ZaranixHomeFirstFloor.STAIRS;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_OFFICE = new AbstractPlaceType(
			"Zaranix's Room",
			"Zaranix's office, which has been converted into a small-scale laboratory.",
			"dominion/zaranixHome/roomZaranix",
			PresetColour.BASE_GREEN_LIME,
			ZaranixHomeFirstFloor.ZARANIX_ROOM,
			null,
			"in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
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
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_ROOM = new AbstractPlaceType(
			"Room",
			"The door to this room is locked, and there's no sound of anyone within.",
			"dominion/zaranixHome/room",
			PresetColour.BASE_GREY,
			ZaranixHomeFirstFloor.ROOM,
			null,
			"in a room in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.ROOM;
				
			} else {
				return ZaranixHomeFirstFloor.ROOM;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_MAID = new AbstractPlaceType(
			"Corridor",
			"An ivory-skinned succubus, wearing a light pink maid's uniform, is busily dusting this area.",
			null,
			PresetColour.BASE_RED,
			ZaranixHomeFirstFloor.CORRIDOR_MAID,
			null,
			"in Zaranix's Home"){
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeFirstFloor.CORRIDOR_MAID;
			}
		}
		@Override
		public String getTooltipDescription() {
			if(isDangerous()) {
				return tooltipDescription;
			} else {
				return ZARANIX_FF_CORRIDOR.getTooltipDescription();
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	}.initWeatherImmune();
	
	
	
	
	// Angel's Kiss:

	public static final AbstractPlaceType ANGELS_KISS_CORRIDOR = new AbstractPlaceType(
			"Corridor",
			"The corridors of Angel's Kiss are carpeted in rich burgundy, while the walls are half-panelled in dark wood, half covered in light-blue floral wallpaper.",
			null,
			PresetColour.BASE_GREY,
			RedLightDistrict.ANGELS_KISS_CORRIDOR,
			null,
			"in Angel's Kiss"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_ENTRANCE = new AbstractPlaceType(
			"Entrance Hall",
			"A golden chandelier dangles from the high ceiling, illuminating the entrance hall's long, mahogany counter in a soft white light.",
			"dominion/angelsKiss/entrance",
			PresetColour.BASE_RED,
			RedLightDistrict.ANGELS_KISS_ENTRANCE,
			null,
			"in Angel's Kiss"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_STAIRCASE_UP = new AbstractPlaceType(
			"Staircase",
			"This staircase links the ground and first floors of Angel's Kiss.",
			"dominion/angelsKiss/stairsUp",
			PresetColour.BASE_GREEN_LIGHT,
			RedLightDistrict.ANGELS_KISS_STAIRS_UP,
			null,
			"in Angel's Kiss"
		).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_STAIRCASE_DOWN = new AbstractPlaceType(
			"Staircase",
			"This staircase links the first and ground floors of Angel's Kiss.",
			"dominion/angelsKiss/stairsDown",
			PresetColour.BASE_RED,
			RedLightDistrict.ANGELS_KISS_STAIRS_DOWN,
			null,
			"in Angel's Kiss"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_BEDROOM = new AbstractPlaceType(
			"Bedroom",
			"These bedrooms are where the establishment's business is conducted, and for that purpose, each one contains a clean, king-sized bed.",
			"dominion/angelsKiss/bedroom",
			PresetColour.BASE_PINK,
			RedLightDistrict.ANGELS_KISS_BEDROOM,
			null,
			"in Angel's Kiss"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_BEDROOM_BUNNY = new AbstractPlaceType(
			"Bunny's Bedroom",
			"This bedroom is home to the submissive prostitute, 'Bunny', who is the twin to her sister, 'Loppy'.",
			"dominion/angelsKiss/bedroomBunny",
			PresetColour.BASE_PINK_LIGHT,
			RedLightDistrict.ANGELS_KISS_BEDROOM_BUNNY,
			null,
			"in Bunny's Bedroom"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_BEDROOM_LOPPY = new AbstractPlaceType(
			"Loppy's Bedroom",
			"This bedroom is home to the dominant prostitute, 'Loppy', who is the twin to her sister, 'Bunny'.",
			"dominion/angelsKiss/bedroomLoppy",
			PresetColour.BASE_PURPLE,
			RedLightDistrict.ANGELS_KISS_BEDROOM_LOPPY,
			null,
			"in Loppy's Bedroom"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_OFFICE = new AbstractPlaceType(
			"Angel's Office",
			"The room in which Angel does all of the paperwork required of her as the red light district's 'Enforcer-sanctioned administration centre'.",
			"dominion/angelsKiss/office",
			PresetColour.BASE_BLUE_LIGHT,
			RedLightDistrict.ANGELS_KISS_OFFICE,
			null,
			"in Angel's Office"
			).initWeatherImmune();
	
	
	
	
	// Shopping arcade:
	
	public static final AbstractPlaceType SHOPPING_ARCADE_PATH = new AbstractPlaceType(
			"Arcade",
			"The main thoroughfares running through the shopping arcade are flanked on both sides by all different sorts of shops.",
			null,
			PresetColour.BASE_BLACK,
			ShoppingArcadeDialogue.ARCADE,
			null,
			"in the Shopping Arcade") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
			}
			pop.add(new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, true, Subspecies.HUMAN)));
			return pop;
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_GENERIC_SHOP = new AbstractPlaceType(
			"Shop",
			"One of the many nondescript shops in the arcade, this particular store doesn't offer anything that's worth your time or money.",
			"dominion/shoppingArcade/genericShop",
			PresetColour.BASE_BLACK,
			ShoppingArcadeDialogue.GENERIC_SHOP,
			null,
			"in the Shopping Arcade") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isExtendedWorkTime()) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
			} else {
				return new ArrayList<>();
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_RALPHS_SHOP = new AbstractPlaceType(
			"Ralph's Snacks",
			"A shop specialising in the sale of food, drink, and other miscellaneous items. The friendly namesake of 'Ralph's Snacks' is a muscular, brown-haired greater horse-boy.",
			"dominion/shoppingArcade/ralphShop",
			PresetColour.BASE_TEAL,
			RalphsSnacks.EXTERIOR,
			null,
			"in his store"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_NYANS_SHOP = new AbstractPlaceType(
			"Nyan's Clothing Emporium",
			"The two-story shop, 'Nyan's Clothing Emporium', is the largest store in all of the arcade.",
			"dominion/shoppingArcade/nyanShop",
			PresetColour.BASE_ROSE,
			ClothingEmporium.EXTERIOR,
			null,
			"in her store"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_VICKYS_SHOP = new AbstractPlaceType(
			"Arcane Arts",
			"Specialising in selling arcane weaponry and associated supplies, 'Arcane Arts' is run by a particularly fierce-looking wolf-girl named Vicky.",
			"dominion/shoppingArcade/vickyShop",
			PresetColour.BASE_MAGENTA,
			ArcaneArts.EXTERIOR,
			null,
			"in her store"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_KATES_SHOP = new AbstractPlaceType(
			"Succubi's Secrets",
			"With boarded-up windows and peeling paintwork, this beauty salon appears to be derelict at first glance, but a closer inspection reveals an 'open for business' sign hanging on the door.",
			"dominion/shoppingArcade/kateShop",
			PresetColour.BASE_PINK,
			SuccubisSecrets.EXTERIOR,
			null,
			"in her beauty salon"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_ASHLEYS_SHOP = new AbstractPlaceType(
			"Dream Lover",
			"The only store you've seen in Dominion which specialises in gifts for others.",
			"dominion/shoppingArcade/ashleyShop",
			PresetColour.BASE_LILAC_LIGHT,
			DreamLover.EXTERIOR,
			null,
			"in their store"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_ANTIQUES = new AbstractPlaceType(
			"Antiques",
			"An antiques shop, selling an eclectic mix of creaky old furniture, obsolete arcane instruments, and all manner of outdated miscellaneous items.",
			"dominion/shoppingArcade/antiques",
			PresetColour.BASE_BROWN,
			ShoppingArcadeDialogue.ANTIQUES,
			null,
			"in an antique shop"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_SUPPLIER_DEPOT = new AbstractPlaceType(
			"Supplier Depot",
			"This 'Supplier Depot' looks to have seen better days, with the interior looking to be both run down and completely deserted.",
			"dominion/shoppingArcade/supplierDepot",
			PresetColour.BASE_CRIMSON,
			SupplierDepot.EXTERIOR,
			null,
			"in the supplier depot") {
		@Override
		public Colour getColour() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return PresetColour.BASE_GREEN;
			} else {
				return PresetColour.BASE_CRIMSON;
			}
		}
		@Override
		public String getTooltipDescription() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return "With the Dobermanns dealt with, this 'Supplier Depot' is once more functioning as the hub through which many of the Shopping Arcade's stores purchase their goods in bulk.";
			} else {
				return tooltipDescription;
			}
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return getSVGOverride("dominion/shoppingArcade/supplierDepot", PresetColour.BASE_GREEN);
			} else {
				return SVGString;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_PIXS_GYM = new AbstractPlaceType(
			"Pix's Playground",
			"A huge, multi-story gym, 'Pix's Playground' is both owned and run by a particularly energetic border-collie-girl.",
			"dominion/shoppingArcade/gym",
			PresetColour.BASE_GOLD,
			PixsPlayground.GYM_EXTERIOR,
			null,
			"in her gym"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_ENTRANCE = new AbstractPlaceType(
			"Exit",
			"These large, glass doors lead back out into Dominion's streets.",
			"dominion/shoppingArcade/exit",
			PresetColour.BASE_RED,
			ShoppingArcadeDialogue.ENTRY,
			null,
			"in the Shopping Arcade"
			).initWeatherImmune();
	
	
	
	
	// Supplier Depot:
	
	public static final AbstractPlaceType SUPPLIER_DEPOT_CORRIDOR = new AbstractPlaceType(
			"Corridor",
			"A dimly-lit corridor, with sounds of laughter coming from the far end.",
			null,
			PresetColour.BASE_BLACK,
			SupplierDepot.SUPPLIER_DEPOT_CORRIDOR,
			null,
			"in the supplier depot"
			) {
		@Override
		public String getTooltipDescription() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return "With the Dobermanns dealt with, the corridor in the rear of the building has been renovated, and is now both clean and well-lit.";
			} else {
				return tooltipDescription;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SUPPLIER_DEPOT_ENTRANCE = new AbstractPlaceType(
			"Reception Area",
			"A derelict and very dusty reception area. There's no sign of life in here.",
			"dominion/shoppingArcade/exit",
			PresetColour.BASE_GREY,
			SupplierDepot.SUPPLIER_DEPOT_RECEPTION,
			null,
			"in the supplier depot") {
		@Override
		public boolean isPopulated() {
			return Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP);
		}
		@Override
		public String getTooltipDescription() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return "With the Dobermanns dealt with, this 'Supplier Depot' is once more functioning as the hub through which many of the Shopping Arcade's stores purchase their goods in bulk.";
			} else {
				return tooltipDescription;
			}
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return getSVGOverride("dominion/shoppingArcade/exit", PresetColour.BASE_GREEN);
			} else {
				return SVGString;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SUPPLIER_DEPOT_STORAGE_ROOM = new AbstractPlaceType(
			"Storage Room",
			"Numerous crates, filled with miscellaneous goods of all sorts, fill this storage room.",
			"dominion/shoppingArcade/supplierStorageRoom",
			PresetColour.BASE_ORANGE,
			SupplierDepot.SUPPLIER_DEPOT_STORAGE_ROOM,
			null,
			"in the supplier depot"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType SUPPLIER_DEPOT_OFFICE = new AbstractPlaceType(
			"Office",
			"Numerous offices, now vandalised and filled with rubbish, are located at the back of the building.",
			"dominion/shoppingArcade/supplierOffice",
			PresetColour.BASE_CRIMSON,
			SupplierDepot.SUPPLIER_DEPOT_OFFICE,
			null,
			"in the supplier depot") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP);
		}
		@Override
		public String getTooltipDescription() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return "With the Dobermanns dealt with, the offices in the rear of the building have been renovated, and are used by the Supplier Depot's many salespersons.";
			} else {
				return tooltipDescription;
			}
		}
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.suppliersEncountered)) {
				return SupplierDepot.SUPPLIER_DEPOT_OFFICE_REPEAT;
				
			} else {
				return SupplierDepot.SUPPLIER_DEPOT_OFFICE;
			}
		}
	}.initWeatherImmune();
	
	
	
	// Slaver Alley:
	
	public static final AbstractPlaceType SLAVER_ALLEY_PATH = new AbstractPlaceType(
			"Alleyway",
			"The alleyways running through Slaver Alley are nothing like those found in the rest of Dominion, as they are busy, clean, and most importantly of all, very safe.",
			null,
			PresetColour.BASE_BLACK,
			SlaverAlleyDialogue.ALLEYWAY,
			null,
			"in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
			}
			pop.add(new Population(false, PopulationType.PRIVATE_SECURITY_GUARD, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_FEMALES = new AbstractPlaceType(
			"A Woman's Touch",
			"A store specialising in selling well-trained and obedient slave girls.",
			"dominion/slaverAlley/marketStallFemale",
			PresetColour.BASE_PINK_LIGHT,
			SlaverAlleyDialogue.MARKET_STALL_FEMALE,
			null,
			"in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_MALES = new AbstractPlaceType(
			"Iron & Steel",
			"From manual labourers to eye-pleasing models, this store specialises in selling all different sorts of male slaves.",
			"dominion/slaverAlley/marketStallMale",
			PresetColour.BASE_BLUE_STEEL,
			SlaverAlleyDialogue.MARKET_STALL_MALE,
			null,
			"in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_ANAL = new AbstractPlaceType(
			"The Rear Entrance",
			"Specialising in selling slaves who have trained to love anal sex, this store is one of three which surrounds the lewd statue of Marlel.",
			"dominion/slaverAlley/marketStallAnal",
			PresetColour.BASE_ORANGE,
			SlaverAlleyDialogue.MARKET_STALL_ANAL,
			null,
			"in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_VAGINAL = new AbstractPlaceType(
			"White Lilies",
			"One of the three stores surrounding the statue of the fallen angel, Marlel, 'White Lillies' specialises in selling female virgins.",
			"dominion/slaverAlley/marketStallVaginal",
			PresetColour.BASE_PINK,
			SlaverAlleyDialogue.MARKET_STALL_VAGINAL,
			null,
			"in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_ORAL = new AbstractPlaceType(
			"Viva Voce",
			"'Viva Voce' specialises in selling slaves trained to love oral sex, and is one of three specialist stores found surrounding the statue of Marlel.",
			"dominion/slaverAlley/marketStallOral",
			PresetColour.BASE_BLUE_LIGHT,
			SlaverAlleyDialogue.MARKET_STALL_ORAL,
			null,
			"in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STATUE = new AbstractPlaceType(
			"Statue of the Fallen Angel",
			"Standing atop a high plinth, this statue of the fallen angel, Marlel, shows her in a highly-corrupted and incredibly lewd form.",
			"dominion/slaverAlley/marketStallStatue",
			PresetColour.BASE_BLACK,
			SlaverAlleyDialogue.MARKET_STALL_STATUE,
			null,
			"in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_MARKET_STALL_EXCLUSIVE = new AbstractPlaceType(
			"Slave Rental Store",
			"Quite a number of the stores in Slaver Alley do not directly sell slaves, but instead rent them out to businesses looking to fill a temporary gap in labour.",
			"dominion/slaverAlley/marketStallExclusive",
			PresetColour.BASE_GREY,
			SlaverAlleyDialogue.MARKET_STALL_EXCLUSIVE,
			null,
			"in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_MARKET_STALL_BULK = new AbstractPlaceType(
			"Zaibatsu Exchange",
			"By far the largest store in all of Slaver Alley, the 'Zaibatsu Exchange' is controlled by a powerful slaver conglomerate, and refuses to do business with non-members.",
			"dominion/slaverAlley/marketStallBulk",
			PresetColour.BASE_BLUE,
			SlaverAlleyDialogue.MARKET_STALL_BULK,
			null,
			"in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_CAFE = new AbstractPlaceType(
			"Cafe",
			"Numerous cafes are scattered throughout Slaver Alley, providing a place for shoppers to rest and replenish their energy.",
			"dominion/slaverAlley/marketStallCafe",
			PresetColour.BASE_BROWN,
			SlaverAlleyDialogue.MARKET_STALL_CAFE,
			null,
			"in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
			}
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_AUCTIONING_BLOCK = new AbstractPlaceType(
			"Auctioning Block",
			"A huge wooden platform sits in the middle of an expansive square, and it's upon this that public auctions are held.",
			"dominion/slaverAlley/auctionBlock",
			PresetColour.BASE_GOLD,
			SlaverAlleyDialogue.AUCTION_BLOCK,
			null,
			"in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_PUBLIC_STOCKS = new AbstractPlaceType(
			"Public Stocks",
			"A series of public-use stocks are positioned immediately in front of the entrance to Slaver Alley, serving as a reminder of what happens to disobedient slaves.",
			"dominion/slaverAlley/stocks",
			PresetColour.BASE_TAN,
			SlaverAlleyDialogue.PUBLIC_STOCKS,
			null,
			"in the stocks at Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_BOUNTY_HUNTERS = new AbstractPlaceType(
			"Bounty Hunter Lodge",
			"A tavern in which bounty hunters can be contracted to track down runaway slaves.",
			"dominion/slaverAlley/bountyHunters",
			PresetColour.BASE_RED_DARK,
			SlaverAlleyDialogue.BOUNTY_HUNTERS,
			null,
			"in Slaver Alley"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_SLAVERY_ADMINISTRATION = new AbstractPlaceType(
			"Slavery Administration",
			"The main administrative hub for all matters related to the ownership of slaves.",
			"dominion/slaverAlley/slaveryAdministration",
			PresetColour.BASE_PURPLE,
			SlaveryAdministration.SLAVERY_ADMINISTRATION_EXTERIOR,
			null,
			"in Slaver Alley"){
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.SLAVERY_ADMINISTRATION_CELLS);
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_SCARLETTS_SHOP = new AbstractPlaceType(
			"Scarlett's Shop",
			"",
			"dominion/slaverAlley/scarlettsStall",
			PresetColour.BASE_CRIMSON,
			ScarlettsShop.SCARLETTS_SHOP_EXTERIOR,
			null,
			"in Slaver Alley"){
		@Override
		public Colour getColour() {
			if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				return PresetColour.BASE_BLACK;
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR)) {
				return PresetColour.BASE_GOLD;
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR)) {
				return PresetColour.BASE_GREY;
			}
			return PresetColour.BASE_CRIMSON;
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			return getSVGOverride("dominion/slaverAlley/scarlettsStall", getColour());
		}
		@Override
		public String getName() {
			if(Main.game.isStarted()) {
				if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
					return "Abandoned Shop";
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR)) {
					return "Helena's Boutique";
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR)) {
					return "Unnamed Shop";
				}
			}
			return "Scarlett's Shop";
		}
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) { // Scarlett owns the shop:
				return ScarlettsShop.SCARLETTS_SHOP_EXTERIOR;
				
			} else { // Helena owns the shop:
				return ScarlettsShop.HELENAS_SHOP_EXTERIOR;
			}
		}
		@Override
		public String getTooltipDescription() {
			if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				return "This slave shop was abandoned by Helena after you refused to sell Scarlett to her...";
				
			} else if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
				return "A slave shop run by the harpy, 'Scarlett'. Unlike all of the other shops in Slaver Alley, hers has absolutely no slaves for sale...";
				
			} else {
				return "Scarlett's matriarch, Helena, has taken over management of this slave shop.";
			}
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isStarted()) {
				if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_6_ADVERTISING)) {
					if(Main.game.getNpc(Helena.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP) {
						return Util.newArrayListOfValues(
								new Population(true, PopulationType.HARPY, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.HARPY_NEST, true)),
								new Population(true, PopulationType.SHOPPER, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, true)),
								new Population(true, PopulationType.FAN, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
					} else {
						return Util.newArrayListOfValues(new Population(true, PopulationType.FAN, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
					}
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) && !Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
					if(Main.game.getNpc(Helena.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP) {
						return Util.newArrayListOfValues(new Population(true, PopulationType.FAN, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
					} else {
						return Util.newArrayListOfValues(new Population(true, PopulationType.FAN, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, true)));
					}
				}
			}
			return new ArrayList<>();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_ENTRANCE = new AbstractPlaceType(
			"Gateway",
			"Slaver Alley's single entrance and exit is guarded by a pair of horse-boys who keep a keen watch for any runaway slaves.",
			"dominion/slaverAlley/exit",
			PresetColour.BASE_RED,
			SlaverAlleyDialogue.GATEWAY,
			null,
			"in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(
					new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, true)),
					new Population(true, PopulationType.PRIVATE_SECURITY_GUARD, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_DESERTED_ALLEYWAY = new AbstractPlaceType(
			"Deserted alleyway",
			"A narrow alleyway snakes its way behind several shops, before coming to a sudden dead end.",
			"dominion/slaverAlley/desertedAlleyway",
			PresetColour.BASE_BLACK,
			SlaverAlleyDialogue.DESERTED_ALLEYWAY,
			null,
			"in Slaver Alley").initWeatherImmune(Weather.MAGIC_STORM);
	
	
	// Watering hole:
	
	public static final AbstractPlaceType WATERING_HOLE_ENTRANCE = new AbstractPlaceType(
			"Entrance",
			"The entrance to the club, 'The Watering Hole', is guarded by a pair of zebra-boy bouncers.",
			"dominion/nightLife/exit",
			PresetColour.BASE_RED,
			NightlifeDistrict.WATERING_HOLE_ENTRANCE,
			null,
			"in 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_MAIN_AREA = new AbstractPlaceType(
			"The Watering Hole",
			"The club is absolutely packed with people, who are busy drinking, chatting, and making out with one another.",
			null,
			PresetColour.BASE_BLUE_LIGHT,
			NightlifeDistrict.WATERING_HOLE_MAIN,
			null,
			"in 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return WATERING_HOLE_ENTRANCE.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_SEATING_AREA = new AbstractPlaceType(
			"Seating Area",
			"Areas set aside on the edge of the club offer relatively peaceful places for revellers to sit down and talk with one another.",
			"dominion/nightLife/seatingArea",
			PresetColour.BASE_BLUE,
			NightlifeDistrict.WATERING_HOLE_SEATING,
			null,
			"in 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_VIP_AREA = new AbstractPlaceType(
			"VIP Area",
			"Guarded by a pair of muscular lion bouncers, the club's VIP area consists of numerous semi-circular booths, each one housing a polished black marble table and a long, curved leather sofa.",
			"dominion/nightLife/vipArea",
			PresetColour.BASE_PURPLE,
			NightlifeDistrict.WATERING_HOLE_VIP,
			null,
			"in 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.VIP, PopulationDensity.SEVERAL,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.CAT_MORPH_LION, SubspeciesSpawnRarity.FOUR_COMMON),
							new Value<>(Subspecies.HORSE_MORPH_ZEBRA, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_BAR = new AbstractPlaceType(
			"Bar",
			"The club's bar is perpetually busy, with the numerous zebra and lion-girl barmaids serving order after order.",
			"dominion/nightLife/bar",
			PresetColour.BASE_ORANGE,
			NightlifeDistrict.WATERING_HOLE_BAR,
			null,
			"in 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_DANCE_FLOOR = new AbstractPlaceType(
			"Dance Floor",
			"The central dance floor is the liveliest, and loudest, place in the entire club.",
			"dominion/nightLife/danceFloor",
			PresetColour.BASE_PINK_DEEP,
			NightlifeDistrict.WATERING_HOLE_DANCE_FLOOR,
			null,
			"in 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_TOILETS = new AbstractPlaceType(
			"Toilets",
			"The club's toilets are quite large, and contain numerous stalls and sinks.",
			"dominion/nightLife/toilets",
			PresetColour.BASE_GREEN_DARK,
			NightlifeDistrict.WATERING_HOLE_TOILETS,
			null,
			"in the toilets of 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, false)));
		}
	}.initWeatherImmune();
	
	
	// Daddy's apartment:
	
	public static final AbstractPlaceType DADDY_APARTMENT_ENTRANCE = new AbstractPlaceType(
			"Entrance Hall",
			"The entrance hall to the apartment.",
			"dominion/daddy/entranceHall",
			PresetColour.BASE_GREEN,
			DaddyDialogue.PLACE_ENTRANCE_HALL,
			null,
			"in the entrance hall of Daddy's apartment"
		).initWeatherImmune();

	public static final AbstractPlaceType DADDY_APARTMENT_LOUNGE = new AbstractPlaceType(
			"Lounge",
			"The apartment's lounge.",
			"dominion/daddy/lounge",
			PresetColour.BASE_ORANGE,
			DaddyDialogue.PLACE_LOUNGE,
			null,
			"in the lounge of Daddy's apartment"
		).initWeatherImmune();

	public static final AbstractPlaceType DADDY_APARTMENT_KITCHEN = new AbstractPlaceType(
			"Kitchen",
			"The apartment's kitchen.",
			"dominion/daddy/kitchen",
			PresetColour.BASE_TAN,
			DaddyDialogue.PLACE_KITCHEN,
			null,
			"in the kitchen of Daddy's apartment"
		).initWeatherImmune();

	public static final AbstractPlaceType DADDY_APARTMENT_BEDROOM = new AbstractPlaceType(
			"Bedroom",
			"The apartment's bedroom.",
			"dominion/daddy/bedroom",
			PresetColour.BASE_CRIMSON,
			DaddyDialogue.PLACE_BEDROOM,
			null,
			"in Daddy's bedroom"
		).initWeatherImmune();
	
	
	// Helena's apartment:
	
	public static final AbstractPlaceType HELENA_APARTMENT_HALLWAY = new AbstractPlaceType(
			"Hallway",
			"The a wide, carpeted hallways connects the rooms in Helena's apartment.",
			null,
			PresetColour.BASE_BLACK,
			HelenaApartment.PLACE_HALLWAY,
			null,
			"in the hallway of Helena's apartment"
		) {
			@Override
			public List<Population> getPopulation() {
				return Util.newArrayListOfValues(new Population(true, PopulationType.MAID, PopulationDensity.OCCASIONAL, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.FOUR_COMMON))));
			}
		}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_BALCONY = new AbstractPlaceType(
			"Balcony",
			"This large, wooden-decked balcony overlooks the streets of Dominion.",
			null,
			PresetColour.BASE_BLUE_LIGHT,
			HelenaApartment.PLACE_BALCONY,
			null,
			"on the balcony at Helena's apartment"
		).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_BLUE)
		.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_ENTRANCE = new AbstractPlaceType(
			"Entrance Hall",
			"The entrance hall to Helena's penthouse apartment is of an impressive size, and is tastefully decorated in a modern style.",
			"dominion/helenaApartment/entranceHall",
			PresetColour.BASE_GREEN,
			HelenaApartment.PLACE_ENTRANCE_HALL,
			null,
			"in the entrance hall of Helena's apartment"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.MAID, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_HELENA_BEDROOM = new AbstractPlaceType(
			"Helena's Bedroom",
			"Helena's bedroom is of an impressive size and is luxuriously furnished.",
			"dominion/helenaApartment/bedroomHelena",
			PresetColour.BASE_GOLD,
			HelenaApartment.PLACE_HELENA_BEDROOM,
			null,
			"in Helena's bedroom"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_SCARLETT_BEDROOM = new AbstractPlaceType(
			"Scarlett's Bedroom",
			"Scarlett's bedroom is of an impressive size and is luxuriously furnished.",
			"dominion/helenaApartment/bedroomScarlett",
			PresetColour.BASE_CRIMSON,
			HelenaApartment.PLACE_SCARLETT_BEDROOM,
			null,
			"in Scarlett's bedroom"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_BEDROOM = new AbstractPlaceType(
			"Guest Bedroom",
			"A couple of spare bedrooms stand ready to receive any guests who might happen to stay here overnight.",
			"dominion/helenaApartment/bedroom",
			PresetColour.BASE_YELLOW,
			HelenaApartment.PLACE_BEDROOM,
			null,
			"in one of the guest bedrooms in Helena's apartment"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_BATHROOM = new AbstractPlaceType(
			"Ensuite Bathroom",
			"Each of the bedrooms in Helena's apartment have their own private ensuite bathroom.",
			"dominion/helenaApartment/bathroom",
			PresetColour.BASE_BLUE_LIGHT,
			HelenaApartment.PLACE_BATHROOM,
			null,
			"in one of the bathrooms in Helena's apartment"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_OFFICE = new AbstractPlaceType(
			"Study Room",
			"Situated in the middle of Helena's apartment, her personal study room is where she goes to think and spend time to herself.",
			"dominion/helenaApartment/office",
			PresetColour.BASE_BROWN,
			HelenaApartment.PLACE_OFFICE,
			null,
			"in the study room in Helena's apartment"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_KITCHEN = new AbstractPlaceType(
			"Kitchen",
			"The kitchen in Helena's apartment is staffed at all times by an excellent professional chef.",
			"dominion/helenaApartment/kitchen",
			PresetColour.BASE_ORANGE,
			HelenaApartment.PLACE_KITCHEN,
			null,
			"in the kitchen in Helena's apartment"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CHEF, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_DINING_ROOM = new AbstractPlaceType(
			"Dining Room",
			"A large table, surrounded by a dozen chairs, forms the centrepiece of this large dining room.",
			"dominion/helenaApartment/diningRoom",
			PresetColour.BASE_BLUE_STEEL,
			HelenaApartment.PLACE_DINING_ROOM,
			null,
			"in the dining room in Helena's apartment"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.MAID, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_LOUNGE = new AbstractPlaceType(
			"Lounge",
			"The huge, open-plan lounge in Helena's apartment is home to numerous comfortable sofas.",
			"dominion/helenaApartment/lounge",
			PresetColour.BASE_INDIGO,
			HelenaApartment.PLACE_LOUNGE,
			null,
			"in the lounge in Helena's apartment"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.MAID, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_HOT_TUB = new AbstractPlaceType(
			"Hot Tub",
			"In one corner of the balcony, there's a large, arcane-powered hot tub.",
			"dominion/helenaApartment/hotTub",
			PresetColour.BASE_RED_LIGHT,
			HelenaApartment.PLACE_HOT_TUB,
			null,
			"in the hot tub on the balcony of Helena's apartment"
		).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_BLUE)
		.initWeatherImmune();
	
	
	// Submission:

	public static final AbstractPlaceType SUBMISSION_ENTRANCE = new AbstractPlaceType(
			"Enforcer Checkpoint",
			"In order to keep the imp population from running rampant up into Dominion, every one of Submission's many entrances takes the form of a well-manned Enforcer checkpoint.",
			"submission/submissionExit",
			PresetColour.BASE_BROWN,
			SubmissionGenericPlaces.SEWER_ENTRANCE,
			null,
			"in Submission") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.ENFORCER, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.ALLIGATOR_MORPH, SubspeciesSpawnRarity.THREE_UNCOMMON),
							new Value<>(Subspecies.CAT_MORPH, SubspeciesSpawnRarity.FOUR_COMMON),
							new Value<>(Subspecies.DOG_MORPH, SubspeciesSpawnRarity.FOUR_COMMON),
							new Value<>(Subspecies.FOX_MORPH, SubspeciesSpawnRarity.THREE_UNCOMMON),
							new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.THREE_UNCOMMON),
							new Value<>(Subspecies.RABBIT_MORPH, SubspeciesSpawnRarity.THREE_UNCOMMON),
							new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.THREE_UNCOMMON),
							new Value<>(Subspecies.WOLF_MORPH, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_WALKWAYS = new AbstractPlaceType(
			"Walkways",
			"Alongside the subterranean waterways which run through most of Submission, well-maintained wooden walkways have been built up against the grey stone walls.",
			null,
			PresetColour.BASE_BLACK,
			SubmissionGenericPlaces.WALKWAYS,
			null,
			"in Submission") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.SUBMISSION, false)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.SUBMISSION, false)));
			}
			pop.addAll(SUBMISSION_ENTRANCE.getPopulation());
			return pop;
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType SUBMISSION_TUNNELS = new AbstractPlaceType(
			"Tunnels",
			"The shaded alcoves and wide pipe openings make these dark, claustrophobic tunnels the perfect place for an ambush...",
			"submission/tunnelsIcon",
			PresetColour.BASE_BLACK,
			SubmissionGenericPlaces.TUNNEL,
			Encounter.SUBMISSION_TUNNELS,
			"in Submission"
			).initDangerous()
			.initWeatherImmune();

	public static final AbstractPlaceType SUBMISSION_BAT_CAVERNS = new AbstractPlaceType(
			"Bat Caverns",
			"Submission's walkways come to an end here, and down a deep, dark opening, a series of steep stone steps lead down into the bat caverns below.",
			"submission/batCaverns",
			PresetColour.BASE_BLUE,
			SubmissionGenericPlaces.BAT_CAVERNS,
			null,
			"in Submission"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_RAT_WARREN = new AbstractPlaceType(
			"Rat Warrens",
			"The entrance to the rat warrens can be found in this area, and takes the form of a stone archway, sealed off by a pair of heavy oaken doors.",
			"submission/ratWarren",
			PresetColour.BASE_BROWN_DARK,
			SubmissionGenericPlaces.RAT_WARREN,
			null,
			"in Submission"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_GAMBLING_DEN = new AbstractPlaceType(
			"Gambling Den",
			"The 'Gambling Den' is by far the most popular attraction in all of Submission, and is the primary destination for visitors from Dominion.",
			"submission/gamblingDen",
			PresetColour.BASE_GOLD,
			SubmissionGenericPlaces.GAMBLING_DEN,
			null,
			"in Submission"
			) {
		@Override
		public List<Population> getPopulation() {
			return SUBMISSION_WALKWAYS.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_LILIN_PALACE = new AbstractPlaceType(
			"Lyssieth's Palace",
			"The palace of the elder Lilin, Lyssieth, is located in the far corner of Submission.",
			"submission/lilinPalace",
			PresetColour.BASE_PURPLE,
			SubmissionGenericPlaces.LILIN_PALACE,
			null,
			"in Submission"
			).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
			.initWeatherImmune()
			.initTeleportPermissions(TeleportPermissions.NONE);
	
	public static final AbstractPlaceType SUBMISSION_LILIN_PALACE_GATE = new AbstractPlaceType(
			"Lyssieth's Palace Gate",
			"The palace gates are well guarded by a group of half-demons.",
			"submission/gate",
			PresetColour.BASE_PURPLE_LIGHT,
			SubmissionGenericPlaces.LILIN_PALACE_GATE,
			null,
			"in Submission") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.GUARD, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(new Value<>(Subspecies.HALF_DEMON, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
	.initWeatherImmune()
	.initTeleportPermissions(TeleportPermissions.NONE);
	
	public static final AbstractPlaceType SUBMISSION_LILIN_PALACE_CAVERN = new AbstractPlaceType(
			"Cavern",
			"Lyssieth's palace is located on the far side of a huge cavern, the floor of which steadily slopes downwards to the front gates.",
			null,
			PresetColour.BASE_GREY,
			SubmissionGenericPlaces.LILIN_PALACE_CAVERN,
			null,
			"in Submission"
			).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
			.initWeatherImmune();
	
	
	
	
	// Alpha succubus imp fortress:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_ALPHA = new AbstractPlaceType(
			"Imp Fortress",
			"A crude, walled fortress, constructed upon a raised mound of rock, sits in the middle of a huge underground cave.",
			"submission/impFortress1",
			PresetColour.BASE_CRIMSON,
			SubmissionGenericPlaces.IMP_FORTRESS_ALPHA,
			null,
			"in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
				return getSVGOverride("submission/impFortress1", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress1", PresetColour.BASE_CRIMSON);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_ALPHA = new AbstractPlaceType(
			"Imp Tunnels",
			"These tunnels are particularly dangerous, as they're home to hostile groups of wandering imps.",
			"submission/impTunnels1Icon",
			PresetColour.BASE_RED,
			SubmissionGenericPlaces.TUNNEL,
			Encounter.SUBMISSION_TUNNELS,
			"in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
				return getSVGOverride("submission/impTunnels1Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels1Icon", PresetColour.BASE_RED);
		}
	}.initDangerous()
	.initWeatherImmune();

	public static final AbstractPlaceType FORTRESS_ALPHA_ENTRANCE = new AbstractPlaceType(
			"Gateway",
			"The front gates of the fortress offer access back out into Submission.",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpFortressDialogue.ENTRANCE,
			null,
			"in the Alpha Imp Fortress"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_ALPHA_COURTYARD = new AbstractPlaceType(
			"Courtyard",
			"Separating the gateway from the fortress's wooden keep, there's nothing but a deserted, squalid courtyard.",
			null,
			PresetColour.BASE_BLACK,
			ImpFortressDialogue.COURTYARD,
			null,
			"in the Alpha Imp Fortress"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_ALPHA_KEEP = new AbstractPlaceType(
			"Keep",
			"A crudely-constructed keep serves as the residence for this particular fortress's ruler.",
			"submission/impFortress/keep",
			PresetColour.BASE_CRIMSON,
			ImpFortressDialogue.KEEP,
			null,
			"in the Alpha Imp Fortress"
			).initDangerous()
			.initWeatherImmune();

	
	
	// Imp citadel:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_DEMON = new AbstractPlaceType(
			"Imp Citadel",
			"Huge, ceiling-height stone walls form the outer fortifications of a mighty imp citadel.",
			"submission/impFortress2",
			PresetColour.BASE_PURPLE,
			SubmissionGenericPlaces.IMP_FORTRESS_DEMON,
			null,
			"in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				return getSVGOverride("submission/impFortress2", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress2", PresetColour.BASE_PURPLE_DARK);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_DEMON = new AbstractPlaceType(
			"Imp Tunnels",
			"These tunnels are particularly dangerous, as they're home to hostile groups of wandering imps.",
			"submission/impTunnels2Icon",
			PresetColour.BASE_PURPLE,
			SubmissionGenericPlaces.TUNNEL,
			Encounter.SUBMISSION_TUNNELS,
			"in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				return getSVGOverride("submission/impTunnels2Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels2Icon", PresetColour.BASE_PURPLE);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_ENTRANCE = new AbstractPlaceType(
			"Gateway",
			"The huge stone gateway serves as the single point of access between the citadel and the cavern beyond.",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpCitadelDialogue.ENTRANCE,
			null,
			"in the Dark Siren's citadel") {
		@Override
		public List<Population> getPopulation() {
			if(ImpCitadelDialogue.isImpsDefeated() || ImpCitadelDialogue.isDefeated()) {
				return new ArrayList<>();
			}
			return Util.newArrayListOfValues(new Population(true, PopulationType.GUARD, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.IMP_ALPHA, SubspeciesSpawnRarity.TWO_RARE),
							new Value<>(Subspecies.IMP, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_COURTYARD = new AbstractPlaceType(
			"Courtyard",
			"The main keep is separated from the outer fortifications by means of an expansive, paved courtyard.",
			null,
			PresetColour.BASE_BLACK,
			ImpCitadelDialogue.COURTYARD,
			null,
			"in the Dark Siren's citadel") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_ENTRANCE.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_WELL = new AbstractPlaceType(
			"Well",
			"A limitless source of fresh water, such as that provided by this well, is invaluable for any fortress's defenders.",
			"submission/impFortress/well",
			PresetColour.BASE_BLUE_LIGHT,
			ImpCitadelDialogue.WELL,
			null,
			"in the Dark Siren's citadel") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_ENTRANCE.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_KEEP = new AbstractPlaceType(
			"Keep",
			"The citadel's main keep has been dug and carved out of the solid rock face of one of the cavern's walls.",
			"submission/impFortress/keep",
			PresetColour.BASE_PURPLE,
			ImpCitadelDialogue.KEEP,
			null,
			"in the Dark Siren's citadel") {
		@Override
		public boolean isDangerous() {
			return Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_CELLS = new AbstractPlaceType(
			"Cells",
			"Carved into the cavern's walls, these cells offer a place in which prisoners may be kept.",
			"submission/impFortress/cells",
			PresetColour.BASE_TEAL,
			ImpCitadelDialogue.CELLS,
			null,
			"in the Dark Siren's citadel") {
		@Override
		public List<Population> getPopulation() {
			if(ImpCitadelDialogue.isImpsDefeated() || ImpCitadelDialogue.isDefeated()) {
				return new ArrayList<>();
			}
			return Util.newArrayListOfValues(new Population(true, PopulationType.GUARD, PopulationDensity.FEW,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.IMP_ALPHA, SubspeciesSpawnRarity.TWO_RARE),
							new Value<>(Subspecies.IMP, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_LAB = new AbstractPlaceType(
			"Laboratory",
			"A large stone structure has been constructed on one side of the courtyard, and has the sole purpose of serving as a specialised laboratory.",
			"submission/impFortress/laboratory",
			PresetColour.BASE_GREEN_LIME,
			ImpCitadelDialogue.LABORATORY,
			null,
			"in the Dark Siren's citadel") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_CELLS.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_TREASURY = new AbstractPlaceType(
			"Treasury",
			"Every citadel's ruler needs a place in which they can safely store their precious belongings.",
			"submission/impFortress/treasury",
			PresetColour.BASE_GOLD,
			ImpCitadelDialogue.TREASURY,
			null,
			"in the Dark Siren's citadel") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_CELLS.getPopulation();
		}
	}.initWeatherImmune();
	
	
	
	// Female seducer imp fortress:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_FEMALES = new AbstractPlaceType(
			"Imp Fortress",
			"A crude, walled fortress, constructed upon a raised mound of rock, sits in the middle of a huge underground cave.",
			"submission/impFortress3",
			PresetColour.BASE_PINK,
			SubmissionGenericPlaces.IMP_FORTRESS_FEMALES,
			null,
			"in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
				return getSVGOverride("submission/impFortress3", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress3", PresetColour.BASE_PINK);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_FEMALES = new AbstractPlaceType(
			"Imp Tunnels",
			"These tunnels are particularly dangerous, as they're home to hostile groups of wandering imps.",
			"submission/impTunnels3Icon",
			PresetColour.BASE_PINK_LIGHT,
			SubmissionGenericPlaces.TUNNEL,
			Encounter.SUBMISSION_TUNNELS,
			"in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
				return getSVGOverride("submission/impTunnels3Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels3Icon", PresetColour.BASE_PINK_LIGHT);
		}
	}.initDangerous()
	.initWeatherImmune();

	public static final AbstractPlaceType FORTRESS_FEMALES_ENTRANCE = new AbstractPlaceType(
			"Gateway",
			"The front gates of the fortress offer access back out into Submission.",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpFortressDialogue.ENTRANCE,
			null,
			"in the Female Imp Fortress"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_FEMALES_COURTYARD = new AbstractPlaceType(
			"Courtyard",
			"Separating the gateway from the fortress's wooden keep, there's nothing but a deserted, squalid courtyard.",
			null,
			PresetColour.BASE_BLACK,
			ImpFortressDialogue.COURTYARD,
			null,
			"in the Female Imp Fortress"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_FEMALES_KEEP = new AbstractPlaceType(
			"Keep",
			"A crudely-constructed keep serves as the residence for this particular fortress's ruler.",
			"submission/impFortress/keep",
			PresetColour.BASE_PINK,
			ImpFortressDialogue.KEEP,
			null,
			"in the Female Imp Fortress"
			).initDangerous()
			.initWeatherImmune();

	
	
	// Incubus imp fortress:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_MALES = new AbstractPlaceType(
			"Imp Fortress",
			"A walled fortress, constructed upon a raised mound of rock, sits in the middle of a huge underground cave.",
			"submission/impFortress4",
			PresetColour.BASE_BLUE,
			SubmissionGenericPlaces.IMP_FORTRESS_MALES,
			null,
			"in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
				return getSVGOverride("submission/impFortress4", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress4", PresetColour.BASE_BLUE);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_MALES = new AbstractPlaceType(
			"Imp Tunnels",
			"These tunnels are particularly dangerous, as they're home to hostile groups of wandering imps.",
			"submission/impTunnels4Icon",
			PresetColour.BASE_BLUE_LIGHT,
			SubmissionGenericPlaces.TUNNEL,
			Encounter.SUBMISSION_TUNNELS,
			"in Submission") {
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
				return getSVGOverride("submission/impTunnels4Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels4Icon", PresetColour.BASE_BLUE_LIGHT);
		}
	}.initDangerous()
	.initWeatherImmune();

	public static final AbstractPlaceType FORTRESS_MALES_ENTRANCE = new AbstractPlaceType(
			"Gateway",
			"The front gates of the fortress offer access back out into Submission.",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpFortressDialogue.ENTRANCE,
			null,
			"in the Male Imp Fortress"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_MALES_COURTYARD = new AbstractPlaceType(
			"Courtyard",
			"Separating the gateway from the fortress's wooden keep, there's a deserted courtyard, which is home to numerous archery targets and straw dummies.",
			null,
			PresetColour.BASE_BLACK,
			ImpFortressDialogue.COURTYARD,
			null,
			"in the Male Imp Fortress"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_MALES_KEEP = new AbstractPlaceType(
			"Keep",
			"This imp fortress's keep takes the form of a very distinctly-Japanese styled single-story building.",
			"submission/impFortress/keep",
			PresetColour.BASE_BLUE,
			ImpFortressDialogue.KEEP,
			null,
			"in the Male Imp Fortress"
			).initDangerous()
			.initWeatherImmune();
	
	
	
	
	// Lyssieth's palace:
	
	public static final AbstractPlaceType LYSSIETH_PALACE_CORRIDOR = new AbstractPlaceType(
			"Corridor",
			"The vast corridors of Lyssieth's palace as just as ostentatiously luxurious as you'd expect from the residence of a direct daughter of Lilith herself.",
			null,
			PresetColour.BASE_GREY,
			LyssiethPalaceDialogue.CORRIDOR,
			null,
			"in Lyssieth's Palace") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.MAID, PopulationDensity.COUPLE,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TWO_RARE),
							new Value<>(Subspecies.HALF_DEMON, SubspeciesSpawnRarity.FOUR_COMMON))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_WINDOWS = new AbstractPlaceType(
			"Windows",
			"The corridors which branch off to the left and right of the main entrance hall are lined with a series of narrow windows, which overlook the cavern beyond.",
			null,
			PresetColour.BASE_GREY_DARK,
			LyssiethPalaceDialogue.WINDOWS,
			null,
			"in Lyssieth's Palace") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
	.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_ENTRANCE = new AbstractPlaceType(
			"Entrance",
			"The entrance hall to Lyssieth's palace is staggeringly opulent, and is in complete contrast to the dark, drab exterior.",
			"submission/lyssiethsPalace/entrance",
			PresetColour.BASE_RED,
			LyssiethPalaceDialogue.ENTRANCE,
			null,
			"in Lyssieth's Palace") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_ROOM = new AbstractPlaceType(
			"Room",
			"Plush sofas, extravagantly-carved coffee tables, and even the odd piano can be found in these drawing rooms.",
			"submission/lyssiethsPalace/lounge",
			PresetColour.BASE_PINK,
			LyssiethPalaceDialogue.ROOM,
			null,
			"in Lyssieth's Palace"
			) {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_HALL = new AbstractPlaceType(
			"Hall",
			"In each wing of the palace, there is a long, extravagantly-furnished dining hall, which Lyssieth uses to entertain her particularly-important guests.",
			"submission/lyssiethsPalace/throneRoom",
			PresetColour.BASE_ORANGE,
			LyssiethPalaceDialogue.HALL,
			null,
			"in Lyssieth's Palace"
			) {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_OFFICE = new AbstractPlaceType(
			"Lyssieth's Office",
			"Lyssieth can almost always be found role-playing as an important executive in her luxuriously-furnished office.",
			"submission/lyssiethsPalace/office",
			PresetColour.BASE_GOLD,
			LyssiethPalaceDialogue.LYSSIETH_OFFICE_ENTER,
			null,
			"in Lyssieth's Palace"
			).initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_SIREN_OFFICE = new AbstractPlaceType(
			"Meraxis's Office",
			"The room through which one must pass to see Lyssieth has been converted into an office-cum-waiting room, and is staffed by none other than her daughter, Meraxis.",
			"submission/lyssiethsPalace/officeSiren",
			PresetColour.BASE_CRIMSON,
			LyssiethPalaceDialogue.SIREN_OFFICE,
			null,
			"in Lyssieth's Palace"
			).initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_STAIRS_1 = new AbstractPlaceType(
			"Staircase",
			"These staircases lead up to the rooms of the first floor, in which Lyssieth and her staff have their private bedchambers.",
			"submission/lyssiethsPalace/staircase",
			PresetColour.BASE_GREEN,
			LyssiethPalaceDialogue.STAIRCASE,
			null,
			"in Lyssieth's Palace") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_STAIRS_2 = new AbstractPlaceType(
			"Staircase",
			"These staircases lead up to the rooms of the first floor, in which Lyssieth and her staff have their private bedchambers.",
			"submission/lyssiethsPalace/staircase",
			PresetColour.BASE_GREEN,
			LyssiethPalaceDialogue.STAIRCASE,
			null,
			"in Lyssieth's Palace") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	
	
	// Bat caverns:

	public static final AbstractPlaceType BAT_CAVERN_ENTRANCE = new AbstractPlaceType(
			"Winding Staircase",
			"This long, winding staircase has been carved out of solid rock, and leads back up to the main walkways of Submission.",
			"submission/batCaverns/cavernStaircase",
			PresetColour.BASE_GREEN,
			BatCaverns.STAIRCASE,
			null,
			"in the Bat Caverns"
			).initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_DARK = new AbstractPlaceType(
			"Dark Cavern",
			"The oppressive, inky blackness of the bat caverns is kept at bay by a softly glowing moss which covers the entire floor.",
			null,
			PresetColour.BASE_GREY,
			BatCaverns.CAVERN_DARK,
			Encounter.BAT_CAVERN,
			"in the Bat Caverns"
			).initDangerous()
			.initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_LIGHT = new AbstractPlaceType(
			"Bioluminescent Cavern",
			"The moss carpet in this particular part of the cavern thrives in amongst bioluminescent fungi of all shapes, sizes, and colours, and forms trailing paths which wind through the area.",
			"submission/batCaverns/cavernBioluminescent",
			PresetColour.BASE_AQUA,
			BatCaverns.CAVERN_LIGHT,
			Encounter.BAT_CAVERN,
			"in the Bat Caverns"
			).initDangerous()
			.initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_RIVER = new AbstractPlaceType(
			"Underground River",
			"A slow-moving underground river carves its way through the bat caverns; it's cool, dark depths prove to be impenetrable to what little light there is given off by the bioluminescent lichen.",
			"submission/batCaverns/cavernRiver",
			PresetColour.BASE_BLUE,
			BatCaverns.RIVER,
			Encounter.BAT_CAVERN,
			"in the Bat Caverns"
			).initDangerous()
			.initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_RIVER_CROSSING = new AbstractPlaceType(
			"Mushroom Bridge",
			"A pair of tree-sized bioluminescent mushrooms have been shaped into a horizontal, weaving pattern in order to form a living bridge across the river's dark depths.",
			"submission/batCaverns/cavernBridge",
			PresetColour.BASE_TEAL,
			BatCaverns.RIVER_BRIDGE,
			Encounter.BAT_CAVERN,
			"in the Bat Caverns"
			).initDangerous()
			.initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_RIVER_END = new AbstractPlaceType(
			"Underground River End",
			"Where the water-side path ends, the river drops away into a pitch-black abyss. A finely meshed metal grate has been built to save anyone from being swept down into the bottomless depths below.",
			"submission/batCaverns/cavernRiverEnd",
			PresetColour.BASE_BLUE_DARK,
			BatCaverns.RIVER_END,
			Encounter.BAT_CAVERN,
			"in the Bat Caverns"
			).initDangerous()
			.initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_SLIME_QUEEN_LAIR = new AbstractPlaceType(
			"Slime Lake",
			"A gigantic underground lake is illuminated by the bioluminescent fungi forests which line its banks. Out in the middle of the still, black waters, there sits a small, mushroom-covered island.",
			"submission/batCaverns/cavernLake",
			PresetColour.BASE_PINK_LIGHT,
			BatCaverns.SLIME_LAKE,
			Encounter.BAT_CAVERN,
			"beside Slime Lake"
			).initDangerous()
			.initWeatherImmune();
	
	
	
	// Slime queen's island tower:

	public static final AbstractPlaceType SLIME_QUEENS_LAIR_CORRIDOR = new AbstractPlaceType(
			"Corridor",
			"A thick burgundy-and-gold rug runs down the middle of the corridor, while the stone walls to either side are covered in thick fabric tapestries.",
			null,
			PresetColour.BASE_GREY,
			SlimeQueensLair.CORRIDOR,
			null,
			"in the Slime Queen's tower"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ENTRANCE = new AbstractPlaceType(
			"Entrance Hall",
			"A heavy, iron-barred, oaken door forms the means by which one may come and go from this tower.",
			"submission/slimeQueensLair/entranceHall",
			PresetColour.BASE_RED,
			SlimeQueensLair.ENTRANCE,
			null,
			"in the Slime Queen's tower"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_STAIRS_UP = new AbstractPlaceType(
			"Spiral Staircase",
			"A narrow spiral staircase leads up to the first floor of the tower.",
			"submission/slimeQueensLair/staircase",
			PresetColour.BASE_GREEN,
			SlimeQueensLair.STAIRCASE_UP,
			null,
			"in the Slime Queen's tower"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_STAIRS_DOWN = new AbstractPlaceType(
			"Spiral Staircase",
			"A narrow spiral staircase leads down to the ground floor of the tower.",
			"submission/slimeQueensLair/staircase",
			PresetColour.BASE_RED,
			SlimeQueensLair.STAIRCASE_DOWN,
			null,
			"in the Slime Queen's tower"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ROOM = new AbstractPlaceType(
			"Bedroom",
			"The place in which one or more of the tower's guards rest, this bedroom houses a neatly made four-poster bed, as well as the usual bedroom furnishings.",
			"submission/slimeQueensLair/room",
			PresetColour.BASE_BLUE_LIGHT,
			SlimeQueensLair.ROOM,
			null,
			"in the Slime Queen's tower"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_STORAGE_VATS = new AbstractPlaceType(
			"Distillery",
			"The huge distillation device located in this room is the source of not only all of the transformative slime fluids in Submission, but also that of the popular drink, 'Slime Quencher'.",
			"submission/slimeQueensLair/storageVats",
			PresetColour.BASE_ORANGE,
			SlimeQueensLair.STORAGE_VATS,
			null,
			"in the Slime Queen's tower") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			for(int i=0; i<15; i++) {
				inventory.addItem(AbstractItemType.generateItem(ItemType.SEX_INGREDIENT_SLIME_QUENCHER));
			}
			for(int i=0; i<5; i++) {
				inventory.addItem(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_SLIME));
			}
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ENTRANCE_GUARDS = new AbstractPlaceType(
			"Guard Post",
			"This area is home to a chest-height wooden barricade, which has been constructed from wall-to-wall.",
			"submission/slimeQueensLair/guards",
			PresetColour.BASE_RED,
			SlimeQueensLair.GUARD_POST,
			null,
			"in the Slime Queen's tower"
			) {
		@Override
		public boolean isDangerous() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsDefeated) && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ROYAL_GUARD = new AbstractPlaceType(
			"Royal Guard Post",
			"A powerful, purple-hued incubus-slime guards this particular stretch of the corridor.",
			"submission/slimeQueensLair/royalGuards",
			PresetColour.BASE_PURPLE,
			SlimeQueensLair.ROYAL_GUARD_POST,
			null,
			"in the Slime Queen's tower"
			) {
		@Override
		public boolean isDangerous() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeated) && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_SLIME_QUEEN = new AbstractPlaceType(
			"Bed Chamber",
			"The slime queen's bed chamber houses a colossal four-poster bed, as well as a huge bath, which is filled with a considerable amount of translucent pink liquid.",
			"submission/slimeQueensLair/bedChamber",
			PresetColour.BASE_PINK,
			SlimeQueensLair.BED_CHAMBER,
			null,
			"in the Slime Queen's tower"
			).initWeatherImmune();
	
	
	
	
	// Gambling Den:
	
	public static final AbstractPlaceType GAMBLING_DEN_CORRIDOR = new AbstractPlaceType(
			"Gambling Den",
			"The walls of the Gambling Den's spacious atrium are lined with countless slot machines, while numerous tables and chairs have been set out down the centre.",
			null,
			PresetColour.BASE_BLACK,
			GamblingDenDialogue.CORRIDOR,
			null,
			"in the Gambling Den") {
		@Override
		public List<Population> getPopulation() {
			Map<Subspecies, SubspeciesSpawnRarity> popComponent = new HashMap<>(Subspecies.getWorldSpecies(WorldType.SUBMISSION, false));
			Subspecies.getWorldSpecies(WorldType.DOMINION, false).forEach((key, value) -> popComponent.merge(key, value, (v1, v2) -> v1));
			popComponent.remove(Subspecies.IMP);
			popComponent.remove(Subspecies.IMP_ALPHA);
			return Util.newArrayListOfValues(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, popComponent));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType GAMBLING_DEN_ENTRANCE = new AbstractPlaceType(
			"Entrance",
			"The wooden doors of the Gambling Den's front entrance are always left propped open, offering easy access for the establishment's many patrons.",
			"submission/gamblingDen/entrance",
			PresetColour.BASE_GREEN,
			GamblingDenDialogue.ENTRANCE,
			null,
			"in the Gambling Den") {
		@Override
		public List<Population> getPopulation() {
			return GAMBLING_DEN_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType GAMBLING_DEN_OFFICE = new AbstractPlaceType(
			"Axel's Office",
			"Axel's office is situated next to the main entrance, and is locked when not in use.",
			"submission/gamblingDen/office",
			PresetColour.BASE_ORANGE,
			GamblingDenDialogue.OFFICE,
			null,
			"in the Gambling Den") {
	}.initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_TRADER = new AbstractPlaceType(
			"Roxy's Box",
			"'Roxy's Box' is a rather over-priced pawn shop, and offers goods that can be found at much reduced prices up in Dominion.",
			"submission/gamblingDen/trader",
			PresetColour.BASE_TEAL,
			RoxysShop.TRADER,
			null,
			"in the Gambling Den"
			).initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_GAMBLING = new AbstractPlaceType(
			"Dice Poker Tables",
			"Dice poker is one of the Gambling Den's prime attractions, and the many tables set aside for this game are almost always fully occupied.",
			"submission/gamblingDen/gambling",
			PresetColour.BASE_GOLD,
			GamblingDenDialogue.GAMBLING,
			null,
			"in the Gambling Den") {
		@Override
		public List<Population> getPopulation() {
			return GAMBLING_DEN_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_PREGNANCY_ROULETTE = new AbstractPlaceType(
			"Pregnancy Roulette",
			"The game 'pregnancy roulette' is run by the horse-girl, Epona, from behind a long wooden counter that's been set into the wall.",
			"submission/gamblingDen/referee",
			PresetColour.BASE_PINK,
			PregnancyRoulette.PREGNANCY_ROULETTE,
			null,
			"in the Gambling Den") {
		@Override
		public List<Population> getPopulation() {
			return GAMBLING_DEN_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_PREGNANCY = new AbstractPlaceType(
			"Breeding Stalls",
			"It's here where the willing, male players of 'pregnancy roulette' get on with their game.",
			"submission/gamblingDen/normalPregnancy",
			PresetColour.BASE_BLUE_LIGHT,
			GamblingDenDialogue.PREGNANCY_ROULETTE_MALE_STALLS,
			null,
			"in the Gambling Den"
			).initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_FUTA_PREGNANCY = new AbstractPlaceType(
			"Futa Breeding Stalls",
			"It's here where the willing, futanari players of 'pregnancy roulette' get on with their game.",
			"submission/gamblingDen/futaPregnancy",
			PresetColour.BASE_PINK_LIGHT,
			GamblingDenDialogue.PREGNANCY_ROULETTE_FUTA_STALLS,
			null,
			"in the Gambling Den"
			).initWeatherImmune();
	
	
	
	
	// Rat warrens:

	public static final AbstractPlaceType RAT_WARRENS_CORRIDOR_LEFT = new AbstractPlaceType(
			"Twisting Passageways",
			"The twisting passageways of the Rat Warrens vary greatly in both width and quality of construction.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			null,
			"in the Rat Warrens") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_CHECKPOINT_LEFT = new AbstractPlaceType(
			"Twisting Passageways",
			"The twisting passageways of the Rat Warrens vary greatly in both width and quality of construction.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			null,
			"in the Rat Warrens") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)
					&& (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_CORRIDOR = new AbstractPlaceType(
			"Twisting Passageways",
			"The twisting passageways of the Rat Warrens vary greatly in both width and quality of construction.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			null,
			"in the Rat Warrens") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_CORRIDOR_RIGHT = new AbstractPlaceType(
			"Twisting Passageways",
			"The twisting passageways of the Rat Warrens vary greatly in both width and quality of construction.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			null,
			"in the Rat Warrens") {
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerCaptive)) {
				return VengarCaptiveDialogue.CORRIDOR;
			}
			return super.getDialogue(withRandomEncounter, forceEncounter);
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_CHECKPOINT_RIGHT = new AbstractPlaceType(
			"Twisting Passageways",
			"The twisting passageways of the Rat Warrens vary greatly in both width and quality of construction.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			null,
			"in the Rat Warrens") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)
					&& (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_ENTRANCE = new AbstractPlaceType(
			"Entrance",
			"The entrance to the Rat Warrens is always guarded by at least two gang members.",
			"submission/ratWarrens/entrance",
			PresetColour.BASE_GREEN,
			RatWarrensDialogue.ENTRANCE,
			null,
			"in the Rat Warrens") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre);
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_DORMITORY_LEFT = new AbstractPlaceType(
			"Dormitory",
			"Bunkbeds line the walls of this damp and dingy room, while a few tables and chairs are scattered around the middle.",
			"submission/ratWarrens/dormitory",
			PresetColour.BASE_BROWN,
			RatWarrensDialogue.DORMITORY,
			null,
			"in the Rat Warrens") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				if(Main.game.isExtendedWorkTime()) {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.FOUR_COMMON))));
				} else {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.FOUR_COMMON))));
				}
			}
			return new ArrayList<>();
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_DORMITORY_RIGHT = new AbstractPlaceType(
			"Dormitory",
			"Bunkbeds line the walls of this damp and dingy room, while a few tables and chairs are scattered around the middle.",
			"submission/ratWarrens/dormitory",
			PresetColour.BASE_BROWN,
			RatWarrensDialogue.DORMITORY,
			null,
			"in the Rat Warrens") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
				if(Main.game.isExtendedWorkTime()) {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.FOUR_COMMON))));
				} else {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.FOUR_COMMON))));
				}
			}
			return new ArrayList<>();
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_DICE_DEN = new AbstractPlaceType(
			"Dice Den",
			"A place where gang members go to drink and gamble.",
			"submission/ratWarrens/diceDen",
			PresetColour.BASE_COPPER,
			RatWarrensDialogue.DICE_DEN,
			null,
			"in the Rat Warrens") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				if(Main.game.isExtendedWorkTime()) {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.FOUR_COMMON))));
				} else {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.FOUR_COMMON))));
				}
			}
			return new ArrayList<>();
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_MILKING_ROOM = new AbstractPlaceType(
			"Milking Room",
			"This is the final destination for humans unfortunate enough to have been kidnapped by Vengar's gang.",
			"submission/ratWarrens/stocks",
			PresetColour.BASE_MAGENTA,
			RatWarrensDialogue.MILKING_ROOM,
			null,
			"in the Rat Warrens") {
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerCaptive)) {
				dialogue = RatWarrensCaptiveDialogue.STOCKS_NIGHT;
			} else {
				dialogue = RatWarrensDialogue.MILKING_ROOM;
			}
			return super.getDialogue(withRandomEncounter, forceEncounter);
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_MILKING_STORAGE = new AbstractPlaceType(
			"Milk Storage",
			"A huge number of metal milk pails are being stored in this area; each one is marked with a bodily fluid type of one sort or another, along with what flavour it is.",
			"submission/ratWarrens/milkingRoom",
			PresetColour.BASE_YELLOW_LIGHT,
			RatWarrensDialogue.MILKING_STORAGE,
			null,
			"in the Rat Warrens") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_VENGARS_HALL = new AbstractPlaceType(
			"Vengar's Hall",
			"A huge, stone hall, filled with numerous long wooden benches and with a raised throne at the far end.",
			"submission/ratWarrens/vengarsHall",
			PresetColour.BASE_PURPLE,
			RatWarrensDialogue.VENGARS_HALL,
			null,
			"in the Rat Warrens") {
		@Override
		public Encounter getEncounterType() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerCaptive)) {
				return Encounter.VENGAR_CAPTIVE_HALL;
			}
			return null;
		}
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerCaptive)) {
				dialogue = VengarCaptiveDialogue.VENGARS_HALL;
			} else {
				dialogue = RatWarrensDialogue.VENGARS_HALL;
			}
			return super.getDialogue(withRandomEncounter, forceEncounter);
		}
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.FOUR_COMMON))));
			}
			return new ArrayList<>();
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_PRIVATE_BEDCHAMBERS = new AbstractPlaceType(
			"Private Bed-chambers",
			"The private bed-chambers used by Vengar and his bodyguards are located adjacent to the main hall.",
			"submission/ratWarrens/bedroom",
			PresetColour.BASE_PURPLE_LIGHT,
			RatWarrensDialogue.VENGARS_BEDROOM,
			null,
			"in the Rat Warrens") {
		@Override
		public Encounter getEncounterType() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerCaptive)) {
				return Encounter.VENGAR_CAPTIVE_BEDROOM;
			}
			return null;
		}
		@Override
		public DialogueNode getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerCaptive)) {
				dialogue = VengarCaptiveDialogue.VENGARS_BEDROOM;
			} else {
				dialogue = RatWarrensDialogue.VENGARS_BEDROOM;
			}
			return super.getDialogue(withRandomEncounter, forceEncounter);
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile);
		}
	}.initWeatherImmune();
	
	
	
	
	// World map tiles:

	public static final AbstractGlobalPlaceType WORLD_MAP_THICK_JUNGLE = new AbstractGlobalPlaceType(
			"thick jungle",
			null,
			"The further into the jungle one travels, the thicker the vegetation becomes, which allows particularly wild and dangerous predators to conceal themselves...",
			new Colour(Util.newColour(0x6b8f7e)),
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_JUNGLE = new AbstractGlobalPlaceType(
			"jungle",
			null,
			"Sparse, tropical foliage is home to many different jungle animal-morphs, not all of which are friendly.",
			new Colour(Util.newColour(0x8fbfa8)),
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_JUNGLE_CITY = new AbstractGlobalPlaceType(
			"Itza'aak",
			null,
			"A sprawling, Mayan-like city, Itza'aak is the last bastion of civilisation before the sprawling, wild jungles of the north.",
			new Colour(Util.newColour(0xb377b0)),
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	};

	public static final AbstractGlobalPlaceType WORLD_MAP_FOOTHILLS = new AbstractGlobalPlaceType(
			"foothills",
			null,
			"A steady increase in elevation leads to the rolling hills at the base of the mountains of the moon.",
			PresetColour.BASE_BLACK,
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_MOUNTAINS = new AbstractGlobalPlaceType(
			"mountains",
			null,
			"The mountain range to the far west is known as the 'Mountains of the Moon', and is home to many alpine animal-morphs.",
			PresetColour.BASE_GREY_DARK,
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_SNOWY_MOUNTAINS = new AbstractGlobalPlaceType(
			"mountain peaks",
			null,
			"The highest peaks of the Mountains of the Moon are capped in snow, and are home to several wild and aggressive races...",
			PresetColour.BASE_GREY_LIGHT,
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_SNOWY_VALLEY = new AbstractGlobalPlaceType(
			"snowstorm valley",
			null,
			"This sheltered valley sees regular, heavy snowfall, and is home to numerous arctic races.",
			new Colour(Util.newColour(0xeeeeee)),
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_GLACIAL_LAKE = new AbstractGlobalPlaceType(
			"selkie lake",
			null,
			"On the western side of snowstorm valley, there can be found a huge, partially-frozen lake.",
			new Colour(Util.newColour(0xbbf0f1)),
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_DOMINION = new AbstractGlobalPlaceType(
			"Dominion",
			"The capital city of Lilith's realm, Dominion is the succubus queen's seat of power.",
			"global/dominion",
			PresetColour.BASE_PURPLE,
			new Colour(Util.newColour(0x826B85)),
			GlobalFoloiFields.DOMINION_EXTERIOR,
			null,
			"in the outskirts of Dominion") {
		@Override
		public WorldType getGlobalLinkedWorldType() {
			return WorldType.DOMINION;
		}
	};

	public static final AbstractGlobalPlaceType WORLD_MAP_GRASSLANDS = new AbstractGlobalPlaceType(
			"grassland wilderness",
			null,//"global/grassland",
			"The grassland wilderness is home to many different races, the vast majority of which are just as wild and untamed as the land they inhabit.",
			new Colour(Util.newColour(0x688255)),
			GlobalFoloiFields.GRASSLAND_WILDERNESS,
			null,
			"") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_FIELDS = new AbstractGlobalPlaceType(
			"Foloi fields",
			null,
			"The farmland surrounding Dominion is known as the 'Foloi fields', and is primarily inhabited by farmyard animal-morphs.",
			new Colour(Util.newColour(0xB9E3A1)),
			GlobalFoloiFields.FOLOI_FIELDS,
			null,
			"") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	};
	
	public static final AbstractGlobalPlaceType WORLD_MAP_FOREST = new AbstractGlobalPlaceType(
			"Foloi forests",
			"The thick forests surrounding the Foloi fields are particularly dangerous, as they are home to the wild, predatory morphs of wolves, foxes, and bears.",
			"global/forest",
			new Colour(Util.newColour(0x51A468)),
			new Colour(Util.newColour(0x5E685E)),
			GlobalFoloiFields.FOLOI_FOREST,
			null,
			"") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_FIELDS_CITY = new AbstractGlobalPlaceType(
			"Elis",
			"The largest and most prosperous of all settlements in the Foloi fields, Elis acts as a trading hub for both the youko and the races inhabiting the mountains.",
			"global/elis",
			new Colour(Util.newColour(0xd544ae)),
			new Colour(Util.newColour(0x859871)),
			GlobalFoloiFields.ELIS,
			null,
			"") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	};
	
	public static final AbstractGlobalPlaceType WORLD_MAP_RIVER = new AbstractGlobalPlaceType(
			"river Hubur",
			"The river Huber runs from the west, through Dominion, and flows out into the endless sea. Those parts of it which border the Foloi fields are considered safe.",
			"global/river",
			new Colour(Util.newColour(0x61BDFF)),
			new Colour(Util.newColour(0x98B4CD)),
			GlobalFoloiFields.RIVER_HUBUR,
			null,
			"") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	};

	public static final AbstractGlobalPlaceType WORLD_MAP_WILD_RIVER = new AbstractGlobalPlaceType(
			"river Hubur (wild)",
			null,
			"Far from Dominion, the river Hubur is a dangerous place in which to swim, as it is home to many wild freshwater races.",
			new Colour(Util.newColour(0xc1f1ee)),
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_YOUKO_FOREST = new AbstractGlobalPlaceType(
			"shinrin highlands",
			null,
			"The Shinrin highlands are a range of low, forest-covered hills, which steadily increase in elevation the further west you go. The elusive youko live here.",
			new Colour(Util.newColour(0x6ccc74)),
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_SEA = new AbstractGlobalPlaceType(
			"endless sea",
			null, "The aquatic races inhabiting Lilith's realm do not like to stray too far from shore, and so to them, the sea is considered to be endless.",
			PresetColour.BASE_BLUE_DARK,
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_SEA_CITY = new AbstractGlobalPlaceType(
			"Lyonesse",
			null,
			"The underwater city of Lyonesse is situated off the eastern coast, and, unsurprisingly, is particularly difficult for non-aquatic races to visit.",
			new Colour(Util.newColour(0x8264b0)),
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	};

	public static final AbstractGlobalPlaceType WORLD_MAP_ARID_GRASSLAND = new AbstractGlobalPlaceType(
			"arid grassland",
			null,
			"To the south, the wild grassland starts to dry out, and is the preferred home for morphs such as lions, leopard, and zebras.",
			PresetColour.BASE_YELLOW_LIGHT,
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_ARID_SAVANNAH = new AbstractGlobalPlaceType(
			"savannah",
			null,
			"Sparse, open-canopy woodlands are scattered across this area, and are inhabited by the same races as those found in the arid grasslands.",
			PresetColour.BASE_TAN,
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_DESERT = new AbstractGlobalPlaceType(
			"desert",
			null,
			"To the south of the arid grassland, all vegetation dies out, creating a hot, barren wasteland.",
			new Colour(Util.newColour(0xffe7a7)),
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_SAND_DUNES = new AbstractGlobalPlaceType(
			"sand dunes",
			null,
			"At the southern edge of the desert, there lies a huge range of sand dunes, which are home to many dangerous races.",
			new Colour(Util.newColour(0xffdb7a)),
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_DESERT_CITY = new AbstractGlobalPlaceType(
			"Thinis",
			null,
			"A city resembling one of ancient Egypt, Thinis is the southern-most settlement in Lilith's realm, and is well known for its prestigious arcane university.",
			new Colour(Util.newColour(0xd5445e)),
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	};

	public static final AbstractGlobalPlaceType WORLD_MAP_VOLCANO = new AbstractGlobalPlaceType(
			"dragon's breath volcano",
			null,
			"A huge volcano, perpetually oozing red-hot lava. Despite its name, dragons are no more common here than they are elsewhere in Lilith's realm.",
			PresetColour.BASE_ORANGE,
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_LAVA_FLOWS = new AbstractGlobalPlaceType(
			"lava flows",
			null,
			"The lava which pours forth from the volcano slowly runs off in a southern direction.",
			PresetColour.BASE_BLACK,
			null, null, "") {
				@Override
				public WorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	
	
	private static List<AbstractPlaceType> allPlaceTypes = new ArrayList<>();
	private static Map<AbstractPlaceType, String> placeToIdMap = new HashMap<>();
	private static Map<String, AbstractPlaceType> idToPlaceMap = new HashMap<>();

	public static List<AbstractPlaceType> getAllPlaceTypes() {
		return allPlaceTypes;
	}
	
	public static AbstractPlaceType getPlaceTypeFromId(String id) {
		id.replaceAll("ALEXA", "HELENA");
		id = Util.getClosestStringMatch(id, idToPlaceMap.keySet());
		return idToPlaceMap.get(id);
	}

	public static String getIdFromPlaceType(AbstractPlaceType placeType) {
		return placeToIdMap.get(placeType);
	}
	
	static {
		Field[] fields = PlaceType.class.getFields();
		
		for(Field f : fields) {
			if(AbstractPlaceType.class.isAssignableFrom(f.getType())) {
				AbstractPlaceType placeType;
				try {
					placeType = ((AbstractPlaceType) f.get(null));

					placeToIdMap.put(placeType, f.getName());
					idToPlaceMap.put(f.getName(), placeType);
					allPlaceTypes.add(placeType);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
