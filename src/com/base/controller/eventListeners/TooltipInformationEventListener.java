package com.base.controller.eventListeners;

import java.util.Map.Entry;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.base.controller.TooltipUpdateThread;
import com.base.game.character.GameCharacter;
import com.base.game.character.PlayerCharacter;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.attributes.ArousalLevel;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.attributes.FitnessLevel;
import com.base.game.character.attributes.IntelligenceLevel;
import com.base.game.character.attributes.StrengthLevel;
import com.base.game.character.body.types.HornType;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.TailType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.types.WingType;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.character.effects.PerkInterface;
import com.base.game.character.effects.StatusEffect;
import com.base.game.combat.Combat;
import com.base.game.combat.SpecialAttack;
import com.base.game.combat.Spell;
import com.base.game.dialogue.utils.CharactersPresentDialogue;
import com.base.game.dialogue.utils.PhoneDialogue;
import com.base.game.dialogue.utils.UtilText;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.79
 * @author Innoxia
 */
public class TooltipInformationEventListener implements EventListener {
	private String title, description;
	private boolean extraAttributes = false, opponentExtraAttributes = false, weather = false, protection = false, tattoo = false, copyInformation=false;
	private GameCharacter owner;
	private StatusEffect statusEffect;
	private PerkInterface perk, levelUpPerk;
	private Fetish fetish;
	private SpecialAttack specialAttack;
	private Spell spell;
	private int spellLevel;
	private Attribute attribute;
	
	private static StringBuilder tooltipSB;
	static {
		tooltipSB = new StringBuilder();
	}

	/*
	 * I have to manually set tooltip height, as all the JavaFX methods for getting document height are completely broken. (Don't bother wasting any more time with JavaFX crap.)
	 */
	
	@Override
	public void handleEvent(Event event) {
		Main.mainController.setTooltipSize(360, 180);
		Main.mainController.setTooltipContent("");

		if (statusEffect != null) {
			
			int yIncrease = (statusEffect.getModifiersAsStringList(owner).size() > 4 ? statusEffect.getModifiersAsStringList(owner).size() - 4 : 0)
								+ (owner.hasStatusEffect(statusEffect)?(owner.getStatusEffectDuration(statusEffect) == -1 ? 0 : 2):0);

			Main.mainController.setTooltipSize(360, 284 + (yIncrease * 20));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<body>"
					+ "<div class='title'>" + Util.capitaliseSentence(statusEffect.getName(owner)) + "</div>");

			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");
				if (statusEffect.getModifiersAsStringList(owner).size() != 0) {
					tooltipSB.append("<b style='color:" + Colour.STATUS_EFFECT + ";'>Status Effect</b>");
					for (String s : statusEffect.getModifiersAsStringList(owner))
						tooltipSB.append("</br>" + s);
				} else {
					tooltipSB.append("<b style='color:" + Colour.STATUS_EFFECT + ";'>Status Effect</b>" + "</br><span style='color:" + Colour.TEXT_GREY + ";'>No bonuses</span>");
				}
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>"
								+ statusEffect.getSVGString(owner)
							+ "</div>"
							+ "<div class='description'>"
								+ statusEffect.getDescription(owner)
							+ "</div>");

			if(owner.hasStatusEffect(statusEffect))
				if (owner.getStatusEffectDuration(statusEffect) != -1) {
					if (statusEffect.isCombatEffect()) {
						tooltipSB.append("<div class='subTitle'><b>Turns remaining: " + owner.getStatusEffectDuration(statusEffect) + "</b></div>");
					} else {
						int timerHeight = (int) ((owner.getStatusEffectDuration(statusEffect)/(60*6f))*100);

						Colour timerColour = Colour.STATUS_EFFECT_TIME_HIGH;
						
						if(timerHeight>100) {
							timerHeight=100;
							timerColour = Colour.STATUS_EFFECT_TIME_OVERFLOW;
						} else if(timerHeight<15) {
							timerColour = Colour.STATUS_EFFECT_TIME_LOW;
						} else if (timerHeight<50) {
							timerColour = Colour.STATUS_EFFECT_TIME_MEDIUM;
						}
						
						tooltipSB.append("<div class='subTitle'><b>Time remaining: "
								+ "<b style='color:"+timerColour+";'>" + (owner.getStatusEffectDuration(statusEffect) / 60) + ":" + String.format("%02d", (owner.getStatusEffectDuration(statusEffect) % 60)) + "</b>"
								+ "</div>");
						//STATUS_EFFECT_TIME_OVERFLOW
					}
				}
			
			tooltipSB.append("</body>");
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (perk != null) { // Perks:
			
			if(perk instanceof Perk) {
			
				int yIncrease = (perk.getModifiersAsStringList().size() > 4 ? perk.getModifiersAsStringList().size() - 4 : 0);
	
				Main.mainController.setTooltipSize(360, 288 + (yIncrease * 20));
	
				// Title:
				tooltipSB.setLength(0);
				tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(perk.getName(owner)) + "</div>");
				
				// Attribute modifiers:
				tooltipSB.append("<div class='subTitle-picture'>");
				if (perk.getModifiersAsStringList().size() != 0) {
					tooltipSB.append("<b style='color:" + Colour.PERK + ";'>Perk</b>");
					for (String s : perk.getModifiersAsStringList())
						tooltipSB.append("</br>" + s);
				} else
					tooltipSB.append("<b style='color:" + Colour.PERK + ";'>Perk</b>" + "</br><span style='color:" + Colour.TEXT_GREY + ";'>None</span>");
				tooltipSB.append("</div>");
	
				// Picture:
				tooltipSB.append("<div class='picture'>" + perk.getSVGString() + "</div>");
	
				// Description:
				tooltipSB.append("<div class='description'>" + perk.getDescription(owner) + "</div>");
	
				Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
				
			} else if(perk instanceof Fetish) {
				int yIncrease = (perk.getModifiersAsStringList().size() > 4 ? perk.getModifiersAsStringList().size() - 4 : 0);
				
				Main.mainController.setTooltipSize(360, 288 + (yIncrease * 20));
	
				// Title:
				tooltipSB.setLength(0);
				tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(perk.getName(owner)) + " fetish</div>");
				
				// Attribute modifiers:
				tooltipSB.append("<div class='subTitle-picture'>");
				if (perk.getModifiersAsStringList().size() != 0) {
					tooltipSB.append("<b style='color:" + Colour.FETISH + ";'>Fetish</b>");
					for (String s : perk.getModifiersAsStringList())
						tooltipSB.append("</br>" + s);
				} else
					tooltipSB.append("<b style='color:" + Colour.FETISH + ";'>Fetish</b>" + "</br><span style='color:" + Colour.TEXT_GREY + ";'>None</span>");
				tooltipSB.append("</div>");
	
				// Picture:
				tooltipSB.append("<div class='picture'>" + perk.getSVGString() + "</div>");
	
				// Description:
				tooltipSB.append("<div class='description'>" + perk.getDescription(owner) + "</div>");
	
				Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
			}

		} else if (levelUpPerk != null) { // Level Up Perk (same as Perk, but with requirements at top):

			int yIncrease = (levelUpPerk.getModifiersAsStringList().size() > 4 ? levelUpPerk.getModifiersAsStringList().size() - 4 : 0) + levelUpPerk.getPerkRequirements(Main.game.getPlayer(), PhoneDialogue.levelUpPerks).size();

			Main.mainController.setTooltipSize(360, 324 + (yIncrease * 20));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(levelUpPerk.getName(owner)) + "</div>");
			
			// Requirements:
			tooltipSB.append("<div class='subTitle'>Requirements");
			for (String s : levelUpPerk.getPerkRequirements(Main.game.getPlayer(), PhoneDialogue.levelUpPerks))
				tooltipSB.append("</br>" + s);
			tooltipSB.append("</div>");

			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");
			if (levelUpPerk.getModifiersAsStringList().size() != 0) {
				tooltipSB.append("<b style='color:" + Colour.PERK + ";'>Perk</b>");
				for (String s : levelUpPerk.getModifiersAsStringList())
					tooltipSB.append("</br>" + s);
			} else
				tooltipSB.append("<b style='color:" + Colour.PERK + ";'>Perk</b>" + "</br><span style='color:" + Colour.TEXT_GREY + ";'>None</span>");
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + levelUpPerk.getSVGString() + "</div>");

			// Description:
			tooltipSB.append("<div class='description'>" + levelUpPerk.getDescription(Main.game.getPlayer()) + "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (fetish != null) { // Fetishes:

			int yIncrease = (fetish.getModifiersAsStringList().size() > 4 ? fetish.getModifiersAsStringList().size() - 4 : 0) + fetish.getFetishesForAutomaticUnlock().size();

			Main.mainController.setTooltipSize(360, (fetish.getFetishesForAutomaticUnlock().size()==0?288:324) + (yIncrease * 20));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(fetish.getName(owner)) + " fetish</div>");
			
			// Requirements:
			if(fetish.getFetishesForAutomaticUnlock().size()>=1) {
				tooltipSB.append("<div class='subTitle'>Requirements");
				for (Fetish f : fetish.getFetishesForAutomaticUnlock())
					tooltipSB.append("</br>[style.boldArcane(" + Util.capitaliseSentence(f.getName(Main.game.getPlayer()))+")]");
				tooltipSB.append("</div>");
			}
			
			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");
			if (fetish.getModifiersAsStringList().size() != 0) {
				tooltipSB.append("<b style='color:" + Colour.FETISH + ";'>Fetish</b>");
				for (String s : fetish.getModifiersAsStringList())
					tooltipSB.append("</br>" + s);
			} else
				tooltipSB.append("<b style='color:" + Colour.FETISH + ";'>Fetish</b>" + "</br><span style='color:" + Colour.TEXT_GREY + ";'>None</span>");
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + fetish.getSVGString() + "</div>");

			// Description:
			tooltipSB.append("<div class='description'>" + fetish.getDescription(owner) + "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (specialAttack != null) { // Special attacks:

			int yIncrease = (specialAttack.getStatusEffects().size() > 2 ? specialAttack.getStatusEffects().size() - 2 : 0);

			Main.mainController.setTooltipSize(360, 324 + (yIncrease * 20));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(specialAttack.getName()) + "</div>");

			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>" + "<b style='color:" + Colour.SPECIAL_ATTACK + ";'>Special Attack</b></br>" + "<b>" + (specialAttack.getMinimumDamage(owner, null)) + "-"
					+ (specialAttack.getMaximumDamage(owner, null)) + "</b>" + " <b style='color:" + specialAttack.getDamageType().getMultiplierAttribute().getColour() + ";'>" + specialAttack.getDamageType().getName()
					+ "</b> damage");

			tooltipSB.append("</br><b style='color:" + Colour.SPECIAL_ATTACK + ";'>Applies</b>");
			if (specialAttack.getStatusEffects().size() != 0) {
				for (Entry<StatusEffect, Integer> e : specialAttack.getStatusEffects().entrySet())
					tooltipSB.append("</br><b style='color:" + e.getKey().getColour() + ";'>" + Util.capitaliseSentence(e.getKey().getName(owner)) + "</b> for " + e.getValue() + " turn" + (e.getValue() > 1 ? "s" : ""));
			} else
				tooltipSB.append("</br><span style='color:" + Colour.TEXT_GREY + ";'>No effects</span>");
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + specialAttack.getSVGString() + "</div>");

			// Description & turns remaining:
			tooltipSB.append("<div class='description'>" + specialAttack.getDescription(owner) + "</div>");

			tooltipSB.append("<div class='subTitle'>"
					+ "<b style='color:" + Colour.GENERIC_BAD + ";'>Costs</b> <b>" + (specialAttack.getMinimumCost(owner)) + " - " + (specialAttack.getMaximumCost(owner)) + "</b>"
							+ " <b style='color:" + Colour.ATTRIBUTE_FITNESS + ";'>stamina</b>" + "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (spell != null) { // Spells:

			int yIncrease = (spell.getStatusEffects().size() > 2 ? spell.getStatusEffects().size() - 2 : 0);

			Main.mainController.setTooltipSize(360, 324 + (yIncrease * 20));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(spell.getName()) + "</div>");

			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>" + "<b style='color:" + Colour.GENERIC_ARCANE + ";'>Spell</b></br>");

			if (spell.isSelfCastSpell())
				tooltipSB.append("<b style='color:" + Colour.GENERIC_GOOD + ";'>Beneficial</b> <b style='color:" + spell.getDamageType().getMultiplierAttribute().getColour() + ";'>" + spell.getDamageType().getName()
						+ "</b> spell");
			else
				tooltipSB.append("<b>" + (spell.getMinimumDamage(owner, null, spellLevel)) + "-" + (spell.getMaximumDamage(owner, null, spellLevel)) + "</b>" + " <b style='color:"
						+ spell.getDamageType().getMultiplierAttribute().getColour() + ";'>" + spell.getDamageType().getName() + "</b> damage");

			tooltipSB.append("</br><b style='color:" + Colour.GENERIC_ARCANE + ";'>Applies</b>");
			if (spell.getStatusEffects().size() != 0) {
				for (Entry<StatusEffect, Integer> e : spell.getStatusEffects().entrySet())
					tooltipSB.append("</br><b style='color:" + e.getKey().getColour() + ";'>" + Util.capitaliseSentence(e.getKey().getName(owner)) + "</b> for " + e.getValue() + " turn" + (e.getValue() > 1 ? "s" : ""));
			} else
				tooltipSB.append("</br><span style='color:" + Colour.TEXT_GREY + ";'>No effects</span>");

			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + spell.getSVGString() + "</div>");

			// Description & turns remaining:
			tooltipSB.append("<div class='description'>" + spell.getDescription(owner, spellLevel) + "</div>");

			tooltipSB.append("<div class='subTitle'>"
					+ "<b style='color:" + Colour.GENERIC_BAD + ";'>Costs</b> <b>" + (spell.getMinimumCost(owner, spellLevel)) + " - " + (spell.getMaximumCost(owner, spellLevel)) + "</b>"
							+ " <b style='color:" + Colour.ATTRIBUTE_MANA + ";'>willpower</b>" + "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (attribute != null) {
			
			if (attribute == Attribute.STRENGTH || attribute == Attribute.INTELLIGENCE || attribute == Attribute.FITNESS || attribute == Attribute.CORRUPTION || attribute == Attribute.AROUSAL) {
				StatusEffect currentAttributeStatusEffect=null;
				int minimumLevelValue=0, maximumLevelValue=0;
				
				if(attribute == Attribute.STRENGTH) {
					currentAttributeStatusEffect = StrengthLevel.getStrengthLevelFromValue(owner.getAttributeValue(Attribute.STRENGTH)).getRelatedStatusEffect();
					minimumLevelValue = StrengthLevel.getStrengthLevelFromValue(owner.getAttributeValue(Attribute.STRENGTH)).getMinimumValue();
					maximumLevelValue = StrengthLevel.getStrengthLevelFromValue(owner.getAttributeValue(Attribute.STRENGTH)).getMaximumValue();
					
				} else if(attribute == Attribute.INTELLIGENCE) {
					currentAttributeStatusEffect = IntelligenceLevel.getIntelligenceLevelFromValue(owner.getAttributeValue(Attribute.INTELLIGENCE)).getRelatedStatusEffect();
					minimumLevelValue = IntelligenceLevel.getIntelligenceLevelFromValue(owner.getAttributeValue(Attribute.INTELLIGENCE)).getMinimumValue();
					maximumLevelValue = IntelligenceLevel.getIntelligenceLevelFromValue(owner.getAttributeValue(Attribute.INTELLIGENCE)).getMaximumValue();
					
				} else if(attribute == Attribute.FITNESS) {
					currentAttributeStatusEffect = FitnessLevel.getFitnessLevelFromValue(owner.getAttributeValue(Attribute.FITNESS)).getRelatedStatusEffect();
					minimumLevelValue = FitnessLevel.getFitnessLevelFromValue(owner.getAttributeValue(Attribute.FITNESS)).getMinimumValue();
					maximumLevelValue = FitnessLevel.getFitnessLevelFromValue(owner.getAttributeValue(Attribute.FITNESS)).getMaximumValue();
					
				} else if(attribute == Attribute.CORRUPTION) {
					currentAttributeStatusEffect = CorruptionLevel.getCorruptionLevelFromValue(owner.getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect();
					minimumLevelValue = CorruptionLevel.getCorruptionLevelFromValue(owner.getAttributeValue(Attribute.CORRUPTION)).getMinimumValue();
					maximumLevelValue = CorruptionLevel.getCorruptionLevelFromValue(owner.getAttributeValue(Attribute.CORRUPTION)).getMaximumValue();
					
				} else {
					currentAttributeStatusEffect = ArousalLevel.getArousalLevelFromValue(owner.getAttributeValue(Attribute.AROUSAL)).getRelatedStatusEffect();
					minimumLevelValue = ArousalLevel.getArousalLevelFromValue(owner.getAttributeValue(Attribute.AROUSAL)).getMinimumValue();
					maximumLevelValue = ArousalLevel.getArousalLevelFromValue(owner.getAttributeValue(Attribute.AROUSAL)).getMaximumValue();
				}
				
				
				int yIncrease = (currentAttributeStatusEffect.getModifiersAsStringList(owner).size() > 4 ? currentAttributeStatusEffect.getModifiersAsStringList(owner).size() - 4 : 0)
						+ (owner.hasStatusEffect(currentAttributeStatusEffect)?(owner.getStatusEffectDuration(currentAttributeStatusEffect) == -1 ? 0 : 2):0);

				Main.mainController.setTooltipSize(360, 460 + (yIncrease * 20));
				
				tooltipSB.setLength(0);
				tooltipSB.append("<div class='title' style='color:" + attribute.getColour() + ";'>" + Util.capitaliseSentence(attribute.getName()) + "</div>"

						+ "<div class='subTitle-third'>" + "<b style='color:" + Colour.TEXT_GREY + ";'>Core</b></br>"
						+ (owner.getBaseAttributeValue(attribute) > 0 ? "<span style='color: " + Colour.GENERIC_EXCELLENT.getShades()[1] + ";'>" : "<span>") + String.format("%.2f", owner.getBaseAttributeValue(attribute)) + "</span>" + "</div>"
						
						+ "<div class='subTitle-third'>" + "<b style='color:" + Colour.TEXT_GREY + ";'>Bonus</b></br>"
						+ ((owner.getBonusAttributeValue(attribute)) > 0 ? "<span style='color: " + Colour.GENERIC_GOOD.getShades()[1] + ";'>"
								: ((owner.getBonusAttributeValue(attribute)) == 0 ? "<span style='color:" + Colour.TEXT_GREY + ";'>" : "<span style='color: " + Colour.GENERIC_BAD.getShades()[1] + ";'>"))
						+ String.format("%.2f", owner.getBonusAttributeValue(attribute))+ "</span>" + "</div>"
						
						+ "<div class='subTitle-third'>" + "<b style='color:" + attribute.getColour() + ";'>Total</b></br>" + String.format("%.2f", owner.getAttributeValue(attribute))
						+ "</span>" + "</div>");
				
				tooltipSB.append("<div class='description-half'>" + attribute.getDescription(owner) + "</div>");
				
				// Related status effect:
				tooltipSB.append("<div class='title'>"
												+ "<span style='color:"+currentAttributeStatusEffect.getColour()+";'>"
												+ currentAttributeStatusEffect.getName(owner)
												+"</span> ("+minimumLevelValue
												+"-"
												+ maximumLevelValue
												+")"
												+ "</div>");
			
				// Attribute modifiers:
				tooltipSB.append("<div class='subTitle-picture'>");
				if (currentAttributeStatusEffect.getModifiersAsStringList(owner).size() != 0) {
					tooltipSB.append("<b style='color:" + Colour.STATUS_EFFECT + ";'>Status Effect</b>");
					for (String s : currentAttributeStatusEffect.getModifiersAsStringList(owner))
						tooltipSB.append("</br>" + s);
				} else
					tooltipSB.append("<b style='color:" + Colour.STATUS_EFFECT + ";'>Status Effect</b>" + "</br><span style='color:" + Colour.TEXT_GREY + ";'>No bonuses</span>");
				tooltipSB.append("</div>");
			
				// Picture:
				tooltipSB.append("<div class='picture'>" + currentAttributeStatusEffect.getSVGString(owner) + "</div>");
			
				// Description & turns remaining:
				tooltipSB.append("<div class='description'>" + currentAttributeStatusEffect.getDescription(owner) + "</div>");

				Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

			} else if (attribute == Attribute.EXPERIENCE) {
				// Special tooltip for experience/transformation combo:

				Main.mainController.setTooltipSize(360, 550);

				tooltipSB.setLength(0);
				tooltipSB.append("<div class='title' style='color:" + owner.getRace().getColour() + ";'>"
						+(owner.getRaceStage().getName()!=""?"<b style='color:"+owner.getRaceStage().getColour()+";'>" + Util.capitaliseSentence(owner.getRaceStage().getName())+"</b> ":"")
						+ "<b style='color:"+owner.getRace().getColour()+";'>"
						+ (owner.isFeminine()?Util.capitaliseSentence(owner.getRace().getSingularFemaleName()):Util.capitaliseSentence(owner.getRace().getSingularMaleName()))
						+ "</b>"
						+ "</div>");
				
				if(owner.isPlayer()) {
					tooltipSB.append("<div class='subTitle'>" + "Level " + owner.getLevel() + " <span style='color:" + Colour.TEXT_GREY + ";'>|</span> " + ((PlayerCharacter)owner).getExperience() + " / "
							+ (10 * owner.getLevel()) + " xp" + "</div>");
				} else {
					tooltipSB.append("<div class='subTitle'>" + "Level " + owner.getLevel() + "</div>");
				}

				// GREATER:
				// Face:
				tooltipSB.append("<div class='subTitle-half body'>" + "Face - <span style='color:" + owner.getFaceRace().getColour() + ";'>"
						+ Util.capitaliseSentence(owner.getFaceRace().getName()) + "</span></br>" + "<span style='color:" + owner.getSkinColour(owner.getFaceType().getSkinType())
						+ ";'>" + Util.capitaliseSentence(owner.getSkinColour(owner.getFaceType().getSkinType()).getName()) + "</span> " + owner.getFaceType().getSkinType().getName(owner) + "</div>");
				// Skin:
				tooltipSB.append("<div class='subTitle-half body'>" + "Body - <span style='color:" + owner.getSkinRace().getColour() + ";'>"
						+ Util.capitaliseSentence(owner.getSkinRace().getName()) + "</span></br>" + "<span style='color:" + owner.getSkinColour(owner.getSkinType()) + ";'>"
						+ Util.capitaliseSentence(owner.getSkinColour(owner.getSkinType()).getName()) + "</span> " + owner.getSkinName() + "</div>");

				// LESSER:
				// Arms:
				tooltipSB.append("<div class='subTitle-half body'>" + "Arms - <span style='color:" + owner.getArmRace().getColour() + ";'>" + Util.capitaliseSentence(owner.getArmRace().getName())
						+ "</span></br>" + "<span style='color:" + owner.getSkinColour(owner.getArmType().getSkinType()) + ";'>"
						+ Util.capitaliseSentence(owner.getSkinColour(owner.getArmType().getSkinType()).getName()) + "</span> " + owner.getArmType().getSkinType().getName(owner) + "</div>");
				// Legs:
				tooltipSB.append("<div class='subTitle-half body'>" + "Legs - <span style='color:" + owner.getLegRace().getColour() + ";'>" + Util.capitaliseSentence(owner.getLegRace().getName())
						+ "</span></br>" + "<span style='color:" + owner.getSkinColour(owner.getLegType().getSkinType()) + ";'>"
						+ Util.capitaliseSentence(owner.getSkinColour(owner.getLegType().getSkinType()).getName()) + "</span> " + owner.getLegType().getSkinType().getName(owner) + "</div>");

				// PARTIAL:
				// Hair:
				tooltipSB.append("<div class='subTitle-half body'>" + "Hair - <span style='color:" + owner.getHairRace().getColour() + ";'>"
						+ Util.capitaliseSentence(owner.getHairRace().getName()) + "</span></br>" + "<span style='color:" + owner.getHairColour() + ";'>"
						+ Util.capitaliseSentence(owner.getHairColour().getName()) + "</span> " + owner.getHairType().getName(owner) + "</div>");
				// Eye:
				tooltipSB.append("<div class='subTitle-half body'>" + "Eyes - <span style='color:" + owner.getEyeRace().getColour() + ";'>" + Util.capitaliseSentence(owner.getEyeRace().getName())
						+ "</span></br>" + "<span style='color:" + owner.getEyeColour() + ";'>" + Util.capitaliseSentence(owner.getEyeColour().getName()) + "</span> "
						+ owner.getEyeType().getName(owner) + "</div>");
				// Ear:
				tooltipSB.append("<div class='subTitle-half body'>" + "Ears - <span style='color:" + owner.getEarRace().getColour() + ";'>" + Util.capitaliseSentence(owner.getEarRace().getName())
						+ "</span></br>" + "<span style='color:" + owner.getSkinColour(owner.getEarType().getSkinType()) + ";'>"
						+ Util.capitaliseSentence(owner.getSkinColour(owner.getEarType().getSkinType()).getName()) + "</span> " + owner.getEarType().getSkinType().getName(owner) + "</div>");
				// Horn:
				if (owner.getHornType() != HornType.NONE)
					tooltipSB.append("<div class='subTitle-half body'>" + "Horns - <span style='color:" + owner.getHornRace().getColour() + ";'>"
							+ Util.capitaliseSentence(owner.getHornRace().getName()) + "</span></br>" + "<span style='color:"
							+ owner.getSkinColour(owner.getHornType().getSkinType()) + ";'>"
							+ Util.capitaliseSentence(owner.getSkinColour(owner.getHornType().getSkinType()).getName()) + "</span> " + owner.getHornType().getSkinType().getName(owner) + "</div>");
				else
					tooltipSB.append("<div class='subTitle-half body'>" + "Horns - <span style='color:" + Colour.TEXT_GREY + ";'>None</span>" + "</div>");
				// Wing:
				if (owner.getWingType() != WingType.NONE)
					tooltipSB.append("<div class='subTitle-half body'>" + "Wings - <span style='color:" + owner.getWingRace().getColour() + ";'>"
							+ Util.capitaliseSentence(owner.getWingRace().getName()) + "</span></br>" + "<span style='color:"
							+ owner.getSkinColour(owner.getWingType().getSkinType()) + ";'>"
							+ Util.capitaliseSentence(owner.getSkinColour(owner.getWingType().getSkinType()).getName()) + "</span> " + owner.getWingType().getSkinType().getName(owner) + "</div>");
				else
					tooltipSB.append("<div class='subTitle-half body'>" + "Wings - <span style='color:" + Colour.TEXT_GREY + ";'>None</span>" + "</div>");
				// Tail:
				if (owner.getTailType() != TailType.NONE)
					tooltipSB.append("<div class='subTitle-half body'>" + "Tail - <span style='color:" + owner.getTailRace().getColour() + ";'>"
							+ Util.capitaliseSentence(owner.getTailRace().getName()) + "</span></br>" + "<span style='color:"
							+ owner.getSkinColour(owner.getTailType().getSkinType()) + ";'>"
							+ Util.capitaliseSentence(owner.getSkinColour(owner.getTailType().getSkinType()).getName()) + "</span> " + owner.getTailType().getSkinType().getName(owner) + "</div>");
				else
					tooltipSB.append("<div class='subTitle-half body'>" + "Tail - <span style='color:" + Colour.TEXT_GREY + ";'>None</span>" + "</div>");

				// SEXUAL:
				// Vagina:
				if (owner.getVaginaType() != VaginaType.NONE)
					tooltipSB.append("<div class='subTitle-half body'>" + "<span style='color:" + Colour.GENERIC_SEX + ";'>Vagina</span> - " + "<span style='color:" + owner.getVaginaRace().getColour()
							+ ";'>" + Util.capitaliseSentence(owner.getVaginaRace().getName()) + "</span></br>" + "<span style='color:"
							+ owner.getSkinColour(owner.getVaginaType().getSkinType()) + ";'>"
							+ Util.capitaliseSentence(owner.getSkinColour(owner.getVaginaType().getSkinType()).getName()) + "</span> " + owner.getVaginaType().getSkinType().getName(owner) + "</div>");
				else
					tooltipSB.append("<div class='subTitle-half body'>" + "<span style='color:" + Colour.GENERIC_SEX + ";'>Vagina</span> - <span style='color:" + Colour.TEXT_GREY + ";'>None</span>" + "</div>");
				// Penis:
				if (owner.getPenisType() != PenisType.NONE)
					tooltipSB.append("<div class='subTitle-half body'>" + "<span style='color:" + Colour.GENERIC_SEX + ";'>Penis</span> - " + "<span style='color:" + owner.getPenisRace().getColour()
							+ ";'>" + Util.capitaliseSentence(owner.getPenisRace().getName()) + "</span></br>" + "<span style='color:"
							+ owner.getSkinColour(owner.getPenisType().getSkinType()) + ";'>"
							+ Util.capitaliseSentence(owner.getSkinColour(owner.getPenisType().getSkinType()).getName()) + "</span> " + owner.getPenisType().getSkinType().getName(owner) + "</div>");
				else
					tooltipSB.append("<div class='subTitle-half body'>" + "<span style='color:" + Colour.GENERIC_SEX + ";'>Penis</span> - <span style='color:" + Colour.TEXT_GREY + ";'>None</span>" + "</div>");
				// Ass:
				tooltipSB.append("<div class='subTitle-half body'>" + "<span style='color:" + Colour.GENERIC_SEX + ";'>Ass</span> - " + "<span style='color:" + owner.getAssRace().getColour() + ";'>"
						+ Util.capitaliseSentence(owner.getAssRace().getName()) + "</span></br>" + "<span style='color:" + owner.getSkinColour(owner.getSkinType()) + ";'>"
						+ Util.capitaliseSentence(owner.getSkinColour(owner.getSkinType()).getName()) + "</span> " + owner.getSkinType().getName(owner) + "</div>");
				// Breasts:
				tooltipSB.append("<div class='subTitle-half body'>" + "<span style='color:" + Colour.GENERIC_SEX + ";'>" + (owner.getBreastSize().getMeasurement() > 0 ? "Breasts" : "Chest") + "</span> - "
						+ "<span style='color:" + owner.getBreastRace().getColour() + ";'>" + Util.capitaliseSentence(owner.getBreastRace().getName()) + "</span></br>" + "<span style='color:"
						+ owner.getSkinColour(owner.getSkinType()) + ";'>" + Util.capitaliseSentence(owner.getSkinColour(owner.getSkinType()).getName())
						+ "</span> " + owner.getSkinType().getName(owner) + "</div>");

				Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

			} else {
				Main.mainController.setTooltipSize(360, 234);

				Main.mainController.setTooltipContent(UtilText.parse(
						"<div class='title' style='color:" + attribute.getColour() + ";'>" + Util.capitaliseSentence(attribute.getName()) + "</div>"

						+ "<div class='subTitle-third'>"
						+ "<b style='color:"
						+ Colour.TEXT_GREY
						+ ";'>Core</b></br>"
						+ (owner.getBaseAttributeValue(attribute) > 0 ? "<span style='color: "
								+ Colour.GENERIC_EXCELLENT.getShades()[1]
								+ ";'>" : "<span>")
						+ owner.getBaseAttributeValue(attribute)
						+ "</span>"
						+ "</div>"
						+ "<div class='subTitle-third'>"
						+ "<b style='color:"
						+ Colour.TEXT_GREY
						+ ";'>Bonus</b></br>"
						+ ((owner.getBonusAttributeValue(attribute)) > 0 ? "<span style='color: "
								+ Colour.GENERIC_GOOD.getShades()[1]
								+ ";'>"
								: ((owner.getBonusAttributeValue(attribute)) == 0 ? "<span style='color:"
										+ Colour.TEXT_GREY
										+ ";'>"
										: "<span style='color: "
												+ Colour.GENERIC_BAD.getShades()[1]
												+ ";'>"))
						+ owner.getBonusAttributeValue(attribute)
						+ "</span>"
						+ "</div>"
						+ "<div class='subTitle-third'>"
						+ "<b style='color:"
						+ attribute.getColour()
						+ ";'>Total</b></br>"
						+ owner.getAttributeValue(attribute)
						+ "</span>"
						+ "</div>"

						+ "<div class='description'>" + attribute.getDescription(owner) + "</div>"));
				
			}

		} else if (extraAttributes) {

			Main.mainController.setTooltipSize(360, 600);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title' style='color:" + Femininity.valueOf(Main.game.getPlayer().getFemininity()).getColour() + ";'>"
					+ (Main.game.getPlayer().getName().length() == 0 ? (Main.game.getPlayer().getFemininity() <= Femininity.MASCULINE.getMaximumFemininity() ? "Hero" : "Heroine") : Main.game.getPlayer().getName()) + "</div>"

					+ extraAttributeBonus(Main.game.getPlayer(), Attribute.CRITICAL_CHANCE)
					+ extraAttributeBonus(Main.game.getPlayer(), Attribute.CRITICAL_DAMAGE)

					// Header:
					+ "<div class='subTitle-third combatValue'>" + "Type" + "</div>" + "<div class='subTitle-third combatValue'>" + "Damage" + "</div>" + "<div class='subTitle-third combatValue'>" + "Resist" + "</div>"

					// Values:
					+ extraAttributeTableRow(Main.game.getPlayer(), "Melee", Attribute.DAMAGE_ATTACK, Attribute.RESISTANCE_ATTACK)
					+ extraAttributeTableRow(Main.game.getPlayer(), "Spell", Attribute.DAMAGE_SPELLS, Attribute.RESISTANCE_SPELLS)

					+ extraAttributeTableRow(Main.game.getPlayer(), "Physical", Attribute.DAMAGE_PHYSICAL, Attribute.RESISTANCE_PHYSICAL)
					+ extraAttributeTableRow(Main.game.getPlayer(), "Fire", Attribute.DAMAGE_FIRE, Attribute.RESISTANCE_FIRE)
					+ extraAttributeTableRow(Main.game.getPlayer(), "Cold", Attribute.DAMAGE_ICE, Attribute.RESISTANCE_ICE)
					+ extraAttributeTableRow(Main.game.getPlayer(), "Poison", Attribute.DAMAGE_POISON, Attribute.RESISTANCE_POISON)
					+ extraAttributeTableRow(Main.game.getPlayer(), "Willpower", Attribute.DAMAGE_MANA, Attribute.RESISTANCE_MANA)
					+ extraAttributeTableRow(Main.game.getPlayer(), "Stamina", Attribute.DAMAGE_STAMINA, Attribute.RESISTANCE_STAMINA)

					+ extraAttributeTableRow(Main.game.getPlayer(), "Pure", Attribute.DAMAGE_PURE, Attribute.DAMAGE_PURE)
					
					+ extraAttributeBonus(Main.game.getPlayer(), Attribute.FERTILITY)
					+ extraAttributeBonus(Main.game.getPlayer(), Attribute.VIRILITY)
					
					+ extraAttributeBonus(Main.game.getPlayer(), Attribute.SPELL_COST_MODIFIER));

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (opponentExtraAttributes) {

			Main.mainController.setTooltipSize(360, 480);

			tooltipSB.setLength(0);
			GameCharacter target = null;
			if(Main.game.isInCombat()) {
				target = Combat.getOpponent();
			} else {
				target = CharactersPresentDialogue.characterViewed;
			}
			tooltipSB.append("<div class='title' style='color:" + Femininity.valueOf(target.getFemininity()).getColour() + ";'>" + Util.capitaliseSentence(target.getName()) + "</div>");

			tooltipSB.append(
					extraAttributeBonus(target, Attribute.CRITICAL_CHANCE)
					+ extraAttributeBonus(target, Attribute.CRITICAL_DAMAGE)

					// Header:
					+ "<div class='subTitle-third combatValue'>" + "Type" + "</div>" + "<div class='subTitle-third combatValue'>" + "&#8224 Damage &#8224" + "</div>" + "<div class='subTitle-third combatValue'>" + "&#8225 Resist &#8225" + "</div>"

					// Values:
					+ extraAttributeTableRow(target, "Melee", Attribute.DAMAGE_ATTACK, null)
					+ extraAttributeTableRow(target, "Spell", Attribute.DAMAGE_SPELLS, null)

					+ extraAttributeTableRow(target, "Physical", Attribute.DAMAGE_PHYSICAL, Attribute.RESISTANCE_PHYSICAL)
					+ extraAttributeTableRow(target, "Fire", Attribute.DAMAGE_FIRE, Attribute.RESISTANCE_FIRE) + extraAttributeTableRow(target, "Cold", Attribute.DAMAGE_ICE, Attribute.RESISTANCE_ICE)
					+ extraAttributeTableRow(target, "Poison", Attribute.DAMAGE_POISON, Attribute.RESISTANCE_POISON)
					+ extraAttributeTableRow(target, "Willpower", Attribute.DAMAGE_MANA, Attribute.RESISTANCE_MANA)
					+ extraAttributeTableRow(target, "Stamina", Attribute.DAMAGE_STAMINA, Attribute.RESISTANCE_STAMINA)

					+ extraAttributeTableRow(target, "Pure", Attribute.DAMAGE_PURE, Attribute.DAMAGE_PURE));

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (weather) {

			Main.mainController.setTooltipSize(360, 100);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + "<b style='color:" + Main.game.getCurrentWeather().getColour() + ";'>" + Util.capitaliseSentence(Main.game.getCurrentWeather().getName()) + "</b>" + "</div>"
					+ "<div class='title'>" + "<b>" + ((Main.game.getWeatherTimeRemaining() / 60) + 1) + " hour" + (((Main.game.getWeatherTimeRemaining() / 60) + 1) > 1 ? "s" : "") + " remaining" + "</b>" + "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (protection) {

			Main.mainController.setTooltipSize(360, 100);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>Protection</div>"
					+ "<div class='subTitle'>"
					+ (owner.isWearingCondom()?"<span style='color:"+Colour.GENERIC_GOOD+";'>Wearing Condom</span>":"<span style='color:"+Colour.GENERIC_BAD+";'>No Condom</span>")
					+"</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (tattoo) {

			Main.mainController.setTooltipSize(360, 100);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>Tattoos</div>"
					+ "<div class='subTitle'>"
					+ "TODO"
					+"</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (copyInformation) {

			Main.mainController.setTooltipSize(360, 170);

			tooltipSB.setLength(0);
			tooltipSB.append(
					"<div class='subTitle'>"
					+(Main.game.getCurrentDialogueNode().getLabel() == "" || Main.game.getCurrentDialogueNode().getLabel() == null ? "-" : Main.game.getCurrentDialogueNode().getLabel())
					+ "</div>"
					+ "<div class='description'>"
					+ "Click to copy the currently displayed dialogue to your clipboard.</br></br>"
					+ "This scene was written by <b style='color:"+Colour.ANDROGYNOUS+";'>"
					+ Main.game.getCurrentDialogueNode().getAuthor()
					+ "</b></div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else { // Standard information:

			Main.mainController.setTooltipSize(360, 175);

			Main.mainController.setTooltipContent(UtilText.parse(
					"<div class='title'>"+title+"</div>"
					+ "<div class='description'>" + description + "</div>"));
		}

		(new Thread(new TooltipUpdateThread())).start();
		// Main.mainController.getTooltip().show(Main.primaryStage);
	}

	private String extraAttributeTableRow(GameCharacter owner, String type, Attribute damage, Attribute resist) {
//		if (owner.isPlayer() || Main.game.getPlayer().getPerks().contains(Perk.OBSERVANT))
			return "<div class='subTitle-third combatValue'>" + "<span style='color:" + damage.getColour() + ";'>" + type + "</span>" + "</div>" + "<div class='subTitle-third combatValue'>"
					+ (owner.getAttributeValue(damage) > damage.getBaseValue()
											? "<span style='color:" + Colour.GENERIC_GOOD + ";'>"
											: (owner.getAttributeValue(damage) < damage.getBaseValue()
													? "<span style='color:" + Colour.GENERIC_BAD + ";'>"
													: ""))
					+ owner.getAttributeValue(damage)
					+ "</span>" + "</div>" + "<div class='subTitle-third combatValue'>"
					+ (resist == null ? "0.0"
							: (owner.getAttributeValue(resist) > 0 ? "<span style='color:" + Colour.GENERIC_GOOD + ";'>"
									: (owner.getAttributeValue(resist) < 0 ? "<span style='color:" + Colour.GENERIC_BAD + ";'>" : "")) + owner.getAttributeValue(resist) + "</span>")
					+ "</div>";
//		else
//			return "<div class='subTitle-third combatValue'>" + "<span style='color:" + damage.getColour() + ";'>" + type + "</span>" + "</div>" + "<div class='subTitle-third combatValue'>" + "<span style='color:"
//					+ Colour.TEXT_GREY + ";'>???</span>" + "</div>" + "<div class='subTitle-third combatValue'>" + "<span style='color:" + Colour.TEXT_GREY + ";'>???</span>" + "</div>";
	}

	private String extraAttributeBonus(GameCharacter owner, Attribute bonus) {
//		if (owner.isPlayer() || Main.game.getPlayer().getPerks().contains(Perk.OBSERVANT))
			return "<div class='subTitle-half'>" + "<span style='color:"
					+ bonus.getColour() + ";'>" + Util.capitaliseSentence(bonus.getName()) + "</span></br>" + (owner.getAttributeValue(bonus) > bonus.getBaseValue()
							? "<span style='color:" + Colour.GENERIC_GOOD + ";'>" : (owner.getAttributeValue(bonus) < bonus.getBaseValue() ? "<span style='color:" + Colour.GENERIC_BAD + ";'>" : ""))
					+ owner.getAttributeValue(bonus) + "</span>" + "</div>";
//		else
//			return "<div class='subTitle-half'>" + "<span style='color:" + bonus.getColour() + ";'>" + Util.capitaliseSentence(bonus.getName()) + "</span></br>" + "<span style='color:" + Colour.TEXT_GREY
//					+ ";'>???</span>" + "</div>";
	}
//	
//	private String corruptionLevelRow(AttributeLevelCorruption level){
//		if(Main.game.getPlayer().getCorruptionLevel()==level)
//			return "<div class='subTitle'><span style='color:"+level.getColour()+";'>"+Utilities.capitaliseSentence(level.getName())+"</span>" + " ("+level.getMinimumValue()+"-"+level.getMaximumValue()+")</div>";
//		else
//			return "<div class='subTitle'><span style='color:"+Colour.TEXT_GREY+";'>"+Utilities.capitaliseSentence(level.getName())+"" + " ("+level.getMinimumValue()+"-"+level.getMaximumValue()+")</span></div>";
//	}

	public TooltipInformationEventListener setInformation(String title, String description) {
		resetFields();
		this.title = title;
		this.description = description;

		return this;
	}

	public TooltipInformationEventListener setWeather() {
		resetFields();
		weather = true;

		return this;
	}

	public TooltipInformationEventListener setExtraAttributes() {
		resetFields();
		extraAttributes = true;

		return this;
	}

	public TooltipInformationEventListener setOpponentExtraAttributes() {
		resetFields();
		opponentExtraAttributes = true;

		return this;
	}

	public TooltipInformationEventListener setStatusEffect(StatusEffect statusEffect, GameCharacter owner) {
		resetFields();
		this.statusEffect = statusEffect;
		this.owner = owner;

		return this;
	}

	public TooltipInformationEventListener setPerk(PerkInterface perk, GameCharacter owner) {
		resetFields();
		this.perk = perk;
		this.owner = owner;

		return this;
	}
	
	public TooltipInformationEventListener setFetish(Fetish fetish, GameCharacter owner) {
		resetFields();
		this.fetish = fetish;
		this.owner = owner;

		return this;
	}

	public TooltipInformationEventListener setLevelUpPerk(PerkInterface levelUpPerk, GameCharacter owner) {
		resetFields();
		this.levelUpPerk = levelUpPerk;
		this.owner = owner;

		return this;
	}

	public TooltipInformationEventListener setSpecialAttack(SpecialAttack specialAttack, GameCharacter owner) {
		resetFields();
		this.specialAttack = specialAttack;
		this.owner = owner;

		return this;
	}

	public TooltipInformationEventListener setSpell(Spell spell, int spellLevel, GameCharacter owner) {
		resetFields();
		this.spell = spell;
		this.spellLevel = spellLevel;
		this.owner = owner;

		return this;
	}

	public TooltipInformationEventListener setAttribute(Attribute attribute, GameCharacter owner) {
		resetFields();
		this.attribute = attribute;
		this.owner = owner;

		return this;
	}
	
	public TooltipInformationEventListener setProtection(GameCharacter owner) {
		resetFields();
		this.owner = owner;
		protection=true;

		return this;
	}
	
	public TooltipInformationEventListener setTattoo(GameCharacter owner) {
		resetFields();
		this.owner = owner;
		tattoo=true;

		return this;
	}
	
	public TooltipInformationEventListener setCopyInformation() {
		resetFields();
		copyInformation = true;

		return this;
	}
	

	private void resetFields() {
		extraAttributes = false;
		opponentExtraAttributes = false;
		weather = false;
		owner = null;
		statusEffect = null;
		perk = null;
		fetish = null;
		levelUpPerk = null;
		specialAttack = null;
		spell = null;
		spellLevel = 1;
		attribute = null;
		protection=false;
		tattoo=false;
		copyInformation=false;
	}
}
