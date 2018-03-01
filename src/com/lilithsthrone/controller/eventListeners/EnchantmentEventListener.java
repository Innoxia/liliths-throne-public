package com.lilithsthrone.controller.eventListeners;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.7
 * @version 0.2.0
 * @author Innoxia
 */
public class EnchantmentEventListener implements EventListener {
	private AbstractCoreItem itemToEnchant;
	private TFModifier primaryModifier, secondaryModifier;
	private TFPotency potency;
	private boolean effect;
	private int effectIndex;
	private int limit;

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
			
		} else if(potency != null) {
			EnchantmentDialogue.potency = potency;
			
		} else if(effect) {
			ItemEffect e = EnchantmentDialogue.effects.get(effectIndex);
			EnchantmentDialogue.effects.remove(e);
			
		} else if(limit != EnchantmentDialogue.limit) {
			EnchantmentDialogue.limit = limit;
		}
		
		if(!EnchantmentDialogue.ingredient.getEnchantmentEffect().getPrimaryModifiers().contains(EnchantmentDialogue.primaryMod)) {
			EnchantmentDialogue.primaryMod = EnchantmentDialogue.ingredient.getEnchantmentEffect().getPrimaryModifiers().get(0);
		}
		if(!EnchantmentDialogue.ingredient.getEnchantmentEffect().getSecondaryModifiers(EnchantmentDialogue.primaryMod).contains(EnchantmentDialogue.secondaryMod)) {
			EnchantmentDialogue.secondaryMod = EnchantmentDialogue.ingredient.getEnchantmentEffect().getSecondaryModifiers(EnchantmentDialogue.primaryMod).get(0);
		}
		if(!EnchantmentDialogue.ingredient.getEnchantmentEffect().getPotencyModifiers(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod).contains(EnchantmentDialogue.potency)) {
			EnchantmentDialogue.potency = TFPotency.MINOR_BOOST;
		}
		if(EnchantmentDialogue.limit > EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod)) {
			EnchantmentDialogue.limit = EnchantmentDialogue.ingredient.getEnchantmentEffect().getLimits(EnchantmentDialogue.primaryMod, EnchantmentDialogue.secondaryMod);
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
	
	public EnchantmentEventListener setPotency(TFPotency potency) {
		resetVariables();
		this.potency = potency;

		return this;
	}
	
	public EnchantmentEventListener removeEffect(int effectIndex) {
		resetVariables();
		effect = true;
		this.effectIndex = effectIndex;

		return this;
	}
	
	public EnchantmentEventListener setLimit(int limit) {
		resetVariables();
		this.limit = limit;

		return this;
	}

	private void resetVariables() {
		effect = false;
		effectIndex = 0;
		itemToEnchant = null;
		primaryModifier = null;
		secondaryModifier = null;
		potency = null;
		limit = 0;
	}
}