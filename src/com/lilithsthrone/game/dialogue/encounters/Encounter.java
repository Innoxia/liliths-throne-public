package com.lilithsthrone.game.dialogue.encounters;

import java.io.File;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.DominionSuccubusAttacker;
import com.lilithsthrone.game.character.npc.dominion.HarpyNestsAttacker;
import com.lilithsthrone.game.character.npc.dominion.Lumi;
import com.lilithsthrone.game.character.npc.dominion.RentalMommy;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.npc.submission.BatCavernLurkerAttacker;
import com.lilithsthrone.game.character.npc.submission.BatCavernSlimeAttacker;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.character.npc.submission.RebelBaseInsaneSurvivor;
import com.lilithsthrone.game.character.npc.submission.SubmissionAttacker;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DominionExpressCentaurDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.EnforcerAlleywayDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.SlaveEncountersDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.WesQuest;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.VengarCaptiveDialogue;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia, DSG
 */
public class Encounter {

	public static AbstractEncounter LILAYAS_HOME_CORRIDOR = new AbstractEncounter() {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			return Util.newHashMapOfValues(
					(Main.game.getCharactersPresent().isEmpty()
						?new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f)
						:null),
					(Main.game.getCharactersPresent().isEmpty()
						?new Value<EncounterType, Float>(EncounterType.SLAVE_USING_OTHER_SLAVE, 5f)
						:null));
		}
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if(node == EncounterType.SLAVE_USES_YOU) {
				List<NPC> slaves = new ArrayList<>();
				List<NPC> hornySlaves = new ArrayList<>();
				
				for(String id : Main.game.getPlayer().getSlavesOwned()) {
					try {
						NPC slave = (NPC) Main.game.getNPCById(id);
						if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER)
								&& slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.IDLE
								&& !slave.getLocationPlace().getPlaceUpgrades().stream().anyMatch(upgrade->upgrade.getImmobilisationType()!=null)
								&& slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_HOUSE_FREEDOM)
								&& slave.isAttractedTo(Main.game.getPlayer())) {
							if(slave.getLastTimeHadSex()+60*4<Main.game.getMinutesPassed()) {
								slaves.add(slave);
							}
							if(slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)) {
								hornySlaves.add(slave);
							}
						}
					} catch (Exception e) {
						System.err.println("Main.game.getNPCById("+id+") returning null in Encounter.LILAYAS_HOME_CORRIDOR");
					}
				}
				slaves.removeIf((slave) -> slave.getWorldLocation()==WorldType.SLAVER_ALLEY);
				hornySlaves.removeIf((slave) -> slave.getWorldLocation()==WorldType.SLAVER_ALLEY);
				
				if(!hornySlaves.isEmpty()) {
					Collections.shuffle(hornySlaves);
					return SlaveEncountersDialogue.getSlaveUsesYou(hornySlaves.get(0));
					
				} else if(!slaves.isEmpty()) {
					Collections.shuffle(slaves);
					return SlaveEncountersDialogue.getSlaveUsesYou(slaves.get(0));
				}
				
				return null;
				
			} else if(node==EncounterType.SLAVE_USING_OTHER_SLAVE) {
				Value<NPC, NPC> slaves = getSlaveUsingOtherSlaveInLilayaCorridor();
				if(slaves==null || slaves.getKey()==null || slaves.getValue()==null) {
					return null; // Return a null Encounter here instead of checking in getDialogues() due to performance issues
				}
				return SlaveEncountersDialogue.getSlaveUsingOtherSlaveLilayaCorridor(slaves);
				
			} else {
				return null;
			}
		}
	};

	public static AbstractEncounter LILAYAS_DUNGEON_PASSAGEWAY = new AbstractEncounter() {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			return Util.newHashMapOfValues(
					(Main.game.getCharactersPresent().isEmpty() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("acexp_dungeon_explored"))
						?new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f)
						:null));
		}
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if(node == EncounterType.SLAVE_USES_YOU) {
				List<NPC> slaves = new ArrayList<>();
				List<NPC> hornySlaves = new ArrayList<>();
				
				for(String id : Main.game.getPlayer().getSlavesOwned()) {
					try {
						NPC slave = (NPC) Main.game.getNPCById(id);
						if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER)
								&& slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.IDLE
								&& !slave.getLocationPlace().getPlaceUpgrades().stream().anyMatch(upgrade->upgrade.getImmobilisationType()!=null)
								&& slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_HOUSE_FREEDOM)
								&& slave.isAttractedTo(Main.game.getPlayer())) {
							if(slave.getLastTimeHadSex()+60*4<Main.game.getMinutesPassed()) {
								slaves.add(slave);
							}
							if(slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)) {
								hornySlaves.add(slave);
							}
						}
					} catch (Exception e) {
						System.err.println("Main.game.getNPCById("+id+") returning null in Encounter.LILAYAS_DUNGEON_PASSAGEWAY");
					}
				}
				slaves.removeIf((slave) -> slave.getWorldLocation()==WorldType.SLAVER_ALLEY);
				hornySlaves.removeIf((slave) -> slave.getWorldLocation()==WorldType.SLAVER_ALLEY);
				
				if(!hornySlaves.isEmpty()) {
					Collections.shuffle(hornySlaves);
					return SlaveEncountersDialogue.getSlaveUsesYou(hornySlaves.get(0));
					
				} else if(!slaves.isEmpty()) {
					Collections.shuffle(slaves);
					return SlaveEncountersDialogue.getSlaveUsesYou(slaves.get(0));
				}
				
				return null;
				
			} else {
				return null;
			}
		}
	};
	
	public static AbstractEncounter DOMINION_STREET = new AbstractEncounter() {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			boolean cultistAvailable = 
					Main.game.getCurrentWeather() != Weather.MAGIC_STORM
						&& Main.game.getDateNow().getMonth().equals(Month.OCTOBER)
						&& Main.game.getNumberOfWitches()<4
						&& Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.DOMINION_STREET);

			
			boolean wesQuestAvailable = false;
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_WES)) {
				boolean harpyQuestTimePassed = false;
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)) {
					if(!Main.game.getDialogueFlags().hasSavedLong("angry_harpies_completed")) {
						Main.game.getDialogueFlags().setSavedLong("angry_harpies_completed", Main.game.getMinutesPassed());
						
					} else {
						harpyQuestTimePassed = Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong("angry_harpies_completed") > (60*24*5);
					}
				}
				wesQuestAvailable = harpyQuestTimePassed
										&& Main.game.getCurrentWeather()!=Weather.MAGIC_STORM
										&& Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)
										&& Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)
										&& Main.game.getHourOfDay()>=17 && Main.game.getHourOfDay()<=21;
			}
			
			return Util.newHashMapOfValues(
					Main.game.getCurrentWeather()==Weather.MAGIC_STORM
						?new Value<EncounterType, Float>(EncounterType.DOMINION_STORM_ATTACK, 15f)
						:null,
					cultistAvailable
						?new Value<EncounterType, Float>(EncounterType.SPECIAL_DOMINION_CULTIST, 5f)
						:null,
					Main.game.getCurrentWeather()!=Weather.MAGIC_STORM
						?new Value<EncounterType, Float>(EncounterType.DOMINION_EXPRESS_CENTAUR, 1f)
						:null,
					new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f),
					wesQuestAvailable
						?new Value<EncounterType, Float>(EncounterType.WES_QUEST_START, 50f)
						:null,
					Main.game.getPlayer().getName(false).equalsIgnoreCase("Kinariu") && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.foundHappiness)
						?new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_FIND_HAPPINESS, 10f)
						:null);
		}
		
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if(node == EncounterType.DOMINION_STORM_ATTACK && Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				NPC npc = new DominionAlleywayAttacker(Gender.getGenderFromUserPreferences(false, false), false, NPCGenerationFlag.DIRTY);
				try {
					Main.game.addNPC(npc, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				npc.setLocation(Main.game.getPlayer(), true);
				Main.game.setActiveNPC(npc);
				return Main.game.getActiveNPC().getEncounterDialogue();
			}
			
			if(node == EncounterType.SPECIAL_DOMINION_CULTIST) {
				boolean suitableTile = true;
				for(GameCharacter character : Main.game.getNonCompanionCharactersPresent()) {
					if(!Main.game.getPlayer().getFriendlyOccupants().contains(character.getId())) {
						suitableTile = false;
						break;
					}
				}
				
				if(suitableTile) {
					Main.game.setActiveNPC(new Cultist());
					
					try {
						Main.game.addNPC(Main.game.getActiveNPC(), false);
					} catch (Exception e) {
						e.printStackTrace();
					}
		
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
			}
			
			if(node==EncounterType.DOMINION_EXPRESS_CENTAUR) {
				AbstractClothing collar = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
				if(collar!=null && collar.getClothingType().getId().equals("innoxia_neck_filly_choker")) { // When wearing filly choker, get approached by horny centaurs:
					return DominionExpressCentaurDialogue.initEncounter(); // Can return null if player cannot access mouth or anus.
				}
			}
			
			if(node == EncounterType.DOMINION_STREET_FIND_HAPPINESS) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.foundHappiness, true);
				return DominionEncounterDialogue.DOMINION_STREET_FIND_HAPPINESS;
				
			}
			
			if(node == EncounterType.SLAVE_USES_YOU && Main.game.getCurrentWeather() != Weather.MAGIC_STORM) {
				NPC slave = getSlaveWantingToUseYouInDominion();
				if(slave==null) {
					return null;
				}
				return SlaveEncountersDialogue.getSlaveUsesYouStreet(slave);
			}
			
			if(node == EncounterType.WES_QUEST_START) {
				return WesQuest.WES_QUEST_START;
			}
			
			return null;
		}
	};

	public static AbstractEncounter DOMINION_BOULEVARD = new AbstractEncounter() {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			return Util.newHashMapOfValues(
					new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_RENTAL_MOMMY, 10f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_PILL_HANDOUT, 5f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_EXPRESS_CENTAUR, 1f),
					new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f));
		}
		
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) { // None of these encounters work during a storm
				return null;
			}
			
			LocalDateTime time = Main.game.getDateNow();
			
			if(time.getMonth().equals(Month.MAY) && time.getDayOfMonth()>7 && time.getDayOfMonth()<=14) { // Mother's day timing, 2nd week of May
				if(node == EncounterType.DOMINION_STREET_RENTAL_MOMMY && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.mommyFound)) {
					Main.game.setActiveNPC(Main.game.getNpc(RentalMommy.class));
					Main.game.getNpc(RentalMommy.class).setLocation(WorldType.DOMINION, Main.game.getPlayer().getLocation(), true);
					
					try {
						Main.game.addNPC(Main.game.getActiveNPC(), false);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return Main.game.getActiveNPC().getEncounterDialogue();
					
				} else if(node==EncounterType.DOMINION_STREET_PILL_HANDOUT) {
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), 3+Util.random.nextInt(4), false, true));
					
					return DominionEncounterDialogue.DOMINION_STREET_PILL_HANDOUT;
				}
			}
			
			if(time.getMonth().equals(Month.JUNE) && time.getDayOfMonth()>14 && time.getDayOfMonth()<=21) { // Father's day timing, 3rd week of June
				if(node==EncounterType.DOMINION_STREET_PILL_HANDOUT) {
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), 3+Util.random.nextInt(4), false, true));
					
					return DominionEncounterDialogue.DOMINION_STREET_PILL_HANDOUT;
				}
			}

			if(node==EncounterType.DOMINION_EXPRESS_CENTAUR) {
				AbstractClothing collar = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
				if(collar!=null && collar.getClothingType().getId().equals("innoxia_neck_filly_choker")) { // When wearing filly choker, get approached by horny centaurs:
					return DominionExpressCentaurDialogue.initEncounter(); // Can return null if player cannot access mouth or anus.
				}
			}
			
			if(node == EncounterType.SLAVE_USES_YOU) {
				NPC slave = getSlaveWantingToUseYouInDominion();
				if(slave==null) {
					return null;
				}
				return SlaveEncountersDialogue.getSlaveUsesYouStreet(slave);
			}
			
			return null;
		}
	};

	public static AbstractEncounter DOMINION_ALLEY = new AbstractEncounter() {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			Map<EncounterType, Float> map = Util.newHashMapOfValues(
					new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_ITEM, 3f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_CLOTHING, 2f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_WEAPON, 1f),
					(Main.game.getCurrentWeather()!=Weather.MAGIC_STORM
						?new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f)
						:null),
					(Main.game.getCurrentWeather()!=Weather.MAGIC_STORM
						?new Value<EncounterType, Float>(EncounterType.SLAVE_USING_OTHER_SLAVE, 5f)
						:null));
			
			if(Main.game.isStarted() && DominionPlaces.isCloseToEnforcerHQ()) {
				map.put(EncounterType.DOMINION_ALLEY_ATTACK, 10f);
				if(Main.game.getCurrentWeather()!=Weather.MAGIC_STORM
						&& (!Main.game.getDialogueFlags().hasSavedLong("enforcer_encounter_minutes") || Main.game.getDialogueFlags().getSavedLong("enforcer_encounter_minutes")+(4*60)<Main.game.getMinutesPassed())) {
					map.put(EncounterType.DOMINION_ALLEY_ENFORCERS, 15f);
				}
			} else {
				map.put(EncounterType.DOMINION_ALLEY_ATTACK, 15f);
				if(Main.game.getCurrentWeather()!=Weather.MAGIC_STORM
						&& (!Main.game.getDialogueFlags().hasSavedLong("enforcer_encounter_minutes") || Main.game.getDialogueFlags().getSavedLong("enforcer_encounter_minutes")+(4*60)<Main.game.getMinutesPassed())) {
					map.put(EncounterType.DOMINION_ALLEY_ENFORCERS, 2.5f);
				}
			}
			
			return map;
		}
		
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if(node == EncounterType.DOMINION_ALLEY_ATTACK) {
				// Prioritise re-encountering the NPC on this tile:
				List<NPC> encounterPossibilities = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
				encounterPossibilities.removeIf(npc -> npc instanceof Lumi);
				if(!encounterPossibilities.isEmpty()) {
					NPC encounterNpc = Util.randomItemFrom(encounterPossibilities);
					Main.game.setActiveNPC(encounterNpc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
				if(Math.random()<IncestEncounterRate()) { // Incest
					List<OffspringSeed> offspringAvailable = Main.game.getOffspringNotSpawned(
						os-> (os.getSubspecies()==Subspecies.HALF_DEMON
								?(os.getHalfDemonSubspecies().isAbleToNaturallySpawnInLocation(WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS))
								:(os.getSubspecies().isAbleToNaturallySpawnInLocation(WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS)
										|| os.getSubspecies()==Subspecies.ANGEL
										|| os.getSubspecies()==Subspecies.FOX_ASCENDANT
										|| os.getSubspecies()==Subspecies.FOX_ASCENDANT_ARCTIC
										|| os.getSubspecies()==Subspecies.FOX_ASCENDANT_FENNEC)));
					
					if(!offspringAvailable.isEmpty()) {
//						for(NPC npc : offspringAvailable) {
//							System.out.println(npc.getName());
//						}
						return SpawnAndStartChildHere(offspringAvailable);
					}
				}
				
				NPC npc = new DominionAlleywayAttacker(Gender.getGenderFromUserPreferences(false, false));
				try {
					Main.game.addNPC(npc, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				npc.setLocation(Main.game.getPlayer(), true);
				Main.game.setActiveNPC(npc);
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			} else if(node == EncounterType.DOMINION_FIND_ITEM) {
				if(!Main.game.isSillyModeEnabled() || Math.random()<0.99f) {
					randomItem = Main.game.getItemGen().generateItem(ItemType.getDominionAlleywayItems().get(Util.random.nextInt(ItemType.getDominionAlleywayItems().size())));
					
				} else {
					if(Math.random()<0.5f) {
						randomItem = Main.game.getItemGen().generateItem(ItemType.EGGPLANT);
					} else {
						randomItem = Main.game.getItemGen().generateItem("innoxia_cheat_unlikely_whammer");
					}
				}
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem((AbstractItem) randomItem);
				return DominionEncounterDialogue.ALLEY_FIND_ITEM;
				
			} else if(node == EncounterType.DOMINION_FIND_CLOTHING) {
				if(Math.random()<0.01f) {
					randomItem = Main.game.getItemGen().generateClothing(ClothingType.MEGA_MILK);
					Main.game.getPlayerCell().getInventory().addClothing((AbstractClothing) randomItem);
					
				} else {
					List<AbstractClothingType> randomClothingList = new ArrayList<>(ClothingType.getAllClothing());
					randomClothingList.removeIf((clothing) ->
							(!clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_KATE)
								&& !clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN)
								&& !clothing.getDefaultItemTags().contains(ItemTag.DOMINION_ALLEYWAY_SPAWN))
							|| clothing.getDefaultItemTags().contains(ItemTag.NO_RANDOM_SPAWN)
							|| clothing.getRarity()==Rarity.EPIC
							|| clothing.getRarity()==Rarity.LEGENDARY);
					randomItem = Main.game.getItemGen().generateClothing(randomClothingList.get(Util.random.nextInt(randomClothingList.size())));
					Main.game.getPlayerCell().getInventory().addClothing((AbstractClothing) randomItem);
				}
				return DominionEncounterDialogue.ALLEY_FIND_ITEM;
				
			} else if(node == EncounterType.DOMINION_FIND_WEAPON) {
				List<AbstractWeaponType> weapons = new ArrayList<>(WeaponType.getAllWeapons());
				weapons.removeIf(w -> !w.getItemTags().contains(ItemTag.DOMINION_ALLEYWAY_SPAWN));
				randomItem = Main.game.getItemGen().generateWeapon(weapons.get(Util.random.nextInt(weapons.size())));
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addWeapon((AbstractWeapon) randomItem);
				return DominionEncounterDialogue.ALLEY_FIND_ITEM;
				
			} else if(node == EncounterType.DOMINION_ALLEY_ENFORCERS) {
				spawnEnforcers();
				return EnforcerAlleywayDialogue.ENFORCER_ALLEYWAY_START;
				
			} else if(node == EncounterType.SLAVE_USES_YOU) {
				NPC slave = getSlaveWantingToUseYouInDominion();
				if(slave==null) {
					return null;
				}
				return SlaveEncountersDialogue.getSlaveUsesYouAlleyway(slave);
				
			} else if(node==EncounterType.SLAVE_USING_OTHER_SLAVE) {
				Value<NPC, NPC> slaves = getSlaveUsingOtherSlaveInDominion();
				if(slaves==null || slaves.getKey()==null || slaves.getValue()==null) {
					return null;
				}
				return SlaveEncountersDialogue.getSlaveUsingOtherSlaveAlleyway(slaves);
			}
			
			return null;
		}
	};

	public static AbstractEncounter DOMINION_DARK_ALLEY = new AbstractEncounter() {
		@Override
		public Map<EncounterType, Float> getDialogues() {
            Map<EncounterType, Float> map = new HashMap<>();

            map.put(EncounterType.DOMINION_ALLEY_ATTACK, 15f);
            
            return map;
        }
        @Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
				
			// Prioritise re-encountering the NPC on this tile:
			List<NPC> encounterPossibilities = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
			if(!encounterPossibilities.isEmpty()) {
				NPC encounterNpc = Util.randomItemFrom(encounterPossibilities);
				Main.game.setActiveNPC(encounterNpc);
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
	};

	public static AbstractEncounter DOMINION_CANAL = new AbstractEncounter() {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			return Util.newHashMapOfValues(
					new Value<EncounterType, Float>(EncounterType.DOMINION_ALLEY_ATTACK, 10f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_ITEM, 3f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_CLOTHING, 2f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_WEAPON, 1f),
					Main.game.getCurrentWeather()!=Weather.MAGIC_STORM
							&& (!Main.game.getDialogueFlags().hasSavedLong("enforcer_encounter_minutes") || Main.game.getDialogueFlags().getSavedLong("enforcer_encounter_minutes")+(4*60)<Main.game.getMinutesPassed())
						?new Value<EncounterType, Float>(EncounterType.DOMINION_ALLEY_ENFORCERS, 2.5f)
						:null,
					Main.game.getCurrentWeather()!=Weather.MAGIC_STORM
						?new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f)
						:null);
		}
		
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if(node==EncounterType.DOMINION_ALLEY_ATTACK) {
				// Prioritise re-encountering the NPC on this tile:
				List<NPC> encounterPossibilities = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
				if(!encounterPossibilities.isEmpty()) {
					NPC encounterNpc = Util.randomItemFrom(encounterPossibilities);
					Main.game.setActiveNPC(encounterNpc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
				if(Math.random()<IncestEncounterRate()) { // Incest
					List<OffspringSeed> offspringAvailable = Main.game.getOffspringNotSpawned(
							os-> (os.getSubspecies()==Subspecies.HALF_DEMON
								?(os.getHalfDemonSubspecies().isAbleToNaturallySpawnInLocation(WorldType.DOMINION, PlaceType.DOMINION_CANAL)
										|| os.getHalfDemonSubspecies()==Subspecies.SLIME
										|| os.getHalfDemonSubspecies()==Subspecies.ALLIGATOR_MORPH
										|| os.getHalfDemonSubspecies()==Subspecies.RAT_MORPH)
								:(os.getSubspecies().isAbleToNaturallySpawnInLocation(WorldType.DOMINION, PlaceType.DOMINION_CANAL)
										|| os.getSubspecies()==Subspecies.SLIME
										|| os.getSubspecies()==Subspecies.ALLIGATOR_MORPH
										|| os.getSubspecies()==Subspecies.RAT_MORPH)));
					
					if(!offspringAvailable.isEmpty()) {
						return SpawnAndStartChildHere(offspringAvailable);
					}
				}
				
				Main.game.setActiveNPC(new DominionAlleywayAttacker(Gender.getGenderFromUserPreferences(false, false)));
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer(), true);
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			}else if(node == EncounterType.DOMINION_FIND_ITEM) {
				if(!Main.game.isSillyModeEnabled() || Math.random()<0.99f) {
					randomItem = Main.game.getItemGen().generateItem(ItemType.getDominionAlleywayItems().get(Util.random.nextInt(ItemType.getDominionAlleywayItems().size())));
					
				} else {
					if(Math.random()<0.5f) {
						randomItem = Main.game.getItemGen().generateItem(ItemType.EGGPLANT);
					} else {
						randomItem = Main.game.getItemGen().generateItem("innoxia_cheat_unlikely_whammer");
					}
				}
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem((AbstractItem) randomItem);
				return DominionEncounterDialogue.ALLEY_FIND_ITEM;
				
			} else if(node == EncounterType.DOMINION_FIND_CLOTHING) {
				if(Math.random()<0.01f) {
					randomItem = Main.game.getItemGen().generateClothing(ClothingType.MEGA_MILK);
					Main.game.getPlayerCell().getInventory().addClothing((AbstractClothing) randomItem);
					
				} else {
					List<AbstractClothingType> randomClothingList = new ArrayList<>(ClothingType.getAllClothing());
					randomClothingList.removeIf((clothing) ->
							(!clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_KATE)
								&& !clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN)
								&& !clothing.getDefaultItemTags().contains(ItemTag.DOMINION_ALLEYWAY_SPAWN))
							|| clothing.getDefaultItemTags().contains(ItemTag.NO_RANDOM_SPAWN)
							|| clothing.getRarity()==Rarity.EPIC
							|| clothing.getRarity()==Rarity.LEGENDARY);
					randomItem = Main.game.getItemGen().generateClothing(randomClothingList.get(Util.random.nextInt(randomClothingList.size())));
					Main.game.getPlayerCell().getInventory().addClothing((AbstractClothing) randomItem);
				}
				return DominionEncounterDialogue.ALLEY_FIND_ITEM;
				
			} else if(node == EncounterType.DOMINION_FIND_WEAPON) {
				List<AbstractWeaponType> weapons = new ArrayList<>(WeaponType.getAllWeapons());
				weapons.removeIf(w -> !w.getItemTags().contains(ItemTag.DOMINION_ALLEYWAY_SPAWN));
				randomItem = Main.game.getItemGen().generateWeapon(weapons.get(Util.random.nextInt(weapons.size())));
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addWeapon((AbstractWeapon) randomItem);
				return DominionEncounterDialogue.ALLEY_FIND_ITEM;
				
			} else if(node == EncounterType.DOMINION_ALLEY_ENFORCERS) {
				spawnEnforcers();
				return EnforcerAlleywayDialogue.ENFORCER_ALLEYWAY_START;
				
			} else if(node == EncounterType.SLAVE_USES_YOU) {
				NPC slave = getSlaveWantingToUseYouInDominion();
				if(slave==null) {
					return null;
				}
				return SlaveEncountersDialogue.getSlaveUsesYouAlleyway(slave);
			}
			
			return null;
		}
	};
	
	public static AbstractEncounter DOMINION_EXPRESS = new AbstractEncounter() {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			return Util.newHashMapOfValues(new Value<EncounterType, Float>(EncounterType.DOMINION_EXPRESS_CENTAUR, 10f));
		}
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if(node==EncounterType.DOMINION_EXPRESS_CENTAUR) {
				AbstractClothing collar = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
				if(collar!=null && collar.getClothingType().getId().equals("innoxia_neck_filly_choker")) { // When wearing filly choker, get approached by horny centaurs:
					return DominionExpressCentaurDialogue.initEncounter(); // Can return null if player cannot access mouth or anus.
				}
			}
			return null;
		}
	};

	public static AbstractEncounter HARPY_NEST_WALKWAYS = new AbstractEncounter() {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			return Util.newHashMapOfValues(
					new Value<EncounterType, Float>(EncounterType.HARPY_NEST_ATTACK, 12f),
					new Value<EncounterType, Float>(EncounterType.HARPY_NEST_FIND_ITEM, 4f));
		}
		
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if (node == EncounterType.HARPY_NEST_ATTACK && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION)) {
				// Prioritise re-encountering the NPC on this tile:
				List<NPC> encounterPossibilities = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
				if(!encounterPossibilities.isEmpty()) {
					NPC encounterNpc = Util.randomItemFrom(encounterPossibilities);
					Main.game.setActiveNPC(encounterNpc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
				if(Math.random()<IncestEncounterRate()) { // Incest
					List<OffspringSeed> offspringAvailable = Main.game.getOffspringNotSpawned(
							os-> (os.getSubspecies()==Subspecies.HALF_DEMON
								?(os.getHalfDemonSubspecies().getRace()==Race.HARPY)
								:(os.getSubspecies().getRace()==Race.HARPY)));
					
					if(!offspringAvailable.isEmpty()) {
						return SpawnAndStartChildHere(offspringAvailable);
					}
				}

				Main.game.setActiveNPC(new HarpyNestsAttacker(Gender.getGenderFromUserPreferences(false, false)));
				
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getLocation());
				
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return Main.game.getActiveNPC().getEncounterDialogue();
			}
			
			if(node == EncounterType.HARPY_NEST_FIND_ITEM) {
				if(Math.random() < 0.66) {
					randomItem = Main.game.getItemGen().generateItem("innoxia_race_harpy_harpy_perfume");
					
				} else {
					randomItem = Main.game.getItemGen().generateItem(ItemType.getItemTypeFromId("innoxia_race_harpy_bubblegum_lollipop"));
				}
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem((AbstractItem) randomItem);
				return DominionEncounterDialogue.HARPY_NESTS_FIND_ITEM;
				
			}
			
			return null;
		}
	};

	public static AbstractEncounter HARPY_NEST_LOOK_FOR_TROUBLE = new AbstractEncounter() {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			return Util.newHashMapOfValues(
					new Value<EncounterType, Float>(EncounterType.HARPY_NEST_ATTACK, 12f),
					new Value<EncounterType, Float>(EncounterType.HARPY_NEST_FIND_ITEM, 4f));
		}

		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if (node == EncounterType.HARPY_NEST_ATTACK) {
				// Prioritise re-encountering the NPC on this tile:
				List<NPC> encounterPossibilities = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
				if(!encounterPossibilities.isEmpty()) {
					NPC encounterNpc = Util.randomItemFrom(encounterPossibilities);
					Main.game.setActiveNPC(encounterNpc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
				if(Math.random()<IncestEncounterRate()) { // Incest
					List<OffspringSeed> offspringAvailable = Main.game.getOffspringNotSpawned(
							os-> (os.getSubspecies()==Subspecies.HALF_DEMON
								?(os.getHalfDemonSubspecies().getRace()==Race.HARPY)
								:(os.getSubspecies().getRace()==Race.HARPY)));
					
					if(!offspringAvailable.isEmpty()) {
						return SpawnAndStartChildHere(offspringAvailable);
					}
				}
				
				Main.game.setActiveNPC(new HarpyNestsAttacker(Gender.getGenderFromUserPreferences(false, false)));
				
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
					randomItem = Main.game.getItemGen().generateItem("innoxia_race_harpy_harpy_perfume");
					
				} else {
					randomItem = Main.game.getItemGen().generateItem(ItemType.getItemTypeFromId("innoxia_race_harpy_bubblegum_lollipop"));
				}
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem((AbstractItem) randomItem);
				return DominionEncounterDialogue.HARPY_NESTS_FIND_ITEM;
				
			}
			
			return null;
		}
	};

	public static AbstractEncounter SUBMISSION_TUNNELS = new AbstractEncounter() {
		@Override
                public Map<EncounterType, Float> getDialogues() {
                    Map<EncounterType, Float> map = new HashMap<>();
			
                    map.put(EncounterType.SUBMISSION_TUNNEL_ATTACK, 20f);
                    map.put(EncounterType.SUBMISSION_FIND_ITEM, 10f);
                    
                    return map;
                }
                @Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			
			if(node == EncounterType.SUBMISSION_TUNNEL_ATTACK) {
				List<String> impAdjectives = new ArrayList<>();
				// If non-pacified imp tunnel, imp attack:
				if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)
						&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
					
					// Alpha imps: Encounters are 2 imp alphas + 2 imps
					List<GameCharacter> impGroup = new ArrayList<>();
					
					try {
						// Leader (alpha imp):
						ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
						imp.setGenericName("alpha-imp leader");
						imp.setLevel(8+Util.random.nextInt(5)); // 8-12
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						// Alpha imp:
						imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
						imp.setLevel(6+Util.random.nextInt(3)); // 6-8
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.getGenderFromUserPreferences(false, false), false);
						impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
						imp.setLevel(3+Util.random.nextInt(4)); // 3-6
						Main.game.addNPC(imp, false);
						impGroup.add(imp);

						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.getGenderFromUserPreferences(false, false), false);
						impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
						imp.setLevel(3+Util.random.nextInt(4)); // 3-6
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						for(GameCharacter impGangMember : impGroup) {
							((NPC) impGangMember).equipClothing(EquipClothingSetting.getAllClothingSettings());
						}
						
					} catch (Exception e) {
					}
					
					return ((NPC) impGroup.get(0)).getEncounterDialogue();
					
				} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_DEMON)
						&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
					
					// Demon-led imps: Encounters are 3 imps with an arcane imp leader
					List<GameCharacter> impGroup = new ArrayList<>();

					try {
						// Leader (arcane alpha imp):
						ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.getGenderFromUserPreferences(false, false), false);
						imp.setGenericName("alpha-imp arcanist");
						imp.setAttribute(Attribute.MAJOR_ARCANE, 50);
						imp.addSpell(Spell.ARCANE_AROUSAL);
						imp.addSpell(Spell.FIREBALL);
						imp.addSpell(Spell.ICE_SHARD);
						imp.addSpell(Spell.TELEKENETIC_SHOWER);
						imp.setLevel(12+Util.random.nextInt(7)); // 12-18
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.getGenderFromUserPreferences(false, false), false);
						impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
						imp.setLevel(6+Util.random.nextInt(4)); // 6-8
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.getGenderFromUserPreferences(false, false), false);
						impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
						imp.setLevel(3+Util.random.nextInt(4)); // 3-6
						Main.game.addNPC(imp, false);
						impGroup.add(imp);

						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.getGenderFromUserPreferences(false, false), false);
						impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
						imp.setLevel(3+Util.random.nextInt(4)); // 3-6
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						for(GameCharacter impGangMember : impGroup) {
							((NPC) impGangMember).equipClothing(EquipClothingSetting.getAllClothingSettings());
						}
						
					} catch (Exception e) {
					}
					
					return ((NPC) impGroup.get(0)).getEncounterDialogue();
					
				} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)
						&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
					
					// Imp seducers: Encounters are 4 female imps
					List<GameCharacter> impGroup = new ArrayList<>();

					try {
						// Leader (Alpha imp):
						ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_V_B_FEMALE, false);
						imp.setGenericName("alpha-imp leader");
						imp.setLevel(12+Util.random.nextInt(7)); // 12-18
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						imp.setBreastSize(imp.getBreastSize().getMeasurement()+4);
						
						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.F_V_B_FEMALE, false);
						impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
						imp.setLevel(8+Util.random.nextInt(3)); // 8-10
						Main.game.addNPC(imp, false);
						impGroup.add(imp);

						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.F_V_B_FEMALE, false);
						impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
						imp.setLevel(6+Util.random.nextInt(3)); // 6-8
						Main.game.addNPC(imp, false);
						impGroup.add(imp);

						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.F_V_B_FEMALE, false);
						impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
						imp.setLevel(4+Util.random.nextInt(3)); // 4-6
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						for(GameCharacter impGangMember : impGroup) {
							((NPC) impGangMember).equipClothing(EquipClothingSetting.getAllClothingSettings());
						}
						
					} catch (Exception e) {
					}
					
					return ((NPC) impGroup.get(0)).getEncounterDialogue();
					
				} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)
						&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
					
					// Imp brawlers: Encounters are 4 male imps
					List<GameCharacter> impGroup = new ArrayList<>();

					try {
						// Leader (Alpha imp):
						ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
						imp.setGenericName("alpha-imp leader");
						imp.setLevel(12+Util.random.nextInt(7)); // 12-18
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						// Alpha imp:
						imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
						impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
						imp.setLevel(8+Util.random.nextInt(3)); // 8-10
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.M_P_MALE, false);
						impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
						imp.setLevel(6+Util.random.nextInt(3)); // 6-8
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.M_P_MALE, false);
						impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
						imp.setLevel(4+Util.random.nextInt(3)); // 4-6
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						for(GameCharacter impGangMember : impGroup) {
							((NPC) impGangMember).equipClothing(EquipClothingSetting.getAllClothingSettings());
						}
						
					} catch (Exception e) {
					}
					
					return ((NPC) impGroup.get(0)).getEncounterDialogue();
				}
				
				// Normal tunnel tiles:

				// Prioritise re-encountering the NPC on this tile:
				List<NPC> encounterPossibilities = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
				if(!encounterPossibilities.isEmpty()) {
					NPC encounterNpc = Util.randomItemFrom(encounterPossibilities);
					Main.game.setActiveNPC(encounterNpc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
				if(Math.random()<IncestEncounterRate()) {
					List<OffspringSeed> offspringAvailable = Main.game.getOffspringNotSpawned(
							os-> (os.getSubspecies()==Subspecies.HALF_DEMON
								?(os.getHalfDemonSubspecies().isAbleToNaturallySpawnInLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_TUNNELS))
								:(os.getSubspecies().isAbleToNaturallySpawnInLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_TUNNELS))));
					
					if(!offspringAvailable.isEmpty()) {
						return SpawnAndStartChildHere(offspringAvailable);
					}
				}
				
				Main.game.setActiveNPC(new SubmissionAttacker(Gender.getGenderFromUserPreferences(false, false)));
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer(), true);
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			} else if (node == EncounterType.SUBMISSION_FIND_ITEM) {
				
				randomItem = Main.game.getItemGen().generateItem(ItemType.getSubmissionTunnelItems().get(Util.random.nextInt(ItemType.getSubmissionTunnelItems().size())));
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem((AbstractItem) randomItem);
				return SubmissionEncounterDialogue.FIND_ITEM;
				
			} else {
				return null;
			}
		}
	};

	public static AbstractEncounter BAT_CAVERN = new AbstractEncounter() {
        @Override
        public Map<EncounterType, Float> getDialogues() {
            Map<EncounterType, Float> map = new HashMap<>();
            
            map.put(EncounterType.BAT_CAVERN_LURKER_ATTACK, 8f);
            map.put(EncounterType.BAT_CAVERN_SLIME_ATTACK, 6f);
            map.put(EncounterType.BAT_CAVERN_FIND_ITEM, 6f);
            
            if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_REBEL_BASE)
                    && !Main.game.getPlayer().isQuestFailed(QuestLine.SIDE_REBEL_BASE)
                    && Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_HANDLE_REFUSED)
                    && Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.BAT_CAVERN_DARK)) {
            	// Make sure that the player is at least a distance of 3 tiles from the entrance before encountering the rebel base:
            	Vector2i playerLocation = Main.game.getPlayer().getLocation();
            	Vector2i entranceLocation = Main.game.getWorlds().get(WorldType.BAT_CAVERNS).getCell(PlaceType.BAT_CAVERN_ENTRANCE).getLocation();
            	int distanceFromEntrance = (int)playerLocation.getDistanceToVector(entranceLocation);
            	if(distanceFromEntrance>=3) {
	                if(Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT)){
	                    map.put(EncounterType.BAT_CAVERN_REBEL_BASE_DISCOVERED, 10f);
	                } else {
	                    map.put(EncounterType.BAT_CAVERN_REBEL_BASE_DISCOVERED, 5f);
	                }
            	}
            }
            
            if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_REBEL_BASE)
                    && !Main.game.getPlayer().isQuestFailed(QuestLine.SIDE_REBEL_BASE)
                    && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_HANDLE_REFUSED)
                    && Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_COMPLETE)) {
            	AbstractPlaceType playerPlaceType = Main.game.getPlayerCell().getPlace().getPlaceType();
            	// Limit encounters for passwords to dark, light, and HLF base entrance tiles only:
            	if(playerPlaceType.equals(PlaceType.BAT_CAVERN_DARK) || playerPlaceType.equals(PlaceType.BAT_CAVERN_LIGHT) || playerPlaceType.equals(PlaceType.BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR)) {
	            	// The player needs to find one password from a dark tile and one from a light tile, so if already found the password in their tile, do not enable Encounter
	            	boolean alreadyFound = (playerPlaceType.equals(PlaceType.BAT_CAVERN_DARK) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.rebelBaseDarkPassFound))
	            			|| (playerPlaceType.equals(PlaceType.BAT_CAVERN_LIGHT) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.rebelBaseLightPassFound));
	            	
	            	if(!alreadyFound) {
		            	EncounterType nextEncounter = EncounterType.BAT_CAVERN_REBEL_PASSWORD_TWO;
		            	if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO)) {
		                	nextEncounter = EncounterType.BAT_CAVERN_REBEL_PASSWORD_ONE;
		            	}
		                if(Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT)){
		                    map.put(nextEncounter, 5f);
		                } else {
		                    map.put(nextEncounter, 1f);
		                }
	            	}
            	}
            }
            
//            if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_REBEL_BASE)
//                    && !Main.game.getPlayer().isQuestFailed(QuestLine.SIDE_REBEL_BASE)
//                    && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_HANDLE_REFUSED)
//                    && Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_TWO)
//                    && Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.BAT_CAVERN_DARK)) {
//                if(Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT)){
//                    map.put(EncounterType.BAT_CAVERN_REBEL_PASSWORD_ONE, 5f);
//                } else {
//                    map.put(EncounterType.BAT_CAVERN_REBEL_PASSWORD_ONE, 1f);
//                }
//            }
//            
//            if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_REBEL_BASE)
//                    && !Main.game.getPlayer().isQuestFailed(QuestLine.SIDE_REBEL_BASE)
//                    && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_PART_ONE)
//                    && Main.game.getPlayer().isQuestProgressLessThan(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_PASSWORD_COMPLETE)
//                    && Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.BAT_CAVERN_LIGHT)) {
//                if(Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT)){
//                    map.put(EncounterType.BAT_CAVERN_REBEL_PASSWORD_TWO, 5f);
//                } else {
//                    map.put(EncounterType.BAT_CAVERN_REBEL_PASSWORD_TWO, 1f);
//                }
//            }
            
            return map;
        }

		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if (node == EncounterType.BAT_CAVERN_LURKER_ATTACK) {

				// Prioritise re-encountering the NPC on this tile:
				List<NPC> encounterPossibilities = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
				if(!encounterPossibilities.isEmpty()) {
					NPC encounterNpc = Util.randomItemFrom(encounterPossibilities);
					Main.game.setActiveNPC(encounterNpc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
				if(Math.random()<IncestEncounterRate()) {
					List<OffspringSeed> offspringAvailable = Main.game.getOffspringNotSpawned(
							os-> (os.getSubspecies()==Subspecies.HALF_DEMON
								?(os.getHalfDemonSubspecies().isAbleToNaturallySpawnInLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_DARK))
								:(os.getSubspecies().isAbleToNaturallySpawnInLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_DARK))));

					if(!offspringAvailable.isEmpty()) {
						return SpawnAndStartChildHere(offspringAvailable);
					}
				}
				
				Main.game.setActiveNPC(new BatCavernLurkerAttacker(Gender.getGenderFromUserPreferences(false, false)));
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
				
				if(Math.random()<IncestEncounterRate()) {
					List<OffspringSeed> offspringAvailable = Main.game.getOffspringNotSpawned(
							os-> (os.getBodyMaterial() == BodyMaterial.SLIME));

					if(!offspringAvailable.isEmpty()) {
						return SpawnAndStartChildHere(offspringAvailable);
					}
				}
				
				Main.game.setActiveNPC(new BatCavernSlimeAttacker(Gender.getGenderFromUserPreferences(false, false)));
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			} else if (node == EncounterType.BAT_CAVERN_FIND_ITEM) {
				
				if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.BAT_CAVERN_LIGHT && Math.random()<0.8f) {
					randomItem = Main.game.getItemGen().generateItem(ItemType.MUSHROOM);
				} else {
					randomItem = Main.game.getItemGen().generateItem(ItemType.getBatCavernItems().get(Util.random.nextInt(ItemType.getBatCavernItems().size())));
				}
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem((AbstractItem) randomItem);
				
				return BatCavernsEncounterDialogue.FIND_ITEM;
			
			} else if (node == EncounterType.BAT_CAVERN_REBEL_BASE_DISCOVERED) {
				return BatCavernsEncounterDialogue.REBEL_BASE_DISCOVERED;
					
			} else if (node == EncounterType.BAT_CAVERN_REBEL_PASSWORD_ONE) {
				return BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_ONE;
				
			} else if (node == EncounterType.BAT_CAVERN_REBEL_PASSWORD_TWO) {
				return BatCavernsEncounterDialogue.REBEL_BASE_PASSWORD_TWO;
			}
			
			return null;
		}
	};

	public static AbstractEncounter REBEL_BASE = new AbstractEncounter() {
        @Override
        public Map<EncounterType, Float> getDialogues() {
            Map<EncounterType, Float> map = new HashMap<>();
            if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_REBEL_BASE, Quest.REBEL_BASE_EXPLORATION) &&
                    !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.rebelBaseInsaneSurvivorEncountered)) {
                map.put(EncounterType.REBEL_BASE_INSANE_SURVIVOR_ATTACK, 100f);
            }               
            return map;
        }
        @Override
        protected DialogueNode initialiseEncounter(EncounterType node) {
            if(node == EncounterType.REBEL_BASE_INSANE_SURVIVOR_ATTACK) {
                Main.game.setActiveNPC(new RebelBaseInsaneSurvivor(Gender.getGenderFromUserPreferences(false, false)));
                try {
                    Main.game.addNPC(Main.game.getActiveNPC(), false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Main.game.getActiveNPC().getEncounterDialogue();
                
            } else {
                return null;
            }
        }           
    };
    
	public static AbstractEncounter VENGAR_CAPTIVE_HALL = new AbstractEncounter() {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			Map<EncounterType, Float> map = new HashMap<>();// Silence delivers if pregnant
			
			map.put(EncounterType.VENGAR_CAPTIVE_SERVE, 40f);
			map.put(EncounterType.VENGAR_CAPTIVE_GROPED, 20f);
			map.put(EncounterType.VENGAR_CAPTIVE_RAT_FUCK, 10f);
			map.put(EncounterType.VENGAR_CAPTIVE_ORAL_UNDER_TABLE, 5f);
			
			// Once daily only:
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveVengarSatisfied)) {
				map.put(EncounterType.VENGAR_CAPTIVE_VENGAR_FUCK, 10f);
			}
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveGangBanged)) { //TODO this is not set anywhere?
				map.put(EncounterType.VENGAR_CAPTIVE_GROUP_SEX, 2f);
			}
			
			return map;
		}
		@Override
		protected DialogueNode getBaseRandomEncounter(boolean forceEncounter) {
			if(!Main.game.isExtendedWorkTime()) {
				return VengarCaptiveDialogue.VENGARS_HALL_NIGHT_TIME;
			}
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)) {
				return VengarCaptiveDialogue.VENGARS_HALL_DELIVERY;
			}
			if(Main.game.getPlayer().hasCompanions() && Main.game.getPlayer().getMainCompanion().hasStatusEffect(StatusEffect.PREGNANT_3)) {
				return VengarCaptiveDialogue.VENGARS_HALL_DELIVERY;
			}
			return super.getBaseRandomEncounter(forceEncounter);
		}
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if(node == EncounterType.VENGAR_CAPTIVE_SERVE) {
				return VengarCaptiveDialogue.VENGARS_HALL_SERVE;
				
			} else if(node == EncounterType.VENGAR_CAPTIVE_GROPED) {
				return VengarCaptiveDialogue.VENGARS_HALL_GROPED;
				
			} else if(node == EncounterType.VENGAR_CAPTIVE_VENGAR_FUCK) {
				return VengarCaptiveDialogue.VENGARS_HALL_VENGAR_FUCK;
				
			} else if(node == EncounterType.VENGAR_CAPTIVE_RAT_FUCK) {
				return VengarCaptiveDialogue.VENGARS_HALL_RAT_FUCK;
				
			} else if(node == EncounterType.VENGAR_CAPTIVE_GROUP_SEX) {
				return VengarCaptiveDialogue.VENGARS_HALL_GROUP_SEX;
			}
			
			return null;
		}
	};

	public static AbstractEncounter VENGAR_CAPTIVE_BEDROOM = new AbstractEncounter() {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			Map<EncounterType, Float> map = new HashMap<>();
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveRoomCleaned)) {
				map.put(EncounterType.VENGAR_CAPTIVE_CLEAN_ROOM, 50f);
			}
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveShadowSatisfied)
					|| !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarCaptiveSilenceSatisfied)) {
				map.put(EncounterType.VENGAR_CAPTIVE_SHADOW_SILENCE_DOMINATE, 25f);
			}
			if(map.isEmpty()) {
				map.put(EncounterType.VENGAR_CAPTIVE_ROOM_BARRED, 80f);
			}
			
			return map;
		}
		@Override
		protected DialogueNode getBaseRandomEncounter(boolean forceEncounter) {
			if(!Main.game.isExtendedWorkTime()) {
				return VengarCaptiveDialogue.VENGARS_BEDROOM_NIGHT_TIME;
			}
			return super.getBaseRandomEncounter(forceEncounter);
		}
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if(node == EncounterType.VENGAR_CAPTIVE_CLEAN_ROOM) {
				return VengarCaptiveDialogue.VENGARS_BEDROOM_CLEAN;
				
			} else if(node == EncounterType.VENGAR_CAPTIVE_SHADOW_SILENCE_DOMINATE) {
				return VengarCaptiveDialogue.VENGARS_BEDROOM_SHADOW_SILENCE;
				
			} else if(node == EncounterType.VENGAR_CAPTIVE_ROOM_BARRED) {
				return VengarCaptiveDialogue.VENGARS_BEDROOM_BARRED;
			}
			
			return null;
		}
	};

	public static List<AbstractEncounter> allEncounters;
	
	public static Map<AbstractEncounter, String> encounterToIdMap = new HashMap<>();
	public static Map<String, AbstractEncounter> idToEncounterMap = new HashMap<>();
	
	private static Map<String, List<AbstractEncounter>> addedEncounters = new HashMap<>();
	
	/**
	 * @return A list of Encounters which are associated with the placeType (which have been added via external file).
	 *  Returns an empty list if no associated encounters are found.
	 */
	public static List<AbstractEncounter> getAddedEncounters(String placeTypeId) {
		addedEncounters.putIfAbsent(placeTypeId, new ArrayList<>());
		return addedEncounters.get(placeTypeId);
	}

	/**
	 * @param id Will be in the format of: 'innoxia_maid'.
	 */
	public static AbstractEncounter getEncounterFromId(String id) {
		id = Util.getClosestStringMatch(id, idToEncounterMap.keySet());
		
		return idToEncounterMap.get(id);
	}
	
	public static String getIdFromEncounter(AbstractEncounter encounter) {
		return encounterToIdMap.get(encounter);
	}
	
	public static List<AbstractEncounter> getAllEncounters() {
		return allEncounters;
	}

	static {
		allEncounters = new ArrayList<>();
		
		// Modded encounters:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/encounters");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractEncounter encounter = new AbstractEncounter(innerEntry.getValue(), entry.getKey(), true) {
						@Override
						protected DialogueNode initialiseEncounter(EncounterType node) {
							return null;
						}
						@Override
						public Map<EncounterType, Float> getDialogues() {
							return null;
						}};
					allEncounters.add(encounter);
					encounterToIdMap.put(encounter, innerEntry.getKey());
					idToEncounterMap.put(innerEntry.getKey(), encounter);
//					System.out.println("modded encounter: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading modded encounter failed at 'Encounter'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res encounters:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/encounters");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractEncounter encounter = new AbstractEncounter(innerEntry.getValue(), entry.getKey(), false) {
						@Override
						protected DialogueNode initialiseEncounter(EncounterType node) {
							return null;
						}
						@Override
						public Map<EncounterType, Float> getDialogues() {
							return null;
						}};
					String id = "innoxia_"+innerEntry.getKey();
					allEncounters.add(encounter);
					encounterToIdMap.put(encounter, id);
					idToEncounterMap.put(id, encounter);
//					System.out.println("res encounter: "+id);
				} catch(Exception ex) {
					System.err.println("Loading encounter failed at 'Encounter'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// Hard-coded encounters (all those up above):
		
		Field[] fields = Encounter.class.getFields();
		
		for(Field f : fields){
			if (AbstractEncounter.class.isAssignableFrom(f.getType())) {
				
				AbstractEncounter encounter;
				
				try {
					encounter = ((AbstractEncounter) f.get(null));

					encounterToIdMap.put(encounter, f.getName());
					idToEncounterMap.put(f.getName(), encounter);
					allEncounters.add(encounter);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		// Add additional place types which can trigger encounters to the 'addedEncounters' map
		for(AbstractEncounter encounter : allEncounters) {
			if(encounter.getPlaceTypeIds()!=null) {
				for(String placeId : encounter.getPlaceTypeIds()) {
					addedEncounters.putIfAbsent(placeId, new ArrayList<>());
					addedEncounters.get(placeId).add(encounter);
				}
			}
		}
	}
}
