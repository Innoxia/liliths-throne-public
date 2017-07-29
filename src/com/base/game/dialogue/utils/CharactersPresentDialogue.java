package com.base.game.dialogue.utils;

import com.base.game.character.GameCharacter;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.attributes.FitnessLevel;
import com.base.game.character.attributes.IntelligenceLevel;
import com.base.game.character.attributes.StrengthLevel;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.character.effects.StatusEffect;
import com.base.game.combat.SpecialAttack;
import com.base.game.combat.Spell;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.main.Main;
import com.base.rendering.RenderingEngine;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.3
 * @version 0.1.8
 * @author Innoxia
 */
public class CharactersPresentDialogue {

	private static String menuContent, menuTitle;
	private static StringBuilder infoBoxSB = new StringBuilder();
	public static GameCharacter characterViewed = null;

	public static void resetContent() {
		
		characterViewed = Main.game.getCharactersPresent().get(0);
		
		RenderingEngine.ENGINE.setCharactersInventoryToRender(Main.game.getCharactersPresent().get(0));
		
		menuTitle = "Characters present ("+Util.capitaliseSentence(Main.game.getCharactersPresent().get(0).getName())+")";

		menuContent = getCharacterInfoBox(Main.game.getCharactersPresent().get(0))
				+ "<h4>Background</h4>"
				+ "<p>"
					+ Main.game.getCharactersPresent().get(0).getDescription()
				+ "</p>"
				+ "<h4>Appearance</h4>"
				+ "<p>"
					+ Main.game.getCharactersPresent().get(0).getBodyDescription()
				+ "</p>"
				+ getStats(Main.game.getCharactersPresent().get(0));
		
		RenderingEngine.ENGINE.renderInventory();
	}
	
	public static final DialogueNodeOld MENU = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return menuTitle;
		}

		@Override
		public String getContent() {
			return menuContent;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new ResponseEffectsOnly("Back", "Stop viewing the characters present and return to the main game."){
					@Override
					public void effects() {
						Main.mainController.openCharactersPresent();
					}
				};
				
			} else if (index <= Main.game.getCharactersPresent().size()) {
				return new Response(Util.capitaliseSentence(
						Main.game.getCharactersPresent().get(index - 1).getName()),
						"Take a detailed look at " + Main.game.getCharactersPresent().get(index - 1).getName("the") + ".",
						MENU){
					@Override
					public void effects() {
						characterViewed = Main.game.getCharactersPresent().get(index-1);
						
						RenderingEngine.ENGINE.setCharactersInventoryToRender(Main.game.getCharactersPresent().get(index - 1));
						menuTitle = "Characters present ("+Util.capitaliseSentence(Main.game.getCharactersPresent().get(index - 1).getName())+")";
						menuContent = getCharacterInfoBox(Main.game.getCharactersPresent().get(index - 1))
								+ "<h4>Background</h4>"
								+ "<p>"
										+ Main.game.getCharactersPresent().get(index - 1).getDescription()
								+ "</p>"
										+ "<h4>Appearance</h4>"
								+ "<p>"
									+ Main.game.getCharactersPresent().get(index - 1).getBodyDescription()
								+ "</p>"
								+ getStats(Main.game.getCharactersPresent().get(index - 1));
						
						RenderingEngine.ENGINE.renderInventory();
					}
				};
				
			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.CHARACTERS_PRESENT;
		}
	};
	
	public static String getCharacterInfoBox(GameCharacter character) {
		infoBoxSB.setLength(0);
		
		infoBoxSB.append("<div class='combat-display left' style='float:right;'>"

		// Display Name and level:
		+ "<div class='combat-inner-container'>"
				+ "<div class='combat-container name'>"
					+ "<div class='combat-container'>"
						+ "<p class='combatant-title name' style='color:" + Femininity.valueOf(character.getFemininity()).getColour().() + ";'>" 
							+ "<b>" + Util.capitaliseSentence(character.getName()) + "</b>"
						+ "</p>"
					+ "</div>"
					+ "<div class='combat-container'>"
						+ "<p class='combatant-title level'>"
							+ "<b>Level " + character.getLevel() +"</b>"
							+ "</br>"
							+(character.getRaceStage().getName()!=""
								?"<b style='color:"+character.getRaceStage().getColour().()+";'>" + Util.capitaliseSentence(character.getRaceStage().getName())+"</b> ":"")
							+ "<b style='color:"+character.getRace().getColour().()+";'>"
							+ (character.isFeminine()?Util.capitaliseSentence(character.getRace().getSingularFemaleName()):Util.capitaliseSentence(character.getRace().getSingularMaleName()))
							+ "</b>"
						+ "</p>"
					+ "</div>"
					+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_ATTRIBUTES'></div>" 
				+ "</div>"
				+ "</div>"

		// Attributes:
		+ "<div class='combat-inner-container'>"
			+ "<div class='combat-container attribute'>"
				+ "<div class='combat-resource-icon'>" + StrengthLevel.getStrengthLevelFromValue(character.getAttributeValue(Attribute.STRENGTH)).getRelatedStatusEffect().getSVGString(character) + "</div>"
				+ "<div class='combat-resource-number'>"
					+ "<b style='color:" + Attribute.STRENGTH.getColour().() + ";'>" + (int) character.getAttributeValue(Attribute.STRENGTH) + "</b>"
				+ "</div>"
				+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.STRENGTH + "'></div>"
			+ "</div>"

			+ "<div class='combat-container attribute'>"
				+ "<div class='combat-resource-icon'>" + IntelligenceLevel.getIntelligenceLevelFromValue(character.getAttributeValue(Attribute.INTELLIGENCE)).getRelatedStatusEffect().getSVGString(character) + "</div>"
				+ "<div class='combat-resource-number'>"
				+ "<b style='color:" + Attribute.INTELLIGENCE.getColour().() + ";'>" + (int) character.getAttributeValue(Attribute.INTELLIGENCE) + "</b>"
				+ "</div>"
				+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.INTELLIGENCE + "'></div>"
			+ "</div>"

			+ "<div class='combat-container attribute'>"
				+ "<div class='combat-resource-icon'>" + FitnessLevel.getFitnessLevelFromValue(character.getAttributeValue(Attribute.FITNESS)).getRelatedStatusEffect().getSVGString(character) + "</div>"
				+ "<div class='combat-resource-number'>"
				+ "<b style='color:" + Attribute.FITNESS.getColour().() + ";'>" + (int) character.getAttributeValue(Attribute.FITNESS) + "</b>"
				+ "</div>"
				+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.FITNESS + "'></div>"
			+ "</div>"

			+ "<div class='combat-container attribute'>"
				+ "<div class='combat-resource-icon'>" + CorruptionLevel.getCorruptionLevelFromValue(character.getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(character) + "</div>"
				+ "<div class='combat-resource-number'>"
				+ "<b style='color:" + Attribute.CORRUPTION.getColour().() + ";'>" + (int) character.getAttributeValue(Attribute.CORRUPTION) + "</b>"
				+ "</div>"
				+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.CORRUPTION + "'></div>"
			+ "</div>"
		+ "</div>"

		// Display health, willpower and stamina:
		+ "<div class='combat-inner-container'>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.HEALTH_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((((float) character.getHealth()) / (character.getAttributeValue(Attribute.HEALTH_MAXIMUM))) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_HEALTH.() + "; border-radius: 2px;'></div>" + "</div>"
				+ "<div class='combat-resource-number'>"
				+ (int) Math.ceil(character.getHealth()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.HEALTH_MAXIMUM + "'></div>" + "</div>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.MANA_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((((float) character.getMana()) / (character.getAttributeValue(Attribute.MANA_MAXIMUM))) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_MANA.() + "; border-radius: 2px;'></div>" + "</div>"
				+ "<div class='combat-resource-number'>" + (int) Math.ceil(character.getMana()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.MANA_MAXIMUM + "'></div>" + "</div>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.STAMINA_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((((float) character.getStamina()) / (character.getAttributeValue(Attribute.STAMINA_MAXIMUM))) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_FITNESS.() + "; border-radius: 2px;'></div>" + "</div>"
				+ "<div class='combat-resource-number'>"
				+ (int) Math.ceil(character.getStamina()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.STAMINA_MAXIMUM + "'></div>" + "</div>"

				+ "</div>"

		// Display status effects:
		+ "<div class='combat-inner-container status-effects'>");
		
		for (Perk p : character.getPerks()) {
			infoBoxSB.append("<div class='combat-status-effect'>" + p.getSVGString() + "<div class='overlay no-pointer' id='PERK_COMBAT_" + p + "'></div>" + "</div>");
		}
		for (Fetish f : character.getFetishes()) {
			infoBoxSB.append("<div class='combat-status-effect'>" + f.getSVGString() + "<div class='overlay no-pointer' id='FETISH_COMBAT_" + f + "'></div>" + "</div>");
		}
		for (StatusEffect se : character.getStatusEffects()) {
			if (se.renderInEffectsPanel()) {
				if (se.isCombatEffect()) {
					infoBoxSB.append("<div class='combat-status-effect" + (!se.isBeneficial() ? " negativeCombat" : " positiveCombat") + "'>"
								+ se.getSVGString(character) + "<div class='overlay no-pointer' id='SE_COMBAT_" + se + "'></div>" + "</div>");
				} else {
					infoBoxSB.append(
							"<div class='combat-status-effect'>" + se.getSVGString(character) + "<div class='overlay no-pointer' id='SE_COMBAT_" + se + "'></div>" + "</div>");
				}
			}
		}
		for (SpecialAttack sa : character.getSpecialAttacks()) {
			infoBoxSB.append(
					"<div class='combat-status-effect'>" + sa.getSVGString() + "<div class='overlay no-pointer' id='SA_COMBAT_" + sa + "'></div>" + "</div>");
		}
		if (character.getMainWeapon() != null) {
			for (Spell s : character.getMainWeapon().getSpells()) {
				infoBoxSB
						.append("<div class='combat-status-effect'>" + s.getSVGString() + "<div class='overlay' id='SPELL_MAIN_COMBAT_" + s + "'></div>" + "</div>");
			}
		}
		if (character.getOffhandWeapon() != null) {
			for (Spell s : character.getOffhandWeapon().getSpells()) {
				infoBoxSB
						.append("<div class='combat-status-effect'>" + s.getSVGString() + "<div class='overlay' id='SPELL_OFFHAND_COMBAT_" + s + "'></div>" + "</div>");
			}
		}
		
		infoBoxSB.append("</div></div>");
			
		return infoBoxSB.toString();
	}
	
	public static String getStats(GameCharacter character) {
		return "<h4 style='text-align:center;'>Stats</h4>"
				+"<table align='center'>"

				+ "<tr style='height:8px; color:"+character.getGender().getColour().()+";'><th>Core</th></tr>"
				+ statRow("Femininity", String.valueOf(character.getFemininity()))
				+ statRow("Height (cm)", String.valueOf(character.getHeight()))
				+ statRow("Hair length (cm)", String.valueOf(Util.conversionInchesToCentimetres(character.getHairRawLengthValue())))
				+ statRow("Makeup", String.valueOf(character.getFaceMakeupLevel().getValue()))
				+ "<tr style='height:8px;'></tr>"

				+ "<tr style='height:8px; color:"+Colour.TRANSFORMATION_SEXUAL.()+";'><th>Breasts</th></tr>"
				+ statRow("Cup size", character.getBreastRawSizeValue() == 0 ? "N/A" : Util.capitaliseSentence(character.getBreastSize().getCupSizeName()))
				+ (character.getPlayerKnowsAreasMap().get(CoverableArea.NIPPLES)
					?statRow("Milk production (mL)", String.valueOf(character.getBreastRawLactationValue()))
						+ statRow("Capacity (inches)", String.valueOf(character.getBreastRawCapacityValue()))
						+ statRow("Elasticity", String.valueOf(character.getBreastElasticity().getValue()) + " ("+Util.capitaliseSentence(character.getBreastElasticity().getDescriptor())+")")
					:statRow("Milk production (mL)", "<span style='color:"+Colour.TEXT_GREY.()+";'>Undiscovered</span>")
						+ statRow("Capacity (inches)","<span style='color:"+Colour.TEXT_GREY.()+";'>Undiscovered</span>")
						+ statRow("Elasticity", "<span style='color:"+Colour.TEXT_GREY.()+";'>Undiscovered</span>"))
				+ "<tr style='height:8px;'></tr>"

				+ "<tr style='height:8px; color:"+Colour.TRANSFORMATION_SEXUAL.()+";'><th>Penis</th></tr>"
				+ (character.getPlayerKnowsAreasMap().get(CoverableArea.PENIS)
					?statRow("Length (inches)", character.getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(character.getPenisRawSizeValue()))
						+ statRow("Ball size", character.getPenisType() == PenisType.NONE ? "N/A" : Util.capitaliseSentence(character.getTesticleSize().getDescriptor()))
						+ statRow("Cum production (mL)", character.getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(character.getPenisRawCumProductionValue()))
					:statRow("Length (inches)", "<span style='color:"+Colour.TEXT_GREY.()+";'>Undiscovered</span>")
						+ statRow("Ball size", "<span style='color:"+Colour.TEXT_GREY.()+";'>Undiscovered</span>")
						+ statRow("Cum production (mL)", "<span style='color:"+Colour.TEXT_GREY.()+";'>Undiscovered</span>"))
				+ "<tr style='height:8px;'></tr>"

				+ "<tr style='height:8px; color:"+Colour.TRANSFORMATION_SEXUAL.()+";'><th>Vagina</th></tr>"
				+ (character.getPlayerKnowsAreasMap().get(CoverableArea.VAGINA)
					?statRow("Capacity (inches)", character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaRawCapacityValue()))
						+ statRow("Elasticity", String.valueOf(character.getVaginaElasticity().getValue()) + " ("+Util.capitaliseSentence(character.getVaginaElasticity().getDescriptor())+")")
						+ statRow("Wetness", character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaWetness().getValue()) +" ("+Util.capitaliseSentence(character.getVaginaWetness().getDescriptor())+")")
						+ statRow("Clit size (inches)", character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaRawClitorisSizeValue()))
					:statRow("Capacity (inches)", "<span style='color:"+Colour.TEXT_GREY.()+";'>Undiscovered</span>")
						+ statRow("Elasticity", "<span style='color:"+Colour.TEXT_GREY.()+";'>Undiscovered</span>")
						+ statRow("Wetness", "<span style='color:"+Colour.TEXT_GREY.()+";'>Undiscovered</span>")
						+ statRow("Clit size (inches)", "<span style='color:"+Colour.TEXT_GREY.()+";'>Undiscovered</span>"))
				+ "<tr style='height:8px;'></tr>"

				+ "<tr style='height:8px; color:"+Colour.TRANSFORMATION_SEXUAL.()+";'><th>Anus</th></tr>"
				+ (character.getPlayerKnowsAreasMap().get(CoverableArea.ANUS)
					?statRow("Capacity (inches)", String.valueOf(character.getAssRawCapacityValue()))
						+ statRow("Elasticity", String.valueOf(character.getAssElasticity().getValue()) + " ("+Util.capitaliseSentence(character.getAssElasticity().getDescriptor())+")")
						+ statRow("Wetness", String.valueOf(character.getAssWetness().getValue()) +" ("+Util.capitaliseSentence(character.getAssWetness().getDescriptor())+")")
					:statRow("Capacity (inches)", "<span style='color:"+Colour.TEXT_GREY.()+";'>Undiscovered</span>")
						+ statRow("Elasticity", "<span style='color:"+Colour.TEXT_GREY.()+";'>Undiscovered</span>")
						+ statRow("Wetness", "<span style='color:"+Colour.TEXT_GREY.()+";'>Undiscovered</span>"))
				+ "</table>";
	}
	
	private static String statRow(String title, String value) {
		return "<tr>"
					+ "<td style='min-width:100px;'>"
						+ "<b>"+title+"</b>"
					+ "</td>"
					+ "<td style='min-width:100px;'>"
						+ "<b>"+value+"</b>"
					+ "</td>"
				+ "</tr>";
	}

	
}
