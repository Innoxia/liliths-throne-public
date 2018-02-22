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
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.MapDisplay;
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

/**
 * Singleton enforced by Enum.</br>
 * Call initialiseCombat() before using.
 *
 * @since 0.1.0
 * @version 0.1.99
 * @author Innoxia
 */
public enum Combat {
	COMBAT;

	// TODO Make sure your status effects end before you take your turn, enemy's status effects end at the start of their turn
	// Also, end combat it enemy drops to 0 health/mana/stamina on their turn from combat effects

	private static NPC activeNPC;
	private static NPC targetedCombatant;
	private static List<NPC> allies;
	private static List<NPC> enemies;
	private static List<NPC> allCombatants;
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
	private static int previouslyUsedSpellLevel;
	private static SpecialAttack previouslyUsedSpecialAttack;

	private Combat() {
	}

	/**
	 * @param allies A list of allies who are fighting with you. <b>Do not include Main.game.getPlayer() in this!</b>
	 * @param enemies A list of enemies you're fighting. The first enemy in the list is considered the leader.
	 * @param escapePercentage THe base chance of escaping in this combat situation. TODO
	 * @param openingDescriptions A map of opening descriptions for characters. If a description is not provided, one is generated automatically.
	 */
	public void initialiseCombat(
			List<NPC> allies,
			List<NPC> enemies,
			Map<GameCharacter, String> openingDescriptions) {
		
		if(allies==null) {
			Combat.allies = new ArrayList<>();
		} else {
			Combat.allies = allies;
		}
		
		Combat.enemies = enemies;
		
		allCombatants = new ArrayList<>();
		allCombatants.addAll(Combat.allies);
		allCombatants.addAll(Combat.enemies);
		
		targetedCombatant = enemies.get(0);
		activeNPC = enemies.get(0);

		escaped = false;
		
		for(NPC enemy : enemies) {
			enemy.setFoughtPlayerCount(enemy.getFoughtPlayerCount()+1);
		}
		
		combatContent = "";
		turn = 0;
		combatStringBuilder.setLength(0);
		postCombatStringBuilder.setLength(0);
		
		previousAction = Attack.NONE;

		escapeChance = targetedCombatant.getEscapeChance();
		if (Main.game.getPlayer().hasTrait(Perk.RUNNER, true)) {
			escapeChance *= 1.5f;
		} else if (Main.game.getPlayer().hasTrait(Perk.RUNNER_2, true)) {
			escapeChance *= 2f;
		}
		
		
		if(openingDescriptions!=null && openingDescriptions.containsKey(Main.game.getPlayer())) {
			combatStringBuilder.append(getCharactersTurnDiv(Main.game.getPlayer(), "Preparation", openingDescriptions.get(Main.game.getPlayer())));
		} else {
			combatStringBuilder.append(getCharactersTurnDiv(Main.game.getPlayer(), "Preparation", "You prepare to make a move..."));
		}
		
		for(NPC enemy : enemies) {
			if(openingDescriptions!=null && openingDescriptions.containsKey(enemy)) {
				combatStringBuilder.append(getCharactersTurnDiv(enemy, "", openingDescriptions.get(enemy)));
			} else {
				combatStringBuilder.append(getCharactersTurnDiv(enemy, "Preparation", UtilText.parse(enemy, "[npc.Name] prepares to make a move...")));
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
				"<div class='container-full-width' style='text-align:center; box-sizing: border-box; border:6px solid #333; border-radius:5px;'>"
					+ "<div class='container-half-width' style='width: calc(80% - 16px);'>"+description+effects+"</div>"
					+ "<div class='container-quarter-width'style='width: calc(20% - 16px);'><b style='color:"+character.getFemininity().getColour().toWebHexString()+";'>[npc.Name()]</b></br>"+title+"</div>"
				+ "</div>");
			
		} else {
			return UtilText.parse(character,
				"<div class='container-full-width' style='text-align:center; box-sizing: border-box; border:6px solid #333; border-radius:5px;'>"
					+ "<div class='container-quarter-width'style='width: calc(20% - 16px);'><b style='color:"+character.getFemininity().getColour().toWebHexString()+";'>[npc.Name()]</b></br>"+title+"</div>"
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
				ally.incrementExperience(xp);
				postCombatStringBuilder.append(UtilText.parse(ally,
						"<h6 style='text-align:center;'>[npc.Name] gained <b style='color:" + Colour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + xp + " xp!</b>"));
			}
			
			Main.game.getPlayer().incrementExperience(xp);
			postCombatStringBuilder.append("<h6 style='text-align:center;'>You gained <b style='color:" + Colour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + xp + " xp</b>");
			
			Main.game.getPlayer().incrementMoney(money);
			if (money > 0) {
				postCombatStringBuilder.append(" and " + UtilText.formatAsMoney(money) + "!</h6>");
			} else {
				postCombatStringBuilder.append("!</h6>");
			}
			
			// Apply loot drop:
			for(NPC enemy : enemies) {
				if(enemy.getLootItems()!=null) {
					for(AbstractCoreItem item : enemy.getLootItems()) {
						postCombatStringBuilder.append("<p style='text-align:center;'>You gained <b style='color:"+item.getRarity().getColour().toWebHexString()+";'>"+item.getName()+"</b>!</p>");
						if(item instanceof AbstractItem) {
							Main.game.getPlayer().addItem((AbstractItem) item, false);
						} else if(item instanceof AbstractWeapon) {
							Main.game.getPlayer().addWeapon((AbstractWeapon) item, false);
						} else if(item instanceof AbstractClothing) {
							Main.game.getPlayer().addClothing((AbstractClothing) item, false);
						}
					}
				}
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
											+ " After fruitlessly trying to shake your head clear, you look down at [npc.name] to see if [npc.she]'s being affected by this peculiar phenomenon as well, but as you do, you feel your eyes going wide in shock."
										+ "</p>"
										+ "<p>"
											+ "A shimmering pink glow has materialised around [npc.her] body, just like the one you saw in Lilaya's lab when she ran her tests on you."
											+ " Quickly realising that you're somehow able to see [npc.name]'s arcane aura, you watch, fascinated, as you see a sizable shard slowly start to break away from [npc.herHim]."
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
											+ " After fruitlessly trying to shake your head clear, you look down at [npc.name] to see if [npc.she]'s being affected by this peculiar phenomenon as well, but as you do, you feel your eyes going wide in shock."
										+ "</p>"
										+ "<p>"
											+ "A shimmering pink glow has materialised around [npc.her] body, just like the one you saw in Lilaya's lab when she ran her tests on you."
											+ " Quickly realising that you're somehow able to see [npc.name]'s arcane aura, you watch, fascinated, as you see a sizable shard slowly start to break away from [npc.herHim]."
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
					postCombatStringBuilder.append(
							"<div style='text-align: center; display:block; margin:0 auto; height:48px; padding:8px 0 8px 0;'>"
									+ "<div class='item-inline "+entry.getKey().getRarity().getName()+"'>"
										+ entry.getKey().getSVGString()
										+ "<div class='overlay no-pointer' id='ESSENCE_"+entry.getKey().hashCode()+"'></div>"
									+ "</div>"
									+ "<div style='display:inline-block; height:20px; vertical-align: middle; margin-left:4px;'>"
										+ " You gained <b>"+entry.getValue()+"</b> <b style='color:"+entry.getKey().getColour().toWebHexString()+";'>"+entry.getKey().getName()+"</b> essence"+(entry.getValue()>1?"s":"")+"!"
									+ "</div>"
							+ "</div>");
					Main.game.getPlayer().incrementEssenceCount(entry.getKey(), entry.getValue());
				}
			}
			
		} else { // Player lost combat:
			int xpGain = (Main.game.getPlayer().getLevel()*2);
			
			for(NPC enemy : enemies) {
				enemy.incrementExperience(xpGain);
				postCombatStringBuilder.append(UtilText.parse(enemy,
						"<h6 style='text-align:center;'>[npc.Name] gained <b style='color:" + Colour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + xpGain + " xp</b> from defeating you!</h6>"));
			}
			
			int money = Main.game.getPlayer().getMoney();
			int moneyLoss = (-enemies.get(0).getLootMoney()/2)*enemies.size();
			Main.game.getPlayer().incrementMoney(moneyLoss);
			postCombatStringBuilder.append("<h6 style='text-align:center;'>You <b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>lost</b> "+ UtilText.formatAsMoney((Main.game.getPlayer().getMoney()==0?money:moneyLoss)) + "!</h6>");

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
			enemy.setLust(enemy.getAttributeValue(Attribute.MAJOR_CORRUPTION)*0.1f);
		}
		for(NPC ally : allies) {
			ally.setMana(ally.getAttributeValue(Attribute.MANA_MAXIMUM));
			ally.setHealth(ally.getAttributeValue(Attribute.HEALTH_MAXIMUM));
			ally.setLust(ally.getAttributeValue(Attribute.MAJOR_CORRUPTION)*0.1f);
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
		return (character.getHealth() <= 0 || character.getMana() <= 0);
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.NORMAL;
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.NORMAL;
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.NORMAL;
		}
	};

	public static final DialogueNodeOld ESCAPE = new DialogueNodeOld("Combat", "Escape", true) {
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
				if (escaped) {
					return new ResponseEffectsOnly("Escaped!", "You got away!"){
						@Override
						public void effects() {
							Main.game.setInCombat(false);
							Main.game.setContent(new Response("", "", DebugDialogue.getDefaultDialogueNoEncounter()));
						}
					};
				} else {
					return new Response("Escape failed!", "You failed to escape...", ENEMY_ATTACK);
				}
				
			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.NORMAL;
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
			if(isEnemyPartyDefeated() || isAlliedPartyDefeated()) {
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
			
			
			if(responseTab==1) { // Special attacks:
				if (Main.game.getPlayer().getSpecialAttacks().size() >= index && index!=0) {
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
					return null;
				}
			}
			
			if(responseTab==2) { // Spells
				if (Main.game.getPlayer().getSpells().size() >= index  && index!=0) {
					return new Response(Util.capitaliseSentence(Main.game.getPlayer().getSpells().get(index - 1).getName()),
							getSpellDescription(Main.game.getPlayer().getSpells().get(index - 1), null),
							ENEMY_ATTACK){
						@Override
						public void effects() {
							attackSpell(Main.game.getPlayer(), Main.game.getPlayer().getSpells().get(index - 1), Main.game.getPlayer().getLevel());
							endCombatTurn();
							previousAction = Attack.SPELL;
							previouslyUsedSpell = Main.game.getPlayer().getSpells().get(index - 1);
							previouslyUsedSpellLevel = Main.game.getPlayer().getLevel();
						}
					};
					
				} else {
					return null;
				}
			}
			
			
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.WITCH_SEAL)) { //TODO replace with generic isStunned() method
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
							Main.game.setInCombat(false);
							Main.game.setContent(new Response("", "", DebugDialogue.getDefaultDialogueNoEncounter()));
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
				
			} else {
				if (index == 0) {
					if (escapeChance == 0) {
						return new Response("Escape", "You can't run from this fight!", null);
						
					} else {
						return new Response("Escape", "Try to escape.</br></br>You have a "+escapeChance+"% chance to get away!", ENEMY_ATTACK){
							@Override
							public void effects() {
								escape(Main.game.getPlayer());
								endCombatTurn();
							}
						};
					}
					
				} else if (index == 1) {
					return new Response("Main attack", getMainAttackDescription(), ENEMY_ATTACK){
						@Override
						public void effects() {
							attackMelee(Main.game.getPlayer());
							endCombatTurn();
						}
					};

				} else if (index == 2) {
					return new Response("Offhand attack", getOffhandAttackDescription(), ENEMY_ATTACK){
						@Override
						public void effects() {
							attackOffhand(Main.game.getPlayer());
							endCombatTurn();
						}
					};

				} else if (index == 3) {
					return new Response("Dual strike", getDualAttackDescription(), ENEMY_ATTACK){
						@Override
						public void effects() {
							attackDual(Main.game.getPlayer());
							endCombatTurn();
						}
					};

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
							return new Response(Util.capitaliseSentence(previouslyUsedSpell.getName()), getSpellDescription(previouslyUsedSpell, null), ENEMY_ATTACK){
								@Override
								public void effects() {
									attackSpell(Main.game.getPlayer(), previouslyUsedSpell, previouslyUsedSpellLevel);
									endCombatTurn();
								}
							};
	
						case SPECIAL_ATTACK:
							return new Response(Util.capitaliseSentence(previouslyUsedSpecialAttack.getName()), getSpecialAttackDescription(previouslyUsedSpecialAttack), ENEMY_ATTACK){
								@Override
								public void effects() {
									attackSpecialAttack(Main.game.getPlayer(), previouslyUsedSpecialAttack);
									endCombatTurn();
								}
							};
	
						default:
							return new Response("Repeat", "You have to perform an action first!", null);
					}

				} else if(index>=11 && index - 10 <= enemies.size()) {
					
					return new Response(Util.capitaliseSentence(enemies.get(index-11).getName()), "Switch your target to "+Util.capitaliseSentence(enemies.get(index-11).getName())+".", ENEMY_ATTACK){
						@Override
						public void effects() {
							targetedCombatant = enemies.get(index-11);
						}
						@Override
						public Colour getHighlightColour() {
							return enemies.get(index-11).getRace().getColour();
						}
					};
					
					
				} else {
					return null;
				}
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.NORMAL;
		}
	};

	private static boolean isCriticalHit(GameCharacter attacker) {
		return Util.random.nextInt(100) + 1 <= attacker.getAttributeValue(Attribute.CRITICAL_CHANCE);
	}

	// Calculations for melee attack:
	private static void attackMelee(GameCharacter attacker) {
		GameCharacter target = getTargetedCombatant(attacker);
		float damage = 0;

		attackStringBuilder = new StringBuilder("");

		attackStringBuilder.append(getMeleeAttackDescription(attacker, target));
		
		boolean critical = isCriticalHit(attacker);

		damage = Attack.calculateDamage(attacker, target, Attack.MAIN, critical);
		
		
		if(attacker.isPlayer()) {
			if(attacker.getMainWeapon() == null) {
				attackStringBuilder.append("<p><b>You " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " : " hit for ") + damage + " <b style='color: "
						+ Attribute.DAMAGE_PHYSICAL.getColour().toWebHexString() + ";'>" + Attribute.DAMAGE_PHYSICAL.getName() + "</b>!</b></p>");
				
			} else {
				attackStringBuilder.append("<p><b>You " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " : " hit for ") + damage + " <b style='color: "
						+ attacker.getMainWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + attacker.getMainWeapon().getDamageType().getMultiplierAttribute().getName() + "</b>!</b></p>");
			}
		} else {
			if(attacker.getMainWeapon() == null) {
				attackStringBuilder.append("<p><b>You were " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " : " hit for ") + damage + " <b style='color: "
						+ Attribute.DAMAGE_PHYSICAL.getColour().toWebHexString() + ";'>" + Attribute.DAMAGE_PHYSICAL.getName() + "</b>!</b></p>");
				
			} else {
				attackStringBuilder.append("<p><b>You were " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " : " hit for ") + damage + " <b style='color: "
						+ attacker.getMainWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + attacker.getMainWeapon().getDamageType().getMultiplierAttribute().getName() + "</b>!</b></p>");
			}
		}

		attackStringBuilder.append(target.incrementHealth(-damage));
		
		combatStringBuilder.append(getCharactersTurnDiv(attacker, "Main Attack", attackStringBuilder.toString()));
	}

	private static String getMeleeAttackDescription(GameCharacter attacker, GameCharacter target) {
		return getPregnancyProtectionText(attacker, target)
				+"<p>"
				+ attacker.getMainAttackDescription(true)
				+"</p>";
	}
	
	private static void attackOffhand(GameCharacter attacker) {
		GameCharacter target = getTargetedCombatant(attacker);
		float damage = 0;

		attackStringBuilder = new StringBuilder("");

		attackStringBuilder.append(getOffhandDescription(attacker, target));
		
		boolean critical = isCriticalHit(attacker);

		damage = Attack.calculateDamage(attacker, target, Attack.OFFHAND, critical);
		
		Attribute damageAttribute = (attacker.getOffhandWeapon() == null ? Attribute.DAMAGE_PHYSICAL : attacker.getOffhandWeapon().getDamageType().getMultiplierAttribute());

		if(attacker.isPlayer()) {
			attackStringBuilder.append("<p>"
					+ "<b>You " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> " : "") +"hit for "+ damage + " <b style='color: "
					+ damageAttribute.getColour().toWebHexString() + ";'>"
					+ damageAttribute.getName() + "</b>!</b></p>");
		} else {
			attackStringBuilder.append("<p>"
					+ "<b>You were " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> " : "") +"hit for "+ damage + " <b style='color: "
					+ damageAttribute.getColour().toWebHexString() + ";'>"
					+ damageAttribute.getName() + "</b>!</b></p>");
		}

		attackStringBuilder.append(target.incrementHealth(-damage));
		
		combatStringBuilder.append(getCharactersTurnDiv(attacker, "Offhand Attack", attackStringBuilder.toString()));
	}

	private static String getOffhandDescription(GameCharacter attacker, GameCharacter target) {
		return getPregnancyProtectionText(attacker, target)
				+"<p>"
				+ attacker.getOffhandAttackDescription(true)
				+"</p>";
	}
	
	
	private static void attackDual(GameCharacter attacker) {
		GameCharacter target = getTargetedCombatant(attacker);
		float damageMain = 0, damageOffhand = 0;

		attackStringBuilder = new StringBuilder("");

		
		if (Math.random()<0.5) {
			attackStringBuilder.append(getDualDescription(attacker, target, true));
			
			boolean critical = isCriticalHit(attacker);

			damageMain = Attack.calculateDamage(attacker, target, Attack.MAIN, critical);
			damageOffhand = Attack.calculateDamage(attacker, target, Attack.OFFHAND, critical);
			
			Attribute damageMainAttribute = (attacker.getMainWeapon() == null ? Attribute.DAMAGE_PHYSICAL : attacker.getMainWeapon().getDamageType().getMultiplierAttribute()),
					damageOffhandAttribute = (attacker.getOffhandWeapon() == null ? Attribute.DAMAGE_PHYSICAL : attacker.getOffhandWeapon().getDamageType().getMultiplierAttribute());
			
			attackStringBuilder.append("<p>"
					+ "<b>You " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> " : "") +"hit for "
					+ damageMain + " <b style='color: " + damageMainAttribute.getColour().toWebHexString() + ";'>" + damageMainAttribute.getName() + "</b>, and then again for "
					+ damageOffhand + " <b style='color: " + damageOffhandAttribute.getColour().toWebHexString() + ";'>" + damageOffhandAttribute.getName() + "</b>!</b></p>");
		} else {
			attackStringBuilder.append(getDualDescription(attacker, target, false));
			attackStringBuilder.append("<p><b>You missed!</b></p>");
		}
		
		attackStringBuilder.append(target.incrementHealth(-(damageMain+damageOffhand)));
		
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
	

	// Calculations for seduction attack:
	private static void attackSeduction(GameCharacter attacker) {
		GameCharacter target = getTargetedCombatant(attacker);
		// Calculate hit + damage
		float damage = 0;
		attackStringBuilder = new StringBuilder("");
		
		attackStringBuilder.append(attacker.getSeductionDescription());
		
		boolean critical = isCriticalHit(attacker);
	
		damage = Attack.calculateDamage(attacker, target, Attack.SEDUCTION, critical);
	
		float auraDamage = damage * target.getLustLevel().getAuraDamagePercentage();
		float lustDamage = damage - auraDamage;
		
		if(attacker.isPlayer()) {
			attackStringBuilder.append(UtilText.parse(target,
					"<p>"
						+ (critical
								? "Your seductive display was <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>extremely effective</b>!</br>"
								: "")
						+ (lustDamage > 0
								? "<b>[npc.Name] gains " + lustDamage + " <b style='color:" + Colour.ATTRIBUTE_LUST.toWebHexString() + ";'>lust</b> as [npc.she] tries to resist your seductive display!</b></br>"
								: "")
						+ (auraDamage > 0
								? "<b>[npc.Name] suffers " + damage + " <b style='color:" + Colour.ATTRIBUTE_MANA.toWebHexString() + ";'>aura damage</b> as your seductive display causes [npc.herHim] to lose control!</b>"
								: "")
					+ "</p>"));
		} else {
			attackStringBuilder.append(UtilText.parse(attacker,
					"<p>"
						+ (critical
								? "[npc.Her] seductive display was <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>extremely effective</b>!</br>"
								: "")
						+ (lustDamage > 0
								? "<b>You gain " + lustDamage + " <b style='color:" + Colour.ATTRIBUTE_LUST.toWebHexString() + ";'>lust</b> as you try to resist [npc.her] seductive display!</b></br>"
								: "")
						+ (auraDamage > 0
								? "<b>You suffer " + damage + " <b style='color:" + Colour.ATTRIBUTE_MANA.toWebHexString() + ";'>aura damage</b> as [npc.her] seductive display causes you to lose control!</b>"
								: "")
					+ "</p>"));
		}

		target.incrementLust(lustDamage);
		target.incrementMana(-auraDamage);
		
		combatStringBuilder.append(getCharactersTurnDiv(attacker, "Seduction", attackStringBuilder.toString()));
	}

	private static void attackSpell(GameCharacter attacker, Spell spell, int level) {
		GameCharacter target = getTargetedCombatant(attacker);

		boolean critical = isCriticalHit(attacker);

		attackStringBuilder = new StringBuilder();

		attackStringBuilder.append(getPregnancyProtectionText(attacker, target));

		attackStringBuilder.append(attacker.getSpellDescription());
		attackStringBuilder.append(spell.applyEffect(attacker, target, level, true, critical));
		
		if(critical && attacker.hasTraitActivated(Perk.ARCANE_CRITICALS)) {//TODO description
			target.addStatusEffect(StatusEffect.ARCANE_WEAKNESS, 1);
		}
		
		combatStringBuilder.append(getCharactersTurnDiv(attacker, Util.capitaliseSentence(spell.getName()), attackStringBuilder.toString()));
	}

	private static void attackSpecialAttack(GameCharacter attacker, SpecialAttack specialAttack) {
		GameCharacter target = getTargetedCombatant(attacker);

		boolean critical = isCriticalHit(attacker);

		attackStringBuilder = new StringBuilder();
		
		attackStringBuilder.append(getPregnancyProtectionText(attacker, target));
		
		attackStringBuilder.append(specialAttack.applyEffect(attacker, target, true, critical));

		combatStringBuilder.append(getCharactersTurnDiv(attacker, Util.capitaliseSentence(specialAttack.getName()), attackStringBuilder.toString()));
	}

	private static void attackWait(GameCharacter attacker) {
		attackStringBuilder = new StringBuilder(
				UtilText.parse(enemies.get(0),
				"<p>"
					+ "You decide not to make a move, and instead try to brace yourself as best as possible against [npc.name]'s next attack."
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
		attackStringBuilder = new StringBuilder("<p>");

		if (Util.random.nextInt(100) < escapeChance) {
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
		
		if(npc.hasStatusEffect(StatusEffect.WITCH_SEAL)) { //TODO replace with generic isStunned() method
			combatStringBuilder.append(getCharactersTurnDiv(npc,
					"Stunned",
					UtilText.parse(npc,
							"<p>"
								+"[npc.Name] is unable to make a move!"
							+ "</p>")));
		} else {
			// Calculate what attack to use based on NPC preference:
			Attack opponentAttack = npc.attackType();
			
			switch(opponentAttack){
				case DUAL:
					attackDual(npc);
					break;
					
				case ESCAPE:
					break;
					
				case MAIN:
					attackMelee(npc);
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
					SpecialAttack specialAttack = npc.getSpecialAttacks().get(Util.random.nextInt(npc.getSpecialAttacks().size()));
					attackSpecialAttack(npc, specialAttack);
					break;
					
				case SPELL:
					Spell spell = npc.getSpell();
					attackSpell(npc, spell, npc.getLevel());
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
//						+opponent.getName("The")+" doesn't have the strength to continue fighting...</br>"
//						+ "<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>You are victorious!</span>"
//						+ "</p>";
//				
//			}	else{
//				opponentTurnText = combatStringBuilder.toString();
//			}
		}
		
		turn++;
	}

	private static StringBuilder endTurnStatusEffectText = new StringBuilder();

	public static void endCombatTurn() {
		
		for(NPC character : allCombatants) {
			attackNPC(character);
		}
		
		// Remove any status effects from all characters that are beneficial:
		removeBeneficialEffects(Main.game.getPlayer());
		for(NPC character : allCombatants) {
			removeBeneficialEffects(character);
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
	}
	
	private static String applyEffects(GameCharacter character) {
		endTurnStatusEffectText = new StringBuilder();
		List<StatusEffect> effectsToRemove = new ArrayList<>();
		for (StatusEffect se : character.getStatusEffects()) {
			if (se.isCombatEffect()) {
				endTurnStatusEffectText.append("<p><b style='color: " + se.getColour().toWebHexString() + "'>" + Util.capitaliseSentence(se.getName(character)) + "</b> - " + se.applyEffect(character, 0)+ "</p>");
				if (!se.isBeneficial()) {//TODO check
					character.setStatusEffectDuration(se, character.getStatusEffectDuration(se) - 1);
				}
				if (character.getStatusEffectDuration(se) <= 0) {
					effectsToRemove.add(se);
				}
			}
		}
		for (StatusEffect se : effectsToRemove) {
			character.removeStatusEffect(se);
		}
		return endTurnStatusEffectText.toString();
	}

	private static void removeBeneficialEffects(GameCharacter character) {
		List<StatusEffect> effectsToRemove = new ArrayList<>();
		for (StatusEffect se : character.getStatusEffects()) {
			if (se.isCombatEffect()) {
				if (se.isBeneficial()) {
					character.setStatusEffectDuration(se, character.getStatusEffectDuration(se) - 1);
				}
				if (character.getStatusEffectDuration(se) <= 0) {
					effectsToRemove.add(se);
				}
			}
		}
		for (StatusEffect se : effectsToRemove) {
			character.removeStatusEffect(se);
		}
	}
	

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
		
		attackDescriptionSB.append("Strike out at " + targetedCombatant.getName("the") + "!</br></br>");
		
		if (Main.game.getPlayer().getMainWeapon() == null) {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + "</b>" + " <b style='color:"
					+ Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString() + ";'>" + Util.capitaliseSentence(DamageType.PHYSICAL.getName()) + "</b> <b>damage</b></br></br>");
		} else {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getMainWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getMainWeapon().getDamageType().getName())
					+ "</b> <b>damage</b></br></br>");
		}

		attackDescriptionSB.append("Main and offhand attacks <b style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>always hit</b>.");
		
		return attackDescriptionSB.toString();
	}
	
	private static String getOffhandAttackDescription() {
		attackDescriptionSB = new StringBuilder();
		
		attackDescriptionSB.append("Strike out at " + targetedCombatant.getName("the") + "!</br></br>");
		
		if (Main.game.getPlayer().getOffhandWeapon() == null) {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + "</b>" + " <b style='color:"
					+ Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString() + ";'>" + Util.capitaliseSentence(DamageType.PHYSICAL.getName()) + "</b> <b>damage</b></br></br>");
		} else {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getOffhandWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getOffhandWeapon().getDamageType().getName())
					+ "</b> <b>damage</b></br></br>");
		}

		attackDescriptionSB.append("Main and offhand attacks <b style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>always hit</b>.");
		
		return attackDescriptionSB.toString();
	}
	
	private static String getDualAttackDescription() {
		attackDescriptionSB = new StringBuilder();
		
		attackDescriptionSB.append("Give " + targetedCombatant.getName("the") + " everything you've got!</br></br>");
		
		if (Main.game.getPlayer().getMainWeapon() == null) {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + "</b>" + " <b style='color:"
					+ Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString() + ";'>" + Util.capitaliseSentence(DamageType.PHYSICAL.getName()) + "</b> <b>damage</b></br>");
		} else {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.MAIN) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getMainWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getMainWeapon().getDamageType().getName())
					+ "</b> <b>damage</b></br>");
		}
		
		if (Main.game.getPlayer().getOffhandWeapon() == null) {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + "</b>" + " <b style='color:"
					+ Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString() + ";'>" + Util.capitaliseSentence(DamageType.PHYSICAL.getName()) + "</b> <b>damage</b></br>");
		} else {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.OFFHAND) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getOffhandWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getOffhandWeapon().getDamageType().getName())
					+ "</b> <b>damage</b></br>");
		}

		attackDescriptionSB.append("You have a <b>50%</b> <b style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>chance to hit</b>.");
		
		return attackDescriptionSB.toString();
	}

	private static String getTeaseDescription() {

		return "Attempt to seduce " + targetedCombatant.getName("the") + ".</br></br>"

				+ "<b>"
				+ Attack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Attack.SEDUCTION) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Attack.SEDUCTION) + "</b>"
				+ " <b style='color:"+ Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(DamageType.LUST.getName()) + "</b> <b>damage</b></br></br>"

				+ "Seduction attacks <b style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>always hit</b>.";
	}

	private static String getSpellDescription(Spell spell, AbstractWeapon source) {
		return "Cast <b>Level " + Main.game.getPlayer().getLevel() + "</b> <b style='color:" + spell.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(spell.getName()) + "</b></br></br>"

				+ "<b>" + spell.getMinimumDamage(Main.game.getPlayer(), targetedCombatant, Main.game.getPlayer().getLevel()) + " - " + spell.getMaximumDamage(Main.game.getPlayer(), targetedCombatant, Main.game.getPlayer().getLevel()) + "</b>" + " <b style='color:"
				+ spell.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(spell.getDamageType().getName()) + "</b> <b>damage</b></br></br>"
				
				+ "<b>" + spell.getMinimumCost(Main.game.getPlayer(), Main.game.getPlayer().getLevel()) + " - " + spell.getMaximumCost(Main.game.getPlayer(), Main.game.getPlayer().getLevel()) + "</b>" + " <b style='color:"
				+ Colour.ATTRIBUTE_MANA.toWebHexString() + ";'>aura</b> <b>cost</b></br></br>";
	}

	private static String getSpecialAttackDescription(SpecialAttack specialAttack) {

		return "Use your <b style='color:" + specialAttack.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(specialAttack.getName()) + "</b> special attack.</br></br>"

				+ "<b>" + specialAttack.getMinimumDamage(Main.game.getPlayer(), targetedCombatant) + " - " + specialAttack.getMaximumDamage(Main.game.getPlayer(), targetedCombatant) + "</b>" + " <b style='color:"
				+ specialAttack.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(specialAttack.getDamageType().getName()) + "</b> <b>damage</b></br></br>"


				+ "<b>" + specialAttack.getMinimumCost(Main.game.getPlayer()) + " - " + specialAttack.getMaximumCost(Main.game.getPlayer()) + "</b>" + " <b style='color:"
				+ Colour.ATTRIBUTE_HEALTH.toWebHexString() + ";'>energy</b> <b>cost</b></br></br>";
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

	public static void setTargetedCombatant(NPC targetedCombatant) {
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
			return (target.isVisiblyPregnant()
					?UtilText.parse(target,
						"<p>"
							+ "A powerful field of arcane energy is protecting [npc.name]'s pregnant belly, which doesn't even come into play, as you're very careful not to hit anywhere near [npc.her] stomach."
						+ "</p>")
					:"");
		}
	}

	public static List<NPC> getAllies() {
		return allies;
	}

	public static List<NPC> getEnemies() {
		return enemies;
	}
}
