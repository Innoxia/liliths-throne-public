package com.lilithsthrone.game.inventory.weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public abstract class AbstractWeapon extends AbstractCoreItem implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;

	private AbstractWeaponType weaponType;
	protected List<ItemEffect> effects;
	
	private DamageType damageType;
	private Attribute coreEnchantment;
	private List<Spell> spells;
	private Colour primaryColour;
	private Colour secondaryColour;

	public AbstractWeapon(AbstractWeaponType weaponType, DamageType dt, Colour primaryColour, Colour secondaryColour) {
		super(weaponType.getName(), weaponType.getNamePlural(), weaponType.getPathName(), dt.getMultiplierAttribute().getColour(), weaponType.getRarity(), null);
		
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

		this.effects = new ArrayList<>();
		if(weaponType.getEffects()!=null) {
			for(ItemEffect effect : weaponType.getEffects()) {
				if(effect.getSecondaryModifier()==TFModifier.DAMAGE_WEAPON) {
					switch(damageType) {
						case FIRE:
							this.effects.add(new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_FIRE, TFPotency.MAJOR_BOOST, 0));
							break;
						case ICE:
							this.effects.add(new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_ICE, TFPotency.MAJOR_BOOST, 0));
							break;
						case LUST:
							this.effects.add(new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_LUST, TFPotency.MAJOR_BOOST, 0));
							break;
						case MISC:
							break;
						case PHYSICAL:
							this.effects.add(new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_PHYSICAL, TFPotency.MAJOR_BOOST, 0));
							break;
						case POISON:
							this.effects.add(new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_POISON, TFPotency.MAJOR_BOOST, 0));
							break;
					}
					
				} else if(effect.getSecondaryModifier()==TFModifier.RESISTANCE_WEAPON) {
					switch(damageType) {
						case FIRE:
							this.effects.add(new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_FIRE, TFPotency.MAJOR_BOOST, 0));
							break;
						case ICE:
							this.effects.add(new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_ICE, TFPotency.MAJOR_BOOST, 0));
							break;
						case LUST:
							this.effects.add(new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_LUST, TFPotency.MAJOR_BOOST, 0));
							break;
						case MISC:
							break;
						case PHYSICAL:
							this.effects.add(new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_PHYSICAL, TFPotency.MAJOR_BOOST, 0));
							break;
						case POISON:
							this.effects.add(new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_POISON, TFPotency.MAJOR_BOOST, 0));
							break;
					}
					
				} else {
					this.effects.add(effect);
				}
			}
		}
		
		int highestEnchantment = 0;
		for (Attribute a : attributeModifiers.keySet()) {
			if (attributeModifiers.get(a) > highestEnchantment) {
				coreEnchantment = a;
				highestEnchantment = attributeModifiers.get(a);
			}
		}

		this.primaryColour = primaryColour;
		this.secondaryColour = secondaryColour;
	}
	
	@Override
	public boolean equals (Object o) {
		if(super.equals(o)){
			if(o instanceof AbstractWeapon){
				if(((AbstractWeapon)o).getWeaponType().equals(getWeaponType())
						&& ((AbstractWeapon)o).getPrimaryColour()==primaryColour
						&& ((AbstractWeapon)o).getSecondaryColour()==secondaryColour
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
		if(getPrimaryColour()!=null) {
			result = 31 * result + getPrimaryColour().hashCode();
		}
		if(getSecondaryColour()!=null) {
			result = 31 * result + getSecondaryColour().hashCode();
		}
		if(coreEnchantment!=null) {
			result = 31 * result + coreEnchantment.hashCode();
		}
		result = 31 * result + spells.hashCode();
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("weapon");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "id", this.getWeaponType().getId());
		CharacterUtils.addAttribute(doc, element, "damageType", this.getDamageType().toString());
		CharacterUtils.addAttribute(doc, element, "coreEnchantment", (this.getCoreEnchantment()==null?"null":this.getCoreEnchantment().toString()));
		CharacterUtils.addAttribute(doc, element, "colourPrimary", this.getPrimaryColour().toString());
		CharacterUtils.addAttribute(doc, element, "colourSecondary", this.getSecondaryColour().toString());
		
		Element innerElement = doc.createElement("effects");
		element.appendChild(innerElement);
		for(ItemEffect ie : this.getEffects()) {
			ie.saveAsXML(innerElement, doc);
		}
		
		innerElement = doc.createElement("spells");
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
		
		// Try to load colour:
		try {
			weapon.setPrimaryColour(Colour.valueOf(parentElement.getAttribute("colourPrimary")));
			weapon.setSecondaryColour(Colour.valueOf(parentElement.getAttribute("colourSecondary")));
		} catch(Exception ex) {
		}

		if(!Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			try {
				weapon.getEffects().clear();
				
				Element element = (Element)parentElement.getElementsByTagName("effects").item(0);
				NodeList effectElements = element.getElementsByTagName("effect");
				for(int i=0; i<effectElements.getLength(); i++){
					Element e = ((Element)effectElements.item(i));
					ItemEffect ie = ItemEffect.loadFromXML(e, doc);
					if(ie!=null) {
						weapon.addEffect(ie);
					}
				}
			} catch(Exception ex) {
			}
		}
		
		weapon.spells = new ArrayList<>();
		Element element = (Element)parentElement.getElementsByTagName("spells").item(0);
		NodeList spellElements = element.getElementsByTagName("spell");
		for(int i=0; i<spellElements.getLength(); i++){
			Element e = ((Element)spellElements.item(i));
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


	public Colour getPrimaryColour() {
		return primaryColour;
	}

	public void setPrimaryColour(Colour primaryColour) {
		this.primaryColour = primaryColour;
	}
	
	public Colour getSecondaryColour() {
		return secondaryColour;
	}

	public void setSecondaryColour(Colour secondaryColour) {
		this.secondaryColour = secondaryColour;
	}
	
	public String getDescription() {
		descriptionSB = new StringBuilder();
		
		descriptionSB.append(
					"<p>"
						+ "<b>"
							+ Attack.getMinimumDamage(Main.game.getPlayer(), null, Attack.MAIN, this) + "-" + Attack.getMaximumDamage(Main.game.getPlayer(), null, Attack.MAIN, this)
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
		return weaponType.getSVGImage(damageType, this.getPrimaryColour(), this.getSecondaryColour());
	}

	public String getSVGEquippedString(GameCharacter owner) {
		return weaponType.getSVGEquippedImage(damageType, this.getPrimaryColour(), this.getSecondaryColour());
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
	

	@Override
	public List<ItemEffect> getEffects() {
		return effects;
	}
	
	public void setEffects(List<ItemEffect> effects) {
		this.effects = effects;
	}

	public void addEffect(ItemEffect effect) {
		effects.add(effect);
	}
	
	@Override
	public Map<Attribute, Integer> getAttributeModifiers() {
		attributeModifiers.clear();
		
		for(ItemEffect ie : getEffects()) {
			if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE) {
				if(attributeModifiers.containsKey(ie.getSecondaryModifier().getAssociatedAttribute())) {
					attributeModifiers.put(ie.getSecondaryModifier().getAssociatedAttribute(), attributeModifiers.get(ie.getSecondaryModifier().getAssociatedAttribute()) + ie.getPotency().getClothingBonusValue());
				} else {
					attributeModifiers.put(ie.getSecondaryModifier().getAssociatedAttribute(), ie.getPotency().getClothingBonusValue());
				}
			}
		}
		
		return attributeModifiers;
	}
	
	@Override
	public int getEnchantmentLimit() {
		return weaponType.getEnchantmentLimit();
	}
	
	@Override
	public AbstractItemEffectType getEnchantmentEffect() {
		return weaponType.getEnchantmentEffect();
	}
	
	@Override
	public AbstractCoreType getEnchantmentItemType(List<ItemEffect> effects) {
		return weaponType.getEnchantmentItemType(effects);
	}
	
	@Override
	public TFEssence getRelatedEssence() {
		return weaponType.getRelatedEssence();
	}

}
