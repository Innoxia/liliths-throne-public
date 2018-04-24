package com.lilithsthrone.game.combat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.2.4
 * @version 0.2.4
 * @author Innoxia
 */
public enum SpellUpgrade {

	// Fire:
	
	FIREBALL_1(true,
			SpellSchool.FIRE,
			"fireball_lingering_flames",
			"Lingering Flames",
			"Arcane flames continue to burn away at the target for some time after Fireball's impact.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<b>5</b> [style.colourFire(Fire Damage)] per turn for [style.colourGood(2 turns)]"))),

	FIREBALL_2(SpellSchool.FIRE,
			"fireball_twin_comets",
			"Twin Comets",
			"Fireball splits into two comets immediately after being cast, and, seemingly with a mind of their own, prioritise different targets where at all possible.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Fires [style.colourExcellent(two)] Fireballs, doing <b>10</b> [style.colourFire(Fire Damage)] each"))),

	FIREBALL_3(SpellSchool.FIRE,
			"fireball_burning_fury",
			"Burning Fury",
			"Fireball's twin comets now burn with an intense fury, doing considerably more damage.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Twin Comets now do <b>15</b> [style.colourFire(Fire Damage)] each"))),
	
	
	FLASH_1(true,
			SpellSchool.FIRE,
			"flash_secondary_sparks",
			"Secondary Sparks",
			"After the initial flash, a series of dazzling sparks crackle and burst into life in front of the target's face.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Flash now [style.colourExcellent(stuns)] for [style.colourGood(2 turns)]"))),

	FLASH_2(SpellSchool.FIRE,
			"flash_arcing_flash",
			"Arcing Flash",
			"A secondary Flash arcs off from the first, seeking out another target to dazzle.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Fires [style.colourExcellent(two)] Flashes"))),

	FLASH_3(SpellSchool.FIRE,
			"flash_efficient_burn",
			"Efficient Burn",
			"By focusing its power into a smaller, more concentrated burst, the caster is able to reduce the cost of Flash, while losing none of its effectiveness.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourExcellent(Reduces)] base cost to [style.boldMana(20)] aura"))),
	
	
	CLOAK_OF_FLAMES_1(true,
			SpellSchool.FIRE,
			"cloak_of_flames_incendiary",
			"Incendiary",
			"With every strike, a jet of fire from the Cloak of Flames shoots forwards to burn the enemy. Damage done is affected by the wearer's fire damage, and the target's fire resistance.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Adds <b>5</b> [style.colourFire(Fire Damage)] to all attacks"))),

	CLOAK_OF_FLAMES_2(SpellSchool.FIRE,
			"cloak_of_flames_inferno",
			"Inferno",
			"The Cloak of Flames imbues the wearer with the knowledge of how to deal the most fire damage possible.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourExcellent(Gain)] +25 [style.boldFire(Fire Damage)]"))),

	CLOAK_OF_FLAMES_3(SpellSchool.FIRE,
			"cloak_of_flames_ring_of_fire",
			"Ring of Fire",
			"Any enemy that approaches the Clock of Flames is now greeted by a retaliatory burst of fire. Damage done is affected by the wearer's fire damage, and the attacker's fire resistance.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<b>Melee</b> or <b>unarmed</b> attackers take <b>5</b> [style.colourFire(Fire Damage)]"))),

	
	ELEMENTAL_FIRE_1(true,
			SpellSchool.FIRE,
			"elemental_fire_wildfire",
			"Wildfire",
			"While the elemental is summoned, all allied combatants gain +10 fire damage.",
			null,
			null),
	
	ELEMENTAL_FIRE_2(SpellSchool.FIRE,
			"elemental_fire_burning_desire",
			"Burning Desire",
			"While the elemental is summoned, all enemy combatants suffer -10 seduction resist.",
			null,
			null),

	ELEMENTAL_FIRE_3A(SpellSchool.FIRE,
			"elemental_fire_servant_of_fire",
			"Servant of Fire",
			"While the elemental is summoned, the caster suffers -40% maximum energy and aura, but the fire elemental's energy, aura, and damage are all doubled.",
			null,
			null),

	ELEMENTAL_FIRE_3B(SpellSchool.FIRE,
			"elemental_fire_binding_of_fire",
			"Binding of Fire",
			"While the elemental is summoned, the caster gains +25 fire damage and +25 fire resist.",
			null,
			null),
	
	// Water:
	
	ICE_SHARD_1(true, SpellSchool.WATER, "ice_shard_freezing_fog", "Freezing Fog", "Ice Shard now applies a debuff to the target, causing -15 spell efficiency for 2 turns.", null, null),
	ICE_SHARD_2(SpellSchool.WATER, "ice_shard_cold_snap", "Cold Snap", "If the target is affected by freezing fog, Ice Shard gains +25 critical chance.", null, null),
	ICE_SHARD_3(SpellSchool.WATER, "ice_shard_deep_freeze", "Deep Freeze", "If Ice Shard critically hits a target under the effect of Freezing Fog, the target is stunned for 1 turn.", null, null),

	RAIN_CLOUD_1(true, SpellSchool.WATER, "rain_cloud_deep_chill", "Deep Chill", "The target of Rain Cloud additionally suffers -25 ice resistance.", null, null),
	RAIN_CLOUD_2(SpellSchool.WATER, "rain_cloud_downpour", "Downpour", "The target of Rain Cloud additionally suffers +10 miss chance.", null, null),
	RAIN_CLOUD_3(SpellSchool.WATER, "rain_cloud_cloud_burst", "Cloud Burst", "If the target misses while under the effect of Rain Cloud, they additionally suffer -30 spell efficiency, and the duration is refreshed to 3 turns.", null, null),

	SOOTHING_WATERS_1(true, SpellSchool.WATER, "soothing_waters_arcane_springs", "Arcane Springs", "Soothing Waters now additionally restores 20% of the target's aura.", null, null),
	SOOTHING_WATERS_2(SpellSchool.WATER, "soothing_waters_rejuvenation", "Rejuvenation", "Restored energy is now 40%.", null, null),
	SOOTHING_WATERS_3(SpellSchool.WATER, "soothing_waters_bouncing_orb", "Bouncing Orb", "Soothing Waters now heals all non-targeted allied combatants for 10% energy, 10% aura.", null, null),

	ELEMENTAL_WATER_1(true, SpellSchool.WATER, "elemental_water_crashing_waves", "Crashing Waves", "While the elemental is summoned, all allied combatants gain +10 ice damage.", null, null),
	ELEMENTAL_WATER_2(SpellSchool.WATER, "elemental_water_calm_waters", "Calm Waters", "While the elemental is summoned, all allied combatants gain +10 seduction resist.", null, null),
	ELEMENTAL_WATER_3A(SpellSchool.WATER, "elemental_water_servant_of_water", "Servant of Water", "While the elemental is summoned, the caster suffers -40% maximum energy and aura, but the water elemental's energy, aura, and damage are all doubled.", null, null),
	ELEMENTAL_WATER_3B(SpellSchool.WATER, "elemental_water_binding_of_water", "Binding of Water", "While the elemental is summoned, the caster gains +25 ice damage and +25 ice resist.", null, null),
	
	// Air:
	
	POISON_VAPOURS_1(true, SpellSchool.AIR, "poison_vapours_choking_haze", "Choking Haze", "Poison Vapours now applies a debuff to the target, causing +10 miss chance for 3 turns.", null, null),
	POISON_VAPOURS_2(SpellSchool.AIR, "poison_vapours_arcane_sickness", "Arcane Sickness", "The target of Poison Vapours now suffers the same amount of energy damage as aura damage per turn.", null, null),
	POISON_VAPOURS_3(SpellSchool.AIR, "poison_vapours_weakening_cloud", "Weakening Cloud", "The target of Poison Vapours additionally suffers -15 physical damage and -25 critical damage.", null, null),
	
	VACUUM_1(true, SpellSchool.AIR, "vacuum_secondary_voids", "Secondary Voids", "The target of Vacuum additionally suffers -15 critical chance, and miss chance is increased to +20.", null, null),
	VACUUM_2(SpellSchool.AIR, "vacuum_suction", "Suction", "There is now a 10% chance each turn for the target of Vacuum to have a random outer layer of their clothing sucked off to the floor.", null, null),
	VACUUM_3(SpellSchool.AIR, "vacuum_total_void", "Total Void", "The strip chance from Suction is increased to 25%.", null, null),

	PROTECTIVE_GUSTS_1(true, SpellSchool.AIR, "protective_gusts_guiding_wind", "Guiding Wind", "The target of Buffeting Winds additionally gains +10 critical chance, and dodge chance is increased to +20.", null, null),
	PROTECTIVE_GUSTS_2(SpellSchool.AIR, "protective_gusts_focused_blast", "Focused Blast", "The additional effect of Guiding Wind is increased to +25 critical chance.", null, null),
	PROTECTIVE_GUSTS_3(SpellSchool.AIR, "protective_gusts_lingering_presence", "Lingering Presence", "Protective Gusts now lasts for 5 turns.", null, null),

	ELEMENTAL_AIR_1(true, SpellSchool.AIR, "elemental_air_whirlwind", "Whirlwind", "While the elemental is summoned, all allied combatants gain +10 critical chance and +5 dodge chance.", null, null),
	ELEMENTAL_AIR_2(SpellSchool.AIR, "elemental_air_vitalising_scents", "Vitalising Scents", "While the elemental is summoned, all allied combatants gain +25 energy.", null, null),
	ELEMENTAL_AIR_3A(SpellSchool.AIR, "elemental_air_servant_of_air", "Servant of Air", "While the elemental is summoned, the caster suffers -40% maximum energy and aura, but the air elemental's energy, aura, and damage are all doubled.", null, null),
	ELEMENTAL_AIR_3B(SpellSchool.AIR, "elemental_air_binding_of_air", "Binding of Air", "While the elemental is summoned, the caster gains +25 poison damage and +25 poison resist.", null, null),

	// Earth:
	
	SLAM_1(true, SpellSchool.EARTH, "slam_ground_shake", "Ground Shake", "Slam now applies a debuff to the target, causing +10 miss chance for 2 turns.", null, null),
	SLAM_2(SpellSchool.EARTH, "slam_aftershock", "Aftershock", "When Ground Shake ends, the affected target takes 5 physical damage.", null, null),
	SLAM_3(SpellSchool.EARTH, "slam_earthquake", "Earthquake", "Ground Shake is now applied to all enemy combatants.", null, null),

	TELEKENETIC_SHOWER_1(true, SpellSchool.EARTH, "telekenetic_shower_mind_over_matter", "Mind Over Matter", "Telekenetic Shower's duration is doubled to 4 turns.", null, null),
	TELEKENETIC_SHOWER_2(SpellSchool.EARTH, "telekenetic_shower_precision_strikes", "Precision Strikes", "The target of Telekenetic Shower additionally suffers -10 physical resistance.", null, null),
	TELEKENETIC_SHOWER_3(SpellSchool.EARTH, "telekenetic_shower_unseen_force", "Unseen Force", "The damage from Telekenetic Shower is doubled.", null, null),

	STONE_SHELL_1(true, SpellSchool.EARTH, "stone_shell_shifting_sands", "Shifting Sands", "The target of Stone Shell additionally gains +10 dodge chance.", null, null),
	STONE_SHELL_2(SpellSchool.EARTH, "stone_shell_hardened_carapace", "Hardened Carapace", "Stone Shell's effect is increased to +50 physical resistance.", null, null),
	STONE_SHELL_3(SpellSchool.EARTH, "stone_shell_explosive_finish", "Explosive Finish", "When Stone Shell's effect comes to and end, all enemy combatants take 5 physical damage.", null, null),

	ELEMENTAL_EARTH_1(true, SpellSchool.EARTH, "elemental_earth_rolling_stone", "Rolling Stone", "While the elemental is summoned, all allied combatants gain +25 critical damage.", null, null),
	ELEMENTAL_EARTH_2(SpellSchool.EARTH, "elemental_earth_hardening", "Hardening", "While the elemental is summoned, all allied combatants gain +10 physical resist.", null, null),
	ELEMENTAL_EARTH_3A(SpellSchool.EARTH, "elemental_earth_servant_of_earth", "Servant of Earth", "While the elemental is summoned, the caster suffers -40% maximum energy and aura, but the earth elemental's energy, aura, and damage are all doubled.", null, null),
	ELEMENTAL_EARTH_3B(SpellSchool.EARTH, "elemental_earth_binding_of_earth", "Binding of Earth", "While the elemental is summoned, the caster gains +25 physical damage and +25 physical resist.", null, null),

	// Arcane:
	
	STEAL_1(true, SpellSchool.ARCANE, "steal_stripper", "Stripper", "Steal can now also affect outer layers of the target's clothing.", null, null),
	STEAL_2(SpellSchool.ARCANE, "steal_disarm", "Disarm", "Steal can now also affect the target's equipped weapons.", null, null),
	STEAL_3(SpellSchool.ARCANE, "steal_deep_reach", "Deep Reach", "Steal can now also affect any layer of the target's clothing.", null, null),

	CLEANSE_1(true, SpellSchool.ARCANE, "cleanse_selective_cleanse", "Selective Cleansing", "Cleanse no longer removes beneficial status effects from targeted allies, and no longer removes negative status effects from targeted enemies.", null, null),
	CLEANSE_2(SpellSchool.ARCANE, "cleanse_bolstered_defence", "Bolstered Defence", "Allied targets of Cleanse are immune to negative status effects for 2 turns.", null, null),
	CLEANSE_3(SpellSchool.ARCANE, "cleanse_impenetrable", "Impenetrable", "Bolstered Defence's duration is doubled to 4 turns.", null, null),

	TELEPORT_1(true, SpellSchool.ARCANE, "teleport_arcane_arrival", "Arcane Arrival", "Teleport additionally deals 5 lust damage to a random enemy combatant.", null, null),
	TELEPORT_2(SpellSchool.ARCANE, "teleport_mass_teleportation", "Mass Teleportation", "Applies teleport's effect to all allied combatants. Map-wide teleportation is no longer disabled by having companions.", null, null),
	TELEPORT_3(SpellSchool.ARCANE, "teleport_rebounding_teleportation", "Rebounding Teleportation", "Teleport's effects are increased to 2 turns. Arcane Arrival's effect is applied each turn.", null, null),

	TELEPATHIC_COMMUNICATION_1(true, SpellSchool.ARCANE, "telepathic_communication_echoing_moans", "Echoing Moans", "Telepathic Communication's effect is increased to +25 seduction damage.", null, null),
	TELEPATHIC_COMMUNICATION_2(SpellSchool.ARCANE, "telepathic_communication_power_of_suggestion", "Power of Suggestion", "Tease attacks against the target of Telepathic Communication now apply a 2-turn debuff, causing -25 physical resistance.", null, null),
	TELEPATHIC_COMMUNICATION_3(SpellSchool.ARCANE, "telepathic_communication_projected_touch", "Projected Touch", "Telepathic Communication's effect is increased to +50 seduction damage.", null, null),

	ARCANE_CLOUD_1(true, SpellSchool.ARCANE, "arcane_cloud_lightning", "Arcane Lightning", "Arcane Storm now deals 5 lust damage per turn.", null, null),
	ARCANE_CLOUD_2(SpellSchool.ARCANE, "arcane_cloud_thunder", "Arcane Thunder", "The target of Arcane Storm now suffers +10 miss chance.", null, null),
	ARCANE_CLOUD_3(SpellSchool.ARCANE, "arcane_cloud_localised_storm", "Localised Storm", "Arcane Cloud is now applied to all enemy combatants, and Arcane Lightning's effect is increased to 10 lust damage per turn.", null, null),

	ARCANE_AROUSAL_1(true, SpellSchool.ARCANE, "arcane_arousal_overwhelming_lust", "Overwhelming Lust", "Arcane Arousal's damage is doubled.", null, null),
	ARCANE_AROUSAL_2(SpellSchool.ARCANE, "arcane_arousal_lustful_distraction", "Lustful Distraction", "Arcane Arousal now applies a debuff to the target, causing them to suffer +10 miss chance for 2 turns.", null, null),
	ARCANE_AROUSAL_3(SpellSchool.ARCANE, "arcane_arousal_dirty_promises", "Dirty Promises", "Lustful Distraction now additionally applies -15 seduction resistance to the target.", null, null),

	LILITHS_COMMAND_1(true, SpellSchool.ARCANE, "liliths_command_overpowering_presence", "Overpowering Presence", "Lilith's Command now has a 50% chance of success.", null, null),
	LILITHS_COMMAND_2(SpellSchool.ARCANE, "liliths_command_demonic_servants", "Demonic Servants", "Lilith's Command now has a 75% chance of success, and now also affects demons.", null, null),
	LILITHS_COMMAND_3(SpellSchool.ARCANE, "liliths_command_ultimate_power", "Ultimate Power", "Lilith's Command now has a 100% chance of success.", null, null),

	ELEMENTAL_ARCANE_1(true, SpellSchool.ARCANE, "elemental_arcane_lewd_encouragements", "Lewd Encouragements", "While the elemental is summoned, all allied combatants gain +20 seduction damage.", null, null),
	ELEMENTAL_ARCANE_2(SpellSchool.ARCANE, "elemental_arcane_caressing_touch", "Caressing Touch", "While the elemental is summoned, all enemy combatants suffer -20 seduction resistance.", null, null),
	ELEMENTAL_ARCANE_3A(SpellSchool.ARCANE, "elemental_arcane_servant_of_arcane", "Servant of Arcane", "While the elemental is summoned, the caster suffers -40% maximum energy and aura, but the arcane elemental's energy, aura, and damage are all doubled.", null, null),
	ELEMENTAL_ARCANE_3B(SpellSchool.ARCANE, "elemental_arcane_binding_of_arcane", "Binding of Arcane", "While the elemental is summoned, the caster gains +25 seduction damage and +25 seduction resist.", null, null);

	
	private boolean isAlwaysAvailable;
	private SpellSchool spellSchool;
	private String name;
	private String description;

	private HashMap<Attribute, Integer> attributeModifiers;
	private List<String> extraEffects;
	private List<String> modifiersList;
	
	private String SVGString;
	
	private SpellUpgrade(SpellSchool spellSchool,
			String pathName,
			String name,
			String description,
			HashMap<Attribute, Integer> attributeModifiers,
			List<String> extraEffects) {
		this(false, spellSchool, pathName, name, description, attributeModifiers, extraEffects);
	}
	
	private SpellUpgrade(boolean isAlwaysAvailable,
			SpellSchool spellSchool,
			String pathName,
			String name,
			String description,
			HashMap<Attribute, Integer> attributeModifiers,
			List<String> extraEffects) {
		
		this.isAlwaysAvailable = isAlwaysAvailable;
		this.spellSchool = spellSchool;
		this.name = name;
		this.description = description;
		
		this.attributeModifiers = attributeModifiers;
		this.extraEffects = extraEffects;
		
		modifiersList = new ArrayList<>();
		
		if (attributeModifiers != null) {
			for (Entry<Attribute, Integer> e : attributeModifiers.entrySet())
				modifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b>"
						+ " <b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
		}
		
		if (extraEffects != null) {
			modifiersList.addAll(extraEffects);
		}
		
		try {
			if(!pathName.isEmpty()) {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/combat/spell/upgrade/" + pathName + ".svg");
				SVGString = Util.inputStreamToString(is);
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAlwaysAvailable() {
		return isAlwaysAvailable;
	}
	
	public SpellSchool getSpellSchool() {
		return spellSchool;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getModifiersAsStringList() {
		return modifiersList;
	}

	public HashMap<Attribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public List<String> getExtraEffects() {
		return extraEffects;
	}

	public String getSVGString() {
		return SVGString;
	}
	
}
