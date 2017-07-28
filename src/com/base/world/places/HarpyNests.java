package com.base.world.places;

import java.io.IOException;
import java.io.InputStream;

import com.base.game.Weather;
import com.base.game.character.QuestLine;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.encounters.Encounter;
import com.base.game.dialogue.places.dominion.harpyNests.HarpyNestBimbo;
import com.base.game.dialogue.places.dominion.harpyNests.HarpyNestDominant;
import com.base.game.dialogue.places.dominion.harpyNests.HarpyNestNympho;
import com.base.game.dialogue.places.dominion.harpyNests.HarpyNestsDialogue;
import com.base.main.Main;
import com.base.utils.BaseColour;
import com.base.utils.Bearing;
import com.base.utils.Util;
import com.base.world.EntranceType;
import com.base.world.WorldType;

/**
 * @since 0.1.75
 * @version 0.1.8
 * @author Innoxia
 */
public enum HarpyNests implements PlaceInterface {
	
	// Standard tiles:
	WALKWAYS("Walkway", null, BaseColour.BLACK, HarpyNestsDialogue.WALKWAY, Encounter.HARPY_NEST_WALKWAYS, false, true) {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isSideQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
	},

	// Places:
	ENTRANCE_ENFORCER_POST("Enforcer post", "dominion/harpyNests/stairsDown", BaseColour.RED, HarpyNestsDialogue.ENTRANCE_ENFORCER_POST, null, true, false){

		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.DOMINION;
		}
		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return Dominion.CITY_HARPY_NESTS;
		}
	},
	
	ALEXAS_NEST("Alexa's nest", "dominion/harpyNests/nestAlexa", BaseColour.GOLD, HarpyNestsDialogue.ALEXAS_NEST, null, true, false),
	
	HARPY_NEST_RED("Harpy nest", "dominion/harpyNests/nestRed", BaseColour.CRIMSON, HarpyNestDominant.HARPY_NEST_DOMINANT, null, true, false),
	
	HARPY_NEST_PINK("Harpy nest", "dominion/harpyNests/nestPink", BaseColour.PINK_LIGHT, HarpyNestNympho.HARPY_NEST_NYMPHO, null, true, false),
	
	HARPY_NEST_YELLOW("Harpy nest", "dominion/harpyNests/nestYellow", BaseColour.YELLOW_LIGHT, HarpyNestBimbo.HARPY_NEST_BIMBO, null, true, false);

	
	private String name, SVGString;
	private BaseColour colour;
	private DialogueNodeOld dialogue;
	private Encounter encounterType;
	private boolean populated, dangerous;

	HarpyNests(String name,
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
				InputStream is = this.getClass().getResourceAsStream("/com/base/res/map/" + SVGPath + ".svg");
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
}
