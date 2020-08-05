package com.lilithsthrone.game.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.AppliedStatusEffect;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.moves.CombatMove;
import com.lilithsthrone.game.combat.moves.CombatMoveType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Singleton enforced by Enum.<br/>
 * Call initialiseCombat() before using.
 *
 * @since 0.1.0
 * @version 0.3.9
 * @author Innoxia, Irbynx
 */
public enum Combat {
	COMBAT;

	// TODO Make sure your status effects end before you take your turn, enemy's status effects end at the start of their turn
	// Also, end combat it enemy drops to 0 health/mana/stamina on their turn from combat effects

	private static NPC activeNPC;
	private static GameCharacter targetedAlly;
	private static GameCharacter targetedEnemy;
	private static NPC enemyLeader;
	
	private static List<NPC> allies = new ArrayList<>();
	private static List<NPC> enemies = new ArrayList<>();
	private static List<NPC> allCombatants = new ArrayList<>();
	private static List<GameCharacter> activeCombatants; // A list of combatants who are still active in the fight. This is updated at the very end of each combat turn, and removes characters which have been defeated during the last turn.
	
	private static float escapeChance = 0;
	private static Map<GameCharacter, Float> totalDamageTaken;
	private static int turn = 0;
	private static boolean attemptedEscape = false;
	private static boolean escaped = false;
	private static boolean playerVictory = false;
	private static StringBuilder postCombatStringBuilder = new StringBuilder();
	
	private static StringBuilder combatTurnResolutionStringBuilder = new StringBuilder();
	
	private static Map<GameCharacter, Stack<Float>> manaBurnStack;
	
	private static Map<GameCharacter, Map<AbstractStatusEffect, Integer>> statusEffectsToApply;
	
	private static Map<GameCharacter, List<String>> combatContent;
	private static Map<GameCharacter, List<String>> predictionContent;
	
	private static Map<GameCharacter, List<Value<GameCharacter, AbstractItem>>> itemsToBeUsed;
	
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
			NPC enemyLeader,
			List<NPC> enemies,
			Map<GameCharacter, String> openingDescriptions) {
		
		allCombatants = new ArrayList<>();
		Combat.allies = new ArrayList<>();
		Combat.enemyLeader = enemyLeader;
		Combat.enemies = new ArrayList<>();
		activeCombatants = new ArrayList<>();

		predictionContent = new HashMap<>();
		combatContent = new HashMap<>();
		itemsToBeUsed = new HashMap<>();
		manaBurnStack = new HashMap<>();
		statusEffectsToApply = new HashMap<>();
		

		predictionContent.put(Main.game.getPlayer(), new ArrayList<>());
		itemsToBeUsed.put(Main.game.getPlayer(), new ArrayList<>());
		manaBurnStack.put(Main.game.getPlayer(), new Stack<>());
		statusEffectsToApply.put(Main.game.getPlayer(), new HashMap<>());
		combatContent.put(Main.game.getPlayer(), new ArrayList<>());
		activeCombatants.add(Main.game.getPlayer());

		if(Main.game.getPlayer().isElementalSummoned()) {
			Combat.addAlly(Main.game.getPlayer().getElemental());
			Main.game.getPlayer().getElemental().setLocation(Main.game.getPlayer(), false);
		}
		if(allies!=null){
			for(NPC ally : allies) {
				Combat.addAlly(ally);
				if(ally.isElementalSummoned()) {
					Combat.addAlly(ally.getElemental());
					ally.getElemental().setLocation(ally, false);
				}
			}
		}
		for(NPC enemy : enemies) {
			Combat.addEnemy(enemy);
			if(enemy.isElementalSummoned()) {
				Combat.addEnemy(enemy.getElemental());
				enemy.getElemental().setLocation(enemy, false);
			}
		}
		enemies.sort((enemy1, enemy2) -> enemy2.getLevel()-enemy1.getLevel());
		
		
		targetedEnemy = enemies.get(0);
		targetedAlly = Main.game.getPlayer();
		activeNPC = enemies.get(0);

		attemptedEscape = false;
		escaped = false;
		playerVictory = false;
				
		totalDamageTaken = new HashMap<>();
		turn = 0;
		postCombatStringBuilder.setLength(0);
		combatTurnResolutionStringBuilder.setLength(0);
		
		

		escapeChance = ((NPC) enemies.get(0)).getEscapeChance();
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
			for(GameCharacter enemy : Combat.getEnemies(Main.game.getPlayer())) {
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
		
		combatContent.get(Main.game.getPlayer()).add(
				(Main.game.getPlayer().hasTrait(Perk.JOB_SOLDIER, true)
					?"Any "+Attribute.HEALTH_MAXIMUM.getName()+" damage you deal in this first turn is [style.boldExcellent(doubled)] thanks to your"
							+ " <b style='color:"+Perk.JOB_SOLDIER.getColour().toWebHexString()+";'>"+Perk.JOB_SOLDIER.getName(Main.game.getPlayer())+"</b> ability."
					:""));
		
		combatContent.get(Main.game.getPlayer()).add(
				openingDescriptions!=null && openingDescriptions.containsKey(Main.game.getPlayer())
					?openingDescriptions.get(Main.game.getPlayer())
					:"You prepare to make a move...");
		
		String pregProtection = getPregnancyProtectionText(Main.game.getPlayer());
		if(!pregProtection.isEmpty()) {
			combatContent.get(Main.game.getPlayer()).add(pregProtection);
		}
		
		combatContent.get(Main.game.getPlayer()).add(startingEffect);
		
		for(NPC combatant : allCombatants) {
			startingEffect="";
			if(combatant.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)) {
				combatant.addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 11);
				startingEffect = Spell.getBasicStatusEffectApplication(combatant, true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 10)));
				
			} else if(combatant.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2)) {
				combatant.addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 11);
				startingEffect = Spell.getBasicStatusEffectApplication(combatant, true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 10)));
				
			} else if(combatant.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1)) {
				combatant.addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION, 11);
				startingEffect = Spell.getBasicStatusEffectApplication(combatant, true, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION, 10)));
			}
			
			combatContent.get(combatant).add(UtilText.parse(combatant,
					(combatant.hasTrait(Perk.JOB_SOLDIER, true)
						?"Any "+Attribute.HEALTH_MAXIMUM.getName()+" damage [npc.name] deals in this first turn is [style.boldExcellent(doubled)] thanks to [npc.her]"
								+ " <b style='color:"+Perk.JOB_SOLDIER.getColour().toWebHexString()+";'>"+Perk.JOB_SOLDIER.getName(combatant)+"</b> ability."
						:"")
					));
			
			combatContent.get(combatant).add(UtilText.parse(combatant,
					openingDescriptions!=null && openingDescriptions.containsKey(combatant)
						?openingDescriptions.get(combatant)
						:"[npc.Name] prepares to make a move..."));
			
			pregProtection = getPregnancyProtectionText(combatant);
			if(!pregProtection.isEmpty()) {
				combatContent.get(combatant).add(pregProtection);
			}
			
			combatContent.get(combatant).add(startingEffect);
		}
		
		Main.game.getPlayer().resetSelectedMoves();
		Main.game.getPlayer().resetMoveCooldowns();
		applyNewTurnShielding(Main.game.getPlayer());
		Main.game.getPlayer().setRemainingAP(Main.game.getPlayer().getMaxAP(), null, null);
		
		combatTurnResolutionStringBuilder.append(getCharactersTurnDiv(Main.game.getPlayer(), Combat.getTurn()==0?"Preparation":"", combatContent.get(Main.game.getPlayer())));

		Main.game.setInCombat(true);
		
		for(NPC npc : allCombatants) {
			combatTurnResolutionStringBuilder.append(getCharactersTurnDiv(npc, Combat.getTurn()==0?"Preparation":"", combatContent.get(npc)));
			
			npc.resetSelectedMoves();
			npc.resetDefaultMoves(); // Resetting in case the save file was too old and NPC has no moves selected for them.
			npc.resetMoveCooldowns();
			applyNewTurnShielding(npc);
			npc.setRemainingAP(npc.getMaxAP(), null, null);
			// Sets up NPC ally/enemy lists that include player
			List<GameCharacter> npcAllies = Combat.getAllies(npc);
			List<GameCharacter> npcEnemies = Combat.getEnemies(npc);
			
			// Selects the moves
			npc.selectMoves(npcEnemies, npcAllies);
			predictionContent.put(npc, npc.getMovesPredictionString(npcEnemies, npcAllies));
		}
		
		Main.mainController.openInventory();
	}

	public static void setCharacterTurnContent(GameCharacter character, List<String> descriptions) {
		combatContent.put(character, descriptions);
	}
	
	private static String getCharactersTurnDiv(GameCharacter character, String title, List<String> descriptions) {
		String effects = Combat.applyEffects(character);
		StringBuilder sb = new StringBuilder();
		
		boolean enemy = enemies.contains(character);
		
		sb.append("<div class='container-full-width' style='text-align:center; box-sizing: border-box; border:6px solid "+(enemy?PresetColour.GENERIC_MINOR_BAD:PresetColour.GENERIC_MINOR_GOOD).getShades()[0]+"; border-radius:5px;'>");

			sb.append(
					"<div class='container-full-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; text-align:center;'>"
						+ "<b style='color:"+character.getFemininity().getColour().toWebHexString()+";'>"
							+ UtilText.parse(character, "[npc.Name]")
						+ "</b>"
					+ "</div>");
		
			sb.append("<div class='container-full-width'>");
				for(String s : descriptions) {
					if(!s.isEmpty()) {
						sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+PresetColour.BACKGROUND.toWebHexString()+";'>"+s+"</div>");
					}
				}
				if(!effects.isEmpty()) {
					sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+PresetColour.BACKGROUND.toWebHexString()+";'>"+effects+"</div>");
				}
			sb.append("</div>");
				
		sb.append("</div>");
		
		return sb.toString();
	}
	
	/**
	 * Ends combat, removing status effects and handling post-combat experience gains and loot drops.
	 * 
	 * @param playerVictory
	 */
	public static void endCombat(boolean playerVictory) {
		
		postCombatStringBuilder.setLength(0);
		
		Combat.playerVictory = playerVictory;

		for(NPC enemy : enemies) {
			enemy.removeFlag(NPCFlagValue.playerEscapedLastCombat);
		}
		
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
				if(!(ally.isElemental())) {
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
			}

			List<String> itemsLooted = new ArrayList<>();
			for(Entry<AbstractCoreItem, Integer> entry : lootedItemsMap.entrySet()) {
				itemsLooted.add("<b style='color:"+entry.getKey().getRarity().getColour().toWebHexString()+";'>"+entry.getKey().getName()+"</b>"+(entry.getValue()>1?" <b>(x"+entry.getValue()+")</b>":""));
			}
			if(!itemsLooted.isEmpty()) {
				postCombatStringBuilder.append("<div class='container-full-width' style='text-align:center;'>You [style.boldGood(gained)] " + Util.stringsToStringList(itemsLooted, false) +"!</div>");
			}
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
										+ "<i>"
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
										+ "</i>"
										+ "</p>"
										+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"")));
								
							} else {
								postCombatStringBuilder.append(
										UtilText.parse(enemy,
										"<p>"
										+ "<i>"
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
										+ "</i>"
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
				if(!(enemy.isElemental())) {
					postCombatStringBuilder.append(enemy.incrementExperience(xpGain, true));
				}
			}
			
			int money = Main.game.getPlayer().getMoney();
			int moneyLoss = (-enemyLeader.getLootMoney()/2)*enemies.size();
			Main.game.getPlayer().incrementMoney(moneyLoss);
			postCombatStringBuilder.append("<div class='container-full-width' style='text-align:center;'>You [style.boldBad(lost)] " + UtilText.formatAsMoney(Math.abs(Main.game.getPlayer().getMoney()==0?money:moneyLoss)) + "!</div>");
			
			for(NPC enemy : enemies) {
				enemy.setWonCombatCount(enemy.getWonCombatCount()+1);
			}
		}
		
		// Remove elementals:
		for(GameCharacter combatant : getAllCombatants(true)) {
			if(combatant.isElementalSummoned()) {
				combatant.getElemental().returnToHome();
				if((playerVictory && Combat.getEnemies(Main.game.getPlayer()).contains(combatant))
						 || (!playerVictory && !Combat.getEnemies(Main.game.getPlayer()).contains(combatant))) {
					combatant.setElementalSummoned(false);
					postCombatStringBuilder.append(UtilText.parse(combatant, combatant.getElemental(),
							"<p style='text-align:center;'><i>"
								+ "[npc.NamePos] elemental, <span style='colour:"+combatant.getElemental().getFemininity().getColour().toWebHexString()+";'>[npc2.name]</span>,"
									+ " is completely drained of energy and is [style.italicsBad(dispelled)]!"
							+ "</i></p>"));
				} else { 
					postCombatStringBuilder.append(UtilText.parse(combatant, combatant.getElemental(),
							"<p style='text-align:center;'><i>"
								+ "[npc.NamePos] elemental, <span style='colour:"+combatant.getElemental().getFemininity().getColour().toWebHexString()+";'>[npc2.name]</span>,"
									+ " is drained of energy and [style.italicsArcane(returns to [npc2.her] passive form)]!"
							+ "</i></p>"));
				}
			}
		}
		
		Main.game.setInCombat(false);
		
		// Sort out effects after combat:
		if (Main.game.getPlayer().getHealth() == 0) {
			Main.game.getPlayer().setHealth(5);
		}
		if (Main.game.getPlayer().getMana() == 0) {
			Main.game.getPlayer().setMana(5);
		}
		
		// Reset opponent resources to starting values:
		for(NPC enemy : enemies) {
			enemy.setMana(enemy.getAttributeValue(Attribute.MANA_MAXIMUM));
			enemy.setHealth(enemy.getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}

		Main.game.getTextStartStringBuilder().append(postCombatStringBuilder.toString());
	}

	private static String npcStatus() {
		return "";
	}

	// DIALOGUES:
	public DialogueNode startCombat() {
		return ENEMY_ATTACK;
	}
	
	public static boolean isCombatantDefeated(GameCharacter character) {
		return (character.getHealth() <= 0 || (character.getLust()>=100 && character.isVulnerableToLustLoss()));
	}
	
	public static boolean isOpponent(GameCharacter character, GameCharacter target) {
		if(allies.contains(character) || character.isPlayer()) {
			return enemies.contains(target);
		} else {
			return allies.contains(target) || target.isPlayer();
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

	public static final DialogueNode ITEM_USED = new DialogueNode("Combat", "Use the item.", true) {
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
			return getCombatContent();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (isEnemyPartyDefeated()) {
					return new ResponseEffectsOnly("Victory", "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>You are victorious!</span>"){
						@Override
						public void effects() {
							endCombat(true);
							Main.game.setContent(enemyLeader.endCombat(true, true));
						}
					};
				} else {
					return new Response("Continue", "Combat continues.", ENEMY_ATTACK){
						@Override
						public void effects() {
							endCombatTurn();//TODO test
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

	public static final DialogueNode SUBMIT = new DialogueNode("Combat", "Submit", true) {

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
			return UtilText.parse(enemyLeader,
							"<p>"
									+ "Are you certain you want to <b>submit</b> to [npc.name]? <b>This will cause you to lose the fight, allowing [npc.herHim] to do anything [npc.she] wants with you!</b>"
							+ "</p>");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Submit",
						UtilText.parse(enemyLeader,
								"Submit to [npc.name]. <span style='color:" + PresetColour.GENERIC_TERRIBLE.toWebHexString() + ";'>This will cause you to lose the current combat!</span>"),
						SUBMIT_CONFIRM){
					@Override
					public void effects() {
						StringBuilder sb = new StringBuilder();
						
						sb.append(getCharactersTurnDiv(Main.game.getPlayer(), "Submit",
								Util.newArrayListOfValues(UtilText.parse(enemyLeader,
									"You kneel in front of [npc.name], lowering your head in submission as you mutter,"
										+ " [pc.speech(I don't want to fight any more, I submit.)]"))));

						sb.append(getCharactersTurnDiv(enemyLeader, "Victory",
								Util.newArrayListOfValues(UtilText.parse(enemyLeader,
									"[npc.Name] lets out a triumphant laugh, before moving forwards to take advantage of your submission..."))));
						
						Main.game.getTextStartStringBuilder().append(sb.toString());
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
	public static final DialogueNode SUBMIT_CONFIRM = new DialogueNode("Combat", "Submit", true) {

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
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Continue", UtilText.parse(enemyLeader, "You wait for [npc.name] to make a move.")){
					@Override
					public void effects() {
						endCombat(false);
						Main.game.setResponseTab(0);
						Main.game.setContent(enemyLeader.endCombat(true, false));
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

	public static final DialogueNode ENEMY_ATTACK = new DialogueNode("Combat", "The enemy strikes back at you.", true) {

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
			return getCombatContent();
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			if(enemyLeader.interruptCombatSpecialCase()!=null) {
				return null;
			}
			if(index==0) {
				return "Core moves";
				
			} else if(index==1) {
				return "Basic moves";
				
			} else if(index==2) {
				return "Special moves";
				
			} else if(index==3) {
				return "Spells";
				
			} else if(index==4) {
				return "Commands";
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			//TODO
			if(enemyLeader.interruptCombatSpecialCase()!=null) {
				if(index == 1) {
					return enemyLeader.interruptCombatSpecialCase();
				}
				return null;
			}
			if(Main.game.getPlayer().isStunned()) {
				if (index == 1) {
					return new Response("Stunned!", "You are unable to make an action this turn!", ENEMY_ATTACK){
						@Override
						public void effects() {
							combatContent.put(Main.game.getPlayer(), Util.newArrayListOfValues("You are stunned, and are unable to make a move!"));
							endCombatTurn();
						}
					};
					
				} else {
					return null;
				}
				
			} else if(escaped) {
				if (index == 1) {
					return new ResponseEffectsOnly("Escaped!", "You got away!"){
						@Override
						public void effects() {
							enemyLeader.applyEscapeCombatEffects();
							for(NPC enemy : enemies) {
								enemy.addFlag(NPCFlagValue.playerEscapedLastCombat);
							}
							Main.game.setInCombat(false);
							Main.game.setResponseTab(0);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
				} else {
					return null;
				}
				
			} else if(isEnemyPartyDefeated()) {
				if (index == 1) {
					return new ResponseEffectsOnly("Victory", UtilText.parse(enemyLeader, "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>You have defeated [npc.name]!</span>")){
						@Override
						public void effects() {
							endCombat(true);
							Main.game.setResponseTab(0);
							Main.game.setContent(enemyLeader.endCombat(true, true));
						}
					};
				} else
					return null;
				
			}  else if(isAlliedPartyDefeated()) {
				if (index == 1) {
					return new ResponseEffectsOnly("Defeat", "You have been defeated!"){
						@Override
						public void effects() {
							endCombat(false);
							Main.game.setResponseTab(0);
							Main.game.setContent(enemyLeader.endCombat(true, false));
						}
					};
				} else
					return null;
				
			} else if(isCombatantDefeated(Main.game.getPlayer())) {
				if (index == 1) {
					return new Response("Watch", "You have been defeated, and can only watch as your allies fight on!", ENEMY_ATTACK){
						@Override
						public void effects() {
							combatContent.put(Main.game.getPlayer(), Util.newArrayListOfValues("You have been defeated, and can only watch as your allies continue the fight!"));
							endCombatTurn();
						}
					};
					
				} else {
					return null;
				}
				
			}

			List<GameCharacter> pcEnemies = getEnemies(Main.game.getPlayer());
			List<GameCharacter> pcAllies = getAllies(Main.game.getPlayer());
			pcAllies.add(Main.game.getPlayer());

			if(index == 0) {
				return new Response("End Turn",
						Main.game.getPlayer().getRemainingAP()<=0?"Ends your current turn":"Ends your current turn. You still have unspent AP!",
						ENEMY_ATTACK){
					@Override
					public void effects() {
						endCombatTurn();
					}
					@Override
					public Colour getHighlightColour() {
						if(Main.game.getPlayer().getRemainingAP() > 0) {
							return PresetColour.GENERIC_BAD;
						} else {
							return PresetColour.GENERIC_GOOD;
						}
					}
				};
				
			} else if(index<=10 || index>14) {
				
				int moveIndex =
						index<=10
							?index-1
							:index-5;
				
				if(responseTab==0 || responseTab==1) {
					moveIndex =
							index<=8
								?index-1
								:index-7;
					
					if(index==9) {
						return new Response("Submit",
								(Combat.getEnemies(Main.game.getPlayer()).size()==1
									?"Surrender this fight to your opponent, allowing them to do whatever they want to you."
									:"Surrender this fight to your enemies, allowing them to do whatever they want to you."),
								SUBMIT);
						
					} else if(index==10) {
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

					}
				}
						
				if(responseTab==0) {
					if(Main.game.getPlayer().getEquippedMoves().size()>moveIndex) {
						CombatMove move = Main.game.getPlayer().getEquippedMoves().get(moveIndex);
						
						return getMoveResponse(move, pcEnemies, pcAllies);
						
					} else if(index<=8) {
						return new Response("-",
								"This is a free core combat move slot. You can add core combat moves when outside of combat by opening your phone menu and selecting the 'Combat Moves' action.",
								null);
					}
					
				} else if(responseTab==1) {
					if(Main.game.getPlayer().getAvailableBasicMoves().size()>moveIndex) {
						CombatMove move = Main.game.getPlayer().getAvailableBasicMoves().get(moveIndex);
						
						return getMoveResponse(move, pcEnemies, pcAllies);
					}
					
				} else if(responseTab==2) {
					if(Main.game.getPlayer().getAvailableSpecialMoves().size()>moveIndex) {
						CombatMove move = Main.game.getPlayer().getAvailableSpecialMoves().get(moveIndex);
						
						return getMoveResponse(move, pcEnemies, pcAllies);
					}
					
				} else if(responseTab==3) {
					if(Main.game.getPlayer().getAvailableSpellMoves().size()>moveIndex) {
						CombatMove move = Main.game.getPlayer().getAvailableSpellMoves().get(moveIndex);
						
						return getMoveResponse(move, pcEnemies, pcAllies);
					}
					
				} else if(responseTab==4) {
					String costDescription = "<br/>[style.colourMinorGood(This is a free move, and does not cost any AP, nor end your turn.)]";
					//TODO set behaviour and recalculate moves
					
					for(int i=1; i<=CombatBehaviour.values().length; i++) {
						if(index==i) {
							CombatBehaviour behaviour = CombatBehaviour.values()[i-1];
							
							if(targetedAlly.isPlayer()) {
								return new Response(Util.capitaliseSentence(behaviour.getName()), UtilText.parse(targetedAlly, "You cannot issue commands to yourself!"), null);
							}
							return new Response(
									Util.capitaliseSentence(behaviour.getName()),
									(targetedAlly.getCombatBehaviour()==behaviour
										?"[style.italicsMinorGood("+behaviour.getDescription(targetedAlly)+")]"
												+ UtilText.parse(targetedAlly, "<br/>[style.italics(You can select this action again to make [npc.name] recalculate [npc.her] moves.)]")
										:behaviour.getOrderDescription(targetedAlly)+costDescription),
									ENEMY_ATTACK) {
								@Override
								public Colour getHighlightColour() {
									if(targetedAlly.getCombatBehaviour()==behaviour) {
										return PresetColour.GENERIC_GOOD;
									}
									return super.getHighlightColour();
								}
								@Override
								public void effects() {
									targetedAlly.setCombatBehaviour(behaviour);
									
									// Sets up NPC ally/enemy lists that include player
									List<GameCharacter> npcAllies= getAllies(targetedAlly);
									List<GameCharacter> npcEnemies = getEnemies(targetedAlly);
									npcAllies.removeIf((character)->Combat.isCombatantDefeated(character));
									npcEnemies.removeIf((character)->Combat.isCombatantDefeated(character));
									
									// Figures out the new moves
									int i = 0;
									for(Value<GameCharacter, CombatMove> move : targetedAlly.getSelectedMoves()) {
										move.getValue().performOnDeselection(i,
												targetedAlly,
												move.getKey(),
												new ArrayList<>(npcEnemies),
												new ArrayList<>(npcAllies));
										targetedAlly.setCooldown(move.getValue().getIdentifier(), 0);
										i++;
									}
									targetedAlly.resetSelectedMoves();
									targetedAlly.setRemainingAP(targetedAlly.getMaxAP(), npcEnemies, npcAllies);
									targetedAlly.selectMoves(npcEnemies, npcAllies);
									
									predictionContent.put(targetedAlly, targetedAlly.getMovesPredictionString(npcEnemies, npcAllies));
								}
							};
						}
					}
				}
				
			} else if(index==11) {
				return new ResponseEffectsOnly("[style.colourGood(Target:)] "+(Combat.getTargetedAlliedCombatant().isPlayer()?"Yourself":Util.capitaliseSentence(Combat.getTargetedAlliedCombatant().getName())),
						"You can cycle through your targeted allied combatant by either using this action, or clicking on their name on the right-hand side of the screen.") {
					@Override
					public void effects() {
						List<GameCharacter> alliesPlusPlayer = Util.newArrayListOfValues(Main.game.getPlayer());
						alliesPlusPlayer.addAll(Combat.getAllies(Main.game.getPlayer()));
						if(alliesPlusPlayer.size()==1) {
							return;
						}
						for(int i=0; i<alliesPlusPlayer.size(); i++) {
							if(alliesPlusPlayer.get(i).equals(Combat.getTargetedAlliedCombatant())) {
								if(i+1<alliesPlusPlayer.size()) {
									Combat.setTargetedCombatant(alliesPlusPlayer.get(i+1));
									break;
								} else {
									Combat.setTargetedCombatant(alliesPlusPlayer.get(0));
									break;
								}
							}
						}
					}
				};

			} else if(index==12) {
				return new ResponseEffectsOnly("[style.colourBad(Target:)] "+Util.capitaliseSentence(Combat.getTargetedCombatant().getName()),
						"You can cycle through your targeted enemy combatant by either using this action, or clicking on their name on the right-hand side of the screen.") {
					@Override
					public void effects() {
						List<GameCharacter> playerEnemies = Combat.getEnemies(Main.game.getPlayer());
						if(playerEnemies.size()==1) {
							return;
						}
						for(int i=0; i<playerEnemies.size(); i++) {
							if(playerEnemies.get(i).equals(Combat.getTargetedCombatant())) {
								if(i+1<playerEnemies.size()) {
									Combat.setTargetedCombatant(playerEnemies.get(i+1));
									break;
								} else {
									Combat.setTargetedCombatant(playerEnemies.get(0));
									break;
								}
							}
						}
					}
				};

			} else if(index==14) {
				return new Response("Reset",
						Main.game.getPlayer().getSelectedMoves().size()==0
								?""
								:".",
							Main.game.getPlayer().getSelectedMoves().size()==0
								?null
								:ENEMY_ATTACK) {
					@Override
					public void effects() {
						if(Main.game.isInCombat()) {
							int i = 0;
							for(Value<GameCharacter, CombatMove> move : Main.game.getPlayer().getSelectedMoves()) {
								move.getValue().performOnDeselection(i,
										Main.game.getPlayer(),
										move.getKey(),
										new ArrayList<>(enemies),
										new ArrayList<>(allies));
								Main.game.getPlayer().setCooldown(move.getValue().getIdentifier(), 0);
								i++;
							}
						}
						Main.game.getPlayer().resetSelectedMoves();
						Main.game.getPlayer().setRemainingAP(Main.game.getPlayer().getMaxAP(), pcEnemies, pcAllies);
						predictionContent.put(Main.game.getPlayer(), new ArrayList<>());
					}
				};

			}
			
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.NORMAL;
		}
	};
	
	private static Response getMoveResponse(CombatMove move, List<GameCharacter> pcEnemies, List<GameCharacter> pcAllies) {
		GameCharacter moveTarget = move.isCanTargetAllies()||move.isCanTargetSelf()?getTargetedAlliedCombatant():getTargetedCombatant();

		int selectedMoveIndex = Main.game.getPlayer().getSelectedMoves().size();
		
		String rejectionReason = move.isUsable(Main.game.getPlayer(), moveTarget, pcEnemies, pcAllies);
		if(rejectionReason != null) {
			return new Response(Util.capitaliseSentence(move.getName(selectedMoveIndex, Main.game.getPlayer())),
								rejectionReason,
					null);
		}
		StringBuilder moveStatblock = new StringBuilder();
		
		boolean isCrit = move.canCrit(selectedMoveIndex, Main.game.getPlayer(), moveTarget, pcEnemies, pcAllies);
		
		if(move.getStatusEffects(Main.game.getPlayer(), moveTarget, isCrit)!=null && !move.getStatusEffects(Main.game.getPlayer(), moveTarget, isCrit).isEmpty()) {
			for(Entry<AbstractStatusEffect, Integer> entry : move.getStatusEffects(Main.game.getPlayer(), moveTarget, isCrit).entrySet()) {
				moveStatblock.append("Applies <b style='color:"+entry.getKey().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getName(moveTarget))+"</b>"
						+ " for <b>"+entry.getValue()+(entry.getValue()==1?" turn":" turns")+"</b>");
			}
			moveStatblock.append("<br/>");
		}
		
		StringBuilder critText = new StringBuilder();
		critText.append("<br/>[style.colourCrit(Critical Requirement)]:");
		for(String s : move.getCritRequirements(Main.game.getPlayer(), moveTarget, pcEnemies, pcAllies)) {
			critText.append(" "+s);
		}
		
		String predictionTooltip = move.getPrediction(selectedMoveIndex, Main.game.getPlayer(), moveTarget, pcEnemies, pcAllies);
		
		return new Response(Util.capitaliseSentence(move.getName(selectedMoveIndex, Main.game.getPlayer())),
			moveStatblock.toString()
				+ predictionTooltip
				+ critText.toString(),
			ENEMY_ATTACK){
			@Override
			public void effects() {
				Main.game.getPlayer().selectMove(Main.game.getPlayer().getSelectedMoves().size(), move, moveTarget, pcEnemies, pcAllies);
				predictionContent.get(Main.game.getPlayer()).add(move.getPrediction(selectedMoveIndex, Main.game.getPlayer(), moveTarget, pcEnemies, pcAllies));
			}
			@Override
			public Colour getHighlightColour() {
				return move.getColour();
			}
			@Override
			public CombatMove getAssociatedCombatMove() {
				return move;
			}
		};
	}
	
	public static List<String> applyExtraAttackEffects(GameCharacter attacker, GameCharacter target, Attack attackType, AbstractWeapon weapon, boolean isHit, boolean isCritical) {
		List<String> extraAttackEffects = new ArrayList<>();
		
		if(target.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES_3)
				&& (attackType==Attack.MAIN || attackType==Attack.OFFHAND || attackType==Attack.DUAL)
				&& (weapon==null || weapon.getWeaponType().isMelee())) {
			float cloakOfFlamesDamage = Math.round(5 * (1 + (target.getAttributeValue(Attribute.DAMAGE_FIRE)/100f)));
			cloakOfFlamesDamage = (Math.round(cloakOfFlamesDamage*10))/10f;
			if (cloakOfFlamesDamage < 1) {
				cloakOfFlamesDamage = 1;
			}
			attacker.incrementHealth(-cloakOfFlamesDamage);
			
			if(attacker.isPlayer()) {
				extraAttackEffects.add(UtilText.parse(target, "You take <b>"+cloakOfFlamesDamage+"</b> [style.boldFire(Fire Damage)] from [npc.namePos] [style.boldFire(Ring of Fire)]!"));
			} else {
				if(target.isPlayer()) {
					extraAttackEffects.add(UtilText.parse(attacker, "[npc.Name] takes <b>"+cloakOfFlamesDamage+"</b> [style.boldFire(Fire Damage)] from your [style.boldFire(Ring of Fire)]!"));
				} else {
					extraAttackEffects.add(UtilText.parse(attacker, target, "[npc1.Name] takes <b>"+cloakOfFlamesDamage+"</b> [style.boldFire(Fire Damage)] from [npc2.namePos] [style.boldFire(Ring of Fire)]!"));
				}
			}
		}
		
		if(isCritical && target.hasStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST)) {
			target.removeStatusEffect(StatusEffect.RAIN_CLOUD);
			target.removeStatusEffect(StatusEffect.RAIN_CLOUD_CLOUDBURST);
			target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DEEP_CHILL);
			target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR);
			target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST);
			
			Combat.addStatusEffectToApply(target, StatusEffect.RAIN_CLOUD_CLOUDBURST, 6);
			
			extraAttackEffects.add(UtilText.parse(target, "As [npc.name] is critically hit, the rain cloud above [npc.her] head grows in size, and suddenly erupts into a torrential cloudburst!"));
			
			extraAttackEffects.add(Spell.getBasicStatusEffectApplication(target, false, Util.newHashMapOfValues(new Value<>(StatusEffect.RAIN_CLOUD_CLOUDBURST, 6))));
			
		}
		
		if(attacker.isPlayer() && attacker.hasFetish(Fetish.FETISH_SADIST) && isCritical && isHit) {
			extraAttackEffects.add(
							"Thanks to your [style.boldFetish(sadist fetish)], the arousal you feel from critically hitting someone manifests as an arcane essence!<br/>"
							+Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 1, false));
		}
		
		if(target.isPlayer() && target.hasFetish(Fetish.FETISH_MASOCHIST) && isCritical && isHit) {
			extraAttackEffects.add(
							"Thanks to your [style.boldFetish(masochist fetish)], the arousal you feel from getting critically hit manifests as an arcane essence!<br/>"
							+Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 1, false));
		}
		
		return extraAttackEffects;
	}

	private static void escape(GameCharacter attacker) {
		attemptedEscape = true;
		
		boolean allEnemiesStunned = true;
		if(attacker.isPlayer() || getAllies(Main.game.getPlayer()).contains(attacker)) {
			for(GameCharacter enemy : getEnemies(Main.game.getPlayer())) {
				if(!enemy.isStunned()) {
					allEnemiesStunned = false;
				}
			}
		} else {
			if(Main.game.getPlayer().isStunned()) {
				allEnemiesStunned = false;
			}
			for(GameCharacter ally : getAllies(Main.game.getPlayer())) {
				if(ally.isStunned()) {
					allEnemiesStunned = false;
				}
			}
		}
		
		String s = "";
		if(allEnemiesStunned) {
			escaped = true;
			s = ("All of your enemies are stunned, so you're easily able to escape!");
		} else if (Util.random.nextInt(100) < escapeChance) {
			escaped = true;
			s = ("You got away!");
		} else {
			s = ("You failed to escape!");
		}
		
		for(GameCharacter combatant : Combat.getAllCombatants(true)) {
			if(Combat.getAllies(attacker).contains(combatant) || combatant.equals(attacker)) {
				int i = 0;
				for(Value<GameCharacter, CombatMove> move : combatant.getSelectedMoves()) {
					move.getValue().performOnDeselection(i,
							combatant,
							move.getKey(),
							new ArrayList<>(enemies),
							new ArrayList<>(allies));
					combatant.setCooldown(move.getValue().getIdentifier(), 0);
					i++;
				}
				combatant.resetSelectedMoves();
				combatant.setRemainingAP(combatant.getMaxAP(), Combat.getEnemies(combatant), Combat.getAllies(combatant));
				predictionContent.put(combatant, new ArrayList<>());
			}
		}
		
		combatContent.put(attacker, Util.newArrayListOfValues(s));
	}

	/**
	 * Calculations for enemy attack.
	 * @param character The character performing an attack turn.
	 * @return true if the character is able to perform an attack, false if they cannot (due to being defeated, stunned, or attempting to escape).
	 */
	private static boolean attackCharacter(GameCharacter character) {
		if(character.isPlayer()) {
			if(escaped) {
				combatContent.put(character,
						Util.newArrayListOfValues(UtilText.parse(character, "You manage to escape!")));
				return false;
				
			} else if (!activeCombatants.contains(character)) {
				combatContent.put(character,
						Util.newArrayListOfValues(UtilText.parse(character, "<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>[npc.NameHasFull] been defeated!</span>")));
				return false;
				
			} else if(attemptedEscape) {
				combatContent.put(character,
						Util.newArrayListOfValues(UtilText.parse(character, "<span style='color:"+PresetColour.GENERIC_MINOR_BAD.toWebHexString()+";'>You fail to escape!</span>")));
				return false;
				
			}
			
		} else if(allies.contains(character)) {
			if(escaped) {
				combatContent.put(character,
						Util.newArrayListOfValues(UtilText.parse(character, "[npc.Name] manages to escape with you!")));
				return false;
				
			} else if (!activeCombatants.contains(character)) {
				combatContent.put(character,
						Util.newArrayListOfValues(UtilText.parse(character, "<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>[npc.NameHasFull] been defeated!</span>")));
				return false;
				
			} else if(attemptedEscape) {
				combatContent.put(character,
						Util.newArrayListOfValues(UtilText.parse(character, "<span style='color:"+PresetColour.GENERIC_MINOR_BAD.toWebHexString()+";'>[npc.Name] fails to escape with you!</span>")));
				return false;
				
			}
			
		} else {
			if (!activeCombatants.contains(character)) {
				combatContent.put(character,
						Util.newArrayListOfValues(UtilText.parse(character, "<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>[npc.NameHasFull] been defeated!</span>")));
				return false;
				
			} else if(escaped) {
				combatContent.put(character,
						Util.newArrayListOfValues(UtilText.parse(character, "[npc.Name] tries to block your escape, but fails!")));
				return false;
			}
		}
		
		if(character.isStunned()) {
			combatContent.put(character,
					Util.newArrayListOfValues(UtilText.parse(character, "[npc.NameIsFull] stunned, and [npc.is] unable to make a move!")));

			character.resetSelectedMoves();
			return false;
		}
		
		return true;
	}

	private static StringBuilder endTurnStatusEffectText = new StringBuilder();
	
	private static void applyNewTurnShielding(GameCharacter character) {
	    character.resetShields();
	    
	    int bonusEnergyShielding = Math.round(character.getAttributeValue(Attribute.ENERGY_SHIELDING));
		character.incrementShields(DamageType.HEALTH, bonusEnergyShielding);
		
		DamageType[] damageTypes = new DamageType[] {DamageType.PHYSICAL, DamageType.FIRE, DamageType.ICE, DamageType.POISON};
		for(DamageType dt : damageTypes) {
			character.incrementShields(dt, Math.round(character.getAttributeValue(dt.getResistAttribute())));
		}
		
	    character.incrementShields(DamageType.LUST, Math.round(character.getAttributeValue(DamageType.LUST.getResistAttribute())));
	}
	
	public static void endCombatTurn() {
		combatTurnResolutionStringBuilder.setLength(0);
		
		List<GameCharacter> combatants = getAllCombatants(true); // To avoid concurrent modification when the 'summon elemental' spell adds combatants.
		
		// Perform moves based on following ordering, so that all defensive and support abilities are applied before attacks start landing:
		CombatMoveType[] order = new CombatMoveType[] {CombatMoveType.DEFEND, CombatMoveType.SPELL, CombatMoveType.ATTACK};
		for(GameCharacter character : getAllCombatants(true)) {
			combatContent.put(character, new ArrayList<>());
		}
		for(int i=0;i<3;i++) {
			for(GameCharacter character : combatants) {
				if(attackCharacter(character)) {
					List<GameCharacter> npcAllies = getAllies(character);
					List<GameCharacter> npcEnemies = getEnemies(character);
					// Performs the actions
					character.performMoves(order[i], combatContent.get(character), npcEnemies, npcAllies);
				}
			}
			// After all defensive and supportive moves have been made, apply the queued up status effects before the attacks start hitting, as they should only be defensive or supportive-based.
			if(i==2) {
				for(GameCharacter character : combatants) {
					for(Entry<AbstractStatusEffect, Integer> entry : statusEffectsToApply.get(character).entrySet()) {
						character.addStatusEffect(entry.getKey(), entry.getValue()+1);// Add 1 to the status effect duration, as it gets immediately decremented by 1 within the getCharactersTurnDiv() method (as it calls the applyEffects() method).
					}
					statusEffectsToApply.put(character, new HashMap<>());
				}
			}
		}
		for(GameCharacter character : combatants) {
			combatTurnResolutionStringBuilder.append(getCharactersTurnDiv(character, Combat.getTurn()==0?"Preparation":"", combatContent.get(character)));
			character.resetSelectedMoves();
		}
		
		attemptedEscape = false;
		
		// End turn effects:
		for(GameCharacter character : combatants) {
			for(Entry<AbstractStatusEffect, Integer> entry : statusEffectsToApply.get(character).entrySet()) {
				character.addStatusEffect(entry.getKey(), entry.getValue());
			}
			statusEffectsToApply.put(character, new HashMap<>());
			
			List<GameCharacter> npcAllies = getAllies(character);
			List<GameCharacter> npcEnemies = getEnemies(character);
			
			applyNewTurnShielding(character);
			character.lowerMoveCooldowns();
			character.setRemainingAP(character.getMaxAP(), npcEnemies, npcAllies);
			
			if(isCombatantDefeated(character)) {
				if(activeCombatants.remove(character)) {
					List<String> vampyres = new ArrayList<>();
					boolean playerVampyre = false;
					float manaAbsorbed = Math.round(character.getMana()/2);
					for(GameCharacter c2 : combatants) {
						if(!isCombatantDefeated(c2) && c2.hasTraitActivated(Perk.ARCANE_VAMPYRISM)) {
							if(c2.isPlayer()) {
								vampyres.add(0, UtilText.parse(c2,"[npc.name]"));
								playerVampyre = true;
							} else {
								vampyres.add(UtilText.parse(c2,"[npc.name]"));
							}
							c2.incrementMana(manaAbsorbed);
						}
					}
					if(!vampyres.isEmpty()) {
						character.setMana(manaAbsorbed);
	
						predictionContent.put(character, Util.newArrayListOfValues(
										UtilText.parse(character,
												"[style.boldArcane("+(Util.capitaliseSentence(Perk.ARCANE_VAMPYRISM.getName(Main.game.getPlayer())))+":)]<br/>"
														+Util.capitaliseSentence(Util.stringsToStringList(vampyres, false))+(vampyres.size()>1 || playerVampyre?" absorb":" absorbs")
														+" half of [npc.namePos] remaining aura,"
														+ (Combat.enemies.contains(character)
																?"[style.colourGood("
																:"[style.colourBad(")
														+" gaining "+manaAbsorbed+" aura)]!")));
					} else {
						predictionContent.put(character, Util.newArrayListOfValues("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Defeated...</span>"));
					}
					
				} else {
					predictionContent.put(character, Util.newArrayListOfValues("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Defeated...</span>"));
				}
				
			} else if(character.isStunned()) {
				predictionContent.put(character, Util.newArrayListOfValues("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Stunned!</span>"));
				
			} else {
				if(character.isPlayer()) {
					predictionContent.put(character, new ArrayList<>());
					
				} else {
					npcAllies.removeIf((c)->Combat.isCombatantDefeated(c));
					npcEnemies.removeIf((c)->Combat.isCombatantDefeated(c));
					
					// Figures out new moves for NPCs:
					character.selectMoves(npcEnemies, npcAllies);
					predictionContent.put(character, character.getMovesPredictionString(npcEnemies, npcAllies));
				}
			}
		}
		
		if(isCombatantDefeated(targetedEnemy)) {
			for(NPC enemy : enemies) {
				if(!isCombatantDefeated(enemy)) {
					targetedEnemy = enemy;
					break;
				}
			}
		}
		
		if(isCombatantDefeated(targetedAlly)) {
			targetedAlly = Main.game.getPlayer();
		}
		
		turn++;
	}

	private static String getShieldsDisplayValue(Attribute att, int shields) {
		String valueForDisplay = String.valueOf(shields);
		if(att.isInfiniteAtUpperLimit() && shields>=att.getUpperLimit()) {
			valueForDisplay = UtilText.getInfinitySymbol(false);
		}
		return valueForDisplay;
	}
	
	private static String getTitleResources(GameCharacter character) {
		int apRemaining = character.getRemainingAP();
		StringBuilder sb = new StringBuilder();
		
		sb.append(" <b>(<span style='color:"+(apRemaining==0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD).toWebHexString()+";'>"+apRemaining+"</span>/"+character.getMaxAP()+" AP)</b>");
		
		sb.append("<div class='container-full-width' style='text-align:center;'>");
		
		boolean shieldsFound = false;
		int shields = character.getShields(DamageType.HEALTH);
		if(shields!=0) {
			shieldsFound = true;
			sb.append("<div style='display:inline-block; float:none; margin:auto; padding:0 2px; background-color:"+PresetColour.BACKGROUND.toWebHexString()+"; border-radius:5px; width:auto; position:relative;'>"
							+"<span style='color:"+DamageType.HEALTH.getColour().toWebHexString()+";'>"+UtilText.getShieldSymbol()+"</span> "+(shields<0?"[style.colourDisabled("+shields+")]":getShieldsDisplayValue(Attribute.ENERGY_SHIELDING, shields))
							+ "<div class='overlay' id='"+character.getId()+"_COMBAT_SHIELD_"+DamageType.HEALTH+"' style='cursor:default;'></div>"
						+ "</div>");
		}

		DamageType[] damageTypes = new DamageType[] {DamageType.PHYSICAL, DamageType.FIRE, DamageType.ICE, DamageType.POISON};
		for(DamageType dt : damageTypes) {
			shields = character.getShields(dt);
			if(shields!=0) {
				if(shieldsFound) {
					sb.append(" | ");
				}
				shieldsFound = true;
				sb.append(
						"<div style='display:inline-block; float:none; margin:auto; padding:0 2px; background-color:"+PresetColour.BACKGROUND.toWebHexString()+"; border-radius:5px; width:auto; position:relative;'>"
							+"<span style='color:"+dt.getColour().toWebHexString()+";'>"+UtilText.getShieldSymbol()+"</span> "+(shields<0?"[style.colourDisabled("+shields+")]":getShieldsDisplayValue(dt.getResistAttribute(), shields))
							+ "<div class='overlay' id='"+character.getId()+"_COMBAT_SHIELD_"+dt+"' style='cursor:default;'></div>"
						+ "</div>");
			}
		}

		shields = character.getShields(DamageType.LUST);
		if(shields!=0) {
			if(shieldsFound) {
				sb.append(" | ");
			}
			shieldsFound = true;
			sb.append(
					"<div style='display:inline-block; float:none; margin:auto; padding:0 2px; background-color:"+PresetColour.BACKGROUND.toWebHexString()+"; border-radius:5px; width:auto; position:relative;'>"
						+"<span style='color:"+DamageType.LUST.getColour().toWebHexString()+";'>"+UtilText.getShieldSymbol()+"</span> "+(shields<0?"[style.colourDisabled("+shields+")]":getShieldsDisplayValue(DamageType.LUST.getResistAttribute(), shields))
						+ "<div class='overlay' id='"+character.getId()+"_COMBAT_SHIELD_"+DamageType.LUST+"' style='cursor:default;'></div>"
					+ "</div>");
		}

		sb.append("</div>");
		
		return sb.toString();
	}
	
	private static String getCombatContent() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='text-align:center; box-sizing: border-box; border:6px solid "+PresetColour.BASE_WHITE.toWebHexString()+"; border-radius:5px;'>");

			sb.append("<div class='container-full-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; text-align:center;'><b>Planning</b></div>");
		
			sb.append("<div class='container-half-width'>");
				
				sb.append("[style.boldGood(You)]"+ getTitleResources(Main.game.getPlayer()));
				if(predictionContent.get(Main.game.getPlayer()).isEmpty()) {
					sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+PresetColour.BACKGROUND.toWebHexString()+";'>[style.colourDisabled(No moves selected...)]</div>");
				} else {
					for(String s : predictionContent.get(Main.game.getPlayer())) {
						sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+PresetColour.BACKGROUND.toWebHexString()+";'>"+s+"</div>");
					}
				}
				sb.append("</br>");
			
				for(GameCharacter ally : Combat.getAllies(Main.game.getPlayer())) {
					sb.append(UtilText.parse(ally, "</br>[style.boldMinorGood([npc.Name])]")+ getTitleResources(ally));
					for(String s : predictionContent.get(ally)) {
						sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+PresetColour.BACKGROUND.toWebHexString()+";'>"+s+"</div>");
					}
					sb.append("</br>");
				}
			sb.append("</div>");

			sb.append("<div class='container-half-width'>");
			for(GameCharacter enemy : Combat.getEnemies(Main.game.getPlayer())) {
				sb.append(UtilText.parse(enemy, (Combat.enemyLeader.equals(enemy)?"[style.boldBad([npc.Name])]":"</br>[style.boldMinorBad([npc.Name])]"))+ getTitleResources(enemy));
				for(String s : predictionContent.get(enemy)) {
					sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+PresetColour.BACKGROUND.toWebHexString()+";'>"+s+"</div>");
				}
				sb.append("</br>");
			}
			sb.append("</div>");
			
		sb.append("</div>");

		sb.append(combatTurnResolutionStringBuilder.toString());
		
		return sb.toString(); 
	}
	
	private static String applyEffects(GameCharacter character) {
		endTurnStatusEffectText = new StringBuilder();
		List<AbstractStatusEffect> effectsToRemove = new ArrayList<>();
		for (AppliedStatusEffect appliedSe : character.getAppliedStatusEffects()) {
			AbstractStatusEffect se = appliedSe.getEffect();
			if (se.isCombatEffect()) {
				appliedSe.setSecondsPassed(turn);
				StringBuilder s = new StringBuilder();
				if(appliedSe.getEffect().getEffectInterval()<=0 || ((turn-appliedSe.getLastTimeAppliedEffect())>appliedSe.getEffect().getEffectInterval())) {
					if(appliedSe.getEffect().getEffectInterval()<=0) {
						s.append(se.applyEffect(character, 1, appliedSe.getSecondsPassed()));
					} else {
						for(int i=0; i<((Main.game.getSecondsPassed()-appliedSe.getLastTimeAppliedEffect())/appliedSe.getEffect().getEffectInterval()); i++) {
							if(s.length()>0) {
								s.append("<br/>");
							}
							s.append(se.applyEffect(character, 1, appliedSe.getSecondsPassed()));
						}
					}
					
					appliedSe.setLastTimeAppliedEffect(Main.game.getSecondsPassed());
					if(s.length()!=0) {
						endTurnStatusEffectText.append("<p><b style='color: " + se.getColour().toWebHexString() + "'>" + Util.capitaliseSentence(se.getName(character)) + ":</b> " + s.toString()+ "</p>");
					}
				}
				
				
//				String effectString = se.applyEffect(character, 1, appliedSe.getSecondsPassed());
//				if(!effectString.isEmpty()) {
//					endTurnStatusEffectText.append("<p><b style='color: " + se.getColour().toWebHexString() + "'>" + Util.capitaliseSentence(se.getName(character)) + ":</b> " + effectString+ "</p>");
//				}
				character.setCombatStatusEffectDuration(se, character.getStatusEffectDuration(se) - 1);
				if (character.getStatusEffectDuration(se) == 0) { // Do not remove special effects (i.e. ones set at -1 duration)
					effectsToRemove.add(se);
				}
			}
		}
		for (AbstractStatusEffect se : effectsToRemove) {
			endTurnStatusEffectText.append(character.removeStatusEffectCombat(se));
		}
		return endTurnStatusEffectText.toString();
	}

	// Utility methods:
	private static String getCombatLabel() {
		if(turn==0) {
			return "Combat - Start";
		} else {
			return "Combat - Turn "+turn;
		}
	}
	
	/**
	 * @return The enemy NPC which the player is targeting. Use COmbatMove's getPreferredTarget for NPC targeting.
	 */
	public static GameCharacter getTargetedCombatant() {
		return targetedEnemy;
	}

	/**
	 * @return The allied NPC which the player is targeting. Use COmbatMove's getPreferredTarget for NPC targeting.
	 */
	public static GameCharacter getTargetedAlliedCombatant() {
		return targetedAlly;
	}

	public static void setTargetedCombatant(GameCharacter targetedCombatant) {
		if(Combat.getEnemies(Main.game.getPlayer()).contains(targetedCombatant)) {
			Combat.targetedEnemy = targetedCombatant;
		} else {
			Combat.targetedAlly = targetedCombatant;
		}
	}
	
	public static NPC getActiveNPC() {
		return activeNPC;
	}

	public static void setActiveNPC(NPC activeNPC) {
		Combat.activeNPC = activeNPC;
	}

	public static void resetItemsToBeUsed(GameCharacter character) {
		itemsToBeUsed.put(character, new ArrayList<>());
	}
	
	public static List<Value<GameCharacter, AbstractItem>> getItemsToBeUsed(GameCharacter user) {
		return itemsToBeUsed.get(user);
	}

	public static void addItemToBeUsed(GameCharacter user, GameCharacter target, AbstractItem item) {
		itemsToBeUsed.get(user).add(new Value<>(target, item));
		//TODO test combatmove
		predictionContent.get(Main.game.getPlayer()).add(CombatMove.ITEM_USAGE.getPrediction(user.getSelectedMoves().size(), user, target, getEnemies(user), getAllies(user)));
		Main.game.getPlayer().selectMove(user.getSelectedMoves().size(), CombatMove.ITEM_USAGE, target, getEnemies(user), getAllies(user));
	}
	
	public static String getPregnancyProtectionText(GameCharacter character) {
			return (character.isVisiblyPregnant()
					?UtilText.parse(character, "A powerful field of arcane energy is protecting [npc.namePos] pregnant belly, ensuring that no harm can come to [npc.her] unborn offspring.")
					:"");
	}

	public static List<GameCharacter> getAllCombatants(boolean includePlayer) {
		List<GameCharacter> returnList = new ArrayList<>(allCombatants);
		if(includePlayer) {
			returnList.add(Main.game.getPlayer());
		}
		return returnList;
	}
	
	public static void addAlly(NPC ally) {
		allies.add(ally);
		allCombatants.add(ally);
		ally.resetMoveCooldowns();
		
		predictionContent.put(ally, new ArrayList<>());
		itemsToBeUsed.put(ally, new ArrayList<>());
		manaBurnStack.put(ally, new Stack<>());
		statusEffectsToApply.put(ally, new HashMap<>());
		combatContent.put(ally, new ArrayList<>());
		activeCombatants.add(ally);
		
		if(Main.game.isInCombat()) {
			List<GameCharacter> npcAllies = getAllies(ally);
			List<GameCharacter> npcEnemies = getEnemies(ally);
			
			applyNewTurnShielding(ally);
			ally.setRemainingAP(ally.getMaxAP(), npcEnemies, npcAllies);
			
			npcAllies.removeIf((c)->Combat.isCombatantDefeated(c));
			npcEnemies.removeIf((c)->Combat.isCombatantDefeated(c));
			
			// Figures out new moves for NPCs:
			ally.selectMoves(npcEnemies, npcAllies);
			predictionContent.put(ally, ally.getMovesPredictionString(npcEnemies, npcAllies));
		}
	}
	
	public static void addEnemy(NPC enemy) {
		enemies.add(enemy);
		allCombatants.add(enemy);
		enemy.resetMoveCooldowns();
		enemy.setFoughtPlayerCount(enemy.getFoughtPlayerCount()+1);
		
		predictionContent.put(enemy, new ArrayList<>());
		itemsToBeUsed.put(enemy, new ArrayList<>());
		manaBurnStack.put(enemy, new Stack<>());
		statusEffectsToApply.put(enemy, new HashMap<>());
		combatContent.put(enemy, new ArrayList<>());
		activeCombatants.add(enemy);

		if(Main.game.isInCombat()) {
			List<GameCharacter> npcAllies = getAllies(enemy);
			List<GameCharacter> npcEnemies = getEnemies(enemy);
			
			applyNewTurnShielding(enemy);
			enemy.setRemainingAP(enemy.getMaxAP(), npcEnemies, npcAllies);
			
			npcAllies.removeIf((c)->Combat.isCombatantDefeated(c));
			npcEnemies.removeIf((c)->Combat.isCombatantDefeated(c));
			
			// Figures out new moves for NPCs:
			enemy.selectMoves(npcEnemies, npcAllies);
			predictionContent.put(enemy, enemy.getMovesPredictionString(npcEnemies, npcAllies));
		}
	}
	
	/**
	 * @return A list of this combatant's allies. <b>Does not include</b> the combatant themselves.
	 */
	public static List<GameCharacter> getAllies(GameCharacter combatant) {
		List<GameCharacter> returnList = new ArrayList<>();
		
		if(combatant.isPlayer()) {
			returnList.addAll(allies);
			
		} else if(allies.contains(combatant)) {
			returnList.add(Main.game.getPlayer());
			returnList.addAll(allies);
			
		} else {
			returnList.addAll(enemies);
		}
		
		returnList.remove(combatant);
		
		return returnList;
	}

	public static List<GameCharacter> getEnemies(GameCharacter combatant) {
		List<GameCharacter> returnList = new ArrayList<>();
		
		if(combatant.isPlayer()) {
			returnList.addAll(enemies);
			
		} else if(allies.contains(combatant)) {
			returnList.addAll(enemies);
			
		} else {
			returnList.add(Main.game.getPlayer());
			returnList.addAll(allies);
		}
		
		return returnList;
	}

	/**
	 * @param target The character whose party member will be returned.
	 * @return A random member of the target's party. WIll attempt to return a member that isn't the target, but if the target's party only contains them, will return the target. 
	 */
	public static GameCharacter getRandomAlliedPartyMember(GameCharacter target) {
		List<GameCharacter> possibleTargets = new ArrayList<>();
		for(GameCharacter character : getAllies(target)) {
			possibleTargets.add(character);
		}
		if(possibleTargets.size() == 0) {
			return target;
		}
		return possibleTargets.get(Util.random.nextInt(possibleTargets.size()));
	}

	public static int getTurn() {
		return turn;
	}

	public static float getTotalDamageTaken(GameCharacter character) {
		totalDamageTaken.putIfAbsent(character, 0f);
		return totalDamageTaken.get(character);
	}

	public static void setTotalDamageTaken(GameCharacter character, float damage) {
		totalDamageTaken.put(character, damage);
	}

	public static void incrementTotalDamageTaken(GameCharacter character, float increment) {
		setTotalDamageTaken(character, getTotalDamageTaken(character) + increment);
	}

	public static boolean isCharacterVictory(GameCharacter character) {
		if(getEnemies(character).contains(Main.game.getPlayer())) {
			return !playerVictory;
		}
		return playerVictory;
	}
	
	/**
	 * @return true if the last combat that took place resulted in the player's victory.
	 */
	public static boolean isPlayerVictory() {
		return playerVictory;
	}

	public static void setupManaBurnStackForOutOfCombat(GameCharacter character) {
		manaBurnStack = new HashMap<>();
		manaBurnStack.put(character, new Stack<>());
	}
	
	public static Map<GameCharacter, Stack<Float>> getManaBurnStack() {
		return manaBurnStack;
	}
	
	public static void addStatusEffectToApply(GameCharacter target, AbstractStatusEffect effect, int duration) {
		statusEffectsToApply.get(target).put(effect, duration);
//		statusEffectsToApply.get(target).putIfAbsent(effect, 0);
//		
//		statusEffectsToApply.get(target).put(effect, statusEffectsToApply.get(target).get(effect)+duration);
	}

	public static Map<GameCharacter, Map<AbstractStatusEffect, Integer>> getStatusEffectsToApply() {
		return statusEffectsToApply;
	}
}
