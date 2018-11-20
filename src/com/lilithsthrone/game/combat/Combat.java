package com.lilithsthrone.game.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * Singleton enforced by Enum.<br/>
 * Call initialiseCombat() before using.
 *
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public enum Combat {
	COMBAT;

	// TODO Make sure your status effects end before you take your turn, enemy's status effects end at the start of their turn
	// Also, end combat it enemy drops to 0 health/mana/stamina on their turn from combat effects

	private static NPC activeNPC;
	private static GameCharacter targetedCombatant;
	private static List<NPC> allies;
	private static List<NPC> enemies;
	private static List<NPC> allCombatants;
	private static Map<GameCharacter, Map<SpecialAttack, Integer>> cooldowns;
	private static float escapeChance = 0;
	private static int turn = 0;
	private static boolean escaped = false;
	private static StringBuilder combatStringBuilder = new StringBuilder();
	private static StringBuilder attackStringBuilder = new StringBuilder();
	private static StringBuilder postCombatStringBuilder = new StringBuilder();
	private static String combatContent;

	private static AbstractItem item;

	// For use in repeat last action:
	private static Attack previousAction;
	private static Spell previouslyUsedSpell;
	private static SpecialAttack previouslyUsedSpecialAttack;

	private Combat() {
	}

	/**
	 * @param allies A list of allies who are fighting with you. <b>Do not include Main.game.getPlayer() in this!</b>
	 * @param enemies A list of enemies you're fighting. The first enemy in the list is considered the leader.
	 * @param escapePercentage The base chance of escaping in this combat situation. TODO
	 * @param openingDescriptions A map of opening descriptions for characters. If a description is not provided, one is generated automatically.
	 */
	public void initialiseCombat(
			List<NPC> allies,
			List<NPC> enemies,
			Map<GameCharacter, String> openingDescriptions) {

		cooldowns = new HashMap<>();
		cooldowns.put(Main.game.getPlayer(), new HashMap<>());
		
		allCombatants = new ArrayList<>();
		Combat.allies = new ArrayList<>();
		Combat.enemies = new ArrayList<>();
		
		if(allies!=null){
			for(NPC ally : allies) {
				Combat.addAlly(ally);
			}
		}
		for(NPC enemy : enemies) {
			Combat.addEnemy(enemy);
		}
		enemies.sort((enemy1, enemy2) -> enemy2.getLevel()-enemy1.getLevel());
		
		targetedCombatant = enemies.get(0);
		activeNPC = enemies.get(0);

		escaped = false;
		
		combatContent = "";
		turn = 0;
		combatStringBuilder.setLength(0);
		postCombatStringBuilder.setLength(0);
		
		previousAction = Attack.NONE;

		escapeChance = ((NPC) targetedCombatant).getEscapeChance();
		if (Main.game.getPlayer().hasTrait(Perk.RUNNER, true)) {
			escapeChance *= 1.5f;
		} else if (Main.game.getPlayer().hasTrait(Perk.RUNNER_2, true)) {
			escapeChance *= 2f;
		}
		if(escapeChance >0 && Main.game.getPlayer().hasTrait(Perk.JOB_ATHLETE, true)) {
			escapeChance = 100;
		}
		if(escapeChance >0 && Main.game.getPlayer().getSubspecies()==Subspecies.CAT_MORPH_CHEETAH) {
			boolean cheetahEnemy = false;
			for(NPC enemy : Combat.getEnemies()) {
				if(enemy.getSubspecies()==Subspecies.CAT_MORPH_CHEETAH) {
					cheetahEnemy = true;
				}
			}
			if(!cheetahEnemy) {
				escapeChance = 100;
			}
		}
		
		String startingEffect = "";
		
		if(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)) {
			Main.game.getPlayer().addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 11);
			startingEffect = Spell.getBasicStatusEffectApplication(Main.game.getPlayer(), true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 10)));
			
		} else if(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2)) {
			Main.game.getPlayer().addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 11);
			startingEffect = Spell.getBasicStatusEffectApplication(Main.game.getPlayer(), true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 10)));
			
		} else if(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1)) {
			Main.game.getPlayer().addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION, 11);
			startingEffect = Spell.getBasicStatusEffectApplication(Main.game.getPlayer(), true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION, 10)));
		}
		
		if(openingDescriptions!=null && openingDescriptions.containsKey(Main.game.getPlayer())) {
			combatStringBuilder.append(getCharactersTurnDiv(Main.game.getPlayer(),
					"Preparation",
					(Main.game.getPlayer().hasTrait(Perk.JOB_SOLDIER, true)
							?"<p style='text-align:center;'>"
								+"Any damage done in this first turn is [style.boldExcellent(doubled)] thanks to your"
									+ " <b style='color:"+Perk.JOB_SOLDIER.getColour().toWebHexString()+";'>"+Perk.JOB_SOLDIER.getName(Main.game.getPlayer())+"</b> ability."
								+ "</p>"
							:"")
					+openingDescriptions.get(Main.game.getPlayer())
					+startingEffect));
		} else {
			combatStringBuilder.append(getCharactersTurnDiv(Main.game.getPlayer(),
					"Preparation",
					(Main.game.getPlayer().hasTrait(Perk.JOB_SOLDIER, true)
							?"<p style='text-align:center;'>"
								+"Any damage done in this first turn is [style.boldExcellent(doubled)] thanks to your"
									+ " <b style='color:"+Perk.JOB_SOLDIER.getColour().toWebHexString()+";'>"+Perk.JOB_SOLDIER.getName(Main.game.getPlayer())+"</b> ability."
								+ "</p>"
							:"")
					+"<p>You prepare to make a move...</p>"
					+startingEffect));
		}
		
		for(NPC enemy : enemies) {
			startingEffect="";
			if(enemy.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)) {
				enemy.addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 11);
				startingEffect = Spell.getBasicStatusEffectApplication(enemy, true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 10)));
				
			} else if(enemy.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2)) {
				enemy.addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 11);
				startingEffect = Spell.getBasicStatusEffectApplication(enemy, true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 10)));
				
			} else if(enemy.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1)) {
				enemy.addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION, 11);
				startingEffect = Spell.getBasicStatusEffectApplication(enemy, true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION, 10)));
			}
			
			if(openingDescriptions!=null && openingDescriptions.containsKey(enemy)) {
				combatStringBuilder.append(getCharactersTurnDiv(enemy, "", openingDescriptions.get(enemy) + startingEffect));
			} else {
				combatStringBuilder.append(getCharactersTurnDiv(enemy, "Preparation", UtilText.parse(enemy, "[npc.Name] prepares to make a move..." + startingEffect)));
			}
		}

		combatContent = combatStringBuilder.toString();
		combatStringBuilder.setLength(0);
		
		Main.game.setInCombat(true);
		
		Main.mainController.openInventory();
		
	}

	public static void appendTurnText(GameCharacter character, String title, String description) {
		combatStringBuilder.append(getCharactersTurnDiv(character, title, description));
	}
	
	private static String getCharactersTurnDiv(GameCharacter character, String title, String description) {
		String effects = Combat.applyEffects(character);
		
		if(enemies.contains(character)) {
			return UtilText.parse(character,
				"<div class='container-full-width' style='text-align:center; box-sizing: border-box; border:6px solid "+Colour.GENERIC_MINOR_BAD.getShades()[0]+"; border-radius:5px;'>"
					+ "<div class='container-half-width' style='width: calc(80% - 16px);'>"+description+effects+"</div>"
					+ "<div class='container-quarter-width'style='width: calc(20% - 16px);'><b style='color:"+character.getFemininity().getColour().toWebHexString()+";'>[npc.Name()]</b><br/>"+title+"</div>"
				+ "</div>");
			
		} else {
			return UtilText.parse(character,
				"<div class='container-full-width' style='text-align:center; box-sizing: border-box; border:6px solid "+Colour.GENERIC_MINOR_GOOD.getShades()[0]+"; border-radius:5px;'>"
					+ "<div class='container-quarter-width'style='width: calc(20% - 16px);'><b style='color:"+character.getFemininity().getColour().toWebHexString()+";'>[npc.Name()]</b><br/>"+title+"</div>"
					+ "<div class='container-half-width' style='width: calc(80% - 16px);'>"+description+effects+"</div>"
				+ "</div>");
		}
	}
	
	/**
	 * Ends combat, removing status effects and handling post-combat experience
	 * gains and loot drops.
	 * 
	 * @param playerVictory
	 */
	private static void endCombat(boolean playerVictory) {
		
		postCombatStringBuilder.setLength(0);
		
		if (playerVictory) {
			// Give the player experience and money if they won:
			int xp = 0;
			int money = 0;
			for(NPC enemy : enemies) {
				xp+=enemy.getExperienceFromVictory();
				money+=enemy.getLootMoney();
				enemy.setLostCombatCount(enemy.getLostCombatCount()+1);
			}
			
			for(NPC ally : allies) {
				if(!(ally instanceof Elemental)) {
					postCombatStringBuilder.append(ally.incrementExperience(xp, true));
				}
			}
			
			postCombatStringBuilder.append(Main.game.getPlayer().incrementExperience(xp, true));
			
			if (money > 0) {
				postCombatStringBuilder.append(Main.game.getPlayer().incrementMoney(money));
			}
			
			// Apply loot drop:
			Map<AbstractCoreItem, Integer> lootedItemsMap = new HashMap<>();
			
			for(NPC enemy : enemies) {
				if(enemy.getLootItems()!=null) {
					for(AbstractCoreItem item : enemy.getLootItems()) {
						lootedItemsMap.putIfAbsent(item, 0);
						lootedItemsMap.put(item, lootedItemsMap.get(item)+1);
						if(item instanceof AbstractItem) {
							Main.game.getPlayer().addItem((AbstractItem) item, false, true);
						} else if(item instanceof AbstractWeapon) {
							Main.game.getPlayer().addWeapon((AbstractWeapon) item, false);
						} else if(item instanceof AbstractClothing) {
							Main.game.getPlayer().addClothing((AbstractClothing) item, false);
						}
					}
				}
				if(enemy.isElementalSummoned()) {
					enemy.removeCompanion(enemy.getElemental());
					enemy.getElemental().returnToHome();
					postCombatStringBuilder.append(UtilText.parse(enemy, enemy.getElemental(),
							"<div class='container-full-width' style='text-align:center;'>"
									+ "[npc1.NamePos] elemental, [npc2.name], is [style.boldTerrible(dispelled)]!"
							+ "</div>"));
				}
			}

			List<String> itemsLooted = new ArrayList<>();
			for(Entry<AbstractCoreItem, Integer> entry : lootedItemsMap.entrySet()) {
				itemsLooted.add("<b style='color:"+entry.getKey().getRarity().getColour().toWebHexString()+";'>"+entry.getKey().getName()+"</b>"+(entry.getValue()>1?" <b>(x"+entry.getValue()+")</b>":""));
			}
			postCombatStringBuilder.append("<div class='container-full-width' style='text-align:center;'>You [style.boldGood(gained)] " + Util.stringsToStringList(itemsLooted, false) +"!</div>");

			// Apply essence drops:
			boolean essenceDropFound = false;
			Map<TFEssence, Integer> essences = new HashMap<>();
			for(NPC enemy : enemies) {
				if(enemy.getLootEssenceDrops()!=null) {
					if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essencePostCombatDiscovered)) {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.essencePostCombatDiscovered);
						
						if(!essenceDropFound) {
							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
								postCombatStringBuilder.append(
										UtilText.parse(enemy,
										"<p>"
											+ "[npc.Name] staggers back, defeated, but before you have a chance to react to your victory, the world around you seems to somehow shift out of focus."
											+ " The pants and gasps coming from [npc.her] mouth start to sound muffled and faint; as though you're listening to [npc.her] while submerged under water."
											+ " After fruitlessly trying to shake your head clear, you look down at [npc.name] to see if [npc.sheIs] being affected by this peculiar phenomenon as well, but as you do, you feel your eyes going wide in shock."
										+ "</p>"
										+ "<p>"
											+ "A shimmering pink glow has materialised around [npc.her] body, just like the one you saw in Lilaya's lab when she ran her tests on you."
											+ " Quickly realising that you're somehow able to see [npc.namePos] arcane aura, you watch, fascinated, as you see a sizable shard slowly start to break away from [npc.herHim]."
											+ " The moment it finally splits from the rest of [npc.her] aura, the shard of energy suddenly launches itself directly at you."
										+ "</p>"
										+ "<p>"
											+ "Slowed down and slightly dazed by the strange state you find yourself in, you have no chance to dodge, and as the shard makes contact with your chest, it pierces straight into your body."
											+ " The world around you instantly snaps back into focus as it enters your body, and you find yourself sharply inhaling as you feel the energy merging with your own arcane aura."
										+ "</p>"
										+ "<p>"
											+ "Looking back down at [npc.name], you see no sign of the shimmering pink field that was surrounding [npc.herHim] a moment ago, and, what's more, [npc.she] seems completely oblivious to what you've just witnessed."
											+ " You think that it would probably be best to go and ask Lilaya about what just happened, but first you'd better deal with this troublesome [npc.race]..."
										+ "</p>"
										+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"")));
								
							} else {
								postCombatStringBuilder.append(
										UtilText.parse(enemy,
										"<p>"
											+ "[npc.Name] staggers back, defeated, but before you have a chance to react to your victory, the world around you seems to somehow shift out of focus."
											+ " The pants and gasps coming from [npc.her] mouth start to sound muffled and faint; as though you're listening to [npc.her] while submerged under water."
											+ " After fruitlessly trying to shake your head clear, you look down at [npc.name] to see if [npc.sheIs] being affected by this peculiar phenomenon as well, but as you do, you feel your eyes going wide in shock."
										+ "</p>"
										+ "<p>"
											+ "A shimmering pink glow has materialised around [npc.her] body, just like the one you saw in Lilaya's lab when she ran her tests on you."
											+ " Quickly realising that you're somehow able to see [npc.namePos] arcane aura, you watch, fascinated, as you see a sizable shard slowly start to break away from [npc.herHim]."
											+ " The moment it finally splits from the rest of [npc.her] aura, the shard of energy suddenly launches itself directly at you."
										+ "</p>"
										+ "<p>"
											+ "Slowed down and slightly dazed by the strange state you find yourself in, you have no chance to dodge, and as the shard makes contact with your chest, it pierces straight into your body."
											+ " The world around you instantly snaps back into focus as it enters your body, and you find yourself sharply inhaling as you feel the energy merging with your own arcane aura."
										+ "</p>"
										+ "<p>"
											+ "Looking back down at [npc.name], you see no sign of the shimmering pink field that was surrounding [npc.herHim] a moment ago, and, what's more, [npc.she] seems completely oblivious to what you've just witnessed."
											+ " You suddenly remember what Lilaya told you about absorbing essences, and how it's absolutely harmless for both parties involved."
											+ " Breathing a sigh of relief, you turn your attention back to this troublesome [npc.race]..."
										+ "</p>"));
							}
						}
					}
					
					for(Entry<TFEssence, Integer> entry : enemy.getLootEssenceDrops().entrySet()) {
						essences.putIfAbsent(entry.getKey(), 0);
						essences.put(entry.getKey(), essences.get(entry.getKey())+entry.getValue());
					}
				}
			}
			
			if(!essences.isEmpty()) {
				for(Entry<TFEssence, Integer> entry : essences.entrySet()) {
					postCombatStringBuilder.append("<div class='container-full-width' style='text-align:center;'>"+Main.game.getPlayer().incrementEssenceCount(entry.getKey(), entry.getValue(), true)+"</div>"
							+ "</br>");
				}
			}
			
		} else { // Player lost combat:
			int xpGain = (Main.game.getPlayer().getLevel()*2);
			
			for(NPC enemy : enemies) {
				if(!(enemy instanceof Elemental)) {
					postCombatStringBuilder.append(enemy.incrementExperience(xpGain, true));
				}
			}
			
			int money = Main.game.getPlayer().getMoney();
			int moneyLoss = (-enemies.get(0).getLootMoney()/2)*enemies.size();
			Main.game.getPlayer().incrementMoney(moneyLoss);
			postCombatStringBuilder.append("<div class='container-full-width' style='text-align:center;'>You [style.boldBad(lost)] " + UtilText.formatAsMoney(Math.abs(Main.game.getPlayer().getMoney()==0?money:moneyLoss)) + "!</div>");
			
			// Remove elementals:
			if(Main.game.getPlayer().isElementalSummoned()) {
				Main.game.getPlayer().removeCompanion(Main.game.getPlayer().getElemental());
				Main.game.getPlayer().getElemental().returnToHome();
				postCombatStringBuilder.append(UtilText.parse(Main.game.getPlayer().getElemental(), "<div class='container-full-width' style='text-align:center;'>Your elemental, [npc.name], is [style.boldTerrible(dispelled)]!</div>"));
			}
			for(NPC ally : allies) {
				if(ally.isElementalSummoned()) {
					ally.removeCompanion(ally.getElemental());
					ally.getElemental().returnToHome();
					postCombatStringBuilder.append(UtilText.parse(ally, ally.getElemental(), "<div class='container-full-width' style='text-align:center;'>[npc1.NamePos] elemental, [npc2.name], is [style.boldTerrible(dispelled)]!</div>"));
				}
			}
			
			for(NPC enemy : enemies) {
				enemy.setWonCombatCount(enemy.getWonCombatCount()+1);
			}
		}

		Main.game.setInCombat(false);

		// Sort out effects after combat:
		if (Main.game.getPlayer().getHealth() == 0)
			Main.game.getPlayer().setHealth(5);
		if (Main.game.getPlayer().getMana() == 0)
			Main.game.getPlayer().setMana(5);
		
		// Reset opponent resources to starting values:
		for(NPC enemy : enemies) {
			enemy.setMana(enemy.getAttributeValue(Attribute.MANA_MAXIMUM));
			enemy.setHealth(enemy.getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
		for(NPC ally : allies) {
			ally.setMana(ally.getAttributeValue(Attribute.MANA_MAXIMUM));
			ally.setHealth(ally.getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}

		Main.game.getTextStartStringBuilder().append(postCombatStringBuilder.toString());
	}

	private static String npcStatus() {
		return "";
	}

	// DIALOGUES:
	public DialogueNodeOld startCombat() {
		return ENEMY_ATTACK;
	}
	
	private static boolean isCombatantDefeated(GameCharacter character) {
		return (character.getHealth() <= 0 || (character.getLust()>=100 && character.isVulnerableToLustLoss()));
	}
	
	public static boolean isOpponent(GameCharacter character, GameCharacter target) {
		if(allies.contains(character)) {
			return enemies.contains(target);
		} else {
			return allies.contains(character);
		}
	}
	
	private static boolean isAlliedPartyDefeated() {
		for(NPC ally : allies) {
			if(!isCombatantDefeated(ally)) {
				return false;
			}
		}
		return isCombatantDefeated(Main.game.getPlayer());
	}
	
	private static boolean isEnemyPartyDefeated() {
		for(NPC enemy : enemies) {
			if(!isCombatantDefeated(enemy)) {
				return false;
			}
		}
		return true;
	}

	public static final DialogueNodeOld ITEM_USED = new DialogueNodeOld("Combat", "Use the item.", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return getCombatLabel();
		}

		@Override
		public String getHeaderContent() {
			return npcStatus();
		}

		@Override
		public String getContent() {
			return combatContent;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (isEnemyPartyDefeated()) {
					return new ResponseEffectsOnly("Victory", "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>You are victorious!</span>"){
						@Override
						public void effects() {
							endCombat(true);
							Main.game.setContent(enemies.get(0).endCombat(true, true));
						}
					};
				} else {
					return new Response("Continue", "Combat continues.", ENEMY_ATTACK){
						@Override
						public void effects() {
							attackNPC(enemies.get(0));
						}
					};
				}
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.NORMAL;
		}
	};

	public static final DialogueNodeOld SUBMIT = new DialogueNodeOld("Combat", "Submit", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return getCombatLabel();
		}

		@Override
		public String getHeaderContent() {
			return npcStatus();
		}

		@Override
		public String getContent() {
			return UtilText.parse(enemies.get(0),
							"<p>"
									+ "Are you certain you want to <b>submit</b> to [npc.name]? <b>This will cause you to lose the fight, allowing [npc.herHim] to do anything [npc.she] wants with you!</b>"
							+ "</p>");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Submit",
						UtilText.parse(enemies.get(0),
								"Submit to [npc.name]. <span style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>This will cause you to lose the current combat!</span>"),
						SUBMIT_CONFIRM){
					@Override
					public void effects() {
						submit(Main.game.getPlayer());
					}
				};
				
			} else if (index == 0) {
				return new Response("Cancel", "Carry on fighting.", ENEMY_ATTACK);
				
			}else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.NORMAL;
		}
	};
	public static final DialogueNodeOld SUBMIT_CONFIRM = new DialogueNodeOld("Combat", "Submit", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return getCombatLabel();
		}

		@Override
		public String getHeaderContent() {
			return npcStatus();
		}

		@Override
		public String getContent() {
			return combatContent;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Continue", UtilText.parse(enemies.get(0), "You wait for [npc.name] to make a move.")){
					@Override
					public void effects() {
						endCombat(false);
						Main.game.setContent(enemies.get(0).endCombat(true, false));
					}
				};
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.NORMAL;
		}
	};

	public static final DialogueNodeOld ENEMY_ATTACK = new DialogueNodeOld("Combat", "The enemy strikes back at you.", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return getCombatLabel();
		}

		@Override
		public String getHeaderContent() {
			return npcStatus();
		}

		@Override
		public String getContent() {
			return combatContent;
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(isEnemyPartyDefeated() || isAlliedPartyDefeated() || Main.game.getPlayer().isStunned() || escaped || isCombatantDefeated(Main.game.getPlayer())) {
				return null;
				
			} else {
				if(index==0) {
					return "Attacks";
				} else if(index==1) {
					return "Specials";
				} else if(index==2) {
					return "Spells";
				}
				return null;
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			
			if(Main.game.getPlayer().isStunned()) {
				if (index == 1) {
					return new Response("Stunned!", "You are unable to make an action this turn!", ENEMY_ATTACK){
						@Override
						public void effects() {
							combatStringBuilder.append(getCharactersTurnDiv(Main.game.getPlayer(), "Stunned", "You are unable to make a move!"));
							endCombatTurn();
						}
					};
					
				} else {
					return null;
				}
				
			} else if (escaped) {
				if (index == 1) {
					return new ResponseEffectsOnly("Escaped!", "You got away!"){
						@Override
						public void effects() {
							enemies.get(0).applyEscapeCombatEffects();
							Main.game.setInCombat(false);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogueNoEncounter()));
						}
					};
				} else {
					return null;
				}
				
			} else if (isEnemyPartyDefeated()) {
				if (index == 1) {
					return new ResponseEffectsOnly("Victory", UtilText.parse(enemies.get(0), "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>You have defeated [npc.name]!</span>")){
						@Override
						public void effects() {
							endCombat(true);
							Main.game.setContent(enemies.get(0).endCombat(true, true));
						}
					};
				} else
					return null;
				
			}  else if (isAlliedPartyDefeated()) {
				if (index == 1) {
					return new ResponseEffectsOnly("Defeat", "You have been defeated!"){
						@Override
						public void effects() {
							endCombat(false);
							Main.game.setContent(enemies.get(0).endCombat(true, false));
						}
					};
				} else
					return null;
				
			} else if(isCombatantDefeated(Main.game.getPlayer())) {
				if (index == 1) {
					return new Response("Watch", "You have been defeated, and can only watch as your allies fight on!", ENEMY_ATTACK){
						@Override
						public void effects() {
							combatStringBuilder.append(getCharactersTurnDiv(Main.game.getPlayer(),
									"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Defeated!</span>",
									"You have been defeated, and can only watch as your allies continue the fight!"));
							endCombatTurn();
						}
					};
					
				} else {
					return null;
				}
				
			}
			
			
			if(responseTab==1) { // Special attacks:
				if (Main.game.getPlayer().getSpecialAttacks().size() >= index && index!=0) {
					int cooldown = Combat.getCooldown(Main.game.getPlayer(), Main.game.getPlayer().getSpecialAttacks().get(index - 1));
					if(cooldown==0) {
						return new Response(Util.capitaliseSentence(Main.game.getPlayer().getSpecialAttacks().get(index - 1).getName()),
								getSpecialAttackDescription(Main.game.getPlayer().getSpecialAttacks().get(index - 1)),
								ENEMY_ATTACK){
							@Override
							public void effects() {
								attackSpecialAttack(Main.game.getPlayer(), Main.game.getPlayer().getSpecialAttacks().get(index - 1));
								endCombatTurn();
								previousAction = Attack.SPECIAL_ATTACK;
								previouslyUsedSpecialAttack = Main.game.getPlayer().getSpecialAttacks().get(index - 1);
							}
						};
					} else {
						return new Response(Util.capitaliseSentence(Main.game.getPlayer().getSpecialAttacks().get(index - 1).getName()),
								"This special move is on cooldown for [style.colourBad("+cooldown+")] more turns!",
								null);
					}
					
				} else {
					return null;
				}
			}
			
			if(responseTab==2) { // Spells
				int spellIndex = index==0?14:(index<15?index-1:index);
				
				if(Main.game.getPlayer().getAllSpells().size()>spellIndex) {
					if(Main.game.getPlayer().getMana() < Main.game.getPlayer().getAllSpells().get(spellIndex).getModifiedCost(Main.game.getPlayer())) {
						return new Response(Util.capitaliseSentence(Main.game.getPlayer().getAllSpells().get(spellIndex).getName()),
								"You don't have enough aura to cast this spell! (Requires "+Main.game.getPlayer().getAllSpells().get(spellIndex).getModifiedCost(Main.game.getPlayer())+" aura.)",
								null);
					}
					if(Main.game.getPlayer().getAllSpells().get(spellIndex).getType()==SpellType.OFFENSIVE
							&& (Combat.getAllies().contains(Combat.getTargetedCombatant(Main.game.getPlayer()))
									|| Combat.getTargetedCombatant(Main.game.getPlayer()).isPlayer())) {
						return new Response(Util.capitaliseSentence(Main.game.getPlayer().getAllSpells().get(spellIndex).getName()),
								"You can only cast this spell on an enemy!",
								null);
					}
					return new Response(Util.capitaliseSentence(Main.game.getPlayer().getAllSpells().get(spellIndex).getName()),
							getSpellDescription(Main.game.getPlayer().getAllSpells().get(spellIndex),
							null),
							ENEMY_ATTACK){
						@Override
						public void effects() {
							attackSpell(Main.game.getPlayer(), Main.game.getPlayer().getAllSpells().get(spellIndex));
							endCombatTurn();
							previousAction = Attack.SPELL;
							previouslyUsedSpell = Main.game.getPlayer().getAllSpells().get(spellIndex);
						}
						@Override
						public Colour getHighlightColour() {
							return Main.game.getPlayer().getAllSpells().get(spellIndex).getSpellSchool().getColour();
						}
					};
					
				} else {
					return null;
				}
			}
			
			
			if (index == 0) {
				if (escapeChance == 0) {
					return new Response("Escape", "You can't run from this fight!", null);
					
				} else if(!Main.game.getPlayer().isAbleToEscape()) {
					return new Response("Escape", Main.game.getPlayer().getUnableToEscapeDescription(), null);
					
				} else {
					return new Response("Escape",
							"Try to escape.<br/><br/>"
							+ (escapeChance==100 && Main.game.getPlayer().hasTrait(Perk.JOB_ATHLETE, true)
								?"Thanks to your <b style='color:"+Perk.JOB_ATHLETE.getColour().toWebHexString()+";'>"
											+Perk.JOB_ATHLETE.getName(Main.game.getPlayer())+"</b> ability, you are easily able to sprint away from this fight! "
								:(escapeChance==100 && Main.game.getPlayer().getSubspecies()==Subspecies.CAT_MORPH_CHEETAH
										?"Thanks to your cheetah-morph's body, you are easily able to sprint away from this fight! "
										:""))
							+"You have a "+escapeChance+"% chance to get away!",
							ENEMY_ATTACK){
						@Override
						public void effects() {
							escape(Main.game.getPlayer());
							endCombatTurn();
						}
					};
				}
				
			} else if (index == 1) {
				if(Main.game.getPlayer().getMainWeapon() == null || Main.game.getPlayer().getMainWeapon().isAbleToBeUsed(Main.game.getPlayer(), Combat.getTargetedCombatant(Main.game.getPlayer()))) {
					return new Response((
							Main.game.getPlayer().getMainWeapon() != null
								? Util.capitaliseSentence(Main.game.getPlayer().getMainWeapon().getWeaponType().getAttackDescriptor())
								: "Melee Strike"),
							getMainAttackDescription(),
							ENEMY_ATTACK){
						@Override
						public void effects() {
							attackMain(Main.game.getPlayer());
							endCombatTurn();
						}
					};
				} else {
					return new Response(
							(Main.game.getPlayer().getMainWeapon() != null
								?Util.capitaliseSentence(Main.game.getPlayer().getMainWeapon().getWeaponType().getAttackDescriptor())
								:"Melee Strike"),
							Main.game.getPlayer().getMainWeapon().getUnableToBeUsedDescription(), null);
				}

			} else if (index == 2) {
				if(Main.game.getPlayer().getOffhandWeapon() == null || Main.game.getPlayer().getOffhandWeapon().isAbleToBeUsed(Main.game.getPlayer(), Combat.getTargetedCombatant(Main.game.getPlayer()))) {
					return new Response(
							(Main.game.getPlayer().getOffhandWeapon() != null
								? Util.capitaliseSentence(Main.game.getPlayer().getOffhandWeapon().getWeaponType().getAttackDescriptor())
								: "Melee Strike"),
							getOffhandAttackDescription(), ENEMY_ATTACK){
						@Override
						public void effects() {
							attackOffhand(Main.game.getPlayer());
							endCombatTurn();
						}
					};
				} else {
					return new Response(
							(Main.game.getPlayer().getOffhandWeapon() != null
								? Util.capitaliseSentence(Main.game.getPlayer().getOffhandWeapon().getWeaponType().getAttackDescriptor())
								: "Melee Strike"),
							Main.game.getPlayer().getOffhandWeapon().getUnableToBeUsedDescription(), null);
				}

			} else if (index == 3) {
				if((Main.game.getPlayer().getMainWeapon() == null || Main.game.getPlayer().getMainWeapon().isAbleToBeUsed(Main.game.getPlayer(), Combat.getTargetedCombatant(Main.game.getPlayer())))
						&& (Main.game.getPlayer().getOffhandWeapon() == null || Main.game.getPlayer().getOffhandWeapon().isAbleToBeUsed(Main.game.getPlayer(), Combat.getTargetedCombatant(Main.game.getPlayer())))) {
					return new Response("Dual Strike", getDualAttackDescription(), ENEMY_ATTACK){
						@Override
						public void effects() {
							attackDual(Main.game.getPlayer());
							endCombatTurn();
						}
					};
				} else {
					if((Main.game.getPlayer().getMainWeapon() != null && !Main.game.getPlayer().getMainWeapon().isAbleToBeUsed(Main.game.getPlayer(), Combat.getTargetedCombatant(Main.game.getPlayer())))) {
						return new Response("Dual Strike", Main.game.getPlayer().getMainWeapon().getUnableToBeUsedDescription(), null);
					} else {
						return new Response("Dual Strike", Main.game.getPlayer().getOffhandWeapon().getUnableToBeUsedDescription(), null);
					}
				}

			} else if (index == 4) {
				return new Response("Seduce", getTeaseDescription(), ENEMY_ATTACK){
					@Override
					public void effects() {
						attackSeduction(Main.game.getPlayer());
						endCombatTurn();
					}
				};

			}  else if (index == 5) {
				return new Response("Wait", "Don't perform an action.", ENEMY_ATTACK){
					@Override
					public void effects() {
						attackWait(Main.game.getPlayer());
						endCombatTurn();
					}
				};

			}  else if (index == 9) {
				return new Response("Submit", "Consider submitting to " + enemies.get(0).getName("the") + ".", SUBMIT);

			} else if (index == 10) {
				switch (previousAction) {
					case SPELL:
						if(Main.game.getPlayer().getMana() < previouslyUsedSpell.getModifiedCost(Main.game.getPlayer())) {
							return new Response(Util.capitaliseSentence(previouslyUsedSpell.getName()),
									"You don't have enough aura to cast this spell!",
									null);
						}
						return new Response(Util.capitaliseSentence(previouslyUsedSpell.getName()), getSpellDescription(previouslyUsedSpell, null), ENEMY_ATTACK){
							@Override
							public void effects() {
								attackSpell(Main.game.getPlayer(), previouslyUsedSpell);
								endCombatTurn();
							}
						};

					case SPECIAL_ATTACK:
						int cooldown = Combat.getCooldown(Main.game.getPlayer(), previouslyUsedSpecialAttack);
						if(cooldown==0) {
							return new Response(Util.capitaliseSentence(previouslyUsedSpecialAttack.getName()), getSpecialAttackDescription(previouslyUsedSpecialAttack), ENEMY_ATTACK){
								@Override
								public void effects() {
									attackSpecialAttack(Main.game.getPlayer(), previouslyUsedSpecialAttack);
									endCombatTurn();
								}
							};
						} else {
							return new Response(Util.capitaliseSentence(previouslyUsedSpecialAttack.getName()),
									"This special move is on cooldown for [style.colourBad("+cooldown+")] more turns!",
									null);
						}
//						case DUAL: TODO
//							break;
//						case ESCAPE:
//							break;
//						case MAIN:
//							break;
//						case NONE:
//							break;
//						case OFFHAND:
//							break;
//						case SEDUCTION:
//							break;
//						case USE_ITEM:
//							break;
//						case WAIT:
//							break;
					default:
						return new Response("Repeat", "You have to perform an action first!", null);
				}

			} else if(index==11) {
				if(targetedCombatant.equals(Main.game.getPlayer())) {
					return new Response("Self", "You are already targeting yourself!", null);
				} else {
					return new Response("Self", "Switch your target to yourself!", ENEMY_ATTACK){
						@Override
						public void effects() {
							targetedCombatant = Main.game.getPlayer();
						}
						@Override
						public Colour getHighlightColour() {
							return Colour.GENERIC_MINOR_GOOD;
						}
					};
				}
				
			} else if(index>11 && index - 11 <= allCombatants.size()) {
				if(targetedCombatant.equals(allCombatants.get(index-12))) {
					return new Response(Util.capitaliseSentence(allCombatants.get(index-12).getName()), "You are already targeting "+allCombatants.get(index-12).getName()+"!", null);
				} else {
					return new Response(Util.capitaliseSentence(allCombatants.get(index-12).getName()),
							"Switch your target to "+allCombatants.get(index-12).getName()+" (You can also do this by clicking on their name in the side bar.).",
							ENEMY_ATTACK){
						@Override
						public void effects() {
							targetedCombatant = allCombatants.get(index-12);
						}
						@Override
						public Colour getHighlightColour() {
							return getAllies().contains(allCombatants.get(index-12))?Colour.GENERIC_GOOD:Colour.GENERIC_COMBAT;
						}
					};
				}
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.NORMAL;
		}
	};

	
	// Calculations for melee attack:
	private static void attackMain(GameCharacter attacker) {
		GameCharacter target = getTargetedCombatant(attacker);
		boolean critical = Attack.rollForCritical(attacker, target);
		float damage = Attack.calculateDamage(attacker, target, Attack.MAIN, critical);
		boolean isHit = Attack.rollForHit(attacker, target);
		
		attackStringBuilder = new StringBuilder("");

		attackStringBuilder.append(getMainAttackDescription(attacker, target, isHit));
		
		Attribute damageAttribute = (attacker.getMainWeapon() == null
					?attacker.getBodyMaterial().getUnarmedDamageType().getMultiplierAttribute()
					:attacker.getMainWeapon().getDamageType().getMultiplierAttribute());
		
		if(damage==0) {
			attackStringBuilder.append(UtilText.parse(target,"<p>[npc.NameIsFull] completely [style.boldExcellent(immune)] to "+damageAttribute.getName()+"!</p>"));
			
		} else if(isHit) {
			attackStringBuilder.append(
					"<p><b>"+UtilText.parse(target, "[npc.Name] [npc.was]")
					+ (critical
							? " <b style='color: " + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>critically</b>"
							:"")
					+ " hit for " + damage + " <b style='color: "
					+ damageAttribute.getColour().toWebHexString() + ";'>" + damageAttribute.getName() + "</b>!</b></p>");
			
			attackStringBuilder.append(target.incrementHealth(attacker, -damage));
		}
		
		if(attacker.getMainWeapon() != null) {
			attackStringBuilder.append(attacker.getMainWeapon().applyExtraEffects(attacker, target, isHit));
		}

		attackStringBuilder.append(applyExtraAttackEffects(attacker, target, Attack.MAIN, isHit));

		if(!isHit) {
			attackStringBuilder.append(applyExtraMissEffects(attacker, target));
		}
		
		combatStringBuilder.append(getCharactersTurnDiv(attacker, "Main Attack", attackStringBuilder.toString()));
	}

	private static String getMainAttackDescription(GameCharacter attacker, GameCharacter target, boolean isHit) {
		return getPregnancyProtectionText(attacker, target)
				+"<p>"
				+ attacker.getMainAttackDescription(isHit)
				+"</p>";
	}
	
	private static void attackOffhand(GameCharacter attacker) {
		GameCharacter target = getTargetedCombatant(attacker);
		
		boolean critical = Attack.rollForCritical(attacker, target);
		float damage = Attack.calculateDamage(attacker, target, Attack.OFFHAND, critical);
		boolean isHit = Attack.rollForHit(attacker, target);

		attackStringBuilder = new StringBuilder("");

		attackStringBuilder.append(getOffhandDescription(attacker, target, isHit));

		Attribute damageAttribute = (attacker.getOffhandWeapon() == null
					?attacker.getBodyMaterial().getUnarmedDamageType().getMultiplierAttribute()
					:attacker.getOffhandWeapon().getDamageType().getMultiplierAttribute());
	
		if(damage==0) {
			attackStringBuilder.append(UtilText.parse(target,"<p>[npc.NameIsFull] completely [style.boldExcellent(immune)] to "+damageAttribute.getName()+"!</p>"));
			
		} else if(isHit) {
			attackStringBuilder.append(
					"<p><b>"+UtilText.parse(target, "[npc.Name] [npc.was]")
					+ (critical
							? " <b style='color: " + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>critically</b>"
							:"")
					+ " hit for " + damage + " <b style='color: "
					+ damageAttribute.getColour().toWebHexString() + ";'>" + damageAttribute.getName() + "</b>!</b></p>");
	
			attackStringBuilder.append(target.incrementHealth(attacker, -damage));
		}
		
		if(attacker.getOffhandWeapon() != null) {
			attackStringBuilder.append(attacker.getOffhandWeapon().applyExtraEffects(attacker, target, isHit));
		}

		attackStringBuilder.append(applyExtraAttackEffects(attacker, target, Attack.OFFHAND, isHit));

		if(!isHit) {
			attackStringBuilder.append(applyExtraMissEffects(attacker, target));
		}
		
		combatStringBuilder.append(getCharactersTurnDiv(attacker, "Offhand Attack", attackStringBuilder.toString()));
	}

	private static String getOffhandDescription(GameCharacter attacker, GameCharacter target, boolean isHit) {
		return getPregnancyProtectionText(attacker, target)
				+"<p>"
				+ attacker.getOffhandAttackDescription(isHit)
				+"</p>";
	}
	
	
	private static void attackDual(GameCharacter attacker) {
		GameCharacter target = getTargetedCombatant(attacker);
		float damageMain = 0;
		float damageOffhand = 0;
		boolean isHit = Attack.rollForHit(attacker, target);

		attackStringBuilder = new StringBuilder("");
		
		if (isHit && Math.random()<0.5) {
			attackStringBuilder.append(getDualDescription(attacker, target, true));
			
			boolean critical = Attack.rollForCritical(attacker, target);

			damageMain = Attack.calculateDamage(attacker, target, Attack.MAIN, critical);
			damageOffhand = Attack.calculateDamage(attacker, target, Attack.OFFHAND, critical);
			
			Attribute damageMainAttribute = (attacker.getMainWeapon() == null ? attacker.getBodyMaterial().getUnarmedDamageType().getMultiplierAttribute() : attacker.getMainWeapon().getDamageType().getMultiplierAttribute()),
					damageOffhandAttribute = (attacker.getOffhandWeapon() == null ? attacker.getBodyMaterial().getUnarmedDamageType().getMultiplierAttribute() : attacker.getOffhandWeapon().getDamageType().getMultiplierAttribute());
			
			attackStringBuilder.append("<p>"
					+ "<b>"+(attacker.isPlayer()?"You ":UtilText.parse(attacker,"[npc.Name] ")) + (critical ? "<b style='color: " + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>critically</b> " : "")
					+(attacker.isPlayer()?"hit":"hits")+" for "
					+ damageMain + " <b style='color: " + damageMainAttribute.getColour().toWebHexString() + ";'>" + damageMainAttribute.getName() + "</b>, and then again for "
					+ damageOffhand + " <b style='color: " + damageOffhandAttribute.getColour().toWebHexString() + ";'>" + damageOffhandAttribute.getName() + "</b>!</b></p>");
		} else {
			isHit = false;
			attackStringBuilder.append(getDualDescription(attacker, target, false));
			attackStringBuilder.append(UtilText.parse(attacker, "<p><b>[npc.Name] missed!</b></p>"));
		}
		
		attackStringBuilder.append(target.incrementHealth(attacker, -(damageMain+damageOffhand)));
		
		if(attacker.getMainWeapon() != null) {
			attackStringBuilder.append(attacker.getMainWeapon().applyExtraEffects(attacker, target, isHit));
		}
		if(attacker.getOffhandWeapon() != null) {
			attackStringBuilder.append(attacker.getOffhandWeapon().applyExtraEffects(attacker, target, isHit));
		}
		
		attackStringBuilder.append(applyExtraAttackEffects(attacker, target, Attack.DUAL, isHit));
		
		if(!isHit) {
			attackStringBuilder.append(applyExtraMissEffects(attacker, target));
		}
		
		combatStringBuilder.append(getCharactersTurnDiv(attacker, "Dual Strike", attackStringBuilder.toString()));
	}

	private static String getDualDescription(GameCharacter attacker, GameCharacter target, boolean isHit) {
		String attack;
		
		if(attacker.getMainWeapon()!= null) {
			attack = attacker.getMainWeapon().getWeaponType().getAttackDescription(attacker, target, isHit);
		} else if(attacker.getOffhandWeapon()!= null) {
			attack = attacker.getOffhandWeapon().getWeaponType().getAttackDescription(attacker, target, isHit);
		} else {
			attack = AbstractWeaponType.genericMeleeAttackDescription(attacker, target, isHit);
		}

		return getPregnancyProtectionText(attacker, target)
				+"<p>"
					+ attack
				+"</p>";
	}
	
	private static String applyExtraAttackEffects(GameCharacter attacker, GameCharacter target, Attack attackType, boolean isHit) {
		StringBuilder extraAttackEffectsSB = new StringBuilder();
		
		if(attacker.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES_1)
				|| attacker.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES_2)
				|| attacker.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES_3)) {
			float cloakOfFlamesDamage = Math.round(5 * (1 + (Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.DAMAGE_FIRE), 100)/100f)));
			cloakOfFlamesDamage *= 1 - (Util.getModifiedDropoffValue(target.getAttributeValue(Attribute.RESISTANCE_FIRE), 100)/100f);
			cloakOfFlamesDamage = (Math.round(cloakOfFlamesDamage*10))/10f;
			if (cloakOfFlamesDamage < 1) {
				cloakOfFlamesDamage = 1;
			}
			target.incrementHealth(-cloakOfFlamesDamage);
			
			if(attacker.isPlayer()) {
				extraAttackEffectsSB.append(UtilText.parse(target, "<p>[npc.Name] takes an extra <b>"+cloakOfFlamesDamage+"</b> [style.boldFire(Fire Damage)] from your [style.boldFire(Cloak of Flames)]!</p>"));
			} else {
				if(target.isPlayer()) {
					extraAttackEffectsSB.append(UtilText.parse(attacker, "<p>You take an extra <b>"+cloakOfFlamesDamage+"</b> [style.boldFire(Fire Damage)] from [npc.namePos] [style.boldFire(Cloak of Flames)]!</p>"));
				} else {
					extraAttackEffectsSB.append(UtilText.parse(attacker, target, "<p>[npc2.Name] takes an extra <b>"+cloakOfFlamesDamage+"</b> [style.boldFire(Fire Damage)] from [npc1.namePos] [style.boldFire(Cloak of Flames)]!</p>"));
				}
			}
		}
		
		if(target.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES_3)
				&& (((attackType==Attack.MAIN || attackType==Attack.DUAL) && (attacker.getMainWeapon() == null || attacker.getMainWeapon().getWeaponType().isMelee()))
						|| ((attackType==Attack.OFFHAND || attackType==Attack.DUAL) && (attacker.getOffhandWeapon() == null || attacker.getOffhandWeapon().getWeaponType().isMelee())))) {
			float cloakOfFlamesDamage = Math.round(5 * (1 + (Util.getModifiedDropoffValue(target.getAttributeValue(Attribute.DAMAGE_FIRE), 100)/100f)));
			cloakOfFlamesDamage *= 1 - (Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.RESISTANCE_FIRE), 100)/100f);
			cloakOfFlamesDamage = (Math.round(cloakOfFlamesDamage*10))/10f;
			if (cloakOfFlamesDamage < 1) {
				cloakOfFlamesDamage = 1;
			}
			attacker.incrementHealth(-cloakOfFlamesDamage);
			
			if(attacker.isPlayer()) {
				extraAttackEffectsSB.append(UtilText.parse(target, "<p>You take <b>"+cloakOfFlamesDamage+"</b> [style.boldFire(Fire Damage)] from [npc.namePos] [style.boldFire(Ring of Fire)]!</p>"));
			} else {
				if(target.isPlayer()) {
					extraAttackEffectsSB.append(UtilText.parse(attacker, "<p>[npc.Name] takes <b>"+cloakOfFlamesDamage+"</b> [style.boldFire(Fire Damage)] from your [style.boldFire(Ring of Fire)]!</p>"));
				} else {
					extraAttackEffectsSB.append(UtilText.parse(attacker, target, "<p>[npc1.Name] takes <b>"+cloakOfFlamesDamage+"</b> [style.boldFire(Fire Damage)] from [npc2.namePos] [style.boldFire(Ring of Fire)]!</p>"));
				}
			}
		}
		
		if(attacker.hasStatusEffect(StatusEffect.MELEE_FIRE)
				&& isHit
				&& (((attackType==Attack.MAIN || attackType==Attack.DUAL) && (attacker.getMainWeapon() == null || attacker.getMainWeapon().getWeaponType().isMelee()))
						|| ((attackType==Attack.OFFHAND || attackType==Attack.DUAL) && (attacker.getOffhandWeapon() == null || attacker.getOffhandWeapon().getWeaponType().isMelee())))) {
			float fireDamage = Math.round(2 * (1 + (Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.DAMAGE_FIRE), 100)/100f)));
			fireDamage *= 1 - (Util.getModifiedDropoffValue(target.getAttributeValue(Attribute.RESISTANCE_FIRE), 100)/100f);
			fireDamage = (Math.round(fireDamage*10))/10f;
			if (fireDamage < 1) {
				fireDamage = 1;
			}
			target.incrementHealth(-fireDamage);
			
			if(attacker.isPlayer()) {
				extraAttackEffectsSB.append(UtilText.parse(target, "<p>[npc.Name] takes an extra <b>"+fireDamage+"</b> [style.boldFire(Fire Damage)] from your [style.boldFire(Flaming Strikes)]!</p>"));
			} else {
				if(target.isPlayer()) {
					extraAttackEffectsSB.append(UtilText.parse(attacker, "<p>You take an extra <b>"+fireDamage+"</b> [style.boldFire(Fire Damage)] from [npc.namePos] [style.boldFire(Flaming Strikes)]!</p>"));
				} else {
					extraAttackEffectsSB.append(UtilText.parse(attacker, target, "<p>[npc2.Name] takes an extra <b>"+fireDamage+"</b> [style.boldFire(Fire Damage)] from [npc1.namePos] [style.boldFire(Flaming Strikes)]!</p>"));
				}
			}
		}
		
		return extraAttackEffectsSB.toString();
	}
	
	// Calculations for seduction attack:
	private static void attackSeduction(GameCharacter attacker) {
		GameCharacter target = getTargetedCombatant(attacker);
		// Calculate hit + damage
		attackStringBuilder = new StringBuilder("");
		
		attackStringBuilder.append(attacker.getSeductionDescription());
		
		boolean critical = false;//Attack.rollForCritical(attacker);
	
		float lustDamage = Attack.calculateDamage(attacker, target, Attack.SEDUCTION, critical);
		
		if(lustDamage==0) {
			if(target.isPlayer()) {
				attackStringBuilder.append("<p>You are completely [style.boldExcellent(immune)] to "+DamageType.LUST.getName()+" damage!</p>");
			} else {
				attackStringBuilder.append(UtilText.parse(target,"<p>[npc.Name] appears to be completely [style.boldExcellent(immune)] to "+DamageType.LUST.getName()+" damage!</p>"));
			}
			
		} else {
			if(attacker.isPlayer()) {
				attackStringBuilder.append(UtilText.parse(target,
						"<p>"
							+ (critical
									? "Your seductive display was [style.boldExcellent(extremely effective)]!<br/>"
									: "")
						+ "</p>"));
				
			} else if(target.isPlayer()) {
				attackStringBuilder.append(UtilText.parse(attacker,
						"<p>"
							+ (critical
									? "[npc.Her] seductive display was [style.boldExcellent(extremely effective)]!<br/>"
									: "")
						+ "</p>"));
				
			} else {
				attackStringBuilder.append(UtilText.parse(attacker, target,
						"<p>"
							+ (critical
									? "[npc1.Her] seductive display was [style.boldExcellent(extremely effective)]!<br/>"
									: "")
						+ "</p>"));
			}

			attackStringBuilder.append(target.incrementLust(lustDamage, true));
		}
		
		if(attacker.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION)) {
			target.addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED, 3);
			attackStringBuilder.append(Spell.getBasicStatusEffectApplication(target, false, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED, 2))));
		}
		
		combatStringBuilder.append(getCharactersTurnDiv(attacker, "Seduction", attackStringBuilder.toString()));
	}

	private static void attackSpell(GameCharacter attacker, Spell spell) {
		GameCharacter target = getTargetedAlliedCombatant(attacker);
		boolean isHit = true;
		
		if(!spell.isBeneficial(attacker, target)) {
			target = getTargetedCombatant(attacker);
		}
		
		if(spell.getType()==SpellType.OFFENSIVE) {
			isHit = Attack.rollForHit(attacker, target);
		}
		
		boolean critical = Attack.rollForCritical(attacker, target, spell);

		attackStringBuilder = new StringBuilder();

		attackStringBuilder.append(getPregnancyProtectionText(attacker, target));

//		attackStringBuilder.append(attacker.getSpellDescription());
		attackStringBuilder.append(spell.applyEffect(attacker, target, isHit, critical));
		
		if(isHit && critical && !spell.isBeneficial(attacker, target) && attacker.hasTraitActivated(Perk.ARCANE_CRITICALS)) {
			target.addStatusEffect(StatusEffect.ARCANE_WEAKNESS, 2);
			if(attacker.isPlayer()) {
				attackStringBuilder.append(
						"<p>"
							+ UtilText.parse(target, "Your [style.boldExcellent(critical)] spell applies [style.boldArcane(arcane weakness)] to [npc.name]!")
						+ "</p>");
			} else {
				attackStringBuilder.append(
						"<p>"
							+ UtilText.parse(attacker, "[npc.NamePos] [style.boldExcellent(critical)] spell applies [style.boldArcane(arcane weakness)] to "+(target.isPlayer()?"you":UtilText.parse(target, "[npc.name]"))+"!")
						+ "</p>");
			}
		}

		if(!isHit) {
			attackStringBuilder.append(applyExtraMissEffects(attacker, target));
		}
		
		combatStringBuilder.append(getCharactersTurnDiv(attacker, Util.capitaliseSentence(spell.getName()), attackStringBuilder.toString()));
	}

	private static void attackSpecialAttack(GameCharacter attacker, SpecialAttack specialAttack) {
		GameCharacter target = getTargetedCombatant(attacker);
		boolean isHit = Attack.rollForHit(attacker, target);
		boolean critical = Attack.rollForCritical(attacker, target);

		attackStringBuilder = new StringBuilder();
		
		attackStringBuilder.append(getPregnancyProtectionText(attacker, target));
		
		attackStringBuilder.append(specialAttack.applyEffect(attacker, target, isHit, critical));

		if(!isHit) {
			attackStringBuilder.append(applyExtraMissEffects(attacker, target));
		}
		
		combatStringBuilder.append(getCharactersTurnDiv(attacker, Util.capitaliseSentence(specialAttack.getName()), attackStringBuilder.toString()));
	}
	
	private static String applyExtraMissEffects(GameCharacter attacker, GameCharacter target) {
		StringBuilder extraAttackEffectsSB = new StringBuilder();
		
		if(attacker.hasStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST)) {
			attacker.removeStatusEffect(StatusEffect.RAIN_CLOUD);
			attacker.removeStatusEffect(StatusEffect.RAIN_CLOUD_CLOUDBURST);
			attacker.removeStatusEffect(StatusEffect.RAIN_CLOUD_DEEP_CHILL);
			attacker.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR);
			attacker.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST);
			
			attacker.addStatusEffect(StatusEffect.RAIN_CLOUD_CLOUDBURST, 6);
			
			if(attacker.isPlayer()) {
				extraAttackEffectsSB.append("<p>As you miss, the rain cloud above your head seems to grow in size, and suddenly erupts into a torrential cloudburst!</p>");
			} else {
				extraAttackEffectsSB.append(UtilText.parse(attacker, "<p>As [npc.name] misses, the rain cloud above [npc.her] head grows in size, and suddenly erupts into a torrential cloudburst!</p>"));
			}
			extraAttackEffectsSB.append(Spell.getBasicStatusEffectApplication(attacker, false, Util.newHashMapOfValues(new Value<>(StatusEffect.RAIN_CLOUD_CLOUDBURST, 6))));
			
		}
		
		return extraAttackEffectsSB.toString();
	}

	private static void attackWait(GameCharacter attacker) {
		attackStringBuilder = new StringBuilder(
				UtilText.parse(enemies.get(0),
				"<p>"
					+ "You decide not to make a move, and instead try to brace yourself as best as possible against [npc.namePos] next attack."
				+ "</p>"));

		combatStringBuilder.append(getCharactersTurnDiv(attacker, "Wait", attackStringBuilder.toString()));
	}
	
	private static void submit(GameCharacter attacker) {
		attackStringBuilder = new StringBuilder(UtilText.parse(enemies.get(0),
				"<p>"
					+ "You kneel in front of [npc.name], lowering your head in submission as you mutter,"
					+ " [pc.speech(I don't want to fight any more, I submit.)]"
				+ "</p>"));

		combatStringBuilder.append(getCharactersTurnDiv(attacker, "Wait", attackStringBuilder.toString()));
	}

	private static void escape(GameCharacter attacker) {
		boolean allEnemiesStunned = true;
		if(attacker.isPlayer() || getAllies().contains(attacker)) {
			for(NPC enemy : getEnemies()) {
				if(!enemy.isStunned()) {
					allEnemiesStunned = false;
				}
			}
		} else {
			if(Main.game.getPlayer().isStunned()) {
				allEnemiesStunned = false;
			}
			for(NPC ally : getAllies()) {
				if(!ally.isStunned()) {
					allEnemiesStunned = false;
				}
			}
		}
		
		attackStringBuilder = new StringBuilder("<p>");
		if(allEnemiesStunned) {
			escaped = true;
			attackStringBuilder.append("All of your enemies are stunned, so you're easily able to escape!");
		} else if (Util.random.nextInt(100) < escapeChance) {
			escaped = true;
			attackStringBuilder.append("You got away!");
		} else {
			attackStringBuilder.append("You failed to escape!");
		}
		attackStringBuilder.append("</p>");

		combatStringBuilder.append(getCharactersTurnDiv(attacker, "Escape", attackStringBuilder.toString()));
	}

	// Calculations for enemy attack:
	private static void attackNPC(NPC npc) {
		if(allies.contains(npc)) {
			if (isCombatantDefeated(npc)) {
				combatStringBuilder.append(getCharactersTurnDiv(npc,
						"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Defeated!</span>",
						"<p>"
							+ "<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>[npc.Name] has been defeated!</span>"
						+ "</p>"));
				return;
				
			} else if(escaped) {
				combatStringBuilder.append(getCharactersTurnDiv(npc,
						"Escapes with you",
						UtilText.parse(npc,
								"<p>"
									+"[npc.Name] manages to escape with you!"
								+ "</p>")));
				return;
			}
			
		} else {
			if (isCombatantDefeated(npc)) {
				combatStringBuilder.append(getCharactersTurnDiv(npc,
						"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Defeated!</span>",
						"<p>"
							+ "<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>[npc.Name] has been defeated!</span>"
						+ "</p>"));
				return;
				
			} else if(escaped) {
				combatStringBuilder.append(getCharactersTurnDiv(npc,
						"Fails to catch you",
						UtilText.parse(npc,
								"<p>"
									+"[npc.Name] tries to block your escape, but fails..."
								+ "</p>")));
				return;
			}
		}
		
		if(npc.isStunned()) {
			combatStringBuilder.append(getCharactersTurnDiv(npc,
					"Stunned",
					UtilText.parse(npc,
							"<p>"
								+"[npc.Name] is unable to make a move!"
							+ "</p>")));
		} else {
			// Calculate what attack to use based on NPC preference:
			Attack opponentAttack = npc.attackType();
			
			if(opponentAttack==null) {
				opponentAttack = Attack.MAIN;
			}
			
			switch(opponentAttack){
				case DUAL:
					attackDual(npc);
					break;
					
				case ESCAPE:
					break;
					
				case MAIN:
					attackMain(npc);
					break;
					
				case NONE:
					break;
					
				case OFFHAND:
					attackOffhand(npc);
					break;
					
				case SEDUCTION:
					attackSeduction(npc);
					break;
					
				case SPECIAL_ATTACK:
					List<SpecialAttack> attacksAvailable = npc.getSpecialAttacksAbleToUse();
					SpecialAttack specialAttack = attacksAvailable.get(Util.random.nextInt(attacksAvailable.size()));
					attackSpecialAttack(npc, specialAttack);
					break;
					
				case SPELL:
					Map<Spell, Integer> spellsAvailableMap = npc.getWeightedSpellsAvailable(getTargetedCombatant(npc));
					
					Spell spell = Util.getRandomObjectFromWeightedMap(spellsAvailableMap);
//					System.out.println(spellsAvailable.size());
					attackSpell(npc, spell);
					break;
					
				case USE_ITEM:
					break;
					
				default:
					break;
				
			}
			
//			if(isEnemyPartyDefeated()) {
//				opponentActionText = "<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Defeated!</span>";
//				opponentTurnText = combatStringBuilder.toString()
//						+"<p>"
//						+opponent.getName("The")+" doesn't have the strength to continue fighting...<br/>"
//						+ "<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>You are victorious!</span>"
//						+ "</p>";
//				
//			}	else{
//				opponentTurnText = combatStringBuilder.toString();
//			}
		}
		
	}

	private static StringBuilder endTurnStatusEffectText = new StringBuilder();

	public static void endCombatTurn() {
		
		List<NPC> combatants = new ArrayList<>(allCombatants); // To avoid concurrent modification when the 'summon elemental' spell adds combatants.
		for(NPC character : combatants) {
			attackNPC(character);
		}
		
		// Player end turn effects:
//		removeBeneficialEffects(Main.game.getPlayer());
		for(SpecialAttack sa : Combat.getCooldowns(Main.game.getPlayer()).keySet()) {
			Combat.incrementCooldown(Main.game.getPlayer(), sa, -1);
		}

		// NPC end turn effects:
		for(NPC character : allCombatants) {
//			removeBeneficialEffects(character);
			for(SpecialAttack sa : Combat.getCooldowns(character).keySet()) {
				Combat.incrementCooldown(character, sa, -1);
			}
		}
		
		combatContent = combatStringBuilder.toString();
		combatStringBuilder.setLength(0);
		
		if(isCombatantDefeated(targetedCombatant)) {
			for(NPC enemy : enemies) {
				if(!isCombatantDefeated(enemy)) {
					targetedCombatant = enemy;
					break;
				}
			}
		}
		
		turn++;
	}
	
	private static String applyEffects(GameCharacter character) {
		endTurnStatusEffectText = new StringBuilder();
		List<StatusEffect> effectsToRemove = new ArrayList<>();
		for (StatusEffect se : character.getStatusEffects()) {
			if (se.isCombatEffect()) {
				String effectString = se.applyEffect(character, 0);
				if(!effectString.isEmpty()) {
					endTurnStatusEffectText.append("<p><b style='color: " + se.getColour().toWebHexString() + "'>" + Util.capitaliseSentence(se.getName(character)) + ":</b> " + effectString+ "</p>");
				}
//				if (!se.isBeneficial()) {
					character.setStatusEffectDuration(se, character.getStatusEffectDuration(se) - 1);
//				}
				if (character.getStatusEffectDuration(se) <= 0) {
					effectsToRemove.add(se);
				}
			}
		}
		for (StatusEffect se : effectsToRemove) {
			endTurnStatusEffectText.append(character.removeStatusEffectCombat(se));
		}
		return endTurnStatusEffectText.toString();
	}

//	private static void removeBeneficialEffects(GameCharacter character) {
//		List<StatusEffect> effectsToRemove = new ArrayList<>();
//		for (StatusEffect se : character.getStatusEffects()) {
//			if (se.isCombatEffect()) {
//				if (se.isBeneficial()) {
//					character.setStatusEffectDuration(se, character.getStatusEffectDuration(se) - 1);
//				}
//				if (character.getStatusEffectDuration(se) <= 0) {
//					effectsToRemove.add(se);
//				}
//			}
//		}
//		for (StatusEffect se : effectsToRemove) {
//			character.removeStatusEffect(se);
//		}
//	}
	

	// Utility methods:
	private static String getCombatLabel() {
		if(turn==0) {
			return "Combat - Start";
		} else {
			return "Combat - Turn "+turn;
		}
	}

	private static StringBuilder attackDescriptionSB = new StringBuilder();
	
	private static String getMainAttackDescription() {
		attackDescriptionSB = new StringBuilder();
		
		if(Main.game.getPlayer().getMainWeapon()!=null) {
			attackDescriptionSB.append(Main.game.getPlayer().getMainWeapon().getWeaponType().getAttackDescription(Main.game.getPlayer(), targetedCombatant)+"<br/>");
		} else {
			attackDescriptionSB.append(UtilText.parse(targetedCombatant, "Attack [npc.name] in melee!<br/>"));
		}
		
		if (Main.game.getPlayer().getMainWeapon() == null) {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getBodyMaterial().getUnarmedDamageType().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getBodyMaterial().getUnarmedDamageType().getName()) + "</b> <b>damage</b><br/><br/>");
		} else {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getMainWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getMainWeapon().getDamageType().getName())
					+ "</b> <b>damage</b><br/><br/>");
		}

		attackDescriptionSB.append("Main and offhand attacks <b style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>always hit</b>.");
		
		return attackDescriptionSB.toString();
	}
	
	private static String getOffhandAttackDescription() {
		attackDescriptionSB = new StringBuilder();

		if(Main.game.getPlayer().getOffhandWeapon()!=null) {
			attackDescriptionSB.append(Main.game.getPlayer().getOffhandWeapon().getWeaponType().getAttackDescription(Main.game.getPlayer(), targetedCombatant)+"<br/>");
		} else {
			attackDescriptionSB.append(UtilText.parse(targetedCombatant, "Attack [npc.name] in melee!<br/>"));
		}
		
		if (Main.game.getPlayer().getOffhandWeapon() == null) {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getBodyMaterial().getUnarmedDamageType().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getBodyMaterial().getUnarmedDamageType().getName()) + "</b> <b>damage</b><br/><br/>");
		} else {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getOffhandWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getOffhandWeapon().getDamageType().getName())
					+ "</b> <b>damage</b><br/><br/>");
		}

		attackDescriptionSB.append("Main and offhand attacks <b style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>always hit</b>.");
		
		return attackDescriptionSB.toString();
	}
	
	private static String getDualAttackDescription() {
		attackDescriptionSB = new StringBuilder();
		
		attackDescriptionSB.append("Give " + targetedCombatant.getName("the") + " everything you've got!<br/><br/>");
		
		if (Main.game.getPlayer().getMainWeapon() == null) {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getBodyMaterial().getUnarmedDamageType().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getBodyMaterial().getUnarmedDamageType().getName()) + "</b> <b>damage</b><br/>");
		} else {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getMainWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getMainWeapon().getDamageType().getName())
					+ "</b> <b>damage</b><br/>");
		}
		
		if (Main.game.getPlayer().getOffhandWeapon() == null) {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getBodyMaterial().getUnarmedDamageType().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getBodyMaterial().getUnarmedDamageType().getName()) + "</b> <b>damage</b><br/>");
		} else {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getOffhandWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getOffhandWeapon().getDamageType().getName())
					+ "</b> <b>damage</b><br/>");
		}

		attackDescriptionSB.append("You have a <b>50%</b> <b style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>chance to hit</b>.");
		
		return attackDescriptionSB.toString();
	}

	private static String getTeaseDescription() {

		return "Attempt to seduce " + targetedCombatant.getName("the") + ".<br/><br/>"

				+ "<b>"
				+ Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.SEDUCTION) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.SEDUCTION) + "</b>"
				+ " <b style='color:"+ Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(DamageType.LUST.getName()) + "</b> <b>damage</b><br/><br/>"

				+ "Seduction attacks <b style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>always hit</b>.";
	}

	private static String getSpellDescription(Spell spell, AbstractWeapon source) {
		return "Cast <b style='color:" + spell.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(spell.getName()) + "</b><br/><br/>"

				+ "<b>"
					+ Attack.getMinimumSpellDamage(Main.game.getPlayer(), targetedCombatant, spell.getDamageType(), spell.getDamage(Main.game.getPlayer()), spell.damageVariance)
					+ " - "
					+ Attack.getMaximumSpellDamage(Main.game.getPlayer(), targetedCombatant, spell.getDamageType(), spell.getDamage(Main.game.getPlayer()), spell.damageVariance)
				+ "</b>"
				+ " <b style='color:"
				+ spell.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(spell.getDamageType().getName()) + "</b> <b>damage</b><br/><br/>"
				
				+ "<b>" + spell.getModifiedCost(Main.game.getPlayer()) + "</b> <b style='color:"+ Colour.ATTRIBUTE_MANA.toWebHexString() + ";'>aura</b> <b>cost</b><br/><br/>";
	}

	private static String getSpecialAttackDescription(SpecialAttack specialAttack) {

		return "Use your <b style='color:" + specialAttack.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(specialAttack.getName()) + "</b> special attack.<br/><br/>"

				+ "<b>"
					+ Attack.getMinimumSpecialAttackDamage(Main.game.getPlayer(), targetedCombatant, specialAttack.getDamageType(), specialAttack.getDamage(), specialAttack.getDamageVariance())
					+ " - "
					+ Attack.getMaximumSpecialAttackDamage(Main.game.getPlayer(), targetedCombatant, specialAttack.getDamageType(), specialAttack.getDamage(), specialAttack.getDamageVariance())
				+ "</b>" 
				+ " <b style='color:"+ specialAttack.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(specialAttack.getDamageType().getName()) + "</b> <b>damage</b><br/><br/>"


				+ "<b style='color:"+ Colour.GENERIC_MINOR_BAD.toWebHexString() + ";'>" + specialAttack.getCooldown() + "-turn cooldown</b><br/><br/>";
	}

	public static GameCharacter getTargetedCombatant(GameCharacter attacker) {
		if(attacker.isPlayer()) {
			return targetedCombatant;
		}
		
		if(allies.contains(attacker)) {
			for(NPC enemy : enemies) {
				if(!isCombatantDefeated(enemy)) {
					return enemy;
				}
			}
			return enemies.get(0);
		}
		
		if(enemies.contains(attacker)) {
			for(NPC playerAlly : allies) {
				if(!isCombatantDefeated(playerAlly)) {
					return playerAlly;
				}
			}
		}
		
		return Main.game.getPlayer();
	}
	
	public static GameCharacter getTargetedAlliedCombatant(GameCharacter attacker) {
		if(attacker.isPlayer()) {
			return targetedCombatant;
		}
		
		if(allies.contains(attacker)) {
			if(Math.random()>0.5f && !isCombatantDefeated(Main.game.getPlayer())) {
				return Main.game.getPlayer();
			}
			for(NPC ally : allies) {
				if(!isCombatantDefeated(ally)) {
					return ally;
				}
			}
			return allies.get(0);
		}
		
		if(enemies.contains(attacker)) {
			for(NPC enemy : enemies) {
				if(!isCombatantDefeated(enemy)) {
					return enemy;
				}
			}
		}
		
		return Main.game.getPlayer();
	}

	public static void setTargetedCombatant(GameCharacter targetedCombatant) {
		Combat.targetedCombatant = targetedCombatant;
	}

	public static NPC getActiveNPC() {
		return activeNPC;
	}

	public static void setActiveNPC(NPC activeNPC) {
		Combat.activeNPC = activeNPC;
	}

	public static AbstractItem getItem() {
		return item;
	}

	public static void setItem(AbstractItem item) {
		Combat.item = item;
	}

	public static Attack getPreviousAction() {
		return previousAction;
	}

	public static void setPreviousAction(Attack previousAction) {
		Combat.previousAction = previousAction;
	}
	
	private static String getPregnancyProtectionText(GameCharacter attacker, GameCharacter target) {
		if(target.isPlayer()) {
			return (target.isVisiblyPregnant()
					?UtilText.parse(attacker,
						"<p>"
							+ "A powerful field of arcane energy is protecting your pregnant belly, which doesn't even come into play, as [npc.name] is very careful not to hit anywhere near your stomach."
						+ "</p>")
					:"");
		} else {
			if(attacker.isPlayer()) {
				return (target.isVisiblyPregnant()
						?UtilText.parse(target,
							"<p>"
								+ "A powerful field of arcane energy is protecting [npc.namePos] pregnant belly, which doesn't even come into play, as you're very careful not to hit anywhere near [npc.her] stomach."
							+ "</p>")
						:"");
			} else {
				return (target.isVisiblyPregnant()
						?UtilText.parse(attacker, target,
							"<p>"
								+ "A powerful field of arcane energy is protecting [npc2.namePos] pregnant belly, which doesn't even come into play, as [npc1.name] is very careful not to hit anywhere near [npc2.her] stomach."
							+ "</p>")
						:"");
			}
		}
	}

	public static List<NPC> getAllCombatants() {
		return allCombatants;
	}
	
	public static void addAlly(NPC ally) {
		allies.add(ally);
		allCombatants.add(ally);
		cooldowns.put(ally, new HashMap<>());
	}
	
	public static void addEnemy(NPC enemy) {
		enemies.add(enemy);
		allCombatants.add(enemy);
		cooldowns.put(enemy, new HashMap<>());
		enemy.setFoughtPlayerCount(enemy.getFoughtPlayerCount()+1);
	}
	
	public static List<NPC> getAllies() {
		return allies;
	}

	public static List<NPC> getEnemies() {
		return enemies;
	}

	/**
	 * @param target The character whose party member will be returned.
	 * @return A random member of the target's party. WIll attempt to return a member that isn't the target, but if the target's party only contains them, will return the target. 
	 */
	public static GameCharacter getRandomAlliedPartyMember(GameCharacter target) {
		if(target.isPlayer()) {
			if(getAllies().isEmpty()) {
				return target;
			} else {
				return getAllies().get(Util.random.nextInt(getAllies().size()));
			}
			
		} else if(getAllies().contains(target)) {
			List<GameCharacter> possibleTargets = new ArrayList<>();
			possibleTargets.add(Main.game.getPlayer());
			for(GameCharacter character : getAllies()) {
				if(!character.equals(target)) {
					possibleTargets.add(character);
				}
			}
			return possibleTargets.get(Util.random.nextInt(possibleTargets.size()));
			
		} else {
			if(getEnemies().size()==1) {
				return target;
			} else {
				List<GameCharacter> possibleTargets = new ArrayList<>();
				for(GameCharacter character : getEnemies()) {
					if(!character.equals(target)) {
						possibleTargets.add(character);
					}
				}
				return possibleTargets.get(Util.random.nextInt(possibleTargets.size()));
			}
		}
	}
	
	public static Map<SpecialAttack, Integer> getCooldowns(GameCharacter character) {
		return cooldowns.get(character);
	}
	
	public static void clearCooldowns(GameCharacter character) {
		cooldowns.get(character).clear();
	}
	
	public static int getCooldown(GameCharacter character, SpecialAttack attack) {
		cooldowns.get(character).putIfAbsent(attack, 0);
		
		return cooldowns.get(character).get(attack);
	}
	
	public static void setCooldown(GameCharacter character, SpecialAttack attack, int cooldown) {
		cooldowns.get(character).put(attack, cooldown);
	}
	
	public static void incrementCooldown(GameCharacter character, SpecialAttack attack, int increment) {
		cooldowns.get(character).putIfAbsent(attack, 0);
		
		cooldowns.get(character).put(attack, Math.max(0, cooldowns.get(character).get(attack)+increment));
	}

	public static int getTurn() {
		return turn;
	}
}
