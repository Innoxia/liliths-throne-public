package com.base.controller.eventListeners;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.base.controller.TooltipUpdateThread;
import com.base.game.character.GameCharacter;
import com.base.game.dialogue.MapDisplay;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.utils.InventoryDialogue;
import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.inventory.weapon.AbstractWeapon;
import com.base.game.sex.Sex;
import com.base.main.Main;
import com.base.rendering.RenderingEngine;
import com.base.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public class InventorySelectedItemEventListener implements EventListener {
	private AbstractItem item, itemFloor;
	private AbstractClothing clothing, clothingFloor, clothingEquipped;
	private AbstractWeapon weapon, weaponFloor, weaponEquipped;
	private GameCharacter owner;
	private int buyBackPrice, buyBackIndex;

	@Override
	public void handleEvent(Event event) {

		if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.CHARACTERS_PRESENT)
			return;
		
		TooltipUpdateThread.cancelThreads=true;
		
		if (item != null) {
			InventoryDialogue.setItem(item);
			
			if (Main.game.isInCombat()) {
				if(item.isAbleToBeUsedInCombat())
					Main.game.setContent(new Response("", "", InventoryDialogue.ITEM_INVENTORY));
				
			} else if(Main.game.isInSex()){
				if(item.isAbleToBeUsedInSex())
					Main.game.setContent(new Response("", "", InventoryDialogue.ITEM_INVENTORY));
				
			} else if (Main.game.getDialogueFlags().tradePartner == null){
				if (Main.game.getDialogueFlags().quickTrade){
					Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropItem(item) + "</p>");
					Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
				}else
					Main.game.setContent(new Response("", "", InventoryDialogue.ITEM_INVENTORY));
				
			} else {
				if (InventoryDialogue.isBuyback()) {
					InventoryDialogue.setBuyBackPrice(buyBackPrice);
					InventoryDialogue.setBuyBackIndex(buyBackIndex);

					if (owner == null) {
						if (Main.game.getDialogueFlags().quickTrade) {
							Main.game.getTextStartStringBuilder().append(InventoryDialogue.buyBackItem(item, buyBackPrice, buyBackIndex));
							Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
						} else {
							Main.game.setContent(new Response("", "", InventoryDialogue.ITEM_TRADER));
						}

					} else {
						if (Main.game.getDialogueFlags().quickTrade) {
							if (Main.game.getDialogueFlags().tradePartner.willBuy(item)) {
								Main.game.getTextStartStringBuilder().append(InventoryDialogue.sellItem(item));
								Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
							}
						} else {
							Main.game.setContent(new Response("", "", InventoryDialogue.ITEM_INVENTORY));
						}
					}

				} else if (owner == Main.game.getDialogueFlags().tradePartner) {
					if (Main.game.getDialogueFlags().quickTrade) {

						Main.game.getTextStartStringBuilder().append(InventoryDialogue.buyItem(item));
						Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));

					} else {
						Main.game.setContent(new Response("", "", InventoryDialogue.ITEM_TRADER));
					}
				} else {
					if (Main.game.getDialogueFlags().quickTrade) {

						if (Main.game.getDialogueFlags().tradePartner.willBuy(item)) {
							Main.game.getTextStartStringBuilder().append(InventoryDialogue.sellItem(item));
							Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
						}
					} else {
						Main.game.setContent(new Response("", "", InventoryDialogue.ITEM_INVENTORY));
					}
				}
			}

		} else if (clothing != null) {
			InventoryDialogue.setClothing(clothing);
			
			if(Main.game.isInSex()){
				// Do nothing...
			}else if (Main.game.getDialogueFlags().tradePartner == null){
				if (Main.game.getDialogueFlags().quickTrade){
					Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropClothing(clothing) + "</p>");
					Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
				}else
					Main.game.setContent(new Response("", "", InventoryDialogue.CLOTHING_INVENTORY));
				
			}else {
				if (InventoryDialogue.isBuyback()) {
					InventoryDialogue.setBuyBackPrice(buyBackPrice);
					InventoryDialogue.setBuyBackIndex(buyBackIndex);

					if (owner == null) {
						if (Main.game.getDialogueFlags().quickTrade) {
							Main.game.getTextStartStringBuilder().append(InventoryDialogue.buyBackClothing(clothing, buyBackPrice, buyBackIndex));
							Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
						} else {
							Main.game.setContent(new Response("", "", InventoryDialogue.CLOTHING_TRADER));
						}

					} else {
						if (Main.game.getDialogueFlags().quickTrade) {
							if (Main.game.getDialogueFlags().tradePartner.willBuy(clothing)) {
								Main.game.getTextStartStringBuilder().append(InventoryDialogue.sellClothing(clothing));
								Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
							}
						} else {
							Main.game.setContent(new Response("", "", InventoryDialogue.CLOTHING_INVENTORY));
						}
					}

				} else if (owner == Main.game.getDialogueFlags().tradePartner) {
					if (Main.game.getDialogueFlags().quickTrade) {

						Main.game.getTextStartStringBuilder().append(InventoryDialogue.buyClothing(clothing));
						Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));

					} else {
						Main.game.setContent(new Response("", "", InventoryDialogue.CLOTHING_TRADER));
					}
				} else {
					if (Main.game.getDialogueFlags().quickTrade) {

						if (Main.game.getDialogueFlags().tradePartner.willBuy(clothing)) {
							Main.game.getTextStartStringBuilder().append(InventoryDialogue.sellClothing(clothing));
							Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
						}

					} else {
						Main.game.setContent(new Response("", "", InventoryDialogue.CLOTHING_INVENTORY));
					}
				}
			}

		} else if (weapon != null) {
			InventoryDialogue.setWeapon(weapon);
			
			if(Main.game.isInSex()){
				// Do nothing...
			}else if (Main.game.getDialogueFlags().tradePartner == null){
				if (Main.game.getDialogueFlags().quickTrade){
					Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().dropWeapon(weapon) + "</p>");
					Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
				}else
					Main.game.setContent(new Response("", "", InventoryDialogue.WEAPON_INVENTORY));
				
			}else {
				if (InventoryDialogue.isBuyback()) {
					InventoryDialogue.setBuyBackPrice(buyBackPrice);
					InventoryDialogue.setBuyBackIndex(buyBackIndex);

					if (owner == null) {
						if (Main.game.getDialogueFlags().quickTrade) {
							Main.game.getTextStartStringBuilder().append(InventoryDialogue.buyBackWeapon(weapon, buyBackPrice, buyBackIndex));
							Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
						} else {
							Main.game.setContent(new Response("", "", InventoryDialogue.WEAPON_TRADER));
						}

					} else {
						if (Main.game.getDialogueFlags().quickTrade) {
							if (Main.game.getDialogueFlags().tradePartner.willBuy(weapon)) {
								Main.game.getTextStartStringBuilder().append(InventoryDialogue.sellWeapon(weapon));
								Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
							}
						} else {
							Main.game.setContent(new Response("", "", InventoryDialogue.WEAPON_INVENTORY));
						}
					}

				} else if (owner == Main.game.getDialogueFlags().tradePartner) {
					if (Main.game.getDialogueFlags().quickTrade) {

						Main.game.getTextStartStringBuilder().append(InventoryDialogue.buyWeapon(weapon));
						Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));

					} else {
						Main.game.setContent(new Response("", "", InventoryDialogue.WEAPON_TRADER));
					}
				} else {
					if (Main.game.getDialogueFlags().quickTrade) {

						if (Main.game.getDialogueFlags().tradePartner.willBuy(weapon)) {
							Main.game.getTextStartStringBuilder().append(InventoryDialogue.sellWeapon(weapon));
							Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
						}
					} else {
						Main.game.setContent(new Response("", "", InventoryDialogue.WEAPON_INVENTORY));
					}
				}
			}

		} else if (itemFloor != null) {
			if(Main.game.isInSex()){
				// Do nothing...
			}else if (Main.game.getDialogueFlags().quickTrade){
				Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().addItem(itemFloor, true) + "</p>");
				Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
				
			}else{
				InventoryDialogue.setItemFloor(itemFloor);
				Main.game.setContent(new Response("", "", InventoryDialogue.ITEM_FLOOR));
			}

		} else if (clothingFloor != null) {
			if(Main.game.isInSex()){
				// Do nothing...
			}else if (Main.game.getDialogueFlags().quickTrade){
				Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().addClothing(clothingFloor, true) + "</p>");
				Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));

			}else{
				InventoryDialogue.setClothingFloor(clothingFloor);
				Main.game.setContent(new Response("", "", InventoryDialogue.CLOTHING_FLOOR));
			}

		} else if (weaponFloor != null) {
			if(Main.game.isInSex()){
				// Do nothing...
			}else if (Main.game.getDialogueFlags().quickTrade){
				Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>" + Main.game.getPlayer().addWeapon(weaponFloor, true) + "</p>");
				Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));

			}else{
				InventoryDialogue.setWeaponFloor(weaponFloor);
				Main.game.setContent(new Response("", "", InventoryDialogue.WEAPON_FLOOR));
			}

		} else if (clothingEquipped != null) {
			if (Main.game.isInCombat()) {
				if (RenderingEngine.ENGINE.getCharactersInventoryToRender().isPlayer()) {
					Main.game.getTextEndStringBuilder().append("<p><span style='color:" + Colour.GENERIC_BAD + ";'>You can't alter your clothing while in combat!</span></p>");
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				} else {
					Main.game.getTextEndStringBuilder().append(
							"<p><span style='color:" + Colour.GENERIC_BAD + ";'>[npc.Name] isn't going to let you play with [npc.her] clothing!</span></p>");
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}
			} else if (Main.game.isInSex()) {
				InventoryDialogue.setClothingEquipped(clothingEquipped);
					
				if (RenderingEngine.ENGINE.getCharactersInventoryToRender().isPlayer()){
					if(Sex.getSexManager().isPlayerCanRemoveOwnClothes()){
						if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL)
							Main.game.saveDialogueNode();

						InventoryDialogue.setOwnerInSex(Main.game.getPlayer());
						Main.game.setContent(new Response("", "", InventoryDialogue.CLOTHING_EQUIPPED));
					}else{
						Main.game.flashMessage(Colour.GENERIC_BAD, "Cannot manage your own clothing!");
//						Main.game.getTextEndStringBuilder().append("<p><span style='color:" + Colour.GENERIC_BAD + ";'>You can't manage your own clothes in this sex scene!</span></p>");
//						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));	
					}
					
				}else{
					if(Sex.getSexManager().isPlayerCanRemovePartnersClothes()){
						if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.NORMAL)
							Main.game.saveDialogueNode();

						InventoryDialogue.setOwnerInSex(Sex.getPartner());
						Main.game.setContent(new Response("", "", InventoryDialogue.CLOTHING_EQUIPPED));
					}else{
						Main.game.flashMessage(Colour.GENERIC_BAD, "Cannot manage "+ Sex.getPartner().getName("the") + "'s clothing!");
//						Main.game.getTextEndStringBuilder().append("<p><span style='color:" + Colour.GENERIC_BAD + ";'>You can't manage "
//								+ Sex.getPartner().getName("the") + "'s clothes in this sex scene!</span></p>");
//						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}
					
				}
			} else {
				InventoryDialogue.setClothingEquipped(clothingEquipped);
				Main.game.setContent(new Response("", "", InventoryDialogue.CLOTHING_EQUIPPED));
			}

		} else if (weaponEquipped != null) {
			if (Main.game.isInCombat()) {
				if (RenderingEngine.ENGINE.getCharactersInventoryToRender().isPlayer()) {
					Main.game.getTextEndStringBuilder().append("<p><span style='color:" + Colour.GENERIC_BAD + ";'>You can't change your weapons while in combat!</span></p>");
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				} else {
					Main.game.getTextEndStringBuilder().append(
							"<p><span style='color:" + Colour.GENERIC_BAD + ";'>[npc.Name] isn't going to let you play with [npc.her] weapons!</span></p>");
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}
			} else if (Main.game.isInSex()) {
//				if (RenderingEngine.ENGINE.getCharactersInventoryToRender().isPlayer()) {
//					Main.game.getTextEndStringBuilder().append("<p><span style='color:" + Colour.GENERIC_BAD + ";'>You can't change your weapons while having sex!</span></p>");
//					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//				} else {
//					Main.game.getTextEndStringBuilder().append(GenericSentence.parseTextForGenderReplacement(Combat.COMBAT.getOpponent(),
//							"<p><span style='color:" + Colour.GENERIC_BAD + ";'>" + "You're too excited to worry about playing with " + Combat.COMBAT.getOpponent().getNameWithDeterminer("the") + "'s weapons right now!</span></p>"));
//					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
//				}
			} else {
				InventoryDialogue.setWeaponEquipped(weaponEquipped);
				Main.game.setContent(new Response("", "", InventoryDialogue.WEAPON_EQUIPPED));
			}
		}

		Main.mainController.getTooltip().hide();
	}

	public InventorySelectedItemEventListener setItemInventory(AbstractItem item, GameCharacter owner) {
		resetVariables();
		this.item = item;
		this.owner = owner;

		return this;
	}

	public InventorySelectedItemEventListener setClothingInventory(AbstractClothing clothing, GameCharacter owner) {
		resetVariables();
		this.clothing = clothing;
		this.owner = owner;

		return this;
	}

	public InventorySelectedItemEventListener setWeaponInventory(AbstractWeapon weapon, GameCharacter owner) {
		resetVariables();
		this.weapon = weapon;
		this.owner = owner;

		return this;
	}

	public InventorySelectedItemEventListener setItemBuyback(AbstractItem item, int buyBackPrice, int buyBackIndex) {
		resetVariables();
		this.item = item;
		this.buyBackPrice = buyBackPrice;
		this.buyBackIndex = buyBackIndex;

		return this;
	}

	public InventorySelectedItemEventListener setClothingBuyback(AbstractClothing clothing, int buyBackPrice, int buyBackIndex) {
		resetVariables();
		this.clothing = clothing;
		this.buyBackPrice = buyBackPrice;
		this.buyBackIndex = buyBackIndex;

		return this;
	}

	public InventorySelectedItemEventListener setWeaponBuyback(AbstractWeapon weapon, int buyBackPrice, int buyBackIndex) {
		resetVariables();
		this.weapon = weapon;
		this.buyBackPrice = buyBackPrice;
		this.buyBackIndex = buyBackIndex;

		return this;
	}

	public InventorySelectedItemEventListener setItemFloor(AbstractItem itemFloor) {
		resetVariables();
		this.itemFloor = itemFloor;

		return this;
	}

	public InventorySelectedItemEventListener setClothingFloor(AbstractClothing clothingFloor) {
		resetVariables();
		this.clothingFloor = clothingFloor;

		return this;
	}

	public InventorySelectedItemEventListener setWeaponFloor(AbstractWeapon weaponFloor) {
		resetVariables();
		this.weaponFloor = weaponFloor;

		return this;
	}

	public InventorySelectedItemEventListener setWeaponEquipped(InventorySlot invSlot) {
		resetVariables();

		if (RenderingEngine.ENGINE.getCharactersInventoryToRender() != null) {
			if (invSlot == InventorySlot.WEAPON_MAIN)
				weaponEquipped = RenderingEngine.ENGINE.getCharactersInventoryToRender().getMainWeapon();
			else
				weaponEquipped = RenderingEngine.ENGINE.getCharactersInventoryToRender().getOffhandWeapon();
		} else {
			weaponEquipped = null;
		}

		return this;
	}

	public InventorySelectedItemEventListener setClothingEquipped(InventorySlot invSlot) {
		resetVariables();

		if (RenderingEngine.ENGINE.getCharactersInventoryToRender() != null)
			clothingEquipped = RenderingEngine.ENGINE.getCharactersInventoryToRender().getClothingInSlot(invSlot);
		else
			clothingEquipped = null;

		return this;
	}

	private void resetVariables() {
		item = null;
		itemFloor = null;
		clothing = null;
		clothingFloor = null;
		clothingEquipped = null;
		weapon = null;
		weaponFloor = null;
		weaponEquipped = null;
		owner = null;
		buyBackPrice = 0;
		buyBackIndex = 0;
	}
}