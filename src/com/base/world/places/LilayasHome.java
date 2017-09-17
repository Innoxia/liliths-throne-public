package com.base.world.places;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.encounters.Encounter;
import com.base.game.dialogue.places.dominion.lilayashome.Lab;
import com.base.game.dialogue.places.dominion.lilayashome.Library;
import com.base.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.base.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.base.utils.BaseColour;
import com.base.utils.Bearing;
import com.base.utils.Util;
import com.base.world.EntranceType;
import com.base.world.WorldType;

/**
 * @since 0.1.75
 * @version 0.1.85
 * @author Innoxia
 */
public enum LilayasHome implements PlaceInterface {
	
	// Ground floor:
	
	LILAYA_HOME_CORRIDOR("Corridor", null, null, LilayaHomeGeneric.CORRIDOR, null, true, false),
	
	LILAYA_HOME_ROOM("Room", "dominion/lilayasHome/room", BaseColour.GREY, LilayaHomeGeneric.ROOM, null, true, false) {
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.coreRoomUpgrades;
		}
	},
	LILAYA_HOME_ROOM_WINDOW("Room", "dominion/lilayasHome/room", BaseColour.GREY, LilayaHomeGeneric.ROOM_WINDOW, null, true, false) {
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.coreRoomUpgrades;
		}
	},
	LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR("Room", "dominion/lilayasHome/room", BaseColour.GREY, LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR, null, true, false) {
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.coreRoomUpgrades;
		}
	},
	LILAYA_HOME_ROOM_GARDEN("Room", "dominion/lilayasHome/room", BaseColour.GREY, LilayaHomeGeneric.ROOM_GARDEN, null, true, false) {
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.coreRoomUpgrades;
		}
	},
	
	LILAYA_HOME_ROOM_SLAVE("Slave's Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_SLAVE, null, true, false) {
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.slaveQuartersUpgrades;
		}
	},
	LILAYA_HOME_ROOM_WINDOW_SLAVE("Slave's Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_WINDOW_SLAVE, null, true, false) {
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.slaveQuartersUpgrades;
		}
	},
	LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE("Slave's Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR_SLAVE, null, true, false) {
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.slaveQuartersUpgrades;
		}
	},
	LILAYA_HOME_ROOM_GARDEN_SLAVE("Slave's Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_GARDEN_SLAVE, null, true, false) {
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.slaveQuartersUpgrades;
		}
	},
	
	LILAYA_HOME_BIRTHING_ROOM("Room", "dominion/lilayasHome/roomBirthing", BaseColour.PINK, LilayaHomeGeneric.BIRTHING_ROOM, null, true, false),
	
	LILAYA_HOME_KITCHEN("Kitchen", "dominion/lilayasHome/kitchen", BaseColour.TAN, LilayaHomeGeneric.KITCHEN, null, true, false),
	
	LILAYA_HOME_LIBRARY("Library", "dominion/lilayasHome/library", BaseColour.TEAL, Library.LIBRARY, null, true, false),
	
	LILAYA_HOME_STAIR_UP("Staircase", "dominion/lilayasHome/stairsUp", BaseColour.GREEN_LIGHT, LilayaHomeGeneric.STAIRCASE_UP, null, true, false){
		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.LILAYAS_HOUSE_FIRST_FLOOR;
		}

		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return LILAYA_HOME_STAIR_DOWN;
		}	
	},
	
	LILAYA_HOME_ENTRANCE_HALL("Entrance Hall", "dominion/lilayasHome/entranceHall", BaseColour.RED, LilayaHomeGeneric.ENTRANCE_HALL, null, true, false){
		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.DOMINION;
		}

		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return Dominion.CITY_AUNTS_HOME;
		}	
	},
	
	LILAYA_HOME_LAB("Lilaya's Lab", "dominion/lilayasHome/lab", BaseColour.ORANGE, Lab.LAB, null, true, false),
	
	LILAYA_HOME_GARDEN("Garden", "dominion/lilayasHome/garden", BaseColour.GREEN, LilayaHomeGeneric.GARDEN, null, true, false) {
		@Override
		public boolean isStormImmune() {
			return false;
		}
	},
	
	LILAYA_HOME_FOUNTAIN("Fountain", "dominion/lilayasHome/fountain", BaseColour.BLUE_LIGHT, LilayaHomeGeneric.FOUNTAIN, null, true, false) {
		@Override
		public boolean isStormImmune() {
			return false;
		}
	},
	

	// First floor:

	LILAYA_HOME_ROOM_LILAYA("Lilaya's Room", "dominion/lilayasHome/roomLilaya", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_LILAYA, null, true, false),
	
	LILAYA_HOME_ROOM_ROSE("Rose's Room", "dominion/lilayasHome/roomRose", BaseColour.PINK, LilayaHomeGeneric.ROOM_ROSE, null, true, false),
	
	LILAYA_HOME_ROOM_PLAYER("Your Room", "dominion/lilayasHome/roomPlayer", BaseColour.AQUA, RoomPlayer.ROOM, null, true, false),
	
	LILAYA_HOME_STAIR_DOWN("Staircase", "dominion/lilayasHome/stairsDown", BaseColour.RED, LilayaHomeGeneric.STAIRCASE_DOWN, null, true, false){
		@Override
		public WorldType getLinkedWorldType() {
			return WorldType.LILAYAS_HOUSE_GROUND_FLOOR;
		}

		@Override
		public PlaceInterface getLinkedPlaceInterface() {
			return LILAYA_HOME_STAIR_UP;
		}	
	};

	
	private String name, SVGString;
	private BaseColour colour;
	private DialogueNodeOld dialogue;
	private Encounter encounterType;
	private boolean populated, dangerous;

	LilayasHome(String name,
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
		return false;
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
