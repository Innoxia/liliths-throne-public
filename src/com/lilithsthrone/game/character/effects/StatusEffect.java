package com.lilithsthrone.game.character.effects;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.AlcoholLevel;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.ReindeerOverseer;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Units.ValueType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.8.2
 * @author Innoxia
 */
public class StatusEffect {

	// Attribute-related status effects:
	// Strength:
	public static AbstractStatusEffect PHYSIQUE_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"sissy",
			"attStrength0",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -15f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f)),
			Util.newArrayListOfValues(
					"Base [style.colourUnarmed(unarmed damage)] equals [style.colourMinorGood(20% of physique)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.ZERO_WEAK.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] incredibly weak. [npc.She] [npc.verb(struggle)] to do much damage with [npc.her] wimpy little [npc.arms], and [npc.her] fragile body is particularly vulnerable to physical damage.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.ZERO_WEAK;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"average",
			"attStrength1",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			null,
			Util.newArrayListOfValues(
					"Base [style.colourUnarmed(unarmed damage)] equals [style.colourMinorGood(20% of physique)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.ONE_AVERAGE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameHasFull] an average level of physical fitness for [npc.her] body size.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.ONE_AVERAGE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"strong",
			"attStrength2",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 10f)),
			Util.newArrayListOfValues(
					"Base [style.colourUnarmed(unarmed damage)] equals [style.colourMinorGood(20% of physique)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.TWO_STRONG.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] stronger and fitter than [npc.her] body size would suggest, and [npc.is] able to inflict more physical damage as a result.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.TWO_STRONG;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"powerful",
			"attStrength3",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 20f)),
			Util.newArrayListOfValues(
					"Base [style.colourUnarmed(unarmed damage)] equals [style.colourMinorGood(20% of physique)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.THREE_POWERFUL.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] considerably stronger and fitter than [npc.her] body size would suggest, and [npc.is] able to inflict a significant amount of physical damage as a result.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.THREE_POWERFUL;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"mighty",
			"attStrength4",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 30f)),
			Util.newArrayListOfValues(
					"Base [style.colourUnarmed(unarmed damage)] equals [style.colourMinorGood(20% of physique)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.FOUR_MIGHTY.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] an exceptional level of fitness, and there are few who could ever hope to rival [npc.her] raw physical power.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.FOUR_MIGHTY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect PHYSIQUE_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"Herculean",
			"attStrength5",
			PresetColour.ATTRIBUTE_PHYSIQUE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 50f)),
			Util.newArrayListOfValues(
					"Base [style.colourUnarmed(unarmed damage)] equals [style.colourMinorGood(20% of physique)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.FIVE_HERCULEAN.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] body is the stuff of legend; mere mortals look upon [npc.herHim] in fear and awe!");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.FIVE_HERCULEAN;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};

	// Intelligence:
	public static AbstractStatusEffect INTELLIGENCE_PERK_0_OLD_WORLD = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"No Arcane Power",
			"attIntelligence0",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Due to the fact that the arcane doesn't exist in this world, your ability to cast spells is non-existent.";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.ZERO_AIRHEAD && !Main.game.isInNewWorld();
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"arcane impotence",
			"attIntelligence0",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, -75f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, -75f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.GENERIC_TERRIBLE.toWebHexString() + "'>Surrender in combat at maximum lust</b>",
					"[style.boldMana(Maximum "+Attribute.MANA_MAXIMUM.getName()+")] [style.boldBad(limited to 5)]",
					"[style.boldBad(Vulnerable)] to [style.boldArcane(arcane storms)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.ZERO_AIRHEAD.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Despite your natural affinity with the arcane, you've somehow managed to end up losing most of your power...";
			} else {
				return UtilText.parse(owner, "[npc.Name] is unable to harness the arcane in any significant manner. This is a typical level of arcane affinity in all the common races of this world.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.ZERO_AIRHEAD && Main.game.isInNewWorld();
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"arcane potential",
			"attIntelligence1",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Immune)] to [style.boldArcane(arcane storms)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.ONE_AVERAGE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You have an exceptional natural ability to harness the arcane, and as a result, you're far more powerful than the vast majority of Dominion's population.";
			} else {
				return UtilText.parse(target, "[npc.Name] has a respectable knowledge of how to harness the arcane; equal to that of a common race who's undergone extensive training.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.ONE_AVERAGE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"arcane proficiency",
			"attIntelligence2",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Immune)] to [style.boldArcane(arcane storms)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.TWO_SMART.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] proficient at harnessing the arcane, and [npc.her] spells are not only easier to cast, but also do more damage.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.TWO_SMART;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"arcane prowess",
			"attIntelligence3",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 15f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 15f),
					new Value<>(Attribute.DAMAGE_FIRE, 5f),
					new Value<>(Attribute.DAMAGE_ICE, 5f),
					new Value<>(Attribute.DAMAGE_POISON, 5f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Immune)] to [style.boldArcane(arcane storms)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.THREE_BRAINY.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You are highly proficient with the arcane. Your spells are easier to cast and do more damage, and you also have a small amount of elemental damage affinity.";
			} else {
				return UtilText.parse(target, "[npc.Name] is highly proficient with the arcane. [npc.Her] spells are easier to cast and do more damage, and [npc.she] also has a small amount of elemental damage affinity.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.THREE_BRAINY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"arcane mastery",
			"attIntelligence4",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 20f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 20f),
					new Value<>(Attribute.DAMAGE_FIRE, 10f),
					new Value<>(Attribute.DAMAGE_ICE, 10f),
					new Value<>(Attribute.DAMAGE_POISON, 10f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Immune)] to [style.boldArcane(arcane storms)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.FOUR_GENIUS.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You are extremely proficient with the arcane. Your spells are easier to cast and do more damage, and you also have a considerable amount of elemental damage affinity.";
			} else {
				return UtilText.parse(target, "[npc.Name] is extremely proficient with the arcane."
						+ " [npc.Her] spells are easier to cast and do more damage, and [npc.she] also has a considerable amount of elemental damage affinity.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.FOUR_GENIUS;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect INTELLIGENCE_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"arcane brilliance",
			"attIntelligence5",
			PresetColour.ATTRIBUTE_ARCANE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 25f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 25f),
					new Value<>(Attribute.DAMAGE_FIRE, 15f),
					new Value<>(Attribute.DAMAGE_ICE, 15f),
					new Value<>(Attribute.DAMAGE_POISON, 15f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Immune)] to [style.boldArcane(arcane storms)]")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.FIVE_POLYMATH.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Your ability to harness the arcane is rivalled only by Lilith herself. Casting spells comes as naturally to you as does breathing.";
			} else {
				return UtilText.parse(owner, "[npc.NamePos] arcane ability is rivalled only by Lilith herself. Casting spells comes as naturally to [npc.herHim] as does breathing.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.FIVE_POLYMATH;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};

	// Corruption:
	public static AbstractStatusEffect CORRUPTION_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"Pure",
			"attCorruption0",
			PresetColour.CORRUPTION_STAGE_ZERO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, 25f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.ZERO_PURE.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You are completely uncorrupted, and aside from performing the most conservative of sexual acts with the person you love, you're not really interested in sex at all.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is completely uncorrupted, and aside from performing the most conservative of sexual acts with the person [npc.she] loves, [npc.sheIs] not really interested in sex at all.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.ZERO_PURE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect CORRUPTION_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"Vanilla",
			"attCorruption1",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, 15f),
					new Value<>(Attribute.DAMAGE_LUST, 5f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.ONE_VANILLA.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You're open to the idea of having casual sex, but are still unwilling to perform any extreme sexual acts.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is open to the idea of having casual sex, but is unwilling to perform any extreme sexual acts.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.ONE_VANILLA;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect CORRUPTION_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"dirty",
			"attCorruption2",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.TWO_HORNY.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Sexual acts that once may have made you feel uncomfortable are now the focus of your fantasies, and you can't wait to try them out on a willing partner...";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a dirty look in [npc.her] eyes, and you often notice [npc.her] gaze lingering hungrily over your body.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.TWO_HORNY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect CORRUPTION_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"Lewd",
			"attCorruption3",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -5f),
					new Value<>(Attribute.DAMAGE_LUST, 30f),
					new Value<>(Attribute.FERTILITY, 25f),
					new Value<>(Attribute.VIRILITY, 25f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.THREE_DIRTY.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				if (owner.hasVagina()) {
					return "Given power by the fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body, and you feel as though it's going to be far easier to get pregnant from now on...";
				} else if (owner.hasPenis()) {
					return "Given power by the fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body,"
							+ " and you feel as though it's going to be far easier to impregnate your sexual partners from now on...";
				} else {
					return "Given power by the fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body, but because you don't have any sexual organs, there's not much that's happened...";
				}
				
			} else {
				if (owner.hasVagina()) {
					return UtilText.parse(owner,
							"Given power by the fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.namePos] body, making it far easier for [npc.herHim] to get pregnant.");
				} else if (owner.hasPenis()) {
					return UtilText.parse(owner,
							"Given power by the fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.namePos] body, making it far easier for [npc.herHim] to impregnate others.");
				} else {
					return UtilText.parse(owner,
							"Given power by the fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.namePos] body,"
							+ " but because [npc.she] doesn't have any sexual organs, there's not much that's happened.");
				}
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.THREE_DIRTY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect CORRUPTION_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"Lustful",
			"attCorruption4",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f),
					new Value<>(Attribute.DAMAGE_LUST, 40f),
					new Value<>(Attribute.FERTILITY, 50f),
					new Value<>(Attribute.VIRILITY, 50f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.FOUR_LUSTFUL.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				if (owner.hasVagina()) {
					return "Given a huge amount of power by the lewd fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body,"
							+ " and you feel as though it's going to be far easier to get pregnant from now on...";
				} else if (owner.hasPenis()) {
					return "Given a huge amount of power by the lewd fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body,"
							+ " and you feel as though it's going to be far easier to impregnate your sexual partners from now on...";
				} else {
					return "Given a huge amount of power by the lewd fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body,"
							+ " but because you don't have any sexual organs, there's not much that's happened...";
				}
				
			} else {
				if (owner.hasVagina()) {
					return UtilText.parse(owner,
							"Given a huge amount of power by the lewd fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.namePos] body,"
							+ " making it far easier for [npc.herHim] to get pregnant.");
				} else if (owner.hasPenis()) {
					return UtilText.parse(owner,
							"Given a huge amount of power by the lewd fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.namePos] body,"
							+ " making it far easier for [npc.herHim] to impregnate others.");
				} else {
					return UtilText.parse(owner,
							"Given a huge amount of power by the lewd fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.namePos] body,"
							+ " but because [npc.she] doesn't have any sexual organs, there's not much that's happened.");
				}
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.FOUR_LUSTFUL;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
			
	public static AbstractStatusEffect CORRUPTION_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"Corrupt",
			"attCorruption5",
			PresetColour.ATTRIBUTE_CORRUPTION,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f),
					new Value<>(Attribute.DAMAGE_LUST, 50f),
					new Value<>(Attribute.FERTILITY, 75f),
					new Value<>(Attribute.VIRILITY, 75f)),
			Util.newArrayListOfValues("<b style='color: "+ PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString()+ "'>Demonic mindset</b>")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.FIVE_CORRUPT.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] completely and utterly corrupted"
						+ (owner.getSubspeciesOverrideRace()==Race.DEMON
							?", as is fitting for [npc.a_race]."
							:", and desperately [npc.verb(wish)] that [npc.she] [npc.was] a demon.")
					+ " The lewd thoughts and fantasies that continuously run through [npc.her] mind have unlocked the full power of the arcane, making [npc.her] body hyper-fertile and virile.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.FIVE_CORRUPT;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	// Arousal:
	public static AbstractStatusEffect AROUSAL_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"none",
			"attArousal0",
			PresetColour.AROUSAL_STAGE_ZERO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.ZERO_NONE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You aren't aroused at all.";
			else
				return UtilText.parse(target, "[npc.Name] isn't aroused at all.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.ZERO_NONE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> effects = new ArrayList<>();
			if(Main.game.isInSex()) {
				if(Main.sex.isInForeplay(target)) {
					effects.add("[style.colourPinkLight(Foreplay)]");
					effects.add("[style.colourMinorBad(-50%)] arousal gains");
				} else {
					effects.add("[style.colourPink(Main Sex)]");
					effects.add("[style.colourMinorGood(Full)] arousal gains");
					effects.add(UtilText.parse(target, "<i>Having already orgasmed, [npc.nameIsFull] no longer in foreplay at this arousal level</i>"));
				}
			}
			return effects;
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"turned on",
			"attArousal1",
			PresetColour.AROUSAL_STAGE_ONE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPinkLight(Foreplay)]",
					"[style.colourMinorBad(-50%)] arousal gains")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.ONE_TURNED_ON.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You're starting to get pretty turned on.";
			else
				return UtilText.parse(target, "[npc.Name] is starting to get turned on.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.ONE_TURNED_ON;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return AROUSAL_PERK_0.getExtraEffects(target);
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"excited",
			"attArousal2",
			PresetColour.AROUSAL_STAGE_TWO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPink(Main Sex)]",
					"[style.colourMinorGood(Full)] arousal gains")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.TWO_EXCITED.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You're getting quite excited, and your thoughts are now focused on your sexual desires.";
			else
				return UtilText.parse(target, "[npc.Name] is getting quite excited.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.TWO_EXCITED;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"heated",
			"attArousal3",
			PresetColour.AROUSAL_STAGE_THREE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPink(Main Sex)]",
					"[style.colourMinorGood(Full)] arousal gains")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.THREE_HEATED.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Things are starting to get pretty heated. You can focus on nothing but the thought of sex.";
			else
				return UtilText.parse(target, "[npc.Name] can no longer focus on anything but sex.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.THREE_HEATED;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"passionate",
			"attArousal4",
			PresetColour.AROUSAL_STAGE_FOUR,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPink(Main Sex)]",
					"[style.colourMinorGood(Full)] arousal gains")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.FOUR_PASSIONATE.getName());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "The only thing you want right now is to reach your climax.";
			else
				return UtilText.parse(target, "[npc.Name] is only concerned with reaching [npc.her] climax.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.FOUR_PASSIONATE;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect AROUSAL_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"imminent orgasm",
			"attArousal5",
			PresetColour.AROUSAL_STAGE_FIVE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourPink(Main Sex)]",
					"[style.colourMinorGood(Full)] arousal gains")) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.FIVE_ORGASM_IMMINENT.getName());
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You feel your climax building. You know that it's only going to be a matter of seconds before you orgasm!";
			else
				return UtilText.parse(owner, "[npc.Name] is about to reach [npc.her] climax!");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.FIVE_ORGASM_IMMINENT;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	
	// Lust:
	public static AbstractStatusEffect LUST_PERK_0 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"none",
			"attLust0",
			PresetColour.LUST_STAGE_ZERO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.ZERO_COLD.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.ZERO_COLD.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.ZERO_COLD.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.ZERO_COLD;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_1 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"turned on",
			"attLust1",
			PresetColour.LUST_STAGE_ONE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.ONE_HORNY.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.ONE_HORNY.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.ONE_HORNY.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.ONE_HORNY;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_2 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"excited",
			"attLust2",
			PresetColour.LUST_STAGE_TWO,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.TWO_AMOROUS.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.TWO_AMOROUS.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.TWO_AMOROUS.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.TWO_AMOROUS;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_3 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"heated",
			"attLust3",
			PresetColour.LUST_STAGE_THREE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.THREE_LUSTFUL.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.THREE_LUSTFUL.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.THREE_LUSTFUL.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.THREE_LUSTFUL;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_4 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"passionate",
			"attLust4",
			PresetColour.LUST_STAGE_FOUR,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.FOUR_IMPASSIONED.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.FOUR_IMPASSIONED.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.FOUR_IMPASSIONED.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.FOUR_IMPASSIONED;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect LUST_PERK_5 = new AbstractStatusEffect(StatusEffectCategory.ATTRIBUTE,
			100,
			"passionate",
			"attLust5",
			PresetColour.LUST_STAGE_FIVE,
			PresetColour.BASE_BLACK,
			PresetColour.BASE_BLACK,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.FIVE_BURNING.getName());
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(super.getModifiersAsStringList(target));
			modList.addAll(LustLevel.FIVE_BURNING.getStatusEffectModifierDescription(Main.sex.isConsensual(), target));
			return modList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.FIVE_BURNING.getStatusEffectDescription(Main.sex.isConsensual(), target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.FIVE_BURNING;
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	
	
	
	// WEATHER & LOCATION EFFECTS:
	
	public static AbstractStatusEffect WEATHER_PROLOGUE = new AbstractStatusEffect(100,
			"Strange Atmosphere",
			"weatherNightStormIncoming",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {
		@Override
		public String getDescription(GameCharacter target) {
			return "There's a strange atmosphere surrounding the museum this evening, and you inexplicably find yourself feeling incredibly aroused...";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInNewWorld();
		}
	};
	
	public static AbstractStatusEffect WEATHER_CLEAR = new AbstractStatusEffect(100,
			"Clear skies",
			"weatherDayClear",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(Main.game.isDayTime()) {
				return "The sun shines down from a perfectly clear blue sky."
						+ " Although there's no sign of a storm at the moment, you can still feel the effects of the arcane manifesting in the form of an increased libido.";
			} else {
				return "The moon and stars shine down from a perfectly clear night's sky."
						+ " Although there's no sign of a storm at the moment, you can still feel the effects of the arcane manifesting in the form of an increased libido.";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.CLEAR && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime())
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayClear();
			else
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightClear();
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				return Util.newArrayListOfValues(	"<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>",
						"Time until next <b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>arcane storm</b>:",
						Main.game.getNextStormTimeAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(	"<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
			}
		}
	};
	
	public static AbstractStatusEffect WEATHER_CLOUD = new AbstractStatusEffect(100,
			"Cloudy skies",
			"weatherDayCloudy",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {
		@Override
		public String getDescription(GameCharacter target) {
			return "The weather seems to change at a moment's notice, and is currently overcast, with a chance of rain."
					+ " Although there's no sign of a storm at the moment, you can still feel the effects of the arcane manifesting in the form of an increased libido.";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.CLOUD && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime())
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayCloud();
			else
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightCloud();
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				return Util.newArrayListOfValues(	"<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>",
						"Time until next <b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>arcane storm</b>:",
						Main.game.getNextStormTimeAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(	"<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
			}
		}
	};
	
	public static AbstractStatusEffect WEATHER_RAIN = new AbstractStatusEffect(100,
			"Rain",
			"weatherDayRain",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {
		@Override
		public String getDescription(GameCharacter target) {
			return "The heavy rain clouds overhead have finally burst, unleashing a sudden, and torrential, downpour."
					+ " Although there's no sign of an arcane storm at the moment, you can still feel its effects manifesting in the form of an increased libido.";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.RAIN && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime())
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayRain();
			else
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightRain();
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				return Util.newArrayListOfValues(	"<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>",
						"Time until next <b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>arcane storm</b>:",
						Main.game.getNextStormTimeAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(	"<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
			}
		}
	};
	
	public static AbstractStatusEffect WEATHER_SNOW = new AbstractStatusEffect(100,
			"Snow",
			"weatherDaySnow",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.hasSnowedThisWinter)) {
				Main.game.getDialogueFlags().values.add(DialogueFlagValue.hasSnowedThisWinter);
				
				if(Main.game.getReindeerOverseers().isEmpty()) {
					try {
						for(int i=0; i<2; i++) {
							Main.game.addNPC(new ReindeerOverseer(Gender.M_P_MALE), false);
						}
						for(int i=0; i<2; i++) {
							Main.game.addNPC(new ReindeerOverseer(Gender.F_V_B_FEMALE), false);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					for(NPC npc : Main.game.getReindeerOverseers()) {
						npc.setRandomLocation(WorldType.DOMINION, PlaceType.DOMINION_STREET, true);
					}
				}
				return "<p>"
							+ "The oppressive, dark-grey clouds which have been hanging over Dominion for the past few hours finally burst."
							+ " Large, fluffy snowflakes slowly drift down from above, and although the first few crystals quickly melt away upon coming into contact with the ground below,"
								+ " it doesn't take long before a thin white dusting of powdery white has settled upon the rooftops and pathways of the capital."
						+ "</p>"
						+ "<p>"
							+ "What looked at first to be no more than a quick flurry soon intensifies into a wild snowstorm, and in less than an hour, a thick white blanket of snow has begun to smother the streets."
							+ " Another hour later, and the snow's built up to such a degree that all travel through the city slows to a crawl."
							+ " A few demons try to use their arcane fire to blast a path through the freezing snowdrifts, but the snowfall is so heavy that even their powerful spells seem to have a limited impact."
						+ "</p>"
						+ "<p>"
							+ "Just as it seems as though the entire capital is about to grind to a halt, the faint jingle of bells heralds the arrival of the city's saviours."
							+ " Travelling a great distance from their frozen tundra homeland, a host of reindeer-morphs descends upon Dominion."
							+ " Their large, cloven hoofs allow them to traverse the snow-bound streets with incredible ease, and they quickly split up into numerous snow-shovelling groups,"
								+ " before setting off into different areas of the city."
							+ " Under the guidance of particularly large, muscular individuals, the reindeer-morphs quickly set about clearing a path through the snow."
						+ "</p>"
						+ "<p>"
							+ "Although the weather refuses to let up, it only takes a few hours for the impressive reindeer-morphs to clear all of the snow from the streets."
							+ " Talk soon spreads about how the visitors will be staying in Dominion to work until the end of February, making sure that the city is able to function through these cold winter months."
						+ "</p>";
			} else {
				return "";
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			return "The heavy clouds overhead have finally burst, unleashing a flurry of brilliant white snowflakes upon the land below."
					+ " Although there's no sign of an arcane storm at the moment, you can still feel its effects manifesting in the form of an increased libido.";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.SNOW && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime())
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDaySnow();
			else
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightSnow();
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				return Util.newArrayListOfValues(	"<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>",
						"Time until next <b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>arcane storm</b>:",
						Main.game.getNextStormTimeAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(	"<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
			}
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM_GATHERING = new AbstractStatusEffect(100,
			"Gathering storm",
			"weatherDayStormIncoming",
			PresetColour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {
		@Override
		public String getDescription(GameCharacter target) {
			return "A roiling mass of thick black storm clouds hang heavy in the skies above you."
					+ " Flashes of pink and purple energy can be seen just beneath their surface, and you realise that an arcane storm is going to break out at any moment.";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM_GATHERING && Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime())
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStormIncoming();
			else
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStormIncoming();
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				return Util.newArrayListOfValues(	"<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>",
						"Time until next <b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>arcane storm</b>:",
						Main.game.getNextStormTimeAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(	"<b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
			}
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM = new AbstractStatusEffect(100,
			"Arcane storm",
			"weatherDayStorm",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -5f)),
			Util.newArrayListOfValues("<b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
					"[style.boldExcellent(Double)] all <b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
				if(!Main.game.isWeatherInterruptionsEnabled() || !Main.game.getPlayer().getGlobalLocationPlace().getPlaceType().equals(PlaceType.WORLD_MAP_DOMINION)) {
					return "";
				}
				
				StringBuilder sb = new StringBuilder();
				
				sb.append("<p>"
							+ "A bright-pink flash suddenly illuminates the entire city of Dominion, causing those few residents still prowling the streets to look skywards."
							+ " High up above them, the threatening storm clouds have finally broken, and a roiling mass of arcane energy finally crackles into life."
						+ "</p>"
						+ "<p>"
							+ "Within moments, a ghostly series of lewd moans and ecstatic screams start echoing throughout the city, and as the arcane thunder penetrates into the minds of those without a strong aura,"
							+ " they find themselves unable to think of anything but sex."
						+ "</p>");
				
				AbstractPlaceType place = target.getGlobalLocationPlace().getPlaceType();
				
				if(!place.equals(PlaceType.WORLD_MAP_DOMINION)){
					sb.append("<p>"
							+ "Although it breaks high over Dominion, the storm isn't contained to just within the city, and swiftly sweeps out across the Foloi Fields and into the surrounding forests and grassland wilderness."
							+ " Like a chain reaction, flashes of purple lightning streak across the sky in all directions, which are quickly followed by the erotic moaning of arcane thunder."
						+ "</p>");

					if(place.equals(PlaceType.WORLD_MAP_FIELDS)
							|| place.equals(PlaceType.WORLD_MAP_FIELDS_CITY)
							|| place.equals(PlaceType.WORLD_MAP_FOREST)
							|| place.equals(PlaceType.WORLD_MAP_GRASSLANDS)
							|| place.equals(PlaceType.WORLD_MAP_RIVER)) {
						
						if(target.getLocationPlace().isStormImmune()) {
							sb.append("<p>"
										+ "Although completely immune to its effects yourself, you can feel that the storm is considerably weaker out here, far from the epicentre."
										+ " While it's highly likely that anyone you meet in this area will be hornier than usual, you imagine that they'll be able to control themselves enough to resist the storm's arousing effects."
									+ "</p>");
						} else {
							sb.append("<p>"
										+ "Although normally vulnerable to its effects, you only feel a little hornier than usual; proof that the storm is considerably weaker out here, far from the epicentre."
										+ " You quickly realise that anyone you meet in this area will surely be able to control themselves enough to resist the usual arousing effects, just as you can."
									+ "</p>");
						}
						
					} else {

						sb.append("<p>"
									+ "As the storm's epicentre is directly over Dominion, you're far enough away so that you can only hear the very faintest of arcane moans in the air."
									+ " While it's likely that anyone you come across out here will be a little hornier than usual, you imagine that they'll easily be able to resist the storm's arousing effects."
								+ "</p>");
					}
				}
				
				return sb.toString();
				
			} else {
				return "";
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Huge streaks of pink and purple lightning arc through the sky as an arcane storm rages high above Dominion."
						+ " Although resistant to most of its arousing power, you're not completely unaffected, and you find yourself feeling a little hornier than usual.";
				
			} else {
				if(!target.isVulnerableToArcaneStorm()) {
					return UtilText.parse(target, "[npc.NamePos] affinity with the arcane has rendered [npc.herHim] all but immune to the arousing effects of arcane storms!");
				} else {
					return UtilText.parse(target, "[npc.NameIsFull] is far enough away from the storm's epicentre to be rendered all but immune to its arousing effects!");
				}
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					&& Main.game.isInNewWorld()
					&& Main.game.isStarted()
					&& ((!target.isVulnerableToArcaneStorm() && !(target.isElemental() && ((Elemental)target).getSummoner()!=null?((Elemental)target).getSummoner().getLocationPlace():target.getLocationPlace()).isStormImmune())
							|| !target.getGlobalLocationPlace().getPlaceType().equals(PlaceType.WORLD_MAP_DOMINION));
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStorm();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStorm();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				return Util.newArrayListOfValues(	"<b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
						"[style.boldExcellent(Double)] all <b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat",
						"Time until <b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>storm ends</b>:",
						Main.game.getWeatherTimeRemainingAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(	"<b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
						"[style.boldExcellent(Double)] all <b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat");
			}
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM_VULNERABLE = new AbstractStatusEffect(100,
			"Arcane storm",
			"weatherDayStorm",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -100f),
					new Value<>(Attribute.RESTING_LUST, 50f)),
			Util.newArrayListOfValues(
					"<b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
					"[style.boldExcellent(Double)] <b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
				if(!Main.game.isWeatherInterruptionsEnabled() || !Main.game.getPlayer().getGlobalLocationPlace().getPlaceType().equals(PlaceType.WORLD_MAP_DOMINION)) {
					return "";
				}
				
				return "<p>"
							+ "A bright-pink flash suddenly illuminates the entire city of Dominion, causing those few residents still prowling the streets to look skywards."
							+ " High up above them, the threatening storm clouds have finally broken, and a roiling mass of arcane energy finally crackles into life."
						+ "</p>"
						+ "<p>"
							+ "Within moments, a ghostly series of lewd moans and ecstatic screams start echoing throughout the city."
							+ " Although you entered this world with a high enough arcane affinity to be rendered immune to the storm's effects, you've ended up losing most of your power,"
								+ " and you can't help but let out a desperate [pc.moan] as you start to feel incredibly turned on."
						+ "</p>"
						+ "<p>"
							+ "As you continue on your way, you find yourself hoping that you'll run into someone willing to give you a good fuck..."
						+ "</p>";
			} else {
				return "";
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] being heavily affected by the ongoing arcane storm, and can think of nothing but sex...");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					&& Main.game.isInNewWorld()
					&& Main.game.isStarted()
					&& target.isVulnerableToArcaneStorm()
					&& !(target.isElemental() && ((Elemental)target).getSummoner()!=null?((Elemental)target).getSummoner().getLocationPlace():target.getLocationPlace()).isStormImmune()
					&& target.getGlobalLocationPlace().getPlaceType().equals(PlaceType.WORLD_MAP_DOMINION);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStorm();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStorm();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				return Util.newArrayListOfValues(	"<b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
						"<b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Overwhelming Lust</b>",
						"[style.boldExcellent(Double)] <b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat",
						"Time until <b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>storm ends</b>:",
						Main.game.getWeatherTimeRemainingAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(	"<b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
						"<b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Overwhelming Lust</b>",
						"[style.boldExcellent(Double)] <b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat");
			}
		}
	};
	
	public static AbstractStatusEffect WEATHER_STORM_PROTECTED = new AbstractStatusEffect(100,
			"Arcane storm (protected)",
			"weatherDayStorm",
			PresetColour.GENERIC_GOOD,
			true,
			null,
			Util.newArrayListOfValues("<b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
				if(!Main.game.isWeatherInterruptionsEnabled() || !Main.game.getPlayer().getGlobalLocationPlace().getPlaceType().equals(PlaceType.WORLD_MAP_DOMINION)) {
					return "";
				}
				
				return "<p>"
							+ "A bright-pink flash suddenly illuminates the entire city of Dominion, causing those few residents still prowling the streets to look skywards."
							+ " High up above them, the threatening storm clouds have finally broken, and a roiling mass of arcane energy finally crackles into life."
						+ "</p>"
						+ "<p>"
							+ "Within moments, a ghostly series of lewd moans and ecstatic screams start echoing throughout the city, and as the arcane thunder penetrates into the minds of those without a strong aura,"
							+ " they find themselves unable to think of anything but sex..."
						+ "</p>";
			} else {
				return "";
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Huge streaks of pink and purple lightning arc through the sky as an arcane storm rages high above you."
						+ " Although you can still feel its effects taking the form of an increased libido, you're currently protected from most of the storm's wrath.";
			} else {
				return UtilText.parse(target,
						"[npc.Name] is currently protected from the arcane storm.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					&& Main.game.isInNewWorld()
					&& Main.game.isStarted()
					&& (target.isElemental() && ((Elemental)target).getSummoner()!=null?((Elemental)target).getSummoner().getLocationPlace():target.getLocationPlace()).isStormImmune()
					&& target.getGlobalLocationPlace().getPlaceType().equals(PlaceType.WORLD_MAP_DOMINION);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStormProtected();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStormProtected();
			}
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)) {
				return Util.newArrayListOfValues(	"<b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
						"Time until <b style='color: " + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>storm ends</b>:",
						Main.game.getWeatherTimeRemainingAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(	"<b style='color: "+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>");
			}
		}
	};

	public static AbstractStatusEffect BLINDED = new AbstractStatusEffect(90,
			"Blinded",
			"blinded",
			PresetColour.BASE_BLACK,
			PresetColour.BASE_RED,
			PresetColour.BASE_GREY_LIGHT,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, -50f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, -50f),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -50f),
					new Value<>(Attribute.DAMAGE_SPELLS, -50f),
					new Value<>(Attribute.ACTION_POINTS, -1f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"[npc.NameHasFull] been effectively blinded, and as such [npc.she] [npc.is] struggling effectively navigate through [npc.her] surroundings, and will be extremely ineffective in combat!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSightHindered() && !target.hasEchoLocation();
		}
	};

	public static AbstractStatusEffect BLINDED_NEGATED = new AbstractStatusEffect(90,
			"Blinded (Echo location)",
			"blinded_negated",
			PresetColour.BASE_BLACK,
			PresetColour.BASE_GREEN,
			PresetColour.BASE_GREY_LIGHT,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, -5f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, -5f),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -5f),
					new Value<>(Attribute.DAMAGE_SPELLS, -5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Although [npc.name] should be effectively blinded, [npc.she] [npc.is] able to effectively navigate through [npc.her] surroundings using [npc.her] echo location ability!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSightHindered() && target.hasEchoLocation();
		}
	};

	public static AbstractStatusEffect DARKNESS = new AbstractStatusEffect(90,
			"Darkness",
			"darkness",
			PresetColour.BASE_BLACK,
			PresetColour.BASE_RED,
			PresetColour.BASE_GREY_LIGHT,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_UNARMED, -25f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, -25f),
					new Value<>(Attribute.DAMAGE_RANGED_WEAPON, -25f),
					new Value<>(Attribute.DAMAGE_SPELLS, -25f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"The area which [npc.name] [npc.verb(find)] [npc.herself] travelling through is very dark, and as [npc.she] [npc.verb(lack)] any means of illuminating the area, [npc.she] [npc.is] struggling to see where [npc.sheIs] going!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isInDarkness();
		}
	};

	public static AbstractStatusEffect DARKNESS_NEGATED = new AbstractStatusEffect(90,
			"Darkness (Negated)",
			"darkness_negated",
			PresetColour.BASE_BLACK,
			PresetColour.BASE_GREEN,
			PresetColour.BASE_GREY_LIGHT,
			true,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues()) {
		@Override
		public EffectBenefit getBeneficialStatus() {
			return EffectBenefit.NEUTRAL;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"The area which [npc.name] [npc.verb(find)] [npc.herself] travelling through is very dark, but despite this, [npc.she] [npc.is] able to see [#npc.getDescriptionInDarkness()].");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getCell().isDark() && !target.isInDarkness();
		}
	};
	
	
	// RACES:
	// HUMAN:
	public static AbstractStatusEffect PURE_HUMAN_PROLOGUE = new AbstractStatusEffect(1000,
			"human",
			null,
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "You're a human, just like every other person in this world.";
			else
				return "[npc.Name] is a human, just like every other person in this world.";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.HUMAN
					&& target.getRaceStage() == RaceStage.HUMAN
					&& !Main.game.isInNewWorld();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return owner.getSubspecies().getSVGString(owner);
		}
	};
	
	public static AbstractStatusEffect SUBSPECIES_BONUS = new AbstractStatusEffect(1000,
			"",
			null,
			PresetColour.CLOTHING_WHITE,
			true,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			if(target.isRaceConcealed()) {
				return "Concealed subspecies bonus";
			}
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				return target.getSubspeciesOverride().getName(null)+" ("+target.getSubspecies().getName(target.getBody())+")";
			}
			return (target.isFeral()?"[style.colourFeral(Feral)] ":"")+target.getSubspecies().getName(target.getBody());
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isRaceConcealed()) {
				return UtilText.parse(target, "Although [npc.namePos] race is concealed, they're still benefiting from the attribute modifiers...");
			}
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				return target.getSubspeciesOverride().getStatusEffectDescription(target);
			} else {
				return target.getSubspecies().getStatusEffectDescription(target);	
			}
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			List<Value<Integer, String>> additionalDescriptions = new ArrayList<>();
			
			// Add subspecies appearance change:
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				String subspeciesName = target.getSubspecies().getName(target.getBody());
				additionalDescriptions.add(
						new Value<>(2,
								UtilText.parse(target,
						"<i style='color:"+target.getSubspecies().getColour(target).toWebHexString()+";'>"
							+ "[npc.Name] [npc.verb(appear)] to be "+UtilText.generateSingularDeterminer(subspeciesName) + " " +subspeciesName+", and will be treated by people [npc.she] [npc.verb(meet)] as being one!"
						+ "</i>")));
			}

			// Add body material modifiers:
			BodyMaterial material = target.getBodyMaterial();
			if(material.getAttributeModifiers(target)!=null
					|| material.getExtraEffects(target)!=null) {
				int lineHeight = 1;
				StringBuilder sb = new StringBuilder();
				sb.append(UtilText.parse(target, "<i style='color:"+material.getColour().toWebHexString()+";'>[npc.NamePos] "+material.getName()+" body grants [npc.herHim]:</i>"));
				if(material.getAttributeModifiers(target)!=null) {
					for(String s : attributeModifiersToStringList(material.getAttributeModifiers(target))) {
						sb.append("<br/>"+s);
						lineHeight++;
					}
				}
				if(material.getExtraEffects(target)!=null) {
					for(String s : material.getExtraEffects(target)) {
						sb.append("<br/>"+s);
						lineHeight++;
					}
				}
				additionalDescriptions.add(new Value<>(lineHeight, sb.toString()));
			}
			
			return additionalDescriptions;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInNewWorld();
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			LinkedHashMap<AbstractAttribute, Float> attMods;
			
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				attMods = new LinkedHashMap<>(target.getSubspeciesOverride().getStatusEffectAttributeModifiers(target));
			} else {
				attMods = new LinkedHashMap<>(target.getSubspecies().getStatusEffectAttributeModifiers(target));
			}
			
			BodyMaterial material = target.getBodyMaterial();
			if(material.getAttributeModifiers(target)!=null) {
				for(Entry<AbstractAttribute, Float> entry : material.getAttributeModifiers(target).entrySet()) {
					attMods.putIfAbsent(entry.getKey(), 0f);
					attMods.put(entry.getKey(), attMods.get(entry.getKey())+entry.getValue());
				}
			}
			
			return attMods;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				return target.getSubspeciesOverride().getExtraEffects(target);
			}
			return target.getSubspecies().getExtraEffects(target);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			LinkedHashMap<AbstractAttribute, Float> attMods;

			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride()!=target.getSubspecies()) {
				attMods = new LinkedHashMap<>(target.getSubspeciesOverride().getStatusEffectAttributeModifiers(target));
			} else {
				attMods = new LinkedHashMap<>(target.getSubspecies().getStatusEffectAttributeModifiers(target));
			}
			
			ArrayList<String> fullModList = new ArrayList<>(attributeModifiersToStringList(attMods));
			fullModList.addAll(getExtraEffects(target));
			
			return fullModList;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isRaceConcealed()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getRaceUnknown();
			}
			return owner.getSubspecies().getSVGString(owner);
		}
	};

	public static AbstractStatusEffect AQUATIC_TAIL_POSITIVE = new AbstractStatusEffect(90,
			"Aquatic harmony",
			"aquatic_positive",
			PresetColour.GENERIC_GOOD,
			PresetColour.BASE_BLUE_LIGHT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, 1f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 15f),
					new Value<>(Attribute.ENERGY_SHIELDING, 5f)),
			Util.newArrayListOfValues(
					"[style.boldBlueLight(Lost legs)]")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			if(!target.isPlayer()) {
				return "";
			}
			return "As you increase your proximity to the nearby body of water, you feel an intense tingling start to run up the length of your legs."
					+ " Without any further warning of what's about to happen, your muscles involuntarily clench, pressing your legs together and causing you to let out a startled cry."
					+ " Before you're able to react, your legs rapidly fuse together and transform, and within moments your lower body has assumed its true, tailed form!"
					+ "<p style='text-align:center;'>"
						+ "[style.italicsMinorBad(You can no longer equip clothing in your leg and foot slots!)]"
					+ "</p>"
					+ target.postTransformationCalculation(); // To handle clothing removals
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"As [npc.nameIsFull] [npc.a_race], and [npc.she] [npc.has] access to a nearby body of water, [npc.her] lower body has transformed into its true, tail-like form, making [npc.herHim] feel very comfortable!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getCell().getAquatic().isWater()
					&& target.getLegConfiguration()==LegConfiguration.TAIL
					&& target.getSubspecies().isAquatic(target);
		}
	};

	public static AbstractStatusEffect AQUATIC_TAIL_NEGATIVE = new AbstractStatusEffect(90,
			"Fish out of water",
			"aquatic_negative",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_TAN,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.ACTION_POINTS, -1f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -15f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues(
					"[style.boldTan(Grown two legs)]")) {
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			if(!target.isPlayer()) {
				return "";
			}
			return "Finding yourself in an area with no large body of water nearby, you suddenly feel your tailed lower body starting to tingle."
					+ " Without any further warning of what's about to happen, your muscles involuntarily clench, causing you to let out a startled cry."
					+ " Before you're able to react, your tail rapidly splits and transforms into a pair of legs, which, while granting you the ability to walk and run on land, feel very alien to you."
					+ "<p style='text-align:center;'>"
						+ "[style.italicsMinorGood(You can now equip clothing in your leg and foot slots!)]"
					+ "</p>"
					+ target.postTransformationCalculation(); // To handle clothing checks
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"As [npc.nameIsFull] [npc.a_race], and there is no body of water nearby, [npc.her] lower body has transformed into a pair of legs, making [npc.herHim] [npc.verb(feel)] very uncomfortable!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.getCell().getAquatic().isWater()
					&& target.getLegConfiguration()==LegConfiguration.TAIL
					&& target.getSubspecies().isAquatic(target);
		}
	};
	
	public static AbstractStatusEffect AQUATIC_POSITIVE = new AbstractStatusEffect(90,
			"Aquatic harmony",
			"aquatic_positive",
			PresetColour.GENERIC_GOOD,
			PresetColour.BASE_BLUE_LIGHT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 15f),
					new Value<>(Attribute.ENERGY_SHIELDING, 5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"As [npc.nameIsFull] [npc.a_race], and [npc.she] [npc.has] access to a nearby body of water, [npc.she] [npc.verb(feel)] very comfortable!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getCell().getAquatic().isWater()
					&& target.getLegConfiguration()!=LegConfiguration.TAIL
					&& target.getSubspecies().isAquatic(target);
		}
	};

	public static AbstractStatusEffect AQUATIC_NEGATIVE = new AbstractStatusEffect(90,
			"Fish out of water",
			"aquatic_negative",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_TAN,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -15f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"As [npc.nameIsFull] [npc.a_race], and there is no body of water nearby, [npc.she] is feeling very uncomfortable!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.getCell().getAquatic().isWater()
					&& target.getLegConfiguration()!=LegConfiguration.TAIL
					&& target.getSubspecies().isAquatic(target);
		}
	};

//	public static AbstractStatusEffect OCCUPATION_PERK = new AbstractStatusEffect(1000,
//			"",
//			null,
//			PresetColour.CLOTHING_WHITE,
//			true,
//			null,
//			null) {
//		@Override
//		public String getName(GameCharacter target) {
//			return target.getOccupation().getAssociatedPerk().getName(target);
//		}
//		@Override
//		public String getDescription(GameCharacter target) {
//			return target.getOccupation().getAssociatedPerk().getDescription(target);
//		}
////		@Override
////		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
////			List<Value<Integer, String>> additionalDescriptions = new ArrayList<>();
////			
////			return additionalDescriptions;
////		}
//		@Override
//		public boolean isConditionsMet(GameCharacter target) {
//			return !target.isPlayer() || Main.game.isInNewWorld(); //TODO
//		}
//		@Override
//		public List<String> getModifiersAsStringList(GameCharacter target) {
//			List<String> extraModifiersList = new ArrayList<>();
//			return extraModifiersList;
//		}
//		@Override
//		public String getSVGString(GameCharacter owner) {
//			return owner.getOccupation().getAssociatedPerk().getSVGString(owner);
//		}
//	};
	
	// SEXUAL ORIENTATIONS:
	
	public static AbstractStatusEffect ORIENTATION_ANDROPHILIC = new AbstractStatusEffect(90,
			"androphilic",
			"orientation_androphilic",
			PresetColour.MASCULINE,
			true,
			null,
			Util.newArrayListOfValues("<b>-50%</b> <b style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>Lust damage</b> both to and from <b style='color:"+ PresetColour.FEMININE.toWebHexString()+ ";'>feminine opponents</b>")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You are sexually attracted to males and masculinity.";
			} else {
				return UtilText.parse(target, "[npc.Name] is sexually attracted to males and masculinity.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getSexualOrientation()==SexualOrientation.ANDROPHILIC;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ORIENTATION_GYNEPHILIC = new AbstractStatusEffect(90,
			"gynephilic",
			"orientation_gynephilic",
			PresetColour.FEMININE,
			true,
			null,
			Util.newArrayListOfValues("<b>-50%</b> <b style='color:"+ PresetColour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>Lust damage</b> both to and from <b style='color:"+ PresetColour.MASCULINE.toWebHexString()+ ";'>masculine opponents</b>")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You are sexually attracted to females and femininity.";
			} else {
				return UtilText.parse(target, "[npc.Name] is sexually attracted to females and femininity.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getSexualOrientation()==SexualOrientation.GYNEPHILIC;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	public static AbstractStatusEffect ORIENTATION_AMBIPHILIC = new AbstractStatusEffect(90,
			"ambiphilic",
			"orientation_ambiphilic",
			PresetColour.ANDROGYNOUS,
			true,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You are sexually attracted to both masculine and feminine people.";
			} else {
				return UtilText.parse(target, "[npc.Name] is sexually attracted to both masculine and feminine people.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getSexualOrientation()==SexualOrientation.AMBIPHILIC;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	

	// CLOTHING:

	public static AbstractStatusEffect CLOTHING_FEMININITY = new AbstractStatusEffect(85,
			"clothing too feminine",
			"clothingFemininity",
			PresetColour.CLOTHING_PINK_LIGHT,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Some of [npc.namePos] clothes are too feminine for [npc.her] masculine figure."
						+ " [npc.She] [npc.verb(find)] [npc.herself] incredibly embarrassed by wearing such clothing and [npc.is] struggling to think clearly.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.hasFetish(Fetish.FETISH_CROSS_DRESSER) || target.hasPerkAnywhereInTree(Perk.SPECIAL_CLOTHING_FEMININITY_INDIFFERENCE)) {
				return false;
			}
			for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if(c.getClothingType().getFemininityMinimum() > target.getFemininityValue()) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_MASCULINITY = new AbstractStatusEffect(85,
			"clothing too masculine",
			"clothingMasculinity",
			PresetColour.CLOTHING_BLUE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Some of [npc.namePos] clothes are too masculine for [npc.her] feminine figure."
						+ " [npc.She] [npc.verb(find)] [npc.herself] incredibly embarrassed by wearing such clothing and [npc.is] struggling to think clearly.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.hasFetish(Fetish.FETISH_CROSS_DRESSER) || target.hasPerkAnywhereInTree(Perk.SPECIAL_CLOTHING_MASCULINITY_INDIFFERENCE)) {
				return false;
			}
			for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if(c.getClothingType().getFemininityMaximum() < target.getFemininityValue()) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_CUM = new AbstractStatusEffect(80,
			"dirty clothing",
			"clothingCummedIn",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 5f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Some of [npc.namePos] clothes have been covered in cum, milk or other sexual fluids."
						+ " [npc.SheIs] feeling incredibly embarrassed to be walking around in such filthy clothing.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your smelly clothes seem to attract trouble.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!isCumEffectPositive(target)) {
				for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
					if (c.isDirty()
							&& Collections.disjoint(
									c.getItemTags(),
									Util.newArrayListOfValues(ItemTag.PLUGS_ANUS, ItemTag.SEALS_ANUS, ItemTag.PLUGS_VAGINA, ItemTag.SEALS_VAGINA, ItemTag.PLUGS_NIPPLES, ItemTag.SEALS_NIPPLES))) {
						return true;
					}
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_CUM_MASOCHIST = new AbstractStatusEffect(80,
			"dirty clothing",
			"clothingCummedInMasochist",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 2f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Some of your clothes have been covered in cum, milk or other sexual fluids."
						+ " You find yourself incredibly turned on to be walking around in such filthy clothing.";
			} else {
				return UtilText.parse(target, "Some of [npc.namePos] clothes have been covered in cum, milk or other sexual fluids."
						+ " [npc.sheIs] feeling incredibly turned on to be walking around in such filthy clothing.");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your smelly clothes seem to attract trouble.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
					if (c.isDirty()
							&& Collections.disjoint(
									c.getItemTags(),
									Util.newArrayListOfValues(ItemTag.PLUGS_ANUS, ItemTag.SEALS_ANUS, ItemTag.PLUGS_VAGINA, ItemTag.SEALS_VAGINA, ItemTag.PLUGS_NIPPLES, ItemTag.SEALS_NIPPLES))) {
						return true;
					}
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect BODY_CUM = new AbstractStatusEffect(80,
			"dirty body",
			"dirtyBody",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 5f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			List<InventorySlot> slotsToClean = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			for(AbstractClothing clothing : new ArrayList<>(target.getClothingCurrentlyEquipped())) {
				if(target.getDirtySlots().contains(clothing.getSlotEquippedTo())) {
					InventorySlot slotEquippedTo = clothing.getSlotEquippedTo();
					Set<ItemTag> tags = clothing.getItemTags();
					slotsToClean.add(slotEquippedTo);
					
					boolean seals = tags.contains(ItemTag.SEALS_ANUS)
										|| tags.contains(ItemTag.SEALS_VAGINA)
										|| tags.contains(ItemTag.SEALS_NIPPLES);
					
					if(clothing.getSlotEquippedTo()==InventorySlot.ANUS && (tags.contains(ItemTag.SEALS_ANUS) || tags.contains(ItemTag.PLUGS_ANUS))
							|| clothing.getSlotEquippedTo()==InventorySlot.VAGINA && (tags.contains(ItemTag.SEALS_VAGINA) || tags.contains(ItemTag.PLUGS_VAGINA))
							|| clothing.getSlotEquippedTo()==InventorySlot.NIPPLE && (tags.contains(ItemTag.SEALS_NIPPLES) || tags.contains(ItemTag.PLUGS_NIPPLES))) {
						sb.append("You use your <b>"+clothing.getDisplayName(true)+"</b> to clean your "+clothing.getSlotEquippedTo().getName()
								+(seals
										?" as you equip "+(clothing.getClothingType().isPlural()?"them":"it")
										:" as you insert "+(clothing.getClothingType().isPlural()?"them into your orifices":"it into your orifice"))
								+".");
						
					} else {
						if(!clothing.isDirty()) {
							clothing.setDirty(target, true);
							if(sb.length()>0) {
								sb.append("<br/>");
							}
							sb.append("You use your <b>"+clothing.getDisplayName(true)+"</b> to clean your "+clothing.getSlotEquippedTo().getName()
									+", <b style='color:"+PresetColour.CUM.toWebHexString()+";'>dirtying "+(clothing.getClothingType().isPlural()?"them":"it")+" in the process</b>.");
						}
					}
					
				} else {
					for(InventorySlot blockedSlot : clothing.getIncompatibleSlots(target, clothing.getSlotEquippedTo())) {
						if(target.getDirtySlots().contains(blockedSlot)) {
							slotsToClean.add(blockedSlot);
							if(!clothing.isDirty()) {
								clothing.setDirty(target, true);
								if(sb.length()>0) {
									sb.append("<br/>");
								}
								sb.append("You use your <b>"+clothing.getDisplayName(true)+"</b> to clean your "+clothing.getSlotEquippedTo().getName()
										+", <b style='color:"+PresetColour.CUM.toWebHexString()+";'>dirtying "+(clothing.getClothingType().isPlural()?"them":"it")+" in the process</b>.");
							}
						}
					}
				}
			}
			for(InventorySlot slotToClean : slotsToClean) {
				target.removeDirtySlot(slotToClean, false);
			}
			
			if(target.isPlayer()) {
				return sb.toString();
			}
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Some parts of your body have been covered in cum, milk or other sexual fluids."
						+ " You find yourself incredibly embarrassed to be walking around in such a filthy state.";
			} else {
				return UtilText.parse(target, "Some parts of [npc.namePos] body have been covered in cum, milk or other sexual fluids."
						+ " [npc.sheIs] feeling incredibly embarrassed to be walking around in such a filthy state.");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your smelly body seems to attract trouble.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (!isCumEffectPositive(target)) && !target.getDirtySlots().isEmpty();
		}
	};
	
	public static AbstractStatusEffect BODY_CUM_MASOCHIST = new AbstractStatusEffect(80,
			"dirty body",
			"dirtyBodyMasochist",
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 2f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return StatusEffect.BODY_CUM.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Some parts of your body have been covered in cum, milk or other sexual fluids."
						+ " You find yourself feeling incredibly turned on by walking around in such a filthy state.";
			} else {
				return UtilText.parse(target, "Some parts of [npc.namePos] body have been covered in cum, milk or other sexual fluids."
						+ " [npc.sheIs] feeling incredibly turned on by walking around in such a filthy state.");
			}
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your smelly body seems to attract trouble.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (isCumEffectPositive(target)) && !target.getDirtySlots().isEmpty();
		}
	};
	

	public static AbstractStatusEffect CLOTHING_ENCHANTMENT_OVER_LIMIT = new AbstractStatusEffect(80,
			"unstable enchantments",
			"unstable_enchantment_1",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 25f)),
			Util.newArrayListOfValues("[style.boldMinorBad(-10%)] [style.boldHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]",
					"[style.boldMinorBad(-10%)] [style.boldMana(Maximum "+Attribute.MANA_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] unable to handle the amount of attribute enchantments infused into [npc.her] weapons, clothing, and tattoos, and so [npc.is] suffering from some negative side-effects."
							+ " If [npc.she] continues equipping enchanted items, this is sure to get much worse...");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int overBy = (int) (target.getEnchantmentPointsUsedTotal()-target.getAttributeValue(Attribute.ENCHANTMENT_LIMIT));
			return Main.game.isEnchantmentCapacityEnabled() && overBy>0 && overBy<10;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_ENCHANTMENT_OVER_LIMIT_2 = new AbstractStatusEffect(80,
			"volatile enchantments",
			"unstable_enchantment_2",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 50f)),
			Util.newArrayListOfValues("[style.boldBad(-50%)] [style.boldHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]",
					"[style.boldBad(-50%)] [style.boldMana(Maximum "+Attribute.MANA_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] unable to handle the amount of attribute enchantments infused into [npc.her] weapons, clothing, and tattoos, and so [npc.is] suffering from some severe negative side-effects."
							+ " If [npc.she] continues equipping enchanted items, this is sure to get much worse...");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int overBy = (int) (target.getEnchantmentPointsUsedTotal()-target.getAttributeValue(Attribute.ENCHANTMENT_LIMIT));
			return Main.game.isEnchantmentCapacityEnabled() && overBy>=10 && overBy<20;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_ENCHANTMENT_OVER_LIMIT_3 = new AbstractStatusEffect(80,
			"shattered enchantments",
			"unstable_enchantment_3",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_CORRUPTION, 100f)),
			Util.newArrayListOfValues("[style.boldHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")] [style.boldTerrible(set to 1)]",
					"[style.boldMana(Maximum "+Attribute.MANA_MAXIMUM.getName()+")] [style.boldTerrible(set to 1)]",
					"[style.boldTerrible(All shielding set to 0)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] unable to handle the amount of attribute enchantments infused into [npc.her] weapons, clothing, and tattoos, and so [npc.is] suffering from some extremely severe negative side-effects.");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int overBy = (int) (target.getEnchantmentPointsUsedTotal()-target.getAttributeValue(Attribute.ENCHANTMENT_LIMIT));
			return Main.game.isEnchantmentCapacityEnabled() && overBy>=20;
		}
	};
	
	public static AbstractStatusEffect CLOTHING_JINXED = new AbstractStatusEffect(80,
			"sealed clothing",
			"jinxed_clothing",
			PresetColour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"The enchantment on [npc.namePos] sealed clothing is vampyric in nature, and [npc.she] can feel its power draining a portion of [npc.her] aura...");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer()) {
				if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.jinxedClothingDiscovered)) {
					Main.game.getDialogueFlags().values.add(DialogueFlagValue.jinxedClothingDiscovered);
					AbstractClothing clothing = null;
					for(AbstractClothing c : new ArrayList<>(target.getClothingCurrentlyEquipped())) {
						if(c.isSealed()) {
							clothing = c;
							break;
						}
					}
					if(target.isPlayer() && !((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
						return "<p>"
									+ "As you finish fitting the "+clothing.getName()+" in place, you start to feel a strange warmth radiating from "+(clothing.getClothingType().isPlural()?"their":"its")+" surface."
									+ " Feeling a little uneasy about the manner of arcane enchantment that "+(clothing.getClothingType().isPlural()?"they":"it")+" must contain, you immediately try to take "
										+(clothing.getClothingType().isPlural()?"them":"it")+" off."
								+ "</p>"
								+ "<p>"
									+ "Taking hold of the "+clothing.getName()+", nothing seems to be wrong at first, but as you try to pull "+(clothing.getClothingType().isPlural()?"them":"it")+" off, you find out that you've made a big mistake."
									+ " A jolt of arcane energy suddenly flashes up through your body, and as the invasive force shoots its way into your mind, you find yourself unwittingly releasing your grip."
								+ "</p>"
								+ "<p>"
									+ "Gritting your teeth, you try once again to remove the offending article of clothing, only to find yourself instantly letting go as you try to pull "+(clothing.getClothingType().isPlural()?"them":"it")+" off."
									+ " No matter how much you struggle, all you're able to do is move the "+clothing.getName()
										+" around a little, and whenever it looks to be in danger of being removed, it moves back into its proper position with a mind of its own."
								+ "</p>"
								+ "<p>"
									+ "Eventually giving up, you decide to go and ask Lilaya what's going on with "+(clothing.getClothingType().isPlural()?"these":"this")
										+" <b style='color:"+PresetColour.RARITY_JINXED.toWebHexString()+";'>sealed</b> "+clothing.getName()+"."
									+ " Maybe she'll know a way to break the seal?"
								+ "</p>"
								+(!((PlayerCharacter) target).hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
										?((PlayerCharacter) target).startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)
										:"");
					
					} else {
						return "<p>"
									+ "As you finish fitting the "+clothing.getName()+" in place, you start to feel a strange warmth radiating from "+(clothing.getClothingType().isPlural()?"their":"its")+" surface."
									+ " Feeling a little uneasy about the manner of arcane enchantment that "+(clothing.getClothingType().isPlural()?"they":"it")+" must contain, you immediately try to take "
										+(clothing.getClothingType().isPlural()?"them":"it")+" off."
								+ "</p>"
								+ "<p>"
									+ "Taking hold of the "+clothing.getName()+", nothing seems to be wrong at first, but as you try to pull "+(clothing.getClothingType().isPlural()?"them":"it")+" off, you find out that you've made a big mistake."
									+ " A jolt of arcane energy suddenly flashes up through your body, and as the invasive force shoots its way into your mind, you find yourself unwittingly releasing your grip."
								+ "</p>"
								+ "<p>"
									+ "Gritting your teeth, you try once again to remove the offending article of clothing, only to find yourself instantly letting go as you try to pull "+(clothing.getClothingType().isPlural()?"them":"it")+" off."
									+ " No matter how much you struggle, all you're able to do is move the "+clothing.getName()
										+" around a little, and whenever it looks to be in danger of being removed, it moves back into its proper position with a mind of its own."
								+ "</p>"
								+ "<p>"
									+ "Lilaya's warning about sealed clothing suddenly shoots to the forefront of your mind, and you let out a groan as you realise that "
										+(clothing.getClothingType().isPlural()?"these ":"this ")+clothing.getName()
										+(clothing.getClothingType().isPlural()?" are":" is")
										+" <b style='color:"+PresetColour.RARITY_JINXED.toWebHexString()+";'>sealed</b>."
									+ " Remembering what Lilaya said, you should be able to remove the seal if you focus some of your absorbed essences into it..."
								+ "</p>";
					}
					
				} else {
					return "";
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if(c.isSealed()) {
					return true;
				}
			}
			return false;
		}
	};
	
	// OTHER:

	public static AbstractStatusEffect WELL_RESTED = new AbstractStatusEffect(80,
			"well rested",
			"wellRested",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.ATTRIBUTE_MANA,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 10f),
					new Value<>(Attribute.MANA_MAXIMUM, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "After having a good rest, [npc.name] [npc.verb(feel)] full of energy.");
			} else {
				return "";
			}
		}
	};
	
	public static AbstractStatusEffect WELL_RESTED_BOOSTED = new AbstractStatusEffect(80,
			"well rested (boosted)",
			"wellRestedBoosted",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.ATTRIBUTE_MANA,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 30f),
					new Value<>(Attribute.MANA_MAXIMUM, 30f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.hasTrait(Perk.JOB_UNEMPLOYED, true)) {
					return UtilText.parse(target, "Thanks to using [npc.her] ability of knowing how to get the most out of a good rest, [npc.name] currently [npc.verb(feel)] full of energy and vigour.");
				} else {
					return UtilText.parse(target, "Thanks to the upgraded emperor-size bed in [npc.her] room, [npc.name] [npc.has] managed to get a very comfortable rest, and now [npc.verb(feel)] full of energy and vigour.");
				}
			} else {
				return "";
			}
		}
	};
	
	public static AbstractStatusEffect WELL_RESTED_BOOSTED_EXTRA = new AbstractStatusEffect(80,
			"well rested (extra boosted)",
			"wellRestedBoostedExtra",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.ATTRIBUTE_MANA,
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 60f),
					new Value<>(Attribute.MANA_MAXIMUM, 60f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"Thanks to the upgraded emperor-size bed in [npc.her] room, combined with [npc.her] ability of knowing how best to get a good rest, [npc.name] now [npc.verb(feel)] as though [npc.sheIs] overflowing of energy and vigour.");
				
			} else {
				return "";
			}
		}
	};

//	public static AbstractStatusEffect SHOWER = new AbstractStatusEffect(80,
//			"recently showered",
//			"bath_minor",
//			PresetColour.ATTRIBUTE_HEALTH,
//			PresetColour.BASE_AQUA,
//			true,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
//					new Value<>(Attribute.MANA_MAXIMUM, 5f),
//					new Value<>(Attribute.DAMAGE_LUST, 5f)),
//			Util.newArrayListOfValues("[style.boldMinorGood(Doubles)] [style.colourHealth(health)] and [style.colourMana(aura)] regeneration rate")) {
//		@Override
//		public String getDescription(GameCharacter target) {
//			if(target!=null) {
//				return UtilText.parse(target, "Having recently taken the time to have a shower, [npc.name] [npc.verb(feel)] refreshed.");
//			} else {
//				return "";
//			}
//		}
//	};
	
//	public static AbstractStatusEffect BATH = new AbstractStatusEffect(80,
//			"recently bathed",
//			"bath",
//			PresetColour.ATTRIBUTE_HEALTH,
//			PresetColour.BASE_AQUA,
//			true,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.HEALTH_MAXIMUM, 10f),
//					new Value<>(Attribute.MANA_MAXIMUM, 10f),
//					new Value<>(Attribute.DAMAGE_LUST, 10f)),
//			Util.newArrayListOfValues("[style.boldGood(Triples)] [style.colourHealth(health)] and [style.colourMana(aura)] regeneration rate")) {
//		@Override
//		public String getDescription(GameCharacter target) {
//			if(target!=null) {
//				return UtilText.parse(target, "Having recently taken the time to relax in [npc.her] bath, [npc.name] [npc.verb(feel)] refreshed and rejuvenated.");
//			} else {
//				return "";
//			}
//		}
//	};

//	public static AbstractStatusEffect BATH_BOOSTED = new AbstractStatusEffect(80,
//			"recently bathed (spa)",
//			"bath_boosted",
//			PresetColour.ATTRIBUTE_HEALTH,
//			PresetColour.BASE_AQUA,
//			PresetColour.GENERIC_EXCELLENT,
//			true,
//			Util.newHashMapOfValues(
//					new Value<>(Attribute.HEALTH_MAXIMUM, 25f),
//					new Value<>(Attribute.MANA_MAXIMUM, 25f),
//					new Value<>(Attribute.DAMAGE_LUST, 15f)),
//			Util.newArrayListOfValues("[style.boldExcellent(Quadruples)] [style.colourHealth(health)] and [style.colourMana(aura)] regeneration rate")) {
//		@Override
//		public String getDescription(GameCharacter target) {
//			if(target!=null) {
//				return UtilText.parse(target, "Having recently taken the time to relax in [npc.her] spa, [npc.name] [npc.verb(feel)] like [npc.sheHas] been born anew!");
//			} else {
//				return "";
//			}
//		}
//	};
	
	public static AbstractStatusEffect OVERWORKED_1 = new AbstractStatusEffect(80,
			"slightly overworked",
			"overworked1",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, -10f),
					new Value<>(Attribute.MANA_MAXIMUM, -10f)),
			Util.newArrayListOfValues("While working:",
					"[style.boldBad(-0.05)] [style.colourAffection(Affection/hour)]",
					"[style.boldBad(-25%)] [style.colourExperience(experience)] gain chance")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"As a result of having a little too much work to do every day, [npc.name] sometimes [npc.verb(find)] [npc.herself] feeling a little fatigued.<br/>"
						+ " <i>(Gained from having between -1 to -9 daily stamina. Current daily stamina is [style.colourBad("+target.getDailySlaveJobStamina()+")])</i>");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSlave()
					&& target.getDailySlaveJobStamina()<0
					&& target.getDailySlaveJobStamina()>=-9;
		}
	};
	
	public static AbstractStatusEffect OVERWORKED_2 = new AbstractStatusEffect(80,
			"overworked",
			"overworked2",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, -25f),
					new Value<>(Attribute.MANA_MAXIMUM, -25f)),
			Util.newArrayListOfValues("[style.boldBad(-0.1)] [style.colourAffection(Affection/hour)]",
					"[style.boldBad(-50%)] [style.colourExperience(experience)] gain chance")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"As a result of having too much work to do every day, [npc.name] is starting to feel very fatigued.<br/>"
						+ " <i>(Gained from having between -10 to -19 daily stamina. Current daily stamina is [style.colourBad("+target.getDailySlaveJobStamina()+")])</i>");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSlave()
					&& target.getDailySlaveJobStamina()<-9
					&& target.getDailySlaveJobStamina()>=-19;
		}
	};
	
	public static AbstractStatusEffect OVERWORKED_3 = new AbstractStatusEffect(80,
			"severely overworked",
			"overworked3",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -50f),
					new Value<>(Attribute.MANA_MAXIMUM, -50f)),
			Util.newArrayListOfValues("[style.boldBad(-0.15)] [style.colourAffection(Affection/hour)]",
					"[style.boldBad(-75%)] [style.colourExperience(experience)] gain chance")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"As a result of having far too much work to do every day, [npc.name] is feeling incredibly fatigued.<br/>"
						+ " <i>(Gained from having -20 or less daily stamina. Current daily stamina is [style.colourBad("+target.getDailySlaveJobStamina()+")])</i>");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSlave()
					&& target.getDailySlaveJobStamina()<-19;
		}
	};
	
	public static AbstractStatusEffect GYM_FATIGUE = new AbstractStatusEffect(80,
			"post-workout fatigue",
			"gym_fatigue",
			PresetColour.ATTRIBUTE_HEALTH,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, -15f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "Having recently worked out, [npc.nameIsFull] feeling very fatigued, and will be unable to do any more gym work until [npc.sheHas] recovered.");
			} else {
				return "";
			}
		}
	};
	
	public static AbstractStatusEffect FATIGUED = new AbstractStatusEffect(80,
			"fatigued",
			"fatigued",
			PresetColour.ATTRIBUTE_HEALTH,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, -15f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			Util.newArrayListOfValues()) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "After a period of hard work, [npc.nameIsFull] feeling very fatigued, and will need to take some time to recover...");
			} else {
				return "";
			}
		}
	};
	
	// Utility status effect to display text of companions leaving:
	public static AbstractStatusEffect COMPANIONS_LEAVING = new AbstractStatusEffect(80,
			"Companions Leaving",
			"",
			PresetColour.BASE_MAGENTA,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return "";
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isPlayer();
		}
	};
	
	public static AbstractStatusEffect PSYCHOACTIVE = new AbstractStatusEffect(80,
			"Psychoactive Trip",
			"psychoactive",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("Open to <b style='color: " + PresetColour.PSYCHOACTIVE.toWebHexString() + ";'>Hypnotic Suggestion</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.isPlayer() && Math.random()<=Util.getModifiedDropoffValue((secondsPassed/60)*0.0075f, 0.5f)) {
				
				if(target.getPsychoactiveFluidsIngested().isEmpty()) {
					return "<p>"
								+ "You find yourself losing track of where you are as you struggle to overcome the psychoactive effects..."
							+ "</p>"
							+ "<p><i>"
							+ UtilText.returnStringAtRandom(
									"Suddenly, you find yourself back in your aunt Lily's museum."
										+ " One of the museum's staff is holding you by the [pc.hand] and pulling you into an empty room."
										+ " Once inside, they drop to their knees, grinning up at you as they lean forwards and plant a wet kiss on your exposed genitals...",
									"Suddenly, you find yourself face-to-face with your aunt Lily; the two of you standing in her old apartment."
										+ " With a giggle, she suddenly reaches down and grabs you by the [pc.hand], before pulling you into her bedroom."
										+ " Once inside, she slips off her dressing gown and steps forwards, pressing her lips against yours...",
									"Suddenly, you find yourself standing before a stunningly-beautiful demon."
										+ " She steps forwards, pressing her naked breasts against your body as her tail snakes up to rub against your groin."
										+ " Letting out a lewd moan, she presses her lips against yours...")
							+"</i></p>"
							+ "<p>"
								+ "With a gasp, you suddenly snap out of the hallucination."
								+ " Blushing, you feel a needy heat spreading throughout your groin..."
							+ "</p>"
							+target.incrementLust(25, false);
				} else {
					List<AbstractFluidType> list = new ArrayList<>(target.getPsychoactiveFluidsIngested());
					AbstractFluidType fluid = list.get(Util.random.nextInt(list.size()));
					String npcName = UtilText.generateSingularDeterminer(fluid.getRace().getName(false))+" "+fluid.getRace().getName(false);
					switch(fluid.getBaseType()) {
						case CUM:
							return "<p>"
										+ "You find yourself losing track of where you are as you struggle to overcome the psychoactive effects..."
									+ "</p>"
									+ "<p><i>"
									+ UtilText.returnStringAtRandom(
											"Suddenly, you find yourself back in your aunt Lily's museum."
												+ " You're kneeling before one of the museum's staff, "+npcName+", as you eagerly suck their cock."
												+ " After just a few moments, their shaft starts to twitch, and you let out a delighted moan as a thick stream of cum pours out into your mouth...",
											"Suddenly, you find yourself beside your aunt Lily; the two of you kneeling next to each other in the bedroom of her old apartment."
												+ " She's grinning in delight as she watches you suck the cock of the "+fluid.getRace().getName(false)+" before you."
												+ " As the thick shaft in your mouth starts to twitch, Lily reaches forwards and holds your head in place, making sure that the hot stream of cum spurts down your throat...",
											"Suddenly, you find yourself kneeling before a completely naked "+fluid.getRace().getName(false)+"."
												+ " They step forwards, reaching down to pull your head forwards as they force their hard cock into your mouth."
												+ " With a desperate moan, they instantly start cumming, filling your mouth with hot cum...")
									+"</i></p>"
									+ "<p>"
										+ "With a gasp, you suddenly snap out of the hallucination."
										+ " Blushing, you feel a needy heat spreading throughout your groin..."
									+ "</p>"
									+target.incrementLust(25, false);
						case GIRLCUM:
							return "<p>"
										+ "You find yourself losing track of where you are as you struggle to overcome the psychoactive effects..."
									+ "</p>"
									+ "<p><i>"
									+ UtilText.returnStringAtRandom(
											"Suddenly, you find yourself back in your aunt Lily's museum."
												+ " You're kneeling before one of the museum's staff, "+npcName+", as you eagerly lick and kiss their dripping-wet pussy."
												+ " After just a few moments, their cunt starts to spasm and twitch, and you let out a delighted moan as you lick up every drop of their delicious girlcum...",
											"Suddenly, you find yourself beside your aunt Lily; the two of you kneeling next to each other in the bedroom of her old apartment."
												+ " She's grinning in delight as she watches you lick and kiss the pussy of the "+fluid.getRace().getName(false)+" before you."
												+ " As their cunt starts to spasm and twitch, Lily reaches forwards and pushes your head forwards, making sure that you lick up every drop of their delicious girlcum...",
											"Suddenly, you find yourself kneeling before a completely naked "+fluid.getRace().getName(false)+"."
												+ " They step forwards, reaching down to pull your head forwards as they force their dripping-wet cunt against your [pc.lips+]."
												+ " With a desperate moan, they instantly start cumming, roughly forcing your face into their groin as they make you lick up every drop of their delicious girlcum...")
									+"</i></p>"
									+ "<p>"
										+ "With a gasp, you suddenly snap out of the hallucination."
										+ " Blushing, you feel a needy heat spreading throughout your groin..."
									+ "</p>"
									+target.incrementLust(25, false);
						case MILK:
							return "<p>"
										+ "You find yourself losing track of where you are as you struggle to overcome the psychoactive effects..."
									+ "</p>"
									+ "<p><i>"
									+ UtilText.returnStringAtRandom(
											"Suddenly, you find yourself back in your aunt Lily's museum."
												+ " You're sitting in the lap of one of the museum's staff, "+npcName+", as you eagerly kiss and suckle their engorged teats."
												+ " After just a few moments, a steady stream of milk starts to flow into your mouth, and you let out a delighted moan as you gulp down the delicious liquid...",
											"Suddenly, you find yourself in the lap of your aunt Lily; the two of you sitting on her bed in her old apartment."
												+ " She's grinning down at you in delight as you kiss and suckle her engorged teats."
												+ " After just a few moments, a steady stream of milk starts to flow into your mouth, and you let out a delighted moan as you gulp down the delicious liquid...",
											"Suddenly, you find yourself sitting in the lap of a completely naked "+fluid.getRace().getName(false)+"."
												+ " They reach down to pull your head into their huge breasts as they force one of their engorged teats against your [pc.lips+]."
												+ " With a desperate moan, you start kissing and suckling their puffy nipples, and after just a few moments, a steady stream of milk starts to flow into your mouth...")
									+"</i></p>"
									+ "<p>"
										+ "With a gasp, you suddenly snap out of the hallucination."
										+ " Blushing, you feel a needy heat spreading throughout your groin..."
									+ "</p>"
									+target.incrementLust(25, false);
					}
				}
				return "";
			} else {
				return "";
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You're feeling very peculiar at the moment... Strange visions keep flashing before your [pc.eyes], and you aren't quite sure of your surroundings...";
			} else {
				return UtilText.parse(target, "[npc.name] is feeling very peculiar at the moment... Strange visions keep flashing before [npc.her] [npc.eyes], and [npc.she] doesn't seem to be quite sure of [npc.her] surroundings...");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.removePsychoactiveEffects();
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CLEANED_MASSAGED = new AbstractStatusEffect(80,
			"Recently massaged",
			"cleaned_massage",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 25f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Having recently received a massage, [npc.nameIsFull] feeling extremely relaxed and limber!");
		}
	};
	
	public static AbstractStatusEffect CLEANED_SHOWER = new AbstractStatusEffect(80,
			"Recently showered",
			"cleaned_shower",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.BASE_AQUA,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.MANA_MAXIMUM, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 5f)),
			Util.newArrayListOfValues(
					"[style.boldMinorGood(Doubles)] [style.colourHealth(health)] and [style.colourMana(aura)] regeneration rate")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Having recently taken the time to have a shower, [npc.name] [npc.verb(feel)] refreshed!");
		}
	};
	
	public static AbstractStatusEffect CLEANED_BATH = new AbstractStatusEffect(80,
			"Recently bathed",
			"cleaned_bath",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.BASE_AQUA,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 10f),
					new Value<>(Attribute.MANA_MAXIMUM, 10f),
					new Value<>(Attribute.DAMAGE_LUST, 10f)),
			Util.newArrayListOfValues(
					"[style.boldGood(Triples)] [style.colourHealth(health)] and [style.colourMana(aura)] regeneration rate")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Having recently taken the time to relax in [npc.her] bath, [npc.name] [npc.verb(feel)] refreshed and rejuvenated.");
		}
	};
	
	public static AbstractStatusEffect CLEANED_SPA = new AbstractStatusEffect(80,
			"Spa soak",
			"cleaned_spa",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.BASE_AQUA,
			PresetColour.ATTRIBUTE_MANA,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.HEALTH_MAXIMUM, 25f),
					new Value<>(Attribute.MANA_MAXIMUM, 25f),
					new Value<>(Attribute.DAMAGE_LUST, 15f)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Quadruples)] [style.colourHealth(health)] and [style.colourMana(aura)] regeneration rate")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Having recently taken the time to relax in the spa, [npc.name] [npc.verb(feel)] like [npc.sheHas] been born anew!");
		}
	};
	
	public static AbstractStatusEffect LOLLIPOP_SUCKING = new AbstractStatusEffect(80,
			"sucking lollipop",
			"lollipop",
			PresetColour.CLOTHING_PINK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] playfully sucking on a lollipop, and every time someone looks at [npc.herHim], [npc.she] [npc.verb(purse)] [npc.her] [npc.lips] and [npc.verb(make)] a show of kissing it.");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SMOKING = new AbstractStatusEffect(80,
			"smoking",
			"smoking",
			PresetColour.CLOTHING_ORANGE,
			PresetColour.CLOTHING_BRASS,
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 10f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] currently smoking a cigarette. Every time [npc.she] [npc.verb(exhale)], a faint white cloud forms in front of [npc.her] face, causing [npc.her] immediate surroundings to smell strongly of burning plant matter.");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(RECENTLY_SMOKED, 4 * 60 * 60);
			return "";
		}
	};
	
	public static AbstractStatusEffect RECENTLY_SMOKED = new AbstractStatusEffect(80,
			"recently smoked",
			"recentlySmoked",
			PresetColour.CLOTHING_ORANGE,
			PresetColour.CLOTHING_BRASS,
			PresetColour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 10f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameHasFull] recently smoked a cigarette, which is obvious to anyone who gets too near to [npc.herHim], as [npc.she] [npc.verb(smell)] strongly of burning plant matter.");
		}
	};
	
	public static AbstractStatusEffect DRUNK_1 = new AbstractStatusEffect(80,
			"Intoxicated I - Tipsy",
			"drunk1",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<>(Attribute.MAJOR_ARCANE, -2f),
					new Value<>(Attribute.DAMAGE_LUST, 5f),
					new Value<>(Attribute.RESISTANCE_LUST, -1f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			target.incrementAlcoholLevel(-((secondsPassed/60)*(1f/(60f*6)))); // alcohol level will completely go after 6 hours
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return (UtilText.parse(target, "After recently drinking an alcoholic liquid, [npc.nameIsFull] feeling a little tipsy...<br/>"
					+ "Intoxication: "+Units.round(target.getIntoxicationPercentage(), 1)+"%"));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.ONE_TIPSY;
		}
	};
	
	public static AbstractStatusEffect DRUNK_2 = new AbstractStatusEffect(80,
			"Intoxicated II - Merry",
			"drunk2",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.RESISTANCE_LUST, -5f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			return (UtilText.parse(target, "After recently drinking an alcoholic liquid, [npc.nameIsFull] feeling quite merry...<br/>"
					+ "Intoxication: "+Units.round(target.getIntoxicationPercentage(), 1)+"%"));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.TWO_MERRY;
		}
	};
	
	public static AbstractStatusEffect DRUNK_3 = new AbstractStatusEffect(80,
			"Intoxicated III - Drunk",
			"drunk3",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.DAMAGE_LUST, 5f),
					new Value<>(Attribute.RESISTANCE_LUST, -10f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			return (UtilText.parse(target, "After recently drinking an alcoholic liquid, [npc.nameIsFull] feeling quite drunk...<br/>"
					+ "Intoxication: "+Units.round(target.getIntoxicationPercentage(), 1)+"%"));
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(3, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>You're obviously drunk and some might think you're easy prey.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.THREE_DRUNK;
		}
	};
	
	public static AbstractStatusEffect DRUNK_4 = new AbstractStatusEffect(80,
			"Intoxicated IV - Hammered",
			"drunk4",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<>(Attribute.MAJOR_ARCANE, -10f),
					new Value<>(Attribute.DAMAGE_LUST, -5f),
					new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			return (UtilText.parse(target, "After recently drinking an alcoholic liquid, [npc.nameIsFull] feeling absolutely hammered...<br/>"
					+ "Intoxication: "+Units.round(target.getIntoxicationPercentage(), 1)+"%"));
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(3, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>You're obviously drunk and some might think you're easy prey.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.FOUR_HAMMERED;
		}
	};
	
	public static AbstractStatusEffect DRUNK_5 = new AbstractStatusEffect(80,
			"Intoxicated V - Wasted",
			"drunk5",
			PresetColour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<>(Attribute.MAJOR_ARCANE, -15f),
					new Value<>(Attribute.DAMAGE_LUST, -10f),
					new Value<>(Attribute.RESISTANCE_LUST, -20f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed, totalSecondsPassed);
		}
		@Override
		public String getDescription(GameCharacter target) {
			return (UtilText.parse(target, "After recently drinking an alcoholic liquid, [npc.nameIsFull] feeling completely wasted...<br/>"
					+ "Intoxication: "+Units.round(target.getIntoxicationPercentage(), 1)+"%"));
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(3, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>You're obviously drunk and some might think you're easy prey.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.FIVE_WASTED;
		}
	};
	
	public static AbstractStatusEffect ADDICTIONS = new AbstractStatusEffect(80,
			"addictions",
			"addictions",
			PresetColour.BASE_CRIMSON,
			false,
			null,
			null) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			extraEffects.clear();
			
			for(Addiction addiction : target.getAddictions()) {
				long oneDayLater = addiction.getLastTimeSatisfied() + (24 * 60);
				long now = Main.game.getMinutesPassed();
				long timeLeft = oneDayLater - now;
				long hoursLeft = timeLeft / 60;
				long minutesLeft = timeLeft % 60;
				AbstractRace fluidRace = addiction.getFluid().getRace();
				extraEffects.add("<b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: "
						+ (timeLeft > 0
								?" [style.colourGood("+hoursLeft+":"+String.format("%02d", minutesLeft)+")]"
								:" [style.boldArcane(Withdrawal!)]"));
			}
			
			return extraEffects;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "You are currently addicted to "+Util.intToString(target.getAddictions().size())+(target.getAddictions().size()==1?" type of fluid":" types of fluids")+"!"
							+ " After going for more than 24 hours without getting a fix, you will start to suffer from withdrawal symptoms."
							+ " <i>Addictions can be cleared by using '"+ItemType.ADDICTION_REMOVAL.getName(false)+"'.</i>";
				} else {
					return UtilText.parse(target,
							"[npc.Name] is currently addicted to "+Util.intToString(target.getAddictions().size())+(target.getAddictions().size()==1?" type of fluid":" types of fluids")+"!"
									+ " After going for more than 24 hours without getting a fix, [npc.she] will start to suffer from withdrawal symptoms."
									+ " <i>Addictions can be cleared by using '"+ItemType.ADDICTION_REMOVAL.getName(false)+"'.</i>");
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.getAddictions().isEmpty();
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_1 = new AbstractStatusEffect(80,
			"Mild Withdrawal",
			"withdrawal1",
			PresetColour.CORRUPTION_STAGE_ONE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -2f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -2f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -2f),
					new Value<>(Attribute.MANA_MAXIMUM, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("You are suffering mild withdrawal from:");
				} else {
					sb.append(UtilText.parse(target, "[npc.Name] is suffering mild withdrawal from:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long oneDayLater = addiction.getLastTimeSatisfied() + (24 * 60);
					long twoDaysLater = oneDayLater + (24 * 60);
					long now = Main.game.getMinutesPassed();
					
					if(oneDayLater <= now && now < twoDaysLater) {
						long timeLeft = twoDaysLater - now;
						long hoursLeft = timeLeft / 60;
						long minutesLeft = timeLeft % 60;
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+ "[style.boldArcane(Addictive)]"
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: "
								+ " [style.boldArcane(worsens in)] "+hoursLeft+":"+String.format("%02d", minutesLeft));
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long oneDayLater = addiction.getLastTimeSatisfied() + (24 * 60);
				long twoDaysLater = oneDayLater + (24 * 60);
				long now = Main.game.getMinutesPassed();
				
				if (oneDayLater <= now && now < twoDaysLater) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_2 = new AbstractStatusEffect(80,
			"Noticeable Withdrawal",
			"withdrawal2",
			PresetColour.CORRUPTION_STAGE_TWO,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -5f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -5f),
					new Value<>(Attribute.MANA_MAXIMUM, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("You are suffering noticeable withdrawal from:");
				} else {
					sb.append(UtilText.parse(target, "[npc.Name] is suffering noticeable withdrawal from:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long twoDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 2);
					long threeDaysLater = twoDaysLater + (24 * 60);
					long now = Main.game.getMinutesPassed();
					
					if (twoDaysLater <= now && now < threeDaysLater) {
						long timeLeft = threeDaysLater - now;
						long hoursLeft = timeLeft / 60;
						long minutesLeft = timeLeft % 60;
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+ "[style.boldArcane(Addictive)]"
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: "
								+ " [style.boldArcane(worsens in)] "+hoursLeft+":"+String.format("%02d", minutesLeft));
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long twoDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 2);
				long threeDaysLater = twoDaysLater + (24 * 60);
				long now = Main.game.getMinutesPassed();
				
				if (twoDaysLater <= now && now < threeDaysLater) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_3 = new AbstractStatusEffect(80,
			"Strong Withdrawal",
			"withdrawal3",
			PresetColour.CORRUPTION_STAGE_THREE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -10f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -10f),
					new Value<>(Attribute.MANA_MAXIMUM, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("You are suffering strong withdrawal from:");
				} else {
					sb.append(UtilText.parse(target, "[npc.Name] is suffering strong withdrawal from:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long threeDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 3);
					long fourDaysLater = threeDaysLater + (24 * 60);
					long now = Main.game.getMinutesPassed();
					
					if (threeDaysLater <= now && now < fourDaysLater) {
						long timeLeft = fourDaysLater - now;
						long hoursLeft = timeLeft / 60;
						long minutesLeft = timeLeft % 60;
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+ "[style.boldArcane(Addictive)]"
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: "
								+ " [style.boldArcane(worsens in)] "+hoursLeft+":"+String.format("%02d", minutesLeft));
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long threeDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 3);
				long fourDaysLater = threeDaysLater + (24 * 60);
				long now = Main.game.getMinutesPassed();
				
				if (threeDaysLater <= now && now < fourDaysLater) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_4 = new AbstractStatusEffect(80,
			"Severe Withdrawal",
			"withdrawal4",
			PresetColour.CORRUPTION_STAGE_FOUR,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -25f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -25f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -25f),
					new Value<>(Attribute.MANA_MAXIMUM, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("You are suffering severe withdrawal from:");
				} else {
					sb.append(UtilText.parse(target, "[npc.Name] is suffering severe withdrawal from:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long fourDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 4);
					long fiveDaysLater = fourDaysLater + (24 * 60);
					long now = Main.game.getMinutesPassed();
					
					if (fourDaysLater <= now && now < fiveDaysLater) {
						long timeLeft = fiveDaysLater - now;
						long hoursLeft = timeLeft / 60;
						long minutesLeft = timeLeft % 60;
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+ "[style.boldArcane(Addictive)]"
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>: "
								+ " [style.boldArcane(worsens in)] "+hoursLeft+":"+String.format("%02d", minutesLeft));
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long fourDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 4);
				long fiveDaysLater = fourDaysLater + (24 * 60);
				long now = Main.game.getMinutesPassed();
				
				if (fourDaysLater <= now && now < fiveDaysLater) {
					return true;
				}
			}
			return false;
		}
	};
	
	public static AbstractStatusEffect WITHDRAWAL_5 = new AbstractStatusEffect(80,
			"Intense Withdrawal",
			"withdrawal5",
			PresetColour.CORRUPTION_STAGE_FIVE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, -50f),
					new Value<>(Attribute.MAJOR_PHYSIQUE, -50f),
					new Value<>(Attribute.HEALTH_MAXIMUM, -50f),
					new Value<>(Attribute.MANA_MAXIMUM, -50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("You are suffering intense withdrawal from:");
				} else {
					sb.append(UtilText.parse(target, "[npc.Name] is suffering intense withdrawal from:"));
				}

				for(Addiction addiction : target.getAddictions()) {
					long fiveDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 5);
					long now = Main.game.getMinutesPassed();
					
					if (fiveDaysLater <= now) {
						AbstractRace fluidRace = addiction.getFluid().getRace();
						sb.append("<br/>"
								+ "[style.boldArcane(Addictive)]"
								+ " <b style='color:"+fluidRace.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fluidRace.getName(fluidRace!=Race.DEMON))+" "+addiction.getFluid().getBaseType().getNames().get(0)+"</b>.");
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(Addiction addiction : target.getAddictions()) {
				long fiveDaysLater = addiction.getLastTimeSatisfied() + (24 * 60 * 5);
				long now = Main.game.getMinutesPassed();
				
				if (fiveDaysLater <= now) {
					return true;
				}
			}
			return false;
		}
	};

	public static AbstractStatusEffect MENOPAUSE = new AbstractStatusEffect(80,
			"Menopause",
			"menopause",
			PresetColour.BASE_CRIMSON,
			false,
			null,
			Util.newArrayListOfValues("[style.colourBad(Total infertility)]")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Due to being over fifty-two years of age, [npc.nameHasFull] undergone menopause, and as [npc.sheIsFull] not a demon, [npc.sheHasFull] been rendered completely unable to bear children.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (Main.getProperties().hasValue(PropertyValue.ageContent) || target.isUnique())
					&& target.hasVagina()
					&& (target.isPlayer()
							?target.getAgeValue()>=52+Game.TIME_SKIP_YEARS
							:target.getAgeValue()>=52)
					&& (target.getSubspecies()==Subspecies.ANGEL || target.getSubspeciesOverride()==null) // Angels and demons are immune
					&& !(target.isElemental());
		}
	};
	
	public static AbstractStatusEffect PREGNANT_0 = new AbstractStatusEffect(80,
			"risk of pregnancy",
			"pregnancy0",
			PresetColour.GENERIC_ARCANE,
			true,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(Main.game.isInNewWorld()) {
				return UtilText.parse(target,
						"After recently having unprotected sex, there's a risk that [npc.name] will get pregnant!"
							+ " Due to the fact that the arcane accelerates people's pregnancies, [npc.she]'ll know if [npc.sheIs] pregnant within a matter of hours...");
			} else {
				return UtilText.parse(target,
						"After recently having unprotected sex, there's a risk that [npc.name] will get pregnant!");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			
			if (target.isPregnant()) {
				target.addStatusEffect(PREGNANT_1, 60 * 60 * (72 + Util.random.nextInt(13)));
				target.loadImages(true); // Reload images for pregnant versions
				
				if (target.isPlayer() && !((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
					if(target.hasFetish(Fetish.FETISH_PREGNANCY)) {
						sb.append("<p>"
								+ "For the last few hours, your belly has been gradually swelling."
								+ " The progress was so slow that you didn't even realise anything was happening, but as you glance down at your stomach, there's no mistaking it."
								+ " You're pregnant."
								+ " You experimentally start stroking your abdomen, making soft little gasps as the realisation of what's happening starts to sink in."
							+ "</p>"
							+ "<p>"
								+ "[pc.thought(I-I'm pregnant?"
									+ "<br/>"
									+ "..."
									+ "<br/>"
									+ "Oh my God! Yes! <b>I'm pregnant!</b>)]"
							+ "</p>"
							+ "<p>"
								+ "The overwhelming happiness you feel at not only discovering that you're pregnant, but also that you're showing physical signs after only a few hours, washes over you like a crashing wave of pure ecstasy."
								+ " You feel tears of joy welling up in your eyes as you lovingly cradle your swollen belly."
							+ "</p>"
							+ "<p>"
								+ "[pc.thought(This is the best feeling ever!"
										+ "<br/>"
										+ "If only aunt Lily were here, I bet she'd be so proud!"
										+ "<br/>"
										+ "Wait! Of course! <b>Lilaya!</b> She'll want to see this too!)]"
							+ "</p>"
							+ "<p>"
								+ (target.getBodyMaterial()==BodyMaterial.SLIME
									?"Taking a closer look at your swollen, slimy stomach, you suddenly realise that you can see "
											+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" little slime core"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing in "
											+ (target.hasVagina()?"the place where your womb should be.":"your belly.")
										+ " You can't help but let out a delighted squeal of happiness as you see your "
											+(target.getPregnantLitter().getTotalLitterCount()==1?"child":"children")+" growing inside of you, and spend the next few minutes stroking and rubbing your wonderfully-swollen abdomen in a state of absolute bliss."
										+ " Eventually, however, you decide that you should probably go and see Lilaya, so that she can help you figure out all the details of giving birth."
									:"After a little while of stroking and rubbing your wonderfully-swollen abdomen, you start to calm down a little."
										+ " You decide that you should probably go and see Lilaya, so that she can help you figure out all the details of giving birth.")
							+ "</p>"
							+ "<p style='text-align:center;'>"
								+ "<b style='color:"+ PresetColour.GENERIC_SEX.toWebHexString() + ";'>You're pregnant!</b>"
							+ "</p>");
						
					} else {
						sb.append("<p>"
									+ "For the last few hours, your belly has been gradually swelling."
									+ " The progress was so slow that you didn't even realise anything was happening, but as you glance down at your stomach, there's no mistaking it."
									+ " You're pregnant."
									+ " You experimentally start stroking your abdomen, making soft little gasps as the realisation of what's happening starts to sink in."
								+ "</p>"
								+ "<p>"
									+ "[pc.thought(I-I'm pregnant?"
										+ "<br/>"
										+ "..."
										+ "<br/>"
										+ "Oh my God! <b>I'm pregnant!</b>)]"
								+ "</p>"
								+ "<p>"
									+ "The sudden shock of not only discovering that you're pregnant, but also that you're showing physical signs after only a few hours, hits you like a sledgehammer."
									+ " Despite your best efforts, you feel yourself starting to hyperventilate as you walk around in little circles, alternating between cradling your head and stomach."
								+ "</p>"
								+ "<p>"
									+ "[pc.thought(What do I do? What do I do? What do I do?"
											+ "<br/>"
											+ "If only aunt Lily were here!"
											+ "<br/>"
											+ "Wait! Of course! <b>Lilaya!</b> She'll know what to do!)]"
								+ "</p>"
								+ "<p>"
									+ (target.getBodyMaterial()==BodyMaterial.SLIME
										?"As you take one last look at your swollen, slimy stomach, you suddenly realise that you can see "
												+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" little slime core"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing in "
												+ (target.hasVagina()?"the place where your womb should be.":"your belly.")
											+ " You can't help but let out a shocked cry as you see your "+(target.getPregnantLitter().getTotalLitterCount()==1?"child":"children")
												+" growing inside of you, and spend the next few minutes stroking and rubbing your swollen abdomen in a state of panic."
											+ " Eventually, however, you start to calm down a little, and decide that you should probably go and see Lilaya as soon as possible."
										:"You start to calm down a little as the initial shock starts to wear off."
											+ " If anyone knows what to do, it'll be Lilaya.")
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ "<b style='color:"+ PresetColour.GENERIC_SEX.toWebHexString() + ";'>You're pregnant!</b>"
								+ "</p>");
					}
					
				} else {
					sb.append("<p>"
							+ "For the last couple of hours, your belly has been gradually swelling."
							+ " The progress was so slow that you didn't even realise anything was happening, but as you glance down at your stomach, there's no mistaking it."
							+ " You're pregnant again."
							+ " You start stroking your abdomen, making soft little gasps as the familiar feeling of being knocked up returns to you."
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?"</p>"
								+ "<p>"
									+ "Looking a little closer at your swollen, slimy stomach, you see "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" little slime core"
										+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing in "+ (target.hasVagina()?"the place where your womb should be.":"your belly.")
									+ " You can't help but let out a happy little sigh as you see your "+(target.getPregnantLitter().getTotalLitterCount()==1?"child":"children")
									+" growing inside of you, and spend the next few minutes stroking and rubbing your swollen abdomen in a state of motherly bliss."
								:"")
						+ "</p>"
						+ "<p>"
							+ (target.hasFetish(Fetish.FETISH_PREGNANCY)
									?"[pc.thought(Haha! Yes! I got pregnant again! This is the best feeling ever...)]"
									:"[pc.thought(Mmm... Looks like I got pregnant again...)]")
						+ "</p>"
						+ "<p>"
							+ (target.hasFetish(Fetish.FETISH_PREGNANCY)
								?"Knowing what you're in for, you let out a delighted laugh before carrying on your way."
								:"Knowing what you're in for, you let out a contented sigh and start carrying on your way.")
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString()+ ";'>You're pregnant!</b>"
						+ "</p>");
				}
				
				// Remove cum inflation:
				float fluidAmount = target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
				float fluidAmountUrethra = target.getTotalFluidInArea(SexAreaOrifice.URETHRA_VAGINA);
				target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, -fluidAmount);
				target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, -fluidAmountUrethra);
				
				sb.append("<p>");
				if(fluidAmount>0) {
					if(target.getBodyMaterial()==BodyMaterial.SLIME) {
						sb.append("[style.italicsSex(The swelling of your pregnant bump forces your body to convert all of the cum in your pussy"+(fluidAmountUrethra>0?" and its urethra":"")+" into more slime.)]");
					} else {
						sb.append("[style.italicsSex(The swelling of your pregnant bump forces your body to expel all of the cum in your pussy"+(fluidAmountUrethra>0?" and its urethra":"")+".)]");
					}
					
				} else if(fluidAmountUrethra>0) {
					if(target.getBodyMaterial()==BodyMaterial.SLIME) {
						sb.append("[style.italicsSex(The swelling of your pregnant bump forces your body to convert all of the cum in your pussy's urethra into more slime.)]");
					} else {
						sb.append("[style.italicsSex(The swelling of your pregnant bump forces your body to expel all of the cum in your pussy's urethra.)]");
					}
				}
				sb.append("</p>");
				
			} else {
				target.endPregnancy(false);
				sb.append("<p>"
							+ "Enough time has passed now for you to be sure that you're in the clear."
							+ " There's no sign of any bump in your belly,"+(target.getBodyMaterial()==BodyMaterial.SLIME?" or of any slime cores growing inside of you,":"")
								+" and you realise that despite having unprotected sex, you managed to avoid getting pregnant."
						+ "</p>"
						+ "<p>"
							+ (target.hasFetish(Fetish.FETISH_PREGNANCY)
								?"[pc.thought(Damn it...)]"
								:"[pc.thought(Well, that's a relief...)]")
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>You aren't pregnant!</b>"
						+ "</p>");	
			}

			if(target.isPlayer()) {
				return sb.toString();
			} else {
				return "";
			}
		}
		@Override
		public String applyPostRemovalStatusEffect(GameCharacter target) {
			if(!target.isPregnant()) {
				target.performImpregnationCheck(false);
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PREGNANT_1 = new AbstractStatusEffect(80,
			"pregnant",
			"pregnancy1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues("-5% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] been impregnated!"
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?" Through the [npc.skinColour] [npc.skin] that makes up [npc.her] body, you can see "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" little slime core"
									+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing inside of [npc.herHim]."
								:" Due to the fact that the arcane accelerates people's pregnancies, [npc.she]'ll move onto the next stage in a matter of days."));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {

			target.addStatusEffect(PREGNANT_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			
			boolean breastGrowth = false;
			if(Main.getProperties().pregnancyBreastGrowth>0 && target.getBreastRawSizeValue()<Main.getProperties().pregnancyBreastGrowthLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyBreastGrowth - Main.getProperties().pregnancyBreastGrowthVariance + Util.random.nextInt(Main.getProperties().pregnancyBreastGrowthVariance*2 + 1));
				
				if(target.getBreastRawSizeValue() + valueIncrease > Main.getProperties().pregnancyBreastGrowthLimit) {
					breastGrowth = true;
					target.setBreastSize(Main.getProperties().pregnancyBreastGrowthLimit);
				} else {
					breastGrowth = true;
					target.incrementBreastSize(valueIncrease);
				}
			}
			
			boolean udderGrowth = false;
			if(target.hasBreastsCrotch() && Main.getProperties().pregnancyUdderGrowth>0 && target.getBreastCrotchRawSizeValue()<Main.getProperties().pregnancyUdderGrowthLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyUdderGrowth - Main.getProperties().pregnancyBreastGrowthVariance + Util.random.nextInt(Main.getProperties().pregnancyBreastGrowthVariance*2 + 1));
				
				if(target.getBreastCrotchRawSizeValue() + valueIncrease > Main.getProperties().pregnancyUdderGrowthLimit) {
					udderGrowth = true;
					target.setBreastCrotchSize(Main.getProperties().pregnancyUdderGrowthLimit);
				} else {
					udderGrowth = true;
					target.incrementBreastCrotchSize(valueIncrease);
				}
			}
			
			if(!target.isPlayer()) {
				return "";
			}
			
			if (!((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				return "<p>"
							+ "Even though the change has been gradual, you're suddenly hit by the realisation that your belly has swollen to a massive size."
							+ " You can't resist rubbing your hands over the huge bump in your abdomen, and you wonder just how big it's going to get."
							+ " As this is your first time getting pregnant, you're not quite sure what to expect, but you're reassured as you remember that Lilaya's always there to help."
						+ "</p>"
						+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?"<p>"
									+ "Clearly visible through the translucent slime which your body is made up of, you see that the "
										+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" slime core"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing inside of you have gotten a lot larger..."
								+ "</p>"
								:"")
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>You're now heavily pregnant!</b>"
						+ "</p>"
						+(breastGrowth
								? "<p><i>"
										+"Your breasts have swollen and grown larger as your body prepares to start lactating."
										+ " You now have [style.boldSex([pc.breastSize]"  + (target.getBreastRawSizeValue()>CupSize.AA.getMeasurement()?", "+target.getBreastSize().getCupSizeName()+"-cup":"") + " breasts)]!"
									+ "</i></p>"
								:"")
						+(udderGrowth
								? "<p><i>"
										+"Your [pc.udders] have swollen and grown larger as your body prepares to start lactating."
										+ " You now have [style.boldSex([pc.udderSize]"  + (target.getBreastCrotchRawSizeValue()>CupSize.AA.getMeasurement()?", "+target.getBreastCrotchSize().getCupSizeName()+"-cup":"") + " [pc.udders])]!"
									+ "</i></p>"
								:"");
			} else {
				return "<p>"
							+ "Even though the change has been gradual, you're suddenly hit by the familiar realisation that your belly has swollen to a massive size."
							+ " You can't resist rubbing your hands over the huge bump in your abdomen, smiling fondly at the comforting feeling."
							+ " Having been through all this before, you know that you've still got a way to go before you're ready to give birth."
						+ "</p>"
						+ (target.getBodyMaterial()==BodyMaterial.SLIME
							?"<p>"
								+ "Clearly visible through the translucent slime which your body is made up of, you see that the "
									+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" slime core"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing inside of you have gotten a lot larger..."
							+ "</p>"
							:"")
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>You're now heavily pregnant!</b>"
						+ "</p>"
						+(breastGrowth
								? "<p><i>"
										+"Your breasts have swollen and grown larger as your body prepares to start lactating."
										+ " You now have [style.boldSex([pc.breastSize]"  + (target.getBreastRawSizeValue()>CupSize.AA.getMeasurement()?", "+target.getBreastSize().getCupSizeName()+"-cup":"") + " breasts)]!"
									+ "</i></p>"
								:"")
						+(udderGrowth
								? "<p><i>"
										+"Your [pc.udders] have swollen and grown larger as your body prepares to start lactating."
										+ " You now have [style.boldSex([pc.udderSize]"  + (target.getBreastCrotchRawSizeValue()>CupSize.AA.getMeasurement()?", "+target.getBreastCrotchSize().getCupSizeName()+"-cup":"") + " [pc.udders])]!"
									+ "</i></p>"
								:"");
			}
		}

		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PREGNANT_2 = new AbstractStatusEffect(80,
			"heavily pregnant",
			"pregnancy2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues("-10% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NamePos] stomach has swollen considerably, making it clearly obvious to anyone who glances [npc.her] way that [npc.sheIs] expecting to give birth soon."
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?" Through the [npc.skinColour] [npc.skin] that makes up [npc.her] body, you can see "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" little slime core"
									+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing inside of [npc.herHim]..."
								:" Due to the fact that the arcane accelerates people's pregnancies, [npc.she]'ll move onto the final stage in a matter of days."));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {

			target.setTimeProgressedToFinalPregnancyStage(Main.game.getSecondsPassed());

			boolean lactationIncrease = false;
			if(Main.getProperties().pregnancyLactationIncrease>0 && target.getBreastRawMilkStorageValue()<Main.getProperties().pregnancyLactationLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyLactationIncrease - Main.getProperties().pregnancyLactationIncreaseVariance + Util.random.nextInt(Main.getProperties().pregnancyLactationIncreaseVariance*2 + 1));
				
				if(target.getBreastRawMilkStorageValue() + valueIncrease > Main.getProperties().pregnancyLactationLimit) {
					lactationIncrease = true;
					target.setBreastMilkStorage(Main.getProperties().pregnancyLactationLimit);
				} else {
					lactationIncrease = true;
					target.incrementBreastMilkStorage(valueIncrease);
				}
			}

			boolean lactationUddersIncrease = false;
			if(target.hasBreastsCrotch() && Main.getProperties().pregnancyUdderLactationIncrease>0 && target.getBreastCrotchRawMilkStorageValue()<Main.getProperties().pregnancyUdderLactationLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyUdderLactationIncrease - Main.getProperties().pregnancyLactationIncreaseVariance + Util.random.nextInt(Main.getProperties().pregnancyLactationIncreaseVariance*2 + 1));
				
				if(target.getBreastCrotchRawMilkStorageValue() + valueIncrease > Main.getProperties().pregnancyUdderLactationLimit) {
					lactationUddersIncrease = true;
					target.setBreastCrotchMilkStorage(Main.getProperties().pregnancyUdderLactationLimit);
				} else {
					lactationUddersIncrease = true;
					target.incrementBreastCrotchMilkStorage(valueIncrease);
				}
			}
			
			if(!target.isPlayer()) {
				return "";
			}
			
			if (!((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				return "<p>"
							+ "By now, your stomach has completely ballooned out in front of you, and you're having to arch your back and support yourself with one hand as you walk around."
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?" Clearly visible through the translucent slime which your body is made up of, you see that the "
										+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" slime core"
										+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing inside of you "+(target.getPregnantLitter().getTotalLitterCount()==1?"has":"have")
										+" grown to be just as large as your own, and you know that you're now ready to give birth."
								:(target.getVaginaType()==VaginaType.HARPY
									?" Although you can feel the hard shells of your clutch of eggs pressing out against the inner walls of your womb, you don't find the sensation to be in any way uncomfortable."
										+ " If anything, the feeling only seems to be boosting your maternal instincts, and you often catch yourself daydreaming about laying and incubating your eggs."
									:" Some time in the last couple of hours, you felt a strange rumble in your pregnant bump, and after panicking for a little while, you realised that it was your offspring kicking about in your womb."
										+ " You keep feeling another kick every now and then, and you know that you're ready to give birth."))
						+ "</p>"
						+ "<p>"
							+ UtilText.parseThought("I really should go and see Lilaya...", Main.game.getPlayer())
						+ "</p>"
						+(lactationIncrease
								? "<p><i>"
										+"Your breasts have gotten noticeably heavier, and as you softly stroke the round bump in your belly, you feel droplets of [pc.milk] beading up on your engorged teats."
										+ " You are now able to produce [style.boldSex(" + target.getBreastMilkStorage().getDescriptor() + " [pc.milk] ("+ Units.fluid(target.getBreastRawMilkStorageValue(), Units.UnitType.LONG)+"))]!"
									+ "</i></p>"
								:"")
						+(lactationUddersIncrease
								? "<p><i>"
										+"Your [pc.udders] have gotten noticeably heavier, and as you walk, you feel droplets of [pc.crotchMilk] beading up on your engorged teats."
										+ " You are now able to produce [style.boldSex(" + target.getBreastMilkStorage().getDescriptor() + " [pc.crotchMilk] ("+ Units.fluid(target.getBreastRawMilkStorageValue(), Units.UnitType.LONG)+"))]!"
									+ "</i></p>"
								:"")
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>You're now ready to give birth!</b>" 
						+ "</p>";
			} else {
				return "<p>"
							+ "By now, your stomach has completely ballooned out in front of you, and you're having to arch your back and support yourself with one hand as you walk around."
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?" Clearly visible through the translucent slime which your body is made up of, you see that the "
										+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" slime core"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing inside of you "
										+(target.getPregnantLitter().getTotalLitterCount()==1?"has":"have")+" grown to be just as large as your own, and you know that you're now ready to give birth."
								:(target.getVaginaType()==VaginaType.HARPY
									?" Although you can feel the hard shells of your clutch of eggs pressing out against the inner walls of your womb, you don't find the sensation to be in any way uncomfortable."
										+ " If anything, the feeling only seems to be boosting your maternal instincts, and you often catch yourself daydreaming about laying and incubating your eggs."
									:" Some time in the last couple of hours, you felt a familiar rumble in your pregnant bump, and from experience, you instantly recognised that it was your offspring kicking about in your womb."
										+ " You keep feeling another kick every now and then, and you know that you're ready to give birth."))
						+ "</p>"
						+ "<p>"
							+ UtilText.parseThought("I really should go and see Lilaya... Or maybe I'll stay like this for a little while!", Main.game.getPlayer())
						+ "</p>"
						+(lactationIncrease
								? "<p><i>"
										+"Your breasts have gotten noticeably heavier, and as you softly stroke the round bump in your belly, you feel droplets of [pc.milk] beading up on your engorged teats."
										+ " You are now able to produce [style.boldSex(" + target.getBreastMilkStorage().getDescriptor() + " [pc.milk] ("+Units.fluid(target.getBreastRawMilkStorageValue(), Units.UnitType.LONG)+"))]!"
									+ "</i></p>"
								:"")
						+(lactationUddersIncrease
								? "<p><i>"
										+"Your [pc.udders] have gotten noticeably heavier, and as you walk, you feel droplets of [pc.crotchMilk] beading up on your engorged teats."
										+ " You are now able to produce [style.boldSex(" + target.getBreastMilkStorage().getDescriptor() + " [pc.crotchMilk] ("+ Units.fluid(target.getBreastRawMilkStorageValue(), Units.UnitType.LONG)+"))]!"
									+ "</i></p>"
								:"")
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + PresetColour.GENERIC_SEX.toWebHexString()+ ";'>You're now ready to give birth!</b>"
						+ "</p>";
			}
		}

		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	public static AbstractStatusEffect PREGNANT_3 = new AbstractStatusEffect(80,
			"ready for birthing",
			"pregnancy3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues("-15% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
							(target.isTaur()
								?"[npc.NamePos] belly has inflated to a colossal size, making it clear to anyone who glances [npc.her] way that [npc.sheIs] ready to give birth."
								:"[npc.NamePos] belly has inflated to a colossal size, and [npc.sheIs] finding that [npc.sheHasFull] to support [npc.her] back with one hand as [npc.she] [npc.verb(walk)].")
							+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?" Through the [npc.skinColour] [npc.skin] that makes up [npc.her] body, you can see "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" fully-grown slime core"
									+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+"."
								:"")
							+(target.isPlayer()
								?" It might be a good idea to visit Lilaya..."
								:""));
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isPregnant()
					 && !target.hasStatusEffect(StatusEffect.PREGNANT_0)
					 && !target.hasStatusEffect(StatusEffect.PREGNANT_1)
					 && !target.hasStatusEffect(StatusEffect.PREGNANT_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_STOMACH_1 = new AbstractStatusEffect(80,
			"Egg Incubation (Stomach)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.ANUS).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] stomach filled with eggs!"
							+ " Thanks to the effects of the arcane, these eggs are sure to rapidly mature and advance to the next stage of incubation in a matter of days.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_STOMACH_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ "The weight in your stomach has gotten noticeably heavier, and you can't help but wonder how long it will be before you're ready to lay the eggs which are incubating in your belly..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(The eggs in your stomach have grown in size!)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.ASS, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_STOMACH_2 = new AbstractStatusEffect(80,
			"Advanced Egg Incubation (Stomach)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.ANUS).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] stomach filled with eggs!"
							+ " Thanks to the effects of the arcane, these eggs are sure to rapidly mature and advance to the final stage of incubation in a matter of days.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.ANUS, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ "From their significant weight, you're sure that the eggs in your stomach have by now reached full maturity, and could be laid and hatched at any time of your choosing."
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(You're now ready to lay the eggs that have been incubating in your stomach!)]"
						+ "<br/>(To lay your eggs, open your phone's menu and access the 'Eggs' tab.)"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.ASS, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_STOMACH_3 = new AbstractStatusEffect(80,
			"Completed Egg Incubation (Stomach)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.ANUS).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "From one of [npc.namePos] sexual encounters, [npc.sheHas] had [npc.her] stomach filled with eggs. Having reached full maturity, they're now ready to be laid and hatched.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.ANUS)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.ASS, 3);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_1 = new AbstractStatusEffect(80,
			"Egg Incubation (Breasts)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] [npc.breasts] filled with eggs!"
							+ " Thanks to the effects of the arcane, these eggs are sure to rapidly mature and advance to the next stage of incubation in a matter of days.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_NIPPLES_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "The weight in your chest has gotten noticeably heavier, and you can't help but wonder how long it will be before you're ready to lay the eggs which are incubating in them..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(The eggs in your breasts have grown in size!)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_2 = new AbstractStatusEffect(80,
			"Advanced Egg Incubation (Breasts)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] [npc.breasts] filled with eggs!"
							+ " Thanks to the effects of the arcane, these eggs are sure to rapidly mature and advance to the final stage of incubation in a matter of days.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.NIPPLE, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "From their significant weight, you're sure that the eggs in your [npc.breasts] have by now reached full maturity, and could be laid and hatched at any time of your choosing."
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(You're now ready to lay the eggs that have been incubating in your [npc.breasts]!)]"
						+ "<br/>(To lay your eggs, open your phone's menu and access the 'Eggs' tab.)"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_3 = new AbstractStatusEffect(80,
			"Completed Egg Incubation (Breasts)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "From one of [npc.namePos] sexual encounters, [npc.sheHas] had [npc.her] [npc.breasts] filled with eggs. Having reached full maturity, they're now ready to be laid and hatched.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.NIPPLE)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST, 3);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_CROTCH_1 = new AbstractStatusEffect(80,
			"Egg Incubation (Crotch-boobs)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getName(GameCharacter target) {
			if(target!=null && target.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Egg Incubation (Udders)";
			}
			return "Egg Incubation (Crotch-boobs)";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] [npc.crotchBoobs] filled with eggs!"
							+ " Thanks to the effects of the arcane, these eggs are sure to rapidly mature and advance to the next stage of incubation in a matter of days.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_NIPPLES_CROTCH_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "The weight in your [npc.crotchBoobs] has gotten noticeably heavier, and you can't help but wonder how long it will be before you're ready to lay the eggs which are incubating in there..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(The eggs in your [npc.crotchBoobs] have grown in size!)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST_CROTCH, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_CROTCH_2 = new AbstractStatusEffect(80,
			"Advanced Egg Incubation (Crotch-boobs)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getName(GameCharacter target) {
			if(target!=null && target.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Advanced Egg Incubation (Udders)";
			}
			return "Advanced Egg Incubation (Crotch-boobs)";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] [npc.crotchBoobs] filled with eggs!"
							+ " Thanks to the effects of the arcane, these eggs are sure to rapidly mature and advance to the final stage of incubation in a matter of days.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.NIPPLE_CROTCH, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "From their significant weight, you're sure that the eggs in your [npc.crotchBoobs] have by now reached full maturity, and could be laid and hatched at any time of your choosing."
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(You're now ready to lay the eggs that have been incubating in your [npc.crotchBoobs]!)]"
						+ "<br/>(To lay your eggs, open your phone's menu and access the 'Eggs' tab.)"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST_CROTCH, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_NIPPLES_CROTCH_3 = new AbstractStatusEffect(80,
			"Completed Egg Incubation (Crotch-boobs)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getName(GameCharacter target) {
			if(target!=null && target.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Completed Egg Incubation (Udders)";
			}
			return "Completed Egg Incubation (Crotch-boobs)";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"From one of [npc.namePos] sexual encounters, [npc.sheHas] had [npc.her] [npc.crotchBoobs] filled with eggs. Having reached full maturity, they're now ready to be laid and hatched.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.BREAST_CROTCH, 3);
		}
	};

	public static AbstractStatusEffect INCUBATING_EGGS_SPINNERET_1 = new AbstractStatusEffect(80,
			"Egg Incubation (Spinneret)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.SPINNERET).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] [npc.spinneret] filled with eggs!"
							+ " Thanks to the effects of the arcane, these eggs are sure to rapidly mature and advance to the next stage of incubation in a matter of days.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_SPINNERET_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "The weight in your [npc.spinneret] has gotten noticeably heavier, and you can't help but wonder how long it will be before you're ready to lay the eggs which are incubating in there..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(The eggs in your [npc.spinneret] have grown in size!)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.SPINNERET, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_SPINNERET_2 = new AbstractStatusEffect(80,
			"Advanced Egg Incubation (Spinneret)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.SPINNERET).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] [npc.spinneret] filled with eggs!"
							+ " Thanks to the effects of the arcane, these eggs are sure to rapidly mature and advance to the final stage of incubation in a matter of days.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.SPINNERET, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "From their significant weight, you're sure that the eggs in your [npc.spinneret] have by now reached full maturity, and could be laid and hatched at any time of your choosing."
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(You're now ready to lay the eggs that have been incubating in your [npc.spinneret]!)]"
						+ "<br/>(To lay your eggs, open your phone's menu and access the 'Eggs' tab.)"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.SPINNERET, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_SPINNERET_3 = new AbstractStatusEffect(80,
			"Completed Egg Incubation (Spinneret)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.SPINNERET).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"From one of [npc.namePos] sexual encounters, [npc.sheHas] had [npc.her] [npc.spinneret] filled with eggs. Having reached full maturity, they're now ready to be laid and hatched.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.SPINNERET)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.SPINNERET, 3);
		}
	};

	public static AbstractStatusEffect INCUBATING_EGGS_WOMB_1 = new AbstractStatusEffect(80,
			"Egg Incubation (Womb)",
			"incubation1",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.VAGINA).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] womb filled with eggs!"
							+ " Thanks to the effects of the arcane, these eggs are sure to rapidly mature and advance to the next stage of incubation in a matter of days.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.addStatusEffect(INCUBATING_EGGS_WOMB_2, 60 * 60 * (72 + Util.random.nextInt(13)));
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "The weight in your womb has gotten noticeably heavier, and you can't help but wonder how long it will be before you're ready to lay the eggs which are incubating in there..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "[style.boldSex(The eggs in your womb have grown in size!)]"
					+ "</p>");
			
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.VAGINA, 1);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_WOMB_2 = new AbstractStatusEffect(80,
			"Advanced Egg Incubation (Womb)",
			"incubation2",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.VAGINA).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"From one of [npc.namePos] recent sexual encounters, [npc.sheHas] had [npc.her] womb filled with eggs!"
							+ " Thanks to the effects of the arcane, these eggs are sure to rapidly mature and advance to the final stage of incubation in a matter of days.");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.setTimeProgressedToFinalIncubationStage(SexAreaOrifice.VAGINA, Main.game.getSecondsPassed());
			if(!target.isPlayer()) {
				return "";
			}
			
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "From their significant weight, you're sure that the eggs in your womb have by now reached full maturity, and could be laid and hatched at any time of your choosing."
					+ "</p>");
			
			sb.append("<p style='text-align:center;'>"
						+ "[style.boldSex(You're now ready to lay the eggs that have been incubating in your womb!)]"
						+ "<br/>(To lay your eggs, open your phone's menu and access the 'Eggs' tab.)"
					+ "</p>");
			
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
				sb.append(Main.game.getPlayer().startQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION));
			}
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.VAGINA, 2);
		}
	};
	
	public static AbstractStatusEffect INCUBATING_EGGS_WOMB_3 = new AbstractStatusEffect(80,
			"Completed Egg Incubation (Womb)",
			"incubation3",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues()) {
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			int count = target.getIncubationLitter(SexAreaOrifice.VAGINA).getTotalLitterCount();
			return Util.newArrayListOfValues("[style.colourYellowLight(Incubating)] [style.colourGood("+Util.intToString(count)+")] [style.colourYellowLight(egg"+(count>1?"s":"")+")]");
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "From one of [npc.namePos] sexual encounters, [npc.sheHas] had [npc.her] womb filled with eggs. Having reached full maturity, they're now ready to be laid and hatched.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getIncubationLitter(SexAreaOrifice.VAGINA)!=null
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_1)
					 && !target.hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_2);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getIncubationSVGString(owner, SexAreaOrifice.VAGINA, 3);
		}
	};
	
	public static AbstractStatusEffect VIXENS_VIRILITY = new AbstractStatusEffect(80,
			"Breeder pill's effects",
			"vixensVirility",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 50f),
					new Value<>(Attribute.VIRILITY, 50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "After consuming a '[#ITEM_innoxia_pills_fertility.getName(false)]', [npc.namePos] fertility and virility have been temporarily boosted.");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect PROMISCUITY_PILL = new AbstractStatusEffect(80,
			"Sterility pill's effects",
			"promiscuityPill",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, -100f),
					new Value<>(Attribute.VIRILITY, -100f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "After consuming a '[#ITEM_innoxia_pills_sterility.getName(false)]', [npc.namePos] fertility and virility have been greatly reduced."
							+ " This is a <b>preventative</b> measure, and will not alter the outcome of any unprotected sex [npc.she] had before taking the pill!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect BROODMOTHER_PILL = new AbstractStatusEffect(80,
			"Broodmother pill's effects",
			"broodmother_pill",
			PresetColour.CLOTHING_PINK,
			true,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, 100f),
					new Value<>(Attribute.VIRILITY, 100f)),
			Util.newArrayListOfValues(
					"[style.colourExcellent(Doubles)] offspring conceived")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"After consuming a '[#ITEM_innoxia_pills_broodmother.getName(false)]', [npc.namePos] fertility and virility have been temporarily boosted,"
							+ " and if [npc.she] impregnates someone or becomes impregnated [npc.herself], [npc.she] will conceive far more offspring than usual!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CUM_PRODUCTION = new AbstractStatusEffect(80,
			"Cum Production",
			"cumProduction",
			PresetColour.GENERIC_SEX,
			true,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			target.incrementPenisStoredCum(secondsPassed * target.getCumRegenerationPerSecond());
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumRegenRate = target.getCumRegenerationPerSecond()*60;
			
			return UtilText.parse(target, "[npc.NamePos] balls are currently producing more [npc.cum], at a rate of "+Units.fluid(cumRegenRate)+"/minute."
					+ " They have stored "+Units.fluid(target.getPenisRawStoredCumValue())+", out of a maximum of "+Units.fluid(target.getPenisRawCumStorageValue())+".");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)
					&& target.getPenisRawCumStorageValue()>0
					&& target.getPenisRawStoredCumValue()!=target.getPenisRawCumStorageValue()
					&& target.hasPenisIgnoreDildo()
					&& (target.isPlayer() || target.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect CUM_FULL = new AbstractStatusEffect(80,
			"Full Balls",
			"cumFull",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumRegenRate = target.getCumRegenerationPerSecond()*60;
			
			return UtilText.parse(target, "[npc.NamePos] balls are completely filled with [npc.cum] ("+Units.fluid(target.getPenisRawCumStorageValue())+"),"
					+ " and [npc.she] can't wait until the next time [npc.sheIs] able to empty them."
					+ " [npc.She] will ejaculate "+Units.fluid(target.getPenisRawOrgasmCumQuantity())+" upon orgasm, and will then regenerate [npc.cum] at a rate of "+Units.fluid(cumRegenRate)+"/minute.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)
					&& target.getPenisRawCumStorageValue()>0
					&& target.getPenisRawStoredCumValue()==target.getPenisRawCumStorageValue()
					&& target.hasPenisIgnoreDildo()
					&& (target.isPlayer() || target.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect MILK_PRODUCTION = new AbstractStatusEffect(80,
			"Milk Production",
			"milkProduction",
			PresetColour.GENERIC_SEX,
			true,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			target.incrementBreastStoredMilk(secondsPassed * target.getLactationRegenerationPerSecond(true));
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getLactationRegenerationPerSecond(false) * 60;

			return UtilText.parse(target,
					"[npc.NamePos] breasts are producing [npc.milk] at an individual rate of "+Units.fluid(milkRegenRate)+"/minute,"
							+ " totalling [style.colourGood("+Units.fluid(milkRegenRate * target.getBreastRows() * 2)+"/minute)] (as [npc.sheHasFull] [npc.totalBreasts] breasts)."
					+ " They have stored "+Units.fluid(target.getBreastRawStoredMilkValue())+", out of a maximum of "+Units.fluid(target.getBreastRawMilkStorageValue())+".");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getBreastRawMilkStorageValue()>0 && target.getBreastRawStoredMilkValue()<target.getBreastRawMilkStorageValue();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect MILK_FULL = new AbstractStatusEffect(80,
			"Full Breasts",
			"milkFull",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getLactationRegenerationPerSecond(false) * 60;
		
			return UtilText.parse(target,
					"[npc.NamePos] [npc.breasts] are filled with "+Units.fluid(target.getBreastRawMilkStorageValue())+" of [npc.milk].<br/>"
						+ "They produce more [npc.milk] at an individual rate of "+Units.fluid(milkRegenRate)+"/minute,"
						+ " totalling [style.colourGood("+Units.fluid(milkRegenRate * target.getBreastRows() * 2)+"/minute)] (as [npc.sheHasFull] [npc.totalBreasts] breasts).");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getBreastRawMilkStorageValue()>0
					&& target.getBreastRawStoredMilkValue()==target.getBreastRawMilkStorageValue();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect MILK_CROTCH_PRODUCTION = new AbstractStatusEffect(80,
			"Crotch-boob Milk Production",
			"milkCrotchProduction",
			PresetColour.GENERIC_SEX,
			true,
			null,
			null) {
		@Override
		public String getName(GameCharacter target) {
			if(target==null) {
				return super.getName(target);
			}
			return UtilText.parse(target, "[npc.CrotchBoob] Milk Production");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			target.incrementBreastCrotchStoredMilk((secondsPassed) * target.getCrotchLactationRegenerationPerSecond(true));
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getCrotchLactationRegenerationPerSecond(false) * 60;
			
			return UtilText.parse(target,
					"[npc.NamePos] [npc.crotchBoobs] are producing [npc.crotchMilk] at an individual rate of "+Units.fluid(milkRegenRate)+"/minute,"
							+ " totalling [style.colourGood("+Units.fluid(milkRegenRate * Math.max(1, target.getBreastCrotchRows()*2))+"/minute)] (as [npc.sheHasFull] [npc.totalCrotchBoobs] [npc.crotchBoobs])."
					+ " They have stored "+Units.fluid(target.getBreastCrotchRawStoredMilkValue())+", out of a maximum of "+Units.fluid(target.getBreastCrotchRawMilkStorageValue())+".");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasBreastsCrotch()
					&& (Main.getProperties().getUddersLevel()>0 || target.isFeral())
					&& target.getBreastCrotchRawMilkStorageValue()>0
					&& target.getBreastCrotchRawStoredMilkValue()!=target.getBreastCrotchRawMilkStorageValue();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect MILK_CROTCH_FULL = new AbstractStatusEffect(80,
			"Full Crotch-boobs",
			"milkCrotchFull",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String getName(GameCharacter target) {
			if(target==null) {
				return super.getName(target);
			}
			return UtilText.parse(target, "Full [npc.CrotchBoobs]");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getCrotchLactationRegenerationPerSecond(false) * 60;
		
			return UtilText.parse(target,
					"[npc.NamePos] [npc.crotchBoobs] are filled with "+Units.fluid(target.getBreastCrotchRawMilkStorageValue())+" of [npc.crotchMilk].<br/>"
							+ " They produce more [npc.crotchMilk] at an individual rate of "+Units.fluid(milkRegenRate)+"/minute,"
							+ " totalling [style.colourGood("+Units.fluid(milkRegenRate * Math.max(1, target.getBreastCrotchRows()*2))+"/minute)] (as [npc.sheHasFull] [npc.totalCrotchBoobs] [npc.crotchBoobs]).");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasBreastsCrotch()
					&& (Main.getProperties().getUddersLevel()>0 || target.isFeral())
					&& target.getBreastCrotchRawMilkStorageValue()>0
					&& target.getBreastCrotchRawStoredMilkValue()==target.getBreastCrotchRawMilkStorageValue();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect STRETCHING_ORIFICE = new AbstractStatusEffect(80,
			"Big Toys",
			"sexEffects/combinationStretching",
			PresetColour.BASE_MAGENTA,
			false,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			float minimumStretchPercentage = 0.00_05f;
			
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificeStretching().entrySet()) {
				AbstractClothing clothing = entry.getValue();
				int length = clothing.getClothingType().getPenetrationSelfLength();
				PenetrationGirth girth = PenetrationGirth.getGirthFromInt(clothing.getClothingType().getPenetrationSelfGirth());
				float diameter = Penis.getGenericDiameter(length, girth);
				
				// Stretch out the orifice by a factor of elasticity's modifier:
				float stretchModifier = 600f;
				float stretch = 0f;
				switch(entry.getKey()) {
					case ANUS:
						stretch = Math.max(diameter*minimumStretchPercentage, (diameter-target.getAssStretchedCapacity())*target.getAssElasticity().getStretchModifier());
						target.incrementAssStretchedCapacity((stretch/stretchModifier) * secondsPassed);
						if(target.getAssStretchedCapacity()>diameter) {
							target.setAssStretchedCapacity(diameter);
						}
						break;
					case MOUTH:
						stretch = Math.max(diameter*minimumStretchPercentage, (diameter-target.getFaceStretchedCapacity())*target.getFaceElasticity().getStretchModifier());
						target.incrementFaceStretchedCapacity((stretch/stretchModifier) * secondsPassed);
						if(target.getFaceStretchedCapacity()>diameter) {
							target.setFaceStretchedCapacity(diameter);
						}
						break;
					case NIPPLE:
						stretch = Math.max(diameter*minimumStretchPercentage, (diameter-target.getNippleStretchedCapacity())*target.getNippleElasticity().getStretchModifier());
						target.incrementNippleStretchedCapacity((stretch/stretchModifier) * secondsPassed);
						if(target.getNippleStretchedCapacity()>diameter) {
							target.setNippleStretchedCapacity(diameter);
						}
						break;
					case VAGINA:
						stretch = Math.max(diameter*minimumStretchPercentage, (diameter-target.getVaginaStretchedCapacity())*target.getVaginaElasticity().getStretchModifier());
						target.incrementVaginaStretchedCapacity((stretch/stretchModifier) * secondsPassed);
						if(target.getVaginaStretchedCapacity()>diameter) {
							target.setVaginaStretchedCapacity(diameter);
						}
						break;
					default:
						break;
				}
			}
			
			return "";
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> effects = new ArrayList<>();
			
			Map<SexAreaOrifice, String> orifices = Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, "[style.boldVagina(Vagina)]:"),
					new Value<>(SexAreaOrifice.ANUS, "[style.boldAsshole(Asshole)]:"),
					new Value<>(SexAreaOrifice.MOUTH, "[style.boldMouth(Throat)]:"),
					new Value<>(SexAreaOrifice.NIPPLE, "[style.boldNipple(Nipples)]:"));
			List<SexAreaOrifice> orificesFound = new ArrayList<>();
			for(Entry<SexAreaOrifice, String> orifice : orifices.entrySet()) {
				if(target.getSexToyOrificeStretching().containsKey(orifice.getKey())) {
					AbstractClothing clothing = target.getSexToyOrificeStretching().get(orifice.getKey());
					int length = clothing.getClothingType().getPenetrationSelfLength();
					PenetrationGirth girth = PenetrationGirth.getGirthFromInt(clothing.getClothingType().getPenetrationSelfGirth());
					float diameter = Penis.getGenericDiameter(length, girth);
					
					effects.add(orifice.getValue());
					effects.add("[style.boldBad(Stretching)] up to [style.boldPinkLight("+Units.size(diameter)+")]");
					orificesFound.add(orifice.getKey());
				}
				if(target.getSexToyOrificePreventingStretchRecovery().containsKey(orifice.getKey())) {
					if(!orificesFound.contains(orifice.getKey())) {
						effects.add(orifice.getValue());
					}
					effects.add("[style.boldMinorBad(Preventing)] [style.boldPinkLight(stretch recovery)]");
					orificesFound.add(orifice.getKey());
				}
				if(target.getSexToyOrificeTooDeep().containsKey(orifice.getKey())) {
					AbstractClothing clothing = target.getSexToyOrificeTooDeep().get(orifice.getKey());
					int length = clothing.getClothingType().getPenetrationSelfLength();
					if(!orificesFound.contains(orifice.getKey())) {
						effects.add(orifice.getValue());
					}
					effects.add("[style.boldTerrible(Too deep)] at [style.boldPinkLight("+Units.size(length)+")]");
				}
			}
			
			return effects;
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			
			List<String> stretching = new ArrayList<>();
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificeStretching().entrySet()) {
				switch(entry.getKey()) {
					case ANUS:
						stretching.add("[style.colourAsshole(asshole)]");
						break;
					case MOUTH:
						stretching.add("[style.colourMouth(throat)]");
						break;
					case NIPPLE:
						stretching.add("[style.colourNipple(nipples)]");
						break;
					case VAGINA:
						stretching.add("[style.colourVagina(vagina)]");
						break;
					default:
						break;
				}
			}
			List<String> preventingRecovery = new ArrayList<>();
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificePreventingStretchRecovery().entrySet()) {
				switch(entry.getKey()) {
					case ANUS:
						preventingRecovery.add("[style.colourAsshole(asshole)]");
						break;
					case MOUTH:
						preventingRecovery.add("[style.colourMouth(throat)]");
						break;
					case NIPPLE:
						preventingRecovery.add("[style.colourNipple(nipples)]");
						break;
					case VAGINA:
						preventingRecovery.add("[style.colourVagina(vagina)]");
						break;
					default:
						break;
				}
			}
			List<String> tooDeep = new ArrayList<>();
			for(Entry<SexAreaOrifice, AbstractClothing> entry : target.getSexToyOrificeTooDeep().entrySet()) {
				switch(entry.getKey()) {
					case ANUS:
						tooDeep.add("[style.colourAsshole(asshole)]");
						break;
					case MOUTH:
						tooDeep.add("[style.colourMouth(throat)]");
						break;
					case NIPPLE:
						tooDeep.add("[style.colourNipple(nipples)]");
						break;
					case VAGINA:
						tooDeep.add("[style.colourVagina(vagina)]");
						break;
					default:
						break;
				}
			}
			
			boolean needBreak = false;
			if(!target.getSexToyOrificeStretching().isEmpty()) {
				boolean singular = target.getSexToyOrificeStretching().size()==1;
				descriptionSB.append("[npc.NamePos] "+(singular?"sex toy is":"sex toys are")+" too big for [npc.herHim], and as a result, [npc.her] "
						+Util.stringsToStringList(stretching, false)+(singular?" is":" are")+" being [style.colourBad(stretched out)]!");
				needBreak = true;
			}
			
			if(!target.getSexToyOrificePreventingStretchRecovery().isEmpty()) {
				if(needBreak) {
					descriptionSB.append(" ");
				}
				boolean singular = target.getSexToyOrificePreventingStretchRecovery().size()==1;
				descriptionSB.append("[npc.NamePos] "+(singular?"sex toy is":"sex toys are")+" preventing [npc.her] [style.colourMinorBad(stretched)] " +Util.stringsToStringList(preventingRecovery, false)+" from recovering!");
				needBreak = true;
			}
			
			if(Main.game.isPenetrationLimitationsEnabled()) {
				if(!target.getSexToyOrificeTooDeep().isEmpty()) {
					if(needBreak) {
						descriptionSB.append(" ");
					}
					boolean singular = target.getSexToyOrificeTooDeep().size()==1;
					descriptionSB.append((singular?"The sex toy":"The sex toys")+" inserted in [npc.namePos] "
							+Util.stringsToStringList(tooDeep, false)+(singular?" is":" are")+" [style.colourTerrible(uncomfortably deep)]!");
				}
			}
			
			return UtilText.parse(target, descriptionSB.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.getSexToyOrificeTooDeep().isEmpty() || !target.getSexToyOrificeStretching().isEmpty() || !target.getSexToyOrificePreventingStretchRecovery().isEmpty();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter target) {
			Set<SexAreaOrifice> orifices = new HashSet<>(target.getSexToyOrificeStretching().keySet());
			orifices.addAll(target.getSexToyOrificePreventingStretchRecovery().keySet());
			orifices.addAll(target.getSexToyOrificeTooDeep().keySet());
			return getStretchingOrificeStatus(target, !target.getSexToyOrificeStretching().isEmpty(), !target.getSexToyOrificePreventingStretchRecovery().isEmpty(), !target.getSexToyOrificeTooDeep().isEmpty(), orifices);
		}
	};
	
	public static AbstractStatusEffect RECOVERING_ORIFICE = new AbstractStatusEffect(80,
			"Recovering Vagina",
			"recoveringOrifice",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -2f), new Value<>(Attribute.HEALTH_MAXIMUM, -5f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			int i=0;
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.isInSex()) {
				sb.append("Stretched");
			} else {
				sb.append("Recovering");
			}
			
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				sb.append(" Vagina");
				i++;
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				sb.append(" Anus");
				i++;
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				sb.append(" Nipples");
				i++;
			}
			if (target.hasBreastsCrotch()
					&& (Main.getProperties().getUddersLevel()>0 || target.isFeral())
					&& target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				sb.append(" Crotch Nipples");
				i++;
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				sb.append(" Penile Urethra");
				i++;
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				sb.append(" Vaginal Urethra");
				i++;
			}
			if (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				sb.append(" Throat");
				i++;
			}
			
			if(i>1) {
				if(Main.game.isInSex()) {
					return "Stretched Orifices";
				} else {
					return "Recovering Orifices";
				}
			} else {
				return sb.toString();
			}
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			
			Set<SexAreaOrifice> stretchRecoveryPrevention = new HashSet<>(target.getSexToyOrificePreventingStretchRecovery().keySet());
			stretchRecoveryPrevention.addAll(target.getSexToyOrificeStretching().keySet());
			
			if(!stretchRecoveryPrevention.contains(SexAreaOrifice.VAGINA) && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				target.incrementVaginaStretchedCapacity(-target.getVaginaPlasticity().getRecoveryModifier()*secondsPassed);

				if(target.getVaginaStretchedCapacity()<target.getVaginaRawCapacityValue()) {
					target.setVaginaStretchedCapacity(target.getVaginaRawCapacityValue());
				}
			}
			if(!stretchRecoveryPrevention.contains(SexAreaOrifice.ANUS) && target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				target.incrementAssStretchedCapacity(-target.getAssPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getAssStretchedCapacity()<target.getAssRawCapacityValue()) {
					target.setAssStretchedCapacity(target.getAssRawCapacityValue());
				}
			}
			if(!stretchRecoveryPrevention.contains(SexAreaOrifice.NIPPLE) && target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				target.incrementNippleStretchedCapacity(-target.getNipplePlasticity().getRecoveryModifier()*secondsPassed);

				if(target.getNippleStretchedCapacity()<target.getNippleRawCapacityValue()) {
					target.setNippleStretchedCapacity(target.getNippleRawCapacityValue());
				}
			}
			if(!stretchRecoveryPrevention.contains(SexAreaOrifice.MOUTH) && target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				target.incrementFaceStretchedCapacity(-target.getFacePlasticity().getRecoveryModifier()*secondsPassed);

				if(target.getFaceStretchedCapacity()<target.getFaceRawCapacityValue()) {
					target.setFaceStretchedCapacity(target.getFaceRawCapacityValue());
				}
			}
			if(target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				target.incrementNippleCrotchStretchedCapacity(-target.getNippleCrotchPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getNippleCrotchStretchedCapacity()<target.getNippleCrotchRawCapacityValue()) {
					target.setNippleCrotchStretchedCapacity(target.getNippleCrotchRawCapacityValue());
				}
			}
			if(target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				target.incrementPenisStretchedCapacity(-target.getUrethraPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getPenisStretchedCapacity()<target.getPenisRawCapacityValue()) {
					target.setPenisStretchedCapacity(target.getPenisRawCapacityValue());
				}
			}
			if(target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				target.incrementVaginaUrethraStretchedCapacity(-target.getVaginaUrethraPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getVaginaUrethraStretchedCapacity()<target.getVaginaUrethraRawCapacityValue()) {
					target.setVaginaUrethraStretchedCapacity(target.getVaginaUrethraRawCapacityValue());
				}
			}
			
			return "";
		}
		
		private String getRecoveryText(float recoveryModifier) {
			int minutes = (int) (1/recoveryModifier)/60;
			int hours = minutes /60;
			
			return "Tightens "+Units.size(1)+" every "+(hours>=1
						?(hours)+" hour"+(hours==1?"":"s")+(minutes%60>0?" and "+(minutes%60)+" minute"+(minutes==1?"":"s"):"")
						:(minutes )+" minute"+(minutes==1?"":"s"));
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			List<String> recoveringEffects = new ArrayList<>();

			Set<SexAreaOrifice> stretchRecoveryPrevention = new HashSet<>(target.getSexToyOrificePreventingStretchRecovery().keySet());
			stretchRecoveryPrevention.addAll(target.getSexToyOrificeStretching().keySet());
			
			String recoveringText = "recovering";
			String from1 = "From";
			String from2 = "to";
			
			if(Main.game.isInSex()) {
				recoveringText = "stretched";
				from1 = "To";
				from2 = "from";
			}
			
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				recoveringEffects.add("[style.boldVagina(Vagina "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getVaginaStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getVaginaRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.VAGINA)) {
					recoveringEffects.add("[style.boldBad(Sex toy preventing recovery!)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getVaginaPlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				recoveringEffects.add("[style.boldAsshole(Asshole "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getAssStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getAssRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.ANUS)) {
					recoveringEffects.add("[style.boldBad(Sex toy preventing recovery!)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getAssPlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				recoveringEffects.add("[style.boldNipples(Nipples "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getNippleStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getNippleRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.NIPPLE)) {
					recoveringEffects.add("[style.boldBad(Sex toy preventing recovery!)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getNipplePlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				recoveringEffects.add("[style.boldMouth(Throat "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getFaceStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getFaceRawCapacityValue())+")]");
				if(stretchRecoveryPrevention.contains(SexAreaOrifice.MOUTH)) {
					recoveringEffects.add("[style.boldBad(Sex toy preventing recovery!)]");
				} else {
					recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getFacePlasticity().getRecoveryModifier())+")]");
				}
			}
			if (target.hasBreastsCrotch() && target.hasBreastsCrotch() && target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				recoveringEffects.add("[style.boldNipplesCrotch(Crotch nipples "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getNippleCrotchStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getNippleCrotchRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getNippleCrotchPlasticity().getRecoveryModifier())+")]");
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				recoveringEffects.add("[style.boldPenisUrethra(Penile Urethra "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getPenisStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getPenisRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getUrethraPlasticity().getRecoveryModifier())+")]");
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				recoveringEffects.add("[style.boldVaginaUrethra(Vaginal Urethra "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getVaginaUrethraStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getVaginaUrethraRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getVaginaUrethraPlasticity().getRecoveryModifier())+")]");
			}
			
			
			return recoveringEffects;
		}
		@Override
		public String getDescription(GameCharacter target) {
			
			StringBuilder descriptionSB = new StringBuilder("After being forced to accommodate");
			
			List<String> orificesRecovering = new ArrayList<>();
			boolean plural = false;
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				orificesRecovering.add("[style.boldVagina(vagina)]");
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				orificesRecovering.add("[style.boldAnus(asshole)]");
			}
			if (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity()){
				orificesRecovering.add("[style.boldMouth(throat)]");
				plural = true;
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				orificesRecovering.add("[style.boldNipple(nipples)]");
				plural = true;
			}
			if (target.hasBreastsCrotch() && target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				orificesRecovering.add("[style.boldNipplesCrotch(crotch nipples)]");
				plural = true;
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				orificesRecovering.add("[style.boldPenisUrethra(penile urethra)]");
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				orificesRecovering.add("[style.boldVaginaUrethra(vaginal urethra)]");
			}
			if(orificesRecovering.size()==1) {
				descriptionSB.append(" an object that was far too big for "+(plural?"them":"it")+", [npc.namePos] "+orificesRecovering.get(0)+" "+(plural?"have":"has")
						+" been stretched out, and "+(plural?"need":"needs")+" some time in which to recover all of "+(plural?"their":"its")+" natural tightness.");
			} else {
				descriptionSB.append(" phallic objects that were far too big for them, [npc.namePos] "+Util.stringsToStringList(orificesRecovering, false)
						+" have been stretched out, and need some time in which to recover all of their natural tightness.");
			}

			if(Main.game.isInSex()) {
				descriptionSB.append(" [style.italicsBad(Orifices do not recover during sex scenes!)]");
			}
			
			return UtilText.parse(target, descriptionSB.toString());
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ((target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity())
					|| (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity())
					|| (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity())
					|| (target.getFaceRawCapacityValue()!=target.getFaceStretchedCapacity())
					|| (target.hasBreastsCrotch()
							&& (Main.getProperties().getUddersLevel()>0 || target.isFeral())
							&& target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity())
					|| (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity())
					|| (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getRecoveringOrificeStatus(owner, super.getSVGString(owner));
		}
	};
	
	
	
	public static AbstractStatusEffect CREAMPIE_VAGINA = new AbstractStatusEffect(80,
			"Pussy Creampie",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			AbstractClothing pump = target.getClothingInSlot(InventorySlot.VAGINA);
			if(pump!=null && pump.isMilkingEquipment()) {
				target.clearFluidsStored(SexAreaOrifice.VAGINA);
				if(!Main.game.isInSex()) {
					return "<p>"
								+ "All of the cum from your creampied pussy is quickly sucked away by your "+pump.getName()+"!"
							+ "</p>";
				}
			}
			
			if(Main.game.isInSex()) {
				return "";
			}
			
			float cumLost = SexAreaOrifice.VAGINA.getCharactersCumLossPerSecond(target)*secondsPassed;
			
			StringBuilder sb = new StringBuilder();

			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.VAGINA);
			boolean dirtyArea = clothingBlocking==null;
			
			if(clothingBlocking!=null
					&& !clothingBlocking.getItemTags().contains(ItemTag.PLUGS_VAGINA)
					&& !clothingBlocking.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					sb.append("<p>"
								+ "The cum from your creampied pussy quickly </b><b style='color:"+PresetColour.CUM.toWebHexString()+";'>dirties</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+"!"
							+ "</p>");
				}
				dirtyArea = true;
			}
			
			if(dirtyArea) {
				if(!target.getDirtySlots().contains(InventorySlot.VAGINA)) {
					target.addDirtySlot(InventorySlot.VAGINA);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, cumLost);
			
			return sb.toString();
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumLost = SexAreaOrifice.VAGINA.getCharactersCumLossPerSecond(target) * 60;
			float cumInArea = target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
			float absorption = SexAreaOrifice.VAGINA.getCumAbsorptionPerSecond() * 60;
			
			String pregnancyText = (target.isVisiblyPregnant()?" [style.italicsSex(Due to pregnancy, maximum storage is "+Units.fluid(250)+".)]":"");
			
			if(target.isOrificePlugged(SexAreaOrifice.VAGINA)) {
				if(target.isPlayer()) {
					return "As you walk, you can feel the cum trapped within your recently-used pussy."+pregnancyText+"<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "[style.boldTerrible(Plugged Vagina:)] No cum is leaking out, although it is still being absorbed at a rate of -"+Units.fluid(absorption, ValueType.PRECISE)+"/minute!";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.pussy] has recently been filled with cum, before being plugged."+pregnancyText+"<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "[style.boldTerrible(Plugged Vagina:)] No cum is leaking out, although it is still being absorbed at a rate of -"+Units.fluid(absorption, ValueType.PRECISE)+"/minute!");
				}
				
			} else {
				if(target.isPlayer()) {
					return "As you walk, you can feel cum drooling out of your recently-used pussy."+pregnancyText+"<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.pussy] has recently been filled with cum."+pregnancyText+"<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)");
				}
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.VAGINA)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.VAGINA);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_VAGINA_URETHRA = new AbstractStatusEffect(80,
			"Vaginal Urethra Creampie",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.URETHRA_VAGINA.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			StringBuilder sb = new StringBuilder();
			List<AbstractClothing> blockingList = target.getBlockingCoverableAreaClothingList(CoverableArea.VAGINA, false);
			boolean pluggedFound = blockingList.removeIf((c) -> c.getItemTags().contains(ItemTag.PLUGS_VAGINA));
			blockingList.sort((c1, c2) -> c1.getSlotEquippedTo().getZLayer()-c2.getSlotEquippedTo().getZLayer());
			AbstractClothing clothingBlocking = blockingList.isEmpty()?null:blockingList.get(0);
//			if(clothingBlocking!=null) {
//				System.out.println(clothingBlocking.getName());
//			}
			boolean dirtyArea = clothingBlocking==null;

			// 'PLUGS_VAGINA' check doesn't entirely make sense, but if not included, and the character has a urethral and normal creampie, then the game enters a loop of cleaning-dirtying if a dildo is inserted into the vagina
			if(clothingBlocking!=null
//					&& !clothingBlocking.getItemTags().contains(ItemTag.PLUGS_VAGINA)
					&& !clothingBlocking.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					sb.append("<p>"
								+ "Cum leaks out of your pussy's creampied urethra, quickly </b><b style='color:"+PresetColour.CUM.toWebHexString()+";'>dirtying</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+"!"
							+ "</p>");
				}
				dirtyArea = true;
			}
			
			if(dirtyArea && !pluggedFound && (clothingBlocking==null || !clothingBlocking.getItemTags().contains(ItemTag.SEALS_VAGINA))) {
				if(!target.getDirtySlots().contains(InventorySlot.VAGINA)) {
					target.addDirtySlot(InventorySlot.VAGINA);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, cumLost);
			
			return sb.toString();
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumLost = SexAreaOrifice.URETHRA_VAGINA.getCharactersCumLossPerSecond(target) * 60;
			float cumInArea = target.getTotalFluidInArea(SexAreaOrifice.URETHRA_VAGINA);
			float absorption = SexAreaOrifice.URETHRA_VAGINA.getCumAbsorptionPerSecond() * 60;

			String pregnancyText = (target.isVisiblyPregnant()?" [style.italicsSex(Due to pregnancy, maximum storage is "+Units.fluid(250)+".)]":"");
			
			if(target.isOrificePlugged(SexAreaOrifice.URETHRA_VAGINA)) {
				if(target.isPlayer()) {
					return "As you walk, you can feel the cum trapped within your pussy's urethra."+pregnancyText+"<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "[style.boldTerrible(Plugged Urethra:)] No cum is leaking out, although it is still being absorbed at a rate of -"+Units.fluid(absorption, ValueType.PRECISE)+"/minute!";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] vaginal urethra has recently been filled with cum, before being plugged."+pregnancyText+"<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "[style.boldTerrible(Plugged Urethra:)] No cum is leaking out, although it is still being absorbed at a rate of -"+Units.fluid(absorption, ValueType.PRECISE)+"/minute!");
				}
				
			} else {
				if(target.isPlayer()) {
					return "As you walk, you can feel cum drooling out of your pussy's urethra."+pregnancyText+"<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] vaginal urethra has recently been filled with cum."+pregnancyText+"<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)");
				}
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.URETHRA_VAGINA)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.URETHRA_VAGINA);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_PENIS_URETHRA = new AbstractStatusEffect(80,
			"Penis Urethra Creampie",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			AbstractClothing pump = target.getClothingInSlot(InventorySlot.PENIS);
			if(pump!=null && pump.isMilkingEquipment()) {
				target.clearFluidsStored(SexAreaOrifice.URETHRA_PENIS);
				if(!Main.game.isInSex()) {
					return "<p>"
								+ "All of the cum from your cock's creampied urethra is quickly sucked away by your "+pump.getName()+"!"
							+ "</p>";
				}
			}
			
			if(Main.game.isInSex()) {
				return "";
			}
			
			float cumLost = SexAreaOrifice.URETHRA_PENIS.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			StringBuilder sb = new StringBuilder();
			
			if(target.getLowestZLayerCoverableArea(CoverableArea.PENIS)!=null){
				if(!target.getLowestZLayerCoverableArea(CoverableArea.PENIS).isDirty()) {
					target.getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(target, true);
					sb.append("<p>"
								+ "Cum leaks out of your cock's creampied urethra, quickly </b><b style='color:"+PresetColour.CUM.toWebHexString()+";'>dirtying</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"!"
							+ "</p>");
				}
			}
			
			if(!target.getDirtySlots().contains(InventorySlot.PENIS)) {
				target.addDirtySlot(InventorySlot.PENIS);
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_PENIS, cumLost);
			
			return sb.toString();
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumLost = SexAreaOrifice.URETHRA_PENIS.getCharactersCumLossPerSecond(target) * 60;
			float cumInArea = target.getTotalFluidInArea(SexAreaOrifice.URETHRA_PENIS);
			float absorption = SexAreaOrifice.URETHRA_PENIS.getCumAbsorptionPerSecond() * 60;
			
			if(target.isOrificePlugged(SexAreaOrifice.URETHRA_PENIS)) {
				if(target.isPlayer()) {
					return "As you walk, you can feel the cum trapped within your cock's urethra.<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "[style.boldTerrible(Plugged Urethra:)] No cum is leaking out, although it is still being absorbed at a rate of -"+Units.fluid(absorption, ValueType.PRECISE)+"/minute!";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] penile urethra has recently been filled with cum, before being plugged.<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "[style.boldTerrible(Plugged Urethra:)] No cum is leaking out, although it is still being absorbed at a rate of -"+Units.fluid(absorption, ValueType.PRECISE)+"/minute!");
				}
				
			} else {
				if(target.isPlayer()) {
					return "As you walk, you can feel cum drooling out of your cock's urethra.<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] penile urethra has recently been filled with cum.<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)");
				}
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.URETHRA_PENIS)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.URETHRA_PENIS);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_ANUS = new AbstractStatusEffect(80,
			"Anal Creampie",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.ANUS.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			StringBuilder sb = new StringBuilder();
			
			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.ANUS);
			boolean dirtyArea = clothingBlocking==null;
			
			if(clothingBlocking!=null && !clothingBlocking.getItemTags().contains(ItemTag.PLUGS_ANUS) && !clothingBlocking.getItemTags().contains(ItemTag.SEALS_ANUS)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					sb.append("<p>"
								+ "The cum from your creampied asshole quickly </b><b style='color:"+PresetColour.CUM.toWebHexString()+";'>dirties</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.ANUS).getName()+"!"
							+ "</p>");
				}
				dirtyArea = true;
			}
			
			if(dirtyArea) {
				if(!target.getDirtySlots().contains(InventorySlot.ANUS)) {
					target.addDirtySlot(InventorySlot.ANUS);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.ANUS, cumLost);
			
			return sb.toString();
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumLost = SexAreaOrifice.ANUS.getCharactersCumLossPerSecond(target) * 60;
			float cumInArea = target.getTotalFluidInArea(SexAreaOrifice.ANUS);
			float absorption = SexAreaOrifice.ANUS.getCumAbsorptionPerSecond() * 60;
			
			if(target.isOrificePlugged(SexAreaOrifice.ANUS)) {
				if(target.isPlayer()) {
					return "As you walk, you can feel the cum trapped within your recently-used asshole.<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "[style.boldTerrible(Plugged Anus:)] No cum is leaking out, although it is still being absorbed at a rate of -"+Units.fluid(absorption, ValueType.PRECISE)+"/minute!";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] asshole has recently been filled with cum, before being plugged.<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "[style.boldTerrible(Plugged Anus:)] No cum is leaking out, although it is still being absorbed at a rate of -"+Units.fluid(absorption, ValueType.PRECISE)+"/minute!");
				}
				
			} else {
				if(target.isPlayer()) {
					return "As you walk, you can feel cum drooling out of your recently-used asshole.<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] asshole has recently been filled with cum.<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)");
				}
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.ANUS)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.ANUS);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_NIPPLES = new AbstractStatusEffect(80,
			"Nipple Creampie",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			AbstractClothing pump = target.getClothingInSlot(InventorySlot.NIPPLE);
			if(pump!=null && pump.isMilkingEquipment()) {
				target.clearFluidsStored(SexAreaOrifice.NIPPLE);
				if(!Main.game.isInSex()) {
					return "<p>"
								+ "All of the cum from your creampied nipples is quickly sucked away by your "+pump.getName()+"!"
							+ "</p>";
				}
			}
			
			if(Main.game.isInSex()) {
				return "";
			}
			
			float cumLost = SexAreaOrifice.NIPPLE.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			StringBuilder sb = new StringBuilder();

			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES);
			boolean dirtyArea = clothingBlocking==null;
			
			if(clothingBlocking!=null && !clothingBlocking.getItemTags().contains(ItemTag.PLUGS_NIPPLES) && !clothingBlocking.getItemTags().contains(ItemTag.SEALS_NIPPLES)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					sb.append("<p>"
								+ "The cum from your creampied nipples quickly </b><b style='color:"+PresetColour.CUM.toWebHexString()+";'>dirties</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+"!"
							+ "</p>");
				}
				dirtyArea = true;
			}
			
			if(dirtyArea) {
				if(!target.getDirtySlots().contains(InventorySlot.NIPPLE)) {
					target.addDirtySlot(InventorySlot.NIPPLE);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.NIPPLE, cumLost);
			
			return sb.toString();
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumLost = SexAreaOrifice.NIPPLE.getCharactersCumLossPerSecond(target) * 60;
			float cumInArea = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE);
			float absorption = SexAreaOrifice.NIPPLE.getCumAbsorptionPerSecond() * 60;
			
			if(target.isOrificePlugged(SexAreaOrifice.NIPPLE)) {
				if(target.isPlayer()) {
					return "As you walk, you can feel the cum trapped within your recently-used [pc.nipples].<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "[style.boldTerrible(Plugged Nipples:)] No cum is leaking out, although it is still being absorbed at a rate of -"+Units.fluid(absorption, ValueType.PRECISE)+"/minute!";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.nipples] have recently been filled with cum, before being plugged.<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "[style.boldTerrible(Plugged Nipples:)] No cum is leaking out, although it is still being absorbed at a rate of -"+Units.fluid(absorption, ValueType.PRECISE)+"/minute!");
				}
				
			} else {
				if(target.isPlayer()) {
					return "As you walk, you can feel cum drooling out of your recently-used [pc.nipples].<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.nipples] have recently been filled with cum.<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)");
				}
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.NIPPLE)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.NIPPLE);
		}
	};

	public static AbstractStatusEffect CREAMPIE_NIPPLES_CROTCH = new AbstractStatusEffect(80,
			"Nipple Creampie",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Udder-nipple creampie";
			} else {
				return "Crotch-nipple creampie";
			}
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			AbstractClothing pump = target.getClothingInSlot(InventorySlot.STOMACH);
			if(pump!=null && pump.isMilkingEquipment()) {
				target.clearFluidsStored(SexAreaOrifice.NIPPLE_CROTCH);
				if(!Main.game.isInSex()) {
					return "<p>"
								+ "All of the cum from your creampied nipples is quickly sucked away by your "+pump.getName()+"!"
							+ "</p>";
				}
			}
			
			if(Main.game.isInSex()) {
				return "";
			}
			
			float cumLost = SexAreaOrifice.NIPPLE_CROTCH.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			StringBuilder sb = new StringBuilder();
			
			if(target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH)!=null){
				if(!target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH).isDirty()) {
					target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH).setDirty(target, true);
					sb.append("<p>"
								+ "The cum from your creampied nipples quickly </b><b style='color:"+PresetColour.CUM.toWebHexString()+";'>dirties</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH).getName()+"!"
							+ "</p>");
				}
			}
			
			if(!target.getDirtySlots().contains(InventorySlot.STOMACH)) {
				target.addDirtySlot(InventorySlot.STOMACH);
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.NIPPLE_CROTCH, cumLost);
			
			return sb.toString();
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumLost = SexAreaOrifice.NIPPLE_CROTCH.getCharactersCumLossPerSecond(target) * 60;
			float cumInArea = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE_CROTCH);
			float absorption = SexAreaOrifice.NIPPLE_CROTCH.getCumAbsorptionPerSecond() * 60;
			
			if(target.isOrificePlugged(SexAreaOrifice.NIPPLE_CROTCH)) {
				if(target.isPlayer()) {
					return "As you walk, you can feel the cum trapped within your recently-used [pc.crotchNipples].<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "[style.boldTerrible(Plugged Crotch-nipples:)] No cum is leaking out, although it is still being absorbed at a rate of -"+Units.fluid(absorption, ValueType.PRECISE)+"/minute!";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.crotchNipples] have recently been filled with cum, before being plugged.<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "[style.boldTerrible(Plugged Crotch-nipples:)] No cum is leaking out, although it is still being absorbed at a rate of -"+Units.fluid(absorption, ValueType.PRECISE)+"/minute!");
				}
				
			} else {
				if(target.isPlayer()) {
					return "As you walk, you can feel cum drooling out of your recently-used [pc.crotchNipples].<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.crotchNipples] have recently been filled with cum.<br/>"
							+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
							+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)");
				}
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.NIPPLE_CROTCH)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.NIPPLE_CROTCH);
		}
	};
	
	public static AbstractStatusEffect CREAMPIE_MOUTH = new AbstractStatusEffect(80,
			"Cummy Meal",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			return target.isOnlyCumInArea(SexAreaOrifice.MOUTH)
					?"Cummy meal"
					:"Yummy meal";
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			if(this.getExtraEffects(target)!=null) {
				attributeModifiersList.addAll(this.getExtraEffects(target));
			}
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.MOUTH.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			target.drainTotalFluidsStored(SexAreaOrifice.MOUTH, cumLost);
			
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumLost = SexAreaOrifice.MOUTH.getCharactersCumLossPerSecond(target) * 60;
			float cumInArea = target.getTotalFluidInArea(SexAreaOrifice.MOUTH);
			
			return UtilText.parse(target, 
					target.isOnlyCumInArea(SexAreaOrifice.MOUTH)
					?"[npc.NameHasFull] recently swallowed a load of cum.<br/>"
						+ "Current cum in stomach: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
						+ "(-"+Units.fluid(cumLost)+"/minute)"
					:"[npc.NameHasFull] recently swallowed some sexual fluids.<br/>"
						+ "Current fluids in stomach: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
						+ "(-"+Units.fluid(cumLost)+"/minute)");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.MOUTH)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.MOUTH);
		}
	};

	public static AbstractStatusEffect CREAMPIE_SPINNERET = new AbstractStatusEffect(80,
			"Spinneret Creampie",
			"creampie",
			PresetColour.CUM,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues("<b style='color: " + PresetColour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(	new Value<>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.SPINNERET.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			StringBuilder sb = new StringBuilder();
			
			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(SexAreaOrifice.SPINNERET.getRelatedCoverableArea(target));
			boolean dirtyArea = clothingBlocking==null;
			
			if(dirtyArea) {
				InventorySlot spinneretSlot = SexAreaOrifice.SPINNERET.getRelatedInventorySlot(target);
				if(!target.getDirtySlots().contains(spinneretSlot)) {
					target.addDirtySlot(spinneretSlot);
				}
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.SPINNERET, cumLost);
			
			return sb.toString();
		}
		@Override
		public String getDescription(GameCharacter target) {
			float cumLost = SexAreaOrifice.SPINNERET.getCharactersCumLossPerSecond(target) * 60;
			float cumInArea = target.getTotalFluidInArea(SexAreaOrifice.SPINNERET);
			
			if(target.isPlayer()) {
				return "As you walk, you can feel cum drooling out of your recently-used spinneret.<br/>"
						+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
						+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)";
			} else {
				return UtilText.parse(target, 
						"[npc.NamePos] spinneret has recently been filled with cum.<br/>"
						+ "Current creampie: [style.colourSex("+Units.fluid(cumInArea)+")]<br/>"
						+ "(-"+Units.fluid(cumLost, ValueType.PRECISE)+"/minute)");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.SPINNERET)>0;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.SPINNERET);
		}
	};
	
	public static AbstractStatusEffect CUM_INFLATION_1 = new AbstractStatusEffect(80,
			"swollen belly",
			"inflation_stomach_1",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.ANUS).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.MOUTH).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target.isPlayer()) {
				return "After being filled with a considerable amount of "+Util.stringsToStringList(fluidNames, false)+", your belly is now a little swollen."
						+ " Your extra weight makes it a little more difficult to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a considerable amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] belly is now a little swollen.");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS) + target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CUM_INFLATION_2 = new AbstractStatusEffect(80,
			"inflated belly",
			"inflation_stomach_2",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.ANUS).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.MOUTH).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target.isPlayer()) {
				return "After being filled with a huge amount of "+Util.stringsToStringList(fluidNames, false)+", your belly is now noticeably inflated."
						+ " The considerable amount of extra weight in your stomach makes it more difficult to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a huge amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] belly is now noticeably inflated."
							+ " The considerable amount of extra weight in [npc.her] stomach is hindering [npc.her] ability to move.");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS) + target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CUM_INFLATION_3 = new AbstractStatusEffect(80,
			"over-inflated belly",
			"inflation_stomach_3",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.ANUS).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.MOUTH).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target.isPlayer()) {
				return "After being filled with a colossal amount of "+Util.stringsToStringList(fluidNames, false)+", your belly is now massively over-inflated."
						+ " The huge amount of extra weight in your stomach is making it extremely difficult for you to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a colossal amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] belly is now massively over-inflated."
									+ " The huge amount of extra weight in [npc.her] stomach is making it extremely difficult for [npc.herHim] to move around.");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS) + target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	
	public static AbstractStatusEffect BREAST_CUM_INFLATION_1 = new AbstractStatusEffect(80,
			"swollen breasts",
			"inflation_breasts_1",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.NIPPLE).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target.isPlayer()) {
				return "After being filled with a considerable amount of "+Util.stringsToStringList(fluidNames, false)+", your [pc.breasts] are now a little swollen."
						+ " Your extra weight makes it a little more difficult to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a considerable amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.breasts] are now a little swollen.");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect BREAST_CUM_INFLATION_2 = new AbstractStatusEffect(80,
			"inflated breasts",
			"inflation_breasts_2",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.NIPPLE).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target.isPlayer()) {
				return "After being filled with a huge amount of "+Util.stringsToStringList(fluidNames, false)+", your [pc.breasts] are now noticeably inflated."
						+ " The considerable amount of extra weight in your top-half is making it more difficult to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a huge amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.breasts] are now noticeably inflated."
							+ " The considerable amount of extra weight in [npc.her] top-half is hindering [npc.her] ability to move.");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect BREAST_CUM_INFLATION_3 = new AbstractStatusEffect(80,
			"over-inflated breasts",
			"inflation_breasts_3",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.NIPPLE).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target.isPlayer()) {
				return "After being filled with a colossal amount of "+Util.stringsToStringList(fluidNames, false)+", your [pc.breasts] are now massively over-inflated."
						+ " The huge amount of extra weight in your top-half is making it extremely difficult for you to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a colossal amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.breasts] are now massively over-inflated."
									+ " The huge amount of extra weight in [npc.her] top-half is making it extremely difficult for [npc.herHim] to move around.");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SPINNERET_CUM_INFLATION_1 = new AbstractStatusEffect(80,
			"swollen spinneret",
			"inflation_spinneret_1",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.SPINNERET).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target!=null) {
				if(target.hasTailSpinneret()) {
					return UtilText.parse(target,
							"After being filled with a considerable amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.tail] "+(target.getTailCount()>1?"are":"is")+"now a little swollen."
							+ " The extra weight is making it a little difficult for [npc.herHim] to move around.");
				} else {
					return UtilText.parse(target,
							"After being filled with a considerable amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] abdomen is now a little swollen."
							+ " The extra weight is making it a little difficult for [npc.herHim] to move around.");
				}
			}
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.SPINNERET);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SPINNERET_CUM_INFLATION_2 = new AbstractStatusEffect(80,
			"inflated spinneret",
			"inflation_spinneret_2",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.SPINNERET).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target!=null) {
				if(target.hasTailSpinneret()) {
					return UtilText.parse(target,
							"After being filled with a huge amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.tail] "+(target.getTailCount()>1?"are":"is")+"now noticeably inflated."
							+ " The extra weight is hindering [npc.her] ability to move.");
				} else {
					return UtilText.parse(target,
							"After being filled with a huge amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] abdomen is now noticeably inflated."
							+ " The extra weight is hindering [npc.her] ability to move.");
				}
			}
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.SPINNERET);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect SPINNERET_CUM_INFLATION_3 = new AbstractStatusEffect(80,
			"over-inflated spinneret",
			"inflation_spinneret_3",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			List<String> fluidNames = new ArrayList<>();
			for(FluidTypeBase ftb : FluidTypeBase.values()) {
				if(target.getFluidsStoredInOrifice(SexAreaOrifice.SPINNERET).stream().anyMatch(f->f.getFluid().getType().getBaseType()==ftb)) {
					fluidNames.add(Util.randomItemFrom(ftb.getNames()));
				}
			}
			if(fluidNames.isEmpty()) {
				fluidNames.add("liquid");
			}
			if(target!=null) {
				if(target.hasTailSpinneret()) {
					return UtilText.parse(target,
							"After being filled with a colossal amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] [npc.tail] "+(target.getTailCount()>1?"are":"is")+"now massively over-inflated."
							+ " The huge amount of extra weight is making it extremely difficult for [npc.herHim] to move around.");
				} else {
					return UtilText.parse(target,
							"After being filled with a colossal amount of "+Util.stringsToStringList(fluidNames, false)+", [npc.namePos] abdomen is now massively over-inflated."
							+ " The huge amount of extra weight is making it extremely difficult for [npc.herHim] to move around.");
				}
			}
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			float cumAmount = target.getTotalFluidInArea(SexAreaOrifice.SPINNERET);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FRUSTRATED_NO_ORGASM = new AbstractStatusEffect(80,
			"Frustrated",
			"frustrated",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] recently had a sexual encounter in which [npc.she] didn't manage to cum."
					+ " As a result, [npc.sheIs] feeling extremely horny and frustrated...");
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
//			if(target.getLastTimeOrgasmedSeconds()>=Main.game.getSecondsPassed()-(60*60*24)) {
//				target.removeStatusEffect(FRUSTRATED_NO_ORGASM); // Remove this effect if orgasmed within the last day
//			}
			return "";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PENT_UP_SLAVE = new AbstractStatusEffect(80,
			"Pent-up",
			"frustrated",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has]n't had any sexual relief for over a day now, and [npc.is] feeling extremely pent-up...");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.isPlayer()
					&& target.isSlave()
					&& ((NPC)target).getLastTimeOrgasmedSeconds()+(60*60*24)<Main.game.getSecondsPassed();
		}
	};
	
	public static AbstractStatusEffect CHASTITY_1 = new AbstractStatusEffect(80,
			"Forced chastity (calm)",
			"chastity1",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("[style.colourSex(Increasing in intensity...)]")) {
		@Override
		public String getDescription(GameCharacter target) {//TODO if one key-holder, mention them
			return UtilText.parse(target, "[npc.Name] [npc.has] been locked in chastity, and will sooner or later start to feel frustrated at not being able to have a proper orgasm...");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(target.isWearingChastity()) {
				if(target.hasStatusEffect(CHASTITY_REMOVED_2)) {
					target.addStatusEffect(CHASTITY_2, 60*60*24*2); // 2 instead of 5
					target.removeStatusEffect(CHASTITY_REMOVED_2);
					
				} else if(target.hasStatusEffect(CHASTITY_REMOVED_3)) {
					target.addStatusEffect(CHASTITY_3, 60*60*24*3); // 3 instead of 7
					target.removeStatusEffect(CHASTITY_REMOVED_3);
					
				} else if(target.hasStatusEffect(CHASTITY_REMOVED_4)) {
					target.addStatusEffect(CHASTITY_4, -1);
					target.removeStatusEffect(CHASTITY_REMOVED_4);
					
				} else {
					target.addStatusEffect(CHASTITY_2, 60*60*24*5); // 5 days
				}
			}
			return "";
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.hasStatusEffect(CHASTITY_REMOVED_2) || target.hasStatusEffect(CHASTITY_REMOVED_3) || target.hasStatusEffect(CHASTITY_REMOVED_4)) {
				target.removeStatusEffect(CHASTITY_1);
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isWearingChastity()
					&& !target.hasStatusEffect(CHASTITY_2)
					&& !target.hasStatusEffect(CHASTITY_3)
					&& !target.hasStatusEffect(CHASTITY_4);
		}
		@Override
		public int getApplicationLength() {
			return 60*60*24*2; // 2 days
		}
		@Override
		public boolean isConstantRefresh() {
			return false;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_2 = new AbstractStatusEffect(80,
			"Forced chastity (restless)",
			"chastity2",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -5f)),
			Util.newArrayListOfValues("[style.colourSex(Increasing in intensity...)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] been locked in chastity for over two days now, and [npc.is] already starting to feel frustrated at not being able to have a proper orgasm...");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(target.isWearingChastity()) {
				target.addStatusEffect(CHASTITY_3, 60*60*24*7); // 7 days
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_3 = new AbstractStatusEffect(80,
			"Forced chastity (pent-up)",
			"chastity3",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			Util.newArrayListOfValues("[style.colourSex(Increasing in intensity...)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] been locked in chastity for over a week now, and [npc.is] feeling very frustrated and pent-up at not being able to have a proper orgasm...");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(target.isWearingChastity()) {
				target.addStatusEffect(CHASTITY_4, -1); // This should be enough to prevent the effect from looping back to CHASTITY_1, as once CHASTITY_4 is applied, CHASTITY_1's conditions are now false, and therefore CHASTITY_4's are true
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_4 = new AbstractStatusEffect(80,
			"Forced chastity (desperate)",
			"chastity4",
			PresetColour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -50f)),
			Util.newArrayListOfValues("[style.colourSex(Maximum intensity)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] [npc.has] been locked in chastity for over two weeks now, and [npc.is] feeling extremely frustrated and pent-up at not being able to have a proper orgasm...");
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(!target.isWearingChastity()) {
				target.addStatusEffect(CHASTITY_REMOVED_4, 60*60*24*1); // 1 day (This effect needs this while the others don't due to the use of the isConditionsMet() method)
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isWearingChastity()
					 && !target.hasStatusEffect(CHASTITY_1)
					 && !target.hasStatusEffect(CHASTITY_2)
					 && !target.hasStatusEffect(CHASTITY_3);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean forceLoad() {
			return true;
		}
	};

	public static AbstractStatusEffect CHASTITY_REMOVED_2 = new AbstractStatusEffect(80,
			"Released from chastity (restless)",
			"chastityRemoved2",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -5f),
					new Value<>(Attribute.RESTING_LUST, 5f)),
			Util.newArrayListOfValues(
					"[style.colourSex(Orgasming)] will remove this effect!")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Recently released from a short spell of being locked in chastity, [npc.name] [npc.is] now feeling very horny!");
			sb.append("<br/>");
			sb.append("[style.italicsSex(If [npc.sheIsFull] locked back into chastity while still under this effect, [npc.she] will remain in the 'restless' stage!)]");
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(CHASTITY_2);
			return "";
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
//			if(totalSecondsPassed==0) {
//				target.removeStatusEffect(CHASTITY_2);
//			}
			if(target.getLastTimeOrgasmedSeconds()>=Main.game.getSecondsPassed()-(60*60*24)) {
				target.removeStatusEffect(CHASTITY_REMOVED_2); // Remove this effect if orgasmed within the last day (the duration of this effect)
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.isWearingChastity() && target.hasStatusEffect(CHASTITY_2);
		}
		@Override
		public int getApplicationLength() {
			return 60*60*24*1; // 1 day
		}
		@Override
		public boolean isConstantRefresh() {
			return false;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_REMOVED_3 = new AbstractStatusEffect(80,
			"Released from chastity (pent-up)",
			"chastityRemoved3",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -15f),
					new Value<>(Attribute.RESTING_LUST, 10f)),
			Util.newArrayListOfValues(
					"[style.colourSex(Orgasming)] will remove this effect!")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Recently released from a long spell of being locked in chastity, [npc.name] [npc.is] now feeling extremely horny!");
			sb.append("<br/>");
			sb.append("[style.italicsSex(If [npc.sheIsFull] locked back into chastity while still under this effect, [npc.she] will remain in the 'pent-up' stage!)]");
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public String applyAdditionEffect(GameCharacter target) {
			target.removeStatusEffect(CHASTITY_3);
			return "";
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
//			if(totalSecondsPassed==0) {
//				target.removeStatusEffect(CHASTITY_3);
//			}
			if(target.getLastTimeOrgasmedSeconds()>=Main.game.getSecondsPassed()-(60*60*24)) {
				target.removeStatusEffect(CHASTITY_REMOVED_3); // Remove this effect if orgasmed within the last day (the duration of this effect)
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.isWearingChastity() && target.hasStatusEffect(CHASTITY_3);
		}
		@Override
		public int getApplicationLength() {
			return 60*60*24*1; // 1 day
		}
		@Override
		public boolean isConstantRefresh() {
			return false;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CHASTITY_REMOVED_4 = new AbstractStatusEffect(80,
			"Released from chastity (desperate)",
			"chastityRemoved4",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.RESTING_LUST, 15f)),
			Util.newArrayListOfValues(
					"[style.colourSex(Orgasming)] will remove this effect!")) {
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder sb = new StringBuilder();
			sb.append("Recently released from an excruciatingly-long spell of being locked in chastity, [npc.name] [npc.is] now feeling unbelievably horny!");
			sb.append("<br/>");
			sb.append("[style.italicsSex(If [npc.sheIsFull] locked back into chastity while still under this effect, [npc.she] will remain in the 'desperate' stage!)]");
			return UtilText.parse(target, sb.toString());
		}
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(target.getLastTimeOrgasmedSeconds()>=Main.game.getSecondsPassed()-(60*60*24)) {
				target.removeStatusEffect(CHASTITY_REMOVED_4); // Remove this effect if orgasmed within the last day (the duration of this effect)
			}
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
//		@Override
//		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
//			if(totalSecondsPassed==0) {
//				target.removeStatusEffect(CHASTITY_4);
//			}
//			return "";
//		}
//		@Override
//		public boolean isConditionsMet(GameCharacter target) {
//			return !target.isWearingChastity() && target.hasStatusEffect(CHASTITY_4);
//		}
//		@Override
//		public int getApplicationLength() {
//			return 60*60*24*1; // 1 day
//		}
//		@Override
//		public boolean isConstantRefresh() {
//			return false;
//		}
	};
	
	public static AbstractStatusEffect RECOVERING_AURA = new AbstractStatusEffect(80,
			"Strengthened aura",
			"recoveringAura",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Due to a recent orgasm, your arcane aura has been temporarily strengthened."
						+ " While in this state, you will no longer receive an arcane essence if you orgasm!";
			} else {
				return UtilText.parse(target, "Due to a recent orgasm, [npc.namePos] arcane aura has been temporarily strengthened."
						+ " While [npc.she] remains in this state, you will not receive an arcane essence if [npc.she] orgasms in your presence!");
			}
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect EXPOSED = new AbstractStatusEffect(80,
			"exposed",
			"exposed",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.GENERIC_BAD,
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] highly embarrassed to be walking around in such an exposed fashion.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your exposed body parts attract all kind of lewd gazes.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& target.getLegConfiguration()==LegConfiguration.BIPEDAL
					&& !target.isFeral()
					&& isExposedParts(target, false, true);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};

	public static AbstractStatusEffect EXPOSED_ANIMAL = new AbstractStatusEffect(80,
			"exposed (feral parts)",
			"exposedFeral",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_TAN,
			PresetColour.BASE_TAN,
			false,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			if(target.getLegConfiguration().isGenitalsExposed(target)) {
				return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", but as [npc.she] [npc.has] a feral body, [npc.she] [npc.verb(feel)] as though it's natural to be so exposed.");
			} else {
				return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", but [npc.her] feral body is shaped in such a way that they aren't on public display.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& (target.getLegConfiguration()!=LegConfiguration.BIPEDAL || target.isFeral())
					&& !((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaVisible(CoverableArea.NIPPLES))
					&& ((target.hasBreastsCrotch()
							&& (Main.getProperties().getUddersLevel()>0 || target.isFeral())
							&& target.isCoverableAreaVisible(CoverableArea.NIPPLES_CROTCH))
						|| target.isCoverableAreaVisible(CoverableArea.ANUS)
						|| (target.isCoverableAreaVisible(CoverableArea.PENIS) && target.hasPenis())
						|| (target.isCoverableAreaVisible(CoverableArea.VAGINA) && target.hasVagina()));
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect EXPOSED_BREASTS = new AbstractStatusEffect(80,
			"exposed breasts",
			"exposed",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.GENERIC_BAD,
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 5f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -2f)),
			null) {
		@Override
		public String getName(GameCharacter target) {
			if (! target.hasBreasts()) {
				return "exposed nipples";
			} else {
				return super.getName(target);
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] highly embarrassed to be walking around in such an exposed fashion.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your exposed breasts attract all kind of lewd gazes.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& (target.getLegConfiguration()==LegConfiguration.BIPEDAL || ((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaVisible(CoverableArea.NIPPLES)))
					&& !target.isFeral()
					&& isExposedParts(target, true, false);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect EXPOSED_PLUS_BREASTS = new AbstractStatusEffect(80,
			"exposed",
			"exposed",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.GENERIC_BAD,
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] highly embarrassed to be walking around in such an exposed fashion.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your exposed body parts attract all kind of lewd gazes.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& target.getLegConfiguration()==LegConfiguration.BIPEDAL
					&& !target.isFeral()
					&& isExposedParts(target, true, true);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect FETISH_EXHIBITIONIST = new AbstractStatusEffect(80,
			"exhibitionist",
			"exposedExhibitionist",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_PINK_DEEP,
			PresetColour.BASE_PINK_DEEP,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] incredibly sexy and empowered to be walking around in such an exposed fashion.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your exposed body parts attract all kind of lewd gazes.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& isExposedParts(target, false, true);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect FETISH_EXHIBITIONIST_BREASTS = new AbstractStatusEffect(80,
			"exhibitionist",
			"exposedExhibitionist",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_PINK_DEEP,
			PresetColour.BASE_PINK_DEEP,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] incredibly sexy and empowered to be walking around in such an exposed fashion.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your exposed body parts attract all kind of lewd gazes.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& isExposedParts(target, true, false);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};
	
	public static AbstractStatusEffect FETISH_EXHIBITIONIST_PLUS_BREASTS = new AbstractStatusEffect(80,
			"exhibitionist",
			"exposedExhibitionist",
			PresetColour.BASE_PINK_LIGHT,
			PresetColour.BASE_PINK_DEEP,
			PresetColour.BASE_PINK_DEEP,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 30f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] incredibly sexy and empowered to be walking around in such an exposed fashion.");
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer()) {
				return new Value<>(2, "<b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your exposed body parts attract all kind of lewd gazes.");
			}
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& isExposedParts(target, true, true);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	};

	public static AbstractStatusEffect FETISH_PURE_VIRGIN = new AbstractStatusEffect(80,
			"Pure Virgin",
			"virginPure",
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(represent)] the perfect image of a pure, righteous being. [npc.Her] noble bearing and virtuous personality mark [npc.herHim] as a paragon of all that is angelic and good in this world.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN)
					&& !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& target.isVaginaVirgin()
					&& target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_PURE_VIRGIN_NO_HYMEN = new AbstractStatusEffect(80,
			"'Pure' Virgin",
			"virginPureNoHymen",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 10f),
					new Value<>(Attribute.MAJOR_CORRUPTION, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(act)] as though [npc.sheIs] a noble and virtuous paragon of purity, but [npc.sheIs] also suspiciously passionate about arguing that a broken hymen does not disqualify a person from being considered a pure virgin...");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN)
					&& !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& target.isVaginaVirgin()
					&& !target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_PURE_VIRGIN_ONLY_HYMEN = new AbstractStatusEffect(80,
			"Pure 'Virgin'",
			"virginPureRepaired",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(act)] as though [npc.sheIs] a noble and virtuous paragon of purity, but [npc.sheIs] also suspiciously passionate about arguing that having an intact hymen is enough to technically make anyone a virgin...");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN)
					&& !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& !target.isVaginaVirgin()
					&& target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_BROKEN_VIRGIN = new AbstractStatusEffect(80,
			"Broken Virgin",
			"virginBroken",
			PresetColour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Losing [npc.her] precious virginity has hit [npc.name] hard, and [npc.she] now [npc.verb(see)] [npc.herself] as nothing but a dirty slut."
						+ " [npc.She] now often fantasises about big, thick cocks slamming deep into [npc.her] worthless cunt, before defiling [npc.her] womb with their foul cum...");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN)
					&& !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& !target.isVaginaVirgin()
					&& !target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_LUSTY_MAIDEN = new AbstractStatusEffect(80,
			"Lusty Maiden",
			"virginLustyMaidenPure",
			PresetColour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.RESISTANCE_LUST, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIs] more than willing to use [npc.her] ass, mouth, breasts, and even the promise of [npc.her] pussy in order to please [npc.her] partners,"
							+ " but [npc.she]'ll never let anyone actually penetrate [npc.her] feminine sex and rob [npc.herHim] of [npc.her] precious virginity!");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& target.isVaginaVirgin()
					&& target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_LUSTY_MAIDEN_NO_HYMEN = new AbstractStatusEffect(80,
			"Lusty 'Maiden'",
			"virginLustyMaidenNoHymen",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(act)] as though [npc.her] pussy is completely unspoiled and off-limits,"
							+ " but [npc.sheIs] also suspiciously passionate about arguing that a broken hymen does not disqualify a person from being considered a pure virgin...");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& target.isVaginaVirgin()
					&& !target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_LUSTY_MAIDEN_ONLY_HYMEN = new AbstractStatusEffect(80,
			"Lusty 'Maiden'",
			"virginLustyMaidenRepaired",
			PresetColour.GENERIC_GOOD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(act)] as though [npc.her] pussy is completely unspoiled and off-limits,"
							+ " but [npc.sheIs] also suspiciously passionate about arguing that having an intact hymen is enough to technically make anyone a virgin...");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& !target.isVaginaVirgin()
					&& target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FETISH_LUSTY_MAIDEN_BROKEN = new AbstractStatusEffect(80,
			"Broken Maiden",
			"virginLustyMaidenBroken",
			PresetColour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, -25f),
					new Value<>(Attribute.RESISTANCE_LUST, -50f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 50f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Losing [npc.her] precious virginity has hit [npc.name] hard, and [npc.she] now [npc.verb(see)] [npc.herself] as nothing but a dirty slut."
						+ " All of [npc.her] efforts to keep [npc.her] pussy pure by using [npc.her] other assets was wasted, and now all [npc.she] fantasises about is using [npc.her] worthless cunt as a public cumdump...");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN)
					&& target.hasVagina()
					&& !target.isVaginaVirgin()
					&& !target.hasHymen();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	
	// CLOTHING SETS:
	
	public static AbstractStatusEffect SET_MAID = new AbstractStatusEffect(70,
			"Hard-working Maid",
			"clothingSets/maid",
			PresetColour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "By wearing the entire Maid's outfit, [npc.nameIsFUll] filled with the energy [npc.she] [npc.verb(need)] in order to be a sexy hard-working maid.");
					
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_maid").isCharacterWearingCompleteSet(target) && !target.hasTrait(Perk.JOB_MAID, true);
		}
	};
	
	public static AbstractStatusEffect SET_MAID_BOOSTED = new AbstractStatusEffect(70,
			"Professional Maid",
			"clothingSets/maid_boosted",
			PresetColour.CLOTHING_BLACK,
			PresetColour.BASE_GOLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "By wearing the entire Maid's outfit, [npc.nameIsFull] reminded of [npc.her] true profession; that of an exceptionally talented (and sexy) maid!");
				
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_maid").isCharacterWearingCompleteSet(target) && target.hasTrait(Perk.JOB_MAID, true);
		}
	};
	
	public static AbstractStatusEffect SET_MILK_MAID = new AbstractStatusEffect(70,
			"Milk Maid",
			"clothingSets/milk_maid",
			PresetColour.BASE_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "By wearing the entire Milk Maid's outfit, [npc.nameIsFull] filled with the energy [npc.she] [npc.verb(need)] in order to perform all of [npc.her] milk maid's duties.");
				
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_milk_maid").isCharacterWearingCompleteSet(target) && !target.hasTrait(Perk.JOB_MAID, true);
		}
	};
	
	public static AbstractStatusEffect SET_MILK_MAID_BOOSTED = new AbstractStatusEffect(70,
			"Professional Milk Maid",
			"clothingSets/milk_maid_boosted",
			PresetColour.BASE_WHITE,
			PresetColour.BASE_GOLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.DAMAGE_LUST, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "By wearing the entire Milk Maid's outfit, [npc.nameIsFull] reminded of [npc.her] true profession; that of an exceptionally talented (and sexy) milk maid.");
				
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_milk_maid").isCharacterWearingCompleteSet(target) && target.hasTrait(Perk.JOB_MAID, true);
		}
	};
	
	public static AbstractStatusEffect SET_BUTLER = new AbstractStatusEffect(70,
			"Butler",
			"clothingSets/butler",
			PresetColour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "By wearing the entire Butler's outfit, [npc.nameIsFull] filled with the energy [npc.she] [npc.verb(need)] in order to carry out [npc.her] duties as a butler.");
					
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_butler").isCharacterWearingCompleteSet(target) && !target.hasTrait(Perk.JOB_BUTLER, true);
		}
	};
	
	public static AbstractStatusEffect SET_BUTLER_BOOSTED = new AbstractStatusEffect(70,
			"Professional Butler",
			"clothingSets/butler_boosted",
			PresetColour.CLOTHING_WHITE,
			PresetColour.BASE_GOLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "By wearing the entire Butler's outfit, [npc.nameIsFull] reminded of [npc.her] true profession; that of an exceptionally talented butler!");
					
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_butler").isCharacterWearingCompleteSet(target) && target.hasTrait(Perk.JOB_BUTLER, true);
		}
	};
	
	public static AbstractStatusEffect SET_WITCH = new AbstractStatusEffect(70,
			"Arcane Witch",
			"clothingSets/witch",
			PresetColour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the complete set of witch's clothes, you feel your arcane power growing stronger.";
					
				} else {
					return UtilText.parse(target, "By wearing the complete set of witch's clothes, [npc.namePos] arcane power has grown stronger.");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_witch").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_SCIENTIST = new AbstractStatusEffect(70,
			"Scientist",
			"clothingSets/scientist",
			PresetColour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.RESISTANCE_FIRE, 2f),
					new Value<>(Attribute.RESISTANCE_POISON, 2f),
					new Value<>(Attribute.RESISTANCE_ICE, 2f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By donning both a lab coat and safety goggles, you're confident that no chemical spill will harm you!";
					
				} else {
					return UtilText.parse(target, "By wearing both a lab coat and safety goggles, [npc.name] is well-protected against any chemical spill.");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_scientist").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_SLUTTY_ENFORCER = new AbstractStatusEffect(70,
			"slutty Enforcer",
			"clothingSets/slutty_enforcer",
			PresetColour.BASE_PINK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f),
					new Value<>(Attribute.MAJOR_CORRUPTION, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIsFull] wearing a slutty fancy-dress version of an Enforcer's uniform, making [npc.herHim] feel extremely sexy.");
					
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_slutty_enforcer").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_RAINBOW = new AbstractStatusEffect(70,
			"double rainbow",
			"clothingSets/rainbow",
			PresetColour.CLOTHING_RED,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return "Double rainbow... What does it mean?!";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_rainbow").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_DARK_SIREN = new AbstractStatusEffect(70,
			"Dark Siren",
			"clothingSets/dark_siren",
			PresetColour.CLOTHING_PURPLE_DARK,
			PresetColour.CLOTHING_BLACK_STEEL,
			PresetColour.CLOTHING_RED_DARK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<>(Attribute.SPELL_COST_MODIFIER, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			return "<i>Darkness and chaos, let the boundaries of the eternal void be shattered! Intangible manifestation of divine will, now let my sealed power be unleashed!</i>";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_dark_siren").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_LYSSIETH_GUARD = new AbstractStatusEffect(70,
			"Lyssieth's Guard",
			"clothingSets/lyssieth_guard",
			PresetColour.CLOTHING_OLIVE,
			PresetColour.CLOTHING_BROWN_DARK,
			PresetColour.CLOTHING_OLIVE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {//British Auxiliary Territorial Service
			return UtilText.parse(target, "While wearing the uniform of Lyssieth's guard, [npc.name] [npc.verb(feel)] as though [npc.sheIs] more easily able to keep [npc.her] composure.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_lyssieth_guard").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_BDSM = new AbstractStatusEffect(70,
			"BDSM",
			"clothingSets/bdsm",
			PresetColour.CLOTHING_BLACK,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, -15f)),
			null) {
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			if(target!=null && target.hasFetish(Fetish.FETISH_BONDAGE_VICTIM)) {
				return Util.newHashMapOfValues(
						new Value<>(Attribute.HEALTH_MAXIMUM, 10f),
						new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f),
						new Value<>(Attribute.DAMAGE_LUST, 10f));
			}
			return super.getAttributeModifiers(target);
		}
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.hasFetish(Fetish.FETISH_BONDAGE_VICTIM)) {
					return UtilText.parse(target, "[npc.Name] [npc.has] been tied up in bondage gear, and [npc.is] absolutely loving the fact that [npc.she] [npc.is] struggling to move.");
				} else {
					return UtilText.parse(target, "[npc.Name] [npc.has] been tied up in bondage gear, and [npc.is] struggling to move.");
				}
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_bdsm").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_CATTLE = new AbstractStatusEffect(70,
			"Cattle",
			"clothingSets/cattle",
			PresetColour.BASE_TAN,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "You are wearing a set of accessories normally found on a cow or bull.";
					
				} else {
					return UtilText.parse(target, "[npc.Name] is wearing a set of accessories normally found on a cow or bull.");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_cattle").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_SNOWFLAKE = new AbstractStatusEffect(70,
			"Glacial",
			"clothingSets/snowflake",
			PresetColour.BASE_BLUE_LIGHT,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.DAMAGE_ICE, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "By donning the complete set of specially enchanted snowflake jewellery, both [npc.namePos] arcane power and ice attacks have become much more potent!");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_snowflake").isCharacterWearingCompleteSet(target);
		}
	};
		
	public static AbstractStatusEffect SET_SUN = new AbstractStatusEffect(70,
			"Radiant",
			"clothingSets/sun",
			PresetColour.BASE_ORANGE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 5f),
					new Value<>(Attribute.DAMAGE_FIRE, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "By donning jewels that sparkle like the sun, [npc.nameIsFull] confident that [npc.her] spells and fire attacks will incinerate [npc.her] foes!");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_sun").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_GEISHA = new AbstractStatusEffect(70,
			"Geisha",
			"clothingSets/geisha",
			PresetColour.BASE_ROSE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.NameIs] wearing a youko's ceremonial outfit, which closely resembles traditional Japanese clothing.");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_geisha").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_RONIN = new AbstractStatusEffect(70,
			"Ronin",
			"clothingSets/ronin",
			PresetColour.BASE_ROSE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "<i>You understand there is something outside yourself that has to be served. And when that need is gone, when belief has died, what are you? [pc.A_man] without a master.</i>";
					
				} else {
					return UtilText.parse(target,
							"<i>[npc.Name] understands there is something outside [npc.herself] that has to be served. And when that need is gone, when belief has died, what is [npc.she]? [npc.A_man] without a master.</i>");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_ronin").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_DAISHO = new AbstractStatusEffect(70,
			"Daisho",
			"clothingSets/daisho",
			PresetColour.BASE_ROSE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 15f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"The Japanese term 'Daisho', meaning 'big-little', is used to describe the simultaneous wearing of a katana (the big) and wakizashi (the little)."
						+ " Wearing both of these swords at once marks the wielder as a member of the samurai class.");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_daisho").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_JOLNIR = new AbstractStatusEffect(70,
			"J&oacute;lnir",
			"clothingSets/jolnir",
			PresetColour.BASE_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_ARCANE, 15f),
					new Value<>(Attribute.DAMAGE_ICE, 15f),
					new Value<>(Attribute.RESISTANCE_ICE, 5f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the outfit of the 'Yule figure', both your wisdom and prowess in battle are greatly increased!";
					
				} else {
					return UtilText.parse(target, "[npc.Name] is wearing the outfit of the 'Yule figure', thereby greatly increasing [npc.her] wisdom and prowess in battle.");
					
				}
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_jolnir").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect SET_KITTY = new AbstractStatusEffect(70,
			"Playful Kitty",
			"clothingSets/kitty",
			PresetColour.CLOTHING_PINK_LIGHT,
			PresetColour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10f)),
			null) {
		@Override
		public StatusEffectCategory getCategory() {
			return StatusEffectCategory.INVENTORY;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "By wearing the entire set of kitty lingerie, [npc.name] [npc.verb(find)] [npc.herself] wanting to tease everyone [npc.she] [npc.verb(meet)]!");
			} else {
				return "";
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return SetBonus.getSetBonusFromId("innoxia_kitty").isCharacterWearingCompleteSet(target);
		}
	};
	
	public static AbstractStatusEffect CLOTHING_EFFECT = new AbstractStatusEffect(70,
			"clothing effects",
			"combatHidden",
			PresetColour.TRANSFORMATION_GENERIC,
			false,
			null,
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			return "-";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return "-";
		}
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	};
	
	public static AbstractStatusEffect POTION_EFFECTS = new AbstractStatusEffect(80,
			"potion effects",
			"potionEffects",
			PresetColour.GENERIC_ARCANE,
			PresetColour.BASE_PINK_LIGHT,
			null,
			false,
			null,
			Util.newArrayListOfValues("[style.italicsMinorGood(Maximum values are 25)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.hasTrait(Perk.JOB_CHEF, true)) {
				return UtilText.parse(target, "Thanks to [npc.namePos] background as a chef, [npc.sheIs] able to harness the full potential of the many arcane-infused consumables found throughout this world...");
			}
			return UtilText.parse(target, "Arcane-infused consumables are very common in this world, and as [npc.nameHasFull] found out, they can have some rather curious effects...");
		}
		@Override
		public Map<AbstractAttribute, Float> getAttributeModifiers(GameCharacter target) {
			return target.getPotionAttributes();
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modifiersList = new ArrayList<>();

			modifiersList.addAll(getExtraEffects(target));
			
			if (getAttributeModifiers(target) != null) {
				for (Entry<AbstractAttribute, Float> e : getAttributeModifiers(target).entrySet()) {
					modifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b>" + " <b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
				}
			}
					
			return modifiersList;
		}
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			if(target.hasTrait(Perk.JOB_CHEF, true)) {
				return Util.newArrayListOfValues("[style.italicsGood(Maximum values are 50)]");
			}
			return super.getExtraEffects(target);
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.clearPotionAttributes();
			return "";
		}
	};
	
	public static AbstractStatusEffect HAPPINESS = new AbstractStatusEffect(70,
			"happiness",
			"happinessFox",
			PresetColour.CLOTHING_SILVER,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<>(Attribute.MANA_MAXIMUM, 5f)),
			Util.newArrayListOfValues("[style.italicsGood(Happiness!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return "The silver-furred fox, <i>Happiness</i>, is following you around wherever you go."
					+ " Whenever you feel tired or down, the cute little animal brushes up against your [pc.legs] and sits still to have its ears scratched, which instantly fills you with happiness.";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isPlayer() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.foundHappiness);
		}
	};
	

	// COMBAT EFFECTS:

	public static AbstractStatusEffect SPECIAL_SILENCE_TRANCE = new AbstractStatusEffect(70,
			"Silence's Trance",
			"glowingEyes",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, 1f),
					new Value<>(Attribute.MANA_MAXIMUM, 100f),
					new Value<>(Attribute.DAMAGE_SPELLS, 50f),
					new Value<>(Attribute.ENERGY_SHIELDING, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return "Upon witnessing the defeat of her beloved friend, Shadow, Silence has summoned an air elemental and entered into an intense trance, granting her improved combat abilities!";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInCombat()
					&& target.equals(Main.game.getNpc(Silence.class))
					&& !Main.combat.getEnemies(Main.game.getPlayer()).contains(Main.game.getNpc(Shadow.class));
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect SPECIAL_SHADOW_BESERK = new AbstractStatusEffect(70,
			"Shadow's Rage",
			"glowingEyes",
			PresetColour.BASE_CRIMSON,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, 3f),
					new Value<>(Attribute.HEALTH_MAXIMUM, 150f),
					new Value<>(Attribute.DAMAGE_MELEE_WEAPON, 50f),
					new Value<>(Attribute.ENERGY_SHIELDING, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return "Upon witnessing the defeat of her beloved friend, Silence, Shadow has entered into a state of frenzied beserker rage, granting her massively improved combat abilities!";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInCombat()
					&& target.equals(Main.game.getNpc(Shadow.class))
					&& Main.combat.getEnemies(Main.game.getPlayer()).size()==1;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect SPECIAL_AMERICAN_FREEDOM = new AbstractStatusEffect(70,
			"Blinded by Freedom",
			"american_freedom",
			PresetColour.BASE_RED,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "[npc.Name] is struggling to fight against your manifest destiny!");
			}
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isStarted()
					&& Main.game.getPlayer().getOccupation()==Occupation.TOURIST
					&& !target.isPlayer()
					&& Main.game.isInCombat()
					&& Main.combat.getEnemies(Main.game.getPlayer()).contains(target);
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect COMBAT_HIDDEN = new AbstractStatusEffect(70,
			"hidden",
			"combatHidden",
			PresetColour.GENERIC_BAD,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return "You don't know what perks, status effects, spells, or special attacks your opponent has available. You require the "+Perk.OBSERVANT.getName(target)+" perk to reveal such information.";
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect DESPERATE_FOR_SEX = new AbstractStatusEffect(70,
			"desperate for sex",
			"desperateForSex",
			PresetColour.ATTRIBUTE_HEALTH,
			PresetColour.ATTRIBUTE_MANA,
			PresetColour.ATTRIBUTE_LUST,
			false,
			null,
			Util.newArrayListOfValues("Incoming <b style='color:"+PresetColour.ATTRIBUTE_LUST.toWebHexString()+";'>Lust damage</b> dealt as"
							+ " <b style='color:"+PresetColour.ATTRIBUTE_HEALTH.toWebHexString()+";'>2*Energy damage</b>"
							+ " and <b style='color:"+PresetColour.ATTRIBUTE_MANA.toWebHexString()+";'>1*Aura damage</b>",
					"<b style='color: " + PresetColour.GENERIC_TERRIBLE.toWebHexString() + "'>Incoming damage ignores all shielding</b>")) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You are absolutely desperate for sex, but thanks to the strength of your arcane aura, you are able to resist giving up right here on the spot!";
			} else {
				return UtilText.parse(target,
						"[npc.Name] is absolutely desperate for sex, but thanks to the strength of [npc.her] arcane aura, [npc.sheIs] able to resist giving up right here on the spot!");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getLust()>=100 && !target.isVulnerableToLustLoss();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	// From spells or combat moves (still in combat):
	
	public static AbstractStatusEffect ARCANE_WEAKNESS = new AbstractStatusEffect(10,
			"arcane weakness",
			"negativeCombatEffect",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -2f),
					new Value<>(Attribute.RESISTANCE_LUST, -2f),
					new Value<>(Attribute.RESISTANCE_FIRE, -2f),
					new Value<>(Attribute.RESISTANCE_ICE, -2f),
					new Value<>(Attribute.RESISTANCE_POISON, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NamePos] head is spinning and [npc.sheIs] struggling to stay upright..");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect DAZED = new AbstractStatusEffect(10,
			"dazed",
			"dazed",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "[npc.NamePos] head is spinning and [npc.sheIs] struggling to stay upright. [npc.SheIs] finding it incredibly difficult to land a hit or dodge any incoming attacks.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CRIPPLE = new AbstractStatusEffect(10,
			"crippled",
			"negativeCombatEffect",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameHasFull] been temporarily crippled, and [npc.sheIs] struggling to do as much damage with [npc.her] attacks as [npc.sheIs] usually able to.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect VULNERABLE = new AbstractStatusEffect(10,
			"vulnerable",
			"negativeCombatEffect",
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] feeling particularly vulnerable, and [npc.she] [npc.is]n't able to defend [npc.herself] to the best of [npc.her] ability.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISONED = new AbstractStatusEffect(10,
			"poisoned",
			"combat_poisoned",
			PresetColour.ATTRIBUTE_HEALTH,
			false,
			null,
			Util.newArrayListOfValues("<b>15</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 15);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "The poison within [npc.namePos] body is taking its toll, and [npc.sheIs] steadily losing [npc.her] strength!");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISONED_LUST = new AbstractStatusEffect(10,
			"lust-poisoned",
			"combat_poisoned",
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues(
					"<b>5</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>",
					"<b>10</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 5);
			Value<String, Integer> lustDamageValue = DamageType.LUST.damageTarget(null, target, 10);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey()
					 + UtilText.parse(target, "<br/>[npc.Name] additionally [npc.verb(take)] <b>" + lustDamageValue.getValue() + "</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+"!")+lustDamageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "The lust poison within [npc.namePos] body is taking its toll, and [npc.sheIs] not only steadily losing [npc.her] strength, but also getting uncontrollably turned on in the process!");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect WITCH_SEAL = new AbstractStatusEffect(10,
			"Witch's Seal",
			"combat_witch_seal",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -3f)),
			Util.newArrayListOfValues("[style.colourTerrible(Cannot attempt to escape!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "A powerful arcane seal is holding [npc.name] firmly in place, preventing [npc.herHim] from taking any action!");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT,
					ItemTag.PREVENTS_COMBAT_ESCAPE);
		}
	};
	
	public static AbstractStatusEffect WITCH_CHARM = new AbstractStatusEffect(10,
			"Bewitching Charm",
			"combat_witch_charm",
			PresetColour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if (target.isPlayer()) {
				return "The <b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Bewitching Charm</b> is making you appear irresistibly attractive!";
				
			} else {
				return UtilText.parse(target,
						"The <b style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Bewitching Charm</b> is making [npc.name] appear irresistibly attractive!");
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				if(target.isFeminine()) {
					return "An arcane enchantment is bewitching anyone who looks upon you, causing them to view you as the most beautiful person they've ever seen.";
				} else {
					return "An arcane enchantment is bewitching anyone who looks upon you, causing them to view you as the most handsome person they've ever seen.";
				}
				
			} else {
				if(target.isFeminine()) {
					return UtilText.parse(target, "An arcane enchantment is bewitching you into viewing [npc.name] as the most beautiful person you've ever seen.");
				} else {
					return UtilText.parse(target, "An arcane enchantment is bewitching you into viewing [npc.name] as the most handsome person you've ever seen.");
				}
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect ROPE_BOUND_SEX = new AbstractStatusEffect(10,
			"Bound in rope",
			"immobilised_rope",
			PresetColour.GENERIC_BAD,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Cannot move!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Strong ropes tied around [npc.namePos] body are holding [npc.herHim] firmly in place, preventing [npc.herHim] from taking any action!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			Value<ImmobilisationType, GameCharacter> type = Main.sex.getImmobilisationType(target);
			return type!=null && type.getKey()==ImmobilisationType.ROPE;
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect CHAINS_BOUND_SEX = new AbstractStatusEffect(10,
			"Bound in chains",
			"immobilised_chains",
			PresetColour.GENERIC_BAD,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Cannot move!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Strong chains tied around [npc.namePos] body are holding [npc.herHim] firmly in place, preventing [npc.herHim] from taking any action!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			Value<ImmobilisationType, GameCharacter> type = Main.sex.getImmobilisationType(target);
			return type!=null && type.getKey()==ImmobilisationType.CHAINS;
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};
	
	public static AbstractStatusEffect WEBBED_1 = new AbstractStatusEffect(10,
			"Webbed",
			"restrain_webbed_1",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Thick, sticky webbing is clinging to [npc.namePos] body, which is causing [npc.her] movements to be somewhat hindered!"
						+ "<br/>[style.italicsMinorBad(If [npc.she] [npc.verb(get)] webbed again, this effect will become more serious!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect WEBBED_2 = new AbstractStatusEffect(10,
			"Seriously webbed",
			"restrain_webbed_2",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -50f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "A large amount of thick, sticky webbing is clinging to [npc.namePos] body, which is causing [npc.her] movements to be significantly hindered!"
						+ "<br/>[style.italicsBad(If [npc.she] [npc.verb(get)] webbed again, this effect will become extremely serious!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT);
		}
	};

	public static AbstractStatusEffect WEBBED_3 = new AbstractStatusEffect(10,
			"Cocooned",
			"restrain_webbed_3",
			PresetColour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -75f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f),
					new Value<>(Attribute.ACTION_POINTS, -2f)),
			Util.newArrayListOfValues("[style.colourTerrible(Cannot attempt to escape!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "A huge amount of thick, sticky webbing is clinging to [npc.namePos] body, making it all but impossible for [npc.herHim] to move [npc.her] [npc.arms] and [npc.legs]!"
						+ "<br/>[style.italicsBad(If [npc.she] [npc.verb(get)] webbed again, [npc.she] will be instantly defeated!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT,
					ItemTag.PREVENTS_COMBAT_ESCAPE);
		}
	};

	public static AbstractStatusEffect WEBBED_SEX = new AbstractStatusEffect(10,
			"Cocooned",
			"immobilised_cocoon",
			PresetColour.GENERIC_BAD,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Cannot move!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "A huge amount of thick, sticky webbing is clinging to [npc.namePos] body, making it all but impossible for [npc.herHim] to move!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			Value<ImmobilisationType, GameCharacter> type = Main.sex.getImmobilisationType(target);
			return type!=null && type.getKey()==ImmobilisationType.COCOON;
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect WITCH_SEAL_SEX = new AbstractStatusEffect(10,
			"Witch's Seal",
			"immobilised_seal",
			PresetColour.GENERIC_ARCANE,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Cannot move!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "A powerful arcane seal is holding [npc.name] firmly in place, preventing [npc.herHim] from taking any action!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			Value<ImmobilisationType, GameCharacter> type = Main.sex.getImmobilisationType(target);
			return type!=null && type.getKey()==ImmobilisationType.WITCH_SEAL;
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect TENTACLE_RESTRAIN_1 = new AbstractStatusEffect(10,
			"Tentacle-grabbed",
			"restrain_tentacles_1",
			PresetColour.GENERIC_BAD,
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Coils of strong tentacles are grabbing at [npc.namePos] body, which is causing [npc.her] movements to be somewhat hindered!"
						+ "<br/>[style.italicsMinorBad(If [npc.she] [npc.verb(get)] tentacle-grabbed again, this effect will become more serious!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect TENTACLE_RESTRAIN_2 = new AbstractStatusEffect(10,
			"Tentacle-embraced",
			"restrain_tentacles_2",
			PresetColour.GENERIC_BAD,
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -50f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Strong tentacles have firmly coiled themselves around [npc.namePos] [npc.arms] and [npc.legs], which is causing [npc.her] movements to be significantly hindered!"
						+ "<br/>[style.italicsBad(If [npc.she] [npc.verb(get)] tentacle-grabbed again, this effect will become extremely serious!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT);
		}
	};

	public static AbstractStatusEffect TENTACLE_RESTRAIN_3 = new AbstractStatusEffect(10,
			"Tentacle-constricted",
			"restrain_tentacles_3",
			PresetColour.GENERIC_BAD,
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -75f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f),
					new Value<>(Attribute.ACTION_POINTS, -2f)),
			Util.newArrayListOfValues("[style.colourTerrible(Cannot attempt to escape!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "Strong tentacles have constricted [npc.namePos] in a vice-like grip, making it all but impossible for [npc.herHim] to move [npc.her] [npc.arms] and [npc.legs]!"
						+ "<br/>[style.italicsBad(If [npc.she] [npc.verb(get)] tentacle-grabbed again, [npc.she] will be instantly defeated!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT,
					ItemTag.PREVENTS_COMBAT_ESCAPE);
		}
	};

	public static AbstractStatusEffect TENTACLE_RESTRICTION_SEX = new AbstractStatusEffect(10,
			"Tentacle-bound",
			"immobilised_tentacles",
			PresetColour.getColourFromId("NoStepOnSnek_octopus"),
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Cannot move!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			Value<ImmobilisationType, GameCharacter> type = Main.sex.getImmobilisationType(target);
			return UtilText.parse(target, type.getValue(), "[npc2.NameIsFull] using four of [npc2.her] [npc2.tentacles] to hold [npc.name] down and firmly prevent [npc.herHim] from moving!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			Value<ImmobilisationType, GameCharacter> type = Main.sex.getImmobilisationType(target);
			return type!=null && type.getKey()==ImmobilisationType.TENTACLE_RESTRICTION;
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect TAIL_RESTRAIN_1 = new AbstractStatusEffect(10,
			"Tail-grabbed",
			"restrain_tail_1",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_GREEN_DARK,
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "A strong, snake-like tail is wrapping itself around [npc.namePos] body, which is causing [npc.her] movements to be somewhat hindered!"
						+ "<br/>[style.italicsMinorBad(If [npc.she] [npc.verb(get)] tail-constricted again, this effect will become more serious!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect TAIL_RESTRAIN_2 = new AbstractStatusEffect(10,
			"Tail-embraced",
			"restrain_tail_2",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_GREEN_DARK,
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -50f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "A strong, snake-like tail has firmly coiled itself around [npc.namePos] [npc.arms] and [npc.legs], which is causing [npc.her] movements to be significantly hindered!"
						+ "<br/>[style.italicsBad(If [npc.she] [npc.verb(get)] tail-constricted again, this effect will become extremely serious!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT);
		}
	};

	public static AbstractStatusEffect TAIL_RESTRAIN_3 = new AbstractStatusEffect(10,
			"Tail-constricted",
			"restrain_tail_3",
			PresetColour.GENERIC_BAD,
			PresetColour.BASE_GREEN_DARK,
			null,
			false,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_PHYSICAL, -75f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, -15f),
					new Value<>(Attribute.ACTION_POINTS, -2f)),
			Util.newArrayListOfValues("[style.colourTerrible(Cannot attempt to escape!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "A strong, snake-like tail has constricted [npc.namePos] in a vice-like grip, making it all but impossible for [npc.herHim] to move [npc.her] [npc.arms] and [npc.legs]!"
						+ "<br/>[style.italicsBad(If [npc.she] [npc.verb(get)] tail-constricted again, [npc.she] will be instantly defeated!)]");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public ArrayList<ItemTag> getTags() {
			return Util.newArrayListOfValues(
					ItemTag.HINDERS_ARM_MOVEMENT,
					ItemTag.HINDERS_LEG_MOVEMENT,
					ItemTag.PREVENTS_COMBAT_ESCAPE);
		}
	};
	
	public static AbstractStatusEffect TAIL_CONSTRICTION_SEX = new AbstractStatusEffect(10,
			"Constricted",
			"immobilised_tail",
			PresetColour.BASE_GREEN_DARK,
			false,
			null,
			Util.newArrayListOfValues("[style.colourTerrible(Cannot move!)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			Value<ImmobilisationType, GameCharacter> type = Main.sex.getImmobilisationType(target);
			return UtilText.parse(target, type.getValue(), "[npc2.NameIsFull] using [npc2.her] long, strong tail to constrict [npc.name] and firmly prevent [npc.herHim] from moving!");
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex()) {
				return false;
			}
			Value<ImmobilisationType, GameCharacter> type = Main.sex.getImmobilisationType(target);
			return type!=null && type.getKey()==ImmobilisationType.TAIL_CONSTRICTION;
		}
		@Override
		public boolean isRemoveAtEndOfSex() {
			return true;
		}
	};

	public static AbstractStatusEffect BANEFUL_FISSURE = new AbstractStatusEffect(10,
			"Fissure's Fumes",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			null,
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue()+ "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"The fissure continues to spew forth poison fumes, causing [npc.name] to cough and splutter each time [npc.she] [npc.verb(breathe)] in.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.DARK_SIREN_SIRENS_CALL.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect FIRE_MANA_BURN = new AbstractStatusEffect(10,
			"Aura Burn",
			"melee_fire",
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			null,
			Util.newArrayListOfValues("[style.boldFire(Fire Spells)] can be cast at a cost of "+Attribute.HEALTH_MAXIMUM.getName()+" when out of aura",
					"[style.boldGood(Will not reduce "+Attribute.HEALTH_MAXIMUM.getName()+" below 1)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"Thanks to [npc.her] affinity with the school of Fire, [npc.nameIsFull] able to sacrifice a portion of [npc.her] "+Attribute.HEALTH_MAXIMUM.getName()+" in order to cast fire spells when [npc.sheIs] out of aura.");
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSpellSchoolSpecialAbilityUnlocked(SpellSchool.FIRE);
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect LINGERING_FLAMES = new AbstractStatusEffect(10,
			"Lingering Flames",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			null,
			Util.newArrayListOfValues("<b>5</b> [style.boldFire(Fire Damage)] per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.FIRE.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldFire(fire damage)]!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Invisible arcane flames lick at your feet, and while they don't cause you any real pain, you still end up hopping around in discomfort.";
			} else {
				return UtilText.parse(target,
						"Invisible arcane flames lick at [npc.namePos] feet, and while they don't cause [npc.herHim] any real pain, [npc.she] still ends up hopping around in discomfort.");
			}
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.FIREBALL_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FLASH = new AbstractStatusEffect(10,
			"Blinded",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"The blinding flash of light has left [npc.name] temporarily dazzled, and [npc.sheIsFull] struggling to regain control of [npc.her] senses!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.FLASH.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect FLASH_1 = new AbstractStatusEffect(10,
			"Blinded (Secondary Sparks)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"After the initial, blinding flash of light, a series of secondary sparks are continuing to  dazzle [npc.name], making it incredibly difficult for [npc.herHim] to perform any actions!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.FLASH_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	public static AbstractStatusEffect CLOAK_OF_FLAMES = new AbstractStatusEffect(10,
			"Cloak of Flames",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull] been shrouded in a cloak of arcane flames which is increasing [npc.her] fire and cold resistances!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CLOAK_OF_FLAMES_1 = new AbstractStatusEffect(10,
			"Cloak of Flames (Incendiary)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			Util.newArrayListOfValues("Unarmed attacks deal +1 damage per caster level",
					"Unarmed attacks deal [style.boldFire(Fire Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull] been shrouded in a cloak of arcane flames, which is not only increasing [npc.her] resistances, but is also imbuing [npc.her] unarmed attacks with fire damage!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CLOAK_OF_FLAMES_2 = new AbstractStatusEffect(10,
			"Cloak of Flames (Inferno)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 25f),
					new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			Util.newArrayListOfValues("Unarmed attacks deal +1 damage per caster level",
					"Unarmed attacks deal [style.boldFire(Fire Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target,
						"[npc.NameHasFull] been shrouded in a cloak of arcane flames, which is not only increasing [npc.her] resistances and fire damage, but is also imbuing [npc.her] unarmed attacks with fire damage!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect CLOAK_OF_FLAMES_3 = new AbstractStatusEffect(10,
			"Cloak of Flames (Ring of Fire)",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 25f),
					new Value<>(Attribute.RESISTANCE_FIRE, 10f),
					new Value<>(Attribute.RESISTANCE_ICE, 20f)),
			Util.newArrayListOfValues("Unarmed attacks deal +1 damage per caster level",
					"Unarmed attacks deal [style.boldFire(Fire Damage)]",
					"Attackers take <b>5</b> [style.colourFire(Fire Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameHasFull] been shrouded in a cloak of arcane flames, which is not only increasing [npc.her] resistances and fire damage, but is also imbuing [npc.her] unarmed attacks with fire damage!"
						+ " Any enemies foolish enough to strike [npc.herHim] in melee are taking retaliatory fire damage!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_WILDFIRE = new AbstractStatusEffect(10,
			"Wildfire",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 20f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "The Fire elemental in [npc.namePos] party is imbuing [npc.herHim] with the knowledge of how to get the most out of fire attacks!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_1)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.FIRE) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_BURNING_DESIRE = new AbstractStatusEffect(10,
			"Burning Desire",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "The Fire elemental in the enemy party is filling [npc.name] with a burning desire for sex!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInCombat()) {
				List<GameCharacter> enemies = Main.combat.getEnemies(target);
				
				for(GameCharacter combatant : enemies) {
					if(combatant.isElemental()
							&& ((Elemental)combatant).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_2)
							&& ((Elemental)combatant).getCurrentSchool()==SpellSchool.FIRE) {
						return true;
					}
				}
			}
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_SERVANT_OF_FIRE = new AbstractStatusEffect(10,
			"Servant of Fire",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			false,
			null,
			Util.newArrayListOfValues("-50% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
					"Having sworn [npc.her] subservience to the school of Fire, [npc.namePos] Fire elemental, [npc2.name], is now siphoning off as much of [npc.namePos] energy as [npc2.she] wants!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.FIRE;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_SERVANT_OF_FIRE_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"Energy Siphon",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Non-Seduction Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.Name] is siphoning off as much of [npc2.namePos] energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.FIRE;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_FIRE_BINDING_OF_FIRE = new AbstractStatusEffect(10,
			"Binding of Fire",
			null,
			PresetColour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 25f),
					new Value<>(Attribute.RESISTANCE_FIRE, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Having bound the school of Fire to [npc.her] will, [npc.namePos] Fire elemental, [npc2.name], is forced to reveal all of [npc2.her] secrets to [npc2.her] [npc.mistress].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.FIRE;
		}
	};
	
	public static AbstractStatusEffect FREEZING_FOG = new AbstractStatusEffect(10,
			"Freezing Fog",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -20f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A freezing fog, left behind by the impact of the spell 'Ice Shard', is lingering around [npc.name]. The arcane cold is seeping into [npc.her] body, slowing [npc.her] movements and dulling [npc.her] mind.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ICE_SHARD_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect FROZEN = new AbstractStatusEffect(10,
			"Frozen",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ACTION_POINTS, -1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "The freezing fog lingering around [npc.name] has exploded, covering [npc.herHim] in a thin sheet of ice and slowing [npc.her] movements!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ICE_SHARD_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect RAIN_CLOUD = new AbstractStatusEffect(10,
			"Rain Cloud",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A small, arcane-infused rain cloud is hovering above [npc.namePos] head. The cold rain is sapping [npc.her] ability to effectively cast spells.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.RAIN_CLOUD.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect RAIN_CLOUD_DEEP_CHILL = new AbstractStatusEffect(10,
			"Rain Cloud (Deep Chill)",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f),
					new Value<>(Attribute.RESISTANCE_ICE, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A small, arcane-infused rain cloud is hovering above [npc.namePos] head. The freezing rain is both sapping [npc.her] ability to effectively cast spells, as well as reducing [npc.her] resistance to the cold.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.RAIN_CLOUD_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect RAIN_CLOUD_DOWNPOUR = new AbstractStatusEffect(10,
			"Rain Cloud (Downpour)",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f),
					new Value<>(Attribute.RESISTANCE_ICE, -25f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"An arcane-infused rain cloud is hovering above [npc.namePos] head."
							+ " The freezing downpour is sapping [npc.her] ability to effectively cast spells, as well as reducing [npc.her] resistance to the cold, and making it harder to defend [npc.herself].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.RAIN_CLOUD_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST = new AbstractStatusEffect(10,
			"Rain Cloud (Downpour)",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -25f),
					new Value<>(Attribute.RESISTANCE_ICE, -25f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"An arcane-infused rain cloud is hovering above [npc.namePos] head."
							+ " The freezing downpour is sapping [npc.her] ability to effectively cast spells, as well as reducing [npc.her] resistance to the cold, and making it harder to defend [npc.herself].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.RAIN_CLOUD_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect RAIN_CLOUD_CLOUDBURST = new AbstractStatusEffect(10,
			"Rain Cloud (Cloudburst)",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.SPELL_COST_MODIFIER, -50f),
					new Value<>(Attribute.RESISTANCE_ICE, -25f),
					new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"An arcane-infused rain cloud is hovering above [npc.namePos] head."
							+ " The torrential, freezing downpour is draining [npc.her] ability to effectively cast spells, as well as reducing [npc.her] resistance to the cold, and making it harder to defend [npc.herself].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.RAIN_CLOUD_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_CRASHING_WAVES = new AbstractStatusEffect(10,
			"Crashing Waves",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_ICE, 20f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"The Water elemental in [npc.namePos] party is imbuing [npc.herHim] with the knowledge of how to get the most out of ice attacks!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_1)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.WATER) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_CALM_WATERS = new AbstractStatusEffect(10,
			"Calm Waters",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "The Water elemental in [npc.namePos] party is helping to calm [npc.her] desire for sex.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_2)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.WATER) {
					return true;
				}
			}
			
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_SERVANT_OF_WATER = new AbstractStatusEffect(10,
			"Servant of Water",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			false,
			null,
			Util.newArrayListOfValues("-50% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Having sworn [npc.her] subservience to the school of Water, [npc.namePos] Water elemental, [npc2.name], is now siphoning off as much of [npc.namePos] energy as [npc2.she] wants!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.WATER;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_SERVANT_OF_WATER_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"Energy Siphon",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Non-Seduction Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.Name] is siphoning off as much of [npc2.namePos] energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.WATER;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_WATER_BINDING_OF_WATER = new AbstractStatusEffect(10,
			"Binding of Water",
			null,
			PresetColour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_ICE, 25f),
					new Value<>(Attribute.RESISTANCE_ICE, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Having bound the school of Water to [npc.her] will, [npc.namePos] Water elemental, [npc2.name], is forced to reveal all of [npc2.her] secrets to [npc2.her] [npc.mistress].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.WATER;
		}
	};
	
	public static AbstractStatusEffect POISON_VAPOURS = new AbstractStatusEffect(10,
			"Poison Vapours",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			null,
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "The cloud of poison vapours continues to linger around [npc.namePos] body, causing [npc.herHim] to cough and splutter each time [npc.she] [npc.verb(breathe)] in.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.POISON_VAPOURS.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISON_VAPOURS_CHOKING_HAZE = new AbstractStatusEffect(10,
			"Poison Vapours (Choking Haze)",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>")) {
				@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "The cloud of poison vapours continues to linger around [npc.namePos] body, causing [npc.herHim] to cough and splutter each time [npc.she] [npc.verb(breathe)] in.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.POISON_VAPOURS_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISON_VAPOURS_ARCANE_SICKNESS = new AbstractStatusEffect(10,
			"Poison Vapours (Arcane Sickness)",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>",
					"<b>10</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+" [style.boldTerrible(drained)] per turn</b>")) {
				@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			int lustDamage = 10;
			target.incrementMana(-lustDamage);
			
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" and [npc.verb(lose)] <b>" + lustDamage + "</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!")
					+ damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "The cloud of poison vapours continues to linger around [npc.namePos] body, causing [npc.herHim] to cough and splutter each time [npc.she] [npc.verb(breathe)] in.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.POISON_VAPOURS_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect POISON_VAPOURS_WEAKENING_CLOUD = new AbstractStatusEffect(10,
			"Poison Vapours (Weakening Cloud)",
			null,
			PresetColour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, -15f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>",
					"<b>10</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+" [style.boldTerrible(drained)] per turn</b>")) {
				@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.POISON.damageTarget(null, target, 25);

			int lustDamage = 10;
			target.incrementMana(-lustDamage);
			
			return UtilText.parse(target,
					"[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" and [npc.verb(lose)] <b>" + lustDamage + "</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!")
					+ damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "The cloud of poison vapours continues to linger around [npc.namePos] body, causing [npc.herHim] to cough and splutter each time [npc.she] [npc.verb(breathe)] in.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.POISON_VAPOURS_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect VACUUM = new AbstractStatusEffect(10,
			"Vacuum",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "A sustained void in the air, which shifts to remain close to [npc.namePos] body, is causing [npc.her] balance to be thrown off!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.VACUUM.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect VACUUM_SECONDARY_VOIDS = new AbstractStatusEffect(10,
			"Vacuum (Secondary Voids)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A sustained void in the air, which shifts to remain close to [npc.namePos] body, is causing [npc.her] balance to be thrown off!"
						+ " Smaller, secondary voids are also continuously being created and collapsing around [npc.herHim]!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.VACUUM_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect VACUUM_SUCTION = new AbstractStatusEffect(10,
			"Vacuum (Suction)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues("<b>10%</b> chance per turn of [style.boldExcellent(stripping)] clothing")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Math.random()<(target.isPlayer()?0.1f:0.166f)) { // I purposefully boost the chance in secret to make the player feel better about the RNG
				List<AbstractClothing> suitableClothing = new ArrayList<>();
				for(AbstractClothing c : new ArrayList<>(target.getClothingCurrentlyEquipped())) {
					if(target.isAbleToUnequip(c, false, target)
							&& !c.getSlotEquippedTo().isJewellery()) {
						suitableClothing.add(c);
					}
				}
				if(!suitableClothing.isEmpty()) {
					AbstractClothing clothingBlownOff = suitableClothing.get(Util.random.nextInt(suitableClothing.size()));
					target.unequipClothingOntoFloor(clothingBlownOff, true, target);
					if(target.isPlayer()) {
						return "Your "+clothingBlownOff.getName()+" "+(clothingBlownOff.getClothingType().isPlural()?"are":"is")+" sucked off and blown to the floor!";
					} else {
						return "[npc.NamePos] "+clothingBlownOff.getName()+" "+(clothingBlownOff.getClothingType().isPlural()?"are":"is")+" sucked off and blown to the floor!";
					}
				}
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A sustained, powerful void is shifting to remain close to [npc.namePos] body, and is causing [npc.her] balance to be thrown off!"
						+ " Smaller, secondary voids are also continuously being created and collapsing around [npc.herHim]!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.VACUUM_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect VACUUM_TOTAL_VOID = new AbstractStatusEffect(10,
			"Vacuum (Total Void)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -20f),
					new Value<>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues("<b>25%</b> chance per turn of [style.boldExcellent(stripping)] clothing")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			if(Math.random()<(target.isPlayer()?0.25f:0.33f)) { // I purposefully boost the chance in secret to make the player feel better about the RNG
				List<AbstractClothing> suitableClothing = new ArrayList<>();
				for(AbstractClothing c : new ArrayList<>(target.getClothingCurrentlyEquipped())) {
					if(target.isAbleToUnequip(c, false, target)
							&& !c.getSlotEquippedTo().isJewellery()) {
						suitableClothing.add(c);
					}
				}
				if(!suitableClothing.isEmpty()) {
					AbstractClothing clothingBlownOff = suitableClothing.get(Util.random.nextInt(suitableClothing.size()));
					target.unequipClothingOntoFloor(clothingBlownOff, true, target);
					if(target.isPlayer()) {
						return "Your "+clothingBlownOff.getName()+" "+(clothingBlownOff.getClothingType().isPlural()?"are":"is")+" sucked off and blown to the floor!";
					} else {
						return "[npc.NamePos] "+clothingBlownOff.getName()+" "+(clothingBlownOff.getClothingType().isPlural()?"are":"is")+" sucked off and blown to the floor!";
					}
				}
			}
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target,
						"A sustained, incredibly-powerful void is shifting to remain close to [npc.namePos] body, and is causing [npc.her] balance to be thrown off!"
						+ " Smaller, secondary voids are also continuously being created and collapsing around [npc.herHim]!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.VACUUM_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PROTECTIVE_GUSTS = new AbstractStatusEffect(10,
			"Protective Gusts",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_POISON, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 1f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"A benevolent wind has been summoned to protect [npc.name] from any poisonous vapours, and to help throw off any attacks directed towards [npc.herHim].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.PROTECTIVE_GUSTS.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect PROTECTIVE_GUSTS_GUIDING_WIND = new AbstractStatusEffect(10,
			"Protective Gusts (Guiding Wind)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_POISON, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"A benevolent wind has been summoned to protect [npc.name] from any poisonous vapours, and to help throw off any attacks directed towards [npc.herHim]."
							+ " It is also helping to guide [npc.her] attacks.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.PROTECTIVE_GUSTS_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect PROTECTIVE_GUSTS_FOCUSED_BLAST = new AbstractStatusEffect(10,
			"Protective Gusts (Focused Blast)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_POISON, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 3f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"A benevolent wind has been summoned to protect [npc.name] from any poisonous vapours, and to help throw off any attacks directed towards [npc.herHim]."
							+ " It is also helping to guide and increase the power of [npc.her] attacks.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.PROTECTIVE_GUSTS_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_WHIRLWIND = new AbstractStatusEffect(10,
			"Whirlwind",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"The Air elemental in the enemy party is surrounding [npc.name] with buffeting winds!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInCombat()) {
				List<GameCharacter> enemies = Main.combat.getEnemies(target);
				
				for(GameCharacter combatant : enemies) {
					if(combatant.isElemental()
							&& ((Elemental)combatant).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_1)
							&& ((Elemental)combatant).getCurrentSchool()==SpellSchool.AIR) {
						return true;
					}
				}
			}
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_VITALISING_SCENTS = new AbstractStatusEffect(10,
			"Vitalising Scents",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 5f),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"The Air elemental in [npc.namePos] party is surrounding [npc.herHim] with vitalising scents, which is giving [npc.herHim] the energy to dodge attacks and land powerful blows!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_2)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.AIR) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_SERVANT_OF_AIR = new AbstractStatusEffect(10,
			"Servant of Air",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			null,
			Util.newArrayListOfValues("-50% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Having sworn [npc.her] subservience to the school of Air, [npc.namePos] Air elemental, [npc2.name], is now siphoning off as much of [npc.namePos] energy as [npc2.she] wants!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.AIR;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_SERVANT_OF_AIR_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"Energy Siphon",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Non-Seduction Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.Name] is siphoning off as much of [npc2.namePos] energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.AIR;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_AIR_BINDING_OF_AIR = new AbstractStatusEffect(10,
			"Binding of Air",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_POISON, 25f),
					new Value<>(Attribute.RESISTANCE_POISON, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Having bound the school of Air to [npc.her] will, [npc.namePos] Air elemental, [npc2.name], is forced to reveal all of [npc2.her] secrets to [npc2.her] [npc.mistress].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.AIR;
		}
	};
	
	public static AbstractStatusEffect SLAM_GROUND_SHAKE = new AbstractStatusEffect(10,
			"Ground Shake",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"The ground beneath [npc.namePos] [npc.feet] is shaking and swaying, making it very difficult for [npc.herHim] to avoid incoming blows.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.SLAM_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect SLAM_AFTER_SHOCK = new AbstractStatusEffect(10,
			"Ground Shake (After Shock)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"The ground beneath [npc.namePos] [npc.feet] is shaking and swaying, making it very difficult for [npc.herHim] to avoid incoming blows."
						+ " [npc.She] can sense that there's an Aftershock coming, but there's little [npc.she] can do to avoid it...");
		}
		@Override
		protected String extraRemovalEffects(GameCharacter target){
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.SLAM_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEKENETIC_SHOWER = new AbstractStatusEffect(10,
			"Telekinetic Shower",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			null,
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.Name] is being continuously pelted by a barrage of rocks and other small objects.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.TELEKENETIC_SHOWER.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEKENETIC_SHOWER_PRECISION_STRIKES = new AbstractStatusEffect(10,
			"Telekinetic Shower (Precision Strikes)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -20f)),
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.Name] is being continuously pelted by a highly-accurate barrage of rocks and other small objects.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEKENETIC_SHOWER_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEKENETIC_SHOWER_UNSEEN_FORCE = new AbstractStatusEffect(10,
			"Telekinetic Shower (Unseen Force)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -20f)),
			Util.newArrayListOfValues("<b>50</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(null, target, 50);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.Name] is being continuously pelted by a highly-accurate barrage of rocks and other small objects. Each impact is immediately followed up by an explosive wave of force.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEKENETIC_SHOWER_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect STONE_SHELL = new AbstractStatusEffect(10,
			"Stone Shell",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A solid barrier of stone is being held up between [npc.name] and [npc.her] enemy, granting [npc.herHim] significantly improved physical defence.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.STONE_SHELL.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect STONE_SHELL_SHIFTING_SANDS = new AbstractStatusEffect(10,
			"Stone Shell (Shifting Sands)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A solid barrier of stone is being held up between [npc.name] and [npc.her] enemy, granting [npc.herHim] significantly improved physical defence."
								+ " Every now and then, the stone barrier suddenly shifts and melts into sand, before reforming in a different location.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.STONE_SHELL_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect STONE_SHELL_HARDENED_CARAPACE = new AbstractStatusEffect(10,
			"Stone Shell (Hardened Carapace)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A solid, hardened barrier of stone is being held up between [npc.name] and [npc.her] enemy, granting [npc.herHim] significantly improved physical defence."
								+ " Every now and then, the stone barrier suddenly shifts and melts into sand, before reforming in a different location.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.STONE_SHELL_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect STONE_SHELL_EXPLOSIVE_FINISH = new AbstractStatusEffect(10,
			"Stone Shell (Explosive Finish)",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f),
					new Value<>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues("[style.colourExcellent(All enemies)] take <b>10</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" when Stone Shell ends")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A solid, hardened barrier of stone is being held up between [npc.name] and [npc.her] enemy, granting [npc.herHim] significantly improved physical defence."
								+ " Every now and then, the stone barrier suddenly shifts and melts into sand, before reforming in a different location.");
		}
		@Override
		protected String extraRemovalEffects(GameCharacter target){
			StringBuilder sb = new StringBuilder();
			
			boolean first=true;
			for(GameCharacter combatant : Main.combat.getEnemies(target)) {
				Value<String, Integer> damageValue = DamageType.PHYSICAL.damageTarget(target, combatant, 10);
				sb.append(UtilText.parse(combatant,
						target, (first?"":"<br/>")+"<br/>[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" as [npc2.namePos] Stone Shell explodes!")
						+damageValue.getKey());
				first=false;
			}
			
			return sb.toString();
			
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.STONE_SHELL_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_ROLLING_STONE = new AbstractStatusEffect(10,
			"Rolling Stone",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<>(Attribute.CRITICAL_DAMAGE, 25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"The Earth elemental in [npc.namePos] party is empowering [npc.her] attacks with waves of force!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_1)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.EARTH) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_HARDENING = new AbstractStatusEffect(10,
			"Hardening",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"The Earth elemental in [npc.namePos] party is using telekinetic powers to surround [npc.herHim] with protective fragments of rock!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_2)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.EARTH) {
					return true;
				}
			}
			
			return false;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_SERVANT_OF_EARTH = new AbstractStatusEffect(10,
			"Servant of Earth",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			false,
			null,
			Util.newArrayListOfValues("-50% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Having sworn [npc.her] subservience to the school of Earth, [npc.namePos] Earth elemental, [npc2.name], is now siphoning off as much of [npc.namePos] energy as [npc2.she] wants!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.EARTH;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_SERVANT_OF_EARTH_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"Energy Siphon",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Non-Seduction Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.Name] is siphoning off as much of [npc2.namePos] energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.EARTH;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_EARTH_BINDING_OF_EARTH = new AbstractStatusEffect(10,
			"Binding of Earth",
			null,
			PresetColour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Having bound the school of Earth to [npc.her] will, [npc.namePos] Earth elemental, [npc2.name], is forced to reveal all of [npc2.her] secrets to [npc2.her] [npc.mistress].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.EARTH;
		}
	};
	

	public static AbstractStatusEffect ARCANE_AROUSAL_LUSTFUL_DISTRACTION = new AbstractStatusEffect(10,
			"Lustful Distraction",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Arousing images and thoughts keep on pushing their way to the front of [npc.namePos] mind, causing [npc.herHim] to lose focus on what it is [npc.sheIs] trying to hit.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_AROUSAL_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ARCANE_AROUSAL_DIRTY_PROMISES = new AbstractStatusEffect(10,
			"Lustful Distraction (Dirty Promises)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, -15f),
					new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"Arousing images keep on pushing their way into [npc.namePos] mind, causing [npc.herHim] to lose focus on what it is [npc.sheIs] trying to hit."
								+ " [npc.She] [npc.verb(hear)] the occasional phantasmal whisper in [npc.her] [npc.ear], promising that [npc.she]'ll have a good time if [npc.she] simply submits.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_AROUSAL_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPATHIC_COMMUNICATION = new AbstractStatusEffect(10,
			"Telepathic Communication",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] able to project [npc.her] seductive taunts and [npc.moans+] directly into a person's mind!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.TELEPATHIC_COMMUNICATION.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH = new AbstractStatusEffect(10,
			"Projected Touch",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 30f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] able to project [npc.her] seductive taunts and [npc.moans+] directly into a person's mind!"
								+ " In addition to this, [npc.she] can deliver phantasmal kisses and gropes to [npc.her] target.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEPATHIC_COMMUNICATION_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION = new AbstractStatusEffect(10,
			"Power of Suggestion",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 30f)),
			Util.newArrayListOfValues("[style.boldLust(Tease)] [style.boldExcellent(applies)] -25 "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b")+" to the target for [style.boldGood(2 turns)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.NameIsFull] able to project [npc.her] seductive taunts and suggestive phrases directly into a person's mind!"
								+ " In addition to this, [npc.she] can deliver phantasmal kisses and gropes to [npc.her] target.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEPATHIC_COMMUNICATION_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED = new AbstractStatusEffect(10,
			"Power of Suggestion",
			"telepathic_communication_power_of_suggestion_targeted",
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"The telepathic moans and suggestions that have been projected into [npc.namePos] mind are causing [npc.herHim] to lower [npc.her] guard!");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	
	public static AbstractStatusEffect ARCANE_CLOUD = new AbstractStatusEffect(10,
			"Arcane Cloud",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A small arcane cloud is hovering above [npc.namePos] head. While subjected to its presence, the arousing effects of the arcane seep into [npc.her] mind.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.ARCANE_CLOUD.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect ARCANE_CLOUD_ARCANE_LIGHTNING = new AbstractStatusEffect(10,
			"Arcane Cloud (Arcane Lightning)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("<b>5</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldLust(lust damage)]!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A small, lust-inducing arcane cloud is hovering above [npc.namePos] head. Every now and then, a bolt of arcane lightning flashes down towards [npc.herHim], filling [npc.her] mind with arousing thoughts.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_CLOUD_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect ARCANE_CLOUD_ARCANE_THUNDER = new AbstractStatusEffect(10,
			"Arcane Cloud (Arcane Thunder)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("<b>15</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, target, 15);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldLust(lust damage)]!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A small, lust-inducing arcane cloud is hovering above [npc.namePos] head. Every now and then, a bolt of arcane lightning, accompanied by a small clap of arcane thunder, fills [npc.her] mind with arousing thoughts.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_CLOUD_2.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ARCANE_CLOUD_LOCALISED_STORM = new AbstractStatusEffect(10,
			"Arcane Cloud (Localised Storm)",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("[style.boldTerrible(All party members)] take <b>15</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn</b>")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			StringBuilder sb = new StringBuilder();
			
			List<GameCharacter> affectedCombatants = new ArrayList<>();
			affectedCombatants.add(target);
			affectedCombatants.addAll(Main.combat.getAllies(target));
			
			for(GameCharacter combatant : affectedCombatants) {
				Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, combatant, 15);
				
				sb.append(UtilText.parse(combatant, "<br/>[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldLust(lust damage)]!")+damageValue.getKey());
			}
			
			return sb.toString();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A localised arcane storm is hovering above [npc.namePos] head. Every now and then, a bolt of arcane lightning, accompanied by a small clap of arcane thunder, fills [npc.her] mind with arousing thoughts.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_CLOUD_3.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_ARCANE_LEWD_ENCOURAGEMENTS = new AbstractStatusEffect(10,
			"Lewd Encouragements",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"The Arcane elemental in [npc.namePos] party is projecting lewd words of encouragement into [npc.her] mind!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_1.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			List<GameCharacter> allPartyMembers = new ArrayList<>(target.getParty());
			allPartyMembers.add(target);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.isElementalSummoned()
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_1)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.ARCANE) {
					return true;
				}
			}
			
			return false;
		}
	};

	public static AbstractStatusEffect ELEMENTAL_ARCANE_CARESSING_TOUCH = new AbstractStatusEffect(10,
			"Caressing Touch",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"The Arcane elemental in the enemy party is projecting phantasmal tentacles to caress and grope [npc.namePos] body!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_2.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInCombat()) {
				List<GameCharacter> enemies = Main.combat.getEnemies(target);
				
				for(GameCharacter combatant : enemies) {
					if(combatant.isElemental()
							&& ((Elemental)combatant).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_2)
							&& ((Elemental)combatant).getCurrentSchool()==SpellSchool.ARCANE) {
						return true;
					}
				}
			}
			return false;
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_ARCANE_SERVANT_OF_ARCANE = new AbstractStatusEffect(10,
			"Servant of Arcane",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			false,
			null,
			Util.newArrayListOfValues("-50% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Having sworn [npc.her] subservience to the school of Arcane, [npc.namePos] Arcane elemental, [npc2.name], is now siphoning off as much of [npc.namePos] energy as [npc2.she] wants!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.ARCANE;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_ARCANE_SERVANT_OF_ARCANE_ELEMENTAL_BUFF = new AbstractStatusEffect(10,
			"Energy Siphon",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			null,
			Util.newArrayListOfValues("+100% [style.colourExcellent(Non-Seduction Damage)]")) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.Name] is siphoning off as much of [npc2.namePos] energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_3A.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElemental()
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.ARCANE;
		}
	};
	
	public static AbstractStatusEffect ELEMENTAL_ARCANE_BINDING_OF_ARCANE = new AbstractStatusEffect(10,
			"Binding of Arcane",
			null,
			PresetColour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 25f),
					new Value<>(Attribute.RESISTANCE_LUST, 10f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, target.getElemental(),
						"Having bound the school of Arcane to [npc.her] will, [npc.namePos] Arcane elemental, [npc2.name], is forced to reveal all of [npc2.her] secrets to [npc2.her] [npc.mistress].");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_3B.getSVGString();
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isElementalSummoned()
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.ARCANE;
		}
	};
	
	public static AbstractStatusEffect ARCANE_DUALITY_POSITIVE = new AbstractStatusEffect(10,
			"Arcane Duality (Defence)",
			"cleanse_positive",
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<>(Attribute.RESISTANCE_LUST, 5f),
					new Value<>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<>(Attribute.RESISTANCE_ICE, 5f),
					new Value<>(Attribute.RESISTANCE_POISON, 5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A protective barrier of arcane energy has surrounded [npc.name], shielding [npc.herHim] from all types of incoming damage.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ARCANE_DUALITY_NEGATIVE = new AbstractStatusEffect(10,
			"Arcane Duality (Weakness)",
			"cleanse_negative",
			PresetColour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, -5f),
					new Value<>(Attribute.RESISTANCE_LUST, -5f),
					new Value<>(Attribute.RESISTANCE_FIRE, -5f),
					new Value<>(Attribute.RESISTANCE_ICE, -5f),
					new Value<>(Attribute.RESISTANCE_POISON, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"A weakening barrier of arcane energy has surrounded [npc.name], making [npc.herHim] more vulnerable to all types of incoming damage.");
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPORT = new AbstractStatusEffect(10,
			"Teleport",
			null,
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 100f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.Name] has teleported behind [npc.her] enemies, making it extremely unlikely that they'll be able to land a hit on [npc.herHim]!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.TELEPORT.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect TELEPORT_ARCANE_ARRIVAL = new AbstractStatusEffect(10,
			"Teleport (Arcane Arrival)",
			null,
			PresetColour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<>(Attribute.ENERGY_SHIELDING, 100f)),
			Util.newArrayListOfValues("<b>5</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn to a random enemy")) {
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
			GameCharacter randomEnemy = Main.combat.getEnemies(target).get(Util.random.nextInt(Main.combat.getEnemies(target).size()));
			
			Value<String, Integer> damageValue = DamageType.LUST.damageTarget(null, randomEnemy, 15);

			return UtilText.parse(randomEnemy, "[npc.Name] [npc.verb(take)] <b>" + damageValue.getValue() + "</b> [style.boldLust(lust damage)]!")+damageValue.getKey();
		}
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
						"[npc.Name] has teleported behind [npc.her] enemies, making it extremely unlikely that they'll be able to land a hit on [npc.herHim]! A burst of lust-inducing arcane energy accompanies [npc.her] arrival!");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEPORT_1.getSVGString();
		}
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	};
	
	// SEX EFFECTS:
	
	public static AbstractStatusEffect CONDOM_WORN = new AbstractStatusEffect(80,
			"Wearing a condom",
			"condom",
			PresetColour.CLOTHING_PINK_LIGHT,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return -0.5f;
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modifiersList = new ArrayList<>();
			modifiersList.add("-0.5% <b style='color: " + PresetColour.GENERIC_SEX.toWebHexString() + "'>arousal/turn</b>");
			return modifiersList;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "Because you're wearing a condom, sex doesn't feel quite as good...";
			else
				return "Because [npc.sheIs] wearing a condom, sex doesn't feel quite as good for [npc.name]...";
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isWearingCondom();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};

	public static AbstractStatusEffect FLOWING_WATER = new AbstractStatusEffect(80,
			"Flowing Water",
			"sexEffects/flowing_water",
			PresetColour.BASE_BLUE_LIGHT,
			PresetColour.BASE_AQUA,
			true,
			null,
			Util.newArrayListOfValues(
					"[style.colourDirty(Dirty body parts)] are [style.colourAqua(quickly cleaned)]")) {
		public EffectBenefit getBeneficialStatus() {
			return EffectBenefit.NEUTRAL;
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "The water flowing over [npc.namePos] body is preventing [npc.herHim] from getting dirty!");
			}
			return null;
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex() && Main.sex.getInitialSexManager().isWashingScene();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect DESIRES = new AbstractStatusEffect(80,
			"Desires",
			"desires",
			PresetColour.GENERIC_ARCANE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Your fetishes and desires affect how much arousal you gain from performing related sex actions. Selecting an action with an associated fetish that you own will also not increase your corruption.";
				
			} else if(Main.game.isInSex()) {
				GameCharacter targetedCharacter = Main.sex.getTargetedPartner(target);
				SexType foreplayPreference = Main.sex.getForeplayPreference((NPC) target, targetedCharacter);
				SexType mainPreference = Main.sex.getMainSexPreference((NPC) target, targetedCharacter);
				
				return UtilText.parse(target, targetedCharacter,
						(Main.game.isInNewWorld()
								?"The power of your arcane aura allows you to sense [npc.namePos] sexual preferences:"
								:"Somehow, you're able to instinctively sense what [npc.namePos] sexual preferences are:")
						+ "<br/>[style.italics"+(Main.sex.isInForeplay(target)?"PinkLight(<b>Foreplay</b>: ":"Disabled(Foreplay: ")
							+ (foreplayPreference!=null
									?"[npc.Her] "+foreplayPreference.getPerformingSexArea().getName(target)+" and [npc2.namePos] "+foreplayPreference.getTargetedSexArea().getName(targetedCharacter)+"."
									:"[npc.She] [npc.has] no preference...")
							+ ")]"
						+ "<br/>[style.italics"+(!Main.sex.isInForeplay(target)?"Pink(<b>Sex</b>: ":"Disabled(Sex: ")
						+ (mainPreference!=null
								?"[npc.Her] "+mainPreference.getPerformingSexArea().getName(target)+" and [npc2.namePos] "+mainPreference.getTargetedSexArea().getName(targetedCharacter)+"."
								:"[npc.She] [npc.has] no preference...")
						+ ")]")
						+ (Main.sex.isCharacterObeyingTarget(target, Main.game.getPlayer())
							?"<br/>[style.italicsMinorGood([npc.She] will listen to your requests.)]"
							:"<br/>[style.italicsMinorBad([npc.She] will ignore all of your requests.)]");
				
			} else {
				return UtilText.parse(target,
						(Main.game.isInNewWorld()
								?"Due to the underlying power of your arcane aura, you can sense [npc.namePos] non-neutral preferences towards sexual actions."
								:"Somehow, you're able to instinctively tell what [npc.namePos] non-neutral preferences towards sexual actions are.")
						+ "<br/>"
						+ "[style.italicsSex(You can detect what areas [npc.name] wants to use when in sex.)]");
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>();
			List<Fetish> orderedFetishList = new ArrayList<>();
			
			for(Fetish f : Fetish.values()) {
				FetishDesire desire = target.getFetishDesire(f);
				if(desire!=FetishDesire.TWO_NEUTRAL) {
					orderedFetishList.add(f);
				}
			}
			orderedFetishList.sort((e1, e2) -> target.getFetishDesire(e2).compareTo(target.getFetishDesire(e1)));

			for(Fetish f : orderedFetishList) {
				FetishDesire desire = target.getFetishDesire(f);
				modList.add("<b style='color:"+desire.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(desire.getNameAsVerb())+"</b>: "+Util.capitaliseSentence(f.getShortDescriptor(target)));
			}
			
			return modList;
		}
//		@Override
//		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
//			return Util.newArrayListOfValues(new Value<>(1, ""));
//		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.isPlayer() || Main.game.isInSex();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect ORGASM_COUNTER = new AbstractStatusEffect(80,
			"Orgasms",
			"sexEffects/orgasms",
			PresetColour.GENERIC_ARCANE,
			false,
			null,
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Anyone with a strong arcane aura, such as yours, doesn't suffer from any sort of refractory period after orgasming...";
			} else {
				return UtilText.parse(target, "Anyone in the presence of a strong arcane aura, such as yours, doesn't suffer from any sort of refractory period after orgasming...<br/>"
						+ "[npc.Name] needs to orgasm [style.boldSex("+Util.intToCount(target.getOrgasmsBeforeSatisfied())+")] before [npc.sheIs] satisfied.");
			}
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>();

			Colour orgasmColour = PresetColour.GENERIC_ARCANE;
			int orgasms = Main.sex.getNumberOfOrgasms(target);
			if(orgasms<RenderingEngine.orgasmColours.length) {
				orgasmColour = RenderingEngine.orgasmColours[orgasms];
			}
			
			modList.add("<b style='color:"+orgasmColour.toWebHexString()+";'>"+orgasms+"</b> Orgasm"+(orgasms==1?"":"s"));

			int essences = Main.sex.getEssenceGeneration(target);
			if(target.hasStatusEffect(StatusEffect.RECOVERING_AURA)) {
				modList.add("Will produce [style.boldBad(0 essences)]");
				modList.add("Caused by [style.boldBad('"+RECOVERING_AURA.getName(target)+"')] effect");
				
			} else {
				if(target.hasTrait(Perk.NYMPHOMANIAC, true)) {
					modList.add("Generates [style.boldArcane("+(essences)+" essences)] after sex");
					modList.add("[style.boldExcellent(Doubled)] from [style.colourTrait("+Perk.NYMPHOMANIAC.getName(target)+" trait)]");
					
				} else {
					modList.add("Will produce [style.boldArcane("+(essences)+" essences)]");
				}
				
				if(orgasms>=5) {
					modList.add("[style.boldBad(Maximum essences reached)]");
				}
			}
			return modList;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			StringBuilder SVGImageSB = new StringBuilder();

			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+super.getSVGString(owner)+"</div>");
			
			int orgasms = Main.sex.getNumberOfOrgasms(owner);
			
			SVGImageSB.append("<div style='width:40%;height:40%;position:absolute; top:0; right:4px;'>");
				if(orgasms == 0) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterZero());
				} else if(orgasms == 1) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterOne());
				} else if(orgasms == 2) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterTwo());
				} else if(orgasms == 3) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterThree());
				} else if(orgasms == 4) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterFour());
				} else if(orgasms == 5) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterFive());
				} else {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterFivePlus());
				}
			SVGImageSB.append("</div>");
			
			return SVGImageSB.toString();
		}
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
	};
	
	public static AbstractStatusEffect PENIS_STATUS = new AbstractStatusEffect(95,
			"Penis status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getPenetrationArousalPerTurn(target, SexAreaPenetration.PENIS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return 0;
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getPenetrationModifiersAsStringList(target, SexAreaPenetration.PENIS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaPenetration type = SexAreaPenetration.PENIS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(stroking [npc.her] own [npc.cock])]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] [style.boldSex(a handjob)]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(frotting)] with [npc.her] two [npc.cocks]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(frotting)] with [npc2.name]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] giving [npc.herself] [style.boldSex(a tail-job)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] [style.boldSex(a tail-job)]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] giving [npc.herself] [style.boldSex(a tentacle-job)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] [style.boldSex(a tentacle-job)]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] giving [npc.herself] [style.boldSex(a blowjob)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] [style.boldSex(a blowjob)]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-frotting)] with [npc.her] own [npc.cock]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-frotting)] with [npc2.name]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] giving [npc.herself] [style.boldSex(a foot-job)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] [style.boldSex(a foot-job)]!"));
								}
								break;
						}
					}
				}
			}
			
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No ongoing action.</b>");
			}

			appendPenetrationAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.penis]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.PENIS).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.PENIS).get(0);
			
			return new Value<>(3,
					Main.sex.formatPenetration(
					target.getPenetrationDescription(false,
							target,
							SexAreaPenetration.PENIS,
							partner,
							Main.sex.getOngoingActionsMap(target).get(SexAreaPenetration.PENIS).get(partner).iterator().next())));
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& Main.sex.getOngoingActionsMap(target)!=null
					&& !Main.sex.getOngoingSexAreas(target, SexAreaPenetration.PENIS).isEmpty()
					&& !Collections.disjoint(
							Main.sex.getOngoingSexAreas(target, SexAreaPenetration.PENIS).get(Main.sex.getCharacterOngoingSexArea(target, SexAreaPenetration.PENIS).get(0)),
							Util.newArrayListOfValues(SexAreaPenetration.values()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaPenetration.PENIS, SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypePenis());
		}
	};
	
	public static AbstractStatusEffect CLIT_STATUS = new AbstractStatusEffect(95,
			"Clitoris status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getPenetrationArousalPerTurn(target, SexAreaPenetration.CLIT);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return 0;
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getPenetrationModifiersAsStringList(target, SexAreaPenetration.CLIT);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaPenetration type = SexAreaPenetration.CLIT;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc2.name]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(teasing)] [npc.her] own [npc.cock]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(teasing)] [npc2.namePos] [npc2.cock]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-teasing)] [npc.her] own pussy!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-teasing)] [npc2.namePos] pussy!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-teasing)] [npc.her] own pussy!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-teasing)] [npc2.namePos] pussy!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(performing cunnilingus)] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(performing cunnilingus)] on [npc2.name]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tribbing)] with [npc2.name]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(foot-teasing)] [npc.her] own pussy!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(foot-teasing)] [npc2.namePos] pussy!"));
								}
								break;
						}
						
					}
				}
			}
			
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No ongoing action.</b>");
			}

			appendPenetrationAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.clit]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.CLIT).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.CLIT).get(0);
			
			return new Value<>(3,
					Main.sex.formatPenetration(
					target.getPenetrationDescription(false,
							target,
							SexAreaPenetration.CLIT,
							partner,
							Main.sex.getOngoingActionsMap(target).get(SexAreaPenetration.CLIT).get(partner).iterator().next())));
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& Main.sex.getOngoingActionsMap(target)!=null
					&& !Main.sex.getOngoingSexAreas(target, SexAreaPenetration.CLIT).isEmpty()
					&& !Collections.disjoint(
							Main.sex.getOngoingSexAreas(target, SexAreaPenetration.CLIT).get(Main.sex.getCharacterOngoingSexArea(target, SexAreaPenetration.CLIT).get(0)),
							Util.newArrayListOfValues(SexAreaPenetration.values()));
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaPenetration.CLIT, SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeClit());
		}
	};
	
	public static AbstractStatusEffect ANUS_STATUS = new AbstractStatusEffect(96,
			"Anus status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.ANUS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.ANUS);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.ANUS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.ANUS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");

			boolean descriptionAdded = false;
			for(SexAreaPenetration pen : SexAreaPenetration.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, pen)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(pen) {
						case FINGER:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fingering)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fucking)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case TAIL:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tail-fucking)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case TENTACLE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tentacle-fucking)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case TONGUE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(anilingus)] [npc.herself]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" performing [style.boldSex(anilingus)] on [npc.name]!"));
							}
							break;
						case CLIT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(clit-fucking)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case FOOT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
										+(names.size()==1?UtilText.parse(main, " [npc.is] [style.boldSex(pushing [npc.her] [npc.toes])]"):" are [style.boldSex(pushing their toes)]")
										+"  into [npc.namePos] [npc.asshole]!"));
							}
							break;
					}
				}
			}

			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, orifice)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					boolean selfAction = target.equals(main);
					descriptionAdded = true;
					switch(orifice) {
						case ARMPITS:
						case ANUS:
						case ASS:
						case BREAST:
						case NIPPLE:
						case BREAST_CROTCH:
						case NIPPLE_CROTCH:
							break;
						case MOUTH:
							if(selfAction) {
								descriptionSB.append("[npc.NameIsFull] performing [style.boldSex(anilingus)] on [npc.herself]!");
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" performing [style.boldSex(anilingus)] on [npc2.name]!"));
							}
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.asshole]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fucking)] [npc.namePos] [npc.asshole]!"));
							}
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							break;
						case SPINNERET:
							break;
					}
				}
			}
			
			if(!descriptionAdded) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.asshole]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.ANUS);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& Main.game.isAnalContentEnabled();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.ANUS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus());
		}
	};

	public static AbstractStatusEffect ASS_STATUS = new AbstractStatusEffect(96,
			"Ass status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.ASS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.ASS);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.ASS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.ASS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.ass]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.ass]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(hotdogging)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(hotdogging)] [npc2.name]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-hotdogging)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-hotdogging)] [npc2.name]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-hotdogging)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-hotdogging)] [npc2.name]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(licking)] [npc2.her] own [npc.ass]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(licking)] [npc2.namePos] [npc2.ass]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-hotdogging)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-hotdogging)] [npc2.name]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.ass] with [npc.her] [npc.feet]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.ass] with [npc.her] [npc.feet]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(licking)] [npc2.her] own [npc.ass]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(licking)] [npc2.namePos] [npc2.ass]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(hotdogging)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(hotdogging)] [npc2.name]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.ass]"), descriptionSB);
			
			descriptionSB.append("</p>");

			
			return descriptionSB.toString();
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.ASS;
			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return new Value<>(3,
					Main.sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice)));
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.ASS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAss());
		}
	};
	
	public static AbstractStatusEffect MOUTH_STATUS = new AbstractStatusEffect(99,
			"Mouth status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.MOUTH);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.MOUTH);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.MOUTH);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.MOUTH;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking)] [npc.her] own [npc.fingers]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(sucking)] [npc.namePos] [npc.fingers]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] giving [npc.herself] a [style.boldSex(blowjob)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] giving [npc.name] a [style.boldSex(blowjob)]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking)] [npc.her] own [npc.tail]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(sucking)] [npc.namePos] [npc.tail]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking)] [npc.her] own tentacle!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(sucking)] [npc.namePos] tentacle!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(kissing)] [npc.name]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking)] [npc.her] own [npc.clit]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(sucking)] [npc.namePos] [npc.clit]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(licking)] [npc.her] own [npc.feet]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(licking)] [npc.namePos] [npc.feet]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(licking)] [npc.her] own [npc.armpits]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(licking)] [npc.namePos] [npc.armpits]!"));
								}
								break;
							case ANUS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(anilingus)] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex(anilingus)] on [npc.name]!"));
								}
								break;
							case ASS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(licking)] [npc.her] own [npc.ass]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(licking)] [npc.namePos] [npc.ass]!"));
								}
								break;
							case BREAST:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.breasts]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(kissing)] [npc.namePos] [npc.breasts]!"));
								}
								break;
							case NIPPLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking on)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(sucking on)] [npc.namePos] [npc.nipples]!"));
								}
								break;
							case BREAST_CROTCH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.crotchBoobs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(kissing)] [npc.namePos] [npc.crotchBoobs]!"));
								}
								break;
							case NIPPLE_CROTCH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(sucking on)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(sucking on)] [npc.namePos] [npc.crotchNipples]!"));
								}
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(kissing)] [npc.name]!"));
								}
								break;
							case THIGHS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.legs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] [style.boldSex(kissing)] [npc.namePos] [npc.legs]!"));
								}
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] giving [npc.herself] a [style.boldSex(blowjob)]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] giving [npc.name] a [style.boldSex(blowjob)]!"));
								}
								break;
							case URETHRA_VAGINA:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(cunnilingus)] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex(cunnilingus)] on [npc.name]!"));
								}
								break;
							case VAGINA:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(cunnilingus)] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex(cunnilingus)] on [npc.name]!"));
								}
								break;
							case SPINNERET:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(oral)] on [npc.her] own spinneret!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex(oral)] on [npc.namePos] spinneret!"));
								}
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] mouth"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.MOUTH);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.MOUTH, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMouth());
		}
	};
	
	public static AbstractStatusEffect BREAST_STATUS = new AbstractStatusEffect(98,
			"Breast status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.hasBreasts()) {
				return "Breast status";
			} else {
				return "Chest status";
			}
		}
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.BREAST);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.BREAST);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.BREAST);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.BREAST;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.breasts]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.breasts]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex("+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex("+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.name]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(tail-"+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex(tail-"+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.name]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(tentacle-"+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex(tentacle-"+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.name]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.breasts]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.breasts]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex("+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.her] own [npc.clit]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex("+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.namePos] [npc.clit]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.breasts] with [npc.her] [npc.feet]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.breasts] with [npc.her] [npc.feet]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.breasts]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.breasts]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex("+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex("+(target.isBreastFuckablePaizuri()?"paizuri":"naizuri")+")] on [npc.name]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.breasts]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.BREAST;
			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return new Value<>(3,
					Main.sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice)));
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& (!target.isFeral() || target.getFeralAttributes().isBreastsPresent())
					&& Main.sex.getAllParticipants(true).contains(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.BREAST, owner.hasBreasts()?SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreasts():SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsFlat());
		}
	};
	
	public static AbstractStatusEffect NIPPLE_STATUS = new AbstractStatusEffect(97,
			"Nipple status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.NIPPLE);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.NIPPLE);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.NIPPLE);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.NIPPLE;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc2.namePos] [npc2.nipples]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.nipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.nipples]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.nipples]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.NIPPLE);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
//					&& target.isBreastFuckableNipplePenetration()
//					&& Main.getProperties().hasValue(PropertyValue.nipplePenContent)
					;
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.NIPPLE, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple());
		}
	};
	
	public static AbstractStatusEffect BREAST_CROTCH_STATUS = new AbstractStatusEffect(98,
			"Crotch-boob status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Udders status";
			} else {
				return "Crotch-boob status";
			}
		}
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.BREAST_CROTCH);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.BREAST_CROTCH);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.BREAST_CROTCH);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.BREAST_CROTCH;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.crotchBoobs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.crotchBoobs]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex([npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] on [npc.name]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] on [npc.her] own [npc.tail]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex([npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] on [npc.namePos] [npc.tail]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] on [npc.her] own [npc.tentacle]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex([npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] on [npc.namePos] [npc.tentacle]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.crotchBoobs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.crotchBoobs]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] on [npc.her] own [npc.clit]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] on [npc.namePos] [npc.clit]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.crotchBoobs] with [npc.her] [npc.feet]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.crotchBoobs] with [npc.her] [npc.feet]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.crotchBoobs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.crotchBoobs]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] on [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing [style.boldSex([npc.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+")] on [npc.name]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.crotchBoobs]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.BREAST_CROTCH;
			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return new Value<>(3,
					Main.sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice)));
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasBreastsCrotch()
					&& (Main.getProperties().getUddersLevel()>0 || target.isFeral());
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.BREAST_CROTCH,
					owner.getBreastCrotchShape()==BreastShape.UDDERS
						?SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUdders()
						:SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsCrotch());
		}
	};
	
	public static AbstractStatusEffect NIPPLE_CROTCH_STATUS = new AbstractStatusEffect(97,
			"Nipple status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Udders teat status";
			} else {
				return "Crotch-boobs nipple status";
			}
		}
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.NIPPLE_CROTCH);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.NIPPLE_CROTCH;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.crotchNipples]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.crotchNipples]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.crotchNipples]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasBreastsCrotch()
					&& (Main.getProperties().getUddersLevel()>0 || target.isFeral())
					&& target.isBreastCrotchFuckableNipplePenetration()
					&& Main.getProperties().hasValue(PropertyValue.nipplePenContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.NIPPLE_CROTCH, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple());
		}
	};
	
	public static AbstractStatusEffect URETHRA_PENIS_STATUS = new AbstractStatusEffect(97,
			"Penis Urethra status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.URETHRA_PENIS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.URETHRA_PENIS);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.URETHRA_PENIS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.URETHRA_PENIS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.urethraPenis]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.urethraPenis]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.urethraPenis]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.URETHRA_PENIS);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.isUrethraFuckable()
					&& Main.getProperties().hasValue(PropertyValue.urethralContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.URETHRA_PENIS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraPenis());
		}
	};
	
	public static AbstractStatusEffect URETHRA_VAGINA_STATUS = new AbstractStatusEffect(97,
			"Vaginal Urethra status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.URETHRA_VAGINA);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.URETHRA_VAGINA);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.URETHRA_VAGINA);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.URETHRA_VAGINA;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.urethraVagina]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] [npc2.urethraVagina]!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.urethraVagina]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.URETHRA_VAGINA);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.isVaginaUrethraFuckable()
					&& Main.getProperties().hasValue(PropertyValue.urethralContent);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.URETHRA_VAGINA, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraVagina());
		}
	};
	
	public static AbstractStatusEffect VAGINA_STATUS = new AbstractStatusEffect(95,
			"Pussy status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.VAGINA);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.VAGINA);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.VAGINA);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.VAGINA;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			
			boolean descriptionAdded = false;
			for(SexAreaPenetration pen : SexAreaPenetration.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, pen)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(pen) {
						case FINGER:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fingering)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fucking)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case TAIL:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tail-fucking)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case TENTACLE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tentacle-fucking)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case TONGUE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(cunnilingus)] [npc.her] own [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" performing [style.boldSex(cunnilingus)] on [npc.name]!"));
							}
							break;
						case CLIT:
							if(selfAction) {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own [npc.pussy]!"));
								} else {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] own [npc.pussy]!"));
								}
							} else {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
											+" [style.boldSex(clit-fucking)] [npc.namePos] [npc.pussy]!"));
								} else {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
											+" [style.boldSex(tribbing)] with [npc2.name]!"));
								}
							}
							break;
						case FOOT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
										+(names.size()==1?UtilText.parse(main, " [npc.is] [style.boldSex(pushing [npc.her] [npc.toes])]"):" are [style.boldSex(pushing their toes)]")
										+"  into [npc.namePos] [npc.pussy]!"));
							}
							break;
					}
				}
			}

			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, orifice)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(orifice) {
						case ARMPITS:
						case ANUS:
						case ASS:
						case BREAST:
						case NIPPLE:
						case BREAST_CROTCH:
						case NIPPLE_CROTCH:
							break;
						case MOUTH:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(cunnilingus)] on [npc.herself]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" performing [style.boldSex(cunnilingus)] on [npc2.name]!"));
							}
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fucking)] [npc.namePos] [npc.pussy]!"));
							}
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] own [npc.pussy]!")); //???
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tribbing)] with [npc2.name]!"));
							}
							break;
						case SPINNERET:
							break;
					}
				}
			}
			
			if(!descriptionAdded) {
				descriptionSB.append("[style.boldDisabled(No ongoing actions.)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.pussy]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.VAGINA);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasVagina();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.VAGINA, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina());
		}
	};
	
	public static AbstractStatusEffect SPINNERET_STATUS = new AbstractStatusEffect(95,
			"Spinneret status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.SPINNERET);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.SPINNERET);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.SPINNERET);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.SPINNERET;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			
			boolean descriptionAdded = false;
			for(SexAreaPenetration pen : SexAreaPenetration.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, pen)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(pen) {
						case FINGER:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fingering)] [npc.namePos] spinneret!"));
							}
							break;
						case PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fucking)] [npc.namePos] spinneret!"));
							}
							break;
						case TAIL:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tail-fucking)] [npc.namePos] spinneret!"));
							}
							break;
						case TENTACLE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tentacle-fucking)] [npc.namePos] spinneret!"));
							}
							break;
						case TONGUE:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(oral)] [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" performing [style.boldSex(oral)] on [npc.namePos] spinneret!"));
							}
							break;
						case CLIT:
							if(selfAction) {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own spinneret!"));
								} else {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] own spinneret!"));
								}
							} else {
								if(main.isClitorisPseudoPenis()) {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
											+" [style.boldSex(clit-fucking)] [npc.namePos] spinneret!"));
								} else {
									descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
											+" [style.boldSex(tribbing)] with [npc2.namePos] spinneret!"));
								}
							}
							break;
						case FOOT:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
										+(names.size()==1?UtilText.parse(main, " [npc.is] [style.boldSex(pushing [npc.her] [npc.toes])]"):" are [style.boldSex(pushing their toes)]")
										+"  into [npc.namePos] spinneret!"));
							}
							break;
					}
				}
			}

			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, orifice)) {
					if(main==null) {
						main = c;
					}
					if(c.isPlayer()) {
						names.add(0, UtilText.parse(c, "[npc.name]"));
					} else {
						names.add(UtilText.parse(c, "[npc.name]"));
					}
				}
				if(!names.isEmpty()) {
					descriptionAdded = true;
					boolean selfAction = target.equals(main);
					switch(orifice) {
						case ARMPITS:
						case ANUS:
						case ASS:
						case BREAST:
						case NIPPLE:
						case BREAST_CROTCH:
						case NIPPLE_CROTCH:
							break;
						case MOUTH:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(oral)] on [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" performing [style.boldSex(oral)] on [npc2.namePos] spinneret!"));
							}
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own spinneret!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(fucking)] [npc.namePos] spinneret!"));
							}
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							if(selfAction) {
								descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] own spinneret!")); //???
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tribbing)] with [npc2.namePos] spinneret!"));
							}
							break;
						case SPINNERET:
							break;
					}
				}
			}
			
			if(!descriptionAdded) {
				descriptionSB.append("[style.boldDisabled(No ongoing actions.)]");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] spinneret"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
			return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.SPINNERET);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& target.hasSpinneret();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.SPINNERET, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaSpinneret());
		}
	};
	
	public static AbstractStatusEffect THIGH_STATUS = new AbstractStatusEffect(95,
			"Thigh status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.THIGHS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.THIGHS);
		}
				@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.THIGHS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.THIGHS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.legs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.legs]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own thighs!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] thighs!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own thighs!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc2.namePos] thighs!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own thighs!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] thighs!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.legs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.legs]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own thighs!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc2.namePos] thighs!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.feet])] between [npc.her] own thighs!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.feet])] between [npc2.namePos] thighs!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.legs]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.legs]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own thighs!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] thighs!"));
								}
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] thighs"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.THIGHS;
			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return new Value<>(3,
					Main.sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice)));
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.THIGHS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaThighs());
		}
	};
	
	public static AbstractStatusEffect ARMPIT_STATUS = new AbstractStatusEffect(95,
			"Armpit status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.ARMPITS);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.ARMPITS);
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.ARMPITS);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.ARMPITS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					boolean selfAction = target.equals(entry.getKey());
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(groping)] [npc.her] own [npc.armpits]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(groping)] [npc2.namePos] [npc2.armpits]!"));
								}
								break;
							case PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own armpits!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] armpits!"));
								}
								break;
							case TAIL:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own armpits!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc2.namePos] armpits!"));
								}
								break;
							case TENTACLE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own armpits!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc2.namePos] armpits!"));
								}
								break;
							case TONGUE:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.armpits]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.armpits]!"));
								}
								break;
							case CLIT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own armpits!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc2.namePos] armpits!"));
								}
								break;
							case FOOT:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.feet])] between [npc.her] own armpits!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.feet])] between [npc2.namePos] armpits!"));
								}
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ARMPITS:
							case ANUS:
							case ASS:
							case BREAST:
							case NIPPLE:
							case BREAST_CROTCH:
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc.her] own [npc.armpits]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(kissing)] [npc2.namePos] [npc2.armpits]!"));
								}
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own armpits!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc2.namePos] armpits!"));
								}
								break;
							case URETHRA_VAGINA:
							case VAGINA:
							case SPINNERET:
								break;
						}
					}
				}
			}
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] armpits"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.ARMPITS;
			if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return new Value<>(3,
					Main.sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice)));
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Main.sex.getAllParticipants(true).contains(target)
					&& Main.game.isArmpitContentEnabled();
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.ARMPITS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaArmpits());
		}
	};

	public static AbstractStatusEffect HAND_STATUS = new AbstractStatusEffect(95,
			"Hand status",
			null,
			PresetColour.GENERIC_SEX,
			false,
			null,
			null) {
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getPenetrationArousalPerTurn(target, SexAreaPenetration.FINGER);
		}
		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return 0;
		}
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getPenetrationModifiersAsStringList(target, SexAreaPenetration.FINGER);
		}
		@Override
		public String getDescription(GameCharacter target) {
			StringBuilder descriptionSB = new StringBuilder();
			SexAreaPenetration type = SexAreaPenetration.FINGER;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(target, type).entrySet()) {
				boolean selfAction = target.equals(entry.getKey());
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case CLIT:
								break;
							case PENIS:
								break;
							case TAIL:
								break;
							case TENTACLE:
								break;
							case TONGUE:
								break;
							case FINGER:
								if(selfAction) {
									descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(holding hands)] with [npc.herself]!"));
								} else {
									descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] [style.boldSex(holding hands)] with [npc2.name]!"));
								}
								break;
							case FOOT:
								break;
						}
						
					}
				}
			}
			
			if(Main.sex.getOngoingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No ongoing action.</b>");
			}

			appendPenetrationAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.hands]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		@Override
		protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
			if(Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.FINGER).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.FINGER).get(0);
			
			return new Value<>(3,
					Main.sex.formatPenetration(
					target.getPenetrationDescription(false,
							target,
							SexAreaPenetration.FINGER,
							partner,
							Main.sex.getOngoingActionsMap(target).get(SexAreaPenetration.FINGER).get(partner).iterator().next())));
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!Main.game.isInSex() || !Main.sex.getAllParticipants(true).contains(target) || Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.FINGER).isEmpty()) {
				return false;
			}
			GameCharacter partner = Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.FINGER).get(0);
			return Main.sex.getOngoingSexAreas(target, SexAreaPenetration.FINGER).get(partner).contains(SexAreaPenetration.FINGER);
		}
		@Override
		public boolean isSexEffect() {
			return true;
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaPenetration.FINGER, SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeFinger(), Util.newArrayListOfValues(SexAreaPenetration.FINGER));
		}
	};
	
	
	public static List<AbstractStatusEffect> allStatusEffects;
	public static List<AbstractStatusEffect> allStatusEffectsRequiringApplicationCheck;
	
	public static Map<AbstractStatusEffect, String> statusEffectToIdMap = new HashMap<>();
	public static Map<String, AbstractStatusEffect> idToStatusEffectMap = new HashMap<>();
	
	/**
	 * @param id Will be in the format of: 'innoxia_maid'.
	 */
	public static AbstractStatusEffect getStatusEffectFromId(String id) {
		if(id.equals("innoxia_massaged")) {
			return CLEANED_MASSAGED;
		} else if(id.equals("BATH_BOOSTED") || id.equals("innoxia_cleaned_spa")) {
			return CLEANED_SPA;
		} else if(id.equals("BATH") || id.equals("innoxia_cleaned_bath")) {
			return CLEANED_BATH;
		} else if(id.equals("SHOWER") || id.equals("innoxia_cleaned_shower")) {
			return CLEANED_SHOWER;
		}
		
		if(id.equals("innoxia_item_broodmother_pill")) {
			return BROODMOTHER_PILL;
		}
		
		id = Util.getClosestStringMatch(id, idToStatusEffectMap.keySet());
		
		return idToStatusEffectMap.get(id);
	}
	
	public static String getIdFromStatusEffect(AbstractStatusEffect perk) {
		return statusEffectToIdMap.get(perk);
	}

	static {
		allStatusEffects = new ArrayList<>();
		allStatusEffectsRequiringApplicationCheck = new ArrayList<>();
		
		// Modded status effects:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/statusEffects");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractStatusEffect statusEffect = new AbstractStatusEffect(innerEntry.getValue(), entry.getKey(), true) {};
					allStatusEffects.add(statusEffect);
					statusEffectToIdMap.put(statusEffect, innerEntry.getKey());
					idToStatusEffectMap.put(innerEntry.getKey(), statusEffect);
//					System.out.println("modded SE: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading modded status effect failed at 'StatusEffect'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res status effects:

		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/statusEffects");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractStatusEffect statusEffect = new AbstractStatusEffect(innerEntry.getValue(), entry.getKey(), false) {};
					allStatusEffects.add(statusEffect);
					statusEffectToIdMap.put(statusEffect, innerEntry.getKey());
					idToStatusEffectMap.put(innerEntry.getKey(), statusEffect);
//					System.out.println("res SE: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading status effect failed at 'StatusEffect'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// Hard-coded status effects (all those up above):
		
		Field[] fields = StatusEffect.class.getFields();
		
		for(Field f : fields){
			if (AbstractStatusEffect.class.isAssignableFrom(f.getType())) {
				
				AbstractStatusEffect statusEffect;
				
				try {
					statusEffect = ((AbstractStatusEffect) f.get(null));

					statusEffectToIdMap.put(statusEffect, f.getName());
					idToStatusEffectMap.put(f.getName(), statusEffect);
					allStatusEffects.add(statusEffect);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static List<AbstractStatusEffect> getAllStatusEffects() {
		return allStatusEffects;
	}
	
	public static List<AbstractStatusEffect> getAllStatusEffectsRequiringApplicationCheck() {
		if(!Main.game.isStarted()) {
			return getAllStatusEffects();
		}
		if(allStatusEffectsRequiringApplicationCheck.isEmpty()) { // Initialise on first call
			for(AbstractStatusEffect se : allStatusEffects) {
				se.isConditionsMet(Main.game.getPlayer()); // To initialise the variable
				if(se.isRequiresApplicationCheck()) {
					allStatusEffectsRequiringApplicationCheck.add(se);
				}
			}
//			System.out.println("ASE/SE: "+allStatusEffectsRequiringApplicationCheck.size()+"/"+allStatusEffects.size());
		}
		return allStatusEffectsRequiringApplicationCheck;
	}
}
