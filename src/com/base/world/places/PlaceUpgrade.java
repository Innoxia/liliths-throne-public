package com.base.world.places;

import java.util.ArrayList;
import java.util.List;

import com.base.game.character.attributes.AffectionLevel;
import com.base.game.character.attributes.ObedienceLevel;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;
import com.base.world.Cell;

/**
 * @since 0.1.85
 * @version 0.1.85
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
			-5,
			null,
			-5,
			null,
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
			null,
			0,
			null,
			null) {
		@Override
		public void applyInstallationEffects(GenericPlace place) {
			if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM_WINDOW_SLAVE) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM_WINDOW);
				
			} else if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR);
				
			} else if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM_GARDEN_SLAVE) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM_GARDEN);
			}
			
			for(PlaceUpgrade upgrade : PlaceUpgrade.values()) {
				if(upgrade != LILAYA_EMPTY_ROOM) {
					place.removePlaceUpgrade(upgrade);
				}
			}
		}
		
		@Override
		public boolean isAvailable(Cell cell) {
			return Main.game.getCharactersPresent(cell).isEmpty();
		}

		@Override
		public String getAvailabilityDescription(Cell cell) {
			if(Main.game.getCharactersPresent(cell).isEmpty()) {
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
			0.25f,
			AffectionLevel.ZERO_NEUTRAL,
			0,
			null,
			null) {
		@Override
		public void applyInstallationEffects(GenericPlace place) {
			if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM_WINDOW) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM_WINDOW_SLAVE);
				
			} else if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE);
				
			} else if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM_GARDEN) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM_GARDEN_SLAVE);
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
			-0.25f,
			AffectionLevel.NEGATIVE_ONE_ANNOYED,
			0.1f,
			ObedienceLevel.ZERO_FREE_WILLED,
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
			0.25f,
			AffectionLevel.POSITIVE_ONE_FRIENDLY,
			-0.1f,
			ObedienceLevel.ZERO_FREE_WILLED,
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
			-0.25f,
			AffectionLevel.NEGATIVE_ONE_ANNOYED,
			0f,
			null,
			null);
	
	
	public static ArrayList<PlaceUpgrade> coreRoomUpgrades, slaveQuartersUpgrades;
	
	static {
		coreRoomUpgrades = Util.newArrayListOfValues(
				new ListValue<>(PlaceUpgrade.LILAYA_SLAVE_ROOM));
		
		slaveQuartersUpgrades = Util.newArrayListOfValues(
				new ListValue<>(PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED),
				new ListValue<>(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED),
				new ListValue<>(PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS),
				new ListValue<>(PlaceUpgrade.LILAYA_EMPTY_ROOM));
	}
	
	
	private boolean isCoreRoomUpgrade;
	private String name, descriptionForPurchase, descriptionAfterPurchase, roomDescription;
	private int installCost, removalCost, upkeep, capacity;
	private Colour colour;
	private float affectionGain, obedienceGain;
	private AffectionLevel affectionCap;
	private ObedienceLevel obedienceCap;
	private List<PlaceUpgrade> prerequisites;

	private PlaceUpgrade(boolean isCoreRoomUpgrade, Colour colour, String name, String descriptionForPurchase, String descriptionAfterPurchase, String roomDescription,
			int installCost, int removalCost, int upkeep, int capacity,
			float affectionGain, AffectionLevel affectionCap,
			float obedienceGain, ObedienceLevel obedienceCap,
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
		this.affectionCap = affectionCap;
		
		this.obedienceGain = obedienceGain;
		this.obedienceCap = obedienceCap;
		
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

	public String getRoomDescription() {
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

	public float getAffectionGain() {
		return affectionGain;
	}

	public float getObedienceGain() {
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

	public AffectionLevel getAffectionCap() {
		return affectionCap;
	}

	public ObedienceLevel getObedienceCap() {
		return obedienceCap;
	}
}
