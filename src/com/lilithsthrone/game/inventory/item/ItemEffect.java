package com.lilithsthrone.game.inventory.item;

import java.io.Serializable;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.8
 * @version 0.1.83
 * @author Innoxia
 */
public class ItemEffect implements Serializable, XMLSaving {
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
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element effect = doc.createElement("effect");
		parentElement.appendChild(effect);

		CharacterUtils.addAttribute(doc, effect, "itemEffectType", getItemEffectType().toString());
		CharacterUtils.addAttribute(doc, effect, "primaryModifier", (getPrimaryModifier()==null?"null":getPrimaryModifier().toString()));
		CharacterUtils.addAttribute(doc, effect, "secondaryModifier", (getSecondaryModifier()==null?"null":getSecondaryModifier().toString()));
		CharacterUtils.addAttribute(doc, effect, "potency", (getPotency()==null?"null":getPotency().toString()));
		CharacterUtils.addAttribute(doc, effect, "limit", String.valueOf(getLimit()));
		
		return effect;
	}
	
	public static ItemEffect loadFromXML(Element parentElement, Document doc) {
		return new ItemEffect(
				ItemEffectType.valueOf(parentElement.getAttribute("itemEffectType")),
				(parentElement.getAttribute("primaryModifier").equals("null")?null:TFModifier.valueOf(parentElement.getAttribute("primaryModifier"))),
				(parentElement.getAttribute("secondaryModifier").equals("null")?null:TFModifier.valueOf(parentElement.getAttribute("secondaryModifier"))),
				(parentElement.getAttribute("potency").equals("null")?null:TFPotency.valueOf(parentElement.getAttribute("potency"))),
				Integer.valueOf(parentElement.getAttribute("limit")));
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

		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
			cost/=2;
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