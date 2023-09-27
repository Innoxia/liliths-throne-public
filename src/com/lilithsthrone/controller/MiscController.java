package com.lilithsthrone.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.w3c.dom.events.EventTarget;

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
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.CombatMovesSetup;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.MapTravelType;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.SpellManagement;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Pathing;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import javafx.scene.input.KeyCode;

/**
 * @since 0.4.6.4
 * @version 0.4.6.4
 * @author Maxis010, Innoxia
 */
public class MiscController {
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
		for (AbstractClothingType clothing : ClothingType.getAllClothing()) {
			String id = clothing.getId();
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setGenericClothing(clothing));
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
							if (Spell.isSpellUpgradeAvailable(SpellManagement.getSpellOwner(), s, upgrade) && SpellManagement.getSpellOwner().getSpellUpgradePoints(upgrade.getCategory())>=upgrade.getEntry().getPointCost()) {
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
		if (currentNode.equals(CompanionManagement.SLAVE_MANAGEMENT_PERKS)) {
			character = OccupantManagementDialogue.characterSelected();
		} else if (currentNode.equals(ElementalDialogue.ELEMENTAL_PERKS)) {
			character = Main.game.getPlayer().getElemental();
		} else if (currentNode.equals(PhoneDialogue.CHARACTER_APPEARANCE)
				|| currentNode.equals(PhoneDialogue.CHARACTER_PERK_TREE)) {
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
							&& !targetedCharacter.isDoll()) {
						targetedCharacter.addFetish(f);
						targetedCharacter.incrementEssenceCount(-f.getCost(), false);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setFetish(f, targetedCharacter));
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
								&& !targetedCharacter.isDoll()) {
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
}
