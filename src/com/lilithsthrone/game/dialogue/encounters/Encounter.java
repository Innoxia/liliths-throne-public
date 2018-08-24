package com.lilithsthrone.game.dialogue.encounters;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.GenderPreference;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.DominionSuccubusAttacker;
import com.lilithsthrone.game.character.npc.dominion.HarpyNestsAttacker;
import com.lilithsthrone.game.character.npc.dominion.Lumi;
import com.lilithsthrone.game.character.npc.dominion.RentalMommy;
import com.lilithsthrone.game.character.npc.submission.BatMorphCavernAttacker;
import com.lilithsthrone.game.character.npc.submission.SlimeCavernAttacker;
import com.lilithsthrone.game.character.npc.submission.SubmissionAttacker;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.occupantManagement.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.2.5
 * @author Innoxia
 */
public enum Encounter {

	LILAYAS_HOME_CORRIDOR(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f))) {
		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			if(node == EncounterType.SLAVE_USES_YOU && Main.game.getNonCompanionCharactersPresent().isEmpty()) {
				
				List<NPC> slaves = new ArrayList<>();
				List<NPC> hornySlaves = new ArrayList<>();
				
				for(String id : Main.game.getPlayer().getSlavesOwned()) {
					NPC slave = (NPC) Main.game.getNPCById(id);
					if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER)
							&& !slave.getWorkHours()[(int) (Main.game.getHour()%24)]
							&& slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_HOUSE_FREEDOM)
							&& slave.isAttractedTo(Main.game.getPlayer())) {
						if(slave.getLastTimeHadSex()+60*4<Main.game.getMinutesPassed()) {
							slaves.add(slave);
						}
						if(slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)) {
							hornySlaves.add(slave);
						}
					}
				}
				slaves.removeIf((slave) -> slave.getWorldLocation()==WorldType.SLAVER_ALLEY);
				hornySlaves.removeIf((slave) -> slave.getWorldLocation()==WorldType.SLAVER_ALLEY);
				
				if(!hornySlaves.isEmpty()) {
					Collections.shuffle(hornySlaves);
					Main.game.setActiveNPC(hornySlaves.get(0));
					Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
					return SlaveDialogue.SLAVE_USES_YOU;
					
				} else if(!slaves.isEmpty()) {
					Collections.shuffle(slaves);
					Main.game.setActiveNPC(slaves.get(0));
					Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
					return SlaveDialogue.SLAVE_USES_YOU;
				}
				
				return null;
				
			} else {
				return null;
			}
		}
	},
	
	
	DOMINION_STREET(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.DOMINION_STORM_ATTACK, 15f),
			new Value<EncounterType, Float>(EncounterType.SPECIAL_DOMINION_CULTIST, 5f),
			new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f),
			new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_FIND_HAPPINESS, 10f))) {
		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			if(node == EncounterType.DOMINION_STORM_ATTACK && Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				Main.game.setActiveNPC(new DominionAlleywayAttacker(GenderPreference.getGenderFromUserPreferences(false, false)));
	
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			} else if(node == EncounterType.SPECIAL_DOMINION_CULTIST
					&& Main.game.getCurrentWeather() != Weather.MAGIC_STORM
					&& Main.game.getDateNow().getMonth().equals(Month.OCTOBER)
					&& Main.game.getNonCompanionCharactersPresent().isEmpty()
					&& Main.game.getNumberOfWitches()<4
					&& Main.game.getPlayerCell().getPlace().getPlaceType() == PlaceType.DOMINION_STREET) {
				
				Main.game.setActiveNPC(new Cultist());
				
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
	
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			} else if(node == EncounterType.DOMINION_STREET_FIND_HAPPINESS
					&& Main.game.getPlayer().getName().equalsIgnoreCase("Kinariu")
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.foundHappiness)) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.foundHappiness, true);
				return DominionEncounterDialogue.DOMINION_STREET_FIND_HAPPINESS;
				
			} else if(node == EncounterType.SLAVE_USES_YOU && Main.game.getNonCompanionCharactersPresent().isEmpty() && Main.game.getCurrentWeather() != Weather.MAGIC_STORM) {
				List<NPC> slaves = new ArrayList<>();
				List<NPC> hornySlaves = new ArrayList<>();
				
				for(String id : Main.game.getPlayer().getSlavesOwned()) {
					NPC slave = (NPC) Main.game.getNPCById(id);
					if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER)
							&& !slave.getWorkHours()[(int) (Main.game.getHour()%24)]
							&& slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_OUTSIDE_FREEDOM)
							&& slave.isAttractedTo(Main.game.getPlayer())) {
						if(slave.getLastTimeHadSex()+60*4<Main.game.getMinutesPassed()) {
							slaves.add(slave);
						}
						if(slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)) {
							hornySlaves.add(slave);
						}
					}
				}
				slaves.removeIf((slave) -> slave.getWorldLocation()==WorldType.SLAVER_ALLEY || slave.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative());
				hornySlaves.removeIf((slave) -> slave.getWorldLocation()==WorldType.SLAVER_ALLEY || slave.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative());
				
				if(!hornySlaves.isEmpty()) {
					Collections.shuffle(hornySlaves);
					Main.game.setActiveNPC(hornySlaves.get(0));
					Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
					return SlaveDialogue.SLAVE_USES_YOU_STREETS;
					
				} else if(!slaves.isEmpty()) {
					Collections.shuffle(slaves);
					Main.game.setActiveNPC(slaves.get(0));
					Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
					return SlaveDialogue.SLAVE_USES_YOU_STREETS;
				}
				
				return null;
				
			} else {
				return null;
			}
		}
	},
	
	DOMINION_BOULEVARD(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_RENTAL_MOMMY, 10f),
			new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f))) {
		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			if(node == EncounterType.DOMINION_STREET_RENTAL_MOMMY) {
				LocalDateTime time = Main.game.getDateNow();
				
				if(time.getMonth().equals(Month.MAY) /*&& time.getDayOfWeek().equals(DayOfWeek.SUNDAY)*/ && time.getDayOfMonth()>7 && time.getDayOfMonth()<=14
						&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.mommyFound)
						&& Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
					
					Main.game.setActiveNPC(new RentalMommy());
					
					try {
						Main.game.addNPC(Main.game.getActiveNPC(), false);
					} catch (Exception e) {
						e.printStackTrace();
					}
		
					return Main.game.getActiveNPC().getEncounterDialogue();
					
				}
				
			} else if(node == EncounterType.SLAVE_USES_YOU && Main.game.getNonCompanionCharactersPresent().isEmpty() && Main.game.getCurrentWeather() != Weather.MAGIC_STORM) {
				List<NPC> slaves = new ArrayList<>();
				List<NPC> hornySlaves = new ArrayList<>();
				
				for(String id : Main.game.getPlayer().getSlavesOwned()) {
					NPC slave = (NPC) Main.game.getNPCById(id);
					if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER)
							&& !slave.getWorkHours()[(int) (Main.game.getHour()%24)]
							&& slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_OUTSIDE_FREEDOM)
							&& slave.isAttractedTo(Main.game.getPlayer())) {
						if(slave.getLastTimeHadSex()+60*4<Main.game.getMinutesPassed()) {
							slaves.add(slave);
						}
						if(slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)) {
							hornySlaves.add(slave);
						}
					}
				}
				slaves.removeIf((slave) -> slave.getWorldLocation()==WorldType.SLAVER_ALLEY || slave.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative());
				hornySlaves.removeIf((slave) -> slave.getWorldLocation()==WorldType.SLAVER_ALLEY || slave.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative());
				
				if(!hornySlaves.isEmpty()) {
					Collections.shuffle(hornySlaves);
					Main.game.setActiveNPC(hornySlaves.get(0));
					Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
					return SlaveDialogue.SLAVE_USES_YOU_STREETS;
					
				} else if(!slaves.isEmpty()) {
					Collections.shuffle(slaves);
					Main.game.setActiveNPC(slaves.get(0));
					Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
					return SlaveDialogue.SLAVE_USES_YOU_STREETS;
				}
				
				return null;
				
			}
			
			return null;
		}
	},

	DOMINION_ALLEY(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.DOMINION_ALLEY_ATTACK, 15f),
			new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_ITEM, 3f),
			new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_CLOTHING, 2f),
			new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_WEAPON, 1f))) {

		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			if (node == EncounterType.DOMINION_ALLEY_ATTACK) {
				
				// Prioritise re-encountering the NPC on this tile:
				for(NPC npc : Main.game.getNonCompanionCharactersPresent()) {
					if(npc instanceof Lumi) {
						return null;
					}
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
				if(Math.random()<IncestEncounterRate()) { // Incest
					List<NPC> offspringAvailable = UnspawnedChildren(
						npc-> (npc.getSubspecies().getWorldLocations().contains(WorldType.DOMINION) || npc.getSubspecies()==Subspecies.ANGEL));
					
					if(!offspringAvailable.isEmpty()) {
						return SpawnAndStartChildHere(offspringAvailable);
					}
				}
				
				Main.game.setActiveNPC(new DominionAlleywayAttacker(GenderPreference.getGenderFromUserPreferences(false, false)));
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return Main.game.getActiveNPC().getEncounterDialogue();
					

			} else if (node == EncounterType.DOMINION_FIND_ITEM) {
				
				if(Math.random()<0.995f) {
					randomItem = AbstractItemType.generateItem(ItemType.dominionAlleywayItems.get(Util.random.nextInt(ItemType.dominionAlleywayItems.size())));
				} else {
					randomItem = AbstractItemType.generateItem(ItemType.EGGPLANT);
				}
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem(randomItem);
				return DominionEncounterDialogue.ALLEY_FIND_ITEM;
				
			} else if (node == EncounterType.DOMINION_FIND_CLOTHING) {
				if(Math.random()<0.01f) {
					randomClothing = AbstractClothingType.generateClothing(ClothingType.MEGA_MILK);
					Main.game.getPlayerCell().getInventory().addClothing(randomClothing);
					
				} else {
					List<AbstractClothingType> randomClothingList = ClothingType.getAllClothing();
					randomClothingList.removeIf((clothing) ->
							(!clothing.getItemTags().contains(ItemTag.SOLD_BY_KATE)
							&& !clothing.getItemTags().contains(ItemTag.SOLD_BY_NYAN)
							&& !clothing.getItemTags().contains(ItemTag.DOMINION_ALLEYWAY_SPAWN))
							|| clothing.getRarity()==Rarity.EPIC
							|| clothing.getRarity()==Rarity.LEGENDARY);
					randomClothing = AbstractClothingType.generateClothing(randomClothingList.get(Util.random.nextInt(randomClothingList.size())));
					Main.game.getPlayerCell().getInventory().addClothing(randomClothing);
				}
				return DominionEncounterDialogue.ALLEY_FIND_CLOTHING;
				
			} else if (node == EncounterType.DOMINION_FIND_WEAPON) {
				randomWeapon = AbstractWeaponType.generateWeapon(WeaponType.rareWeapons.get(Util.random.nextInt(WeaponType.rareWeapons.size())));
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addWeapon(randomWeapon);
				return DominionEncounterDialogue.ALLEY_FIND_WEAPON;
				
			} else {
				return null;
			}
		}
	},
	
	DOMINION_DARK_ALLEY(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.DOMINION_ALLEY_ATTACK, 15f))) {

		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
				
			for (NPC npc : Main.game.getNonCompanionCharactersPresent()) {
				Main.game.setActiveNPC(npc);
				return Main.game.getActiveNPC().getEncounterDialogue();
			}
			
			Main.game.setActiveNPC(new DominionSuccubusAttacker());

			try {
				Main.game.addNPC(Main.game.getActiveNPC(), false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Main.game.getActiveNPC().getEncounterDialogue();
		}
	},
	
	//TODO
	DOMINION_CANAL(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.DOMINION_ALLEY_ATTACK, 8f))) {

		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			// Prioritise re-encountering the NPC on this tile:
			for(NPC npc : Main.game.getNonCompanionCharactersPresent()) {
				Main.game.setActiveNPC(npc);
				return Main.game.getActiveNPC().getEncounterDialogue();
			}
			
			if(Math.random()<IncestEncounterRate()) { // Incest
				List<NPC> offspringAvailable = UnspawnedChildren(
					npc -> npc.getSubspecies().getWorldLocations().contains(WorldType.DOMINION));
				
				if(!offspringAvailable.isEmpty()) {
					return SpawnAndStartChildHere(offspringAvailable);
				}
			}
			
			Main.game.setActiveNPC(new DominionAlleywayAttacker(GenderPreference.getGenderFromUserPreferences(false, false)));
			try {
				Main.game.addNPC(Main.game.getActiveNPC(), false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Main.game.getActiveNPC().getEncounterDialogue();
		}
	},
	
	HARPY_NEST_WALKWAYS(null) {
		
		@Override
		public Map<EncounterType, Float> getDialogues() {
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.HARPY_NEST_ATTACK_STORM, 12f),
						new Value<EncounterType, Float>(EncounterType.HARPY_NEST_FIND_ITEM, 4f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.HARPY_NEST_ATTACK, 12f),
						new Value<EncounterType, Float>(EncounterType.HARPY_NEST_FIND_ITEM, 4f));
			}
		}
		
		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			if (node == EncounterType.HARPY_NEST_ATTACK && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)) {
				
				for (NPC npc : Main.game.getNonCompanionCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
				if(Math.random()<IncestEncounterRate()) { // Incest
					List<NPC> offspringAvailable = UnspawnedChildren(
						npc -> npc.getSubspecies().getWorldLocations().contains(WorldType.HARPY_NEST));
					
					if(!offspringAvailable.isEmpty()) {
						return SpawnAndStartChildHere(offspringAvailable);
					}
				}

				Main.game.setActiveNPC(new HarpyNestsAttacker(GenderPreference.getGenderFromUserPreferences(false, false)));
				
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getLocation());
				
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return Main.game.getActiveNPC().getEncounterDialogue();
			}
			
			if (node == EncounterType.HARPY_NEST_ATTACK_STORM) {

				for (NPC npc : Main.game.getNonCompanionCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}

				Main.game.setActiveNPC(new HarpyNestsAttacker(GenderPreference.getGenderFromUserPreferences(false, false)));
				
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getLocation());
				
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return Main.game.getActiveNPC().getEncounterDialogue();
			}
			
			if (node == EncounterType.HARPY_NEST_FIND_ITEM) {
				
				if(Math.random() < 0.66) {
					randomItem = AbstractItemType.generateItem(ItemType.SEX_INGREDIENT_HARPY_PERFUME);
					
				} else {
					randomItem = AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_HARPY);
				}
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem(randomItem);
				return DominionEncounterDialogue.HARPY_NESTS_FIND_ITEM;
				
			}
			
			return null;
		}
	},
	
	HARPY_NEST_LOOK_FOR_TROUBLE(null) {
		
		@Override
		public Map<EncounterType, Float> getDialogues() {
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.HARPY_NEST_ATTACK_STORM, 12f),
						new Value<EncounterType, Float>(EncounterType.HARPY_NEST_FIND_ITEM, 4f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.HARPY_NEST_ATTACK, 12f),
						new Value<EncounterType, Float>(EncounterType.HARPY_NEST_FIND_ITEM, 4f));
			}
		}

		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			if (node == EncounterType.HARPY_NEST_ATTACK) {

				for (NPC npc : Main.game.getNonCompanionCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
				if(Math.random()<IncestEncounterRate()) { // Incest
					List<NPC> offspringAvailable = UnspawnedChildren(
						npc -> npc.getSubspecies().getWorldLocations().contains(WorldType.HARPY_NEST));
					
					if(!offspringAvailable.isEmpty()) {
						return SpawnAndStartChildHere(offspringAvailable);
					}
				}
				
				Main.game.setActiveNPC(new HarpyNestsAttacker(GenderPreference.getGenderFromUserPreferences(false, false)));
				
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getLocation());
				
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return Main.game.getActiveNPC().getEncounterDialogue();
			}
			
			if (node == EncounterType.HARPY_NEST_ATTACK_STORM) {

				for (NPC npc : Main.game.getNonCompanionCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}

				Main.game.setActiveNPC(new HarpyNestsAttacker(GenderPreference.getGenderFromUserPreferences(false, false)));
				
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getLocation());
				
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return Main.game.getActiveNPC().getEncounterDialogue();
			}
			
			if (node == EncounterType.HARPY_NEST_FIND_ITEM) {
				
				if(Math.random() < 0.66) {
					randomItem = AbstractItemType.generateItem(ItemType.SEX_INGREDIENT_HARPY_PERFUME);
					
				} else {
					randomItem = AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_HARPY);
				}
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem(randomItem);
				return DominionEncounterDialogue.HARPY_NESTS_FIND_ITEM;
				
			}
			
			return null;
		}
	},
	
	SUBMISSION_TUNNELS(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.SUBMISSION_TUNNEL_ATTACK, 20f),
			new Value<EncounterType, Float>(EncounterType.SUBMISSION_FIND_ITEM, 10f))) {

		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			if (node == EncounterType.SUBMISSION_TUNNEL_ATTACK) {
				
				// Prioritise re-encountering the NPC on this tile:
				for(NPC npc : Main.game.getNonCompanionCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
				if(Math.random()<IncestEncounterRate()) {
					List<NPC> offspringAvailable = UnspawnedChildren(
						npc -> npc.getSubspecies().getWorldLocations().contains(WorldType.SUBMISSION));
					
					if(!offspringAvailable.isEmpty()) {
						return SpawnAndStartChildHere(offspringAvailable);
					}
				}
				
				Main.game.setActiveNPC(new SubmissionAttacker(GenderPreference.getGenderFromUserPreferences(false, false)));
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			} else if (node == EncounterType.SUBMISSION_FIND_ITEM) {
				
				randomItem = AbstractItemType.generateItem(ItemType.submissionTunnelItems.get(Util.random.nextInt(ItemType.submissionTunnelItems.size())));
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem(randomItem);
				return SubmissionEncounterDialogue.FIND_ITEM;
				
			} else {
				return null;
			}
		}
	},
	
	BAT_CAVERN(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.BAT_CAVERN_BAT_ATTACK, 8f),
			new Value<EncounterType, Float>(EncounterType.BAT_CAVERN_SLIME_ATTACK, 6f),
			new Value<EncounterType, Float>(EncounterType.BAT_CAVERN_FIND_ITEM, 6f))) {

		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			if (node == EncounterType.BAT_CAVERN_BAT_ATTACK) {
				
				// Prioritise re-encountering the NPC on this tile:
				for(NPC npc : Main.game.getNonCompanionCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
//				TODO Add offspring encounters
				
				Main.game.setActiveNPC(new BatMorphCavernAttacker(GenderPreference.getGenderFromUserPreferences(false, false)));
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			} else if (node == EncounterType.BAT_CAVERN_SLIME_ATTACK) {
				
				// Prioritise re-encountering the NPC on this tile:
				for(NPC npc : Main.game.getNonCompanionCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
//				TODO Add offspring encounters
				
				Main.game.setActiveNPC(new SlimeCavernAttacker(GenderPreference.getGenderFromUserPreferences(false, false)));
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			} else if (node == EncounterType.BAT_CAVERN_FIND_ITEM) {
				
				randomItem = AbstractItemType.generateItem(ItemType.batCavernItems.get(Util.random.nextInt(ItemType.batCavernItems.size())));

				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem(randomItem);
				return BatCavernsEncounterDialogue.FIND_ITEM;
				
			} else {
				return null;
			}
		}
	};

	private static List<NPC> UnspawnedChildren(Predicate<NPC> matcher) {
		List<NPC> offspringAvailable = Main.game.getOffspring().stream().filter(npc -> !npc.isSlave())
										.filter(npc -> npc.getWorldLocation()==WorldType.EMPTY)
										.filter(npc -> npc.getLastTimeEncountered()==NPC.DEFAULT_TIME_START_VALUE)
										.filter(matcher).collect(Collectors.toList());
		return offspringAvailable;
	}

	private static DialogueNodeOld SpawnAndStartChildHere(List<NPC> offspringAvailable) {
		NPC offspring = offspringAvailable.get(Util.random.nextInt(offspringAvailable.size()));
		Main.game.getOffspringSpawned().add(offspring);

		offspring.setWorldLocation(Main.game.getPlayer().getWorldLocation());
		offspring.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));

		Main.game.setActiveNPC(offspring);

		return Main.game.getActiveNPC().getEncounterDialogue();
	}

	private static AbstractItem randomItem;
	private static AbstractClothing randomClothing;
	private static AbstractWeapon randomWeapon;

	private static final double INCEST_ENCOUNTER_RATE = 0.2f;

	private static double IncestEncounterRate() {
		if (!Main.game.isIncestEnabled()) {
			return -1;
		}
		return INCEST_ENCOUNTER_RATE;
	}

	private Map<EncounterType, Float> dialogues;

	private Encounter(Map<EncounterType, Float> dialogues) {
		this.dialogues = dialogues;
	}

	protected abstract DialogueNodeOld initialiseEncounter(EncounterType node);

	/**
	 * Returns a random encounter from the list, or null if no encounter was selected.
	 * 
	 * @param forceEncounter Forces an encounter to be selected. (Will still return null if the encounter list is empty.)
	 * @return null if no encounter.
	 */
	public DialogueNodeOld getRandomEncounter(boolean forceEncounter) {
		return getBaseRandomEncounter(forceEncounter);
	}
	
	public Map<EncounterType, Float> getDialogues() {
		return dialogues;
	}

	protected DialogueNodeOld getBaseRandomEncounter(boolean forceEncounter) {
		float r = (float) (Math.random() * 100);
		float total = 0;
		
		if(forceEncounter) {
			r = 0;
			for (Entry<EncounterType, Float> e : getDialogues().entrySet()) {
				r += e.getValue();
			}
			r *= Math.random();
		}
		
		for (Entry<EncounterType, Float> e : getDialogues().entrySet()) {
			total += e.getValue();
			if (r <= total) {
				return initialiseEncounter(e.getKey());
			}
		}

		return null;
	}

	public static AbstractItem getRandomItem() {
		return randomItem;
	}

	public static AbstractWeapon getRandomWeapon() {
		return randomWeapon;
	}

	public static AbstractClothing getRandomClothing() {
		return randomClothing;
	}

}
