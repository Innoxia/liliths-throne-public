package com.lilithsthrone.game.occupantManagement.slave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.World;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.87
 * @version 0.3.9
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
			-0,
			"Idle",
			"Idle",
			"Do not assign any job to this character.",
			0, 0,
			0,
			0, 0,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					SlaveJobFlag.INTERACTION_SEX,
					SlaveJobFlag.INTERACTION_BONDING,
					SlaveJobFlag.GUEST_CAN_WORK),
			null, null) {
		@Override
		public boolean isAvailable(int hour, GameCharacter character) {
			return true;
		}
		private Cell getSlaveLoungeCell(GameCharacter slave) {
			Random rnd = new Random();
			rnd.setSeed(Main.game.getSecondsPassed()); // Make sure that the seed is consistent based on time so that both sendToWorkLocation() and getWorkDestinationCell() return the same cell during the same turn update
			
			if(!slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_HOUSE_FREEDOM) || rnd.nextFloat()<0.25f) { // 75% chance of using the lounge
				return null;
			}
			
			// If they're sleeping they will only rarely go to the lounge (5% chance)
			int hour = Main.game.getHourOfDay();
			if(slave.isSleepingAtHour(hour) || rnd.nextFloat()<0.05f) {
				return null;
			}
			
			// If they're overworked, they're more likely to be resting in their room
			if((rnd.nextFloat()<0.25f && slave.hasStatusEffect(StatusEffect.OVERWORKED_1))
					|| ( rnd.nextFloat()<0.5f && slave.hasStatusEffect(StatusEffect.OVERWORKED_2))
					|| slave.hasStatusEffect(StatusEffect.OVERWORKED_3)) {
				return null;
			}
			
			List<Cell> cells = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_SLAVE_LOUNGE);
			cells.addAll(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(PlaceUpgrade.LILAYA_SLAVE_LOUNGE));
			cells.removeIf(c -> Main.game.getCharactersPresent(c).size() > 8); // If lounge is full then don't use it
			if(!cells.isEmpty()) {
				if(slave.isShy() && rnd.nextFloat()<0.8f) { // 4/5 times go to the emptiest lounge if the slave is shy:
					cells.sort((c1, c2) -> c2.getCharactersPresentIds().size() - c1.getCharactersPresentIds().size());
				} else { // Return lounge with the most slaves in it:
					cells.sort((c1, c2) -> c1.getCharactersPresentIds().size() - c2.getCharactersPresentIds().size());
				}
				return cells.get(0);
			}
			return null;
		}
		@Override
		public void sendToWorkLocation(GameCharacter slave) {
			Cell lounge = getSlaveLoungeCell(slave);
			if(lounge==null) {
				slave.returnToHome();
			} else {
				slave.setLocation(lounge);
			}
		}
		@Override
		public Cell getWorkDestinationCell(GameCharacter slave) {
			Cell lounge = getSlaveLoungeCell(slave);
			if(lounge==null) {
				return slave.getHomeCell();
			} else {
				return lounge;
			}
		}
	},
	
	CLEANING(PresetColour.BASE_BLUE_LIGHT,
			0.05f,
			20,
			2f,
			"maid",
			"manservant",
			"Assign this character to help Rose keep the house clean, deal with visitors, and perform all sorts of menial housework.",
			0, 0.5f,
			80,
			0f, 0.1f,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS,
					SlaveJobFlag.INTERACTION_SEX,
					SlaveJobFlag.INTERACTION_BONDING,
					SlaveJobFlag.GUEST_CAN_WORK),
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR) {
		
		@Override
		public void sendToWorkLocation(GameCharacter slave) {
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

	SECURITY(PresetColour.BASE_CRIMSON,
			0.05f,
			8,
			2f,
			"security guard",
			"security guard",
			"Assign this character to act as a security guard. A guard will always be posted at the entrance, with other guards patrolling the corridors.",
			0, 0.5f,
			80,
			0f, 0.1f,
			Util.newArrayListOfValues(
					SlaveJobSetting.SECURITY_ENTRANCE_PRIORITY,
					SlaveJobSetting.SECURITY_ANSWER_DOOR),
			Util.newArrayListOfValues(
					SlaveJobSetting.SECURITY_ANSWER_DOOR),
			null,
			null,
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS,
					SlaveJobFlag.INTERACTION_SEX,
					SlaveJobFlag.INTERACTION_BONDING,
					SlaveJobFlag.GUEST_CAN_WORK),
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL) {
		private void moveToCorridor(GameCharacter slave) {
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
		@Override
		public void sendToWorkLocation(GameCharacter slave) {
			Optional<NPC> guardAtEntrance = Main.game.getCharactersPresent(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL).stream().filter(npc->npc.isSlave() && npc.isAtWork()).findFirst();
			
			if(slave.getLocationPlaceType()==PlaceType.LILAYA_HOME_ENTRANCE_HALL
					|| !guardAtEntrance.isPresent()
					|| (guardAtEntrance.isPresent()
							&& !guardAtEntrance.get().hasSlaveJobSetting(SlaveJob.SECURITY, SlaveJobSetting.SECURITY_ENTRANCE_PRIORITY)
							&& slave.hasSlaveJobSetting(SlaveJob.SECURITY, SlaveJobSetting.SECURITY_ENTRANCE_PRIORITY))) {
				if(guardAtEntrance.isPresent() && slave.getLocationPlaceType()!=PlaceType.LILAYA_HOME_ENTRANCE_HALL) {
					moveToCorridor(guardAtEntrance.get());
				}
				slave.setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ENTRANCE_HALL, false);
				
			} else {
				moveToCorridor(slave);
			}
		}
	},
	
	LIBRARY(PresetColour.BASE_TEAL,
			0.05f,
			5,
			1.5f,
			"librarian",
			"librarian",
			"Assign this character to work in Lilaya's library.",
			0, 0.25f, 
			80,
			0, 0.1f,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS,
					SlaveJobFlag.INTERACTION_SEX,
					SlaveJobFlag.INTERACTION_BONDING,
					SlaveJobFlag.GUEST_CAN_WORK),
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LIBRARY),
	
	KITCHEN(PresetColour.BASE_TAN,
			0.05f,
			5,
			2,
			"cook",
			"cook",
			"Assign this character to work in Lilaya's kitchen as a cook.",
			0, 0.25f,
			80,
			0, 0.05f,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS,
					SlaveJobFlag.INTERACTION_SEX,
					SlaveJobFlag.INTERACTION_BONDING,
					SlaveJobFlag.GUEST_CAN_WORK),
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_KITCHEN),

	GARDEN(PresetColour.BASE_GREEN,
			0.05f,
			4,
			2,
			"gardener",
			"gardener",
			"Assign this character to work as a gardener in Lilaya's courtyard garden.",
			0, 0.25f,
			80,
			0, 0.05f,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS,
					SlaveJobFlag.INTERACTION_SEX,
					SlaveJobFlag.INTERACTION_BONDING,
					SlaveJobFlag.GUEST_CAN_WORK),
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_GARDEN),
	
	LAB_ASSISTANT(PresetColour.BASE_GREEN_LIME,
			0.05f,
			1,
			1.5f,
			"lab assistant",
			"lab assistant",
			"Assign this character to help Lilaya in her lab.",
			0, 0.25f,
			100,
			0, 0.2f,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS,
					SlaveJobFlag.INTERACTION_BONDING,
					SlaveJobFlag.GUEST_CAN_WORK),
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
				return "No-one can work in Lilaya's lab while she is sleeping!";
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
			null,
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS),
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB) {
		@Override
		public float getAffectionGain(GameCharacter slave) {
			if(slave.isDoll()) {
				return 0;
			}
			if(slave.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
				return 0.5f;
			} else {
				return -0.5f;
			}
		}
		@Override
		public boolean isHidden(GameCharacter character) {
			return character.isDoll();
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
				return "No-one can work in Lilaya's lab while she is sleeping!";
			}
			return super.getAvailabilityText(hour, character);
		}
	},

	DOLL_STATUE(PresetColour.BASE_GOLD,
			0.5f,
			-1,
			0,
			"statue",
			"statue",
			"Command your doll to go to their assigned station and remain motionless there.",
			0f, 0f,
			0,
			0, 0,
			null,
			null,
			Util.newHashMapOfValues(
					new Value<>("Statue Pose", Util.newArrayListOfValues(
							SlaveJobSetting.DOLL_STATUE_ARTISTIC,
							SlaveJobSetting.DOLL_STATUE_ATTENTION,
							SlaveJobSetting.DOLL_STATUE_STANDING_SPLIT,
							SlaveJobSetting.DOLL_STATUE_MISSIONARY,
							SlaveJobSetting.DOLL_STATUE_ALL_FOURS,
							SlaveJobSetting.DOLL_STATUE_SQUATTING,
							SlaveJobSetting.DOLL_STATUE_BRIDGE))),
			Util.newArrayListOfValues(
					SlaveJobSetting.DOLL_STATUE_ARTISTIC),
			Util.newArrayListOfValues(),
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR) {
		@Override
		public boolean isHidden(GameCharacter character) {
			return !character.isDoll();
		}
		@Override
		public boolean isAvailable(int hour, GameCharacter character) {
			return !character.getHomeWorldLocation().equals(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"));
		}
		@Override
		public String getAvailabilityText(int hour, GameCharacter character) {
			if(character.getHomeWorldLocation().equals(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"))) {
				return "Dolls cannot work while being stored at Lovienne's Luxuries. Move them into a doll closet first!";
				
			}
			return "This job is available!";
		}
		@Override
		public void sendToWorkLocation(GameCharacter slave) {
			if(slave.getSlaveStationWorldType()==null || slave.getSlaveStationLocation()==null) {
				// Use slave's birthday as the seed so that it will always be the same (and different from other slaves):
				Random rnd = new Random(slave.getBirthday().getYear() + slave.getBirthday().getDayOfYear() + slave.getBirthday().getHour() + slave.getBirthday().getMinute());
				AbstractWorldType worldType = rnd.nextFloat()<0.5f?WorldType.LILAYAS_HOUSE_GROUND_FLOOR:WorldType.LILAYAS_HOUSE_FIRST_FLOOR;
				World world = Main.game.getWorlds().get(worldType);
				slave.setLocation(world.getRandomCell(PlaceType.LILAYA_HOME_CORRIDOR, rnd));
			} else {
				slave.setLocation(slave.getSlaveStationWorldType(), slave.getSlaveStationLocation(), false);
			}
		}
	},
	
	PUBLIC_STOCKS(PresetColour.BASE_PINK_LIGHT,
			0.5f,
			5,
			2f,
			"public fucktoy",
			"public fucktoy",
			"Assign this slave to be locked in the public-use stocks in slaver ally.",
			-5f, 1f,
			0,
			0, 0,
			Util.newArrayListOfValues(
					SlaveJobSetting.SEX_ORAL,
					SlaveJobSetting.SEX_VAGINAL,
					SlaveJobSetting.SEX_ANAL,
					SlaveJobSetting.SEX_NIPPLES),
			Util.newArrayListOfValues(
					SlaveJobSetting.SEX_ORAL,
					SlaveJobSetting.SEX_VAGINAL,
					SlaveJobSetting.SEX_ANAL),
			null,
			null,
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS),
			WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS) {
		@Override
		public float getAffectionGain(GameCharacter slave) {
			if(slave.isDoll()) {
				return 0;
			}
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
			Util.newArrayListOfValues(
					SlaveJobSetting.SEX_ORAL,
					SlaveJobSetting.SEX_VAGINAL,
					SlaveJobSetting.SEX_ANAL),
			null,
			null,
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS,
					SlaveJobFlag.INTERACTION_BONDING),
			WorldType.ANGELS_KISS_FIRST_FLOOR, PlaceType.ANGELS_KISS_BEDROOM) {
		
		@Override
		public float getAffectionGain(GameCharacter slave) {
			if(slave.isDoll()) {
				return 0;
			}
			if(slave.hasTraitActivated(Perk.NYMPHOMANIAC)) {
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
				
			} else if(Main.game.getOccupancyUtil().getCharactersWorkingJob(hour, this)>=this.getSlaveLimit()) {
				return "You have already assigned the maximum number of slaves to this job!";
				
			} else if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)) {
				return "Slaves cannot work out of the cells at slavery administration. Move them into a room first!";
				
			} else if(character.getHomeWorldLocation().equals(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"))) {
				return "Dolls cannot work while being stored at Lovienne's Luxuries. Move them into a doll closet first!";
				
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
			"Assign this slave to the milking stalls, ready to have their milk, cum, and/or girlcum milked from them.",
			-0.25f, 1f,
			0,
			0, 0,
			Util.newArrayListOfValues(
					SlaveJobSetting.MILKING_MILK,
					SlaveJobSetting.MILKING_MILK_CROTCH,
					SlaveJobSetting.MILKING_CUM,
					SlaveJobSetting.MILKING_GIRLCUM,
					SlaveJobSetting.MILKING_MILK_AUTO_SELL,
					SlaveJobSetting.MILKING_MILK_CROTCH_AUTO_SELL,
					SlaveJobSetting.MILKING_CUM_AUTO_SELL,
					SlaveJobSetting.MILKING_GIRLCUM_AUTO_SELL,
					SlaveJobSetting.MILKING_TEAR_HYMEN),
			Util.newArrayListOfValues(
					SlaveJobSetting.MILKING_MILK,
					SlaveJobSetting.MILKING_MILK_CROTCH,
					SlaveJobSetting.MILKING_CUM,
					SlaveJobSetting.MILKING_GIRLCUM),
			Util.newHashMapOfValues(
					new Value<>("Room Preference", Util.newArrayListOfValues(
							SlaveJobSetting.MILKING_INDUSTRIAL,
							SlaveJobSetting.MILKING_REGULAR,
							SlaveJobSetting.MILKING_ARTISAN,
							SlaveJobSetting.MILKING_NO_PREFERENCE))),
			Util.newArrayListOfValues(
					SlaveJobSetting.MILKING_NO_PREFERENCE),
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS,
					SlaveJobFlag.INTERACTION_BONDING),
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR) {
		@Override
		public int getSlaveLimit() {
			return Main.game.getOccupancyUtil().getMilkingRooms().size()*8;
		}
		@Override
		public float getAffectionGain(GameCharacter slave) {
			if(slave.isDoll()) {
				return 0;
			}
			float aff = this.affectionGain;
			if((slave.hasFetish(Fetish.FETISH_LACTATION_SELF) && (slave.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_MILK) || slave.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_MILK_CROTCH)))
					|| (slave.hasFetish(Fetish.FETISH_CUM_STUD) && slave.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_CUM))) {
				aff = 0.25f;
			}
			Cell c = this.getWorkDestinationCell(slave);
			return aff + (c==null?0:c.getPlace().getHourlyAffectionChange());
		}
		@Override
		public boolean isAvailable(int hour, GameCharacter character) {
			return !character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)
					&& !character.getHomeWorldLocation().equals(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"))
					&& Main.game.getOccupancyUtil().getCharactersWorkingJob(hour, SlaveJob.MILKING)<getSlaveLimit();
		}
		@Override
		public String getAvailabilityText(int hour, GameCharacter character) {
			if(!isAvailable(hour, character)) {
				return "Not enough space in milking rooms!";
				
			} else if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)) {
				return "Slaves cannot work out of the cells at slavery administration. Move them into a room first!";
				
			} else if(character.getHomeWorldLocation().equals(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"))) {
				return "Dolls cannot work while being stored at Lovienne's Luxuries. Move them into a doll closet first!";
				
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
		public Cell getWorkDestinationCell(GameCharacter slave) {
			return MilkingRoom.getMilkingCell(slave, false);
		}
		@Override
		public void sendToWorkLocation(GameCharacter slave) {
			Cell c = MilkingRoom.getMilkingCell(slave, false);
			if(c!=null) {
				if(c.getType()!=slave.getWorldLocation() || c.getLocation()!=slave.getLocation()) {
					slave.setLocation(c.getType(), c.getLocation(), false);
				}
				
			} else {
				slave.returnToHome();
			}
		}
		@Override
		public void applyJobStartEffects(GameCharacter slave) {
			Cell c = MilkingRoom.getMilkingCell(slave, false);
			if(c!=null) {
				List<AbstractClothing> clothingRemoved = new ArrayList<>();
				boolean equipBreastPumps = false;
				boolean equipUdderPumps = false;
				boolean equipPenisPump = false;
				boolean equipVaginaPump = false;
				
				if(MilkingRoom.getActualMilkPerHour(slave)>0 && slave.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_MILK)) {
					clothingRemoved.addAll(slave.displaceClothingForAccess(CoverableArea.NIPPLES, null, true, false).keySet());
					AbstractClothing clothing = slave.getClothingInSlot(InventorySlot.NIPPLE);
					if(clothing!=null) {
						if(!clothing.isMilkingEquipment()) {
							clothingRemoved.add(clothing);
							slave.unequipClothingIntoInventory(clothing, true, slave);
							equipBreastPumps = true;
						}
					} else {
						equipBreastPumps = true;
					}
				}
				if(MilkingRoom.getActualCrotchMilkPerHour(slave)>0 && slave.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_MILK_CROTCH)) {
					clothingRemoved.addAll(slave.displaceClothingForAccess(CoverableArea.NIPPLES_CROTCH, null, true, false).keySet());
					AbstractClothing clothing = slave.getClothingInSlot(InventorySlot.STOMACH);
					if(clothing!=null) {
						if(!clothing.isMilkingEquipment()) {
							clothingRemoved.add(clothing);
							slave.unequipClothingIntoInventory(clothing, true, slave);
							equipUdderPumps = true;
						}
					} else {
						equipUdderPumps = true;
					}
				}
				if(MilkingRoom.getActualCumPerHour(slave)>0 && slave.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_CUM)) {
					clothingRemoved.addAll(slave.displaceClothingForAccess(CoverableArea.PENIS, null, true, false).keySet());
					AbstractClothing clothing = slave.getClothingInSlot(InventorySlot.PENIS);
					if(clothing!=null) {
						if(!clothing.isMilkingEquipment()) {
							clothingRemoved.add(clothing);
							slave.unequipClothingIntoInventory(clothing, true, slave);
							equipPenisPump = true;
						}
					} else {
						equipPenisPump = true;
					}
				}
				if(MilkingRoom.getActualGirlcumPerHour(slave)>0
						&& slave.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_GIRLCUM)
						&& (!slave.hasHymen() || slave.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_TEAR_HYMEN))) {
					clothingRemoved.addAll(slave.displaceClothingForAccess(CoverableArea.VAGINA, null, true, false).keySet());
					AbstractClothing clothing = slave.getClothingInSlot(InventorySlot.VAGINA);
					if(clothing!=null) {
						if(!clothing.isMilkingEquipment()) {
							clothingRemoved.add(clothing);
							slave.unequipClothingIntoInventory(clothing, true, slave);
							equipVaginaPump = true;
						}
					} else {
						equipVaginaPump = true;
					}
				}
				
				if(equipBreastPumps) {
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.NIPPLE, true, slave);
				}
				if(equipUdderPumps) {
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.STOMACH, true, slave);
				}
				if(equipPenisPump) {
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_penis_pump"), false), true, slave);
				}
				if(equipVaginaPump) {
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_vagina_pump"), false), true, slave);
				}
				
				MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(c.getType(), c.getLocation());
				
				for(AbstractClothing cl : clothingRemoved) {
					if(cl.isMilkingEquipment()) {
						slave.removeClothing(cl);
					}
				}
				
				clothingRemoved.removeIf(cl->cl.isMilkingEquipment());
				
				for(AbstractClothing clothing : clothingRemoved) {
					if(clothing.isMilkingEquipment()) {
						throw new IllegalAccessError();
					}
					room.addClothingRemovedForMilking(slave, clothing);
				}
			}
		}
		@Override
		public void applyJobEndEffects(GameCharacter slave) {
//			Cell c = MilkingRoom.getMilkingCell(slave, true);
//			System.out.println("1: "+slave.getName());
//			if(c!=null) {
//				System.out.println("2: "+slave.getName());
				MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(slave.getWorldLocation(), slave.getLocation());
				
				if(room!=null) {
					AbstractClothing pump = slave.getClothingInSlot(InventorySlot.NIPPLE);
					if(pump!=null && pump.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"))) {
						slave.forceUnequipClothingIntoVoid(slave, pump);
					}
					pump = slave.getClothingInSlot(InventorySlot.STOMACH);
					if(pump!=null && pump.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"))) {
						slave.forceUnequipClothingIntoVoid(slave, pump);
					}
					pump = slave.getClothingInSlot(InventorySlot.PENIS);
					if(pump!=null && pump.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_milking_penis_pump"))) {
						slave.forceUnequipClothingIntoVoid(slave, pump);
					}
					pump = slave.getClothingInSlot(InventorySlot.VAGINA);
					if(pump!=null && pump.getClothingType().equals(ClothingType.getClothingTypeFromId("innoxia_milking_vagina_pump"))) {
						slave.forceUnequipClothingIntoVoid(slave, pump);
					}
					
					List<AbstractClothing> clothingRemoved = room.getClothingRemovedForMilking().get(slave.getId());
					if(clothingRemoved!=null) {
						for(AbstractClothing clothing : clothingRemoved) {
							if(slave.getClothingCurrentlyEquipped().contains(clothing)) {
								for(DisplacementType dt : new ArrayList<>(clothing.getDisplacedList())) {
									slave.isAbleToBeReplaced(slave.getClothingInSlot(clothing.getSlotEquippedTo()), dt, true, true, slave);
								}
							} else if(slave.hasClothing(clothing)) {
								slave.equipClothingFromInventory(clothing, true, slave, slave);
							} else if(slave.getCell().getInventory().hasClothing(clothing)) {
								slave.equipClothingFromGround(clothing, true, slave);
							}
						}
						room.clearClothingRemovedForMilking(slave);
					}
				}
//			}
		}
	},
	
	OFFICE(PresetColour.BASE_LILAC,
			0.05f,
			4,
			2f,
			"office worker",
			"office worker",
			"Assign this character to work in the office which you've had outfitted here in Lilaya's house.",
			0, 0,
			100,
			0, 1f,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS,
					SlaveJobFlag.INTERACTION_SEX,
					SlaveJobFlag.INTERACTION_BONDING,
					SlaveJobFlag.GUEST_CAN_WORK),
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
			return !character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)
					&& !character.getHomeWorldLocation().equals(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"))
					&& Main.game.getOccupancyUtil().getCharactersWorkingJob(hour, SlaveJob.OFFICE) < getSlaveLimit();
		}
	
		public String getAvailabilityText(int hour, GameCharacter character) {
			if(!isAvailable(hour, character)) {
				return "There isn't enough office space to assign this job!";
				
			} else if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)) {
				return "Slaves cannot work out of the cells at slavery administration. Move them into a room first!";
				
			} else if(character.getHomeWorldLocation().equals(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"))) {
				return "Dolls cannot work while being stored at Lovienne's Luxuries. Move them into a doll closet first!";
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
		public Cell getWorkDestinationCell(GameCharacter slave) {
			return getOfficeCell();
		}
		
		@Override
		public void sendToWorkLocation(GameCharacter slave) {
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
			0,
			"bedroom slave",
			"bedroom slave",
			"Assign this slave to wait upon you in your bedroom.",
			0, 0.25f,
			0,
			0, 0,
			Util.newArrayListOfValues(
					SlaveJobSetting.BEDROOM_GREETING,
					SlaveJobSetting.BEDROOM_CLEAN,
					SlaveJobSetting.BEDROOM_WAKE_UP,
					SlaveJobSetting.BEDROOM_HELP_WASH),
			Util.newArrayListOfValues(
					SlaveJobSetting.BEDROOM_GREETING,
					SlaveJobSetting.BEDROOM_CLEAN),
			Util.newHashMapOfValues(
					new Value<>("Sleeping Arrangements",
						Util.newArrayListOfValues(
							SlaveJobSetting.BEDROOM_SLEEP_FLOOR,
							SlaveJobSetting.BEDROOM_SLEEP_ON_BED,
							SlaveJobSetting.BEDROOM_SLEEP_IN_BED))),
			Util.newArrayListOfValues(
					SlaveJobSetting.BEDROOM_SLEEP_ON_BED),
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS,
					SlaveJobFlag.INTERACTION_SEX,
					SlaveJobFlag.INTERACTION_BONDING),
			WorldType.LILAYAS_HOUSE_FIRST_FLOOR,
			PlaceType.LILAYA_HOME_ROOM_PLAYER),
	
	SPA(PresetColour.BASE_AQUA,
			0.05f,
			8,
			1.5f,
			"Spa servant",
			"Spa servant",
			"Assign this slave to your private spa, ready to give you a massage or tend to any of your needs.",
			0.5f, -0.1f,
			0,
			0, 0,
			Util.newArrayListOfValues(
					SlaveJobSetting.SPA_BATHING,
					SlaveJobSetting.SPA_STRIP_TO_BATHE,
					SlaveJobSetting.SPA_MASSAGE
//					SlaveJobSetting.SPA_SAUNA,
//					SlaveJobSetting.SPA_POOL
					),
			Util.newArrayListOfValues(
					SlaveJobSetting.SPA_BATHING,
					SlaveJobSetting.SPA_STRIP_TO_BATHE,
					SlaveJobSetting.SPA_MASSAGE),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(),
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS,
					SlaveJobFlag.INTERACTION_SEX,
					SlaveJobFlag.INTERACTION_BONDING),
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			PlaceType.LILAYA_HOME_SPA) {
		@Override
		public int getSlaveLimit() {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return 0;
			}
			return super.getSlaveLimit();
		}
		@Override
		public boolean isAvailable(int hour, GameCharacter character) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return false;
			}
			return super.isAvailable(hour, character);
		}
		@Override
		public String getAvailabilityText(int hour, GameCharacter character) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return "The spa upgrade must be constructed before this job is available!";
			}
			return super.getAvailabilityText(hour, character);
		}
		@Override
		public AbstractWorldType getWorldLocation(GameCharacter character) {
			return WorldType.LILAYAS_HOUSE_GROUND_FLOOR;
		}
	},

	SPA_RECEPTIONIST(PresetColour.BASE_BLUE_STEEL,
			0.05f,
			2,
			2f,
			"Spa clerk",
			"Spa clerk",
			"Assign this slave to work on the reception desk of your private spa.",
			0, 0.05f,
			0,
			0, 0,
			Util.newArrayListOfValues(
					SlaveJobSetting.SPA_SHOWERING),
			null,
			null,
			null,
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS,
					SlaveJobFlag.INTERACTION_SEX,
					SlaveJobFlag.INTERACTION_BONDING),
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR,
			PlaceType.LILAYA_HOME_SPA) {
		@Override
		public int getSlaveLimit() {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return 0;
			}
			return super.getSlaveLimit();
		}
		@Override
		public boolean isAvailable(int hour, GameCharacter character) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return false;
			}
			return super.isAvailable(hour, character);
		}
		@Override
		public String getAvailabilityText(int hour, GameCharacter character) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return "The spa upgrade must be constructed before this job is available!";
			}
			return super.getAvailabilityText(hour, character);
		}
		@Override
		public AbstractWorldType getWorldLocation(GameCharacter character) {
			return WorldType.LILAYAS_HOUSE_GROUND_FLOOR;
		}
		@Override
		public AbstractPlaceType getPlaceLocation(GameCharacter character) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_SPA).isEmpty()) {
				return PlaceType.LILAYA_HOME_FOUNTAIN;
			}
			return Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_SPA).get(0).getPlace().getPlaceType();
		}
		@Override
		public Cell getWorkDestinationCell(GameCharacter slave) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_SPA).isEmpty()) {
				return Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceType.LILAYA_HOME_FOUNTAIN).get(0);
			}
			return Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_SPA).get(0);
		}
		@Override
		public void sendToWorkLocation(GameCharacter slave) {
			Cell c = getWorkDestinationCell(slave);
			if(c!=null) {
				if(c.getType()!=slave.getWorldLocation() || c.getLocation()!=slave.getLocation()) {
					slave.setLocation(c.getType(), c.getLocation(), false);
				}
				
			} else {
				slave.returnToHome();
			}
		}
	},

	DINING_HALL(PresetColour.BASE_ORANGE_LIGHT,
			0.05f,
			6,
			2f,
			"waitress",
			"waiter",
			"Assign this character to serve food in a dining hall.",
			0, 0.5f,
			50,
			0, 0.05f,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					SlaveJobFlag.EXPERIENCE_GAINS,
					SlaveJobFlag.INTERACTION_SEX,
					SlaveJobFlag.INTERACTION_BONDING,
					SlaveJobFlag.GUEST_CAN_WORK),
			WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR) {
		@Override
		public int getSlaveLimit() {
			return (Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_DINING_HALL).size()
					+ Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(PlaceUpgrade.LILAYA_DINING_HALL).size())
					* 6;
		}
		private Cell getDiningHallCell() {
			List<Cell> cells = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_DINING_HALL);
			cells.addAll(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(PlaceUpgrade.LILAYA_DINING_HALL));
			if(!cells.isEmpty()) {
				cells.sort((c1, c2) -> Main.game.getCharactersPresent(c1).size() - Main.game.getCharactersPresent(c2).size());
				return cells.get(0); // Return dining hall with the least amount of workers in it
			}
			return null;
		}
		@Override
		public AbstractWorldType getWorldLocation(GameCharacter character) {
			Cell c = getDiningHallCell();
			if(c==null) {
				return null;
			}
			return c.getType();
		}
		@Override
		public AbstractPlaceType getPlaceLocation(GameCharacter character) {
			Cell c = getDiningHallCell();
			if(c==null) {
				return null;
			}
			return c.getPlace().getPlaceType();
		}
		@Override
		public Cell getWorkDestinationCell(GameCharacter slave) {
			return getDiningHallCell();
		}
		@Override
		public void sendToWorkLocation(GameCharacter slave) {
			Cell c = getDiningHallCell();
			if(c!=null) {
				if(c.getType()!=slave.getWorldLocation() || c.getLocation()!=slave.getLocation()) {
					if(Main.game.getCharactersPresent(c).size() < Main.game.getCharactersPresent(slave.getCell()).size() - 1 || !slave.getCell().getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_DINING_HALL)) {
						slave.setLocation(c.getType(), c.getLocation(), false);
					}
				}
			} else {
				slave.returnToHome();
			}
		}
		@Override
		public boolean isAvailable(int hour, GameCharacter character) {
			if(getDiningHallCell()==null) {
				return false;
			}
			return super.isAvailable(hour, character);
		}
		@Override
		public String getAvailabilityText(int hour, GameCharacter character) {
			if(getDiningHallCell()==null) {
				return "The dining hall upgrade must be constructed before this job is available!";
			}
			return super.getAvailabilityText(hour, character);
		}
	};
	
	private Colour colour;
	private float hourlyEventChance;
	private int slaveLimit;
	private float hourlyStaminaDrain;
	private String nameFeminine;
	private String nameMasculine;
	private String description;
	private float obedienceGain;
	protected float affectionGain;
	private int income;
	private float obedienceIncomeModifier;
	private float affectionIncomeModifier;
	private List<SlaveJobSetting> mutualSettings;
	private List<SlaveJobSetting> defaultMutualSettings;
	private Map<String, List<SlaveJobSetting>> mutuallyExclusiveSettings;
	private List<SlaveJobSetting> defaultMutuallyExclusiveSettings;
	private List<SlaveJobFlag> flags;
	private AbstractWorldType worldLocation;
	private AbstractPlaceType placeLocation;
	
	private SlaveJob(
			Colour colour,
			float hourlyEventChance,
			int slaveLimit,
			float hourlyStaminaDrain,
			String nameFeminine,
			String nameMasculine,
			String description,
			float affectionGain,
			float obedienceGain,
			int income,
			float affectionIncomeModifier,
			float obedienceIncomeModifier,
			List<SlaveJobSetting> mutualSettings,
			List<SlaveJobSetting> defaultMutualSettings,
			Map<String, List<SlaveJobSetting>> mutuallyExclusiveSettings,
			List<SlaveJobSetting> defaultMutuallyExclusiveSettings,
			List<SlaveJobFlag> flags,
			AbstractWorldType worldLocation,
			AbstractPlaceType placeLocation) {
		this.colour = colour;
		this.hourlyEventChance = hourlyEventChance;
		this.slaveLimit = slaveLimit;
		this.hourlyStaminaDrain = hourlyStaminaDrain;
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

		if(defaultMutualSettings == null) {
			this.defaultMutualSettings = new ArrayList<>();
		} else {
			this.defaultMutualSettings = defaultMutualSettings;
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

		if(flags == null) {
			this.flags = new ArrayList<>();
		} else {
			this.flags = flags;
		}
		Collections.sort(this.flags);
		
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
	
	public float getHourlyStaminaDrain(GameCharacter character) {
		if(character.isDoll()) {
			return 0;
		}
		return hourlyStaminaDrain;
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
		if(slave.isDoll()) {
			return 0;
		}
		Cell c = this.getWorkDestinationCell(slave);
		return obedienceGain + (c==null?0:c.getPlace().getHourlyObedienceChange());
	}

	public float getAffectionGain(GameCharacter slave) {
		if(slave.isDoll()) {
			return 0;
		}
		Cell c = this.getWorkDestinationCell(slave);
		return affectionGain + (c==null?0:c.getPlace().getHourlyAffectionChange());
	}

	public int getIncome() {
		return income;
	}
	
	public int getFinalHourlyIncomeAfterModifiers(GameCharacter character) {
		int value = (int)(Math.max(0, (income + ((getAffectionIncomeModifier()*character.getAffection(Main.game.getPlayer()))) + ((getObedienceIncomeModifier()*character.getObedienceValue())))));
		
		if(this==SlaveJob.MILKING) {
			value = 0;
			if(character.getBreastRawStoredMilkValue()>0
					&& character.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_MILK)
					&& character.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_MILK_AUTO_SELL)) {
				int milked = MilkingRoom.getActualMilkPerHour(character);
				value += (milked * character.getMilk().getValuePerMl());
			}
			if(character.hasBreastsCrotch()
					&& character.getBreastCrotchRawStoredMilkValue()>0
					&& character.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_MILK_CROTCH)
					&& character.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_MILK_CROTCH_AUTO_SELL)) {
				int milked = MilkingRoom.getActualCrotchMilkPerHour(character);
				value += (milked * character.getMilkCrotch().getValuePerMl());
			}
			if(character.hasPenis()
					&& character.getPenisRawStoredCumValue()>0
					&& character.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_CUM)
					&& character.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_CUM_AUTO_SELL)) {
				int milked = MilkingRoom.getActualCumPerHour(character);
				value += (milked * character.getCum().getValuePerMl());
			}
			if(character.hasVagina()
					&& character.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_GIRLCUM)
					&& character.hasSlaveJobSetting(this, SlaveJobSetting.MILKING_GIRLCUM_AUTO_SELL)) {
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

	public List<SlaveJobSetting> getDefaultMutualSettings() {
		return defaultMutualSettings;
	}
	
	public Map<String, List<SlaveJobSetting>> getMutuallyExclusiveSettings() {
		return mutuallyExclusiveSettings;
	}
	
	public List<SlaveJobSetting> getDefaultMutuallyExclusiveSettings() {
		return defaultMutuallyExclusiveSettings;
	}

	public List<SlaveJobFlag> getFlags() {
		return flags;
	}

	public boolean hasFlag(SlaveJobFlag flag) {
		return flags.contains(flag);
	}
	
	public AbstractWorldType getWorldLocation(GameCharacter character) {
		return worldLocation;
	}

	public AbstractPlaceType getPlaceLocation(GameCharacter character) {
		return placeLocation;
	}
	
	public Cell getWorkDestinationCell(GameCharacter slave) {
		AbstractWorldType wType = this.getWorldLocation(slave);
		if(wType==null) {
			return null;
		}
		return Main.game.getWorlds().get(wType).getRandomUnoccupiedCell(this.getPlaceLocation(slave));
	}
	
	public void sendToWorkLocation(GameCharacter slave) {
		if(this.getWorldLocation(slave)!=null
				&& this.getPlaceLocation(slave)!=null
				&& (this.getWorldLocation(slave)!=slave.getWorldLocation() || this.getPlaceLocation(slave)!=slave.getLocationPlace().getPlaceType())) {
			slave.setRandomUnoccupiedLocation(this.getWorldLocation(slave), this.getPlaceLocation(slave), false);
		}
	}
	
	/**
	 * @return true if the job is available at the supplied hour.
	 */
	public boolean isAvailable(int hour, GameCharacter character) {
		return character.getSlaveJob(hour)==this
				|| (!character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)
						&& !character.getHomeWorldLocation().equals(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"))
						&& Main.game.getOccupancyUtil().getCharactersWorkingJob(hour, this)<this.getSlaveLimit());
	}
	
	/**
	 * @return true if the job should be completely unavailable to the supplied character, and be hidden from job options as a result.
	 */
	public boolean isHidden(GameCharacter character) {
		return false;
	}
	
	public String getAvailabilityText(int hour, GameCharacter character) {
		if(Main.game.getOccupancyUtil().getCharactersWorkingJob(hour, this)>=this.getSlaveLimit()) {
			return "You have already assigned the maximum number of people to this job!";
			
		} else if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)) {
			return "Slaves cannot work out of the cells at slavery administration. Move them into a room first!";
			
		} else if(character.getHomeWorldLocation().equals(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"))) {
			return "Dolls cannot work while being stored at Lovienne's Luxuries. Move them into a doll closet first!";
			
		} else if(!character.isSlave() && character.isSleepingAtHour(hour)){
			return UtilText.parse(character, "[npc.Name] is sleeping at this hour, and as [npc.she] is not your slave, you cannot force [npc.herHim] to work at this time!");
			
		} else {
			return "This job is unavailable!";
		}
	}
	
	public EventLogEntry getHourlyEvent(long hour, NPC slave, List<NPC> otherNPCsPresent) {
//		return new EventLogEntry(Main.game.getDayNumber()-1*24l+hour, "[style.colourDisabled(Nothing)]", "");
		return null;
	}
	
	/**
	 * Called immediately before the character is moved to their new job.
	 */
	public void applyJobStartEffects(GameCharacter slave) {
	}

	/**
	 * Called immediately after the character is moved to their new job.
	 */
	public void applyJobEndEffects(GameCharacter slave) {
	}
	
}
