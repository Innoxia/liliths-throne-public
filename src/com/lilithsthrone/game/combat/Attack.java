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

/**
 * @since 0.1.0
 * @version 0.3.4
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
	 * @param attacker The attacking character.
	 * @param defender The defending character.
	 * @return Hit chance from 0 to 1, representing % chance to hit.
	 */
	public static float getHitChance(GameCharacter attacker, GameCharacter defender) {

		// Calculate hit:
		float chanceToHit = 1;//(100 - Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.MISS_CHANCE), 100))/100f;
		
//		chanceToHit *= (1 - (Util.getModifiedDropoffValue(defender.getAttributeValue(Attribute.DODGE_CHANCE), 100)/100f));
		
		return chanceToHit > 1 ? 1 : (chanceToHit < 0 ? 0 : chanceToHit);
	}
	
	public static boolean rollForHit(GameCharacter attacker, GameCharacter defender) {
		return Math.random() < getHitChance(attacker, defender);
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
			return attacker.getUnarmedDamage();
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
		return 10;
	}
	
	public static int calculateSeductionDamage(GameCharacter attacker, GameCharacter defender, int baseDamage, boolean critical) {
		float damage = getModifiedDamage(attacker, defender, Attack.SEDUCTION, null, DamageType.LUST, baseDamage);
		
		// Is critical:
		if (critical) {
			damage *= (attacker.getAttributeValue(Attribute.CRITICAL_DAMAGE) / 100f);
		}
		
		if(attacker.isPlayer()||Main.game.getPlayer().hasCompanion(attacker)) {
			damage *= Main.getProperties().difficultyLevel.getDamageModifierPlayer();
		} else {
			damage *= Main.getProperties().difficultyLevel.getDamageModifierNPC();
		}

		return Math.round(damage);
	}
	
	public static int calculateDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, AbstractWeapon weapon, boolean critical) {
		float damage = getMinimumDamage(attacker, defender, attackType, weapon);

		// Add variation:
		if (getMaximumDamage(attacker, defender, attackType, weapon) - getMinimumDamage(attacker, defender, attackType, weapon) > 0) {
			float difference = getMaximumDamage(attacker, defender, attackType, weapon) - getMinimumDamage(attacker, defender, attackType, weapon);
			
			damage += Math.random()*difference;
		}

		return applyFinalDamageModifiers(attacker, defender, damage, critical);
	}
	
	public static int applyFinalDamageModifiers(GameCharacter attacker, GameCharacter defender, float damage, boolean critical) {
		// Is critical:
		if (critical) {
			damage *= (attacker.getAttributeValue(Attribute.CRITICAL_DAMAGE) / 100f);
		}
		
		if(attacker.isPlayer() || Main.game.getPlayer().hasCompanion(attacker)) {
			damage *= Main.getProperties().difficultyLevel.getDamageModifierPlayer();
		} else {
			damage *= Main.getProperties().difficultyLevel.getDamageModifierNPC();
		}
		
		if(attacker.hasTrait(Perk.JOB_SOLDIER, true) && Main.game.isInCombat() && Combat.getTurn()==0) {
			return 2 * Math.round(damage);
		} else {
			return Math.round(damage);
		}
	}
	
	public static int calculateSpellDamage(GameCharacter attacker, GameCharacter defender, DamageType damageType, float damage, DamageVariance damageVariance, boolean critical) {
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
		
		if(attacker.isPlayer()||Main.game.getPlayer().hasCompanion(attacker)) {
			finalDamage *= Main.getProperties().difficultyLevel.getDamageModifierPlayer();
		} else {
			finalDamage *= Main.getProperties().difficultyLevel.getDamageModifierNPC();
		}
		
		if(attacker.hasTrait(Perk.JOB_SOLDIER, true) && Main.game.isInCombat() && Combat.getTurn()==0) {
			return 2 * Math.round(finalDamage);
		} else {
			return Math.round(finalDamage);
		}
	}
	
	public static int calculateSpecialAttackDamage(GameCharacter attacker, GameCharacter defender, DamageType damageType, float damage, DamageVariance damageVariance, boolean critical) {
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
		
		if(attacker.isPlayer()||Main.game.getPlayer().hasCompanion(attacker)) {
			finalDamage *= Main.getProperties().difficultyLevel.getDamageModifierPlayer();
		} else {
			finalDamage *= Main.getProperties().difficultyLevel.getDamageModifierNPC();
		}
		
		if(attacker.hasTrait(Perk.JOB_SOLDIER, true) && Main.game.isInCombat() && Combat.getTurn()==0) {
			return 2 * Math.round(finalDamage);
		} else {
			return Math.round(finalDamage);
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
	 * @param weapon
	 *            The weapon being used. Pass in null if this attack type is not MAIN or OFFHAND.
	 * @return Minimum damage possible for this attack.
	 */
	public static int getMinimumDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, AbstractWeapon weapon) {
		float damage = 0;
		
		if (attackType == MAIN
				|| attackType == OFFHAND) {
			damage = getModifiedDamage(attacker,
					defender,
					attackType,
					weapon,
					(weapon == null
						? attacker.getBodyMaterial().getUnarmedDamageType()
						: weapon.getDamageType()),
					getMeleeDamage(attacker, weapon) * (weapon == null
																? 1f - DamageVariance.MEDIUM.getPercentage()
																: 1f - weapon.getWeaponType().getDamageVariance().getPercentage()));
			
		} else {
			damage = (getModifiedDamage(attacker, defender, attackType, weapon, DamageType.LUST, getSeductionDamage(attacker) * 0.9f)); // TODO why is it 90%?
		}
		
		return Math.round(damage);
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
	 * @param weapon
	 *            The weapon being used. Pass in null if this attack type is not MAIN or OFFHAND.
	 * @return Minimum damage possible for this attack.
	 */
	public static int getMaximumDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, AbstractWeapon weapon) {

		float damage = 0;
		
		if (attackType == MAIN
				|| attackType == OFFHAND) {
			damage = getModifiedDamage(attacker, defender, attackType, weapon, (weapon == null ? attacker.getBodyMaterial().getUnarmedDamageType() : weapon.getDamageType()),
					getMeleeDamage(attacker, weapon) * (weapon == null ? 1f + DamageVariance.MEDIUM.getPercentage() : 1f + weapon.getWeaponType().getDamageVariance().getPercentage()));
			
		} else {
			damage = (getModifiedDamage(attacker, defender, attackType, weapon, DamageType.LUST, getSeductionDamage(attacker) * 1.1f));
		}

		return Math.round(damage);
	}

	public static float getMinimumSpellDamage(GameCharacter attacker, GameCharacter defender, DamageType damageType, float damage, DamageVariance damageVariance) {
		float minDamage = getModifiedDamage(attacker, defender, Attack.SPELL, null, damageType, damage * (1 - damageVariance.getPercentage()));

		// Round float value to nearest 1 decimal place:
		minDamage = (Math.round(minDamage*10))/10f;
		
		return minDamage;
	}
	public static float getMaximumSpellDamage(GameCharacter caster, GameCharacter target, DamageType damageType, float damage, DamageVariance damageVariance) {
		float maxDamage = getModifiedDamage(caster, target, Attack.SPELL, null, damageType, damage * (1 + damageVariance.getPercentage()));

		// Round float value to nearest 1 decimal place:
		maxDamage = (Math.round(maxDamage*10))/10f;
		
		return maxDamage;
	}
	
	public static float getMinimumSpecialAttackDamage(GameCharacter attacker, GameCharacter defender, DamageType damageType, float damage, DamageVariance damageVariance) {
		float minDamage = getModifiedDamage(attacker, defender, Attack.SPECIAL_ATTACK, null, damageType, damage * (1 - damageVariance.getPercentage()));

		// Round float value to nearest 1 decimal place:
		minDamage = (Math.round(minDamage*10))/10f;
		
		minDamage *= 1 + Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.DAMAGE_UNARMED), 100)/100f;
		
		return minDamage;
	}
	public static float getMaximumSpecialAttackDamage(GameCharacter caster, GameCharacter target, DamageType damageType, float damage, DamageVariance damageVariance) {
		float maxDamage = getModifiedDamage(caster, target, Attack.SPECIAL_ATTACK, null, damageType, damage * (1 + damageVariance.getPercentage()));

		// Round float value to nearest 1 decimal place:
		maxDamage = (Math.round(maxDamage*10))/10f;

		maxDamage *= 1 + Util.getModifiedDropoffValue(caster.getAttributeValue(Attribute.DAMAGE_UNARMED), 100)/100f;
		
		return maxDamage;
	}
	
	/**
	 * Applies attacker bonuses to the supplied attack value, then returns the result.
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
	public static float getModifiedDamage(GameCharacter attacker, GameCharacter defender, Attack attackType, AbstractWeapon weapon, DamageType damageType, float attackersDamage) {
		float damage = 0;
		boolean damageDoubledFromElemental = false;
		
		if(defender!=null && defender.isImmuneToDamageType(damageType)) {
			return 0;
		}
			
		if(attacker instanceof Elemental) {
			switch(attacker.getBodyMaterial()) {
				case AIR:
					damageDoubledFromElemental = ((Elemental)attacker).hasStatusEffect(StatusEffect.ELEMENTAL_AIR_SERVANT_OF_AIR_ELEMENTAL_BUFF);
					break;
				case ARCANE:
					damageDoubledFromElemental = ((Elemental)attacker).hasStatusEffect(StatusEffect.ELEMENTAL_ARCANE_SERVANT_OF_ARCANE_ELEMENTAL_BUFF);
					break;
				case FIRE:
					damageDoubledFromElemental = ((Elemental)attacker).hasStatusEffect(StatusEffect.ELEMENTAL_FIRE_SERVANT_OF_FIRE_ELEMENTAL_BUFF);
					break;
				case FLESH:
				case SLIME:
					break;
				case RUBBER:
				case STONE:
					damageDoubledFromElemental = ((Elemental)attacker).hasStatusEffect(StatusEffect.ELEMENTAL_EARTH_SERVANT_OF_EARTH_ELEMENTAL_BUFF);
					break;
				case ICE:
				case WATER:
					damageDoubledFromElemental = ((Elemental)attacker).hasStatusEffect(StatusEffect.ELEMENTAL_WATER_SERVANT_OF_WATER_ELEMENTAL_BUFF);
					break;
			}
		}
		
		if (attackType == MAIN || attackType == OFFHAND || attackType == SPECIAL_ATTACK) {
			if(damageDoubledFromElemental) {
				damage += attackersDamage*2;
			} else {
				damage += attackersDamage;
			}
			
			if (attacker != null) { // Attacker modifiers:
				damage += attackersDamage * (Util.getModifiedDropoffValue(attacker.getAttributeValue(damageType.getMultiplierAttribute()), 100)/100f);
				
				if(weapon!=null) {
					if(weapon.getWeaponType().isMelee()) {
						damage += attackersDamage * (Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.DAMAGE_MELEE_WEAPON), 100)/100f);
					} else {
						damage += attackersDamage * (Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.DAMAGE_RANGED_WEAPON), 100)/100f);
					}
				} else {
					damage += attackersDamage * (Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.DAMAGE_UNARMED), 100)/100f);
				}
				
				if (damage < 1) {
					damage = 1;
				}
			}

//			if (defender != null && !defender.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX)) { // Defender modifiers:
//				damage *= 1 - Util.getModifiedDropoffValue(defender.getAttributeValue(damageType.getResistAttribute()), 100)/100f;
//				
//				if (damage < 1) {
//					damage = 1;
//				}
//			}
			
		} else if(attackType == SPELL) {
			if(damageDoubledFromElemental) {
				damage += attackersDamage*2;
			} else {
				damage += attackersDamage;
			}
			
			if (attacker != null) { // Attacker modifiers:
				damage += attackersDamage * (Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.DAMAGE_SPELLS), 100)/100f);
				damage += attackersDamage * (Util.getModifiedDropoffValue(attacker.getAttributeValue(damageType.getMultiplierAttribute()), 100)/100f);
			}

//			if (defender != null && !defender.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX)) {
//				damage *= 1 - Util.getModifiedDropoffValue(defender.getAttributeValue(damageType.getResistAttribute()), 100)/100f;
//			}
			
		} else {
			if (attacker != null) { // Attacker modifiers:
				damage += attackersDamage * (1 + Util.getModifiedDropoffValue(attacker.getAttributeValue(Attribute.DAMAGE_LUST), 100)/100f);
				
				if(defender!=null) {
					if((attacker.hasTrait(Perk.FEMALE_ATTRACTION, true) && defender.isFeminine())
							|| (attacker.hasTrait(Perk.MALE_ATTRACTION, true) && !defender.isFeminine())) {
						damage += attackersDamage * 0.1f;
					}
				}
				if (damage < 1) {
					damage = 1;
				}
			}

			if (defender != null && !defender.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX)) {
				// Defender modifiers:
//				damage *= 1 - Util.getModifiedDropoffValue(defender.getAttributeValue(Attribute.RESISTANCE_LUST), 100)/100f;
				if(attacker!=null) {
					if((defender.getSexualOrientation()==SexualOrientation.ANDROPHILIC && attacker.isFeminine())
							|| (attacker.getSexualOrientation()==SexualOrientation.ANDROPHILIC && defender.isFeminine())) {
						damage*=0.5f;
					}
					if((defender.getSexualOrientation()==SexualOrientation.GYNEPHILIC && !attacker.isFeminine())
							|| (attacker.getSexualOrientation()==SexualOrientation.GYNEPHILIC && !defender.isFeminine())) {
						damage*=0.5f;
					}
				}
				if (damage < 1) {
					damage = 1;
				}
			}
		}
		
		if (attacker != null && defender!=null) {
			// Modifiers based on race damage:
			damage += attackersDamage * (Util.getModifiedDropoffValue(attacker.getAttributeValue(defender.getSubspecies().getDamageMultiplier()), 100)/100f);
			
			// Modifiers based on level:
//			float levelBoost = (attacker.getLevel() - defender.getLevel())*2;
//			levelBoost = Util.getModifiedDropoffValue(levelBoost, 100)/100f;
//			damage = damage * (1 + (levelBoost/100));
		}
		
		return damage;
	}

}
