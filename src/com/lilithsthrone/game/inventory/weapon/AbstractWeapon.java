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
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.main.Main;
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
		if (weaponType.getSpells() != null)
			this.spells = weaponType.getSpells();

		// Add random spells:
		if (weaponType.getRarity() == Rarity.RARE) {
			if (weaponType.getSpells().isEmpty()) {
				this.spells = new ArrayList<>();
				if (dt == DamageType.PHYSICAL)
					this.spells.add(Spell.SLAM_1);
				else if (dt == DamageType.FIRE)
					this.spells.add(Spell.FIREBALL_1);
				else if (dt == DamageType.ICE)
					this.spells.add(Spell.ICESHARD_1);
				else if (dt == DamageType.POISON)
					this.spells.add(Spell.POISON_NOVA_1);
			}

		} else if (weaponType.getRarity() == Rarity.EPIC) {
			Attribute rndAtt = Attribute.baseAttributesGood.get(Util.random.nextInt(Attribute.baseAttributesGood.size()));
			attributeModifiers.put(rndAtt, Util.random.nextInt(3) + 1);
			coreEnchantment = rndAtt;

			if (weaponType.getSpells().isEmpty()) {
				this.spells = new ArrayList<>();
				if (dt == DamageType.PHYSICAL) {
					this.spells.add(Spell.SLAM_1);
					this.spells.add(Spell.ARCANE_SHIELD);

				} else if (dt == DamageType.FIRE) {
					this.spells.add(Spell.FIREBALL_1);
					this.spells.add(Spell.FIRE_SHIELD);

				} else if (dt == DamageType.ICE) {
					this.spells.add(Spell.ICESHARD_1);
					this.spells.add(Spell.ICE_SHIELD);

				} else if (dt == DamageType.POISON) {
					this.spells.add(Spell.POISON_NOVA_1);
					this.spells.add(Spell.POISON_SHIELD);

				}
			}
		} else if (weaponType.getRarity() == Rarity.LEGENDARY) {
			int highestEnchantment = 0;
			for (Attribute a : weaponType.getAttributeModifiers().keySet()) {
				if (weaponType.getAttributeModifiers().get(a) > highestEnchantment) {
					coreEnchantment = a;
					highestEnchantment = weaponType.getAttributeModifiers().get(a);
				}
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
			weapon.coreEnchantment = Attribute.valueOf(parentElement.getAttribute("coreEnchantment"));
		}
		
		weapon.setAttributeModifiers(new HashMap<Attribute, Integer>());
		Element element = (Element)parentElement.getElementsByTagName("attributeModifiers").item(0);
		for(int i=0; i<element.getElementsByTagName("modifier").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("modifier").item(i));
			weapon.getAttributeModifiers().put(Attribute.valueOf(e.getAttribute("attribute")), Integer.valueOf(e.getAttribute("value")));
		}
		
		weapon.spells = new ArrayList<>();
		element = (Element)parentElement.getElementsByTagName("spells").item(0);
		for(int i=0; i<element.getElementsByTagName("spell").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("spell").item(i));
			weapon.spells.add(Spell.valueOf(e.getAttribute("value")));
		}
		
		return weapon;
	}
	
	public abstract String onEquip(GameCharacter character);

	public abstract String onUnequip(GameCharacter character);

	private StringBuilder descriptionSB = new StringBuilder("");

	public String getDescription() {
		descriptionSB = new StringBuilder();

		descriptionSB.append("<p'><b>" + Attack.getMinimumDamage(Main.game.getPlayer(), null, Attack.MAIN) + "-" + Attack.getMaximumDamage(Main.game.getPlayer(), null, Attack.MAIN) + "</b>" + " <b style='color:"
				+ damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + damageType.getName() + "</b> damage</p>");

		descriptionSB.append("<p>" + weaponType.getDescription() + "</p>");

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

				descriptionSB.append("<b style='color:" + s.getDamageType().getMultiplierAttribute().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(s.getName()) + "</b>");
				i++;
			}
			descriptionSB.append(".</p>");
		}

		descriptionSB.append("<p>It has a value of " + UtilText.formatAsMoney(getValue()) + ".</p>");

		return descriptionSB.toString();
	}

	@Override
	public int getValue() {
		int runningTotal = 0;

		switch (rarity) {
			case COMMON:
				break;
			case UNCOMMON:
				runningTotal += 10;
				break;
			case RARE:
				runningTotal += 20;
				break;
			case EPIC:
				runningTotal += 60;
				break;
			case LEGENDARY:
				runningTotal += 100;
				break;
			case JINXED:
				break;
		}

		if (attributeModifiers != null)
			for (Integer i : attributeModifiers.values())
				runningTotal += i * 25;

		if (runningTotal <= 0)
			runningTotal = 1;

		return runningTotal;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public AbstractWeaponType getWeaponType() {
		return weaponType;
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

}
