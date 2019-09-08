package com.lilithsthrone.game.character.effects;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.AlcoholLevel;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.ReindeerOverseer;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.combat.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingSet;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.SlaveJob;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Units.ValueType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3
 * @author Innoxia
 */
public enum StatusEffect {

	// Attribute-related status effects:
	// Strength:
	PHYSIQUE_PERK_0(
			100,
			"sissy",
			"attStrength0",
			Colour.PHYSIQUE_STAGE_ZERO,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, -15f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -15f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.ZERO_WEAK.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are incredibly weak. You struggle to do much damage with your wimpy little [pc.arms], and your fragile body is particularly vulnerable to physical damage.";
			else
				return UtilText.parse(target, "[npc.Name] is incredibly weak. [npc.She] struggles to do much damage with [npc.her] wimpy little [npc.arms], and [npc.her] fragile body is particularly vulnerable to physical damage.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.ZERO_WEAK;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	PHYSIQUE_PERK_1(
			100,
			"average",
			"attStrength1",
			Colour.PHYSIQUE_STAGE_ONE,
			true,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.ONE_AVERAGE.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You have an average level of physical fitness for your body size.";
			else
				return UtilText.parse(target, "[npc.Name] has an average level of physical fitness for [npc.her] body size.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.ONE_AVERAGE;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	PHYSIQUE_PERK_2(
			100,
			"strong",
			"attStrength2",
			Colour.PHYSIQUE_STAGE_TWO,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 10f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.TWO_STRONG.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are stronger and fitter than your body size would suggest, and are able to inflict more physical damage as a result.";
			else
				return UtilText.parse(target, "[npc.Name] is stronger and fitter than [npc.her] body size would suggest, and is able to inflict more physical damage as a result.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.TWO_STRONG;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	PHYSIQUE_PERK_3(
			100,
			"powerful",
			"attStrength3",
			Colour.PHYSIQUE_STAGE_THREE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 20f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.THREE_POWERFUL.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are considerably stronger and fitter than your body size would suggest, and are able to inflict a significant amount of physical damage as a result.";
			else
				return UtilText.parse(target, "[npc.Name] is stronger and fitter than [npc.her] body size would suggest, and is able to inflict a significant amount of physical damage as a result.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.THREE_POWERFUL;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	PHYSIQUE_PERK_4(
			100,
			"mighty",
			"attStrength4",
			Colour.PHYSIQUE_STAGE_FOUR,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 30f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.FOUR_MIGHTY.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You have an exceptional level of fitness, and there are few who could ever hope to rival your raw physical power.";
			else
				return UtilText.parse(target, "[npc.Name] has an exceptional level of fitness, and there are few who could ever hope to rival [npc.her] raw physical power.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.FOUR_MIGHTY;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	PHYSIQUE_PERK_5(
			100,
			"Herculean",
			"attStrength5",
			Colour.PHYSIQUE_STAGE_FIVE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 20f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 50f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.FIVE_HERCULEAN.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Your body is the stuff of legend; mere mortals look upon you in fear and awe!";
			else
				return UtilText.parse(owner, "[npc.NamePos] body is the stuff of legend.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.FIVE_HERCULEAN;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},

	// Intelligence:
	INTELLIGENCE_PERK_0_OLD_WORLD(
			80,
			"No Arcane Power",
			"attIntelligence0",
			Colour.INTELLIGENCE_STAGE_ZERO,
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
	},
	INTELLIGENCE_PERK_0(
			80,
			"arcane impotence",
			"attIntelligence0",
			Colour.INTELLIGENCE_STAGE_ZERO,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, -75f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, -75f)),
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.GENERIC_TERRIBLE.toWebHexString() + "'>Surrender in combat at maximum lust</b>",
					"[style.boldMana(Maximum "+Attribute.MANA_MAXIMUM.getName()+")] [style.boldBad(limited to 5)]",
					"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + "'>Vulnerable to arcane storms</b>")) {
		
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
	},
	INTELLIGENCE_PERK_1(
			100,
			"arcane potential",
			"attIntelligence1",
			Colour.INTELLIGENCE_STAGE_ONE,
			true,
			null,
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.GENERIC_TERRIBLE.toWebHexString() + "'>Surrender in combat at maximum lust</b>",
					"[style.boldMana(Maximum "+Attribute.MANA_MAXIMUM.getName()+")] [style.boldBad(limited to 5)]",
					"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + "'>Vulnerable to arcane storms</b>")) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.ONE_AVERAGE.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You are a less adept at harnessing the arcane than you were when first entering this world, but are nevertheless still far more powerful than the vast majority of the population.";
			} else {
				return UtilText.parse(target, "[npc.Name] has a small amount of ability with the arcane; equal to that of a common race who's undergone extensive training.");
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
	},
	INTELLIGENCE_PERK_2(
			100,
			"arcane proficiency",
			"attIntelligence2",
			Colour.INTELLIGENCE_STAGE_TWO,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 5f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 5f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.TWO_SMART.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Your natural arcane ability is little weaker than it was when first entering this world.";
			} else {
				return UtilText.parse(target, "[npc.Name] is proficient at harnessing the arcane, and [npc.her] spells are not only easier to cast, but also do more damage.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.TWO_SMART;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	INTELLIGENCE_PERK_3(
			100,
			"arcane prowess",
			"attIntelligence3",
			Colour.INTELLIGENCE_STAGE_THREE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 5f)),
			null) {
		
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
	},
	INTELLIGENCE_PERK_4(
			100,
			"arcane mastery",
			"attIntelligence4",
			Colour.INTELLIGENCE_STAGE_FOUR,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 15f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 10f)),
			null) {
		
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
	},
	INTELLIGENCE_PERK_5(
			100,
			"arcane brilliance",
			"attIntelligence5",
			Colour.INTELLIGENCE_STAGE_FIVE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 20f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 20f),
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 15f)),
			null) {
		
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
	},

	// Corruption:
	CORRUPTION_PERK_0(100,
			"Pure",
			"attCorruption0",
			Colour.CORRUPTION_STAGE_ZERO,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 5f)),
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
	},
	
	CORRUPTION_PERK_1(
			100,
			"Vanilla",
			"attCorruption1",
			Colour.CORRUPTION_STAGE_ONE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 5f)),
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
	},
	
	CORRUPTION_PERK_2(
			100,
			"dirty",
			"attCorruption2",
			Colour.CORRUPTION_STAGE_TWO,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 15f)),
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
	},
	
	CORRUPTION_PERK_3(
			100,
			"Lewd",
			"attCorruption3",
			Colour.CORRUPTION_STAGE_THREE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 30f),
					new Value<Attribute, Float>(Attribute.FERTILITY, 25f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 25f)),
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
	},
	
	CORRUPTION_PERK_4(
			100,
			"Lustful",
			"attCorruption4",
			Colour.CORRUPTION_STAGE_FOUR,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 40f),
					new Value<Attribute, Float>(Attribute.FERTILITY, 50f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 50f)),
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
	},
	CORRUPTION_PERK_5(
			100,
			"Corrupt",
			"attCorruption5",
			Colour.CORRUPTION_STAGE_FIVE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -25f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 50f),
					new Value<Attribute, Float>(Attribute.FERTILITY, 75f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 75f)),
			Util.newArrayListOfValues("<b style='color: "+ Colour.ATTRIBUTE_CORRUPTION.toWebHexString()+ "'>Demonic mindset</b>")) {

		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.FIVE_CORRUPT.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] completely and utterly corrupted"
						+ (owner.getSubspeciesOverride()!=null && owner.getSubspeciesOverride().getRace()==Race.DEMON
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
			null) {
		
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
	},
	AROUSAL_PERK_5(
			100,
			"imminent orgasm",
			"attArousal5",
			Colour.AROUSAL_STAGE_FIVE,
			false,
			null,
			null) {
		
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
	},
	
	
	// Lust:
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
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(modifiersList);
			modList.addAll(LustLevel.ZERO_COLD.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.ZERO_COLD.getStatusEffectDescription(Sex.isConsensual(), target);
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
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(modifiersList);
			modList.addAll(LustLevel.ONE_HORNY.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.ONE_HORNY.getStatusEffectDescription(Sex.isConsensual(), target);
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
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(modifiersList);
			modList.addAll(LustLevel.TWO_AMOROUS.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.TWO_AMOROUS.getStatusEffectDescription(Sex.isConsensual(), target);
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
			return Util.capitaliseSentence(LustLevel.THREE_LUSTFUL.getName());
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(modifiersList);
			modList.addAll(LustLevel.THREE_LUSTFUL.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.THREE_LUSTFUL.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.THREE_LUSTFUL;
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
			return Util.capitaliseSentence(LustLevel.FOUR_IMPASSIONED.getName());
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(modifiersList);
			modList.addAll(LustLevel.FOUR_IMPASSIONED.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.FOUR_IMPASSIONED.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.FOUR_IMPASSIONED;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	LUST_PERK_5(
			100,
			"passionate",
			"attLust5",
			Colour.LUST_STAGE_FOUR,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.FIVE_BURNING.getName());
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(modifiersList);
			modList.addAll(LustLevel.FIVE_BURNING.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.FIVE_BURNING.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.FIVE_BURNING;
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
			Util.newArrayListOfValues("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {

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
				return Util.newArrayListOfValues(
						"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>",
						"Time until next <b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>arcane storm</b>:",
						Main.game.getNextStormTimeAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(
						"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
			}
		}
	},
	
	WEATHER_CLOUD(100,
			"Cloudy skies",
			"weatherDayCloudy",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {

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
				return Util.newArrayListOfValues(
						"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>",
						"Time until next <b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>arcane storm</b>:",
						Main.game.getNextStormTimeAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(
						"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
			}
		}
	},
	
	WEATHER_RAIN(100,
			"Rain",
			"weatherDayRain",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {

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
				return Util.newArrayListOfValues(
						"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>",
						"Time until next <b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>arcane storm</b>:",
						Main.game.getNextStormTimeAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(
						"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
			}
		}
	},
	
	WEATHER_SNOW(100,
			"Snow",
			"weatherDaySnow",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
				return Util.newArrayListOfValues(
						"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>",
						"Time until next <b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>arcane storm</b>:",
						Main.game.getNextStormTimeAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(
						"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
			}
		}
	},
	
	WEATHER_STORM_GATHERING(100,
			"Gathering storm",
			"weatherDayStormIncoming",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {

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
				return Util.newArrayListOfValues(
						"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>",
						"Time until next <b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>arcane storm</b>:",
						Main.game.getNextStormTimeAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(
						"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
			}
		}
	},
	
	WEATHER_STORM(100,
			"Arcane storm",
			"weatherDayStorm",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -5f)),
			Util.newArrayListOfValues(
					"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
					"[style.boldExcellent(Double)] all <b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat")) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
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
							+ "Although it breaks high over Dominion, the storm isn't contained within the city, and swiftly sweeps out across the Foloi fields and into the surrounding forests and grassland wilderness."
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
					&& ((!target.isVulnerableToArcaneStorm() && !target.getLocationPlace().isStormImmune()) || !target.getGlobalLocationPlace().getPlaceType().equals(PlaceType.WORLD_MAP_DOMINION));
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
				return Util.newArrayListOfValues(
						"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
						"[style.boldExcellent(Double)] all <b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat",
						"Time until <b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>storm ends</b>:",
						Main.game.getWeatherTimeRemainingAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(
						"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
						"[style.boldExcellent(Double)] all <b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat");
			}
		}
	},
	
	WEATHER_STORM_VULNERABLE(100,
			"Arcane storm",
			"weatherDayStorm",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -100f)),
			Util.newArrayListOfValues(
					"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
					"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Overwhelming Lust</b>",
					"[style.boldExcellent(Double)] <b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat")) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
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
					&& target.isVulnerableToArcaneStorm()
					&& !target.getLocationPlace().isStormImmune()
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
				return Util.newArrayListOfValues(
						"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
						"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Overwhelming Lust</b>",
						"[style.boldExcellent(Double)] <b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat",
						"Time until <b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>storm ends</b>:",
						Main.game.getWeatherTimeRemainingAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(
						"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
						"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Overwhelming Lust</b>",
						"[style.boldExcellent(Double)] <b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat");
			}
		}
	},
	
	WEATHER_STORM_PROTECTED(100,
			"Arcane storm (protected)",
			"weatherDayStorm",
			Colour.GENERIC_GOOD,
			true,
			null,
			Util.newArrayListOfValues("<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>")) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
			return target.getLocationPlace().isStormImmune()
					&& Main.game.getCurrentWeather()==Weather.MAGIC_STORM
					&& Main.game.isInNewWorld()
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
				return Util.newArrayListOfValues(
						"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
						"Time until <b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>storm ends</b>:",
						Main.game.getWeatherTimeRemainingAsTimeString());
				
			} else {
				return Util.newArrayListOfValues(
						"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>");
			}
		}
	},
	
	// RACES:
	// HUMAN:
	PURE_HUMAN_PROLOGUE(
			1000,
			"human",
			null,
			Colour.CLOTHING_WHITE,
			true,
			null,
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
	},
	
	SUBSPECIES_BONUS(
			1000,
			"",
			null,
			Colour.CLOTHING_WHITE,
			true,
			null,
			null) {

		@Override
		public String getName(GameCharacter target) {
			if(target.isRaceConcealed()) {
				return "Concealed subspecies bonus";
			}
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride().equals(Subspecies.DEMON) && target.getRaceStage()!=RaceStage.GREATER) {
				return Subspecies.DEMON.getName(null)+" ("+target.getSubspecies().getName(target)+")";
			}
			return target.getSubspecies().getName(target);
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isRaceConcealed()) {
				return UtilText.parse(target, "Although [npc.namePos] race is concealed, they're still benefiting from the attribute modifiers...");
			}
			if(target.getSubspeciesOverride()!=null && target.getSubspeciesOverride().equals(Subspecies.DEMON) && target.getRaceStage()!=RaceStage.GREATER) {
				String subspeciesName = target.getSubspecies().getName(target);//target.getHalfDemonSubspecies().getName(target);
				return Subspecies.DEMON.getStatusEffectDescription(target)
						+UtilText.parse(target, " [style.italicsDemon([npc.NameHasFull] used [npc.her] transformative powers to appear as "+UtilText.generateSingularDeterminer(subspeciesName) + " " +subspeciesName+".)]");
			} else {
				return target.getSubspecies().getStatusEffectDescription(target);	
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInNewWorld();
		}

		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			return target.getSubspecies().getStatusEffectAttributeModifiers(target);
		}

		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return target.getSubspecies().getExtraEffects(target);
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isRaceConcealed()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getRaceUnknown();
			}
//			if(owner.getSubspeciesOverride()!=null && owner.getSubspeciesOverride().equals(Subspecies.DEMON)) {
//				return Subspecies.DEMON.getSVGString(null);
//			}
			return owner.getSubspecies().getSVGString(owner);
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
					"<b>-50%</b> <b style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>Lust damage</b> both to and from <b style='color:"+ Colour.FEMININE.toWebHexString()+ ";'>feminine opponents</b>")) {

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
					"<b>-50%</b> <b style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>Lust damage</b> both to and from <b style='color:"+ Colour.MASCULINE.toWebHexString()+ ";'>masculine opponents</b>")) {

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
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_LUST, -15f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return "Some of your clothes are too feminine for your masculine figure."
					+ " You find yourself incredibly embarrassed by wearing such clothing, causing you to struggle to think clearly.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.hasFetish(Fetish.FETISH_CROSS_DRESSER) || target.hasPerkAnywhereInTree(Perk.SPECIAL_CLOTHING_FEMININITY_INDIFFERENCE)) {
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
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_LUST, -15f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return "Some of your clothes are too masculine for your feminine figure."
					+ " You find yourself incredibly embarrassed by wearing such clothing, causing you to struggle to think clearly.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.hasFetish(Fetish.FETISH_CROSS_DRESSER) || target.hasPerkAnywhereInTree(Perk.SPECIAL_CLOTHING_MASCULINITY_INDIFFERENCE)) {
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
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -2f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "Some of your clothes have been covered in cum, milk or other sexual fluids."
						+ " You find yourself incredibly embarrassed to be walking around in such filthy clothing.";
			else
				return "Some of "+target.getName("the")+"'s clothes have been covered in cum, milk or other sexual fluids."
						+ " [npc.sheIs] feeling incredibly embarrassed to be walking around in such filthy clothing.";
		}

		@Override
		public String getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer())
				return "<b style='color:" + Colour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your smelly clothes seem to attract trouble.";
			return super.getAdditionalDescription(target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!isCumEffectPositive(target)) {
				for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
					if (c.isDirty()
							&& Collections.disjoint(
									c.getClothingType().getItemTags(c.getSlotEquippedTo()),
									Util.newArrayListOfValues(ItemTag.PLUGS_ANUS, ItemTag.SEALS_ANUS, ItemTag.PLUGS_VAGINA, ItemTag.SEALS_VAGINA, ItemTag.PLUGS_NIPPLES, ItemTag.SEALS_NIPPLES))) {
						return true;
					}
				}
			}
			return false;
		}
	},
	
	CLOTHING_CUM_MASOCHIST(
			80,
			"dirty clothing",
			"clothingCummedInMasochist",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
		public String getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer())
				return "<b style='color:" + Colour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your smelly clothes seem to attract trouble.";
			return super.getAdditionalDescription(target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
					if (c.isDirty()
							&& Collections.disjoint(
									c.getClothingType().getItemTags(c.getSlotEquippedTo()),
									Util.newArrayListOfValues(ItemTag.PLUGS_ANUS, ItemTag.SEALS_ANUS, ItemTag.PLUGS_VAGINA, ItemTag.SEALS_VAGINA, ItemTag.PLUGS_NIPPLES, ItemTag.SEALS_NIPPLES))) {
						return true;
					}
				}
			}
			return false;
		}
	},
	
	BODY_CUM(
			80,
			"dirty body",
			"dirtyBody",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -2f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			List<InventorySlot> slotsToClean = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			for(AbstractClothing clothing : new ArrayList<>(target.getClothingCurrentlyEquipped())) {
				if(target.getDirtySlots().contains(clothing.getSlotEquippedTo())) {
					InventorySlot slotEquippedTo = clothing.getSlotEquippedTo();
					List<ItemTag> tags = clothing.getClothingType().getItemTags(slotEquippedTo);
					slotsToClean.add(slotEquippedTo);
					
					boolean seals =
							tags.contains(ItemTag.SEALS_ANUS)
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
									+", <b style='color:"+Colour.CUM.toWebHexString()+";'>dirtying "+(clothing.getClothingType().isPlural()?"them":"it")+" in the process</b>.");
						}
					}
					
				} else {
					for(InventorySlot blockedSlot : clothing.getClothingType().getIncompatibleSlots(target, clothing.getSlotEquippedTo())) {
						if(target.getDirtySlots().contains(blockedSlot)) {
							slotsToClean.add(blockedSlot);
							if(!clothing.isDirty()) {
								clothing.setDirty(target, true);
								if(sb.length()>0) {
									sb.append("<br/>");
								}
								sb.append("You use your <b>"+clothing.getDisplayName(true)+"</b> to clean your "+clothing.getSlotEquippedTo().getName()
										+", <b style='color:"+Colour.CUM.toWebHexString()+";'>dirtying "+(clothing.getClothingType().isPlural()?"them":"it")+" in the process</b>.");
							}
						}
					}
				}
			}
			for(InventorySlot slotToClean : slotsToClean) {
				target.removeDirtySlot(slotToClean);
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
		public String getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer())
				return "<b style='color:" + Colour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your smelly body seems to attract trouble.";
			return super.getAdditionalDescription(target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (!isCumEffectPositive(target)) && !target.getDirtySlots().isEmpty();
		}
	},
	
	BODY_CUM_MASOCHIST(
			80,
			"dirty body",
			"dirtyBodyMasochist",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			return StatusEffect.BODY_CUM.applyEffect(target, secondsPassed);
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
		public String getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer())
				return "<b style='color:" + Colour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your smelly body seems to attract trouble.";
			return super.getAdditionalDescription(target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (isCumEffectPositive(target)) && !target.getDirtySlots().isEmpty();
		}
	},
	

	CLOTHING_ENCHANTMENT_OVER_LIMIT(
			80,
			"unstable enchantments",
			"unstable_enchantment_1",
			Colour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_CORRUPTION, 25f)),
			Util.newArrayListOfValues(
					"[style.boldMinorBad(-10%)] [style.boldHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]",
					"[style.boldMinorBad(-10%)] [style.boldMana(Maximum "+Attribute.MANA_MAXIMUM.getName()+")]")) {

		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] unable to handle the amount of attribute enchantments infused into [npc.her] weapons, clothing, and tattoos, and so [npc.is] suffering from some negative side-effects."
							+ " If [npc.she] continues equipping enchanted items, this is sure to get much worse...");
		}

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int overBy = (int) (target.getEnchantmentPointsUsedTotal()-target.getAttributeValue(Attribute.ENCHANTMENT_LIMIT));
			return Main.game.isEnchantmentCapacityEnabled() && overBy>0 && overBy<10;
		}
	},
	
	CLOTHING_ENCHANTMENT_OVER_LIMIT_2(
			80,
			"volatile enchantments",
			"unstable_enchantment_2",
			Colour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_CORRUPTION, 50f)),
			Util.newArrayListOfValues(
					"[style.boldBad(-50%)] [style.boldHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]",
					"[style.boldBad(-50%)] [style.boldMana(Maximum "+Attribute.MANA_MAXIMUM.getName()+")]")) {

		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] unable to handle the amount of attribute enchantments infused into [npc.her] weapons, clothing, and tattoos, and so [npc.is] suffering from some severe negative side-effects."
							+ " If [npc.she] continues equipping enchanted items, this is sure to get much worse...");
		}

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int overBy = (int) (target.getEnchantmentPointsUsedTotal()-target.getAttributeValue(Attribute.ENCHANTMENT_LIMIT));
			return Main.game.isEnchantmentCapacityEnabled() && overBy>=10 && overBy<20;
		}
	},
	
	CLOTHING_ENCHANTMENT_OVER_LIMIT_3(
			80,
			"shattered enchantments",
			"unstable_enchantment_3",
			Colour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_CORRUPTION, 100f)),
			Util.newArrayListOfValues(
					"[style.boldHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")] [style.boldTerrible(set to 1)]",
					"[style.boldMana(Maximum "+Attribute.MANA_MAXIMUM.getName()+")] [style.boldTerrible(set to 1)]")) {

		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"[npc.NameIsFull] unable to handle the amount of attribute enchantments infused into [npc.her] weapons, clothing, and tattoos, and so [npc.is] suffering from some extremely severe negative side-effects.");
		}

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int overBy = (int) (target.getEnchantmentPointsUsedTotal()-target.getAttributeValue(Attribute.ENCHANTMENT_LIMIT));
			return Main.game.isEnchantmentCapacityEnabled() && overBy>=20;
		}
	},
	
	CLOTHING_JINXED(
			80,
			"jinxed clothing",
			"jinxed_clothing",
			Colour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_CORRUPTION, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"The enchantment on [npc.namePos] jinxed clothing is highly corruptive in nature. [npc.She] can feel its power projecting lewd thoughts into [npc.her] mind, lowering [npc.her] inhibitions towards performing sexual acts...");
		}

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
										+" <b style='color:"+Colour.RARITY_JINXED.toWebHexString()+";'>jinxed</b> "+clothing.getName()+"."
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
									+ "Lilaya's warning about jinxed clothing suddenly shoots to the forefront of your mind, and you let out a groan as you realise that "
										+(clothing.getClothingType().isPlural()?"these ":"this ")+clothing.getName()
										+(clothing.getClothingType().isPlural()?" are":" is")
										+" <b style='color:"+Colour.RARITY_JINXED.toWebHexString()+";'>jinxed</b>."
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
			Colour.ATTRIBUTE_HEALTH,
			Colour.ATTRIBUTE_MANA,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, 20f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, 20f)),
			null) {

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

		
	},
	
	WELL_RESTED_BOOSTED(
			80,
			"very well rested",
			"wellRestedBoosted",
			Colour.ATTRIBUTE_HEALTH,
			Colour.ATTRIBUTE_MANA,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, 50f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, 50f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "Thanks to your ability of knowing how to get the most out of a good rest, you now feel extremely full of energy.";
				} else {
					return UtilText.parse(target, "After having a good rest, [npc.name] feels full of energy.");
				}
			} else {
				return "";
			}
		}

		
	},
	
	OVERWORKED(
			80,
			"overworked",
			"overworked",
			Colour.BASE_MAGENTA,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -15f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, -50f)),
			Util.newArrayListOfValues("[style.boldBad(-0.1)] <b style='color: " + Colour.AFFECTION.toWebHexString() + ";'>Affection per hour while at work</b>")) {

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
	
	COMPANIONS_LEAVING( // Utility status effect to display text of companions leaving
			80,
			"Companions Leaving",
			"overworked",
			Colour.BASE_MAGENTA,
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
	},
	
	PSYCHOACTIVE(
			80,
			"Psychoactive Trip",
			"psychoactive",
			Colour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("Open to <b style='color: " + Colour.PSYCHOACTIVE.toWebHexString() + ";'>Hypnotic Suggestion</b>")) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
					List<FluidType> list = new ArrayList<>(target.getPsychoactiveFluidsIngested());
					FluidType fluid = list.get(Util.random.nextInt(list.size()));
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

		
	},
	
	DRUNK_1(
			80,
			"Intoxicated I - Tipsy",
			"drunk1",
			Colour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -1f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
	},
	
	DRUNK_2(
			80,
			"Intoxicated II - Merry",
			"drunk2",
			Colour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed);
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
	},
	
	DRUNK_3(
			80,
			"Intoxicated III - Drunk",
			"drunk3",
			Colour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed);
		}

		@Override
		public String getDescription(GameCharacter target) {
			return (UtilText.parse(target, "After recently drinking an alcoholic liquid, [npc.nameIsFull] feeling quite drunk...<br/>"
					+ "Intoxication: "+Units.round(target.getIntoxicationPercentage(), 1)+"%"));
		}

		@Override
		public String getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer())
				return "<b style='color:" + Colour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>You're obviously drunk and some might think you're easy prey.";
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
	},
	
	DRUNK_4(
			80,
			"Intoxicated IV - Hammered",
			"drunk4",
			Colour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, -5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -15f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed);
		}

		@Override
		public String getDescription(GameCharacter target) {
			return (UtilText.parse(target, "After recently drinking an alcoholic liquid, [npc.nameIsFull] feeling absolutely hammered...<br/>"
					+ "Intoxication: "+Units.round(target.getIntoxicationPercentage(), 1)+"%"));
		}

		@Override
		public String getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer())
				return "<b style='color:" + Colour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>You're obviously drunk and some might think you're easy prey.";
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
	},
	
	DRUNK_5(
			80,
			"Intoxicated V - Wasted",
			"drunk5",
			Colour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, -10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -20f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			return DRUNK_1.applyEffect(target, secondsPassed);
		}

		@Override
		public String getDescription(GameCharacter target) {
			return (UtilText.parse(target, "After recently drinking an alcoholic liquid, [npc.nameIsFull] feeling completely wasted...<br/>"
					+ "Intoxication: "+Units.round(target.getIntoxicationPercentage(), 1)+"%"));
		}

		@Override
		public String getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer())
				return "<b style='color:" + Colour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>You're obviously drunk and some might think you're easy prey.";
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
	},
	
	ADDICTIONS(
			80,
			"addictions",
			"addictions",
			Colour.BASE_CRIMSON,
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
				extraEffects.add("<b style='color:"+addiction.getFluid().getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(addiction.getFluid().getDescriptor(target))+" "+addiction.getFluid().getName(target)+"</b>: "
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
	},
	
	WITHDRAWAL_1(
			80,
			"Mild Withdrawal",
			"withdrawal1",
			Colour.CORRUPTION_STAGE_ONE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -2f),
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -2f),
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, -2f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, -2f)),
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
						sb.append("<br/>"
								+ "<b style='color:"+addiction.getFluid().getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(addiction.getFluid().getDescriptor(target))+" "+addiction.getFluid().getName(target)+"</b>: "
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
	},
	
	WITHDRAWAL_2(
			80,
			"Noticeable Withdrawal",
			"withdrawal2",
			Colour.CORRUPTION_STAGE_TWO,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -5f),
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, -5f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, -5f)),
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
						sb.append("<br/>"
								+ "<b style='color:"+addiction.getFluid().getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(addiction.getFluid().getDescriptor(target))+" "+addiction.getFluid().getName(target)+"</b>: "
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
	},
	
	WITHDRAWAL_3(
			80,
			"Strong Withdrawal",
			"withdrawal3",
			Colour.CORRUPTION_STAGE_THREE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -10f),
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, -10f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, -10f)),
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
						sb.append("<br/>"
								+ "<b style='color:"+addiction.getFluid().getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(addiction.getFluid().getDescriptor(target))+" "+addiction.getFluid().getName(target)+"</b>: "
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
	},
	
	WITHDRAWAL_4(
			80,
			"Severe Withdrawal",
			"withdrawal4",
			Colour.CORRUPTION_STAGE_FOUR,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -25f),
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -25f),
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, -25f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, -25f)),
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
						sb.append("<br/>"
								+ "<b style='color:"+addiction.getFluid().getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(addiction.getFluid().getDescriptor(target))+" "+addiction.getFluid().getName(target)+"</b>: "
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
	},
	
	WITHDRAWAL_5(
			80,
			"Intense Withdrawal",
			"withdrawal5",
			Colour.CORRUPTION_STAGE_FIVE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -50f),
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -50f),
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, -50f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, -50f)),
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
						sb.append("<br/>"
								+ "<b style='color:"+addiction.getFluid().getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(addiction.getFluid().getDescriptor(target))+" "+addiction.getFluid().getName(target)+"</b>.");
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
	},

	MENOPAUSE(
			80,
			"Menopause",
			"menopause",
			Colour.BASE_CRIMSON,
			false,
			null,
			Util.newArrayListOfValues(
					"[style.colourBad(Total infertility)]")) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
					&& target.getAgeValue()>=52
					&& (target.getSubspecies()==Subspecies.ANGEL || target.getSubspeciesOverride()==null) // Angels and demons are immune
					&& !(target instanceof Elemental);
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
											+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" little slime core"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing in the place where your womb should be."
										+ " You can't help but let out a delighted squeal of happiness as you see your "
											+(target.getPregnantLitter().getTotalLitterCount()==1?"child":"children")+" growing inside of you, and spend the next few minutes stroking and rubbing your wonderfully-swollen abdomen in a state of absolute bliss."
										+ " Eventually, however, you decide that you should probably go and see Lilaya, so that she can help you figure out all the details of giving birth."
									:"After a little while of stroking and rubbing your wonderfully-swollen abdomen, you start to calm down a little."
										+ " You decide that you should probably go and see Lilaya, so that she can help you figure out all the details of giving birth.")
							+ "</p>"
							+ "<p style='text-align:center;'>"
								+ "<b style='color:"+ Colour.GENERIC_SEX.toWebHexString() + ";'>You're pregnant!</b>"
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
												+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" little slime core"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing in the place where your womb should be."
											+ " You can't help but let out a shocked cry as you see your "+(target.getPregnantLitter().getTotalLitterCount()==1?"child":"children")
												+" growing inside of you, and spend the next few minutes stroking and rubbing your swollen abdomen in a state of panic."
											+ " Eventually, however, you start to calm down a little, and decide that you should probably go and see Lilaya as soon as possible."
										:"You start to calm down a little as the initial shock starts to wear off."
											+ " If anyone knows what to do, it'll be Lilaya.")
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ "<b style='color:"+ Colour.GENERIC_SEX.toWebHexString() + ";'>You're pregnant!</b>"
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
										+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing in the place where your womb should be."
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
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString()+ ";'>You're pregnant!</b>"
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
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You aren't pregnant!</b>"
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
	},
	
	PREGNANT_1(
			80,
			"pregnant",
			"pregnancy1",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, 2f)),
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

			if (target.isPlayer() && !((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				return "<p>"
							+ "Even though the change has been gradual, you're suddenly hit by the realisation that your belly has swollen massively."
							+ " You can't resist rubbing your hands over the bump in your abdomen, and you wonder just how big it's going to get."
							+ " As this is your first time getting pregnant, you're not quite sure what to expect, but you're reassured as you remember that Lilaya's always there to help."
						+ "</p>"
						+ (target.getBodyMaterial()==BodyMaterial.SLIME
								?"<p>"
									+ "Clearly visible through the translucent slime which your body is made up of, you see that the "
										+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" slime core"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing inside of you have gotten a lot larger..."
								+ "</p>"
								:"")
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're now heavily pregnant!</b>"
						+ "</p>"
						+(breastGrowth
								? "<p><i>"
										+"Your breasts have swollen and grown larger as your body prepares to start lactating."
										+ " You now have [style.boldSex([pc.breastSize]"  + (target.getBreastRawSizeValue()>CupSize.AA.getMeasurement()?", "+target.getBreastSize().getCupSizeName()+"-cup":"") + " breasts)]!"
									+ "</i></p>"
								:"");
			} else {
				return "<p>"
							+ "Even though the change has been gradual, you're suddenly hit by the familiar realisation that your belly has swollen massively."
							+ " You can't resist rubbing your hands over the bump in your abdomen, smiling fondly at the comforting feeling."
							+ " Having been through all this before, you know that you've still got a way to go before you're ready to give birth."
						+ "</p>"
						+ (target.getBodyMaterial()==BodyMaterial.SLIME
							?"<p>"
								+ "Clearly visible through the translucent slime which your body is made up of, you see that the "
									+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" slime core"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing inside of you have gotten a lot larger..."
							+ "</p>"
							:"")
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're now heavily pregnant!</b>"
						+ "</p>"
						+(breastGrowth
								? "<p><i>"
										+"Your breasts have swollen and grown larger as your body prepares to start lactating."
										+ " You now have [style.boldSex([pc.breastSize]"  + (target.getBreastRawSizeValue()>CupSize.AA.getMeasurement()?", "+target.getBreastSize().getCupSizeName()+"-cup":"") + " breasts)]!"
									+ "</i></p>"
								:"");
			}
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
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, 4f)),
			Util.newArrayListOfValues("-10% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Your stomach has swollen considerably, making it clearly obvious to anyone who glances your way that you're expecting to give birth soon."
						+ (target.getBodyMaterial()==BodyMaterial.SLIME
							?" Through the [pc.skinColour] [pc.skin] that makes up your body, you can see "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" little slime core"
								+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing inside of you..."
							:" Due to the fact that the arcane accelerates people's pregnancies, you'll move onto the final stage in a matter of days.");
			} else {
				return UtilText.parse(target,
							"[npc.NamePos] stomach has swollen considerably, making it clearly obvious to anyone who glances [npc.her] way that [npc.sheIs] expecting to give birth soon."
								+ (target.getBodyMaterial()==BodyMaterial.SLIME
									?" Through the [npc.skinColour] [npc.skin] that makes up [npc.her] body, you can see "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" little slime core"
										+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+" growing inside of [npc.herHim]..."
									:""));
			}
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
			
			if (target.isPlayer() && !((PlayerCharacter) target).isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
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
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're now ready to give birth!</b>" 
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
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString()+ ";'>You're now ready to give birth!</b>"
						+ "</p>";
			}
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
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, 6f)),
			Util.newArrayListOfValues("-15% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Your belly has inflated to a colossal size, and you find yourself having to support your back as you walk."
						+ (target.getBodyMaterial()==BodyMaterial.SLIME
							?" Through the [pc.skinColour] [pc.skin] that makes up your body, you can see "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" slime core"+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")
									+", just as big as your own..."
							:" It might be a good idea to visit Lilaya so that she can help you to give birth.");
			} else {
				return UtilText.parse(target,
							"[npc.NamePos] belly has inflated to a colossal size, and [npc.sheIs] finding that [npc.she] has to support [npc.her] back with one hand as [npc.she] walks."
								+ (target.getBodyMaterial()==BodyMaterial.SLIME
									?" Through the [npc.skinColour] [npc.skin] that makes up [npc.her] body, you can see "+Util.intToString(target.getPregnantLitter().getTotalLitterCount())+" slime core"
										+(target.getPregnantLitter().getTotalLitterCount()==1?"":"s")+", just as big as [npc.her] own..."
									:""));
			}
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
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "After consuming a Vixen's Virility pill, [npc.namePos] fertility and virility have been temporarily boosted.");
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
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target,
					"After consuming a Promiscuity Pill, [npc.namePos] fertility and virility has been temporarily reduced."
							+ " This is a <b>preventative</b> measure, and will not alter the outcome of any unprotected sex [npc.she] had before taking the pill!");
		}

		
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	CUM_PRODUCTION(
			80,
			"Cum Production",
			"cumProduction",
			Colour.GENERIC_SEX,
			true,
			null,
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
	},

	CUM_FULL(
			80,
			"Full Balls",
			"cumFull",
			Colour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
	},

	MILK_PRODUCTION(
			80,
			"Milk Production",
			"milkProduction",
			Colour.GENERIC_SEX,
			true,
			null,
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
	},

	MILK_FULL(
			80,
			"Full Breasts",
			"milkFull",
			Colour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
	},

	MILK_CROTCH_PRODUCTION(
			80,
			"Crotch-boob Milk Production",
			"milkCrotchProduction",
			Colour.GENERIC_SEX,
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
		public String applyEffect(GameCharacter target, int secondsPassed) {
			target.incrementBreastCrotchStoredMilk((secondsPassed) * target.getCrotchLactationRegenerationPerSecond(true));
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getCrotchLactationRegenerationPerSecond(false) * 60;
			
			return UtilText.parse(target,
					"[npc.NamePos] [npc.crotchBoobs] are producing [npc.crotchMilk] at an individual rate of "+Units.fluid(milkRegenRate)+"/minute,"
							+ " totalling [style.colourGood("+Units.fluid(milkRegenRate * target.getBreastCrotchRows() * 2)+"/minute)] (as [npc.sheHasFull] [npc.totalCrotchBoobs] [npc.crotchBoobs])."
					+ " They have stored "+Units.fluid(target.getBreastCrotchRawStoredMilkValue())+", out of a maximum of "+Units.fluid(target.getBreastCrotchRawMilkStorageValue())+".");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasBreastsCrotch()
					&& Main.getProperties().udders>0
					&& target.getBreastCrotchRawMilkStorageValue()>0
					&& target.getBreastCrotchRawStoredMilkValue()!=target.getBreastCrotchRawMilkStorageValue();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},

	MILK_CROTCH_FULL(
			80,
			"Full Crotch-boobs",
			"milkCrotchFull",
			Colour.GENERIC_SEX,
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
		public String applyEffect(GameCharacter target, int secondsPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = target.getCrotchLactationRegenerationPerSecond(false) * 60;
		
			return UtilText.parse(target,
					"[npc.NamePos] [npc.crotchBoobs] are filled with "+Units.fluid(target.getBreastCrotchRawMilkStorageValue())+" of [npc.crotchMilk].<br/>"
							+ " They produce more [npc.crotchMilk] at an individual rate of "+Units.fluid(milkRegenRate)+"/minute,"
							+ " totalling [style.colourGood("+Units.fluid(milkRegenRate * target.getBreastCrotchRows() * 2)+"/minute)] (as [npc.sheHasFull] [npc.totalCrotchBoobs] [npc.crotchBoobs]).");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasBreastsCrotch()
					&& Main.getProperties().udders>0
					&& target.getBreastCrotchRawMilkStorageValue()>0
					&& target.getBreastCrotchRawStoredMilkValue()==target.getBreastCrotchRawMilkStorageValue();
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
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -2f), new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, -5f)),
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
					&& Main.getProperties().udders>0
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
		public String applyEffect(GameCharacter target, int secondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			if (target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				target.incrementVaginaStretchedCapacity(-target.getVaginaPlasticity().getRecoveryModifier()*secondsPassed);

				if(target.getVaginaStretchedCapacity()<target.getVaginaRawCapacityValue()) {
					target.setVaginaStretchedCapacity(target.getVaginaRawCapacityValue());
				}
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				target.incrementAssStretchedCapacity(-target.getAssPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getAssStretchedCapacity()<target.getAssRawCapacityValue()) {
					target.setAssStretchedCapacity(target.getAssRawCapacityValue());
				}
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				target.incrementNippleStretchedCapacity(-target.getNipplePlasticity().getRecoveryModifier()*secondsPassed);

				if(target.getNippleStretchedCapacity()<target.getNippleRawCapacityValue()) {
					target.setNippleStretchedCapacity(target.getNippleRawCapacityValue());
				}
			}
			if (target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				target.incrementNippleCrotchStretchedCapacity(-target.getNippleCrotchPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getNippleCrotchStretchedCapacity()<target.getNippleCrotchRawCapacityValue()) {
					target.setNippleCrotchStretchedCapacity(target.getNippleCrotchRawCapacityValue());
				}
			}
			if (target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				target.incrementPenisStretchedCapacity(-target.getUrethraPlasticity().getRecoveryModifier()*secondsPassed);
				
				if(target.getPenisStretchedCapacity()<target.getPenisRawCapacityValue()) {
					target.setPenisStretchedCapacity(target.getPenisRawCapacityValue());
				}
			}
			if (target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
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
			
			String recoveringText = "recovering";
			String from1 = "From";
			String from2 = "to";
			
			if(Main.game.isInSex()) {
				recoveringText = "stretched";
				from1 = "To";
				from2 = "from";
			}
			
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				recoveringEffects.add("[style.boldVagina(Vagina recovering:)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getVaginaStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getVaginaRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getVaginaPlasticity().getRecoveryModifier())+")]");
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				recoveringEffects.add("[style.boldAsshole(Asshole "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getAssStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getAssRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getAssPlasticity().getRecoveryModifier())+")]");
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				recoveringEffects.add("[style.boldNipples(Nipples "+recoveringText+":)]");
				recoveringEffects.add(from1+" [style.boldBad("+Units.size(target.getNippleStretchedCapacity())+")] "+from2+" [style.boldGood("+Units.size(target.getNippleRawCapacityValue())+")]");
				recoveringEffects.add("[style.boldPlasticity("+getRecoveryText(target.getNipplePlasticity().getRecoveryModifier())+")]");
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
			
			descriptionSB = new StringBuilder("After being forced to accommodate");
			
			List<String> orificesRecovering = new ArrayList<>();
			boolean plural = false;
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				orificesRecovering.add("[style.boldPink(vagina)]");
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				orificesRecovering.add("[style.boldPinkDeep(asshole)]");
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				orificesRecovering.add("[style.boldPinkLight(nipples)]");
				plural = true;
			}
			if (target.hasBreastsCrotch() && target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				orificesRecovering.add("[style.boldPurple(crotch nipples)]");
				plural = true;
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				orificesRecovering.add("[style.boldViolet(penile urethra)]");
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				orificesRecovering.add("[style.boldPurpleLight(vaginal urethra)]");
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
					|| (target.hasBreastsCrotch()
							&& Main.getProperties().udders>0
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
	},
	
	
	
	CREAMPIE_VAGINA(
			80,
			"Pussy Creampie",
			"creampie",
			Colour.CUM,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		
		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.VAGINA.getCharactersCumLossPerSecond(target)*secondsPassed;
			
			StringBuilder sb = new StringBuilder();

			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.VAGINA);
			boolean dirtyArea = clothingBlocking==null;
			
			if(clothingBlocking!=null && !clothingBlocking.getItemTags().contains(ItemTag.PLUGS_VAGINA) && !clothingBlocking.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					sb.append("<p>"
								+ "The cum from your creampied pussy quickly </b><b style='color:"+Colour.CUM.toWebHexString()+";'>dirties</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+"!"
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
	},
	
	CREAMPIE_VAGINA_URETHRA(
			80,
			"Vaginal Urethra Creampie",
			"creampie",
			Colour.CUM,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		
		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.URETHRA_VAGINA.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			StringBuilder sb = new StringBuilder();

			AbstractClothing clothingBlocking = target.getLowestZLayerCoverableArea(CoverableArea.VAGINA);
			boolean dirtyArea = clothingBlocking==null;
			
			if(clothingBlocking!=null && !clothingBlocking.getItemTags().contains(ItemTag.SEALS_VAGINA)) {
				if(!clothingBlocking.isDirty()) {
					clothingBlocking.setDirty(target, true);
					sb.append("<p>"
								+ "Cum leaks out of your pussy's creampied urethra, quickly </b><b style='color:"+Colour.CUM.toWebHexString()+";'>dirtying</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+"!"
							+ "</p>");
				}
				dirtyArea = true;
			}
			
			if(dirtyArea) {
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
	},
	
	CREAMPIE_PENIS_URETHRA(
			80,
			"Penis Urethra Creampie",
			"creampie",
			Colour.CUM,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		
		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.URETHRA_PENIS.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			StringBuilder sb = new StringBuilder();
			
			if(target.getLowestZLayerCoverableArea(CoverableArea.PENIS)!=null){
				if(!target.getLowestZLayerCoverableArea(CoverableArea.PENIS).isDirty()) {
					target.getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(target, true);
					sb.append("<p>"
								+ "Cum leaks out of your cock's creampied urethra, quickly </b><b style='color:"+Colour.CUM.toWebHexString()+";'>dirtying</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"!"
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
	},
	
	CREAMPIE_ANUS(
			80,
			"Anal Creampie",
			"creampie",
			Colour.CUM,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		
		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
								+ "The cum from your creampied asshole quickly </b><b style='color:"+Colour.CUM.toWebHexString()+";'>dirties</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.ANUS).getName()+"!"
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
	},
	
	CREAMPIE_NIPPLES(
			80,
			"Nipple Creampie",
			"creampie",
			Colour.CUM,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		
		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
								+ "The cum from your creampied nipples quickly </b><b style='color:"+Colour.CUM.toWebHexString()+";'>dirties</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+"!"
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
	},

	CREAMPIE_NIPPLES_CROTCH(
			80,
			"Nipple Creampie",
			"creampie",
			Colour.CUM,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {

		@Override
		public String getName(GameCharacter owner) {
			if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
				return "Udder-nipple creampie";
			} else {
				return "Crotch-nipple creampie";
			}
		}
		
		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			if(Main.game.isInSex()) {
				return "";
			}
			float cumLost = SexAreaOrifice.NIPPLE_CROTCH.getCharactersCumLossPerSecond(target) * secondsPassed;
			
			StringBuilder sb = new StringBuilder();
			
			if(target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH)!=null){
				if(!target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH).isDirty()) {
					target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH).setDirty(target, true);
					sb.append("<p>"
								+ "The cum from your creampied nipples quickly </b><b style='color:"+Colour.CUM.toWebHexString()+";'>dirties</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES_CROTCH).getName()+"!"
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
	},
	
	CREAMPIE_MOUTH(
			80,
			"Cummy Meal",
			"creampie",
			Colour.CUM,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return target.isOnlyCumInArea(SexAreaOrifice.MOUTH)
					?"Cummy meal"
					:"Yummy meal";
		}
		
		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f));
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
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
	},
	
	
	CUM_INFLATION_1(
			80,
			"swollen belly",
			"cumInflation1",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -2f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "After being filled with a considerable amount of cum, your belly is now a little swollen."
						+ " Your extra weight makes it a little more difficult to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a considerable amount of cum, [npc.namePos] belly is now a little swollen.");
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
	},
	
	CUM_INFLATION_2(
			80,
			"inflated belly",
			"cumInflation2",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "After being filled with a huge amount of cum, your belly is now noticeably inflated."
						+ " The considerable amount of extra weight in your stomach makes it more difficult to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a huge amount of cum, [npc.namePos] belly is now noticeably inflated."
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
	},
	
	CUM_INFLATION_3(
			80,
			"over-inflated belly",
			"cumInflation3",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "After being filled with a colossal amount of cum, your belly is now massively over-inflated."
						+ " The huge amount of extra weight in your stomach is making it extremely difficult for you to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a colossal amount of cum, [npc.namePos] belly is now massively over-inflated."
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
	},
	
	
	BREAST_CUM_INFLATION_1(
			80,
			"swollen breasts",
			"cumInflationBreasts1",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -2f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "After being filled with a considerable amount of cum, your [pc.breasts] are now a little swollen."
						+ " Your extra weight makes it a little more difficult to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a considerable amount of cum, [npc.namePos] [npc.breasts] are now a little swollen.");
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
	},
	
	BREAST_CUM_INFLATION_2(
			80,
			"inflated breasts",
			"cumInflationBreasts2",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "After being filled with a huge amount of cum, your [pc.breasts] are now noticeably inflated."
						+ " The considerable amount of extra weight in your top-half is making it more difficult to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a huge amount of cum, [npc.namePos] [npc.breasts] are now noticeably inflated."
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
	},
	
	BREAST_CUM_INFLATION_3(
			80,
			"over-inflated breasts",
			"cumInflationBreasts3",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "After being filled with a colossal amount of cum, your [pc.breasts] are now massively over-inflated."
						+ " The huge amount of extra weight in your top-half is making it extremely difficult for you to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a colossal amount of cum, [npc.namePos] [npc.breasts] are now massively over-inflated."
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
	},
	
	
	FRUSTRATED_NO_ORGASM(
			80,
			"Frustrated",
			"frustrated",
			Colour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -15f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You've recently had a sexual encounter in which you didn't manage to cum."
						+ " As a result, you're feeling extremely horny and frustrated...";
			} else {
				return UtilText.parse(target, "[npc.Name] recently had a sexual encounter in which [npc.she] didn't manage to cum."
						+ " As a result, [npc.sheIs] feeling extremely horny and frustrated...");
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
	},
	
	PENT_UP_SLAVE(
			80,
			"Pent-up",
			"frustrated",
			Colour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -15f)),
			null) {

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
			return !target.isPlayer() && target.isSlave() && (target.getOwner()!=null && target.getOwner().isPlayer()) && ((NPC)target).getLastTimeOrgasmed()+60*24<Main.game.getMinutesPassed();
		}
	},
	
	RECOVERING_AURA(
			80,
			"Strengthened aura",
			"recoveringAura",
			Colour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 5f)),
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
	},

	EXPOSED(
			80,
			"exposed",
			"exposed",
			Colour.BASE_PINK_LIGHT,
			Colour.GENERIC_BAD,
			Colour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 10f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, -5f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] highly embarrassed to be walking around in such an exposed fashion.");
		}
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer())
				return "<b style='color:" + Colour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your exposed body parts attract all kind of lewd gazes.";
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& target.getLegConfiguration()==LegConfiguration.BIPEDAL
					&& isExposedParts(target, false, true);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	},

	EXPOSED_ANIMAL(
			80,
			"exposed (feral parts)",
			"exposedFeral",
			Colour.BASE_PINK_LIGHT,
			Colour.BASE_TAN,
			Colour.BASE_TAN,
			false,
			Util.newHashMapOfValues(),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", but as [npc.her] lower body is feral in nature, [npc.she] [npc.verb(feel)] as though it's natural to be so exposed.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& target.getLegConfiguration()!=LegConfiguration.BIPEDAL
					&& !((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaVisible(CoverableArea.NIPPLES))
					&& ((target.hasBreastsCrotch()
							&& Main.getProperties().udders>0
							&& target.isCoverableAreaVisible(CoverableArea.NIPPLES_CROTCH))
						|| target.isCoverableAreaVisible(CoverableArea.ANUS)
						|| (target.isCoverableAreaVisible(CoverableArea.PENIS) && target.hasPenis())
						|| (target.isCoverableAreaVisible(CoverableArea.VAGINA) && target.hasVagina()));
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	},
	
	EXPOSED_BREASTS(
			80,
			"exposed breasts",
			"exposed",
			Colour.BASE_PINK_LIGHT,
			Colour.GENERIC_BAD,
			Colour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 5f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, -2f)),
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
		public String getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer())
				return "<b style='color:" + Colour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your exposed body parts attract all kind of lewd gazes.";
			return super.getAdditionalDescription(target);
		}
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& (target.getLegConfiguration()==LegConfiguration.BIPEDAL || ((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaVisible(CoverableArea.NIPPLES)))
					&& isExposedParts(target, true, false);
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	},
	
	EXPOSED_PLUS_BREASTS(
			80,
			"exposed",
			"exposed",
			Colour.BASE_PINK_LIGHT,
			Colour.GENERIC_BAD,
			Colour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 20f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, -10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] highly embarrassed to be walking around in such an exposed fashion.");
		}
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer())
				return "<b style='color:" + Colour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your exposed body parts attract all kind of lewd gazes.";
			return super.getAdditionalDescription(target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& target.getLegConfiguration()==LegConfiguration.BIPEDAL
					&& isExposedParts(target, true, true);
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getExposedStatus(owner, super.getSVGString(owner));
		}
	},
	
	FETISH_EXHIBITIONIST(
			80,
			"exhibitionist",
			"exposedExhibitionist",
			Colour.BASE_PINK_LIGHT,
			Colour.BASE_PINK_DEEP,
			Colour.BASE_PINK_DEEP,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 25f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] incredibly sexy and empowered to be walking around in such an exposed fashion.");
		}
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer())
				return "<b style='color:" + Colour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your exposed body parts attract all kind of lewd gazes.";
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
	},
	
	FETISH_EXHIBITIONIST_BREASTS(
			80,
			"exhibitionist",
			"exposedExhibitionist",
			Colour.BASE_PINK_LIGHT,
			Colour.BASE_PINK_DEEP,
			Colour.BASE_PINK_DEEP,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] incredibly sexy and empowered to be walking around in such an exposed fashion.");
		}
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer())
				return "<b style='color:" + Colour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your exposed body parts attract all kind of lewd gazes.";
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
	},
	
	FETISH_EXHIBITIONIST_PLUS_BREASTS(
			80,
			"exhibitionist",
			"exposedExhibitionist",
			Colour.BASE_PINK_LIGHT,
			Colour.BASE_PINK_DEEP,
			Colour.BASE_PINK_DEEP,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 30f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "[npc.NamePos] clothing doesn't conceal [npc.her] "+getExposedPartsNamesList(target)+", and [npc.she] [npc.verb(feel)] incredibly sexy and empowered to be walking around in such an exposed fashion.");
		}
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			if(Main.game.isOpportunisticAttackersEnabled() && target.isPlayer())
				return "<b style='color:" + Colour.BASE_GREY.toWebHexString() +";'>Opportunistic Attackers</b><br>Your exposed body parts attract all kind of lewd gazes.";
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
	},

	FETISH_PURE_VIRGIN(
			80,
			"pure virgin",
			"virginPure",
			Colour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 15f)),
			null) {

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
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 10f)),
			null) {

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
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -50f),
					new Value<Attribute, Float>(Attribute.MAJOR_CORRUPTION, 50f)),
			null) {

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
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, -25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -50f),
					new Value<Attribute, Float>(Attribute.MAJOR_CORRUPTION, 50f)),
			null) {

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
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 10f)),
			null) {

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
			return ClothingSet.MAID.isCharacterWearingCompleteSet(target) && !target.hasTrait(Perk.JOB_MAID, true);
		}
	},
	
	SET_MAID_BOOSTED(
			70,
			"Professional Maid",
			"set_maidBoosted",
			Colour.CLOTHING_BLACK,
			Colour.BASE_GOLD,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "By wearing the entire Maid's outfit, [npc.nameIsFUll] reminded of [npc.her] true profession; that of an exceptionally talented (and sexy) maid!");
				
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.MAID.isCharacterWearingCompleteSet(target) && target.hasTrait(Perk.JOB_MAID, true);
		}
	},
	
	SET_BUTLER(
			70,
			"Butler",
			"set_butler",
			Colour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the entire Butler's outfit, you are filled with the energy you need in order to carry out your duties as a butler.";
					
				} else {
					return UtilText.parse(target, "By wearing the entire Butler's outfit, [npc.name] is filled with the energy [npc.she] needs in order to carry out [npc.her] duties as a butler.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.BUTLER.isCharacterWearingCompleteSet(target) && !target.hasTrait(Perk.JOB_BUTLER, true);
		}
	},
	
	SET_BUTLER_BOOSTED(
			70,
			"Professional Butler",
			"set_butlerBoosted",
			Colour.CLOTHING_WHITE,
			Colour.BASE_GOLD,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the entire Butler's outfit, you are reminded of your true profession; that of an exceptionally talented butler!";
					
				} else {
					return UtilText.parse(target, "By wearing the entire Butler's outfit, [npc.name] is reminded of [npc.her] true profession; that of an exceptionally talented butler!");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.BUTLER.isCharacterWearingCompleteSet(target) && target.hasTrait(Perk.JOB_BUTLER, true);
		}
	},
	
	SET_WITCH(
			70,
			"Arcane Witch",
			"set_witch",
			Colour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 10f)),
			null) {

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
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 2f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, 2f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 2f)),
			null) {

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
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 10f), new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target, "By wearing the entire Milk Maid's outfit, [npc.nameIsFull] filled with the energy [npc.she] [npc.verb(need)] in order to perform all of [npc.her] milking duties.");
				
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
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, 25f)),
			null) {

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
			Colour.CLOTHING_MULTICOLOURED,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return "Double rainbow... What does it mean?!";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.RAINBOW.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_DARK_SIREN(
			70,
			"Dark Siren",
			"set_dark_siren",
			Colour.CLOTHING_PURPLE_DARK,
			Colour.CLOTHING_BLACK_STEEL,
			Colour.CLOTHING_RED_DARK,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return "<i>Darkness and chaos, let the boundaries of the eternal void be shattered! Intangible manifestation of divine will, now let my sealed power be unleashed!</i>";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.DARK_SIREN.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_LYSSIETH_GUARD(
			70,
			"Lyssieth's Guard",
			"set_lyssieth_guard",
			Colour.CLOTHING_OLIVE,
			Colour.CLOTHING_BROWN_DARK,
			Colour.CLOTHING_OLIVE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {//British Auxiliary Territorial Service
			return UtilText.parse(target, "While wearing the uniform of Lyssieth's guard, [npc.name] [npc.verb(feel)] as though [npc.sheIs] more easily able to keep [npc.her] composure.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.LYSSIETH_GUARD.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_BDSM(
			70,
			"BDSM",
			"set_bdsm",
			Colour.CLOTHING_BLACK,
			false,
			Util.newHashMapOfValues( new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -15f)),
			null) {

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
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 5f)),
			null) {

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
	
	SET_SNOWFLAKE(
			70,
			"Glacial",
			"set_snowflake",
			Colour.BASE_BLUE_LIGHT,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 15f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "By donning the complete set of specially enchanted snowflake jewellery, both [npc.namePos] arcane power and ice attacks have become much more potent!");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.SNOWFLAKE.isCharacterWearingCompleteSet(target);
		}
	},
		
	SET_SUN(
			70,
			"Radiant",
			"set_sun",
			Colour.BASE_ORANGE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 15f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target==null) {
				return "";
			}
			return UtilText.parse(target, "By donning jewels that sparkle like the sun, [npc.nameIsFull] confident that [npc.her] spells and fire attacks will incinerate [npc.her] foes!");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.SUN.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_GEISHA(
			70,
			"Geisha",
			"set_geisha",
			Colour.BASE_ROSE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 15f)),
			null) {

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
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 15f)),
			null) {

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
	
	SET_DAISHO(
			70,
			"Daisho",
			"set_daisho",
			Colour.BASE_ROSE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 15f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				return UtilText.parse(target,
						"The Japanese term 'Daisho', meaning 'big-little', is used to describe the simultaneous wearing of a katana (the big) and wakizashi (the little)."
						+ " Wearing both of these swords at once marks the wielder as a member of the samurai class.");
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.WEAPON_DAISHO.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_JOLNIR(
			70,
			"J&oacute;lnir",
			"set_jolnir",
			Colour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 15f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 5f)),
			null) {

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
	
	CLOTHING_EFFECT(
			70,
			"clothing effects",
			"combatHidden",
			Colour.TRANSFORMATION_GENERIC,
			false,
			null,
			null) {

		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
	},
	
	POTION_EFFECTS(
			80,
			"potion effects",
			"potionEffects",
			Colour.GENERIC_ARCANE,
			Colour.BASE_PINK_LIGHT,
			null,
			false,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "Arcane-infused consumables are very common in this world, and, as [npc.nameHasFull] out, they can have some rather curious effects...");
		}
		
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

		
	},
	
	HAPPINESS(
			70,
			"happiness",
			"happinessFox",
			Colour.CLOTHING_SILVER,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, 5f)),
			Util.newArrayListOfValues(
					"[style.italicsGood(Happiness!)]")) {

		@Override
		public String getDescription(GameCharacter target) {
			return "The silver-furred fox, <i>Happiness</i>, is following you around wherever you go."
					+ " Whenever you feel tired or down, the cute little animal brushes up against your [pc.legs] and sits still to have its ears scratched, which instantly fills you with happiness.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isPlayer() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.foundHappiness);
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
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ANGEL, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how angels will behave.");
		}
		
	},
	
	COMBAT_BONUS_CAT_MORPH(
			80,
			"cat-morph intuition",
			"combatBonusCatMorph",
			Colour.RACE_CAT_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_CAT_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how cat-morphs will behave.");
		}
		
	},
	
	COMBAT_BONUS_COW_MORPH(
			80,
			"cow-morph intuition",
			"combatBonusCowMorph",
			Colour.RACE_COW_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_COW_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how cow-morphs will behave.");
		}
		
	},
	
	COMBAT_BONUS_DEMON(
			80,
			"demonic intuition",
			"combatBonusDemon",
			Colour.RACE_DEMON,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_DEMON, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how demons will behave.");
		}
		
	},

	COMBAT_BONUS_IMP(
			80,
			"impish intuition",
			"combatBonusImp",
			Colour.RACE_DEMON,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_IMP, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how imps will behave.");
		}
		
	},
	
	COMBAT_BONUS_DOG_MORPH(
			80,
			"dog-morph intuition",
			"combatBonusDogMorph",
			Colour.RACE_DOG_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_DOG_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how dog-morphs will behave.");
		}
		
	},
	
	COMBAT_BONUS_HARPY(
			80,
			"harpy intuition",
			"combatBonusHarpy",
			Colour.RACE_HARPY,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_HARPY, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how harpies will behave.");
		}
		
	},
	
	COMBAT_BONUS_HORSE_MORPH(
			80,
			"horse-morph intuition",
			"combatBonusHorseMorph",
			Colour.RACE_HORSE_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_HORSE_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how horse-morphs will behave.");
		}
		
	},
	
	COMBAT_BONUS_REINDEER_MORPH(
			80,
			"reindeer-morph intuition",
			"combatBonusReindeerMorph",
			Colour.RACE_REINDEER_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_REINDEER_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how reindeer-morphs will behave.");
		}
		
	},
	
	COMBAT_BONUS_HUMAN(
			80,
			"human intuition",
			"combatBonusHuman",
			Colour.RACE_HUMAN,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_HUMAN, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how humans will behave.");
		}
		
	},
	
	COMBAT_BONUS_SQUIRREL_MORPH(
			80,
			"squirrel-morph intuition",
			"combatBonusSquirrelMorph",
			Colour.RACE_SQUIRREL_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SQUIRREL_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how squirrel-morphs will behave.");
		}
		
	},
	
	COMBAT_BONUS_RAT_MORPH(
			80,
			"rat-morph intuition",
			"combatBonusRatMorph",
			Colour.RACE_RAT_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_RAT_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how rat-morphs will behave.");
		}
		
	},
	
	COMBAT_BONUS_RABBIT_MORPH(
			80,
			"rabbit-morph intuition",
			"combatBonusRabbitMorph",
			Colour.RACE_RAT_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_RABBIT_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how rabbit-morphs will behave.");
		}
		
	},
	
	COMBAT_BONUS_BAT_MORPH(
			80,
			"bat-morph intuition",
			"combatBonusBatMorph",
			Colour.RACE_BAT_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_BAT_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how bat-morphs will behave.");
		}
		
	},
	
	COMBAT_BONUS_ALLIGATOR_MORPH(
			80,
			"alligator-morph intuition",
			"combatBonusAlligatorMorph",
			Colour.RACE_ALLIGATOR_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ALLIGATOR_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how alligator-morphs will behave.");
		}
		
	},
	
	COMBAT_BONUS_WOLF_MORPH(
			80,
			"wolf-morph intuition",
			"combatBonusWolfMorph",
			Colour.RACE_WOLF_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_WOLF_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how wolf-morphs will behave.");
		}
	},
	
	COMBAT_BONUS_FOX_MORPH(
			80,
			"fox-morph intuition",
			"combatBonusFoxMorph",
			Colour.RACE_FOX_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_FOX_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how fox-morphs will behave.");
		}
	},
	
	COMBAT_BONUS_SLIME(
			80,
			"slime intuition",
			"combatBonusSlime",
			Colour.RACE_SLIME,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SLIME, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.nameIsFull] able to accurately predict how slimes will behave.");
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
		public String getDescription(GameCharacter target) {
			return "You don't know what perks, status effects, spells, or special attacks your opponent has available. You require the "+Perk.OBSERVANT.getName(target)+" perk to reveal such information.";
		}

		
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	DESPERATE_FOR_SEX(
			70,
			"desperate for sex",
			"desperateForSex",
			Colour.ATTRIBUTE_LUST,
			false,
			null,
			Util.newArrayListOfValues(
					"Incoming <b style='color:"+Colour.ATTRIBUTE_LUST.toWebHexString()+";'>Lust damage</b> dealt as"
							+ " <b style='color:"+Colour.ATTRIBUTE_HEALTH.toWebHexString()+";'>2*Energy damage</b>"
							+ " and <b style='color:"+Colour.ATTRIBUTE_MANA.toWebHexString()+";'>1*Aura damage</b>",
					"<b style='color: " + Colour.GENERIC_TERRIBLE.toWebHexString() + "'>Incoming damage ignores all shielding</b>")) {

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
	},

	// From spells (still in combat):
	
	ARCANE_WEAKNESS(//TODO
			10,
			"arcane weakness",
			"negativeCombatEffect",
			Colour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -2f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -2f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, -2f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, -2f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, -2f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.namePos] head is spinning and [npc.sheIs] struggling to stay upright..");
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	DAZED(10,
			"dazed",
			"negativeCombatEffect",
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, -25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -2f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
				return UtilText.parse(target, "[npc.NamePos] head is spinning and [npc.sheIs] struggling to stay upright. [npc.SheIs] finding it incredibly difficult to land a hit or dodge any incoming attacks.");
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
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You've been temporarily crippled, and you're struggling to do as much damage with your attacks as you're usually able to.";
			else
				return UtilText.parse(target,
						target.getName("The") + "'s been temporarily crippled, and [npc.sheIs] struggling to do as much damage with [npc.her] attacks as [npc.sheIs] usually able to.");
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
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -2f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.NameIsFull] feeling particularly vulnerable, and [npc.she] [npc.is]n't able to defend [npc.herself] to the best of [npc.her] ability.");
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
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public boolean isStun() {
			return true;
		}
	},
	
	WITCH_CHARM(
			10,
			"Bewitching Charm",
			"combat_witch_charm",
			Colour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 25f)),
			null) {
			@Override
			public String applyEffect(GameCharacter target, int secondsPassed) {
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
	
	BANEFUL_FISSURE(
			10,
			"Fissure's Fumes",
			null,
			Colour.DAMAGE_TYPE_POISON,
			false,
			null,
			Util.newArrayListOfValues("<b>25</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			int damage = DamageType.POISON.damageTarget(null, target, 25);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!");
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
	},

	FIRE_MANA_BURN(10,
			"Aura Burn",
			"melee_fire",
			Colour.DAMAGE_TYPE_FIRE,
			true,
			null,
			Util.newArrayListOfValues(
					"[style.boldFire(Fire Spells)] can be cast at a cost of "+Attribute.HEALTH_MAXIMUM.getName()+" when out of aura",
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
	},
	
	LINGERING_FLAMES(
			10,
			"Lingering Flames",
			null,
			Colour.DAMAGE_TYPE_FIRE,
			false,
			null,
			Util.newArrayListOfValues("<b>5</b> [style.boldFire(Fire Damage)] per turn</b>")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			int damage = DamageType.FIRE.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> [style.boldFire(fire damage)]!");
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
	},
	
	FLASH(10,
			"Blinded",
			null,
			Colour.DAMAGE_TYPE_FIRE,
			false,
			null,
			Util.newArrayListOfValues("[style.boldTerrible(Stunned!)]")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			if (target.isPlayer()) {
				return "You are [style.boldTerrible(stunned)] from the blinding flash!";
				
			} else {
				return "[npc.Name] is [style.boldTerrible(stunned)] from the blinding flash!";
			}
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The blinding flash of light has left you temporarily stunned, and you're unable to make any sort of move as you struggle to regain control of your senses.";
			} else {
				return UtilText.parse(target,
						"The blinding flash of light has left [npc.name] temporarily stunned, and [npc.sheIs] unable to make any sort of move as [npc.she] struggles to regain control of [npc.her] senses.");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.FLASH.getSVGString();
		}

		@Override
		public boolean isCombatEffect() {
			return true;
		}
		
		@Override
		public boolean isStun() {
			return true;
		}
	},

	CLOAK_OF_FLAMES(10,
			"Cloak of Flames",
			null,
			Colour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You have been shrouded in a cloak of arcane flames that improve your unarmed attacks at a cost of burning your aura proportionally.";
			} else {
				return UtilText.parse(target,
						"[npc.Name] has been shrouded in a cloak of arcane flames that improve [npc.her] unarmed attacks at a cost of burning [npc.her] aura proportionally.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_1.getSVGString();
		}

		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	CLOAK_OF_FLAMES_1(10,
			"Cloak of Flames (Incendiary)",
			null,
			Colour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 10f)),
			Util.newArrayListOfValues(
					"Unarmed attacks deal +1 damage per caster level",
					"Unarmed attacks deal [style.boldFire(Fire Damage)]")) {

		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You have been shrouded in a cloak of arcane flames that improve your unarmed attacks at a cost of burning your aura proportionally.";
			} else {
				return UtilText.parse(target,
						"[npc.Name] has been shrouded in a cloak of arcane flames that improve [npc.her] unarmed attacks at a cost of burning [npc.her] aura proportionally.");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_1.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	CLOAK_OF_FLAMES_2(10,
			"Cloak of Flames (Inferno)",
			null,
			Colour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 10f)),
			Util.newArrayListOfValues(
					"Unarmed attacks deal +1 damage per caster level",
					"Unarmed attacks deal [style.boldFire(Fire Damage)]")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You have been shrouded in a cloak of arcane flames, granting you significant bonuses to your ice and fire resistances, as well as to fire damage."
						+ " You are also dealing extra fire damage each time you attack.";
			} else {
				return UtilText.parse(target,
						"[npc.Name] has been shrouded in a cloak of arcane flames, granting [npc.herHim] significant bonuses to [npc.her] ice and fire resistances, as well as to fire damage."
						+ " [npc.She] is also dealing extra fire damage each time [npc.she] attacks.");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_2.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	CLOAK_OF_FLAMES_3(10,
			"Cloak of Flames (Ring of Fire)",
			null,
			Colour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 20f)),
			Util.newArrayListOfValues(
					"Unarmed attacks deal +1 damage per caster level",
					"Unarmed attacks deal [style.boldFire(Fire Damage)]",
					"Attackers take <b>5</b> [style.colourFire(Fire Damage)]")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You have been shrouded in a cloak of arcane flames, granting you significant bonuses to your ice and fire resistances, as well as to fire damage."
						+ " You are also dealing extra fire damage each time you attack, as well as to any enemies that strike you in melee!";
			} else {
				return UtilText.parse(target,
						"[npc.Name] has been shrouded in a cloak of arcane flames, granting [npc.herHim] significant bonuses to [npc.her] ice and fire resistances, as well as to fire damage."
						+ " [npc.She] is also dealing extra fire damage each time [npc.she] attacks, as well as to any enemies that strike [npc.herHim] in melee!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.CLOAK_OF_FLAMES_3.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	ELEMENTAL_FIRE_WILDFIRE(10,
			"Wildfire",
			null,
			Colour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 20f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The Fire elemental in your party is imbuing you with the knowledge of how to get the most out of fire attacks!";
			} else {
				return UtilText.parse(target,
						"The Fire elemental in [npc.namePos] party is imbuing [npc.herHim] with the knowledge of how to get the most out of fire attacks!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_1.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			GameCharacter partyLeader = null;
			if(!target.getCompanions().isEmpty()) {
				partyLeader = target;
			}
			if(target.getPartyLeader()!=null) {
				partyLeader = target.getPartyLeader();
			}
			
			if(partyLeader==null) {
				return false;
			}
			
			List<GameCharacter> allPartyMembers = new ArrayList<>(partyLeader.getCompanions());
			allPartyMembers.add(partyLeader);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.hasDiscoveredElemental()
						&& allPartyMembers.contains(companion.getElemental())
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_1)
						&& !companion.getElemental().equals(target)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.FIRE) {
					return true;
				}
			}
			
			return false;
		}
	},
	
	ELEMENTAL_FIRE_BURNING_DESIRE(10,
			"Burning Desire",
			null,
			Colour.DAMAGE_TYPE_FIRE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The Fire elemental in the enemy party is filling you with a burning desire for sex!";
			} else {
				return UtilText.parse(target,
						"The Fire elemental in the enemy party is filling [npc.name] with a burning desire for sex!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_2.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInCombat()) {
				List<GameCharacter> enemies = Combat.getEnemies(target);
				
				for(GameCharacter combatant : enemies) {
					if(combatant instanceof Elemental
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
	},
	
	ELEMENTAL_FIRE_SERVANT_OF_FIRE(10,
			"Servant of Fire",
			null,
			Colour.DAMAGE_TYPE_FIRE,
			false,
			null,
			Util.newArrayListOfValues(
					"-50% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return UtilText.parse(target.getElemental(), "Having sworn your subservience to the school of Fire, your Fire elemental, [npc.name], is siphoning off as much of your energy as [npc.she] wants!");
			} else {
				return UtilText.parse(target, target.getElemental(),
						"Having sworn [npc1.her] subservience to the school of Fire, [npc1.namePos] Fire elemental, [npc2.name], is now siphoning off as much of [npc1.namePos] energy as [npc2.she] wants!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_3A.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasDiscoveredElemental()
					&& target.hasCompanion(target.getElemental())
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.FIRE;
		}
	},
	
	ELEMENTAL_FIRE_SERVANT_OF_FIRE_ELEMENTAL_BUFF(10,
			"Energy Siphon",
			null,
			Colour.DAMAGE_TYPE_FIRE,
			true,
			null,
			Util.newArrayListOfValues(
					"+100% [style.colourExcellent(Non-Seduction Damage)]")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if(((Elemental)target).getSummoner().isPlayer()) {
				return UtilText.parse(target, "[npc.Name] is siphoning off as much of your energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
			} else {
				return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.Name] is siphoning off as much of [npc2.namePos] energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_3A.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target instanceof Elemental
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.FIRE;
		}
	},
	
	ELEMENTAL_FIRE_BINDING_OF_FIRE(10,
			"Binding of Fire",
			null,
			Colour.DAMAGE_TYPE_FIRE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 10f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return UtilText.parse(target.getElemental(), "Having bound the school of Fire to your will, your Fire elemental, [npc.name], is forced to reveal all of [npc.her] secrets to you.");
			} else {
				return UtilText.parse(target, target.getElemental(),
						"Having bound the school of Fire to [npc1.her] will, [npc1.namePos] Fire elemental, [npc2.name], is forced to reveal all of [npc2.her] secrets to [npc2.her] [npc1.mistress].");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_FIRE_3B.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasDiscoveredElemental()
					&& target.hasCompanion(target.getElemental())
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_FIRE_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.FIRE;
		}
	},
	
	FREEZING_FOG(
			10,
			"Freezing Fog",
			null,
			Colour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, -20f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A freezing fog, left behind by the impact of the spell 'Ice Shard', is lingering around you. The arcane cold seeps into your body, slowing your movements and dulling your mind.";
			} else {
				return UtilText.parse(target,
						"A freezing fog, left behind by the impact of the spell 'Ice Shard', is lingering around [npc.name]. The arcane cold is seeping into [npc.her] body, slowing [npc.her] movements and dulling [npc.her] mind.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ICE_SHARD_1.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	FROZEN(10,
			"Frozen",
			null,
			Colour.DAMAGE_TYPE_COLD,
			false,
			null,
			Util.newArrayListOfValues("[style.boldTerrible(Stunned!)]")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			if (target.isPlayer()) {
				return "You are [style.boldTerrible(stunned)] from freezing fog's detonation!";
				
			} else {
				return "[npc.Name] is [style.boldTerrible(stunned)] from freezing fog's detonation!";
			}
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The freezing fog lingering around you has exploded, covering you in a thin sheet of ice and leaving you temporarily stunned!";
				
			} else {
				return UtilText.parse(target, "The freezing fog lingering around [npc.name] has exploded, covering [npc.herHim] in a thin sheet of ice and leaving [npc.herHim] temporarily stunned!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ICE_SHARD_3.getSVGString();
		}

		@Override
		public boolean isCombatEffect() {
			return true;
		}
		
		@Override
		public boolean isStun() {
			return true;
		}
	},
	
	RAIN_CLOUD(
			10,
			"Rain Cloud",
			null,
			Colour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, -25f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A small, arcane-infused rain cloud is hovering above your head. The cold rain is sapping your ability to effectively cast spells.";
			} else {
				return UtilText.parse(target,
						"A small, arcane-infused rain cloud is hovering above [npc.namePos] head. The cold rain is sapping [npc.her] ability to effectively cast spells.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.RAIN_CLOUD.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},

	RAIN_CLOUD_DEEP_CHILL(
			10,
			"Rain Cloud (Deep Chill)",
			null,
			Colour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, -25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, -25f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A small, arcane-infused rain cloud is hovering above your head. The freezing rain is both sapping your ability to effectively cast spells, and reducing your resistance to the cold.";
			} else {
				return UtilText.parse(target,
						"A small, arcane-infused rain cloud is hovering above [npc.namePos] head. The freezing rain is both sapping [npc.her] ability to effectively cast spells, and reducing [npc.her] resistance to the cold.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.RAIN_CLOUD_1.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},

	RAIN_CLOUD_DOWNPOUR(
			10,
			"Rain Cloud (Downpour)",
			null,
			Colour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, -25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, -25f),
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -5f)),
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
	},

	RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST(
			10,
			"Rain Cloud (Downpour)",
			null,
			Colour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, -25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, -25f),
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -5f)),
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
	},
	
	RAIN_CLOUD_CLOUDBURST(
			10,
			"Rain Cloud (Cloudburst)",
			null,
			Colour.DAMAGE_TYPE_COLD,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, -50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, -25f),
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -5f)),
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
	},
	
	ELEMENTAL_WATER_CRASHING_WAVES(10,
			"Crashing Waves",
			null,
			Colour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 20f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The Water elemental in your party is imbuing you with the knowledge of how to get the most out of ice attacks!";
			} else {
				return UtilText.parse(target,
						"The Water elemental in [npc.namePos] party is imbuing [npc.herHim] with the knowledge of how to get the most out of ice attacks!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_1.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			
			GameCharacter partyLeader = null;
			if(!target.getCompanions().isEmpty()) {
				partyLeader = target;
			}
			if(target.getPartyLeader()!=null) {
				partyLeader = target.getPartyLeader();
			}
			
			if(partyLeader==null) {
				return false;
			}
			
			List<GameCharacter> allPartyMembers = new ArrayList<>(partyLeader.getCompanions());
			allPartyMembers.add(partyLeader);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.hasDiscoveredElemental()
						&& allPartyMembers.contains(companion.getElemental())
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_1)
						&& !companion.getElemental().equals(target)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.WATER) {
					return true;
				}
			}
			
			return false;
		}
	},
	
	ELEMENTAL_WATER_CALM_WATERS(10,
			"Calm Waters",
			null,
			Colour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 5f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The Water elemental in your party is helping to calm your desire for sex.";
			} else {
				return UtilText.parse(target,
						"The Water elemental in [npc.namePos] party is helping to calm [npc.her] desire for sex.");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_2.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			GameCharacter partyLeader = null;
			if(!target.getCompanions().isEmpty()) {
				partyLeader = target;
			}
			if(target.getPartyLeader()!=null) {
				partyLeader = target.getPartyLeader();
			}
			
			if(partyLeader==null) {
				return false;
			}
			
			List<GameCharacter> allPartyMembers = new ArrayList<>(partyLeader.getCompanions());
			allPartyMembers.add(partyLeader);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.hasDiscoveredElemental()
						&& allPartyMembers.contains(companion.getElemental())
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_2)
						&& !companion.getElemental().equals(target)
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
	},
	
	ELEMENTAL_WATER_SERVANT_OF_WATER(10,
			"Servant of Water",
			null,
			Colour.DAMAGE_TYPE_COLD,
			false,
			null,
			Util.newArrayListOfValues(
					"-50% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return UtilText.parse(target.getElemental(), "Having sworn your subservience to the school of Water, your Water elemental, [npc.name], is siphoning off as much of your energy as [npc.she] wants!");
			} else {
				return UtilText.parse(target, target.getElemental(),
						"Having sworn [npc1.her] subservience to the school of Water, [npc1.namePos] Water elemental, [npc2.name], is now siphoning off as much of [npc1.namePos] energy as [npc2.she] wants!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_3A.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasDiscoveredElemental()
					&& target.hasCompanion(target.getElemental())
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.WATER;
		}
	},
	
	ELEMENTAL_WATER_SERVANT_OF_WATER_ELEMENTAL_BUFF(10,
			"Energy Siphon",
			null,
			Colour.DAMAGE_TYPE_COLD,
			true,
			null,
			Util.newArrayListOfValues(
					"+100% [style.colourExcellent(Non-Seduction Damage)]")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if(((Elemental)target).getSummoner().isPlayer()) {
				return UtilText.parse(target, "[npc.Name] is siphoning off as much of your energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
			} else {
				return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.Name] is siphoning off as much of [npc2.namePos] energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_3A.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target instanceof Elemental
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.WATER;
		}
	},
	
	ELEMENTAL_WATER_BINDING_OF_WATER(10,
			"Binding of Water",
			null,
			Colour.DAMAGE_TYPE_COLD,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 10f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return UtilText.parse(target.getElemental(), "Having bound the school of Water to your will, your Water elemental, [npc.name], is forced to reveal all of [npc.her] secrets to you.");
			} else {
				return UtilText.parse(target, target.getElemental(),
						"Having bound the school of Water to [npc1.her] will, [npc1.namePos] Water elemental, [npc2.name], is forced to reveal all of [npc2.her] secrets to [npc2.her] [npc1.mistress].");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_WATER_3B.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasDiscoveredElemental()
					&& target.hasCompanion(target.getElemental())
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_WATER_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.WATER;
		}
	},
	
	

	POISON_VAPOURS(
			10,
			"Poison Vapours",
			null,
			Colour.DAMAGE_TYPE_POISON,
			false,
			null,
			Util.newArrayListOfValues("<b>10</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			int damage = DamageType.POISON.damageTarget(null, target, 10);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!");
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The cloud of poison vapours continues to linger around your body, causing you to cough and splutter each time you breathe in.";
			} else {
				return UtilText.parse(target,
						"The cloud of poison vapours continues to linger around [npc.namePos] body, causing [npc.herHim] to cough and splutter each time [npc.she] breathes in.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.POISON_VAPOURS.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},

	POISON_VAPOURS_CHOKING_HAZE(
			10,
			"Poison Vapours (Choking Haze)",
			null,
			Colour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues("<b>10</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>")) {
				
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			int damage = DamageType.POISON.damageTarget(null, target, 10);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+"!");
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The cloud of poison vapours continues to linger around your body, causing you to cough and splutter each time you breathe in.";
			} else {
				return UtilText.parse(target,
						"The cloud of poison vapours continues to linger around [npc.namePos] body, causing [npc.herHim] to cough and splutter each time [npc.she] breathes in.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.POISON_VAPOURS_1.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},

	POISON_VAPOURS_ARCANE_SICKNESS(
			10,
			"Poison Vapours (Arcane Sickness)",
			null,
			Colour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -5f)),
			Util.newArrayListOfValues(
					"<b>10</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>",
					"<b>10</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+" [style.boldTerrible(drained)] per turn</b>")) {
				
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			int damage = DamageType.POISON.damageTarget(null, target, 10);

			int lustDamage = 10;
			target.incrementMana(-lustDamage);
			
			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" and [npc.verb(lose)] <b>" + lustDamage + "</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!");
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The cloud of poison vapours continues to linger around your body, causing you to cough and splutter each time you breathe in.";
			} else {
				return UtilText.parse(target,
						"The cloud of poison vapours continues to linger around [npc.namePos] body, causing [npc.herHim] to cough and splutter each time [npc.she] breathes in.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.POISON_VAPOURS_2.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},

	POISON_VAPOURS_WEAKENING_CLOUD(
			10,
			"Poison Vapours (Weakening Cloud)",
			null,
			Colour.DAMAGE_TYPE_POISON,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, -15f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues(
					"<b>10</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" per turn</b>",
					"<b>10</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+" [style.boldTerrible(drained)] per turn</b>")) {
				
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			int damage = DamageType.POISON.damageTarget(null, target, 10);

			int lustDamage = 10;
			target.incrementMana(-lustDamage);
			
			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> "+Attribute.DAMAGE_POISON.getColouredName("b")+" and [npc.verb(lose)] <b>" + lustDamage + "</b> "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!");
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The cloud of poison vapours continues to linger around your body, causing you to cough and splutter each time you breathe in.";
			} else {
				return UtilText.parse(target,
						"The cloud of poison vapours continues to linger around [npc.namePos] body, causing [npc.herHim] to cough and splutter each time [npc.she] breathes in.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.POISON_VAPOURS_3.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	VACUUM(
			10,
			"Vacuum",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -10f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A sustained void in the air, which shifts to remain close to your body, is causing your balance to be thrown off!";
			} else {
				return UtilText.parse(target,
						"A sustained void in the air, which shifts to remain close to [npc.namePos] body, is causing [npc.her] balance to be thrown off!");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.VACUUM.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},

	VACUUM_SECONDARY_VOIDS(
			10,
			"Vacuum (Secondary Voids)",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -20f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, -25f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A sustained void in the air, which shifts to remain close to your body, is causing your balance to be thrown off!"
						+ " Smaller, secondary voids are also continuously being created and destroyed around you!";
			} else {
				return UtilText.parse(target,
						"A sustained void in the air, which shifts to remain close to [npc.namePos] body, is causing [npc.her] balance to be thrown off!"
						+ " Smaller, secondary voids are also continuously being created and destroyed around [npc.herHim]!");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.VACUUM_1.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},

	VACUUM_SUCTION(
			10,
			"Vacuum (Suction)",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -20f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues(
					"<b>10%</b> chance per turn of [style.boldExcellent(stripping)] clothing")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
			if (target.isPlayer()) {
				return "A sustained, powerful void is shifting to remain close to your body, and is causing your balance to be thrown off!"
						+ " Smaller, secondary voids are also continuously being created and destroyed around you!";
			} else {
				return UtilText.parse(target,
						"A sustained, powerful void is shifting to remain close to [npc.namePos] body, and is causing [npc.her] balance to be thrown off!"
						+ " Smaller, secondary voids are also continuously being created and destroyed around [npc.herHim]!");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.VACUUM_2.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},

	VACUUM_TOTAL_VOID(
			10,
			"Vacuum (Total Void)",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -20f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, -25f)),
			Util.newArrayListOfValues(
					"<b>25%</b> chance per turn of [style.boldExcellent(stripping)] clothing")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
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
			if (target.isPlayer()) {
				return "A sustained, incredibly-powerful void is shifting to remain close to your body, and is causing your balance to be thrown off!"
						+ " Smaller, secondary voids are also continuously being created and destroyed around you!";
			} else {
				return UtilText.parse(target,
						"A sustained, incredibly-powerful void is shifting to remain close to [npc.namePos] body, and is causing [npc.her] balance to be thrown off!"
						+ " Smaller, secondary voids are also continuously being created and destroyed around [npc.herHim]!");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.VACUUM_3.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	PROTECTIVE_GUSTS(
			10,
			"Protective Gusts",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, 5f),
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, 1f)),
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
	},

	PROTECTIVE_GUSTS_GUIDING_WIND(
			10,
			"Protective Gusts (Guiding Wind)",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, 5f),
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 10f)),
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
	},

	PROTECTIVE_GUSTS_FOCUSED_BLAST(
			10,
			"Protective Gusts (Focused Blast)",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, 5f),
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, 3f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 25f)),
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
	},
	

	
	ELEMENTAL_AIR_WHIRLWIND(10,
			"Whirlwind",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -5f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The Air elemental in the enemy party is surrounding you with buffeting winds!";
			} else {
				return UtilText.parse(target,
						"The Air elemental in the enemy party is surrounding [npc.name] with buffeting winds!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_1.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInCombat()) {
				List<GameCharacter> enemies = Combat.getEnemies(target);
				
				for(GameCharacter combatant : enemies) {
					if(combatant instanceof Elemental
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
	},
	
	ELEMENTAL_AIR_VITALISING_SCENTS(10,
			"Vitalising Scents",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 10f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The Air elemental in your party is surrounding you with vitalising scents, giving you the energy to dodge attacks, while landing powerful strikes of your own!";
			} else {
				return UtilText.parse(target,
						"The Air elemental in [npc.namePos] party is surrounding [npc.herHim] with vitalising scents, giving [npc.herHim] the energy to dodge attacks, while landing powerful strikes of [npc.her] own!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_2.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			
			GameCharacter partyLeader = null;
			if(!target.getCompanions().isEmpty()) {
				partyLeader = target;
			}
			if(target.getPartyLeader()!=null) {
				partyLeader = target.getPartyLeader();
			}
			
			if(partyLeader==null) {
				return false;
			}
			
			List<GameCharacter> allPartyMembers = new ArrayList<>(partyLeader.getCompanions());
			allPartyMembers.add(partyLeader);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.hasDiscoveredElemental()
						&& allPartyMembers.contains(companion.getElemental())
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_2)
						&& !companion.getElemental().equals(target)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.AIR) {
					return true;
				}
			}
			
			return false;
		}
	},
	
	ELEMENTAL_AIR_SERVANT_OF_AIR(10,
			"Servant of Air",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			null,
			Util.newArrayListOfValues(
					"-50% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return UtilText.parse(target.getElemental(), "Having sworn your subservience to the school of Air, your Air elemental, [npc.name], is siphoning off as much of your energy as [npc.she] wants!");
			} else {
				return UtilText.parse(target, target.getElemental(),
						"Having sworn [npc1.her] subservience to the school of Air, [npc1.namePos] Air elemental, [npc2.name], is now siphoning off as much of [npc1.namePos] energy as [npc2.she] wants!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_3A.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasDiscoveredElemental()
					&& target.hasCompanion(target.getElemental())
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.AIR;
		}
	},
	
	ELEMENTAL_AIR_SERVANT_OF_AIR_ELEMENTAL_BUFF(10,
			"Energy Siphon",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			null,
			Util.newArrayListOfValues(
					"+100% [style.colourExcellent(Non-Seduction Damage)]")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if(((Elemental)target).getSummoner().isPlayer()) {
				return UtilText.parse(target, "[npc.Name] is siphoning off as much of your energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
			} else {
				return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.Name] is siphoning off as much of [npc2.namePos] energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_3A.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target instanceof Elemental
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.AIR;
		}
	},
	
	ELEMENTAL_AIR_BINDING_OF_AIR(10,
			"Binding of Air",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, 10f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return UtilText.parse(target.getElemental(), "Having bound the school of Air to your will, your Air elemental, [npc.name], is forced to reveal all of [npc.her] secrets to you.");
			} else {
				return UtilText.parse(target, target.getElemental(),
						"Having bound the school of Air to [npc1.her] will, [npc1.namePos] Air elemental, [npc2.name], is forced to reveal all of [npc2.her] secrets to [npc2.her] [npc1.mistress].");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_AIR_3B.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasDiscoveredElemental()
					&& target.hasCompanion(target.getElemental())
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.AIR;
		}
	},
	
	SLAM_GROUND_SHAKE(
			10,
			"Ground Shake",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -10f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The ground beneath your [npc.feet] is shaking and swaying, causing you to miss the occasional attack.";
			} else {
				return UtilText.parse(target,
						"The ground beneath [npc.namePos] [npc.feet] is shaking and swaying, causing [npc.herHim] to miss the occasional attack.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.SLAM_1.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},

	SLAM_AFTER_SHOCK(
			10,
			"Ground Shake (After Shock)",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -10f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The ground beneath your [npc.feet] is shaking and swaying, causing you to miss the occasional attack. You can sense that there's an Aftershock coming, but there's little you can do to avoid it...";
			} else {
				return UtilText.parse(target,
						"The ground beneath [npc.namePos] [npc.feet] is shaking and swaying, causing [npc.herHim] to miss the occasional attack. [npc.She] can sense that there's an Aftershock coming, but there's little [npc.she] can do to avoid it...");
			}
		}
		
		@Override
		protected String extraRemovalEffects(GameCharacter target){
			int damage = DamageType.PHYSICAL.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!");
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.SLAM_2.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	TELEKENETIC_SHOWER(
			10,
			"Telekinetic Shower",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			null,
			Util.newArrayListOfValues("<b>10</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" per turn</b>")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			int damage = DamageType.PHYSICAL.damageTarget(null, target, 10);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!");
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You are being continuously pelted by a barrage of rocks and other small objects.";
			} else {
				return UtilText.parse(target,
						"[npc.Name] is being continuously pelted by a barrage of rocks and other small objects.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.TELEKENETIC_SHOWER.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	TELEKENETIC_SHOWER_PRECISION_STRIKES(
			10,
			"Telekinetic Shower (Precision Strikes)",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -20f)),
			Util.newArrayListOfValues("<b>10</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" per turn</b>")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			int damage = DamageType.PHYSICAL.damageTarget(null, target, 10);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!");
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You are being continuously pelted by a highly-accurate barrage of rocks and other small objects.";
			} else {
				return UtilText.parse(target,
						"[npc.Name] is being continuously pelted by a highly-accurate barrage of rocks and other small objects.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEKENETIC_SHOWER_2.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	TELEKENETIC_SHOWER_UNSEEN_FORCE(
			10,
			"Telekinetic Shower (Unseen Force)",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -20f)),
			Util.newArrayListOfValues("<b>20</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" per turn</b>")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			int damage = DamageType.PHYSICAL.damageTarget(null, target, 20);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+"!");
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You are being continuously pelted by a highly-accurate barrage of rocks and other small objects. Each impact is immediately followed up by an explosive wave of force.";
			} else {
				return UtilText.parse(target,
						"[npc.Name] is being continuously pelted by a highly-accurate barrage of rocks and other small objects. Each impact is immediately followed up by an explosive wave of force.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEKENETIC_SHOWER_3.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	STONE_SHELL(
			10,
			"Stone Shell",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 5f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A solid barrier of stone is being held up between you and your enemy, granting you a significantly improved physical defence.";
			} else {
				return UtilText.parse(target,
						"A solid barrier of stone is being held up between [npc.name] and [npc.her] enemy, granting [npc.herHim] a significantly improved physical defence.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.STONE_SHELL.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},

	STONE_SHELL_SHIFTING_SANDS(
			10,
			"Stone Shell (Shifting Sands)",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, 2f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A solid barrier of stone is being held up between you and your enemy, granting you a significantly improved physical defence."
							+ " Every now and then, the stone barrier suddenly shifts and melts into sand, before reforming in a different location.";
			} else {
				return UtilText.parse(target,
						"A solid barrier of stone is being held up between [npc.name] and [npc.her] enemy, granting [npc.herHim] a significantly improved physical defence."
								+ " Every now and then, the stone barrier suddenly shifts and melts into sand, before reforming in a different location.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.STONE_SHELL_1.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	STONE_SHELL_HARDENED_CARAPACE(
			10,
			"Stone Shell (Hardened Carapace)",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 10f),
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, 2f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A solid, hardened barrier of stone is being held up between you and your enemy, granting you a significantly improved physical defence."
							+ " Every now and then, the stone barrier suddenly shifts and melts into sand, before reforming in a different location.";
			} else {
				return UtilText.parse(target,
						"A solid, hardened barrier of stone is being held up between [npc.name] and [npc.her] enemy, granting [npc.herHim] a significantly improved physical defence."
								+ " Every now and then, the stone barrier suddenly shifts and melts into sand, before reforming in a different location.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.STONE_SHELL_2.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	STONE_SHELL_EXPLOSIVE_FINISH(
			10,
			"Stone Shell (Explosive Finish)",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 10f),
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, 2f)),
			Util.newArrayListOfValues("[style.colourExcellent(All enemies)] take <b>10</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" when Stone Shell ends")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A solid, hardened barrier of stone is being held up between you and your enemy, granting you a significantly improved physical defence."
							+ " Every now and then, the stone barrier suddenly shifts and melts into sand, before reforming in a different location.";
			} else {
				return UtilText.parse(target,
						"A solid, hardened barrier of stone is being held up between [npc.name] and [npc.her] enemy, granting [npc.herHim] a significantly improved physical defence."
								+ " Every now and then, the stone barrier suddenly shifts and melts into sand, before reforming in a different location.");
			}
		}
		
		@Override
		protected String extraRemovalEffects(GameCharacter target){
			StringBuilder sb = new StringBuilder();
			
			boolean first=true;
			for(GameCharacter combatant : Combat.getEnemies(target)) {
				int damage = DamageType.PHYSICAL.damageTarget(target, combatant, 10);
				sb.append(UtilText.parse(combatant, target, (first?"":"<br/>")+"<br/>[npc.Name] [npc.verb(take)] <b>" + damage + "</b> "+Attribute.DAMAGE_PHYSICAL.getColouredName("b")+" as [npc2.namePos] Stone Shell explodes!"));
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
	},
	
	ELEMENTAL_EARTH_ROLLING_STONE(10,
			"Rolling Stone",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 25f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The Earth elemental in your party is empowering your attacks with waves of force!";
			} else {
				return UtilText.parse(target,
						"The Earth elemental in [npc.namePos] party is empowering [npc.her] attacks with waves of force!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_1.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			
			GameCharacter partyLeader = null;
			if(!target.getCompanions().isEmpty()) {
				partyLeader = target;
			}
			if(target.getPartyLeader()!=null) {
				partyLeader = target.getPartyLeader();
			}
			
			if(partyLeader==null) {
				return false;
			}
			
			List<GameCharacter> allPartyMembers = new ArrayList<>(partyLeader.getCompanions());
			allPartyMembers.add(partyLeader);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.hasDiscoveredElemental()
						&& allPartyMembers.contains(companion.getElemental())
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_1)
						&& !companion.getElemental().equals(target)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.EARTH) {
					return true;
				}
			}
			
			return false;
		}
	},
	
	ELEMENTAL_EARTH_HARDENING(10,
			"Hardening",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The Earth elemental in your party is using telekinetic powers to surround you with protective fragments of rock!";
			} else {
				return UtilText.parse(target,
						"The Earth elemental in [npc.namePos] party is using telekinetic powers to surround [npc.herHim] with protective fragments of rock!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_2.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			
			GameCharacter partyLeader = null;
			if(!target.getCompanions().isEmpty()) {
				partyLeader = target;
			}
			if(target.getPartyLeader()!=null) {
				partyLeader = target.getPartyLeader();
			}
			
			if(partyLeader==null) {
				return false;
			}
			
			List<GameCharacter> allPartyMembers = new ArrayList<>(partyLeader.getCompanions());
			allPartyMembers.add(partyLeader);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.hasDiscoveredElemental()
						&& allPartyMembers.contains(companion.getElemental())
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_2)
						&& !companion.getElemental().equals(target)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.EARTH) {
					return true;
				}
			}
			
			return false;
		}
	},
	
	ELEMENTAL_EARTH_SERVANT_OF_EARTH(10,
			"Servant of Earth",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			null,
			Util.newArrayListOfValues(
					"-50% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return UtilText.parse(target.getElemental(), "Having sworn your subservience to the school of Earth, your Earth elemental, [npc.name], is siphoning off as much of your energy as [npc.she] wants!");
			} else {
				return UtilText.parse(target, target.getElemental(),
						"Having sworn [npc1.her] subservience to the school of Earth, [npc1.namePos] Earth elemental, [npc2.name], is now siphoning off as much of [npc1.namePos] energy as [npc2.she] wants!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_3A.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasDiscoveredElemental()
					&& target.hasCompanion(target.getElemental())
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.EARTH;
		}
	},
	
	ELEMENTAL_EARTH_SERVANT_OF_EARTH_ELEMENTAL_BUFF(10,
			"Energy Siphon",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			null,
			Util.newArrayListOfValues(
					"+100% [style.colourExcellent(Non-Seduction Damage)]")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if(((Elemental)target).getSummoner().isPlayer()) {
				return UtilText.parse(target, "[npc.Name] is siphoning off as much of your energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
			} else {
				return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.Name] is siphoning off as much of [npc2.namePos] energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_3A.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target instanceof Elemental
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.EARTH;
		}
	},
	
	ELEMENTAL_EARTH_BINDING_OF_EARTH(10,
			"Binding of Earth",
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return UtilText.parse(target.getElemental(), "Having bound the school of Earth to your will, your Earth elemental, [npc.name], is forced to reveal all of [npc.her] secrets to you.");
			} else {
				return UtilText.parse(target, target.getElemental(),
						"Having bound the school of Earth to [npc1.her] will, [npc1.namePos] Earth elemental, [npc2.name], is forced to reveal all of [npc2.her] secrets to [npc2.her] [npc1.mistress].");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_EARTH_3B.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasDiscoveredElemental()
					&& target.hasCompanion(target.getElemental())
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_EARTH_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.EARTH;
		}
	},
	

	ARCANE_AROUSAL_LUSTFUL_DISTRACTION(
			10,
			"Lustful Distraction",
			null,
			Colour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -15f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Arousing images and thoughts keep on pushing their way to the front of your mind, causing you to lose focus on what it is you're trying to hit.";
			} else {
				return UtilText.parse(target,
						"Arousing images and thoughts keep on pushing their way to the front of [npc.namePos] mind, causing [npc.herHim] to lose focus on what it is [npc.sheIs] trying to hit.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_AROUSAL_2.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	ARCANE_AROUSAL_DIRTY_PROMISES(
			10,
			"Lustful Distraction (Dirty Promises)",
			null,
			Colour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, -15f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Arousing images keep on pushing their way into your mind, causing you to lose focus on what it is you're trying to hit."
						+ " You hear the occasional phantasmal whisper in your [pc.ear], promising that you'll have a good time if you simply submit...";
			} else {
				return UtilText.parse(target,
						"Arousing images keep on pushing their way into [npc.namePos] mind, causing [npc.herHim] to lose focus on what it is [npc.sheIs] trying to hit."
								+ " [npc.She] hears the occasional phantasmal whisper in [npc.her] [npc.ear], promising that [npc.she]'ll have a good time if [npc.she] simply submits.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_AROUSAL_3.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	TELEPATHIC_COMMUNICATION(
			10,
			"Telepathic Communication",
			null,
			Colour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You are able to project your seductive taunts and [pc.moans+] directly into a person's mind!";
			} else {
				return UtilText.parse(target,
						"[npc.Name] is able to project [npc.her] seductive taunts and [npc.moans+] directly into a person's mind!");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.TELEPATHIC_COMMUNICATION.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH(
			10,
			"Projected Touch",
			null,
			Colour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 30f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You are able to project your seductive taunts and [pc.moans+] directly into a person's mind!"
						+ " In addition to this, you can deliver phantasmal kisses and gropes to your target.";
			} else {
				return UtilText.parse(target,
						"[npc.Name] is able to project [npc.her] seductive taunts and [npc.moans+] directly into a person's mind!"
								+ " In addition to this, [npc.she] can deliver phantasmal kisses and gropes to [npc.her] target.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEPATHIC_COMMUNICATION_2.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION(
			10,
			"Power of Suggestion",
			null,
			Colour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 30f)),
			Util.newArrayListOfValues(
					"[style.boldLust(Tease)] [style.boldExcellent(applies)] -25 "+Attribute.RESISTANCE_PHYSICAL.getColouredName("b")+" to the target for [style.boldGood(2 turns)]")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You are able to project your seductive taunts and suggestive phrases directly into a person's mind!"
						+ " In addition to this, you can deliver phantasmal kisses and gropes to your target.";
			} else {
				return UtilText.parse(target,
						"[npc.Name] is able to project [npc.her] seductive taunts and suggestive phrases directly into a person's mind!"
								+ " In addition to this, [npc.she] can deliver phantasmal kisses and gropes to [npc.her] target.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEPATHIC_COMMUNICATION_3.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED(
			10,
			"Power of Suggestion",
			"telepathic_communication_power_of_suggestion_targeted",
			Colour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -25f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The telepathic moans and suggestions that have been projected into your mind are causing you to lower your guard!";
			} else {
				return UtilText.parse(target,
						"The telepathic moans and suggestions that have been projected into [npc.namePos] mind are causing [npc.herHim] to lower [npc.her] guard!");
			}
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	
	ARCANE_CLOUD(
			10,
			"Arcane Cloud",
			null,
			Colour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -25f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A small arcane cloud is hovering above your head. While subjected to its presence, you feel the arousing effects of the arcane seeping into your mind.";
			} else {
				return UtilText.parse(target,
						"A small arcane cloud is hovering above [npc.namePos] head. While subjected to its presence, the arousing effects of the arcane seep into [npc.her] mind.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.ARCANE_CLOUD.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},

	ARCANE_CLOUD_ARCANE_LIGHTNING(
			10,
			"Arcane Cloud (Arcane Lightning)",
			null,
			Colour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("<b>5</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn</b>")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			int damage = DamageType.LUST.damageTarget(null, target, 5);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> [style.boldLust(lust damage)]!");
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A small, lust-inducing arcane cloud is hovering above your head. Every now and then, a bolt of arcane lightning flashes down towards you, filling your mind with arousing thoughts.";
			} else {
				return UtilText.parse(target,
						"A small, lust-inducing arcane cloud is hovering above [npc.namePos] head. Every now and then, a bolt of arcane lightning flashes down towards [npc.herHim], filling [npc.her] mind with arousing thoughts.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_CLOUD_1.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},

	ARCANE_CLOUD_ARCANE_THUNDER(
			10,
			"Arcane Cloud (Arcane Thunder)",
			null,
			Colour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("<b>15</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn</b>")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			int damage = DamageType.LUST.damageTarget(null, target, 15);

			return UtilText.parse(target, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> [style.boldLust(lust damage)]!");
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A small, lust-inducing arcane cloud is hovering above your head. Every now and then, a bolt of arcane lightning, accompanied by a small clap of arcane thunder, fills your mind with arousing thoughts.";
			} else {
				return UtilText.parse(target,
						"A small, lust-inducing arcane cloud is hovering above [npc.namePos] head. Every now and then, a bolt of arcane lightning, accompanied by a small clap of arcane thunder, fills [npc.her] mind with arousing thoughts.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_CLOUD_2.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	ARCANE_CLOUD_LOCALISED_STORM(
			10,
			"Arcane Cloud (Localised Storm)",
			null,
			Colour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -25f)),
			Util.newArrayListOfValues("[style.boldTerrible(All party members)] take <b>15</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn</b>")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			StringBuilder sb = new StringBuilder();
			
			for(GameCharacter combatant : Combat.getEnemies(target)) {
				int damage = DamageType.LUST.damageTarget(null, combatant, 15);

				sb.append(UtilText.parse(combatant, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> [style.boldLust(lust damage)]!"));
			}
			
			return sb.toString();
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A localised arcane storm is hovering above your head. Every now and then, a bolt of arcane lightning, accompanied by a small clap of arcane thunder, fills your mind with arousing thoughts.";
			} else {
				return UtilText.parse(target,
						"A localised arcane storm is hovering above [npc.namePos] head. Every now and then, a bolt of arcane lightning, accompanied by a small clap of arcane thunder, fills [npc.her] mind with arousing thoughts.");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ARCANE_CLOUD_3.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	
	
	
	


	ELEMENTAL_ARCANE_LEWD_ENCOURAGEMENTS(10,
			"Lewd Encouragements",
			null,
			Colour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 15f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The Arcane elemental in your party is projecting lewd words of encouragement into your mind!";
			} else {
				return UtilText.parse(target,
						"The Arcane elemental in [npc.namePos] party is projecting lewd words of encouragement into [npc.her] mind!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_1.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			
			GameCharacter partyLeader = null;
			if(!target.getCompanions().isEmpty()) {
				partyLeader = target;
			}
			if(target.getPartyLeader()!=null) {
				partyLeader = target.getPartyLeader();
			}
			
			if(partyLeader==null) {
				return false;
			}
			
			List<GameCharacter> allPartyMembers = new ArrayList<>(partyLeader.getCompanions());
			allPartyMembers.add(partyLeader);
			
			for(GameCharacter companion : allPartyMembers) {
				if(companion.hasDiscoveredElemental()
						&& allPartyMembers.contains(companion.getElemental())
						&& companion.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_1)
						&& !companion.getElemental().equals(target)
						&& companion.getElemental().getCurrentSchool()==SpellSchool.ARCANE) {
					return true;
				}
			}
			
			return false;
		}
	},

	ELEMENTAL_ARCANE_CARESSING_TOUCH(10,
			"Caressing Touch",
			null,
			Colour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -15f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The Arcane elemental in the enemy party is projecting phantasmal tentacles to caress and grope your body!";
			} else {
				return UtilText.parse(target,
						"The Arcane elemental in the enemy party is projecting phantasmal tentacles to caress and grope [npc.namePos] body!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_2.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInCombat()) {
				List<GameCharacter> enemies = Combat.getEnemies(target);
				
				for(GameCharacter combatant : enemies) {
					if(combatant instanceof Elemental
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
	},
	
	ELEMENTAL_ARCANE_SERVANT_OF_ARCANE(10,
			"Servant of Arcane",
			null,
			Colour.DAMAGE_TYPE_LUST,
			false,
			null,
			Util.newArrayListOfValues(
					"-50% [style.colourHealth(Maximum "+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return UtilText.parse(target.getElemental(), "Having sworn your subservience to the school of Arcane, your Arcane elemental, [npc.name], is siphoning off as much of your energy as [npc.she] wants!");
			} else {
				return UtilText.parse(target, target.getElemental(),
						"Having sworn [npc1.her] subservience to the school of Arcane, [npc1.namePos] Arcane elemental, [npc2.name], is now siphoning off as much of [npc1.namePos] energy as [npc2.she] wants!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_3A.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasDiscoveredElemental()
					&& target.hasCompanion(target.getElemental())
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_3A)
					&& target.getElemental().getCurrentSchool()==SpellSchool.ARCANE;
		}
	},
	
	ELEMENTAL_ARCANE_SERVANT_OF_ARCANE_ELEMENTAL_BUFF(10,
			"Energy Siphon",
			null,
			Colour.DAMAGE_TYPE_LUST,
			true,
			null,
			Util.newArrayListOfValues(
					"+100% [style.colourExcellent(Non-Seduction Damage)]")) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if(((Elemental)target).getSummoner().isPlayer()) {
				return UtilText.parse(target, "[npc.Name] is siphoning off as much of your energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
			} else {
				return UtilText.parse(target, ((Elemental)target).getSummoner(), "[npc.Name] is siphoning off as much of [npc2.namePos] energy as [npc.she] wants, enabling [npc.herHim] to deal a huge amount of damage!");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_3A.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target instanceof Elemental
					&& ((Elemental)target).getSummoner()!=null
					&& ((Elemental)target).getSummoner().hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_3A)
					&& ((Elemental)target).getCurrentSchool()==SpellSchool.ARCANE;
		}
	},
	
	ELEMENTAL_ARCANE_BINDING_OF_ARCANE(10,
			"Binding of Arcane",
			null,
			Colour.DAMAGE_TYPE_LUST,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 10f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return UtilText.parse(target.getElemental(), "Having bound the school of Arcane to your will, your Arcane elemental, [npc.name], is forced to reveal all of [npc.her] secrets to you.");
			} else {
				return UtilText.parse(target, target.getElemental(),
						"Having bound the school of Arcane to [npc1.her] will, [npc1.namePos] Arcane elemental, [npc2.name], is forced to reveal all of [npc2.her] secrets to [npc2.her] [npc1.mistress].");
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.ELEMENTAL_ARCANE_3B.getSVGString();
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasDiscoveredElemental()
					&& target.hasCompanion(target.getElemental())
					&& target.hasSpellUpgrade(SpellUpgrade.ELEMENTAL_ARCANE_3B)
					&& target.getElemental().getCurrentSchool()==SpellSchool.ARCANE;
		}
	},
	
	
	

	ARCANE_DUALITY_POSITIVE(
			10,
			"Arcane Duality (Defence)",
			"cleanse_positive",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, 5f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A protective barrier of arcane energy has surrounded you, shielding you from all types of incoming damage.";
			} else {
				return UtilText.parse(target,
						"A protective barrier of arcane energy has surrounded [npc.name], shielding [npc.herHim] from all types of incoming damage.");
			}
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	ARCANE_DUALITY_NEGATIVE(
			10,
			"Arcane Duality (Weakness)",
			"cleanse_negative",
			Colour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, -5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, -5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, -5f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A weakening barrier of arcane energy has surrounded you, making you more vulnerable to all types of incoming damage.";
			} else {
				return UtilText.parse(target,
						"A weakening barrier of arcane energy has surrounded [npc.name], making [npc.herHim] more vulnerable to all types of incoming damage.");
			}
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	TELEPORT(
			10,
			"Teleport",
			null,
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, 100f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You have teleported behind your enemies, making it extremely unlikely that they'll be able to land a hit on you!";
			} else {
				return UtilText.parse(target,
						"[npc.Name] has teleported behind [npc.her] enemies, making it extremely unlikely that they'll be able to land a hit on [npc.herHim]!");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return Spell.TELEPORT.getSVGString();
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	TELEPORT_ARCANE_ARRIVAL(
			10,
			"Teleport (Arcane Arrival)",
			null,
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.ENERGY_SHIELDING, 100f)),
			Util.newArrayListOfValues("<b>5</b> "+Attribute.DAMAGE_LUST.getColouredName("b")+" per turn to a random enemy")) {
		
		@Override
		public String applyEffect(GameCharacter target, int secondsPassed) {
			GameCharacter randomEnemy = Combat.getEnemies(target).get(Util.random.nextInt(Combat.getEnemies(target).size()));
			
			int damage = DamageType.LUST.damageTarget(null, randomEnemy, 15);

			return UtilText.parse(randomEnemy, "[npc.Name] [npc.verb(take)] <b>" + damage + "</b> [style.boldLust(lust damage)]!");
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You have teleported behind your enemies, making it extremely unlikely that they'll be able to land a hit on you! A burst of lust-inducing arcane energy accompanies your arrival!";
			} else {
				return UtilText.parse(target,
						"[npc.Name] has teleported behind [npc.her] enemies, making it extremely unlikely that they'll be able to land a hit on [npc.herHim]! A burst of lust-inducing arcane energy accompanies [npc.her] arrival!");
			}
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return SpellUpgrade.TELEPORT_1.getSVGString();
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
	},
	
	DESIRES(
			80,
			"Desires",
			"desires",
			Colour.GENERIC_ARCANE,
			false,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Your fetishes and desires affect how much arousal you gain from performing related sex actions. Selecting an action with an associated fetish that you own will also not increase your corruption.";
				
			} else if(Main.game.isInSex()) {
				GameCharacter targetedCharacter = Sex.getTargetedPartner(target);
				SexType preference = Sex.isInForeplay(target)?Sex.getForeplayPreference((NPC) target, targetedCharacter):Sex.getMainSexPreference((NPC) target, targetedCharacter);
				return UtilText.parse(target, targetedCharacter,
						(Main.game.isInNewWorld()
								?"The power of your arcane aura allows you to sense [npc.namePos] sexual preferences:"
								:"Somehow, you're able to instinctively sense what [npc.namePos] sexual preferences are: ")
						+ " [style.italicsSex("
						+ (preference!=null
								?"[npc.She] wants to use [npc.her] "+preference.getPerformingSexArea().getName(target)+" and [npc2.namePos] "+preference.getTargetedSexArea().getName(Sex.getTargetedPartner(target))+"."
								:"[npc.She] has no preference in how to fuck [npc2.name]...")
						+ ")]")
						+ (Sex.isCharacterObeyingTarget(target, Main.game.getPlayer())
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
				modList.add("<b style='color:"+desire.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(desire.getNameAsVerb())+"</b>: "+Util.capitaliseSentence(f.getShortDescriptor()));
			}
			
			return modList;
		}
		
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
	},
	
	ORGASM_COUNTER(
			80,
			"Orgasms",
			"sexEffects/orgasms",
			Colour.GENERIC_ARCANE,
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

			Colour orgasmColour = Colour.GENERIC_ARCANE;
			int orgasms = Sex.getNumberOfOrgasms(target);
			if(orgasms<RenderingEngine.orgasmColours.length) {
				orgasmColour = RenderingEngine.orgasmColours[orgasms];
			}
			
			modList.add("<b style='color:"+orgasmColour.toWebHexString()+";'>"+orgasms+"</b> Orgasm"+(orgasms==1?"":"s"));

			int essences = Sex.getEssenceGeneration(target);
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
			SVGImageSB = new StringBuilder();

			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGString+"</div>");
			
			int orgasms = Sex.getNumberOfOrgasms(owner);
			
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
	},
	
	PENIS_STATUS(
			95,
			"Penis status",
			null,
			Colour.GENERIC_SEX,
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
			descriptionSB = new StringBuilder();
			SexAreaPenetration type = SexAreaPenetration.PENIS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>a handjob</b>!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>frotting</b> with [npc2.name]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>a tail-job</b>!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>a tentacle-job</b>!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>a blowjob</b>!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-frotting</b> with [npc2.name]!"));
								break;
							case FOOT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>a foot-job</b>!"));
								break;
						}
						
					}
				}
			}
			
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No ongoing action.</b>");
			}

			appendPenetrationAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.penis]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			if(Sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.PENIS).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.PENIS).get(0);
			
			return Sex.formatPenetration(
					target.getPenetrationDescription(false,
							target,
							SexAreaPenetration.PENIS,
							partner,
							Sex.getOngoingActionsMap(target).get(SexAreaPenetration.PENIS).get(partner).iterator().next()));
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getAllParticipants(true).contains(target)
					&& Sex.getOngoingActionsMap(target)!=null
					&& !Sex.getContactingSexAreas(target, SexAreaPenetration.PENIS).isEmpty()
					&& !Collections.disjoint(
							Sex.getContactingSexAreas(target, SexAreaPenetration.PENIS).get(Sex.getCharacterContactingSexArea(target, SexAreaPenetration.PENIS).get(0)),
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
	},
	
	CLIT_STATUS(
			95,
			"Clitoris status",
			null,
			Colour.GENERIC_SEX,
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
			descriptionSB = new StringBuilder();
			SexAreaPenetration type = SexAreaPenetration.CLIT;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc2.name]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>teasing</b> [npc2.namePos] pussy!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-teasing</b> [npc2.namePos] pussy!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-teasing</b> [npc2.namePos] pussy!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>performing cunnilingus</b> on [npc2.name]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tribbing</b> with [npc2.name]!"));
								break;
							case FOOT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>foot-teasing</b> [npc2.namePos] pussy!"));
								break;
						}
						
					}
				}
			}
			
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No ongoing action.</b>");
			}

			appendPenetrationAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.clit]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			if(Sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.CLIT).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Sex.getCharactersHavingOngoingActionWith(target, SexAreaPenetration.CLIT).get(0);
			
			return Sex.formatPenetration(
					target.getPenetrationDescription(false,
							target,
							SexAreaPenetration.CLIT,
							partner,
							Sex.getOngoingActionsMap(target).get(SexAreaPenetration.CLIT).get(partner).iterator().next()));
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getAllParticipants(true).contains(target)
					&& Sex.getOngoingActionsMap(target)!=null
					&& !Sex.getContactingSexAreas(target, SexAreaPenetration.CLIT).isEmpty()
					&& !Collections.disjoint(
							Sex.getContactingSexAreas(target, SexAreaPenetration.CLIT).get(Sex.getCharacterContactingSexArea(target, SexAreaPenetration.CLIT).get(0)),
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
	},
	
	ANUS_STATUS(
			96,
			"Anus status",
			null,
			Colour.GENERIC_SEX,
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
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.ANUS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");

			boolean descriptionAdded = false;
			for(SexAreaPenetration pen : SexAreaPenetration.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Sex.getOngoingCharactersUsingAreas(target, type, pen)) {
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
					switch(pen) {
						case FINGER:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" [style.boldSex(fingering)] [npc.namePos] [npc.asshole]!"));
							break;
						case PENIS:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" [style.boldSex(fucking)] [npc.namePos] [npc.asshole]!"));
							break;
						case TAIL:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" [style.boldSex(tail-fucking)] [npc.namePos] [npc.asshole]!"));
							break;
						case TENTACLE:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" [style.boldSex(tentacle-fucking)] [npc.namePos] [npc.asshole]!"));
							break;
						case TONGUE:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" performing [style.boldSex(anilingus)] on [npc.name]!"));
							break;
						case CLIT:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" [style.boldSex(clit-fucking)] [npc.namePos] [npc.asshole]!"));
							break;
						case FOOT:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
									+(names.size()==1?UtilText.parse(main, " [npc.is] [style.boldSex(pushing [npc.her] [npc.toes])]"):" are [style.boldSex(pushing their toes)]")
									+"  into [npc.namePos] [npc.asshole]!"));
							break;
					}
				}
			}

			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Sex.getOngoingCharactersUsingAreas(target, type, orifice)) {
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
					switch(orifice) {
						case ANUS:
							break;
						case ASS:
							break;
						case BREAST:
							break;
						case NIPPLE:
							break;
						case BREAST_CROTCH:
							break;
						case NIPPLE_CROTCH:
							break;
						case MOUTH:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" performing [style.boldSex(anilingus)] on [npc2.name]!"));
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" [style.boldSex(fucking)] [npc.namePos] [npc.pussy]!"));
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							break;
					}
				}
			}
			
			if(!descriptionAdded) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.asshole]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.ANUS;
			if(Sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			List<GameCharacter> ongoingCharacters = Sex.getCharactersHavingOngoingActionWith(target, orifice);
			GameCharacter partner = ongoingCharacters.get(Util.random.nextInt(ongoingCharacters.size()));
			
			return Sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice));
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getAllParticipants(true).contains(target);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.ANUS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus());
		}
	},

	ASS_STATUS(
			96,
			"Ass status",
			null,
			Colour.GENERIC_SEX,
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
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.ASS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.namePos] [npc2.ass]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>hotdogging</b> [npc2.name]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-hotdogging</b> [npc2.name]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-hotdogging</b> [npc2.name]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>licking</b> [npc2.namePos] [npc2.ass]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-hotdogging</b> [npc2.name]!"));
								break;
							case FOOT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.namePos] [npc2.ass] with [npc.her] [npc.feet]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case NIPPLE:
								break;
							case BREAST_CROTCH:
								break;
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>licking</b> [npc2.namePos] [npc2.ass]!"));
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>hotdogging</b> [npc2.name]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.ass]"), descriptionSB);
			
			descriptionSB.append("</p>");

			
			return descriptionSB.toString();
		}
		
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.ASS;
			if(Sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return Sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice));
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getAllParticipants(true).contains(target);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.ASS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAss());
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
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.MOUTH;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.namePos] [npc.fingers]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] giving [npc.name] a <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>blowjob</b>!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.namePos] [npc.tail]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.namePos] tentacle!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.name]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.namePos] [npc.clit]!"));
								break;
							case FOOT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>licking</b> [npc.namePos] [npc.feet]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>anilingus</b> on [npc.name]!"));
								break;
							case ASS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>licking</b> [npc.namePos] [npc.ass]!"));
								break;
							case BREAST:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.namePos] [npc.breasts]!"));
								break;
							case NIPPLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.namePos] [npc.nipples]!"));
								break;
							case BREAST_CROTCH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.namePos] [npc.crotchBoobs]!"));
								break;
							case NIPPLE_CROTCH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.namePos] [npc.crotchNipples]!"));
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.name]!"));
								break;
							case THIGHS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.namePos] [npc.legs]!"));
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] giving [npc.name] a <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>blowjob</b>!"));
								break;
							case URETHRA_VAGINA:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>cunnilingus</b> on [npc.name]!"));
								break;
							case VAGINA:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>cunnilingus</b> on [npc.name]!"));
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] mouth"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.MOUTH;
			if(Sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return Sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice));
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getAllParticipants(true).contains(target);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.MOUTH, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMouth());
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
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.BREAST;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.namePos] [npc2.breasts]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.name]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.name]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.name]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.breasts]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.namePos] [npc.clit]!"));
								break;
							case FOOT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.namePos] [npc2.breasts] with [npc.her] [npc.feet]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case NIPPLE:
								break;
							case BREAST_CROTCH:
								break;
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.breasts]!"));
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.name]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.breasts]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.BREAST;
			if(Sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return Sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice));
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getAllParticipants(true).contains(target);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.BREAST, owner.hasBreasts()?SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreasts():SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsFlat());
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
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.NIPPLE;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-fucking</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-fucking</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case FOOT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>pushing [npc.her] [npc.toes]</b> into [npc2.namePos] [npc2.nipples]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case NIPPLE:
								break;
							case BREAST_CROTCH:
								break;
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.nipples]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			
			return descriptionSB.toString();
		}
		
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.NIPPLE;
			if(Sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return Sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice));
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getAllParticipants(true).contains(target)
					&& target.isBreastFuckableNipplePenetration()
					&& Main.getProperties().hasValue(PropertyValue.nipplePenContent);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.NIPPLE, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple());
		}
	},
	
	BREAST_CROTCH_STATUS(
			98,
			"Crotch-boob status",
			null,
			Colour.GENERIC_SEX,
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
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.BREAST_CROTCH;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.namePos] [npc2.crotchBoobs]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>[npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.name]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>[npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.namePos] [npc.tail]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>[npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.namePos] [npc.tentacle]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.crotchBoobs]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>[npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.namePos] [npc.clit]!"));
								break;
							case FOOT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.namePos] [npc2.crotchBoobs] with [npc.her] [npc.feet]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case NIPPLE:
								break;
							case BREAST_CROTCH:
								break;
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.crotchBoobs]!"));
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>[npc2.crotchBoob]-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.name]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.crotchBoobs]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.BREAST_CROTCH;
			if(Sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return Sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice));
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getAllParticipants(true).contains(target)
					&& target.hasBreastsCrotch()
					&& Main.getProperties().udders>0;
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
	},
	
	NIPPLE_CROTCH_STATUS(
			97,
			"Nipple status",
			null,
			Colour.GENERIC_SEX,
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
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.NIPPLE_CROTCH;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc2.namePos] [npc2.crotchNipples]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.crotchNipples]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.namePos] [npc2.crotchNipples]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-fucking</b> [npc2.namePos] [npc2.crotchNipples]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.crotchNipples]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-fucking</b> [npc2.namePos] [npc2.crotchNipples]!"));
								break;
							case FOOT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>pushing [npc.her] [npc.toes]</b> into [npc2.namePos] [npc2.crotchNipples]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case NIPPLE:
								break;
							case BREAST_CROTCH:
								break;
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.crotchNipples]!"));
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.crotchNipples]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.crotchNipples]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			
			return descriptionSB.toString();
		}
		
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.NIPPLE_CROTCH;
			if(Sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return Sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice));
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getAllParticipants(true).contains(target)
					&& target.hasBreastsCrotch()
					&& Main.getProperties().udders>0
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
	},
	
	URETHRA_PENIS_STATUS(
			97,
			"Penis Urethra status",
			null,
			Colour.GENERIC_SEX,
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
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.URETHRA_PENIS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-fucking</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-fucking</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case FOOT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>pushing [npc.her] [npc.toes]</b> into [npc2.namePos] [npc2.urethraPenis]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case NIPPLE:
								break;
							case BREAST_CROTCH:
								break;
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.urethraPenis]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.URETHRA_PENIS;
			if(Sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return Sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice));
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getAllParticipants(true).contains(target)
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
	},
	
	URETHRA_VAGINA_STATUS(
			97,
			"Vaginal Urethra status",
			null,
			Colour.GENERIC_SEX,
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
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.URETHRA_VAGINA;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-fucking</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-fucking</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case FOOT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>pushing [npc.her] [npc.toes]</b> into [npc2.namePos] [npc2.urethraVagina]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case NIPPLE:
								break;
							case BREAST_CROTCH:
								break;
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.urethraVagina]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.URETHRA_VAGINA;
			if(Sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return Sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice));
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getAllParticipants(true).contains(target)
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
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.VAGINA;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			
			boolean descriptionAdded = false;
			for(SexAreaPenetration pen : SexAreaPenetration.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Sex.getOngoingCharactersUsingAreas(target, type, pen)) {
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
					switch(pen) {
						case FINGER:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" [style.boldSex(fingering)] [npc.namePos] [npc.pussy]!"));
							break;
						case PENIS:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" [style.boldSex(fucking)] [npc.namePos] [npc.pussy]!"));
							break;
						case TAIL:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" [style.boldSex(tail-fucking)] [npc.namePos] [npc.pussy]!"));
							break;
						case TENTACLE:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" [style.boldSex(tentacle-fucking)] [npc.namePos] [npc.pussy]!"));
							break;
						case TONGUE:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" performing [style.boldSex(cunnilingus)] on [npc.name]!"));
							break;
						case CLIT:
							if(main.getVaginaClitorisSize()!=ClitorisSize.ZERO_AVERAGE) {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(clit-fucking)] [npc.namePos] [npc.pussy]!"));
							} else {
								descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
										+" [style.boldSex(tribbing)] with [npc2.name]!"));
							}
							break;
						case FOOT:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
									+(names.size()==1?UtilText.parse(main, " [npc.is] [style.boldSex(pushing [npc.her] [npc.toes])]"):" are [style.boldSex(pushing their toes)]")
									+"  into [npc.namePos] [npc.pussy]!"));
							break;
					}
				}
			}

			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				List<String> names = new ArrayList<>();
				GameCharacter main = null;
				for(GameCharacter c : Sex.getOngoingCharactersUsingAreas(target, type, orifice)) {
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
					switch(orifice) {
						case ANUS:
							break;
						case ASS:
							break;
						case BREAST:
							break;
						case NIPPLE:
							break;
						case BREAST_CROTCH:
							break;
						case NIPPLE_CROTCH:
							break;
						case MOUTH:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" performing [style.boldSex(cunnilingus)] on [npc2.name]!"));
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" [style.boldSex(fucking)] [npc.namePos] [npc.pussy]!"));
							break;
						case URETHRA_VAGINA:
							break;
						case VAGINA:
							descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
									+" [style.boldSex(tribbing)] with [npc2.name]!"));
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
		public String getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.VAGINA;
			if(Sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}

			List<GameCharacter> ongoingCharacters = Sex.getCharactersHavingOngoingActionWith(target, orifice);
			GameCharacter partner = ongoingCharacters.get(Util.random.nextInt(ongoingCharacters.size()));
			
			return Sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice));
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getAllParticipants(true).contains(target)
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
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.THIGHS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.namePos] [npc2.legs]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] thighs!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.namePos] thighs!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-fucking</b> [npc2.namePos] thighs!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.legs]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-fucking</b> [npc2.namePos] thighs!"));
								break;
							case FOOT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>pushing [npc.her] [npc.feet]</b> between [npc2.namePos] thighs!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case NIPPLE:
								break;
							case BREAST_CROTCH:
								break;
							case NIPPLE_CROTCH:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.legs]!"));
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] thighs!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] thighs"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public String getAdditionalDescription(GameCharacter target) {
			SexAreaOrifice orifice = SexAreaOrifice.THIGHS;
			if(Sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
				return null;
			}
			
			GameCharacter partner = Sex.getCharactersHavingOngoingActionWith(target, orifice).get(0);
			
			return Sex.formatPenetration(
					target.getPenetrationDescription(false,
							partner,
							(SexAreaPenetration)Sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
							target,
							orifice));
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getAllParticipants(true).contains(target);
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

	private int renderingPriority;
	private String name;
	private Colour colourShade;
	private boolean beneficial;
	private final Map<Attribute, Float> attributeModifiers;

	protected String SVGString;

	protected List<String> extraEffects;

	protected List<String> modifiersList;
	
	private static StringBuilder descriptionSB, SVGImageSB;

	private StatusEffect(int renderingPriority,
			String name,
			String pathName,
			Colour colourShade,
			boolean beneficial,
			Map<Attribute, Float> attributeModifiers,
			List<String> extraEffects) {
		this(renderingPriority, name, pathName, colourShade, colourShade, colourShade, beneficial, attributeModifiers, extraEffects);
	}
	
	private StatusEffect(int renderingPriority,
			String name,
			String pathName,
			Colour colourShade,
			Colour colourShadeSecondary,
			boolean beneficial,
			Map<Attribute, Float> attributeModifiers,
			List<String> extraEffects) {
		this(renderingPriority, name, pathName, colourShade, colourShadeSecondary, colourShade, beneficial, attributeModifiers, extraEffects);
	}
	
	private StatusEffect(int renderingPriority,
			String name,
			String pathName,
			Colour colourShade,
			Colour colourShadeSecondary,
			Colour colourShadeTertiary,
			boolean beneficial,
			Map<Attribute, Float> attributeModifiers,
			List<String> extraEffects) {
		
		this.renderingPriority = renderingPriority;
		this.name = name;
		this.beneficial = beneficial;
		this.attributeModifiers = attributeModifiers;
		this.colourShade = colourShade;

		if(extraEffects == null) {
			this.extraEffects = new ArrayList<>();
		} else {
			this.extraEffects = extraEffects;
		}
		
		if(pathName!=null && !pathName.isEmpty()) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/" + pathName + ".svg");
				if(is==null) {
					System.err.println("Error! StatusEffect icon file does not exist (Trying to read from '"+pathName+"')!");
				}
				SVGString = SvgUtil.colourReplacement(this.toString(), colourShade, colourShadeSecondary, colourShadeTertiary, Util.inputStreamToString(is));
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			SVGString = "";
		}
	}

	public static void updateAttributeModifiers() {
		for (StatusEffect s : StatusEffect.values()) {
			s.modifiersList = s.attributeModifiersToStringList(s.attributeModifiers);
		}
	}
	
	protected List<String> attributeModifiersToStringList(Map<Attribute, Float> attributeMap) {
		List<String> attributeModifiersList = new ArrayList<>();
		
		if (attributeMap != null) {
			for (Entry<Attribute, Float> e : attributeMap.entrySet()) {
				attributeModifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + Units.number(e.getValue(), 1, 1) + "</b>" + " <b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
			}
		}
		
		return attributeModifiersList;
	}
	
	public String applyEffect(GameCharacter target, int secondsPassed) {
		return "";
	}

	/**
	 * @param target
	 * @return True if this status effect should be applied to the target.
	 *  False if conditions are not met <b>or</b> this status effect is only for timed purposes (i.e. the only time it should be applied is with a time condition.).
	 */
	public boolean isConditionsMet(GameCharacter target) {
		return false;
	}
	
	public boolean renderInEffectsPanel() {
		return true;
	}
	
	public boolean isCombatEffect() {
		return false;
	}
	
	public boolean isSexEffect() {
		return false;
	}
	
	public float getArousalPerTurnSelf(GameCharacter self) {
		return 0;
	}
	
	public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
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
	
	public String applyPostRemovalStatusEffect(GameCharacter target) {
		return "";
	}

	public int getRenderingPriority() {
		return renderingPriority;
	}

	public String getName(GameCharacter target) {
		return name;
	}

	public abstract String getDescription(GameCharacter target);

	// For any extra effects, such as ongoing sex descriptions.
	public String getAdditionalDescription(GameCharacter target) {
		return null;
	}

	public List<String> getModifiersAsStringList(GameCharacter target) {
		ArrayList<String> fullModList = new ArrayList<>(attributeModifiersToStringList(getAttributeModifiers(target)));
		fullModList.addAll(getExtraEffects(target));
		return fullModList;
	}
	
	public boolean isBeneficial() {
		return beneficial;
	}
	
	public boolean isStun() {
		return false;
	}

	public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
		return attributeModifiers;
	}

	public Colour getColour() {
		return colourShade;
	}

	public List<String> getExtraEffects(GameCharacter target) {
		return extraEffects;
	}

	public String getSVGString(GameCharacter owner) {
		return SVGString;
	}
	
	// Helper methods for sex effects:
	
	public static float getPenetrationArousalPerTurn(GameCharacter target, SexAreaPenetration penetration) {
		float arousal = 0;
		
		if(!Sex.getContactingSexAreas(target, penetration).isEmpty()) {
			arousal+=penetration.getBaseArousalWhenPenetrating();
			
			if(!Sex.hasLubricationTypeFromAnyone(target, penetration)) {
				arousal += penetration.getArousalChangePenetratingDry();
			}
		}
		
		return arousal;
	}
	
	public List<String> getPenetrationModifiersAsStringList(GameCharacter target, SexAreaPenetration penetration) {
		modifiersList.clear();

		if(!Sex.getContactingSexAreas(target, penetration).isEmpty()
				&& !Collections.disjoint(Sex.getContactingSexAreas(target, penetration).get(Sex.getCharacterContactingSexArea(target, penetration).get(0)), Util.newArrayListOfValues(SexAreaPenetration.values()))) {

			modifiersList.add("[style.boldSex(Arousal/turn:)]");

			String targetName = target.isPlayer()?"You":UtilText.parse(target, "[npc.Name]");
			modifiersList.add("+"+penetration.getBaseArousalWhenPenetrating()+" <b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>("+targetName+"</b> - [style.boldSex(Sex)])");
			
			if(!Sex.hasLubricationTypeFromAnyone(target, penetration)) {
				modifiersList.add(penetration.getArousalChangePenetratingDry()+ " <b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>("+targetName+"</b> - [style.boldBad(Dry)])");
			}
			
//			for(GameCharacter penetrator : Sex.getContactingSexAreas(target, penetration).keySet()) {
//				String penetratorName = penetrator.isPlayer()?"You":UtilText.parse(penetrator, "[npc.Name]");
//				
//				if(!Sex.getContactingSexAreas(target, penetration).isEmpty()) {
//					modifiersList.add("+0 <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+penetratorName+"</b> (<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>Sex</b>)");
//				}
//			}
			
		} else {
			modifiersList.add("[style.colourDisabled(No bonuses)]");
		}
		return modifiersList;
	}
	
	public static float getOrificeArousalPerTurnSelf(GameCharacter target, SexAreaOrifice orifice) {
		float arousal = 0;
		
		if(!Sex.getContactingSexAreas(target, orifice).isEmpty()) {
			arousal+=orifice.getBaseArousalWhenPenetrated();
			
			if(Sex.getAreasCurrentlyStretching(target).contains(orifice)) {
				arousal += orifice.getArousalChangePenetratedStretching();
			}
			if(Sex.getAreasTooLoose(target).contains(orifice)) {
				arousal += orifice.getArousalChangePenetratedTooLoose();
			}
			if(!Sex.hasLubricationTypeFromAnyone(target, orifice)) {
				arousal += orifice.getArousalChangePenetratedDry();
			}
		}
		
		return arousal;
	}
	
	public float getOrificeArousalPerTurnPartner(GameCharacter self, GameCharacter target, SexAreaOrifice orifice) {
		float arousal = 0;
		
		if(Sex.getContactingSexAreas(self, orifice).containsKey(target)) {
			for(SexAreaInterface sArea : Sex.getContactingSexAreas(self, orifice).get(target)) {
				if(sArea.isPenetration()) {
					arousal+=((SexAreaPenetration)sArea).getBaseArousalWhenPenetrating();
				} else if(sArea.isOrifice()) {
					arousal+=((SexAreaOrifice)sArea).getBaseArousalWhenPenetrated();
				}
			}
			
			if(Sex.getAreasCurrentlyStretching(self).contains(orifice)) {
				arousal += orifice.getArousalChangePenetratingStretching();
			}
			if(Sex.getAreasTooLoose(self).contains(orifice)) {
				arousal += orifice.getArousalChangePenetratingTooLoose();
			}
			if(!Sex.hasLubricationTypeFromAnyone(self, orifice)) {
				arousal += orifice.getArousalChangePenetratingDry();
			}
		}
		
		return arousal;
	}
	
	public List<String> getOrificeModifiersAsStringList(GameCharacter target, SexAreaOrifice orifice) {
		modifiersList.clear();
		
		modifiersList.add("[style.boldSex(Arousal/turn:)]");

		String targetName = target.isPlayer()?"You":UtilText.parse(target, "[npc.Name]");
		
		if(!Sex.getContactingSexAreas(target, orifice).isEmpty()) {
			modifiersList.add("+"+orifice.getBaseArousalWhenPenetrated()+" (<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>"+targetName+"</b> - [style.boldSex(Sex)])");
			
			if(Sex.getAreasCurrentlyStretching(target).contains(orifice)) {
				modifiersList.add((orifice.getArousalChangePenetratedStretching()>0?"+":"")
						+orifice.getArousalChangePenetratedStretching()+" (<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>"+targetName+"</b> - [style.boldBad(Stretching)])");
			}
			if(Sex.getAreasTooLoose(target).contains(orifice)) {
				modifiersList.add((orifice.getArousalChangePenetratedTooLoose()>0?"+":"")
						+orifice.getArousalChangePenetratedTooLoose()+" (<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>"+targetName+"</b> - [style.boldBad(Too loose)])");
			}
			if(!Sex.hasLubricationTypeFromAnyone(target, orifice)) {
				modifiersList.add((orifice.getArousalChangePenetratedDry()>0?"+":"")
						+orifice.getArousalChangePenetratedDry()+" (<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>"+targetName+"</b> - [style.boldBad(Dry)])");
			}
			
			for(GameCharacter penetrator : Sex.getContactingSexAreas(target, orifice).keySet()) {
				String penetratorName = penetrator.isPlayer()?"You":UtilText.parse(penetrator, "[npc.Name]");
				
				if(!Sex.getContactingSexAreas(target, orifice).isEmpty()) {
					modifiersList.add("+"+getOrificeArousalPerTurnPartner(target, penetrator, orifice)+" (<b style='color:"+penetrator.getFemininity().getColour().toWebHexString()+";'>"+penetratorName+"</b> - [style.boldSex(Sex)])");
					
					if(Sex.getAreasCurrentlyStretching(target).contains(orifice)) {
						modifiersList.add((orifice.getArousalChangePenetratingStretching()>0?"+":"")
								+orifice.getArousalChangePenetratingStretching()+" (<b style='color:"+penetrator.getFemininity().getColour().toWebHexString()+";'>"+penetratorName+"</b> - [style.boldGood(Tight)])");
					}
					if(Sex.getAreasTooLoose(target).contains(orifice)) {
						modifiersList.add((orifice.getArousalChangePenetratingTooLoose()>0?"+":"")
								+orifice.getArousalChangePenetratingTooLoose()+" (<b style='color:"+penetrator.getFemininity().getColour().toWebHexString()+";'>"+penetratorName+"</b> - [style.boldBad(Too loose)])");
					}
					if(!Sex.hasLubricationTypeFromAnyone(target, orifice)) {
						modifiersList.add((orifice.getArousalChangePenetratingDry()>0?"+":"")
								+orifice.getArousalChangePenetratingDry()+" (<b style='color:"+penetrator.getFemininity().getColour().toWebHexString()+";'>"+penetratorName+"</b> - [style.boldBad(Dry)])");
					}
				}
			}
			
		} else {
			modifiersList.add("[style.colourDisabled(No bonuses)]");
		}
		return modifiersList;
	}
	
	public void appendPenetrationAdditionGenericDescriptions(GameCharacter owner, SexAreaPenetration penetration, String penetrationName, StringBuilder stringBuilderToAppendTo) {
		
		if(!Sex.hasLubricationTypeFromAnyone(owner, penetration)) {
			stringBuilderToAppendTo.append("<br/>"+penetrationName+" "+(penetration.isPlural()?"are":"is")+" <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>dry</b>!");
			
		} else {
			stringBuilderToAppendTo.append("<br/>"+penetrationName+" "+(penetration.isPlural()?"have":"has")+" been <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>lubricated</b> by:<br/>");
			int i=0;
			List<String> lubricants = new ArrayList<>();
			for(GameCharacter lubricantProvider : Sex.getAllParticipants()) {
				for(LubricationType lt : Sex.getWetAreas(owner).get(penetration).get(lubricantProvider)) {
					if(i==0) {
						lubricants.add(lubricantProvider==null
								?Util.capitaliseSentence(lt.getName(lubricantProvider))
								:UtilText.parse(lubricantProvider, "[npc.NamePos] "+lt.getName(lubricantProvider)));
					} else {
						lubricants.add(lubricantProvider==null
								?lt.getName(lubricantProvider)
								:UtilText.parse(lubricantProvider, "[npc.namePos] "+lt.getName(lubricantProvider)));
					}
					i++;
				}
			}
			stringBuilderToAppendTo.append(Util.stringsToStringList(lubricants, false)+".");
		}
		stringBuilderToAppendTo.append("</p>");
	}
	
	public void appendOrificeAdditionGenericDescriptions(GameCharacter owner, SexAreaOrifice orificeType, String orificeName, StringBuilder stringBuilderToAppendTo) {
		
		if(Sex.getAreasCurrentlyStretching(owner).contains(orificeType)) {
			if(Sex.getFirstContactingSexAreaPenetration(owner, orificeType)==null) {
				stringBuilderToAppendTo.append("<br/>"+orificeName+" has been <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>stretched</b>!");
			} else {
				stringBuilderToAppendTo.append("<br/>"+orificeName+" "+(orificeType.isPlural()?"are":"is")+" being <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>stretched</b>!");
			}
			
		} else if(Sex.getAreasTooLoose(owner).contains(orificeType)) {
			stringBuilderToAppendTo.append("<br/>"+orificeName+" "+(orificeType.isPlural()?"are":"is")+" <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>too loose</b>!");
			
		} else if(Sex.getAreasStretched(owner).contains(orificeType)) {
			stringBuilderToAppendTo.append("<br/>"+orificeName+" has been <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>stretched</b>!");
			
		} else {
			stringBuilderToAppendTo.append("<br/><b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No stretch effect.</b>");
		}
		
		
		if(!Sex.hasLubricationTypeFromAnyone(owner, orificeType)) {
			stringBuilderToAppendTo.append("<br/>"+orificeName+" "+(orificeType.isPlural()?"are":"is")+" <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>dry</b>!");
			
		} else {
			stringBuilderToAppendTo.append("<br/>"+orificeName+" "+(orificeType.isPlural()?"have":"has")+" been <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>lubricated</b> by:<br/>");
			int i=0;
			List<String> lubricants = new ArrayList<>();
			for(GameCharacter lubricantProvider : Sex.getAllParticipants()) {
				for(LubricationType lt : Sex.getWetAreas(owner).get(orificeType).get(lubricantProvider)) {
					if(i==0) {
						lubricants.add(lubricantProvider==null
								?Util.capitaliseSentence(lt.getName(lubricantProvider))
								:UtilText.parse(lubricantProvider, "[npc.NamePos] "+lt.getName(lubricantProvider)));
					} else {
						lubricants.add(lubricantProvider==null
								?lt.getName(lubricantProvider)
								:UtilText.parse(lubricantProvider, "[npc.namePos] "+lt.getName(lubricantProvider)));
					}
					i++;
				}
			}
			stringBuilderToAppendTo.append(Util.stringsToStringList(lubricants, false)+".");
		}
		stringBuilderToAppendTo.append("</p>");
	}
	
	public String getPenetrationSVGString(GameCharacter owner, SexAreaInterface penetration, String baseSVG) {
		SVGImageSB = new StringBuilder();

		if(!Sex.getContactingSexAreas(owner, penetration).isEmpty()) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getActiveSexBackground()+"</div>");
		}
		
		SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+baseSVG+"</div>");
		
		if(!Sex.getContactingSexAreas(owner, penetration).isEmpty()) {
			SexAreaPenetration firstPenetration = null;
			outerloop:
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(owner, penetration).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						firstPenetration = (SexAreaPenetration) sArea;
						break outerloop;
					}
				}
			}
			if(firstPenetration!=null) {
				switch(firstPenetration){
					case FINGER:
						SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeFinger()+"</div>");
						break;
					case PENIS:
						SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypePenis()+"</div>");
						break;
					case TAIL:
						SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTail()+"</div>");
						break;
					case TONGUE:
						SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTongue()+"</div>");
						break;
					default:
						break;
				}
			}
		}
		
		if(!Sex.hasLubricationTypeFromAnyone(owner, penetration)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationDry()+"</div>");
		} else {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationWet()+"</div>");
		}
		
		return SVGImageSB.toString();
	}
	
	public String getOrificeSVGString(GameCharacter owner, SexAreaInterface orifice, String baseSVG) {
		SVGImageSB = new StringBuilder();

		if(!Sex.getContactingSexAreas(owner, orifice).isEmpty()) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getActiveSexBackground()+"</div>");
		}
		
		SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+baseSVG+"</div>");
		
		if(!Sex.getContactingSexAreas(owner, orifice).isEmpty()) {
			int rightOffset = 0;
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(owner, orifice).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:"+rightOffset+"%;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeFinger()+"</div>");
								break;
							case PENIS:
								SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:"+rightOffset+"%;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypePenis()+"</div>");
								break;
							case TAIL:
								SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:"+rightOffset+"%;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTail()+"</div>");
								break;
							case TONGUE:
								SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:"+rightOffset+"%;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTongue()+"</div>");
								break;
							case CLIT:
								SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:"+rightOffset+"%;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeClit()+"</div>");
								break;
							case FOOT:
								SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:"+rightOffset+"%;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeFoot()+"</div>");
								break;
							case TENTACLE:
								break;
						}
						rightOffset+=8;
					}
				}
			}
		}
		
		if(Sex.getAreasCurrentlyStretching(owner).contains(orifice)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationStretching()+"</div>");
		}
		
		if(Sex.getAreasTooLoose(owner).contains(orifice)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationTooLoose()+"</div>");
		}
		
		if(!Sex.hasLubricationTypeFromAnyone(owner, orifice)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationDry()+"</div>");
		} else {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationWet()+"</div>");
		}
		
		return SVGImageSB.toString();
	}
	
	public String getCreampieSVGString(GameCharacter owner, SexAreaOrifice orifice) {
		SVGImageSB = new StringBuilder();
		
		boolean justCum = owner.isOnlyCumInArea(orifice);
		
		if(isCumEffectPositive(owner)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"
									+ (justCum
											?SVGImages.SVG_IMAGE_PROVIDER.getCreampieMasochist()
											:SVGImages.SVG_IMAGE_PROVIDER.getFluidIngestedMasochist())
								+"</div>");
		} else {
			SVGImageSB.append((justCum
					?SVGImages.SVG_IMAGE_PROVIDER.getCreampie()
					:SVGImages.SVG_IMAGE_PROVIDER.getFluidIngested()));
		}
		
		switch(orifice) {
			case ANUS:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus()+"</div>");
				break;
			case ASS:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus()+"</div>");
				break;
			case BREAST:
				if(owner.hasBreasts()) {
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreasts()+"</div>");
				} else {
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsFlat()+"</div>");
				}
				break;
			case NIPPLE:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple()+"</div>");
				break;
			case BREAST_CROTCH:
				if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUdders()+"</div>");
				} else {
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsCrotch()+"</div>");
				}
				break;
			case NIPPLE_CROTCH:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNippleCrotch()+"</div>");
				break;
			case MOUTH:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMouth()+"</div>");
				break;
			case THIGHS:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaThighs()+"</div>");
				break;
			case URETHRA_PENIS:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraPenis()+"</div>");
				break;
			case URETHRA_VAGINA:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraVagina()+"</div>");
				break;
			case VAGINA:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina()+"</div>");
				break;
		}
		
		return SVGImageSB.toString();
	}
	
	private static boolean isCumEffectPositive(GameCharacter target) {
		return target.hasFetish(Fetish.FETISH_MASOCHIST) || target.hasFetish(Fetish.FETISH_CUM_ADDICT);
	}
	
	public static boolean isExposedParts(GameCharacter target, boolean requiresBreastsExposed, boolean requiresGenitalsExposed) {
		boolean breastsExposed = (((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaVisible(CoverableArea.NIPPLES)) || (target.hasBreastsCrotch() && target.isCoverableAreaVisible(CoverableArea.NIPPLES_CROTCH)));
		boolean genitalsExposed = (target.isCoverableAreaVisible(CoverableArea.ANUS) || (target.isCoverableAreaVisible(CoverableArea.PENIS) && target.hasPenis()) || (target.isCoverableAreaVisible(CoverableArea.VAGINA) && target.hasVagina()));
		
		return !Main.game.isInSex()
				&& target.getLegConfiguration().isGenitalsExposed(target)
				&& (requiresBreastsExposed?breastsExposed:!breastsExposed)
				&& (requiresGenitalsExposed?genitalsExposed:!genitalsExposed);
	}
	
	public static String getExposedPartsNamesList(GameCharacter owner) {
		List<String> names = new ArrayList<>();
		
		if(owner.hasBreasts() && owner.isCoverableAreaVisible(CoverableArea.NIPPLES)) {
			names.add("breasts");
		} else if (owner.isFeminine() && owner.isCoverableAreaVisible(CoverableArea.NIPPLES)) {
			names.add("nipples");
		}
		if(owner.hasBreastsCrotch()
				&& Main.getProperties().udders>0
				&& owner.isCoverableAreaVisible(CoverableArea.NIPPLES_CROTCH)) {
			names.add(UtilText.parse(owner, "[npc.crotchBoobs]"));
		}
		if(owner.isCoverableAreaVisible(CoverableArea.ANUS)) {
			names.add("asshole");
		}
		if(owner.hasPenis() && owner.isCoverableAreaVisible(CoverableArea.PENIS)) {
			names.add("cock");
		}
		if(owner.hasVagina() && owner.isCoverableAreaVisible(CoverableArea.VAGINA)) {
			names.add("pussy");
		}
		if(!owner.hasPenis() && !owner.hasVagina() && owner.isCoverableAreaVisible(CoverableArea.VAGINA)) {
			names.add("genderless mound");
		}
		
		return Util.stringsToStringChoice(names, false);
	}
	
	public static String getExposedStatus(GameCharacter owner, String baseSVG) {
		SVGImageSB = new StringBuilder();
		
		SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;top:0;'>"+baseSVG+"</div>");

		
		boolean breastsExposed = owner.hasBreasts() && owner.isCoverableAreaVisible(CoverableArea.NIPPLES);
		boolean nipplesExposed = ! breastsExposed && owner.isFeminine() && owner.isCoverableAreaVisible(CoverableArea.NIPPLES);
		boolean crotchBoobsExposed = owner.hasBreastsCrotch()
				&& Main.getProperties().udders>0
				&& owner.isCoverableAreaVisible(CoverableArea.NIPPLES_CROTCH);

		boolean anusExposed = owner.isCoverableAreaVisible(CoverableArea.ANUS);
		
		boolean penisExposed = owner.hasPenis() && owner.isCoverableAreaVisible(CoverableArea.PENIS);
		boolean vaginaExposed = owner.hasVagina() && owner.isCoverableAreaVisible(CoverableArea.VAGINA);
		boolean moundExposed = !owner.hasPenis() && !owner.hasVagina() && owner.isCoverableAreaVisible(CoverableArea.VAGINA);

		int exposedAreas = 0;
		if(moundExposed) {
			exposedAreas++;
		} else {
			exposedAreas+= (penisExposed?1:0) + (vaginaExposed?1:0);
		}
		exposedAreas+= (anusExposed?1:0) + (breastsExposed || nipplesExposed?1:0) + (crotchBoobsExposed?1:0);
		
		int size = Math.min(50, (100/(exposedAreas+1)));
		int marginTop = (100-(exposedAreas*size))/2;
		int marginLeft = (50-size)/2;

		if(breastsExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreasts()+"</div>");
			marginTop+=size;
		} else if (nipplesExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsFlat()+"</div>");
			marginTop+=size;
		}
		if(crotchBoobsExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsCrotch()+"</div>");
			marginTop+=size;
		}
		if(anusExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus()+"</div>");
			marginTop+=size;
		}
		if(moundExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMound()+"</div>");
			marginTop+=size;
		}
		if(penisExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypePenis()+"</div>");
			marginTop+=size;
		}
		if(vaginaExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina()+"</div>");
			marginTop+=size;
		}
		
		return SVGImageSB.toString();
	}
	
	public static String getRecoveringOrificeStatus(GameCharacter owner, String baseSVG) {
		SVGImageSB = new StringBuilder();
		
		SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;top:25%;'>"+baseSVG+"</div>");

		boolean vaginaRecovering = owner.hasVagina() && owner.getVaginaRawCapacityValue()!=owner.getVaginaStretchedCapacity();
		boolean anusRecovering = owner.getAssRawCapacityValue()!=owner.getAssStretchedCapacity();
		boolean nipplesRecovering = owner.getNippleRawCapacityValue()!=owner.getNippleStretchedCapacity();
		boolean nipplesCrotchRecovering = owner.hasBreastsCrotch()
				&& Main.getProperties().udders>0
				&& owner.getNippleCrotchRawCapacityValue()!=owner.getNippleCrotchStretchedCapacity();
		boolean penileUrethraRecovering = owner.hasPenis() && owner.getPenisRawCapacityValue()!=owner.getPenisStretchedCapacity();
		boolean vaginalUrethraRecovering = owner.hasVagina() && owner.getVaginaUrethraRawCapacityValue()!=owner.getVaginaUrethraStretchedCapacity();
		
		int exposedAreas = (vaginaRecovering?1:0) + (anusRecovering?1:0) + (nipplesRecovering?1:0) + (nipplesCrotchRecovering?1:0) + (penileUrethraRecovering?1:0) + (vaginalUrethraRecovering?1:0);
		
		int size = Math.min(50, (100/(exposedAreas+1)));
		int marginTop = (100-(exposedAreas*size))/2;
		int marginLeft = (50-size)/2;

		if(vaginaRecovering) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina()+"</div>");
			marginTop+=size;
		}
		if(anusRecovering) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus()+"</div>");
			marginTop+=size;
		}
		if(nipplesRecovering) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple()+"</div>");
			marginTop+=size;
		}
		if(nipplesCrotchRecovering) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNippleCrotch()+"</div>");
			marginTop+=size;
		}
		if(penileUrethraRecovering) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraPenis()+"</div>");
			marginTop+=size;
		}
		if(vaginalUrethraRecovering) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraVagina()+"</div>");
			marginTop+=size;
		}
		
		return SVGImageSB.toString();
	}
	
}
