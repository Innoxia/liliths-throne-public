package com.base.controller.eventListeners;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.utils.EnchantmentDialogue;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.enchanting.TFModifier;
import com.base.main.Main;

/**
 * @since 0.1.7
 * @version 0.1.8
 * @author Innoxia
 */
public class EnchantmentEventListener implements EventListener {
	private AbstractCoreItem itemToEnchant;
	private TFModifier primaryModifier, secondaryModifier;

	@Override
	public void handleEvent(Event event) {
		
		if (itemToEnchant != null) {
			if(itemToEnchant.getEnchantmentEffect()!=null) {
				EnchantmentDialogue.primaryMod = TFModifier.NONE;
				EnchantmentDialogue.secondaryMod = TFModifier.NONE;
				EnchantmentDialogue.ingredient = itemToEnchant;
			}
			
		} else if(primaryModifier != null) {
			EnchantmentDialogue.primaryMod = primaryModifier;
			if(!EnchantmentDialogue.ingredient.getEnchantmentEffect().getSecondaryModifiers(primaryModifier).contains(EnchantmentDialogue.secondaryMod))
				EnchantmentDialogue.secondaryMod = TFModifier.NONE;
			
		} else if(secondaryModifier != null) {
			EnchantmentDialogue.secondaryMod = secondaryModifier;
			
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