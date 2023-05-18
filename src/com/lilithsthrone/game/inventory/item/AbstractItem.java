package com.lilithsthrone.game.inventory.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.EffectBenefit;
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
	public AbstractItem(AbstractItem item) {
		super(item.getName(),
				item.getNamePlural(),
				item.getSVGString(),
				item.colours.get(0),
				item.getRarity(),
				item.attributeModifiers,
				item.getItemTags());

		this.itemType = item.getItemType();
		this.itemEffects = item.getEffects();
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
		
		if(!this.getEffects().isEmpty()) {
			Element innerElement = doc.createElement("itemEffects");
			element.appendChild(innerElement);
			
			for(ItemEffect ie : this.getEffects()) {
				ie.saveAsXML(innerElement, doc);
			}
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
			Element ieElement = (Element) parentElement.getElementsByTagName("itemEffects").item(0);
			if(ieElement!=null) {
				NodeList element = ieElement.getElementsByTagName("effect");
				for(int i = 0; i < element.getLength(); i++){
					Element e = ((Element)element.item(i));
					ItemEffect itemEffect = ItemEffect.loadFromXML(e, doc);
					if(itemEffect != null) {
						effectsToBeAdded.add(itemEffect);
					}
				}
				item.setItemEffects(effectsToBeAdded);
			}
			item.setColour(0,
					parentElement.getAttribute("colour").isEmpty()
						?PresetColour.GENERIC_ARCANE
						:PresetColour.getColourFromId(parentElement.getAttribute("colour")));
			
			if(!effectsToBeAdded.isEmpty()
					&& (item.getItemType().getId().equals(ItemType.ELIXIR.getId()) || item.getItemType().getId().equals(ItemType.POTION.getId()) || item.getItemType().getId().equals(ItemType.ORIENTATION_HYPNO_WATCH.getId()))) {
				item.setSVGString(EnchantingUtils.getImportedSVGString(item, item.getColour(0), effectsToBeAdded));
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
		
		if(this.getItemType().getAppliedStatusEffects()!=null) {
			for(Entry<AbstractStatusEffect, Integer> entry : this.getItemType().getAppliedStatusEffects().entrySet()) {
				AbstractStatusEffect se = entry.getKey();
				int time = entry.getValue();
				target.addStatusEffect(se, time);
				String timeDesc = time+" turns";
				if(!se.isCombatEffect()) {
					int timeMinutes = (time/60);
					if(timeMinutes > 3*60) {
						timeDesc = timeMinutes/60+" hours";
					} else {
						timeDesc = timeMinutes+" minutes";
					}
				}
				sb.append(UtilText.parse(target,
						"<p style='text-align:center; padding-top:0; margin-top:0;'>"
						+ "[npc.NameIsFull] now "
						+(se.getBeneficialStatus()==EffectBenefit.DETRIMENTAL?"suffering from [style.italicsBad(":(se.getBeneficialStatus()==EffectBenefit.BENEFICIAL?"benefitting from [style.italicsGood(":"affected by "))
						+se.getName(target)
						+(se.getBeneficialStatus()==EffectBenefit.NEUTRAL?"":")]")
						+ " for "+timeDesc+"!"
						+ "</p>"));
			}
		}
		
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
				sb.append(target.incrementAlcoholLevel(intoxicationLevel/100f));
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
		float modifier = 1;
		if(this.getEffects().size() > 0) {
			for(ItemEffect ie : this.getEffects()) {
				if(ie.getPotency()==null) {
					continue;
				}
				float modIncrease = 0;
				switch(ie.getPotency()) {
					case MAJOR_BOOST:
						modIncrease = 0.05f;
						break;
					case BOOST:
						modIncrease = 0.025f;
						break;
					case MINOR_BOOST:
						modIncrease = 0.01f;
						break;
					default:
						break;
				}
				modifier += modIncrease;
			}
			
//			List<TFPotency> potencies = this.getEffects().stream().map(ItemEffect::getPotency).collect(Collectors.toList());
//			if (potencies.contains(TFPotency.MAJOR_BOOST)) {
//				modifier += 0.5;
//			} else if (potencies.contains(TFPotency.BOOST)) {
//				modifier += 0.3;
//			} else if (potencies.contains(TFPotency.MINOR_BOOST)) {
//				modifier += 0.1;
//			}
//			
//			modifier += itemEffects.size()*0.01f;
		}
		return (int) (itemType.getValue() * modifier);
	}
	
	public boolean isAppendItemEffectLinesToTooltip() {
		return this.getItemType().isAppendItemEffectLinesToTooltip();
	}
	
	public List<String> getEffectTooltipLines() {
		return this.getItemType().getEffectTooltipLines();
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
		for(String s : this.getEffectTooltipLines()) {
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
