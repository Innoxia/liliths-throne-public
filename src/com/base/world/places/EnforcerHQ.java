package com.base.world.places;

import java.io.IOException;
import java.io.InputStream;

import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.encounters.Encounter;
import com.base.game.dialogue.places.dominion.EnforcerHQDialogue;
import com.base.main.Main;
import com.base.utils.BaseColour;
import com.base.utils.Bearing;
import com.base.utils.Util;
import com.base.world.EntranceType;
import com.base.world.WorldType;

/**
 * @since 0.1.78
 * @version 0.1.78
 * @author Innoxia
 */
public enum EnforcerHQ implements PlaceInterface {
	
	// Standard tiles:
	CORRIDOR("Corridor", null, BaseColour.BLACK, EnforcerHQDialogue.CORRIDOR, null, true, false),

	// Places:

	WAITING_AREA("Waiting area", "dominion/enforcerHQ/waitingRoom", BaseColour.BROWN, EnforcerHQDialogue.WAITING_AREA, null, true, false),
	
	RECEPTION_DESK("Reception desk", "dominion/enforcerHQ/receptionDesk", BaseColour.BLUE_LIGHT, EnforcerHQDialogue.RECEPTION_DESK, null, true, false),
	
	GUARDED_DOOR("Guarded door", "dominion/enforcerHQ/guardedDoor", BaseColour.CRIMSON, EnforcerHQDialogue.GUARDED_DOOR, null, true, false),
	
	BRAXS_OFFICE("Brax's Office", "dominion/enforcerHQ/braxsOffice", BaseColour.BLUE_STEEL, EnforcerHQDialogue.INTERIOR_BRAX, null, true, false) {
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter) {
			if(Main.game.getPlayer().getCharactersEncountered().contains(Main.game.getBrax())) {
				return EnforcerHQDialogue.INTERIOR_BRAX_REPEAT;
				
			} else {
				return EnforcerHQDialogue.INTERIOR_BRAX;
			}
		}
	},

	// Exits & entrances:
	ENTRANCE("Entranceway", "dominion/enforcerHQ/entranceHall", BaseColour.RED, EnforcerHQDialogue.ENTRANCE, null, true, false){
		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.DOMINION;
		}

		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return Dominion.CITY_ENFORCER_HQ;
		}	
	};

	
	private String name, SVGString;
	private BaseColour colour;
	private DialogueNodeOld dialogue;
	private Encounter encounterType;
	private boolean populated, dangerous;

	EnforcerHQ(String name,
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
		return true;
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
