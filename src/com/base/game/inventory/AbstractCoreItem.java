package com.base.game.inventory;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

import com.base.game.character.attributes.Attribute;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.enchanting.TFModifier;
import com.base.game.inventory.item.ItemEffectType;
import com.base.game.inventory.item.ItemType;
import com.base.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.1.8
 * @author Innoxia
 */
public abstract class AbstractCoreItem implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String name, SVGString;
	protected Colour colourShade;
	protected Rarity rarity;

	protected Map<Attribute, Integer> attributeModifiers;
	protected TFEssence relatedEssence;

	public AbstractCoreItem(String name, String SVGString, Colour colour, Rarity rarity, Map<Attribute, Integer> attributeModifiers) {
		super();
		this.name = name;
		this.colourShade = colour;
		this.rarity = rarity;
		this.SVGString = SVGString;

		this.attributeModifiers = new EnumMap<>(Attribute.class);
		
		relatedEssence = null;

		if (attributeModifiers != null)
			for (Entry<Attribute, Integer> e : attributeModifiers.entrySet())
				this.attributeModifiers.put(e.getKey(), e.getValue());

	}
	
	// Enchantments:
	
	public boolean isAbleToBeEnchanted() {
		return getEnchantmentEffect() != null
				&& getEnchantmentItemType() != null;
	}
	
	public ItemEffectType getEnchantmentEffect() {
		return null;
	}
	
	public ItemType getEnchantmentItemType() {
		return null;
	}
	
	public AbstractCoreItem enchant(TFEssence essence, TFModifier primaryModifier, TFModifier secondaryModifier) {
		return this;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TFEssence getRelatedEssence() {
		return relatedEssence;
	}
	public void setRelatedEssence(TFEssence relatedEssence) {
		this.relatedEssence = relatedEssence;
	}

	// Other:
	
	@Override
	public boolean equals (Object o) {
		if(o instanceof AbstractCoreItem){
			if(((AbstractCoreItem)o).getName().equals(name)
				&& ((AbstractCoreItem)o).getColour() == colourShade
				&& ((AbstractCoreItem)o).getRarity() == rarity
				&& ((AbstractCoreItem)o).getAttributeModifiers().equals(attributeModifiers)
				&& ((AbstractCoreItem)o).getEnchantmentEffect() == getEnchantmentEffect()
				&& ((AbstractCoreItem)o).getEnchantmentItemType() == getEnchantmentItemType()
				&& ((AbstractCoreItem)o).getRelatedEssence() == getRelatedEssence()){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + name.hashCode();
		result = 31 * result + colourShade.hashCode();
		result = 31 * result + rarity.hashCode();
		result = 31 * result + attributeModifiers.hashCode();
		if(getEnchantmentEffect()!=null)
			result = 31 * result + getEnchantmentEffect().hashCode();
		if(getEnchantmentItemType()!=null)
		result = 31 * result + getEnchantmentItemType().hashCode();
		if(getRelatedEssence()!=null)
			result = 31 * result + getRelatedEssence().hashCode();
		return result;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSVGString() {
		return SVGString;
	}
	
	public void setSVGString(String SVGString) {
		this.SVGString = SVGString;
	}

	public abstract String getDescription();

	public abstract int getValue();

	public Rarity getRarity() {
		return rarity;
	}
	
	/**
	 * @return the name of a css class to use as a displayed rarity in inventory screens
	 */
	public String getDisplayRarity() {
		return rarity.getName();
	}

	public Colour getColour() {
		return colourShade;
	}

	public void setColour(Colour Colour) {
		this.colourShade = Colour;
	}

	public Map<Attribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public void setAttributeModifiers(Map<Attribute, Integer> attributeModifiers) {
		this.attributeModifiers = attributeModifiers;
	}

}
