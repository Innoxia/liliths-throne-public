package com.base.game.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.base.game.character.GameCharacter;
import com.base.game.character.QuestLine;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.attributes.FitnessLevel;
import com.base.game.character.attributes.IntelligenceLevel;
import com.base.game.character.attributes.StrengthLevel;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.npc.NPC;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.GenericDialogue;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.dialogue.utils.InventoryDialogue;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.Rarity;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.inventory.weapon.AbstractWeapon;
import com.base.game.inventory.weapon.AbstractWeaponType;
import com.base.main.Main;
import com.base.rendering.RenderingEngine;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * Singleton enforced by Enum.</br>
 * Call initialiseCombat() before using.
 *
 * @since 0.1.0
 * @version 0.1.84
 * @author Innoxia
 */
public enum Combat {
	COMBAT;

	// TODO Make sure your status effects end before you take your turn, enemy's status effects end at the start of their turn
	// Also, end combat it enemy drops to 0 health/mana/stamina on their turn from combat effects

	private static NPC opponent;
	private static String combatText = "", playerActionText = "", opponentActionText = "", playerTurnText = "", opponentTurnText = "";
	private static int escapeChance = 0, turn = 0;
	private static float renderedOpponentHealthValue, renderedOpponentManaValue, renderedOpponentStaminaValue, renderedPlayerHealthValue, renderedPlayerManaValue, renderedPlayerStaminaValue;
	private static boolean escaped = false;
	private static StringBuilder combatStringBuilder = new StringBuilder("");
	private static StringBuilder descriptionStringBuilder = new StringBuilder("");

	// For internal calculations
	private static boolean critical = false;

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
	 * @param escapeChance
	 *            Chance of an escape succeeding (out of 100)
	 */
	public void initialiseCombat(NPC npc, int escapePercentage) {
		escaped = false;
		opponent = npc;
		critical = false;
		
		opponent.setFoughtPlayerCount(opponent.getFoughtPlayerCount()+1);

		previousAction = Attack.NONE;

		escapeChance = npc.getEscapeChance();
		if (Main.game.getPlayer().hasPerk(Perk.RUNNER))
			escapeChance *= 1.5f;
		else if (Main.game.getPlayer().hasPerk(Perk.RUNNER_2))
			escapeChance *= 2f;
		
		turn = 0;
		
		combatText = "";
		playerActionText = "Your action";
		opponentActionText = opponent.getName("The")+"'s action";
		playerTurnText = "You prepare to make a move.";
		opponentTurnText = opponent.getName("The")+" prepares to make a move.";

		renderedOpponentHealthValue = npc.getHealth();
		renderedOpponentManaValue = npc.getMana();
		renderedOpponentStaminaValue = npc.getStamina();

		renderedPlayerHealthValue = Main.game.getPlayer().getHealth();
		renderedPlayerManaValue = Main.game.getPlayer().getMana();
		renderedPlayerStaminaValue = Main.game.getPlayer().getStamina();

		Main.game.setInCombat(true);

		// Set to opponent, as when the inventory opens, it switches to the opposite (in MainController openInventory())
		RenderingEngine.ENGINE.setCharactersInventoryToRender(opponent);
		
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
				postCombatStringBuilder.append(" and <b style='color: " + com.base.utils.Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b><b>" + opponent.getLootMoney() + "</b>!</h6>");
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
				
				if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
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
							+ Main.game.getPlayer().incrementQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)));
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
			postCombatStringBuilder.append("<h6 style='text-align:center;'>You <b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>lost</b>"
					+ " <b style='color: " + com.base.utils.Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b><b>" + (Main.game.getPlayer().getMoney()==0?money:opponent.getLootMoney()/2) + "</b>!</h6>");

			opponent.setWonCombatCount(opponent.getWonCombatCount()+1);
		}

		RenderingEngine.ENGINE.setCharactersInventoryToRender(Main.game.getPlayer());

		Main.game.setInCombat(false);

		// Sort out effects after combat:
		if (Main.game.getPlayer().getHealth() == 0)
			Main.game.getPlayer().setHealth(5);
		if (Main.game.getPlayer().getMana() == 0)
			Main.game.getPlayer().setMana(5);
		if (Main.game.getPlayer().getStamina() == 0)
			Main.game.getPlayer().setStamina(5);
		
		// Reset opponent resources to max:
		opponent.setMana(opponent.getAttributeValue(Attribute.MANA_MAXIMUM));
		opponent.setHealth(opponent.getAttributeValue(Attribute.HEALTH_MAXIMUM));
		opponent.setStamina(opponent.getAttributeValue(Attribute.STAMINA_MAXIMUM));
		
		postCombatString = postCombatStringBuilder.toString();
		
		Main.game.getTextStartStringBuilder().append(postCombatString);
	}

	private static String npcStatus() {
		descriptionStringBuilder = new StringBuilder("<div class='combat-header-info'>");

		// PLAYER INFO:
		descriptionStringBuilder.append("<div class='combat-display left'>");
		// +(Main.game.getCurrentDialogueNode()==ENEMY_ATTACK?"
		// style='background-color:#555;'":"")
		// + ">");

		// Display Name and level:
		descriptionStringBuilder.append("<div class='combat-inner-container'>"
					+ "<div class='combat-container name'>"
						+ "<div class='combat-container'>"
							+ "<p class='combatant-title name' style='color:" + Femininity.valueOf(Main.game.getPlayer().getFemininity()).getColour().toWebHexString() + ";'>" 
								+ "<b>" + Util.capitaliseSentence(Main.game.getPlayer().getName()) + "</b>"
							+ "</p>"
						+ "</div>"
						+ "<div class='combat-container'>"
							+ "<p class='combatant-title level'>"
								+ "<b>Level " + Main.game.getPlayer().getLevel() + "</b></br>"
								+(Main.game.getPlayer().getRaceStage().getName()!=""
									?"<b style='color:"+Main.game.getPlayer().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(Main.game.getPlayer().getRaceStage().getName())+"</b> ":"")
								+ "<b style='color:"+Main.game.getPlayer().getRace().getColour().toWebHexString()+";'>"
								+ (Main.game.getPlayer().isFeminine()?Util.capitaliseSentence(Main.game.getPlayer().getRace().getSingularFemaleName()):Util.capitaliseSentence(Main.game.getPlayer().getRace().getSingularMaleName()))
								+ "</b>"
							+ "</p>"
						+ "</div>"
						+ "<div class='overlay no-pointer' id='COMBAT_PLAYER_ATTRIBUTES'></div>"
					+ "</div>"
				+ "</div>");

		// Attributes:
		descriptionStringBuilder.append(
				"<div class='combat-inner-container'>"
					+ "<div class='combat-container attribute'>"
						+ "<div class='combat-resource-icon'>"
							+ StrengthLevel.getStrengthLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
						+ "<div class='combat-resource-number'>"
							+ "<b style='color:" + Attribute.STRENGTH.getColour().toWebHexString() + ";'>" + (int) Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH) + "</b>"
						+ "</div>"
						+ "<div class='overlay no-pointer' id='COMBAT_PLAYER_" + Attribute.STRENGTH + "'></div>"
					+ "</div>"
	
					+ "<div class='combat-container attribute'>"
						+ "<div class='combat-resource-icon'>"
							+ IntelligenceLevel.getIntelligenceLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
						+ "<div class='combat-resource-number'>"
						+ "<b style='color:" + Attribute.INTELLIGENCE.getColour().toWebHexString() + ";'>" + (int) Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE) + "</b>"
						+ "</div>"
						+ "<div class='overlay no-pointer' id='COMBAT_PLAYER_" + Attribute.INTELLIGENCE + "'></div>"
					+ "</div>"
	
					+ "<div class='combat-container attribute'>"
						+ "<div class='combat-resource-icon'>"
							+ FitnessLevel.getFitnessLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.FITNESS)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
						+ "<div class='combat-resource-number'>"
						+ "<b style='color:" + Attribute.FITNESS.getColour().toWebHexString() + ";'>" + (int) Main.game.getPlayer().getAttributeValue(Attribute.FITNESS) + "</b>"
						+ "</div>"
						+ "<div class='overlay no-pointer' id='COMBAT_PLAYER_" + Attribute.FITNESS + "'></div>"
					+ "</div>"
	
					+ "<div class='combat-container attribute'>"
						+ "<div class='combat-resource-icon'>"
							+ CorruptionLevel.getCorruptionLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
						+ "<div class='combat-resource-number'>"
						+ "<b style='color:" + Attribute.CORRUPTION.getColour().toWebHexString() + ";'>" + (int) Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION) + "</b>"
						+ "</div>"
						+ "<div class='overlay no-pointer' id='COMBAT_PLAYER_" + Attribute.CORRUPTION + "'></div>"
					+ "</div>"
				+ "</div>");

		// Display health, willpower and stamina:
		descriptionStringBuilder.append("<div class='combat-inner-container'>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.HEALTH_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((Main.game.getPlayer().getHealth() / Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM)) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_HEALTH.toWebHexString() + "; border-radius: 2px;'></div>"
				+ "</div>" + "<div class='combat-resource-number' style='color:"
				+ (renderedPlayerHealthValue < Main.game.getPlayer().getHealth() ? (Colour.CLOTHING_GREEN.toWebHexString()) : (renderedPlayerHealthValue > Main.game.getPlayer().getHealth() ? (Colour.CLOTHING_RED.toWebHexString()) : "default")) + ";'>"
				+ (int) Math.ceil(Main.game.getPlayer().getHealth()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_PLAYER_" + Attribute.HEALTH_MAXIMUM + "'></div>" + "</div>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.MANA_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((Main.game.getPlayer().getMana() / Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM)) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_MANA.toWebHexString() + "; border-radius: 2px;'></div>"
				+ "</div>" + "<div class='combat-resource-number' style='color:"
				+ (renderedPlayerManaValue < Main.game.getPlayer().getMana() ? (Colour.CLOTHING_GREEN.toWebHexString()) : (renderedPlayerManaValue > Main.game.getPlayer().getMana() ? (Colour.CLOTHING_RED.toWebHexString()) : "default")) + ";'>"
				+ (int) Math.ceil(Main.game.getPlayer().getMana()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_PLAYER_" + Attribute.MANA_MAXIMUM + "'></div>" + "</div>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.STAMINA_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((Main.game.getPlayer().getStamina() / Main.game.getPlayer().getAttributeValue(Attribute.STAMINA_MAXIMUM)) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_FITNESS.toWebHexString()
				+ "; border-radius: 2px;'></div>" + "</div>" + "<div class='combat-resource-number' style='color:"
				+ (renderedPlayerStaminaValue < Main.game.getPlayer().getStamina() ? (Colour.CLOTHING_GREEN.toWebHexString()) : (renderedPlayerStaminaValue > Main.game.getPlayer().getStamina() ? (Colour.CLOTHING_RED.toWebHexString()) : "default")) + ";'>"
				+ (int) Math.ceil(Main.game.getPlayer().getStamina()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_PLAYER_" + Attribute.STAMINA_MAXIMUM + "'></div>" + "</div>"

				+ "</div>");

		// Display status effects:
		descriptionStringBuilder.append("<div class='combat-inner-container status-effects'>");
		boolean statusEffectFound=false;
		for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
			if (se.isCombatEffect() && se.renderInEffectsPanel()){
				descriptionStringBuilder.append("<div class='combat-status-effect" + (!se.isBeneficial() ? " negativeCombat" : " positiveCombat") + "'>"
													+ se.getSVGString(Main.game.getPlayer()) + "<div class='overlay no-pointer' id='COMBAT_PLAYER_SE_" + se + "'></div>" + "</div>");
				statusEffectFound = true;
			}
		}
		for (SpecialAttack sa : Main.game.getPlayer().getSpecialAttacks()) {
			descriptionStringBuilder.append(
					"<div class='combat-status-effect'>"
							+ sa.getSVGString()
							+ "<div class='overlay' id='COMBAT_PLAYER_SA_" + sa + "'></div>"
					+ "</div>");
			statusEffectFound = true;
		}
		
		if (Main.game.getPlayer().getMainWeapon() != null) {
			for (Spell s : Main.game.getPlayer().getMainWeapon().getSpells()) {
				descriptionStringBuilder.append(
						"<div class='combat-status-effect'>" 
								+ s.getSVGString() 
								+ "<div class='overlay' id='COMBAT_PLAYER_SPELL_MAIN_" + s + "'></div>"
						+ "</div>");
				statusEffectFound = true;
			}
		}
		
		if (Main.game.getPlayer().getOffhandWeapon() != null) {
			for (Spell s : Main.game.getPlayer().getOffhandWeapon().getSpells()) {
				descriptionStringBuilder.append(
						"<div class='combat-status-effect'>"
								+ s.getSVGString() 
								+ "<div class='overlay' id='COMBAT_PLAYER_SPELL_OFFHAND_" + s + "'></div>" 
						+ "</div>");
				statusEffectFound = true;
			}
		}
		if(!statusEffectFound)
			descriptionStringBuilder.append("<p style='text-align:center; color:"+Colour.TEXT_GREY.toWebHexString()+";'><b>No combat effects</b></p>");
			
		descriptionStringBuilder.append("</div>");

		descriptionStringBuilder.append("</div>");

		// Display description:
		descriptionStringBuilder.append("<div class='combat-description'>" + "<p>" + opponent.getCombatDescription() + "</p>" + "</div>");

		// OPPONENT INFO:
		descriptionStringBuilder.append("<div class='combat-display left'>");

		// Display Name and level:
		descriptionStringBuilder.append("<div class='combat-inner-container'>"
				+ "<div class='combat-container name'>"
					+ "<div class='combat-container'>"
						+ "<p class='combatant-title name' style='color:" + Femininity.valueOf(opponent.getFemininity()).getColour().toWebHexString() + ";'>" 
							+ "<b>" + Util.capitaliseSentence(opponent.getName()) + "</b>"
						+ "</p>"
					+ "</div>"
					+ "<div class='combat-container'>"
						+ "<p class='combatant-title level'>"
							+ (opponent.getLevel() - Main.game.getPlayer().getLevel() <= -3 ? "<b style='color: " + Colour.GENERIC_GOOD.toWebHexString() + ";'>"
									: (opponent.getLevel() - Main.game.getPlayer().getLevel() >= 3 ? "<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>" : "<b>"))
							+ "Level " + opponent.getLevel() +"</b>"
							+ "</br>"
							+(opponent.getRaceStage().getName()!=""
								?"<b style='color:"+opponent.getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(opponent.getRaceStage().getName())+"</b> ":"")
							+ "<b style='color:"+opponent.getRace().getColour().toWebHexString()+";'>"
							+ (opponent.isFeminine()?Util.capitaliseSentence(opponent.getRace().getSingularFemaleName()):Util.capitaliseSentence(opponent.getRace().getSingularMaleName()))
							+ "</b>"
						+ "</p>"
					+ "</div>"
					+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_ATTRIBUTES'></div>" 
				+ "</div>"
				+ "</div>");

		// Attributes:
		descriptionStringBuilder.append(
				"<div class='combat-inner-container'>"
						+ "<div class='combat-container attribute'>"
							+ "<div class='combat-resource-icon'>" + StrengthLevel.getStrengthLevelFromValue(opponent.getAttributeValue(Attribute.STRENGTH)).getRelatedStatusEffect().getSVGString(opponent) + "</div>"
							+ "<div class='combat-resource-number'>"
								+ "<b style='color:" + Attribute.STRENGTH.getColour().toWebHexString() + ";'>" + (int) opponent.getAttributeValue(Attribute.STRENGTH) + "</b>"
							+ "</div>"
							+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.STRENGTH + "'></div>"
						+ "</div>"
		
						+ "<div class='combat-container attribute'>"
							+ "<div class='combat-resource-icon'>" + IntelligenceLevel.getIntelligenceLevelFromValue(opponent.getAttributeValue(Attribute.INTELLIGENCE)).getRelatedStatusEffect().getSVGString(opponent) + "</div>"
							+ "<div class='combat-resource-number'>"
							+ "<b style='color:" + Attribute.INTELLIGENCE.getColour().toWebHexString() + ";'>" + (int) opponent.getAttributeValue(Attribute.INTELLIGENCE) + "</b>"
							+ "</div>"
							+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.INTELLIGENCE + "'></div>"
						+ "</div>"
		
						+ "<div class='combat-container attribute'>"
							+ "<div class='combat-resource-icon'>" + FitnessLevel.getFitnessLevelFromValue(opponent.getAttributeValue(Attribute.FITNESS)).getRelatedStatusEffect().getSVGString(opponent) + "</div>"
							+ "<div class='combat-resource-number'>"
							+ "<b style='color:" + Attribute.FITNESS.getColour().toWebHexString() + ";'>" + (int) opponent.getAttributeValue(Attribute.FITNESS) + "</b>"
							+ "</div>"
							+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.FITNESS + "'></div>"
						+ "</div>"
		
						+ "<div class='combat-container attribute'>"
							+ "<div class='combat-resource-icon'>" + CorruptionLevel.getCorruptionLevelFromValue(opponent.getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(opponent) + "</div>"
							+ "<div class='combat-resource-number'>"
							+ "<b style='color:" + Attribute.CORRUPTION.getColour().toWebHexString() + ";'>" + (int) opponent.getAttributeValue(Attribute.CORRUPTION) + "</b>"
							+ "</div>"
							+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.CORRUPTION + "'></div>"
						+ "</div>"
					+ "</div>");

		// Display health, willpower and stamina:
		descriptionStringBuilder.append("<div class='combat-inner-container'>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.HEALTH_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((opponent.getHealth() / opponent.getAttributeValue(Attribute.HEALTH_MAXIMUM)) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_HEALTH.toWebHexString() + "; border-radius: 2px;'></div>" + "</div>"
				+ "<div class='combat-resource-number' style='color:"
				+ (renderedOpponentHealthValue < opponent.getHealth() ? (Colour.CLOTHING_GREEN.toWebHexString()) : (renderedOpponentHealthValue > opponent.getHealth() ? (Colour.CLOTHING_RED.toWebHexString()) : "default")) + ";'>"
				+ (int) Math.ceil(opponent.getHealth()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.HEALTH_MAXIMUM + "'></div>" + "</div>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.MANA_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((opponent.getMana() / opponent.getAttributeValue(Attribute.MANA_MAXIMUM)) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_MANA.toWebHexString() + "; border-radius: 2px;'></div>" + "</div>"
				+ "<div class='combat-resource-number' style='color:" + (renderedOpponentManaValue < opponent.getMana() ? (Colour.CLOTHING_GREEN.toWebHexString()) : (renderedOpponentManaValue > opponent.getMana() ? (Colour.CLOTHING_RED.toWebHexString()) : "default"))
				+ ";'>" + (int) Math.ceil(opponent.getMana()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.MANA_MAXIMUM + "'></div>" + "</div>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.STAMINA_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((opponent.getStamina() / opponent.getAttributeValue(Attribute.STAMINA_MAXIMUM)) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_FITNESS.toWebHexString() + "; border-radius: 2px;'></div>" + "</div>"
				+ "<div class='combat-resource-number' style='color:"
				+ (renderedOpponentStaminaValue < opponent.getStamina() ? (Colour.CLOTHING_GREEN.toWebHexString()) : (renderedOpponentStaminaValue > opponent.getStamina() ? (Colour.CLOTHING_RED.toWebHexString()) : "default")) + ";'>"
				+ (int) Math.ceil(opponent.getStamina()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.STAMINA_MAXIMUM + "'></div>" + "</div>"

				+ "</div>");

		// Display status effects:
		descriptionStringBuilder.append("<div class='combat-inner-container status-effects'>");
//		if (Main.game.getPlayer().hasPerk(Perk.OBSERVANT)) {
			for (Perk p : opponent.getPerks()) {
				descriptionStringBuilder.append("<div class='combat-status-effect'>" + p.getSVGString() + "<div class='overlay no-pointer' id='PERK_COMBAT_" + p + "'></div>" + "</div>");
			}
			for (Fetish f : opponent.getFetishes()) {
				descriptionStringBuilder.append("<div class='combat-status-effect'>" + f.getSVGString() + "<div class='overlay no-pointer' id='FETISH_COMBAT_" + f + "'></div>" + "</div>");
			}
			for (StatusEffect se : opponent.getStatusEffects()) {
				if (se.renderInEffectsPanel()) {
					if (se.isCombatEffect()) {
						descriptionStringBuilder.append("<div class='combat-status-effect" + (!se.isBeneficial() ? " negativeCombat" : " positiveCombat") + "'>"
									+ se.getSVGString(opponent) + "<div class='overlay no-pointer' id='SE_COMBAT_" + se + "'></div>" + "</div>");
					} else {
						descriptionStringBuilder.append(
								"<div class='combat-status-effect'>" + se.getSVGString(opponent) + "<div class='overlay no-pointer' id='SE_COMBAT_" + se + "'></div>" + "</div>");
					}
				}
			}
			for (SpecialAttack sa : opponent.getSpecialAttacks()) {
				descriptionStringBuilder.append(
						"<div class='combat-status-effect'>" + sa.getSVGString() + "<div class='overlay no-pointer' id='SA_COMBAT_" + sa + "'></div>" + "</div>");
			}
			if (opponent.getMainWeapon() != null) {
				for (Spell s : opponent.getMainWeapon().getSpells()) {
					descriptionStringBuilder
							.append("<div class='combat-status-effect'>" + s.getSVGString() + "<div class='overlay' id='SPELL_MAIN_COMBAT_" + s + "'></div>" + "</div>");
				}
			}
			if (opponent.getOffhandWeapon() != null) {
				for (Spell s : opponent.getOffhandWeapon().getSpells()) {
					descriptionStringBuilder
							.append("<div class='combat-status-effect'>" + s.getSVGString() + "<div class='overlay' id='SPELL_OFFHAND_COMBAT_" + s + "'></div>" + "</div>");
				}
			}
//		} else {
//			descriptionStringBuilder.append("<div class='combat-status-effect'>"
//												+ StatusEffect.COMBAT_HIDDEN.getSVGString(opponent) + "<div class='overlay no-pointer' id='SE_COMBAT_"+StatusEffect.COMBAT_HIDDEN+"'></div>" + "</div>");
//			for (StatusEffect se : opponent.getStatusEffects()) {
//				if (se.isCombatEffect())
//					descriptionStringBuilder
//							.append("<div class='combat-status-effect" + (!se.isBeneficial() ? " negativeCombat" : " positiveCombat") + "'>" + se.getSVGString(opponent) + "<div class='overlay no-pointer' id='SE_COMBAT_" + se + "'></div>" + "</div>");
//			}
//		}
		descriptionStringBuilder.append("</div>");

		descriptionStringBuilder.append("</div>");

		// Close containing div:
		descriptionStringBuilder.append("</div>");

		renderedOpponentHealthValue = opponent.getHealth();
		renderedOpponentManaValue = opponent.getMana();
		renderedOpponentStaminaValue = opponent.getStamina();

		renderedPlayerHealthValue = Main.game.getPlayer().getHealth();
		renderedPlayerManaValue = Main.game.getPlayer().getMana();
		renderedPlayerStaminaValue = Main.game.getPlayer().getStamina();

		return descriptionStringBuilder.toString();
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
							+ " <b style='color:"+Colour.ATTRIBUTE_MANA.toWebHexString()+";'>Willpower</b></td>"
						+ "</tr>");
			
			tempSB.append("</table></div>");
			
			return tempSB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Do something else.", ENEMY_ATTACK);
				
			} else if (Main.game.getPlayer().getSpells().size() >= index) {
				return new Response(Util.capitaliseSentence(Main.game.getPlayer().getSpells().get(index - 1).getName()),
						getSpellDescription(Main.game.getPlayer().getSpells().get(index - 1), null),
						ENEMY_ATTACK){
					@Override
					public void effects() {
						attackSpell(Main.game.getPlayer().getSpells().get(index - 1), Main.game.getPlayer().getLevel());
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
							+ " <b style='color:"+Colour.ATTRIBUTE_STAMINA.toWebHexString()+";'>Stamina</b></td>"
						+ "</tr>");
			
			tempSB.append("</table></div>");
			
			return tempSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Do something else.", ENEMY_ATTACK);
				
			} else if (Main.game.getPlayer().getSpecialAttacks().size() >= index) {
				return new Response(Util.capitaliseSentence(Main.game.getPlayer().getSpecialAttacks().get(index - 1).getName()),
						getSpecialAttackDescription(Main.game.getPlayer().getSpecialAttacks().get(index - 1)),
						ENEMY_ATTACK){
					@Override
					public void effects() {
						attackSpecialAttack(Main.game.getPlayer().getSpecialAttacks().get(index - 1));
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
		public Response getResponse(int index) {
			if (index == 1) {
				if (opponent.getHealth() <= 0 || (opponent.getMana() <= 0 && !opponent.hasPerk(Perk.INDEFATIGABLE)) || (opponent.getStamina() <= 0 && !opponent.hasPerk(Perk.INDEFATIGABLE))) {
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
			return UtilText.genderParsing(opponent, "<p>" + "Are you certain you want to <b>submit</b> to " + opponent.getName("the") + "?" + "</p>" + "<p>"
							+ "<b>This will cause you to lose the fight, allowing <herPro> to do anything <she> wants with you!</b>"
							+ "</p>");
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Submit", "Submit to " + opponent.getName("the") + ". <span style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>This will cause you to lose the current combat!</span>", SUBMIT_CONFIRM){
					@Override
					public void effects() {
						combatText = submit();
					}
				};
				
			} else if (index == 0) {
				return new Response("Cancel", "Carry on fighting.", ENEMY_ATTACK){
					@Override
					public void effects() {
						combatText = submit();
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
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
			if (index == 1) {
				if (escaped) {
					return new ResponseEffectsOnly("Escaped!", "You got away!"){
						@Override
						public void effects() {
							RenderingEngine.ENGINE.setCharactersInventoryToRender(Main.game.getPlayer());
							Main.game.setInCombat(false);
							Main.game.setContent(new Response("", "", GenericDialogue.getDefaultDialogueNoEncounter()));
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
			return "<div style='width:368px; padding:8px;margin:8px;float:left;text-align:center;'>"
						+ "<h6 style='width:100%;margin:0 0 8px 0;'>"+playerActionText+"</h6>"
						+ playerTurnText
					+ "</div>"
					
					+"<div style='width:368px; padding:8px;margin:8px;float:left;text-align:center;'>"
						+ "<h6 style='width:100%;margin:0 0 8px 0;'>"+opponentActionText+"</h6>"
						+ opponentTurnText 
					+ "</div>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (escaped) {
				if (index == 1) {
					return new ResponseEffectsOnly("Escaped!", "You got away!"){
						@Override
						public void effects() {
							RenderingEngine.ENGINE.setCharactersInventoryToRender(Main.game.getPlayer());
							Main.game.setInCombat(false);
							Main.game.setContent(new Response("", "", GenericDialogue.getDefaultDialogueNoEncounter()));
						}
					};
				} else
					return null;
				
			} else if (opponent.getHealth() <= 0
					|| (opponent.getMana() <= 0 && !opponent.hasPerk(Perk.INDEFATIGABLE))
					|| (opponent.getStamina() <= 0 && !opponent.hasPerk(Perk.INDEFATIGABLE))) {
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
				
			}  else if (Main.game.getPlayer().getHealth() <= 0 
					|| (Main.game.getPlayer().getMana() <= 0 && !Main.game.getPlayer().hasPerk(Perk.INDEFATIGABLE))
					|| (Main.game.getPlayer().getStamina() <= 0 && !Main.game.getPlayer().hasPerk(Perk.INDEFATIGABLE))) {
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
							attackMelee();
							attackEnemy();
							previousAction = Attack.MAIN;
						}
					};

				} else if (index == 2) {
					return new Response("Offhand attack", getOffhandAttackDescription(), ENEMY_ATTACK){
						@Override
						public void effects() {
							attackOffhand();
							attackEnemy();
							previousAction = Attack.OFFHAND;
						}
					};

				} else if (index == 3) {
					return new Response("Dual strike", getDualAttackDescription(), ENEMY_ATTACK){
						@Override
						public void effects() {
							attackDual();
							attackEnemy();
							previousAction = Attack.DUAL;
						}
					};

				} else if (index == 4) {
					return new Response("Seduce", getTeaseDescription(), ENEMY_ATTACK){
						@Override
						public void effects() {
							attackSeduction();
							attackEnemy();
							previousAction = Attack.SEDUCTION;
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
							attackWait();
							attackEnemy();
							previousAction = Attack.WAIT;
						}
					};

				} else if (index == 9) {
					switch (previousAction) {
						case NONE:
							return new Response("Repeat", "You have to perform an action first!", null);
	
						case MAIN:
							return new Response("Main attack", getMainAttackDescription(), ENEMY_ATTACK){
								@Override
								public void effects() {
									attackMelee();
									attackEnemy();
								}
							};
							
						case OFFHAND:
							return new Response("Offhand attack", getOffhandAttackDescription(), ENEMY_ATTACK){
								@Override
								public void effects() {
									attackOffhand();
									attackEnemy();
								}
							};
							
						case DUAL:
							return new Response("Dual strike", getDualAttackDescription(), ENEMY_ATTACK){
								@Override
								public void effects() {
									attackDual();
									attackEnemy();
								}
							};
							
						case SEDUCTION:
							return new Response("Seduce", getTeaseDescription(), ENEMY_ATTACK){
								@Override
								public void effects() {
									attackSeduction();
									attackEnemy();
								}
							};
	
						case SPELL:
							return new Response(Util.capitaliseSentence(previouslyUsedSpell.getName()), getSpellDescription(previouslyUsedSpell, null), ENEMY_ATTACK){
								@Override
								public void effects() {
									attackSpell(previouslyUsedSpell, previouslyUsedSpellLevel);
									attackEnemy();
								}
							};
	
						case SPECIAL_ATTACK:
							return new Response(Util.capitaliseSentence(previouslyUsedSpecialAttack.getName()), getSpecialAttackDescription(previouslyUsedSpecialAttack), ENEMY_ATTACK){
								@Override
								public void effects() {
									attackSpecialAttack(previouslyUsedSpecialAttack);
									attackEnemy();
								}
							};
	
						case USE_ITEM:
							return new Response("Repeat", "You have to perform an action first!", null);
							
						case WAIT:
							return new Response("Wait", "Don't perform an action.", ENEMY_ATTACK){
								@Override
								public void effects() {
									attackWait();
									attackEnemy();
								}
							};
							
						case ESCAPE:
							return new Response("Escape", "Try to escape.</br></br>You have a "+escapeChance+"% chance to get away!", ENEMY_ATTACK){
								@Override
								public void effects() {
									escape();
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
								escape();
								attackEnemy();
								previousAction = Attack.ESCAPE;
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

	// Calculations for melee attack:
	private static void attackMelee() {
		float damage = 0;

		combatStringBuilder = new StringBuilder("");

		combatStringBuilder.append(getMeleeAttackDescription());
		
		critical = isCriticalHit(Main.game.getPlayer());

		damage = Attack.calculateDamage(Main.game.getPlayer(), opponent, Attack.MAIN, critical);
		
		if(Main.game.getPlayer().getMainWeapon() == null) {
			combatStringBuilder.append("<p><b>You " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " : " hit for ") + damage + " <b style='color: "
					+ Attribute.DAMAGE_PHYSICAL.getColour().toWebHexString() + ";'>" + Attribute.DAMAGE_PHYSICAL.getName() + "</b>!</b></p>");
			
		} else {
			combatStringBuilder.append("<p><b>You " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " : " hit for ") + damage + " <b style='color: "
					+ Main.game.getPlayer().getMainWeapon().getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Main.game.getPlayer().getMainWeapon().getDamageType().getMultiplierAttribute().getName() + "</b>!</b></p>");
		}

		combatStringBuilder.append(opponent.incrementHealth(-damage));
		combatStringBuilder.append(endCombatTurn(true));
		
		playerActionText = "Main attack";
		playerTurnText = combatStringBuilder.toString();
	}

	private static String getMeleeAttackDescription() {
		String attack;
		
		if(Main.game.getPlayer().getMainWeapon()!= null) {
			attack = Main.game.getPlayer().getMainWeapon().getWeaponType().getAttackDescription(Main.game.getPlayer(), opponent);
		} else {
			attack = AbstractWeaponType.genericMeleeAttackDescription(Main.game.getPlayer(), opponent);
		}

		return (opponent.isVisiblyPregnant()
					?UtilText.genderParsing(opponent,
					"<p>"
						+ "A powerful field of arcane energy is protecting "+opponent.getName("the")+"'s pregnant belly, which doesn't even come into play, as you're very careful not to hit anywhere near <her> stomach."
					+ "</p>")
					:"")
				+"<p>"
				+ attack
				+"</p>";
	}
	
	private static void attackOffhand() {
		float damage = 0;

		combatStringBuilder = new StringBuilder("");

		combatStringBuilder.append(getOffhandDescription());
		
		critical = isCriticalHit(Main.game.getPlayer());

		damage = Attack.calculateDamage(Main.game.getPlayer(), opponent, Attack.OFFHAND, critical);
		
		Attribute damageAttribute = (Main.game.getPlayer().getOffhandWeapon() == null ? Attribute.DAMAGE_PHYSICAL : Main.game.getPlayer().getOffhandWeapon().getDamageType().getMultiplierAttribute());
		
		combatStringBuilder.append("<p>"
				+ "<b>You " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> " : "") +"hit for "+ damage + " <b style='color: "
				+ damageAttribute.getColour().toWebHexString() + ";'>"
				+ damageAttribute.getName() + "</b>!</b></p>");

		combatStringBuilder.append(opponent.incrementHealth(-damage));
		combatStringBuilder.append(endCombatTurn(true));
		
		playerActionText = "Offhand attack";
		playerTurnText = combatStringBuilder.toString();
	}

	private static String getOffhandDescription() {
		String attack;
		
		if(Main.game.getPlayer().getOffhandWeapon()!= null) {
			attack = Main.game.getPlayer().getOffhandWeapon().getWeaponType().getAttackDescription(Main.game.getPlayer(), opponent);
		} else {
			attack = AbstractWeaponType.genericMeleeAttackDescription(Main.game.getPlayer(), opponent);
		}

		return (opponent.isVisiblyPregnant()
					?UtilText.genderParsing(opponent,
					"<p>"
						+ "A powerful field of arcane energy is protecting "+opponent.getName("the")+"'s pregnant belly, which doesn't even come into play, as you're very careful not to hit anywhere near <her> stomach."
					+ "</p>")
					:"")
				+"<p>"
				+ attack
				+"</p>";
	}
	
	
	private static void attackDual() {
		float damageMain = 0, damageOffhand = 0;

		combatStringBuilder = new StringBuilder("");

		combatStringBuilder.append(getDualDescription());
		
		if (Math.random()<0.5) {
			critical = isCriticalHit(Main.game.getPlayer());

			damageMain = Attack.calculateDamage(Main.game.getPlayer(), opponent, Attack.MAIN, critical);
			damageOffhand = Attack.calculateDamage(Main.game.getPlayer(), opponent, Attack.OFFHAND, critical);
			
			Attribute damageMainAttribute = (Main.game.getPlayer().getMainWeapon() == null ? Attribute.DAMAGE_PHYSICAL : Main.game.getPlayer().getMainWeapon().getDamageType().getMultiplierAttribute()),
					damageOffhandAttribute = (Main.game.getPlayer().getOffhandWeapon() == null ? Attribute.DAMAGE_PHYSICAL : Main.game.getPlayer().getOffhandWeapon().getDamageType().getMultiplierAttribute());
			
			combatStringBuilder.append("<p>"
					+ "<b>You " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> " : "") +"hit for "
					+ damageMain + " <b style='color: " + damageMainAttribute.getColour().toWebHexString() + ";'>" + damageMainAttribute.getName() + "</b>, and then again for "
					+ damageOffhand + " <b style='color: " + damageOffhandAttribute.getColour().toWebHexString() + ";'>" + damageOffhandAttribute.getName() + "</b>!</b></p>");
		} else
			combatStringBuilder.append("<p><b>You missed!</b></p>");

		combatStringBuilder.append(opponent.incrementHealth(-(damageMain+damageOffhand)));
		combatStringBuilder.append(endCombatTurn(true));

		playerActionText = "Dual strike";
		playerTurnText = combatStringBuilder.toString();
	}

	private static String getDualDescription() {
		String attack;
		
		if(Main.game.getPlayer().getMainWeapon()!= null) {
			attack = Main.game.getPlayer().getMainWeapon().getWeaponType().getAttackDescription(Main.game.getPlayer(), opponent);
		} else if(Main.game.getPlayer().getOffhandWeapon()!= null) {
			attack = Main.game.getPlayer().getOffhandWeapon().getWeaponType().getAttackDescription(Main.game.getPlayer(), opponent);
		} else {
			attack = AbstractWeaponType.genericMeleeAttackDescription(Main.game.getPlayer(), opponent);
		}

		return (opponent.isVisiblyPregnant()
					?UtilText.genderParsing(opponent,
					"<p>"
						+ "A powerful field of arcane energy is protecting "+opponent.getName("the")+"'s pregnant belly, which doesn't even come into play, as you're very careful not to hit anywhere near <her> stomach."
					+ "</p>")
					:"")
				+"<p>"
				+ attack
				+"</p>";
	}
	

	// Calculations for seduction attack:
	private static void attackSeduction() {
		// Calculate hit + damage
		float damage = 0;
		combatStringBuilder = new StringBuilder("");

		combatStringBuilder.append("<p>" + getSeductionAttackDescription() + "</p>");

		critical = isCriticalHit(Main.game.getPlayer());
	
		damage = Attack.calculateDamage(Main.game.getPlayer(), opponent, Attack.SEDUCTION, critical);
	
		combatStringBuilder.append(UtilText.genderParsing(opponent, "<p>" + (critical ? "Your seductive display was <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>extremely effective</b>!</br>" : "")
				+ "<b><She> loses " + damage + " <b style='color:" + DamageType.MANA.getMultiplierAttribute().getColour().toWebHexString() + ";'>willpower</b> as <she> tries to resist your seductive display!</b>" + "</p>"));


		opponent.incrementMana(-damage);

		combatStringBuilder.append(endCombatTurn(true));

		playerActionText = "Seduction";
		playerTurnText = combatStringBuilder.toString();
	}

	private static String getSeductionAttackDescription() {
		if(Main.game.getPlayer().isFeminine()) {
			return UtilText.genderParsing(opponent,
					UtilText.returnStringAtRandom(
					"You blow a kiss at "+opponent.getName("the")+" and wink suggestively at <herPro>.",
					
					"Biting your lip and putting on your most smouldering look, you run your hands slowly up your inner thighs.",
					
					"As you give "+opponent.getName("the")+" your most innocent look, you blow <herPro> a little kiss.",
					
					"Turning around, you let out a playful giggle as you give your "+Main.game.getPlayer().getAssName(true)+" a slap.",
					
					"You slowly run your hands up the length of your body, before pouting at "+opponent.getName("the")+"."));
		} else {
			return UtilText.genderParsing(opponent,
					UtilText.returnStringAtRandom(
					"You blow a kiss at "+opponent.getName("the")+" and wink suggestively at <herPro>.",
					
					"Smiling confidently at "+opponent.getName("the")+", you slowly run your hands up your inner thighs.",
					
					"As you give "+opponent.getName("the")+" your most seductive look, you blow <herPro> a little kiss.",
					
					"Turning around, you let out a playful laugh as you give your "+Main.game.getPlayer().getAssName(true)+" a slap.",
					
					"You try to look as commanding as possible as you smirk playfully at "+opponent.getName("the")+"."));
		}
	}

	private static void attackSpell(Spell spell, int level) {

		critical = isCriticalHit(Main.game.getPlayer());

		combatStringBuilder = new StringBuilder();

		combatStringBuilder.append(opponent.isVisiblyPregnant()?UtilText.genderParsing(opponent,
				"<p>"
				+ "A powerful field of arcane energy is protecting "+opponent.getName("the")+"'s pregnant belly, which doesn't even come into play, as you're very careful not to hit anywhere near <her> stomach."
				+ "</p>"):"");
		
		combatStringBuilder.append(spell.applyEffect(Main.game.getPlayer(), opponent, level, true, critical));
		
		combatStringBuilder.append(endCombatTurn(true));

		playerActionText = Util.capitaliseSentence(spell.getName());
		playerTurnText = combatStringBuilder.toString();
	}

	private static void attackSpecialAttack(SpecialAttack specialAttack) {

		critical = isCriticalHit(Main.game.getPlayer());

		combatStringBuilder = new StringBuilder();
		
		combatStringBuilder.append(opponent.isVisiblyPregnant()?UtilText.genderParsing(opponent,
				"<p>"
				+ "A powerful field of arcane energy is protecting "+opponent.getName("the")+"'s pregnant belly, which doesn't even come into play, as you're very careful not to hit anywhere near <her> stomach."
				+ "</p>"):"");

		combatStringBuilder.append(specialAttack.applyEffect(Main.game.getPlayer(), opponent, true, critical));
		
		combatStringBuilder.append(endCombatTurn(true));

		playerActionText = Util.capitaliseSentence(specialAttack.getName());
		playerTurnText = combatStringBuilder.toString();
	}

	private static void attackWait() {
		combatStringBuilder = new StringBuilder(
				UtilText.parse(opponent,
				"<p>"
					+ "You decide not to make a move, and instead try to brace yourself as best as possible against [npc.name]'s next attack."
				+ "</p>"));

		combatStringBuilder.append(endCombatTurn(true));

		playerActionText = "Wait";
		playerTurnText = combatStringBuilder.toString();
	}
	
	private static String submit() {
		combatStringBuilder = new StringBuilder(
				"<p>" + "You kneel in front of " + opponent.getName("the") + ", lowering your head in submission. " + UtilText.parsePlayerSpeech("I don't want to fight any more, I submit.") + "</p>");

		combatStringBuilder.append(endCombatTurn(true));

		return combatStringBuilder.toString();
	}

	private static void escape() {
		combatStringBuilder = new StringBuilder("<p>");

		if (Util.random.nextInt(100) < escapeChance) {
			escaped = true;
			combatStringBuilder.append("You got away!");
		} else {
			combatStringBuilder.append("You failed to escape!");
			combatStringBuilder.append(endCombatTurn(true));
		}
		combatStringBuilder.append("</p>");

		playerActionText = "Escape";
		playerTurnText = combatStringBuilder.toString();
	}

	// Calculations for enemy attack:
	public static void attackEnemy() {

		if(opponent.getHealth() <= 0
				|| (opponent.getMana() <= 0 && !opponent.hasPerk(Perk.INDEFATIGABLE))
				|| (opponent.getStamina() <= 0 && !opponent.hasPerk(Perk.INDEFATIGABLE))) {

			opponentActionText = "<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Defeated!</span>";
			opponentTurnText = "<p>"
								+opponent.getName("The")+" doesn't have the strength to continue fighting...</br>"
								+ "<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>You are victorious!</span>"
								+ "</p>";
			
		} else if (Main.game.getPlayer().getHealth() <= 0 
				|| (Main.game.getPlayer().getMana() <= 0 && !Main.game.getPlayer().hasPerk(Perk.INDEFATIGABLE))
				|| (Main.game.getPlayer().getStamina() <= 0 && !Main.game.getPlayer().hasPerk(Perk.INDEFATIGABLE))) {
			
			playerActionText = "<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Defeated!</span>";
			playerTurnText += "<p>"
								+"You don't have the strength to continue fighting...</br>"
								+ "<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>You have been defeated!</span>"
								+ "</p>";
			
		} else if(escaped) {
			
			opponentActionText = "Fails to catch you";
			opponentTurnText = opponent.getName("The")+" tries to block your escape, but fails...";
			
		} else {
		
			// Calculate what attack to use based on NPC preference:
			Attack opponentAttack;
			critical = isCriticalHit(opponent);
	
			opponentAttack = opponent.attackType();
			float damage = 0;
			
			switch(opponentAttack){
				case DUAL:
					opponentActionText = "Dual strike";
					break;
					
				case ESCAPE:
					opponentActionText = "Escape";
					break;
					
				case MAIN:
					damage = 0;
					combatStringBuilder = new StringBuilder(opponent.getAttackDescription(opponentAttack, true));
					damage = Attack.calculateDamage(opponent, Main.game.getPlayer(), opponentAttack, critical);
					combatStringBuilder.append("<p>"
							+ "<b>You were " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " : " hit for ") + damage + " <b style='color: "
							+ (opponent.getMainWeapon() == null ? Attribute.DAMAGE_PHYSICAL : opponent.getMainWeapon().getDamageType().getMultiplierAttribute()).getColour().toWebHexString()
							+ ";'>"
							+ (opponent.getMainWeapon() == null ? Attribute.DAMAGE_PHYSICAL : opponent.getMainWeapon().getDamageType().getMultiplierAttribute()).getName()
							+ "</b>!</b>"
							+ "</p>");
					combatStringBuilder.append(Main.game.getPlayer().incrementHealth(-damage));
					
					opponentActionText = "Main attack";
					break;
					
				case NONE:
					opponentActionText = "Idle";
					break;
					
				case OFFHAND:
					damage = 0;
					combatStringBuilder = new StringBuilder(opponent.getAttackDescription(opponentAttack, true));
					damage = Attack.calculateDamage(opponent, Main.game.getPlayer(), opponentAttack, critical);
					combatStringBuilder.append("<p>"
							+ "<b>You were " + (critical ? "<b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " : " hit for ") + damage + " <b style='color: "
							+ (opponent.getOffhandWeapon() == null ? Attribute.DAMAGE_PHYSICAL :opponent.getOffhandWeapon().getDamageType().getMultiplierAttribute()).getColour().toWebHexString()
							+ ";'>"
							+ (opponent.getOffhandWeapon() == null ? Attribute.DAMAGE_PHYSICAL :opponent.getOffhandWeapon().getDamageType().getMultiplierAttribute()).getName()
							+ "</b>!</b>"
							+ "</p>");
					combatStringBuilder.append(Main.game.getPlayer().incrementHealth(-damage));
					
					opponentActionText = "Offhand attack";
					break;
					
				case SEDUCTION:
					damage = 0;
					combatStringBuilder = new StringBuilder(opponent.getAttackDescription(opponentAttack, true));
					damage = Attack.calculateDamage(opponent, Main.game.getPlayer(), opponentAttack, critical);
					combatStringBuilder.append(UtilText.genderParsing(opponent,
							"<p>"
							+ (critical ? "<Her> seductive display was <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>extremely effective</b>!</br>" : "")
							+ "<b>You lose " + damage + " <b style='color:" + DamageType.MANA.getMultiplierAttribute().getColour().toWebHexString() + ";'>willpower</b> as you try to resist the seductive display!</b>"
							+ "</p>"));
					Main.game.getPlayer().incrementMana(-damage);
					
					opponentActionText = "Seduction";
					break;
					
				case SPECIAL_ATTACK:
					SpecialAttack specialAttack = opponent.getSpecialAttacks().get(Util.random.nextInt(opponent.getSpecialAttacks().size()));
					combatStringBuilder = new StringBuilder(specialAttack.applyEffect(opponent, Main.game.getPlayer(), true, critical));

					opponentActionText = Util.capitaliseSentence(specialAttack.getName());
					break;
					
				case SPELL:
					Spell spell = opponent.getSpell();
					combatStringBuilder = new StringBuilder(spell.applyEffect(opponent, Main.game.getPlayer(), opponent.getLevel(), true, critical));
					
					opponentActionText = Util.capitaliseSentence(spell.getName());
					break;
					
				case USE_ITEM:
					opponentActionText = "";
					break;
					
				default:
					break;
					
					
			}
			
			combatStringBuilder.append(endCombatTurn(false));
			
			if(opponent.getHealth() <= 0
					|| (opponent.getMana() <= 0 && !opponent.hasPerk(Perk.INDEFATIGABLE))
					|| (opponent.getStamina() <= 0 && !opponent.hasPerk(Perk.INDEFATIGABLE))) {

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
				+ " <b style='color:"+ Attribute.DAMAGE_MANA.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(DamageType.MANA.getName()) + "</b> <b>damage</b></br></br>"

				+ "Seduction attacks <b style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>always hit</b>.";
	}

	private static String getSpellDescription(Spell spell, AbstractWeapon source) {
		return "Cast <b>Level " + Main.game.getPlayer().getLevel() + "</b> <b style='color:" + spell.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(spell.getName()) + "</b></br></br>"

				+ "<b>" + spell.getMinimumDamage(Main.game.getPlayer(), opponent, Main.game.getPlayer().getLevel()) + " - " + spell.getMaximumDamage(Main.game.getPlayer(), opponent, Main.game.getPlayer().getLevel()) + "</b>" + " <b style='color:"
				+ spell.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(spell.getDamageType().getName()) + "</b> <b>damage</b></br></br>"
				
				+ "<b>" + spell.getMinimumCost(Main.game.getPlayer(), Main.game.getPlayer().getLevel()) + " - " + spell.getMaximumCost(Main.game.getPlayer(), Main.game.getPlayer().getLevel()) + "</b>" + " <b style='color:"
				+ Colour.ATTRIBUTE_MANA.toWebHexString() + ";'>willpower</b> <b>cost</b></br></br>";
	}

	private static String getSpecialAttackDescription(SpecialAttack specialAttack) {

		return "Use your <b style='color:" + specialAttack.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(specialAttack.getName()) + "</b> special attack.</br></br>"

				+ "<b>" + specialAttack.getMinimumDamage(Main.game.getPlayer(), opponent) + " - " + specialAttack.getMaximumDamage(Main.game.getPlayer(), opponent) + "</b>" + " <b style='color:"
				+ specialAttack.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(specialAttack.getDamageType().getName()) + "</b> <b>damage</b></br></br>"


				+ "<b>" + specialAttack.getMinimumCost(Main.game.getPlayer()) + " - " + specialAttack.getMaximumCost(Main.game.getPlayer()) + "</b>" + " <b style='color:"
				+ Colour.ATTRIBUTE_STAMINA.toWebHexString() + ";'>stamina</b> <b>cost</b></br></br>";
	}

	public static GameCharacter getOpponent() {
		return opponent;
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
}
