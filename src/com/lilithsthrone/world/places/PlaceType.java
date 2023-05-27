package com.lilithsthrone.world.places;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Elle;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.npc.dominion.Wes;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.DemonHome;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.EnforcerWarehouse;
import com.lilithsthrone.game.dialogue.places.dominion.HomeImprovements;
import com.lilithsthrone.game.dialogue.places.dominion.LilithsTower;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPark;
import com.lilithsthrone.game.dialogue.places.dominion.RedLightDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHallDemographics;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHallProperty;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.BraxOffice;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.EnforcerHQDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.feliciaApartment.FeliciaApartment;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestHelena;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestsDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaApartment;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Library;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaSpa;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomArthur;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.places.dominion.nightlife.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanApartment;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ArcaneArts;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ClothingEmporium;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.DreamLover;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.PixsPlayground;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.RalphsSnacks;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ShoppingArcadeDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.BountyHunterLodge;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaveryAdministration;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.DominionExpress;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.KaysWarehouse;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.Warehouses;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeFirstFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeFirstFloorRepeat;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.places.fields.FieldsDialogue;
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
import com.lilithsthrone.game.dialogue.places.submission.rebelBase.RebelBase;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Bearing;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.TeleportPermissions;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.population.Population;
import com.lilithsthrone.world.population.PopulationDensity;
import com.lilithsthrone.world.population.PopulationType;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class PlaceType {
	
	// Generic holding map:
	
	public static final AbstractPlaceType GENERIC_IMPASSABLE = new AbstractPlaceType(
			WorldRegion.MISC, "Impassable Tile", "", null, PresetColour.BASE_GREY, null, Darkness.ALWAYS_LIGHT, null, "");
	
	public static final AbstractPlaceType GENERIC_EMPTY_TILE = new AbstractPlaceType(
			WorldRegion.MISC, "Empty Tile", "", "dominion/slaverAlleyIcon", PresetColour.BASE_CRIMSON, null, Darkness.ALWAYS_LIGHT, null, "");

	public static final AbstractPlaceType GENERIC_HOLDING_CELL = new AbstractPlaceType(
			WorldRegion.MISC, "Holding Cell", "", "dominion/slaverAlleyIcon", PresetColour.BASE_GREY, null, Darkness.ALWAYS_LIGHT, null, "");

	public static final AbstractPlaceType GENERIC_CLUB_HOLDING_CELL = new AbstractPlaceType(
			WorldRegion.MISC, "Holding Cell (club)", "", "dominion/slaverAlleyIcon", PresetColour.BASE_GREY, null, Darkness.ALWAYS_LIGHT, null, "");
	
	public static final AbstractPlaceType GENERIC_MUSEUM = new AbstractPlaceType(
			WorldRegion.OLD_WORLD, "Museum", "", "dominion/slaverAlleyIcon", PresetColour.BASE_TAN, null, Darkness.ALWAYS_LIGHT, null, "in Lily's Museum"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	
	
	// Museum:
	
	public static final AbstractPlaceType MUSEUM_ENTRANCE = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Entrance",
			"The main entrance to the museum in which your aunt Lily works.",
			"prologue/exit",
			PresetColour.BASE_RED,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "in Lily's Museum") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_CROWDS = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Crowds",
			"This part of the museum's main lobby is currently filled with a large crowd of visitors who, much like you, are here for the exhibition's opening event.",
			"prologue/crowd",
			PresetColour.BASE_YELLOW,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "in Lily's Museum") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_OFFICE = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Office",
			"A large, executive office. It looks sort of familiar, and in the back of your mind you think that it might be your aunt Lily's...",
			"prologue/office",
			PresetColour.BASE_BLUE_LIGHT,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "in Lily's Museum"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_STAGE = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Stage",
			"A large stage has been erected in the middle of the lobby, and it's from here that your aunt Lily is due to give her speech.",
			"prologue/stage",
			PresetColour.BASE_ORANGE,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "in Lily's Museum") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_ROOM = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Exhibit Room",
			"One of the many rooms in the museum that's dedicated to exhibiting ancient relics and precious artifacts.",
			"prologue/room",
			PresetColour.BASE_TAN,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "in Lily's Museum"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_STAIRS = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Stairs",
			"The museum's staircase connects the ground and first floors.",
			"prologue/stairsUp",
			PresetColour.BASE_GREEN,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "in Lily's Museum"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_LOBBY = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Lobby",
			 "The main, double-height lobby of the museum. Banners celebrating the new exhibition have been hung from the upper floor railings.",
			null,
			PresetColour.BASE_TAN,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "in Lily's Museum") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_CORRIDOR = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Corridor",
			 "The museum's upper floor corridors are alarmingly maze-like in their layout.",
			null,
			PresetColour.BASE_TAN,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "in Lily's Museum"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_MIRROR = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Mirror Room",
			"This particular room has a huge, ceiling-height mirror as its main attraction.",
			"prologue/mirror",
			PresetColour.BASE_PINK,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "in Lily's Museum"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	
	
	// Dominion:
	
	public static final AbstractPlaceType DOMINION_PLAZA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Lilith's Plaza",
			"In the very centre of Dominion there is an expansive plaza, where news of Lilith's Domain is read out by full-time criers.",
			"dominion/statue",
			PresetColour.BASE_PINK_DEEP,
			DominionPlaces.DOMINION_PLAZA,
			Darkness.ALWAYS_LIGHT,
			null, "in Dominion's central plaza") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getDominionStormImmuneSpecies(true)));
				pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true, Subspecies.HUMAN)));
			} else {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
				pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				pop.add(new Population(true, PopulationType.CENTAUR_CARTS, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
			}
			
			return pop;
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_STREET = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Dominion Streets",
			"The wide streets of Dominion are lined with well-kept town houses, and are entirely pedestrianised.",
			null,
			PresetColour.BASE_GREY,
			DominionPlaces.STREET,
			Darkness.ALWAYS_LIGHT,
			Encounter.DOMINION_STREET,
			"in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
				List<Population> pop = Util.newArrayListOfValues(new Population(true, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
				pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				pop.add(new Population(true, PopulationType.CENTAUR_CARTS, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
				return pop;
				
			} else {
				return new ArrayList<>();
			}
		}
	};
	
	public static final AbstractPlaceType DOMINION_BOULEVARD = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Dominion Boulevard",
			"The main boulevards which lead to the centre of Dominion are extremely wide and well-travelled, and are very regularly patrolled by Enforcers.",
			null,
			PresetColour.BASE_PINK_LIGHT,
			DominionPlaces.BOULEVARD,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_BOULEVARD, "in the streets of Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_LILITHS_TOWER = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Lilith's Tower",
			"Visible from many miles away, the colossal, dark-stone tower in which Lilith resides is a constant visual reminder to the city's population of who's in charge.",
			"dominion/lilithsTowerIcon",
			PresetColour.BASE_PURPLE,
			LilithsTower.OUTSIDE,
			Darkness.ALWAYS_LIGHT,
			null, "in the streets of Dominion") {

		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_ENFORCER_HQ = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Enforcer HQ",
			"The Enforcer HQ is one of the more modern-looking buildings in Dominion, and it's from here that all of Dominion's law enforcement personnel are commanded.",
			"dominion/enforcerHQIcon",
			PresetColour.BASE_BLUE,
			EnforcerHQDialogue.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the streets of Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_GATE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Demon Home Gates",
			"There are only a couple of entrances to the area known as 'Demon Home', and both are very heavily guarded by numerous Enforcers.",
			"dominion/gate",
			PresetColour.BASE_PINK_LIGHT,
			DemonHome.DEMON_HOME_GATE,
			Darkness.ALWAYS_LIGHT,
			null, "in the streets of Demon Home") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Demon Home",
			"The area surrounding Lilith's tower is known as 'Demon Home', but despite that name, the residents are of all manner of different races.",
			null,
			PresetColour.BASE_PINK,
			DemonHome.DEMON_HOME_STREET,
			Darkness.ALWAYS_LIGHT,
			null, "in the streets of Demon Home") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_ARTHUR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Sawlty Towers",
			"A large stone building ornately decorated in the Victorian style, it resembles a five-star hotel more than an apartment complex.",
			"dominion/demonHomeSawltyTowersIcon",
			PresetColour.RACE_HUMAN,
			DemonHome.DEMON_HOME_STREET_ARTHUR,
			Darkness.ALWAYS_LIGHT,
			null, "in the streets of Demon Home outside Sawlty Towers") {
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
			WorldRegion.DOMINION,
			"Demon Home (Zaranix)",
			"The area surrounding Lilith's tower is known as 'Demon Home', but despite that name, the residents are of all manner of different races.",
			"dominion/demonHomeZaranixIcon",
			PresetColour.BASE_PINK,
			DemonHome.DEMON_HOME_STREET_ZARANIX,
			Darkness.ALWAYS_LIGHT,
			null, "in the streets of Demon Home") {
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
			WorldRegion.DOMINION,
			"Demon Home (Daddy)",
			"The area surrounding Lilith's tower is known as 'Demon Home', but despite that name, the residents are of all manner of different races.",
			"dominion/demonHomeDaddyIcon",
			PresetColour.BASE_INDIGO,
			DemonHome.DEMON_HOME_STREET_DADDY,
			Darkness.ALWAYS_LIGHT,
			null, "in the streets of Demon Home") {
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
				return Util.newArrayListOfValues(new Population(true, PopulationType.DINER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_SHOPPING_ARCADE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Shopping Arcade",
			"Although there are countless stores scattered all throughout Dominion, this arcade is well-known as the best place for shopping.",
			"dominion/shoppingArcadeIcon",
			PresetColour.BASE_GOLD,
			ShoppingArcadeDialogue.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_NYAN_APARTMENT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Nyan's Apartment",
			"As she's shown you where she lives, you know that Nyan's Apartment building is in this area of Dominion.",
			"dominion/homeNyanIcon",
			PresetColour.BASE_PINK_LIGHT,
			DominionPlaces.STREET,
			Darkness.ALWAYS_LIGHT,
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

	public static final AbstractPlaceType DOMINION_CALLIE_BAKERY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"The Creamy Bakey",
			"This bakery is owned by a horse-girl named Callie.",
			"dominion/callieBakeryIcon",
			PresetColour.BASE_BROWN,
			DominionPlaces.STREET,
			Darkness.ALWAYS_LIGHT,
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
			WorldRegion.DOMINION,
			"Dominion Streets",
			"The Harpy Nests' intricate series of walkways and bridges criss-cross between the rooftops in this area, casting their shadows onto the streets below.",
			null,
			PresetColour.BASE_GREY,
			DominionPlaces.STREET_SHADED,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "in the streets of Dominion") {
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
			WorldRegion.DOMINION,
			"Harpy Nests Entrance",
			"A large building, containing numerous elevators and winding stairs, links the Harpy Nests to the streets below.",
			"dominion/harpyNestIcon",
			PresetColour.BASE_MAGENTA,
			HarpyNestsDialogue.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "in the streets of Dominion") {
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
			WorldRegion.DOMINION,
			"The Golden Feather Hotel",
			"One of the most prestigious and well-known of all the hotels in Dominion, 'The Golden Feather' is owned by the harpy matriarch, Helena.",
			"dominion/demonHomeHelenaIcon",
			PresetColour.BASE_GOLD,
			DominionPlaces.HELENAS_HOTEL,
			Darkness.ALWAYS_LIGHT,
			null, "in the streets of Demon Home") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK);
	
	public static final AbstractPlaceType DOMINION_NIGHTLIFE_DISTRICT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Nightlife District",
			"While there are clubs, bars, and other such establishments found all throughout Dominion, the very best nightlife that Dominion can offer is found here.",
			"dominion/nightlifeIcon",
			PresetColour.BASE_PINK_LIGHT,
			NightlifeDistrict.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "in the streets of Dominion") {
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
			WorldRegion.DOMINION,
			"City hall",
			"Acting as a centre for regional government, the city hall handles the administrative affairs of not only Dominion, but of all of Lilith's Domain.",
			"dominion/townHallIcon",
			PresetColour.BASE_INDIGO,
			CityHall.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_BANK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Bank of Dominion",
			"The Realm's only bank, the 'Bank of Dominion' has its main branch here in Dominion.",
			"dominion/bankIcon",
			PresetColour.BASE_GOLD,
			DialogueManager.getDialogueFromId("innoxia_places_dominion_bank_generic_exterior"),
			Darkness.ALWAYS_LIGHT,
			null,
			"in the streets of Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_AUNTS_HOME = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Lilaya's Home",
			"Lilaya's home is more of a mansion than a town house, and, due to its impressive size, stands out as a particularly impressive building in this area.",
			"dominion/homeIcon",
			PresetColour.BASE_BLUE_LIGHT,
			LilayaHomeGeneric.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "in the streets of Dominion") {
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
			WorldRegion.DOMINION,
			"Slaver Alley",
			"Although slavery is completely legal in Dominion, this main hub for all slave transactions is located in a very shady part of the city, being surrounded by dangerous back alleys.",
			"dominion/slaverAlleyIcon",
			PresetColour.BASE_CRIMSON,
			SlaverAlleyDialogue.OUTSIDE,
			Darkness.ALWAYS_LIGHT,
			null, "in the alleyways near Slaver Alley") {
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
			WorldRegion.DOMINION,
			"Red-Light District",
			"As easy as sex is to come by in Dominion, there's no guarantee that a partner has any skill in the bedroom. It's in this area that professional, obligation-free lovers can be hired.",
			"dominion/brothel",
			PresetColour.BASE_MAGENTA,
			RedLightDistrict.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "in the streets of Dominion") {
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
			WorldRegion.DOMINION,
			"Park",
			"There are several large parks found throughout Dominion, all of which are fully open to the public.",
			"dominion/park",
			PresetColour.BASE_GREEN,
			DominionPark.PARK,
			Darkness.DAYLIGHT,
			Encounter.DOMINION_PARK,
			"in one of Dominion's parks") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			
			if(Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
				pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Natalya.class))) {
					pop.add(new Population(true, PopulationType.CENTAUR_CARTS, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
				}
			}
			
			return pop;
		}
	};

	public static final AbstractPlaceType DOMINION_HOME_IMPROVEMENT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Argus's DIY Depot",
			"Supplying both DIY enthusiasts and professional construction firms alike, 'Argus's DIY Depot' takes the form of a pair of huge warehouses, situated in the middle of an expansive lumber yard.",
			"dominion/construction",
			PresetColour.BASE_ORANGE,
			HomeImprovements.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "in the streets of Dominion") {
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
			WorldRegion.DOMINION,
			"Warehouse District",
			"While there are countless industrial buildings scattered throughout Dominion, the area with the largest concentration of them is in this dedicated warehouse district.",
			"dominion/warehouse",
			PresetColour.BASE_BROWN,
			Warehouses.WAREHOUSE_DISTRICT,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "in the streets of Dominion") {
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
			WorldRegion.DOMINION,
			"Alleyways (Patrolled)",
			"While the vast majority of Dominion's alleyways are very dangerous, this particular section is very heavily patrolled by Enforcers, who ensure that muggers are of no threat to the general population.",
			"dominion/alleysIcon",
			PresetColour.BASE_GREY,
			DominionPlaces.BACK_ALLEYS_SAFE,
			Darkness.DAYLIGHT, Encounter.DOMINION_STREET, "in one of Dominion's backalleys"
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
					new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)),
					new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	};
	
	public static final AbstractPlaceType DOMINION_BACK_ALLEYS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Alleyways",
			"Although offering shortcuts between main streets, the city's labyrinthine alleyways are very seldom travelled by anyone, as they're well-known to be inhabited by dangerous elements of society.",
			"dominion/alleysIcon",
			PresetColour.BASE_BLACK,
			DominionPlaces.BACK_ALLEYS,
			Darkness.DAYLIGHT, Encounter.DOMINION_ALLEY, "in one of Dominion's backalleys"
			).initDangerous()
			.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType DOMINION_DARK_ALLEYS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Dark Alleyways",
			"The darkest depths of Dominion's twisting, maze-like alleyways are avoided even by the muggers who would usually call such places home...",
			"dominion/alleysDarkIcon",
			PresetColour.BASE_PURPLE,
			DominionPlaces.DARK_ALLEYS,
			Darkness.DAYLIGHT, Encounter.DOMINION_DARK_ALLEY, "in one of Dominion's dark alleyways"
			).initDangerous()
			.initSexNotBlockedFromCharacterPresent();
	
	public static final AbstractPlaceType DOMINION_ALLEYS_CANAL_CROSSING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Canal Crossing",
			"These alleyway crossings over the city's canal are considered to be very dangerous, and have the reputation as being the favourite haunts of dangerous desperadoes.",
			"dominion/bridge",
			PresetColour.BASE_BLUE_LIGHT,
			DominionPlaces.BACK_ALLEYS_CANAL,
			Darkness.DAYLIGHT, Encounter.DOMINION_ALLEY, "in one of Dominion's backalleys"
			).initDangerous()
			.initAquatic(Aquatic.MIXED);
	       
	// Canals:
	
	public static final AbstractPlaceType DOMINION_CANAL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Canal",
			"Dominion's canal is safe enough for anyone on a barge or boat, but for those walking along the often-deserted and Enforcer-free towpaths, it's a different story entirely...",
			"dominion/canalIcon",
			PresetColour.BASE_BLUE_LIGHT,
			DominionPlaces.CANAL,
			Darkness.DAYLIGHT, Encounter.DOMINION_CANAL, "beside one of Dominion's canals"
			).initDangerous()
			.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType DOMINION_CANAL_END = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Canal",
			"The towpaths which run alongside Dominion's canal come to an abrupt end at the city outskirts.",
			"dominion/canalEndIcon",
			PresetColour.BASE_BLUE,
			DominionPlaces.CANAL_END,
			Darkness.DAYLIGHT, Encounter.DOMINION_CANAL, "beside one of Dominion's canals"
			).initDangerous()
			.initAquatic(Aquatic.MIXED);
	
	// Exits & entrances:
	
	public static final AbstractPlaceType DOMINION_EXIT_TO_SUBMISSION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Submission Entrance",
			"At each crossing between Dominion's canal and main streets, there exist Enforcer-guarded entrances to the undercity of Submission.",
			"dominion/submissionExit",
			PresetColour.BASE_TEAL,
			DominionPlaces.CITY_EXIT_SEWERS,
			Darkness.ALWAYS_LIGHT,
			null, "in the streets of Dominion") {
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

	public static final AbstractPlaceType DOMINION_EXIT_TO_BAT_CAVERNS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Shaft to the Bat Caverns",
			"This deep, twisting shaft is surrounded by a high chain-link fence, with nearby signs indicating that it leads down to the Bat Caverns beneath Submission.",
			"dominion/batCaverns",
			PresetColour.BASE_BLUE,
			DominionPlaces.CITY_EXIT_BAT_CAVERNS,
			Darkness.ALWAYS_LIGHT,
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
			WorldRegion.DOMINION,
			"Dominion Exit",
			"Dominion's wide boulevards turn back into regular streets as they trail off into the city's surprisingly-small suburbs.",
			"dominion/exitEast",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			Darkness.ALWAYS_LIGHT,
			null, "in the streets of Dominion") {
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
			WorldRegion.DOMINION,
			"Dominion Exit",
			"Dominion's wide boulevards turn back into regular streets as they trail off into the city's surprisingly-small suburbs.",
			"dominion/exitNorth",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			Darkness.ALWAYS_LIGHT,
			null, "in the streets of Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_EXIT_WEST = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Dominion Exit",
			"Dominion's wide boulevards turn back into regular streets as they trail off into the city's surprisingly-small suburbs.",
			"dominion/exitWest",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			Darkness.ALWAYS_LIGHT,
			null, "in the streets of Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_EXIT_SOUTH = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Dominion Exit",
			"Dominion's wide boulevards turn back into regular streets as they trail off into the city's surprisingly-small suburbs.",
			"dominion/exitSouth",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			Darkness.ALWAYS_LIGHT,
			null, "in the streets of Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	
	
	// Enforcer HQ:
	
	public static final AbstractPlaceType ENFORCER_HQ_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"Many doors line either side of this rather ordinary-looking corridor, each one marked with a different Enforcer division's name and speciality.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerHQDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer HQ") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_CELLS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"This particular corridor doesn't have any distinguishing features to it.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerHQDialogue.CORRIDOR_PLAIN,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer HQ")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Guarded Staircase",
			"A staircase leading up to the next floor is guarded by a vigilant Enforcer.",
			"dominion/enforcerHQ/stairs",
			PresetColour.BASE_GREEN,
			EnforcerHQDialogue.STAIRCASE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer HQ")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_WAITING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Waiting area",
			"Several low sofas, a few potted plants, and an air of tedious boredom make up this waiting area.",
			"dominion/enforcerHQ/waitingRoom",
			PresetColour.BASE_BROWN,
			EnforcerHQDialogue.WAITING_AREA,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer HQ")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_RECEPTION_DESK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Reception desk",
			"The Enforcer HQ's front desk is staffed by the bimbo cat-girl, Candi.",
			"dominion/enforcerHQ/receptionDesk",
			PresetColour.BASE_BLUE_LIGHT,
			EnforcerHQDialogue.RECEPTION_DESK,
			Darkness.ALWAYS_LIGHT,
			null, "in Candi's office")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_GUARDED_DOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Guarded door",
			"The door connecting the public waiting room to the rest of the Enforcer HQ is guarded by a particularly buff horse-boy.",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_CRIMSON,
			EnforcerHQDialogue.GUARDED_DOOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer HQ") {
		@Override
		public Colour getColour() {
			if((Main.game.getDialogueFlags().values.contains(DialogueFlagValue.accessToEnforcerHQ) && !Main.game.isBraxMainQuestComplete())
					|| Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES)) {
				return PresetColour.BASE_GREEN_LIGHT;
			}
			return PresetColour.BASE_CRIMSON;
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_REQUISITIONS_DOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Locked door",
			"This internal door is firmly locked, barring passage to anyone not in possession of the required key.",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_CRIMSON,
			EnforcerHQDialogue.REQUISITIONS_DOOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer HQ") {
		@Override
		public Colour getColour() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES)) {
				return PresetColour.BASE_GREEN_LIGHT;
			}
			return PresetColour.BASE_CRIMSON;
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_LOCKED_DOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Locked door",
			"This internal door is firmly locked, barring passage to anyone not in possession of the required key.",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_CRIMSON,
			EnforcerHQDialogue.LOCKED_DOOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer HQ")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_LOCKED_DOOR_EDGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Locked door",
			"This internal door is firmly locked, barring passage to anyone not in possession of the required key.",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_RED_DARK,
			EnforcerHQDialogue.LOCKED_DOOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer HQ")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_BRAXS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Brax's Office",
			"Enforcers of the rank 'Inspector' are allowed their own office, and are permitted to decorate them as they see fit.",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_BLUE_DARK,
			BraxOffice.INTERIOR_BRAX,
			Darkness.ALWAYS_LIGHT,
			null, "in his office") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLUE, null, false);
			jacket.setSticker("collar", "tab_ip");
			jacket.setSticker("name", "name_brax");
			jacket.setSticker("ribbon", "ribbon_brax");
			inventory.addClothing(jacket);
			
			inventory.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false));
			
			AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_dominion");
			inventory.addClothing(hat);
		}
		@Override
		public boolean isItemsDisappear() {
			return false;
		}
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.braxEncountered)) {
				return BraxOffice.INTERIOR_BRAX_REPEAT;
			} else {
				return BraxOffice.INTERIOR_BRAX;
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Locked Office",
			"The door to this particular office is locked, leaving you wondering as to what could be contained within.",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_GREY,
			EnforcerHQDialogue.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer HQ")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_CELLS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Cells Office",
			"This small office is the place where all prisoners are checked in and out of the cells.",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_PURPLE,
			EnforcerHQDialogue.CELLS_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer HQ")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_CELL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Cell",
			"The cells in the Enforcer Headquarters are where prisoners are temporarily held until such time as they can be properly processed.",
			"dominion/enforcerHQ/cell",
			PresetColour.BASE_BROWN_DARK,
			EnforcerHQDialogue.CELL,
			Darkness.ALWAYS_LIGHT,
			null, "in the cells of the Enforcer HQ")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entranceway",
			"The entrance to the Enforcer HQ consists of a pair of sound-proof glass doors.",
			"dominion/enforcerHQ/exit",
			PresetColour.BASE_RED,
			EnforcerHQDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_ENFORCER_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Enforcer Entrance",
			"One of the many non-public entrances to the Enforcer HQ, the sound-proof glass doors only open when a special pass is swiped over a nearby arcane scanner.",
			"dominion/enforcerHQ/exit",
			PresetColour.BASE_BLUE,
			EnforcerHQDialogue.ENTRANCE_ENFORCER,
			Darkness.ALWAYS_LIGHT,
			null, "")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_REQUISITIONS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Requisitions Desk",
			"Specialist or replacement Enforcer equipment is checked out of this area.",
			"dominion/enforcerHQ/requisitions",
			PresetColour.BASE_TAN,
			EnforcerHQDialogue.REQUISITIONS,
			Darkness.ALWAYS_LIGHT,
			null, "") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(ENFORCER_HQ_REQUISITIONS)).contains(Main.game.getNpc(Wes.class))
					&& !Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(ENFORCER_HQ_REQUISITIONS)).contains(Main.game.getNpc(Elle.class))) {
				return Util.newArrayListOfValues(new Population(false, PopulationType.ENFORCER, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.DOG_MORPH_GERMAN_SHEPHERD, SubspeciesSpawnRarity.TEN))));
			}
			return super.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_OFFICE_QUARTERMASTER = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Quartermaster's Office",
			"Responsible for the management of Enforcer equipment, the HQ's quartermaster has their office conveniently positioned opposite to the Requisitions Desk.",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_ORANGE,
			EnforcerHQDialogue.OFFICE_QUARTERMASTER,
			Darkness.ALWAYS_LIGHT,
			null, "")
			.initWeatherImmune();
	
        //Felicia's Apartment       
        
        public static final AbstractPlaceType FELICIA_APARTMENT_ENTRYWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entrance Hall",
			"The entryway in [felicia.NamePos] apartment has a coat closet off to the side.",
			"dominion/feliciaApartment/entranceHall",
			PresetColour.BASE_RED,
			FeliciaApartment.ENTRYWAY,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the entrance hall of [felicia.NamePos] apartment"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Bedroom",
			"PLACEHOLDER_FELICIA_APARTMENT_BEDROOM",
			"dominion/feliciaApartment/feliciaBedroom",
			PresetColour.BASE_YELLOW_PALE,
			FeliciaApartment.FELICIA_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"in [felicia.NamePos] bedroom"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_BATHROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Bathroom",
			"PLACEHOLDER_FELICIA_APARTMENT_BATHROOM",
			"dominion/feliciaApartment/toilet",
			PresetColour.BASE_BLUE_LIGHT,
			FeliciaApartment.BATHROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the bathroom in Felicia's apartment"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Kitchen",
			"The barren kitchen is open to both the hallway and the dining area.",
			"dominion/feliciaApartment/kitchen",
			PresetColour.BASE_ORANGE,
			FeliciaApartment.KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the kitchen in [felicia.NamePos] apartment"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_DINING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Dining Area",
			"The dining area in [felicia.NamePos] apartment is cramped despite being equipped to only seat one.",
			"dominion/feliciaApartment/diningArea",
			PresetColour.BASE_BLUE_STEEL,
			FeliciaApartment.DINING_AREA,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the dining area in [felicia.NamePos] apartment"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_LIVING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Living Area",
			"[felicia.Name]'s living room is sparsely decorated but has a view of Dominion's streets.",
			"dominion/feliciaApartment/livingArea",
			PresetColour.BASE_INDIGO,
			FeliciaApartment.LIVING_AREA,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the living area in [felicia.NamePos] apartment"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_HALLWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Hallway",
			"The hallway is totally void of decoration or furniture.",
			null,
			PresetColour.BASE_BLACK,
			FeliciaApartment.HALLWAY,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the hallway in [felicia.NamePos] apartment"
        ).initWeatherImmune();
        
	// Enforcer warehouse:
	
	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entrance",
			"The only entrance to the warehouse is guarded by a small, Enforcer-manned booth.",
			"dominion/enforcerWarehouse/exit",
			PresetColour.BASE_RED,
			EnforcerWarehouse.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initDangerous()
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"Numerous corridors, lined on both sides by stacked wooden crates, snake their way through the warehouse.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerWarehouse.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CLAIRE_WARNING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"Numerous corridors, lined on both sides by stacked wooden crates, snake their way through the warehouse.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerWarehouse.CLAIRE_WARNING,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENCLOSURE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Enclosure",
			"A forgotten corner of the warehouse, enclosed on all sides by towering stacks of wooden crates.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerWarehouse.ENCLOSURE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_PADS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Teleportation pad",
			"The teleportation pad which you and Claire arrived on is in this area.",
			"dominion/enforcerWarehouse/teleportPads",
			PresetColour.BASE_MAGENTA,
			EnforcerWarehouse.ENCLOSURE_TELEPORT_PADS,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_SHELVING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Shelves",
			"Several free-standing shelving units have been stacked up in this corner.",
			"dominion/enforcerWarehouse/shelving",
			PresetColour.BASE_PURPLE_LIGHT,
			EnforcerWarehouse.ENCLOSURE_SHELVING,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENFORCER_GUARD_POST = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Enforcer guard post",
			"Scattered throughout the warehouse are several Enforcer guard posts, which consist of little more than a chair and table.",
			"dominion/enforcerWarehouse/enforcerGuardPost",
			PresetColour.BASE_BLUE_STEEL,
			EnforcerWarehouse.ENFORCER_GUARD_POST,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initDangerous()
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Crates",
			"One or two of the crates in this area of the warehouse have not yet been sealed shut.",
			"dominion/enforcerWarehouse/crates",
			PresetColour.BASE_ORANGE,
			EnforcerWarehouse.CRATES,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_SEARCHED = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Crates (searched)",
			"One or two of the crates in this area of the warehouse had not yet been sealed shut, which allowed you to search through them.",
			"dominion/enforcerWarehouse/cratesSearched",
			PresetColour.BASE_GREY,
			EnforcerWarehouse.CRATES,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_ARK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Crates",
			"One or two of the crates in this area of the warehouse have not yet been sealed shut.",
			"dominion/enforcerWarehouse/crates",
			PresetColour.BASE_ORANGE,
			EnforcerWarehouse.CRATES_ARK,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_ARK_SEARCHED = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Crates (searched)",
			"One or two of the crates in this area of the warehouse had not yet been sealed shut, which allowed you to search through them.",
			"dominion/enforcerWarehouse/cratesSearched",
			PresetColour.BASE_GREY,
			EnforcerWarehouse.CRATES_ARK,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_LUST_WEAPON = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"'Top Secret' Crate",
			"One of the crates in this area has been marked as 'Top Secret'.",
			"dominion/enforcerWarehouse/cratesLustWeapon",
			PresetColour.BASE_PINK_DEEP,
			EnforcerWarehouse.CRATES_LUST_WEAPON,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Shelving",
			"Instead of the usual crates found all throughout the warehouse, this particular area contains shelves full of banned books and other such contraband writings.",
			"dominion/enforcerWarehouse/shelvingSpellBook",
			PresetColour.BASE_MAGENTA,
			EnforcerWarehouse.SHELVES_SPELL_BOOK,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK_SEARCHED = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Shelving (searched)",
			"Instead of the usual crates found all throughout the warehouse, this particular area contains shelves full of banned books and other such contraband writings. You've already searched through them and found a spell book.",
			"dominion/enforcerWarehouse/shelvingSearched",
			PresetColour.BASE_GREY,
			EnforcerWarehouse.SHELVES_SPELL_BOOK,
			Darkness.ALWAYS_LIGHT,
			null, "in the Enforcer warehouse")
			.initWeatherImmune();
	
	
	// City hall:
	
	public static final AbstractPlaceType CITY_HALL_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"The marble corridors of Dominion's city hall allow the resident bureaucrats to easily stride from one office to another.",
			null,
			PresetColour.BASE_BLACK,
			CityHall.CITY_HALL_CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in Dominion's city hall") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.OFFICE_WORKER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entrance",
			"The entrance to city hall takes the form of a pair of revolving glass doors; one of them is marked 'Exit', and the other, 'Entrance: no access'.",
			"dominion/cityHall/exit",
			PresetColour.BASE_RED,
			CityHall.CITY_HALL_FOYER,
			Darkness.ALWAYS_LIGHT,
			null, "in Dominion's city hall") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getDominionStormImmuneSpecies(true)));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType CITY_HALL_INFORMATION_DESK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Information Desk",
			"A circular desk, staffed by several receptionists, sits in the middle of the large entrance hall.",
			"dominion/cityHall/front_desk",
			PresetColour.BASE_BLUE_LIGHT,
			CityHall.CITY_HALL_INFORMATION_DESK,
			Darkness.ALWAYS_LIGHT,
			null, "in Dominion's city hall") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType CITY_HALL_WAITING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Waiting Room",
			"A large analogue clock hangs on the far side of the open-plan waiting room; the slow ticking of the second hand a constant reminder to all those who are present of the crippling inefficiency of the bureaucracy.",
			"dominion/cityHall/waiting_area",
			PresetColour.BASE_PURPLE_LIGHT,
			CityHall.CITY_HALL_WAITING_AREA,
			Darkness.ALWAYS_LIGHT,
			null, "in Dominion's city hall") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Office",
			"The door to this particular office is marked 'Private', letting everybody know that absolutely nothing of importance happens inside.",
			"dominion/cityHall/office",
			PresetColour.BASE_ORANGE,
			CityHall.CITY_HALL_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "in Dominion's city hall") {
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Staircase",
			"The staircases to city hall's upper floors are marked as private, and are cordoned off by means of red rope barriers.",
			"dominion/cityHall/stairs",
			PresetColour.BASE_GREY,
			CityHall.CITY_HALL_STAIRS,
			Darkness.ALWAYS_LIGHT,
			null, "in Dominion's city hall") {
	}.initWeatherImmune();
	
	public static final AbstractPlaceType CITY_HALL_BUREAU_OF_DEMOGRAPHICS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Bureau of Demographics",
			"The 'Bureau of Demographics' consists of a small office adjoining a vast, library-like storage area.",
			"dominion/cityHall/officeDemographics",
			PresetColour.BASE_TEAL,
			CityHallDemographics.CITY_HALL_DEMOGRAPHICS_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in Dominion's city hall") {
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_ARCHIVES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Bureau of Demographics (Archives)",
			"The 'Bureau of Demographics' consists of a small office adjoining a vast, library-like storage area.",
			"dominion/cityHall/officeDemographicsArchives",
			PresetColour.BASE_BLUE, // Player cannot enter this tile.
			CityHallDemographics.CITY_HALL_DEMOGRAPHICS_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in Dominion's city hall") {
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_BUREAU_OF_PROPERTY_RIGHTS_AND_COMMERCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"The 'Bureau of Property and Commerce' is one of the largest and most well-funded departments in city hall, and consists of numerous interlinked offices and meeting rooms.",
			"dominion/cityHall/officeProperty",
			PresetColour.BASE_GOLD,
			CityHallProperty.CITY_HALL_PROPERTY_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in Dominion's city hall") {
	}.initWeatherImmune();

	
	// Home Improvements:
	
	public static final AbstractPlaceType HOME_IMPROVEMENTS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Aisles",
			"Wide, concrete-floored aisles run throughout the warehouse, giving customers plenty of room in which to wheel their trolleys past one another.",
			null,
			PresetColour.BASE_BLACK,
			HomeImprovements.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in 'Argus's DIY Depot'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entrance",
			"The entrance to city hall takes the form of a pair of revolving glass doors; one of them is marked 'Exit', and the other, 'Entrance: no access'.",
			"dominion/homeImprovements/exit",
			PresetColour.BASE_RED,
			HomeImprovements.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in 'Argus's DIY Depot'") {
		@Override
		public List<Population> getPopulation() {
			return HOME_IMPROVEMENTS_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_SHELVING_PREMIUM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Shelving (Premium)",
			"The shelving facing the main entrance is stocked full of 'premium-grade' paint, tools, and other miscellaneous DIY supplies.",
			"dominion/homeImprovements/shelving",
			PresetColour.BASE_GOLD,
			HomeImprovements.SHELVING_PREMIUM,
			Darkness.ALWAYS_LIGHT,
			null, "in 'Argus's DIY Depot'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_SHELVING_STANDARD = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Shelving (Standard)",
			"The shelving which runs alongside the main aisles is stocked full of 'standard-grade' paint, tools, and other miscellaneous DIY supplies.",
			"dominion/homeImprovements/shelving",
			PresetColour.BASE_BROWN,
			HomeImprovements.SHELVING_STANDARD,
			Darkness.ALWAYS_LIGHT,
			null, "in 'Argus's DIY Depot'") {
		@Override
		public List<Population> getPopulation() {
			return HOME_IMPROVEMENTS_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_BUILDING_SUPPLIES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Building supplies",
			"Near the back of the warehouse, there are numerous shelving units stocked full of timber, tiles, piping, and all sorts of other building materials.",
			"dominion/homeImprovements/crates",
			PresetColour.BASE_ORANGE,
			HomeImprovements.BUILDING_SUPPLIES,
			Darkness.ALWAYS_LIGHT,
			null, "in 'Argus's DIY Depot'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Manager's Office",
			"Argus, the owner and manager of this business, has his office situated close to the warehouse's entrance.",
			"dominion/homeImprovements/office",
			PresetColour.BASE_MAGENTA,
			HomeImprovements.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "in Argus's office")
	.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_TOILETS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Toilets",
			"At the back corner of the warehouse, there are some toilets available for customers to use.",
			"dominion/homeImprovements/toilets",
			PresetColour.BASE_BLUE_LIGHT,
			HomeImprovements.TOILETS,
			Darkness.ALWAYS_LIGHT,
			null, "in the toilets at 'Argus's DIY Depot'")
	.initWeatherImmune();

	
	// Dominion Express:
	
	public static final AbstractPlaceType DOMINION_EXPRESS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"This corridor runs the entire length of the warehouse, and effectively splits the open-plan storage area from the business's numerous offices.",
			null,
			PresetColour.BASE_BLACK,
			DominionExpress.CORRIDOR,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_EXPRESS, "in the 'Dominion Express' warehouse") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isExtendedWorkTime()) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.SLAVE, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
			}
			return Util.newArrayListOfValues(new Population(true, PopulationType.SLAVE, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_EXIT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entrance",
			"A solitary reception desk is positioned to one side of the warehouse's entrance, with the secretaries sitting behind it making sure that any visitors have a reason to be in here.",
			"dominion/dominionExpress/exit",
			PresetColour.BASE_RED,
			DominionExpress.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in the 'Dominion Express' warehouse") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isExtendedWorkTime()) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.RECEPTIONIST, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN))));
			}
			return Util.newArrayListOfValues(new Population(false, PopulationType.RECEPTIONIST, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_STORAGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Storage",
			"Half of the warehouse's floor space is reserved for the temporary storage of goods.",
			"dominion/dominionExpress/crates",
			PresetColour.BASE_ORANGE,
			DominionExpress.STORAGE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_EXPRESS, "in the 'Dominion Express' warehouse") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_EXPRESS_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Office",
			"The day-to-day running of 'Dominion Express' is handled within these offices.",
			"dominion/dominionExpress/office",
			PresetColour.BASE_BLUE_LIGHT,
			DominionExpress.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "in the 'Dominion Express' warehouse") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.OFFICE_WORKER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_FILLY_STATION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Filly Rewards Station",
			"Set into a small alcove on one side of the warehouse corridor, there's a curious-looking arcane vending machine, which is clearly marked as a 'Filly Rewards Station'.",
			"dominion/dominionExpress/fillyStation",
			PresetColour.BASE_PINK_LIGHT,
			DominionExpress.FILLY_STATION,
			Darkness.ALWAYS_LIGHT,
			null, "in the 'Dominion Express' warehouse")
		.initWeatherImmune();
	
	public static final AbstractPlaceType DOMINION_EXPRESS_OFFICE_STABLE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Stable Mistress's office",
			"The office responsible for the care and management of the centaur slaves is located at the far end of the warehouse's corridor.",
			"dominion/dominionExpress/officeStable",
			PresetColour.BASE_TAN,
			DominionExpress.OFFICE_STABLE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Stable Mistress's office at the 'Dominion Express' warehouse")
	.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_STABLES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Stables",
			"This large, single-story annex contains a vast array of stalls, each one being large enough to comfortably house a centaur slave.",
			"dominion/dominionExpress/stables",
			PresetColour.BASE_BROWN,
			DominionExpress.STABLES,
			Darkness.ALWAYS_LIGHT,
			null, "in the stables at the 'Dominion Express' warehouse") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SLAVE, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	
	
	// Harpy Nests:
	
	public static final AbstractPlaceType HARPY_NESTS_WALKWAYS = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"Walkway",
			"The harpy nests are connected to one another by means of narrow wooden walkways built on top of Dominion's residential buildings.",
			null,
			PresetColour.BASE_BLACK,
			HarpyNestsDialogue.WALKWAY,
			Darkness.ALWAYS_LIGHT, Encounter.HARPY_NEST_WALKWAYS, "in the Harpy Nests") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				return super.getPopulation();
			} else {
				return Util.newArrayListOfValues(new Population(true, PopulationType.HARPY, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.HARPY_NEST, this, false, false)));
			}
		}
	}.initSexNotBlockedFromCharacterPresent();
	
	public static final AbstractPlaceType HARPY_NESTS_WALKWAYS_BRIDGE = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"Walkway Bridge",
			"Here and there, bridges span over the streets below, connecting one set of walkways with another.",
			"dominion/harpyNests/bridge",
			PresetColour.BASE_GREY,
			HarpyNestsDialogue.WALKWAY_BRIDGE,
			Darkness.ALWAYS_LIGHT, Encounter.HARPY_NEST_WALKWAYS, "in the Harpy Nests") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	}.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType HARPY_NESTS_ENTRANCE_ENFORCER_POST = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Enforcer post",
			"A well-staffed Enforcer outpost is required in order to keep the peace between countless quarrelsome harpies.",
			"dominion/harpyNests/exit",
			PresetColour.BASE_RED,
			HarpyNestsDialogue.ENTRANCE_ENFORCER_POST,
			Darkness.ALWAYS_LIGHT,
			null, "in the Harpy Nests") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.ENFORCER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HARPY_NESTS_HELENAS_NEST = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"Helena's nest",
			"The stunningly beautiful harpy matriarch, Helena, rules over the largest of all the harpy nests.",
			"dominion/harpyNests/nestHelena",
			PresetColour.BASE_GOLD,
			HarpyNestHelena.HELENAS_NEST_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "in Helena's nest"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	};
	
	public static final AbstractPlaceType HARPY_NESTS_HARPY_NEST_RED = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"Harpy nest",
			"Diana's nest consists primarily of angry red harpies; their feather colour an attempt to mimic the appearance of their sadistic leader.",
			"dominion/harpyNests/nestRed",
			PresetColour.BASE_CRIMSON,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "in Diana's nest"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			return DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior");
		}
	};
	
	public static final AbstractPlaceType HARPY_NESTS_HARPY_NEST_PINK = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"Harpy nest",
			"Lexi's nest contains a disproportionate amount of harpy males, each of whom hangs around in the hopes of getting to fuck their sex-loving matriarch.",
			"dominion/harpyNests/nestPink",
			PresetColour.BASE_PINK_LIGHT,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "in Lexi's nest"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			return DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior");
		}
	};
	
	public static final AbstractPlaceType HARPY_NESTS_HARPY_NEST_YELLOW = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"Harpy nest",
			"Brittany's nest has a considerable population of bleach-blonde-feathered, big-busted, bimbo harpies.",
			"dominion/harpyNests/nestYellow",
			PresetColour.BASE_YELLOW_LIGHT,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "in Brittany's nest"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			return DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior");
		}
	};
	
			
	// Lilaya's home (ground floor):
	
	public static final AbstractPlaceType LILAYA_HOME_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"Immaculately-clean red carpet runs down the centre of each of the corridors in Lilaya's home, while fine paintings and masterfully-carved marble busts line the walls.",
			null,
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			Encounter.LILAYAS_HOME_CORRIDOR,
			"in Lilaya's Home"
		).initItemsPersistInTile()
		.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Room",
			"The outer rooms on the ground floor, including this one, have windows looking down onto either the streets of Dominion, or the private alleyways running around the house.",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_WINDOW,
			Darkness.ALWAYS_LIGHT,
			null,
			"in Lilaya's Home") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.ROOM_WINDOW;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			if(upgrades.contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
				return PlaceUpgrade.getGuestRoomUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM)) {
				return PlaceUpgrade.getSlaveQuartersUpgradesSingle();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE)) {
				return PlaceUpgrade.getSlaveQuartersUpgradesDouble();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE)) {
				return PlaceUpgrade.getSlaveQuartersUpgradesQuadruple();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
				return PlaceUpgrade.getMilkingUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_OFFICE)) {
				return PlaceUpgrade.getOfficeUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SPA)) {
				return PlaceUpgrade.getSpaUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_DINING_HALL)) {
				return PlaceUpgrade.getDiningHallUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_LOUNGE)) {
				return PlaceUpgrade.getSlaveLoungeUpgrades();
			}
			
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
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Garden Room",
			"The inner rooms on the ground floor are all connected to the private garden by means of patio doors.",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's Home") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			return LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR.getAvailablePlaceUpgrades(upgrades);
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
			WorldRegion.DOMINION,
			"Room",
			"The outer rooms on the first floor, including this one, have windows looking down onto either the streets of Dominion, or the private alleyways running around the house.",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_WINDOW,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's Home") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.ROOM_WINDOW;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			return LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR.getAvailablePlaceUpgrades(upgrades);
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
			WorldRegion.DOMINION,
			"Garden Room",
			"The inner rooms on the first floor, including this one, have windows looking down onto the private garden.",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_GARDEN,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's Home") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.ROOM_GARDEN;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			return LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR.getAvailablePlaceUpgrades(upgrades);
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
	
	public static final AbstractPlaceType LILAYA_HOME_DUNGEON_CELL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Dungeon cell",
			"The cells within Lilaya's dungeon are designed to be cramped and uncomfortable.",
			"dominion/lilayasHome/roomSlave",
			PresetColour.BASE_GREY,//BASE_MAGENTA
			LilayaHomeGeneric.DUNGEON_CELL,
			Darkness.ALWAYS_LIGHT,
			null,
			"in Lilaya's dungeon") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.DUNGEON_CELL;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_DUNGEON_CELL);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			return PlaceUpgrade.getDungeonCellUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " D-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ARTHUR_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Arthur's Room",
			"Converted from a store room houses Lilaya's one-time lover and colleague, Arthur.",
			"dominion/lilayasHome/roomArthur",
			PresetColour.BASE_BLUE_STEEL,
			RoomArthur.ROOM_ARTHUR,
			Darkness.ALWAYS_LIGHT,
			null, "in Arthur's Room"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_BIRTHING_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Room",
			"You aren't quite sure why Lilaya has a dedicated delivery room, but you assume that it must at one point have been for arcane research purposes.",
			"dominion/lilayasHome/roomBirthing",
			PresetColour.BASE_PINK,
			LilayaHomeGeneric.BIRTHING_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Kitchen",
			"At the back of the house, there's a huge, well-equipped kitchen.",
			"dominion/lilayasHome/kitchen",
			PresetColour.BASE_TAN,
			LilayaHomeGeneric.KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's kitchen"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_LIBRARY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Library",
			"A huge library, containing row after row of book-filled shelving, is situated in one corner of the ground floor.",
			"dominion/lilayasHome/library",
			PresetColour.BASE_TEAL,
			Library.LIBRARY,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's library") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			inventory.addItem(Main.game.getItemGen().generateItem(ItemType.getLoreBook(Subspecies.HALF_DEMON)));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_STAIR_UP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Staircase",
			"This staircase connects the ground and first floor of Lilaya's home, and has a small, secondary landing area half-way up.",
			"dominion/lilayasHome/stairsUp",
			PresetColour.BASE_GREEN_LIGHT,
			LilayaHomeGeneric.STAIRCASE_UP,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_STAIR_UP_SECONDARY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Staircase",
			"This staircase, while smaller than the main one near the mansion's entrance, performs the same purpose of connecting the ground and first floor of Lilaya's home.",
			"dominion/lilayasHome/stairsUpSecondary",
			PresetColour.BASE_GREEN_LIME,
			LilayaHomeGeneric.STAIRCASE_UP_SECONDARY,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ENTRANCE_HALL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entrance Hall",
			"Fine paintings and marble busts line the walls of this grand entrance hall, while a huge crystal chandelier hangs from the double-height ceiling overhead.",
			"dominion/lilayasHome/entranceHall",
			PresetColour.BASE_RED,
			LilayaHomeGeneric.ENTRANCE_HALL,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_LAB = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Lilaya's Lab",
			"A room in one corner of the ground floor has been converted into a dedicated laboratory, in which Lilaya spends almost all of her time.",
			"dominion/lilayasHome/lab",
			PresetColour.BASE_GREEN_LIME,
			Lab.LAB,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's lab") {
//		@Override
//		public void applyInventoryInit(CharacterInventory inventory) {
//			inventory.addClothing(Main.game.getItemGen().generateClothing("innoxia_scientist_safety_goggles", false));
//		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_GARDEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Garden",
			"This private garden is surrounded on all four side by the walls of Lilaya's house.",
			null,
			PresetColour.BASE_GREEN,
			LilayaHomeGeneric.GARDEN,
			Darkness.DAYLIGHT,
			null, "in Lilaya's garden"
			).initItemsPersistInTile()
			.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_GREEN);
	
	public static final AbstractPlaceType LILAYA_HOME_FOUNTAIN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Fountain",
			"In the very centre of the garden, a huge, ornate water fountain happily bubbles away with a mind of its own.",
			"dominion/lilayasHome/fountain",
			PresetColour.BASE_BLUE_LIGHT,
			LilayaHomeGeneric.FOUNTAIN,
			Darkness.DAYLIGHT,
			null, "in Lilaya's garden"
			).initItemsPersistInTile()
			.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_GREEN)
			.initAquatic(Aquatic.MIXED);

	public static final AbstractPlaceType LILAYA_HOME_UNDER_CONSTRUCTION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Building site",
			"This section of Lilaya's mansion is currently being extended, and so resembles a building site...",
			"dominion/lilayasHome/construction",
			PresetColour.BASE_BROWN,
			LilayaSpa.SPA_CONSTRUCTION,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's spa"
			) {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isWorkTime()) {
				pop.add(new Population(true, PopulationType.CONSTRUCTION_WORKER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_SPA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Spa pools",
			"A series of pools, filled with warm water drawn from geothermal springs, is the centrepiece of the private spa.",
			"dominion/lilayasHome/roomSpa",
			PresetColour.BASE_TEAL,
			LilayaSpa.SPA_CORE,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's spa"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_SPA_POOL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Swimming pool",
			"A large indoor swimming pool has been installed in this area.",
			"dominion/lilayasHome/roomSpaPool",
			PresetColour.BASE_BLUE_LIGHT,
			LilayaSpa.SPA_POOL,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's spa"
			).initItemsPersistInTile();
	
	public static final AbstractPlaceType LILAYA_HOME_SPA_SAUNA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Sauna",
			"Both a large sauna and steam room have been constructed in this area.",
			"dominion/lilayasHome/roomSpaSauna",
			PresetColour.BASE_BROWN,
			LilayaSpa.SPA_SAUNA,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's spa"
			).initItemsPersistInTile();
	
	
	
	// Lilaya's home (first floor):

	public static final AbstractPlaceType LILAYA_HOME_ROOM_LILAYA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Lilaya's Room",
			"Lilaya's room is situated in a corner of the first floor, and is noticeably adjacent to Rose's...",
			"dominion/lilayasHome/roomLilaya",
			PresetColour.BASE_CRIMSON,
			LilayasRoom.ROOM_LILAYA,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_ROSE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Rose's Room",
			"Rose's room is situated in a corner of the first floor, and is noticeably adjacent to Lilaya's...",
			"dominion/lilayasHome/roomRose",
			PresetColour.BASE_PINK,
			LilayaHomeGeneric.ROOM_ROSE,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_PLAYER = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Your Room",
			"The room given freely to you by Lilaya; this is the once place in Dominion in which you can truly have a care-free rest.",
			"dominion/lilayasHome/roomPlayer",
			PresetColour.BASE_AQUA,
			RoomPlayer.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in your room"
			) {
				@Override
				public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
					return Util.newArrayListOfValues(
							PlaceUpgrade.LILAYA_PLAYER_ROOM_BED);
				}
				@Override
				public boolean isAbleToBeUpgraded() {
					return true;
				}
			}.initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_STAIR_DOWN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Staircase",
			"This staircase connects the first and ground floor of Lilaya's home, and has a small, secondary landing area half-way down.",
			"dominion/lilayasHome/stairsDown",
			PresetColour.BASE_RED,
			LilayaHomeGeneric.STAIRCASE_DOWN,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();

	public static final AbstractPlaceType LILAYA_HOME_STAIR_DOWN_SECONDARY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Staircase",
			"This staircase, while smaller than the main one near the mansion's entrance, performs the same purpose of connecting the ground and first floor of Lilaya's home.",
			"dominion/lilayasHome/stairsDownSecondary",
			PresetColour.BASE_RED_LIGHT,
			LilayaHomeGeneric.STAIRCASE_DOWN_SECONDARY,
			Darkness.ALWAYS_LIGHT,
			null, "in Lilaya's Home"
			).initItemsPersistInTile()
			.initWeatherImmune();
	

	
	
	// Zaranix's home (ground floor):
	
	public static final AbstractPlaceType ZARANIX_GF_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"Numerous fine paintings, cushioned chairs, and well-crafted cabinets line the corridors of Zaranix's home.",
			null,
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeGroundFloor.CORRIDOR;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Staircase",
			"This staircase connects the ground and first floor of Zaranix's home.",
			"dominion/zaranixHome/stairsDown",
			PresetColour.BASE_GREEN_LIGHT,
			ZaranixHomeGroundFloor.STAIRS,
			Darkness.ALWAYS_LIGHT,
			null, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.STAIRS;
				
			} else {
				return ZaranixHomeGroundFloor.STAIRS;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entrance",
			"A huge, crystal chandelier casts its bright, arcane-powered light over the entrance hall, and fine paintings in golden frames hang on each of the surrounding walls.",
			"dominion/zaranixHome/entranceHall",
			PresetColour.BASE_RED,
			ZaranixHomeGroundFloor.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.ENTRANCE;
				
			} else {
				return ZaranixHomeGroundFloor.ENTRANCE;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_LOUNGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Lounge",
			"Several sofas and chairs are tastefully arranged around a low table in the centre of the room, while numerous bookcases and cabinets line the floral-wallpapered walls.",
			"dominion/zaranixHome/lounge",
			PresetColour.BASE_ORANGE,
			ZaranixHomeGroundFloor.LOUNGE,
			Darkness.ALWAYS_LIGHT,
			null, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.LOUNGE;
				
			} else {
				return ZaranixHomeGroundFloor.LOUNGE;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Room",
			"The door to this room is locked, and there's no sound of anyone within.",
			"dominion/zaranixHome/room",
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in a room in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.ROOM;
				
			} else {
				return ZaranixHomeGroundFloor.ROOM;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_MAID = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"An ivory-skinned succubus, wearing a light pink maid's uniform, is busily dusting this area.",
			null,
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.CORRIDOR_MAID,
			Darkness.ALWAYS_LIGHT,
			null, "in Zaranix's Home"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
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
			WorldRegion.DOMINION,
			"Room",
			"A rather uninteresting room which links the garden to the rest of Zaranix's house.",
			"dominion/zaranixHome/room",
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.GARDEN_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in a room in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN_ROOM;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN_ROOM;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_GARDEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Garden",
			"A garden in which all sorts of strange and exotic-looking plants are being grown.",
			"dominion/zaranixHome/garden",
			PresetColour.BASE_GREEN,
			ZaranixHomeGroundFloor.GARDEN,
			Darkness.DAYLIGHT,
			null, "in Zaranix's garden"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN;
			}
		}
	};
	
	public static final AbstractPlaceType ZARANIX_GF_GARDEN_ENTRY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Garden",
			"This particular area is next to the fence separating Zaranix's garden from Dominion's streets.",
			"dominion/zaranixHome/entranceHall",
			PresetColour.BASE_GREEN,
			ZaranixHomeGroundFloor.GARDEN_ENTRY,
			Darkness.DAYLIGHT,
			null, "in Zaranix's garden"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN_ENTRY;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN_ENTRY;
			}
		}
	};
	
	
	
	// Zaranix's home (first floor):
	
	public static final AbstractPlaceType ZARANIX_FF_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"Numerous fine paintings, cushioned chairs, and well-crafted cabinets line the corridors of Zaranix's home.",
			null,
			PresetColour.BASE_GREY,
			ZaranixHomeFirstFloor.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeFirstFloor.CORRIDOR;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Staircase",
			"This staircase connects the first and ground floor of Zaranix's home.",
			"dominion/zaranixHome/stairsDown",
			PresetColour.BASE_RED,
			ZaranixHomeFirstFloor.STAIRS,
			Darkness.ALWAYS_LIGHT,
			null, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.STAIRS;
				
			} else {
				return ZaranixHomeFirstFloor.STAIRS;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Zaranix's Room",
			"Zaranix's office, which has been converted into a small-scale laboratory.",
			"dominion/zaranixHome/roomZaranix",
			PresetColour.BASE_GREEN_LIME,
			ZaranixHomeFirstFloor.ZARANIX_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
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
			WorldRegion.DOMINION,
			"Room",
			"The door to this room is locked, and there's no sound of anyone within.",
			"dominion/zaranixHome/room",
			PresetColour.BASE_GREY,
			ZaranixHomeFirstFloor.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in a room in Zaranix's home"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.ROOM;
				
			} else {
				return ZaranixHomeFirstFloor.ROOM;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_MAID = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"An ivory-skinned succubus, wearing a light pink maid's uniform, is busily dusting this area.",
			null,
			PresetColour.BASE_RED,
			ZaranixHomeFirstFloor.CORRIDOR_MAID,
			Darkness.ALWAYS_LIGHT,
			null, "in Zaranix's Home"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
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
			WorldRegion.DOMINION,
			"Corridor",
			"The corridors of Angel's Kiss are carpeted in rich burgundy, while the walls are half-panelled in dark wood, half covered in light-blue floral wallpaper.",
			null,
			PresetColour.BASE_GREY,
			RedLightDistrict.ANGELS_KISS_CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in Angel's Kiss"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entrance Hall",
			"A golden chandelier dangles from the high ceiling, illuminating the entrance hall's long, mahogany counter in a soft white light.",
			"dominion/angelsKiss/entrance",
			PresetColour.BASE_RED,
			RedLightDistrict.ANGELS_KISS_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in Angel's Kiss"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_STAIRCASE_UP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Staircase",
			"This staircase links the ground and first floors of Angel's Kiss.",
			"dominion/angelsKiss/stairsUp",
			PresetColour.BASE_GREEN_LIGHT,
			RedLightDistrict.ANGELS_KISS_STAIRS_UP,
			Darkness.ALWAYS_LIGHT,
			null, "in Angel's Kiss"
		).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_STAIRCASE_DOWN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Staircase",
			"This staircase links the first and ground floors of Angel's Kiss.",
			"dominion/angelsKiss/stairsDown",
			PresetColour.BASE_RED,
			RedLightDistrict.ANGELS_KISS_STAIRS_DOWN,
			Darkness.ALWAYS_LIGHT,
			null, "in Angel's Kiss"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Bedroom",
			"These bedrooms are where the establishment's business is conducted, and for that purpose, each one contains a clean, king-sized bed.",
			"dominion/angelsKiss/bedroom",
			PresetColour.BASE_PINK,
			RedLightDistrict.ANGELS_KISS_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in Angel's Kiss"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_BEDROOM_BUNNY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Bunny's Bedroom",
			"This bedroom is home to the submissive prostitute, 'Bunny', who is the twin to her sister, 'Loppy'.",
			"dominion/angelsKiss/bedroomBunny",
			PresetColour.BASE_PINK_LIGHT,
			RedLightDistrict.ANGELS_KISS_BEDROOM_BUNNY,
			Darkness.ALWAYS_LIGHT,
			null, "in Bunny's Bedroom"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_BEDROOM_LOPPY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Loppy's Bedroom",
			"This bedroom is home to the dominant prostitute, 'Loppy', who is the twin to her sister, 'Bunny'.",
			"dominion/angelsKiss/bedroomLoppy",
			PresetColour.BASE_PURPLE,
			RedLightDistrict.ANGELS_KISS_BEDROOM_LOPPY,
			Darkness.ALWAYS_LIGHT,
			null, "in Loppy's Bedroom"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Angel's Office",
			"The room in which Angel does all of the paperwork required of her as the red-light district's 'Enforcer-sanctioned administration centre'.",
			"dominion/angelsKiss/office",
			PresetColour.BASE_BLUE_LIGHT,
			RedLightDistrict.ANGELS_KISS_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "in Angel's Office"
			).initWeatherImmune();
	
	
	
	
	// Shopping arcade:
	
	public static final AbstractPlaceType SHOPPING_ARCADE_PATH = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Arcade",
			"The main thoroughfares running through the shopping arcade are flanked on both sides by all different sorts of shops.",
			null,
			PresetColour.BASE_BLACK,
			ShoppingArcadeDialogue.ARCADE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Shopping Arcade") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			pop.add(new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
			return pop;
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_GENERIC_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Shop",
			"One of the many nondescript shops in the arcade, this particular store doesn't offer anything that's worth your time or money.",
			"dominion/shoppingArcade/genericShop",
			PresetColour.BASE_BLACK,
			ShoppingArcadeDialogue.GENERIC_SHOP,
			Darkness.ALWAYS_LIGHT,
			null, "in the Shopping Arcade") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isExtendedWorkTime()) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				return new ArrayList<>();
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_RALPHS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ralph's Snacks",
			"A shop specialising in the sale of food, drink, and other miscellaneous items. The friendly namesake of 'Ralph's Snacks' is a muscular, brown-haired greater horse-boy.",
			"dominion/shoppingArcade/ralphShop",
			PresetColour.BASE_TEAL,
			RalphsSnacks.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "in his store"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_NYANS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Nyan's Clothing Emporium",
			"The two-story shop, 'Nyan's Clothing Emporium', is the largest store in all of the arcade.",
			"dominion/shoppingArcade/nyanShop",
			PresetColour.BASE_ROSE,
			ClothingEmporium.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "in her store"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_VICKYS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Arcane Arts",
			"Specialising in selling arcane weaponry and associated supplies, 'Arcane Arts' is run by a particularly fierce-looking wolf-girl named Vicky.",
			"dominion/shoppingArcade/vickyShop",
			PresetColour.BASE_MAGENTA,
			ArcaneArts.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "in her store"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_KATES_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Succubi's Secrets",
			"With boarded-up windows and peeling paintwork, this beauty salon appears to be derelict at first glance, but a closer inspection reveals an 'open for business' sign hanging on the door.",
			"dominion/shoppingArcade/kateShop",
			PresetColour.BASE_PINK,
			SuccubisSecrets.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "in her beauty salon"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_ASHLEYS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Dream Lover",
			"The only store you've seen in Dominion which specialises in gifts for others.",
			"dominion/shoppingArcade/ashleyShop",
			PresetColour.BASE_LILAC_LIGHT,
			DreamLover.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "in their store"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_ANTIQUES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Antiques",
			"An antiques shop, selling an eclectic mix of creaky old furniture, obsolete arcane instruments, and all manner of outdated miscellaneous items.",
			"dominion/shoppingArcade/antiques",
			PresetColour.BASE_BROWN,
			ShoppingArcadeDialogue.ANTIQUES,
			Darkness.ALWAYS_LIGHT,
			null, "in an antique shop"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_RESTAURANT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"The Oaken Glade",
			"The restaurant 'The Oaken Glade' caters to the more affluent of the Shopping Arcade's patrons, with their most basic three-course meal set at the eye-watering price of almost one thousand flames.",
			"dominion/shoppingArcade/restaurant",
			PresetColour.BASE_GREEN_DARK,
			ShoppingArcadeDialogue.RESTAURANT,
			Darkness.ALWAYS_LIGHT,
			null, "in 'The Oaken Glade'") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getHourOfDay()>=18) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.DINER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				return new ArrayList<>();
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_PIXS_GYM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Pix's Playground",
			"A huge, multi-story gym, 'Pix's Playground' is both owned and run by a particularly energetic border collie-girl.",
			"dominion/shoppingArcade/gym",
			PresetColour.BASE_GOLD,
			PixsPlayground.GYM_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the gym, 'Pix's Playground'"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Exit",
			"These large, glass doors lead back out into Dominion's streets.",
			"dominion/shoppingArcade/exit",
			PresetColour.BASE_RED,
			ShoppingArcadeDialogue.ENTRY,
			Darkness.ALWAYS_LIGHT,
			null, "in the Shopping Arcade"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_TOILETS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Toilets",
			"Conveniently positioned near to the entrance of the Shopping Arcade, there are some public toilets.",
			"dominion/shoppingArcade/toilets",
			PresetColour.BASE_BLUE_LIGHT,
			ShoppingArcadeDialogue.TOILETS,
			Darkness.ALWAYS_LIGHT,
			null, "in the public toilets in the Shopping Arcade") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isExtendedWorkTime()) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				return new ArrayList<>();
			}
		}
	}.initWeatherImmune();
	
	
	
	// Supplier Depot:
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"A well-lit corridor, open to the warehouse's storage and weaving areas, runs down the full length of the building.",
			null,
			PresetColour.BASE_BLACK,
			KaysWarehouse.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null,
			"in Kay's Textiles") {
		@Override
		public String getTooltipDescription() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return "With the Dobermanns dealt with, the corridor in the rear of the building has been renovated, and is now both clean and well-lit.";
			} else {
				return tooltipDescription;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Reception Area",
			"The warehouse's reception area is staffed by a depressed-looking dog-girl, who doesn't seem at all interested in fulfilling her duties.",
			"dominion/textilesWarehouse/exit",
			PresetColour.BASE_RED,
			KaysWarehouse.RECEPTION,
			Darkness.ALWAYS_LIGHT,
			null,
			"in Kay's Textiles") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.RECEPTIONIST, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.RABBIT_MORPH, SubspeciesSpawnRarity.TEN))));
		}
		@Override
		public String getTooltipDescription() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return "With the Dobermanns dealt with, the depressed dog-girl who staffs the reception desk seems a lot happier and eager to help visitors.";
			} else {
				return tooltipDescription;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_STORAGE_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Storage Room",
			"Numerous crates fill this storage room, each one packed full of enchanted clothing.",
			"dominion/textilesWarehouse/storage",
			PresetColour.BASE_RED,
			KaysWarehouse.STORAGE_AREA,
			Darkness.ALWAYS_LIGHT,
			null,
			"in Kay's Textiles"
			) {
		@Override
		public Colour getColour() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayCratesSearched)
					|| !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS)) {
				return PresetColour.BASE_RED;
			} else {
				return PresetColour.BASE_GREEN;
			}
		}
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayCratesSearched)
					|| !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS)) {
				return SVGString;
			} else {
				return getSVGOverride("dominion/textilesWarehouse/storage", PresetColour.BASE_GREEN);
			}
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_ENCHANTING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Weaving Machines",
			"A large number of what look to be arcane weaving machines are being operated by the dozens of textile workers in this area.",
			"dominion/textilesWarehouse/enchanting",
			PresetColour.GENERIC_ARCANE,
			KaysWarehouse.WEAVING_MACHINES,
			Darkness.ALWAYS_LIGHT,
			null,
			"in Kay's Textiles"
			) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(
					new Population(true, PopulationType.TEXTILE_WORKER, PopulationDensity.NUMEROUS,
							Util.newHashMapOfValues(
									new Value<>(Subspecies.RABBIT_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.CAT_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.DOG_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.FOX_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.COW_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_OVERSEER_STATION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Overseer Station",
			"A set of steps leads up to a mezzanine floor, upon which an overseer's station looks out over the entire warehouse floor.",
			"dominion/textilesWarehouse/overseer_station",
			PresetColour.BASE_GREY_DARK,
			KaysWarehouse.OVERSEER_STATION,
			Darkness.ALWAYS_LIGHT,
			null,
			"in Kay's Textiles"
			) {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getWorlds().get(WorldType.TEXTILES_WAREHOUSE).getCell(PlaceType.TEXTILE_WAREHOUSE_OFFICE).isTravelledTo()) {
				return Util.newArrayListOfValues(new Population(false, PopulationType.TEXTILE_WORKER, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.FOX_MORPH, SubspeciesSpawnRarity.TEN))));
			} else {
				return super.getPopulation();
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS);
		}
		@Override
		public String getTooltipDescription() {
			if(Main.game.getWorlds().get(WorldType.TEXTILES_WAREHOUSE).getCell(PlaceType.TEXTILE_WAREHOUSE_OFFICE).isTravelledTo()) {
				return "With the Dobermanns dealt with, the overseer station outside of Kay's office is now appropriately staffed by one of the textile workers.";
			} else {
				return tooltipDescription;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Kay's Office",
			"Accessed via the overseer's station, Kay's office is rather small, although very elegantly furnished.",
			"dominion/textilesWarehouse/office",
			PresetColour.BASE_BLUE_LIGHT,
			KaysWarehouse.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null,
			"in Kay's Textiles").initWeatherImmune();
	
	
	
	// Slaver Alley:
	
	public static final AbstractPlaceType SLAVER_ALLEY_PATH = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Alleyway",
			"The alleyways running through Slaver Alley are nothing like those found in the rest of Dominion, as they are busy, clean, and most importantly of all, very safe.",
			null,
			PresetColour.BASE_BLACK,
			SlaverAlleyDialogue.ALLEYWAY,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			pop.add(new Population(false, PopulationType.PRIVATE_SECURITY_GUARD, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_FEMALES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"A Woman's Touch",
			"A store specialising in selling well-trained and obedient slave girls.",
			"dominion/slaverAlley/marketStallFemale",
			PresetColour.BASE_PINK_LIGHT,
			SlaverAlleyDialogue.MARKET_STALL_FEMALE,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_MALES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Iron & Steel",
			"From manual labourers to eye-pleasing models, this store specialises in selling all different sorts of male slaves.",
			"dominion/slaverAlley/marketStallMale",
			PresetColour.BASE_BLUE_STEEL,
			SlaverAlleyDialogue.MARKET_STALL_MALE,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_ANAL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"The Rear Entrance",
			"Specialising in selling slaves who have trained to love anal sex, this store is one of three which surrounds the lewd statue of Marlel.",
			"dominion/slaverAlley/marketStallAnal",
			PresetColour.BASE_ORANGE,
			SlaverAlleyDialogue.MARKET_STALL_ANAL,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_VAGINAL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"White Lilies",
			"One of the three stores surrounding the statue of the fallen angel, Marlel, 'White Lillies' specialises in selling female virgins.",
			"dominion/slaverAlley/marketStallVaginal",
			PresetColour.BASE_PINK,
			SlaverAlleyDialogue.MARKET_STALL_VAGINAL,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_ORAL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Viva Voce",
			"'Viva Voce' specialises in selling slaves trained to love oral sex, and is one of three specialist stores found surrounding the statue of Marlel.",
			"dominion/slaverAlley/marketStallOral",
			PresetColour.BASE_BLUE_LIGHT,
			SlaverAlleyDialogue.MARKET_STALL_ORAL,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STATUE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Statue of the Fallen Angel",
			"Standing atop a high plinth, this statue of the fallen angel, Marlel, shows her in a highly-corrupted and incredibly lewd form.",
			"dominion/slaverAlley/marketStallStatue",
			PresetColour.BASE_BLACK,
			SlaverAlleyDialogue.MARKET_STALL_STATUE,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_MARKET_STALL_EXCLUSIVE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Slave Rental Store",
			"Quite a number of the stores in Slaver Alley do not directly sell slaves, but instead rent them out to businesses looking to fill a temporary gap in labour.",
			"dominion/slaverAlley/marketStallExclusive",
			PresetColour.BASE_GREY,
			SlaverAlleyDialogue.MARKET_STALL_EXCLUSIVE,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_MARKET_STALL_BULK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Zaibatsu Exchange",
			"By far the largest store in all of Slaver Alley, the 'Zaibatsu Exchange' is controlled by a powerful slaver conglomerate, and refuses to do business with non-members.",
			"dominion/slaverAlley/marketStallBulk",
			PresetColour.BASE_BLUE,
			SlaverAlleyDialogue.MARKET_STALL_BULK,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_CAFE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Cafe",
			"Numerous cafes are scattered throughout Slaver Alley, providing a place for shoppers to rest and replenish their energy.",
			"dominion/slaverAlley/marketStallCafe",
			PresetColour.BASE_BROWN,
			SlaverAlleyDialogue.MARKET_STALL_CAFE,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_CAFE_2 = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Cafe",
			"Numerous cafes are scattered throughout Slaver Alley, providing a place for shoppers to rest and replenish their energy.",
			"dominion/slaverAlley/marketStallCafe",
			PresetColour.BASE_BROWN,
			SlaverAlleyDialogue.MARKET_STALL_CAFE,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	
	public static final AbstractPlaceType SLAVER_ALLEY_CAFE_3 = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Cafe",
			"Numerous cafes are scattered throughout Slaver Alley, providing a place for shoppers to rest and replenish their energy.",
			"dominion/slaverAlley/marketStallCafe",
			PresetColour.BASE_BROWN,
			SlaverAlleyDialogue.MARKET_STALL_CAFE,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	
	public static final AbstractPlaceType SLAVER_ALLEY_CAFE_4 = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Cafe",
			"Numerous cafes are scattered throughout Slaver Alley, providing a place for shoppers to rest and replenish their energy.",
			"dominion/slaverAlley/marketStallCafe",
			PresetColour.BASE_BROWN,
			SlaverAlleyDialogue.MARKET_STALL_CAFE,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_AUCTIONING_BLOCK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Auctioning Block",
			"A huge wooden platform sits in the middle of an expansive square, and it's upon this that public auctions are held.",
			"dominion/slaverAlley/auctionBlock",
			PresetColour.BASE_GOLD,
			SlaverAlleyDialogue.AUCTION_BLOCK,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_PUBLIC_STOCKS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Public Stocks",
			"A series of public-use stocks are positioned immediately in front of the entrance to Slaver Alley, serving as a reminder of what happens to disobedient slaves.",
			"dominion/slaverAlley/stocks",
			PresetColour.BASE_TAN,
			SlaverAlleyDialogue.PUBLIC_STOCKS,
			Darkness.ALWAYS_LIGHT,
			null, "in the stocks at Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_BOUNTY_HUNTERS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"'The Rusty Collar'",
			"A tavern in which bounty hunters can be contracted to track down runaway slaves.",
			"dominion/slaverAlley/bountyHunters",
			PresetColour.BASE_COPPER,
			SlaverAlleyDialogue.BOUNTY_HUNTERS,
			Darkness.ALWAYS_LIGHT,
			null,
			"in Slaver Alley"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_SLAVERY_ADMINISTRATION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Slavery Administration",
			"The main administrative hub for all matters related to the ownership of slaves.",
			"dominion/slaverAlley/slaveryAdministration",
			PresetColour.BASE_PURPLE,
			SlaveryAdministration.SLAVERY_ADMINISTRATION_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley"){
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.SLAVERY_ADMINISTRATION_CELLS);
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_SCARLETTS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Scarlett's Shop",
			"",
			"dominion/slaverAlley/scarlettsStall",
			PresetColour.BASE_CRIMSON,
			ScarlettsShop.SCARLETTS_SHOP_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley"){
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
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
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
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
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
								new Population(true, PopulationType.HARPY, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.HARPY_NEST, this, false, false)),
								new Population(true, PopulationType.SHOPPER, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)),
								new Population(true, PopulationType.FAN, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					} else {
						return Util.newArrayListOfValues(new Population(true, PopulationType.FAN, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					}
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) && !Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
					if(Main.game.getNpc(Helena.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP) {
						return Util.newArrayListOfValues(new Population(true, PopulationType.FAN, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					} else {
						return Util.newArrayListOfValues(new Population(true, PopulationType.FAN, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					}
				}
			}
			return new ArrayList<>();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Gateway",
			"Slaver Alley's single entrance and exit is guarded by a pair of horse-boys who keep a keen watch for any runaway slaves.",
			"dominion/slaverAlley/exit",
			PresetColour.BASE_RED,
			SlaverAlleyDialogue.GATEWAY,
			Darkness.ALWAYS_LIGHT,
			null, "in Slaver Alley") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(
					new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)),
					new Population(true, PopulationType.PRIVATE_SECURITY_GUARD, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_DESERTED_ALLEYWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Deserted alleyway",
			"A narrow alleyway snakes its way behind several shops, before coming to a sudden dead end.",
			"dominion/slaverAlley/desertedAlleyway",
			PresetColour.BASE_BLACK,
			SlaverAlleyDialogue.DESERTED_ALLEYWAY,
			Darkness.DAYLIGHT,
			null, "in Slaver Alley").initWeatherImmune(Weather.MAGIC_STORM);
	
	
	// Bounty hunter lodge:

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entrance",
			"The main entrance to the tavern takes the form of a pair of weathered, wooden doors.",
			"dominion/slaverAlley/bountyHunterLodge/exit",
			PresetColour.BASE_RED,
			BountyHunterLodge.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null,
			"in 'The Rusty Collar'")
		.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Main floor",
			"The majority of the floor area inside the tavern is occupied by a dozen large, wooden tables, around which bounty hunters and all sorts of unsavoury individuals are seated.",
			null,
			PresetColour.BASE_BLACK,
			BountyHunterLodge.FLOOR,
			Darkness.ALWAYS_LIGHT,
			null,
			"in 'The Rusty Collar'"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_BOUNTY_BOARD = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Bounty board",
			"Next to the main entrance, there's a huge wooden board, onto which active bounties are posted.",
			"dominion/slaverAlley/bountyHunterLodge/bountyBoard",
			PresetColour.CLOTHING_BLUE_GREY,
			BountyHunterLodge.BOUNTY_BOARD,
			Darkness.ALWAYS_LIGHT,
			null,
			"in 'The Rusty Collar'"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_BAR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Bar",
			"The long bar is always busy, and keeps the tavern's many patrons plied with all sorts of alcoholic beverages.",
			"dominion/slaverAlley/bountyHunterLodge/bar",
			PresetColour.BASE_ORANGE,
			BountyHunterLodge.BAR,
			Darkness.ALWAYS_LIGHT,
			null,
			"in 'The Rusty Collar'"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_SEATING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Recessed seating",
			"Around the edges of the tavern's main floor, there are several recessed seating areas.",
			"dominion/slaverAlley/bountyHunterLodge/seatingArea",
			PresetColour.BASE_BROWN,
			BountyHunterLodge.SEATING,
			Darkness.ALWAYS_LIGHT,
			null,
			"in 'The Rusty Collar'"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Stairs",
			"A narrow staircase leads up to the tavern's first floor.",
			"dominion/slaverAlley/bountyHunterLodge/stairsUp",
			PresetColour.BASE_GREEN_LIGHT,
			BountyHunterLodge.STAIRS,
			Darkness.ALWAYS_LIGHT,
			null,
			"in 'The Rusty Collar'")
		.initWeatherImmune(Weather.MAGIC_STORM);
	
	// First floor:
	
	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Corridor",
			"A narrow corridor links all the rooms on the tavern's first floor.",
			null,
			PresetColour.BASE_BLACK,
			BountyHunterLodge.UPSTAIRS_CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null,
			"in 'The Rusty Collar'")
		.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Stairs",
			"A narrow staircase leads down to the tavern's ground floor.",
			"dominion/slaverAlley/bountyHunterLodge/stairsDown",
			PresetColour.BASE_RED_LIGHT,
			BountyHunterLodge.UPSTAIRS_STAIRS,
			Darkness.ALWAYS_LIGHT,
			null,
			"in 'The Rusty Collar'")
		.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Room",
			"Just like all of the other rooms here, this one has already been rented out...",
			"dominion/slaverAlley/bountyHunterLodge/room",
			PresetColour.BASE_TEAL,
			BountyHunterLodge.UPSTAIRS_ROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"in 'The Rusty Collar'")
		.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM_DOBERMANNS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Room",
			"Just like all of the other rooms here, this one has already been rented out...",
			"dominion/slaverAlley/bountyHunterLodge/room",
			PresetColour.BASE_TEAL,
			BountyHunterLodge.UPSTAIRS_ROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"in 'The Rusty Collar'")
		.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM_SHADOW_SILENCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Room",
			"Just like all of the other rooms here, this one has already been rented out...",
			"dominion/slaverAlley/bountyHunterLodge/room",
			PresetColour.BASE_TEAL,
			BountyHunterLodge.UPSTAIRS_ROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"in 'The Rusty Collar'")
		.initWeatherImmune(Weather.MAGIC_STORM);
	
	
	// Watering hole:
	
	public static final AbstractPlaceType WATERING_HOLE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entrance",
			"The entrance to the club, 'The Watering Hole', is guarded by a pair of zebra-boy bouncers.",
			"dominion/nightLife/exit",
			PresetColour.BASE_RED,
			NightlifeDistrict.WATERING_HOLE_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_MAIN_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"The Watering Hole",
			"The club is absolutely packed with people, who are busy drinking, chatting, and making out with one another.",
			null,
			PresetColour.BASE_BLUE_LIGHT,
			NightlifeDistrict.WATERING_HOLE_MAIN,
			Darkness.ALWAYS_LIGHT,
			null, "in 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return WATERING_HOLE_ENTRANCE.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_SEATING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Seating Area",
			"Areas set aside on the edge of the club offer relatively peaceful places for revellers to sit down and talk with one another.",
			"dominion/nightLife/seatingArea",
			PresetColour.BASE_BROWN,
			NightlifeDistrict.WATERING_HOLE_SEATING,
			Darkness.ALWAYS_LIGHT,
			null, "in 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_VIP_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"VIP Area",
			"Guarded by a pair of muscular lion bouncers, the club's VIP area consists of numerous semi-circular booths, each one housing a polished black marble table and a long, curved leather sofa.",
			"dominion/nightLife/vipArea",
			PresetColour.BASE_PURPLE,
			NightlifeDistrict.WATERING_HOLE_VIP,
			Darkness.ALWAYS_LIGHT,
			null, "in 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.VIP, PopulationDensity.SEVERAL,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_lion"), SubspeciesSpawnRarity.TEN),
							new Value<>(Subspecies.HORSE_MORPH_ZEBRA, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_BAR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Bar",
			"The club's bar is perpetually busy, with the numerous zebra and lion-girl barmaids serving order after order.",
			"dominion/nightLife/bar",
			PresetColour.BASE_ORANGE,
			NightlifeDistrict.WATERING_HOLE_BAR,
			Darkness.ALWAYS_LIGHT,
			null, "in 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_DANCE_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Dance Floor",
			"The central dance floor is the liveliest, and loudest, place in the entire club.",
			"dominion/nightLife/danceFloor",
			PresetColour.BASE_PINK_DEEP,
			NightlifeDistrict.WATERING_HOLE_DANCE_FLOOR,
			Darkness.ALWAYS_LIGHT,
			null, "in 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_TOILETS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Toilets",
			"The club's toilets are quite large, and contain numerous stalls and sinks.",
			"dominion/nightLife/toilets",
			PresetColour.BASE_BLUE_LIGHT,
			NightlifeDistrict.WATERING_HOLE_TOILETS,
			Darkness.ALWAYS_LIGHT,
			null, "in the toilets of 'The Watering Hole'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
	}.initWeatherImmune();
	
	
	// Daddy's apartment:
	
	public static final AbstractPlaceType DADDY_APARTMENT_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entrance Hall",
			"The entrance hall to the apartment.",
			"dominion/daddy/entranceHall",
			PresetColour.BASE_GREEN,
			DaddyDialogue.PLACE_ENTRANCE_HALL,
			Darkness.ALWAYS_LIGHT,
			null, "in the entrance hall of Daddy's apartment"
		).initWeatherImmune();

	public static final AbstractPlaceType DADDY_APARTMENT_LOUNGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Lounge",
			"The apartment's lounge.",
			"dominion/daddy/lounge",
			PresetColour.BASE_ORANGE,
			DaddyDialogue.PLACE_LOUNGE,
			Darkness.ALWAYS_LIGHT,
			null, "in the lounge of Daddy's apartment"
		).initWeatherImmune();

	public static final AbstractPlaceType DADDY_APARTMENT_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Kitchen",
			"The apartment's kitchen.",
			"dominion/daddy/kitchen",
			PresetColour.BASE_TAN,
			DaddyDialogue.PLACE_KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null, "in the kitchen of Daddy's apartment"
		).initWeatherImmune();

	public static final AbstractPlaceType DADDY_APARTMENT_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Bedroom",
			"The apartment's bedroom.",
			"dominion/daddy/bedroom",
			PresetColour.BASE_CRIMSON,
			DaddyDialogue.PLACE_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in Daddy's bedroom"
		).initWeatherImmune();
	
	
	// Helena's apartment:
	
	public static final AbstractPlaceType HELENA_APARTMENT_HALLWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Hallway",
			"The wide, carpeted hallways connects the rooms in Helena's apartment.",
			null,
			PresetColour.BASE_BLACK,
			HelenaApartment.PLACE_HALLWAY,
			Darkness.ALWAYS_LIGHT,
			null, "in the hallway of Helena's apartment"
		) {
			@Override
			public List<Population> getPopulation() {
				return Util.newArrayListOfValues(new Population(false, PopulationType.MAID, PopulationDensity.OCCASIONAL, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
			}
		}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_BALCONY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Balcony",
			"This large, wooden-decked balcony overlooks the streets of Dominion.",
			null,
			PresetColour.BASE_BLUE_LIGHT,
			HelenaApartment.PLACE_BALCONY,
			Darkness.ALWAYS_LIGHT,
			null, "on the balcony at Helena's apartment"
		).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_BLUE)
		.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entrance Hall",
			"The entrance hall to Helena's penthouse apartment is of an impressive size, and is tastefully decorated in a modern style.",
			"dominion/helenaApartment/entranceHall",
			PresetColour.BASE_GREEN,
			HelenaApartment.PLACE_ENTRANCE_HALL,
			Darkness.ALWAYS_LIGHT,
			null, "in the entrance hall of Helena's apartment"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.MAID, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_HELENA_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Helena's Bedroom",
			"Helena's bedroom is of an impressive size and is luxuriously furnished.",
			"dominion/helenaApartment/bedroomHelena",
			PresetColour.BASE_GOLD,
			HelenaApartment.PLACE_HELENA_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in Helena's bedroom"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_SCARLETT_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Scarlett's Bedroom",
			"Scarlett's bedroom is of an impressive size and is luxuriously furnished.",
			"dominion/helenaApartment/bedroomScarlett",
			PresetColour.BASE_CRIMSON,
			HelenaApartment.PLACE_SCARLETT_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in Scarlett's bedroom"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Guest Bedroom",
			"A couple of spare bedrooms stand ready to receive any guests who might happen to stay here overnight.",
			"dominion/helenaApartment/bedroom",
			PresetColour.BASE_YELLOW,
			HelenaApartment.PLACE_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in one of the guest bedrooms in Helena's apartment"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_BATHROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ensuite Bathroom",
			"Each of the bedrooms in Helena's apartment have their own private ensuite bathroom.",
			"dominion/helenaApartment/bathroom",
			PresetColour.BASE_BLUE_LIGHT,
			HelenaApartment.PLACE_BATHROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in one of the bathrooms in Helena's apartment"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Study Room",
			"Situated in the middle of Helena's apartment, her personal study room is where she goes to think and spend time to herself.",
			"dominion/helenaApartment/office",
			PresetColour.BASE_BROWN,
			HelenaApartment.PLACE_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "in the study room in Helena's apartment"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Kitchen",
			"The kitchen in Helena's apartment is staffed at all times by an excellent professional chef.",
			"dominion/helenaApartment/kitchen",
			PresetColour.BASE_ORANGE,
			HelenaApartment.PLACE_KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null, "in the kitchen in Helena's apartment"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CHEF, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_DINING_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Dining Room",
			"A large table, surrounded by a dozen chairs, forms the centrepiece of this large dining room.",
			"dominion/helenaApartment/diningRoom",
			PresetColour.BASE_BLUE_STEEL,
			HelenaApartment.PLACE_DINING_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in the dining room in Helena's apartment"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.MAID, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_LOUNGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Lounge",
			"The huge, open-plan lounge in Helena's apartment is home to numerous comfortable sofas.",
			"dominion/helenaApartment/lounge",
			PresetColour.BASE_INDIGO,
			HelenaApartment.PLACE_LOUNGE,
			Darkness.ALWAYS_LIGHT,
			null, "in the lounge in Helena's apartment"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.MAID, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_HOT_TUB = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Hot Tub",
			"In one corner of the balcony, there's a large, arcane-powered hot tub.",
			"dominion/helenaApartment/hotTub",
			PresetColour.BASE_RED_LIGHT,
			HelenaApartment.PLACE_HOT_TUB,
			Darkness.ALWAYS_LIGHT,
			null, "in the hot tub on the balcony of Helena's apartment"
		).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_BLUE)
		.initWeatherImmune();
	
	

	// Helena's apartment:
	
	public static final AbstractPlaceType NYAN_APARTMENT_HALLWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Hallway",
			"The wide, carpeted hallways connects the rooms in Helena's apartment.",
			null,
			PresetColour.BASE_BLACK,
			NyanApartment.HALLWAY,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the hallway of Nyan's apartment"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Entrance Hall",
			"While modesty decorated, the entrance hall to Nyan's apartment is large enough to have a built-in cloak-room.",
			"dominion/nyanApartment/entranceHall",
			PresetColour.BASE_RED,
			NyanApartment.ENTRANCE_HALL,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the entrance hall of Nyan's apartment"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_NYAN_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Nyan's Bedroom",
			"Decorated in soft, pastel colours, and holding an impressive collection of romance novels and stuffed toys, Nyan's bedroom is the one place in which she feels completely calm.",
			"dominion/nyanApartment/bedroomNyan",
			PresetColour.BASE_PINK_LIGHT,
			NyanApartment.NYAN_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"in Nyan's bedroom"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_SPARE_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Spare Bedroom",
			"Directly opposite Nyan's bedroom, there's a fully-furnished spare bedroom.",
			"dominion/nyanApartment/bedroom",
			PresetColour.BASE_LILAC,
			NyanApartment.SPARE_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the spare bedroom in Nyan's apartment"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_ENSUITE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ensuite Bathroom",
			"Nyan's bedroom has an ensuite bathroom, half of which is taken up by a huge bathtub.",
			"dominion/nyanApartment/bathroom",
			PresetColour.BASE_AQUA,
			NyanApartment.ENSUITE,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the ensuite in Nyan's apartment"
		).initWeatherImmune();

	public static final AbstractPlaceType NYAN_APARTMENT_BATHROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Bathroom",
			"This bathroom is quite small, and holds only a toilet and sink.",
			"dominion/nyanApartment/toilet",
			PresetColour.BASE_BLUE_LIGHT,
			NyanApartment.BATHROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the bathroom in Nyan's apartment"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Kitchen",
			"Nyan's open-plan kitchen is joined to her dining room, and is of a reasonable size. The windows offer a decent down into the street below.",
			"dominion/nyanApartment/kitchen",
			PresetColour.BASE_ORANGE,
			NyanApartment.KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the kitchen in Nyan's apartment"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_DINING_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Dining Room",
			"Nyan's open-plan dining room is joined to her kitchen, and contains several cabinets, as well as the expected table and chairs. The windows offer a decent down into the street below.",
			"dominion/nyanApartment/diningRoom",
			PresetColour.BASE_BLUE_STEEL,
			NyanApartment.DINING_ROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the dining room in Nyan's apartment"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_LOUNGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Lounge",
			"The large lounge in Nyan's apartment holds several plush sofas, each of which is covered in colourful cushions.",
			"dominion/nyanApartment/lounge",
			PresetColour.BASE_INDIGO,
			NyanApartment.LOUNGE,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the lounge in Nyan's apartment"
		).initWeatherImmune();
	
	
	
	// Submission:

	public static final AbstractPlaceType SUBMISSION_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Enforcer Checkpoint",
			"In order to keep the imp population from running rampant up into Dominion, every one of Submission's many entrances takes the form of a well-manned Enforcer checkpoint.",
			"submission/submissionExit",
			PresetColour.BASE_BROWN,
			SubmissionGenericPlaces.SEWER_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in Submission") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.ENFORCER, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.ALLIGATOR_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.CAT_MORPH, SubspeciesSpawnRarity.TEN),
							new Value<>(Subspecies.DOG_MORPH, SubspeciesSpawnRarity.TEN),
							new Value<>(Subspecies.FOX_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.RABBIT_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.WOLF_MORPH, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_WALKWAYS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Walkways",
			"Alongside the subterranean waterways which run through most of Submission, well-maintained wooden walkways have been built up against the grey stone walls.",
			null,
			PresetColour.BASE_BLACK,
			SubmissionGenericPlaces.WALKWAYS,
			Darkness.ALWAYS_LIGHT,
			null, "in Submission") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.SUBMISSION, this, false)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.SUBMISSION, this, false)));
			}
			pop.addAll(SUBMISSION_ENTRANCE.getPopulation());
			return pop;
		}
	}.initWeatherImmune()
	.initAquatic(Aquatic.MIXED);

	public static final AbstractPlaceType SUBMISSION_TUNNELS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Tunnels",
			"The shaded alcoves and wide pipe openings make these dark, claustrophobic tunnels the perfect place for an ambush...",
			"submission/tunnelsIcon",
			PresetColour.BASE_BLACK,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "in Submission"
			).initDangerous()
			.initWeatherImmune()
			.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType SUBMISSION_BAT_CAVERNS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Bat Caverns",
			"Submission's walkways come to an end here, and down a deep, dark opening, a series of steep stone steps lead down into the bat caverns below.",
			"submission/batCaverns",
			PresetColour.BASE_BLUE,
			SubmissionGenericPlaces.BAT_CAVERNS,
			Darkness.ALWAYS_LIGHT,
			null, "in Submission"
			).initWeatherImmune()
			.initAquatic(Aquatic.MIXED)
			.initSexNotBlockedFromCharacterPresent();
	
	public static final AbstractPlaceType SUBMISSION_RAT_WARREN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Rat Warrens",
			"The entrance to the rat warrens can be found in this area, and takes the form of a stone archway, sealed off by a pair of heavy oaken doors.",
			"submission/ratWarren",
			PresetColour.BASE_BROWN_DARK,
			SubmissionGenericPlaces.RAT_WARREN,
			Darkness.ALWAYS_DARK,
			null, "in Submission"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_GAMBLING_DEN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Gambling Den",
			"The 'Gambling Den' is by far the most popular attraction in all of Submission, and is the primary destination for visitors from Dominion.",
			"submission/gamblingDen",
			PresetColour.BASE_GOLD,
			SubmissionGenericPlaces.GAMBLING_DEN,
			Darkness.ALWAYS_LIGHT,
			null, "in Submission"
			) {
		@Override
		public List<Population> getPopulation() {
			return SUBMISSION_WALKWAYS.getPopulation();
		}
	}.initWeatherImmune()
	.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType SUBMISSION_LILIN_PALACE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Lyssieth's Palace",
			"The palace of the elder Lilin, Lyssieth, is located in the far corner of Submission.",
			"submission/lilinPalace",
			PresetColour.BASE_PURPLE,
			SubmissionGenericPlaces.LILIN_PALACE,
			Darkness.ALWAYS_LIGHT,
			null, "in Submission"
			).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
			.initWeatherImmune()
			.initTeleportPermissions(TeleportPermissions.NONE);
	
	public static final AbstractPlaceType SUBMISSION_LILIN_PALACE_GATE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Lyssieth's Palace Gate",
			"The palace gates are well guarded by a group of half-demons.",
			"submission/gate",
			PresetColour.BASE_PURPLE_LIGHT,
			SubmissionGenericPlaces.LILIN_PALACE_GATE,
			Darkness.ALWAYS_LIGHT,
			null, "in Submission") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.GUARD, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(new Value<>(Subspecies.HALF_DEMON, SubspeciesSpawnRarity.TEN))));
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
	.initWeatherImmune()
	.initTeleportPermissions(TeleportPermissions.NONE);
	
	public static final AbstractPlaceType SUBMISSION_LILIN_PALACE_CAVERN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Cavern",
			"Lyssieth's palace is located on the far side of a huge cavern, the floor of which steadily slopes downwards to the front gates.",
			null,
			PresetColour.BASE_GREY,
			SubmissionGenericPlaces.LILIN_PALACE_CAVERN,
			Darkness.ALWAYS_DARK,
			null, "in Submission"
			).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
			.initWeatherImmune();
	
	
	
	
	// Alpha succubus imp fortress:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_ALPHA = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Imp Fortress",
			"A crude, walled fortress, constructed upon a raised mound of rock, sits in the middle of a huge underground cave.",
			"submission/impFortress1",
			PresetColour.BASE_CRIMSON,
			SubmissionGenericPlaces.IMP_FORTRESS_ALPHA,
			Darkness.ALWAYS_LIGHT,
			null, "in Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
				return getSVGOverride("submission/impFortress1", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress1", PresetColour.BASE_CRIMSON);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_ALPHA = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Imp Tunnels",
			"These tunnels are particularly dangerous, as they're home to hostile groups of wandering imps.",
			"submission/impTunnels1Icon",
			PresetColour.BASE_RED,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "in Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
				return getSVGOverride("submission/impTunnels1Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels1Icon", PresetColour.BASE_RED);
		}
	}.initDangerous()
	.initWeatherImmune()
	.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType FORTRESS_ALPHA_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Gateway",
			"The front gates of the fortress offer access back out into Submission.",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpFortressDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Alpha Imp Fortress"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_ALPHA_COURTYARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Courtyard",
			"Separating the gateway from the fortress's wooden keep, there's nothing but a deserted, squalid courtyard.",
			null,
			PresetColour.BASE_BLACK,
			ImpFortressDialogue.COURTYARD,
			Darkness.ALWAYS_LIGHT,
			null, "in the Alpha Imp Fortress"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_ALPHA_KEEP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Keep",
			"A crudely-constructed keep serves as the residence for this particular fortress's ruler.",
			"submission/impFortress/keep",
			PresetColour.BASE_CRIMSON,
			ImpFortressDialogue.KEEP,
			Darkness.ALWAYS_LIGHT,
			null, "in the Alpha Imp Fortress"
			).initDangerous()
			.initWeatherImmune();

	
	
	// Imp citadel:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_DEMON = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Imp Citadel",
			"Huge, ceiling-height stone walls form the outer fortifications of a mighty imp citadel.",
			"submission/impFortress2",
			PresetColour.BASE_PURPLE,
			SubmissionGenericPlaces.IMP_FORTRESS_DEMON,
			Darkness.ALWAYS_LIGHT,
			null, "in Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				return getSVGOverride("submission/impFortress2", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress2", PresetColour.BASE_PURPLE_DARK);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_DEMON = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Imp Tunnels",
			"These tunnels are particularly dangerous, as they're home to hostile groups of wandering imps.",
			"submission/impTunnels2Icon",
			PresetColour.BASE_PURPLE,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "in Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				return getSVGOverride("submission/impTunnels2Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels2Icon", PresetColour.BASE_PURPLE);
		}
	}.initDangerous()
	.initWeatherImmune()
	.initSexNotBlockedFromCharacterPresent();
	
	public static final AbstractPlaceType FORTRESS_DEMON_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Gateway",
			"The huge stone gateway serves as the single point of access between the citadel and the cavern beyond.",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpCitadelDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Dark Siren's citadel") {
		@Override
		public List<Population> getPopulation() {
			if(ImpCitadelDialogue.isImpsDefeated() || ImpCitadelDialogue.isDefeated()) {
				return new ArrayList<>();
			}
			return Util.newArrayListOfValues(new Population(true, PopulationType.GUARD, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.IMP_ALPHA, SubspeciesSpawnRarity.THREE),
							new Value<>(Subspecies.IMP, SubspeciesSpawnRarity.TEN))));
		}
		@Override
		public Darkness getDarkness() {
			if(ImpCitadelDialogue.isDefeated()) {
				return Darkness.ALWAYS_DARK;
			}
			return Darkness.ALWAYS_LIGHT;
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_COURTYARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Courtyard",
			"The main keep is separated from the outer fortifications by means of an expansive, paved courtyard.",
			null,
			PresetColour.BASE_BLACK,
			ImpCitadelDialogue.COURTYARD,
			Darkness.ALWAYS_LIGHT,
			null, "in the Dark Siren's citadel") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_ENTRANCE.getPopulation();
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_WELL = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Well",
			"A limitless source of fresh water, such as that provided by this well, is invaluable for any fortress's defenders.",
			"submission/impFortress/well",
			PresetColour.BASE_BLUE_LIGHT,
			ImpCitadelDialogue.WELL,
			Darkness.ALWAYS_LIGHT,
			null, "in the Dark Siren's citadel") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_ENTRANCE.getPopulation();
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_KEEP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Keep",
			"The citadel's main keep has been dug and carved out of the solid rock face of one of the cavern's walls.",
			"submission/impFortress/keep",
			PresetColour.BASE_PURPLE,
			ImpCitadelDialogue.KEEP,
			Darkness.ALWAYS_LIGHT,
			null, "in the Dark Siren's citadel") {
		@Override
		public boolean isDangerous() {
			return Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL);
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_CELLS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Cells",
			"Carved into the cavern's walls, these cells offer a place in which prisoners may be kept.",
			"submission/impFortress/cells",
			PresetColour.BASE_TEAL,
			ImpCitadelDialogue.CELLS,
			Darkness.ALWAYS_LIGHT,
			null, "in the Dark Siren's citadel") {
		@Override
		public List<Population> getPopulation() {
			if(ImpCitadelDialogue.isImpsDefeated() || ImpCitadelDialogue.isDefeated()) {
				return new ArrayList<>();
			}
			return Util.newArrayListOfValues(new Population(true, PopulationType.GUARD, PopulationDensity.FEW,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.IMP_ALPHA, SubspeciesSpawnRarity.THREE),
							new Value<>(Subspecies.IMP, SubspeciesSpawnRarity.TEN))));
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_LAB = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Laboratory",
			"A large stone structure has been constructed on one side of the courtyard, and has the sole purpose of serving as a specialised laboratory.",
			"submission/impFortress/laboratory",
			PresetColour.BASE_GREEN_LIME,
			ImpCitadelDialogue.LABORATORY,
			Darkness.ALWAYS_LIGHT,
			null, "in the Dark Siren's citadel") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_CELLS.getPopulation();
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_TREASURY = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Treasury",
			"Every citadel's ruler needs a place in which they can safely store their precious belongings.",
			"submission/impFortress/treasury",
			PresetColour.BASE_GOLD,
			ImpCitadelDialogue.TREASURY,
			Darkness.ALWAYS_LIGHT,
			null, "in the Dark Siren's citadel") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_CELLS.getPopulation();
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	
	
	// Female seducer imp fortress:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_FEMALES = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Imp Fortress",
			"A crude, walled fortress, constructed upon a raised mound of rock, sits in the middle of a huge underground cave.",
			"submission/impFortress3",
			PresetColour.BASE_PINK,
			SubmissionGenericPlaces.IMP_FORTRESS_FEMALES,
			Darkness.ALWAYS_LIGHT,
			null, "in Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
				return getSVGOverride("submission/impFortress3", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress3", PresetColour.BASE_PINK);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_FEMALES = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Imp Tunnels",
			"These tunnels are particularly dangerous, as they're home to hostile groups of wandering imps.",
			"submission/impTunnels3Icon",
			PresetColour.BASE_PINK_LIGHT,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "in Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
				return getSVGOverride("submission/impTunnels3Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels3Icon", PresetColour.BASE_PINK_LIGHT);
		}
	}.initDangerous()
	.initWeatherImmune()
	.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType FORTRESS_FEMALES_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Gateway",
			"The front gates of the fortress offer access back out into Submission.",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpFortressDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Female Imp Fortress"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_FEMALES_COURTYARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Courtyard",
			"Separating the gateway from the fortress's wooden keep, there's nothing but a deserted, squalid courtyard.",
			null,
			PresetColour.BASE_BLACK,
			ImpFortressDialogue.COURTYARD,
			Darkness.ALWAYS_LIGHT,
			null, "in the Female Imp Fortress"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_FEMALES_KEEP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Keep",
			"A crudely-constructed keep serves as the residence for this particular fortress's ruler.",
			"submission/impFortress/keep",
			PresetColour.BASE_PINK,
			ImpFortressDialogue.KEEP,
			Darkness.ALWAYS_LIGHT,
			null, "in the Female Imp Fortress"
			).initDangerous()
			.initWeatherImmune();

	
	
	// Incubus imp fortress:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_MALES = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Imp Fortress",
			"A walled fortress, constructed upon a raised mound of rock, sits in the middle of a huge underground cave.",
			"submission/impFortress4",
			PresetColour.BASE_BLUE,
			SubmissionGenericPlaces.IMP_FORTRESS_MALES,
			Darkness.ALWAYS_LIGHT,
			null, "in Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
				return getSVGOverride("submission/impFortress4", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress4", PresetColour.BASE_BLUE);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_MALES = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Imp Tunnels",
			"These tunnels are particularly dangerous, as they're home to hostile groups of wandering imps.",
			"submission/impTunnels4Icon",
			PresetColour.BASE_BLUE_LIGHT,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "in Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
				return getSVGOverride("submission/impTunnels4Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels4Icon", PresetColour.BASE_BLUE_LIGHT);
		}
	}.initDangerous()
	.initWeatherImmune()
	.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType FORTRESS_MALES_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Gateway",
			"The front gates of the fortress offer access back out into Submission.",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpFortressDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Male Imp Fortress"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_MALES_COURTYARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Courtyard",
			"Separating the gateway from the fortress's wooden keep, there's a deserted courtyard, which is home to numerous archery targets and straw dummies.",
			null,
			PresetColour.BASE_BLACK,
			ImpFortressDialogue.COURTYARD,
			Darkness.ALWAYS_LIGHT,
			null, "in the Male Imp Fortress"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_MALES_KEEP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Keep",
			"This imp fortress's keep takes the form of a very distinctly-Japanese styled single-story building.",
			"submission/impFortress/keep",
			PresetColour.BASE_BLUE,
			ImpFortressDialogue.KEEP,
			Darkness.ALWAYS_LIGHT,
			null, "in the Male Imp Fortress"
			).initDangerous()
			.initWeatherImmune();
	
	
	
	
	// Lyssieth's palace:
	
	public static final AbstractPlaceType LYSSIETH_PALACE_CORRIDOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Corridor",
			"The vast corridors of Lyssieth's palace as just as ostentatiously luxurious as you'd expect from the residence of a direct daughter of Lilith herself.",
			null,
			PresetColour.BASE_GREY,
			LyssiethPalaceDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in Lyssieth's Palace") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.MAID, PopulationDensity.COUPLE,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.THREE),
							new Value<>(Subspecies.HALF_DEMON, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_WINDOWS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Windows",
			"The corridors which branch off to the left and right of the main entrance hall are lined with a series of narrow windows, which overlook the cavern beyond.",
			null,
			PresetColour.BASE_GREY_DARK,
			LyssiethPalaceDialogue.WINDOWS,
			Darkness.ALWAYS_LIGHT,
			null, "in Lyssieth's Palace") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
	.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Entrance",
			"The entrance hall to Lyssieth's palace is staggeringly opulent, and is in complete contrast to the dark, drab exterior.",
			"submission/lyssiethsPalace/entrance",
			PresetColour.BASE_RED,
			LyssiethPalaceDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in Lyssieth's Palace") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_ROOM = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Room",
			"Plush sofas, extravagantly-carved coffee tables, and even the odd piano can be found in these drawing rooms.",
			"submission/lyssiethsPalace/lounge",
			PresetColour.BASE_PINK,
			LyssiethPalaceDialogue.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in Lyssieth's Palace"
			) {
		@Override
		public DialogueNode getBaseDialogue(Cell cell) {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Elizabeth.class))) {
				return DialogueManager.getDialogueFromId("acexp_submission_palace_elizabeth");
			}
			return LyssiethPalaceDialogue.ROOM;
		}
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_HALL = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Hall",
			"In each wing of the palace, there is a long, extravagantly-furnished dining hall, which Lyssieth uses to entertain her particularly-important guests.",
			"submission/lyssiethsPalace/hall",
			PresetColour.BASE_ORANGE_LIGHT,
			LyssiethPalaceDialogue.HALL,
			Darkness.ALWAYS_LIGHT,
			null, "in Lyssieth's Palace"
			) {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_OFFICE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Lyssieth's Office",
			"Lyssieth can almost always be found role-playing as an important executive in her luxuriously-furnished office.",
			"submission/lyssiethsPalace/office",
			PresetColour.BASE_GOLD,
			LyssiethPalaceDialogue.LYSSIETH_OFFICE_ENTER,
			Darkness.ALWAYS_LIGHT,
			null, "in Lyssieth's Palace"
			).initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_SIREN_OFFICE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Meraxis's Office",
			"The room through which one must pass to see Lyssieth has been converted into an office-cum-waiting room, and is staffed by none other than her daughter, Meraxis.",
			"submission/lyssiethsPalace/officeSiren",
			PresetColour.BASE_CRIMSON,
			LyssiethPalaceDialogue.SIREN_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "in Lyssieth's Palace") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getNpc(DarkSiren.class).getHomeWorldLocation()!=WorldType.LYSSIETH_PALACE) {
				return Util.newArrayListOfValues(new Population(false, PopulationType.RECEPTIONIST, PopulationDensity.ONE,
						Util.newHashMapOfValues(
								new Value<>(Subspecies.HALF_DEMON, SubspeciesSpawnRarity.TEN))));
			}
			return super.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_STAIRS_1 = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Staircase",
			"These staircases lead up to the rooms of the first floor, in which Lyssieth and her staff have their private bedchambers.",
			"submission/lyssiethsPalace/staircase",
			PresetColour.BASE_GREEN,
			LyssiethPalaceDialogue.STAIRCASE,
			Darkness.ALWAYS_LIGHT,
			null, "in Lyssieth's Palace") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_STAIRS_2 = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Staircase",
			"These staircases lead up to the rooms of the first floor, in which Lyssieth and her staff have their private bedchambers.",
			"submission/lyssiethsPalace/staircase",
			PresetColour.BASE_GREEN,
			LyssiethPalaceDialogue.STAIRCASE,
			Darkness.ALWAYS_LIGHT,
			null, "in Lyssieth's Palace") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	
	
	// Bat caverns:

	public static final AbstractPlaceType BAT_CAVERN_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Winding Staircase",
			"This long, winding staircase has been carved out of solid rock, and leads back up to the main walkways of Submission.",
			"submission/batCaverns/cavernStaircase",
			PresetColour.BASE_GREEN,
			BatCaverns.STAIRCASE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Bat Caverns"
			).initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_DARK = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Dark Cavern",
			"The oppressive, inky blackness of the bat caverns is kept at bay by a softly glowing moss which covers the entire floor.",
			null,
			PresetColour.BASE_GREY,
			BatCaverns.CAVERN_DARK,
			Darkness.ALWAYS_DARK,
			Encounter.BAT_CAVERN,
			"in the Bat Caverns"
			).initDangerous()
			.initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_LIGHT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Bioluminescent Cavern",
			"The moss carpet in this particular part of the cavern thrives in amongst bioluminescent fungi of all shapes, sizes, and colours, and forms trailing paths which wind through the area.",
			"submission/batCaverns/cavernBioluminescent",
			PresetColour.BASE_AQUA,
			BatCaverns.CAVERN_LIGHT,
			Darkness.ALWAYS_LIGHT, Encounter.BAT_CAVERN, "in the Bat Caverns"
			) {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Elle.class))) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(
						new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN),
						new Value<>(Subspecies.ALLIGATOR_MORPH, SubspeciesSpawnRarity.TEN),
						new Value<>(Subspecies.DOG_MORPH, SubspeciesSpawnRarity.TEN))));
			}
			return super.getPopulation();
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_RIVER = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Underground River",
			"A slow-moving underground river carves its way through the bat caverns; it's cool, dark depths prove to be impenetrable to what little light there is given off by the bioluminescent lichen.",
			"submission/batCaverns/cavernRiver",
			PresetColour.BASE_BLUE,
			BatCaverns.RIVER,
			Darkness.ALWAYS_DARK, Encounter.BAT_CAVERN, "in the Bat Caverns"
			).initDangerous()
			.initWeatherImmune()
			.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType BAT_CAVERN_RIVER_CROSSING = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Mushroom Bridge",
			"A pair of tree-sized bioluminescent mushrooms have been shaped into a horizontal, weaving pattern in order to form a living bridge across the river's dark depths.",
			"submission/batCaverns/cavernBridge",
			PresetColour.BASE_TEAL,
			BatCaverns.RIVER_BRIDGE,
			Darkness.ALWAYS_DARK, Encounter.BAT_CAVERN, "in the Bat Caverns"
			).initDangerous()
			.initWeatherImmune()
			.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType BAT_CAVERN_RIVER_END = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Underground River End",
			"Where the water-side path ends, the river drops away into a pitch-black abyss. A finely meshed metal grate has been built to save anyone from being swept down into the bottomless depths below.",
			"submission/batCaverns/cavernRiverEnd",
			PresetColour.BASE_BLUE_DARK,
			BatCaverns.RIVER_END,
			Darkness.ALWAYS_DARK, Encounter.BAT_CAVERN, "in the Bat Caverns"
			).initDangerous()
			.initWeatherImmune()
			.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType BAT_CAVERN_SLIME_QUEEN_LAIR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Slime Lake",
			"A gigantic underground lake is illuminated by the bioluminescent fungi forests which line its banks. Out in the middle of the still, black waters, there sits a small, mushroom-covered island.",
			"submission/batCaverns/cavernLake",
			PresetColour.BASE_PINK_LIGHT,
			BatCaverns.SLIME_LAKE,
			Darkness.ALWAYS_LIGHT,
			Encounter.BAT_CAVERN,
			"beside Slime Lake"
			).initDangerous()
			.initWeatherImmune()
			.initAquatic(Aquatic.MIXED);

	public static final AbstractPlaceType BAT_CAVERN_SHAFT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Shaft to Dominion",
			"A large, winding shaft set into the ceiling provides a direct link between the Bat Caverns and Dominion.",
			"submission/batCaverns/cavernShaft",
			PresetColour.BASE_GREEN,
			BatCaverns.SHAFT,
			Darkness.DAYLIGHT,
			null,
			"in the Bat Caverns"
			).initWeatherImmune();
	
	// HLF Quest places:
	
	public static final AbstractPlaceType BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Hidden Cave Entrance",
			"The entrance to a mysterious artificial cave, formerly concealed behind a tight-fitting stone door.",
			"submission/rebelBase/entrance",
			PresetColour.BASE_RED,
			BatCaverns.REBEL_BASE_ENTRANCE_EXTERIOR,
			Darkness.ALWAYS_DARK,
			Encounter.BAT_CAVERN,
			"beside the entrance to a mysterious artificial cave"
			).initDangerous()
			.initWeatherImmune();
        
       public static final AbstractPlaceType BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Strange Handle",
			"A strange handle juts out from the rock.",
			"submission/rebelBase/entrance",
			PresetColour.BASE_GREY,
			BatCaverns.REBEL_BASE_ENTRANCE_HANDLE,
			Darkness.ALWAYS_DARK,
			Encounter.BAT_CAVERN,
			"in the Bat Caverns"
			).initDangerous()
			.initWeatherImmune();
	
	// Slime queen's island tower:

	public static final AbstractPlaceType SLIME_QUEENS_LAIR_CORRIDOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Corridor",
			"A thick burgundy-and-gold rug runs down the middle of the corridor, while the stone walls to either side are covered in thick fabric tapestries.",
			null,
			PresetColour.BASE_GREY,
			SlimeQueensLair.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Slime Queen's tower"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Entrance Hall",
			"A heavy, iron-barred, oaken door forms the means by which one may come and go from this tower.",
			"submission/slimeQueensLair/entranceHall",
			PresetColour.BASE_RED,
			SlimeQueensLair.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Slime Queen's tower"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_STAIRS_UP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Spiral Staircase",
			"A narrow spiral staircase leads up to the first floor of the tower.",
			"submission/slimeQueensLair/staircase",
			PresetColour.BASE_GREEN,
			SlimeQueensLair.STAIRCASE_UP,
			Darkness.ALWAYS_LIGHT,
			null, "in the Slime Queen's tower"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_STAIRS_DOWN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Spiral Staircase",
			"A narrow spiral staircase leads down to the ground floor of the tower.",
			"submission/slimeQueensLair/staircase",
			PresetColour.BASE_RED,
			SlimeQueensLair.STAIRCASE_DOWN,
			Darkness.ALWAYS_LIGHT,
			null, "in the Slime Queen's tower"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ROOM = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Bedroom",
			"The place in which one or more of the tower's guards rest, this bedroom houses a neatly made four-poster bed, as well as the usual bedroom furnishings.",
			"submission/slimeQueensLair/room",
			PresetColour.BASE_BLUE_LIGHT,
			SlimeQueensLair.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in the Slime Queen's tower"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_STORAGE_VATS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Distillery",
			"The huge distillation device located in this room is the source of not only all of the transformative slime fluids in Submission, but also that of the popular drink, 'Slime Quencher'.",
			"submission/slimeQueensLair/storageVats",
			PresetColour.BASE_ORANGE,
			SlimeQueensLair.STORAGE_VATS,
			Darkness.ALWAYS_LIGHT,
			null, "in the Slime Queen's tower") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			for(int i=0; i<15; i++) {
				inventory.addItem(Main.game.getItemGen().generateItem("innoxia_race_slime_slime_quencher"));
			}
			for(int i=0; i<5; i++) {
				inventory.addItem(Main.game.getItemGen().generateItem("innoxia_race_slime_biojuice_canister"));
			}
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ENTRANCE_GUARDS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Guard Post",
			"This area is home to a chest-height wooden barricade, which has been constructed from wall-to-wall.",
			"submission/slimeQueensLair/guards",
			PresetColour.BASE_RED,
			SlimeQueensLair.GUARD_POST,
			Darkness.ALWAYS_LIGHT,
			null, "in the Slime Queen's tower"
			) {
		@Override
		public boolean isDangerous() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsDefeated) && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ROYAL_GUARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Royal Guard Post",
			"A powerful, purple-hued incubus-slime guards this particular stretch of the corridor.",
			"submission/slimeQueensLair/royalGuards",
			PresetColour.BASE_PURPLE,
			SlimeQueensLair.ROYAL_GUARD_POST,
			Darkness.ALWAYS_LIGHT,
			null, "in the Slime Queen's tower"
			) {
		@Override
		public boolean isDangerous() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeated) && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_SLIME_QUEEN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Bed Chamber",
			"The slime queen's bed chamber houses a colossal four-poster bed, as well as a huge bath, which is filled with a considerable amount of translucent pink liquid.",
			"submission/slimeQueensLair/bedChamber",
			PresetColour.BASE_PINK,
			SlimeQueensLair.BED_CHAMBER,
			Darkness.ALWAYS_LIGHT,
			null, "in the Slime Queen's tower"
			).initWeatherImmune();
	
	
	
	
	// Gambling Den:
	
	public static final AbstractPlaceType GAMBLING_DEN_CORRIDOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Gambling Den",
			"The walls of the Gambling Den's spacious atrium are lined with countless slot machines, while numerous tables and chairs have been set out down the centre.",
			null,
			PresetColour.BASE_BLACK,
			GamblingDenDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Gambling Den") {
		@Override
		public List<Population> getPopulation() {
			Map<AbstractSubspecies, SubspeciesSpawnRarity> popComponent = new HashMap<>(Subspecies.getWorldSpecies(WorldType.SUBMISSION, this, false));
			Subspecies.getWorldSpecies(WorldType.DOMINION, this, false).forEach((key, value) -> popComponent.merge(key, value, (v1, v2) -> v1));
			popComponent.remove(Subspecies.IMP);
			popComponent.remove(Subspecies.IMP_ALPHA);
			return Util.newArrayListOfValues(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, popComponent));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType GAMBLING_DEN_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Entrance",
			"The wooden doors of the Gambling Den's front entrance are always left propped open, offering easy access for the establishment's many patrons.",
			"submission/gamblingDen/entrance",
			PresetColour.BASE_GREEN,
			GamblingDenDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Gambling Den") {
		@Override
		public List<Population> getPopulation() {
			return GAMBLING_DEN_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType GAMBLING_DEN_OFFICE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Axel's Office",
			"Axel's office is situated next to the main entrance, and is locked when not in use.",
			"submission/gamblingDen/office",
			PresetColour.BASE_ORANGE,
			GamblingDenDialogue.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Gambling Den") {
	}.initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_TRADER = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Roxy's Box",
			"'Roxy's Box' is a rather over-priced pawn shop, and offers goods that can be found at much reduced prices up in Dominion.",
			"submission/gamblingDen/trader",
			PresetColour.BASE_TEAL,
			RoxysShop.TRADER_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Gambling Den"
			).initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_GAMBLING = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Dice Poker Tables",
			"Dice poker is one of the Gambling Den's prime attractions, and the many tables set aside for this game are almost always fully occupied.",
			"submission/gamblingDen/gambling",
			PresetColour.BASE_GOLD,
			GamblingDenDialogue.GAMBLING,
			Darkness.ALWAYS_LIGHT,
			null, "in the Gambling Den") {
		@Override
		public List<Population> getPopulation() {
			return GAMBLING_DEN_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_PREGNANCY_ROULETTE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Pregnancy Roulette",
			"The game 'pregnancy roulette' is run by the horse-girl, Epona, from behind a long wooden counter that's been set into the wall.",
			"submission/gamblingDen/referee",
			PresetColour.BASE_PINK,
			PregnancyRoulette.PREGNANCY_ROULETTE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Gambling Den") {
		@Override
		public List<Population> getPopulation() {
			return GAMBLING_DEN_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_PREGNANCY = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Breeding Stalls",
			"It's here where the willing, male players of 'pregnancy roulette' get on with their game.",
			"submission/gamblingDen/normalPregnancy",
			PresetColour.BASE_BLUE_LIGHT,
			GamblingDenDialogue.PREGNANCY_ROULETTE_MALE_STALLS,
			Darkness.ALWAYS_LIGHT,
			null, "in the Gambling Den"
			).initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_FUTA_PREGNANCY = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Futa Breeding Stalls",
			"It's here where the willing, futanari players of 'pregnancy roulette' get on with their game.",
			"submission/gamblingDen/futaPregnancy",
			PresetColour.BASE_PINK_LIGHT,
			GamblingDenDialogue.PREGNANCY_ROULETTE_FUTA_STALLS,
			Darkness.ALWAYS_LIGHT,
			null, "in the Gambling Den"
			).initWeatherImmune();
	
	
	
	
	// Rat warrens:

	public static final AbstractPlaceType RAT_WARRENS_CORRIDOR_LEFT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Twisting Passageways",
			"The twisting passageways of the Rat Warrens vary greatly in both width and quality of construction.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Rat Warrens") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_CHECKPOINT_LEFT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Twisting Passageways",
			"The twisting passageways of the Rat Warrens vary greatly in both width and quality of construction.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Rat Warrens") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)
					&& (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Twisting Passageways",
			"The twisting passageways of the Rat Warrens vary greatly in both width and quality of construction.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Rat Warrens") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_CORRIDOR_RIGHT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Twisting Passageways",
			"The twisting passageways of the Rat Warrens vary greatly in both width and quality of construction.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Rat Warrens") {
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isCaptive()) {
				return VengarCaptiveDialogue.CORRIDOR;
			}
			return super.getDialogue(c, withRandomEncounter, forceEncounter);
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_CHECKPOINT_RIGHT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Twisting Passageways",
			"The twisting passageways of the Rat Warrens vary greatly in both width and quality of construction.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "in the Rat Warrens") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)
					&& (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Entrance",
			"The entrance to the Rat Warrens is always guarded by at least two gang members.",
			"submission/ratWarrens/entrance",
			PresetColour.BASE_GREEN,
			RatWarrensDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Rat Warrens") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre);
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_DORMITORY_LEFT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Dormitory",
			"Bunkbeds line the walls of this damp and dingy room, while a few tables and chairs are scattered around the middle.",
			"submission/ratWarrens/dormitory",
			PresetColour.BASE_BROWN,
			RatWarrensDialogue.DORMITORY,
			Darkness.ALWAYS_LIGHT,
			null, "in the Rat Warrens") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				if(Main.game.isExtendedWorkTime()) {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				} else {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
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
			WorldRegion.SUBMISSION,
			"Dormitory",
			"Bunkbeds line the walls of this damp and dingy room, while a few tables and chairs are scattered around the middle.",
			"submission/ratWarrens/dormitory",
			PresetColour.BASE_BROWN,
			RatWarrensDialogue.DORMITORY,
			Darkness.ALWAYS_LIGHT,
			null, "in the Rat Warrens") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
				if(Main.game.isExtendedWorkTime()) {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				} else {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
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
			WorldRegion.SUBMISSION,
			"Dice Den",
			"A place where gang members go to drink and gamble.",
			"submission/ratWarrens/diceDen",
			PresetColour.BASE_COPPER,
			RatWarrensDialogue.DICE_DEN,
			Darkness.ALWAYS_LIGHT,
			null, "in the Rat Warrens") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				if(Main.game.isExtendedWorkTime()) {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				} else {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
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
			WorldRegion.SUBMISSION,
			"Milking Room",
			"This is the final destination for humans unfortunate enough to have been kidnapped by Vengar's gang.",
			"submission/ratWarrens/stocks",
			PresetColour.BASE_MAGENTA,
			RatWarrensDialogue.MILKING_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "in the Rat Warrens") {
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isCaptive()) {
				dialogue = RatWarrensCaptiveDialogue.CAPTIVE_NIGHT;
			} else {
				dialogue = RatWarrensDialogue.MILKING_ROOM;
			}
			return super.getDialogue(c, withRandomEncounter, forceEncounter);
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_MILKING_STORAGE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Milk Storage",
			"A huge number of metal milk pails are being stored in this area; each one is marked with a bodily fluid type of one sort or another, along with what flavour it is.",
			"submission/ratWarrens/milkingRoom",
			PresetColour.BASE_YELLOW_LIGHT,
			RatWarrensDialogue.MILKING_STORAGE,
			Darkness.ALWAYS_LIGHT,
			null, "in the Rat Warrens") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_VENGARS_HALL = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Vengar's Hall",
			"A huge, stone hall, filled with numerous long wooden benches and with a raised throne at the far end.",
			"submission/ratWarrens/vengarsHall",
			PresetColour.BASE_PURPLE,
			RatWarrensDialogue.VENGARS_HALL,
			Darkness.ALWAYS_LIGHT,
			null, "in the Rat Warrens") {
		@Override
		public AbstractEncounter getEncounterType() {
			if(Main.game.getPlayer().isCaptive()) {
				return Encounter.VENGAR_CAPTIVE_HALL;
			}
			return null;
		}
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isCaptive()) {
				dialogue = VengarCaptiveDialogue.VENGARS_HALL;
			} else {
				dialogue = RatWarrensDialogue.VENGARS_HALL;
			}
			return super.getDialogue(c, withRandomEncounter, forceEncounter);
		}
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
			}
			return new ArrayList<>();
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_PRIVATE_BEDCHAMBERS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Private Bed-chambers",
			"The private bed-chambers used by Vengar and his bodyguards are located adjacent to the main hall.",
			"submission/ratWarrens/bedroom",
			PresetColour.BASE_PURPLE_LIGHT,
			RatWarrensDialogue.VENGARS_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"in the Rat Warrens") {
		@Override
		public AbstractEncounter getEncounterType() {
			if(Main.game.getPlayer().isCaptive()) {
				return Encounter.VENGAR_CAPTIVE_BEDROOM;
			}
			return null;
		}
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isCaptive()) {
				dialogue = VengarCaptiveDialogue.VENGARS_BEDROOM;
			} else {
				dialogue = RatWarrensDialogue.VENGARS_BEDROOM;
			}
			return super.getDialogue(c, withRandomEncounter, forceEncounter);
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile);
		}
	}.initWeatherImmune();

	// HLF Quest places:
	
    public static final AbstractPlaceType REBEL_BASE_ENTRANCE = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Entrance",
			"The only way in or out of the cave is cleverly concealed behind a tight-fitting stone door.",
			"submission/rebelBase/entrance",
			PresetColour.BASE_RED,
			RebelBase.REBEL_BASE_ENTRANCE,
			Darkness.ALWAYS_DARK,
			null,
			"in a mysterious artificial cave")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_CORRIDOR = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Corridor",
			"An artificial cave lined with questionable wooden supports.",
			null,
			PresetColour.BASE_BLACK,
			RebelBase.REBEL_BASE_CORRIDOR,
			Darkness.ALWAYS_DARK,
			null,
			"in a mysterious artificial cave")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_SLEEPING_AREA = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Abandoned Sleeping Area",
			"A long abandoned room full of long abandoned beds.",
			"submission/rebelBase/cache1",
			PresetColour.BASE_BLUE,
			RebelBase.REBEL_BASE_SLEEPING_AREA,
			Darkness.ALWAYS_DARK,
			Encounter.REBEL_BASE,
			"in a mysterious artificial cave")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_SLEEPING_AREA_SEARCHED = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Abandoned Sleeping Area",
			"A long abandoned room full of long abandoned beds.",
			"submission/rebelBase/cache1",
			PresetColour.BASE_GREY,
			RebelBase.REBEL_BASE_SLEEPING_AREA_SEARCHED,
			Darkness.ALWAYS_DARK,
			null,
			"in a mysterious artificial cave")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_COMMON_AREA = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Abandoned Common Area",
			"The sparsely furnished ruins of a common area.",
			"submission/rebelBase/cache2",
			PresetColour.BASE_ORANGE,
			RebelBase.REBEL_BASE_COMMON_AREA,
			Darkness.ALWAYS_DARK,
			null,
			"in a mysterious artificial cave")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_COMMON_AREA_SEARCHED = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Abandoned Common Area",
			"The sparsely furnished ruins of a common area.",
			"submission/rebelBase/cache2",
			PresetColour.BASE_GREY,
			RebelBase.REBEL_BASE_COMMON_AREA_SEARCHED,
			Darkness.ALWAYS_DARK,
			null,
			"in a mysterious artificial cave")
	            .initWeatherImmune();

    public static final AbstractPlaceType REBEL_BASE_ARMORY = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"A Partly Caved-in Room",
			"A room partly filled with rubble.",
			"submission/rebelBase/cache3",
			PresetColour.BASE_GREEN,
			RebelBase.REBEL_BASE_ARMORY,
			Darkness.ALWAYS_DARK,
			null,
			"in a mysterious artificial cave")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_ARMORY_SEARCHED = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"A Partly Caved-in Room",
			"A room partly filled with rubble.",
			"submission/rebelBase/cache3",
			PresetColour.BASE_GREY,
			RebelBase.REBEL_BASE_ARMORY_SEARCHED,
			Darkness.ALWAYS_DARK,
			null,
			"in a mysterious artificial cave")
	            .initWeatherImmune();
    
     public static final AbstractPlaceType REBEL_BASE_CAVED_IN_ROOM = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"A Mostly Caved-in Room",
			"A room with nothing but rubble in it.",
			"submission/rebelBase/cavein",
			PresetColour.BASE_GREY_DARK,
			RebelBase.REBEL_BASE_CAVED_IN_ROOM,
			Darkness.ALWAYS_DARK,
			null,
			"in a mysterious artificial cave")
	            .initWeatherImmune();
	
	
	
	// World map tiles:

	public static final AbstractGlobalPlaceType WORLD_MAP_THICK_JUNGLE = new AbstractGlobalPlaceType(
			WorldRegion.JUNGLE,
			"thick jungle",
			null,
			"The further into the jungle one travels, the thicker the vegetation becomes, which allows particularly wild and dangerous predators to conceal themselves...",
			new Colour(Util.newColour(0x6b8f7e)), null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_JUNGLE = new AbstractGlobalPlaceType(
			WorldRegion.JUNGLE,
			"jungle",
			null,
			"Sparse, tropical foliage is home to many different jungle animal-morphs, not all of which are friendly.",
			new Colour(Util.newColour(0x8fbfa8)), null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_JUNGLE_CITY = new AbstractGlobalPlaceType(
			WorldRegion.JUNGLE_CITY,
			"Itza'aak",
			null,
			"A sprawling, Mayan-like city, Itza'aak is the last bastion of civilisation before the sprawling, wild jungles of the north.",
			new Colour(Util.newColour(0xb377b0)), null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	};

	public static final AbstractGlobalPlaceType WORLD_MAP_FOOTHILLS = new AbstractGlobalPlaceType(
			WorldRegion.MOUNTAINS,
			"foothills",
			null,
			"A steady increase in elevation leads to the rolling hills at the base of the mountains of the moon.",
			PresetColour.BASE_BLACK, null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_MOUNTAINS = new AbstractGlobalPlaceType(
			WorldRegion.MOUNTAINS,
			"mountains",
			null,
			"The mountain range to the far west is known as the 'Mountains of the Moon', and is home to many alpine animal-morphs.",
			PresetColour.BASE_GREY_DARK, null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_SNOWY_MOUNTAINS = new AbstractGlobalPlaceType(
			WorldRegion.MOUNTAINS,
			"mountain peaks",
			null,
			"The highest peaks of the Mountains of the Moon are capped in snow, and are home to several wild and aggressive races...",
			PresetColour.BASE_GREY_LIGHT, null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_SNOWY_VALLEY = new AbstractGlobalPlaceType(
			WorldRegion.SNOW,
			"snowstorm valley",
			null,
			"This sheltered valley sees regular, heavy snowfall, and is home to numerous arctic races.",
			new Colour(Util.newColour(0xeeeeee)), null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_GLACIAL_LAKE = new AbstractGlobalPlaceType(
			WorldRegion.SNOW,
			"selkie lake",
			null,
			"On the western side of snowstorm valley, there can be found a huge, partially-frozen lake.",
			new Colour(Util.newColour(0xbbf0f1)), null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous()
	.initAquatic(Aquatic.MIXED);

	public static final AbstractGlobalPlaceType WORLD_MAP_DOMINION = new AbstractGlobalPlaceType(
			WorldRegion.DOMINION,
			"Dominion Suburbs",
			"The capital city of Lilith's realm, Dominion is the succubus queen's seat of power.",
			"global/dominion",
			PresetColour.BASE_PURPLE,
			new Colour(Util.newColour(0x826B85)),
			FieldsDialogue.DOMINION_EXTERIOR,
			null, "in the outskirts of Dominion") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.leftDominionFirstTime)) {
				return FieldsDialogue.DOMINION_EXTERIOR;
			} else {
				return DialogueManager.getDialogueFromId("innoxia_places_fields_leaving_dominion_start");
			}
		}
		@Override
		public AbstractWorldType getGlobalLinkedWorldType() {
			return WorldType.DOMINION;
		}
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getDominionStormImmuneSpecies(true)));
				pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true, Subspecies.HUMAN)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
				pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				pop.add(new Population(false, PopulationType.CENTAUR_CARTS, PopulationDensity.OCCASIONAL, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
			}
			
			return pop;
		}
	}.initAquatic(Aquatic.MIXED);

	public static final AbstractGlobalPlaceType WORLD_MAP_GRASSLANDS = new AbstractGlobalPlaceType(
			WorldRegion.FIELDS,
			"grassland wilderness",//"global/grassland",
			null,
			"The grassland wilderness is home to many different races, the vast majority of which are just as wild and untamed as the land they inhabit.",
			new Colour(Util.newColour(0x688255)),
			FieldsDialogue.GRASSLAND_WILDERNESS,
			null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_FIELDS = new AbstractGlobalPlaceType(
			WorldRegion.FIELDS,
			"Foloi Fields",
			null,
			"The farmland surrounding Dominion is known as the 'Foloi Fields', and is primarily inhabited by farmyard animal-morphs.",
			new Colour(Util.newColour(0xB9E3A1)),
			FieldsDialogue.FOLOI_FIELDS,
			null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_FOREST = new AbstractGlobalPlaceType(
			WorldRegion.WOODLAND,
			"Foloi forests",
			"The thick forests surrounding the Foloi Fields are particularly dangerous, as they are home to the wild, predatory morphs of wolves, foxes, and bears.",
			"global/forest",
			new Colour(Util.newColour(0x51A468)),
			new Colour(Util.newColour(0x5E685E)),
			FieldsDialogue.FOLOI_FOREST,
			null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_FIELDS_CITY = new AbstractGlobalPlaceType(
			WorldRegion.FIELD_CITY,
			"Elis",
			"The largest and most prosperous of all settlements in the Foloi Fields, Elis acts as a trading hub for both the youko and the races inhabiting the mountains.",
			"global/elis",
			new Colour(Util.newColour(0xd544ae)),
			new Colour(Util.newColour(0x859871)),
			FieldsDialogue.ELIS,
			null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	};
	
	public static final AbstractGlobalPlaceType WORLD_MAP_RIVER = new AbstractGlobalPlaceType(
			WorldRegion.RIVER,
			"river Hubur",
			"The river Hubur runs from the west, through Dominion, and flows out into the endless sea. Those parts of it which border the Foloi Fields are considered safe.",
			"global/river",
			new Colour(Util.newColour(0x61BDFF)),
			new Colour(Util.newColour(0x98B4CD)),
			FieldsDialogue.RIVER_HUBUR,
			null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initAquatic(Aquatic.MIXED)
	.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_WILD_RIVER = new AbstractGlobalPlaceType(
			WorldRegion.RIVER,
			"river Hubur (wild)",
			null,
			"Far from Dominion, the river Hubur is a dangerous place in which to swim, as it is home to many wild freshwater races.",
			new Colour(Util.newColour(0xc1f1ee)), null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous()
	.initAquatic(Aquatic.MIXED);

	public static final AbstractGlobalPlaceType WORLD_MAP_YOUKO_FOREST = new AbstractGlobalPlaceType(
			WorldRegion.YOUKO_FOREST,
			"shinrin highlands",
			null,
			"The Shinrin highlands are a range of low, forest-covered hills, which steadily increase in elevation the further west you go. The elusive youko live here.",
			new Colour(Util.newColour(0x6ccc74)), null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_SEA = new AbstractGlobalPlaceType(
			WorldRegion.SEA,
			"endless sea",
			null,
			"The aquatic races inhabiting Lilith's realm do not like to stray too far from shore, and so to them, the sea is considered to be endless.",
			PresetColour.BASE_BLUE_DARK, null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous()
	.initAquatic(Aquatic.WATER_SURFACE);
	
	public static final AbstractGlobalPlaceType WORLD_MAP_SEA_CITY = new AbstractGlobalPlaceType(
			WorldRegion.SEA_CITY,
			"Lyonesse",
			null,
			"The underwater city of Lyonesse is situated off the eastern coast, and, unsurprisingly, is particularly difficult for non-aquatic races to visit.",
			new Colour(Util.newColour(0x8264b0)), null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initAquatic(Aquatic.WATER_UNDER);

	public static final AbstractGlobalPlaceType WORLD_MAP_ARID_GRASSLAND = new AbstractGlobalPlaceType(
			WorldRegion.SAVANNAH,
			"arid grassland",
			null,
			"To the south, the wild grassland starts to dry out, and is the preferred home for morphs such as lions, leopard, and zebras.",
			PresetColour.BASE_YELLOW_LIGHT, null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_ARID_SAVANNAH = new AbstractGlobalPlaceType(
			WorldRegion.SAVANNAH,
			"savannah",
			null,
			"Sparse, open-canopy woodlands are scattered across this area, and are inhabited by the same races as those found in the arid grasslands.",
			PresetColour.BASE_TAN, null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_DESERT = new AbstractGlobalPlaceType(
			WorldRegion.DESERT,
			"desert",
			null,
			"To the south of the arid grassland, all vegetation dies out, creating a hot, barren wasteland.",
			new Colour(Util.newColour(0xffe7a7)), null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_SAND_DUNES = new AbstractGlobalPlaceType(
			WorldRegion.DESERT,
			"sand dunes",
			null,
			"At the southern edge of the desert, there lies a huge range of sand dunes, which are home to many dangerous races.",
			new Colour(Util.newColour(0xffdb7a)), null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_DESERT_CITY = new AbstractGlobalPlaceType(
			WorldRegion.DESERT_CITY,
			"Thinis",
			null,
			"A city resembling one of ancient Egypt, Thinis is the southern-most settlement in Lilith's realm, and is well known for its prestigious arcane university.",
			new Colour(Util.newColour(0xd5445e)), null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	};

	public static final AbstractGlobalPlaceType WORLD_MAP_VOLCANO = new AbstractGlobalPlaceType(
			WorldRegion.VOLCANO,
			"dragon's breath volcano",
			null,
			"A huge volcano, perpetually oozing red-hot lava. Despite its name, dragons are no more common here than they are elsewhere in Lilith's realm.",
			PresetColour.BASE_ORANGE, null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_LAVA_FLOWS = new AbstractGlobalPlaceType(
			WorldRegion.VOLCANO,
			"lava flows",
			null,
			"The lava which pours forth from the volcano slowly runs off in a southern direction.",
			PresetColour.BASE_BLACK, null, null, "") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
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
		id = id.replaceAll("ALEXA", "HELENA");
		id = id.replaceAll("SUPPLIER_DEPOT", "TEXTILE_WAREHOUSE");
		
		if(id.equals("ZARANIX_FF_BEDROOM")) {
			id = "ZARANIX_FF_OFFICE";
			
		} else if(id.equals("LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_SLAVE")
				|| id.equals("LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_MILKING")) {
			id = "LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR";
			
		} else if(id.equals("LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE")
				|| id.equals("LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_MILKING")) {
			id = "LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR";
			
		} else if(id.equals("LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_SLAVE")
				|| id.equals("LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_MILKING")) {
			id = "LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR";
			
		} else if(id.equals("LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_SLAVE")
				|| id.equals("LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_MILKING")) {
			id = "LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR";
			
		} else if(id.equals("DOMINION_EXIT_TO_JUNGLE")) {
			id = "DOMINION_EXIT_EAST";
		} else if(id.equals("DOMINION_EXIT_TO_DESERT")) {
			id = "DOMINION_EXIT_SOUTH";
		} else if(id.equals("DOMINION_EXIT_TO_FIELDS")) {
			id = "DOMINION_EXIT_NORTH";
		} else if(id.equals("DOMINION_EXIT_TO_SEA")) {
			id = "DOMINION_EXIT_WEST";
			
		} else if(id.equals("SHOPPING_ARCADE_SUPPLIER_DEPOT")) {
			id = "SHOPPING_ARCADE_RESTAURANT";
			
		} else if(id.equals("innoxia_fields_elis_town_tavern_seedy")) {
			id = "innoxia_fields_elis_town_tavern_alley";
		}
		
		id = Util.getClosestStringMatch(id, idToPlaceMap.keySet());
		return idToPlaceMap.get(id);
	}

	public static String getIdFromPlaceType(AbstractPlaceType placeType) {
		return placeToIdMap.get(placeType);
	}
	
	static {
		// Modded place types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/maps", "placeTypes", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey().replace("_placeTypes", "");
					AbstractPlaceType placeType = new AbstractPlaceType(innerEntry.getValue(), entry.getKey(), id, true) {};
					allPlaceTypes.add(placeType);
					placeToIdMap.put(placeType, id);
					idToPlaceMap.put(id, placeType);
//					System.out.println("modded PT: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading modded place type failed at 'PlaceType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res place types:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/maps", "placeTypes", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey().replace("_placeTypes", "");
					AbstractPlaceType placeType = new AbstractPlaceType(innerEntry.getValue(), entry.getKey(), id, false) {};
					allPlaceTypes.add(placeType);
					placeToIdMap.put(placeType, id);
					idToPlaceMap.put(id, placeType);
//					System.out.println("res PT: "+innerEntry.getKey()+" | "+id);
				} catch(Exception ex) {
					System.err.println("Loading place type failed at 'PlaceType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}

		// Hard-coded place types (all those up above):
		
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
