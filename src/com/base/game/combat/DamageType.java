package com.base.game.combat;

import com.base.game.character.attributes.Attribute;

/**
 * @since 0.1.0
 * @version 0.1.4
 * @author Innoxia
 */
public enum DamageType {

	PHYSICAL("physical",
			"forceful",
			Attribute.RESISTANCE_PHYSICAL,
			Attribute.DAMAGE_PHYSICAL),
	
	ICE("ice",
			"freezing",
			Attribute.RESISTANCE_ICE,
			Attribute.DAMAGE_ICE),
	
	FIRE("fire",
			"burning",
			Attribute.RESISTANCE_FIRE,
			Attribute.DAMAGE_FIRE),
	
	POISON("poison",
			"poisoned",
			Attribute.RESISTANCE_POISON,
			Attribute.DAMAGE_POISON),

	MANA("willpower",
			"arousing",
			Attribute.RESISTANCE_MANA,
			Attribute.DAMAGE_MANA),
	
	STAMINA("stamina",
			"draining",
			Attribute.RESISTANCE_STAMINA,
			Attribute.DAMAGE_STAMINA),

	MISC("generic",
			"standard",
			Attribute.RESISTANCE_MANA,
			Attribute.DAMAGE_MANA);

	private String name, weaponDescriptor;
	private Attribute resistAttribute, multiplierAttribute;

	private DamageType(String name, String weaponDescriptor, Attribute resistAttribute, Attribute multiplierAttribute) {
		this.name = name;
		this.weaponDescriptor = weaponDescriptor;
		this.resistAttribute = resistAttribute;
		this.multiplierAttribute = multiplierAttribute;
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
}
