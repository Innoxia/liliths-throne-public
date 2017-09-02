package com.base.game.inventory.item;

import java.io.Serializable;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.inventory.enchanting.TFModifier;
import com.base.game.inventory.enchanting.TFPotency;

/**
 * @since 0.1.8
 * @version 0.1.83
 * @author Innoxia
 */
public class ItemEffect implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ItemEffectType itemEffectType;
	private TFModifier primaryModifier, secondaryModifier;
	private TFPotency potency;
	private int limit;
	
	public ItemEffect(ItemEffectType itemEffectType, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit) {
		this.itemEffectType = itemEffectType;
		this.primaryModifier = primaryModifier;
		this.secondaryModifier = secondaryModifier;
		this.potency = potency;
		this.limit = limit;
	}
	
	@Override
	public boolean equals (Object o) {
		if(o instanceof ItemEffect){
			if(((ItemEffect)o).getItemEffectType()==itemEffectType
				&& ((ItemEffect)o).getPrimaryModifier() == primaryModifier
				&& ((ItemEffect)o).getSecondaryModifier() == secondaryModifier
				&& ((ItemEffect)o).getSecondaryModifier() == secondaryModifier
				&& ((ItemEffect)o).getPotency() == potency
				&& ((ItemEffect)o).getLimit() == limit){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + itemEffectType.hashCode();
		if(primaryModifier!=null) {
			result = 31 * result + primaryModifier.hashCode();
		}
		if(secondaryModifier!=null) {
			result = 31 * result + secondaryModifier.hashCode();
		}
		if(potency!=null) {
			result = 31 * result + potency.hashCode();
		}
		result = 31 * result + limit;
		return result;
	}
	
	public String applyEffect(GameCharacter user, GameCharacter target) {
		return getItemEffectType().applyEffect(getPrimaryModifier(), getSecondaryModifier(), getPotency(), getLimit(), user, target);
	}
	
	public List<String> getEffectsDescription(GameCharacter user, GameCharacter target) {
		return getItemEffectType().getEffectsDescription(getPrimaryModifier(), getSecondaryModifier(), getPotency(), getLimit(), user, target);
	}
	
	public int getCost() {
		int cost = 1;
		if(getPrimaryModifier()!=null) {
			if(getPrimaryModifier()!=TFModifier.NONE) {
				cost+=getPrimaryModifier().getValue();
			}
		}
		if(getSecondaryModifier()!=null) {
			if(getSecondaryModifier()!=TFModifier.NONE) {
				cost+=getSecondaryModifier().getValue();
			}
		}
		if(getPotency()!=null) {
			cost+=getPotency().getValue();
		}
		if(getLimit()!=-1) {
			cost+=1;
		}
		return cost;
	}
	
	public ItemEffectType getItemEffectType() {
		return itemEffectType;
	}

	public void setItemEffectType(ItemEffectType itemEffectType) {
		this.itemEffectType = itemEffectType;
	}

	public TFModifier getPrimaryModifier() {
		return primaryModifier;
	}

	public void setPrimaryModifier(TFModifier primaryModifier) {
		this.primaryModifier = primaryModifier;
	}

	public TFModifier getSecondaryModifier() {
		return secondaryModifier;
	}

	public void setSecondaryModifier(TFModifier secondaryModifier) {
		this.secondaryModifier = secondaryModifier;
	}

	public TFPotency getPotency() {
		return potency;
	}

	public void setPotency(TFPotency potency) {
		this.potency = potency;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
}