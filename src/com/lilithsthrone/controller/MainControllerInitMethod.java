package com.lilithsthrone.controller;

import java.io.File;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.events.EventTarget;

import com.lilithsthrone.controller.eventListeners.EnchantmentEventListener;
import com.lilithsthrone.controller.eventListeners.InventorySelectedItemEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInventoryEventListener;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.Properties;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Horn;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Testicle;
import com.lilithsthrone.game.character.body.types.AbstractArmType;
import com.lilithsthrone.game.character.body.types.AbstractAssType;
import com.lilithsthrone.game.character.body.types.AbstractBreastType;
import com.lilithsthrone.game.character.body.types.AbstractEarType;
import com.lilithsthrone.game.character.body.types.AbstractHornType;
import com.lilithsthrone.game.character.body.types.AbstractLegType;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.FootStructure;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.TreeEntry;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounter;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.persona.SexualOrientationPreference;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.combat.CombatMove;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.combat.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Library;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePoker;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.CombatMovesSetup;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.MapTravelType;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
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
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.SlaveJob;
import com.lilithsthrone.game.occupantManagement.SlaveJobHours;
import com.lilithsthrone.game.occupantManagement.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.SlavePermission;
import com.lilithsthrone.game.occupantManagement.SlavePermissionSetting;
import com.lilithsthrone.game.settings.ContentPreferenceValue;
import com.lilithsthrone.game.settings.ForcedFetishTendency;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artist;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

import javafx.stage.FileChooser;

/**
 * This method was causing MainController to lag out Eclipse, so I moved it to a separate file.
 * 
 * @since 0.2.5
 * @version 0.2.11
 * @author Innoxia
 */
public class MainControllerInitMethod {

	private static File lastOpened = null;

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
		
		if(Main.game.isInCombat()) {
			for(GameCharacter combatant : Combat.getAllCombatants(true)) {
				for(DamageType dt : DamageType.values()) {
					id = combatant.getId()+"_COMBAT_SHIELD_"+dt;
					
					if (MainController.document.getElementById(id) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(dt.getName())+" shielding",
								dt==DamageType.HEALTH
									? UtilText.parse(combatant, "[npc.Name] will block incoming damage from any non-lust source by this amount."
											+ " Other damage type shielding will be used first, with health shielding used as the last resort."
											+ " Negative values have no effect.")
									: dt!=DamageType.LUST
										? UtilText.parse(combatant, "[npc.Name] will block incoming "+dt.getName()+" damage by this amount."
												+ " Once this shielding is broken, health shielding will be used, and once that's broken, damage will be dealt [npc.her] health."
												+ " Negative values have no effect.")
										: UtilText.parse(combatant, "[npc.Name] will block incoming "+dt.getName()+" damage by this amount."
												+ " Once this shielding is broken, incoming "+dt.getName()+" damage will cause [npc.her] lust to rise."
												+ " Negative values have no effect.")),
								false);
					}
				}
			}
		}

		if(Main.game.getCurrentDialogueNode().equals(CharactersPresentDialogue.MENU)
				|| Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CONTACTS_CHARACTER)
				|| Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CHARACTER_APPEARANCE)
				|| Main.game.getCurrentDialogueNode().equals(OccupantManagementDialogue.SLAVE_MANAGEMENT_INSPECT)) {
			GameCharacter character = Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CHARACTER_APPEARANCE) ? Main.game.getPlayer() : CharactersPresentDialogue.characterViewed;
			id = "ARTWORK_ADD";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					// Create file chooser for .jpg and .png images in the most recently used directory
					FileChooser chooser = new FileChooser();
					chooser.setTitle("Add Images");
					chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.gif"));
					if (lastOpened != null)
						chooser.setInitialDirectory(lastOpened);

					List<File> files = chooser.showOpenMultipleDialog(Main.primaryStage);
					if (files != null && !files.isEmpty()) {
						lastOpened = files.get(0).getParentFile();

						character.importImages(files);

						if (!character.isPlayer()) CharactersPresentDialogue.resetContent(character);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setInformation(
						"Add custom artwork",
						"Browse your own images and add them to the character."
								+ " Please note that GIF animation files are limited to a <b>maximum of 10MB</b> in size, and if over 1MB, <b>may</b> cause [style.italicsBad(significant lag)], depending on your system."),
						false);
			}

			if (character.hasArtwork()) {
				try {
					Artwork artwork = character.getCurrentArtwork();

					id = "ARTWORK_INFO";
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(!artwork.getArtist().getWebsites().isEmpty()) {
								Util.openLinkInDefaultBrowser(artwork.getArtist().getWebsites().get(0).getURL());
							}
						}, false);

						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						String description;
						if (artwork.getArtist().getName().equals("Custom")) {
							description = "You added this yourself.";
						} else if (artwork.getArtist().getWebsites().isEmpty()) {
							description = "This artist has no associated websites!";
						} else {
							description = "Click to open <b style='color:"+artwork.getArtist().getColour().toWebHexString()+";'>"+artwork.getArtist().getWebsites().get(0).getName()+"</b>"
									+ " ("+artwork.getArtist().getWebsites().get(0).getURL()+") <b>externally</b> in your default browser!";
						}
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setInformation(
								"Artwork by <b style='color:"+artwork.getArtist().getColour().toWebHexString()+";'>"+artwork.getArtist().getName()+"</b>",
								description), false);
					}

					id = "ARTWORK_PREVIOUS";
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(artwork.getTotalArtworkCount()>1) {
								artwork.incrementIndex(-1);
								if (!character.isPlayer()) CharactersPresentDialogue.resetContent(character);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}

					id = "ARTWORK_NEXT";
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(artwork.getTotalArtworkCount()>1) {
								artwork.incrementIndex(1);
								if (!character.isPlayer()) CharactersPresentDialogue.resetContent(character);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}

					id = "ARTWORK_ARTIST_PREVIOUS";
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(character.getArtworkList().size()>1) {
								character.incrementArtworkIndex(-1);
								if (!character.isPlayer()) CharactersPresentDialogue.resetContent(character);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}

					id = "ARTWORK_ARTIST_NEXT";
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(character.getArtworkList().size()>1) {
								character.incrementArtworkIndex(1);
								if (!character.isPlayer()) CharactersPresentDialogue.resetContent(character);
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
		
		if(Main.game.getCurrentDialogueNode().equals(DebugDialogue.SPAWN_MENU) || Main.game.getCurrentDialogueNode().equals(DebugDialogue.ITEM_VIEWER)) {
			id = "";
			
			for(AbstractClothingType clothingType : ClothingType.getAllClothing()) {
				id = clothingType.getId() + "_SPAWN";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addClothing(AbstractClothingType.generateClothing(clothingType));
						MainController.updateUIRightPanel();
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					
					TooltipInventoryEventListener el = new TooltipInventoryEventListener().setGenericClothing(clothingType, clothingType.getAvailablePrimaryColours().get(0));
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
			
			for(AbstractWeaponType weaponType : WeaponType.getAllWeapons()) {
				id = weaponType.getId() + "_SPAWN";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addWeapon(AbstractWeaponType.generateWeapon(weaponType));
						MainController.updateUIRightPanel();
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					
					TooltipInventoryEventListener el = new TooltipInventoryEventListener().setGenericWeapon(weaponType, weaponType.getAvailableDamageTypes().get(0));
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
			
			for(AbstractItemType itemType : ItemType.getAllItems()) {
				id = itemType.getId() + "_SPAWN";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem(AbstractItemType.generateItem(itemType));
						MainController.updateUIRightPanel();
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					
					TooltipInventoryEventListener el = new TooltipInventoryEventListener().setGenericItem(itemType);
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
			
			for(InventorySlot slot : InventorySlot.values()) {
				id = slot + "_SPAWN_SELECT";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						DebugDialogue.itemTag = null;
						DebugDialogue.activeSlot = slot;
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
					
				}
			}
			id = "ITEM_SPAWN_SELECT";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					DebugDialogue.activeSlot = null;
					DebugDialogue.itemTag = null;
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "BOOK_SPAWN_SELECT";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					DebugDialogue.activeSlot = null;
					DebugDialogue.itemTag = ItemTag.BOOK;
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "ESSENCE_SPAWN_SELECT";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					DebugDialogue.activeSlot = null;
					DebugDialogue.itemTag = ItemTag.ESSENCE;
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "SPELL_SPAWN_SELECT";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					DebugDialogue.activeSlot = null;
					DebugDialogue.itemTag = ItemTag.SPELL_BOOK;
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "HIDDEN_SPAWN_SELECT";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					DebugDialogue.activeSlot = null;
					DebugDialogue.itemTag = ItemTag.HIDDEN_IN_DEBUG_SPAWNER;
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
		
		
		
		
		if(Main.game.getCurrentDialogueNode() == CharacterCreation.BACKGROUND_SELECTION_MENU) {
			for(Occupation history : Occupation.values()) {
				id = "OCCUPATION_"+history;
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
			
			// Gifts:
			if(Main.game.getCurrentDialogueNode().equals(GiftDialogue.GIFT_DIALOGUE)) {
				for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayer().getAllWeaponsInInventory().entrySet()) {
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
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setWeapon(entry.getKey(), Main.game.getPlayer(), false);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				for (Entry<AbstractItem, Integer> entry : Main.game.getPlayer().getAllItemsInInventory().entrySet()) {
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
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setItem(entry.getKey(), Main.game.getPlayer(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayer().getAllClothingInInventory().entrySet()) {
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
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setClothing(entry.getKey(), Main.game.getPlayer(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
			}
			
			// Non-equipped inventory:
			for(int i=0 ; i<RenderingEngine.INVENTORY_PAGES; i++) {
				MainController.setInventoryPageLeft(i);
				MainController.setInventoryPageRight(i);
			}
			// Quest inventory:
			MainController.setInventoryPageLeft(5);
			
			
			// Player:
			for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayer().getAllWeaponsInInventory().entrySet()) {
				id = "PLAYER_WEAPON_" + entry.getKey().hashCode();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), Main.game.getPlayer());
					MainController.addEventListener(MainController.document, id, "click", el, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setWeapon(entry.getKey(), Main.game.getPlayer(), false);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			for (Entry<AbstractItem, Integer> entry : Main.game.getPlayer().getAllItemsInInventory().entrySet()) {
				id = "PLAYER_ITEM_" + entry.getKey().hashCode();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), Main.game.getPlayer());
					MainController.addEventListener(MainController.document, id, "click", el, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setItem(entry.getKey(), Main.game.getPlayer(), null);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayer().getAllClothingInInventory().entrySet()) {
				id = "PLAYER_CLOTHING_" + entry.getKey().hashCode();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), Main.game.getPlayer());
					MainController.addEventListener(MainController.document, id, "click", el, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setClothing(entry.getKey(), Main.game.getPlayer(), null);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			
			// Partner:
			if(InventoryDialogue.getInventoryNPC()!=null) {
				String idModifier = "NPC_"+InventoryDialogue.getInventoryNPC().getId()+"_";
				for (Entry<AbstractWeapon, Integer> entry : InventoryDialogue.getInventoryNPC().getAllWeaponsInInventory().entrySet()) {
					id = idModifier+"WEAPON_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), InventoryDialogue.getInventoryNPC());
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setWeapon(entry.getKey(), InventoryDialogue.getInventoryNPC(), false);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				for (Entry<AbstractClothing, Integer> entry : InventoryDialogue.getInventoryNPC().getAllClothingInInventory().entrySet()) {
					id = idModifier+"CLOTHING_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), InventoryDialogue.getInventoryNPC());
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setClothing(entry.getKey(), InventoryDialogue.getInventoryNPC(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				for (Entry<AbstractItem, Integer> entry : InventoryDialogue.getInventoryNPC().getAllItemsInInventory().entrySet()) {
					id = idModifier+"ITEM_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), InventoryDialogue.getInventoryNPC());
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setItem(entry.getKey(), InventoryDialogue.getInventoryNPC(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
			// Floor:
			} else {
				// Weapons on floor:
				for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
					id = "FLOOR_WEAPON_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), null);
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setWeapon(entry.getKey(), null, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				// Clothing on floor:
				for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getAllClothingInInventory().entrySet()) {
					id = "FLOOR_CLOTHING_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), null);
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setClothing(entry.getKey(), null, null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				// Items on floor:
				for (Entry<AbstractItem, Integer> entry : Main.game.getPlayerCell().getInventory().getAllItemsInInventory().entrySet()) {
					id = "FLOOR_ITEM_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), null);
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setItem(entry.getKey(), null, null);
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
							TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setWeapon((AbstractWeapon) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), false);
							((EventTarget) MainController.document.getElementById("WEAPON_" + i)).addEventListener("mouseenter",el2, false);
						}
						
						if (((EventTarget) MainController.document.getElementById("CLOTHING_" + i)) != null) {
							InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory((AbstractClothing) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), i);
							MainController.addEventListener(MainController.document, "CLOTHING_" + i, "click", el, false);
							MainController.addEventListener(MainController.document, "CLOTHING_" + i, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, "CLOTHING_" + i, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setClothing((AbstractClothing) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), null);
							MainController.addEventListener(MainController.document, "CLOTHING_" + i, "mouseenter", el2, false);
						}
						
						if (((EventTarget) MainController.document.getElementById("ITEM_" + i)) != null) {
							InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory((AbstractItem) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), i);
							MainController.addEventListener(MainController.document, "ITEM_" + i, "click", el, false);
							MainController.addEventListener(MainController.document, "ITEM_" + i, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, "ITEM_" + i, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setItem((AbstractItem) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), null);
							MainController.addEventListener(MainController.document, "ITEM_" + i, "mouseenter", el2, false);
						}
					}
				}
			}
			
			for(TFEssence essence : TFEssence.values()) {
				if (((EventTarget) MainController.document.getElementById("ESSENCE_" + essence.hashCode())) != null) {
					MainController.addEventListener(MainController.document, "ESSENCE_" + essence.hashCode(), "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, "ESSENCE_" + essence.hashCode(), "mouseleave", MainController.hideTooltipListener, false);
					
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setEssence(essence);
					MainController.addEventListener(MainController.document, "ESSENCE_" + essence.hashCode(), "mouseenter", el2, false);
				}
				if (((EventTarget) MainController.document.getElementById("ESSENCE_COST_" + essence.hashCode())) != null) {
					MainController.addEventListener(MainController.document, "ESSENCE_COST_" + essence.hashCode(), "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, "ESSENCE_COST_" + essence.hashCode(), "mouseleave", MainController.hideTooltipListener, false);
					
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setEssence(essence);
					MainController.addEventListener(MainController.document, "ESSENCE_COST_" + essence.hashCode(), "mouseenter", el2, false);
				}
			}
			
	
			// -------------------- Enchantments --------------------

			if (Main.game.getCurrentDialogueNode() == EnchantmentDialogue.ENCHANTMENT_MENU) {
				// Tooltips:
				if (((EventTarget) MainController.document.getElementById("MOD_PRIMARY_ENCHANTING")) != null) {

					EnchantmentEventListener el = new EnchantmentEventListener().setPrimaryModifier(TFModifier.NONE);
					MainController.addEventListener(MainController.document, "MOD_PRIMARY_ENCHANTING", "click", el, false);

					MainController.addEventListener(MainController.document, "MOD_PRIMARY_ENCHANTING", "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, "MOD_PRIMARY_ENCHANTING", "mouseleave", MainController.hideTooltipListener, false);

					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setTFModifier(EnchantmentDialogue.getPrimaryMod());
					MainController.addEventListener(MainController.document, "MOD_PRIMARY_ENCHANTING", "mouseenter", el2, false);
				}
				if (((EventTarget) MainController.document.getElementById("MOD_SECONDARY_ENCHANTING")) != null) {

					EnchantmentEventListener el = new EnchantmentEventListener().setSecondaryModifier(TFModifier.NONE);
					MainController.addEventListener(MainController.document, "MOD_SECONDARY_ENCHANTING", "click", el, false);

					MainController.addEventListener(MainController.document, "MOD_SECONDARY_ENCHANTING", "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, "MOD_SECONDARY_ENCHANTING", "mouseleave", MainController.hideTooltipListener, false);

					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setTFModifier(EnchantmentDialogue.getSecondaryMod());
					MainController.addEventListener(MainController.document, "MOD_SECONDARY_ENCHANTING", "mouseenter", el2, false);
				}

				for(TFPotency potency : TFPotency.values()) {
					id = "POTENCY_"+potency;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {

						EnchantmentEventListener el = new EnchantmentEventListener().setPotency(potency);
						MainController.addEventListener(MainController.document, id, "click", el, false);

						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
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

				AbstractItemEffectType effect = EnchantmentDialogue.getIngredient().getEnchantmentEffect();
				int maxLimit = effect.getMaximumLimit();
				int currentLimit = EnchantmentDialogue.getLimit();

				id = "LIMIT_MINIMUM";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					if(currentLimit > 0) {
						EnchantmentEventListener el = new EnchantmentEventListener().setLimit(0);
						MainController.addEventListener(MainController.document, id, "click", el, false);
					}
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				}

				id = "LIMIT_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					if(currentLimit > 0) {
                        EnchantmentEventListener el = new EnchantmentEventListener().setLimit(Math.max(0, EnchantmentDialogue.getLimit() - effect.getLargeLimitChange()));
                        MainController.addEventListener(MainController.document, id, "click", el, false);
					}
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				}

				id = "LIMIT_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					if(currentLimit > 0) {
						EnchantmentEventListener el = new EnchantmentEventListener().setLimit(EnchantmentDialogue.getLimit() - effect.getSmallLimitChange());
						MainController.addEventListener(MainController.document, id, "click", el, false);
					}
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				}

				id = "LIMIT_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					if(currentLimit < maxLimit) {
						EnchantmentEventListener el = new EnchantmentEventListener().setLimit(EnchantmentDialogue.getLimit() + effect.getSmallLimitChange());
						MainController.addEventListener(MainController.document, id, "click", el, false);
					}
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				}

				id = "LIMIT_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					if(currentLimit < maxLimit) {
                        EnchantmentEventListener el = new EnchantmentEventListener().setLimit(Math.min(maxLimit, EnchantmentDialogue.getLimit() + effect.getLargeLimitChange()));
                        MainController.addEventListener(MainController.document, id, "click", el, false);
					}
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				}

				id = "LIMIT_MAXIMUM";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					if(currentLimit < maxLimit) {
						EnchantmentEventListener el = new EnchantmentEventListener().setLimit(maxLimit);
						MainController.addEventListener(MainController.document, id, "click", el, false);
					}
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
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

						} else if(EnchantmentDialogue.getIngredient() instanceof AbstractWeapon) {
							Main.game.setContent(new Response("Back", "Stop enchanting.", InventoryDialogue.WEAPON_INVENTORY){
								@Override
								public void effects() {
									EnchantmentDialogue.resetEnchantmentVariables();
								}
							});

						} else if(EnchantmentDialogue.getIngredient() instanceof Tattoo) {
							Main.game.setContent(new Response("Back", "Stop enchanting.", BodyChanging.getTarget().isPlayer()?SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS:OccupantManagementDialogue.SLAVE_MANAGEMENT_TATTOOS){
								@Override
								public void effects() {
									EnchantmentDialogue.resetEnchantmentVariables();
								}
							});

						}

					}, false);

					MainController.addEventListener(MainController.document, "INGREDIENT_ENCHANTING", "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, "INGREDIENT_ENCHANTING", "mouseleave", MainController.hideTooltipListener, false);

					TooltipInventoryEventListener el2 = null;
					if(EnchantmentDialogue.getIngredient() instanceof AbstractItem) {
						el2 = new TooltipInventoryEventListener().setItem((AbstractItem) EnchantmentDialogue.getIngredient(), Main.game.getPlayer(), null);

					} else if(EnchantmentDialogue.getIngredient() instanceof AbstractClothing) {
						el2 = new TooltipInventoryEventListener().setClothing((AbstractClothing) EnchantmentDialogue.getIngredient(), Main.game.getPlayer(), null);

					} else if(EnchantmentDialogue.getIngredient() instanceof AbstractWeapon) {
						el2 = new TooltipInventoryEventListener().setWeapon((AbstractWeapon) EnchantmentDialogue.getIngredient(), Main.game.getPlayer(), false);

					}  else if(EnchantmentDialogue.getIngredient() instanceof Tattoo) {
						el2 = new TooltipInventoryEventListener().setTattoo(EnchantmentDialogue.getTattooSlot(), (Tattoo) EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getTattooBearer(), EnchantmentDialogue.getTattooBearer());
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
				for (TFModifier tfMod : EnchantmentDialogue.getIngredient().getEnchantmentEffect().getPrimaryModifiers()) {
					if (((EventTarget) MainController.document.getElementById("MOD_PRIMARY_" + tfMod.hashCode())) != null) {

						EnchantmentEventListener el = new EnchantmentEventListener().setPrimaryModifier(tfMod);
						MainController.addEventListener(MainController.document, "MOD_PRIMARY_"+tfMod.hashCode(), "click", el, false);

						MainController.addEventListener(MainController.document, "MOD_PRIMARY_" + tfMod.hashCode(), "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, "MOD_PRIMARY_" + tfMod.hashCode(), "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setTFModifier(tfMod);
						MainController.addEventListener(MainController.document, "MOD_PRIMARY_" + tfMod.hashCode(), "mouseenter", el2, false);
					}
				}
				// Choosing a secondary modifier:
				for (TFModifier tfMod : EnchantmentDialogue.getIngredient().getEnchantmentEffect().getSecondaryModifiers(EnchantmentDialogue.getPrimaryMod())) {
					if (((EventTarget) MainController.document.getElementById("MOD_SECONDARY_" + tfMod.hashCode())) != null) {

						EnchantmentEventListener el = new EnchantmentEventListener().setSecondaryModifier(tfMod);
						MainController.addEventListener(MainController.document, "MOD_SECONDARY_"+tfMod.hashCode(), "click", el, false);

						MainController.addEventListener(MainController.document, "MOD_SECONDARY_" + tfMod.hashCode(), "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, "MOD_SECONDARY_" + tfMod.hashCode(), "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setTFModifier(tfMod);
						MainController.addEventListener(MainController.document, "MOD_SECONDARY_" + tfMod.hashCode(), "mouseenter", el2, false);
					}
				}
			}

			
			// -------------------- Room upgrades -------------------- //
			
			if(Main.game.getCurrentDialogueNode() == OccupantManagementDialogue.ROOM_MANAGEMENT) {
				for(Cell c : OccupantManagementDialogue.getImportantCells()) {
					id = c.getId();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									OccupantManagementDialogue.cellToInspect = c;
								}
								@Override
								public DialogueNode getNextDialogue() {
									return OccupantManagementDialogue.ROOM_UPGRADES_MANAGEMENT;
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
									OccupantManagementDialogue.cellToInspect = c;
								}
								@Override
								public DialogueNode getNextDialogue() {
									return OccupantManagementDialogue.ROOM_UPGRADES_MANAGEMENT;
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

			if(Main.game.getCurrentDialogueNode() == OccupantManagementDialogue.ROOM_UPGRADES
					|| Main.game.getCurrentDialogueNode() == OccupantManagementDialogue.ROOM_UPGRADES_MANAGEMENT) {
				for(PlaceUpgrade placeUpgrade : PlaceUpgrade.values()) {
					
					id = "ROOM_MOD_INFO_"+placeUpgrade;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("", (OccupantManagementDialogue.cellToInspect.getPlace().getPlaceUpgrades().contains(placeUpgrade)
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
										OccupantManagementDialogue.cellToInspect.addPlaceUpgrade(placeUpgrade);
										Main.game.getPlayer().incrementMoney(-placeUpgrade.getInstallCost());
									}
								});
							} else {
								Main.game.setContent(new Response("", "", LilayaHomeGeneric.ROOM_ARTHUR_INSTALLATION){
									@Override
									public void effects() {
										Main.game.getNpc(Arthur.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), true);
										OccupantManagementDialogue.cellToInspect.addPlaceUpgrade(placeUpgrade);
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.arthursRoomInstalled, true);
									}
								});
							}
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Purchase Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getInstallCost())+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					id = placeUpgrade+"_BUY_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Purchase Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getInstallCost())+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = placeUpgrade+"_SELL";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									OccupantManagementDialogue.cellToInspect.removePlaceUpgrade(placeUpgrade);
									Main.game.getPlayer().incrementMoney(-placeUpgrade.getRemovalCost());
								}
							});
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					id = placeUpgrade+"_SELL_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove Modification",
								(placeUpgrade.isCoreRoomUpgrade()
										?"You cannot directly remove core upgrades. Instead, you'll have to purchase a different core modification in order to remove the current one."
										:"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade)));
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
										OccupantManagementDialogue.cellToInspect.getPlace().setName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
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
				MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(Main.game.getPlayerCell().getType(), Main.game.getPlayerCell().getLocation());

				for(FluidStored fluid : room.getFluidsStored()) {
					fluidHandler(room, fluid);
				}

			}
			
			
			if(Main.game.getCurrentDialogueNode() == OccupantManagementDialogue.OCCUPANT_OVERVIEW) {
				id ="PREVIOUS_DAY";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						OccupantManagementDialogue.incrementDayNumber(-1);
						Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				id ="NEXT_DAY";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						OccupantManagementDialogue.incrementDayNumber(1);
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
										NPC slave = Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected();
										slave.setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent()));
										slave.loadImages();
									}
								});
							}
							
						}
							
					}, false);
				}

				id = Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().getId()+"_RENAME_SURNAME";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
	
						boolean unsuitableName = false;
					 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('slaveSurnameInput')")!=null) {
						 
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveSurnameInput').value;");
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
										NPC slave = Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected();
										slave.setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
//										slave.loadImages();
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
										Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected().setPetName(Main.game.getPlayer(), Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
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
											try {
												Main.game.getNPCById(id).setPetName(Main.game.getPlayer(), Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
											} catch (Exception e) {
												Util.logGetNpcByIdError("initMainControllerListeners(), instance 1.", id);
											}
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
							
							Main.game.setContent(new Response("", "", OccupantManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
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
							Main.game.setContent(new Response("", "", OccupantManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
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
								Main.game.setContent(new Response("", "", OccupantManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
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
								Main.game.setContent(new Response("", "", OccupantManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
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
									Main.game.setContent(new Response("", "", OccupantManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
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
								Main.game.setContent(new Response("", "", OccupantManagementDialogue.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
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
								Main.game.setContent(new Response("", "", OccupantManagementDialogue.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getSlaveryManagerSlaveSelected())));
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
				NPC slave;
				
				try {
					slave = (NPC) Main.game.getNPCById(slaveId);
				} catch (Exception e) {
					Util.logGetNpcByIdError("initMainControllerListeners(), instance 2.", slaveId);
					continue;
				}
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", OccupantManagementDialogue.getSlaveryManagementInspectSlaveDialogue(slave)));
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
						Main.game.setContent(new Response("", "", OccupantManagementDialogue.getSlaveryManagementSlaveJobsDialogue(slave)));
					}, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

					TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Job",
							UtilText.parse(slave, "Set [npc.namePos] job and work hours."));
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
				
				id = slaveId+"_PERMISSIONS"; //TODO
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", OccupantManagementDialogue.getSlaveryManagementSlavePermissionsDialogue(slave)));
					}, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

					TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Permissions",
							UtilText.parse(slave, "Manage [npc.namePos] permissions."));
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
							UtilText.parse(slave, "Manage [npc.namePos] inventory."));
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
								Main.game.getPlayer().incrementMoney((int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()));
								Main.game.getDialogueFlags().getSlaveTrader().addSlave(slave);
								slave.setLocation(Main.game.getDialogueFlags().getSlaveTrader().getWorldLocation(), Main.game.getDialogueFlags().getSlaveTrader().getLocation(), true);
							}
						});
					}, false);
					
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

					TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Sell Slave",
							UtilText.parse(slave, "[npc.Name] has a value of "+UtilText.formatAsMoney(slave.getValueAsSlave(true), "b", Colour.GENERIC_GOOD)+"<br/>"
									+ "However, "+Main.game.getDialogueFlags().getSlaveTrader().getName(true)+" will buy [npc.herHim] for "
										+UtilText.formatAsMoney((int)(slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()), "b", Colour.GENERIC_ARCANE)+"."));
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
						Main.game.setContent(new Response("", "", OccupantManagementDialogue.SLAVE_MANAGEMENT_COSMETICS_HAIR) {
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
			
			for(String occupantId : Main.game.getPlayer().getFriendlyOccupants()) {
				id = occupantId;
				NPC occupant;

				try {
					occupant = (NPC) Main.game.getNPCById(occupantId);
				} catch (Exception e) {
					Util.logGetNpcByIdError("initMainControllerListeners(), instance 3.", occupantId);
					continue;
				}
				
				if(occupant!=null) { // It shouldn't equal null...
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", OccupantManagementDialogue.getSlaveryManagementInspectSlaveDialogue(occupant)));
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Inspect Character",
								UtilText.parse(occupant, "Inspect [npc.name]."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_JOB"; //TODO
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Job", "You cannot manage a free-willed occupant's job.");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_PERMISSIONS"; //TODO
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Permissions", "You cannot manage a free-willed occupant's permissions.");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_INVENTORY";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.mainController.openInventory(occupant, InventoryInteraction.FULL_MANAGEMENT);
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Inventory",
								UtilText.parse(occupant, "Manage [npc.namePos] inventory."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_TRANSFER";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									occupant.setHomeLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation());
									occupant.returnToHome();
								}
							});
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Move Here",
								UtilText.parse(occupant, "Move [npc.name] to your current location."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_TRANSFER_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Move Here",
								UtilText.parse(occupant, "You cannot move [npc.name] to this location, as there's no room for [npc.herHim] here."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_COSMETICS";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", OccupantManagementDialogue.SLAVE_MANAGEMENT_COSMETICS_HAIR) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(occupant);
									BodyChanging.setTarget(occupant);
								}
							});
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Send to Kate",
								UtilText.parse(occupant, "Send [npc.name] to Kate's beauty salon, 'Succubi's Secrets', to get [npc.her] appearance changed."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_COSMETICS_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Send to Kate", "Will be added soon!");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
			}
			

			if(Main.game.getDialogueFlags().getSlaveTrader()!=null) {
				for(String slaveId : Main.game.getDialogueFlags().getSlaveTrader().getSlavesOwned()) {
					id = slaveId+"_TRADER";
					NPC slave;
					try {
						slave = (NPC) Main.game.getNPCById(slaveId);
					} catch (Exception e) {
						Util.logGetNpcByIdError("initMainControllerListeners(), instance 4.", slaveId);
						continue;
					}
					
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", OccupantManagementDialogue.getSlaveryManagementInspectSlaveDialogue(slave)));
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
								UtilText.parse(slave, "You cannot manage [npc.namePos] job, as you don't own [npc.herHim]!"));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRADER_PERMISSIONS";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Permissions",
								UtilText.parse(slave, "You cannot manage [npc.namePos] permissions, as you don't own [npc.herHim]!"));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_TRADER_INVENTORY";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Inventory",
								UtilText.parse(slave, "You cannot manage [npc.namePos] inventory, as you don't own [npc.herHim]!"));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					
					id = slaveId+"_TRADER_TRANSFER";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Move Slave To Here",
								UtilText.parse(slave, "You cannot move [npc.name] to this location, as you don't own [npc.herHim], as well as due to the fact that [npc.sheIs] already here!"));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = slaveId+"_BUY";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-(int)(slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier()));
									Main.game.getPlayer().addSlave(slave);
									slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
								}
							});
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Buy Slave",
								UtilText.parse(slave, "[npc.Name] has a value of "+UtilText.formatAsMoney(slave.getValueAsSlave(true), "b", Colour.GENERIC_GOOD)+"<br/>"
										+ "However, "+Main.game.getDialogueFlags().getSlaveTrader().getName(true)+" will sell [npc.herHim] for "
											+UtilText.formatAsMoney((int)(slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier()), "b", Colour.GENERIC_ARCANE)+"."));
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
										Main.game.getActiveNPC().setPetName(Main.game.getPlayer(), Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
									}
								});
							}
							
						}
							
					}, false);
				}
			}
			
			
			// -------------------- Character Creation -------------------- //
			
			if(!Main.game.isInNewWorld()) {
				if(Main.game.getCurrentDialogueNode().equals(CharacterCreation.CHOOSE_APPEARANCE)) {
					for(Month month : Month.values()) {
						id = "STARTING_MONTH_"+month;
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.setStartingDateMonth(month);
								int age = Math.max(18, Main.game.getPlayer().getAgeValue());
								CharacterModificationUtils.performPlayerAgeCheck(age);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
					
					// Birth day:
					id = "BIRTH_DAY_INCREASE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							int age = Main.game.getPlayer().getAgeValue();
							Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().plusDays(1));
							CharacterModificationUtils.performPlayerAgeCheck(age);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "BIRTH_DAY_INCREASE_LARGE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							int age = Main.game.getPlayer().getAgeValue();
							Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().plusDays(5));
							CharacterModificationUtils.performPlayerAgeCheck(age);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "BIRTH_DAY_DECREASE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							int age = Main.game.getPlayer().getAgeValue();
							Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().minusDays(1));
							CharacterModificationUtils.performPlayerAgeCheck(age);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "BIRTH_DAY_DECREASE_LARGE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							int age = Main.game.getPlayer().getAgeValue();
							Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().minusDays(5));
							CharacterModificationUtils.performPlayerAgeCheck(age);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					// Birth month:
					id = "BIRTH_MONTH_INCREASE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							int age = Main.game.getPlayer().getAgeValue();
							Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().plusMonths(1));
							CharacterModificationUtils.performPlayerAgeCheck(age);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "BIRTH_MONTH_INCREASE_LARGE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							int age = Main.game.getPlayer().getAgeValue();
							Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().plusMonths(5));
							CharacterModificationUtils.performPlayerAgeCheck(age);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "BIRTH_MONTH_DECREASE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							int age = Main.game.getPlayer().getAgeValue();
							Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().minusMonths(1));
							CharacterModificationUtils.performPlayerAgeCheck(age);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "BIRTH_MONTH_DECREASE_LARGE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							int age = Main.game.getPlayer().getAgeValue();
							Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().minusMonths(5));
							CharacterModificationUtils.performPlayerAgeCheck(age);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					// Age:
					id = "AGE_INCREASE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().minusYears(1));
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "AGE_INCREASE_LARGE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().minusYears(5));
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "AGE_DECREASE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().plusYears(1));
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "AGE_DECREASE_LARGE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().plusYears(5));
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
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.URETHRA_PENIS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "FINGERINGS_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "BLOWJOBS_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "CUNNILINGUS_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "VAGINAL_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "ANAL_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					// Received:

					id = "HANDJOBS_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.FINGER), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "FINGERINGS_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "BLOWJOBS_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "CUNNILINGUS_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "VAGINAL_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "ANAL_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), i);
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
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_PENIS)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_BREASTS_CROTCH)) {
				
				for(Entry<String, TooltipInformationEventListener> entry : CharacterModificationUtils.informationTooltips.entrySet()) {
					id = entry.getKey();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", entry.getValue(), false);
					}
				}
				
				
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
				
				
				// Variable femininity:
				
				
				id = "FEMININITY_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementFemininity(1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "FEMININITY_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementFemininity(5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "FEMININITY_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementFemininity(-1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "FEMININITY_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementFemininity(-5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Personality:
				for(PersonalityTrait trait : PersonalityTrait.values()) {
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
				id = "AGE_APPEARANCE_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setAgeAppearanceDifferenceToAppearAsAge(Math.max(18, Math.min(BodyChanging.getTarget().getAppearsAsAgeValue()+1, BodyChanging.getTarget().getAgeValue()+10)));
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "AGE_APPEARANCE_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setAgeAppearanceDifferenceToAppearAsAge(Math.max(18, Math.min(BodyChanging.getTarget().getAppearsAsAgeValue()+5, BodyChanging.getTarget().getAgeValue()+10)));
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "AGE_APPEARANCE_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setAgeAppearanceDifferenceToAppearAsAge(Math.max(18, Math.min(BodyChanging.getTarget().getAppearsAsAgeValue()-1, BodyChanging.getTarget().getAgeValue()+10)));
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "AGE_APPEARANCE_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setAgeAppearanceDifferenceToAppearAsAge(Math.max(18, Math.min(BodyChanging.getTarget().getAppearsAsAgeValue()-5, BodyChanging.getTarget().getAgeValue()+10)));
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				
				// Height:
				id = "HEIGHT_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(1, BodyChanging.isDebugMenu());
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "HEIGHT_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(5, BodyChanging.isDebugMenu());
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "HEIGHT_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(-1, BodyChanging.isDebugMenu());
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "HEIGHT_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(-5, BodyChanging.isDebugMenu());
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

				// Crotch-boob Shape:
				for(BreastShape bs : BreastShape.values()) {
					id = "CROTCH_BOOB_SHAPE_"+bs;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastCrotchShape(bs);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple Shape:
				for(NippleShape ns : NippleShape.values()) {
					id = "NIPPLE_SHAPE_"+ns;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleShape(ns);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				// Crotch-boob nipple shape:
				for(NippleShape ns : NippleShape.values()) {
					id = "NIPPLE_CROTCH_SHAPE_"+ns;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleCrotchShape(ns);
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
					id = "NIPPLE_CROTCH_SIZE_"+ns;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleCrotchSize(ns.getValue());
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
					id = "AREOLAE_CROTCH_SIZE_"+as;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAreolaeCrotchSize(as.getValue());
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
				
				// Vagina elasticity:
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
				
				for(AbstractArmType armType: ArmType.getAllArmTypes()) {
					id = "CHANGE_ARM_"+ArmType.getIdFromArmType(armType);
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
				
				for(AbstractEarType earType: EarType.getAllEarTypes()) {
					id = "CHANGE_EAR_"+EarType.getIdFromEarType(earType);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setEarType(earType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(AbstractHornType hornType: HornType.getAllHornTypes()) {
					id = "CHANGE_HORN_"+HornType.getIdFromHornType(hornType);
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
				
				
				for(AbstractLegType legType: LegType.getAllLegTypes()) {
					id = "CHANGE_LEG_"+LegType.getIdFromLegType(legType);
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
				
				for(GenitalArrangement genitalArrangement: GenitalArrangement.values()) {
					id = "CHANGE_GENITAL_ARRANGEMENT_"+genitalArrangement;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setGenitalArrangement(genitalArrangement);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(LegConfiguration legConfig: LegConfiguration.values()) {
					id = "CHANGE_LEG_CONFIGURATION_"+legConfig;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().getLegType().applyLegConfigurationTransformation(BodyChanging.getTarget(), legConfig, true, false);
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

				for(AbstractAssType assType: AssType.getAllAssTypes()) {
					id = "CHANGE_ASS_"+AssType.getIdFromAssType(assType);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssType(assType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(AbstractBreastType breastType: BreastType.getAllBreastTypes()) {
					id = "CHANGE_BREAST_"+BreastType.getIdFromBreastType(breastType);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastType(breastType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "CHANGE_BREAST_CROTCH_"+BreastType.getIdFromBreastType(breastType);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastCrotchType(breastType);
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
				
				// Arms:

				for(int i=1; i <= Arm.MAXIMUM_ROWS; i++) {
					MainController.setArmCountListener(i);
				}
				
				// Legs:

				for(FootStructure footStructure : FootStructure.values()) {
					id = "CHANGE_FOOT_STRUCTURE_"+footStructure;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setFootStructure(footStructure);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Eyes:

				for(int i=1; i <= Eye.MAXIMUM_PAIRS; i++) {
					MainController.setEyeCountListener(i);
				}

				// Horns:

				for(int i=1; i <= Horn.MAXIMUM_ROWS; i++) {
					MainController.setHornCountListener(i);
				}

				for(int i=1; i <= Horn.MAXIMUM_HORNS_PER_ROW; i++) {
					MainController.setHornsPerRowCountListener(i);
				}
				

				// Tails:

				for(int i=1; i <= Tail.MAXIMUM_COUNT; i++) {
					MainController.setTailCountListener(i);
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
				
				id = "BREAST_SIZE_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastSize(1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "BREAST_SIZE_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastSize(5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "BREAST_SIZE_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastSize(-1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "BREAST_SIZE_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastSize(-5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				

				id = "CROTCH_BOOB_SIZE_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastCrotchSize(1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CROTCH_BOOB_SIZE_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastCrotchSize(5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CROTCH_BOOB_SIZE_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastCrotchSize(-1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CROTCH_BOOB_SIZE_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastCrotchSize(-5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				
				for(int i=1; i <= Breast.MAXIMUM_BREAST_ROWS; i++) {
					MainController.setBreastCountListener(i);
				}
				for(int i=1; i <= BreastCrotch.MAXIMUM_BREAST_ROWS; i++) {
					MainController.setBreastCrotchCountListener(i);
				}
				
				for(int i=1; i <= Breast.MAXIMUM_NIPPLES_PER_BREAST; i++) {
					MainController.setNippleCountListener(i);
				}
				for(int i=1; i <= BreastCrotch.MAXIMUM_NIPPLES_PER_BREAST; i++) {
					MainController.setNippleCrotchCountListener(i);
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
					id = "NIPPLE_CROTCH_CAPACITY_"+capacity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleCrotchCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple elasticity:
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "NIPPLE_ELASTICITY_"+elasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "NIPPLE_CROTCH_ELASTICITY_"+elasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleCrotchElasticity(elasticity.getValue());
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
					id = "NIPPLE_CROTCH_PLASTICITY_"+plasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleCrotchPlasticity(plasticity.getValue());
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
					id = "CHANGE_NIPPLE_CROTCH_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasNippleCrotchOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeNippleCrotchOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addNippleCrotchOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(FluidFlavour flavour : FluidFlavour.values()) {
					id = "MILK_FLAVOUR_"+flavour;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setMilkFlavour(flavour);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(FluidFlavour flavour : FluidFlavour.values()) {
					id = "MILK_CROTCH_FLAVOUR_"+flavour;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setMilkCrotchFlavour(flavour);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				id = "MILK_PRODUCTION_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBreastMilkStorage(Math.min(CharacterModificationUtils.getLactationUpperLimit(), BodyChanging.getTarget().getBreastRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_SMALL));
						BodyChanging.getTarget().fillMilkToMaxStorage();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_PRODUCTION_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBreastMilkStorage(Math.min(CharacterModificationUtils.getLactationUpperLimit(), BodyChanging.getTarget().getBreastRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_AVERAGE));
						BodyChanging.getTarget().fillMilkToMaxStorage();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_PRODUCTION_INCREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBreastMilkStorage(Math.min(CharacterModificationUtils.getLactationUpperLimit(), BodyChanging.getTarget().getBreastRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_LARGE));
						BodyChanging.getTarget().fillMilkToMaxStorage();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_PRODUCTION_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_SMALL);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_PRODUCTION_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_AVERAGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_PRODUCTION_DECREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_LARGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				
				id = "MILK_CROTCH_PRODUCTION_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBreastCrotchMilkStorage(
								Math.min(CharacterModificationUtils.getLactationCrotchUpperLimit(), BodyChanging.getTarget().getBreastCrotchRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_SMALL));
						BodyChanging.getTarget().fillMilkCrotchToMaxStorage();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_PRODUCTION_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBreastCrotchMilkStorage(
								Math.min(CharacterModificationUtils.getLactationCrotchUpperLimit(), BodyChanging.getTarget().getBreastCrotchRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_AVERAGE));
						BodyChanging.getTarget().fillMilkCrotchToMaxStorage();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_PRODUCTION_INCREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBreastCrotchMilkStorage(
								Math.min(CharacterModificationUtils.getLactationCrotchUpperLimit(), BodyChanging.getTarget().getBreastCrotchRawMilkStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_LARGE));
						BodyChanging.getTarget().fillMilkCrotchToMaxStorage();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_PRODUCTION_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastCrotchMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_SMALL);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_PRODUCTION_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastCrotchMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_AVERAGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_PRODUCTION_DECREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastCrotchMilkStorage(-CharacterModificationUtils.FLUID_INCREMENT_LARGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				
				id = "MILK_REGENERATION_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_REGENERATION_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_REGENERATION_INCREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_REGENERATION_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_REGENERATION_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_REGENERATION_DECREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				
				id = "MILK_CROTCH_REGENERATION_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_REGENERATION_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_REGENERATION_INCREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_REGENERATION_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_REGENERATION_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_REGENERATION_DECREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
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
				
				for(FluidFlavour flavour : FluidFlavour.values()) {
					id = "GIRLCUM_FLAVOUR_"+flavour;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setGirlcumFlavour(flavour);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(Capacity capacity: Capacity.values()) {
					id = "VAGINA_URETHRA_CAPACITY_"+capacity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaUrethraCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "VAGINA_URETHRA_ELASTICITY_"+elasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaUrethraElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "VAGINA_URETHRA_PLASTICITY_"+plasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaUrethraPlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_VAGINA_URETHRA_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasVaginaUrethraOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeVaginaUrethraOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addVaginaUrethraOrificeModifier(orificeMod);
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
				
				for(FluidFlavour flavour : FluidFlavour.values()) {
					id = "CUM_FLAVOUR_"+flavour;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setCumFlavour(flavour);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				id = "CUM_PRODUCTION_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setPenisCumStorage(Math.min(CharacterModificationUtils.getCumUpperLimit(), BodyChanging.getTarget().getPenisRawCumStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_SMALL));
						BodyChanging.getTarget().fillCumToMaxStorage();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setPenisCumStorage(Math.min(CharacterModificationUtils.getCumUpperLimit(), BodyChanging.getTarget().getPenisRawCumStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_AVERAGE));
						BodyChanging.getTarget().fillCumToMaxStorage();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_INCREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setPenisCumStorage(Math.min(CharacterModificationUtils.getCumUpperLimit(), BodyChanging.getTarget().getPenisRawCumStorageValue()+CharacterModificationUtils.FLUID_INCREMENT_LARGE));
						BodyChanging.getTarget().fillCumToMaxStorage();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumStorage(-CharacterModificationUtils.FLUID_INCREMENT_SMALL);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumStorage(-CharacterModificationUtils.FLUID_INCREMENT_AVERAGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_DECREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumStorage(-CharacterModificationUtils.FLUID_INCREMENT_LARGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				

				id = "CUM_REGENERATION_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumProductionRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_REGENERATION_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumProductionRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_REGENERATION_INCREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumProductionRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_REGENERATION_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumProductionRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_REGENERATION_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumProductionRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_REGENERATION_DECREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumProductionRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}

				
				id = "CUM_EXPULSION_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumExpulsion(1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_EXPULSION_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumExpulsion(10);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_EXPULSION_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumExpulsion(-1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_EXPULSION_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumExpulsion(-10);
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
				
				for(PenetrationModifier orificeMod : PenetrationModifier.values()) {
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
			
			if(Main.game.getCurrentDialogueNode()==SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS
					|| Main.game.getCurrentDialogueNode()==OccupantManagementDialogue.SLAVE_MANAGEMENT_TATTOOS
					|| Main.game.getCurrentDialogueNode()==CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS) {
				for(InventorySlot invSlot : InventorySlot.getClothingSlots()) {
					id = "TATTOO_INFO_"+invSlot.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						
						if(BodyChanging.getTarget().getTattooInSlot(invSlot)!=null) {
							TooltipInventoryEventListener el = new TooltipInventoryEventListener().setTattoo(invSlot, BodyChanging.getTarget().getTattooInSlot(invSlot), BodyChanging.getTarget(), BodyChanging.getTarget());
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						} else {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(invSlot.getTattooSlotName()), "");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}
					
					id = "TATTOO_ADD_REMOVE_"+invSlot.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						if(BodyChanging.getTarget().getTattooInSlot(invSlot)==null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.setContent(new Response("", "",
										Main.game.isInNewWorld()
											?(Main.game.getCurrentDialogueNode()==SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS
												?SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS_ADD
												:OccupantManagementDialogue.SLAVE_MANAGEMENT_TATTOOS_ADD)
											:CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD){
									@Override
									public void effects() {
										SuccubisSecrets.invSlotTattooToRemove = null;
										CharacterModificationUtils.resetTattooVariables(invSlot);
									}
								});
							}, false);
						
						} else {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								if(Main.game.getPlayer().getMoney()>=100 || !Main.game.isInNewWorld()) {
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
										@Override
										public void effects() {
											if(SuccubisSecrets.invSlotTattooToRemove == invSlot || !Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)) {
												SuccubisSecrets.invSlotTattooToRemove = null;
												if(Main.game.isInNewWorld()) {
													Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-100)); //TODO Kate description
												}
												BodyChanging.getTarget().removeTattoo(invSlot);
											} else {
												SuccubisSecrets.invSlotTattooToRemove = invSlot;
											}
										}
									});
								}
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove tattoo",
									Main.game.isInNewWorld()
									?(Main.game.getPlayer().getMoney()>=100
										?"It will cost "+UtilText.formatAsMoney(100, "span")+" to remove this tattoo!"
												+ (!Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)?" (<i>You will need to click twice to remove it.</i>)":"")
										:"You don't have the required "+UtilText.formatAsMoney(100, "span")+" to remove this tattoo!")
									:"Remove this tattoo. "+(!Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)?" (<i>You will need to click twice to remove it.</i>)":""));
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}
					
					id = "TATTOO_ENCHANT_"+invSlot.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", EnchantmentDialogue.ENCHANTMENT_MENU){
								@Override
								public DialogueNode getNextDialogue() {
									return EnchantmentDialogue.getEnchantmentMenu(BodyChanging.getTarget().getTattooInSlot(invSlot), BodyChanging.getTarget(), invSlot);
								}
							});
						}, false);
					}
				}
			}

			if(Main.game.getCurrentDialogueNode()==SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS_ADD
					|| Main.game.getCurrentDialogueNode()==OccupantManagementDialogue.SLAVE_MANAGEMENT_TATTOOS_ADD
					|| Main.game.getCurrentDialogueNode()==CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD) {
				id = "NEW_TATTOO_INFO";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					
					TooltipInventoryEventListener el = new TooltipInventoryEventListener().setTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo, BodyChanging.getTarget(), BodyChanging.getTarget());
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
				
				for(AbstractTattooType type : TattooType.getAllTattooTypes()) {
					id = "TATTOO_TYPE_"+type.getId();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						if(type.getSlotAvailability().contains(CharacterModificationUtils.tattooInventorySlot)) {
							if(!CharacterModificationUtils.tattoo.getType().equals(type)) {
								((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
										@Override
										public void effects() {
											Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
											CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
											CharacterModificationUtils.tattoo.setType(type);
											CharacterModificationUtils.resetTattooColours();
										}
									});
								}, false);
							}
						}
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(type.getName()), type.getDescription()
								+(type.getSlotAvailability().contains(CharacterModificationUtils.tattooInventorySlot)
										?""
										:"<br/>[style.italicsBad(This tattoo type can't be applied to '"+CharacterModificationUtils.tattooInventorySlot.getTattooSlotName()+"'!)]<br/>"
												+ "Available slots: "+Util.tattooInventorySlotsToStringList(type.getSlotAvailability())));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
				
				for(Colour c : CharacterModificationUtils.tattoo.getType().getAvailablePrimaryColours()) {
					id = "TATTOO_COLOUR_PRIMARY_"+c.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.setPrimaryColour(c);
								}
							});
						}, false);
					}
				}
				
				for(Colour c : CharacterModificationUtils.tattoo.getType().getAvailableSecondaryColours()) {
					id = "TATTOO_COLOUR_SECONDARY_"+c.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.setSecondaryColour(c);
								}
							});
						}, false);
					}
				}
				
				for(Colour c : CharacterModificationUtils.tattoo.getType().getAvailableTertiaryColours()) {
					id = "TATTOO_COLOUR_TERTIARY_"+c.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.setTertiaryColour(c);
								}
							});
						}, false);
					}
				}
				
				id = "TATTOO_GLOW";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
								CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
								CharacterModificationUtils.tattoo.setGlowing(!CharacterModificationUtils.tattoo.isGlowing());
							}
						});
					}, false);
				}
				
				// Writing:

				for(TattooWritingStyle style : TattooWritingStyle.values()) {
					id = "TATTOO_WRITING_STYLE_"+style.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									if(!CharacterModificationUtils.tattoo.getWriting().getStyles().contains(style)) {
										CharacterModificationUtils.tattoo.getWriting().addStyle(style);
									} else {
										CharacterModificationUtils.tattoo.getWriting().removeStyle(style);
									}
								}
							});
						}, false);
					}
				}

				for(Colour c : TattooWriting.getAvailableColours()) {
					id = "TATTOO_WRITING_COLOUR_"+c.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.getWriting().setColour(c);
								}
							});
						}, false);
					}
				}
				
				id = "TATTOO_WRITING_GLOW";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
								CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
								CharacterModificationUtils.tattoo.getWriting().setGlow(!CharacterModificationUtils.tattoo.getWriting().isGlow());
							}
						});
					}, false);
				}
				
				// Counter:

				for(TattooCounterType counterType : TattooCounterType.values()) {
					id = "TATTOO_COUNTER_TYPE_"+counterType.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.getCounter().setType(counterType);
								}
							});
						}, false);

						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(counterType.getName()), counterType.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}

				for(TattooCountType countType : TattooCountType.values()) {
					id = "TATTOO_COUNT_TYPE_"+countType.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.getCounter().setCountType(countType);
								}
							});
						}, false);
					}
				}

				for(Colour c : TattooCounter.getAvailableColours()) {
					id = "TATTOO_COUNTER_COLOUR_"+c.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.getCounter().setColour(c);
								}
							});
						}, false);
					}
				}
				
				id = "TATTOO_COUNTER_GLOW";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
								CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
								CharacterModificationUtils.tattoo.getCounter().setGlow(!CharacterModificationUtils.tattoo.getCounter().isGlow());
							}
						});
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
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setDyeClothingPrimary(InventoryDialogue.getClothing(), c);
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
							TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setDyeClothingSecondary(InventoryDialogue.getClothing(), c);
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
							TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setDyeClothingTertiary(InventoryDialogue.getClothing(), c);
							MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
						}
					}
				}
				for(Pattern pattern : Pattern.getAllPatterns().values()) {
					id = "ITEM_PATTERN_"+pattern.getName();
					
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(InventoryDialogue.dyePreviewPattern != pattern.getName()){
								InventoryDialogue.dyePreviewPattern = pattern.getName();
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
						
						// For some reason tooltips here cause massive lag.
						/*if(InventoryDialogue.dyePreviewPattern != pattern.getName())
						{
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeClothingPattern(InventoryDialogue.getClothing(), pattern);
							MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
						}*/
					}
				}
				if(Pattern.getPattern(InventoryDialogue.dyePreviewPattern)!=null && Pattern.getPattern(InventoryDialogue.dyePreviewPattern).isPrimaryRecolourAvailable()) {
					for (Colour c : ColourListPresets.ALL) {
						id = "PATTERN_PRIMARY_"+clothing.hashCode() + "_" + c.toString();
						if ((EventTarget) MainController.document.getElementById(id) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								InventoryDialogue.dyePreviewPatternPrimary = c;
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
				}
				if(Pattern.getPattern(InventoryDialogue.dyePreviewPattern)!=null && Pattern.getPattern(InventoryDialogue.dyePreviewPattern).isSecondaryRecolourAvailable()) {
					for (Colour c : ColourListPresets.ALL) {
						id = "PATTERN_SECONDARY_"+clothing.hashCode() + "_" + c.toString();
						if ((EventTarget) MainController.document.getElementById(id) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								InventoryDialogue.dyePreviewPatternSecondary = c;
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
				}
				if(Pattern.getPattern(InventoryDialogue.dyePreviewPattern)!=null && Pattern.getPattern(InventoryDialogue.dyePreviewPattern).isTertiaryRecolourAvailable()) {
					for (Colour c : ColourListPresets.ALL) {
						id = "PATTERN_TERTIARY_"+clothing.hashCode() + "_" + c.toString();
						if ((EventTarget) MainController.document.getElementById(id) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								InventoryDialogue.dyePreviewPatternTertiary = c;
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
				}
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
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setDyeWeaponPrimary(InventoryDialogue.getWeapon(), c);
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
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setDyeWeaponSecondary(InventoryDialogue.getWeapon(), c);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				for (DamageType dt : weapon.getAvailableDamageTypes()) {
					id = "DAMAGE_TYPE_"+weapon.hashCode() + "_" + dt.toString();
					if ((EventTarget) MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							InventoryDialogue.damageTypePreview = dt;
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setDamageTypeWeapon(InventoryDialogue.getWeapon(), dt);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
			}
			
			for (AbstractClothingType clothing : ClothingType.getAllClothing()) {
				for (Colour colour : clothing.getAllAvailablePrimaryColours()) {
					if ((EventTarget) MainController.document.getElementById(clothing.hashCode() + "_" + colour.toString()) != null) {
						MainController.addEventListener(MainController.document, clothing.hashCode() + "_" + colour.toString(), "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, clothing.hashCode() + "_" + colour.toString(), "mouseleave", MainController.hideTooltipListener, false);
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setGenericClothing(clothing, colour);
						MainController.addEventListener(MainController.document, clothing.hashCode() + "_" + colour.toString(), "mouseenter", el2, false);
					}
				}
			}
			
			for (AbstractItemType item : ItemType.getAllItems()) {
				id = ItemType.getItemToIdMap().get(item);
				if ((EventTarget) MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setGenericItem(item);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			
			for (AbstractWeaponType weapon : WeaponType.getAllWeapons()) {
				for (DamageType dt : weapon.getAvailableDamageTypes()) {
					if ((EventTarget) MainController.document.getElementById(weapon.hashCode() + "_" + dt.toString()) != null) {
						MainController.addEventListener(MainController.document, weapon.hashCode() + "_" + dt.toString(), "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, weapon.hashCode() + "_" + dt.toString(), "mouseleave", MainController.hideTooltipListener, false);
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setGenericWeapon(weapon, dt);
						MainController.addEventListener(MainController.document, weapon.hashCode() + "_" + dt.toString(), "mouseenter", el2, false);
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

			
			for(AbstractPerk perk : Perk.getAllPerks()) {
				GameCharacter character =
						(Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_PERK_TREE || Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_APPEARANCE)
							?Main.game.getPlayer()
							:(Main.game.getCurrentDialogueNode() == OccupantManagementDialogue.SLAVE_MANAGEMENT_PERKS
								?OccupantManagementDialogue.characterSelected()
								:CharactersPresentDialogue.characterViewed);

				boolean availableForSelection =
						Main.game.getCurrentDialogueNode() != PhoneDialogue.CONTACTS_CHARACTER
						&& Main.game.getCurrentDialogueNode() != PhoneDialogue.CHARACTER_APPEARANCE
						&& Main.game.getCurrentDialogueNode() != CharactersPresentDialogue.MENU;
						
				if(perk.getPerkCategory() == PerkCategory.JOB) {
					id = "OCCUPATION_"+Perk.getIdFromPerk(perk);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setLevelUpPerk(0, perk, character, availableForSelection), false);
					}
					
				} else {
					id = "TRAIT_"+Perk.getIdFromPerk(perk);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setLevelUpPerk(PerkManager.MANAGER.getPerkRow(character, perk), perk, character, availableForSelection), false);

						
						if(availableForSelection) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event -> {
								character.removeTrait(perk);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
				}
				if(perk.isHiddenPerk()) {
					id = "HIDDEN_PERK_"+Perk.getIdFromPerk(perk);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						if(character.hasPerkAnywhereInTree(perk)) {
							MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setLevelUpPerk(0, perk, character, false), false);
						} else {
							MainController.addEventListener(MainController.document, id, "mouseenter",
									new TooltipInformationEventListener().setInformation("Unknown!",
											"This is an undiscovered hidden perk, and as such, you have no idea what it could be!<br/><i>Hidden perks are discovered through the main quest.</i>"), false);
						}
					}
				}
			}


			for(CombatMove move : CombatMove.getAllCombatMoves()) {
				GameCharacter character = CombatMovesSetup.getTarget();

				id = "MOVE_"+move.getIdentifier();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setCombatMove(move, character), false);

					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event -> {
						if(character.getEquippedMoves().contains(move)) {
							character.unequipMove(move.getIdentifier());
							
						} else if(character.getEquippedMoves().size() < GameCharacter.MAX_COMBAT_MOVES) {
							character.equipMove(move.getIdentifier());
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			
			// Level up dialogue:
			if (Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_PERK_TREE
					|| Main.game.getCurrentDialogueNode() == OccupantManagementDialogue.SLAVE_MANAGEMENT_PERKS
					|| Main.game.getCurrentDialogueNode() == CharactersPresentDialogue.PERKS
					|| Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_APPEARANCE
					|| Main.game.getCurrentDialogueNode() == CharactersPresentDialogue.MENU
					|| Main.game.getCurrentDialogueNode() == PhoneDialogue.CONTACTS_CHARACTER) {
				
				GameCharacter character = Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_PERK_TREE
						?Main.game.getPlayer()
						:(Main.game.getCurrentDialogueNode() == OccupantManagementDialogue.SLAVE_MANAGEMENT_PERKS
							?OccupantManagementDialogue.characterSelected()
							:CharactersPresentDialogue.characterViewed);
						
				for(int i = 0; i<PerkManager.ROWS; i++) {
					for(Entry<PerkCategory, List<TreeEntry<PerkCategory, AbstractPerk>>> entry : PerkManager.MANAGER.getPerkTree(character).get(i).entrySet()) {
						for(TreeEntry<PerkCategory, AbstractPerk> e : entry.getValue()) {
							id = i+"_"+e.getCategory()+"_"+Perk.getIdFromPerk(e.getEntry());
	
							if (((EventTarget) MainController.document.getElementById(id)) != null) {
								boolean availableForSelection =
										Main.game.getCurrentDialogueNode() != PhoneDialogue.CONTACTS_CHARACTER
										&& Main.game.getCurrentDialogueNode() != PhoneDialogue.CHARACTER_APPEARANCE
										&& Main.game.getCurrentDialogueNode() != CharactersPresentDialogue.MENU;
								MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setLevelUpPerk(i, e.getEntry(), character, availableForSelection), false);
								// These two dialogues are just for viewing, not modifying:
								if(availableForSelection) {
									((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event -> {
										if(e.getEntry().isEquippableTrait() && PerkManager.MANAGER.isPerkOwned(character, e)) {
											if(!character.hasTraitActivated(e.getEntry())) {
												character.addTrait(e.getEntry());
											} else {
												character.removeTrait(e.getEntry());
											}
											Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
											
										} else if(character.getPerkPoints()>=1 && PerkManager.MANAGER.isPerkAvailable(character, e)) {
											if(character.addPerk(e.getRow(), e.getEntry())) {
												if(e.getEntry().isEquippableTrait() && character.getTraits().size()<GameCharacter.MAX_TRAITS) {
													character.addTrait(e.getEntry());
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
			}
			if (Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_FETISHES) {
				for (Fetish f : Fetish.values()) {
					id = "fetishUnlock" + f;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE)>=f.getCost()) {
								if(!Main.game.getPlayer().hasFetish(f)) {
									Main.game.getPlayer().addFetish(f);
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
									if(Main.game.getPlayer().getBaseFetishDesire(f)!=desire) {
										Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -FetishDesire.getCostToChange(), false);
										Main.game.getPlayer().setFetishDesire(f, desire);
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
			
			if (
//					Main.game.getCurrentDialogueNode().equals(PhoneDialogue.MENU) ||
					Main.game.getCurrentDialogueNode().equals(Library.DOMINION_MAP)) {
				Cell[][] grid;
//				if(Main.game.getCurrentDialogueNode().equals(PhoneDialogue.MENU)) {
//					grid = Main.game.getWorlds().get(Main.game.getPlayer().getWorldLocation()).getGrid();
//				} else {
					grid = Main.game.getWorlds().get(WorldType.DOMINION).getGrid();
//				}

				for(int i=grid[0].length-1; i>=0; i--) {
					for(int j=0; j<grid.length; j++) {
						Cell c = grid[j][i];
						if(Main.game.isInGlobalMap()) {
							MainController.setMapLocationListeners(c, i, j);
							
						} else {
							boolean discovered = c.isDiscovered() || Main.game.isMapReveal();
							if(!discovered) {
								continue;
							}
							if(c.getPlace().getPlaceType().equals(PlaceType.GENERIC_IMPASSABLE)) {
								continue;
							}
							MainController.setMapLocationListeners(c, i, j);
						}
					}
				}
				
			}
			
			if (Main.game.getCurrentDialogueNode().equals(PhoneDialogue.MAP)) {
				WorldType worldType = PhoneDialogue.worldTypeMap;
				Cell[][] grid = Main.game.getWorlds().get(worldType).getGrid();

				for(int i=grid[0].length-1; i>=0; i--) {
					for(int j=0; j<grid.length; j++) {
						Cell c = grid[j][i];
						if(PhoneDialogue.worldTypeMap.equals(WorldType.WORLD_MAP)) {
							MainController.setMapLocationListeners(c, i, j);
							
						} else {
							boolean discovered = c.isDiscovered() || Main.game.isMapReveal();
							if(!discovered) {
								continue;
							}
							if(c.getPlace().getPlaceType().equals(PlaceType.GENERIC_IMPASSABLE)) {
								continue;
							}
							MainController.setMapLocationListeners(c, i, j);
						}
					}
				}

				for(MapTravelType type : MapTravelType.values()) {
					id = type.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						if(type.isAvailable(Main.game.getPlayer())) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Pathing.initPathingVariables();
								Pathing.setMapTravelType(type);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter",
								new TooltipInformationEventListener().setInformation(
										type.getName(),
										type.getDescription()
											+(type.isAvailable(Main.game.getPlayer())
													?" ("+type.getUseInstructions()+")"
													:"<br/>[style.italicsBad("+type.getUnavailablilityDescription(Main.game.getPlayer())+")]")),
								false);
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
				for(ContentPreferenceValue preference : ContentPreferenceValue.values()) {
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

		// Sexual orientation preferences:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.ORIENTATION_PREFERENCE) {
			for (SexualOrientation o : SexualOrientation.values()) {
				for(SexualOrientationPreference preference : SexualOrientationPreference.values()) {
					if (((EventTarget) MainController.document.getElementById(preference+"_"+o)) != null) {
						((EventTarget) MainController.document.getElementById(preference+"_"+o)).addEventListener("click", e -> {
							Main.getProperties().orientationPreferencesMap.put(o, preference.getValue());
							Main.saveProperties();
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
			}
		}
		
		// Age preferences:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.AGE_PREFERENCE) {
			for (AgeCategory ageCat : AgeCategory.values()) {
				for(PronounType pronoun : PronounType.values()) {
					for(ContentPreferenceValue preference : ContentPreferenceValue.values()) {
						if (((EventTarget) MainController.document.getElementById(pronoun+"_"+preference+"_"+ageCat)) != null) {
							((EventTarget) MainController.document.getElementById(pronoun+"_"+preference+"_"+ageCat)).addEventListener("click", e -> {
								Main.getProperties().agePreferencesMap.get(pronoun).put(ageCat, preference.getValue());
								Main.saveProperties();
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
				}
			}
		}
		
		// Furry preferences:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.FURRY_PREFERENCE) {
			
			for(Subspecies s : Subspecies.values()) {
				id="SUBSPECIES_PREFERNCE_INFO_"+s;

				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(s.getName(null)), s.getDescription(null));
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
			
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
			
			// Taur furry spawns:
			if (((EventTarget) MainController.document.getElementById("taur_furry_preference_zero")) != null) {
				((EventTarget) MainController.document.getElementById("taur_furry_preference_zero")).addEventListener("click", e -> {
					Main.getProperties().taurFurryLevel=0;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, "taur_furry_preference_zero", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "taur_furry_preference_zero", "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Disabled",
						"The only randomly generated NPCs to be spawned as taurs will be those that are of a taur-specific race (and as such, you can set their spawn rate in the detailed menu below).");
				MainController.addEventListener(MainController.document, "taur_furry_preference_zero", "mouseenter", el, false);
			}
			if (((EventTarget) MainController.document.getElementById("taur_furry_preference_one")) != null) {
				((EventTarget) MainController.document.getElementById("taur_furry_preference_one")).addEventListener("click", e -> {
					Main.getProperties().taurFurryLevel=1;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, "taur_furry_preference_one", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "taur_furry_preference_one", "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Normal",
						"There will be a 5% chance for any randomly spawned NPC to be a taur, and they will always have a human upper-body.");
				MainController.addEventListener(MainController.document, "taur_furry_preference_one", "mouseenter", el, false);
			}
			if (((EventTarget) MainController.document.getElementById("taur_furry_preference_two")) != null) {
				((EventTarget) MainController.document.getElementById("taur_furry_preference_two")).addEventListener("click", e -> {
					Main.getProperties().taurFurryLevel=2;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, "taur_furry_preference_two", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "taur_furry_preference_two", "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Lesser",
						"There will be a 5% chance for any randomly spawned NPC to be a taur, and they will always have the upper-body of a lesser morph (so just eyes, ears, horns will be non-human).");
				MainController.addEventListener(MainController.document, "taur_furry_preference_two", "mouseenter", el, false);
			}
			if (((EventTarget) MainController.document.getElementById("taur_furry_preference_three")) != null) {
				((EventTarget) MainController.document.getElementById("taur_furry_preference_three")).addEventListener("click", e -> {
					Main.getProperties().taurFurryLevel=3;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, "taur_furry_preference_three", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "taur_furry_preference_three", "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Untouched Conversion",
						"There will be a 5% chance for any randomly spawned NPC to be a taur, and their upper body's furriness will be based on your furry preferences below.");
				MainController.addEventListener(MainController.document, "taur_furry_preference_three", "mouseenter", el, false);
			}
			if (((EventTarget) MainController.document.getElementById("taur_furry_preference_four")) != null) {
				((EventTarget) MainController.document.getElementById("taur_furry_preference_four")).addEventListener("click", e -> {
					Main.getProperties().taurFurryLevel=4;
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, "taur_furry_preference_four", "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, "taur_furry_preference_four", "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Always Greater",
						"There will be a 5% chance for any randomly spawned NPC to be a taur, and their upper body will always be fully furry.");
				MainController.addEventListener(MainController.document, "taur_furry_preference_four", "mouseenter", el, false);
			}
			
			// Forced TF racial limits:
			id = "forced_tf_limit_human";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setForcedTFPreference(FurryPreference.HUMAN);
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
					Main.getProperties().setForcedTFPreference(FurryPreference.MINIMUM);
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
					Main.getProperties().setForcedTFPreference(FurryPreference.REDUCED);
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
					Main.getProperties().setForcedTFPreference(FurryPreference.NORMAL);
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
					Main.getProperties().setForcedTFPreference(FurryPreference.MAXIMUM);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Always Greater Furry",
						"Forced transformations from NPCs will always give you non-human hair, ears, eyes, tails, horns, antenna, wings, breasts, genitalia, ass, arms, legs, skin, and face. (So everything will be affected.)");
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			
			
			// Race preferences:
			if (((EventTarget) MainController.document.getElementById("furry_preference_human_all")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_human_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().setFeminineFurryPreference(r, FurryPreference.HUMAN);
						Main.getProperties().setMasculineFurryPreference(r, FurryPreference.HUMAN);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_minimum_all")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_minimum_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().setFeminineFurryPreference(r, FurryPreference.MINIMUM);
						Main.getProperties().setMasculineFurryPreference(r, FurryPreference.MINIMUM);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_reduced_all")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_reduced_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().setFeminineFurryPreference(r, FurryPreference.REDUCED);
						Main.getProperties().setMasculineFurryPreference(r, FurryPreference.REDUCED);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_normal_all")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_normal_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().setFeminineFurryPreference(r, FurryPreference.NORMAL);
						Main.getProperties().setMasculineFurryPreference(r, FurryPreference.NORMAL);
					}
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			if (((EventTarget) MainController.document.getElementById("furry_preference_maximum_all")) != null) {
				((EventTarget) MainController.document.getElementById("furry_preference_maximum_all")).addEventListener("click", e -> {
					for (Subspecies r : Subspecies.values()) {
						Main.getProperties().setFeminineFurryPreference(r, FurryPreference.MAXIMUM);
						Main.getProperties().setMasculineFurryPreference(r, FurryPreference.MAXIMUM);
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
							Main.getProperties().setFeminineFurryPreference(s, preference);
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
							Main.getProperties().setMasculineFurryPreference(s, preference);
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

		// Unit preferences:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.UNIT_PREFERENCE) {
			createToggleListener("AUTO_LOCALE_ON", PropertyValue.autoLocale, true, () -> {
				Units.FORMATTER.updateSettings();
				Units.FORMATTER.updateFormats(true);
			});
			createToggleListener("AUTO_LOCALE_OFF", PropertyValue.autoLocale, false, () -> {
				Units.FORMATTER.updateSettings();
				Units.FORMATTER.updateFormats(false);

			});
			createToggleListener("METRIC_SIZES_ON", PropertyValue.metricSizes, true, MainController::overrideAutoLocale);
			createToggleListener("METRIC_SIZES_OFF", PropertyValue.metricSizes, false, MainController::overrideAutoLocale);
			createToggleListener("METRIC_FLUIDS_ON", PropertyValue.metricFluids, true, MainController::overrideAutoLocale);
			createToggleListener("METRIC_FLUIDS_OFF", PropertyValue.metricFluids, false, MainController::overrideAutoLocale);
			createToggleListener("METRIC_WEIGHTS_ON", PropertyValue.metricWeights, true, MainController::overrideAutoLocale);
			createToggleListener("METRIC_WEIGHTS_OFF", PropertyValue.metricWeights, false, MainController::overrideAutoLocale);

			Runnable updater = () -> {
				MainController.overrideAutoLocale();
				Units.FORMATTER.updateTimeFormat(false);
			};
			createToggleListener("TWENTYFOUR_HOUR_TIME_ON", PropertyValue.twentyFourHourTime, true, updater);
			createToggleListener("TWENTYFOUR_HOUR_TIME_OFF", PropertyValue.twentyFourHourTime, false, updater);

			updater = () -> {
				MainController.overrideAutoLocale();
				Units.FORMATTER.updateDateFormat(false);
			};
			createToggleListener("INTERNATIONAL_DATE_ON", PropertyValue.internationalDate, true, updater);
			createToggleListener("INTERNATIONAL_DATE_OFF", PropertyValue.internationalDate, false, updater);
		}
		
		// Content preferences:

		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.CONTENT_PREFERENCE
				|| Main.game.getCurrentDialogueNode() == CharacterCreation.CONTENT_PREFERENCES
				|| Main.game.getCurrentDialogueNode() == OptionsDialogue.OPTIONS) {
			
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

			// Multi-breast options:
			for(int i=0; i<3; i++) {
				id = "MULTI_BREAST_PREFERENCE_"+i;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					setMultiBreastPreference(id, i);
				}
				id = "UDDER_PREFERENCE_"+i;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					setUdderPreference(id, i);
				}
			}
			
			// Auto-save options:
			for(int i=0; i<3; i++) {
				id = "AUTOSAVE_FREQUENCY_"+i;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					setAutosavePreference(id, i);
				}
			}
			
			Map<String, PropertyValue> settingsMap = Util.newHashMapOfValues(
					new Value<>("ENCHANTMENT_LIMITS", PropertyValue.enchantmentLimits),
					new Value<>("ARTWORK", PropertyValue.artwork),
					new Value<>("THUMBNAIL", PropertyValue.thumbnail),
					new Value<>("SILLY", PropertyValue.sillyMode),
					new Value<>("AUTO_SEX_CLOTHING_MANAGEMENT", PropertyValue.autoSexClothingManagement),
					new Value<>("NON_CON", PropertyValue.nonConContent),
					new Value<>("SADISTIC_SEX", PropertyValue.sadisticSexContent),
					new Value<>("VOLUNTARY_NTR", PropertyValue.voluntaryNTR),
					new Value<>("INVOLUNTARY_NTR", PropertyValue.involuntaryNTR),
					new Value<>("INCEST", PropertyValue.incestContent),
					new Value<>("LACTATION", PropertyValue.lactationContent),
					new Value<>("CUM_REGENERATION", PropertyValue.cumRegenerationContent),
					new Value<>("URETHRAL", PropertyValue.urethralContent),
					new Value<>("NIPPLE_PEN", PropertyValue.nipplePenContent),
					new Value<>("HAIR_FACIAL", PropertyValue.facialHairContent),
					new Value<>("ANAL", PropertyValue.analContent),
					new Value<>("FOOT", PropertyValue.footContent),
					new Value<>("FUTA_BALLS", PropertyValue.futanariTesticles),
					new Value<>("CLOACA", PropertyValue.bipedalCloaca),
					new Value<>("AGE", PropertyValue.ageContent),
					new Value<>("HAIR_PUBIC", PropertyValue.pubicHairContent),
					new Value<>("HAIR_BODY", PropertyValue.bodyHairContent),
					new Value<>("HAIR_ASS", PropertyValue.assHairContent),
					new Value<>("FEMININE_BEARD", PropertyValue.feminineBeardsContent),
					new Value<>("FURRY_TAIL_PENETRATION", PropertyValue.furryTailPenetrationContent),
					new Value<>("INFLATION_CONTENT", PropertyValue.inflationContent),
					new Value<>("SPITTING_ENABLED", PropertyValue.spittingEnabled),
					new Value<>("OPPORTUNISTIC_ATTACKERS", PropertyValue.opportunisticAttackers),
					new Value<>("BYPASS_SEX_ACTIONS", PropertyValue.bypassSexActions));
			
			for(Entry<String, PropertyValue> entry : settingsMap.entrySet()) {
				createToggleListener(entry.getKey()+"_ON", entry.getValue(), true);
				createToggleListener(entry.getKey()+"_OFF", entry.getValue(), false);
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
					Main.getProperties().pregnancyBreastGrowthLimit = Math.min(CupSize.getCupSizeFromInt(100).getMeasurement(), Main.getProperties().pregnancyBreastGrowthLimit+1);
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
					Main.getProperties().pregnancyLactationLimit = Math.min(Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue(), Main.getProperties().pregnancyLactationLimit+500);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_LACTATION_LIMIT_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyLactationLimit = Math.max(0, Main.getProperties().pregnancyLactationLimit-500);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			
			id = "BREAST_SIZE_PREFERENCE_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().breastSizePreference = Math.min(20, Main.getProperties().breastSizePreference+1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "BREAST_SIZE_PREFERENCE_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().breastSizePreference = Math.max(-20, Main.getProperties().breastSizePreference-1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}

			
			id = "PENIS_SIZE_PREFERENCE_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().penisSizePreference = Math.min(20, Main.getProperties().penisSizePreference+1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PENIS_SIZE_PREFERENCE_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().penisSizePreference = Math.max(-20, Main.getProperties().penisSizePreference-1);
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
					Main.getProperties().setForcedTFTendency(ForcedTFTendency.NEUTRAL);
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
					Main.getProperties().setForcedTFTendency(ForcedTFTendency.FEMININE);
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
					Main.getProperties().setForcedTFTendency(ForcedTFTendency.FEMININE_HEAVY);
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
					Main.getProperties().setForcedTFTendency(ForcedTFTendency.MASCULINE);
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
					Main.getProperties().setForcedTFTendency(ForcedTFTendency.MASCULINE_HEAVY);
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
					Main.getProperties().setForcedFetishTendency(ForcedFetishTendency.NEUTRAL);
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
					Main.getProperties().setForcedFetishTendency(ForcedFetishTendency.BOTTOM);
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
					Main.getProperties().setForcedFetishTendency(ForcedFetishTendency.BOTTOM_HEAVY);
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
					Main.getProperties().setForcedFetishTendency(ForcedFetishTendency.TOP);
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
					Main.getProperties().setForcedFetishTendency(ForcedFetishTendency.TOP_HEAVY);
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
				String fileIdentifier = Util.getFileIdentifier(f);
				String fileName = Util.getFileName(f);
				
				id = "overwrite_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.overwriteConfirmationName.equals(f.getName())) {
							OptionsDialogue.overwriteConfirmationName = "";
							Main.saveGame(fileName, true);
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
				id = "overwrite_saved_"+ fileIdentifier+"_disabled";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Overwrite (Disabled)",
							(!Main.game.isStarted()
									?"You need to have started a game before you can overwrite a save!"
									:"You cannot overwrite save files unless you are in a tile's default scene!"));
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				id = "load_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.loadConfirmationName.equals(f.getName())) {
							OptionsDialogue.loadConfirmationName = "";
							Main.loadGame(fileName);
//							Main.loadGame(f);
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
							Main.deleteGame(fileName);
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
				String fileIdentifier = Util.getFileIdentifier(f);
				String fileName = Util.getFileName(f);
				
				id = "delete_saved_character_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.deleteConfirmationName.equals(f.getName())) {
							OptionsDialogue.deleteConfirmationName = "";
							Main.deleteExportedCharacter(fileName);
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
				String fileIdentifier = Util.getFileIdentifier(f);
				
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
				String fileIdentifier = Util.getFileIdentifier(f);
				String fileName = Util.getFileName(f);
				
				if (((EventTarget) MainController.document.getElementById("import_slave_" + fileIdentifier )) != null) {
					((EventTarget) MainController.document.getElementById("import_slave_" + fileIdentifier )).addEventListener("click", e -> {
						
						try {
							Game.importCharacterAsSlave(fileName);
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
				String fileIdentifier = Util.getFileIdentifier(f);
				String fileName = Util.getFileName(f);
				
				id = "overwrite_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || EnchantmentDialogue.overwriteConfirmationName.equals(f.getName())) {
							EnchantmentDialogue.overwriteConfirmationName = "";
							EnchantmentDialogue.saveEnchant(fileName, true);
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
							LoadedEnchantment lEnch = EnchantmentDialogue.loadEnchant(fileName);
							
							EnchantmentDialogue.resetNonTattooEnchantmentVariables();
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
							EnchantmentDialogue.deleteEnchant(fileName);
							EnchantmentDialogue.initSaveLoadMenu();
							Main.game.setContent(new Response("Save/Load", ".", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
							
						} else {
							EnchantmentDialogue.overwriteConfirmationName = "";
							EnchantmentDialogue.loadConfirmationName = "";
							EnchantmentDialogue.deleteConfirmationName = f.getName();
							Main.game.setContent(new Response("Save/Load", ".", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
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
		

		// Save/load enchantment:
		if(Main.game.isStarted() && Main.game.getPlayerCell().getPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_GAMBLING)) {
			for(int i=0; i<DicePoker.getPlayerDice().size(); i++) {
				setDiceHandler(i);
			}
		}
		
		MainController.setResponseEventListeners();
	
	}

	private static void createToggleListener(String id, PropertyValue option, boolean value) {
		createToggleListener(id, option, value, null);
	}

	private static void createToggleListener(String id, PropertyValue option, boolean value, Runnable action) {
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
				Main.getProperties().setValue(option, value);
				if (action != null) action.run();
				Main.saveProperties();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}

	static void setDiceHandler(int i) {
		String id = "DICE_PLAYER_"+i;
		if (((EventTarget) MainController.document.getElementById(id)) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
				if(DicePoker.isAbleToSelectReroll()) {
					DicePoker.setReroll(DicePoker.getPlayerDice().get(i));
				}
			}, false);
			
			MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
			TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation(
					"Mark for reroll",
					DicePoker.isAbleToSelectReroll()?"":"[style.italicsBad(You cannot mark your dice for reroll in this scene!)]");
			MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
		}
	}
	
	static void setMultiBreastPreference(String id, int i) {
		((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
			Main.getProperties().multiBreasts=i;
			Main.saveProperties();
			Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
		}, false);
		
		MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
		MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
		TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(Properties.multiBreastsLabels[i], Properties.multiBreastsDescriptions[i]);
		MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
	}

	static void setUdderPreference(String id, int i) {
		((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
			Main.getProperties().udders=i;
			Main.saveProperties();
			Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
		}, false);
		
		MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
		MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
		TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(Properties.uddersLabels[i], Properties.uddersDescriptions[i]);
		MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
	}

	static void setAutosavePreference(String id, int i) {
		((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
			Main.getProperties().autoSaveFrequency=i;
			Main.saveProperties();
			Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
		}, false);
		
		MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
		MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
		TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(Properties.autoSaveLabels[i], Properties.autoSaveDescriptions[i]);
		MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
	}
	
	
	
	private static void fluidHandler(MilkingRoom room, FluidStored fluid) {
		
		String idModifier = "MILK";
		if(fluid.isCum()) {
			idModifier = "CUM";
		} else if(fluid.isGirlCum()) {
			idModifier = "GIRLCUM";
		}
		GameCharacter fluidOwner;
		try {
			fluidOwner = fluid.getFluidCharacter();
		} catch (Exception e1) {
			fluidOwner = null;
		}
		
		String fluidName = fluid.getFluid().getName(fluidOwner);
		
		Map<CoverableArea, SexAreaOrifice> areas = Util.newHashMapOfValues(
				new Value<>(CoverableArea.MOUTH, SexAreaOrifice.MOUTH),
				new Value<>(CoverableArea.VAGINA, SexAreaOrifice.VAGINA),
				new Value<>(CoverableArea.ANUS, SexAreaOrifice.ANUS));
		
		for(Entry<CoverableArea, SexAreaOrifice> area : areas.entrySet()) {
			String id = idModifier+"_"+area.getKey()+"_"+fluid.hashCode();
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				float milkAmount = Math.min(fluid.getMillilitres(), MilkingRoom.INGESTION_AMOUNT);
				boolean canIngest = room.isAbleToIngestThroughArea(fluid.getFluid().getType().getBaseType(), MilkingRoom.getTargetedCharacter(), area.getKey(), milkAmount);
				
				String fluidOwnerName = fluidOwner==null
						?"the"
						:(fluidOwner.equals(MilkingRoom.getTargetedCharacter())
							?UtilText.parse(fluidOwner, "[npc.her] own")
							:UtilText.parse(fluidOwner, "[npc.namePos]"));
				

				if(canIngest) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getTextEndStringBuilder().append("<p>");
						
						if(MilkingRoom.getTargetedCharacter().isPlayer()) {
							switch(area.getKey()) {
								case ANUS:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Grabbing one of the free tubes connected to the vat of "+fluidOwnerName+" "+fluidName+", you quickly remove the suction device on the end, before screwing on one of the funnel attachments."
											+ " Guiding the end of the tube around to your [pc.ass+], you waste no time in sliding the funnel into your [pc.asshole+]."
											+ " Flicking the switch on the side of the vat from 'suck' to 'pump', you then press the start button, letting out a delighted [pc.moan] as you feel the "+fluidName+" being pumped into your [pc.asshole]."));
									break;
								case MOUTH:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Grabbing one of the free tubes connected to the vat of "+fluidOwnerName+" "+fluidName+", you quickly remove the suction device on the end, before screwing on one of the straw-like attachments."
											+ " Lifting the straw up to your mouth, you waste no time in sliding it between your [pc.lips+]."
											+ " Flicking the switch on the side of the vat from 'suck' to 'pump', you then press the start button, letting out a delighted [pc.moan] as you hungrily gulp down the "+fluidName+"."));
									break;
								case VAGINA:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Grabbing one of the free tubes connected to the vat of "+fluidOwnerName+" "+fluidName+", you quickly remove the suction device on the end, before screwing on one of the funnel attachments."
											+ " Guiding the end of the tube between your [pc.legs+], you waste no time in sliding the funnel into your [pc.pussy+]."
											+ " Flicking the switch on the side of the vat from 'suck' to 'pump', you then press the start button, letting out a delighted [pc.moan] as you feel the "+fluidName+" being pumped"
												+(MilkingRoom.getTargetedCharacter().isVisiblyPregnant()
													?" into your [pc.pussy]."
													:" directly into your waiting womb.")));
									break;
								default:
									break;
							}
						} else {
							switch(area.getKey()) {
								case ANUS:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
												"Wanting to pump [npc.namePos] [npc.ass+] full of "+fluidOwnerName+" "+fluidName+", you instruct [npc.herHim] to"
													+ (!MilkingRoom.getTargetedCharacter().isTaur() && MilkingRoom.getTargetedCharacter().getGenitalArrangement()==GenitalArrangement.NORMAL
															?" bend over before you."
															:" kneel down and present [npc.herself] to you.")
												+ " As soon as [npc.her] [npc.asshole+] is fully on display, you grab one of the free tubes connected to your selected vat of fluid,"
													+ " before quickly removing the suction device on the end and screwing on one of the funnel attachments."
											+ "</p>"
											+ "<p>"
												+ "Guiding the end of the tube up to [npc.namePos] [npc.ass+], you waste no time in sliding the funnel into [npc.her] [npc.asshole+], smiling to yourself as [npc.she] lets out [npc.a_moan+]."
												+ " Flicking the switch on the side of the vat from 'suck' to 'pump', you then press the start button,"
													+ " causing [npc.name] to let out yet more delighted [npc.moans] as [npc.she] feels the "+fluidName+" being pumped into [npc.her] [npc.asshole]."));
									break;
								case MOUTH:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Wanting to give [npc.namePos] a taste of "+fluidOwnerName+" "+fluidName+", you instruct [npc.herHim] to kneel down before you."
											+ " As soon as [npc.she] complies, you grab one of the free tubes connected to your selected vat of fluid,"
												+ " before quickly removing the suction device on the end and screwing on one of the straw-like attachments."
										+ "</p>"
											+ "Bringing the straw to [npc.namePos] mouth, you waste no time in sliding it between [npc.her] [npc.lips+] and telling [npc.herHim] to prepare [npc.herself] for a tasty meal."
											+ " Flicking the switch on the side of the vat from 'suck' to 'pump', you then press the start button, letting out a delighted [pc.moan] as you watch [npc.name] hungrily gulp down the "+fluidName+"."));
									break;
								case VAGINA:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"Wanting to pump [npc.namePos] [npc.pussy+] full of "+fluidOwnerName+" "+fluidName+", you instruct [npc.herHim] to"
												+ (!MilkingRoom.getTargetedCharacter().isTaur() && MilkingRoom.getTargetedCharacter().getGenitalArrangement()==GenitalArrangement.NORMAL
														?" sit down on a nearby chair and spread [npc.her] [npc.legs]."
														:" kneel down and present [npc.herself] to you.")
												+ " As soon as [npc.she] complies, and [npc.her] [npc.pussy+] is fully on display, you grab one of the free tubes connected to your selected vat of fluid,"
													+ " before quickly removing the suction device on the end and screwing on one of the funnel attachments."
											+ "</p>"
											+ "<p>"
												+ "Guiding the end of the tube up to [npc.namePos] [npc.labia+], you waste no time in sliding the funnel into [npc.her] [npc.pussy+], smiling to yourself as [npc.she] lets out [npc.a_moan+]."
												+ " Flicking the switch on the side of the vat from 'suck' to 'pump', you then press the start button,"
													+ " causing [npc.name] to let out yet more delighted [npc.moans] as [npc.she] feels the "+fluidName+" being pumped"
											+(MilkingRoom.getTargetedCharacter().isVisiblyPregnant()
												?" into [npc.her] [npc.pussy]."
												:" directly into [npc.her] waiting womb.")));
									break;
								default:
									break;
							}
						}
						
						String ingestion;
						try {
							GameCharacter c = fluid.getFluidCharacter();
							ingestion = MilkingRoom.getTargetedCharacter().ingestFluid(c, fluid.getFluid(), area.getValue(), milkAmount);
						} catch (Exception e1) {
							ingestion = MilkingRoom.getTargetedCharacter().ingestFluid(null, fluid.getFluid(), area.getValue(), milkAmount);
						}
						if(!ingestion.isEmpty()) {
							Main.game.getTextEndStringBuilder().append("</p>"
									+ "<p>"
									+ ingestion);
						}
						Main.game.getTextEndStringBuilder().append("</p>");
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center;'>"
										+ "<i style='color:"+Colour.GENERIC_MINOR_BAD.toWebHexString()+";'>"+Units.fluid(milkAmount)+" of "+fluidOwnerName+" "+fluidName+" has been consumed!</i>"
								+ "</p>");
						
						room.incrementFluidStored(fluid, -milkAmount);
						
						Main.game.setContent(new Response("", "", LilayaHomeGeneric.MILKED));
						
					}, false);
				}
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				String verb = "Drink";
				String description;
				
				if(MilkingRoom.getTargetedCharacter().isPlayer()) {
					description = "Drink "+Units.fluid(milkAmount)+" of the "+fluidName+".";
					if(area.getKey()!=CoverableArea.MOUTH) {
						verb = "Pump";
						description = "Pump "+Units.fluid(milkAmount)+" of the "+fluidName+" into your "+area.getKey().getName()+".";
					}
					
				} else {
					description = UtilText.parse(MilkingRoom.getTargetedCharacter(), "Get [npc.name] to drink ")+Units.fluid(milkAmount)+" of the "+fluidName+".";
					if(area.getKey()!=CoverableArea.MOUTH) {
						verb = "Pump";
						description = "Pump "+Units.fluid(milkAmount)+" of the "+fluidName+" into " + UtilText.parse(MilkingRoom.getTargetedCharacter(),"[npc.namePos] ")+area.getKey().getName()+".";
					}
				}
				
				if(canIngest) {
					TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(verb+" ("+Units.fluid(milkAmount)+")",
							description);
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					
				} else {
					TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(verb+" ("+Units.fluid(milkAmount)+")",
							room.getAreaIngestionBlockedDescription(fluid.getFluid().getType().getBaseType(), MilkingRoom.getTargetedCharacter(), area.getKey(), milkAmount));
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
		}
		
		String id = idModifier+"_SELL_"+fluid.hashCode();
		if (((EventTarget) MainController.document.getElementById(id)) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
				int income = Math.max(1, (int)(fluid.getFluid().getValuePerMl()*fluid.getMillilitres()));
				Main.game.getPlayer().incrementMoney(income);
				room.getFluidsStored().remove(fluid);
				Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>You sold the "+fluidName+" for "+(UtilText.formatAsMoney(income, "span"))+"!</p>");
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
			
			MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
			TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Sell",
					"Sell all of the "+fluidName+" for "+(Math.max(1, (int)(fluid.getFluid().getValuePerMl()*fluid.getMillilitres())))+" flames.");
			MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
		}
	}
}
