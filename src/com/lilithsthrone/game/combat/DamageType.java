package com.lilithsthrone.game.combat;

import com.lilithsthrone.game.character.attributes.Attribute;

/**
 * @since 0.1.0
 * @version 0.2.1
 * @author Innoxia
 */
public enum DamageType {

	PHYSICAL("physical",
			"forceful",
			Attribute.RESISTANCE_PHYSICAL,
			Attribute.DAMAGE_PHYSICAL,
			SpellSchool.EARTH),
	
	ICE("ice",
			"freezing",
			Attribute.RESISTANCE_ICE,
			Attribute.DAMAGE_ICE,
			SpellSchool.WATER),
	
	FIRE("fire",
			"burning",
			Attribute.RESISTANCE_FIRE,
			Attribute.DAMAGE_FIRE,
			SpellSchool.FIRE),
	
	POISON("poison",
			"poisoned",
			Attribute.RESISTANCE_POISON,
			Attribute.DAMAGE_POISON,
			SpellSchool.AIR),

	LUST("lust",
			"arousing",
			Attribute.RESISTANCE_LUST,
			Attribute.DAMAGE_LUST,
			SpellSchool.ARCANE),
	
	MISC("generic",
			"standard",
			Attribute.RESISTANCE_PHYSICAL,
			Attribute.DAMAGE_PHYSICAL,
			SpellSchool.ARCANE);

	private String name;
	private String weaponDescriptor;
	private Attribute resistAttribute;
	private Attribute multiplierAttribute;
	private SpellSchool spellSchool;

	private DamageType(String name, String weaponDescriptor, Attribute resistAttribute, Attribute multiplierAttribute, SpellSchool spellSchool) {
		this.name = name;
		this.weaponDescriptor = weaponDescriptor;
		this.resistAttribute = resistAttribute;
		this.multiplierAttribute = multiplierAttribute;
		this.spellSchool = spellSchool;
	}

	public String getName() {
		return name;
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
	
}
