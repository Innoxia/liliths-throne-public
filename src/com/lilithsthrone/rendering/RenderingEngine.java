package com.lilithsthrone.rendering;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.KeyboardAction;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.BladderLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.combat.SpecialAttack;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.0
 * @author Innoxia
 */
public enum RenderingEngine {
	ENGINE;
	
	private static boolean zoomedIn = true, renderedDisabledMap = false;

	public static Colour[] orgasmColours = new Colour[]{Colour.AROUSAL_STAGE_ZERO, Colour.AROUSAL_STAGE_ONE, Colour.AROUSAL_STAGE_TWO, Colour.AROUSAL_STAGE_THREE, Colour.AROUSAL_STAGE_FOUR, Colour.AROUSAL_STAGE_FIVE, Colour.GENERIC_ARCANE};
	
	private static InventorySlot[] inventorySlots = {
			InventorySlot.EYES,			InventorySlot.HEAD,			InventorySlot.HAIR,		InventorySlot.HORNS,
			InventorySlot.TORSO_OVER,	InventorySlot.NECK,			InventorySlot.MOUTH,	InventorySlot.FINGER,
			InventorySlot.TORSO_UNDER,	InventorySlot.CHEST,		InventorySlot.NIPPLE,	InventorySlot.WRIST,
			InventorySlot.HIPS,			InventorySlot.STOMACH,		InventorySlot.TAIL,		InventorySlot.HAND,
			InventorySlot.LEG,			InventorySlot.GROIN,		InventorySlot.PENIS,	InventorySlot.ANKLE,
			InventorySlot.FOOT,			InventorySlot.SOCK,			InventorySlot.VAGINA,	InventorySlot.ANUS};
	//InventorySlot.WINGS,
	
	private static InventorySlot[] piercingSlots = {
			InventorySlot.PIERCING_EAR,		InventorySlot.PIERCING_NOSE,	
			InventorySlot.PIERCING_LIP,		InventorySlot.PIERCING_TONGUE,
			InventorySlot.PIERCING_NIPPLE,	InventorySlot.PIERCING_STOMACH,
			InventorySlot.PIERCING_PENIS,	InventorySlot.PIERCING_VAGINA };
	
	
	private RenderingEngine() {
	}

	private StringBuilder inventorySB = new StringBuilder(), equippedPanelSB = new StringBuilder();
	
	public String getInventoryPanel(GameCharacter charactersInventoryToRender, boolean buyback) {
		return getInventoryDiv(Main.game.getPlayer(), false) + (charactersInventoryToRender==null?getInventoryDivGround():getInventoryDiv(charactersInventoryToRender, buyback));
	}
	
	private String getInventoryEquippedPanel(GameCharacter charactersInventoryToRender) {
		
		equippedPanelSB.setLength(0);
		
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
		
		List<InventorySlot> concealedSlots = charactersInventoryToRender.getInventorySlotsConcealed();
		
		for (InventorySlot invSlot : inventorySlots) {
			
			if(!charactersInventoryToRender.isPlayer() && concealedSlots.contains(invSlot)) {
				equippedPanelSB.append("<div class='inventory-item-slot concealed' id='" + invSlot.toString() + "Slot'>"
						+ "<div class='concealedIcon'>"+SVGImages.SVG_IMAGE_PROVIDER.getConcealedIcon()+"</div>"
					+ "</div>");
			} else {
				AbstractClothing clothing = charactersInventoryToRender.getClothingInSlot(invSlot);
				
				if (clothing != null) {
					// add to content:
					equippedPanelSB.append(
							// If slot is sealed:
							"<div class='inventory-item-slot" + getClassRarityIdentifier(clothing.getRarity()) + "'"
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
						equippedPanelSB.append("<div class='inventory-item-slot disabled'>"
													+ (charactersInventoryToRender.isDirtySlot(invSlot) ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
													+ "<div class='overlay' id='" + invSlot.toString() + "Slot'></div>"
												+ "</div>");
						
					} else if (invSlot.slotBlockedByRace(charactersInventoryToRender) != null) {
						equippedPanelSB.append(
								"<div class='inventory-item-slot disabled'>"
									+ (charactersInventoryToRender.isDirtySlot(invSlot) ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
									+ "<div class='overlay' id='" + invSlot.toString() + "Slot'></div>"
									+ "<div class='raceBlockIcon'>" + invSlot.slotBlockedByRace(charactersInventoryToRender).getStatusEffect().getSVGString(charactersInventoryToRender) + "</div>"
								+ "</div>");
						
					} else {
						equippedPanelSB.append("<div class='inventory-item-slot' id='" + invSlot.toString() + "Slot'>"
								+ (charactersInventoryToRender.isDirtySlot(invSlot) ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
								+ "</div>");
					}
				}
			}
		}

		equippedPanelSB.append("</div>");
		
		// Render weapons & piercings:
		equippedPanelSB.append("<div class='inventory-equipped accessories'>");
		
		// Main weapon:
		if (charactersInventoryToRender.getMainWeapon() != null) {
			equippedPanelSB.append(
					"<div class='inventory-item-slot weapon" + getClassRarityIdentifier(charactersInventoryToRender.getMainWeapon().getRarity()) + "'>"
						+ "<div class='inventory-icon-content'>"+charactersInventoryToRender.getMainWeapon().getSVGString()+"</div>"
						+ "<div class='overlay-inventory' id='" + InventorySlot.WEAPON_MAIN.toString() + "Slot'></div>"
					+ "</div>");
		} else {
			equippedPanelSB.append("<div class='inventory-item-slot weapon' id='" + InventorySlot.WEAPON_MAIN.toString() + "Slot'></div>");
		}
		
		// Offhand weapon:
		if (charactersInventoryToRender.getOffhandWeapon() != null) {
			equippedPanelSB.append("<div class='inventory-item-slot weapon" + getClassRarityIdentifier(charactersInventoryToRender.getOffhandWeapon().getRarity()) + "'>"
						+ "<div class='inventory-icon-content'>"+charactersInventoryToRender.getOffhandWeapon().getSVGString()+"</div>"
						+ "<div class='overlay-inventory' id='" + InventorySlot.WEAPON_OFFHAND.toString() + "Slot'></div>"
					+ "</div>");
		} else {
			equippedPanelSB.append("<div class='inventory-item-slot weapon' id='" + InventorySlot.WEAPON_OFFHAND.toString() + "Slot'></div>");
		}
		
		//piercingSlots
		for (InventorySlot invSlot : piercingSlots) {
			
			AbstractClothing clothing = charactersInventoryToRender.getClothingInSlot(invSlot);
			
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
					switch(invSlot){
						case PIERCING_VAGINA:
							if(charactersInventoryToRender.getVaginaType()==VaginaType.NONE || !charactersInventoryToRender.isPiercedVagina())
								equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_EAR:
							if(!charactersInventoryToRender.isPiercedEar())
								equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_LIP:
							if(!charactersInventoryToRender.isPiercedLip())
								equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_NIPPLE:
							if(!charactersInventoryToRender.isPiercedNipple())
								equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_NOSE:
							if(!charactersInventoryToRender.isPiercedNose())
								equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_PENIS:
							if(charactersInventoryToRender.getPenisType()==PenisType.NONE || !charactersInventoryToRender.isPiercedPenis())
								equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_STOMACH:
							if(!charactersInventoryToRender.isPiercedNavel())
								equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_TONGUE:
							if(!charactersInventoryToRender.isPiercedTongue())
								equippedPanelSB.append("<div class='inventory-item-slot piercing disabled' id='" + invSlot.toString() + "Slot'>" + "</div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + invSlot.toString() + "Slot'></div>");
							break;
						default:
							break;
					}
				}
			}
		}
		
		equippedPanelSB.append("</div>");
		
		return equippedPanelSB.toString();
	}
	
	private String getInventoryDiv(GameCharacter charactersInventoryToRender, boolean buyback) {
		
		inventorySB.setLength(0);
		
		String idModifier = (charactersInventoryToRender.isPlayer()?"PLAYER_":"NPC_"+charactersInventoryToRender.getId()+"_");
		
		inventorySB.append("<div class='inventory-container"+(charactersInventoryToRender.isPlayer()?" left":" right")+"'>");
		
		inventorySB.append(
				"<div style='position:absolute; left:16px'>"+ UtilText.formatAsMoney(charactersInventoryToRender.getMoney(), "b") +"</div>"
				+ "<div style='position:absolute; right:16px'>"+ UtilText.formatAsEssences(charactersInventoryToRender.getEssenceCount(TFEssence.ARCANE), "b", true) +"</div>"
				+"<p style='width:100%; text-align:center; padding:0 margin:0;'>"
					+(charactersInventoryToRender.isPlayer()
						?"<b style='color:"+Femininity.valueOf(charactersInventoryToRender.getFemininityValue()).getColour().toWebHexString()+";'>Your</b> <b>Inventory</b>"
						:"<b style='color:"+Femininity.valueOf(charactersInventoryToRender.getFemininityValue()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(charactersInventoryToRender.getName())+"'s</b> <b>Inventory</b>")
				+"</p>");
		
//		inventorySB.append(getInventoryEquippedPanel(charactersInventoryToRender, idModifier));
		
		inventorySB.append("<div class='inventory-not-equipped'>");
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
				inventorySB.append("<div class='inventory-item-slot unequipped'><//div>");
			}
			
		} else {
			// Weapons:
			if (charactersInventoryToRender.getWeaponCount() > 0) {
				appendDivsForItemsToInventory(charactersInventoryToRender, inventorySB, charactersInventoryToRender.getMapOfDuplicateWeapons(), idModifier+"WEAPON_");
			}
			// Clothing:
			if (charactersInventoryToRender.getClothingCount() > 0) {
				appendDivsForItemsToInventory(charactersInventoryToRender, inventorySB, charactersInventoryToRender.getMapOfDuplicateClothing(), idModifier+"CLOTHING_");
			}
			// Items:
			if (charactersInventoryToRender.getItemCount() > 0) {
				appendDivsForItemsToInventory(charactersInventoryToRender, inventorySB, charactersInventoryToRender.getMapOfDuplicateItems(), idModifier+"ITEM_");
			}
			// Fill space:
			for (int i = charactersInventoryToRender.getMaximumInventorySpace(); i > charactersInventoryToRender.getInventorySlotsTaken(); i--) {
				inventorySB.append("<div class='inventory-item-slot unequipped'></div>");
			}
		}
		inventorySB.append("</div>");
		
		
		inventorySB.append("</div>");

		inventorySB.append("</body>");
		
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
	
	
	private String getInventoryDivGround() {
		
		inventorySB.setLength(0);
		
		inventorySB.append("<div class='inventory-container right'>");

		inventorySB.append(
				"<p style='width:100%; text-align:center; padding:0 margin:0;'>"
					+(InventoryDialogue.getNPCInventoryInteraction() ==  InventoryInteraction.CHARACTER_CREATION
							?"<b style='color:"+Colour.BASE_TAN.toWebHexString()+";'>Your wardrobe</b>"
							:"<b style='color:"+Colour.BASE_TAN.toWebHexString()+";'>In this Area</b>")
				+"</p>");
		

		inventorySB.append("<div class='inventory-not-equipped'>");
		// Weapons:
		if (Main.game.getPlayerCell().getInventory().getWeaponCount() > 0) {
			appendDivsForItemsToInventory(null, inventorySB, Main.game.getPlayerCell().getInventory().getMapOfDuplicateWeapons(), "WEAPON_FLOOR_");
		}
		// Clothing:
		if (Main.game.getPlayerCell().getInventory().getClothingCount() > 0) {
			appendDivsForItemsToInventory(null, inventorySB, Main.game.getPlayerCell().getInventory().getMapOfDuplicateClothing(), "CLOTHING_FLOOR_");
		}
		// Items:
		if (Main.game.getPlayerCell().getInventory().getItemCount() > 0) {
			appendDivsForItemsToInventory(null, inventorySB, Main.game.getPlayerCell().getInventory().getMapOfDuplicateItems(), "ITEM_FLOOR_");
		}
		// Fill space:
		for (int i = Main.game.getPlayerCell().getInventory().getMaximumInventorySpace(); i > Main.game.getPlayerCell().getInventory().getInventorySlotsTaken(); i--) {
			inventorySB.append("<div class='inventory-item-slot unequipped'></div>");
		}
		inventorySB.append("</div>");
		
		
		inventorySB.append("<div style='float:left; display:block; text-align:center; margin:0 auto; height:36px; padding:8px 0 8px 0;'>");
		inventorySB.append("</div>");
		
		inventorySB.append("</div>");

		inventorySB.append("</body>");
		
		return inventorySB.toString();
	}
	
	private static void appendDivsForItemsToInventory(GameCharacter charactersInventoryToRender, StringBuilder stringBuilder, Map<? extends AbstractCoreItem, Integer> map, String idPrefix) {
		for (Entry<? extends AbstractCoreItem, Integer> entry : map.entrySet()) {
			AbstractCoreItem item = entry.getKey();

			stringBuilder.append("<div class='inventory-item-slot unequipped "+ item.getDisplayRarity() + "'>"
							+ "<div class='inventory-icon-content'>"+item.getSVGString()+"</div>");

			if (item instanceof AbstractClothing && ((AbstractClothing)item).isDirty()) {
				stringBuilder.append("<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>");
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
				boolean nonPlayerInv = charactersInventoryToRender!=null && charactersInventoryToRender.isPlayer() == false;
				boolean isTraderInv = nonPlayerInv && ((NPC) charactersInventoryToRender).isTrader();

				if (item instanceof AbstractItem) {
					AbstractItem abItem = (AbstractItem)item;
					if (nonPlayerInv || (Main.game.isInSex() && (isTraderInv || abItem.isAbleToBeUsedInSex() == false))
							|| (Main.game.isInCombat() && abItem.isAbleToBeUsedInCombat() == false)) {
						overlay += " disabled";
					}
				} else if (item instanceof AbstractClothing) {
					AbstractClothing clothing = (AbstractClothing)item;
					if (Main.game.isInCombat() || (Main.game.isInSex() && (isTraderInv || clothing.getClothingType().isAbleToBeEquippedDuringSex() == false ))) {
						overlay += " disabled";
					}
				}
			}
			stringBuilder.append(overlay+"' id='" + idPrefix + item.hashCode() + "'>"+getItemCountDiv(entry.getValue())+last_layer+"</div></div>");
		}
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
					uiAttributeSB.append(getCharacterPanelSexDiv(true, character.isPlayer()?"PLAYER_":"NPC_"+character.getId()+"_", character));
				}
				
			} else {
				for(GameCharacter character : Sex.getSubmissiveParticipants().keySet()) {
					uiAttributeSB.append(getCharacterPanelSexDiv(true, character.isPlayer()?"PLAYER_":"NPC_"+character.getId()+"_", character));
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
			
			uiAttributeSB.append(getCharacterPanelCombatDiv(true, "PLAYER_", Main.game.getPlayer()));
			
			for(GameCharacter character : Combat.getAllies()) {
				uiAttributeSB.append(getCharacterPanelCombatDiv(true, "NPC_"+character.getId()+"_", character));
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
			
			uiAttributeSB.append(getCharacterPanelDiv(true, "PLAYER_", Main.game.getPlayer()));
			
			uiAttributeSB.append("</div>");
		}

		uiAttributeSB.append("</div>");
		
		uiAttributeSB.append("<div class='full-width-container' style='background-color:#19191a; border-radius:5px; margin-bottom:1px; padding:4px;'>"
					+ "<div class='full-width-container' style='text-align:center; margin-left:4px;'>"
					+"<div class='item-inline' style='float:left;'><div class='overlay' id='DATE_DISPLAY_TOGGLE'>"+SVGImages.SVG_IMAGE_PROVIDER.getCalendarIcon()+"</div></div>"
							+ "<p style='color:"+Colour.TEXT.getShades(8)[3]+"; float:left; width:50%;'>"
								+ (Main.getProperties().calendarDisplay
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
		
		uiAttributeSB.append((Main.getProperties().twentyFourHourTime
								?Main.game.getDateNow().format(DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH))
								:Main.game.getDateNow().format(DateTimeFormatter.ofPattern("hh:mma", Locale.ENGLISH)))
							+"</p>"
					+ "</div>"
				+ "</div>"
				);
		
		if(Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.INVENTORY || Main.game.isInCombat() || Main.game.isInSex()) {

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
		
		if(Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.CHARACTERS_PRESENT || Main.game.getCurrentDialogueNode() == PhoneDialogue.CONTACTS_CHARACTER) {
			return (NPC) CharactersPresentDialogue.characterViewed;
		}
		
		if(Main.game.isInSex()) {
			return Sex.getActivePartner();
		}
		
		if(InventoryDialogue.getInventoryNPC()!=null) {
			return InventoryDialogue.getInventoryNPC();
		}
		
		if(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected()!=null) {
			return Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected();
		}
		
		return Main.game.getActiveNPC();
	}
	
	public static String getEntryBackgroundColour(boolean alternative) {
		if(Main.getProperties().lightTheme) {
			if(alternative) {
				return "#d9d9d9";
			}
			return "#dddddd";
		} else {
			if(alternative) {
				return "#292929";
			}
			return "#222222";  
		}
	}

	public void renderAttributesPanelRight() {
		uiAttributeSB.setLength(0);
		
			uiAttributeSB.append(
					"<body onLoad='scrollEventLogToBottom()'>"
						+ " <script>"
							+"function scrollEventLogToBottom() {document.getElementById('event-log-inner-id').scrollTop = document.getElementById('event-log-inner-id').scrollHeight;}"
						+ "</script>");
			
			boolean renderNPC = false;
			
			if(Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.CHARACTERS_PRESENT || Main.game.getCurrentDialogueNode() == PhoneDialogue.CONTACTS_CHARACTER) {
				renderNPC = true;
			}
			
			if(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected()!=null) {
				renderNPC = true;
			}
			
			if(getCharacterToRender()!=null
					&& (Main.game.isInCombat() || Main.game.isInSex() || renderNPC
							|| (Main.game.getCurrentDialogueNode().getMapDisplay()==MapDisplay.INVENTORY && InventoryDialogue.getInventoryNPC()!=null))) {
			
				
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
						uiAttributeSB.append(getCharacterPanelSexDiv(true, "NPC_"+character.getId()+"_", character));
					}
					
				} else {
					for(GameCharacter character : Sex.getSubmissiveParticipants().keySet()) {
						uiAttributeSB.append(getCharacterPanelSexDiv(true, "NPC_"+character.getId()+"_", character));
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
					uiAttributeSB.append(getCharacterPanelCombatDiv(true, "NPC_"+character.getId()+"_", character));
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
									+ "<p>"
										+ UtilText.parse(getCharacterToRender(), "[npc.Name]'s Inventory")
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
							"<div class='attribute-container'>"
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
					uiAttributeSB.append(
							"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(count%2==0)+";'>"
								+ "<span style='color:"+character.getFemininity().getColour().toWebHexString()+";'>"+character.getName("A")+"</span>"
								+ " - "
									+ (character.isRaceConcealed()
											?"<span style='color:"+Colour.RACE_UNKNOWN.toWebHexString()+";'>Unknown"
											:"<span style='color:"+character.getRace().getColour().toWebHexString()+";'>"
												+Util.capitaliseSentence((character.isFeminine()?character.getSubspecies().getSingularFemaleName():character.getSubspecies().getSingularMaleName())))
									+"</span>"
								+ "<div class='overlay-inventory' id='CHARACTERS_PRESENT_"+character.getId()+"'></div>"
							+ "</div>");
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
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getDisplayName(true))
										+ "<div class='overlay-inventory' id='WEAPON_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					} else {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(true)+";'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getDisplayName(true))
										+ "<div class='overlay-inventory' id='WEAPON_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					}
					count++;
				}
				for(Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateClothing().entrySet()) {
					if(count%2==0) {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(false)+";'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getDisplayName(true))
										+ "<div class='overlay-inventory' id='CLOTHING_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					} else {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(true)+";'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getDisplayName(true))
										+ "<div class='overlay-inventory' id='CLOTHING_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					}
					count++;
				}
				for(Entry<AbstractItem, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateItems().entrySet()) {
					if(count%2==0) {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(false)+";'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getDisplayName(true))
										+ "<div class='overlay-inventory' id='ITEM_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					} else {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:"+getEntryBackgroundColour(true)+";'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getDisplayName(true))
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
				for(EventLogEntry event : Main.game.getEventLog().subList(Main.game.getEventLog().size()-50, Main.game.getEventLog().size())) {
					if(count%2==0) {
						uiAttributeSB.append("<div class='event-log-entry' style='background:"+getEntryBackgroundColour(false)+";'>"+UtilText.parse(event.getFormattedEntry())+"</div>");
					} else {
						uiAttributeSB.append("<div class='event-log-entry' style='background:"+getEntryBackgroundColour(true)+";'>"+UtilText.parse(event.getFormattedEntry())+"</div>");
					}
					count++;
				}
			} else {
				for(EventLogEntry event : Main.game.getEventLog()) {
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

	public String renderedHTMLMap() {

		mapSB.setLength(0);

		mapSB.append("<div class='map-container'>");
		
		if(!Main.game.isInNewWorld()) {
			mapSB.append("<div style='left:0; top:0; margin:0; padding:0; width:100%; height:100vw; background-color:#19191a; border-radius:5px;'></div>");
			renderedDisabledMap = true;
			
		}

		int mapSize = zoomedIn ? 2 : 3;
		float unit = zoomedIn ? 4.5f : 3f, borderSizeReduction = 2.5f;

		Vector2i playerPosition = Main.game.getPlayer().getLocation();

		// It looks messy, but it works...
		for (int y = playerPosition.getY() + mapSize; y >= playerPosition.getY() - mapSize; y--) {
			for (int x = playerPosition.getX() - mapSize; x <= playerPosition.getX() + mapSize; x++) {
				if (x < Main.game.getActiveWorld().WORLD_WIDTH && x >= 0 && y < Main.game.getActiveWorld().WORLD_HEIGHT && y >= 0) {// If within  bounds of map:

					if (Main.game.getActiveWorld().getCell(x, y).isDiscovered() || Main.game.isDebugMode()) { // If the tile is discovered:

						if (Main.game.getActiveWorld().getCell(x, y).getPlace().getPlaceType() == PlaceType.GENERIC_IMPASSABLE) {
							mapSB.append("<div class='map-tile blank' style='width:" + (4 * unit) + "%;'></div>");
						} else {

							// This is the "move North" tile:
							if (y == playerPosition.getY() + 1 && x == playerPosition.getX() && Main.game.getActiveWorld().getCell(x, y).getPlace().getPlaceType() != PlaceType.GENERIC_IMPASSABLE) {
								if(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
									mapSB.append("<div class='map-tile movement dangerous' id='upButton' style='width:" + (4 * unit - borderSizeReduction) + "%; border-width:1%;'>");
									
								} else {
									mapSB.append("<div class='map-tile movement' id='upButton' style='width:" + (4 * unit - borderSizeReduction) + "%; border-width:1%; border-color:"+
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
								
								appendNPCIcon(x, y);
								appendItemsInAreaIcon(x, y);
								
								// Close the tile's div:
								mapSB.append("</div>");

								// This is the "move South" tile:
							} else if (y == playerPosition.getY() - 1 && x == playerPosition.getX() && Main.game.getActiveWorld().getCell(x, y).getPlace().getPlaceType() != PlaceType.GENERIC_IMPASSABLE) {
								if(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
									mapSB.append("<div class='map-tile movement dangerous' id='downButton' style='width:" + (4 * unit - borderSizeReduction) + "%; border-width:1%;'>");
									
								} else {
									mapSB.append("<div class='map-tile movement' id='downButton' style='width:" + (4 * unit - borderSizeReduction) + "%; border-width:1%; border-color:"+
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

								appendNPCIcon(x, y);
								appendItemsInAreaIcon(x, y);
								
								// Close the tile's div:
								mapSB.append("</div>");

								// This is the "move West" tile:
							} else if (y == playerPosition.getY() && x == playerPosition.getX() - 1 && Main.game.getActiveWorld().getCell(x, y).getPlace().getPlaceType() != PlaceType.GENERIC_IMPASSABLE) {
								if(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
									mapSB.append("<div class='map-tile movement dangerous' id='leftButton' style='width:" + (4 * unit - borderSizeReduction) + "%; border-width:1%;'>");
									
								} else {
									mapSB.append("<div class='map-tile movement' id='leftButton' style='width:" + (4 * unit - borderSizeReduction) + "%; border-width:1%; border-color:"+
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

								appendNPCIcon(x, y);
								appendItemsInAreaIcon(x, y);
								
								// Close the tile's div:
								mapSB.append("</div>");

								// This is the "move East" tile:
							} else if (y == playerPosition.getY() && x == playerPosition.getX() + 1 && Main.game.getActiveWorld().getCell(x, y).getPlace().getPlaceType() != PlaceType.GENERIC_IMPASSABLE) {
								if(Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
									mapSB.append("<div class='map-tile movement dangerous' id='rightButton' style='width:" + (4 * unit - borderSizeReduction) + "%; border-width:1%;'>");
									
								} else {
									mapSB.append("<div class='map-tile movement' id='rightButton' style='width:" + (4 * unit - borderSizeReduction) + "%; border-width:1%; border-color:"+
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

								appendNPCIcon(x, y);
								appendItemsInAreaIcon(x, y);
								
								// Close the tile's div:
								mapSB.append("</div>");

							} else {
								mapSB.append("<div class='map-tile" + (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous() ? (y == playerPosition.getY() && x == playerPosition.getX() ? " player dangerous" : " dangerous")
										: (y == playerPosition.getY() && x == playerPosition.getX() ? " player" : "")) + "' style='width:" + (4 * unit) + "%;'>");

								// Put place icon onto tile:
								if (Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() != null) {
									if (y == playerPosition.getY() && x == playerPosition.getX()) {
										if (Main.game.getActiveWorld().getCell(x, y).getPlace().isDangerous()) {
											mapSB.append("<div class='place-icon' style='margin:7%;width:86%;'><div class='map-tile-content'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerPlaceMapDangerousIcon() + "</div></div>");
											
										} else {
											if(Main.game.getPlayer().getFemininityValue()<=Femininity.MASCULINE.getMaximumFemininity()) {
												mapSB.append("<div class='place-icon' style='margin:7%;width:86%;'><div class='map-tile-content'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerPlaceMapIconMasculine() + "</div></div>");
												
											} else if(Main.game.getPlayer().getFemininityValue()<=Femininity.ANDROGYNOUS.getMaximumFemininity()) {
												mapSB.append("<div class='place-icon' style='margin:7%;width:86%;'><div class='map-tile-content'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerPlaceMapIconAndrogynous() + "</div></div>");
												
											} else{
												mapSB.append("<div class='place-icon' style='margin:7%;width:86%;'><div class='map-tile-content'>" + SVGImages.SVG_IMAGE_PROVIDER.getPlayerPlaceMapIconFeminine() + "</div></div>");
											}
										}
									}
									mapSB.append("<div class='place-icon' style='margin:18%;width:64%;'><div class='map-tile-content'>" + Main.game.getActiveWorld().getCell(x, y).getPlace().getSVGString() + "</div></div>");

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
								
								appendNPCIcon(x, y);
								appendItemsInAreaIcon(x, y);

								// Close the tile's div:
								mapSB.append("</div>");
							}

						}
						
					} else {
						mapSB.append("<div class='map-tile blank' style='width:" + (4 * unit) + "%;'></div>");
					}
					
				} else {
					mapSB.append("<div class='map-tile blank' style='width:" + (4 * unit) + "%;'></div>");
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
	
	private void appendNPCIcon(int x, int y) {
		
		List<String> mapIcons = new ArrayList<>();
		List<NPC> charactersPresent = Main.game.getCharactersPresent(Main.game.getActiveWorld().getCell(x, y));
		
		for(NPC gc : charactersPresent) {
			mapIcons.add(gc.getMapIcon());
		}
		
		for(NPC gc : Main.game.getCharactersTreatingCellAsHome(Main.game.getActiveWorld().getCell(x, y))) {
			if(!charactersPresent.contains(gc) && gc.isSlave() && gc.getOwner().isPlayer()) {
				mapIcons.add(gc.getHomeMapIcon());
			}
		}
		
		for(int i = mapIcons.size() ; i>0 ; i--) {
			mapSB.append("<div class='npc-icon' style='left:"+(((i-1)*6)+2)+"px;'>"+mapIcons.get(i-1)+"</div>");
		}
	}
	
	private void appendItemsInAreaIcon(int x, int y) {
		if(Main.game.getActiveWorld().getCell(x, y).getInventory().getInventorySlotsTaken() != 0) {
			mapSB.append("<div class='item-icon'>"+SVGImages.SVG_IMAGE_PROVIDER.getItemsOnFloorIcon()+"</div>");
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
							|| Main.game.getPlayer().isRomanceQuestUpdated()
							|| Main.getProperties().isNewWeaponDiscovered()
							|| Main.getProperties().isNewClothingDiscovered()
							|| Main.getProperties().isNewItemDiscovered()
							|| Main.getProperties().isNewRaceDiscovered()
							|| Main.game.getPlayer().getPerkPoints()>0
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
					+ "<div class='button" + (Main.game.getCharactersPresent().isEmpty() && Main.game.getCurrentDialogueNode().getMapDisplay() != MapDisplay.CHARACTERS_PRESENT ? " disabled" : "")
					+ "' id='charactersPresent'>" + SVGImages.SVG_IMAGE_PROVIDER.getPeopleIcon()
						+ (Main.game.getCharactersPresent().isEmpty() && Main.game.getCurrentDialogueNode().getMapDisplay() != MapDisplay.CHARACTERS_PRESENT ? "<div class='disabledLayer'></div>" : "")
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
											? Util.capitaliseSentence(character.isFeminine()?character.getSubspecies().getSingularFemaleName():character.getSubspecies().getSingularMaleName())
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
		
		panelSB.append("<hr style='border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+"; margin: 2px 0;'></hr>");
		
		panelSB.append(
				getAttributeBarHalf(PhysiqueLevel.getPhysiqueLevelFromValue(character.getAttributeValue(Attribute.MAJOR_PHYSIQUE)).getRelatedStatusEffect().getSVGString(character),
						Attribute.MAJOR_PHYSIQUE.getColour(),
						character.getAttributeValue(Attribute.MAJOR_PHYSIQUE),
						100,
						idPrefix + Attribute.MAJOR_PHYSIQUE.getName())
				
				+getAttributeBarHalf(IntelligenceLevel.getIntelligenceLevelFromValue(character.getAttributeValue(Attribute.MAJOR_ARCANE)).getRelatedStatusEffect().getSVGString(character),
						Attribute.MAJOR_ARCANE.getColour(),
						character.getAttributeValue(Attribute.MAJOR_ARCANE),
						100,
						idPrefix + Attribute.MAJOR_ARCANE.getName())
			
				+getAttributeBarHalf(CorruptionLevel.getCorruptionLevelFromValue(character.getAttributeValue(Attribute.MAJOR_CORRUPTION)).getRelatedStatusEffect().getSVGString(character),
						Attribute.MAJOR_CORRUPTION.getColour(),
						character.getAttributeValue(Attribute.MAJOR_CORRUPTION),
						100,
						idPrefix + Attribute.MAJOR_CORRUPTION.getName())
				
				+getAttributeBarHalf(LustLevel.getLustLevelFromValue(character.getLust()).getRelatedStatusEffect().getSVGString(character),
						Colour.ATTRIBUTE_LUST,
						character.getLust(),
						100,
						idPrefix + Attribute.LUST.getName()));
		
		panelSB.append("<hr style='border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+"; margin: 2px 0;'></hr>");
		
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
						idPrefix + Attribute.MANA_MAXIMUM.getName()));
		
	
							
		// Status effects:
		panelSB.append("<hr style='border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+"; margin: 2px 0;'></hr>"
				+"<div class='attribute-container' style='padding:0; overflow-y: auto;'>");
		
		// Traits:
		for (Perk trait : character.getTraits()) {
			panelSB.append(
					"<div class='icon"+(compact?" effect":"")+"' style='border:1px solid "+Colour.TRAIT.toWebHexString()+"'>"
							+ "<div class='icon-content'>"
								+ trait.getSVGString()
								+ "<div class='overlay' id='TRAIT_" + idPrefix + trait + "'></div>"
							+ "</div>"
					+ "</div>");
		}
		
		// Infinite duration:
		for (StatusEffect se : character.getStatusEffects()) {
			if (!se.isCombatEffect() && character.getStatusEffectDuration(se)==-1 && se.renderInEffectsPanel())
				panelSB.append(
						"<div class='icon"+(compact?" effect":"")+"'>"
								+ "<div class='icon-content'>"
									+ se.getSVGString(character)
									+ "<div class='overlay' id='SE_" + idPrefix + se + "'></div>"
								+ "</div>"
						+ "</div>");
		}
		// Timed:
		for (StatusEffect se : character.getStatusEffects()) {
			if (!se.isCombatEffect() && character.getStatusEffectDuration(se)!=-1 && se.renderInEffectsPanel()) {
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
						"<div class='icon"+(compact?" effect":"")+"'>"
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
					"<div class='icon"+(compact?" effect":"")+"'>"
						+ "<div class='icon-content'>"
								+ f.getSVGString()
								+ "<div class='overlay' id='FETISH_"+idPrefix + f + "'></div>"
						+ "</div>"
					+ "</div>");
			}
		}
		
		panelSB.append("</div></div>");
		
		return panelSB.toString();
	}
	
	
	
	
	private static String getCharacterPanelSexDiv(boolean compact, String idPrefix, GameCharacter character) {
		StringBuilder panelSB = new StringBuilder();
		
		panelSB.append(
				"<div class='attribute-container' style='"+ (Sex.getActivePartner().equals(character)?"border:2px solid "+Colour.GENERIC_ARCANE.toWebHexString()+";":"border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+";")+ "'>"
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
											? Util.capitaliseSentence(character.isFeminine()?character.getSubspecies().getSingularFemaleName():character.getSubspecies().getSingularMaleName())
											: Util.capitaliseSentence(character.getName()))
								+"</b>"
									+ " - <span style='color:"+Sex.getSexPace(character).getColour().toWebHexString()+";'>"+ Util.capitaliseSentence(Sex.getSexPace(character).getName())+"</span>"
							+"</div>"
							+ "<div class='full-width-container' style='text-align:center;padding:0;margin:0;background:#333; border-radius: 2px;'>"
								+ (character.getLevel() != GameCharacter.LEVEL_CAP
									? "<div style='mix-blend-mode: difference; width:" + (character.getExperience() / (character.getLevel() * 10f)) * 100 + "%; height:2vw; background:" + Colour.CLOTHING_BLUE_LIGHT.toWebHexString()
											+ "; float:left; border-radius: 2px;'></div>"
									: "<div style=' mix-blend-mode: difference; width:100%; height:2vw; background:" + Colour.GENERIC_EXCELLENT.toWebHexString() + "; float:left; border-radius: 2px;'></div>")
							+"</div>"
							+"<div class='overlay' id='"+idPrefix+"ATTRIBUTES' style='cursor:pointer;'></div>"
						+"</div>"
						+"<div class='full-width-container' style='text-align:center;'>"
							+ Util.capitaliseSentence(Sex.getSexPositionSlot(character).getName())
						+ "</div>"
					+"</div>");
		
		panelSB.append("<hr style='border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+"; margin: 2px 0;'></hr>");
		
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
		panelSB.append("<hr style='border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+"; margin: 2px 0;'></hr>"
				+"<div class='attribute-container' style='padding:0; overflow-y: auto;'>");

		// Traits:
		for (Perk trait : character.getTraits()) {
			panelSB.append(
					"<div class='icon"+(compact?" effect":"")+"' style='border:1px solid "+Colour.TRAIT.toWebHexString()+"'>"
							+ "<div class='icon-content'>"
								+ trait.getSVGString()
								+ "<div class='overlay' id='TRAIT_" + idPrefix + trait + "'></div>"
							+ "</div>"
					+ "</div>");
		}
		
		// Infinite duration:
		for (StatusEffect se : character.getStatusEffects()) {
			if (se.isSexEffect() && character.getStatusEffectDuration(se)==-1 && se.renderInEffectsPanel()) {
				panelSB.append(
						"<div class='icon"+(compact?" effect":"")+"'>"
								+ "<div class='icon-content'>"
									+ se.getSVGString(character)
									+ "<div class='overlay' id='SE_" + idPrefix + se + "'></div>"
								+ "</div>"
						+ "</div>");
			}
		}
		// Timed:
		for (StatusEffect se : character.getStatusEffects()) {
			if (se.isSexEffect() && character.getStatusEffectDuration(se)!=-1 && se.renderInEffectsPanel()) {
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
						"<div class='icon"+(compact?" effect":"")+"'>"
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
					"<div class='icon"+(compact?" effect":"")+"'>"
						+ "<div class='icon-content'>"
								+ f.getSVGString()
								+ "<div class='overlay' id='FETISH_"+idPrefix + f + "'></div>"
						+ "</div>"
					+ "</div>");
			}
		}
		
		panelSB.append("</div></div>");
		
		return panelSB.toString();
	}
	
	private static String getCharacterPanelCombatDiv(boolean compact, String idPrefix, GameCharacter character) {
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
											? Util.capitaliseSentence(character.isFeminine()?character.getSubspecies().getSingularFemaleName():character.getSubspecies().getSingularMaleName())
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
					+"</div>");
		
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
		
		panelSB.append("<hr style='border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+"; margin: 2px 0;'></hr>");
		
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
		
	
							
		// Status effects:
		panelSB.append("<hr style='border:1px solid "+Colour.TEXT_GREY_DARK.toWebHexString()+"; padding:0; margin: 2px 0;'></hr>"
				+"<div class='attribute-container' style='padding:0; overflow-y: auto;'>");
		
		// Traits:
		for (Perk trait : character.getTraits()) {
			panelSB.append(
					"<div class='icon"+(compact?" effect":"")+"' style='border:1px solid "+Colour.TRAIT.toWebHexString()+"'>"
							+ "<div class='icon-content'>"
								+ trait.getSVGString()
								+ "<div class='overlay' id='TRAIT_" + idPrefix + trait + "'></div>"
							+ "</div>"
					+ "</div>");
		}
		
		// Infinite duration:
		for (StatusEffect se : character.getStatusEffects()) {
			if (character.getStatusEffectDuration(se)==-1 && se.renderInEffectsPanel())
				panelSB.append(
						"<div class='icon"+(compact?" effect":"")+"'>"
								+ "<div class='icon-content'>"
									+ se.getSVGString(character)
									+ "<div class='overlay' id='SE_" + idPrefix + se + "'></div>"
								+ "</div>"
						+ "</div>");
		}
		// Timed:
		for (StatusEffect se : character.getStatusEffects()) {
			if (character.getStatusEffectDuration(se)!=-1 && se.renderInEffectsPanel()) {
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
						"<div class='icon"+(compact?" effect":"")+"'>"
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
				
//				String counterOverlay = "";
//				
//				if(cooldown == 1) {
//					counterOverlay = ("<div style='width:50%;height:50%;position:absolute;right:0; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCounterOne()+"</div>");
//				} else if(cooldown == 2) {
//					counterOverlay = ("<div style='width:50%;height:50%;position:absolute;right:0; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCounterTwo()+"</div>");
//				} else if(cooldown == 3) {
//					counterOverlay = ("<div style='width:50%;height:50%;position:absolute;right:0; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCounterThree()+"</div>");
//				} else if(cooldown == 4) {
//					counterOverlay = ("<div style='width:50%;height:50%;position:absolute;right:0; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCounterFour()+"</div>");
//				} else if(cooldown == 5) {
//					counterOverlay = ("<div style='width:50%;height:50%;position:absolute;right:0; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCounterFive()+"</div>");
//				} else {
//					counterOverlay = ("<div style='width:50%;height:50%;position:absolute;right:0; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCounterFivePlus()+"</div>");
//				}
				
				panelSB.append(
						"<div class='icon"+(compact?" effect":"")+"' style='border:1px solid "+Colour.SPECIAL_ATTACK.toWebHexString()+"'>"
								+ "<div class='timer-background' style='width:"+timerHeight+"%; background:"+ timerColour.toWebHexString() + ";'></div>"
								+ "<div class='icon-content'>"
									+ sa.getSVGString()
								+ "</div>"
								+ "<div style='width:100%;height:100%;position:absolute;right:0; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getStopwatch()+"</div>"
								+ "<div class='overlay' id='SA_" + idPrefix + sa + "'></div>"
						+ "</div>");
				
			} else {
				panelSB.append(
						"<div class='icon"+(compact?" effect":"")+"' style='border:1px solid "+Colour.SPECIAL_ATTACK.toWebHexString()+"'>"
								+ "<div class='icon-content'>"
									+ sa.getSVGString()
									+ "<div class='overlay' id='SA_" + idPrefix + sa + "'></div>"
								+ "</div>"
						+ "</div>");
			}
		}
		
		for (Spell s : character.getSpells()) {
			panelSB.append(
					"<div class='icon"+(compact?" effect":"")+"' style='border:1px solid "+Colour.DAMAGE_TYPE_SPELL.toWebHexString()+"'>"
							+ "<div class='icon-content'>"
								+ s.getSVGString()
								+ "<div class='overlay' id='SPELL_" + idPrefix + s + "'></div>"
							+ "</div>"
					+ "</div>");
		}
		
		panelSB.append("</div></div>");
		
		return panelSB.toString();
	}
	
}
