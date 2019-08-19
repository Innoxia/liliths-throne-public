package com.lilithsthrone.game.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
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
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * Singleton enforced by Enum.<br/>
 * Call initialiseCombat() before using.
 *
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia, Irbynx
 */
public enum Combat {
	COMBAT;

	// TODO Make sure your status effects end before you take your turn, enemy's status effects end at the start of their turn
	// Also, end combat it enemy drops to 0 health/mana/stamina on their turn from combat effects

	private static NPC activeNPC;
	private static GameCharacter targetedAlly;
	private static GameCharacter targetedEnemy;
	private static List<NPC> allies = new ArrayList<>();
	private static NPC enemyLeader;
	private static List<NPC> enemies = new ArrayList<>();
	private static List<NPC> allCombatants;
	private static float escapeChance = 0;
	private static Map<GameCharacter, Float> totalDamageTaken;
	private static int turn = 0;
	private static boolean attemptedEscape = false;
	private static boolean escaped = false;
	private static boolean playerVictory = false;
	private static StringBuilder postCombatStringBuilder = new StringBuilder();
	
	private static StringBuilder combatTurnResolutionStringBuilder = new StringBuilder();
	
	private static Map<GameCharacter, Stack<Float>> manaBurnStack;
	
	private static Map<GameCharacter, List<String>> combatContent;
	private static Map<GameCharacter, List<String>> predictionContent;
	
	private static Colour[] actionPointColours = new Colour[] {Colour.GENERIC_EXCELLENT, Colour.GENERIC_GOOD, Colour.GENERIC_MINOR_GOOD, Colour.GENERIC_MINOR_BAD, Colour.GENERIC_BAD, Colour.GENERIC_TERRIBLE};

	private static AbstractItem item;

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
		
		if(allies!=null){
			for(NPC ally : allies) {
				Combat.addAlly(ally);
			}
		}
		for(NPC enemy : enemies) {
			Combat.addEnemy(enemy);
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
		
		combatContent = new HashMap<>();
		predictionContent = new HashMap<>();
		
		combatContent.put(Main.game.getPlayer(), new ArrayList<>());
		predictionContent.put(Main.game.getPlayer(), new ArrayList<>());
		
		manaBurnStack = new HashMap<>();
		for(GameCharacter character :Combat.getAllCombatants(true)) {
			manaBurnStack.put(character, new Stack<>());
		}
		
		for(GameCharacter character : Combat.allCombatants) {
			combatContent.put(character, new ArrayList<>());
			predictionContent.put(character, new ArrayList<>());
		}
		

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
		
		if(Main.game.getPlayer().getEquippedMoves().isEmpty()) {
			Main.game.getPlayer().equipBasicCombatMoves();
		}
		
		Main.game.getPlayer().resetSelectedMoves();
		Main.game.getPlayer().resetMoveCooldowns();
		applyNewTurnShielding(Main.game.getPlayer());
		Main.game.getPlayer().setRemainingAP(Main.game.getPlayer().getMaxAP(), null, null);
		

		combatTurnResolutionStringBuilder.append(getCharactersTurnDiv(Main.game.getPlayer(), Combat.getTurn()==0?"Preparation":"", combatContent.get(Main.game.getPlayer())));
		
		for(NPC npc : allCombatants) {
			combatTurnResolutionStringBuilder.append(getCharactersTurnDiv(npc, Combat.getTurn()==0?"Preparation":"", combatContent.get(npc)));
			
			npc.resetSelectedMoves();
			npc.resetDefaultMoves(); // Resetting in case the save file was too old and NPC has no moves selected for them.
			npc.resetMoveCooldowns();
			applyNewTurnShielding(npc);
			npc.setRemainingAP(npc.getMaxAP(), null, null);
			// Sets up NPC ally/enemy lists that include player
			List<GameCharacter> npcAllies;
			List<GameCharacter> npcEnemies;

			if(allies.contains(npc)) {
				npcAllies = new ArrayList<>(allies);
				npcAllies.add(Main.game.getPlayer());
				npcEnemies = new ArrayList<>(enemies);
				
			} else {
				npcEnemies = new ArrayList<>(allies);
				npcEnemies.add(Main.game.getPlayer());
				npcAllies = new ArrayList<>(enemies);
			}
			
			// Selects the moves
			npc.selectMoves(npcEnemies, npcAllies);
			predictionContent.put(npc, npc.getMovesPredictionString(npcEnemies, npcAllies));
		}
		
		Main.game.setInCombat(true);
		
		Main.mainController.openInventory();
	}

	public static void setCharacterTurnContent(GameCharacter character, List<String> descriptions) {
		combatContent.put(character, descriptions);
	}
	
	private static String getCharactersTurnDiv(GameCharacter character, String title, List<String> descriptions) {
		String effects = Combat.applyEffects(character);
		StringBuilder sb = new StringBuilder();
		
		boolean enemy = enemies.contains(character);
		
		sb.append("<div class='container-full-width' style='text-align:center; box-sizing: border-box; border:6px solid "+(enemy?Colour.GENERIC_MINOR_BAD:Colour.GENERIC_MINOR_GOOD).getShades()[0]+"; border-radius:5px;'>");

		sb.append(
				"<div class='container-full-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; text-align:center;'>"
					+ "<b style='color:"+character.getFemininity().getColour().toWebHexString()+";'>"
						+ UtilText.parse(character, "[npc.Name]")
					+ "</b>"
				+ "</div>");
		
//			if(!enemies.contains(character)) {
//				sb.append(UtilText.parse(character,"<div class='container-quarter-width'style='width: calc(20% - 16px);'><b style='color:"+character.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b><br/>"+title+"</div>"));
//			}
			
//			sb.append("<div class='container-half-width' style='width: calc(80% - 16px);'>");
			sb.append("<div class='container-full-width'>");
				for(String s : descriptions) {
					if(!s.isEmpty()) {
						sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+Colour.BACKGROUND.toWebHexString()+";'>"+s+"</div>");
					}
				}
				if(!effects.isEmpty()) {
					sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+Colour.BACKGROUND.toWebHexString()+";'>"+effects+"</div>");
				}
			sb.append("</div>");
				
//			if(enemies.contains(character)) {
//				sb.append(UtilText.parse(character,"<div class='container-quarter-width'style='width: calc(20% - 16px);'><b style='color:"+character.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b><br/>"+title+"</div>"));
//			}
		sb.append("</div>");
		
		return sb.toString();
	}
	
	/**
	 * Ends combat, removing status effects and handling post-combat experience
	 * gains and loot drops.
	 * 
	 * @param playerVictory
	 */
	public static void endCombat(boolean playerVictory) {
		
		postCombatStringBuilder.setLength(0);
		
		Combat.playerVictory = playerVictory;
		
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
			int moneyLoss = (-enemyLeader.getLootMoney()/2)*enemies.size();
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
//		for(NPC ally : allies) {
//			ally.setMana(ally.getAttributeValue(Attribute.MANA_MAXIMUM));
//			ally.setHealth(ally.getAttributeValue(Attribute.HEALTH_MAXIMUM));
//		}

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
					return new ResponseEffectsOnly("Victory", "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>You are victorious!</span>"){
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
							attackNPC(enemyLeader);
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
								"Submit to [npc.name]. <span style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>This will cause you to lose the current combat!</span>"),
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
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
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
				
			} else if (escaped) {
				if (index == 1) {
					return new ResponseEffectsOnly("Escaped!", "You got away!"){
						@Override
						public void effects() {
							enemyLeader.applyEscapeCombatEffects();
							Main.game.setInCombat(false);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogueNoEncounter()));
						}
					};
				} else {
					return null;
				}
				
			} else if (isEnemyPartyDefeated()) {
				if (index == 1) {
					return new ResponseEffectsOnly("Victory", UtilText.parse(enemyLeader, "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>You have defeated [npc.name]!</span>")){
						@Override
						public void effects() {
							endCombat(true);
							Main.game.setContent(enemyLeader.endCombat(true, true));
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

			List<GameCharacter> pcEnemies = new ArrayList<>(enemies);
			List<GameCharacter> pcAllies = new ArrayList<>(allies);
			pcAllies.add(Main.game.getPlayer());
			int moveIndex = index==0?14:(index<15?index-1:index);

			if(Main.game.getPlayer().getEquippedMoves().size()>moveIndex) {
				CombatMove move = Main.game.getPlayer().getEquippedMoves().get(moveIndex);
				
				GameCharacter moveTarget = move.isCanTargetAllies()||move.isCanTargetSelf()?getTargetedAlliedCombatant(Main.game.getPlayer()):getTargetedCombatant(Main.game.getPlayer());
				
				String rejectionReason = move.isUseable(Main.game.getPlayer(), moveTarget, pcEnemies, pcAllies);
				if(rejectionReason != null) {
					return new Response(Util.capitaliseSentence(move.getName(Main.game.getPlayer())),
										rejectionReason,
							null);
				}
				StringBuilder moveStatblock = new StringBuilder();
				
				int apCost = move.getAPcost();
				moveStatblock.append("AP: <span style='color:"+(actionPointColours[apCost]).toWebHexString()+";'>"+apCost+"</span> <b>|</b> ");
				
				int cooldown = move.getCooldown();
				moveStatblock.append("Cooldown: <span style='color:"+(cooldown==0?Colour.GENERIC_MINOR_GOOD:Colour.GENERIC_MINOR_BAD).toWebHexString()+";'>"+cooldown+"</span>");

				if(move.getStatusEffects()!=null) {
					for(Entry<StatusEffect, Integer> entry : move.getStatusEffects().entrySet()) {
						moveStatblock.append(" <b>|</b> Applies <b style='color:"+entry.getKey().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getName(moveTarget))+"</b>"
								+ " for <b>"+entry.getValue()+(entry.getValue()==1?" turn":" turns")+"</b>");
					}
				}
				moveStatblock.append("<br/>");
				
				StringBuilder critText = new StringBuilder();
				critText.append("<br/>[style.colourCrit(Critical Requirement)]:");
				for(String s : move.getCritRequirements(Main.game.getPlayer(), moveTarget, pcEnemies, pcAllies)) {
					critText.append(" "+s);
				}
				int selectedMoveIndex = Main.game.getPlayer().getSelectedMoves().size();
				
				String predictionTooltip = move.getPrediction(selectedMoveIndex, Main.game.getPlayer(), moveTarget, pcEnemies, pcAllies);
				
				return new Response(Util.capitaliseSentence(move.getName(Main.game.getPlayer())),
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
				};

			} else if(index == 0) {
				return new Response("End Turn",
						Main.game.getPlayer().getRemainingAP()<=0?"Ends your current turn":"Ends your current turn. You still have unspent AP!",
						ENEMY_ATTACK){
					@Override
					public void effects() {
						combatContent.put(Main.game.getPlayer(), Main.game.getPlayer().performMoves(pcEnemies, pcAllies));
						endCombatTurn();
					}
					@Override
					public Colour getHighlightColour() {
						if(Main.game.getPlayer().getRemainingAP() > 0) {
							return Colour.GENERIC_BAD;
						} else {
							return Colour.GENERIC_GOOD;
						}
					}
				};
				
			} else if(index<=8) {
				return new Response("-",
						"This is a free combat move slot. You can add combat moves when outside of combat by opening your phone menu and selecting the 'Combat Moves' action.",
						null);
				
			} else if(index==9) {
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

			} else if(index==11) {
				return new ResponseEffectsOnly("[style.colourGood(Target:)] "+(Combat.getTargetedAlliedCombatant(Main.game.getPlayer()).isPlayer()?"Yourself":Util.capitaliseSentence(Combat.getTargetedAlliedCombatant(Main.game.getPlayer()).getName())),
						"You can cycle through your targeted allied combatant by either using this action, or clicking on their name on the right-hand side of the screen.") {
					@Override
					public void effects() {
						List<GameCharacter> alliesPlusPlayer = Util.newArrayListOfValues(Main.game.getPlayer());
						alliesPlusPlayer.addAll(Combat.getAllies(Main.game.getPlayer()));
						if(alliesPlusPlayer.size()==1) {
							return;
						}
						for(int i=0; i<alliesPlusPlayer.size(); i++) {
							if(alliesPlusPlayer.get(i).equals(Combat.getTargetedAlliedCombatant(Main.game.getPlayer()))) {
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
				return new ResponseEffectsOnly("[style.colourBad(Target:)] "+Util.capitaliseSentence(Combat.getTargetedCombatant(Main.game.getPlayer()).getName()),
						"You can cycle through your targeted enemy combatant by either using this action, or clicking on their name on the right-hand side of the screen.") {
					@Override
					public void effects() {
						List<GameCharacter> playerEnemies = Combat.getEnemies(Main.game.getPlayer());
						if(playerEnemies.size()==1) {
							return;
						}
						for(int i=0; i<playerEnemies.size(); i++) {
							if(playerEnemies.get(i).equals(Combat.getTargetedCombatant(Main.game.getPlayer()))) {
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

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.NORMAL;
		}
	};
	
	
	public static List<String> applyExtraAttackEffects(GameCharacter attacker, GameCharacter target, Attack attackType, boolean isHit, boolean isCritical) {
		List<String> extraAttackEffects = new ArrayList<>();
		
		if(target.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES_3)
				&& (((attackType==Attack.MAIN || attackType==Attack.DUAL) && (attacker.getMainWeapon() == null || attacker.getMainWeapon().getWeaponType().isMelee()))
						|| ((attackType==Attack.OFFHAND || attackType==Attack.DUAL) && (attacker.getOffhandWeapon() == null || attacker.getOffhandWeapon().getWeaponType().isMelee())))) {
			float cloakOfFlamesDamage = Math.round(5 * (1 + (Util.getModifiedDropoffValue(target.getAttributeValue(Attribute.DAMAGE_FIRE), 100)/100f)));
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
			
			target.addStatusEffect(StatusEffect.RAIN_CLOUD_CLOUDBURST, 6);
			
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

	// Calculations for enemy attack:
	private static void attackNPC(NPC npc) {
		if(allies.contains(npc)) {
			if(escaped) {
				combatContent.put(npc,
						Util.newArrayListOfValues(UtilText.parse(npc, "[npc.Name] manages to escape with you!")));
				return;
				
			} else if (isCombatantDefeated(npc)) {
				combatContent.put(npc,
						Util.newArrayListOfValues(UtilText.parse(npc, "<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>[npc.Name] has been defeated!</span>")));
				return;
				
			} else if(attemptedEscape) {
				combatContent.put(npc,
						Util.newArrayListOfValues(UtilText.parse(npc, "<span style='color:"+Colour.GENERIC_MINOR_BAD.toWebHexString()+";'>[npc.Name] fails to escape with you!</span>")));
				return;
				
			}
			
		} else {
			if (isCombatantDefeated(npc)) {
				combatContent.put(npc,
						Util.newArrayListOfValues(UtilText.parse(npc, "<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>[npc.Name] has been defeated!</span>")));
				return;
				
			} else if(escaped) {
				combatContent.put(npc,
						Util.newArrayListOfValues(UtilText.parse(npc, "[npc.Name] tries to block your escape, but fails!")));
				return;
			}
		}
		
		if(npc.isStunned()) {
			combatContent.put(npc,
					Util.newArrayListOfValues(UtilText.parse(npc, "[npc.Name] is stunned, and is unable to make a move!")));
			
		} else {
			// Sets up NPC ally/enemy lists that include player
			List<GameCharacter> npcAllies;
			List<GameCharacter> npcEnemies;
			if(allies.contains(npc)) {
				npcAllies = new ArrayList<>(allies);
				npcAllies.add(Main.game.getPlayer());
				npcEnemies = new ArrayList<>(enemies);
			}
			else {
				npcEnemies = new ArrayList<>(allies);
				npcEnemies.add(Main.game.getPlayer());
				npcAllies = new ArrayList<>(enemies);
			}
			// Performs the actions
			combatContent.put(npc, npc.performMoves(npcEnemies, npcAllies));
			
			npcAllies.removeIf((character)->Combat.isCombatantDefeated(character));
			npcEnemies.removeIf((character)->Combat.isCombatantDefeated(character));
			
			// Figures out the new ones
			npc.selectMoves(npcEnemies, npcAllies);
			predictionContent.put(npc, npc.getMovesPredictionString(npcEnemies, npcAllies));
		}
		
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
		combatTurnResolutionStringBuilder.append(getCharactersTurnDiv(Main.game.getPlayer(), Combat.getTurn()==0?"Preparation":"", combatContent.get(Main.game.getPlayer())));
		
		List<NPC> combatants = new ArrayList<>(allCombatants); // To avoid concurrent modification when the 'summon elemental' spell adds combatants.
		for(NPC character : combatants) {
			// Sets up NPC ally/enemy lists that include player
			List<GameCharacter> npcAllies;
			List<GameCharacter> npcEnemies;
			if(allies.contains(character)) {
				npcAllies = new ArrayList<>(allies);
				npcAllies.add(Main.game.getPlayer());
				npcEnemies = new ArrayList<>(enemies);
				
			} else {
				npcEnemies = new ArrayList<>(allies);
				npcEnemies.add(Main.game.getPlayer());
				npcAllies = new ArrayList<>(enemies);
			}

			applyNewTurnShielding(character);
			character.lowerMoveCooldowns();
			character.setRemainingAP(character.getMaxAP(), npcEnemies, npcAllies);
			attackNPC(character);
			
			combatTurnResolutionStringBuilder.append(getCharactersTurnDiv(character, Combat.getTurn()==0?"Preparation":"", combatContent.get(character)));
		}
		attemptedEscape = false;
		
		// Player end turn effects:
//		removeBeneficialEffects(Main.game.getPlayer());
		/*for(SpecialAttack sa : Combat.getCooldowns(Main.game.getPlayer()).keySet()) {
			Combat.incrementCooldown(Main.game.getPlayer(), sa, -1);
		}*/
		List<GameCharacter> pcAllies = new ArrayList<>(allies);
		pcAllies.add(Main.game.getPlayer());
		List<GameCharacter> pcEnemies = new ArrayList<>(enemies);
		applyNewTurnShielding(Main.game.getPlayer());
		Main.game.getPlayer().lowerMoveCooldowns();
		Main.game.getPlayer().setRemainingAP(Main.game.getPlayer().getMaxAP(), pcEnemies, pcAllies);
		
		predictionContent.put(Main.game.getPlayer(), new ArrayList<>());
		
		// NPC end turn effects:
		for(NPC character : allCombatants) {
			character.lowerMoveCooldowns();
			if(isCombatantDefeated(character)) {
				predictionContent.put(character, Util.newArrayListOfValues("<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Defeated...</span>"));
			}
//			removeBeneficialEffects(character);
			/*for(SpecialAttack sa : Combat.getCooldowns(character).keySet()) {
				Combat.incrementCooldown(character, sa, -1);
			}*/
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

	private static String getTitleResources(GameCharacter character) {
		int apRemaining = character.getRemainingAP();
		StringBuilder sb = new StringBuilder();
		
		sb.append(" <b>(<span style='color:"+(apRemaining==0?Colour.GENERIC_GOOD:Colour.GENERIC_BAD).toWebHexString()+";'>"+apRemaining+"</span>/"+Main.game.getPlayer().getMaxAP()+" AP)</b>");
		
		sb.append("<div class='container-full-width' style='text-align:center;'>");
		
		boolean shieldsFound = false;
		int shields = character.getShields(DamageType.HEALTH);
		if(shields!=0) {
			shieldsFound = true;
			sb.append("<div style='display:inline-block; float:none; margin:auto; padding:0 2px; background-color:"+Colour.BACKGROUND.toWebHexString()+"; border-radius:5px; width:auto; position:relative;'>"
							+"<span style='color:"+DamageType.HEALTH.getColour().toWebHexString()+";'>&#9930;</span> "+(shields<0?"[style.colourDisabled("+shields+")]":shields)
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
						"<div style='display:inline-block; float:none; margin:auto; padding:0 2px; background-color:"+Colour.BACKGROUND.toWebHexString()+"; border-radius:5px; width:auto; position:relative;'>"
							+"<span style='color:"+dt.getColour().toWebHexString()+";'>&#9930;</span> "+(shields<0?"[style.colourDisabled("+shields+")]":shields)
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
					"<div style='display:inline-block; float:none; margin:auto; padding:0 2px; background-color:"+Colour.BACKGROUND.toWebHexString()+"; border-radius:5px; width:auto; position:relative;'>"
						+"<span style='color:"+DamageType.LUST.getColour().toWebHexString()+";'>&#9930;</span> "+(shields<0?"[style.colourDisabled("+shields+")]":shields)
						+ "<div class='overlay' id='"+character.getId()+"_COMBAT_SHIELD_"+DamageType.LUST+"' style='cursor:default;'></div>"
					+ "</div>");
		}

		sb.append("</div>");
		
		return sb.toString();
	}
	
	private static String getCombatContent() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='text-align:center; box-sizing: border-box; border:6px solid "+Colour.BASE_WHITE.toWebHexString()+"; border-radius:5px;'>");

			sb.append("<div class='container-full-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; text-align:center;'><b>Planning</b></div>");
		
			sb.append("<div class='container-half-width'>");
				
				sb.append("[style.boldGood(You)]"+ getTitleResources(Main.game.getPlayer()));
				for(String s : predictionContent.get(Main.game.getPlayer())) {
					sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+Colour.BACKGROUND.toWebHexString()+";'>"+s+"</div>");
				}
//				if(Main.game.getPlayer().getRemainingAP()>0) {
//					sb.append("<br/>[style.colourDisabled("+Util.capitaliseSentence(Util.intToString(Main.game.getPlayer().getRemainingAP()))+" unused AP...)]");
//				}
				sb.append("</br>");
			
				for(GameCharacter ally : Combat.getAllies(Main.game.getPlayer())) {
					sb.append(UtilText.parse(ally, "</br>[style.boldMinorGood([npc.Name])]")+ getTitleResources(ally));
					for(String s : predictionContent.get(ally)) {
						sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+Colour.BACKGROUND.toWebHexString()+";'>"+s+"</div>");
					}
//					if(ally.getRemainingAP()>0) {
//						sb.append("<br/>[style.colourDisabled("+Util.capitaliseSentence(Util.intToString(ally.getRemainingAP()))+" unused AP...)]");
//					}
					sb.append("</br>");
				}
			sb.append("</div>");

			sb.append("<div class='container-half-width'>");
			for(GameCharacter enemy : Combat.getEnemies(Main.game.getPlayer())) {
				sb.append(UtilText.parse(enemy, (Combat.enemyLeader.equals(enemy)?"[style.boldBad([npc.Name])]":"</br>[style.boldMinorBad([npc.Name])]"))+ getTitleResources(enemy));
				for(String s : predictionContent.get(enemy)) {
					sb.append("<div class='container-half-width' style='margin:2px; padding:4px; width:100%; border-radius:5px; background:"+Colour.BACKGROUND.toWebHexString()+";'>"+s+"</div>");
				}
//				if(enemy.getRemainingAP()>0) {
//					sb.append("<br/>[style.colourDisabled("+Util.capitaliseSentence(Util.intToString(enemy.getRemainingAP()))+" unused AP...)]");
//				}
				sb.append("</br>");
			}
			sb.append("</div>");
			
		sb.append("</div>");

		sb.append(combatTurnResolutionStringBuilder.toString());
		
		return sb.toString(); 
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
					character.setCombatStatusEffectDuration(se, character.getStatusEffectDuration(se) - 1);
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
	
	public static GameCharacter getTargetedCombatant(GameCharacter attacker) {
		if(attacker.isPlayer()) {
			return targetedEnemy;
		}
		
		if(allies.contains(attacker)) {
			for(NPC enemy : enemies) {
				if(!isCombatantDefeated(enemy)) {
//					System.out.println("T1: "+enemy.getName());
					return enemy;
				}
			}
			return enemyLeader;
		}
		
		if(enemies.contains(attacker)) {
			for(NPC playerAlly : allies) {
				if(!isCombatantDefeated(playerAlly)) {
//					System.out.println("T2: "+playerAlly.getName());
					return playerAlly;
				}
			}
		}
		
		return Main.game.getPlayer();
	}
	
	public static GameCharacter getTargetedAlliedCombatant(GameCharacter attacker) {
		if(attacker.isPlayer()) {
			return targetedAlly;
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
	
	public static AbstractItem getItem() {
		return item;
	}

	public static void setItem(AbstractItem item) {
		Combat.item = item;
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
	}
	
	public static void addEnemy(NPC enemy) {
		enemies.add(enemy);
		allCombatants.add(enemy);
		enemy.resetMoveCooldowns();
		enemy.setFoughtPlayerCount(enemy.getFoughtPlayerCount()+1);
	}
	
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
			if(!character.equals(target)) {
				possibleTargets.add(character);
			}
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
}
