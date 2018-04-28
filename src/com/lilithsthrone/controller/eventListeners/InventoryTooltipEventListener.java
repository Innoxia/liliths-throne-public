package com.lilithsthrone.controller.eventListeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * Shows the tooltip at the given element's position.
 * 
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public class InventoryTooltipEventListener implements EventListener {
	private GameCharacter owner, equippedToCharacter;
	private AbstractCoreItem coreItem;
	private AbstractItem item;
	private AbstractItemType genericItem;
	private AbstractWeapon weapon;
	private AbstractWeaponType genericWeapon;
	private DamageType dt;
	private AbstractClothing clothing;
	private Colour colour;
	private Colour secondaryColour;
	private Colour tertiaryColour;
	private AbstractClothingType genericClothing;
	private AbstractClothing dyeClothing;
	private InventorySlot invSlot;
	private TFModifier enchantmentModifier;
	private TFPotency potency;
	private TFEssence essence;
	private static StringBuilder tooltipSB = new StringBuilder();

	private static final int LINE_HEIGHT = 16;
	private static final int LINE_HEIGHT_TITULAR = 18;
	
	@Override
	public void handleEvent(Event event) {
		if (item != null || (coreItem instanceof AbstractItem)) {
			
			if(coreItem != null) {
				item = (AbstractItem) coreItem;
			}
			
			int yIncrease = 0;
			if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
				yIncrease += 2;
			}
			
			if(!item.getEffects().isEmpty()) {
				yIncrease+=1;
				for(ItemEffect ie : item.getEffects()) {
					yIncrease += ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size();
				}
			}
			
			Main.mainController.setTooltipSize(360, 256 + (LINE_HEIGHT * yIncrease));

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(item.getDisplayName(true)) + "</div>");

			if(!item.getEffects().isEmpty()) {
				tooltipSB.append("<div class='subTitle'>");
				int ieCount=0;
				for(ItemEffect ie : item.getEffects()) {
					if(ieCount>0)
						tooltipSB.append("</br>");
					ieCount++;
					for(int i=0; i<ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size(); i++) {
						if(i!=0)
							tooltipSB.append("</br>");
						tooltipSB.append(ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).get(i));
					}
				}
				tooltipSB.append("</div>");
			}

			tooltipSB.append("<div class='subTitle-half'>"
					+ (item.isConsumedOnUse() ? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Consumed on use</span>" : "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Infinite uses</span>") + "</div>"
					+ "<div class='subTitle-half'>"
					+ (item.isAbleToBeUsedInCombat()
							? (item.isAbleToBeUsedInSex()
									?"<span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span> & <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span> use"
									:"<span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span> use")
							: (item.isAbleToBeUsedInSex()
									?"<span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span> use"
									:"Standard use"))
					+ "</div>"

					+ "<div class='description' style='height:104px'>" + UtilText.parse(item.getDescription()) + "</div>"
					
					
					+ "<div class='subTitle'>" + UtilText.formatAsMoney(item.getValue()) + "</div>");

			if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
				if (owner.isPlayer()) {
					if (InventoryDialogue.getInventoryNPC().willBuy(item))
						tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " offers " + UtilText.formatAsMoney(item.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier())) + "</div>");
					else
						tooltipSB.append(
								"<div class='subTitle'>" + "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>" + "</div>");
				} else {
					if (InventoryDialogue.isBuyback()) {
						tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(getBuybackPriceFor(item)) + "</div>");
					} else {
						tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(item.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier())) + "</div>");
					}
					
				}
			}

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (weapon != null || (coreItem instanceof AbstractWeapon)) {
			if(coreItem != null)
				weapon = (AbstractWeapon) coreItem;

			weaponTooltip(weapon);

		} else if (clothing != null || (coreItem instanceof AbstractClothing)) {
			if(coreItem != null)
				clothing = (AbstractClothing) coreItem;
			
			clothingTooltip(clothing);

		} else if (dyeClothing != null) {

			Main.mainController.setTooltipSize(360, 446);

			tooltipSB.setLength(0);
			if(colour!=null) {
				tooltipSB.append("<div class='title' style='color:" + dyeClothing.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(dyeClothing.getName()) + "</div>"
						
						+ "<div class='subTitle'>" + Util.capitaliseSentence(colour.getName()) + "</div>"
	
						+ "<div class='picture full' style='position:relative;'>" + dyeClothing.getClothingType().getSVGImage(colour, InventoryDialogue.dyePreviewSecondary, InventoryDialogue.dyePreviewTertiary) + "</div>");
			
			} else if(secondaryColour!=null) {
				tooltipSB.append("<div class='title' style='color:" + dyeClothing.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(dyeClothing.getName()) + "</div>"
						
						+ "<div class='subTitle'>" + Util.capitaliseSentence(secondaryColour.getName()) + "</div>"
	
						+ "<div class='picture full' style='position:relative;'>" + dyeClothing.getClothingType().getSVGImage(InventoryDialogue.dyePreviewPrimary, secondaryColour, InventoryDialogue.dyePreviewTertiary) + "</div>");
				
			} else if(tertiaryColour!=null) {
				tooltipSB.append("<div class='title' style='color:" + dyeClothing.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(dyeClothing.getName()) + "</div>"
						
						+ "<div class='subTitle'>" + Util.capitaliseSentence(tertiaryColour.getName()) + "</div>"
	
						+ "<div class='picture full' style='position:relative;'>" + dyeClothing.getClothingType().getSVGImage(InventoryDialogue.dyePreviewPrimary, InventoryDialogue.dyePreviewSecondary, tertiaryColour) + "</div>");
				
			}
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (genericItem != null) {
			Main.mainController.setTooltipSize(360, 60);
			Main.mainController.setTooltipContent(UtilText.parse("<div class='title'>" + Util.capitaliseSentence(genericItem.getName(false)) + "</div>"));
			
		} else if (genericClothing != null) {

			Main.mainController.setTooltipSize(360, 446);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title' style='color:" + genericClothing.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(genericClothing.getName()) + "</div>"
					
					+ "<div class='subTitle'>" + Util.capitaliseSentence(colour.getName()) + "</div>"

					+ "<div class='picture full' style='position:relative;'>" + genericClothing.getSVGImage(colour,
							genericClothing.getAvailableSecondaryColours().isEmpty()?null:genericClothing.getAvailableSecondaryColours().get(0),
							genericClothing.getAvailableTertiaryColours().isEmpty()?null:genericClothing.getAvailableTertiaryColours().get(0)) + "</div>");
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (genericWeapon != null) {

			Main.mainController.setTooltipSize(360, 446);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title' style='color:" + genericWeapon.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(genericWeapon.getName()) + "</div>"

					+ "<div class='subTitle'>" + Util.capitaliseSentence(dt.getName()) + "</div>"

					+ "<div class='picture full'>" + genericWeapon.getSVGStringMap().get(dt) + "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (invSlot != null) {
			if (invSlot == InventorySlot.WEAPON_MAIN) {

				if (equippedToCharacter != null) {
					if (equippedToCharacter.getMainWeapon() == null) {
						Main.mainController.setTooltipSize(360, 60);
						Main.mainController.setTooltipContent("<div class='title'>Primary Weapon</div>");

					} else {
						weaponTooltip(equippedToCharacter.getMainWeapon());
					}
				} else {
					Main.mainController.setTooltipSize(360, 60);
					Main.mainController.setTooltipContent("<div class='title'>Primary Weapon</div>");
				}

			} else if (invSlot == InventorySlot.WEAPON_OFFHAND) {

				if (equippedToCharacter != null) {
					if (equippedToCharacter.getOffhandWeapon() == null) {
						Main.mainController.setTooltipSize(360, 60);
						Main.mainController.setTooltipContent("<div class='title'>Secondary Weapon</div>");

					} else {
						weaponTooltip(equippedToCharacter.getOffhandWeapon());
					}
				} else {
					Main.mainController.setTooltipSize(360, 60);
					Main.mainController.setTooltipContent("<div class='title'>Secondary Weapon</div>");
				}

			} else {

				if (equippedToCharacter != null) {
					if (equippedToCharacter.getClothingInSlot(invSlot) == null) {
						
						List<String> clothingBlockingThisSlot = new ArrayList<>();
						for (AbstractClothing c : equippedToCharacter.getClothingCurrentlyEquipped()) {
							if (c.getClothingType().getIncompatibleSlots().contains(invSlot)) {
								clothingBlockingThisSlot.add(c.getName());
							}
						}
						
						if (!clothingBlockingThisSlot.isEmpty()) {
							setBlockedTooltipContent("This slot is currently <b style='color:" + Colour.SEALED.toWebHexString() + ";'>blocked</b> by your " + Util.stringsToStringList(clothingBlockingThisSlot, false) + ".");
							
						} else if (invSlot.slotBlockedByRace(equippedToCharacter) != null) {
							setBlockedTooltipContent(invSlot.getCannotBeWornDescription(equippedToCharacter));
							
						} else {
							
							boolean piercingBlocked=false;
							boolean bypassesPiercing = !equippedToCharacter.getBody().getBodyMaterial().isRequiresPiercing();
							
							switch(invSlot){
								case PIERCING_VAGINA:
									if(equippedToCharacter.getVaginaType()==VaginaType.NONE) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"You don't have a vagina.",
													(equippedToCharacter.getPlayerKnowsAreas().contains(CoverableArea.VAGINA)
														?"[npc.Name] doesn't have a vagina."
														:"You don't know if [npc.name] has a vagina.")));
										piercingBlocked=true;
										
									} else if(!bypassesPiercing && !equippedToCharacter.isPiercedVagina()) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"Your vagina has not been pierced.",
												(equippedToCharacter.getPlayerKnowsAreas().contains(CoverableArea.VAGINA)
														?"[npc.Name]'s vagina has not been pierced."
														:"You don't know if [npc.name] has a vagina.")));
										piercingBlocked=true;
									}
									break;
									
								case PIERCING_EAR:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedEar()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your ears have not been pierced.",
													"[npc.Name]'s ears have not been pierced."));
											piercingBlocked=true;
										}
									}
									break;
									
								case PIERCING_LIP:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedLip()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your lips have not been pierced.",
													"[npc.Name]'s lips have not been pierced."));
											piercingBlocked=true;
										}
									}
									break;
									
								case PIERCING_NIPPLE:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedNipple()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your nipples have not been pierced.",
													(equippedToCharacter.getPlayerKnowsAreas().contains(CoverableArea.NIPPLES)
															?"[npc.Name]'s nipples have not been pierced."
															:"You don't know if [npc.name]'s nipples have been pierced.")));
											piercingBlocked=true;
										}
									}
									break;
									
								case PIERCING_NOSE:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedNose()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your nose has not been pierced.",
													"[npc.Name]'s nose has not been pierced."));
											piercingBlocked=true;
										}
									}
									break;
									
								case PIERCING_PENIS:
									if(equippedToCharacter.getPenisType()==PenisType.NONE) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"You don't have a penis.",
												(equippedToCharacter.getPlayerKnowsAreas().contains(CoverableArea.PENIS)
														?"[npc.Name] doesn't have a penis."
														:"You don't know if [npc.name] has a penis.")));
										piercingBlocked=true;
										
									} else if(!bypassesPiercing && !equippedToCharacter.isPiercedPenis()) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"Your penis has not been pierced.",
												(equippedToCharacter.getPlayerKnowsAreas().contains(CoverableArea.PENIS)
														?"[npc.Name]'s penis has not been pierced."
														:"You don't know if [npc.name] has a penis.")));
										piercingBlocked=true;
									}
									break;
									
								case PIERCING_STOMACH:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedNavel()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your navel has not been pierced.",
													"[npc.Name]'s navel has not been pierced."));
											piercingBlocked=true;
										}
									}
									break;
									
								case PIERCING_TONGUE:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedTongue()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your tongue has not been pierced.",
													"[npc.Name]'s tongue has not been pierced."));
											piercingBlocked=true;
										}
									}
									break;
								default:
									break;
							}
							
							if(!piercingBlocked){
								Main.mainController.setTooltipSize(360, 60);
								Main.mainController.setTooltipContent(UtilText.parse("<div class='title'>" + Util.capitaliseSentence(invSlot.getName()) + "</div>"));
							}
						}

					} else {
						clothingTooltip(equippedToCharacter.getClothingInSlot(invSlot));
					}
				} else {
					Main.mainController.setTooltipSize(360, 60);
					Main.mainController.setTooltipContent(UtilText.parse("<div class='title'>" + Util.capitaliseSentence(invSlot.getName()) + "</div>"));
				}
			}
			
		} else if (enchantmentModifier != null) {
			Main.mainController.setTooltipSize(360, 152);
			Main.mainController.setTooltipContent(UtilText.parse(
					"<div class='title' style='color:"+enchantmentModifier.getRarity().getColour().toWebHexString()+";'>"
							+ Util.capitaliseSentence(enchantmentModifier.getName())
					+ "</div>"
					+ "<div class='description' style='height:48px'>"
					+ UtilText.parse(enchantmentModifier.getDescription())
					+ "</div>"
					+ "<div class='subTitle'>"
							+ "<b style='color:"+EnchantmentDialogue.ingredient.getRelatedEssence().getColour().toWebHexString() + ";'>+"
								+ enchantmentModifier.getValue()+" "+EnchantmentDialogue.ingredient.getRelatedEssence().getName()+"</b> essence cost"
					+ "</div>"));
		
		} else if(potency!=null) {
			Main.mainController.setTooltipSize(360, 60);
			Main.mainController.setTooltipContent(UtilText.parse("<div class='title'>Set potency to <b style='color:"+potency.getColour().toWebHexString()+";'>" + Util.capitaliseSentence(potency.getName()) + "</b></div>"));
			
		} else if (essence != null) {
			Main.mainController.setTooltipSize(360, 60);
			Main.mainController.setTooltipContent(UtilText.parse("<div class='title'><b style='color:"+essence.getColour().toWebHexString()+";'>" + Util.capitaliseSentence(essence.getName()) + "</b> essence</div>"));
		
		}  else {
			return;
		}

		(new Thread(new TooltipUpdateThread())).start();
		// Main.mainController.getTooltip().show(Main.primaryStage);
	}

	private void setBlockedTooltipContent(String description){
		Main.mainController.setTooltipSize(360, 164);
		Main.mainController.setTooltipContent(UtilText.parse(
				"<div class='title'>" + Util.capitaliseSentence(invSlot.getName()) + ": <span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Blocked!</span></div>"
				+ "<div class='description'>" + UtilText.parse(description) + "</div>"));
	}
	
	
	public InventoryTooltipEventListener setCoreItem(AbstractCoreItem coreItem, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.coreItem = coreItem;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}
	
	public InventoryTooltipEventListener setItem(AbstractItem item, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.item = item;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}
	
	public InventoryTooltipEventListener setGenericItem(AbstractItemType genericItem) {
		resetVariables();
		this.genericItem = genericItem;
		return this;
	}

	public InventoryTooltipEventListener setClothing(AbstractClothing clothing, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.clothing = clothing;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}

	public InventoryTooltipEventListener setDyeClothingPrimary(AbstractClothing dyeClothing, Colour colour) {
		resetVariables();
		this.dyeClothing = dyeClothing;
		this.colour = colour;
		return this;
	}
	
	public InventoryTooltipEventListener setDyeClothingSecondary(AbstractClothing dyeClothing, Colour secondaryColour) {
		resetVariables();
		this.dyeClothing = dyeClothing;
		this.secondaryColour = secondaryColour;
		return this;
	}
	
	public InventoryTooltipEventListener setDyeClothingTertiary(AbstractClothing dyeClothing, Colour tertiaryColour) {
		resetVariables();
		this.dyeClothing = dyeClothing;
		this.tertiaryColour = tertiaryColour;
		return this;
	}
	
	public InventoryTooltipEventListener setGenericClothing(AbstractClothingType genericClothing, Colour colour) {
		resetVariables();
		this.genericClothing = genericClothing;
		this.colour = colour;
		return this;
	}

	public InventoryTooltipEventListener setGenericWeapon(AbstractWeaponType genericWeapon, DamageType dt) {
		resetVariables();
		this.genericWeapon = genericWeapon;
		this.dt = dt;
		return this;
	}

	public InventoryTooltipEventListener setWeapon(AbstractWeapon weapon, GameCharacter equippedToCharacter) {
		resetVariables();
		this.weapon = weapon;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = equippedToCharacter;
		return this;
	}

	public InventoryTooltipEventListener setInventorySlot(InventorySlot invSlot, GameCharacter equippedToCharacter) {
		resetVariables();
		this.invSlot = invSlot;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = equippedToCharacter;
		return this;
	}
	
	public InventoryTooltipEventListener setTFModifier(TFModifier enchantmentModifier) {
		resetVariables();
		this.enchantmentModifier = enchantmentModifier;
		return this;
	}
	
	public InventoryTooltipEventListener setTFPotency(TFPotency potency) {
		resetVariables();
		this.potency = potency;
		return this;
	}
	
	public InventoryTooltipEventListener setEssence(TFEssence essence) {
		resetVariables();
		this.essence = essence;
		return this;
	}

	private void resetVariables() {
		owner = null;
		equippedToCharacter = null;
		coreItem = null;
		item = null;
		genericItem = null;
		weapon = null;
		genericWeapon = null;
		dt = null;
		clothing = null;
		colour = null;
		dyeClothing = null;
		secondaryColour = null;
		tertiaryColour = null;
		genericClothing = null;
		invSlot = null;
		enchantmentModifier = null;
		potency = null;
		essence = null;
	}

	private void weaponTooltip(AbstractWeapon absWep) {
		
		int yIncrease = 0;
		int listIncrease = absWep.getAttributeModifiers().size();
		listIncrease += absWep.getSpells().size();
		yIncrease += Math.max(0, listIncrease-3);
		
		
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitaliseSentence(absWep.getDisplayName(true)) + "</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-half-width titular' style='color:"+absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(absWep.getDamageType().getName()) + " damage</div>");
		tooltipSB.append("<div class='container-half-width titular'>"
							+ (absWep.getWeaponType().getSlot() == InventorySlot.WEAPON_MAIN ? "Primary Slot" : "Secondary Slot")
						+ "</div>");
		

		
		// Attribute modifiers:
		tooltipSB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width titular' style='width:calc(66.6% - 16px);'>");
		if (equippedToCharacter != null) {
			tooltipSB.append("<b>"
								+ Attack.getMinimumDamage(owner, null, absWep.getWeaponType().getSlot() == InventorySlot.WEAPON_MAIN ? Attack.MAIN : Attack.OFFHAND, absWep)
								+ " - "
								+ Attack.getMaximumDamage(owner, null, absWep.getWeaponType().getSlot() == InventorySlot.WEAPON_MAIN ? Attack.MAIN : Attack.OFFHAND, absWep)
							+ "</b>"
							+ " <b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</b>");
		} else {
			tooltipSB.append("<b>"
								+ Attack.getMinimumDamage(Main.game.getPlayer(), null, absWep.getWeaponType().getSlot() == InventorySlot.WEAPON_MAIN ? Attack.MAIN : Attack.OFFHAND, absWep)
								+ " - "
								+ Attack.getMaximumDamage(Main.game.getPlayer(), null, absWep.getWeaponType().getSlot() == InventorySlot.WEAPON_MAIN ? Attack.MAIN : Attack.OFFHAND, absWep)
							+ "</b>"
							+ " <b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</b>");
		}

//		if (absWep.getEffects().size() != 0) { TODO enchanting effects
//			for (ItemEffect e : absWep.getEffects()) {
//				for(String s : e.getEffectsDescription(owner, owner)) {
//					tooltipSB.append("</br>"+ s);
//				}
//			}
			for(Entry<Attribute, Integer> entry : absWep.getAttributeModifiers().entrySet()) {
				tooltipSB.append("</br>"+ 
						(entry.getValue()<0
								?"[style.boldBad("+entry.getValue()+")] "
								:"[style.boldGood(+"+entry.getValue()+")] ")
						+ "<b style='color:"+entry.getKey().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getName())+"</b>");
			}
//		} else {
//			tooltipSB.append("</br>[style.colourDisabled(No bonuses)]");
//		}
		
			for(Spell s : absWep.getSpells()) {
				tooltipSB.append("</br><b style='color:"+Colour.DAMAGE_TYPE_SPELL.toWebHexString()+";'>Grants Spell</b><b>:</b> <b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(s.getName())+"</b>");
			}
			
		tooltipSB.append("</div>");
		
		// Picture:
		tooltipSB.append("<div class='container-half-width' style='width:calc(33.3% - 16px);'>"
						+ absWep.getSVGString()
					+ "</div>");

		tooltipSB.append("</div>");

		tooltipSB.append("<div class='container-full-width' style='padding:8px; height:106px;'>"
						+ absWep.getWeaponType().getDescription()
					+ "</div>");

		
		// Value:

		if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			if (owner.isPlayer()) {
				if (InventoryDialogue.getInventoryNPC().willBuy(absWep)) {
					tooltipSB.append("<div class='container-full-width titular'>"
										+ "Value: "+UtilText.formatAsMoney(absWep.getValue())
										+" | "
										+ InventoryDialogue.getInventoryNPC().getName("The") + " offers: " + UtilText.formatAsMoney(absWep.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier()))
									+ "</div>");
				} else {
					tooltipSB.append("<div class='container-full-width titular'>"
										+ "Value: "+UtilText.formatAsMoney(absWep.getValue())
										+" | "
										+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>"
									+ "</div>");
				}
			} else {
				if (InventoryDialogue.isBuyback()) {
					tooltipSB.append("<div class='container-full-width titular'>"
											+ "Value: "+UtilText.formatAsMoney(absWep.getValue())
											+" | "
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney( + getBuybackPriceFor(absWep))
									+ "</div>");
				} else {
					tooltipSB.append("<div class='container-full-width titular'>"
											+ "Value: "+UtilText.formatAsMoney(absWep.getValue())
											+" | "
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(absWep.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier()))
									+ "</div>");
				}
			}
		} else {
			tooltipSB.append("<div class='container-full-width titular'>" + "Value: "+UtilText.formatAsMoney(absWep.getValue()) + "</div>");
		}
		
		tooltipSB.append("</body>");

		Main.mainController.setTooltipSize(360, 356 + (yIncrease * LINE_HEIGHT_TITULAR));
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
		
	}

	private void clothingTooltip(AbstractClothing absClothing) {
		int yIncrease = 0;
				
		int listIncrease = absClothing.getAttributeModifiers().size();

		yIncrease += absClothing.getExtraDescriptions(equippedToCharacter).size();
		
		for(ItemEffect ie : absClothing.getEffects()) {
			if(ie.getPrimaryModifier()==TFModifier.CLOTHING_ENSLAVEMENT
					|| ie.getPrimaryModifier()==TFModifier.CLOTHING_SEALING) {
				listIncrease+=1;
			} else if(ie.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE) {
				listIncrease+=2;
			}
		}
		yIncrease += Math.max(0, listIncrease-4);
		
		
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitaliseSentence(absClothing.getDisplayName(true)) + "</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-half-width titular'>" + Util.capitaliseSentence(absClothing.getClothingType().getSlot().getName()) + "</div>");
		tooltipSB.append("<div class='container-half-width titular'>"
							+ (absClothing.getClothingType().getClothingSet() == null ? "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Not part of a set</span>" : absClothing.getClothingType().getClothingSet().getName() + " set")
						+ "</div>");
		
		// Attribute modifiers:
		tooltipSB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width titular' style='width:calc(66.6% - 16px);'>");
		int res = absClothing.getClothingType().getPhysicalResistance();
		tooltipSB.append(
				(res>0
					?"[style.boldGood(+"+absClothing.getClothingType().getPhysicalResistance()+")]"
					:"[style.boldDisabled(0)]")
				+" [style.boldResPhysical("+Util.capitaliseSentence(Attribute.RESISTANCE_PHYSICAL.getName())+")]");
		
		if (!absClothing.getEffects().isEmpty()) {
			if (!absClothing.isEnchantmentKnown()) {
				tooltipSB.append("</br>[style.colourDisabled(Unidentified effects!)]");
			} else {
				for (ItemEffect e : absClothing.getEffects()) {
					if(e.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE) {
						for(String s : e.getEffectsDescription(owner, owner)) {
							tooltipSB.append("</br>"+ s);
						}
					}
				}
				for(Entry<Attribute, Integer> entry : absClothing.getAttributeModifiers().entrySet()) {
					tooltipSB.append("</br>"+ 
							(entry.getValue()<0
									?"[style.boldBad("+entry.getValue()+")] "
									:"[style.boldGood(+"+entry.getValue()+")] ")
							+ "<b style='color:"+entry.getKey().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getName())+"</b>");
				}
			}
			
		} else {
			tooltipSB.append("</br>[style.colourDisabled(No bonuses)]");
		}
		
		tooltipSB.append("</div>");
		
		// Picture:
		tooltipSB.append("<div class='container-half-width' style='width:calc(33.3% - 16px);'>"
						+ (owner!=null && owner.getClothingCurrentlyEquipped().contains(absClothing)?absClothing.getSVGEquippedString(owner):absClothing.getSVGString())
					+ "</div>");

		tooltipSB.append("</div>");

		tooltipSB.append("<div class='container-full-width' style='padding:8px; height:106px;'>"
						+ absClothing.getTypeDescription()
					+ "</div>");
		
		tooltipSB.append("<div class='container-full-width titular'>");
		if (absClothing.getExtraDescriptions(equippedToCharacter).isEmpty()) {
			tooltipSB.append("<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No Status</span>");
		} else {
			tooltipSB.append("<b>Status</b>");
			for (String s : absClothing.getExtraDescriptions(equippedToCharacter)) {
				tooltipSB.append("</br>" + s);
			}
		}
		tooltipSB.append("</div>");

		
		// Value:

		if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			if (owner.isPlayer()) {
				if (InventoryDialogue.getInventoryNPC().willBuy(absClothing)) {
					tooltipSB.append("<div class='container-full-width titular'>"
										+ "Value: "+(absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : UtilText.formatAsMoney("?", "b"))
										+" | "
										+ InventoryDialogue.getInventoryNPC().getName("The") + " offers " + UtilText.formatAsMoney(absClothing.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier()))
									+ "</div>");
				} else {
					tooltipSB.append("<div class='container-full-width titular'>"
										+ "Value: "+(absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : UtilText.formatAsMoney("?", "b"))
										+" | "
										+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>"
									+ "</div>");
				}
			} else {
				if (InventoryDialogue.isBuyback()) {
					tooltipSB.append("<div class='container-full-width titular'>"
											+ "Value: "+(absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : UtilText.formatAsMoney("?", "b"))
											+" | "
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney( + getBuybackPriceFor(absClothing))
									+ "</div>");
				} else {
					tooltipSB.append("<div class='container-full-width titular'>"
											+ "Value: "+(absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : UtilText.formatAsMoney("?", "b"))
											+" | "
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(absClothing.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier()))
									+ "</div>");
				}
			}
		} else {
			tooltipSB.append("<div class='container-full-width titular'>Value: "+ (absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : UtilText.formatAsMoney("?", "b")) + "</div>");
		}
		
		tooltipSB.append("</body>");

		int specialIncrease = 0;
		if(absClothing.getDisplayName(false).length()>40) {
			specialIncrease = 26;
		}
		Main.mainController.setTooltipSize(360, 392 + (yIncrease * LINE_HEIGHT_TITULAR) + specialIncrease);
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

	}
	
	private int getBuybackPriceFor(AbstractCoreItem item) {
	    for (ShopTransaction s : Main.game.getPlayer().getBuybackStack()) {
	        if (s.getAbstractItemSold() == item) {
	            return s.getPrice();
	        }
	    }
	    throw new IllegalArgumentException("That's not a buyback item");
	}
	
	private static String getTooltipText(GameCharacter character, String playerText, String NPCText) {
		if(character.isPlayer()) {
			return playerText;
		} else {
			return UtilText.parse(character, NPCText);
		}
	}
}
