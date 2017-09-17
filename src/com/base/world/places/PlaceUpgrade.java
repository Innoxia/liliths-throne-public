package com.base.world.places;

import java.util.ArrayList;
import java.util.List;

import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.85
 * @version 0.1.85
 * @author Innoxia
 */
public enum PlaceUpgrade {
	
	BASIC_SLAVE_QUARTERS(Colour.BASE_CRIMSON,
			"Basic Slave Quarters",
			"Rose will prepare this room for housing one of your slaves.",
			"This room has been converted into a suitable place for housing one of your slaves.",
			500,
			100,
			10,
			0,
			0,
			null) {
		@Override
		public void applyInstallationEffects(GenericPlace place) {
			if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM_SLAVE);
				
			} else if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM_WINDOW) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM_WINDOW_SLAVE);
				
			} else if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE);
				
			} else if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM_GARDEN) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM_GARDEN_SLAVE);
			}
		}
		
		@Override
		public void applyRemovalEffects(GenericPlace place) {
			if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM_SLAVE) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM);
				
			} else if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM_WINDOW_SLAVE) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM_WINDOW);
				
			} else if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR);
				
			} else if(place.getPlaceType() == LilayasHome.LILAYA_HOME_ROOM_GARDEN_SLAVE) {
				place.setPlaceType(LilayasHome.LILAYA_HOME_ROOM_GARDEN);
			}
			
			for(PlaceUpgrade upgrade : PlaceUpgrade.values()) {
				place.removePlaceUpgrade(upgrade);
			}
		}
	},
	
	SLAVE_QUARTERS_ARCANE_INSTRUMENTS(Colour.GENERIC_ARCANE,
			"Arcane Instruments",
			"Allow Lilaya to install arcane sensors in this room, so that she can gather useful data on your slave's aura."
					+ " Your slaves will find this to be an intrusion on what little personal space they have, and their affection towards you will suffer as a result.",
			"A series of arcane sensors have been set up around this room, allowing Lilaya to gather useful data on your slave's aura."
					+ " Your slaves find this to be an intrusion on what little personal space they have, and their affection towards you will suffer as a result.",
			50,
			50,
			-5,
			-0.25f,
			0f,
			Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.BASIC_SLAVE_QUARTERS))) {
				@Override
				public void applyInstallationEffects(GenericPlace place) {
				}
				
				@Override
				public void applyRemovalEffects(GenericPlace place) {
				}
			};
	
	
	public static ArrayList<PlaceUpgrade> coreRoomUpgrades, slaveQuartersUpgrades;
	
	static {
		coreRoomUpgrades = Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.BASIC_SLAVE_QUARTERS));
		slaveQuartersUpgrades = Util.newArrayListOfValues(new ListValue<>(PlaceUpgrade.SLAVE_QUARTERS_ARCANE_INSTRUMENTS));
	}
	
	
	private String name, descriptionForPurchase, descriptionAfterPurchase;
	private int installCost, removalCost, upkeep;
	private Colour colour;
	private float affectionGain, obedienceGain;
	private List<PlaceUpgrade> prerequisites;
	
	private PlaceUpgrade(Colour colour, String name, String descriptionForPurchase, String descriptionAfterPurchase, int installCost, int removalCost, int upkeep, float affectionGain, float obedienceGain, List<PlaceUpgrade> prerequisites) {
		this.colour = colour;
		this.name = name;
		this.descriptionForPurchase = descriptionForPurchase;
		this.descriptionAfterPurchase = descriptionAfterPurchase;
		this.installCost = installCost;
		this.removalCost = removalCost;
		this.upkeep = upkeep;
		this.affectionGain = affectionGain;
		this.obedienceGain = obedienceGain;
		
		if(prerequisites==null) {
			this.prerequisites = new ArrayList<>();
			
		} else {
			this.prerequisites = prerequisites;
		}
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
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
	
	public abstract void applyInstallationEffects(GenericPlace place);

	public abstract void applyRemovalEffects(GenericPlace place);
}
