package com.lilithsthrone.game.dialogue.encounters;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.gender.GenderPreference;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.DominionAlleywayAttacker;
import com.lilithsthrone.game.character.npc.dominion.DominionSuccubusAttacker;
import com.lilithsthrone.game.character.npc.dominion.HarpyNestsAttacker;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.slavery.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.1.88
 * @author Innoxia
 */
public enum Encounter {

	
	LILAYAS_HOME_CORRIDOR(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.SLAVE_USES_YOU, 5f))) {
		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			if(node == EncounterType.SLAVE_USES_YOU && Main.game.getCharactersPresent().isEmpty()) {
				
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
			new Value<EncounterType, Float>(EncounterType.SPECIAL_DOMINION_CULTIST, 5f))) {
		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			if(node == EncounterType.DOMINION_STORM_ATTACK && Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				Main.game.setActiveNPC(new DominionAlleywayAttacker(GenderPreference.getGenderFromUserPreferences()));
	
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			} else if(node == EncounterType.SPECIAL_DOMINION_CULTIST
					&& Main.game.getCurrentWeather() != Weather.MAGIC_STORM
					&& Main.game.getDateNow().getMonth().equals(Month.OCTOBER)
					&& Main.game.getCharactersPresent().isEmpty()
					&& Main.game.getNumberOfWitches()<4
					&& Main.game.getPlayerCell().getPlace().getPlaceType() == PlaceType.DOMINION_STREET) {
				
				Main.game.setActiveNPC(new Cultist());
				
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
				for(NPC npc : Main.game.getCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}
				
				if(Main.game.isIncestEnabled() && Math.random()<0.2f) { // Incest
					List<NPC> offspringAvailable = new ArrayList<>();
					offspringAvailable.addAll(Main.game.getOffspring().stream().filter(npc -> !npc.isSlave()).collect(Collectors.toList()));
					offspringAvailable.removeAll(Main.game.getOffspringSpawned());
					
					if(!offspringAvailable.isEmpty()) {
						NPC offspring = offspringAvailable.get(Util.random.nextInt(offspringAvailable.size()));
						Main.game.getOffspringSpawned().add(offspring);
						
						offspring.setWorldLocation(Main.game.getPlayer().getWorldLocation());
						offspring.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));
						
						Main.game.setActiveNPC(offspring);
						
						return Main.game.getActiveNPC().getEncounterDialogue();
					}
				}
				
				Main.game.setActiveNPC(new DominionAlleywayAttacker(GenderPreference.getGenderFromUserPreferences()));
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return Main.game.getActiveNPC().getEncounterDialogue();
					

			} else if (node == EncounterType.DOMINION_FIND_ITEM) {
				
				if(Math.random()<0.995f) {
					randomItem = AbstractItemType.generateItem(ItemType.commonItems.get(Util.random.nextInt(ItemType.commonItems.size())));
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
					randomClothing = AbstractClothingType.generateClothing(ClothingType.getCommonClothing().get(Util.random.nextInt(ClothingType.getCommonClothing().size())));
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
				
			for (NPC npc : Main.game.getCharactersPresent()) {
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

				for (NPC npc : Main.game.getCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}

				Main.game.setActiveNPC(new HarpyNestsAttacker(GenderPreference.getGenderFromUserPreferences()));
				
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getLocation());
				
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return Main.game.getActiveNPC().getEncounterDialogue();
			}
			
			if (node == EncounterType.HARPY_NEST_ATTACK_STORM) {

				for (NPC npc : Main.game.getCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}

				Main.game.setActiveNPC(new HarpyNestsAttacker(GenderPreference.getGenderFromUserPreferences()));
				
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

				for (NPC npc : Main.game.getCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}

				Main.game.setActiveNPC(new HarpyNestsAttacker(GenderPreference.getGenderFromUserPreferences()));
				
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getLocation());
				
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return Main.game.getActiveNPC().getEncounterDialogue();
			}
			
			if (node == EncounterType.HARPY_NEST_ATTACK_STORM) {

				for (NPC npc : Main.game.getCharactersPresent()) {
					Main.game.setActiveNPC(npc);
					return Main.game.getActiveNPC().getEncounterDialogue();
				}

				Main.game.setActiveNPC(new HarpyNestsAttacker(GenderPreference.getGenderFromUserPreferences()));
				
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
	};

	private static AbstractItem randomItem;
	private static AbstractClothing randomClothing;
	private static AbstractWeapon randomWeapon;

	private Map<EncounterType, Float> dialogues;

	private Encounter(Map<EncounterType, Float> dialogues) {
		this.dialogues = dialogues;
	}

	protected abstract DialogueNodeOld initialiseEncounter(EncounterType node);

	/**
	 * Returns a random encounter from the list, or null if no encounter was
	 * selected.
	 * 
	 * @param encounters
	 * @return null if no encounter.
	 */
	public DialogueNodeOld getRandomEncounter() {
		return getBaseRandomEncounter();
	}
	
	public Map<EncounterType, Float> getDialogues() {
		return dialogues;
	}

	protected DialogueNodeOld getBaseRandomEncounter() {
		float r = (float) (Math.random() * 100), total = 0;
		
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
