package com.lilithsthrone.world.places;

import java.io.IOException;
import java.io.InputStream;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.places.SewerPlaces;
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
public enum Submission implements PlaceInterface {
	
	// Standard tiles:
	SEWER_WALKWAYS("Submission Walkways", null, BaseColour.BLACK, SewerPlaces.WALKWAYS, null, false, true),

	// Places:
	SEWER_RAT_TUNNELS("Rat Tunnels", null, BaseColour.BLACK, SewerPlaces.RAT_TUNNELS, null, true, false),
	
	SEWER_GAMBLING_DEN("Gambling Den", null, BaseColour.BLACK, SewerPlaces.GAMBLING_DEN, null, true, false),
	
	SEWER_IMP_PALACE("Imp Palace", null, BaseColour.BLACK, SewerPlaces.IMP_PALACE, null, true, false),

	// Exits & entrances:
	SEWER_ENTRANCE("Submission Entrance", null, BaseColour.BLACK, SewerPlaces.SEWER_ENTRANCE, null, true, false){
		@Override
		public WorldType getParentWorldType() {
			return WorldType.DOMINION;
		}
		@Override
		public PlaceInterface getParentPlaceInterface() {
			return Dominion.CITY_EXIT_TO_SEWERS;
		}
		@Override
		public EntranceType getParentAlignment() {
			return EntranceType.ALIGNED;
		}
	};

	
	private String name, SVGString;
	private BaseColour colour;
	private DialogueNodeOld dialogue;
	private Encounter encounterType;
	private boolean populated, dangerous;

	Submission(String name,
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
}
