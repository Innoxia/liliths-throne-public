package com.lilithsthrone.game.combat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
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
					new ListValue<>("[style.colourExcellent(Reduces)] base cost to [style.boldMana(40)] aura"))),
	
	
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
			"The summoned elemental imbues all allies with the knowledge of how best to harness arcane fire.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourExcellent(All allies gain)] +20 [style.boldFire(Fire Damage)]"))),
	ELEMENTAL_FIRE_2(SpellSchool.FIRE,
			"elemental_fire_burning_desire",
			"Burning Desire",
			"The Fire elemental is able to harness the lustful properties of the arcane in order to ignite a burning desire for sex in the hearts and minds of their enemies.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourTerrible(All enemies suffer)] -25 [style.boldLust("+Attribute.RESISTANCE_LUST.getName()+")]"))),
	ELEMENTAL_FIRE_3A(SpellSchool.FIRE,
			"elemental_fire_servant_of_fire",
			"Servant of Fire",
			"The summoner swears to be subservient to the school of Fire, and while their elemental is bound to this form, the elemental is able to draw as much energy from the summoner as they wish.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("While summoned:"),
					new ListValue<>("[style.colourFire(Elemental)]: +100% [style.colourExcellent(Non-Seduction Damage)]"),
					new ListValue<>("[style.colourArcane(Caster)]: -50% [style.colourHealth(maximum energy)]"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_FIRE_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Mutually exclusive with 'Binding of Fire'!)]";
			} else {
				return "[style.boldBad(Mutually exclusive with 'Binding of Fire'!)]";
			}
		}
	},
	ELEMENTAL_FIRE_3B(SpellSchool.FIRE,
			"elemental_fire_binding_of_fire",
			"Binding of Fire",
			"The summoner assumes complete dominance over the school of Fire, and while their elemental is bound to this form, they are forced to share all of their secrets.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("While summoned:"),
					new ListValue<>("[style.colourArcane(Caster)]: +25 [style.boldFire(Fire Damage)]"),
					new ListValue<>("[style.colourArcane(Caster)]: +25 [style.boldFire(Fire Resistance)]"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_FIRE_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Mutually exclusive with 'Servant of Fire'!)]";
			} else {
				return "[style.boldBad(Mutually exclusive with 'Servant of Fire'!)]";
			}
		}
	},
	
	// Water:

	ICE_SHARD_1(true,
			SpellSchool.WATER,
			"ice_shard_freezing_fog",
			"Freezing Fog",
			"Ice Shard explodes on impact, saturating the air around the target with arcane crystals. These crystals then quickly condense into a freezing fog, which saps the target's ability to cast spells.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<b>-20</b> "+Attribute.SPELL_COST_MODIFIER.getColouredName("b")+" for [style.colourGood(3 turns)]"))),
	ICE_SHARD_2(SpellSchool.WATER,
			"ice_shard_cold_snap",
			"Cold Snap",
			"As Ice Shard travels through the freezing layer of fog left behind by a previous impact, there's a chance that the crystals in the air will explode, dealing extra damage.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Ice Shard has +25 "+Attribute.CRITICAL_CHANCE.getColouredName("b")+" against targets affected by Freezing Fog"))),
	ICE_SHARD_3(SpellSchool.WATER,
			"ice_shard_deep_freeze",
			"Deep Freeze",
			"When the crystals in freezing fog detonate, they instantly entomb any objects nearby in a case of thin ice, momentarily locking them in place.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("If Ice Shard [style.boldExcellent(critically hits)] a target affected by Freezing Fog, that target is [style.colourExcellent(stunned)] for [style.colourGood(1 turn)]"))),

	RAIN_CLOUD_1(true,
			SpellSchool.WATER,
			"rain_cloud_deep_chill",
			"Deep Chill",
			"The arcane rain seeps into the target's bones, chilling them to the core.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Adds <b>-25</b> "+Attribute.RESISTANCE_ICE.getColouredName("b")+" to Rain Cloud's effects"))),
	RAIN_CLOUD_2(SpellSchool.WATER,
			"rain_cloud_downpour",
			"Downpour",
			"Sheets of torrential arcane rain sweep into the target's eyes, causing them to miss the occasional attack.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Adds <b>+15</b> "+Attribute.MISS_CHANCE.getColouredName("b")+" to Rain Cloud's effects"))),
	RAIN_CLOUD_3(SpellSchool.WATER,
			"rain_cloud_cloud_burst",
			"Cloud Burst",
			"The anger and annoyance of the rain cloud's target is harnessed as energy, and each time they miss an attack, the cloud grows in strength and size.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("If Rain Cloud's target misses, the duration is set to [style.colourGood(6 turns)], and "+Attribute.SPELL_COST_MODIFIER.getColouredName("b")+" debuff is increased to -50"))),

	SOOTHING_WATERS_1(true,
			SpellSchool.WATER,
			"soothing_waters_arcane_springs",
			"Arcane Springs",
			"The arcane power infused into Soothing Waters is massively increased, allowing the spell to additionally restore a target's aura.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Soothing Waters [style.boldExcellent(additionally)] restores <b>20%</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")))),
	SOOTHING_WATERS_2(SpellSchool.WATER,
			"soothing_waters_rejuvenation",
			"Rejuvenation",
			"The life-giving properties of water are fully harnessed by the arcane, restoring a huge amount of energy to the target of Soothing Water.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Soothing Waters' restoration [style.boldExcellent(increased)] to <b>40%</b> "+Attribute.HEALTH_MAXIMUM.getColouredName("b")))),
	SOOTHING_WATERS_3(SpellSchool.WATER,
			"soothing_waters_bouncing_orb",
			"Bouncing Orb",
			"Once cast, Soothing Waters now splits into several orbs, each one seeking out an ally to heal.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Soothing Waters heals [style.boldExcellent(all allies)] for <b>10%</b> "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and <b>10%</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")))),

	ELEMENTAL_WATER_1(true,
			SpellSchool.WATER,
			"elemental_water_crashing_waves",
			"Crashing Waves",
			"The Water elemental continuously sends forth waves of freezing water to crash upon their enemies, making them far more susceptible to ice attacks.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourExcellent(All allies gain)] +20 [style.boldIce(Ice Damage)]"))),
	ELEMENTAL_WATER_2(SpellSchool.WATER,
			"elemental_water_calm_waters",
			"Calm Waters",
			"The Water elemental projects the image of calm, steady waters into the mind of any ally who starts to get turned on, helping them to control ther lust.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourExcellent(All allies gain)] +20 "+Attribute.RESISTANCE_LUST.getColouredName("b")))),
	ELEMENTAL_WATER_3A(SpellSchool.WATER,
			"elemental_water_servant_of_water",
			"Servant of Water",
			"The summoner swears to be subservient to the school of Water, and while their elemental is bound to this form, the elemental is able to draw as much energy from the summoner as they wish.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("While summoned:"),
					new ListValue<>("[style.colourIce(Elemental)]: +100% [style.colourExcellent(Non-Seduction Damage)]"),
					new ListValue<>("[style.colourArcane(Caster)]: -50% [style.colourHealth(maximum energy)]"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_WATER_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Mutually exclusive with 'Binding of Water'!)]";
			} else {
				return "[style.boldBad(Mutually exclusive with 'Binding of Water'!)]";
			}
		}
	},
	ELEMENTAL_WATER_3B(SpellSchool.WATER,
			"elemental_water_binding_of_water",
			"Binding of Water",
			"The summoner assumes complete dominance over the school of Water, and while their elemental is bound to this form, they are forced to share all of their secrets.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("While summoned:"),
					new ListValue<>("[style.colourArcane(Caster)]: +25 [style.boldIce(Ice Damage)]"),
					new ListValue<>("[style.colourArcane(Caster)]: +25 [style.boldIce(Ice Resistance)]"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_WATER_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Mutually exclusive with 'Servant of Water'!)]";
			} else {
				return "[style.boldBad(Mutually exclusive with 'Servant of Water'!)]";
			}
		}
	},
	
	// Air:

	POISON_VAPOURS_1(true,
			SpellSchool.AIR,
			"poison_vapours_choking_haze",
			"Choking Haze",
			"The clouds of Poison Vapours become far thicker, becoming a stifling, choking haze that causes the target to occasisoanlly miss their attacks.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Poison Vapours additionally applies <b>+10</b> "+Attribute.MISS_CHANCE.getColouredName("b")))),
	POISON_VAPOURS_2(SpellSchool.AIR,
			"poison_vapours_arcane_sickness",
			"Arcane Sickness",
			"Poison Vapours become infused with a potent arcane sickness, which steadily drains the target's arcane aura.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Poison Vapours additionally drains <b>10</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+" per turn"))),
	POISON_VAPOURS_3(SpellSchool.AIR,
			"poison_vapours_weakening_cloud",
			"Weakening Cloud",
			"Poison Vapours starts to seep into the target's body, causing them to suffer a reduction in damage done.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Poison Vapours additionally applies <b>-15</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")),
					new ListValue<>("Poison Vapours additionally applies <b>-25</b> "+Attribute.CRITICAL_DAMAGE.getColouredName("b")))),

	VACUUM_1(true,
			SpellSchool.AIR,
			"vacuum_secondary_voids",
			"Secondary Voids",
			"The target of Vacuum additionally suffers -15 critical chance, and miss chance is increased to +20.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Vacuum additionally applies <b>-15</b> "+Attribute.CRITICAL_CHANCE.getColouredName("b")),
					new ListValue<>("Vacuum debuff increased to <b>+20</b> "+Attribute.MISS_CHANCE.getColouredName("b")))),
	VACUUM_2(SpellSchool.AIR,
			"vacuum_suction",
			"Suction",
			"There is now a 10% chance each turn for the target of Vacuum to have a random outer layer of their clothing sucked off to the floor.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<b>10%</b> chance per turn of [style.boldExcellent(stripping)] clothing"))),
	VACUUM_3(SpellSchool.AIR,
			"vacuum_total_void",
			"Total Void",
			"The strip chance from Suction is increased to 25%.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Suction's strip chance [style.boldExcellent(increased)] to <b>25%</b>"))),

	PROTECTIVE_GUSTS_1(true,
			SpellSchool.AIR,
			"protective_gusts_guiding_wind",
			"Guiding Wind",
			"Guided by the forces of the arcane, the summoned winds apply pressure to their target at key moments, helping them to avoid incoming attacks, as well as to land powerful strikes of their own.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Protective Gusts buff increased to <b>+15</b> "+Attribute.DODGE_CHANCE.getColouredName("b")),
					new ListValue<>("Protective Gusts additionally applies <b>+10</b> "+Attribute.CRITICAL_CHANCE.getColouredName("b")))),
	PROTECTIVE_GUSTS_2(SpellSchool.AIR,
			"protective_gusts_focused_blast",
			"Focused Blast",
			"With every strike, the arcane winds push forwards to help their target deal the most amount of damage possible.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Protective Gusts buff increased to <b>+20</b> "+Attribute.DODGE_CHANCE.getColouredName("b")),
					new ListValue<>("Protective Gusts additionally applies <b>+25</b> "+Attribute.CRITICAL_DAMAGE.getColouredName("b")))),
	PROTECTIVE_GUSTS_3(SpellSchool.AIR,
			"protective_gusts_lingering_presence",
			"Lingering Presence",
			"By conserving their energy for those times when most needed, the Protective Gusts are able to assist their target for a longer period of time.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Protective Gusts now lasts for [style.boldGood(5 turns)]"))),

	ELEMENTAL_AIR_1(true,
			SpellSchool.AIR,
			"elemental_air_whirlwind",
			"Whirlwind",
			"The Air elemental summons forth a swirling whirlwind, which disrupts and staggers all enemies.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourTerrible(All enemies suffer)] +5 "+Attribute.MISS_CHANCE.getColouredName("b")))),
	ELEMENTAL_AIR_2(SpellSchool.AIR,
			"elemental_air_vitalising_scents",
			"Vitalising Scents",
			"The Air elemental surrounds their allies with vitalising scents, imbuing them with the energy needed to dodge incoming attacks, as well as to land powerful strikes of their own.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourExcellent(All allies gain)] +10 "+Attribute.CRITICAL_CHANCE.getColouredName("b")),
					new ListValue<>("[style.colourExcellent(All allies gain)] +5 "+Attribute.DODGE_CHANCE.getColouredName("b")))),
	ELEMENTAL_AIR_3A(SpellSchool.AIR,
			"elemental_air_servant_of_air",
			"Servant of Air",
			"The summoner swears to be subservient to the school of Water, and while their elemental is bound to this form, the elemental is able to draw as much energy from the summoner as they wish.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("While summoned:"),
					new ListValue<>("[style.colourAir(Elemental)]: +100% [style.colourExcellent(Non-Seduction Damage)]"),
					new ListValue<>("[style.colourArcane(Caster)]: -50% [style.colourHealth(maximum energy)]"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_AIR_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Mutually exclusive with 'Binding of Water'!)]";
			} else {
				return "[style.boldBad(Mutually exclusive with 'Binding of Water'!)]";
			}
		}
	},
	ELEMENTAL_AIR_3B(SpellSchool.AIR,
			"elemental_air_binding_of_air",
			"Binding of Air",
			"The summoner assumes complete dominance over the school of Water, and while their elemental is bound to this form, they are forced to share all of their secrets.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("While summoned:"),
					new ListValue<>("[style.colourArcane(Caster)]: +25 "+Attribute.DAMAGE_POISON.getColouredName("b")),
					new ListValue<>("[style.colourArcane(Caster)]: +25 "+Attribute.RESISTANCE_POISON.getColouredName("b")))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_AIR_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Mutually exclusive with 'Servant of Water'!)]";
			} else {
				return "[style.boldBad(Mutually exclusive with 'Servant of Water'!)]";
			}
		}
	},

	// Earth:

	SLAM_1(true,
			SpellSchool.EARTH,
			"slam_ground_shake",
			"Ground Shake",
			"Slam continues on down into the earth after doing its damage, causing the ground beneath the target's feet to shake.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<b>+10</b> "+Attribute.MISS_CHANCE.getColouredName("b")+" for [style.colourGood(2 turns)]"))),
	SLAM_2(SpellSchool.EARTH,
			"slam_aftershock",
			"Aftershock",
			"Just as the tremors start to die away, a huge surge of force rises up out of the ground to hit the target.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Applies <b>5</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" as Ground Shake ends"))),
	SLAM_3(SpellSchool.EARTH,
			"slam_earthquake",
			"Earthquake",
			"Slam impacts the ground so hard that all enemies suffer the effects of Ground Shake.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourExcellent(All enemies)] are affected by Ground Shake"))),

	TELEKENETIC_SHOWER_1(true,
			SpellSchool.EARTH,
			"telekenetic_shower_mind_over_matter",
			"Mind Over Matter",
			"By using their telekenetic force to pick up and recycle already-hurled objects, the caster can keep the Telekenetic Shower going for a considerable amount of time.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Telekenetic Shower now lasts for [style.boldGood(6 turns)]"))),
	TELEKENETIC_SHOWER_2(SpellSchool.EARTH,
			"telekenetic_shower_precision_strikes",
			"Precision Strikes",
			"Each strike from Telekenetic Shower is precisely aimed to bypass and degrade the target's physical defences.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Telekenetic Shower additionally applies <b>-20</b> "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b")))),
	TELEKENETIC_SHOWER_3(SpellSchool.EARTH,
			"telekenetic_shower_unseen_force",
			"Unseen Force",
			"Every time an object from Telekenetic Shower impacts the target, an explosive wave of force is unleashed, dealing considerable damage.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Telekenetic Shower damage [style.colourExcellent(doubled)] to <b>20</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" per turn"))),

	STONE_SHELL_1(true,
			SpellSchool.EARTH,
			"stone_shell_shifting_sands",
			"Shifting Sands",
			"The Stone Shell now occasionally shifts and disintegrates into flowing sand, before quickly reforming elsewhere in order to confuse any enemies.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Stone Shell additionally applies <b>+10</b> "+Attribute.DODGE_CHANCE.getColouredName("b")))),
	STONE_SHELL_2(SpellSchool.EARTH,
			"stone_shell_hardened_carapace",
			"Hardened Carapace",
			"A second layer of hardened stone is created behind Shone Shell, massively increasing the target's physical resistance.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Stone Shell's buff is increased to <b>+50</b> "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b")))),
	STONE_SHELL_3(SpellSchool.EARTH,
			"stone_shell_explosive_finish",
			"Explosive Finish",
			"A reserve of telekenetic energy is stored within the Stone Shell, and when the effect finally comes to an end, this energy is released in an explosive burst.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourExcellent(All enemies)] take <b>10</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" when Stone Shell ends"))),

	ELEMENTAL_EARTH_1(true,
			SpellSchool.EARTH,
			"elemental_earth_rolling_stone",
			"Rolling Stone",
			"The Earth elemental's powers are used to empower all allies attacks.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourExcellent(All allies gain)] +15 "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")),
					new ListValue<>("[style.colourExcellent(All allies gain)] +25 "+Attribute.CRITICAL_DAMAGE.getColouredName("b")))),
	ELEMENTAL_EARTH_2(SpellSchool.EARTH,
			"elemental_earth_hardening",
			"Hardening",
			"The Earth elemental's telekenetic powers are used to surround all allies with protective fragments of rock.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourExcellent(All allies gain)] +10 "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b")))),
	ELEMENTAL_EARTH_3A(SpellSchool.EARTH,
			"elemental_earth_servant_of_earth",
			"Servant of Earth",
			"The summoner swears to be subservient to the school of Earth, and while their elemental is bound to this form, the elemental is able to draw as much energy from the summoner as they wish.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("While summoned:"),
					new ListValue<>("[style.colourEarth(Elemental)]: +100% [style.colourExcellent(Non-Seduction Damage)]"),
					new ListValue<>("[style.colourArcane(Caster)]: -50% [style.colourHealth(maximum energy)]"))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_EARTH_3B);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Mutually exclusive with 'Binding of Earth'!)]";
			} else {
				return "[style.boldBad(Mutually exclusive with 'Binding of Earth'!)]";
			}
		}
	},
	ELEMENTAL_EARTH_3B(SpellSchool.EARTH,
			"elemental_earth_binding_of_earth",
			"Binding of Earth",
			"The summoner assumes complete dominance over the school of Earth, and while their elemental is bound to this form, they are forced to share all of their secrets.",
			null,
			Util.newArrayListOfValues(
					new ListValue<>("While summoned:"),
					new ListValue<>("[style.colourArcane(Caster)]: +25 "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")),
					new ListValue<>("[style.colourArcane(Caster)]: +25 "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b")))) {

		public boolean isAvailable(GameCharacter caster) {
			return !caster.hasSpellUpgrade(ELEMENTAL_EARTH_3A);
		}
		
		public String getUnavailableReason(GameCharacter caster) {
			if(this.isAvailable(caster) && !caster.hasSpellUpgrade(this)) {
				return "[style.boldMinorBad(Mutually exclusive with 'Servant of Earth'!)]";
			} else {
				return "[style.boldBad(Mutually exclusive with 'Servant of Earth'!)]";
			}
		}
	},

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
	
	public boolean isAvailable(GameCharacter caster) {
		return true;
	}
	
	public String getUnavailableReason(GameCharacter caster) {
		return "";
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
