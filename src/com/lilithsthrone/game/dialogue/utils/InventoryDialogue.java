package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.combat.CombatMove;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.ShopTransaction;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.BlockedParts;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.comparators.ClothingZLayerComparator;

/**
 * @since 0.1.0
 * @version 0.3.4.5
 * @author Innoxia
 */
public class InventoryDialogue {
	
	// Welcome to a slightly cleaned-up hell!
	
	private static final int IDENTIFICATION_PRICE = 400;
	private static final int IDENTIFICATION_ESSENCE_PRICE = 3;
	
	private static AbstractItem item;
	private static AbstractClothing clothing;
	private static AbstractWeapon weapon;
	private static InventorySlot weaponSlot;
	private static GameCharacter owner;
	
	private static NPC inventoryNPC;
	private static InventoryInteraction interactionType;

	private static StringBuilder inventorySB = new StringBuilder();

	private static boolean buyback;

	private static int buyBackPrice;
	private static int buyBackIndex;

	private static String inventoryView() {
		inventorySB = new StringBuilder();
		
		inventorySB.append(RenderingEngine.ENGINE.getInventoryPanel(inventoryNPC, buyback));
		
		return inventorySB.toString();
	}

	/**
	 * The main DialogueNode. From here, the player can gain access to all parts
	 * of their inventory.
	 */
	public static final DialogueNode INVENTORY_MENU = new DialogueNode("Inventory", "Return to inventory menu.", true) {
		@Override
		public String getLabel() {
			if(!Main.game.isInNewWorld()) {
				return "Evening's Attire";
			}
			
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "Inventory (Quick-Manage is <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>)";
			} else {
				return "Inventory";
			}
		}

		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if (inventoryNPC!=null && interactionType == InventoryInteraction.TRADING) {
				UtilText.nodeContentSB.append(inventoryNPC.getTraderDescription());
				
			} else if(interactionType==InventoryInteraction.CHARACTER_CREATION) {
				return CharacterCreation.getCheckingClothingDescription();
			}
//			if(item!=null) {
//				return ITEM_INVENTORY.getResponse(responseTab, index);
//			} else if(clothing!=null) {
//				if(Main.game.getPlayer().getClothingCurrentlyEquipped().contains(clothing) || (inventoryNPC!=null && inventoryNPC.getClothingCurrentlyEquipped().contains(clothing))) {
//					return CLOTHING_EQUIPPED.getResponse(responseTab, index);
//				} else {
//					return CLOTHING_INVENTORY.getResponse(responseTab, index);
//				}
//			} else if(weapon!=null) {
//				if((Main.game.getPlayer().getMainWeapon().equals(weapon) || Main.game.getPlayer().getOffhandWeapon().equals(weapon))
//						 || (inventoryNPC!=null && (inventoryNPC.getMainWeapon().equals(weapon) || inventoryNPC.getOffhandWeapon().equals(weapon)))) {
//					return WEAPON_EQUIPPED.getResponse(responseTab, index);
//				} else {
//					return WEAPON_INVENTORY.getResponse(responseTab, index);
//				}
//			}
			
			return UtilText.nodeContentSB.toString();
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			//TODO sex end
			// What does that even mean? BlobSweats
			
			if (index == 0) {
				return getCloseInventoryResponse();
			}
			
			if(responseTab==1) {
				if(item!=null) {
					return ITEM_INVENTORY.getResponse(responseTab, index);
				} else if(clothing!=null) {
					if(Main.game.getPlayer().getClothingCurrentlyEquipped().contains(clothing) || (inventoryNPC!=null && inventoryNPC.getClothingCurrentlyEquipped().contains(clothing))) {
						return CLOTHING_EQUIPPED.getResponse(responseTab, index);
					} else {
						return CLOTHING_INVENTORY.getResponse(responseTab, index);
					}
				} else if(weapon!=null) {
					if(Main.game.getPlayer().hasWeaponEquipped(weapon)
							|| (inventoryNPC!=null && inventoryNPC.hasWeaponEquipped(weapon))) {
						return WEAPON_EQUIPPED.getResponse(responseTab, index);
					} else {
						return WEAPON_INVENTORY.getResponse(responseTab, index);
					}
				} else {
					return null;
				}
			}

			StringBuilder responseSB = new StringBuilder();
			switch(interactionType) {
				case COMBAT:
					if(index == 1) {
						return new Response("Take all", "You can't do this during combat!", null);

					} else if (index == 2) {
						return new Response("Displace all", "You cannot do this while fighting someone!", null);

					} else if (index == 3) {
						return new Response("Replace all", "You cannot do this while fighting someone!", null);
						
					} else if (index == 4) {
						return new Response("Unequip all", "You cannot do this while fighting someone!", null);

					} else if (index == 5) {
						return new Response("Equip all", "You cannot do this while fighting someone!", null);

					} else {
						return null;
					}
					
				case FULL_MANAGEMENT:
					if (index == 1) {
						if(inventoryNPC == null ) {
							if((Main.game.getPlayerCell().getInventory().getInventorySlotsTaken()==0 && !Main.game.getPlayerCell().getInventory().isAnyQuestItemPresent())
									|| Main.game.isInCombat()
									|| Main.game.isInSex()) {
								return new Response("Take all", "Pick up everything on the ground.", null);

							} else {
								return new Response("Take all", "Pick up everything on the ground.", INVENTORY_MENU){
									@Override
									public void effects(){
										for(Entry<AbstractItem, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllItemsInInventory()).entrySet()) {
											Main.game.getPlayer().addItem(entry.getKey(), entry.getValue(), true, true);
										}
										for(Entry<AbstractWeapon, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory()).entrySet()) {
											Main.game.getPlayer().addWeapon(entry.getKey(), entry.getValue(), true, true);
										}
										for(Entry<AbstractClothing, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllClothingInInventory()).entrySet()) {
											Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), true, true);
										}
									}
								};
							}

						} else {
							if(inventoryNPC.getInventorySlotsTaken()==0 || Main.game.isInCombat() || Main.game.isInSex()) {
								return new Response("Take all", UtilText.parse(inventoryNPC, "Take everything from [npc.namePos] inventory."), null);

							} else {
								return new Response("Take all", UtilText.parse(inventoryNPC, "Take everything from [npc.namePos] inventory."), INVENTORY_MENU){
									@Override
									public void effects(){
										for(Entry<AbstractItem, Integer> entry : new HashMap<>(inventoryNPC.getAllItemsInInventory()).entrySet()) {
											if(!Main.game.getPlayer().isInventoryFull()
													|| Main.game.getPlayer().hasItem(entry.getKey())
													|| entry.getKey().getRarity()==Rarity.QUEST) {
												inventoryNPC.removeItem(entry.getKey(), entry.getValue());
												Main.game.getPlayer().addItem(entry.getKey(), entry.getValue(), true, true);
											}
										}
										for(Entry<AbstractClothing, Integer> entry : new HashMap<>(inventoryNPC.getAllClothingInInventory()).entrySet()) {
											if(!Main.game.getPlayer().isInventoryFull()
													|| Main.game.getPlayer().hasClothing(entry.getKey())
													|| entry.getKey().getRarity()==Rarity.QUEST) {
												inventoryNPC.removeClothing(entry.getKey(), entry.getValue());
												Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), true, true);
											}
										}
										for(Entry<AbstractWeapon, Integer> entry : new HashMap<>(inventoryNPC.getAllWeaponsInInventory()).entrySet()) {
											if(!Main.game.getPlayer().isInventoryFull()
													|| Main.game.getPlayer().hasWeapon(entry.getKey())
													|| entry.getKey().getRarity()==Rarity.QUEST) {
												inventoryNPC.removeWeapon(entry.getKey(), entry.getValue());
												Main.game.getPlayer().addWeapon(entry.getKey(), entry.getValue(), true, true);
											}
										}
									}
								};
							}
						}

					} else if (index == 2) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Displace all", "You aren't wearing any clothing, so there's nothing to displace!", null);

						} else {
							return new Response("Displace all", "Displace as much of your clothing as possible.", INVENTORY_MENU){
								@Override
								public void effects(){
									for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getClothingType().getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getDisplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 3) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Replace all", "You aren't wearing any clothing, so there's nothing to replace!", null);

						} else {
							return new Response("Replace all", "Replace as much of your clothing as possible.", INVENTORY_MENU){
								@Override
								public void effects(){

									List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator().reversed());

									for(AbstractClothing c : zlayerClothing) {
										for(BlockedParts bp : c.getClothingType().getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeReplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getReplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 4) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Unequip all", "You aren't wearing any clothing, so there's nothing to remove!", null);

						} else {
							return new Response("Unequip all", "Remove as much of your clothing as possible.", INVENTORY_MENU){
								@Override
								public void effects(){
									List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator());

									for(AbstractClothing c : zlayerClothing) { 
										Main.game.getPlayer().unequipClothingIntoInventory(c, true, Main.game.getPlayer());
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getUnequipDescription()+"</p>");
									}
								}
							};
						}

					} else if (index == 5) {
						if(Main.game.getPlayer().getAllClothingInInventory().isEmpty()) {
							return new Response("Equip all", "You don't have any clothing, so there's nothing to equip!", null);

						} else {
							return new Response("Equip all", "Equip as much of the clothing in your inventory as possible.", INVENTORY_MENU){
								@Override
								public void effects(){
									List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getAllClothingInInventory().keySet());
									zlayerClothing.sort(new ClothingZLayerComparator().reversed());
									Set<InventorySlot> slotsTaken = new HashSet<>();

									for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
										slotsTaken.add(c.getSlotEquippedTo());
									}

									for(AbstractClothing c : zlayerClothing) {
										if(!slotsTaken.contains(c.getClothingType().getEquipSlots().get(0))) {
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().equipClothingFromInventory(c, true, Main.game.getPlayer(), Main.game.getPlayer())+"</p>");
											slotsTaken.add(c.getClothingType().getEquipSlots().get(0));
										}
									}
								}
							};
						}

					} else if (index == 6 && inventoryNPC != null) {
						if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Displace all (them)", UtilText.parse(inventoryNPC, "[npc.Name] isn't wearing any clothing, so there's nothing to displace!"), null);

						} else {
							return new Response("Displace all (them)", UtilText.parse(inventoryNPC, "Displace as much of [npc.namePos] clothing as possible."), INVENTORY_MENU){
								@Override
								public void effects(){
									for(AbstractClothing c : inventoryNPC.getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getClothingType().getBlockedPartsMap(inventoryNPC, c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												inventoryNPC.isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+inventoryNPC.getDisplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 7 && inventoryNPC != null) {
						if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Replace all (them)",  UtilText.parse(inventoryNPC, "[npc.Name] isn't wearing any clothing, so there's nothing to replace!"), null);

						} else {
							return new Response("Replace all (them)", UtilText.parse(inventoryNPC, "Replace as much of [npc.namePos] clothing as possible."), INVENTORY_MENU){
								@Override
								public void effects(){

									List<AbstractClothing> zlayerClothing = new ArrayList<>(inventoryNPC.getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator().reversed());

									for(AbstractClothing c : zlayerClothing) {
										for(BlockedParts bp : c.getClothingType().getBlockedPartsMap(inventoryNPC, c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												inventoryNPC.isAbleToBeReplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+inventoryNPC.getReplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 8 && inventoryNPC != null) {
						if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Unequip all (them)", UtilText.parse(inventoryNPC, "[npc.Name] isn't wearing any clothing, so there's nothing to remove!"), null);

						} else {
							return new Response("Unequip all (them)", UtilText.parse(inventoryNPC, "Remove as much of [npc.namePos] clothing as possible."), INVENTORY_MENU){
								@Override
								public void effects(){
									List<AbstractClothing> zlayerClothing = new ArrayList<>(inventoryNPC.getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator());

									for(AbstractClothing c : zlayerClothing) { 
										inventoryNPC.unequipClothingIntoInventory(c, true, Main.game.getPlayer());
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+inventoryNPC.getUnequipDescription()+"</p>");
									}
								}
							};
						}

					} else if (index == 10 && !Main.game.isInSex() && !Main.game.isInCombat()) {
						return getQuickTradeResponse();

					} else {
						return null;
					}
				case CHARACTER_CREATION:
					if (index == 1) {
						if(Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.NIPPLES)
								|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.ANUS)
								|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.PENIS)
								|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.VAGINA)
								|| (Main.game.getPlayer().getClothingInSlot(InventorySlot.FOOT)==null && Main.game.getPlayer().getLegType().equals(LegType.HUMAN))) {
							return new Response("To the stage", "You need to be wearing clothing that covers your body, as well as a pair of shoes.", null);
							
						} else {
							return new Response("To the stage", "You're ready to approach the stage now.", CharacterCreation.CHOOSE_BACKGROUND) {
								@Override
								public int getSecondsPassed() {
									return CharacterCreation.TIME_TO_BACKGROUND;
								}
								@Override
								public void effects() {
									CharacterCreation.moveNPCIntoPlayerTile();
								}
							};
						}
						
					} else if(index == 2){
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()){
							return new Response("Unequip all", "You're currently naked, there's nothing to be unequipped.", null);
						}
						else{
							return new Response("Unequip all", "Remove as much of your clothing as possible.", INVENTORY_MENU){
								@Override
								public void effects(){
									List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator());

									for(AbstractClothing c : zlayerClothing){
										Main.game.getPlayer().unequipClothingOntoFloor(c, true, Main.game.getPlayer());
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getUnequipDescription()+"</p>");
									}
								}
							};
						}

					} else {
						return null;
					}
					
				case TRADING:
					if (index == 1) {
						if(inventoryNPC != null ||Main.game.getPlayerCell().getInventory().getInventorySlotsTaken()==0 || Main.game.isInCombat() || Main.game.isInSex()) {
							return new Response("Take all", "Pick up everything on the ground.", null);

						} else {
							return new Response("Take all", "Pick up everything on the ground.", INVENTORY_MENU){
								@Override
								public void effects(){
									for(Entry<AbstractItem, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllItemsInInventory()).entrySet()) {
										Main.game.getPlayer().addItem(entry.getKey(), entry.getValue(), true, true);
									}
									for(Entry<AbstractWeapon, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory()).entrySet()) {
										Main.game.getPlayer().addWeapon(entry.getKey(), entry.getValue(), true, true);
									}
									for(Entry<AbstractClothing, Integer> entry : new HashMap<>(Main.game.getPlayerCell().getInventory().getAllClothingInInventory()).entrySet()) {
										Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), true, true);
									}
								}
							};
						}

					} else if (index == 2) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Displace all", "You aren't wearing any clothing, so there's nothing to displace!", null);

						} else {
							return new Response("Displace all", "Displace as much of your clothing as possible.", INVENTORY_MENU){
								@Override
								public void effects(){
									for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getClothingType().getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getDisplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 3) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Replace all", "You aren't wearing any clothing, so there's nothing to replace!", null);

						} else {
							return new Response("Replace all", "Replace as much of your clothing as possible.", INVENTORY_MENU){
								@Override
								public void effects(){

									List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator().reversed());

									for(AbstractClothing c : zlayerClothing) {
										for(BlockedParts bp : c.getClothingType().getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeReplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getReplaceDescription()+"</p>");
											}
										}
									}
								}
							};
						}

					} else if (index == 4) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Unequip all", "You aren't wearing any clothing, so there's nothing to remove!", null);

						} else {
							return new Response("Unequip all", "Remove as much of your clothing as possible.", INVENTORY_MENU){
								@Override
								public void effects(){
									List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator());

									for(AbstractClothing c : zlayerClothing) { 
										Main.game.getPlayer().unequipClothingIntoInventory(c, true, Main.game.getPlayer());
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getUnequipDescription()+"</p>");
									}
								}
							};
						}

					} else if (index == 5) {
						if(Main.game.getPlayer().getAllClothingInInventory().isEmpty()) {
							return new Response("Equip all", "You don't have any clothing, so there's nothing to equip!", null);

						} else {
							return new Response("Equip all", "Equip as much of the clothing in your inventory as possible.", INVENTORY_MENU){
								@Override
								public void effects(){
									List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getAllClothingInInventory().keySet());
									zlayerClothing.sort(new ClothingZLayerComparator().reversed());
									Set<InventorySlot> slotsTaken = new HashSet<>();

									for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
										slotsTaken.add(c.getSlotEquippedTo());
									}

									for(AbstractClothing c : zlayerClothing) {
										if(!slotsTaken.contains(c.getClothingType().getEquipSlots().get(0))) {
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().equipClothingFromInventory(c, true, Main.game.getPlayer(), Main.game.getPlayer())+"</p>");
											slotsTaken.add(c.getClothingType().getEquipSlots().get(0));
										}
									}
								}
							};
						}

					} else if (index == 9 && inventoryNPC!=null) {
						return getBuybackResponse();

					} else if (index == 10 && !Main.game.isInSex() && !Main.game.isInCombat()) {
						return getQuickTradeResponse();

					} else {
						return null;
					}
				case SEX:
					if(index == 1) {
						return new Response("Take all", "Pick up everything on the ground.", null);

					} else if (index == 2) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Displace all", "You aren't wearing any clothing, so there's nothing to displace!", null);

						} else {
							return new Response("Displace all", "Displace as much of your clothing as possible.", Sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									responseSB.setLength(0);

									for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getClothingType().getBlockedPartsMap(Main.game.getPlayer(), c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												Main.game.getPlayer().isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												responseSB.append("<p style='text-align:center;'>"+Main.game.getPlayer().getDisplaceDescription()+"</p>");
											}
										}
									}

									Sex.setUnequipClothingText(null, responseSB.toString());
									Main.mainController.openInventory();
									Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Sex.setSexStarted(true);
								}
							};
						}

					} else if (index == 3) {
						return new Response("Replace all", "You can't replace clothing in sex!", null);

					} else if (index == 4) {
						if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Unequip all", "You aren't wearing any clothing, so there's nothing to remove!", null);

						} else {
							return new Response("Unequip all", "Remove as much of your clothing as possible.", Sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									responseSB.setLength(0);

									List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator());

									for(AbstractClothing c : zlayerClothing) { 
										if(!c.getSlotEquippedTo().isJewellery()) {
											Main.game.getPlayer().unequipClothingIntoInventory(c, true, Main.game.getPlayer());
											responseSB.append("<p style='text-align:center;'>"+Main.game.getPlayer().getUnequipDescription()+"</p>");
										}
									}

									Sex.setUnequipClothingText(null, responseSB.toString());
									Main.mainController.openInventory();
									Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Sex.setSexStarted(true);
								}
							};
						}

					} else if (index == 5) {
						return new Response("Equip all", "You can't equip clothing in sex!", null);

					} else if (index == 6 && inventoryNPC != null) {
						if(!Sex.getSexManager().isAbleToRemoveOthersClothing(Main.game.getPlayer(), null)) {
							return new Response("Displace all (them)", UtilText.parse(inventoryNPC, "You can't displace [npc.namePos] clothing in this sex scene!"), null);

						} else if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Displace all (them)", UtilText.parse(inventoryNPC, "[npc.Name] isn't wearing any clothing, so there's nothing to displace!"), null);

						} else {
							return new Response("Displace all (them)", UtilText.parse(inventoryNPC, "Displace as much of [npc.namePos] clothing as possible."), Sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									responseSB.setLength(0);

									for(AbstractClothing c : inventoryNPC.getClothingCurrentlyEquipped()) {
										for(BlockedParts bp : c.getClothingType().getBlockedPartsMap(inventoryNPC, c.getSlotEquippedTo())) {
											if(bp.displacementType != DisplacementType.REMOVE_OR_EQUIP) {
												inventoryNPC.isAbleToBeDisplaced(c, bp.displacementType, true, true, Main.game.getPlayer());
												responseSB.append("<p style='text-align:center;'>"+inventoryNPC.getDisplaceDescription()+"</p>");
											}
										}
									}

									Sex.setUnequipClothingText(null, responseSB.toString());
									Main.mainController.openInventory();
									Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Sex.setSexStarted(true);
								}
							};
						}

					} else if (index == 7 && inventoryNPC != null) {
						return new Response("Replace all (them)", "You can't replace clothing in sex!", null);

					} else if (index == 8 && inventoryNPC != null) {
						if(!Sex.getSexManager().isAbleToRemoveOthersClothing(Main.game.getPlayer(), null)) {
							return new Response("Unequip all (them)", UtilText.parse(inventoryNPC, "You can't unequip [npc.namePos] clothing in this sex scene!"), null);

						} else if(inventoryNPC.getClothingCurrentlyEquipped().isEmpty()) {
							return new Response("Unequip all (them)", UtilText.parse(inventoryNPC, "[npc.Name] isn't wearing any clothing, so there's nothing to remove!"), null);

						} else {
							return new Response("Unequip all (them)", UtilText.parse(inventoryNPC, "Remove as much of [npc.namePos] clothing as possible."), Sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									responseSB.setLength(0);

									List<AbstractClothing> zlayerClothing = new ArrayList<>(inventoryNPC.getClothingCurrentlyEquipped());
									zlayerClothing.sort(new ClothingZLayerComparator());

									for(AbstractClothing c : zlayerClothing) { 
										if(!c.getSlotEquippedTo().isJewellery()) {
											inventoryNPC.unequipClothingIntoInventory(c, true, Main.game.getPlayer());
											responseSB.append("<p style='text-align:center;'>"+inventoryNPC.getUnequipDescription()+"</p>");
										}
									}

									Sex.setUnequipClothingText(null, responseSB.toString());
									Main.mainController.openInventory();
									Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Sex.setSexStarted(true);
								}
							};
						}

					} else {
						return null;
					}
			}
			
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static final DialogueNode ITEM_INVENTORY = new DialogueNode("Item", "", true) {

		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "Inventory (Quick-Manage is <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>)";
			} else {
				return "Inventory";
			}
		}

		@Override
		public String getHeaderContent() {
			return inventoryView();
		}
		
		@Override
		public String getContent() {
			return getItemDisplayPanel(item.getSVGString(),
					item.getDisplayName(true),
					item.getDescription()
					+ item.getExtraDescription(owner, owner)
					+ (owner!=null && owner.isPlayer()
							? (inventoryNPC != null && interactionType == InventoryInteraction.TRADING
									? inventoryNPC.willBuy(item) && item.getItemType().isAbleToBeSold()
											? "<p>"
												+ inventoryNPC.getName("The") + " will buy it for " + UtilText.formatAsMoney(item.getPrice(inventoryNPC.getBuyModifier())) + "."
											+ "</p>" 
											: inventoryNPC.getName("The") + " doesn't want to buy this."
										: "")
							:(inventoryNPC != null && interactionType == InventoryInteraction.TRADING
								? "<p>"
										+ inventoryNPC.getName("The") + " will sell it for " + UtilText.formatAsMoney(item.getPrice(inventoryNPC.getSellModifier())) + "."
									+ "</p>" 
								: "")));
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasItem(item);
					
					switch(interactionType) {
						case SEX:
							String dropTitle = owner.getLocationPlace().isItemsDisappear()?"Drop ":"Store";
							if(index == 1) {
								return new Response(dropTitle+"(1)", "You can't drop items while masturbating.", null);
								
							} else if(index == 2) {
								return new Response(dropTitle+"(5)", "You can't drop items while masturbating.", null);
								
							} else if(index == 3) {
								return new Response(dropTitle+"(All)", "You can't drop items while masturbating.", null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant items while masturbating.", null);
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedInSex()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", "You cannot use this during sex!", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUsingItemText(Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false));
											resetPostAction();
											Main.mainController.openInventory();
											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
											Sex.setSexStarted(true);
										}
									};
								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", "You can only use one item at a time during sex!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
						
						default:
							if(index == 1) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("Drop (1)", "You cannot drop the " + item.getName() + "!", null);
									} else if(areaFull) {
										return new Response("Drop (1)", "This area is full, so you can't drop your " + item.getName() + " here!", null);
									} else {
										return new Response("Drop (1)", "Drop your " + item.getName() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, 1);
											}
										};
									}
								} else {
									if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("Store (1)", "You cannot drop the " + item.getName() + "!", null);
									} else if(areaFull) {
										return new Response("Store (1)", "This area is full, so you can't store your " + item.getName() + " here!", null);
									} else {
										return new Response("Store (1)", "Store the " + item.getName() + " in this area.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, 1);
											}
										};
									}
								}
								
							} else if(index == 2) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(owner.getItemCount(item) < 5) {
										return new Response("Drop (5)", "You don't have five " + item.getNamePlural() + " to give!", null);
										
									} else if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("Drop (5)", "You cannot drop the " + item.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Drop (5)", "This area is full, so you can't drop your " + item.getNamePlural() + " here!", null);
										
									} else {
										return new Response("Drop (5)", "Drop five of your " + item.getNamePlural() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, 5);
											}
										};
									}
								} else {
									if(owner.getItemCount(item) < 5) {
										return new Response("Store (5)", "You don't have five " + item.getNamePlural() + " to give!", null);
										
									} else if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("Store (5)", "You cannot drop the " + item.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Store (5)", "This area is full, so you can't store your " + item.getNamePlural() + " here!", null);
										
									} else {
										return new Response("Store (5)", "Store five of your " + item.getNamePlural() + " in this area.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, 5);
											}
										};
									}
								}
								
							} else if(index == 3) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("Drop (All)", "You cannot drop the " + item.getName() + "!", null);
									} else if(areaFull) {
										return new Response("Drop (All)", "This area is full, so you can't drop your " + item.getNamePlural() + " here!", null);
									} else {
										return new Response("Drop (All)", "Drop all of your " + item.getNamePlural() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, owner.getItemCount(item));
											}
										};
									}
								} else {
									if(!item.getItemType().isAbleToBeDropped()) {
										return new Response("Store (All)", "You cannot drop the " + item.getName() + "!", null);
									} else if(areaFull) {
										return new Response("Store (All)", "This area is full, so you can't store your " + item.getNamePlural() + " here!", null);
									} else {
										return new Response("Store (All)", "Store all of your " + item.getNamePlural() + " in this area.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropItems(owner, item, owner.getItemCount(item));
											}
										};
									}
								}
								
							} else if(index == 5) {
								if(item.getEnchantmentItemType(null)==null) {
									return new Response("Enchant", "This item cannot be enchanted!", null);
									
								} else if(Main.game.isDebugMode()) {
									return new Response("Enchant", "Enchant this item.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(item);
										}
									};
									
								} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
										return new Response("Enchant", "Enchant this item.", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public DialogueNode getNextDialogue() {
												return EnchantmentDialogue.getEnchantmentMenu(item);
											}
										};
									}
								}
								
								return null;
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												resetPostAction();
											}
										};
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" all (self)",
												item.getItemType().getUseTooltipDescription(owner, owner)
													+"<br/>[style.italicsMinorGood(Repeat this for all of the " + item.getNamePlural() + " which are in your inventory.)]"){
											@Override
											public void effects(){
												int itemCount = Main.game.getPlayer().getItemCount(item);
												for(int i=0;i<itemCount;i++) {
													Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												}
												resetPostAction();
											}
										};
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)",
											item.getItemType().getUseTooltipDescription(owner, owner)
												+"<br/>[style.italicsMinorGood(Repeat this for all of the " + item.getNamePlural() + " which are in your inventory.)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("Give (1)", "You can't give someone items while fighting them!", null);
								
							} else if(index == 2) {
								return new Response("Give (5)", "You can't give someone items while fighting them!", null);
								
							} else if(index == 3) {
								return new Response("Give (All)", "You can't give someone items while fighting them!", null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant items while fighting someone!", null);
								
							} else if(index == 6) {
								if(Main.game.getPlayer().isStunned()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", "You cannot use any items while you're stunned!", null);
									
								} else if(Combat.isCombatantDefeated(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", "You cannot use any items while you're defeated!", null);
									
								} else if(Main.game.getPlayer().getRemainingAP()<CombatMove.ITEM_USAGE.getAPcost(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", "You need at least "+CombatMove.ITEM_USAGE.getAPcost(Main.game.getPlayer())+" AP to use this actions!", null);
									
								} else if (!item.isAbleToBeUsedInCombat()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", "You cannot use this during combat!", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											Combat.ENEMY_ATTACK){
										@Override
										public void effects(){
											Combat.addItemToBeUsed(owner, owner, item);
											resetPostAction();
											Main.mainController.openInventory();
										}
									};
								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", "You can only use one item at a time during combat!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if(Main.game.getPlayer().isStunned()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", "You cannot use any items while you're stunned!", null);
									
								} else if(Combat.isCombatantDefeated(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", "You cannot use any items while you're defeated!", null);
									
								} else if(Main.game.getPlayer().getRemainingAP()<CombatMove.ITEM_USAGE.getAPcost(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", "You need at least "+CombatMove.ITEM_USAGE.getAPcost(Main.game.getPlayer())+" AP to use this actions!", null);
									
								} else if (!item.isAbleToBeUsedInCombat()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (opponent)", "You cannot use this during combat!", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (opponent)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (opponent)", item.getUnableToBeUsedDescription(inventoryNPC), null);

								} else if(item.getItemType().isFetishGiving()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (opponent)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											Combat.ENEMY_ATTACK,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Combat.addItemToBeUsed(owner, inventoryNPC, item);
											resetPostAction();
											Main.mainController.openInventory();
										}
									};
								} else if(item.getItemType().isTransformative()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (opponent)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											Combat.ENEMY_ATTACK,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Combat.addItemToBeUsed(owner, inventoryNPC, item);
											resetPostAction();
											Main.mainController.openInventory();
										}
									};
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (opponent)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											Combat.ENEMY_ATTACK){
										@Override
										public void effects(){
											Combat.addItemToBeUsed(owner, inventoryNPC, item);
											resetPostAction();
											Main.mainController.openInventory();
										}
									};
								}
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (opponent)", "You can only use one item at a time during combat!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:  case CHARACTER_CREATION:
							boolean inventoryFull = inventoryNPC.isInventoryFull() && !inventoryNPC.hasItem(item);
							
							if(index == 1) {
								if(!item.getItemType().isAbleToBeDropped()) {
									return new Response("Give (1)", "You cannot give away the " + item.getName() + "!", null);
								} else if(inventoryFull) {
									return new Response("Give (1)", UtilText.parse(inventoryNPC, "[npc.NamePos] inventory is already full!"), null);
								}
								return new Response("Give (1)", UtilText.parse(inventoryNPC, "Give [npc.name] one " + item.getName() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(Main.game.getPlayer(), inventoryNPC, item, 1);
									}
								};
								
							} else if(index == 2) {
								if(!item.getItemType().isAbleToBeDropped()) {
									return new Response("Give (5)", "You cannot give away the " + item.getName() + "!", null);
								} else if(inventoryFull) {
									return new Response("Give (5)", UtilText.parse(inventoryNPC, "[npc.NamePos] inventory is already full!"), null);
								}
								if(Main.game.getPlayer().getItemCount(item) >= 5) {
									return new Response("Give (5)", UtilText.parse(inventoryNPC, "Give [npc.name] five of your " + item.getNamePlural() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											transferItems(Main.game.getPlayer(), inventoryNPC, item, 5);
										}
									};
								} else {
									return new Response("Give (5)", "You don't have five " + item.getNamePlural() + " to give!", null);
								}
								
							} else if(index == 3) {
								if(!item.getItemType().isAbleToBeDropped()) {
									return new Response("Give (All)", "You cannot give away the " + item.getName() + "!", null);
								} else if(inventoryFull) {
									return new Response("Give (All)", UtilText.parse(inventoryNPC, "[npc.NamePos] inventory is already full!"), null);
								}
								return new Response("Give (All)", UtilText.parse(inventoryNPC, "Give [npc.name] all of your " + item.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(Main.game.getPlayer(), inventoryNPC, item, Main.game.getPlayer().getItemCount(item));
									}
								};
								
							} else if(index == 5) {
								if(item.getEnchantmentItemType(null)==null) {
									return new Response("Enchant", "This item cannot be enchanted!", null);
									
								} else if(Main.game.isDebugMode()) {
									return new Response("Enchant", "Enchant this item.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(item);
										}
									};
									
								} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
										return new Response("Enchant", "Enchant this item.", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public DialogueNode getNextDialogue() {
												return EnchantmentDialogue.getEnchantmentMenu(item);
											}
										};
									}
								}
								
								return null;
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												resetPostAction();
											}
										};
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											item.getItemType().getUseTooltipDescription(owner, owner)
												+"<br/>[style.italicsMinorGood(Repeat this for all of the " + item.getNamePlural() + " which are in your inventory.)]"){
											@Override
											public void effects(){
												int itemCount = Main.game.getPlayer().getItemCount(item);
												for(int i=0;i<itemCount;i++) {
													Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												}
												resetPostAction();
											}
										};
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)",
											item.getItemType().getUseTooltipDescription(owner, owner)
												+"<br/>[style.italicsMinorGood(Repeat this for all of the " + item.getNamePlural() + " which are in your inventory.)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (them)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)", item.getUnableToBeUsedDescription(inventoryNPC), null);
									
								} else if(item.isBreakOutOfInventory()) {
									return new ResponseEffectsOnly(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)",
											item.getItemType().getUseTooltipDescription(owner, owner)){
										@Override
										public void effects(){
											Main.game.getPlayer().useItem(item, inventoryNPC, false);
											resetPostAction();
										}
									};
									
								} else if(item.getItemType().isFetishGiving()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC));
											resetPostAction();
										}
									};
								} else if(item.getItemType().isTransformative()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC));
											resetPostAction();
										}
									};
									
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC));
											resetPostAction();
										}
									};
								}
							} else if(index == 12) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (them)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (them)", item.getUnableToBeUsedDescription(inventoryNPC), null);
									
								} else if(item.isBreakOutOfInventory()) {
									return new ResponseEffectsOnly(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" all (them)",
											item.getItemType().getUseTooltipDescription(owner, owner)){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getPlayer().useItem(item, inventoryNPC, false);
											}
											resetPostAction();
										}
									};
									
								} else if(item.getItemType().isFetishGiving()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (them)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC)
												+"<br/>[style.italicsMinorGood(Repeat this for all of the " + item.getNamePlural() + " which are in your inventory.)]",
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC));
											}
											resetPostAction();
										}
									};
								} else if(item.getItemType().isTransformative()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (them)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC)
												+"<br/>[style.italicsMinorGood(Repeat this for all of the " + item.getNamePlural() + " which are in your inventory.)]",
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC));
											}
											resetPostAction();
										}
									};
									
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (them)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC)
												+"<br/>[style.italicsMinorGood(Repeat this for all of the " + item.getNamePlural() + " which are in your inventory.)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC));
											}
											resetPostAction();
										}
									};
								}
								
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("Give (1)", "You can't give someone items while having sex with them!", null);
								
							} else if(index == 2) {
								return new Response("Give (5)", "You can't give someone items while having sex with them!", null);
								
							} else if(index == 3) {
								return new Response("Give (All)", "You can't give someone items while having sex with them!", null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant items while having sex with someone!", null);
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedInSex()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", "You cannot use this during sex!", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUsingItemText(((NPC)Sex.getTargetedPartner(Main.game.getPlayer())).getItemUseEffects(item, owner, Main.game.getPlayer(), Main.game.getPlayer()));
											resetPostAction();
											Main.mainController.openInventory();
											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
											Sex.setSexStarted(true);
										}
									};
								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", "You can only use one item at a time during sex!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if (!item.isAbleToBeUsedInSex()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (partner)", "You cannot use this during sex!", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (partner)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)", item.getUnableToBeUsedDescription(inventoryNPC), null);
									
								} else if(item.getItemType().isFetishGiving()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											Sex.SEX_DIALOGUE,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Sex.setUsingItemText(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC));
											resetPostAction();
											Main.mainController.openInventory();
											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
											Sex.setSexStarted(true);
										}
									};
									
								} else if(item.getItemType().isTransformative()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											Sex.SEX_DIALOGUE,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Sex.setUsingItemText(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC));
											resetPostAction();
											Main.mainController.openInventory();
											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
											Sex.setSexStarted(true);
										}
									};
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)",
											item.getItemType().getUseTooltipDescription(owner, inventoryNPC),
											Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUsingItemText(inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC));
											resetPostAction();
											Main.mainController.openInventory();
											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
											Sex.setSexStarted(true);
										}
									};
								}
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (partner)", "You can only use one item at a time during sex!", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							if(index == 1) {
								if(!item.getItemType().isAbleToBeSold()) {
									return new Response("Sell (1)", "You cannot sell the " + item.getName() + "!", null);
									
								} else if (inventoryNPC.willBuy(item)) {
									int sellPrice = item.getPrice(inventoryNPC.getBuyModifier());
									return new Response("Sell (1) (" + UtilText.formatAsMoney(sellPrice, "span") + ")", "Sell the " + item.getName() + " for " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											sellItems(Main.game.getPlayer(), inventoryNPC, item, 1, sellPrice);
										}
									};
								} else {
									return new Response("Sell (1)", inventoryNPC.getName("The") + " doesn't want to buy this.", null);
								}
								
							} else if(index == 2) {
								if(Main.game.getPlayer().getItemCount(item) >= 5) {
									if(!item.getItemType().isAbleToBeSold()) {
										return new Response("Sell (5)", "You cannot sell the " + item.getName() + "!", null);
										
									} else if (inventoryNPC.willBuy(item)) {
										int sellPrice = item.getPrice(inventoryNPC.getBuyModifier());
										return new Response("Sell (5) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "Sell five of your " + item.getNamePlural() + " for " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												sellItems(Main.game.getPlayer(), inventoryNPC, item, 5, sellPrice);
											}
										};
									} else {
										return new Response("Sell (5)", inventoryNPC.getName("The") + " doesn't want to buy these.", null);
									}
									
								} else {
									return new Response("Sell (5)", "You don't have five " + item.getNamePlural() + " to sell!", null);
								}
								
							} else if(index == 3) {
								if(!item.getItemType().isAbleToBeSold()) {
									return new Response("Sell (All)", "You cannot sell the " + item.getName() + "!", null);
									
								} else if (inventoryNPC.willBuy(item)) {
									int sellPrice = item.getPrice(inventoryNPC.getBuyModifier());
									return new Response("Sell (All) (" + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getItemCount(item), "span") + ")",
											"Sell the " + item.getName() + " for " + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getItemCount(item)) + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											sellItems(Main.game.getPlayer(), inventoryNPC, item, Main.game.getPlayer().getItemCount(item), sellPrice);
										}
									};
								} else {
									return new Response("Sell (All)", inventoryNPC.getName("The") + " doesn't want to buy these.", null);
								}
								
							} else if(index == 5) {
								if(item.getEnchantmentItemType(null)==null) {
									return new Response("Enchant", "This item cannot be enchanted!", null);
									
								} else if(Main.game.isDebugMode()) {
									return new Response("Enchant", "Enchant this item.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(item);
										}
									};
									
								} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
										return new Response("Enchant", "Enchant this item.", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public DialogueNode getNextDialogue() {
												return EnchantmentDialogue.getEnchantmentMenu(item);
											}
										};
									}
								}
								
								return null;
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												resetPostAction();
											}
										};
									}
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											item.getItemType().getUseTooltipDescription(owner, owner),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												int itemCount = Main.game.getPlayer().getItemCount(item);
												for(int i=0;i<itemCount;i++) {
													Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false);
												}
												resetPostAction();
											}
										};
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)",
											item.getItemType().getUseTooltipDescription(owner, owner)
												+"<br/>[style.italicsMinorGood(Repeat this for all of the " + item.getNamePlural() + " which are in your inventory.)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)", UtilText.parse(inventoryNPC, "[npc.Name] doesn't want to use your items."), null);
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" all (them)", UtilText.parse(inventoryNPC, "[npc.Name] doesn't want to use your items."), null);
								
							} else {
								return null;
							}
					}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ****************************** TODO
				
			} else {
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item) && item.getRarity()!=Rarity.QUEST;
					
					switch(interactionType) {
						case SEX:
							if(index == 1) {
								return new Response("Take (1)", "You can't pick up items while masturbating.", null);
								
							} else if(index == 2) {
								return new Response("Take (5)", "You can't pick up items while masturbating.", null);
								
							} else if(index == 3) {
								return new Response("Take (All)", "You can't pick up items while masturbating.", null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant items while masturbating.", null);
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedInSex()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", "You cannot use this during sex!", null);
									
								} else if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer()),
											Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUsingItemText(Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true));
											resetPostAction();
											Main.mainController.openInventory();
											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
											Sex.setSexStarted(true);
										}
									};
								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", "You can only use one item at a time during sex!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					
						default:
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Take (1)", "Your inventory is already full!", null);
								}
								return new Response("Take (1)", "Take one " + item.getName() + " from the ground.", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpItems(Main.game.getPlayer(), item, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("Take (5)", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayerCell().getInventory().getItemCount(item) >= 5) {
									return new Response("Take (5)", "Take five of the " + item.getNamePlural() + " from the ground.", INVENTORY_MENU){
										@Override
										public void effects(){
											pickUpItems(Main.game.getPlayer(), item, 5);
										}
									};
								} else {
									return new Response("Take (5)", "There aren't five " + item.getNamePlural() + " on the ground!", null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("Take (All)", "Your inventory is already full!", null);
								}
								return new Response("Take (All)", "Take all of the " + item.getNamePlural() + " from the ground.", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpItems(Main.game.getPlayer(), item, Main.game.getPlayerCell().getInventory().getItemCount(item));
									}
								};
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant items on the ground!", null);
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
												item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer())){
											@Override
											public void effects(){
												Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true);
												resetPostAction();
											}
										};
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer()),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
									
								} else {
									if(item.isBreakOutOfInventory()) {
										return new ResponseEffectsOnly(
												Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
												item.getItemType().getUseTooltipDescription(owner, owner)){
											@Override
											public void effects(){
												int itemCount = Main.game.getPlayerCell().getInventory().getItemCount(item);
												for(int i=0;i<itemCount;i++) {
													Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true);
												}
												resetPostAction();
											}
										};
									}
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer())
											+"<br/>[style.italicsMinorGood(Repeat this for all of the " + item.getNamePlural() + " which are in this area.)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayerCell().getInventory().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					boolean inventoryFull = false;
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("Take (1)", "You can't take someone items while fighting them!", null);
								
							} else if(index == 2) {
								return new Response("Take (5)", "You can't take someone items while fighting them!", null);
								
							} else if(index == 3) {
								return new Response("Take (All)", "You can't take someone items while fighting them!", null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant someone else's items, especially not while fighting them!", null);
								
							} else if(index == 6) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", "You can't use someone else's items while fighting them!", null);
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" all (self)", "You can't use someone else's items while fighting them!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (opponent)", "You can't use make someone use an item while fighting them!", null);
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" all (opponent)", "You can't use make someone use an item while fighting them!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:  case CHARACTER_CREATION:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item) && item.getRarity()!=Rarity.QUEST;
						
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Take (1)", "Your inventory is already full!", null);
								}
								return new Response("Take (1)", UtilText.parse(inventoryNPC, "Take " + item.getName() + " from [npc.name]."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(inventoryNPC, Main.game.getPlayer(), item, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("Take (5)", "Your inventory is already full!", null);
								}
								if(inventoryNPC.getItemCount(item) >= 5) {
									return new Response("Take (5)", UtilText.parse(inventoryNPC, "Take five of  [npc.namePos] " + item.getNamePlural() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											transferItems(inventoryNPC, Main.game.getPlayer(), item, 5);
										}
									};
								} else {
									return new Response("Take (5)", UtilText.parse(inventoryNPC, "[npc.Name] doesn't have five " + item.getNamePlural() + "!"), null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("Take (All)", "Your inventory is already full!", null);
								}
								return new Response("Take (All)", UtilText.parse(inventoryNPC, "Take all "+Util.intToString(inventoryNPC.getItemCount(item))+" of  [npc.namePos] " + item.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(inventoryNPC, Main.game.getPlayer(), item, inventoryNPC.getItemCount(item));
									}
								};
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant items owned by someone else!", null);
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer()),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.useItem(item, Main.game.getPlayer(), false) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 7) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), Main.game.getPlayer())
												+"<br/>[style.italicsMinorGood(Repeat this for all of the " + item.getNamePlural() + " which are in [npc.namePos] inventory.)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = inventoryNPC.getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.useItem(item, Main.game.getPlayer(), false) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (them)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if (!item.isAbleToBeUsed(inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)", item.getUnableToBeUsedDescription(inventoryNPC), null);
									
								} else if(item.isBreakOutOfInventory()) {
									return new ResponseEffectsOnly(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)",
											item.getItemType().getUseTooltipDescription(owner, owner)){
										@Override
										public void effects(){
											Main.game.getPlayer().useItem(item, inventoryNPC, false);
											resetPostAction();
										}
									};
									
								} else if(item.getItemType().isFetishGiving()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC),
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC) + "</p>");
											resetPostAction();
										}
									};
								} else if(item.getItemType().isTransformative()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC),
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC) + "</p>");
											resetPostAction();
										}
									};
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC) + "</p>");
											resetPostAction();
										}
									};
								}
								
							} else if(index == 12) {
								if (!item.isAbleToBeUsedFromInventory()) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (them)", item.getUnableToBeUsedFromInventoryDescription(), null);
									
								} else if(!item.isAbleToBeUsed(inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (them)", item.getUnableToBeUsedDescription(inventoryNPC), null);
									
								} else if(item.isBreakOutOfInventory()) {
									return new ResponseEffectsOnly(
											Util.capitaliseSentence(item.getItemType().getUseName()) +" all (them)",
											item.getItemType().getUseTooltipDescription(owner, owner)){
										@Override
										public void effects(){
											int itemCount = Main.game.getPlayer().getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getPlayer().useItem(item, inventoryNPC, false);
											}
											resetPostAction();
										}
									};
									
								} else if(item.getItemType().isFetishGiving()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+" all (them)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC)
												+"<br/>[style.italicsMinorGood(Repeat this for all of the " + item.getNamePlural() + " which are in [npc.namePos] inventory.)]",
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
											Fetish.FETISH_KINK_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											int itemCount = inventoryNPC.getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC) + "</p>");
											}
											resetPostAction();
										}
									};
									
								} else if(item.getItemType().isTransformative()) {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+" all (them)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC)
												+"<br/>[style.italicsMinorGood(Repeat this for all of the " + item.getNamePlural() + " which are in [npc.namePos] inventory.)]",
											INVENTORY_MENU,
											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
											null,
											null,
											null){
										@Override
										public void effects(){
											int itemCount = inventoryNPC.getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC) + "</p>");
											}
											resetPostAction();
										}
									};
									
								} else {
									return new Response(
											Util.capitaliseSentence(item.getItemType().getUseName())+" all (them)",
											item.getItemType().getUseTooltipDescription(Main.game.getPlayer(), inventoryNPC)
												+"<br/>[style.italicsMinorGood(Repeat this for all of the " + item.getNamePlural() + " which are in [npc.namePos] inventory.)]",
											INVENTORY_MENU){
										@Override
										public void effects(){
											int itemCount = inventoryNPC.getItemCount(item);
											for(int i=0;i<itemCount;i++) {
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.getItemUseEffects(item, owner, Main.game.getPlayer(), inventoryNPC) + "</p>");
											}
											resetPostAction();
										}
									};
								}
								
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("Take (1)", "You can't take someone's items while having sex with them!", null);
								
							} else if(index == 2) {
								return new Response("Take (5)", "You can't take someone's items while having sex with them!", null);
								
							} else if(index == 3) {
								return new Response("Take (All)", "You can't take someone's items while having sex with them!", null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant someone else's items, especially not while having sex with them!", null);
								
							} else if(index == 6) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", "You can't use your partner's items during sex!", null);
								//TODO
//								if (!item.isAbleToBeUsedInSex()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", "This cannot be used during sex!", null);
//									
//								} else if (!item.isAbleToBeUsedFromInventory()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (self)", item.getUnableToBeUsedFromInventoryDescription(), null);
//									
//								} else if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
//									
//								} else {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
//											Util.capitaliseSentence(item.getItemType().getUseName()) + " the " + item.getName() + ".", Sex.SEX_DIALOGUE){
//										@Override
//										public void effects(){
//											Sex.setUsingItemText(Sex.getPartner().getItemUseEffects(item, owner, inventoryNPC, Main.game.getPlayer()));
//											resetPostAction();
//											Main.mainController.openInventory();
//											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
//											Sex.setSexStarted(true);
//										}
//									};
//								}
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (self)", "You can only use one item at a time during sex!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (partner)", "You can't use your partner's items during sex!", null);
								//TODO
//								if (!item.isAbleToBeUsedInSex()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (partner)", "This cannot be used during sex!", null);
//									
//								} else if (!item.isAbleToBeUsedFromInventory()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" (partner)", item.getUnableToBeUsedFromInventoryDescription(), null);
//									
//								} else if (!item.isAbleToBeUsed(inventoryNPC)) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)", item.getUnableToBeUsedDescription(inventoryNPC), null);
//									
//								} else if(item.getItemType().isFetishGiving()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)",
//											"Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".",
//											Sex.SEX_DIALOGUE,
//											Util.newArrayListOfValues(Fetish.FETISH_KINK_GIVING),
//											Fetish.v.getAssociatedCorruptionLevel(),
//											null,
//											null,
//											null){
//										@Override
//										public void effects(){
//											Sex.setUsingItemText(Sex.getPartner().getItemUseEffects(item, owner, inventoryNPC, inventoryNPC));
//											resetPostAction();
//											Main.mainController.openInventory();
//											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
//											Sex.setSexStarted(true);
//										}
//									};
//								} else if(item.getItemType().isTransformative()) {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)",
//											"Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".",
//											Sex.SEX_DIALOGUE,
//											Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_GIVING),
//											Fetish.FETISH_TRANSFORMATION_GIVING.getAssociatedCorruptionLevel(),
//											null,
//											null,
//											null){
//										@Override
//										public void effects(){
//											Sex.setUsingItemText(Sex.getPartner().getItemUseEffects(item, owner, inventoryNPC, inventoryNPC));
//											resetPostAction();
//											Main.mainController.openInventory();
//											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
//											Sex.setSexStarted(true);
//										}
//									};
//									
//								} else {
//									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)",
//											"Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".", Sex.SEX_DIALOGUE){
//										@Override
//										public void effects(){
//											Sex.setUsingItemText(Sex.getPartner().getItemUseEffects(item, owner, inventoryNPC, inventoryNPC));
//											resetPostAction();
//											Main.mainController.openInventory();
//											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
//											Sex.setSexStarted(true);
//										}
//									};
//								}
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all (partner)", "You can only use one item at a time during sex!", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item)  && item.getRarity()!=Rarity.QUEST;
							
							if(index == 1) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():item.getPrice(inventoryNPC.getSellModifier());
								if(inventoryFull) {
									return new Response("Buy (1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
									return new Response("Buy (1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "You can't afford to buy this!", null);
								}
								return new Response("Buy (1) (" + UtilText.formatAsMoney(sellPrice, "span") + ")", "Buy the " + item.getName() + " for " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellItems(inventoryNPC, Main.game.getPlayer(), item, 1, sellPrice);
									}
								};
								
							} else if(index == 2) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():item.getPrice(inventoryNPC.getSellModifier());
								if((buyback && Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<5)
										|| (!buyback && inventoryNPC.getItemCount(item) < 5)) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", UtilText.parse(inventoryNPC, "[npc.Name] doesn't have five "+item.getNamePlural()+"."), null);
								}
								if(inventoryFull) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*5) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "You can't afford to buy this!", null);
								}
								return new Response("Buy (5) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "Buy the " + item.getName() + " for " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellItems(inventoryNPC, Main.game.getPlayer(), item, 5, sellPrice);
									}
								};
								
							} else if(index == 3) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():item.getPrice(inventoryNPC.getSellModifier());
								int count = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount():inventoryNPC.getItemCount(item);
								if(inventoryFull) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*count) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "span")+")", "You can't afford to buy this!", null);
								}
								return new Response("Buy (All) (" + UtilText.formatAsMoney(sellPrice*count, "span") + ")",
										"Buy the " + item.getName() + " for " + UtilText.formatAsMoney(sellPrice*count) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellItems(inventoryNPC, Main.game.getPlayer(), item, count, sellPrice);
									}
								};
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant someone else's item!", null);
								
							} else if(index == 6) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to let you use [npc.her] items without buying them first."), null);
								
							} else if(index == 7) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" all (self)", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to let you use [npc.her] items without buying them first."), null);
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to use the items that [npc.sheIs] trying to sell!"), null);
								
							} else if(index == 12) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" all (them)", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to use the items that [npc.sheIs] trying to sell!"), null);
								
							} else {
								return null;
							}
					}
				}
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static final DialogueNode WEAPON_INVENTORY = new DialogueNode("Weapon", "", true) {
		

		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "Inventory (Quick-Manage is <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>)";
			} else {
				return "Inventory";
			}
		}
		
		@Override
		public String getHeaderContent() {
			return inventoryView();
		}
		
		@Override
		public String getContent() {
			return getItemDisplayPanel(weapon.getSVGString(),
					weapon.getDisplayName(true),
					weapon.getDescription()
					+ (owner!=null && owner.isPlayer()
							? (inventoryNPC != null && interactionType == InventoryInteraction.TRADING
									? inventoryNPC.willBuy(weapon)
											? "<p>"
												+ inventoryNPC.getName("The") + " will buy it for " + UtilText.formatAsMoney(weapon.getPrice(inventoryNPC.getBuyModifier())) + "."
											+ "</p>" 
											: inventoryNPC.getName("The") + " doesn't want to buy this."
										: "")
							:(inventoryNPC != null && interactionType == InventoryInteraction.TRADING
								? "<p>"
										+ inventoryNPC.getName("The") + " will sell it for " + UtilText.formatAsMoney(weapon.getPrice(inventoryNPC.getSellModifier())) + "."
									+ "</p>" 
								: "")));
		}


		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasWeapon(weapon);
					
					switch(interactionType) {
						case SEX:
							String dropTitle = owner.getLocationPlace().isItemsDisappear()?"Drop ":"Store";
							if(index == 1) {
								return new Response(dropTitle+"(1)", "You can't drop your weapons while masturbating.", null);
								
							} else if(index == 2) {
								return new Response(dropTitle+"(5)", "You can't drop your weapons while masturbating.", null);
								
							} else if(index == 3) {
								return new Response(dropTitle+"(All)", "You can't drop your weapons while masturbating.", null);
								
							} else if(index == 4) {
								return new Response("Dye/Reforge", "You can't dye or reforge your weapons while masturbating.", null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant weapons while masturbating.", null);
								
							} else if(index == 6) {
								return new Response("Equip Main (self)", "You can't equip weapons while masturbating.", null);
								
							} else if(index == 7) {
								return new Response("Equip Offhand (self)", "You can't equip weapons while masturbating.", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
							
						default:
							if(index == 1) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("Drop (1)", "You cannot drop the " + weapon.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Drop (1)", "This area is full, so you can't drop your " + weapon.getName() + " here!", null);
										
									} else {
										return new Response("Drop (1)", "Drop your " + weapon.getName() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, 1);
											}
										};
									}
								} else {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("Store (1)", "You cannot drop the " + weapon.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Store (1)", "This area is full, so you can't store your " + weapon.getName() + " here!", null);
									} else {
										return new Response("Store (1)", "Store the " + weapon.getName() + " in this area.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, 1);
											}
										};
									}
								}
								
							} else if(index == 2) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("Drop (5)", "You cannot drop the " + weapon.getName() + "!", null);
										
									} else if(owner.getWeaponCount(weapon) < 5) {
										return new Response("Drop (5)", "You don't have five " + weapon.getNamePlural() + " to drop!", null);
										
									} else if(areaFull) {
										return new Response("Drop (5)", "This area is full, so you can't drop your " + weapon.getNamePlural() + " here!", null);
										
									} else {
										return new Response("Drop (5)", "Drop five of your " + weapon.getNamePlural() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, 5);
											}
										};
									}
								} else {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("Store (5)", "You cannot drop the " + weapon.getName() + "!", null);
										
									} else if(owner.getWeaponCount(weapon) < 5) {
										return new Response("Store (5)", "You don't have five " + weapon.getNamePlural() + " to drop!", null);
										
									} else if(areaFull) {
										return new Response("Store (5)", "This area is full, so you can't store your " + weapon.getNamePlural() + " here!", null);
										
									} else {
										return new Response("Store (5)", "Store five of your " + weapon.getNamePlural() + " in this area.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, 5);
											}
										};
									}
								}
								
							} else if(index == 3) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Drop (All)", "You cannot drop the " + weapon.getName() + "!", null);
									
								} else if(owner.getLocationPlace().isItemsDisappear()) {
									if(areaFull) {
										return new Response("Drop (All)", "This area is full, so you can't drop your " + weapon.getNamePlural() + " here!", null);
									} else {
										return new Response("Drop (All)", "Drop all of your " + weapon.getNamePlural() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, owner.getWeaponCount(weapon));
											}
										};
									}
								} else {
									if(!weapon.getWeaponType().isAbleToBeDropped()) {
										return new Response("Store (All)", "You cannot drop the " + weapon.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Store (All)", "This area is full, so you can't store your " + weapon.getNamePlural() + " here!", null);
									} else {
										return new Response("Store (All)", "Store all of your " + weapon.getNamePlural() + " in this area.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropWeapons(owner, weapon, owner.getWeaponCount(weapon));
											}
										};
									}
								}
								
							} else if (index==4) {
								if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
										|| Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
										|| Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Dye/Reforge",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Use your proficiency with [style.colourEarth(Earth spells)] to dye or reforge this item."
													:"Use a dye-brush or reforge hammer to alter this weapon's properties.",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("Dye/Reforge", "Your inventory is full, so you can't alter this weapon's properties.", null);
									}
								} else {
									return new Response("Dye/Reforge", "You'll need to find a dye-brush or reforge hammer if you want to alter this weapon's properties.", null);
								}
								
							} else if(index == 5) {
								if(weapon.getEnchantmentItemType(null)==null) {
									return new Response("Enchant", "This weapon cannot be enchanted!", null);
									
								} else if(Main.game.isDebugMode()) {
									return new Response("Enchant", "Enchant this weapon.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(weapon);
										}
									};
									
								} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
										return new Response("Enchant", "Enchant this weapon.", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public DialogueNode getNextDialogue() {
												return EnchantmentDialogue.getEnchantmentMenu(weapon);
											}
										};
									}
								}
								
								return null;
								
							} else if(index == 6) {
									return new Response("Equip Main (self)", "Equip the " + weapon.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
									
							} else if(index == 7) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("Equip Offhand (self)",
											(weapon.getWeaponType().isPlural()
												?"As the " + weapon.getName() + " require two hands to wield, they can only be equipped in the main slot!"
												:"As the " + weapon.getName() + " is a two-handed weapon, it can only be equipped in the main slot!"),
											null);
								}
								return new Response("Equip Offhand (self)",
										"Equip the " + weapon.getName() + "."
											+(weapon.getWeaponType().isTwoHanded()
													?"<br/>[style.italicsGood(Although "+(weapon.getWeaponType().isPlural()?"":"")+")]"
													:""),
										INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
												+ Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer())
											+ "</p>");
										resetPostAction();
									}
								};
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("Give (1)", "You can't give someone weapons while fighting them!", null);
								
							} else if(index == 2) {
								return new Response("Give (5)", "You can't give someone weapons while fighting them!", null);
								
							} else if(index == 3) {
								return new Response("Give (All)", "You can't give someone weapons while fighting them!", null);
								
							} else if(index == 4) {
								return new Response("Dye", "You can't dye your weapons while fighting someone!", null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant weapons while fighting someone!", null);
								
							} else if(index == 6) {
								return new Response("Equip Main (self)", "You can't change weapons while fighting someone!", null);
									
							} else if(index == 7) {
								return new Response("Equip Offhand (self)", "You can't change weapons while fighting someone!", null);
									
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("Equip (opponent)", "You can't make your opponent equip a weapon!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:  case CHARACTER_CREATION:
							boolean inventoryFull = inventoryNPC.isInventoryFull() && !inventoryNPC.hasWeapon(weapon);
							
							if(index == 1) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Give (1)", "You cannot give away the " + weapon.getName() + "!", null);
									
								} else if(inventoryFull) {
									return new Response("Give (1)", UtilText.parse(inventoryNPC, "[npc.NamePos] inventory is already full!"), null);
								}
								return new Response("Give (1)", UtilText.parse(inventoryNPC, "Give [npc.name] one " + weapon.getName() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(Main.game.getPlayer(), inventoryNPC, weapon, 1);
									}
								};
								
							} else if(index == 2) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Give (5)", "You cannot give away the " + weapon.getName() + "!", null);
									
								} else if(inventoryFull) {
									return new Response("Give (5)", UtilText.parse(inventoryNPC, "[npc.NamePos] inventory is already full!"), null);
								}
								if(Main.game.getPlayer().getWeaponCount(weapon) >= 5) {
									return new Response("Give (5)", UtilText.parse(inventoryNPC, "Give [npc.name] five of your " + weapon.getNamePlural() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											transferWeapons(Main.game.getPlayer(), inventoryNPC, weapon, 5);
										}
									};
								} else {
									return new Response("Give (5)", "You don't have five " + weapon.getNamePlural() + " to give!", null);
								}
								
							} else if(index == 3) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Give (All)", "You cannot give away the " + weapon.getName() + "!", null);
									
								} else if(inventoryFull) {
									return new Response("Give (All)", UtilText.parse(inventoryNPC, "[npc.NamePos] inventory is already full!"), null);
								}
								return new Response("Give (All)", UtilText.parse(inventoryNPC, "Give [npc.name] all of your " + weapon.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(Main.game.getPlayer(), inventoryNPC, weapon, Main.game.getPlayer().getWeaponCount(weapon));
									}
								};
								
							} else if (index==4) {
								if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
										|| Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
										|| Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Dye/Reforge",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Use your proficiency with [style.colourEarth(Earth spells)] to dye or reforge this item."
													:"Use a dye-brush or reforge hammer to alter this weapon's properties.",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("Dye/Reforge", "Your inventory is full, so you can't alter this weapon's properties.", null);
									}
								} else {
									return new Response("Dye/Reforge", "You'll need to find a dye-brush or reforge hammer if you want to alter this weapon's properties.", null);
								}
								
							} else if(index == 5) {
								if(weapon.getEnchantmentItemType(null)==null) {
									return new Response("Enchant", "This weapon cannot be enchanted!", null);
									
								} else if(Main.game.isDebugMode()) {
									return new Response("Enchant", "Enchant this weapon.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(weapon);
										}
									};
									
								} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
										return new Response("Enchant", "Enchant this weapon.", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public DialogueNode getNextDialogue() {
												return EnchantmentDialogue.getEnchantmentMenu(weapon);
											}
										};
									}
								}
								
								return null;
								
							} else if(index == 6) {
								return new Response("Equip Main (self)", "Equip the " + weapon.getName() + " as your main weapon.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
													+ Main.game.getPlayer().equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
												+ "</p>");
											resetPostAction();
										}
									};
									
							} else if(index == 7) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("Equip Offhand (self)",
											(weapon.getWeaponType().isPlural()
												?"As the " + weapon.getName() + " require two hands to wield, they can only be equipped in the main slot!"
												:"As the " + weapon.getName() + " is a two-handed weapon, it can only be equipped in the main slot!"),
											null);
								}
								return new Response("Equip Offhand (self)", "Equip the " + weapon.getName() + " as your offhand weapon.", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
												+ Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer())
											+ "</p>");
										resetPostAction();
									}
								};
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response(UtilText.parse(inventoryNPC, "Equip Main ([npc.Name])"), "You cannot give away the " + weapon.getName() + "!", null);
								}
								
								return new Response(UtilText.parse(inventoryNPC, "Equip Main ([npc.Name])"), UtilText.parse(inventoryNPC, "Make [npc.name] equip the "+weapon.getName()+" as [npc.her] main weapon."), INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
											+ inventoryNPC.equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
											+ "</p>");
										resetPostAction();
									}
								};
							
							} else if(index == 12) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response(UtilText.parse(inventoryNPC, "Equip Main ([npc.Name])"), "You cannot give away the " + weapon.getName() + "!", null);
								}
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("Equip Offhand (self)",
											(weapon.getWeaponType().isPlural()
												?"As the " + weapon.getName() + " require two hands to wield, they can only be equipped in the main slot!"
												:"As the " + weapon.getName() + " is a two-handed weapon, it can only be equipped in the main slot!"),
											null);
								}
								return new Response(UtilText.parse(inventoryNPC, "Equip Offhand ([npc.Name])"), UtilText.parse(inventoryNPC, "Make [npc.name] equip the "+weapon.getName()+" as [npc.her] offhand weapon."), INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
											+ inventoryNPC.equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer())
											+ "</p>");
										resetPostAction();
									}
								};
							
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("Give (1)", "You can't give someone weapons while having sex with them!", null);
								
							} else if(index == 2) {
								return new Response("Give (5)", "You can't give someone weapons while having sex with them!", null);
								
							} else if(index == 3) {
								return new Response("Give (All)", "You can't give someone weapons while having sex with them!", null);
								
							} else if(index == 4) {
								return new Response("Dye", "You can't dye your weapons while having sex with someone!", null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant weapons while having sex with someone!", null);
								
							} else if(index == 6) {
								return new Response("Equip Main (self)", "You can't equip weapons while having sex with someone!", null);
								
							} else if(index == 7) {
								return new Response("Equip Offhand (self)", "You can't equip weapons while having sex with someone!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip ([npc.Name])"), "You can't equip weapons while having sex with someone!", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							if(index == 1) {
								if(!weapon.getWeaponType().isAbleToBeSold()) {
									return new Response("Sell (1)", "You cannot sell the " + weapon.getName() + "!", null);
									
								} else if (inventoryNPC.willBuy(weapon)) {
									int sellPrice = weapon.getPrice(inventoryNPC.getBuyModifier());
									return new Response("Sell (1) (" + UtilText.formatAsMoney(sellPrice, "span") + ")", "Sell the " + weapon.getName() + " for " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											sellWeapons(Main.game.getPlayer(), inventoryNPC, weapon, 1, sellPrice);
										}
									};
								} else {
									return new Response("Sell (1)", inventoryNPC.getName("The") + " doesn't want to buy this.", null);
								}
								
							} else if(index == 2) {
								if(Main.game.getPlayer().getWeaponCount(weapon) >= 5) {
									if(!weapon.getWeaponType().isAbleToBeSold()) {
										return new Response("Sell (5)", "You cannot sell the " + weapon.getName() + "!", null);
										
									} else if (inventoryNPC.willBuy(weapon)) {
										int sellPrice = weapon.getPrice(inventoryNPC.getBuyModifier());
										return new Response("Sell (5) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "Sell five of your " + weapon.getNamePlural() + " for " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												sellWeapons(Main.game.getPlayer(), inventoryNPC, weapon, 5, sellPrice);
											}
										};
									} else {
										return new Response("Sell (5)", inventoryNPC.getName("The") + " doesn't want to buy these.", null);
									}
									
								} else {
									return new Response("Sell (5)", "You don't have five " + weapon.getNamePlural() + " to sell!", null);
								}
								
							} else if(index == 3) {
								if(!weapon.getWeaponType().isAbleToBeSold()) {
									return new Response("Sell (All)", "You cannot sell the " + weapon.getName() + "!", null);
									
								} else if (inventoryNPC.willBuy(weapon)) {
									int sellPrice = weapon.getPrice(inventoryNPC.getBuyModifier());
									return new Response("Sell (All) (" + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getWeaponCount(weapon), "span") + ")",
											"Sell the " + weapon.getName() + " for " + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getWeaponCount(weapon)) + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											sellWeapons(Main.game.getPlayer(), inventoryNPC, weapon, Main.game.getPlayer().getWeaponCount(weapon), sellPrice);
										}
									};
								} else {
									return new Response("Sell (All)", inventoryNPC.getName("The") + " doesn't want to buy these.", null);
								}
								
							} else if (index==4) {
								if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
										|| Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
										|| Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Dye/Reforge",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Use your proficiency with [style.colourEarth(Earth spells)] to dye or reforge this item."
													:"Use a dye-brush or reforge hammer to alter this weapon's properties.",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("Dye/Reforge", "Your inventory is full, so you can't alter this weapon's properties.", null);
									}
								} else {
									return new Response("Dye/Reforge", "You'll need to find a dye-brush or reforge hammer if you want to alter this weapon's properties.", null);
								}
								
							} else if(index == 5) {
								if(weapon.getEnchantmentItemType(null)==null) {
									return new Response("Enchant", "This weapon cannot be enchanted!", null);
									
								} else if(Main.game.isDebugMode()) {
									return new Response("Enchant", "Enchant this weapon.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(weapon);
										}
									};
									
								} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
										return new Response("Enchant", "Enchant this weapon.", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public DialogueNode getNextDialogue() {
												return EnchantmentDialogue.getEnchantmentMenu(weapon);
											}
										};
									}
								}
								
								return null;
								
							} else if(index == 6) {
								return new Response("Equip Main (self)", "Equip the " + weapon.getName() + " as your main weapon.", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
												+ Main.game.getPlayer().equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
											+ "</p>");
										resetPostAction();
									}
								};
									
							} else if(index == 7) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("Equip Offhand (self)",
											(weapon.getWeaponType().isPlural()
												?"As the " + weapon.getName() + " require two hands to wield, they can only be equipped in the main slot!"
												:"As the " + weapon.getName() + " is a two-handed weapon, it can only be equipped in the main slot!"),
											null);
								}
								return new Response("Equip Offhand (self)", "Equip the " + weapon.getName() + " as your offhand weapon.", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
												+ Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer())
											+ "</p>");
										resetPostAction();
									}
								};
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip Main ([npc.Name])"), UtilText.parse(inventoryNPC, "[npc.Name] doesn't want to use your weapons."), null);
								
							} else if(index == 12) {
								return new Response(UtilText.parse(inventoryNPC, "Equip Offhand ([npc.Name])"), UtilText.parse(inventoryNPC, "[npc.Name] doesn't want to use your weapons."), null);
								
							} else {
								return null;
							}
					}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon) && weapon.getRarity()!=Rarity.QUEST;

					switch(interactionType) {
						case SEX:
							if(index == 1) {
								return new Response("Take (1)", "You can't pick up weapons while masturbating.", null);
								
							} else if(index == 2) {
								return new Response("Take (5)", "You can't pick up weapons while masturbating.", null);
								
							} else if(index == 3) {
								return new Response("Take (All)", "You can't pick up weapons while masturbating.", null);
								
							} else if(index == 4) {
								return new Response("Dye", "You can't dye weapons while masturbating.", null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant weapons while masturbating.", null);
								
							} else if(index == 6) {
								return new Response("Equip Main (self)", "You can't equip weapons while masturbating.", null);
								
							} else if(index == 7) {
								return new Response("Equip Offhand (self)", "You can't equip weapons while masturbating.", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
							
						default:
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Take (1)", "Your inventory is already full!", null);
								}
								return new Response("Take (1)", "Take one " + weapon.getName() + " from the ground.", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpWeapons(Main.game.getPlayer(), weapon, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("Take (5)", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayerCell().getInventory().getWeaponCount(weapon) >= 5) {
									return new Response("Take (5)", "Take five of the " + weapon.getNamePlural() + " from the ground.", INVENTORY_MENU){
										@Override
										public void effects(){
											pickUpWeapons(Main.game.getPlayer(), weapon, 5);
										}
									};
								} else {
									return new Response("Take (5)", "There aren't five " + weapon.getNamePlural() + " on the ground!", null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("Take (All)", "Your inventory is already full!", null);
								}
								return new Response("Take (All)", "Take all of the " + weapon.getNamePlural() + " from the ground.", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpWeapons(Main.game.getPlayer(), weapon, Main.game.getPlayerCell().getInventory().getWeaponCount(weapon));
									}
								};
								
							} else if (index==4) {
								if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
										|| Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
										|| Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
									boolean hasFullInventory = Main.game.getPlayerCell().getInventory().isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Dye/Reforge",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Use your proficiency with [style.colourEarth(Earth spells)] to dye or reforge this item."
													:"Use a dye-brush or reforge hammer to alter this weapon's properties.",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("Dye/Reforge", "Your inventory is full, so you can't alter this weapon's properties.", null);
									}
								} else {
									return new Response("Dye/Reforge", "You'll need to find a dye-brush or reforge hammer if you want to alter this weapon's properties.", null);
								}
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant weapons on the ground!", null);
								
							} else if(index == 6) {
								return new Response("Equip Main (self)", "Equip the " + weapon.getName() + " as your main weapon.", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
											+ Main.game.getPlayer().equipMainWeaponFromFloor(weapon)
											+ "</p>");
										resetPostAction();
									}
								};
									
							} else if(index == 7) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("Equip Offhand (self)",
											(weapon.getWeaponType().isPlural()
												?"As the " + weapon.getName() + " require two hands to wield, they can only be equipped in the main slot!"
												:"As the " + weapon.getName() + " is a two-handed weapon, it can only be equipped in the main slot!"),
											null);
								}
								return new Response("Equip Offhand (self)", "Equip the " + weapon.getName() + " as your offhand weapon.", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
											+ Main.game.getPlayer().equipOffhandWeaponFromFloor(weapon)
											+ "</p>");
										resetPostAction();
									}
								};
									
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					boolean inventoryFull = false;
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("Take (1)", "You can't take someone weapons while fighting them!", null);
								
							} else if(index == 2) {
								return new Response("Take (5)", "You can't take someone weapons while fighting them!", null);
								
							} else if(index == 3) {
								return new Response("Take (All)", "You can't take someone weapons while fighting them!", null);
								
							} else if(index == 4) {
								return new Response("Dye", "You can't dye someone's weapons while fighting them!", null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant someone else's weapons, especially not while fighting them!", null);
								
							} else if(index == 6) {
								return new Response("Equip Main (self)", "You can't use someone else's weapons while fighting them!", null);
								
							} else if(index == 7) {
								return new Response("Equip Offhand (self)", "You can't use someone else's weapons while fighting them!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("Equip Main (opponent)", "You can't use make someone use a weapon while fighting them!", null);
								
							} else if(index == 12) {
								return new Response("Equip Offhand (opponent)", "You can't use make someone use a weapon while fighting them!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:  case CHARACTER_CREATION:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon) && weapon.getRarity()!=Rarity.QUEST;
						
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Take (1)", "Your inventory is already full!", null);
								}
								return new Response("Take (1)", UtilText.parse(inventoryNPC, "Take one " + weapon.getName() + " from [npc.name]."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(inventoryNPC, Main.game.getPlayer(), weapon, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("Take (5)", "Your inventory is already full!", null);
								}
								if(inventoryNPC.getWeaponCount(weapon) >= 5) {
									return new Response("Take (5)", UtilText.parse(inventoryNPC, "Take five of  [npc.namePos] " + weapon.getNamePlural() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											transferWeapons(inventoryNPC, Main.game.getPlayer(), weapon, 5);
										}
									};
								} else {
									return new Response("Take (5)", UtilText.parse(inventoryNPC, "[npc.Name] doesn't have five " + weapon.getNamePlural() + "!"), null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("Take (All)", "Your inventory is already full!", null);
								}
								return new Response("Take (All)", UtilText.parse(inventoryNPC, "Take all "+Util.intToString(inventoryNPC.getWeaponCount(weapon))+" of  [npc.namePos] " + weapon.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(inventoryNPC, Main.game.getPlayer(), weapon, inventoryNPC.getWeaponCount(weapon));
									}
								};
								
							} else if (index==4) {
								if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
										|| Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
										|| Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
									boolean hasFullInventory = inventoryNPC.isInventoryFull() && weapon.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = inventoryNPC.getAllWeaponsInInventory().get(weapon) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Dye/Reforge",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Use your proficiency with [style.colourEarth(Earth spells)] to dye or reforge this item."
													:"Use a dye-brush or reforge hammer to alter this weapon's properties.",
												DYE_WEAPON) {
											@Override
											public void effects() {
												resetWeaponDyeColours();
											}
										};
									} else {
										return new Response("Dye/Reforge", UtilText.parse(inventoryNPC, "[npc.NamePos] inventory is full, so you can't alter this weapon's properties."), null);
									}
								} else {
									return new Response("Dye/Reforge", UtilText.parse(inventoryNPC, "You'll need to find a dye-brush or reforge hammer if you want to alter the properties of [npc.namePos] weapons."), null);
								}
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant weapons owned by someone else!", null);
								
							} else if(index == 6) {
								return new Response("Equip Main (self)", "Equip the " + weapon.getName() + " as your main weapon.", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
											+ Main.game.getPlayer().equipMainWeaponFromInventory(weapon, inventoryNPC)
											+ "</p>");
										resetPostAction();
									}
								};
								
							} else if(index == 7) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response("Equip Offhand (self)",
											(weapon.getWeaponType().isPlural()
												?"As the " + weapon.getName() + " require two hands to wield, they can only be equipped in the main slot!"
												:"As the " + weapon.getName() + " is a two-handed weapon, it can only be equipped in the main slot!"),
											null);
								}
								return new Response("Equip Offhand (self)", "Equip the " + weapon.getName() + " as your offhand weapon.", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
											+ Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, inventoryNPC)
											+ "</p>");
										resetPostAction();
									}
								};
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip Main ([npc.Name])"), UtilText.parse(inventoryNPC, "Get [npc.name] to equip the " + weapon.getName() + " as [npc.her] main weapon."), INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
											+ inventoryNPC.equipMainWeaponFromInventory(weapon, inventoryNPC)
											+ "</p>");
										resetPostAction();
									}
								};
								
							} else if(index == 12) {
								if(weapon.getWeaponType().isTwoHanded()) {
									return new Response(UtilText.parse(inventoryNPC, "Equip Offhand ([npc.Name])"),
											(weapon.getWeaponType().isPlural()
												?"As the " + weapon.getName() + " require two hands to wield, they can only be equipped in the main slot!"
												:"As the " + weapon.getName() + " is a two-handed weapon, it can only be equipped in the main slot!"),
											null);
								}
								return new Response(UtilText.parse(inventoryNPC, "Equip Offhand ([npc.Name])"), UtilText.parse(inventoryNPC, "Get [npc.name] to equip the " + weapon.getName() + " as [npc.her] offhand weapon."), INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
											+ inventoryNPC.equipOffhandWeaponFromInventory(weapon, inventoryNPC)
											+ "</p>");
										resetPostAction();
									}
								};
								
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("Take (1)", "You can't take someone's weapons while having sex with them!", null);
								
							} else if(index == 2) {
								return new Response("Take (5)", "You can't take someone's weapons while having sex with them!", null);
								
							} else if(index == 3) {
								return new Response("Take (All)", "You can't take someone's weapons while having sex with them!", null);
								
							} else if(index == 4) {
								return new Response("Dye", "You can't dye someone's weapons while having sex with them!", null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant someone else's weapons, especially not while having sex with them!", null);
								
							} else if(index == 6) {
								return new Response("Equip Main (self)", "You can't use someone else's weapons while having sex with them!", null);
								
							} else if(index == 7) {
								return new Response("Equip Offhand (self)", "You can't use someone else's weapons while having sex with them!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("Equip Main (opponent)", "You can't use make someone use a weapon while having sex with them!", null);
								
							} else if(index == 12) {
								return new Response("Equip Offhand (opponent)", "You can't use make someone use a weapon while having sex with them!", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon) && weapon.getRarity()!=Rarity.QUEST;
							
							if(index == 1) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():weapon.getPrice(inventoryNPC.getSellModifier());
								if(inventoryFull) {
									return new Response("Buy (1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
									return new Response("Buy (1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "You can't afford to buy this!", null);
								}
								return new Response("Buy (1) (" + UtilText.formatAsMoney(sellPrice, "span") + ")", "Buy the " + weapon.getName() + " for " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellWeapons(inventoryNPC, Main.game.getPlayer(), weapon, 1, sellPrice);
									}
								};
								
							} else if(index == 2) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():weapon.getPrice(inventoryNPC.getSellModifier());
								if((buyback && Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<5)
										|| (!buyback && inventoryNPC.getWeaponCount(weapon) < 5)) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", UtilText.parse(inventoryNPC, "[npc.Name] doesn't have five "+weapon.getNamePlural()+"."), null);
								}
								if(inventoryFull) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*5) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "You can't afford to buy this!", null);
								}
								return new Response("Buy (5) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "Buy the " + weapon.getName() + " for " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellWeapons(inventoryNPC, Main.game.getPlayer(), weapon, 5, sellPrice);
									}
								};
								
							} else if(index == 3) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():weapon.getPrice(inventoryNPC.getSellModifier());
								int count = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount():inventoryNPC.getWeaponCount(weapon);
								if(inventoryFull) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*count) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "span")+")", "You can't afford to buy this!", null);
								}
								return new Response("Buy (All) (" + UtilText.formatAsMoney(sellPrice*count, "span") + ")",
										"Buy the " + weapon.getName() + " for " + UtilText.formatAsMoney(sellPrice*count) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellWeapons(inventoryNPC, Main.game.getPlayer(), weapon, count, sellPrice);
									}
								};
								
							} else if(index == 4) {
								return new Response("Dye", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to let you dye the weapon that [npc.sheIs] trying to sell!"), null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant someone else's weapon!", null);
								
							} else if(index == 6) {
								return new Response("Equip Main (self)", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to let you equip [npc.her] weapons without buying them first."), null);
								
							} else if(index == 7) {
								return new Response("Equip Offhand (self)", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to let you equip [npc.her] weapons without buying them first."), null);
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip Main ([npc.Name])"), UtilText.parse(inventoryNPC, "[npc.Name] isn't going to equip the weapons that [npc.sheIs] trying to sell!"), null);
								
							} else if(index == 12) {
								return new Response(UtilText.parse(inventoryNPC, "Equip Offhand ([npc.Name])"), UtilText.parse(inventoryNPC, "[npc.Name] isn't going to equip the weapons that [npc.sheIs] trying to sell!"), null);
								
							} else {
								return null;
							}
					}
				}
			}
			return null;
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static final DialogueNode CLOTHING_INVENTORY = new DialogueNode("Clothing", "", true) {

		@Override
		public String getLabel() {
			if(!Main.game.isInNewWorld()) {
				return "Evening's Attire";
			}
			
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "Inventory (Quick-Manage is <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>)";
			} else {
				return "Inventory";
			}
		}
		
		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			return getItemDisplayPanel(clothing.getSVGString(),
					clothing.getDisplayName(true),
					clothing.getDescription()
					+ clothing.clothingExtraInformation(null, clothing.getClothingType().getEquipSlots().get(0))
					+ (owner!=null && owner.isPlayer()
							? (inventoryNPC != null && interactionType == InventoryInteraction.TRADING
									? inventoryNPC.willBuy(clothing)
											? "<p>"
												+ inventoryNPC.getName("The") + " will buy it for " + UtilText.formatAsMoney(clothing.getPrice(inventoryNPC.getBuyModifier())) + "."
											+ "</p>" 
											: inventoryNPC.getName("The") + " doesn't want to buy this."
										: "")
							:(inventoryNPC != null && interactionType == InventoryInteraction.TRADING
								? "<p>"
										+ inventoryNPC.getName("The") + " will sell it for " + UtilText.formatAsMoney(clothing.getPrice(inventoryNPC.getSellModifier())) + "."
									+ "</p>" 
								: "")))
					+(interactionType==InventoryInteraction.CHARACTER_CREATION?CharacterCreation.getCheckingClothingDescription():"");
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);

					switch(interactionType) {
						case SEX:
							String dropTitle = owner.getLocationPlace().isItemsDisappear()?"Drop ":"Store";
							if(index == 1) {
								return new Response(dropTitle+"(1)", "You can't drop clothing while masturbating.", null);
								
							} else if(index == 2) {
								return new Response(dropTitle+"(5)", "You can't drop clothing while masturbating.", null);
								
							} else if(index == 3) {
								return new Response(dropTitle+"(All)", "You can't drop clothing while masturbating.", null);
								
							} else if(index == 4) {
								return new Response("Dye", "You can't dye your clothing while masturbating.", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair the condom while masturbating.", null);
									}
									return new Response("Sabotage", "You can't sabotage the condom while masturbating.", null);
								}
								return new Response("Enchant", "You can't enchant clothing while masturbating.", null);

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) { //TODO
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									if(clothing.getClothingType().isAbleToBeEquippedDuringSex(slot)) {
										if(!Sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer())) {
											return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "As this is a special sex scene, you cannot equip clothing during it!", null);
										}
										if (Main.game.getPlayer().isAbleToEquip(clothing, false, Main.game.getPlayer())) {
											return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "Equip the " + clothing.getName() + ".", Sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
													Sex.setEquipClothingText(c, Main.game.getPlayer().getUnequipDescription());
													Main.mainController.openInventory();
													Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Sex.setSexStarted(true);
												}
											};
										} else {
											return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "You can't equip the " + clothing.getName() + ", as other clothing is blocking you from doing so!", null);
										}
									} else {
										return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "You can't equip clothing while having sex with someone!", null);
									}
								} else {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else {
								return null;
							}
					default:
							if(index == 1) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Drop (1)", "You cannot drop the " + clothing.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Drop (1)", "This area is full, so you can't drop your " + clothing.getName() + " here!", null);
									} else {
										return new Response("Drop (1)", "Drop your " + clothing.getName() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, 1);
											}
										};
									}
								} else {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Store (1)", "You cannot drop the " + clothing.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Store (1)", "This area is full, so you can't store your " + clothing.getName() + " here!", null);
									} else {
										return new Response("Store (1)", "Store the " + clothing.getName() + " in this area.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, 1);
											}
										};
									}
								}
								
							} else if(index == 2) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Drop (5)", "You cannot drop the " + clothing.getName() + "!", null);
										
									} else if(owner.getClothingCount(clothing) < 5) {
										return new Response("Drop (5)", "You don't have five " + clothing.getNamePlural() + " to give!", null);
										
									} else if(areaFull) {
										return new Response("Drop (5)", "This area is full, so you can't drop your " + clothing.getNamePlural() + " here!", null);
										
									} else {
										return new Response("Drop (5)", "Drop five of your " + clothing.getNamePlural() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, 5);
											}
										};
									}
								} else {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Store (5)", "You cannot drop the " + clothing.getName() + "!", null);
										
									} else if(owner.getClothingCount(clothing) < 5) {
										return new Response("Store (5)", "You don't have five " + clothing.getNamePlural() + " to give!", null);
										
									} else if(areaFull) {
										return new Response("Store (5)", "This area is full, so you can't store your " + clothing.getNamePlural() + " here!", null);
										
									} else {
										return new Response("Store (5)", "Store five of your " + clothing.getNamePlural() + " in this area.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, 5);
											}
										};
									}
								}
								
							} else if(index == 3) {
								if(owner.getLocationPlace().isItemsDisappear()) {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Drop (All)", "You cannot drop the " + clothing.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Drop (All)", "This area is full, so you can't drop your " + clothing.getNamePlural() + " here!", null);
									} else {
										return new Response("Drop (All)", "Drop all of your " + clothing.getNamePlural() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, owner.getClothingCount(clothing));
											}
										};
									}
								} else {
									if(!clothing.getClothingType().isAbleToBeDropped()) {
										return new Response("Store (All)", "You cannot drop the " + clothing.getName() + "!", null);
										
									} else if(areaFull) {
										return new Response("Store (All)", "This area is full, so you can't store your " + clothing.getNamePlural() + " here!", null);
									} else {
										return new Response("Store (All)", "Store all of your " + clothing.getNamePlural() + " in this area.", INVENTORY_MENU){
											@Override
											public void effects(){
												dropClothing(owner, clothing, owner.getClothingCount(clothing));
											}
										};
									}
								}
								
							} else if (index==4) {
								if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && clothing.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllClothingInInventory().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Dye",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Use your proficiency with [style.colourEarth(Earth spells)] to dye this item of clothing."
													:"Use a dye-brush to dye this item of clothing.",
												DYE_CLOTHING) {
											@Override
											public void effects() {
												resetClothingDyeColours();
											}
										};
									} else {
										return new Response("Dye", "Your inventory is full, so you can't dye this item of clothing.", null);
									}
								} else {
									return new Response("Dye", "You'll need to find a dye-brush if you want to dye your clothes.", null);
								}
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									return getCondomSabotageResponse(clothing);
								}
								if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									if(clothing.getEnchantmentItemType(null)==null) {
										return new Response("Enchant", "This clothing cannot be enchanted!", null);
										
									} else if(!clothing.isEnchantmentKnown()) {
										if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE) >= IDENTIFICATION_ESSENCE_PRICE) {
											return new Response("Identify ([style.italicsArcane("+IDENTIFICATION_ESSENCE_PRICE+" Essences)])",
													"To identify the "+clothing.getName()+", you can either spend "+IDENTIFICATION_ESSENCE_PRICE+" arcane essences to do it yourself,"
															+ " or go to a vendor and pay "+IDENTIFICATION_PRICE+" flames to have them do it for you.",
													CLOTHING_INVENTORY) {
												@Override
												public void effects() {
													Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -IDENTIFICATION_ESSENCE_PRICE, false);
													
													String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
													
													clothing = AbstractClothing.enchantmentRemovedClothing;
													
													Main.game.getTextEndStringBuilder().append(
															"<p>"
																+ "You channel the power of "+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+" of your arcane essences into the "+clothing.getName()
																	+", and as it emits a faint purple glow, you find yourself able to detect what sort of enchantment it has!"
															+ "</p>"
															+ enchantmentRemovedString
															+ "<p style='text-align:center;'>"
																+ "Identifying the "+clothing.getName()+" has cost you [style.boldBad("+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+")] [style.boldArcane(Arcane Essences)]!"
															+ "</p>");
													RenderingEngine.setPage(Main.game.getPlayer(), clothing);
												}
											};
										} else {
											return new Response("Identify (<i>"+IDENTIFICATION_ESSENCE_PRICE+" Essences</i>)",
													"To identify the "+clothing.getName()+", you can either spend "+IDENTIFICATION_ESSENCE_PRICE+" arcane essences to do it yourself ([style.italicsBad(which you don't have)]),"
															+ " or go to a vendor and pay "+IDENTIFICATION_PRICE+" flames to have them do it for you.", null);
										}
									}
									return new Response("Enchant", "Enchant this clothing.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(clothing);
										}
									};
								}
								
								return null;
								
							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "Equip the " + clothing.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								} else {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("Give (1)", "You can't give someone clothing while fighting them!", null);
								
							} else if(index == 2) {
								return new Response("Give (5)", "You can't give someone clothing while fighting them!", null);
								
							} else if(index == 3) {
								return new Response("Give (All)", "You can't give someone clothing while fighting them!", null);
								
							} else if(index == 4) {
								return new Response("Dye", "You can't dye your clothing while fighting someone!", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair the condom while fighting someone!", null);
									}
									return new Response("Sabotage", "You can't sabotage the condom while fighting someone!", null);
								}
								return new Response("Enchant", "You can't enchant clothing while fighting someone!", null);
								
							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "You cannot change your clothes while fighting someone!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								return new Response("Equip: "+Util.capitaliseSentence(slot.getName())+" (opponent)", "You can't make your opponent equip clothing while fighting them!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT: case CHARACTER_CREATION:
							boolean inventoryFull = inventoryNPC.isInventoryFull() && !inventoryNPC.hasClothing(clothing);
							
							if(index == 1) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Give (1)", "You cannot give away the " + clothing.getName() + "!", null);
									
								} else if(inventoryFull) {
									return new Response("Give (1)", UtilText.parse(inventoryNPC, "[npc.NamePos] inventory is already full!"), null);
								}
								return new Response("Give (1)", UtilText.parse(inventoryNPC, "Give [npc.name] the " + clothing.getName() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(Main.game.getPlayer(), inventoryNPC, clothing, 1);
									}
								};
								
							} else if(index == 2) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Give (5)", "You cannot give away the " + clothing.getName() + "!", null);
									
								} else if(inventoryFull) {
									return new Response("Give (5)", UtilText.parse(inventoryNPC, "[npc.NamePos] inventory is already full!"), null);
								}
								if(Main.game.getPlayer().getClothingCount(clothing) >= 5) {
									return new Response("Give (5)", UtilText.parse(inventoryNPC, "Give [npc.name] five of your " + clothing.getNamePlural() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											transferClothing(Main.game.getPlayer(), inventoryNPC, clothing, 5);
										}
									};
								} else {
									return new Response("Give (5)", "You don't have five " + clothing.getNamePlural() + " to give!", null);
								}
								
							} else if(index == 3) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Give (All)", "You cannot give away the " + clothing.getName() + "!", null);
									
								} else if(inventoryFull) {
									return new Response("Give (All)", UtilText.parse(inventoryNPC, "[npc.NamePos] inventory is already full!"), null);
								}
								return new Response("Give (All)", UtilText.parse(inventoryNPC, "Give [npc.name] all of your " + clothing.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(Main.game.getPlayer(), inventoryNPC, clothing, Main.game.getPlayer().getClothingCount(clothing));
									}
								};
								
							} else if (index==4) {
								if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull();
									boolean isDyeingStackItem = Main.game.getPlayer().getAllClothingInInventory().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Dye",
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Use your proficiency with [style.colourEarth(Earth spells)] to dye this item."
													:"Use a dye-brush to dye this item of clothing.",
												DYE_CLOTHING) {
											@Override
											public void effects() {
												resetClothingDyeColours();
											}
										};
									} else {
										return new Response("Dye", "Your inventory is full, so you can't dye this item of clothing.", null);
									}
								} else {
									return new Response("Dye", "You'll need to find a dye-brush if you want to dye your clothes.", null);
								}
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									return getCondomSabotageResponse(clothing);
								}
								if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									if(clothing.getEnchantmentItemType(null)==null) {
										return new Response("Enchant", "This clothing cannot be enchanted!", null);
										
									} else if(!clothing.isEnchantmentKnown()) {
										if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE) >= IDENTIFICATION_ESSENCE_PRICE) {
											return new Response("Identify ([style.italicsArcane("+IDENTIFICATION_ESSENCE_PRICE+" Essences)])",
													"To identify the "+clothing.getName()+", you can either spend "+IDENTIFICATION_ESSENCE_PRICE+" arcane essences to do it yourself,"
															+ " or go to a vendor and pay "+IDENTIFICATION_PRICE+" flames to have them do it for you.",
													CLOTHING_INVENTORY) {
												@Override
												public void effects() {
													Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -IDENTIFICATION_ESSENCE_PRICE, false);

													String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
													
													clothing = AbstractClothing.enchantmentRemovedClothing;
													
													Main.game.getTextEndStringBuilder().append(
															"<p>"
																+ "You channel the power of "+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+" of your arcane essences into the "+clothing.getName()
																	+", and as it emits a faint purple glow, you find yourself able to detect what sort of enchantment it has!"
															+ "</p>"
															+ enchantmentRemovedString
															+ "<p style='text-align:center;'>"
																+ "Identifying the "+clothing.getName()+" has cost you [style.boldBad("+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+")] [style.boldArcane(Arcane Essences)]!"
															+ "</p>");
													RenderingEngine.setPage(Main.game.getPlayer(), clothing);
												}
											};
										} else {
											return new Response("Identify (<i>"+IDENTIFICATION_ESSENCE_PRICE+" Essences</i>)",
													"To identify the "+clothing.getName()+", you can either spend "+IDENTIFICATION_ESSENCE_PRICE+" arcane essences to do it yourself ([style.italicsBad(which you don't have)]),"
															+ " or go to a vendor and pay "+IDENTIFICATION_PRICE+" flames to have them do it for you.", null);
										}
									}
									return new Response("Enchant", "Enchant this clothing.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(clothing);
										}
									};
								}
								
								return null;
								
							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "Equip the " + clothing.getName() + ".", INVENTORY_MENU) {
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								} else {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
									
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response(
											UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
											"You cannot give away the " + clothing.getName() + "!",
											null);
								}
								if(!inventoryNPC.isOverrideInventoryEquip() && !clothing.getClothingType().isCondom(slot) && inventoryNPC.isUnique() && (!inventoryNPC.isSlave() || !inventoryNPC.getOwner().isPlayer())) {
									return new Response(
											UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
											UtilText.parse(inventoryNPC, "As [npc.name] is a unique character, who is not your slave, you cannot force [npc.herHim] to wear the "+clothing.getName()+"."),
											null);
								}
								if(clothing.isCanBeEquipped(inventoryNPC, slot)) {
									if(inventoryNPC.isAbleToEquip(clothing, true, Main.game.getPlayer()) && clothing.isEnslavementClothing() && (!inventoryNPC.isSlave() || !inventoryNPC.getOwner().isPlayer())) {
										return new Response(
												UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
												UtilText.parse(inventoryNPC, "Make [npc.name] equip the "+clothing.getName()+"!"),
												INVENTORY_MENU){
											@Override
											public DialogueNode getNextDialogue() {
												if(inventoryNPC.getEnslavementDialogue(clothing)!=null) {
													return inventoryNPC.getEnslavementDialogue(clothing);
													
												} else {
													return INVENTORY_MENU;
												}
											}
											@Override
											public void effects(){
												SlaveDialogue.setFollowupEnslavementDialogue(Main.game.getSavedDialogueNode());
												if(inventoryNPC.getEnslavementDialogue(clothing)==null) {
													Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing) + "</p>");
												} else {
													equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing);
												}
											}
										};
										
									} else {
										return new Response(
												UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
												UtilText.parse(inventoryNPC, "Make [npc.name] equip the "+clothing.getName()+"!"),
												INVENTORY_MENU){
											@Override
											public void effects(){
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing) + "</p>");
											}
										};
									}
									
								} else {
									return new Response(
											UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
											clothing.getCannotBeEquippedText(inventoryNPC, slot),
											null);
								}
							
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("Give (1)", "You can't give someone clothing while having sex with them!", null);
								
							} else if(index == 2) {
								return new Response("Give (5)", "You can't give someone clothing while having sex with them!", null);
								
							} else if(index == 3) {
								return new Response("Give (All)", "You can't give someone clothing while having sex with them!", null);
								
							} else if(index == 4) {
								return new Response("Dye", "You can't dye your clothing while having sex with someone!", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair the condom while having sex with someone!", null);
									}
									return new Response("Sabotage", "You can't sabotage the condom while having sex with someone!", null);
								}
								return new Response("Enchant", "You can't enchant clothing while having sex with someone!", null);

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) { //TODO
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									if(clothing.getClothingType().isAbleToBeEquippedDuringSex(slot)) {
										if(!Sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer())) {
											return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "As this is a special sex scene, you cannot equip clothing during it!", null);
										}
										if (Main.game.getPlayer().isAbleToEquip(clothing, false, Main.game.getPlayer())) {
											return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "Equip the " + clothing.getName() + ".", Sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
													Sex.setEquipClothingText(c, Main.game.getPlayer().getUnequipDescription());
													Main.mainController.openInventory();
													Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Sex.setSexStarted(true);
												}
											};
										} else {
											return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "You can't equip the " + clothing.getName() + ", as other clothing is blocking you from doing so!", null);
										}
									} else {
										return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "You can't equip clothing while having sex with someone!", null);
									}
								} else {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response(
											UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
											"You cannot give away the " + clothing.getName() + "!",
											null);
								}
								if(!inventoryNPC.isOverrideInventoryEquip() && !clothing.getClothingType().isCondom(slot) && inventoryNPC.isUnique() && (!inventoryNPC.isSlave() || !inventoryNPC.getOwner().isPlayer())) {
									return new Response(
											UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
											UtilText.parse(inventoryNPC, "As [npc.name] is a unique character, who is not your slave, you cannot force [npc.herHim] to wear the "+clothing.getName()+"."),
											null);
								}
								if(clothing.isCanBeEquipped(inventoryNPC, slot)) {
									if(clothing.getClothingType().isAbleToBeEquippedDuringSex(slot)) {
										if(!Sex.getInitialSexManager().isAbleToEquipSexClothing(inventoryNPC)) {
											return new Response(
													UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
													"As this is a special sex scene, you cannot equip clothing during it!",
													null);
										}
										if (inventoryNPC.isAbleToEquip(clothing, false, Main.game.getPlayer())) {
											return new Response(
													UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
													UtilText.parse(inventoryNPC, "Get [npc.Name] to equip the " + clothing.getName() + "."),
													Sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing);
													Sex.setEquipClothingText(c, inventoryNPC.getUnequipDescription());
													Main.mainController.openInventory();
													Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Sex.setSexStarted(true);
												}
											};
										} else {
											return new Response(UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
													UtilText.parse(inventoryNPC, "[npc.Name] can't equip the " + clothing.getName() + ", as other clothing is blocking [npc.herHim] from doing so!"), null);
										}
									} else {
										return new Response(UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"), "You can't equip clothing while having sex with someone!", null);
									}
								} else {
									return new Response(UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"), clothing.getCannotBeEquippedText(inventoryNPC, slot), null);
								}
								
							} else {
								return null;
							}
							
						case TRADING:
							if(index == 1) {
								if(!clothing.getClothingType().isAbleToBeSold()) {
									return new Response("Sell (1)", "You cannot sell the " + clothing.getName() + "!", null);
									
								} else if (inventoryNPC.willBuy(clothing)) {
									int sellPrice = clothing.getPrice(inventoryNPC.getBuyModifier());
									return new Response("Sell (1) (" + UtilText.formatAsMoney(sellPrice, "span") + ")", "Sell the " + clothing.getName() + " for " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											sellClothing(Main.game.getPlayer(), inventoryNPC, clothing, 1, sellPrice);
										}
									};
								} else {
									return new Response("Sell (1)", inventoryNPC.getName("The") + " doesn't want to buy this.", null);
								}
								
							} else if(index == 2) {
								if(!clothing.getClothingType().isAbleToBeSold()) {
									return new Response("Sell (5)", "You cannot sell the " + clothing.getName() + "!", null);
									
								} else if(Main.game.getPlayer().getClothingCount(clothing) >= 5) {
									if (inventoryNPC.willBuy(clothing)) {
										int sellPrice = clothing.getPrice(inventoryNPC.getBuyModifier());
										return new Response("Sell (5) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "Sell five of your " + clothing.getNamePlural() + " for " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												sellClothing(Main.game.getPlayer(), inventoryNPC, clothing, 5, sellPrice);
											}
										};
									} else {
										return new Response("Sell (5)", inventoryNPC.getName("The") + " doesn't want to buy these.", null);
									}
									
								} else {
									return new Response("Sell (5)", "You don't have five " + clothing.getNamePlural() + " to sell!", null);
								}
								
							} else if(index == 3) {
								if(!clothing.getClothingType().isAbleToBeSold()) {
									return new Response("Sell (All)", "You cannot sell the " + clothing.getName() + "!", null);
									
								} else if (inventoryNPC.willBuy(clothing)) {
									int sellPrice = clothing.getPrice(inventoryNPC.getBuyModifier());
									return new Response("Sell (All) (" + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getClothingCount(clothing), "span") + ")",
											"Sell the " + clothing.getName() + " for " + UtilText.formatAsMoney(sellPrice*Main.game.getPlayer().getClothingCount(clothing)) + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											sellClothing(Main.game.getPlayer(), inventoryNPC, clothing, Main.game.getPlayer().getClothingCount(clothing), sellPrice);
										}
									};
								} else {
									return new Response("Sell (All)", inventoryNPC.getName("The") + " doesn't want to buy these.", null);
								}
								
							} else if (index==4) {
								if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull() && clothing.getRarity()!=Rarity.QUEST;
									boolean isDyeingStackItem = Main.game.getPlayer().getAllClothingInInventory().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Dye", 
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Use your proficiency with [style.colourEarth(Earth spells)] to dye this item."
													:"Use a dye-brush to dye this item of clothing.",
												DYE_CLOTHING) {
											@Override
											public void effects() {
												resetClothingDyeColours();
											}
										};
									} else {
										return new Response("Dye", "Your inventory is full, so you can't dye this item of clothing.", null);
									}
								} else {
									return new Response("Dye", "You'll need to find a dye-brush if you want to dye your clothes.", null);
								}
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									return getCondomSabotageResponse(clothing);
								}
								if(Main.game.isDebugMode()
										|| (Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY) && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY))) {
									if(clothing.getEnchantmentItemType(null)==null) {
										return new Response("Enchant", "This clothing cannot be enchanted!", null);
										
									} else if(!clothing.isEnchantmentKnown()) {
										if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE) >= IDENTIFICATION_ESSENCE_PRICE) {
											return new Response("Identify ([style.italicsArcane("+IDENTIFICATION_ESSENCE_PRICE+" Essences)])",
													"To identify the "+clothing.getName()+", you can either spend "+IDENTIFICATION_ESSENCE_PRICE+" arcane essences to do it yourself,"
															+ " or go to a vendor and pay "+IDENTIFICATION_PRICE+" flames to have them do it for you.",
													CLOTHING_INVENTORY) {
												@Override
												public void effects() {
													Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -IDENTIFICATION_ESSENCE_PRICE, false);

													String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
													
													clothing = AbstractClothing.enchantmentRemovedClothing;
													
													Main.game.getTextEndStringBuilder().append(
															"<p>"
																+ "You channel the power of "+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+" of your arcane essences into the "+clothing.getName()
																	+", and as it emits a faint purple glow, you find yourself able to detect what sort of enchantment it has!"
															+ "</p>"
															+ enchantmentRemovedString
															+ "<p style='text-align:center;'>"
																+ "Identifying the "+clothing.getName()+" has cost you [style.boldBad("+Util.intToString(IDENTIFICATION_ESSENCE_PRICE)+")] [style.boldArcane(Arcane Essences)]!"
															+ "</p>");
													RenderingEngine.setPage(Main.game.getPlayer(), clothing);
												}
											};
										} else {
											return new Response("Identify (<i>"+IDENTIFICATION_ESSENCE_PRICE+" Essences</i>)",
													"To identify the "+clothing.getName()+", you can either spend "+IDENTIFICATION_ESSENCE_PRICE+" arcane essences to do it yourself ([style.italicsBad(which you don't have)]),"
															+ " or go to a vendor and pay "+IDENTIFICATION_PRICE+" flames to have them do it for you.", null);
										}
									}
									return new Response("Enchant", "Enchant this clothing.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public DialogueNode getNextDialogue() {
											return EnchantmentDialogue.getEnchantmentMenu(clothing);
										}
									};
								}
								
								return null;

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "Equip the " + clothing.getName() + ".", INVENTORY_MENU){
											@Override
											public void effects(){
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
											}
										};
								} else {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							}
//							else if (index == 10) {
//								return getQuickTradeResponse();
//
//							}
							else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								return new Response(UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"), UtilText.parse(inventoryNPC, "[npc.Name] doesn't want to wear your clothing."), null);
								
							} else if (index == 10 && !clothing.isEnchantmentKnown()) {
								if(!inventoryNPC.willBuy(clothing)) {
									return new Response("Identify", inventoryNPC.getName("The") + " can't identify clothing!", null);
									
								} else if(Main.game.getPlayer().getMoney() < IDENTIFICATION_PRICE){
									// don't format as money because we don't want to highlight non-selectable choices
									return new Response("Identify (" + UtilText.formatAsMoneyUncoloured(IDENTIFICATION_PRICE, "span") + ")", "You don't have enough money!", null);
									
								}else {
									return new Response("Identify (" + UtilText.formatAsMoney(IDENTIFICATION_PRICE, "span") + ")",
												"Have the " + clothing.getName() + " identified for " + UtilText.formatAsMoney(IDENTIFICATION_PRICE, "span") + ".", CLOTHING_INVENTORY){
										@Override
										public void effects(){
											Main.game.getPlayer().incrementMoney(-IDENTIFICATION_PRICE);

											String enchantmentRemovedString = clothing.setEnchantmentKnown(owner, true);
											
											clothing = AbstractClothing.enchantmentRemovedClothing;
											
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ UtilText.parse(inventoryNPC,
																"You hand over " + UtilText.formatAsMoney(IDENTIFICATION_PRICE) + " to [npc.name],"
																		+ " who promptly feeds several bottles of arcane essence into a specialist identification device, before using it to reveal the enchantment on your "+clothing.getName()+".")
													+ "</p>"
													+enchantmentRemovedString);
											
											RenderingEngine.setPage(Main.game.getPlayer(), clothing);
										}
									};
								}
							} else {
								return null;
							}
					}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {

					boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing) && clothing.getRarity()!=Rarity.QUEST;
					switch(interactionType) {
						case CHARACTER_CREATION:
							if (index == 1) {
								if(Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.NIPPLES)
										|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.ANUS)
										|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.PENIS)
										|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.VAGINA)
										|| (Main.game.getPlayer().getClothingInSlot(InventorySlot.FOOT)==null && Main.game.getPlayer().getLegType().equals(LegType.HUMAN))) {
									return new Response("To the stage", "You need to be wearing clothing that covers your body, as well as a pair of shoes.", null);
									
								} else {
									return new Response("To the stage", "You're ready to approach the stage now.", CharacterCreation.CHOOSE_BACKGROUND) {
										@Override
										public void effects() {
											CharacterCreation.moveNPCIntoPlayerTile();
										}
									};
								}
								
							} else if(index == 4) {
								if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()){
									return new Response("Unequip all", "You're currently naked, there's nothing to be unequipped.", null);
								}
								else{
									return new Response("Unequip all", "Remove as much of your clothing as possible.", INVENTORY_MENU){
										@Override
										public void effects(){
											List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped());
											zlayerClothing.sort(new ClothingZLayerComparator());
	
											for(AbstractClothing c : zlayerClothing){
												Main.game.getPlayer().unequipClothingOntoFloor(c, true, Main.game.getPlayer());
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getUnequipDescription()+"</p>");
											}
										}
									};
								}
								
							} else if(index == 5) {
								return new Response("Change colour", "Change the colour of this item of clothing.", DYE_CLOTHING_CHARACTER_CREATION) {
									@Override
									public void effects() {
										resetClothingDyeColours();
									}
								};
							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "Equip the " + clothing.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											equipClothingFromGround(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
										}
									};
									
								} else {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
							
							} else {
								return null;
							}
					
					case SEX:
						if(index == 1) {
							return new Response("Take (1)", "You can't pick up clothing while masturbating.", null);
							
						} else if(index == 2) {
							return new Response("Take (5)", "You can't pick up clothing while masturbating.", null);
							
						} else if(index == 3) {
							return new Response("Take (All)", "You can't pick up clothing while masturbating.", null);
							
						} else if(index == 4) {
							return new Response("Dye", "You can't dye your clothing while masturbating.", null);
							
						} else if(index == 5) {
							if(clothing.isCondom()) {
								if(clothing.getCondomEffect().getPotency().isNegative()) {
									return new Response("Repair (<i>1 Essence</i>)", "You can't repair condoms on the ground!", null);
								}
								return new Response("Sabotage", "You can't sabotage condoms on the ground!", null);
							}
							return new Response("Enchant", "You can't enchant clothing on the ground!", null);

						} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
							InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
							if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
								if(clothing.getClothingType().isAbleToBeEquippedDuringSex(slot)) {
									if(!Sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer())) {
										return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "As this is a special sex scene, you cannot equip clothing during it!", null);
									}
									if (Main.game.getPlayer().isAbleToEquip(clothing, false, Main.game.getPlayer())) {
										return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "Equip the " + clothing.getName() + ".", Sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												AbstractClothing c = clothing;
												equipClothingFromGround(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
												Sex.setEquipClothingText(c, Main.game.getPlayer().getUnequipDescription());
												Main.mainController.openInventory();
												Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "You can't equip the " + clothing.getName() + ", as other clothing is blocking you from doing so!", null);
									}
								} else {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "You can't equip clothing while masturbating.", null);
								}
							} else {
								return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();

						} else {
							return null;
						}
					
					default:
						if(index == 1) {
							if(inventoryFull) {
								return new Response("Take (1)", "Your inventory is already full!", null);
							}
							return new Response("Take (1)", "Take one " + clothing.getName() + " from the ground.", INVENTORY_MENU){
								@Override
								public void effects(){
									pickUpClothing(Main.game.getPlayer(), clothing, 1);
								}
							};
							
						} else if(index == 2) {
							if(inventoryFull) {
								return new Response("Take (5)", "Your inventory is already full!", null);
							}
							if(Main.game.getPlayerCell().getInventory().getClothingCount(clothing) >= 5) {
								return new Response("Take (5)", "Take five of the " + clothing.getNamePlural() + " from the ground.", INVENTORY_MENU){
									@Override
									public void effects(){
										pickUpClothing(Main.game.getPlayer(), clothing, 5);
									}
								};
							} else {
								return new Response("Take (5)", "There aren't five " + clothing.getNamePlural() + " on the ground!", null);
							}
							
						} else if(index == 3) {
							if(inventoryFull) {
								return new Response("Take (All)", "Your inventory is already full!", null);
							}
							return new Response("Take (All)", "Take all of the " + clothing.getNamePlural() + " from the ground.", INVENTORY_MENU){
								@Override
								public void effects(){
									pickUpClothing(Main.game.getPlayer(), clothing, Main.game.getPlayerCell().getInventory().getClothingCount(clothing));
								}
							};
							
						} else if (index==4) {
							if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
								boolean hasFullInventory = Main.game.getPlayerCell().getInventory().isInventoryFull();
								boolean isDyeingStackItem = Main.game.getPlayerCell().getInventory().getAllClothingInInventory().get(clothing) > 1;
								boolean canDye = !(isDyeingStackItem && hasFullInventory);
								if (canDye) {
									return new Response("Dye", 
											Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"Use your proficiency with [style.colourEarth(Earth spells)] to dye this item."
												:"Use a dye-brush to dye this item of clothing.",
											DYE_CLOTHING) {
										@Override
										public void effects() {
											resetClothingDyeColours();
										}
									};
								} else {
									return new Response("Dye", "Your inventory is full, so you can't dye this item of clothing.", null);
								}
							} else {
								return new Response("Dye", "You'll need to find a dye-brush if you want to dye your clothes.", null);
							}
							
						} else if(index == 5) {
							if(clothing.isCondom()) {
								if(clothing.getCondomEffect().getPotency().isNegative()) {
									return new Response("Repair (<i>1 Essence</i>)", "You can't repair condoms on the ground!", null);
								}
								return new Response("Sabotage", "You can't sabotage condoms on the ground!", null);
							}
							return new Response("Enchant", "You can't enchant clothing on the ground!", null);
	
						} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
							InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
							if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
								return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "Equip the " + clothing.getName() + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromGround(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
									}
								};
							} else {
								return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
					}
					
				// ****************************** Interacting with an NPC ******************************
				} else {
					boolean inventoryFull = false;
					switch(interactionType) {
						case COMBAT:
							if(index == 1) {
								return new Response("Take (1)", "You can't take someone clothing while fighting them!", null);
								
							} else if(index == 2) {
								return new Response("Take (5)", "You can't take someone clothing while fighting them!", null);
								
							} else if(index == 3) {
								return new Response("Take (All)", "You can't take someone clothing while fighting them!", null);
								
							} else if(index == 4) {
								return new Response("Dye", "You can't dye someone's clothing while fighting them!", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair someone else's condom, especially not when fighting them!", null);
									}
									return new Response("Sabotage", "You can't sabotage someone else's condom, especially not while fighting them!", null);
								}
								return new Response("Enchant", "You can't enchant someone else's clothing, especially not while fighting them!", null);
								
							} else if(index == 6) {
								return new Response("Equip (self)", "You can't use someone else's clothing while fighting them!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("Equip (opponent)", "You can't make someone wear clothing while fighting them!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT: case CHARACTER_CREATION:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing) && clothing.getRarity()!=Rarity.QUEST;
						
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Take (1)", "Your inventory is already full!", null);
								}
								return new Response("Take (1)", UtilText.parse(inventoryNPC, "Take the " + clothing.getName() + " from [npc.name]."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(inventoryNPC, Main.game.getPlayer(), clothing, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("Take (5)", "Your inventory is already full!", null);
								}
								if(inventoryNPC.getClothingCount(clothing) >= 5) {
									return new Response("Take (5)", UtilText.parse(inventoryNPC, "Take five of  [npc.namePos] " + clothing.getNamePlural() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											transferClothing(inventoryNPC, Main.game.getPlayer(), clothing, 5);
										}
									};
								} else {
									return new Response("Take (5)", UtilText.parse(inventoryNPC, "[npc.Name] doesn't have five " + clothing.getNamePlural() + "!"), null);
								}
								
							} else if(index == 3) {
								if(inventoryFull) {
									return new Response("Take (All)", "Your inventory is already full!", null);
								}
								return new Response("Take (All)", UtilText.parse(inventoryNPC, "Take all "+Util.intToString(inventoryNPC.getClothingCount(clothing))+" of  [npc.namePos] " + clothing.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(inventoryNPC, Main.game.getPlayer(), clothing, inventoryNPC.getClothingCount(clothing));
									}
								};
								
							} else if (index==4) {
								if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
									boolean hasFullInventory = inventoryNPC.isInventoryFull();
									boolean isDyeingStackItem = clothing!=null && inventoryNPC.getAllClothingInInventory().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Dye", 
												Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
													?"Use your proficiency with [style.colourEarth(Earth spells)] to dye this item."
													:"Use a dye-brush to dye this item of clothing.",
												DYE_CLOTHING) {
											@Override
											public void effects() {
												resetClothingDyeColours();
											}
										};
									} else {
										return new Response("Dye", UtilText.parse(inventoryNPC, "[npc.NamePos] inventory is full, so you can't dye this item of clothing."), null);
									}
								} else {
									return new Response("Dye", UtilText.parse(inventoryNPC, "You'll need to find another dye-brush if you want to dye [npc.namePos] clothes."), null);
								}
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair condoms owned by someone else!", null);
									}
									return new Response("Sabotage", "You can't sabotage condoms owned by someone else!", null);
								}
								return new Response("Enchant", "You can't enchant clothing owned by someone else!", null);

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "Equip the " + clothing.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								} else {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								if(!inventoryNPC.isOverrideInventoryEquip() && !clothing.getClothingType().isCondom(slot) && inventoryNPC.isUnique() && (!inventoryNPC.isSlave() || !inventoryNPC.getOwner().isPlayer())) {
									return new Response(
											UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
											UtilText.parse(inventoryNPC, "As [npc.name] is a unique character, who is not your slave, you cannot force [npc.herHim] to wear the "+clothing.getName()+"."),
											null);
								}
								if(clothing.isCanBeEquipped(inventoryNPC, slot)) {
									if(inventoryNPC.isAbleToEquip(clothing, true, Main.game.getPlayer()) && clothing.isEnslavementClothing() && (!inventoryNPC.isSlave() || !inventoryNPC.getOwner().isPlayer())) {
										return new Response(
												UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
												UtilText.parse(inventoryNPC, "Make [npc.name] equip the "+clothing.getName()+"!"),
												INVENTORY_MENU){
											@Override
											public DialogueNode getNextDialogue() {
												if(inventoryNPC.getEnslavementDialogue(clothing)!=null) {//inventoryNPC.isAbleToBeEnslaved() && !inventoryNPC.isSlave()) {
													return inventoryNPC.getEnslavementDialogue(clothing);
													
												} else {
													return INVENTORY_MENU;
												}
											}
											@Override
											public void effects(){
												SlaveDialogue.setFollowupEnslavementDialogue(Main.game.getSavedDialogueNode());
												if(inventoryNPC.getEnslavementDialogue(clothing)==null) {
													Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing) + "</p>");
												} else {
//													if(inventoryNPC.isSlave()) {
//														Main.game.getTextEndStringBuilder().append(inventoryNPC.getEnslavementDialogue(clothing).getContent());
//													}
													equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing);
												}
											}
										};
										
									} else {
										return new Response(
												UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
												UtilText.parse(inventoryNPC, "Get [npc.name] to equip the " + clothing.getName() + "."),
												INVENTORY_MENU){
											@Override
											public void effects(){
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing) + "</p>");
											}
										};
									}
								} else {
									return new Response(
											UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
											clothing.getCannotBeEquippedText(inventoryNPC, slot),
											null);
								}
								
							} else {
								return null;
							}
							
						case SEX:
							if(index == 1) {
								return new Response("Take (1)", "You can't take someone's clothing while having sex with them!", null);
								
							} else if(index == 2) {
								return new Response("Take (5)", "You can't take someone's clothing while having sex with them!", null);
								
							} else if(index == 3) {
								return new Response("Take (All)", "You can't take someone's clothing while having sex with them!", null);
								
							} else if(index == 4) {
								return new Response("Dye", "You can't dye someone's clothing while having sex with them!", null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair someone else's condom, especially not while having sex with them!", null);
									}
									return new Response("Sabotage", "You can't sabotage someone else's condom, especially not while having sex with them!", null);
								}
								return new Response("Enchant", "You can't enchant someone else's clothing, especially not while having sex with them!", null);

							} else if(index >= 6 && index <= 9 && index-6<clothing.getClothingType().getEquipSlots().size()) { //TODO
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-6);
								if(clothing.isCanBeEquipped(Main.game.getPlayer(), slot)) {
									if(clothing.getClothingType().isAbleToBeEquippedDuringSex(slot) && !inventoryNPC.isTrader()) {
										if(!Sex.getInitialSexManager().isAbleToEquipSexClothing(Main.game.getPlayer())) {
											return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "As this is a special sex scene, you cannot equip clothing during it!", null);
										}
										if (Main.game.getPlayer().isAbleToEquip(clothing, false, Main.game.getPlayer())) {
											return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "Equip the " + clothing.getName() + ".", Sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(Main.game.getPlayer(), slot, Main.game.getPlayer(), clothing);
													Sex.setEquipClothingText(c, Main.game.getPlayer().getUnequipDescription());
													Main.mainController.openInventory();
													Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Sex.setSexStarted(true);
												}
											};
										} else {
											return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "You can't equip the " + clothing.getName() + ", as other clothing is blocking you from doing so!", null);
										}
									} else {
										return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), "You can't equip this clothing while having sex with someone!", null);
									}
								} else {
									return new Response("Equip: "+Util.capitaliseSentence(slot.getName()), clothing.getCannotBeEquippedText(Main.game.getPlayer(), slot), null);
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();

							} else if(index >= 11 && index <= 14 && index-11<clothing.getClothingType().getEquipSlots().size()) {
								InventorySlot slot = clothing.getClothingType().getEquipSlots().get(index-11);
								if(!inventoryNPC.isOverrideInventoryEquip() && !clothing.getClothingType().isCondom(slot) && inventoryNPC.isUnique() && (!inventoryNPC.isSlave() || !inventoryNPC.getOwner().isPlayer())) {
									return new Response(
											UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
											UtilText.parse(inventoryNPC, "As [npc.name] is a unique character, who is not your slave, you cannot force [npc.herHim] to wear the "+clothing.getName()+"."),
											null);
								}
								if(clothing.isCanBeEquipped(inventoryNPC, slot)) {
									if(clothing.getClothingType().isAbleToBeEquippedDuringSex(slot) && !inventoryNPC.isTrader()) {
										if(!Sex.getInitialSexManager().isAbleToEquipSexClothing(inventoryNPC)) {
											return new Response(
													UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
													"As this is a special sex scene, you cannot equip clothing during it!",
													null);
										}
										if (inventoryNPC.isAbleToEquip(clothing, false, Main.game.getPlayer())) {
											return new Response(
													UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
													UtilText.parse(inventoryNPC, "Get [npc.Name] to equip the " + clothing.getName() + "."),
													Sex.SEX_DIALOGUE){
												@Override
												public void effects(){
													AbstractClothing c = clothing;
													equipClothingFromInventory(inventoryNPC, slot, Main.game.getPlayer(), clothing);
													Sex.setEquipClothingText(c, inventoryNPC.getUnequipDescription());
													Main.mainController.openInventory();
													Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
													Sex.setSexStarted(true);
												}
											};
										} else {
											return new Response(
													UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
													UtilText.parse(inventoryNPC, "[npc.Name] can't equip the " + clothing.getName() + ", as other clothing is blocking [npc.herHim] from doing so!"),
													null);
										}
									} else {
										return new Response(
												UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
												"You can't equip this clothing while having sex with someone!",
												null);
									}
								} else {
									return new Response(
											UtilText.parse(inventoryNPC, "Equip: "+Util.capitaliseSentence(slot.getName())+" ([npc.Name])"),
											clothing.getCannotBeEquippedText(inventoryNPC, slot),
											null);
								}
								
							} else {
								return null;
							}
							
						case TRADING:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing) && clothing.getRarity()!=Rarity.QUEST;
							
							if(index == 1) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():clothing.getPrice(inventoryNPC.getSellModifier());
								if(inventoryFull) {
									return new Response("Buy (1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
									return new Response("Buy (1) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "You can't afford to buy this!", null);
								}
								return new Response("Buy (1) (" + UtilText.formatAsMoney(sellPrice, "span") + ")", "Buy the " + clothing.getName() + " for " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellClothing(inventoryNPC, Main.game.getPlayer(), clothing, 1, sellPrice);
									}
								};
								
							} else if(index == 2) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():clothing.getPrice(inventoryNPC.getSellModifier());
								if((buyback && Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<5)
										|| (!buyback && inventoryNPC.getClothingCount(clothing) < 5)) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", UtilText.parse(inventoryNPC, "[npc.Name] doesn't have five "+clothing.getNamePlural()+"."), null);
								}
								if(inventoryFull) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*5) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "You can't afford to buy this!", null);
								}
								return new Response("Buy (5) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "Buy the " + clothing.getName() + " for " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellClothing(inventoryNPC, Main.game.getPlayer(), clothing, 5, sellPrice);
									}
								};
								
							} else if(index == 3) {
								int sellPrice = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getPrice():clothing.getPrice(inventoryNPC.getSellModifier());
								int count = buyback?Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount():inventoryNPC.getClothingCount(clothing);
								if(inventoryFull) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice*count) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice*count, "span")+")", "You can't afford to buy this!", null);
								}
								return new Response("Buy (All) (" + UtilText.formatAsMoney(sellPrice*count, "span") + ")",
										"Buy the " + clothing.getName() + " for " + UtilText.formatAsMoney(sellPrice*count) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellClothing(inventoryNPC, Main.game.getPlayer(), clothing, count, sellPrice);
									}
								};
								
							} else if(index == 4) {
								return new Response("Dye", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to let you dye the clothing that [npc.sheIs] trying to sell!"), null);
								
							} else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair someone else's condom!", null);
									}
									return new Response("Sabotage", "You can't sabotage someone else's condom!", null);
								}
								return new Response("Enchant", "You can't enchant someone else's clothing!", null);
								
							} else if(index == 6) {
								return new Response("Equip (self)", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to let you use [npc.her] clothing without buying them first."), null);
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip ([npc.Name])"), UtilText.parse(inventoryNPC, "[npc.Name] isn't going to use the clothing that [npc.sheIs] trying to sell!"), null);
								
							} else {
								return null;
							}
					}
				}
			}
			return null;
			
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	public static final DialogueNode WEAPON_EQUIPPED = new DialogueNode("Weapon equipped", "", true) {
		

		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "Inventory (Quick-Manage is <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>)";
			} else {
				return "Inventory";
			}
		}
		
		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			return getItemDisplayPanel(weapon.getSVGEquippedString(owner),
					weapon.getDisplayName(true),
					 weapon.getDescription());
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				switch(interactionType) {
					case COMBAT:
						if(index == 1) {
							return new Response("Unequip", "You can't change weapons while fighting someone!", null);
							
						} else if (index == 2) {
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								return new Response("Drop", "You can't change weapons while fighting someone!", null);
								
							} else {
								return new Response("Store", "You can't change weapons while fighting someone!", null);
							}
							
						} else if (index==4) {
							return new Response("Dye/Reforge", "You can't alter the properties of "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" weapons in combat!", null);
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant equipped weapons!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case FULL_MANAGEMENT: case TRADING: case CHARACTER_CREATION:
						if(index == 1) {
							return new Response("Unequip", "Unequip the " + weapon.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
												+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, false, true))
											+ "</p>");
									resetPostAction();
								}
							};
							
						} else if (index == 2) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Drop", "You cannot drop the " + weapon.getName() + "!", null);
									
								} else if(areaFull) {
									return new Response("Drop", "This area is full, so you can't drop your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Drop", "Drop your " + weapon.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, true, true))
													+ "</p>");
											resetPostAction();
										}
									};
								}
								
							} else {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Store", "You cannot drop the " + weapon.getName() + "!", null);
									
								} else if(areaFull) {
									return new Response("Store", "This area is full, so you can't store your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Store", "Store your " + weapon.getName() + " in this area.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, true, true))
													+ "</p>");
											resetPostAction();
										}
									};
								}
							}
							
						} else if (index==4) {
							if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
									|| Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
									|| Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
								return new Response("Dye/Reforge", 
										Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
											?"Use your proficiency with [style.colourEarth(Earth spells)] to alter this weapon's properties."
											:"Use a dye-brush or reforge hammer to alter this weapon's properties.",
										DYE_EQUIPPED_WEAPON) {
									@Override
									public void effects() {
										resetWeaponDyeColours();
									}
								};
							} else {
								return new Response("Dye", "You need a dye-brush or reforge hammer in order to alter this weapon's properties.", null);
							}
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant equipped weapons!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case SEX:
						if(index == 1) {
							return new Response("Unequip", "Unequip the " + weapon.getName() + ".", Sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									Sex.setUnequipWeaponText(weapon,
											"<p style='text-align:center;'>"
												+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, false, true))
											+ "</p>");
									resetPostAction();
									Main.mainController.openInventory();
									Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Sex.setSexStarted(true);
								}
							};
							
						} else if (index == 2) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Drop", "You cannot drop the " + weapon.getName() + "!", null);
									
								} else if(areaFull) {
									return new Response("Drop", "This area is full, so you can't drop your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Drop", "Drop your " + weapon.getName() + ".", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUnequipWeaponText(weapon,
													"<p style='text-align:center;'>"
														+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, true, true))
													+ "</p>");
											resetPostAction();
											Main.mainController.openInventory();
											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Sex.setSexStarted(true);
										}
									};
								}
								
							} else {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Store", "You cannot drop the " + weapon.getName() + "!", null);
									
								} else if(areaFull) {
									return new Response("Store", "This area is full, so you can't store your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Store", "Store your " + weapon.getName() + " in this area.", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUnequipWeaponText(weapon,
													"<p style='text-align:center;'>"
														+ (Main.game.getPlayer().unequipWeapon(weaponSlot, weapon, true, true))
													+ "</p>");
											resetPostAction();
											Main.mainController.openInventory();
											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Sex.setSexStarted(true);
										}
									};
								}
							}
							
						} else if (index==4) {
							return new Response("Dye/Reforge", "You can't alter the proptries of "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" weapons in sex!", null);
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant equipped weapons!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				switch(interactionType) {
					case COMBAT:
						if(index == 1) {
							return new Response("Unequip", "You can't unequip someone's weapon while fighting them!", null);
							
						} else if (index == 2) {
							return new Response("Drop", "You can't make someone drop their weapon while fighting them!", null);
							
						} else if (index==4) {
							return new Response("Dye/Reforge", "You can't alter the properties of someone else's equipped weapons while you're fighting them!", null);
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant someone else's equipped weapon, especially not while fighting them!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case FULL_MANAGEMENT:  case CHARACTER_CREATION:
						if(index == 1) {
							return new Response("Unequip", UtilText.parse(inventoryNPC, "Get [npc.name] to unequip [npc.her] " + weapon.getName() + "."), INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append(
											"<p style='text-align:center;'>"
												+ (inventoryNPC.unequipWeapon(weaponSlot, weapon, false, false))
											+ "</p>");
									resetPostAction();
								}
							};
							
						} else if (index == 2) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Drop", UtilText.parse(inventoryNPC, "[npc.Name] cannot drop [npc.her] " + weapon.getName() + "!"), null);
									
								} else if(areaFull) {
									return new Response("Drop", UtilText.parse(inventoryNPC, "This area is full, so [npc.name] can't drop [npc.her] " + weapon.getName() + " here!"), null);
								} else {
									return new Response("Drop", UtilText.parse(inventoryNPC, "Get [npc.name] to drop [npc.her] " + weapon.getName() + "."), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (inventoryNPC.unequipWeapon(weaponSlot, weapon, true, false))
													+ "</p>");
											resetPostAction();
										}
									};
								}
								
							} else {
								if(!weapon.getWeaponType().isAbleToBeDropped()) {
									return new Response("Drop", UtilText.parse(inventoryNPC, "[npc.Name] cannot drop [npc.her] " + weapon.getName() + "!"), null);
									
								} else if(areaFull) {
									return new Response("Store", UtilText.parse(inventoryNPC, "This area is full, so [npc.name] can't store [npc.her] " + weapon.getName() + " here!"), null);
								} else {
									return new Response("Store", UtilText.parse(inventoryNPC, "Get [npc.name] to store [npc.her] " + weapon.getName() + " in this area."), INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (inventoryNPC.unequipWeapon(weaponSlot, weapon, true, false))
													+ "</p>");
											resetPostAction();
										}
									};
								}
							}
							
						} else if (index==4) {
							if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
									|| Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
									|| Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
								return new Response("Dye/Reforge", 
										Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
											?"Use your proficiency with [style.colourEarth(Earth spells)] to alter this weapon's properties."
											:"Use a dye-brush or reforge hammer to alter this weapon's properties.",
										DYE_EQUIPPED_WEAPON) {
									@Override
									public void effects() {
										resetWeaponDyeColours();
									}
								};
							} else {
								return new Response("Dye", UtilText.parse(inventoryNPC, "You'll need to find a dye-brush or reforge hammer if you want to alter the properties of [npc.namePos] weapons."), null);
							}
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant equipped weapons!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case SEX:
						if(index == 1) {
							return new Response("Unequip", "You can't unequip someone's weapon while having sex with them!", null);
							
						} else if (index == 2) {
							return new Response("Drop", "You can't unequip someone's weapon while having sex with them!", null);
							
						} else if (index==4) {
							return new Response("Dye/Reforge", UtilText.parse(inventoryNPC, "You can't alter the properties of [npc.namePos] weapons in sex!"), null);
						
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant equipped weapons!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case TRADING:
						if(index == 1) {
							return new Response("Unequip", "You can't unequip someone's weapon!", null);
							
						} else if (index == 2) {
							return new Response("Drop", "You can't make someone drop their weapon!", null);
							
						} else if (index==4) {
							return new Response("Dye/Reforge", UtilText.parse(inventoryNPC, "You can't alter the properties of [npc.namePos] weapons!"), null);
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant someone else's equipped weapon!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
					}
				
				}
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	public static final DialogueNode CLOTHING_EQUIPPED = new DialogueNode("Clothing equipped", "", true) {

		@Override
		public String getLabel() {
			if(!Main.game.isInNewWorld()) {
				return "Evening's Attire";
			}
			
			if (Main.game.getDialogueFlags().values.contains(DialogueFlagValue.quickTrade) && !Main.game.isInSex() && !Main.game.isInCombat()) {
				return "Inventory (Quick-Manage is <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>)";
			} else {
				return "Inventory";
			}
		}
		
		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			return getItemDisplayPanel(
					clothing.getSVGEquippedString(owner),
					clothing.getDisplayName(true),
					clothing.getDescription()
						+ clothing.clothingExtraInformation((Main.game.isInSex()?owner:Main.game.getPlayer()), clothing.getSlotEquippedTo())
						+ (Main.game.isInSex()||Main.game.isInCombat()?clothing.getDisplacementBlockingDescriptions(owner):""))
						+(interactionType==InventoryInteraction.CHARACTER_CREATION?CharacterCreation.getCheckingClothingDescription():"");
		}

		public String getResponseTabTitle(int index) {
			return getGeneralResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}
			if(responseTab==0) {
				return INVENTORY_MENU.getResponse(responseTab, index);
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				InventorySlot slotEquippedTo = clothing.getSlotEquippedTo();
				switch(interactionType) {
					case COMBAT:
						if (index == 1) {
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								return new Response("Drop", "You cannot drop the " + clothing.getName() + " while fighting someone!", null);
								
							} else {
								return new Response("Store", "You cannot drop the " + clothing.getName() + " while fighting someone!", null);
							}
							
						} else if (index==4) {
							return new Response("Dye", "You can't dye "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" clothes in combat!", null);
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								if(isAbleToRemoveJinxes()) {
									return new Response("Unjinx (<i>"+clothing.getJinxRemovalCost()+" Essences</i>)", "You can't unjinx clothing in combat!", null);
								} else {
									return new Response("Unjinx", "You don't know how to remove jinxes! Perhaps you should pay Lilaya a visit...", null);
								}
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair equipped condoms!", null);
									}
									return new Response("Sabotage", "You can't sabotage equipped condoms!", null);
								}
								return new Response("Enchant", "You can't enchant equipped clothing!", null);
							}
							
						} else if(index == 6 && !clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
							return new Response("Unequip", "You can't unequip the " + clothing.getName() + " during combat!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).size()){
								return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										"You can't "+clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()
										+ " the " + clothing.getName() + " during combat!", null);
								
							
						} else {
							
							return null;
						}
						
					case FULL_MANAGEMENT: case TRADING:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Drop", "You cannot drop the " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Drop", "This area is full, so you can't drop "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" " + clothing.getName() + " here!", null);
								} else {
									return new Response((clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)?"Discard":"Drop"),
											(clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)
													?"Take off "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" " + clothing.getName() + " and throw it away."
													:"Drop "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" " + clothing.getName() + "."),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								}
								
							} else {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Store", "You cannot drop the " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Store", "This area is full, so you can't store "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" " + clothing.getName() + " here!", null);
								} else {
									return new Response((clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)?"Discard":"Store"),
											(clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)
													?"Take off "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" " + clothing.getName() + " and throw it away."
													:"Store "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" " + clothing.getName() + " in this area."),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								}
							}
							
						} else if (index==4) {
							if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
								return new Response("Dye", 
										Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
											?"Use your proficiency with [style.colourEarth(Earth spells)] to dye this item."
											:"Use a dye-brush to dye this item of clothing.",
										DYE_EQUIPPED_CLOTHING) {
									@Override
									public void effects() {
										resetClothingDyeColours();
									}
								};
							} else {
								return new Response("Dye", "You need a dye-brush in order to dye this item of clothing.", null);
							}
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								if(isAbleToRemoveJinxes()) {
									if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE) >= clothing.getJinxRemovalCost()) {
										return new Response("Unjinx ([style.italicsArcane("+clothing.getJinxRemovalCost()+" Essences)])",
												"Spend "+clothing.getJinxRemovalCost()+" arcane essences on removing the jinx from this piece of clothing.", INVENTORY_MENU) {
											@Override
											public void effects() {
												Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -clothing.getJinxRemovalCost(), false);
												Main.game.getTextEndStringBuilder().append(
														"<p>"
															+ "You channel the power of your arcane essences into your "+clothing.getName()+", and with a bright purple flash, you manage to remove the jinx!"
														+ "</p>"
														+ "<p style='text-align:center;'>"
															+ "Removing the jinx has cost you [style.boldBad("+clothing.getJinxRemovalCost()+")] [style.boldArcane(Arcane Essences)]!"
														+ "</p>");
												clothing.setSealed(false);
											}
										};
									} else {
										return new Response("Unjinx (<i>"+clothing.getJinxRemovalCost()+" Essences</i>)", "You need at least "+clothing.getJinxRemovalCost()+" arcane essences in order to unjinx this piece of clothing!", null);
									}
								} else {
									return new Response("Unjinx", "You don't know how to remove jinxes! Perhaps you should pay Lilaya a visit...", null);
								}
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair equipped condoms!", null);
									}
									return new Response("Sabotage", "You can't sabotage equipped condoms!", null);
								}
								return new Response("Enchant", "You can't enchant equipped clothing!", null);
							}
							
						} else if(index == 6 && !clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
							return new Response("Unequip", "Unequip the " + clothing.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToInventory(Main.game.getPlayer(), clothing) + "</p>");
								}
							};
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).size()){
							
							if (clothing.getDisplacedList().contains(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getOppositeDescription()),
										Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getOppositeDescription()) + " the " + clothing.getName() + ". "
												+ clothing.getClothingBlockingDescription(
														clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11),
														Main.game.getPlayer(),
														clothing.getSlotEquippedTo(),
														" <span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>This will cover "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" ",
														".</span>"),
												CLOTHING_EQUIPPED){
									@Override
									public void effects(){
										Main.game.getPlayer().isAbleToBeReplaced(clothing, clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
									}
								};
							} else {
								return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
										Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()) + " the " + clothing.getName() + ". "
												+ clothing.getClothingBlockingDescription(
														clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11),
														Main.game.getPlayer(),
														clothing.getSlotEquippedTo(),
														" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>This will expose "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" ",
														".</span>"),
												CLOTHING_EQUIPPED){
									@Override
									public void effects(){
										Main.game.getPlayer().isAbleToBeDisplaced(clothing, clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
									}
								};
							}
							
						} else {
							return null;
						}
						
					case CHARACTER_CREATION:
						if (index == 1) {
							if(Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.NIPPLES)
									|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.ANUS)
									|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.PENIS)
									|| Main.game.getPlayer().isCoverableAreaVisible(CoverableArea.VAGINA)
									|| (Main.game.getPlayer().getClothingInSlot(InventorySlot.FOOT)==null && Main.game.getPlayer().getLegType().equals(LegType.HUMAN))) {
								return new Response("To the stage", "You need to be wearing clothing that covers your body, as well as a pair of shoes.", null);
								
							} else {
								return new Response("To the stage", "You're ready to approach the stage now.", CharacterCreation.CHOOSE_BACKGROUND) {
									@Override
									public void effects() {
										CharacterCreation.moveNPCIntoPlayerTile();
									}
								};
							}
							
						} else if(index == 4){
							if(Main.game.getPlayer().getClothingCurrentlyEquipped().isEmpty()){
								return new Response("Unequip all", "You're currently naked, there's nothing to be unequipped.", null);
							}
							else{
								return new Response("Unequip all", "Remove as much of your clothing as possible.", INVENTORY_MENU){
									@Override
									public void effects(){
										List<AbstractClothing> zlayerClothing = new ArrayList<>(Main.game.getPlayer().getClothingCurrentlyEquipped());
										zlayerClothing.sort(new ClothingZLayerComparator());

										for(AbstractClothing c : zlayerClothing){
											Main.game.getPlayer().unequipClothingOntoFloor(c, true, Main.game.getPlayer());
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"+Main.game.getPlayer().getUnequipDescription()+"</p>");
										}
									}
								};
							}
							
						} else if(index == 5) {
							return new Response("Change colour", "Change the colour of this item of clothing.", DYE_EQUIPPED_CLOTHING_CHARACTER_CREATION) {
								@Override
								public void effects() {
									resetClothingDyeColours();
								}
							};
							
						} else if(index == 6) {
							return new Response("Unequip", "Unequip the " + clothing.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									unequipClothingToFloor(Main.game.getPlayer(), clothing);
								}
							};
								
						} else {
							return null;
						}
						
					case SEX:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Drop", "You cannot drop the " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Drop", "This area is full, so you can't drop "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" " + clothing.getName() + " here!", null);
								} else {
									if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
										return new Response((clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)?"Discard":"Drop"),
												(clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)
														?"Take off "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" " + clothing.getName() + " and throw it away."
														:"Drop "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" " + clothing.getName() + "."),
												Sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												GameCharacter unequipOwner = owner;
												AbstractClothing c = clothing;
												unequipClothingToFloor(Main.game.getPlayer(), clothing);
												Sex.setUnequipClothingText(c, unequipOwner.getUnequipDescription());
												Main.mainController.openInventory();
												Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("Drop", "You can't unequip the " + clothing.getName() + ", as other clothing is blocking you from doing so!", null);
									}
								}
								
							} else {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Store", "You cannot drop the " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Store", "This area is full, so you can't store "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" " + clothing.getName() + " here!", null);
								} else {
									if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
										return new Response((clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)?"Discard":"Store"),
												(clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)
														?"Take off "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" " + clothing.getName() + " and throw it away."
														:"Drop "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" " + clothing.getName() + "."),
												Sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												GameCharacter unequipOwner = owner;
												AbstractClothing c = clothing;
												unequipClothingToFloor(Main.game.getPlayer(), clothing);
												Sex.setUnequipClothingText(c, unequipOwner.getUnequipDescription());
												Main.mainController.openInventory();
												Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("Store", "You can't unequip the " + clothing.getName() + ", as other clothing is blocking you from doing so!", null);
									}
								}
							}
							
						} else if (index==4) {
							return new Response("Dye", "You can't dye "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" clothes in sex!", null);
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								if(isAbleToRemoveJinxes()) {
									if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE) >= clothing.getJinxRemovalCost()) {
										return new Response("Unjinx ([style.italicsArcane("+clothing.getJinxRemovalCost()+" Essences)])",
												"Spend "+clothing.getJinxRemovalCost()+" arcane essences on removing the jinx from this piece of clothing.",
												Sex.SEX_DIALOGUE) {
											@Override
											public void effects() {
												Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -clothing.getJinxRemovalCost(), false);
												Sex.setJinxRemovalClothingText(clothing,
														"<p>"
															+ "You channel the power of your arcane essences into your "+clothing.getName()+", and with a bright purple flash, you manage to remove the jinx!"
														+ "</p>"
														+ "<p style='text-align:center;'>"
															+ "Removing the jinx has cost you [style.boldBad("+clothing.getJinxRemovalCost()+")] [style.boldArcane(Arcane Essences)]!"
														+ "</p>");
												clothing.setSealed(false);
												Main.mainController.openInventory();
												Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("Unjinx (<i>"+clothing.getJinxRemovalCost()+" Essences</i>)", "You need at least "+clothing.getJinxRemovalCost()+" arcane essences in order to unjinx a piece of clothing!", null);
									}
								} else {
									return new Response("Unjinx", "You don't know how to remove jinxes! Perhaps you should pay Lilaya a visit...", null);
								}
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair equipped condoms!", null);
									}
									return new Response("Sabotage", "You can't sabotage equipped condoms!", null);
								}
								return new Response("Enchant", "You can't enchant equipped clothing!", null);
							}
							
						} else if(index == 6 && !clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
							if(!Sex.getSexManager().isAbleToRemoveSelfClothing(Main.game.getPlayer())) {
								return new Response("Unequip", "You can't unequip the " + clothing.getName() + " in this sex scene!", null);
							}
							
							if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
								return new Response("Unequip", "Unequip the " + clothing.getName() + ".", Sex.SEX_DIALOGUE){
									@Override
									public void effects(){
										AbstractClothing c = clothing;
										unequipClothingToInventory(Main.game.getPlayer(), clothing);
										Sex.setUnequipClothingText(c, owner.getUnequipDescription());
										Main.mainController.openInventory();
										Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
										Sex.setSexStarted(true);
									}
								};
							} else {
								return new Response("Unequip", "You can't unequip the " + clothing.getName() + ", as other clothing is blocking you from doing so!", null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).size()) {
							if (clothing.getDisplacedList().contains(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										"The "+ clothing.getName()+ " "
										+(clothing.getClothingType().isPlural()?"have":"has")+" already been "
												+ clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription() + "!", null);
								
							} else {
								if(!Sex.getSexManager().isAbleToRemoveSelfClothing(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
											"You can't can't "+clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()
											+ " "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" " + clothing.getName() + " in this sex scene!", null);
								}
								
								if(owner.isAbleToBeDisplaced(clothing, clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11), false, false, Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
											Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11).getDescription()) + " the " + clothing.getName() + ". "
													+ clothing.getClothingBlockingDescription(
															clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11),
															Main.game.getPlayer(),
															clothing.getSlotEquippedTo(),
															" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>This will expose "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" ",
															".</span>"),
													Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Main.game.getPlayer().isAbleToBeDisplaced(clothing, clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
											Sex.setDisplaceClothingText(clothing, owner.getDisplaceDescription());
											Main.mainController.openInventory();
											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Sex.setSexStarted(true);
										}
									};
								
								} else {
									return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
											"You can't "+clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(owner, clothing.getSlotEquippedTo()).get(index -11).getDescription()
											+ " the " + clothing.getName() + ", as other clothing is in the way!", null);
								}
							}
							
						} else {
							return null;
						}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				InventorySlot slotEquippedTo = clothing.getSlotEquippedTo();
				switch(interactionType) {
					case COMBAT:
						if (index == 1) {
							return new Response("Drop", "You can't make someone drop their clothing while fighting them!", null);
							
						} else if (index==4) {
							return new Response("Dye", "You can't dye someone else's equipped clothing while you're fighting them!", null);
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								if(isAbleToRemoveJinxes()) {
									return new Response("Unjinx (<i>"+clothing.getJinxRemovalCost()+" Essences</i>)", "You can't unjinx someone's clothing while fighting them!", null);
								} else {
									return new Response("Unjinx", "You don't know how to remove jinxes! Perhaps you should pay Lilaya a visit...", null);
								}
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair equipped condoms!", null);
									}
									return new Response("Sabotage", "You can't sabotage equipped condoms!", null);
								}
								return new Response("Enchant", "You can't enchant equipped clothing!", null);
							}
							
						} else if(index == 6) {
							return new Response("Unequip", "You can't unequip someone's clothing while fighting them!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).size()){
							if (clothing.getDisplacedList().contains(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										"The "+ clothing.getName()+ " "
										+(clothing.getClothingType().isPlural()?"have":"has")+" already been "
												+ clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + "!", null);
								
							} else {
								return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										"You can't "+clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + " the " + clothing.getName() + " while in a fight!", null);
							}
							
						} else {
							return null;
						}
						
					case FULL_MANAGEMENT: case CHARACTER_CREATION:
						boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing) && clothing.getRarity()!=Rarity.QUEST;
						
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Drop", "You cannot drop the " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Drop", UtilText.parse(inventoryNPC, "This area is full, so you can't drop [npc.namePos] " + clothing.getName() + " here!"), null);
								} else {
									return new Response((clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)?"Discard":"Drop"),
											(clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)
													?UtilText.parse(inventoryNPC, "Take off [npc.namePos] " + clothing.getName() + " and throw it away.")
													:UtilText.parse(inventoryNPC, "Drop [npc.namePos] " + clothing.getName() + ".")),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								}
								
							} else {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Store", "You cannot drop the " + clothing.getName() + "!", null);
									
								} else if(areaFull && !clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Store", UtilText.parse(inventoryNPC, "This area is full, so you can't store [npc.namePos] " + clothing.getName() + " here!"), null);
								} else {
									return new Response((clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)?"Discard":"Store"),
											(clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)
													?UtilText.parse(inventoryNPC, "Take off [npc.namePos] " + clothing.getName() + " and throw it away.")
													:UtilText.parse(inventoryNPC, "Store [npc.namePos] " + clothing.getName() + " in this area.")),
											INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
										}
									};
								}
							}
							
						} else if(index==2) {
							if(inventoryFull) {
								return new Response("Take", "Your inventory is full, so you can't take this!", null);
								
							} else if(clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
								return new Response("Take", "This piece of clothing is automatically discarded when unequipped, so you can't take it!", null);
								
							} else {
								return new Response("Take",
										UtilText.parse(inventoryNPC, "Take [npc.namePos] " + clothing.getName() + " and add it to your inventory."),
										INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextEndStringBuilder().append(
												"<p style='text-align:center;'>"
													+ unequipClothingToUnequippersInventory(Main.game.getPlayer(), clothing)
												+ "</p>");
									}
								};
							}
							
						} else if (index==4) {
							if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
								return new Response("Dye", 
										Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
											?"Use your proficiency with [style.colourEarth(Earth spells)] to dye this item."
											:"Use a dye-brush to dye this item of clothing.",
										DYE_EQUIPPED_CLOTHING) {
									@Override
									public void effects() {
										resetClothingDyeColours();
									}
								};
								
							} else {
								return new Response("Dye", UtilText.parse(inventoryNPC, "You'll need to find a dye-brush if you want to dye [npc.namePos] clothes."), null);
							}
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								if(isAbleToRemoveJinxes()) {
									if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE) >= clothing.getJinxRemovalCost()) {
										return new Response("Unjinx ([style.italicsArcane("+clothing.getJinxRemovalCost()+" Essences)])",
												"Spend "+clothing.getJinxRemovalCost()+" arcane essences on removing the jinx from this piece of clothing.", INVENTORY_MENU) {
											@Override
											public void effects() {
												Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -clothing.getJinxRemovalCost(), false);
												Main.game.getTextEndStringBuilder().append(UtilText.parse(inventoryNPC,
														"<p>"
															+ "You channel the power of your arcane essences into [npc.namePos] "+clothing.getName()+", and with a bright purple flash, you manage to remove the jinx!"
														+ "</p>"
														+ "<p style='text-align:center;'>"
															+ "Removing the jinx has cost you [style.boldBad("+clothing.getJinxRemovalCost()+")] [style.boldArcane(Arcane Essences)]!"
														+ "</p>"));
												clothing.setSealed(false);
											}
										};
									} else {
										return new Response("Unjinx (<i>"+clothing.getJinxRemovalCost()+" Essences</i>)", "You need at least "+clothing.getJinxRemovalCost()+" arcane essences in order to unjinx this piece of clothing!", null);
									}
								} else {
									return new Response("Unjinx", "You don't know how to remove jinxes! Perhaps you should pay Lilaya a visit...", null);
								}
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair equipped condoms!", null);
									}
									return new Response("Sabotage", "You can't sabotage equipped condoms!", null);
								}
								return new Response("Enchant", "You can't enchant equipped clothing!", null);
							}
							
						} else if(index == 6 && !clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
							return new Response("Unequip", "Unequip the " + clothing.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToInventory(Main.game.getPlayer(), clothing) + "</p>");
								}
							};
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).size()){
							
							if (clothing.getDisplacedList().contains(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getOppositeDescription()),
										Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getOppositeDescription()) + " the " + clothing.getName() + ". "
												+ clothing.getClothingBlockingDescription(
														clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11),
														owner,
														clothing.getSlotEquippedTo(),
														" <span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>This will cover "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" ",
														".</span>"),
												CLOTHING_EQUIPPED){
									@Override
									public void effects(){
										owner.isAbleToBeReplaced(clothing, clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
									}
								};
							} else {
								return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
										Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()) + " the " + clothing.getName() + ". "
												+ clothing.getClothingBlockingDescription(
														clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11),
														owner,
														clothing.getSlotEquippedTo(),
														" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>This will expose "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" ",
														".</span>"),
												CLOTHING_EQUIPPED){
									@Override
									public void effects(){
										owner.isAbleToBeDisplaced(clothing, clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
										Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + owner.getDisplaceDescription() + "</p>");
									}
								};
							}
							
						} else {
							return null;
						}
						
					case SEX:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getPlayerCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Drop", "You cannot drop the " + clothing.getName() + "!", null);
									
								} else if(!Sex.getSexManager().isAbleToRemoveOthersClothing(Main.game.getPlayer(), clothing)) {
									return new Response("Drop", UtilText.parse(inventoryNPC, "You can't unequip the " + clothing.getName() + " in this sex scene!"), null);
									
								} else if(areaFull && !clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Drop", UtilText.parse(inventoryNPC, "This area is full, so you can't drop [npc.namePos] " + clothing.getName() + " here!"), null);
									
								} else {
									if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
										return new Response((clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)?"Discard":"Drop"),
											(clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)
													?UtilText.parse(inventoryNPC, "Take off [npc.namePos] " + clothing.getName() + " and throw it away.")
													:UtilText.parse(inventoryNPC, "Drop [npc.namePos] " + clothing.getName() + ".")),
												Sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												AbstractClothing c = clothing;
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
												Sex.setUnequipClothingText(c, inventoryNPC.getUnequipDescription());
												Main.mainController.openInventory();
												Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("Drop", "You can't unequip the " + clothing.getName() + ", as other clothing is blocking you from doing so!", null);
									}
								}
								
							} else {
								if(!clothing.getClothingType().isAbleToBeDropped()) {
									return new Response("Store", "You cannot drop the " + clothing.getName() + "!", null);
									
								} else if(!Sex.getSexManager().isAbleToRemoveOthersClothing(Main.game.getPlayer(), clothing)) {
									return new Response("Store", UtilText.parse(inventoryNPC, "You can't unequip the " + clothing.getName() + " in this sex scene!"), null);
									
								} else if(areaFull && !clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
									return new Response("Store", UtilText.parse(inventoryNPC, "This area is full, so you can't store [npc.namePos] " + clothing.getName() + " here!"), null);
								} else {
									if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
										return new Response((clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)?"Discard":"Store"),
												(clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)
														?UtilText.parse(inventoryNPC, "Take off [npc.namePos] " + clothing.getName() + " and throw it away.")
														:UtilText.parse(inventoryNPC, "Store [npc.namePos] " + clothing.getName() + " in this area.")),
												Sex.SEX_DIALOGUE){
											@Override
											public void effects(){
												AbstractClothing c = clothing;
												Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>" + unequipClothingToFloor(Main.game.getPlayer(), clothing) + "</p>");
												Sex.setUnequipClothingText(c, inventoryNPC.getUnequipDescription());
												Main.mainController.openInventory();
												Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("Store", "You can't unequip the " + clothing.getName() + ", as other clothing is blocking you from doing so!", null);
									}
								}
							}
							
						} else if (index==4) {
							return new Response("Dye", UtilText.parse(inventoryNPC, "You can't dye [npc.namePos] clothes in sex!"), null);
							
						} else if(index == 5) {
							if(clothing.isSealed()) {
								if(isAbleToRemoveJinxes()) {
									if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE) >= clothing.getJinxRemovalCost()) {
										return new Response("Unjinx ([style.italicsArcane("+clothing.getJinxRemovalCost()+" Essences)])",
												"Spend "+clothing.getJinxRemovalCost()+" arcane essences on removing the jinx from this piece of clothing.", Sex.SEX_DIALOGUE) {
											@Override
											public void effects() {
												Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -clothing.getJinxRemovalCost(), false);
												Sex.setJinxRemovalClothingText(clothing, UtilText.parse(inventoryNPC,
														"<p>"
															+ "You channel the power of your arcane essences into [npc.namePos] "+clothing.getName()+", and with a bright purple flash, you manage to remove the jinx!"
														+ "</p>"
														+ "<p style='text-align:center;'>"
															+ "Removing the jinx has cost you [style.boldBad("+clothing.getJinxRemovalCost()+")] [style.boldArcane(Arcane Essences)]!"
														+ "</p>"));
												clothing.setSealed(false);
												Main.mainController.openInventory();
												Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
												Sex.setSexStarted(true);
											}
										};
									} else {
										return new Response("Unjinx (<i>"+clothing.getJinxRemovalCost()+" Essences</i>)", "You need at least "+clothing.getJinxRemovalCost()+" arcane essences in order to unjinx this piece of clothing!", null);
									}
								} else {
									return new Response("Unjinx", "You don't know how to remove jinxes! Perhaps you should pay Lilaya a visit...", null);
								}
							} else {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair equipped condoms!", null);
									}
									return new Response("Sabotage", "You can't sabotage equipped condoms!", null);
								}
								return new Response("Enchant", "You can't enchant equipped clothing!", null);
							}
							
						} else if(index == 6 && !clothing.getClothingType().isDiscardedOnUnequip(slotEquippedTo)) {
							if(!Sex.getSexManager().isAbleToRemoveOthersClothing(Main.game.getPlayer(), clothing)) {
								return new Response("Unequip", "You can't unequip the " + clothing.getName() + " in this sex scene!", null);
							}
							
							if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
								return new Response("Unequip", "Unequip the " + clothing.getName() + ".", Sex.SEX_DIALOGUE){
									@Override
									public void effects(){
										AbstractClothing c = clothing;
										unequipClothingToInventory(Main.game.getPlayer(), clothing);
										Sex.setUnequipClothingText(c, inventoryNPC.getUnequipDescription());
										Main.mainController.openInventory();
										Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
										Sex.setSexStarted(true);
									}
								};
							} else {
								return new Response("Unequip", "You can't unequip the " + clothing.getName() + ", as other clothing is blocking you from doing so!", null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else if (index > 10 && index - 11 < clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).size()){
							if (clothing.getDisplacedList().contains(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11))) {
								return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
										"The "+ clothing.getName()+ " "
										+(clothing.getClothingType().isPlural()?"have":"has")+" already been "
												+ clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + "!", null);
								
							} else {
								if(owner.isAbleToBeDisplaced(clothing, clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11), false, false, Main.game.getPlayer())){
									
									if(!Sex.getSexManager().isAbleToRemoveOthersClothing(Main.game.getPlayer(), clothing)) {
										return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
												"You "+clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + " the " + clothing.getName() + " in this sex scene!", null);
									}
									
									return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()),
											Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11).getDescription()) + " the " + clothing.getName() + ". "
													+ clothing.getClothingBlockingDescription(
															clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11),
															owner,
															clothing.getSlotEquippedTo(),
															" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>This will expose "+(owner.isPlayer()?"your":owner.getName("")+"'s")+" ",
															".</span>"),
													Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											owner.isAbleToBeDisplaced(clothing, clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11), true, true, Main.game.getPlayer());
											Sex.setDisplaceClothingText(clothing, owner.getDisplaceDescription());
											Main.mainController.openInventory();
											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Sex.setSexStarted(true);
										}
									};
								
								} else {
									return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
											"You can't "+clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()
											+ " the " + clothing.getName() + ", as other clothing is in the way!", null);
								}
							}
							
						} else {
							return null;
						}
						
						case TRADING:
							if (index == 1) {
								return new Response("Drop", UtilText.parse(inventoryNPC, "You can't make [npc.name] drop [npc.her] clothing!"), null);
								
							} else if (index==4) {
								return new Response("Dye", UtilText.parse(inventoryNPC, "You can't dye [npc.namePos] clothes!"), null);
								
							}  else if(index == 5) {
								if(clothing.isCondom()) {
									if(clothing.getCondomEffect().getPotency().isNegative()) {
										return new Response("Repair (<i>1 Essence</i>)", "You can't repair [npc.namePos] condom!", null);
									}
									return new Response("Sabotage", "You can't sabotage [npc.namePos] condom!", null);
								}
								return new Response("Enchant", UtilText.parse(inventoryNPC, "You can't enchant [npc.namePos] clothing!"), null);
								
							} else if(index == 6) {
								return new Response("Unequip", UtilText.parse(inventoryNPC, "You can't unequip [npc.namePos] clothing!"), null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if (index > 10 && index - 11 < clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).size()){
								if (clothing.getDisplacedList().contains(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index - 11))) {
									return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
											"The "+ clothing.getName()+ " "
													+(clothing.getClothingType().isPlural()?"have":"has")+" already been "
													+ clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + "!", null);
									
								} else {
									return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription()),
											"You can't "+clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE(inventoryNPC, clothing.getSlotEquippedTo()).get(index -11).getDescription() + " the " + clothing.getName() + "!", null);
								}
								
							} else {
								return null;
							}
					}
				
				}
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static DamageType damageTypePreview;
	
	public static Colour dyePreviewPrimary;
	public static Colour dyePreviewSecondary;
	public static Colour dyePreviewTertiary;
	public static String dyePreviewPattern;
	public static Colour dyePreviewPatternPrimary;
	public static Colour dyePreviewPatternSecondary;
	public static Colour dyePreviewPatternTertiary;
	
	private static void resetClothingDyeColours() {
		dyePreviewPrimary = clothing.getColour();
		dyePreviewSecondary = clothing.getSecondaryColour();
		dyePreviewTertiary = clothing.getTertiaryColour();
		dyePreviewPattern = clothing.getPattern();
		dyePreviewPatternPrimary = clothing.getPatternColour();
		dyePreviewPatternSecondary = clothing.getPatternSecondaryColour();
		dyePreviewPatternTertiary = clothing.getPatternTertiaryColour();
	}

	private static void resetWeaponDyeColours() {
		dyePreviewPrimary = weapon.getPrimaryColour();
		dyePreviewSecondary = weapon.getSecondaryColour();
		
		damageTypePreview = weapon.getDamageType();
	}
	
	private static String getClothingDyeUI() {
		InventorySlot slotEquippedTo = clothing.getSlotEquippedTo();
		if(slotEquippedTo==null) {
			slotEquippedTo = clothing.getClothingType().getEquipSlots().get(0);
		}
		
		inventorySB = new StringBuilder(
				"<div class='container-full-width'>"
					+ "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ clothing.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<h3 style='text-align:center;'><b>"+clothing.getDisplayName(true)+"</b></h3>"
					+ "<p>"
						+ "Select the desired colours from the coloured buttons below, and after using the preview to see how the new clothing will look, press the 'Dye' option at the bottom of the screen to apply your changes."
					+ "</p>"
				+ "</div>"
					
				+ "<br/>"
				
				+ "<div class='container-full-width'>"
					+ "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ clothing.getClothingType().getSVGImage(slotEquippedTo, dyePreviewPrimary, dyePreviewSecondary, dyePreviewTertiary, dyePreviewPattern, dyePreviewPatternPrimary, dyePreviewPatternSecondary, dyePreviewPatternTertiary)
						+ "</div>"
					+ "</div>"
					+ "<h3 style='text-align:center;'><b>Dye & Preview</b></h3>"
					+ "<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
					+ "Primary Colour:<br/>");

		for (Colour c : clothing.getClothingType().getAllAvailablePrimaryColours()) {
			inventorySB.append("<div class='normal-button"+(dyePreviewPrimary==c?" selected":"")+"' id='PRIMARY_" + (clothing.getClothingType().hashCode() + "_" + c.toString()) + "'"
									+ " style='width:auto; margin-right:4px;"+(dyePreviewPrimary==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
								+ "<div class='phone-item-colour' style='"
									+ (c.isMetallic()
											?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
											:"background-color:" + c.toWebHexString() + ";")
									+ "'></div>"
							+ "</div>");
		}
		
		inventorySB.append("</div>");
		
		if(!clothing.getClothingType().getAllAvailableSecondaryColours().isEmpty()) {
			inventorySB.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
					+ "Secondary Colour:<br/>");
			for (Colour c : clothing.getClothingType().getAllAvailableSecondaryColours()) {
				inventorySB.append("<div class='normal-button"+(dyePreviewSecondary==c?" selected":"")+"' id='SECONDARY_" + (clothing.getClothingType().hashCode() + "_" + c.toString()) + "'"
									+ " style='width:auto; margin-right:4px;"+(dyePreviewSecondary==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
								+ "<div class='phone-item-colour' style='"
									+ (c.isMetallic()
											?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
											:"background-color:" + c.toWebHexString() + ";")
									+ "'></div>"
					+ "</div>");
			}
			inventorySB.append("</div>");
		}

		if(!clothing.getClothingType().getAllAvailableTertiaryColours().isEmpty()) {
			inventorySB.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
					+ "Tertiary Colour:<br/>");
			for (Colour c : clothing.getClothingType().getAllAvailableTertiaryColours()) {
				inventorySB.append("<div class='normal-button"+(dyePreviewTertiary==c?" selected":"")+"' id='TERTIARY_" + (clothing.getClothingType().hashCode() + "_" + c.toString()) + "'"
									+ " style='width:auto; margin-right:4px;"+(dyePreviewTertiary==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
								+ "<div class='phone-item-colour' style='"
									+ (c.isMetallic()
											?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
											:"background-color:" + c.toWebHexString() + ";")
									+ "'></div>"
					+ "</div>");
			}
			inventorySB.append("</div>");
		}
		
		if(clothing.getClothingType().isPatternAvailable()){
			inventorySB.append(
					"<br/>"
					+ "<div class='container-full-width'>"
					+ "Pattern:<br/>");
	 
			for (Pattern pattern : Pattern.getAllPatterns().values()) {
				if (dyePreviewPattern.equals(pattern.getName())) {
					inventorySB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(pattern.getNiceName()) + "</b>"
							+ "</div>");
				} else {
					inventorySB.append(
							"<div id='ITEM_PATTERN_"+pattern.getName()+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(pattern.getNiceName()) + "</span>"
							+ "</div>");
				}
			}
			inventorySB.append("</div>");

			
			if(Pattern.getPattern(dyePreviewPattern)!=null && Pattern.getPattern(dyePreviewPattern).isPrimaryRecolourAvailable()) {
				inventorySB.append("<div class='container-full-width'>"
						+ "Pattern Primary Colour:<br/>");
				for (Colour c : clothing.getClothingType().getAllAvailablePatternPrimaryColours()) {
					inventorySB.append("<div class='normal-button"+(dyePreviewPatternPrimary==c?" selected":"")+"' id='PATTERN_PRIMARY_" + (clothing.getClothingType().hashCode() + "_" + c.toString()) + "'"
										+ " style='width:auto; margin-right:4px;"+(dyePreviewPatternPrimary==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
									+ "<div class='phone-item-colour' style='"
										+ (c.isMetallic()
												?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
												:"background-color:" + c.toWebHexString() + ";")
										+ "'></div>"
						+ "</div>");
				}
				inventorySB.append("</div>");
			}
			
			if(Pattern.getPattern(dyePreviewPattern)!=null && Pattern.getPattern(dyePreviewPattern).isSecondaryRecolourAvailable()) {
				inventorySB.append("<div class='container-full-width'>"
						+ "Pattern Secondary Colour:<br/>");
				for (Colour c : clothing.getClothingType().getAllAvailablePatternSecondaryColours()) {
					inventorySB.append("<div class='normal-button"+(dyePreviewPatternSecondary==c?" selected":"")+"' id='PATTERN_SECONDARY_" + (clothing.getClothingType().hashCode() + "_" + c.toString()) + "'"
										+ " style='width:auto; margin-right:4px;"+(dyePreviewPatternSecondary==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
									+ "<div class='phone-item-colour' style='"
										+ (c.isMetallic()
												?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
												:"background-color:" + c.toWebHexString() + ";")
										+ "'></div>"
						+ "</div>");
				}
				inventorySB.append("</div>");
			}
			
			if(Pattern.getPattern(dyePreviewPattern)!=null && Pattern.getPattern(dyePreviewPattern).isTertiaryRecolourAvailable()) {
				inventorySB.append("<div class='container-full-width'>"
						+ "Pattern Tertiary Colour:<br/>");
				for (Colour c : clothing.getClothingType().getAllAvailablePatternTertiaryColours()) {
					inventorySB.append("<div class='normal-button"+(dyePreviewPatternTertiary==c?" selected":"")+"' id='PATTERN_TERTIARY_" + (clothing.getClothingType().hashCode() + "_" + c.toString()) + "'"
										+ " style='width:auto; margin-right:4px;"+(dyePreviewPatternTertiary==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
									+ "<div class='phone-item-colour' style='"
										+ (c.isMetallic()
												?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
												:"background-color:" + c.toWebHexString() + ";")
										+ "'></div>"
						+ "</div>");
				}
				inventorySB.append("</div>");
			}
			
		}
		inventorySB.append("</div>");
		
		return inventorySB.toString();
	}
	
	private static String getWeaponDyeUI() {
		inventorySB = new StringBuilder(
				"<div class='container-full-width'>"
					+ "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ weapon.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<h3 style='text-align:center;'><b>"+weapon.getDisplayName(true)+"</b></h3>"
					+ "<p>"
						+ "Select the desired colours from the coloured buttons below, and after using the preview to see how the new weapon will look, press the 'Dye' option at the bottom of the screen to apply your changes."
					+ "</p>"
				+ "</div>"
				+ "<br/>"
				+ "<div class='container-full-width'>"
					+ "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ weapon.getWeaponType().getSVGImage(damageTypePreview, dyePreviewPrimary, dyePreviewSecondary)
						+ "</div>"
					+ "</div>"
					+ "<h3 style='text-align:center;'><b>Dye & Preview</b></h3>");
		
		
		inventorySB.append("<div class='container-quarter-width' style='text-align:center;'>"
				+ "<b>Damage type:</b>");
		for(DamageType dt : weapon.getWeaponType().getAvailableDamageTypes()) {
			inventorySB.append("<br/>"
					+ "<div class='normal-button"+(damageTypePreview==dt?" selected":"")+"' id='DAMAGE_TYPE_" + weapon.getWeaponType().hashCode() + "_" + dt.toString() + "'"
							+ "style='width:75%; color:"+(damageTypePreview==dt?dt.getColour().toWebHexString():dt.getColour().getShades(8)[0])+";'>"
						+ Util.capitaliseSentence(dt.getName())
					+ "</div>");
		}
		inventorySB.append("</div>");

		boolean colourOptions = false;
		if(!weapon.getWeaponType().getAllAvailablePrimaryColours().isEmpty()) {
			colourOptions = true;
			inventorySB.append("<div class='container-quarter-width'>"
					+ "Primary:<br/>");
			
			for (Colour c : weapon.getWeaponType().getAllAvailablePrimaryColours()) {
				inventorySB.append("<div class='normal-button"+(dyePreviewPrimary==c?" selected":"")+"' id='PRIMARY_" + (weapon.getWeaponType().hashCode() + "_" + c.toString()) + "'"
										+ " style='width:auto; margin-right:4px;"+(dyePreviewPrimary==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
									+ "<div class='phone-item-colour' style='"
										+ (c.isMetallic()
												?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
												:"background-color:" + c.toWebHexString() + ";")
										+ "'></div>"
								+ "</div>");
			}
			
			inventorySB.append("</div>");
		}
		
		if(!weapon.getWeaponType().getAllAvailableSecondaryColours().isEmpty()) {
			colourOptions = true;
			inventorySB.append("<div class='container-quarter-width'>"
					+ "Secondary:<br/>");
			for (Colour c : weapon.getWeaponType().getAllAvailableSecondaryColours()) {
				inventorySB.append("<div class='normal-button"+(dyePreviewSecondary==c?" selected":"")+"' id='SECONDARY_" + (weapon.getWeaponType().hashCode() + "_" + c.toString()) + "'"
									+ " style='width:auto; margin-right:4px;"+(dyePreviewSecondary==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
								+ "<div class='phone-item-colour' style='"
									+ (c.isMetallic()
											?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
											:"background-color:" + c.toWebHexString() + ";")
									+ "'></div>"
					+ "</div>");
			}
			inventorySB.append("</div>");
		}
		
		if(!colourOptions) {
			inventorySB.append("<div class='container-half-width' style='text-align:center;'>"
					+ "[style.colourDisabled(No dye options available...)]"
					+ "</div>");
		}

		inventorySB.append("</div>");
		
		return inventorySB.toString();
	}
	
	public static final DialogueNode DYE_CLOTHING = new DialogueNode("Dye clothing", "", true) {

		@Override
		public String getContent() {
			return getClothingDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous menu.", INVENTORY_MENU);

			} else if (index == 1) {
				if(dyePreviewPrimary == clothing.getColour()
						&& dyePreviewSecondary == clothing.getSecondaryColour()
						&& dyePreviewTertiary == clothing.getTertiaryColour()
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternPrimary == clothing.getPatternColour()
						&& dyePreviewPatternSecondary == clothing.getPatternSecondaryColour()
						&& dyePreviewPatternTertiary == clothing.getPatternTertiaryColour()) {
					return new Response("Dye",
							"You need to choose different colours before being able to dye the " + clothing.getName() + "!",
							null); 
				}
				
				return new Response("Dye",
						"Dye the " + clothing.getName() + " in the colours you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can dye it a different colour at any time."
										:" This action is permanent, and you'll need another dye-brush if you want to change its colour again."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.DYE_BRUSH.getDyeBrushEffects(clothing, dyePreviewPrimary)
									+ "</p>"
									+ "<p>"
										+ "<b>The " + clothing.getName() + " " + (clothing.getClothingType().isPlural() ? "have been" : "has been") + " dyed</b>!"
									+ "</p>"
									+ "<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
														+ "</b> dye-brush" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
												:"You have <b>0</b> dye-brushes left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to dye the " + clothing.getName() + " without needing to use a dye-brush!"
										+ "</p>");
						}
						
						if(owner!=null) {
							owner.removeClothing(clothing);
							AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
							dyedClothing.setColour(dyePreviewPrimary);
							dyedClothing.setSecondaryColour(dyePreviewSecondary);
							dyedClothing.setTertiaryColour(dyePreviewTertiary);
							dyedClothing.setPattern(dyePreviewPattern);
							dyedClothing.setPatternColour(dyePreviewPatternPrimary);
							dyedClothing.setPatternSecondaryColour(dyePreviewPatternSecondary);
							dyedClothing.setPatternTertiaryColour(dyePreviewPatternTertiary);
							owner.addClothing(dyedClothing, false);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", dyedClothing.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeClothing(clothing);
							AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
							dyedClothing.setColour(dyePreviewPrimary);
							dyedClothing.setSecondaryColour(dyePreviewSecondary);
							dyedClothing.setTertiaryColour(dyePreviewTertiary);
							dyedClothing.setPattern(dyePreviewPattern);
							dyedClothing.setPatternColour(dyePreviewPatternPrimary);
							dyedClothing.setPatternSecondaryColour(dyePreviewPatternSecondary);
							dyedClothing.setPatternTertiaryColour(dyePreviewPatternTertiary);
							Main.game.getPlayerCell().getInventory().addClothing(dyedClothing);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", dyedClothing.getDisplayName(true)), false);
						}
						
					}
				};

			} else if (index == 6) {
				if(dyePreviewPrimary == clothing.getColour()
						&& dyePreviewSecondary == clothing.getSecondaryColour()
						&& dyePreviewTertiary == clothing.getTertiaryColour()
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternPrimary == clothing.getPatternColour()
						&& dyePreviewPatternSecondary == clothing.getPatternSecondaryColour()
						&& dyePreviewPatternTertiary == clothing.getPatternTertiaryColour()) {
					return new Response("Dye all (stack)",
							"You need to choose different colours before being able to dye the " + clothing.getName() + "!",
							null); 
				}
				
				int stackCount = 0;
				if(owner!=null) {
					stackCount = owner.getClothingCount(clothing);
				} else {
					stackCount = Main.game.getPlayerCell().getInventory().getClothingCount(clothing);
				}

				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Dye all (stack)",
							"You only have one "+clothing.getName()+", so you should dye it using the individual action...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.DYE_BRUSH));
					
					if(dyeBrushCount<stackCount) {
						return new Response("Dye all (stack)",
								"You do not have enough dye brushes to dye all the " + clothing.getNamePlural() + "! You have "+dyeBrushCount+" dye brushes, but there are "+stackCount+" "+clothing.getNamePlural()+" in this stack...",
								null); 
					}
				}
				
				return new Response("Dye all (stack)",
						"Dye all " + clothing.getNamePlural() + " which are in this clothing stack ("+stackCount+" in total) in the colours you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can dye them a different colour at any time."
										:" This action is permanent, and you'll need another dye-brush if you want to change their colour again."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.DYE_BRUSH.getDyeBrushEffects(clothing, dyePreviewPrimary)
									+ "</p>"
									+ "<p>"
										+ "<b>The "+clothing.getName()+(clothing.getClothingType().isPlural()?"have":"has")+" been dyed</b>!"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>You then repeat this for the other "+Util.intToString(finalCount-1)+" "+clothing.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
														+ "</b> dye-brush" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
												:"You have <b>0</b> dye-brushes left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to dye "
												+ (finalCount==2
													?"both"
													:"all "+Util.intToString(finalCount))
												+" of the " + clothing.getNamePlural() + " without needing to use a single dye-brush!"
										+ "</p>");
						}
						
						if(owner!=null) {
							owner.removeClothing(clothing, finalCount);
							AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
							dyedClothing.setColour(dyePreviewPrimary);
							dyedClothing.setSecondaryColour(dyePreviewSecondary);
							dyedClothing.setTertiaryColour(dyePreviewTertiary);
							dyedClothing.setPattern(dyePreviewPattern);
							dyedClothing.setPatternColour(dyePreviewPatternPrimary);
							dyedClothing.setPatternSecondaryColour(dyePreviewPatternSecondary);
							dyedClothing.setPatternTertiaryColour(dyePreviewPatternTertiary);
							owner.addClothing(dyedClothing, finalCount, false, false);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", dyedClothing.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeClothing(clothing);
							AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
							dyedClothing.setColour(dyePreviewPrimary);
							dyedClothing.setSecondaryColour(dyePreviewSecondary);
							dyedClothing.setTertiaryColour(dyePreviewTertiary);
							dyedClothing.setPattern(dyePreviewPattern);
							dyedClothing.setPatternColour(dyePreviewPatternPrimary);
							dyedClothing.setPatternSecondaryColour(dyePreviewPatternSecondary);
							dyedClothing.setPatternTertiaryColour(dyePreviewPatternTertiary);
							Main.game.getPlayerCell().getInventory().addClothing(dyedClothing, finalCount);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", dyedClothing.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 11) {
				if(dyePreviewPrimary == clothing.getColour()
						&& dyePreviewSecondary == clothing.getSecondaryColour()
						&& dyePreviewTertiary == clothing.getTertiaryColour()
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternPrimary == clothing.getPatternColour()
						&& dyePreviewPatternSecondary == clothing.getPatternSecondaryColour()
						&& dyePreviewPatternTertiary == clothing.getPatternTertiaryColour()) {
					return new Response("Dye all",
							"You need to choose different colours before being able to dye the " + clothing.getName() + "!",
							null); 
				}
				
				List<AbstractClothing> clothingMatches = new ArrayList<>();
				int stackCount = 0;
				if(owner!=null) {
					for(Entry<AbstractClothing, Integer> entry : owner.getAllClothingInInventory().entrySet()) {
						if(entry.getKey().getClothingType().equals(clothing.getClothingType())) {
							clothingMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				} else {
					for(Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getAllClothingInInventory().entrySet()) {
						if(entry.getKey().getClothingType().equals(clothing.getClothingType())) {
							clothingMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				}
				
				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Dye all",
							"You only have one "+clothing.getName()+", so you should dye it using the individual action...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.DYE_BRUSH));
					
					if(dyeBrushCount<stackCount) {
						return new Response("Dye all (stack)",
								"You do not have enough dye brushes to dye all the " + clothing.getNamePlural() + "! You have "+dyeBrushCount+" dye brushes, but there are "+stackCount+" "+clothing.getNamePlural()+" in total...",
								null); 
					}
				}
				
				return new Response("Dye all",
						"Dye all " + clothing.getNamePlural() + " which are in this clothing stack ("+stackCount+" in total) in the colours you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can dye them a different colour at any time."
										:" This action is permanent, and you'll need another dye-brush if you want to change their colour again."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.DYE_BRUSH.getDyeBrushEffects(clothing, dyePreviewPrimary)
									+ "</p>"
									+ "<p>"
									+ "<b>The "+clothing.getName()+(clothing.getClothingType().isPlural()?"have":"has")+" been dyed</b>!"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>You then repeat this for the other "+Util.intToString(finalCount-1)+" "+clothing.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
														+ "</b> dye-brush" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
												:"You have <b>0</b> dye-brushes left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to dye "
												+ (finalCount==2
													?"both"
													:"all "+Util.intToString(finalCount))
												+" of the " + clothing.getNamePlural() + " without needing to use a single dye-brush!"
										+ "</p>");
						}
						
						if(owner!=null) {
							for(AbstractClothing c : clothingMatches) {
								int clothingCount = owner.getAllClothingInInventory().get(c);
								owner.removeClothing(c, clothingCount);
								AbstractClothing dyedClothing = new AbstractClothing(c) {};
								dyedClothing.setColour(dyePreviewPrimary);
								dyedClothing.setSecondaryColour(dyePreviewSecondary);
								dyedClothing.setTertiaryColour(dyePreviewTertiary);
								dyedClothing.setPattern(dyePreviewPattern);
								dyedClothing.setPatternColour(dyePreviewPatternPrimary);
								dyedClothing.setPatternSecondaryColour(dyePreviewPatternSecondary);
								dyedClothing.setPatternTertiaryColour(dyePreviewPatternTertiary);
								owner.addClothing(dyedClothing, clothingCount, false, false);
								Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", dyedClothing.getDisplayName(true)), false);
							}
							
						} else {
							for(AbstractClothing c : clothingMatches) {
								int clothingCount = Main.game.getPlayerCell().getInventory().getAllClothingInInventory().get(c);
								Main.game.getPlayerCell().getInventory().removeClothing(c, clothingCount);
								AbstractClothing dyedClothing = new AbstractClothing(c) {};
								dyedClothing.setColour(dyePreviewPrimary);
								dyedClothing.setSecondaryColour(dyePreviewSecondary);
								dyedClothing.setTertiaryColour(dyePreviewTertiary);
								dyedClothing.setPattern(dyePreviewPattern);
								dyedClothing.setPatternColour(dyePreviewPatternPrimary);
								dyedClothing.setPatternSecondaryColour(dyePreviewPatternSecondary);
								dyedClothing.setPatternTertiaryColour(dyePreviewPatternTertiary);
								Main.game.getPlayerCell().getInventory().addClothing(dyedClothing, clothingCount);
								Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", dyedClothing.getDisplayName(true)), false);
							}
						}
						
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static final DialogueNode DYE_EQUIPPED_CLOTHING = new DialogueNode("Dye clothing", "", true) {

		@Override
		public String getContent() {
			return getClothingDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous menu.", INVENTORY_MENU);

			} else if (index == 1) {
				if(dyePreviewPrimary == clothing.getColour()
						&& dyePreviewSecondary == clothing.getSecondaryColour()
						&& dyePreviewTertiary == clothing.getTertiaryColour()
						&& dyePreviewPattern.equals(clothing.getPattern())
						&& dyePreviewPatternPrimary == clothing.getPatternColour()
						&& dyePreviewPatternSecondary == clothing.getPatternSecondaryColour()
						&& dyePreviewPatternTertiary == clothing.getPatternTertiaryColour()) {
					return new Response("Dye",
							"You need to choose different colours before being able to dye the " + clothing.getName() + "!",
							null); 
				}
				
				return new Response("Dye",
								"Dye the " + clothing.getName() + " in the colours you have chosen."
										+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can dye it a different colour at any time."
												:" This action is permanent, and you'll need another dye-brush if you want to change its colour again."),
								INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.DYE_BRUSH.getDyeBrushEffects(clothing, dyePreviewPrimary)
									+ "</p>"
									+ "<p>"
										+ "<b>The " + clothing.getName() + " " + (clothing.getClothingType().isPlural() ? "have been" : "has been") + " dyed</b>!"
									+ "</p>"
									+ "<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
														+ "</b> dye-brush" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
												:"You have <b>0</b> dye-brushes left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to dye the " + clothing.getName() + " without needing to use a dye-brush!"
										+ "</p>");
						}
						
						clothing.setColour(dyePreviewPrimary);
						clothing.setSecondaryColour(dyePreviewSecondary);
						clothing.setTertiaryColour(dyePreviewTertiary);
						clothing.setPattern(dyePreviewPattern);
						clothing.setPatternColour(dyePreviewPatternPrimary);
						clothing.setPatternSecondaryColour(dyePreviewPatternSecondary);
						clothing.setPatternTertiaryColour(dyePreviewPatternTertiary);
						Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", clothing.getDisplayName(true)), false);
					}
				};

			} else
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	
	public static final DialogueNode DYE_CLOTHING_CHARACTER_CREATION = new DialogueNode("Choose Colour", "", true) {

		@Override
		public String getContent() {
			return getClothingDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous menu.", CLOTHING_INVENTORY);

			} else if (index == 1) {
				if(dyePreviewPrimary == clothing.getColour() && dyePreviewSecondary == clothing.getSecondaryColour() && dyePreviewTertiary == clothing.getTertiaryColour() && dyePreviewPattern.equals(clothing.getPattern())) {
					return new Response("Dye",
							"You need to choose different colours before being able to dye the " + clothing.getName() + "!",
							null); 
				}
				
				return new Response("Dye",
						"Change the colour of the " + clothing.getName() + " to the colours you have chosen.",
						INVENTORY_MENU){
					@Override
					public void effects(){
						Main.game.getPlayerCell().getInventory().removeClothing(clothing);
						AbstractClothing dyedClothing = new AbstractClothing(clothing) {};
						dyedClothing.setColour(dyePreviewPrimary);
						dyedClothing.setSecondaryColour(dyePreviewSecondary);
						dyedClothing.setTertiaryColour(dyePreviewTertiary);
						dyedClothing.setPattern(dyePreviewPattern);
						dyedClothing.setPatternColour(dyePreviewPatternPrimary);
						dyedClothing.setPatternSecondaryColour(dyePreviewPatternSecondary);
						dyedClothing.setPatternTertiaryColour(dyePreviewPatternTertiary);
						clothing = dyedClothing;
						Main.game.getPlayerCell().getInventory().addClothing(dyedClothing);
					}
				};

			} else
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static final DialogueNode DYE_EQUIPPED_CLOTHING_CHARACTER_CREATION = new DialogueNode("Choose Colour", "", true) {

		@Override
		public String getContent() {
			return getClothingDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous menu.", CLOTHING_EQUIPPED);

			} else if (index  == 1) {
				if(dyePreviewPrimary == clothing.getColour() && dyePreviewSecondary == clothing.getSecondaryColour() && dyePreviewTertiary == clothing.getTertiaryColour() && dyePreviewPattern.equals(clothing.getPattern())) {
					return new Response("Dye",
							"You need to choose different colours before being able to dye the " + clothing.getName() + "!",
							null); 
				}
				
				return new Response("Dye",
						"Change the colour of the " + clothing.getName() + " to the colours you have chosen.",
						CLOTHING_EQUIPPED){
					@Override
					public void effects(){
						clothing.setColour(dyePreviewPrimary);
						clothing.setSecondaryColour(dyePreviewSecondary);
						clothing.setTertiaryColour(dyePreviewTertiary);
						clothing.setPattern(dyePreviewPattern);
						clothing.setPatternColour(dyePreviewPatternPrimary);
						clothing.setPatternSecondaryColour(dyePreviewPatternSecondary);
						clothing.setPatternTertiaryColour(dyePreviewPatternTertiary);
					}
				};

			} else
				return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static final DialogueNode DYE_WEAPON = new DialogueNode("Dye Weapon", "", true) {

		@Override
		public String getContent() {
			return getWeaponDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous menu.", INVENTORY_MENU);

			} else if (index == 1) {
				if (!Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					return new Response("Dye",
							"You do not have a dye-brush, so cannot change the colours of the " + weapon.getName() + "...",
							null); 
				}
				
				if(dyePreviewPrimary == weapon.getPrimaryColour() && dyePreviewSecondary == weapon.getSecondaryColour()) {
					return new Response("Dye",
							"You need to choose different colours before being able to dye the " + weapon.getName() + "!",
							null); 
				}
				
				return new Response("Dye",
						"Dye the " + weapon.getName() + " in the colours you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can dye it a different colour at any time."
										:" This action is permanent, and you'll need another dye-brush if you want to change its colour again."),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.DYE_BRUSH.getDyeBrushEffects(weapon, dyePreviewPrimary)
									+ "</p>"
									+ "<p>"
										+ "<b>The " + weapon.getName() + " " + (weapon.getWeaponType().isPlural() ? "have been" : "has been") + " dyed</b>!"
									+ "</p>"
									+ "<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
														+ "</b> dye-brush" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
												:"You have <b>0</b> dye-brushes left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to dye the " + weapon.getName() + " without needing to use a dye-brush!"
										+ "</p>");
						}
						 
						if(owner!=null) {
							owner.removeWeapon(weapon);
							AbstractWeapon dyedWeapon = AbstractWeaponType.generateWeapon(weapon);
							dyedWeapon.setPrimaryColour(dyePreviewPrimary);
							dyedWeapon.setSecondaryColour(dyePreviewSecondary);
							owner.addWeapon(dyedWeapon, false);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", dyedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon);
							AbstractWeapon dyedWeapon = AbstractWeaponType.generateWeapon(weapon);
							dyedWeapon.setPrimaryColour(dyePreviewPrimary);
							dyedWeapon.setSecondaryColour(dyePreviewSecondary);
							Main.game.getPlayerCell().getInventory().addWeapon(dyedWeapon);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", dyedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 2) {
				if (!Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					return new Response("Reforge",
							"You do not have a reforging hammer, so cannot change the damage type of the " + weapon.getName() + "...",
							null); 
				}
				
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Reforge",
							"You need to choose a different damage type before being able to reforge the " + weapon.getName() + "!",
							null); 
				}
				
				return new Response("Reforge",
						"Reforge the " + weapon.getName() + " into the damage type you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can reforge it at any time."
										:" This action is permanent, and you'll need another reforging hammer if you want to change its damage type again."),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER), owner, false);
							
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.REFORGE_HAMMER.getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
										+ "<b>The " + weapon.getName() + " " + (weapon.getWeaponType().isPlural() ? "have been" : "has been") + " reforged</b>!"
									+ "</p>"
									+ "<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER))
														+ "</b> reforging " + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "hammer" : "hammers") + " left!"
												:"You have <b>0</b> reforging hammers left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to reforge the " + weapon.getName() + " without needing to use a reforging hammer!"
										+ "</p>");
						}
						 
						if(owner!=null) {
							owner.removeWeapon(weapon);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.addWeapon(AbstractWeaponType.generateWeapon(modifiedWeapon), false);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Reforged", modifiedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							Main.game.getPlayerCell().getInventory().addWeapon(AbstractWeaponType.generateWeapon(modifiedWeapon));
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Reforged", modifiedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 3) {
				if ((!Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || !Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH))
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					return new Response("Dye & reforge",
							"You do not have both a dye brush and a reforging hammer, so cannot dye and reforge the " + weapon.getName() + "...",
							null); 
				}
				
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Dye & reforge",
							"You need to choose a different damage type before being able to reforge the " + weapon.getName() + "!",
							null); 
				}
				
				if(dyePreviewPrimary == weapon.getPrimaryColour() && dyePreviewSecondary == weapon.getSecondaryColour()) {
					return new Response("Dye & reforge",
							"You need to choose different colours before being able to dye the " + weapon.getName() + "!",
							null); 
				}
				
				return new Response("Dye & reforge",
						"Dye and reforge the " + weapon.getName() + " into the colours and damage type you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can dye and reforge it at any time."
										:" This action is permanent, and you'll need another dye-brush and another reforging hammer if you want to change its colours and damage type again."),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.DYE_BRUSH.getDyeBrushEffects(weapon, dyePreviewPrimary)
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ ItemType.REFORGE_HAMMER.getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
										+ "<b>The " + weapon.getName() + " " + (weapon.getWeaponType().isPlural() ? "have been" : "has been") + " dyed and reforged</b>!"
									+ "</p>"
									+ "<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
														+ "</b> dye-brush" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
												:"You have <b>0</b> dye-brushes left!")
										+"<br/>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER))
														+ "</b> reforging " + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "hammer" : "hammers") + " left!"
												:"You have <b>0</b> reforging hammers left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to dye and reforge the " + weapon.getName() + " without needing to use a dye-brush or reforging hammer!"
										+ "</p>");
						}
						
						if(owner!=null) {
							owner.removeWeapon(weapon);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							modifiedWeapon.setPrimaryColour(dyePreviewPrimary);
							modifiedWeapon.setSecondaryColour(dyePreviewSecondary);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.addWeapon(AbstractWeaponType.generateWeapon(modifiedWeapon), false);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed & Reforged", modifiedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							modifiedWeapon.setPrimaryColour(dyePreviewPrimary);
							modifiedWeapon.setSecondaryColour(dyePreviewSecondary);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							Main.game.getPlayerCell().getInventory().addWeapon(AbstractWeaponType.generateWeapon(modifiedWeapon));
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed & Reforged", modifiedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 6) {
				if(dyePreviewPrimary == weapon.getPrimaryColour() && dyePreviewSecondary == weapon.getSecondaryColour()) {
					return new Response("Dye all (stack)",
							"You need to choose different colours before being able to dye the " + weapon.getName() + "!",
							null); 
				}
				
				int stackCount = 0;
				if(owner!=null) {
					stackCount = owner.getWeaponCount(weapon);
				} else {
					stackCount = Main.game.getPlayerCell().getInventory().getWeaponCount(weapon);
				}

				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Dye all (stack)",
							"You only have one "+weapon.getName()+", so you should dye it using the individual action...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.DYE_BRUSH));
					
					if(dyeBrushCount<stackCount) {
						return new Response("Dye all (stack)",
								"You do not have enough dye brushes to dye all the " + weapon.getNamePlural() + "! You have "+dyeBrushCount+" dye brushes, but there are "+stackCount+" "+weapon.getNamePlural()+" in this stack...",
								null); 
					}
				}
				
				return new Response("Dye all (stack)",
						"Dye all " + weapon.getNamePlural() + " which are in this weapon stack ("+stackCount+" in total) in the colours you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can dye them a different colour at any time."
										:" This action is permanent, and you'll need another dye-brush if you want to change their colour again."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.DYE_BRUSH.getDyeBrushEffects(weapon, dyePreviewPrimary)
									+ "</p>"
									+ "<p>"
									+ "<b>The "+weapon.getName()+(weapon.getWeaponType().isPlural()?"have":"has")+" been dyed</b>!"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>You then repeat this for the other "+Util.intToString(finalCount-1)+" "+weapon.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
														+ "</b> dye-brush" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
												:"You have <b>0</b> dye-brushes left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to dye "
												+ (finalCount==2
													?"both"
													:"all "+Util.intToString(finalCount))
												+" of the " + weapon.getNamePlural() + " without needing to use a single dye-brush!"
										+ "</p>");
						}
						
						if(owner!=null) {
							owner.removeWeapon(weapon, finalCount);
							AbstractWeapon dyedWeapon = AbstractWeaponType.generateWeapon(weapon);
							dyedWeapon.setPrimaryColour(dyePreviewPrimary);
							dyedWeapon.setSecondaryColour(dyePreviewSecondary);
							owner.addWeapon(dyedWeapon, finalCount, false, false);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", dyedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon, finalCount);
							AbstractWeapon dyedWeapon = AbstractWeaponType.generateWeapon(weapon);
							dyedWeapon.setPrimaryColour(dyePreviewPrimary);
							dyedWeapon.setSecondaryColour(dyePreviewSecondary);
							Main.game.getPlayerCell().getInventory().addWeapon(dyedWeapon, finalCount);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", dyedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 7) {
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Reforge all (stack)",
							"You need to choose a different damage type before being able to reforge the " + weapon.getName() + "!",
							null); 
				}
				
				int stackCount = 0;
				if(owner!=null) {
					stackCount = owner.getWeaponCount(weapon);
				} else {
					stackCount = Main.game.getPlayerCell().getInventory().getWeaponCount(weapon);
				}

				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Reforge all (stack)",
							"You only have one "+weapon.getName()+", so you should reforge it using the individual action...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					int reforgeHammerCount = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER));
					
					if(reforgeHammerCount<stackCount) {
						return new Response("Reforge all (stack)",
								"You do not have enough reforging hammers to dye all the " + weapon.getNamePlural() + "! You have "+reforgeHammerCount+" reforging hammers, but there are "+stackCount+" "+weapon.getNamePlural()+" in this stack...",
								null); 
					}
				}
				
				return new Response("Reforge all (stack)",
						"Reforge all " + weapon.getNamePlural() + " which are in this weapon stack ("+stackCount+" in total) into the damage type you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can reforge them at any time."
										:" This action is permanent, and you'll need another reforging hammer if you want to change their damage type again."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.REFORGE_HAMMER.getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
									+ "<b>The "+weapon.getName()+(weapon.getWeaponType().isPlural()?"have":"has")+" been reforged</b>!"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>You then repeat this for the other "+Util.intToString(finalCount-1)+" "+weapon.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER))
														+ "</b> reforging hammer" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "s") + " left!"
												:"You have <b>0</b> reforging hammers left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to reforge "
												+ (finalCount==2
													?"both"
													:"all "+Util.intToString(finalCount))
												+" of the " + weapon.getNamePlural() + " without needing to use a single reforging hammer!"
										+ "</p>");
						}
						
						if(owner!=null) {
							owner.removeWeapon(weapon, finalCount);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.addWeapon(AbstractWeaponType.generateWeapon(modifiedWeapon), finalCount, false, false);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Reforged", modifiedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon, finalCount);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							Main.game.getPlayerCell().getInventory().addWeapon(AbstractWeaponType.generateWeapon(modifiedWeapon), finalCount);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Reforged", modifiedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 8) {
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Dye & reforge all (stack)",
							"You need to choose a different damage type before being able to reforge the " + weapon.getName() + "!",
							null); 
				}
				
				if(dyePreviewPrimary == weapon.getPrimaryColour() && dyePreviewSecondary == weapon.getSecondaryColour()) {
					return new Response("Dye & reforge all (stack)",
							"You need to choose different colours before being able to dye the " + weapon.getName() + "!",
							null); 
				}
				
				
				int stackCount = 0;
				if(owner!=null) {
					stackCount = owner.getWeaponCount(weapon);
				} else {
					stackCount = Main.game.getPlayerCell().getInventory().getWeaponCount(weapon);
				}

				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Dye & reforge all (stack)",
							"You only have one "+weapon.getName()+", so you should dye & reforge it using the individual action...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.DYE_BRUSH));
					int reforgeHammerCount = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER));
					
					if(dyeBrushCount<stackCount || reforgeHammerCount<stackCount) {
						return new Response("Dye & reforge all (stack)",
								"You do not have enough dye brushes or reforge hammers to dye & reforge all "+stackCount+" "+weapon.getNamePlural()+" in this stack...",
								null); 
					}
				}
				
				return new Response("Dye & reforge all (stack)",
						"Dye & reforge all " + weapon.getNamePlural() + " which are in this weapon stack ("+stackCount+" in total) in the colours and damage type you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can do this at any time."
										:" This action is permanent, and you'll need another dye-brush and reforging hammer if you want to do this again."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.DYE_BRUSH.getDyeBrushEffects(weapon, dyePreviewPrimary)
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ ItemType.REFORGE_HAMMER.getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
									+ "<b>The "+weapon.getName()+(weapon.getWeaponType().isPlural()?"have":"has")+" been dyed and reforged</b>!"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>You then repeat this for the other "+Util.intToString(finalCount-1)+" "+weapon.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
														+ "</b> dye-brush" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
												:"You have <b>0</b> dye-brushes left!")
									+ "</p>");

							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER))
														+ "</b> reforging hammer" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "s") + " left!"
												:"You have <b>0</b> reforging hammers left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to dye "
												+ (finalCount==2
													?"both"
													:"all "+Util.intToString(finalCount))
												+" of the " + weapon.getNamePlural() + " without needing to use a single dye-brush or reforging hammer!"
										+ "</p>");
						}
						
						if(owner!=null) {
							owner.removeWeapon(weapon, finalCount);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							modifiedWeapon.setPrimaryColour(dyePreviewPrimary);
							modifiedWeapon.setSecondaryColour(dyePreviewSecondary);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.addWeapon(AbstractWeaponType.generateWeapon(modifiedWeapon), finalCount, false, false);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed & Reforged", modifiedWeapon.getDisplayName(true)), false);

						} else {
							Main.game.getPlayerCell().getInventory().removeWeapon(weapon, finalCount);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							modifiedWeapon.setPrimaryColour(dyePreviewPrimary);
							modifiedWeapon.setSecondaryColour(dyePreviewSecondary);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							Main.game.getPlayerCell().getInventory().addWeapon(AbstractWeaponType.generateWeapon(modifiedWeapon), finalCount);
							Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed & Reforged", modifiedWeapon.getDisplayName(true)), false);
						}
					}
				};

			} else if (index == 11) {
				if(dyePreviewPrimary == weapon.getPrimaryColour() && dyePreviewSecondary == weapon.getSecondaryColour()) {
					return new Response("Dye all",
							"You need to choose different colours before being able to dye the " + weapon.getName() + "!",
							null); 
				}
				
				List<AbstractWeapon> weaponMatches = new ArrayList<>();
				int stackCount = 0;
				if(owner!=null) {
					for(Entry<AbstractWeapon, Integer> entry : owner.getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				} else {
					for(Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				}
				
				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Dye all",
							"You only have one "+weapon.getName()+", so you should dye it using the individual action...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.DYE_BRUSH));
					
					if(dyeBrushCount<stackCount) {
						return new Response("Dye all (stack)",
								"You do not have enough dye brushes to dye all the " + weapon.getNamePlural() + "! You have "+dyeBrushCount+" dye brushes, but there are "+stackCount+" "+weapon.getNamePlural()+" in total...",
								null); 
					}
				}
				
				return new Response("Dye all",
						"Dye all " + weapon.getNamePlural() + " which are in this clothing stack ("+stackCount+" in total) in the colours you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can dye them a different colour at any time."
										:" This action is permanent, and you'll need another dye-brush if you want to change their colour again."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.DYE_BRUSH.getDyeBrushEffects(weapon, dyePreviewPrimary)
									+ "</p>"
									+ "<p>"
									+ "<b>The "+weapon.getName()+(weapon.getWeaponType().isPlural()?"have":"has")+" been dyed</b>!"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>You then repeat this for the other "+Util.intToString(finalCount-1)+" "+weapon.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
														+ "</b> dye-brush" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
												:"You have <b>0</b> dye-brushes left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to dye "
												+ (finalCount==2
													?"both"
													:"all "+Util.intToString(finalCount))
												+" of the " + weapon.getNamePlural() + " without needing to use a single dye-brush!"
										+ "</p>");
						}
						

						if(owner!=null) {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = owner.getAllWeaponsInInventory().get(w);
								owner.removeWeapon(w, weaponCount);
								AbstractWeapon dyedWeapon = AbstractWeaponType.generateWeapon(w);
								dyedWeapon.setPrimaryColour(dyePreviewPrimary);
								dyedWeapon.setSecondaryColour(dyePreviewSecondary);
								owner.addWeapon(dyedWeapon, weaponCount, false, false);
								Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", dyedWeapon.getDisplayName(true)), false);
							}
							
						} else {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().get(w);
								Main.game.getPlayerCell().getInventory().removeWeapon(w, weaponCount);
								AbstractWeapon dyedWeapon = AbstractWeaponType.generateWeapon(w);
								dyedWeapon.setPrimaryColour(dyePreviewPrimary);
								dyedWeapon.setSecondaryColour(dyePreviewSecondary);
								Main.game.getPlayerCell().getInventory().addWeapon(dyedWeapon, weaponCount);
								Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", dyedWeapon.getDisplayName(true)), false);
							}
						}
					}
				};

			} else if (index == 12) {
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Reforge all",
							"You need to choose a different damage type before being able to reforge the " + weapon.getName() + "!",
							null); 
				}
				
				List<AbstractWeapon> weaponMatches = new ArrayList<>();
				int stackCount = 0;
				if(owner!=null) {
					for(Entry<AbstractWeapon, Integer> entry : owner.getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				} else {
					for(Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				}
				
				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Reforge all",
							"You only have one "+weapon.getName()+", so you should reforge it using the individual action...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					int reforgeHammerCount = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER));
					
					if(reforgeHammerCount<stackCount) {
						return new Response("Reforge all",
								"You do not have enough reforging hammers to dye all the " + weapon.getNamePlural() + "! You have "+reforgeHammerCount+" reforging hammers, but there are "+stackCount+" "+weapon.getNamePlural()+" in total...",
								null); 
					}
				}
				
				return new Response("Reforge all",
						"Reforge all " + weapon.getNamePlural() + " which are in this weapon stack ("+stackCount+" in total) into the damage type you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can reforge them at any time."
										:" This action is permanent, and you'll need another reforging hammer if you want to change their damage type again."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.REFORGE_HAMMER.getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
									+ "<b>The "+weapon.getName()+(weapon.getWeaponType().isPlural()?"have":"has")+" been reforged</b>!"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>You then repeat this for the other "+Util.intToString(finalCount-1)+" "+weapon.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER))
														+ "</b> reforging hammer" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "s") + " left!"
												:"You have <b>0</b> reforging hammers left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to reforge "
												+ (finalCount==2
													?"both"
													:"all "+Util.intToString(finalCount))
												+" of the " + weapon.getNamePlural() + " without needing to use a single reforging hammer!"
										+ "</p>");
						}

						if(owner!=null) {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = owner.getAllWeaponsInInventory().get(w);
								owner.removeWeapon(w, weaponCount);
								AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(w);
								modifiedWeapon.setDamageType(damageTypePreview);
								// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
								owner.addWeapon(AbstractWeaponType.generateWeapon(modifiedWeapon), weaponCount, false, false);
								Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Reforged", modifiedWeapon.getDisplayName(true)), false);
							}

						} else {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().get(w);
								Main.game.getPlayerCell().getInventory().removeWeapon(w, weaponCount);
								AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(w);
								modifiedWeapon.setDamageType(damageTypePreview);
								// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
								Main.game.getPlayerCell().getInventory().addWeapon(AbstractWeaponType.generateWeapon(modifiedWeapon), weaponCount);
								Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Reforged", modifiedWeapon.getDisplayName(true)), false);
							}
						}
					}
				};

			} else if (index == 13) {
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Dye & reforge all",
							"You need to choose a different damage type before being able to reforge the " + weapon.getName() + "!",
							null); 
				}
				
				if(dyePreviewPrimary == weapon.getPrimaryColour() && dyePreviewSecondary == weapon.getSecondaryColour()) {
					return new Response("Dye & reforge all",
							"You need to choose different colours before being able to dye the " + weapon.getName() + "!",
							null); 
				}
				
				List<AbstractWeapon> weaponMatches = new ArrayList<>();
				int stackCount = 0;
				if(owner!=null) {
					for(Entry<AbstractWeapon, Integer> entry : owner.getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				} else {
					for(Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
						if(entry.getKey().getWeaponType().equals(weapon.getWeaponType())) {
							weaponMatches.add(entry.getKey());
							stackCount += entry.getValue();
						}
					}
				}
				
				int finalCount = stackCount;
				
				if(stackCount==1) {
					return new Response("Dye & reforge all",
							"You only have one "+weapon.getName()+", so you should dye & reforge it using the individual action...",
							null); 
				}
				
				if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					int dyeBrushCount = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.DYE_BRUSH));
					int reforgeHammerCount = Main.game.getPlayer().getItemCount(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER));
					
					if(dyeBrushCount<stackCount || reforgeHammerCount<stackCount) {
						return new Response("Dye & reforge all",
								"You do not have enough dye brushes or reforge hammers to dye & reforge all "+stackCount+" "+weapon.getNamePlural()+" in this stack...",
								null); 
					}
				}
				
				return new Response("Dye & reforge all",
						"Dye & reforge all " + weapon.getNamePlural() + " which are in this weapon stack ("+stackCount+" in total) in the colours and damage type you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can do this at any time."
										:" This action is permanent, and you'll need another dye-brush and reforging hammer if you want to do this again."),
						INVENTORY_MENU) {
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), finalCount);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.DYE_BRUSH.getDyeBrushEffects(weapon, dyePreviewPrimary)
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ ItemType.REFORGE_HAMMER.getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
									+ "<b>The "+weapon.getName()+(weapon.getWeaponType().isPlural()?"have":"has")+" been dyed and reforged</b>!"
									+ "</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>You then repeat this for the other "+Util.intToString(finalCount-1)+" "+weapon.getNamePlural()+"...</p>");
							
							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
														+ "</b> dye-brush" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
												:"You have <b>0</b> dye-brushes left!")
									+ "</p>");

							Main.game.getTextEndStringBuilder().append("<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER))
														+ "</b> reforging hammer" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "" : "s") + " left!"
												:"You have <b>0</b> reforging hammers left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to dye and reforge "
												+ (finalCount==2
													?"both"
													:"all "+Util.intToString(finalCount))
												+" of the " + weapon.getNamePlural() + " without needing to use a single dye-brush or reforging hammer!"
										+ "</p>");
						}
						
						if(owner!=null) {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = owner.getAllWeaponsInInventory().get(w);
								owner.removeWeapon(w, weaponCount);
								AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(w);
								modifiedWeapon.setDamageType(damageTypePreview);
								modifiedWeapon.setPrimaryColour(dyePreviewPrimary);
								modifiedWeapon.setSecondaryColour(dyePreviewSecondary);
								// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
								owner.addWeapon(AbstractWeaponType.generateWeapon(modifiedWeapon), weaponCount, false, false);
								Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed & Reforged", modifiedWeapon.getDisplayName(true)), false);
							}
							
						} else {
							for(AbstractWeapon w : weaponMatches) {
								int weaponCount = Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().get(w);
								Main.game.getPlayerCell().getInventory().removeWeapon(w, weaponCount);
								AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(w);
								modifiedWeapon.setDamageType(damageTypePreview);
								modifiedWeapon.setPrimaryColour(dyePreviewPrimary);
								modifiedWeapon.setSecondaryColour(dyePreviewSecondary);
								// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
								Main.game.getPlayerCell().getInventory().addWeapon(AbstractWeaponType.generateWeapon(modifiedWeapon), weaponCount);
								Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed & Reforged", modifiedWeapon.getDisplayName(true)), false);
							}
						}
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};

	public static final DialogueNode DYE_EQUIPPED_WEAPON = new DialogueNode("Dye Weapon", "", true) {

		@Override
		public String getContent() {
			return getWeaponDyeUI();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous menu.", INVENTORY_MENU);

			} else if (index == 1) {
				if (!Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					return new Response("Dye",
							"You do not have a dye-brush, so cannot change the colours of the " + weapon.getName() + "...",
							null); 
				}
				
				if(dyePreviewPrimary == weapon.getPrimaryColour() && dyePreviewSecondary == weapon.getSecondaryColour()) {
					return new Response("Dye",
							"You need to choose different colours before being able to dye the " + weapon.getName() + "!",
							null); 
				}
				
				return new Response("Dye",
						"Dye the " + weapon.getName() + " in the colours you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can dye it a different colour at any time."
										:" This action is permanent, and you'll need another dye-brush if you want to change its colour again."),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.DYE_BRUSH.getDyeBrushEffects(weapon, dyePreviewPrimary)
									+ "</p>"
									+ "<p>"
										+ "<b>The " + weapon.getName() + " " + (weapon.getWeaponType().isPlural() ? "have been" : "has been") + " dyed</b>!"
									+ "</p>"
									+ "<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
														+ "</b> dye-brush" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
												:"You have <b>0</b> dye-brushes left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to dye the " + weapon.getName() + " without needing to use a dye-brush!"
										+ "</p>");
						}
						
						
						
						if(weaponSlot==InventorySlot.WEAPON_MAIN_1
								|| weaponSlot==InventorySlot.WEAPON_MAIN_2
								|| weaponSlot==InventorySlot.WEAPON_MAIN_3) {
							owner.unequipWeaponIntoVoid(weaponSlot, weapon);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setPrimaryColour(dyePreviewPrimary);
							modifiedWeapon.setSecondaryColour(dyePreviewSecondary);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(modifiedWeapon));
							
						} else {
							owner.unequipWeaponIntoVoid(weaponSlot, weapon);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setPrimaryColour(dyePreviewPrimary);
							modifiedWeapon.setSecondaryColour(dyePreviewSecondary);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(modifiedWeapon));
						}

//						weapon.setPrimaryColour(dyePreviewPrimary);
//						weapon.setSecondaryColour(dyePreviewSecondary);
						Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed", weapon.getDisplayName(true)), false);
					}
				};

			} else if (index == 2) {
				if (!Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER)
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					return new Response("Reforge",
							"You do not have a reforging hammer, so cannot change the damage type of the " + weapon.getName() + "...",
							null); 
				}
				
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Reforge",
							"You need to choose a different damage type before being able to reforge the " + weapon.getName() + "!",
							null); 
				}
				
				return new Response("Reforge",
						"Reforge the " + weapon.getName() + " into the damage type you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can reforge it at any time."
										:" This action is permanent, and you'll need another reforging hammer if you want to change its damage type again."),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.REFORGE_HAMMER.getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
										+ "<b>The " + weapon.getName() + " " + (weapon.getWeaponType().isPlural() ? "have been" : "has been") + " reforged</b>!"
									+ "</p>"
									+ "<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER))
														+ "</b> reforging " + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "hammer" : "hammers") + " left!"
												:"You have <b>0</b> reforging hammers left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to reforge the " + weapon.getName() + " without needing to use a reforging hammer!"
										+ "</p>");
						}
						
						if(weaponSlot==InventorySlot.WEAPON_MAIN_1
								|| weaponSlot==InventorySlot.WEAPON_MAIN_2
								|| weaponSlot==InventorySlot.WEAPON_MAIN_3) {
							owner.unequipWeaponIntoVoid(weaponSlot, weapon);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(modifiedWeapon));
							
						} else {
							owner.unequipWeaponIntoVoid(weaponSlot, weapon);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(modifiedWeapon));
						}
						
						Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Reforged", weapon.getDisplayName(true)), false);
					}
				};

			} else if (index == 3) {
				if ((!Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || !Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH))
						&& !Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
					return new Response("Dye & reforge",
							"You do not have both a dye brush and a reforging hammer, so cannot dye and reforge the " + weapon.getName() + "...",
							null); 
				}
				
				if(damageTypePreview == weapon.getDamageType()) {
					return new Response("Dye & reforge",
							"You need to choose a different damage type before being able to reforge the " + weapon.getName() + "!",
							null); 
				}
				
				if(dyePreviewPrimary == weapon.getPrimaryColour() && dyePreviewSecondary == weapon.getSecondaryColour()) {
					return new Response("Dye & reforge",
							"You need to choose different colours before being able to dye the " + weapon.getName() + "!",
							null); 
				}
				
				return new Response("Dye & reforge",
						"Dye and reforge the " + weapon.getName() + " into the colours and damage type you have chosen."
								+ (Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
										?" This action is permanent, but thanks to your proficiency with [style.boldEarth(Earth spells)], you can dye and reforge it at any time."
										:" This action is permanent, and you'll need another dye-brush and another reforging hammer if you want to change its colours and damage type again."),
						INVENTORY_MENU){
					@Override
					public void effects(){
						if(!Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
							Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), owner, false);
							Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER), owner, false);
							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center;'>"
										+ ItemType.DYE_BRUSH.getDyeBrushEffects(weapon, dyePreviewPrimary)
									+ "</p>"
									+ "<p style='text-align:center;'>"
										+ ItemType.REFORGE_HAMMER.getReforgeHammerEffects(weapon, damageTypePreview)
									+ "</p>"
									+ "<p>"
										+ "<b>The " + weapon.getName() + " " + (weapon.getWeaponType().isPlural() ? "have been" : "has been") + " reforged</b>!"
									+ "</p>"
									+ "<p>"
										+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
														+ "</b> dye-brush" + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
												:"You have <b>0</b> dye-brushes left!")
										+"<br/>"
										+ (Main.game.getPlayer().hasItemType(ItemType.REFORGE_HAMMER) || Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
												?"You have <b>" + Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER))
														+ "</b> reforging " + (Main.game.getPlayer().getAllItemsInInventory().get(AbstractItemType.generateItem(ItemType.REFORGE_HAMMER)) == 1 ? "hammer" : "hammers") + " left!"
												:"You have <b>0</b> reforging hammers left!")
									+ "</p>");
							
						} else {
							Main.game.getTextEndStringBuilder().append(
									"<p>"
											+ "Thanks to your proficiency with [style.boldEarth(Earth spells)], you are able to dye and reforge the " + weapon.getName() + " without needing to use a dye-brush or reforging hammer!"
										+ "</p>");
						}
						
						if(weaponSlot==InventorySlot.WEAPON_MAIN_1
								|| weaponSlot==InventorySlot.WEAPON_MAIN_2
								|| weaponSlot==InventorySlot.WEAPON_MAIN_3) {
							owner.unequipWeaponIntoVoid(weaponSlot, weapon);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setPrimaryColour(dyePreviewPrimary);
							modifiedWeapon.setSecondaryColour(dyePreviewSecondary);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(modifiedWeapon));
							
						} else {
							owner.unequipWeaponIntoVoid(weaponSlot, weapon);
							AbstractWeapon modifiedWeapon = AbstractWeaponType.generateWeapon(weapon);
							modifiedWeapon.setPrimaryColour(dyePreviewPrimary);
							modifiedWeapon.setSecondaryColour(dyePreviewSecondary);
							modifiedWeapon.setDamageType(damageTypePreview);
							// For some reason, if you add the modifiedWeapon directly, it won't stack with other identical weapons... Have to generateWeapon(modifiedWeapon) again to get it to start stacking properly:
							owner.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(modifiedWeapon));
						}
						
//						weapon.setDamageType(damageTypePreview);
//						weapon.setPrimaryColour(dyePreviewPrimary);
//						weapon.setSecondaryColour(dyePreviewSecondary);
						Main.game.addEvent(new EventLogEntry(Main.game.getMinutesPassed(), "Dyed & Reforged", weapon.getDisplayName(true)), false);
					}
				};

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.INVENTORY;
		}
	};
	
	
	
	// Utility methods:
	
	private static String getItemDisplayPanel(String SVGString, String title, String description) {
		return "<div class='inventoryImage'>"
					+ "<div class='inventoryImage-content'>"
						+ SVGString
					+ "</div>"
				+ "</div>"
				+ "<h5 style='margin-bottom:0; padding-bottom:0;'><b>"+title+"</b></h5>"
				+ "<p style='margin-top:0; padding-top:0;'>"
					+ description
				+ "</p>";
	}
	
	private static String getGeneralResponseTabTitle(int index) {
		if(index==0) {
			return "Overview";
		} else if(index==1) {
			return "Selected item";
		} else {
			return null;
		}
	}
	
	private static Response getCloseInventoryResponse() {
		if(interactionType == InventoryInteraction.CHARACTER_CREATION) {
			return new Response("Back", "Return to looking in the mirror at your appearance.", CharacterCreation.CHOOSE_ADVANCED_APPEARANCE){
				@Override
				public int getSecondsPassed() {
					return -CharacterCreation.TIME_TO_CLOTHING;
				}
				@Override
				public void effects(){
					item = null;
					clothing = null;
					weapon = null;
				}
			};
			
		} else {
			return new ResponseEffectsOnly("Close Inventory", "Close the Inventory menu."){
				@Override
				public void effects(){
					item = null;
					clothing = null;
					weapon = null;
					Main.mainController.openInventory();
				}
			};
		}
	}
	
	private static Response getBuybackResponse() {
		if (buyback) {
			return new Response("Normal trade", "Switch back to the normal trade menu.", INVENTORY_MENU){
				@Override
				public void effects(){
					buyback = !buyback;
				}
			};
		} else {
			return new Response("Buyback", "Switch to viewing the buyback menu.", INVENTORY_MENU){
				@Override
				public void effects(){
					buyback = !buyback;
				}
			};
		}
	}
	
	private static Response getQuickTradeResponse() { //TODO move this into options
		
		return null;
		
//		if (Main.game.getDialogueFlags().quickTrade) {
//			return new Response("Quick-Manage: <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>",
//					"Quick-Manage is turned <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>!<br/>"
//							+ "That means you can buy and sell items with a single click when trading, and pick-up and drop items with a single click when in normal inventory mode.", INVENTORY_MENU){
//				
//				@Override
//				public DialogueNodeOld getNextDialogue() {
//					return Main.game.getCurrentDialogueNode();
//				}
//				
//				@Override
//				public void effects(){
//					Main.game.getDialogueFlags().quickTrade = !Main.game.getDialogueFlags().quickTrade;
//				}
//			};
//			
//		} else {
//			return new Response("Quick-Manage: <b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>OFF</b>",
//					"Quick-Manage is turned <b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>OFF</b>.<br/>"
//							+ "That means when you click on an item, you get a detailed view of the item before deciding whether to buy/sell or pick-up/drop it.", INVENTORY_MENU){
//
//				@Override
//				public DialogueNodeOld getNextDialogue() {
//					return Main.game.getCurrentDialogueNode();
//				}
//				
//				@Override
//				public void effects(){
//					Main.game.getDialogueFlags().quickTrade = !Main.game.getDialogueFlags().quickTrade;
//				}
//			};
//		}
	}
	
	private static boolean isAbleToRemoveJinxes() {
		return Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY);
	}
	
	
	
	// Items:
	
	private static void transferItems(GameCharacter from, GameCharacter to, AbstractItem item, int count) {
		if (!to.isInventoryFull() || to.hasItem(item) || item.getRarity()==Rarity.QUEST) {
			from.removeItem(item, count);
			to.addItem(item, count, false, to.isPlayer());
		}
		resetPostAction();
	}
	
	private static void dropItems(GameCharacter from, AbstractItem item, int count) {
		if (!Main.game.getPlayerCell().getInventory().isInventoryFull() || Main.game.getPlayerCell().getInventory().hasItem(item)) {
			from.dropItem(item, count, from.isPlayer());
		}
		resetPostAction();
	}
	
	private static void pickUpItems(GameCharacter to, AbstractItem item, int count) {
		if (!to.isInventoryFull() || to.hasItem(item) || item.getRarity()==Rarity.QUEST) {
			to.addItem(item, count, true, to.isPlayer());
		}
		resetPostAction();
	}
	
	private static void sellItems(GameCharacter from, GameCharacter to, AbstractItem item, int count, int itemPrice) {
		if (!to.isPlayer() || !to.isInventoryFull() || to.hasItem(item) || item.getRarity()==Rarity.QUEST) {
			from.incrementMoney(itemPrice*count);
			to.incrementMoney(-itemPrice*count);
			
			if(buyback && to.isPlayer()) {
				Main.game.getPlayer().addItem(new AbstractItem(item.getItemType()) {}, count, false, true);
				Main.game.getPlayer().getBuybackStack().get(buyBackIndex).incrementCount(-count);
				if(Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<=0) {	
					Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
				}
				
			} else {
				if(from.isPlayer()) {
					Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(item, itemPrice, count));
				} else {
					to.addItem(new AbstractItem(item.getItemType()) {}, count, false, true);
				}
				from.removeItem(item, count);
			}
			
			if(from.isPlayer()) {
				Main.game.addEvent(
						new EventLogEntry(
								Main.game.getMinutesPassed(),
								"Sold",
								count+"x <span style='color:"+item.getRarity().getColour().toWebHexString()+";'>"+(count==1?item.getName():item.getNamePlural())+"</span> for "+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(to.isPlayer()) {
				((NPC) from).handleSellingEffects(item, count, itemPrice);
				Main.game.addEvent(
						new EventLogEntry(
								Main.game.getMinutesPassed(),
								"Bought",
								count+"x <span style='color:"+item.getRarity().getColour().toWebHexString()+";'>"+(count==1?item.getName():item.getNamePlural())+"</span> for "+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
		}
		resetPostAction();
	}
	
	
	// Weapons:
	
	private static void transferWeapons(GameCharacter from, GameCharacter to, AbstractWeapon weapon, int count) {
		if (!to.isInventoryFull() || to.hasWeapon(weapon) || weapon.getRarity()==Rarity.QUEST) {
			from.removeWeapon(weapon, count);
			to.addWeapon(weapon, count, false, to.isPlayer());
		}
		resetPostAction();
	}
	
	private static void dropWeapons(GameCharacter from, AbstractWeapon weapon, int count) {
		if (!Main.game.getPlayerCell().getInventory().isInventoryFull() || Main.game.getPlayerCell().getInventory().hasWeapon(weapon)) {
			from.dropWeapon(weapon, count, from.isPlayer());
		}
		resetPostAction();
	}
	
	private static void pickUpWeapons(GameCharacter to, AbstractWeapon weapon, int count) {
		if (!to.isInventoryFull() || to.hasWeapon(weapon) || weapon.getRarity()==Rarity.QUEST) {
			to.addWeapon(weapon, count, true, to.isPlayer());
		}
		resetPostAction();
	}
	
	private static void sellWeapons(GameCharacter from, GameCharacter to, AbstractWeapon weapon, int count, int itemPrice) {
		if (!to.isPlayer() || !to.isInventoryFull() || to.hasWeapon(weapon) || weapon.getRarity()==Rarity.QUEST) {

			from.incrementMoney(itemPrice*count);
			to.incrementMoney(-itemPrice*count);
			
			if(buyback && to.isPlayer()) {
				Main.game.getPlayer().addWeapon(AbstractWeaponType.generateWeapon(weapon), count, false, true);
				Main.game.getPlayer().getBuybackStack().get(buyBackIndex).incrementCount(-count);
				if(Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<=0) {	
					Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
				}
				
			} else {
				if(from.isPlayer()) {
					Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(weapon, itemPrice, count));
				} else {
					to.addWeapon(AbstractWeaponType.generateWeapon(weapon), count, false, true);
				}
				from.removeWeapon(weapon, count);
			}
			
			if(from.isPlayer()) {
				Main.game.addEvent(
						new EventLogEntry(
								Main.game.getMinutesPassed(),
								"Sold",
								count+"x <span style='color:"+weapon.getRarity().getColour().toWebHexString()+";'>"+(count==1?weapon.getName():weapon.getNamePlural())+"</span> for "+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(to.isPlayer()) {
				((NPC) from).handleSellingEffects(weapon, count, itemPrice);
				Main.game.addEvent(
						new EventLogEntry(
								Main.game.getMinutesPassed(),
								"Bought",
								count+"x <span style='color:"+weapon.getRarity().getColour().toWebHexString()+";'>"+(count==1?weapon.getName():weapon.getNamePlural())+"</span> for "+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
		}
		resetPostAction();
	}
	
	
	// Clothing:
	
	private static void transferClothing(GameCharacter from, GameCharacter to, AbstractClothing clothing, int count) {
		if (!to.isInventoryFull() || to.hasClothing(clothing) || clothing.getRarity()==Rarity.QUEST) {
			from.removeClothing(clothing, count);
			to.addClothing(clothing, count, false, to.isPlayer());
			owner = to;
		}
		resetPostAction();
	}
	
	
	private static void dropClothing(GameCharacter from, AbstractClothing clothing, int count) {
		if (!Main.game.getPlayerCell().getInventory().isInventoryFull() || Main.game.getPlayerCell().getInventory().hasClothing(clothing)) {
			from.dropClothing(clothing, count, from.isPlayer());
			
			if(from.getClothingCount(clothing) == 0) {
				owner = null;
			}
		}
		resetPostAction();
	}
	
	private static void pickUpClothing(GameCharacter to, AbstractClothing clothing, int count) {
		if (!to.isInventoryFull() || to.hasClothing(clothing) || clothing.getRarity()==Rarity.QUEST) {
			to.addClothing(clothing, count, true, to.isPlayer());
			
			owner = to;
		}
		resetPostAction();
	}
	
	private static void sellClothing(GameCharacter from, GameCharacter to, AbstractClothing clothing, int count, int itemPrice) {
		if (!to.isPlayer() || !to.isInventoryFull() || to.hasClothing(clothing) || clothing.getRarity()==Rarity.QUEST) {

			from.incrementMoney(itemPrice*count);
			to.incrementMoney(-itemPrice*count);
			
			if(buyback && to.isPlayer()) {
				Main.game.getPlayer().addClothing(new AbstractClothing(clothing) {}, count, false, true);
				Main.game.getPlayer().getBuybackStack().get(buyBackIndex).incrementCount(-count);
				if(Main.game.getPlayer().getBuybackStack().get(buyBackIndex).getCount()<=0) {	
					Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
				}
				
			} else {
				if(from.isPlayer()) {
					Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(clothing, itemPrice, count));
				} else {
					to.addClothing(new AbstractClothing(clothing) {}, count, false, true);
				}
				from.removeClothing(clothing, count);
			}
			
			if(from.isPlayer()) {
				Main.game.addEvent(
						new EventLogEntry(
								Main.game.getMinutesPassed(),
								"Sold",
								count+"x <span style='color:"+clothing.getRarity().getColour().toWebHexString()+";'>"+(count==1?clothing.getName():clothing.getNamePlural())+"</span> for "+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
			
			if(to.isPlayer()) {
				((NPC) from).handleSellingEffects(clothing, count, itemPrice);
				Main.game.addEvent(
						new EventLogEntry(
								Main.game.getMinutesPassed(),
								"Bought",
								count+"x <span style='color:"+clothing.getRarity().getColour().toWebHexString()+";'>"+(count==1?clothing.getName():clothing.getNamePlural())+"</span> for "+UtilText.formatAsMoney(itemPrice*count)),
						false);
			}
		}
		resetPostAction();
	}
	
	private static String unequipClothingToFloor(GameCharacter unequipper, AbstractClothing clothing) {
		String unequipDescription = "";
		if(clothing.getClothingType().isDiscardedOnUnequip(clothing.getSlotEquippedTo())) {
			unequipDescription = owner.unequipClothingIntoVoid(clothing, true, unequipper);
		} else {
			unequipDescription = owner.unequipClothingOntoFloor(clothing, true, unequipper);
		}
		owner = null;
		resetPostAction();
		
		return unequipDescription;
	}
	
	private static String unequipClothingToUnequippersInventory(GameCharacter unequipper, AbstractClothing clothing) {
		String unequipDescription = "";
		if(clothing.getClothingType().isDiscardedOnUnequip(clothing.getSlotEquippedTo())) {
			unequipDescription = owner.unequipClothingIntoVoid(clothing, true, unequipper);
		} else {
			unequipDescription = owner.unequipClothingIntoUnequippersInventory(clothing, true, unequipper);
		}
		resetPostAction();
		
		return unequipDescription;
	}
	
	private static String unequipClothingToInventory(GameCharacter unequipper, AbstractClothing clothing) {
		String unequipDescription = "";
		if(clothing.getClothingType().isDiscardedOnUnequip(clothing.getSlotEquippedTo())) {
			unequipDescription = owner.unequipClothingIntoVoid(clothing, true, unequipper);
		} else {
			unequipDescription = owner.unequipClothingIntoInventory(clothing, true, unequipper);
		}
		resetPostAction();
		
		return unequipDescription;
	}
	
	private static String equipClothingFromInventory(GameCharacter to, InventorySlot slot, GameCharacter equipper, AbstractClothing clothing) {
		String equipDescription = to.equipClothingFromInventory(clothing, slot, true, equipper, owner);
		owner = to;
		resetPostAction();
		return equipDescription;
	}
	
	private static String equipClothingFromGround(GameCharacter to, InventorySlot slot, GameCharacter equipper, AbstractClothing clothing) {
		owner = to;
		resetPostAction();
		return to.equipClothingFromGround(clothing, slot, true, equipper);
	}
	
	private static Response getCondomSabotageResponse(AbstractClothing clothing) {
		if(clothing.getCondomEffect().getPotency().isNegative()) {
			if(Main.game.getPlayer().getEssenceCount(TFEssence.ARCANE) >= 1) {
				return new Response("Repair ([style.italicsArcane(1 Essence)])",
						"Spend 1 arcane essence to repair the condom.", CLOTHING_INVENTORY) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, -1, false);
						Main.game.getTextEndStringBuilder().append(
								"<p>"
									+ "You channel the power of an arcane essence into the condom, and, after emitting a faint purple glow, it is repaired!"
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ "Repairing the condom has cost you [style.boldBad(1)] [style.boldArcane(Arcane Essence)]!"
								+ "</p>");
						AbstractClothing c = (AbstractClothing) EnchantmentDialogue.craftItem(clothing, clothing.getClothingType().getEffects());

						Main.game.getPlayer().removeClothing(c);
						c.setName(c.getClothingType().getName());
						setClothing(c);
						Main.game.getPlayer().addClothing(c, false);
						
						RenderingEngine.setPage(Main.game.getPlayer(), c);
					}
				};
			} else {
				return new Response("Repair (<i>1 Essence</i>)", "You need at least 1 arcane essence in order to repair the condom!", null);
			}
			
		} else {
			return new Response("Sabotage", "By making a small tear in the end of this condom, you can ensure that it will break at the moment of orgasm!", CLOTHING_INVENTORY) {
				@Override
				public void effects(){
					AbstractClothing c = (AbstractClothing) EnchantmentDialogue.craftItem(clothing, Util.newArrayListOfValues(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_CONDOM, TFModifier.ARCANE_BOOST, TFPotency.MAJOR_DRAIN, 0)));
					
					Main.game.getPlayer().removeClothing(c);
					c.setName(c.getClothingType().getName());
					setClothing(c);
					Main.game.getPlayer().addClothing(c, false);

					RenderingEngine.setPage(Main.game.getPlayer(), c);
					Main.game.getTextEndStringBuilder().append(
							"<p>"
								+ "By making a tiny, near-invisible tear in the end of the condom, you ensure that it will split when filled with cum..."
							+ "</p>"
							+ "<p style='text-align:center;'>"
								+ "[style.italicsBad(The condom is now guaranteed to break upon orgasm)]!"
							+ "</p>");
				}
			};
		}
	}
	
	private static void resetPostAction() {
		Main.game.setResponseTab(0);
		resetItems();
	}
	
	private static void resetItems() {
		item = null;
		clothing = null;
		weapon = null;
	}

	public static AbstractItem getItem() {
		return item;
	}

	public static void setItem(AbstractItem item) {
		resetItems();
		InventoryDialogue.item = item;
	}

	public static AbstractWeapon getWeapon() {
		return weapon;
	}

	public static void setWeapon(InventorySlot slot, AbstractWeapon weapon) {
		resetItems();
		InventoryDialogue.weaponSlot = slot;
		InventoryDialogue.weapon = weapon;
	}

	public static AbstractClothing getClothing() {
		return clothing;
	}

	public static void setClothing(AbstractClothing clothing) {
		resetItems();
		InventoryDialogue.clothing = clothing;
	}

	public static boolean isBuyback() {
		return buyback;
	}

	public static void setBuyback(boolean buyback) {
		InventoryDialogue.buyback = buyback;
	}

	public static int getBuyBackPrice() {
		return buyBackPrice;
	}

	public static void setBuyBackPrice(int buyBackPrice) {
		InventoryDialogue.buyBackPrice = buyBackPrice;
	}

	public static int getBuyBackIndex() {
		return buyBackIndex;
	}

	public static void setBuyBackIndex(int buyBackIndex) {
		InventoryDialogue.buyBackIndex = buyBackIndex;
	}

	public static GameCharacter getOwner() {
		return owner;
	}

	public static void setOwner(GameCharacter owner) {
		InventoryDialogue.owner = owner;
	}

	public static NPC getInventoryNPC() {
		return inventoryNPC;
	}

	public static void setInventoryNPC(NPC inventoryNPC) {
		InventoryDialogue.inventoryNPC = inventoryNPC;
	}

	public static InventoryInteraction getNPCInventoryInteraction() {
		return interactionType;
	}

	public static void setNPCInventoryInteraction(InventoryInteraction nPCInventoryInteraction) {
		interactionType = nPCInventoryInteraction;
	}

}