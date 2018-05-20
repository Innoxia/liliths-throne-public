package com.lilithsthrone.controller;

import java.io.File;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.events.EventTarget;

import com.lilithsthrone.controller.eventListeners.EnchantmentEventListener;
import com.lilithsthrone.controller.eventListeners.InventorySelectedItemEventListener;
import com.lilithsthrone.controller.eventListeners.InventoryTooltipEventListener;
import com.lilithsthrone.controller.eventListeners.TooltipInformationEventListener;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidGirlCum;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.body.Testicle;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.TreeEntry;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderPreference;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.History;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.combat.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.SlaveryManagementDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.LoadedEnchantment;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.settings.ForcedFetishTendency;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.slavery.MilkingRoom;
import com.lilithsthrone.game.slavery.SlaveJob;
import com.lilithsthrone.game.slavery.SlaveJobHours;
import com.lilithsthrone.game.slavery.SlaveJobSetting;
import com.lilithsthrone.game.slavery.SlavePermission;
import com.lilithsthrone.game.slavery.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artist;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * This method was causing MainController to lag out Eclipse, so I moved it to a separate file.
 * 
 * @since 0.2.5
 * @version 0.2.5
 * @author Innoxia
 */
public class MainControllerInitMethod {

	public static void initMainControllerListeners() {
		MainController.document = (Document) MainController.webEngine.executeScript("document");
		MainController.EventListenerDataMap.put(MainController.document, new ArrayList<>());
		
		String id = "";
		
		if(MainController.flashMessageColour !=null && MainController.flashMessageText != null) {
			Main.game.flashMessage(MainController.flashMessageColour, MainController.flashMessageText);
			MainController.flashMessageColour = null;
			MainController.flashMessageText = null;
		}
		
		if (((EventTarget) MainController.document.getElementById("copy-content-button")) != null) {
			MainController.addEventListener(MainController.document, "copy-content-button", "click", MainController.copyDialogueButtonListener, false);
			MainController.addEventListener(MainController.document, "copy-content-button", "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(MainController.document, "copy-content-button", "mouseleave", MainController.hideTooltipListener, false);
			MainController.addEventListener(MainController.document, "copy-content-button", "mouseenter", MainController.copyInfoListener, false);
		}
		
		if (((EventTarget) MainController.document.getElementById("export-character-button")) != null) {
			MainController.addEventListener(MainController.document, "export-character-button", "click", e -> {
				if(Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CHARACTER_APPEARANCE)) {
					Game.exportCharacter(Main.game.getPlayer());
				} else {
					Game.exportCharacter(CharactersPresentDialogue.characterViewed);
				}
				
				Main.game.flashMessage(Colour.GENERIC_EXCELLENT, "Character Exported!");
			}, false);
			MainController.addEventListener(MainController.document, "export-character-button", "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(MainController.document, "export-character-button", "mouseleave", MainController.hideTooltipListener, false);
			MainController.addEventListener(MainController.document, "export-character-button", "mouseenter", new TooltipInformationEventListener().setInformation(
					"Export Character",
					"Export the currently displayed character to the 'data/characters' folder. Exported characters can be imported at the auction block in Slaver Alley."), false);
		}
		
		if(Main.game.getCurrentDialogueNode().equals(CharactersPresentDialogue.MENU)
				|| Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CONTACTS_CHARACTER)
				|| Main.game.getCurrentDialogueNode().equals(SlaveryManagementDialogue.SLAVE_MANAGEMENT_INSPECT)) {
			if(CharactersPresentDialogue.characterViewed instanceof NPC && !((NPC)CharactersPresentDialogue.characterViewed).getArtworkList().isEmpty()) {
				
				try {
					Artwork artwork = ((NPC)CharactersPresentDialogue.characterViewed).getArtworkList().get(((NPC)CharactersPresentDialogue.characterViewed).getArtworkIndex());
					
					id = "ARTWORK_INFO";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(!artwork.getArtist().getWebsites().isEmpty()) {
								Util.openLinkInDefaultBrowser(artwork.getArtist().getWebsites().get(0).getURL());
							}
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setInformation(
								"Artwork by <b style='color:"+artwork.getArtist().getColour().toWebHexString()+";'>"+artwork.getArtist().getName()+"</b>",
								(artwork.getArtist().getWebsites().isEmpty()
										?"This artist has no associated websites!"
										:"Click this button to open <b style='color:"+artwork.getArtist().getColour().toWebHexString()+";'>"+artwork.getArtist().getWebsites().get(0).getName()+"</b>"
											+ " ("+artwork.getArtist().getWebsites().get(0).getURL()+") <b>externally</b> in your default browser!")),
								false);
					}
					
					id = "ARTWORK_PREVIOUS";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(artwork.getTotalArtworkCount()>1) {
								artwork.incrementIndex(-1);
								CharactersPresentDialogue.resetContent(CharactersPresentDialogue.characterViewed);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}
					
					id = "ARTWORK_NEXT";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(artwork.getTotalArtworkCount()>1) {
								artwork.incrementIndex(1);
								CharactersPresentDialogue.resetContent(CharactersPresentDialogue.characterViewed);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}
					
					id = "ARTWORK_ARTIST_PREVIOUS";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(((NPC)CharactersPresentDialogue.characterViewed).getArtworkList().size()>1) {
								((NPC)CharactersPresentDialogue.characterViewed).incrementArtworkIndex(-1);
								CharactersPresentDialogue.resetContent(CharactersPresentDialogue.characterViewed);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}
					
					id = "ARTWORK_ARTIST_NEXT";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(((NPC)CharactersPresentDialogue.characterViewed).getArtworkList().size()>1) {
								((NPC)CharactersPresentDialogue.characterViewed).incrementArtworkIndex(1);
								CharactersPresentDialogue.resetContent(CharactersPresentDialogue.characterViewed);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}
				} catch(Exception ex) {
					System.err.println("MainController Artwork handling error.");
				}
				
			}
		}
		
		// -------------------- Debug menu -------------------- //
		
		if(Main.game.getCurrentDialogueNode().equals(DebugDialogue.SPAWN_MENU)) {
			id = "";
			
			for(AbstractClothingType clothingType : DebugDialogue.clothingTotal) {
				id = clothingType.getId() + "_SPAWN";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addClothing(AbstractClothingType.generateClothing(clothingType));
						MainController.updateUIRightPanel();
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					
					InventoryTooltipEventListener el = new InventoryTooltipEventListener().setGenericClothing(clothingType, clothingType.getAvailablePrimaryColours().get(0));
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
			
			for(AbstractWeaponType weaponType : DebugDialogue.weaponsTotal) {
				id = weaponType.getId() + "_SPAWN";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addWeapon(AbstractWeaponType.generateWeapon(weaponType));
						MainController.updateUIRightPanel();
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					
					InventoryTooltipEventListener el = new InventoryTooltipEventListener().setGenericWeapon(weaponType, weaponType.getAvailableDamageTypes().get(0));
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
			
			for(AbstractItemType itemType : DebugDialogue.itemsTotal) {
				id = itemType.getId() + "_SPAWN";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem(AbstractItemType.generateItem(itemType));
						MainController.updateUIRightPanel();
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					
					InventoryTooltipEventListener el = new InventoryTooltipEventListener().setGenericItem(itemType);
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
			
			for(InventorySlot slot : InventorySlot.values()) {
				id = slot + "_SPAWN_SELECT";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						DebugDialogue.activeSlot = slot;
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
					
				}
			}
			id = "ITEM_SPAWN_SELECT";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					DebugDialogue.activeSlot = null;
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
			}
		}
		
		
		
		
		if(Main.game.getCurrentDialogueNode() == CharacterCreation.BACKGROUND_SELECTION_MENU) {
			for(History history : History.values()) {
				id = "HISTORY_"+history;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setPerk(history.getAssociatedPerk(), Main.game.getPlayer()), false);
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event -> {
						Main.game.getPlayer().setHistory(history);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			
			}
		}
		
		
		// -------------------- Inventory listeners -------------------- //
		
		if(Main.game.isStarted()) {
			id = "";
			
			// Equipped inventory:
			
			// For weapons:
			InventorySlot[] inventorySlots = { InventorySlot.WEAPON_MAIN, InventorySlot.WEAPON_OFFHAND };
			for (InventorySlot invSlot : inventorySlots) {
				id = "PLAYER_" + invSlot.toString() + "Slot";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponEquipped(Main.game.getPlayer(), invSlot);
					MainController.addEventListener(MainController.document, id, "click", el, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, Main.game.getPlayer());
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				
				id = "NPC_" + invSlot.toString() + "Slot";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponEquipped(InventoryDialogue.getInventoryNPC(), invSlot);
					MainController.addEventListener(MainController.document, id, "click", el, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, InventoryDialogue.getInventoryNPC());
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				
				id = "NPC_VIEW_" + invSlot.toString() + "Slot";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, CharactersPresentDialogue.characterViewed);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}

			// For all equipped clothing slots:
			for (InventorySlot invSlot : InventorySlot.values()) {
				id = "PLAYER_" + invSlot.toString() + "Slot";
				if (invSlot != InventorySlot.WEAPON_MAIN && invSlot != InventorySlot.WEAPON_OFFHAND) {
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingEquipped(Main.game.getPlayer(),invSlot);
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, Main.game.getPlayer());
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				id = "NPC_" + invSlot.toString() + "Slot";
				if (invSlot != InventorySlot.WEAPON_MAIN && invSlot != InventorySlot.WEAPON_OFFHAND) {
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingEquipped(InventoryDialogue.getInventoryNPC(), invSlot);
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, InventoryDialogue.getInventoryNPC());
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				id = "NPC_VIEW_" + invSlot.toString() + "Slot";
				if (invSlot != InventorySlot.WEAPON_MAIN && invSlot != InventorySlot.WEAPON_OFFHAND) {
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setInventorySlot(invSlot, CharactersPresentDialogue.characterViewed);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
			}
			
			// Gifts:
			if(Main.game.getCurrentDialogueNode().equals(GiftDialogue.GIFT_DIALOGUE)) {
				for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayer().getMapOfDuplicateWeapons().entrySet()) {
					id = "GIFT_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("Give Gift", ":3", GiftDialogue.getDialogueToProceedTo()){
								@Override
								public void effects() {
									Main.game.setResponseTab(GiftDialogue.getProceedDialogueTab());
									Main.game.getTextStartStringBuilder().append(GiftDialogue.getReceiver().getGiftReaction(entry.getKey(), true));
									Main.game.getPlayer().removeWeapon(entry.getKey());
								}
							});
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), Main.game.getPlayer());
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				for (Entry<AbstractItem, Integer> entry : Main.game.getPlayer().getMapOfDuplicateItems().entrySet()) {
					id = "GIFT_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("Give Gift", ":3", GiftDialogue.getDialogueToProceedTo()){
								@Override
								public void effects() {
									Main.game.setResponseTab(GiftDialogue.getProceedDialogueTab());
									Main.game.getTextStartStringBuilder().append(GiftDialogue.getReceiver().getGiftReaction(entry.getKey(), true));
									Main.game.getPlayer().removeItem(entry.getKey());
								}
							});
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), Main.game.getPlayer(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayer().getMapOfDuplicateClothing().entrySet()) {
					id = "GIFT_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("Give Gift", ":3", GiftDialogue.getDialogueToProceedTo()){
								@Override
								public void effects() {
									Main.game.setResponseTab(GiftDialogue.getProceedDialogueTab());
									Main.game.getTextStartStringBuilder().append(GiftDialogue.getReceiver().getGiftReaction(entry.getKey(), true));
									Main.game.getPlayer().removeClothing(entry.getKey());
								}
							});
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), Main.game.getPlayer(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
			}
			
			// Non-equipped inventory:
			for(int i=0 ; i<RenderingEngine.INVENTORY_PAGES; i++) {
				MainController.setInventoryPageLeft(i);
				MainController.setInventoryPageRight(i);
			}
			
			
			// Player:
			for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayer().getMapOfDuplicateWeapons().entrySet()) {
				id = "PLAYER_WEAPON_" + entry.getKey().hashCode();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), Main.game.getPlayer());
					MainController.addEventListener(MainController.document, id, "click", el, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), Main.game.getPlayer());
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			for (Entry<AbstractItem, Integer> entry : Main.game.getPlayer().getMapOfDuplicateItems().entrySet()) {
				id = "PLAYER_ITEM_" + entry.getKey().hashCode();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), Main.game.getPlayer());
					MainController.addEventListener(MainController.document, id, "click", el, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), Main.game.getPlayer(), null);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayer().getMapOfDuplicateClothing().entrySet()) {
				id = "PLAYER_CLOTHING_" + entry.getKey().hashCode();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), Main.game.getPlayer());
					MainController.addEventListener(MainController.document, id, "click", el, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), Main.game.getPlayer(), null);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			
			// Partner:
			if(InventoryDialogue.getInventoryNPC()!=null) {
				String idModifier = "NPC_"+InventoryDialogue.getInventoryNPC().getId()+"_";
				for (Entry<AbstractWeapon, Integer> entry : InventoryDialogue.getInventoryNPC().getMapOfDuplicateWeapons().entrySet()) {
					id = idModifier+"WEAPON_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), InventoryDialogue.getInventoryNPC());
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), InventoryDialogue.getInventoryNPC());
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				for (Entry<AbstractClothing, Integer> entry : InventoryDialogue.getInventoryNPC().getMapOfDuplicateClothing().entrySet()) {
					id = idModifier+"CLOTHING_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), InventoryDialogue.getInventoryNPC());
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), InventoryDialogue.getInventoryNPC(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				for (Entry<AbstractItem, Integer> entry : InventoryDialogue.getInventoryNPC().getMapOfDuplicateItems().entrySet()) {
					id = idModifier+"ITEM_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), InventoryDialogue.getInventoryNPC());
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), InventoryDialogue.getInventoryNPC(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
			// Floor:
			} else {
				// Weapons on floor:
				for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateWeapons().entrySet()) {
					id = "FLOOR_WEAPON_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), null);
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				// Clothing on floor:
				for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateClothing().entrySet()) {
					id = "FLOOR_CLOTHING_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), null);
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), null, null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				// Items on floor:
				for (Entry<AbstractItem, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateItems().entrySet()) {
					id = "FLOOR_ITEM_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), null);
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), null, null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);	
					}
				}
			}
			
			if(InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			
				if(InventoryDialogue.getInventoryNPC() != null) {
					// Buyback panel:
					for (int i = Main.game.getPlayer().getBuybackStack().size() - 1; i >= 0; i--) {
						if (((EventTarget) MainController.document.getElementById("WEAPON_" + i)) != null) {
							InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory((AbstractWeapon) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), i);
							((EventTarget) MainController.document.getElementById("WEAPON_" + i)).addEventListener("click",el, false);
							MainController.addEventListener(MainController.document, "WEAPON_" + i, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, "WEAPON_" + i, "mouseleave", MainController.hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon((AbstractWeapon) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC());
							((EventTarget) MainController.document.getElementById("WEAPON_" + i)).addEventListener("mouseenter",el2, false);
						}
						
						if (((EventTarget) MainController.document.getElementById("CLOTHING_" + i)) != null) {
							InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory((AbstractClothing) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), i);
							MainController.addEventListener(MainController.document, "CLOTHING_" + i, "click", el, false);
							MainController.addEventListener(MainController.document, "CLOTHING_" + i, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, "CLOTHING_" + i, "mouseleave", MainController.hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing((AbstractClothing) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), null);
							MainController.addEventListener(MainController.document, "CLOTHING_" + i, "mouseenter", el2, false);
						}
						
						if (((EventTarget) MainController.document.getElementById("ITEM_" + i)) != null) {
							InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory((AbstractItem) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), i);
							MainController.addEventListener(MainController.document, "ITEM_" + i, "click", el, false);
							MainController.addEventListener(MainController.document, "ITEM_" + i, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, "ITEM_" + i, "mouseleave", MainController.hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem((AbstractItem) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), null);
							MainController.addEventListener(MainController.document, "ITEM_" + i, "mouseenter", el2, false);
						}
					}
				}
			}
			
			for(TFEssence essence : TFEssence.values()) {
				if (((EventTarget) MainController.document.getElementById("ESSENCE_" + essence.hashCode())) != null) {
					MainController.addEventListener(MainController.document, "ESSENCE_" + essence.hashCode(), "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, "ESSENCE_" + essence.hashCode(), "mouseleave", MainController.hideTooltipListener, false);
					
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setEssence(essence);
					MainController.addEventListener(MainController.document, "ESSENCE_" + essence.hashCode(), "mouseenter", el2, false);
				}
				if (((EventTarget) MainController.document.getElementById("ESSENCE_COST_" + essence.hashCode())) != null) {
					MainController.addEventListener(MainController.document, "ESSENCE_COST_" + essence.hashCode(), "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, "ESSENCE_COST_" + essence.hashCode(), "mouseleave", MainController.hideTooltipListener, false);
					
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setEssence(essence);
					MainController.addEventListener(MainController.document, "ESSENCE_COST_" + essence.hashCode(), "mouseenter", el2, false);
				}
			}
			
	
			// -------------------- Enchantments --------------------
			
			// Tooltips:
			if (((EventTarget) MainController.document.getElementById("MOD_PRIMARY_ENCHANTING")) != null) {

				EnchantmentEventListener el = new EnchantmentEventListener().setPrimaryModifier(TFModifier.NONE);
				MainController.addEventListener(MainController.document, "MOD_PRIMARY_ENCHANTING", "click", el, false);
				
				MainController.addEventListener(MainController.document, "MOD_PRIMARY_ENCHANTING", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "MOD_PRIMARY_ENCHANTING", "mouseleave", MainController.hideTooltipListener, false);
				
				InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setTFModifier(EnchantmentDialogue.getPrimaryMod());
				MainController.addEventListener(MainController.document, "MOD_PRIMARY_ENCHANTING", "mouseenter", el2, false);
			}
			if (((EventTarget) MainController.document.getElementById("MOD_SECONDARY_ENCHANTING")) != null) {

				EnchantmentEventListener el = new EnchantmentEventListener().setSecondaryModifier(TFModifier.NONE);
				MainController.addEventListener(MainController.document, "MOD_SECONDARY_ENCHANTING", "click", el, false);
				
				MainController.addEventListener(MainController.document, "MOD_SECONDARY_ENCHANTING", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "MOD_SECONDARY_ENCHANTING", "mouseleave", MainController.hideTooltipListener, false);
				
				InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setTFModifier(EnchantmentDialogue.getSecondaryMod());
				MainController.addEventListener(MainController.document, "MOD_SECONDARY_ENCHANTING", "mouseenter", el2, false);
			}

			for(TFPotency potency : TFPotency.values()) {
				id = "POTENCY_"+potency;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
	
					EnchantmentEventListener el = new EnchantmentEventListener().setPotency(potency);
					MainController.addEventListener(MainController.document, id, "click", el, false);
					
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setTFPotency(potency);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			for(int effectCount=0; effectCount<EnchantmentDialogue.getEffects().size(); effectCount++) {
				id = "DELETE_EFFECT_"+effectCount;
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					EnchantmentEventListener el = new EnchantmentEventListener().removeEffect(effectCount);
					MainController.addEventListener(MainController.document, id, "click", el, false);
					
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

					TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation("Delete Effect", "");
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			
			id = "LIMIT_MINIMUM";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				if(EnchantmentDialogue.getLimit()>0) {
					EnchantmentEventListener el = new EnchantmentEventListener().setLimit(0);
					MainController.addEventListener(MainController.document, id, "click", el, false);
				}
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				
				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation((EnchantmentDialogue.getLimit()==0?"Minimum Limit Reached":"Limit Minimum"), "");
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
			
			id = "LIMIT_DECREASE_LARGE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				if(EnchantmentDialogue.getLimit()>0) {
					EnchantmentEventListener el = new EnchantmentEventListener().setLimit(
							Math.max(0, EnchantmentDialogue.getLimit()-(EnchantmentDialogue.getIngredient().getEnchantmentEffect().getLimits(EnchantmentDialogue.getPrimaryMod(), EnchantmentDialogue.getSecondaryMod())/10)));
					MainController.addEventListener(MainController.document, id, "click", el, false);
				}
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				
				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation((EnchantmentDialogue.getLimit()==0?"Minimum Limit Reached":"Large Limit Decrease"), "");
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
			
			id = "LIMIT_DECREASE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				if(EnchantmentDialogue.getLimit()>0) {
					EnchantmentEventListener el = new EnchantmentEventListener().setLimit(EnchantmentDialogue.getLimit()-1);
					MainController.addEventListener(MainController.document, id, "click", el, false);
				}
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				
				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation((EnchantmentDialogue.getLimit()==0?"Minimum Limit Reached":"Limit Decrease"), "");
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
			
			id = "LIMIT_INCREASE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				if(EnchantmentDialogue.getLimit() < EnchantmentDialogue.getIngredient().getEnchantmentEffect().getLimits(EnchantmentDialogue.getPrimaryMod(), EnchantmentDialogue.getSecondaryMod())) {
					EnchantmentEventListener el = new EnchantmentEventListener().setLimit(EnchantmentDialogue.getLimit()+1);
					MainController.addEventListener(MainController.document, id, "click", el, false);
				}
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				
				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation(
						(EnchantmentDialogue.getLimit() < EnchantmentDialogue.getIngredient().getEnchantmentEffect().getLimits(EnchantmentDialogue.getPrimaryMod(), EnchantmentDialogue.getSecondaryMod())
								?"Limit Increase"
								:"Maximum Limit Reached"), "");
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
			
			id = "LIMIT_INCREASE_LARGE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				int currentLimit = EnchantmentDialogue.getIngredient().getEnchantmentEffect().getLimits(EnchantmentDialogue.getPrimaryMod(), EnchantmentDialogue.getSecondaryMod());
				if(EnchantmentDialogue.getLimit() < currentLimit) {
					EnchantmentEventListener el = new EnchantmentEventListener().setLimit(Math.min(currentLimit, EnchantmentDialogue.getLimit()+(currentLimit/10)));
					MainController.addEventListener(MainController.document, id, "click", el, false);
				}
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				
				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation(
						(EnchantmentDialogue.getLimit() < EnchantmentDialogue.getIngredient().getEnchantmentEffect().getLimits(EnchantmentDialogue.getPrimaryMod(), EnchantmentDialogue.getSecondaryMod())
								?"Large Limit Increase"
								:"Maximum Limit Reached"), "");
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}

			id = "LIMIT_MAXIMUM";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				if(EnchantmentDialogue.getLimit() < EnchantmentDialogue.getIngredient().getEnchantmentEffect().getLimits(EnchantmentDialogue.getPrimaryMod(), EnchantmentDialogue.getSecondaryMod())) {
					EnchantmentEventListener el = new EnchantmentEventListener().setLimit(EnchantmentDialogue.getIngredient().getEnchantmentEffect().getLimits(EnchantmentDialogue.getPrimaryMod(), EnchantmentDialogue.getSecondaryMod()));
					MainController.addEventListener(MainController.document, id, "click", el, false);
				}
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				
				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation(
						(EnchantmentDialogue.getLimit() < EnchantmentDialogue.getIngredient().getEnchantmentEffect().getLimits(EnchantmentDialogue.getPrimaryMod(), EnchantmentDialogue.getSecondaryMod())
								?"Set Limit To Maximum"
								:"Maximum Limit Reached"), "");
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
			
			// Ingredient icon:
			if (((EventTarget) MainController.document.getElementById("INGREDIENT_ENCHANTING")) != null) {
				
				((EventTarget) MainController.document.getElementById("INGREDIENT_ENCHANTING")).addEventListener("click", e -> {
					Main.game.setResponseTab(1);
					if(EnchantmentDialogue.getIngredient() instanceof AbstractItem) {
						Main.game.setContent(new Response("Back", "Stop enchanting.", InventoryDialogue.ITEM_INVENTORY){
							@Override
							public void effects() {
								EnchantmentDialogue.resetEnchantmentVariables();
							}
						});
					} else if(EnchantmentDialogue.getIngredient() instanceof AbstractClothing) {
						Main.game.setContent(new Response("Back", "Stop enchanting.", InventoryDialogue.CLOTHING_INVENTORY){
							@Override
							public void effects() {
								EnchantmentDialogue.resetEnchantmentVariables();
							}
						});
						
					} else {
						Main.game.setContent(new Response("Back", "Stop enchanting.", InventoryDialogue.WEAPON_INVENTORY){
							@Override
							public void effects() {
								EnchantmentDialogue.resetEnchantmentVariables();
							}
						});
						
					}
					
				}, false);
				
				MainController.addEventListener(MainController.document, "INGREDIENT_ENCHANTING", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "INGREDIENT_ENCHANTING", "mouseleave", MainController.hideTooltipListener, false);
				
				InventoryTooltipEventListener el2;
				if(EnchantmentDialogue.getIngredient() instanceof AbstractItem) {
					el2 = new InventoryTooltipEventListener().setItem((AbstractItem) EnchantmentDialogue.getIngredient(), Main.game.getPlayer(), null);
				} else if(EnchantmentDialogue.getIngredient() instanceof AbstractClothing) {
					el2 = new InventoryTooltipEventListener().setClothing((AbstractClothing) EnchantmentDialogue.getIngredient(), Main.game.getPlayer(), null);
				} else {
					el2 = new InventoryTooltipEventListener().setWeapon((AbstractWeapon) EnchantmentDialogue.getIngredient(), Main.game.getPlayer());
				}
				MainController.addEventListener(MainController.document, "INGREDIENT_ENCHANTING", "mouseenter", el2, false);
			}
			
			// Output icon:
			if (((EventTarget) MainController.document.getElementById("OUTPUT_ENCHANTING")) != null) {
				
				((EventTarget) MainController.document.getElementById("OUTPUT_ENCHANTING")).addEventListener("click", e -> {
					 if(EnchantmentDialogue.getEffects().isEmpty()) {
							
						} else if(EnchantmentDialogue.canAffordCost(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects())) {
							Main.game.setContent(new ResponseEffectsOnly("Craft", "Craft '"+EnchantmentDialogue.getOutputName()+"'."){
								@Override
								public void effects() {
									
									EnchantmentDialogue.craftItem(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects());
									
									if((EnchantmentDialogue.getPreviousIngredient() instanceof AbstractItem?Main.game.getPlayer().hasItem((AbstractItem) EnchantmentDialogue.getPreviousIngredient()):true)
											&& (EnchantmentDialogue.getPreviousIngredient() instanceof AbstractClothing?Main.game.getPlayer().hasClothing((AbstractClothing) EnchantmentDialogue.getPreviousIngredient()):true)
											&& (EnchantmentDialogue.getPreviousIngredient() instanceof AbstractWeapon?Main.game.getPlayer().hasWeapon((AbstractWeapon) EnchantmentDialogue.getPreviousIngredient()):true)) {
										EnchantmentDialogue.setIngredient(EnchantmentDialogue.getPreviousIngredient());
										Main.game.setContent(new Response("", "", EnchantmentDialogue.ENCHANTMENT_MENU));
									} else {
										Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
									}
								}
							});
							
						}
				}, false);
				
				MainController.addEventListener(MainController.document, "OUTPUT_ENCHANTING", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "OUTPUT_ENCHANTING", "mouseleave", MainController.hideTooltipListener, false);
				
				TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Craft", "Click to craft this item!");
				MainController.addEventListener(MainController.document, "OUTPUT_ENCHANTING", "mouseenter", el2, false);
			}
			
			// Adding an effect:
			id = "ENCHANT_ADD_BUTTON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					if(EnchantmentDialogue.getIngredient().getEnchantmentEffect().getEffectsDescription(
							EnchantmentDialogue.getPrimaryMod(), EnchantmentDialogue.getSecondaryMod(), EnchantmentDialogue.getPotency(), EnchantmentDialogue.getLimit(), Main.game.getPlayer(), Main.game.getPlayer())==null) {
						
					} else {
						Main.game.setContent(new Response("Add", "Add the effect.", EnchantmentDialogue.ENCHANTMENT_MENU){
							@Override
							public void effects() {
								EnchantmentDialogue.addEffect(new ItemEffect(
										EnchantmentDialogue.getIngredient().getEnchantmentEffect(), EnchantmentDialogue.getPrimaryMod(), EnchantmentDialogue.getSecondaryMod(), EnchantmentDialogue.getPotency(), EnchantmentDialogue.getLimit()));
							}
						});
					}
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation("Add Effect", "");
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
			
			id = "ENCHANT_ADD_BUTTON_DISABLED";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

				TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation("Add Effect",
						EnchantmentDialogue.getEffects().size() >= EnchantmentDialogue.getIngredient().getEnchantmentLimit()?"You have already added the maximum number of effects for this item!":"");
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
			
			// Choosing a primary modifier:
			if(EnchantmentDialogue.getIngredient() != null) {
				for (TFModifier tfMod : EnchantmentDialogue.getIngredient().getEnchantmentEffect().getPrimaryModifiers()) {
					if (((EventTarget) MainController.document.getElementById("MOD_PRIMARY_" + tfMod.hashCode())) != null) {
						
						EnchantmentEventListener el = new EnchantmentEventListener().setPrimaryModifier(tfMod);
						MainController.addEventListener(MainController.document, "MOD_PRIMARY_"+tfMod.hashCode(), "click", el, false);
						
						MainController.addEventListener(MainController.document, "MOD_PRIMARY_" + tfMod.hashCode(), "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, "MOD_PRIMARY_" + tfMod.hashCode(), "mouseleave", MainController.hideTooltipListener, false);
	
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setTFModifier(tfMod);
						MainController.addEventListener(MainController.document, "MOD_PRIMARY_" + tfMod.hashCode(), "mouseenter", el2, false);
					}
				}
			}
			// Choosing a secondary modifier:
			if(EnchantmentDialogue.getIngredient() != null) {
				for (TFModifier tfMod : EnchantmentDialogue.getIngredient().getEnchantmentEffect().getSecondaryModifiers(EnchantmentDialogue.getPrimaryMod())) {
					if (((EventTarget) MainController.document.getElementById("MOD_SECONDARY_" + tfMod.hashCode())) != null) {
						
						EnchantmentEventListener el = new EnchantmentEventListener().setSecondaryModifier(tfMod);
						MainController.addEventListener(MainController.document, "MOD_SECONDARY_"+tfMod.hashCode(), "click", el, false);
						
						MainController.addEventListener(MainController.document, "MOD_SECONDARY_" + tfMod.hashCode(), "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, "MOD_SECONDARY_" + tfMod.hashCode(), "mouseleave", MainController.hideTooltipListener, false);
	
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setTFModifier(tfMod);
						MainController.addEventListener(MainController.document, "MOD_SECONDARY_" + tfMod.hashCode(), "mouseenter", el2, false);
					}
				}
			}

			
			// -------------------- Room upgrades -------------------- //
			
			if(Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.ROOM_MANAGEMENT) {
				for(Cell c : SlaveryManagementDialogue.getImportantCells()) {
					id = c.getId();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									SlaveryManagementDialogue.cellToInspect = c;
								}
								@Override
								public DialogueNodeOld getNextDialogue() {
									return SlaveryManagementDialogue.ROOM_UPGRADES_MANAGEMENT;
								}
							});
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Room", "Open this room's management screen.");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = c.getId()+"_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Room", "You are not able to manage this room!");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = c.getId()+"_PRESENT";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									SlaveryManagementDialogue.cellToInspect = c;
								}
								@Override
								public DialogueNodeOld getNextDialogue() {
									return SlaveryManagementDialogue.ROOM_UPGRADES_MANAGEMENT;
								}
							});
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Room", "Open this room's management screen.");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = c.getId()+"_PRESENT_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Room", "You are not able to manage this room!");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
			}

			if(Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.ROOM_UPGRADES
					|| Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.ROOM_UPGRADES_MANAGEMENT) {
				for(PlaceUpgrade placeUpgrade : PlaceUpgrade.values()) {
					
					id = "ROOM_MOD_INFO_"+placeUpgrade;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("", (SlaveryManagementDialogue.cellToInspect.getPlace().getPlaceUpgrades().contains(placeUpgrade)
								?placeUpgrade.getDescriptionAfterPurchase()
								:placeUpgrade.getDescriptionForPurchase()));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = placeUpgrade+"_BUY";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(placeUpgrade!=PlaceUpgrade.LILAYA_ARTHUR_ROOM) {
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										SlaveryManagementDialogue.cellToInspect.addPlaceUpgrade(placeUpgrade);
										Main.game.getPlayer().incrementMoney(-placeUpgrade.getInstallCost());
									}
								});
							} else {
								Main.game.setContent(new Response("", "", LilayaHomeGeneric.ROOM_ARTHUR_INSTALLATION){
									@Override
									public void effects() {
										Main.game.getArthur().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), true);
										SlaveryManagementDialogue.cellToInspect.addPlaceUpgrade(placeUpgrade);
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.arthursRoomInstalled, true);
									}
								});
							}
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Purchase Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getInstallCost())+"</br>"+SlaveryManagementDialogue.getPurchaseAvailabilityTooltipText(SlaveryManagementDialogue.cellToInspect, placeUpgrade));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					id = placeUpgrade+"_BUY_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Purchase Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getInstallCost())+"</br>"+SlaveryManagementDialogue.getPurchaseAvailabilityTooltipText(SlaveryManagementDialogue.cellToInspect, placeUpgrade));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = placeUpgrade+"_SELL";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									SlaveryManagementDialogue.cellToInspect.removePlaceUpgrade(placeUpgrade);
									Main.game.getPlayer().incrementMoney(-placeUpgrade.getRemovalCost());
								}
							});
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())+"</br>"+SlaveryManagementDialogue.getPurchaseAvailabilityTooltipText(SlaveryManagementDialogue.cellToInspect, placeUpgrade));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					id = placeUpgrade+"_SELL_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove Modification",
								(placeUpgrade.isCoreRoomUpgrade()
										?"You cannot directly remove core upgrades. Instead, you'll have to purchase a different core modification in order to remove the current one."
										:"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())+"</br>"+SlaveryManagementDialogue.getPurchaseAvailabilityTooltipText(SlaveryManagementDialogue.cellToInspect, placeUpgrade)));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
				

				id = "rename_room_button";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {

						boolean unsuitableName = false;
						if(Main.mainController.getWebEngine().executeScript("document.getElementById('nameInput')")!=null) {
							 
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('nameInput').value;");
							if(Main.mainController.getWebEngine().getDocument()!=null) {
								unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
												|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32;
							}
							
							if (!unsuitableName) {
								Main.game.setContent(new Response("Rename Room", "Rename this room to whatever you've entered in the text box.", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										SlaveryManagementDialogue.cellToInspect.getPlace().setName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
									}
								});
							}
						}
							
					}, false);
				}
			}
			
			
			
			// -------------------- Slavery -------------------- //
			
			// Room specials:
			if(Main.game.getPlayer().getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
				MilkingRoom room = Main.game.getSlaveryUtil().getMilkingRoom(Main.game.getPlayerCell().getType(), Main.game.getPlayerCell().getLocation());

				for(Entry<FluidMilk, Float> entry : room.getMilkStorage().entrySet()) {
					id ="MILK_DRINK_SMALL_"+entry.hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						int milkAmount = (int) (Math.min(room.getMilkStorage().get(entry.getKey()), 100));
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(milkAmount>0) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().ingestFluid(entry.getKey().getType(), OrificeType.MOUTH, milkAmount, entry.getKey().getFluidModifiers()));
								room.incrementMilkStorage(entry.getKey(), -milkAmount);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						if(milkAmount>0) {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Drink ("+milkAmount+"ml)", "Drink "+milkAmount+"ml of the "+entry.getKey().getName(null)+".");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						} else {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Drink (0ml)", "There needs to be at least 1ml of "+entry.getKey().getName(null)+" stored here before you can drink it!");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}
					
					id ="MILK_DRINK_"+entry.hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(room.getMilkStorage().get(entry.getKey())>=500) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().ingestFluid(entry.getKey().getType(), OrificeType.MOUTH, 500, entry.getKey().getFluidModifiers()));
								room.incrementMilkStorage(entry.getKey(), -500);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						if(room.getMilkStorage().get(entry.getKey())>=500) {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Drink (500ml)", "Drink 500ml of the "+entry.getKey().getName(null)+".");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						} else {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Drink (500ml)", "There needs to be at least 500ml of "+entry.getKey().getName(null)+" stored here before you can drink it!");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}
					
					id ="MILK_SELL_"+entry.hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							int income = Math.max(1, (int)(entry.getKey().getValuePerMl()*entry.getValue()));
							Main.game.getPlayer().incrementMoney(income);
							room.getMilkStorage().remove(entry.getKey());
							Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>You sold the milk for "+(UtilText.formatAsMoney(income, "span"))+"!</p>");
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Sell",
								"Sell all of the "+entry.getKey().getName(null)+" for "+(Math.max(1, (int)(entry.getKey().getValuePerMl()*entry.getValue())))+" flames.");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
				
				for(Entry<FluidCum, Float> entry : room.getCumStorage().entrySet()) {
					id ="CUM_DRINK_SMALL_"+entry.hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						int cumAmount = (int) (Math.min(room.getCumStorage().get(entry.getKey()), 100));
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(room.getCumStorage().get(entry.getKey())>0) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().ingestFluid(entry.getKey().getType(), OrificeType.MOUTH, cumAmount, entry.getKey().getFluidModifiers()));
								room.incrementCumStorage(entry.getKey(), -cumAmount);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						if(room.getCumStorage().get(entry.getKey())>0) {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Drink ("+cumAmount+"ml)", "Drink "+cumAmount+"ml of the "+entry.getKey().getName(null)+".");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						} else {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Drink (0ml)", "There needs to be at least 1ml of "+entry.getKey().getName(null)+" stored here before you can drink it!");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}
					
					id ="CUM_DRINK_"+entry.hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(room.getCumStorage().get(entry.getKey())>=500) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().ingestFluid(entry.getKey().getType(), OrificeType.MOUTH, 500, entry.getKey().getFluidModifiers()));
								room.incrementCumStorage(entry.getKey(), -500);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						if(room.getCumStorage().get(entry.getKey())>=500) {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Drink (500ml)", "Drink 500ml of the "+entry.getKey().getName(null)+".");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						} else {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Drink (500ml)", "There needs to be at least 500ml of "+entry.getKey().getName(null)+" stored here before you can drink it!");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}
					
					id ="CUM_SELL_"+entry.hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							int income = Math.max(1, (int)(entry.getKey().getValuePerMl()*entry.getValue()));
							Main.game.getPlayer().incrementMoney(income);
							room.getCumStorage().remove(entry.getKey());
							Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>You sold the cum for "+(UtilText.formatAsMoney(income, "span"))+"!</p>");
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Sell",
								"Sell all of the "+entry.getKey().getName(null)+" for "+(Math.max(1, (int)(entry.getKey().getValuePerMl()*entry.getValue())))+" flames.");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
				
				for(Entry<FluidGirlCum, Float> entry : room.getGirlcumStorage().entrySet()) {
					id ="GIRLCUM_DRINK_SMALL_"+entry.hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						int girlcumAmount = (int) (Math.min(room.getGirlcumStorage().get(entry.getKey()), 100));
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(room.getGirlcumStorage().get(entry.getKey())>0) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().ingestFluid(entry.getKey().getType(), OrificeType.MOUTH, girlcumAmount, entry.getKey().getFluidModifiers()));
								room.incrementGirlcumStorage(entry.getKey(), -girlcumAmount);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						if(room.getGirlcumStorage().get(entry.getKey())>0) {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Drink ("+girlcumAmount+"ml)", "Drink "+girlcumAmount+"ml of the "+entry.getKey().getName(null)+".");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						} else {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Drink (0ml)", "There needs to be at least 1ml of "+entry.getKey().getName(null)+" stored here before you can drink it!");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}
					
					id ="GIRLCUM_DRINK_"+entry.hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(room.getGirlcumStorage().get(entry.getKey())>=500) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().ingestFluid(entry.getKey().getType(), OrificeType.MOUTH, 500, entry.getKey().getFluidModifiers()));
								room.incrementGirlcumStorage(entry.getKey(), -500);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						if(room.getGirlcumStorage().get(entry.getKey())>=500) {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Drink (500ml)", "Drink 500ml of the "+entry.getKey().getName(null)+".");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						} else {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Drink (500ml)", "There needs to be at least 500ml of "+entry.getKey().getName(null)+" stored here before you can drink it!");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}
					
					id ="GIRLCUM_SELL_"+entry.hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							int income = Math.max(1, (int)(entry.getKey().getValuePerMl()*entry.getValue()));
							Main.game.getPlayer().incrementMoney(income);
							room.getGirlcumStorage().remove(entry.getKey());
							Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>You sold the girlcum for "+(UtilText.formatAsMoney(income, "span"))+"!</p>");
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Sell",
								"Sell all of the "+entry.getKey().getName(null)+" for "+(Math.max(1, (int)(entry.getKey().getValuePerMl()*entry.getValue())))+" flames.");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
			}
			
			
			if(Main.game.getCurrentDialogueNode() == SlaveryManagementDialogue.SLAVERY_OVERVIEW) {
				id ="PREVIOUS_DAY";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						SlaveryManagementDialogue.incrementDayNumber(-1);
						Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				id ="NEXT_DAY";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						SlaveryManagementDialogue.incrementDayNumber(1);
						Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			
			if(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected()!=null) {
				
				id = Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().getId()+"_RENAME";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
	
						boolean unsuitableName = false;
					 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('slaveNameInput')")!=null) {
						 
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveNameInput').value;");
							if(Main.mainController.getWebEngine().getDocument()!=null) {
								if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
										|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32)
									unsuitableName = true;
								else {
									unsuitableName = false;
								}
							}
							
							if (!unsuitableName) {
								Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent()));
									}
								});
							}
							
						}
							
					}, false);
				}
				
				id = Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().getId()+"_CALLS_PLAYER";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
	
						boolean unsuitableName = false;
					 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('slaveToPlayerNameInput')")!=null) {
						 
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveToPlayerNameInput').value;");
							if(Main.mainController.getWebEngine().getDocument()!=null) {
								unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
												|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32;
							}
							
							if (!unsuitableName) {
								Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setPlayerPetName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
									}
								});
							}
							
						}
							
					}, false);
				}
				
				id = "GLOBAL_CALLS_PLAYER";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {

						boolean unsuitableName = false;
					 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('slaveToPlayerNameInput')")!=null) {
						 
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveToPlayerNameInput').value;");
							if(Main.mainController.getWebEngine().getDocument()!=null) {
								unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
												|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32;
							}
							
							if (!unsuitableName) {
								Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										for(String id: Main.game.getPlayer().getSlavesOwned()) {
											Main.game.getNPCById(id).setPlayerPetName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
										}
									}
								});
							}
							
						}
							
					}, false);
				}
				
				// Job hours:
				for(int i=0 ; i<24; i++) {
					MainController.allocateWorkTime(i);
				}
				for(SlaveJobHours preset : SlaveJobHours.values()) {

					id = preset+"_TIME";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().resetWorkHours();
							for(int hour = preset.getStartHour(); hour<preset.getStartHour()+preset.getLength(); hour++) {
								if(hour>=24) {
									Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setWorkHour(hour-24, true);
								} else {
									Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setWorkHour(hour, true);
								}
							}
							
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Set Preset Work Hours", preset.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = preset+"_TIME_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Set Preset Work Hours", "You can't assign hours to a slave who is idle. Assign them a job first.");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
				
				
				// Jobs:
				for(SlaveJob job : SlaveJob.values()) {
					id = "SLAVE_JOB_INFO_" + job;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())),
								UtilText.parse(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected(), job.getDescription()));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = job+"_ASSIGN";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setSlaveJob(job);
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Assign Job", job.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = job+"_ASSIGN_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Assign Job",
								UtilText.parse(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected(), job.getAvailabilityText(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					for(SlaveJobSetting setting : job.getMutualSettings()) {
						id = setting+"_ADD";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().addSlaveJobSettings(setting);
								Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Apply Setting", setting.getDescription());
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = setting+"_REMOVE";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().removeSlaveJobSettings(setting);
								Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove Setting", setting.getDescription());
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = setting+"_DISABLED";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Apply Setting",
									UtilText.parse(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected(), "You'll need to assign this job to [npc.name] before you can apply any settings."));
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}

					for(Entry<String, List<SlaveJobSetting>> entry : job.getMutuallyExclusiveSettings().entrySet()) {

						for(SlaveJobSetting setting : entry.getValue()) {
							id = setting+"_TOGGLE_ADD";
							if (((EventTarget) MainController.document.getElementById(id)) != null) {
								((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
									for(SlaveJobSetting settingRem : entry.getValue()) {
										Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().removeSlaveJobSettings(settingRem);
									}
									Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().addSlaveJobSettings(setting);
									Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
								}, false);
								
								MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
								TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Apply Setting", setting.getDescription());
								MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
							}
							
							id = setting+"_DISABLED";
							if (((EventTarget) MainController.document.getElementById(id)) != null) {
								MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
								TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Setting Applied", setting.getDescription());
								MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
							}
						}
					}
				}
				
				// Permissions:
				for(SlavePermission permission : SlavePermission.values()) {
					for(SlavePermissionSetting setting : permission.getSettings()) {
						id = setting+"_ADD";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().addSlavePermissionSetting(permission, setting);
								Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Apply Setting", setting.getDescription());
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = setting+"_REMOVE";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().removeSlavePermissionSetting(permission, setting);
								Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove Setting", setting.getDescription());
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = setting+"_REMOVE_ME";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove Setting", "You cannot remove mutually exclusive settings! Choose a different option instead.");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}
				}
			}
			
			
			for(String slaveId : Main.game.getPlayer().getSlavesOwned()) {
				id = slaveId;
				NPC slave = (NPC) Main.game.getNPCById(slaveId);
				if(slave!=null) { // It shouldn't equal null...
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementInspectSlaveDialogue(slave)));
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Inspect Slave",
								UtilText.parse(slave, "Inspect [npc.name]."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_JOB"; //TODO
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(slave)));
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Job",
								UtilText.parse(slave, "Set [npc.name]'s job and work hours."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_PERMISSIONS"; //TODO
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementSlavePermissionsDialogue(slave)));
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Permissions",
								UtilText.parse(slave, "Manage [npc.name]'s permissions."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_INVENTORY";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.mainController.openInventory(slave, InventoryInteraction.FULL_MANAGEMENT);
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Inventory",
								UtilText.parse(slave, "Manage [npc.name]'s inventory."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRANSFER";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									slave.setHomeLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation());
									slave.returnToHome();
								}
							});
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Move Slave Here",
								UtilText.parse(slave, "Move [npc.name] to your current location."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRANSFER_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Move Slave Here",
								UtilText.parse(slave, "You cannot move [npc.name] to this location, as there's no room for [npc.herHim] here."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_SELL";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney((int) (slave.getValueAsSlave()*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()));
									Main.game.getDialogueFlags().getSlaveTrader().addSlave(slave);
									slave.setLocation(Main.game.getDialogueFlags().getSlaveTrader().getWorldLocation(), Main.game.getDialogueFlags().getSlaveTrader().getLocation(), true);
								}
							});
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Sell Slave",
								UtilText.parse(slave, "[npc.Name] has a value of "+UtilText.formatAsMoney(slave.getValueAsSlave(), "b", Colour.GENERIC_GOOD)+"</br>"
										+ "However, "+Main.game.getDialogueFlags().getSlaveTrader().getName()+" will buy [npc.herHim] for "
											+UtilText.formatAsMoney((int)(slave.getValueAsSlave()*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()), "b", Colour.GENERIC_ARCANE)+"."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_SELL_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Sell Slave",
								UtilText.parse(slave, "You cannot sell [npc.name], as there's nobody here to sell [npc.herHim] to."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_COSMETICS";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.SLAVE_MANAGEMENT_COSMETICS_HAIR) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(slave);
									BodyChanging.setTarget(slave);
								}
							});
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Send Slave to Kate",
								UtilText.parse(slave, "Send [npc.name] to Kate's beauty salon, 'Succubi's Secrets', to get [npc.her] appearance changed."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_COSMETICS_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Send Slave to Kate",
								UtilText.parse(slave, "You haven't met Kate yet!"));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
			}
			

			if(Main.game.getDialogueFlags().getSlaveTrader()!=null) {
				for(String slaveId : Main.game.getDialogueFlags().getSlaveTrader().getSlavesOwned()) {
					id = slaveId+"_TRADER";
					NPC slave = (NPC) Main.game.getNPCById(slaveId);
					
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", SlaveryManagementDialogue.getSlaveryManagementInspectSlaveDialogue(slave)));
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Inspect Slave",
								UtilText.parse(slave, "Take a detailed look at [npc.name]."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRADER_JOB";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Job",
								UtilText.parse(slave, "You cannot manage [npc.name]'s job, as you don't own [npc.herHim]!"));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRADER_PERMISSIONS";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Permissions",
								UtilText.parse(slave, "You cannot manage [npc.name]'s permissions, as you don't own [npc.herHim]!"));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRADER_INVENTORY";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Inventory",
								UtilText.parse(slave, "You cannot manage [npc.name]'s inventory, as you don't own [npc.herHim]!"));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					
					id = slaveId+"_TRADER_TRANSFER";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Move Slave To Here",
								UtilText.parse(slave, "You cannot move [npc.name] to this location, as you don't own [npc.herHim], as well as due to the fact that [npc.she]'s already here!"));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_BUY";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-(int)(slave.getValueAsSlave()*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier()));
									Main.game.getPlayer().addSlave(slave);
									slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
								}
							});
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Buy Slave",
								UtilText.parse(slave, "[npc.Name] has a value of "+UtilText.formatAsMoney(slave.getValueAsSlave(), "b", Colour.GENERIC_GOOD)+"</br>"
										+ "However, "+Main.game.getDialogueFlags().getSlaveTrader().getName()+" will sell [npc.herHim] for "
											+UtilText.formatAsMoney((int)(slave.getValueAsSlave()*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier()), "b", Colour.GENERIC_ARCANE)+"."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_BUY_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Buy Slave",
								UtilText.parse(slave, "You cannot buy [npc.name], as you don't have enough money!"));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRADER_COSMETICS";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Send Slave to Kate",
								UtilText.parse(slave, "You can't send a slave you don't own to Kate!"));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
			}
			
			
			// -------------------- Incest Renaming -------------------- //
			
			if(Main.game.getActiveNPC()!=null) {
				id = Main.game.getActiveNPC().getId()+"_PET_NAME";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
	
						boolean unsuitableName = false;
					 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('offspringPetNameInput')")!=null) {
						 
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('offspringPetNameInput').value;");
							if(Main.mainController.getWebEngine().getDocument()!=null) {
								if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 2
										|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32)
									unsuitableName = true;
								else {
									unsuitableName = false;
								}
							}
							
							if (!unsuitableName) {
								Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										Main.game.getActiveNPC().setPlayerPetName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
									}
								});
							}
							
						}
							
					}, false);
				}
			}
			
			
			// -------------------- Character Creation -------------------- //
			
			if(!Main.game.isInNewWorld()) {
				for(Month month : Month.values()) {
					id = "STARTING_MONTH_"+month;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setStartingDateMonth(month);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				// Sex experiences:
				for(int i : CharacterModificationUtils.soSilly) {
					
					// Given:
					
					id = "HANDJOBS_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.PITCHER, PenetrationType.FINGER, OrificeType.URETHRA_PENIS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "FINGERINGS_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.PITCHER, PenetrationType.FINGER, OrificeType.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "BLOWJOBS_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.MOUTH), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "CUNNILINGUS_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "VAGINAL_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "ANAL_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.ANUS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					// Received:

					id = "HANDJOBS_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.CATCHER, PenetrationType.FINGER, OrificeType.URETHRA_PENIS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "FINGERINGS_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.CATCHER, PenetrationType.FINGER, OrificeType.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "BLOWJOBS_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "CUNNILINGUS_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "VAGINAL_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "ANAL_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.ANUS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
			}
			
			if(!Main.game.isInNewWorld()
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_CORE)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_FACE)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_ASS)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_BREASTS)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_VAGINA)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_PENIS)) {
				
				
				// Gender:
				id = "CHOOSE_GENDER_MALE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						CharacterCreation.setGenderMale();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}

				id = "CHOOSE_GENDER_FEMALE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						CharacterCreation.setGenderFemale();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Femininity
				id = "CHOOSE_FEM_MASCULINE_STRONG";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.MASCULINE_STRONG.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_MASCULINE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.MASCULINE.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_ANDROGYNOUS";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.ANDROGYNOUS.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_FEMININE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.FEMININE.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_FEMININE_STRONG";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.FEMININE_STRONG.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				
				// Personality:
				for(PersonalityTrait trait : PersonalityTrait.values()) {
					id = "PERSONALITY_INFO_"+trait;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(trait.getName()), trait.getDescription()), false);
					}
					
					for(PersonalityWeight weight : PersonalityWeight.values()) {
						id = "PERSONALITY_"+trait+"_"+weight;
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								BodyChanging.getTarget().setPersonalityTrait(trait, weight);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
				}
				
				// Orientation:
				for(SexualOrientation orientation : SexualOrientation.values()) {
					id = "SEXUAL_ORIENTATION_"+orientation;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setSexualOrientation(orientation);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
			
				// Height:
				id = "HEIGHT_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "HEIGHT_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "HEIGHT_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(-1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "HEIGHT_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(-5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Body size:
				for(BodySize bs : BodySize.values()) {
					id = "BODY_SIZE_"+bs;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBodySize(bs.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Muscles:
				for(Muscle muscle : Muscle.values()) {
					id = "MUSCLE_"+muscle;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setMuscle(muscle.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// ------------ Character creation -------------- //
				
				// Lip Size:
				for(LipSize ls : LipSize.values()) {
					id = "LIP_SIZE_"+ls;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setLipSize(ls.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				// Lip puffiness:
				id = "LIP_PUFFY_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().addFaceOrificeModifier(OrificeModifier.PUFFY);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "LIP_PUFFY_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().removeFaceOrificeModifier(OrificeModifier.PUFFY);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				for(HornLength hornLength : HornLength.values()) {
					id = "CHANGE_HORN_LENGTH_"+hornLength;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHornLength(hornLength.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(TongueLength tongueLength : TongueLength.values()) {
					id = "CHANGE_TONGUE_LENGTH_"+tongueLength;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTongueLength(tongueLength.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(WingSize wingSize : WingSize.values()) {
					id = "CHANGE_WING_SIZE_"+wingSize;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setWingSize(wingSize.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Breast size:
				for(CupSize cs : CupSize.values()) {
					id = "BREAST_SIZE_"+cs;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastSize(cs.getMeasurement());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Breast Shape:
				for(BreastShape bs : BreastShape.values()) {
					id = "BREAST_SHAPE_"+bs;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastShape(bs);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple size:
				for(NippleSize ns : NippleSize.values()) {
					id = "NIPPLE_SIZE_"+ns;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleSize(ns.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Areolae size:
				for(AreolaeSize as : AreolaeSize.values()) {
					id = "AREOLAE_SIZE_"+as;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAreolaeSize(as.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple puffiness:
				id = "NIPPLE_PUFFY_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().addNippleOrificeModifier(OrificeModifier.PUFFY);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "NIPPLE_PUFFY_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().removeNippleOrificeModifier(OrificeModifier.PUFFY);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Lactation:
				for(int i : CharacterModificationUtils.getLactationQuantitiesAvailable()) {
					id = "LACTATION_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastMilkStorage(i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Ass size:
				for(AssSize as : CharacterModificationUtils.getAssSizesAvailable()) {
					id = "ASS_SIZE_"+as;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssSize(as.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Hip size:
				for(HipSize hs : CharacterModificationUtils.getHipSizesAvailable()) {
					id = "HIP_SIZE_"+hs;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHipSize(hs.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Bleached anus:
				id = "ANUS_BLEACHED_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setAssBleached(true);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "ANUS_BLEACHED_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setAssBleached(false);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Penis size:
				for(int ps : CharacterModificationUtils.getPenisSizesAvailable()) {
					id = "PENIS_SIZE_"+ps;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisSize(ps);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				// Penis girth:
				for(PenisGirth girth : PenisGirth.values()) {
					id = "PENIS_GIRTH_"+girth;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisGirth(girth.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				// Testicle size:
				for(TesticleSize ts : CharacterModificationUtils.getTesticleSizesAvailable()) {
					id = "TESTICLE_SIZE_"+ts;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTesticleSize(ts.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Cum production:
				for(CumProduction cp: CharacterModificationUtils.getCumProductionAvailable()) {
					id = "CUM_PRODUCTION_"+cp;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setCumProduction(cp.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina capacity:
				for(Capacity capacity: Capacity.values()) {
					id = "VAGINA_CAPACITY_"+capacity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina wetness:
				for(Wetness wetness: Wetness.values()) {
					id = "VAGINA_WETNESS_"+wetness;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaWetness(wetness.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina elastcity:
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "VAGINA_ELASTICITY_"+elasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina plasticity:
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "VAGINA_PLASTICITY_"+plasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaPlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Clit size:
				for(ClitorisSize cs: ClitorisSize.values()) {
					id = "CLITORIS_SIZE_"+cs;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaClitorisSize(cs.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Labia size:
				for(LabiaSize ls: LabiaSize.values()) {
					id = "LABIA_SIZE_"+ls;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaLabiaSize(ls.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				

				// ------------ Demonic/Slime transformations -------------- //
				
				for(ArmType armType: ArmType.values()) {
					id = "CHANGE_ARM_"+armType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setArmType(armType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(EyeType eyeType: EyeType.values()) {
					id = "CHANGE_EYE_"+eyeType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setEyeType(eyeType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(EarType earType: EarType.values()) {
					id = "CHANGE_EAR_"+earType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setEarType(earType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(HornType hornType: HornType.values()) {
					id = "CHANGE_HORN_"+hornType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHornType(hornType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(AntennaType antennaType: AntennaType.values()) {
					id = "CHANGE_ANTENNA_"+antennaType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAntennaType(antennaType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(HairType hairType: HairType.values()) {
					id = "CHANGE_HAIR_"+hairType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHairType(hairType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				for(LegType legType: LegType.values()) {
					id = "CHANGE_LEG_"+legType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setLegType(legType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(FaceType faceType: FaceType.values()) {
					id = "CHANGE_FACE_"+faceType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setFaceType(faceType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(SkinType skinType: SkinType.values()) {
					id = "CHANGE_SKIN_"+skinType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setSkinType(skinType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(TailType tailType: TailType.values()) {
					id = "CHANGE_TAIL_"+tailType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTailType(tailType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(WingType wingType: WingType.values()) {
					id = "CHANGE_WING_"+wingType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setWingType(wingType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(AssType assType: AssType.values()) {
					id = "CHANGE_ASS_"+assType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssType(assType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(BreastType breastType: BreastType.values()) {
					id = "CHANGE_BREAST_"+breastType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastType(breastType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				// Face:
				
				for(EyeShape eyeShape : EyeShape.values()) {
					id = "CHANGE_IRIS_SHAPE_"+eyeShape;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setIrisShape(eyeShape);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "CHANGE_PUPIL_SHAPE_"+eyeShape;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPupilShape(eyeShape);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(LipSize lipSize : LipSize.values()) {
					id = "CHANGE_LIP_SIZE_"+lipSize;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setLipSize(lipSize.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_MOUTH_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasFaceOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeFaceOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addFaceOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(TongueModifier tongueMod : TongueModifier.values()) {
					id = "CHANGE_TONGUE_MOD_"+tongueMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasTongueModifier(tongueMod)) {
								BodyChanging.getTarget().removeTongueModifier(tongueMod);
							} else {
								BodyChanging.getTarget().addTongueModifier(tongueMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Ass:
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_ANUS_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasAssOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeAssOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addAssOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Ass size:
				for(AssSize as : AssSize.values()) {
					id = "CHANGE_ASS_SIZE_"+as;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssSize(as.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(HipSize hs : HipSize.values()) {
					id = "CHANGE_HIP_SIZE_"+hs;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHipSize(hs.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(Capacity capacity: Capacity.values()) {
					id = "ANUS_CAPACITY_"+capacity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(Wetness wetness: Wetness.values()) {
					id = "ANUS_WETNESS_"+wetness;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssWetness(wetness.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "ANUS_ELASTICITY_"+elasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "ANUS_PLASTICITY_"+plasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssPlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				
				// Breasts:
				
				for(int i=1; i <= Breast.MAXIMUM_BREAST_ROWS; i++) {
					MainController.setBreastCountListener(i);
				}
				
				for(int i=1; i <= Breast.MAXIMUM_NIPPLES_PER_BREAST; i++) {
					MainController.setNippleCountListener(i);
				}
				
				// Nipple capacity:
				for(Capacity capacity: Capacity.values()) {
					id = "NIPPLE_CAPACITY_"+capacity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple elastcity:
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "NIPPLE_ELASTICITY_"+elasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple plasticity:
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "NIPPLE_PLASTICITY_"+plasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNipplePlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(int i : CharacterModificationUtils.demonLactationValues) {
					id = "LACTATION_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastMilkStorage(i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_NIPPLE_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasNippleOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeNippleOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addNippleOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina:
				
				for(VaginaType vaginaType: VaginaType.values()) {
					id = "CHANGE_VAGINA_"+vaginaType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaType(vaginaType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_VAGINA_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasVaginaOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeVaginaOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addVaginaOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Penis:
				
				for(PenisType penisType: PenisType.values()) {
					id = "CHANGE_PENIS_"+penisType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisType(penisType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				id = "PENIS_SIZE_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisSize(1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "PENIS_SIZE_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisSize(5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "PENIS_SIZE_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisSize(-1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "PENIS_SIZE_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisSize(-5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				for(TesticleSize size : TesticleSize.values()) {
					id = "TESTICLE_SIZE_"+size;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTesticleSize(size.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(int i=Testicle.MIN_TESTICLE_COUNT; i<=Testicle.MAX_TESTICLE_COUNT; i+=2) {
					MainController.setTesticleCountListener(i);
				}
				
				id = "TESTICLES_INTERNAL_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setInternalTesticles(true);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "TESTICLES_INTERNAL_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setInternalTesticles(false);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				for(Capacity capacity: Capacity.values()) {
					id = "URETHRA_CAPACITY_"+capacity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				id = "CUM_PRODUCTION_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumProduction(1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumProduction(25);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_INCREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumProduction(500);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumProduction(-1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumProduction(-25);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_DECREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumProduction(-500);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "URETHRA_ELASTICITY_"+elasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setUrethraElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "URETHRA_PLASTICITY_"+plasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setUrethraPlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(PenisModifier orificeMod : PenisModifier.values()) {
					id = "CHANGE_PENIS_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasPenisModifier(orificeMod)) {
								BodyChanging.getTarget().removePenisModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addPenisModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_URETHRA_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasUrethraOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeUrethraOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addUrethraOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
			}
			
			// -------------------- Cosmetics -------------------- //
			
			
			boolean noCost = !Main.game.isInNewWorld() || Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.PHONE;

			for(BodyCoveringType bct : BodyCoveringType.values()) {
				
				id = "APPLY_COVERING_"+bct;
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getBodyCoveringTypeCost(bct) || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(CharacterModificationUtils.getCoveringsToBeApplied().containsKey(bct)) {
										if(!noCost) {
											Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getBodyCoveringTypeCost(bct));
										}
										
										BodyChanging.getTarget().setSkinCovering(new Covering(CharacterModificationUtils.getCoveringsToBeApplied().get(bct)), false);
										
										if(noCost) {
											if(bct == BodyCoveringType.HUMAN) {
												BodyChanging.getTarget().getBody().updateCoverings(false, false, false, true);
											}
										}
									}
								}
							});
						}
					}, false);
				}
				
				
				id = bct+"_PRIMARY_GLOW_OFF";
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing(false);
							}
						});
					}, false);
				}
				
				id = bct+"_PRIMARY_GLOW_ON";
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing(true);
								
							}
						});
					}, false);
				}
				
				id = bct+"_SECONDARY_GLOW_OFF";
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(false);
							}
						});
					}, false);
				}
				
				id = bct+"_SECONDARY_GLOW_ON";
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(true);
							}
						});
					}, false);
				}
				
				for(CoveringPattern pattern : CoveringPattern.values()) {
					id = bct+"_PATTERN_"+pattern;
					
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPattern(pattern);
								}
							});
						}, false);
					}
				}
				
				for(CoveringModifier modifier : CoveringModifier.values()) {
					id = bct+"_MODIFIER_"+modifier;
					
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setModifier(modifier);
								}
							});
						}, false);
					}
				}

				for(Colour colour : bct.getAllPrimaryColours()) {
					id = bct+"_PRIMARY_"+colour;
					
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryColour(colour);
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing((colour != Colour.COVERING_NONE && BodyChanging.getTarget().getCovering(bct).isPrimaryGlowing()));
								}
							});
						}, false);
					}
				}
				for(Colour colour : bct.getAllSecondaryColours()) {
					id = bct+"_SECONDARY_"+colour;
					
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryColour(colour);
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(colour != Colour.COVERING_NONE && BodyChanging.getTarget().getCovering(bct).isSecondaryGlowing());
								}
							});
						}, false);
					}
				}
			}
			
			for(HairLength hairLength : HairLength.values()) {
				id = "HAIR_LENGTH_"+hairLength;
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_HAIR_LENGTH_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_HAIR_LENGTH_COST);
									}
									BodyChanging.getTarget().setHairLength(hairLength.getMedianValue());
								}
							});
						}
					}, false);
				}
			}
			
			for(HairStyle hairStyle: HairStyle.values()) {
				id = "HAIR_STYLE_"+hairStyle;
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_HAIR_STYLE_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_HAIR_STYLE_COST);
									}
									BodyChanging.getTarget().setHairStyle(hairStyle);
								}
							});
						}
					}, false);
				}
			}
			
			for(PiercingType piercingType : PiercingType.values()) {
				id = piercingType+"_PIERCE_REMOVE";
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getPiercingCost(piercingType) || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getPiercingCost(piercingType));
									}
									switch(piercingType) {
										case EAR:
											BodyChanging.getTarget().setPiercedEar(false);
											break;
										case LIP:
											BodyChanging.getTarget().setPiercedLip(false);
											break;
										case NAVEL:
											BodyChanging.getTarget().setPiercedNavel(false);
											break;
										case NIPPLE:
											BodyChanging.getTarget().setPiercedNipples(false);
											break;
										case NOSE:
											BodyChanging.getTarget().setPiercedNose(false);
											break;
										case PENIS:
											BodyChanging.getTarget().setPiercedPenis(false);
											break;
										case TONGUE:
											BodyChanging.getTarget().setPiercedTongue(false);
											break;
										case VAGINA:
											BodyChanging.getTarget().setPiercedVagina(false);
											break;
									}
								}
							});
						}
					}, false);
				}
				
				id = piercingType+"_PIERCE";
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getPiercingCost(piercingType) || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getPiercingCost(piercingType));
									}
									switch(piercingType) {
										case EAR:
											BodyChanging.getTarget().setPiercedEar(true);
											break;
										case LIP:
											BodyChanging.getTarget().setPiercedLip(true);
											break;
										case NAVEL:
											BodyChanging.getTarget().setPiercedNavel(true);
											break;
										case NIPPLE:
											BodyChanging.getTarget().setPiercedNipples(true);
											break;
										case NOSE:
											BodyChanging.getTarget().setPiercedNose(true);
											break;
										case PENIS:
											BodyChanging.getTarget().setPiercedPenis(true);
											break;
										case TONGUE:
											BodyChanging.getTarget().setPiercedTongue(true);
											break;
										case VAGINA:
											BodyChanging.getTarget().setPiercedVagina(true);
											break;
									}
								}
							});
						}
					}, false);
				}
			}
			
			if (((EventTarget) MainController.document.getElementById("BLEACHING_OFF")) != null) {
				
				((EventTarget) MainController.document.getElementById("BLEACHING_OFF")).addEventListener("click", e -> {
					if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_ANAL_BLEACHING_COST || noCost) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								if(!noCost) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_ANAL_BLEACHING_COST);
								}
								BodyChanging.getTarget().setAssBleached(false);
							}
						});
					}
				}, false);
			}
			
			if (((EventTarget) MainController.document.getElementById("BLEACHING_ON")) != null) {
				
				((EventTarget) MainController.document.getElementById("BLEACHING_ON")).addEventListener("click", e -> {
					if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_ANAL_BLEACHING_COST || noCost) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								if(!noCost) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_ANAL_BLEACHING_COST);
								}
								BodyChanging.getTarget().setAssBleached(true);
							}
						});
					}
				}, false);
			}
			
			for(BodyHair bodyHair: BodyHair.values()) {
				
				id = "ASS_HAIR_"+bodyHair;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									}
									BodyChanging.getTarget().setAssHair(bodyHair);
								}
							});
						}
					}, false);
				}
				
				id = "UNDERARM_HAIR_"+bodyHair;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									}
									BodyChanging.getTarget().setUnderarmHair(bodyHair);
								}
							});
						}
					}, false);
				}
				
				id = "PUBIC_HAIR_"+bodyHair;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									}
									BodyChanging.getTarget().setPubicHair(bodyHair);
								}
							});
						}
					}, false);
				}
				
				id = "FACIAL_HAIR_"+bodyHair;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									}
									BodyChanging.getTarget().setFacialHair(bodyHair);
								}
							});
						}
					}, false);
				}
			}
			
			
			// -------------------- Phone listeners -------------------- // TODO track listeners
			
			// Phone item viewer:
			if(Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_CLOTHING
					|| Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_CLOTHING_CHARACTER_CREATION
					|| Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_EQUIPPED_CLOTHING
					|| Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_EQUIPPED_CLOTHING_CHARACTER_CREATION) {
//				for (AbstractClothingType clothing : ClothingType.getAllClothing()) {
				AbstractClothingType clothing = InventoryDialogue.getClothing().getClothingType();
				for (Colour c : clothing.getAllAvailablePrimaryColours()) {
					id = "PRIMARY_"+clothing.hashCode() + "_" + c.toString();
					if ((EventTarget) MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							InventoryDialogue.dyePreviewPrimary = c;
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeClothingPrimary(InventoryDialogue.getClothing(), c);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				if(!clothing.getAllAvailableSecondaryColours().isEmpty()) {
					for (Colour c : clothing.getAllAvailableSecondaryColours()) {
						id = "SECONDARY_"+clothing.hashCode() + "_" + c.toString();
						if ((EventTarget) MainController.document.getElementById(id) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								InventoryDialogue.dyePreviewSecondary = c;
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeClothingSecondary(InventoryDialogue.getClothing(), c);
							MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
						}
					}
				}
				if(!clothing.getAllAvailableTertiaryColours().isEmpty()) {
					for (Colour c : clothing.getAllAvailableTertiaryColours()) {
						id = "TERTIARY_"+clothing.hashCode() + "_" + c.toString();
						if ((EventTarget) MainController.document.getElementById(id) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								InventoryDialogue.dyePreviewTertiary = c;
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeClothingTertiary(InventoryDialogue.getClothing(), c);
							MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
						}
					}
				}
//				}
			}
			
			if(Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_WEAPON
					|| Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_EQUIPPED_WEAPON) {
				AbstractWeaponType weapon = InventoryDialogue.getWeapon().getWeaponType();
				for (Colour c : weapon.getAllAvailablePrimaryColours()) {
					id = "PRIMARY_"+weapon.hashCode() + "_" + c.toString();
					if ((EventTarget) MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							InventoryDialogue.dyePreviewPrimary = c;
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeWeaponPrimary(InventoryDialogue.getWeapon(), c);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				for (Colour c : weapon.getAllAvailableSecondaryColours()) {
					id = "SECONDARY_"+weapon.hashCode() + "_" + c.toString();
					if ((EventTarget) MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							InventoryDialogue.dyePreviewSecondary = c;
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeWeaponSecondary(InventoryDialogue.getWeapon(), c);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
			}
			
			for (AbstractClothingType clothing : ClothingType.getAllClothing()) {
				for (Colour colour : clothing.getAllAvailablePrimaryColours()) {
					if ((EventTarget) MainController.document.getElementById(clothing.hashCode() + "_" + colour.toString()) != null) {
						MainController.addEventListener(MainController.document, clothing.hashCode() + "_" + colour.toString(), "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, clothing.hashCode() + "_" + colour.toString(), "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setGenericClothing(clothing, colour);
						MainController.addEventListener(MainController.document, clothing.hashCode() + "_" + colour.toString(), "mouseenter", el2, false);
					}
				}
			}
			
			for (AbstractItemType item : ItemType.allItems) {
				id = ItemType.itemToIdMap.get(item);
				if ((EventTarget) MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setGenericItem(item);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			
			for (AbstractWeaponType weapon : WeaponType.allweapons) {
				for (DamageType dt : weapon.getAvailableDamageTypes()) {
					if ((EventTarget) MainController.document.getElementById(weapon.hashCode() + "_" + dt.toString()) != null) {
						MainController.addEventListener(MainController.document, weapon.hashCode() + "_" + dt.toString(), "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, weapon.hashCode() + "_" + dt.toString(), "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setGenericWeapon(weapon, dt);
						MainController.addEventListener(MainController.document, weapon.hashCode() + "_" + dt.toString(), "mouseenter", el2, false);
					}
				}
			}

			for(Perk perk : Perk.values()) { //TODO
				if(perk.getPerkCategory() == PerkCategory.JOB) {
					id = "HISTORY_"+perk;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setLevelUpPerk(0, perk, Main.game.getPlayer()), false);
					}
					
				} else {
					id = "TRAIT_"+perk;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setLevelUpPerk(PerkManager.MANAGER.getPerkRow(perk), perk, Main.game.getPlayer()), false);
						
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event -> {
							Main.game.getPlayer().removeTrait(perk);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
			}
			
			if (Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_SPELLS_ARCANE
					|| Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_SPELLS_EARTH
					|| Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_SPELLS_WATER
					|| Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_SPELLS_AIR
					|| Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_SPELLS_FIRE) {
				for(Spell s : Spell.values()) {
					id = "SPELL_TREE_" + s;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setSpell(s, Main.game.getPlayer()), false);
						
					}
					for(List<TreeEntry<SpellSchool, SpellUpgrade>> upgradeList : s.getSpellUpgradeTree().values()) {
						for(TreeEntry<SpellSchool, SpellUpgrade> upgrade : upgradeList) {
							id = "SPELL_UPGRADE_" + upgrade.getEntry();
							if (((EventTarget) MainController.document.getElementById(id)) != null) {
								MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setSpellUpgrade(upgrade.getEntry(), Main.game.getPlayer()), false);
								
								((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event -> {
									if(Spell.isSpellUpgradeAvailable(Main.game.getPlayer(), s, upgrade) && Main.game.getPlayer().getSpellUpgradePoints(upgrade.getCategory())>=upgrade.getEntry().getPointCost()) {
										Main.game.getPlayer().addSpellUpgrade(upgrade.getEntry());
										Main.game.getPlayer().incrementSpellUpgradePoints(upgrade.getCategory(), -upgrade.getEntry().getPointCost());
										Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
									}
								}, false);
							}
						}
					}
				}
				
			}
			
			// Level up dialogue:
			if (Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_LEVEL_UP) {
				for(int i = 0; i<PerkManager.ROWS; i++) {
					for(Entry<PerkCategory, List<TreeEntry<PerkCategory, Perk>>> entry : PerkManager.MANAGER.getPerkTree().get(i).entrySet()) {
						for(TreeEntry<PerkCategory, Perk> e : entry.getValue()) {
							id = i+"_"+e.getEntry();
	
							if (((EventTarget) MainController.document.getElementById(id)) != null) {
								MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setLevelUpPerk(i, e.getEntry(), Main.game.getPlayer()), false);
								((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event -> {
									if(e.getEntry().isEquippableTrait() && PerkManager.MANAGER.isPerkOwned(e)) {
										if(!Main.game.getPlayer().hasTraitActivated(e.getEntry())) {
											Main.game.getPlayer().addTrait(e.getEntry());
										} else {
											Main.game.getPlayer().removeTrait(e.getEntry());
										}
										Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
										
									} else if(Main.game.getPlayer().getPerkPoints()>=1 && PerkManager.MANAGER.isPerkAvailable(e)) {
										if(Main.game.getPlayer().addPerk(e.getRow(), e.getEntry())) {
											Main.game.getPlayer().incrementPerkPoints(-1);
											if(e.getEntry().isEquippableTrait() && Main.game.getPlayer().getTraits().size()<GameCharacter.MAX_TRAITS) {
												Main.game.getPlayer().addTrait(e.getEntry());
											}
											Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
										}
									}
								}, false);
							}
						}
					}
				}
			}
			if (Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_FETISHES) {
				for (Fetish f : Fetish.values()) {
					id = "fetishUnlock" + f;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE)>=f.getCost()) {
								if(Main.game.getPlayer().addFetish(f)) {
									Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -f.getCost(), false);
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
								}
							}
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setFetish(f, Main.game.getPlayer()), false);
					}
					
					id = f+"_EXPERIENCE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setFetishExperience(f, Main.game.getPlayer()), false);
					}
					
					for (FetishDesire desire : FetishDesire.values()) {
						id = f+"_"+desire;
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE)>=FetishDesire.getCostToChange()) {
									if(Main.game.getPlayer().setFetishDesire(f, desire)) {
										Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -FetishDesire.getCostToChange(), false);
										Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
									}
								}
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setFetishDesire(f, desire, Main.game.getPlayer()), false);
						}
					}
				}
			}
			if (Main.game.getCurrentDialogueNode() == PhoneDialogue.MENU) {
				Cell[][] grid = Main.game.getWorlds().get(Main.game.getPlayer().getWorldLocation()).getGrid();

				for(int i=grid[0].length-1; i>=0; i--) {
					for(int j=0; j<grid.length; j++) {
						Cell c = grid[j][i];
						boolean discovered = c.isDiscovered() || Main.game.isDebugMode();
						if(!discovered) {
							continue;
						}
						if(c.getPlace().getPlaceType() == PlaceType.GENERIC_IMPASSABLE) {
							continue;
						}
						MainController.setMapLocationListeners(c, i, j);
					}
				}
			}
		}

		// Hotkey bindings:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.KEYBINDS) {
			for (KeyboardAction ka : KeyboardAction.values()) {
				if (((EventTarget) MainController.document.getElementById("primary_" + ka)) != null)
					((EventTarget) MainController.document.getElementById("primary_" + ka)).addEventListener("click", e -> {
						MainController.actionToBind = ka;
						MainController.primaryBinding = true;
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) MainController.document.getElementById("primaryClear_" + ka)) != null)
					((EventTarget) MainController.document.getElementById("primaryClear_" + ka)).addEventListener("click", e -> {
						Main.getProperties().hotkeyMapPrimary.put(ka, null);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);

				if (((EventTarget) MainController.document.getElementById("secondary_" + ka)) != null)
					((EventTarget) MainController.document.getElementById("secondary_" + ka)).addEventListener("click", e -> {
						MainController.actionToBind = ka;
						MainController.primaryBinding = false;
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) MainController.document.getElementById("secondaryClear_" + ka)) != null)
					((EventTarget) MainController.document.getElementById("secondaryClear_" + ka)).addEventListener("click", e -> {
						Main.getProperties().hotkeyMapSecondary.put(ka, null);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);

			}
		}
		
		// Gender preferences:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.GENDER_PREFERENCE) {
			for (Gender g : Gender.values()) {
				for(GenderPreference preference : GenderPreference.values()) {
					if (((EventTarget) MainController.document.getElementById(preference+"_"+g)) != null) {
						((EventTarget) MainController.document.getElementById(preference+"_"+g)).addEventListener("click", e -> {
							Main.getProperties().genderPreferencesMap.put(g, preference.getValue());
							Main.saveProperties();
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
			}
		}
		
		// Furry preferences:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.FURRY_PREFERENCE) {

			// Human encounter rates:
			if (((EventTarget) MainController.document.getElementById("furry_preference_human_encounter_zero")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_human_encounter_zero")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=0;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_zero", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_zero", "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Disabled", "Randomly generated NPCs will never be fully human, unless all of the other furry preference options are set to disabled.");
				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_zero", "mouseenter", el, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_human_encounter_one")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_human_encounter_one")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=1;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_one", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_one", "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("5%",
						"There will be a 5% chance for any randomly generated NPC to be fully human. (It will be 100% if all of the other furry preference options are set to disabled)");
				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_one", "mouseenter", el, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_human_encounter_two")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_human_encounter_two")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=2;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_two", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_two", "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("25%",
						"There will be a 25% chance for any randomly generated NPC to be fully human. (It will be 100% if all of the other furry preference options are set to disabled)");
				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_two", "mouseenter", el, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_human_encounter_three")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_human_encounter_three")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=3;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_three", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_three", "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("50%",
						"There will be a 50% chance for any randomly generated NPC to be fully human. (It will be 100% if all of the other furry preference options are set to disabled)");
				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_three", "mouseenter", el, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_human_encounter_four")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_human_encounter_four")).addEventListener("click", e -> {
					Main.getProperties().humanEncountersLevel=4;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_four", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_four", "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("75%",
						"There will be a 75% chance for any randomly generated NPC to be fully human. (It will be 100% if all of the other furry preference options are set to disabled)");
				MainController.addEventListener(MainController.document, "furry_preference_human_encounter_four", "mouseenter", el, false);
			}
			
			
			// Forced TF racial limits:
			id = "forced_tf_limit_human";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPreference = FurryPreference.HUMAN;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Human Only",
						"Forced transformations from NPCs will only ever affect your body's non-racial stats, and if a new part is required (such as a vagina or penis) it will always grow to be a human one.");
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			id = "forced_tf_limit_minimum";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPreference = FurryPreference.MINIMUM;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Minimum Furry",
						"Forced transformations from NPCs will have the chance to give you non-human hair, ears, eyes, tails, horns, antenna, and wings. All other parts will always remain human.");
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			id = "forced_tf_limit_reduced";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPreference = FurryPreference.REDUCED;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Lesser Furry",
						"Forced transformations from NPCs will have the chance to give you non-human hair, ears, eyes, tails, horns, antenna, wings, breasts, ass, genitalia, arms, and legs. Your skin and face will always remain human.");
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			id = "forced_tf_limit_normal";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPreference = FurryPreference.NORMAL;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Greater Furry",
						"Forced transformations from NPCs will have the chance to give you non-human hair, ears, eyes, tails, horns, antenna, wings, breasts, ass, genitalia, arms, legs, skin, and face. (So everything can be affected.)");
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			id = "forced_tf_limit_maximum";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPreference = FurryPreference.MAXIMUM;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Always Greater Furry",
						"Forced transformations from NPCs will always give you non-human hair, ears, eyes, tails, horns, antenna, wings, breasts, genitalia, ass, arms, legs, skin, and face. (So everything will be affected.)");
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			
			
			// Multi-breast options:
			if (((EventTarget) MainController.document.getElementById("furry_preference_multi_breast_zero")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_multi_breast_zero")).addEventListener("click", e -> {
					Main.getProperties().multiBreasts=0;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				MainController.addEventListener(MainController.document, "furry_preference_multi_breast_zero", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "furry_preference_multi_breast_zero", "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Off", "Randomly-generated NPCs will never spawn in with multiple rows of breasts.");
				MainController.addEventListener(MainController.document, "furry_preference_multi_breast_zero", "mouseenter", el, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_multi_breast_one")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_multi_breast_one")).addEventListener("click", e -> {
					Main.getProperties().multiBreasts=1;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				MainController.addEventListener(MainController.document, "furry_preference_multi_breast_one", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "furry_preference_multi_breast_one", "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Furry-only", "Randomly-generated NPCs will only spawn in with multiple rows of breasts if they have furry skin.");
				MainController.addEventListener(MainController.document, "furry_preference_multi_breast_one", "mouseenter", el, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_multi_breast_two")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_multi_breast_two")).addEventListener("click", e -> {
					Main.getProperties().multiBreasts=2;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, "furry_preference_multi_breast_two", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "furry_preference_multi_breast_two", "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("On", "Randomly-generated NPCs will spawn in with multiple rows of breasts if their breast type is furry (starts at 'Minor morph' level).");
				MainController.addEventListener(MainController.document, "furry_preference_multi_breast_two", "mouseenter", el, false);
			}
			
			// Race preferences:
			if (((EventTarget) MainController.document.getElementById("furry_preference_female_human_all")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_female_human_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(r, FurryPreference.HUMAN);
						Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(r, FurryPreference.HUMAN);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_female_minimum_all")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_female_minimum_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(r, FurryPreference.MINIMUM);
						Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(r, FurryPreference.MINIMUM);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_female_reduced_all")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_female_reduced_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(r, FurryPreference.REDUCED);
						Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(r, FurryPreference.REDUCED);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_female_normal_all")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_female_normal_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(r, FurryPreference.NORMAL);
						Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(r, FurryPreference.NORMAL);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_female_maximum_all")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_female_maximum_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(r, FurryPreference.MAXIMUM);
						Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(r, FurryPreference.MAXIMUM);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			for (Subspecies s : Subspecies.values()) {
				for(FurryPreference preference : FurryPreference.values()) {
					id = "FEMININE_" + preference+"_"+s;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.getProperties().subspeciesFeminineFurryPreferencesMap.put(s, preference);
							Main.saveProperties();
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
	
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(preference.getName(), preference.getDescriptionFeminine(s));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					id = "MASCULINE_" + preference+"_"+s;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.getProperties().subspeciesMasculineFurryPreferencesMap.put(s, preference);
							Main.saveProperties();
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
	
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(preference.getName(), preference.getDescriptionMasculine(s));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
			}
			
//			for (Subspecies s : Subspecies.values()) {
//				for(SubspeciesPreference preference : SubspeciesPreference.values()) {
//					id = "FEMININE_" + preference+"_"+s;
//					if (((EventTarget) MainController.document.getElementById(id)) != null) {
//						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
//							Main.getProperties().subspeciesFemininePreferencesMap.put(s, preference);
//							Main.saveProperties();
//							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//						}, false);
//
//						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
//						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
//						TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(preference.getName(), "");
//						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
//					}
//					id = "MASCULINE_" + preference+"_"+s;
//					if (((EventTarget) MainController.document.getElementById(id)) != null) {
//						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
//							Main.getProperties().subspeciesFemininePreferencesMap.put(s, preference);
//							Main.saveProperties();
//							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//						}, false);
//
//						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
//						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
//						TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(preference.getName(), "");
//						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
//					}
//				}
//			}
		}
		
//		// Race preferences:
//		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.SPECIES_PREFERENCE) {
//			for (Subspecies s : Subspecies.values()) {
//				for(SubspeciesPreference preference : SubspeciesPreference.values()) {
//					if (((EventTarget) MainController.document.getElementById(preference+"_"+s)) != null) {
//						((EventTarget) MainController.document.getElementById(preference+"_"+s)).addEventListener("click", e -> {
//							Main.getProperties().subspeciesPreferencesMap.put(s, preference);
//							Main.saveProperties();
//							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//						}, false);
//					}
//				}
//			}
//		}
		
		// Content preferences:

		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.CONTENT_PREFERENCE || Main.game.getCurrentDialogueNode() == CharacterCreation.CONTENT_PREFERENCES) {
			id = "ARTWORK_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.artwork, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "ARTWORK_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.artwork, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}

			for(Artist artist : Artwork.allArtists) {
				id = "ARTIST_"+artist.getFolderName();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.getProperties().preferredArtist = artist.getFolderName();
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			
			id = "NON_CON_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.nonConContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "NON_CON_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.nonConContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "INCEST_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.incestContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "INCEST_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.incestContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "LACTATION_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.lactationContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "LACTATION_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.lactationContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "URETHRAL_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.urethralContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "URETHRAL_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.urethralContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "NIPPLE_PEN_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.nipplePenContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "NIPPLE_PEN_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.nipplePenContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "HAIR_FACIAL_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.facialHairContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "HAIR_FACIAL_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.facialHairContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "HAIR_PUBIC_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.pubicHairContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "HAIR_PUBIC_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.pubicHairContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "HAIR_BODY_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.bodyHairContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "HAIR_BODY_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.bodyHairContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "HAIR_ASS_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.assHairContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "HAIR_ASS_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.assHairContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "FEMININE_BEARD_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.feminineBeardsContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "FEMININE_BEARD_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.feminineBeardsContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			
			id = "FURRY_TAIL_PENETRATION_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.furryTailPenetrationContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "FURRY_TAIL_PENETRATION_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.furryTailPenetrationContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "INFLATION_CONTENT_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.inflationContent, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "INFLATION_CONTENT_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.inflationContent, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			

			id = "FORCED_TF_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPercentage = Math.min(100, Main.getProperties().forcedTFPercentage+10);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "FORCED_TF_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFPercentage = Math.max(0, Main.getProperties().forcedTFPercentage-10);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			

			id = "PREGNANCY_BREAST_GROWTH_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyBreastGrowth = Math.min(10, Main.getProperties().pregnancyBreastGrowth+1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_BREAST_GROWTH_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyBreastGrowth = Math.max(0, Main.getProperties().pregnancyBreastGrowth-1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "PREGNANCY_BREAST_GROWTH_LIMIT_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyBreastGrowthLimit = Math.min(100, Main.getProperties().pregnancyBreastGrowthLimit+1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_BREAST_GROWTH_LIMIT_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyBreastGrowthLimit = Math.max(0, Main.getProperties().pregnancyBreastGrowthLimit-1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			
			id = "PREGNANCY_LACTATION_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyLactationIncrease = Math.min(1000, Main.getProperties().pregnancyLactationIncrease+50);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_LACTATION_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyLactationIncrease = Math.max(0, Main.getProperties().pregnancyLactationIncrease-50);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "PREGNANCY_LACTATION_LIMIT_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyLactationLimit = Math.min(Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue(), Main.getProperties().pregnancyLactationLimit+250);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_LACTATION_LIMIT_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyLactationLimit = Math.max(0, Main.getProperties().pregnancyLactationLimit-250);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			
			id = "FORCED_FETISH_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishPercentage = Math.min(100, Main.getProperties().forcedFetishPercentage+10);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "FORCED_FETISH_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishPercentage = Math.max(0, Main.getProperties().forcedFetishPercentage-10);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			
			// Forced TF Tendency setting events
			id = "FORCED_TF_TENDENCY_"+ForcedTFTendency.NEUTRAL;
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFTendency = ForcedTFTendency.NEUTRAL;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedTFTendency.NEUTRAL.getName(),
						ForcedTFTendency.NEUTRAL.getDescription());
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			
			
			
			id = "FORCED_TF_TENDENCY_"+ForcedTFTendency.FEMININE;
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFTendency = ForcedTFTendency.FEMININE;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedTFTendency.FEMININE.getName(),
						ForcedTFTendency.FEMININE.getDescription());
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			
			id = "FORCED_TF_TENDENCY_"+ForcedTFTendency.FEMININE_HEAVY;
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFTendency = ForcedTFTendency.FEMININE_HEAVY;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedTFTendency.FEMININE_HEAVY.getName(),
						ForcedTFTendency.FEMININE_HEAVY.getDescription());
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			
			id = "FORCED_TF_TENDENCY_"+ForcedTFTendency.MASCULINE;
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFTendency = ForcedTFTendency.MASCULINE;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedTFTendency.MASCULINE.getName(),
						ForcedTFTendency.MASCULINE.getDescription());
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			
			id = "FORCED_TF_TENDENCY_"+ForcedTFTendency.MASCULINE_HEAVY;
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedTFTendency = ForcedTFTendency.MASCULINE_HEAVY;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedTFTendency.MASCULINE_HEAVY.getName(),
						ForcedTFTendency.MASCULINE_HEAVY.getDescription());
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			
			// Forced Fetish Tendency setting events
			id = "FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.NEUTRAL;
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishTendency = ForcedFetishTendency.NEUTRAL;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedFetishTendency.NEUTRAL.getName(),
						ForcedFetishTendency.NEUTRAL.getDescription());
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			
			
			
			id = "FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.BOTTOM;
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishTendency = ForcedFetishTendency.BOTTOM;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedFetishTendency.BOTTOM.getName(),
						ForcedFetishTendency.BOTTOM.getDescription());
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			
			id = "FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.BOTTOM_HEAVY;
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishTendency = ForcedFetishTendency.BOTTOM_HEAVY;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedFetishTendency.BOTTOM_HEAVY.getName(),
						ForcedFetishTendency.BOTTOM_HEAVY.getDescription());
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			
			id = "FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.TOP;
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishTendency = ForcedFetishTendency.TOP;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedFetishTendency.TOP.getName(),
						ForcedFetishTendency.TOP.getDescription());
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			
			id = "FORCED_FETISH_TENDENCY_"+ForcedFetishTendency.TOP_HEAVY;
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().forcedFetishTendency = ForcedFetishTendency.TOP_HEAVY;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(ForcedFetishTendency.TOP_HEAVY.getName(),
						ForcedFetishTendency.TOP_HEAVY.getDescription());
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
		}
		
		// Save/load:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.SAVE_LOAD) {
			for (File f : Main.getSavedGames()) {
				String fileIdentifier = f.getName().substring(0, f.getName().lastIndexOf('.'));
				
				id = "overwrite_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.overwriteConfirmationName.equals(f.getName())) {
							OptionsDialogue.overwriteConfirmationName = "";
							Main.saveGame(fileIdentifier, true);
						} else {
							OptionsDialogue.overwriteConfirmationName = f.getName();
							OptionsDialogue.loadConfirmationName = "";
							OptionsDialogue.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD));
						}
						
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Overwrite", "");
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				id = "load_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.loadConfirmationName.equals(f.getName())) {
							OptionsDialogue.loadConfirmationName = "";
							Main.loadGame(fileIdentifier);
						} else {
							OptionsDialogue.overwriteConfirmationName = "";
							OptionsDialogue.loadConfirmationName = f.getName();
							OptionsDialogue.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD));
						}
						
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Load", "");
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				id = "delete_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.deleteConfirmationName.equals(f.getName())) {
							OptionsDialogue.deleteConfirmationName = "";
							Main.deleteGame(fileIdentifier);
						} else {
							OptionsDialogue.overwriteConfirmationName = "";
							OptionsDialogue.loadConfirmationName = "";
							OptionsDialogue.deleteConfirmationName = f.getName();
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD));
						}
						
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Delete", "");
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			id = "new_saved";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {//TODO
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
					Main.saveGame(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
					
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Save", "");
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
			id = "new_saved_disabled";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Save (Disabled)",
						(!Main.game.isStarted()
								?"You need to have started a game before you can save!"
								:"You cannot save the game unless you are in a tile's default scene!"));
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
		}
		
		// Import:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.IMPORT_EXPORT) {
			for (File f : Main.getCharactersForImport()) {
				String fileIdentifier = f.getName().substring(0, f.getName().lastIndexOf('.'));
				
				id = "delete_saved_character_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.deleteConfirmationName.equals(f.getName())) {
							OptionsDialogue.deleteConfirmationName = "";
							Main.deleteExportedCharacter(fileIdentifier);
						} else {
							OptionsDialogue.overwriteConfirmationName = "";
							OptionsDialogue.loadConfirmationName = "";
							OptionsDialogue.deleteConfirmationName = f.getName();
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.IMPORT_EXPORT));
						}
						
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Delete", "");
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			if (((EventTarget) MainController.document.getElementById("new_saved")) != null) {
				((EventTarget) MainController.document.getElementById("new_saved")).addEventListener("click", e -> {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
					Main.saveGame(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
					
				}, false);
			}
		}
		
		if (Main.game.getCurrentDialogueNode() == CharacterCreation.IMPORT_CHOOSE) {
			for (File f : Main.getCharactersForImport()) {
				String fileIdentifier = f.getName().substring(0, f.getName().lastIndexOf('.'));
				
				if (((EventTarget) MainController.document.getElementById("character_import_" + fileIdentifier )) != null) {
					((EventTarget) MainController.document.getElementById("character_import_" + fileIdentifier )).addEventListener("click", e -> {
						Main.importCharacter(f);
						
					}, false);
				}
			}
		}
		
		// Slave import:
		if (Main.game.getCurrentDialogueNode() == SlaverAlleyDialogue.AUCTION_IMPORT) {
			for (File f : Main.getSlavesForImport()) {
				String fileIdentifier = f.getName().substring(0, f.getName().lastIndexOf('.'));
				
				if (((EventTarget) MainController.document.getElementById("import_slave_" + fileIdentifier )) != null) {
					((EventTarget) MainController.document.getElementById("import_slave_" + fileIdentifier )).addEventListener("click", e -> {
						
						try {
							Game.importCharacterAsSlave(fileIdentifier);
							MainController.updateUI();
							Main.game.flashMessage(Colour.GENERIC_GOOD, "Imported Character!");
						
						} catch(Exception ex) {
							Main.game.flashMessage(Colour.GENERIC_BAD, "Import Failed!");
						}
						
							
					}, false);
				}
			}
		}
		if (Main.game.getCurrentDialogueNode() == SlaverAlleyDialogue.AUCTION_BLOCK_LIST) {
			for (NPC npc : Main.game.getCharactersPresent()) {
				id = npc.getId()+"_BID";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					if(Main.game.getPlayer().isHasSlaverLicense()) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							SlaverAlleyDialogue.setupBidding(npc);
							Main.game.setContent(new Response("", "", SlaverAlleyDialogue.AUCTION_BIDDING));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								UtilText.parse(npc, "Bid on [npc.name]"),
								UtilText.parse(npc, "Start bidding on [npc.name]. There's a chance that the bidding might exceed [npc.her] value, so make sure you have enough money first!"));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					} else {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								UtilText.parse(npc, "Bid on [npc.name]"),
								UtilText.parse(npc, "You don't have a slaver license, so you're unable to big on any slaves!"));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
			}
		}
		
		// Save/load enchantment:
		if (Main.game.getCurrentDialogueNode() == EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD) {
			for (File f : EnchantmentDialogue.getSavedEnchants()) {
				String fileIdentifier = f.getName().substring(0, f.getName().lastIndexOf('.'));
				
				id = "overwrite_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || EnchantmentDialogue.overwriteConfirmationName.equals(f.getName())) {
							EnchantmentDialogue.overwriteConfirmationName = "";
							EnchantmentDialogue.saveEnchant(fileIdentifier, true);
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
							
						} else {
							EnchantmentDialogue.overwriteConfirmationName = f.getName();
							EnchantmentDialogue.loadConfirmationName = "";
							EnchantmentDialogue.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
						}
						
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Overwrite", "");
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				id = "load_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || EnchantmentDialogue.loadConfirmationName.equals(f.getName())) {
							EnchantmentDialogue.loadConfirmationName = "";
							LoadedEnchantment lEnch = EnchantmentDialogue.loadEnchant(fileIdentifier);
							EnchantmentDialogue.resetEnchantmentVariables();
							EnchantmentDialogue.initModifiers(lEnch.getSuitableItem());
							EnchantmentDialogue.getEffects().clear();
							for(ItemEffect ie : lEnch.getEffects()) {
								EnchantmentDialogue.addEffect(ie);
							}
							EnchantmentDialogue.setOutputName(lEnch.getName());
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", EnchantmentDialogue.ENCHANTMENT_MENU));
							
						} else {
							EnchantmentDialogue.overwriteConfirmationName = "";
							EnchantmentDialogue.loadConfirmationName = f.getName();
							EnchantmentDialogue.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
						}
						
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Load", "");
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				id = "delete_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || EnchantmentDialogue.deleteConfirmationName.equals(f.getName())) {
							EnchantmentDialogue.deleteConfirmationName = "";
							EnchantmentDialogue.deleteEnchant(fileIdentifier);
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
							
						} else {
							EnchantmentDialogue.overwriteConfirmationName = "";
							EnchantmentDialogue.loadConfirmationName = "";
							EnchantmentDialogue.deleteConfirmationName = f.getName();
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
						}
						
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Delete", "");
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			
			id = "new_saved";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
					EnchantmentDialogue.saveEnchant(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
					
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Save", "");
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
			
			for(Entry<String, LoadedEnchantment> entry : EnchantmentDialogue.getLoadedEnchantmentsMap().entrySet()) {
				id = "LOADED_ENCHANTMENT_"+entry.getKey();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setLoadedEnchantment(entry.getValue());
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
		}
		
		MainController.setResponseEventListeners();
	
	}
	
}
