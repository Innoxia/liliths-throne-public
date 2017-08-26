package com.base.game.dialogue.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.base.game.character.GameCharacter;
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
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.Rarity;
import com.base.game.inventory.ShopTransaction;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.enchanting.TFModifier;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.inventory.item.ItemType;
import com.base.game.inventory.weapon.AbstractWeapon;
import com.base.game.sex.Sex;
import com.base.game.sex.sexActions.SexActionUtility;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.world.places.Dominion;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class InventoryDialogue {
	
	// Welcome to hell!
	
	private static final int IDENTIFICATION_PRICE = 10;
	
	private static AbstractItem item, itemFloor;
	private static AbstractClothing clothing, clothingFloor, clothingEquipped;
	private static AbstractWeapon weapon, weaponFloor, weaponEquipped;
	private static GameCharacter ownerInSex;

	private static StringBuilder inventorySB;

	private static List<AbstractClothing> jinxedClothing = new ArrayList<>();

	private static boolean jinxRemovalFromFloor, buyback;

	private static int buyBackPrice, buyBackIndex;

	private static String inventoryView() {
//		System.out.println(System.nanoTime()+": rendered");
		inventorySB = new StringBuilder();

		inventorySB.append("<div class='inventory-contents'>");
		inventorySB.append("<p style='text-align: center;'>" + "<b>Your inventory ("+Main.game.getPlayer().getInventorySlotsTaken()+"/"+Main.game.getPlayer().getMaximumInventorySpace()+")</b>"
				+ "</p>");

		inventorySB.append("<div class='inventory-container'>");

		// Weapons:
		if (Main.game.getPlayer().getWeaponCount() > 0) {
			for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayer().getMapOfDuplicateWeapons().entrySet()) {
				inventorySB.append("<div class='item-background "
						+ getClassFromRarity(entry.getKey().getRarity()) + "'>" + entry.getKey().getSVGString()
						+ "<div class='overlay"
						+ (Main.game.getDialogueFlags().tradePartner!=null 
							? (Main.game.getDialogueFlags().tradePartner.willBuy(entry.getKey()) ? "" : " dark")
							: (Main.game.isInSex() || Main.game.isInCombat()?" disabled":""))
						+ "' id='WEAPON_" + entry.getKey().hashCode() + "'>"
						+ getItemCountDiv(entry.getValue())
						+ (Main.game.getDialogueFlags().tradePartner != null ? 
								(Main.game.getDialogueFlags().tradePartner.willBuy(entry.getKey()) ? 
										getItemPriceDiv((int) (entry.getKey().getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier())) 
										: "") 
								: "")
						+ "</div>" + "</div>");
			}
		}
		// Clothing:
		if (Main.game.getPlayer().getClothingCount() > 0) {
			for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayer().getMapOfDuplicateClothing().entrySet()) {
				inventorySB.append("<div class='item-background ");
				
				if (entry.getKey().isEnchantmentKnown()) {
					inventorySB.append(getClassFromRarity(entry.getKey().getRarity()));
				} else {
					inventorySB.append("unknown");
				}
				inventorySB.append("'>");

				inventorySB.append(entry.getKey().getSVGString() + "<div class='overlay"
							+ (Main.game.getDialogueFlags().tradePartner!=null
								? (Main.game.getDialogueFlags().tradePartner.willBuy(entry.getKey()) ? "" : " dark")
								: (Main.game.isInSex() || Main.game.isInCombat()?" disabled":""))
							+ "' id='CLOTHING_" + entry.getKey().hashCode() + "'>"
							+ getItemCountDiv(entry.getValue())
							+ (Main.game.getDialogueFlags().tradePartner != null ? 
									(Main.game.getDialogueFlags().tradePartner.willBuy(entry.getKey()) ? 
											getItemPriceDiv(!entry.getKey().getAttributeModifiers().isEmpty() && !entry.getKey().isEnchantmentKnown() ? 5
															:(int) (entry.getKey().getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier())) 
											: "") 
									: "")
							+ "</div>"
						+ "</div>");
			}
		}
		// Items:
		if (Main.game.getPlayer().getItemCount() > 0) {
			for (Entry<AbstractItem, Integer> entry : Main.game.getPlayer().getMapOfDuplicateItems().entrySet()) {
				inventorySB.append("<div class='item-background "
						+ getClassFromRarity(entry.getKey().getRarity()) + "'>" + entry.getKey().getSVGString()
						+ "<div class='overlay"
						+ (Main.game.getDialogueFlags().tradePartner!=null
										? (Main.game.getDialogueFlags().tradePartner.willBuy(entry.getKey()) ? ""
												: " dark")
										: ((Main.game.isInSex() && !entry.getKey().getItemType().isAbleToBeUsedInSex()) || (Main.game.isInCombat() && !entry.getKey().getItemType().isAbleToBeUsedInCombat())?" disabled":""))
						+ "' id='ITEM_" + entry.getKey().hashCode() + "'>"
						+ getItemCountDiv(entry.getValue())
						+ (Main.game.getDialogueFlags().tradePartner != null ? 
								(Main.game.getDialogueFlags().tradePartner.willBuy(entry.getKey()) ? 
										getItemPriceDiv((int) (entry.getKey().getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier())) 
										: "") 
								: "")
						+ "</div>" + "</div>");
			}
		}
		// Fill space:
		for (int i = Main.game.getPlayer().getMaximumInventorySpace(); i > Main.game.getPlayer().getInventorySlotsTaken(); i--) {
			inventorySB.append("<div class='item-background empty'></div>");
		}
		
		inventorySB.append("</div>"
				
				+ "<div style='text-align: center; display:block; margin:0 auto; height:48px; padding:8px 0 8px 0;'>");
		for(TFEssence essence : TFEssence.values()) {
			inventorySB.append(
					"<div style='width:28px; display:inline-block; margin:0 4px 0 4px;'>"
						+ "<div class='item-inline " + getClassFromRarity(essence.getRarity()) + "'>"
							+ essence.getSVGString()
							+ "<div class='overlay no-pointer' id='ESSENCE_"+essence.hashCode()+"'></div>"
						+ "</div>"
						+ " <div style='display:inline-block; height:20px; vertical-align: middle;'>"
							+ "<b>"+Main.game.getPlayer().getEssenceCount(essence)+"</b>"
						+ "</div>"
					+ "</div>"
					);
		}
		
		inventorySB.append(
				"<div style='width:60px; display:inline-block; margin:0 4px 0 4px;'>"
					+ " <div style='display:inline-block; height:48px; vertical-align: middle; top:0;'>"
						+ formatAsMoney(Main.game.getPlayer().getMoney())
					+ "</div>"
				+ "</div>"
				);
		
		inventorySB.append("</div>");
		
		
		inventorySB.append("</div>");

		// Right-panel:
		inventorySB.append("<div class='inventory-contents'>" + "<p style='text-align: center;'>");
		
		if(Main.game.isInSex() || Main.game.isInCombat()){
			
			inventorySB.append("<b>Disabled</b>");
			inventorySB.append("</p>" + "<div class='inventory-container'>");
			for (int i = 24; i > 0; i--) {
				inventorySB.append("<div class='item-background disabled'></div>");
			}
			inventorySB.append("</div>");
			inventorySB.append("<p style='text-align: center; height:48px;'>-</p>");
			inventorySB.append("</div>");
			
		} else {

			if (Main.game.getDialogueFlags().tradePartner!=null) {
				if (buyback)
					inventorySB.append("<b>Buyback:</b>");
				else
					inventorySB.append("<b>" + Util.capitaliseSentence(Main.game.getDialogueFlags().tradePartner.getName()) + " is offering to sell:</b>");
	
			} else {
				inventorySB.append("<b>Items on the ground:</b>");
			}
			
			inventorySB.append("</p>" + "<div class='inventory-container'>");
	
			// This is a trade dialogue:
			if (Main.game.getDialogueFlags().tradePartner!=null) {
	
				// Buyback screen:
				if (buyback) {
					for (int i = Main.game.getPlayer().getBuybackStack().size() - 1; i >= 0; i--) {
						
						AbstractCoreItem itemBuyback = Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold();
	
						if (itemBuyback != null) {
							// Clothing:
							int itemPrice = (int) (Main.game.getPlayer().getBuybackStack().get(i).getPrice());
							if (itemBuyback instanceof AbstractClothing) {
								inventorySB.append(getBuybackItemPanel(itemBuyback, "CLOTHING_BUYBACK_" + i, itemPrice));
	
							// Weapon:
							} else if (itemBuyback instanceof AbstractWeapon) {
								inventorySB.append(getBuybackItemPanel(itemBuyback, "WEAPON_BUYBACK_" + i, itemPrice));
								
							// Item:
							} else {
								inventorySB.append(getBuybackItemPanel(itemBuyback, "ITEM_BUYBACK_" + i, itemPrice));
							}
						}
					}
	
					// Fill space:
					for (int i = 24; i > Main.game.getPlayer().getBuybackStack().size(); i--) {
						inventorySB.append("<div class='item-background empty'></div>");
					}
					
				// Normal trader screen:
				} else {
					
					// Weapons:
					if (Main.game.getDialogueFlags().tradePartner.getWeaponCount() > 0) {
						for (Entry<AbstractWeapon, Integer> entry : Main.game.getDialogueFlags().tradePartner.getMapOfDuplicateWeapons().entrySet()) {
							inventorySB.append("<div class='item-background " + getClassFromRarity(entry.getKey().getRarity()) + "'>" + entry.getKey().getSVGString());
							inventorySB.append("<div class='overlay' id='WEAPON_TRADER_" + entry.getKey().hashCode() + "'>"
									+ getItemCountDiv(entry.getValue())
									+ getItemPriceDiv((int) (entry.getKey().getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier()))
									+ "</div>" + "</div>");
						}
					}
					
					// Clothing:
					if (Main.game.getDialogueFlags().tradePartner.getClothingCount() > 0) {
						for (Entry<AbstractClothing, Integer> entry : Main.game.getDialogueFlags().tradePartner.getMapOfDuplicateClothing().entrySet()) {
							inventorySB.append("<div class='item-background " + (entry.getKey().isEnchantmentKnown() ? getClassFromRarity(entry.getKey().getRarity()) : "unknown") + "'>"
									+ entry.getKey().getSVGString() + "<div class='overlay' id='CLOTHING_TRADER_" + entry.getKey().hashCode() + "'>"
											+ getItemCountDiv(entry.getValue())
											+ getItemPriceDiv(!entry.getKey().getAttributeModifiers().isEmpty() && !entry.getKey().isEnchantmentKnown() ? 5
													:(int) (entry.getKey().getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier()))
									+ "</div>" + "</div>");
						}
					}
					// Items:
					if (Main.game.getDialogueFlags().tradePartner.getItemCount() > 0) {
						for (Entry<AbstractItem, Integer> entry : Main.game.getDialogueFlags().tradePartner.getMapOfDuplicateItems().entrySet()) {
							inventorySB.append("<div class='item-background " + getClassFromRarity(entry.getKey().getRarity()) + "'>" + entry.getKey().getSVGString()
											+ "<div class='overlay' id='ITEM_TRADER_" + entry.getKey().hashCode() + "'>"
													+ getItemCountDiv(entry.getValue())
													+ getItemPriceDiv((int) (entry.getKey().getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier()))
											+ "</div>" + "</div>");
						}
					}
					// Fill space:
					for (int i = Main.game.getDialogueFlags().tradePartner.getMaximumInventorySpace(); i > Main.game.getDialogueFlags().tradePartner.getInventorySlotsTaken(); i--) {
						inventorySB.append("<div class='item-background empty'></div>");
					}
	
				}
				
			// Floor:
			} else {
				// Weapons:
				if (Main.game.getPlayerCell().getInventory().getWeaponCount() > 0) {
					for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateWeapons().entrySet()) {
						inventorySB.append("<div class='item-background " + getClassFromRarity(entry.getKey().getRarity()) + "'>"
								+ entry.getKey().getSVGString() + "<div class='overlay' id='WEAPON_FLOOR_" + entry.getKey().hashCode() + "'>"
										+ getItemCountDiv(entry.getValue())
										+ "</div>"
								+ "</div>");
					}
				}
				// Clothing:
				if (Main.game.getPlayerCell().getInventory().getClothingCount() > 0) {
					for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateClothing().entrySet()) {
						inventorySB.append("<div class='item-background ");
						
						if (entry.getKey().isEnchantmentKnown()) {
							inventorySB.append(getClassFromRarity(entry.getKey().getRarity()));
						} else {
							inventorySB.append("unknown");
						}
						inventorySB.append("'>");
	
						inventorySB.append(entry.getKey().getSVGString() + "<div class='overlay' id='CLOTHING_FLOOR_" + entry.getKey().hashCode() + "'>"
								+ getItemCountDiv(entry.getValue())
								+ "</div>"
						+ "</div>");
					}
				}
				// Items:
				if (Main.game.getPlayerCell().getInventory().getItemCount() > 0) {
					for (Entry<AbstractItem, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateItems().entrySet()) {
						inventorySB.append("<div class='item-background " + getClassFromRarity(entry.getKey().getRarity()) + "'>"
								+ entry.getKey().getSVGString()
								+ "<div class='overlay' id='ITEM_FLOOR_" + entry.getKey().hashCode() + "'>"
										+ getItemCountDiv(entry.getValue())
								+ "</div>" + "</div>");
					}
				}
				// Fill space:
				for (int i = 24; i > Main.game.getPlayerCell().getInventory().getInventorySlotsTaken(); i--) {
					inventorySB.append("<div class='item-background empty'></div>");
				}
	
			}
	
			inventorySB.append("</div>");
			if (Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace() != Dominion.CITY_AUNTS_HOME
					&& Main.game.getPlayerCell().getPlace().isItemsDisappear()
					&& Main.game.getPlayerCell().getInventory().getInventorySlotsTaken() > 0)
				inventorySB.append("<p style='text-align: center; height:48px;'>" + "<b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>These items will disappear when you leave the area.</b>" + "</p>");
			else
				inventorySB.append("<p style='text-align: center; height:48px;'>-</p>");
			inventorySB.append("</div>");
		}

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
			if (Main.game.getDialogueFlags().quickTrade && !Main.game.isInSex() && !Main.game.isInCombat())
				return "Inventory (Quick-Manage is <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>ON</b>)";
			else
				return "Inventory";
		}

		@Override
		public String getHeaderContent() {
			return inventoryView();
		}

		@Override
		public String getContent() {
			if (Main.game.getDialogueFlags().tradePartner!=null)
				return Main.game.getDialogueFlags().tradePartner.getTraderDescription();
			else
				return "";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return getCloseInventoryResponse();
			} else if (index == 1 && Main.game.getDialogueFlags().tradePartner==null) {
				if(Main.game.getPlayerCell().getInventory().getInventorySlotsTaken()==0 || Main.game.isInCombat() || Main.game.isInSex()) {
					return new Response("Take all", "Pick up everything on the ground.", null);
					
				} else {
					return new Response("Take all", "Pick up everything on the ground.", INVENTORY_MENU){
						@Override
						public void effects(){
							int i = Main.game.getPlayerCell().getInventory().getItemsInInventory().size();
							while(i>0 && !Main.game.getPlayer().isInventoryFull()) {
								Main.game.getPlayer().addItem(Main.game.getPlayerCell().getInventory().getItemsInInventory().get(i-1), true);
								i = Main.game.getPlayerCell().getInventory().getItemsInInventory().size();
							}
							
							i = Main.game.getPlayerCell().getInventory().getClothingInInventory().size();
							while(i>0 && !Main.game.getPlayer().isInventoryFull()) {
								Main.game.getPlayer().addClothing(Main.game.getPlayerCell().getInventory().getClothingInInventory().get(i-1), true);
								i = Main.game.getPlayerCell().getInventory().getClothingInInventory().size();
							}
							
							i = Main.game.getPlayerCell().getInventory().getWeaponsInInventory().size();
							while(i>0 && !Main.game.getPlayer().isInventoryFull()) {
								Main.game.getPlayer().addWeapon(Main.game.getPlayerCell().getInventory().getWeaponsInInventory().get(i-1), true);
								i = Main.game.getPlayerCell().getInventory().getWeaponsInInventory().size();
							}
						}
					};
				}
				
			} else if (index == 8 && Main.game.getDialogueFlags().tradePartner!=null) {
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
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return item.getDisplayName(true);
		}

		@Override
		public String getHeaderContent() {
			if(!Main.game.isInSex())
				return inventoryView();
			else
				return null;
		}
		
		@Override
		public String getContent() {
			return "<div class='inventoryImage'>" + item.getSVGString() + "</div>" + item.getDescription() + item.getExtraDescription()
					+ (Main.game.getDialogueFlags().tradePartner != null ? 
							Main.game.getDialogueFlags().tradePartner.willBuy(item) ? 
									"<p>" + Main.game.getDialogueFlags().tradePartner.getName("The") + " will buy it for " + formatAsMoney((int) (item.getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier())) + ".</p>" 
							: Main.game.getDialogueFlags().tradePartner.getName("The") + " doesn't want to buy this."
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
					if(Main.game.getDialogueFlags().tradePartner!=null) {
						if (Main.game.getDialogueFlags().tradePartner.willBuy(item)) {
							int sellPrice = (int) (item.getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier());
							return new Response("Sell (" + formatAsMoney(sellPrice, "span") + ")", "Sell the " + item.getName() + " for " + formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									Main.game.getTextStartStringBuilder().append(sellItem(item));
								}
							};
						} else {
							return new Response("Sell", Main.game.getDialogueFlags().tradePartner.getName("The") + " doesn't want to buy this.", null);
						}
						
					} else {
						if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
							if(Main.game.isPlayerTileFull()) {
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
							if(Main.game.isPlayerTileFull()) {
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
					if(Main.game.getDialogueFlags().tradePartner!=null) {
						if (Main.game.getDialogueFlags().tradePartner.willBuy(item)) {
							int sellPrice = (int) (item.getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier() * Main.game.getPlayer().getItemCount(item));
							return new Response("Sell all (" + formatAsMoney(sellPrice, "span") + ")",
									"Sell all of your " + item.getName() + (item.getItemType().isPlural()?"":"s")+" for " + formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
								@Override
								public void effects(){
									int itemCount = Main.game.getPlayer().getItemCount(item);
									
									Main.game.getTextStartStringBuilder().append(
											"<p style='text-align:center;'>"
												+ "You hand over all of your <b>" + item.getDisplayName(true) + "</b> to " + Main.game.getDialogueFlags().tradePartner.getName("the") + " in exchange for " + formatAsMoney(sellPrice) + "."
											+ "</p>");
									
									for(int i = 0; i < itemCount; i++) {
										sellItem(item);
									}
								}
							};
						} else {
							return new Response("Sell all", Main.game.getDialogueFlags().tradePartner.getName("The") + " doesn't want to buy this.", null);
						}
						
					} else {
						if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
							if(Main.game.isPlayerTileFull()) {
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
							if(Main.game.isPlayerTileFull()) {
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
								EnchantmentDialogue.ingredient = item;
								EnchantmentDialogue.primaryMod = TFModifier.NONE;
								EnchantmentDialogue.secondaryMod = TFModifier.NONE;
								EnchantmentDialogue.previousPrimaryMod = TFModifier.NONE;
								EnchantmentDialogue.previousSecondaryMod = TFModifier.NONE;
							}
						};
						
					} else if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
						if(Main.game.getPlayer().getQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY).getSortingOrder() != 0) {
							return new Response("Enchant", "Enchant this item.", EnchantmentDialogue.ENCHANTMENT_MENU) {
								@Override
								public void effects() {
									EnchantmentDialogue.effects.clear();
									EnchantmentDialogue.ingredient = item;
									EnchantmentDialogue.primaryMod = TFModifier.NONE;
									EnchantmentDialogue.secondaryMod = TFModifier.NONE;
									EnchantmentDialogue.previousPrimaryMod = TFModifier.NONE;
									EnchantmentDialogue.previousSecondaryMod = TFModifier.NONE;
								}
							};
						}
					}
					
					return null;
					
				} else if (index == 8 && Main.game.getDialogueFlags().tradePartner!=null) {
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
			return weapon.getDisplayName(true);
		}
		
		@Override
		public String getHeaderContent() {
			if(!Main.game.isInSex())
				return inventoryView();
			else
				return null;
		}

		@Override
		public String getContent() {
			return "<div class='inventoryImage'>" + weapon.getSVGString() + "</div>" + weapon.getDescription()
					+ (Main.game.getDialogueFlags().tradePartner != null ? 
							Main.game.getDialogueFlags().tradePartner.willBuy(weapon) ? 
									"<p>" + Main.game.getDialogueFlags().tradePartner.getName("The") + " will buy it for " + formatAsMoney((int) (weapon.getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier())) + ".</p>" 
							: Main.game.getDialogueFlags().tradePartner.getName("The") + " doesn't want to buy this."
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
				if(Main.game.getDialogueFlags().tradePartner!=null) {
					if (Main.game.getDialogueFlags().tradePartner.willBuy(weapon)) {
						int sellPrice = (int) (weapon.getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier());
						return new Response("Sell (" + formatAsMoney(sellPrice, "span") + ")",
								"Sell the " + weapon.getName() + " for " + formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(sellWeapon(weapon));
							}
						};
					} else {
						return new Response("Sell", Main.game.getDialogueFlags().tradePartner.getName("The") + " doesn't want to buy this.", null);
					}
					
				} else {
					if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
						if(Main.game.isPlayerTileFull()) {
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
						if(Main.game.isPlayerTileFull()) {
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
				if(Main.game.getDialogueFlags().tradePartner!=null) {
					if (Main.game.getDialogueFlags().tradePartner.willBuy(weapon)) {
						int sellPrice = (int) (weapon.getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier() * Main.game.getPlayer().getWeaponCount(weapon));
						return new Response("Sell all (" + formatAsMoney(sellPrice, "span") + ")",
								"Sell all of your " + weapon.getName() +"s for " + formatAsMoney(sellPrice) + ".", INVENTORY_MENU){
							@Override
							public void effects(){
								int itemCount = Main.game.getPlayer().getWeaponCount(weapon);
								
								Main.game.getTextStartStringBuilder().append(
										"<p style='text-align:center;'>"
											+ "You hand over all of your <b>" + weapon.getDisplayName(true) + "</b> to " + Main.game.getDialogueFlags().tradePartner.getName("the") + " in exchange for " + formatAsMoney(sellPrice) + "."
										+ "</p>");
								
								for(int i = 0; i < itemCount; i++) {
									sellWeapon(weapon);
								}
							}
						};
					} else {
						return new Response("Sell all", Main.game.getDialogueFlags().tradePartner.getName("The") + " doesn't want to buy this.", null);
					}
					
				} else {
					if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
						if(Main.game.isPlayerTileFull()) {
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
						if(Main.game.isPlayerTileFull()) {
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
				if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_JINXED_CLOTHING)){
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_JINXED_CLOTHING).getSortingOrder() != 0){
						return new Response("Remove jinx", "Proceed to the jinxed clothing choice menu.", REMOVE_JINX){
							@Override
							public void effects() {
								jinxRemovalFromFloor=false;
							}
						};
					}
				}
				
				return null;
				
			} else if (index == 8 && Main.game.getDialogueFlags().tradePartner!=null) {
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
			return clothing.getDisplayName(true);
		}
		
		@Override
		public String getHeaderContent() {
			if(!Main.game.isInSex())
				return inventoryView();
			else
				return null;
		}

		@Override
		public String getContent() {
			return "<div class='inventoryImage'>" + clothing.getSVGString() + "</div>" + clothing.getDescription() + clothing.clothingExtraInformation(null)
					+ (Main.game.getDialogueFlags().tradePartner != null ? 
							Main.game.getDialogueFlags().tradePartner.willBuy(clothing) ? 
									"<p>" + Main.game.getDialogueFlags().tradePartner.getName("The") + " will buy it for " + formatAsMoney((int) (clothing.getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier())) + ".</p>" 
							: Main.game.getDialogueFlags().tradePartner.getName("The") + " doesn't want to buy this."
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
				if(Main.game.getDialogueFlags().tradePartner!=null) {
					if (Main.game.getDialogueFlags().tradePartner.willBuy(clothing)) {
						int itemPrice = (int) (clothing.getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier());
						return new Response("Sell (" + formatAsMoney(itemPrice, "span") + ")",
								"Sell your " + clothing.getName() + " for " + formatAsMoney(itemPrice) + ".", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(sellClothing(clothing));
							}
						};
					} else {
						return new Response("Sell", Main.game.getDialogueFlags().tradePartner.getName("The") + " doesn't want to buy this.", null);
					}
					
				} else {
					if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
						if(Main.game.isPlayerTileFull()) {
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
						if(Main.game.isPlayerTileFull()) {
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
				if(Main.game.getDialogueFlags().tradePartner!=null) {
					if (Main.game.getDialogueFlags().tradePartner.willBuy(clothing)) {
						int itemPrice = (int) (clothing.getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier() * Main.game.getPlayer().getClothingCount(clothing));
						return new Response("Sell all (" + formatAsMoney(itemPrice, "span") + ")",
								"Sell all of your " + clothing.getName() +" for " + formatAsMoney(itemPrice) + ".", INVENTORY_MENU){
							@Override
							public void effects(){
								int itemCount = Main.game.getPlayer().getClothingCount(clothing);
								
								Main.game.getTextStartStringBuilder().append(
										"<p style='text-align:center;'>"
											+ "You hand over all of your <b>" + clothing.getDisplayName(true) + "</b> to " + Main.game.getDialogueFlags().tradePartner.getName("the") + " in exchange for " + formatAsMoney(itemPrice) + "."
										+ "</p>");
								
								for(int i = 0; i < itemCount; i++) {
									sellClothing(clothing);
								}
							}
						};
					} else {
						return new Response("Sell all", Main.game.getDialogueFlags().tradePartner.getName("The") + " doesn't want to buy this.", null);
					}
					
				} else {
					if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
						if(Main.game.isPlayerTileFull()) {
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
						if(Main.game.isPlayerTileFull()) {
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
					if (Main.game.getPlayer().getItemsDiscovered().contains(ItemType.DYE_BRUSH))
						return new Response("Dye", "You'll need to find another Dye-brush if you want to dye your clothes.", null);
					else
						return new Response("Dye", "You'll need to find an item of some sort if you want to dye your clothes.", null);
				}
				
			// Identify:
			} else if (index == 5 && Main.game.getDialogueFlags().tradePartner!=null && !clothing.isEnchantmentKnown()) {
				if(!Main.game.getDialogueFlags().tradePartner.willBuy(clothing)) {
					return new Response("Identify", Main.game.getDialogueFlags().tradePartner.getName("The") + " can't identify clothing!", null);
					
				} else if(Main.game.getPlayer().getMoney() < IDENTIFICATION_PRICE){
					// don't format as money because we don't want to highlight non-selectable choices
					return new Response("Identify (" + Main.game.getCurrencySymbol() + " " + IDENTIFICATION_PRICE + ")", "You don't have enough money!", null);
					
				}else {
					return new Response("Identify (" + formatAsMoney(IDENTIFICATION_PRICE, "span") + ")",
								"Have the " + clothing.getName() + " identified for " + formatAsMoney(IDENTIFICATION_PRICE, "span") + ".", INVENTORY_MENU){
						@Override
						public void effects(){
							Main.game.getTextStartStringBuilder().append(
									"<p style='text-align:center;'>" + "You hand over " + formatAsMoney(IDENTIFICATION_PRICE) + " to "
											+Main.game.getDialogueFlags().tradePartner.getName("the")+", who promptly identifies your "+clothing.getName()+"."
									+ "</p>"
									+clothing.setEnchantmentKnown(true));
							Main.game.getPlayer().incrementMoney(-IDENTIFICATION_PRICE);
						}
					};
				}

			} else if (index == 8 && Main.game.getDialogueFlags().tradePartner!=null) {
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
			return item.getDisplayName(true);
		}
		
		@Override
		public String getHeaderContent() {
			if(!Main.game.isInSex())
				return inventoryView();
			else
				return null;
		}

		@Override
		public String getContent() {
			int itemPrice = buyback ? buyBackPrice : (int) (item.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier());
			return "<div class='inventoryImage'>" + item.getSVGString() + "</div>" + "<p>" + item.getDescription() + "</p>" + item.getExtraDescription() + "<p>" + Main.game.getDialogueFlags().tradePartner.getName("The")
					+ " will sell it to you for " + formatAsMoney(itemPrice) + "." + "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			// Use item:
			if (index == 1) {
				if (buyback) {
					if (Main.game.getPlayer().isInventoryFull()) {
						return new Response("Buy-back", "Your inventory is full, so you can't buy back the " + item.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < buyBackPrice) {
						return new Response("Buy-back", "You don't have enough money to buy back the " + item.getName() + "!", null);
						
					} else {
						return new Response("Buy-back",
								"Buy back the " + item.getName() + " for " + formatAsMoney(buyBackPrice) + " and add it to your inventory.", ITEM_TRADER){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyBackItem(item, buyBackPrice, buyBackIndex));
							}
						};
					}
					
				} else {
					int itemPrice = (int) (item.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier());
					if (Main.game.getPlayer().isInventoryFull()) {
						return new Response("Buy", "Your inventory is full, so you can't buy the " + item.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < itemPrice) {
						return new Response("Buy", "You don't have enough money to buy the " + item.getName() + "!", null);
						
					} else {
						return new Response("Buy (" + formatAsMoney(itemPrice, "span") + ")",
								"Buy the " + item.getName() + " for " + formatAsMoney(itemPrice) + " and add it to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyItem(item));
							}
						};
					}
				}
				
			} else if (index == 2 && Main.game.getDialogueFlags().tradePartner.getItemCount(item)>1) {
				if (!buyback) {
					int totalPrice = (int) ((item.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier()) * Main.game.getDialogueFlags().tradePartner.getItemCount(item));
					if (Main.game.getPlayer().isInventoryFull()) {
						return new Response("Buy all", "Your inventory is full, so you can't buy the " + item.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < totalPrice) {
						
						return new Response("Buy all", "You don't have enough money to buy the " + item.getName() + "!", null);
						
					} else {
						return new Response("Buy all (" + formatAsMoney(totalPrice, "span") + ")",
								"Buy all of the " + item.getName() + " for " + formatAsMoney(totalPrice) + " and add them to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyAllItems(item));
							}
						};
					}
				} else {
					return null;
				}
				
			} else if (index == 8 && Main.game.getDialogueFlags().tradePartner!=null) {
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
			return weapon.getDisplayName(true);
		}
		
		@Override
		public String getHeaderContent() {
			if(!Main.game.isInSex())
				return inventoryView();
			else
				return null;
		}

		@Override
		public String getContent() {
			int itemPrice = buyback ? buyBackPrice : (int) (weapon.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier());
			return "<div class='inventoryImage'>" + weapon.getSVGString() + "</div>" + weapon.getDescription() + "<p>" + Main.game.getDialogueFlags().tradePartner.getName("The") + " will sell it to you for " 
					+ formatAsMoney(itemPrice) + ".</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			// Use item:
			if (index == 1) {
				if (buyback) {
					if (Main.game.getPlayer().isInventoryFull()) {
						return new Response("Buy-back", "Your inventory is full, so you can't buy back the " + weapon.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < buyBackPrice) {
						return new Response("Buy-back", "You don't have enough money to buy back the " + weapon.getName() + "!", null);
						
					} else {
						return new Response("Buy-back",
								"Buy back the " + weapon.getName() + " for " + formatAsMoney(buyBackPrice) + " and add it to your inventory.", WEAPON_TRADER){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyBackWeapon(weapon, buyBackPrice, buyBackIndex));
							}
						};
					}
					
				} else {
					int itemPrice = (int) (weapon.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier());
					if (Main.game.getPlayer().isInventoryFull()) {
						return new Response("Buy", "Your inventory is full, so you can't buy the " + weapon.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < itemPrice) {
						return new Response("Buy", "You don't have enough money to buy the " + weapon.getName() + "!", null);
						
					} else {
						return new Response("Buy (" + formatAsMoney(itemPrice, "span") + ")",
								"Buy the " + weapon.getName() + " for " + formatAsMoney(itemPrice) + " and add it to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyWeapon(weapon));
							}
						};
					}
				}
				
			} else if (index == 2 && Main.game.getDialogueFlags().tradePartner.getWeaponCount(weapon)>1) {
				if (!buyback) {
					int totalPrice = (int) ((weapon.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier()) * Main.game.getDialogueFlags().tradePartner.getWeaponCount(weapon));
					if (Main.game.getPlayer().isInventoryFull()) {
						return new Response("Buy all", "Your inventory is full, so you can't buy the " + weapon.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < totalPrice) {
						
						return new Response("Buy all", "You don't have enough money to buy the " + weapon.getName() + "!", null);
						
					} else {
						return new Response("Buy all (" + formatAsMoney(totalPrice, "span") + ")",
								"Buy all of the " + weapon.getName() + " for " + formatAsMoney(totalPrice) + " and add them to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyAllWeapons(weapon));
							}
						};
					}
				} else {
					return null;
				}
				
			} else if (index == 8 && Main.game.getDialogueFlags().tradePartner!=null) {
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
			return clothing.getDisplayName(true);
		}
		
		@Override
		public String getHeaderContent() {
			if(!Main.game.isInSex())
				return inventoryView();
			else
				return null;
		}

		@Override
		public String getContent() {
			int itemPrice = buyback ? buyBackPrice : (int) (clothing.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier());
			return "<div class='inventoryImage'>" + clothing.getSVGString() + "</div>" + clothing.getDescription() + clothing.clothingExtraInformation(null) + "<p>" + Main.game.getDialogueFlags().tradePartner.getName("The")
					+ " will sell it to you for " + formatAsMoney(itemPrice) + ".</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			// Use item:
			if (index == 1) {
				if (buyback) {
					if (Main.game.getPlayer().isInventoryFull()) {
						return new Response("Buy-back", "Your inventory is full, so you can't buy back the " + clothing.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < buyBackPrice) {
						return new Response("Buy-back", "You don't have enough money to buy back the " + clothing.getName() + "!", null);
						
					} else {
						return new Response("Buy-back",
								"Buy back the " + clothing.getName() + " for " + formatAsMoney(buyBackPrice) + " and add it to your inventory.", CLOTHING_TRADER){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyBackClothing(clothing, buyBackPrice, buyBackIndex));
							}
						};
					}
					
				} else {
					int itemPrice = (int) (clothing.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier());
					if (Main.game.getPlayer().isInventoryFull()) {
						return new Response("Buy", "Your inventory is full, so you can't buy the " + clothing.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < itemPrice) {
						return new Response("Buy", "You don't have enough money to buy the " + clothing.getName() + "!", null);
						
					} else {
						return new Response("Buy (" + formatAsMoney(itemPrice, "span") + ")",
								"Buy the " + clothing.getName() + " for " + formatAsMoney(itemPrice) + " and add it to your inventory.", INVENTORY_MENU){
							@Override
							public void effects(){
								Main.game.getTextStartStringBuilder().append(buyClothing(clothing));
							}
						};
					}
				}
				
			} else if (index == 2 && Main.game.getDialogueFlags().tradePartner.getClothingCount(clothing)>1) {
				if (!buyback) {
					int totalPrice = (int) ((clothing.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier()) * Main.game.getDialogueFlags().tradePartner.getClothingCount(clothing));
					if (Main.game.getPlayer().isInventoryFull()) {
						return new Response("Buy all", "Your inventory is full, so you can't buy the " + clothing.getName() + "!", null);
						
					} else if (Main.game.getPlayer().getMoney() < totalPrice) {
						
						return new Response("Buy all", "You don't have enough money to buy the " + clothing.getName() + "!", null);
						
					} else {
						return new Response("Buy all (" + formatAsMoney(totalPrice, "span") + ")",
								"Buy all of the " + clothing.getName() + " for " + formatAsMoney(totalPrice) + " and add them to your inventory.",
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
				
			} else if (index == 8 && Main.game.getDialogueFlags().tradePartner!=null) {
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
			return itemFloor.getDisplayName(true);
		}
		
		@Override
		public String getHeaderContent() {
			if(!Main.game.isInSex())
				return inventoryView();
			else
				return null;
		}

		@Override
		public String getContent() {
			return "<div class='inventoryImage'>" + itemFloor.getSVGString() + "</div>" + itemFloor.getDescription() + itemFloor.getExtraDescription();
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
			return weaponFloor.getDisplayName(true);
		}
		
		@Override
		public String getHeaderContent() {
			if(!Main.game.isInSex())
				return inventoryView();
			else
				return null;
		}

		@Override
		public String getContent() {
			return "<div class='inventoryImage'>" + weaponFloor.getSVGString() + "</div>" + weaponFloor.getDescription();
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
				
				if (Main.game.getPlayer().isInventoryFull())
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
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_JINXED_CLOTHING).getSortingOrder() != 0){
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
			return clothingFloor.getDisplayName(true);
		}
		
		@Override
		public String getHeaderContent() {
			if(!Main.game.isInSex())
				return inventoryView();
			else
				return null;
		}

		@Override
		public String getContent() {
			return "<div class='inventoryImage'>" + clothingFloor.getSVGString() + "</div>" + clothingFloor.getDescription() + clothingFloor.clothingExtraInformation(null);
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
				
				if (Main.game.getPlayer().isInventoryFull())
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
			return weaponEquipped.getDisplayName(true);
		}
		
		@Override
		public String getHeaderContent() {
			if(!Main.game.isInSex())
				return inventoryView();
			else
				return null;
		}

		@Override
		public String getContent() {
			return "<div class='inventoryImage'>" + weaponEquipped.getSVGString() + "</div>" + weaponEquipped.getDescription();
		}
		
		@Override
		public Response getResponse(int index) {
			// Unequip item:
			if (index == 1) {
				if (Main.game.getPlayer().isInventoryFull())
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
				if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
					if(Main.game.isPlayerTileFull()) {
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
					if(Main.game.isPlayerTileFull()) {
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
			return clothingEquipped.getDisplayName(true);
		}
		
		@Override
		public String getHeaderContent() {
			if(!Main.game.isInSex())
				return inventoryView();
			else
				return null;
		}

		@Override
		public String getContent() {
			return "<div class='inventoryImage'>" + clothingEquipped.getSVGString() + "</div>"
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
						if (Main.game.getPlayer().getItemsDiscovered().contains(ItemType.DYE_BRUSH))
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
						
					} else if (Main.game.getPlayer().isInventoryFull()) {
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
					if(Main.game.getPlayer().getLocationPlace().isItemsDisappear()) {
						if (clothingEquipped.isSealed()) {
							return new Response("Drop",
									"<span style='color:" + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + ";'>You can't drop the " + clothingEquipped.getName()
									+ " because " + (clothingEquipped.getClothingType().isPlural() ? "they've" : "it's")
									+ " been jinxed!</span>", null);
							
						} else if(Main.game.isPlayerTileFull()) {
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
							
						} else if(Main.game.isPlayerTileFull()) {
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
						if (Main.game.getPlayer().getItemsDiscovered().contains(ItemType.DYE_BRUSH))
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
					
				} else if (index == 8 && Main.game.getDialogueFlags().tradePartner!=null) {
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

		if (jinxedClothing.size() > 0) {
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
						Main.game.getPlayer().useItem(ItemType.generateItem(ItemType.DYE_BRUSH), Main.game.getPlayer(), false);
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center;'>"
									+ ItemType.DYE_BRUSH.getDyeBrushEffects(clothing.getClothingType(), clothing.getClothingType().getAvailableColours().get(index - 1))
								+ "</p>"
								+ "<p>"
									+ "<b>Your " + clothing.getName() + " " + (clothing.getClothingType().isPlural() ? "have been" : "has been") + " dyed</b> <b style='color:"
										+ clothing.getClothingType().getAvailableColours().get(index - 1).toWebHexString() + ";'>" + clothing.getClothingType().getAvailableColours().get(index - 1).getName() + "</b>!"
								+ "</p>"
								+ "<p>"
									+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
											?"You have <b>" + Main.game.getPlayer().getMapOfDuplicateItems().get(ItemType.generateItem(ItemType.DYE_BRUSH))
													+ "</b> Dye-brush" + (Main.game.getPlayer().getMapOfDuplicateItems().get(ItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
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
						Main.game.getPlayer().useItem(ItemType.generateItem(ItemType.DYE_BRUSH), Main.game.getPlayer(), false);
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center;'>"
									+ ItemType.DYE_BRUSH.getDyeBrushEffects(clothingEquipped.getClothingType(), clothingEquipped.getClothingType().getAvailableColours().get(index - 1))
								+ "</p>"
								+ "<p>"
									+ "<b>Your " + clothingEquipped.getName() + " " + (clothingEquipped.getClothingType().isPlural() ? "have been" : "has been") + " dyed</b> <b style='color:"
										+ clothingEquipped.getClothingType().getAvailableColours().get(index - 1).toWebHexString() + ";'>" + clothingEquipped.getClothingType().getAvailableColours().get(index - 1).getName() + "</b>!"
								+ "</p>"
								+ "<p>"
									+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
											?"You have <b>" + Main.game.getPlayer().getMapOfDuplicateItems().get(ItemType.generateItem(ItemType.DYE_BRUSH))
													+ "</b> Dye-brush" + (Main.game.getPlayer().getMapOfDuplicateItems().get(ItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
											:"You have <b>0</b> Dye-brushes left!")
								+ "</p>");
						clothingEquipped.setColour(clothingEquipped.getClothingType().getAvailableColours().get(index - 1));
						Main.mainController.forceInventoryRender();
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
						Main.game.getPlayer().useItem(ItemType.generateItem(ItemType.DYE_BRUSH), Main.game.getPlayer(), false);
						Sex.setDyeClothingText(
								"<p style='text-align:center;'>"
									+ ItemType.DYE_BRUSH.getDyeBrushEffects(clothingEquipped.getClothingType(), clothingEquipped.getClothingType().getAvailableColours().get(index - 1))
								+ "</p>"
								+ "<p>"
									+ "<b>"+(ownerInSex.isPlayer()?"Your":"[npc.Name]'s")+" " + clothingEquipped.getName() + " " + (clothingEquipped.getClothingType().isPlural() ? "have been" : "has been") + " dyed</b> <b style='color:"
										+ clothingEquipped.getClothingType().getAvailableColours().get(index - 1).toWebHexString() + ";'>" + clothingEquipped.getClothingType().getAvailableColours().get(index - 1).getName() + "</b>!"
								+ "</p>"
								+ "<p>"
									+ (Main.game.getPlayer().hasItemType(ItemType.DYE_BRUSH)
											?"You have <b>" + Main.game.getPlayer().getMapOfDuplicateItems().get(ItemType.generateItem(ItemType.DYE_BRUSH))
													+ "</b> Dye-brush" + (Main.game.getPlayer().getMapOfDuplicateItems().get(ItemType.generateItem(ItemType.DYE_BRUSH)) == 1 ? "" : "es") + " left!"
											:"You have <b>0</b> Dye-brushes left!")
								+ "</p>");
						clothingEquipped.setColour(clothingEquipped.getClothingType().getAvailableColours().get(index - 1));

						Main.mainController.forceInventoryRender();
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
	
	private static String getBuybackItemPanel(AbstractCoreItem itemBuyback, String id, int price) {
		return "<div class='item-background " + getClassFromRarity(itemBuyback.getRarity()) + "'>"
				+ itemBuyback.getSVGString()
				+ "<div class='overlay' id='" + id + "'>"
					+ getItemPriceDiv(price)
				+ "</div>"
				+ "</div>";
	}
	
	private static String getItemCountDiv(int amount) {
		if (amount > 1) {
			return "<div class='item-count'><b>x" + amount + "</b></div>";
		}
		return "";
	}
	
	private static String getItemPriceDiv(int price) {
		return "<div class='item-price'>"
				+ formatAsItemPrice(price)
			+ "</div>";
	}
	
	private static String getClassFromRarity(Rarity rarity) {
		if (rarity != null) {
			return rarity.getName();
		}
		return "unknown";
	}
	
	private static String formatAsMoney(int money) {
		return formatAsMoney(money, "b");
	}
	
	private static String formatAsMoney(int money, String tag) {
		return "<" + tag + " style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</" + tag + "> <" + tag + ">" + money + "</" + tag + ">";
	}
	
	private static String formatAsItemPrice(int money) {
		String tag = "b";
		return "<" + tag + " style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</" + tag + "><" + tag + ">" + money + "</" + tag + ">";
	}

	// Items:
	public static String buyItem(AbstractItem item) {
		int itemPrice = (int) (item.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier());
		if (Main.game.getPlayer().getMoney() < itemPrice)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull())
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			Main.game.getDialogueFlags().tradePartner.removeItem(item);
			Main.game.getPlayer().incrementMoney(-itemPrice);

			String s = "<p style='text-align:center;'>" + "You hand over " + formatAsMoney(itemPrice) + 
					" to " + Main.game.getDialogueFlags().tradePartner.getName("the") + " in exchange for the "
					+ item.getName() + "." + "</p>" + Main.game.getPlayer().addItem(item, false);

			Main.mainController.forceInventoryRender();
			return s;
		}
	}
	
	public static String buyAllItems(AbstractItem item) {
		int itemCount = Main.game.getDialogueFlags().tradePartner.getItemCount(item);
		int totalPrice = (int) (item.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier() * itemCount);
		if (Main.game.getPlayer().getMoney() < totalPrice)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull())
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			List<AbstractItem> items = Main.game.getDialogueFlags().tradePartner.getAllItemsInInventory().stream()
				.filter(item::equals)
				.collect(Collectors.toList());
				
			items.stream().forEach(tradeItem -> {
				Main.game.getPlayer().addItem(tradeItem, false);
				Main.game.getDialogueFlags().tradePartner.removeItem(tradeItem);
			});
			
			Main.game.getPlayer().incrementMoney(-totalPrice);
			
			String s = UtilText.parse(Main.game.getDialogueFlags().tradePartner,
					"<p style='text-align:center;'>"
							+ "You hand over " + formatAsMoney(totalPrice) + " to [npc.name] in exchange for all of the "
							+ item.getName() + " [npc.she] has."
							+ "</br>"
							+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Items added to inventory:</b> <b>"+itemCount+"x</b> <b>"+item.getDisplayName(true)+"</b>"
					+ "</p>");

			Main.mainController.forceInventoryRender();
			return s;
		}
	}

	public static String buyBackItem(AbstractItem item, int price, int buyBackIndex) {

		if (Main.game.getPlayer().getMoney() < price)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull())
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
			Main.game.getPlayer().incrementMoney(-price);

			String s = "<p style='text-align:center;'>" + "You hand over " + formatAsMoney(price) + " to "
					+ Main.game.getDialogueFlags().tradePartner.getName("the") + " in exchange for the " + item.getName() + "." + "</p>" + Main.game.getPlayer().addItem(item, false);

			Main.mainController.forceInventoryRender();
			return s;
		}
	}

	public static String sellItem(AbstractItem item) {
		int itemPrice = (int) (item.getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier());
		Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(item, itemPrice));
		Main.game.getPlayer().removeItem(item);
		Main.game.getPlayer().incrementMoney(itemPrice);

		String s = "<p style='text-align:center;'>" 
						+ "You hand over the " + item.getName() + " to " + Main.game.getDialogueFlags().tradePartner.getName("the") + " in exchange for " + formatAsMoney(itemPrice) + "." 
					+ "</p>";

		Main.mainController.forceInventoryRender();
		return s;
	}

	// Clothing:
	public static String buyClothing(AbstractClothing clothing) {
		int itemPrice = (int) (clothing.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier());
		if (Main.game.getPlayer().getMoney() < itemPrice) {
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		} else if (Main.game.getPlayer().isInventoryFull()) {
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		} else {
			Main.game.getDialogueFlags().tradePartner.removeClothing(clothing);
			// Temporary fix! TODO
			((Nyan)Main.game.getNyan()).removeClothingFromLists(clothing);
			
			Main.game.getPlayer().incrementMoney(-itemPrice);

			String s = "<p style='text-align:center;'>" + "You hand over " + formatAsMoney(itemPrice) + " to " + Main.game.getDialogueFlags().tradePartner.getName("the") 
					+ " in exchange for the " + clothing.getName() + "." + "</p>" + Main.game.getPlayer().addClothing(clothing, false);

			Main.mainController.forceInventoryRender();
			return s;
		}
	}
	
	public static String buyAllClothing(AbstractClothing clothing) {
		int clothingCount = Main.game.getDialogueFlags().tradePartner.getClothingCount(clothing);
		int totalPrice = (int) (clothing.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier() * clothingCount);
		if (Main.game.getPlayer().getMoney() < totalPrice) {
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		} else if (Main.game.getPlayer().isInventoryFull()) {
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		} else {
			
			if(Main.game.getDialogueFlags().tradePartner == Main.game.getNyan()) {
				for(int i=0; i<clothingCount; i++) {
					// Temporary fix! TODO
					((Nyan)Main.game.getNyan()).removeClothingFromLists(clothing);
				}
			}
			
			List<AbstractClothing> clothingItems = Main.game.getDialogueFlags().tradePartner.getAllClothingInInventory().stream()
					.filter(item::equals)
					.collect(Collectors.toList());
					
			clothingItems.stream().forEach(tradeClothing -> {
				Main.game.getPlayer().addClothing(tradeClothing, false);
				Main.game.getDialogueFlags().tradePartner.removeClothing(tradeClothing);
			});
			
			Main.game.getPlayer().incrementMoney(-totalPrice);

			String s = UtilText.parse(Main.game.getDialogueFlags().tradePartner,
					"<p style='text-align:center;'>"
							+ "You hand over " + formatAsMoney(totalPrice) + " to [npc.name] in exchange for all of the "
							+ clothing.getName() + " [npc.she] has."
							+ "</br>"
							+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Clothing added to inventory:</b> <b>"+clothingCount+"x</b> <b>"+clothing.getDisplayName(true)+"</b>"
					+ "</p>");

			Main.mainController.forceInventoryRender();
			return s;
		}
	}

	public static String buyBackClothing(AbstractClothing clothing, int price, int buyBackIndex) {

		if (Main.game.getPlayer().getMoney() < price)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull())
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
			Main.game.getPlayer().incrementMoney(-price);

			String s = "<p style='text-align:center;'>" + "You hand over " + formatAsMoney(price) + " to "
					+ Main.game.getDialogueFlags().tradePartner.getName("the") + " in exchange for the " + clothing.getName() + "." + "</p>" + Main.game.getPlayer().addClothing(clothing, false);

			Main.mainController.forceInventoryRender();
			return s;
		}
	}

	public static String sellClothing(AbstractClothing clothing) {
		int itemPrice = (int) (clothing.getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier());
		Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(clothing, itemPrice));
		Main.game.getPlayer().removeClothing(clothing);
		Main.game.getPlayer().incrementMoney(itemPrice);

		String s = "<p style='text-align:center;'>" 
						+ "You hand over the " + clothing.getName() + " to " + Main.game.getDialogueFlags().tradePartner.getName("the") + " in exchange for " + formatAsMoney(itemPrice) + "." 
				+ "</p>";

		Main.mainController.forceInventoryRender();
		return s;
	}

	// Weapons:
	public static String buyWeapon(AbstractWeapon weapon) {
		int itemPrice = (int) (weapon.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier());
		if (Main.game.getPlayer().getMoney() < itemPrice)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull())
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			Main.game.getDialogueFlags().tradePartner.removeWeapon(weapon);
			Main.game.getPlayer().incrementMoney(-itemPrice);

			String s = "<p style='text-align:center;'>" 
							+ "You hand over " + formatAsMoney(itemPrice) + " to " + Main.game.getDialogueFlags().tradePartner.getName("the") + " in exchange for the " + weapon.getName() + "." 
					+ "</p>" + Main.game.getPlayer().addWeapon(weapon, false);

			Main.mainController.forceInventoryRender();
			return s;
		}
	}
	
	public static String buyAllWeapons(AbstractWeapon weapon) {
		int weaponCount = Main.game.getDialogueFlags().tradePartner.getWeaponCount(weapon);
		int totalPrice = (int) (weapon.getValue() * Main.game.getDialogueFlags().tradePartner.getSellModifier() * weaponCount);
		if (Main.game.getPlayer().getMoney() < totalPrice)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull())
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			List<AbstractWeapon> weapons = Main.game.getDialogueFlags().tradePartner.getAllWeaponsInInventory().stream()
				.filter(weapon::equals)
				.collect(Collectors.toList());
			
			weapons.forEach(tradeWeapon -> {
				Main.game.getPlayer().addWeapon(tradeWeapon, false);
				Main.game.getDialogueFlags().tradePartner.removeWeapon(tradeWeapon);
			});
			
			Main.game.getPlayer().incrementMoney(-totalPrice);

			String s = UtilText.parse(Main.game.getDialogueFlags().tradePartner,
					"<p style='text-align:center;'>"
							+ "You hand over " + formatAsMoney(totalPrice) + " to [npc.name] in exchange for all of the "
							+ weapon.getName() + "s [npc.she] has."
							+ "</br>"
							+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Weapons added to inventory:</b> <b>"+weaponCount+"x</b> <b>"+weapon.getDisplayName(true)+"</b>"
					+ "</p>");

			Main.mainController.forceInventoryRender();
			return s;
		}
	}

	public static String buyBackWeapon(AbstractWeapon weapon, int price, int buyBackIndex) {

		if (Main.game.getPlayer().getMoney() < price)
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>You don't have enough money to buy this!</p>";

		else if (Main.game.getPlayer().isInventoryFull())
			return "<p style='colour:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Your inventory is full, so you can't buy this!</p>";

		else {
			Main.game.getPlayer().getBuybackStack().remove(buyBackIndex);
			Main.game.getPlayer().incrementMoney(-price);

			String s = "<p style='text-align:center;'>" + "You hand over " + formatAsMoney(price) + " to "
					+ Main.game.getDialogueFlags().tradePartner.getName("the") + " in exchange for the " + weapon.getName() + "." + "</p>" + Main.game.getPlayer().addWeapon(weapon, false);

			Main.mainController.forceInventoryRender();
			return s;
		}
	}

	public static String sellWeapon(AbstractWeapon weapon) {
		int itemPrice = (int) (weapon.getValue() * Main.game.getDialogueFlags().tradePartner.getBuyModifier());
		Main.game.getPlayer().getBuybackStack().push(new ShopTransaction(weapon, itemPrice));
		Main.game.getPlayer().removeWeapon(weapon);
		Main.game.getPlayer().incrementMoney(itemPrice);

		String s = "<p style='text-align:center;'>" 
						+ "You hand over the " + weapon.getName() + " to " + Main.game.getDialogueFlags().tradePartner.getName("the") + " in exchange for " + formatAsMoney(itemPrice) + "." 
				+ "</p>";

		Main.mainController.forceInventoryRender();
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

}
