package com.base.game.inventory.item;

import java.io.Serializable;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.enchanting.TFModifier;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.8
 * @author Innoxia
 */
public abstract class AbstractItem extends AbstractCoreItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected ItemType itemType;
	protected List<ItemEffect> itemEffects;

	public AbstractItem(ItemType itemType) {
		super(itemType.getName(false), itemType.getSVGString(), itemType.getColour(), itemType.getRarity(), null);

		this.itemType = itemType;
		this.itemEffects = itemType.getEffects();
	}
	
	@Override
	public boolean equals (Object o) {//TODO check itemEffects
		if(super.equals(o)) {
			return (o instanceof AbstractItem)
					&& ((AbstractItem)o).getItemType()==itemType
					&& ((AbstractItem)o).getItemEffects().equals(itemEffects);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {//TODO check itemEffects
		int result = super.hashCode();
		result = 31 * result + itemType.hashCode();
		result = 31 * result + itemEffects.hashCode();
		return result;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public List<ItemEffect> getItemEffects() {
		return itemEffects;
	}

	public void setItemEffects(List<ItemEffect> itemEffects) {
		this.itemEffects = itemEffects;
	}

	public String applyEffect(GameCharacter user, GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		for(ItemEffect ie : getItemEffects()) {
			sb.append(ie.applyEffect(user, target));
		}
		
		return sb.toString();
	}
	
	// Enchantments:

	@Override
	public ItemEffectType getEnchantmentEffect() {
		return itemType.getEnchantmentEffect();
	}
	
	@Override
	public ItemType getEnchantmentItemType() {
		return itemType.getEnchantmentItemType();
	}
	
	@Override
	public TFEssence getRelatedEssence() {
		return itemType.getRelatedEssence();
	}
	
	// Getters & setters:
	
	public String getDisplayName(boolean withRarityColour) {
		return Util.capitaliseSentence((itemType.getDeterminer()==""?"":itemType.getDeterminer()+" ") + (withRarityColour ? ("<span style='color: " + rarity.getColour()() + ";'>" + name + "</span>") : name));
	}

	@Override
	public String getDescription() {
		return itemType.getDescription();
	}
	
	@Override
	public int getValue() {
		int additionalValue=0;
		if(getItemEffects()!=null) {
			for(ItemEffect ie : getItemEffects()) {
				additionalValue+=5;
				if(ie.getPrimaryModifier()!=null) {
					if(ie.getPrimaryModifier()!=TFModifier.NONE) {
						switch(ie.getPrimaryModifier().getRarity()) {
							case JINXED:
								additionalValue++;
								break;
							case COMMON:
								additionalValue+=5;
								break;
							case UNCOMMON:
								additionalValue+=10;
								break;
							case RARE:
								additionalValue+=20;
								break;
							case EPIC:
								additionalValue+=40;
								break;
							case LEGENDARY:
								additionalValue+=60;
								break;
						}
					}
				}
				if(ie.getSecondaryModifier()!=null) {
					if(ie.getSecondaryModifier()!=TFModifier.NONE) {
						switch(ie.getSecondaryModifier().getRarity()) {
							case JINXED:
								additionalValue++;
								break;
							case COMMON:
								additionalValue+=5;
								break;
							case UNCOMMON:
								additionalValue+=10;
								break;
							case RARE:
								additionalValue+=20;
								break;
							case EPIC:
								additionalValue+=40;
								break;
							case LEGENDARY:
								additionalValue+=60;
								break;
						}
					}
				}
			}
		}
		return itemType.getValue()+additionalValue;
	}
	
	public String getExtraDescription() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>"
					+ "<b>Effects:</b></br>");
		
		for(ItemEffect ie : getItemEffects()) {
			for(String s : ie.getEffectsDescription()) {
				sb.append(s+"</br>");
			}
		}

		sb.append("</p>"
				+ "<p>"
					+ "It has a value of <b style='color: " + Colour.CURRENCY() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>" + getValue() + "</b>."
				+ "</p>");
		
		return sb.toString();
	}

	public String getPathName() {
		return itemType.getPathName();
	}

	public boolean isConsumedOnUse() {
		return itemType.isConsumedOnUse();
	}
	
	public String getUseDescription(GameCharacter user, GameCharacter target) {
		return itemType.getUseDescription(user, target);
	}

	public boolean isAbleToBeUsedFromInventory() {
		return itemType.isAbleToBeUsedFromInventory();
	}
	
	public String getUnableToBeUsedFromInventoryDescription() {
		return itemType.getUnableToBeUsedFromInventoryDescription();
	}
	
	public boolean isAbleToBeUsed(GameCharacter target) {
		return itemType.isAbleToBeUsed(target);
	}
	
	public String getUnableToBeUsedDescription(GameCharacter target) {
		return itemType.getUnableToBeUsedDescription(target);
	}

	public boolean isAbleToBeUsedInCombat(){
		return itemType.isAbleToBeUsedInCombat();
	}

	public boolean isAbleToBeUsedInSex(){
		return itemType.isAbleToBeUsedInSex();
	}
}
