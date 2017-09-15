package com.base.game.dialogue.utils;

import java.util.Map.Entry;

import com.base.game.character.GameCharacter;
import com.base.game.character.attributes.AffectionLevel;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.attributes.FitnessLevel;
import com.base.game.character.attributes.IntelligenceLevel;
import com.base.game.character.attributes.ObedienceLevel;
import com.base.game.character.attributes.StrengthLevel;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.npc.NPC;
import com.base.game.combat.SpecialAttack;
import com.base.game.combat.Spell;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.3
 * @version 0.1.85
 * @author Innoxia
 */
public class CharactersPresentDialogue {

	private static String menuContent, menuTitle;
	private static StringBuilder infoBoxSB = new StringBuilder(), infoScreenSB = new StringBuilder();
	public static GameCharacter characterViewed = null;

	public static void resetContent() {
		
		characterViewed = Main.game.getCharactersPresent().get(0);
		
		menuTitle = "Characters present ("+Util.capitaliseSentence(Main.game.getCharactersPresent().get(0).getName())+")";
		menuContent = getCharacterInformationScreen((NPC) Main.game.getCharactersPresent().get(0));
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
						
						menuTitle = "Characters present ("+Util.capitaliseSentence(Main.game.getCharactersPresent().get(index - 1).getName())+")";
						menuContent = getCharacterInformationScreen((NPC) Main.game.getCharactersPresent().get(index - 1));
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
	
	public static String getCharacterInformationScreen(NPC character) {
		infoScreenSB.setLength(0);
		
		infoScreenSB.append(getCharacterInfoBox(character)
				+ "<h4>Background</h4>"
				+ "<p>"
					+ character.getDescription()
				+ "</p>"
				+ "</br>"
				+ "<h4>Relationships</h4>"
				+ "<p>"
					+ "[style.boldAffection(Affection:)]</br>"
					+ AffectionLevel.getDescription(character, Main.game.getPlayer(),
							AffectionLevel.getAffectionLevelFromValue(character.getAffection(Main.game.getPlayer())), true));
		
		for(Entry<GameCharacter, Integer> entry : character.getRelationshipsMap().entrySet()) {
			if(!entry.getKey().isPlayer()) {
				infoScreenSB.append("</br>" + AffectionLevel.getDescription(character, entry.getKey(), AffectionLevel.getAffectionLevelFromValue(character.getAffection(entry.getKey())), true));
			}
		}
		
		infoScreenSB.append("</br></br>"
					+ "[style.boldObedience(Obedience:)]</br>"
					+ UtilText.parse(character,
							(character.isSlave()
								?"[npc.Name] [style.boldArcane(is a slave)], owned by "+character.getOwner().getName("a")+"."
								:"[npc.Name] [style.boldGood(is not a slave)]."))
					+ "</br>"+ObedienceLevel.getDescription(character, ObedienceLevel.getObedienceLevelFromValue(character.getObedience()), true)
					+"</br></br>"
					+ "[style.boldArcane(Slaves owned:)]");
		
		if(character.getSlavesOwned().isEmpty()) {
			infoScreenSB.append("</br>[style.colourDisabled(None)]");
		} else {
			for(GameCharacter slave : character.getSlavesOwned()) {
				infoScreenSB.append(UtilText.parse(slave, "</br>[npc.Name]"));
			}
		}
		
		infoScreenSB.append("</p>"
				+ "</br>"
					+ "<h4>Appearance</h4>"
				+ "<p>"
					+ character.getBodyDescription()
				+ "</p>"
				+ getStats(character));
		
		return infoScreenSB.toString();
	}
	
	private static String getCharacterInfoBox(GameCharacter character) {
		infoBoxSB.setLength(0);
		
		infoBoxSB.append("<div class='combat-display left' style='float:right;'>"

		// Display Name and level:
		+ "<div class='combat-inner-container'>"
				+ "<div class='combat-container name'>"
					+ "<div class='combat-container'>"
						+ "<p class='combatant-title name' style='color:" + Femininity.valueOf(character.getFemininity()).getColour().toWebHexString() + ";'>" 
							+ "<b>" + Util.capitaliseSentence(character.getName()) + "</b>"
						+ "</p>"
					+ "</div>"
					+ "<div class='combat-container'>"
						+ "<p class='combatant-title level'>"
							+ "<b>Level " + character.getLevel() +"</b>"
							+ "</br>"
							+(character.getRaceStage().getName()!=""
								?"<b style='color:"+character.getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(character.getRaceStage().getName())+"</b> ":"")
							+ "<b style='color:"+character.getRace().getColour().toWebHexString()+";'>"
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
					+ "<b style='color:" + Attribute.STRENGTH.getColour().toWebHexString() + ";'>" + (int) character.getAttributeValue(Attribute.STRENGTH) + "</b>"
				+ "</div>"
				+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.STRENGTH + "'></div>"
			+ "</div>"

			+ "<div class='combat-container attribute'>"
				+ "<div class='combat-resource-icon'>" + IntelligenceLevel.getIntelligenceLevelFromValue(character.getAttributeValue(Attribute.INTELLIGENCE)).getRelatedStatusEffect().getSVGString(character) + "</div>"
				+ "<div class='combat-resource-number'>"
				+ "<b style='color:" + Attribute.INTELLIGENCE.getColour().toWebHexString() + ";'>" + (int) character.getAttributeValue(Attribute.INTELLIGENCE) + "</b>"
				+ "</div>"
				+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.INTELLIGENCE + "'></div>"
			+ "</div>"

			+ "<div class='combat-container attribute'>"
				+ "<div class='combat-resource-icon'>" + FitnessLevel.getFitnessLevelFromValue(character.getAttributeValue(Attribute.FITNESS)).getRelatedStatusEffect().getSVGString(character) + "</div>"
				+ "<div class='combat-resource-number'>"
				+ "<b style='color:" + Attribute.FITNESS.getColour().toWebHexString() + ";'>" + (int) character.getAttributeValue(Attribute.FITNESS) + "</b>"
				+ "</div>"
				+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.FITNESS + "'></div>"
			+ "</div>"

			+ "<div class='combat-container attribute'>"
				+ "<div class='combat-resource-icon'>" + CorruptionLevel.getCorruptionLevelFromValue(character.getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(character) + "</div>"
				+ "<div class='combat-resource-number'>"
				+ "<b style='color:" + Attribute.CORRUPTION.getColour().toWebHexString() + ";'>" + (int) character.getAttributeValue(Attribute.CORRUPTION) + "</b>"
				+ "</div>"
				+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.CORRUPTION + "'></div>"
			+ "</div>"
		+ "</div>"

		// Display health, willpower and stamina:
		+ "<div class='combat-inner-container'>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.HEALTH_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((character.getHealth() / character.getAttributeValue(Attribute.HEALTH_MAXIMUM)) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_HEALTH.toWebHexString() + "; border-radius: 2px;'></div>" + "</div>"
				+ "<div class='combat-resource-number'>"
				+ (int) Math.ceil(character.getHealth()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.HEALTH_MAXIMUM + "'></div>" + "</div>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.MANA_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((character.getMana() / character.getAttributeValue(Attribute.MANA_MAXIMUM)) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_MANA.toWebHexString() + "; border-radius: 2px;'></div>" + "</div>"
				+ "<div class='combat-resource-number'>" + (int) Math.ceil(character.getMana()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.MANA_MAXIMUM + "'></div>" + "</div>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.STAMINA_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((character.getStamina() / character.getAttributeValue(Attribute.STAMINA_MAXIMUM)) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_FITNESS.toWebHexString() + "; border-radius: 2px;'></div>" + "</div>"
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

				+ "<tr style='height:8px; color:"+character.getGender().getColour().toWebHexString()+";'><th>Core</th></tr>"
				+ statRow("Femininity", String.valueOf(character.getFemininity()))
				+ statRow("Height (cm)", String.valueOf(character.getHeight()))
				+ statRow("Hair length (cm)", String.valueOf(Util.conversionInchesToCentimetres(character.getHairRawLengthValue())))
				+ "<tr style='height:8px;'></tr>"

				+ "<tr style='height:8px; color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+";'><th>Breasts</th></tr>"
				+ statRow("Cup size", character.getBreastRawSizeValue() == 0 ? "N/A" : Util.capitaliseSentence(character.getBreastSize().getCupSizeName()))
				+ (character.getPlayerKnowsAreasMap().get(CoverableArea.NIPPLES)
					?statRow("Milk production (mL)", String.valueOf(character.getBreastRawLactationValue()))
						+ statRow("Capacity (inches)", String.valueOf(character.getNippleRawCapacityValue()))
						+ statRow("Elasticity", String.valueOf(character.getNippleElasticity().getValue()) + " ("+Util.capitaliseSentence(character.getNippleElasticity().getDescriptor())+")")
					:statRow("Milk production (mL)", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Capacity (inches)","<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Elasticity", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>"))
				+ "<tr style='height:8px;'></tr>"

				+ "<tr style='height:8px; color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+";'><th>Penis</th></tr>"
				+ (character.getPlayerKnowsAreasMap().get(CoverableArea.PENIS)
					?statRow("Length (inches)", character.getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(character.getPenisRawSizeValue()))
						+ statRow("Ball size", character.getPenisType() == PenisType.NONE ? "N/A" : Util.capitaliseSentence(character.getTesticleSize().getDescriptor()))
						+ statRow("Cum production (mL)", character.getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(character.getPenisRawCumProductionValue()))
					:statRow("Length (inches)", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Ball size", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Cum production (mL)", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>"))
				+ "<tr style='height:8px;'></tr>"

				+ "<tr style='height:8px; color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+";'><th>Vagina</th></tr>"
				+ (character.getPlayerKnowsAreasMap().get(CoverableArea.VAGINA)
					?statRow("Capacity (inches)", character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaRawCapacityValue()))
						+ statRow("Elasticity", String.valueOf(character.getVaginaElasticity().getValue()) + " ("+Util.capitaliseSentence(character.getVaginaElasticity().getDescriptor())+")")
						+ statRow("Wetness", character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaWetness().getValue()) +" ("+Util.capitaliseSentence(character.getVaginaWetness().getDescriptor())+")")
						+ statRow("Clit size (inches)", character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaRawClitorisSizeValue()))
					:statRow("Capacity (inches)", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Elasticity", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Wetness", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Clit size (inches)", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>"))
				+ "<tr style='height:8px;'></tr>"

				+ "<tr style='height:8px; color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+";'><th>Anus</th></tr>"
				+ (character.getPlayerKnowsAreasMap().get(CoverableArea.ANUS)
					?statRow("Capacity (inches)", String.valueOf(character.getAssRawCapacityValue()))
						+ statRow("Elasticity", String.valueOf(character.getAssElasticity().getValue()) + " ("+Util.capitaliseSentence(character.getAssElasticity().getDescriptor())+")")
						+ statRow("Wetness", String.valueOf(character.getAssWetness().getValue()) +" ("+Util.capitaliseSentence(character.getAssWetness().getDescriptor())+")")
					:statRow("Capacity (inches)", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Elasticity", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Wetness", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>"))
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
