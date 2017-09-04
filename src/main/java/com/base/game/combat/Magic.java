package com.base.game.combat;

import java.util.ArrayList;
import java.util.List;

import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.0
 * @version 0.1.0
 * @author Innoxia
 */
public enum Magic {

	/*
	 * Chaos: Damage of all elemental types Order: Incapacitate & status effects
	 * Light: Heal & buffs Shadow: Debuffs
	 * 
	 * Damage: Fire: Mid-damage with not much variance Cold: Low damage and
	 * applies debuffs Poison: Low damage and applies DoTs Electric: High-damage
	 * with high variance
	 * 
	 */
	CHAOS_BASIC("chaos magic (basic)", "", Util.newArrayListOfValues(new ListValue<Spell>(Spell.FIREBALL_1))),

	ORDER_BASIC("order magic (basic)", "", Util.newArrayListOfValues(new ListValue<Spell>(Spell.FIREBALL_1))),

	SHADOW_BASIC("shadow magic (basic)", "", Util.newArrayListOfValues(new ListValue<Spell>(Spell.FIREBALL_1))),

	LIGHT_BASIC("light magic (basic)", "", Util.newArrayListOfValues(new ListValue<Spell>(Spell.CLEANSE)));

	private String name, description;
	private List<Spell> spells;

	private Magic(String name, String description, List<Spell> spells) {
		if (spells != null)
			this.spells = spells;
		else
			this.spells = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Spell> getSpells() {
		return spells;
	}
}
