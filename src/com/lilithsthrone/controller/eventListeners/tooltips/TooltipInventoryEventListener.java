package com.lilithsthrone.controller.eventListeners.tooltips;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.Scar;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounter;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.SizedStack;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Shows the tooltip at the given element's position.
 * 
 * @since 0.1.0
 * @version 0.3.9
 * @author Innoxia
 */
public class TooltipInventoryEventListener implements EventListener {
	private GameCharacter owner;
	private GameCharacter equippedToCharacter;
	
	private AbstractCoreItem coreItem;
	private InventorySlot invSlot;
	
	private AbstractItem item;
	private AbstractItemType genericItem;
	
	private AbstractWeapon weapon;
	private AbstractWeaponType genericWeapon;
	private DamageType dt;
	private AbstractWeapon dyeWeapon;
	private DamageType damageType;
	
	private AbstractClothing clothing;
	private AbstractClothingType genericClothing;
	private AbstractClothing dyeClothing;

	private Tattoo tattoo;
	private AbstractTattooType genericTattoo;
	
	private int colourIndex;
	
	private Colour patternColour;
	private Colour colour;
	
	private TFModifier enchantmentModifier;
	private TFPotency potency;
	
	private static StringBuilder tooltipSB = new StringBuilder();

	private static final int LINE_HEIGHT = 17;
	private static final int TOOLTIP_WIDTH = 400;
	
	@Override
	public void handleEvent(Event event) {
		
		if (item != null || (coreItem instanceof AbstractItem)) {
			if(coreItem != null) {
				item = (AbstractItem) coreItem;
			}
			itemTooltip(item);
			
		} else if (weapon != null || (coreItem instanceof AbstractWeapon)) {
			if(coreItem != null) {
				weapon = (AbstractWeapon) coreItem;
			}
			weaponTooltip(weapon);

		} else if(clothing != null || (coreItem instanceof AbstractClothing)) {
			if(coreItem != null) {
				clothing = (AbstractClothing) coreItem;
			}
			clothingTooltip(clothing);

		} else if(tattoo!=null) {
			tattooTooltip(tattoo);
			
		} else if(dyeClothing != null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 480);

			Colour subtitleColour = dyeClothing.isEnchantmentKnown()?dyeClothing.getRarity().getColour():PresetColour.RARITY_UNKNOWN;

			InventorySlot slotEquippedTo = dyeClothing.getSlotEquippedTo();
			if(slotEquippedTo==null) {
				slotEquippedTo = dyeClothing.getClothingType().getEquipSlots().get(0);
			}
			
			tooltipSB.setLength(0);
			if(colour!=null) {
				List<Colour> dyeColours = new ArrayList<>(InventoryDialogue.dyePreviews);
				dyeColours.remove(colourIndex);
				dyeColours.add(colourIndex, colour);
				tooltipSB.append("<div class='title' style='color:" + subtitleColour.toWebHexString() + ";'>" + Util.capitaliseSentence(dyeClothing.getName()) + "</div>"
						+ "<div class='subTitle'>"
							+ Util.capitaliseSentence(colour.getName())
							+" ("
							+ (dyeClothing.getClothingType().getColourReplacement(colourIndex).getDefaultColours().contains(colour)
								?"Standard Colour"
								:"Non-standard Colour")
							+")"
						+ "</div>"
						+ "<div class='picture full' style='position:relative; margin:8px; padding:0; width:calc("+TOOLTIP_WIDTH+"px - 24px); height:calc("+TOOLTIP_WIDTH+"px - 24px);'>"
						+ dyeClothing.getClothingType().getSVGImage(
								slotEquippedTo,
								dyeColours,
								InventoryDialogue.dyePreviewPattern,
								InventoryDialogue.dyePreviewPatterns,
								InventoryDialogue.getDyePreviewStickersAsStrings())
						+ "</div>");
			
			} else if(patternColour!=null) {
				List<Colour> dyeColours = new ArrayList<>(InventoryDialogue.dyePreviewPatterns);
				dyeColours.remove(colourIndex);
				dyeColours.add(colourIndex, patternColour);
				tooltipSB.append("<div class='title' style='color:" + subtitleColour.toWebHexString() + ";'>" + Util.capitaliseSentence(dyeClothing.getName()) + "</div>"
						+ "<div class='subTitle'>" + Util.capitaliseSentence(Pattern.getPattern(InventoryDialogue.dyePreviewPattern).getNiceName()) + "</div>"
						+ "<div class='picture full' style='position:relative; margin:8px; padding:0; width:calc("+TOOLTIP_WIDTH+"px - 24px); height:calc("+TOOLTIP_WIDTH+"px - 24px);'>"
						+ dyeClothing.getClothingType().getSVGImage(
								slotEquippedTo,
								InventoryDialogue.dyePreviews,
								InventoryDialogue.dyePreviewPattern,
								dyeColours,
								InventoryDialogue.getDyePreviewStickersAsStrings())
						+ "</div>");
				
			}
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if(dyeWeapon != null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 480);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title' style='color:" + dyeWeapon.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(dyeWeapon.getName()) + "</div>");
			
			if(colour!=null) {
				List<Colour> dyeColours = new ArrayList<>(InventoryDialogue.dyePreviews);
				dyeColours.remove(colourIndex);
				dyeColours.add(colourIndex, colour);
				tooltipSB.append("<div class='subTitle'>"
							+ Util.capitaliseSentence(colour.getName()) 
							+" ("
							+ (dyeWeapon.getWeaponType().getColourReplacement(true, colourIndex).getDefaultColours().contains(colour)
								?"Standard Colour"
								:"Non-standard Colour")
							+")"
						+ "</div>"
						+ "<div class='picture full' style='position:relative; margin:8px; padding:0; width:calc("+TOOLTIP_WIDTH+"px - 24px); height:calc("+TOOLTIP_WIDTH+"px - 24px);'>"
							+ dyeWeapon.getWeaponType().getSVGImage(dyeWeapon.getDamageType(), dyeColours)
						+ "</div>");
			
			} else if(damageType!=null) {
				tooltipSB.append("<div class='subTitle'>" + Util.capitaliseSentence(damageType.getName()) + "</div>"
						+ "<div class='picture full' style='position:relative; margin:8px; padding:0; width:calc("+TOOLTIP_WIDTH+"px - 24px); height:calc("+TOOLTIP_WIDTH+"px - 24px);'>"
							+ dyeWeapon.getWeaponType().getSVGImage(damageType, InventoryDialogue.dyePreviews)
						+ "</div>");
			}
			
			Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));

		} else if (genericItem != null) {
			itemTooltip(Main.game.getItemGen().generateItem(genericItem));
			
		} else if (genericClothing != null) {
			if(colour!=null) {
				clothingTooltip(Main.game.getItemGen().generateClothing(genericClothing, colour, false));
			} else {
				clothingTooltip(Main.game.getItemGen().generateClothing(genericClothing, false));
			}

		} else if (genericTattoo != null) {
			tattooTooltip(new Tattoo(
					genericTattoo,
					false,
					new TattooWriting(
							"The quick brown fox jumps over the lazy dog.",
							genericTattoo.getAvailablePrimaryColours().get(0),
							false),
					new TattooCounter(
							TattooCounterType.UNIQUE_SEX_PARTNERS,
							TattooCountType.NUMBERS,
							genericTattoo.getAvailablePrimaryColours().get(0),
							false)));

		} else if (genericWeapon != null) {
			weaponTooltip(Main.game.getItemGen().generateWeapon(genericWeapon, dt));

		} else if (invSlot != null) {
			if (invSlot == InventorySlot.WEAPON_MAIN_1) {
				if (equippedToCharacter != null) {
					
					if (equippedToCharacter.getMainWeapon(0) == null) {
						setUnarmedWeaponSlotTooltip(InventorySlot.WEAPON_MAIN_1, "Primary Weapon");
						
					} else {
						weaponTooltip(equippedToCharacter.getMainWeapon(0));
					}
				} else {
					Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
					Main.mainController.setTooltipContent("<div class='title'>Primary Weapon</div>");
				}

			} else if (invSlot == InventorySlot.WEAPON_MAIN_2) {
				if (equippedToCharacter != null) {
					if (equippedToCharacter.getMainWeapon(1) == null) {
						if(equippedToCharacter.getArmRows()<2) {
							setBlockedTooltipContent(getTooltipText(equippedToCharacter,
									"You do not have a second pair of arms with which to hold another primary weapon!",
									"[npc.Name] [npc.does] not have a second pair of arms with which to hold another primary weapon!"));
						} else {
							setUnarmedWeaponSlotTooltip(InventorySlot.WEAPON_MAIN_2, "Primary Weapon (2nd)");
						}
						
					} else {
						weaponTooltip(equippedToCharacter.getMainWeapon(1));
					}
				} else {
					Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
					Main.mainController.setTooltipContent("<div class='title'>Primary Weapon (2nd)</div>");
				}

			} else if (invSlot == InventorySlot.WEAPON_MAIN_3) {
				if (equippedToCharacter != null) {
					if (equippedToCharacter.getMainWeapon(2) == null) {
						if(equippedToCharacter.getArmRows()<3) {
							setBlockedTooltipContent(getTooltipText(equippedToCharacter,
									"You do not have a third pair of arms with which to hold another primary weapon!",
									"[npc.Name] [npc.does] not have a third pair of arms with which to hold another primary weapon!"));
						} else {
							setUnarmedWeaponSlotTooltip(InventorySlot.WEAPON_MAIN_3, "Primary Weapon (3rd)");
						}
						
					} else {
						weaponTooltip(equippedToCharacter.getMainWeapon(2));
					}
				} else {
					Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
					Main.mainController.setTooltipContent("<div class='title'>Primary Weapon (3rd)</div>");
				}

			} else if (invSlot == InventorySlot.WEAPON_OFFHAND_1) {
				if (equippedToCharacter != null) {
					if (equippedToCharacter.getOffhandWeapon(0) == null) {
						AbstractWeapon primary = equippedToCharacter.getMainWeapon(0);
						if(primary!=null && primary.getWeaponType().isTwoHanded()) {
							setBlockedTooltipContent(getTooltipText(equippedToCharacter,
									primary.getWeaponType().isPlural()
										?"As your "+primary.getName()+" require two hands to wield correctly, you are unable to equip a weapon in your off-hand."
										:"As your "+primary.getName()+" requires two hands to wield correctly, you are unable to equip a weapon in your off-hand",
									primary.getWeaponType().isPlural()
										?"As [npc.namePos] "+primary.getName()+" require two hands to wield correctly, [npc.sheIsFull] unable to equip a weapon in [npc.her] off-hand."
										:"As [npc.namePos] "+primary.getName()+" requires two hands to wield correctly, [npc.sheIsFull] unable to equip a weapon in [npc.her] off-hand"));
							
						} else {
							setUnarmedWeaponSlotTooltip(InventorySlot.WEAPON_OFFHAND_1, "Secondary Weapon");
						}

					} else {
						weaponTooltip(equippedToCharacter.getOffhandWeapon(0));
					}
					
				} else {
					Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
					Main.mainController.setTooltipContent("<div class='title'>Secondary Weapon</div>");
				}

			} else if (invSlot == InventorySlot.WEAPON_OFFHAND_2) {
				if (equippedToCharacter != null) {
					if (equippedToCharacter.getOffhandWeapon(1) == null) {
						AbstractWeapon primary = equippedToCharacter.getMainWeapon(1);
						if(primary!=null && primary.getWeaponType().isTwoHanded()) {
							setBlockedTooltipContent(getTooltipText(equippedToCharacter,
									primary.getWeaponType().isPlural()
										?"As your "+primary.getName()+" require two hands to wield correctly, you are unable to equip a weapon in your off-hand."
										:"As your "+primary.getName()+" requires two hands to wield correctly, you are unable to equip a weapon in your off-hand",
									primary.getWeaponType().isPlural()
										?"As [npc.namePos] "+primary.getName()+" require two hands to wield correctly, [npc.sheIsFull] unable to equip a weapon in [npc.her] off-hand."
										:"As [npc.namePos] "+primary.getName()+" requires two hands to wield correctly, [npc.sheIsFull] unable to equip a weapon in [npc.her] off-hand"));
							
						} else if(equippedToCharacter.getArmRows()<2) {
							setBlockedTooltipContent(getTooltipText(equippedToCharacter,
									"You do not have a second pair of arms with which to hold another secondary weapon!",
									"[npc.Name] [npc.does] not have a second pair of arms with which to hold another secondary weapon!"));
						} else {
							setUnarmedWeaponSlotTooltip(InventorySlot.WEAPON_OFFHAND_2, "Secondary Weapon (2nd)");
						}
						
					} else {
						weaponTooltip(equippedToCharacter.getOffhandWeapon(1));
					}
				} else {
					Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
					Main.mainController.setTooltipContent("<div class='title'>Secondary Weapon (2nd)</div>");
				}

			} else if (invSlot == InventorySlot.WEAPON_OFFHAND_3) {
				if (equippedToCharacter != null) {
					if (equippedToCharacter.getOffhandWeapon(2) == null) {
						AbstractWeapon primary = equippedToCharacter.getMainWeapon(2);
						if(primary!=null && primary.getWeaponType().isTwoHanded()) {
							setBlockedTooltipContent(getTooltipText(equippedToCharacter,
									primary.getWeaponType().isPlural()
										?"As your "+primary.getName()+" require two hands to wield correctly, you are unable to equip a weapon in your off-hand."
										:"As your "+primary.getName()+" requires two hands to wield correctly, you are unable to equip a weapon in your off-hand",
									primary.getWeaponType().isPlural()
										?"As [npc.namePos] "+primary.getName()+" require two hands to wield correctly, [npc.sheIsFull] unable to equip a weapon in [npc.her] off-hand."
										:"As [npc.namePos] "+primary.getName()+" requires two hands to wield correctly, [npc.sheIsFull] unable to equip a weapon in [npc.her] off-hand"));
							
						} else if(equippedToCharacter.getArmRows()<3) {
							setBlockedTooltipContent(getTooltipText(equippedToCharacter,
									"You do not have a third pair of arms with which to hold another secondary weapon!",
									"[npc.Name] [npc.does] not have a third pair of arms with which to hold another secondary weapon!"));
						} else {
							setUnarmedWeaponSlotTooltip(InventorySlot.WEAPON_OFFHAND_3, "Secondary Weapon (3rd)");
						}
					} else {
						weaponTooltip(equippedToCharacter.getOffhandWeapon(2));
					}
				} else {
					Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
					Main.mainController.setTooltipContent("<div class='title'>Secondary Weapon (3rd)</div>");
				}

			} else {
				if (equippedToCharacter != null) {
					boolean renderingTattoos = false;
					
					if((equippedToCharacter.isPlayer() && RenderingEngine.ENGINE.isRenderingTattoosLeft()) || (!equippedToCharacter.isPlayer() && RenderingEngine.ENGINE.isRenderingTattoosRight())
							&& !invSlot.isJewellery()) {
						renderingTattoos = true;
					}
						
					if ((!renderingTattoos && equippedToCharacter.getClothingInSlot(invSlot)==null)
							|| (renderingTattoos && equippedToCharacter.getTattooInSlot(invSlot)==null)) {
						
						List<String> clothingBlockingThisSlot = new ArrayList<>();
						for (AbstractClothing c : equippedToCharacter.getClothingCurrentlyEquipped()) {
							if (c.getIncompatibleSlots(equippedToCharacter, c.getSlotEquippedTo()).contains(invSlot)) {
								clothingBlockingThisSlot.add(c.getName());
							}
						}
						
						BodyPartClothingBlock block = invSlot.getBodyPartClothingBlock(equippedToCharacter);
						
						if (!renderingTattoos && !clothingBlockingThisSlot.isEmpty()) {
							setBlockedTooltipContent(UtilText.parse(equippedToCharacter, "This slot is currently <b style='color:" + PresetColour.SEALED.toWebHexString() + ";'>blocked</b> by [npc.namePos] ")
									+ Util.stringsToStringList(clothingBlockingThisSlot, false) + ".");
							
						} else if (!renderingTattoos && block != null) {
							setBlockedTooltipContent("<span style='color:" + PresetColour.GENERIC_MINOR_BAD.toWebHexString() + ";'>Restricted!</span>", UtilText.parse(equippedToCharacter, block.getDescription()));
							
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
									if(equippedToCharacter.getHornType().equals(HornType.NONE)) {
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
									setEmptyInventorySlotTooltipContent();
								}
							}
						}

					} else {
						if(renderingTattoos && !invSlot.isJewellery()) {
							tattooTooltip(equippedToCharacter.getTattooInSlot(invSlot));
						} else {
							clothingTooltip(equippedToCharacter.getClothingInSlot(invSlot));
						}
					}
					
				} else {
					setEmptyInventorySlotTooltipContent();
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
			
		} else {
			return;
		}

		TooltipUpdateThread.updateToolTip(-1,-1);
		// Main.mainController.getTooltip().show(Main.primaryStage);
	}


	private void setBlockedTooltipContent(String description){
		setBlockedTooltipContent("<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Blocked!</span>", description);
	}
	private void setBlockedTooltipContent(String title, String description){
		boolean dirty = equippedToCharacter.isDirtySlot(invSlot);
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 144);
		Main.mainController.setTooltipContent(UtilText.parse(equippedToCharacter,
				"<div class='title'>"
						+ Util.capitaliseSentence(invSlot.getName())+ ": "+title
				+ "</div>"
				+"<div class='description' style='height:72px; text-align:center;'>"
					+ (dirty
						?"[npc.NamePos] "+invSlot.getName()+" "+(invSlot.isPlural()?"are":"is")
								+ " <span style='color:"+PresetColour.CUM.toWebHexString()+";'>dirty</span>!<br/>"
						:"")
					 + UtilText.parse(description)
				 +"</div>"));
	}
	


	private void setEmptyInventorySlotTooltipContent(){
		if(equippedToCharacter==null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
			Main.mainController.setTooltipContent("<div class='title'>"
					+ Util.capitaliseSentence(invSlot.getName())
			+ "</div>");
			return;
		}
		boolean dirty = equippedToCharacter.isDirtySlot(invSlot);
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60+(dirty?56:0));
		Main.mainController.setTooltipContent(UtilText.parse(equippedToCharacter,
				"<div class='title'>"
						+ Util.capitaliseSentence(invSlot.getName())
				+ "</div>"
				+ (dirty
					?"<div class='description' style='height:48px; text-align:center;'>"
							+ "[npc.NamePos] "+invSlot.getName()+" "+(invSlot.isPlural()?"have":"has")
							+ " been <span style='color:"+PresetColour.CUM.toWebHexString()+";'>dirtied</span> by sexual fluids!"
						+ "</div>"
					:"")));
	}
	
	
	public TooltipInventoryEventListener setCoreItem(AbstractCoreItem coreItem, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.coreItem = coreItem;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}
	
	public TooltipInventoryEventListener setItem(AbstractItem item, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.item = item;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}
	
	public TooltipInventoryEventListener setTattoo(InventorySlot invSlot, Tattoo tattoo, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.invSlot = invSlot;
		this.tattoo = tattoo;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}
	
	public TooltipInventoryEventListener setGenericItem(AbstractItemType genericItem) {
		resetVariables();
		this.genericItem = genericItem;
		return this;
	}

	public TooltipInventoryEventListener setClothing(AbstractClothing clothing, GameCharacter owner, GameCharacter equippedToCharacter) {
		resetVariables();
		this.clothing = clothing;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = owner;
		return this;
	}
	
	public TooltipInventoryEventListener setDyeClothing(AbstractClothing dyeClothing, int colourIndex, Colour colour) {
		resetVariables();
		this.dyeClothing = dyeClothing;
		this.colourIndex = colourIndex;
		this.colour = colour;
		return this;
	}
	
	public TooltipInventoryEventListener setDyeClothingPattern(AbstractClothing dyeClothing, int colourIndex, Colour patternColour) {
		resetVariables();
		this.dyeClothing = dyeClothing;
		this.colourIndex = colourIndex;
		this.patternColour = patternColour;
		return this;
	}

	public TooltipInventoryEventListener setDyeWeapon(AbstractWeapon dyeWeapon, int colourIndex, Colour colour) {
		resetVariables();
		this.dyeWeapon = dyeWeapon;
		this.colourIndex = colourIndex;
		this.colour = colour;
		return this;
	}
	
	public TooltipInventoryEventListener setDamageTypeWeapon(AbstractWeapon dyeWeapon, DamageType damageType) {
		resetVariables();
		this.dyeWeapon = dyeWeapon;
		this.damageType = damageType;
		return this;
	}
	
	public TooltipInventoryEventListener setGenericClothing(AbstractClothingType genericClothing) {
		resetVariables();
		this.genericClothing = genericClothing;
		return this;
	}
	
	public TooltipInventoryEventListener setGenericClothing(AbstractClothingType genericClothing, Colour colour) {
		resetVariables();
		this.genericClothing = genericClothing;
		this.colour = colour;
		return this;
	}
	
	public TooltipInventoryEventListener setGenericTattoo(AbstractTattooType genericTattoo) {
		resetVariables();
		this.genericTattoo = genericTattoo;
		invSlot = genericTattoo.getSlotAvailability().contains(InventorySlot.TORSO_UNDER)?InventorySlot.TORSO_UNDER:genericTattoo.getSlotAvailability().get(0);
		return this;
	}

	public TooltipInventoryEventListener setGenericWeapon(AbstractWeaponType genericWeapon, DamageType dt) {
		resetVariables();
		this.genericWeapon = genericWeapon;
		this.dt = dt;
		return this;
	}

	public TooltipInventoryEventListener setWeapon(AbstractWeapon weapon, GameCharacter owner, boolean isEquipped) {
		resetVariables();
		this.weapon = weapon;
		if(isEquipped) {
			this.equippedToCharacter = owner;
		}
		this.owner = owner;
		return this;
	}

	public TooltipInventoryEventListener setInventorySlot(InventorySlot invSlot, GameCharacter equippedToCharacter) {
		resetVariables();
		this.invSlot = invSlot;
		this.equippedToCharacter = equippedToCharacter;
		this.owner = equippedToCharacter;
		return this;
	}
	
	public TooltipInventoryEventListener setTFModifier(TFModifier enchantmentModifier) {
		resetVariables();
		this.enchantmentModifier = enchantmentModifier;
		return this;
	}
	
	public TooltipInventoryEventListener setTFPotency(TFPotency potency) {
		resetVariables();
		this.potency = potency;
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
		patternColour = null;
		colour = null;
		colourIndex = 0;
		dyeClothing = null;
		dyeWeapon = null;
		damageType = null;
		genericClothing = null;
		genericTattoo = null;
		invSlot = null;
		enchantmentModifier = null;
		potency = null;
	}
	
	private void itemTooltip(AbstractItem absItem) {
		
		int yIncrease = 0;
		int listIncrease = 0;

		String author = absItem.getItemType().getAuthorDescription();
		if(!author.isEmpty()) {
			yIncrease+=4;
		}
		
		if(!absItem.getEffects().isEmpty()) {
			listIncrease+=1;
			for(ItemEffect ie : absItem.getEffects()) {
				listIncrease += ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size();
			}
			listIncrease+=absItem.getEffectTooltipLines().size();
		}
		
		if(!absItem.getExtraDescriptions(equippedToCharacter).isEmpty()) { //TODO
			yIncrease += 2 + absItem.getExtraDescriptions(equippedToCharacter).size();
		}
		
		
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitaliseSentence(absItem.getDisplayName(true)) + "</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-full-width titular'>"
				+ (absItem.isConsumedOnUse()
						? "<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Consumed on use</span>"
						: "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Infinite uses</span>")
				+ "</div>"
					);

		tooltipSB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width titular' style='width:calc(66.6% - 16px);'>"
				+ "<span style='color:" + absItem.getRarity().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(absItem.getRarity().getName())+"</span>"
				);
		
		
		if(absItem.isAppendItemEffectLinesToTooltip()) {
			String effectEntry = "";
			int effectMulti = 0;
			for(int it = 0; it<absItem.getEffects().size(); it++) {
				ItemEffect ie = absItem.getEffects().get(it);
				StringBuilder effectSB = new StringBuilder();
				for(int i=0; i<ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size(); i++) {
					if(i!=0) {
						effectSB.append("</br>");
					}
					effectSB.append(ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).get(i));
				}
	
				effectEntry = effectSB.toString();
				if(it==absItem.getEffects().size()-1 || !absItem.getEffects().get(it+1).equals(ie)) {
					tooltipSB.append("</br>");
					if(effectMulti>0) {
						tooltipSB.append("[style.colourArcane(x"+(effectMulti+1)+")] ");
						listIncrease-=effectMulti;
					}
					tooltipSB.append(effectEntry);
					effectMulti = 0;
					
				} else {
					effectMulti++;
				}
			}
		}
		yIncrease += Math.max(0, listIncrease-4);
		
		for(String s : absItem.getEffectTooltipLines()) {
			tooltipSB.append("</br>"+s);
		}
		
		tooltipSB.append("</div>");
		
		// Picture:
		tooltipSB.append("<div class='item-image'>"
								+ "<div class='item-image-content'>"
									+(absItem.getSVGString())
								+ "</div>"
							+ "</div>");

		tooltipSB.append("</div>");

		tooltipSB.append("<div class='container-full-width' style='padding:8px; min-height:106px;'>"
						+ absItem.getDescription()
					+ "</div>");
		
		
		// Extra descriptions:
		List<String> extraDescriptions = absItem.getExtraDescriptions(equippedToCharacter);

		if(!extraDescriptions.isEmpty()) {
			tooltipSB.append("<div class='container-full-width titular' style='font-weight: normal;'>");
				tooltipSB.append("<b>Status</b>");
				for(int i=0; i<extraDescriptions.size();i++) {
					tooltipSB.append("<br/>");
					tooltipSB.append(extraDescriptions.get(i));
				}
			tooltipSB.append("</div>");
		}

		
		// Value:
		
		if(owner!=null && InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			if(owner.isPlayer()) {
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
										+ "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>"
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
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(absItem.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier(absItem)))
									+ "</div>");
				}
			}
		} else {
			tooltipSB.append("<div class='container-full-width titular'>" + "Value: "+UtilText.formatAsMoney(absItem.getValue()) + "</div>");
		}
		
		if(!author.isEmpty()) {
			tooltipSB.append("<div class='description' style='height:52px;'>" + author + "</div>");
		}
		
		tooltipSB.append("</body>");

		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 364 + (yIncrease * LINE_HEIGHT));
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
	}

	private void weaponTooltip(AbstractWeapon absWep) {
		
		int yIncrease = 0;
		int listIncrease = 2 + absWep.getAttributeModifiers().size();
		listIncrease += absWep.getSpells().size();
		listIncrease += absWep.getWeaponType().getExtraEffects().size();

		String author = absWep.getWeaponType().getAuthorDescription();
		if(!author.isEmpty()) {
			yIncrease+=4;
		}
		if(!absWep.getExtraDescriptions(equippedToCharacter).isEmpty()) { //TODO
			yIncrease += 2 + absWep.getExtraDescriptions(equippedToCharacter).size();
		}
		
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitaliseSentence(absWep.getDisplayName(true)) + "</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-half-width titular' style='color:"+absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(absWep.getDamageType().getName()) + " damage</div>");
		tooltipSB.append("<div class='container-half-width titular'>"
							+ (absWep.getWeaponType().getClothingSet() == null
								? "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>Not part of a set</span>"
								: "<span style='color:" + PresetColour.RARITY_EPIC.toWebHexString() + ";'>"+absWep.getWeaponType().getClothingSet().getName() + " set</span>")
						+ "</div>");
		
		
		// Attribute modifiers:
		tooltipSB.append("<div class='container-full-width'>");
		tooltipSB.append("<div class='container-half-width titular' style='width:calc(66.6% - 16px);'>");
			tooltipSB.append("<span style='color:" + absWep.getRarity().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(absWep.getRarity().getName())+"</span>"+ " | "
						+(absWep.getWeaponType().isUsingUnarmedCalculation()
								?"[style.colourUnarmed(Unarmed)]"
								:(absWep.getWeaponType().isMelee()
									?"[style.colourMelee(Melee)]"
									:"[style.colourRanged(Ranged)]"))
						+"</br>"
						+ (absWep.getWeaponType().isTwoHanded()? "Two-handed" : "One-handed")
						+ (absWep.getWeaponType().isOneShot()?" - [style.colourYellow(One-shot)]":"")
						+"</br>"
						);
			
			float res = absWep.getWeaponType().getPhysicalResistance();
			if(res>0) {
				listIncrease++;
				tooltipSB.append("[style.boldGood(+"+res+")] Natural [style.boldResPhysical("+Util.capitaliseSentence(Attribute.RESISTANCE_PHYSICAL.getName())+")]</br>");
			}
			
			int cost = absWep.getWeaponType().getArcaneCost();
			if(cost>0) {
				listIncrease++;
				tooltipSB.append("Costs [style.boldArcane("+cost+" Arcane essence"+(cost>1?"s":"")+")] "+(absWep.getWeaponType().isMelee()?"per attack":"to fire")+"<br/>");
				if(absWep.getWeaponType().isMelee()) {
					listIncrease++; // To account for the fact that the arcane cost description for melee weapons takes two lines
				}
			}
			
			if(equippedToCharacter != null) {
				if(absWep.getWeaponType().isUsingUnarmedCalculation()) {
					listIncrease++;
					tooltipSB.append("Includes [style.boldUnarmed("+equippedToCharacter.getUnarmedDamage()+" unarmed damage)]<br/>");
				}
				tooltipSB.append("<b>"+ Attack.getMinimumDamage(equippedToCharacter, null, Attack.MAIN, absWep) + " - " + Attack.getMaximumDamage(equippedToCharacter, null, Attack.MAIN, absWep)+ "</b>"
						+ " <b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</b>");
				
				for(Value<Integer, Integer> aoe : absWep.getWeaponType().getAoeDamage()) {
					listIncrease++;
					int aoeChance = aoe.getKey();
					tooltipSB.append("<br/>[style.boldAqua(AoE)]: "
							+ "(<b style='color:"+(aoeChance<=25?PresetColour.GENERIC_BAD:(aoeChance<=50?PresetColour.GENERIC_MINOR_BAD:(aoeChance<=75?PresetColour.GENERIC_MINOR_GOOD:PresetColour.GENERIC_GOOD))).toWebHexString()+";'>"+aoeChance+"%</b>): "
							+ "<b>"+ Attack.getMinimumDamage(equippedToCharacter, null, Attack.MAIN, absWep, aoe.getValue()) + " - " + Attack.getMaximumDamage(equippedToCharacter, null, Attack.MAIN, absWep, aoe.getValue())+ "</b>"
							+ " <b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</b>");
				}
				
			} else {
				if(owner!=null && !owner.isPlayer()) {
					listIncrease++;
					tooltipSB.append(UtilText.parse(owner, "[npc.Name]: ")
						+"<b>"
							+ Attack.getMinimumDamage(owner, null, Attack.MAIN, absWep) + " - " + Attack.getMaximumDamage(owner, null, Attack.MAIN, absWep)
						+ "</b>"
						+ " <b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</b>");
					for(Value<Integer, Integer> aoe : absWep.getWeaponType().getAoeDamage()) {
						listIncrease++;
						int aoeChance = aoe.getKey();
						tooltipSB.append("<br/>[style.boldAqua(AoE)]: "
								+ "(<b style='color:"+(aoeChance<=25?PresetColour.GENERIC_BAD:(aoeChance<=50?PresetColour.GENERIC_MINOR_BAD:(aoeChance<=75?PresetColour.GENERIC_MINOR_GOOD:PresetColour.GENERIC_GOOD))).toWebHexString()+";'>"+aoeChance+"%</b>): "
								+ "<b>"+ Attack.getMinimumDamage(owner, null, Attack.MAIN, absWep, aoe.getValue()) + " - " + Attack.getMaximumDamage(owner, null, Attack.MAIN, absWep, aoe.getValue())+ "</b>"
								+ " <b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</b>");
					}
					tooltipSB.append("<br/>You: ");
				}
				tooltipSB.append("<b>"+ Attack.getMinimumDamage(Main.game.getPlayer(), null, Attack.MAIN, absWep) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), null, Attack.MAIN, absWep)+ "</b>"
								+ " <b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</b>");
				
				for(Value<Integer, Integer> aoe : absWep.getWeaponType().getAoeDamage()) {
					listIncrease++;
					int aoeChance = aoe.getKey();
					tooltipSB.append("<br/>[style.boldAqua(AoE)] "
							+ "(<b style='color:"+(aoeChance<=25?PresetColour.GENERIC_BAD:(aoeChance<=50?PresetColour.GENERIC_MINOR_BAD:(aoeChance<=75?PresetColour.GENERIC_MINOR_GOOD:PresetColour.GENERIC_GOOD))).toWebHexString()+";'>"+aoeChance+"%</b>): "
							+ "<b>"+ Attack.getMinimumDamage(Main.game.getPlayer(), null, Attack.MAIN, absWep, aoe.getValue()) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), null, Attack.MAIN, absWep, aoe.getValue())+ "</b>"
							+ " <b style='color:" + absWep.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</b>");
				}
			}
			
			if(absWep.getWeaponType().isOneShot()) {
				listIncrease++;
				listIncrease++;
				int chanceToRecoverTurn = (int)absWep.getWeaponType().getOneShotChanceToRecoverAfterTurn();
				int chanceToRecoverCombat = (int)absWep.getWeaponType().getOneShotChanceToRecoverAfterCombat();

				tooltipSB.append("<br/><span style='color:"
						+(chanceToRecoverTurn<=25?PresetColour.GENERIC_BAD:(chanceToRecoverTurn<=50?PresetColour.GENERIC_MINOR_BAD:(chanceToRecoverTurn<=75?PresetColour.GENERIC_MINOR_GOOD:PresetColour.GENERIC_GOOD))).toWebHexString()
						+"'>"+chanceToRecoverTurn+"%</span> recovery [style.colourBlueLight(after use)]<br/>");
				
				tooltipSB.append("<span style='color:"
						+(chanceToRecoverCombat<=25?PresetColour.GENERIC_BAD:(chanceToRecoverCombat<=50?PresetColour.GENERIC_MINOR_BAD:(chanceToRecoverCombat<=75?PresetColour.GENERIC_MINOR_GOOD:PresetColour.GENERIC_GOOD))).toWebHexString()
						+";'>"+chanceToRecoverCombat+"%</span> recovery [style.colourCombat(after combat)]");
			}
			
			for(String s : absWep.getWeaponType().getExtraEffects()) {
				tooltipSB.append("<br/><b>"+s+"</b>");
			}
			
			for(Entry<AbstractAttribute, Integer> entry : absWep.getAttributeModifiers().entrySet()) {
				tooltipSB.append("<br/><b>"+entry.getKey().getFormattedValue(entry.getValue())+"</b>");
			}
		
			for(Spell s : absWep.getSpells()) {
				tooltipSB.append("<br/>[style.boldSpell(Spell)]<b>:</b> <b style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(s.getName())+"</b>");
			}
		
			for(AbstractCombatMove cm : absWep.getCombatMoves()) {
				tooltipSB.append("<br/>[style.boldCombat(Move)]<b>:</b> "+Util.capitaliseSentence(cm.getName(0, Main.game.getPlayer())));
			}
			
		tooltipSB.append("</div>");
		
		// Picture:
		tooltipSB.append("<div class='item-image'>"
							+ "<div class='item-image-content'>"
								+ ((owner!=null && owner.hasWeaponEquipped(absWep))
									?absWep.getSVGEquippedString(owner)
									:absWep.getSVGString())
							+ "</div>"
						+ "</div>");

		tooltipSB.append("</div>");

		tooltipSB.append("<div class='container-full-width' style='padding:8px; min-height:106px;'>"
						+ UtilText.parse(absWep.getWeaponType().getDescription())
					+ "</div>");

		if(owner!=null && owner.getEssenceCount()<absWep.getWeaponType().getArcaneCost()) {
			yIncrease+=2;
			tooltipSB.append("<div class='container-full-width titular'>"
								+ "[style.colourBad(Not enough essences to fire!)]"
							+ "</div>");
		}
		
		// Extra descriptions:
		List<String> extraDescriptions = absWep.getExtraDescriptions(equippedToCharacter);

		if(!extraDescriptions.isEmpty()) {
			tooltipSB.append("<div class='container-full-width titular' style='font-weight: normal;'>");
				tooltipSB.append("<b>Status</b>");
				for(int i=0; i<extraDescriptions.size();i++) {
					tooltipSB.append("<br/>");
					tooltipSB.append(extraDescriptions.get(i));
				}
			tooltipSB.append("</div>");
		}
		
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
										+ "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>"
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
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(absWep.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier(absWep)))
									+ "</div>");
				}
			}
		} else {
			tooltipSB.append(
					"<div class='container-full-width titular'>"
							+ "Value: "+UtilText.formatAsMoney(absWep.getValue())
					+ "</div>");
		}
		if(Main.game.isEnchantmentCapacityEnabled()) {
			int enchCapacityCost = absWep.getEnchantmentCapacityCost();
			tooltipSB.append(
					"<div class='container-full-width titular'>"
							+(enchCapacityCost==0
								?Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+" cost: [style.boldDisabled("+enchCapacityCost+")]"
								:"[style.colourEnchantment("+Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+" cost)]: [style.boldBad("+enchCapacityCost+")]")
					+ "</div>");
		}
		if(!author.isEmpty()) {
			tooltipSB.append("<div class='description' style='height:52px;'>" + author + "</div>");
		}
		
		tooltipSB.append("</body>");

		yIncrease += Math.max(0, listIncrease-4);
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 364 + (Main.game.isEnchantmentCapacityEnabled()?32:0) + (yIncrease * LINE_HEIGHT));
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
		
	}

	private void clothingTooltip(AbstractClothing absClothing) {
		int yIncrease = 0;
				
		int listIncrease = absClothing.getAttributeModifiers().size();
		
		float resistance = absClothing.getClothingType().getPhysicalResistance();
		if(resistance>0) {
			listIncrease++;
		}
		
		InventorySlot slotEquippedTo = absClothing.getSlotEquippedTo();
		yIncrease += absClothing.getExtraDescriptions(equippedToCharacter, null, false).size();
		if(slotEquippedTo==null) {
			slotEquippedTo = absClothing.getClothingType().getEquipSlots().get(0);
			for(InventorySlot is : absClothing.getClothingType().getEquipSlots()) {
				yIncrease += absClothing.getExtraDescriptions(equippedToCharacter, is, false).size();
			}
			
		} else {
			yIncrease += absClothing.getExtraDescriptions(equippedToCharacter, slotEquippedTo, false).size();
		}
		
		for(ItemEffect ie : absClothing.getEffects()) {
			if(ie.getSecondaryModifier()==TFModifier.CLOTHING_ENSLAVEMENT
					|| ie.getSecondaryModifier()==TFModifier.CLOTHING_SEALING) {
				listIncrease+=1;
				
			} else if(ie.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE && ie.getPrimaryModifier()!=TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
				listIncrease+=2;
			}
		}
		yIncrease += Math.max(0, listIncrease-4);

		String author = absClothing.getClothingType().getAuthorDescription();
		if(!author.isEmpty()) {
			yIncrease+=4;
		}
		
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitaliseSentence(absClothing.getDisplayName(true)) + "</h5></div>");

		// Core info:
		tooltipSB.append("<div class='container-half-width titular'>");
			boolean nonPiercingSlots = absClothing.getClothingType().getEquipSlots().stream().anyMatch(is->!is.isJewellery());
			List<InventorySlot> possibleSlots = new ArrayList<>(absClothing.getClothingType().getEquipSlots());
			if(nonPiercingSlots) {
				possibleSlots.sort((is1, is2)->absClothing.getSlotEquippedTo()==is1?1:(absClothing.getSlotEquippedTo()==is2?-1:0));
			}
			for(int i=0; i<possibleSlots.size(); i++) {
				InventorySlot slot = possibleSlots.get(i);
				boolean equipped = absClothing.getSlotEquippedTo() == slot;
				if(nonPiercingSlots) { // Slots contain non-piercing slots
					tooltipSB.append(
							(equipped || absClothing.getSlotEquippedTo()==null
								?Util.capitaliseSentence(slot.getName())
								:"[style.colourDisabled("+Util.capitaliseSentence(slot.getName())+")]")
							+(i==possibleSlots.size()-1
								?""
								:(absClothing.getSlotEquippedTo()!=null
									?"[style.colourDisabled(/)]"
									:"/")));
				} else { // Slots are all piercings, so to abbreviate the slot names, the ' piercing' parts can all be removed, then a final ' piercing' can be appended at the end
					String slotName = slot.getName().replace(" piercing", "");
					tooltipSB.append(
							(equipped || absClothing.getSlotEquippedTo()==null
								?Util.capitaliseSentence(slotName)
								:"[style.colourDisabled("+Util.capitaliseSentence(slotName)+")]")
							+(i==possibleSlots.size()-1
								?""
								:(absClothing.getSlotEquippedTo()!=null
									?"[style.colourDisabled(/)]"
									:"/")));
					if(i==possibleSlots.size()-1) {
						tooltipSB.append(" piercing");
					}
				}
			}
		tooltipSB.append("</div>");
		tooltipSB.append("<div class='container-half-width titular'>"
							+ (absClothing.getClothingType().getClothingSet() == null
								? "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>Not part of a set</span>"
								: "<span style='color:" + PresetColour.RARITY_EPIC.toWebHexString() + ";'>"+absClothing.getClothingType().getClothingSet().getName() + " set</span>")
						+ "</div>");
		
		// Attribute modifiers:
		tooltipSB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width titular' style='width:calc(66.6% - 16px);'>");
		
		Femininity femininityRestriction = absClothing.getClothingType().getFemininityRestriction();
		tooltipSB.append(
				"<span style='color:" + (absClothing.isEnchantmentKnown()?absClothing.getRarity().getColour():PresetColour.TEXT_GREY).toWebHexString() + ";'>"
						+Util.capitaliseSentence(absClothing.isEnchantmentKnown()?absClothing.getRarity().getName():"Unknown")
				+"</span>"
				+ " | "
				+ (femininityRestriction==null || femininityRestriction==Femininity.ANDROGYNOUS
					?"[style.boldAndrogynous(Unisex)]"
					:(femininityRestriction.isFeminine()
						?"[style.boldFeminine(Feminine)]"
						:"[style.boldMasculine(Masculine)]")));
		
		if(resistance>0) {
			tooltipSB.append("</br>[style.boldGood(+"+resistance+")] Natural [style.boldResPhysical("+Util.capitaliseSentence(Attribute.RESISTANCE_PHYSICAL.getName())+")]");
		}
		
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
				for(Entry<AbstractAttribute, Integer> entry : absClothing.getAttributeModifiers().entrySet()) {
					tooltipSB.append("<br/><b>"+entry.getKey().getFormattedValue(entry.getValue())+"</b>");
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

		tooltipSB.append("<div class='container-full-width' style='padding:8px; min-height:106px;'>"
						+ absClothing.getTypeDescription()
					+ "</div>");
		
		
		// Extra descriptions:
		List<String> extraDescriptions = new ArrayList<>();

		tooltipSB.append("<div class='container-full-width titular' style='font-weight: normal;'>");
		
		extraDescriptions.addAll(absClothing.getExtraDescriptions(equippedToCharacter, null, false));
		
		if(absClothing.getSlotEquippedTo()==null && absClothing.getClothingType().getEquipSlots().size()>1) {
			for(int i=0; i<absClothing.getClothingType().getEquipSlots().size();i++) {
				InventorySlot slot = absClothing.getClothingType().getEquipSlots().get(i);
				
				if(!absClothing.getExtraDescriptions(equippedToCharacter, slot, false).isEmpty()) {
					extraDescriptions.add("<i>When equipped into '"+slot.getName()+"' slot:</i>");
					yIncrease++;
					for (String s : absClothing.getExtraDescriptions(equippedToCharacter, slot, false)) {
						extraDescriptions.add(s);
					}
				}
			}
			
		} else {
			if(!absClothing.getExtraDescriptions(equippedToCharacter, slotEquippedTo, false).isEmpty()) {
				for (String s : absClothing.getExtraDescriptions(equippedToCharacter, slotEquippedTo, false)) {
					extraDescriptions.add(s);
				}
			}
		}
		if(extraDescriptions.isEmpty()) {
			tooltipSB.append("<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>No Status</span>");
			
		} else {
			tooltipSB.append("<b>Status</b>");
			for(int i=0; i<extraDescriptions.size();i++) {
				tooltipSB.append("<br/>");
				tooltipSB.append(extraDescriptions.get(i));
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
										+ "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>" + InventoryDialogue.getInventoryNPC().getName("The") + " will not buy this</span>"
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
											+ InventoryDialogue.getInventoryNPC().getName("The") + " wants " + UtilText.formatAsMoney(absClothing.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier(absClothing)))
									+ "</div>");
				}
			}
		} else {
			tooltipSB.append("<div class='container-full-width titular'>Value: "+ (absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : UtilText.formatAsMoney("?", "b")) + "</div>");
		}
		
		if(Main.game.isEnchantmentCapacityEnabled()) {
			int enchCapacityCost = absClothing.getEnchantmentCapacityCost();
			tooltipSB.append(
					"<div class='container-full-width titular'>"
							+(absClothing.isEnchantmentKnown() ?
								(enchCapacityCost==0
									?Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+" cost: [style.boldDisabled("+enchCapacityCost+")]"
									:"[style.colourEnchantment("+Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+" cost)]: [style.boldBad("+enchCapacityCost+")]")
							:Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+" cost: ?")
					+ "</div>");
		}
		
		if(!author.isEmpty()) {
			tooltipSB.append("<div class='description' style='height:52px;'>" + author + "</div>");
		}
		
		tooltipSB.append("</body>");

		int specialIncrease = 0;
		if(absClothing.getDisplayName(false).length()>40) {
			specialIncrease = 26;
		}
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 400 + (Main.game.isEnchantmentCapacityEnabled()?32:0) + (yIncrease * LINE_HEIGHT) + specialIncrease);
		Main.mainController.setTooltipContent(UtilText.parse(equippedToCharacter==null?Main.game.getPlayer():equippedToCharacter, tooltipSB.toString()));

	}
	
	
	private void scarTooltip(Scar scar) {
		int yIncrease = 0;
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>No tattoo</h5></div>");
		
		// Core info:
		tooltipSB.append("<div class='container-half-width titular'>" + Util.capitaliseSentence(invSlot.getTattooSlotName()) + "</div>");
		tooltipSB.append("<div class='container-half-width titular'>"
							+ (scar==null
									? "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>No scars</span>"
									: "<span style='color:" + PresetColour.SCAR.toWebHexString() + ";'>"+Util.capitaliseSentence(owner.getScarInSlot(invSlot).getName())+"</span>")
						+ "</div>");
		
		SizedStack<Covering> lipsticks = owner.getLipstickMarkingsInSlot(invSlot);
		if(lipsticks!=null) {
			yIncrease = 24 + (1+lipsticks.size())*LINE_HEIGHT;
			tooltipSB.append("<div class='container-full-width' style='text-align:center; padding:8px; height:"+(16+(1+lipsticks.size())*LINE_HEIGHT)+"px;'>");
			tooltipSB.append(UtilText.parse(owner, "[npc.NamePos] ")+invSlot.getNameOfAssociatedPart(owner)+" "+(invSlot.isPlural()?"have":"has")+" been marked by:");
				for(int i=lipsticks.size()-1; i>=0; i--) {
					tooltipSB.append("<br/>"+Util.capitaliseSentence(lipsticks.get(i).getFullDescription(owner, true)));
				}
			tooltipSB.append("</div>");
		}
		
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, yIncrease+88);
		Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
	}
	
	private void tattooTooltip(Tattoo tattoo) {
		int yIncrease = 0;
		int specialIncrease = 0;
		int lipstickYIncrease = 0;
		
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
		if(owner!=null) {
			tooltipSB.append("<div class='container-half-width titular'>"
								+ (owner.getScarInSlot(invSlot)==null
										? "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>No scars</span>"
										: "<span style='color:" + PresetColour.SCAR.toWebHexString() + ";'>"+Util.capitaliseSentence(owner.getScarInSlot(invSlot).getName())+"</span>")
							+ "</div>");
		}
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
			for(Entry<AbstractAttribute, Integer> entry : tattoo.getAttributeModifiers().entrySet()) {
				tooltipSB.append((i>0?"<br/>":"")+"<b>"+entry.getKey().getFormattedValue(entry.getValue())+"</b>");
				i++;
			}
			
		} else {
			tooltipSB.append("[style.colourDisabled(No bonuses)]");
		}
		
		tooltipSB.append("</div>");
		
		// Picture:
		tooltipSB.append("<div class='item-image'>");
			tooltipSB.append("<div class='item-image-content'>");
				tooltipSB.append(tattoo.getSVGImage(
										equippedToCharacter==null
											?Main.game.getPlayer()
											:equippedToCharacter));
			tooltipSB.append("</div>");
		tooltipSB.append("</div>");

		tooltipSB.append("</div>");

		tooltipSB.append("<div class='container-full-width' style='padding:8px; height:106px;'>");
				tooltipSB.append(tattoo.getType().getDescription());
		
			if (tattoo.getWriting()!=null && !tattoo.getWriting().getText().isEmpty()) {
				tooltipSB.append("<br/>");
				if(tattoo.getWriting().getStyles().isEmpty()) {
					tooltipSB.append("Normal,");
				} else {
					int i=0;
					for(TattooWritingStyle style : tattoo.getWriting().getStyles()) {
						tooltipSB.append(i==0?Util.capitaliseSentence(style.getName()):", "+style.getName());
						i++;
					}
				}
				tooltipSB.append(" "+tattoo.getWriting().getColour().getName()+" writing forms part of the tattoo.");
			}
			
			if (tattoo.getCounter()!=null && tattoo.getCounter().getType()!=TattooCounterType.NONE) {
				tooltipSB.append("<br/>"
									+ "An enchanted, "+tattoo.getCounter().getColour().getName()+" "+tattoo.getCounter().getType().getName()+" counter has been applied to the tattoo.");
			}

		tooltipSB.append("</div>");
		
		if (tattoo.getWriting()!=null && !tattoo.getWriting().getText().isEmpty()) {
			tooltipSB.append("<div class='container-full-width' style='padding:4px; height:42px; text-align:center;'>");
			tooltipSB.append("The writing reads:<br/>");
			tooltipSB.append(tattoo.getFormattedWritingOutput()
					+ "</div>");
		} else {
			tooltipSB.append(
					"<div class='container-full-width' style='padding:4px; height:28px; text-align:center;'>"
						+"[style.colourDisabled(This tattoo doesn't have any writing.)]"
					+ "</div>");
		}

		if (tattoo.getCounter()!=null && tattoo.getCounter().getType()!=TattooCounterType.NONE) {
			tooltipSB.append("<div class='container-full-width' style='padding:4px; height:42px; text-align:center;'>"
								+ "The '"+tattoo.getCounter().getType().getName()+"' counter displays:<br/>"
									+ "<span style='color:"+tattoo.getCounter().getColour().toWebHexString()+";'>"
											+tattoo.getFormattedCounterOutput(
													equippedToCharacter==null
														?Main.game.getPlayer()
														:equippedToCharacter)
									+"</span>"
							+ "</div>");
		} else {
			tooltipSB.append(
					"<div class='container-full-width' style='padding:8px; height:28px; text-align:center;'>"
						+"[style.colourDisabled(This tattoo doesn't have a counter.)]"
					+ "</div>");
		}
		
		if(Main.game.isEnchantmentCapacityEnabled()) {
			int enchCapacityCost = tattoo.getEnchantmentCapacityCost();
			tooltipSB.append(
					"<div class='container-full-width titular'>"
							+(enchCapacityCost==0
								?Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+" cost: [style.boldDisabled("+enchCapacityCost+")]"
								:"[style.colourEnchantment("+Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+" cost)]: [style.boldBad("+enchCapacityCost+")]")
					+ "</div>");
		}
		
		tooltipSB.append("</div>");

		if(owner!=null) {
			SizedStack<Covering> lipsticks = owner.getLipstickMarkingsInSlot(invSlot);
			if(lipsticks!=null) {
				lipstickYIncrease = 24 + (1+lipsticks.size())*LINE_HEIGHT;
				tooltipSB.append("<div class='container-full-width' style='text-align:center; padding:8px; height:"+(16+(1+lipsticks.size())*LINE_HEIGHT)+"px;'>");
				tooltipSB.append(UtilText.parse(owner, "[npc.NamePos] ")+invSlot.getNameOfAssociatedPart(owner)+" "+(invSlot.isPlural()?"have":"has")+" been marked by:");
					for(int i=lipsticks.size()-1; i>=0; i--) {
						tooltipSB.append("<br/>"+Util.capitaliseSentence(lipsticks.get(i).getFullDescription(owner, true)));
					}
				tooltipSB.append("</div>");
			}
		}
		
		tooltipSB.append("</body>");

		if(tattoo.getDisplayName(false).length()>40) {
			specialIncrease = 26;
		}
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 404 + (Main.game.isEnchantmentCapacityEnabled()?32:0) + (yIncrease * LINE_HEIGHT) + lipstickYIncrease + specialIncrease);
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
	
	private String getTooltipText(GameCharacter character, String playerText, String NPCText) {
		if(character.isPlayer()) {
			return playerText;
		} else {
			return UtilText.parse(character, NPCText);
		}
	}
	
	private void setUnarmedWeaponSlotTooltip(InventorySlot slot, String title) {
		BodyPartClothingBlock block = slot.getBodyPartClothingBlock(equippedToCharacter);
		
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 132+(block!=null?128:0));
		int baseDamage = equippedToCharacter.getBaseUnarmedDamage();
		int modifiedDamage = equippedToCharacter.getUnarmedDamage();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='title'>"
						+title+" (Unarmed)"
					+ "</div>"
					+ "<div class='description' style='height:64px; text-align:center;'>"
						+ UtilText.parse(equippedToCharacter,
							"[npc.Name] [npc.has] a base unarmed damage value of "+baseDamage+", which is modified from attributes to deal:"
							+ "<br/>[style.boldUnarmed("+modifiedDamage+" Unarmed damage)]")
					+ "</div>");

		if(block != null) {
			sb.append(UtilText.parse(equippedToCharacter,
					"<div class='title'>"
						+ "<span style='color:" + PresetColour.GENERIC_MINOR_BAD.toWebHexString() + ";'>Restricted!</span>"
					+ "</div>"
					+"<div class='description' style='height:72px; text-align:center;'>"
						+ UtilText.parse(equippedToCharacter, block.getDescription())
					 +"</div>"));
		}
		
		Main.mainController.setTooltipContent(sb.toString());
	}
}
