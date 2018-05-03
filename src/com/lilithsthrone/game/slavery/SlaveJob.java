package com.lilithsthrone.game.slavery;

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
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.87
 * @version 0.2.2
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
	
	IDLE(-1, "Idle", "Idle", "Do not assign any job to this slave.",
			0, 0,
			0, 0, 0,
			null, null, null,
			null, null) {
		public boolean isAvailable(GameCharacter character) {
			return true;
		}
	},
	
	CLEANING(20, "maid", "manservant", "Assign this slave to help Rose keep the house clean, deal with visitors, and perform all sorts of menial housework.",
			0, 0.5f,
			80, 0f, 0.1f,
			null, null, null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			PlaceType.LILAYA_HOME_CORRIDOR) {
		@Override
		public EventLogEntry getHourlyEvent(long hour, NPC slave, List<NPC> otherNPCsPresent) {
			return new EventLogEntry(Main.game.getDayNumber()-1*24l+hour, "[style.colourDisabled(Nothing)]", "");
		}
		
		@Override
		public void sendToWorkLocation(GameCharacter slave) {
			if(slave.getLocationPlace().getPlaceType() == PlaceType.LILAYA_HOME_CORRIDOR) {
				slave.moveToAdjacentMatchingCellType();
			
			} else {
				// 50/50 of being upstairs or downstairs:
				WorldType worldTypeToUse = WorldType.LILAYAS_HOUSE_FIRST_FLOOR;
				if(Math.random()>0.5f) {
					worldTypeToUse = WorldType.LILAYAS_HOUSE_GROUND_FLOOR;
				}
				
				slave.setRandomLocation(worldTypeToUse, PlaceType.LILAYA_HOME_CORRIDOR, false);
			}
		}
	},
	
	LIBRARY(5, "librarian", "librarian", "Assign this slave to work in Lilaya's library.",
			0, 0.25f,
			80, 0, 0.1f,
			null, null, null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			PlaceType.LILAYA_HOME_LIBRARY),
	
	KITCHEN(5, "cook", "cook", "Assign this slave to work in Lilaya's kitchen as a cook.",
			0, 0.25f,
			80, 0, 0.05f,
			null, null, null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			PlaceType.LILAYA_HOME_KITCHEN),
	
	LAB_ASSISTANT(1, "lab assistant", "lab assistant", "Assign this slave to help Lilaya in her lab.",
			0, 0.25f,
			100, 0, 0.2f,
			null, null, null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			PlaceType.LILAYA_HOME_LAB),

	TEST_SUBJECT(5, "test subject", "test subject", "Allow Lilaya to use this slave as a test subject for her experiments.",
			-0.5f, 0.5f,
			150, 0, 0,
			Util.newArrayListOfValues(
					new ListValue<>(SlaveJobSetting.TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE),
					new ListValue<>(SlaveJobSetting.TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE)),
			null, null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			PlaceType.LILAYA_HOME_LAB) {
		@Override
		public float getAffectionGain(GameCharacter slave) {
			if(slave.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
				return 0.5f;
			} else {
				return -0.5f;
			}
		}
	},
	
	PUBLIC_STOCKS(5, "public use", "public use", "Assign this slave to be locked in the public-use stocks in slaver ally.",
			-5f, 1f,
			0, 0, 0,
			Util.newArrayListOfValues(
					new ListValue<>(SlaveJobSetting.SEX_ORAL),
					new ListValue<>(SlaveJobSetting.SEX_VAGINAL),
					new ListValue<>(SlaveJobSetting.SEX_ANAL),
					new ListValue<>(SlaveJobSetting.SEX_NIPPLES)),
			Util.newHashMapOfValues(new Value<>("Pregnancy", Util.newArrayListOfValues(
					new ListValue<>(SlaveJobSetting.SEX_PROMISCUITY_PILLS),
					new ListValue<>(SlaveJobSetting.SEX_NO_PILLS),
					new ListValue<>(SlaveJobSetting.SEX_VIXENS_VIRILITY)))),
			Util.newArrayListOfValues(
					new ListValue<>(SlaveJobSetting.SEX_NO_PILLS)),
			WorldType.SLAVER_ALLEY,
			PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS) {
		@Override
		public float getAffectionGain(GameCharacter slave) {
			if(slave.hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return 1f;
			} else {
				return -5f;
			}
		}
	},
	
	PROSTITUTE(10, "Prostitute", "Prostitute", "Assign this slave to work as a prostitute at the brothel 'Angel's Kiss'.",
			-0.25f, 0.5f,
			200, 0, 0.5f,
			Util.newArrayListOfValues(
					new ListValue<>(SlaveJobSetting.SEX_ORAL),
					new ListValue<>(SlaveJobSetting.SEX_VAGINAL),
					new ListValue<>(SlaveJobSetting.SEX_ANAL),
					new ListValue<>(SlaveJobSetting.SEX_NIPPLES)),
			Util.newHashMapOfValues(new Value<>("Pregnancy", Util.newArrayListOfValues(
					new ListValue<>(SlaveJobSetting.SEX_PROMISCUITY_PILLS),
					new ListValue<>(SlaveJobSetting.SEX_NO_PILLS),
					new ListValue<>(SlaveJobSetting.SEX_VIXENS_VIRILITY)))),
			Util.newArrayListOfValues(
					new ListValue<>(SlaveJobSetting.SEX_NO_PILLS)),
			WorldType.ANGELS_KISS_FIRST_FLOOR,
			PlaceType.ANGELS_KISS_BEDROOM) {
		
		@Override
		public float getAffectionGain(GameCharacter slave) {
			if(slave.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
				return 1f;
			} else {
				return -0.25f;
			}
		}
		
		@Override
		public boolean isAvailable(GameCharacter character) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
				return false;
			}
			return super.isAvailable(character);
		}

		@Override
		public String getAvailabilityText(GameCharacter character) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
				return "You do not have permission from Angel to send your slaves to work in her brothel!";
				
			} else if(character.getOwner().getSlavesWorkingJob(this)>=this.getSlaveLimit()) {
				return "You have already assigned the maximum number of slaves to this job!";
				
			} else if(character.getHomeLocationPlace().getPlaceType() == PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION) {
				return "Slaves cannot work out of the cells at slavery administration. Move them into a room first!";
				
			} else {
				return "This job is available!";
			}
		}
	},
	
	MILKING(5, "Dairy Cow", "Dairy Bull", "Assign this slave to the cow stalls, ready for milking or breeding (or perhaps both). Income is based off of the assigned slave's milk production.",
			-0.25f, 1f,
			0, 0, 0,
			Util.newArrayListOfValues(
					new ListValue<>(SlaveJobSetting.SEX_ORAL),
					new ListValue<>(SlaveJobSetting.SEX_VAGINAL),
					new ListValue<>(SlaveJobSetting.SEX_ANAL)),
			Util.newHashMapOfValues(
					new Value<>("Pregnancy", Util.newArrayListOfValues(
												new ListValue<>(SlaveJobSetting.SEX_PROMISCUITY_PILLS),
												new ListValue<>(SlaveJobSetting.SEX_NO_PILLS),
												new ListValue<>(SlaveJobSetting.SEX_VIXENS_VIRILITY))),
					new Value<>("Room Preference", Util.newArrayListOfValues(
							new ListValue<>(SlaveJobSetting.MILKING_INDUSTRIAL),
							new ListValue<>(SlaveJobSetting.MILKING_REGULAR),
							new ListValue<>(SlaveJobSetting.MILKING_ARTISAN),
							new ListValue<>(SlaveJobSetting.MILKING_NO_PREFERENCE)))),
			Util.newArrayListOfValues(
					new ListValue<>(SlaveJobSetting.SEX_NO_PILLS),
					new ListValue<>(SlaveJobSetting.MILKING_NO_PREFERENCE)),
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR) {

		@Override
		public float getAffectionGain(GameCharacter slave) {
			if(slave.hasFetish(Fetish.FETISH_LACTATION_SELF)) {
				return 2f;
			} else {
				return -0.25f;
			}
		}
		
		private List<Cell> getMilkingCells() {
			List<Cell> milkingCells = new ArrayList<>();
			Cell[][] grid = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCellGrid();
			for(int i=0 ; i<grid.length ; i++) {
				for(int j=0 ; j<grid[0].length ; j++) {
					if(grid[i][j].getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
						milkingCells.add(grid[i][j]);
					}
				}
			}
			grid = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCellGrid();
			for(int i=0 ; i<grid.length ; i++) {
				for(int j=0 ; j<grid[0].length ; j++) {
					if(grid[i][j].getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
						milkingCells.add(grid[i][j]);
					}
				}
			}
			return milkingCells;
		}
		
		private Cell getMilkingCell(GameCharacter character) {
			List<Cell> milkingCells = getMilkingCells();
			
			List<Cell> freeMilkingCells = new ArrayList<>();
			
			for(Cell c : milkingCells) {
				int charactersPresent = Main.game.getCharactersPresent(c).size();
				
				if(character.getSlaveJobSettings().contains(SlaveJobSetting.MILKING_INDUSTRIAL) && c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS) && charactersPresent<8) {
					return c;
				} else if(character.getSlaveJobSettings().contains(SlaveJobSetting.MILKING_ARTISAN) && c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS) && charactersPresent<8) {
					return c;
				} else if(character.getSlaveJobSettings().contains(SlaveJobSetting.MILKING_REGULAR) && !c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS)
						&& !c.getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS) && charactersPresent<8) {
					return c;
				}
				
				if(charactersPresent<8) {
					freeMilkingCells.add(c);
				}
			}
			
			if(milkingCells.isEmpty()) {
				return null;
			}
			
			return freeMilkingCells.get(Util.random.nextInt(freeMilkingCells.size()));
		}
		
		@Override
		public boolean isAvailable(GameCharacter character) {
			return Main.game.getPlayer().getSlavesWorkingJob(SlaveJob.MILKING) < (getMilkingCells().size() * 8);
		}

		public String getAvailabilityText(GameCharacter character) {
			if(!isAvailable(character)) {
				return "Not enough space in milking rooms!";
			}
			
			return super.getAvailabilityText(character);
		}
		
		@Override
		public WorldType getWorldLocation(GameCharacter character) {
			Cell c = getMilkingCell(character);
			if(c==null) {
				return null;
			}
			return c.getType();
		}

		@Override
		public PlaceType getPlaceLocation(GameCharacter character) {
			Cell c = getMilkingCell(character);
			if(c==null) {
				return null;
			}
			return c.getPlace().getPlaceType();
		}
		
		@Override
		public void sendToWorkLocation(GameCharacter slave) {
			Cell c = getMilkingCell(slave);
			if(c!=null) {
				if(slave.getSlaveJob().getWorldLocation(slave)!=slave.getWorldLocation() || slave.getSlaveJob().getPlaceLocation(slave)!=slave.getLocationPlace().getPlaceType()) {
					slave.setLocation(c.getType(), c.getLocation(), false);
				}
			}
		}
		
	}
	
	;
	
	private int slaveLimit;
	private String nameFeminine;
	private String nameMasculine;
	private String description;
	private float obedienceGain;
	private float affectionGain;
	private int income;
	private float obedienceIncomeModifier;
	private float affectionIncomeModifier;
	private List<SlaveJobSetting> mutualSettings;
	private Map<String, List<SlaveJobSetting>> mutuallyExclusiveSettings;
	private List<SlaveJobSetting> defaultMutuallyExclusiveSettings;
	private WorldType worldLocation;
	private PlaceType placeLocation;
	
	private SlaveJob(int slaveLimit,
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
			WorldType worldLocation,
			PlaceType placeLocation) {
		
		this.slaveLimit = slaveLimit;
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
	
	public int getSlaveLimit() {
		return slaveLimit;
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
	
	public float getObedienceGain(GameCharacter slave) {
		return obedienceGain;
	}

	public float getAffectionGain(GameCharacter slave) {
		return affectionGain;
	}

	public int getIncome() {
		return income;
	}
	
	public int getFinalHourlyIncomeAfterModifiers(GameCharacter character) {
		int value = (int)(Math.max(0, (income + ((getAffectionIncomeModifier()*character.getAffection(Main.game.getPlayer()))) + ((getObedienceIncomeModifier()*character.getObedienceValue())))));
		
		if(this==SlaveJob.MILKING) {
			value = 0;
			if(character.getBreastRawStoredMilkValue()>0) {
				int milked = (int) Math.min(1, (character.getBreastLactationRegeneration().getPercentageRegen()*60));
				value += milked * (1 + character.getMilk().getFluidModifiers().size());
				
			} 
			if(character.hasPenis() && character.getPenisRawCumProductionValue()>0) {
				int milked = character.getPenisRawCumProductionValue();
				value += milked * (1 + character.getCum().getFluidModifiers().size());
			}
		}
		
		if(character.getOwner().hasTrait(Perk.JOB_OFFICE_WORKER, true)) {
			return (int) (1.25f * value);
		} else if((character.getOwner().hasTrait(Perk.JOB_MAID, true) || character.getOwner().hasTrait(Perk.JOB_BUTLER, true)) && this==SlaveJob.CLEANING) {
			return (int) (2 * value);
		}
		
		return value;
	}
	
	public int getFinalDailyIncomeAfterModifiers(GameCharacter character) {
		int value = (int)(Math.max(0, (income + ((getAffectionIncomeModifier()*character.getAffection(Main.game.getPlayer()))) + ((getObedienceIncomeModifier()*character.getObedienceValue()))))*character.getTotalHoursWorked());
		
		if(this==SlaveJob.MILKING) {
			value = 0;
			if(character.getBreastRawStoredMilkValue()>0) {
				int milked = (int) Math.min(1, (character.getBreastLactationRegeneration().getPercentageRegen()*60));
				value += milked * (1 + character.getMilk().getFluidModifiers().size());
				
			} 
			if(character.hasPenis() && character.getPenisRawCumProductionValue()>0) {
				int milked = character.getPenisRawCumProductionValue();
				value += milked * (1 + character.getCum().getFluidModifiers().size());
			}
			value *= character.getTotalHoursWorked();
		}
		
		if(character.getOwner().hasTrait(Perk.JOB_OFFICE_WORKER, true)) {
			return (int) (1.25f * value);
		} else if((character.getOwner().hasTrait(Perk.JOB_MAID, true) || character.getOwner().hasTrait(Perk.JOB_BUTLER, true)) && this==SlaveJob.CLEANING) {
			return (int) (2 * value);
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

	public WorldType getWorldLocation(GameCharacter character) {
		return worldLocation;
	}

	public PlaceType getPlaceLocation(GameCharacter character) {
		return placeLocation;
	}
	
	public void sendToWorkLocation(GameCharacter slave) {
		if(slave.getSlaveJob().getWorldLocation(slave)!=null && slave.getSlaveJob().getPlaceLocation(slave)!=null
				&& (slave.getSlaveJob().getWorldLocation(slave)!=slave.getWorldLocation() || slave.getSlaveJob().getPlaceLocation(slave)!=slave.getLocationPlace().getPlaceType())) {
			slave.setRandomUnoccupiedLocation(slave.getSlaveJob().getWorldLocation(slave), slave.getSlaveJob().getPlaceLocation(slave), false);
		}
	}
	
	public boolean isAvailable(GameCharacter character) {
		return character.getSlaveJob()==this || (character.getHomeLocationPlace().getPlaceType() != PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION && character.getOwner().getSlavesWorkingJob(this)<this.getSlaveLimit());
	}
	
	public String getAvailabilityText(GameCharacter character) {
		if(character.getOwner().getSlavesWorkingJob(this)>=this.getSlaveLimit()) {
			return "You have already assigned the maximum number of slaves to this job!";
			
		} else if(character.getHomeLocationPlace().getPlaceType() == PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION) {
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
