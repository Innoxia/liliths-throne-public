package com.lilithsthrone.game.dialogue.encounters;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.DominionSuccubusAttacker;
import com.lilithsthrone.game.character.npc.dominion.HarpyNestsAttacker;
import com.lilithsthrone.game.character.npc.dominion.Lumi;
import com.lilithsthrone.game.character.npc.dominion.RentalMommy;
import com.lilithsthrone.game.character.npc.submission.BatMorphCavernAttacker;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.character.npc.submission.SlimeCavernAttacker;
import com.lilithsthrone.game.character.npc.submission.SubmissionAttacker;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.TunnelImpsDialogue;
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
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.4
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
					} catch (Exception e) {
						System.err.println("Main.game.getNPCById("+id+") returning null in Encounter.LILAYAS_HOME_CORRIDOR");
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
	
	
	DOMINION_STREET(null) {
		
		@Override
		public Map<EncounterType, Float> getDialogues() {
			if(getSlaveWantingToUseYouInDominion()!=null) {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.DOMINION_STORM_ATTACK, 15f),
						new Value<EncounterType, Float>(EncounterType.SPECIAL_DOMINION_CULTIST, 5f),
						new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f),
						new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_FIND_HAPPINESS, 10f));
			} else {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.DOMINION_STORM_ATTACK, 15f),
						new Value<EncounterType, Float>(EncounterType.SPECIAL_DOMINION_CULTIST, 5f),
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

//			System.out.println(node);
//			if(node == EncounterType.SPECIAL_DOMINION_CULTIST) {
//				System.out.println(Main.game.getCurrentWeather() != Weather.MAGIC_STORM);
//				System.out.println(Main.game.getDateNow().getMonth().equals(Month.OCTOBER));
//				System.out.println(Main.game.getNumberOfWitches()<4);
//				System.out.println(Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.DOMINION_STREET));
//			}
			
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
				Main.game.setActiveNPC(getSlaveWantingToUseYouInDominion());
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
				return SlaveDialogue.SLAVE_USES_YOU_STREETS;
				
			}
			
			return null;
		}
	},
	
	DOMINION_BOULEVARD(null) {
		
		@Override
		public Map<EncounterType, Float> getDialogues() {
			if(getSlaveWantingToUseYouInDominion()!=null) {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_RENTAL_MOMMY, 10f),
						new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_PILL_HANDOUT, 5f),
						new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f));
			} else {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_RENTAL_MOMMY, 10f),
						new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_PILL_HANDOUT, 5f));
			}
		}
		
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			LocalDateTime time = Main.game.getDateNow();
			if(time.getMonth().equals(Month.MAY) && time.getDayOfMonth()>7 && time.getDayOfMonth()<=14) { // Mother's day timing, 2nd week of May
				if(node == EncounterType.DOMINION_STREET_RENTAL_MOMMY
						&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.mommyFound)
						&& Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
					Main.game.setActiveNPC(Main.game.getNpc(RentalMommy.class));
					Main.game.getNpc(RentalMommy.class).setLocation(WorldType.DOMINION, Main.game.getPlayer().getLocation(), true);
					
					try {
						Main.game.addNPC(Main.game.getActiveNPC(), false);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return Main.game.getActiveNPC().getEncounterDialogue();
					
				} else if(node==EncounterType.DOMINION_STREET_PILL_HANDOUT
						&& Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
					
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.VIXENS_VIRILITY), 3+Util.random.nextInt(4), false, true));
					
					return DominionEncounterDialogue.DOMINION_STREET_PILL_HANDOUT;
				}
			}
			
			if(time.getMonth().equals(Month.JUNE) && time.getDayOfMonth()>14 && time.getDayOfMonth()<=21) { // Father's day timing, 3rd week of June
				if(node==EncounterType.DOMINION_STREET_PILL_HANDOUT
						&& Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
					
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.VIXENS_VIRILITY), 3+Util.random.nextInt(4), false, true));
					
					return DominionEncounterDialogue.DOMINION_STREET_PILL_HANDOUT;
				}
			}
			
			if(node == EncounterType.SLAVE_USES_YOU && Main.game.getCharactersPresent().isEmpty() && Main.game.getCurrentWeather() != Weather.MAGIC_STORM) {
				NPC slave = getSlaveWantingToUseYouInDominion();
				if(slave==null) {
					return null;
				}
				Main.game.setActiveNPC(getSlaveWantingToUseYouInDominion());
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
				return SlaveDialogue.SLAVE_USES_YOU_STREETS;
				
			}
			
			return null;
		}
	},

	DOMINION_ALLEY(null) {

		@Override
		public Map<EncounterType, Float> getDialogues() {
			if(getSlaveWantingToUseYouInDominion()!=null) {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.DOMINION_ALLEY_ATTACK, 15f),
						new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_ITEM, 3f),
						new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_CLOTHING, 2f),
						new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_WEAPON, 1f),
						new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f));
			} else {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.DOMINION_ALLEY_ATTACK, 15f),
						new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_ITEM, 3f),
						new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_CLOTHING, 2f),
						new Value<EncounterType, Float>(EncounterType.DOMINION_FIND_WEAPON, 1f));
			}
		}
		
		@Override
		protected DialogueNode initialiseEncounter(EncounterType node) {
			if (node == EncounterType.DOMINION_ALLEY_ATTACK) {
				
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
						npc-> (npc.getSubspecies().getWorldLocations().keySet().contains(WorldType.DOMINION)
								|| npc.getSubspecies()==Subspecies.ANGEL
								|| npc.getSubspecies()==Subspecies.FOX_ASCENDANT
								|| npc.getSubspecies()==Subspecies.FOX_ASCENDANT_FENNEC));
					
					if(!offspringAvailable.isEmpty()) {
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
					

			} else if (node == EncounterType.DOMINION_FIND_ITEM) {
				
				if(Math.random()<0.995f) {
					randomItem = AbstractItemType.generateItem(ItemType.getDominionAlleywayItems().get(Util.random.nextInt(ItemType.getDominionAlleywayItems().size())));
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
					List<AbstractClothingType> randomClothingList = new ArrayList<>(ClothingType.getAllClothing());
					randomClothingList.removeIf((clothing) ->
							(!clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_KATE)
							&& !clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN)
							&& !clothing.getDefaultItemTags().contains(ItemTag.DOMINION_ALLEYWAY_SPAWN))
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
				
			} else if(node == EncounterType.SLAVE_USES_YOU && Main.game.getCharactersPresent().isEmpty()) {
				NPC slave = getSlaveWantingToUseYouInDominion();
				if(slave==null) {
					return null;
				}
				Main.game.setActiveNPC(getSlaveWantingToUseYouInDominion());
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
				return SlaveDialogue.SLAVE_USES_YOU_ALLEYWAY;
				
			} else {
				return null;
			}
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
	
	//TODO
	DOMINION_CANAL(null) {

		@Override
		public Map<EncounterType, Float> getDialogues() {
			if(getSlaveWantingToUseYouInDominion()!=null) {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.DOMINION_ALLEY_ATTACK, 10f),
						new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f));
			} else {
				return Util.newHashMapOfValues(
						new Value<EncounterType, Float>(EncounterType.DOMINION_ALLEY_ATTACK, 10f));
			}
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
						npc -> npc.getSubspecies().getWorldLocations().keySet().contains(WorldType.DOMINION)
							|| npc.getSubspecies()==Subspecies.SLIME
							|| npc.getSubspecies()==Subspecies.ALLIGATOR_MORPH
							|| npc.getSubspecies()==Subspecies.RAT_MORPH);
					
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
				
			} else if(node == EncounterType.SLAVE_USES_YOU && Main.game.getCharactersPresent().isEmpty()) {
				NPC slave = getSlaveWantingToUseYouInDominion();
				if(slave==null) {
					return null;
				}
				Main.game.setActiveNPC(getSlaveWantingToUseYouInDominion());
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
				return SlaveDialogue.SLAVE_USES_YOU_ALLEYWAY;
			}
			return null;
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
						npc -> npc.getSubspecies().getWorldLocations().keySet().contains(WorldType.HARPY_NEST));
					
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
			
			if (node == EncounterType.HARPY_NEST_ATTACK_STORM) {

				for (NPC npc : Main.game.getNonCompanionCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
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
						npc -> npc.getSubspecies().getWorldLocations().keySet().contains(WorldType.HARPY_NEST));
					
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
			
			if (node == EncounterType.HARPY_NEST_ATTACK_STORM) {

				for (NPC npc : Main.game.getNonCompanionCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
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
		protected DialogueNode initialiseEncounter(EncounterType node) {
			
			if (node == EncounterType.SUBMISSION_TUNNEL_ATTACK) {

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
						AbstractWeapon pipe = AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe"));
						imp.equipMainWeaponFromNowhere(pipe);
						imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield"), pipe.getDamageType()));
						
						// Alpha imp:
						imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
						imp.setLevel(6+Util.random.nextInt(3)); // 6-8
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.OFFHAND_BOW_AND_ARROW, Util.randomItemFrom(new DamageType[] {DamageType.POISON, DamageType.FIRE})));

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
					
					TunnelImpsDialogue.setImpGroup(impGroup);
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
					
					TunnelImpsDialogue.setImpGroup(impGroup);
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
						imp.setBreastSize(CupSize.M);
						
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
					
					TunnelImpsDialogue.setImpGroup(impGroup);
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
						AbstractWeapon pipe = AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe"));
						imp.equipMainWeaponFromNowhere(pipe);
						imp.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield"), pipe.getDamageType()));
						
						// Alpha imp:
						imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
						imp.setLevel(8+Util.random.nextInt(3)); // 8-10
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));

						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.M_P_MALE, false);
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
						imp.setLevel(6+Util.random.nextInt(3)); // 6-8
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));

						// Normal imp:
						imp = new ImpAttacker(Subspecies.IMP, Gender.M_P_MALE, false);
						impAdjectives.add(CharacterUtils.setGenericName(imp, impAdjectives));
						imp.setLevel(4+Util.random.nextInt(3)); // 4-6
						Main.game.addNPC(imp, false);
						impGroup.add(imp);
						imp.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
						
						for(GameCharacter impGangMember : impGroup) {
							((NPC) impGangMember).equipClothing(EquipClothingSetting.getAllClothingSettings());
						}
						
					} catch (Exception e) {
					}
					
					TunnelImpsDialogue.setImpGroup(impGroup);
					return ((NPC) impGroup.get(0)).getEncounterDialogue();
					
				}

				// Prioritise re-encountering the NPC on this tile:
				List<NPC> encounterPossibilities = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
				if(!encounterPossibilities.isEmpty()) {
					NPC encounterNpc = Util.randomItemFrom(encounterPossibilities);
					Main.game.setActiveNPC(encounterNpc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
				if(Math.random()<IncestEncounterRate()) {
					List<NPC> offspringAvailable = Main.game.getOffspringNotSpawned(
						npc -> npc.getSubspecies().getWorldLocations().keySet().contains(WorldType.SUBMISSION));
					
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
				
				randomItem = AbstractItemType.generateItem(ItemType.getSubmissionTunnelItems().get(Util.random.nextInt(ItemType.getSubmissionTunnelItems().size())));
				
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
					randomItem = AbstractItemType.generateItem(ItemType.MUSHROOM);
				} else {
					randomItem = AbstractItemType.generateItem(ItemType.getBatCavernItems().get(Util.random.nextInt(ItemType.getBatCavernItems().size())));
				}

				Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem(randomItem);
				return BatCavernsEncounterDialogue.FIND_ITEM;
				
			} else {
				return null;
			}
		}
	};

	private static DialogueNode SpawnAndStartChildHere(List<NPC> offspringAvailable) {
		NPC offspring = offspringAvailable.get(Util.random.nextInt(offspringAvailable.size()));
		Main.game.getOffspringSpawned().add(offspring);

		offspring.setLocation(Main.game.getPlayer(), true);

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

	private static AbstractItem randomItem;
	private static AbstractClothing randomClothing;
	private static AbstractWeapon randomWeapon;

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
		float r = (float) (Math.random() * 100);
		float total = 0;
		
		if(forceEncounter) {
			r = 0;
			for (Entry<EncounterType, Float> e : getDialogues().entrySet()) {
				r += e.getValue();
			}
			r *= Math.random();
		}

		float opportunisticMultiplier = 1;
		if(Main.game.isOpportunisticAttackersEnabled()) {
			// lust: linear boost; 25% max
			opportunisticMultiplier += Main.game.getPlayer().getLust() / 200;
			// health: linear boost; 25% (theoretical) max
			opportunisticMultiplier += 0.25f - Main.game.getPlayer().getHealthPercentage() * 0.25f;
			// smelly body: 25% boost
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.BODY_CUM)
					|| Main.game.getPlayer().hasStatusEffect(StatusEffect.BODY_CUM_MASOCHIST))
				opportunisticMultiplier += 0.25f;
			// smelly clothes: 25% boost
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CLOTHING_CUM)
					|| Main.game.getPlayer().hasStatusEffect(StatusEffect.CLOTHING_CUM_MASOCHIST))
				opportunisticMultiplier += 0.25f;
			// exposure: 50% or 75% boost
			if(!Collections.disjoint(Util.newArrayListOfValues(
					StatusEffect.EXPOSED,
					StatusEffect.EXPOSED_BREASTS,
					StatusEffect.FETISH_EXHIBITIONIST,
					StatusEffect.FETISH_EXHIBITIONIST_BREASTS
			), Main.game.getPlayer().getStatusEffects()))
				opportunisticMultiplier += 0.5f;
			if(!Collections.disjoint(Util.newArrayListOfValues(
					StatusEffect.EXPOSED_PLUS_BREASTS,
					StatusEffect.FETISH_EXHIBITIONIST_PLUS_BREASTS
			), Main.game.getPlayer().getStatusEffects()))
				opportunisticMultiplier += 0.75f;
			// drunk: 50% boost
			if(!Collections.disjoint(Util.newArrayListOfValues(
					StatusEffect.DRUNK_3,
					StatusEffect.DRUNK_4,
					StatusEffect.DRUNK_5
			), Main.game.getPlayer().getStatusEffects()))
				opportunisticMultiplier += 0.5f;
		}

		for (Entry<EncounterType, Float> e : getDialogues().entrySet()) {
			EncounterType encounter = e.getKey();
			float encounterChance = e.getValue();
			// opportunistic attackers: compare with amplified chance
			if(encounter.isOpportunistic()) {
				encounterChance *= opportunisticMultiplier;
			}
			if (r <= total + encounterChance) {
				return initialiseEncounter(encounter);
			}
			// add unmodified chance
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
