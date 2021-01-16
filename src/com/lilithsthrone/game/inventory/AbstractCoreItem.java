package com.lilithsthrone.game.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;

/**
 * @since 0.1.0
 * @version 0.3.9
 * @author Innoxia
 */
public abstract class AbstractCoreItem implements XMLSaving {

	protected String name;
	protected String namePlural;
	protected String SVGString;
	protected Rarity rarity;

	protected List<Colour> colours;
	
	protected Map<AbstractAttribute, Integer> attributeModifiers;
	
	protected Set<ItemTag> itemTags;

	public AbstractCoreItem(String name,
			String namePlural,
			String SVGString,
			Colour colour,
			Rarity rarity,
			Map<AbstractAttribute, Integer> attributeModifiers) {
		this(name,
				namePlural,
				SVGString,
				colour,
				rarity,
				attributeModifiers,
				new HashSet<>());
	}
	
	public AbstractCoreItem(String name,
			String namePlural,
			String SVGString,
			Colour colour,
			Rarity rarity,
			Map<AbstractAttribute, Integer> attributeModifiers,
			Set<ItemTag> itemTags) {
		super();
		this.name = name;
		this.namePlural = namePlural;
		this.colours = Util.newArrayListOfValues(colour);
		this.rarity = rarity;
		this.SVGString = SVGString;

		this.attributeModifiers = new HashMap<>();
		this.itemTags = new HashSet<>();
		
		if (attributeModifiers != null) {
			for (Entry<AbstractAttribute, Integer> e : attributeModifiers.entrySet()) {
				this.attributeModifiers.put(e.getKey(), e.getValue());
			}
		}
		
		if(itemTags != null) {
			this.itemTags.addAll(itemTags);
		}
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		System.err.print("Error: Tried to export an abstract item!");
		return null;
	}
	
	public static AbstractCoreItem loadFromXML(Element parentElement, Document doc) {
		System.err.print("Error: Tried to import an abstract item!");
		return null;
	}
	
	// Enchantments:
	
	public boolean isAbleToBeEnchanted() {
		return getEnchantmentEffect() != null
				&& getEnchantmentItemType(null) != null;
	}
	
	public int getEnchantmentLimit() {
		return 100;
	}
	
	public AbstractItemEffectType getEnchantmentEffect() {
		return null;
	}
	
	public AbstractCoreType getEnchantmentItemType(List<ItemEffect> effects) {
		return null;
	}
	
	public AbstractCoreItem enchant(TFModifier primaryModifier, TFModifier secondaryModifier) {
		return this;
	}
	
	// Other:
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof AbstractCoreItem){
			if(((AbstractCoreItem)o).getName().equals(this.getName())
				&& ((AbstractCoreItem)o).getColours().equals(this.getColours())
				&& ((AbstractCoreItem)o).getRarity() == this.getRarity()
				&& ((AbstractCoreItem)o).getAttributeModifiers().equals(this.getAttributeModifiers())
				&& ((AbstractCoreItem)o).getEnchantmentEffect() == getEnchantmentEffect()
				&& ((AbstractCoreItem)o).getEnchantmentItemType(null) == getEnchantmentItemType(null)
				&& ((AbstractCoreItem)o).getItemTags().equals(getItemTags())){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.getName().hashCode();
		result = 31 * result + this.getColours().hashCode();
		result = 31 * result + this.getRarity().hashCode();
		result = 31 * result + this.getAttributeModifiers().hashCode();
		if(getEnchantmentEffect()!=null) {
			result = 31 * result + getEnchantmentEffect().hashCode();
		}
		if(getEnchantmentItemType(null)!=null) {
			result = 31 * result + getEnchantmentItemType(null).hashCode();
		}
		if(getItemTags()!=null) {
			result = 31 * result + getItemTags().hashCode();
		}
		return result;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNamePlural() {
		return namePlural;
	}

	public String getDisplayName(boolean withRarityColour) {
		return Util.capitaliseSentence(UtilText.generateSingularDeterminer(name))+ " "+ (withRarityColour ? ("<span style='color: " + rarity.getColour().toWebHexString() + ";'>" + name + "</span>") : name);
	}
	
	public String getDisplayNamePlural(boolean withRarityColour) {
		return Util.capitaliseSentence((withRarityColour ? ("<span style='color: " + rarity.getColour().toWebHexString() + ";'>" + namePlural + "</span>") : namePlural));
	}
	
	public String getSVGString() {
		return SVGString;
	}
	
	public void setSVGString(String SVGString) {
		this.SVGString = SVGString;
	}

	public abstract String getDescription();

	public abstract int getValue();
	
	public int getPrice(float modifier) {
		return (int) (getValue() * modifier);
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public Colour getColour(int index) {
		try {
			return colours.get(index);
		} catch(Exception ex) {
			return null;
		}
	}
	
	public List<Colour> getColours() {
		return colours;
	}

	public void setColours(List<Colour> colours) {
		this.colours = new ArrayList<>(colours);
	}
	
	public void setColour(int index, Colour colour) {
		if(colours.size()>index) {
			colours.remove(index);
		}
		colours.add(index, colour);
	}

	public Map<AbstractAttribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public void setAttributeModifiers(Map<AbstractAttribute, Integer> attributeModifiers) {
		this.attributeModifiers = attributeModifiers;
	}
	
	public List<ItemEffect> getEffects() {
		return new ArrayList<ItemEffect>();
	}

	public Set<ItemTag> getItemTags() {
		return itemTags;
	}
}
