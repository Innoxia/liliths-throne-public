package com.lilithsthrone.game.dialogue.encounters;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.DominionSuccubusAttacker;
import com.lilithsthrone.game.character.npc.dominion.EnforcerPatrol;
import com.lilithsthrone.game.character.npc.dominion.HarpyNestsAttacker;
import com.lilithsthrone.game.character.npc.dominion.Lumi;
import com.lilithsthrone.game.character.npc.dominion.RentalMommy;
import com.lilithsthrone.game.character.npc.submission.BatMorphCavernAttacker;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.character.npc.submission.SlimeCavernAttacker;
import com.lilithsthrone.game.character.npc.submission.SubmissionAttacker;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DominionExpressCentaurDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.EnforcerAlleywayDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.VengarCaptiveDialogue;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
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
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5.5
 * @author Innoxia
 */
public enum Encounter {

	LILAYAS_HOME_CORRIDOR(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f))) {
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if(node == EncounterType.SLAVE_USES_YOU && Main.game.getCharactersPresent().isEmpty()) {
				
				List<NPC> slaves = new ArrayList<>();
				List<NPC> hornySlaves = new ArrayList<>();
				
				for(String id : Main.game.getPlayer().getSlavesOwned()) {
					try {
						NPC slave = (NPC) Main.game.getNPCById(id);
						if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER)
								&& slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.IDLE
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
					return SlaveDialogue.getSlaveUsesYou(hornySlaves.get(0));
					
				} else if(!slaves.isEmpty()) {
					Collections.shuffle(slaves);
					return SlaveDialogue.getSlaveUsesYou(slaves.get(0));
				}
				
				return null;
				
			} else {
				return null;
			}
		}
	},
	
	
	DOMINION_STREET(null) {
		
		@Override
		public Map<EncounterType, Float> getDialogues() {
			if(getSlaveWantingToUseYouInDominion()!=null) {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.DOMINION_STORM_ATTACK, 15f),
						new Value<EncounterType, Float>(EncounterType.SPECIAL_DOMINION_CULTIST, 5f),
						new Value<EncounterType, Float>(EncounterType.DOMINION_EXPRESS_CENTAUR, 1f),
						new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f),
						new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_FIND_HAPPINESS, 10f));
			} else {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.DOMINION_STORM_ATTACK, 15f),
						new Value<EncounterType, Float>(EncounterType.SPECIAL_DOMINION_CULTIST, 5f),
						new Value<EncounterType, Float>(EncounterType.DOMINION_EXPRESS_CENTAUR, 1f),
						new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_FIND_HAPPINESS, 10f));
			}
		}
		
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if(node == EncounterType.DOMINION_STORM_ATTACK && Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				NPC npc = new DominionAlleywayAttacker(Gender.getGenderFromUserPreferences(false, false));
				try {
					Main.game.addNPC(npc, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Main.game.setActiveNPC(npc);
				return Main.game.getActiveNPC().getEncounterDialogue();
			}
			
			if(node == EncounterType.SPECIAL_DOMINION_CULTIST
					&& Main.game.getCurrentWeather() != Weather.MAGIC_STORM
					&& Main.game.getDateNow().getMonth().equals(Month.OCTOBER)
					&& Main.game.getNumberOfWitches()<4
					&& Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.DOMINION_STREET)) {
				
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

			if(node==EncounterType.DOMINION_EXPRESS_CENTAUR
					&& Main.game.getCurrentWeather() != Weather.MAGIC_STORM) {
				AbstractClothing collar = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
				if(collar!=null && collar.getClothingType().getId().equals("innoxia_neck_filly_choker")) { // When wearing filly choker, get approached by horny centaurs:
					return DominionExpressCentaurDialogue.initEncounter(); // Can return null if player cannot access mouth or anus.
				}
			}
			
			if(node == EncounterType.DOMINION_STREET_FIND_HAPPINESS
					&& Main.game.getPlayer().getName(false).equalsIgnoreCase("Kinariu")
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.foundHappiness)) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.foundHappiness, true);
				return DominionEncounterDialogue.DOMINION_STREET_FIND_HAPPINESS;
				
			}
			
			if(node == EncounterType.SLAVE_USES_YOU && Main.game.getCharactersPresent().isEmpty() && Main.game.getCurrentWeather() != Weather.MAGIC_STORM) {
				NPC slave = getSlaveWantingToUseYouInDominion();
				if(slave==null) {
					return null;
				}
				return SlaveDialogue.getSlaveUsesYouStreet(slave);
			}
			
			return null;
		}
	},
	
	DOMINION_BOULEVARD(null) {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			return Util.newHashMapOfValues(
					new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_RENTAL_MOMMY, 10f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_PILL_HANDOUT, 5f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_EXPRESS_CENTAUR, 1f),
					getSlaveWantingToUseYouInDominion()!=null
						?new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f)
						:null);
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
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.VIXENS_VIRILITY), 3+Util.random.nextInt(4), false, true));
					
					return DominionEncounterDialogue.DOMINION_STREET_PILL_HANDOUT;
				}
			}
			
			if(time.getMonth().equals(Month.JUNE) && time.getDayOfMonth()>14 && time.getDayOfMonth()<=21) { // Father's day timing, 3rd week of June
				if(node==EncounterType.DOMINION_STREET_PILL_HANDOUT) {
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.VIXENS_VIRILITY), 3+Util.random.nextInt(4), false, true));
					
					return DominionEncounterDialogue.DOMINION_STREET_PILL_HANDOUT;
				}
			}

			if(node==EncounterType.DOMINION_EXPRESS_CENTAUR) {
				AbstractClothing collar = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
				if(collar!=null && collar.getClothingType().getId().equals("innoxia_neck_filly_choker")) { // When wearing filly choker, get approached by horny centaurs:
					return DominionExpressCentaurDialogue.initEncounter(); // Can return null if player cannot access mouth or anus.
				}
			}
			
			if(node == EncounterType.SLAVE_USES_YOU && Main.game.getCharactersPresent().isEmpty()) {
				NPC slave = getSlaveWantingToUseYouInDominion();
				if(slave==null) {
					return null;
				}
				return SlaveDialogue.getSlaveUsesYouStreet(slave);
			}
			
			return null;
		}
	},

	DOMINION_ALLEY(null) {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			Map<EncounterType, Float> map = Util.newHashMapOfValues(
					new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_ITEM, 3f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_CLOTHING, 2f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_WEAPON, 1f),
					getSlaveWantingToUseYouInDominion()!=null && Main.game.getCurrentWeather()!=Weather.MAGIC_STORM
						?new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f)
						:null);
			
			if(Main.game.isStarted() && DominionPlaces.isCloseToEnforcerHQ()) {
				map.put(EncounterType.DOMINION_ALLEY_ATTACK, 10f);
				map.put(EncounterType.DOMINION_ALLEY_ENFORCERS, 15f);
			} else {
				map.put(EncounterType.DOMINION_ALLEY_ATTACK, 15f);
				map.put(EncounterType.DOMINION_ALLEY_ENFORCERS, 2.5f);
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
					List<NPC> offspringAvailable = Main.game.getOffspringNotSpawned(
						npc-> (npc.getSubspecies()==Subspecies.HALF_DEMON
								?(npc.getHalfDemonSubspecies().getWorldLocations().keySet().contains(WorldType.DOMINION))
								:(npc.getSubspecies().getWorldLocations().keySet().contains(WorldType.DOMINION)
										|| npc.getSubspecies()==Subspecies.ANGEL
										|| npc.getSubspecies()==Subspecies.FOX_ASCENDANT
										|| npc.getSubspecies()==Subspecies.FOX_ASCENDANT_ARCTIC
										|| npc.getSubspecies()==Subspecies.FOX_ASCENDANT_FENNEC)));
					
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
				Main.game.setActiveNPC(npc);
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			} else if(node == EncounterType.DOMINION_FIND_ITEM) {
				if(!Main.game.isSillyModeEnabled() || Math.random()<0.99f) {
					randomItem = Main.game.getItemGen().generateItem(ItemType.getDominionAlleywayItems().get(Util.random.nextInt(ItemType.getDominionAlleywayItems().size())));
					
				} else {
					if(Math.random()<0.5f) {
						randomItem = Main.game.getItemGen().generateItem(ItemType.EGGPLANT);
					} else {
						randomItem = Main.game.getItemGen().generateItem(ItemType.FEMININE_BURGER);
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
				
			} else if(node == EncounterType.DOMINION_ALLEY_ENFORCERS && Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
				spawnEnforcers();
				return EnforcerAlleywayDialogue.ENFORCER_ALLEYWAY_START;
				
			} else if(node == EncounterType.SLAVE_USES_YOU && Main.game.getCharactersPresent().isEmpty()) {
				NPC slave = getSlaveWantingToUseYouInDominion();
				if(slave==null) {
					return null;
				}
				return SlaveDialogue.getSlaveUsesYouAlleyway(slave);
			}
			
			return null;
		}
	},
	
	DOMINION_DARK_ALLEY(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.DOMINION_ALLEY_ATTACK, 15f))) {

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
	},
	
	DOMINION_CANAL(null) {
		@Override
		public Map<EncounterType, Float> getDialogues() {
			return Util.newHashMapOfValues(
					new Value<EncounterType, Float>(EncounterType.DOMINION_ALLEY_ATTACK, 10f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_ITEM, 3f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_CLOTHING, 2f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_WEAPON, 1f),
					new Value<EncounterType, Float>(EncounterType.DOMINION_ALLEY_ENFORCERS, 2.5f),
					getSlaveWantingToUseYouInDominion()!=null && Main.game.getCurrentWeather()!=Weather.MAGIC_STORM
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
					List<NPC> offspringAvailable = Main.game.getOffspringNotSpawned(
							npc-> (npc.getSubspecies()==Subspecies.HALF_DEMON
								?(npc.getHalfDemonSubspecies().getWorldLocations().keySet().contains(WorldType.DOMINION)
										|| npc.getHalfDemonSubspecies()==Subspecies.SLIME
										|| npc.getHalfDemonSubspecies()==Subspecies.ALLIGATOR_MORPH
										|| npc.getHalfDemonSubspecies()==Subspecies.RAT_MORPH)
								:(npc.getSubspecies().getWorldLocations().keySet().contains(WorldType.DOMINION)
										|| npc.getSubspecies()==Subspecies.SLIME
										|| npc.getSubspecies()==Subspecies.ALLIGATOR_MORPH
										|| npc.getSubspecies()==Subspecies.RAT_MORPH)));
					
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
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			}else if(node == EncounterType.DOMINION_FIND_ITEM) {
				if(!Main.game.isSillyModeEnabled() || Math.random()<0.99f) {
					randomItem = Main.game.getItemGen().generateItem(ItemType.getDominionAlleywayItems().get(Util.random.nextInt(ItemType.getDominionAlleywayItems().size())));
					
				} else {
					if(Math.random()<0.5f) {
						randomItem = Main.game.getItemGen().generateItem(ItemType.EGGPLANT);
					} else {
						randomItem = Main.game.getItemGen().generateItem(ItemType.FEMININE_BURGER);
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
				
			} else if(node == EncounterType.DOMINION_ALLEY_ENFORCERS && Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
				spawnEnforcers();
				return EnforcerAlleywayDialogue.ENFORCER_ALLEYWAY_START;
				
			} else if(node == EncounterType.SLAVE_USES_YOU && Main.game.getCharactersPresent().isEmpty()) {
				NPC slave = getSlaveWantingToUseYouInDominion();
				if(slave==null) {
					return null;
				}
				return SlaveDialogue.getSlaveUsesYouAlleyway(slave);
			}
			
			return null;
		}
	},
	
	DOMINION_EXPRESS(null) {
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
	},
	
	HARPY_NEST_WALKWAYS(null) {
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
					List<NPC> offspringAvailable = Main.game.getOffspringNotSpawned(
							npc-> (npc.getSubspecies()==Subspecies.HALF_DEMON
								?(npc.getHalfDemonSubspecies().getWorldLocations().keySet().contains(WorldType.HARPY_NEST))
								:(npc.getSubspecies().getWorldLocations().keySet().contains(WorldType.HARPY_NEST))));
					
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
					randomItem = Main.game.getItemGen().generateItem(ItemType.SEX_INGREDIENT_HARPY_PERFUME);
					
				} else {
					randomItem = Main.game.getItemGen().generateItem(ItemType.RACE_INGREDIENT_HARPY);
				}
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem((AbstractItem) randomItem);
				return DominionEncounterDialogue.HARPY_NESTS_FIND_ITEM;
				
			}
			
			return null;
		}
	},
	
	HARPY_NEST_LOOK_FOR_TROUBLE(null) {
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
					List<NPC> offspringAvailable = Main.game.getOffspringNotSpawned(
							npc-> (npc.getSubspecies()==Subspecies.HALF_DEMON
								?(npc.getHalfDemonSubspecies().getWorldLocations().keySet().contains(WorldType.HARPY_NEST))
								:(npc.getSubspecies().getWorldLocations().keySet().contains(WorldType.HARPY_NEST))));
					
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
					randomItem = Main.game.getItemGen().generateItem(ItemType.SEX_INGREDIENT_HARPY_PERFUME);
					
				} else {
					randomItem = Main.game.getItemGen().generateItem(ItemType.RACE_INGREDIENT_HARPY);
				}
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem((AbstractItem) randomItem);
				return DominionEncounterDialogue.HARPY_NESTS_FIND_ITEM;
				
			}
			
			return null;
		}
	},
	
	SUBMISSION_TUNNELS(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.SUBMISSION_TUNNEL_ATTACK, 20f),
			new Value<EncounterType, Float>(EncounterType.SUBMISSION_FIND_ITEM, 10f))) {
		
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
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
						imp.setLevel(3+Util.random.nextInt(4)); // 3-6
						Main.game.addNPC(imp, false);
						impGroup.add(imp);

						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.getGenderFromUserPreferences(false, false), false);
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
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
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
						imp.setLevel(6+Util.random.nextInt(4)); // 6-8
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.getGenderFromUserPreferences(false, false), false);
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
						imp.setLevel(3+Util.random.nextInt(4)); // 3-6
						Main.game.addNPC(imp, false);
						impGroup.add(imp);

						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.getGenderFromUserPreferences(false, false), false);
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
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
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
						imp.setLevel(8+Util.random.nextInt(3)); // 8-10
						Main.game.addNPC(imp, false);
						impGroup.add(imp);

						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.F_V_B_FEMALE, false);
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
						imp.setLevel(6+Util.random.nextInt(3)); // 6-8
						Main.game.addNPC(imp, false);
						impGroup.add(imp);

						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.F_V_B_FEMALE, false);
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
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
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
						imp.setLevel(8+Util.random.nextInt(3)); // 8-10
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.M_P_MALE, false);
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
						imp.setLevel(6+Util.random.nextInt(3)); // 6-8
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						
						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.M_P_MALE, false);
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
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
					List<NPC> offspringAvailable = Main.game.getOffspringNotSpawned(
							npc-> (npc.getSubspecies()==Subspecies.HALF_DEMON
								?(npc.getHalfDemonSubspecies().getWorldLocations().keySet().contains(WorldType.SUBMISSION))
								:(npc.getSubspecies().getWorldLocations().keySet().contains(WorldType.SUBMISSION))));
					
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
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			} else if (node == EncounterType.SUBMISSION_FIND_ITEM) {
				
				randomItem = Main.game.getItemGen().generateItem(ItemType.getSubmissionTunnelItems().get(Util.random.nextInt(ItemType.getSubmissionTunnelItems().size())));
				
				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem((AbstractItem) randomItem);
				return SubmissionEncounterDialogue.FIND_ITEM;
				
			} else {
				return null;
			}
		}
	},
	
	BAT_CAVERN(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.BAT_CAVERN_BAT_ATTACK, 8f),
			new Value<EncounterType, Float>(EncounterType.BAT_CAVERN_SLIME_ATTACK, 6f),
			new Value<EncounterType, Float>(EncounterType.BAT_CAVERN_FIND_ITEM, 6f), 
                        new Value<EncounterType, Float>(EncounterType.BAT_CAVERN_REBEL_BASE_DISCOVERED, 8f))){

		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if (node == EncounterType.BAT_CAVERN_BAT_ATTACK) {

				// Prioritise re-encountering the NPC on this tile:
				List<NPC> encounterPossibilities = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
				if(!encounterPossibilities.isEmpty()) {
					NPC encounterNpc = Util.randomItemFrom(encounterPossibilities);
					Main.game.setActiveNPC(encounterNpc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
//				TODO Add offspring encounters
				
				Main.game.setActiveNPC(new BatMorphCavernAttacker(Gender.getGenderFromUserPreferences(false, false)));
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
				
				Main.game.setActiveNPC(new SlimeCavernAttacker(Gender.getGenderFromUserPreferences(false, false)));
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
                        } else if (node == EncounterType.BAT_CAVERN_REBEL_BASE_DISCOVERED && 
                                    !(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_REBEL_BASE) || Main.game.getPlayer().isQuestFailed(QuestLine.SIDE_REBEL_BASE)) && 
                                    !(!Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT) && Math.random()<0.5f)) {

                                return BatCavernsEncounterDialogue.REBEL_BASE_DISCOVERED;
				
			} else {
				return null;
			}
		}
	},
	
	// chance of encounters (in likelihood order):
	//  If night, always taken to bedroom. If ready to give birth, birthing & sleep, else fucked & sleep
	// 	Rats get you to serve drinks
	//  Rats grope you
	//  Vengar fucks you in front of everyone
	//  A rat fucks you
	// 	Rats fuck you
	// 	Rat gets you to perform oral under table
	VENGAR_CAPTIVE_HALL(null) {
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
	},

	//  SS make you clean room
	// 	Shadow & Silence use you
	//  SS forbid you from sulking in room (if already cleaned)
	VENGAR_CAPTIVE_BEDROOM(null) {
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
	},
	
	;
	
	private static void spawnEnforcers() {
		List<List<String>> savedEnforcerIds = Main.game.getSavedEnforcers(WorldType.DOMINION);
		
		// Keep 4 sets of Enforcers saved
		float chanceOfNewEnforcers = 1f - (0.25f * savedEnforcerIds.size());
		if(Math.random()<chanceOfNewEnforcers) {
			try {
				List<String> enforcerIds = new ArrayList<>();
				EnforcerPatrol npc = new EnforcerPatrol(Occupation.NPC_ENFORCER_PATROL_CONSTABLE, Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(npc, false);
				npc.setLevel(9+Util.random.nextInt(4)); // 9-12
				((EnforcerPatrol)npc).setWeapons("dsg_eep_pbweap_pbpistol");
				enforcerIds.add(npc.getId());
				
				EnforcerPatrol npc2 = new EnforcerPatrol(Occupation.NPC_ENFORCER_PATROL_CONSTABLE, Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(npc2, false);
				npc2.setLevel(4+Util.random.nextInt(5)); // 4-8
				((EnforcerPatrol)npc2).setWeapons("dsg_eep_taser_taser");
				enforcerIds.add(npc2.getId());
				
				Main.game.addSavedEnforcers(WorldType.DOMINION, enforcerIds);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			List<String> enforcerIds = Util.randomItemFrom(savedEnforcerIds);
			for(String id : enforcerIds) {
				try {
					Main.game.getNPCById(id).setLocation(Main.game.getPlayer(), false);
				} catch (Exception e) {
					System.err.println("Error in Encounter.spawnEnforcers()");
					e.printStackTrace();
				}
			}
		}
	}
	
	private static DialogueNode SpawnAndStartChildHere(List<NPC> offspringAvailable) {
		NPC offspring = offspringAvailable.get(Util.random.nextInt(offspringAvailable.size()));
		Main.game.getOffspringSpawned().add(offspring);

		offspring.setLocation(Main.game.getPlayer(), true);
		
		offspring.equipClothing(EquipClothingSetting.getAllClothingSettings());
		
		Main.game.setActiveNPC(offspring);

		return Main.game.getActiveNPC().getEncounterDialogue();
	}
	
	private static NPC getSlaveWantingToUseYouInDominion() {
		List<NPC> slaves = new ArrayList<>();
		List<NPC> hornySlaves = new ArrayList<>();
		
		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			try {
				NPC slave = (NPC) Main.game.getNPCById(id);
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER)
						&& slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.IDLE
						&& slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_OUTSIDE_FREEDOM)
						&& slave.isAttractedTo(Main.game.getPlayer())) {
					if(slave.getLastTimeHadSex()+60*4<Main.game.getMinutesPassed()) {
						slaves.add(slave);
					}
					if(slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)) {
						hornySlaves.add(slave);
					}
				}
			} catch (Exception e) {
				System.err.println("Main.game.getNPCById("+id+") returning null in getSlaveWantingToUseYouInDominion()");
			}
		}
		slaves.removeIf((slave) -> slave.getWorldLocation()==WorldType.SLAVER_ALLEY && (!Main.game.getPlayer().getLocationPlace().getPlaceType().isPopulated() || slave.hasFetish(Fetish.FETISH_EXHIBITIONIST)));
		hornySlaves.removeIf((slave) -> slave.getWorldLocation()==WorldType.SLAVER_ALLEY && (!Main.game.getPlayer().getLocationPlace().getPlaceType().isPopulated() || slave.hasFetish(Fetish.FETISH_EXHIBITIONIST)));
		
		if(!hornySlaves.isEmpty()) {
			Collections.shuffle(hornySlaves);
			return hornySlaves.get(0);
			
		} else if(!slaves.isEmpty()) {
			Collections.shuffle(slaves);
			return slaves.get(0);
		}
		
		return null;
	}

	private static AbstractCoreItem randomItem;

	private static final double INCEST_ENCOUNTER_RATE = 0.2f;

	private static double IncestEncounterRate() {
//		if (!Main.game.isIncestEnabled()) {
//			return -1;
//		}
		return INCEST_ENCOUNTER_RATE;
	}

	private Map<EncounterType, Float> dialogues;

	private Encounter(Map<EncounterType, Float> dialogues) {
		this.dialogues = dialogues;
	}

	protected abstract DialogueNode initialiseEncounter(EncounterType node);

	/**
	 * Returns a random encounter from the list, or null if no encounter was selected.
	 * 
	 * @param forceEncounter Forces an encounter to be selected. (Will still return null if the encounter list is empty.)
	 * @return null if no encounter.
	 */
	public DialogueNode getRandomEncounter(boolean forceEncounter) {
		return getBaseRandomEncounter(forceEncounter);
	}
	
	public Map<EncounterType, Float> getDialogues() {
		return dialogues;
	}

	protected DialogueNode getBaseRandomEncounter(boolean forceEncounter) {
		float opportunisticMultiplier = 1;
		if(Main.game.isOpportunisticAttackersEnabled()) {
			// lust: linear boost; 25% max
			opportunisticMultiplier += Main.game.getPlayer().getLust() / 200;
			// health: linear boost; 25% (theoretical) max
			opportunisticMultiplier += 0.25f - Main.game.getPlayer().getHealthPercentage() * 0.25f;
			// smelly body: 25% boost
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.BODY_CUM) || Main.game.getPlayer().hasStatusEffect(StatusEffect.BODY_CUM_MASOCHIST)) {
				opportunisticMultiplier += 0.25f;
			}
			// smelly clothes: 25% boost
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CLOTHING_CUM) || Main.game.getPlayer().hasStatusEffect(StatusEffect.CLOTHING_CUM_MASOCHIST)) {
				opportunisticMultiplier += 0.25f;
			}
			// exposure: 50% or 75% boost
			if(!Collections.disjoint(
					Util.newArrayListOfValues(
						StatusEffect.EXPOSED_PLUS_BREASTS,
						StatusEffect.FETISH_EXHIBITIONIST_PLUS_BREASTS),
					Main.game.getPlayer().getStatusEffects())) {
				opportunisticMultiplier += 0.75f;
				
			} else if(!Collections.disjoint(
					Util.newArrayListOfValues(
						StatusEffect.EXPOSED,
						StatusEffect.EXPOSED_BREASTS,
						StatusEffect.FETISH_EXHIBITIONIST,
						StatusEffect.FETISH_EXHIBITIONIST_BREASTS),
					Main.game.getPlayer().getStatusEffects())) {
				opportunisticMultiplier += 0.5f;
			}
			// drunk: 50% boost
			if(!Collections.disjoint(
					Util.newArrayListOfValues(
						StatusEffect.DRUNK_3,
						StatusEffect.DRUNK_4,
						StatusEffect.DRUNK_5),
					Main.game.getPlayer().getStatusEffects())) {
				opportunisticMultiplier += 0.5f;
			}
		}
		
		float total = 0;
		float opportunisticIncrease = 0;
		Map<EncounterType, Float> finalMap = new HashMap<>();
		for(Entry<EncounterType, Float> e : getDialogues().entrySet()) { // Iterate through the base encounter map, apply opportunisticMultiplier if applicable, and create a new 'finalMap' of these weighted chances.
			float weighting = e.getValue();
			if(e.getKey().isOpportunistic()) {
				weighting *= opportunisticMultiplier;
				opportunisticIncrease+=opportunisticMultiplier;
			}
			total+=weighting;
			finalMap.put(e.getKey(), weighting);
		}
		
		if(forceEncounter || Math.random()*(100+opportunisticIncrease)<total) {
			EncounterType encounter = Util.getRandomObjectFromWeightedFloatMap(finalMap);
			return initialiseEncounter(encounter);
		}
		

		return null;
	}

	public static AbstractCoreItem getRandomItem() {
		return randomItem;
	}

}
