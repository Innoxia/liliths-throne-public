package com.lilithsthrone.game.inventory.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.9.2
 * @author Innoxia
 */
public abstract class AbstractItem extends AbstractCoreItem implements XMLSaving {

	protected AbstractItemType itemType;
	protected List<ItemEffect> itemEffects;

	public AbstractItem(AbstractItemType itemType) {
		super(itemType.getName(false), itemType.getNamePlural(false), itemType.getSVGString(), itemType.getColourShades().get(0), itemType.getRarity(), null, itemType.getItemTags());

		this.itemType = itemType;
		this.itemEffects = itemType.getEffects();
	}
	
	@Override
	public boolean equals(Object o) {
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

		XMLUtil.addAttribute(doc, element, "id", this.getItemType().getId());
		XMLUtil.addAttribute(doc, element, "name", this.getName());
		if(this.getColour(0)!=null) {
			XMLUtil.addAttribute(doc, element, "colour", this.getColour(0).getId());
		}
		
		Element innerElement = doc.createElement("itemEffects");
		element.appendChild(innerElement);
		
		for(ItemEffect ie : this.getEffects()) {
			ie.saveAsXML(innerElement, doc);
		}
		
		return element;
	}
	
	public static AbstractItem loadFromXML(Element parentElement, Document doc) {
		try {
			AbstractItemType it = ItemType.getItemTypeFromId(parentElement.getAttribute("id"));
			if(it==null) {
				System.err.println("Warning: An instance of AbstractItem was unable to be imported, due to AbstractItemType not existing. ("+parentElement.getAttribute("id")+")");
				return null;
			}
			AbstractItem item = Main.game.getItemGen().generateItem(it);
			
			if(!parentElement.getAttribute("name").isEmpty()) {
				item.setName(parentElement.getAttribute("name"));
			}
			
			List<ItemEffect> effectsToBeAdded = new ArrayList<>();
			NodeList element = ((Element) parentElement.getElementsByTagName("itemEffects").item(0)).getElementsByTagName("effect");
			for(int i = 0; i < element.getLength(); i++){
				Element e = ((Element)element.item(i));
				ItemEffect itemEffect = ItemEffect.loadFromXML(e, doc);
				if(itemEffect != null) {
					effectsToBeAdded.add(itemEffect);
				}
			}
			item.setItemEffects(effectsToBeAdded);
			
			if(!effectsToBeAdded.isEmpty()
					&& (item.getItemType().getId().equals(ItemType.ELIXIR.getId()) || item.getItemType().getId().equals(ItemType.POTION.getId()) || item.getItemType().getId().equals(ItemType.ORIENTATION_HYPNO_WATCH.getId()))) {
				item.setSVGString(EnchantingUtils.getImportedSVGString(item, (parentElement.getAttribute("colour").isEmpty()?PresetColour.GENERIC_ARCANE:PresetColour.getColourFromId(parentElement.getAttribute("colour"))), effectsToBeAdded));
			}
			
			return item;
		} catch(Exception ex) {
			System.err.println("Warning: An instance of AbstractItem was unable to be imported. ("+parentElement.getAttribute("id")+")");
			ex.printStackTrace();
			return null;
		}
	}

	public AbstractItemType getItemType() {
		return itemType;
	}

	public boolean isBreakOutOfInventory() {
		for(ItemEffect effect : this.getEffects()) {
			if(effect.getItemEffectType().isBreakOutOfInventory()) {
				return true;
			}
		}
		return false;
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
		sb.append(UtilText.parse(target, user, this.getItemType().getSpecialEffect()));
		
		for(ItemTag tag : this.getItemTags()) {
			int intoxicationLevel = 0;
			switch(tag) {
				case CAFFEINATED_005:
					intoxicationLevel = 5;
					break;
				case CAFFEINATED_010:
					intoxicationLevel = 10;
					break;
				case CAFFEINATED_015:
					intoxicationLevel = 15;
					break;
				case CAFFEINATED_020:
					intoxicationLevel = 20;
					break;
				case CAFFEINATED_025:
					intoxicationLevel = 25;
					break;
				case CAFFEINATED_030:
					intoxicationLevel = 30;
					break;
				case CAFFEINATED_040:
					intoxicationLevel = 40;
					break;
				case CAFFEINATED_050:
					intoxicationLevel = 50;
					break;
				case CAFFEINATED_075:
					intoxicationLevel = 75;
					break;
				case CAFFEINATED_100:
					intoxicationLevel = 100;
					break;
				default:
					break;
			}
			if(intoxicationLevel>0 && target.getRace()==Race.getRaceFromId("charisma_spider")) {
				sb.append(UtilText.parse(target,
						"<p style='text-align:center;'>"
							+ "Due to [npc.her] spider physiology, the caffeine in the "+this.getName()+" acts in a similar manner to alcohol, and as a result [npc.she] [npc.verb(feel)] [npc.herself] getting [style.boldAlcohol(drunk)]..."
						+ "</p>"));
				sb.append(user.incrementAlcoholLevel(intoxicationLevel/100f));
				break;
			}
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
	
	// Getters & setters:
	
	public String getName(boolean withDeterminer, boolean withRarityColour) {
		return (withDeterminer
				? (!itemType.getDeterminer().equalsIgnoreCase("a") && !itemType.getDeterminer().equalsIgnoreCase("an")
					? itemType.getDeterminer()
					: UtilText.generateSingularDeterminer(name))
				: "")
				+ " "+(withRarityColour ? (" <span style='color: " + rarity.getColour().toWebHexString() + ";'>" + name + "</span>") : " "+name);
	}

	@Override
	public String getDisplayName(boolean withRarityColour) {
		return Util.capitaliseSentence(
				(!itemType.getDeterminer().equalsIgnoreCase("a") && !itemType.getDeterminer().equalsIgnoreCase("an")
						? itemType.getDeterminer()
						: UtilText.generateSingularDeterminer(name))
				+ " "+ (withRarityColour ? ("<span style='color: " + rarity.getColour().toWebHexString() + ";'>" + name + "</span>") : name));
	}

	@Override
	public String getDisplayNamePlural(boolean withRarityColour) {
		return Util.capitaliseSentence((withRarityColour ? ("<span style='color: " + rarity.getColour().toWebHexString() + ";'>" + namePlural + "</span>") : namePlural));
	}

	@Override
	public String getDescription() {
		return itemType.getDescription();
	}
	
	@Override
	public int getValue() {
		return itemType.getValue(this.getEffects());
	}
	
	public String getExtraDescription(GameCharacter user, GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>"
					+ "<b>Effects:</b>");
		
		for(ItemEffect ie : getEffects()) {
			for(String s : ie.getEffectsDescription(user, target)) {
				sb.append("<br/>"+s);
			}
		}
		for(String s : this.getItemType().getEffectTooltipLines()) {
			sb.append("<br/>"+s);
		}
		for(ItemTag it : this.getItemTags()) {
			for(String s : it.getClothingTooltipAdditions()) {
				sb.append("<br/>"+s);
			}
		}

		sb.append("</p>"
				+ "<p>"
					+ (this.getItemType().isPlural()?"They have":"It has")+" a value of " + UtilText.formatAsMoney(getValue()) + "."
				+ "</p>");
		
		return sb.toString();
	}
	
	/**
	 * @param characterOwner The character who owns this item.
	 * @return A List of Strings describing extra features of this ItemType.
	 */
	public List<String> getExtraDescriptions(GameCharacter characterOwner) {
		List<String> descriptionsList = new ArrayList<>();
		
		for(ItemTag it : this.getItemType().getItemTags()) {
			descriptionsList.addAll(it.getClothingTooltipAdditions());
		}
		
		return descriptionsList;
	}
	
	public List<SvgInformation> getPathNameInformation() {
		return itemType.getPathNameInformation();
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

	public boolean isAbleToBeUsedInCombatAllies(){
		return !this.isBreakOutOfInventory() && itemType.isAbleToBeUsedInCombatAllies();
	}

	public boolean isAbleToBeUsedInCombatEnemies(){
		return !this.isBreakOutOfInventory() && itemType.isAbleToBeUsedInCombatEnemies();
	}

	public boolean isAbleToBeUsedInSex(){
		return !this.isBreakOutOfInventory() && itemType.isAbleToBeUsedInSex();
	}
	
	public boolean isGift() {
		return itemType.isGift();
	}

	public boolean isTypeOneOf(String ... itemType) {
		return Stream.of(itemType).anyMatch(it -> (this.getItemType().equals(ItemType.getItemTypeFromId(it))));
	}

	@Override
	public Set<ItemTag> getItemTags() {
		return new HashSet<>(this.getItemType().getItemTags());
	}
}
