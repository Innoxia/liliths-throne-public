package com.lilithsthrone.controller.eventListeners.tooltips;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaDressingRoomDialogue;
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
			
			boolean isInventoryDialogue = Main.game.getCurrentDialogueNode()!=LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_DYE;
			
			tooltipSB.setLength(0);
			if(colour!=null) {
				List<Colour> dyeColours = new ArrayList<>(isInventoryDialogue?InventoryDialogue.dyePreviews:LilayaDressingRoomDialogue.getClothingSelected().getColours());
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
								isInventoryDialogue?InventoryDialogue.dyePreviewPattern:LilayaDressingRoomDialogue.getClothingSelected().getPattern(),
								isInventoryDialogue?InventoryDialogue.dyePreviewPatternColours:LilayaDressingRoomDialogue.getClothingSelected().getPatternColours(),
								isInventoryDialogue?InventoryDialogue.getDyePreviewStickersAsStrings():LilayaDressingRoomDialogue.getClothingSelected().getStickers())
						+ "</div>");
			
			} else if(patternColour!=null) {
				List<Colour> dyeColours = new ArrayList<>(isInventoryDialogue?InventoryDialogue.dyePreviewPatternColours:LilayaDressingRoomDialogue.getClothingSelected().getPatternColours());
				dyeColours.remove(colourIndex);
				dyeColours.add(colourIndex, patternColour);
				tooltipSB.append("<div class='title' style='color:" + subtitleColour.toWebHexString() + ";'>" + Util.capitaliseSentence(dyeClothing.getName()) + "</div>"
						+ "<div class='subTitle'>" + Util.capitaliseSentence(Pattern.getPattern(isInventoryDialogue?InventoryDialogue.dyePreviewPattern:LilayaDressingRoomDialogue.getClothingSelected().getPattern()).getNiceName()) + "</div>"
						+ "<div class='picture full' style='position:relative; margin:8px; padding:0; width:calc("+TOOLTIP_WIDTH+"px - 24px); height:calc("+TOOLTIP_WIDTH+"px - 24px);'>"
						+ dyeClothing.getClothingType().getSVGImage(
								slotEquippedTo,
								isInventoryDialogue?InventoryDialogue.dyePreviews:LilayaDressingRoomDialogue.getClothingSelected().getColours(),
								isInventoryDialogue?InventoryDialogue.dyePreviewPattern:LilayaDressingRoomDialogue.getClothingSelected().getPattern(),
								dyeColours,
								isInventoryDialogue?InventoryDialogue.getDyePreviewStickersAsStrings():LilayaDressingRoomDialogue.getClothingSelected().getStickers())
						+ "</div>");
				
			}
			
			Main.mainController.setTooltipContent(UtilText.parse(dyeClothing, tooltipSB.toString()));

		} else if(dyeWeapon != null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 480);

			tooltipSB.setLength(0);
			tooltipSB.append("<div class='title' style='color:" + dyeWeapon.getRarity().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(dyeWeapon.getName()) + "</div>");

			boolean isInventoryDialogue = Main.game.getCurrentDialogueNode()!=LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_DYE;
			
			if(colour!=null) {
				List<Colour> dyeColours = new ArrayList<>(isInventoryDialogue?InventoryDialogue.dyePreviews:LilayaDressingRoomDialogue.getWeaponSelected().getColours());
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
							+ dyeWeapon.getWeaponType().getSVGImage(damageType, isInventoryDialogue?InventoryDialogue.dyePreviews:LilayaDressingRoomDialogue.getWeaponSelected().getColours())
						+ "</div>");
			}
			
			Main.mainController.setTooltipContent(UtilText.parse(dyeWeapon, tooltipSB.toString()));

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
							false,
							0)));

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
							setBlockedTooltipContent("<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Blocked!</span>", UtilText.parse(equippedToCharacter, block.getDescription()));
							
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
					+ "<div class='description' style='min-height:48px; max-height:48px;'>"
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
		setEmptyInventorySlotTooltipContent(title, description);
//		boolean dirty = equippedToCharacter.isDirtySlot(invSlot);
//		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 144);
//		Main.mainController.setTooltipContent(UtilText.parse(equippedToCharacter,
//				"<div class='title'>"
//						+ Util.capitaliseSentence(invSlot.getName())+ ": "+title
//				+ "</div>"
//				+"<div class='description' style='height:72px; text-align:center;'>"
//					+ (dirty
//						?"[npc.NamePos] "+invSlot.getName()+" "+(invSlot.isPlural()?"are":"is")
//								+ " <span style='color:"+PresetColour.CUM.toWebHexString()+";'>dirty</span>!<br/>"
//						:"")
//					 + UtilText.parse(description)
//				 +"</div>"));
	}

	private void setEmptyInventorySlotTooltipContent(){
		setEmptyInventorySlotTooltipContent("", "");
	}

	private void setEmptyInventorySlotTooltipContent(String title, String description){
		if(equippedToCharacter==null) {
			Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60);
			Main.mainController.setTooltipContent("<div class='title'>"
					+ Util.capitaliseSentence(invSlot.getName())+(title!=null&&!title.isEmpty()?": "+title:"")
			+ "</div>");
			return;
		}
		boolean dirty = equippedToCharacter.isDirtySlot(invSlot);
		boolean hasDescription = description!=null && !description.isEmpty();
		
		Map<GameCharacter, Integer> cummedOnInfo = new HashMap<>();
		if(Main.game.isInSex()) {
			for(Entry<GameCharacter, Map<InventorySlot, Integer>> entry : Main.sex.getAmountCummedOnByPartners(equippedToCharacter).entrySet()) {
				for(Entry<InventorySlot, Integer> areas : entry.getValue().entrySet()) {
					if(areas.getKey()==invSlot) {
						cummedOnInfo.put(entry.getKey(), areas.getValue());
					}
				}
			}
		}
		int cummedMapSize = cummedOnInfo.size();
		int descHeight = LINE_HEIGHT*(cummedMapSize+(cummedMapSize>0?1:0)+(dirty?(Main.game.isInSex()&&cummedMapSize==0?2:1):0)+(hasDescription?2:0));
		if(descHeight>0) {
			descHeight+=16;
		}
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH, 60+(dirty||!cummedOnInfo.isEmpty()||hasDescription?8:0)+descHeight);
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='title'>"
						+ Util.capitaliseSentence(invSlot.getName())+(title!=null&&!title.isEmpty()?": "+title:"")
				+ "</div>");
		
		if(dirty || !cummedOnInfo.isEmpty() || hasDescription) {
			sb.append("<div class='description' style='min-height:0; height:"+descHeight+"px; text-align:center;'>");
			
			if(hasDescription) {
				sb.append(description);
				if(dirty) {
					sb.append("<br/>");
				}
			}
			
			if(dirty) {
				sb.append("[npc.NamePos] "+invSlot.getName()+" "+(invSlot.isPlural(equippedToCharacter)?"have":"has")
						+ " been [style.colourDirty(dirtied)]!");
				if(Main.game.isInSex()) {
					sb.append("<br/>");
				}
				if(!cummedOnInfo.isEmpty()) {
					sb.append("[style.boldDirty(Sexual fluids present:)]");
					for(Entry<GameCharacter, Integer> entry : cummedOnInfo.entrySet()) {
						sb.append("<br/>");
						sb.append(UtilText.parse(entry.getKey(), "[style.fluid("+entry.getValue()+")] of <span style='color:"+entry.getKey().getFemininity().getColour().toWebHexString()+";'>[npc.namePos]</span> [npc.cum+]!"));
					}
				} else if(Main.game.isInSex()) {
					sb.append("[style.italicsDisabled(No fluid is available...)]");
				}
			}
			
			
			
			sb.append("</div>");
			
		}
		
		Main.mainController.setTooltipContent(UtilText.parse(equippedToCharacter, sb.toString()));
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
		int specialYIncrease = 0;
		int yIncrease = 0;
		
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>");
		
		tooltipSB.append("<div class='container-full-width center'><h5>" + Util.capitaliseSentence(absItem.getDisplayName(true)) + "</h5></div>");

		// Use number info (full-width title):
		tooltipSB.append("<div class='container-full-width titular' style='margin-bottom:2px;'>"
				+ (absItem.isConsumedOnUse()
						? "<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Consumed on use</span>"
						: "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Infinite uses</span>")
				+ "</div>"
					);

		// Value:
		String valueDivClass = Main.game.isEnchantmentCapacityEnabled()?"container-half-width titular":"container-full-width titular";
		String valueStyle = "style='margin:2px 2px 2px 8px; width:calc(50% - 10px);'";
		if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			if (owner.isPlayer()) {
				if (InventoryDialogue.getInventoryNPC().willBuy(absItem)) {
					int buyModifierPercentage = (int)(InventoryDialogue.getInventoryNPC().getBuyModifier()*100);
					Colour buyColour = buyModifierPercentage<=50?PresetColour.GENERIC_BAD:(buyModifierPercentage<100?PresetColour.GENERIC_MINOR_BAD:PresetColour.GENERIC_MINOR_GOOD);
					
					tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
										+ "Sell price: "+UtilText.formatAsMoney(absItem.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier()))
										+ " (<span style='color:"+buyColour.toWebHexString()+";'>"+buyModifierPercentage+"%</span>)"
									+ "</div>");
				} else {
					tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
										+ UtilText.formatAsMoney(absItem.getValue())
									+ "</div>");
				}
			} else {
				if (InventoryDialogue.isBuyback()) {
					tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
											+ "Buy-back price: "+UtilText.formatAsMoney(getBuybackPriceFor(absItem))
									+ "</div>");
				} else {
					int sellModifierPercentage = (int)(InventoryDialogue.getInventoryNPC().getSellModifier(absItem)*100);
					Colour sellColour = sellModifierPercentage>=200?PresetColour.GENERIC_BAD:(sellModifierPercentage>100?PresetColour.GENERIC_MINOR_BAD:PresetColour.GENERIC_MINOR_GOOD);
					
					tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
											+ "Buy price: "+UtilText.formatAsMoney(absItem.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier(absItem)))
											+ " (<span style='color:"+sellColour.toWebHexString()+";'>"+sellModifierPercentage+"%</span>)"
									+ "</div>");
				}
			}
		} else {
			tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
						+ UtilText.formatAsMoney(absItem.getValue())
					+ "</div>");
		}

		// Rarity box (half-width title):
		tooltipSB.append("<div class='container-half-width titular' style='margin:2px 8px 2px 2px; width:calc(50% - 10px);'>"
							+ "<span style='color:" + absItem.getRarity().getColour().toWebHexString() + ";'>"
									+Util.capitaliseSentence(absItem.getRarity().getName())
								+"</span>"
						+ "</div>");
		
		

		// Main description box & image:
		tooltipSB.append("<div class='container-full-width' style='padding:0; min-height:106px;'>");
			tooltipSB.append("<div class='container-half-width' style='width:calc(100% - 16px); font-weight:normal; text-align:left; max-height:140px;'>");
			
			// Picture:
			tooltipSB.append("<div class='item-image' style='float:right;'>"
								+ "<div class='item-image-content'>"
									+ absItem.getSVGString()
								+ "</div>"
							+ "</div>");
			
			tooltipSB.append(absItem.getTypeDescription(owner));
			tooltipSB.append("</div>");
		tooltipSB.append("</div>");
		
		
		// Core info:
		StringBuilder effectsSB = new StringBuilder();
		if(absItem.isAppendItemEffectLinesToTooltip()) {
			String effectEntry = "";
			int effectMulti = 0;
			for(int it = 0; it<absItem.getEffects().size(); it++) {
				ItemEffect ie = absItem.getEffects().get(it);
				StringBuilder effectSB = new StringBuilder();
				for(int i=0; i<ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).size(); i++) {
					yIncrease++;
					if(effectSB.length()>0) {
						effectSB.append("<br/>");
					}
					effectSB.append(ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer()).get(i));
				}
	
				effectEntry = effectSB.toString();
				if(it==absItem.getEffects().size()-1 || !absItem.getEffects().get(it+1).equals(ie)) {
					if(effectsSB.length()>0) {
						effectsSB.append("<br/>");
					}
					if(effectMulti>0) {
						effectsSB.append("[style.colourArcane(x"+(effectMulti+1)+")] ");
						yIncrease-=effectMulti;
					}
					effectsSB.append(effectEntry);
					effectMulti = 0;
					
				} else {
					effectMulti++;
				}
			}
		}
		for(String s : absItem.getEffectTooltipLines()) {
			yIncrease++;
			if(effectsSB.length()>0) {
				effectsSB.append("<br/>");
			}
			effectsSB.append(s);
		}

		if(effectsSB.length()>0) {
			specialYIncrease += 16;
			tooltipSB.append("<div class='container-full-width titular' style='font-weight:normal;'>");
				tooltipSB.append(effectsSB.toString());
			tooltipSB.append("</div>");
		}
		
		
		
		// Extra descriptions:
		List<String> extraDescriptions = absItem.getExtraDescriptions(equippedToCharacter);

		if(!extraDescriptions.isEmpty()) {
			specialYIncrease += 16;
			tooltipSB.append("<div class='container-full-width titular' style='font-weight: normal;'>");
				for(int i=0; i<extraDescriptions.size();i++) {
					yIncrease++;
					if(i>0) {
						tooltipSB.append("<br/>");
					}
					tooltipSB.append(extraDescriptions.get(i));
					i++;
				}
			tooltipSB.append("</div>");
		}

		String author = absItem.getItemType().getAuthorDescription();
		if(!author.isEmpty()) {
			int authorDivHeight = 48;
			specialYIncrease += 48 + 8;
			tooltipSB.append("<div class='description' style='margin:4px 8px; min-height:"+authorDivHeight+"px; max-height:"+authorDivHeight+"px;'>" + author + "</div>");
		}
		
		tooltipSB.append("</body>");

		String fullNameWithHtmlTags = absItem.getDisplayName(true);
		if(fullNameWithHtmlTags.replaceAll("<[^>]*>", "").length()>45) {
			specialYIncrease += 26;
		}
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH+80, 272 + (yIncrease * 18) + specialYIncrease);
		Main.mainController.setTooltipContent(UtilText.parse(equippedToCharacter==null?Main.game.getPlayer():equippedToCharacter, absItem, tooltipSB.toString()));
	}

	private void weaponTooltip(AbstractWeapon absWeapon) {
		int specialYIncrease = 0;
		
		int yIncrease = 0;
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitaliseSentence(absWeapon.getDisplayName(true)) + "</h5></div>");

		// Ranged/melee box (half-width title):
		tooltipSB.append("<div class='container-half-width titular' style='margin:2px 2px 2px 8px; width:calc(50% - 10px);'>"
								+ (absWeapon.getWeaponType().isTwoHanded()? "Two-handed" : "One-handed")
								+ (absWeapon.getWeaponType().isOneShot()?" [style.colourYellow(One-shot)]":"")
						+ "</div>");

		// Rarity box (half-width title):
		tooltipSB.append("<div class='container-half-width titular' style='margin:2px 8px 2px 2px; width:calc(50% - 10px);'>"
							+ "<span style='color:" + absWeapon.getRarity().getColour().toWebHexString() + ";'>"
									+Util.capitaliseSentence(absWeapon.getRarity().getName())
								+"</span>"
						+ "</div>");
		
		// Damage type box (half-width title):
		tooltipSB.append("<div class='container-half-width titular' style='margin:2px 2px 2px 8px; width:calc(50% - 10px); color:"+absWeapon.getDamageType().getMultiplierAttribute().getColour().toWebHexString()+";'>"
					+ (absWeapon.getWeaponType().isUsingUnarmedCalculation()
							?"[style.colourUnarmed(Unarmed)]"
									:(absWeapon.getWeaponType().isMelee()
										?"[style.colourMelee(Melee)]"
										:"[style.colourRanged(Ranged)]"))
					+ " "+ Util.capitaliseSentence(absWeapon.getDamageType().getName()) + " damage"
				+ "</div>");
		
		// Set box (half-width title):
		tooltipSB.append("<div class='container-half-width titular' style='margin:2px 8px 2px 2px; width:calc(50% - 10px);'>"
							+ (absWeapon.getWeaponType().getClothingSet() == null
								? "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>Not part of a set</span>"
								: "<span style='color:" + PresetColour.RARITY_EPIC.toWebHexString() + ";'>"+absWeapon.getWeaponType().getClothingSet().getName() + " set</span>")
						+ "</div>");
		
		// Value:
		String valueDivClass = Main.game.isEnchantmentCapacityEnabled()?"container-half-width titular":"container-full-width titular";
		String valueStyle = Main.game.isEnchantmentCapacityEnabled()
				?"style='margin:2px 2px 2px 8px; width:calc(50% - 10px);'"
				:"";
		if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			if (owner.isPlayer()) {
				if (InventoryDialogue.getInventoryNPC().willBuy(absWeapon)) {
					int buyModifierPercentage = (int)(InventoryDialogue.getInventoryNPC().getBuyModifier()*100);
					Colour buyColour = buyModifierPercentage<=50?PresetColour.GENERIC_BAD:(buyModifierPercentage<100?PresetColour.GENERIC_MINOR_BAD:PresetColour.GENERIC_MINOR_GOOD);
					
					tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
										+ "Sell price: "+UtilText.formatAsMoney(absWeapon.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier()))
										+ " (<span style='color:"+buyColour.toWebHexString()+";'>"+buyModifierPercentage+"%</span>)"
									+ "</div>");
				} else {
					tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
										+ UtilText.formatAsMoney(absWeapon.getValue())
									+ "</div>");
				}
			} else {
				if (InventoryDialogue.isBuyback() && !owner.hasWeaponEquipped(absWeapon)) {
					tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
											+ "Buy-back price: "+UtilText.formatAsMoney(getBuybackPriceFor(absWeapon))
									+ "</div>");
				} else {
					int sellModifierPercentage = (int)(InventoryDialogue.getInventoryNPC().getSellModifier(absWeapon)*100);
					Colour sellColour = sellModifierPercentage>=200?PresetColour.GENERIC_BAD:(sellModifierPercentage>100?PresetColour.GENERIC_MINOR_BAD:PresetColour.GENERIC_MINOR_GOOD);
					
					tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
											+ "Buy price: "+UtilText.formatAsMoney(absWeapon.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier(absWeapon)))
											+ " (<span style='color:"+sellColour.toWebHexString()+";'>"+sellModifierPercentage+"%</span>)"
									+ "</div>");
				}
			}
		} else {
			tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
						+ UtilText.formatAsMoney(absWeapon.getValue())
					+ "</div>");
		}
		
		// Enchantment instability
		if(Main.game.isEnchantmentCapacityEnabled()) {
			int enchCapacityCost = absWeapon.getEnchantmentCapacityCost();
			tooltipSB.append(
					"<div class='container-half-width titular' style='margin:2px 8px 2px 2px; width:calc(50% - 10px);'>"
							+(enchCapacityCost==0
								?"[style.colourDisabled("+UtilText.formatAsEnchantmentCapacityUncoloured(enchCapacityCost, "b")+")]"
								:UtilText.formatAsEnchantmentCapacity(enchCapacityCost, "b"))
					+ "</div>");
		}
		
		// Main description box & image:
		tooltipSB.append("<div class='container-full-width' style='padding:0; min-height:106px;'>");
			tooltipSB.append("<div class='container-half-width' style='width:calc(100% - 16px); font-weight:normal; text-align:left; max-height:140px;'>");
			
			// Picture:
			tooltipSB.append("<div class='item-image' style='float:right;'>"
								+ "<div class='item-image-content'>"
									+ ((owner!=null && owner.hasWeaponEquipped(absWeapon))
											?absWeapon.getSVGEquippedString(owner)
											:absWeapon.getSVGString())
								+ "</div>"
							+ "</div>");
			
			tooltipSB.append(absWeapon.getTypeDescription(owner));
			tooltipSB.append("</div>");
		tooltipSB.append("</div>");

		if(owner!=null && owner.getEssenceCount()<absWeapon.getWeaponType().getArcaneCost()) {
			yIncrease+=2;
			tooltipSB.append("<div class='container-full-width titular'>"
								+ "[style.colourBad(Not enough essences to fire!)]"
							+ "</div>");
		}
		
		
		// Attribute modifiers:
		StringBuilder effectsSB = new StringBuilder();
		float res = absWeapon.getWeaponType().getPhysicalResistance();
		if(res>0) {
			yIncrease++;
			effectsSB.append("[style.colourGood(+"+res+")] Natural [style.colourResPhysical("+Util.capitaliseSentence(Attribute.RESISTANCE_PHYSICAL.getName())+")]<br/>");
		}
		int cost = absWeapon.getWeaponType().getArcaneCost();
		if(cost>0) {
			yIncrease++;
			effectsSB.append("Costs [style.boldArcane("+cost+" Arcane essence"+(cost>1?"s":"")+")] "+(absWeapon.getWeaponType().isMelee()?"per attack":"to fire")+"<br/>");
		}
		if(equippedToCharacter != null) {
			if(absWeapon.getWeaponType().isUsingUnarmedCalculation()) {
				yIncrease++;
				effectsSB.append("Includes [style.colourUnarmed("+equippedToCharacter.getUnarmedDamage()+" unarmed damage)]<br/>");
			}
			yIncrease++;
			effectsSB.append("<span>"+ Attack.getMinimumDamage(equippedToCharacter, null, Attack.MAIN, absWeapon) + " - " + Attack.getMaximumDamage(equippedToCharacter, null, Attack.MAIN, absWeapon)+ "</span>"
					+ " <span style='color:" + absWeapon.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</span>");
			
			for(Value<Integer, Integer> aoe : absWeapon.getWeaponType().getAoeDamage()) {
				yIncrease++;
				int aoeChance = aoe.getKey();
				effectsSB.append("<br/>[style.colourAqua(AoE)]: "
						+ "(<span style='color:"
							+(aoeChance<=25
								?PresetColour.GENERIC_BAD
								:(aoeChance<=50
									?PresetColour.GENERIC_MINOR_BAD
									:(aoeChance<=75
										?PresetColour.GENERIC_MINOR_GOOD
										:PresetColour.GENERIC_GOOD))).toWebHexString()+";'>"
							+aoeChance+"%</span>): "
						+ "<span>"+ Attack.getMinimumDamage(equippedToCharacter, null, Attack.MAIN, absWeapon, aoe.getValue()) + " - " + Attack.getMaximumDamage(equippedToCharacter, null, Attack.MAIN, absWeapon, aoe.getValue())+ "</span>"
						+ " <span style='color:" + absWeapon.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</span>");
			}
			
		} else {
			if(owner!=null && !owner.isPlayer()) {
				yIncrease++;
				effectsSB.append(UtilText.parse(owner, "[npc.Name]: ")
					+"<span>"
						+ Attack.getMinimumDamage(owner, null, Attack.MAIN, absWeapon) + " - " + Attack.getMaximumDamage(owner, null, Attack.MAIN, absWeapon)
					+ "</span>"
					+ " <span style='color:" + absWeapon.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</span>");
				for(Value<Integer, Integer> aoe : absWeapon.getWeaponType().getAoeDamage()) {
					yIncrease++;
					int aoeChance = aoe.getKey();
					effectsSB.append("<br/>[style.colourAqua(AoE)]: "
							+ "(<span style='color:"
								+(aoeChance<=25
									?PresetColour.GENERIC_BAD
									:(aoeChance<=50
										?PresetColour.GENERIC_MINOR_BAD
										:(aoeChance<=75
											?PresetColour.GENERIC_MINOR_GOOD
											:PresetColour.GENERIC_GOOD))).toWebHexString()+";'>"
								+aoeChance+"%</span>): "
							+ "<span>"+ Attack.getMinimumDamage(owner, null, Attack.MAIN, absWeapon, aoe.getValue()) + " - " + Attack.getMaximumDamage(owner, null, Attack.MAIN, absWeapon, aoe.getValue())+ "</span>"
							+ " <span style='color:" + absWeapon.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</span>");
				}
				effectsSB.append("<br/>You: ");
			}
			yIncrease++;
			effectsSB.append("<span>"+ Attack.getMinimumDamage(Main.game.getPlayer(), null, Attack.MAIN, absWeapon) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), null, Attack.MAIN, absWeapon)+ "</span>"
							+ " <span style='color:" + absWeapon.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</span>");
			
			for(Value<Integer, Integer> aoe : absWeapon.getWeaponType().getAoeDamage()) {
				yIncrease++;
				int aoeChance = aoe.getKey();
				effectsSB.append("<br/>[style.colourAqua(AoE)] "
						+ "(<span style='color:"
							+(aoeChance<=25
								?PresetColour.GENERIC_BAD
								:(aoeChance<=50
									?PresetColour.GENERIC_MINOR_BAD
									:(aoeChance<=75
										?PresetColour.GENERIC_MINOR_GOOD
										:PresetColour.GENERIC_GOOD))).toWebHexString()+";'>"
							+aoeChance+"%</span>): "
						+ "<span>"+ Attack.getMinimumDamage(Main.game.getPlayer(), null, Attack.MAIN, absWeapon, aoe.getValue()) + " - " + Attack.getMaximumDamage(Main.game.getPlayer(), null, Attack.MAIN, absWeapon, aoe.getValue())+ "</span>"
						+ " <span style='color:" + absWeapon.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>Damage</span>");
			}
		}
		if(absWeapon.getWeaponType().isOneShot()) {
			yIncrease++;
			yIncrease++;
			int chanceToRecoverTurn = (int)absWeapon.getWeaponType().getOneShotChanceToRecoverAfterTurn();
			int chanceToRecoverCombat = (int)absWeapon.getWeaponType().getOneShotChanceToRecoverAfterCombat();

			effectsSB.append("<br/><span style='color:"
					+(chanceToRecoverTurn<=25?PresetColour.GENERIC_BAD:(chanceToRecoverTurn<=50?PresetColour.GENERIC_MINOR_BAD:(chanceToRecoverTurn<=75?PresetColour.GENERIC_MINOR_GOOD:PresetColour.GENERIC_GOOD))).toWebHexString()
					+"'>"+chanceToRecoverTurn+"%</span> recovery [style.colourBlueLight(after use)]<br/>");
			
			effectsSB.append("<span style='color:"
					+(chanceToRecoverCombat<=25?PresetColour.GENERIC_BAD:(chanceToRecoverCombat<=50?PresetColour.GENERIC_MINOR_BAD:(chanceToRecoverCombat<=75?PresetColour.GENERIC_MINOR_GOOD:PresetColour.GENERIC_GOOD))).toWebHexString()
					+";'>"+chanceToRecoverCombat+"%</span> recovery [style.colourCombat(after combat)]");
		}
		for(String s : absWeapon.getWeaponType().getExtraEffects()) {
			yIncrease++;
			effectsSB.append("<br/><span>"+s+"</span>");
		}
		for(Entry<AbstractAttribute, Integer> entry : absWeapon.getAttributeModifiers().entrySet()) {
			yIncrease++;
			effectsSB.append("<br/><span>"+entry.getKey().getFormattedValue(entry.getValue())+"</span>");
		}
		for(Spell s : absWeapon.getSpells()) {
			yIncrease++;
			effectsSB.append("<br/>[style.colourSpell(Spell)]: <span style='color:"+s.getSpellSchool().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(s.getName())+"</span>");
		}
		for(AbstractCombatMove cm : absWeapon.getCombatMoves()) {
			yIncrease++;
			effectsSB.append("<br/>[style.colourCombat(Move)]: "+Util.capitaliseSentence(cm.getName(0, Main.game.getPlayer())));
		}
		
		if(effectsSB.length()>0) {
			specialYIncrease += 16;
			tooltipSB.append("<div class='container-full-width titular' style='font-weight:normal;'>");
				tooltipSB.append(effectsSB.toString());
			tooltipSB.append("</div>");
		}
		
		
		// Extra descriptions:
		StringBuilder extraDescriptionsSB = new StringBuilder();
		List<String> extraDescriptions = absWeapon.getExtraDescriptions(equippedToCharacter);
		if(!extraDescriptions.isEmpty()) {
			for(int i=0; i<extraDescriptions.size();i++) {
				yIncrease++;
				if(i>0) {
					extraDescriptionsSB.append("<br/>");
				}
				extraDescriptionsSB.append(extraDescriptions.get(i));
			}
		}
		if(extraDescriptionsSB.length()>0) {
			specialYIncrease += 16;
			tooltipSB.append("<div class='container-full-width titular' style='font-weight:normal;'>");
				tooltipSB.append(extraDescriptionsSB.toString());
			tooltipSB.append("</div>");
		}
		
		String author = absWeapon.getWeaponType().getAuthorDescription();
		if(!author.isEmpty()) {
			int authorDivHeight = 48;
			specialYIncrease += 48 + 8;
			tooltipSB.append("<div class='description' style='margin:4px 8px; min-height:"+authorDivHeight+"px; max-height:"+authorDivHeight+"px;'>" + author + "</div>");
		}
		
		tooltipSB.append("</body>");

		String fullNameWithHtmlTags = absWeapon.getDisplayName(false);
		if(fullNameWithHtmlTags.replaceAll("<[^>]*>", "").length()>45) {
			specialYIncrease += 26;
		}
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH+80, 300 + (yIncrease * 18) + specialYIncrease);
		Main.mainController.setTooltipContent(UtilText.parse(equippedToCharacter==null?Main.game.getPlayer():equippedToCharacter, absWeapon, tooltipSB.toString()));
		
	}

	private void clothingTooltip(AbstractClothing absClothing) {
		InventorySlot slotEquippedTo = absClothing.getSlotEquippedTo();
		int specialYIncrease = 0;
		
		int yIncrease = 0;
		if(absClothing.isEnchantmentKnown()) {
			yIncrease += absClothing.getAttributeModifiers().size();
		}
		float resistance = absClothing.getClothingType().getPhysicalResistance();
		if(resistance>0) {
			yIncrease++;
		}
		if(absClothing.isEnchantmentKnown()) {
			for(ItemEffect ie : absClothing.getEffects()) {
				if(ie.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE && ie.getPrimaryModifier()!=TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
					yIncrease += ie.getEffectsDescription(owner, equippedToCharacter).size();
				}
			}
		} else {
			yIncrease++;
		}

		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>");
			tooltipSB.append("<div class='container-full-width center'>");
				tooltipSB.append("<h5>" + Util.capitaliseSentence(absClothing.getDisplayName(true)) + "</h5>");
			tooltipSB.append("</div>");

		// Femininity box (half-width title):
		Femininity femininityRestriction = absClothing.getClothingType().getFemininityRestriction();
		tooltipSB.append("<div class='container-half-width titular' style='margin:2px 2px 2px 8px; width:calc(50% - 10px);'>"
							+ (femininityRestriction==null || femininityRestriction==Femininity.ANDROGYNOUS
									?"[style.colourAndrogynous(Unisex)]"
											:(femininityRestriction.isFeminine()
												?"[style.colourFeminine(Feminine)]"
												:"[style.colourMasculine(Masculine)]"))
						+ "</div>");

		// Rarity box (half-width title):
		tooltipSB.append("<div class='container-half-width titular' style='margin:2px 8px 2px 2px; width:calc(50% - 10px);'>"
							+ "<span style='color:" + (absClothing.isEnchantmentKnown()?absClothing.getRarity().getColour():PresetColour.TEXT_GREY).toWebHexString() + ";'>"
									+Util.capitaliseSentence(absClothing.isEnchantmentKnown()?absClothing.getRarity().getName():"Unknown")
									+"</span>"
						+ "</div>");
		
		// Slot box (half-width title):
		tooltipSB.append("<div class='container-half-width titular' style='margin:2px 2px 2px 8px; width:calc(50% - 10px);'>");
			boolean nonPiercingSlots = absClothing.getClothingType().getEquipSlots().stream().anyMatch(is->!is.isJewellery());
			List<InventorySlot> possibleSlots = new ArrayList<>(absClothing.getClothingType().getEquipSlots());
			if(nonPiercingSlots) {
				possibleSlots.sort((is1, is2)->slotEquippedTo==is1?1:(slotEquippedTo==is2?-1:0));
			}
			for(int i=0; i<possibleSlots.size(); i++) {
				InventorySlot slot = possibleSlots.get(i);
				boolean equipped = slotEquippedTo == slot;
				if(nonPiercingSlots) { // Slots contain non-piercing slots
					tooltipSB.append(
							(equipped || slotEquippedTo==null
								?Util.capitaliseSentence(slot.getName())
								:"[style.colourDisabled("+Util.capitaliseSentence(slot.getName())+")]")
							+(i==possibleSlots.size()-1
								?""
								:(slotEquippedTo!=null
									?"[style.colourDisabled(/)]"
									:"/")));
				} else { // Slots are all piercings, so to abbreviate the slot names, the ' piercing' parts can all be removed, then a final ' piercing' can be appended at the end
					String slotName = slot.getName().replace(" piercing", "");
					tooltipSB.append(
							(equipped || slotEquippedTo==null
								?Util.capitaliseSentence(slotName)
								:"[style.colourDisabled("+Util.capitaliseSentence(slotName)+")]")
							+(i==possibleSlots.size()-1
								?""
								:(slotEquippedTo!=null
									?"[style.colourDisabled(/)]"
									:"/")));
					if(i==possibleSlots.size()-1) {
						tooltipSB.append(" piercing");
					}
				}
			}
		tooltipSB.append("</div>");
		
		// Set box (half-width title):
		tooltipSB.append("<div class='container-half-width titular' style='margin:2px 8px 2px 2px; width:calc(50% - 10px);'>"
							+ (absClothing.getClothingType().getClothingSet() == null
								? "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>Not part of a set</span>"
								: "<span style='color:" + PresetColour.RARITY_EPIC.toWebHexString() + ";'>"+absClothing.getClothingType().getClothingSet().getName() + " set</span>")
						+ "</div>");
		
		// Value:
		String valueDivClass = Main.game.isEnchantmentCapacityEnabled()?"container-half-width titular":"container-full-width titular";
		String valueStyle = Main.game.isEnchantmentCapacityEnabled()
				?"style='margin:2px 2px 2px 8px; width:calc(50% - 10px);'"
				:"";
		if (InventoryDialogue.getInventoryNPC() != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			if (owner.isPlayer()) {
				if (InventoryDialogue.getInventoryNPC().willBuy(absClothing)) {
					int buyModifierPercentage = (int)(InventoryDialogue.getInventoryNPC().getBuyModifier()*100);
					Colour buyColour = buyModifierPercentage<=50?PresetColour.GENERIC_BAD:(buyModifierPercentage<100?PresetColour.GENERIC_MINOR_BAD:PresetColour.GENERIC_MINOR_GOOD);
					
					tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
										+ "Sell price: "+UtilText.formatAsMoney(absClothing.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier()))
										+ " (<span style='color:"+buyColour.toWebHexString()+";'>"+buyModifierPercentage+"%</span>)"
									+ "</div>");
				} else {
					tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
										+ (absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : UtilText.formatAsMoney("?", "b"))
									+ "</div>");
				}
			} else {
				if (InventoryDialogue.isBuyback() && !owner.getClothingCurrentlyEquipped().contains(absClothing)) {
					tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
											+ "Buy-back price: "+(absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(getBuybackPriceFor(absClothing)) : UtilText.formatAsMoney("?", "b"))
									+ "</div>");
				} else {
					int sellModifierPercentage = (int)(InventoryDialogue.getInventoryNPC().getSellModifier(absClothing)*100);
					Colour sellColour = sellModifierPercentage>=200?PresetColour.GENERIC_BAD:(sellModifierPercentage>100?PresetColour.GENERIC_MINOR_BAD:PresetColour.GENERIC_MINOR_GOOD);
					
					tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
											+ "Buy price: "+UtilText.formatAsMoney(absClothing.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier(absClothing)))
											+ " (<span style='color:"+sellColour.toWebHexString()+";'>"+sellModifierPercentage+"%</span>)"
									+ "</div>");
				}
			}
		} else {
			tooltipSB.append("<div class='"+valueDivClass+"' "+valueStyle+">"
						+ (absClothing.isEnchantmentKnown() ? UtilText.formatAsMoney(absClothing.getValue()) : UtilText.formatAsMoney("?", "b"))
					+ "</div>");
		}
		
		// Enchantment instability
		if(Main.game.isEnchantmentCapacityEnabled()) {
			int enchCapacityCost = absClothing.getEnchantmentCapacityCost();
			tooltipSB.append(
					"<div class='container-half-width titular' style='margin:2px 8px 2px 2px; width:calc(50% - 10px);'>"
							+(absClothing.isEnchantmentKnown() ?
//								Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+": " +
									(enchCapacityCost==0
										?"[style.colourDisabled("+UtilText.formatAsEnchantmentCapacityUncoloured(enchCapacityCost, "b")+")]"
										:UtilText.formatAsEnchantmentCapacity(enchCapacityCost, "b"))
							: "[style.boldDisabled("+UtilText.getEnchantmentCapacitySymbolUncoloured()+"?)]")
					+ "</div>");
		}
		
		// Main description box & image:
		tooltipSB.append("<div class='container-full-width' style='padding:0; min-height:106px;'>");
			tooltipSB.append("<div class='container-half-width' style='width:calc(100% - 16px); font-weight:normal; text-align:left; max-height:140px;'>");
				// Picture:
				tooltipSB.append("<div class='item-image' style='float:right;'>"
									+ "<div class='item-image-content'>"
										+ (owner!=null && owner.getClothingCurrentlyEquipped().contains(absClothing)?absClothing.getSVGEquippedString(owner):absClothing.getSVGString())
									+ "</div>"
								+ "</div>");
				tooltipSB.append(absClothing.getTypeDescription(owner));
			tooltipSB.append("</div>");
		tooltipSB.append("</div>");
		
		
		// Effects list:
		StringBuilder effectsSB = new StringBuilder();
		boolean brNeeded = false;
		if(resistance>0) {
			effectsSB.append("[style.colourGood(+"+resistance+")] Natural [style.colourResPhysical("+Util.capitaliseSentence(Attribute.RESISTANCE_PHYSICAL.getName())+")]");
			brNeeded = true;
		}
		
		if(!absClothing.getEffects().isEmpty()) {
			if (!absClothing.isEnchantmentKnown()) {
				effectsSB.append((brNeeded?"<br/>":"")+"[style.colourDisabled(Unidentified effects!)]");
				brNeeded = true;
			} else {
				for(Entry<AbstractAttribute, Integer> entry : absClothing.getAttributeModifiers().entrySet()) {
					effectsSB.append((brNeeded?"<br/>":"")+entry.getKey().getFormattedValue(entry.getValue()));
					brNeeded = true;
				}
				for (ItemEffect e : absClothing.getEffects()) {
					if(e.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE && e.getPrimaryModifier()!=TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
						for(String s : e.getEffectsDescription(owner, owner)) {
							effectsSB.append((brNeeded?"<br/>":"")+ s);
							brNeeded = true;
						}
					}
				}
			}
		}
		
		// Extra descriptions:
		StringBuilder extraDescriptionsSB = new StringBuilder();
		List<String> extraDescriptions = new ArrayList<>();
		if(slotEquippedTo==null && absClothing.getClothingType().getEquipSlots().size()>1) {
			if(!absClothing.getExtraDescriptions(equippedToCharacter, null, false).isEmpty()) {
				for (String s : absClothing.getExtraDescriptions(equippedToCharacter, null, false)) {
					extraDescriptions.add(s);
					yIncrease++;
				}
			}
			for(int i=0; i<absClothing.getClothingType().getEquipSlots().size();i++) {
				InventorySlot slot = absClothing.getClothingType().getEquipSlots().get(i);
				
				if(!absClothing.getExtraDescriptions(equippedToCharacter, slot, false).isEmpty()) {
					extraDescriptions.add("<i>When equipped into '"+slot.getName()+"' slot:</i>");
					yIncrease++;
					for (String s : absClothing.getExtraDescriptions(equippedToCharacter, slot, false)) {
						extraDescriptions.add(s);
						yIncrease++;
					}
				}
			}
			
		} else {
			if(slotEquippedTo!=null) {
				if(!absClothing.getExtraDescriptions(equippedToCharacter, null, false).isEmpty()) {
					for (String s : absClothing.getExtraDescriptions(equippedToCharacter, null, false)) {
						extraDescriptions.add(s);
						yIncrease++;
					}
				}
			}
			if(!absClothing.getExtraDescriptions(equippedToCharacter, slotEquippedTo, false).isEmpty()) {
				for (String s : absClothing.getExtraDescriptions(equippedToCharacter, slotEquippedTo, false)) {
					extraDescriptions.add(s);
					yIncrease++;
				}
			}
		}
		if(!extraDescriptions.isEmpty()) {
			for(int i=0; i<extraDescriptions.size();i++) {
				if(i>0) {
					extraDescriptionsSB.append("<br/>");
				}
				extraDescriptionsSB.append(extraDescriptions.get(i));
			}
		}
		
		if(effectsSB.length()>0) {
			specialYIncrease += 16;
			
//			String background = "background: linear-gradient(0.25turn, "
//				+PresetColour.GENERIC_ARCANE.toWebHexString()
//				+", "+PresetColour.BACKGROUND_DARK.toWebHexString()+" 5%, "
//				+PresetColour.BACKGROUND_DARK.toWebHexString()+" 95%, "
//				+PresetColour.GENERIC_ARCANE.toWebHexString()+");";
			
			String background = "";//"box-shadow: 0 0 10px 2px "+PresetColour.GENERIC_ARCANE.toWebHexString()+";";
			
			tooltipSB.append("<div class='container-full-width titular' style='font-weight:normal;"+background+"'>");
				tooltipSB.append(effectsSB.toString());
			tooltipSB.append("</div>");
		}
		if(extraDescriptionsSB.length()>0) {
			specialYIncrease += 16;
			tooltipSB.append("<div class='container-full-width titular' style='font-weight:normal;'>");
				tooltipSB.append(extraDescriptionsSB.toString());
			tooltipSB.append("</div>");
		}

		String author = absClothing.getClothingType().getAuthorDescription();
		if(!author.isEmpty()) {
			int authorDivHeight = 48;
			specialYIncrease += 48 + 8;
			tooltipSB.append("<div class='description' style='margin:4px 8px; min-height:"+authorDivHeight+"px; max-height:"+authorDivHeight+"px;'>" + author + "</div>");
		}
		
		tooltipSB.append("</body>");

		String fullNameWithHtmlTags = absClothing.getDisplayName(false);
//		System.out.println(fullNameWithHtmlTags+" | "+fullNameWithHtmlTags.replaceAll("<[^>]*>", ""));
		if(fullNameWithHtmlTags.replaceAll("<[^>]*>", "").length()>45) {
			specialYIncrease += 26;
		}
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH+80, 300 + (yIncrease * 18) + specialYIncrease);
		Main.mainController.setTooltipContent(UtilText.parse(equippedToCharacter==null?Main.game.getPlayer():equippedToCharacter, absClothing, tooltipSB.toString()));

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
			tooltipSB.append(UtilText.parse(owner, "[npc.NamePos] ")+invSlot.getNameOfAssociatedPart(owner)+" "+(invSlot.isPlural(owner)?"have":"has")+" been marked by:");
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
		
//		if (tattoo.getWriting()!=null && !tattoo.getWriting().getText().isEmpty()) {
//			yIncrease++;
//			yIncrease += tattoo.getWriting().getText().split("<br/>").length - 1;
//		}
//		if (tattoo.getCounter()!=null && tattoo.getCounter().getType()!=TattooCounterType.NONE) {
//			yIncrease++;
//		}
//		int lSize=0;
//		for (ItemEffect e : tattoo.getEffects()) {
//			if(e.getPrimaryModifier()==TFModifier.CLOTHING_ATTRIBUTE
//					|| e.getPrimaryModifier()==TFModifier.CLOTHING_MAJOR_ATTRIBUTE
//					|| e.getPrimaryModifier()==TFModifier.TF_MOD_FETISH_BEHAVIOUR
//					|| e.getPrimaryModifier()==TFModifier.TF_MOD_FETISH_BODY_PART) {
//				lSize++;
//			} else {
//				lSize+=2;
//			}
//		}
//		lSize-=4;
//		if(lSize<0) {
//			lSize=0;
//		}
		
		// Title:
		tooltipSB.setLength(0);
		tooltipSB.append("<body>"
			+ "<div class='container-full-width center'><h5>" + Util.capitaliseSentence(tattoo.getDisplayName(true)) + "</h5></div>");

		// Scars (half-width title):
		tooltipSB.append("<div class='container-half-width titular' style='margin:2px 2px 2px 8px; width:calc(50% - 10px);'>"
							+ (owner==null || owner.getScarInSlot(invSlot)==null
									? "<span style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>No scars</span>"
									: "<span style='color:" + PresetColour.SCAR.toWebHexString() + ";'>"+Util.capitaliseSentence(owner.getScarInSlot(invSlot).getName())+"</span>")
						+ "</div>");

		// Rarity box (half-width title):
		tooltipSB.append("<div class='container-half-width titular' style='margin:2px 8px 2px 2px; width:calc(50% - 10px);'>"
							+ "<span style='color:" + tattoo.getRarity().getColour().toWebHexString() + ";'>"
									+Util.capitaliseSentence(tattoo.getRarity().getName())
								+"</span>"
						+ "</div>");

		// Slot (half-width title):
		String slotStyle = Main.game.isEnchantmentCapacityEnabled()
				?"style='margin:2px 2px 2px 8px; width:calc(50% - 10px);'"
				:"";
		tooltipSB.append("<div class='"+(Main.game.isEnchantmentCapacityEnabled()?"container-half-width titular":"container-full-width titular")+"' "+slotStyle+">" 
					+ (invSlot.getTattooSlotName()==null
						?"[style.colourDisabled(Cannot be tattooed)]"
						:Util.capitaliseSentence(invSlot.getTattooSlotName())) 
				+ "</div>");

		// Enchantment instability
		if(Main.game.isEnchantmentCapacityEnabled()) {
			int enchCapacityCost = tattoo.getEnchantmentCapacityCost();
			tooltipSB.append(
					"<div class='container-half-width titular' style='margin:2px 8px 2px 2px; width:calc(50% - 10px);'>"
							+(enchCapacityCost==0
								?"[style.colourDisabled("+UtilText.formatAsEnchantmentCapacityUncoloured(enchCapacityCost, "b")+")]"
								:UtilText.formatAsEnchantmentCapacity(enchCapacityCost, "b"))
					+ "</div>");
		}
		

		// Main description box & image:
		tooltipSB.append("<div class='container-full-width' style='padding:0; min-height:106px;'>");
			tooltipSB.append("<div class='container-half-width' style='width:calc(100% - 16px); font-weight:normal; text-align:left; max-height:140px;'>");
				// Picture:
				tooltipSB.append("<div class='item-image' style='float:right;'>"
									+ "<div class='item-image-content'>"
										+ tattoo.getSVGImage(
												equippedToCharacter==null
													?Main.game.getPlayer()
													:equippedToCharacter)
									+ "</div>"
								+ "</div>");
				tooltipSB.append(tattoo.getType().getDescription());
				
				StringBuilder writingSB = new StringBuilder();
				if (tattoo.getWriting()!=null && !tattoo.getWriting().getText().isEmpty()) {
					if(tattoo.getWriting().getStyles().isEmpty()) {
						writingSB.append("Normal,");
					} else {
						int i=0;
						for(TattooWritingStyle style : tattoo.getWriting().getStyles()) {
							writingSB.append(i==0?Util.capitaliseSentence(style.getName()):", "+style.getName());
							i++;
						}
					}
					writingSB.append(" "+tattoo.getWriting().getColour().getName()+" writing");
				}
				StringBuilder counterSB = new StringBuilder();
				if (tattoo.getCounter()!=null && tattoo.getCounter().getType()!=TattooCounterType.NONE) {
					counterSB.append("an enchanted, '"+tattoo.getCounter().getColour().getName()+" "+tattoo.getCounter().getType().getName()+"' counter");
				}
				if(writingSB.length()>0) {
					tooltipSB.append(" <i>");
					tooltipSB.append(writingSB.toString());
					if(counterSB.length()>0){
						tooltipSB.append(" and ");
						tooltipSB.append(counterSB.toString());
						tooltipSB.append(" form part of this tattoo.");
					} else {
						tooltipSB.append(" forms part of this tattoo.");
					}
					tooltipSB.append("</i>");
					
				} else if(counterSB.length()>0){
					tooltipSB.append(" <i>");
						tooltipSB.append(Util.capitaliseSentence(writingSB.toString()));
						tooltipSB.append(" forms part of this tattoo.");
					tooltipSB.append("</i>");
				}
				
			tooltipSB.append("</div>");
		tooltipSB.append("</div>");
		
		
		// Attribute modifiers:
		StringBuilder effectsSB = new StringBuilder();
		if (!tattoo.getEffects().isEmpty()) {
			for (ItemEffect e : tattoo.getEffects()) {
				if(e.getPrimaryModifier()!=TFModifier.CLOTHING_ATTRIBUTE && e.getPrimaryModifier()!=TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
					for(String s : e.getEffectsDescription(owner, owner)) {
						yIncrease++;
						if(effectsSB.length()>0) {
							effectsSB.append("<br/>");
						}
						effectsSB.append(s);
					}
				}
			}
			for(Entry<AbstractAttribute, Integer> entry : tattoo.getAttributeModifiers().entrySet()) {
				yIncrease++;
				if(effectsSB.length()>0) {
					effectsSB.append("<br/>");
				}
				effectsSB.append(entry.getKey().getFormattedValue(entry.getValue()));
			}
		}

		if(effectsSB.length()>0) {
			specialIncrease += 16;
			tooltipSB.append("<div class='container-full-width titular' style='font-weight:normal;'>");
				tooltipSB.append(effectsSB.toString());
			tooltipSB.append("</div>");
		}
		
		
		// Writing:
		if (tattoo.getWriting()!=null && !tattoo.getWriting().getText().isEmpty()) {
			specialIncrease += 16;
			yIncrease++;
			yIncrease++;
			int writingHeight = tattoo.getWriting().getText().split("<br/>").length;
			
			tooltipSB.append("<div class='container-full-width' style='padding:4px; height:"+(28 + writingHeight*LINE_HEIGHT)+"px; text-align:center;'>");
				tooltipSB.append("The writing reads:<br/>");
				tooltipSB.append(tattoo.getFormattedWritingOutput());
			tooltipSB.append("</div>");
		}
//		else {
//			tooltipSB.append(
//					"<div class='container-full-width' style='padding:4px; height:28px; text-align:center;'>"
//						+"[style.colourDisabled(This tattoo doesn't have any writing.)]"
//					+ "</div>");
//		}

		// Counter:
		if (tattoo.getCounter()!=null && tattoo.getCounter().getType()!=TattooCounterType.NONE) {
			specialIncrease += 16;
			yIncrease++;
			yIncrease++;
			tooltipSB.append("<div class='container-full-width' style='padding:4px; height:42px; text-align:center;'>"
								+ "The '"+tattoo.getCounter().getType().getName()+"' counter displays:"
									+ "<br/>"
									+ "<span style='color:"+tattoo.getCounter().getColour().toWebHexString()+";'>"
											+tattoo.getFormattedCounterOutput(
													equippedToCharacter==null
														?Main.game.getPlayer()
														:equippedToCharacter)
									+"</span>"
							+ "</div>");
		}
//		else {
//			tooltipSB.append(
//					"<div class='container-full-width' style='padding:8px; height:28px; text-align:center;'>"
//						+"[style.colourDisabled(This tattoo doesn't have a counter.)]"
//					+ "</div>");
//		}
		
		
		tooltipSB.append("</div>");

		if(owner!=null) {
			SizedStack<Covering> lipsticks = owner.getLipstickMarkingsInSlot(invSlot);
			if(lipsticks!=null) {
				specialIncrease += 16;
				yIncrease++;
				tooltipSB.append("<div class='container-full-width' style='text-align:center; padding:8px; height:"+(16+(1+lipsticks.size())*LINE_HEIGHT)+"px;'>");
				tooltipSB.append(UtilText.parse(owner, "[npc.NamePos] ")+invSlot.getTattooSlotName()+" "+(invSlot.isPlural(owner)?"have":"has")+" been marked by:");
					for(int i=lipsticks.size()-1; i>=0; i--) {
						yIncrease++;
						tooltipSB.append("<br/>"+Util.capitaliseSentence(lipsticks.get(i).getFullDescription(owner, true)));
					}
				tooltipSB.append("</div>");
			}
		}
		
		tooltipSB.append("</body>");


		String fullNameWithHtmlTags = tattoo.getDisplayName(true);
		if(fullNameWithHtmlTags.replaceAll("<[^>]*>", "").length()>45) {
			specialIncrease += 26;
		}
		Main.mainController.setTooltipSize(TOOLTIP_WIDTH+80, 272 + (yIncrease * 18) + specialIncrease);
		Main.mainController.setTooltipContent(UtilText.parse(equippedToCharacter==null?Main.game.getPlayer():equippedToCharacter, tooltipSB.toString()));
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
						+ "<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Blocked!</span>"
					+ "</div>"
					+"<div class='description' style='height:72px; text-align:center;'>"
						+ UtilText.parse(equippedToCharacter, block.getDescription())
					 +"</div>"));
		}
		
		Main.mainController.setTooltipContent(sb.toString());
	}
}
