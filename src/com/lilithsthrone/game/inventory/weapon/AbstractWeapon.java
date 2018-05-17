package com.lilithsthrone.game.inventory.weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.0
 * @version 0.1.87
 * @author Innoxia
 */
public abstract class AbstractWeapon extends AbstractCoreItem implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;

	private AbstractWeaponType weaponType;
	private DamageType damageType;
	private Attribute coreEnchantment;
	private List<Spell> spells;

	public AbstractWeapon(AbstractWeaponType weaponType, DamageType dt) {
		super(weaponType.getName(), weaponType.getNamePlural(), weaponType.getPathName(), dt.getMultiplierAttribute().getColour(), weaponType.getRarity(), weaponType.getAttributeModifiers());
		
		this.weaponType = weaponType;
		damageType = dt;
		
		coreEnchantment = null;
		
		spells = new ArrayList<>();
		if (weaponType.getSpells() != null) {
			this.spells.addAll(weaponType.getSpells());
		}
		if (weaponType.getGenerationSpells(damageType) != null) {
			this.spells.addAll(weaponType.getGenerationSpells(damageType));
		}
		
		if(weaponType.getGenerationAttributeModifiers(damageType)!=null) {
			for(Entry<Attribute, Integer> e : weaponType.getGenerationAttributeModifiers(damageType).entrySet()) {
				this.getAttributeModifiers().putIfAbsent(e.getKey(), 0);
				this.getAttributeModifiers().put(e.getKey(), this.getAttributeModifiers().get(e.getKey())+e.getValue());
			}
		}
		
		int highestEnchantment = 0;
		for (Attribute a : attributeModifiers.keySet()) {
			if (attributeModifiers.get(a) > highestEnchantment) {
				coreEnchantment = a;
				highestEnchantment = attributeModifiers.get(a);
			}
		}
		
	}
	
	@Override
	public boolean equals (Object o) {
		if(super.equals(o)){
			if(o instanceof AbstractWeapon){
				if(((AbstractWeapon)o).getWeaponType().equals(getWeaponType())
						&& ((AbstractWeapon)o).getDamageType()==damageType
						&& ((AbstractWeapon)o).getCoreEnchantment()==coreEnchantment
						&& ((AbstractWeapon)o).getSpells().equals(spells)
						){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getWeaponType().hashCode();
		result = 31 * result + damageType.hashCode();
		if(coreEnchantment!=null)
			result = 31 * result + coreEnchantment.hashCode();
		result = 31 * result + spells.hashCode();
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("weapon");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "id", this.getWeaponType().getId());
		CharacterUtils.addAttribute(doc, element, "damageType", this.getDamageType().toString());
		CharacterUtils.addAttribute(doc, element, "coreEnchantment", (this.getCoreEnchantment()==null?"null":this.getCoreEnchantment().toString()));
		
		Element attributeElement = doc.createElement("attributeModifiers");
		element.appendChild(attributeElement);
		for(Entry<Attribute, Integer> entry : this.getAttributeModifiers().entrySet()) {
			Element modifier = doc.createElement("modifier");
			attributeElement.appendChild(modifier);
			
			CharacterUtils.addAttribute(doc, modifier, "attribute", entry.getKey().toString());
			CharacterUtils.addAttribute(doc, modifier, "value", String.valueOf(entry.getValue()));
		}
		
		Element innerElement = doc.createElement("spells");
		element.appendChild(innerElement);
		for(Spell s : this.getSpells()) {
			Element spell = doc.createElement("spell");
			innerElement.appendChild(spell);
			CharacterUtils.addAttribute(doc, spell, "value", s.toString());
		}
		
		return element;
	}
	
	public static AbstractWeapon loadFromXML(Element parentElement, Document doc) {
		AbstractWeapon weapon = AbstractWeaponType.generateWeapon(WeaponType.idToWeaponMap.get(parentElement.getAttribute("id")), DamageType.valueOf(parentElement.getAttribute("damageType")));
		
		if(!parentElement.getAttribute("coreEnchantment").equals("null")) {
			try {
				weapon.coreEnchantment = Attribute.valueOf(parentElement.getAttribute("coreEnchantment"));
			} catch(Exception ex) {
			}
		}
		
		weapon.setAttributeModifiers(new HashMap<Attribute, Integer>());
		Element element = (Element)parentElement.getElementsByTagName("attributeModifiers").item(0);
		for(int i=0; i<element.getElementsByTagName("modifier").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("modifier").item(i));
			try {
				weapon.getAttributeModifiers().put(Attribute.valueOf(e.getAttribute("attribute")), Integer.valueOf(e.getAttribute("value")));
			} catch(Exception ex) {
			}
		}
		
		weapon.spells = new ArrayList<>();
		element = (Element)parentElement.getElementsByTagName("spells").item(0);
		for(int i=0; i<element.getElementsByTagName("spell").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("spell").item(i));
			try {
				weapon.spells.add(Spell.valueOf(e.getAttribute("value")));
			} catch(Exception ex) {
			}
		}
		
		return weapon;
	}
	
	public abstract String onEquip(GameCharacter character);

	public abstract String onUnequip(GameCharacter character);

	private StringBuilder descriptionSB = new StringBuilder("");

	public String getDescription() {
		descriptionSB = new StringBuilder();

		descriptionSB.append(
					"<p>"
						+ "<b>"
							+ Attack.getMinimumDamage(Main.game.getPlayer(), null, this.getWeaponType().getSlot() == InventorySlot.WEAPON_MAIN ? Attack.MAIN : Attack.OFFHAND, this)
							+ "-"
							+ Attack.getMaximumDamage(Main.game.getPlayer(), null, this.getWeaponType().getSlot() == InventorySlot.WEAPON_MAIN ? Attack.MAIN : Attack.OFFHAND, this)
						+ "</b>"
						+ " <b style='color:"+ damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
							+ damageType.getName()
						+ "</b>"
						+ " damage"
					+ "</p>"
					+ "<p>"
						+ weaponType.getDescription()
					+ "</p>");

		if (!attributeModifiers.isEmpty()) {
			descriptionSB.append("<p>It provides ");
			int i = 0;
			for (Entry<Attribute, Integer> e : attributeModifiers.entrySet()) {
				if (i != 0) {
					if (i + 1 == attributeModifiers.size())
						descriptionSB.append(" and ");
					else
						descriptionSB.append(", ");
				}

				descriptionSB.append(" <b>" + e.getValue() + "</b> <b style='color: " + e.getKey().getColour().toWebHexString() + ";'> " + e.getKey().getName() + "</b>");
				i++;
			}
			descriptionSB.append(".</p>");
		}

		if (!spells.isEmpty()) {
			descriptionSB.append("<p>Its arcane power grants you the ability to cast ");
			int i = 0;
			for (Spell s : spells) {
				if (i != 0) {
					if (i + 1 == spells.size())
						descriptionSB.append(" and ");
					else
						descriptionSB.append(", ");
				}

				descriptionSB.append("<b style='color:" + s.getSpellSchool().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(s.getName()) + "</b>");
				i++;
			}
			descriptionSB.append(".</p>");
		}
		
		descriptionSB.append("<p>It has a value of " + UtilText.formatAsMoney(getValue()) + ".</p>");

		return descriptionSB.toString();
	}

	@Override
	public int getValue() {
		float runningTotal = this.getWeaponType().getBaseValue();

		if (colourShade == Colour.CLOTHING_PLATINUM) {
			runningTotal *= 2f;
			
		} else if (colourShade == Colour.CLOTHING_GOLD) {
			runningTotal *= 1.75f;
			
		} else if (colourShade == Colour.CLOTHING_ROSE_GOLD) {
			runningTotal *= 1.5f;
			
		} else if (colourShade == Colour.CLOTHING_SILVER) {
			runningTotal *= 1.25f;
		}
		
		if(rarity==Rarity.JINXED) {
			runningTotal *= 0.5;
		}
		
		float attributeBonuses = 0;//getModifiedDropoffValue
		if (attributeModifiers != null) {
			for (Integer i : attributeModifiers.values()) {
				attributeBonuses += i * 5;
			}
		}
		
		attributeBonuses = Util.getModifiedDropoffValue(attributeBonuses * 25, 500);
		
		runningTotal += attributeBonuses;
		
		if (runningTotal < 1) {
			runningTotal = 1;
		}
		
		return (int) runningTotal;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public AbstractWeaponType getWeaponType() {
		return weaponType;
	}

	public String getName(boolean withDeterminer, boolean withRarityColour) {
		return (withDeterminer
				? (!weaponType.getDeterminer().equalsIgnoreCase("a") && !weaponType.getDeterminer().equalsIgnoreCase("an")
					? weaponType.getDeterminer() + " "
					: (Util.isVowel(damageType.getWeaponDescriptor().charAt(0)) ? "an " : "a "))
				: " ")
				+ damageType.getWeaponDescriptor() + (withRarityColour ? (" <span style='color: " + rarity.getColour().toWebHexString() + ";'>" + name + "</span>") : " "+name);
	}
	
	public String getDisplayName(boolean withRarityColour) {
		return "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(damageType.getWeaponDescriptor()) + "</span> "
				+ (withRarityColour ? (" <span style='color: " + rarity.getColour().toWebHexString() + ";'>" + name + "</span>") : name);
	}

	@Override
	public String getSVGString() {
		return weaponType.getSVGStringMap().get(damageType);
	}

	public List<Spell> getSpells() {
		return spells;
	}

	public Attribute getCoreEnchantment() {
		return coreEnchantment;
	}
	
	public SpellSchool getSpellSchool() {
		return this.getDamageType().getSpellSchool();
	}
	
	public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
		return this.getWeaponType().isAbleToBeUsed(user, target);
	}
	
	public String getUnableToBeUsedDescription() {
		return this.getWeaponType().getUnableToBeUsedDescription();
	}
	
	public String applyExtraEfects(GameCharacter user, GameCharacter target, boolean isHit) {
		return this.getWeaponType().applyExtraEfects(user, target, isHit);
	}

}
