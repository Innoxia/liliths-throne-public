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
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.markings.Scar;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
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
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.Pattern;
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
	private Tattoo tattoo;
	private AbstractItemType genericItem;
	private AbstractWeapon weapon;
	private AbstractWeaponType genericWeapon;
	private DamageType dt;
	private AbstractClothing clothing;
	private Colour colour;
	private Colour secondaryColour;
	private Colour tertiaryColour;
	private Pattern pattern;
	private AbstractClothingType genericClothing;
	private AbstractClothing dyeClothing;
	private AbstractWeapon dyeWeapon;
	private InventorySlot invSlot;
	private TFModifier enchantmentModifier;
	private TFPotency potency;
	private TFEssence essence;
	private static StringBuilder tooltipSB = new StringBuilder();

	private static final int LINE_HEIGHT = 14;
	private static final int TOOLTIP_WIDTH = 400;
	
	@Override
	public void handleEvent(Event event) {
		if (item != null || (coreItem instanceof AbstractItem)) {
			
			if(coreItem != null) {
				item = (AbstractItem) coreItem;
			}
			
			itemTooltip(item);
			
//			int yIncrease = 0;
//			if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
//				yIncrease += 2;
//			}
//			
//			if(!item.getEffects().isEmpty()) {
//				yIncrease+=1;
//				for(ItemEffect ie : item.getEffects()) {
//					yIncrease += ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size();
//				}
//			}
//			
//			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 256 + (LINE_HEIGHT * yIncrease)); // TODO check shop for all
//
//			tooltipSB.setLength(0);
//			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(item.getDisplayName(true)) + "</div>");
//
//			if(!item.getEffects().isEmpty()) {
//				tooltipSB.append("<div class='subTitle'>");
//				int ieCount=0;
//				for(ItemEffect ie : item.getEffects()) {
//					if(ieCount>0)
//						tooltipSB.append("<br/>");
//					ieCount++;
//					for(int i=0; i<ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size(); i++) {
//						if(i!=0)
//							tooltipSB.append("<br/>");
//						tooltipSB.append(ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).get(i));
//					}
//				}
//				tooltipSB.append("</div>");
//			}
//
//			tooltipSB.append("<div class='subTitle-half'>"
//					+ (item.isConsumedOnUse() ? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Consumed on use</span>" : "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Infinite uses</span>") + "</div>"
//					+ "<div class='subTitle-half'>"
//					+ "<span style='color:" + item.getRarity().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(item.getDisplayRarity())+"</span>"
////					+ (item.isAbleToBeUsedInCombat()
////							? (item.isAbleToBeUsedInSex()
////									?"<span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span> & <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span> use"
////									:"<span style='color:" + Colour.GENERIC_COMBAT.toWebHexString() + ";'>Combat</span> use")
////							: (item.isAbleToBeUsedInSex()
////									?"<span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Sex</span> use"
////									:"Standard use"))
//					+ "</div>"
//
//					+ "<div class='description' style='height:104px'>" + UtilText.parse(item.getDescription()) + "</div>"
//					
//					
//					+ "<div class='subTitle'>" + UtilText.formatAsMoney(item.getValue()) + "</div>");
//
//			if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
//				if (owner.isPlayer()) {
//					if (InventoryDialogue.getInventoryNPC().willBuy(item))
//						tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " offers " + UtilText.formatAsMoney(item.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier())) + "</div>");
//					else
//						tooltipSB.append(
//								"<div class='subTitle'>" + "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>" + "</div>");
//				} else {
//					if (InventoryDialogue.isBuyback()) {
//						tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(getBuybackPriceFor(item)) + "</div>");
//					} else {
//						tooltipSB.append("<div class='subTitle'>" + InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(item.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier())) + "</div>");
//					}
//					
//				}
//			}
//
//			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (weapon != null || (coreItem instanceof AbstractWeapon)) {
			if(coreItem != null)
				weapon = (AbstractWeapon) coreItem;

			weaponTooltip(weapon);

		} else if (clothing != null || (coreItem instanceof AbstractClothing)) {
			if(coreItem != null)
				clothing = (AbstractClothing) coreItem;
			
			clothingTooltip(clothing);

		} else if(tattoo!=null) {
			tattooTooltip(tattoo);
			
		} else if (dyeClothing != null) {

			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 446);

			tooltipSB.setLength(0);
			if(colour!=null) {
				tooltipSB.append("<div class='title' style='color:" + dyeClothing.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(dyeClothing.getName()) + "</div>"
						+ "<div class='subTitle'>" + Util.capitaliseSentence(colour.getName()) + "</div>"
						+ "<div class='picture full' style='position:relative;'>"
						+ dyeClothing.getClothingType().getSVGImage(
								colour, InventoryDialogue.dyePreviewSecondary, InventoryDialogue.dyePreviewTertiary,
								InventoryDialogue.dyePreviewPattern,
								InventoryDialogue.dyePreviewPatternPrimary, InventoryDialogue.dyePreviewPatternSecondary, InventoryDialogue.dyePreviewPatternTertiary)
						+ "</div>");
			
			} else if(secondaryColour!=null) {
				tooltipSB.append("<div class='title' style='color:" + dyeClothing.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(dyeClothing.getName()) + "</div>"
						+ "<div class='subTitle'>" + Util.capitaliseSentence(secondaryColour.getName()) + "</div>"
						+ "<div class='picture full' style='position:relative;'>"
						+ dyeClothing.getClothingType().getSVGImage(
								InventoryDialogue.dyePreviewPrimary, secondaryColour, InventoryDialogue.dyePreviewTertiary,
								InventoryDialogue.dyePreviewPattern,
								InventoryDialogue.dyePreviewPatternPrimary, InventoryDialogue.dyePreviewPatternSecondary, InventoryDialogue.dyePreviewPatternTertiary)
						+ "</div>");
				
			} else if(tertiaryColour!=null) {
				tooltipSB.append("<div class='title' style='color:" + dyeClothing.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(dyeClothing.getName()) + "</div>"
						+ "<div class='subTitle'>" + Util.capitaliseSentence(tertiaryColour.getName()) + "</div>"
						+ "<div class='picture full' style='position:relative;'>"
						+ dyeClothing.getClothingType().getSVGImage(
								InventoryDialogue.dyePreviewPrimary, InventoryDialogue.dyePreviewSecondary, tertiaryColour,
								InventoryDialogue.dyePreviewPattern,
								InventoryDialogue.dyePreviewPatternPrimary, InventoryDialogue.dyePreviewPatternSecondary, InventoryDialogue.dyePreviewPatternTertiary)
						+ "</div>");
				
			} else if(pattern!=null) {
				tooltipSB.append("<div class='title' style='color:" + dyeClothing.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(dyeClothing.getName()) + "</div>"
						
						+ "<div class='subTitle'>" + Util.capitaliseSentence(pattern.getNiceName()) + "</div>"
	
						+ "<div class='picture full' style='position:relative;'>" + dyeClothing.getClothingType().getSVGImage(
								InventoryDialogue.dyePreviewPrimary, InventoryDialogue.dyePreviewSecondary, InventoryDialogue.dyePreviewTertiary,
								pattern.getName(),
								InventoryDialogue.dyePreviewPatternPrimary, InventoryDialogue.dyePreviewPatternSecondary, InventoryDialogue.dyePreviewPatternTertiary)
						+ "</div>");
				
			}
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (dyeWeapon != null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 446);

			tooltipSB.setLength(0);
			
			if(colour!=null) {
				tooltipSB.append("<div class='title' style='color:" + dyeWeapon.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(dyeWeapon.getName()) + "</div>"
						+ "<div class='subTitle'>" + Util.capitaliseSentence(colour.getName()) + "</div>"
						+ "<div class='picture full' style='position:relative;'>" + dyeWeapon.getWeaponType().getSVGImage(dyeWeapon.getDamageType(), colour, InventoryDialogue.dyePreviewSecondary) + "</div>");
			
			} else if(secondaryColour!=null) {
				tooltipSB.append("<div class='title' style='color:" + dyeWeapon.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(dyeWeapon.getName()) + "</div>"
						+ "<div class='subTitle'>" + Util.capitaliseSentence(secondaryColour.getName()) + "</div>"
						+ "<div class='picture full' style='position:relative;'>" + dyeWeapon.getWeaponType().getSVGImage(dyeWeapon.getDamageType(), InventoryDialogue.dyePreviewPrimary, secondaryColour) + "</div>");
				
			}
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (genericItem != null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 416);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title'>" + Util.capitaliseSentence(genericItem.getName(true)) + "</div>"

					+ "<div class='picture full' style='position:relative;'>" + genericItem.getSVGString() + "</div>");
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
			
		} else if (genericClothing != null) {

			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 446);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title' style='color:" + genericClothing.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(genericClothing.getName()) + "</div>"
					
					+ "<div class='subTitle'>" + Util.capitaliseSentence(colour.getName()) + "</div>"

					+ "<div class='picture full' style='position:relative;'>" + genericClothing.getSVGImage(colour,
							genericClothing.getAvailableSecondaryColours().isEmpty()?null:genericClothing.getAvailableSecondaryColours().get(0),
							genericClothing.getAvailableTertiaryColours().isEmpty()?null:genericClothing.getAvailableTertiaryColours().get(0),
							null, null, null, null) + "</div>");
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (genericWeapon != null) {

			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 446);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title' style='color:" + genericWeapon.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(genericWeapon.getName()) + "</div>"

					+ "<div class='subTitle'>" + Util.capitaliseSentence(dt.getName()) + "</div>"

					+ "<div class='picture full'>" + genericWeapon.getSVGImage(dt, null, null) + "</div>");

			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (invSlot != null) {
			if (invSlot == InventorySlot.WEAPON_MAIN) {

				if (equippedToCharacter != null) {
					if (equippedToCharacter.getMainWeapon() == null) {
						Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
						Main.mainController.setTooltipContent("<div class='title'>Primary Weapon</div>");

					} else {
						weaponTooltip(equippedToCharacter.getMainWeapon());
					}
				} else {
					Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
					Main.mainController.setTooltipContent("<div class='title'>Primary Weapon</div>");
				}

			} else if (invSlot == InventorySlot.WEAPON_OFFHAND) {

				if (equippedToCharacter != null) {
					if (equippedToCharacter.getOffhandWeapon() == null) {
						Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
						Main.mainController.setTooltipContent("<div class='title'>Secondary Weapon</div>");

					} else {
						weaponTooltip(equippedToCharacter.getOffhandWeapon());
					}
				} else {
					Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
					Main.mainController.setTooltipContent("<div class='title'>Secondary Weapon</div>");
				}

			} else {
				if (equippedToCharacter != null) {
					
					boolean renderingTattoos = false;
					
					if((equippedToCharacter.isPlayer() && RenderingEngine.ENGINE.isRenderingTattoosLeft())
							|| (!equippedToCharacter.isPlayer() && RenderingEngine.ENGINE.isRenderingTattoosRight())) {
						renderingTattoos = true;
					}
						
					if ((!renderingTattoos && equippedToCharacter.getClothingInSlot(invSlot)==null)
							|| (renderingTattoos && equippedToCharacter.getTattooInSlot(invSlot)==null)) {
						
						List<String> clothingBlockingThisSlot = new ArrayList<>();
						for (AbstractClothing c : equippedToCharacter.getClothingCurrentlyEquipped()) {
							if (c.getClothingType().getIncompatibleSlots().contains(invSlot)) {
								clothingBlockingThisSlot.add(c.getName());
							}
						}
						
						if (!renderingTattoos && !clothingBlockingThisSlot.isEmpty()) {
							setBlockedTooltipContent(UtilText.parse(equippedToCharacter, "This slot is currently <b style='color:" + Colour.SEALED.toWebHexString() + ";'>blocked</b> by [npc.namePos] ")
									+ Util.stringsToStringList(clothingBlockingThisSlot, false) + ".");
							
						} else if (!renderingTattoos && invSlot.slotBlockedByRace(equippedToCharacter) != null) {
							setBlockedTooltipContent(invSlot.getCannotBeWornDescription(equippedToCharacter));
							
						} else {
							
							boolean piercingBlocked=false;
							boolean bypassesPiercing = !equippedToCharacter.getBody().getBodyMaterial().isRequiresPiercing();
							
							switch(invSlot){
								case PIERCING_VAGINA:
									if(equippedToCharacter.getVaginaType()==VaginaType.NONE) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"You don't have a vagina.",
													(equippedToCharacter.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())
														?"[npc.Name] doesn't have a vagina."
														:"You don't know if [npc.name] has a vagina.")));
										piercingBlocked=true;
										
									} else if(!bypassesPiercing && !equippedToCharacter.isPiercedVagina()) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"Your vagina has not been pierced.",
												(equippedToCharacter.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())
														?"[npc.NamePos] vagina has not been pierced."
														:"You don't know if [npc.name] has a vagina.")));
										piercingBlocked=true;
									}
									break;
									
								case PIERCING_EAR:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedEar()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your ears have not been pierced.",
													"[npc.NamePos] ears have not been pierced."));
											piercingBlocked=true;
										}
									}
									break;
									
								case PIERCING_LIP:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedLip()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your lips have not been pierced.",
													"[npc.NamePos] lips have not been pierced."));
											piercingBlocked=true;
										}
									}
									break;
									
								case PIERCING_NIPPLE:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedNipple()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your nipples have not been pierced.",
													(equippedToCharacter.isAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer())
															?"[npc.NamePos] nipples have not been pierced."
															:"You don't know if [npc.namePos] nipples have been pierced.")));
											piercingBlocked=true;
										}
									}
									break;
									
								case PIERCING_NOSE:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedNose()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your nose has not been pierced.",
													"[npc.NamePos] nose has not been pierced."));
											piercingBlocked=true;
										}
									}
									break;
									
								case PIERCING_PENIS:
									if(equippedToCharacter.getPenisType()==PenisType.NONE) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"You don't have a penis.",
												(equippedToCharacter.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())
														?"[npc.Name] doesn't have a penis."
														:"You don't know if [npc.name] has a penis.")));
										piercingBlocked=true;
										
									} else if(!bypassesPiercing && !equippedToCharacter.isPiercedPenis()) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"Your penis has not been pierced.",
												(equippedToCharacter.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())
														?"[npc.NamePos] penis has not been pierced."
														:"You don't know if [npc.name] has a penis.")));
										piercingBlocked=true;
									}
									break;
									
								case PIERCING_STOMACH:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedNavel()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your navel has not been pierced.",
													"[npc.NamePos] navel has not been pierced."));
											piercingBlocked=true;
										}
									}
									break;
									
								case PIERCING_TONGUE:
									if(!bypassesPiercing) {
										if(!equippedToCharacter.isPiercedTongue()) {
											setBlockedTooltipContent(getTooltipText(equippedToCharacter,
													"Your tongue has not been pierced.",
													"[npc.NamePos] tongue has not been pierced."));
											piercingBlocked=true;
										}
									}
									break;
									
								case HORNS:
									if(equippedToCharacter.getHornType()==HornType.NONE) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"You don't have any horns.",
												"[npc.Name] doesn't have any horns."));
										piercingBlocked=true;
									}
									break;
									
								case PENIS:
									if(!equippedToCharacter.hasPenisIgnoreDildo()) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"You don't have a penis.",
												"[npc.Name] doesn't have a penis."));
										piercingBlocked=true;
									}
									break;
									
								case TAIL:
									if(equippedToCharacter.getTailType()==TailType.NONE) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"You don't have a tail.",
												"[npc.Name] doesn't have a tail."));
										piercingBlocked=true;
									}
									break;
									
								case VAGINA:
									if(!equippedToCharacter.hasVagina()) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"You don't have a vagina.",
												"[npc.Name] doesn't have a vagina."));
										piercingBlocked=true;
									}
									break;
									
								case WINGS:
									if(equippedToCharacter.getWingType()==WingType.NONE) {
										setBlockedTooltipContent(getTooltipText(equippedToCharacter,
												"You don't have any wings.",
												"[npc.Name] doesn't have any wings."));
										piercingBlocked=true;
									}
									break;
									
								default:
									break;
							}
							
							if(!piercingBlocked){
								if(renderingTattoos) {
									scarTooltip(equippedToCharacter.getScarInSlot(invSlot));
									
								} else {
									Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
									Main.mainController.setTooltipContent(UtilText.parse("<div class='title'>" + Util.capitaliseSentence(invSlot.getName()) + "</div>"));
								}
							}
						}

					} else {
						if(renderingTattoos) {
							tattooTooltip(equippedToCharacter.getTattooInSlot(invSlot));
						} else {
							clothingTooltip(equippedToCharacter.getClothingInSlot(invSlot));
						}
					}
					
				} else {
					Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
					Main.mainController.setTooltipContent(UtilText.parse("<div class='title'>" + Util.capitaliseSentence(invSlot.getName()) + "</div>"));
				}
			}
			
		} else if (enchantmentModifier != null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 152);
			Main.mainController.setTooltipContent(UtilText.parse(
					"<div class='title' style='color:"+enchantmentModifier.getRarity().getColour().toWebHexString()+";'>"
							+ Util.capitaliseSentence(enchantmentModifier.getName())
					+ "</div>"
					+ "<div class='description' style='height:48px'>"
					+ UtilText.parse(enchantmentModifier.getDescription())
					+ "</div>"
					+ "<div class='subTitle'>"
					+(EnchantmentDialogue.getIngredient() instanceof Tattoo
							? UtilText.formatAsMoney(enchantmentModifier.getValue()*EnchantingUtils.FLAME_COST_MODIFER, "b")+" cost"
							: UtilText.formatAsEssences(enchantmentModifier.getValue(), "b", false)+" essence cost")
					+ "</div>"));
		
		} else if(potency!=null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
			Main.mainController.setTooltipContent(UtilText.parse("<div class='title'>Set potency to <b style='color:"+potency.getColour().toWebHexString()+";'>" + Util.capitaliseSentence(potency.getName()) + "</b></div>"));
			
		} else if (essence != null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
			Main.mainController.setTooltipContent(UtilText.parse("<div class='title'><b style='color:"+essence.getColour().toWebHexString()+";'>" + Util.capitaliseSentence(essence.getName()) + "</b> essence</div>"));
		
		}  else {
			return;
		}

		TooltipUpdateThread.updateToolTip(-1,-1);
		// Main.mainController.getTooltip().show(Main.primaryStage);
	}

	private void setBlockedTooltipContent(String description){
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 164);
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
	
	public InventoryTooltipEventListener setTattoo(InventorySlot invSlot, Tattoo tattoo, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.invSlot = invSlot;
		this.tattoo = tattoo;
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

	public InventoryTooltipEventListener setDyeWeaponPrimary(AbstractWeapon dyeWeapon, Colour colour) {
		resetVariables();
		this.dyeWeapon = dyeWeapon;
		this.colour = colour;
		return this;
	}
	
	public InventoryTooltipEventListener setDyeWeaponSecondary(AbstractWeapon dyeWeapon, Colour secondaryColour) {
		resetVariables();
		this.dyeWeapon = dyeWeapon;
		this.secondaryColour = secondaryColour;
		return this;
	}
	
	public InventoryTooltipEventListener setDyeClothingPattern(AbstractClothing dyeClothing, Pattern pattern) {
		resetVariables();
		this.dyeClothing = dyeClothing;
		this.pattern = pattern;
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
		tattoo = null;
		genericItem = null;
		weapon = null;
		genericWeapon = null;
		dt = null;
		clothing = null;
		colour = null;
		dyeClothing = null;
		dyeWeapon = null;
		secondaryColour = null;
		tertiaryColour = null;
		pattern = null;
		genericClothing = null;
		invSlot = null;
		enchantmentModifier = null;
		potency = null;
		essence = null;
	}
	
	private void itemTooltip(AbstractItem absItem) {
		
		int yIncrease = 0;
		int listIncrease = 0;
		
		if(!absItem.getEffects().isEmpty()) {
			listIncrease+=1;
			for(ItemEffect ie : absItem.getEffects()) {
				listIncrease += ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size();
			}
		}
		
		yIncrease += Math.max(0, listIncrease-3);
		
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitaliseSentence(absItem.getDisplayName(true)) + "</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-full-width titular'>"
				+ (absItem.isConsumedOnUse()
						? "<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Consumed on use</span>"
						: "<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Infinite uses</span>")
				+ "</div>"
//				+ "<div class='container-half-width titular'>"
//					+ "<span style='color:" + absItem.getRarity().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(absItem.getDisplayRarity())+"</span>"
//				+ "</div>"
					);

		tooltipSB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width titular' style='width:calc(66.6% - 16px);'>"
				+ "<span style='color:" + absItem.getRarity().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(absItem.getDisplayRarity())+"</span>"
				);
		
		for(ItemEffect ie : absItem.getEffects()) {
			for(int i=0; i<ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size(); i++) {
				tooltipSB.append("</br>"+ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).get(i));
			}
		}
		
		tooltipSB.append("</div>");
		
		// Picture:
		tooltipSB.append("<div class='item-image'>"
								+ "<div class='item-image-content'>"
									+(absItem.getSVGString())
								+ "</div>"
							+ "</div>");

		tooltipSB.append("</div>");

		tooltipSB.append("<div class='container-full-width' style='padding:8px; height:106px;'>"
						+ absItem.getDescription()
					+ "</div>");

		
		// Value:

		if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			if (owner.isPlayer()) {
				if (InventoryDialogue.getInventoryNPC().willBuy(absItem)) {
					tooltipSB.append("<div class='container-full-width titular'>"
										+ "Value: "+UtilText.formatAsMoney(absItem.getValue())
										+" | "
										+ InventoryDialogue.getInventoryNPC().getName("The") + " offers: " + UtilText.formatAsMoney(absItem.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier()))
									+ "</div>");
				} else {
					tooltipSB.append("<div class='container-full-width titular'>"
										+ "Value: "+UtilText.formatAsMoney(absItem.getValue())
										+" | "
										+ "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>"
									+ "</div>");
				}
			} else {
				if (InventoryDialogue.isBuyback()) {
					tooltipSB.append("<div class='container-full-width titular'>"
											+ "Value: "+UtilText.formatAsMoney(absItem.getValue())
											+" | "
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney( + getBuybackPriceFor(absItem))
									+ "</div>");
				} else {
					tooltipSB.append("<div class='container-full-width titular'>"
											+ "Value: "+UtilText.formatAsMoney(absItem.getValue())
											+" | "
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(absItem.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier()))
									+ "</div>");
				}
			}
		} else {
			tooltipSB.append("<div class='container-full-width titular'>" + "Value: "+UtilText.formatAsMoney(absItem.getValue()) + "</div>");
		}

		tooltipSB.append("</body>");

		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 364 + (yIncrease * LINE_HEIGHT));
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
		
	}

	private void weaponTooltip(AbstractWeapon absWep) {
		
		int yIncrease = 0;
		int listIncrease = 2 + absWep.getAttributeModifiers().size();
		listIncrease += absWep.getSpells().size();
		yIncrease += Math.max(0, listIncrease-3);
		
		
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitaliseSentence(absWep.getDisplayName(true)) + "</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-half-width titular' style='color:"+absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(absWep.getDamageType().getName()) + " damage</div>");
		tooltipSB.append("<div class='container-half-width titular'>"
							+ (absWep.getWeaponType().getClothingSet() == null
								? "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Not part of a set</span>"
								: "<span style='color:" + Colour.RARITY_EPIC.toWebHexString() + ";'>"+absWep.getWeaponType().getClothingSet().getName() + " set</span>")
						+ "</div>");
		
		
		// Attribute modifiers:
		tooltipSB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width titular' style='width:calc(66.6% - 16px);'>"
				+ "<span style='color:" + absWep.getRarity().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(absWep.getDisplayRarity())+"</span></br>"
				+ (absWep.getWeaponType().isTwoHanded()? "Two-handed" : "One-handed")+"</br>"
				);
		if (equippedToCharacter != null) {
			tooltipSB.append("<b>"
								+ Attack.getMinimumDamage(owner, null, Attack.MAIN, absWep)
								+ " - "
								+ Attack.getMaximumDamage(owner, null, Attack.MAIN, absWep)
							+ "</b>"
							+ " <b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</b>");
		} else {
			tooltipSB.append("<b>"
								+ Attack.getMinimumDamage(Main.game.getPlayer(), null, Attack.MAIN, absWep)
								+ " - "
								+ Attack.getMaximumDamage(Main.game.getPlayer(), null, Attack.MAIN, absWep)
							+ "</b>"
							+ " <b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</b>");
		}

//		if (absWep.getEffects().size() != 0) { TODO enchanting effects
//			for (ItemEffect e : absWep.getEffects()) {
//				for(String s : e.getEffectsDescription(owner, owner)) {
//					tooltipSB.append("<br/>"+ s);
//				}
//			}
			for(Entry<Attribute, Integer> entry : absWep.getAttributeModifiers().entrySet()) {
				tooltipSB.append("<br/>"+ 
						(entry.getValue()<0
								?"[style.boldBad("+entry.getValue()+")] "
								:"[style.boldGood(+"+entry.getValue()+")] ")
						+ "<b style='color:"+entry.getKey().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getName())+"</b>");
			}
//		} else {
//			tooltipSB.append("<br/>[style.colourDisabled(No bonuses)]");
//		}
		
			for(Spell s : absWep.getSpells()) {
				tooltipSB.append("<br/><b style='color:"+Colour.DAMAGE_TYPE_SPELL.toWebHexString()+";'>Grants Spell</b><b>:</b> <b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(s.getName())+"</b>");
			}
			
		tooltipSB.append("</div>");
		
		// Picture:
		tooltipSB.append("<div class='item-image'>"
							+ "<div class='item-image-content'>"
								+ (owner!=null && (absWep.equals(owner.getMainWeapon()) || absWep.equals(owner.getOffhandWeapon()))?absWep.getSVGEquippedString(owner):absWep.getSVGString())
							+ "</div>"
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

		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 364 + (yIncrease * LINE_HEIGHT));
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
		
	}

	private void clothingTooltip(AbstractClothing absClothing) {
		int yIncrease = 0;
				
		int listIncrease = 1 + absClothing.getAttributeModifiers().size();

		yIncrease += absClothing.getExtraDescriptions(equippedToCharacter).size();
		
		for(ItemEffect ie : absClothing.getEffects()) {
			if(ie.getPrimaryModifier()==TFModifier.CLOTHING_ENSLAVEMENT
					|| ie.getPrimaryModifier()==TFModifier.CLOTHING_SEALING) {
				listIncrease+=1;
			} else if(ie.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE && ie.getPrimaryModifier()!=TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
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
							+ (absClothing.getClothingType().getClothingSet() == null
								? "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>Not part of a set</span>"
								: "<span style='color:" + Colour.RARITY_EPIC.toWebHexString() + ";'>"+absClothing.getClothingType().getClothingSet().getName() + " set</span>")
						+ "</div>");
		
		// Attribute modifiers:
		tooltipSB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width titular' style='width:calc(66.6% - 16px);'>");
		int res = absClothing.getClothingType().getPhysicalResistance();
		tooltipSB.append(
				"<span style='color:" + absClothing.getRarity().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(absClothing.getDisplayRarity())+"</span></br>"
				+ (res>0
					?"[style.boldGood(+"+absClothing.getClothingType().getPhysicalResistance()+")]"
					:"[style.boldDisabled(0)]")
				+" [style.boldResPhysical("+Util.capitaliseSentence(Attribute.RESISTANCE_PHYSICAL.getName())+")]");
		
		if (!absClothing.getEffects().isEmpty()) {
			if (!absClothing.isEnchantmentKnown()) {
				tooltipSB.append("<br/>[style.colourDisabled(Unidentified effects!)]");
			} else {
				for (ItemEffect e : absClothing.getEffects()) {
					if(e.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE && e.getPrimaryModifier()!=TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
						for(String s : e.getEffectsDescription(owner, owner)) {
							tooltipSB.append("<br/>"+ s);
						}
					}
				}
				for(Entry<Attribute, Integer> entry : absClothing.getAttributeModifiers().entrySet()) {
					tooltipSB.append("<br/>"+ 
							(entry.getValue()<0
									?"[style.boldBad("+entry.getValue()+")] "
									:"[style.boldGood(+"+entry.getValue()+")] ")
							+ "<b style='color:"+entry.getKey().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getName())+"</b>");
				}
			}
			
		} else {
			tooltipSB.append("<br/>[style.colourDisabled(No bonuses)]");
		}
		
		tooltipSB.append("</div>");
		
		// Picture:
		tooltipSB.append("<div class='item-image'>"
							+ "<div class='item-image-content'>"
								+ (owner!=null && owner.getClothingCurrentlyEquipped().contains(absClothing)?absClothing.getSVGEquippedString(owner):absClothing.getSVGString())
							+ "</div>"
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
				tooltipSB.append("<br/>" + s);
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
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 400 + (yIncrease * LINE_HEIGHT) + specialIncrease);
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

	}
	
	
	private void scarTooltip(Scar scar) {
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>No tattoo</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-half-width titular'>" + Util.capitaliseSentence(invSlot.getTattooSlotName()) + "</div>");
		tooltipSB.append("<div class='container-half-width titular'>"
							+ (scar==null
									? "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No scars</span>"
									: "<span style='color:" + Colour.SCAR.toWebHexString() + ";'>"+Util.capitaliseSentence(owner.getScarInSlot(invSlot).getName())+"</span>")
						+ "</div>");
		
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 88);
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
	}
	
	private void tattooTooltip(Tattoo tattoo) {
		int yIncrease = 0;
		
		if (tattoo.getWriting()!=null && !tattoo.getWriting().getText().isEmpty()) {
			yIncrease++;
		}
		if (tattoo.getCounter()!=null && tattoo.getCounter().getType()!=TattooCounterType.NONE) {
			yIncrease++;
		}
		int lSize=0;
		for (ItemEffect e : tattoo.getEffects()) {
			if(e.getPrimaryModifier()==TFModifier.CLOTHING_ATTRIBUTE
					|| e.getPrimaryModifier()==TFModifier.CLOTHING_MAJOR_ATTRIBUTE
					|| e.getPrimaryModifier()==TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| e.getPrimaryModifier()==TFModifier.TF_MOD_FETISH_BODY_PART) {
				lSize++;
			} else {
				lSize+=2;
			}
		}
		lSize-=4;
		if(lSize<0) {
			lSize=0;
		}
		
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitaliseSentence(tattoo.getDisplayName(true)) + "</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-half-width titular'>" + (invSlot.getTattooSlotName()==null?"[style.colourDisabled(Cannot be tattooed)]":Util.capitaliseSentence(invSlot.getTattooSlotName())) + "</div>");
		tooltipSB.append("<div class='container-half-width titular'>"
							+ (owner.getScarInSlot(invSlot)==null
									? "<span style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>No scars</span>"
									: "<span style='color:" + Colour.SCAR.toWebHexString() + ";'>"+Util.capitaliseSentence(owner.getScarInSlot(invSlot).getName())+"</span>")
						+ "</div>");
		
		// Attribute modifiers:
		tooltipSB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width titular' style='width:calc(66.6% - 16px);'>");
		
		if (!tattoo.getEffects().isEmpty()) {
			int i=0;
			for (ItemEffect e : tattoo.getEffects()) {
				if(e.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE && e.getPrimaryModifier()!=TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
					for(String s : e.getEffectsDescription(owner, owner)) {
						tooltipSB.append((i>0?"<br/>"+s:s));
					}
					i++;
				}
			}
			for(Entry<Attribute, Integer> entry : tattoo.getAttributeModifiers().entrySet()) {
				tooltipSB.append((i>0?"<br/>":"")
						+ (entry.getValue()<0
								?"[style.boldBad("+entry.getValue()+")] "
								:"[style.boldGood(+"+entry.getValue()+")] ")
						+ "<b style='color:"+entry.getKey().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getName())+"</b>");
				i++;
			}
			
		} else {
			tooltipSB.append("[style.colourDisabled(No bonuses)]");
		}
		
		tooltipSB.append("</div>");
		
		// Picture:
		tooltipSB.append("<div class='container-half-width' style='width:calc(33.3% - 16px);'>"
						+ tattoo.getSVGImage(equippedToCharacter)
					+ "</div>");

		tooltipSB.append("</div>");

		tooltipSB.append("<div class='container-full-width' style='padding:8px; height:106px;'>"
						+ tattoo.getType().getDescription()
						+"</div>");
		
			if (tattoo.getWriting()!=null && !tattoo.getWriting().getText().isEmpty()) {
					tooltipSB.append("<div class='container-full-width' style='padding:8px; height:54px; text-align:center;'>");
					if(tattoo.getWriting().getStyles().isEmpty()) {
						tooltipSB.append("Normal,");
					} else {
						int i=0;
						for(TattooWritingStyle style : tattoo.getWriting().getStyles()) {
							tooltipSB.append(i==0?Util.capitaliseSentence(style.getName()):", "+style.getName());
							i++;
						}
					}
					tooltipSB.append(" "+tattoo.getWriting().getColour().getName()+" text reads:<br/>");
					tooltipSB.append(tattoo.getFormattedWritingOutput()
							+ "</div>");
			} else {
				tooltipSB.append(
						"<div class='container-full-width' style='padding:8px; height:28px; text-align:center;'>"
							+"[style.colourDisabled(This tattoo doesn't have any writing.)]"
						+ "</div>");
			}
			
			if (tattoo.getCounter()!=null && tattoo.getCounter().getType()!=TattooCounterType.NONE) {
				tooltipSB.append("<div class='container-full-width' style='padding:8px; height:54px; text-align:center;'>"
									+ "An enchanted, "+tattoo.getCounter().getColour().getName()+" "+tattoo.getCounter().getType().getName()+" counter reads:<br/>"
										+ "<span style='color:"+tattoo.getCounter().getColour().toWebHexString()+";'>"
												+tattoo.getFormattedCounterOutput(equippedToCharacter)
										+"</span>"
								+ "</div>");
			} else {
				tooltipSB.append(
						"<div class='container-full-width' style='padding:8px; height:28px; text-align:center;'>"
							+"[style.colourDisabled(This tattoo doesn't have a counter.)]"
						+ "</div>");
			}
			
		tooltipSB.append("</div>");
		
		tooltipSB.append("</body>");

		int specialIncrease = 0;
		if(tattoo.getDisplayName(false).length()>40) {
			specialIncrease = 26;
		}
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 396 + ((lSize+yIncrease) * LINE_HEIGHT) + yIncrease*8 + specialIncrease);
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
