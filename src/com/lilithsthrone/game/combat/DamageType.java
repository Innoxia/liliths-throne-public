package com.lilithsthrone.game.combat;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public enum DamageType {


	ENERGY("energy",
			Colour.DAMAGE_TYPE_PHYSICAL,
			"energy damaging",
			Attribute.RESISTANCE_PHYSICAL,
			Attribute.DAMAGE_PHYSICAL,
			SpellSchool.EARTH,
			null),

	PHYSICAL("physical",
			Colour.DAMAGE_TYPE_PHYSICAL,
			"forceful",
			Attribute.RESISTANCE_PHYSICAL,
			Attribute.DAMAGE_PHYSICAL,
			SpellSchool.EARTH,
			DamageType.ENERGY),
	
	ICE("ice",
			Colour.DAMAGE_TYPE_COLD,
			"freezing",
			Attribute.RESISTANCE_ICE,
			Attribute.DAMAGE_ICE,
			SpellSchool.WATER,
			DamageType.ENERGY),
	
	FIRE("fire",
			Colour.DAMAGE_TYPE_FIRE,
			"burning",
			Attribute.RESISTANCE_FIRE,
			Attribute.DAMAGE_FIRE,
			SpellSchool.FIRE,
			DamageType.ENERGY),
	
	POISON("poison",
			Colour.DAMAGE_TYPE_POISON,
			"poisoned",
			Attribute.RESISTANCE_POISON,
			Attribute.DAMAGE_POISON,
			SpellSchool.AIR,
			DamageType.ENERGY),

	LUST("lust",
			Colour.DAMAGE_TYPE_LUST,
			"arousing",
			Attribute.RESISTANCE_LUST,
			Attribute.DAMAGE_LUST,
			SpellSchool.ARCANE,
			null) {
		@Override
		public int damageTarget(GameCharacter target, int damageAmount)
		{
			damageAmount = shieldCheck(target, damageAmount);
			if(damageAmount > 0)
			{
				target.setLust(target.getLust()+damageAmount);
			}
			return damageAmount;

			// TODO: DAMAGE OVERFLOW
		}
	},
	
	MISC("generic",
			Colour.DAMAGE_TYPE_PHYSICAL,
			"standard",
			Attribute.RESISTANCE_PHYSICAL,
			Attribute.DAMAGE_PHYSICAL,
			SpellSchool.ARCANE,
			DamageType.ENERGY);

	private String name;
	private Colour colour;
	private String weaponDescriptor;
	private Attribute resistAttribute;
	private Attribute multiplierAttribute;
	private SpellSchool spellSchool;
	private DamageType superDamage;

	private DamageType(String name, Colour colour, String weaponDescriptor, Attribute resistAttribute, Attribute multiplierAttribute, SpellSchool spellSchool, DamageType superDamage) {
		this.name = name;
		this.colour = colour;
		this.weaponDescriptor = weaponDescriptor;
		this.resistAttribute = resistAttribute;
		this.multiplierAttribute = multiplierAttribute;
		this.spellSchool = spellSchool;
		this.superDamage = superDamage; // The umbrella damage type  that covers all of the other damage types in it. Usually doesn't show up for weapons.
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}

	public String getWeaponDescriptor() {
		return weaponDescriptor;
	}

	public Attribute getResistAttribute() {
		return resistAttribute;
	}

	public Attribute getMultiplierAttribute() {
		return multiplierAttribute;
	}

	public SpellSchool getSpellSchool() {
		return spellSchool;
	}

	/**
	 * Deals damage to the target, checking against their shielding against the attack type. Override this if the attack
	 * @param target
	 * @param damageAmount
	 * @return
	 */
	public int damageTarget(GameCharacter target, int damageAmount) {
		damageAmount = shieldCheck(target, damageAmount);
		if(damageAmount > 0)
		{
			target.setHealth(target.getHealth()-damageAmount);
		}
		return damageAmount;
	}

	public int shieldCheck(GameCharacter target, int damageAmount)
	{
		if(this.superDamage != null)
		{
			if(target.getShields(this.superDamage) > 0)
			{
				int oldShields = target.getShields(this.superDamage);
				target.setShields(this.superDamage, target.getShields(this.superDamage) - damageAmount);
				damageAmount -= oldShields;
				if(damageAmount < 0)
				{
					damageAmount = 0;
				}
			}
		}
		if(target.getShields(this) > 0)
		{
			int oldShields = target.getShields(this);
			target.setShields(this, target.getShields(this) - damageAmount);
			damageAmount -= oldShields;
			if(damageAmount < 0)
			{
				damageAmount = 0;
			}
		}
		return damageAmount;
	}
	
}
