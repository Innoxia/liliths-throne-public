package com.lilithsthrone.world.places;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.world.Cell;

/**
 * @since 0.1.85
 * @version 0.1.87
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
			200,
			0,
			0,
			0,
			0,
			0,
			null) {
		@Override
		public void applyInstallationEffects(GenericPlace place) {
			if(place.getPlaceType() == PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_SLAVE) {
				place.setPlaceType(PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR);
				
			} else if(place.getPlaceType() == PlaceType.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE) {
				place.setPlaceType(PlaceType.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR);
				
			} else if(place.getPlaceType() == PlaceType.LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_SLAVE) {
				place.setPlaceType(PlaceType.LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR);
				
			} else if(place.getPlaceType() == PlaceType.LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_SLAVE) {
				place.setPlaceType(PlaceType.LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR);
			}
			
			for(PlaceUpgrade upgrade : PlaceUpgrade.values()) {
				if(upgrade != LILAYA_EMPTY_ROOM) {
					place.removePlaceUpgrade(upgrade);
				}
			}
		}
		
		@Override
		public boolean isAvailable(Cell cell) {
			return Main.game.getCharactersTreatingCellAsHome(cell).isEmpty();
		}

		@Override
		public String getAvailabilityDescription(Cell cell) {
			if(Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return "";
			} else {
				return "This room needs to be unoccupied in order to purchase this modification.";
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
			200,
			0,
			10,
			1,
			0.1f,
			0,
			null) {
		
		@Override
		public String getRoomDescription(GenericPlace place) {
			if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return "You've paid to have this room converted into basic slave's quarters."
						+ " A comfortable double size bed, covered in a warm fully duvet, sits against one wall."
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
		public void applyInstallationEffects(GenericPlace place) {
			if(place.getPlaceType() == PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR) {
				place.setPlaceType(PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_SLAVE);
				
			} else if(place.getPlaceType() == PlaceType.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR) {
				place.setPlaceType(PlaceType.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE);
				
			} else if(place.getPlaceType() == PlaceType.LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR) {
				place.setPlaceType(PlaceType.LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_SLAVE);
				
			} else if(place.getPlaceType() == PlaceType.LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR) {
				place.setPlaceType(PlaceType.LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_SLAVE);
			}
			
			for(PlaceUpgrade upgrade : PlaceUpgrade.values()) {
				if(upgrade != LILAYA_SLAVE_ROOM) {
					place.removePlaceUpgrade(upgrade);
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
			25,
			25,
			-1,
			0,
			-0.1f,
			0.2f,
			null) {
		
		@Override
		public boolean isAvailable(Cell cell) {
			return !cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_UPGRADE_BED);
		}

		@Override
		public String getAvailabilityDescription(Cell cell) {
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
			200,
			50,
			5,
			0,
			0.2f,
			-0.1f,
			null) {
		
		@Override
		public boolean isAvailable(Cell cell) {
			return !cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOWNGRADE_BED);
		}

		@Override
		public String getAvailabilityDescription(Cell cell) {
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
			1000,
			500,
			5,
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
			"An little push-trolley with a few empty silver plates and glasses stacked on top of it is evidence that the slave who lives here is taking full advantage of the unlimited room service you've offered to them."
					+ " It's definitely making them like you more, but having such a luxury available to them is also having a negative impact on their obedience, not to mention the damage it's doing to your bank account...",
			20,
			0,
			50,
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
			50,
			50,
			-5,
			0,
			-0.1f,
			0f,
			null);
	
	
	public static ArrayList<PlaceUpgrade> coreRoomUpgrades, slaveQuartersUpgrades;
	
	static {
		coreRoomUpgrades = Util.newArrayListOfValues(
				new ListValue<>(PlaceUpgrade.LILAYA_SLAVE_ROOM));
		
		slaveQuartersUpgrades = Util.newArrayListOfValues(
				new ListValue<>(PlaceUpgrade.LILAYA_SLAVE_ROOM_ROOM_SERVICE),
				
				new ListValue<>(PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED),
				new ListValue<>(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED),
				
				new ListValue<>(PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS),
				new ListValue<>(PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER),
				
				new ListValue<>(PlaceUpgrade.LILAYA_EMPTY_ROOM));
	}
	
	
	private boolean isCoreRoomUpgrade;
	private String name, descriptionForPurchase, descriptionAfterPurchase, roomDescription;
	private int installCost, removalCost, upkeep, capacity;
	private Colour colour;
	private float affectionGain, obedienceGain;
	private List<PlaceUpgrade> prerequisites;

	private PlaceUpgrade(boolean isCoreRoomUpgrade, Colour colour, String name, String descriptionForPurchase, String descriptionAfterPurchase, String roomDescription,
			int installCost, int removalCost, int upkeep, int capacity,
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
	
	public boolean isAvailable(Cell cell) {
		return true;
	}
	
	public String getAvailabilityDescription(Cell cell) {
		return "";
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

	public String getRoomDescription(GenericPlace place) {
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
	
	public void applyInstallationEffects(GenericPlace place) {
	}

	public void applyRemovalEffects(GenericPlace place) {
	}
}
