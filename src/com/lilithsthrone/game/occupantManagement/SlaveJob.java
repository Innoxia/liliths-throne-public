package com.lilithsthrone.game.occupantManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.87
 * @version 0.3.5
 * @author Innoxia
 */
public enum SlaveJob {
	
	/*
		restaurant
			w&m
		bar
			sleazy (obedience not counted, but income is less)
			allow groping
	 */
	
	IDLE(PresetColour.BASE_GREY_DARK,
			0.05f,
			-1,
			-1f,
			"Idle",
			"Idle",
			"Do not assign any job to this slave.",
			0, 0,
			0,
			0, 0,
			null,
			null,
			null,
			null, null) {
		@Override
		public boolean isAvailable(int hour, GameCharacter character) {
			return true;
		}
	},
	
	CLEANING(PresetColour.BASE_BLUE_LIGHT,
			0.05f,
			20,
			2f,
			"maid",
			"manservant",
			"Assign this slave to help Rose keep the house clean, deal with visitors, and perform all sorts of menial housework.",
			0, 0.5f,
			80,
			0f, 0.1f,
			null,
			null,
			null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR) {
		@Override
		public EventLogEntry getHourlyEvent(long hour, NPC slave, List<NPC> otherNPCsPresent) {
			return new EventLogEntry(Main.game.getDayNumber()-1*24l+hour, "[style.colourDisabled(Nothing)]", "");
		}
		
		@Override
		public void sendToWorkLocation(int hour, GameCharacter slave) {
			if(slave.getLocationPlace().getPlaceType().equals(PlaceType.LILAYA_HOME_CORRIDOR)) {
				slave.moveToAdjacentMatchingCellType(false);
			
			} else {
				// 50/50 of being upstairs or downstairs:
				AbstractWorldType worldTypeToUse = WorldType.LILAYAS_HOUSE_FIRST_FLOOR;
				if(Math.random()>0.5f) {
					worldTypeToUse = WorldType.LILAYAS_HOUSE_GROUND_FLOOR;
				}
				
				slave.setRandomLocation(worldTypeToUse, PlaceType.LILAYA_HOME_CORRIDOR, false);
			}
		}
	},
	
	LIBRARY(PresetColour.BASE_TEAL,
			0.05f,
			5,
			1.5f,
			"librarian",
			"librarian",
			"Assign this slave to work in Lilaya's library.",
			0, 0.25f, 
			80,
			0, 0.1f,
			null,
			null,
			null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LIBRARY),
	
	KITCHEN(PresetColour.BASE_TAN,
			0.05f,
			5,
			2,
			"cook",
			"cook",
			"Assign this slave to work in Lilaya's kitchen as a cook.",
			0, 0.25f,
			80,
			0, 0.05f,
			null,
			null,
			null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_KITCHEN),
	
	LAB_ASSISTANT(PresetColour.BASE_GREEN_LIME,
			0.05f,
			1,
			1f,
			"lab assistant",
			"lab assistant",
			"Assign this slave to help Lilaya in her lab.",
			0, 0.25f,
			100,
			0, 0.2f,
			null,
			null,
			null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB) {
		@Override
		public boolean isAvailable(int hour, GameCharacter character) {
			if(hour<6 || hour>=22) {
				return false;
			}
			return super.isAvailable(hour, character);
		}
		@Override
		public String getAvailabilityText(int hour, GameCharacter character) {
			if(hour<6 || hour>=22) {
				return "Slaves cannot work in Lilaya's lab while she is sleeping!";
			}
			return super.getAvailabilityText(hour, character);
		}
	},

	TEST_SUBJECT(PresetColour.BASE_RED_LIGHT,
			0.5f,
			5,
			3f,
			"test subject",
			"test subject",
			"Allow Lilaya to use this slave as a test subject for her experiments.",
			-0.5f, 0.5f,
			150,
			0, 0,
			Util.newArrayListOfValues(
					SlaveJobSetting.TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE,
					SlaveJobSetting.TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE),
			null,
			null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB) {
		@Override
		public float getAffectionGain(int hour, GameCharacter slave) {
			if(slave.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
				return 0.5f;
			} else {
				return -0.5f;
			}
		}
		@Override
		public boolean isAvailable(int hour, GameCharacter character) {
			if(hour<6 || hour>=22) {
				return false;
			}
			return super.isAvailable(hour, character);
		}
		@Override
		public String getAvailabilityText(int hour, GameCharacter character) {
			if(hour<6 || hour>=22) {
				return "Slaves cannot work in Lilaya's lab while she is sleeping!";
			}
			return super.getAvailabilityText(hour, character);
		}
	},
	
	PUBLIC_STOCKS(PresetColour.BASE_PINK_LIGHT,
			0.5f,
			5,
			2f,
			"public use",
			"public use",
			"Assign this slave to be locked in the public-use stocks in slaver ally.",
			-5f, 1f,
			0,
			0, 0,
			Util.newArrayListOfValues(
					SlaveJobSetting.SEX_ORAL,
					SlaveJobSetting.SEX_VAGINAL,
					SlaveJobSetting.SEX_ANAL,
					SlaveJobSetting.SEX_NIPPLES),
			null,
			null,
			WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS) {
		@Override
		public float getAffectionGain(int hour, GameCharacter slave) {
			if(slave.hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return 1f;
			} else {
				return -5f;
			}
		}
	},
	
	PROSTITUTE(PresetColour.BASE_PINK_DEEP,
			0.5f,
			10,
			2.5f,
			"Prostitute",
			"Prostitute",
			"Assign this slave to work as a prostitute at the brothel 'Angel's Kiss'.",
			-0.25f, 0.5f,
			200,
			0, 0.5f,
			Util.newArrayListOfValues(
					SlaveJobSetting.SEX_ORAL,
					SlaveJobSetting.SEX_VAGINAL,
					SlaveJobSetting.SEX_ANAL,
					SlaveJobSetting.SEX_NIPPLES),
			null,
			null,
			WorldType.ANGELS_KISS_FIRST_FLOOR, PlaceType.ANGELS_KISS_BEDROOM) {
		
		@Override
		public float getAffectionGain(int hour, GameCharacter slave) {
			if(slave.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
				return 1f;
			} else {
				return -0.25f;
			}
		}
		
		@Override
		public boolean isAvailable(int hour, GameCharacter character) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
				return false;
			}
			return super.isAvailable(hour, character);
		}

		@Override
		public String getAvailabilityText(int hour, GameCharacter character) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
				return "You do not have permission from Angel to send your slaves to work in her brothel!";
				
			} else if(character.getOwner().getSlavesWorkingJob(hour, this)>=this.getSlaveLimit()) {
				return "You have already assigned the maximum number of slaves to this job!";
				
			} else if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)) {
				return "Slaves cannot work out of the cells at slavery administration. Move them into a room first!";
				
			} else {
				return "This job is available!";
			}
		}
	},
	
	MILKING(PresetColour.BASE_YELLOW_LIGHT,
			1f,
			-1,
			2f,
			"Dairy Cow",
			"Dairy Bull",
			"Assign this slave to the cow stalls, ready for milking or breeding (or perhaps both). Income is based off of the assigned slave's milk, cum, and girlcum production.",
			-0.25f, 1f,
			0,
			0, 0,
			Util.newArrayListOfValues(
					SlaveJobSetting.MILKING_MILK_DISABLE,
					SlaveJobSetting.MILKING_MILK_CROTCH_DISABLE,
					SlaveJobSetting.MILKING_CUM_DISABLE,
					SlaveJobSetting.MILKING_GIRLCUM_DISABLE,
					SlaveJobSetting.MILKING_MILK_AUTO_SELL,
					SlaveJobSetting.MILKING_MILK_CROTCH_AUTO_SELL,
					SlaveJobSetting.MILKING_CUM_AUTO_SELL,
					SlaveJobSetting.MILKING_GIRLCUM_AUTO_SELL),
			Util.newHashMapOfValues(
					new Value<>("Room Preference", Util.newArrayListOfValues(
							SlaveJobSetting.MILKING_INDUSTRIAL,
							SlaveJobSetting.MILKING_REGULAR,
							SlaveJobSetting.MILKING_ARTISAN,
							SlaveJobSetting.MILKING_NO_PREFERENCE))),
			Util.newArrayListOfValues(
					SlaveJobSetting.MILKING_NO_PREFERENCE),
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR) {

		@Override
		public int getSlaveLimit() {
			return Main.game.getOccupancyUtil().getMilkingRooms().size()*8;
		}
		
		@Override
		public float getAffectionGain(int hour, GameCharacter slave) {
			float aff = this.affectionGain;
			if(slave.hasFetish(Fetish.FETISH_LACTATION_SELF)) {
				aff = 2f;
			}
			Cell c = this.getWorkDestinationCell(hour, slave);
			return aff + (c==null?0:c.getPlace().getHourlyAffectionChange());
		}
		
		@Override
		public boolean isAvailable(int hour, GameCharacter character) {
			return Main.game.getPlayer().getSlavesWorkingJob(hour, SlaveJob.MILKING) < getSlaveLimit();
		}

		public String getAvailabilityText(int hour, GameCharacter character) {
			if(!isAvailable(hour, character)) {
				return "Not enough space in milking rooms!";
				
			} else if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)) {
				return "Slaves cannot work out of the cells at slavery administration. Move them into a room first!";
				
			}
			
			return super.getAvailabilityText(hour, character);
		}
		
		@Override
		public AbstractWorldType getWorldLocation(GameCharacter character) {
			Cell c = MilkingRoom.getMilkingCell(character, false);
			if(c==null) {
				return null;
			}
			return c.getType();
		}
		
		@Override
		public AbstractPlaceType getPlaceLocation(GameCharacter character) {
			Cell c = MilkingRoom.getMilkingCell(character, false);
			if(c==null) {
				return null;
			}
			return c.getPlace().getPlaceType();
		}

		@Override
		public Cell getWorkDestinationCell(int hour, GameCharacter slave) {
			return MilkingRoom.getMilkingCell(slave, false);
		}
		
		@Override
		public void sendToWorkLocation(int hour, GameCharacter slave) {
			Cell c = MilkingRoom.getMilkingCell(slave, true);
			if(c!=null) {
				if(c.getType()!=slave.getWorldLocation() || c.getLocation()!=slave.getLocation()) {
					slave.setLocation(c.getType(), c.getLocation(), false);
				}
				
			} else {
				slave.returnToHome();
			}
		}
	},
	
	OFFICE(PresetColour.BASE_LILAC,
			0.05f,
			4,
			2f,
			"office worker",
			"office worker",
			"Assign this slave to work in the office which you've had outfitted here in Lilaya's house.",
			0, 0,
			100,
			0, 1f,
			null, null,
			null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR) {
		
		private Cell getOfficeCell() {
			List<Cell> cells = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_OFFICE);
			if(!cells.isEmpty()) {
				return cells.get(0);
			}
			cells = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(PlaceUpgrade.LILAYA_OFFICE);
			if(!cells.isEmpty()) {
				return cells.get(0);
			}
			return null;
		}
		
		@Override
		public int getSlaveLimit() {
			if(getOfficeCell()==null) {
				return 0;
			}
			return 4;
		}
		
		@Override
		public boolean isAvailable(int hour, GameCharacter character) {
			return Main.game.getPlayer().getSlavesWorkingJob(hour, SlaveJob.OFFICE) < getSlaveLimit();
		}
	
		public String getAvailabilityText(int hour, GameCharacter character) {
			if(!isAvailable(hour, character)) {
				return "There isn't enough office space to assign this job!";
				
			} else if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)) {
				return "Slaves cannot work out of the cells at slavery administration. Move them into a room first!";
			}
			
			return super.getAvailabilityText(hour, character);
		}
		
		@Override
		public AbstractWorldType getWorldLocation(GameCharacter character) {
			Cell c = getOfficeCell();
			if(c==null) {
				return null;
			}
			return c.getType();
		}
		
		@Override
		public AbstractPlaceType getPlaceLocation(GameCharacter character) {
			Cell c = getOfficeCell();
			if(c==null) {
				return null;
			}
			return c.getPlace().getPlaceType();
		}

		@Override
		public Cell getWorkDestinationCell(int hour, GameCharacter slave) {
			return getOfficeCell();
		}
		
		@Override
		public void sendToWorkLocation(int hour, GameCharacter slave) {
			Cell c = getOfficeCell();
			if(c!=null) {
				if(c.getType()!=slave.getWorldLocation() || c.getLocation()!=slave.getLocation()) {
					slave.setLocation(c.getType(), c.getLocation(), false);
				}
			} else {
				slave.returnToHome();
			}
		}
	},

	BEDROOM(PresetColour.BASE_PERIWINKLE,
			0.05f,
			4,
			-1f,
			"bedroom",
			"bedroom",
			"Assign this slave to wait upon you in your bedroom.",
			0, 0.25f,
			0,
			0, 0,
			Util.newArrayListOfValues(
					SlaveJobSetting.BEDROOM_GREETING,
					SlaveJobSetting.BEDROOM_CLEAN,
					SlaveJobSetting.BEDROOM_WAKE_UP,
					SlaveJobSetting.BEDROOM_HELP_WASH),
			Util.newHashMapOfValues(
					new Value<>("Sleeping Arrangements",
						Util.newArrayListOfValues(
							SlaveJobSetting.BEDROOM_SLEEP_FLOOR,
							SlaveJobSetting.BEDROOM_SLEEP_ON_BED,
							SlaveJobSetting.BEDROOM_SLEEP_IN_BED))),
			Util.newArrayListOfValues(
					SlaveJobSetting.BEDROOM_SLEEP_ON_BED),
			WorldType.LILAYAS_HOUSE_FIRST_FLOOR,
			PlaceType.LILAYA_HOME_ROOM_PLAYER),
	;
	
	private Colour colour;
	private float hourlyEventChance;
	private int slaveLimit;
	private float hourlyFatigue;
	private String nameFeminine;
	private String nameMasculine;
	private String description;
	private float obedienceGain;
	protected float affectionGain;
	private int income;
	private float obedienceIncomeModifier;
	private float affectionIncomeModifier;
	private List<SlaveJobSetting> mutualSettings;
	private Map<String, List<SlaveJobSetting>> mutuallyExclusiveSettings;
	private List<SlaveJobSetting> defaultMutuallyExclusiveSettings;
	private AbstractWorldType worldLocation;
	private AbstractPlaceType placeLocation;
	
	private SlaveJob(
			Colour colour,
			float hourlyEventChance,
			int slaveLimit,
			float hourlyFatigue,
			String nameFeminine,
			String nameMasculine,
			String description,
			float affectionGain,
			float obedienceGain,
			int income,
			float affectionIncomeModifier,
			float obedienceIncomeModifier,
			List<SlaveJobSetting> mutualSettings,
			Map<String, List<SlaveJobSetting>> mutuallyExclusiveSettings,
			List<SlaveJobSetting> defaultMutuallyExclusiveSettings,
			AbstractWorldType worldLocation,
			AbstractPlaceType placeLocation) {
		this.colour = colour;
		this.hourlyEventChance = hourlyEventChance;
		this.slaveLimit = slaveLimit;
		this.hourlyFatigue = hourlyFatigue;
		this.nameFeminine = nameFeminine;
		this.nameMasculine = nameMasculine;
		this.description = description;
		this.obedienceGain = obedienceGain;
		this.affectionGain = affectionGain;
		this.income = income;
		this.obedienceIncomeModifier = obedienceIncomeModifier;
		this.affectionIncomeModifier = affectionIncomeModifier;
		
		if(mutualSettings == null) {
			this.mutualSettings = new ArrayList<>();
		} else {
			this.mutualSettings = mutualSettings;
		}
		
		if(mutuallyExclusiveSettings == null) {
			this.mutuallyExclusiveSettings = new HashMap<>();
		} else {
			this.mutuallyExclusiveSettings = mutuallyExclusiveSettings;
		}

		if(defaultMutuallyExclusiveSettings == null) {
			this.defaultMutuallyExclusiveSettings = new ArrayList<>();
		} else {
			this.defaultMutuallyExclusiveSettings = defaultMutuallyExclusiveSettings;
		}
		
		this.worldLocation = worldLocation;
		this.placeLocation = placeLocation;
	}
	
	public Colour getColour() {
		return colour;
	}

	public float getHourlyEventChance() {
		return hourlyEventChance;
	}

	public int getSlaveLimit() {
		return slaveLimit;
	}
	
	public float getHourlyFatigue() {
		return hourlyFatigue;
	}

	public String getName(GameCharacter character) {
		if(character.isFeminine()) {
			return nameFeminine;
		} else {
			return nameMasculine;
		}
	}
	
	public String getNameFeminine() {
		return nameFeminine;
	}
	
	public String getNameMasculine() {
		return nameMasculine;
	}

	public String getDescription() {
		return description;
	}
	
	public float getObedienceGain(int hour, GameCharacter slave) {
		Cell c = this.getWorkDestinationCell(hour, slave);
		return obedienceGain + (c==null?0:c.getPlace().getHourlyObedienceChange());
	}

	public float getAffectionGain(int hour, GameCharacter slave) {
		Cell c = this.getWorkDestinationCell(hour, slave);
		return affectionGain + (c==null?0:c.getPlace().getHourlyAffectionChange());
	}

	public int getIncome() {
		return income;
	}
	
	public int getFinalHourlyIncomeAfterModifiers(GameCharacter character) {
		int value = (int)(Math.max(0, (income + ((getAffectionIncomeModifier()*character.getAffection(Main.game.getPlayer()))) + ((getObedienceIncomeModifier()*character.getObedienceValue())))));
		
		if(this==SlaveJob.MILKING) {
			value = 0;
			if(character.getBreastRawStoredMilkValue()>0  && !character.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_MILK_DISABLE)) {
				int milked = MilkingRoom.getActualMilkPerHour(character);
				value += (milked * character.getMilk().getValuePerMl());
			}
			if(character.hasBreastsCrotch() && character.getBreastCrotchRawStoredMilkValue()>0  && !character.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_MILK_CROTCH_DISABLE)) {
				int milked = MilkingRoom.getActualCrotchMilkPerHour(character);
				value += (milked * character.getMilkCrotch().getValuePerMl());
			}
			if(character.hasPenis() && character.getPenisRawStoredCumValue()>0  && !character.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_CUM_DISABLE)) {
				int milked = MilkingRoom.getActualCumPerHour(character);
				value += (milked * character.getCum().getValuePerMl());
			}
			if(character.hasVagina() && !character.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_GIRLCUM_DISABLE)) {
				int milked = MilkingRoom.getActualGirlcumPerHour(character);
				value += (milked * character.getGirlcum().getValuePerMl());
			}
		}

		if(character.isSlave()) {
			if(character.getOwner().hasTrait(Perk.JOB_OFFICE_WORKER, true)) {
				return (int) (1.25f * value);
			} else if((character.getOwner().hasTrait(Perk.JOB_MAID, true) || character.getOwner().hasTrait(Perk.JOB_BUTLER, true)) && this==SlaveJob.CLEANING) {
				return 2 * value;
			}
		}
		
		return value;
	}
	
	public static int getFinalDailyIncomeAfterModifiers(GameCharacter character) {
		int value = 0;
		
		for(int i=0; i<24; i++) {
			value += character.getSlaveJob(i).getFinalHourlyIncomeAfterModifiers(character);
		}
		
		return value;
	}

	public float getObedienceIncomeModifier() {
		return obedienceIncomeModifier;
	}

	public float getAffectionIncomeModifier() {
		return affectionIncomeModifier;
	}

	public List<SlaveJobSetting> getMutualSettings() {
		return mutualSettings;
	}

	public Map<String, List<SlaveJobSetting>> getMutuallyExclusiveSettings() {
		return mutuallyExclusiveSettings;
	}
	
	public List<SlaveJobSetting> getDefaultMutuallyExclusiveSettings() {
		return defaultMutuallyExclusiveSettings;
	}

	public AbstractWorldType getWorldLocation(GameCharacter character) {
		return worldLocation;
	}

	public AbstractPlaceType getPlaceLocation(GameCharacter character) {
		return placeLocation;
	}
	
	public Cell getWorkDestinationCell(int hour, GameCharacter slave) {
		AbstractWorldType wType = slave.getSlaveJob(hour).getWorldLocation(slave);
		if(wType==null) {
			return null;
		}
		return Main.game.getWorlds().get(wType).getRandomUnoccupiedCell(slave.getSlaveJob(hour).getPlaceLocation(slave));
	}
	
	public void sendToWorkLocation(int hour, GameCharacter slave) {
		if(slave.getSlaveJob(hour).getWorldLocation(slave)!=null
				&& slave.getSlaveJob(hour).getPlaceLocation(slave)!=null
				&& (slave.getSlaveJob(hour).getWorldLocation(slave)!=slave.getWorldLocation() || slave.getSlaveJob(hour).getPlaceLocation(slave)!=slave.getLocationPlace().getPlaceType())) {
			slave.setRandomUnoccupiedLocation(slave.getSlaveJob(hour).getWorldLocation(slave), slave.getSlaveJob(hour).getPlaceLocation(slave), false);
		}
	}
	
	public boolean isAvailable(int hour, GameCharacter character) {
		return character.getSlaveJob(hour)==this
				|| (!character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION) && character.getOwner().getSlavesWorkingJob(hour, this)<this.getSlaveLimit());
	}
	
	public String getAvailabilityText(int hour, GameCharacter character) {
		if(character.getOwner().getSlavesWorkingJob(hour, this)>=this.getSlaveLimit()) {
			return "You have already assigned the maximum number of slaves to this job!";
			
		} else if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)) {
			return "Slaves cannot work out of the cells at slavery administration. Move them into a room first!";
			
		} else {
			return "This job is unavailable!";
		}
	}
	
	public EventLogEntry getHourlyEvent(long hour, NPC slave, List<NPC> otherNPCsPresent) {
//		return new EventLogEntry(Main.game.getDayNumber()-1*24l+hour, "[style.colourDisabled(Nothing)]", "");
		return null;
	}
	
}
