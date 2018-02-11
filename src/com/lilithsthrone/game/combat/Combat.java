package com.lilithsthrone.game.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.Rarity;
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

	private static NPC targetedCombatant, opponent;
	private static String combatText = "", playerActionText = "", opponentActionText = "", playerTurnText = "", opponentTurnText = "";
	private static int escapeChance = 0, turn = 0;
	private static boolean escaped = false;
	private static StringBuilder combatStringBuilder = new StringBuilder("");

	private static AbstractItem item;

	// For use in repeat last action:
	private static Attack previousAction;
	private static Spell previouslyUsedSpell;
	private static int previouslyUsedSpellLevel;
	private static SpecialAttack previouslyUsedSpecialAttack;

	private Combat() {
	}

	/**
	 * Initialises combat, setting the opponent's health and composure to full.
	 * 
	 * @param npc
	 * @param escapeChance Chance of an escape succeeding (out of 100)
	 */
	public void initialiseCombat(NPC npc,
			int escapePercentage,
			String playerStartingTitle,
			String playerStartingDescription,
			String opponentStartingTitle,
			String opponentStartingDescription) {
		
		escaped = false;
		opponent = npc;
		targetedCombatant = npc;
		
		opponent.setFoughtPlayerCount(opponent.getFoughtPlayerCount()+1);

		previousAction = Attack.NONE;

		escapeChance = npc.getEscapeChance();
		if (Main.game.getPlayer().hasTrait(Perk.RUNNER, true))
			escapeChance *= 1.5f;
		else if (Main.game.getPlayer().hasTrait(Perk.RUNNER_2, true))
			escapeChance *= 2f;
		
		turn = 0;
		
		combatText = "";
		
		playerActionText = playerStartingTitle;
		playerTurnText = playerStartingDescription;
		
		opponentActionText = opponentStartingTitle;
		opponentTurnText = opponentStartingDescription;

		Main.game.setInCombat(true);
		
		Main.mainController.openInventory();
		
	}

	
	private static StringBuilder postCombatStringBuilder = new StringBuilder();
	private static String postCombatString = "";
	
	/**
	 * Ends combat, removing status effects and handling post-combat experience
	 * gains and loot drops.
	 * 
	 * @param playerVictory
	 */
	private static void endCombat(boolean playerVictory) {
		
		postCombatStringBuilder.setLength(0);
		
		if (playerVictory) {
			// Give the player experience if they won:
			Main.game.getPlayer().incrementExperience(opponent.getExperienceFromVictory());
			postCombatStringBuilder.append("<h6 style='text-align:center;'>You gained <b style='color:" + Colour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + opponent.getExperienceFromVictory() + " xp</b>");

			// Give the player money, or take money away, depending on victory or defeat:
			Main.game.getPlayer().incrementMoney(opponent.getLootMoney());
			if (opponent.getLootMoney() > 0)
				postCombatStringBuilder.append(" and " + UtilText.formatAsMoney(opponent.getLootMoney()) + "!</h6>");
			else
				postCombatStringBuilder.append("!</h6>");

			// Apply loot drop:
			if(opponent.getLootItems()!=null) {
				for(AbstractCoreItem item : opponent.getLootItems()) {
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
			if(opponent.getLootEssenceDrops()!=null) {
				
				if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.essencePostCombatDiscovered)) {
					Main.game.getDialogueFlags().values.add(DialogueFlagValue.essencePostCombatDiscovered);
					
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
						postCombatStringBuilder.append(
								UtilText.parse(opponent,
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
								+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().incrementQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"")));
						
					} else {
						postCombatStringBuilder.append(
								UtilText.parse(opponent,
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
				
				for(Entry<TFEssence, Integer> entry : opponent.getLootEssenceDrops().entrySet()) {
					postCombatStringBuilder.append(
							"<div style='text-align: center; display:block; margin:0 auto; height:48px; padding:8px 0 8px 0;'>"
									+ "<div class='item-inline"
										+ (entry.getKey().getRarity() == Rarity.COMMON ? " common" : "")
										+ (entry.getKey().getRarity() == Rarity.UNCOMMON ? " uncommon" : "")
										+ (entry.getKey().getRarity() == Rarity.RARE ? " rare" : "")
										+ (entry.getKey().getRarity() == Rarity.EPIC ? " epic" : "")
										+ (entry.getKey().getRarity() == Rarity.LEGENDARY ? " legendary" : "")
										+ (entry.getKey().getRarity() == Rarity.JINXED ? " jinxed" : "") + "'>"
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
			opponent.setLostCombatCount(opponent.getLostCombatCount()+1);
			
		} else {
			int xpGain = (Main.game.getPlayer().getLevel()*2);
			
			opponent.incrementExperience(xpGain);
			postCombatStringBuilder.append(UtilText.parse(opponent,
					"<h6 style='text-align:center;'>[npc.Name] gained <b style='color:" + Colour.GENERIC_EXPERIENCE.toWebHexString() + ";'>" + xpGain + " xp</b> from defeating you!</h6>"));
			
			int money = Main.game.getPlayer().getMoney();
			Main.game.getPlayer().incrementMoney(-opponent.getLootMoney()/2);
			postCombatStringBuilder.append("<h6 style='text-align:center;'>You <b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>lost</b> "
					+ UtilText.formatAsMoney((Main.game.getPlayer().getMoney()==0?money:opponent.getLootMoney()/2)) + "!</h6>");

			opponent.setWonCombatCount(opponent.getWonCombatCount()+1);
		}

		Main.game.setInCombat(false);

		// Sort out effects after combat:
		if (Main.game.getPlayer().getHealth() == 0)
			Main.game.getPlayer().setHealth(5);
		if (Main.game.getPlayer().getMana() == 0)
			Main.game.getPlayer().setMana(5);
		
		// Reset opponent resources to starting values:
		opponent.setMana(opponent.getAttributeValue(Attribute.MANA_MAXIMUM));
		opponent.setHealth(opponent.getAttributeValue(Attribute.HEALTH_MAXIMUM));
		opponent.setLust(opponent.getAttributeValue(Attribute.MAJOR_CORRUPTION)*0.1f);

		postCombatString = postCombatStringBuilder.toString();
		
		Main.game.getTextStartStringBuilder().append(postCombatString);
	}

	private static String npcStatus() {
		return "";
	}

	// DIALOGUES:
	public DialogueNodeOld startCombat() {
		return ENEMY_ATTACK;
	}

	private static StringBuilder tempSB;
	
	public static final DialogueNodeOld SPELL_SELECTION = new DialogueNodeOld("Combat", "Use a spell.", true) {
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
			tempSB = new StringBuilder();
			
			tempSB.append("<div style='width:800px; padding:8px;margin:8px;float:left;text-align:center;'>"
					+ "<h6 style='width:100%;margin:0 0 8px 0;'>Available spells:</h6>"
					+ "<table style='margin:0 auto; font-size:1.1em;'>"
					+ "<th>Spell</th><th>Damage</th><th>Cost</th>");
			
			for(Spell s : Main.game.getPlayer().getSpells())
				tempSB.append("<tr>"
						+ "<td style='min-width:120px;'><b>"+Util.capitaliseSentence(s.getName())+"</b></td>"
						+ "<td style='min-width:200px;'><b>"+s.getMinimumDamage(Main.game.getPlayer(), opponent, Main.game.getPlayer().getLevel())+" - "+s.getMaximumDamage(Main.game.getPlayer(), opponent, Main.game.getPlayer().getLevel())+"</b>"
								+ " <b style='color:"+s.getDamageType().getMultiplierAttribute().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(s.getDamageType().getName())+"</b></td>"
						+ "<td style='min-width:200px;'><b>"+s.getMinimumCost(Main.game.getPlayer(), Main.game.getPlayer().getLevel())+" - "+s.getMaximumCost(Main.game.getPlayer(), Main.game.getPlayer().getLevel())+"</b>"
							+ " <b style='color:"+Colour.ATTRIBUTE_MANA.toWebHexString()+";'>Aura</b></td>"
						+ "</tr>");
			
			tempSB.append("</table></div>");
			
			return tempSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Do something else.", ENEMY_ATTACK);
				
			} else if (Main.game.getPlayer().getSpells().size() >= index) {
				return new Response(Util.capitaliseSentence(Main.game.getPlayer().getSpells().get(index - 1).getName()),
						getSpellDescription(Main.game.getPlayer().getSpells().get(index - 1), null),
						ENEMY_ATTACK){
					@Override
					public void effects() {
						attackSpell(Main.game.getPlayer(), opponent, Main.game.getPlayer().getSpells().get(index - 1), Main.game.getPlayer().getLevel());
						attackEnemy();
						previousAction = Attack.SPELL;
						previouslyUsedSpell = Main.game.getPlayer().getSpells().get(index - 1);
						previouslyUsedSpellLevel = Main.game.getPlayer().getLevel();
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

	public static final DialogueNodeOld SPECIAL_ATTACK_SELECTION = new DialogueNodeOld("Combat", "Use a special attack.", true) {
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
			tempSB = new StringBuilder();
			
			tempSB.append("<div style='width:800px; padding:8px;margin:8px;float:left;text-align:center;'>"
					+ "<h6 style='width:100%;margin:0 0 8px 0;'>Available special attacks:</h6>"
					+ "<table style='margin:0 auto; font-size:1.1em;'>"
					+ "<th>Spell</th><th>Damage</th><th>Cost</th>");
			
			for(SpecialAttack sa : Main.game.getPlayer().getSpecialAttacks())
				tempSB.append("<tr>"
						+ "<td style='min-width:120px;'><b>"+Util.capitaliseSentence(sa.getName())+"</b></td>"
						+ "<td style='min-width:200px;'><b>"+sa.getMinimumDamage(Main.game.getPlayer(), opponent)+" - "+sa.getMaximumDamage(Main.game.getPlayer(), opponent)+"</b>"
								+ " <b style='color:"+sa.getDamageType().getMultiplierAttribute().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(sa.getDamageType().getName())+"</b></td>"
						+ "<td style='min-width:200px;'><b>"+sa.getMinimumCost(Main.game.getPlayer())+" - "+sa.getMaximumCost(Main.game.getPlayer())+"</b>"
							+ " <b style='color:"+Colour.ATTRIBUTE_HEALTH.toWebHexString()+";'>Health</b></td>"
						+ "</tr>");
			
			tempSB.append("</table></div>");
			
			return tempSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Do something else.", ENEMY_ATTACK);
				
			} else if (Main.game.getPlayer().getSpecialAttacks().size() >= index) {
				return new Response(Util.capitaliseSentence(Main.game.getPlayer().getSpecialAttacks().get(index - 1).getName()),
						getSpecialAttackDescription(Main.game.getPlayer().getSpecialAttacks().get(index - 1)),
						ENEMY_ATTACK){
					@Override
					public void effects() {
						attackSpecialAttack(Main.game.getPlayer(), opponent, Main.game.getPlayer().getSpecialAttacks().get(index - 1));
						attackEnemy();
						previousAction = Attack.SPECIAL_ATTACK;
						previouslyUsedSpecialAttack = Main.game.getPlayer().getSpecialAttacks().get(index - 1);
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
			return combatText;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (opponent.getHealth() <= 0 || opponent.getMana() <= 0) {
					return new ResponseEffectsOnly("Victory", "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>You have defeated " + opponent.getName("the") + "!</span>"){
						@Override
						public void effects() {
							endCombat(true);
							Main.game.setContent(opponent.endCombat(true, true));
						}
					};
				} else {
					return new Response("Continue", opponent.getName("The") + " strikes back.", ENEMY_ATTACK){
						@Override
						public void effects() {
							attackEnemy();
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
			return UtilText.parse(opponent,
							"<p>"
									+ "Are you certain you want to <b>submit</b> to [npc.name]? <b>This will cause you to lose the fight, allowing [npc.herHim] to do anything [npc.she] wants with you!</b>"
							+ "</p>");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Submit", "Submit to " + opponent.getName("the") + ". <span style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>This will cause you to lose the current combat!</span>", SUBMIT_CONFIRM){
					@Override
					public void effects() {
						combatText = submit(Main.game.getPlayer(), opponent);
					}
				};
				
			} else if (index == 0) {
				return new Response("Cancel", "Carry on fighting.", ENEMY_ATTACK){
					@Override
					public void effects() {
						combatText = submit(Main.game.getPlayer(), opponent);
					}
				};
				
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
			return combatText;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Continue", "You wait for " + opponent.getName("the") + " to make a move."){
					@Override
					public void effects() {
						endCombat(false);
						Main.game.setContent(opponent.endCombat(true, false));
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
			return combatText;
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
			return "<div style='width:49%; padding:0 4% 4% 4%; margin:2% 1% 0 0; float:left; text-align:center; box-sizing: border-box; border:6px solid #333; border-radius:5px;'>"
						+ "<h6 style='width:100%; margin:0 0 8px 0;'><span style='color:"+Main.game.getPlayer().getFemininity().getColour().toWebHexString()+";'>Your Turn:</br></span>"+playerActionText+"</h6>"
						+ playerTurnText
					+ "</div>"
					
					+"<div style='width:49%; padding:0 4% 4% 4%; margin:2% 0 0 1%; float:left; text-align:center; box-sizing: border-box; border:6px solid #333; border-radius:5px;'>"
						+ "<h6 style='width:100%;margin:0 0 8px 0;'><span style='color:"+opponent.getFemininity().getColour().toWebHexString()+";'>[npc.Name]'s Turn:</br></span>"+opponentActionText+"</h6>"
						+ opponentTurnText 
					+ "</div>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.WITCH_SEAL)) { //TODO replace with generic isStunned() method
				if (index == 1) {
					return new Response("Stunned!", "You are unable to make an action this turn!", ENEMY_ATTACK){
						@Override
						public void effects() {
							stunnedTurn();
							attackEnemy();
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
				
			} else if (opponent.getHealth() <= 0 || opponent.getMana() <= 0) {
				if (index == 1) {
					return new ResponseEffectsOnly("Victory", "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>You have defeated " + opponent.getName("the") + "!</span>"){
						@Override
						public void effects() {
							endCombat(true);
							Main.game.setContent(opponent.endCombat(true, true));
						}
					};
				} else
					return null;
				
			}  else if (Main.game.getPlayer().getHealth() <= 0 || Main.game.getPlayer().getMana() <= 0) {
				if (index == 1) {
					return new ResponseEffectsOnly("Defeat", "You have been defeated!"){
						@Override
						public void effects() {
							endCombat(false);
							Main.game.setContent(opponent.endCombat(true, false));
						}
					};
				} else
					return null;
				
			} else {
				if (index == 1) {
					return new Response("Main attack", getMainAttackDescription(), ENEMY_ATTACK){
						@Override
						public void effects() {
							attackMelee(Main.game.getPlayer(), opponent);
							attackEnemy();
						}
					};

				} else if (index == 2) {
					return new Response("Offhand attack", getOffhandAttackDescription(), ENEMY_ATTACK){
						@Override
						public void effects() {
							attackOffhand(Main.game.getPlayer(), opponent);
							attackEnemy();
						}
					};

				} else if (index == 3) {
					return new Response("Dual strike", getDualAttackDescription(), ENEMY_ATTACK){
						@Override
						public void effects() {
							attackDual(Main.game.getPlayer(), opponent);
							attackEnemy();
						}
					};

				} else if (index == 4) {
					return new Response("Seduce", getTeaseDescription(), ENEMY_ATTACK){
						@Override
						public void effects() {
							attackSeduction(Main.game.getPlayer(), opponent);
							attackEnemy();
						}
					};

				} else if (index == 5) {
					if (Main.game.getPlayer().getSpells().size() == 0)
						return new Response("Spells", "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't know any spells!</span>", null);
					else
						return new Response("Spells", "Proceed to spell choice menu.", SPELL_SELECTION);

				} else if (index == 6) {
					if (Main.game.getPlayer().getSpecialAttacks().size() == 0)
						return new Response("Special Attacks", "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't know any special attacks!</span>", null);
					else
						return new Response("Special Attacks", "Proceed to special attack menu.", SPECIAL_ATTACK_SELECTION);

				} else if (index == 7) {
					if (Main.game.getPlayer().getInventorySlotsTaken() == 0) {
						return new Response("Use item", "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have any items!</span>", null);
					} else {
						return new Response("Use item", "Proceed to item menu.", InventoryDialogue.INVENTORY_MENU){
							@Override
							public void effects() {
								Main.game.saveDialogueNode();
							}
						};
					}
					
				} else if (index == 8) {
					return new Response("Wait", "Don't perform an action.", ENEMY_ATTACK){
						@Override
						public void effects() {
							attackWait(Main.game.getPlayer(), opponent);
							attackEnemy();
						}
					};

				} else if (index == 9) {
					switch (previousAction) {
						case SPELL:
							return new Response(Util.capitaliseSentence(previouslyUsedSpell.getName()), getSpellDescription(previouslyUsedSpell, null), ENEMY_ATTACK){
								@Override
								public void effects() {
									attackSpell(Main.game.getPlayer(), opponent, previouslyUsedSpell, previouslyUsedSpellLevel);
									attackEnemy();
								}
							};
	
						case SPECIAL_ATTACK:
							return new Response(Util.capitaliseSentence(previouslyUsedSpecialAttack.getName()), getSpecialAttackDescription(previouslyUsedSpecialAttack), ENEMY_ATTACK){
								@Override
								public void effects() {
									attackSpecialAttack(Main.game.getPlayer(), opponent, previouslyUsedSpecialAttack);
									attackEnemy();
								}
							};
	
						default:
							return new Response("Repeat", "You have to perform an action first!", null);
					}

				}  else if (index == 10) {
					return new Response("Submit", "Consider submitting to " + opponent.getName("the") + ".", SUBMIT);

				} else if (index == 0) {
					if (escapeChance == 0) {
						return new Response("Escape", "You can't run from this fight!", null);
						
					} else {
						return new Response("Escape", "Try to escape.</br></br>You have a "+escapeChance+"% chance to get away!", ENEMY_ATTACK){
							@Override
							public void effects() {
								escape(Main.game.getPlayer(), opponent);
								attackEnemy();
							}
						};
					}
					
				} else
					return null;
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

	private static void stunnedTurn() {
		playerActionText = "Stunned";
		playerTurnText = "You are unable to make a move!"+endCombatTurn(true);
	}
	
	// Calculations for melee attack:
	private static void attackMelee(GameCharacter attacker, GameCharacter target) {
		float damage = 0;

		combatStringBuilder = new StringBuilder("");

		combatStringBuilder.append(getMeleeAttackDescription(attacker, target));
		
		boolean critical = isCriticalHit(attacker);

		damage = Attack.calculateDamage(attacker, target, Attack.MAIN, critical);
		
		
		if(attacker.isPlayer()) {
			if(attacker.getMainWeapon() == null) {
				combatStringBuilder.append("<p><b>You " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " : " hit for ") + damage + " <b style='color: "
						+ Attribute.DAMAGE_PHYSICAL.getColour().toWebHexString() + ";'>" + Attribute.DAMAGE_PHYSICAL.getName() + "</b>!</b></p>");
				
			} else {
				combatStringBuilder.append("<p><b>You " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " : " hit for ") + damage + " <b style='color: "
						+ attacker.getMainWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + attacker.getMainWeapon().getDamageType().getMultiplierAttribute().getName() + "</b>!</b></p>");
			}
		} else {
			if(attacker.getMainWeapon() == null) {
				combatStringBuilder.append("<p><b>You were " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " : " hit for ") + damage + " <b style='color: "
						+ Attribute.DAMAGE_PHYSICAL.getColour().toWebHexString() + ";'>" + Attribute.DAMAGE_PHYSICAL.getName() + "</b>!</b></p>");
				
			} else {
				combatStringBuilder.append("<p><b>You were " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " : " hit for ") + damage + " <b style='color: "
						+ attacker.getMainWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + attacker.getMainWeapon().getDamageType().getMultiplierAttribute().getName() + "</b>!</b></p>");
			}
		}

		combatStringBuilder.append(target.incrementHealth(-damage));

		if(attacker.isPlayer()) {
			combatStringBuilder.append(endCombatTurn(true));
		}
		
		if(attacker.isPlayer()) {
			playerActionText = "Main attack";
			playerTurnText = combatStringBuilder.toString();
		}
	}

	private static String getMeleeAttackDescription(GameCharacter attacker, GameCharacter target) {
		String attack;
		
		if(attacker.getMainWeapon()!= null) {
			attack = attacker.getMainWeapon().getWeaponType().getAttackDescription(attacker, target, true);
		} else {
			attack = AbstractWeaponType.genericMeleeAttackDescription(attacker, target, true);
		}

		return getPregnancyProtectionText(attacker, target)
				+"<p>"
				+ attack
				+"</p>";
	}
	
	private static void attackOffhand(GameCharacter attacker, GameCharacter target) {
		float damage = 0;

		combatStringBuilder = new StringBuilder("");

		combatStringBuilder.append(getOffhandDescription(attacker, target));
		
		boolean critical = isCriticalHit(attacker);

		damage = Attack.calculateDamage(attacker, target, Attack.OFFHAND, critical);
		
		Attribute damageAttribute = (attacker.getOffhandWeapon() == null ? Attribute.DAMAGE_PHYSICAL : attacker.getOffhandWeapon().getDamageType().getMultiplierAttribute());

		if(attacker.isPlayer()) {
			combatStringBuilder.append("<p>"
					+ "<b>You " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> " : "") +"hit for "+ damage + " <b style='color: "
					+ damageAttribute.getColour().toWebHexString() + ";'>"
					+ damageAttribute.getName() + "</b>!</b></p>");
		} else {
			combatStringBuilder.append("<p>"
					+ "<b>You were " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> " : "") +"hit for "+ damage + " <b style='color: "
					+ damageAttribute.getColour().toWebHexString() + ";'>"
					+ damageAttribute.getName() + "</b>!</b></p>");
		}

		combatStringBuilder.append(target.incrementHealth(-damage));

		if(attacker.isPlayer()) {
			combatStringBuilder.append(endCombatTurn(true));
		}
		
		playerActionText = "Offhand attack";
		playerTurnText = combatStringBuilder.toString();
	}

	private static String getOffhandDescription(GameCharacter attacker, GameCharacter target) {
		String attack;
		
		if(attacker.getOffhandWeapon()!= null) {
			attack = attacker.getOffhandWeapon().getWeaponType().getAttackDescription(attacker, target, true);
		} else {
			attack = AbstractWeaponType.genericMeleeAttackDescription(attacker, target, true);
		}

		return getPregnancyProtectionText(attacker, target)
				+"<p>"
				+ attack
				+"</p>";
	}
	
	
	private static void attackDual(GameCharacter attacker, GameCharacter target) {
		float damageMain = 0, damageOffhand = 0;

		combatStringBuilder = new StringBuilder("");

		
		if (Math.random()<0.5) {
			combatStringBuilder.append(getDualDescription(attacker, target, true));
			
			boolean critical = isCriticalHit(attacker);

			damageMain = Attack.calculateDamage(attacker, target, Attack.MAIN, critical);
			damageOffhand = Attack.calculateDamage(attacker, target, Attack.OFFHAND, critical);
			
			Attribute damageMainAttribute = (attacker.getMainWeapon() == null ? Attribute.DAMAGE_PHYSICAL : attacker.getMainWeapon().getDamageType().getMultiplierAttribute()),
					damageOffhandAttribute = (attacker.getOffhandWeapon() == null ? Attribute.DAMAGE_PHYSICAL : attacker.getOffhandWeapon().getDamageType().getMultiplierAttribute());
			
			combatStringBuilder.append("<p>"
					+ "<b>You " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> " : "") +"hit for "
					+ damageMain + " <b style='color: " + damageMainAttribute.getColour().toWebHexString() + ";'>" + damageMainAttribute.getName() + "</b>, and then again for "
					+ damageOffhand + " <b style='color: " + damageOffhandAttribute.getColour().toWebHexString() + ";'>" + damageOffhandAttribute.getName() + "</b>!</b></p>");
		} else {
			combatStringBuilder.append(getDualDescription(attacker, target, false));
			combatStringBuilder.append("<p><b>You missed!</b></p>");
		}
		
		combatStringBuilder.append(target.incrementHealth(-(damageMain+damageOffhand)));

		if(attacker.isPlayer()) {
			combatStringBuilder.append(endCombatTurn(true));
		}

		playerActionText = "Dual strike";
		playerTurnText = combatStringBuilder.toString();
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
	private static void attackSeduction(GameCharacter attacker, GameCharacter target) {
		// Calculate hit + damage
		float damage = 0;
		combatStringBuilder = new StringBuilder("");

		
		if(attacker.isPlayer()) {
			combatStringBuilder.append("<p>" + getSeductionAttackDescription() + "</p>");
		} else {
			combatStringBuilder.append("<p>" + ((NPC) attacker).getAttackDescription(Attack.SEDUCTION, true) + "</p>");
		}
		
		boolean critical = isCriticalHit(attacker);
	
		damage = Attack.calculateDamage(attacker, target, Attack.SEDUCTION, critical);
	
		float auraDamage = damage * target.getLustLevel().getAuraDamagePercentage();
		float lustDamage = damage - auraDamage;
		
		if(attacker.isPlayer()) {
			combatStringBuilder.append(UtilText.parse(target,
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
			combatStringBuilder.append(UtilText.parse(attacker,
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
		
		if(attacker.isPlayer()) {
			combatStringBuilder.append(endCombatTurn(true));
		}
		
		if(attacker.isPlayer()) {
			playerActionText = "Seduction";
			playerTurnText = combatStringBuilder.toString();
		}
	}

	private static String getSeductionAttackDescription() {
		if(Main.game.getPlayer().isFeminine()) {
			return UtilText.parse(opponent,
					UtilText.returnStringAtRandom(
					"You blow a kiss at [npc.name] and wink suggestively at [npc.herHim].",
					"Biting your lip and putting on your most smouldering look, you run your hands slowly up your inner thighs.",
					"As you give [npc.name] your most innocent look, you blow [npc.herHim] a little kiss.",
					"Turning around, you let out a playful giggle as you give your [pc.ass+] a slap.",
					"You slowly run your hands up the length of your body, before pouting at [npc.name]."));
			
		} else {
			return UtilText.parse(opponent,
					UtilText.returnStringAtRandom(
					"You blow a kiss at [npc.name] and wink suggestively at [npc.herHim].",
					"Smiling confidently at [npc.name], you slowly run your hands up your inner thighs.",
					"As you give [npc.name] your most seductive look, you blow [npc.herHim] a little kiss.",
					"Turning around, you let out a playful laugh as you give your [pc.ass+] a slap.",
					"You try to look as commanding as possible as you smirk playfully at [npc.name]."));
		}
	}

	private static void attackSpell(GameCharacter attacker, GameCharacter target, Spell spell, int level) {

		boolean critical = isCriticalHit(attacker);

		combatStringBuilder = new StringBuilder();

		combatStringBuilder.append(getPregnancyProtectionText(attacker, target));
		
		combatStringBuilder.append(spell.applyEffect(attacker, target, level, true, critical));
		
		if(critical && attacker.hasTraitActivated(Perk.ARCANE_CRITICALS)) {//TODO description
			target.addStatusEffect(StatusEffect.ARCANE_WEAKNESS, 1);
		}
		
		if(attacker.isPlayer()) {
			combatStringBuilder.append(endCombatTurn(true));
		}
		
		if(attacker.isPlayer()) {
			playerActionText = Util.capitaliseSentence(spell.getName());
			playerTurnText = combatStringBuilder.toString();
		}
	}

	private static void attackSpecialAttack(GameCharacter attacker, GameCharacter target, SpecialAttack specialAttack) {

		boolean critical = isCriticalHit(attacker);

		combatStringBuilder = new StringBuilder();
		
		combatStringBuilder.append(getPregnancyProtectionText(attacker, target));
		
		combatStringBuilder.append(specialAttack.applyEffect(attacker, target, true, critical));

		if(attacker.isPlayer()) {
			combatStringBuilder.append(endCombatTurn(true));
		}

		if(attacker.isPlayer()) {
			playerActionText = Util.capitaliseSentence(specialAttack.getName());
			playerTurnText = combatStringBuilder.toString();
		}
	}

	private static void attackWait(GameCharacter attacker, GameCharacter target) {
		combatStringBuilder = new StringBuilder(
				UtilText.parse(opponent,
				"<p>"
					+ "You decide not to make a move, and instead try to brace yourself as best as possible against [npc.name]'s next attack."
				+ "</p>"));

		if(attacker.isPlayer()) {
			combatStringBuilder.append(endCombatTurn(true));
		}

		playerActionText = "Wait";
		playerTurnText = combatStringBuilder.toString();
	}
	
	private static String submit(GameCharacter attacker, GameCharacter target) {
		combatStringBuilder = new StringBuilder(
				"<p>" + "You kneel in front of " + opponent.getName("the") + ", lowering your head in submission. " + UtilText.parsePlayerSpeech("I don't want to fight any more, I submit.") + "</p>");

		if(attacker.isPlayer()) {
			combatStringBuilder.append(endCombatTurn(true));
		}

		return combatStringBuilder.toString();
	}

	private static void escape(GameCharacter attacker, GameCharacter target) {
		combatStringBuilder = new StringBuilder("<p>");

		if (Util.random.nextInt(100) < escapeChance) {
			escaped = true;
			combatStringBuilder.append("You got away!");
		} else {
			combatStringBuilder.append("You failed to escape!");
			if(attacker.isPlayer()) {
				combatStringBuilder.append(endCombatTurn(true));
			}
		}
		combatStringBuilder.append("</p>");

		playerActionText = "Escape";
		playerTurnText = combatStringBuilder.toString();
	}

	// Calculations for enemy attack:
	public static void attackEnemy() {
		if(opponent.getHealth() <= 0 || opponent.getMana() <= 0) {
			opponentActionText = "<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Defeated!</span>";
			opponentTurnText = "<p>"
								+opponent.getName("The")+" doesn't have the strength to continue fighting...</br>"
								+ "<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>You are victorious!</span>"
								+ "</p>";
			
		} else if (Main.game.getPlayer().getHealth() <= 0 || Main.game.getPlayer().getMana() <= 0) {
			
			playerActionText = "<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Defeated!</span>";
			playerTurnText += "<p>"
								+"You don't have the strength to continue fighting...</br>"
								+ "<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>You have been defeated!</span>"
								+ "</p>";
			
		} else if(escaped) {
			opponentActionText = "Fails to catch you";
			opponentTurnText = opponent.getName("The")+" tries to block your escape, but fails...";
			
		} else if(opponent.hasStatusEffect(StatusEffect.WITCH_SEAL)) { //TODO replace with generic isStunned() method
			opponentActionText = "Stunned";
			opponentTurnText = UtilText.parse(opponent, "[npc.Name] is unable to make a move!")+endCombatTurn(false);
			
		} else {
			// Calculate what attack to use based on NPC preference:
			Attack opponentAttack = opponent.attackType();
			
			switch(opponentAttack){
				case DUAL:
					opponentActionText = "Dual strike";
					break;
					
				case ESCAPE:
					opponentActionText = "Escape";
					break;
					
				case MAIN:
					attackMelee(opponent, Main.game.getPlayer());
					
					opponentActionText = "Main attack";
					break;
					
				case NONE:
					opponentActionText = "Idle";
					break;
					
				case OFFHAND:
					attackOffhand(opponent, Main.game.getPlayer());
					
					opponentActionText = "Offhand attack";
					break;
					
				case SEDUCTION:
					attackSeduction(opponent, Main.game.getPlayer());
					
					opponentActionText = "Seduction";
					break;
					
				case SPECIAL_ATTACK:
					SpecialAttack specialAttack = opponent.getSpecialAttacks().get(Util.random.nextInt(opponent.getSpecialAttacks().size()));
//					combatStringBuilder = new StringBuilder(specialAttack.applyEffect(opponent, Main.game.getPlayer(), true, critical));
					attackSpecialAttack(opponent, Main.game.getPlayer(), specialAttack);

					opponentActionText = Util.capitaliseSentence(specialAttack.getName());
					break;
					
				case SPELL:
					Spell spell = opponent.getSpell();
//					combatStringBuilder = new StringBuilder(spell.applyEffect(opponent, Main.game.getPlayer(), opponent.getLevel(), true, critical));
					attackSpell(opponent, Main.game.getPlayer(), spell, opponent.getLevel());
					
					opponentActionText = Util.capitaliseSentence(spell.getName());
					break;
					
				case USE_ITEM:
					opponentActionText = "";
					break;
					
				default:
					break;
				
			}
			
			combatStringBuilder.append(endCombatTurn(false));
			
			if(opponent.getHealth() <= 0 || opponent.getMana() <= 0) {

				opponentActionText = "<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Defeated!</span>";
				opponentTurnText = combatStringBuilder.toString()
						+"<p>"
						+opponent.getName("The")+" doesn't have the strength to continue fighting...</br>"
						+ "<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>You are victorious!</span>"
						+ "</p>";
				
			}	else{
				opponentTurnText = combatStringBuilder.toString();
			}
		}
		
		turn++;
	}

	private static StringBuilder endTurnStatusEffectText = new StringBuilder();

	private static String endCombatTurn(boolean playersTurnEnd) { // TODO test
		endTurnStatusEffectText = new StringBuilder();
		List<StatusEffect> effectsToRemove = new ArrayList<>();

		// Opponent's turn
		if (!playersTurnEnd) {
			for (StatusEffect se : opponent.getStatusEffects()) {
				if (se.isCombatEffect()) {
					endTurnStatusEffectText.append("<p>" + "<b style='color: " + se.getColour().toWebHexString() + "'>" + Util.capitaliseSentence(se.getName(opponent)) + "</b> - " + se.applyEffect(opponent, 0) + "</p>");
					if (!se.isBeneficial())
						opponent.setStatusEffectDuration(se, opponent.getStatusEffectDuration(se) - 1);
					if (opponent.getStatusEffectDuration(se) <= 0)
						effectsToRemove.add(se);
				}
			}
			for (StatusEffect se : effectsToRemove)
				opponent.removeStatusEffect(se);

			// Remove any status effects from the player that are beneficial:
			effectsToRemove.clear();
			for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
				if (se.isCombatEffect()) {
					if (se.isBeneficial())
						Main.game.getPlayer().setStatusEffectDuration(se, Main.game.getPlayer().getStatusEffectDuration(se) - 1);
					if (Main.game.getPlayer().getStatusEffectDuration(se) <= 0)
						effectsToRemove.add(se);
				}
			}
			for (StatusEffect se : effectsToRemove)
				Main.game.getPlayer().removeStatusEffect(se);

			// Player's turn:
		} else {
			for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
				if (se.isCombatEffect()) {
					endTurnStatusEffectText.append("<p>" + "<b style='color: " + se.getColour().toWebHexString() + "'>" + Util.capitaliseSentence(se.getName(Main.game.getPlayer())) + "</b> - " + se.applyEffect(Main.game.getPlayer(), 0) + "</p>");
					if (!se.isBeneficial())
						Main.game.getPlayer().setStatusEffectDuration(se, Main.game.getPlayer().getStatusEffectDuration(se) - 1);
					if (Main.game.getPlayer().getStatusEffectDuration(se) <= 0)
						effectsToRemove.add(se);
				}
			}
			for (StatusEffect se : effectsToRemove)
				Main.game.getPlayer().removeStatusEffect(se);

			// Remove any status effects from the opponent that are beneficial:
			effectsToRemove.clear();
			for (StatusEffect se : opponent.getStatusEffects()) {
				if (se.isCombatEffect()) {
					if (se.isBeneficial())
						opponent.setStatusEffectDuration(se, opponent.getStatusEffectDuration(se) - 1);
					if (opponent.getStatusEffectDuration(se) <= 0)
						effectsToRemove.add(se);
				}
			}
			for (StatusEffect se : effectsToRemove)
				opponent.removeStatusEffect(se);
		}

		return endTurnStatusEffectText.toString();
	}

	// Utility methods:
	private static String getCombatLabel() {
		if(turn==0)
			return "Combat - Start";
		else
			return "Combat - Turn "+turn;
	}

	private static StringBuilder attackDescriptionSB = new StringBuilder();
	
	private static String getMainAttackDescription() {
		attackDescriptionSB = new StringBuilder();
		
		attackDescriptionSB.append("Strike out at " + opponent.getName("the") + "!</br></br>");
		
		if (Main.game.getPlayer().getMainWeapon() == null) {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), opponent, Attack.MAIN) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), opponent, Attack.MAIN) + "</b>" + " <b style='color:"
					+ Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString() + ";'>" + Util.capitaliseSentence(DamageType.PHYSICAL.getName()) + "</b> <b>damage</b></br></br>");
		} else {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), opponent, Attack.MAIN) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), opponent, Attack.MAIN) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getMainWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getMainWeapon().getDamageType().getName())
					+ "</b> <b>damage</b></br></br>");
		}

		attackDescriptionSB.append("Main and offhand attacks <b style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>always hit</b>.");
		
		return attackDescriptionSB.toString();
	}
	
	private static String getOffhandAttackDescription() {
		attackDescriptionSB = new StringBuilder();
		
		attackDescriptionSB.append("Strike out at " + opponent.getName("the") + "!</br></br>");
		
		if (Main.game.getPlayer().getOffhandWeapon() == null) {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), opponent, Attack.OFFHAND) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), opponent, Attack.OFFHAND) + "</b>" + " <b style='color:"
					+ Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString() + ";'>" + Util.capitaliseSentence(DamageType.PHYSICAL.getName()) + "</b> <b>damage</b></br></br>");
		} else {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), opponent, Attack.OFFHAND) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), opponent, Attack.OFFHAND) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getOffhandWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getOffhandWeapon().getDamageType().getName())
					+ "</b> <b>damage</b></br></br>");
		}

		attackDescriptionSB.append("Main and offhand attacks <b style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>always hit</b>.");
		
		return attackDescriptionSB.toString();
	}
	
	private static String getDualAttackDescription() {
		attackDescriptionSB = new StringBuilder();
		
		attackDescriptionSB.append("Give " + opponent.getName("the") + " everything you've got!</br></br>");
		
		if (Main.game.getPlayer().getMainWeapon() == null) {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), opponent, Attack.MAIN) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), opponent, Attack.MAIN) + "</b>" + " <b style='color:"
					+ Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString() + ";'>" + Util.capitaliseSentence(DamageType.PHYSICAL.getName()) + "</b> <b>damage</b></br>");
		} else {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), opponent, Attack.MAIN) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), opponent, Attack.MAIN) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getMainWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getMainWeapon().getDamageType().getName())
					+ "</b> <b>damage</b></br>");
		}
		
		if (Main.game.getPlayer().getOffhandWeapon() == null) {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), opponent, Attack.OFFHAND) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), opponent, Attack.OFFHAND) + "</b>" + " <b style='color:"
					+ Colour.DAMAGE_TYPE_PHYSICAL.toWebHexString() + ";'>" + Util.capitaliseSentence(DamageType.PHYSICAL.getName()) + "</b> <b>damage</b></br>");
		} else {
			attackDescriptionSB.append("<b>" + Attack.getMinimumDamage(Main.game.getPlayer(), opponent, Attack.OFFHAND) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), opponent, Attack.OFFHAND) + "</b>" + " <b style='color:"
					+ Main.game.getPlayer().getOffhandWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getPlayer().getOffhandWeapon().getDamageType().getName())
					+ "</b> <b>damage</b></br>");
		}

		attackDescriptionSB.append("You have a <b>50%</b> <b style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>chance to hit</b>.");
		
		return attackDescriptionSB.toString();
	}

	private static String getTeaseDescription() {

		return "Attempt to seduce " + opponent.getName("the") + ".</br></br>"

				+ "<b>"
				+ Attack.getMinimumDamage(Main.game.getPlayer(), opponent, Attack.SEDUCTION) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), opponent, Attack.SEDUCTION) + "</b>"
				+ " <b style='color:"+ Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(DamageType.LUST.getName()) + "</b> <b>damage</b></br></br>"

				+ "Seduction attacks <b style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>always hit</b>.";
	}

	private static String getSpellDescription(Spell spell, AbstractWeapon source) {
		return "Cast <b>Level " + Main.game.getPlayer().getLevel() + "</b> <b style='color:" + spell.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(spell.getName()) + "</b></br></br>"

				+ "<b>" + spell.getMinimumDamage(Main.game.getPlayer(), opponent, Main.game.getPlayer().getLevel()) + " - " + spell.getMaximumDamage(Main.game.getPlayer(), opponent, Main.game.getPlayer().getLevel()) + "</b>" + " <b style='color:"
				+ spell.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(spell.getDamageType().getName()) + "</b> <b>damage</b></br></br>"
				
				+ "<b>" + spell.getMinimumCost(Main.game.getPlayer(), Main.game.getPlayer().getLevel()) + " - " + spell.getMaximumCost(Main.game.getPlayer(), Main.game.getPlayer().getLevel()) + "</b>" + " <b style='color:"
				+ Colour.ATTRIBUTE_MANA.toWebHexString() + ";'>aura</b> <b>cost</b></br></br>";
	}

	private static String getSpecialAttackDescription(SpecialAttack specialAttack) {

		return "Use your <b style='color:" + specialAttack.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(specialAttack.getName()) + "</b> special attack.</br></br>"

				+ "<b>" + specialAttack.getMinimumDamage(Main.game.getPlayer(), opponent) + " - " + specialAttack.getMaximumDamage(Main.game.getPlayer(), opponent) + "</b>" + " <b style='color:"
				+ specialAttack.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(specialAttack.getDamageType().getName()) + "</b> <b>damage</b></br></br>"


				+ "<b>" + specialAttack.getMinimumCost(Main.game.getPlayer()) + " - " + specialAttack.getMaximumCost(Main.game.getPlayer()) + "</b>" + " <b style='color:"
				+ Colour.ATTRIBUTE_HEALTH.toWebHexString() + ";'>energy</b> <b>cost</b></br></br>";
	}

	public static GameCharacter getOpponent() {
		return opponent;
	}

	public static NPC getTargetedCombatant() {
		return targetedCombatant;
	}

	public static void setTargetedCombatant(NPC targetedCombatant) {
		Combat.targetedCombatant = targetedCombatant;
	}

	public static AbstractItem getItem() {
		return item;
	}

	public static void setItem(AbstractItem item) {
		Combat.item = item;
	}

	public static String getPostCombatString() {
		return postCombatString;
	}

	public static Attack getPreviousAction() {
		return previousAction;
	}

	public static void setPreviousAction(Attack previousAction) {
		Combat.previousAction = previousAction;
	}

	public static String getPlayerTurnText() {
		return playerTurnText;
	}

	public static void setPlayerTurnText(String playerTurnText) {
		Combat.playerTurnText = playerTurnText;
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
}
