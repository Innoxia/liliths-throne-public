package com.lilithsthrone.controller;

import java.util.Map.Entry;

import org.w3c.dom.events.EventTarget;

import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInventoryEventListener;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.EncounterType;
import com.lilithsthrone.game.dialogue.encounters.ExternalEncounterData;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.DebugDialogue;
import com.lilithsthrone.game.inventory.AbstractSetBonus;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.RandomEnchantment;
import com.lilithsthrone.game.inventory.enchanting.RandomPotionEnchantment;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.outfit.AbstractOutfit;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.4.6.4
 * @version 0.4.6.4
 * @author Maxis010, Innoxia
 */
public class DebugController {
	public static void initSpawnItemListeners() {
		String id;
		for (AbstractClothingType clothingType : ClothingType.getAllClothing()) {
			id = clothingType.getId()+"_SPAWN";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addClothing(Main.game.getItemGen().generateClothing(clothingType, true));
					MainController.updateUIRightPanel();
				}, false);
				MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setGenericClothing(clothingType, clothingType.getColourReplacement(0).getFirstOfDefaultColours()));
			}
		}
		
		for (AbstractWeaponType weaponType : WeaponType.getAllWeapons()) {
			id = weaponType.getId()+"_SPAWN";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addWeapon(Main.game.getItemGen().generateWeapon(weaponType));
					MainController.updateUIRightPanel();
				}, false);
				MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setGenericWeapon(weaponType, weaponType.getAvailableDamageTypes().get(0)));
			}
		}
		
		for (AbstractItemType itemType : ItemType.getAllItems()) {
			id = itemType.getId()+"_SPAWN";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem(Main.game.getItemGen().generateItem(itemType));
					MainController.updateUIRightPanel();
				}, false);
				MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setGenericItem(itemType));
			}
		}
		
		for (AbstractTattooType tattooType : TattooType.getAllTattooTypes()) {
			id = tattooType.getId()+"_SPAWN";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setGenericTattoo(tattooType));
			}
		}
		
		for (InventorySlot slot : InventorySlot.values()) {
			id = slot+"_SPAWN_SELECT";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					DebugDialogue.itemTag = null;
					DebugDialogue.activeSlot = slot;
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
		id = "ITEM_SPAWN_SELECT";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				DebugDialogue.activeSlot = null;
				DebugDialogue.itemTag = null;
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "BOOK_SPAWN_SELECT";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				DebugDialogue.activeSlot = null;
				DebugDialogue.itemTag = ItemTag.BOOK;
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "ESSENCE_SPAWN_SELECT";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				DebugDialogue.activeSlot = null;
				DebugDialogue.itemTag = ItemTag.ESSENCE;
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "SPELL_SPAWN_SELECT";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				DebugDialogue.activeSlot = null;
				DebugDialogue.itemTag = ItemTag.SPELL_BOOK;
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "HIDDEN_SPAWN_SELECT";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				DebugDialogue.activeSlot = null;
				DebugDialogue.itemTag = ItemTag.CHEAT_ITEM;
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initSpawnSetListeners() {
		for (AbstractSetBonus sb : SetBonus.allSetBonuses) {
			String id = "SET_BONUS_"+SetBonus.getIdFromSetBonus(sb);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if(WeaponType.getAllWeaponsInSet(sb)!=null) {
						for (AbstractWeaponType wt : WeaponType.getAllWeaponsInSet(sb)) {
							Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addWeapon(Main.game.getItemGen().generateWeapon(wt));
						}
					}
					if(ClothingType.getAllClothingInSet(sb)!=null) {
						for (AbstractClothingType ct : ClothingType.getAllClothingInSet(sb)) {
							Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addClothing(Main.game.getItemGen().generateClothing(ct, false));
						}
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}

	public static void initApplyOutfitListeners() {
		for (AbstractOutfit ot : OutfitType.getAllOutfits()) {
			String id = "OUTFIT_"+OutfitType.getIdFromOutfitType(ot);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					DebugDialogue.applyOutfitToDoll(ot);
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Apply Outfit",
						"Click to apply this outfit to the Dress-up doll."
						+ " The outfit's femininity, outfit type, and conditional statement are all ignored for this purpose."
						+ " The doll's leg configuration will change if needed."
						+ " Click this multiple times to see many variations.");
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
		}
	}

	public static void initApplyRandomClothingEnchantmentListeners() {
		for (RandomEnchantment randomEnchantment : RandomEnchantment.getAllClothingEnchantments()) {
			
			for(int i=0; i<DebugDialogue.randomEnchantmentClothingIds.size(); i++) {
				String clothingId = DebugDialogue.randomEnchantmentClothingIds.get(i);
				String id = "RANDOM_ENCHANTMENT_"+clothingId+"_"+RandomEnchantment.getIdFromRandomEnchantment(randomEnchantment);
				if (MainController.document.getElementById(id) != null) {
					AbstractClothing clothing = Main.game.getItemGen().generateClothing(clothingId, false);
					randomEnchantment.applyEffects(clothing);
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.getPlayerCell().getInventory().addClothing(clothing);
						
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
	
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInventoryEventListener el = new TooltipInventoryEventListener().setClothing(clothing, Main.game.getPlayer(), null);
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
		}
	}
	
	public static void initApplyRandomPotionEnchantmentListeners() {
		for (RandomPotionEnchantment randomPotionEnchantment : RandomPotionEnchantment.getAllPotionEnchantments()) {
			
			String id = "RANDOM_POTION_ENCHANTMENT_"+RandomPotionEnchantment.getIdFromRandomEnchantment(randomPotionEnchantment);
			if (MainController.document.getElementById(id) != null) {
				AbstractItem potion = Main.game.getItemGen().generateItem("innoxia_potions_youko_potion");
				potion.setOverrideSpecialEffects("[#itemGen.applyPotionEffect('"+RandomPotionEnchantment.getIdFromRandomEnchantment(randomPotionEnchantment)+"', npc)]");
				randomPotionEnchantment.executeConditionalPreParsing(Main.game.getPlayer()); // Execute parsing so that the name is corretly initialised
				potion.setName(randomPotionEnchantment.getName(Main.game.getPlayer()));
				
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.getPlayerCell().getInventory().addItem(potion);
					
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInventoryEventListener el = new TooltipInventoryEventListener().setItem(potion, Main.game.getPlayer(), null);
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
		}
	}

	public static void initEncounterListeners() {
		for(Entry<AbstractEncounter, Float> entry : DebugDialogue.encounters.entrySet()) {
			AbstractEncounter encounter = entry.getKey();
			String encounterId = encounter.getId();
			
			//TODO handle via Encounter trigger, not direct DN
			if(encounter.isFromExternalFile()) {
				int encounterIndex=0;
				for(ExternalEncounterData data : encounter.getPossibleEncounters()) {
					String id = "DEBUG_ENCOUNTER_"+encounterId+"_"+encounterIndex;
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
							Main.game.setContent(new Response("", "", DialogueManager.getDialogueFromId(data.getDialogueId())));
						}, false);
					}
					encounterIndex++;
				}
				
			} else {

				for(Entry<EncounterType, Float> typeEntry : encounter.getDialogues().entrySet()) {
					EncounterType et = typeEntry.getKey();
					String id = "DEBUG_ENCOUNTER_"+encounterId+"_"+et;
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
							DialogueNode dn = encounter.initialiseEncounter(et);
							Main.game.setContent(new Response("", "", dn));
						}, false);
					}
				}
			}
		}
	}
	
}
