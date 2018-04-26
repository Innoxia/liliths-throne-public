package com.lilithsthrone.controller.eventListeners;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.fetishes.FetishLevel;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.combat.SpecialAttack;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellUpgrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public class TooltipInformationEventListener implements EventListener {
	private String title, description;
	private boolean extraAttributes = false, weather = false, protection = false, tattoo = false, copyInformation=false;
	private GameCharacter owner;
	private StatusEffect statusEffect;
	private Perk perk, levelUpPerk;
	private int perkRow;
	private Fetish fetish;
	private boolean fetishExperience = false;
	private FetishDesire desire;
	private SpecialAttack specialAttack;
	private Spell spell;
	private SpellUpgrade spellUpgrade;
	private Attribute attribute;
	private InventorySlot concealedSlot;
	private static StringBuilder tooltipSB  = new StringBuilder();
	
	
	private static final int LINE_HEIGHT= 16;

	
	@Override
	public void handleEvent(Event event) {
		Main.mainController.setTooltipSize(360, 180);
		Main.mainController.setTooltipContent("");

		if (statusEffect != null) {
			
			int yIncrease = (statusEffect.getModifiersAsStringList(owner).size() > 4 ? statusEffect.getModifiersAsStringList(owner).size() - 4 : 0)
								+ (owner.hasStatusEffect(statusEffect)?(owner.getStatusEffectDuration(statusEffect) == -1 ? 0 : 2):0);

			Main.mainController.setTooltipSize(360, 284 + (yIncrease * LINE_HEIGHT));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<body>"
					+ "<div class='title'>" + Util.capitaliseSentence(statusEffect.getName(owner)) + "</div>");

			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");
				if (!statusEffect.getModifiersAsStringList(owner).isEmpty()) {
					int i=0;
					for (String s : statusEffect.getModifiersAsStringList(owner)) {
						tooltipSB.append((i!=0?"</br>":"") + s);
						i++;
					}
					
				} else {
					tooltipSB.append("<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No bonuses</span>");
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
								+ "<b style='color:"+timerColour.toWebHexString()+";'>" + (owner.getStatusEffectDuration(statusEffect) / 60) + ":" + String.format("%02d", (owner.getStatusEffectDuration(statusEffect) % 60)) + "</b>"
								+ "</div>");
						//STATUS_EFFECT_TIME_OVERFLOW
					}
				}
			
			tooltipSB.append("</body>");
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (perk != null) { // Perks:
			
			int yIncrease = (perk.getModifiersAsStringList().size() > 4 ? perk.getModifiersAsStringList().size() - 4 : 0);

			Main.mainController.setTooltipSize(360, 324 + (yIncrease * LINE_HEIGHT));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(perk.getName(owner)) + "</div>");

			if(perk.isEquippableTrait()) {
				tooltipSB.append("<div class='subTitle' style='color:"+Colour.TRAIT.toWebHexString()+";'>Trait</div>");
			} else {
				tooltipSB.append("<div class='subTitle' style='color:"+Colour.PERK.toWebHexString()+";'>Perk</div>");
			}
			
			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");
			if (!perk.getModifiersAsStringList().isEmpty()) {
				int i=0;
				for (String s : perk.getModifiersAsStringList()) {
					tooltipSB.append((i!=0?"</br>":"") + s);
					i++;
				}
			} else
				tooltipSB.append("<b style='color:" + Colour.PERK.toWebHexString() + ";'>Perk</b>" + "</br><span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>None</span>");
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + perk.getSVGString() + "</div>");

			// Description:
			tooltipSB.append("<div class='description'>" + perk.getDescription(owner) + "</div>");
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
				
			
		} else if (levelUpPerk != null) { // Level Up Perk (same as Perk, but with requirements at top):

			int yIncrease = (levelUpPerk.getModifiersAsStringList().size() > 4 ? levelUpPerk.getModifiersAsStringList().size() - 4 : 0);

			Main.mainController.setTooltipSize(360, 352 + (yIncrease * LINE_HEIGHT));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(levelUpPerk.getName(owner)) + "</div>");
			
			if(levelUpPerk.isEquippableTrait()) {
				tooltipSB.append("<div class='subTitle' style='color:"+Colour.TRAIT.toWebHexString()+";'>Trait</div>");
			} else {
				tooltipSB.append("<div class='subTitle' style='color:"+Colour.PERK.toWebHexString()+";'>Perk</div>");
			}
			
			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");
			if (!levelUpPerk.getModifiersAsStringList().isEmpty()) {
				int i=0;
				for (String s : levelUpPerk.getModifiersAsStringList()) {
					tooltipSB.append((i!=0?"</br>":"") + s);
					i++;
				}
			} else {
				tooltipSB.append("<b style='color:" + Colour.PERK.toWebHexString() + ";'>Perk</b>" + "</br><span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>None</span>");
			}
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + levelUpPerk.getSVGString() + "</div>");

			// Description:
			tooltipSB.append("<div class='description'>" + levelUpPerk.getDescription(Main.game.getPlayer()) + "</div>");

			if(levelUpPerk.isEquippableTrait()) {
				if(levelUpPerk.getPerkCategory()==PerkCategory.JOB) {
					tooltipSB.append("<div class='subTitle' style='color:"+Colour.TRAIT.toWebHexString()+";'>Job-related trait cannot be removed.</div>");
					
				} else {
					if(!owner.hasPerkInTree(perkRow, levelUpPerk)) {
						if(!PerkManager.MANAGER.isPerkAvailable(perkRow, levelUpPerk)) {
							tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Purchasing requires a connecting perk or trait.</div>");
						} else {
							tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_MINOR_GOOD.toWebHexString()+";'>Click to purchase trait.</div>");
						}
					} else {
						if(owner.getTraits().contains(levelUpPerk)) {
							tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_MINOR_BAD.toWebHexString()+";'>Click to unequip trait.</div>");
						} else {
							if(owner.getTraits().size()==GameCharacter.MAX_TRAITS) {
								tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Maximum traits activated.</div>");
							} else {
								tooltipSB.append("<div class='subTitle' style='color:"+Colour.TRAIT.toWebHexString()+";'>Click to equip trait.</div>");
							}
						}
					}
				}
				
			} else {
				if(!owner.hasPerkInTree(perkRow, levelUpPerk)) {
					if(!PerkManager.MANAGER.isPerkAvailable(perkRow, levelUpPerk)) {
						tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Purchasing requires a connecting perk or trait.</div>");
					} else {
						tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_MINOR_GOOD.toWebHexString()+";'>Click to purchase perk.</div>");
					}
				} else {
					tooltipSB.append("<div class='subTitle' style='color:"+Colour.PERK.toWebHexString()+";'>You already own this perk!</div>");
				}
			}
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (desire != null) { // Desire:

			Main.mainController.setTooltipSize(360, 264);

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>Set Desire: <b style='color:" + desire.getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(desire.getName())+"</b></div>");
			
			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");
			int i=0;
			for (String s : desire.getModifiersAsStringList()) {
				tooltipSB.append((i!=0?"</br>":"") + s);
				i++;
			}
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + desire.getSVGImage() + "</div>");

			// Description:
			if(owner.hasFetish(fetish) && desire!=FetishDesire.FOUR_LOVE) {
				tooltipSB.append("<div class='description' style='height:53px'>Your desire is [style.boldBad(locked)] to <b style='color:"+FetishDesire.FOUR_LOVE.getColour().toWebHexString()+";'>"+FetishDesire.FOUR_LOVE.getName()+"</b>,"
						+ " due to owning the related fetish ("+fetish.getName(owner)+").</div>");
				tooltipSB.append("<div class='subTitle' style='text-align:center;'>Cost: [style.boldDisabled(N/A)]</div>");
			} else {
				tooltipSB.append("<div class='description' style='height:53px'>" + fetish.getFetishDesireDescription(owner, desire) + "</div>");
				if(owner.getBaseFetishDesire(fetish)==desire) {
					tooltipSB.append("<div class='subTitle' style='text-align:center;'>Cost: [style.boldDisabled(N/A)]</div>");
				} else {
					tooltipSB.append("<div class='subTitle' style='text-align:center;'>Cost: [style.boldArcane("
							+ (FetishDesire.getCostToChange()==0
								?"Free"
								:""+FetishDesire.getCostToChange()+" Arcane Essence"+(FetishDesire.getCostToChange()>1?"s":""))
							+ ")]</div>");
				}
			}

			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (fetish != null) { // Fetishes:
			
			if(fetishExperience) {
				
				Main.mainController.setTooltipSize(420, 156);
				
				tooltipSB.setLength(0);
				tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(fetish.getName(owner)) + " fetish</div>");
				FetishLevel level = FetishLevel.getFetishLevelFromValue(owner.getFetishExperience(fetish));
				tooltipSB.append("<div class='subTitle'>Level "+level.getNumeral()+": <span style='color:"+level.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(level.getName())+"</span>"
									+ " <span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>|</span> " + owner.getFetishExperience(fetish) +" / "+ level.getMaximumExperience() + " xp" + "</div>");
				tooltipSB.append("<div class='description' style='height:53px'>You earn fetish experience by performing related actions in sex. Each level increases the fetish's bonuses (max level is 5).</div>");

				Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
				
			} else {
				int yIncrease = (fetish.getModifiersAsStringList(owner).size() > 4 ? fetish.getModifiersAsStringList(owner).size() - 4 : 0) + fetish.getFetishesForAutomaticUnlock().size();
				
				Main.mainController.setTooltipSize(360, (fetish.getFetishesForAutomaticUnlock().isEmpty()?350:360) + (yIncrease * LINE_HEIGHT));
				
				// Title:
				tooltipSB.setLength(0);
				tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(fetish.getName(owner)) + " fetish</div>");
				FetishLevel level = FetishLevel.getFetishLevelFromValue(owner.getFetishExperience(fetish));
				tooltipSB.append("<div class='subTitle'>Level "+level.getNumeral()+": <span style='color:"+level.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(level.getName())+"</span>"
						+ " <span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>|</span> " + owner.getFetishExperience(fetish) +" / "+ level.getMaximumExperience() + " xp" + "</div>");
				
				// Requirements:
				if(!fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					tooltipSB.append("<div class='subTitle'>Requirements");
					for (Fetish f : fetish.getFetishesForAutomaticUnlock())
						tooltipSB.append("</br>[style.boldArcane(" + Util.capitaliseSentence(f.getName(Main.game.getPlayer()))+")]");
					tooltipSB.append("</div>");
				}
				
				// Attribute modifiers:
				tooltipSB.append("<div class='subTitle-picture'>");
				if (!fetish.getModifiersAsStringList(owner).isEmpty()) {
					int i=0;
					for (String s : fetish.getModifiersAsStringList(owner)) {
						tooltipSB.append((i!=0?"</br>":"") + s);
						i++;
					}
				} else {
					tooltipSB.append("<b style='color:" + Colour.FETISH.toWebHexString() + ";'>Fetish</b>" + "</br><span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>None</span>");
				}
				tooltipSB.append("</div>");
	
				// Picture:
				tooltipSB.append("<div class='picture'>" + fetish.getSVGString() + "</div>");
	
				// Description:
				tooltipSB.append("<div class='description'>" + fetish.getDescription(owner) + "</div>");
				
				if(fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					if(owner.hasBaseFetish(fetish)) {
						tooltipSB.append("<div class='subTitle' style='text-align:center;'>Cost: [style.boldDisabled(N/A)]</div>");
					} else {
						tooltipSB.append("<div class='subTitle' style='text-align:center;'>Cost: [style.boldArcane("+fetish.getCost()+" Arcane Essences)]</div>");
					}
				}
				
				Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
			}

		} else if (specialAttack != null) { // Special attacks:

			int yIncrease = (specialAttack.getStatusEffects().size() > 2 ? specialAttack.getStatusEffects().size() - 2 : 0);

			Main.mainController.setTooltipSize(360, 324 + (yIncrease * LINE_HEIGHT));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(specialAttack.getName()) + "</div>");

			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>"
					+ "<b style='color:" + Colour.SPECIAL_ATTACK.toWebHexString() + ";'>Special Attack</b></br>"
					+ "<b>"
						+ Attack.getMinimumSpecialAttackDamage(owner, null, specialAttack.getDamageType(), specialAttack.getDamage(), specialAttack.getDamageVariance())
						+ "-"
						+ Attack.getMaximumSpecialAttackDamage(owner, null, specialAttack.getDamageType(), specialAttack.getDamage(), specialAttack.getDamageVariance())
					+ "</b>"
					+ " <b style='color:" + specialAttack.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + specialAttack.getDamageType().getName()
					+ "</b> damage");

			tooltipSB.append("</br><b style='color:" + Colour.SPECIAL_ATTACK.toWebHexString() + ";'>Applies</b>");
			if (!specialAttack.getStatusEffects().isEmpty()) {
				for (Entry<StatusEffect, Integer> e : specialAttack.getStatusEffects().entrySet())
					tooltipSB.append("</br><b style='color:" + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getName(owner)) + "</b> for " + e.getValue() + " turn" + (e.getValue() > 1 ? "s" : ""));
			} else
				tooltipSB.append("</br><span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No effects</span>");
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + specialAttack.getSVGString() + "</div>");

			// Description & turns remaining:
			tooltipSB.append("<div class='description'>"
							+ specialAttack.getDescription(owner)
					+ "</div>"
					+ "<div class='subTitle-half'>"
						+ "<b>Cooldown: " + (specialAttack.getCooldown()) + "</b>"
					+ "</div>"
					+ "<div class='subTitle-half'>"
						+ (Main.game.isInCombat()
								?(Combat.getCooldown(owner, specialAttack)==0
										?"[style.boldGood(Off Cooldown)]"
										:"[style.boldCooldown(On Cooldown:)] "+Combat.getCooldown(owner, specialAttack))
								:"[style.boldGood(Off Cooldown)]")
					+ "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (spell != null) { // Spells:

			int yIncrease = (spell.getModifiersAsStringList().size() > 5 ? spell.getModifiersAsStringList().size() - 5 : 0);

			Main.mainController.setTooltipSize(360, 328 + (yIncrease * LINE_HEIGHT));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(spell.getName()) + "</div>");

			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");

			if(spell.getDamage(Main.game.getPlayer())>0) {
				tooltipSB.append(
						"<b>Base "+spell.getDamage(owner)+"</b> <b style='color:"+ spell.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(spell.getDamageType().getName()) + " Damage</b></br>"
						+"<b>"
							+ Attack.getMinimumSpellDamage(owner, null, spell.getDamageType(), spell.getDamage(owner), spell.getDamageVariance())
							+ "-"
							+ Attack.getMaximumSpellDamage(owner, null, spell.getDamageType(), spell.getDamage(owner), spell.getDamageVariance())
						+ "</b>"
						+ " <b style='color:"+ spell.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(spell.getDamageType().getName()) + " Damage</b></br>");
			}
			
			if(!spell.getModifiersAsStringList().isEmpty()) {
				for(int i=0; i<spell.getModifiersAsStringList().size(); i++) {
					tooltipSB.append(spell.getModifiersAsStringList().get(i)+(i<spell.getModifiersAsStringList().size()-1?"</br>":""));
				}
			} else {
				tooltipSB.append("<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No effects</span></br>");	
			}
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + spell.getSVGString() + "</div>");

			// Description & turns remaining:
			tooltipSB.append(
					"<div class='description'>"
							+ spell.getDescription()
					+ "</div>"
					+ "<div class='subTitle'>"
						+ "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Costs</b> <b>" + (spell.getModifiedCost(owner)) + "</b>"
						+ " <b style='color:" + Colour.ATTRIBUTE_MANA.toWebHexString() + ";'>aura</b>"
					+ "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (spellUpgrade != null) { // Spells:

			int yIncrease = (spellUpgrade.getModifiersAsStringList().size() > 5 ? spellUpgrade.getModifiersAsStringList().size() - 5 : 0);

			Main.mainController.setTooltipSize(360, 284 + (yIncrease * LINE_HEIGHT));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(spellUpgrade.getName()) + "</div>");

			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");

			if(!spellUpgrade.getModifiersAsStringList().isEmpty()) {
				for(int i=0; i<spellUpgrade.getModifiersAsStringList().size(); i++) {
					tooltipSB.append(spellUpgrade.getModifiersAsStringList().get(i)+(i<spellUpgrade.getModifiersAsStringList().size()-1?"</br>":""));
				}
			} else {
				tooltipSB.append("<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No effects</span></br>");
			}
			
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + spellUpgrade.getSVGString() + "</div>");

			// Description & turns remaining:
			tooltipSB.append(
					"<div class='description'>"
							+ spellUpgrade.getDescription()+" "+spellUpgrade.getUnavailableReason(owner)
					+ "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (attribute != null) {
			
			if (attribute == Attribute.MAJOR_PHYSIQUE
					|| attribute == Attribute.MAJOR_ARCANE
					|| attribute == Attribute.MAJOR_CORRUPTION
					|| attribute == Attribute.AROUSAL
					|| attribute == Attribute.LUST) {
				StatusEffect currentAttributeStatusEffect=null;
				int minimumLevelValue=0, maximumLevelValue=0;
				
				if(attribute == Attribute.MAJOR_PHYSIQUE) {
					currentAttributeStatusEffect = PhysiqueLevel.getPhysiqueLevelFromValue(owner.getAttributeValue(Attribute.MAJOR_PHYSIQUE)).getRelatedStatusEffect();
					minimumLevelValue = PhysiqueLevel.getPhysiqueLevelFromValue(owner.getAttributeValue(Attribute.MAJOR_PHYSIQUE)).getMinimumValue();
					maximumLevelValue = PhysiqueLevel.getPhysiqueLevelFromValue(owner.getAttributeValue(Attribute.MAJOR_PHYSIQUE)).getMaximumValue();
					
				} else if(attribute == Attribute.MAJOR_ARCANE) {
					currentAttributeStatusEffect = IntelligenceLevel.getIntelligenceLevelFromValue(owner.getAttributeValue(Attribute.MAJOR_ARCANE)).getRelatedStatusEffect();
					minimumLevelValue = IntelligenceLevel.getIntelligenceLevelFromValue(owner.getAttributeValue(Attribute.MAJOR_ARCANE)).getMinimumValue();
					maximumLevelValue = IntelligenceLevel.getIntelligenceLevelFromValue(owner.getAttributeValue(Attribute.MAJOR_ARCANE)).getMaximumValue();
					
				} else if(attribute == Attribute.MAJOR_CORRUPTION) {
					currentAttributeStatusEffect = CorruptionLevel.getCorruptionLevelFromValue(owner.getAttributeValue(Attribute.MAJOR_CORRUPTION)).getRelatedStatusEffect();
					minimumLevelValue = CorruptionLevel.getCorruptionLevelFromValue(owner.getAttributeValue(Attribute.MAJOR_CORRUPTION)).getMinimumValue();
					maximumLevelValue = CorruptionLevel.getCorruptionLevelFromValue(owner.getAttributeValue(Attribute.MAJOR_CORRUPTION)).getMaximumValue();
					
				} else if(attribute == Attribute.AROUSAL) {
					currentAttributeStatusEffect = ArousalLevel.getArousalLevelFromValue(owner.getAttributeValue(Attribute.AROUSAL)).getRelatedStatusEffect();
					minimumLevelValue = ArousalLevel.getArousalLevelFromValue(owner.getAttributeValue(Attribute.AROUSAL)).getMinimumValue();
					maximumLevelValue = ArousalLevel.getArousalLevelFromValue(owner.getAttributeValue(Attribute.AROUSAL)).getMaximumValue();
					
				} else if(attribute == Attribute.LUST) {
					currentAttributeStatusEffect = LustLevel.getLustLevelFromValue(owner.getAttributeValue(Attribute.LUST)).getRelatedStatusEffect();
					minimumLevelValue = LustLevel.getLustLevelFromValue(owner.getAttributeValue(Attribute.LUST)).getMinimumValue();
					maximumLevelValue = LustLevel.getLustLevelFromValue(owner.getAttributeValue(Attribute.LUST)).getMaximumValue();
				}
				
				
				int yIncrease = (currentAttributeStatusEffect.getModifiersAsStringList(owner).size() > 4 ? currentAttributeStatusEffect.getModifiersAsStringList(owner).size() - 4 : 0)
						+ (owner.hasStatusEffect(currentAttributeStatusEffect)?(owner.getStatusEffectDuration(currentAttributeStatusEffect) == -1 ? 0 : 2):0);

				Main.mainController.setTooltipSize(360, 460 + (yIncrease * LINE_HEIGHT));
				
				tooltipSB.setLength(0);
				tooltipSB.append("<div class='title' style='color:" + attribute.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(attribute.getName()) + "</div>"

						+ "<div class='subTitle-third'>" + "<b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Core</b></br>"
						+ (owner.getBaseAttributeValue(attribute) > 0 ? "<span style='color: " + Colour.GENERIC_EXCELLENT.getShades()[1] + ";'>" : "<span>") + String.format("%.2f", owner.getBaseAttributeValue(attribute)) + "</span>" + "</div>"
						
						+ "<div class='subTitle-third'>" + "<b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Bonus</b></br>"
						+ ((owner.getBonusAttributeValue(attribute)) > 0 ? "<span style='color: " + Colour.GENERIC_GOOD.getShades()[1] + ";'>"
								: ((owner.getBonusAttributeValue(attribute)) == 0 ? "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>" : "<span style='color: " + Colour.GENERIC_BAD.getShades()[1] + ";'>"))
						+ String.format("%.2f", owner.getBonusAttributeValue(attribute))+ "</span>" + "</div>"
						
						+ "<div class='subTitle-third'>" + "<b style='color:" + attribute.getColour().toWebHexString() + ";'>Total</b></br>" + String.format("%.2f", owner.getAttributeValue(attribute))
						+ "</span>" + "</div>");
				
				tooltipSB.append("<div class='description-half'>" + attribute.getDescription(owner) + "</div>");
				
				// Related status effect:
				tooltipSB.append("<div class='title'>"
												+ "<span style='color:"+currentAttributeStatusEffect.getColour().toWebHexString()+";'>"
												+ currentAttributeStatusEffect.getName(owner)
												+"</span> ("+minimumLevelValue
												+"-"
												+ maximumLevelValue
												+")"
												+ "</div>");
			
				// Attribute modifiers:
				tooltipSB.append("<div class='subTitle-picture'>");
				if (!currentAttributeStatusEffect.getModifiersAsStringList(owner).isEmpty()) {
					int i=0;
					for (String s : currentAttributeStatusEffect.getModifiersAsStringList(owner)) {
						if(i!=0) {
							tooltipSB.append("</br>");
						}
						tooltipSB.append(s);
						i++;
					}
				} else {
					tooltipSB.append("<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No bonuses</span>");
				}
				tooltipSB.append("</div>");
			
				// Picture:
				tooltipSB.append("<div class='picture'>" + currentAttributeStatusEffect.getSVGString(owner) + "</div>");
			
				// Description & turns remaining:
				tooltipSB.append("<div class='description'>" + currentAttributeStatusEffect.getDescription(owner) + "</div>");

				Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

			} else if (attribute == Attribute.EXPERIENCE) {
				// Special tooltip for experience/transformation combo:

				if(owner.isRaceConcealed()) {
					Main.mainController.setTooltipSize(420, 64);
					
					tooltipSB.setLength(0);
					tooltipSB.append("<div class='title' style='color:" + Colour.RACE_UNKNOWN.toWebHexString() + ";'>"
							+ "Unknown Race!"
							+ "</div>");
					
				} else {
					Main.mainController.setTooltipSize(420, 508);
					
					tooltipSB.setLength(0);
					tooltipSB.append("<div class='title' style='color:" + owner.getRace().getColour().toWebHexString() + ";'>"
							+(owner.getRaceStage().getName()!=""?"<b style='color:"+owner.getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(owner.getRaceStage().getName())+"</b> ":"")
							+ "<b style='color:"+owner.getRace().getColour().toWebHexString()+";'>"
							+ (owner.isFeminine()?Util.capitaliseSentence(owner.getSubspecies().getSingularFemaleName()):Util.capitaliseSentence(owner.getSubspecies().getSingularMaleName()))
							+ "</b>"
							+ "</div>");
	
					// GREATER:
					tooltipSB.append(getBodyPartDiv("Face", owner.getFaceRace(), owner.getFaceType().getBodyCoveringType(owner)));
					tooltipSB.append(getBodyPartDiv("Body", owner.getSkinRace(), owner.getSkinType().getBodyCoveringType(owner)));
					
	
					// LESSER:
					tooltipSB.append(getBodyPartDiv("Arms", owner.getArmRace(), owner.getArmType().getBodyCoveringType(owner)));
					tooltipSB.append(getBodyPartDiv("Legs", owner.getLegRace(), owner.getLegType().getBodyCoveringType(owner)));
					
					// PARTIAL:
					tooltipSB.append(getBodyPartDiv("Hair", owner.getHairRace(), owner.getHairType().getBodyCoveringType(owner)));
					tooltipSB.append(getBodyPartDiv("Eyes", owner.getEyeRace(), owner.getEyeType().getBodyCoveringType(owner)));
					tooltipSB.append(getBodyPartDiv("Ears", owner.getEarRace(), owner.getEarType().getBodyCoveringType(owner)));
					tooltipSB.append(getBodyPartDiv("Tongue", owner.getTongueRace(), owner.getTongueType().getBodyCoveringType(owner)));
					if (owner.getHornType() != HornType.NONE) {
						tooltipSB.append(getBodyPartDiv((owner.hasHorns()?Util.capitaliseSentence(owner.getHornName()):"Horns"), owner.getHornRace(), owner.getHornType().getBodyCoveringType(owner)));
					} else {
						tooltipSB.append(getEmptyBodyPartDiv("Horns", "None"));
					}
					if (owner.getAntennaType() != AntennaType.NONE) {
						tooltipSB.append(getBodyPartDiv("Antennae", owner.getAntennaRace(), owner.getAntennaType().getBodyCoveringType(owner)));
					} else {
						tooltipSB.append(getEmptyBodyPartDiv("Antennae", "None"));
					}
					if (owner.getWingType() != WingType.NONE) {
						tooltipSB.append(getBodyPartDiv("Wings", owner.getWingRace(), owner.getWingType().getBodyCoveringType(owner)));
					} else {
						tooltipSB.append(getEmptyBodyPartDiv("Wings", "None"));
					}
					if (owner.getTailType() != TailType.NONE) {
						tooltipSB.append(getBodyPartDiv("Tail", owner.getTailRace(), owner.getTailType().getBodyCoveringType(owner)));
					} else {
						tooltipSB.append(getEmptyBodyPartDiv("Tail", "None"));
					}
					
					// SEXUAL:
					if(!owner.isPlayer() && !owner.getPlayerKnowsAreas().contains(CoverableArea.VAGINA)) {
						tooltipSB.append(getEmptyBodyPartDiv("Vagina", "Unknown!"));
					} else {
						if (owner.getVaginaType() != VaginaType.NONE) {
							tooltipSB.append(getBodyPartDiv("Vagina", owner.getVaginaRace(), owner.getVaginaType().getBodyCoveringType(owner)));
						} else {
							tooltipSB.append(getEmptyBodyPartDiv("Vagina", "None"));
						}
					}
					
					if(!owner.isPlayer() && !owner.getPlayerKnowsAreas().contains(CoverableArea.PENIS)) {
						tooltipSB.append(getEmptyBodyPartDiv("Penis", "Unknown!"));
					} else {
						if (owner.getPenisType() != PenisType.NONE) {
							tooltipSB.append(getBodyPartDiv("Penis", owner.getPenisRace(), owner.getPenisType().getBodyCoveringType(owner)));
						} else {
							tooltipSB.append(getEmptyBodyPartDiv("Penis", "None"));
						}
					}
					tooltipSB.append(getBodyPartDiv("Ass", owner.getAssRace(), owner.getAssType().getBodyCoveringType(owner)));
					tooltipSB.append(getBodyPartDiv(owner.hasBreasts()?"Breasts":"Chest", owner.getBreastRace(), owner.getBreastType().getBodyCoveringType(owner)));
				}
				
				Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

			} else {
				Main.mainController.setTooltipSize(360, 234);

				Main.mainController.setTooltipContent(UtilText.parse(
						"<div class='title' style='color:" + attribute.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(attribute.getName()) + "</div>"

						+ "<div class='subTitle-third'>"
						+ "<b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Core</b></br>"
						+ (owner.getBaseAttributeValue(attribute) > 0 ? "<span style='color: " + Colour.GENERIC_EXCELLENT.getShades()[1] + ";'>" : "<span>") + String.format("%.2f", owner.getBaseAttributeValue(attribute))
						+ "</span>"
						+ "</div>"
						+ "<div class='subTitle-third'>"
						+ "<b style='color:"
						+ Colour.TEXT_GREY.toWebHexString()
						+ ";'>Bonus</b></br>"
						+ ((owner.getBonusAttributeValue(attribute)) > 0 ? "<span style='color: "
								+ Colour.GENERIC_GOOD.getShades()[1]
								+ ";'>"
								: ((owner.getBonusAttributeValue(attribute)) == 0 ? "<span style='color:"
										+ Colour.TEXT_GREY.toWebHexString()
										+ ";'>"
										: "<span style='color: "
												+ Colour.GENERIC_BAD.getShades()[1]
												+ ";'>"))
						+ String.format("%.2f", owner.getBonusAttributeValue(attribute))
						+ "</span>"
						+ "</div>"
						+ "<div class='subTitle-third'>"
						+ "<b style='color:"
						+ attribute.getColour().toWebHexString() + ";'>Total</b></br>" + String.format("%.2f", owner.getAttributeValue(attribute)) + "</span>"
						+ "</div>"

						+ "<div class='description'>" + attribute.getDescription(owner) + "</div>"));
				
			}

		} else if (extraAttributes) {

			Main.mainController.setTooltipSize(400, 600);

			tooltipSB.setLength(0);
			tooltipSB.append(UtilText.parse(owner,
					"<div class='title' style='color:" + Femininity.valueOf(owner.getFemininityValue()).getColour().toWebHexString() + ";'>"+ (owner.getName().length() == 0 ? "[npc.Race]" : "[npc.Name]") + "</div>"
					
					+"<div class='subTitle' style='margin-bottom:4px;'>Level " + owner.getLevel() + " <span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>|</span> " + owner.getExperience() + " / "
							+ (10 * owner.getLevel()) + " xp</div>"
			
					+ extraAttributeBonus(owner, Attribute.CRITICAL_CHANCE)
					+ extraAttributeBonus(owner, Attribute.CRITICAL_DAMAGE)

					+ extraAttributeBonus(owner, Attribute.DAMAGE_UNARMED)
					+ extraAttributeBonus(owner, Attribute.DAMAGE_SPELLS)
					+ extraAttributeBonus(owner, Attribute.DAMAGE_MELEE_WEAPON)
					+ extraAttributeBonus(owner, Attribute.DAMAGE_RANGED_WEAPON)

					// Header:
					+ "<div class='subTitle-third combatValue'>" + "Type" + "</div>" + "<div class='subTitle-third combatValue'>" + "Damage" + "</div>" + "<div class='subTitle-third combatValue'>" + "Resist" + "</div>"

					// Values:
					+ extraAttributeTableRow(owner, "Physical", Attribute.DAMAGE_PHYSICAL, Attribute.RESISTANCE_PHYSICAL)
					+ extraAttributeTableRow(owner, "Fire", Attribute.DAMAGE_FIRE, Attribute.RESISTANCE_FIRE)
					+ extraAttributeTableRow(owner, "Cold", Attribute.DAMAGE_ICE, Attribute.RESISTANCE_ICE)
					+ extraAttributeTableRow(owner, "Poison", Attribute.DAMAGE_POISON, Attribute.RESISTANCE_POISON)
					+ extraAttributeTableRow(owner, "Seduction", Attribute.DAMAGE_LUST, Attribute.RESISTANCE_LUST)
					
					+ extraAttributeBonus(owner, Attribute.FERTILITY)
					+ extraAttributeBonus(owner, Attribute.VIRILITY)
					
					+ extraAttributeBonus(owner, Attribute.SPELL_COST_MODIFIER)));

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (weather) {

			Main.mainController.setTooltipSize(360, 100);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + "<b style='color:" + Main.game.getCurrentWeather().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getCurrentWeather().getName()) + "</b>" + "</div>"
					+ "<div class='title'>" + "<b>" + ((Main.game.getWeatherTimeRemaining() / 60) + 1) + " hour" + (((Main.game.getWeatherTimeRemaining() / 60) + 1) > 1 ? "s" : "") + " remaining" + "</b>" + "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (protection) {

			Main.mainController.setTooltipSize(360, 100);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>Protection</div>"
					+ "<div class='subTitle'>"
					+ (owner.isWearingCondom()?"<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Wearing Condom</span>":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>No Condom</span>")
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
					+ "This scene was written by <b style='color:"+Colour.ANDROGYNOUS.toWebHexString()+";'>"
					+ Main.game.getCurrentDialogueNode().getAuthor()
					+ "</b></div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if(concealedSlot!=null) {

			Map<InventorySlot, List<AbstractClothing>> concealedSlots = RenderingEngine.getCharacterToRender().getInventorySlotsConcealed();
			
			Main.mainController.setTooltipSize(360, 175);

			Main.mainController.setTooltipContent(UtilText.parse(
					"<div class='title'>"+Util.capitaliseSentence(concealedSlot.getName())+" - [style.boldBad(Concealed!)]</div>"
					+ "<div class='description'>"
						+ UtilText.parse(RenderingEngine.getCharacterToRender(), "This slot is currently hidden from view by [npc.name]'s <b>")
							+Util.clothesToStringList(concealedSlots.get(concealedSlot).stream().filter(clothing -> !concealedSlots.containsKey(clothing.getClothingType().getSlot())).collect(Collectors.toList()), false)
						+"</b>."
					+ "</div>"));
			
		} else { // Standard information:
			if(description==null || description.isEmpty()) {
				Main.mainController.setTooltipSize(360, 64);

				Main.mainController.setTooltipContent(UtilText.parse(
						"<div class='title'>"+title+"</div>"));
				
			} else if(title==null || title.isEmpty()) {
				Main.mainController.setTooltipSize(360, 200);

				Main.mainController.setTooltipContent(UtilText.parse(
						"<div class='description' style='height:176px;'>"+description+"</div>"));
				
			} else {
				Main.mainController.setTooltipSize(360, 175);

				Main.mainController.setTooltipContent(UtilText.parse(
						"<div class='title'>"+title+"</div>"
						+ "<div class='description'>" + description + "</div>"));
			}
		}

		(new Thread(new TooltipUpdateThread())).start();
	}
	
	private String getBodyPartDiv(String name, Race race, BodyCoveringType covering) {
		return "<div class='subTitle' style='font-weight:normal; text-align:left; margin-top:2px; white-space: nowrap;'>"+ name + ": <span style='color:" + race.getColour().toWebHexString() + ";'>"+ Util.capitaliseSentence(race.getName()) + "</span> - "
					+ owner.getCovering(covering).getColourDescriptor(owner, true, true) + " " + owner.getCovering(covering).getName(owner)+"</div>";
	}
	
	private String getEmptyBodyPartDiv(String name, String description) {
		return "<div class='subTitle' style='font-weight:normal; text-align:left; margin-top:2px; white-space: nowrap;'>"+ name + ": <span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>"+description+"</span></div>";
	}
	

	private String extraAttributeTableRow(GameCharacter owner, String type, Attribute damage, Attribute resist) {
		return "<div class='subTitle-third combatValue'>" + "<span style='color:" + damage.getColour().toWebHexString() + ";'>" + type + "</span>" + "</div>" + "<div class='subTitle-third combatValue'>"
				+ (owner.getAttributeValue(damage) > damage.getBaseValue()
										? "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>"
										: (owner.getAttributeValue(damage) < damage.getBaseValue()
												? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>"
												: ""))
				+ owner.getAttributeValue(damage)
				+ "</span>" + "</div>" + "<div class='subTitle-third combatValue'>"
				+ (resist == null ? "0.0"
						: (owner.getAttributeValue(resist) > 0 ? "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>"
								: (owner.getAttributeValue(resist) < 0 ? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>" : "")) + owner.getAttributeValue(resist) + "</span>")
				+ "</div>";
	}

	private String extraAttributeBonus(GameCharacter owner, Attribute bonus) {
		return "<div class='subTitle-half'>" + "<span style='color:"
				+ bonus.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(bonus.getName()) + "</span></br>" + (owner.getAttributeValue(bonus) > bonus.getBaseValue()
						? "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>" : (owner.getAttributeValue(bonus) < bonus.getBaseValue() ? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>" : ""))
				+ owner.getAttributeValue(bonus) + "</span>" + "</div>";
	}

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

	public TooltipInformationEventListener setExtraAttributes(GameCharacter owner) {
		resetFields();
		extraAttributes = true;
		this.owner = owner;

		return this;
	}
	public TooltipInformationEventListener setStatusEffect(StatusEffect statusEffect, GameCharacter owner) {
		resetFields();
		this.statusEffect = statusEffect;
		this.owner = owner;

		return this;
	}

	public TooltipInformationEventListener setPerk(Perk perk, GameCharacter owner) {
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

	public TooltipInformationEventListener setFetishExperience(Fetish fetish, GameCharacter owner) {
		resetFields();
		fetishExperience = true;
		this.fetish = fetish;
		this.owner = owner;

		return this;
	}
	
	public TooltipInformationEventListener setFetishDesire(Fetish fetish, FetishDesire desire, GameCharacter owner) {
		resetFields();
		this.desire = desire;
		this.fetish = fetish;
		this.owner = owner;

		return this;
	}

	public TooltipInformationEventListener setLevelUpPerk(int perkRow, Perk levelUpPerk, GameCharacter owner) {
		resetFields();
		this.levelUpPerk = levelUpPerk;
		this.perkRow = perkRow;
		this.owner = owner;

		return this;
	}

	public TooltipInformationEventListener setSpecialAttack(SpecialAttack specialAttack, GameCharacter owner) {
		resetFields();
		this.specialAttack = specialAttack;
		this.owner = owner;

		return this;
	}

	public TooltipInformationEventListener setSpell(Spell spell, GameCharacter owner) {
		resetFields();
		this.spell = spell;
		this.owner = owner;

		return this;
	}

	public TooltipInformationEventListener setSpellUpgrade(SpellUpgrade spellUpgrade, GameCharacter owner) {
		resetFields();
		this.spellUpgrade = spellUpgrade;
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

	public TooltipInformationEventListener setConcealedSlot(InventorySlot concealedSlot) {
		resetFields();
		this.concealedSlot = concealedSlot;

		return this;
	}

	
	private void resetFields() {
		extraAttributes = false;
		weather = false;
		owner = null;
		statusEffect = null;
		perk = null;
		fetish = null;
		fetishExperience = false;
		desire = null;
		levelUpPerk = null;
		perkRow = 0;
		specialAttack = null;
		spell = null;
		spellUpgrade = null;
		attribute = null;
		protection=false;
		tattoo=false;
		copyInformation=false;
		concealedSlot=null;
	}
}
