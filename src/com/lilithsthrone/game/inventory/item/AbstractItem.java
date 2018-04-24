package com.lilithsthrone.game.inventory.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.0
 * @version 0.1.97
 * @author Innoxia
 */
public abstract class AbstractItem extends AbstractCoreItem implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;
	
	protected AbstractItemType itemType;
	protected List<ItemEffect> itemEffects;

	public AbstractItem(AbstractItemType itemType) {
		super(itemType.getName(false), itemType.getNamePlural(false), itemType.getSVGString(), itemType.getColourPrimary(), itemType.getRarity(), null, itemType.getItemTags());

		this.itemType = itemType;
		this.itemEffects = itemType.getEffects();
	}
	
	@Override
	public boolean equals (Object o) {
		if(super.equals(o)) {
			return (o instanceof AbstractItem)
					&& ((AbstractItem)o).getItemType().equals(itemType)
					&& ((AbstractItem)o).getEffects().equals(itemEffects);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + itemType.hashCode();
		result = 31 * result + itemEffects.hashCode();
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("item");
		parentElement.appendChild(element);

		CharacterUtils.addAttribute(doc, element, "id", this.getItemType().getId());
		CharacterUtils.addAttribute(doc, element, "name", this.getName());
		CharacterUtils.addAttribute(doc, element, "colour", this.getColour().toString());
		
		Element innerElement = doc.createElement("itemEffects");
		element.appendChild(innerElement);
		
		for(ItemEffect ie : this.getEffects()) {
			ie.saveAsXML(innerElement, doc);
		}
		
		return element;
	}
	
	public static AbstractItem loadFromXML(Element parentElement, Document doc) {
		try {
			AbstractItem item = AbstractItemType.generateItem(ItemType.idToItemMap.get(parentElement.getAttribute("id")));
			
			if(!parentElement.getAttribute("name").isEmpty()) {
				item.setName(parentElement.getAttribute("name"));
			}
			
			List<ItemEffect> effectsToBeAdded = new ArrayList<>();
			Element element = (Element)parentElement.getElementsByTagName("itemEffects").item(0);
			for(int i=0; i<element.getElementsByTagName("effect").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("effect").item(i));
				ItemEffect itemEffect = ItemEffect.loadFromXML(e, doc);
				if(itemEffect == null) {
					System.err.println("Warning: Failed to import ItemEffect");
				}
				else {
					effectsToBeAdded.add(itemEffect);
				}
			}
			item.setItemEffects(effectsToBeAdded);
			
			if(!effectsToBeAdded.isEmpty() && (item.getItemType().getId().equals(ItemType.ELIXIR.getId()) || item.getItemType().getId().equals(ItemType.POTION.getId()))) {
				item.setSVGString(EnchantingUtils.getImportedSVGString(item, (parentElement.getAttribute("colour").isEmpty()?Colour.GENERIC_ARCANE:Colour.valueOf(parentElement.getAttribute("colour"))), effectsToBeAdded));
			}
			
			return item;
		} catch(Exception ex) {
			System.err.println("Warning: An instance of AbstractItem was unable to be imported. ("+parentElement.getAttribute("id")+")");
			return null;
		}
	}

	public AbstractItemType getItemType() {
		return itemType;
	}

	@Override
	public List<ItemEffect> getEffects() {
		return itemEffects;
	}

	public void setItemEffects(List<ItemEffect> itemEffects) {
		this.itemEffects = itemEffects;
	}

	public String applyEffect(GameCharacter user, GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		for(ItemEffect ie : getEffects()) {
			sb.append(UtilText.parse(target, ie.applyEffect(user, target, 1)));
		}
		
		return sb.toString();
	}
	
	// Enchantments:

	@Override
	public int getEnchantmentLimit() {
		return itemType.getEnchantmentLimit();
	}
	
	@Override
	public AbstractItemEffectType getEnchantmentEffect() {
		return itemType.getEnchantmentEffect();
	}
	
	@Override
	public AbstractCoreType getEnchantmentItemType(List<ItemEffect> effects) {
		return itemType.getEnchantmentItemType(effects);
	}
	
	@Override
	public TFEssence getRelatedEssence() {
		return itemType.getRelatedEssence();
	}
	
	// Getters & setters:
	
	public String getName(boolean withDeterminer, boolean withRarityColour) {
		return (withDeterminer
				? (!itemType.getDeterminer().equalsIgnoreCase("a") && !itemType.getDeterminer().equalsIgnoreCase("an")
					? itemType.getDeterminer() + " "
					: (Util.isVowel(name.charAt(0)) ? "an " : "a "))
				: " ")
				+ (withRarityColour ? (" <span style='color: " + rarity.getColour().toWebHexString() + ";'>" + name + "</span>") : " "+name);
	}
	
	public String getDisplayName(boolean withRarityColour) {
		return Util.capitaliseSentence((itemType.getDeterminer()==""?"":itemType.getDeterminer()+" ") + (withRarityColour ? ("<span style='color: " + rarity.getColour().toWebHexString() + ";'>" + name + "</span>") : name));
	}

	@Override
	public String getDescription() {
		return itemType.getDescription();
	}
	
	@Override
	public int getValue() {
		return itemType.getValue();
	}
	
	public String getExtraDescription(GameCharacter user, GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>"
					+ "<b>Effects:</b></br>");
		
		for(ItemEffect ie : getEffects()) {
			for(String s : ie.getEffectsDescription(user, target)) {
				sb.append(s+"</br>");
			}
		}

		sb.append("</p>"
				+ "<p>"
					+ (this.getItemType().isPlural()?"They have":"It has")+" a value of " + UtilText.formatAsMoney(getValue()) + "."
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
	
	public boolean isGift() {
		return itemType.isGift();
	}
	
}
