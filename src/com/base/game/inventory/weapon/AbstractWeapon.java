package com.base.game.inventory.weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.base.game.character.GameCharacter;
import com.base.game.character.attributes.Attribute;
import com.base.game.combat.Attack;
import com.base.game.combat.DamageType;
import com.base.game.combat.Spell;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.Rarity;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.66
 * @author Innoxia
 */
public abstract class AbstractWeapon extends AbstractCoreItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private WeaponType weaponType;
	private DamageType damageType;
	private Attribute coreEnchantment;
	private List<Spell> spells;

	public AbstractWeapon(WeaponType weaponType, DamageType dt) {
		super(weaponType.getName(), weaponType.getPathName(), dt.getMultiplierAttribute().getColour(), weaponType.getRarity(), weaponType.getAttributeModifiers());
		this.weaponType = weaponType;
		damageType = dt;
		
		coreEnchantment = null;
		
		spells = new ArrayList<>();
		if (weaponType.getSpells() != null)
			this.spells = weaponType.getSpells();

		// Add random spells:
		if (weaponType.getRarity() == Rarity.RARE) {
			if (weaponType.getSpells() == null) {
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

			if (weaponType.getSpells() == null) {
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
				if(
						((AbstractWeapon)o).getWeaponType()==weaponType
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
		result = 31 * result + weaponType.hashCode();
		result = 31 * result + damageType.hashCode();
		if(coreEnchantment!=null)
			result = 31 * result + coreEnchantment.hashCode();
		result = 31 * result + spells.hashCode();
		return result;
	}

	public abstract String onEquip(GameCharacter character);

	public abstract String onUnequip(GameCharacter character);

	private StringBuilder descriptionSB = new StringBuilder("");

	public String getDescription() {
		descriptionSB = new StringBuilder();

		descriptionSB.append("<p style='text-align:center;'><b>" + Attack.getMinimumDamage(Main.game.getPlayer(), null, Attack.MAIN) + "-" + Attack.getMaximumDamage(Main.game.getPlayer(), null, Attack.MAIN) + "</b>" + " <b style='color:"
				+ damageType.getMultiplierAttribute().getColour() + ";'>" + damageType.getName() + "</b> damage</p>");

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

				descriptionSB.append(" <b>" + e.getValue() + "</b> <b style='color: " + e.getKey().getColour() + ";'> " + e.getKey().getName() + "</b>");
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

				descriptionSB.append("<b style='color:" + s.getDamageType().getMultiplierAttribute().getColour() + ";'>" + Util.capitaliseSentence(s.getName()) + "</b>");
				i++;
			}
			descriptionSB.append(".</p>");
		}

		descriptionSB.append("<p>It has a value of <b style='color: " + Colour.CURRENCY + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>" + getValue() + "</b>.</p>");

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

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public String getDisplayName(boolean withRarityColour) {
		return "<span style='color:" + damageType.getMultiplierAttribute().getColour() + ";'>" + Util.capitaliseSentence(damageType.getWeaponDescriptor()) + "</span> "
				+ (withRarityColour ? (" <span style='color: " + rarity.getColour() + ";'>" + name + "</span>") : name);
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
