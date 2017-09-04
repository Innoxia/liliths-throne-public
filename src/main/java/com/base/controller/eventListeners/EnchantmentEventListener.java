package com.base.controller.eventListeners;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.utils.EnchantmentDialogue;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.enchanting.TFModifier;
import com.base.game.inventory.enchanting.TFPotency;
import com.base.main.Main;

/**
 * @since 0.1.7
 * @version 0.1.83
 * @author Innoxia
 */
public class EnchantmentEventListener implements EventListener {
	private AbstractCoreItem itemToEnchant;
	private TFModifier primaryModifier, secondaryModifier;

	@Override
	public void handleEvent(Event event) {
		
		if (itemToEnchant != null) {
			if(itemToEnchant.getEnchantmentEffect()!=null) {
				EnchantmentDialogue.resetEnchantmentVariables();
				EnchantmentDialogue.ingredient = itemToEnchant;
			}
			
		} else if(primaryModifier != null) {
			EnchantmentDialogue.primaryMod = primaryModifier;
			
		} else if(secondaryModifier != null) {
			EnchantmentDialogue.secondaryMod = secondaryModifier;
		}
		
		if(!EnchantmentDialogue.ingredient.getEnchantmentEffect().getSecondaryModifiers(EnchantmentDialogue.primaryMod).contains(EnchantmentDialogue.secondaryMod)) {
			EnchantmentDialogue.secondaryMod = TFModifier.NONE;
		}
		if(!EnchantmentDialogue.ingredient.getEnchantmentEffect().getPotencyModifiers(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod).contains(EnchantmentDialogue.potency)) {
			EnchantmentDialogue.potency = TFPotency.MINOR_BOOST;
		}
		if(!EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod).contains(EnchantmentDialogue.limit)) {
			EnchantmentDialogue.limit = 0;
		}
		
		Main.game.setContent(new Response("Enchanting", "Start enchanting.", EnchantmentDialogue.ENCHANTMENT_MENU));
	}

	public EnchantmentEventListener setItemToEnchant(AbstractCoreItem itemToEnchant) {
		resetVariables();
		this.itemToEnchant = itemToEnchant;

		return this;
	}
	
	public EnchantmentEventListener setPrimaryModifier(TFModifier primaryModifier) {
		resetVariables();
		this.primaryModifier = primaryModifier;

		return this;
	}
	
	public EnchantmentEventListener setSecondaryModifier(TFModifier secondaryModifier) {
		resetVariables();
		this.secondaryModifier = secondaryModifier;

		return this;
	}

	private void resetVariables() {
		itemToEnchant = null;
		primaryModifier = null;
		secondaryModifier = null;
	}
}