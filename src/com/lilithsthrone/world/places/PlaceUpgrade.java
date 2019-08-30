package com.lilithsthrone.world.places;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Cell;

/**
 * @since 0.1.85
 * @version 0.2.11
 * @author Innoxia
 */
public enum PlaceUpgrade {
	
	SLAVERY_ADMINISTRATION_CELLS(true,
			Colour.GENERIC_ARCANE,
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
			null),
	
	LILAYA_EMPTY_ROOM(true,
			Colour.GENERIC_ARCANE,
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
			for(PlaceUpgrade upgrade : PlaceUpgrade.values()) {
				if(upgrade != LILAYA_EMPTY_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
		
		@Override
		protected boolean isExtraConditionsMet(Cell cell) {
			return Main.game.getCharactersTreatingCellAsHome(cell).isEmpty() && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM);
		}

		@Override
		protected String getExtraConditionalAvailabilityDescription(Cell cell) {
			if(Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return "";
			} else {
				return "This room needs to be unoccupied in order to purchase this modification.";
			}
		}
	},
	
	LILAYA_ARTHUR_ROOM(true,
			Colour.RACE_HUMAN,
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
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			place.setPlaceType(PlaceType.LILAYA_HOME_ARTHUR_ROOM);
			
			for(PlaceUpgrade upgrade : PlaceUpgrade.values()) {
				if(upgrade != LILAYA_ARTHUR_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
		
		@Override
		protected boolean isExtraConditionsMet(Cell cell) {
			return Main.game.getCharactersTreatingCellAsHome(cell).isEmpty();
		}

		@Override
		protected String getExtraConditionalAvailabilityDescription(Cell cell) {
			if(Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return "";
			} else {
				return "This room needs to be unoccupied in order to purchase this modification.";
			}
		}
	},
	
	LILAYA_GUEST_ROOM(true,
			Colour.GENERIC_ARCANE,
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
		public boolean isSlaverUpgrade() {
			return false;
		}
		
		@Override
		protected boolean isExtraConditionsMet(Cell cell) {
			return Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)
					&& Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()
					&& !cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM);
		}
		
		@Override
		protected String getExtraConditionalAvailabilityDescription(Cell cell) {
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
				return "To install a guest bedroom, you'd need to first find someone to invite to live with you, and then get Lilaya's permission to make the upgrade!";
			} else {
				return super.getExtraConditionalAvailabilityDescription(cell);
			}
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(PlaceUpgrade upgrade : PlaceUpgrade.values()) {
				if(upgrade != LILAYA_GUEST_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	},
	
	LILAYA_SLAVE_ROOM(true,
			Colour.GENERIC_ARCANE,
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
		protected boolean isExtraConditionsMet(Cell cell) {
			return Main.game.getCharactersTreatingCellAsHome(cell).isEmpty() && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(PlaceUpgrade upgrade : PlaceUpgrade.values()) {
				if(upgrade != LILAYA_SLAVE_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	},
	
	LILAYA_MILKING_ROOM(true,
			Colour.BASE_ORANGE,
			"Milking Room",
			"Install milking machines in this room, allowing eight slaves to be assigned to work in here, each of which will be milked of their milk and cum.<br/>"
					+ "<i>Milk: "+Units.fluid(MilkingRoom.BASE_MILKING_AMOUNT)+" per hour<br/>"
					+ "Cum: "+Units.fluid(MilkingRoom.BASE_CUM_MILKING_AMOUNT)+" per hour<br/>"
					+ "Girlcum: "+Units.fluid(MilkingRoom.BASE_GIRLCUM_MILKING_AMOUNT)+" per hour</i>",
			"This room has been converted into a suitable place for milking eight of your slaves' milk and cum.",
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
		public String getRoomDescription(Cell c) {
			MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(c.getType(), c.getLocation());
			
			return room.getRoomDescription();
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(PlaceUpgrade upgrade : PlaceUpgrade.values()) {
				if(upgrade != LILAYA_MILKING_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
			if(Main.game.getOccupancyUtil().getMilkingRoom(c.getType(), c.getLocation())==null) {
				Main.game.getOccupancyUtil().addMilkingRoom(new MilkingRoom(c.getType(), c.getLocation()));
			}
		}
		
		@Override
		protected boolean isExtraConditionsMet(Cell cell) {
			return Main.game.getCharactersTreatingCellAsHome(cell).isEmpty() && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM);
		}

		@Override
		protected String getExtraConditionalAvailabilityDescription(Cell cell) {
			if(Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return "";
			} else {
				return "This room needs to be unoccupied in order to purchase this modification.";
			}
		}
	},
	
	LILAYA_SLAVE_ROOM_DOUBLE(true,
			Colour.BASE_MAGENTA,
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
		protected boolean isExtraConditionsMet(Cell cell) {
			return (Main.game.getCharactersTreatingCellAsHome(cell).isEmpty() && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM))
						|| cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)) {
				place.removePlaceUpgrade(c, LILAYA_SLAVE_ROOM);
				
			} else {
				for(PlaceUpgrade upgrade : PlaceUpgrade.values()) {
					if(upgrade != LILAYA_SLAVE_ROOM_DOUBLE) {
						place.removePlaceUpgrade(c, upgrade);
					}
				}
			}
		}
	},
	
	LILAYA_SLAVE_ROOM_QUADRUPLE(true,
			Colour.BASE_MAGENTA,
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
		protected boolean isExtraConditionsMet(Cell cell) {
			return (Main.game.getCharactersTreatingCellAsHome(cell).isEmpty() && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM))
						|| cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)
						|| cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)) {
				place.removePlaceUpgrade(c, LILAYA_SLAVE_ROOM);
				
			} else if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				place.removePlaceUpgrade(c, LILAYA_SLAVE_ROOM_DOUBLE);
				
			} else {
				for(PlaceUpgrade upgrade : PlaceUpgrade.values()) {
					if(upgrade != LILAYA_SLAVE_ROOM_QUADRUPLE) {
						place.removePlaceUpgrade(c, upgrade);
					}
				}
			}
		}
	},
	
	LILAYA_SLAVE_ROOM_DOWNGRADE_BED(false,
			Colour.GENERIC_BAD,
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
		protected boolean isExtraConditionsMet(Cell cell) {
			return !cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_UPGRADE_BED);
		}

		@Override
		protected String getExtraConditionalAvailabilityDescription(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return "You'll need to remove the Double Size Bed before installing this.";
			}
			return "";
		}
	},
	
	LILAYA_SLAVE_ROOM_UPGRADE_BED(false,
			Colour.GENERIC_GOOD,
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
			
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				return "This room's pair of single-sized beds have been replaced with a solitary double-sized one, complete with a comfortable mattress, fluffy pillows, and a warm duvet."
						+ " The pair of slaves assigned to be this room's occupants will have to learn to live with the fact that they now sleep in the same bed...";
			} else {
				return "A double size bed, complete with a comfortable mattress, fluffy pillows, and a warm duvet, sits against one side of the room."
						+ " Providing this room's occupant with such a delightful place to sleep will definitely get them to like you more, although such luxury might make them forget their place...";
			}
		}
		
		@Override
		protected boolean isExtraConditionsMet(Cell cell) {
			return !cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOWNGRADE_BED);
		}

		@Override
		protected String getExtraConditionalAvailabilityDescription(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOWNGRADE_BED)) {
				return "You'll need to remove the Small Steel Bed before installing this.";
			}
			return "";
		}
	},
	
	LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER(false,
			Colour.GENERIC_ARCANE,
			"Obedience Trainer",
			"Lilaya has asked you if you'd consider installing one of her experimental devices in this room; a so-called 'obedience trainer'."
					+ " This particular addition takes the form of a large, glowing crystal that is to be placed in the centre of the room."
					+ " Whenever the room's occupant thinks a disobedient thought, the crystal will shoot out a shocking bolt of arcane energy, thereby training a slave's obedience in the most intrusive fashion imaginable.",
			"You've installed one one of Lilaya's experimental devices in this room; a so-called 'obedience trainer'."
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
			null),
	
	LILAYA_SLAVE_ROOM_ROOM_SERVICE(false,
			Colour.GENERIC_ARCANE,
			"Room service",
			"You could offer this room's occupant unlimited room service."
					+ " This isn't exactly how most owners treat their slaves, and while it's sure to make the occupant like you more, it's also going to cost quite a bit in upkeep, as well as have some negative effects on their obedience...",
			"You've offered unlimited room service to the occupant of this room."
					+ " It's definitely making them like you more, but it's also costing a fair amount in upkeep, and is having a negative effect on your slave's obedience...",
			"A little push-trolley with a few empty silver plates and glasses stacked on top of it is evidence that the slave who lives here is taking full advantage of the unlimited room service you've offered to them."
					+ " It's definitely making them like you more, but having such a luxury available to them is also having a negative impact on their obedience, not to mention the damage it's doing to your bank account...",
			100,
			0,
			250,
			0,
			0.4f,
			-0.2f,
			null),
	
	LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS(false,
			Colour.GENERIC_ARCANE,
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
			null),
	
	LILAYA_MILKING_ROOM_ARTISAN_MILKERS(false,
			Colour.GENERIC_ARCANE,
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
			250,
			0,
			1f,
			0.5f,
			null) {
		
		@Override
		protected boolean isExtraConditionsMet(Cell cell) {
			return !cell.getPlace().getPlaceUpgrades().contains(LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS);
		}

		@Override
		protected String getExtraConditionalAvailabilityDescription(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)) {
				return "You'll need to remove the Industrial Milkers before installing this.";
			}
			return "";
		}
	},
	
	LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS(false,
			Colour.GENERIC_ARCANE,
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
					+ " Although they're sure to maximise milk output, and profits, these machines aren't exactly the most comfortable of devices to be strapped in to, and any slaves assigned to me milked in here are sure to hate you for it...",
			1500,
			500,
			100,
			0,
			-1f,
			0.5f,
			null) {
		
		@Override
		protected boolean isExtraConditionsMet(Cell cell) {
			return !cell.getPlace().getPlaceUpgrades().contains(LILAYA_MILKING_ROOM_ARTISAN_MILKERS);
		}

		@Override
		protected String getExtraConditionalAvailabilityDescription(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_MILKING_ROOM_ARTISAN_MILKERS)) {
				return "You'll need to remove the Artisan Milkers before installing this.";
			}
			return "";
		}
	},
	
	LILAYA_MILKING_ROOM_MILK_EFFICIENCY(false,
			Colour.MILK,
			"Lact-o-Cups",
			"The company that makes the milking machines also offers a selection of aftermarket upgrades. One of these is 'Lact-o-Cups', which promises to double the machine's maximum hourly milking efficiency.",
			"You've added the optional 'Lact-o-Cups' to the milking machines in this room, doubling their maximum hourly milking efficiency.",
			"The standard suction cups on each machine have been replaced with aftermarket 'Lact-o-Cups', which are doubling the maximum amount of milk extracted per hour.",
			500,
			100,
			10,
			0,
			0,
			0,
			null),
	
	LILAYA_MILKING_ROOM_CUM_EFFICIENCY(false,
			Colour.CUM,
			"Succ-u-Buses",
			"The company that makes the milking machines also offers a selection of aftermarket upgrades. One of these is 'Succ-u-Buses', which promises to double the machine's maximum hourly cum-milking efficiency.",
			"You've added the optional 'Succ-u-Buses' to the milking machines in this room, doubling their maximum hourly cum-milking efficiency.",
			"The standard cock-milking tubes on each machine have been replaced with aftermarket 'Succ-u-Buses', which are doubling the maximum amount of cum extracted per hour.",
			500,
			100,
			10,
			0,
			0,
			0,
			null),

	LILAYA_MILKING_ROOM_GIRLCUM_EFFICIENCY(false,
			Colour.GIRLCUM,
			"Vibro-Pumps",
			"The company that makes the milking machines also offers a selection of aftermarket upgrades. One of these is 'Vibro-Pumps', which promises to double the machine's maximum hourly girlcum-milking efficiency.",
			"You've added the optional 'Vibro-Pumps' to the milking machines in this room, doubling their maximum hourly girlcum-milking efficiency.",
			"The standard vaginal pumps on each machine have been replaced with aftermarket 'Vibro-Pumps', which are doubling the maximum amount of girlcum extracted per hour.",
			500,
			100,
			10,
			0,
			0,
			0,
			null),
	
	;
	
	
	private static ArrayList<PlaceUpgrade> coreRoomUpgrades;
	private static ArrayList<PlaceUpgrade> guestRoomUpgrades;
	private static ArrayList<PlaceUpgrade> slaveQuartersUpgradesSingle;
	private static ArrayList<PlaceUpgrade> slaveQuartersUpgradesDouble;
	private static ArrayList<PlaceUpgrade> slaveQuartersUpgradesQuadruple;
	private static ArrayList<PlaceUpgrade> milkingRoomUpgrades;
	
	public static ArrayList<PlaceUpgrade> getCoreRoomUpgrades() {
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursRoomInstalled) || Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_J_ARTHURS_ROOM)) {
			ArrayList<PlaceUpgrade> listArthurRemoved = new ArrayList<>(coreRoomUpgrades);
			listArthurRemoved.remove(PlaceUpgrade.LILAYA_ARTHUR_ROOM);
			return listArthurRemoved;
		}
		return coreRoomUpgrades;
	}

	public static ArrayList<PlaceUpgrade> getGuestRoomUpgrades() {
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursRoomInstalled) || Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_J_ARTHURS_ROOM)) {
			ArrayList<PlaceUpgrade> listArthurRemoved = new ArrayList<>(guestRoomUpgrades);
			listArthurRemoved.remove(PlaceUpgrade.LILAYA_ARTHUR_ROOM);
			return listArthurRemoved;
		}
		return guestRoomUpgrades;
	}

	public static ArrayList<PlaceUpgrade> getSlaveQuartersUpgradesSingle() {
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursRoomInstalled) || Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_J_ARTHURS_ROOM)) {
			ArrayList<PlaceUpgrade> listArthurRemoved = new ArrayList<>(slaveQuartersUpgradesSingle);
			listArthurRemoved.remove(PlaceUpgrade.LILAYA_ARTHUR_ROOM);
			return listArthurRemoved;
		}
		return slaveQuartersUpgradesSingle;
	}
	
	public static ArrayList<PlaceUpgrade> getSlaveQuartersUpgradesDouble() {
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursRoomInstalled) || Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_J_ARTHURS_ROOM)) {
			ArrayList<PlaceUpgrade> listArthurRemoved = new ArrayList<>(slaveQuartersUpgradesDouble);
			listArthurRemoved.remove(PlaceUpgrade.LILAYA_ARTHUR_ROOM);
			return listArthurRemoved;
		}
		return slaveQuartersUpgradesDouble;
	}

	public static ArrayList<PlaceUpgrade> getSlaveQuartersUpgradesQuadruple() {
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursRoomInstalled) || Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_J_ARTHURS_ROOM)) {
			ArrayList<PlaceUpgrade> listArthurRemoved = new ArrayList<>(slaveQuartersUpgradesQuadruple);
			listArthurRemoved.remove(PlaceUpgrade.LILAYA_ARTHUR_ROOM);
			return listArthurRemoved;
		}
		return slaveQuartersUpgradesQuadruple;
	}
	
	
	public static ArrayList<PlaceUpgrade> getMilkingUpgrades() {
		return milkingRoomUpgrades;
	}

	static {
		coreRoomUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_GUEST_ROOM,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE,
				
				PlaceUpgrade.LILAYA_MILKING_ROOM,
				
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);

		guestRoomUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_EMPTY_ROOM);
				
				
		slaveQuartersUpgradesSingle = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ROOM_SERVICE,
				
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
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER,

				PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE,
				PlaceUpgrade.LILAYA_EMPTY_ROOM,
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);
		
		slaveQuartersUpgradesQuadruple = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ROOM_SERVICE,
				
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
	}
	
	
	private boolean isCoreRoomUpgrade;
	
	protected String name;
	protected String descriptionForPurchase;
	protected String descriptionAfterPurchase;
	protected String roomDescription;
	
	private int installCost;
	private int removalCost;
	private int upkeep;
	private int capacity;
	
	private Colour colour;
	
	private float affectionGain;
	private float obedienceGain;
	
	private List<PlaceUpgrade> prerequisites;
	
	private PlaceUpgrade(boolean isCoreRoomUpgrade,
			Colour colour,
			String name,
			String descriptionForPurchase,
			String descriptionAfterPurchase,
			String roomDescription,
			int installCost,
			int removalCost,
			int upkeep,
			int capacity,
			float affectionGain,
			float obedienceGain,
			List<PlaceUpgrade> prerequisites) {
		
		this.isCoreRoomUpgrade = isCoreRoomUpgrade;
		this.colour = colour;
		this.name = name;
		this.descriptionForPurchase = descriptionForPurchase;
		this.descriptionAfterPurchase = descriptionAfterPurchase;
		this.roomDescription = roomDescription;
		
		this.installCost = installCost;
		this.removalCost = removalCost;
		this.upkeep = upkeep;
		this.capacity = capacity;
		
		this.affectionGain = affectionGain;
		
		this.obedienceGain = obedienceGain;
		
		if(prerequisites==null) {
			this.prerequisites = new ArrayList<>();
			
		} else {
			this.prerequisites = prerequisites;
		}
	}
	
	protected boolean isExtraConditionsMet(Cell cell) {
		return true;
	}

	protected String getExtraConditionalAvailabilityDescription(Cell cell) {
		return "";
	}
	
	public boolean isAvailable(Cell cell) {
		return (Main.game.getPlayer().isHasSlaverLicense() || (Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION) && !this.isSlaverUpgrade()))
				&& isExtraConditionsMet(cell);
	}
	
	public String getAvailabilityDescription(Cell cell) {
		if(!(Main.game.getPlayer().isHasSlaverLicense() || (Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION) && !this.isSlaverUpgrade()))) {
			return "You are unable to purchase this upgrade without a slaver license!";
		}
		
		return getExtraConditionalAvailabilityDescription(cell);
	}
	
	public boolean isSlaverUpgrade() {
		return true;
	}
	
	public boolean isCoreRoomUpgrade() {
		return isCoreRoomUpgrade;
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}

	public String getRoomDescription(Cell c) {
		return roomDescription;
	}

	public String getDescriptionForPurchase() {
		return descriptionForPurchase;
	}

	public String getDescriptionAfterPurchase() {
		return descriptionAfterPurchase;
	}

	public int getInstallCost() {
		return installCost;
	}

	public int getRemovalCost() {
		return removalCost;
	}

	public int getUpkeep() {
		return upkeep;
	}

	public int getCapacity() {
		return capacity;
	}

	public float getHourlyAffectionGain() {
		return affectionGain;
	}

	public float getHourlyObedienceGain() {
		return obedienceGain;
	}

	public List<PlaceUpgrade> getPrerequisites() {
		return prerequisites;
	}
	
	public boolean isPrerequisitesMet(GenericPlace place) {
		return place.getPlaceUpgrades().containsAll(prerequisites);
	}
	
	public void applyInstallationEffects(Cell c) {
	}

	public void applyRemovalEffects(Cell c) {
	}
}
