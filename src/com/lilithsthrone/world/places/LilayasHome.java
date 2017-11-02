package com.lilithsthrone.world.places;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Library;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Bearing;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.world.EntranceType;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.75
 * @version 0.1.85
 * @author Innoxia
 */
public enum LilayasHome implements PlaceInterface {
	
	// Ground floor:
	
	LILAYA_HOME_CORRIDOR("Corridor", null, BaseColour.GREY, LilayaHomeGeneric.CORRIDOR, Encounter.LILAYAS_HOME_CORRIDOR, true, false),
	
	LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR("Room", "dominion/lilayasHome/room", BaseColour.GREY, LilayaHomeGeneric.ROOM_WINDOW, null, true, false) {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.LILAYA_EMPTY_ROOM));
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.coreRoomUpgrades;
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
	
	LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR("Garden Room", "dominion/lilayasHome/room", BaseColour.GREY, LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR, null, true, false) {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.LILAYA_EMPTY_ROOM));
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.coreRoomUpgrades;
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
	
	LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR("Room", "dominion/lilayasHome/room", BaseColour.GREY, LilayaHomeGeneric.ROOM_WINDOW, null, true, false) {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.LILAYA_EMPTY_ROOM));
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.coreRoomUpgrades;
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
	
	LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR("Garden Room", "dominion/lilayasHome/room", BaseColour.GREY, LilayaHomeGeneric.ROOM_GARDEN, null, true, false) {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.LILAYA_EMPTY_ROOM));
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.coreRoomUpgrades;
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
	
	LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_SLAVE("Slave's Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_WINDOW_SLAVE, null, true, false) {
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.slaveQuartersUpgrades;
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
	},
	
	LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE("Slave's Garden Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR_SLAVE, null, true, false) {
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.slaveQuartersUpgrades;
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
	},
	
	LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_SLAVE("Slave's Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_WINDOW_SLAVE, null, true, false) {
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.slaveQuartersUpgrades;
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
	},
	
	LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_SLAVE("Slave's Garden Room", "dominion/lilayasHome/roomSlave", BaseColour.CRIMSON, LilayaHomeGeneric.ROOM_GARDEN_SLAVE, null, true, false) {
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades() {
			return PlaceUpgrade.slaveQuartersUpgrades;
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
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
