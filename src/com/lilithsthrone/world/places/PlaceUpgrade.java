package com.lilithsthrone.world.places;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaMilkingRoomDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaOfficeDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaSpa;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomArthur;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;


/**
 * @since 0.1.85
 * @version 0.3.9
 * @author Innoxia
 */
public class PlaceUpgrade {

	//**** MISC. UPGRADES ****//
	
	public static final AbstractPlaceUpgrade SLAVERY_ADMINISTRATION_CELLS = new AbstractPlaceUpgrade(true,
			PresetColour.GENERIC_ARCANE,
			"Dingy Cells",
			"-",
			"",
			"Finch doesn't put any effort into the maintenance of the cells beneath Slavery Administration, and as a result, any slaves kept here will quickly lose whatever affection and obedience they have.",
			200,
			0,
			0,
			1000,
			-0.5f,
			-0.5f,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_EMPTY_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_GREY,
			"Empty Room",
			"Rose will return this room to its original state, which will render it unsuitable for housing any of your slaves.",
			"This room is empty, and would need conversion work to be done if you'd like to house any of your slaves here.",
			"This room is unoccupied, and although Rose seems to be doing an excellent job of keeping it clean and well-dusted, it seems a shame that it's not being used to its full potential...",
			2000,
			0,
			0,
			0,
			0,
			0,
			null) {
		
		@Override
		public boolean isSlaverUpgrade() {
			return false;
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(LILAYA_MILKING_ROOM)) {
				Main.game.getOccupancyUtil().removeMilkingRoom(Main.game.getOccupancyUtil().getMilkingRoom(c.getType(), c.getLocation()));
			}
			
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_EMPTY_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			boolean nonCompanionCharactersPresent = false;
			if(cell.getCharactersPresentIds()!=null) {
				for(String id : cell.getCharactersPresentIds()) {
					if(!id.equals(Main.game.getPlayer().getId()) && !Main.game.getPlayer().getCompanionsId().contains(id)) {
						nonCompanionCharactersPresent = true;
					}
				}
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty() || nonCompanionCharactersPresent) {
				return new Value<>(false, "This room needs to be unoccupied and not currently in use in order to purchase this modification.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_ARTHUR_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.RACE_HUMAN,
			"Arthur's Room",
			"Help Rose to move arcane instrumentation into this room in order to make it suitable for Arthur to stay in. <b>This is a permanent modification, and can never be undone!</b>",
			"This room now belongs to Arthur, who uses it as his personal lab-cum-bedroom.",
			"This room is unoccupied, and although Rose seems to be doing an excellent job of keeping it clean and well-dusted, it seems a shame that it's not being used to its full potential...",
			0,
			0,
			0,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			return RoomArthur.ROOM_ARTHUR_INSTALLATION;
		}
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			place.setPlaceType(PlaceType.LILAYA_HOME_ARTHUR_ROOM);
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_ARTHUR_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
			c.getPlace().setName("Arthur's Room");
			if(Main.game.isStarted()) {
				Main.game.getNpc(Arthur.class).setLocation(c.getType(), c.getLocation(), true);
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_2_A_INTO_THE_DEPTHS)
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_ARTHUR_ROOM).isEmpty()
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_ARTHUR_ROOM).isEmpty()) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "This room needs to be unoccupied in order to purchase this modification.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};


	//**** PLAYER'S ROOM UPGRADES ****//

	public static final AbstractPlaceUpgrade LILAYA_PLAYER_ROOM_BED = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_GOLD,
			"Emperor-Size Bed",
			"Have your current, king-size bed replaced by a huge, 'emperor-size' one. [style.italicsGood(This will improve the 'well rested' bonus gained from resting in your room.)]",
			"Your old, king-size bed has been replaced by a huge, 'emperor-size' one. [style.italicsGood(The 'well rested' bonus gained from resting in your room has been improved!)]",
			"Your old, king-size bed has been replaced by a huge, 'emperor-size' one, which sits in a dominant position against one wall of your room."
					+ " Its comfortable mattress, fluffy pillows, and warm duvet ensure that you always feel extremely well rested after sleeping in it.",
			10000,
			-5000,
			0,
			0,
			0.2f,
			-0.1f,
			null) {
		public Value<Boolean, String> getAvailability(Cell cell) {
			return new Value<>(true, "");
		}
	};
	
	
	//**** GUEST ROOM ****//
	
	public static final AbstractPlaceUpgrade LILAYA_GUEST_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.GENERIC_ARCANE,
			"Guest Room",
			"Rose will prepare this room for one of your guests, making it suitable for housing one person."
					+ " While not the most economical option, the occupant of this room will no doubt appreciate having their own personal space.",
			"This room has been converted into a suitable place for housing one of your guests.",
			"You've paid to have this room converted into a basic guest room."
					+ " A single-size bed, covered in a plain white duvet, sits against one wall."
					+ " Beside it, there's a simple bedside cabinet, complete with arcane-powered lamp."
					+ " Other than that, the only other pieces of furniture in here are a wooden wardrobe and chest of drawers.",
			2000,
			0,
			100,
			1,
			0,
			0,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomGuest", PresetColour.BASE_GREEN_LIGHT);
		}
		@Override
		public boolean isSlaverUpgrade() {
			return false;
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
				return new Value<>(false, "To install a guest bedroom, you'd need to first find someone to invite to live with you, and then get Lilaya's permission to make the upgrade.");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "This room needs to be unoccupied in order to purchase this modification.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_GUEST_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};

	
	//**** SLAVE UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.GENERIC_ARCANE,
			"Slave's Room",
			"Rose will prepare this room just like she would for any other guest, making it suitable for housing just one of your slaves."
					+ " While not the most economical choice if you plan on owning a large number of slaves, the occupant of this room will no doubt appreciate having their own personal space.",
			"This room has been converted into a suitable place for housing one of your slaves.",
			"You've paid to have this room converted into basic slave's quarters."
					+ " A single-size bed, covered in a plain white duvet, sits against one wall."
					+ " Beside it, there's a simple bedside cabinet, complete with arcane-powered lamp."
					+ " Other than that, the only other pieces of furniture in here are a wooden wardrobe and chest of drawers.",
			2000,
			0,
			100,
			1,
			0,
			0,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlave", PresetColour.BASE_CRIMSON);
		}
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return "You've paid to have this room converted into basic slave's quarters."
						+ " A comfortable double size bed, covered in a warm, fluffy duvet, sits against one wall."
						+ " Beside it, there's a simple bedside cabinet, complete with arcane-powered lamp."
						+ " Other than that, the only other pieces of furniture in here are a wooden wardrobe and chest of drawers.";
				
			} else if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED)) {
				return "You've paid to have this room converted into basic slave's quarters."
						+ " An uncomfortable single-size bed, covered in a thin blanket, sits against one wall."
						+ " Beside it, there's a simple bedside cabinet, complete with arcane-powered lamp."
						+ " Other than that, the only other pieces of furniture in here are a wooden wardrobe and chest of drawers.";
		
			}else {
				return "You've paid to have this room converted into basic slave's quarters."
						+ " A single-size bed, covered in a plain white duvet, sits against one wall."
						+ " Beside it, there's a simple bedside cabinet, complete with arcane-powered lamp."
						+ " Other than that, the only other pieces of furniture in here are a wooden wardrobe and chest of drawers.";
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "This room needs to be unoccupied in order to purchase this modification.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_SLAVE_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_DOUBLE = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_MAGENTA,
			"Double Slave Room",
			"Rose will prepare this room just like she would for any other guest, making it suitable for housing two of your slaves."
					+ " While more cost-effective than giving each slave their own room, the occupants will no doubt be a little frustrated at having to share their personal space with another slave,"
						+ " and will also get the opportunity to conspire with one another against you.",
			"This room has been converted into a suitable place for housing two of your slaves.",
			"You've paid to have this room converted so that it's suitable for housing two of your slaves."
					+ " A pair of single-size beds, covered in a plain white duvets, sit against opposite walls."
					+ " Beside each one, there's a simple bedside cabinet, complete with arcane-powered lamp."
					+ " Other than that, the only other pieces of furniture in here are a single wooden wardrobe and solitary chest of drawers.",
			3500,
			0,
			100,
			2,
			-0.05f,
			-0.05f,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveDouble", PresetColour.BASE_MAGENTA);
		}
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return "You've paid to have this room converted so that it's suitable for housing two of your slaves."
							+ " Two double size beds, covered in warm, fluffy duvets, sit against opposite walls."
							+ " On either side of them, there are simple bedside cabinets, each complete with its own arcane-powered lamp."
							+ " Other than those, the only other pieces of furniture in here are a pair of wooden wardrobes and two chests of drawers.";
				
			} else if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED)) {
				return "You've paid to have this room converted so that it's suitable for housing two of your slaves."
						+ " A pair of uncomfortable single-size beds, covered in thin blankets, sit against opposite walls."
						+ " Beside each one, there's a simple bedside cabinet, complete with arcane-powered lamp."
						+ " Other than that, the only other pieces of furniture in here are a single wooden wardrobe and solitary chest of drawers.";
		
			}else {
				return "You've paid to have this room converted so that it's suitable for housing two of your slaves."
					+ " A pair of single-size beds, covered in a plain white duvets, sit against opposite walls."
					+ " Beside each one, there's a simple bedside cabinet, complete with arcane-powered lamp."
					+ " Other than that, the only other pieces of furniture in here are a single wooden wardrobe and solitary chest of drawers.";
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty() && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)) {
				return new Value<>(false, "This room needs to be unoccupied in order to purchase this modification.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)) {
				place.removePlaceUpgrade(c, LILAYA_SLAVE_ROOM);
				
			} else {
				for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
					if(upgrade != LILAYA_SLAVE_ROOM_DOUBLE) {
						place.removePlaceUpgrade(c, upgrade);
					}
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_QUADRUPLE = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_MAGENTA,
			"Quadruple Slave Room",
			"Rose will prepare this room just like she would for any other guest, making it suitable for housing four of your slaves."
					+ " While a lot more cost-effective than giving each slave their own room, the occupants will no doubt be a little frustrated at having to share their personal space with another slave,"
						+ " and will also get plenty of opportunities to conspire with one another against you.",
			"This room has been converted into a suitable place for housing four of your slaves.",
			"You've paid to have this room converted so that it's suitable for housing four of your slaves."
					+ " Four single-size beds, covered in a plain white duvets, sit against the walls."
					+ " Beside each one, there's a simple bedside cabinet, complete with arcane-powered lamp."
					+ " Other than that, the only other pieces of furniture in here are a pair of wooden wardrobes and two chests of drawers.",
			6000,
			0,
			100,
			4,
			-0.1f,
			-0.2f,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveQuadruple", PresetColour.BASE_MAGENTA);
		}
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return "You've paid to have this room converted so that it's suitable for housing four of your slaves."
						+ " Four double size beds, covered in warm, fluffy duvets, sit against the walls."
						+ " On either side of each one, there are simple bedside cabinets, upon which there are arcane-powered lamps."
						+ " Other than those, the only other pieces of furniture in here are four wooden wardrobes and four chests of drawers.";
				
			} else if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED)) {
				return "You've paid to have this room converted so that it's suitable for housing four of your slaves."
						+ " Four uncomfortable, single-size beds, covered in thin blankets, sit against the walls."
						+ " Beside each one, there's a simple bedside cabinet, complete with arcane-powered lamp."
						+ " Other than those, the only other pieces of furniture in here are a pair of wooden wardrobes and two chests of drawers.";
		
			}else {
				return "You've paid to have this room converted so that it's suitable for housing four of your slaves."
					+ " Four single-size beds, covered in plain white duvets, sit against the walls."
					+ " Beside each one, there's a simple bedside cabinet, complete with arcane-powered lamp."
					+ " Other than that, the only other pieces of furniture in here are a single wooden wardrobe and solitary chest of drawers.";
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()
					 && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)
					 && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				return new Value<>(false, "This room needs to be unoccupied in order to purchase this modification.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)) {
				place.removePlaceUpgrade(c, LILAYA_SLAVE_ROOM);
				
			} else if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				place.removePlaceUpgrade(c, LILAYA_SLAVE_ROOM_DOUBLE);
				
			} else {
				for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
					if(upgrade != LILAYA_SLAVE_ROOM_QUADRUPLE) {
						place.removePlaceUpgrade(c, upgrade);
					}
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_DOWNGRADE_BED = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_BAD,
			"Small Steel Bed",
			"Replace the bed in this room with a steel-framed one, complete with an uncomfortable mattress, a hard pillow, and a thin blanket."
					+ " These changes will leave no doubt in the occupant's mind that they're a slave.",
			"A small, single size bed, complete with an uncomfortable mattress, a hard pillow, and a thin blanket, has been installed in this room.",
			"A small, single size bed, complete with an uncomfortable mattress, a hard pillow, and a thin blanket, sits against one side of the room."
					+ " Providing this room's occupant with such an uncomfortable place to sleep will definitely reinforce the fact that they're a slave, but at the same time, they're bound to dislike you more...",
			250,
			100,
			-10,
			0,
			-0.1f,
			0.2f,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_QUADRUPLE)) {
				return "The four single-sized beds in this room have all been replaced with basic, steel-framed ones, and are equipped with uncomfortable mattress, hard pillows, and thin blankets."
						+ " Providing this room's occupants with such uncomfortable places to sleep will definitely help them to accept the fact that they're slaves, but at the same time, they're bound to dislike you more...";
				
			} else if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				return "This room's pair of single-sized beds have been replaced with basic, steel-framed ones, and are equipped with uncomfortable mattress, hard pillows, and thin blankets."
						+ " Providing this room's occupants with such uncomfortable places to sleep will definitely help them to accept the fact that they're slaves, but at the same time, they're bound to dislike you more...";
				
			} else {
				return "The small, single size bed in this room has been replaced with a basic, steel-framed one, and is equipped with an uncomfortable mattress, a hard pillow, and a thin blanket."
						+ " Providing this room's occupant with such an uncomfortable place to sleep will definitely help them to accept the fact that they're a slave, but at the same time, they're bound to dislike you more...";
			}
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return new Value<>(false, "The 'Double Size Bed' upgrade must be removed before the 'Small Steel Bed' can be installed.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_UPGRADE_BED = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_GOOD,
			"Double Size Bed",
			"Install a double size bed in this room, along with a more comfortable mattress, fluffy pillows, and a warm duvet."
					+ " The occupant of this room is sure to appreciate these upgrades.",
			"A double size bed, complete with a comfortable mattress, fluffy pillows, and a warm duvet, has been installed in this room.",
			"A double size bed, complete with a comfortable mattress, fluffy pillows, and a warm duvet, sits against one side of the room."
					+ " Providing this room's occupant with such a delightful place to sleep will definitely get them to like you more, although such luxury might make them forget their place...",
			500,
			200,
			25,
			0,
			0.2f,
			-0.1f,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_QUADRUPLE)) {
				return "The four single-sized beds in this room have all been replaced with double-sized ones, and come complete with comfortable mattresses, fluffy pillows, and warm duvets."
						+ " Providing this room's occupants with such comfortable places to sleep will definitely get them to like you more, although such luxury might make them forget their place...";
				
			} else if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				return "This room's pair of single-sized beds have been replaced with double-sized ones, and come complete with comfortable mattresses, fluffy pillows, and warm duvets."
						+ " Providing this room's occupants with such comfortable places to sleep will definitely get them to like you more, although such luxury might make them forget their place...";
				
			} else {
				return "A double size bed, complete with a comfortable mattress, fluffy pillows, and a warm duvet, sits against one side of the room."
						+ " Providing this room's occupant with such a delightful place to sleep will definitely get them to like you more, although such luxury might make them forget their place...";
			}
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOWNGRADE_BED)) {
				return new Value<>(false, "The 'Small Steel Bed' upgrade must be removed before the 'Double Size Bed' can be installed.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"Obedience Trainer",
			"Lilaya has asked you if you'd consider installing one of her experimental devices in this room; a so-called 'obedience trainer'."
					+ " This particular addition takes the form of a large, glowing crystal that is to be placed in the centre of the room."
					+ " Whenever the room's occupant thinks a disobedient thought, the crystal will shoot out a shocking bolt of arcane energy, thereby training a slave's obedience in the most intrusive fashion imaginable.",
			"You've installed one of Lilaya's experimental devices in this room; a so-called 'obedience trainer'."
					+ " This particular addition takes the form of a large, glowing crystal that has been placed in the centre of the room."
					+ " Whenever the room's occupant thinks a disobedient thought, the crystal shoots out a shocking bolt of arcane energy, thereby training a slave's obedience in the most intrusive fashion imaginable.",
			"One of Lilaya's experimental devices, a so-called 'obedience trainer', has been installed in the middle of this room."
					+ " Taking the form of a large, glowing crystal, the obedience trainer will shoot a shocking bolt of arcane energy at any slave nearby that dares to think a disobedient thought."
					+ " Although highly effective at training obedience, any slaves subjected to this intrusive training method will be sure to loathe you before long...",
			10000,
			500,
			250,
			0,
			-0.2f,
			0.4f,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_DOG_BOWLS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_BAD,
			"Dog Bowls",
			"Have meals served to this room's occupants in dog bowls placed upon the floor."
					+ " Being forced to eat in such a humiliating fashion is sure to make your slaves dislike you, but it will also serve to emphasise the fact that they're nothing more than your property.",
			"Metal dog bowls have been placed upon the floor in this room, and it's from out of these that this room's occupants are made to eat their meals."
					+ " Being forced to eat in such a humiliating fashion is making your slaves dislike you, but it is also serving to emphasise the fact that they're nothing more than your property.",
			"A series of metal dog bowls have been placed in one corner of the room, and it's from out of these that this room's occupants are expected to eat and drink."
					+ " Being forced to get down on all fours and eat their meals like a dog is making your slaves dislike you, but at the same time, it's hammering home the fact that they're nothing more than your property...",
			100,
			0,
			10,
			0,
			-0.2f,
			0.25f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_ROOM_SERVICE)) {
				return new Value<>(false, "The 'Room Service' upgrade must be removed before the 'Dog Bowls' can be used.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_ROOM_SERVICE = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"Room Service",
			"You could offer this room's occupant unlimited room service."
					+ " This isn't exactly how most owners treat their slaves, and while it's sure to make the occupant like you more, it's also going to cost quite a bit in upkeep, as well as have some negative effects on their obedience...",
			"You've offered unlimited room service to the occupants of this room."
					+ " It's definitely making them like you more, but it's also costing a fair amount in upkeep, and is having a negative effect on your slaves' obedience...",
			"A little push-trolley with a few empty silver plates and glasses stacked on top of it is evidence that the slaves who live here are taking full advantage of the unlimited room service you've offered to them."
					+ " It's definitely making them like you more, but having such a luxury available to them is also having a negative impact on their obedience, not to mention the damage it's doing to your finances...",
			500,
			0,
			250,
			0,
			0.4f,
			-0.2f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOG_BOWLS)) {
				return new Value<>(false, "The 'Dog Bowls' upgrade must be removed before the 'Room Service' can be used.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"Arcane Instruments",
			"Allow Lilaya to install arcane sensors in this room, so that she can gather useful data on your slave's aura."
					+ " Your slaves will find this to be an intrusion on what little personal space they have, and their affection towards you will suffer as a result.",
			"A series of arcane sensors have been set up around this room, allowing Lilaya to gather useful data on your slave's aura."
					+ " Your slaves find this to be an intrusion on what little personal space they have, and their affection towards you will suffer as a result.",
			"In exchange for lowering this room's upkeep, Lilaya has installed several arcane instruments around the room, which allow her to gather data about any slave being housed in here."
					+ " A couple of them give off a very quiet humming noise, which, combined with their faint purple glow, makes them quite intrusive, and will negatively impact the occupant's affection towards you.",
			500,
			100,
			-25,
			0,
			-0.1f,
			0f,
			null) {
	};

	//**** DUNGEON UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_MAGENTA,
			"Dungeon Cell",
			"-",
			"This cell is just about large enough to hold four of your unlucky slaves.",
			"-",
			0,
			0,
			10,
			4,
			-0.15f,
			0.2f,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveQuadruple", PresetColour.BASE_MAGENTA);
		}
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("With the walls, floor, and ceiling being constructed out of large, grey stones, this cell has a gloomy, oppressive feel to it."
					+ " A couple of metal sconces are fixed into opposite walls of the cell, with each one holding an arcane torch."
					+ " Their perpetually-burning flames occasionally splutter and flicker, casting dancing shadows across the cell's interior."
					+ " A small, barred window is set into the cell's heavy wooden door, allowing any unlucky slaves who are being held within to be monitored from the safety of the corridor.");
			
			if(!place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_DUNGEON_CELL_UPGRADE_BED) && !place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_DUNGEON_CELL_DOWNGRADE_BED)) {
				sb.append("<br/><br/>"
						+ "Four small, steel-framed beds are located within this cell, each of which holds a thin mattress, a couple of deflated pillows, and a ragged blanket."
						+ " Old, wooden boxes positioned beside these beds serve as crude substitutes for bedside cabinets."
						+ " Other than these beds and crates, there are no other pieces of furniture within the cell.");
			}
			
			return sb.toString();
		}
	};
	

	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_DOWNGRADE_BED = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_BAD,
			"Straw Bedding",
			"Remove the steel-framed beds in this cell and replace them with piles of straw."
					+ " Providing this cell's slaves with such uncomfortable places to sleep will definitely reinforce the fact that they're nothing but your property, but at the same time, they're bound to dislike you more...",
			"The beds in this room have been removed and replaced with piles of straw.",
			"A small, single size bed, complete with an uncomfortable mattress, a hard pillow, and a thin blanket, sits against one side of the room."
					+ " Providing this cell's slaves with such uncomfortable places to sleep is definitely reinforcing the fact that they're nothing but your property, but at the same time, they're starting to dislike you more...",
			100,
			25,
			-5,
			0,
			-0.1f,
			0.2f,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			return "The four steel-framed beds in this room have all been removed and replaced with piles of straw."
					+ " The old, wooden boxes which served as crude substitutes for bedside cabinets have also been replaced by the simple wooden frames which the straw was packaged in."
					+ " Providing this cell's slaves with such uncomfortable places to sleep will definitely reinforce the fact that they're nothing but your property, but at the same time, they're bound to dislike you more...";
				
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_UPGRADE_BED)) {
				return new Value<>(false, "The 'Improved Bedding' upgrade must be removed before the 'Straw Bedding' can be installed.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_UPGRADE_BED = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_GOOD,
			"Improved Bedding",
			"Remove the beds' thin mattresses, deflated pillows, and ragged blankets, and replace them with more comfortable alternatives."
					+ " Providing this cell's slaves with a higher level of comfort will definitely get them to like you more, although such relative luxury might make them forget their place...",
			"The four beds in this cell have had their mattresses, pillows, and blankets replaced with more comfortable alternatives.",
			"The four beds in this cell have had their mattresses, pillows, and blankets replaced with more comfortable alternatives."
					+ " Providing this cell's slaves with a higher level of comfort is definitely getting them to like you more, although such relative luxury is making them forget their place...",
			250,
			50,
			5,
			0,
			0.05f,
			-0.05f,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			return "The four beds in this cell have had their mattresses, pillows, and blankets replaced with more comfortable alternatives."
					+ " Providing this cell's slaves with a higher level of comfort will definitely get them to like you more, although such relative luxury might make them forget their place...";
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_DOWNGRADE_BED)) {
				return new Value<>(false, "The 'Straw Bedding' upgrade must be removed before the 'Improved Bedding' can be installed.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};

	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_DOG_BOWLS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_BAD,
			"Dog Bowls",
			"Have meals served to this cell's slaves in dog bowls placed upon the floor."
					+ " Being forced to eat in such a humiliating fashion is sure to make your slaves dislike you, but it will also serve to emphasise the fact that they're nothing more than your property.",
			"Metal dog bowls have been placed upon the floor, and it's from out of these that this cell's slaves are made to eat their meals.",
			"Four pairs of metal dog bowls have been placed upon the floor, and it's from out of these that the cell's slaves are expected to eat and drink."
					+ " Being forced to get down on all fours and eat their meals like a dog is making your slaves dislike you, but at the same time, it's hammering home the fact that they're nothing more than your property...",
			100,
			0,
			10,
			0,
			-0.2f,
			0.25f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_DECENT_FOOD)) {
				return new Value<>(false, "The 'Decent Food' upgrade must be removed before the 'Dog Bowls' can be used.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_DECENT_FOOD = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_GOOD,
			"Decent Food",
			"Replace the cold, bland meals served to this cell's slaves with decent, hot food."
					+ " Providing this cell's slaves with nicer food will definitely get them to like you more, although such relative luxury might make them forget their place...",
			"You've replaced the cold, bland meals served to this cell's slaves with decent, hot food.",
			"You've replaced the cold, bland meals served to this cell's slaves with decent, hot food."
					+ " Providing this cell's slaves with nicer food is definitely getting them to like you more, although such relative luxury is making them forget their place...",
			250,
			0,
			100,
			0,
			0.1f,
			-0.1f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_DOG_BOWLS)) {
				return new Value<>(false, "The 'Dog Bowls' upgrade must be removed before the 'Decent Food' can be used.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};

	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_ROPES = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_TERRIBLE,
			"Rope Restraints",
			"Attach sturdy ropes to the walls of this cell and use them to bind your slaves in place."
					+ " This will prevent your slaves from interacting with one another, and if in a submissive slot, they will start sex scenes with the 'Bound in rope' immobilising effect.",
			"Sturdy ropes have been attached to the walls of this cell, and are being used to bind the occupying slaves in place.",
			"Sturdy ropes have been attached to the walls of this cell, and are being used to bind the occupying slaves in place."
					+ " This is preventing your slaves from interacting with one another, and is making them more obedient at the cost of disliking you more...",
			250,
			100,
			10,
			0,
			-0.2f,
			0.15f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_CHAINS)) {
				return new Value<>(false, "The 'Chain Restraints' upgrade must be removed before the 'Rope Restraints' can be used.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public ImmobilisationType getImmobilisationType() {
			return ImmobilisationType.ROPE;
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_CHAINS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_TERRIBLE,
			"Chain Restraints",
			"Attach metal chains to the walls of this cell and use them to bind your slaves in place."
					+ " This will prevent your slaves from interacting with one another, and if in a submissive slot, they will start sex scenes with the 'Bound in chains' immobilising effect.",
			"Metal chains have been attached to the walls of this cell, and are being used to bind the occupying slaves in place.",
			"Metal chains have been attached to the walls of this cell, and are being used to bind the occupying slaves in place."
					+ " This is preventing your slaves from interacting with one another, and is making them more obedient at the cost of disliking you more...",
			500,
			250,
			25,
			0,
			-0.25f,
			0.3f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_ROPES)) {
				return new Value<>(false, "The 'Rope Restraints' upgrade must be removed before the 'Chain Restraints' can be used.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public ImmobilisationType getImmobilisationType() {
			return ImmobilisationType.CHAINS;
		}
	};
	
	
	//**** MILKING UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_ORANGE,
			"Milking Room",
			"Install milking machines in this room, allowing [style.colourGood(eight)] of your slaves to be assigned to work in here, each of which will be milked of their milk and cum.<br/>"
					+ "<i>Milk: "+Units.fluid(MilkingRoom.BASE_MILKING_AMOUNT)+" per hour<br/>"
					+ "Cum: "+Units.fluid(MilkingRoom.BASE_CUM_MILKING_AMOUNT)+" per hour<br/>"
					+ "Girlcum: "+Units.fluid(MilkingRoom.BASE_GIRLCUM_MILKING_AMOUNT)+" per hour</i>",
			"This room has been converted into a suitable place for milking [style.colourGood(eight)] of your slaves' milk and cum.",
			"This room has been converted into a special milking room, in which eight of your slaves can be milked of their various fluids."
					+ " Four machines are set along the left-hand side of the wall, with the other four being placed on the opposite side of the room.",
			10000,
			0,
			500,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaMilkingRoomDialogue.MILKING_ROOM;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomMilking", PresetColour.BASE_YELLOW_LIGHT);
		}
		@Override
		public String getRoomDescription(Cell c) {
			MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(c.getType(), c.getLocation());
			
			return room.getRoomDescription();
		}
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_MILKING_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
			if(Main.game.getOccupancyUtil().getMilkingRoom(c.getType(), c.getLocation())==null) {
				Main.game.getOccupancyUtil().addMilkingRoom(new MilkingRoom(c.getType(), c.getLocation()));
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "This room needs to be unoccupied in order to purchase this modification.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_ARTISAN_MILKERS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"Artisan Milkers",
			"You could replace the standard milking machines in this room with very expensive artisan ones."
					+ " While being far more comfortable for the slaves that use them, these milking machines seem to be designed more for show than practicality, and deliver a slightly lower milk output than the regular machines.<br/>"
					+ "<i>Milk: "+Units.fluid(MilkingRoom.ARTISAN_MILKING_AMOUNT)+" per hour<br/>"
					+ "Cum: "+Units.fluid(MilkingRoom.ARTISAN_CUM_MILKING_AMOUNT)+" per hour<br/>"
					+ "Girlcum: "+Units.fluid(MilkingRoom.ARTISAN_GIRLCUM_MILKING_AMOUNT)+" per hour</i>",
			"You've installed artisan milking machines in this room."
					+ " The slaves that have the good fortune to be locked into these machines are sure to appreciate you for it.<br/>"
					+ "<i>Milk: "+Units.fluid(MilkingRoom.ARTISAN_MILKING_AMOUNT)+" per hour<br/>"
					+ "Cum: "+Units.fluid(MilkingRoom.ARTISAN_CUM_MILKING_AMOUNT)+" per hour<br/>"
					+ "Girlcum: "+Units.fluid(MilkingRoom.ARTISAN_GIRLCUM_MILKING_AMOUNT)+" per hour</i>",
			"The artisan, arcane-powered milking machines that have been placed in this room fill the air with a very soft, almost melodic, humming noise."
					+ " Although they're far more comfortable than regular milking machines, they appear to be designed more for show than practicality, and while your slaves are sure to be happy, milk output is a lot lower than normal.",
			2500,
			500,
			500,
			0,
			1f,
			0.5f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)) {
				return new Value<>(false, "The 'Industrial Milkers' upgrade must be removed before the 'Artisan Milkers' can be installed.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"Industrial Milkers",
			"You could replace the standard milking machines in this room with industrial-grade ones."
					+ " While being far less comfortable for the slaves that use them, these milking machines maximise both milk output and profit.<br/>"
					+ "<i>Milk: "+Units.fluid(MilkingRoom.INDUSTRIAL_MILKING_AMOUNT)+" per hour<br/>"
					+ "Cum: "+Units.fluid(MilkingRoom.INDUSTRIAL_CUM_MILKING_AMOUNT)+" per hour<br/>"
					+ "Girlcum: "+Units.fluid(MilkingRoom.INDUSTRIAL_GIRLCUM_MILKING_AMOUNT)+" per hour</i>",
			"You've installed industrial milking machines in this room."
					+ " The slaves that have the misfortune to be locked into these machines are sure to hate you for it.<br/>"
					+ "<i>Milk: "+Units.fluid(MilkingRoom.INDUSTRIAL_MILKING_AMOUNT)+" per hour<br/>"
					+ "Cum: "+Units.fluid(MilkingRoom.INDUSTRIAL_CUM_MILKING_AMOUNT)+" per hour<br/>"
					+ "Girlcum: "+Units.fluid(MilkingRoom.INDUSTRIAL_GIRLCUM_MILKING_AMOUNT)+" per hour</i>",
			"The industrial, arcane-powered milking machines that have been placed in this room fill the air with a steady background humming noise."
					+ " Although they're sure to maximise milk output, and profits, these machines aren't exactly the most comfortable of devices to be strapped in to, and any slaves assigned to be milked in here are sure to hate you for it...",
			1500,
			500,
			1000,
			0,
			-1f,
			0.5f,
			null) {
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_MILKING_ROOM_ARTISAN_MILKERS)) {
				return new Value<>(false, "The 'Artisan Milkers' upgrade must be removed before the 'Industrial Milkers' can be installed.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_MILK_EFFICIENCY = new AbstractPlaceUpgrade(false,
			PresetColour.MILK,
			"Lact-o-Cups",
			"The company that makes the milking machines also offers a selection of aftermarket upgrades. One of these is 'Lact-o-Cups', which promises to double the machine's maximum hourly milking efficiency.",
			"You've added the optional 'Lact-o-Cups' to the milking machines in this room, doubling their maximum hourly milking efficiency.",
			"The standard suction cups on each machine have been replaced with aftermarket 'Lact-o-Cups', which are doubling the maximum amount of milk extracted per hour.",
			500,
			100,
			200,
			0,
			0,
			0,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_CUM_EFFICIENCY = new AbstractPlaceUpgrade(false,
			PresetColour.CUM,
			"Succ-u-Buses",
			"The company that makes the milking machines also offers a selection of aftermarket upgrades. One of these is 'Succ-u-Buses', which promises to double the machine's maximum hourly cum-milking efficiency.",
			"You've added the optional 'Succ-u-Buses' to the milking machines in this room, doubling their maximum hourly cum-milking efficiency.",
			"The standard cock-milking tubes on each machine have been replaced with aftermarket 'Succ-u-Buses', which are doubling the maximum amount of cum extracted per hour.",
			500,
			100,
			200,
			0,
			0,
			0,
			null) {
	};

	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_GIRLCUM_EFFICIENCY = new AbstractPlaceUpgrade(false,
			PresetColour.GIRLCUM,
			"Vibro-Pumps",
			"The company that makes the milking machines also offers a selection of aftermarket upgrades. One of these is 'Vibro-Pumps', which promises to double the machine's maximum hourly girlcum-milking efficiency.",
			"You've added the optional 'Vibro-Pumps' to the milking machines in this room, doubling their maximum hourly girlcum-milking efficiency.",
			"The standard vaginal pumps on each machine have been replaced with aftermarket 'Vibro-Pumps', which are doubling the maximum amount of girlcum extracted per hour.",
			500,
			100,
			200,
			0,
			0,
			0,
			null) {
	};

	
	//**** OFFICE UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_OFFICE = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_TEAL,
			"Office",
			"Due to the heavily-regulated exotic materials which Lilaya regularly orders for use in her laboratory, she has a significant amount of paperwork which needs to be completed each month."
					+ " By having Rose replace this room's furniture with desks, chairs, and filing cabinets, you could have it turned into an office space in which [style.colourGood(four)] of your slaves could be paid to complete this work for her."
					+ " [style.italicsGood(You will also gain access to the 'Occupancy ledger' when in the office!)]",
			"This room has been converted into an office, with enough desks and room to comfortably accommodate [style.colourGood(four)] workers."
					+ " [style.italicsGood(You have also gained access to the 'Occupancy ledger' when in the office!)]",
			"In order to help Lilaya with her copious amounts of paperwork related to exotic material acquisition, you've had this room converted into a four-person-capacity office."
					+ " Along with the forms related to Lilaya's heavily-regulated purchases, the workers assigned here are tasked with keeping records in a general 'Occupancy ledger', which you can access here at any time.",
			8000,
			500,
			250,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaOfficeDialogue.ROOM_OFFICE;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomOffice", PresetColour.BASE_LILAC);
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_OFFICE).isEmpty()
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_OFFICE).isEmpty()) {
				return new Value<>(false, "There isn't enough paperwork to justify having more than one office.");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "This room needs to be unoccupied in order to purchase this modification.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_OFFICE) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_OFFICE_EXECUTIVE_UPGRADE = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_GOLD,
			"Executive Office",
			"Tell Rose to outfit this office with the most luxurious and extravagant furnishings she can get her hands on."
					+ " Although sure to instill a sense of respect in any worker who's assigned to this office, the finest furniture in Dominion doesn't exactly come cheap...",
			"You've had this office fitted out with the most upmarket furnishings available, which are sure to instill a sense of respect for you in anyone who's assigned to work here.",
			"You've had this office outfitted with the most luxurious and extravagantly opulent furnishings which money can buy."
					+ " Each of the four work stations have their own masterfully carved mahogany desk, and fixed to the walls behind them are shelves filled with artisanal leather-bound record books.",
			500_000,
			-200_000,
			50,
			0,
			0.25f,
			1f,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_OFFICE_COFFEE_MACHINE = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_BROWN_DARK,
			"Coffee Machine",
			"Rose has informed you that she knows of an excellent arcane-powered coffee machine available for purchase."
					+ " Capable of dispensing a wide range of both hot and cold caffeinated beverages, such a machine is sure to be appreciated by the workers assigned to this office.",
			"You've purchased an arcane-powered coffee machine for this office, which is very much appreciated by the slaves assigned to work here.",
			"An arcane-powered coffee machine, capable of dispensing a wide range of both hot and cold caffeinated beverages, sits in one corner of the room.",
			5000,
			100,
			250,
			0,
			0.1f,
			0.05f,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_OFFICE_PARTITIONING_WALLS = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_TAN,
			"Partitioning Walls",
			"You could have some partitioning walls set up in this office, which would give each of the four potential workers assigned here their own personal space in which to get on with their work.",
			"You've had several partitioning walls set up in this office, which afford each worker a little more privacy and peace in which to get on with the work that's assigned to them.",
			"Each of the four work stations in this office have been separated from one another by means of partitioning walls, affording the workers some privacy and peace in which to get on with the tasks assigned to them.",
			2500,
			500,
			0,
			0,
			0.05f,
			0,
			null) {
	};
	

	//**** SPA UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_SPA = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_AQUA,
			"Spa",
			"By completely stripping and repurposing this room, it would be possible to transform it into a private spa."
					+ " While it would no doubt make an excellent addition to the mansion, the rerouting of geothermal springs to make an indoor pool is going to be extremely expensive..."
					+ "<br/>[style.italicsbad(Only one spa can be installed, and it cannot be removed, so make sure that you want it in this tile before installing it!)]",
			"This room has been completely renovated and transformed into a luxurious, private spa."
					+ " In the middle of the marble floor, there are a series of large pools, each of which is filled with warm water drawn from geothermal springs.",
			"This room has been completely renovated and transformed into a luxurious, private spa, complete with private showers and changing rooms."
					+ " In the middle of the marble floor, there are a series of large pools, each of which is filled with warm water drawn from geothermal springs."
					+ " Scattered about the room, there are numerous comfortable loungers, offering places to relax and recuperate for if you don't feel like taking a dip in one of the pools.",
			1_500_000,
			250_000,
			500,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			LilayaSpa.setCellInstallation(c);
			return LilayaSpa.SPA_INSTALLATION;
		}
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaSpa.SPA_RECEPTION;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSpaReception", PresetColour.BASE_BLUE_STEEL);
		}
		@Override
		public boolean isSlaverUpgrade() {
			return false;
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_SPA).isEmpty()
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_SPA).isEmpty()) {
				return new Value<>(false, "You can only have one spa!");
			}
			int size = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).WORLD_WIDTH;
			if(cell.getType()!=WorldType.LILAYAS_HOUSE_GROUND_FLOOR
					|| cell.getLocation().getY()<2
					|| (Math.abs(cell.getLocation().getY()-size)>2 && Math.min(cell.getLocation().getX(), Math.abs(size-cell.getLocation().getX()))>2)) {
				return new Value<>(false, "The spa can only be built on the North, East, or West sides of the ground floor!");
			}
			if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& (Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0) || Main.game.getNpc(Lilaya.class).isPregnant())) {
				return new Value<>(false, "Lilaya needs to resolve her pregnancy first!");
			}
			if(Lab.isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
				return new Value<>(false, "You need to confront Lilaya about your demonic form first!");
			}
			if(!Main.game.isExtendedWorkTime()) {
				return new Value<>(false, "You can only install the spa while Lilaya is awake!");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "This room needs to be unoccupied in order to purchase this modification.");
			}
			
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public void applyInstallationEffects(Cell cell) {
			GenericPlace place = cell.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_SPA) {
					place.removePlaceUpgrade(cell, upgrade);
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SPA_SAUNA = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_ROSE,
			"Sauna (extension)",
			"There's still a considerable amount of space in which a further extension to the spa could be built..."
				+ "<br/>If you wanted to, you could suggest to Lilaya that a sauna would be a valuable addition to the spa.",
			"",
			"",
			150_000,
			25_000,
			100,
			0,
			0.25f,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			return LilayaSpa.SPA_SAUNA_INSTALLATION;
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return new Value<>(false, "The main spa extension needs to be finished before you can extend it further!");
			}
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION)!=null) {
				return new Value<>(false, "You need to wait for the swimming pool extension to be finished before starting this one!");
			}
			if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& (Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0) || Main.game.getNpc(Lilaya.class).isPregnant())) {
				return new Value<>(false, "Lilaya needs to resolve her pregnancy first!");
			}
			if(Lab.isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
				return new Value<>(false, "You need to confront Lilaya about your demonic form first!");
			}
			if(!Main.game.isExtendedWorkTime()) {
				return new Value<>(false, "You can only install the sauna extension while Lilaya is awake!");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public Value<Boolean, String> getRemovalAvailability(Cell cell) {
			return new Value<>(false, "This upgrade can not be removed!");
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SPA_POOL = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_BLUE_LIGHT,
			"Swimming pool (extension)",
			"There's still a considerable amount of space in which a further extension to the spa could be built..."
				+ "<br/>If you wanted to, you could suggest to Lilaya that an indoor swimming pool would be a valuable addition to the spa.",
			"",
			"",
			300_000,
			50_000,
			100,
			0,
			0.25f,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			return LilayaSpa.SPA_POOL_INSTALLATION;
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return new Value<>(false, "The main spa extension needs to be finished before you can extend it further!");
			}
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION)!=null) {
				return new Value<>(false, "You need to wait for the sauna extension to be finished before starting this one!");
			}
			if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& (Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0) || Main.game.getNpc(Lilaya.class).isPregnant())) {
				return new Value<>(false, "Lilaya needs to resolve her pregnancy first!");
			}
			if(Lab.isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
				return new Value<>(false, "You need to confront Lilaya about your demonic form first!");
			}
			if(!Main.game.isExtendedWorkTime()) {
				return new Value<>(false, "You can only install the sauna extension while Lilaya is awake!");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public Value<Boolean, String> getRemovalAvailability(Cell cell) {
			return new Value<>(false, "This upgrade can not be removed!");
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SPA_BAR = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_ORANGE,
			"Bar",
			"",
			"",
			"",
			15_000,
			5_000,
			500,
			0,
			0.1f,
			-0.2f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return new Value<>(false, "The main spa extension needs to be finished before you can upgrade it!");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	
	private static ArrayList<AbstractPlaceUpgrade> coreRoomUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> guestRoomUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> dungeonCellUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> slaveQuartersUpgradesSingle;
	private static ArrayList<AbstractPlaceUpgrade> slaveQuartersUpgradesDouble;
	private static ArrayList<AbstractPlaceUpgrade> slaveQuartersUpgradesQuadruple;
	private static ArrayList<AbstractPlaceUpgrade> milkingRoomUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> officeUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> spaUpgrades;
	
	public static ArrayList<AbstractPlaceUpgrade> getCoreRoomUpgrades() {
		return coreRoomUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getGuestRoomUpgrades() {
		return guestRoomUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getDungeonCellUpgrades() {
		return dungeonCellUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getSlaveQuartersUpgradesSingle() {
		return slaveQuartersUpgradesSingle;
	}
	
	public static ArrayList<AbstractPlaceUpgrade> getSlaveQuartersUpgradesDouble() {
		return slaveQuartersUpgradesDouble;
	}

	public static ArrayList<AbstractPlaceUpgrade> getSlaveQuartersUpgradesQuadruple() {
		return slaveQuartersUpgradesQuadruple;
	}
	
	public static ArrayList<AbstractPlaceUpgrade> getMilkingUpgrades() {
		return milkingRoomUpgrades;
	}
	
	public static ArrayList<AbstractPlaceUpgrade> getOfficeUpgrades() {
		return officeUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getSpaUpgrades() {
		return spaUpgrades;
	}
	
	
	static {
		coreRoomUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_GUEST_ROOM,
				PlaceUpgrade.LILAYA_SPA,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE,

				PlaceUpgrade.LILAYA_OFFICE,
				PlaceUpgrade.LILAYA_MILKING_ROOM,
				
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);

		guestRoomUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_EMPTY_ROOM);

		dungeonCellUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_DUNGEON_CELL_DECENT_FOOD,
				PlaceUpgrade.LILAYA_DUNGEON_CELL_DOG_BOWLS,
				
				PlaceUpgrade.LILAYA_DUNGEON_CELL_UPGRADE_BED,
				PlaceUpgrade.LILAYA_DUNGEON_CELL_DOWNGRADE_BED,

				PlaceUpgrade.LILAYA_DUNGEON_CELL_ROPES,
				PlaceUpgrade.LILAYA_DUNGEON_CELL_CHAINS,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER);
				
		slaveQuartersUpgradesSingle = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ROOM_SERVICE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOG_BOWLS,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER,

				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE,
				PlaceUpgrade.LILAYA_EMPTY_ROOM,
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);
		
		slaveQuartersUpgradesDouble = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ROOM_SERVICE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOG_BOWLS,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER,

				PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE,
				PlaceUpgrade.LILAYA_EMPTY_ROOM,
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);
		
		slaveQuartersUpgradesQuadruple = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ROOM_SERVICE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOG_BOWLS,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER,
				
				PlaceUpgrade.LILAYA_EMPTY_ROOM,
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);
		
		milkingRoomUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS,
				PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS,

				PlaceUpgrade.LILAYA_MILKING_ROOM_MILK_EFFICIENCY,
				PlaceUpgrade.LILAYA_MILKING_ROOM_CUM_EFFICIENCY,
				PlaceUpgrade.LILAYA_MILKING_ROOM_GIRLCUM_EFFICIENCY,
				
				PlaceUpgrade.LILAYA_EMPTY_ROOM);
		
		officeUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_OFFICE_EXECUTIVE_UPGRADE,
				PlaceUpgrade.LILAYA_OFFICE_COFFEE_MACHINE,
				PlaceUpgrade.LILAYA_OFFICE_PARTITIONING_WALLS,
				
				PlaceUpgrade.LILAYA_EMPTY_ROOM);
		
		spaUpgrades = Util.newArrayListOfValues(
				//TODO
//				PlaceUpgrade.LILAYA_SPA_SAUNA,
//				PlaceUpgrade.LILAYA_SPA_POOL,
				PlaceUpgrade.LILAYA_SPA_BAR);
	}
	

	private static List<AbstractPlaceUpgrade> allPlaceUpgrades = new ArrayList<>();
	private static Map<AbstractPlaceUpgrade, String> placeUpgradeToIdMap = new HashMap<>();
	private static Map<String, AbstractPlaceUpgrade> idToPlaceUpgradeMap = new HashMap<>();

	public static List<AbstractPlaceUpgrade> getAllPlaceUpgrades() {
		return allPlaceUpgrades;
	}
	
	public static AbstractPlaceUpgrade getPlaceUpgradeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToPlaceUpgradeMap.keySet());
		return idToPlaceUpgradeMap.get(id);
	}

	public static String getIdFromPlaceUpgrade(AbstractPlaceUpgrade placeType) {
		return placeUpgradeToIdMap.get(placeType);
	}
	
	static {
		Field[] fields = PlaceUpgrade.class.getFields();
		
		for(Field f : fields) {
			if(AbstractPlaceUpgrade.class.isAssignableFrom(f.getType())) {
				AbstractPlaceUpgrade placeType;
				try {
					placeType = ((AbstractPlaceUpgrade) f.get(null));

					placeUpgradeToIdMap.put(placeType, f.getName());
					idToPlaceUpgradeMap.put(f.getName(), placeType);
					allPlaceUpgrades.add(placeType);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
