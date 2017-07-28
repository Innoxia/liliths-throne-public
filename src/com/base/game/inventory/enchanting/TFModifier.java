package com.base.game.inventory.enchanting;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.base.game.character.attributes.Attribute;
import com.base.game.inventory.Rarity;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.7
 * @version 0.1.8
 * @author Innoxia
 */
public enum TFModifier {
	
	NONE("Empty",
		"No modifier",
		"nullification",
		"modifier_circle",
		Colour.TEXT_GREY,
		Rarity.COMMON),
	
	// Pos/Neg
	
	NEGATIVE_WEAK("minor negative",
			"Applies a minor negative effect.",
			"reduced",
			"modifier_circle_negative",
			Colour.GENERIC_BAD,
			Rarity.COMMON),
	NEGATIVE_AVERAGE("average negative",
			"Applies a negative effect.",
			"reduced",
			"modifier_circle_negative",
			Colour.GENERIC_BAD,
			Rarity.UNCOMMON),
	NEGATIVE_STRONG("major negative",
			"Applies a major negative effect.",
			"reduced",
			"modifier_circle_negative",
			Colour.GENERIC_BAD,
			Rarity.RARE),
	
	POSITIVE_WEAK("minor positive",
			"Applies a minor positive effect.",
			"boosted",
			"modifier_circle_positive",
			Colour.GENERIC_GOOD,
			Rarity.COMMON),
	POSITIVE_AVERAGE("average positive",
			"Applies a positive effect.",
			"boosted",
			"modifier_circle_positive",
			Colour.GENERIC_GOOD,
			Rarity.UNCOMMON),
	POSITIVE_STRONG("major positive",
			"Applies a major positive effect.",
			"boosted",
			"modifier_circle_positive",
			Colour.GENERIC_GOOD,
			Rarity.RARE),

	REMOVAL("removal",
			"Applies an effect such that the primary modifier's body part is removed.",
			"vanishing",
			"modifier_circle_removal",
			Colour.GENERIC_TERRIBLE,
			Rarity.RARE),
	
	// Attributes:
	
	STRENGTH(AttributeCategory.STRENGTH,
			Attribute.STRENGTH,
			"Applies an effect related to the primary attribute 'Strength'.",
			"modifier_circle_strength",
			Rarity.EPIC),
	
	INTELLIGENCE(AttributeCategory.INTELLIGENCE,
			Attribute.INTELLIGENCE,
			"Applies an effect related to the primary attribute 'Intelligence'.",
			"modifier_circle_intelligence",
			Rarity.EPIC),
	
	FITNESS(AttributeCategory.FITNESS,
			Attribute.FITNESS,
			"Applies an effect related to the primary attribute 'Fitness'.",
			"modifier_circle_fitness",
			Rarity.EPIC),
	
	CORRUPTION(AttributeCategory.CORRUPTION,
			Attribute.CORRUPTION,
			"Applies an effect related to the primary attribute 'Corruption'.",
			"modifier_circle_corruption",
			Rarity.EPIC),
	
	
	FERTILITY(AttributeCategory.CORRUPTION,
			Attribute.FERTILITY,
			"Applies an effect related to the secondary attribute 'Fertility'.",
			"modifier_circle_fertility",
			Rarity.RARE),

	VIRILITY(AttributeCategory.CORRUPTION,
			Attribute.VIRILITY,
			"Applies an effect related to the secondary attribute 'Virility'.",
			"modifier_circle_virility",
			Rarity.RARE),
	
	SPELL_COST_MODIFIER(AttributeCategory.INTELLIGENCE,
			Attribute.SPELL_COST_MODIFIER,
			"Applies an effect related to the secondary attribute 'Spell cost reduction'.",
			"modifier_circle",
			Rarity.RARE),
	
	
	CRITICAL_CHANCE(AttributeCategory.FITNESS,
			Attribute.CRITICAL_CHANCE,
			"Applies an effect related to the secondary attribute 'Critical chance'.",
			"modifier_circle",
			Rarity.RARE),
	
	CRITICAL_DAMAGE(AttributeCategory.STRENGTH,
			Attribute.CRITICAL_DAMAGE,
			"Applies an effect related to the secondary attribute 'Critical damage'.",
			"modifier_circle",
			Rarity.RARE),
	
	
	DAMAGE_ATTACK(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_ATTACK,
			"Applies an effect related to the secondary attribute 'Attack damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_SPELLS(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_SPELLS,
			"Applies an effect related to the secondary attribute 'Spell damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_MANA(AttributeCategory.CORRUPTION,
			Attribute.DAMAGE_MANA,
			"Applies an effect related to the secondary attribute 'Willpower damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_STAMINA(AttributeCategory.FITNESS,
			Attribute.DAMAGE_STAMINA,
			"Applies an effect related to the secondary attribute 'Stamina damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_PHYSICAL(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_PHYSICAL,
			"Applies an effect related to the secondary attribute 'Physical damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_FIRE(AttributeCategory.FITNESS,
			Attribute.DAMAGE_FIRE,
			"Applies an effect related to the secondary attribute 'Fire damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_ICE(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_ICE,
			"Applies an effect related to the secondary attribute 'Ice damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_POISON(AttributeCategory.CORRUPTION,
			Attribute.DAMAGE_POISON,
			"Applies an effect related to the secondary attribute 'Posion damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_PURE(AttributeCategory.CORRUPTION,
			Attribute.DAMAGE_PURE,
			"Applies an effect related to the secondary attribute 'Global damage'.",
			"modifier_circle_damage",
			Rarity.LEGENDARY),
	
	
	RESISTANCE_ATTACK(AttributeCategory.STRENGTH,
			Attribute.RESISTANCE_ATTACK,
			"Applies an effect related to the secondary attribute 'Attack resistance'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_SPELLS(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_SPELLS,
			"Applies an effect related to the secondary attribute 'Spell resistance'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_MANA(AttributeCategory.CORRUPTION,
			Attribute.RESISTANCE_MANA,
			"Applies an effect related to the secondary attribute 'Willpower resistance'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_STAMINA(AttributeCategory.FITNESS,
			Attribute.RESISTANCE_STAMINA,
			"Applies an effect related to the secondary attribute 'Stamina resistance'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_PHYSICAL(AttributeCategory.STRENGTH,
			Attribute.RESISTANCE_PHYSICAL,
			"Applies an effect related to the secondary attribute 'Physical resistance'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_FIRE(AttributeCategory.FITNESS,
			Attribute.RESISTANCE_FIRE,
			"Applies an effect related to the secondary attribute 'Fire resistance'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_ICE(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_ICE,
			"Applies an effect related to the secondary attribute 'Ice resistance'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_POISON(AttributeCategory.CORRUPTION,
			Attribute.RESISTANCE_POISON,
			"Applies an effect related to the secondary attribute 'Poison resistance'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_PURE(AttributeCategory.CORRUPTION,
			Attribute.RESISTANCE_PURE,
			"Applies an effect related to the secondary attribute 'Global resistance'.",
			"modifier_circle_resistance",
			Rarity.LEGENDARY),
	
	
	// Racial parts:
	
	/*
	 * Arm
	 * Ass
	 * Breast
	 * Ear
	 * Eye
	 * Face
	 * Hair
	 * Horn
	 * Leg
	 * Penis
	 * Skin
	 * Tail
	 * Tongue
	 * Vagina
	 * Wing
	 */
	
	TF_ARMS("arms",
			"Applies a transformative effect to your arms.",
			"arms",
			"modifier_circle_tf_arm",
			Colour.TRANSFORMATION_LESSER,
			Rarity.RARE),
	
	TF_ASS("ass",
			"Applies a transformative effect to your ass.",
			"asses",
			"modifier_circle_tf_ass",
			Colour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_BREASTS("breasts",
			"Applies a transformative effect to your breasts.",
			"breasts",
			"modifier_circle_tf_breast",
			Colour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_CORE("core",
			"Applies a transformative effect to your body.",
			"core",
			"modifier_circle",
			Colour.TRANSFORMATION_GREATER,
			Rarity.RARE),
	
	TF_EARS("ears",
			"Applies a transformative effect to your ears.",
			"ears",
			"modifier_circle_tf_ear",
			Colour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_EYES("eyes",
			"Applies a transformative effect to your eyes.",
			"eyes",
			"modifier_circle_tf_eye",
			Colour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_FACE("face",
			"Applies a transformative effect to your face.",
			"faces",
			"modifier_circle_tf_face",
			Colour.TRANSFORMATION_GREATER,
			Rarity.EPIC),
	
	TF_HAIR("hair",
			"Applies a transformative effect to your hair.",
			"hair",
			"modifier_circle_tf_hair",
			Colour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_HORNS("horns",
			"Applies a transformative effect to your horns.",
			"horns",
			"modifier_circle_tf_horn",
			Colour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_LEGS("legs",
			"Applies a transformative effect to your legs.",
			"legs",
			"modifier_circle_tf_leg",
			Colour.TRANSFORMATION_LESSER,
			Rarity.RARE),
	
	TF_PENIS("penis",
			"Applies a transformative effect to your penis.",
			"cocks",
			"modifier_circle_tf_penis",
			Colour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_SKIN("skin",
			"Applies a transformative effect to your skin.",
			"skin",
			"modifier_circle",
			Colour.TRANSFORMATION_GREATER,
			Rarity.EPIC),
	
	TF_TAIL("tail",
			"Applies a transformative effect to your tail.",
			"tails",
			"modifier_circle_tf_tail",
			Colour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	TF_VAGINA("vagina",
			"Applies a transformative effect to your vagina.",
			"pussies",
			"modifier_circle_tf_vagina",
			Colour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_WINGS("wings",
			"Applies a transformative effect to your wings.",
			"wings",
			"modifier_circle_tf_wing",
			Colour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
	// Body part modifiers:
	
	/*
	 * Size +/-
	 * Capacity +/-
	 * Wetness +/-
	 * Elasticity +/-
	 * 
	 * Special:
	 *  Femininity +/-
	 *  
	 */
	
	TF_MOD_SIZE_GROW("growth",
			"Applies an effect related to the growth of a certain body part.",
			"growing",
			"modifier_circle_size_increase",
			Colour.GENERIC_GOOD,
			Rarity.COMMON),
	
	TF_MOD_SIZE_SHRINK("shrink",
			"Applies an effect related to shrinking a certain body part.",
			"shrinking",
			"modifier_circle_size_decrease",
			Colour.GENERIC_BAD,
			Rarity.COMMON),
	
	TF_MOD_CAPACITY_INCREASE("loosen",
			"Applies an effect related to loosening an orifice.",
			"loosening",
			"modifier_circle_capacity_increase",
			Colour.CLOTHING_PINK,
			Rarity.COMMON),
	
	TF_MOD_CAPACITY_DECREASE("tighten",
			"Applies an effect related to tightening an orifice.",
			"tightening",
			"modifier_circle_capacity_decrease",
			Colour.CLOTHING_PINK_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_WETNESS_INCREASE("wetness",
			"Applies an effect related to increasing the wetness of an orifice, or the fluid production of a body part.",
			"wet",
			"modifier_circle_wetness_increase",
			Colour.CLOTHING_BLUE,
			Rarity.COMMON),
	
	TF_MOD_WETNESS_DECREASE("dryness",
			"Applies an effect related to decreasing the wetness of an orifice, or the fluid production of a body part.",
			"dry",
			"modifier_circle_wetness_decrease",
			Colour.CLOTHING_TAN,
			Rarity.COMMON),
	
	TF_MOD_ELASTICITY_INCREASE("elasticity",
			"Applies an effect related to increasing the elasticity of an orifice.",
			"elastic",
			"modifier_circle_elasticity_increase",
			Colour.GENERIC_GOOD,
			Rarity.COMMON),
	
	TF_MOD_ELASTICITY_DECREASE("rigidity",
			"Applies an effect related to decreasing the elasticity of an orifice.",
			"rigid",
			"modifier_circle_elasticity_decrease",
			Colour.GENERIC_BAD,
			Rarity.COMMON),
	
	// More powerful variants:
	
	TF_MOD_SIZE_GROW_STRONG("major growth",
			"Applies a very strong effect related to the growth of a certain body part.",
			"growing",
			"modifier_circle_size_increase",
			Colour.GENERIC_GOOD,
			Rarity.UNCOMMON),
	
	TF_MOD_SIZE_SHRINK_STRONG("major shrink",
			"Applies a very strong effect related to shrinking a certain body part.",
			"shrinking",
			"modifier_circle_size_decrease",
			Colour.GENERIC_BAD,
			Rarity.UNCOMMON),
	
	TF_MOD_CAPACITY_INCREASE_STRONG("major loosen",
			"Applies a very strong effect related to loosening an orifice.",
			"loosening",
			"modifier_circle_capacity_increase",
			Colour.CLOTHING_PINK,
			Rarity.UNCOMMON),
	
	TF_MOD_CAPACITY_DECREASE_STRONG("major tighten",
			"Applies a very strong effect related to tightening an orifice.",
			"tightening",
			"modifier_circle_capacity_decrease",
			Colour.CLOTHING_PINK_LIGHT,
			Rarity.UNCOMMON),
	
	TF_MOD_WETNESS_INCREASE_STRONG("major wetness",
			"Applies a very strong effect related to increasing the wetness of an orifice, or the fluid production of a body part.",
			"wet",
			"modifier_circle_wetness_increase",
			Colour.CLOTHING_BLUE,
			Rarity.UNCOMMON),
	
	TF_MOD_WETNESS_DECREASE_STRONG("major dryness",
			"Applies a very strong effect related to decreasing the wetness of an orifice, or the fluid production of a body part.",
			"dry",
			"modifier_circle_wetness_decrease",
			Colour.CLOTHING_TAN,
			Rarity.UNCOMMON),
	
	TF_MOD_ELASTICITY_INCREASE_STRONG("major elasticity",
			"Applies a very strong effect related to increasing the elasticity of an orifice.",
			"elastic",
			"modifier_circle_elasticity_increase",
			Colour.GENERIC_GOOD,
			Rarity.UNCOMMON),
	
	TF_MOD_ELASTICITY_DECREASE_STRONG("major rigidity",
			"Applies a very strong effect related to decreasing the elasticity of an orifice.",
			"rigid",
			"modifier_circle_elasticity_decrease",
			Colour.GENERIC_BAD,
			Rarity.UNCOMMON),
	
	
	// Specials:
	
	TF_MOD_HIP_SIZE_INCREASE("larger hips",
			"Applies an effect such that the user's hips are widened.",
			"big hips",
			"modifier_circle_positive",
			Colour.GENERIC_GOOD,
			Rarity.COMMON){
		public boolean isSoloDescriptor() {
			return true;
		}
	},
	
	TF_MOD_HIP_SIZE_DECREASE("smaller hips",
			"Applies an effect such that the user's hips are narrowed.",
			"small hips",
			"modifier_circle_negative",
			Colour.GENERIC_BAD,
			Rarity.COMMON){
		public boolean isSoloDescriptor() {
			return true;
		}
	},
	
	TF_MOD_BREAST_ROWS_INCREASE("add breast row",
			"Applies an effect such that the user grows an extra row of breasts.",
			"multi-breasts",
			"modifier_circle_positive",
			Colour.GENERIC_GOOD,
			Rarity.EPIC){
		public boolean isSoloDescriptor() {
			return true;
		}
	},
	
	TF_MOD_BREAST_ROWS_DECREASE("remove breast row",
			"Applies an effect such that the user loses one of their extra rows of breasts.",
			"less breasts",
			"modifier_circle_negative",
			Colour.GENERIC_BAD,
			Rarity.COMMON){
		public boolean isSoloDescriptor() {
			return true;
		}
	},
	
	TF_MOD_TESTICLE_SIZE_INCREASE("larger testicles",
			"Applies an effect such that the user's testicles grow in size.",
			"bigger balls",
			"modifier_circle_positive",
			Colour.GENERIC_GOOD,
			Rarity.COMMON){
		public boolean isSoloDescriptor() {
			return true;
		}
	},
	
	TF_MOD_TESTICLE_SIZE_DECREASE("smaller testicles",
			"Applies an effect such that the user's testicles shrink.",
			"smaller balls",
			"modifier_circle_negative",
			Colour.GENERIC_BAD,
			Rarity.COMMON){
		public boolean isSoloDescriptor() {
			return true;
		}
	},
	
	TF_MOD_FEMININITY_INCREASE("femininity",
			"Applies an effect such that the user becomes more feminine.",
			"feminine",
			"modifier_circle_femininity",
			Colour.FEMININE,
			Rarity.COMMON),
	
	TF_MOD_FEMININITY_DECREASE("masculinity",
			"Applies an effect such that the user becomes more masculine.",
			"masculine",
			"modifier_circle_masculinity",
			Colour.MASCULINE,
			Rarity.COMMON),

	// More powerful variants:
	
	TF_MOD_FEMININITY_INCREASE_STRONG("major femininity",
			"Applies a very strong effect such that the user becomes more feminine.",
			"feminine",
			"modifier_circle_femininity",
			Colour.FEMININE,
			Rarity.UNCOMMON),
	
	TF_MOD_FEMININITY_DECREASE_STRONG("major masculinity",
			"Applies a very strong effect such that the user becomes more masculine.",
			"masculine",
			"modifier_circle_masculinity",
			Colour.MASCULINE,
			Rarity.UNCOMMON)
	
	;

	
	private static List<TFModifier>
		TFModStrengthList = new ArrayList<>(),
		TFModIntelligenceList = new ArrayList<>(),
		TFModFitnessList = new ArrayList<>(),
		TFModCorruptionList = new ArrayList<>(),
		TFModSexualList = new ArrayList<>(),
		TFModNegPosList = new ArrayList<>(),
		TFRacialBodyPartsList = new ArrayList<>();
	
	static {

		TFModStrengthList.add(NONE);
		TFModIntelligenceList.add(NONE);
		TFModFitnessList.add(NONE);
		TFModCorruptionList.add(NONE);
		TFModSexualList.add(NONE);
		
		for(TFModifier tfMod : TFModifier.values()) {
			if(tfMod.getAttributeCategory()!=null) {
				switch(tfMod.getAttributeCategory()) {
					case CORRUPTION:
						TFModCorruptionList.add(tfMod);
						break;
					case FITNESS:
						TFModFitnessList.add(tfMod);
						break;
					case INTELLIGENCE:
						TFModIntelligenceList.add(tfMod);
						break;
					case STRENGTH:
						TFModStrengthList.add(tfMod);
						break;
				}
			}
		}
		
		TFModSexualList.add(FITNESS);
		TFModSexualList.add(FERTILITY);
		TFModSexualList.add(VIRILITY);
		TFModSexualList.add(DAMAGE_MANA);
		TFModSexualList.add(RESISTANCE_MANA);
		
		// Body parts:
		
		TFRacialBodyPartsList.add(NONE);
		TFRacialBodyPartsList.add(TF_FACE);
		TFRacialBodyPartsList.add(TF_SKIN);
		TFRacialBodyPartsList.add(TF_CORE);
		
		TFRacialBodyPartsList.add(TF_ARMS);
		TFRacialBodyPartsList.add(TF_LEGS);
		
		TFRacialBodyPartsList.add(TF_EARS);
		TFRacialBodyPartsList.add(TF_EYES);
		TFRacialBodyPartsList.add(TF_HAIR);
		TFRacialBodyPartsList.add(TF_HORNS);
		TFRacialBodyPartsList.add(TF_TAIL);
		TFRacialBodyPartsList.add(TF_WINGS);
		
		TFRacialBodyPartsList.add(TF_ASS);
		TFRacialBodyPartsList.add(TF_BREASTS);
		TFRacialBodyPartsList.add(TF_PENIS);
		TFRacialBodyPartsList.add(TF_VAGINA);
		
		TFModNegPosList.add(NONE);
		TFModNegPosList.add(NEGATIVE_WEAK);
		TFModNegPosList.add(NEGATIVE_AVERAGE);
		TFModNegPosList.add(NEGATIVE_STRONG);
		TFModNegPosList.add(POSITIVE_WEAK);
		TFModNegPosList.add(POSITIVE_AVERAGE);
		TFModNegPosList.add(POSITIVE_STRONG);
		
	}
	
	
	private enum AttributeCategory {
		STRENGTH,
		FITNESS,
		INTELLIGENCE,
		CORRUPTION;
	}
	
	private AttributeCategory attributeCategory;
	private Attribute associatedAttribute;
	private String name, description, descriptor, SVGString;
	private Colour colour;
	private Rarity rarity;
	
	private TFModifier(AttributeCategory attributeCategory, Attribute associatedAttribute, String description, String SVGString, Rarity rarity) {
		this.attributeCategory=attributeCategory;
		this.associatedAttribute=associatedAttribute;
		this.name = associatedAttribute.getName();
		this.description = description;
		this.descriptor = associatedAttribute.getPositiveEnchantment();
		this.colour = associatedAttribute.getColour();
		this.rarity=rarity;
		
		// Set this item's file image:
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/base/res/crafting/" + SVGString + ".svg");
			String s = Util.inputStreamToString(is);

			s = s.replaceAll("#ff2a2a", this.colour.getShades()[0]);
			s = s.replaceAll("#ff5555", this.colour.getShades()[1]);
			s = s.replaceAll("#ff8080", this.colour.getShades()[2]);
			s = s.replaceAll("#ffaaaa", this.colour.getShades()[3]);
			s = s.replaceAll("#ffd5d5", this.colour.getShades()[4]);
			this.SVGString = s;

			is.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private TFModifier(String name, String description, String descriptor, String SVGString, Colour colour, Rarity rarity) {
		this.name = name;
		this.description = description;
		this.descriptor = descriptor;
		this.rarity=rarity;
		
		if (colour == null) {
			this.colour = Colour.CLOTHING_BLACK;
		} else {
			this.colour = colour;
		}
		
		// Set this item's file image:
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/base/res/crafting/" + SVGString + ".svg");
			String s = Util.inputStreamToString(is);

			s = s.replaceAll("#ff2a2a", this.colour.getShades()[0]);
			s = s.replaceAll("#ff5555", this.colour.getShades()[1]);
			s = s.replaceAll("#ff8080", this.colour.getShades()[2]);
			s = s.replaceAll("#ffaaaa", this.colour.getShades()[3]);
			s = s.replaceAll("#ffd5d5", this.colour.getShades()[4]);
			this.SVGString = s;

			is.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public int getValue() {
		switch(getRarity()) {
			case JINXED:
				return 1;
			case COMMON:
				return 1;
			case UNCOMMON:
				return 2;
			case RARE:
				return 3;
			case EPIC:
				return 5;
			case LEGENDARY:
				return 8;
		}
		return 1;
	}
	
	public AttributeCategory getAttributeCategory() {
		return attributeCategory;
	}

	public Attribute getAssociatedAttribute() {
		return associatedAttribute;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public String getSVGString() {
		return SVGString;
	}

	public Colour getColour() {
		return colour;
	}
	
	public boolean isSoloDescriptor() {
		return false;
	}
	
	public Rarity getRarity() {
		return rarity;
	}

	public static List<TFModifier> getTFModStrengthList() {
		return TFModStrengthList;
	}

	public static List<TFModifier> getTFModIntelligenceList() {
		return TFModIntelligenceList;
	}

	public static List<TFModifier> getTFModFitnessList() {
		return TFModFitnessList;
	}

	public static List<TFModifier> getTFModCorruptionList() {
		return TFModCorruptionList;
	}
	
	public static List<TFModifier> getTFModSexualList() {
		return TFModSexualList;
	}

	public static List<TFModifier> getTFModNegPosList() {
		return TFModNegPosList;
	}

	public static List<TFModifier> getTFRacialBodyPartsListList() {
		return TFRacialBodyPartsList;
	}
}
