package com.lilithsthrone.rendering;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.combat.SpecialAttack;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.World;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum RenderingEngine {
	ENGINE;
	
	private static boolean zoomedIn = true;
	private static boolean renderedDisabledMap = false;

	private static int pageLeft = 0;
	private static int pageRight = 0;

	private boolean renderingTattoosLeft = false;
	private boolean renderingTattoosRight = false;
	
	public static final int INVENTORY_PAGES = 5;
	public static final int ITEMS_PER_PAGE = 24;
	
	public static Colour[] orgasmColours = new Colour[]{Colour.AROUSAL_STAGE_ZERO, Colour.AROUSAL_STAGE_ONE, Colour.AROUSAL_STAGE_TWO, Colour.AROUSAL_STAGE_THREE, Colour.AROUSAL_STAGE_FOUR, Colour.AROUSAL_STAGE_FIVE, Colour.GENERIC_ARCANE};
	
	public static InventorySlot[] mainInventorySlots = {
			InventorySlot.EYES,			InventorySlot.HEAD,			InventorySlot.HAIR,		InventorySlot.HORNS,
			InventorySlot.NECK,			InventorySlot.TORSO_OVER,	InventorySlot.MOUTH,	InventorySlot.WINGS,
			InventorySlot.STOMACH,		InventorySlot.TORSO_UNDER,	InventorySlot.CHEST,	InventorySlot.NIPPLE,
			InventorySlot.WRIST,		InventorySlot.HIPS,			InventorySlot.HAND,		InventorySlot.FINGER,
			InventorySlot.ANKLE,		InventorySlot.LEG,			InventorySlot.GROIN,	InventorySlot.TAIL};

	public static InventorySlot[] secondaryInventorySlots = {InventorySlot.SOCK, InventorySlot.FOOT, InventorySlot.ANUS, InventorySlot.PENIS, InventorySlot.VAGINA};
	
//	public static InventorySlot[] mainInventorySlots = {
//			InventorySlot.EYES,			InventorySlot.HEAD,			InventorySlot.HAIR,		InventorySlot.MOUTH,
//			InventorySlot.NECK,			InventorySlot.TORSO_OVER,	InventorySlot.CHEST,	InventorySlot.NIPPLE,
//			InventorySlot.STOMACH,		InventorySlot.TORSO_UNDER,	InventorySlot.WRIST,	InventorySlot.HAND,
//			InventorySlot.HIPS,			InventorySlot.LEG,			InventorySlot.GROIN,	InventorySlot.FINGER,
//			InventorySlot.SOCK,			InventorySlot.FOOT,			InventorySlot.ANKLE,	InventorySlot.ANUS};
//
//	public static InventorySlot[] secondaryInventorySlots = {InventorySlot.HORNS, InventorySlot.TAIL, InventorySlot.WINGS, InventorySlot.PENIS, InventorySlot.VAGINA};
	
	private static InventorySlot[] piercingSlots = {
			InventorySlot.PIERCING_EAR,		InventorySlot.PIERCING_NOSE,
			InventorySlot.PIERCING_LIP,		InventorySlot.PIERCING_TONGUE,
			InventorySlot.PIERCING_NIPPLE,	InventorySlot.PIERCING_STOMACH,
			InventorySlot.PIERCING_PENIS,	InventorySlot.PIERCING_VAGINA };
	
	
	private RenderingEngine() {
	}

	private StringBuilder inventorySB = new StringBuilder(), equippedPanelSB = new StringBuilder();
	
	public String getInventoryPanel(GameCharacter charactersInventoryToRender, boolean buyback) {
		return "<div class='container-full-width' style=''background:#45aac1>"
					+getInventoryDiv(Main.game.getPlayer(), false) + getInventoryDiv(charactersInventoryToRender, buyback)
				+"</div>";
	}
	
	private String getInventoryEquippedPanel(GameCharacter charactersInventoryToRender) {
		
		equippedPanelSB.setLength(0);
		
		if(charactersInventoryToRender == null) { //TODO
			equippedPanelSB.append("<div class='inventory-equipped'>");

			for (InventorySlot invSlot : mainInventorySlots) {
				equippedPanelSB.append("<div class='inventory-item-slot' id='" + invSlot.toString() + "Slot'></div>");
			}
			
			equippedPanelSB.append("</div>"
					+ "<div class='inventory-equipped accessories'>");
			
			equippedPanelSB.append("<div class='inventory-item-slot weapon' id='" + InventorySlot.WEAPON_MAIN.toString() + "Slot'></div>");
			equippedPanelSB.append("<div class='inventory-item-slot weapon' id='" + InventorySlot.WEAPON_OFFHAND.toString() + "Slot'></div>");
			
			for (InventorySlot invSlot : piercingSlots) {
				equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
			}
			equippedPanelSB.append("</div>");
			

			equippedPanelSB.append("<div class='inventory-equipped' style='width:100%;'>");
			
			for (InventorySlot invSlot : secondaryInventorySlots) {
				equippedPanelSB.append("<div class='inventory-item-slot secondary' id='" + invSlot.toString() + "Slot'></div>");
			}
			
			equippedPanelSB.append("<div class='inventory-item-slot secondary'><div class='inventory-icon-content'>"
											+SVGImages.SVG_IMAGE_PROVIDER.getTattooSwitchClothing()
										+"</div>"
										+ "<div class='overlay-inventory disabled'></div></div>");

			equippedPanelSB.append("</div>");
			
			return equippedPanelSB.toString();
		}
		
		
		Set<InventorySlot> blockedSlots = new HashSet<>();
		
		for (AbstractClothing c : charactersInventoryToRender.getClothingCurrentlyEquipped()) {
			if (c.getClothingType().getIncompatibleSlots() != null) {
				for (InventorySlot is : c.getClothingType().getIncompatibleSlots()) {
					blockedSlots.add(is);
				}
			}
		}
		
		// EQUIPPED:
		equippedPanelSB.append("<div class='inventory-equipped'>");
		
		Map<InventorySlot, List<AbstractClothing>> concealedSlots = charactersInventoryToRender.getInventorySlotsConcealed();
		
		for (InventorySlot invSlot : mainInventorySlots) {
			appendEquippedClothingSlot(charactersInventoryToRender, invSlot, blockedSlots, concealedSlots, false);
		}

		equippedPanelSB.append("</div>");
		
		// Render weapons & piercings:
		equippedPanelSB.append("<div class='inventory-equipped accessories'>");
		
		// Main weapon:
		if (charactersInventoryToRender.getMainWeapon() != null) {
			equippedPanelSB.append(
					"<div class='inventory-item-slot weapon" + getClassRarityIdentifier(charactersInventoryToRender.getMainWeapon().getRarity()) + "'>"
						+ "<div class='inventory-icon-content'>"+charactersInventoryToRender.getMainWeapon().getSVGEquippedString(charactersInventoryToRender)+"</div>"
						+ "<div class='overlay-inventory' id='" + InventorySlot.WEAPON_MAIN.toString() + "Slot'></div>"
					+ "</div>");
		} else {
			equippedPanelSB.append("<div class='inventory-item-slot weapon' id='" + InventorySlot.WEAPON_MAIN.toString() + "Slot'></div>");
		}
		
		// Offhand weapon:
		if (charactersInventoryToRender.getOffhandWeapon() != null) {
			equippedPanelSB.append("<div class='inventory-item-slot weapon" + getClassRarityIdentifier(charactersInventoryToRender.getOffhandWeapon().getRarity()) + "'>"
						+ "<div class='inventory-icon-content'>"+charactersInventoryToRender.getOffhandWeapon().getSVGEquippedString(charactersInventoryToRender)+"</div>"
						+ "<div class='overlay-inventory' id='" + InventorySlot.WEAPON_OFFHAND.toString() + "Slot'></div>"
					+ "</div>");
		} else if (charactersInventoryToRender.getMainWeapon() != null && charactersInventoryToRender.getMainWeapon().getWeaponType().isTwoHanded()) {
			equippedPanelSB.append("<div class='inventory-item-slot weapon disabled' id='" + InventorySlot.WEAPON_OFFHAND.toString() + "Slot'></div>");
			
		} else {
			equippedPanelSB.append("<div class='inventory-item-slot weapon' id='" + InventorySlot.WEAPON_OFFHAND.toString() + "Slot'></div>");
		}
		
		//piercingSlots
		for (InventorySlot invSlot : piercingSlots) {
			
			AbstractClothing clothing = charactersInventoryToRender.getClothingInSlot(invSlot);
			
			if(!charactersInventoryToRender.isPlayer() && concealedSlots.keySet().contains(invSlot)) {
				equippedPanelSB.append("<div class='inventory-item-slot piercing concealed' id='" + invSlot.toString() + "Slot'>"
						+ "<div class='concealedIcon'>"+SVGImages.SVG_IMAGE_PROVIDER.getConcealedIcon()+"</div>"
					+ "</div>");
				
			} else {
				if (clothing != null) {
					// add to content:
					equippedPanelSB.append(
							// If slot is sealed:
							"<div class='inventory-item-slot piercing " + getClassRarityIdentifier(clothing.getRarity()) + "'"
								+ (clothing.isSealed() ? "style='border-width:2px; border-color:" + Colour.SEALED.toWebHexString() + "; border-style:solid;'" : "") + ">"
								
								// Picture:
								+ "<div class='inventory-icon-content'>"+clothing.getSVGEquippedString(charactersInventoryToRender)+"</div>"
								
								// If clothing is displaced:
								+ (!clothing.getDisplacedList().isEmpty() ? "<div class='displacedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getDisplacedIcon() + "</div>" : "")
								// If clothing is cummed in:
								+ (clothing.isDirty() ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
								// If clothing is too masculine:
								+ (clothing.getClothingType().getFemininityMaximum() < charactersInventoryToRender.getFemininityValue() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getMasculineWarningIcon() + "</div>" : "")
								// If clothing is too feminine:
								+ (clothing.getClothingType().getFemininityMinimum() > charactersInventoryToRender.getFemininityValue() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getFeminineWarningIcon() + "</div>" : "")
	
								+ "<div class='overlay-inventory' id='" + invSlot.toString() + "Slot'>" + "</div>" + "</div>");
				} else {
					// add to content:
					if (blockedSlots.contains(invSlot)) {
						equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
						
					} else {
						boolean bypassesPiercing = !charactersInventoryToRender.getBody().getBodyMaterial().isRequiresPiercing();
						
						switch(invSlot){
							case PIERCING_VAGINA:
								if(!charactersInventoryToRender.hasVagina() || (!bypassesPiercing && !charactersInventoryToRender.isPiercedVagina())) {
									equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
								} else {
									equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
								}
								break;
							case PIERCING_EAR:
								if(!bypassesPiercing && !charactersInventoryToRender.isPiercedEar()) {
									equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
								} else {
									equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
								}
								break;
							case PIERCING_LIP:
								if(!bypassesPiercing && !charactersInventoryToRender.isPiercedLip()) {
									equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
								} else {
									equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
								}
								break;
							case PIERCING_NIPPLE:
								if(!bypassesPiercing && !charactersInventoryToRender.isPiercedNipple()) {
									equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
								} else {
									equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
								}
								break;
							case PIERCING_NOSE:
								if(!bypassesPiercing && !charactersInventoryToRender.isPiercedNose()) {
									equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
								} else {
									equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
								}
								break;
							case PIERCING_PENIS:
								if(!charactersInventoryToRender.hasPenis() || (!bypassesPiercing && !charactersInventoryToRender.isPiercedPenis())) {
									equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
								} else {
									equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
								}
								break;
							case PIERCING_STOMACH:
								if(!bypassesPiercing && !charactersInventoryToRender.isPiercedNavel()) {
									equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
								} else {
									equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
								}
								break;
							case PIERCING_TONGUE:
								if(!bypassesPiercing && !charactersInventoryToRender.isPiercedTongue()) {
									equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
								} else {
									equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
								}
								break;
							default:
								break;
						}
					}
				}
			}
		}
		equippedPanelSB.append("</div>");
		
		
		equippedPanelSB.append("<div class='inventory-equipped' style='width:100%;'>");
		
		for (InventorySlot invSlot : secondaryInventorySlots) {
			appendEquippedClothingSlot(charactersInventoryToRender, invSlot, blockedSlots, concealedSlots, true);
		}
		
		equippedPanelSB.append("<div class='inventory-item-slot secondary'>");
		if(charactersInventoryToRender.isPlayer()) {
			equippedPanelSB.append("<div class='inventory-icon-content'>"
										+(this.isRenderingTattoosLeft()
											?SVGImages.SVG_IMAGE_PROVIDER.getTattooSwitchTattoo()
											:SVGImages.SVG_IMAGE_PROVIDER.getTattooSwitchClothing())
									+"</div>"
									+ "<div class='overlay-inventory' id='TATTOO_SWITCH_LEFT'></div>");
			
		} else {
			equippedPanelSB.append("<div class='inventory-icon-content'>"
										+(this.isRenderingTattoosRight()
												?SVGImages.SVG_IMAGE_PROVIDER.getTattooSwitchTattoo()
												:SVGImages.SVG_IMAGE_PROVIDER.getTattooSwitchClothing())
									+"</div>"
									+ "<div class='overlay-inventory' id='TATTOO_SWITCH_RIGHT'></div>");
		}
		equippedPanelSB.append("</div>");

		equippedPanelSB.append("</div>");
		
		return equippedPanelSB.toString();
	}
	
	private void appendEquippedClothingSlot(GameCharacter charactersInventoryToRender, InventorySlot invSlot, Set<InventorySlot> blockedSlots, Map<InventorySlot, List<AbstractClothing>> concealedSlots, boolean isSecondary) {
		String inventorySlotId = "inventory-item-slot";
		if(isSecondary) {
			inventorySlotId = "inventory-item-slot secondary";
		}
		
		if(!charactersInventoryToRender.isPlayer() && concealedSlots.keySet().contains(invSlot)) {
			equippedPanelSB.append("<div class='"+inventorySlotId+" concealed' id='" + invSlot.toString() + "Slot'>"
					+ "<div class='concealedIcon'>"+SVGImages.SVG_IMAGE_PROVIDER.getConcealedIcon()+"</div>"
				+ "</div>");
			
		} else {
			if((charactersInventoryToRender.isPlayer() && !this.isRenderingTattoosLeft())
					|| (!charactersInventoryToRender.isPlayer() && !this.isRenderingTattoosRight())) {
				AbstractClothing clothing = charactersInventoryToRender.getClothingInSlot(invSlot);
				
				if (clothing != null) {
					// add to content:
					equippedPanelSB.append(
							// If slot is sealed:
							"<div class='"+inventorySlotId + getClassRarityIdentifier(clothing.getRarity()) + "'"
								+ (clothing.isSealed() ? "style='border-width:2px; border-color:" + Colour.SEALED.toWebHexString() + "; border-style:solid;'" : "") + ">"
								
								// Picture:
								+ "<div class='inventory-icon-content'>"+clothing.getSVGEquippedString(charactersInventoryToRender)+"</div>"

//								+ (charactersInventoryToRender.getScarInSlot(invSlot)!=null ? "<div class='scarIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getScarIcon() + "</div>" : "")
								// If clothing is displaced:
								+ (!clothing.getDisplacedList().isEmpty() ? "<div class='displacedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getDisplacedIcon() + "</div>" : "")
								// If clothing is cummed in:
								+ (clothing.isDirty() ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
								// If clothing is too masculine:
								+ (clothing.getClothingType().getFemininityMaximum() < charactersInventoryToRender.getFemininityValue() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getMasculineWarningIcon() + "</div>" : "")
								// If clothing is too feminine:
								+ (clothing.getClothingType().getFemininityMinimum() > charactersInventoryToRender.getFemininityValue() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getFeminineWarningIcon() + "</div>" : "")
	
								+ "<div class='overlay-inventory' id='" + invSlot.toString() + "Slot'>" + "</div>"
							+ "</div>");
					
				} else {
					// add to content:
					if (blockedSlots.contains(invSlot)) {
						equippedPanelSB.append("<div class='"+inventorySlotId+" disabled'>"
													+ (charactersInventoryToRender.isDirtySlot(invSlot) ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
													+ "<div class='overlay' id='" + invSlot.toString() + "Slot'></div>"
												+ "</div>");
						
					} else if (invSlot.slotBlockedByRace(charactersInventoryToRender) != null) {
						equippedPanelSB.append(
								"<div class='"+inventorySlotId+" disabled'>"
									+ (charactersInventoryToRender.isDirtySlot(invSlot) ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
									+ "<div class='overlay' id='" + invSlot.toString() + "Slot'></div>"
									+ "<div class='raceBlockIcon'>" + Subspecies.getMainSubspeciesOfRace(invSlot.slotBlockedByRace(charactersInventoryToRender)).getSVGString(charactersInventoryToRender) + "</div>"
								+ "</div>");
						
					} else {
						boolean disabled = false;
						switch(invSlot){
							case HORNS:
								disabled = charactersInventoryToRender.getHornType()==HornType.NONE;
								break;
							case PENIS:
								disabled = !charactersInventoryToRender.hasPenisIgnoreDildo();
								break;
							case TAIL:
								disabled = charactersInventoryToRender.getTailType()==TailType.NONE;
								break;
							case VAGINA:
								disabled = !charactersInventoryToRender.hasVagina();
								break;
							case WINGS:
								disabled = charactersInventoryToRender.getWingType()==WingType.NONE;
								break;
							default:
								break;
						}
						
						equippedPanelSB.append("<div class='"+inventorySlotId+""+(disabled?" disabled":"")+"' id='" + invSlot.toString() + "Slot'>"
								+ (charactersInventoryToRender.isDirtySlot(invSlot) ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
								+ "</div>");
					}
				}
				
			} else {
				// Tattoos:
				
				Tattoo tattoo = charactersInventoryToRender.getTattooInSlot(invSlot);
				
				if (tattoo != null) {
					// add to content:
					equippedPanelSB.append(
							// If slot is sealed:
							"<div class='"+inventorySlotId + getClassRarityIdentifier(tattoo.getRarity()) +"'>"
								// Picture:
								+ "<div class='inventory-icon-content'>"+tattoo.getSVGImage(charactersInventoryToRender)+"</div>"
								
								// If scarred:
								+ (charactersInventoryToRender.getScarInSlot(invSlot)!=null ? "<div class='displacedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getScarIcon() + "</div>" : "")
								
								+ "<div class='overlay-inventory no-pointer' id='" + invSlot.toString() + "Slot'>" + "</div>"
							+ "</div>");
					
				} else {
					// add to content:
					if (blockedSlots.contains(invSlot)) {
						equippedPanelSB.append("<div class='"+inventorySlotId+" disabled'>"
													+ (charactersInventoryToRender.getScarInSlot(invSlot)!=null ? "<div class='scarIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getScarIcon() + "</div>" : "")
													+ "<div class='overlay' id='" + invSlot.toString() + "Slot'></div>"
												+ "</div>");
						
					} else {
						boolean disabled = false;
						switch(invSlot){
							case HORNS:
								disabled = charactersInventoryToRender.getHornType()==HornType.NONE;
								break;
							case PENIS:
								disabled = !charactersInventoryToRender.hasPenisIgnoreDildo();
								break;
							case TAIL:
								disabled = charactersInventoryToRender.getTailType()==TailType.NONE;
								break;
							case VAGINA:
								disabled = !charactersInventoryToRender.hasVagina();
								break;
							case WINGS:
								disabled = charactersInventoryToRender.getWingType()==WingType.NONE;
								break;
							default:
								break;
						}
						
						equippedPanelSB.append("<div class='"+inventorySlotId+""+(disabled?" disabled":"")+"' id='" + invSlot.toString() + "Slot'>"
								+ (charactersInventoryToRender.getScarInSlot(invSlot)!=null ? "<div class='scarIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getScarIcon() + "</div>" : "")
								+ "</div>");
					}
				}
				
			}
		}
	}
	
	private String getInventoryDiv(GameCharacter charactersInventoryToRender, boolean buyback) {
		
		inventorySB.setLength(0);
		
		String idModifier = charactersInventoryToRender==null?"FLOOR_":(charactersInventoryToRender.isPlayer()?"PLAYER_":"NPC_"+charactersInventoryToRender.getId()+"_");
		
		inventorySB.append("<div class='inventory-container'>");
		
		if(charactersInventoryToRender != null) {
			inventorySB.append(
					"<div style='position:absolute; left:16px'>"+ UtilText.formatAsMoney(charactersInventoryToRender.getMoney(), "b") +"</div>"
					+ "<div style='position:absolute; right:16px'>"+ UtilText.formatAsEssences(charactersInventoryToRender.getEssenceCount(TFEssence.ARCANE), "b", true) +"</div>"
					+"<p style='width:100%; text-align:center; padding:0 margin:0;'>"
						+(charactersInventoryToRender.isPlayer()
							?"<b style='color:"+Femininity.valueOf(charactersInventoryToRender.getFemininityValue()).getColour().toWebHexString()+";'>Your</b> <b>Inventory | Page "+(charactersInventoryToRender.isPlayer()?pageLeft+1:pageRight+1)+"</b>"
							:"<b style='color:"+Femininity.valueOf(charactersInventoryToRender.getFemininityValue()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(charactersInventoryToRender.getName())+"'s</b> <b>Inventory</b>")
					+"</p>");
			
		} else {
			inventorySB.append(
					"<p style='width:100%; text-align:center; padding:0 margin:0;'>"
						+(InventoryDialogue.getNPCInventoryInteraction() ==  InventoryInteraction.CHARACTER_CREATION
								?"<b style='color:"+Colour.BASE_TAN.toWebHexString()+";'>Your wardrobe</b>"
								:"<b style='color:"+Colour.BASE_TAN.toWebHexString()+";'>In this Area</b>")
						+"<b> | Page "+(pageRight+1)+"</b>"
					+"</p>");
		}
		
		// Buttons:
		int totalUniques = 0;
		String pageIdMod = "";
		int currentPage = 0;
		boolean renderQuestTab = false;
		boolean hasQuestItems = false;
		if(charactersInventoryToRender == null) {
			totalUniques = Main.game.getPlayerCell().getInventory().getUniqueItemCount() + Main.game.getPlayerCell().getInventory().getUniqueClothingCount() + Main.game.getPlayerCell().getInventory().getUniqueWeaponCount();
			pageIdMod = "INV_PAGE_RIGHT_";
			currentPage = pageRight;
		} else {
			renderQuestTab = charactersInventoryToRender.isPlayer();
			hasQuestItems = Main.game.getPlayer().isCarryingQuestItems();
			totalUniques = charactersInventoryToRender.getUniqueItemCount() + charactersInventoryToRender.getUniqueClothingCount() + charactersInventoryToRender.getUniqueWeaponCount();
			pageIdMod = (charactersInventoryToRender.isPlayer()?"INV_PAGE_LEFT_":"INV_PAGE_RIGHT_");
			currentPage = (charactersInventoryToRender.isPlayer()?pageLeft:pageRight);
		}
		
		inventorySB.append(
				"<div class='container-full-width' style='width:15%; margin:0; text-align:center;'>"
					+ "<div class='square-button max"+(currentPage==0?" selected":"")+"'>"
							+ "<div style='width:80%;height:80%;position:absolute;left:0; bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInventoryIcon()+"</div>"
							+ "<div style='width:50%;height:50%;position:absolute;right:4px; top:0;'>"+(currentPage==0?SVGImages.SVG_IMAGE_PROVIDER.getCounterOne():SVGImages.SVG_IMAGE_PROVIDER.getCounterOneDisabled())+"</div>"
							+ "<div class='overlay' "+(currentPage==0?"":"id='"+pageIdMod+"0'")+"></div>"
					+ "</div>"
					+ "<div class='square-button max"+(currentPage==1?" selected":"")+"'>"
							+ "<div style='width:80%;height:80%;position:absolute;left:0; bottom:0;'>"+(totalUniques>1*ITEMS_PER_PAGE?SVGImages.SVG_IMAGE_PROVIDER.getInventoryIcon():SVGImages.SVG_IMAGE_PROVIDER.getInventoryIconDisabled())+"</div>"
							+ "<div style='width:50%;height:50%;position:absolute;right:4px; top:0;'>"+(currentPage==1?SVGImages.SVG_IMAGE_PROVIDER.getCounterTwo():SVGImages.SVG_IMAGE_PROVIDER.getCounterTwoDisabled())+"</div>"
							+ (totalUniques>1*ITEMS_PER_PAGE
									?"<div class='overlay' "+(currentPage==1?"":"id='"+pageIdMod+"1'")+"></div>"
									:"<div class='overlay disabled'></div>")
					+ "</div>"
					+ "<div class='square-button max"+(currentPage==2?" selected":"")+"'>"
							+ "<div style='width:80%;height:80%;position:absolute;left:0; bottom:0;'>"+(totalUniques>2*ITEMS_PER_PAGE?SVGImages.SVG_IMAGE_PROVIDER.getInventoryIcon():SVGImages.SVG_IMAGE_PROVIDER.getInventoryIconDisabled())+"</div>"
							+ "<div style='width:50%;height:50%;position:absolute;right:4px; top:0;'>"+(currentPage==2?SVGImages.SVG_IMAGE_PROVIDER.getCounterThree():SVGImages.SVG_IMAGE_PROVIDER.getCounterThreeDisabled())+"</div>"
							+ (totalUniques>2*ITEMS_PER_PAGE
									?"<div class='overlay' "+(currentPage==2?"":"id='"+pageIdMod+"2'")+"></div>"
									:"<div class='overlay disabled'></div>")
					+ "</div>"
					+ "<div class='square-button max"+(currentPage==3?" selected":"")+"'>"
							+ "<div style='width:80%;height:80%;position:absolute;left:0; bottom:0;'>"+(totalUniques>3*ITEMS_PER_PAGE?SVGImages.SVG_IMAGE_PROVIDER.getInventoryIcon():SVGImages.SVG_IMAGE_PROVIDER.getInventoryIconDisabled())+"</div>"
							+ "<div style='width:50%;height:50%;position:absolute;right:4px; top:0;'>"+(currentPage==3?SVGImages.SVG_IMAGE_PROVIDER.getCounterFour():SVGImages.SVG_IMAGE_PROVIDER.getCounterFourDisabled())+"</div>"
							+ (totalUniques>3*ITEMS_PER_PAGE
									?"<div class='overlay' "+(currentPage==3?"":"id='"+pageIdMod+"3'")+"></div>"
									:"<div class='overlay disabled'></div>")
					+ "</div>"
					+ "<div class='square-button max"+(currentPage==4?" selected":"")+"'>"
						+ "<div style='width:80%;height:80%;position:absolute;left:0; bottom:0;'>"+(totalUniques>4*ITEMS_PER_PAGE?SVGImages.SVG_IMAGE_PROVIDER.getInventoryIcon():SVGImages.SVG_IMAGE_PROVIDER.getInventoryIconDisabled())+"</div>"
						+ "<div style='width:50%;height:50%;position:absolute;right:4px; top:0;'>"+(currentPage==4?SVGImages.SVG_IMAGE_PROVIDER.getCounterFive():SVGImages.SVG_IMAGE_PROVIDER.getCounterFiveDisabled())+"</div>"
						+ (totalUniques>4*ITEMS_PER_PAGE
								?"<div class='overlay' "+(currentPage==4?"":"id='"+pageIdMod+"4'")+"></div>"
								:"<div class='overlay disabled'></div>")
					+ "</div>"
					+ (renderQuestTab
							?"<div class='square-button max"+(currentPage==5?" selected":"")+"'>"
								+ "<div style='width:100%;height:100%;position:absolute;left:0; bottom:0;'>"+(hasQuestItems?SVGImages.SVG_IMAGE_PROVIDER.getQuestInventoryIcon():SVGImages.SVG_IMAGE_PROVIDER.getQuestInventoryIconDisabled())+"</div>"
								+ (hasQuestItems
									?"<div class='overlay' id='"+pageIdMod+"5'></div>"
									:"<div class='overlay disabled' id='"+pageIdMod+"5'></div>")
							+ "</div>"
							:"")
				+ "</div>");
		
		inventorySB.append("<div class='container-full-width' style='width:85%; margin:0;'>");
		if(buyback) {
			for (int i = Main.game.getPlayer().getBuybackStack().size() - 1; i >= 0; i--) {
				
				AbstractCoreItem itemBuyback = Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold();
				
				if (itemBuyback != null) {
					// Clothing:
					int itemPrice = Main.game.getPlayer().getBuybackStack().get(i).getPrice();
					if (itemBuyback instanceof AbstractClothing) {
						inventorySB.append(getBuybackItemPanel(itemBuyback, "CLOTHING_" + i, itemPrice));

					// Weapon:
					} else if (itemBuyback instanceof AbstractWeapon) {
						inventorySB.append(getBuybackItemPanel(itemBuyback, "WEAPON_" + i, itemPrice));
						
					// Item:
					} else {
						inventorySB.append(getBuybackItemPanel(itemBuyback, "ITEM_" + i, itemPrice));
					}
				}
			}
			
			// Fill space:
			for (int i = 24; i > Main.game.getPlayer().getBuybackStack().size(); i--) {
				inventorySB.append("<div class='inventory-item-slot unequipped'></div>");
			}
			
		} else {
			inventorySB.append(getInventoryIconsForPage(currentPage, charactersInventoryToRender, idModifier));
		}
		inventorySB.append("</div>");
		
		
		inventorySB.append("</div>");

//		inventorySB.append("</body>");
		
		return inventorySB.toString();
	}
	
	public String getGiftDiv(GameCharacter receiver) {
		inventorySB.setLength(0);
		
		Map<AbstractCoreItem, Integer> giftsAvailable = new HashMap<>();
		for(Entry<AbstractItem, Integer> entry : Main.game.getPlayer().getMapOfDuplicateItems().entrySet()) {
			if(receiver.getGiftReaction(entry.getKey(), false) != null) {
				giftsAvailable.put(entry.getKey(), entry.getValue());
			}
		}
		for(Entry<AbstractClothing, Integer> entry : Main.game.getPlayer().getMapOfDuplicateClothing().entrySet()) {
			if(receiver.getGiftReaction(entry.getKey(), false) != null) {
				giftsAvailable.put(entry.getKey(), entry.getValue());
			}
		}
		for(Entry<AbstractWeapon, Integer> entry : Main.game.getPlayer().getMapOfDuplicateWeapons().entrySet()) {
			if(receiver.getGiftReaction(entry.getKey(), false) != null) {
				giftsAvailable.put(entry.getKey(), entry.getValue());
			}
		}
		
		if(giftsAvailable.isEmpty()) {
			inventorySB.append("<div class='container-full-width'>"
					+ "<p style='width:100%; text-align:center; padding:0 margin:0;'>"
						+"<b style='color:"+Colour.GENERIC_MINOR_BAD.toWebHexString()+";'>No Suitable Gifts In Inventory</b>"
					+"</p>"
					+ "</div>");
		} else {
			inventorySB.append("<div class='container-full-width'>"
					+ "<p style='width:100%; text-align:center; padding:0 margin:0;'>"
						+"<b style='color:"+Colour.GENERIC_MINOR_GOOD.toWebHexString()+";'>Available Gifts</b>"
					+"</p>"
					+ "<div class='inventory-not-equipped'>");
			
			appendDivsForGiftsToInventory(inventorySB, giftsAvailable, "GIFT_");
			
			for (int i = 8-giftsAvailable.size(); i > 0; i--) {
				inventorySB.append("<div class='inventory-item-slot unequipped'></div>");
			}
			
			inventorySB.append("</div>"
					+ "</div>");
		}
		
		return inventorySB.toString();
	}

	private static StringBuilder pageSB = new StringBuilder();
	private static String getInventoryIconsForPage(int page, GameCharacter charactersInventoryToRender, String idModifier) {
		int uniqueItemCount = 0;
		pageSB.setLength(0);
		
		if(charactersInventoryToRender == null) {
			for(Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateWeapons().entrySet()) {
				if(uniqueItemCount >= page*ITEMS_PER_PAGE && uniqueItemCount < (page+1)*ITEMS_PER_PAGE) {
					pageSB.append(getInventoryItemDiv(Main.game.getPlayerCell().getInventory(), entry.getKey(), entry.getValue(), idModifier+"WEAPON_"));
				}
				uniqueItemCount++;
			}
			
			for(Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateClothing().entrySet()) {
				if(uniqueItemCount >= page*ITEMS_PER_PAGE && uniqueItemCount < (page+1)*ITEMS_PER_PAGE) {
					pageSB.append(getInventoryItemDiv(Main.game.getPlayerCell().getInventory(), entry.getKey(), entry.getValue(), idModifier+"CLOTHING_"));
				}
				uniqueItemCount++;
			}
			
			for(Entry<AbstractItem, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateItems().entrySet()) {
				if(uniqueItemCount >= page*ITEMS_PER_PAGE && uniqueItemCount < (page+1)*ITEMS_PER_PAGE) {
					pageSB.append(getInventoryItemDiv(Main.game.getPlayerCell().getInventory(), entry.getKey(), entry.getValue(), idModifier+"ITEM_"));
				}
				uniqueItemCount++;
			}
			
		} else {
			if(page==5) { // Quest:
				for(Entry<AbstractWeapon, Integer> entry : charactersInventoryToRender.getMapOfDuplicateWeapons().entrySet()) {
					if(entry.getKey().getRarity()==Rarity.QUEST && charactersInventoryToRender.isPlayer()) {
						if(uniqueItemCount < ITEMS_PER_PAGE) {
							pageSB.append(getInventoryItemDiv(charactersInventoryToRender, entry.getKey(), entry.getValue(), idModifier+"WEAPON_"));
						}
						uniqueItemCount++;
					}
				}
				
				for(Entry<AbstractClothing, Integer> entry : charactersInventoryToRender.getMapOfDuplicateClothing().entrySet()) {
					if(entry.getKey().getRarity()==Rarity.QUEST && charactersInventoryToRender.isPlayer()) {
						if(uniqueItemCount < ITEMS_PER_PAGE) {
							pageSB.append(getInventoryItemDiv(charactersInventoryToRender, entry.getKey(), entry.getValue(), idModifier+"CLOTHING_"));
						}
						uniqueItemCount++;
					}
				}
				
				for(Entry<AbstractItem, Integer> entry : charactersInventoryToRender.getMapOfDuplicateItems().entrySet()) {
					if(entry.getKey().getRarity()==Rarity.QUEST && charactersInventoryToRender.isPlayer()) {
						if(uniqueItemCount < ITEMS_PER_PAGE) {
							pageSB.append(getInventoryItemDiv(charactersInventoryToRender, entry.getKey(), entry.getValue(), idModifier+"ITEM_"));
						}
						uniqueItemCount++;
					}
				}
				
			} else {
				for(Entry<AbstractWeapon, Integer> entry : charactersInventoryToRender.getMapOfDuplicateWeapons().entrySet()) {
					if(entry.getKey().getRarity()!=Rarity.QUEST || !charactersInventoryToRender.isPlayer()) {
						if(uniqueItemCount >= page*ITEMS_PER_PAGE && uniqueItemCount < (page+1)*ITEMS_PER_PAGE) {
							pageSB.append(getInventoryItemDiv(charactersInventoryToRender, entry.getKey(), entry.getValue(), idModifier+"WEAPON_"));
						}
						uniqueItemCount++;
					}
				}
				
				for(Entry<AbstractClothing, Integer> entry : charactersInventoryToRender.getMapOfDuplicateClothing().entrySet()) {
					if(entry.getKey().getRarity()!=Rarity.QUEST || !charactersInventoryToRender.isPlayer()) {
						if(uniqueItemCount >= page*ITEMS_PER_PAGE && uniqueItemCount < (page+1)*ITEMS_PER_PAGE) {
							pageSB.append(getInventoryItemDiv(charactersInventoryToRender, entry.getKey(), entry.getValue(), idModifier+"CLOTHING_"));
						}
						uniqueItemCount++;
					}
				}
				
				for(Entry<AbstractItem, Integer> entry : charactersInventoryToRender.getMapOfDuplicateItems().entrySet()) {
					if(entry.getKey().getRarity()!=Rarity.QUEST || !charactersInventoryToRender.isPlayer()) {
						if(uniqueItemCount >= page*ITEMS_PER_PAGE && uniqueItemCount < (page+1)*ITEMS_PER_PAGE) {
							pageSB.append(getInventoryItemDiv(charactersInventoryToRender, entry.getKey(), entry.getValue(), idModifier+"ITEM_"));
						}
						uniqueItemCount++;
					}
				}
			}
		}
		
		// Fill space:
		for (int i = uniqueItemCount - (page==5?0:page)*ITEMS_PER_PAGE ; i < ITEMS_PER_PAGE; i++) {
			pageSB.append("<div class='inventory-item-slot'></div>");
		}
		
		return pageSB.toString();
	}
	
	private static StringBuilder itemSB = new StringBuilder();
	private static String getInventoryItemDiv(GameCharacter charactersInventoryToRender, AbstractCoreItem item, int count, String idPrefix) {
		itemSB.setLength(0);
		itemSB.append("<div class='inventory-item-slot "+ item.getDisplayRarity() + "'>"
						+ "<div class='inventory-icon-content'>"+item.getSVGString()+"</div>");

		if (item instanceof AbstractClothing && ((AbstractClothing)item).isDirty()) {
			itemSB.append("<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>");
		}
		String overlay = "<div class='overlay";
		String last_layer = "";

		if (charactersInventoryToRender!=null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {

			NPC npc = InventoryDialogue.getInventoryNPC();
			if(charactersInventoryToRender.isPlayer()) {

				if (npc.willBuy(item)) {
					last_layer = getItemPriceDiv(item.getPrice(npc.getBuyModifier()));
				} else {
					overlay += " dark";
				}
			} else {
				last_layer = getItemPriceDiv(item.getPrice(npc.getSellModifier()));
			}
		} else {
			boolean nonPlayerInv = charactersInventoryToRender!=null && !charactersInventoryToRender.isPlayer();
			boolean isTraderInv = nonPlayerInv && ((NPC) charactersInventoryToRender).isTrader();

			if (item instanceof AbstractItem) {
				AbstractItem abItem = (AbstractItem)item;
				if (nonPlayerInv || (Main.game.isInSex() && (isTraderInv || !abItem.isAbleToBeUsedInSex()))
						|| (Main.game.isInCombat() && abItem.isAbleToBeUsedInCombat() == false)) {
					overlay += " disabled";
				}
			} else if (item instanceof AbstractClothing) {
				AbstractClothing clothing = (AbstractClothing)item;
				if (Main.game.isInCombat() || (Main.game.isInSex() && (isTraderInv || !clothing.getClothingType().isAbleToBeEquippedDuringSex()))) {
					overlay += " disabled";
				}
			}
		}
		itemSB.append(overlay+"' id='" + idPrefix + item.hashCode() + "'>"+getItemCountDiv(count)+last_layer+"</div></div>");
		return itemSB.toString();
	}
	private static String getInventoryItemDiv(CharacterInventory inventory, AbstractCoreItem item, int count, String idPrefix) {
		itemSB.setLength(0);
		itemSB.append("<div class='inventory-item-slot "+ item.getDisplayRarity() + "'>"
						+ "<div class='inventory-icon-content'>"+item.getSVGString()+"</div>");

		if (item instanceof AbstractClothing && ((AbstractClothing)item).isDirty()) {
			itemSB.append("<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>");
		}
		String overlay = "<div class='overlay";
		String last_layer = "";

		if (item instanceof AbstractItem) {
			AbstractItem abItem = (AbstractItem)item;
			if ((Main.game.isInSex() && !abItem.isAbleToBeUsedInSex()) || (Main.game.isInCombat() && !abItem.isAbleToBeUsedInCombat())) {
				overlay += " disabled";
			}
		} else if (item instanceof AbstractClothing) {
			AbstractClothing clothing = (AbstractClothing)item;
			if (Main.game.isInCombat() || (Main.game.isInSex() && !clothing.getClothingType().isAbleToBeEquippedDuringSex())) {
				overlay += " disabled";
			}
		}
			
		itemSB.append(overlay+"' id='" + idPrefix + item.hashCode() + "'>"+getItemCountDiv(count)+last_layer+"</div></div>");
		return itemSB.toString();
	}

	private static void appendDivsForGiftsToInventory(StringBuilder stringBuilder, Map<AbstractCoreItem, Integer> map, String idPrefix) {
		for (Entry<? extends AbstractCoreItem, Integer> entry : map.entrySet()) {
			stringBuilder.append(
					"<div class='inventory-item-slot unequipped "+ entry.getKey().getDisplayRarity() + "'>"
						+ "<div class='inventory-icon-content'>"
							+entry.getKey().getSVGString()
						+"</div>"
						+ "<div class='overlay' id='" + idPrefix + entry.getKey().hashCode() + "'>"
							+ getItemCountDiv(true, entry.getValue())
						+"</div>"
					+ "</div>");
		}
	}
	
	private static String getItemCountDiv(int amount) {
		return getItemCountDiv(false, amount);
	}
	
	private static String getItemCountDiv(boolean countSingles, int amount) {
		if (amount > 1 || countSingles) {
			return "<div class='item-count'>x" + amount + "</div>";
		}
		return "";
	}
	
	private static String getItemPriceDiv(int price) {
		return "<div class='item-price'>"
				+ UtilText.formatAsItemPrice(price)
			+ "</div>";
	}
	
	private static String getBuybackItemPanel(AbstractCoreItem itemBuyback, String id, int price) {
		return "<div class='inventory-item-slot unequipped " + itemBuyback.getDisplayRarity() + "'>"
					+ "<div class='inventory-icon-content'>"+itemBuyback.getSVGString()+"</div>"
					+ "<div class='overlay' id='" + id + "'>"
						+ getItemPriceDiv(price)
					+ "</div>"
				+ "</div>";
	}

	// DecimalFormat decimalFormatter = new DecimalFormat("#,###");
	private StringBuilder uiAttributeSB = new StringBuilder();

	private DialogueNodeOld renderedDialogueNode = null;
	
	public void renderAttributesPanelLeft() {
		uiAttributeSB.setLength(0);
		
		uiAttributeSB.append(
				"<body onLoad='scrollEventLogToBottom()'>");
		
		if(Main.game.isInSex()) {
			// Name box:
			uiAttributeSB.append(
				"<div class='full-width-container' style='background-color:#19191a; border-radius:5px; margin-bottom:8px;'>"
					+ "<div class='full-width-container'>"
						+ "<p class='character-name' style='color:"+ (Sex.isDom(Main.game.getPlayer())
								?Colour.BASE_CRIMSON.toWebHexString()+";'>Dominant"+(Sex.getDominantParticipants().size()>1?"s":"")+"</b>"
								:Colour.BASE_PINK_LIGHT.toWebHexString()+";'>Submissive"+(Sex.getSubmissiveParticipants().size()>1?"s":"")+"</b>")
						+"</p>"
					+ "</div>"
				+ "</div>"
				+ "<div class='full-width-container' style='height: calc(100% - 128vw); overflow-y: auto;'>");
			
			if(Sex.isDom(Main.game.getPlayer())) {
				for(GameCharacter character : Sex.getDominantParticipants().keySet()) {
					uiAttributeSB.append(getCharacterPanelSexDiv(Sex.getDominantParticipants().size()>1, character.isPlayer()?"PLAYER_":"NPC_"+character.getId()+"_", character));
				}
				
			} else {
				for(GameCharacter character : Sex.getSubmissiveParticipants().keySet()) {
					uiAttributeSB.append(getCharacterPanelSexDiv(Sex.getSubmissiveParticipants().size()>1, character.isPlayer()?"PLAYER_":"NPC_"+character.getId()+"_", character));
				}
			}
			
			uiAttributeSB.append("</div>");
						
		} else if(Main.game.isInCombat()) {
			
			// Name box:
			uiAttributeSB.append(
				"<div class='full-width-container' style='background-color:#19191a; border-radius:5px; margin-bottom:8px;'>"
					+ "<div class='full-width-container'>"
						+ "<p class='character-name' style='color:"+ Colour.BASE_GREEN.toWebHexString()+";'>"
								+ "Allies"
						+"</p>"
					+ "</div>"
				+ "</div>"
				+ "<div class='full-width-container' style='height: calc(100% - 128vw); overflow-y: auto;'>");
			
			uiAttributeSB.append(getCharacterPanelDiv(Combat.getAllies().size()>0, "PLAYER_", Main.game.getPlayer()));
			
			for(GameCharacter character : Combat.getAllies()) {
				uiAttributeSB.append(getCharacterPanelDiv(true, "NPC_"+character.getId()+"_", character));
			}
			
			
			uiAttributeSB.append("</div>");
			
			
		} else {
			// Name box: TODO
			PlaceType place = Main.game.getPlayer().getWorldLocation().getStandardPlace();
			if(Main.game.getPlayer().getLocationPlace()!=null) {
				place = Main.game.getPlayer().getLocationPlace().getPlaceType();
			}
			uiAttributeSB.append(
				"<div class='full-width-container' style='background-color:#19191a; border-radius:5px; margin-bottom:8px;'>"
					+ "<div class='full-width-container'>"
						+ "<p class='character-name' style='color:"+ Main.game.getPlayer().getWorldLocation().getColour().toWebHexString() + ";'>"
							+ Main.game.getPlayer().getWorldLocation().getName()
						+ "</p>"
					+ "</div>"
					+ "<div class='full-width-container' style='margin:0;padding:0;'>"
						+ "<p style='text-align:center;"+ (place.getColour()==null?"":" color:"+place.getColour().toWebHexString()) + ";'>"
							+ place.getName()
						+"</p>"
					+ "</div>"
				+ "</div>"
				+ "<div class='full-width-container' style='height: calc(100% - 138vw); overflow-y: auto;'>");
			
			uiAttributeSB.append(getCharacterPanelDiv(!Main.game.getPlayer().getCompanions().isEmpty(), "PLAYER_", Main.game.getPlayer()));
			
			for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
				uiAttributeSB.append(getCharacterPanelDiv(true, "NPC_"+character.getId()+"_", character));
				for(GameCharacter characterCompanion : character.getCompanions()) {
					uiAttributeSB.append(getCharacterPanelDiv(true, "NPC_"+characterCompanion.getId()+"_", characterCompanion));
					
				}
			}
			
			uiAttributeSB.append("</div>");
		}

		uiAttributeSB.append("</div>");
		
		uiAttributeSB.append("<div class='full-width-container' style='background-color:#19191a; border-radius:5px; margin-bottom:1px; padding:4px;'>"
					+ "<div class='full-width-container' style='text-align:center; margin-left:4px;'>"
					+"<div class='item-inline' style='float:left;'><div class='overlay' id='DATE_DISPLAY_TOGGLE'>"+SVGImages.SVG_IMAGE_PROVIDER.getCalendarIcon()+"</div></div>"
							+ "<p style='color:"+Colour.TEXT.getShades(8)[3]+"; float:left; width:50%;'>"
								+ (Main.getProperties().hasValue(PropertyValue.calendarDisplay)
									? Main.game.getDateNow().format(DateTimeFormatter.ofPattern("d", Locale.ENGLISH))
										+ Util.getDayOfMonthSuffix(Main.game.getDateNow().getDayOfMonth())
										+ " "
										+ Main.game.getDateNow().format(DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH))
									:"Day "+Main.game.getDayNumber())
							+ "</p>"
							+ "<p style='float:right; margin-right:8px;'>");
		
		if(Main.game.getPlayer().getClothingInSlot(InventorySlot.WRIST)!=null
				&& (Main.game.getPlayer().getClothingInSlot(InventorySlot.WRIST).getClothingType().equals(ClothingType.WRIST_WOMENS_WATCH)
						|| Main.game.getPlayer().getClothingInSlot(InventorySlot.WRIST).getClothingType().equals(ClothingType.WRIST_MENS_WATCH))) {
			uiAttributeSB.append("<div class='item-inline' style='float:left;'><div class='overlay' id='TWENTY_FOUR_HOUR_TIME_TOGGLE'>"
						+Main.game.getPlayer().getClothingInSlot(InventorySlot.WRIST).getSVGEquippedString(Main.game.getPlayer())+"</div></div>");
			
		} else {
			uiAttributeSB.append("<div class='item-inline' style='float:left;'><div class='overlay' id='TWENTY_FOUR_HOUR_TIME_TOGGLE'>"+SVGImages.SVG_IMAGE_PROVIDER.getJournalIcon()+"</div></div>");
		}
		
		uiAttributeSB.append((Main.getProperties().hasValue(PropertyValue.twentyFourHourTime)
								?Main.game.getDateNow().format(DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH))
								:Main.game.getDateNow().format(DateTimeFormatter.ofPattern("hh:mma", Locale.ENGLISH)))
							+"</p>"
					+ "</div>"
				+ "</div>"
				);
		
		if(Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.INVENTORY || Main.game.isInCombat() || Main.game.isInSex()) {

			//TODO
			uiAttributeSB.append(getInventoryEquippedPanel(Main.game.getPlayer()));
			
		} else {
			uiAttributeSB.append("<div>" + renderedHTMLMap() + "</div>");
		}
		
		uiAttributeSB.append("</body>");

		if (Main.mainController != null) {
			if (Main.game.getCurrentDialogueNode() != null) {
				if (!Main.game.getCurrentDialogueNode().isNoTextForContinuesDialogue() && renderedDialogueNode != Main.game.getCurrentDialogueNode()) {
					renderedDialogueNode = Main.game.getCurrentDialogueNode();

				}
			}
		}
		
		Main.mainController.setAttributePanelContent(uiAttributeSB.toString());
	}
	
	public static GameCharacter getCharacterToRender() {
		
		if(Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.CHARACTERS_PRESENT || Main.game.getCurrentDialogueNode() == PhoneDialogue.CONTACTS_CHARACTER) {
			return (NPC) CharactersPresentDialogue.characterViewed;
		}
		
		if(Main.game.isInSex()) {
			return Sex.getActivePartner();
		}
		
		if(Main.game.isInCombat()) {
			return Combat.getTargetedCombatant(Main.game.getPlayer());
		}
		
		if(InventoryDialogue.getInventoryNPC()!=null && Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.INVENTORY) {
			return InventoryDialogue.getInventoryNPC();
		}
		
		if(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected()!=null) {
			return Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected();
		}
		
		return Main.game.getActiveNPC();
	}
	
	public static String getEntryBackgroundColour(boolean alternative) {
		if(alternative) {
			return Colour.BACKGROUND_ALT.toWebHexString();
		}
		return Colour.BACKGROUND.toWebHexString();
	}

	public boolean isRenderingCharactersRightPanel() {
		return Main.game.isInSex()
				|| Main.game.isInCombat()
				|| (getCharacterToRender()!=null
					&& (Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.CHARACTERS_PRESENT
						|| Main.game.getCurrentDialogueNode() == PhoneDialogue.CONTACTS_CHARACTER
						|| Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected()!=null
						|| (Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.INVENTORY
							&& InventoryDialogue.getInventoryNPC()!=null)));
	}
	
	public void renderAttributesPanelRight() {
		uiAttributeSB.setLength(0);
		
		uiAttributeSB.append(
				"<body onLoad='scrollEventLogToBottom()'>"
					+ " <script>"
						+"function scrollEventLogToBottom() {document.getElementById('event-log-inner-id').scrollTop = document.getElementById('event-log-inner-id').scrollHeight;}"
					+ "</script>");
			
		if(isRenderingCharactersRightPanel()) {
			
			if(Main.game.isInSex()) {
				// Name box:
				uiAttributeSB.append(
						"<div class='full-width-container' style='background-color:#19191a; border-radius:5px; margin-bottom:8px;'>"
						+ "<div class='full-width-container'>"
							+ "<p class='character-name' style='color:"+ (!Sex.isDom(Main.game.getPlayer())
									?Colour.BASE_CRIMSON.toWebHexString()+";'>Dominant"+(Sex.getDominantParticipants().size()>1?"s":"")
									:Colour.BASE_PINK_LIGHT.toWebHexString()+";'>Submissive"+(Sex.getSubmissiveParticipants().size()>1?"s":""))
							+"</p>"
						+ "</div>"
					+ "</div>"
					+ "<div class='full-width-container' style='height: calc(100% - 128vw); overflow-y: auto;'>");
				
				if(!Sex.isDom(Main.game.getPlayer())) {
					for(GameCharacter character : Sex.getDominantParticipants().keySet()) {
						uiAttributeSB.append(getCharacterPanelSexDiv(Sex.getDominantParticipants().size()>1, "NPC_"+character.getId()+"_", character));
					}
					
				} else {
					for(GameCharacter character : Sex.getSubmissiveParticipants().keySet()) {
						uiAttributeSB.append(getCharacterPanelSexDiv(Sex.getSubmissiveParticipants().size()>1, "NPC_"+character.getId()+"_", character));
					}
				}
				uiAttributeSB.append("</div>");
				
			
			} else if(Main.game.isInCombat()) {
				
				// Name box:
				uiAttributeSB.append(
					"<div class='full-width-container' style='background-color:#19191a; border-radius:5px; margin-bottom:8px;'>"
						+ "<div class='full-width-container'>"
							+ "<p class='character-name' style='color:"+ Colour.BASE_CRIMSON.toWebHexString()+";'>"
								+ (Combat.getEnemies().size()>1?"Enemies":"Enemy")
							+"</p>"
						+ "</div>"
					+ "</div>"
					+ "<div class='full-width-container' style='height: calc(100% - 128vw); overflow-y: auto;'>");
				
				for(GameCharacter character : Combat.getEnemies()) {
					uiAttributeSB.append(getCharacterPanelDiv(Combat.getEnemies().size()>1, "NPC_"+character.getId()+"_", character));
				}
				
				
				uiAttributeSB.append("</div>");
				
				
			} else {
				// Name box: TODO
				PlaceType place = Main.game.getPlayer().getWorldLocation().getStandardPlace();
				if(Main.game.getPlayer().getLocationPlace()!=null) {
					place = Main.game.getPlayer().getLocationPlace().getPlaceType();
				}
				uiAttributeSB.append(
					"<div class='full-width-container' style='background-color:#19191a; border-radius:5px; margin-bottom:8px;'>"
						+ "<div class='full-width-container'>"
							+ "<p class='character-name' style='color:"+ Main.game.getPlayer().getWorldLocation().getColour().toWebHexString() + ";'>"
								+ Main.game.getPlayer().getWorldLocation().getName()
							+ "</p>"
						+ "</div>"
						+ "<div class='full-width-container' style='margin:0;padding:0;'>"
							+ "<p style='text-align:center;"+ (place.getColour()==null?"":" color:"+place.getColour().toWebHexString()) + ";'>"
								+ place.getName()
							+"</p>"
						+ "</div>"
					+ "</div>"
					+ "<div class='full-width-container' style='height: calc(100% - 138vw); overflow-y: auto;'>");
				
				uiAttributeSB.append(getCharacterPanelDiv(true, "NPC_"+getCharacterToRender().getId()+"_", getCharacterToRender()));
				
				uiAttributeSB.append("</div>");

			}
			uiAttributeSB.append(
					"<div class='full-width-container' style='background-color:#19191a; border-radius:5px; margin-bottom:1px; padding:4px;'>"
							+ "<div class='full-width-container' style='text-align:center;'>"
									+ "<p style='white-space: nowrap;  overflow: hidden;  text-overflow: ellipsis;'>"
										+ (getCharacterToRender()==null
											?"No Character"
											:UtilText.parse(getCharacterToRender(), "[npc.NamePos] Inventory"))
									+ "</p>"
							+ "</div>"
						+ "</div>");
			
			uiAttributeSB.append(getInventoryEquippedPanel(getCharacterToRender()));
				
		} else {

			uiAttributeSB.append("<div class='full'>");
			
			PlaceType place = Main.game.getPlayer().getWorldLocation().getStandardPlace();
			if(Main.game.getPlayer().getLocationPlace()!=null) {
				place = Main.game.getPlayer().getLocationPlace().getPlaceType();
			}
			uiAttributeSB.append(
							// Name box:
					"<div class='full-width-container' style='background-color:#19191a; border-radius:5px; margin-bottom:8px;'>"
					+ "<div class='full-width-container'>"
						+ "<p class='character-name' style='color:"+ Main.game.getPlayer().getWorldLocation().getColour().toWebHexString() + ";'>"
							+ Main.game.getPlayer().getWorldLocation().getName()
						+ "</p>"
					+ "</div>"
					+ "<div class='full-width-container' style='margin:0;padding:0;'>"
						+ "<p style='text-align:center;"+ (place.getColour()==null?"":" color:"+place.getColour().toWebHexString()) + ";'>"
							+ place.getName()
						+"</p>"
					+ "</div>"
				+ "</div>");
			
			// Characters Present:
			uiAttributeSB.append("<div class='attribute-container effects'>"
								+ "<p style='text-align:center;padding:0;margin:0;'><b>Characters Present</b></p>");
			List <NPC> charactersPresent = Main.game.getCharactersPresent();
			if(charactersPresent.isEmpty()) {
				uiAttributeSB.append("<p style='text-align:center;padding:0;margin:0;'><span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>None...</span></p>");
				
			} else {
				int count = 0;
				for(NPC character : charactersPresent) {
//					if(!character.isRaceConcealed()) {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(count%2==0)+";'>"
									+ "<div class='icon' style='width:11%; left:0; top:0; margin:0 8px 0 0; padding:0;'>"
										+ "<div class='icon-content'>"
											+ (character.isRaceConcealed()?SVGImages.SVG_IMAGE_PROVIDER.getRaceUnknown():character.getMapIcon())
										+ "</div>"
									+ "</div>"
									+" <div style='color:"+character.getFemininity().getColour().toWebHexString()+";'>"
										+(!character.getArtworkList().isEmpty() && Main.getProperties().hasValue(PropertyValue.artwork)?"&#128247; ":"")+character.getName("A")
										+ "<div class='overlay-inventory' id='NPC_" + character.getId() + "_" + Attribute.EXPERIENCE.getName() + "' style='width:calc(11% + 8px);'></div>"
										+ "<div class='overlay-inventory' id='NPC_"+character.getId()+"_ATTRIBUTES' style='width:calc(89% - 8px); left:calc(11% + 8px);'></div>"
									+"</div>"
								+ "</div>");
//					} else {
//						uiAttributeSB.append(
//								"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(count%2==0)+";'>"
//									+ "<div class='icon' style='width:11%; left:0; top:0; margin:0 8px 0 0; padding:0;'>"
//										+ "<div class='icon-content'>"
//											+ (character.isRaceConcealed()?SVGImages.SVG_IMAGE_PROVIDER.getRaceUnknown():character.getMapIcon())
//										+ "</div>"
//									+ "</div>"
//									+" <div style='color:"+Colour.BASE_GREY_DARK.toWebHexString()+";'>"
//										+(!character.getArtworkList().isEmpty() && Main.getProperties().hasValue(PropertyValue.artwork)?"&#128247; ":"")+ (character.isPlayerKnowsName()?character.getName("A"):"Unknown person")
//										+ "<div class='overlay-inventory' id='NPC_" + character.getId() + "_" + Attribute.EXPERIENCE.getName() + "' style='width:calc(11% + 8px);'></div>"
//										+ "<div class='overlay-inventory' id='NPC_"+character.getId()+"_ATTRIBUTES' style='width:calc(89% - 8px); left:calc(11% + 8px);'></div>"
//									+"</div>"
//								+ "</div>");
//					}//The commented section remove the ability to click the character to see the character present dialogue and the overlay that contain the stats. Clicking on the icon will still bring you to the character present dialogue
					count++;
				}
			}
			uiAttributeSB.append("</div>");
			
			
			// Items Present:
			uiAttributeSB.append("<div class='attribute-container effects'>"
								+ "<p style='text-align:center;padding:0;margin:0;'><b>Items Present</b></p>");
			
			int count = 0;
			if(Main.game.isInNewWorld()) {
				for(Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateWeapons().entrySet()) {
					if(count%2==0) {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(false)+";'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getName(false, true))
										+ "<div class='overlay-inventory' id='WEAPON_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					} else {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(true)+";'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getName(false, true))
										+ "<div class='overlay-inventory' id='WEAPON_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					}
					count++;
				}
				for(Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateClothing().entrySet()) {
					if(count%2==0) {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(false)+";'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getName(false, true))
										+ "<div class='overlay-inventory' id='CLOTHING_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					} else {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(true)+";'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getName(false, true))
										+ "<div class='overlay-inventory' id='CLOTHING_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					}
					count++;
				}
				for(Entry<AbstractItem, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateItems().entrySet()) {
					if(count%2==0) {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(false)+";'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getName(false, true))
										+ "<div class='overlay-inventory' id='ITEM_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					} else {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(true)+";'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getName(false, true))
										+ "<div class='overlay-inventory' id='ITEM_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					}
					count++;
				}
			}
			if(count==0) {
				uiAttributeSB.append("<p style='text-align:center;padding:0;margin:0;'><span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>None...</span></p>");
			}
			uiAttributeSB.append(
					"</div>");
						
			
			// Event log:
			uiAttributeSB.append(
					"</div>"
						+ "<div class='event-log'>"
							+ "<p style='text-align:center;padding:0;margin:0;'><b>Event Log</b></p>"
							+ "<div class='event-log-inner' id='event-log-inner-id'>");
			
			if(Main.game.getEventLog().isEmpty()) {
				uiAttributeSB.append("<p style='text-align:center;padding:0;margin:0;'><span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No events yet...</span></p>");
			}
			
			count = 0;
			
			if(Main.game.getEventLog().size()>50) {
				List<EventLogEntry> youveCatToBeKittenMeRightMeow = new ArrayList<>(Main.game.getEventLog().subList(Main.game.getEventLog().size()-50, Main.game.getEventLog().size()));
				Collections.reverse(youveCatToBeKittenMeRightMeow);
				for(EventLogEntry event : youveCatToBeKittenMeRightMeow) {
					if(count%2==0) {
						uiAttributeSB.append("<div class='event-log-entry' style='background:"+getEntryBackgroundColour(false)+";'>"+UtilText.parse(event.getFormattedEntry())+"</div>");
					} else {
						uiAttributeSB.append("<div class='event-log-entry' style='background:"+getEntryBackgroundColour(true)+";'>"+UtilText.parse(event.getFormattedEntry())+"</div>");
					}
					count++;
				}
				
			} else {
				List<EventLogEntry> youveCatToBeKittenMeRightMeow = new ArrayList<>(Main.game.getEventLog());
				Collections.reverse(youveCatToBeKittenMeRightMeow);
				for(EventLogEntry event : youveCatToBeKittenMeRightMeow) {
					if(count%2==0) {
						uiAttributeSB.append("<div class='event-log-entry' style='background:"+getEntryBackgroundColour(false)+";'>"+UtilText.parse(event.getFormattedEntry())+"</div>");
					} else {
						uiAttributeSB.append("<div class='event-log-entry' style='background:"+getEntryBackgroundColour(true)+";'>"+UtilText.parse(event.getFormattedEntry())+"</div>");
					}
					count++;
				}
			}
			uiAttributeSB.append("</div>"
					+ "</div>");
		
		}
		
		uiAttributeSB.append("</body>");

		if (Main.mainController != null) {
			if (Main.game.getCurrentDialogueNode() != null) {
				if (!Main.game.getCurrentDialogueNode().isNoTextForContinuesDialogue() && renderedDialogueNode != Main.game.getCurrentDialogueNode()) {
					renderedDialogueNode = Main.game.getCurrentDialogueNode();
				}
			}
		}
		
		Main.mainController.setRightPanelContent(uiAttributeSB.toString());
	}

	private StringBuilder mapSB = new StringBuilder();
	
	private Colour getPlayerIconColour(boolean isDangerous) {
		if(isDangerous) {
			return Colour.BASE_RED;
		} else {
			switch(Main.game.getPlayer().getFemininity()) {
				case ANDROGYNOUS:
					return Femininity.ANDROGYNOUS.getColour();
				case FEMININE:
				case FEMININE_STRONG:
					return Femininity.FEMININE_STRONG.getColour();
				case MASCULINE:
				case MASCULINE_STRONG:
					return Femininity.MASCULINE_STRONG.getColour();
			}
			return Main.game.getPlayer().getFemininity().getColour();
		}
	}

	public String getFullMap(WorldType world, boolean withFastTravelAndIcons) {
		mapSB.setLength(0);
		
		if(withFastTravelAndIcons) {
			boolean isAbleToTeleport = (Main.game.getPlayer().isAbleToTeleport() || Main.game.isDebugMode())
						&& Main.game.getSavedDialogueNode().equals(Main.game.getPlayer().getLocationPlace().getDialogue(false))
						&& Main.game.getPlayer().getMana()>=Spell.TELEPORT.getModifiedCost(Main.game.getPlayer())
					;
			
			if(Main.game.getPlayer().hasSpell(Spell.TELEPORT) || Main.game.isDebugMode()) {
				mapSB.append("<div class='container-full-width'>"
						+ (isAbleToTeleport
								?"[style.boldArcane(Teleport:)] You are currently able to teleport to locations you've previously visited!"
										+ (Main.game.isDebugMode()
												?" [style.italicsOrange(As debug mode is on, this costs nothing, and you can teleport you and your party anywhere.)]"
												:" This will cost <b>"+Spell.TELEPORT.getModifiedCost(Main.game.getPlayer())+"</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+".")
								:"[style.boldBad(Teleport:)] Teleport is unavailable! You need to:"
									+(!Main.game.getPlayer().getCompanions().isEmpty() && !Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.TELEPORT_2)
										?"<br/><b>-</b> Either learn the upgrade '"+SpellUpgrade.TELEPORT_2.getName()+"', or dismiss your party members."
										:"")
									+(!Main.game.getSavedDialogueNode().equals(Main.game.getPlayer().getLocationPlace().getDialogue(false))
										?"<br/><b>-</b> Not be in the middle of a special scene."
										:"")
									+(Main.game.getPlayer().getMana()<Spell.TELEPORT.getModifiedCost(Main.game.getPlayer())
											?"<br/><b>-</b> Have at least <b>"+Spell.TELEPORT.getModifiedCost(Main.game.getPlayer())+"</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+"."
											:""))
						
						+ "</div>");
			}
		}

		Cell[][] grid = Main.game.getWorlds().get(world).getGrid();
		int divWidth = (Math.min(75, grid.length*10));
		mapSB.append("<div class='container-full-width' style='width:"+divWidth+"%; margin:2% "+((100-divWidth)/2)+"%; float:left;'>");
		
		float width = 100f/grid.length;
		for(int i=grid[0].length-1; i>=0; i--) {
			for(int j=0; j<grid.length; j++) {
				Cell c = grid[j][i];
				
				boolean discovered = c.isDiscovered() || Main.game.isMapReveal();
				
				String background = c.getPlace().getPlaceType()==PlaceType.GENERIC_IMPASSABLE
									?"background:transparent;"
									:c.getPlace().getPlaceType().isDangerous() && discovered
										?""
										:discovered
											?"background-color:"+c.getPlace().getPlaceType().getBackgroundColour().toWebHexString()+";"
											:"background-color:"+Colour.MAP_BACKGROUND_UNEXPLORED.toWebHexString()+";";
				
				if(!discovered || c.getPlace().getPlaceType()==PlaceType.GENERIC_IMPASSABLE) {
					mapSB.append("<div class='map-icon' style='width:"+(width-0.5)+"%; margin:0.25%; "+background+"'></div>");
					
				} else {
					String border = c.getPlace() != null && c.getPlace().getPlaceType().getColour()!=null
									?"border:1px solid; border-color:"+c.getPlace().getPlaceType().getColour().toWebHexString()+";"
									:"";
					
					boolean playerOnTile = Main.game.getPlayer().getWorldLocation()==world && Main.game.getPlayer().getLocation().getX()==j && Main.game.getPlayer().getLocation().getY()==i;
					boolean dangerousTile = c.getPlace().getPlaceType().isDangerous();
					
					mapSB.append(
							"<div class='map-icon"+(dangerousTile?" dangerous":"")+"'"
									+ " style='width:"+(width-0.5)+"%; margin:0.25%; "+border+" "+background+" "
										+(c.getPlace().getPlaceType()!=PlaceType.GENERIC_IMPASSABLE
												&& ((Main.game.getPlayer().getMana()>=Spell.TELEPORT.getModifiedCost(Main.game.getPlayer())
														&& c.isTravelledTo()
														&& Main.game.getPlayer().isAbleToTeleport())
													|| Main.game.isDebugMode())
											?"cursor:pointer;"
											:"")
									+"' id='MAP_NODE_" + i + "_" + j + "'>"
								+(playerOnTile && (c.getPlace() == null || c.getPlace().getSVGString()==null)
									?getPlayerIcon(dangerousTile)
									:"")
								+ (c.getPlace() != null && c.getPlace().getSVGString()!=null
									? "<div class='map-icon-content' style='background-color:"+c.getPlace().getPlaceType().getColour().toWebHexString()+";"
											+ " width:75%; height:75%; margin:12.5%; border-radius:50%;"
										+(playerOnTile?" border:2px solid "+getPlayerIconColour(dangerousTile).toWebHexString()+";":"")+"'>"
											+c.getPlace().getSVGString()+"</div>"
									: "")
								+(playerOnTile?"<div class='overlay map-player'></div>":""));

					if(withFastTravelAndIcons) {
						appendNPCIcon(Main.game.getWorlds().get(world), j, i);
					}
					appendNotVisitedLayer(Main.game.getWorlds().get(world), j, i);
					
					mapSB.append("</div>");
				}
			}
		}
		
		mapSB.append("</div>");
		
		return mapSB.toString();
	}
	
	private String getPlayerIcon(boolean dangerous) {
		if (dangerous) {
			return ("<div class='map-icon-content' style='width:75%; height:75%; margin:12.5%; border-radius:50%;'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerMapDangerousIcon() + "</div>");
		} else {
			if(Main.game.getPlayer().getFemininityValue()<=Femininity.MASCULINE.getMaximumFemininity()) {
				return ("<div class='map-icon-content' style='width:75%; height:75%; margin:12.5%; border-radius:50%;'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerMapIconMasculine() + "</div>");
				
			} else if(Main.game.getPlayer().getFemininityValue()<=Femininity.ANDROGYNOUS.getMaximumFemininity()) {
				return ("<div class='map-icon-content' style='width:75%; height:75%; margin:12.5%; border-radius:50%;'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerMapIconAndrogynous() + "</div>");
				
			} else{
				return ("<div class='map-icon-content' style='width:75%; height:75%; margin:12.5%; border-radius:50%;'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerMapIconFeminine() + "</div>");
			}
		}
	}
	
	public String renderedHTMLMap() {

		mapSB.setLength(0);

		mapSB.append("<div class='map-container'>");
		
		if(!Main.game.isInNewWorld()) {
			mapSB.append("<div style='left:0; top:0; margin:0; padding:0; width:100%; height:100vw; background-color:#19191a; border-radius:5px;'></div>");
			renderedDisabledMap = true;
			
		}

		int mapSize = zoomedIn ? 2 : 3;
		float unit = zoomedIn ? 18f : 13.25f;
		
		String tileWidthStyle = "width:" + unit + "%; border-width:1%; margin:"+(zoomedIn?1:0.5)+"%;";

		Vector2i playerPosition = Main.game.getPlayer().getLocation();

		// It looks messy, but it works...
		for (int y = playerPosition.getY() + mapSize; y >= playerPosition.getY() - mapSize; y--) {
			for (int x = playerPosition.getX() - mapSize; x <= playerPosition.getX() + mapSize; x++) {
				
				if (x < Main.game.getActiveWorld().WORLD_WIDTH && x >= 0 && y < Main.game.getActiveWorld().WORLD_HEIGHT && y >= 0) {// If within  bounds of map:
					PlaceType placeType = Main.game.getActiveWorld().getCell(x, y).getPlace().getPlaceType();

					if (Main.game.getActiveWorld().getCell(x, y).isDiscovered() || Main.game.isMapReveal()) { // If the tile is discovered:

						if (placeType == PlaceType.GENERIC_IMPASSABLE) {
							mapSB.append("<div class='map-tile blank' style='"+tileWidthStyle+"'></div>");
						} else {

							// This is the "move North" tile:
							if (y == playerPosition.getY() + 1 && x == playerPosition.getX() && placeType != PlaceType.GENERIC_IMPASSABLE) {
								if(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
									mapSB.append("<div class='map-tile movement dangerous' id='upButton' style='"+tileWidthStyle+"'>");
									
								} else {
									mapSB.append("<div class='map-tile movement' id='upButton' style='"+tileWidthStyle+" background:"+placeType.getBackgroundColour().toWebHexString()+"; border-color:"+
												(Main.game.getPlayer().getFemininityValue()<=Femininity.MASCULINE.getMaximumFemininity()
														?Colour.MASCULINE_PLUS
														:(Main.game.getPlayer().getFemininityValue()<=Femininity.ANDROGYNOUS.getMaximumFemininity()
																?Colour.ANDROGYNOUS
																		:Colour.FEMININE_PLUS)).toWebHexString()+";'>");
								}
								
								// Put place icon onto tile:
								if (Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() != null) {
									mapSB.append("<div class='place-icon'><div class='map-tile-content'>" + Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() + "</div></div>");
								}
								
								mapSB.append("<b class='hotkey-icon" + (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous() ? " dangerous" : "") + "'>"
										+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_NORTH) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_NORTH).getFullName()) + "</b>");
								
								appendNPCIcon(Main.game.getActiveWorld(), x, y);
								appendItemsInAreaIcon(x, y);
								appendNotVisitedLayer(Main.game.getActiveWorld(), x, y);
								
								// Close the tile's div:
								mapSB.append("</div>");

								// This is the "move South" tile:
							} else if (y == playerPosition.getY() - 1 && x == playerPosition.getX() && placeType != PlaceType.GENERIC_IMPASSABLE) {
								if(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
									mapSB.append("<div class='map-tile movement dangerous' id='downButton' style='"+tileWidthStyle+"'>");
									
								} else {
									mapSB.append("<div class='map-tile movement' id='downButton' style='"+tileWidthStyle+" background:"+placeType.getBackgroundColour().toWebHexString()+"; border-color:"+
												(Main.game.getPlayer().getFemininityValue()<=Femininity.MASCULINE.getMaximumFemininity()
														?Colour.MASCULINE_PLUS
														:(Main.game.getPlayer().getFemininityValue()<=Femininity.ANDROGYNOUS.getMaximumFemininity()
																?Colour.ANDROGYNOUS
																		:Colour.FEMININE_PLUS)).toWebHexString()+";'>");
								}

								// Put place icon onto tile:
								if (Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() != null) {
									mapSB.append("<div class='place-icon'><div class='map-tile-content'>" + Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() + "</div></div>");
								}
								
								mapSB.append("<b class='hotkey-icon" + (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous() ? " dangerous" : "") + "'>"
										+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_SOUTH) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_SOUTH).getFullName()) + "</b>");

								appendNPCIcon(Main.game.getActiveWorld(), x, y);
								appendItemsInAreaIcon(x, y);
								appendNotVisitedLayer(Main.game.getActiveWorld(), x, y);
								
								// Close the tile's div:
								mapSB.append("</div>");

								// This is the "move West" tile:
							} else if (y == playerPosition.getY() && x == playerPosition.getX() - 1 && placeType != PlaceType.GENERIC_IMPASSABLE) {
								if(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
									mapSB.append("<div class='map-tile movement dangerous' id='leftButton' style='"+tileWidthStyle+"'>");
									
								} else {
									mapSB.append("<div class='map-tile movement' id='leftButton' style='"+tileWidthStyle+" background:"+placeType.getBackgroundColour().toWebHexString()+"; border-color:"+
												(Main.game.getPlayer().getFemininityValue()<=Femininity.MASCULINE.getMaximumFemininity()
														?Colour.MASCULINE_PLUS
														:(Main.game.getPlayer().getFemininityValue()<=Femininity.ANDROGYNOUS.getMaximumFemininity()
																?Colour.ANDROGYNOUS
																		:Colour.FEMININE_PLUS)).toWebHexString()+";'>");
								}

								// Put place icon onto tile:
								if (Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() != null) {
									mapSB.append("<div class='place-icon'><div class='map-tile-content'>" + Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() + "</div></div>");
								}
								
								mapSB.append("<b class='hotkey-icon" + (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous() ? " dangerous" : "") + "'>"
										+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_WEST) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_WEST).getFullName()) + "</b>");

								appendNPCIcon(Main.game.getActiveWorld(), x, y);
								appendItemsInAreaIcon(x, y);
								appendNotVisitedLayer(Main.game.getActiveWorld(), x, y);
								
								// Close the tile's div:
								mapSB.append("</div>");

								// This is the "move East" tile:
							} else if (y == playerPosition.getY() && x == playerPosition.getX() + 1 && placeType != PlaceType.GENERIC_IMPASSABLE) {
								if(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
									mapSB.append("<div class='map-tile movement dangerous' id='rightButton' style='"+tileWidthStyle+"'>");
									
								} else {
									mapSB.append("<div class='map-tile movement' id='rightButton' style='"+tileWidthStyle+" background:"+placeType.getBackgroundColour().toWebHexString()+"; border-color:"+
												(Main.game.getPlayer().getFemininityValue()<=Femininity.MASCULINE.getMaximumFemininity()
														?Colour.MASCULINE_PLUS
														:(Main.game.getPlayer().getFemininityValue()<=Femininity.ANDROGYNOUS.getMaximumFemininity()
																?Colour.ANDROGYNOUS
																		:Colour.FEMININE_PLUS)).toWebHexString()+";'>");
								}

								// Put place icon onto tile:
								if (Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() != null) {
									mapSB.append("<div class='place-icon'><div class='map-tile-content'>" + Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() + "</div></div>");
								}

								mapSB.append("<b class='hotkey-icon" + (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous() ? " dangerous" : "") + "'>"
										+ (Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_EAST) == null ? "" : Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.MOVE_EAST).getFullName()) + "</b>");

								appendNPCIcon(Main.game.getActiveWorld(), x, y);
								appendItemsInAreaIcon(x, y);
								appendNotVisitedLayer(Main.game.getActiveWorld(), x, y);
								
								// Close the tile's div:
								mapSB.append("</div>");

							} else {
								if(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
									mapSB.append("<div class='map-tile" + (y == playerPosition.getY() && x == playerPosition.getX() ? " player dangerous" : " dangerous") + "' style='"+tileWidthStyle+"'>");
									
								} else {
									mapSB.append("<div class='map-tile" + (y == playerPosition.getY() && x == playerPosition.getX() ? " player" : "") + "' style='"+tileWidthStyle+" background:"+placeType.getBackgroundColour().toWebHexString()+";'>");
									
								}

								// Put place icon onto tile:
								if (Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() != null) { //TODO border
									if (y == playerPosition.getY() && x == playerPosition.getX()) {
										mapSB.append("<div class='place-icon' style='margin:calc(18% - 4px); width:64%;'>"
												+ "<div class='map-tile-content' style='background-color:"+getPlayerIconColour(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()).toWebHexString()+";"
														+ "border:4px solid "+getPlayerIconColour(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()).toWebHexString()+"; border-radius:50%;'>"
												+ Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() + "</div></div>");
									} else {
										mapSB.append("<div class='place-icon' style='margin:18%;width:64%;'><div class='map-tile-content'>" + Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() + "</div></div>");
									}

								} else if (y == playerPosition.getY() && x == playerPosition.getX()) {

									if (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
										mapSB.append("<div class='place-icon' style='margin:18%;width:64%;'><div class='map-tile-content'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerMapDangerousIcon() + "</div></div>");
									} else {
										if(Main.game.getPlayer().getFemininityValue()<=Femininity.MASCULINE.getMaximumFemininity()) {
											mapSB.append("<div class='place-icon' style='margin:18%;width:64%;'><div class='map-tile-content'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerMapIconMasculine() + "</div></div>");
											
										} else if(Main.game.getPlayer().getFemininityValue()<=Femininity.ANDROGYNOUS.getMaximumFemininity()) {
											mapSB.append("<div class='place-icon' style='margin:18%;width:64%;'><div class='map-tile-content'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerMapIconAndrogynous() + "</div></div>");
											
										} else{
											mapSB.append("<div class='place-icon' style='margin:18%;width:64%;'><div class='map-tile-content'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerMapIconFeminine() + "</div></div>");
										}
									}
								}
								
								appendNPCIcon(Main.game.getActiveWorld(), x, y);
								appendItemsInAreaIcon(x, y);
								appendNotVisitedLayer(Main.game.getActiveWorld(), x, y);

								// Close the tile's div:
								mapSB.append("</div>");
							}

						}
						
					} else {
						mapSB.append("<div class='map-tile blank' style='"+tileWidthStyle+"'></div>");
					}
					
				} else {
					mapSB.append("<div class='map-tile blank' style='"+tileWidthStyle+"'></div>");
				}
			}

		}
		
		if(Main.game.isInNewWorld()) {
			if (Main.game.getCurrentDialogueNode().isTravelDisabled()) {
				mapSB.append("<div style='left:0; top:0; margin:0; padding:0; width:100%; height:100vw; background-color:#000; opacity:0.7; border-radius:5px;'></div>");
				renderedDisabledMap = true;
			} else {
				renderedDisabledMap = false;
			}
		}
		
		mapSB.append("</div>");

		
		

		return mapSB.toString();
	}
	
	private void appendNPCIcon(World world, int x, int y) {
		
		List<String> mapIcons = new ArrayList<>();
		List<NPC> charactersPresent = Main.game.getCharactersPresent(world.getCell(x, y));

		for(NPC gc : charactersPresent) {
			mapIcons.add(gc.getMapIcon());
		}
		
		List<NPC> charactersHome = Main.game.getCharactersTreatingCellAsHome(world.getCell(x, y));
		for(NPC gc : charactersHome) {
			if(!charactersPresent.contains(gc) && (charactersHome.size()==1 || (x!=0 && y!=0))) {// && ((gc.isSlave() && gc.getOwner().isPlayer()) || Main.game.getPlayer().getFriendlyOccupants().contains(gc.getId()))) {
				mapIcons.add(gc.getHomeMapIcon());
			}
		}
		
		for(int i = mapIcons.size() ; i>0 ; i--) {
			mapSB.append("<div class='npc-icon' style='left:"+(((i-1)*6)+2)+"px;'>"+mapIcons.get(i-1)+"</div>");
		}
	}
	
	private void appendItemsInAreaIcon(int x, int y) {
		if(Main.game.getActiveWorld().getCell(x, y).getInventory().getInventorySlotsTaken()>0
				|| Main.game.getActiveWorld().getCell(x, y).getInventory().getUniqueQuestWeaponCount()>0
				|| Main.game.getActiveWorld().getCell(x, y).getInventory().getUniqueQuestClothingCount()>0
				|| Main.game.getActiveWorld().getCell(x, y).getInventory().getUniqueQuestItemCount()>0) {
			mapSB.append("<div class='item-icon'>"+SVGImages.SVG_IMAGE_PROVIDER.getItemsOnFloorIcon()+"</div>");
		}
	}
	
	private void appendNotVisitedLayer(World world, int x, int y) {
		if(!world.getCell(x, y).isTravelledTo()) {
			mapSB.append("<div style='position:absolute;width:100%;height:100%;top:0;left:0;background-color:#000;opacity:0.5;border-radius:5px;'></div>");
		}
	}

	public void renderButtons() {
		Main.mainController.setButtonsContent(
				"<div class='quarterContainer'>"
					+ "<div class='button" + (!Main.game.getCurrentDialogueNode().isOptionsDisabled() ? "" : " disabled") + "' id='mainMenu'>"
						+ SVGImages.SVG_IMAGE_PROVIDER.getMenuIcon() + (!Main.game.getCurrentDialogueNode().isOptionsDisabled() ? "" : "<div class='disabledLayer'></div>")
					+ "</div>"
				+ "</div>"

				+ "<div class='quarterContainer'>"
					+ "<div class='button" + (
							Main.game.getPlayer().isMainQuestUpdated()
							|| Main.game.getPlayer().isSideQuestUpdated()
							|| Main.game.getPlayer().isRelationshipQuestUpdated()
							|| Main.getProperties().hasValue(PropertyValue.newWeaponDiscovered)
							|| Main.getProperties().hasValue(PropertyValue.newClothingDiscovered)
							|| Main.getProperties().hasValue(PropertyValue.newItemDiscovered)
							|| Main.getProperties().hasValue(PropertyValue.newRaceDiscovered)
							|| Main.getProperties().hasValue(PropertyValue.levelUpHightlight)
								?" highlight"
								:"")
						+ (!Main.game.getCurrentDialogueNode().isOptionsDisabled() && Main.game.isInNewWorld() ? "" : " disabled") + "' id='journal'>" + SVGImages.SVG_IMAGE_PROVIDER.getJournalIcon()
						+ (!Main.game.getCurrentDialogueNode().isOptionsDisabled() && Main.game.isInNewWorld() ? "" : "<div class='disabledLayer'></div>")
					+ "</div>"
				+ "</div>"

				+ "<div class='quarterContainer'>"
					+ "<div class='button" + (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().getInventorySlotsTaken()>0 ? " highlight" : "")
					+ (Main.mainController.isInventoryDisabled() ? " disabled" : "") + "' id='inventory'>"
						+ SVGImages.SVG_IMAGE_PROVIDER.getInventoryIcon()
						+ (Main.mainController.isInventoryDisabled() ? "<div class='disabledLayer'></div>" : "")
					+ "</div>"
				+ "</div>"

				+ "<div class='quarterContainer'>"
					+ "<div class='button" + (Main.game.getCharactersPresent().isEmpty() && Main.game.getCurrentDialogueNode().getDialogueNodeType() != DialogueNodeType.CHARACTERS_PRESENT ? " disabled" : "")
					+ "' id='charactersPresent'>" + SVGImages.SVG_IMAGE_PROVIDER.getPeopleIcon()
						+ (Main.game.getCharactersPresent().isEmpty() && Main.game.getCurrentDialogueNode().getDialogueNodeType() != DialogueNodeType.CHARACTERS_PRESENT ? "<div class='disabledLayer'></div>" : "")
					+ "</div>"
				+ "</div>"

				+ "<div class='quarterContainer'>"
					+ "<div class='button" + (Main.game.getCurrentDialogueNode().isTravelDisabled() ? " disabled" : "") + "' id='mapZoom'>"
						+ (RenderingEngine.isZoomedIn() ? SVGImages.SVG_IMAGE_PROVIDER.getZoomOutIcon() : SVGImages.SVG_IMAGE_PROVIDER.getZoomInIcon()) + (Main.game.getCurrentDialogueNode().isTravelDisabled() ? "<div class='disabledLayer'></div>" : "")
					+ "</div>"
				+ "</div>");
	}

	public static boolean isZoomedIn() {
		return zoomedIn;
	}

	public static void setZoomedIn(boolean zoomedIn) {
		RenderingEngine.zoomedIn = zoomedIn;
	}

	public static boolean isRenderedDisabledMap() {
		return renderedDisabledMap;
	}

	public static void setRenderedDisabledMap(boolean renderedDisabledMap) {
		RenderingEngine.renderedDisabledMap = renderedDisabledMap;
	}
	
	private String getClassRarityIdentifier(Rarity rarity) {
		return (rarity == Rarity.COMMON ? " common" : "")
				+ (rarity == Rarity.UNCOMMON ? " uncommon" : "")
				+ (rarity == Rarity.RARE ? " rare" : "")
				+ (rarity == Rarity.EPIC ? " epic" : "")
				+ (rarity == Rarity.LEGENDARY ? " legendary" : "")
				+ (rarity == Rarity.QUEST ? " quest" : "")
				+ (rarity == Rarity.JINXED ? " jinxed" : "");
	}
	
	private static String getAttributeBar(String SVGImage, Colour barColour, float attributeValue, float attributeMaximum, String id) {
		return "<div class='full-width-container' style='margin:8 0 0 0; margin:0; padding:0;'>"
					+ "<div class='icon small'>"
						+ "<div class='icon-content'>"
							+ SVGImage
						+ "</div>"
					+ "</div>"
					+ "<div class='barBackgroundAtt'>"
						+ "<div style='width:" + (attributeValue/attributeMaximum) * 100 + "%; height:5vw; background:" + barColour.toWebHexString() + "; float:left; border-radius: 2px;'></div>"
					+ "</div>"
					+ "<p style='text-align:center; margin:0; padding:0; line-height:12vw;'>"
						+ (int) Math.ceil(attributeValue)
					+ "</p>"
					+ "<div class='overlay' id='" + id + "'></div>"
				+ "</div>";
	}
	
	private static String getAttributeBarHalf(String SVGImage, Colour barColour, float attributeValue, float attributeMaximum, String id) {
		return "<div class='half-width-container' style='margin:8 0 0 0; margin:0; padding:0;'>"
					+ "<div class='icon small' style='width:20%;'>"
						+ "<div class='icon-content'>"
							+ SVGImage
						+ "</div>"
					+ "</div>"
					+ "<div class='barBackgroundAtt' style='width:50%;'>"
						+ "<div style='width:" + (attributeValue/attributeMaximum) * 100 + "%; height:5vw; background:" + barColour.toWebHexString() + "; float:left; border-radius: 2px;'></div>"
					+ "</div>"
					+ "<p style='text-align:center; margin:0; padding:0; line-height:12vw; color:" + barColour.toWebHexString() + ";'>"
						+ (int) Math.ceil(attributeValue)
					+ "</p>"
					+ "<div class='overlay' id='" + id + "'></div>"
				+ "</div>";
	}
	
	private static String getAttributeBarThird(String SVGImage, Colour barColour, float attributeValue, float attributeMaximum, String id) {
		return "<div class='half-width-container' style='width:33.3%; margin:8 0 0 0; margin:0; padding:0;'>"
					+ "<div class='icon small' style='width:30%;'>"
						+ "<div class='icon-content'>"
							+ SVGImage
						+ "</div>"
					+ "</div>"
					+ "<div class='barBackgroundAtt' style='width:30%;'>"
						+ "<div style='width:" + (attributeValue/attributeMaximum) * 100 + "%; height:5vw; background:" + barColour.toWebHexString() + "; float:left; border-radius: 2px;'></div>"
					+ "</div>"
					+ "<p style='text-align:center; margin:0; padding:0; line-height:12vw; color:" + barColour.toWebHexString() + ";'>"
						+ (int) Math.ceil(attributeValue)
					+ "</p>"
					+ "<div class='overlay' id='" + id + "'></div>"
				+ "</div>";
	}
	
	private static String getCharacterPanelDiv(boolean compact, String idPrefix, GameCharacter character) {
		StringBuilder panelSB = new StringBuilder();
		
		panelSB.append(
				"<div class='attribute-container' style='"
						+ (Main.game.isInCombat()&&Combat.getTargetedCombatant(Main.game.getPlayer()).equals(character)?"border:2px solid "+Colour.GENERIC_COMBAT.toWebHexString()+";":"border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+";")
							+ "'>"
					+ "<div class='full-width-container' style='margin-bottom:4px;'>"
						+ "<div class='icon' style='width:12%'>"
							+ "<div class='icon-content'>"
								+ (character.isRaceConcealed()?SVGImages.SVG_IMAGE_PROVIDER.getRaceUnknown():character.getMapIcon())
							+ "</div>"
							+"<div class='overlay' id='" + idPrefix + Attribute.EXPERIENCE.getName() + "' style='cursor:pointer;'></div>"
						+ "</div>"
						+ "<div class='full-width-container' style='text-align:center;padding:0;margin:0;float:left;width:86%;'>"
							+ "<div class='full-width-container' style='text-align:center;padding:0;margin:0;'>"
								+ "<b style='color:"+ Femininity.valueOf(character.getFemininityValue()).getColour().toWebHexString() + ";'>"
									+ (character.getName().length() == 0
											? Util.capitaliseSentence(character.isFeminine()?character.getSubspecies().getSingularFemaleName(character):character.getSubspecies().getSingularMaleName(character))
											: Util.capitaliseSentence(character.getName()))
								+"</b>"
								+ " - Level "+ character.getLevel()
							+"</div>"
							+ "<div class='full-width-container' style='text-align:center;padding:0;margin:0;background:#333; border-radius: 2px;'>"
								+ (character.getLevel() != GameCharacter.LEVEL_CAP
									? "<div style='mix-blend-mode: difference; width:" + (character.getExperience() / (character.getLevel() * 10f)) * 100 + "%; height:2vw; background:" + Colour.CLOTHING_BLUE_LIGHT.toWebHexString()
											+ "; float:left; border-radius: 2px;'></div>"
									: "<div style=' mix-blend-mode: difference; width:100%; height:2vw; background:" + Colour.GENERIC_EXCELLENT.toWebHexString() + "; float:left; border-radius: 2px;'></div>")
							+"</div>"
							+"<div class='overlay' id='"+idPrefix+"ATTRIBUTES' style='cursor:pointer;'></div>"
						+"</div>"
						+ (character.isPlayer()
								?"<div class='full-width-container' style='text-align:center;'>"
									+ "<div class='half-width-container' style='padding:0 8px;'>"+ UtilText.formatAsMoney(character.getMoney(), "b") +"</div>"
									+ "<div class='half-width-container' style='padding:0 8px;'>"+ UtilText.formatAsEssences(character.getEssenceCount(TFEssence.ARCANE), "b", true) +"</div>"
								+"</div>"
								:"")
					+"</div>");
		
		panelSB.append("<hr style='border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+"; margin: 2px 0;'/>");
		

		panelSB.append(
				getAttributeBarThird(PhysiqueLevel.getPhysiqueLevelFromValue(character.getAttributeValue(Attribute.MAJOR_PHYSIQUE)).getRelatedStatusEffect().getSVGString(character),
						Attribute.MAJOR_PHYSIQUE.getColour(),
						character.getAttributeValue(Attribute.MAJOR_PHYSIQUE),
						100,
						idPrefix + Attribute.MAJOR_PHYSIQUE.getName())
				
				+getAttributeBarThird(IntelligenceLevel.getIntelligenceLevelFromValue(character.getAttributeValue(Attribute.MAJOR_ARCANE)).getRelatedStatusEffect().getSVGString(character),
						Attribute.MAJOR_ARCANE.getColour(),
						character.getAttributeValue(Attribute.MAJOR_ARCANE),
						100,
						idPrefix + Attribute.MAJOR_ARCANE.getName())
			
				+getAttributeBarThird(CorruptionLevel.getCorruptionLevelFromValue(character.getAttributeValue(Attribute.MAJOR_CORRUPTION)).getRelatedStatusEffect().getSVGString(character),
						Attribute.MAJOR_CORRUPTION.getColour(),
						character.getAttributeValue(Attribute.MAJOR_CORRUPTION),
						100,
						idPrefix + Attribute.MAJOR_CORRUPTION.getName())
				);
		
		panelSB.append("<hr style='border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+"; margin: 2px 0;'/>");
		
		if(compact) {
			panelSB.append(
					getAttributeBarThird(Attribute.HEALTH_MAXIMUM.getSVGString(),
							Colour.ATTRIBUTE_HEALTH,
							character.getHealth(),
							character.getAttributeValue(Attribute.HEALTH_MAXIMUM),
							idPrefix + Attribute.HEALTH_MAXIMUM.getName())
	
					+getAttributeBarThird(Attribute.MANA_MAXIMUM.getSVGString(),
							Colour.ATTRIBUTE_MANA,
							character.getMana(),
							character.getAttributeValue(Attribute.MANA_MAXIMUM),
							idPrefix + Attribute.MANA_MAXIMUM.getName())
					
					+getAttributeBarThird(LustLevel.getLustLevelFromValue(character.getLust()).getRelatedStatusEffect().getSVGString(character),
							Colour.ATTRIBUTE_LUST,
							character.getLust(),
							100,
							idPrefix + Attribute.LUST.getName()));
			
		} else {
			panelSB.append(
					getAttributeBar(Attribute.HEALTH_MAXIMUM.getSVGString(),
							Colour.ATTRIBUTE_HEALTH,
							character.getHealth(),
							character.getAttributeValue(Attribute.HEALTH_MAXIMUM),
							idPrefix + Attribute.HEALTH_MAXIMUM.getName())
	
					+getAttributeBar(Attribute.MANA_MAXIMUM.getSVGString(),
							Colour.ATTRIBUTE_MANA,
							character.getMana(),
							character.getAttributeValue(Attribute.MANA_MAXIMUM),
							idPrefix + Attribute.MANA_MAXIMUM.getName())
					
					+getAttributeBar(LustLevel.getLustLevelFromValue(character.getLust()).getRelatedStatusEffect().getSVGString(character),
							Colour.ATTRIBUTE_LUST,
							character.getLust(),
							100,
							idPrefix + Attribute.LUST.getName()));
		}
		
		
		// Status effects:
		panelSB.append("<hr style='border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+"; margin: 2px 0;'/>"
				+"<div class='attribute-container' style='padding:0; overflow-y: auto;'>");
		
//		// Traits:
//		for (Perk trait : character.getTraits()) {
//			panelSB.append(
//					"<div class='icon effect' style='border:1px solid "+Colour.TRAIT.toWebHexString()+"'>"
//							+ "<div class='icon-content'>"
//								+ trait.getSVGString()
//								+ "<div class='overlay' id='TRAIT_" + idPrefix + trait + "'></div>"
//							+ "</div>"
//					+ "</div>");
//		}
		
		if(!Main.game.isInCombat()) {
			// Infinite duration:
			for (StatusEffect se : character.getStatusEffects()) {
				if (!se.isCombatEffect() && character.getStatusEffectDuration(se) == -1 && se.renderInEffectsPanel())
					panelSB.append(
							"<div class='icon effect'>"
									+ "<div class='icon-content'>"
										+ se.getSVGString(character)
										+ "<div class='overlay' id='SE_" + idPrefix + se + "'></div>"
									+ "</div>"
							+ "</div>");
			}
			// Timed:
			for (StatusEffect se : character.getStatusEffects()) {
				if (!se.isCombatEffect() && character.getStatusEffectDuration(se) != -1 && se.renderInEffectsPanel()) {
					int timerHeight = (int) ((character.getStatusEffectDuration(se)/(60*6f))*100);
	
					Colour timerColour = Colour.STATUS_EFFECT_TIME_HIGH;
					
					if(timerHeight>100) {
						timerHeight=100;
						timerColour = Colour.STATUS_EFFECT_TIME_OVERFLOW;
					} else if(timerHeight<15) {
						timerColour = Colour.STATUS_EFFECT_TIME_LOW;
					} else if (timerHeight<50) {
						timerColour = Colour.STATUS_EFFECT_TIME_MEDIUM;
					}
					
					panelSB.append(
							"<div class='icon effect'>"
									+ "<div class='timer-background' style='width:"+timerHeight+"%; background:"+ timerColour.toWebHexString() + ";'></div>"
									+ "<div class='icon-content'>"
										+ se.getSVGString(character)
										+ "<div class='overlay' id='SE_" + idPrefix + se + "'></div>"
									+ "</div>"
							+ "</div>");
				}
			}
			
			if(!character.isPlayer()) {
				for (Fetish f : character.getFetishes()) {
					panelSB.append(
						"<div class='icon effect'>"
							+ "<div class='icon-content'>"
									+ f.getSVGString()
									+ "<div class='overlay' id='FETISH_"+idPrefix + f + "'></div>"
							+ "</div>"
						+ "</div>");
				}
			}
		
		} else {
			// Infinite duration:
			for (StatusEffect se : character.getStatusEffects()) {
				if (character.getStatusEffectDuration(se) == -1 && se.renderInEffectsPanel())
					panelSB.append(
							"<div class='icon effect' "+(se.isCombatEffect()?"style='border:1px solid "+(se.isBeneficial()?Colour.GENERIC_GOOD:Colour.GENERIC_BAD).toWebHexString()+";":"")+"'>"
									+ "<div class='icon-content'>"
										+ se.getSVGString(character)
										+ "<div class='overlay' id='SE_" + idPrefix + se + "'></div>"
									+ "</div>"
							+ "</div>");
			}
			// Timed:
			for (StatusEffect se : character.getStatusEffects()) {
				if (character.getStatusEffectDuration(se) != -1 && se.renderInEffectsPanel() && se.isCombatEffect()) {
					int timerHeight = (int) ((character.getStatusEffectDuration(se)/(60*6f))*100);

					Colour timerColour = Colour.STATUS_EFFECT_TIME_HIGH;
					
					if(timerHeight>100) {
						timerHeight=100;
						timerColour = Colour.STATUS_EFFECT_TIME_OVERFLOW;
					} else if(timerHeight<15) {
						timerColour = Colour.STATUS_EFFECT_TIME_LOW;
					} else if (timerHeight<50) {
						timerColour = Colour.STATUS_EFFECT_TIME_MEDIUM;
					}
					
					panelSB.append(
							"<div class='icon effect' style='border:1px solid "+(se.isBeneficial()?Colour.GENERIC_GOOD:Colour.GENERIC_BAD).toWebHexString()+";'>"
									+ "<div class='timer-background' style='width:"+timerHeight+"%; background:"+ timerColour.toWebHexString() + ";'></div>"
									+ "<div class='icon-content'>"
										+ se.getSVGString(character)
										+ "<div class='overlay' id='SE_" + idPrefix + se + "'></div>"
									+ "</div>"
							+ "</div>");
				}
			}
			
			for (SpecialAttack sa : character.getSpecialAttacks()) {
				int cooldown = Combat.getCooldown(character, sa);
				if (cooldown > 0) {
					int timerHeight = (int) ((Math.min(5, cooldown)/5f)*100);

					Colour timerColour = Colour.STATUS_EFFECT_TIME_MEDIUM;
					
					if(timerHeight>=100) {
						timerHeight=100;
						timerColour = Colour.STATUS_EFFECT_TIME_LOW;
					} else if(cooldown<2) {
						timerColour = Colour.STATUS_EFFECT_TIME_OVERFLOW;
					} else if (cooldown<4) {
						timerColour = Colour.STATUS_EFFECT_TIME_HIGH;
					}
					
					panelSB.append(
							"<div class='icon effect' style='border:1px solid "+Colour.SPECIAL_ATTACK.toWebHexString()+"'>"
									+ "<div class='timer-background' style='width:"+timerHeight+"%; background:"+ timerColour.toWebHexString() + ";'></div>"
									+ "<div class='icon-content'>"
										+ sa.getSVGString()
									+ "</div>"
									+ "<div style='width:100%;height:100%;position:absolute;right:0; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getStopwatch()+"</div>"
									+ "<div class='overlay' id='SA_" + idPrefix + sa + "'></div>"
							+ "</div>");
					
				}
			}
		}
		
		panelSB.append("</div></div>");
		
		return panelSB.toString();
	}
	
	
	
	
	private static String getCharacterPanelSexDiv(boolean compact, String idPrefix, GameCharacter character) {
		StringBuilder panelSB = new StringBuilder();
		
		panelSB.append(
				"<div class='attribute-container' style='"
						+ (Sex.getActivePartner()!=null && Sex.getActivePartner().equals(character)
							?"border:2px solid "+Colour.GENERIC_ARCANE.toWebHexString()+";"
							:"border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+";")
						+ "'>"
					+ "<div class='full-width-container' style='margin-bottom:4px;'>"
						+ "<div class='icon' style='width:12%'>"
							+ "<div class='icon-content'>"
							+ (character.isRaceConcealed()?SVGImages.SVG_IMAGE_PROVIDER.getRaceUnknown():character.getMapIcon())
							+ "</div>"
							+"<div class='overlay' id='" + idPrefix + Attribute.EXPERIENCE.getName() + "' style='cursor:pointer;'></div>"
						+ "</div>"
						+ "<div class='full-width-container' style='text-align:center;padding:0;margin:0;float:left;width:86%;'>"
							+ "<div class='full-width-container' style='text-align:center;padding:0;margin:0;'>"
								+ "<b style='color:"+ Femininity.valueOf(character.getFemininityValue()).getColour().toWebHexString() + ";'>"
									+ (character.getName().length() == 0
											? Util.capitaliseSentence(character.isFeminine()?character.getSubspecies().getSingularFemaleName(character):character.getSubspecies().getSingularMaleName(character))
											: Util.capitaliseSentence(character.getName()))
								+"</b>"
									+ (Sex.getSexPositionSlot(character)==SexPositionSlot.MISC_WATCHING
										?""
										:" - <span style='color:"+Sex.getSexPace(character).getColour().toWebHexString()+";'>"+ Util.capitaliseSentence(Sex.getSexPace(character).getName())+"</span>")
							+"</div>"
							+ "<div class='full-width-container' style='text-align:center;padding:0;margin:0;background:#333; border-radius: 2px;'>"
								+ (character.getLevel() != GameCharacter.LEVEL_CAP
									? "<div style='mix-blend-mode: difference; width:" + (character.getExperience() / (character.getLevel() * 10f)) * 100 + "%; height:2vw; background:" + Colour.CLOTHING_BLUE_LIGHT.toWebHexString()
											+ "; float:left; border-radius: 2px;'></div>"
									: "<div style=' mix-blend-mode: difference; width:100%; height:2vw; background:" + Colour.GENERIC_EXCELLENT.toWebHexString() + "; float:left; border-radius: 2px;'></div>")
							+"</div>"
							+"<div class='overlay' id='"+idPrefix+"ATTRIBUTES' style='cursor:pointer;'></div>"
						+"</div>"
						+(Sex.getSexPositionSlot(character)==SexPositionSlot.MISC_WATCHING
							?""
							:"<div class='full-width-container' style='text-align:center;'>"
								+ Util.capitaliseSentence(Sex.getSexPositionSlot(character).getName(character))
							+ "</div>")
					+"</div>");
		
		if(Sex.getSexPositionSlot(character)==SexPositionSlot.MISC_WATCHING) {
			panelSB.append("<p style='padding:0;margin:auto 0;text-align:center;color:"+Colour.TEXT_GREY.toWebHexString()+";'>"+Util.capitaliseSentence(Sex.getSexPositionSlot(character).getName(character))+"</p>");
			panelSB.append("</div>");
			
		} else {
			panelSB.append("<hr style='border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+"; margin: 2px 0;'/>");
			
			panelSB.append(
					getAttributeBarHalf(CorruptionLevel.getCorruptionLevelFromValue(character.getAttributeValue(Attribute.MAJOR_CORRUPTION)).getRelatedStatusEffect().getSVGString(character),
							Attribute.MAJOR_CORRUPTION.getColour(),
							character.getAttributeValue(Attribute.MAJOR_CORRUPTION),
							100,
							idPrefix + Attribute.MAJOR_CORRUPTION.getName())
					
					+getAttributeBarHalf(LustLevel.getLustLevelFromValue(character.getLust()).getRelatedStatusEffect().getSVGString(character),
							Colour.ATTRIBUTE_LUST,
							character.getLust(),
							100,
							idPrefix + Attribute.LUST.getName())
					
					+getAttributeBar(ArousalLevel.getArousalLevelFromValue(character.getAttributeValue(Attribute.AROUSAL)).getRelatedStatusEffect().getSVGString(character),
							Colour.ATTRIBUTE_AROUSAL,
							character.getArousal(),
							100,
							idPrefix + Attribute.AROUSAL.getName()));
	
			
			// Status effects:
			panelSB.append("<hr style='border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+"; margin: 2px 0;'/>"
					+"<div class='attribute-container' style='padding:0; overflow-y: auto;'>");
	
	//		// Traits:
	//		for (Perk trait : character.getTraits()) {
	//			panelSB.append(
	//					"<div class='icon effect' style='border:1px solid "+Colour.TRAIT.toWebHexString()+"'>"
	//							+ "<div class='icon-content'>"
	//								+ trait.getSVGString()
	//								+ "<div class='overlay' id='TRAIT_" + idPrefix + trait + "'></div>"
	//							+ "</div>"
	//					+ "</div>");
	//		}
			
			// Infinite duration:
			for (StatusEffect se : character.getStatusEffects()) {
				boolean pointer = false;
				SexAreaInterface si = null;
				switch(se) {
					case PENIS_STATUS:
						si = SexAreaPenetration.PENIS;
						break;
					case ANUS_STATUS:
						si = SexAreaOrifice.ANUS;
						break;
					case ASS_STATUS:
						si = SexAreaOrifice.ASS;
						break;
					case MOUTH_STATUS:
						si = SexAreaOrifice.MOUTH;
						break;
					case BREAST_STATUS:
						si = SexAreaOrifice.BREAST;
						break;
					case NIPPLE_STATUS:
						si = SexAreaOrifice.NIPPLE;
						break;
					case THIGH_STATUS:
						si = SexAreaOrifice.THIGHS;
						break;
					case URETHRA_PENIS_STATUS:
						si = SexAreaOrifice.URETHRA_PENIS;
						break;
					case URETHRA_VAGINA_STATUS:
						si = SexAreaOrifice.URETHRA_VAGINA;
						break;
					case VAGINA_STATUS:
						si = SexAreaOrifice.VAGINA;
						break;
					default:
						break;
				}
				if(Main.game.isInSex() && si!=null) {
					if(!Sex.getCharactersHavingOngoingActionWith(character, si).isEmpty()) {
						pointer = true;
					}
				}
				
				if (se.isSexEffect() && character.getStatusEffectDuration(se) == -1 && se.renderInEffectsPanel()) {
					panelSB.append(
							"<div class='icon effect' "+(pointer?"style='cursor:pointer;'":"")+">"
									+ "<div class='icon-content'>"
										+ se.getSVGString(character)
										+ "<div class='overlay' id='SE_" + idPrefix + se + "'></div>"
									+ "</div>"
							+ "</div>");
				}
			}
			// Timed:
			for (StatusEffect se : character.getStatusEffects()) {
				if (se.isSexEffect() && character.getStatusEffectDuration(se) != -1 && se.renderInEffectsPanel()) {
					int timerHeight = (int) ((character.getStatusEffectDuration(se)/(60*6f))*100);
	
					Colour timerColour = Colour.STATUS_EFFECT_TIME_HIGH;
					
					if(timerHeight>100) {
						timerHeight=100;
						timerColour = Colour.STATUS_EFFECT_TIME_OVERFLOW;
					} else if(timerHeight<15) {
						timerColour = Colour.STATUS_EFFECT_TIME_LOW;
					} else if (timerHeight<50) {
						timerColour = Colour.STATUS_EFFECT_TIME_MEDIUM;
					}
					
					panelSB.append(
							"<div class='icon effect'>"
									+ "<div class='timer-background' style='width:"+timerHeight+"%; background:"+ timerColour.toWebHexString() + ";'></div>"
									+ "<div class='icon-content'>"
										+ se.getSVGString(character)
										+ "<div class='overlay' id='SE_" + idPrefix + se + "'></div>"
									+ "</div>"
							+ "</div>");
				}
			}
			
			if(!character.isPlayer()) {
				for (Fetish f : character.getFetishes()) {
					panelSB.append(
						"<div class='icon effect'>"
							+ "<div class='icon-content'>"
									+ f.getSVGString()
									+ "<div class='overlay' id='FETISH_"+idPrefix + f + "'></div>"
							+ "</div>"
						+ "</div>");
				}
			}
			
			panelSB.append("</div></div>");
		}
		
		return panelSB.toString();
	}

	public static int getPageLeft() {
		return pageLeft;
	}

	public static void setPageLeft(int pageLeft) {
		RenderingEngine.pageLeft = pageLeft;
	}

	public static int getPageRight() {
		return pageRight;
	}

	public static void setPageRight(int pageRight) {
		RenderingEngine.pageRight = pageRight;
	}

	public boolean isRenderingTattoosLeft() {
		return renderingTattoosLeft;
	}

	public void setRenderingTattoosLeft(boolean renderingTattoosLeft) {
		this.renderingTattoosLeft = renderingTattoosLeft;
	}

	public boolean isRenderingTattoosRight() {
		return renderingTattoosRight;
	}

	public void setRenderingTattoosRight(boolean renderingTattoosRight) {
		this.renderingTattoosRight = renderingTattoosRight;
	}
	
}
