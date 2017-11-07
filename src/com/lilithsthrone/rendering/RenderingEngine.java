package com.lilithsthrone.rendering;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.lilithsthrone.game.KeyboardAction;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.FitnessLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.StrengthLevel;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
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
import com.lilithsthrone.world.places.GenericPlaces;
import com.lilithsthrone.world.places.PlaceInterface;

/**
 * @since 0.1.0
 * @version 0.1.87
 * @author Innoxia
 */
public enum RenderingEngine {
	ENGINE;
	
	private static boolean zoomedIn = true, renderedDisabledMap = false;

	private static Colour[] orgasmColours = new Colour[]{Colour.AROUSAL_STAGE_ZERO, Colour.AROUSAL_STAGE_ONE, Colour.AROUSAL_STAGE_TWO, Colour.AROUSAL_STAGE_THREE, Colour.AROUSAL_STAGE_FOUR, Colour.AROUSAL_STAGE_FIVE, Colour.GENERIC_ARCANE};

	private static InventorySlot[] inventorySlots = {
			InventorySlot.EYES,			InventorySlot.HEAD,			InventorySlot.NECK,		InventorySlot.HAIR,		InventorySlot.HORNS,
			InventorySlot.TORSO_OVER,	InventorySlot.TORSO_UNDER,	InventorySlot.CHEST,	InventorySlot.MOUTH,	InventorySlot.WINGS,
			InventorySlot.HAND,			InventorySlot.HIPS,			InventorySlot.STOMACH,	InventorySlot.NIPPLE,	InventorySlot.TAIL,
			InventorySlot.FINGER,		InventorySlot.LEG,			InventorySlot.GROIN,	InventorySlot.ANKLE,	InventorySlot.PENIS,
			InventorySlot.WRIST,		InventorySlot.FOOT,			InventorySlot.SOCK,		InventorySlot.ANUS,		InventorySlot.VAGINA };

	private RenderingEngine() {
	}

	private StringBuilder inventorySB = new StringBuilder(), equippedPanelSB = new StringBuilder();
	
	public String getInventoryPanel(GameCharacter charactersInventoryToRender, boolean buyback) {
		return getInventoryDiv(Main.game.getPlayer(), false) + (charactersInventoryToRender==null?getInventoryDivGround():getInventoryDiv(charactersInventoryToRender, buyback));
	}
	
	public String getInventoryEquippedPanel(GameCharacter charactersInventoryToRender) {
		return getInventoryEquippedPanel(charactersInventoryToRender, "NPC_VIEW_");
	}
	
	private String getInventoryEquippedPanel(GameCharacter charactersInventoryToRender, String idModifier) {
		
		equippedPanelSB.setLength(0);
		
		AbstractClothing clothing;
		Set<InventorySlot> blockedSlots = new HashSet<>();
		
		for (AbstractClothing c : charactersInventoryToRender.getClothingCurrentlyEquipped()) {
			if (c.getClothingType().getIncompatibleSlots() != null) {
				for (InventorySlot is : c.getClothingType().getIncompatibleSlots()) {
					blockedSlots.add(is);
				}
			}
		}
		
		equippedPanelSB.append(
				"<div style='position:absolute; left:16px'>"+ UtilText.formatAsMoney(charactersInventoryToRender.getMoney(), "b") +"</div>"
				+ "<div style='position:absolute; right:16px'>"+ UtilText.formatAsEssences(charactersInventoryToRender.getEssenceCount(TFEssence.ARCANE), "b", true) +"</div>"
				+"<p style='width:100%; text-align:center; padding:0 margin:0;'>"
					+(charactersInventoryToRender.isPlayer()
						?"<b style='color:"+Femininity.valueOf(charactersInventoryToRender.getFemininityValue()).getColour().toWebHexString()+";'>Your</b> <b>Inventory</b>"
						:"<b style='color:"+Femininity.valueOf(charactersInventoryToRender.getFemininityValue()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(charactersInventoryToRender.getName())+"'s</b> <b>Inventory</b>")
				+"</p>");
		
		// Right panel:
		equippedPanelSB.append("<div class='inventory-accessories'>");

//		// Weapons:
		// Main weapon:
		if (charactersInventoryToRender.getMainWeapon() != null) {
			equippedPanelSB.append(
					"<div class='inventory-item-slot weapon" + getClassRarityIdentifier(charactersInventoryToRender.getMainWeapon().getRarity()) + "'>"
						+ "<div class='inventory-icon-content'>"+charactersInventoryToRender.getMainWeapon().getSVGString()+"</div>"
						+ "<div class='overlay' id='" + idModifier + InventorySlot.WEAPON_MAIN.toString()+ "Slot'></div>"
					+ "</div>");
		} else {
			equippedPanelSB.append("<div class='inventory-item-slot weapon' id='" + idModifier + InventorySlot.WEAPON_MAIN.toString() + "Slot'></div>");
		}
		
		// Offhand weapon:
		if (charactersInventoryToRender.getOffhandWeapon() != null) {
			equippedPanelSB.append("<div class='inventory-item-slot weapon" + getClassRarityIdentifier(charactersInventoryToRender.getOffhandWeapon().getRarity()) + "'>"
					+ "<div class='inventory-icon-content'>"+charactersInventoryToRender.getOffhandWeapon().getSVGString()+"</div>"
					+ "<div class='overlay' id='" + idModifier + InventorySlot.WEAPON_OFFHAND.toString() + "Slot'></div>"
					+ "</div>");
		} else {
			equippedPanelSB.append("<div class='inventory-item-slot weapon' id='" + idModifier + InventorySlot.WEAPON_OFFHAND.toString() + "Slot'></div>");
		}

		equippedPanelSB.append("</div><div class='inventory-accessories'>");

		// Piercings:
		InventorySlot[] piercingSlots = {
				InventorySlot.PIERCING_EAR,
				InventorySlot.PIERCING_NOSE,
				InventorySlot.PIERCING_TONGUE,
				InventorySlot.PIERCING_LIP,
				InventorySlot.PIERCING_NIPPLE,
				InventorySlot.PIERCING_STOMACH,
				InventorySlot.PIERCING_VAGINA,
				InventorySlot.PIERCING_PENIS};
		for (InventorySlot invSlot : piercingSlots) {

			if (charactersInventoryToRender != null) {
				clothing = charactersInventoryToRender.getClothingInSlot(invSlot);
			} else {
				clothing = null;
			}
			
			if (clothing != null) {
				// add to content:
				equippedPanelSB.append(
						// If slot is sealed:
						"<div class='inventory-item-slot piercing" + getClassRarityIdentifier(clothing.getRarity()) + "'"
								+ (clothing.isSealed() ? "style='border-width:2px; border-color:" + Colour.SEALED.toWebHexString() + ";border-style:solid;'" : "") + ">"
								
								// Picture:
								+ "<div class='inventory-icon-content'>"+clothing.getSVGString()+"</div>"
								
								// If clothing is displaced:
								+ (!clothing.getDisplacedList().isEmpty() ? "<div class='displacedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getDisplacedIcon() + "</div>" : "")
								// If clothing is cummed in:
								+ (clothing.isDirty() ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
								// If clothing is too masculine:
								+ (clothing.getClothingType().getFemininityMaximum() < charactersInventoryToRender.getFemininityValue() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getMasculineWarningIcon() + "</div>" : "")
								// If clothing is too feminine:
								+ (clothing.getClothingType().getFemininityMinimum() > charactersInventoryToRender.getFemininityValue() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getFeminineWarningIcon() + "</div>" : "")


								+ "<div class='overlay' id='" + idModifier + invSlot.toString() + "Slot'>" + "</div>" + "</div>");

			} else {
				// add to content:
				if (blockedSlots.contains(invSlot))
					equippedPanelSB.append("<div class='inventory-item-slot piercing'><div class='overlay disabled' id='" + idModifier + invSlot.toString() + "Slot'>" + "</div></div>");
				else{
					switch(invSlot){
						case PIERCING_VAGINA:
							if(charactersInventoryToRender.getVaginaType()==VaginaType.NONE || !charactersInventoryToRender.isPiercedVagina())
								equippedPanelSB.append("<div class='inventory-item-slot piercing'><div class='overlay disabled'  id='" + idModifier + invSlot.toString() + "Slot'>" + "</div></div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + idModifier + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_EAR:
							if(!charactersInventoryToRender.isPiercedEar())
								equippedPanelSB.append("<div class='inventory-item-slot piercing'><div class='overlay disabled'  id='" + idModifier + invSlot.toString() + "Slot'>" + "</div></div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + idModifier + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_LIP:
							if(!charactersInventoryToRender.isPiercedLip())
								equippedPanelSB.append("<div class='inventory-item-slot piercing'><div class='overlay disabled'  id='" + idModifier + invSlot.toString() + "Slot'>" + "</div></div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + idModifier + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_NIPPLE:
							if(!charactersInventoryToRender.isPiercedNipple())
								equippedPanelSB.append("<div class='inventory-item-slot piercing'><div class='overlay disabled'  id='" + idModifier + invSlot.toString() + "Slot'>" + "</div></div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + idModifier + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_NOSE:
							if(!charactersInventoryToRender.isPiercedNose())
								equippedPanelSB.append("<div class='inventory-item-slot piercing'><div class='overlay disabled'  id='" + idModifier + invSlot.toString() + "Slot'>" + "</div></div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + idModifier + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_PENIS:
							if(charactersInventoryToRender.getPenisType()==PenisType.NONE || !charactersInventoryToRender.isPiercedPenis())
								equippedPanelSB.append("<div class='inventory-item-slot piercing'><div class='overlay disabled'  id='" + idModifier + invSlot.toString() + "Slot'>" + "</div></div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + idModifier + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_STOMACH:
							if(!charactersInventoryToRender.isPiercedNavel())
								equippedPanelSB.append("<div class='inventory-item-slot piercing'><div class='overlay disabled'  id='" + idModifier + invSlot.toString() + "Slot'>" + "</div></div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + idModifier + invSlot.toString() + "Slot'></div>");
							break;
						case PIERCING_TONGUE:
							if(!charactersInventoryToRender.isPiercedTongue())
								equippedPanelSB.append("<div class='inventory-item-slot piercing'><div class='overlay disabled'  id='" + idModifier + invSlot.toString() + "Slot'>" + "</div></div>");
							else
								equippedPanelSB.append("<div class='inventory-item-slot piercing'  id='" + idModifier + invSlot.toString() + "Slot'></div>");
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
		
		String idModifier = (charactersInventoryToRender.isPlayer()?"PLAYER_":"NPC_");
		
		inventorySB.append("<div class='inventory-container"+(charactersInventoryToRender.isPlayer()?" left":" right")+"'>");
		
		inventorySB.append(getInventoryEquippedPanel(charactersInventoryToRender, idModifier));
		
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
				inventorySB.append("<div class='inventory-item-slot unequipped'></div>");
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
			stringBuilder.append(
					"<div class='inventory-item-slot unequipped "+ entry.getKey().getDisplayRarity() + "'>"
							+ "<div class='inventory-icon-content'>"+entry.getKey().getSVGString()+"</div>"
					+ "<div class='overlay"
					+ (charactersInventoryToRender!=null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING
							? (InventoryDialogue.getInventoryNPC().willBuy(entry.getKey()) || !charactersInventoryToRender.isPlayer() ? "" : " dark")
							: (entry.getKey() instanceof AbstractItem
									?((Main.game.isInSex() && !((AbstractItem)entry.getKey()).isAbleToBeUsedInSex()) || (Main.game.isInCombat() && !((AbstractItem)entry.getKey()).isAbleToBeUsedInCombat())?" disabled":"")
									:(entry.getKey() instanceof AbstractClothing
											?((Main.game.isInSex() && !((AbstractClothing)entry.getKey()).getClothingType().isAbleToBeEquippedDuringSex()) || Main.game.isInCombat() ?" disabled":"")
											:(Main.game.isInSex() || Main.game.isInCombat() ?" disabled":""))))
					+ "' id='" + idPrefix + entry.getKey().hashCode() + "'>"
					+ getItemCountDiv(entry.getValue()));
			
			if(charactersInventoryToRender != null && InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
				if(charactersInventoryToRender.isPlayer()) {
					if (InventoryDialogue.getInventoryNPC().willBuy(entry.getKey())) {
						stringBuilder.append(getItemPriceDiv(entry.getKey().getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier())));
					}
				} else {
					stringBuilder.append(getItemPriceDiv(entry.getKey().getPrice(InventoryDialogue.getInventoryNPC().getSellModifier())));
				}
			}
			
			stringBuilder.append("</div></div>");
		}
	}
	
	private static String getItemCountDiv(int amount) {
		if (amount > 1) {
			return "<div class='item-count'><b>x" + amount + "</b></div>";
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

	private static float
			renderedPlayerStrengthValue = 0, renderedPlayerIntelligenceValue = 0, renderedPlayerFitnessValue = 0, renderedPlayerCorruptionValue = 0,
			renderedPlayerHealthValue = 0, renderedPlayerManaValue = 0, renderedPlayerStaminaValue = 0, renderedPlayerArousalValue = 0,
			
			renderedNPCStrengthValue = 0, renderedNPCIntelligenceValue = 0, renderedNPCFitnessValue = 0, renderedNPCCorruptionValue = 0,
			renderedNPCHealthValue = 0, renderedNPCManaValue = 0, renderedNPCStaminaValue = 0, renderedNPCArousalValue = 0;

	private DialogueNodeOld renderedDialogueNode = null;
	
	public void renderAttributesPanel() {
		uiAttributeSB.setLength(0);
		
		uiAttributeSB.append(
				"<body onLoad='scrollEventLogToBottom()'>"
					+ " <script>"
						+"function scrollEventLogToBottom() {document.getElementById('event-log-inner-id').scrollTop = document.getElementById('event-log-inner-id').scrollHeight;}"
					+ "</script>"
					+ "<div class='full'>"
						
						// Name box:
						+ "<div class='attribute-container'>"
							+ "<div class='full-width-container'>"
								+ "<p class='character-name' style='color:"+ Femininity.valueOf(Main.game.getPlayer().getFemininityValue()).getColour().toWebHexString() + ";'>"
									+ (Main.game.getPlayer().getName().length() == 0 ? (Main.game.getPlayer().getFemininityValue() <= Femininity.MASCULINE.getMaximumFemininity() ? "Hero" : "Heroine") : Main.game.getPlayer().getName())
								+ "</p>"
								+ "<div class='overlay' id='EXTRA_ATTRIBUTES'></div>"
							+ "</div>"
							+ "<div class='full-width-container' style='margin:0;padding:0;'>"
								+ "<p style='text-align:center;'>"
									+ "<b>Level " + Main.game.getPlayer().getLevel()+ "</b> "
										+ (Main.game.getPlayer().getRaceStage().getName()!=""
											?"<b style='color:"+Main.game.getPlayer().getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(Main.game.getPlayer().getRaceStage().getName())+"</b> ":"")
										+ "<b style='color:"+Main.game.getPlayer().getRace().getColour().toWebHexString()+";'>"
										+ (Main.game.getPlayer().isFeminine()?Util.capitaliseSentence(Main.game.getPlayer().getRace().getSingularFemaleName()):Util.capitaliseSentence(Main.game.getPlayer().getRace().getSingularMaleName()))
										+ "</b>"
								+"</p>"
								+ "<div class='barBackgroundExp'>"
								+ (Main.game.getPlayer().getLevel() != 20
									? "<div style=' mix-blend-mode: difference; width:" + (Main.game.getPlayer().getExperience() / (Main.game.getPlayer().getLevel() * 10f)) * 90 + "vw; height:2vw; background:" + Colour.CLOTHING_BLUE_LIGHT.toWebHexString()
											+ "; float:left; border-radius: 2px;'></div>"
									: "<div style=' mix-blend-mode: difference; width:90vw; height:2vw; background:" + Colour.GENERIC_EXCELLENT.toWebHexString() + "; float:left; border-radius: 2px;'></div>")
								+ "</div>"
								+ "<div class='overlay' id='PLAYER_" + Attribute.EXPERIENCE.getName() + "' style='cursor:pointer;'></div>"
							+ "</div>"
							+ "<div class='full-width-container'>"
								+ "<div class='half-width-container' style='text-align:center;'>"
									+ UtilText.formatAsEssences(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE), "b", true)
								+ "</div>"
								+ "<div class='half-width-container' style='text-align:center;'>"
									+ UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "b")
								+ "</div>"
							+ "</div>"
						+ "</div>");
		
		if(Main.mainController.getWebViewAttributes().getHeight()>=750) {
			uiAttributeSB.append("<div class='attribute-container'>"
								+ "<div class='full-width-container' style='margin:8 0 0 0; margin:0; padding:0;'>"
								+ "<div class='icon small'><div class='icon-content'>"
									+ StrengthLevel.getStrengthLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div></div>"
								+ "<div class='barBackgroundAtt'>"
									+ "<div style='width:" + Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH) * 0.65 + "vw; height:5vw; background:" + Colour.GENERIC_ATTRIBUTE.toWebHexString() + "; float:left; border-radius: 2px;'></div>"
								+ "</div>"
								+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:"+ (renderedPlayerStrengthValue < Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH)
										? (Colour.GENERIC_GOOD.toWebHexString())
										: (renderedPlayerStrengthValue > Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH) ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))+ ";'>"
										+ (int) Math.ceil(Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH))
								+ "</p>"
								+ "<div class='overlay' id='PLAYER_" + Attribute.STRENGTH.getName() + "'></div>"
							+ "</div>"
		
							// Intelligence:
							+ "<div class='full-width-container' style='margin:0;padding:0;'>"
								+ "<div class='icon small'><div class='icon-content'>"
									+ IntelligenceLevel.getIntelligenceLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div></div>"
								+ "<div class='barBackgroundAtt'>"
									+ "<div style='width:" + Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE) * 0.65 + "vw; height:5vw; background:" + Colour.GENERIC_ATTRIBUTE.toWebHexString() + "; float:left; border-radius: 2px;'></div>"
								+ "</div>"
								+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:" + (renderedPlayerIntelligenceValue < Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE)
										? (Colour.GENERIC_GOOD.toWebHexString())
										: (renderedPlayerIntelligenceValue > Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE) ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
										+ (int) Math.ceil(Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE))
								+ "</p>"
								+ "<div class='overlay' id='PLAYER_" + Attribute.INTELLIGENCE.getName() + "'></div>"
							+ "</div>"
									
							// Fitness:
							+ "<div class='full-width-container' style='margin:0;padding:0;'>"
								+ "<div class='icon small'><div class='icon-content'>"
									+ FitnessLevel.getFitnessLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.FITNESS)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div></div>"
								+ "<div class='barBackgroundAtt'>"
									+ "<div style='width:" + Main.game.getPlayer().getAttributeValue(Attribute.FITNESS) * 0.65 + "vw; height:5vw; background:" + Colour.GENERIC_ATTRIBUTE.toWebHexString() + "; float:left; border-radius: 2px;'></div>"
								+ "</div>"
								+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:" + (renderedPlayerFitnessValue < Main.game.getPlayer().getAttributeValue(Attribute.FITNESS)
										? (Colour.GENERIC_GOOD.toWebHexString())
										: (renderedPlayerFitnessValue > Main.game.getPlayer().getAttributeValue(Attribute.FITNESS) ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
										+ (int) Math.ceil(Main.game.getPlayer().getAttributeValue(Attribute.FITNESS))
								+ "</p>"
								+ "<div class='overlay' id='PLAYER_" + Attribute.FITNESS.getName() + "'></div>"
							+ "</div>"
									
							// Corruption:
							+ "<div class='full-width-container' style='padding:0;'>"
								+ "<div class='icon small'><div class='icon-content'>"
									+ CorruptionLevel.getCorruptionLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div></div>"
								+ "<div class='barBackgroundAtt corruption'>"
									+ "<div style='width:" + Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION) * 0.65 + "vw; height:5vw; background:"
										+ Attribute.CORRUPTION.getColour().toWebHexString() + "; float:left; border-radius: 2px;'></div>"
								+ "</div>"
								+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:" + (renderedPlayerCorruptionValue < Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)
										? (Colour.GENERIC_GOOD.toWebHexString())
										: (renderedPlayerCorruptionValue > Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION) ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
										+ (int) Math.ceil(Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION))
								+ "</p>"
								+ "<div class='overlay' id='PLAYER_" + Attribute.CORRUPTION.getName() + "'></div>"
							+ "</div>"
									
						+ "</div>");
		} else {
			uiAttributeSB.append("<div class='attribute-container'>"
						+ "<div class='quarter-width-container'>"
						+ "<div class='icon' style='width:45%'><div class='icon-content'>"
							+ StrengthLevel.getStrengthLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div></div>"
							+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>"+ "<b style='color:" + Attribute.STRENGTH.getColour().toWebHexString() + ";'>"
								+ (int) Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH) + "</b>" + "</div>"
							+ "<div class='overlay' id='PLAYER_"+ Attribute.STRENGTH.getName() + "'></div>"
						+ "</div>"

						+ "<div class='quarter-width-container'>"
						+ "<div class='icon' style='width:45%'><div class='icon-content'>"
							+ IntelligenceLevel.getIntelligenceLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div></div>"
							+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>" + "<b style='color:" + Attribute.INTELLIGENCE.getColour().toWebHexString() + ";'>"
								+ (int) Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE) + "</b>" + "</div>"
							+ "<div class='overlay' id='PLAYER_" + Attribute.INTELLIGENCE.getName() + "'></div>"
						+ "</div>"

						+ "<div class='quarter-width-container'>"
						+ "<div class='icon' style='width:45%'><div class='icon-content'>"
							+ FitnessLevel.getFitnessLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.FITNESS)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div></div>"
							+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>" + "<b style='color:" + Attribute.FITNESS.getColour().toWebHexString() + ";'>"
								+ (int) Main.game.getPlayer().getAttributeValue(Attribute.FITNESS) + "</b>" + "</div>"
							+ "<div class='overlay' id='PLAYER_" + Attribute.FITNESS.getName() + "'></div>"
						+ "</div>"

						+ "<div class='quarter-width-container'>"
						+ "<div class='icon' style='width:45%'><div class='icon-content'>"
							+ CorruptionLevel.getCorruptionLevelFromValue(Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div></div>"
							+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>" + "<b style='color:"+ Attribute.CORRUPTION.getColour().toWebHexString() + ";'>"
								+ (int) Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION) + "</b>" + "</div>"
							+ "<div class='overlay' id='PLAYER_" + Attribute.CORRUPTION.getName() + "'></div>"
						+ "</div>"
					+ "</div>");
		}
						
		if(Main.game.isInSex()) {
			Colour playerOrgasmColour = Colour.GENERIC_ARCANE;
			if(Sex.getNumberOfPlayerOrgasms()<orgasmColours.length) {
				playerOrgasmColour = orgasmColours[Sex.getNumberOfPlayerOrgasms()];
			}
			
			uiAttributeSB.append("<div class='attribute-container'>"
					+ "<p style='text-align:center;padding:0;margin:0;'>"
						+(Sex.isPlayerDom()?"<b style='color:"+Colour.BASE_CRIMSON.toWebHexString()+";'>Dominant</b>":"<b style='color:"+Colour.BASE_PINK_LIGHT.toWebHexString()+";'>Submissive</b>")+"<b> partner</b>"
					+"</p>"
					+ "<p style='text-align:center;padding:0;margin:0;'>"
						+ "<b style='color:"+Sex.getSexPacePlayer().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(Sex.getSexPacePlayer().getName())+"</b><b> pace</b>"
					+ "</p>"
					+ "<p style='text-align:center;padding:0;margin:0;'><b>Orgasms: </b><b style='color:"+playerOrgasmColour.toWebHexString()+";'>"+Sex.getNumberOfPlayerOrgasms()+"</b></p>"
					
					// Arousal:
					+ "<div class='full-width-container' style='margin:8 0 0 0; margin:0; padding:0;'>"
						+ "<div class='icon small'><div class='icon-content'>" + ArousalLevel.getArousalLevelFromValue(Main.game.getPlayer().getArousal()).getRelatedStatusEffect().getSVGString(Main.game.getPlayer()) + "</div></div>"
						+ "<div class='barBackgroundAtt'>"
							+ "<div style='width:" + Main.game.getPlayer().getArousal() * 0.65 + "vw; height:5vw; background:"
								+ ArousalLevel.getArousalLevelFromValue(Main.game.getPlayer().getArousal()).getColour().toWebHexString() + "; float:left; border-radius: 2px;'></div>"
						+ "</div>"
						+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:"+ (renderedPlayerArousalValue < Main.game.getPlayer().getArousal()
								? (Colour.GENERIC_GOOD.toWebHexString())
								: (renderedPlayerArousalValue > Main.game.getPlayer().getArousal() ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))+ ";'>"
								+ (int) Math.ceil(Main.game.getPlayer().getArousal())
						+ "</p>"
						+ "<div class='overlay' id='PLAYER_" + Attribute.AROUSAL.getName() + "'></div>"
					+ "</div>"
					
				+ "</div>");
			
										
			// Status effects:

			uiAttributeSB.append("<div class='attribute-container effects'>"
									+ "<p style='text-align:center;padding:0;margin:0;'><b>Effects</b></p>");
			boolean hasStatusEffects = false;
			
			for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
				if (se.isSexEffect()) {
					uiAttributeSB.append(
							"<div class='icon'><div class='icon-content'>"
									+ se.getSVGString(Main.game.getPlayer())
									+ "<div class='overlay' id='SE_PLAYER_" + se + "'></div>"
							+ "</div></div>");
					hasStatusEffects = true;
				}
			}
			
			for (Fetish f : Main.game.getPlayer().getFetishes()) {
				uiAttributeSB.append(
						"<div class='icon'><div class='icon-content'>"
								+ f.getSVGString()
								+ "<div class='overlay' id='FETISH_PLAYER_" + f + "'></div>"
						+ "</div></div>");
				hasStatusEffects = true;
			}
			
			
			if(!hasStatusEffects) {
				uiAttributeSB.append("<p style='text-align:center;padding:0;margin:0;height:12vw;'><b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No sex effects</b></p>");
			}
//			uiAttributeSB.append("</div>");
						
		} else {
		
			// Health, mana and experience:
			uiAttributeSB.append("<div class='attribute-container'>"
						+ "<p style='text-align:center;padding:0;margin:0;'><b>Combat</b></p>"
						
						+ "<div class='full-width-container' style='margin:0;padding:0;'>"
							+ "<div class='icon small'><div class='icon-content'>" + Attribute.HEALTH_MAXIMUM.getSVGString() + "</div></div>" + "<div class='barBackgroundAtt'>" + "<div style='width:"
							+ (Main.game.getPlayer().getHealth() / Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM)) * 65 + "vw; height:5vw; background:" + Colour.ATTRIBUTE_HEALTH.toWebHexString()
							+ "; float:left; border-radius: 2px;'></div>" + "</div>" + "<p style='text-align:center; margin:1 0 0 0; padding:0;color:"
							+ (renderedPlayerHealthValue < Main.game.getPlayer().getHealth() ? Colour.GENERIC_GOOD.toWebHexString() : renderedPlayerHealthValue > Main.game.getPlayer().getHealth() ? (Colour.GENERIC_BAD.toWebHexString()) : "default") + ";'>"
							+ (int) Math.ceil(Main.game.getPlayer().getHealth()) + "</p>" + "<div class='overlay' id='PLAYER_" + Attribute.HEALTH_MAXIMUM.getName() + "'></div>"
						+ "</div>"
	
						+ "<div class='full-width-container' style='margin:0;padding:0;'>"
							+ "<div class='icon small'><div class='icon-content'>" + Attribute.MANA_MAXIMUM.getSVGString() + "</div></div>" + "<div class='barBackgroundAtt'>" + "<div style='width:"
							+ (Main.game.getPlayer().getMana() / Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM)) * 65 + "vw; height:5vw; background:" + Colour.ATTRIBUTE_MANA.toWebHexString()
							+ "; float:left; border-radius: 2px;'></div>" + "</div>" + "<p style='text-align:center; margin:1 0 0 0; padding:0;color:"
							+ (renderedPlayerManaValue < Main.game.getPlayer().getMana() ? (Colour.GENERIC_GOOD.toWebHexString()) : (renderedPlayerManaValue > Main.game.getPlayer().getMana() ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
							+ (int) Math.ceil(Main.game.getPlayer().getMana()) + "</p>" + "<div class='overlay' id='PLAYER_" + Attribute.MANA_MAXIMUM.getName() + "'></div>"
						+ "</div>"
	
						+ "<div class='full-width-container' style='margin:0;padding:0;'>"
							+ "<div class='icon small'><div class='icon-content'>" + Attribute.STAMINA_MAXIMUM.getSVGString() + "</div></div>" + "<div class='barBackgroundAtt'>"
							+ "<div style='width:" + (Main.game.getPlayer().getStaminaPercentage() * 65) + "vw; height:5vw; background:" + Colour.ATTRIBUTE_FITNESS.toWebHexString() + "; float:left; border-radius: 2px;'></div>" + "</div>"
							+ "<p style='text-align:center; margin:1 0 0 0; padding:0;color:"
							+ (renderedPlayerStaminaValue < Main.game.getPlayer().getStamina() ? (Colour.GENERIC_GOOD.toWebHexString()) : (renderedPlayerStaminaValue > Main.game.getPlayer().getStamina() ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))
							+ ";'>" + (int) Math.ceil(Main.game.getPlayer().getStamina()) + "</p>" + "<div class='overlay' id='PLAYER_" + Attribute.STAMINA_MAXIMUM.getName() + "'></div>"
						+ "</div>"
						
					+ "</div>");
			
			
			// Status effects:
			uiAttributeSB.append("<div class='attribute-container effects'>"
									+ "<p style='text-align:center;padding:0;margin:0;'><b>Effects</b></p>");
			
			// Infinite duration:
			for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
				if (!se.isCombatEffect() && Main.game.getPlayer().getStatusEffectDuration(se)==-1 && se.renderInEffectsPanel())
					uiAttributeSB.append(
							"<div class='icon'>"
									+ "<div class='icon-content'>"
										+ se.getSVGString(Main.game.getPlayer())
										+ "<div class='overlay' id='SE_PLAYER_" + se + "'></div>"
									+ "</div>"
							+ "</div>");
			}
			// Timed:
			for (StatusEffect se : Main.game.getPlayer().getStatusEffects()) {
				if (!se.isCombatEffect() && Main.game.getPlayer().getStatusEffectDuration(se)!=-1 && se.renderInEffectsPanel()) {
					int timerHeight = (int) ((Main.game.getPlayer().getStatusEffectDuration(se)/(60*6f))*100);

					Colour timerColour = Colour.STATUS_EFFECT_TIME_HIGH;
					
					if(timerHeight>100) {
						timerHeight=100;
						timerColour = Colour.STATUS_EFFECT_TIME_OVERFLOW;
					} else if(timerHeight<15) {
						timerColour = Colour.STATUS_EFFECT_TIME_LOW;
					} else if (timerHeight<50) {
						timerColour = Colour.STATUS_EFFECT_TIME_MEDIUM;
					}
					
					uiAttributeSB.append(
							"<div class='icon'>"
									+ "<div class='timer-background' style='width:"+timerHeight+"%; background:"+ timerColour.toWebHexString() + ";'></div>"
									+ "<div class='icon-content'>"
										+ se.getSVGString(Main.game.getPlayer())
										+ "<div class='overlay' id='SE_PLAYER_" + se + "'></div>"
									+ "</div>"
							+ "</div>");
				}
			}
//			// Fetishes:
//			for (Fetish f : Main.game.getPlayer().getFetishes()) {
//				uiAttributeSB.append(
//						"<div class='icon'><div class='icon-content'>"
//								+ f.getSVGString()
//								+ "<div class='overlay' id='FETISH_PLAYER_" + f + "'></div>"
//						+ "</div></div>");
//			}
//			// Special attacks:
//			for (SpecialAttack sa : Main.game.getPlayer().getSpecialAttacks()) {
//				uiAttributeSB.append(
//						"<div class='icon'><div class='icon-content'>"
//								+ sa.getSVGString()
//								+ "<div class='overlay' id='SA_" + sa + "'></div>"
//						+ "</div></div>");
//			}
//			if (Main.game.getPlayer().getMainWeapon() != null) {
//				for (Spell s : Main.game.getPlayer().getMainWeapon().getSpells()) {
//					uiAttributeSB.append(
//							"<div class='icon'><div class='icon-content'>"
//									+ s.getSVGString()
//									+ "<div class='overlay' id='SPELL_MAIN_" + s + "'></div>"
//							+ "</div></div>");
//				}
//			}
//			if (Main.game.getPlayer().getOffhandWeapon() != null) {
//				for (Spell s : Main.game.getPlayer().getOffhandWeapon().getSpells()) {
//					uiAttributeSB.append(
//							"<div class='icon'><div class='icon-content'>"
//									+ s.getSVGString()
//									+ "<div class='overlay' id='SPELL_OFFHAND_" + s + "'></div>"
//							+ "</div></div>");
//				}
//			}
		}
		
		//TODO
		uiAttributeSB.append("</div>"
				+ "<div class='attribute-container' style=' margin-bottom:1px;'>"
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
				+ "</div>"
				);
		
		
		if(Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.INVENTORY || Main.game.isInCombat() || Main.game.isInSex()) {
			
			Set<InventorySlot> blockedSlots = new HashSet<>();
			
			for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
				if (c.getClothingType().getIncompatibleSlots() != null) {
					for (InventorySlot is : c.getClothingType().getIncompatibleSlots()) {
						blockedSlots.add(is);
					}
				}
			}
			// EQUIPPED:
			uiAttributeSB.append("<div class='inventory-equipped'>");
			
			for (InventorySlot invSlot : inventorySlots) {
				
				AbstractClothing clothing = Main.game.getPlayer().getClothingInSlot(invSlot);
				
				if (clothing != null) {
					// add to content:
					uiAttributeSB.append(
							// If slot is sealed:
							"<div class='inventory-item-slot" + getClassRarityIdentifier(clothing.getRarity()) + "'"
								+ (clothing.isSealed() ? "style='border-width:2px; border-color:" + Colour.SEALED.toWebHexString() + "; border-style:solid;'" : "") + ">"
								
								// Picture:
								+ "<div class='inventory-icon-content'>"+clothing.getSVGEquippedString(Main.game.getPlayer())+"</div>"
								
								// If clothing is displaced:
								+ (!clothing.getDisplacedList().isEmpty() ? "<div class='displacedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getDisplacedIcon() + "</div>" : "")
								// If clothing is cummed in:
								+ (clothing.isDirty() ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
								// If clothing is too masculine:
								+ (clothing.getClothingType().getFemininityMaximum() < Main.game.getPlayer().getFemininityValue() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getMasculineWarningIcon() + "</div>" : "")
								// If clothing is too feminine:
								+ (clothing.getClothingType().getFemininityMinimum() > Main.game.getPlayer().getFemininityValue() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getFeminineWarningIcon() + "</div>" : "")

								+ "<div class='overlay-inventory' id='" + invSlot.toString() + "Slot'>" + "</div>" + "</div>");
				} else {
					// add to content:
					if (blockedSlots.contains(invSlot)) {
						uiAttributeSB.append("<div class='inventory-item-slot disabled'><div class='overlay' id='" + invSlot.toString() + "Slot'></div></div>");
						
					} else if (invSlot.slotBlockedByRace(Main.game.getPlayer()) != null) {
						uiAttributeSB.append(
								"<div class='inventory-item-slot disabled'>"
									+ "<div class='overlay' id='" + invSlot.toString() + "Slot'></div>"
									+ "<div class='raceBlockIcon'>" + invSlot.slotBlockedByRace(Main.game.getPlayer()).getStatusEffect().getSVGString(Main.game.getPlayer()) + "</div>"
								+ "</div>");
						
					} else {
						uiAttributeSB.append("<div class='inventory-item-slot' id='" + invSlot.toString() + "Slot'></div>");
					}
				}
			}

			uiAttributeSB.append("</div>");
			
		} else {
			uiAttributeSB.append("<div>" + renderedHTMLMap() + "</div>");
		}
		
		uiAttributeSB.append("</body>");

		if (Main.mainController != null) {
			if (Main.game.getCurrentDialogueNode() != null) {
				if (!Main.game.getCurrentDialogueNode().isNoTextForContinuesDialogue() && renderedDialogueNode != Main.game.getCurrentDialogueNode()) {

					renderedPlayerHealthValue = Main.game.getPlayer().getHealth();
					renderedPlayerManaValue = Main.game.getPlayer().getMana();
					renderedPlayerStaminaValue = Main.game.getPlayer().getStamina();
					if (Main.game.isInSex()) {
						renderedPlayerArousalValue = Main.game.getPlayer().getArousal();
					}
					renderedPlayerStrengthValue = Main.game.getPlayer().getAttributeValue(Attribute.STRENGTH);
					renderedPlayerIntelligenceValue = Main.game.getPlayer().getAttributeValue(Attribute.INTELLIGENCE);
					renderedPlayerFitnessValue = Main.game.getPlayer().getAttributeValue(Attribute.FITNESS);
					renderedPlayerCorruptionValue = Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION);
					

					renderedDialogueNode = Main.game.getCurrentDialogueNode();

				}
			}
		}
		
		Main.mainController.setAttributePanelContent(uiAttributeSB.toString());
	}
	
	private static NPC npcToRender = null;
	public static NPC getNpcToRender() {
		return npcToRender;
	}

	public void renderAttributesPanelRight() {
		uiAttributeSB.setLength(0);
		
			uiAttributeSB.append(
					"<body onLoad='scrollEventLogToBottom()'>"
						+ " <script>"
							+"function scrollEventLogToBottom() {document.getElementById('event-log-inner-id').scrollTop = document.getElementById('event-log-inner-id').scrollHeight;}"
						+ "</script>"
						+ "<div class='full'>");
			
			npcToRender = Main.game.getActiveNPC();
			boolean renderNPC = false;
			
			if(InventoryDialogue.getInventoryNPC()!=null) {
				npcToRender = InventoryDialogue.getInventoryNPC();
			}
			
			if(Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.CHARACTERS_PRESENT || Main.game.getCurrentDialogueNode() == PhoneDialogue.CONTACTS_CHARACTER) {
				npcToRender = (NPC) CharactersPresentDialogue.characterViewed;
				renderNPC = true;
			}
			
			if(Main.game.getDialogueFlags().slaveryManagerSlaveSelected!=null) {
				npcToRender = Main.game.getDialogueFlags().slaveryManagerSlaveSelected;
				renderNPC = true;
			}
			
			if(npcToRender!=null
					&& (Main.game.isInCombat() || Main.game.isInSex() || renderNPC
							|| (Main.game.getCurrentDialogueNode().getMapDisplay()==MapDisplay.INVENTORY && InventoryDialogue.getInventoryNPC()!=null))) {
			
				uiAttributeSB.append(
						// Name box:
						"<div class='attribute-container'>"
								+ "<div class='full-width-container'>"
									+ "<p class='character-name' style='color:"+ Femininity.valueOf(npcToRender.getFemininityValue()).getColour().toWebHexString() + ";'>"
										+ (npcToRender.getName().length() == 0
												? (npcToRender.getFemininityValue() <= Femininity.MASCULINE.getMaximumFemininity() ? "Hero" : "Heroine") 
												: Util.capitaliseSentence(npcToRender.getName()))
									+ "</p>"
									+ "<div class='overlay' id='NPC_ATTRIBUTES'></div>"
								+ "</div>"
									+ "<div class='full-width-container' style='margin:0;padding:0;'>"
									+ "<p style='text-align:center;'>"
										+ "<b>Level " + npcToRender.getLevel()+ "</b> "
											+ (npcToRender.getRaceStage().getName()!=""
												?"<b style='color:"+npcToRender.getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(npcToRender.getRaceStage().getName())+"</b> ":"")
											+ "<b style='color:"+npcToRender.getRace().getColour().toWebHexString()+";'>"
											+ (npcToRender.isFeminine()?Util.capitaliseSentence(npcToRender.getRace().getSingularFemaleName()):Util.capitaliseSentence(npcToRender.getRace().getSingularMaleName()))
											+ "</b>"
									+"</p>"
									+ "<div class='barBackgroundExp'>"
									+ (npcToRender.getLevel() != 20
										? "<div style=' mix-blend-mode: difference; width:" + (npcToRender.getExperience() / (npcToRender.getLevel() * 10f)) * 90 + "vw; height:2vw; background:" + Colour.CLOTHING_BLUE_LIGHT.toWebHexString()
												+ "; float:left; border-radius: 2px;'></div>"
										: "<div style=' mix-blend-mode: difference; width:90vw; height:2vw; background:" + Colour.GENERIC_EXCELLENT.toWebHexString() + "; float:left; border-radius: 2px;'></div>")
									+ "</div>"
									+ "<div class='overlay' id='NPC_" + Attribute.EXPERIENCE.getName() + "' style='cursor:pointer;'></div>"
								+ "</div>"
								+ "<div class='full-width-container' style='padding:0 8px 0 8px'>"
									+ "<span style='float:right;'>"
										+ UtilText.formatAsMoney(npcToRender.getMoney(), "b")
									+ "</span>"
								+ "</div>"
							+ "</div>");
				
				if(Main.mainController.getWebViewAttributes().getHeight()>=750) {
						
						uiAttributeSB.append("<div class='attribute-container'>"
							+ "<p style='text-align:center;padding:0;margin:0;'><b>Attributes</b></p>"
				
							// Strength:
							+ "<div class='full-width-container' style='margin:8 0 0 0; margin:0; padding:0;'>"
								+ "<div class='icon small'><div class='icon-content'>"
									+ StrengthLevel.getStrengthLevelFromValue(npcToRender.getAttributeValue(Attribute.STRENGTH)).getRelatedStatusEffect().getSVGString(npcToRender) + "</div></div>"
								+ "<div class='barBackgroundAtt'>"
									+ "<div style='width:" + npcToRender.getAttributeValue(Attribute.STRENGTH) * 0.65 + "vw; height:5vw; background:" + Colour.GENERIC_ATTRIBUTE.toWebHexString() + "; float:left; border-radius: 2px;'></div>"
								+ "</div>"
								+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:"+ (renderedNPCStrengthValue < npcToRender.getAttributeValue(Attribute.STRENGTH)
										? (Colour.GENERIC_GOOD.toWebHexString())
										: (renderedNPCStrengthValue > npcToRender.getAttributeValue(Attribute.STRENGTH) ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))+ ";'>"
										+ (int) Math.ceil(npcToRender.getAttributeValue(Attribute.STRENGTH))
								+ "</p>"
								+ "<div class='overlay' id='NPC_" + Attribute.STRENGTH.getName() + "'></div>"
							+ "</div>"
		
							// Intelligence:
							+ "<div class='full-width-container' style='margin:0;padding:0;'>"
								+ "<div class='icon small'><div class='icon-content'>"
									+ IntelligenceLevel.getIntelligenceLevelFromValue(npcToRender.getAttributeValue(Attribute.INTELLIGENCE)).getRelatedStatusEffect().getSVGString(npcToRender) + "</div></div>"
								+ "<div class='barBackgroundAtt'>"
									+ "<div style='width:" + npcToRender.getAttributeValue(Attribute.INTELLIGENCE) * 0.65 + "vw; height:5vw; background:" + Colour.GENERIC_ATTRIBUTE.toWebHexString() + "; float:left; border-radius: 2px;'></div>"
								+ "</div>"
								+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:" + (renderedNPCIntelligenceValue < npcToRender.getAttributeValue(Attribute.INTELLIGENCE)
										? (Colour.GENERIC_GOOD.toWebHexString())
										: (renderedNPCIntelligenceValue > npcToRender.getAttributeValue(Attribute.INTELLIGENCE) ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
										+ (int) Math.ceil(npcToRender.getAttributeValue(Attribute.INTELLIGENCE))
								+ "</p>"
								+ "<div class='overlay' id='NPC_" + Attribute.INTELLIGENCE.getName() + "'></div>"
							+ "</div>"
									
							// Fitness:
							+ "<div class='full-width-container' style='margin:0;padding:0;'>"
								+ "<div class='icon small'><div class='icon-content'>"
									+ FitnessLevel.getFitnessLevelFromValue(npcToRender.getAttributeValue(Attribute.FITNESS)).getRelatedStatusEffect().getSVGString(npcToRender) + "</div></div>"
								+ "<div class='barBackgroundAtt'>"
									+ "<div style='width:" + npcToRender.getAttributeValue(Attribute.FITNESS) * 0.65 + "vw; height:5vw; background:" + Colour.GENERIC_ATTRIBUTE.toWebHexString() + "; float:left; border-radius: 2px;'></div>"
								+ "</div>"
								+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:" + (renderedNPCFitnessValue < npcToRender.getAttributeValue(Attribute.FITNESS)
										? (Colour.GENERIC_GOOD.toWebHexString())
										: (renderedNPCFitnessValue > npcToRender.getAttributeValue(Attribute.FITNESS) ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
										+ (int) Math.ceil(npcToRender.getAttributeValue(Attribute.FITNESS))
								+ "</p>"
								+ "<div class='overlay' id='NPC_" + Attribute.FITNESS.getName() + "'></div>"
							+ "</div>"
									
							// Corruption:
							+ "<div class='full-width-container' style='padding:0;'>"
								+ "<div class='icon small'><div class='icon-content'>"
									+ CorruptionLevel.getCorruptionLevelFromValue(npcToRender.getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(npcToRender) + "</div></div>"
								+ "<div class='barBackgroundAtt corruption'>"
									+ "<div style='width:" + npcToRender.getAttributeValue(Attribute.CORRUPTION) * 0.65 + "vw; height:5vw; background:"
										+ Attribute.CORRUPTION.getColour().toWebHexString() + "; float:left; border-radius: 2px;'></div>"
								+ "</div>"
								+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:" + (renderedNPCCorruptionValue < npcToRender.getAttributeValue(Attribute.CORRUPTION)
										? (Colour.GENERIC_GOOD.toWebHexString())
										: (renderedNPCCorruptionValue > npcToRender.getAttributeValue(Attribute.CORRUPTION) ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
										+ (int) Math.ceil(npcToRender.getAttributeValue(Attribute.CORRUPTION))
								+ "</p>"
								+ "<div class='overlay' id='NPC_" + Attribute.CORRUPTION.getName() + "'></div>"
							+ "</div>"
									
						+ "</div>");
				} else {
					uiAttributeSB.append(
							"<div class='attribute-container'>"
							+ "<p style='text-align:center;padding:0;margin:0;'><b>Attributes</b></p>"
								+ "<div class='quarter-width-container'>"
								+ "<div class='icon' style='width:45%'><div class='icon-content'>"
									+ StrengthLevel.getStrengthLevelFromValue(npcToRender.getAttributeValue(Attribute.STRENGTH)).getRelatedStatusEffect().getSVGString(npcToRender) + "</div></div>"
									+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>"+ "<b style='color:" + Attribute.STRENGTH.getColour().toWebHexString() + ";'>"
										+ (int) npcToRender.getAttributeValue(Attribute.STRENGTH) + "</b>" + "</div>"
									+ "<div class='overlay' id='NPC_"+ Attribute.STRENGTH.getName() + "'></div>"
								+ "</div>"

								+ "<div class='quarter-width-container'>"
								+ "<div class='icon' style='width:45%'><div class='icon-content'>"
									+ IntelligenceLevel.getIntelligenceLevelFromValue(npcToRender.getAttributeValue(Attribute.INTELLIGENCE)).getRelatedStatusEffect().getSVGString(npcToRender) + "</div></div>"
									+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>" + "<b style='color:" + Attribute.INTELLIGENCE.getColour().toWebHexString() + ";'>"
										+ (int) npcToRender.getAttributeValue(Attribute.INTELLIGENCE) + "</b>" + "</div>"
									+ "<div class='overlay' id='NPC_" + Attribute.INTELLIGENCE.getName() + "'></div>"
								+ "</div>"

								+ "<div class='quarter-width-container'>"
								+ "<div class='icon' style='width:45%'><div class='icon-content'>"
									+ FitnessLevel.getFitnessLevelFromValue(npcToRender.getAttributeValue(Attribute.FITNESS)).getRelatedStatusEffect().getSVGString(npcToRender) + "</div></div>"
									+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>" + "<b style='color:" + Attribute.FITNESS.getColour().toWebHexString() + ";'>"
										+ (int) npcToRender.getAttributeValue(Attribute.FITNESS) + "</b>" + "</div>"
									+ "<div class='overlay' id='NPC_" + Attribute.FITNESS.getName() + "'></div>"
								+ "</div>"

								+ "<div class='quarter-width-container'>"
								+ "<div class='icon' style='width:45%'><div class='icon-content'>"
									+ CorruptionLevel.getCorruptionLevelFromValue(npcToRender.getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(npcToRender) + "</div></div>"
									+ "<div style='text-align:center;height:30px;padding:0;margin:0;line-height:30px;'>" + "<b style='color:"+ Attribute.CORRUPTION.getColour().toWebHexString() + ";'>"
										+ (int) npcToRender.getAttributeValue(Attribute.CORRUPTION) + "</b>" + "</div>"
									+ "<div class='overlay' id='NPC_" + Attribute.CORRUPTION.getName() + "'></div>"
								+ "</div>"
							+ "</div>");
				}
				
				
				
			if(Main.game.isInSex()) {
				Colour NPCOrgasmColour = Colour.GENERIC_ARCANE;
				if(Sex.getNumberOfPartnerOrgasms()<orgasmColours.length) {
					NPCOrgasmColour = orgasmColours[Sex.getNumberOfPartnerOrgasms()];
				}
				
				uiAttributeSB.append("<div class='attribute-container'>"
						+ "<p style='text-align:center;padding:0;margin:0;'>"
							+(!Sex.isPlayerDom()?"<b style='color:"+Colour.BASE_CRIMSON.toWebHexString()+";'>Dominant</b>":"<b style='color:"+Colour.BASE_PINK_LIGHT.toWebHexString()+";'>Submissive</b>")+"<b> partner</b>"
						+"</p>"
						+ "<p style='text-align:center;padding:0;margin:0;'>"
							+ "<b style='color:"+Sex.getSexPacePartner().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(Sex.getSexPacePartner().getName())+"</b><b> pace</b>"
						+ "</p>"
						+ "<p style='text-align:center;padding:0;margin:0;'><b>Orgasms: </b><b style='color:"+NPCOrgasmColour.toWebHexString()+";'>"+Sex.getNumberOfPartnerOrgasms()+"</b></p>"
						
						// Partner arousal:
						+ "<div class='full-width-container' style='margin:8 0 0 0; margin:0; padding:0;'>"
							+ "<div class='icon small'><div class='icon-content'>" + ArousalLevel.getArousalLevelFromValue(Sex.getPartner().getArousal()).getRelatedStatusEffect().getSVGString(Sex.getPartner()) + "</div></div>"
							+ "<div class='barBackgroundAtt'>"
								+ "<div style='width:" + Sex.getPartner().getArousal() * 0.65 + "vw; height:5vw; background:"
									+ ArousalLevel.getArousalLevelFromValue(Sex.getPartner().getArousal()).getColour().toWebHexString() + "; float:left; border-radius: 2px;'></div>"
							+ "</div>"
							+ "<p style='text-align:center; margin:2vw 0 0 0; padding:0;color:"+ (renderedNPCArousalValue < Sex.getPartner().getArousal()
									? (Colour.GENERIC_GOOD.toWebHexString())
									: (renderedNPCArousalValue > Sex.getPartner().getArousal() ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))+ ";'>"
									+ (int) Math.ceil(Sex.getPartner().getArousal())
							+ "</p>"
							+ "<div class='overlay' id='NPC_" + Attribute.AROUSAL.getName() + "'></div>"
						+ "</div>"
						
					+ "</div>"
					
					// Status effects:
					+"<div class='attribute-container effects'>"
					+ "<p style='text-align:center;padding:0;margin:0;'><b>Effects</b></p>");
					boolean hasStatusEffects = false;

					for (StatusEffect se : Sex.getPartner().getStatusEffects()) {
						if (se.isSexEffect()) {
							uiAttributeSB.append(
									"<div class='icon'><div class='icon-content'>"
											+ se.getSVGString(Sex.getPartner())
											+ "<div class='overlay' id='SE_NPC_" + se + "'></div>"
									+ "</div></div>");
							hasStatusEffects = true;
						}
					}
					
					for (Fetish f : Sex.getPartner().getFetishes()) {
						uiAttributeSB.append(
								"<div class='icon'><div class='icon-content'>"
										+ f.getSVGString()
										+ "<div class='overlay' id='FETISH_NPC_" + f + "'></div>"
								+ "</div></div>");
						hasStatusEffects = true;
					}
					
					if(!hasStatusEffects) {
						uiAttributeSB.append("<p style='text-align:center;padding:0;margin:0;height:12vw;'><b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No sex effects</b></p>");
					}
					uiAttributeSB.append("</div>");
			
			} else {
			
				// Health, mana and experience:
				uiAttributeSB.append("<div class='attribute-container'>"
							+ "<p style='text-align:center;padding:0;margin:0;'><b>Combat</b></p>"
							
							+ "<div class='full-width-container' style='margin:0;padding:0;'>"
								+ "<div class='icon small'><div class='icon-content'>" + Attribute.HEALTH_MAXIMUM.getSVGString() + "</div></div>" + "<div class='barBackgroundAtt'>" + "<div style='width:"
								+ (npcToRender.getHealth() / npcToRender.getAttributeValue(Attribute.HEALTH_MAXIMUM)) * 65 + "vw; height:5vw; background:" + Colour.ATTRIBUTE_HEALTH.toWebHexString()
								+ "; float:left; border-radius: 2px;'></div>" + "</div>" + "<p style='text-align:center; margin:1 0 0 0; padding:0;color:"
								+ (renderedNPCHealthValue < npcToRender.getHealth() ? Colour.GENERIC_GOOD.toWebHexString() : renderedNPCHealthValue > npcToRender.getHealth() ? (Colour.GENERIC_BAD.toWebHexString()) : "default") + ";'>"
								+ (int) Math.ceil(npcToRender.getHealth()) + "</p>" + "<div class='overlay' id='NPC_" + Attribute.HEALTH_MAXIMUM.getName() + "'></div>"
							+ "</div>"
		
							+ "<div class='full-width-container' style='margin:0;padding:0;'>"
								+ "<div class='icon small'><div class='icon-content'>" + Attribute.MANA_MAXIMUM.getSVGString() + "</div></div>" + "<div class='barBackgroundAtt'>" + "<div style='width:"
								+ (npcToRender.getMana() / npcToRender.getAttributeValue(Attribute.MANA_MAXIMUM)) * 65 + "vw; height:5vw; background:" + Colour.ATTRIBUTE_MANA.toWebHexString()
								+ "; float:left; border-radius: 2px;'></div>" + "</div>" + "<p style='text-align:center; margin:1 0 0 0; padding:0;color:"
								+ (renderedNPCManaValue < npcToRender.getMana() ? (Colour.GENERIC_GOOD.toWebHexString()) : (renderedNPCManaValue > npcToRender.getMana() ? (Colour.GENERIC_BAD.toWebHexString()) : "default")) + ";'>"
								+ (int) Math.ceil(npcToRender.getMana()) + "</p>" + "<div class='overlay' id='NPC_" + Attribute.MANA_MAXIMUM.getName() + "'></div>"
							+ "</div>"
		
							+ "<div class='full-width-container' style='margin:0;padding:0;'>"
								+ "<div class='icon small'><div class='icon-content'>" + Attribute.STAMINA_MAXIMUM.getSVGString() + "</div></div>" + "<div class='barBackgroundAtt'>"
								+ "<div style='width:" + (npcToRender.getStaminaPercentage() * 65) + "vw; height:5vw; background:" + Colour.ATTRIBUTE_FITNESS.toWebHexString() + "; float:left; border-radius: 2px;'></div>" + "</div>"
								+ "<p style='text-align:center; margin:1 0 0 0; padding:0;color:"
								+ (renderedNPCStaminaValue < npcToRender.getStamina() ? (Colour.GENERIC_GOOD.toWebHexString()) : (renderedNPCStaminaValue > npcToRender.getStamina() ? (Colour.GENERIC_BAD.toWebHexString()) : "default"))
								+ ";'>" + (int) Math.ceil(npcToRender.getStamina()) + "</p>" + "<div class='overlay' id='NPC_" + Attribute.STAMINA_MAXIMUM.getName() + "'></div>"
							+ "</div>"
							
						+ "</div>");
				
				
				// Status effects:
				uiAttributeSB.append("<div class='attribute-container effects'>"
										+ "<p style='text-align:center;padding:0;margin:0;'><b>Effects</b></p>");
				// Infinite duration:
				for (StatusEffect se : npcToRender.getStatusEffects()) {
					if (!se.isCombatEffect() && npcToRender.getStatusEffectDuration(se)==-1 && se.renderInEffectsPanel())
						uiAttributeSB.append(
								"<div class='icon'>"
										+ "<div class='icon-content'>"
											+ se.getSVGString(npcToRender)
											+ "<div class='overlay' id='SE_NPC_" + se + "'></div>"
										+ "</div>"
								+ "</div>");
				}
				// Timed:
				for (StatusEffect se : npcToRender.getStatusEffects()) {
					if (!se.isCombatEffect() && npcToRender.getStatusEffectDuration(se)!=-1 && se.renderInEffectsPanel()) {
						int timerHeight = (int) ((npcToRender.getStatusEffectDuration(se)/(60*6f))*100);
	
						Colour timerColour = Colour.STATUS_EFFECT_TIME_HIGH;
						
						if(timerHeight>100) {
							timerHeight=100;
							timerColour = Colour.STATUS_EFFECT_TIME_OVERFLOW;
						} else if(timerHeight<15) {
							timerColour = Colour.STATUS_EFFECT_TIME_LOW;
						} else if (timerHeight<50) {
							timerColour = Colour.STATUS_EFFECT_TIME_MEDIUM;
						}
						
						uiAttributeSB.append(
								"<div class='icon'>"
										+ "<div class='timer-background' style='width:"+timerHeight+"%; background:"+ timerColour.toWebHexString() + ";'></div>"
										+ "<div class='icon-content'>"
											+ se.getSVGString(npcToRender)
											+ "<div class='overlay' id='SE_NPC_" + se + "'></div>"
										+ "</div>"
								+ "</div>");
					}
				}
				// Fetishes:
				for (Fetish f : npcToRender.getFetishes()) {
					uiAttributeSB.append(
							"<div class='icon'><div class='icon-content'>"
									+ f.getSVGString()
									+ "<div class='overlay' id='FETISH_NPC_" + f + "'></div>"
							+ "</div></div>");
				}
//				// Special attacks:
//				for (SpecialAttack sa : npcToRender.getSpecialAttacks()) {
//					uiAttributeSB.append(
//							"<div class='icon'><div class='icon-content'>"
//									+ sa.getSVGString()
//									+ "<div class='overlay' id='SA_" + sa + "'></div>"
//							+ "</div></div>");
//				}
//				if (npcToRender.getMainWeapon() != null) {
//					for (Spell s : npcToRender.getMainWeapon().getSpells()) {
//						uiAttributeSB.append(
//								"<div class='icon'><div class='icon-content'>"
//										+ s.getSVGString()
//										+ "<div class='overlay' id='SPELL_MAIN_" + s + "'></div>"
//								+ "</div></div>");
//					}
//				}
//				if (npcToRender.getOffhandWeapon() != null) {
//					for (Spell s : npcToRender.getOffhandWeapon().getSpells()) {
//						uiAttributeSB.append(
//								"<div class='icon'><div class='icon-content'>"
//										+ s.getSVGString()
//										+ "<div class='overlay' id='SPELL_OFFHAND_" + s + "'></div>"
//								+ "</div></div>");
//					}
//				}
			}
			uiAttributeSB.append("</div>");
			
			
			// Inventory:
			Set<InventorySlot> blockedSlots = new HashSet<>();
			
			for (AbstractClothing c : npcToRender.getClothingCurrentlyEquipped()) {
				if (c.getClothingType().getIncompatibleSlots() != null) {
					for (InventorySlot is : c.getClothingType().getIncompatibleSlots()) {
						blockedSlots.add(is);
					}
				}
			}
			// EQUIPPED:
			uiAttributeSB.append("</div><div class='inventory-equipped'>");
			
			for (InventorySlot invSlot : inventorySlots) {
				
				AbstractClothing clothing = npcToRender.getClothingInSlot(invSlot);
				
				if (clothing != null) {
					// add to content:
					uiAttributeSB.append(
							// If slot is sealed:
							"<div class='inventory-item-slot" + getClassRarityIdentifier(clothing.getRarity()) + "'"
								+ (clothing.isSealed() ? "style='border-width:2px; border-color:" + Colour.SEALED.toWebHexString() + "; border-style:solid;'" : "") + ">"
								
								// Picture:
								+ "<div class='inventory-icon-content'>"+clothing.getSVGEquippedString(npcToRender)+"</div>"
								
								// If clothing is displaced:
								+ (!clothing.getDisplacedList().isEmpty() ? "<div class='displacedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getDisplacedIcon() + "</div>" : "")
								// If clothing is cummed in:
								+ (clothing.isDirty() ? "<div class='cummedIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getCummedInIcon() + "</div>" : "")
								// If clothing is too masculine:
								+ (clothing.getClothingType().getFemininityMaximum() < npcToRender.getFemininityValue() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getMasculineWarningIcon() + "</div>" : "")
								// If clothing is too feminine:
								+ (clothing.getClothingType().getFemininityMinimum() > npcToRender.getFemininityValue() ? "<div class='femininityIcon'>" + SVGImages.SVG_IMAGE_PROVIDER.getFeminineWarningIcon() + "</div>" : "")

								+ "<div class='overlay-inventory' id='" + invSlot.toString() + "Slot'>" + "</div>" + "</div>");
				} else {
					// add to content:
					if (blockedSlots.contains(invSlot)) {
						uiAttributeSB.append("<div class='inventory-item-slot disabled'><div class='overlay' id='" + invSlot.toString() + "Slot'></div></div>");
						
					} else if (invSlot.slotBlockedByRace(npcToRender) != null) {
						uiAttributeSB.append(
								"<div class='inventory-item-slot disabled'>"
									+ "<div class='overlay' id='" + invSlot.toString() + "Slot'></div>"
									+ "<div class='raceBlockIcon'>" + invSlot.slotBlockedByRace(npcToRender).getStatusEffect().getSVGString(npcToRender) + "</div>"
								+ "</div>");
						
					} else {
						uiAttributeSB.append("<div class='inventory-item-slot' id='" + invSlot.toString() + "Slot'></div>");
					}
				}
			}

			uiAttributeSB.append("</div>");
				
		} else {
			//TODO
			if(!Main.game.isInNewWorld()) {
				uiAttributeSB.append(
						// Name box:
						"<div class='attribute-container'>"
							+ "<div class='full-width-container'>"
								+ "<p class='character-name' style='color:"+ Colour.BASE_BROWN.toWebHexString() + ";'>"
									+ "City"
								+ "</p>"
							+ "</div>"
							+ "<div class='full-width-container' style='margin:0;padding:0;'>"
								+ "<p style='text-align:center; color:"+ Colour.BASE_TAN.toWebHexString() + ";'>"
									+ "Museum"
								+"</p>"
							+ "</div>"
						+ "</div>");
				
			} else {
				PlaceInterface place = Main.game.getPlayer().getWorldLocation().getStandardPlace();
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
			}
			
			// Characters Present:
			uiAttributeSB.append("<div class='attribute-container effects'>"
								+ "<p style='text-align:center;padding:0;margin:0;'><b>Characters Present</b></p>");
			List <NPC> charactersPresent = Main.game.getCharactersPresent();
			if(charactersPresent.isEmpty()) {
				uiAttributeSB.append("<p style='text-align:center;padding:0;margin:0;'><span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>None...</span></p>");
			} else {
				int count = 0;
				for(NPC character : charactersPresent) {
					if(count%2==0) {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:#222222;'>"
									+ "<span style='color:"+character.getFemininity().getColour().toWebHexString()+";'>"+character.getName("A")+"</span>"
									+ " - <span style='color:"+character.getRace().getColour().toWebHexString()+";'>"
										+Util.capitaliseSentence((character.isFeminine()?character.getRace().getSingularFemaleName():character.getRace().getSingularMaleName()))+"</span>"
									+ "<div class='overlay-inventory' id='CHARACTERS_PRESENT_"+character.getId()+"'></div>"
								+ "</div>");
					} else {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:#292929;'>"
									+ "<span style='color:"+character.getFemininity().getColour().toWebHexString()+";'>"+character.getName("A")+"</span>"
									+ " - <span style='color:"+character.getRace().getColour().toWebHexString()+";'>"
										+Util.capitaliseSentence((character.isFeminine()?character.getRace().getSingularFemaleName():character.getRace().getSingularMaleName()))+"</span>"
									+ "<div class='overlay-inventory' id='CHARACTERS_PRESENT_"+character.getId()+"'></div>"
								+ "</div>");
					}
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
								"<div class='event-log-entry' style='background:#222222;'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getDisplayName(true))
										+ "<div class='overlay-inventory' id='WEAPON_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					} else {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:#292929;'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getDisplayName(true))
										+ "<div class='overlay-inventory' id='WEAPON_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					}
					count++;
				}
				for(Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateClothing().entrySet()) {
					if(count%2==0) {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:#222222;'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getDisplayName(true))
										+ "<div class='overlay-inventory' id='CLOTHING_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					} else {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:#292929;'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getDisplayName(true))
										+ "<div class='overlay-inventory' id='CLOTHING_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					}
					count++;
				}
				for(Entry<AbstractItem, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateItems().entrySet()) {
					if(count%2==0) {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:#222222;'>"
										+entry.getValue()+"x "+UtilText.parse(entry.getKey().getDisplayName(true))
										+ "<div class='overlay-inventory' id='ITEM_FLOOR_"+entry.getKey().hashCode()+"'></div>"
								+"</div>");
					} else {
						uiAttributeSB.append(
								"<div class='event-log-entry' style='background:#292929;'>"
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
				for(EventLogEntry event : Main.game.getEventLog().subList(Main.game.getEventLog().size()-50, Main.game.getEventLog().size()-1)) {
					if(count%2==0) {
						uiAttributeSB.append("<div class='event-log-entry' style='background:#222222;'>"+UtilText.parse(event.getFormattedEntry())+"</div>");
					} else {
						uiAttributeSB.append("<div class='event-log-entry' style='background:#292929;'>"+UtilText.parse(event.getFormattedEntry())+"</div>");
					}
					count++;
				}
			} else {
				for(EventLogEntry event : Main.game.getEventLog()) {
					if(count%2==0) {
						uiAttributeSB.append("<div class='event-log-entry' style='background:#222222;'>"+UtilText.parse(event.getFormattedEntry())+"</div>");
					} else {
						uiAttributeSB.append("<div class='event-log-entry' style='background:#292929;'>"+UtilText.parse(event.getFormattedEntry())+"</div>");
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

					renderedNPCHealthValue = npcToRender.getHealth();
					renderedNPCManaValue = npcToRender.getMana();
					renderedNPCStaminaValue = npcToRender.getStamina();
					if (Main.game.isInSex()) {
						renderedNPCArousalValue = npcToRender.getArousal();
					}
					renderedNPCStrengthValue = npcToRender.getAttributeValue(Attribute.STRENGTH);
					renderedNPCIntelligenceValue = npcToRender.getAttributeValue(Attribute.INTELLIGENCE);
					renderedNPCFitnessValue = npcToRender.getAttributeValue(Attribute.FITNESS);
					renderedNPCCorruptionValue = npcToRender.getAttributeValue(Attribute.CORRUPTION);
					
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

						if (Main.game.getActiveWorld().getCell(x, y).getPlace().getPlaceType() == GenericPlaces.IMPASSABLE) {
							mapSB.append("<div class='map-tile blank' style='width:" + (4 * unit) + "%;'></div>");
						} else {

							// This is the "move North" tile:
							if (y == playerPosition.getY() + 1 && x == playerPosition.getX() && Main.game.getActiveWorld().getCell(x, y).getPlace().getPlaceType() != GenericPlaces.IMPASSABLE) {
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
							} else if (y == playerPosition.getY() - 1 && x == playerPosition.getX() && Main.game.getActiveWorld().getCell(x, y).getPlace().getPlaceType() != GenericPlaces.IMPASSABLE) {
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
							} else if (y == playerPosition.getY() && x == playerPosition.getX() - 1 && Main.game.getActiveWorld().getCell(x, y).getPlace().getPlaceType() != GenericPlaces.IMPASSABLE) {
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
							} else if (y == playerPosition.getY() && x == playerPosition.getX() + 1 && Main.game.getActiveWorld().getCell(x, y).getPlace().getPlaceType() != GenericPlaces.IMPASSABLE) {
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
							|| (Main.game.getPlayer().getLevelUpPoints()>0
									&& (Main.game.getPlayer().getBaseAttributeValue(Attribute.STRENGTH) + Main.game.getPlayer().getBaseAttributeValue(Attribute.INTELLIGENCE) + Main.game.getPlayer().getBaseAttributeValue(Attribute.FITNESS))<300)
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

}
