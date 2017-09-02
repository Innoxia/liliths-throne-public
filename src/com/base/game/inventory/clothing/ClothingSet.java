package com.base.game.inventory.clothing;

import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.effects.StatusEffect;
import com.base.game.combat.SpecialAttack;
import com.base.game.combat.Spell;
import com.base.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum ClothingSet {

	// Hat, gloves, shirt, skirt/shorts, thigh-high boots
	ENFORCER("Enforcer", StatusEffect.SET_ENFORCER.getDescription(Main.game.getPlayer()), StatusEffect.SET_ENFORCER, 2, null, null),
	
	MAID("Maid", StatusEffect.SET_MAID.getDescription(Main.game.getPlayer()), StatusEffect.SET_MAID, 5, null, null),
	
	MILK_MAID("Milk Maid", StatusEffect.SET_MILK_MAID.getDescription(Main.game.getPlayer()), StatusEffect.SET_MILK_MAID, 2, null, null),

	BDSM("BDSM", StatusEffect.SET_BDSM.getDescription(Main.game.getPlayer()), StatusEffect.SET_BDSM, 4, null, null),

	RAINBOW("Rainbow", StatusEffect.SET_RAINBOW.getDescription(Main.game.getPlayer()), StatusEffect.SET_RAINBOW, 2, null, null);

	private String name, description;
	private int numberRequiredForCompleteSet;
	private List<SpecialAttack> specialAttacks;
	private List<Spell> spells;
	private StatusEffect associatedStatusEffect;

	private ClothingSet(String name, String description, StatusEffect associatedStatusEffect, int numberRequiredForCompleteSet, List<SpecialAttack> specialAttacks, List<Spell> spells) {
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
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
