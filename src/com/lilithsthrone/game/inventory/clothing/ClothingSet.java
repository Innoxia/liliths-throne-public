package com.lilithsthrone.game.inventory.clothing;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.combat.CombatMove;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.11
 * @author Innoxia
 */
public enum ClothingSet {

	ENFORCER("Commanding Enforcer",
			StatusEffect.SET_ENFORCER,
			2,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_UNDER,
					InventorySlot.LEG),
			null,
			null),

	MAID("Hard-working Maid",
			StatusEffect.SET_MAID,
			5,
			Util.newArrayListOfValues(
					InventorySlot.HEAD,
					InventorySlot.TORSO_UNDER,
					InventorySlot.SOCK,
					InventorySlot.FOOT,
					InventorySlot.HAND),
			null,
			null),

	BUTLER("Butler",
			StatusEffect.SET_BUTLER,
			5,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_OVER,
					InventorySlot.TORSO_UNDER,
					InventorySlot.LEG,
					InventorySlot.FOOT,
					InventorySlot.HAND),
			null,
			null),

	WITCH("Witch",
			StatusEffect.SET_WITCH,
			3,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_UNDER,
					InventorySlot.FOOT,
					InventorySlot.HEAD),
			null,
			null),

	SCIENTIST("Brilliant Scientist",
			StatusEffect.SET_SCIENTIST,
			2,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_OVER,
					InventorySlot.EYES),
			null,
			null),

	MILK_MAID("Milk Maid",
			StatusEffect.SET_MILK_MAID,
			2,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_UNDER,
					InventorySlot.HEAD),
			null,
			null),

	BDSM("Locked in Bondage",
			StatusEffect.SET_BDSM,
			4,
			null,
			null,
			null),

	CATTLE("Cattle",
			StatusEffect.SET_CATTLE,
			3,
			Util.newArrayListOfValues(
					InventorySlot.PIERCING_EAR,
					InventorySlot.PIERCING_NOSE,
					InventorySlot.NECK),
			null,
			null),

	GEISHA("Geisha",
			StatusEffect.SET_GEISHA,
			3,
			Util.newArrayListOfValues(
					InventorySlot.HAIR,
					InventorySlot.TORSO_UNDER,
					InventorySlot.FOOT),
			null,
			null), // "We want the /jp/ audience." - Innoxia, probably.

	RONIN("Ronin",
			StatusEffect.SET_RONIN,
			3,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_OVER,
					InventorySlot.TORSO_UNDER,
					InventorySlot.LEG,
					InventorySlot.FOOT),
			null,
			null),

	WEAPON_DAISHO("Daisho",
			StatusEffect.SET_DAISHO,
			2,
			null,
			null,
			null),

	JOLNIR("J&oacute;lnir",
			StatusEffect.SET_JOLNIR,
			3,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_OVER,
					InventorySlot.TORSO_UNDER,
					InventorySlot.FOOT,
					InventorySlot.HEAD),
			null,
			null),

	SUN("Solar Power",
			StatusEffect.SET_SUN,
			3,
			null,
			null,
			null),

	SNOWFLAKE("Blizzard",
			StatusEffect.SET_SNOWFLAKE,
			3,
			null,
			null,
			null),

	RAINBOW("Rainbow",
			StatusEffect.SET_RAINBOW,
			2,
			null,
			null,
			null),

	DARK_SIREN("Dark Siren",
			StatusEffect.SET_DARK_SIREN,
			4,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_OVER,
					InventorySlot.NECK,
					InventorySlot.EYES),
			null,
			null),

	LYSSIETH_GUARD("Lyssieth's Guard",
			StatusEffect.SET_LYSSIETH_GUARD,
			4,
			Util.newArrayListOfValues(
					InventorySlot.FOOT,
					InventorySlot.TORSO_OVER,
					InventorySlot.LEG,
					InventorySlot.HEAD),
			null,
			null)
	
	;

	private String name;
	private int numberRequiredForCompleteSet;
	private List<InventorySlot> blockedSlotsCountingTowardsFullSet;
	private List<CombatMove> combatMoves;
	private List<Spell> spells;
	private StatusEffect associatedStatusEffect;

	private ClothingSet(String name, StatusEffect associatedStatusEffect, int numberRequiredForCompleteSet, List<InventorySlot> blockedSlotsCountingTowardsFullSet, List<CombatMove> combatMoves, List<Spell> spells) {
		this.name = name;
		this.numberRequiredForCompleteSet = numberRequiredForCompleteSet;
		
		if(blockedSlotsCountingTowardsFullSet==null) {
			this.blockedSlotsCountingTowardsFullSet = new ArrayList<>();
		} else {
			this.blockedSlotsCountingTowardsFullSet = blockedSlotsCountingTowardsFullSet;
		}
		
		this.combatMoves = combatMoves;
		this.spells = spells;
		this.associatedStatusEffect = associatedStatusEffect;
	}
	
	public boolean isCharacterWearingCompleteSet(GameCharacter target) {
		int setCount = 0;
		
		for(InventorySlot slot : this.getBlockedSlotsCountingTowardsFullSet()) {
			if(slot.getBodyPartClothingBlock(target) != null) {
				setCount++;
			}
		}
		
		boolean atLeastOneClothingFound = false;
		for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
			if (c.getClothingType().getClothingSet() == this) {
				setCount++;
				atLeastOneClothingFound = true;
			}
		}
		
		for(AbstractWeapon weapon : target.getMainWeaponArray()) {
			if(weapon!=null && weapon.getWeaponType().getClothingSet() == this) {
				setCount++;
				atLeastOneClothingFound = true;
				break; // Only one main weapon should be counted.
			}
		}
		for(AbstractWeapon weapon : target.getOffhandWeaponArray()) {
			if(weapon!=null && weapon.getWeaponType().getClothingSet() == this) {
				setCount++;
				atLeastOneClothingFound = true;
				break; // Only one offhand weapon should be counted.
			}
		}
		
		return atLeastOneClothingFound && setCount >= this.getNumberRequiredForCompleteSet();
	}

	public String getName() {
		return name;
	}

	public int getNumberRequiredForCompleteSet() {
		return numberRequiredForCompleteSet;
	}

	public List<CombatMove> getCombatMoves() {
		return combatMoves;
	}

	public List<Spell> getSpells() {
		return spells;
	}

	public StatusEffect getAssociatedStatusEffect() {
		return associatedStatusEffect;
	}

	public List<InventorySlot> getBlockedSlotsCountingTowardsFullSet() {
		return blockedSlotsCountingTowardsFullSet;
	}
}
