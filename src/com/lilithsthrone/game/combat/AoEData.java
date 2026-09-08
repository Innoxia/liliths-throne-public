package com.lilithsthrone.game.combat;

import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;


/**
 * @since 0.4.11.2
 * @version 0.4.11.2
 * @author Innoxia
 */
public class AoEData {
	private int chance;
	private String damageType;
	private String damage;
	private DamageVariance damageVariance;
	private Map<AbstractStatusEffect, Integer> aoeStatusEffects;
	private Map<AbstractStatusEffect, Integer> aoeStatusEffectsCritical;
	
	public AoEData(int chance, String damageType, String damage, DamageVariance damageVariance, Map<AbstractStatusEffect, Integer> aoeStatusEffects, Map<AbstractStatusEffect, Integer> aoeStatusEffectsCritical) {
		this.chance = chance;
		this.damageType = damageType;
		this.damage = damage;
		this.damageVariance = damageVariance;
		this.aoeStatusEffects = aoeStatusEffects;
		this.aoeStatusEffectsCritical = aoeStatusEffectsCritical;
	}
	
	public int getChance() {
		return chance;
	}
	
	public DamageVariance getDamageVariance() {
		return damageVariance;
	}
	
	public DamageType getDamageType(GameCharacter performer, GameCharacter target) {
		DamageType dt = DamageType.PHYSICAL;
		try{
			dt = DamageType.valueOf(UtilText.parse(performer, damageType).trim());
		} catch(Exception ex) {
			System.err.println("AoEData loading error: getDamageType() parsing not recognised! (Set to DamageType.PHYSICAL)");
		}
		if(dt==DamageType.UNARMED) {
			return DamageType.UNARMED.getParentDamageType(performer, target);
		}
		return dt;
	}
	
	public int getDamage(GameCharacter performer) {
		float dmg = 1;
		try{
			String s = UtilText.parse(performer, damage).trim();
			dmg = Float.valueOf(s);
		} catch(Exception ex) {
			System.err.println("AoEData loading error: getDamage() parsing not recognised! (Set to 1)");
		}
		return (int) dmg;
	}

    public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
    	if(isCritical) {
    		return aoeStatusEffectsCritical;
    	}
		return aoeStatusEffects;
	}
	
}
