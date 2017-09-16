package com.base.game.dialogue.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.base.game.character.GameCharacter;
import com.base.game.character.Quest;
import com.base.game.character.QuestLine;
import com.base.game.character.npc.NPC;
import com.base.game.character.npc.dominion.Nyan;
import com.base.game.combat.Attack;
import com.base.game.combat.Combat;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.ShopTransaction;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.inventory.item.AbstractItemType;
import com.base.game.inventory.item.ItemType;
import com.base.game.inventory.weapon.AbstractWeapon;
import com.base.game.sex.Sex;
import com.base.game.sex.sexActions.SexActionUtility;
import com.base.main.Main;
import com.base.rendering.RenderingEngine;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.85
 * @author Innoxia
 */
public class InventoryDialogue {
	
	// Welcome to a slightly cleaned-up hell!
	
	private static final int IDENTIFICATION_PRICE = 10;
	
	private static AbstractItem item, itemFloor;
	private static AbstractClothing clothing, clothingFloor;
	private static AbstractWeapon weapon, weaponFloor;
	private static GameCharacter owner;
	
	private static NPC inventoryNPC;
	private static InventoryInteraction interactionType;

	private static StringBuilder inventorySB;

	private static List<AbstractClothing> jinxedClothing = new ArrayList<>();

	private static boolean jinxRemovalFromFloor, buyback;

	private static int buyBackPrice, buyBackIndex;

	private static String inventoryView() {
		inventorySB = new StringBuilder();
		
		inventorySB.append(RenderingEngine.ENGINE.getInventoryPanel(inventoryNPC, buyback));
		
		return inventorySB.toString();
	}

	/**
	 * The main DialogueNode. From here, the player can gain access to all parts
	 * of their inventory.
	 */
	public static final DialogueNodeOld INVENTORY_MENU = new DialogueNodeOld("Inventory", "Return to inventory menu.", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().quickTrade && !Main.game.isInSex() && !Main.game.isInCombat()) {
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
			if (inventoryNPC!=null && interactionType == InventoryInteraction.TRADING) {
				return inventoryNPC.getTraderDescription();
			} else {
				return "";
			}
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			} else if (index == 1 && inventoryNPC==null) {
				if(Main.game.getPlayerCell().getInventory().getInventorySlotsTaken()==0 || Main.game.isInCombat() || Main.game.isInSex()) {
					return new Response("Take all", "Pick up everything on the ground.", null);
					
				} else {
					return new Response("Take all", "Pick up everything on the ground.", INVENTORY_MENU){
						@Override
						public void effects(){
							//TODO if this starts printing it will complain about the player's inventory being full
							//TODO optimize (what if someone stores a thousand panties somewhere?)
							int i = Main.game.getPlayerCell().getInventory().getItemsInInventory().size();
							while(i > 0) {
								Main.game.getPlayer().addItem(Main.game.getPlayerCell().getInventory().getItemsInInventory().get(i-1), true);
								i--;
							}
							
							i = Main.game.getPlayerCell().getInventory().getClothingInInventory().size();
							while(i > 0) {
								Main.game.getPlayer().addClothing(Main.game.getPlayerCell().getInventory().getClothingInInventory().get(i-1), true);
								i--;
							}
							
							i = Main.game.getPlayerCell().getInventory().getWeaponsInInventory().size();
							while(i > 0) {
								Main.game.getPlayer().addWeapon(Main.game.getPlayerCell().getInventory().getWeaponsInInventory().get(i-1), true);
								i--;
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
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};

	public static final DialogueNodeOld ITEM_INVENTORY = new DialogueNodeOld("Item", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().quickTrade && !Main.game.isInSex() && !Main.game.isInCombat()) {
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
			return "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ item.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+item.getDisplayName(true)+"</b></p>"
					+ item.getDescription()
					+ item.getExtraDescription(owner, owner)
					+ (inventoryNPC != null && interactionType == InventoryInteraction.TRADING
						? inventoryNPC.willBuy(item)
							? "<p>"
								+ inventoryNPC.getName("The") + " will buy it for " + UtilText.formatAsMoney(item.getPrice(inventoryNPC.getBuyModifier())) + "."
							+ "</p>" 
							: inventoryNPC.getName("The") + " doesn't want to buy this."
						: "");
		}

		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasItem(item);
					
					if(index == 1) {
						if(owner.getLocationPlace().isItemsDisappear()) {
							if(areaFull) {
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
							if(areaFull) {
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
						if(owner.getItemCount(item) < 5) {
							return new Response("Drop (5)", "You don't have five " + item.getNamePlural() + " to give!", null);
						}
						if(owner.getLocationPlace().isItemsDisappear()) {
							if(areaFull) {
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
							if(areaFull) {
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
							if(areaFull) {
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
							if(areaFull) {
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
						if(item.getEnchantmentItemType()==null) {
							return new Response("Enchant", "This item cannot be enchanted!", null);
							
						} else if(Main.game.isDebugMode()) {
							return new Response("Enchant", "Enchant this item.", EnchantmentDialogue.ENCHANTMENT_MENU) {
								@Override
								public void effects() {
									EnchantmentDialogue.effects.clear();
									EnchantmentDialogue.resetEnchantmentVariables();
									EnchantmentDialogue.ingredient = item;
								}
							};
							
						} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
							if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_ENCHANTMENTS_LILAYA_HELP)) {
								return new Response("Enchant", "Enchant this item.", EnchantmentDialogue.ENCHANTMENT_MENU) {
									@Override
									public void effects() {
										EnchantmentDialogue.effects.clear();
										EnchantmentDialogue.resetEnchantmentVariables();
										EnchantmentDialogue.ingredient = item;
									}
								};
							}
						}
						
						return null;
						
					} else if(index == 6) {
						if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
							return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
						} else {
							return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
									Util.capitaliseSentence(item.getItemType().getUseName()) + " the " + item.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
								}
							};
						}
						
					} else if (index == 10) {
						return getQuickTradeResponse();
						
					} else if(index == 11) {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)", "There's nobody to use your "+item.getName()+" on!", null);
						
					} else {
						return null;
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
								if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", Util.capitaliseSentence(item.getItemType().getUseName()) + " the " + item.getName() + ".", Combat.ENEMY_ATTACK){
										@Override
										public void effects(){
											Combat.setPlayerTurnText(Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false));
											Combat.attackEnemy();
											Combat.setPreviousAction(Attack.NONE);
											Main.game.restoreSavedContent();
										}
									};
								}
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if (!item.isAbleToBeUsed(inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (opponent)", item.getUnableToBeUsedDescription(inventoryNPC), null);
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (opponent)", "Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".", Combat.ENEMY_ATTACK){
										@Override
										public void effects(){
											Combat.setPlayerTurnText(Main.game.getPlayer().useItem(item, inventoryNPC, false));
											Combat.attackEnemy();
											Combat.setPreviousAction(Attack.NONE);
											Main.game.restoreSavedContent();
										}
									};
								}
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:
							boolean inventoryFull = inventoryNPC.isInventoryFull() && !inventoryNPC.hasItem(item);
							
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Give (1)", UtilText.parse(inventoryNPC, "[npc.Name]'s inventory is already full!"), null);
								}
								return new Response("Give (1)", UtilText.parse(inventoryNPC, "Give [npc.name] " + item.getItemType().getDeterminer() + " " + item.getName() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(Main.game.getPlayer(), inventoryNPC, item, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("Give (5)", UtilText.parse(inventoryNPC, "[npc.Name]'s inventory is already full!"), null);
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
								if(inventoryFull) {
									return new Response("Give (All)", UtilText.parse(inventoryNPC, "[npc.Name]'s inventory is already full!"), null);
								}
								return new Response("Give (All)", UtilText.parse(inventoryNPC, "Give [npc.name] all of your " + item.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(Main.game.getPlayer(), inventoryNPC, item, Main.game.getPlayer().getItemCount(item));
									}
								};
								
							} else if(index == 5) {
								if(item.getEnchantmentItemType()==null) {
									return new Response("Enchant", "This item cannot be enchanted!", null);
									
								} else if(Main.game.isDebugMode()) {
									return new Response("Enchant", "Enchant this item.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public void effects() {
											EnchantmentDialogue.effects.clear();
											EnchantmentDialogue.resetEnchantmentVariables();
											EnchantmentDialogue.ingredient = item;
										}
									};
									
								} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_ENCHANTMENTS_LILAYA_HELP)) {
										return new Response("Enchant", "Enchant this item.", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public void effects() {
												EnchantmentDialogue.effects.clear();
												EnchantmentDialogue.resetEnchantmentVariables();
												EnchantmentDialogue.ingredient = item;
											}
										};
									}
								}
								
								return null;
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											Util.capitaliseSentence(item.getItemType().getUseName()) + " the " + item.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if (!item.isAbleToBeUsed(inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)", item.getUnableToBeUsedDescription(inventoryNPC), null);
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)",
											"Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, inventoryNPC, false) + "</p>");
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
								if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											Util.capitaliseSentence(item.getItemType().getUseName()) + " the " + item.getName() + ".", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUsingItemText(Sex.getPartner().getItemUseEffects(item, Main.game.getPlayer(), Main.game.getPlayer()));
											Main.game.restoreSavedContent();
											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if (!item.isAbleToBeUsed(inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)", item.getUnableToBeUsedDescription(inventoryNPC), null);
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)",
											"Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUsingItemText(Sex.getPartner().getItemUseEffects(item, Main.game.getPlayer(), inventoryNPC));
											Main.game.restoreSavedContent();
											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
										}
									};
								}
							} else {
								return null;
							}
							
						case TRADING:
							if(index == 1) {
								if (inventoryNPC.willBuy(item)) {
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
									if (inventoryNPC.willBuy(item)) {
										int sellPrice = item.getPrice(inventoryNPC.getBuyModifier());
										return new Response("Sell (1) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "Sell five of your " + item.getNamePlural() + " for " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
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
								if (inventoryNPC.willBuy(item)) {
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
								if(item.getEnchantmentItemType()==null) {
									return new Response("Enchant", "This item cannot be enchanted!", null);
									
								} else if(Main.game.isDebugMode()) {
									return new Response("Enchant", "Enchant this item.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public void effects() {
											EnchantmentDialogue.effects.clear();
											EnchantmentDialogue.resetEnchantmentVariables();
											EnchantmentDialogue.ingredient = item;
										}
									};
									
								} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_ENCHANTMENTS_LILAYA_HELP)) {
										return new Response("Enchant", "Enchant this item.", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public void effects() {
												EnchantmentDialogue.effects.clear();
												EnchantmentDialogue.resetEnchantmentVariables();
												EnchantmentDialogue.ingredient = item;
											}
										};
									}
								}
								
								return null;
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											Util.capitaliseSentence(item.getItemType().getUseName()) + " the " + item.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
										}
									};
								}
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)", UtilText.parse(inventoryNPC, "[npc.Name] doesn't want to use your items."), null);
								
							} else {
								return null;
							}
					}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item);
					
					if(index == 1) {
						if(inventoryFull) {
							return new Response("Take (1)", "Your inventory is already full!", null);
						}
						return new Response("Take (1)", "Take one " + item.getItemType().getDeterminer() + " " + item.getName() + " from the ground.", INVENTORY_MENU){
							@Override
							public void effects(){
								pickUpItems(Main.game.getPlayer(), item, 1);
							}
						};
						
					} else if(index == 2) {
						if(inventoryFull) {
							return new Response("Take (5)", "Your inventory is already full!", null);
						}
						if(Main.game.getCurrentCell().getInventory().getItemCount(item) >= 5) {
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
								pickUpItems(Main.game.getPlayer(), item, Main.game.getCurrentCell().getInventory().getItemCount(item));
							}
						};
						
					} else if(index == 5) {
						return new Response("Enchant", "You can't enchant items on the ground!", null);
						
					} else if(index == 6) {
						if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
							return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
						} else {
							return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
									Util.capitaliseSentence(item.getItemType().getUseName()) + " the " + item.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), true) + "</p>");
								}
							};
						}
						
					} else if (index == 10) {
						return getQuickTradeResponse();
						
					} else if(index == 11) {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)", "There's nobody to use " + item.getItemType().getDeterminer() + " "+item.getName()+" on!", null);
						
					} else {
						return null;
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
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (opponent)", "You can't use make someone use an item while fighting them!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item);
						
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Take (1)", "Your inventory is already full!", null);
								}
								return new Response("Take (1)", UtilText.parse(inventoryNPC, "Take " + item.getItemType().getDeterminer() + " " + item.getName() + " from [npc.name]."), INVENTORY_MENU){
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
									return new Response("Take (5)", UtilText.parse(inventoryNPC, "Take five of  [npc.name]'s " + item.getNamePlural() + "."), INVENTORY_MENU){
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
								return new Response("Take (All)", UtilText.parse(inventoryNPC, "Take five of  [npc.name]'s " + item.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferItems(inventoryNPC, Main.game.getPlayer(), item, inventoryNPC.getItemCount(item));
									}
								};
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant items owned by someone else!", null);
								
							} else if(index == 6) {
								if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											Util.capitaliseSentence(item.getItemType().getUseName()) + " the " + item.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.useItem(item, Main.game.getPlayer(), false) + "</p>");
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if (!item.isAbleToBeUsed(inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)", item.getUnableToBeUsedDescription(inventoryNPC), null);
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)",
											"Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.useItem(item, inventoryNPC, false) + "</p>");
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
								if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
											Util.capitaliseSentence(item.getItemType().getUseName()) + " the " + item.getName() + ".", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUsingItemText(Sex.getPartner().getItemUseEffects(item, inventoryNPC, Main.game.getPlayer()));
											Main.game.restoreSavedContent();
											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
										}
									};
								}
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								if (!item.isAbleToBeUsed(inventoryNPC)) {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)", item.getUnableToBeUsedDescription(inventoryNPC), null);
								} else {
									return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)",
											"Get "+inventoryNPC.getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUsingItemText(Sex.getPartner().getItemUseEffects(item, inventoryNPC, inventoryNPC));
											Main.game.restoreSavedContent();
											Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
										}
									};
								}
							} else {
								return null;
							}
							
						case TRADING:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item);
							
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
								if(buyback) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "Cannot use this option in the buyback menu.", null);
								}
								if(inventoryNPC.getItemCount(item) < 5) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", UtilText.parse(inventoryNPC, "[npc.Name] doesn't have five "+item.getNamePlural()+"."), null);
								}
								if(inventoryFull) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
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
								if(buyback) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "Cannot use this option in the buyback menu.", null);
								}
								if(inventoryFull) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice*inventoryNPC.getItemCount(item), "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice*inventoryNPC.getItemCount(item), "span")+")", "You can't afford to buy this!", null);
								}
								return new Response("Buy (All) (" + UtilText.formatAsMoney(sellPrice*inventoryNPC.getItemCount(item), "span") + ")",
										"Buy the " + item.getName() + " for " + UtilText.formatAsMoney(sellPrice*inventoryNPC.getItemCount(item)) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellItems(inventoryNPC, Main.game.getPlayer(), item, inventoryNPC.getItemCount(item), sellPrice);
									}
								};
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant someone else's item!", null);
								
							} else if(index == 6) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to let you use [npc.her] items without buying them first."), null);
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (them)", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to use the items that [npc.she]'s trying to sell!"), null);
								
							} else {
								return null;
							}
					}
				}
			}
			return null;
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};
	
	public static final DialogueNodeOld WEAPON_INVENTORY = new DialogueNodeOld("Weapon", "", true) {
		
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().quickTrade && !Main.game.isInSex() && !Main.game.isInCombat()) {
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
			return "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ weapon.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+weapon.getDisplayName(true)+"</b></p>"
					+ weapon.getDescription()
					+ (inventoryNPC != null ? 
							inventoryNPC.willBuy(weapon) ? 
									"<p>" + inventoryNPC.getName("The") + " will buy it for " + UtilText.formatAsMoney(weapon.getPrice(inventoryNPC.getBuyModifier())) + ".</p>" 
							: inventoryNPC.getName("The") + " doesn't want to buy this."
						: "");
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasWeapon(weapon);
					
					if(index == 1) {
						if(owner.getLocationPlace().isItemsDisappear()) {
							if(areaFull) {
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
							if(areaFull) {
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
						if(owner.getWeaponCount(weapon) < 5) {
							return new Response("Drop (5)", "You don't have five " + weapon.getNamePlural() + " to give!", null);
						}
						if(owner.getLocationPlace().isItemsDisappear()) {
							if(areaFull) {
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
							if(areaFull) {
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
						if(owner.getLocationPlace().isItemsDisappear()) {
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
							if(areaFull) {
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
						
					} else if(index == 5) {
						if(weapon.getEnchantmentItemType()==null) {
							return new Response("Enchant", "This weapon cannot be enchanted!", null);
							
						} else if(Main.game.isDebugMode()) {
							return new Response("Enchant", "Enchant this weapon.", EnchantmentDialogue.ENCHANTMENT_MENU) {
								@Override
								public void effects() {
									EnchantmentDialogue.effects.clear();
									EnchantmentDialogue.resetEnchantmentVariables();
									EnchantmentDialogue.ingredient = weapon;
								}
							};
							
						} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
							if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_ENCHANTMENTS_LILAYA_HELP)) {
								return new Response("Enchant", "Enchant this weapon.", EnchantmentDialogue.ENCHANTMENT_MENU) {
									@Override
									public void effects() {
										EnchantmentDialogue.effects.clear();
										EnchantmentDialogue.resetEnchantmentVariables();
										EnchantmentDialogue.ingredient = weapon;
									}
								};
							}
						}
						
						return null;
						
					} else if(index == 6) {
							return new Response("Equip (self)", "Equip the " + weapon.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"
										+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
											?Main.game.getPlayer().equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
											:Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer()))
										+ "</p>");
								}
							};
							
					} else if (index==7) {
						if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_JINXED_CLOTHING, Quest.SIDE_JINXED_LILAYA_HELP)){
							return new Response("Remove jinx", "Proceed to the jinxed clothing choice menu.", REMOVE_JINX){
								@Override
								public void effects() {
									jinxRemovalFromFloor = false;
								}
							};
						}
					
						return null;
						
					} else if (index == 10) {
						return getQuickTradeResponse();
						
					} else if(index == 11) {
						return new Response("Equip (Ground)", "You can't make the ground equip your "+weapon.getName()+"!", null);
						
					} else {
						return null;
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
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant weapons while fighting someone!", null);
								
							} else if(index == 6) {
								return new Response("Equip (self)", "Equip the " + weapon.getName() + ".", Combat.ENEMY_ATTACK){
									@Override
									public void effects(){
										Combat.setPlayerTurnText("<p style='text-align:center;'>"
																	+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
																	?Main.game.getPlayer().equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
																	:Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer()))
																+ "</p>");
										Combat.attackEnemy();
										Combat.setPreviousAction(Attack.NONE);
										Main.game.restoreSavedContent();
									}
								};
									
							} else if(index == 7) {
								if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_JINXED_CLOTHING, Quest.SIDE_JINXED_LILAYA_HELP)){
									return new Response("Remove jinx", "You can't remove a jinx while fighting someone!", null);
								}
								return null;
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("Equip (opponent)", "You can't make your opponent equip a weapon!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:
							boolean inventoryFull = inventoryNPC.isInventoryFull() && !inventoryNPC.hasWeapon(weapon);
							
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Give (1)", UtilText.parse(inventoryNPC, "[npc.Name]'s inventory is already full!"), null);
								}
								return new Response("Give (1)", UtilText.parse(inventoryNPC, "Give [npc.name] " + weapon.getWeaponType().getDeterminer() + " " + weapon.getName() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(Main.game.getPlayer(), inventoryNPC, weapon, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("Give (5)", UtilText.parse(inventoryNPC, "[npc.Name]'s inventory is already full!"), null);
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
								if(inventoryFull) {
									return new Response("Give (All)", UtilText.parse(inventoryNPC, "[npc.Name]'s inventory is already full!"), null);
								}
								return new Response("Give (All)", UtilText.parse(inventoryNPC, "Give [npc.name] all of your " + weapon.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(Main.game.getPlayer(), inventoryNPC, weapon, Main.game.getPlayer().getWeaponCount(weapon));
									}
								};
								
							} else if(index == 5) {
								if(weapon.getEnchantmentItemType()==null) {
									return new Response("Enchant", "This weapon cannot be enchanted!", null);
									
								} else if(Main.game.isDebugMode()) {
									return new Response("Enchant", "Enchant this weapon.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public void effects() {
											EnchantmentDialogue.effects.clear();
											EnchantmentDialogue.resetEnchantmentVariables();
											EnchantmentDialogue.ingredient = weapon;
										}
									};
									
								} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_ENCHANTMENTS_LILAYA_HELP)) {
										return new Response("Enchant", "Enchant this weapon.", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public void effects() {
												EnchantmentDialogue.effects.clear();
												EnchantmentDialogue.resetEnchantmentVariables();
												EnchantmentDialogue.ingredient = weapon;
											}
										};
									}
								}
								
								return null;
								
							}  else if(index == 6) {
								return new Response("Equip (self)", "Equip the " + weapon.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"
												+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
														?Main.game.getPlayer().equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
														:Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer()))
												+ "</p>");
										}
									};
									
							} else if (index==7) {
								if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_JINXED_CLOTHING, Quest.SIDE_JINXED_LILAYA_HELP)){
									return new Response("Remove jinx", "Proceed to the jinxed clothing choice menu.", REMOVE_JINX){
										@Override
										public void effects() {
											jinxRemovalFromFloor = false;
										}
									};
								}
							
								return null;
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip ([npc.Name()])"), UtilText.parse(inventoryNPC, "Make [npc.name] equip the "+weapon.getName()+"!"), INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"
											+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
												?inventoryNPC.equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
												:inventoryNPC.equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer()))
											+ "</p>");
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
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant weapons while having sex with someone!", null);
								
							} else if(index == 6) {
								return new Response("Equip (self)", "You can't equip weapons while having sex with someone!", null);
								
							} else if(index == 7) {
								if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_JINXED_CLOTHING, Quest.SIDE_JINXED_LILAYA_HELP)){
									return new Response("Remove jinx", "You can't remove a jinx while having sex with someone!", null);
								}
								return null;
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip ([npc.Name()])"), "You can't equip weapons while having sex with someone!", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							if(index == 1) {
								if (inventoryNPC.willBuy(weapon)) {
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
									if (inventoryNPC.willBuy(weapon)) {
										int sellPrice = weapon.getPrice(inventoryNPC.getBuyModifier());
										return new Response("Sell (1) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "Sell five of your " + weapon.getNamePlural() + " for " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
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
								if (inventoryNPC.willBuy(weapon)) {
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
								
							} else if(index == 5) {
								if(weapon.getEnchantmentItemType()==null) {
									return new Response("Enchant", "This weapon cannot be enchanted!", null);
									
								} else if(Main.game.isDebugMode()) {
									return new Response("Enchant", "Enchant this weapon.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public void effects() {
											EnchantmentDialogue.effects.clear();
											EnchantmentDialogue.resetEnchantmentVariables();
											EnchantmentDialogue.ingredient = weapon;
										}
									};
									
								} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_ENCHANTMENTS_LILAYA_HELP)) {
										return new Response("Enchant", "Enchant this weapon.", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public void effects() {
												EnchantmentDialogue.effects.clear();
												EnchantmentDialogue.resetEnchantmentVariables();
												EnchantmentDialogue.ingredient = weapon;
											}
										};
									}
								}
								
								return null;
								
							} else if(index == 6) {
								return new Response("Equip (self)", "Equip the " + weapon.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"
												+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
													?Main.game.getPlayer().equipMainWeaponFromInventory(weapon, Main.game.getPlayer())
													:Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, Main.game.getPlayer()))
												+ "</p>");
										}
									};
									
							} else if (index==7) {
								if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_JINXED_CLOTHING, Quest.SIDE_JINXED_LILAYA_HELP)){
									return new Response("Remove jinx", "Proceed to the jinxed clothing choice menu.", REMOVE_JINX){
										@Override
										public void effects() {
											jinxRemovalFromFloor = false;
										}
									};
								}
							
								return null;
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip ([npc.Name()])"), UtilText.parse(inventoryNPC, "[npc.Name] doesn't want to use your weapons."), null);
								
							} else {
								return null;
							}
					}
				}
				
			// ****************************** ITEM DOES NOT BELONG TO PLAYER ******************************
				
			} else {
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon);
					
					if(index == 1) {
						if(inventoryFull) {
							return new Response("Take (1)", "Your inventory is already full!", null);
						}
						return new Response("Take (1)", "Take one " + weapon.getWeaponType().getDeterminer() + " " + weapon.getName() + " from the ground.", INVENTORY_MENU){
							@Override
							public void effects(){
								pickUpWeapons(Main.game.getPlayer(), weapon, 1);
							}
						};
						
					} else if(index == 2) {
						if(inventoryFull) {
							return new Response("Take (5)", "Your inventory is already full!", null);
						}
						if(Main.game.getCurrentCell().getInventory().getWeaponCount(weapon) >= 5) {
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
								pickUpWeapons(Main.game.getPlayer(), weapon, Main.game.getCurrentCell().getInventory().getWeaponCount(weapon));
							}
						};
						
					} else if(index == 5) {
						return new Response("Enchant", "You can't enchant weapons on the ground!", null);
						
					} else if(index == 6) {
						return new Response("Equip (self)", "Equip the " + weapon.getName() + ".", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"
									+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
										?Main.game.getPlayer().equipMainWeaponFromFloor(weapon)
										:Main.game.getPlayer().equipOffhandWeaponFromFloor(weapon))
									+ "</p>");
							}
						};
							
					} else if (index==7) {
						if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_JINXED_CLOTHING, Quest.SIDE_JINXED_LILAYA_HELP)){
							return new Response("Remove jinx", "Proceed to the jinxed clothing choice menu.", REMOVE_JINX){
								@Override
								public void effects() {
									jinxRemovalFromFloor = true;
								}
							};
						}
					
						return null;
						
					} else if (index == 10) {
						return getQuickTradeResponse();
						
					} else if(index == 11) {
						return new Response("Equip (Ground)", "There's nobody to use the "+weapon.getName()+" on!", null);
						
					} else {
						return null;
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
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant someone else's weapons, especially not while fighting them!", null);
								
							} else if(index == 6) {
								return new Response("Equip (self)", "You can't use someone else's weapons while fighting them!", null);
								
							} else if(index == 7) {
								if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_JINXED_CLOTHING, Quest.SIDE_JINXED_LILAYA_HELP)){
									return new Response("Remove jinx", "You can't remove a jinx while fighting someone!", null);
								}
								return null;
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("Equip (opponent)", "You can't use make someone use a weapon while fighting them!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon);
						
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Take (1)", "Your inventory is already full!", null);
								}
								return new Response("Take (1)", UtilText.parse(inventoryNPC, "Take " + weapon.getWeaponType().getDeterminer() + " " + weapon.getName() + " from [npc.name]."), INVENTORY_MENU){
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
									return new Response("Take (5)", UtilText.parse(inventoryNPC, "Take five of  [npc.name]'s " + weapon.getNamePlural() + "."), INVENTORY_MENU){
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
								return new Response("Take (All)", UtilText.parse(inventoryNPC, "Take five of  [npc.name]'s " + weapon.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferWeapons(inventoryNPC, Main.game.getPlayer(), weapon, inventoryNPC.getWeaponCount(weapon));
									}
								};
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant weapons owned by someone else!", null);
								
							} else if(index == 6) {
								return new Response("Equip (self)", "Equip the " + weapon.getName() + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"
											+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
												?Main.game.getPlayer().equipMainWeaponFromInventory(weapon, inventoryNPC)
												:Main.game.getPlayer().equipOffhandWeaponFromInventory(weapon, inventoryNPC))
											+ "</p>");
									}
								};
								
							} else if(index == 7) {
								if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_JINXED_CLOTHING, Quest.SIDE_JINXED_LILAYA_HELP)){
									return new Response("Remove jinx", "You can't remove a jinx while having sex with someone!", null);
								}
								return null;
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip ([npc.Name()])"), UtilText.parse(inventoryNPC, "Get [npc.name] to equip the " + weapon.getName() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"
											+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
												?inventoryNPC.equipMainWeaponFromInventory(weapon, inventoryNPC)
												:inventoryNPC.equipOffhandWeaponFromInventory(weapon, inventoryNPC))
											+ "</p>");
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
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant someone else's weapons, especially not while having sex with them!", null);
								
							} else if(index == 6) {
								return new Response("Equip (self)", "You can't use someone else's weapons while having sex with them!", null);
								
							} else if(index == 7) {
								if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_JINXED_CLOTHING, Quest.SIDE_JINXED_LILAYA_HELP)){
									return new Response("Remove jinx", "You can't remove a jinx while having sex with someone!", null);
								}
								return null;
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("Equip (opponent)", "You can't use make someone use a weapon while having sex with them!", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon);
							
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
								if(buyback) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "Cannot use this option in the buyback menu.", null);
								}
								if(inventoryNPC.getWeaponCount(weapon) < 5) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", UtilText.parse(inventoryNPC, "[npc.Name] doesn't have five "+weapon.getNamePlural()+"."), null);
								}
								if(inventoryFull) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
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
								if(buyback) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "Cannot use this option in the buyback menu.", null);
								}
								if(inventoryFull) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice*inventoryNPC.getWeaponCount(weapon), "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice*inventoryNPC.getWeaponCount(weapon), "span")+")", "You can't afford to buy this!", null);
								}
								return new Response("Buy (All) (" + UtilText.formatAsMoney(sellPrice*inventoryNPC.getWeaponCount(weapon), "span") + ")",
										"Buy the " + weapon.getName() + " for " + UtilText.formatAsMoney(sellPrice*inventoryNPC.getWeaponCount(weapon)) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellWeapons(inventoryNPC, Main.game.getPlayer(), weapon, inventoryNPC.getWeaponCount(weapon), sellPrice);
									}
								};
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant someone else's weapon!", null);
								
							} else if(index == 6) {
								return new Response("Equip (self)", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to let you equip [npc.her] weapons without buying them first."), null);
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip ([npc.Name()])"), UtilText.parse(inventoryNPC, "[npc.Name] isn't going to equip the weapons that [npc.she]'s trying to sell!"), null);
								
							} else {
								return null;
							}
					}
				}
			}
			return null;
		}
		
		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};
	
	public static final DialogueNodeOld CLOTHING_INVENTORY = new DialogueNodeOld("Clothing", "", true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().quickTrade && !Main.game.isInSex() && !Main.game.isInCombat()) {
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
			return "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ clothing.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+clothing.getDisplayName(true)+"</b></p>"
					+ clothing.getDescription() + clothing.clothingExtraInformation(null)
					+ (inventoryNPC != null ? 
							inventoryNPC.willBuy(clothing) ? 
									"<p>" + inventoryNPC.getName("The") + " will buy it for " + UtilText.formatAsMoney(clothing.getPrice(inventoryNPC.getBuyModifier())) + ".</p>" 
							: inventoryNPC.getName("The") + " doesn't want to buy this."
						: "");
		}
		
		@Override
		public Response getResponse(int index) {
			
			if (index == 0) {
				return getCloseInventoryResponse();
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ******************************
			if(owner != null && owner.isPlayer()) {
				
				// ****************************** Interacting with the ground ******************************
				if(inventoryNPC == null) {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasClothing(clothing);
					
					if(index == 1) {
						if(owner.getLocationPlace().isItemsDisappear()) {
							if(areaFull) {
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
							if(areaFull) {
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
						if(owner.getClothingCount(clothing) < 5) {
							return new Response("Drop (5)", "You don't have five " + clothing.getNamePlural() + " to give!", null);
						}
						if(owner.getLocationPlace().isItemsDisappear()) {
							if(areaFull) {
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
							if(areaFull) {
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
							if(areaFull) {
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
							if(areaFull) {
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
						if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)) {
							boolean hasFullInventory = Main.game.getPlayer().isInventoryFull();
							boolean isDyeingStackItem = Main.game.getPlayer().getMapOfDuplicateClothing().get(clothing) > 1;
							boolean canDye = !(isDyeingStackItem && hasFullInventory);
							if (canDye) {
								return new Response("Dye", "Use a dye-brush to dye this item of clothing.", DYE_CLOTHING);
							} else {
								return new Response("Dye", "Your inventory is full, so you can't dye this item of clothing.", null);
							}
						} else {
							return new Response("Dye", "You'll need to find a dye-brush if you want to dye your clothes.", null);
						}
						
					} else if(index == 5) {
						if(clothing.getEnchantmentItemType()==null) {
							return new Response("Enchant", "This clothing cannot be enchanted!", null);
							
						} else if(Main.game.isDebugMode()) {
							return new Response("Enchant", "Enchant this clothing.", EnchantmentDialogue.ENCHANTMENT_MENU) {
								@Override
								public void effects() {
									EnchantmentDialogue.effects.clear();
									EnchantmentDialogue.resetEnchantmentVariables();
									EnchantmentDialogue.ingredient = clothing;
								}
							};
							
						} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
							if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_ENCHANTMENTS_LILAYA_HELP)) {
								return new Response("Enchant", "Enchant this clothing.", EnchantmentDialogue.ENCHANTMENT_MENU) {
									@Override
									public void effects() {
										EnchantmentDialogue.effects.clear();
										EnchantmentDialogue.resetEnchantmentVariables();
										EnchantmentDialogue.ingredient = clothing;
									}
								};
							}
						}
						
						return null;
						
					} else if(index == 6) {
						return new Response("Equip (self)", "Equip the " + clothing.getName() + ".", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().equipClothingFromInventory(clothing, true, Main.game.getPlayer(), Main.game.getPlayer()) + "</p>");
								populateJinxedClothingList();
							}
						};
							
					} else if (index == 10) {
						return getQuickTradeResponse();
						
					} else if(index == 11) {
						return new Response("Equip (Ground)", "You can't make the ground equip your "+clothing.getName()+"!", null);
						
					} else {
						return null;
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
								return new Response("Enchant", "You can't enchant clothing while fighting someone!", null);
								
							} else if(index == 6) {
								return new Response("Equip (self)", "Equip the " + clothing.getName() + ".", Combat.ENEMY_ATTACK){
									@Override
									public void effects(){
										Combat.setPlayerTurnText(
												"<p style='text-align:center;'>"
														+ Main.game.getPlayer().equipClothingFromInventory(clothing, true, Main.game.getPlayer(), Main.game.getPlayer())
												+ "</p>");
										populateJinxedClothingList();
										Combat.attackEnemy();
										Combat.setPreviousAction(Attack.NONE);
										Main.game.restoreSavedContent();
									}
								};
									
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("Equip (opponent)", "You can't make your opponent equip clothing while fighting them!", null);
								
							} else {
								return null;
							}
							
						case FULL_MANAGEMENT:
							boolean inventoryFull = inventoryNPC.isInventoryFull() && !inventoryNPC.hasClothing(clothing);
							
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Give (1)", UtilText.parse(inventoryNPC, "[npc.Name]'s inventory is already full!"), null);
								}
								return new Response("Give (1)", UtilText.parse(inventoryNPC, "Give [npc.name] " + clothing.getClothingType().getDeterminer() + " " + clothing.getName() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(Main.game.getPlayer(), inventoryNPC, clothing, 1);
									}
								};
								
							} else if(index == 2) {
								if(inventoryFull) {
									return new Response("Give (5)", UtilText.parse(inventoryNPC, "[npc.Name]'s inventory is already full!"), null);
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
								if(inventoryFull) {
									return new Response("Give (All)", UtilText.parse(inventoryNPC, "[npc.Name]'s inventory is already full!"), null);
								}
								return new Response("Give (All)", UtilText.parse(inventoryNPC, "Give [npc.name] all of your " + clothing.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(Main.game.getPlayer(), inventoryNPC, clothing, Main.game.getPlayer().getClothingCount(clothing));
									}
								};
								
							} else if (index==4) {
								if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull();
									boolean isDyeingStackItem = Main.game.getPlayer().getMapOfDuplicateClothing().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Dye", "Use a dye-brush to dye this item of clothing.", DYE_CLOTHING);
									} else {
										return new Response("Dye", "Your inventory is full, so you can't dye this item of clothing.", null);
									}
								} else {
									return new Response("Dye", "You'll need to find a dye-brush if you want to dye your clothes.", null);
								}
								
							} else if(index == 5) {
								if(clothing.getEnchantmentItemType()==null) {
									return new Response("Enchant", "This clothing cannot be enchanted!", null);
									
								} else if(Main.game.isDebugMode()) {
									return new Response("Enchant", "Enchant this clothing.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public void effects() {
											EnchantmentDialogue.effects.clear();
											EnchantmentDialogue.resetEnchantmentVariables();
											EnchantmentDialogue.ingredient = clothing;
										}
									};
									
								} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_ENCHANTMENTS_LILAYA_HELP)) {
										return new Response("Enchant", "Enchant this clothing.", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public void effects() {
												EnchantmentDialogue.effects.clear();
												EnchantmentDialogue.resetEnchantmentVariables();
												EnchantmentDialogue.ingredient = clothing;
											}
										};
									}
								}
								
								return null;
								
							}  else if(index == 6) {
								return new Response("Equip (self)", "Equip the " + clothing.getName() + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().equipClothingFromInventory(clothing, true, Main.game.getPlayer(), Main.game.getPlayer()) + "</p>");
										populateJinxedClothingList();
									}
								};
									
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip ([npc.Name()])"), UtilText.parse(inventoryNPC, "Make [npc.name] equip the "+clothing.getName()+"!"), INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.equipClothingFromInventory(clothing, true, Main.game.getPlayer(), Main.game.getPlayer()) + "</p>");
										populateJinxedClothingList();
									}
								};
							
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
								return new Response("Enchant", "You can't enchant clothing while having sex with someone!", null);
								
							} else if(index == 6) {
								return new Response("Equip (self)", "You can't equip clothing while having sex with someone!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip ([npc.Name()])"), "You can't equip clothing while having sex with someone!", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							if(index == 1) {
								if (inventoryNPC.willBuy(clothing)) {
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
								if(Main.game.getPlayer().getClothingCount(clothing) >= 5) {
									if (inventoryNPC.willBuy(clothing)) {
										int sellPrice = clothing.getPrice(inventoryNPC.getBuyModifier());
										return new Response("Sell (1) (" + UtilText.formatAsMoney(sellPrice*5, "span") + ")", "Sell five of your " + clothing.getNamePlural() + " for " + UtilText.formatAsMoney(sellPrice*5) + ".", INVENTORY_MENU){
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
								if (inventoryNPC.willBuy(clothing)) {
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
								if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)) {
									boolean hasFullInventory = Main.game.getPlayer().isInventoryFull();
									boolean isDyeingStackItem = Main.game.getPlayer().getMapOfDuplicateClothing().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Dye", "Use a dye-brush to dye this item of clothing.", DYE_CLOTHING);
									} else {
										return new Response("Dye", "Your inventory is full, so you can't dye this item of clothing.", null);
									}
								} else {
									return new Response("Dye", "You'll need to find a dye-brush if you want to dye your clothes.", null);
								}
								
							} else if(index == 5) {
								if(clothing.getEnchantmentItemType()==null) {
									return new Response("Enchant", "This clothing cannot be enchanted!", null);
									
								} else if(Main.game.isDebugMode()) {
									return new Response("Enchant", "Enchant this clothing.", EnchantmentDialogue.ENCHANTMENT_MENU) {
										@Override
										public void effects() {
											EnchantmentDialogue.effects.clear();
											EnchantmentDialogue.resetEnchantmentVariables();
											EnchantmentDialogue.ingredient = clothing;
										}
									};
									
								} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
									if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_ENCHANTMENT_DISCOVERY, Quest.SIDE_ENCHANTMENTS_LILAYA_HELP)) {
										return new Response("Enchant", "Enchant this clothing.", EnchantmentDialogue.ENCHANTMENT_MENU) {
											@Override
											public void effects() {
												EnchantmentDialogue.effects.clear();
												EnchantmentDialogue.resetEnchantmentVariables();
												EnchantmentDialogue.ingredient = clothing;
											}
										};
									}
								}
								
								return null;
								
							} else if(index == 6) {
								return new Response("Equip (self)", "Equip the " + clothing.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().equipClothingFromInventory(clothing, true, Main.game.getPlayer(), Main.game.getPlayer()) + "</p>");
											populateJinxedClothingList();
										}
									};
									
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip ([npc.Name()])"), UtilText.parse(inventoryNPC, "[npc.Name] doesn't want to wear your clothing."), null);
								
							} else if (index == 14 && !clothing.isEnchantmentKnown()) {
								if(!inventoryNPC.willBuy(clothing)) {
									return new Response("Identify", inventoryNPC.getName("The") + " can't identify clothing!", null);
									
								} else if(Main.game.getPlayer().getMoney() < IDENTIFICATION_PRICE){
									// don't format as money because we don't want to highlight non-selectable choices
									return new Response("Identify (" + UtilText.formatAsMoneyUncoloured(IDENTIFICATION_PRICE, "span") + ")", "You don't have enough money!", null);
									
								}else {
									return new Response("Identify (" + UtilText.formatAsMoney(IDENTIFICATION_PRICE, "span") + ")",
												"Have the " + clothing.getName() + " identified for " + UtilText.formatAsMoney(IDENTIFICATION_PRICE, "span") + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getPlayer().removeClothing(clothing);
											Main.game.getTextStartStringBuilder().append(
													"<p style='text-align:center;'>" + "You hand over " + UtilText.formatAsMoney(IDENTIFICATION_PRICE) + " to "
															+inventoryNPC.getName("the")+", who promptly identifies your "+clothing.getName()+"."
													+ "</p>"
													+clothing.setEnchantmentKnown(true));
											
											Main.game.getPlayer().addClothing(clothing, false);
											Main.game.getPlayer().incrementMoney(-IDENTIFICATION_PRICE);
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
					boolean inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing);
					
					if(index == 1) {
						if(inventoryFull) {
							return new Response("Take (1)", "Your inventory is already full!", null);
						}
						return new Response("Take (1)", "Take one " + clothing.getClothingType().getDeterminer() + " " + clothing.getName() + " from the ground.", INVENTORY_MENU){
							@Override
							public void effects(){
								pickUpClothing(Main.game.getPlayer(), clothing, 1);
							}
						};
						
					} else if(index == 2) {
						if(inventoryFull) {
							return new Response("Take (5)", "Your inventory is already full!", null);
						}
						if(Main.game.getCurrentCell().getInventory().getClothingCount(clothing) >= 5) {
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
								pickUpClothing(Main.game.getPlayer(), clothing, Main.game.getCurrentCell().getInventory().getClothingCount(clothing));
							}
						};
						
					} else if (index==4) {
						if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)) {
							boolean hasFullInventory = Main.game.getCurrentCell().getInventory().isInventoryFull();
							boolean isDyeingStackItem = Main.game.getCurrentCell().getInventory().getMapOfDuplicateClothing().get(clothing) > 1;
							boolean canDye = !(isDyeingStackItem && hasFullInventory);
							if (canDye) {
								return new Response("Dye", "Use a dye-brush to dye this item of clothing.", DYE_CLOTHING);
							} else {
								return new Response("Dye", "Your inventory is full, so you can't dye this item of clothing.", null);
							}
						} else {
							return new Response("Dye", "You'll need to find a dye-brush if you want to dye your clothes.", null);
						}
						
					} else if(index == 5) {
						return new Response("Enchant", "You can't enchant clothing on the ground!", null);
						
					} else if(index == 6) {
						return new Response("Equip (self)", "Equip the " + clothing.getName() + ".", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().equipClothingFromGround(clothing, true, Main.game.getPlayer()) + "</p>");
								populateJinxedClothingList();
							}
						};
							
					} else if (index == 10) {
						return getQuickTradeResponse();
						
					} else if(index == 11) {
						return new Response("Equip (Ground)", "There's nobody to use the "+clothing.getName()+" on!", null);
						
					} else {
						return null;
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
							
						case FULL_MANAGEMENT:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing);
						
							if(index == 1) {
								if(inventoryFull) {
									return new Response("Take (1)", "Your inventory is already full!", null);
								}
								return new Response("Take (1)", UtilText.parse(inventoryNPC, "Take " + clothing.getClothingType().getDeterminer() + " " + clothing.getName() + " from [npc.name]."), INVENTORY_MENU){
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
									return new Response("Take (5)", UtilText.parse(inventoryNPC, "Take five of  [npc.name]'s " + clothing.getNamePlural() + "."), INVENTORY_MENU){
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
								return new Response("Take (All)", UtilText.parse(inventoryNPC, "Take five of  [npc.name]'s " + clothing.getNamePlural() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										transferClothing(inventoryNPC, Main.game.getPlayer(), clothing, inventoryNPC.getClothingCount(clothing));
									}
								};
								
							} else if (index==4) {
								if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)) {
									boolean hasFullInventory = inventoryNPC.isInventoryFull();
									boolean isDyeingStackItem = inventoryNPC.getMapOfDuplicateClothing().get(clothing) > 1;
									boolean canDye = !(isDyeingStackItem && hasFullInventory);
									if (canDye) {
										return new Response("Dye", "Use a dye-brush to dye this item of clothing.", DYE_CLOTHING);
									} else {
										return new Response("Dye", UtilText.parse(inventoryNPC, "[npc.Name]'s inventory is full, so you can't dye this item of clothing."), null);
									}
								} else {
									return new Response("Dye", UtilText.parse(inventoryNPC, "You'll need to find another dye-brush if you want to dye [npc.name]'s clothes."), null);
								}
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant clothing owned by someone else!", null);
								
							} else if(index == 6) {
								return new Response("Equip (self)", "Equip the " + clothing.getName() + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().equipClothingFromInventory(clothing, true, Main.game.getPlayer(), inventoryNPC) + "</p>");
										populateJinxedClothingList();
									}
								};
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip ([npc.Name()])"), UtilText.parse(inventoryNPC, "Get [npc.name] to equip the " + clothing.getName() + "."), INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.equipClothingFromInventory(clothing, true, Main.game.getPlayer(), inventoryNPC) + "</p>");
										populateJinxedClothingList();
									}
								};
								
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
								return new Response("Enchant", "You can't enchant someone else's clothing, especially not while having sex with them!", null);
								
							} else if(index == 6) {
								return new Response("Equip (self)", "You can't equip someone else's clothing while having sex with them!", null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response("Equip (opponent)", "You can't use make someone wear clothing while having sex with them!", null);
								
							} else {
								return null;
							}
							
						case TRADING:
							inventoryFull = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing);
							
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
								if(buyback) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "Cannot use this option in the buyback menu.", null);
								}
								if(inventoryNPC.getClothingCount(clothing) < 5) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", UtilText.parse(inventoryNPC, "[npc.Name] doesn't have five "+clothing.getNamePlural()+"."), null);
								}
								if(inventoryFull) {
									return new Response("Buy (5) ("+UtilText.formatAsMoneyUncoloured(sellPrice*5, "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
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
								if(buyback) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice, "span")+")", "Cannot use this option in the buyback menu.", null);
								}
								if(inventoryFull) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice*inventoryNPC.getClothingCount(clothing), "span")+")", "Your inventory is already full!", null);
								}
								if(Main.game.getPlayer().getMoney() < sellPrice) {
									return new Response("Buy (All) ("+UtilText.formatAsMoneyUncoloured(sellPrice*inventoryNPC.getClothingCount(clothing), "span")+")", "You can't afford to buy this!", null);
								}
								return new Response("Buy (All) (" + UtilText.formatAsMoney(sellPrice*inventoryNPC.getClothingCount(clothing), "span") + ")",
										"Buy the " + clothing.getName() + " for " + UtilText.formatAsMoney(sellPrice*inventoryNPC.getClothingCount(clothing)) + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										sellClothing(inventoryNPC, Main.game.getPlayer(), clothing, inventoryNPC.getClothingCount(clothing), sellPrice);
									}
								};
								
							} else if(index == 4) {
								return new Response("Dye", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to let you dye the clothing that [npc.she]'s trying to sell!"), null);
								
							} else if(index == 5) {
								return new Response("Enchant", "You can't enchant someone else's clothing!", null);
								
							} else if(index == 6) {
								return new Response("Equip (self)", UtilText.parse(inventoryNPC, "[npc.Name] isn't going to let you use [npc.her] clothing without buying them first."), null);
								
							} else if (index == 9) {
								return getBuybackResponse();
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else if(index == 11) {
								return new Response(UtilText.parse(inventoryNPC, "Equip ([npc.Name()])"), UtilText.parse(inventoryNPC, "[npc.Name] isn't going to use the clothing that [npc.she]'s trying to sell!"), null);
								
							} else {
								return null;
							}
					}
				}
			}
			return null;
			
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};
	
	public static final DialogueNodeOld WEAPON_EQUIPPED = new DialogueNodeOld("Weapon equipped", "", true) {
		
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().quickTrade && !Main.game.isInSex() && !Main.game.isInCombat()) {
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
			return "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ weapon.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+weapon.getDisplayName(true)+"</b></p>"
					+ weapon.getDescription();
		}
		
		@Override
		public Response getResponse(int index) {
			
			if (index == 0) {
				return getCloseInventoryResponse();
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ****************************** TODO
			if(owner != null && owner.isPlayer()) {
				switch(interactionType) {
					case COMBAT:
						if(index == 1) {
							return new Response("Unequip", "Unequip the " + weapon.getName() + ".", Combat.ENEMY_ATTACK){
								@Override
								public void effects(){
									Combat.setPlayerTurnText(
											"<p style='text-align:center;'>"
												+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
													?Main.game.getPlayer().unequipMainWeapon(false)
													:Main.game.getPlayer().unequipOffhandWeapon(false))
											+ "</p>");
									Combat.attackEnemy();
									Combat.setPreviousAction(Attack.NONE);
									Main.game.restoreSavedContent();
								}
							};
							
						} else if (index == 2) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(areaFull) {
									return new Response("Drop", "This area is full, so you can't drop your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Drop", "Drop your " + weapon.getName() + ".", Combat.ENEMY_ATTACK){
										@Override
										public void effects(){
											Combat.setPlayerTurnText(
													"<p style='text-align:center;'>"
														+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
															?Main.game.getPlayer().unequipMainWeapon(true)
															:Main.game.getPlayer().unequipOffhandWeapon(true))
													+ "</p>");
											Combat.attackEnemy();
											Combat.setPreviousAction(Attack.NONE);
											Main.game.restoreSavedContent();
										}
									};
								}
								
							} else {
								if(areaFull) {
									return new Response("Store", "This area is full, so you can't store your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Store", "Store your " + weapon.getName() + " in this area.", Combat.ENEMY_ATTACK){
										@Override
										public void effects(){
											Combat.setPlayerTurnText(
													"<p style='text-align:center;'>"
														+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
															?Main.game.getPlayer().unequipMainWeapon(true)
															:Main.game.getPlayer().unequipOffhandWeapon(true))
													+ "</p>");
											Combat.attackEnemy();
											Combat.setPreviousAction(Attack.NONE);
											Main.game.restoreSavedContent();
										}
									};
								}
							}
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant equipped weapons!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case FULL_MANAGEMENT: case TRADING:
						if(index == 1) {
							return new Response("Unequip", "Unequip the " + weapon.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextStartStringBuilder().append(
											"<p style='text-align:center;'>"
												+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
													?Main.game.getPlayer().unequipMainWeapon(false)
													:Main.game.getPlayer().unequipOffhandWeapon(false))
											+ "</p>");
								}
							};
							
						} else if (index == 2) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(areaFull) {
									return new Response("Drop", "This area is full, so you can't drop your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Drop", "Drop your " + weapon.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
															?Main.game.getPlayer().unequipMainWeapon(true)
															:Main.game.getPlayer().unequipOffhandWeapon(true))
													+ "</p>");
										}
									};
								}
								
							} else {
								if(areaFull) {
									return new Response("Store", "This area is full, so you can't store your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Store", "Store your " + weapon.getName() + " in this area.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
															?Main.game.getPlayer().unequipMainWeapon(true)
															:Main.game.getPlayer().unequipOffhandWeapon(true))
													+ "</p>");
										}
									};
								}
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
									Sex.setUnequipClothingText("<p style='text-align:center;'>"
											+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
											?Main.game.getPlayer().unequipMainWeapon(false)
											:Main.game.getPlayer().unequipOffhandWeapon(false))
									+ "</p>");
									Main.game.restoreSavedContent();
									Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Sex.setSexStarted(true);
								}
							};
							
						} else if (index == 2) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(areaFull) {
									return new Response("Drop", "This area is full, so you can't drop your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Drop", "Drop your " + weapon.getName() + ".", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUnequipClothingText("<p style='text-align:center;'>"
													+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
													?Main.game.getPlayer().unequipMainWeapon(true)
													:Main.game.getPlayer().unequipOffhandWeapon(true))
											+ "</p>");
											Main.game.restoreSavedContent();
											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Sex.setSexStarted(true);
										}
									};
								}
								
							} else {
								if(areaFull) {
									return new Response("Store", "This area is full, so you can't store your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Store", "Store your " + weapon.getName() + " in this area.", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUnequipClothingText("<p style='text-align:center;'>"
													+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
													?Main.game.getPlayer().unequipMainWeapon(true)
													:Main.game.getPlayer().unequipOffhandWeapon(true))
											+ "</p>");
											Main.game.restoreSavedContent();
											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Sex.setSexStarted(true);
										}
									};
								}
							}
							
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
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant someone else's equipped weapon, especially not while fighting them!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case FULL_MANAGEMENT:
						if(index == 1) {
							return new Response("Unequip", "Unequip the " + weapon.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextStartStringBuilder().append(
											"<p style='text-align:center;'>"
												+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
													?inventoryNPC.unequipMainWeapon(false)
													:inventoryNPC.unequipOffhandWeapon(false))
											+ "</p>");
								}
							};
							
						} else if (index == 2) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(areaFull) {
									return new Response("Drop", "This area is full, so you can't drop your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Drop", "Drop your " + weapon.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
															?inventoryNPC.unequipMainWeapon(true)
															:inventoryNPC.unequipOffhandWeapon(true))
													+ "</p>");
										}
									};
								}
								
							} else {
								if(areaFull) {
									return new Response("Store", "This area is full, so you can't store your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Store", "Store your " + weapon.getName() + " in this area.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append(
													"<p style='text-align:center;'>"
														+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
															?inventoryNPC.unequipMainWeapon(true)
															:inventoryNPC.unequipOffhandWeapon(true))
													+ "</p>");
										}
									};
								}
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
									Sex.setUnequipClothingText("<p style='text-align:center;'>"
											+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
											?inventoryNPC.unequipMainWeapon(false)
											:inventoryNPC.unequipOffhandWeapon(false))
									+ "</p>");
									Main.game.restoreSavedContent();
									Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Sex.setSexStarted(true);
								}
							};
							
						} else if (index == 2) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasWeapon(weapon);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(areaFull) {
									return new Response("Drop", "This area is full, so you can't drop your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Drop", "Drop your " + weapon.getName() + ".", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUnequipClothingText("<p style='text-align:center;'>"
													+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
													?inventoryNPC.unequipMainWeapon(true)
													:inventoryNPC.unequipOffhandWeapon(true))
											+ "</p>");
											Main.game.restoreSavedContent();
											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Sex.setSexStarted(true);
										}
									};
								}
								
							} else {
								if(areaFull) {
									return new Response("Store", "This area is full, so you can't store your " + weapon.getName() + " here!", null);
								} else {
									return new Response("Store", "Store your " + weapon.getName() + " in this area.", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											Sex.setUnequipClothingText("<p style='text-align:center;'>"
													+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
													?inventoryNPC.unequipMainWeapon(true)
													:inventoryNPC.unequipOffhandWeapon(true))
											+ "</p>");
											Main.game.restoreSavedContent();
											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Sex.setSexStarted(true);
										}
									};
								}
							}
							
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
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};
	public static final DialogueNodeOld CLOTHING_EQUIPPED = new DialogueNodeOld("Clothing equipped", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			if (Main.game.getDialogueFlags().quickTrade && !Main.game.isInSex() && !Main.game.isInCombat()) {
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
			return "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ clothing.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+clothing.getDisplayName(true)+"</b></p>"
					+ clothing.getDescription()
					+ clothing.clothingExtraInformation((Main.game.isInSex()?owner:Main.game.getPlayer()))
					+ (Main.game.isInSex()||Main.game.isInCombat()?clothing.getDisplacementBlockingDescriptions(owner):"");
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			}
			
			// ****************************** ITEM BELONGS TO THE PLAYER ****************************** TODO
			if(owner != null && owner.isPlayer()) {
				switch(interactionType) {
					case COMBAT:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(areaFull) {
									return new Response("Drop", "This area is full, so you can't drop your " + clothing.getName() + " here!", null);
								} else {
									return new Response("Drop", "Drop your " + clothing.getName() + ".", Combat.ENEMY_ATTACK){
										@Override
										public void effects(){
											owner.unequipClothingOntoFloor(clothing, true, Main.game.getPlayer());
											Combat.setPlayerTurnText(owner.getUnequipDescription());
											Combat.attackEnemy();
											Combat.setPreviousAction(Attack.NONE);
											Main.game.restoreSavedContent();
										}
									};
								}
								
							} else {
								if(areaFull) {
									return new Response("Store", "This area is full, so you can't store your " + clothing.getName() + " here!", null);
								} else {
									return new Response("Store", "Store your " + clothing.getName() + " in this area.", Combat.ENEMY_ATTACK){
										@Override
										public void effects(){
											owner.unequipClothingOntoFloor(clothing, true, Main.game.getPlayer());
											Combat.setPlayerTurnText(owner.getUnequipDescription());
											Combat.attackEnemy();
											Combat.setPreviousAction(Attack.NONE);
											Main.game.restoreSavedContent();
										}
									};
								}
							}
							
						} else if (index==4) {
							return new Response("Dye", "You can't dye your clothes in combat!", null);
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant equipped clothing!", null);
							
						} else if(index == 6) {
							if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
								return new Response("Unequip", "Unequip the " + clothing.getName() + ".", Sex.SEX_DIALOGUE){
									@Override
									public void effects(){
										owner.unequipClothingIntoInventory(clothing, true, Main.game.getPlayer());
										Sex.setUnequipClothingText(owner.getUnequipDescription());
										Main.game.restoreSavedContent();
										Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
										Sex.setSexStarted(true);
									}
								};
							} else {
								return new Response("Unequip", "You can't unequip the " + clothing.getName() + ", as other clothing is blocking you from doing so!", null);
							}
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case FULL_MANAGEMENT: case TRADING:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(areaFull) {
									return new Response("Drop", "This area is full, so you can't drop your " + clothing.getName() + " here!", null);
								} else {
									return new Response("Drop", "Drop your " + clothing.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().unequipClothingOntoFloor(clothing, true, Main.game.getPlayer()) + "</p>");
											populateJinxedClothingList();
										}
									};
								}
								
							} else {
								if(areaFull) {
									return new Response("Store", "This area is full, so you can't store your " + clothing.getName() + " here!", null);
								} else {
									return new Response("Store", "Store your " + clothing.getName() + " in this area.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().unequipClothingOntoFloor(clothing, true, Main.game.getPlayer()) + "</p>");
											populateJinxedClothingList();
										}
									};
								}
							}
							
						} else if (index==4) {
							if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)) {
								return new Response("Dye", "Use a dye-brush to dye this item of clothing.", DYE_CLOTHING);
							} else {
								return new Response("Dye", "You need a dye-brush in order to dye this item of clothing.", null);
							}
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant equipped clothing!", null);
							
						} else if(index == 6) {
							return new Response("Unequip", "Unequip the " + clothing.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().unequipClothingIntoInventory(clothing, true, Main.game.getPlayer()) + "</p>");
									populateJinxedClothingList();
								}
							};
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case SEX:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(areaFull) {
									return new Response("Drop", "This area is full, so you can't drop your " + clothing.getName() + " here!", null);
								} else {
									return new Response("Drop", "Drop your " + clothing.getName() + ".", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											owner.unequipClothingOntoFloor(clothing, true, Main.game.getPlayer());
											Sex.setUnequipClothingText(owner.getUnequipDescription());
											Main.game.restoreSavedContent();
											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Sex.setSexStarted(true);
										}
									};
								}
								
							} else {
								if(areaFull) {
									return new Response("Store", "This area is full, so you can't store your " + clothing.getName() + " here!", null);
								} else {
									return new Response("Store", "Store your " + clothing.getName() + " in this area.", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											owner.unequipClothingOntoFloor(clothing, true, Main.game.getPlayer());
											Sex.setUnequipClothingText(owner.getUnequipDescription());
											Main.game.restoreSavedContent();
											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Sex.setSexStarted(true);
										}
									};
								}
							}
							
						} else if (index==4) {
							return new Response("Dye", "You can't dye your clothes in sex!", null);
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant equipped clothing!", null);
							
						} else if(index == 6) {
							if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
								return new Response("Unequip", "Unequip the " + clothing.getName() + ".", Sex.SEX_DIALOGUE){
									@Override
									public void effects(){
										owner.unequipClothingIntoInventory(clothing, true, Main.game.getPlayer());
										Sex.setUnequipClothingText(owner.getUnequipDescription());
										Main.game.restoreSavedContent();
										Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
										Sex.setSexStarted(true);
									}
								};
							} else {
								return new Response("Unequip", "You can't unequip the " + clothing.getName() + ", as other clothing is blocking you from doing so!", null);
							}
							
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
						if (index == 1) {
							return new Response("Drop", "You can't make someone drop their clothing while fighting them!", null);
							
						} else if (index==4) {
							return new Response("Dye", "You can't dye someone else's equipped clothing while you're fighting them!", null);
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant someone else's equipped clothing, especially not while fighting them!", null);
							
						} else if(index == 6) {
							return new Response("Unequip", "You can't unequip someone's clothing while fighting them!", null);
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case FULL_MANAGEMENT:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(areaFull) {
									return new Response("Drop", "This area is full, so you can't drop your " + clothing.getName() + " here!", null);
								} else {
									return new Response("Drop", "Drop your " + clothing.getName() + ".", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.unequipClothingOntoFloor(clothing, true, Main.game.getPlayer()) + "</p>");
											populateJinxedClothingList();
										}
									};
								}
								
							} else {
								if(areaFull) {
									return new Response("Store", "This area is full, so you can't store your " + clothing.getName() + " here!", null);
								} else {
									return new Response("Store", "Store your " + clothing.getName() + " in this area.", INVENTORY_MENU){
										@Override
										public void effects(){
											Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.unequipClothingOntoFloor(clothing, true, Main.game.getPlayer()) + "</p>");
											populateJinxedClothingList();
										}
									};
								}
							}
							
						} else if (index==4) {
							if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)) {
								return new Response("Dye", "Use a dye-brush to dye this item of clothing.", DYE_CLOTHING);
								
							} else {
								return new Response("Dye", UtilText.parse(inventoryNPC, "You'll need to find a dye-brush if you want to dye [npc.name]'s clothes."), null);
							}
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant equipped clothings!", null);
							
						} else if(index == 6) {
							return new Response("Unequip", "Unequip the " + clothing.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + inventoryNPC.unequipClothingIntoInventory(clothing, true, Main.game.getPlayer()) + "</p>");
									populateJinxedClothingList();
								}
							};
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
					case SEX:
						if (index == 1) {
							boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasClothing(clothing);
							if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
								if(areaFull) {
									return new Response("Drop", "This area is full, so you can't drop your " + clothing.getName() + " here!", null);
								} else {
									return new Response("Drop", "Drop your " + clothing.getName() + ".", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											inventoryNPC.unequipClothingOntoFloor(clothing, true, Main.game.getPlayer());
											Sex.setUnequipClothingText(inventoryNPC.getUnequipDescription());
											Main.game.restoreSavedContent();
											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Sex.setSexStarted(true);
										}
									};
								}
								
							} else {
								if(areaFull) {
									return new Response("Store", "This area is full, so you can't store your " + clothing.getName() + " here!", null);
								} else {
									return new Response("Store", "Store your " + clothing.getName() + " in this area.", Sex.SEX_DIALOGUE){
										@Override
										public void effects(){
											inventoryNPC.unequipClothingOntoFloor(clothing, true, Main.game.getPlayer());
											Sex.setUnequipClothingText(inventoryNPC.getUnequipDescription());
											Main.game.restoreSavedContent();
											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Sex.setSexStarted(true);
										}
									};
								}
							}
							
						} else if (index==4) {
							return new Response("Dye", UtilText.parse(inventoryNPC, "You can't dye [npc.name]'s clothes in sex!"), null);
							
						} else if(index == 5) {
							return new Response("Enchant", "You can't enchant equipped clothing!", null);
							
						} else if(index == 6) {
							return new Response("Unequip", "Unequip the " + clothing.getName() + ".", Sex.SEX_DIALOGUE){
								@Override
								public void effects(){
									inventoryNPC.unequipClothingIntoInventory(clothing, true, Main.game.getPlayer());
									Sex.setUnequipClothingText(inventoryNPC.getUnequipDescription());
									Main.game.restoreSavedContent();
									Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
									Sex.setSexStarted(true);
								}
							};
							
						} else if (index == 10) {
							return getQuickTradeResponse();
							
						} else {
							return null;
						}
						
						case TRADING:
							if (index == 1) {
								return new Response("Drop", UtilText.parse("You can't make [npc.name] drop [npc.her] clothing!"), null);
								
							} else if (index==4) {
								return new Response("Dye", UtilText.parse(inventoryNPC, "You can't dye [npc.name]'s clothes!"), null);
								
							}  else if(index == 5) {
								return new Response("Enchant", UtilText.parse("You can't enchant [npc.name]'s clothing!"), null);
								
							} else if(index == 6) {
								return new Response("Unequip", UtilText.parse("You can't unequip [npc.name]'s clothing!"), null);
								
							} else if (index == 10) {
								return getQuickTradeResponse();
								
							} else {
								return null;
							}
					}
				
				}
				return null;
			
			
			
			
//			
//			// Handle clothing while in sex a little differently: TODO "as other clothing is in the way" needs to be more specific.
//			if (Main.game.isInSex()) {
//				if (index == 1) {
//					if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
//						return new Response("Unequip", "Unequip the " + clothing.getName() + ".", Sex.SEX_DIALOGUE){
//							@Override
//							public void effects(){
//								owner.unequipClothingOntoFloor(clothing, true, Main.game.getPlayer());
//								Sex.setUnequipClothingText(owner.getUnequipDescription());
//								Main.game.restoreSavedContent();
//								Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
//								Sex.setSexStarted(true);
//							}
//						};
//					} else {
//						return new Response("Unequip", "You can't unequip the " + clothing.getName() + ", as other clothing is blocking you from doing so!", null);
//					}
//					
//				} else if (index == 2 ) {
//					if(owner.isPlayer()) {
//						return new Response("Steal", "You can't steal your own clothing...", null,
//								null, CorruptionLevel.FOUR_LUSTFUL, null, null, null);
//						
//					} else {
//						if(owner instanceof NPC) {
//							if(!((NPC)owner).isClothingStealable()) {
//								return new Response("Steal", "You can't steal this character's clothing...", null,
//										null, CorruptionLevel.FOUR_LUSTFUL, null, null, null);
//							}
//						}
//						
//						if(Sex.isPlayerDom() && !Sex.getSexManager().isConsensualSex()) {
//							if (owner.isAbleToUnequip(clothing, false, Main.game.getPlayer())) {
//								if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing)) {
//									return new Response("Steal", "Your inventory is full, so you can't steal [npc.name]'s " + clothing.getName() + "!", null,
//											null, CorruptionLevel.FOUR_LUSTFUL, null, null, null);
//									
//								} else {
//									return new Response("Steal", "Steal [npc.name]'s " + clothing.getName() + ".", Sex.SEX_DIALOGUE,
//											null, CorruptionLevel.FOUR_LUSTFUL, null, null, null){
//										@Override
//										public void effects(){
//											owner.unequipClothingOntoFloor(clothing, true, Main.game.getPlayer());
//											Sex.setUnequipClothingText(owner.getUnequipDescription()
//													+ "<p style='text-align:center;'>" + Main.game.getPlayer().addClothing(clothing, true) + "</p>");
//											Main.game.restoreSavedContent();
//											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
//											Sex.setSexStarted(true);
//										}
//									};
//								}
//							} else {
//								return new Response("Steal", "You can't unequip [npc.name]'s " + clothing.getName() + ", as other clothing is blocking you from doing so!", null,
//										null, CorruptionLevel.FOUR_LUSTFUL, null, null, null);
//							}
//						} else {
//							return new Response("Steal", "You can only steal clothing if you're the dominant partner in non-consensual sex.", null,
//									null, CorruptionLevel.FOUR_LUSTFUL, null, null, null);
//						}
//						
//					}
//					
//				} else if (index == 3) {
//					if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)) {
//						if(Sex.isPlayerDom() && !Sex.getSexManager().isConsensualSex()) {
//							return new Response("Dye", "Use a dye-brush to dye this item of clothing.", DYE_CLOTHING_SEX);
//						} else {
//							return new Response("Dye", "You can only dye clothing if you're the dominant partner in non-consensual sex.", null);
//						}
//					} else {
//						if (Main.getProperties().isItemDiscovered(ItemType.DYE_BRUSH))
//							return new Response("Dye", "You'll need to find another dye-brush if you want to dye your clothes.", null);
//						else
//							return new Response("Dye", "You'll need to find an item of some sort if you want to dye your clothes.", null);
//					}
//	
//				} else if (index != 0 && index - 4 < clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().size()) {
//					if(owner.isAbleToBeDisplaced(clothing, clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4), false, false, Main.game.getPlayer())){
//						return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4).getDescription()),
//								Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4).getDescription()) + " the " + clothing.getName() + ". "
//										+ clothing.getClothingBlockingDescription(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4), owner,
//												" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>This will expose "+(owner.isPlayer()?"your":"[npc.her]")+" ", ".</span>"),
//										Sex.SEX_DIALOGUE){
//							@Override
//							public void effects(){
//								owner.isAbleToBeDisplaced(clothing, clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4), true, false, Main.game.getPlayer());
//								Sex.setUnequipClothingText(owner.getDisplaceDescription());
//								Main.game.restoreSavedContent();
//								Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
//								Sex.setSexStarted(true);
//							}
//						};
//						
//					} else {
//						return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4).getDescription()),
//								"You can't "+clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4).getDescription() + " the " + clothing.getName() + ", as other clothing is in the way!", null);
//					}
//					
//				} else if (index == 0){
//					return getCloseInventoryResponse();
//				} else {
//					return null;
//				}
//				
//			// Standard handling of clothing:
//			} else {
//				if (index == 1) {
//					if (clothing.isSealed()) {
//						return new Response("Unequip",
//								"<span style='color:" + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>You can't unequip the " + clothing.getName() + " because " + (clothing.getClothingType().isPlural() ? "they've" : "it's")
//								+ " been jinxed!</span>", null);
//						
//					} else if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing)) {
//						return new Response("Unequip", "Your inventory is full, so you can't unequip the " + clothing.getName() + " into your inventory!", null);
//					} else {
//						return new Response("Unequip", "Unequip the " + clothing.getName() + " and add " + (clothing.getClothingType().isPlural() ? "them" : "it") + " to your inventory.", INVENTORY_MENU){
//							@Override
//							public void effects(){
//								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().unequipClothingIntoInventory(clothing, true, Main.game.getPlayer()) + "</p>");
//								populateJinxedClothingList();
//							}
//						};
//					}
//					
//				} else if (index == 2) {
//					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasClothing(clothing);
//					if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
//						if (clothing.isSealed()) {
//							return new Response("Drop",
//									"<span style='color:" + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>You can't drop the " + clothing.getName()
//									+ " because " + (clothing.getClothingType().isPlural() ? "they've" : "it's")
//									+ " been jinxed!</span>", null);
//							
//						} else if(areaFull) {
//							return new Response("Drop", "This area is full, so you can't drop your " + clothing.getName() + " here!", null);
//							
//						} else {
//							return new Response("Drop", "Drop your " + clothing.getName() + ".", INVENTORY_MENU){
//								@Override
//								public void effects(){
//									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().unequipClothingOntoFloor(clothing, true, Main.game.getPlayer()) + "</p>");
//									populateJinxedClothingList();
//								}
//							};
//						}
//						
//					} else {
//						if (clothing.isSealed()) {
//							return new Response("Store",
//									"<span style='color:" + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>You can't store your " + clothing.getName()
//									+ " because " + (clothing.getClothingType().isPlural() ? "they've" : "it's")
//									+ " been jinxed!</span>", null);
//							
//						} else if(areaFull) {
//							return new Response("Store", "This area is full, so you can't store your " + clothing.getName() + " here!", null);
//							
//						} else {
//							return new Response("Store", "Store your " + clothing.getName() + " in this area.", INVENTORY_MENU){
//								@Override
//								public void effects(){
//									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().unequipClothingOntoFloor(clothing, true, Main.game.getPlayer()) + "</p>");
//									populateJinxedClothingList();
//								}
//							};
//						}
//					}
//	
//				} else if (index == 3) {
//					if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)) {
//						return new Response("Dye", "Use a dye-brush to dye this item of clothing.", DYE_EQUIPPED_CLOTHING);
//					} else {
//						if (Main.getProperties().isItemDiscovered(ItemType.DYE_BRUSH))
//							return new Response("Dye", "You'll need to find another dye-brush if you want to dye your clothes.", null);
//						else
//							return new Response("Dye", "You'll need to find an item of some sort if you want to dye your clothes.", null);
//					}
//	
//				} else if (index != 0 && index - 4 < clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().size()){
//
//					if (clothing.getDisplacedList().contains(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4))) {
//						return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4).getOppositeDescription()),
//								Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4).getOppositeDescription()) + " the " + clothing.getName() + ". "
//										+ clothing.getClothingBlockingDescription(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4), Main.game.getPlayer(),
//												" <span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>This will cover your ", ".</span>"),
//										CLOTHING_EQUIPPED){
//							@Override
//							public void effects(){
//								Main.game.getPlayer().isAbleToBeReplaced(clothing, clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4), true, true, Main.game.getPlayer());
//							}
//						};
//					} else {
//						return new Response(Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4).getDescription()),
//								Util.capitaliseSentence(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4).getDescription()) + " the " + clothing.getName() + ". "
//										+ clothing.getClothingBlockingDescription(clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4), Main.game.getPlayer(),
//												" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>This will expose your ", ".</span>"),
//										CLOTHING_EQUIPPED){
//							@Override
//							public void effects(){
//								Main.game.getPlayer().isAbleToBeDisplaced(clothing, clothing.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4), true, true, Main.game.getPlayer());
//							}
//						};
//					}
//					
//				} else if (index == 9 && inventoryNPC!=null) {
//					return getBuybackResponse();
//				} else if (index == 10) {
//					return getQuickTradeResponse();
//				} else if (index == 0) {
//					return getCloseInventoryResponse();
//				} else
//					return null;
//			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};

	public static String jinxedClothingView() { //TODO
		inventorySB = new StringBuilder("");

		inventorySB.append("<p style='text-align: center;'>" + "<b>Jinxed clothing:</b>"
				+ "</p>");

		inventorySB.append("<div class='inventory-not-equipped'>");

		if (!jinxedClothing.isEmpty()) {
			for (int i = 0; i < jinxedClothing.size(); i++) {
				inventorySB.append(
						"<div class='inventory-item-slot'>"
							+ "<div class='inventory-icon-content'>"
								+ jinxedClothing.get(i).getSVGString()
							+ "</div>"
							+"<div class='overlay' id='JINXED_" + i + "'></div>"
						+ "</div>");
			}
		}
		inventorySB.append("</div>");

		return inventorySB.toString();
	}

	public static void populateJinxedClothingList() {
		jinxedClothing.clear();
		for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped())
			if (c.isSealed() || c.isBadEnchantment())
				jinxedClothing.add(c);
	}

	public static final DialogueNodeOld REMOVE_JINX = new DialogueNodeOld("Choose a jinxed item", "", true) {
		/**
		 */
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent() {
			return jinxedClothingView();
		}
		
		@Override
		public String getContent() {
			return "<p>Choose an item to remove its jinx.</p>"
					+ "<p><b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>The " + (jinxRemovalFromFloor ? weaponFloor.getName() : weapon.getName())
					+ " will be lost if you do this!</b></p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous menu.", INVENTORY_MENU);
			} else {
				return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};

	public static final DialogueNodeOld DYE_CLOTHING = new DialogueNodeOld("Dye clothing", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			inventorySB = new StringBuilder(
					"<div class='inventoryImage'>"
							+ "<div class='inventoryImage-content'>"
								+ clothing.getSVGString()
							+ "</div>"
						+ "</div>"
						+ "<p><b>"+clothing.getDisplayName(true)+"</b></p>"
						+ clothing.getDescription()
						+ clothing.clothingExtraInformation(null)
						+ "<p>"
							+ "Available colours for this item (hover over to view preview):"
						+ "</p>");

			for (Colour c : clothing.getClothingType().getAvailableColours()) {
				inventorySB.append("<div class='phone-item-colour' id='" + (clothing.getClothingType().hashCode() + "_" + c.toString()) + "' style='background-color:" + c.toWebHexString() + ";'></div>");
			}
			
			return inventorySB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous menu.", INVENTORY_MENU);

			} else if (index - 1 < clothing.getClothingType().getAvailableColours().size()) {
				return new Response("Dye: " + Util.capitaliseSentence(clothing.getClothingType().getAvailableColours().get(index - 1).getName()),
						"Dye the " + clothing.getName() + " " + Util.capitaliseSentence(clothing.getClothingType().getAvailableColours().get(index - 1).getName())
							+ ". This action is permanent, and you'll need another dye-brush if you want to change its colour again.", INVENTORY_MENU){
					@Override
					public void effects(){
						Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), Main.game.getPlayer(), false);
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center;'>"
									+ ItemType.DYE_BRUSH.getDyeBrushEffects(clothing, clothing.getClothingType().getAvailableColours().get(index - 1))
								+ "</p>"
								+ "<p>"
									+ "<b>Your " + clothing.getName() + " " + (clothing.getClothingType().isPlural() ? "have been" : "has been") + " dyed</b> <b style='color:"
										+ clothing.getClothingType().getAvailableColours().get(index - 1).toWebHexString() + ";'>" + clothing.getClothingType().getAvailableColours().get(index - 1).getName() + "</b>!"
								+ "</p>"
								+ "<p>"
									+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
											?"You have <b>" + Main.game.getPlayer().getMapOfDuplicateItems().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
													+ "</b> dye-brush" + (Main.game.getPlayer().getMapOfDuplicateItems().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
											:"You have <b>0</b> dye-brushes left!")
								+ "</p>");
						Main.game.getPlayer().removeClothing(clothing);
						clothing.setColour(clothing.getClothingType().getAvailableColours().get(index - 1));
						Main.game.getPlayer().addClothing(clothing, false);
					}
				};

			} else
				return null;
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};

	public static final DialogueNodeOld DYE_EQUIPPED_CLOTHING = new DialogueNodeOld("Dye clothing", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			inventorySB = new StringBuilder(
					"<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ clothing.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+clothing.getDisplayName(true)+"</b></p>"
					+ clothing.getDescription()
					+ clothing.clothingExtraInformation(Main.game.getPlayer())
					+ "<p>"
						+ "Available colours for this item (hover over to view preview):"
					+ "</p>");

			for (Colour c : clothing.getClothingType().getAvailableColours())
				inventorySB.append("<div class='phone-item-colour' id='" + (clothing.getClothingType().hashCode() + "_" + c.toString()) + "' style='background-color:" + c.toWebHexString() + ";'></div>");

			return inventorySB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous menu.", CLOTHING_EQUIPPED);

			} else if (index - 1 < clothing.getClothingType().getAvailableColours().size()) {
				return new Response("Dye: " + Util.capitaliseSentence(clothing.getClothingType().getAvailableColours().get(index - 1).getName()),
						"Dye the " + clothing.getName() + " " + Util.capitaliseSentence(clothing.getClothingType().getAvailableColours().get(index - 1).getName())
						+ ". This action is permanent, and you'll need another dye-brush if you want to change its colour again.", CLOTHING_EQUIPPED){
					@Override
					public void effects(){
						Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), Main.game.getPlayer(), false);
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center;'>"
									+ ItemType.DYE_BRUSH.getDyeBrushEffects(clothing, clothing.getClothingType().getAvailableColours().get(index - 1))
								+ "</p>"
								+ "<p>"
									+ "<b>Your " + clothing.getName() + " " + (clothing.getClothingType().isPlural() ? "have been" : "has been") + " dyed</b> <b style='color:"
										+ clothing.getClothingType().getAvailableColours().get(index - 1).toWebHexString() + ";'>" + clothing.getClothingType().getAvailableColours().get(index - 1).getName() + "</b>!"
								+ "</p>"
								+ "<p>"
									+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
											?"You have <b>" + Main.game.getPlayer().getMapOfDuplicateItems().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
													+ "</b> dye-brush" + (Main.game.getPlayer().getMapOfDuplicateItems().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
											:"You have <b>0</b> dye-brushes left!")
								+ "</p>");
						clothing.setColour(clothing.getClothingType().getAvailableColours().get(index - 1));
					}
				};

			} else
				return null;
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};
	
	public static final DialogueNodeOld DYE_CLOTHING_SEX = new DialogueNodeOld("Dye clothing", "", true) { //TODO is this working?
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			inventorySB = new StringBuilder(
					"<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ clothing.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+clothing.getDisplayName(true)+"</b></p>"
					+ clothing.getDescription()
					+ clothing.clothingExtraInformation(Main.game.getPlayer())
					+ "<p>"
						+ "Available colours for this item (hover over to view preview):"
					+ "</p>");

			for (Colour c : clothing.getClothingType().getAvailableColours()) {
				inventorySB.append("<div class='phone-item-colour' id='" + (clothing.getClothingType().hashCode() + "_" + c.toString()) + "' style='background-color:" + c.toWebHexString() + ";'></div>");
			}
			
			return inventorySB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous menu.", CLOTHING_EQUIPPED);

			} else if (index - 1 < clothing.getClothingType().getAvailableColours().size()) {
				return new Response("Dye: " + Util.capitaliseSentence(clothing.getClothingType().getAvailableColours().get(index - 1).getName()),
						"Dye the " + clothing.getName() + " " + Util.capitaliseSentence(clothing.getClothingType().getAvailableColours().get(index - 1).getName())
						+ ". This action is permanent, and you'll need another dye-brush if you want to change its colour again.",  Sex.SEX_DIALOGUE){
					@Override
					public void effects(){
						Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), Main.game.getPlayer(), false);
						Sex.setDyeClothingText(
								"<p style='text-align:center;'>"
									+ ItemType.DYE_BRUSH.getDyeBrushEffects(clothing, clothing.getClothingType().getAvailableColours().get(index - 1))
								+ "</p>"
								+ "<p>"
									+ "<b>"+(owner.isPlayer()?"Your":"[npc.Name]'s")+" " + clothing.getName() + " " + (clothing.getClothingType().isPlural() ? "have been" : "has been") + " dyed</b> <b style='color:"
										+ clothing.getClothingType().getAvailableColours().get(index - 1).toWebHexString() + ";'>" + clothing.getClothingType().getAvailableColours().get(index - 1).getName() + "</b>!"
								+ "</p>"
								+ "<p>"
									+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
											?"You have <b>" + Main.game.getPlayer().getMapOfDuplicateItems().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
													+ "</b> dye-brush" + (Main.game.getPlayer().getMapOfDuplicateItems().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
											:"You have <b>0</b> dye-brushes left!")
								+ "</p>");
						clothing.setColour(clothing.getClothingType().getAvailableColours().get(index - 1));

						Main.game.restoreSavedContent();
						Sex.endSexTurn(SexActionUtility.CLOTHING_DYE);
						Sex.setSexStarted(true);
					}
				};

			} else
				return null;
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};

	// Utility methods:
	private static ResponseEffectsOnly getCloseInventoryResponse() { 
		return new ResponseEffectsOnly("Back", "Close the Inventory menu."){
			@Override
			public void effects(){
				Main.game.restoreSavedContent();
			}
		};
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
	
	private static Response getQuickTradeResponse() {
		if (Main.game.getDialogueFlags().quickTrade) {
			return new Response("Quick-Manage: <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>",
					"Quick-Manage is turned <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>!</br>"
				+ "That means you can buy and sell items with a single click when trading, and pick-up and drop items with a single click when in normal inventory mode.", INVENTORY_MENU){
				@Override
				public void effects(){
					Main.game.getDialogueFlags().quickTrade = !Main.game.getDialogueFlags().quickTrade;
				}
			};
		} else {
			return new Response("Quick-Manage: <b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>OFF</b>",
					"Quick-Manage is turned <b style='color:" + Colour.TEXT_GREY.toWebHexString() + ";'>OFF</b>.</br>"
				+ "That means when you click on an item, you get a detailed view of the item before deciding whether to buy/sell or pick-up/drop it.", INVENTORY_MENU){
				@Override
				public void effects(){
					Main.game.getDialogueFlags().quickTrade = !Main.game.getDialogueFlags().quickTrade;
				}
			};
		}
	}

	// Items:
	
	private static void transferItems(GameCharacter from, GameCharacter to, AbstractItem item, int count) {
		if (!to.isInventoryFull() || to.hasItem(item)) {
			
			List<AbstractItem> items = from.getAllItemsInInventory().stream()
				.filter(item::equals)
				.collect(Collectors.toList());
			
			for(int i = 0 ; i<count; i++) {
				to.addItem(items.get(i), false);
				from.removeItem(items.get(i));
			}
		}
	}
	
	private static void dropItems(GameCharacter from, AbstractItem item, int count) {
		if (!Main.game.getCurrentCell().getInventory().isInventoryFull() || Main.game.getCurrentCell().getInventory().hasItem(item)) {
			
			List<AbstractItem> items = from.getAllItemsInInventory().stream()
				.filter(item::equals)
				.collect(Collectors.toList());
			
			for(int i = 0 ; i<count; i++) {
				Main.game.getCurrentCell().getInventory().addItem(items.get(i));
				from.removeItem(items.get(i));
			}
		}
	}
	
	private static void pickUpItems(GameCharacter to, AbstractItem item, int count) {
		if (!Main.game.getCurrentCell().getInventory().isInventoryFull() || Main.game.getCurrentCell().getInventory().hasItem(item)) {
			
			List<AbstractItem> items = Main.game.getCurrentCell().getInventory().getAllItemsInInventory().stream()
				.filter(item::equals)
				.collect(Collectors.toList());
			
			for(int i = 0 ; i<count; i++) {
				to.addItem(items.get(i), true);
			}
		}
	}
	
	private static void sellItems(GameCharacter from, GameCharacter to, AbstractItem item, int count, int itemPrice) {
		if (!to.isInventoryFull() || to.hasItem(item)) {
			
			if(buyback && to.isPlayer()) {
				Main.game.getPlayer().incrementMoney(-itemPrice);
				from.incrementMoney(itemPrice);
				Main.game.getPlayer().addItem(item, false);
				Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
				
			} else {
				List<AbstractItem> items = from.getAllItemsInInventory().stream()
					.filter(item::equals)
					.collect(Collectors.toList());
				
				for(int i = 0 ; i<count; i++) {
					if(from.isPlayer()) {
						Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(item, itemPrice));
					} else {
						to.addItem(items.get(i), false);
					}
					from.incrementMoney(itemPrice);
					to.incrementMoney(-itemPrice);
					from.removeItem(items.get(i));
				}
			}
		}
	}
	
	
	
	// Weapons:
	
	private static void transferWeapons(GameCharacter from, GameCharacter to, AbstractWeapon weapon, int count) {
		if (!to.isInventoryFull() || to.hasWeapon(weapon)) {
			
			List<AbstractWeapon> weapons = from.getAllWeaponsInInventory().stream()
				.filter(weapon::equals)
				.collect(Collectors.toList());
			
			for(int i = 0 ; i<count; i++) {
				to.addWeapon(weapons.get(i), false);
				from.removeWeapon(weapons.get(i));
			}
		}
	}
	
	private static void dropWeapons(GameCharacter from, AbstractWeapon weapon, int count) {
		if (!Main.game.getCurrentCell().getInventory().isInventoryFull() || Main.game.getCurrentCell().getInventory().hasWeapon(weapon)) {
			
			List<AbstractWeapon> weapons = from.getAllWeaponsInInventory().stream()
				.filter(weapon::equals)
				.collect(Collectors.toList());
			
			for(int i = 0 ; i<count; i++) {
				Main.game.getCurrentCell().getInventory().addWeapon(weapons.get(i));
				from.removeWeapon(weapons.get(i));
			}
		}
	}
	
	private static void pickUpWeapons(GameCharacter to, AbstractWeapon weapon, int count) {
		if (!Main.game.getCurrentCell().getInventory().isInventoryFull() || Main.game.getCurrentCell().getInventory().hasWeapon(weapon)) {
			
			List<AbstractWeapon> weapons = Main.game.getCurrentCell().getInventory().getAllWeaponsInInventory().stream()
				.filter(weapon::equals)
				.collect(Collectors.toList());
			
			for(int i = 0 ; i<count; i++) {
				to.addWeapon(weapons.get(i), true);
			}
		}
	}
	
	private static void sellWeapons(GameCharacter from, GameCharacter to, AbstractWeapon weapon, int count, int itemPrice) {
		if (!to.isInventoryFull() || to.hasWeapon(weapon)) {
			
			if(buyback && to.isPlayer()) {
				Main.game.getPlayer().incrementMoney(-itemPrice);
				from.incrementMoney(itemPrice);
				Main.game.getPlayer().addWeapon(weapon, false);
				Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
				
			} else {
				List<AbstractWeapon> weapons = from.getAllWeaponsInInventory().stream()
					.filter(weapon::equals)
					.collect(Collectors.toList());
				
				for(int i = 0 ; i<count; i++) {
					if(from.isPlayer()) {
						Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(weapon, itemPrice));
					} else {
						to.addWeapon(weapons.get(i), false);
					}
					from.incrementMoney(itemPrice);
					to.incrementMoney(-itemPrice);
					from.removeWeapon(weapons.get(i));
				}
			}
		}
	}
	
	
	
	
	
	// Clothing:
	
	private static void transferClothing(GameCharacter from, GameCharacter to, AbstractClothing clothing, int count) {
		if (!to.isInventoryFull() || to.hasClothing(clothing)) {
			
			List<AbstractClothing> clothings = from.getAllClothingInInventory().stream()
				.filter(clothing::equals)
				.collect(Collectors.toList());
			
			for(int i = 0 ; i<count; i++) {
				to.addClothing(clothings.get(i), false);
				from.removeClothing(clothings.get(i));
			}
		}
	}
	
	private static void dropClothing(GameCharacter from, AbstractClothing clothing, int count) {
		if (!Main.game.getCurrentCell().getInventory().isInventoryFull() || Main.game.getCurrentCell().getInventory().hasClothing(clothing)) {
			
			List<AbstractClothing> clothings = from.getAllClothingInInventory().stream()
				.filter(clothing::equals)
				.collect(Collectors.toList());
			
			for(int i = 0 ; i<count; i++) {
				Main.game.getCurrentCell().getInventory().addClothing(clothings.get(i));
				from.removeClothing(clothings.get(i));
			}
		}
	}
	
	private static void pickUpClothing(GameCharacter to, AbstractClothing clothing, int count) {
		if (!Main.game.getCurrentCell().getInventory().isInventoryFull() || Main.game.getCurrentCell().getInventory().hasClothing(clothing)) {
			
			List<AbstractClothing> clothings = Main.game.getCurrentCell().getInventory().getAllClothingInInventory().stream()
				.filter(clothing::equals)
				.collect(Collectors.toList());
			
			for(int i = 0 ; i<count; i++) {
				to.addClothing(clothings.get(i), true);
			}
		}
	}
	
	private static void sellClothing(GameCharacter from, GameCharacter to, AbstractClothing clothing, int count, int itemPrice) {
		if (!to.isInventoryFull() || to.hasClothing(clothing)) {
			
			if(buyback && to.isPlayer()) {
				Main.game.getPlayer().incrementMoney(-itemPrice);
				from.incrementMoney(itemPrice);
				Main.game.getPlayer().addClothing(clothing, false);
				Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
				
			} else {
				List<AbstractClothing> clothings = from.getAllClothingInInventory().stream()
					.filter(clothing::equals)
					.collect(Collectors.toList());
				
				for(int i = 0 ; i<count; i++) {
					if(from.isPlayer()) {
						Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(clothing, itemPrice));
					} else {
						to.addClothing(clothings.get(i), false);
					}
					from.incrementMoney(itemPrice);
					to.incrementMoney(-itemPrice);
					from.removeClothing(clothings.get(i));
				}
			}
		}
	}
	
	
	
	
	public static String buyClothing(AbstractClothing clothing) {
		int itemPrice = clothing.getPrice(inventoryNPC.getSellModifier());
		if (Main.game.getPlayer().getMoney() < itemPrice) {
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		} else if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing)) {
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		} else {
			inventoryNPC.removeClothing(clothing);
			// Temporary fix! TODO
			((Nyan)Main.game.getNyan()).removeClothingFromLists(clothing);
			
			Main.game.getPlayer().incrementMoney(-itemPrice);

			String s = "<p style='text-align:center;'>" + "You hand over " + UtilText.formatAsMoney(itemPrice) + " to " + inventoryNPC.getName("the") 
					+ " in exchange for the " + clothing.getName() + "." + "</p>" + Main.game.getPlayer().addClothing(clothing, false);

			return s;
		}
	}
	
	public static String buyAllClothing(AbstractClothing clothing) {
		int clothingCount = inventoryNPC.getClothingCount(clothing);
		int totalPrice = clothing.getPrice(inventoryNPC.getSellModifier()) * clothingCount;
		if (Main.game.getPlayer().getMoney() < totalPrice) {
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		} else if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing)) {
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		} else {
			
			if(inventoryNPC == Main.game.getNyan()) {
				for(int i=0; i<clothingCount; i++) {
					// Temporary fix! TODO
					((Nyan)Main.game.getNyan()).removeClothingFromLists(clothing);
				}
			}
			
			List<AbstractClothing> clothingItems = inventoryNPC.getAllClothingInInventory().stream()
					.filter(clothing::equals)
					.collect(Collectors.toList());
					
			clothingItems.stream().forEach(tradeClothing -> {
				Main.game.getPlayer().addClothing(tradeClothing, false);
				inventoryNPC.removeClothing(tradeClothing);
			});
			
			Main.game.getPlayer().incrementMoney(-totalPrice);

			String s = UtilText.parse(inventoryNPC,
					"<p style='text-align:center;'>"
							+ "You hand over " + UtilText.formatAsMoney(totalPrice) + " to [npc.name] in exchange for all of the "
							+ clothing.getName() + " [npc.she] has."
							+ "</br>"
							+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Clothing added to inventory:</b> <b>"+clothingCount+"x</b> <b>"+clothing.getDisplayName(true)+"</b>"
					+ "</p>");

			return s;
		}
	}

	public static String buyBackClothing(AbstractClothing clothing, int price, int buyBackIndex) {

		if (Main.game.getPlayer().getMoney() < price)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing))
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
			Main.game.getPlayer().incrementMoney(-price);

			String s = "<p style='text-align:center;'>" + "You hand over " + UtilText.formatAsMoney(price) + " to "
					+ inventoryNPC.getName("the") + " in exchange for the " + clothing.getName() + "." + "</p>" + Main.game.getPlayer().addClothing(clothing, false);

			return s;
		}
	}

	public static String sellClothing(AbstractClothing clothing) {
		int itemPrice = clothing.getPrice(inventoryNPC.getBuyModifier());
		Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(clothing, itemPrice));
		Main.game.getPlayer().removeClothing(clothing);
		Main.game.getPlayer().incrementMoney(itemPrice);

		String s = "<p style='text-align:center;'>" 
						+ "You hand over the " + clothing.getName() + " to " + inventoryNPC.getName("the") + " in exchange for " + UtilText.formatAsMoney(itemPrice) + "." 
				+ "</p>";

		return s;
	}

	public static AbstractItem getItem() {
		return item;
	}

	public static void setItem(AbstractItem item) {
		InventoryDialogue.item = item;
	}

	public static AbstractWeapon getWeapon() {
		return weapon;
	}

	public static void setWeapon(AbstractWeapon weapon) {
		InventoryDialogue.weapon = weapon;
	}

	public static AbstractClothing getClothing() {
		return clothing;
	}

	public static void setClothing(AbstractClothing clothing) {
		InventoryDialogue.clothing = clothing;
	}

	public static AbstractItem getItemFloor() {
		return itemFloor;
	}

	public static void setItemFloor(AbstractItem itemFloor) {
		InventoryDialogue.itemFloor = itemFloor;
	}

	public static AbstractWeapon getWeaponFloor() {
		return weaponFloor;
	}

	public static void setWeaponFloor(AbstractWeapon weaponFloor) {
		InventoryDialogue.weaponFloor = weaponFloor;
	}

	public static AbstractClothing getClothingFloor() {
		return clothingFloor;
	}

	public static void setClothingFloor(AbstractClothing clothingFloor) {
		InventoryDialogue.clothingFloor = clothingFloor;
	}

	public static List<AbstractClothing> getJinxedClothing() {
		return jinxedClothing;
	}

	public static boolean isJinxRemovalFromFloor() {
		return jinxRemovalFromFloor;
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
