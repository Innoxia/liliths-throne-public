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
import com.base.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.85
 * @author Innoxia
 */
public class InventorySelectedItemEventListener implements EventListener {
	private AbstractItem item;
	private AbstractClothing clothing, clothingEquipped;
	private AbstractWeapon weapon, weaponEquipped;
	private GameCharacter owner;
	private int buyBackIndex;

	@Override
	public void handleEvent(Event event) {

		if (Main.game.getCurrentDialogueNode().getMapDisplay() == MapDisplay.CHARACTERS_PRESENT)
			return;
		
		TooltipUpdateThread.cancelThreads=true;
		
		if (item != null) {
			if(Main.game.getDialogueFlags().quickTrade) {
				switch(InventoryDialogue.getNPCInventoryInteraction()) {
					case COMBAT:
						break;
					case FULL_MANAGEMENT:
						break;
					case SEX:
						break;
					case TRADING:
						break;
				}
				
				
			} else {
				InventoryDialogue.setOwner(owner);
				InventoryDialogue.setItem(item);
				InventoryDialogue.setBuyBackIndex(buyBackIndex);
				Main.game.setContent(new Response("", "", InventoryDialogue.ITEM_INVENTORY));
			}
			
		} else if (clothing != null) {
			InventoryDialogue.setOwner(owner);
			InventoryDialogue.setClothing(clothing);
			InventoryDialogue.setBuyBackIndex(buyBackIndex);
			Main.game.setContent(new Response("", "", InventoryDialogue.CLOTHING_INVENTORY));
			
		} else if (weapon != null) {
			InventoryDialogue.setOwner(owner);
			InventoryDialogue.setWeapon(weapon);
			InventoryDialogue.setBuyBackIndex(buyBackIndex);
			Main.game.setContent(new Response("", "", InventoryDialogue.WEAPON_INVENTORY));

		} else if (clothingEquipped != null) {
			InventoryDialogue.setOwner(owner);
			InventoryDialogue.setClothing(clothingEquipped);
			InventoryDialogue.setBuyBackIndex(buyBackIndex);
			Main.game.setContent(new Response("", "", InventoryDialogue.CLOTHING_EQUIPPED));

		} else if (weaponEquipped != null) {
			InventoryDialogue.setOwner(owner);
			InventoryDialogue.setWeapon(weaponEquipped);
			InventoryDialogue.setBuyBackIndex(buyBackIndex);
			Main.game.setContent(new Response("", "", InventoryDialogue.WEAPON_EQUIPPED));
		}

		Main.mainController.getTooltip().hide();
	}

	public InventorySelectedItemEventListener setItemInventory(AbstractItem item, GameCharacter owner) {
		return setItemInventory(item, owner, 0);
	}
	public InventorySelectedItemEventListener setItemInventory(AbstractItem item, GameCharacter owner, int buyBackIndex) {
		resetVariables();
		this.item = item;
		this.buyBackIndex=buyBackIndex;
		setOwner(owner);
		return this;
	}

	public InventorySelectedItemEventListener setClothingInventory(AbstractClothing clothing, GameCharacter owner) {
		return setClothingInventory(clothing, owner, 0);
	}
	public InventorySelectedItemEventListener setClothingInventory(AbstractClothing clothing, GameCharacter owner, int buyBackIndex) {
		resetVariables();
		this.clothing = clothing;
		this.buyBackIndex=buyBackIndex;
		setOwner(owner);
		return this;
	}

	public InventorySelectedItemEventListener setWeaponInventory(AbstractWeapon weapon, GameCharacter owner) {
		return setWeaponInventory(weapon, owner, 0);
	}
	public InventorySelectedItemEventListener setWeaponInventory(AbstractWeapon weapon, GameCharacter owner, int buyBackIndex) {
		resetVariables();
		this.weapon = weapon;
		this.buyBackIndex=buyBackIndex;
		setOwner(owner);
		return this;
	}
	
	public InventorySelectedItemEventListener setWeaponEquipped(GameCharacter owner, InventorySlot invSlot) {
		resetVariables();
		
		setOwner(owner);
		
		if (owner != null) {
			if (invSlot == InventorySlot.WEAPON_MAIN) {
				weaponEquipped = owner.getMainWeapon();
			} else {
				weaponEquipped = owner.getOffhandWeapon();
			}
		} else {
			weaponEquipped = null;
		}

		return this;
	}

	public InventorySelectedItemEventListener setClothingEquipped(GameCharacter owner, InventorySlot invSlot) {
		resetVariables();
		
		setOwner(owner);

		if (owner != null) {
			clothingEquipped = owner.getClothingInSlot(invSlot);
		} else {
			clothingEquipped = null;
		}
		
		return this;
	}
	
	private void setOwner(GameCharacter owner) {
		this.owner = owner;
	}

	private void resetVariables() {
		item = null;
		clothing = null;
		clothingEquipped = null;
		weapon = null;
		weaponEquipped = null;
		owner = null;
		buyBackIndex = 0;
	}
}