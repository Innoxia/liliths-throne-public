package com.lilithsthrone.game.combat;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public enum DamageType {

	HEALTH("health",
			PresetColour.ATTRIBUTE_HEALTH,
			"health damaging",
			Attribute.ENERGY_SHIELDING,
			Attribute.HEALTH_MAXIMUM,
			SpellSchool.EARTH,
			null),

	PHYSICAL("physical",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			"forceful",
			Attribute.RESISTANCE_PHYSICAL,
			Attribute.DAMAGE_PHYSICAL,
			SpellSchool.EARTH,
			DamageType.HEALTH),
	
	ICE("ice",
			PresetColour.DAMAGE_TYPE_COLD,
			"freezing",
			Attribute.RESISTANCE_ICE,
			Attribute.DAMAGE_ICE,
			SpellSchool.WATER,
			DamageType.HEALTH),
	
	FIRE("fire",
			PresetColour.DAMAGE_TYPE_FIRE,
			"burning",
			Attribute.RESISTANCE_FIRE,
			Attribute.DAMAGE_FIRE,
			SpellSchool.FIRE,
			DamageType.HEALTH),
	
	POISON("poison",
			PresetColour.DAMAGE_TYPE_POISON,
			"poisoned",
			Attribute.RESISTANCE_POISON,
			Attribute.DAMAGE_POISON,
			SpellSchool.AIR,
			DamageType.HEALTH),

	UNARMED("unarmed",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			"unarmed",
			Attribute.RESISTANCE_PHYSICAL,
			Attribute.DAMAGE_PHYSICAL,
			SpellSchool.EARTH,
			DamageType.HEALTH) {
		@Override
		public Value<String, Integer> damageTarget(GameCharacter source, GameCharacter target, int damageAmount) {
			return getParentDamageType(source, target).damageTarget(source, target, damageAmount);
		}

		@Override
		public DamageType getParentDamageType(GameCharacter source, GameCharacter target) {
			// Flame cloak gives fire melee damage
			if(source.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES_1)
					|| source.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES_2)
					|| source.hasStatusEffect(StatusEffect.CLOAK_OF_FLAMES_3)) {
				return FIRE;
			}

//			if(source.isElemental()) {
//				switch(source.getBodyMaterial()) {
//					case AIR:
//						return POISON;
//					case ARCANE:
//						return LUST;
//					case FIRE:
//						return FIRE;
//					case FLESH:
//					case SLIME:
//						return PHYSICAL;
//					case RUBBER:
//					case STONE:
//						return PHYSICAL;
//					case ICE:
//					case WATER:
//						return ICE;
//					default:
//						return PHYSICAL;
//				}
//			}

			return source.getBodyMaterial().getUnarmedDamageType();
		}
	},

	LUST("lust",
			PresetColour.DAMAGE_TYPE_LUST,
			"arousing",
			Attribute.RESISTANCE_LUST,
			Attribute.DAMAGE_LUST,
			SpellSchool.ARCANE,
			null) {
		@Override
		public Value<String, Integer> damageTarget(GameCharacter source, GameCharacter target, int damageAmount) {
			damageAmount = shieldCheck(source, target, damageAmount);
			if(damageAmount > 0) {
				if(target.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX)) {
					target.incrementMana(-damageAmount);
					return HEALTH.damageTarget(source, target, damageAmount*2);
				} else {
					target.setLust(target.getLust()+damageAmount);
				}
			}
			return new Value<>("", damageAmount);
		}
	},
	
	MISC("generic",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			"standard",
			Attribute.RESISTANCE_PHYSICAL,
			Attribute.DAMAGE_PHYSICAL,
			SpellSchool.ARCANE,
			DamageType.HEALTH);

	private String name;
	private Colour colour;
	private String weaponDescriptor;
	private AbstractAttribute resistAttribute;
	private AbstractAttribute multiplierAttribute;
	private SpellSchool spellSchool;
	private DamageType parentDamageType;

	private DamageType(String name, Colour colour, String weaponDescriptor, AbstractAttribute resistAttribute, AbstractAttribute multiplierAttribute, SpellSchool spellSchool, DamageType parentDamageType) {
		this.name = name;
		this.colour = colour;
		this.weaponDescriptor = weaponDescriptor;
		this.resistAttribute = resistAttribute;
		this.multiplierAttribute = multiplierAttribute;
		this.spellSchool = spellSchool;
		this.parentDamageType = parentDamageType; // The umbrella damage type  that covers all of the other damage types in it. Usually doesn't show up for weapons.
	}

	public String getName() {
		return name;
	}
	
	public String getNameColoured() {
		return getNameColoured("span");
	}
	
	public String getNameColoured(String tag) {
		return "<"+tag+" style='color:" + this.getMultiplierAttribute().getColour().toWebHexString() + ";'>"+this.getName()+"</"+tag+">";
	}
	
	public Colour getColour() {
		return colour;
	}

	public String getWeaponDescriptor() {
		return weaponDescriptor;
	}

	public AbstractAttribute getResistAttribute() {
		return resistAttribute;
	}

	public AbstractAttribute getMultiplierAttribute() {
		return multiplierAttribute;
	}

	public SpellSchool getSpellSchool() {
		return spellSchool;
	}

	/**
	 * Deals damage to the target, checking against their shielding against the attack type. Override this if the attack
	 * @param target
	 * @param damageAmount
	 * @return A Value pair with the key being the description of health loss, and the value being the numerical value of the health lost.
	 */
	public Value<String, Integer> damageTarget(GameCharacter source, GameCharacter target, int damageAmount) {
		damageAmount = shieldCheck(source, target, damageAmount);
		String description = "";
//		if(damageAmount > 0) {
			description = target.incrementHealth(source, -damageAmount);
//		}
		if(target.hasFetish(Fetish.FETISH_MASOCHIST)) { // Change damageAmount after health damage applied, as the 75% damage taken effect is handled within the incrementHealth method itself.
			damageAmount*=0.75f;
		}
		return new Value<>(description, damageAmount);
	}

	/**
	 * @return How much damage this damage type will do vs the target, taking into account the target's shields. <br/>
	 * <b>Does not</b> deplete the target's shields.
	 */
	public int shieldCheckNoDamage(GameCharacter source, GameCharacter target, int damageAmount) {
		if(this.getParentDamageType(source, target) != null) {
			damageAmount = this.getParentDamageType(source, target).shieldCheckNoDamage(source, target, damageAmount);
		}
		if(target.getShields(this) > 0) {
			AbstractAttribute resist = this.getResistAttribute();
			if(target.getAttributeValue(resist)>=resist.getUpperLimit() && resist.isInfiniteAtUpperLimit()) {
				damageAmount = 0;
			} else {
				damageAmount -= target.getShields(this);
			}
			if(damageAmount < 0) {
				damageAmount = 0;
			}
		}
		return damageAmount;
	}

	/**
	 * @return How much damage this damage type will do vs the target, taking into account the target's shields. <br/>
	 * <b>Does</b> deplete the target's shields.
	 */
	public int shieldCheck(GameCharacter source, GameCharacter target, int damageAmount) {
		if(damageAmount>0) {
			if(this.getParentDamageType(source, target) != null) {
				damageAmount = this.getParentDamageType(source, target).shieldCheck(source, target, damageAmount);
			}
			if(target.getShields(this) > 0) {
				AbstractAttribute resist = this.getResistAttribute();
				if(target.getAttributeValue(resist)>=resist.getUpperLimit() && resist.isInfiniteAtUpperLimit()) {
					damageAmount = 0;
				} else {
					int oldShields = target.getShields(this);
					target.setShields(this, target.getShields(this) - damageAmount);
					damageAmount -= oldShields;
				}
				if(damageAmount < 0) {
					damageAmount = 0;
				}
			}
		}
		return damageAmount;
	}

	public DamageType getParentDamageType(GameCharacter source, GameCharacter target) {
		return this.parentDamageType;
	}
	
}
