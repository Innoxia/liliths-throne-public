package com.base.game.combat;

import com.base.game.character.GameCharacter;
import com.base.game.character.SexualOrientation;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.effects.Perk;

/**
 * @since 0.1.0
 * @version 0.1.69
 * @author Innoxia
 */
public enum Attack {
	NONE("none"),
	MAIN("main"),
	OFFHAND("offhand"),
	DUAL("dual strike"),
	SEDUCTION("seduction"),
	SPELL("spell"),
	SPECIAL_ATTACK("special attack"),
	USE_ITEM("use item"),
	ESCAPE("escape"),
	WAIT("wait");

	private String name;

	private Attack(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param attacker
	 *            The attacking character.
	 * @param defender
	 *            The defending character.
	 * @param attackType
	 *            Type of this attack.
	 * @return Hit chance from 1 to 100, representing % chance to hit.
	 */
	public static float calculateHitChance(GameCharacter attacker, GameCharacter defender, Attack attackType) {

		// Calculate hit:
		float chanceToHit = 100;

		return chanceToHit > 100 ? 100 : (chanceToHit < 1 ? 1 : chanceToHit);
	}

	/**
	 * @param attacker
	 *            The attacking character.
	 * @param defender
	 *            The defending character.
	 * @param spell
	 *            The spell that is being cast.
	 * @return Hit chance from 1 to 100, representing % chance to hit.
	 */
	public static float calculateHitChance(GameCharacter attacker, GameCharacter defender, Spell spell) {
		if (spell.isSelfCastSpell())
			return 100;

		// Calculate hit:
		float chanceToHit = 100;

		return chanceToHit > 100 ? 100 : (chanceToHit < 1 ? 1 : chanceToHit);
	}

	/**
	 * @param attacker
	 * @return
	 */
	public static float getMeleeDamage(GameCharacter attacker) {
		if (attacker == null)
			return 0;
		if (attacker.getMainWeapon() == null)
			return 4 + attacker.getLevel();
		else
			return ((4 + attacker.getLevel()) * (attacker.getMainWeapon().getWeaponType().getDamageLevel().getDamageModifier()));
	}

	/**
	 * @param attacker
	 * @return
	 */
	public static float getSeductionDamage(GameCharacter attacker) {
		if (attacker == null)
			return 0;
		return (4 + attacker.getLevel());
	}

	/**
	 * @param attacker
	 *            The attacking character.
	 * @param defender
	 *            The defending character.
	 * @param attackType
	 *            Type of this attack.
	 * @return damage
	 */
	public static float calculateDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, boolean critical) {

		float damage = getMinimumDamage(attacker, defender, attackType);

		// Add variation:
		if (getMaximumDamage(attacker, defender, attackType) - getMinimumDamage(attacker, defender, attackType) > 0) {
			float difference = getMaximumDamage(attacker, defender, attackType) - getMinimumDamage(attacker, defender, attackType);
			
			damage += Math.random()*difference;
		}

		// Is critical:
		if (critical)
			damage *= (attacker.getAttributeValue(Attribute.CRITICAL_DAMAGE) / 100f);
		
		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;
		
		return damage;
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
		
		float damage = 0;
		
		if (attackType == MAIN) {
			damage = getModifiedDamage(attacker, defender, attackType, (attacker.getMainWeapon() == null ? DamageType.PHYSICAL : attacker.getMainWeapon().getDamageType()),
						getMeleeDamage(attacker) * (attacker.getMainWeapon() == null ? 1 - DamageVariance.MEDIUM.getPercentange() : 1f - attacker.getMainWeapon().getWeaponType().getDamageVariance().getPercentange()));
		
		} else if (attackType == OFFHAND) {
			damage = getModifiedDamage(attacker, defender, attackType, (attacker.getOffhandWeapon() == null ? DamageType.PHYSICAL : attacker.getOffhandWeapon().getDamageType()),
					getMeleeDamage(attacker) * (attacker.getOffhandWeapon() == null ? 1 - DamageVariance.MEDIUM.getPercentange() : 1f - attacker.getOffhandWeapon().getWeaponType().getDamageVariance().getPercentange()));
	
		} else {
			damage =  (getModifiedDamage(attacker, defender, attackType, DamageType.MANA, getSeductionDamage(attacker) * 0.8f));
		}
		
		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;
		
		return damage;
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

		float damage = 0;
		
		if (attackType == MAIN) {
			damage = (getModifiedDamage(attacker, defender, attackType, (attacker.getMainWeapon() == null ? DamageType.PHYSICAL : attacker.getMainWeapon().getDamageType()),
					getMeleeDamage(attacker) * (attacker.getMainWeapon() == null ? 1 + DamageVariance.MEDIUM.getPercentange() : 1f + attacker.getMainWeapon().getWeaponType().getDamageVariance().getPercentange())));

		}  else if (attackType == OFFHAND) {
			damage = getModifiedDamage(attacker, defender, attackType, (attacker.getOffhandWeapon() == null ? DamageType.PHYSICAL : attacker.getOffhandWeapon().getDamageType()),
					getMeleeDamage(attacker) * (attacker.getOffhandWeapon() == null ? 1 + DamageVariance.MEDIUM.getPercentange() : 1f + attacker.getOffhandWeapon().getWeaponType().getDamageVariance().getPercentange()));
	
		} else {
			damage = (getModifiedDamage(attacker, defender, attackType, DamageType.MANA, getSeductionDamage(attacker) * 1.2f));
		}

		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;
		
		return damage;
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
	private static float getModifiedDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, DamageType damageType, float attackersDamage) {
		float damage = attackersDamage;
		if (damage < 1)
			damage = 1;

		// Melee attack:
		if (attackType == MAIN || attackType == OFFHAND) {

			if (attacker != null) {
				// Attacker modifiers:
				// Melee modifier:
				damage *= (attacker.getAttributeValue(Attribute.DAMAGE_ATTACK) / 100f);
				// Damage Type modifier:
				damage *= (attacker.getAttributeValue(damageType.getMultiplierAttribute()) / 100f);
				// Pure damage modifier:
				damage *= ((100 + attacker.getAttributeValue(Attribute.DAMAGE_PURE)) / 100f);

				if (damage < 1)
					damage = 1;
			}

			if (defender != null) {
				// Defender modifiers:
				// Melee modifier:
				damage *= ((100 - defender.getAttributeValue(Attribute.RESISTANCE_ATTACK)) / 100f);
				// Damage Type modifier:
				damage *= ((100 - defender.getAttributeValue(damageType.getResistAttribute())) / 100f);
				// Pure damage modifier:
				damage *= ((100 - defender.getAttributeValue(Attribute.RESISTANCE_PURE)) / 100f);

				if (damage < 1)
					damage = 1;
			}
			
		// Seduction attack:
		} else {

			if (attacker != null) {
				// Attacker modifiers:
				damage *= (attacker.getAttributeValue(Attribute.DAMAGE_MANA) / 100f);
				
				if(attacker.hasPerk(Perk.FEMALE_ATTRACTION) && defender.isFeminine()) {
					damage *=1.1f;
				}
				if(attacker.hasPerk(Perk.MALE_ATTRACTION) && !defender.isFeminine()) {
					damage *=1.1f;
				}

				if (damage < 1)
					damage = 1;
			}

			if (defender != null) {
				// Defender modifiers:
				damage *= ((100 - defender.getAttributeValue(Attribute.RESISTANCE_MANA)) / 100f);
				
				if(attacker.getSexualOrientation()==SexualOrientation.ANDROPHILIC && defender.isFeminine()) {
					damage*=0.5f;
				}
				if(attacker.getSexualOrientation()==SexualOrientation.GYNEPHILIC && !defender.isFeminine()) {
					damage*=0.5f;
				}
				
				if (damage < 1) {
					damage = 1;
				}
			}

		}

		if (defender != null) {
			// Modifiers based on level:
			if (defender.getLevel() - attacker.getLevel() >= 3)
				return damage * 0.75f;
			else if (defender.getLevel() - attacker.getLevel() <= -3)
				return damage * 1.25f;
			else
				return damage;

		} else
			return damage;
	}

}
