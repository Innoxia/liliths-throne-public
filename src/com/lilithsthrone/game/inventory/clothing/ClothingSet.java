package com.lilithsthrone.game.inventory.clothing;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.combat.SpecialAttack;
import com.lilithsthrone.game.combat.Spell;

/**
 * @since 0.1.0
 * @version 0.1.85
 * @author Innoxia
 */
public enum ClothingSet {

	// Hat, gloves, shirt, skirt/shorts, thigh-high boots
	ENFORCER("Enforcer", StatusEffect.SET_ENFORCER, 2, null, null),
	
	MAID("Maid", StatusEffect.SET_MAID, 5, null, null),

	WITCH("Witch", StatusEffect.SET_WITCH, 3, null, null),
	
	MILK_MAID("Milk Maid", StatusEffect.SET_MILK_MAID, 2, null, null),

	BDSM("BDSM", StatusEffect.SET_BDSM, 4, null, null),
	
	CATTLE("Cattle", StatusEffect.SET_CATTLE, 3, null, null),

	RAINBOW("Rainbow", StatusEffect.SET_RAINBOW, 2, null, null);

	private String name;
	private int numberRequiredForCompleteSet;
	private List<SpecialAttack> specialAttacks;
	private List<Spell> spells;
	private StatusEffect associatedStatusEffect;

	private ClothingSet(String name, StatusEffect associatedStatusEffect, int numberRequiredForCompleteSet, List<SpecialAttack> specialAttacks, List<Spell> spells) {
		this.name = name;
		this.numberRequiredForCompleteSet = numberRequiredForCompleteSet;
		this.specialAttacks = specialAttacks;
		this.spells = spells;
		this.associatedStatusEffect = associatedStatusEffect;
	}
	
	public boolean isCharacterWearingCompleteSet(GameCharacter target) {
		int setCount = 0;

		for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
			if (c.getClothingType().getClothingSet() == this) {
				setCount++;
			}
		}
		
		return setCount >= this.getNumberRequiredForCompleteSet();
	}

	public String getName() {
		return name;
	}

	public int getNumberRequiredForCompleteSet() {
		return numberRequiredForCompleteSet;
	}

	public List<SpecialAttack> getSpecialAttacks() {
		return specialAttacks;
	}

	public List<Spell> getSpells() {
		return spells;
	}

	public StatusEffect getAssociatedStatusEffect() {
		return associatedStatusEffect;
	}
}
