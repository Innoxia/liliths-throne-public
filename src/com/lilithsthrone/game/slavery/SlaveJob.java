package com.lilithsthrone.game.slavery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.World;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.LilayasHome;
import com.lilithsthrone.world.places.PlaceInterface;
import com.lilithsthrone.world.places.SlaverAlley;

/**
 * @since 0.1.87
 * @version 0.1.87
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
			null, null,
			null, null) {
		public boolean isAvailable(GameCharacter character) {
			return true;
		}
	},
	
	CLEANING(20, "Maid", "Manservant", "Assign this slave to help Rose keep the house clean, deal with visitors, and perform all sorts of menial housework.",
			0, 0.5f,
			5, 0f, 0.1f,
			null, null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			LilayasHome.LILAYA_HOME_CORRIDOR) {
		@Override
		public EventLogEntry getHourlyEvent(long hour, NPC slave, List<NPC> otherNPCsPresent) {
			return new EventLogEntry(Main.game.getDayNumber()-1*24l+hour, "[style.colourDisabled(Nothing)]", "");
		}
		
		@Override
		public void sendToWorkLocation(NPC slave) {
			if(slave.getLocationPlace().getPlaceType() == LilayasHome.LILAYA_HOME_CORRIDOR) {
				World world = Main.game.getWorlds().get(slave.getWorldLocation());
				List<Vector2i> availableLocations = new ArrayList<>();
				
				if(world.getCell(slave.getLocation().getX()+1, slave.getLocation().getY())!=null && world.getCell(slave.getLocation().getX()+1, slave.getLocation().getY()).getPlace().getPlaceType()==LilayasHome.LILAYA_HOME_CORRIDOR) {
					availableLocations.add(new Vector2i(slave.getLocation().getX()+1, slave.getLocation().getY()));
				}
				if(world.getCell(slave.getLocation().getX()-1, slave.getLocation().getY())!=null && world.getCell(slave.getLocation().getX()-1, slave.getLocation().getY()).getPlace().getPlaceType()==LilayasHome.LILAYA_HOME_CORRIDOR) {
					availableLocations.add(new Vector2i(slave.getLocation().getX()-1, slave.getLocation().getY()));
				}
				if(world.getCell(slave.getLocation().getX(), slave.getLocation().getY()+1)!=null && world.getCell(slave.getLocation().getX(), slave.getLocation().getY()+1).getPlace().getPlaceType()==LilayasHome.LILAYA_HOME_CORRIDOR) {
					availableLocations.add(new Vector2i(slave.getLocation().getX(), slave.getLocation().getY()+1));
				}
				if(world.getCell(slave.getLocation().getX(), slave.getLocation().getY()-1)!=null && world.getCell(slave.getLocation().getX(), slave.getLocation().getY()-1).getPlace().getPlaceType()==LilayasHome.LILAYA_HOME_CORRIDOR) {
					availableLocations.add(new Vector2i(slave.getLocation().getX(), slave.getLocation().getY()-1));
				}
				
				slave.setLocation(availableLocations.get(Util.random.nextInt(availableLocations.size())));
					
			} else {
				// 50/50 of being upstairs or downstairs:
				WorldType worldTypeToUse = WorldType.LILAYAS_HOUSE_FIRST_FLOOR;
				if(Math.random()>0.5f) {
					worldTypeToUse = WorldType.LILAYAS_HOUSE_GROUND_FLOOR;
				}
				World world = Main.game.getWorlds().get(worldTypeToUse);
				Cell[][] cellGrid =world.getCellGrid();
				List<Cell> corridorCells = new ArrayList<>();
				for(int i = 0; i< cellGrid.length; i++) {
					for(int j = 0; j < cellGrid[0].length; j++) {
						if(cellGrid[i][j].getPlace().getPlaceType() == LilayasHome.LILAYA_HOME_CORRIDOR) {
							corridorCells.add(cellGrid[i][j]);
						}
					}
				}
				
				Cell c = corridorCells.get(Util.random.nextInt(corridorCells.size()));
				
				slave.setLocation(worldTypeToUse, c.getLocation(), false);
			}
		}
	},
	
	LIBRARY(5, "Librarian", "Librarian", "Assign this slave to work in Lilaya's library.",
			0, 0.25f,
			5, 0, 0.1f,
			null, null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			LilayasHome.LILAYA_HOME_LIBRARY),
	
	KITCHEN(5, "Cook", "Cook", "Assign this slave to work in Lilaya's kitchen as a cook.",
			0, 0.25f,
			5, 0, 0.05f,
			null, null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			LilayasHome.LILAYA_HOME_KITCHEN),
	
	LAB_ASSISTANT(1, "Lab Assistant", "Lab Assistant", "Assign this slave to help Lilaya in her lab.",
			0, 0.25f,
			10, 0, 0.2f,
			null, null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			LilayasHome.LILAYA_HOME_LAB),
	
	TEST_SUBJECT(5, "Test Subject", "Test Subject", "Allow Lilaya to use this slave as a test subject for her experiments.",
			-0.5f, 0.5f,
			10, 0, 0,
			Util.newArrayListOfValues(
					new ListValue<>(SlaveJobSettings.TEST_SUBJECT_ALLOW_TRANSFORMATIONS_FEMALE),
					new ListValue<>(SlaveJobSettings.TEST_SUBJECT_ALLOW_TRANSFORMATIONS_MALE)),
			null,
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			LilayasHome.LILAYA_HOME_LAB),
	
//	BROTHEL(5, "Prostitute (Brothel)", "Prostitute (Brothel)", "Assign this slave to work as a prostitute at the brothel 'Angel's Kiss' in slaver ally.",
//			-0.5f, 0.5f,
//			25, 0, 0.5f,
//			Util.newArrayListOfValues(
//					new ListValue<>(SlaveJobSettings.SEX_ORAL),
//					new ListValue<>(SlaveJobSettings.SEX_VAGINAL),
//					new ListValue<>(SlaveJobSettings.SEX_ANAL),
//					new ListValue<>(SlaveJobSettings.SEX_NIPPLES),
//					new ListValue<>(SlaveJobSettings.SEX_PROMISCUITY_PILLS),
//					new ListValue<>(SlaveJobSettings.SEX_VIXENS_VIRILITY)),
//			null,
//			WorldType.SLAVER_ALLEY,
//			SlaverAlley.BROTHEL),
//	
//	PUBLIC_USE(5, "Public Use", "Public Use", "Assign this slave to be locked in the public-use stocks in slaver ally.",
//			-5f, 1f,
//			0, 0, 0,
//			Util.newArrayListOfValues(
//					new ListValue<>(SlaveJobSettings.SEX_ORAL),
//					new ListValue<>(SlaveJobSettings.SEX_VAGINAL),
//					new ListValue<>(SlaveJobSettings.SEX_ANAL),
//					new ListValue<>(SlaveJobSettings.SEX_NIPPLES),
//					new ListValue<>(SlaveJobSettings.SEX_PROMISCUITY_PILLS),
//					new ListValue<>(SlaveJobSettings.SEX_VIXENS_VIRILITY)),
//			null,
//			WorldType.SLAVER_ALLEY,
//			SlaverAlley.PUBLIC_STOCKS),
	
	//'Allow to be impregnated (Public use)' and 'Allow to be impregnated (Other slaves)'
//	MILKING(5, "Cow Stalls", "Cow Stalls", "Assign this slave to the cow stalls, ready for milking or breeding (or perhaps both).",
//			-5f, 1f,
//			0, 0, 0,
//			Util.newArrayListOfValues(
//					new ListValue<>(SlaveJobSettings.SEX_ORAL),
//					new ListValue<>(SlaveJobSettings.SEX_VAGINAL),
//					new ListValue<>(SlaveJobSettings.SEX_ANAL),
//					new ListValue<>(SlaveJobSettings.SEX_NIPPLES),
//					new ListValue<>(SlaveJobSettings.SEX_PROMISCUITY_PILLS),
//					new ListValue<>(SlaveJobSettings.SEX_VIXENS_VIRILITY)),
//			null,
//			WorldType.SLAVER_ALLEY,
//			SlaverAlley.PUBLIC_STOCKS)
	
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
	private List<SlaveJobSettings> mutualSettings;
	private Map<String, List<SlaveJobSettings>> mutuallyExclusiveSettings;
	private WorldType worldLocation;
	private PlaceInterface placeLocation;
	
	private SlaveJob(int slaveLimit,
			String nameFeminine, String nameMasculine, String description,
			float affectionGain, float obedienceGain,
			int income, float affectionIncomeModifier, float obedienceIncomeModifier,
			List<SlaveJobSettings> mutualSettings, Map<String, List<SlaveJobSettings>> mutuallyExclusiveSettings,
			WorldType worldLocation, PlaceInterface placeLocation) {
		
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
	
	public float getObedienceGain() {
		return obedienceGain;
	}

	public float getAffectionGain() {
		return affectionGain;
	}

	public int getIncome() {
		return income;
	}
	
	public int getFinalHourlyIncomeAfterModifiers(GameCharacter character) {
		return Math.max(0, (int) (income + ((getAffectionIncomeModifier()*character.getAffection(Main.game.getPlayer()))) + ((getObedienceIncomeModifier()*character.getObedienceValue()))));
	}
	
	public int getFinalDailyIncomeAfterModifiers(GameCharacter character) {
		return Math.max(0, (int) (income + ((getAffectionIncomeModifier()*character.getAffection(Main.game.getPlayer()))) + ((getObedienceIncomeModifier()*character.getObedienceValue()))))*character.getTotalHoursWorked();
	}

	public float getObedienceIncomeModifier() {
		return obedienceIncomeModifier;
	}

	public float getAffectionIncomeModifier() {
		return affectionIncomeModifier;
	}

	public List<SlaveJobSettings> getMutualSettings() {
		return mutualSettings;
	}

	public Map<String, List<SlaveJobSettings>> getMutuallyExclusiveSettings() {
		return mutuallyExclusiveSettings;
	}

	public WorldType getWorldLocation() {
		return worldLocation;
	}

	public PlaceInterface getPlaceLocation() {
		return placeLocation;
	}
	
	public void sendToWorkLocation(NPC slave) {
		slave.setLocation(slave.getSlaveJob().getWorldLocation(), slave.getSlaveJob().getPlaceLocation(), false);
	}
	
	public boolean isAvailable(GameCharacter character) {
		return character.getSlaveJob()==this || (character.getHomeLocationPlace().getPlaceType() != SlaverAlley.SLAVERY_ADMINISTRATION && character.getOwner().getSlavesWorkingJob(this)<this.getSlaveLimit());
	}
	
	public String getAvailabilityText(GameCharacter character) {
		if(character.getOwner().getSlavesWorkingJob(this)>=this.getSlaveLimit()) {
			return "You have already assigned the maximum number of slaves to this job!";
			
		} else if(character.getHomeLocationPlace().getPlaceType() == SlaverAlley.SLAVERY_ADMINISTRATION) {
			return "Slaves cannot work out of the cells at slavery administration. Move them into a room first!";
			
		} else {
			return "This job is available!";
		}
	}
	
	public EventLogEntry getHourlyEvent(long hour, NPC slave, List<NPC> otherNPCsPresent) {
//		return new EventLogEntry(Main.game.getDayNumber()-1*24l+hour, "[style.colourDisabled(Nothing)]", "");
		return null;
	}
	
}
