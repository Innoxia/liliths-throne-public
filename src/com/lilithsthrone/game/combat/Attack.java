package com.lilithsthrone.game.combat;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

import java.util.Optional;

/**
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public enum Attack {
	
	NONE,
	MAIN,
	OFFHAND,
	DUAL,
	SEDUCTION,
	SPELL,
	SPECIAL_ATTACK,
	USE_ITEM,
	ESCAPE,
	WAIT;

	/**
	 * @param attacker The attacking character.
	 * @param defender The defending character.
	 * @return Hit chance from 0 to 1, representing % chance to hit.
	 */
	public static float getHitChance(GameCharacter attacker, GameCharacter defender) {
		float chanceToHit = (100 - Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.MISS_CHANCE), 100))/100f;
		
		chanceToHit *= (1 - (Util.getModifiedDropoffValue(defender.getAttributeValue(Attribute.DODGE_CHANCE), 100)/100f));
		return Math.max(0, Math.min(chanceToHit, 1));
	}
	
	public static boolean rollForHit(GameCharacter attacker, GameCharacter defender) {
		return Math.random() < getHitChance(attacker, defender);
	}

	public static boolean rollForCritical(GameCharacter attacker, GameCharacter defender) {
		return rollForCritical(attacker, defender, null);
	}
	
	
	public static boolean rollForCritical(GameCharacter attacker, GameCharacter defender, Spell spell) {
		float criticalChance = attacker.getAttributeValue(Attribute.CRITICAL_CHANCE);
		
		if (spell == Spell.ICE_SHARD && attacker.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_2) && defender.hasStatusEffect(StatusEffect.FREEZING_FOG)) {
			criticalChance += 25;
		}
		
		return Math.random() < (Util.getModifiedDropoffValue(criticalChance, 100)/100f);
	}

	public static float getMeleeDamage(GameCharacter attacker, AbstractWeapon weapon) {
		if (attacker == null) {
			return 0;
		}
		if (weapon == null) {
			return 1 + (int)(attacker.getAttributeValue(Attribute.MAJOR_PHYSIQUE)/10);
		} else {
			return weapon.getWeaponType().getDamage();
		}
	}

	public static float getSeductionDamage(GameCharacter attacker) {
		if (attacker == null) {
			return 0;
		}
		return 10;
	}

	private static float calculateDamage(GameCharacter attacker, float minimumDamage, float maximumDamage, boolean critical) {
		float difference = maximumDamage - minimumDamage;
		float finalDamage = minimumDamage;

		// Add variation:
		if (difference > 0) {
			finalDamage += Math.random()*difference;
		}

		if (critical) {
			finalDamage *= (attacker.getAttributeValue(Attribute.CRITICAL_DAMAGE) / 100f);
		}

		finalDamage *= getDamageModifierForAttacker(attacker);

		finalDamage = roundToNearestTenth(finalDamage);

		if (attacker.hasTrait(Perk.JOB_SOLDIER, true) && Main.game.isInCombat() && Combat.getTurn() == 0) {
			return 2 * finalDamage;
		} else {
			return finalDamage;
		}
	}

	public static float calculateNormalDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, boolean critical) {
		return calculateDamage(attacker,
				getMinimumDamage(attacker, defender, attackType),
				getMaximumDamage(attacker, defender, attackType),
				critical);
	}
	
	public static float calculateSpellDamage(GameCharacter attacker, GameCharacter defender, DamageType damageType, float damage, DamageVariance damageVariance, boolean critical) {
		return calculateDamage(attacker,
				getMinimumSpellDamage(attacker, defender, damageType, damage, damageVariance),
				getMaximumSpellDamage(attacker, defender, damageType, damage, damageVariance),
				critical);
	}

	public static float calculateSpecialAttackDamage(GameCharacter attacker, GameCharacter defender, DamageType damageType, float damage, DamageVariance damageVariance, boolean critical) {
		return calculateDamage(attacker,
				getMinimumSpecialAttackDamage(attacker, defender, damageType, damage, damageVariance),
				getMaximumSpecialAttackDamage(attacker, defender, damageType, damage, damageVariance),
				critical);
	}

	/**
	 * Returns a value that represents the minimum possible damage done to the
	 * defender. The only mechanic not taken into consideration is critical
	 * chance/damage, which is handled in the calculateDamage() method.
	 * 
	 * @param attacker
	 *            The attacking character.
	 * @param defender
	 *            The defending character.
	 * @param attackType
	 *            Type of this attack.
	 * @return Minimum damage possible for this attack.
	 */

	public static float getMinimumDamage(GameCharacter attacker, GameCharacter defender, Attack attackType) {
		return getMinimumDamage(attacker, defender, attackType, getWeaponBasedOnAttackType(attacker, attackType));
	}

	public static float getMinimumDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, AbstractWeapon weapon) {
		if (attackType == MAIN || attackType == OFFHAND) {
			return getMinimumPhysicalDamage(attacker, defender, attackType, weapon);
		}
		return getMinimumSeductionDamage(attacker, defender, attackType);
	}

	private static float getMinimumPhysicalDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, AbstractWeapon weapon) {
		float variancePercentageLowerBound = weapon == null
				? 1 - DamageVariance.MEDIUM.getPercentage()
				: 1f - weapon.getWeaponType().getDamageVariance().getPercentage();
		return getPhysicalDamage(attacker, defender, attackType, weapon, variancePercentageLowerBound);
	}

	private static float getMinimumSeductionDamage(GameCharacter attacker, GameCharacter defender, Attack attackType) {
		float damage = getModifiedDamage(attacker, defender, attackType, DamageType.LUST, getSeductionDamage(attacker) * 0.9f);

		return roundToNearestTenth(damage);
	}

	/**
	 * Returns a value that represents the maximum possible damage done to the
	 * defender. The only mechanic not taken into consideration is critical
	 * chance/damage, which is handled in the calculateDamage() method.
	 * 
	 * @param attacker
	 *            The attacking character.
	 * @param defender
	 *            The defending character.
	 * @param attackType
	 *            Type of this attack.
	 * @return Minimum damage possible for this attack.
	 */
	public static float getMaximumDamage(GameCharacter attacker, GameCharacter defender, Attack attackType) {
		return getMaximumDamage(attacker, defender, attackType, getWeaponBasedOnAttackType(attacker, attackType));
	}

	public static float getMaximumDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, AbstractWeapon weapon) {
		if (attackType == MAIN || attackType == OFFHAND) {
			return getMaximumPhysicalDamage(attacker, defender, attackType, weapon);
		}
		return getMaximumSeductionDamage(attacker, defender, attackType);
	}

	private static float getMaximumPhysicalDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, AbstractWeapon weapon) {
		float variancePercentageUpperBound = weapon == null
				? 1 + DamageVariance.MEDIUM.getPercentage()
				: 1f + weapon.getWeaponType().getDamageVariance().getPercentage();
		return getPhysicalDamage(attacker, defender, attackType, weapon, variancePercentageUpperBound);
	}

	private static float getMaximumSeductionDamage(GameCharacter attacker, GameCharacter defender, Attack attackType) {
		float damage = getModifiedDamage(attacker, defender, attackType, DamageType.LUST, getSeductionDamage(attacker) * 1.1f);

		return roundToNearestTenth(damage);
	}

	public static float getMinimumSpellDamage(GameCharacter attacker, GameCharacter defender, DamageType damageType, float damage, DamageVariance damageVariance) {
		return getSpellDamage(attacker, defender, damageType, damage, 1 - damageVariance.getPercentage());
	}

	public static float getMaximumSpellDamage(GameCharacter caster, GameCharacter target, DamageType damageType, float damage, DamageVariance damageVariance) {
		return getSpellDamage(caster, target, damageType, damage, 1 + damageVariance.getPercentage());
	}
	
	public static float getMinimumSpecialAttackDamage(GameCharacter attacker, GameCharacter defender, DamageType damageType, float damage, DamageVariance damageVariance) {
		return getSpecialAttackDamage(attacker, defender, damageType, damage, 1 - damageVariance.getPercentage());
	}

	public static float getMaximumSpecialAttackDamage(GameCharacter caster, GameCharacter target, DamageType damageType, float damage, DamageVariance damageVariance) {
		return getSpecialAttackDamage(caster, target, damageType, damage, 1 + damageVariance.getPercentage());
	}

	private static float getPhysicalDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, AbstractWeapon weapon, float variancePercentage) {
		DamageType damageType = weapon == null
				? attacker.getBodyMaterial().getUnarmedDamageType()
				: weapon.getDamageType();
		float attackersDamage = getMeleeDamage(attacker, weapon) * variancePercentage;
		float damage = getModifiedDamage(attacker, defender, attackType, damageType, attackersDamage);

		Attribute governingAttribute = getGoverningAttributeFromWeapon(weapon);
		damage *= 1 + Util.getModifiedDropoffValue(attacker.getAttributeValue(governingAttribute), 100)/100f;

		return roundToNearestTenth(damage);
	}

	private static float getSpellDamage(GameCharacter caster, GameCharacter target, DamageType damageType, float damage, float variancePercentage) {
		float maxDamage = getModifiedDamage(caster, target, Attack.SPELL, damageType, damage * variancePercentage);

		return roundToNearestTenth(maxDamage);
	}

	private static float getSpecialAttackDamage(GameCharacter caster, GameCharacter target, DamageType damageType, float damage, float variancePercentage) {
		float finalDamage = getModifiedDamage(caster, target, Attack.SPECIAL_ATTACK, damageType, damage * variancePercentage);

		finalDamage = roundToNearestTenth(finalDamage);

		finalDamage *= 1 + Util.getModifiedDropoffValue(caster.getAttributeValue(Attribute.DAMAGE_UNARMED), 100)/100f;

		return finalDamage;
	}
	
	/**
	 * Applies attacker and defender resistances and bonuses to the supplied attack value, then returns the result.
	 * 
	 * 
	 * @param attacker
	 *            The attacking character.
	 * @param defender
	 *            The defending character.
	 * @param attackType
	 *            Type of this attack.
	 * @return Modified damage value.
	 */
	public static float getModifiedDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, DamageType damageType, float attackersDamage) {
		if (defender != null && defender.isImmuneToDamageType(damageType)) {
			return 0;
		}

		float damage = attackersDamage;
		boolean damageDoubledFromElemental = doesAttackerBenefitFromElementalServantBonus(attacker);

		if (attackType == MAIN || attackType == OFFHAND || attackType == SPECIAL_ATTACK) {
			
			if (damageDoubledFromElemental) {
				damage *= 2;
			}
			
			if (attacker != null) {
				// Attacker modifiers:
				// Damage Type modifier:
				damage *= 1 + Util.getModifiedDropoffValue(attacker.getAttributeValue(damageType.getMultiplierAttribute()), 100)/100f;

				damage = Math.max(1, damage);
			}

			if (defender != null && !defender.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX)) {
				// Defender modifiers:
				// Damage Type modifier:
				damage *= 1 - Util.getModifiedDropoffValue(defender.getAttributeValue(damageType.getResistAttribute()), 100)/100f;

				damage = Math.max(1, damage);
			}
			
		} else if (attackType == SPELL) {
			
			if (damageDoubledFromElemental) {
				damage *= 2;
			}
			
			if (attacker != null) {
				// Attacker modifiers:
				damage *= 1 + Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.DAMAGE_SPELLS), 100)/100f;

				damage *= 1 + Util.getModifiedDropoffValue(attacker.getAttributeValue(damageType.getMultiplierAttribute()), 100)/100f;
			}

			if (defender != null && !defender.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX)) {
				// Defender modifiers:
				damage *= 1 - Util.getModifiedDropoffValue(defender.getAttributeValue(damageType.getResistAttribute()), 100)/100f;
				
			}
			
		} else {

			if (attacker != null) {
				// Attacker modifiers:
				damage *= 1 + Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.DAMAGE_LUST), 100)/100f;
				
				if (defender!=null) {
					if (attacker.hasTrait(Perk.FEMALE_ATTRACTION, true) && defender.isFeminine()) {
						damage *= 1.1f;
					}
					if (attacker.hasTrait(Perk.MALE_ATTRACTION, true) && !defender.isFeminine()) {
						damage *= 1.1f;
					}
				}
				damage = Math.max(1, damage);
			}

			if (defender != null && !defender.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX)) {
				// Defender modifiers:
				damage *= 1 - Util.getModifiedDropoffValue(defender.getAttributeValue(Attribute.RESISTANCE_LUST), 100)/100f;
				if (attacker!=null) {
					if ((defender.getSexualOrientation() == SexualOrientation.ANDROPHILIC && attacker.isFeminine())
							|| (attacker.getSexualOrientation() == SexualOrientation.ANDROPHILIC && defender.isFeminine())) {
						damage *= 0.5f;
					}
					if ((defender.getSexualOrientation() == SexualOrientation.GYNEPHILIC && !attacker.isFeminine())
							|| (attacker.getSexualOrientation() == SexualOrientation.GYNEPHILIC && !defender.isFeminine())) {
						damage *= 0.5f;
					}
				}
				damage = Math.max(1, damage);
			}

		}

		if (attacker != null && defender != null) {
			// Modifiers based on race resistance:
			if (!defender.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX)) {
				damage *= 1 - Util.getModifiedDropoffValue(defender.getAttributeValue(attacker.getSubspecies().getResistanceMultiplier()), 100)/100f;
			}
			// Modifiers based on race damage:
			damage *= 1 + Util.getModifiedDropoffValue(attacker.getAttributeValue(defender.getSubspecies().getDamageMultiplier()), 100)/100f;
			
			// Modifiers based on level:
			float levelBoost = (attacker.getLevel() - defender.getLevel())*2;
			levelBoost = Util.getModifiedDropoffValue(levelBoost, 100)/100f;
			damage *= 1 + (levelBoost / 100);
		}
		
		return damage;
	}

	private static boolean doesAttackerBenefitFromElementalServantBonus(GameCharacter attacker) {
		if (attacker instanceof Elemental) {
			switch(attacker.getBodyMaterial()) {
				case AIR:
					return ((Elemental)attacker).hasStatusEffect(StatusEffect.ELEMENTAL_AIR_SERVANT_OF_AIR_ELEMENTAL_BUFF);
				case ARCANE:
					return ((Elemental)attacker).hasStatusEffect(StatusEffect.ELEMENTAL_ARCANE_SERVANT_OF_ARCANE_ELEMENTAL_BUFF);
				case FIRE:
					return ((Elemental)attacker).hasStatusEffect(StatusEffect.ELEMENTAL_FIRE_SERVANT_OF_FIRE_ELEMENTAL_BUFF);
				case FLESH:
				case SLIME:
					break;
				case RUBBER:
				case STONE:
					return ((Elemental)attacker).hasStatusEffect(StatusEffect.ELEMENTAL_EARTH_SERVANT_OF_EARTH_ELEMENTAL_BUFF);
				case ICE:
				case WATER:
					return ((Elemental)attacker).hasStatusEffect(StatusEffect.ELEMENTAL_WATER_SERVANT_OF_WATER_ELEMENTAL_BUFF);
			}
		}
		return false;
	}

	private static float roundToNearestTenth(float value) {
		return Math.round(value * 10) / 10F;
	}

	private static AbstractWeapon getWeaponBasedOnAttackType(GameCharacter attacker, Attack attackType) {
		if (attackType == MAIN) {
			return attacker.getMainWeapon();
		} else if (attackType == OFFHAND) {
			return attacker.getOffhandWeapon();
		}
		return null;
	}

	private static Attribute getGoverningAttributeFromWeapon(AbstractWeapon weapon) {
		if (weapon == null) {
			return Attribute.DAMAGE_UNARMED;
		}
		if (weapon.getWeaponType().isMelee()) {
			return Attribute.DAMAGE_MELEE_WEAPON;
		}
		return Attribute.DAMAGE_RANGED_WEAPON;
	}

	private static float getDamageModifierForAttacker(GameCharacter attacker) {
		boolean attackerIsOnPlayersSide = attacker.isPlayer() || Optional.ofNullable(attacker.getPartyLeader()).filter(GameCharacter::isPlayer).isPresent();
		if (attackerIsOnPlayersSide) {
			return Main.getProperties().difficultyLevel.getDamageModifierPlayer();
		}
		return Main.getProperties().difficultyLevel.getDamageModifierNPC();
	}

}
