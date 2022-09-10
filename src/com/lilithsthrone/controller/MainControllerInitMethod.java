package com.lilithsthrone.controller;

import java.io.File;
import java.time.LocalDateTime;
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
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.Antenna;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Horn;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Testicle;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
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
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
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
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
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
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.effects.TreeEntry;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.fetishes.FetishPreference;
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
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.persona.SexualOrientationPreference;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesPreference;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.moves.CombatMove;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.companions.OccupantSortingMethod;
import com.lilithsthrone.game.dialogue.npcDialogue.elemental.ElementalDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Library;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaMilkingRoomDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePoker;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.CombatMovesSetup;
import com.lilithsthrone.game.dialogue.utils.CosmeticsDialogue;
import com.lilithsthrone.game.dialogue.utils.DebugDialogue;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.MapTravelType;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.SpellManagement;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.AbstractSetBonus;
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.Sticker;
import com.lilithsthrone.game.inventory.clothing.StickerCategory;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.LoadedEnchantment;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobHours;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
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
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;
import javafx.stage.FileChooser;

/**
 * This method was causing MainController to lag out Eclipse, so I moved it to a separate file.
 *
 * @since 0.2.5
 * @version 0.4.2
 * @author Innoxia, Maxis
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
		
//		if (((EventTarget) MainController.document.getElementById("copy-content-button")) != null) {
//			MainController.addEventListener(MainController.document, "copy-content-button", "click", MainController.copyDialogueButtonListener, false);
//			MainController.addEventListener(MainController.document, "copy-content-button", "mousemove", MainController.moveTooltipListener, false);
//			MainController.addEventListener(MainController.document, "copy-content-button", "mouseleave", MainController.hideTooltipListener, false);
//			MainController.addEventListener(MainController.document, "copy-content-button", "mouseenter", MainController.copyInfoListener, false);
//		}
//		
//		if (((EventTarget) MainController.document.getElementById("export-character-button")) != null) {
//			MainController.addEventListener(MainController.document, "export-character-button", "click", e -> {
//				if(Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CHARACTER_APPEARANCE)) {
//					Game.exportCharacter(Main.game.getPlayer());
//				} else {
//					Game.exportCharacter(CharactersPresentDialogue.characterViewed);
//				}
//				
//				Main.game.flashMessage(PresetColour.GENERIC_EXCELLENT, "Character Exported!");
//			}, false);
//			MainController.addEventListener(MainController.document, "export-character-button", "mousemove", MainController.moveTooltipListener, false);
//			MainController.addEventListener(MainController.document, "export-character-button", "mouseleave", MainController.hideTooltipListener, false);
//			MainController.addEventListener(MainController.document, "export-character-button", "mouseenter", new TooltipInformationEventListener().setInformation(
//					"Export Character",
//					"Export the currently displayed character to the 'data/characters' folder. Exported characters can be imported at the auction block in Slaver Alley."), false);
//		}
		
		if(Main.game.isInCombat()) {
			for(GameCharacter combatant : Main.combat.getAllCombatants(true)) {
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
				|| Main.game.getCurrentDialogueNode().equals(CompanionManagement.SLAVE_MANAGEMENT_INSPECT)) {
			GameCharacter character =
					Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CHARACTER_APPEARANCE)
						? Main.game.getPlayer()
						: (Main.game.getCurrentDialogueNode().equals(CompanionManagement.SLAVE_MANAGEMENT_INSPECT)
								?Main.game.getDialogueFlags().getManagementCompanion()
								:CharactersPresentDialogue.characterViewed);
			
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

					id = "ARTWORK_DELETE";
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							artwork.getCurrentImage().delete();
							character.updateImages();
							if (!character.isPlayer()) CharactersPresentDialogue.resetContent(character);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);

						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setInformation(
								"Remove custom artwork",
								"Removes the current image from this character."
										+ "<br/>[style.italicsBad(Please note that this will delete the image from the game's folder!)]"),
								false);
					}
				} catch(Exception ex) {
					System.err.println("MainController Artwork handling error.");
				}
			}
		}
		
		if(Main.game.getCurrentDialogueNode().equals(RoomPlayer.ROOM_SET_ALARM)) {
			id = "PLAYER_ALARM_DECREASE_LARGE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", -60);
					if(Main.game.getDialogueFlags().getSavedLong("player_phone_alarm") < 0) {
						Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", 24*60);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PLAYER_ALARM_DECREASE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", -5);
					if(Main.game.getDialogueFlags().getSavedLong("player_phone_alarm") < 0) {
						Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", 24*60);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PLAYER_ALARM_INCREASE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", 5);
					if(Main.game.getDialogueFlags().getSavedLong("player_phone_alarm") >= 24*60) {
						Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", -24*60);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PLAYER_ALARM_INCREASE_LARGE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", 60);
					if(Main.game.getDialogueFlags().getSavedLong("player_phone_alarm") >= 24*60) {
						Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", -24*60);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
		
		// -------------------- Debug menu -------------------- //
		
		if(Main.game.getCurrentDialogueNode().equals(DebugDialogue.SPAWN_MENU) || Main.game.getCurrentDialogueNode().equals(DebugDialogue.ITEM_VIEWER)) {
			id = "";
			
			for(AbstractClothingType clothingType : ClothingType.getAllClothing()) {
				id = clothingType.getId() + "_SPAWN";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addClothing(Main.game.getItemGen().generateClothing(clothingType));
						MainController.updateUIRightPanel();
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					
					TooltipInventoryEventListener el = new TooltipInventoryEventListener().setGenericClothing(clothingType, clothingType.getColourReplacement(0).getFirstOfDefaultColours());
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
			
			for(AbstractWeaponType weaponType : WeaponType.getAllWeapons()) {
				id = weaponType.getId() + "_SPAWN";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addWeapon(Main.game.getItemGen().generateWeapon(weaponType));
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
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem(Main.game.getItemGen().generateItem(itemType));
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
					DebugDialogue.itemTag = ItemTag.CHEAT_ITEM;
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}

		if(Main.game.getCurrentDialogueNode().equals(DebugDialogue.SPAWN_MENU_SET)) {
			for(AbstractSetBonus sb : SetBonus.allSetBonuses) {
				id = "SET_BONUS_"+SetBonus.getIdFromSetBonus(sb);
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						for(AbstractWeaponType wt : WeaponType.getAllWeapons()) {
							if(wt.getClothingSet()==sb) {
								Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addWeapon(Main.game.getItemGen().generateWeapon(wt));
							}
						}
						for(AbstractClothingType ct : ClothingType.getAllClothing()) {
							if(ct.getClothingSet()==sb) {
								Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addClothing(Main.game.getItemGen().generateClothing(ct));
							}
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
					
				}
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


		if(Main.game.isStarted() || Main.game.getCurrentDialogueNode().equals(OptionsDialogue.FETISH_PREFERENCE)) {
			for(Entry<String, TooltipInformationEventListener> entry : Game.informationTooltips.entrySet()) {
				id = entry.getKey();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseenter", entry.getValue(), false);
				}
			}
		}
		
		if(Main.game.isStarted()) {
			id = "";
			
			// Gifts:
			if(Main.game.getCurrentDialogueNode().equals(GiftDialogue.GIFT_DIALOGUE)) {
				for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayer().getAllWeaponsInInventory().entrySet()) {
					id = "GIFT_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.restoreSavedContent(false);
							Main.game.setContent(new Response("Give Gift", "", GiftDialogue.getDialogueToProceedTo()){
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
							Main.game.restoreSavedContent(false);
							Main.game.setContent(new Response("Give Gift", "", GiftDialogue.getDialogueToProceedTo()){
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
							Main.game.restoreSavedContent(false);
							Main.game.setContent(new Response("Give Gift", "", GiftDialogue.getDialogueToProceedTo()){
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
			id = "PLAYER_MONEY_TRANSFER_SMALL";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					if(Main.game.getPlayer().getMoney()>0 && InventoryDialogue.getNPCInventoryInteraction()==InventoryInteraction.FULL_MANAGEMENT) {
						if(InventoryDialogue.getInventoryNPC()==null) {
							Main.game.getPlayerCell().getInventory().incrementMoney((int) Math.max(1, Main.game.getPlayer().getMoney()*0.01f));
						} else {
							InventoryDialogue.getInventoryNPC().incrementMoney((int) Math.max(1, Main.game.getPlayer().getMoney()*0.01f));
						}
						Main.game.getPlayer().incrementMoney((int) -Math.max(1, Main.game.getPlayer().getMoney()*0.01f));
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el2 = new TooltipInformationEventListener().setMoneyTransferTarget(Main.game.getPlayer(), InventoryDialogue.getInventoryNPC(), 1);
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
			id = "PLAYER_MONEY_TRANSFER_AVERAGE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					if(Main.game.getPlayer().getMoney()>0 && InventoryDialogue.getNPCInventoryInteraction()==InventoryInteraction.FULL_MANAGEMENT) {
						if(InventoryDialogue.getInventoryNPC()==null) {
							Main.game.getPlayerCell().getInventory().incrementMoney((int) Math.max(1, Main.game.getPlayer().getMoney()*0.1f));
						} else {
							InventoryDialogue.getInventoryNPC().incrementMoney((int) Math.max(1, Main.game.getPlayer().getMoney()*0.1f));
						}
						Main.game.getPlayer().incrementMoney((int) -Math.max(1, Main.game.getPlayer().getMoney()*0.1f));
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el2 = new TooltipInformationEventListener().setMoneyTransferTarget(Main.game.getPlayer(), InventoryDialogue.getInventoryNPC(), 10);
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
			id = "PLAYER_MONEY_TRANSFER_BIG";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					if(Main.game.getPlayer().getMoney()>0 && InventoryDialogue.getNPCInventoryInteraction()==InventoryInteraction.FULL_MANAGEMENT) {
						if(InventoryDialogue.getInventoryNPC()==null) {
							Main.game.getPlayerCell().getInventory().incrementMoney((int) Math.max(1, Main.game.getPlayer().getMoney()));
						} else {
							InventoryDialogue.getInventoryNPC().incrementMoney((int) Math.max(1, Main.game.getPlayer().getMoney()));
						}
						Main.game.getPlayer().incrementMoney((int) -Math.max(1, Main.game.getPlayer().getMoney()));
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}
				}, false);
				
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el2 = new TooltipInformationEventListener().setMoneyTransferTarget(Main.game.getPlayer(), InventoryDialogue.getInventoryNPC(), 100);
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
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
				id = idModifier+"MONEY_TRANSFER_SMALL";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(InventoryDialogue.getInventoryNPC().getMoney()>0 && InventoryDialogue.getNPCInventoryInteraction()==InventoryInteraction.FULL_MANAGEMENT) {
							Main.game.getPlayer().incrementMoney((int) Math.max(1, InventoryDialogue.getInventoryNPC().getMoney()*0.01f));
							InventoryDialogue.getInventoryNPC().incrementMoney((int) -Math.max(1, InventoryDialogue.getInventoryNPC().getMoney()*0.01f));
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
					
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setMoneyTransferTarget(InventoryDialogue.getInventoryNPC(), Main.game.getPlayer(), 1);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				id = idModifier+"MONEY_TRANSFER_AVERAGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(InventoryDialogue.getInventoryNPC().getMoney()>0 && InventoryDialogue.getNPCInventoryInteraction()==InventoryInteraction.FULL_MANAGEMENT) {
							Main.game.getPlayer().incrementMoney((int) Math.max(1, InventoryDialogue.getInventoryNPC().getMoney()*0.1f));
							InventoryDialogue.getInventoryNPC().incrementMoney((int) -Math.max(1, InventoryDialogue.getInventoryNPC().getMoney()*0.1f));
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
					
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setMoneyTransferTarget(InventoryDialogue.getInventoryNPC(), Main.game.getPlayer(), 10);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				id = idModifier+"MONEY_TRANSFER_BIG";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(InventoryDialogue.getInventoryNPC().getMoney()>0 && InventoryDialogue.getNPCInventoryInteraction()==InventoryInteraction.FULL_MANAGEMENT) {
							Main.game.getPlayer().incrementMoney((int) Math.max(1, InventoryDialogue.getInventoryNPC().getMoney()));
							InventoryDialogue.getInventoryNPC().incrementMoney((int) -Math.max(1, InventoryDialogue.getInventoryNPC().getMoney()));
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
					
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setMoneyTransferTarget(InventoryDialogue.getInventoryNPC(), Main.game.getPlayer(), 100);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
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
				id = "FLOOR_MONEY_TRANSFER_SMALL";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayerCell().getInventory().getMoney()>0 && InventoryDialogue.getNPCInventoryInteraction()==InventoryInteraction.FULL_MANAGEMENT) {
							Main.game.getPlayer().incrementMoney((int) Math.max(1, Main.game.getPlayerCell().getInventory().getMoney()*0.01f));
							Main.game.getPlayerCell().getInventory().incrementMoney((int) -Math.max(1, Main.game.getPlayerCell().getInventory().getMoney()*0.01f));
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
					
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setMoneyTransferTarget(null, Main.game.getPlayer(), 1);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				id = "FLOOR_MONEY_TRANSFER_AVERAGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayerCell().getInventory().getMoney()>0 && InventoryDialogue.getNPCInventoryInteraction()==InventoryInteraction.FULL_MANAGEMENT) {
							Main.game.getPlayer().incrementMoney((int) Math.max(1, Main.game.getPlayerCell().getInventory().getMoney()*0.1f));
							Main.game.getPlayerCell().getInventory().incrementMoney((int) -Math.max(1, Main.game.getPlayerCell().getInventory().getMoney()*0.1f));
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
					
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setMoneyTransferTarget(null, Main.game.getPlayer(), 10);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				id = "FLOOR_MONEY_TRANSFER_BIG";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayerCell().getInventory().getMoney()>0 && InventoryDialogue.getNPCInventoryInteraction()==InventoryInteraction.FULL_MANAGEMENT) {
							Main.game.getPlayer().incrementMoney((int) Math.max(1, Main.game.getPlayerCell().getInventory().getMoney()));
							Main.game.getPlayerCell().getInventory().incrementMoney((int) -Math.max(1, Main.game.getPlayerCell().getInventory().getMoney()));
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
					
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setMoneyTransferTarget(null, Main.game.getPlayer(), 100);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
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
			
//			for(TFEssence essence : TFEssence.values()) {
//				if (((EventTarget) MainController.document.getElementById("ESSENCE_" + essence.hashCode())) != null) {
//					MainController.addEventListener(MainController.document, "ESSENCE_" + essence.hashCode(), "mousemove", MainController.moveTooltipListener, false);
//					MainController.addEventListener(MainController.document, "ESSENCE_" + essence.hashCode(), "mouseleave", MainController.hideTooltipListener, false);
//					
//					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setEssence(essence);
//					MainController.addEventListener(MainController.document, "ESSENCE_" + essence.hashCode(), "mouseenter", el2, false);
//				}
//				if (((EventTarget) MainController.document.getElementById("ESSENCE_COST_" + essence.hashCode())) != null) {
//					MainController.addEventListener(MainController.document, "ESSENCE_COST_" + essence.hashCode(), "mousemove", MainController.moveTooltipListener, false);
//					MainController.addEventListener(MainController.document, "ESSENCE_COST_" + essence.hashCode(), "mouseleave", MainController.hideTooltipListener, false);
//					
//					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setEssence(essence);
//					MainController.addEventListener(MainController.document, "ESSENCE_COST_" + essence.hashCode(), "mouseenter", el2, false);
//				}
//			}
			
	
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

//					((EventTarget) MainController.document.getElementById("INGREDIENT_ENCHANTING")).addEventListener("click", e -> {
//						Main.game.setResponseTab(1);
//						if(EnchantmentDialogue.getIngredient() instanceof AbstractItem) {
//							Main.game.setContent(new Response("Back", "Stop enchanting.", InventoryDialogue.ITEM_INVENTORY){
//								@Override
//								public void effects() {
//									EnchantmentDialogue.resetEnchantmentVariables();
//								}
//							});
//
//						} else if(EnchantmentDialogue.getIngredient() instanceof AbstractClothing) {
//							Main.game.setContent(new Response("Back", "Stop enchanting.", InventoryDialogue.CLOTHING_INVENTORY){
//								@Override
//								public void effects() {
//									EnchantmentDialogue.resetEnchantmentVariables();
//								}
//							});
//
//						} else if(EnchantmentDialogue.getIngredient() instanceof AbstractWeapon) {
//							Main.game.setContent(new Response("Back", "Stop enchanting.", InventoryDialogue.WEAPON_INVENTORY){
//								@Override
//								public void effects() {
//									EnchantmentDialogue.resetEnchantmentVariables();
//								}
//							});
//
//						} else if(EnchantmentDialogue.getIngredient() instanceof Tattoo) {
//							Main.game.setContent(new Response("Back", "Stop enchanting.", BodyChanging.getTarget().isPlayer()?SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS:CompanionManagement.SLAVE_MANAGEMENT_TATTOOS){
//								@Override
//								public void effects() {
//									EnchantmentDialogue.resetEnchantmentVariables();
//								}
//							});
//
//						}
//
//					}, false);

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

					MainController.addEventListener(MainController.document, "OUTPUT_ENCHANTING", "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, "OUTPUT_ENCHANTING", "mouseleave", MainController.hideTooltipListener, false);

					TooltipInventoryEventListener el2 = null;
					if(EnchantmentDialogue.getIngredient() instanceof AbstractItem) {
						AbstractItem preview = EnchantingUtils.craftItem(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects());
						el2 = new TooltipInventoryEventListener().setItem(preview, Main.game.getPlayer(), null);

					} else if(EnchantmentDialogue.getIngredient() instanceof AbstractClothing) {
						AbstractClothing preview = EnchantingUtils.craftClothing(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects());
						el2 = new TooltipInventoryEventListener().setClothing(preview, Main.game.getPlayer(), null);

					} else if(EnchantmentDialogue.getIngredient() instanceof AbstractWeapon) {
						AbstractWeapon preview = EnchantingUtils.craftWeapon(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects());
						el2 = new TooltipInventoryEventListener().setWeapon(preview, Main.game.getPlayer(), false);

					}  else if(EnchantmentDialogue.getIngredient() instanceof Tattoo) {
						Tattoo preview = EnchantingUtils.craftTattoo(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getEffects());
						el2 = new TooltipInventoryEventListener().setTattoo(EnchantmentDialogue.getTattooSlot(), preview, EnchantmentDialogue.getTattooBearer(), EnchantmentDialogue.getTattooBearer());
					}

					MainController.addEventListener(MainController.document, "OUTPUT_ENCHANTING", "mouseenter", el2, false);
				}

				// Adding an effect:
				id = "ENCHANT_ADD_BUTTON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {

					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(EnchantmentDialogue.getIngredient().getEnchantmentEffect().getEffectsDescription(
								EnchantmentDialogue.getPrimaryMod(), EnchantmentDialogue.getSecondaryMod(), EnchantmentDialogue.getPotency(), EnchantmentDialogue.getLimit(), Main.game.getPlayer(), Main.game.getPlayer())!=null) {
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

					String blockedString = EnchantmentDialogue.getEnchantmentEffectBlockedReason(EnchantmentDialogue.getCurrentEffect());
					TooltipInformationEventListener el2 =  new TooltipInformationEventListener().setInformation("Add Effect",
							EnchantmentDialogue.getEffects().size() >= EnchantmentDialogue.getIngredient().getEnchantmentLimit()
								?"You have already added the maximum number of effects for this item!"
								:(blockedString!=null
									?blockedString
									:""));
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
				for (TFModifier tfMod : EnchantmentDialogue.getIngredient().getEnchantmentEffect().getSecondaryModifiers(EnchantmentDialogue.getIngredient(), EnchantmentDialogue.getPrimaryMod())) {
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
				for(AbstractPlaceUpgrade placeUpgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
					
					id = "ROOM_MOD_INFO_"+PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("", (OccupantManagementDialogue.cellToInspect.getPlace().getPlaceUpgrades().contains(placeUpgrade)
								?placeUpgrade.getDescriptionAfterPurchase()
								:placeUpgrade.getDescriptionForPurchase()));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_BUY";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(
									new Response("",
											"",
											placeUpgrade.getInstallationDialogue(OccupantManagementDialogue.cellToInspect)==null
												?Main.game.getCurrentDialogueNode()
												:placeUpgrade.getInstallationDialogue(OccupantManagementDialogue.cellToInspect)){
								@Override
								public void effects() {
									OccupantManagementDialogue.cellToInspect.addPlaceUpgrade(placeUpgrade);
									Main.game.getPlayer().incrementMoney(-placeUpgrade.getInstallCost());
								}
							});
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Purchase Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getInstallCost())
								+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_BUY_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Purchase Modification",
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getInstallCost())
								+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_SELL";
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
								"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())
								+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_SELL_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove Modification",
								(!placeUpgrade.getRemovalAvailability(OccupantManagementDialogue.cellToInspect).getKey()
										?placeUpgrade.getRemovalAvailability(OccupantManagementDialogue.cellToInspect).getValue()
										:"This will cost: "+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())
											+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade)));
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
				for(int i=6; i>=0; i--) {
					id ="SLAVE_DAY_"+i;
					int iMustBeFinalOrEffectivelyFinal = i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							OccupantManagementDialogue.setDayNumber(Main.game.getDayNumber()-iMustBeFinalOrEffectivelyFinal);
							Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
			}
			
			if(Main.game.getDialogueFlags().getManagementCompanion()!=null
					|| ScarlettsShop.isSlaveCustomisationMenu()
					|| Main.game.getCurrentDialogueNode() == ElementalDialogue.ELEMENTAL_CHOOSE_NAME) {
				GameCharacter javaFfs = Main.game.getDialogueFlags().getManagementCompanion();
				if(ScarlettsShop.isSlaveCustomisationMenu()) {
					javaFfs = BodyChanging.getTarget();
				} else if(Main.game.getCurrentDialogueNode() == ElementalDialogue.ELEMENTAL_CHOOSE_NAME) {
					javaFfs = Main.game.getPlayer().getElemental();
				}
				GameCharacter slave = javaFfs;
				
				id = slave.getId()+"_RENAME";
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
										slave.setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent()));
										slave.loadImages();
									}
								});
							}
							
						}
						
					}, false);
				}

				id = slave.getId()+"_RENAME_RANDOM";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								slave.setName(new NameTriplet(Name.getRandomName(slave)));
								slave.loadImages();
							}
						});
					}, false);
				}
				
				id = slave.getId()+"_RENAME_SURNAME";
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
										slave.setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
//										slave.loadImages();
									}
								});
							}
						}
						
					}, false);
				}

				id = slave.getId()+"_RENAME_SURNAME_RANDOM";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								slave.setSurname(Name.getSurname(slave));
								slave.loadImages();
							}
						});
					}, false);
				}
				
				id = slave.getId()+"_CALLS_PLAYER";
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
										slave.setPetName(Main.game.getPlayer(), Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
									}
								});
							}
							
						}
						
					}, false);
				}
			}
			
			if(Main.game.getDialogueFlags().getManagementCompanion()!=null) {
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
							if(preset==SlaveJobHours.NONE) {
								for(int hour = 0; hour<24; hour++) {
									SlaveJob job = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
									if(Main.game.getDialogueFlags().getManagementCompanion().getSlaveJob(hour)==job) {
										Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(hour, SlaveJob.IDLE);
									}
								}
								
							} else {
								for(int hour = preset.getStartHour(); hour<preset.getStartHour()+preset.getLength(); hour++) {
									int appliedHour = hour%24;
									SlaveJob job = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
									if(job.isAvailable(appliedHour, Main.game.getDialogueFlags().getManagementCompanion())) {
										Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(appliedHour, job);
									}
								}
							}
							
							Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
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

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setSlaveJob(job, Main.game.getDialogueFlags().getManagementCompanion());
						
//						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
//								Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion())),
//								UtilText.parse(Main.game.getDialogueFlags().getManagementCompanion(), job.getDescription()));
						
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = job+"_ASSIGN";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.getDialogueFlags().setSlaveryManagerJobSelected(job);
							Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setSlaveJob(job, Main.game.getDialogueFlags().getManagementCompanion());
//						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
//								Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion())),
//								job.getDescription()
//								+"<br/>[style.boldOrange(Hourly Fatigue:)] "+(job.getHourlyFatigue()>0?"[style.boldBad(":"[style.boldGood(")+job.getHourlyFatigue()+")]");
						
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					for(SlaveJobSetting setting : job.getMutualSettings()) {
						id = job.toString()+setting.toString()+"_ADD";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.getDialogueFlags().getManagementCompanion().addSlaveJobSettings(job, setting);
								Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
									"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+":</b> "
										+"<b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
									setting.getDescription()
										+" [style.italicsMinorGood(Click to apply this permission.)]");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = job.toString()+setting.toString()+"_REMOVE";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.getDialogueFlags().getManagementCompanion().removeSlaveJobSettings(job, setting);
								Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
									"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+":</b> "
											+"<b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
									setting.getDescription()
										+" [style.italicsMinorBad(Click to revoke this permission.)]");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}

					for(Entry<String, List<SlaveJobSetting>> entry : job.getMutuallyExclusiveSettings().entrySet()) {

						for(SlaveJobSetting setting : entry.getValue()) {
							id = setting+"_TOGGLE_ADD";
							if (((EventTarget) MainController.document.getElementById(id)) != null) {
								((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
									for(SlaveJobSetting settingRem : entry.getValue()) {
										Main.game.getDialogueFlags().getManagementCompanion().removeSlaveJobSettings(job, settingRem);
									}
									Main.game.getDialogueFlags().getManagementCompanion().addSlaveJobSettings(job, setting);
									Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
								}, false);
								
								MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
								TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
										"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+":</b> "
												+"<b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
										setting.getDescription()
											+" [style.italicsMinorGood(Click to apply this permission.)]");
								MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
							}
							
							id = setting+"_DISABLED";
							if (((EventTarget) MainController.document.getElementById(id)) != null) {
								MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
								TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
										"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+":</b> "
												+"<b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
										setting.getDescription()
											+" [style.italicsMinorBad(You cannot revoke permissions in this category. Select a different one instead.)]");
								MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
							}
						}
					}

//					id = "SET_SLAVE_CATEGORY";
//					if (((EventTarget) MainController.document.getElementById(id)) != null) {
//						((EventTarget) MainController.document.getElementById(id)).addEventListener("change", e -> {
//							Main.game.getDialogueFlags().getManagementCompanion().setSlaveCategory(((HTMLInputElement)e.getTarget()).getValue());
//							//Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
//						}, false);
//						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
//						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
//						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
//								"<b>Slave Category</b>",
//								"Used when you select [style.italicsArcane(Sort by Custom Category)]. Use any format you desire.");
//						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
//					}

//					id = "SET_SLAVE_NOTES";
//					if (((EventTarget) MainController.document.getElementById(id)) != null) {
//						((EventTarget) MainController.document.getElementById(id)).addEventListener("change", e -> {
//							Main.game.getDialogueFlags().getManagementCompanion().setSlaveNotes(((HTMLTextAreaElement)e.getTarget()).getValue());
//							//Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
//						}, false);
//						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
//						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
//						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
//								"<b>Slave Notes</b>",
//								"Write any arbitrary text here, or none at all. Useful if you need to track some information about a slave not available elsewhere.");
//						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
//						// This needs to be done here rather than in HTML due to Java not handling linebreaks and <br>s correctly.
//						((HTMLTextAreaElement)MainController.document.getElementById(id)).setValue(Main.game.getDialogueFlags().getManagementCompanion().getSlaveNotes());
//					}
				}
				
				// Permissions:
				for(SlavePermission permission : SlavePermission.values()) {
					for(SlavePermissionSetting setting : permission.getSettings()) {
						id = setting+"_ADD";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.getDialogueFlags().getManagementCompanion().addSlavePermissionSetting(permission, setting);
								Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
									"<b style='color:"+permission.getColour().toWebHexString()+";'>"+permission.getName()+":</b> "+setting.getName(),
									setting.getDescription()
										+" [style.italicsMinorGood(Click to apply this permission.)]"
										+(permission.isMutuallyExclusiveSettings()
											?" [style.italicsMinorBad(Only one permission in this category can be active at once.)]"
											:""));
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = setting+"_REMOVE";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.getDialogueFlags().getManagementCompanion().removeSlavePermissionSetting(permission, setting);
								Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
									"<b style='color:"+permission.getColour().toWebHexString()+";'>"+permission.getName()+":</b> "+setting.getName(),
									setting.getDescription()
										+" [style.italicsMinorBad(Click to revoke this permission.)]");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = setting+"_REMOVE_ME";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
									"<b style='color:"+permission.getColour().toWebHexString()+";'>"+permission.getName()+":</b> "+setting.getName(),
									setting.getDescription()
										+" [style.italicsMinorBad(You cannot revoke permissions in this category. Select a different one instead.)]");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}
				}
			}
			

			if(Main.game.getCurrentDialogueNode() == OccupantManagementDialogue.SLAVE_LIST
					|| Main.game.getCurrentDialogueNode() == OccupantManagementDialogue.SLAVE_LIST_MANAGEMENT) {
				
				// Sorting
				for(OccupantSortingMethod osm : OccupantSortingMethod.values()) {
					id = "SORT_SLAVES_BY_" + osm;
					if(((EventTarget)MainController.document.getElementById(id))!=null) {
						String friendlyName = Util.capitaliseSentence(osm.getName());
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									OccupantManagementDialogue.setSlavesSortedBy(osm);
								}
							});
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
							"<b>Sort By "+friendlyName+"</b>",
							osm.getSortingDescription());
						
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}

				// Sort ascending
				id = "SORT_SLAVES_ASC";
				if(((EventTarget)MainController.document.getElementById(id))!=null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								OccupantManagementDialogue.setSlavesAreInReverseOrder(false);
							}
						});
					}, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

					TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
						"Sort in ascending order",
						"");
					
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}

				// Sort Descending
				id = "SORT_SLAVES_DESC";
				if(((EventTarget)MainController.document.getElementById(id))!=null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								OccupantManagementDialogue.setSlavesAreInReverseOrder(true);
							}
						});
					}, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

					TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
						"Sort in descending order",
						"");
					
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
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

					if(slave!=null) { // slave shouldn't be null...
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementInspectSlaveDialogue(slave)) {
									@Override
									public void effects() {
										CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
										Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
									}
								});
							}, false);
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
		
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Inspect Slave",
									UtilText.parse(slave, "Inspect [npc.name]."));
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = slaveId+"_JOB";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(slave)) {
									@Override
									public void effects() {
										CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
										Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
									}
								});
							}, false);
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
		
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Slave's Job",
									UtilText.parse(slave, "Set [npc.namePos] job and work hours."));
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = slaveId+"_PERMISSIONS";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlavePermissionsDialogue(slave)) {
									@Override
									public void effects() {
										CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
										Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
									}
								});
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
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
	//							Main.game.getDialogueFlags().setManagementCompanion(slave);
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
										if(!slave.isAtWork()) {
											slave.returnToHome();
										}
									}
								});
							}, false);
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
		
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Assign Slave Here",
									UtilText.parse(slave, "Assign [npc.name] to your current location."));
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
								if(slave.isAbleToBeSold()) {
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
										@Override
										public void effects() {
											Main.game.getPlayer().incrementMoney((int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()));
											Main.game.getDialogueFlags().getSlaveTrader().addSlave(slave);
											slave.setLocation(Main.game.getDialogueFlags().getSlaveTrader().getWorldLocation(), Main.game.getDialogueFlags().getSlaveTrader().getLocation(), true);
										}
									});
								}
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
		
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Sell Slave",
									UtilText.parse(slave,
											(slave.isAbleToBeSold()
												?"[npc.Name] has a value of "+UtilText.formatAsMoney(slave.getValueAsSlave(true), "b", PresetColour.GENERIC_GOOD)+"<br/>"
													+ "However, "+Main.game.getDialogueFlags().getSlaveTrader().getName(true)+" will buy [npc.herHim] for "
														+UtilText.formatAsMoney((int)(slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()), "b", PresetColour.GENERIC_ARCANE)+"."
												:"[npc.Name] cannot be sold!")));
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = slaveId+"_SELL_DISABLED";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
		
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Sell Slave",
									UtilText.parse(slave,
											slave.isAbleToBeSold()
													?"You cannot sell [npc.name], as there's nobody here to sell [npc.herHim] to."
													:"[npc.Name] cannot be sold!"));
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = slaveId+"_COSMETICS";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveCosmeticsDialogue(slave)) {
									@Override
									public void effects() {
										CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
										Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
										BodyChanging.setTarget(slave);
									}
								});
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
		
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Send to Kate",
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
								Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementInspectSlaveDialogue(occupant)) {
									@Override
									public void effects() {
										CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), occupant);
										Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
									}
								});
							}, false);
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
		
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Inspect Character",
									UtilText.parse(occupant, "Inspect [npc.name]."));
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = occupantId+"_JOB";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Job", "You cannot manage a free-willed occupant's job.");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = occupantId+"_PERMISSIONS";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Permissions", "You cannot manage a free-willed occupant's permissions.");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
						
						id = occupantId+"_INVENTORY";
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.getDialogueFlags().setManagementCompanion(occupant);
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
								Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveCosmeticsDialogue(occupant)) {
									@Override
									public void effects() {
										CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), occupant);
										Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
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
	
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Send to Kate", "You haven't met Kate yet!");
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
								Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementInspectSlaveDialogue(slave)) {
									@Override
									public void effects() {
										CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
										Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
									}
								});
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
										Main.game.getPlayer().incrementMoney(-(int)(slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier(null)));
										Main.game.getPlayer().addSlave(slave);
										slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
									}
								});
							}, false);
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
		
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Buy Slave",
									UtilText.parse(slave, "[npc.Name] has a value of "+UtilText.formatAsMoney(slave.getValueAsSlave(true), "b", PresetColour.GENERIC_GOOD)+"<br/>"
											+ "However, "+Main.game.getDialogueFlags().getSlaveTrader().getName(true)+" will sell [npc.herHim] for "
												+UtilText.formatAsMoney((int)(slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier(null)), "b", PresetColour.GENERIC_ARCANE)+"."));
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
			
			if(Main.game.isStarted()
					&& (Main.game.getCurrentDialogueNode().equals(CharacterCreation.CHOOSE_APPEARANCE)
							|| Main.game.getCurrentDialogueNode().equals(CharacterCreation.CHOOSE_SEX_EXPERIENCE)
							|| Main.game.getCurrentDialogueNode().equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY)
							|| Main.game.getCurrentDialogueNode().equals(ScarlettsShop.HELENAS_SHOP_CUSTOM_SLAVE_FINISH))) {
				for(Month month : Month.values()) {
					id = "STARTING_MONTH_"+month;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setStartingDateMonth(month);
							CharacterModificationUtils.performAgeCheck();
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Birth day:
				id = "BIRTH_DAY_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusDays(1));
						CharacterModificationUtils.performAgeCheck();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "BIRTH_DAY_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusDays(5));
						CharacterModificationUtils.performAgeCheck();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "BIRTH_DAY_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusDays(1));
						CharacterModificationUtils.performAgeCheck();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "BIRTH_DAY_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusDays(5));
						CharacterModificationUtils.performAgeCheck();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Birth month:
				id = "BIRTH_MONTH_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusMonths(1));
						CharacterModificationUtils.performAgeCheck();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "BIRTH_MONTH_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusMonths(5));
						CharacterModificationUtils.performAgeCheck();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "BIRTH_MONTH_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusMonths(1));
						CharacterModificationUtils.performAgeCheck();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "BIRTH_MONTH_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusMonths(5));
						CharacterModificationUtils.performAgeCheck();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Age:
				id = "AGE_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusYears(1));
						CharacterModificationUtils.performAgeCheck();
						
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "AGE_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusYears(5));
						CharacterModificationUtils.performAgeCheck();
						
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "AGE_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusYears(1));
						CharacterModificationUtils.performAgeCheck();
						
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "AGE_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusYears(5));
						CharacterModificationUtils.performAgeCheck();
						
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				
				// Sex experiences:
				for(int i : CharacterModificationUtils.soSilly) {
					// Given:
					id = "HANDJOBS_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS), i);
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
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER), i);
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
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_EYES)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_HAIR)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_HEAD)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_ASS)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_BREASTS)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_VAGINA)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_PENIS)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_BREASTS_CROTCH)
					|| ScarlettsShop.isSlaveCustomisationMenu()) {
				
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
						if(!Main.game.isInNewWorld() && BodyChanging.getTarget().isPlayer()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_MASCULINE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.MASCULINE.getMedianFemininity());
						if(!Main.game.isInNewWorld() && BodyChanging.getTarget().isPlayer()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_ANDROGYNOUS";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.ANDROGYNOUS.getMedianFemininity());
						if(!Main.game.isInNewWorld() && BodyChanging.getTarget().isPlayer()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_FEMININE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.FEMININE.getMedianFemininity());
						if(!Main.game.isInNewWorld() && BodyChanging.getTarget().isPlayer()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_FEMININE_STRONG";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.FEMININE_STRONG.getMedianFemininity());
						if(!Main.game.isInNewWorld() && BodyChanging.getTarget().isPlayer()) {
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
					id = "PERSONALITY_TRAIT_"+trait;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(!BodyChanging.getTarget().hasPersonalityTrait(trait)) {
								BodyChanging.getTarget().addPersonalityTrait(trait);
							} else {
								BodyChanging.getTarget().removePersonalityTrait(trait);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence("<b style='color:"+trait.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(trait.getName())+"</b>"),
								trait.getDescription(BodyChanging.getTarget(), true, true)),
								false);
					}
				}
				
				// Obedience:
				for(ObedienceLevel obedience : ObedienceLevel.values()) {
					id = "OBEDIENCE_LEVEL_"+obedience;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setObedience(obedience.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Affection:
				for(AffectionLevel affection : AffectionLevel.values()) {
					id = "AFFECTION_LEVEL_"+affection;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAffection(Main.game.getPlayer(), affection.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
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
				
				// Age category:
				for(AgeCategory ageCategory : AgeCategory.values()) {
					id = "AGE_CATEGORY_"+ageCategory;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							int age = 18;
							Month month = Util.randomItemFrom(Month.values());
							BodyChanging.getTarget().setBirthday(LocalDateTime.of(Main.game.getStartingDate().getYear()-age, month, 1+Util.random.nextInt(27), 12, 0));
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Fetishes:
				for(AbstractFetish fetish : Fetish.getAllFetishes()) {
					for(FetishDesire desire : FetishDesire.values()) {
						id = "FETISH_DESIRE_"+Fetish.getIdFromFetish(fetish)+desire;
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								if(desire==FetishDesire.FOUR_LOVE) {
									BodyChanging.getTarget().addFetish(fetish);
								} else {
									BodyChanging.getTarget().removeFetish(fetish);
									BodyChanging.getTarget().setFetishDesire(fetish, desire);
								}
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
				}
				
				
				// Age:
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

				for(HornLength antennaLength : HornLength.values()) {
					id = "CHANGE_ANTENNA_LENGTH_"+antennaLength;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAntennaLength(antennaLength.getMedianValue());
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
				for(PenetrationGirth girth : PenetrationGirth.values()) {
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

				// Vagina squirter:
				id = "VAGINA_SQUIRTER_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setVaginaSquirter(true);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "VAGINA_SQUIRTER_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setVaginaSquirter(false);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}

				// Vagina hymen:
				id = "VAGINA_HYMEN_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setHymen(true);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "VAGINA_HYMEN_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setHymen(false);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				

				// Vagina egg-layer:
				id = "VAGINA_EGG_LAYER_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(!BodyChanging.getTarget().isPregnant()) {
							BodyChanging.getTarget().setVaginaEggLayer(true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
				}
				id = "VAGINA_EGG_LAYER_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(!BodyChanging.getTarget().isPregnant()) {
							BodyChanging.getTarget().setVaginaEggLayer(false);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
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
				
				// Vagina depth:
				for(OrificeDepth depth: OrificeDepth.values()) {
					id = "VAGINA_DEPTH_"+depth;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaDepth(depth.getValue());
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

				
				for(PenetrationModifier penMod : PenetrationModifier.getPenetrationModifiers(SexAreaPenetration.CLIT)) {
					id = "CHANGE_CLITORIS_MOD_"+penMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasClitorisModifier(penMod)) {
								BodyChanging.getTarget().removeClitorisModifier(penMod);
							} else {
								BodyChanging.getTarget().addClitorisModifier(penMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(penMod.getName()),
								(penMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+penMod.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
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

				id = "CLITORIS_SIZE_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementVaginaClitorisSize(1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CLITORIS_SIZE_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementVaginaClitorisSize(5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CLITORIS_SIZE_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementVaginaClitorisSize(-1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CLITORIS_SIZE_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementVaginaClitorisSize(-5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Clit girth:
				for(PenetrationGirth girth: PenetrationGirth.values()) {
					id = "CLITORIS_GIRTH_"+girth;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setClitorisGirth(girth.getValue());
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
				
				for(AbstractEyeType eyeType: EyeType.getAllEyeTypes()) {
					id = "CHANGE_EYE_"+EyeType.getIdFromEyeType(eyeType);
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
				
				for(AbstractAntennaType antennaType: AntennaType.getAllAntennaTypes()) {
					id = "CHANGE_ANTENNA_"+AntennaType.getIdFromAntennaType(antennaType);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAntennaType(antennaType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(AbstractHairType hairType: HairType.getAllHairTypes()) {
					id = "CHANGE_HAIR_"+HairType.getIdFromHairType(hairType);
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
				
				for(AbstractFaceType faceType: FaceType.getAllFaceTypes()) {
					id = "CHANGE_FACE_"+FaceType.getIdFromFaceType(faceType);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setFaceType(faceType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(AbstractTorsoType skinType: TorsoType.getAllTorsoTypes()) {
					id = "CHANGE_SKIN_"+TorsoType.getIdFromTorsoType(skinType);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTorsoType(skinType);
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
				
				for(BodyMaterial mat: BodyMaterial.values()) {
					id = "CHANGE_BODY_MATERIAL_"+mat;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBodyMaterial(mat);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(AbstractTailType tailType: TailType.getAllTailTypes()) {
					id = "CHANGE_TAIL_"+TailType.getIdFromTailType(tailType);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTailType(tailType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(AbstractWingType wingType: WingType.getAllWingTypes()) {
					id = "CHANGE_WING_"+WingType.getIdFromWingType(wingType);
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
				
				for(Capacity capacity: Capacity.values()) {
					id = "THROAT_CAPACITY_"+capacity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setFaceCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(Wetness wetness: Wetness.values()) {
					id = "THROAT_WETNESS_"+wetness;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setFaceWetness(wetness.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeDepth depth: OrificeDepth.values()) {
					id = "THROAT_DEPTH_"+depth;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setFaceDepth(depth.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "THROAT_ELASTICITY_"+elasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setFaceElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "THROAT_PLASTICITY_"+plasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setFacePlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
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

						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(orificeMod.getName()),
								(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
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


				// Antennae:

				for(int i=1; i <= Antenna.MAXIMUM_ROWS; i++) {
					MainController.setAntennaCountListener(i);
				}

				for(int i=1; i <= Antenna.MAXIMUM_ANTENNAE_PER_ROW; i++) {
					MainController.setAntennaePerRowCountListener(i);
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

				// Tail length:
				id = "TAIL_LENGTH_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(BodyChanging.getTarget().getLegConfiguration()==LegConfiguration.TAIL_LONG) {
							BodyChanging.getTarget().incrementLegTailLengthAsPercentageOfHeight(0.05f);
						} else {
							BodyChanging.getTarget().incrementTailLengthAsPercentageOfHeight(0.05f);
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "TAIL_LENGTH_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(BodyChanging.getTarget().getLegConfiguration()==LegConfiguration.TAIL_LONG) {
							BodyChanging.getTarget().incrementLegTailLengthAsPercentageOfHeight(0.25f);
						} else {
							BodyChanging.getTarget().incrementTailLengthAsPercentageOfHeight(0.25f);
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "TAIL_LENGTH_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(BodyChanging.getTarget().getLegConfiguration()==LegConfiguration.TAIL_LONG) {
							BodyChanging.getTarget().incrementLegTailLengthAsPercentageOfHeight(-0.05f);
						} else {
							BodyChanging.getTarget().incrementTailLengthAsPercentageOfHeight(-0.05f);
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "TAIL_LENGTH_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(BodyChanging.getTarget().getLegConfiguration()==LegConfiguration.TAIL_LONG) {
							BodyChanging.getTarget().incrementLegTailLengthAsPercentageOfHeight(-0.25f);
						} else {
							BodyChanging.getTarget().incrementTailLengthAsPercentageOfHeight(-0.25f);
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Tail girth:
				for(PenetrationGirth girth : PenetrationGirth.values()) {
					id = "TAIL_GIRTH_"+girth;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTailGirth(girth.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				// Tentacles:
				
				// Tentacle length:
				id = "TENTACLE_LENGTH_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementTentacleLengthAsPercentageOfHeight(0.05f);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "TENTACLE_LENGTH_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementTentacleLengthAsPercentageOfHeight(0.25f);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "TENTACLE_LENGTH_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementTentacleLengthAsPercentageOfHeight(-0.05f);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "TENTACLE_LENGTH_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementTentacleLengthAsPercentageOfHeight(-0.25f);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Tentacle girth:
				for(PenetrationGirth girth : PenetrationGirth.values()) {
					id = "TENTACLE_GIRTH_"+girth;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTentacleGirth(girth.getValue());
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

						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(orificeMod.getName()),
								(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
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
				
				for(OrificeDepth depth: OrificeDepth.values()) {
					id = "ANUS_DEPTH_"+depth;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssDepth(depth.getValue());
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

					if(BodyChanging.getTarget().getBreastRawSizeValue()<=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement() && BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.NIPPLE)!=null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								"[style.colourBad(Size Decrease Blocked)]",
								UtilText.parse(BodyChanging.getTarget(), "[npc.NamePos] breasts cannot be shrunk any further while eggs are being incubated in them!"),
								32);
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
				id = "BREAST_SIZE_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastSize(-5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);

					if(BodyChanging.getTarget().getBreastRawSizeValue()<=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement() && BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.NIPPLE)!=null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								"[style.colourBad(Size Decrease Blocked)]",
								UtilText.parse(BodyChanging.getTarget(), "[npc.NamePos] breasts cannot be shrunk any further while eggs are being incubated in them!"),
								32);
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
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

					if(BodyChanging.getTarget().getBreastCrotchRawSizeValue()<=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement() && BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH)!=null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								"[style.colourBad(Size Decrease Blocked)]",
								UtilText.parse(BodyChanging.getTarget(), "[npc.NamePos] [npc.crotchBoobs] cannot be shrunk any further while eggs are being incubated in them!"),
								32);
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
				id = "CROTCH_BOOB_SIZE_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastCrotchSize(-5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);

					if(BodyChanging.getTarget().getBreastCrotchRawSizeValue()<=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement() && BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH)!=null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								"[style.colourBad(Size Decrease Blocked)]",
								UtilText.parse(BodyChanging.getTarget(), "[npc.NamePos] [npc.crotchBoobs] cannot be shrunk any further while eggs are being incubated in them!"),
								32);
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
				
				
				for(int i=1; i <= Breast.MAXIMUM_BREAST_ROWS; i++) {
					MainController.setBreastCountListener(i);
				}
				for(int i=0; i <= BreastCrotch.MAXIMUM_BREAST_ROWS; i++) {
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
				
				// Nipple depth:
				for(OrificeDepth depth: OrificeDepth.values()) {
					id = "NIPPLE_DEPTH_"+depth;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleDepth(depth.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "NIPPLE_CROTCH_DEPTH_"+depth;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleCrotchDepth(depth.getValue());
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

						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(orificeMod.getName()),
								(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
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

						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(orificeMod.getName()),
								(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
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
					id = "MILK_CROTCH_FLAVOUR_"+flavour;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setMilkCrotchFlavour(flavour);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "GIRLCUM_FLAVOUR_"+flavour;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setGirlcumFlavour(flavour);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "CUM_FLAVOUR_"+flavour;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setCumFlavour(flavour);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(FluidModifier modifier : FluidModifier.values()) {
					id = "MILK_MODIFIER_"+modifier;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasMilkModifier(modifier)) {
								BodyChanging.getTarget().removeMilkModifier(modifier);
							} else {
								BodyChanging.getTarget().addMilkModifier(modifier);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(modifier.getName()),
								(modifier.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+modifier.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					id = "MILK_CROTCH_MODIFIER_"+modifier;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasMilkCrotchModifier(modifier)) {
								BodyChanging.getTarget().removeMilkCrotchModifier(modifier);
							} else {
								BodyChanging.getTarget().addMilkCrotchModifier(modifier);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(modifier.getName()),
								(modifier.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+modifier.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					id = "GIRLCUM_MODIFIER_"+modifier;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasGirlcumModifier(modifier)) {
								BodyChanging.getTarget().removeGirlcumModifier(modifier);
							} else {
								BodyChanging.getTarget().addGirlcumModifier(modifier);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(modifier.getName()),
								(modifier.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+modifier.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					id = "CUM_MODIFIER_"+modifier;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasCumModifier(modifier)) {
								BodyChanging.getTarget().removeCumModifier(modifier);
							} else {
								BodyChanging.getTarget().addCumModifier(modifier);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(modifier.getName()),
								(modifier.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+modifier.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
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
						BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_REGENERATION_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_REGENERATION_INCREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_REGENERATION_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_SMALL);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_REGENERATION_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_AVERAGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "MILK_CROTCH_REGENERATION_DECREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementBreastCrotchLactationRegeneration(-CharacterModificationUtils.FLUID_REGEN_INCREMENT_LARGE);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				
				// Vagina:
				
				for(AbstractVaginaType vaginaType: VaginaType.getAllVaginaTypes()) {
					id = "CHANGE_VAGINA_"+VaginaType.getIdFromVaginaType(vaginaType);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaType(vaginaType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					
						if(vaginaType==VaginaType.NONE
								&& (BodyChanging.getTarget().isPregnant() || BodyChanging.getTarget().hasStatusEffect(StatusEffect.PREGNANT_0) || BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.VAGINA)!=null)) {
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
									"[style.colourBad(Vagina Removal Blocked)]",
									UtilText.parse(BodyChanging.getTarget(),
											BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.VAGINA)!=null
												?"[npc.NamePos] vagina cannot be removed while eggs are being incubated in [npc.namePos] womb!"
												:(BodyChanging.getTarget().hasStatusEffect(StatusEffect.PREGNANT_0)
													?"[npc.NamePos] vagina cannot be removed while there is a chance that [npc.name] might be pregnant!"
													:"[npc.NamePos] vagina cannot be removed while [npc.nameIsFull] pregnant!")),
									32);
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
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

						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(orificeMod.getName()),
								(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
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

				// Vagina urethra depth:
				for(OrificeDepth depth: OrificeDepth.values()) {
					id = "VAGINA_URETHRA_DEPTH_"+depth;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaUrethraDepth(depth.getValue());
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

						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(orificeMod.getName()),
								(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
				
				
				// Penis:
				
				for(AbstractPenisType penisType: PenisType.getAllPenisTypes()) {
					id = "CHANGE_PENIS_"+PenisType.getIdFromPenisType(penisType);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisType(penisType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(PenetrationModifier penMod : PenetrationModifier.getPenetrationModifiers(SexAreaPenetration.PENIS)) {
					id = "CHANGE_PENIS_MOD_"+penMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasPenisModifier(penMod)) {
								BodyChanging.getTarget().removePenisModifier(penMod);
							} else {
								BodyChanging.getTarget().addPenisModifier(penMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(penMod.getName()),
								(penMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+penMod.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
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
						BodyChanging.getTarget().fillCumToMaxStorage();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumStorage(-CharacterModificationUtils.FLUID_INCREMENT_AVERAGE);
						BodyChanging.getTarget().fillCumToMaxStorage();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_DECREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumStorage(-CharacterModificationUtils.FLUID_INCREMENT_LARGE);
						BodyChanging.getTarget().fillCumToMaxStorage();
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
				
				for(OrificeDepth depth: OrificeDepth.values()) {
					id = "URETHRA_DEPTH_"+depth;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setUrethraDepth(depth.getValue());
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

						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(orificeMod.getName()),
								(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
				
			}

			// Spinneret:
			
			for(OrificeModifier orificeMod : OrificeModifier.values()) {
				id = "CHANGE_SPINNERET_MOD_"+orificeMod;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(BodyChanging.getTarget().hasSpinneretOrificeModifier(orificeMod)) {
							BodyChanging.getTarget().removeSpinneretOrificeModifier(orificeMod);
						} else {
							BodyChanging.getTarget().addSpinneretOrificeModifier(orificeMod);
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
							Util.capitaliseSentence(orificeMod.getName()),
							(orificeMod.isSpecialEffects()?"[style.boldGood(Special Effect:)] ":"")+orificeMod.getDescription());
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}

			for(Wetness wetness: Wetness.values()) {
				id = "SPINNERET_WETNESS_"+wetness;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setSpinneretWetness(wetness.getValue());
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			
			for(Capacity capacity: Capacity.values()) {
				id = "SPINNERET_CAPACITY_"+capacity;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setSpinneretCapacity(capacity.getMedianValue(), true);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			
			for(OrificeDepth depth: OrificeDepth.values()) {
				id = "SPINNERET_DEPTH_"+depth;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setSpinneretDepth(depth.getValue());
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			
			for(OrificeElasticity elasticity: OrificeElasticity.values()) {
				id = "SPINNERET_ELASTICITY_"+elasticity;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setSpinneretElasticity(elasticity.getValue());
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			
			for(OrificePlasticity plasticity: OrificePlasticity.values()) {
				id = "SPINNERET_PLASTICITY_"+plasticity;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setSpinneretPlasticity(plasticity.getValue());
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}

			
			
			
			// -------------------- Cosmetics -------------------- //
			

			boolean noCost = !Main.game.isInNewWorld()
								|| ScarlettsShop.isSlaveCustomisationMenu()
								|| Main.game.getCurrentDialogueNode()==MiscDialogue.getMakeupDialogueForEqualityCheck()
								|| Main.game.getCurrentDialogueNode()==RoomPlayer.AUNT_HOME_PLAYERS_ROOM_MAKEUP
								|| Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.PHONE;
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.coveringChangeListenersRequired)) {
				for(AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
					id = "APPLY_COVERING_"+BodyCoveringType.getIdFromBodyCoveringType(bct);
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
											
											if(BodyChanging.getTarget().isPlayer() && bct == BodyCoveringType.HUMAN) { // Start of game should reset skin colourings for all parts
												BodyChanging.getTarget().getBody().updateCoverings(false, false, false, true);
											}
										}
									}
								});
							}
						}, false);
					}
					id = "RESET_COVERING_"+BodyCoveringType.getIdFromBodyCoveringType(bct);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getBodyCoveringTypeCost(bct) || noCost) {
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										CharacterModificationUtils.getCoveringsToBeApplied().remove(bct);
									}
								});
							}
						}, false);
					}
					
					
					id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_PRIMARY_GLOW_OFF";
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
					
					id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_PRIMARY_GLOW_ON";
					
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
					
					id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_SECONDARY_GLOW_OFF";
					
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
					
					id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_SECONDARY_GLOW_ON";
					
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
						id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_PATTERN_"+pattern;
						
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
						id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_MODIFIER_"+modifier;
						
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
						id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_PRIMARY_"+colour.getId();
						
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
										CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryColour(colour);
										CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing((colour != PresetColour.COVERING_NONE && BodyChanging.getTarget().getCovering(bct).isPrimaryGlowing()));
									}
								});
							}, false);
						}
					}
					for(Colour colour : bct.getAllSecondaryColours()) {
						id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_SECONDARY_"+colour.getId();
						
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
										CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryColour(colour);
										CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(colour != PresetColour.COVERING_NONE && BodyChanging.getTarget().getCovering(bct).isSecondaryGlowing());
									}
								});
							}, false);
						}
					}
				}
				
				id = "MAKEUP_LIPSTICK_HEAVY_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								BodyChanging.getTarget().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
							}
						});
					}, false);
				}
				id = "MAKEUP_LIPSTICK_HEAVY_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								BodyChanging.getTarget().removeHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
							}
						});
					}, false);
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
			
			id = "NECK_FLUFF_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
						@Override
						public void effects() {
							BodyChanging.getTarget().setNeckFluff(true);
						}
					});
				}, false);
			}
			id = "NECK_FLUFF_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
						@Override
						public void effects() {
							BodyChanging.getTarget().setNeckFluff(false);
						}
					});
				}, false);
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
					|| Main.game.getCurrentDialogueNode()==CompanionManagement.SLAVE_MANAGEMENT_TATTOOS
					|| Main.game.getCurrentDialogueNode()==CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS
					|| Main.game.getCurrentDialogueNode()==CosmeticsDialogue.BEAUTICIAN_TATTOOS) {
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
												:(Main.game.getCurrentDialogueNode()==CosmeticsDialogue.BEAUTICIAN_TATTOOS
													?CosmeticsDialogue.BEAUTICIAN_TATTOOS_ADD
													:CompanionManagement.SLAVE_MANAGEMENT_TATTOOS_ADD))
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
					|| Main.game.getCurrentDialogueNode()==CompanionManagement.SLAVE_MANAGEMENT_TATTOOS_ADD
					|| Main.game.getCurrentDialogueNode()==CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD
					|| Main.game.getCurrentDialogueNode()==CosmeticsDialogue.BEAUTICIAN_TATTOOS_ADD) {
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
					id = "TATTOO_COLOUR_PRIMARY_"+c.getId();
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
					id = "TATTOO_COLOUR_SECONDARY_"+c.getId();
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
					id = "TATTOO_COLOUR_TERTIARY_"+c.getId();
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
					id = "TATTOO_WRITING_COLOUR_"+c.getId();
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
					id = "TATTOO_COUNTER_COLOUR_"+c.getId();
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
				
				// Clothing recolouring:
				AbstractClothingType clothing = InventoryDialogue.getClothing().getClothingType();
				for(int i=0; i<clothing.getColourReplacements().size(); i++) {
					int index = i;
					ColourReplacement cr = clothing.getColourReplacement(i);
					for(Colour c : cr.getAllColours()) {
						id = "DYE_CLOTHING_"+i+"_"+c.getId();
						if ((EventTarget) MainController.document.getElementById(id) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								if(cr.isRecolouringAllowed()) {
									InventoryDialogue.dyePreviews.remove(index);
									InventoryDialogue.dyePreviews.add(index, c);
								}
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setDyeClothing(InventoryDialogue.getClothing(), i, c);
							MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
						}
					}
				}

				// Clothing pattern selection:
				for(Pattern pattern : Pattern.getAllPatterns()) {
					id = "ITEM_PATTERN_"+pattern.getId();
					
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(!InventoryDialogue.dyePreviewPattern.equals(pattern.getId())){
								InventoryDialogue.dyePreviewPattern = pattern.getId();
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
				
				// Clothing pattern recolouring:
				for(int i=0; i<clothing.getPatternColourReplacements().size(); i++) {
					int index = i;
					ColourReplacement cr = clothing.getPatternColourReplacement(i);
					for(Colour c : cr.getAllColours()) {
						id = "DYE_CLOTHING_PATTERN_"+i+"_"+c.getId();
						if ((EventTarget) MainController.document.getElementById(id) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								InventoryDialogue.dyePreviewPatterns.remove(index);
								InventoryDialogue.dyePreviewPatterns.add(index, c);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
//							TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setDyeClothingPattern(InventoryDialogue.getClothing(), Pattern.getPattern(InventoryDialogue.dyePreviewPattern));
							TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setDyeClothingPattern(InventoryDialogue.getClothing(), i, c);
							MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
						}
					}
				}

				// Clothing sticker selection:
				for(Entry<StickerCategory, List<Sticker>> stickerEntry : clothing.getStickers().entrySet()) {
					for(Sticker s : stickerEntry.getValue()) {
						id = "ITEM_STICKER_"+stickerEntry.getKey().getId()+s.getId();
						String requirements = UtilText.parse(s.getUnavailabilityText()).trim();
						
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								if(InventoryDialogue.dyePreviewStickers.get(stickerEntry.getKey())!=s && requirements.isEmpty()){
									InventoryDialogue.dyePreviewStickers.put(stickerEntry.getKey(), s);
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
								}
							}, false);

							int lineHeight = 0;
							StringBuilder tooltipDescriptionSB = new StringBuilder();
							
							if(!requirements.isEmpty()) {
								tooltipDescriptionSB.append("[style.boldBad(Locked:)] <i>"+requirements+"</i><br/>");
								lineHeight+=2;
								
							} else {
								if(!s.getAvailabilityText().isEmpty()) {
									tooltipDescriptionSB.append("[style.boldGood(Unlocked:)] <i>"+s.getAvailabilityText()+"</i><br/>");
									lineHeight+=2;
								}
								
								boolean tagApplicationFound = false;
								for(ItemTag tag : s.getTagsApplied()) {
									for(String tagTooltip : tag.getClothingTooltipAdditions()) {
										if(!tagApplicationFound) {
											tooltipDescriptionSB.append("[style.boldMinorGood(Effects added:)]<br/>");
											tagApplicationFound = true;
											lineHeight++;
										}
										tooltipDescriptionSB.append(tagTooltip+"<br/>");
										lineHeight++;
									}
								}
								
								tagApplicationFound = false;
								for(ItemTag tag : s.getTagsRemoved()) {
									for(String tagTooltip : tag.getClothingTooltipAdditions()) {
										if(!tagApplicationFound) {
											tooltipDescriptionSB.append("[style.boldMinorBad(Effects removed:)]<br/>");
											tagApplicationFound = true;
											lineHeight++;
										}
										tooltipDescriptionSB.append(tagTooltip+"<br/>");
										lineHeight++;
									}
								}
							}
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							if(lineHeight>0) {
								TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation(
										"Special Effects",
										tooltipDescriptionSB.toString(),
										(lineHeight*16));
								MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
								
							} else {
								TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("No Special Effects", "");
								MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
							}
						}
					}
				}
			}
			
			if(Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_WEAPON
					|| Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_EQUIPPED_WEAPON) {
				AbstractWeaponType weapon = InventoryDialogue.getWeapon().getWeaponType();
				for(int i=0; i<weapon.getColourReplacements(false).size(); i++) {
					int index = i;
					ColourReplacement cr = weapon.getColourReplacement(false, i);
					for(Colour c : cr.getAllColours()) {
						id = "DYE_WEAPON_"+i+"_"+c.getId();
						if ((EventTarget) MainController.document.getElementById(id) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								if(cr.isRecolouringAllowed()) {
									InventoryDialogue.dyePreviews.remove(index);
									InventoryDialogue.dyePreviews.add(index, c);
								}
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setDyeWeapon(InventoryDialogue.getWeapon(), i, c);
							MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
						}
					}
				}
				for (DamageType dt : weapon.getAvailableDamageTypes()) {
					id = "DAMAGE_TYPE_"+dt.toString();
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
				if ((EventTarget) MainController.document.getElementById(clothing.getId()) != null) {
					MainController.addEventListener(MainController.document, clothing.getId(), "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, clothing.getId(), "mouseleave", MainController.hideTooltipListener, false);
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setGenericClothing(clothing);
					MainController.addEventListener(MainController.document, clothing.getId(), "mouseenter", el2, false);
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
				if ((EventTarget) MainController.document.getElementById(weapon.getId()) != null) {
					MainController.addEventListener(MainController.document, weapon.getId(), "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, weapon.getId(), "mouseleave", MainController.hideTooltipListener, false);
					TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setGenericWeapon(weapon, weapon.getAvailableDamageTypes().get(0));
					MainController.addEventListener(MainController.document, weapon.getId(), "mouseenter", el2, false);
				}
				for (DamageType dt : weapon.getAvailableDamageTypes()) {
					if ((EventTarget) MainController.document.getElementById(weapon.getId() + "_" + dt.toString()) != null) {
						MainController.addEventListener(MainController.document, weapon.getId() + "_" + dt.toString(), "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, weapon.getId() + "_" + dt.toString(), "mouseleave", MainController.hideTooltipListener, false);
						TooltipInventoryEventListener el2 = new TooltipInventoryEventListener().setGenericWeapon(weapon, dt);
						MainController.addEventListener(MainController.document, weapon.getId() + "_" + dt.toString(), "mouseenter", el2, false);
					}
				}
			}
			
			if (Main.game.getCurrentDialogueNode() == SpellManagement.CHARACTER_SPELLS_ARCANE
					|| Main.game.getCurrentDialogueNode() == SpellManagement.CHARACTER_SPELLS_EARTH
					|| Main.game.getCurrentDialogueNode() == SpellManagement.CHARACTER_SPELLS_WATER
					|| Main.game.getCurrentDialogueNode() == SpellManagement.CHARACTER_SPELLS_AIR
					|| Main.game.getCurrentDialogueNode() == SpellManagement.CHARACTER_SPELLS_FIRE
					|| Main.game.getCurrentDialogueNode() == SpellManagement.CHARACTER_SPELLS_MISC) {
				for(Spell s : Spell.values()) {
					id = "SPELL_TREE_" + s;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setSpell(s, SpellManagement.getSpellOwner()), false);
					}
					id = "SPELL_TREE_CAST_" + s;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						Value<Boolean, String> useDec = s.getSpellCastOutOfCombatDescription(SpellManagement.getSpellOwner(), SpellManagement.getSpellTarget());
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setInformation(
								"Cast "+s.getName(),
								useDec.getKey()
									?useDec.getValue()
									:"[style.italicsBad("+useDec.getValue()+")]"),
								false);
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event -> {
							if(s.getSpellCastOutOfCombatDescription(SpellManagement.getSpellOwner(), SpellManagement.getSpellTarget()).getKey()) {
								Main.game.setContent(new Response("", "", SpellManagement.castSpell(s)));
							}
						}, false);
					}
					for(List<TreeEntry<SpellSchool, SpellUpgrade>> upgradeList : s.getSpellUpgradeTree().values()) {
						for(TreeEntry<SpellSchool, SpellUpgrade> upgrade : upgradeList) {
							id = "SPELL_UPGRADE_" + upgrade.getEntry();
							if (((EventTarget) MainController.document.getElementById(id)) != null) {
								MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setSpellUpgrade(upgrade.getEntry(), SpellManagement.getSpellOwner()), false);
								
								((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event -> {
									if(Spell.isSpellUpgradeAvailable(SpellManagement.getSpellOwner(), s, upgrade) && SpellManagement.getSpellOwner().getSpellUpgradePoints(upgrade.getCategory())>=upgrade.getEntry().getPointCost()) {
										SpellManagement.getSpellOwner().addSpellUpgrade(upgrade.getEntry());
										SpellManagement.getSpellOwner().incrementSpellUpgradePoints(upgrade.getCategory(), -upgrade.getEntry().getPointCost());
										Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
									}
								}, false);
							}
						}
					}
				}
			}

			
			for(AbstractPerk perk : Perk.getAllPerks()) {
				GameCharacter character = CharactersPresentDialogue.characterViewed;
				if(Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_PERK_TREE || Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_APPEARANCE) {
					character = Main.game.getPlayer();
				} else if(Main.game.getCurrentDialogueNode() == CompanionManagement.SLAVE_MANAGEMENT_PERKS) {
					character = OccupantManagementDialogue.characterSelected();
				} else if(Main.game.getCurrentDialogueNode() == ElementalDialogue.ELEMENTAL_PERKS) {
					character = Main.game.getPlayer().getElemental();
				}
				
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
							GameCharacter stupidJava = character;
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event -> {
								stupidJava.removeTrait(perk);
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


			for(AbstractCombatMove move : CombatMove.getAllCombatMoves()) {
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
					|| Main.game.getCurrentDialogueNode() == CompanionManagement.SLAVE_MANAGEMENT_PERKS
					|| Main.game.getCurrentDialogueNode() == CharactersPresentDialogue.PERKS
					|| Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_APPEARANCE
					|| Main.game.getCurrentDialogueNode() == CharactersPresentDialogue.MENU
					|| Main.game.getCurrentDialogueNode() == PhoneDialogue.CONTACTS_CHARACTER
					|| Main.game.getCurrentDialogueNode() == ElementalDialogue.ELEMENTAL_PERKS) {
				
				GameCharacter character = CharactersPresentDialogue.characterViewed;
				if(Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_PERK_TREE || Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_APPEARANCE) {
					character = Main.game.getPlayer();
				} else if(Main.game.getCurrentDialogueNode() == CompanionManagement.SLAVE_MANAGEMENT_PERKS) {
					character = OccupantManagementDialogue.characterSelected();
				} else if(Main.game.getCurrentDialogueNode() == ElementalDialogue.ELEMENTAL_PERKS) {
					character = Main.game.getPlayer().getElemental();
				}
				
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
									GameCharacter javaSucks = character;
									((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event -> {
										if(e.getEntry().isEquippableTrait() && PerkManager.MANAGER.isPerkOwned(javaSucks, e)) {
											if(!javaSucks.hasTraitActivated(e.getEntry())) {
												javaSucks.addTrait(e.getEntry());
											} else {
												javaSucks.removeTrait(e.getEntry());
											}
											Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
											
										} else if((javaSucks.getPerkPoints()>=1
													|| javaSucks.getAdditionalPerkCategoryPoints(e.getCategory()) > Math.max(0, javaSucks.getPerksInCategory(e.getCategory())-PerkManager.getInitialPerkCount(javaSucks, e.getCategory())))
												&& PerkManager.MANAGER.isPerkAvailable(javaSucks, e)) {
											if(javaSucks.addPerk(e.getRow(), e.getEntry())) {
												if(e.getEntry().isEquippableTrait() && javaSucks.getTraits().size()<GameCharacter.MAX_TRAITS) {
													javaSucks.addTrait(e.getEntry());
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
			if (Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_FETISHES || Main.game.getCurrentDialogueNode() == ElementalDialogue.ELEMENTAL_FETISHES) {
				GameCharacter targetedCharacter;
				if(Main.game.getCurrentDialogueNode() == ElementalDialogue.ELEMENTAL_FETISHES) {
					targetedCharacter = Main.game.getPlayer().getElemental();
				} else {
					targetedCharacter = Main.game.getPlayer();
				}
				for (AbstractFetish f : Fetish.getAllFetishes()) {
					id = "fetishUnlock" + Fetish.getIdFromFetish(f);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(Main.game.getPlayer().getEssenceCount()>=f.getCost() && f.getFetishesForAutomaticUnlock().isEmpty()) {
								if(!targetedCharacter.hasFetish(f)) {
									targetedCharacter.addFetish(f);
									targetedCharacter.incrementEssenceCount(-f.getCost(), false);
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
								}
							}
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setFetish(f, targetedCharacter), false);
					}
					
					id = Fetish.getIdFromFetish(f)+"_EXPERIENCE";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setFetishExperience(f, targetedCharacter), false);
					}
					
					for (FetishDesire desire : FetishDesire.values()) {
						id = Fetish.getIdFromFetish(f)+"_"+desire;
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								if(Main.game.getPlayer().getEssenceCount()>=FetishDesire.getCostToChange()) {
									if(targetedCharacter.getBaseFetishDesire(f)!=desire) {
										targetedCharacter.incrementEssenceCount(-FetishDesire.getCostToChange(), false);
										targetedCharacter.setFetishDesire(f, desire);
										Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
									}
								}
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setFetishDesire(f, desire, targetedCharacter), false);
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
				AbstractWorldType worldType = PhoneDialogue.worldTypeMap;
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
						if(type.isAvailable(null, Main.game.getPlayer())) {
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
											+(type.isAvailable(null, Main.game.getPlayer())
													?" ("+type.getUseInstructions()+")"
													:"<br/>[style.italicsBad("+type.getUnavailablilityDescription(null, Main.game.getPlayer())+")]")),
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
		
		// NPC Fetish spawn preferences:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.FETISH_PREFERENCE) {
			for (AbstractFetish f : Fetish.getAllFetishes()) {
				if(!Main.game.isPenetrationLimitationsEnabled() && f == Fetish.FETISH_SIZE_QUEEN) {
					continue;
				}
				if(!Main.game.isNonConEnabled() && (f == Fetish.FETISH_NON_CON_DOM || f == Fetish.FETISH_NON_CON_SUB)) {
					continue;
				}
				if(!Main.game.isIncestEnabled() && f == Fetish.FETISH_INCEST) {
					continue;
				}
				if(!Main.game.isLactationContentEnabled() && (f == Fetish.FETISH_LACTATION_OTHERS || f == Fetish.FETISH_LACTATION_SELF)) {
					continue;
				}
				if(!Main.game.isAnalContentEnabled() && (f == Fetish.FETISH_ANAL_GIVING || f == Fetish.FETISH_ANAL_RECEIVING)) {
					continue;
				}
				if(!Main.game.isFootContentEnabled() && (f == Fetish.FETISH_FOOT_GIVING || f == Fetish.FETISH_FOOT_RECEIVING)) {
					continue;
				}
				
				for(FetishPreference preference : FetishPreference.values()) {
					id=preference+"_"+Fetish.getIdFromFetish(f);
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.getProperties().fetishPreferencesMap.put(f, preference.getValue());
							Main.saveProperties();
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(
							Util.capitaliseSentence(preference.getName()),
							preference.getTooltip());
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
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
			
			for(AbstractSubspecies s : Subspecies.getAllSubspecies()) {
				id="SUBSPECIES_PREFERENCE_INFO_"+Subspecies.getIdFromSubspecies(s);

				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(s.getName(null)), s.getDescription(null));
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
			
			// Human spawn rates:
			id = "HUMAN_SPAWN_RATE_INCREASE_LARGE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().humanSpawnRate+=25;
					Main.getProperties().humanSpawnRate = Math.max(0, Math.min(Main.getProperties().humanSpawnRate, 100));
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Large Increase", "Increase the rate at which NPCs spawn as full humans. (Default value is 5%.)", 24);
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			id = "HUMAN_SPAWN_RATE_INCREASE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().humanSpawnRate+=5;
					Main.getProperties().humanSpawnRate = Math.max(0, Math.min(Main.getProperties().humanSpawnRate, 100));
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Increase", "Increase the rate at which NPCs spawn as full humans. (Default value is 5%.)", 24);
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			id = "HUMAN_SPAWN_RATE_DECREASE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().humanSpawnRate-=5;
					Main.getProperties().humanSpawnRate = Math.max(0, Math.min(Main.getProperties().humanSpawnRate, 100));
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Decrease", "Decrease the rate at which NPCs spawn as full humans. (Default value is 5%.)", 24);
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			id = "HUMAN_SPAWN_RATE_DECREASE_LARGE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().humanSpawnRate-=25;
					Main.getProperties().humanSpawnRate = Math.max(0, Math.min(Main.getProperties().humanSpawnRate, 100));
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Large Decrease", "Decrease the rate at which NPCs spawn as full humans. (Default value is 5%.)", 24);
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}

			// Taur spawn rates:
			id = "TAUR_SPAWN_RATE_INCREASE_LARGE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().taurSpawnRate+=25;
					Main.getProperties().taurSpawnRate = Math.max(0, Math.min(Main.getProperties().taurSpawnRate, 100));
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Large Increase", "Increase the rate at which non-human NPCs spawn with a tauric lower body. (Default value is 5%.)", 24);
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			id = "TAUR_SPAWN_RATE_INCREASE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().taurSpawnRate+=5;
					Main.getProperties().taurSpawnRate = Math.max(0, Math.min(Main.getProperties().taurSpawnRate, 100));
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Increase", "Increase the rate at which non-human NPCs spawn with a tauric lower body. (Default value is 5%.)", 24);
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			id = "TAUR_SPAWN_RATE_DECREASE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().taurSpawnRate-=5;
					Main.getProperties().taurSpawnRate = Math.max(0, Math.min(Main.getProperties().taurSpawnRate, 100));
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Decrease", "Decrease the rate at which non-human NPCs spawn with a tauric lower body. (Default value is 5%.)", 24);
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			id = "TAUR_SPAWN_RATE_DECREASE_LARGE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().taurSpawnRate-=25;
					Main.getProperties().taurSpawnRate = Math.max(0, Math.min(Main.getProperties().taurSpawnRate, 100));
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Large Decrease", "Decrease the rate at which non-human NPCs spawn with a tauric lower body. (Default value is 5%.)", 24);
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			
			// Human spawn rates:
			id = "HALF_DEMON_SPAWN_RATE_INCREASE_LARGE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().halfDemonSpawnRate+=25;
					Main.getProperties().halfDemonSpawnRate = Math.max(0, Math.min(Main.getProperties().halfDemonSpawnRate, 100));
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Large Increase", "Increase the rate at which NPCs spawn as half-demons. (Default value is 5%.)", 24);
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			id = "HALF_DEMON_SPAWN_RATE_INCREASE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().halfDemonSpawnRate+=5;
					Main.getProperties().halfDemonSpawnRate = Math.max(0, Math.min(Main.getProperties().halfDemonSpawnRate, 100));
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Increase", "Increase the rate at which NPCs spawn as half-demons. (Default value is 5%.)", 24);
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			id = "HALF_DEMON_SPAWN_RATE_DECREASE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().halfDemonSpawnRate-=5;
					Main.getProperties().halfDemonSpawnRate = Math.max(0, Math.min(Main.getProperties().halfDemonSpawnRate, 100));
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Decrease", "Decrease the rate at which NPCs spawn as half-demons. (Default value is 5%.)", 24);
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			id = "HALF_DEMON_SPAWN_RATE_DECREASE_LARGE";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().halfDemonSpawnRate-=25;
					Main.getProperties().halfDemonSpawnRate = Math.max(0, Math.min(Main.getProperties().halfDemonSpawnRate, 100));
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Large Decrease", "Decrease the rate at which NPCs spawn as half-demons. (Default value is 5%.)", 24);
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
			}
			
			
			// Taur furry spawns:
			for(int i=0; i<=5; i++) {
				int index = i;
				id = "TAUR_FURRY_LIMIT_"+index;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.getProperties().taurFurryLevel=index;
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(Properties.taurFurryLevelName[index], Properties.taurFurryLevelDescription[index]);
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
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
			for(FurryPreference preference : FurryPreference.values()) {
				id = "ALL_FURRY_"+preference;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						for (AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
							Main.getProperties().setFeminineFurryPreference(subspecies, preference);
							Main.getProperties().setMasculineFurryPreference(subspecies, preference);
						}
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			for(SubspeciesPreference preference : SubspeciesPreference.values()) {
				id = "ALL_SPAWN_"+preference;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						for (AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
							Main.getProperties().setFeminineSubspeciesPreference(subspecies, preference);
							Main.getProperties().setMasculineSubspeciesPreference(subspecies, preference);
						}
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			
			
			for (AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
				String subspeciesId = Subspecies.getIdFromSubspecies(subspecies);
				for(FurryPreference preference : FurryPreference.values()) {
					id = "FEMININE_" + preference+"_"+subspeciesId;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						if(subspecies.isFurryPreferencesEnabled()) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.getProperties().setFeminineFurryPreference(subspecies, preference);
								Main.saveProperties();
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(
								preference.getName(),
								subspecies.isFurryPreferencesEnabled()
									?preference.getDescriptionFeminine(subspecies)
									:"This subspecies cannot have its furry preference changed!"
								);
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					id = "MASCULINE_" + preference+"_"+subspeciesId;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(subspecies.isFurryPreferencesEnabled()) {
								Main.getProperties().setMasculineFurryPreference(subspecies, preference);
								Main.saveProperties();
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
	
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(
								preference.getName(),
								subspecies.isFurryPreferencesEnabled()
									?preference.getDescriptionMasculine(subspecies)
									:"This subspecies cannot have its furry preference changed!");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
				for(SubspeciesPreference preference : SubspeciesPreference.values()) {
					id = "FEMININE_SPAWN_" + preference+"_"+subspeciesId;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						if(subspecies.isSpawnPreferencesEnabled()) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.getProperties().setFeminineSubspeciesPreference(subspecies, preference);
								Main.saveProperties();
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
	
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(preference.getName()),
								subspecies.isSpawnPreferencesEnabled()
									?"Set the weighted chance for feminine genders of this subspecies to spawn. The spawn frequency of '"+preference.getName()+"' has a weight of: <b>"+preference.getValue()+"</b><br/>"
										+ "<i>This weighting is further affected by map-specific spawn frequencies.</i>"
									:"This subspecies cannot have its spawn preference changed!");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					id = "MASCULINE_SPAWN_" + preference+"_"+subspeciesId;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						if(subspecies.isSpawnPreferencesEnabled()) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.getProperties().setMasculineSubspeciesPreference(subspecies, preference);
								Main.saveProperties();
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(
								Util.capitaliseSentence(preference.getName()),
								subspecies.isSpawnPreferencesEnabled()
									?"Set the weighted chance for masculine genders of this subspecies to spawn. The spawn frequency of '"+preference.getName()+"' has a weight of: <b>"+preference.getValue()+"</b><br/>"
										+ "<i>This weighting is further affected by map-specific spawn frequencies.</i>"
									:"This subspecies cannot have its spawn preference changed!");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
			}
		}

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
				|| Main.game.getCurrentDialogueNode() == CharacterCreation.CONTENT_PREFERENCE
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

			// bypass sex action options:
			for(int i=0; i<3; i++) {
				id = "BYPASS_SEX_ACTIONS_"+i;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					setBypassSexActionsPreference(id, i);
				}
			}
			
			Map<String, PropertyValue> settingsMap = Util.newHashMapOfValues(
					new Value<>("ENCHANTMENT_LIMITS", PropertyValue.enchantmentLimits),
					new Value<>("LEVEL_DRAIN", PropertyValue.levelDrain),
					new Value<>("ARTWORK", PropertyValue.artwork),
					new Value<>("THUMBNAIL", PropertyValue.thumbnail),
					new Value<>("SILLY", PropertyValue.sillyMode),
					new Value<>("WEATHER_INTERRUPTION", PropertyValue.weatherInterruptions),
					new Value<>("DIALOGUE_COPY", PropertyValue.automaticDialogueCopy),
					new Value<>("AUTO_SEX_CLOTHING_STRIP", PropertyValue.autoSexStrip),
					new Value<>("AUTO_SEX_CLOTHING_MANAGEMENT", PropertyValue.autoSexClothingManagement),
					new Value<>("NON_CON", PropertyValue.nonConContent),
					new Value<>("SADISTIC_SEX", PropertyValue.sadisticSexContent),
					new Value<>("FERAL", PropertyValue.feralContent),
					new Value<>("VOLUNTARY_NTR", PropertyValue.voluntaryNTR),
					new Value<>("INVOLUNTARY_NTR", PropertyValue.involuntaryNTR),
					new Value<>("INCEST", PropertyValue.incestContent),
					new Value<>("LACTATION", PropertyValue.lactationContent),
					new Value<>("CUM_REGENERATION", PropertyValue.cumRegenerationContent),
					new Value<>("URETHRAL", PropertyValue.urethralContent),
					new Value<>("NIPPLE_PEN", PropertyValue.nipplePenContent),
					new Value<>("HAIR_FACIAL", PropertyValue.facialHairContent),
					new Value<>("ANAL", PropertyValue.analContent),
					new Value<>("GAPE", PropertyValue.gapeContent),
					new Value<>("PENETRATION_LIMITATION", PropertyValue.penetrationLimitations),
					new Value<>("PENETRATION_LIMITATION_DYNAMIC", PropertyValue.elasticityAffectDepth),
					new Value<>("FOOT", PropertyValue.footContent),
					new Value<>("ARMPIT", PropertyValue.armpitContent),
					new Value<>("FUTA_BALLS", PropertyValue.futanariTesticles),
					new Value<>("CLOACA", PropertyValue.bipedalCloaca),
					new Value<>("VESTIGIAL_MULTI_BREAST", PropertyValue.vestigialMultiBreasts),
					new Value<>("COMPANION", PropertyValue.companionContent),
					new Value<>("BAD_END", PropertyValue.badEndContent),
					new Value<>("AGE", PropertyValue.ageContent),
					new Value<>("LIPSTICK_MARKING", PropertyValue.lipstickMarkingContent),
					new Value<>("HAIR_PUBIC", PropertyValue.pubicHairContent),
					new Value<>("HAIR_BODY", PropertyValue.bodyHairContent),
					new Value<>("HAIR_ASS", PropertyValue.assHairContent),
					new Value<>("FEMININE_BEARD", PropertyValue.feminineBeardsContent),
					new Value<>("FURRY_HAIR", PropertyValue.furryHairContent),
					new Value<>("SCALY_HAIR", PropertyValue.scalyHairContent),
					new Value<>("FURRY_TAIL_PENETRATION", PropertyValue.furryTailPenetrationContent),
					new Value<>("INFLATION_CONTENT", PropertyValue.inflationContent),
					new Value<>("SPITTING_ENABLED", PropertyValue.spittingEnabled),
					new Value<>("OPPORTUNISTIC_ATTACKERS", PropertyValue.opportunisticAttackers),
					new Value<>("SHARED_ENCYCLOPEDIA", PropertyValue.sharedEncyclopedia)
					);
			
			for(Entry<String, PropertyValue> entry : settingsMap.entrySet()) {
				createToggleListener(entry.getKey()+"_ON", entry.getValue(), true);
				createToggleListener(entry.getKey()+"_OFF", entry.getValue(), false);
			}
			
			
			id = "PREGNANCY_DURATION_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyDuration = Math.min(40, Main.getProperties().pregnancyDuration+1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_DURATION_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyDuration = Math.max(1, Main.getProperties().pregnancyDuration-1);
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
			id = "PREGNANCY_BREAST_GROWTH_UDDERS_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyUdderGrowth = Math.min(10, Main.getProperties().pregnancyUdderGrowth+1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_BREAST_GROWTH_UDDERS_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyUdderGrowth = Math.max(0, Main.getProperties().pregnancyUdderGrowth-1);
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
			id = "PREGNANCY_BREAST_GROWTH_LIMIT_UDDERS_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyUdderGrowthLimit = Math.min(CupSize.getCupSizeFromInt(100).getMeasurement(), Main.getProperties().pregnancyUdderGrowthLimit+1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_BREAST_GROWTH_LIMIT_UDDERS_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyUdderGrowthLimit = Math.max(0, Main.getProperties().pregnancyUdderGrowthLimit-1);
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
			id = "PREGNANCY_LACTATION_UDDERS_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyUdderLactationIncrease = Math.min(1000, Main.getProperties().pregnancyUdderLactationIncrease+50);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_LACTATION_UDDERS_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyUdderLactationIncrease = Math.max(0, Main.getProperties().pregnancyUdderLactationIncrease-50);
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
			id = "PREGNANCY_LACTATION_LIMIT_UDDERS_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyUdderLactationLimit = Math.min(Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue(), Main.getProperties().pregnancyUdderLactationLimit+500);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_LACTATION_LIMIT_UDDERS_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyUdderLactationLimit = Math.max(0, Main.getProperties().pregnancyUdderLactationLimit-500);
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
			id = "BREAST_SIZE_PREFERENCE_UDDERS_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().udderSizePreference = Math.min(20, Main.getProperties().udderSizePreference+1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "BREAST_SIZE_PREFERENCE_UDDERS_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().udderSizePreference = Math.max(-20, Main.getProperties().udderSizePreference-1);
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

			
			id = "TRAP_PENIS_SIZE_PREFERENCE_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().trapPenisSizePreference = Math.min(100, Main.getProperties().trapPenisSizePreference+10);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "TRAP_PENIS_SIZE_PREFERENCE_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().trapPenisSizePreference = Math.max(-90, Main.getProperties().trapPenisSizePreference-10);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}


			for(Colour colour : PresetColour.getHumanSkinColours()) {
				id = "SKIN_COLOUR_PREFERENCE_"+colour.getId()+"_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						int newValue = Math.min(10, Main.getProperties().skinColourPreferencesMap.get(colour)+1);
						Main.getProperties().skinColourPreferencesMap.put(colour, newValue);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "SKIN_COLOUR_PREFERENCE_"+colour.getId()+"_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						int newValue = Math.max(0, Main.getProperties().skinColourPreferencesMap.get(colour)-1);
						Main.getProperties().skinColourPreferencesMap.put(colour, newValue);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
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
			

			// Forced TF racial limits:
			id = "FORCED_TF_FURRY_LIMIT_"+FurryPreference.HUMAN;
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
			id = "FORCED_TF_FURRY_LIMIT_"+FurryPreference.MINIMUM;
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
			id = "FORCED_TF_FURRY_LIMIT_"+FurryPreference.REDUCED;
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
			id = "FORCED_TF_FURRY_LIMIT_"+FurryPreference.NORMAL;
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
			id = "FORCED_TF_FURRY_LIMIT_"+FurryPreference.MAXIMUM;
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setForcedTFPreference(FurryPreference.MAXIMUM);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation("Maximum Furry",
						"Forced transformations from NPCs will always give you non-human hair, ears, eyes, tails, horns, antenna, wings, breasts, genitalia, ass, arms, legs, skin, and face. (So everything will be affected.)");
				MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
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
			for (File f : Main.getSavedGames(false)) {
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
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							
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
							Main.game.flashMessage(PresetColour.GENERIC_GOOD, "Imported Character!");
						
						} catch(Exception ex) {
							Main.game.flashMessage(PresetColour.GENERIC_BAD, "Import Failed!");
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
		
		// Lodger import:
		if (Main.game.getCurrentDialogueNode() == CityHall.LODGER_IMPORT) {
			for (File f : Main.getSlavesForImport()) {
				String fileIdentifier = Util.getFileIdentifier(f);
				String fileName = Util.getFileName(f);
				
				if (((EventTarget) MainController.document.getElementById("import_lodger_" + fileIdentifier )) != null) {
					((EventTarget) MainController.document.getElementById("import_lodger_" + fileIdentifier )).addEventListener("click", e -> {
						try {
							Game.importCharacterAsLodger(fileName);
							MainController.updateUI();
							Main.game.flashMessage(PresetColour.GENERIC_GOOD, "Imported Character!");
						
						} catch(Exception ex) {
							Main.game.flashMessage(PresetColour.GENERIC_BAD, "Import Failed!");
						}
					}, false);
				}
			}
		}
		if (Main.game.getCurrentDialogueNode() == CityHall.CITY_HALL_WAITING_AREA_LODGER_LIST) {
			for (NPC npc : Main.game.getCharactersPresent()) {
				id = npc.getId()+"_LODGER";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						CityHall.setupLodger(npc);
						Main.game.setContent(new Response("", "", CityHall.CITY_HALL_APPROACH_LODGER));
					}, false);
					
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

					TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(
							UtilText.parse(npc, "Find [npc.name]"),
							UtilText.parse(npc, "Look around the waiting area and see if you can find [npc.name]..."));
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
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
							Main.game.setContent(new Response("Save/Load", "Open the save/load enchantment window.", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
							
						} else {
							EnchantmentDialogue.overwriteConfirmationName = f.getName();
							EnchantmentDialogue.loadConfirmationName = "";
							EnchantmentDialogue.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load enchantment window.", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
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
							AbstractCoreItem abstractItem = lEnch.getSuitableItem();
							EnchantmentDialogue.initModifiers(abstractItem);
							EnchantmentDialogue.getEffects().clear();
							for(ItemEffect ie : lEnch.getEffects()) {
								EnchantmentDialogue.addEffect(ie);
							}
							EnchantmentDialogue.setOutputName(lEnch.getName());
							Main.game.setContent(new Response("Save/Load", "Open the save/load enchantment window.", EnchantmentDialogue.ENCHANTMENT_MENU));
							
						} else {
							EnchantmentDialogue.overwriteConfirmationName = "";
							EnchantmentDialogue.loadConfirmationName = f.getName();
							EnchantmentDialogue.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load enchantment window.", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
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
		
		

		// Save/load body transformations:
		if (Main.game.getCurrentDialogueNode() == BodyChanging.BODY_CHANGING_SAVE_LOAD) {
			for (File f : BodyChanging.getSavedBodies()) {
				String fileIdentifier = Util.getFileIdentifier(f);
				String fileName = Util.getFileName(f);
				
				id = "overwrite_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || BodyChanging.overwriteConfirmationName.equals(f.getName())) {
							BodyChanging.overwriteConfirmationName = "";
							BodyChanging.saveBody(fileName, true);
							
						} else {
							BodyChanging.overwriteConfirmationName = f.getName();
							BodyChanging.loadConfirmationName = "";
							BodyChanging.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load transformation window.", BodyChanging.BODY_CHANGING_SAVE_LOAD));
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
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || BodyChanging.loadConfirmationName.equals(f.getName())) {
							BodyChanging.loadConfirmationName = "";
							Body loadedBody = BodyChanging.loadBody(fileName);
							BodyChanging.applyLoadedBody(loadedBody);
							Main.game.setContent(new Response("Save/Load", "Open the save/load transformation window.", BodyChanging.BODY_CHANGING_SAVE_LOAD));
							
						} else {
							BodyChanging.overwriteConfirmationName = "";
							BodyChanging.loadConfirmationName = f.getName();
							BodyChanging.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load transformation window.", BodyChanging.BODY_CHANGING_SAVE_LOAD));
						}
						
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Load",
							BodyChanging.isPresetTransformationAvailable(BodyChanging.loadBody(fileName))
								?""
								:BodyChanging.getPresetTransformationUnavailabilityText(BodyChanging.loadBody(fileName)));
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				id = "delete_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || BodyChanging.deleteConfirmationName.equals(f.getName())) {
							BodyChanging.deleteConfirmationName = "";
							BodyChanging.deleteBody(fileName);
							BodyChanging.initSaveLoadMenu();
							Main.game.setContent(new Response("Save/Load", ".", BodyChanging.BODY_CHANGING_SAVE_LOAD));
							
						} else {
							BodyChanging.overwriteConfirmationName = "";
							BodyChanging.loadConfirmationName = "";
							BodyChanging.deleteConfirmationName = f.getName();
							Main.game.setContent(new Response("Save/Load", ".", BodyChanging.BODY_CHANGING_SAVE_LOAD));
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
					BodyChanging.saveBody(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Save", "");
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
			
			for(Entry<String, Value<String, Body>> entry : BodyChanging.getPresetTransformationsMap().entrySet()) {
				id = "LOADED_BODY_"+entry.getKey();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setLoadedBody(entry.getValue().getValue(), BodyChanging.getTarget());
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
		}
		
		
		// Dice poker:
		if(Main.game.isStarted() && DicePoker.progress>0) {
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
				if(action != null) {
					action.run();
				}
				if(option.isFetishRelated() && Main.game.isStarted()) {
					Main.game.getPlayer().recalculateAvailableCombatMoves();
					Main.game.getPlayer().calculateSpecialFetishes();
					for(GameCharacter character : Main.game.getAllNPCs()) {
						character.recalculateAvailableCombatMoves();
						character.calculateSpecialFetishes();
					}
				}
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
			Main.getProperties().setUddersLevel(i);
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

	static void setBypassSexActionsPreference(String id, int i) {
		((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
			Main.getProperties().bypassSexActions=i;
			Main.saveProperties();
			Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
		}, false);

		MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
		MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
		TooltipInformationEventListener el = new TooltipInformationEventListener().setInformation(Properties.bypassSexActionsLabels[i], Properties.getBypassSexActionsDescriptions[i]);
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
											+ " Guiding the end of the tube #IF(pc.hasLegs())between your [pc.legs+]#ELSEto your groin#ENDIF, you waste no time in sliding the funnel into your [pc.pussy+]."
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
												+ (!MilkingRoom.getTargetedCharacter().isTaur()
													&& MilkingRoom.getTargetedCharacter().hasLegs()
													&& MilkingRoom.getTargetedCharacter().getGenitalArrangement()==GenitalArrangement.NORMAL
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
							ingestion = MilkingRoom.getTargetedCharacter().ingestFluid(fluid, area.getValue(), milkAmount);
						}
						if(!ingestion.isEmpty()) {
							Main.game.getTextEndStringBuilder().append("</p>"
									+ "<p>"
									+ ingestion);
						}
						Main.game.getTextEndStringBuilder().append("</p>");
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center;'>"
										+ "<i style='color:"+PresetColour.GENERIC_MINOR_BAD.toWebHexString()+";'>"+Units.fluid(milkAmount)+" of "+fluidOwnerName+" "+fluidName+" has been consumed!</i>"
								+ "</p>");
						
						room.incrementFluidStored(fluid, -milkAmount);
						
						Main.game.setContent(new Response("", "", LilayaMilkingRoomDialogue.MILKED));
						
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
