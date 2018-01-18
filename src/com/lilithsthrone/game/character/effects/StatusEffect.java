package com.lilithsthrone.game.character.effects;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.FitnessLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.StrengthLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AddictionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.ReindeerOverseer;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingSet;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.slavery.SlaveJob;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.1.96
 * @author Innoxia
 */
public enum StatusEffect {

	// Attribute-related status effects:
	// Strength:
	STRENGTH_PERK_0(
			100,
			"sissy",
			"attStrength0",
			Colour.STRENGTH_STAGE_ZERO,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PURE, -15f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, -15f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(StrengthLevel.ZERO_WEAK.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are incredibly weak. You struggle to do much damage with your wimpy little [pc.arms], and your fragile body is vulnerable to all damage sources.";
			else
				return UtilText.parse(target, "[npc.Name] is incredibly weak. [npc.She] struggles to do much damage with [npc.her] wimpy little [npc.arms], and [npc.her] fragile body is vulnerable to all damage sources.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return StrengthLevel.getStrengthLevelFromValue(target.getAttributeValue(Attribute.STRENGTH)) == StrengthLevel.ZERO_WEAK;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	STRENGTH_PERK_1(
			100,
			"average",
			"attStrength1",
			Colour.STRENGTH_STAGE_ONE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 5f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(StrengthLevel.ONE_AVERAGE.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You have an average level of strength for a human.";
			else
				return UtilText.parse(target, "[npc.Name] is about as strong as an average human.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return StrengthLevel.getStrengthLevelFromValue(target.getAttributeValue(Attribute.STRENGTH)) == StrengthLevel.ONE_AVERAGE;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	STRENGTH_PERK_2(
			100,
			"strong",
			"attStrength2",
			Colour.STRENGTH_STAGE_TWO,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 10f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(StrengthLevel.TWO_STRONG.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are stronger than an average human.";
			else
				return UtilText.parse(target, "[npc.Name] is stronger than an average human.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return StrengthLevel.getStrengthLevelFromValue(target.getAttributeValue(Attribute.STRENGTH)) == StrengthLevel.TWO_STRONG;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	STRENGTH_PERK_3(
			100,
			"powerful",
			"attStrength3",
			Colour.STRENGTH_STAGE_THREE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 15f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(StrengthLevel.THREE_POWERFUL.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are stronger than even an average horse-boy, and, when compared to an average human, you are on the same level as a bodybuilder.";
			else
				return UtilText.parse(target, "[npc.Name] is stronger than an average horse-boy.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return StrengthLevel.getStrengthLevelFromValue(target.getAttributeValue(Attribute.STRENGTH)) == StrengthLevel.THREE_POWERFUL;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	STRENGTH_PERK_4(
			100,
			"mighty",
			"attStrength4",
			Colour.STRENGTH_STAGE_FOUR,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 20f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 20f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(StrengthLevel.FOUR_MIGHTY.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your strength is enough to rival a dragon-morph's, and, when compared to the more common races of Dominion, their strength pales in comparison to yours.";
			else
				return UtilText.parse(target, "[npc.Name] is as strong as a dragon-morph!");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return StrengthLevel.getStrengthLevelFromValue(target.getAttributeValue(Attribute.STRENGTH)) == StrengthLevel.FOUR_MIGHTY;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	STRENGTH_PERK_5(
			100,
			"Herculean",
			"attStrength5",
			Colour.STRENGTH_STAGE_FIVE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, 10f),
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, 50f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(StrengthLevel.FIVE_HERCULEAN.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Your body is the stuff of legend; mere mortals look upon you in fear and awe!";
			else
				return UtilText.parse(owner, "[npc.Name]'s body is the stuff of legend.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return StrengthLevel.getStrengthLevelFromValue(target.getAttributeValue(Attribute.STRENGTH)) == StrengthLevel.FIVE_HERCULEAN;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},

	// Intelligence:
	INTELLIGENCE_PERK_0(
			80,
			"airhead",
			"attIntelligence0",
			Colour.INTELLIGENCE_STAGE_ZERO,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, -25f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, -25f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.ZERO_AIRHEAD.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are a complete airhead. You struggle to perform even the most basic of arithmetic, and would likely lose a battle of wits against a brick wall.";
			else
				return UtilText.parse(owner, "[npc.Name] has a blank look on [npc.her] face, and every now and then, [npc.she] lets out a little giggle at nothing.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.INTELLIGENCE)) == IntelligenceLevel.ZERO_AIRHEAD;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	INTELLIGENCE_PERK_1(
			100,
			"average",
			"attIntelligence1",
			Colour.INTELLIGENCE_STAGE_ONE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 5f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 5f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.ONE_AVERAGE.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You have an average level of intelligence for a human.";
			else
				return UtilText.parse(target, "[npc.Name] is about as intelligent as an average human.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.INTELLIGENCE)) == IntelligenceLevel.ONE_AVERAGE;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	INTELLIGENCE_PERK_2(
			100,
			"smart",
			"attIntelligence2",
			Colour.INTELLIGENCE_STAGE_TWO,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 10f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 10f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.TWO_SMART.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You're pretty smart. Your intelligence is significantly higher than that of an average human's.";
			else
				return UtilText.parse(target, "[npc.Name] is significantly more intelligent than an average human.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.INTELLIGENCE)) == IntelligenceLevel.TWO_SMART;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	INTELLIGENCE_PERK_3(
			100,
			"brainy",
			"attIntelligence3",
			Colour.INTELLIGENCE_STAGE_THREE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 15f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 15f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.THREE_BRAINY.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are exceptionally clever. A demon or angel has an average intelligence of this sort of level.";
			else
				return UtilText.parse(target, "[npc.Name] is as intelligent as a regular demon or angel.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.INTELLIGENCE)) == IntelligenceLevel.THREE_BRAINY;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	INTELLIGENCE_PERK_4(
			100,
			"genius",
			"attIntelligence4",
			Colour.INTELLIGENCE_STAGE_FOUR,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 20f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 20f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.FOUR_GENIUS.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your intelligence is comparable to a Lilin's, or, perhaps more relatably, to the greatest minds in human history.";
			else
				return UtilText.parse(target, "[npc.Name] is as intelligent as a Lilin.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.INTELLIGENCE)) == IntelligenceLevel.FOUR_GENIUS;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	INTELLIGENCE_PERK_5(
			100,
			"polymath",
			"attIntelligence5",
			Colour.INTELLIGENCE_STAGE_FIVE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 25f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 25f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PURE, 5f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, 50f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.FIVE_POLYMATH.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "The greatest scientists and philosophers in human history pale in comparison to your mighty intellect.";
			else
				return UtilText.parse(owner, "[npc.Name] is one of the most intelligent beings to ever have existed.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.INTELLIGENCE)) == IntelligenceLevel.FIVE_POLYMATH;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},

	// Agility:
	FITNESS_PERK_0(
			80,
			"klutz",
			"attFitness0",
			Colour.FITNESS_STAGE_ZERO,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, -25f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, -25f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(FitnessLevel.ZERO_KLUTZ.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are incredibly clumsy, and sometimes even struggle to place one foot in front of the other.";
			else
				return UtilText.parse(owner, owner.getName("The")
						+ " is extremely clumsy, and constantly trips over [npc.her] own feet.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return FitnessLevel.getFitnessLevelFromValue(target.getAttributeValue(Attribute.FITNESS)) == FitnessLevel.ZERO_KLUTZ;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	FITNESS_PERK_1(
			100,
			"average",
			"attFitness1",
			Colour.FITNESS_STAGE_ONE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 5f),
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 4f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(FitnessLevel.ONE_AVERAGE.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You have an average level of fitness for a human.";
			else
				return UtilText.parse(target, "[npc.Name] is about as fit as an average human.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return FitnessLevel.getFitnessLevelFromValue(target.getAttributeValue(Attribute.FITNESS)) == FitnessLevel.ONE_AVERAGE;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	FITNESS_PERK_2(
			100,
			"flexible",
			"attFitness2",
			Colour.FITNESS_STAGE_TWO,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 10f),
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 8f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(FitnessLevel.TWO_FLEXIBLE.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are very flexible, and can easily do the splits and cartwheels on demand.";
			else
				return UtilText.parse(target, "[npc.Name] is very flexible.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return FitnessLevel.getFitnessLevelFromValue(target.getAttributeValue(Attribute.FITNESS)) == FitnessLevel.TWO_FLEXIBLE;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	FITNESS_PERK_3(
			100,
			"limber",
			"attFitness3",
			Colour.FITNESS_STAGE_THREE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 15f),
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 12f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(FitnessLevel.THREE_LIMBER.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are extremely fit, and can easily contort your body into all sorts of uncomfortable-looking shapes.";
			else
				return UtilText.parse(target, "[npc.Name] is incredibly fit.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return FitnessLevel.getFitnessLevelFromValue(target.getAttributeValue(Attribute.FITNESS)) == FitnessLevel.THREE_LIMBER;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	FITNESS_PERK_4(
			100,
			"athletic",
			"attFitness4",
			Colour.FITNESS_STAGE_FOUR,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 20f),
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 16f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(FitnessLevel.FOUR_ATHLETIC.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You have the same level of fitness as a top-class athlete, and could easily run a full marathon with enough energy left over to do it all over again.";
			else
				return UtilText.parse(target, "[npc.Name] is exceedingly fit and healthy.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return FitnessLevel.getFitnessLevelFromValue(target.getAttributeValue(Attribute.FITNESS)) == FitnessLevel.FOUR_ATHLETIC;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	FITNESS_PERK_5(
			100,
			"acrobat",
			"attFitness5",
			Colour.FITNESS_STAGE_FIVE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 25f),
					new Value<Attribute, Float>(Attribute.STAMINA_MAXIMUM, 50f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PURE, 5f),
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 20f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(FitnessLevel.FIVE_ACROBAT.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have super-human reflexes and agility, and each of your steps radiate an ethereal grace.";
			else
				return UtilText.parse(owner, "[npc.Name] moves with an ethereal grace, making [npc.her] supreme agility apparent in each of [npc.her] delicate steps.");
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return FitnessLevel.getFitnessLevelFromValue(target.getAttributeValue(Attribute.FITNESS)) == FitnessLevel.FIVE_ACROBAT;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},

	// Corruption:
	CORRUPTION_PERK_0(100,
			"Pure",
			"attCorruption0",
			Colour.CORRUPTION_STAGE_ZERO,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, 25f)),
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
				return UtilText.parse(owner, "[npc.Name] is completely uncorrupted, and aside from performing the most conservative of sexual acts with the person [npc.she] loves, [npc.she]'s not really interested in sex at all.");
			}
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.CORRUPTION)) == CorruptionLevel.ZERO_PURE;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	CORRUPTION_PERK_1(
			100,
			"Vanilla",
			"attCorruption1",
			Colour.CORRUPTION_STAGE_ONE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, -10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 5f)),
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
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.CORRUPTION)) == CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	CORRUPTION_PERK_2(
			100,
			"dirty",
			"attCorruption2",
			Colour.CORRUPTION_STAGE_TWO,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, -20f),
					new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 10f)),
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
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.CORRUPTION)) == CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	CORRUPTION_PERK_3(
			100,
			"Lewd",
			"attCorruption3",
			Colour.CORRUPTION_STAGE_THREE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, -30f),
					new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 10f),
					new Value<Attribute, Float>(Attribute.FERTILITY, 25f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 25f),
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 15f)),
			null) {

		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.THREE_DIRTY.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE) {
					return "Given power by the fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body, and you feel as though it's going to be far easier to get pregnant from now on...";
				} else if (Main.game.getPlayer().getPenisType() != PenisType.NONE) {
					return "Given power by the fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body,"
							+ " and you feel as though it's going to be far easier to impregnate your sexual partners from now on...";
				} else {
					return "Given power by the fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body, but because you don't have any sexual organs, there's not much that's happened...";
				}
				
			} else {
				if (owner.getVaginaType() != VaginaType.NONE) {
					return UtilText.parse(owner,
							"Given power by the fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.name]'s body, making it far easier for [npc.herHim] to get pregnant.");
				} else if (owner.getPenisType() != PenisType.NONE) {
					return UtilText.parse(owner,
							"Given power by the fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.name]'s body, making it far easier for [npc.herHim] to impregnate others.");
				} else {
					return UtilText.parse(owner,
							"Given power by the fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.name]'s body,"
							+ " but because [npc.she] doesn't have any sexual organs, there's not much that's happened.");
				}
			}
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.CORRUPTION)) == CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	CORRUPTION_PERK_4(
			100,
			"Lustful",
			"attCorruption4",
			Colour.CORRUPTION_STAGE_FOUR,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, -40f),
					new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 10f),
					new Value<Attribute, Float>(Attribute.FERTILITY, 50f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 50f),
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 20f)),
			null) {

		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.FOUR_LUSTFUL.getName());
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE) {
					return "Given a huge amount of power by the lewd fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body,"
							+ " and you feel as though it's going to be far easier to get pregnant from now on...";
				} else if (Main.game.getPlayer().getPenisType() != PenisType.NONE) {
					return "Given a huge amount of power by the lewd fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body,"
							+ " and you feel as though it's going to be far easier to impregnate your sexual partners from now on...";
				} else {
					return "Given a huge amount of power by the lewd fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body,"
							+ " but because you don't have any sexual organs, there's not much that's happened...";
				}
				
			} else {
				if (owner.getVaginaType() != VaginaType.NONE) {
					return UtilText.parse(owner,
							"Given a huge amount of power by the lewd fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.name]'s body,"
							+ " making it far easier for [npc.herHim] to get pregnant.");
				} else if (owner.getPenisType() != PenisType.NONE) {
					return UtilText.parse(owner,
							"Given a huge amount of power by the lewd fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.name]'s body,"
							+ " making it far easier for [npc.herHim] to impregnate others.");
				} else {
					return UtilText.parse(owner,
							"Given a huge amount of power by the lewd fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.name]'s body,"
							+ " but because [npc.she] doesn't have any sexual organs, there's not much that's happened.");
				}
			}
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.CORRUPTION)) == CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	CORRUPTION_PERK_5(
			100,
			"Corrupt",
			"attCorruption5",
			Colour.CORRUPTION_STAGE_FIVE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, -50f),
					new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 25f),
					new Value<Attribute, Float>(Attribute.FERTILITY, 75f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 75f),
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 25f)),
			Util.newArrayListOfValues(new ListValue<String>("<b style='color: "+ Colour.ATTRIBUTE_CORRUPTION.toWebHexString()+ "'>Obeys Lilin</b>"))) {

		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.FIVE_CORRUPT.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You are completely and utterly corrupted. The lewd thoughts and fantasies that continuously run through your mind have unlocked the full power of the arcane, making your body hyper-fertile and virile.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is completely and utterly corrupted."
						+ " The lewd thoughts and fantasies that continuously run through [npc.her] mind have unlocked the full power of the arcane, making [npc.her] body hyper-fertile and virile.");
			}

		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.CORRUPTION)) == CorruptionLevel.FIVE_CORRUPT;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	// Arousal:
	AROUSAL_PERK_0(
			100,
			"none",
			"attArousal0",
			Colour.AROUSAL_STAGE_ZERO,
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
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.ZERO_NONE;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	AROUSAL_PERK_1(
			100,
			"turned on",
			"attArousal1",
			Colour.AROUSAL_STAGE_ONE,
			false,
			null,
			null) {
		
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
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.ONE_TURNED_ON;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	AROUSAL_PERK_2(
			100,
			"excited",
			"attArousal2",
			Colour.AROUSAL_STAGE_TWO,
			false,
			null,
			null) {
		
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
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.TWO_EXCITED;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	AROUSAL_PERK_3(
			100,
			"heated",
			"attArousal3",
			Colour.AROUSAL_STAGE_THREE,
			false,
			null,
			null) {
		
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
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.THREE_HEATED;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	AROUSAL_PERK_4(
			100,
			"passionate",
			"attArousal4",
			Colour.AROUSAL_STAGE_FOUR,
			false,
			null,
			Util.newArrayListOfValues(
					new ListValue<String>("<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>Mutual orgasm</b>"))) {
		
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
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.FOUR_PASSIONATE;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	AROUSAL_PERK_5(
			100,
			"imminent orgasm",
			"attArousal5",
			Colour.AROUSAL_STAGE_FIVE,
			false,
			null,
			Util.newArrayListOfValues(
					new ListValue<String>("<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>Mutual orgasm</b>"))) {
		
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
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.FIVE_ORGASM_IMMINENT;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	
	// Arousal:
	LUST_PERK_0(
			100,
			"none",
			"attLust0",
			Colour.LUST_STAGE_ZERO,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.ZERO_COLD.getName());
		}
		
//		@Override
//		public String getSVGString(GameCharacter character) {
//			return LustLevel.ZERO_COLD.getSVGImage(character);
//		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			modifiersList.clear();
			modifiersList.add(LustLevel.ZERO_COLD.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modifiersList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.ZERO_COLD.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.ZERO_COLD;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	LUST_PERK_1(
			100,
			"turned on",
			"attLust1",
			Colour.LUST_STAGE_ONE,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.ONE_HORNY.getName());
		}
		
//		@Override
//		public String getSVGString(GameCharacter character) {
//			return LustLevel.ONE_HORNY.getSVGImage(character);
//		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			modifiersList.clear();
			modifiersList.add(LustLevel.ONE_HORNY.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modifiersList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.ONE_HORNY.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.ONE_HORNY;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	LUST_PERK_2(
			100,
			"excited",
			"attLust2",
			Colour.LUST_STAGE_TWO,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.TWO_AMOROUS.getName());
		}
		
//		@Override
//		public String getSVGString(GameCharacter character) {
//			return LustLevel.TWO_AMOROUS.getSVGImage(character);
//		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			modifiersList.clear();
			modifiersList.add(LustLevel.TWO_AMOROUS.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modifiersList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.TWO_AMOROUS.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.TWO_AMOROUS;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	LUST_PERK_3(
			100,
			"heated",
			"attLust3",
			Colour.LUST_STAGE_THREE,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.THREE_IMPASSIONED.getName());
		}
		
//		@Override
//		public String getSVGString(GameCharacter character) {
//			return LustLevel.THREE_IMPASSIONED.getSVGImage(character);
//		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			modifiersList.clear();
			modifiersList.add(LustLevel.THREE_IMPASSIONED.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modifiersList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.THREE_IMPASSIONED.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.THREE_IMPASSIONED;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	LUST_PERK_4(
			100,
			"passionate",
			"attLust4",
			Colour.LUST_STAGE_FOUR,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.FOUR_BURNING.getName());
		}
		
//		@Override
//		public String getSVGString(GameCharacter character) {
//			return LustLevel.FOUR_BURNING.getSVGImage(character);
//		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			modifiersList.clear();
			modifiersList.add(LustLevel.FOUR_BURNING.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modifiersList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.FOUR_BURNING.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.FOUR_BURNING;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	
	
	
	// STANDARD EFFECTS:
	
	WEATHER_PROLOGUE(100,
			"Strange Atmosphere",
			"weatherNightStormIncoming",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues(new ListValue<String>("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "There's a strange atmosphere surrounding the museum this evening, and you inexplicably find yourself feeling incredibly aroused...";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInNewWorld();
		}
	},
	
	WEATHER_CLEAR(100,
			"Clear skies",
			"weatherDayClear",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues(new ListValue<String>("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(Main.game.isDayTime()) {
				return "The sun shines down on you, and you let out a contented sigh as you look up at the clear blue sky."
						+ " Although there's no sign of a storm at the moment, you can still feel the effects of the arcane manifesting in the form of an increased libido.";
			} else {
				return "The moon shines down through a clear night's sky, and you let out a contented sigh as you look up at the stars."
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
	},
	
	WEATHER_CLOUD(100,
			"Cloudy skies",
			"weatherDayCloudy",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues(new ListValue<String>("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

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
	},
	
	WEATHER_RAIN(100,
			"Rain",
			"weatherDayRain",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues(new ListValue<String>("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

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
	},
	
	WEATHER_SNOW(100,
			"Snow",
			"weatherDaySnow",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues(new ListValue<String>("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
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
							+ " Their large, cloven hooves allow them to traverse the snow-bound streets with incredible ease, and they quickly split up into numerous snow-shovelling groups,"
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
	},
	
	WEATHER_STORM_GATHERING(100,
			"Gathering storm",
			"weatherDayStormIncoming",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues(new ListValue<String>("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

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
	},
	
	WEATHER_STORM(100,
			"Arcane storm",
			"weatherDayStorm",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, -15f)),
			Util.newArrayListOfValues(new ListValue<String>("<b style='color: "
					+ Colour.GENERIC_ARCANE.toWebHexString()
					+ ";'>Enhanced libido</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
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
						+ " Although resistant to most of its arousing power, you're not completely unaffected, and you find yourself feeling hornier than usual.";
			} else {
				return UtilText.parse(target, "[npc.Name] seems to be just as resistant to the ongoing arcane storm as you are!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if((target.isPlayer() || !target.getRace().isVulnerableToLilithsLustStorm()) && !target.getLocationPlace().isStormImmune()) {
				return Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.isInNewWorld();
				
			} else {
				return false;
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStorm();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStorm();
			}
		}
	},
	
	WEATHER_STORM_VULNERABLE(100,
			"Arcane storm",
			"weatherDayStorm",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, -75f)),
			Util.newArrayListOfValues(new ListValue<String>("<b style='color: "
					+ Colour.GENERIC_ARCANE.toWebHexString()
					+ ";'>Enhanced libido</b>"),
					new ListValue<String>("<b style='color: "
							+ Colour.GENERIC_ARCANE.toWebHexString()
							+ ";'>Overwhelming Lust</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] is being heavily affected by the ongoing arcane storm.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.getRace().isVulnerableToLilithsLustStorm() && !target.isPlayer() && !target.getLocationPlace().isStormImmune()) {
				return Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.isInNewWorld();
			} else {
				return false;
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime())
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStorm();
			else
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStorm();
		}
	},
	
	WEATHER_STORM_PROTECTED(100,
			"Arcane storm (protected)",
			"weatherDayStorm",
			Colour.GENERIC_GOOD,
			false,
			null,
			Util.newArrayListOfValues(new ListValue<String>("<b style='color: "
					+ Colour.GENERIC_ARCANE.toWebHexString()
					+ ";'>Enhanced libido</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
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
			if(target.getLocationPlace().isStormImmune()) {
				return Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.isInNewWorld();
			} else {
				return false;
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime())
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStormProtected();
			else
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStormProtected();
		}
	},
	
	
	// RACES:
	// HUMAN:
	PURE_HUMAN_PROLOGUE(
			90,
			"human",
			"raceHuman",
			Colour.CLOTHING_WHITE,
			true,
			null,
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "You're a human, just like every other person in this world.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.HUMAN
					&& target.getRaceStage() == RaceStage.HUMAN
					&& !Main.game.isInNewWorld();
		}
	},
	
	PURE_HUMAN(
			90,
			"human",
			"raceHuman",
			Colour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, 20f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "Humans have a much higher resistance to the arousing effects of the arcane than any other race.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.HUMAN
					&& target.getRaceStage() == RaceStage.HUMAN
					&& Main.game.isInNewWorld();
		}
		
		@Override
		protected boolean needsDesaturated() {
			return true;
		}
	},

	// ANGEL:
	ANGEL(
			90,
			"angel",
			"raceHuman",
			Colour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, 100f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "Angels are completely immune to corruption.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.ANGEL
					&& target.getRaceStage() == RaceStage.GREATER;
		}
		
		@Override
		protected boolean needsDesaturated() {
			return true;
		}
	},

	// DEMON: TODO
	DEMON(
			90,
			"demon",
			"raceDemon",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CORRUPTION, 25f),
					new Value<Attribute, Float>(Attribute.FITNESS, 10f),
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 75f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You have been twisted by arcane corruption into becoming a demon!"
						+ " (You hear a little voice in your head saying: 'Demon transformations don't actually work like this in the lore! This is just to have some fun until I implement proper TF mechanics!')";
			else
				return UtilText.parse(target,
						"Due to the fact that demons are very easily able to harness arcane power, "+target.getName("this")+"'s spell-casting abilities are truly a terrifying force to behold!");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.DEMON
					&& target.getRaceStage() == RaceStage.GREATER;
		}
		
		@Override
		protected boolean needsDesaturated() {
			return true;
		}
	},

	// CANINE:
	DOG_MORPH(
			90,
			"dog-morph",
			"raceDogMorph",
			Colour.RACE_DOG_MORPH,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.FITNESS, 5f), new Value<Attribute, Float>(Attribute.STRENGTH, 5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You always have lots of energy, and get excited about new things very easily.";
			else
				return UtilText.parse(target, target.getName("The")
						+ " always has lots of energy, and [npc.she] gets excited about new things very easily.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.DOG_MORPH
					&& target.getRaceStage() == RaceStage.GREATER;
		}
		
		@Override
		protected boolean needsDesaturated() {
			return true;
		}
	},
	
	WOLF_MORPH(
			90,
			"wolf-morph",
			"raceDogMorph",
			Colour.RACE_WOLF_MORPH,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.CORRUPTION, 5f), new Value<Attribute, Float>(Attribute.FITNESS, 5f), new Value<Attribute, Float>(Attribute.STRENGTH, 10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your wolf-like body is very strong, but you often get strong urges to try and dominate people you meet.";
			else
				return UtilText.parse(target, target.getName("The")
						+ "'s wolf-like body is very strong, but [npc.she] often gets strong urges to try and dominate people [npc.she] meets.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.WOLF_MORPH
					&& target.getRaceStage() == RaceStage.GREATER;
		}
		
		@Override
		protected boolean needsDesaturated() {
			return true;
		}
	},

	// FELINE:
	CAT_MORPH(
			90,
			"cat-morph",
			"raceCatMorph",
			Colour.RACE_CAT_MORPH,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.FITNESS, 5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your body is incredibly agile, and you possess lightning reflexes.";
			else
				return UtilText.parse(target, target.getName("The")
						+ "'s body is incredibly agile, and [npc.she] possesses lightning reflexes.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.CAT_MORPH
					&& target.getRaceStage() == RaceStage.GREATER;
		}
		
		@Override
		protected boolean needsDesaturated() {
			return true;
		}
	},

	// RODENT:
	SQUIRREL_MORPH(
			90,
			"Squirrel-morph",
			"raceSquirrelMorph",
			Colour.RACE_SQUIRREL_MORPH,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.FITNESS, 5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your body is incredibly agile, and you possess lightning reflexes.";
			else
				return UtilText.parse(target, "[npc.Name]'s body is incredibly agile, and [npc.she] possesses lightning reflexes.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.SQUIRREL_MORPH
					&& target.getRaceStage() == RaceStage.GREATER;
		}
		
		@Override
		protected boolean needsDesaturated() {
			return true;
		}
	},

	// EQUINE:
	HORSE_MORPH(
			90,
			"horse-morph",
			"raceHorseMorph",
			Colour.RACE_HORSE_MORPH,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.INTELLIGENCE, -5f), new Value<Attribute, Float>(Attribute.STRENGTH, 15f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your body possesses a great strength, but your mind is considerably slower than it once was.";
			else
				return UtilText.parse(target, target.getName("The")
						+ "'s body possesses a great strength, but [npc.her] mind isn't exactly the quickest.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.HORSE_MORPH
					&& target.getRaceStage() == RaceStage.GREATER;
		}
		
		@Override
		protected boolean needsDesaturated() {
			return true;
		}
	},
	
	REINDEER_MORPH(
			90,
			"reindeer-morph",
			"raceReindeerMorph",
			Colour.RACE_REINDEER_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FITNESS, 5f),
					new Value<Attribute, Float>(Attribute.STRENGTH, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Your reindeer-like body grants you significant resistance to the cold, as well as an increase in strength and fitness.";
			} else {
				return UtilText.parse(target, "[npc.Name]'s reindeer-like body grants [npc.herHim] significant resistance to the cold, as well as an increase in strength and fitness.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.REINDEER_MORPH
					&& target.getRaceStage() == RaceStage.GREATER;
		}
		
		@Override
		protected boolean needsDesaturated() {
			return true;
		}
	},

	// BOVINE:
	COW_MORPH(
			90,
			"cow-morph",
			"raceCowMorph",
			Colour.RACE_COW_MORPH,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.INTELLIGENCE, -5f), new Value<Attribute, Float>(Attribute.STRENGTH, 15f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your body possesses a great strength, but your mind is considerably slower than it once was.";
			else
				return UtilText.parse(target, target.getName("The")
						+ "'s body possesses a great strength, but [npc.her] mind isn't exactly the quickest.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.COW_MORPH
					&& target.getRaceStage() == RaceStage.GREATER;
		}
		
		@Override
		protected boolean needsDesaturated() {
			return true;
		}
	},

	// REPTILE:
	ALLIGATOR_MORPH(
			90,
			"Alligator-morph",
			"raceGatorMorph",
			Colour.RACE_ALLIGATOR_MORPH,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 15f),
			new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 5f),
			new Value<Attribute, Float>(Attribute.STRENGTH, 5f),
			new Value<Attribute, Float>(Attribute.FITNESS, 5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your body is incredibly tough, and you possess lightning reflexes.";
			else
				return UtilText.parse(target, "[npc.Name]'s body is incredibly tough, and [npc.she] possesses lightning reflexes.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.ALLIGATOR_MORPH
					&& target.getRaceStage() == RaceStage.GREATER;
		}
		
		@Override
		protected boolean needsDesaturated() {
			return true;
		}
	},

		// SLIME:
	SLIME(
			90,
			"slime",
			"raceSlime",
			Colour.CLOTHING_BLUE_LIGHT,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 100f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, -100f),
					new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 15f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, -15f)),
			Util.newArrayListOfValues(new ListValue<String>("<b>*</b> <b style='color: "
					+ Colour.CLOTHING_PINK.toWebHexString()
					+ ";'>You can morph your body at will.<b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "Slimes are completely immune to physical damage, but can't really do any physical damage either."
					+ " They can morph their bodies to seem extremely attractive, but also get aroused incredibly easily.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.SLIME
					&& target.getRaceStage() == RaceStage.GREATER;
		}
		
		@Override
		protected boolean needsDesaturated() {
			return true;
		}
	},

	// AVIAN:
	HARPY(
			90,
			"harpy",
			"raceHarpy",
			Colour.CLOTHING_PINK_LIGHT,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 10f), new Value<Attribute, Float>(Attribute.STRENGTH, -10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "You are a harpy, and are extremely proficient at seduction, although you are quite physically weak.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.HARPY
					&& target.getRaceStage() == RaceStage.GREATER;
		}
		
		@Override
		protected boolean needsDesaturated() {
			return true;
		}
	},
	
	
	// SEXUAL ORIENTATIONS:
	
	ORIENTATION_ANDROPHILIC(
			90,
			"androphilic",
			"orientation_androphilic",
			Colour.MASCULINE,
			true,
			null,
			Util.newArrayListOfValues(
					new ListValue<String>(
							"<b>-50%</b> <b style='color:"+ Colour.DAMAGE_TYPE_MANA.toWebHexString()+ ";'>Willpower damage</b> from <b style='color:"+ Colour.FEMININE.toWebHexString()+ ";'>feminine opponents</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

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
	},
	ORIENTATION_GYNEPHILIC(
			90,
			"gynephilic",
			"orientation_gynephilic",
			Colour.FEMININE,
			true,
			null,
			Util.newArrayListOfValues(
					new ListValue<String>(
							"<b>-50%</b> <b style='color:"+ Colour.DAMAGE_TYPE_MANA.toWebHexString()+ ";'>Willpower damage</b> from <b style='color:"+ Colour.MASCULINE.toWebHexString()+ ";'>masculine opponents</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

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
	},
	ORIENTATION_AMBIPHILIC(
			90,
			"ambiphilic",
			"orientation_ambiphilic",
			Colour.ANDROGYNOUS,
			true,
			null,
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

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
	},
	

	// CLOTHING:

	CLOTHING_FEMININITY(
			85,
			"clothing too feminine",
			"clothingFemininity",
			Colour.CLOTHING_PINK_LIGHT,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.INTELLIGENCE, -5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "Some of your clothes are too feminine for your masculine figure."
					+ " You find yourself incredibly embarrassed by wearing such clothing, causing you to struggle to think clearly.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.hasFetish(Fetish.FETISH_CROSS_DRESSER)) {
				return false;
			}
			
			for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if (c.getClothingType().getFemininityMinimum() > target.getFemininityValue()) {
					return true;
				}
			}
			
			return false;
		}
	},
	CLOTHING_MASCULINITY(
			85,
			"clothing too masculine",
			"clothingMasculinity",
			Colour.CLOTHING_BLUE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.INTELLIGENCE, -5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "Some of your clothes are too masculine for your feminine figure."
					+ " You find yourself incredibly embarrassed by wearing such clothing, causing you to struggle to think clearly.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.hasFetish(Fetish.FETISH_CROSS_DRESSER)) {
				return false;
			}
			
			for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if (c.getClothingType().getFemininityMaximum() < target.getFemininityValue()) {
					return true;
				}
			}
			
			return false;
		}
	},
	CLOTHING_CUM(
			80,
			"dirty clothing",
			"clothingCummedIn",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.INTELLIGENCE, -2f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			// NPCs randomly clean themselves:
			if(!target.isPlayer() && !target.isSlave()) {
				if(Math.random()<minutesPassed*0.05f) {
					for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
						c.setDirty(false);
					}
				}
			}
			
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "Some of your clothes have been covered in cum, milk or other sexual fluids."
						+ " You find yourself incredibly embarrassed to be walking around in such filthy clothing.";
			else
				return "Some of "+target.getName("the")+"'s clothes have been covered in cum, milk or other sexual fluids.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			for (AbstractClothing c : target.getClothingCurrentlyEquipped())
				if (c.isDirty())
					return true;

			return false;
		}
	},
	CLOTHING_JINXED(
			80,
			"jinxed clothing",
			"arcaneDrain",
			Colour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.STRENGTH, -2f),
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, -2f),
					new Value<Attribute, Float>(Attribute.FITNESS, -2f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			if(target.isPlayer()) {
				if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.jinxedClothingDiscovered)) {
					Main.game.getDialogueFlags().values.add(DialogueFlagValue.jinxedClothingDiscovered);
					AbstractClothing clothing = null;
					for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
						if(c.isSealed()) {
							clothing = c;
							break;
						}
					}
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
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
										+" <b style='color:"+Colour.RARITY_JINXED.toWebHexString()+";'>jinxed</b> "+clothing.getName()+"."
									+ " Maybe she'll know a way to break the seal?"
								+ "</p>"
								+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().incrementQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"");
					
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
									+ "Lilaya's warning about jinxed clothing suddenly shoots to the forefront of your mind, and you let out a groan as you realise that "+(clothing.getClothingType().isPlural()?"these ":"this ")+clothing.getName()
										+" are <b style='color:"+Colour.RARITY_JINXED.toWebHexString()+";'>jinxed</b>."
									+ " Remembering what Lilaya said, you should be able to remove the jinx if you focus some of your absorbed essences into it..."
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
		public String getDescription(GameCharacter target) {
			return "The enchantment on your jinxed clothing seems to be vampyric in nature. You can feel it draining a little of your arcane aura as it uses your strength to power itself.";
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
	},
	
	// OTHER:

	WELL_RESTED(
			80,
			"well rested",
			"wellRested",
			Colour.CLOTHING_BLUE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.STAMINA_MAXIMUM, 20f), new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, 20f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "After having a good rest, you feel full of energy.";
				} else {
					return UtilText.parse(target, "After having a good rest, [npc.name] feels full of energy.");
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	OVERWORKED(
			80,
			"overworked",
			"overworked",
			Colour.BASE_MAGENTA,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FITNESS, -5f),
					new Value<Attribute, Float>(Attribute.STAMINA_MAXIMUM, -50f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, -50f)),
			Util.newArrayListOfValues(new ListValue<String>("[style.boldBad(-0.1)] <b style='color: " + Colour.AFFECTION.toWebHexString() + ";'>Affection per hour while at work</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "As a result of working over eight hours a day, you often find yourself feeling tired and lethargic.";
				} else {
					return UtilText.parse(target, "As a result of working over eight hours a day, [npc.Name] often finds [npc.herself] feeling tired and lethargic.");
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSlave() && target.getSlaveJob()!=SlaveJob.IDLE && target.getTotalHoursWorked()>8;
		}
	},
	
	ADDICTIONS(
			80,
			"addictions",
			"addictions",
			Colour.BASE_CRIMSON,
			false,
			null,
			Util.newArrayListOfValues(new ListValue<String>("[style.boldBad(Suffer withdrawal effects)]"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			target.recalculateFluidAddictions();
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("You have the following addictions:");
				} else {
					sb.append(UtilText.parse(target, "[npc.Name] has the following addictions:"));
				}
				
				for(Entry<FluidType, Integer> entry : target.getAddictionsMap().entrySet()) {
					if(entry.getValue()>0) {
						sb.append("</br><b style='color:"+entry.getKey().getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getDescriptor(target))+" "+entry.getKey().getName(target)+"</b>: "
								+entry.getValue()+" (<span style='color:"+AddictionLevel.valueOf(entry.getValue()).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(AddictionLevel.valueOf(entry.getValue()).getName(false))+"</span>)"
								+ (Main.game.getMinutesPassed()-target.getLastTimeSatisfiedAddictionMap().get(entry.getKey())<24*60
										?" [style.colourGood(Satisfied)]: "+(23-(Main.game.getMinutesPassed()-target.getLastTimeSatisfiedAddictionMap().get(entry.getKey()))/60)
												+":"+String.format("%02d", (60-(Main.game.getMinutesPassed()-target.getLastTimeSatisfiedAddictionMap().get(entry.getKey()))%60))
										:" [style.boldArcane(Withdrawal!)]"));
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.getAddictionsMap().isEmpty();
		}
	},
	
	WITHDRAWAL_1(
			80,
			"Mild Withdrawal",
			"withdrawal1",
			Colour.CORRUPTION_STAGE_ONE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, -2f),
					new Value<Attribute, Float>(Attribute.STRENGTH, -2f),
					new Value<Attribute, Float>(Attribute.FITNESS, -2f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, -2f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("You are suffering withdrawal from:");
				} else {
					sb.append(UtilText.parse(target, "[npc.Name] is suffering withdrawal from:"));
				}
				
				for(Entry<FluidType, Long> entry : target.getLastTimeSatisfiedAddictionMap().entrySet()) {
					int minutesPassed = (int) (Main.game.getMinutesPassed()-entry.getValue());
					if(minutesPassed>(AddictionLevel.ONE_MILD.getDaysUntilAddictionCured()-1)*24*60 && minutesPassed<AddictionLevel.ONE_MILD.getDaysUntilAddictionCured()*24*60) {
						sb.append("</br><b style='color:"+entry.getKey().getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getDescriptor(target))+" "+entry.getKey().getName(target)+"</b>: "
								+(AddictionLevel.ONE_MILD.getDaysUntilAddictionCured()*24*60-minutesPassed)/60+":"+String.format("%02d", (AddictionLevel.ONE_MILD.getDaysUntilAddictionCured()*24*60-minutesPassed)%60)
								+" ("+(AddictionLevel.valueOf(target.getAddiction(entry.getKey()))==AddictionLevel.ONE_MILD
									?"[style.colourGood(Until addiction removed)]"
									:"[style.colourArcane(Until next withdrawal stage)]")+")");
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(long value : target.getLastTimeSatisfiedAddictionMap().values()) {
				if(Main.game.getMinutesPassed()-value>=(AddictionLevel.ONE_MILD.getDaysUntilAddictionCured()-1)*24*60
						&& Main.game.getMinutesPassed()-value<AddictionLevel.ONE_MILD.getDaysUntilAddictionCured()*24*60) {
					return true;
				}
			}
			return false;
		}
	},
	
	WITHDRAWAL_2(
			80,
			"Noticeable Withdrawal",
			"withdrawal2",
			Colour.CORRUPTION_STAGE_TWO,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, -5f),
					new Value<Attribute, Float>(Attribute.STRENGTH, -5f),
					new Value<Attribute, Float>(Attribute.FITNESS, -5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, -5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("You are suffering withdrawal from:");
				} else {
					sb.append(UtilText.parse(target, "[npc.Name] is suffering withdrawal from:"));
				}
				
				for(Entry<FluidType, Long> entry : target.getLastTimeSatisfiedAddictionMap().entrySet()) {
					int minutesPassed = (int) (Main.game.getMinutesPassed()-entry.getValue());
					if(minutesPassed>(AddictionLevel.TWO_NOTICEABLE.getDaysUntilAddictionCured()-1)*24*60 && minutesPassed<AddictionLevel.TWO_NOTICEABLE.getDaysUntilAddictionCured()*24*60) {
						sb.append("</br><b style='color:"+entry.getKey().getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getDescriptor(target))+" "+entry.getKey().getName(target)+"</b>: "
								+(AddictionLevel.TWO_NOTICEABLE.getDaysUntilAddictionCured()*24*60-minutesPassed)/60+":"+String.format("%02d", (AddictionLevel.TWO_NOTICEABLE.getDaysUntilAddictionCured()*24*60-minutesPassed)%60)
								+" ("+(AddictionLevel.valueOf(target.getAddiction(entry.getKey()))==AddictionLevel.TWO_NOTICEABLE
									?"[style.colourGood(Until addiction removed)]"
									:"[style.colourArcane(Until next withdrawal stage)]")+")");
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(long value : target.getLastTimeSatisfiedAddictionMap().values()) {
				if(Main.game.getMinutesPassed()-value>=(AddictionLevel.TWO_NOTICEABLE.getDaysUntilAddictionCured()-1)*24*60
						&& Main.game.getMinutesPassed()-value<AddictionLevel.TWO_NOTICEABLE.getDaysUntilAddictionCured()*24*60) {
					return true;
				}
			}
			return false;
		}
	},
	
	WITHDRAWAL_3(
			80,
			"Strong Withdrawal",
			"withdrawal3",
			Colour.CORRUPTION_STAGE_THREE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, -10f),
					new Value<Attribute, Float>(Attribute.STRENGTH, -10f),
					new Value<Attribute, Float>(Attribute.FITNESS, -10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, -10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("You are suffering withdrawal from:");
				} else {
					sb.append(UtilText.parse(target, "[npc.Name] is suffering withdrawal from:"));
				}
				
				for(Entry<FluidType, Long> entry : target.getLastTimeSatisfiedAddictionMap().entrySet()) {
					int minutesPassed = (int) (Main.game.getMinutesPassed()-entry.getValue());
					if(minutesPassed>(AddictionLevel.THREE_STRONG.getDaysUntilAddictionCured()-1)*24*60 && minutesPassed<AddictionLevel.THREE_STRONG.getDaysUntilAddictionCured()*24*60) {
						sb.append("</br><b style='color:"+entry.getKey().getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getDescriptor(target))+" "+entry.getKey().getName(target)+"</b>: "
								+(AddictionLevel.THREE_STRONG.getDaysUntilAddictionCured()*24*60-minutesPassed)/60+":"+String.format("%02d", (AddictionLevel.THREE_STRONG.getDaysUntilAddictionCured()*24*60-minutesPassed)%60)
								+" ("+(AddictionLevel.valueOf(target.getAddiction(entry.getKey()))==AddictionLevel.THREE_STRONG
									?"[style.colourGood(Until addiction removed)]"
									:"[style.colourArcane(Until next withdrawal stage)]")+")");
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(long value : target.getLastTimeSatisfiedAddictionMap().values()) {
				if(Main.game.getMinutesPassed()-value>=(AddictionLevel.THREE_STRONG.getDaysUntilAddictionCured()-1)*24*60
						&& Main.game.getMinutesPassed()-value<AddictionLevel.THREE_STRONG.getDaysUntilAddictionCured()*24*60) {
					return true;
				}
			}
			return false;
		}
	},
	
	WITHDRAWAL_4(
			80,
			"Severe Withdrawal",
			"withdrawal4",
			Colour.CORRUPTION_STAGE_FOUR,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, -25f),
					new Value<Attribute, Float>(Attribute.STRENGTH, -25f),
					new Value<Attribute, Float>(Attribute.FITNESS, -25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, -25f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("You are suffering withdrawal from:");
				} else {
					sb.append(UtilText.parse(target, "[npc.Name] is suffering withdrawal from:"));
				}
				
				for(Entry<FluidType, Long> entry : target.getLastTimeSatisfiedAddictionMap().entrySet()) {
					int minutesPassed = (int) (Main.game.getMinutesPassed()-entry.getValue());
					if(minutesPassed>(AddictionLevel.FOUR_SEVERE.getDaysUntilAddictionCured()-1)*24*60 && minutesPassed<AddictionLevel.FOUR_SEVERE.getDaysUntilAddictionCured()*24*60) {
						sb.append("</br><b style='color:"+entry.getKey().getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getDescriptor(target))+" "+entry.getKey().getName(target)+"</b>: "
								+(AddictionLevel.FOUR_SEVERE.getDaysUntilAddictionCured()*24*60-minutesPassed)/60+":"+String.format("%02d", (AddictionLevel.FOUR_SEVERE.getDaysUntilAddictionCured()*24*60-minutesPassed)%60)
								+" ("+(AddictionLevel.valueOf(target.getAddiction(entry.getKey()))==AddictionLevel.FOUR_SEVERE
									?"[style.colourGood(Until addiction removed)]"
									:"[style.colourArcane(Until next withdrawal stage)]")+")");
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(long value : target.getLastTimeSatisfiedAddictionMap().values()) {
				if(Main.game.getMinutesPassed()-value>=(AddictionLevel.FOUR_SEVERE.getDaysUntilAddictionCured()-1)*24*60
						&& Main.game.getMinutesPassed()-value<AddictionLevel.FOUR_SEVERE.getDaysUntilAddictionCured()*24*60) {
					return true;
				}
			}
			return false;
		}
	},
	
	WITHDRAWAL_5(
			80,
			"Dependence Withdrawal",
			"withdrawal5",
			Colour.CORRUPTION_STAGE_FIVE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, -50f),
					new Value<Attribute, Float>(Attribute.STRENGTH, -50f),
					new Value<Attribute, Float>(Attribute.FITNESS, -50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, -50f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				StringBuilder sb = new StringBuilder();
				
				if(target.isPlayer()) {
					sb.append("You are suffering withdrawal from:");
				} else {
					sb.append(UtilText.parse(target, "[npc.Name] is suffering withdrawal from:"));
				}
				
				for(Entry<FluidType, Long> entry : target.getLastTimeSatisfiedAddictionMap().entrySet()) {
					int minutesPassed = (int) (Main.game.getMinutesPassed()-entry.getValue());
					if(minutesPassed>(AddictionLevel.FIVE_DEPENDENCE.getDaysUntilAddictionCured()-1)*24*60 && minutesPassed<AddictionLevel.FIVE_DEPENDENCE.getDaysUntilAddictionCured()*24*60) {
						sb.append("</br><b style='color:"+entry.getKey().getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(entry.getKey().getDescriptor(target))+" "+entry.getKey().getName(target)+"</b>: "
								+(AddictionLevel.FIVE_DEPENDENCE.getDaysUntilAddictionCured()*24*60-minutesPassed)/60+":"+String.format("%02d", (AddictionLevel.FIVE_DEPENDENCE.getDaysUntilAddictionCured()*24*60-minutesPassed)%60)
								+" ("+(AddictionLevel.valueOf(target.getAddiction(entry.getKey()))==AddictionLevel.FIVE_DEPENDENCE
									?"[style.colourGood(Until addiction removed)]"
									:"[style.colourArcane(Until next withdrawal stage)]")+")");
					}
				}
				
				return sb.toString();
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			// Time without getting fluid:
			for(long value : target.getLastTimeSatisfiedAddictionMap().values()) {
				if(Main.game.getMinutesPassed()-value>=(AddictionLevel.FIVE_DEPENDENCE.getDaysUntilAddictionCured()-1)*24*60
						&& Main.game.getMinutesPassed()-value<AddictionLevel.FIVE_DEPENDENCE.getDaysUntilAddictionCured()*24*60) {
					return true;
				}
			}
			return false;
		}
	},

	PREGNANT_0(
			80,
			"risk of pregnancy",
			"pregnancy0",
			Colour.GENERIC_ARCANE,
			true,
			null,
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "After recently having unprotected sex, there's a risk that you'll get pregnant!"
					+ " Due to the fact that the arcane accelerates people's pregnancies, you'll know if you're pregnant within a matter of hours.";
			else
				return UtilText.parse(target,
						"After recently having unprotected sex, there's a risk that "+target.getName("the")+" will get pregnant!"
							+ " Due to the fact that the arcane accelerates people's pregnancies, [npc.she]'ll know if [npc.she]'s pregnant within a matter of hours.");
				
		}

		@Override
		public String extraRemovalEffects(GameCharacter target) {
			
			if (target.isPregnant()) {
				target.addStatusEffect(PREGNANT_1, 60 * (72 + Util.random.nextInt(13)));
				
				if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PREGNANCY)) {
						return "<p>"
								+ "For the last few hours, your belly has been gradually swelling."
								+ " The progress was so slow that you didn't even realise anything was happening, but as you glance down at your stomach, there's no mistaking it."
								+ " You're pregnant."
								+ " You experimentally start stroking your abdomen, making soft little gasps as the realisation of what's happening starts to sink in."
							+ "</p>"
							+ "<p>"
								+ "[pc.thought(I-I'm pregnant?"
									+ "</br>"
									+ "..."
									+ "</br>"
									+ "Oh my God! Yes! <b>I'm pregnant!</b>)]"
							+ "</p>"
							+ "<p>"
								+ "The overwhelming happiness you feel at not only discovering that you're pregnant, but also that you're showing physical signs after only a few hours, washes over you like a crashing wave of pure ecstasy."
								+ " You feel tears of joy welling up in your eyes as you lovingly cradle your swollen belly."
							+ "</p>"
							+ "<p>"
								+ "[pc.thought(This is the best feeling ever!"
										+ "</br>"
										+ "If only aunt Lily were here, I bet she'd be so proud!"
										+ "</br>"
										+ "Wait! Of course! <b>Lilaya!</b> She'll want to see this too!)]"
							+ "</p>"
							+ "<p>"
								+ "After a little while of stroking and rubbing your wonderfully-swollen abdomen, you start to calm down a little."
								+ " You decide that you should probably go and see Lilaya, so that she can help you figure out all the details of giving birth."
							+ "</p>"
							+ "<p style='text-align:center;'>"
								+ "<b style='color:"+ Colour.GENERIC_SEX.toWebHexString() + ";'>You're pregnant!</b>"
							+ "</p>";
						
					} else {
						return "<p>"
									+ "For the last few hours, your belly has been gradually swelling."
									+ " The progress was so slow that you didn't even realise anything was happening, but as you glance down at your stomach, there's no mistaking it."
									+ " You're pregnant."
									+ " You experimentally start stroking your abdomen, making soft little gasps as the realisation of what's happening starts to sink in."
								+ "</p>"
								+ "<p>"
									+ "[pc.thought(I-I'm pregnant?"
										+ "</br>"
										+ "..."
										+ "</br>"
										+ "Oh my God! <b>I'm pregnant!</b>)]"
								+ "</p>"
								+ "<p>"
									+ "The sudden shock of not only discovering that you're pregnant, but also that you're showing physical signs after only a few hours, hits you like a sledgehammer."
									+ " Despite your best efforts, you feel yourself starting to hyperventilate as you walk around in little circles, alternating between cradling your head and stomach."
								+ "</p>"
								+ "<p>"
									+ "[pc.thought(What do I do? What do I do? What do I do?"
											+ "</br>"
											+ "If only aunt Lily were here!"
											+ "</br>"
											+ "Wait! Of course! <b>Lilaya!</b> She'll know what to do!)]"
								+ "</p>"
								+ "<p>"
									+ "You start to calm down a little as the initial shock starts to wear off."
									+ " If anyone knows what to do, it'll be Lilaya."
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ "<b style='color:"+ Colour.GENERIC_SEX.toWebHexString() + ";'>You're pregnant!</b>"
								+ "</p>";
					}
					
				} else {
					return "<p>"
							+ "For the last couple of hours, your belly has been gradually swelling."
							+ " The progress was so slow that you didn't even realise anything was happening, but as you glance down at your stomach, there's no mistaking it."
							+ " You're pregnant again."
							+ " You start stroking your abdomen, making soft little gasps as the familiar feeling of being knocked up returns to you."
						+ "</p>"
						+ "<p>"
							+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_PREGNANCY)
									?"[pc.thought(Haha! Yes! I got pregnant again! This is the best feeling ever...)]"
									:"[pc.thought(Mmm... Looks like I got pregnant again...)]")
						+ "</p>"
						+ "<p>"
							+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_PREGNANCY)
								?"Knowing what you're in for, you let out a delighted laugh before carrying on your way."
								:"Knowing what you're in for, you let out a little contented sigh and start carrying on your way.")
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString()+ ";'>You're pregnant!</b>"
						+ "</p>";
				}
				
			} else{
				target.endPregnancy(false);
				return "<p>"
							+ "Enough time has passed now for you to be sure that you're in the clear."
							+ " There's no sign of any bump in your belly, and you realise that despite having unprotected sex, you managed to avoid getting pregnant."
						+ "</p>"
						+ "<p>"
							+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_PREGNANCY)
								?"[pc.thought(Damn it...)]"
								:"[pc.thought(Well, that's a relief...)]")
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You aren't pregnant!</b>"
						+ "</p>";	
			}
			
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	PREGNANT_1(
			80,
			"pregnant",
			"pregnancy1",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, 5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "From one of your recent sexual encounters, you've been impregnated!"
						+ " Due to the fact that the arcane accelerates people's pregnancies, you'll move onto the next stage in a matter of days.";
			else
				return UtilText.parse(target,
							"From one of [npc.name]'s recent sexual encounters, [npc.she]'s been impregnated!");
		}

		@Override
		public String extraRemovalEffects(GameCharacter target) {

			target.addStatusEffect(PREGNANT_2, 60 * (72 + Util.random.nextInt(13)));

			if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				return "<p>"
							+ "Even though the change has been gradual, you're suddenly hit by the realisation that your belly has swollen massively."
							+ " You can't resist rubbing your hands over the bump in your abdomen, and you wonder just how big it's going to get."
							+ " As this is your first time getting pregnant, you're not quite sure what to expect, but you're reassured as you remember that Lilaya's always there to help."
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're now heavily pregnant!</b>"
						+ "</p>";
			} else {
				return "<p>"
							+ "Even though the change has been gradual, you're suddenly hit by the familiar realisation that your belly has swollen massively."
							+ " You can't resist rubbing your hands over the bump in your abdomen, smiling fondly at the comforting feeling."
							+ " Having been through all this before, you know that you've still got a way to go before you're ready to give birth, and, giving your belly one final stroke, you continue on your way."
						+ "</p>"
							+ "<p style='text-align:center;'>" + "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're now heavily pregnant!</b>"
						+ "</p>";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	PREGNANT_2(
			80,
			"heavily pregnant",
			"pregnancy2",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, 10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "Your stomach has swollen considerably, making it clearly obvious to anyone who glances your way that you're expecting to give birth soon."
						+ " Due to the fact that the arcane accelerates people's pregnancies, you'll move onto the final stage in a matter of days.";
			else
				return UtilText.parse(target,
							"[npc.Name]'s stomach has swollen considerably, making it clearly obvious to anyone who glances [npc.her] way that [npc.she]'s expecting to give birth soon.");
		}

		@Override
		public String extraRemovalEffects(GameCharacter target) {

			target.setTimeProgressedToFinalPregnancyStage(Main.game.getMinutesPassed());

			if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				return "<p>"
							+ "By now, your stomach has completely ballooned out in front of you, and you're having to arch your back and support yourself with one hand as you walk around."
							+ (Main.game.getPlayer().getVaginaType()==VaginaType.HARPY
								?" Although you can feel the hard shells of your clutch of eggs pressing out against the inner walls of your womb, you don't find the sensation to be in any way uncomfortable."
									+ " If anything, the feeling only seems to be boosting your maternal instincts, and you often catch yourself daydreaming about laying and incubating your eggs."
								:" Some time in the last couple of hours, you felt a strange rumble in your pregnant bump, and after panicking for a little while, you realised that it was your offspring kicking about in your womb."
									+ " You keep feeling another kick every now and then, and you know that you're ready to give birth.")
						+ "</p>"
						+ "<p>"
							+ UtilText.parsePlayerThought("I really should go and see Lilaya...")
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're now ready to give birth!</b>" 
						+ "</p>";
			} else {
				return "<p>"
							+ "By now, your stomach has completely ballooned out in front of you, and you're having to arch your back and support yourself with one hand as you walk around."
							+ (Main.game.getPlayer().getVaginaType()==VaginaType.HARPY
							?" Although you can feel the hard shells of your clutch of eggs pressing out against the inner walls of your womb, you don't find the sensation to be in any way uncomfortable."
								+ " If anything, the feeling only seems to be boosting your maternal instincts, and you often catch yourself daydreaming about laying and incubating your eggs."
							:" Some time in the last couple of hours, you felt a familiar rumble in your pregnant bump, and from experience, you instantly recognised that it was your offspring kicking about in your womb."
								+ " You keep feeling another kick every now and then, and you know that you're ready to give birth.")
						+ "</p>"
						+ "<p>"
							+ UtilText.parsePlayerThought("I really should go and see Lilaya... Or maybe I'll stay like this for a little while!")
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString()+ ";'>You're now ready to give birth!</b>"
						+ "</p>";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	PREGNANT_3(
			80,
			"ready for birthing",
			"pregnancy3",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, 15f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "Your belly has inflated to a colossal size, and you find yourself having to support your back as you walk."
						+ " It might be a good idea to visit Lilaya so that she can help you to give birth.";
			else
				return UtilText.parse(target,
							"[npc.Name]'s belly has inflated to a colossal size, and [npc.she]'s finding that [npc.she] has to support [npc.her] back with one hand as [npc.she] walks.");
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(target instanceof NPC) {
				((NPC)target).setReactedToPregnancy(false);
			} else {
				if(target.isPlayer()) {
					for(NPC npc : Main.game.getAllNPCs()) {
						npc.setReactedToPlayerPregnancy(false);
					}
				}
			}
			
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
	},

	VIXENS_VIRILITY(
			80,
			"Vixen's Virility",
			"vixensVirility",
			Colour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FERTILITY, 50f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 50f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "After consuming a Vixen's Virility pill, your body's fertility and virility have been temporarily boosted.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},

	PROMISCUITY_PILL(
			80,
			"Promiscuity Pill",
			"promiscuityPill",
			Colour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FERTILITY, -100f),
					new Value<Attribute, Float>(Attribute.VIRILITY, -100f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "After consuming a Promiscuity Pill, your body's fertility and virility has been temporarily reduced."
					+ " This is a <b>preventative</b> measure, and will not alter the outcome of any unprotected sex you had before taking the pill!";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},

	RECOVERING_ORIFICE(
			80,
			"Recovering Vagina",
			"recoveringOrifice",
			Colour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.FITNESS, -2f), new Value<Attribute, Float>(Attribute.STRENGTH, -2f), new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, -5f)),
			null) {

		@Override
		public String getName(GameCharacter target) {
			int i=0;
			String s="";
			
			// Vagina:
			if (target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				s = "Recovering Vagina";
				i++;
				
			// Ass:
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				s = "Recovering Anus";
				i++;
				
			// Nipples:
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				s = "Recovering Nipples";
				i++;
				
			// Urethra:
			}
			if (target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				s = "Recovering Urethra";
				i++;
			}
			
			if(i>1)
				return "Recovering Orifices";
			else
				return s;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			
			// Vagina:
			if (target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				switch(target.getVaginaElasticity()){
					//Takes 6 hours to recover each inch of capacity:
					case ZERO_UNYIELDING:
						target.incrementVaginaStretchedCapacity(-(1/6f) * (minutesPassed/60f));
						break;
					//Takes 4 hours to recover each inch of capacity:
					case ONE_RIGID:
						target.incrementVaginaStretchedCapacity(-(1/4f) * (minutesPassed/60f));
						break;
					//Takes 2 hours to recover each inch of capacity:
					case TWO_FIRM:
						target.incrementVaginaStretchedCapacity(-(1/2f) * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case THREE_FLEXIBLE:
						target.incrementVaginaStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case FOUR_LIMBER:
						target.incrementVaginaStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 30 minutes to recover each inch of capacity:
					case FIVE_STRETCHY:
						target.incrementVaginaStretchedCapacity(-2 * (minutesPassed/60f));
						break;
					//Takes 15 minutes to recover each inch of capacity:
					case SIX_SUPPLE:
						target.incrementVaginaStretchedCapacity(-4 * (minutesPassed/60f));
						break;
					//Should have been instant after sex, this is just a backup:
					case SEVEN_ELASTIC:
						target.incrementVaginaStretchedCapacity(-100);
						break;
					default:
						break;
				}
			
				if(target.getVaginaStretchedCapacity()<target.getVaginaRawCapacityValue()) {
					target.setVaginaStretchedCapacity(target.getVaginaRawCapacityValue());
				}
			}
			
			// Ass:
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				switch(target.getAssElasticity()){
					//Takes 6 hours to recover each inch of capacity:
					case ZERO_UNYIELDING:
						target.incrementAssStretchedCapacity(-(1/6f) * (minutesPassed/60f));
						break;
					//Takes 4 hours to recover each inch of capacity:
					case ONE_RIGID:
						target.incrementAssStretchedCapacity(-(1/4f) * (minutesPassed/60f));
						break;
					//Takes 2 hours to recover each inch of capacity:
					case TWO_FIRM:
						target.incrementAssStretchedCapacity(-(1/2f) * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case THREE_FLEXIBLE:
						target.incrementAssStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case FOUR_LIMBER:
						target.incrementAssStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 30 minutes to recover each inch of capacity:
					case FIVE_STRETCHY:
						target.incrementAssStretchedCapacity(-2 * (minutesPassed/60f));
						break;
					//Takes 15 minutes to recover each inch of capacity:
					case SIX_SUPPLE:
						target.incrementAssStretchedCapacity(-4 * (minutesPassed/60f));
						break;
					//Should have been instant after sex, this is just a backup:
					case SEVEN_ELASTIC:
						target.incrementAssStretchedCapacity(-100);
						break;
					default:
						break;
				}
				
				if(target.getAssStretchedCapacity()<target.getAssRawCapacityValue())
					target.setAssStretchedCapacity(target.getAssRawCapacityValue());
			}
			
			// Nipples:
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				switch(target.getNippleElasticity()){
					//Takes 6 hours to recover each inch of capacity:
					case ZERO_UNYIELDING:
						target.incrementNippleStretchedCapacity(-(1/6f) * (minutesPassed/60f));
						break;
					//Takes 4 hours to recover each inch of capacity:
					case ONE_RIGID:
						target.incrementNippleStretchedCapacity(-(1/4f) * (minutesPassed/60f));
						break;
					//Takes 2 hours to recover each inch of capacity:
					case TWO_FIRM:
						target.incrementNippleStretchedCapacity(-(1/2f) * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case THREE_FLEXIBLE:
						target.incrementNippleStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case FOUR_LIMBER:
						target.incrementNippleStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 30 minutes to recover each inch of capacity:
					case FIVE_STRETCHY:
						target.incrementNippleStretchedCapacity(-2 * (minutesPassed/60f));
						break;
					//Takes 15 minutes to recover each inch of capacity:
					case SIX_SUPPLE:
						target.incrementNippleStretchedCapacity(-4 * (minutesPassed/60f));
						break;
					//Should have been instant after sex, this is just a backup:
					case SEVEN_ELASTIC:
						target.incrementNippleStretchedCapacity(-100);
						break;
					default:
						break;
				}
				
				if(target.getNippleStretchedCapacity()<target.getNippleRawCapacityValue())
					target.setNippleStretchedCapacity(target.getNippleRawCapacityValue());
			}
			
			// Urethra:
			if (target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				switch(target.getUrethraElasticity()){
					//Takes 6 hours to recover each inch of capacity:
					case ZERO_UNYIELDING:
						target.incrementPenisStretchedCapacity(-(1/6f) * (minutesPassed/60f));
						break;
					//Takes 4 hours to recover each inch of capacity:
					case ONE_RIGID:
						target.incrementPenisStretchedCapacity(-(1/4f) * (minutesPassed/60f));
						break;
					//Takes 2 hours to recover each inch of capacity:
					case TWO_FIRM:
						target.incrementPenisStretchedCapacity(-(1/2f) * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case THREE_FLEXIBLE:
						target.incrementPenisStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case FOUR_LIMBER:
						target.incrementPenisStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 30 minutes to recover each inch of capacity:
					case FIVE_STRETCHY:
						target.incrementPenisStretchedCapacity(-2 * (minutesPassed/60f));
						break;
					//Takes 15 minutes to recover each inch of capacity:
					case SIX_SUPPLE:
						target.incrementPenisStretchedCapacity(-4 * (minutesPassed/60f));
						break;
					//Should have been instant after sex, this is just a backup:
					case SEVEN_ELASTIC:
						target.incrementPenisStretchedCapacity(-100);
						break;
					default:
						break;
				}
				
				if(target.getPenisStretchedCapacity()<target.getPenisRawCapacityValue())
					target.setPenisStretchedCapacity(target.getPenisRawCapacityValue());
			}
			
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			
			descriptionSB = new StringBuilder("The following orifices are recovering:");
			
			// Vagina:
			if (target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				descriptionSB.append("</br><b>Vagina:</b> From "+Capacity.getCapacityFromValue(target.getVaginaStretchedCapacity()).getDescriptor()+" to "+target.getVaginaCapacity().getDescriptor()+".");
				
			// Ass:
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				descriptionSB.append("</br><b>Anus:</b> From "+Capacity.getCapacityFromValue(target.getAssStretchedCapacity()).getDescriptor()+" to "+target.getAssCapacity().getDescriptor()+".");
				
			// Nipples:
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				descriptionSB.append("</br><b>Nipples:</b> From "+Capacity.getCapacityFromValue(target.getNippleStretchedCapacity()).getDescriptor()+" to "+target.getNippleCapacity().getDescriptor()+".");
				
			// Urethra:
			}
			if (target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				descriptionSB.append("</br><b>Urethra:</b> From "+Capacity.getCapacityFromValue(target.getPenisStretchedCapacity()).getDescriptor()+" to "+target.getPenisCapacity().getDescriptor()+".");
			}
			
			return descriptionSB.toString();
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex() &&
					((target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity())
					|| (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity())
					|| (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity())
					|| (target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()));
		}
	},

	CREAMPIE_VAGINA(
			80,
			"Pussy Creampie",
			"creampie",
			Colour.CUMMED,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FITNESS, -2f),
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, -2f)),
			Util.newArrayListOfValues(
					new ListValue<String>("<b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>"))) {
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			if(target.getLowestZLayerCoverableArea(CoverableArea.VAGINA)!=null){
				if(!target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).isDirty()){
					target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).setDirty(true);
					return "<p>"
							+ "Cum leaks out of your creampied pussy, quickly </b><b style='color:"+Colour.CUMMED.toWebHexString()+";'>dirtying</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+"!"
							+ "</p>";
				}
			}
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "As you walk, you can feel slimy cum drooling out of your recently-used pussy."
						+ " You find it hard to concentrate on anything other than the memory of being creampied.</br>"
						+ "Perhaps you should take a shower...";
			else
				return target.getName("The")+"'s "+target.getVaginaName(true)+" has recently been filled with cum.";
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.clearCummedInArea(OrificeType.VAGINA);
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	CREAMPIE_ANUS(
			80,
			"Anal Creampie",
			"creampie",
			Colour.CUMMED,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FITNESS, -2f),
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, -2f)),
			Util.newArrayListOfValues(new ListValue<String>("<b style='color: "
					+ Colour.ATTRIBUTE_CORRUPTION.toWebHexString()
					+ "'>Dirties clothing</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			if(target.getLowestZLayerCoverableArea(CoverableArea.ANUS)!=null){
				if(!target.getLowestZLayerCoverableArea(CoverableArea.ANUS).isDirty()){
					target.getLowestZLayerCoverableArea(CoverableArea.ANUS).setDirty(true);
					return "<p>"
							+ "Cum leaks out of your creampied asshole, quickly </b><b style='color:"+Colour.CUMMED.toWebHexString()+";'>dirtying</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.ANUS).getName()+"!"
							+ "</p>";
				}
			}
			
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			if(target.isPlayer()) {
				return "As you walk, you can feel slimy cum drooling out of your recently-used asshole."
						+ " You find it hard to concentrate on anything other than the memory of being creampied.</br>"
						+ "Perhaps you should take a shower...";
			} else {
				return UtilText.parse(target, "[npc.Name]'s [npc.asshole+] has recently been filled with cum.");
			}
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.clearCummedInArea(OrificeType.ANUS);
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}

	},
	CREAMPIE_NIPPLES(
			80,
			"Nipple Creampie",
			"creampie",
			Colour.CUMMED,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FITNESS, -2f),
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, -2f)),
			Util.newArrayListOfValues(new ListValue<String>("<b style='color: "
					+ Colour.ATTRIBUTE_CORRUPTION.toWebHexString()
					+ "'>Dirties clothing</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			if(target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES)!=null){
				if(!target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES).isDirty()){
					target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES).setDirty(true);
					return "<p>"
							+ "Cum leaks out of your creampied nipples, quickly </b><b style='color:"+Colour.CUMMED.toWebHexString()+";'>dirtying</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+"!"
							+ "</p>";
				}
			}
			
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "As you walk, you can feel slimy cum drooling out of your recently-used nipples."
						+ " You find it hard to concentrate on anything other than the memory of being creampied.</br>"
						+ "Perhaps you should take a shower...";
			else
				return target.getName("The")+"'s "+target.getNippleName(true)+" have recently been filled with cum.";
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.clearCummedInArea(OrificeType.NIPPLE);
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	CREAMPIE_PENIS(
			80,
			"Urethral Creampie",
			"creampie",
			Colour.CUMMED,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FITNESS, -2f),
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, -2f)),
			Util.newArrayListOfValues(new ListValue<String>("<b style='color: "
					+ Colour.ATTRIBUTE_CORRUPTION.toWebHexString()
					+ "'>Dirties clothing</b>"))) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			if(target.getLowestZLayerCoverableArea(CoverableArea.PENIS)!=null){
				if(!target.getLowestZLayerCoverableArea(CoverableArea.PENIS).isDirty()){
					target.getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
					return "<p>"
							+ "Cum leaks out of your creampied urethra, quickly </b><b style='color:"+Colour.CUMMED.toWebHexString()+";'>dirtying</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"!"
							+ "</p>";
				}
			}
			
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "As you walk, you can feel slimy cum drooling out of your recently-used urethra."
						+ " You find it hard to concentrate on anything other than the memory of being creampied.</br>"
						+ "Perhaps you should take a shower...";
			else
				return target.getName("The")+"'s urethra has recently been filled with cum.";
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.clearCummedInArea(OrificeType.URETHRA);
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	FRUSTRATED_NO_ORGASM(
			80,
			"Frustrated",
			"frustrated",
			Colour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, -50f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, -10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You've recently had a sexual encounter in which you didn't manage to orgasm."
						+ " As a result, you're feeling extremely horny and frustrated...";
			} else {
				return UtilText.parse(target, "[npc.Name] recently had a sexual encounter in which [npc.she] didn't manage to orgasm."
						+ " As a result, [npc.she]'s feeling extremely horny and frustrated...");
			}
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	PENT_UP_SLAVE(
			80,
			"Pent-up",
			"frustrated",
			Colour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, -50f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] hasn't had any sexual relief for over a day now, and is feeling extremely pent-up...");
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.isPlayer() && target.isSlave() && target.getOwner().isPlayer() && ((NPC)target).getLastTimeOrgasmed()+60*24<Main.game.getMinutesPassed();
		}
	},
	
	RECOVERING_AURA(
			80,
			"Strengthened aura",
			"recoveringAura",
			Colour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, 25f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Due to a recent orgasm, your arcane aura has been temporarily strengthened."
						+ " While in this state, you will no longer receive an arcane essence if you orgasm!";
			} else {
				return UtilText.parse(target, "Due to a recent orgasm, [npc.name]'s arcane aura has been temporarily strengthened."
						+ " While [npc.she] remains in this state, you will not receive an arcane essence if [npc.she] orgasms in your presence!");
			}
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},

	EXPOSED(
			80,
			"exposed",
			"clothingExposed",
			Colour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.INTELLIGENCE, -4f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null && !target.isPlayer()) {
				return UtilText.parse(target, "[npc.Name]'s clothing doesn't cover [npc.her] private parts, and [npc.she] feels highly embarrassed to be walking around in such an exposed fashion.");
			}
			return "Your clothing doesn't cover your private parts, and you feel highly embarrassed to be walking around in such an exposed fashion.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex()
					&& !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& (target.isCoverableAreaExposed(CoverableArea.ANUS)
							|| (target.isCoverableAreaExposed(CoverableArea.PENIS) && target.getPenisType() != PenisType.NONE)
							|| (target.isCoverableAreaExposed(CoverableArea.VAGINA) && target.getVaginaType() != VaginaType.NONE))
					&& !((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaExposed(CoverableArea.NIPPLES)); 
		}
	},
	
	EXPOSED_BREASTS(
			80,
			"exposed breasts",
			"clothingExposed",
			Colour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.INTELLIGENCE, -2f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null && !target.isPlayer()) {
				return UtilText.parse(target, "[npc.Name]'s clothing doesn't cover [npc.her] breasts, and [npc.she] feels highly embarrassed to be walking around in such an exposed fashion.");
			}
			return "Your clothing doesn't cover your breasts, and you feel highly embarrassed to be walking around in such an exposed fashion.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex()
					&& !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& !(target.isCoverableAreaExposed(CoverableArea.ANUS)
							|| (target.isCoverableAreaExposed(CoverableArea.PENIS) && target.getPenisType() != PenisType.NONE)
							|| (target.isCoverableAreaExposed(CoverableArea.VAGINA) && target.getVaginaType() != VaginaType.NONE))
					&& ((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaExposed(CoverableArea.NIPPLES)); 
		}
	},
	
	EXPOSED_PLUS_BREASTS(
			80,
			"exposed",
			"clothingExposed",
			Colour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.INTELLIGENCE, -6f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null && !target.isPlayer()) {
				return UtilText.parse(target, "[npc.Name]'s breasts and private parts are naked for the world to see, and [npc.she] feels highly embarrassed to be walking around in such an exposed fashion.");
			}
			return "Your breasts and private parts are naked for the world to see, and you feel highly embarrassed to be walking around in such an exposed fashion.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex()
					&& !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& (target.isCoverableAreaExposed(CoverableArea.ANUS)
							|| (target.isCoverableAreaExposed(CoverableArea.PENIS) && target.getPenisType() != PenisType.NONE)
							|| (target.isCoverableAreaExposed(CoverableArea.VAGINA) && target.getVaginaType() != VaginaType.NONE))
					&& ((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaExposed(CoverableArea.NIPPLES)); 
		}
	},
	
	FETISH_EXHIBITIONIST(
			80,
			"exhibitionist",
			"clothingExposed",
			Colour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 8f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null && !target.isPlayer()) {
				return UtilText.parse(target, "[npc.Name]'s clothing doesn't cover [npc.her] private parts, and [npc.she] feels incredibly sexy every time [npc.she] catches someone staring at [npc.her] exposed groin.");
			}
			return "Your clothing doesn't cover your private parts, and you feel incredibly sexy every time you catch someone staring at your exposed groin.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex()
					&& (target.hasFetish(Fetish.FETISH_EXHIBITIONIST)// Exhibitionist
						&& (target.isCoverableAreaExposed(CoverableArea.ANUS)
							|| (target.isCoverableAreaExposed(CoverableArea.PENIS) && target.getPenisType() != PenisType.NONE)
							|| (target.isCoverableAreaExposed(CoverableArea.VAGINA) && target.getVaginaType() != VaginaType.NONE) 
							|| ((target.isCoverableAreaExposed(CoverableArea.PENIS) || target.isCoverableAreaExposed(CoverableArea.VAGINA)) && (target.getPenisType() == PenisType.NONE && target.getVaginaType() == VaginaType.NONE)))
						&& !((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaExposed(CoverableArea.NIPPLES)));
		}
	},
	FETISH_EXHIBITIONIST_BREASTS(
			80,
			"exhibitionist",
			"clothingExposed",
			Colour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 4f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null && !target.isPlayer()) {
				return UtilText.parse(target, "[npc.Name]'s clothing doesn't cover [npc.her] breasts, and [npc.she] feels incredibly sexy every time [npc.she] catches someone staring at [npc.her] exposed chest.");
			}
			return "Your clothing doesn't cover your breasts, and you feel incredibly sexy every time you catch someone staring at your exposed chest.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex()
					&& (target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
						&& (!(target.isCoverableAreaExposed(CoverableArea.PENIS) && target.getPenisType() != PenisType.NONE)
							&& !(target.isCoverableAreaExposed(CoverableArea.VAGINA) && target.getVaginaType() != VaginaType.NONE)
							&& !((target.isCoverableAreaExposed(CoverableArea.PENIS) || target.isCoverableAreaExposed(CoverableArea.VAGINA)) && (target.getPenisType() == PenisType.NONE && target.getVaginaType() == VaginaType.NONE)))
						&& ((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaExposed(CoverableArea.NIPPLES)));
		}
	},
	FETISH_EXHIBITIONIST_PLUS_BREASTS(
			80,
			"exhibitionist",
			"clothingExposed",
			Colour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 12f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null && !target.isPlayer()) {
				return UtilText.parse(target, "[npc.Name]'s breasts and private parts are naked for the world to see, and [npc.she] feels incredibly sexy as [npc.she] walks around with all [npc.her] goods on display.");
			}
			return "Your breasts and private parts are naked for the world to see, and you feel incredibly sexy as you walk around with all your goods on display.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex()
					&& (target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
						&& (target.isCoverableAreaExposed(CoverableArea.ANUS)
							|| (target.isCoverableAreaExposed(CoverableArea.PENIS) && target.getPenisType() != PenisType.NONE)
							|| (target.isCoverableAreaExposed(CoverableArea.VAGINA) && target.getVaginaType() != VaginaType.NONE)
							|| ((target.isCoverableAreaExposed(CoverableArea.PENIS) || target.isCoverableAreaExposed(CoverableArea.VAGINA)) && (target.getPenisType() == PenisType.NONE && target.getVaginaType() == VaginaType.NONE)))
						&& ((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaExposed(CoverableArea.NIPPLES)));
		}
	},

	FETISH_PURE_VIRGIN(
			80,
			"pure virgin",
			"virginPure",
			Colour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, 50f), new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, 25f), new Value<Attribute, Float>(Attribute.DAMAGE_PURE, 25f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You represent the perfect image of a pure, righteous being. Your noble bearing and virtuous personality mark you as a paragon of all that is angelic and good in this world.";
			} else {
				return UtilText.parse(target,
						"[npc.Name] represents the perfect image of a pure, righteous being. [npc.Her] noble bearing and virtuous personality mark [npc.herHim] as a paragon of all that is angelic and good in this world.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN) && !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN) && target.hasVagina() && target.isVaginaVirgin();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	FETISH_PURE_VIRGIN_LUSTY_MAIDEN(
			80,
			"lusty maiden",
			"virginPureLustyMaiden",
			Colour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, 50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, 25f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PURE, 25f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You're more than willing to use your ass, mouth, breasts, and even the promise of your pussy in order to please your partners, but you'll never let anyone actually penetrate your feminine sex and take your precious virginity!";
			} else {
				return UtilText.parse(target,
						"[npc.Name] is more than willing to use [npc.her] ass, mouth, breasts, and even the promise of [npc.her] pussy in order to please [npc.her] partners,"
								+ " but [npc.she]'ll never let anyone actually penetrate [npc.her] feminine sex and take [npc.her] precious virginity!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN) && target.hasVagina() && target.isVaginaVirgin();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	FETISH_BROKEN_VIRGIN(
			80,
			"broken virgin",
			"virginBroken",
			Colour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, -50f),
					new Value<Attribute, Float>(Attribute.CORRUPTION, 50f), new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, -25f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Losing your virginity has hit you hard. All you can think of is big, thick cocks breaking you in like a worthless slut, before defiling your womb with their foul cum..."
						+ " Without your virginity, all you can see yourself as is a cheap sex toy.";
			} else {
				return UtilText.parse(target,
						"Losing [npc.her] virginity has hit [npc.name] hard. All [npc.she] can think of is big, thick cocks breaking [npc.herHim] in like a worthless slut, before defiling [npc.her] womb with their foul cum..."
								+ " Without [npc.her] virginity, all [npc.she] can see [npc.herself] as is a cheap sex toy.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN) && !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN) && target.hasVagina() && !target.isVaginaVirgin();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	FETISH_BROKEN_VIRGIN_LUSTY_MAIDEN(
			80,
			"broken maiden",
			"virginBrokenLustyMaiden",
			Colour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_MANA, -50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, -50f),
					new Value<Attribute, Float>(Attribute.CORRUPTION, 50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PURE, -25f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Losing your virginity has hit you hard. All of your efforts to deflect attention away from your pussy and to your other assets was wasted, and now you can only see yourself as a cheap sex toy, to be used by anyone.";
			} else {
				return UtilText.parse(target,
						"Losing [npc.her] virginity has hit [npc.name] hard. All [npc.she] can think of is big, thick cocks breaking [npc.herHim] in like a worthless slut, before defiling [npc.her] womb with their foul cum..."
								+ " Without [npc.her] virginity, all [npc.she] can see [npc.herself] as is a cheap sex toy.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN) && target.hasVagina() && !target.isVaginaVirgin();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},

	// CLOTHING SETS:
	// (attribute bonuses are handled in the ClothingSet enum, leave all
	// attribute modifiers as null)
	SET_MAID(
			70,
			"Hard-working Maid",
			"set_maid",
			Colour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.FITNESS, 10f), new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the entire Maid's Outfit, you are filled with the energy you need in order to be a sexy hard-working maid.";
					
				} else {
					return UtilText.parse(target, "By wearing the entire Maid's Outfit, [npc.name] is filled with the energy [npc.she] needs in order to be a sexy hard-working maid.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.MAID.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_WITCH(
			70,
			"Arcane Witch",
			"set_witch",
			Colour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the complete set of witch's clothes, you feel your arcane power growing stronger.";
					
				} else {
					return UtilText.parse(target, "By wearing the complete set of witch's clothes, [npc.name]'s arcane power has grown stronger.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.WITCH.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_SCIENTIST(
			70,
			"Scientist",
			"set_scientist",
			Colour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
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
			return ClothingSet.SCIENTIST.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_MILK_MAID(
			70,
			"Milk Maid",
			"set_milk_maid",
			Colour.BASE_WHITE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.FITNESS, 10f), new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the entire Milk Maid's outfit, you're filled with the energy you need in order to perform all of your milking duties!";
				} else {
					return UtilText.parse(target, "By wearing the entire Milk Maid's outfit, [npc.name] is filled with the energy [npc.she] needs in order to perform all of [npc.her] milking duties.");
				}
				
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.MILK_MAID.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_ENFORCER(
			70,
			"enforcer's uniform",
			"set_enforcer",
			Colour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.STRENGTH, 10f), new Value<Attribute, Float>(Attribute.FITNESS, 5f), new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 15f)), null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing an Enforcer's uniform, you gain the energy and strength you need to fight crime.";
					
				} else {
					return UtilText.parse(target, "[npc.Name] is wearing an Enforcer's uniform, granting [npc.herHim] the energy and strength [npc.she] needs to fight crime.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.ENFORCER.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_RAINBOW(
			70,
			"double rainbow",
			"set_rainbow",
			Colour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "Double rainbow... What does it mean?!";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.RAINBOW.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_BDSM(
			70,
			"BDSM",
			"set_bdsm",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.FITNESS, -15f), new Value<Attribute, Float>(Attribute.STRENGTH, -15f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "You have been tied up in bondage gear, and are struggling to move.";
					
				} else {
					return UtilText.parse(target, "[npc.Name] has been tied up in bondage gear, and is struggling to move.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.BDSM.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_CATTLE(
			70,
			"Cattle",
			"set_cattle",
			Colour.BASE_TAN,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FITNESS, 5f),
					new Value<Attribute, Float>(Attribute.STRENGTH, 5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
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
			return ClothingSet.CATTLE.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_GEISHA(
			70,
			"Geisha",
			"set_geisha",
			Colour.BASE_ROSE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FITNESS, 15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "You are wearing a kitsune's ceremonial outfit, which closely resembles traditional Japanese clothing.";
					
				} else {
					return UtilText.parse(target, "[npc.Name] is wearing a kitsune's ceremonial outfit, which closely resembles traditional Japanese clothing.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.GEISHA.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_RONIN(
			70,
			"Ronin",
			"set_ronin",
			Colour.BASE_ROSE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.STRENGTH, 5f),
					new Value<Attribute, Float>(Attribute.FITNESS, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 15f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
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
			return ClothingSet.RONIN.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_JOLNIR(
			70,
			"J&oacute;lnir",
			"set_jolnir",
			Colour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, 25f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PURE, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
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
			return ClothingSet.JOLNIR.isCharacterWearingCompleteSet(target);
		}
	},
	
	
	POTION_EFFECTS(
			80,
			"potion effects",
			"potionEffects",
			Colour.GENERIC_ARCANE,
			false,
			null,
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "After drinking a potion, you are now experiencing some effects...";
			else
				return UtilText.parse(target, "After drinking a potion, [npc.name] is now experiencing some effects...");
		}
		
//		potionAttributes
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			return target.getPotionAttributes();
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			modifiersList.clear();
			
			if (getAttributeModifiers(target) != null) {
				for (Entry<Attribute, Float> e : getAttributeModifiers(target).entrySet())
					modifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b>" + " <b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
			}
					
			return modifiersList;
		}
		
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.clearPotionAttributes();
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	// Combat bonuses:
	
	COMBAT_BONUS_ANGEL(
			80,
			"angelic intuition",
			"combatBonusAngel",
			Colour.RACE_ANGEL,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ANGEL, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ANGEL, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how angels will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how angels will behave.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	COMBAT_BONUS_CAT_MORPH(
			80,
			"cat-morph intuition",
			"combatBonusCatMorph",
			Colour.RACE_CAT_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FITNESS, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_CAT_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_CAT_MORPH, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how cat-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how cat-morphs will behave.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	COMBAT_BONUS_COW_MORPH(
			80,
			"cow-morph intuition",
			"combatBonusCowMorph",
			Colour.RACE_COW_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.STRENGTH, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_COW_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_COW_MORPH, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how cow-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how cow-morphs will behave.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	COMBAT_BONUS_DEMON(
			80,
			"demonic intuition",
			"combatBonusDemon",
			Colour.RACE_DEMON,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.STRENGTH, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_DEMON, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_DEMON, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how demons will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how demons will behave.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	COMBAT_BONUS_DOG_MORPH(
			80,
			"dog-morph intuition",
			"combatBonusDogMorph",
			Colour.RACE_DOG_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.STRENGTH, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_DOG_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_DOG_MORPH, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how dog-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how dog-morphs will behave.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	COMBAT_BONUS_HARPY(
			80,
			"harpy intuition",
			"combatBonusHarpy",
			Colour.RACE_HARPY,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.STRENGTH, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_HARPY, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_HARPY, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how harpies will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how harpies will behave.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	COMBAT_BONUS_HORSE_MORPH(
			80,
			"horse-morph intuition",
			"combatBonusHorseMorph",
			Colour.RACE_HORSE_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.STRENGTH, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_HORSE_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_HORSE_MORPH, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how horse-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how horse-morphs will behave.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	COMBAT_BONUS_REINDEER_MORPH(
			80,
			"reindeer-morph intuition",
			"combatBonusReindeerMorph",
			Colour.RACE_REINDEER_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.STRENGTH, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_REINDEER_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_REINDEER_MORPH, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how reindeer-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how reindeer-morphs will behave.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	COMBAT_BONUS_HUMAN(
			80,
			"human intuition",
			"combatBonusHuman",
			Colour.RACE_HUMAN,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.INTELLIGENCE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_HUMAN, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_HUMAN, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how humans will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how humans will behave.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	COMBAT_BONUS_SQUIRREL_MORPH(
			80,
			"squirrel-morph intuition",
			"combatBonusSquirrelMorph",
			Colour.RACE_SQUIRREL_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FITNESS, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SQUIRREL_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_SQUIRREL_MORPH, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how squirrel-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how squirrel-morphs will behave.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	COMBAT_BONUS_ALLIGATOR_MORPH(
			80,
			"alligator-morph intuition",
			"combatBonusAlligatorMorph",
			Colour.RACE_ALLIGATOR_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.STRENGTH, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ALLIGATOR_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ALLIGATOR_MORPH, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how alligator-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how alligator-morphs will behave.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	COMBAT_BONUS_WOLF_MORPH(
			80,
			"wolf-morph intuition",
			"combatBonusWolfMorph",
			Colour.RACE_WOLF_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.STRENGTH, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_WOLF_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_WOLF_MORPH, 25f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how wolf-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how wolf-morphs will behave.");
			}
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
	},
	
	
	

	// COMBAT EFFECTS:
	
	COMBAT_HIDDEN(
			70,
			"hidden",
			"combatHidden",
			Colour.GENERIC_BAD,
			false,
			null,
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "You don't know what perks, status effects, spells, or special attacks your opponent has available. You require the "+Perk.OBSERVANT.getName(Main.game.getPlayer())+" perk to reveal such information.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	ZERO_MANA(
			70,
			"mentally fatigued",
			"outOfMana",
			Colour.ATTRIBUTE_INTELLIGENCE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, -100f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "You feel completely drained, but due to your indefatigable perk, you aren't ready to give up just yet!";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getMana() == 0 && target.hasPerk(Perk.INDEFATIGABLE);
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	ZERO_STAMINA(
			70,
			"fatigued",
			"outOfStamina",
			Colour.ATTRIBUTE_FITNESS,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_ATTACK, -50f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "You are extremely tired, but due to your indefatigable perk, you aren't ready to give up just yet!";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getStamina() == 0 && target.hasPerk(Perk.INDEFATIGABLE);
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},

	// From spells (still in combat):
	
	DAZED(
			10,
			"dazed",
			"negativeCombatEffect",
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_ATTACK, -25f), new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, -25f)),
			null) {
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your head is spinning and you're struggling to stay upright. You're finding it incredibly difficult to land a hit on your opponent or dodge one of their attacks.";
			else
				return UtilText.parse(target,
						target.getName("The") + "'s head is spinning and [npc.she]'s struggling to stay upright. <She>'s finding it incredibly difficult to land a hit on you or dodge one of your attacks.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	CRIPPLE(
			10,
			"crippled",
			"negativeCombatEffect",
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_ATTACK, -15f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You've been temporarily crippled, and you're struggling to do as much damage with your attacks as you're usually able to.";
			else
				return UtilText.parse(target,
						target.getName("The") + "'s been temporarily crippled, and [npc.she]'s struggling to do as much damage with [npc.her] attacks as [npc.she]'s usually able to.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	VULNERABLE(
			10,
			"vulnerable",
			"negativeCombatEffect",
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -15f)),
			null) {
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You're feeling particularly vulnerable, and aren't able to defend yourself to the best of your ability.";
			else
				return UtilText.parse(target, "[npc.Name] is feeling particularly vulnerable, and [npc.she] isn't able to defend [npc.herself] to the best of [npc.her] ability.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	WITCH_SEAL(
		10,
		"Witch's Seal",
		"combat_witch_seal",
		Colour.GENERIC_ARCANE,
		false,
		null,
		null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			if (target.isPlayer()) {
				return "The <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Witch's Seal</b> is preventing you from making a move!";
				
			} else {
				return UtilText.parse(target,
						"The <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Witch's Seal</b> is preventing [npc.name] from making a move!");
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A powerful arcane seal is holding you firmly in place, preventing you from taking any action!";
				
			} else {
				return UtilText.parse(target, "A powerful arcane seal is holding [npc.name] firmly in place, preventing [npc.herHim] from taking any action!");
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
	},
	
	WITCH_CHARM(
			10,
			"Bewitching Charm",
			"combat_witch_charm",
			Colour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_MANA, 25f)),
			null) {
			@Override
			public String applyEffect(GameCharacter target, int minutesPassed) {
				if (target.isPlayer()) {
					return "The <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Bewitching Charm</b> is making you appear irresistibly attractive!";
					
				} else {
					return UtilText.parse(target,
							"The <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Bewitching Charm</b> is making [npc.name] appear irresistibly attractive!");
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
		},

	BURN_WEAK(
			10,
			"burn",
			"negativeCombatEffect",
			Colour.DAMAGE_TYPE_FIRE,
			false,
			null,
			Util.newArrayListOfValues(new ListValue<String>("<b>5</b> <b style='color: " + Colour.DAMAGE_TYPE_FIRE.toWebHexString() + ";'>Fire damage per turn</b>"))) {
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			int damage = (int) (5 * ((100 - (target.getAttributeValue(Attribute.RESISTANCE_FIRE))) / 100f)); 
			if (damage < 1)
				damage = 1;
			target.incrementHealth(-damage);

			if (target.isPlayer()) {
				return "You take <b>" + damage + "</b> <b style='color:" + Attribute.DAMAGE_FIRE.getColour().toWebHexString() + ";'>" + DamageType.FIRE.getName() + "</b> damage!";
				
			} else {
				return "[npc.Name] takes <b>" + damage + "</b> <b style='color:" + Attribute.DAMAGE_FIRE.getColour().toWebHexString() + ";'>" + DamageType.FIRE.getName() + "</b> damage!";
			}
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Invisible arcane flames lick at your feet, and while they don't cause you any real pain, you still end up hopping around in discomfort.";
			} else {
				return UtilText.parse(target,
						"Invisible arcane flames lick at [npc.name]'s feet, and while they don't cause [npc.herHim] any real pain, [npc.she] still ends up hopping around in discomfort.");
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
	},
	
	BURN_STRONG(
			10,
			"ignited",
			"negativeCombatEffect",
			Colour.DAMAGE_TYPE_FIRE,
			false,
			null,
			Util.newArrayListOfValues(new ListValue<String>("<b>10</b> <b style='color: " + Colour.DAMAGE_TYPE_FIRE.toWebHexString() + ";'>Fire damage per turn</b>"))) {
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			int damage = (int) (10 * ((100 - (target.getAttributeValue(Attribute.RESISTANCE_FIRE))) / 100f)); 
			if (damage < 1)
				damage = 1;
			target.incrementHealth(-damage);

			if (target.isPlayer()) {
				return "You take <b>" + damage + "</b> <b style='color:" + Attribute.DAMAGE_FIRE.getColour().toWebHexString() + ";'>" + DamageType.FIRE.getName() + "</b> damage!";
				
			} else {
				return "[npc.Name] takes <b>" + damage + "</b> <b style='color:" + Attribute.DAMAGE_FIRE.getColour().toWebHexString() + ";'>" + DamageType.FIRE.getName() + "</b> damage!";
			}
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Invisible arcane flames lick at your feet, and while they don't cause you any real pain, you still end up hopping around in discomfort.";
			} else {
				return UtilText.parse(target,
						"Invisible arcane flames lick at [npc.name]'s feet, and while they don't cause [npc.herHim] any real pain, [npc.she] still ends up hopping around in discomfort.");
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
	},
	
	CHILL(
			10,
			"chill",
			"negativeCombatEffect",
			Colour.DAMAGE_TYPE_COLD,
			false,
			null,
			Util.newArrayListOfValues(new ListValue<String>("<b>2</b> <b style='color: "
					+ Colour.DAMAGE_TYPE_MANA.toWebHexString()
					+ ";'>Willpower damage</b>"),
					new ListValue<String>("<b>2</b> <b style='color: " + Colour.DAMAGE_TYPE_STAMINA.toWebHexString() + ";'>Stamina damage</b>"))) {
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			target.incrementMana(-2f);
			target.incrementStamina(-2f);

			if (target.isPlayer()) {
				return "You take <b>2</b> <b style='color:" + Attribute.DAMAGE_STAMINA.getColour().toWebHexString() + ";'>" + DamageType.STAMINA.getName() + "</b> damage and <b>2</b>"
							+ " <b style='color:" + Attribute.DAMAGE_MANA.getColour().toWebHexString() + ";'>" + DamageType.MANA.getName() + "</b> damage!";
				
			} else {
				return "[npc.Name] takes <b>2</b> <b style='color:" + Attribute.DAMAGE_STAMINA.getColour().toWebHexString() + ";'>" + DamageType.STAMINA.getName() + "</b> damage and <b>2</b>"
						+ " <b style='color:" + Attribute.DAMAGE_MANA.getColour().toWebHexString() + ";'>" + DamageType.MANA.getName() + "</b> damage!";
			}
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A fine mist of icy shards is lingering around you. The arcane cold seeps into your body, slowing your movements and dulling your mind.";
			} else {
				return UtilText.parse(target,
						"A fine mist of icy shards is lingering around [npc.name]. The arcane cold is seeping into [npc.her] body, slowing [npc.her] movements and dulling [npc.her] mind.");
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
	},
	
	POISON_WEAK(
			10,
			"poison",
			"negativeCombatEffect",
			Colour.DAMAGE_TYPE_POISON,
			false,
			null,
			Util.newArrayListOfValues(new ListValue<String>("<b>2%</b> <b style='color: "
					+ Colour.DAMAGE_TYPE_POISON.toWebHexString()
					+ ";'>Poison damage per turn</b>"))) {
		@Override
		/**
		 * 2% health damage per tick. Minimum of 1 damage.
		 */
		public String applyEffect(GameCharacter target, int minutesPassed) {
			float damage = (target.getAttributeValue(Attribute.HEALTH_MAXIMUM) * 0.02f) * ((100 - (target.getAttributeValue(Attribute.RESISTANCE_POISON))) / 100f);
			if (damage < 1)
				damage = 1;
			target.incrementHealth(-(int) damage);
			
			if (target.isPlayer()) {
				return "You take <b>" + (int)damage + "</b> <b style='color:" + Attribute.DAMAGE_POISON.getColour().toWebHexString() + ";'>" + DamageType.POISON.getName() + "</b> damage!";
				
			} else {
				return "[npc.Name] takes <b>" + (int)damage + "</b> <b style='color:" + Attribute.DAMAGE_POISON.getColour().toWebHexString() + ";'>"+ DamageType.POISON.getName() + "</b> damage!";
			}
			
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A choking haze of toxic arcane poison is lingering around you, and every time you breathe in, you can taste the sickly miasma in the back of your throat.";
			} else {
				return UtilText.parse(target,
						"A choking haze of toxic arcane poison is lingering around [npc.name], and every time [npc.she] breathes in, the sickly miasma hits the back of [npc.her] throat, causing [npc.herHim] to cough and splutter.");
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
	},
	
	// Shields:
	ARCANE_SHIELD(
			10,
			"arcane shield",
			"positiveCombatEffect",
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 50f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "A swirling vortex of arcane energy has surrounded you, granting you a considerable boost to your physical resistance." + " The energy is also helping to focus your mind, granting a boost to your hit and dodge chances.";
			else
				return UtilText.parse(target, "A swirling vortex of arcane energy has surrounded " + target.getName("the")
						+ ", granting [npc.herHim] a considerable boost to [npc.her] physical resistance." + " The energy is also helping to focus [npc.her] mind, granting a boost to [npc.her] hit and dodge chances.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	FIRE_SHIELD(
			10,
			"fire shield",
			"positiveCombatEffect",
			Colour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 50f), new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 15f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "A swirling vortex of arcane fire has surrounded you, granting you a considerable boost to your fire resistance." + " The arcane flames also help to mitigate any physical attacks directed your way.";
			else
				return UtilText.parse(target, "A swirling vortex of arcane fire has surrounded " + target.getName("the") + ", granting [npc.herHim] a considerable boost to [npc.her] fire resistance."
						+ " The arcane flames also help to mitigate any physical attacks directed [npc.her] way.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	ICE_SHIELD(
			10,
			"ice shield",
			"positiveCombatEffect",
			Colour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 50f), new Value<Attribute, Float>(Attribute.RESISTANCE_MANA, 15f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "A swirling vortex of arcane ice has surrounded you, granting you a considerable boost to your cold resistance."
						+ " The arcane ice shards radiate a soothing energy, helping to mitigate any willpower-draining attacks directed your way.";
			else
				return UtilText.parse(target, "A swirling vortex of arcane ice has surrounded " + target.getName("the") + ", granting [npc.herHim] a considerable boost to [npc.her] cold resistance."
						+ " The arcane ice shards radiate a soothing energy, helping to mitigate any willpower-draining attacks directed [npc.her] way.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	POISON_SHIELD(
			10,
			"poison shield",
			"positiveCombatEffect",
			Colour.DAMAGE_TYPE_POISON,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, 50f), new Value<Attribute, Float>(Attribute.RESISTANCE_STAMINA, 15f)),
			null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "A swirling vortex of toxic miasma has surrounded you, granting you a considerable boost to your poison resistance." + " The toxic energy helps to mitigate any stamina-draining attacks directed your way.";
			else
				return UtilText.parse(target, "A swirling vortex of toxic miasma has surrounded " + target.getName("the")
						+ ", granting [npc.herHim] a considerable boost to [npc.her] poison resistance." + " The toxic energy helps to mitigate any stamina-draining attacks directed [npc.her] way.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return false;
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	// SEX EFFECTS:
	
	CONDOM_WORN(
			80,
			"Wearing a condom",
			"condom",
			Colour.CLOTHING_PINK_LIGHT,
			false,
			null,
			null) {
		
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return -0.5f;
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			modifiersList.clear();
			
			modifiersList.add("-0.5% <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>arousal/turn</b>");
					
			return modifiersList;
		}

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "Because you're wearing a condom, sex doesn't feel quite as good...";
			else
				return "Because [npc.she]'s wearing a condom, sex doesn't feel quite as good for [npc.name]...";
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
	},
	
	ANUS_STATUS(
			96,
			"Ass status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {
		
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, OrificeType.ANUS);
		}
		
		public float getArousalPerTurnPartner(GameCharacter target) {
			return getOrificeArousalPerTurnPartner(target, OrificeType.ANUS);
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, OrificeType.ANUS);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			OrificeType type = OrificeType.ANUS;
			
			descriptionSB.append("<p style='text-align:center;margin-top:0;'>");

			GameCharacter penetrator = Sex.getPenetratingCharacterUsingOrifice(target ,type);
			
			if(Sex.getPenetrationTypeInOrifice(target, type) != null) {
				switch(Sex.getPenetrationTypeInOrifice(target, type)){
					case FINGER:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> your own [pc.asshole]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> your [pc.asshole]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc.name]'s [npc.asshole]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc.her] own [npc.asshole]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc2.name]'s [npc2.asshole]!")));
						}
						break;
						
					case PENIS:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> your own [pc.asshole]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> your [pc.asshole]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc.name]'s [npc.asshole]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc.her] own [npc.asshole]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.name]'s [npc2.asshole]!")));
						}
						break;
						
					case TAIL:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> your own [pc.asshole]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> your [pc.asshole]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc.name]'s [npc.asshole]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc.her] own [npc.asshole]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.name]'s [npc2.asshole]!")));
						}
						break;
						
					case TONGUE:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>autoanilingus</b>!"
									:"[npc.Name] is performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>anilingus</b> on you!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>anilingus</b> on [npc.name]!"
									:(target.equals(penetrator)
											?"[npc.Name] is performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>autoanilingus</b>!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>anilingus</b> on [npc2.name]!")));
						}
						break;
					default:
						descriptionSB.append("<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Penetrated.</b>");
						break;
				}
			} else {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, OrificeType.ANUS, target.isPlayer()?"Your [pc.asshole]":UtilText.parse(target, "[npc.Name]'s [npc.asshole]"), descriptionSB);
			
			if(target.isPlayer()) {
				if(penetrator!=null && !penetrator.isPlayer()) {
					return UtilText.parse(penetrator, descriptionSB.toString());
				} else {
					return descriptionSB.toString();
				}
			} else {
				return UtilText.parse(target, descriptionSB.toString());
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, OrificeType.ANUS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus());
		}
	},
	
	MOUTH_STATUS(
			99,
			"Mouth status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {

		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, OrificeType.MOUTH);
		}
		
		public float getArousalPerTurnPartner(GameCharacter target) {
			return getOrificeArousalPerTurnPartner(target, OrificeType.MOUTH);
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, OrificeType.MOUTH);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			OrificeType type = OrificeType.MOUTH;
			
			descriptionSB.append("<p style='text-align:center;margin-top:0;'>");

			GameCharacter penetrator = Sex.getPenetratingCharacterUsingOrifice(target, type);
			if(Sex.getPenetrationTypeInOrifice(target, type) != null) {
				switch(Sex.getPenetrationTypeInOrifice(target, type)){
					case FINGER:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> your own [pc.fingers]!"
									:"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.name]'s [npc.fingers]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> your [pc.fingers]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.her] own [npc.fingers]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc2.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc1.name]'s [npc1.fingers]!")));
						}
						break;
						
					case PENIS:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> your own [pc.cock]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> your [pc.cock]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> your [pc.cock]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.her] own [npc.cock]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc2.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc1.name]'s [npc1.cock]!")));
						}
						break;
						
					case TAIL:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> your own [pc.tail]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> your [pc.tail]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> your [pc.tail]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.her] own [npc.tail]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc2.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc1.name]'s [npc1.tail]!")));
						}
						break;
						
					case TONGUE:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> yourself! (Somehow... How are you seeing this?)"
									:"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.name]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.name]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.herself]! (Somehow... How are you seeing this?)"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.name]!")));
						}
						break;
					default:
						descriptionSB.append("<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Penetrated.</b>");
						break;
				}
			} else {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, OrificeType.MOUTH, target.isPlayer()?"Your mouth":UtilText.parse(target, "[npc.Name]'s mouth"), descriptionSB);
			
			if(target.isPlayer()) {
				if(penetrator!=null && !penetrator.isPlayer()) {
					return UtilText.parse(penetrator, descriptionSB.toString());
				} else {
					return descriptionSB.toString();
				}
			} else {
				return UtilText.parse(target, descriptionSB.toString());
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, OrificeType.MOUTH, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMouth());
		}
	},
	
	BREAST_STATUS(
			98,
			"Breast status",
			null,
			Colour.GENERIC_SEX,
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
			return getOrificeArousalPerTurnSelf(target, OrificeType.BREAST);
		}
		
		public float getArousalPerTurnPartner(GameCharacter target) {
			return getOrificeArousalPerTurnPartner(target, OrificeType.BREAST);
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, OrificeType.BREAST);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			OrificeType type = OrificeType.BREAST;
			
			descriptionSB.append("<p style='text-align:center;margin-top:0;'>");

			GameCharacter penetrator = Sex.getPenetratingCharacterUsingOrifice(target, type);
			if(Sex.getPenetrationTypeInOrifice(target, type) != null) {
				switch(Sex.getPenetrationTypeInOrifice(target, type)){
					case FINGER:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> your own [pc.breasts]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> your [pc.breasts]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc.name]'s [npc.breasts]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc.her] own [npc.breasts]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.name]'s [npc2.breasts]!")));
						}
						break;
						
					case PENIS:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> your own [pc.breasts]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> your [pc.breasts]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc.name]'s [npc.breasts]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc.her] own [npc.breasts]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.name]'s [npc2.breasts]!")));
						}
						break;
						
					case TAIL:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> your own [pc.breasts]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> your [pc.breasts]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc.name]'s [npc.breasts]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc.her] own [npc.breasts]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.name]'s [npc2.breasts]!")));
						}
						break;
						
					case TONGUE:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> your own [pc.breasts]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> your [pc.breasts]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.name]'s [npc.breasts]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.her] own [npc.breasts]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.name]'s [npc.breasts]!")));
						}
						break;
					default:
						descriptionSB.append("<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Penetrated.</b>");
						break;
				}
			} else {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, OrificeType.BREAST, target.isPlayer()?"Your [pc.breasts]":UtilText.parse(target, "[npc.Name]'s [npc.breasts]"), descriptionSB);
			
			if(target.isPlayer()) {
				if(penetrator!=null && !penetrator.isPlayer()) {
					return UtilText.parse(penetrator, descriptionSB.toString());
				} else {
					return descriptionSB.toString();
				}
			} else {
				return UtilText.parse(target, descriptionSB.toString());
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, OrificeType.BREAST, owner.hasBreasts()?SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreasts():SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsFlat());
		}
	},
	
	NIPPLE_STATUS(
			97,
			"Nipple status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {

		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, OrificeType.NIPPLE);
		}
		
		public float getArousalPerTurnPartner(GameCharacter target) {
			return getOrificeArousalPerTurnPartner(target, OrificeType.NIPPLE);
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, OrificeType.NIPPLE);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			OrificeType type = OrificeType.NIPPLE;
			
			descriptionSB.append("<p style='text-align:center;margin-top:0;'>");

			GameCharacter penetrator = Sex.getPenetratingCharacterUsingOrifice(target, type);
			if(Sex.getPenetrationTypeInOrifice(target, type) != null) {
				switch(Sex.getPenetrationTypeInOrifice(target, type)){
					case FINGER:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> your own [pc.nipples]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> your [pc.nipples]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc.name]'s [npc.nipples]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc.her] own [npc.nipples]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc2.name]'s [npc2.nipples]!")));
						}
						break;
						
					case PENIS:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> your own [pc.nipples]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> your [pc.nipples]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc.name]'s [npc.nipples]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc.her] own [npc.nipples]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.name]'s [npc2.nipples]!")));
						}
						break;
						
					case TAIL:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> your own [pc.nipples]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> your [pc.nipples]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc.name]'s [npc.nipples]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc.her] own [npc.nipples]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.name]'s [npc2.nipples]!")));
						}
						break;
						
					case TONGUE:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> your own [pc.nipples]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> your [pc.nipples]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.name]'s [npc.nipples]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.her] own [npc.nipples]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.name]'s [npc.nipples]!")));
						}
						break;
					default:
						descriptionSB.append("<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Penetrated.</b>");
						break;
				}
			} else {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, OrificeType.NIPPLE, target.isPlayer()?"Your [pc.nipples]":UtilText.parse(target, "[npc.Name]'s [npc.nipples]"), descriptionSB);
			
			if(target.isPlayer()) {
				if(penetrator!=null && !penetrator.isPlayer()) {
					return UtilText.parse(penetrator, descriptionSB.toString());
				} else {
					return descriptionSB.toString();
				}
			} else {
				return UtilText.parse(target, descriptionSB.toString());
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex() && target.isBreastFuckableNipplePenetration();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, OrificeType.NIPPLE, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple());
		}
	},
	
	VAGINA_STATUS(
			95,
			"Pussy status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {

		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, OrificeType.VAGINA);
		}
		
		public float getArousalPerTurnPartner(GameCharacter target) {
			return getOrificeArousalPerTurnPartner(target, OrificeType.VAGINA);
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, OrificeType.VAGINA);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			OrificeType type = OrificeType.VAGINA;
			
			descriptionSB.append("<p style='text-align:center;margin-top:0;'>");

			GameCharacter penetrator = Sex.getPenetratingCharacterUsingOrifice(target, type);
			if(Sex.getPenetrationTypeInOrifice(target, type) != null) {
				switch(Sex.getPenetrationTypeInOrifice(target, type)){
					case FINGER:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> your own [pc.pussy]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> your [pc.pussy]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc.name]'s [npc.pussy]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc.her] own [npc.pussy]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc2.name]'s [npc2.pussy]!")));
						}
						break;
						
					case PENIS:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> your own [pc.pussy]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> your [pc.pussy]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc.name]'s [npc.pussy]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc.her] own [npc.pussy]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.name]'s [npc2.pussy]!")));
						}
						break;
						
					case TAIL:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> your own [pc.pussy]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> your [pc.pussy]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc.name]'s [npc.pussy]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc.her] own [npc.pussy]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.name]'s [npc2.pussy]!")));
						}
						break;
						
					case TONGUE:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>autocunnilingus</b>!"
									:"[npc.Name] is performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>cunnilingus</b> on you!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>cunnilingus</b> on [npc.name]!"
									:(target.equals(penetrator)
											?"[npc.Name] is performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>autocunnilingus</b>!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>cunnilingus</b> on [npc2.name]!")));
						}
						break;
					default:
						descriptionSB.append("<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Penetrated.</b>");
						break;
				}
			} else {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, OrificeType.VAGINA, target.isPlayer()?"Your [pc.pussy]":UtilText.parse(target, "[npc.Name]'s [npc.pussy]"), descriptionSB);
			
			if(target.isPlayer()) {
				if(penetrator!=null && !penetrator.isPlayer()) {
					return UtilText.parse(penetrator, descriptionSB.toString());
				} else {
					return descriptionSB.toString();
				}
			} else {
				return UtilText.parse(target, descriptionSB.toString());
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInSex()) {
				return target.hasVagina();
				
			} else {
				return false;
			}
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, OrificeType.VAGINA, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina());
		}
	},
	
	THIGH_STATUS(
			95,
			"Thigh status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {

		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, OrificeType.THIGHS);
		}
		
		public float getArousalPerTurnPartner(GameCharacter target) {
			return getOrificeArousalPerTurnPartner(target, OrificeType.THIGHS);
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, OrificeType.THIGHS);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			OrificeType type = OrificeType.THIGHS;
			
			descriptionSB.append("<p style='text-align:center;margin-top:0;'>");

			GameCharacter penetrator = Sex.getPenetratingCharacterUsingOrifice(target, type);
			if(Sex.getPenetrationTypeInOrifice(target, type) != null) {
				switch(Sex.getPenetrationTypeInOrifice(target, type)){
					case FINGER:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> your own [pc.thighs]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> your [pc.thighs]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc.name]'s [npc.thighs]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc.her] own [npc.thighs]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.name]'s [npc2.thighs]!")));
						}
						break;
						
					case PENIS:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> your own [pc.thighs]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> your [pc.thighs]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc.name]'s [npc.thighs]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc.her] own [npc.thighs]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.name]'s [npc2.thighs]!")));
						}
						break;
						
					case TAIL:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> your own [pc.thighs]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> your [pc.thighs]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc.name]'s [npc.thighs]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc.her] own [npc.thighs]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.name]'s [npc2.thighs]!")));
						}
						break;
						
					case TONGUE:
						if(target.isPlayer()) {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> your own [pc.thighs]!"
									:"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> your [pc.thighs]!");
						} else {
							descriptionSB.append(penetrator.isPlayer()
									?"You are <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.name]'s [npc.thighs]!"
									:(target.equals(penetrator)
											?"[npc.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.her] own [npc.thighs]!"
											:UtilText.parse(Util.newArrayListOfValues(new ListValue<>(penetrator), new ListValue<>(target)),
													"[npc1.Name] is <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.name]'s [npc.thighs]!")));
						}
						break;
					default:
						descriptionSB.append("<b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Penetrated.</b>");
						break;
				}
			} else {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, OrificeType.THIGHS, target.isPlayer()?"Your [pc.thighs]":UtilText.parse(target, "[npc.Name]'s [npc.thighs]"), descriptionSB);
			
			if(target.isPlayer()) {
				if(penetrator!=null && !penetrator.isPlayer()) {
					return UtilText.parse(penetrator, descriptionSB.toString());
				} else {
					return descriptionSB.toString();
				}
			} else {
				return UtilText.parse(target, descriptionSB.toString());
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, OrificeType.THIGHS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaThighs());
		}
	};

	private int renderingPriority;
	private String name;
	private Colour colourShade;
	private boolean beneficial;
	private Map<Attribute, Float> attributeModifiers;

	private String SVGString;
	private String SVGStringDesaturated;

	private List<String> extraEffects;

	protected List<String> modifiersList;
	
	private static StringBuilder descriptionSB, SVGImageSB;

	private StatusEffect(int renderingPriority, String name, String pathName, Colour colourShade, boolean beneficial, Map<Attribute, Float> attributeModifiers, List<String> extraEffects) {
		this.renderingPriority = renderingPriority;
		this.name = name;
		this.beneficial = beneficial;
		this.attributeModifiers = attributeModifiers;
		this.colourShade = colourShade;

		this.extraEffects = extraEffects;

		if(pathName!=null) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/" + pathName + ".svg");
				SVGString = Util.inputStreamToString(is);
	
				SVGString = SVGString.replaceAll("#ff2a2a", colourShade.getShades()[0]);
				SVGString = SVGString.replaceAll("#ff5555", colourShade.getShades()[1]);
				SVGString = SVGString.replaceAll("#ff8080", colourShade.getShades()[2]);
				SVGString = SVGString.replaceAll("#ffaaaa", colourShade.getShades()[3]);
				SVGString = SVGString.replaceAll("#ffd5d5", colourShade.getShades()[4]);
	
				is.close();
	
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(needsDesaturated()) {
				try {
					InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/" + pathName + ".svg");
					SVGStringDesaturated = Util.inputStreamToString(is);
		
					SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ff2a2a", Colour.BASE_GREY.getShades()[0]);
					SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ff5555", Colour.BASE_GREY.getShades()[1]);
					SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ff8080", Colour.BASE_GREY.getShades()[2]);
					SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ffaaaa", Colour.BASE_GREY.getShades()[3]);
					SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ffd5d5", Colour.BASE_GREY.getShades()[4]);
		
					is.close();
		
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} else {
			SVGString = "";
		}

		modifiersList = new ArrayList<>();

		if (attributeModifiers != null) {
			for (Entry<Attribute, Float> e : attributeModifiers.entrySet())
				modifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b>" + " <b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
		}

		if (extraEffects != null)
			modifiersList.addAll(extraEffects);
	}
	
	protected boolean needsDesaturated() {
		return false;
	}

	public abstract String applyEffect(GameCharacter target, int minutesPassed);

	/**
	 * @param target
	 * @return True if this status effect should be applied to the target. False
	 *         if conditions are not met <b>or</b> this status effect is only
	 *         for timed purposes (i.e. the only time it should be applied is
	 *         with a time condition.).
	 */
	public abstract boolean isConditionsMet(GameCharacter target);
	
	public boolean renderInEffectsPanel() {
		return true;
	}
	
	public boolean isCombatEffect() {
		return false;
	}
	
	public boolean isSexEffect() {
		return false;
	}
	
	public float getArousalPerTurnSelf(GameCharacter target) {
		return 0;
	}
	
	public float getArousalPerTurnPartner(GameCharacter target) {
		return 0;
	}

	public final String applyRemoveStatusEffect(GameCharacter target) {
		reverseStatusEffectAttributeModifiers(target);
		return extraRemovalEffects(target);
	}
	
	protected String extraRemovalEffects(GameCharacter target){
		return "";
	}
	
	private void reverseStatusEffectAttributeModifiers(GameCharacter target) {
		if (getAttributeModifiers(target) != null)
			for (Entry<Attribute, Float> e : getAttributeModifiers(target).entrySet())
				target.incrementBonusAttribute(e.getKey(), -e.getValue());
	}

	public int getRenderingPriority() {
		return renderingPriority;
	}

	public String getName(GameCharacter target) {
		return name;
	}

	public abstract String getDescription(GameCharacter target);

	public List<String> getModifiersAsStringList(GameCharacter target) {
		return modifiersList;
	}

	public boolean isBeneficial() {
		return beneficial;
	}

	public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
		return attributeModifiers;
	}

	public Colour getColour() {
		return colourShade;
	}

	public List<String> getExtraEffects() {
		return extraEffects;
	}

	public String getSVGString(GameCharacter owner) {
		return SVGString;
	}
	
	public String getSVGStringDesaturated(GameCharacter owner) {
		if(needsDesaturated()) {
			return SVGStringDesaturated;
		}
		return SVGString;
	}
	
	// Helper methods for sex effects:
	
	public static float getOrificeArousalPerTurnSelf(GameCharacter target, OrificeType orifice) {
		float arousal = 0;
		
		if(Sex.getPenetrationTypeInOrifice(target, orifice) != null) {
			arousal+=orifice.getBaseArousalWhenPenetrated();
			
			if(Sex.getAreasCurrentlyStretching(target).contains(orifice)) {
				arousal += orifice.getArousalChangePenetratedStretching();
			}
			if(Sex.getAreasTooLoose(target).contains(orifice)) {
				arousal += orifice.getArousalChangePenetratedTooLoose();
			}
			if(Sex.getWetOrificeTypes(target).get(orifice).isEmpty()) {
				arousal += orifice.getArousalChangePenetratedDry();
			}
		}
		
		return arousal;
	}
	
	public float getOrificeArousalPerTurnPartner(GameCharacter target, OrificeType orifice) {
		float arousal = 0;
		
		arousal+=Sex.getPenetrationTypeInOrifice(target, orifice).getBaseArousalWhenPenetrating();
		
		if(Sex.getAreasCurrentlyStretching(target).contains(orifice)) {
			arousal += orifice.getArousalChangePenetratingStretching();
		}
		if(Sex.getAreasTooLoose(target).contains(orifice)) {
			arousal += orifice.getArousalChangePenetratingTooLoose();
		}
		if(Sex.getWetOrificeTypes(target).get(orifice).isEmpty()) {
			arousal += orifice.getArousalChangePenetratingDry();
		}
		
		return arousal;
	}
	
	public List<String> getOrificeModifiersAsStringList(GameCharacter target, OrificeType orifice) {
		modifiersList.clear();
		
		String targetName = target.isPlayer()?"your":UtilText.parse(target, "[npc.name]'s");
		GameCharacter penetrator = Sex.getPenetratingCharacterUsingOrifice(target, orifice);
		String penetratorName = penetrator.isPlayer()?"your":UtilText.parse(penetrator, "[npc.name]'s");
		
		if(Sex.getPenetrationTypeInOrifice(target, orifice) != null) {
			modifiersList.add("+"+orifice.getBaseArousalWhenPenetrated()
					+" <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+(target.isPlayer()?targetName:penetratorName)+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>Sex</b>)");
			modifiersList.add("+"+Sex.getPenetrationTypeInOrifice(target, orifice).getBaseArousalWhenPenetrating()
					+" <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+(target.isPlayer()?penetratorName:targetName)+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>Sex</b>)");
			
			if(Sex.getAreasCurrentlyStretching(target).contains(orifice)) {
				modifiersList.add((orifice.getArousalChangePenetratedStretching()>0?"+":"")+orifice.getArousalChangePenetratedStretching()
						+ " <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+(target.isPlayer()?targetName:penetratorName)+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + "'>Stretching</b>)");
				modifiersList.add((orifice.getArousalChangePenetratingStretching()>0?"+":"")+orifice.getArousalChangePenetratingStretching()
						+ " <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+(target.isPlayer()?penetratorName:targetName)+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>Tight</b>)");
			}
			if(Sex.getAreasTooLoose(target).contains(orifice)) {
				modifiersList.add((orifice.getArousalChangePenetratedTooLoose()>0?"+":"")+orifice.getArousalChangePenetratedTooLoose()
						+ " <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+(target.isPlayer()?targetName:penetratorName)+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + "'>Too loose</b>)");
				modifiersList.add((orifice.getArousalChangePenetratingTooLoose()>0?"+":"")+orifice.getArousalChangePenetratingTooLoose()
						+ "<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+(target.isPlayer()?penetratorName:targetName)+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + "'>Too loose</b>)");
			}
			if(Sex.getWetOrificeTypes(target).get(orifice).isEmpty()) {
				modifiersList.add((orifice.getArousalChangePenetratedDry()>0?"+":"")+orifice.getArousalChangePenetratedDry()
						+ " <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+(target.isPlayer()?targetName:penetratorName)+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + "'>Dry</b>)");
				modifiersList.add((orifice.getArousalChangePenetratingDry()>0?"+":"")+orifice.getArousalChangePenetratingDry()
						+ " <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+(target.isPlayer()?penetratorName:targetName)+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + "'>Dry</b>)");
			}
		}
		
		return modifiersList;
	}
	
	public void appendOrificeAdditionGenericDescriptions(GameCharacter owner, OrificeType orificeType, String orificeName, StringBuilder stringBuilderToAppendTo) {
		
		if(Sex.getAreasCurrentlyStretching(owner).contains(orificeType)) {
			stringBuilderToAppendTo.append("</br>"+orificeName+" is being <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>stretched</b>!");
			
		} else if(Sex.getAreasTooLoose(owner).contains(orificeType)) {
			stringBuilderToAppendTo.append("</br>"+orificeName+" is <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>too loose</b>!");
			
		} else {
			stringBuilderToAppendTo.append("</br><b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No stretch effect.</b>");
		}
		
		
		if(Sex.getWetOrificeTypes(owner).get(orificeType).isEmpty()) {
			stringBuilderToAppendTo.append("</br>"+orificeName+" is <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>dry</b>!");
			
		} else {
			stringBuilderToAppendTo.append("</br>"+orificeName+" has been <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>lubricated</b> by:</br>");
			int i=0;
			for(LubricationType lt : Sex.getWetOrificeTypes(owner).get(orificeType)) {
				if(i!=0) {
					if(i == Sex.getWetOrificeTypes(owner).get(orificeType).size()-1) {
						stringBuilderToAppendTo.append(", and ");
					} else {
						stringBuilderToAppendTo.append(", ");
					}
				}
				
				if(i==0)
					stringBuilderToAppendTo.append(Util.capitaliseSentence(lt.getName()));
				else
					stringBuilderToAppendTo.append(lt.getName());
				
				if(i == Sex.getWetOrificeTypes(owner).get(orificeType).size()-1) {
					stringBuilderToAppendTo.append(".");
				}
				
				i++;
			}
		}
		stringBuilderToAppendTo.append("</p>");
	}
	
	public String getOrificeSVGString(GameCharacter owner, OrificeType orifice, String baseSVG) {
		SVGImageSB = new StringBuilder();
		
		SVGImageSB.append(baseSVG);
		
		if(Sex.getPenetrationTypeInOrifice(owner, orifice) != null) {
			switch(Sex.getPenetrationTypeInOrifice(owner, orifice)){
				case FINGER:
					SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeFinger()+"</div>");
					break;
				case PENIS:
					SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypePenis()+"</div>");
					break;
				case TAIL:
					SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTail()+"</div>");
					break;
				case TONGUE:
					SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTongue()+"</div>");
					break;
				default:
					break;
			}
		}
		
		if(Sex.getAreasCurrentlyStretching(owner).contains(orifice)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationStretching()+"</div>");
		}
		
		if(Sex.getAreasTooLoose(owner).contains(orifice)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationTooLoose()+"</div>");
		}
		
		if(Sex.getWetOrificeTypes(owner).get(orifice).isEmpty()) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationDry()+"</div>");
		} else {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationWet()+"</div>");
		}
		
		return SVGImageSB.toString();
	}
}
