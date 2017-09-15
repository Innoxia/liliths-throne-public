package com.base.game.dialogue.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.base.game.character.GameCharacter;
import com.base.game.character.Quest;
import com.base.game.character.QuestLine;
import com.base.game.character.attributes.CorruptionLevel;
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
	
	// Welcome to hell!
	
	private static final int IDENTIFICATION_PRICE = 10;
	
	private static AbstractItem item, itemFloor;
	private static AbstractClothing clothing, clothingFloor, clothingEquipped;
	private static AbstractWeapon weapon, weaponFloor, weaponEquipped;
	private static GameCharacter ownerInSex;
	
	private static NPC inventoryNPC;
	private static NPCInventoryInteraction interactionType;

	private static StringBuilder inventorySB;

	private static List<AbstractClothing> jinxedClothing = new ArrayList<>();

	private static boolean jinxRemovalFromFloor, buyback;

	private static int buyBackPrice, buyBackIndex;

	private static String inventoryView() {
		inventorySB = new StringBuilder();
		
//		if (InventoryDialogue.getInventoryNPC()!=null) {
//			inventorySB.append(RenderingEngine.ENGINE.getInventoryPanel(InventoryDialogue.getInventoryNPC(), buyback));
//			
//		} else if (Main.game.isInCombat()) {
//			inventorySB.append(RenderingEngine.ENGINE.getInventoryPanel(Combat.getOpponent(), false));
//			
//		} else if (Main.game.isInSex()) {
//			inventorySB.append(RenderingEngine.ENGINE.getInventoryPanel(Sex.getPartner(), false));
//			
//		} else {
			inventorySB.append(RenderingEngine.ENGINE.getInventoryPanel(inventoryNPC, false));
//		}
		
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
			if (InventoryDialogue.getInventoryNPC()!=null && interactionType == NPCInventoryInteraction.TRADING) {
				return InventoryDialogue.getInventoryNPC().getTraderDescription();
			} else {
				return "";
			}
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			} else if (index == 1 && InventoryDialogue.getInventoryNPC()==null) {
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
				
			} else if (index == 8 && InventoryDialogue.getInventoryNPC()!=null) {
				return getBuybackResponse();
			} else if (index == 9 && !Main.game.isInSex() && !Main.game.isInCombat()) {
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
					+ item.getExtraDescription(Main.game.getPlayer(), Main.game.getPlayer())
					+ (InventoryDialogue.getInventoryNPC() != null ? 
							InventoryDialogue.getInventoryNPC().willBuy(item)
								? "<p>"
									+ InventoryDialogue.getInventoryNPC().getName("The") + " will buy it for " + UtilText.formatAsMoney(item.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier())) + "."
								+ "</p>" 
								: InventoryDialogue.getInventoryNPC().getName("The") + " doesn't want to buy this."
						: "");
		}

		@Override
		public Response getResponse(int index) {
			if(Main.game.isInSex()){
				if (index == 0) {
					return getCloseInventoryResponse();
				} else if (index == 1) {
					if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
								item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
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
	
				} else if (index == 2) {
					if (!item.isAbleToBeUsed(Sex.getPartner())) {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)",
								item.getUnableToBeUsedDescription(Sex.getPartner()), null);
					} else {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (partner)",
								"Get "+Sex.getPartner().getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".", Sex.SEX_DIALOGUE){
							@Override
							public void effects(){
								Sex.setUsingItemText(Sex.getPartner().getItemUseEffects(item, Main.game.getPlayer(), Sex.getPartner()));
								Main.game.restoreSavedContent();
								Sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
							}
						};
					}
					
				} else {
					return null;
				}
				
			} else if(Main.game.isInCombat()){
				if (index == 0) {
					return getCloseInventoryResponse();
				} else if (index == 1) {
					if (!item.isAbleToBeUsed(Main.game.getPlayer())) {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
								item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
					} else {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (self)",
								Util.capitaliseSentence(item.getItemType().getUseName()) + " the " + item.getName() + ".", Combat.ENEMY_ATTACK){
							@Override
							public void effects(){
								Combat.setPlayerTurnText(Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false));
								Combat.attackEnemy();
								Combat.setPreviousAction(Attack.NONE);
								Main.game.restoreSavedContent();
							}
						};
					}
	
				} else if (index == 2) {
					if (!item.isAbleToBeUsed(Combat.getOpponent())) {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (opponent)",
								item.getUnableToBeUsedDescription(Combat.getOpponent()), null);
					} else {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()) +" (opponent)",
								"Get "+Combat.getOpponent().getName("the")+" to "+ item.getItemType().getUseName() + " the " + item.getName() + ".", Combat.ENEMY_ATTACK){
							@Override
							public void effects(){
								Combat.setPlayerTurnText(Main.game.getPlayer().useItem(item, Combat.getOpponent(), false));
								Combat.attackEnemy();
								Combat.setPreviousAction(Attack.NONE);
								Main.game.restoreSavedContent();
							}
						};
					}
					
				} else {
					return null;
				}
				
			} else {
				// Use item:
				if (index == 1) {
					if (!item.isAbleToBeUsedFromInventory()) {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()), item.getUnableToBeUsedFromInventoryDescription(), null);
						
					}else if(!item.isAbleToBeUsed(Main.game.getPlayer())) {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()), item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
						
					} else {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()),
								Util.capitaliseSentence(item.getItemType().getUseName()) + " the " + item.getName() + ".", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
							}
						};
					}
	
				// Drop or sell item:
				}  else if (index == 2) {
					if(InventoryDialogue.getInventoryNPC()!=null) {
						if (InventoryDialogue.getInventoryNPC().willBuy(item)) {
							int sellPrice = item.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier());
							return new Response("Sell (" + UtilText.formatAsMoney(sellPrice, "span") + ")", "Sell the " + item.getName() + " for " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextStartStringBuilder().append(sellItem(item));
								}
							};
						} else {
							return new Response("Sell", InventoryDialogue.getInventoryNPC().getName("The") + " doesn't want to buy this.", null);
						}
						
					} else {
						boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasItem(item);
						if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
							if(areaFull) {
								return new Response("Drop", "This area is full, so you can't drop your " + item.getName() + " here!", null);
							} else {
								return new Response("Drop", "Drop your " + item.getName() + ".", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropItem(item) + "</p>");
									}
								};
							}
						} else {
							if(areaFull) {
								return new Response("Store", "This area is full, so you can't store your " + item.getName() + " here!", null);
							} else {
								return new Response("Store", "Store the " + item.getName() + " in this area.", INVENTORY_MENU){
									@Override
									public void effects(){
										Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropItem(item) + "</p>");
									}
								};
							}
						}
					}
					
				// Mass drop or sell items:
				} else if (index == 3 && Main.game.getPlayer().getItemCount(item)>1) {
					if(InventoryDialogue.getInventoryNPC()!=null) {
						if (InventoryDialogue.getInventoryNPC().willBuy(item)) {
							int sellPrice = item.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier()) * Main.game.getPlayer().getItemCount(item);
							return new Response("Sell all (" + UtilText.formatAsMoney(sellPrice, "span") + ")",
									"Sell all of your " + item.getName() + (item.getItemType().isPlural()?"":"s")+" for " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									int itemCount = Main.game.getPlayer().getItemCount(item);
									
									Main.game.getTextStartStringBuilder().append(
											"<p style='text-align:center;'>"
												+ "You hand over all of your <b>" + item.getDisplayName(true) + "</b> to " + InventoryDialogue.getInventoryNPC().getName("the") + " in exchange for " + UtilText.formatAsMoney(sellPrice) + "."
											+ "</p>");
									
									for(int i = 0; i < itemCount; i++) {
										sellItem(item);
									}
								}
							};
						} else {
							return new Response("Sell all", InventoryDialogue.getInventoryNPC().getName("The") + " doesn't want to buy this.", null);
						}
						
					} else {
						boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasItem(item);
						if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
							if(areaFull) {
								return new Response("Drop all", "This area is full, so you can't drop your " + item.getName() + " here!", null);
							} else {
								return new Response("Drop all", "Drop all of your " + item.getName() + (item.getItemType().isPlural()?"":"s")+".", INVENTORY_MENU){
									@Override
									public void effects(){
										int itemCount = Main.game.getPlayer().getItemCount(item);
										
										Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropItem(item) + "</p>");
										for(int i = itemCount-1; i > 0; i--)
											Main.game.getPlayer().dropItem(item);
									}
								};
							}
						} else {
							if(areaFull) {
								return new Response("Store all", "This area is full, so you can't store your " + item.getName() + " here!", null);
							} else {
								return new Response("Store all", "Store all of your " + item.getName() + (item.getItemType().isPlural()?"":"s")+" in this area.", INVENTORY_MENU){
									@Override
									public void effects(){
										int itemCount = Main.game.getPlayer().getItemCount(item);
										
										Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropItem(item) + "</p>");
										for(int i = itemCount-1; i > 0; i--)
											Main.game.getPlayer().dropItem(item);
									}
								};
							}
						}
						
					}
					
				// Use all items:
				} else if (index == 4  && Main.game.getPlayer().getItemCount(item)>1) {
					if (!item.isAbleToBeUsedFromInventory()) {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()), item.getUnableToBeUsedFromInventoryDescription(), null);
						
					}else if(!item.isAbleToBeUsed(Main.game.getPlayer())) {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName()), item.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
						
					} else {
						return new Response(Util.capitaliseSentence(item.getItemType().getUseName())+" all",
								Util.capitaliseSentence(item.getItemType().getUseName()) + " all of the " + item.getName() + " that are currently in your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								int itemCount = Main.game.getPlayer().getItemCount(item);
								for(int i=0;i<itemCount;i++) {
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(item, Main.game.getPlayer(), false) + "</p>");
								}
							}
						};
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
					
				} else if (index == 8 && InventoryDialogue.getInventoryNPC()!=null) {
					return getBuybackResponse();
				} else if (index == 9) {
					return getQuickTradeResponse();
				} else if (index == 0) {
					return getCloseInventoryResponse();
				} else
					return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};
	public static final DialogueNodeOld WEAPON_INVENTORY = new DialogueNodeOld("Weapon", "", true) {
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
							+ weapon.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+weapon.getDisplayName(true)+"</b></p>"
					+ weapon.getDescription()
					+ (InventoryDialogue.getInventoryNPC() != null ? 
							InventoryDialogue.getInventoryNPC().willBuy(weapon) ? 
									"<p>" + InventoryDialogue.getInventoryNPC().getName("The") + " will buy it for " + UtilText.formatAsMoney(weapon.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier())) + ".</p>" 
							: InventoryDialogue.getInventoryNPC().getName("The") + " doesn't want to buy this."
						: "");
		}
		
		@Override
		public Response getResponse(int index) {
			// Use item:
			if (index == 1) {
				return new Response("Equip", "Equip the " + weapon.getName() + ".", INVENTORY_MENU){
					@Override
					public void effects(){
						Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"
								+ (weapon.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
									?Main.game.getPlayer().equipMainWeapon(weapon, false)
									:Main.game.getPlayer().equipOffhandWeapon(weapon, false))
								+ "</p>");
					}
				};

			// Drop or sell weapon:
			} else if (index == 2) {
				if(InventoryDialogue.getInventoryNPC()!=null) {
					if (InventoryDialogue.getInventoryNPC().willBuy(weapon)) {
						int sellPrice = weapon.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier());
						return new Response("Sell (" + UtilText.formatAsMoney(sellPrice, "span") + ")",
								"Sell the " + weapon.getName() + " for " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(sellWeapon(weapon));
							}
						};
					} else {
						return new Response("Sell", InventoryDialogue.getInventoryNPC().getName("The") + " doesn't want to buy this.", null);
					}
					
				} else {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasWeapon(weapon);
					if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
						if(areaFull) {
							return new Response("Drop", "This area is full, so you can't drop your " + weapon.getName() + " here!", null);
						} else {
							return new Response("Drop", "Drop your " + weapon.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropWeapon(weapon) + "</p>");
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
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropWeapon(weapon) + "</p>");
								}
							};
						}
					}
				}
				
			// Mass drop or sell weapons:
			} else if (index == 3 && Main.game.getPlayer().getWeaponCount(weapon)>1) {
				if(InventoryDialogue.getInventoryNPC()!=null) {
					if (InventoryDialogue.getInventoryNPC().willBuy(weapon)) {
						int sellPrice = weapon.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier()) * Main.game.getPlayer().getWeaponCount(weapon);
						return new Response("Sell all (" + UtilText.formatAsMoney(sellPrice, "span") + ")",
								"Sell all of your " + weapon.getName() +"s for " + UtilText.formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
							@Override
							public void effects(){
								int itemCount = Main.game.getPlayer().getWeaponCount(weapon);
								
								Main.game.getTextStartStringBuilder().append(
										"<p style='text-align:center;'>"
											+ "You hand over all of your <b>" + weapon.getDisplayName(true) + "</b> to " + InventoryDialogue.getInventoryNPC().getName("the") + " in exchange for " + UtilText.formatAsMoney(sellPrice) + "."
										+ "</p>");
								
								for(int i = 0; i < itemCount; i++) {
									sellWeapon(weapon);
								}
							}
						};
					} else {
						return new Response("Sell all", InventoryDialogue.getInventoryNPC().getName("The") + " doesn't want to buy this.", null);
					}
					
				} else {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasWeapon(weapon);
					if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
						if(areaFull) {
							return new Response("Drop all", "This area is full, so you can't drop your " + weapon.getName() + "s here!", null);
						} else {
							return new Response("Drop all", "Drop all of your " + weapon.getName() + "s.", INVENTORY_MENU){
								@Override
								public void effects(){
									int itemCount = Main.game.getPlayer().getWeaponCount(weapon);
									
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropWeapon(weapon) + "</p>");
									for(int i = itemCount-1; i > 0; i--)
										Main.game.getPlayer().dropWeapon(weapon);
								}
							};
						}
						
					} else {
						if(areaFull) {
							return new Response("Store all", "This area is full, so you can't store your " + weapon.getName() + "s here!", null);
						} else {
							return new Response("Store all", "Store all of your " + weapon.getName() + "s in this area.", INVENTORY_MENU){
								@Override
								public void effects(){
									int itemCount = Main.game.getPlayer().getWeaponCount(weapon);
									
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropWeapon(weapon) + "</p>");
									for(int i = itemCount-1; i > 0; i--)
										Main.game.getPlayer().dropWeapon(weapon);
								}
							};
						}
					}
				}
				
			} else if (index == 4) {
				if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_JINXED_CLOTHING, Quest.SIDE_JINXED_LILAYA_HELP)){
					return new Response("Remove jinx", "Proceed to the jinxed clothing choice menu.", REMOVE_JINX){
						@Override
						public void effects() {
							jinxRemovalFromFloor = false;
						}
					};
				}
				
				return null;
				
			} else if (index == 8 && InventoryDialogue.getInventoryNPC()!=null) {
				return getBuybackResponse();
			} else if (index == 9) {
				return getQuickTradeResponse();
			} else if (index == 0) {
				return getCloseInventoryResponse();
			} else
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
					+ (InventoryDialogue.getInventoryNPC() != null ? 
							InventoryDialogue.getInventoryNPC().willBuy(clothing) ? 
									"<p>" + InventoryDialogue.getInventoryNPC().getName("The") + " will buy it for " + UtilText.formatAsMoney(clothing.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier())) + ".</p>" 
							: InventoryDialogue.getInventoryNPC().getName("The") + " doesn't want to buy this."
						: "");
		}
		
		@Override
		public Response getResponse(int index) {
			// Equip:
			if (index == 1) {
				return new Response("Equip", "Equip the " + clothing.getName() + ".", INVENTORY_MENU){
					@Override
					public void effects(){
						Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().equipClothingFromInventory(clothing, true, Main.game.getPlayer()) + "</p>");
						populateJinxedClothingList();
					}
				};

			// Drop:
			} else if (index == 2) {
				if(InventoryDialogue.getInventoryNPC()!=null) {
					if (InventoryDialogue.getInventoryNPC().willBuy(clothing)) {
						int itemPrice = clothing.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier());
						return new Response("Sell (" + UtilText.formatAsMoney(itemPrice, "span") + ")",
								"Sell your " + clothing.getName() + " for " + UtilText.formatAsMoney(itemPrice) + ".", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(sellClothing(clothing));
							}
						};
					} else {
						return new Response("Sell", InventoryDialogue.getInventoryNPC().getName("The") + " doesn't want to buy this.", null);
					}
					
				} else {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasClothing(clothing);
					if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
						if(areaFull) {
							return new Response("Drop", "This area is full, so you can't drop your " + clothing.getName() + " here!", null);
						} else {
							return new Response("Drop", "Drop your " + clothing.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropClothing(clothing) + "</p>");
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
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropClothing(clothing) + "</p>");
								}
							};
						}
					}
				}
				
			// Mass drop or sell items:
			} else if (index == 3 && Main.game.getPlayer().getClothingCount(clothing)>1) {
				if(InventoryDialogue.getInventoryNPC()!=null) {
					if (InventoryDialogue.getInventoryNPC().willBuy(clothing)) {
						int itemPrice = clothing.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier()) * Main.game.getPlayer().getClothingCount(clothing);
						return new Response("Sell all (" + UtilText.formatAsMoney(itemPrice, "span") + ")",
								"Sell all of your " + clothing.getName() +" for " + UtilText.formatAsMoney(itemPrice) + ".", INVENTORY_MENU){
							@Override
							public void effects(){
								int itemCount = Main.game.getPlayer().getClothingCount(clothing);
								
								Main.game.getTextStartStringBuilder().append(
										"<p style='text-align:center;'>"
											+ "You hand over all of your <b>" + clothing.getDisplayName(true) + "</b> to " + InventoryDialogue.getInventoryNPC().getName("the") + " in exchange for " + UtilText.formatAsMoney(itemPrice) + "."
										+ "</p>");
								
								for(int i = 0; i < itemCount; i++) {
									sellClothing(clothing);
								}
							}
						};
					} else {
						return new Response("Sell all", InventoryDialogue.getInventoryNPC().getName("The") + " doesn't want to buy this.", null);
					}
					
				} else {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasClothing(clothing);
					if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
						if(areaFull) {
							return new Response("Drop all", "This area is full, so you can't drop your " + clothing.getName() + " here!", null);
						} else {
							return new Response("Drop all", "Drop all of your " + clothing.getName() + "s.", INVENTORY_MENU){
								@Override
								public void effects(){
									int itemCount = Main.game.getPlayer().getClothingCount(clothing);
									
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropClothing(clothing) + "</p>");
									for(int i = itemCount-1; i > 0; i--)
										Main.game.getPlayer().dropClothing(clothing);
								}
							};
						}
						
					} else {
						if(areaFull) {
							return new Response("Store all", "This area is full, so you can't store your " + clothing.getName() + " here!", null);
						} else {
							return new Response("Store all", "Store all of your " + clothing.getName() + "s in this area.", INVENTORY_MENU){
								@Override
								public void effects(){
									int itemCount = Main.game.getPlayer().getClothingCount(clothing);
									
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropClothing(clothing) + "</p>");
									for(int i = itemCount-1; i > 0; i--)
										Main.game.getPlayer().dropClothing(clothing);
								}
							};
						}
					}
				}
				
			// Dye:
			} else if (index == 4) {
				if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)) {
					boolean hasFullInventory = Main.game.getPlayer().isInventoryFull();
					boolean isDyeingStackItem = Main.game.getPlayer().getMapOfDuplicateClothing().get(clothing) > 1;
					boolean canDye = !(isDyeingStackItem && hasFullInventory);
					if (canDye) {
						return new Response("Dye", "Use a Dye-brush to dye this item of clothing.", DYE_CLOTHING);
					} else {
						return new Response("Dye", "Your inventory is full, so you can't dye this item of clothing.", null);
					}
				} else {
					if (Main.getProperties().isItemDiscovered(ItemType.DYE_BRUSH))
						return new Response("Dye", "You'll need to find another Dye-brush if you want to dye your clothes.", null);
					else
						return new Response("Dye", "You'll need to find an item of some sort if you want to dye your clothes.", null);
				}
				
			// Identify:
			} else if (index == 5 && InventoryDialogue.getInventoryNPC()!=null && !clothing.isEnchantmentKnown()) {
				if(!InventoryDialogue.getInventoryNPC().willBuy(clothing)) {
					return new Response("Identify", InventoryDialogue.getInventoryNPC().getName("The") + " can't identify clothing!", null);
					
				} else if(Main.game.getPlayer().getMoney() < IDENTIFICATION_PRICE){
					// don't format as money because we don't want to highlight non-selectable choices
					return new Response("Identify (" + Main.game.getCurrencySymbol() + " " + IDENTIFICATION_PRICE + ")", "You don't have enough money!", null);
					
				}else {
					return new Response("Identify (" + UtilText.formatAsMoney(IDENTIFICATION_PRICE, "span") + ")",
								"Have the " + clothing.getName() + " identified for " + UtilText.formatAsMoney(IDENTIFICATION_PRICE, "span") + ".", INVENTORY_MENU){
						@Override
						public void effects(){
							Main.game.getPlayer().removeClothing(clothing);
							Main.game.getTextStartStringBuilder().append(
									"<p style='text-align:center;'>" + "You hand over " + UtilText.formatAsMoney(IDENTIFICATION_PRICE) + " to "
											+InventoryDialogue.getInventoryNPC().getName("the")+", who promptly identifies your "+clothing.getName()+"."
									+ "</p>"
									+clothing.setEnchantmentKnown(true));
							
							Main.game.getPlayer().addClothing(clothing, false);
							Main.game.getPlayer().incrementMoney(-IDENTIFICATION_PRICE);
						}
					};
				}

			} else if (index == 8 && InventoryDialogue.getInventoryNPC()!=null) {
				return getBuybackResponse();
			} else if (index == 9) {
				return getQuickTradeResponse();
			} else if (index == 0) {
				return getCloseInventoryResponse();
			} else
				return null;
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};

	public static final DialogueNodeOld ITEM_TRADER = new DialogueNodeOld("Item trader", "", true) {
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
			int itemPrice = buyback ? buyBackPrice : item.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier());
			return "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ item.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+item.getDisplayName(true)+"</b></p>"
					+ "<p>" + item.getDescription() + "</p>" + item.getExtraDescription(Main.game.getPlayer(), Main.game.getPlayer()) + "<p>" + InventoryDialogue.getInventoryNPC().getName("The")
					+ " will sell it to you for " + UtilText.formatAsMoney(itemPrice) + "." + "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			// Use item:
			boolean noSlotForItem = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item);
			if (index == 1) {
				if (buyback) {
					if (noSlotForItem) {
						return new Response("Buy-back", "Your inventory is full, so you can't buy back the " + item.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < buyBackPrice) {
						return new Response("Buy-back", "You don't have enough money to buy back the " + item.getName() + "!", null);
						
					} else {
						return new Response("Buy-back",
								"Buy back the " + item.getName() + " for " + UtilText.formatAsMoney(buyBackPrice) + " and add it to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyBackItem(item, buyBackPrice, buyBackIndex));
							}
						};
					}
					
				} else {
					int itemPrice = item.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier());
					if (noSlotForItem) {
						return new Response("Buy", "Your inventory is full, so you can't buy the " + item.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < itemPrice) {
						return new Response("Buy", "You don't have enough money to buy the " + item.getName() + "!", null);
						
					} else {
						return new Response("Buy (" + UtilText.formatAsMoney(itemPrice, "span") + ")",
								"Buy the " + item.getName() + " for " + UtilText.formatAsMoney(itemPrice) + " and add it to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyItem(item));
							}
						};
					}
				}
				
			} else if (index == 2 && InventoryDialogue.getInventoryNPC().getItemCount(item)>1) {
				if (!buyback) {
					int totalPrice = item.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier()) * InventoryDialogue.getInventoryNPC().getItemCount(item);
					if (noSlotForItem) {
						return new Response("Buy all", "Your inventory is full, so you can't buy the " + item.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < totalPrice) {
						
						return new Response("Buy all", "You don't have enough money to buy the " + item.getName() + "!", null);
						
					} else {
						return new Response("Buy all (" + UtilText.formatAsMoney(totalPrice, "span") + ")",
								"Buy all of the " + item.getName() + " for " + UtilText.formatAsMoney(totalPrice) + " and add them to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyAllItems(item));
							}
						};
					}
				} else {
					return null;
				}
				
			} else if (index == 8 && InventoryDialogue.getInventoryNPC()!=null) {
				return getBuybackResponse();
			} else if (index == 9) {
				return getQuickTradeResponse();
			} else if (index == 0) {
				return getCloseInventoryResponse();
			} else
				return null;
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};
	public static final DialogueNodeOld WEAPON_TRADER = new DialogueNodeOld("Weapon trader", "", true) {
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
			int itemPrice = buyback ? buyBackPrice : weapon.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier());
			return "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ weapon.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+weapon.getDisplayName(true)+"</b></p>"
					+ "</div>" + weapon.getDescription() + "<p>" + InventoryDialogue.getInventoryNPC().getName("The") + " will sell it to you for " 
					+ UtilText.formatAsMoney(itemPrice) + ".</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			// Use item:
			boolean noSlotForItem = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon);
			if (index == 1) {
				if (buyback) {
					if (noSlotForItem) {
						return new Response("Buy-back", "Your inventory is full, so you can't buy back the " + weapon.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < buyBackPrice) {
						return new Response("Buy-back", "You don't have enough money to buy back the " + weapon.getName() + "!", null);
						
					} else {
						return new Response("Buy-back",
								"Buy back the " + weapon.getName() + " for " + UtilText.formatAsMoney(buyBackPrice) + " and add it to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyBackWeapon(weapon, buyBackPrice, buyBackIndex));
							}
						};
					}
					
				} else {
					int itemPrice = weapon.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier());
					if (noSlotForItem) {
						return new Response("Buy", "Your inventory is full, so you can't buy the " + weapon.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < itemPrice) {
						return new Response("Buy", "You don't have enough money to buy the " + weapon.getName() + "!", null);
						
					} else {
						return new Response("Buy (" + UtilText.formatAsMoney(itemPrice, "span") + ")",
								"Buy the " + weapon.getName() + " for " + UtilText.formatAsMoney(itemPrice) + " and add it to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyWeapon(weapon));
							}
						};
					}
				}
				
			} else if (index == 2 && InventoryDialogue.getInventoryNPC().getWeaponCount(weapon)>1) {
				if (!buyback) {
					int totalPrice = weapon.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier()) * InventoryDialogue.getInventoryNPC().getWeaponCount(weapon);
					if (noSlotForItem) {
						return new Response("Buy all", "Your inventory is full, so you can't buy the " + weapon.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < totalPrice) {
						
						return new Response("Buy all", "You don't have enough money to buy the " + weapon.getName() + "!", null);
						
					} else {
						return new Response("Buy all (" + UtilText.formatAsMoney(totalPrice, "span") + ")",
								"Buy all of the " + weapon.getName() + " for " + UtilText.formatAsMoney(totalPrice) + " and add them to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyAllWeapons(weapon));
							}
						};
					}
				} else {
					return null;
				}
				
			} else if (index == 8 && InventoryDialogue.getInventoryNPC()!=null) {
				return getBuybackResponse();
			} else if (index == 9) {
				return getQuickTradeResponse();
			} else if (index == 0) {
				return getCloseInventoryResponse();
			} else
				return null;
		}
		

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};
	public static final DialogueNodeOld CLOTHING_TRADER = new DialogueNodeOld("Clothing trader", "", true) {
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
			int itemPrice = buyback ? buyBackPrice : clothing.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier());
			return "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ clothing.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+clothing.getDisplayName(true)+"</b></p>"
					+ clothing.getDescription() + clothing.clothingExtraInformation(null) + "<p>" + InventoryDialogue.getInventoryNPC().getName("The")
					+ " will sell it to you for " + UtilText.formatAsMoney(itemPrice) + ".</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			// Use item:
			boolean noSlotForItem = Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing);
			if (index == 1) {
				if (buyback) {
					if (noSlotForItem) {
						return new Response("Buy-back", "Your inventory is full, so you can't buy back the " + clothing.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < buyBackPrice) {
						return new Response("Buy-back", "You don't have enough money to buy back the " + clothing.getName() + "!", null);
						
					} else {
						return new Response("Buy-back",
								"Buy back the " + clothing.getName() + " for " + UtilText.formatAsMoney(buyBackPrice) + " and add it to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyBackClothing(clothing, buyBackPrice, buyBackIndex));
							}
						};
					}
					
				} else {
					int itemPrice = clothing.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier());
					if (noSlotForItem) {
						return new Response("Buy", "Your inventory is full, so you can't buy the " + clothing.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < itemPrice) {
						return new Response("Buy", "You don't have enough money to buy the " + clothing.getName() + "!", null);
						
					} else {
						return new Response("Buy (" + UtilText.formatAsMoney(itemPrice, "span") + ")",
								"Buy the " + clothing.getName() + " for " + UtilText.formatAsMoney(itemPrice) + " and add it to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyClothing(clothing));
							}
						};
					}
				}
				
			} else if (index == 2 && InventoryDialogue.getInventoryNPC().getClothingCount(clothing)>1) {
				if (!buyback) {
					int totalPrice = clothing.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier()) * InventoryDialogue.getInventoryNPC().getClothingCount(clothing);
					if (noSlotForItem) {
						return new Response("Buy all", "Your inventory is full, so you can't buy the " + clothing.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < totalPrice) {
						
						return new Response("Buy all", "You don't have enough money to buy the " + clothing.getName() + "!", null);
						
					} else {
						return new Response("Buy all (" + UtilText.formatAsMoney(totalPrice, "span") + ")",
								"Buy all of the " + clothing.getName() + " for " + UtilText.formatAsMoney(totalPrice) + " and add them to your inventory.",
										INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyAllClothing(clothing));
							}
						};
					}
				} else {
					return null;
				}
				
			} else if (index == 8 && InventoryDialogue.getInventoryNPC()!=null) {
				return getBuybackResponse();
			} else if (index == 9) {
				return getQuickTradeResponse();
			} else if (index == 0) {
				return getCloseInventoryResponse();
			} else
				return null;
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};

	public static final DialogueNodeOld ITEM_FLOOR = new DialogueNodeOld("Item floor", "", true) {
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
							+ itemFloor.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+itemFloor.getDisplayName(true)+"</b></p>"
					+ itemFloor.getDescription() + itemFloor.getExtraDescription(Main.game.getPlayer(), Main.game.getPlayer());
		}
		
		@Override
		public Response getResponse(int index) {
			// Use item:
			if (index == 1) {
				if (!itemFloor.isAbleToBeUsedFromInventory()) {
					return new Response(Util.capitaliseSentence(itemFloor.getItemType().getUseName()), itemFloor.getUnableToBeUsedFromInventoryDescription(), null);
					
				}else if(!itemFloor.isAbleToBeUsed(Main.game.getPlayer())) {
					return new Response(Util.capitaliseSentence(itemFloor.getItemType().getUseName()), itemFloor.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
					
				} else {
					return new Response(Util.capitaliseSentence(itemFloor.getItemType().getUseName()),
							Util.capitaliseSentence(itemFloor.getItemType().getUseName()) + " the " + itemFloor.getName() + ".", INVENTORY_MENU){
						@Override
						public void effects(){
							Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(itemFloor, Main.game.getPlayer(), true) + "</p>");
						}
					};
				}

			// Take item:
			} else if (index == 2) {

				if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(itemFloor)) {
					return new Response("Take", "Your inventory is full, so you can't take the " + itemFloor.getName() + "!", null);
				} else {
					return new Response("Take", "Take the " + itemFloor.getName() + ".", INVENTORY_MENU){
						@Override
						public void effects(){
							Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().addItem(itemFloor, true) + "</p>");
						}
					};
				}

			} else if (index == 3 && Main.game.getPlayerCell().getInventory().getItemCount(itemFloor)>1) {
				
				if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(itemFloor)) {
					return new Response("Take all", "Your inventory is full, so you can't take the " + itemFloor.getName() + "!", null);
				} else {
					return new Response("Take all", "Take the " + itemFloor.getName() + ".", INVENTORY_MENU){
						@Override
						public void effects(){
							int itemCount = Main.game.getPlayerCell().getInventory().getItemCount(itemFloor);
							
							Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().addItem(itemFloor, true) + "</p>");
							for(int i = itemCount-1; i > 0; i--)
								Main.game.getPlayer().addItem(itemFloor, true);
						}
					};
				}

			} else if (index == 4 && Main.game.getPlayerCell().getInventory().getItemCount(itemFloor)>1) {
				if (!itemFloor.isAbleToBeUsedFromInventory()) {
					return new Response(Util.capitaliseSentence(itemFloor.getItemType().getUseName()), itemFloor.getUnableToBeUsedFromInventoryDescription(), null);
					
				}else if(!itemFloor.isAbleToBeUsed(Main.game.getPlayer())) {
					return new Response(Util.capitaliseSentence(itemFloor.getItemType().getUseName()), itemFloor.getUnableToBeUsedDescription(Main.game.getPlayer()), null);
					
				} else {
					return new Response(Util.capitaliseSentence(itemFloor.getItemType().getUseName())+" all",
							Util.capitaliseSentence(itemFloor.getItemType().getUseName()) + " all of the " + itemFloor.getName() + ".", INVENTORY_MENU){
						@Override
						public void effects(){
							int itemCount = Main.game.getPlayerCell().getInventory().getItemCount(itemFloor);
							for(int i=0;i<itemCount;i++) {
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().useItem(itemFloor, Main.game.getPlayer(), true) + "</p>");
							}
						}
					};
				}
			
			} else if (index == 9) {
				return getQuickTradeResponse();
			} else if (index == 0) {
				return getCloseInventoryResponse();
			} else
				return null;
		
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};
	public static final DialogueNodeOld WEAPON_FLOOR = new DialogueNodeOld("Weapon floor", "", true) {
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
							+ weaponFloor.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+weaponFloor.getDisplayName(true)+"</b></p>"
					+ weaponFloor.getDescription();
		}
		
		@Override
		public Response getResponse(int index) {
			// Use item:
			if (index == 1) {
				return new Response("Equip", "Equip the " + weaponFloor.getName() + ".", INVENTORY_MENU){
					@Override
					public void effects(){
						Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"
								+ (weaponFloor.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
									?Main.game.getPlayer().equipMainWeapon(weaponFloor, true)
									:Main.game.getPlayer().equipOffhandWeapon(weaponFloor, true))
								+ "</p>");
					}
				};

			// Take item:
			} else if (index == 2) {
				if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weaponFloor))
					return new Response("Take", "Your inventory is full, so you can't take the " + weaponFloor.getName() + "!", null);
				else
					return new Response("Take", "Take the " + weaponFloor.getName() + ".", INVENTORY_MENU){
						@Override
						public void effects(){
							Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().addWeapon(weaponFloor, true) + "</p>");
						}
					};

			} else if (index == 3 && Main.game.getPlayerCell().getInventory().getWeaponCount(weaponFloor)>1) {
				
				if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weaponFloor))
					return new Response("Take all", "Your inventory is full, so you can't take the " + weaponFloor.getName() + "!", null);
				else
					return new Response("Take all", "Take the " + weaponFloor.getName() + ".", INVENTORY_MENU){
						@Override
						public void effects(){
							int itemCount = Main.game.getPlayerCell().getInventory().getWeaponCount(weaponFloor);
							
							Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().addWeapon(weaponFloor, true) + "</p>");
							for(int i = itemCount-1; i > 0; i--)
								Main.game.getPlayer().addWeapon(weaponFloor, true);
						}
					};

			} else if (index == 4) {
				if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING)){
					if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_JINXED_CLOTHING, Quest.SIDE_JINXED_LILAYA_HELP)){
						return new Response("Remove jinx", "Proceed to the jinxed clothing choice menu.", REMOVE_JINX){
							@Override
							public void effects() {
								jinxRemovalFromFloor=true;
							}
						};
					}
				}
				
				return null;
				
			} else if (index == 9) {
				return getQuickTradeResponse();
			} else if (index == 0) {
				return getCloseInventoryResponse();
			} else
				return null;
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};
	public static final DialogueNodeOld CLOTHING_FLOOR = new DialogueNodeOld("Clothing floor", "", true) {
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
							+ clothingFloor.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+clothingFloor.getDisplayName(true)+"</b></p>"
					+ clothingFloor.getDescription() + clothingFloor.clothingExtraInformation(null);
		}
		
		@Override
		public Response getResponse(int index) {
			// Use item:
			if (index == 1) {
				return new Response("Equip", "Equip the " + clothingFloor.getName() + ".", INVENTORY_MENU){
					@Override
					public void effects(){
						Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().equipClothingFromGround(clothingFloor, true, Main.game.getPlayer()) + "</p>");
						populateJinxedClothingList();
					}
				};

			// Take item:
			} else if (index == 2) {
				if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothingFloor)) {
					return new Response("Take", "Your inventory is full, so you can't take the " + clothingFloor.getName() + "!", null);
				} else {
					return new Response("Take", "Take the " + clothingFloor.getName() + ".", INVENTORY_MENU){
						@Override
						public void effects(){
							Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().addClothing(clothingFloor, true) + "</p>");
						}
					};
				}
				
			} else if (index == 3 && Main.game.getPlayerCell().getInventory().getClothingCount(clothingFloor)>1) {
				
				if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothingFloor))
					return new Response("Take all", "Your inventory is full, so you can't take the " + clothingFloor.getName() + "!", null);
				else
					return new Response("Take all", "Take the " + clothingFloor.getName() + ".", INVENTORY_MENU){
						@Override
						public void effects(){
							int itemCount = Main.game.getPlayerCell().getInventory().getClothingCount(clothingFloor);
							
							Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().addClothing(clothingFloor, true) + "</p>");
							for(int i = itemCount-1; i > 0; i--)
								Main.game.getPlayer().addClothing(clothingFloor, true);
						}
					};

			} else if (index == 9) {
				return getQuickTradeResponse();
			} else if (index == 0) {
				return getCloseInventoryResponse();
			} else
				return null;
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};

	public static final DialogueNodeOld WEAPON_EQUIPPED = new DialogueNodeOld("Weapon equipped", "", true) {
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
							+ weaponEquipped.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+weaponEquipped.getDisplayName(true)+"</b></p>"
					+ weaponEquipped.getDescription();
		}
		
		@Override
		public Response getResponse(int index) {
			// Unequip item:
			if (index == 1) {
				if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weaponEquipped))
					return new Response("Unequip", "Your inventory is full, so you can't unequip the " + weaponEquipped.getName() + " into your inventory!", null);
				else
					return new Response("Unequip", "Unequip the " + weaponEquipped.getName() + ".", INVENTORY_MENU){
						@Override
						public void effects(){
						Main.game.getTextStartStringBuilder().append(
									"<p style='text-align:center;'>"
										+ (weaponEquipped.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
												?Main.game.getPlayer().unequipMainWeapon(false)
												:Main.game.getPlayer().unequipOffhandWeapon(false))
									+ "</p>");
						}
					};

			// Drop item:
			} else if (index == 2) {
				boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasWeapon(weaponEquipped);
				if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
					if(areaFull) {
						return new Response("Drop", "This area is full, so you can't drop your " + weaponEquipped.getName() + " here!", null);
					} else {
						return new Response("Drop", "Drop your " + weaponEquipped.getName() + ".", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(
											"<p style='text-align:center;'>"
												+ (weaponEquipped.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
													?Main.game.getPlayer().unequipMainWeapon(true)
													:Main.game.getPlayer().unequipOffhandWeapon(true))
											+ "</p>");
							}
						};
					}
					
				} else {
					if(areaFull) {
						return new Response("Store", "This area is full, so you can't store your " + weaponEquipped.getName() + " here!", null);
					} else {
						return new Response("Store", "Store your " + weaponEquipped.getName() + " in this area.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(
											"<p style='text-align:center;'>"
												+ (weaponEquipped.getWeaponType().getSlot()==InventorySlot.WEAPON_MAIN
													?Main.game.getPlayer().unequipMainWeapon(true)
													:Main.game.getPlayer().unequipOffhandWeapon(true))
											+ "</p>");
							}
						};
					}
				}

			} else if (index == 9) {
				return getQuickTradeResponse();
			} else if (index == 0) {
				return getCloseInventoryResponse();
			} else
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
							+ clothingEquipped.getSVGString()
						+ "</div>"
					+ "</div>"
					+ "<p><b>"+clothingEquipped.getDisplayName(true)+"</b></p>"
					+ clothingEquipped.getDescription()
					+ clothingEquipped.clothingExtraInformation((Main.game.isInSex()?ownerInSex:Main.game.getPlayer()))
					+ (Main.game.isInSex()||Main.game.isInCombat()?clothingEquipped.getDisplacementBlockingDescriptions(ownerInSex):"");
		}
		
		@Override
		public Response getResponse(int index) {
			// Handle clothing while in sex a little differently: TODO "as other clothing is in the way" needs to be more specific.
			if (Main.game.isInSex()) {
				if (index == 1) {
					if (ownerInSex.isAbleToUnequip(clothingEquipped, false, Main.game.getPlayer())) {
						return new Response("Unequip", "Unequip the " + clothingEquipped.getName() + ".", Sex.SEX_DIALOGUE){
							@Override
							public void effects(){
								ownerInSex.unequipClothingOntoFloor(clothingEquipped, true, Main.game.getPlayer());
								Sex.setUnequipClothingText(ownerInSex.getUnequipDescription());
								Main.game.restoreSavedContent();
								Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
								Sex.setSexStarted(true);
							}
						};
					} else {
						return new Response("Unequip", "You can't unequip the " + clothingEquipped.getName() + ", as other clothing is blocking you from doing so!", null);
					}
					
				} else if (index == 2 ) {
					if(ownerInSex.isPlayer()) {
						return new Response("Steal", "You can't steal your own clothing...", null,
								null, CorruptionLevel.FOUR_LUSTFUL, null, null, null);
						
					} else {
						if(ownerInSex instanceof NPC) {
							if(!((NPC)ownerInSex).isClothingStealable()) {
								return new Response("Steal", "You can't steal this character's clothing...", null,
										null, CorruptionLevel.FOUR_LUSTFUL, null, null, null);
							}
						}
						
						if(Sex.isPlayerDom() && !Sex.getSexManager().isConsensualSex()) {
							if (ownerInSex.isAbleToUnequip(clothingEquipped, false, Main.game.getPlayer())) {
								if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothingEquipped)) {
									return new Response("Steal", "Your inventory is full, so you can't steal [npc.name]'s " + clothingEquipped.getName() + "!", null,
											null, CorruptionLevel.FOUR_LUSTFUL, null, null, null);
									
								} else {
									return new Response("Steal", "Steal [npc.name]'s " + clothingEquipped.getName() + ".", Sex.SEX_DIALOGUE,
											null, CorruptionLevel.FOUR_LUSTFUL, null, null, null){
										@Override
										public void effects(){
											ownerInSex.unequipClothingOntoFloor(clothingEquipped, true, Main.game.getPlayer());
											Sex.setUnequipClothingText(ownerInSex.getUnequipDescription()
													+ "<p style='text-align:center;'>" + Main.game.getPlayer().addClothing(clothingEquipped, true) + "</p>");
											Main.game.restoreSavedContent();
											Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
											Sex.setSexStarted(true);
										}
									};
								}
							} else {
								return new Response("Steal", "You can't unequip [npc.name]'s " + clothingEquipped.getName() + ", as other clothing is blocking you from doing so!", null,
										null, CorruptionLevel.FOUR_LUSTFUL, null, null, null);
							}
						} else {
							return new Response("Steal", "You can only steal clothing if you're the dominant partner in non-consensual sex.", null,
									null, CorruptionLevel.FOUR_LUSTFUL, null, null, null);
						}
						
					}
					
				} else if (index == 3) {
					if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)) {
						if(Sex.isPlayerDom() && !Sex.getSexManager().isConsensualSex()) {
							return new Response("Dye", "Use a Dye-brush to dye this item of clothing.", DYE_CLOTHING_SEX);
						} else {
							return new Response("Dye", "You can only dye clothing if you're the dominant partner in non-consensual sex.", null);
						}
					} else {
						if (Main.getProperties().isItemDiscovered(ItemType.DYE_BRUSH))
							return new Response("Dye", "You'll need to find another Dye-brush if you want to dye your clothes.", null);
						else
							return new Response("Dye", "You'll need to find an item of some sort if you want to dye your clothes.", null);
					}
	
				} else if (index != 0 && index - 4 < clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().size()) {
					if(ownerInSex.isAbleToBeDisplaced(clothingEquipped, clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4), false, false, Main.game.getPlayer())){
						return new Response(Util.capitaliseSentence(clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4).getDescription()),
								Util.capitaliseSentence(clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4).getDescription()) + " the " + clothingEquipped.getName() + ". "
										+ clothingEquipped.getClothingBlockingDescription(clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4), ownerInSex,
												" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>This will expose "+(ownerInSex.isPlayer()?"your":"[npc.her]")+" ", ".</span>"),
										Sex.SEX_DIALOGUE){
							@Override
							public void effects(){
								ownerInSex.isAbleToBeDisplaced(clothingEquipped, clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4), true, false, Main.game.getPlayer());
								Sex.setUnequipClothingText(ownerInSex.getDisplaceDescription());
								Main.game.restoreSavedContent();
								Sex.endSexTurn(SexActionUtility.CLOTHING_REMOVAL);
								Sex.setSexStarted(true);
							}
						};
						
					} else {
						return new Response(Util.capitaliseSentence(clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4).getDescription()),
								"You can't "+clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index -4).getDescription() + " the " + clothingEquipped.getName() + ", as other clothing is in the way!", null);
					}
					
				} else if (index == 0){
					return getCloseInventoryResponse();
				} else {
					return null;
				}
				
			// Standard handling of clothing:
			} else {
				if (index == 1) {
					if (clothingEquipped.isSealed()) {
						return new Response("Unequip",
								"<span style='color:" + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>You can't unequip the " + clothingEquipped.getName() + " because " + (clothingEquipped.getClothingType().isPlural() ? "they've" : "it's")
								+ " been jinxed!</span>", null);
						
					} else if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothingEquipped)) {
						return new Response("Unequip", "Your inventory is full, so you can't unequip the " + clothingEquipped.getName() + " into your inventory!", null);
					} else {
						return new Response("Unequip", "Unequip the " + clothingEquipped.getName() + " and add " + (clothingEquipped.getClothingType().isPlural() ? "them" : "it") + " to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().unequipClothingIntoInventory(clothingEquipped, true, Main.game.getPlayer()) + "</p>");
								populateJinxedClothingList();
							}
						};
					}
					
				} else if (index == 2) {
					boolean areaFull = Main.game.isPlayerTileFull() && !Main.game.getCurrentCell().getInventory().hasClothing(clothingEquipped);
					if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
						if (clothingEquipped.isSealed()) {
							return new Response("Drop",
									"<span style='color:" + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>You can't drop the " + clothingEquipped.getName()
									+ " because " + (clothingEquipped.getClothingType().isPlural() ? "they've" : "it's")
									+ " been jinxed!</span>", null);
							
						} else if(areaFull) {
							return new Response("Drop", "This area is full, so you can't drop your " + clothingEquipped.getName() + " here!", null);
							
						} else {
							return new Response("Drop", "Drop your " + clothingEquipped.getName() + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().unequipClothingOntoFloor(clothingEquipped, true, Main.game.getPlayer()) + "</p>");
									populateJinxedClothingList();
								}
							};
						}
						
					} else {
						if (clothingEquipped.isSealed()) {
							return new Response("Store",
									"<span style='color:" + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>You can't store your " + clothingEquipped.getName()
									+ " because " + (clothingEquipped.getClothingType().isPlural() ? "they've" : "it's")
									+ " been jinxed!</span>", null);
							
						} else if(areaFull) {
							return new Response("Store", "This area is full, so you can't store your " + clothingEquipped.getName() + " here!", null);
							
						} else {
							return new Response("Store", "Store your " + clothingEquipped.getName() + " in this area.", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().unequipClothingOntoFloor(clothingEquipped, true, Main.game.getPlayer()) + "</p>");
									populateJinxedClothingList();
								}
							};
						}
					}
	
				} else if (index == 3) {
					if (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)) {
						return new Response("Dye", "Use a Dye-brush to dye this item of clothing.", DYE_EQUIPPED_CLOTHING);
					} else {
						if (Main.getProperties().isItemDiscovered(ItemType.DYE_BRUSH))
							return new Response("Dye", "You'll need to find another Dye-brush if you want to dye your clothes.", null);
						else
							return new Response("Dye", "You'll need to find an item of some sort if you want to dye your clothes.", null);
					}
	
				} else if (index != 0 && index - 4 < clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().size()){

					if (clothingEquipped.getDisplacedList().contains(clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4))) {
						return new Response(Util.capitaliseSentence(clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4).getOppositeDescription()),
								Util.capitaliseSentence(clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4).getOppositeDescription()) + " the " + clothingEquipped.getName() + ". "
										+ clothingEquipped.getClothingBlockingDescription(clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4), Main.game.getPlayer(),
												" <span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>This will cover your ", ".</span>"),
										CLOTHING_EQUIPPED){
							@Override
							public void effects(){
								Main.game.getPlayer().isAbleToBeReplaced(clothingEquipped, clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4), true, true, Main.game.getPlayer());
							}
						};
					} else {
						return new Response(Util.capitaliseSentence(clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4).getDescription()),
								Util.capitaliseSentence(clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4).getDescription()) + " the " + clothingEquipped.getName() + ". "
										+ clothingEquipped.getClothingBlockingDescription(clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4), Main.game.getPlayer(),
												" <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>This will expose your ", ".</span>"),
										CLOTHING_EQUIPPED){
							@Override
							public void effects(){
								Main.game.getPlayer().isAbleToBeDisplaced(clothingEquipped, clothingEquipped.getClothingType().getBlockedPartsKeysAsListWithoutNONE().get(index - 4), true, true, Main.game.getPlayer());
							}
						};
					}
					
				} else if (index == 8 && InventoryDialogue.getInventoryNPC()!=null) {
					return getBuybackResponse();
				} else if (index == 9) {
					return getQuickTradeResponse();
				} else if (index == 0) {
					return getCloseInventoryResponse();
				} else
					return null;
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.INVENTORY;
		}
	};

	public static String jinxedClothingView() {
		inventorySB = new StringBuilder("");

		inventorySB.append("<div class='inventory-contents'>");
		inventorySB.append("<p style='text-align: center;'>" + "<b>Jinxed clothing:</b>"
				+ "</p>");

		inventorySB.append("<div class='inventory-container'>");

		if (!jinxedClothing.isEmpty()) {
			for (int i = 0; i < jinxedClothing.size(); i++) {
				inventorySB.append("<div class='item-background jinxed'>" + jinxedClothing.get(i).getSVGString());
				inventorySB.append("<div class='overlay' id='JINXED_" + i + "'></div>" + "</div>");
			}
		}

		// Fill space:
		for (int i = Main.game.getPlayer().getMaximumInventorySpace(); i > jinxedClothing.size(); i--) {
			inventorySB.append("<div class='item-background empty'></div>");
		}
		
		inventorySB.append("</div>");
		inventorySB.append("<p style='text-align: center;'></p>");
		inventorySB.append("</div>");

		inventorySB.append("<div class='inventory-contents'>" + "<p style='text-align: center;'>");
		inventorySB.append("<b>-</b>");
		inventorySB.append("</p>" + "<div class='inventory-container'>");
		for (int i = 24; i > 0; i--) {
			inventorySB.append("<div class='item-background disabled'></div>");
		}
		inventorySB.append("</div>");
		inventorySB.append("<p style='text-align: center;'></p>");
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
			inventorySB = new StringBuilder("<div class='inventoryImage'>" + clothing.getSVGString() + "</div>" + clothing.getDescription() + clothing.clothingExtraInformation(null) + "<p>"
					+ "Available colours for this item (hover over to view preview):" + "</p>");

			for (Colour c : clothing.getClothingType().getAvailableColours())
				inventorySB.append("<div class='phone-item-colour' id='" + (clothing.getClothingType().toString() + "_" + c.toString()) + "' style='background-color:" + c.toWebHexString() + ";'></div>");

			return inventorySB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous menu.", INVENTORY_MENU);

			} else if (index - 1 < clothing.getClothingType().getAvailableColours().size()) {
				return new Response("Dye: " + Util.capitaliseSentence(clothing.getClothingType().getAvailableColours().get(index - 1).getName()),
						"Dye the " + clothing.getName() + " " + Util.capitaliseSentence(clothing.getClothingType().getAvailableColours().get(index - 1).getName())
							+ ". This action is permanent, and you'll need another Dye-brush if you want to change its colour again.", INVENTORY_MENU){
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
													+ "</b> Dye-brush" + (Main.game.getPlayer().getMapOfDuplicateItems().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
											:"You have <b>0</b> Dye-brushes left!")
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
			inventorySB = new StringBuilder("<div class='inventoryImage'>" + clothingEquipped.getSVGString() + "</div>" + clothingEquipped.getDescription() + clothingEquipped.clothingExtraInformation(Main.game.getPlayer()) + "<p>"
					+ "Available colours for this item (hover over to view preview):" + "</p>");

			for (Colour c : clothingEquipped.getClothingType().getAvailableColours())
				inventorySB.append("<div class='phone-item-colour' id='" + (clothingEquipped.getClothingType().toString() + "_" + c.toString()) + "' style='background-color:" + c.toWebHexString() + ";'></div>");

			return inventorySB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous menu.", CLOTHING_EQUIPPED);

			} else if (index - 1 < clothingEquipped.getClothingType().getAvailableColours().size()) {
				return new Response("Dye: " + Util.capitaliseSentence(clothingEquipped.getClothingType().getAvailableColours().get(index - 1).getName()),
						"Dye the " + clothingEquipped.getName() + " " + Util.capitaliseSentence(clothingEquipped.getClothingType().getAvailableColours().get(index - 1).getName())
						+ ". This action is permanent, and you'll need another Dye-brush if you want to change its colour again.", CLOTHING_EQUIPPED){
					@Override
					public void effects(){
						Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), Main.game.getPlayer(), false);
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center;'>"
									+ ItemType.DYE_BRUSH.getDyeBrushEffects(clothingEquipped, clothingEquipped.getClothingType().getAvailableColours().get(index - 1))
								+ "</p>"
								+ "<p>"
									+ "<b>Your " + clothingEquipped.getName() + " " + (clothingEquipped.getClothingType().isPlural() ? "have been" : "has been") + " dyed</b> <b style='color:"
										+ clothingEquipped.getClothingType().getAvailableColours().get(index - 1).toWebHexString() + ";'>" + clothingEquipped.getClothingType().getAvailableColours().get(index - 1).getName() + "</b>!"
								+ "</p>"
								+ "<p>"
									+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
											?"You have <b>" + Main.game.getPlayer().getMapOfDuplicateItems().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
													+ "</b> Dye-brush" + (Main.game.getPlayer().getMapOfDuplicateItems().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
											:"You have <b>0</b> Dye-brushes left!")
								+ "</p>");
						clothingEquipped.setColour(clothingEquipped.getClothingType().getAvailableColours().get(index - 1));
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
	
	public static final DialogueNodeOld DYE_CLOTHING_SEX = new DialogueNodeOld("Dye clothing", "", true) { //TODO
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			inventorySB = new StringBuilder("<div class='inventoryImage'>" + clothingEquipped.getSVGString() + "</div>" + clothingEquipped.getDescription() + clothingEquipped.clothingExtraInformation(Main.game.getPlayer()) + "<p>"
					+ "Available colours for this item (hover over to view preview):" + "</p>");

			for (Colour c : clothingEquipped.getClothingType().getAvailableColours())
				inventorySB.append("<div class='phone-item-colour' id='" + (clothingEquipped.getClothingType().toString() + "_" + c.toString()) + "' style='background-color:" + c.toWebHexString() + ";'></div>");

			return inventorySB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous menu.", CLOTHING_EQUIPPED);

			} else if (index - 1 < clothingEquipped.getClothingType().getAvailableColours().size()) {
				return new Response("Dye: " + Util.capitaliseSentence(clothingEquipped.getClothingType().getAvailableColours().get(index - 1).getName()),
						"Dye the " + clothingEquipped.getName() + " " + Util.capitaliseSentence(clothingEquipped.getClothingType().getAvailableColours().get(index - 1).getName())
						+ ". This action is permanent, and you'll need another Dye-brush if you want to change its colour again.",  Sex.SEX_DIALOGUE){
					@Override
					public void effects(){
						Main.game.getPlayer().useItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), Main.game.getPlayer(), false);
						Sex.setDyeClothingText(
								"<p style='text-align:center;'>"
									+ ItemType.DYE_BRUSH.getDyeBrushEffects(clothingEquipped, clothingEquipped.getClothingType().getAvailableColours().get(index - 1))
								+ "</p>"
								+ "<p>"
									+ "<b>"+(ownerInSex.isPlayer()?"Your":"[npc.Name]'s")+" " + clothingEquipped.getName() + " " + (clothingEquipped.getClothingType().isPlural() ? "have been" : "has been") + " dyed</b> <b style='color:"
										+ clothingEquipped.getClothingType().getAvailableColours().get(index - 1).toWebHexString() + ";'>" + clothingEquipped.getClothingType().getAvailableColours().get(index - 1).getName() + "</b>!"
								+ "</p>"
								+ "<p>"
									+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
											?"You have <b>" + Main.game.getPlayer().getMapOfDuplicateItems().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH))
													+ "</b> Dye-brush" + (Main.game.getPlayer().getMapOfDuplicateItems().get(AbstractItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
											:"You have <b>0</b> Dye-brushes left!")
								+ "</p>");
						clothingEquipped.setColour(clothingEquipped.getClothingType().getAvailableColours().get(index - 1));

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
	public static String buyItem(AbstractItem item) {
		int itemPrice = item.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier());
		if (Main.game.getPlayer().getMoney() < itemPrice)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item))
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			InventoryDialogue.getInventoryNPC().removeItem(item);
			Main.game.getPlayer().incrementMoney(-itemPrice);

			String s = "<p style='text-align:center;'>" + "You hand over " + UtilText.formatAsMoney(itemPrice) + 
					" to " + InventoryDialogue.getInventoryNPC().getName("the") + " in exchange for the "
					+ item.getName() + "." + "</p>" + Main.game.getPlayer().addItem(item, false);

			return s;
		}
	}
	
	public static String buyAllItems(AbstractItem item) {
		int itemCount = InventoryDialogue.getInventoryNPC().getItemCount(item);
		int totalPrice = item.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier()) * itemCount;
		if (Main.game.getPlayer().getMoney() < totalPrice)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item))
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			List<AbstractItem> items = InventoryDialogue.getInventoryNPC().getAllItemsInInventory().stream()
				.filter(item::equals)
				.collect(Collectors.toList());
				
			items.stream().forEach(tradeItem -> {
				Main.game.getPlayer().addItem(tradeItem, false);
				InventoryDialogue.getInventoryNPC().removeItem(tradeItem);
			});
			
			Main.game.getPlayer().incrementMoney(-totalPrice);
			
			String s = UtilText.parse(InventoryDialogue.getInventoryNPC(),
					"<p style='text-align:center;'>"
							+ "You hand over " + UtilText.formatAsMoney(totalPrice) + " to [npc.name] in exchange for all of the "
							+ item.getName() + " [npc.she] has."
							+ "</br>"
							+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Items added to inventory:</b> <b>"+itemCount+"x</b> <b>"+item.getDisplayName(true)+"</b>"
					+ "</p>");

			return s;
		}
	}

	public static String buyBackItem(AbstractItem item, int price, int buyBackIndex) {

		if (Main.game.getPlayer().getMoney() < price)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasItem(item))
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
			Main.game.getPlayer().incrementMoney(-price);

			String s = "<p style='text-align:center;'>" + "You hand over " + UtilText.formatAsMoney(price) + " to "
					+ InventoryDialogue.getInventoryNPC().getName("the") + " in exchange for the " + item.getName() + "." + "</p>" + Main.game.getPlayer().addItem(item, false);

			return s;
		}
	}

	public static String sellItem(AbstractItem item) {
		int itemPrice = item.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier());
		Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(item, itemPrice));
		Main.game.getPlayer().removeItem(item);
		Main.game.getPlayer().incrementMoney(itemPrice);

		String s = "<p style='text-align:center;'>" 
						+ "You hand over the " + item.getName() + " to " + InventoryDialogue.getInventoryNPC().getName("the") + " in exchange for " + UtilText.formatAsMoney(itemPrice) + "." 
					+ "</p>";

		return s;
	}

	// Clothing:
	public static String buyClothing(AbstractClothing clothing) {
		int itemPrice = clothing.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier());
		if (Main.game.getPlayer().getMoney() < itemPrice) {
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		} else if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing)) {
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		} else {
			InventoryDialogue.getInventoryNPC().removeClothing(clothing);
			// Temporary fix! TODO
			((Nyan)Main.game.getNyan()).removeClothingFromLists(clothing);
			
			Main.game.getPlayer().incrementMoney(-itemPrice);

			String s = "<p style='text-align:center;'>" + "You hand over " + UtilText.formatAsMoney(itemPrice) + " to " + InventoryDialogue.getInventoryNPC().getName("the") 
					+ " in exchange for the " + clothing.getName() + "." + "</p>" + Main.game.getPlayer().addClothing(clothing, false);

			return s;
		}
	}
	
	public static String buyAllClothing(AbstractClothing clothing) {
		int clothingCount = InventoryDialogue.getInventoryNPC().getClothingCount(clothing);
		int totalPrice = clothing.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier()) * clothingCount;
		if (Main.game.getPlayer().getMoney() < totalPrice) {
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		} else if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasClothing(clothing)) {
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		} else {
			
			if(InventoryDialogue.getInventoryNPC() == Main.game.getNyan()) {
				for(int i=0; i<clothingCount; i++) {
					// Temporary fix! TODO
					((Nyan)Main.game.getNyan()).removeClothingFromLists(clothing);
				}
			}
			
			List<AbstractClothing> clothingItems = InventoryDialogue.getInventoryNPC().getAllClothingInInventory().stream()
					.filter(clothing::equals)
					.collect(Collectors.toList());
					
			clothingItems.stream().forEach(tradeClothing -> {
				Main.game.getPlayer().addClothing(tradeClothing, false);
				InventoryDialogue.getInventoryNPC().removeClothing(tradeClothing);
			});
			
			Main.game.getPlayer().incrementMoney(-totalPrice);

			String s = UtilText.parse(InventoryDialogue.getInventoryNPC(),
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
					+ InventoryDialogue.getInventoryNPC().getName("the") + " in exchange for the " + clothing.getName() + "." + "</p>" + Main.game.getPlayer().addClothing(clothing, false);

			return s;
		}
	}

	public static String sellClothing(AbstractClothing clothing) {
		int itemPrice = clothing.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier());
		Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(clothing, itemPrice));
		Main.game.getPlayer().removeClothing(clothing);
		Main.game.getPlayer().incrementMoney(itemPrice);

		String s = "<p style='text-align:center;'>" 
						+ "You hand over the " + clothing.getName() + " to " + InventoryDialogue.getInventoryNPC().getName("the") + " in exchange for " + UtilText.formatAsMoney(itemPrice) + "." 
				+ "</p>";

		return s;
	}

	// Weapons:
	public static String buyWeapon(AbstractWeapon weapon) {
		int itemPrice = weapon.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier());
		if (Main.game.getPlayer().getMoney() < itemPrice)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon))
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			InventoryDialogue.getInventoryNPC().removeWeapon(weapon);
			Main.game.getPlayer().incrementMoney(-itemPrice);

			String s = "<p style='text-align:center;'>" 
							+ "You hand over " + UtilText.formatAsMoney(itemPrice) + " to " + InventoryDialogue.getInventoryNPC().getName("the") + " in exchange for the " + weapon.getName() + "." 
					+ "</p>" + Main.game.getPlayer().addWeapon(weapon, false);

			return s;
		}
	}
	
	public static String buyAllWeapons(AbstractWeapon weapon) {
		int weaponCount = InventoryDialogue.getInventoryNPC().getWeaponCount(weapon);
		int totalPrice = weapon.getPrice(InventoryDialogue.getInventoryNPC().getSellModifier()) * weaponCount;
		if (Main.game.getPlayer().getMoney() < totalPrice)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon))
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			List<AbstractWeapon> weapons = InventoryDialogue.getInventoryNPC().getAllWeaponsInInventory().stream()
				.filter(weapon::equals)
				.collect(Collectors.toList());
			
			weapons.forEach(tradeWeapon -> {
				Main.game.getPlayer().addWeapon(tradeWeapon, false);
				InventoryDialogue.getInventoryNPC().removeWeapon(tradeWeapon);
			});
			
			Main.game.getPlayer().incrementMoney(-totalPrice);

			String s = UtilText.parse(InventoryDialogue.getInventoryNPC(),
					"<p style='text-align:center;'>"
							+ "You hand over " + UtilText.formatAsMoney(totalPrice) + " to [npc.name] in exchange for all of the "
							+ weapon.getName() + "s [npc.she] has."
							+ "</br>"
							+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Weapons added to inventory:</b> <b>"+weaponCount+"x</b> <b>"+weapon.getDisplayName(true)+"</b>"
					+ "</p>");

			return s;
		}
	}

	public static String buyBackWeapon(AbstractWeapon weapon, int price, int buyBackIndex) {

		if (Main.game.getPlayer().getMoney() < price)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull() && !Main.game.getPlayer().hasWeapon(weapon))
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
			Main.game.getPlayer().incrementMoney(-price);

			String s = "<p style='text-align:center;'>" + "You hand over " + UtilText.formatAsMoney(price) + " to "
					+ InventoryDialogue.getInventoryNPC().getName("the") + " in exchange for the " + weapon.getName() + "." + "</p>" + Main.game.getPlayer().addWeapon(weapon, false);

			return s;
		}
	}

	public static String sellWeapon(AbstractWeapon weapon) {
		int itemPrice = weapon.getPrice(InventoryDialogue.getInventoryNPC().getBuyModifier());
		Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(weapon, itemPrice));
		Main.game.getPlayer().removeWeapon(weapon);
		Main.game.getPlayer().incrementMoney(itemPrice);

		String s = "<p style='text-align:center;'>" 
						+ "You hand over the " + weapon.getName() + " to " + InventoryDialogue.getInventoryNPC().getName("the") + " in exchange for " + UtilText.formatAsMoney(itemPrice) + "." 
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

	public static AbstractWeapon getWeaponEquipped() {
		return weaponEquipped;
	}

	public static void setWeaponEquipped(AbstractWeapon weaponEquipped) {
		InventoryDialogue.weaponEquipped = weaponEquipped;
	}

	public static AbstractClothing getClothingEquipped() {
		return clothingEquipped;
	}

	public static void setClothingEquipped(AbstractClothing clothingEquipped) {
		InventoryDialogue.clothingEquipped = clothingEquipped;
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

	public static GameCharacter getOwnerInSex() {
		return ownerInSex;
	}

	public static void setOwnerInSex(GameCharacter ownerInSex) {
		InventoryDialogue.ownerInSex = ownerInSex;
	}

	public static NPC getInventoryNPC() {
		return inventoryNPC;
	}

	public static void setInventoryNPC(NPC inventoryNPC) {
		InventoryDialogue.inventoryNPC = inventoryNPC;
	}

	public static NPCInventoryInteraction getNPCInventoryInteraction() {
		return interactionType;
	}

	public static void setNPCInventoryInteraction(NPCInventoryInteraction nPCInventoryInteraction) {
		interactionType = nPCInventoryInteraction;
	}

}
