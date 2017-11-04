package com.lilithsthrone.world.places;

import java.io.IOException;
import java.io.InputStream;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.places.dominion.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.CityPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.DemonHome;
import com.lilithsthrone.game.dialogue.places.dominion.EnforcerHQDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.LilithsTower;
import com.lilithsthrone.game.dialogue.places.dominion.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestsDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ShoppingArcadeDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Bearing;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.EntranceType;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.75
 * @version 0.1.75
 * @author Innoxia
 */
public enum Dominion implements PlaceInterface {
	
	// Safe places:
	CITY_STREET("Dominion Streets", null, null, CityPlaces.STREET, Encounter.DOMINION_STREET, true, false) {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	CITY_LILITHS_TOWER("Lilith's Tower", "lilithsTowerIcon", BaseColour.PURPLE, LilithsTower.OUTSIDE, Encounter.DOMINION_STREET, true, false) {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	CITY_ENFORCER_HQ("Enforcer HQ", "enforcerHQIcon", BaseColour.BLUE, EnforcerHQDialogue.EXTERIOR, Encounter.DOMINION_STREET, true, false) {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.ENFORCER_HQ;
		}

		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return EnforcerHQ.ENTRANCE;
		}
	},
	
	CITY_DEMON_HOME("Demon Home", "demonHomeIcon", BaseColour.PINK, DemonHome.DEMON_HOME_STREET, Encounter.DOMINION_STREET, true, false) {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	CITY_SHOPPING_ARCADE("Shopping Arcade", "shoppingArcadeIcon", BaseColour.GOLD, ShoppingArcadeDialogue.OUTSIDE, Encounter.DOMINION_STREET, true, false) {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.SHOPPING_ARCADE;
		}

		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return ShoppingArcade.ARCADE_ENTRANCE;
		}
	},
	
	CITY_HARPY_NESTS("Harpy Nests", "harpyNestIcon", BaseColour.MAGENTA, HarpyNestsDialogue.OUTSIDE, Encounter.DOMINION_STREET, true, false) {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.HARPY_NEST;
		}

		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return HarpyNests.ENTRANCE_ENFORCER_POST;
		}
	},
	
	CITY_NIGHTLIFE_DISTRICT("Nightlife District", "nightlifeIcon", BaseColour.PINK_LIGHT, NightlifeDistrict.OUTSIDE, Encounter.DOMINION_STREET, true, false) {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	CITY_HALL("City Hall", "townHallIcon",  BaseColour.LILAC, CityHall.OUTSIDE, Encounter.DOMINION_STREET, true, false) {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
	},
	
	CITY_AUNTS_HOME("Lilaya's Home", "homeIcon", BaseColour.BLUE_LIGHT, LilayaHomeGeneric.OUTSIDE, Encounter.DOMINION_STREET, true, false) {

		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}

		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.LILAYAS_HOUSE_GROUND_FLOOR;
		}

		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return LilayasHome.LILAYA_HOME_ENTRANCE_HALL;
		}
	},
	
	CITY_SLAVER_ALLEY("Slaver Alley", "slaverAlleyIcon",  BaseColour.CRIMSON, SlaverAlleyDialogue.OUTSIDE, null, true, false) {

		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		
		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.SLAVER_ALLEY;
		}

		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return SlaverAlley.ALLEY_ENTRANCE;
		}
	},

	// Dangerous tiles:
	
	CITY_BACK_ALLEYS("Dominion Alleyways", "alleysIcon",  BaseColour.BLACK, CityPlaces.BACK_ALLEYS, Encounter.DOMINION_ALLEY, false, true),

	CITY_DARK_ALLEYS("Dark Alleyways", "alleysDarkIcon",  BaseColour.PURPLE, CityPlaces.DARK_ALLEYS, Encounter.DOMINION_DARK_ALLEY, false, true),
	
	// Exits & entrances:
	
	CITY_EXIT_TO_SEWERS("Submission Entrance", "submissionExit",  BaseColour.BROWN, CityPlaces.CITY_EXIT_SEWERS, Encounter.DOMINION_STREET, true, false) {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.SEWERS;
		}

		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return Submission.SEWER_ENTRANCE;
		}
		
		@Override
		public Bearing getBearing() {
			return Bearing.RANDOM;
		}
	},
	
	CITY_EXIT_TO_JUNGLE("Jungle Entrance", "JungleExit",  BaseColour.GREEN_LIME, CityPlaces.CITY_EXIT_JUNGLE, Encounter.DOMINION_STREET, true, false) {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		
		@Override
		public Bearing getBearing() {
			return Bearing.NORTH;
		}
		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.JUNGLE;
		}

		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return Jungle.JUNGLE_ENTRANCE;
		}
	},
	
	CITY_EXIT_TO_FIELDS("Fields Entrance", "fieldsExit",  BaseColour.GREEN_LIGHT, CityPlaces.CITY_EXIT_FIELDS, Encounter.DOMINION_STREET, true, false) {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		
		@Override
		public Bearing getBearing() {
			return Bearing.WEST;
		}
	},
	
	CITY_EXIT_TO_SEA("Endless Sea Entrance", "endlessSeaExit",  BaseColour.TEAL, CityPlaces.CITY_EXIT_SEA, Encounter.DOMINION_STREET, true, false) {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		
		@Override
		public Bearing getBearing() {
			return Bearing.EAST;
		}
	},
	
	CITY_EXIT_TO_DESERT("Desert Entrance", "desertExit", BaseColour.YELLOW, CityPlaces.CITY_EXIT_DESERT, Encounter.DOMINION_STREET, true, false) {
		
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		
		@Override
		public Bearing getBearing() {
			return Bearing.SOUTH;
		}
	};

	
	private String name, SVGString;
	private BaseColour colour;
	private DialogueNodeOld dialogue;
	private Encounter encounterType;
	private boolean populated, dangerous;

	Dominion(String name,
			String SVGPath,
			BaseColour colour,
			DialogueNodeOld dialogue,
			Encounter encounterType,
			boolean populated,
			boolean dangerous) {
		
		this.name = name;
		this.colour = colour;
		this.dialogue = dialogue;
		this.encounterType = encounterType;
		this.populated = populated;
		this.dangerous = dangerous;

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
		return false;
	}

	public boolean isItemsDisappear() {
		return true;
	}

	public String getSVGString() {
		return SVGString;
	}
	
	
	// For determining where this place should be placed:
	
	public Bearing getBearing() {
		return null;
	}
	
	public WorldType getParentWorldType() {
		return null;
	}
	
	public PlaceInterface getParentPlaceInterface() {
		return null;
	}
	
	public EntranceType getParentAlignment() {
		return null;
	}
	
	
	// For porting to another world:
	
	public WorldType getLinkedWorldType() {
		return null;
	}
	
	public PlaceInterface getLinkedPlaceInterface() {
		return null;
	}

	public DialogueNodeOld getDialogue() {
		return dialogue;
	}

	public Encounter getEncounterType() {
		return encounterType;
	}
}
