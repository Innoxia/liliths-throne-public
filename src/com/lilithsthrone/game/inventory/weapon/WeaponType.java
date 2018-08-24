package com.lilithsthrone.game.inventory.weapon;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.DamageVariance;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.84
 * @version 0.2.11
 * @author Innoxia
 */
public class WeaponType {
	
	public static AbstractWeaponType MELEE_CHAOS_RARE = new AbstractWeaponType(1000,
			true,
			false,
			"it",
			false,
			"opaque demonstone",
			"opaque demonstones",
			"Crystal Strike",
			"A common type of demonstone, the power of which can be harnessed as a weapon."
					+ " Demonstones are rumoured to be crystallised essences of a Lilin's orgasm.",
			"meleeCrystal1",
			"meleeCrystal1",
			Rarity.RARE,
			Util.newArrayListOfValues(
					DamageType.PHYSICAL,
					DamageType.FIRE,
					DamageType.ICE,
					DamageType.POISON),
			8,
			0,
			DamageVariance.MEDIUM,
			5,
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_WEAPON, TFPotency.MAJOR_BOOST, 0)),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_VICKY)) {
				
		@Override
		public String equipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(focus)] on the energy in the crystal."
					+ " As [npc.she] [npc.does] so, it dissolves and flows into [npc.her] body, granting [npc.herHim] the ability to perform magical attacks.");
		}

		@Override
		public String unequipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(focus)] on the energy inside of [npc.herHim], forcing it out from [npc.her] body."
					+ " As [npc.she] [npc.does] so, it reforms back into a demonstone.");
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return genericMeleeAttackDescription(character, target, isHit);
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Strike at [npc.name] in melee, using your crystal's power to inflict extra damage!");
		}
		
		@Override
		public List<Spell> getGenerationSpells(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newArrayListOfValues(Spell.FIREBALL);
				case ICE:
					return Util.newArrayListOfValues(Spell.ICE_SHARD);
				case LUST:
					return Util.newArrayListOfValues(Spell.ARCANE_AROUSAL);
				case MISC:
					break;
				case PHYSICAL:
					return Util.newArrayListOfValues(Spell.SLAM);
				case POISON:
					return Util.newArrayListOfValues(Spell.POISON_VAPOURS);
			}
			return null;
		}
	};
	
	public static AbstractWeaponType MELEE_CHAOS_EPIC = new AbstractWeaponType(1500,
			true,
			false,
			"it",
			false,
			"misty demonstone",
			"misty demonstones",
			"Crystal Strike",
			"A powerful demonstone, the power of which can be harnessed as a weapon."
					+ " Demonstones are rumoured to be crystallised essences of a Lilin's orgasm.",
			"meleeCrystal2",
			"meleeCrystal2",
			Rarity.EPIC,
			Util.newArrayListOfValues(
					DamageType.PHYSICAL,
					DamageType.FIRE,
					DamageType.ICE,
					DamageType.POISON),
			12,
			0,
			DamageVariance.MEDIUM,
			5,
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_WEAPON, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_WEAPON, TFPotency.MAJOR_BOOST, 0)),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_VICKY)) {

		@Override
		public String equipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(focus)] on the energy in the crystal."
					+ " As [npc.she] [npc.does] so, it dissolves and flows into [npc.her] body, granting [npc.herHim] the ability to perform magical attacks.");
		}

		@Override
		public String unequipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(focus)] on the energy inside of [npc.herHim], forcing it out from [npc.her] body."
					+ " As [npc.she] [npc.does] so, it reforms back into a demonstone.");
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return genericMeleeAttackDescription(character, target, isHit);
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Strike at [npc.name] in melee, using your crystal's power to inflict extra damage!");
		}
		
		@Override
		public List<Spell> getGenerationSpells(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newArrayListOfValues(Spell.FIREBALL);
				case ICE:
					return Util.newArrayListOfValues(Spell.ICE_SHARD);
				case LUST:
					return Util.newArrayListOfValues(Spell.ARCANE_AROUSAL);
				case MISC:
					break;
				case PHYSICAL:
					return Util.newArrayListOfValues(Spell.SLAM);
				case POISON:
					return Util.newArrayListOfValues(Spell.POISON_VAPOURS);
			}
			return null;
		}
	};
	
	public static AbstractWeaponType MELEE_CHAOS_LEGENDARY = new AbstractWeaponType(2500,
			true,
			false,
			"it",
			false,
			"clear demonstone",
			"clear demonstones",
			"Crystal Strike",
			"An extremely powerful demonstone, the power of which can be harnessed as a weapon."
					+ " Demonstones are rumoured to be crystallised essences of a Lilin's orgasm.",
			"meleeCrystal3",
			"meleeCrystal3",
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(
					DamageType.PHYSICAL,
					DamageType.FIRE,
					DamageType.ICE,
					DamageType.POISON),
			16,
			0,
			DamageVariance.LOW,
			10,
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_WEAPON, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_WEAPON, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_WEAPON, TFPotency.MAJOR_BOOST, 0)),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_VICKY)){

		@Override
		public String equipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(focus)] on the energy in the crystal."
					+ " As [npc.she] [npc.does] so, it dissolves and flows into [npc.her] body, granting [npc.herHim] the ability to perform magical attacks.");
		}

		@Override
		public String unequipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(focus)] on the energy inside of [npc.herHim], forcing it out from [npc.her] body."
					+ " As [npc.she] [npc.does] so, it reforms back into a demonstone.");
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return genericMeleeAttackDescription(character, target, isHit);
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Strike at [npc.name] in melee, using your crystal's power to inflict extra damage!");
		}

		@Override
		public List<Spell> getGenerationSpells(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newArrayListOfValues(Spell.FIREBALL);
				case ICE:
					return Util.newArrayListOfValues(Spell.ICE_SHARD);
				case LUST:
					return Util.newArrayListOfValues(Spell.ARCANE_AROUSAL);
				case MISC:
					break;
				case PHYSICAL:
					return Util.newArrayListOfValues(Spell.SLAM);
				case POISON:
					return Util.newArrayListOfValues(Spell.POISON_VAPOURS);
			}
			return null;
		}
	};
	
	public static AbstractWeaponType MELEE_ZWEIHANDER = new AbstractWeaponType(5000,
			true,
			true,
			"it",
			false,
			"Zweih&auml;nder",
			"Zweih&auml;nders",
			"Slash",
			"As its name of 'Zweih&auml;nder' (two-hander) suggests, this sword is so large that it requires two hands to wield correctly."
					+ " The blade is made out of ethereal arcane energy, which, instead of cutting someone, drains their energy as it passes through them.",
			"zweihander",
			"zweihander",
			Rarity.EPIC,
			Util.newArrayListOfValues(
					DamageType.PHYSICAL,
					DamageType.FIRE,
					DamageType.ICE,
					DamageType.POISON),
			20,
			0,
			DamageVariance.MEDIUM,
			5,
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_WEAPON, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MAJOR_BOOST, 0)),
			null,
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			Util.newArrayListOfValues(ItemTag.SOLD_BY_VICKY)) {

		@Override
		public String equipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(ready)] [npc.her] "+this.getName()+".");
		}

		@Override
		public String unequipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(put)] [npc.her] "+this.getName()+" away.");
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return getDescriptions(character, target, isHit,
					UtilText.returnStringAtRandom(
							"You slash your "+this.getName()+" at [npc.name], grinning as the ethereal arcane blade travels through [npc.her] body!",
							"Slashing out at [npc.name] with your "+this.getName()+", you grin as you see the ethereal blade travel straight through [npc.her] body!",
							"You slash at [npc.name] with your "+this.getName()+", causing [npc.herHim] to stagger back as the ethereal blade passes right through [npc.her] chest!"),
					UtilText.returnStringAtRandom(
							"[npc.Name] slashes [npc.her] "+this.getName()+" at you, grinning as the ethereal arcane blade travels through your body!",
							"Slashing out at you with [npc.her] "+this.getName()+", [npc.name] grins as [npc.she] sees the ethereal blade travel straight through your body!",
							"[npc.Name] slashes at you with [npc.her] "+this.getName()+", causing you to stagger back as the ethereal blade passes right through your chest!"),
					UtilText.returnStringAtRandom(
							"[npc.Name] slashes [npc.her] "+this.getName()+" at [npc2.name], grinning as the ethereal arcane blade travels through [npc2.her] body!",
							"Slashing out at [npc2.name] with [npc.her] "+this.getName()+", [npc.name] grins as [npc.she] sees the ethereal blade travel straight through [npc2.her] body!",
							"[npc.Name] slashes at [npc2.name] with [npc.her] "+this.getName()+", causing [npc2.herHim] to stagger back as the ethereal blade passes right through [npc2.her] chest!"),
					UtilText.returnStringAtRandom(
							"You slash your "+this.getName()+" at [npc.name], but the blade sails harmlessly through mid-air as you miss your target!",
							"Slashing out at [npc.name] with your "+this.getName()+", you sigh in frustration as you end up missing your target!",
							"You slash at [npc.name] with your "+this.getName()+", but fail to land a hit!"),
					UtilText.returnStringAtRandom(
							"[npc.Name] slashes [npc.her] "+this.getName()+" at you, but the blade sails harmlessly through mid-air as [npc.she] misses you!",
							"Slashing out at you with [npc.her] "+this.getName()+", [npc.name] sighs in frustration as [npc.she] ends up missing you!",
							"[npc.Name] slashes at you with [npc.her] "+this.getName()+", but fails to land a hit!"),
					UtilText.returnStringAtRandom(
							"[npc.Name] slashes [npc.her] "+this.getName()+" at [npc2.name], but the blade sails harmlessly through mid-air as [npc.she] misses [npc2.herHim]!",
							"Slashing out at [npc2.name] with [npc.her] "+this.getName()+", [npc.name] sighs in frustration as [npc.she] ends up missing [npc2.herHim]!",
							"[npc.Name] slashes at [npc2.name] with [npc.her] "+this.getName()+", but fails to land a hit!"));
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Strike at [npc.name] with your zweihander!");
		}
	};
	
	public static AbstractWeaponType MELEE_KNIGHTLY_SWORD = new AbstractWeaponType(2500,
			true,
			false,
			"it",
			false,
			"Knightly Sword",
			"Knightly Swords",
			"Slash",
			"This straight-edged sword has a single-handed cruciform hilt."
					+ " The 75cm-long blade is made out of ethereal arcane energy, which, instead of cutting someone, drains their energy as it passes through them.",
			"knightlySword",
			"knightlySword",
			Rarity.EPIC,
			Util.newArrayListOfValues(
					DamageType.PHYSICAL,
					DamageType.FIRE,
					DamageType.ICE,
					DamageType.POISON),
			18,
			0,
			DamageVariance.LOW,
			5,
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_WEAPON, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_CHANCE, TFPotency.MAJOR_BOOST, 0)),
			null,
			ColourListPresets.JUST_BLACK.getPresetColourList(),
			ColourListPresets.ALL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			Util.newArrayListOfValues(ItemTag.SOLD_BY_VICKY)) {

		@Override
		public String equipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(ready)] [npc.her] "+this.getName()+".");
		}

		@Override
		public String unequipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(put)] [npc.her] "+this.getName()+" away.");
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return getDescriptions(character, target, isHit,
					UtilText.returnStringAtRandom(
							"You slash your "+this.getName()+" at [npc.name], grinning as the ethereal arcane blade travels through [npc.her] body!",
							"Slashing out at [npc.name] with your "+this.getName()+", you grin as you see the ethereal blade travel straight through [npc.her] body!",
							"You slash at [npc.name] with your "+this.getName()+", causing [npc.herHim] to stagger back as the ethereal blade passes right through [npc.her] chest!"),
					UtilText.returnStringAtRandom(
							"[npc.Name] slashes [npc.her] "+this.getName()+" at you, grinning as the ethereal arcane blade travels through your body!",
							"Slashing out at you with [npc.her] "+this.getName()+", [npc.name] grins as [npc.she] sees the ethereal blade travel straight through your body!",
							"[npc.Name] slashes at you with [npc.her] "+this.getName()+", causing you to stagger back as the ethereal blade passes right through your chest!"),
					UtilText.returnStringAtRandom(
							"[npc.Name] slashes [npc.her] "+this.getName()+" at [npc2.name], grinning as the ethereal arcane blade travels through [npc2.her] body!",
							"Slashing out at [npc2.name] with [npc.her] "+this.getName()+", [npc.name] grins as [npc.she] sees the ethereal blade travel straight through [npc2.her] body!",
							"[npc.Name] slashes at [npc2.name] with [npc.her] "+this.getName()+", causing [npc2.herHim] to stagger back as the ethereal blade passes right through [npc2.her] chest!"),
					UtilText.returnStringAtRandom(
							"You slash your "+this.getName()+" at [npc.name], but the blade sails harmlessly through mid-air as you miss your target!",
							"Slashing out at [npc.name] with your "+this.getName()+", you sigh in frustration as you end up missing your target!",
							"You slash at [npc.name] with your "+this.getName()+", but fail to land a hit!"),
					UtilText.returnStringAtRandom(
							"[npc.Name] slashes [npc.her] "+this.getName()+" at you, but the blade sails harmlessly through mid-air as [npc.she] misses you!",
							"Slashing out at you with [npc.her] "+this.getName()+", [npc.name] sighs in frustration as [npc.she] ends up missing you!",
							"[npc.Name] slashes at you with [npc.her] "+this.getName()+", but fails to land a hit!"),
					UtilText.returnStringAtRandom(
							"[npc.Name] slashes [npc.her] "+this.getName()+" at [npc2.name], but the blade sails harmlessly through mid-air as [npc.she] misses [npc2.herHim]!",
							"Slashing out at [npc2.name] with [npc.her] "+this.getName()+", [npc.name] sighs in frustration as [npc.she] ends up missing [npc2.herHim]!",
							"[npc.Name] slashes at [npc2.name] with [npc.her] "+this.getName()+", but fails to land a hit!"));
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Strike at [npc.name] with your "+this.getName()+"!");
		}
	};
	
	public static AbstractWeaponType OFFHAND_BUCKLER = new AbstractWeaponType(1000,
			true,
			false,
			"it",
			false,
			"Buckler",
			"Bucklers",
			"Bash",
			"A small metal shield, measuring 45cm in diameter, and gripped in one hand by means of a handle positioned behind the boss."
					+ " Shields such as this one are typically enchanted to help the wielder resist a certain type of arcane damage.",
			"buckler",
			"buckler",
			Rarity.EPIC,
			Util.newArrayListOfValues(
					DamageType.FIRE,
					DamageType.ICE,
					DamageType.POISON),
			6,
			0,
			DamageVariance.HIGH,
			5,
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_WEAPON, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_WEAPON, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_PHYSICAL, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_PHYSICAL, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_PHYSICAL, TFPotency.MAJOR_BOOST, 0)),
			null,
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			ColourListPresets.JUST_STEEL.getPresetColourList(),
			ColourListPresets.ALL_METAL.getPresetColourList(),
			Util.newArrayListOfValues(ItemTag.SOLD_BY_VICKY)) {

		@Override
		public String equipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(ready)] [npc.her] "+this.getName()+".");
		}

		@Override
		public String unequipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(put)] [npc.her] "+this.getName()+" away.");
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return getDescriptions(character, target, isHit,
					UtilText.returnStringAtRandom(
							"You bash your "+this.getName()+" at [npc.name], grinning as you end up whacking [npc.herHim] right on the [npc.arm]!",
							"Striking out at [npc.name] with your "+this.getName()+", you grin as you bash [npc.herHim] right in the chest!",
							"You strike at [npc.name] with your "+this.getName()+", causing [npc.herHim] to stagger back as you bash [npc.her] torso!"),
					UtilText.returnStringAtRandom(
							"[npc.Name] bashes [npc.her] "+this.getName()+" at you, grinning as [npc.she] whacks you right on the [pc.arm]!",
							"Striking out at you with [npc.her] "+this.getName()+", [npc.name] grins as [npc.she] bashes you right in the chest!",
							"[npc.Name] strikes at you with [npc.her] "+this.getName()+", causing you to stagger back [npc.she] bashes your torso!"),
					UtilText.returnStringAtRandom(
							"[npc.Name] bashes [npc.her] "+this.getName()+" at [npc2.name], grinning as [npc.she] whacks [npc2.herHim] right on the [npc2.arm]!",
							"Striking out at [npc2.name] with [npc.her] "+this.getName()+", [npc.name] grins as [npc.she] bashes [npc2.herHim] right in the chest!",
							"[npc.Name] strikes at [npc2.name] with [npc.her] "+this.getName()+", causing [npc2.herHim] to stagger back [npc.she] bashes [npc2.her] torso!"),
					UtilText.returnStringAtRandom(
							"You bash your "+this.getName()+" at [npc.name], but your strike sails harmlessly through mid-air as you miss your target!",
							"Striking out at [npc.name] with your "+this.getName()+", you sigh in frustration as you end up missing your target!",
							"You slash at [npc.name] with your "+this.getName()+", but fail to land a hit!"),
					UtilText.returnStringAtRandom(
							"[npc.Name] bashes [npc.her] "+this.getName()+" at you, but [npc.her] strike sails harmlessly through mid-air as [npc.she] misses you!",
							"Striking out at you with [npc.her] "+this.getName()+", [npc.name] sighs in frustration as [npc.she] ends up missing you!",
							"[npc.Name] strikes at you with [npc.her] "+this.getName()+", but fails to land a hit!"),
					UtilText.returnStringAtRandom(
							"[npc.Name] bashes [npc.her] "+this.getName()+" at [npc2.name], but [npc.her] strike sails harmlessly through mid-air as [npc.she] misses [npc2.herHim]!",
							"Striking out at [npc2.name] with [npc.her] "+this.getName()+", [npc.name] sighs in frustration as [npc.she] ends up missing [npc2.herHim]!",
							"[npc.Name] strikes at [npc2.name] with [npc.her] "+this.getName()+", but fails to land a hit!"));
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Strike at [npc.name] with your "+this.getName()+"!");
		}

	};

	// OFFHAND
	public static AbstractWeaponType OFFHAND_CHAOS_RARE = new AbstractWeaponType(1000,
			false,
			false,
			"it",
			false,
			"chaos feather",
			"chaos feathers",
			"Feather Bolt",
			"A magical feather, the power of which can be harnessed as a weapon."
					+ " Feathers like this are rumoured to have been plucked from a Lilin's wings.",
			"rangedFeather1",
			"rangedFeather1",
			Rarity.RARE,
			Util.newArrayListOfValues(DamageType.PHYSICAL, DamageType.FIRE, DamageType.ICE, DamageType.POISON),
			8,
			0,
			DamageVariance.HIGH,
			5,
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_WEAPON, TFPotency.MAJOR_BOOST, 0)),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_VICKY)) {

		@Override
		public String equipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(focus)] on the energy in the feather."
					+ " As [npc.she] [npc.does] so, it dissolves and flows into [npc.her] body, granting [npc.herHim] the ability to perform magical attacks at range.");
		}

		@Override
		public String unequipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(focus)] on the energy inside of [npc.herHim], forcing it out from [npc.her] body."
					+ " As [npc.she] [npc.does] so, it reforms back into a chaos feather.");
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return genericRangedAttackDescription(character, target, isHit);
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Use your feather's power to shoot a bolt of energy at [npc.name]!");
		}
		
		@Override
		public List<Spell> getGenerationSpells(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newArrayListOfValues(Spell.FIREBALL);
				case ICE:
					return Util.newArrayListOfValues(Spell.ICE_SHARD);
				case LUST:
					return Util.newArrayListOfValues(Spell.ARCANE_AROUSAL);
				case MISC:
					break;
				case PHYSICAL:
					return Util.newArrayListOfValues(Spell.SLAM);
				case POISON:
					return Util.newArrayListOfValues(Spell.POISON_VAPOURS);
			}
			return null;
		}
	};
	
	public static AbstractWeaponType OFFHAND_CHAOS_EPIC = new AbstractWeaponType(1500,
			false,
			false,
			"it",
			false,
			"chaos feather",
			"chaos feathers",
			"Feather Bolt",
			"A well-preserved magical feather, the power of which can be harnessed as a weapon."
					+ " Feathers like this are rumoured to have been plucked from a Lilin's wings.",
			"rangedFeather2",
			"rangedFeather2",
			Rarity.EPIC,
			Util.newArrayListOfValues(DamageType.PHYSICAL, DamageType.FIRE, DamageType.ICE, DamageType.POISON),
			14,
			0,
			DamageVariance.HIGH,
			5,
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_WEAPON, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.RESISTANCE_WEAPON, TFPotency.MAJOR_BOOST, 0)),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_VICKY)) {

		@Override
		public String equipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(focus)] on the energy in the feather."
					+ " As [npc.she] [npc.does] so, it dissolves and flows into [npc.her] body, granting [npc.herHim] the ability to perform magical attacks at range.");
		}

		@Override
		public String unequipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(focus)] on the energy inside of [npc.herHim], forcing it out from [npc.her] body."
					+ " As [npc.she] [npc.does] so, it reforms back into a chaos feather.");
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return genericRangedAttackDescription(character, target,isHit);
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Use your feather's power to shoot a bolt of energy at [npc.name]!");
		}
		
		@Override
		public List<Spell> getGenerationSpells(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newArrayListOfValues(Spell.FIREBALL);
				case ICE:
					return Util.newArrayListOfValues(Spell.ICE_SHARD);
				case LUST:
					return Util.newArrayListOfValues(Spell.ARCANE_AROUSAL);
				case MISC:
					break;
				case PHYSICAL:
					return Util.newArrayListOfValues(Spell.SLAM);
				case POISON:
					return Util.newArrayListOfValues(Spell.POISON_VAPOURS);
			}
			return null;
		}
	};
	
	// I made this in one of my lunch breaks x_x
	public static AbstractWeaponType MAIN_WESTERN_KKP = new AbstractWeaponType(25000,
			false,
			false,
			"it",
			false,
			"Western KKP",
			"Western KKPs",
			"Fire KKP",
			"A blowback-operated semi-automatic pistol, featuring an exposed hammer, a traditional double-action trigger mechanism, a single-column magazine, and a fixed barrel that also acts as the guide rod for the recoil spring.",
			"western_kkp",
			"western_kkp",
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(DamageType.PHYSICAL),
			100000,
			0,
			DamageVariance.LOW,
			0,
			null,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.REMOVE_FROM_DEBUG_SPAWNER)) {

		@Override
		public String equipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(ready)] [npc.her] pistol.");
		}

		@Override
		public String unequipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(holster)] [npc.her] pistol.");
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return UtilText.parse(character, target, "[npc.Name] just [npc.verb(shoot)] [npc2.name]... Thankfully, there seems to be some kind of arcane force preventing [npc.her] gun from actually killing [npc2.herHim]...");
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"The name's [pc.surname]... [pc.name] [pc.surname].");
		}
	};
	
	public static AbstractWeaponType RANGED_MUSKET = new AbstractWeaponType(15000,
			false,
			true,
			"it",
			false,
			"Arcane Musket",
			"Arcane Muskets",
			"Fire Musket",
			"Carried by Lyssieth's demonic guard, these smoothbore long guns fire bolts of arcane energy instead of bullets."
					+ " Each discharge drains one arcane essence from the user, meaning that only those who have the ability to absorb arcane essences can fire it.",
			"arcaneMusket",
			"arcaneMusket",
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(
					DamageType.PHYSICAL),
			25,
			1,
			DamageVariance.LOW,
			5,
			null,
			null,
			null,
			null,
			null,
			null,
			null) {
				
		@Override
		public String equipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(ready)] [npc.her] musket.");
		}

		@Override
		public String unequipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(put)] [npc.her] musket away.");
		}
		
		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			if(isHit) {
				if(character.isPlayer()) {
					return UtilText.parse(target,
							UtilText.returnStringAtRandom(
								"You point your arcane musket at [npc.name] and pull the trigger, unleashing a blast of arcane power that strikes [npc.herHim] right in the chest!",
								"Taking aim at [npc.name] with your musket, you pull the trigger, unleashing a powerful blast of arcane energy that slams into [npc.her] torso!",
								"You fire your musket at [npc.name], blasting forth a powerful jolt of arcane energy that strikes [npc.herHim] straight in the chest!"));
					
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"[npc.Name] points [npc.her] arcane musket at you and pulls the trigger, unleashing a blast of arcane power that strikes you right in the chest!",
								"Taking aim at you with [npc.her] musket, [npc.name] pulls the trigger, unleashing a powerful blast of arcane energy that slams into your torso!",
								"[npc.Name] fires [npc.her] musket at you, blasting forth a powerful jolt of arcane energy that strikes you straight in the chest!"));
				}
				
			} else {
				if(character.isPlayer()) {
					return UtilText.parse(target,
							UtilText.returnStringAtRandom(
								"You point you arcane musket at [npc.name] and pull the trigger, but, much to your dismay, the blast of arcane power misses its target!",
								"Taking aim at [npc.name] with your musket, you pull the trigger, but unfortunately the blast of arcane power that you unleash misses your target!",
								"You fire your musket at [npc.name], blasting forth a powerful jolt of arcane energy that unfortunately fails to hit your target!"));
					
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
									"[npc.Name] points [npc.her] arcane musket at you and pulls the trigger, but, much to [npc.her] dismay, the blast of arcane power misses you!",
									"Taking aim at you with [npc.her] musket, [npc.name] pulls the trigger, but thankfully the blast of arcane power that [npc.she] unleashes misses you!",
									"[npc.Name] fires [npc.her] musket at you, blasting forth a powerful jolt of arcane energy that thankfully fails to hit you!"));
				}
			}
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Point your musket at [npc.name] and fire!");
		}
	};
	
	public static AbstractWeaponType MAIN_WITCH_BROOM = new AbstractWeaponType(5000,
			true,
			false,
			"it",
			false,
			"Witch's Broom",
			"Witch's Brooms",
			"Broom Swipe",
			"An old-fashioned wooden broom, consisting of a long pole with a bundle of flexible twigs attached to one end."
					+ " The opposite end of the pole widens out a little, where there's a curious engraving of a pentagram etched into the wood.",
			"primary_witch_broom",
			"primary_witch_broom",
			Rarity.EPIC,
			Util.newArrayListOfValues(
					DamageType.PHYSICAL,
					DamageType.FIRE,
					DamageType.ICE,
					DamageType.POISON),
			5,
			0,
			DamageVariance.LOW,
			5,
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.INTELLIGENCE, TFPotency.MAJOR_BOOST, 0)),
			Util.newArrayListOfValues(
					Spell.WITCH_SEAL,
					Spell.WITCH_CHARM),
			null,
			null,
			null,
			null,
			null) {
		
		@Override
		public String equipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(ready)] [npc.her] broom.");
		}

		@Override
		public String unequipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(put)] [npc.her] broom away.");
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			if(isHit) {
				if(character.isPlayer()) {
					return UtilText.parse(target,
							UtilText.returnStringAtRandom(
								"You whack [npc.namePos] [npc.arm] with your broom.",
								"You swipe your broom at [npc.namePos] [npc.legs], and manage to hit [npc.herHim] in the shins.",
								"You swing your broom at [npc.name], grinning as the end makes contact with [npc.her] torso."));
					
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"[npc.Name] whacks your [pc.arm] with [npc.her] broom.",
								"[npc.Name] swipes [npc.her] broom at your [pc.legs], and manages to hit you in the shins.",
								"[npc.Name] swings [npc.her] broom at you, grinning as the end makes contact with your torso."));
				}
				
			} else {
				if(character.isPlayer()) {
					return UtilText.parse(target,
							UtilText.returnStringAtRandom(
								"You try to whack [npc.name] with your broom, but [npc.she] manages to grab the shaft and push you away.",
								"You attempt to swipe your broom at [npc.namePos] [npc.legs], but [npc.she] jumps back and manages to avoid the blow.",
								"You swing your broom at [npc.name], but [npc.she] manages to duck at the last moment and avoid the blow."));
					
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"[npc.Name] tries to whack you with [npc.her] broom, but you manage to grab the shaft and push [npc.herHim] away.",
								"[npc.Name] attempts to swipe [npc.her] broom at your [pc.legs], but you jump back and manage to avoid the blow.",
								"[npc.Name] swings [npc.her] broom at you, but you manage to duck at the last moment and avoid the blow."));
				}
			}
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Swipe the broom at [npc.name]!");
		}
	};
	
	public static AbstractWeaponType MAIN_FEATHER_DUSTER = new AbstractWeaponType(250,
			true,
			false,
			"it",
			false,
			"feather duster",
			"feather dusters",
			"Duster Tickle",
			"A short-handled feather duster, ideal for keeping a house clean, but not much use in combat...",
			"feather_duster",
			"feather_duster",
			Rarity.EPIC,
			Util.newArrayListOfValues(DamageType.PHYSICAL),
			2,
			0,
			DamageVariance.LOW,
			5,
			null,
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_VICKY)) {
		
		
		@Override

		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP) {
				return "A short-handled feather duster, ideal for keeping a house clean, but not much use in combat..."
						+ " [Ashley.speech(A feather duster: the epitome of romance, at least for those who don't know anything about their lover, other than that they're the person who keeps the house clean.)]";
//						+ " Surely, that's all that's going on with their lives, right?)]";
			} else {
				return "A short-handled feather duster, ideal for keeping a house clean, but not much use in combat...";
			}
		}
		
		@Override
		public String equipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(ready)] [npc.her] feather duster.");
		}

		@Override
		public String unequipText(GameCharacter character) {
			return UtilText.parse(character, "[npc.Name] [npc.verb(put)] [npc.her] feather duster away.");
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			if(isHit) {
				if(character.isPlayer()) {
					return UtilText.parse(target,
							UtilText.returnStringAtRandom(
								"You tickle [npc.namePos] [npc.arm] with your feather duster.",
								"You swipe your feather duster at [npc.namePos] [npc.legs], and manage to tickle [npc.her] shins.",
								"You swing your feather duster at [npc.name], grinning as you tickle [npc.her] torso."));
					
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"[npc.Name] tickles your [pc.arm] with [npc.her] feather duster.",
								"[npc.Name] swipes [npc.her] feather duster at your [pc.legs], and manages to tickle your shins.",
								"[npc.Name] swings [npc.her] feather duster at you, grinning as [npc.she] tickles your torso."));
				}
				
			} else {
				if(character.isPlayer()) {
					return UtilText.parse(target,
							UtilText.returnStringAtRandom(
								"You try to tickle [npc.name] with your feather duster, but [npc.she] manages to push you away.",
								"You attempt to swipe your feather duster at [npc.namePos] [npc.legs], but [npc.she] jumps back and manages to avoid the tickling.",
								"You swing your feather duster at [npc.name], but [npc.she] manages to duck at the last moment and avoid the tickling."));
					
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"[npc.Name] tries to tickle you with [npc.her] feather duster, but you manage to grab the shaft and push [npc.herHim] away.",
								"[npc.Name] attempts to swipe [npc.her] feather duster at your [pc.legs], but you jump back and manage to avoid the tickling.",
								"[npc.Name] swings [npc.her] feather duster at you, but you manage to duck at the last moment and avoid the tickling."));
				}
			}
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Tickle [npc.name] with your feather duster!");
		}
	};

	public static AbstractWeaponType OFFHAND_BOW_AND_ARROW = new AbstractWeaponType(2500,
			false,
			false,
			"it",
			false,
			"Arcane Short Bow",
			"Arcane Short Bows",
			"Fire Bow",
			"This short bow is infused with a potent arcane enchantment, which, once the string is drawn back, extracts an arcane essence out of the user's aura, before converting it into a bolt of energy to be fired at the target.",
			"bowandarrow",
			"bowandarrow",
			Rarity.EPIC,
			Util.newArrayListOfValues(
					DamageType.PHYSICAL,
					DamageType.FIRE,
					DamageType.ICE,
					DamageType.POISON),
			18,
			1,
			DamageVariance.LOW,
			5,
			Util.newArrayListOfValues(
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_WEAPON, TFPotency.MAJOR_BOOST, 0),
					new ItemEffect(ItemEffectType.WEAPON, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_CHANCE, TFPotency.MAJOR_BOOST, 0)),
			null,
			null,
			null,
			null,
			null,
			Util.newArrayListOfValues(ItemTag.SOLD_BY_VICKY)) {

		@Override
		public String equipText(GameCharacter character) {
			return "You ready your "+this.getName()+".";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You put your "+this.getName()+" away.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.returnStringAtRandom(
							"Pulling back the arcane bow's string, an essence is drawn out of [npc.namePos] arcane aura, before [npc.she] [npc.verb(let)] loose and fires it at [npc2.name].",
							"[npc.Name] pulls the bow's string back, grinning as an arcane essence is drawn out of [npc.her] arcane aura, before letting loose and firing the newly-formed bolt straight at [npc2.name]."));
			
			if(isHit) {
				sb.append(UtilText.returnStringAtRandom(
								" The ethereal arrow hits its mark, and passes right through [npc2.namePos] chest, causing [npc2.herHim] to gasp and stagger back from the blow.",
								" The arcane arrow sails straight through the centre of [npc2.namePos] chest, causing [npc2.herHim] to let out a shocked gasp as [npc2.she] staggers back."));
			} else {
				sb.append(UtilText.returnStringAtRandom(
								" The ethereal arrow sails harmlessly off to one side of its target, causing [npc.name] to let out a frustrated sigh.",
								" The arcane arrow sails harmlessly over [npc2.namePos] shoulder, causing [npc2.herHim] to let out a mocking laugh."));
			}
			return UtilText.parse(character, target,sb.toString());
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Shoot at [npc.name] with your "+this.getName()+"!");
		}
	};
	
	public static List<AbstractWeaponType> rareWeapons = new ArrayList<>();
	public static List<AbstractWeaponType> allweapons = new ArrayList<>();
	public static List<AbstractWeaponType> moddedWeapons = new ArrayList<>();
	
	public static Map<AbstractWeaponType, String> weaponToIdMap = new HashMap<>();
	public static Map<String, AbstractWeaponType> idToWeaponMap = new HashMap<>();

	static {
		
		// Load in modded clothing:
		moddedWeapons = new ArrayList<>();
		File dir = new File("res/mods");
		
		if (dir.exists() && dir.isDirectory()) {
			File[] modDirectoryListing = dir.listFiles();
			if (modDirectoryListing != null) {
				for (File modAuthorDirectory : modDirectoryListing) {
					File modAuthorClothingDirectory = new File(modAuthorDirectory.getAbsolutePath()+"/items/weapons");
					
					File[] clothingDirectoriesListing = modAuthorClothingDirectory.listFiles();
					if (clothingDirectoriesListing != null) {
						for (File clothingDirectory : clothingDirectoriesListing) {
							if (clothingDirectory.isDirectory()){
								File[] innerDirectoryListing = clothingDirectory.listFiles((path, filename) -> filename.endsWith(".xml"));
								if (innerDirectoryListing != null) {
									for (File innerChild : innerDirectoryListing) {
										try {
											AbstractWeaponType ct = new AbstractWeaponType(innerChild) {};
											moddedWeapons.add(ct);
											String id = modAuthorDirectory.getName()+"_"+innerChild.getParentFile().getName()+"_"+innerChild.getName().split("\\.")[0];
//													System.out.println(id);
											weaponToIdMap.put(ct, id);
											idToWeaponMap.put(id, ct);
										} catch(Exception ex) {
											System.err.println("Loading modded weapon failed at 'WeaponType' Code 1. File path: "+innerChild.getAbsolutePath());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		allweapons.addAll(moddedWeapons);
		
		
		// Add in external res clothing:
		
		dir = new File("res/weapons");
		
		if (dir.exists() && dir.isDirectory()) {
			File[] authorDirectoriesListing = dir.listFiles();
			if (authorDirectoriesListing != null) {
				for (File authorDirectory : authorDirectoriesListing) {
					if (authorDirectory.isDirectory()){
						for (File clothingDirectory : authorDirectory.listFiles()) {
							if (clothingDirectory.isDirectory()){
								File[] innerDirectoryListing = clothingDirectory.listFiles((path, filename) -> filename.endsWith(".xml"));
								if (innerDirectoryListing != null) {
									for (File innerChild : innerDirectoryListing) {
										try {
											AbstractWeaponType ct = new AbstractWeaponType(innerChild) {};
											allweapons.add(ct);
											String id = authorDirectory.getName()+"_"+innerChild.getParentFile().getName()+"_"+innerChild.getName().split("\\.")[0];
		//											System.out.println(id);
											weaponToIdMap.put(ct, id);
											idToWeaponMap.put(id, ct);
										} catch(Exception ex) {
											System.err.println("Loading modded weapon failed at 'WeaponType' Code 2. File path: "+innerChild.getAbsolutePath());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		Field[] fields = WeaponType.class.getFields();
		
		for(Field f : fields){
			
			if (AbstractWeaponType.class.isAssignableFrom(f.getType())) {
				
				AbstractWeaponType weapon;
				
				try {
					weapon = ((AbstractWeaponType) f.get(null));

					// I feel like this is stupid :thinking:
					weaponToIdMap.put(weapon, f.getName());
					idToWeaponMap.put(f.getName(), weapon);
					
					if(weapon != MAIN_WESTERN_KKP) {
						allweapons.add(weapon);
					
						if (weapon.getRarity() == Rarity.RARE) {
							rareWeapons.add(weapon);
						}
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
