package com.base.game.dialogue.encounters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.base.game.Weather;
import com.base.game.character.QuestLine;
import com.base.game.character.gender.GenderPreference;
import com.base.game.character.npc.NPC;
import com.base.game.character.npc.dominion.NPCRandomDominion;
import com.base.game.character.npc.dominion.NPCRandomHarpy;
import com.base.game.character.npc.dominion.NPCRandomSuccubus;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.AbstractClothingType;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.inventory.item.AbstractItemType;
import com.base.game.inventory.item.ItemType;
import com.base.game.inventory.weapon.AbstractWeapon;
import com.base.game.inventory.weapon.AbstractWeaponType;
import com.base.game.inventory.weapon.WeaponType;
import com.base.main.Main;
import com.base.utils.Util;
import com.base.utils.Util.Value;
import com.base.utils.Vector2i;

/**
 * @since 0.1.0
 * @version 0.1.85
 * @author Innoxia
 */
public enum Encounter {

	DOMINION_STREET(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.DOMINION_STORM_ATTACK, 15f))) {
		@Override
		public DialogueNodeOld getRandomEncounter() {
			if (Main.game.getCurrentWeather() == Weather.MAGIC_STORM)
				return getBaseRandomEncounter();
			else
				return null;
		}

		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			
			Main.game.setActiveNPC(new NPCRandomDominion(GenderPreference.getGenderFromUserPreferences()));

			try {
				Main.game.addNPC(Main.game.getActiveNPC());
			} catch (Exception e) {
				e.printStackTrace();
			}

			return Main.game.getActiveNPC().getEncounterDialogue();
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
				
				if(Main.game.isIncestEnabled() && Math.random()>0.75f) { // Incest
					List<NPC> offspringAvailable = new ArrayList<>();
					for(NPC npc : Main.game.getOffspring()) {
						offspringAvailable.add(npc);
					}
					for(NPC npc : Main.game.getOffspringSpawned()) {
						offspringAvailable.remove(npc);
					}
					
					if(!offspringAvailable.isEmpty()) {
						NPC offspring = offspringAvailable.get(Util.random.nextInt(offspringAvailable.size()));
						Main.game.getOffspringSpawned().add(offspring);
						
						offspring.setWorldLocation(Main.game.getPlayer().getWorldLocation());
						offspring.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));
						
						Main.game.setActiveNPC(offspring);
						try {
							Main.game.addNPC(offspring);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return Main.game.getActiveNPC().getEncounterDialogue();
					}
					
				}
				
				Main.game.setActiveNPC(new NPCRandomDominion(GenderPreference.getGenderFromUserPreferences()));
				try {
					Main.game.addNPC(Main.game.getActiveNPC());
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
			
			Main.game.setActiveNPC(new NPCRandomSuccubus());

			try {
				Main.game.addNPC(Main.game.getActiveNPC());
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

				Main.game.setActiveNPC(new NPCRandomHarpy(GenderPreference.getGenderFromUserPreferences()));
				
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getLocation());
				
				try {
					Main.game.addNPC(Main.game.getActiveNPC());
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

				Main.game.setActiveNPC(new NPCRandomHarpy(GenderPreference.getGenderFromUserPreferences()));
				
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getLocation());
				
				try {
					Main.game.addNPC(Main.game.getActiveNPC());
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

				Main.game.setActiveNPC(new NPCRandomHarpy(GenderPreference.getGenderFromUserPreferences()));
				
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getLocation());
				
				try {
					Main.game.addNPC(Main.game.getActiveNPC());
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

				Main.game.setActiveNPC(new NPCRandomHarpy(GenderPreference.getGenderFromUserPreferences()));
				
				Main.game.getActiveNPC().setLocation(Main.game.getPlayer().getLocation());
				
				try {
					Main.game.addNPC(Main.game.getActiveNPC());
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
		
//		System.out.println(r);

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
