package com.lilithsthrone.game.combat;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

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
		if (attacker.equals(defender)) {
			return 100;
		}
		
		// Calculate hit:
		float chanceToHit = 100;

		return chanceToHit > 100 ? 100 : (chanceToHit < 1 ? 1 : chanceToHit);
	}

	/**
	 * @param attacker
	 * @return
	 */
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

	/**
	 * @param attacker
	 * @return
	 */
	public static float getSeductionDamage(GameCharacter attacker) {
		if (attacker == null) {
			return 0;
		}
		return 15;
	}

	public static float calculateDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, boolean critical) {

		float damage = getMinimumDamage(attacker, defender, attackType);

		// Add variation:
		if (getMaximumDamage(attacker, defender, attackType) - getMinimumDamage(attacker, defender, attackType) > 0) {
			float difference = getMaximumDamage(attacker, defender, attackType) - getMinimumDamage(attacker, defender, attackType);
			
			damage += Math.random()*difference;
		}

		// Is critical:
		if (critical) {
			damage *= (attacker.getAttributeValue(Attribute.CRITICAL_DAMAGE) / 100f);
		}
		
		if(attacker.isPlayer()) {
			damage *= Main.getProperties().difficultyLevel.getDamageModifierPlayer();
		} else {
			damage *= Main.getProperties().difficultyLevel.getDamageModifierNPC();
		}
		
		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;

		if(attacker.hasTrait(Perk.JOB_SOLDIER, true) && Main.game.isInCombat() && Combat.getTurn()==0) {
			return 2 * damage;
		} else {
			return damage;
		}
	}
	
	public static float calculateSpellDamage(GameCharacter attacker, GameCharacter defender, DamageType damageType, float damage, DamageVariance damageVariance, boolean critical) {

		float minimumDamage = getMinimumSpellDamage(attacker, defender, damageType, damage, damageVariance);
		float maximumDamage = getMaximumSpellDamage(attacker, defender, damageType, damage, damageVariance);

		float difference = maximumDamage - minimumDamage;
		float finalDamage = minimumDamage;
		
		// Add variation:
		if (difference > 0) {
			finalDamage += Math.random()*difference;
		}

		// Is critical:
		if (critical) {
			finalDamage *= (attacker.getAttributeValue(Attribute.CRITICAL_DAMAGE) / 100f);
		}
		
		if(attacker.isPlayer()) {
			finalDamage *= Main.getProperties().difficultyLevel.getDamageModifierPlayer();
		} else {
			finalDamage *= Main.getProperties().difficultyLevel.getDamageModifierNPC();
		}
		
		// Round float value to nearest 1 decimal place:
		finalDamage = (Math.round(finalDamage*10))/10f;
		
		if(attacker.hasTrait(Perk.JOB_SOLDIER, true) && Main.game.isInCombat() && Combat.getTurn()==0) {
			return 2 * finalDamage;
		} else {
			return finalDamage;
		}
	}
	
	public static float calculateSpecialAttackDamage(GameCharacter attacker, GameCharacter defender, DamageType damageType, float damage, DamageVariance damageVariance, boolean critical) {

		float minimumDamage = getMinimumSpecialAttackDamage(attacker, defender, damageType, damage, damageVariance);
		float maximumDamage = getMaximumSpecialAttackDamage(attacker, defender, damageType, damage, damageVariance);

		float difference = maximumDamage - minimumDamage;
		float finalDamage = minimumDamage;
		
		// Add variation:
		if (difference > 0) {
			finalDamage += Math.random()*difference;
		}

		// Is critical:
		if (critical) {
			finalDamage *= (attacker.getAttributeValue(Attribute.CRITICAL_DAMAGE) / 100f);
		}
		
		if(attacker.isPlayer()) {
			finalDamage *= Main.getProperties().difficultyLevel.getDamageModifierPlayer();
		} else {
			finalDamage *= Main.getProperties().difficultyLevel.getDamageModifierNPC();
		}

		// Round float value to nearest 1 decimal place:
		finalDamage = (Math.round(finalDamage*10))/10f;

		if(attacker.hasTrait(Perk.JOB_SOLDIER, true) && Main.game.isInCombat() && Combat.getTurn()==0) {
			return 2 * finalDamage;
		} else {
			return finalDamage;
		}
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
		return getMinimumDamage(attacker, defender, attackType, (attackType == MAIN ? attacker.getMainWeapon() : attackType == OFFHAND ? attacker.getOffhandWeapon() : null));
	}
	public static float getMinimumDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, AbstractWeapon weapon) {
		
		float damage = 0;
		
		if (attackType == MAIN) {
			damage = getModifiedDamage(attacker,
					defender,
					attackType,
					(weapon == null
						? DamageType.PHYSICAL
						: weapon.getDamageType()),
					getMeleeDamage(attacker, weapon) * (weapon == null
																? 1 - DamageVariance.MEDIUM.getPercentage()
																: 1f - weapon.getWeaponType().getDamageVariance().getPercentage()));
		
		} else if (attackType == OFFHAND) {
			damage = getModifiedDamage(attacker, defender, attackType, (weapon == null ? DamageType.PHYSICAL : weapon.getDamageType()),
					getMeleeDamage(attacker, weapon) * (weapon == null ? 1 - DamageVariance.MEDIUM.getPercentage() : 1f - weapon.getWeaponType().getDamageVariance().getPercentage()));
	
		} else {
			damage =  (getModifiedDamage(attacker, defender, attackType, DamageType.LUST, getSeductionDamage(attacker) * 0.9f));
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
		return getMaximumDamage(attacker, defender, attackType, (attackType == MAIN ? attacker.getMainWeapon() : attackType == OFFHAND ? attacker.getOffhandWeapon() : null));
	}
	public static float getMaximumDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, AbstractWeapon weapon) {

		float damage = 0;
		
		if (attackType == MAIN) {
			damage = (getModifiedDamage(attacker, defender, attackType, (weapon == null ? DamageType.PHYSICAL : weapon.getDamageType()),
					getMeleeDamage(attacker, weapon) * (weapon == null ? 1 + DamageVariance.MEDIUM.getPercentage() : 1f + weapon.getWeaponType().getDamageVariance().getPercentage())));

		}  else if (attackType == OFFHAND) {
			damage = getModifiedDamage(attacker, defender, attackType, (weapon == null ? DamageType.PHYSICAL : weapon.getDamageType()),
					getMeleeDamage(attacker, weapon) * (weapon == null ? 1 + DamageVariance.MEDIUM.getPercentage() : 1f + weapon.getWeaponType().getDamageVariance().getPercentage()));
	
		} else {
			damage = (getModifiedDamage(attacker, defender, attackType, DamageType.LUST, getSeductionDamage(attacker) * 1.1f));
		}

		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;
		
		return damage;
	}

	public static float getMinimumSpellDamage(GameCharacter attacker, GameCharacter defender, DamageType damageType, float damage, DamageVariance damageVariance) {
		float minDamage = getModifiedDamage(attacker, defender, Attack.SPELL, damageType, damage * (1 - damageVariance.getPercentage()));

		// Round float value to nearest 1 decimal place:
		minDamage = (Math.round(minDamage*10))/10f;
		
		return minDamage;
	}
	public static float getMaximumSpellDamage(GameCharacter caster, GameCharacter target, DamageType damageType, float damage, DamageVariance damageVariance) {
		float maxDamage = getModifiedDamage(caster, target, Attack.SPELL, damageType, damage * (1 + damageVariance.getPercentage()));

		// Round float value to nearest 1 decimal place:
		maxDamage = (Math.round(maxDamage*10))/10f;
		
		return maxDamage;
	}
	
	public static float getMinimumSpecialAttackDamage(GameCharacter attacker, GameCharacter defender, DamageType damageType, float damage, DamageVariance damageVariance) {
		float minDamage = getModifiedDamage(attacker, defender, Attack.SPECIAL_ATTACK, damageType, damage * (1 - damageVariance.getPercentage()));

		// Round float value to nearest 1 decimal place:
		minDamage = (Math.round(minDamage*10))/10f;
		
		return minDamage;
	}
	public static float getMaximumSpecialAttackDamage(GameCharacter caster, GameCharacter target, DamageType damageType, float damage, DamageVariance damageVariance) {
		float maxDamage = getModifiedDamage(caster, target, Attack.SPECIAL_ATTACK, damageType, damage * (1 + damageVariance.getPercentage()));

		// Round float value to nearest 1 decimal place:
		maxDamage = (Math.round(maxDamage*10))/10f;
		
		return maxDamage;
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
		if (damage < 1) {
			damage = 1;
		}
		
		if (attackType == MAIN || attackType == OFFHAND || attackType == SPECIAL_ATTACK) {

			if (attacker != null) {
				// Attacker modifiers:
				// Damage Type modifier:
				damage *= 1 + Util.getModifiedDropoffValue(attacker.getAttributeValue(damageType.getMultiplierAttribute()), 100)/100f;

				if (damage < 1) {
					damage = 1;
				}
			}

			if (defender != null && !defender.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX)) {
				// Defender modifiers:
				// Damage Type modifier:
				damage *= 1 - Util.getModifiedDropoffValue(defender.getAttributeValue(damageType.getResistAttribute()), 100)/100f;

				if (damage < 1) {
					damage = 1;
				}
			}
			
		} else if(attackType == SPELL) {
			if (attacker != null) {
				// Attacker modifiers:
				damage *= 1 + Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.DAMAGE_SPELLS), 100)/100f;

				damage *= 1 + Util.getModifiedDropoffValue(attacker.getAttributeValue(damageType.getMultiplierAttribute()), 100)/100f;

				if (damage < 1) {
					damage = 1;
				}
			}

			if (defender != null && !defender.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX)) {
				// Defender modifiers:
				damage *= 1 - Util.getModifiedDropoffValue(defender.getAttributeValue(Attribute.RESISTANCE_SPELLS), 100)/100f;
				
				damage *= 1 - Util.getModifiedDropoffValue(defender.getAttributeValue(damageType.getResistAttribute()), 100)/100f;
				
				if (damage < 1) {
					damage = 1;
				}
			}
			
		} else {

			if (attacker != null) {
				// Attacker modifiers:
				damage *= 1 + Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.DAMAGE_LUST), 100)/100f;
				
				if(attacker.hasTrait(Perk.FEMALE_ATTRACTION, true) && defender.isFeminine()) {
					damage *=1.1f;
				}
				if(attacker.hasTrait(Perk.MALE_ATTRACTION, true) && !defender.isFeminine()) {
					damage *=1.1f;
				}

				if (damage < 1) {
					damage = 1;
				}
			}

			if (defender != null && !defender.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX)) {
				// Defender modifiers:
				damage *= 1 - Util.getModifiedDropoffValue(defender.getAttributeValue(Attribute.RESISTANCE_LUST), 100)/100f;
				
				if(defender.getSexualOrientation()==SexualOrientation.ANDROPHILIC && attacker.isFeminine()) {
					damage*=0.5f;
				}
				if(defender.getSexualOrientation()==SexualOrientation.GYNEPHILIC && !attacker.isFeminine()) {
					damage*=0.5f;
				}
				
				if (damage < 1) {
					damage = 1;
				}
			}

		}

		if (defender != null) {
			// Modifiers based on race resistance:
			if(!defender.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX)) {
				damage *= 1 - Util.getModifiedDropoffValue(defender.getAttributeValue(attacker.getRace().getResistanceMultiplier()), 100)/100f;
			}
			// Modifiers based on race damage:
			damage *= 1 + Util.getModifiedDropoffValue(attacker.getAttributeValue(defender.getRace().getDamageMultiplier()), 100)/100f;
			
			// Modifiers based on level:
			float levelBoost = (attacker.getLevel() - defender.getLevel())*2;
			levelBoost = Util.getModifiedDropoffValue(levelBoost, 100)/100f;
			damage = damage * (1 + (levelBoost/100));
		}
		
		return damage;
	}

}
