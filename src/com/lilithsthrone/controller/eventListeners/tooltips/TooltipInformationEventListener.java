package com.lilithsthrone.controller.eventListeners.tooltips;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.fetishes.FetishLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.CombatMove;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellUpgrade;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Library;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.LoadedEnchantment;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.CachedImage;
import com.lilithsthrone.rendering.ImageCache;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public class TooltipInformationEventListener implements EventListener {
	private String title;
	private String description;
	
	private boolean extraAttributes = false;
	private boolean weather = false;
	private boolean protection = false;
	private boolean copyInformation = false;
	private boolean availableForSelection = false;
	
	private GameCharacter owner;
	private StatusEffect statusEffect;
	private AbstractPerk perk;
	private AbstractPerk levelUpPerk;
	private int perkRow;
	private Fetish fetish;
	private boolean fetishExperience = false;
	private FetishDesire desire;
	private Spell spell;
	private SpellUpgrade spellUpgrade;
	private Attribute attribute;
	private InventorySlot concealedSlot;
	private LoadedEnchantment loadedEnchantment;
	private CombatMove move;
	private Cell cell;
	
	private static StringBuilder tooltipSB  = new StringBuilder();
	
	private int descriptionHeightOverride;
	
	private static final int LINE_HEIGHT= 16;

	
	@Override
	public void handleEvent(Event event) {
		Main.mainController.setTooltipSize(360, 180);
		Main.mainController.setTooltipContent("");

		if (statusEffect != null) {

			// I hate this. If only JavaFX's height detection and resizing methods actually worked...
			int size = statusEffect.getModifiersAsStringList(owner).size();
			int yIncrease = (size > 4 ? size - 4 : 0)
								+ (owner.hasStatusEffect(statusEffect)?(owner.getStatusEffectDuration(statusEffect) == -1 ? 0 : 2):0);

			if(statusEffect.getAdditionalDescription(owner)!=null && !statusEffect.getAdditionalDescription(owner).isEmpty()) {
				yIncrease += 7;
			}
				
			Main.mainController.setTooltipSize(360, 284 + (yIncrease * LINE_HEIGHT));
			
			
			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<body>"
					+ "<div class='title'>" + Util.capitaliseSentence(statusEffect.getName(owner)) + "</div>");

			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");// style='white-space: nowrap'>");
				if (!statusEffect.getModifiersAsStringList(owner).isEmpty()) {
					int i=0;
					for (String s : statusEffect.getModifiersAsStringList(owner)) {
						tooltipSB.append((i!=0?"<br/>":"") + s);
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
			
			if(statusEffect.getAdditionalDescription(owner)!=null && !statusEffect.getAdditionalDescription(owner).isEmpty()) {
				tooltipSB.append("<div class='description'>"
						+ statusEffect.getAdditionalDescription(owner)
					+ "</div>");
			}
			
			if(owner.hasStatusEffect(statusEffect)) {
				if (owner.getStatusEffectDuration(statusEffect) != -1) {
					if (statusEffect.isCombatEffect()) {
						tooltipSB.append("<div class='subTitle'><b>Turns remaining: " + owner.getStatusEffectDuration(statusEffect) + "</b></div>");
					} else {
						int timerHeight = (int) ((owner.getStatusEffectDuration(statusEffect)/(60*60*6f))*100);

						Colour timerColour = Colour.STATUS_EFFECT_TIME_HIGH;
						
						if(timerHeight>100) {
							timerHeight=100;
							timerColour = Colour.STATUS_EFFECT_TIME_OVERFLOW;
						} else if(timerHeight<15) {
							timerColour = Colour.STATUS_EFFECT_TIME_LOW;
						} else if (timerHeight<50) {
							timerColour = Colour.STATUS_EFFECT_TIME_MEDIUM;
						}
						int minutes = Math.max(1, owner.getStatusEffectDuration(statusEffect)/60);
						int hours = minutes/60;
						int days = hours/24;
						
						tooltipSB.append("<div class='subTitle'><b>Time remaining: "
								+ "<b style='color:"+timerColour.toWebHexString()+";'>"
								+(days>0
									?days+" day"+(days>1?"s":"")
										+(hours%24>0
												?" "+(hours%24)+" hour"+((hours%24)>1?"s":"")
														+ (minutes%60>0
																?" "+(minutes%60)+" minute"+((minutes%60)>1?"s":"")
																		:"")
												:(minutes%60>0
														?" "+(minutes%60)+" minute"+((minutes%60)>1?"s":"")
																:""))
									:(hours>0
											?" "+(hours)+" hour"+((hours)>1?"s":"")
													+ (minutes%60>0
															?" "+(minutes%60)+" minute"+((minutes%60)>1?"s":"")
																	:"")
											:(minutes)+" minute"+((minutes)>1?"s":"")))
								+ "</b>"
								+ "</div>");
						//STATUS_EFFECT_TIME_OVERFLOW
					}
				}
			}
			
			tooltipSB.append("</body>");
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
			
			// Wasted more time trying to get JavaFX to return sensible height values.
//			int height = Integer.valueOf(((String) Main.mainController.getWebEngineTooltip().executeScript("window.getComputedStyle(document.body, null).getPropertyValue('height')")).replace("px", ""));
////					"Math.max( document.body.scrollHeight, document.body.offsetHeight );");
//			
//			System.out.println(height);
//
//			Main.mainController.setTooltipSize(360, height+8);

		} else if (perk != null) { // Perks:
			
			int yIncrease = (perk.getModifiersAsStringList(owner).size() > 4 ? perk.getModifiersAsStringList(owner).size() - 4 : 0);

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
			if (!perk.getModifiersAsStringList(owner).isEmpty()) {
				int i=0;
				for (String s : perk.getModifiersAsStringList(owner)) {
					tooltipSB.append((i!=0?"<br/>":"") + s);
					i++;
				}
			} else
				tooltipSB.append("<b style='color:" + Colour.PERK.toWebHexString() + ";'>Perk</b>" + "<br/><span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>None</span>");
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + perk.getSVGString() + "</div>");

			// Description:
			tooltipSB.append("<div class='description'>" + UtilText.parse(owner, perk.getDescription(owner)) + "</div>");
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
				
			
		} else if (levelUpPerk != null) { // Level Up Perk (same as Perk, but with requirements at top):

			int yIncrease = (levelUpPerk.getModifiersAsStringList(owner).size() > 4 ? levelUpPerk.getModifiersAsStringList(owner).size() - 4 : 0);

			Main.mainController.setTooltipSize(360, 320 + (availableForSelection?32:0) + (yIncrease * LINE_HEIGHT));
			
			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(levelUpPerk.getName(owner)) + "</div>");
			
			if(levelUpPerk.isEquippableTrait()) {
				if(levelUpPerk.getPerkCategory()==PerkCategory.JOB) {
					tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>'"+Util.capitaliseSentence(owner.getHistory().getName())+"' Occupation Trait</div>");
				} else if(levelUpPerk.isHiddenPerk()) {
					tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Unique Trait</div>");
				} else {
					tooltipSB.append("<div class='subTitle' style='color:"+Colour.TRAIT.toWebHexString()+";'>Trait</div>");
				}
			} else {
				 if(levelUpPerk.isHiddenPerk()) {
					tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Unique Perk</div>");
				} else {
					tooltipSB.append("<div class='subTitle' style='color:"+Colour.PERK.toWebHexString()+";'>Perk</div>");
				}
			}
			
			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");
			if (!levelUpPerk.getModifiersAsStringList(owner).isEmpty()) {
				int i=0;
				for (String s : levelUpPerk.getModifiersAsStringList(owner)) {
					tooltipSB.append((i!=0?"<br/>":"") + s);
					i++;
				}
			} else {
				tooltipSB.append("<b style='color:" + Colour.PERK.toWebHexString() + ";'>Perk</b>" + "<br/><span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>None</span>");
			}
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + levelUpPerk.getSVGString() + "</div>");

			// Description:
			tooltipSB.append("<div class='description'>" + UtilText.parse(owner, levelUpPerk.getDescription(owner)) + "</div>");
			
			if(availableForSelection) {
				if(levelUpPerk.isEquippableTrait()) {
					if(levelUpPerk.getPerkCategory()==PerkCategory.JOB) {
						tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>Occupation traits cannot be removed.</div>");
						
					} else {
						if(!owner.hasPerkInTree(perkRow, levelUpPerk)) {
							if(!PerkManager.MANAGER.isPerkAvailable(owner, perkRow, levelUpPerk)) {
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
					if(!owner.hasPerkInTree(perkRow, levelUpPerk) && !levelUpPerk.isHiddenPerk()) {
						if(!PerkManager.MANAGER.isPerkAvailable(owner, perkRow, levelUpPerk)) {
							tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Purchasing requires a connecting perk or trait.</div>");
						} else {
							tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_MINOR_GOOD.toWebHexString()+";'>Click to purchase perk.</div>");
						}
						
					} else {
						tooltipSB.append("<div class='subTitle' style='color:"+Colour.PERK.toWebHexString()+";'>"
											+ UtilText.parse(owner, "[npc.Name] already [npc.verb(own)] this perk!")
										+ "</div>");
					}
				}
			}
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (move != null) {
			List<String> critReqs = move.getCritRequirements(owner, null, null, null);
			
			int currentCooldown = owner.getMoveCooldown(move.getIdentifier());
			
			Main.mainController.setTooltipSize(360,
					352
					+ (critReqs.size()>0?(32+critReqs.size()*16):0)
					+ (currentCooldown>0?32:0));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(move.getName(0, owner)) + "</div>");

			boolean coreMove = owner.getEquippedMoves().contains(move);
			
			tooltipSB.append("<div class='subTitle' style='width:46%; margin:2% 2% 0% 2%;'>"+(coreMove?"[style.colourMinorGood(Core)]":"[style.colourMinorBad(Non-core)]")+"</div>");
			tooltipSB.append("<div class='subTitle' style='color:"+move.getType().getColour().toWebHexString()+"; width:46%; margin:2% 2% 0% 2%;'>"+move.getType().getName()+"</div>");
			
			if(currentCooldown>0) {
				tooltipSB.append("<div class='subTitle'><span style='color:"+Colour.GENERIC_MINOR_BAD.toWebHexString()+";'>On cooldown</span>: "+currentCooldown+(currentCooldown==1?" turn":" turns")+"</div>");
			}
			
			// Picture:
			tooltipSB.append("<div class='picture'>" + move.getSVGString() + "</div>");

			// Description:
			tooltipSB.append("<div class='subTitle-picture'>");

			int apCost = move.getAPcost(owner);
			int cooldown = move.getCooldown(owner);

			tooltipSB.append(
					"AP cost: "
						+"<span style='color:"+(Colour.ACTION_POINT_COLOURS[apCost]).toWebHexString()+";'>"
						+(coreMove?apCost:(apCost-1)+"[style.colourBad(+1)]")
						+"</span>"
					+ "<br/>Cooldown: "
						+ "<span style='color:"+(cooldown-(coreMove?0:1)<=0?Colour.GENERIC_MINOR_GOOD:Colour.GENERIC_MINOR_BAD).toWebHexString()+";'>"
						+(coreMove?cooldown:(cooldown-1)+"[style.colourBad(+1)]")
						+"</span> turn"+(cooldown==1?"":"s"));
			
//			tooltipSB.append("AP cost: "+"<span style='color:"+(apColours[apCost]).toWebHexString()+";'>"+apCost+"</span>");
//			tooltipSB.append("<br/>Cooldown: "+"<span style='color:"+(cooldown==0?Colour.GENERIC_MINOR_GOOD:Colour.GENERIC_MINOR_BAD).toWebHexString()+";'>"+cooldown+(cooldown==1?" turn":" turns")+"</span>");
			
			if(move.getStatusEffects(owner, owner, false)!=null) {
				for(Entry<StatusEffect, Integer> entry : move.getStatusEffects(owner, owner, false).entrySet()) {
					tooltipSB.append("<br/>Applies: <span style='color:"+entry.getKey().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getName(null))+"</span> for "+entry.getValue()+(entry.getValue()==1?" turn":" turns"));
				}
			}
			tooltipSB.append("</div>");

			// Description:
			Value<Boolean, String> availableValue = owner.isMoveAvailable(move.getIdentifier());
			
			tooltipSB.append(
					"<div class='description'>"
						+"<span style='color:"+(availableValue.getKey()?Colour.GENERIC_MINOR_GOOD:Colour.GENERIC_MINOR_BAD).toWebHexString()+";'>"+availableValue.getValue()+"</span> "
						+ move.getDescription()
					+ "</div>");
			

			tooltipSB.append("<div class='subTitle'><span style='color:"+Colour.CRIT.toWebHexString()+";'>Critically hits when:</span>");
			for(String s : critReqs) {
				tooltipSB.append("<br/>"+s);
			}
			tooltipSB.append("</div>");

			if(owner.getEquippedMoves().contains(move)) {
				tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_MINOR_BAD.toWebHexString()+";'>Click to unequip move.</div>");
			} else {
				if(owner.getEquippedMoves().size()>=GameCharacter.MAX_COMBAT_MOVES) {
					tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Maximum core moves selected.</div>");
				} else {
					tooltipSB.append("<div class='subTitle' style='color:"+Colour.TRAIT.toWebHexString()+";'>Click to equip move.</div>");
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
				tooltipSB.append((i!=0?"<br/>":"") + s);
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
								:Integer.toString(FetishDesire.getCostToChange())+" Arcane Essence"+(FetishDesire.getCostToChange()>1?"s":""))
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
						tooltipSB.append("<br/>[style.boldArcane(" + Util.capitaliseSentence(f.getName(Main.game.getPlayer()))+")]");
					tooltipSB.append("</div>");
				}
				
				// Attribute modifiers:
				tooltipSB.append("<div class='subTitle-picture'>");
				if (!fetish.getModifiersAsStringList(owner).isEmpty()) {
					int i=0;
					for (String s : fetish.getModifiersAsStringList(owner)) {
						tooltipSB.append((i!=0?"<br/>":"") + s);
						i++;
					}
				} else {
					tooltipSB.append("<b style='color:" + Colour.FETISH.toWebHexString() + ";'>Fetish</b>" + "<br/><span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>None</span>");
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

		} else if (spell != null) { // Spells:

			int yIncrease = (spell.getModifiersAsStringList().size() > 5 ? spell.getModifiersAsStringList().size() - 5 : 0);

			Main.mainController.setTooltipSize(360, 332 + (yIncrease * LINE_HEIGHT));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(spell.getName()) + "</div>");

			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");

			if(spell.getDamage(Main.game.getPlayer())>0) {
				tooltipSB.append(
						"<b>Base "+spell.getDamage(owner)+"</b> <b style='color:"+ spell.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(spell.getDamageType().getName()) + " Damage</b><br/>"
						+"<b>"
							+ Attack.getMinimumSpellDamage(owner, null, spell.getDamageType(), spell.getDamage(owner), spell.getDamageVariance())
							+ "-"
							+ Attack.getMaximumSpellDamage(owner, null, spell.getDamageType(), spell.getDamage(owner), spell.getDamageVariance())
						+ "</b>"
						+ " <b style='color:"+ spell.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(spell.getDamageType().getName()) + " Damage</b><br/>");
			}
			
			if(!spell.getModifiersAsStringList().isEmpty()) {
				for(int i=0; i<spell.getModifiersAsStringList().size(); i++) {
					tooltipSB.append(spell.getModifiersAsStringList().get(i)+(i<spell.getModifiersAsStringList().size()-1?"<br/>":""));
				}
			} else {
				tooltipSB.append("<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No effects</span><br/>");	
			}
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + spell.getSVGString() + "</div>");

			// Description & turns remaining:
			tooltipSB.append(
					"<div class='description'>"
							+ (spell.isForbiddenSpell() && !owner.hasSpell(spell)?"[style.italicsArcane(This is a forbidden spell, and can only be discovered through a special quest!)]<br/>":"")
							+ spell.getDescription()
					+ "</div>"
					+ "<div class='subTitle'>"
						+ "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Costs</b> <b>" + (spell.getModifiedCost(owner)) + "</b> <b style='color:" + Colour.ATTRIBUTE_MANA.toWebHexString() + ";'>aura</b>"
					+ "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (spellUpgrade != null) { // Spell upgrades:

			int yIncrease = (spellUpgrade.getModifiersAsStringList().size() > 5 ? spellUpgrade.getModifiersAsStringList().size() - 5 : 0);

			Main.mainController.setTooltipSize(360, 316 + (yIncrease * LINE_HEIGHT));

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(spellUpgrade.getName()) + "</div>");

			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");

			if(!spellUpgrade.getModifiersAsStringList().isEmpty()) {
				for(int i=0; i<spellUpgrade.getModifiersAsStringList().size(); i++) {
					tooltipSB.append(spellUpgrade.getModifiersAsStringList().get(i)+(i<spellUpgrade.getModifiersAsStringList().size()-1?"<br/>":""));
				}
			} else {
				tooltipSB.append("<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No effects</span><br/>");
			}
			
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + spellUpgrade.getSVGString() + "</div>");

			// Description:
			tooltipSB.append(
					"<div class='description'>"
							+ spellUpgrade.getDescription()+" "+spellUpgrade.getUnavailableReason(owner)
					+ "</div>"
					+ "<div class='subTitle'>"
						+ (owner.hasSpellUpgrade(spellUpgrade)
								?"[style.boldExcellent(Owned)] (Cost <b style='color:"+spellUpgrade.getSpellSchool().getColour().toWebHexString()+";'>"+spellUpgrade.getPointCost()+"</b> Point"+(spellUpgrade.getPointCost()==1?"":"s")+")"
								:(owner.getSpellUpgradePoints(spellUpgrade.getSpellSchool()) >= spellUpgrade.getPointCost()
										?"Costs <b style='color:"+spellUpgrade.getSpellSchool().getColour().toWebHexString()+";'>"+spellUpgrade.getPointCost()+"</b> Point"+(spellUpgrade.getPointCost()==1?"":"s")+" - [style.colourGood(Can afford!)]"
										:"Costs <b style='color:"+spellUpgrade.getSpellSchool().getColour().toWebHexString()+";'>"+spellUpgrade.getPointCost()+"</b> Point"+(spellUpgrade.getPointCost()==1?"":"s")+" - [style.colourBad(Cannot afford!)]"))
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

						+ "<div class='subTitle-third'>" + "<b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Core</b><br/>"
						+ (owner.getBaseAttributeValue(attribute) > 0 ? "<span style='color: " + Colour.GENERIC_EXCELLENT.getShades()[1] + ";'>" : "<span>")
							+ Units.number(owner.getBaseAttributeValue(attribute), 1, 1)
						+ "</span>" + "</div>"
						
						+ "<div class='subTitle-third'>" + "<b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Bonus</b><br/>"
						+ ((owner.getBonusAttributeValue(attribute)) > 0 ? "<span style='color: " + Colour.GENERIC_GOOD.getShades()[1] + ";'>"
								: ((owner.getBonusAttributeValue(attribute)) == 0 ? "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>" : "<span style='color: " + Colour.GENERIC_BAD.getShades()[1] + ";'>"))
						+ Units.number(owner.getBonusAttributeValue(attribute), 1, 1)+ "</span>" + "</div>"
						
						+ "<div class='subTitle-third'>" + "<b style='color:" + attribute.getColour().toWebHexString() + ";'>Total</b><br/>" + Units.number(owner.getAttributeValue(attribute), 1, 1)
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
							tooltipSB.append("<br/>");
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
					CachedImage image = null;
					boolean displayImage = Main.getProperties().hasValue(PropertyValue.thumbnail)
							&& Main.getProperties().hasValue(PropertyValue.artwork);
					if (displayImage) {
						if (owner.hasArtwork()) {
							image = ImageCache.INSTANCE.requestImage(owner.getCurrentArtwork().getCurrentImage());
						}
						displayImage = image != null;
					}
					
					boolean crotchBreasts = owner.hasBreastsCrotch()
							&& Main.getProperties().udders>0
							&& (owner.isBreastsCrotchVisibleThroughClothing()||owner.isAreaKnownByCharacter(CoverableArea.NIPPLES_CROTCH, Main.game.getPlayer()));
					int crotchBreastAddition = crotchBreasts?24:0;
					
					int[] dimensions = new int[]{419, 508+crotchBreastAddition};
					int imagePadding = 0;
					int imageWidth = 0;
					if (displayImage) {
						// Add the scaled width to the tooltip dimensions
						int[] scaledSize = image.getAdjustedSize(300, 445);
						imageWidth = scaledSize[0];
						dimensions[0] += scaledSize[0];
						// ... and place it in the bottom right corner of the tooltip
						imagePadding = Math.max(0, 455 - scaledSize[1]);
					}

					Main.mainController.setTooltipSize(dimensions[0], dimensions[1]);
					
					tooltipSB.setLength(0);
					tooltipSB.append("<div class='title' style='color:" + owner.getRace().getColour().toWebHexString() + ";'>"
							+(owner.getRaceStage().getName()!=""?"<b style='color:"+owner.getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(owner.getRaceStage().getName())+"</b> ":"")
							+ "<b style='color:"+owner.getSubspecies().getColour(owner).toWebHexString()+";'>"
							+ (owner.isFeminine()?Util.capitaliseSentence(owner.getSubspecies().getSingularFemaleName(owner)):Util.capitaliseSentence(owner.getSubspecies().getSingularMaleName(owner)))
							+ "</b>"
							+ "</div>");

					if (displayImage) {
						tooltipSB.append("<div style='width: 410px; float: left'>");
					}

					// GREATER:
					tooltipSB.append(getBodyPartDivFace(owner, "Face", owner.getFaceRace(), owner.getFaceCovering(), owner.isFaceBestial()));
					tooltipSB.append(getBodyPartDiv(owner, "Torso", owner.getSkinRace(), owner.getSkinCovering(), owner.isSkinBestial(),
							(owner.isSizeDifferenceShorterThan(Main.game.getPlayer())
							?"<span style='color:"+Colour.BODY_SIZE_ONE.toWebHexString()+";'>"
							:(owner.isSizeDifferenceTallerThan(Main.game.getPlayer())
								?"<span style='color:"+Colour.BODY_SIZE_FOUR.toWebHexString()+";'>"
								:"<span>"))
							+"Height: [unit.sizeShort(" + owner.getHeightValue()+ ")]</span>"));
					
					
					// LESSER:
					tooltipSB.append(getBodyPartDiv(owner, Util.capitaliseSentence(Util.intToString(owner.getArmRows()*2))+" arms", owner.getArmRace(), owner.getArmCovering(), owner.isArmBestial()));
					tooltipSB.append(getBodyPartDiv(owner, Util.capitaliseSentence(Util.intToString(owner.getLegCount()))+" "+owner.getFootStructure().getName()+" legs", owner.getLegRace(), owner.getLegCovering(), owner.isLegBestial()));
					
					// PARTIAL:
					tooltipSB.append(getBodyPartDiv(owner, Util.capitaliseSentence(owner.getHairLength().getDescriptor())+" "+owner.getHairStyle().getName()+" "+owner.getHairName(), owner.getHairRace(), owner.getHairCovering(), owner.isHairBestial()));
					tooltipSB.append(getBodyPartDiv(owner, Util.capitaliseSentence(Util.intToString(owner.getEyePairs()*2))+" eyes", owner.getEyeRace(), owner.getEyeCovering(), owner.isEyeBestial()));
					tooltipSB.append(getBodyPartDiv(owner, "Ears", owner.getEarRace(), owner.getEarCovering(), owner.isEarBestial()));
					tooltipSB.append(getBodyPartDiv(owner, "Tongue", owner.getTongueRace(), owner.getTongueCovering(), owner.isTongueBestial()));
					if (owner.getHornType() != HornType.NONE) {
						tooltipSB.append(getBodyPartDiv(owner, Util.capitaliseSentence(Util.intToString(owner.getTotalHorns()))+" "+owner.getHornName(), owner.getHornRace(), owner.getHornCovering(), owner.isHornBestial()));
					} else {
						tooltipSB.append(getEmptyBodyPartDiv("Horns", "None"));
					}
					if (owner.getAntennaType() != AntennaType.NONE) {
						//TODO might need changing if made like horn count:
						tooltipSB.append(getBodyPartDiv(owner, Util.capitaliseSentence(Util.intToString(owner.getAntennaRows()*2))+" antennae", owner.getAntennaRace(), owner.getAntennaCovering(), owner.isAntennaBestial()));
					} else {
						tooltipSB.append(getEmptyBodyPartDiv("Antennae", "None"));
					}
					if (owner.getWingType() != WingType.NONE) {
						tooltipSB.append(getBodyPartDiv(owner, Util.capitaliseSentence(owner.getWingSize().getName())+" wings", owner.getWingRace(), owner.getWingCovering(), owner.isWingBestial()));
					} else {
						tooltipSB.append(getEmptyBodyPartDiv("Wings", "None"));
					}
					if (owner.getTailType() != TailType.NONE) {
						tooltipSB.append(getBodyPartDiv(owner, Util.capitaliseSentence(Util.intToString(owner.getTailCount()))+" tail"+(owner.getTailCount()!=1?"s":""), owner.getTailRace(), owner.getTailCovering(), owner.isTailBestial()));
					} else {
						tooltipSB.append(getEmptyBodyPartDiv("Tail", "None"));
					}
					
					// SEXUAL:
					if(!owner.isPlayer() && !owner.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())) {
						tooltipSB.append(getEmptyBodyPartDiv("Vagina", "Unknown!"));
					} else {
						if (owner.getVaginaType() != VaginaType.NONE) {
							tooltipSB.append(getBodyPartDiv(owner, "Vagina", owner.getVaginaRace(), owner.getVaginaCovering(), owner.isVaginaBestial()));
						} else {
							tooltipSB.append(getEmptyBodyPartDiv("Vagina", "None"));
						}
					}
					
					if(!owner.isPlayer() && !owner.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
						tooltipSB.append(getEmptyBodyPartDiv("Penis", "Unknown!"));
					} else {
						if (owner.hasPenisIgnoreDildo()) {
							tooltipSB.append(getBodyPartDiv(owner, "Penis", owner.getPenisRace(), owner.getPenisCovering(), owner.isPenisBestial(), "[unit.sizes(" + owner.getPenisRawSizeValue()+ ")]"));
						} else if (owner.hasPenis()) {
							tooltipSB.append(getBodyPartDiv(owner, "Penis", owner.getPenisRace(), owner.getPenisCovering(), owner.isPenisBestial(), "[unit.sizes(" + owner.getPenisRawSizeValue()+ ")]"));
						} else {
							tooltipSB.append(getEmptyBodyPartDiv("Penis", "None"));
						}
					}

					if(!owner.isPlayer() && !owner.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer())) {
						tooltipSB.append(getEmptyBodyPartDiv("Anus", "Unknown!"));
					} else {
						tooltipSB.append(getBodyPartDiv(owner, "Anus", owner.getAssRace(), owner.getAssType().getAnusType().getBodyCoveringType(owner), owner.isAnusBestial()));
					}

					if(!owner.isPlayer() && !owner.isAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer())) {
						tooltipSB.append(getEmptyBodyPartDiv("Nipples",
								"Unknown!",
								Util.capitaliseSentence(Util.intToString(owner.getBreastRows()*2))+" "+(owner.getBreastRawSizeValue()>0?(owner.getBreastSize().getCupSizeName() + "-cup breasts"):(owner.isFeminine()?"flat breasts":"pecs"))));
					} else {
						tooltipSB.append(getBodyPartDiv(owner, "Nipples",
								owner.getBreastRace(),
								owner.getBreastType().getNippleType().getBodyCoveringType(owner),
								owner.isNippleBestial(),
								Util.capitaliseSentence(Util.intToString(owner.getBreastRows()*2))+" "+(owner.getBreastRawSizeValue()>0?(owner.getBreastSize().getCupSizeName() + "-cup breasts"):(owner.isFeminine()?"flat breasts":"pecs"))));
					}

					if(crotchBreasts) {
						if(!owner.isAreaKnownByCharacter(CoverableArea.NIPPLES_CROTCH, Main.game.getPlayer())) {
							tooltipSB.append(getEmptyBodyPartDiv("Nipples",
									"Unknown!",
									Util.capitaliseSentence(Util.intToString(owner.getBreastCrotchRows()*2))+" "
											+(owner.getBreastRawSizeValue()>0?(owner.getBreastCrotchSize().getCupSizeName() + "-cup "):"flat ")+(owner.getBreastCrotchShape()==BreastShape.UDDERS?"udders":"crotch-boobs")));
						} else {
							tooltipSB.append(getBodyPartDiv(owner, "Nipples",
									owner.getBreastCrotchRace(),
									owner.getNippleCrotchCovering(),
									owner.isNippleCrotchBestial(),
									Util.capitaliseSentence(Util.intToString(owner.getBreastCrotchRows()*2))+" "
											+(owner.getBreastRawSizeValue()>0?(owner.getBreastCrotchSize().getCupSizeName() + "-cup "):"flat ")+(owner.getBreastCrotchShape()==BreastShape.UDDERS?"udders":"crotch-boobs")));
						}
					}
					
					if (displayImage) {
						boolean revealed = owner.isImageRevealed();
						tooltipSB.append("</div>"
								+ "<div style='float: left;'>"
									+ "<img id='CHARACTER_IMAGE' style='"+(revealed?"":"-webkit-filter: brightness(0%);")
										+" width: auto; height: auto; max-width: 300; max-height: 445; padding-top: " + imagePadding + "px;' src='" + image.getThumbnailString()+ "'/>"
										+(revealed?"":"<p style='position:absolute; top:33%; right:0; width:"+imageWidth+"; font-weight:bold; text-align:center; color:"+Colour.BASE_GREY.toWebHexString()+";'>Unlocked through sex!</p>")
								+ "</div>");
					}
				}
				
				Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

			} else {
				Main.mainController.setTooltipSize(360, 234);

				Main.mainController.setTooltipContent(UtilText.parse(
						"<div class='title' style='color:" + attribute.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(attribute.getName()) + "</div>"

						+ "<div class='subTitle-third'>"
						+ "<b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Core</b><br/>"
						+ (owner.getBaseAttributeValue(attribute) > 0 ? "<span style='color: " + Colour.GENERIC_EXCELLENT.getShades()[1] + ";'>" : "<span>")
							+ Units.number(owner.getBaseAttributeValue(attribute), 1, 1)
						+ "</span>"
						+ "</div>"
						+ "<div class='subTitle-third'>"
						+ "<b style='color:"
						+ Colour.TEXT_GREY.toWebHexString()
						+ ";'>Bonus</b><br/>"
						+ ((owner.getBonusAttributeValue(attribute)) > 0 ? "<span style='color: "
								+ Colour.GENERIC_GOOD.getShades()[1]
								+ ";'>"
								: ((owner.getBonusAttributeValue(attribute)) == 0 ? "<span style='color:"
										+ Colour.TEXT_GREY.toWebHexString()
										+ ";'>"
										: "<span style='color: "
												+ Colour.GENERIC_BAD.getShades()[1]
												+ ";'>"))
						+ Units.number(owner.getBonusAttributeValue(attribute), 1, 1)
						+ "</span>"
						+ "</div>"
						+ "<div class='subTitle-third'>"
						+ "<b style='color:"
						+ attribute.getColour().toWebHexString() + ";'>Total</b><br/>" + Units.number(owner.getAttributeValue(attribute), 1, 1) + "</span>"
						+ "</div>"

						+ "<div class='description'>" + attribute.getDescription(owner) + "</div>"));
				
			}

		} else if (extraAttributes) {

//			if(owner.isRaceConcealed()) {
//				Main.mainController.setTooltipSize(420, 64);
//				
//				tooltipSB.setLength(0);
//				tooltipSB.append("<div class='title' style='color:" + Colour.RACE_UNKNOWN.toWebHexString() + ";'>"
//						+ "Unknown Stats!"
//						+ "</div>");
//				
//			} else {
				Main.mainController.setTooltipSize(400, 516+(Main.game.isEnchantmentCapacityEnabled()?42:0));
	
				int enchantmentPointsUsed = owner.getEnchantmentPointsUsedTotal();
				tooltipSB.setLength(0);
				tooltipSB.append(UtilText.parse(owner,
						"<div class='title' style='color:" + Femininity.valueOf(owner.getFemininityValue()).getColour().toWebHexString() + ";'>"
								+ (owner.getName(true).length() == 0
									? "[npc.Race]"
									: (owner.isPlayer()
											?"[pc.Name]"
											:"[npc.Name]"))
							+ "</div>"
						
						+"<div class='subTitle' style='margin-bottom:4px;'>Level " + owner.getLevel() + " <span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>| "
							+ (owner instanceof Elemental
									?"Elementals share their summoner's level</span>"
									:"</span>"+owner.getExperience() + " / "+ (10 * owner.getLevel()) + " xp")
						+ "</div>"
				
						+ (Main.game.isEnchantmentCapacityEnabled()
								?"<div class='subTitle' style='margin-bottom:4px;'>"
										+ "[style.colourEnchantment("+Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+")]: "
										+ (enchantmentPointsUsed>owner.getAttributeValue(Attribute.ENCHANTMENT_LIMIT)
												?"[style.colourBad("
												:(enchantmentPointsUsed==owner.getAttributeValue(Attribute.ENCHANTMENT_LIMIT)
														?"[style.colourGood("
														:"[style.colourMinorGood("))
										+ enchantmentPointsUsed + ")]" + "/" + Math.round(owner.getAttributeValue(Attribute.ENCHANTMENT_LIMIT))
									+ "</div>"
								:"")
						
						+ extraAttributeBonus(owner, Attribute.MAJOR_PHYSIQUE)
						+ extraAttributeBonus(owner, Attribute.MAJOR_ARCANE)
						+ extraAttributeBonus(owner, Attribute.MAJOR_CORRUPTION)
						+ extraAttributeBonus(owner, Attribute.CRITICAL_DAMAGE)
						
						+ extraAttributeBonus(owner, Attribute.SPELL_COST_MODIFIER)
						+ extraAttributeBonus(owner, Attribute.DAMAGE_SPELLS)
						
						+ extraAttributeBonus(owner, Attribute.DAMAGE_UNARMED)
						+ extraAttributeBonus(owner, Attribute.DAMAGE_MELEE_WEAPON)
						+ extraAttributeBonus(owner, Attribute.DAMAGE_RANGED_WEAPON)
						
						+ extraAttributeBonus(owner, Attribute.ENERGY_SHIELDING)
	
						// Header:
						+ "<div class='subTitle-third combatValue' style='padding:2px; margin:2px 1%; width:31%;'>"
							+ "Type"
						+ "</div>"
							+ "<div class='subTitle-third combatValue' style='padding:2px; margin:2px 1%; width:31%;'>"
						+ "Damage"
							+ "</div>"
						+ "<div class='subTitle-third combatValue' style='padding:2px; margin:2px 1%; width:31%;'>"
							+ "Shielding"
						+ "</div>"
	
						// Values:
						+ extraAttributeTableRow(owner, "Physical", Attribute.DAMAGE_PHYSICAL, Attribute.RESISTANCE_PHYSICAL)
						+ extraAttributeTableRow(owner, "Fire", Attribute.DAMAGE_FIRE, Attribute.RESISTANCE_FIRE)
						+ extraAttributeTableRow(owner, "Cold", Attribute.DAMAGE_ICE, Attribute.RESISTANCE_ICE)
						+ extraAttributeTableRow(owner, "Poison", Attribute.DAMAGE_POISON, Attribute.RESISTANCE_POISON)
						+ extraAttributeTableRow(owner, "Seduction", Attribute.DAMAGE_LUST, Attribute.RESISTANCE_LUST)
						
						+ extraAttributeBonus(owner, Attribute.FERTILITY)
						+ extraAttributeBonus(owner, Attribute.VIRILITY)));
//			}
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (weather) {

			Main.mainController.setTooltipSize(360, 100);

			tooltipSB.setLength(0);
			int minutes = Math.max(1, Main.game.getWeatherTimeRemainingInSeconds()/60);
			int hours = minutes/60;
			tooltipSB.append(
				"<div class='title'>"
					+ "<b style='color:" + Main.game.getCurrentWeather().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(Main.game.getCurrentWeather().getName()) + "</b>"
				+ "</div>"
				+ "<div class='title'><b>"
					+ (hours>0
							?hours+" hour"+(hours>1?"s ":" ")
							:"")
					+ (minutes%60>0
							?minutes+" minute"+(minutes>1?"s ":" ")
							:"")
					+"remaining"
				+ "</b></div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (protection) {

			Main.mainController.setTooltipSize(360, 100);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>Protection</div>"
					+ "<div class='subTitle'>"
					+ (owner.isWearingCondom()?"<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Wearing Condom</span>":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>No Condom</span>")
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
					+ "Click to copy the currently displayed dialogue to your clipboard.<br/><br/>"
					+ "This scene was written by <b style='color:"+Colour.ANDROGYNOUS.toWebHexString()+";'>"
					+ Main.game.getCurrentDialogueNode().getAuthor()
					+ "</b></div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if(concealedSlot!=null) {

			Map<InventorySlot, List<AbstractClothing>> concealedSlots = RenderingEngine.getCharacterToRender().getInventorySlotsConcealed();
			
			List<AbstractClothing> clothingVisible = concealedSlots.get(concealedSlot).stream().filter(clothing -> !concealedSlots.containsKey(clothing.getSlotEquippedTo())).collect(Collectors.toList());
			
			Main.mainController.setTooltipSize(360, 175);

			Main.mainController.setTooltipContent(UtilText.parse(
					"<div class='title'>"+Util.capitaliseSentence(concealedSlot.getName())+" - [style.boldBad(Concealed!)]</div>"
					+ "<div class='description'>"
						+ UtilText.parse(RenderingEngine.getCharacterToRender(),
							(concealedSlots.get(concealedSlot).isEmpty()
								?"Due to [npc.namePos] position, this slot is currently hidden from view!"
								:(clothingVisible.isEmpty()
										?"This slot is currently hidden from view by items of [npc.namePos] clothing that you cannot see!"
										:"This slot is currently hidden from view by [npc.namePos] <b>"+Util.clothesToStringList(clothingVisible, false)+"</b>.")))
					+ "</div>"));
			
		} else if(loadedEnchantment!=null) {
			//TODO
			
			int yIncrease = 0;

			// Title:
			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(loadedEnchantment.getName()) + "</div>");

			if(loadedEnchantment.isSuitableItemAvailable()) {
				tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Suitable item in inventory</div>");
			} else {
				tooltipSB.append("<div class='subTitle' style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>No suitable item in inventory</div>");
			}
			
			// Attribute modifiers:
			tooltipSB.append("<div class='subTitle-picture'>");
			int i=0;
			for (ItemEffect ie : loadedEnchantment.getEffects()) {
				for(String s : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
					tooltipSB.append((i!=0?"<br/>":"") + s);
					yIncrease++;
					if(UtilText.parse(s).replaceAll("<.*?>", "").length()>32) { // Yes, this is terrible...
						yIncrease++;
					}
				}
				i++;
			}
			if(yIncrease>=5) {
				yIncrease-=5;
			} else {
				yIncrease=0;
			}
			tooltipSB.append("</div>");

			// Picture:
			tooltipSB.append("<div class='picture'>" + loadedEnchantment.getSVGString() + "</div>");

			Main.mainController.setTooltipSize(360, 208 + (yIncrease>0?4:0) + (yIncrease * LINE_HEIGHT));
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
			
			
		} else if(cell!=null) {

			Set<NPC> charactersPresent = new HashSet<>(Main.game.getCharactersPresent(cell));
			if(!cell.equals(Main.game.getWorlds().get(WorldType.DOMINION).getCell(0, 0))) { // Override as NPCs had their home placed here... Add a version catch?
				charactersPresent.addAll(Main.game.getCharactersTreatingCellAsHome(cell));
			}

			int yIncrease = 0;
			
			StringBuilder charactersPresentDescription = new StringBuilder();
			
			if(Main.game.getCurrentDialogueNode() != Library.DOMINION_MAP) {
				if(!charactersPresent.isEmpty()) {
					for(NPC character : charactersPresent) {
						yIncrease++;
						charactersPresentDescription.append(
								(Main.game.getCharactersPresent(cell).contains(character)
										?character.getName("The")
										:"[style.colourDisabled("+character.getName("The")+")]")
								+": "+(character.isRaceConcealed()?"[style.colourDisabled(Unknown race!)]":UtilText.parse(character, "[npc.FullRace(true)]"))
								+"<br/>");
					}
					
				}
//				else {
//					charactersPresentDescription.append("No characters present...");
//				}
			}

			Main.mainController.setTooltipSize(360, 175+(yIncrease>0?32:0)+(yIncrease * LINE_HEIGHT));
			
			String tooltipDesc = cell.getPlace().getPlaceType().getTooltipDescription();
			
			Main.mainController.setTooltipContent(UtilText.parse(
					"<div class='title'>"+Util.capitaliseSentence(cell.getPlaceName())+"</div>"
					+ "<div class='description'>"
						+ (tooltipDesc==null || tooltipDesc.isEmpty()
							?""
							:tooltipDesc+"<br/>")
						+(cell.getPlace().getPlaceType().isDangerous()
							?"This is a [style.italicsBad(dangerous)] area!"
							:"This is a [style.italicsGood(safe)] area.")
					+ "</div>"
					+ (yIncrease>0
							?"<div class='description' style='height:"+(24 + yIncrease * LINE_HEIGHT)+"px;'>"+charactersPresentDescription.toString()+"</div>"
							:"")));
			
		} else { // Standard information:
			if(description==null || description.isEmpty()) {
				Main.mainController.setTooltipSize(360, 64);

				Main.mainController.setTooltipContent(UtilText.parse(
						"<div class='title'>"+title+"</div>"));
				
			} else if(title==null || title.isEmpty()) {
				Main.mainController.setTooltipSize(360, descriptionHeightOverride>0?descriptionHeightOverride+64+32:200);

				Main.mainController.setTooltipContent(UtilText.parse(
						"<div class='description' style='height:"+(descriptionHeightOverride>0?(descriptionHeightOverride+26):"176")+"px;'>"+description+"</div>"));
				
			} else {
				Main.mainController.setTooltipSize(360, descriptionHeightOverride>0?descriptionHeightOverride+64+32:175);

				Main.mainController.setTooltipContent(UtilText.parse(
						"<div class='title'>"+title+"</div>"
						+ "<div class='description' "+(descriptionHeightOverride>0?"style='height:"+(descriptionHeightOverride+26)+"px;'":"")+">" + description + "</div>"));
			}
		}

		TooltipUpdateThread.updateToolTip(-1,-1);
	}

	private String getBodyPartDiv(GameCharacter character, String name, Race race, BodyCoveringType covering, boolean bestial) {
		return getBodyPartDiv(character, name, race, covering, bestial, null);
	}
	
	private String getBodyPartDiv(GameCharacter character, String name, Race race, BodyCoveringType covering, boolean bestial, String size) {
		String raceName;
		raceName = race.getName(character, bestial);

		if(raceName.equals("wolf-morph") && Main.getProperties().hasValue(PropertyValue.sillyMode)){
			raceName = "awoo-morph";
		}
		if(raceName.equals("cat-morph") && Main.getProperties().hasValue(PropertyValue.sillyMode)){
			raceName = "catte-morph";
		}
		if(raceName.equals("harpy") && Main.getProperties().hasValue(PropertyValue.sillyMode)){
			raceName = "birb";
		}
		
		return "<div class='subTitle' style='font-weight:normal; text-align:left; margin-top:2px; white-space: nowrap;'>"
					+ name +(size!=null?" ("+size+"): ":": ")
					+ (bestial&&race!=Race.NONE?"[style.colourBestial(Feral )]":"")
					+(covering!=BodyCoveringType.DILDO
						?"<span style='color:" + race.getColour().toWebHexString() + ";'>"
							+Util.capitaliseSentence(raceName)
						:"<span style='color:" + Colour.BASE_PINK_DEEP.toWebHexString() + ";'>"
								+"Dildo")
					+ "</span> - "
					+ owner.getCovering(covering).getColourDescriptor(owner, true, true) + " " + owner.getCovering(covering).getName(owner)
				+"</div>";
	}
	
	private String getBodyPartDivFace(GameCharacter character, String name, Race race, BodyCoveringType covering, boolean bestial) {
		String raceName;
		raceName = race.getName(character, bestial);

		if(raceName.equals("wolf-morph") && Main.getProperties().hasValue(PropertyValue.sillyMode)){
			raceName = "awoo-morph";
		}
		if(raceName.equals("cat-morph") && Main.getProperties().hasValue(PropertyValue.sillyMode)){
			raceName = "catte-morph";
		}
		if(raceName.equals("harpy") && Main.getProperties().hasValue(PropertyValue.sillyMode)){
			raceName = "birb";
		}
		
		Covering coveringHandledForFreckles = character.getCovering(covering);
		
		if(character.getCovering(covering).getPattern()==CoveringPattern.FRECKLED_FACE) {
			coveringHandledForFreckles = new Covering(covering,
					CoveringPattern.FRECKLED,
					character.getCovering(covering).getModifier(),
					character.getCovering(covering).getPrimaryColour(),
					character.getCovering(covering).isPrimaryGlowing(),
					character.getCovering(covering).getSecondaryColour(),
					character.getCovering(covering).isSecondaryGlowing());
		}
		
		return "<div class='subTitle' style='font-weight:normal; text-align:left; margin-top:2px; white-space: nowrap;'>"
					+ name+": "
					+ (bestial&&race!=Race.NONE?"[style.colourBestial(Feral )]":"")
					+(covering!=BodyCoveringType.DILDO
						?"<span style='color:" + race.getColour().toWebHexString() + ";'>"
							+Util.capitaliseSentence(raceName)
						:"<span style='color:" + Colour.BASE_PINK_DEEP.toWebHexString() + ";'>"
								+"Dildo")
					+ "</span> - "
					+ coveringHandledForFreckles.getColourDescriptor(owner, true, true) + " " + coveringHandledForFreckles.getName(owner)
				+"</div>";
		
	}
	
	private String getEmptyBodyPartDiv(String name, String description) {
		return getEmptyBodyPartDiv(name, description, null);
	}

	private String getEmptyBodyPartDiv(String name, String description, String size) {
		return "<div class='subTitle' style='font-weight:normal; text-align:left; margin-top:2px; white-space: nowrap;'>"
					+ name +(size!=null?" ("+size+"): ":": ")+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>"+description+"</span>"
			+ "</div>";
	}

	private String extraAttributeTableRow(GameCharacter owner, String type, Attribute damage, Attribute resist) {
		return "<div class='subTitle-third combatValue' style='padding:2px; margin:2px 1%; width:31%;'>"
				+ "<span style='color:" + damage.getColour().toWebHexString() + ";'>" + type + "</span>"
				+ "</div>"
				+ "<div class='subTitle-third combatValue' style='padding:2px; margin:2px 1%; width:31%;'>"
					+ (owner.getAttributeValue(damage) > damage.getBaseValue()
										? "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>"
										: (owner.getAttributeValue(damage) < damage.getBaseValue()
												? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>"
												: ""))
						+ owner.getAttributeValue(damage)
					+ "</span>"
				+ "</div>"
				+ "<div class='subTitle-third combatValue' style='padding:2px; margin:2px 1%; width:31%;'>"
					+ (resist == null
							? "0.0"
							: (owner.getAttributeValue(resist) > 0
									? "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>"
									: (owner.getAttributeValue(resist) < 0
											? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>"
											: ""))
						+ owner.getAttributeValue(resist)
					+ "</span>")
				+ "</div>";
	}

	private String extraAttributeBonus(GameCharacter owner, Attribute bonus) {
		return "<div class='subTitle-half' style='padding:2px; margin:2px 1%; width:48%;'>"
				+ "<span style='color:"+ bonus.getColour().toWebHexString() + ";'>"
					+ Util.capitaliseSentence(bonus.getName())
				+ "</span><br/>" + (owner.getAttributeValue(bonus) > bonus.getBaseValue()
						? "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>" : (owner.getAttributeValue(bonus) < bonus.getBaseValue() ? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>" : ""))
				+ owner.getAttributeValue(bonus) + "</span>" + "</div>";
	}

	public TooltipInformationEventListener setInformation(String title, String description) {
		resetFields();
		this.title = title;
		this.description = description;

		return this;
	}

	public TooltipInformationEventListener setInformation(String title, String description, int descriptionHeightOverride) {
		setInformation(title, description);
		this.descriptionHeightOverride = descriptionHeightOverride;

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

	public TooltipInformationEventListener setPerk(AbstractPerk perk, GameCharacter owner) {
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

	public TooltipInformationEventListener setLevelUpPerk(int perkRow, AbstractPerk levelUpPerk, GameCharacter owner, boolean availableForSelection) {
		resetFields();
		this.levelUpPerk = levelUpPerk;
		this.perkRow = perkRow;
		this.owner = owner;
		this.availableForSelection = availableForSelection;

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

	public TooltipInformationEventListener setLoadedEnchantment(LoadedEnchantment loadedEnchantment) {
		resetFields();
		this.loadedEnchantment = loadedEnchantment;

		return this;
	}

	public TooltipInformationEventListener setCombatMove(CombatMove move, GameCharacter owner) {
		resetFields();
		this.owner = owner;
		this.move = move;
		
		return this;
	}
	
	public TooltipInformationEventListener setCell(Cell cell) {
		resetFields();
		this.cell = cell;

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
		availableForSelection = false;
		perkRow = 0;
		spell = null;
		spellUpgrade = null;
		attribute = null;
		protection=false;
		copyInformation=false;
		concealedSlot=null;
		loadedEnchantment=null;
		move=null;
		descriptionHeightOverride = 0;
		cell = null;
	}
}
