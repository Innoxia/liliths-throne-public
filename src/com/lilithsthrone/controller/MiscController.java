package com.lilithsthrone.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.w3c.dom.events.EventTarget;

import com.lilithsthrone.controller.eventListeners.EnchantmentEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInventoryEventListener;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.TreeEntry;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.moves.CombatMove;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.elemental.ElementalDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaDressingRoomDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.CombatMovesSetup;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.MapTravelType;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.SpellManagement;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.Sticker;
import com.lilithsthrone.game.inventory.clothing.StickerCategory;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

import javafx.scene.input.KeyCode;

/**
 * @since 0.4.6.4
 * @version 0.4.9.1
 * @author Maxis010, Innoxia
 */
public class MiscController {
	
	
	public static void initDollBrochureListeners() {
		String id = "DOLL_CORE_";
		for(int i=0; i<3; i++) {
			if (MainController.document.getElementById(id+i) != null) {
				int i2 = i;
				((EventTarget) MainController.document.getElementById(id+i)).addEventListener("click", e->{
					MiscDialogue.dollOption = i2;
					Main.game.setContent(new Response("", "", MiscDialogue.DOLL_BROCHURE_INTERNAL));
				}, false);
			}
		}
		id = "DOLL_GENITALS_";
		for(int i=0; i<3; i++) {
			if (MainController.document.getElementById(id+i) != null) {
				int i2 = i;
				((EventTarget) MainController.document.getElementById(id+i)).addEventListener("click", e->{
					MiscDialogue.genitalsOption = i2;
					Main.game.setContent(new Response("", "", MiscDialogue.DOLL_BROCHURE_INTERNAL));
				}, false);
			}
		}
		id = "DOLL_AGE_";
		for(int i=0; i<6; i++) {
			if (MainController.document.getElementById(id+i) != null) {
				int i2 = i;
				((EventTarget) MainController.document.getElementById(id+i)).addEventListener("click", e->{
					MiscDialogue.ageOption = i2;
					Main.game.setContent(new Response("", "", MiscDialogue.DOLL_BROCHURE_INTERNAL));
				}, false);
			}
		}
		id = "DOLL_CLOTHING_";
		for(int i=0; i<4; i++) {
			if (MainController.document.getElementById(id+i) != null) {
				int i2 = i;
				((EventTarget) MainController.document.getElementById(id+i)).addEventListener("click", e->{
					MiscDialogue.outfitOption = i2;
					Main.game.setContent(new Response("", "", MiscDialogue.DOLL_BROCHURE_INTERNAL));
				}, false);
			}
		}
		// Extra:
		id = "DOLL_BARCODE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				MiscDialogue.barcodeRemoval = !MiscDialogue.barcodeRemoval;
				Main.game.setContent(new Response("", "", MiscDialogue.DOLL_BROCHURE_INTERNAL));
			}, false);
		}
		id = "DOLL_TOYS";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				MiscDialogue.toySet = !MiscDialogue.toySet;
				Main.game.setContent(new Response("", "", MiscDialogue.DOLL_BROCHURE_INTERNAL));
			}, false);
		}
		id = "DOLL_HAIR";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				MiscDialogue.hair = !MiscDialogue.hair;
				Main.game.setContent(new Response("", "", MiscDialogue.DOLL_BROCHURE_INTERNAL));
			}, false);
		}
		id = "DOLL_DECK";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				MiscDialogue.deck = !MiscDialogue.deck;
				Main.game.setContent(new Response("", "", MiscDialogue.DOLL_BROCHURE_INTERNAL));
			}, false);
		}
		id = "DOLL_FUCKED";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				MiscDialogue.fucked = !MiscDialogue.fucked;
				Main.game.setContent(new Response("", "", MiscDialogue.DOLL_BROCHURE_INTERNAL));
			}, false);
		}
	}
	
	public static void initAlarmListeners() {
		String id = "PLAYER_ALARM_DECREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", -60);
				if (Main.game.getDialogueFlags().getSavedLong("player_phone_alarm")<0) {
					Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", 24*60);
				}
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "PLAYER_ALARM_DECREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", -5);
				if (Main.game.getDialogueFlags().getSavedLong("player_phone_alarm")<0) {
					Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", 24*60);
				}
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "PLAYER_ALARM_INCREASE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", 5);
				if (Main.game.getDialogueFlags().getSavedLong("player_phone_alarm")>=24*60) {
					Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", -24*60);
				}
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		id = "PLAYER_ALARM_INCREASE_LARGE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", 60);
				if (Main.game.getDialogueFlags().getSavedLong("player_phone_alarm")>=24*60) {
					Main.game.getDialogueFlags().incrementSavedLong("player_phone_alarm", -24*60);
				}
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
	}
	
	public static void initGiftListeners() {
		String id;
		for (Map.Entry<AbstractWeapon, Integer> entry : Main.game.getPlayer().getAllWeaponsInInventory().entrySet()) {
			id = "GIFT_"+entry.getKey().hashCode();
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.restoreSavedContent(false);
					Main.game.setContent(new Response("Give Gift", "", GiftDialogue.getDialogueToProceedTo()) {
						@Override
						public void effects() {
							Main.game.setResponseTab(GiftDialogue.getProceedDialogueTab());
							Main.game.getTextStartStringBuilder().append(GiftDialogue.getReceiver().getGiftReaction(entry.getKey(), true));
							Main.game.getPlayer().removeWeapon(entry.getKey());
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setWeapon(entry.getKey(), Main.game.getPlayer(), false));
			}
		}
		for (Map.Entry<AbstractItem, Integer> entry : Main.game.getPlayer().getAllItemsInInventory().entrySet()) {
			id = "GIFT_"+entry.getKey().hashCode();
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.restoreSavedContent(false);
					Main.game.setContent(new Response("Give Gift", "", GiftDialogue.getDialogueToProceedTo()) {
						@Override
						public void effects() {
							Main.game.setResponseTab(GiftDialogue.getProceedDialogueTab());
							Main.game.getTextStartStringBuilder().append(GiftDialogue.getReceiver().getGiftReaction(entry.getKey(), true));
							Main.game.getPlayer().removeItem(entry.getKey());
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setItem(entry.getKey(), Main.game.getPlayer(), null));
			}
		}
		for (Map.Entry<AbstractClothing, Integer> entry : Main.game.getPlayer().getAllClothingInInventory().entrySet()) {
			id = "GIFT_"+entry.getKey().hashCode();
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.restoreSavedContent(false);
					Main.game.setContent(new Response("Give Gift", "", GiftDialogue.getDialogueToProceedTo()) {
						@Override
						public void effects() {
							Main.game.setResponseTab(GiftDialogue.getProceedDialogueTab());
							Main.game.getTextStartStringBuilder().append(GiftDialogue.getReceiver().getGiftReaction(entry.getKey(), true));
							Main.game.getPlayer().removeClothing(entry.getKey());
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setClothing(entry.getKey(), Main.game.getPlayer(), null));
			}
		}
	}
	
	public static void initRenameListeners() {
		GameCharacter slave = Main.game.getDialogueFlags().getManagementCompanion();
		if (ScarlettsShop.isSlaveCustomisationMenu()) {
			slave = BodyChanging.getTarget();
		} else if (Main.game.getCurrentDialogueNode() == ElementalDialogue.ELEMENTAL_CHOOSE_NAME) {
			slave = Main.game.getPlayer().getElemental();
		}
		GameCharacter finalSlave = slave; // Lambda requirement
		
		String id = slave.getId()+"_RENAME";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				boolean unsuitableName = false;
				if (Main.mainController.getWebEngine().executeScript("document.getElementById('slaveNameInput')") != null) {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveNameInput').value;");
					if (Main.mainController.getWebEngine().getDocument() != null) {
						unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()<1
								|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()>32;
					}
					
					if (!unsuitableName) {
						Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								finalSlave.setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent()));
								finalSlave.loadImages();
							}
						});
					}
					
				}
			}, false);
		}
		
		id = slave.getId()+"_RENAME_RANDOM";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						finalSlave.setName(new NameTriplet(Name.getRandomName(finalSlave)));
						finalSlave.loadImages();
					}
				});
			}, false);
		}
		
		id = slave.getId()+"_RENAME_SURNAME";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				boolean unsuitableName = false;
				if (Main.mainController.getWebEngine().executeScript("document.getElementById('slaveSurnameInput')") != null) {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveSurnameInput').value;");
					if (Main.mainController.getWebEngine().getDocument() != null) {
						unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()<1
								|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()>32;
					}
					
					if (!unsuitableName) {
						Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								finalSlave.setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
							}
						});
					}
				}
			}, false);
		}
		
		id = slave.getId()+"_RENAME_SURNAME_RANDOM";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						finalSlave.setSurname(Name.getSurname(finalSlave));
					}
				});
			}, false);
		}
		
		id = slave.getId()+"_CALLS_PLAYER";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				boolean unsuitableName = false;
				if (Main.mainController.getWebEngine().executeScript("document.getElementById('slaveToPlayerNameInput')") != null) {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveToPlayerNameInput').value;");
					if (Main.mainController.getWebEngine().getDocument() != null) {
						unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()<1
								|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()>32;
					}
					
					if (!unsuitableName) {
						Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								finalSlave.setPetName(Main.game.getPlayer(), Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
							}
						});
					}
					
				}
			}, false);
		}
		
		id = "GLOBAL_CALLS_PLAYER";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				boolean unsuitableName = false;
				if (Main.mainController.getWebEngine().executeScript("document.getElementById('slaveToPlayerNameInput')") != null) {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('slaveToPlayerNameInput').value;");
					if (Main.mainController.getWebEngine().getDocument() != null) {
						unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()<1
								|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()>32;
					}
					
					if (!unsuitableName) {
						Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								for (String id : Main.game.getPlayer().getSlavesOwned()) {
									try {
										Main.game.getNPCById(id).setPetName(Main.game.getPlayer(), Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
									} catch (Exception e) {
										Util.logGetNpcByIdError("MiscController.initRenameListeners, GLOBAL_CALLS_PLAYER", id);
									}
								}
							}
						});
					}
				}
			}, false);
		}
	}
	
	public static void initFamilyRenameListeners() {
		String id = Main.game.getActiveNPC().getId()+"_PET_NAME";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				boolean unsuitableName = false;
				if (Main.mainController.getWebEngine().executeScript("document.getElementById('offspringPetNameInput')") != null) {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('offspringPetNameInput').value;");
					if (Main.mainController.getWebEngine().getDocument() != null) {
						unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()<1
								|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()>32;
					}
					
					if (!unsuitableName) {
						Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()) {
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
	
	public static void initCombatListeners() {
		for (GameCharacter combatant : Main.combat.getAllCombatants(true)) {
			for (DamageType dt : DamageType.values()) {
				String id = combatant.getId()+"_COMBAT_SHIELD_"+dt;
				
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							Util.capitaliseSentence(dt.getName())+" shielding",
							dt == DamageType.HEALTH
									?UtilText.parse(combatant, "[npc.Name] will block incoming damage from any non-lust source by this amount."
									+" Other damage type shielding will be used first, with health shielding used as the last resort."
									+" Negative values have no effect.")
									:dt != DamageType.LUST
									?UtilText.parse(combatant, "[npc.Name] will block incoming "+dt.getName()+" damage by this amount."
									+" Once this shielding is broken, health shielding will be used, and once that's broken, damage will be dealt [npc.her] health."
									+" Negative values have no effect.")
									:UtilText.parse(combatant, "[npc.Name] will block incoming "+dt.getName()+" damage by this amount."
									+" Once this shielding is broken, incoming "+dt.getName()+" damage will cause [npc.her] lust to rise."
									+" Negative values have no effect.")));
				}
			}
		}
	}
	
	public static void initEncyclopediaClothingListeners() {
		for (AbstractClothingType clothingType : ClothingType.getAllClothing()) {
			for(InventorySlot slot : clothingType.getEquipSlots()) {
				String id = clothingType.getId() +"_"+ slot.toString();
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setGenericClothing(clothingType));
				}
			}
		}
	}
	
	public static void initEncyclopediaItemListeners() {
		for (AbstractItemType item : ItemType.getAllItems()) {
			String id = ItemType.getItemToIdMap().get(item);
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setGenericItem(item));
			}
		}
	}
	
	public static void initEncyclopediaWeaponListeners() {
		for (AbstractWeaponType weapon : WeaponType.getAllWeapons()) {
			String id = weapon.getId();
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setGenericWeapon(weapon, weapon.getAvailableDamageTypes().get(0)));
			}
			for (DamageType dt : weapon.getAvailableDamageTypes()) {
				if (MainController.document.getElementById(id+"_"+dt.toString()) != null) {
					MainController.addTooltipListeners(id+"_"+dt, new TooltipInventoryEventListener().setGenericWeapon(weapon, dt));
				}
			}
		}
	}
	
	public static void initSpellListeners(SpellSchool spellSchool) {
		List<Spell> spells;
		if (spellSchool != null) {
			spells = Spell.getSpellsFromSchoolMap().get(spellSchool);
		} else {
			spells = Arrays.asList(Spell.values());
		}
		for (Spell s : spells) {
			if ((spellSchool != null && !s.isSpellBook()) || (spellSchool == null && s.isSpellBook())) {
				continue;
			}
			String id = "SPELL_TREE_"+s;
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setSpell(s, SpellManagement.getSpellOwner()));
			}
			id = "SPELL_TREE_CAST_"+s;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event->{
					if (s.getSpellCastOutOfCombatDescription(SpellManagement.getSpellOwner(), SpellManagement.getSpellTarget()).getKey()) {
						Main.game.setContent(new Response("", "", SpellManagement.castSpell(s)));
					}
				}, false);
				Util.Value<Boolean, String> useDec = s.getSpellCastOutOfCombatDescription(SpellManagement.getSpellOwner(), SpellManagement.getSpellTarget());
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						"Cast "+s.getName(),
						useDec.getKey()
								?useDec.getValue()
								:"[style.italicsBad("+useDec.getValue()+")]"));
			}
			for (List<TreeEntry<SpellSchool, SpellUpgrade>> upgradeList : s.getSpellUpgradeTree().values()) {
				for (TreeEntry<SpellSchool, SpellUpgrade> upgrade : upgradeList) {
					id = "SPELL_UPGRADE_"+upgrade.getEntry();
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event->{
							if (Spell.isSpellUpgradeAvailable(SpellManagement.getSpellOwner(), s, upgrade)
									&& SpellManagement.getSpellOwner().getSpellUpgradePoints(upgrade.getCategory())>=upgrade.getEntry().getPointCost()
									&& !SpellManagement.getSpellOwner().hasSpellUpgrade(upgrade.getEntry())) {
								SpellManagement.getSpellOwner().addSpellUpgrade(upgrade.getEntry());
								SpellManagement.getSpellOwner().incrementSpellUpgradePoints(upgrade.getCategory(), -upgrade.getEntry().getPointCost());
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setSpellUpgrade(upgrade.getEntry(), SpellManagement.getSpellOwner()));
					}
				}
			}
		}
	}
	
	public static void initPerkListeners(DialogueNode currentNode) {
		GameCharacter character = CharactersPresentDialogue.characterViewed;
		if (currentNode.equals(CompanionManagement.SLAVE_MANAGEMENT_PERKS)
				|| currentNode.equals(CompanionManagement.SLAVE_MANAGEMENT_INSPECT)) {
			character = OccupantManagementDialogue.characterSelected();
		} else if (currentNode.equals(ElementalDialogue.ELEMENTAL_PERKS)) {
			character = Main.game.getPlayer().getElemental();
		} else if (currentNode.equals(PhoneDialogue.CHARACTER_APPEARANCE)
				|| currentNode.equals(PhoneDialogue.CHARACTER_PERK_TREE)) {
			character = Main.game.getPlayer();
		}
		if(character==null) {
			System.err.println("Error: character was null in initPerkListeners(), Dialogue ID: "+currentNode.getId());
			character = Main.game.getPlayer();
		}
		
		boolean availableForSelection = !currentNode.equals(CharactersPresentDialogue.MENU)
				&& !currentNode.equals(PhoneDialogue.CHARACTER_APPEARANCE)
				&& !currentNode.equals(PhoneDialogue.CONTACTS_CHARACTER);
		
		String id;
		for (AbstractPerk perk : Perk.getAllPerks()) {
			if (perk.isHiddenPerk()) {
				id = "HIDDEN_PERK_"+Perk.getIdFromPerk(perk);
				if (MainController.document.getElementById(id) != null) {
					if (character.hasPerkAnywhereInTree(perk)) {
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setLevelUpPerk(0, perk, character, false));
					} else {
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Unknown!",
								"This is an undiscovered hidden perk, and as such, you have no idea what it could be!<br/><i>Hidden perks are discovered through the main quest.</i>"));
					}
				}
			} else if (perk.getPerkCategory() == PerkCategory.JOB) {
				id = "OCCUPATION_"+Perk.getIdFromPerk(perk);
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setLevelUpPerk(0, perk, character, availableForSelection));
				}
				
			} else {
				id = "TRAIT_"+Perk.getIdFromPerk(perk);
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setLevelUpPerk(PerkManager.MANAGER.getPerkRow(character, perk), perk, character, availableForSelection));
					if (availableForSelection) {
						GameCharacter finalCharacter = character;
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event->{
							finalCharacter.removeTrait(perk);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
			}
		}
		
		for (int i = 0; i<PerkManager.ROWS; i++) {
			for (Map.Entry<PerkCategory, List<TreeEntry<PerkCategory, AbstractPerk>>> entry : PerkManager.MANAGER.getPerkTree(character).get(i).entrySet()) {
				for (TreeEntry<PerkCategory, AbstractPerk> e : entry.getValue()) {
					id = i+"_"+e.getCategory()+"_"+Perk.getIdFromPerk(e.getEntry());
					if (MainController.document.getElementById(id) != null) {
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setLevelUpPerk(i, e.getEntry(), character, availableForSelection));
						// These two dialogues are just for viewing, not modifying:
						if (availableForSelection) {
							GameCharacter finalCharacter = character;
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event->{
								if (e.getEntry().isEquippableTrait() && PerkManager.MANAGER.isPerkOwned(finalCharacter, e)) {
									if (!finalCharacter.hasTraitActivated(e.getEntry())) {
										finalCharacter.addTrait(e.getEntry());
									} else {
										finalCharacter.removeTrait(e.getEntry());
									}
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
								} else if ((finalCharacter.getPerkPoints()>=1
										|| finalCharacter.getAdditionalPerkCategoryPoints(e.getCategory())>Math.max(0, finalCharacter.getPerksInCategory(e.getCategory())-PerkManager.getInitialPerkCount(finalCharacter, e.getCategory())))
										&& PerkManager.MANAGER.isPerkAvailable(finalCharacter, e)) {
									if (finalCharacter.addPerk(e.getRow(), e.getEntry())) {
										if (e.getEntry().isEquippableTrait() && finalCharacter.getTraits().size()<GameCharacter.MAX_TRAITS) {
											finalCharacter.addTrait(e.getEntry());
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
	
	public static void initCombatMoveListeners() {
		for (AbstractCombatMove move : CombatMove.getAllCombatMoves()) {
			GameCharacter character = CombatMovesSetup.getTarget();
			String id = "MOVE_"+move.getIdentifier();
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event->{
					if (character.getEquippedMoves().contains(move)) {
						character.unequipMove(move.getIdentifier());
					} else if (character.getEquippedMoves().size()<GameCharacter.MAX_COMBAT_MOVES) {
						character.equipMove(move.getIdentifier());
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setCombatMove(move, character));
			}
		}
	}
	
	public static void initFetishListeners() {
		GameCharacter targetedCharacter;
		if (Main.game.getCurrentDialogueNode() == ElementalDialogue.ELEMENTAL_FETISHES) {
			targetedCharacter = Main.game.getPlayer().getElemental();
		} else {
			targetedCharacter = Main.game.getPlayer();
		}
		for (AbstractFetish f : Fetish.getAllFetishes()) {
			String id = "FETISH_"+Fetish.getIdFromFetish(f);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (Main.game.getPlayer().getEssenceCount()>=f.getCost()
							&& f.getFetishesForAutomaticUnlock().isEmpty()
							&& !targetedCharacter.hasFetish(f)
							&& !targetedCharacter.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)) {
						targetedCharacter.addFetish(f);
						targetedCharacter.incrementEssenceCount(-f.getCost(), false);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setFetish(f, targetedCharacter, true));
			}
			
			id = Fetish.getIdFromFetish(f)+"_EXPERIENCE";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setFetishExperience(f, targetedCharacter));
			}
			
			for (FetishDesire desire : FetishDesire.values()) {
				id = Fetish.getIdFromFetish(f)+"_"+desire;
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						if (Main.game.getPlayer().getEssenceCount()>=FetishDesire.getCostToChange()
								&& targetedCharacter.getBaseFetishDesire(f) != desire
								&& !targetedCharacter.hasPerkAnywhereInTree(Perk.DOLL_LUST_3)) {
							targetedCharacter.incrementEssenceCount(-FetishDesire.getCostToChange(), false);
							targetedCharacter.setFetishDesire(f, desire);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setFetishDesire(f, desire, targetedCharacter));
				}
			}
		}
	}

	public static void initEncyclopediaRaceListeners() {
		for(AbstractRace race : Race.getAllRaces()) {
			String id = "ENCYCLOPEDIA_RACE_"+Race.getIdFromRace(race);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					PhoneDialogue.applyRaceSelection(race);
					Main.game.setContent(new Response("", "", PhoneDialogue.SUBSPECIES));
				}, false);
			}
		}
	}
	
	
	public static void initMapListeners(AbstractWorldType worldType, boolean interactive) {
		Cell[][] grid = Main.game.getWorlds().get(worldType).getGrid();
		for (int i = grid[0].length-1; i>=0; i--) {
			for (int j = 0; j<grid.length; j++) {
				Cell c = grid[j][i];
				if ((!worldType.equals(WorldType.WORLD_MAP)
							&& !Main.game.isMapReveal()
							&& !c.isDiscovered())
						|| c.getPlace().getPlaceType().equals(PlaceType.GENERIC_IMPASSABLE)) {
					continue;
				}
				MiscController.initMapLocationListeners(worldType, c, i, j, interactive);
			}
		}
		
		if (interactive) {
			for (MapTravelType type : MapTravelType.values()) {
				String id = type.toString();
				if (MainController.document.getElementById(id) != null) {
					if (type.isAvailable(null, Main.game.getPlayer())) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
							Pathing.initPathingVariables();
							Pathing.setMapTravelType(type);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					MainController.addTooltipListeners(id,
							new TooltipInformationEventListener().setInformation(type.getName(),
									type.getDescription()
											+(type.isAvailable(null, Main.game.getPlayer())
											?" ("+type.getUseInstructions()+")"
											:"<br/>[style.italicsBad("+type.getUnavailablilityDescription(null, Main.game.getPlayer())+")]")));
				}
			}
		}
	}
	
	static void initMapLocationListeners(AbstractWorldType worldType, Cell c, int i, int j, boolean interactive) {
		String id = "MAP_NODE_"+i+"_"+j;
		if (MainController.document.getElementById(id) != null) {
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setCell(c));
			if (!interactive) {
			    return;
			}
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Vector2i clickLocation = new Vector2i(j, i);
				if (c.getDialogue(false) != null // Make sure the destination actually has an associated DialogueNode
						&& (c.isTravelledTo() || Main.game.isDebugMode()) // The player needs to have travelled here before (or have debug active)
						&& (Main.game.getSavedDialogueNode() != null && !Main.game.getSavedDialogueNode().isTravelDisabled()) // You can't fast travel out of a special dialogue
						&& Pathing.getMapTravelType().isAvailable(c, Main.game.getPlayer())) { // Make sure the travel type is actually available
					if (!clickLocation.equals(Main.game.getPlayer().getLocation())
							|| !worldType.equals(Main.game.getPlayer().getWorldLocation())) {
						switch (Pathing.getMapTravelType()) {
							case TELEPORT:
								if (clickLocation.equals(Pathing.getEndPoint())) {
									if (!Main.game.isDebugMode()) {
										Main.game.getPlayer().incrementMana(-Spell.TELEPORT.getModifiedCost(Main.game.getPlayer()));
									}
									Main.game.getPlayer().setLocation(worldType, clickLocation, false);
									DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true);
									Main.game.getTextStartStringBuilder().append(
											"<p style='text-align:center'>"
													+"[style.italicsArcane(Recalling what your destination looked like the last time you were there, you cast the teleportation spell, and in an instant, you appear there!)]"
													+"</p>");
									Main.game.setContent(new Response("", "", dn) {
										@Override
										public int getSecondsPassed() {
											return 5;
										}
									});
								} else {
									Pathing.setEndPoint(clickLocation, c, null);
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
								}
								break;
							case FLYING:
								if (worldType.equals(Main.game.getPlayer().getWorldLocation())) {
									if (clickLocation.equals(Pathing.getEndPoint())) {
										if (Pathing.isImpossibleDestination()) {
											Main.game.flashMessage(PresetColour.GENERIC_BAD, "Cannot travel here!");
										} else {
											Main.game.getPlayer().setLocation(worldType, clickLocation, false);
											DialogueNode dn = c.getDialogue(true);
											Main.game.getTextStartStringBuilder()
													.append("<p style='text-align:center'>[style.italicsAir(")
													.append(!Main.game.getPlayer().isAbleToFlyFromExtraParts()?"With a flap of your wings, you":"You")
													.append(" launch yourself into the air, before swiftly flying to your destination!)]</p>");
											Main.game.setContent(new Response("", "", dn) {
												@Override
												public int getSecondsPassed() {
													return Pathing.getTravelTime();
												}
											});
										}
										
									} else {
										Pathing.setEndPoint(clickLocation, c, worldType);
										Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
									}
								}
								break;
							case WALK_DANGEROUS:
							case WALK_SAFE:
								if (worldType.equals(Main.game.getPlayer().getWorldLocation())) {
									if (clickLocation.equals(Pathing.getEndPoint())) {
										if (Pathing.isImpossibleDestination()) {
											Main.game.flashMessage(PresetColour.GENERIC_BAD, "Cannot travel here!");
										} else {
											Main.game.setContent(Pathing.walkPath(Pathing.getMapTravelType()));
										}
									} else {
										boolean preferSafe = Pathing.getMapTravelType().equals(MapTravelType.WALK_SAFE);
										if (Main.mainController.buttonsPressed.contains(KeyCode.SHIFT)) {
											Pathing.appendPathingCells(Pathing.aStarPathing(Main.game.getWorlds().get(worldType).getCellGrid(), Pathing.getEndPoint(), clickLocation, preferSafe), clickLocation);
										} else {
											Pathing.setPathingCells(Pathing.aStarPathing(Main.game.getWorlds().get(worldType).getCellGrid(), Main.game.getPlayer().getLocation(), clickLocation, preferSafe), clickLocation);
										}
										Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
									}
								}
								break;
						}
					}
				} else {
					if (!c.isTravelledTo()) {
						Main.game.flashMessage(PresetColour.GENERIC_BAD, "Cannot fast-travel to unexplored locations!");
					} else {
						Main.game.flashMessage(PresetColour.GENERIC_BAD, "Cannot travel here!");
					}
				}
				
			}, false);
		}
	}
	
	public static void initInfoTooltipListeners() {
		for (Map.Entry<String, TooltipInformationEventListener> entry : Game.informationTooltips.entrySet()) {
			String id = entry.getKey();
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, entry.getValue());
			}
		}
	}
	
	public static void initDressingRoomListeners() {
		String id = "apply_outfit_name";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				boolean unsuitableName = false;
				if (Main.mainController.getWebEngine().executeScript("document.getElementById('outfit_name')") != null) {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('outfit_name').value;");
					if (Main.mainController.getWebEngine().getDocument() != null) {
						unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent().length()<1
								|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent().length()>32;
					}
					
					if (!unsuitableName) {
						Main.game.setContent(new Response("Set outfit name", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								LilayaDressingRoomDialogue.setOutfitName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							}
						});
					}
					
				}
			}, false);
		}
		
		// Selecting a slot:
		if(Main.game.getCurrentDialogueNode()==LilayaDressingRoomDialogue.OUTFIT_EDITOR) {
			for(InventorySlot slot : InventorySlot.values()) {
				id = "outfit_select_slot_"+slot.toString();
				if (MainController.document.getElementById(id) != null) {
					boolean disabled = LilayaDressingRoomDialogue.isSlotDisabled(slot);
					if(!disabled) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
							
							LilayaDressingRoomDialogue.setSelectedSlot(slot);
							LilayaDressingRoomDialogue.newlyCreatedWeapon = false;
							
							if(LilayaDressingRoomDialogue.initItemFromSlot()) {	// If there's already an item in this slot, load it and go straight to enchant menu:
								LilayaDressingRoomDialogue.initEnchantDialogue();
	//							LilayaDressingRoomDialogue.setOutputName(LilayaDressingRoomDialogue.getSelectedItem().getName());
								Main.game.setContent(new Response("Enchantment screen", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_ENCHANT));
								
							} else {
								if(slot.isWeapon()) {
									LilayaDressingRoomDialogue.newlyCreatedWeapon = true;
								}
								Main.game.setContent(new Response("Select item", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_CHOICE));
							}
						}, false);
					}
					
					AbstractClothing clothingInSlot = LilayaDressingRoomDialogue.getActiveOutfit().getClothing().get(slot);
					AbstractWeapon weaponInSlot = LilayaDressingRoomDialogue.getActiveOutfit().getWeapons().get(slot);
					if(clothingInSlot!=null) {
						MainController.addTooltipListeners(id,
								new TooltipInventoryEventListener().setClothing(clothingInSlot, null, null));
					} else if(weaponInSlot!=null) {
						MainController.addTooltipListeners(id,
								new TooltipInventoryEventListener().setWeapon(weaponInSlot, null, true));
					} else {
						if(disabled) {
							MainController.addTooltipListeners(id,
									new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(slot.getName())+": [style.colourBad(Blocked!)]",
											LilayaDressingRoomDialogue.getSlotDisabledText(slot),
											18));
						} else {
							MainController.addTooltipListeners(id,
									new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(slot.getName()),
											"Click on this slot to open the item selection screen.",
											18));
						}
					}
				}
				
				id = "clear_slot_"+slot.toString();
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						
						LilayaDressingRoomDialogue.setSelectedSlot(slot);
						LilayaDressingRoomDialogue.clearSlot();
						Main.game.setContent(new Response("Remove item", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR));
						
					}, false);
				}
			}
		}
		
		// Choosing an item of clothing or a weapon:
		if(Main.game.getCurrentDialogueNode()==LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_CHOICE) {
			id = "clear_slot";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					
					LilayaDressingRoomDialogue.clearSlot();
					Main.game.setContent(new Response("Clear slot", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR));
					
				}, false);
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("Clear slot", "Remove whatever clothing/weapon is in this slot.", 18));
			}
			
			id = "ignore_slot";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{

					LilayaDressingRoomDialogue.ignoreSlot();
					Main.game.setContent(new Response("Ignore slot", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR));
					
				}, false);
				
				String clothingWeapon = LilayaDressingRoomDialogue.getSelectedSlot().isWeapon()?"weapon":"clothing";
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation(
								"Ignore slot",
								"Remove whatever "+clothingWeapon+" is in this slot and then define it as being [style.italicsBad(ignored)]."
										+ " This means that whenever this outfit is applied to a character, instead of removing whatever "+clothingWeapon+" the character currently has equipped in this slot, it will be ignored and left as-is.",
								106));
			}
			
			if(LilayaDressingRoomDialogue.getSelectedSlot().isWeapon()) {
				for(AbstractWeaponType weaponType : PhoneDialogue.getWeaponsDiscoveredList()) {
					id = weaponType.getId();
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
							
							LilayaDressingRoomDialogue.setWeaponSelected(weaponType);
							Main.game.setContent(new Response("Select weapon", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_DYE));
							
						}, false);
						MainController.addTooltipListeners(id,
								new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(weaponType.getName()), "Select this weapon to proceed to the dye menu.", 18));
					}
				}
				
			} else {
				for(AbstractClothingType clothingType : PhoneDialogue.getClothingDiscoveredList()) {
					id = clothingType.getId();
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{

							LilayaDressingRoomDialogue.setClothingSelected(clothingType);
							Main.game.setContent(new Response("Select clothing", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_DYE));
							
						}, false);
						// Warn player if this clothing is incompatible with any of the outfit's currently selected clothing
						List<AbstractClothing> incompatibleClothing = new ArrayList<>();
						for(InventorySlot slot : Main.game.getItemGen().generateClothing(clothingType, false).getIncompatibleSlots(LilayaDressingRoomDialogue.getDoll(), LilayaDressingRoomDialogue.getSelectedSlot())) {
							if(LilayaDressingRoomDialogue.getDoll().getClothingInSlot(slot)!=null) {
								incompatibleClothing.add(LilayaDressingRoomDialogue.getDoll().getClothingInSlot(slot));
							}
						}
						StringBuilder incompatibleClothingSB = new StringBuilder();
						for(AbstractClothing clothing : incompatibleClothing) {
							incompatibleClothingSB.append("<br/>[style.boldBad(-)] "+Util.capitaliseSentence(clothing.getName(false, true)));
						}
						MainController.addTooltipListeners(id,
								new TooltipInformationEventListener().setInformation(
										Util.capitaliseSentence(clothingType.getName()),
										"Select this clothing to proceed to the dye menu."
										+(!incompatibleClothing.isEmpty()
											?"<br/>[style.boldBad(!)] [style.italicsMinorBad(The "+clothingType.getName()+" "+(clothingType.isPlural()?"are":"is")
													+" incompatible with other clothing in your outfit, and if selected, the following items will be removed:)]"
												+incompatibleClothingSB.toString()
											:""),
										incompatibleClothing.isEmpty()
											?18
											:(74+incompatibleClothing.size()*12)));
					}
				}
			}
		}
	}
	
	public static void initDressingRoomDyeListeners() {
		// Clothing listeners:
		if(LilayaDressingRoomDialogue.getClothingSelected()!=null) {
			AbstractClothingType clothing = LilayaDressingRoomDialogue.getClothingSelected().getClothingType();
			for (int i = 0; i<clothing.getColourReplacements().size(); i++) {
				int index = i;
				ColourReplacement cr = clothing.getColourReplacement(i);
				for (Colour c : cr.getAllColours()) {
					String id = "DYE_CLOTHING_"+i+"_"+c.getId();
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
							if (cr.isRecolouringAllowed()) {
								LilayaDressingRoomDialogue.getClothingSelected().setColour(index, c);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setDyeClothing(LilayaDressingRoomDialogue.getClothingSelected(), i, c));
					}
				}
			}
			
			for (Pattern pattern : Pattern.getAllPatterns()) {
				String id = "ITEM_PATTERN_"+pattern.getId();
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						if (!LilayaDressingRoomDialogue.getClothingSelected().getPattern().equals(pattern.getId())) {
							LilayaDressingRoomDialogue.getClothingSelected().setPattern(pattern.getId());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
				}
			}
			
			for (int i = 0; i<clothing.getPatternColourReplacements().size(); i++) {
				ColourReplacement cr = clothing.getPatternColourReplacement(i);
				for (Colour c : cr.getAllColours()) {
					String id = "DYE_CLOTHING_PATTERN_"+i+"_"+c.getId();
					if (MainController.document.getElementById(id) != null) {
						int finalI = i;
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
							LilayaDressingRoomDialogue.getClothingSelected().setPatternColour(finalI, c);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setDyeClothingPattern(LilayaDressingRoomDialogue.getClothingSelected(), i, c));
					}
				}
			}
			
			for (Map.Entry<StickerCategory, List<Sticker>> stickerEntry : clothing.getStickers().entrySet()) {
				for (Sticker s : stickerEntry.getValue()) {
					String id = "ITEM_STICKER_"+stickerEntry.getKey().getId()+s.getId();
					String requirements = UtilText.parse(s.getUnavailabilityText()).trim();
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
							if (LilayaDressingRoomDialogue.getClothingSelected().getStickersAsObjects().get(stickerEntry.getKey()) != s && requirements.isEmpty()) {
								LilayaDressingRoomDialogue.getClothingSelected().setSticker(stickerEntry.getKey(), s);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
						
						int lineHeight = 0;
						StringBuilder tooltipDescriptionSB = new StringBuilder();
						if (!requirements.isEmpty()) {
							tooltipDescriptionSB.append("[style.boldBad(Locked:)] <i>"+requirements+"</i><br/>");
							lineHeight += 2;
						} else {
							if (!s.getAvailabilityText().isEmpty()) {
								tooltipDescriptionSB.append("[style.boldGood(Unlocked:)] <i>"+s.getAvailabilityText()+"</i><br/>");
								lineHeight += 2;
							}
							
							boolean tagApplicationFound = false;
							for (ItemTag tag : s.getTagsApplied()) {
								for (String tagTooltip : tag.getClothingTooltipAdditions()) {
									if (!tagApplicationFound) {
										tooltipDescriptionSB.append("[style.boldMinorGood(Effects added:)]<br/>");
										tagApplicationFound = true;
										lineHeight++;
									}
									tooltipDescriptionSB.append(tagTooltip+"<br/>");
									lineHeight++;
								}
							}
							
							tagApplicationFound = false;
							for (ItemTag tag : s.getTagsRemoved()) {
								for (String tagTooltip : tag.getClothingTooltipAdditions()) {
									if (!tagApplicationFound) {
										tooltipDescriptionSB.append("[style.boldMinorBad(Effects removed:)]<br/>");
										tagApplicationFound = true;
										lineHeight++;
									}
									tooltipDescriptionSB.append(tagTooltip+"<br/>");
									lineHeight++;
								}
							}
						}
						TooltipInformationEventListener el = new TooltipInformationEventListener();
						if (lineHeight>0) {
							el.setInformation("Special Effects",
									tooltipDescriptionSB.toString(),
									(lineHeight*16));
						} else {
							el.setInformation("No Special Effects", "");
						}
						MainController.addTooltipListeners(id, el);
					}
				}
			}
			
		// Weapon listeners:
		} else {
			AbstractWeaponType weapon = LilayaDressingRoomDialogue.getWeaponSelected().getWeaponType();
			for (int i = 0; i<weapon.getColourReplacements(false).size(); i++) {
				int index = i;
				ColourReplacement cr = weapon.getColourReplacement(false, i);
				for (Colour c : cr.getAllColours()) {
					String id = "DYE_WEAPON_"+i+"_"+c.getId();
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
							if (cr.isRecolouringAllowed()) {
								LilayaDressingRoomDialogue.getWeaponSelected().setColour(index, c);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setDyeWeapon(LilayaDressingRoomDialogue.getWeaponSelected(), i, c));
					}
				}
			}
			
			for (DamageType dt : weapon.getAvailableDamageTypes()) {
				String id = "DAMAGE_TYPE_"+dt.toString();
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						LilayaDressingRoomDialogue.getWeaponSelected().setDamageType(dt);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setDamageTypeWeapon(LilayaDressingRoomDialogue.getWeaponSelected(), dt));
				}
			}
		}
	}

	public static void initDressingRoomEnchantmentListeners() {
		String id = "apply_enchanted_item_name";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
				LilayaDressingRoomDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
		}
		
		// Tooltips:
		id = "MOD_PRIMARY_ENCHANTING";
		if (MainController.document.getElementById(id) != null) {
			MainController.addTooltipListeners(id,
					new TooltipInventoryEventListener().setTFModifier(LilayaDressingRoomDialogue.getPrimaryMod()),
					new EnchantmentEventListener().setPrimaryModifier(TFModifier.NONE), false);
		}
		id = "MOD_SECONDARY_ENCHANTING";
		if (MainController.document.getElementById(id) != null) {
			MainController.addTooltipListeners(id,
					new TooltipInventoryEventListener().setTFModifier(LilayaDressingRoomDialogue.getSecondaryMod()),
					new EnchantmentEventListener().setSecondaryModifier(TFModifier.NONE), false);
		}
		
		for (TFPotency potency : TFPotency.values()) {
			id = "POTENCY_"+potency;
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click", new EnchantmentEventListener().setPotency(potency), false);
			}
		}
		
		int effectCount = 0;
		id = "DELETE_EFFECT_"+effectCount;
		while(MainController.document.getElementById(id) != null) {
			MainController.addTooltipListeners(id,
					new TooltipInformationEventListener().setInformation("Delete Effect", ""),
					new EnchantmentEventListener().removeEffect(effectCount), false);
			effectCount++;
			id = "DELETE_EFFECT_"+effectCount;
		}
		
//		for (int effectCount = 0; effectCount<LilayaDressingRoomDialogue.getEffects().size(); effectCount++) {
//			id = "DELETE_EFFECT_"+effectCount;
//			if (MainController.document.getElementById(id) != null) {
//				MainController.addTooltipListeners(id,
//						new TooltipInformationEventListener().setInformation("Delete Effect", ""),
//						new EnchantmentEventListener().removeEffect(effectCount), false);
//			}
//		}
		
		AbstractItemEffectType effect = LilayaDressingRoomDialogue.getSelectedItem().getEnchantmentEffect();
		// int maxLimit = effect.getMaximumLimit(); //TODO for some reason this works in the normal enchantment menu but not in the outfit menu... (but the method below works)
		int maxLimit = effect.getLimits(LilayaDressingRoomDialogue.getPrimaryMod(), LilayaDressingRoomDialogue.getSecondaryMod());
		int currentLimit = LilayaDressingRoomDialogue.getLimit();

		if (currentLimit>0) {
			id = "LIMIT_MINIMUM";
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click",
						new EnchantmentEventListener().setLimit(0), false);
			}
			
			id = "LIMIT_DECREASE_LARGE";
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click",
						new EnchantmentEventListener().setLimit(Math.max(0, LilayaDressingRoomDialogue.getLimit()-effect.getLargeLimitChange(LilayaDressingRoomDialogue.getPrimaryMod(), LilayaDressingRoomDialogue.getSecondaryMod()))), false);
			}
			
			id = "LIMIT_DECREASE";
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click",
						new EnchantmentEventListener().setLimit(LilayaDressingRoomDialogue.getLimit()-effect.getSmallLimitChange(LilayaDressingRoomDialogue.getPrimaryMod(), LilayaDressingRoomDialogue.getSecondaryMod())), false);
			}
		}
		
		if (currentLimit<maxLimit) {
			id = "LIMIT_INCREASE";
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click",
						new EnchantmentEventListener().setLimit(LilayaDressingRoomDialogue.getLimit()+effect.getSmallLimitChange(LilayaDressingRoomDialogue.getPrimaryMod(), LilayaDressingRoomDialogue.getSecondaryMod())), false);
			}
			
			id = "LIMIT_INCREASE_LARGE";
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click",
						new EnchantmentEventListener().setLimit(Math.min(maxLimit, LilayaDressingRoomDialogue.getLimit()+effect.getLargeLimitChange(LilayaDressingRoomDialogue.getPrimaryMod(), LilayaDressingRoomDialogue.getSecondaryMod()))), false);
			}
			
			id = "LIMIT_MAXIMUM";
			if (MainController.document.getElementById(id) != null) {
				MainController.addEventListener(MainController.document, id, "click",
						new EnchantmentEventListener().setLimit(maxLimit), false);
			}
		}
		
		// Ingredient icon:
		id = "INGREDIENT_ENCHANTING";
		if (MainController.document.getElementById(id) != null) {
			TooltipInventoryEventListener el = null;
			if (LilayaDressingRoomDialogue.getSelectedItem() instanceof AbstractClothing) {
				el = new TooltipInventoryEventListener().setClothing((AbstractClothing) LilayaDressingRoomDialogue.getSelectedItem(), Main.game.getPlayer(), null);
			} else if (LilayaDressingRoomDialogue.getSelectedItem() instanceof AbstractWeapon) {
				el = new TooltipInventoryEventListener().setWeapon((AbstractWeapon) LilayaDressingRoomDialogue.getSelectedItem(), Main.game.getPlayer(), false);
			}
			MainController.addTooltipListeners(id, el);
		}
		
		// Output icon:
		id = "OUTPUT_ENCHANTING";
		if (MainController.document.getElementById(id) != null) {
			TooltipInventoryEventListener el = null;
			if (LilayaDressingRoomDialogue.getSelectedItem() instanceof AbstractClothing) {
				el = new TooltipInventoryEventListener().setClothing(EnchantingUtils.craftClothing(LilayaDressingRoomDialogue.getSelectedItem(), LilayaDressingRoomDialogue.getEffects()), Main.game.getPlayer(), null);
			} else if (LilayaDressingRoomDialogue.getSelectedItem() instanceof AbstractWeapon) {
				el = new TooltipInventoryEventListener().setWeapon(EnchantingUtils.craftWeapon(LilayaDressingRoomDialogue.getSelectedItem(), LilayaDressingRoomDialogue.getEffects()), Main.game.getPlayer(), false);
			}
			MainController.addTooltipListeners(id, el);
		}
		
		// Adding an effect:
		id = "ENCHANT_ADD_BUTTON";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				if (LilayaDressingRoomDialogue.getSelectedItem().getEnchantmentEffect().getEffectsDescription(
						LilayaDressingRoomDialogue.getPrimaryMod(), LilayaDressingRoomDialogue.getSecondaryMod(), LilayaDressingRoomDialogue.getPotency(), LilayaDressingRoomDialogue.getLimit(), Main.game.getPlayer(), Main.game.getPlayer()) != null) {
					Main.game.setContent(new Response("Add", "Add the effect.", LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_ENCHANT) {
						@Override
						public void effects() {
							LilayaDressingRoomDialogue.addEffect(new ItemEffect(
									LilayaDressingRoomDialogue.getSelectedItem().getEnchantmentEffect(), LilayaDressingRoomDialogue.getPrimaryMod(), LilayaDressingRoomDialogue.getSecondaryMod(), LilayaDressingRoomDialogue.getPotency(), LilayaDressingRoomDialogue.getLimit()));
						}
					});
				}
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Add Effect", ""));
		}
		
		id = "ENCHANT_ADD_BUTTON_DISABLED";
		if (MainController.document.getElementById(id) != null) {
			String blockedString = LilayaDressingRoomDialogue.getEnchantmentEffectBlockedReason(LilayaDressingRoomDialogue.getCurrentEffect());
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Add Effect",
					LilayaDressingRoomDialogue.getEffects().size()>=LilayaDressingRoomDialogue.getSelectedItem().getEnchantmentLimit()
							?"You have already added the maximum number of effects for this item!"
							:(blockedString != null
							?blockedString
							:"")));
		}
		
		// Choosing a primary modifier:
		for (TFModifier tfMod : LilayaDressingRoomDialogue.getSelectedItem().getEnchantmentEffect().getPrimaryModifiers(LilayaDressingRoomDialogue.getSelectedItem())) {
			id = "MOD_PRIMARY_"+tfMod.hashCode();
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id,
						new TooltipInventoryEventListener().setTFModifier(tfMod),
						new EnchantmentEventListener().setPrimaryModifier(tfMod),false);
			}
		}
		// Choosing a secondary modifier:
		for (TFModifier tfMod : LilayaDressingRoomDialogue.getSelectedItem().getEnchantmentEffect().getSecondaryModifiers(LilayaDressingRoomDialogue.getSelectedItem(), LilayaDressingRoomDialogue.getPrimaryMod())) {
			id = "MOD_SECONDARY_"+tfMod.hashCode();
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id,
						new TooltipInventoryEventListener().setTFModifier(tfMod),
						new EnchantmentEventListener().setSecondaryModifier(tfMod),false);
			}
		}
	
	}
	
	
}
