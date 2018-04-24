package com.lilithsthrone.game.inventory.enchanting;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.7
 * @version 0.1.99
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
	
	ARCANE_BOOST("arcane booster",
			"Applies a more powerful effect in relation to the primary essence.",
			"boosted",
			"modifier_circle_arcane",
			Colour.GENERIC_ARCANE,
			Rarity.COMMON),

	REMOVAL("removal",
			"Applies an effect such that the primary modifier's body part is removed.",
			"vanishing",
			"modifier_circle_removal",
			Colour.GENERIC_TERRIBLE,
			Rarity.UNCOMMON),
	
	// Misc:
	
	ORIENTATION_GYNEPHILIC("gynephilia",
			"Applies an effect related to changing orientation to gynephilic.",
			"gynephilia",
			"modifier_circle_orientation_gynephilic",
			Colour.FEMININE_PLUS,
			Rarity.LEGENDARY),
	
	ORIENTATION_AMBIPHILIC("ambiphilia",
			"Applies an effect related to changing orientation to ambiphilic.",
			"ambiphilia",
			"modifier_circle_orientation_ambiphilic",
			Colour.ANDROGYNOUS,
			Rarity.LEGENDARY),
	
	ORIENTATION_ANDROPHILIC("androphilia",
			"Applies an effect related to changing orientation to androphilic.",
			"androphilia",
			"modifier_circle_orientation_androphilic",
			Colour.MASCULINE_PLUS,
			Rarity.LEGENDARY),
	
	// Attributes:
	
	STRENGTH(AttributeCategory.STRENGTH,
			Attribute.MAJOR_PHYSIQUE,
			"Applies an effect related to the primary attribute 'Physique'.",
			"modifier_circle_strength",
			Rarity.EPIC),
	
	INTELLIGENCE(AttributeCategory.INTELLIGENCE,
			Attribute.MAJOR_ARCANE,
			"Applies an effect related to the primary attribute 'Arcane'.",
			"modifier_circle_intelligence",
			Rarity.EPIC),
	
	CORRUPTION(AttributeCategory.CORRUPTION,
			Attribute.MAJOR_CORRUPTION,
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
	
	
	CRITICAL_CHANCE(AttributeCategory.STRENGTH,
			Attribute.CRITICAL_CHANCE,
			"Applies an effect related to the secondary attribute 'Critical chance'.",
			"modifier_circle",
			Rarity.RARE),
	
	CRITICAL_DAMAGE(AttributeCategory.STRENGTH,
			Attribute.CRITICAL_DAMAGE,
			"Applies an effect related to the secondary attribute 'Critical damage'.",
			"modifier_circle",
			Rarity.RARE),
	
	
	DAMAGE_LUST(AttributeCategory.CORRUPTION,
			Attribute.DAMAGE_LUST,
			"Applies an effect related to the secondary attribute 'Seduction damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_SPELLS(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_SPELLS,
			"Applies an effect related to the secondary attribute 'Spell damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_UNARMED(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_UNARMED,
			"Applies an effect related to the secondary attribute 'Unarmed damage'.",
			"modifier_circle_damage",
			Rarity.RARE),

	DAMAGE_MELEE_WEAPON(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_MELEE_WEAPON,
			"Applies an effect related to the secondary attribute 'Melee weapon damage'.",
			"modifier_circle_damage",
			Rarity.RARE),

	DAMAGE_RANGED_WEAPON(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_RANGED_WEAPON,
			"Applies an effect related to the secondary attribute 'Ranged weapon damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_PHYSICAL(AttributeCategory.STRENGTH,
			Attribute.DAMAGE_PHYSICAL,
			"Applies an effect related to the secondary attribute 'Physical damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_FIRE(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_FIRE,
			"Applies an effect related to the secondary attribute 'Fire damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_ICE(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_ICE,
			"Applies an effect related to the secondary attribute 'Ice damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	DAMAGE_POISON(AttributeCategory.INTELLIGENCE,
			Attribute.DAMAGE_POISON,
			"Applies an effect related to the secondary attribute 'Poison damage'.",
			"modifier_circle_damage",
			Rarity.RARE),
	
	
	RESISTANCE_LUST(AttributeCategory.CORRUPTION,
			Attribute.RESISTANCE_LUST,
			"Applies an effect related to the secondary attribute 'Seduction resistance'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_PHYSICAL(AttributeCategory.STRENGTH,
			Attribute.RESISTANCE_PHYSICAL,
			"Applies an effect related to the secondary attribute 'Physical resistance'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_FIRE(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_FIRE,
			"Applies an effect related to the secondary attribute 'Fire resistance'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_ICE(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_ICE,
			"Applies an effect related to the secondary attribute 'Ice resistance'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	RESISTANCE_POISON(AttributeCategory.INTELLIGENCE,
			Attribute.RESISTANCE_POISON,
			"Applies an effect related to the secondary attribute 'Poison resistance'.",
			"modifier_circle_resistance",
			Rarity.RARE),
	
	
	// Clothing parts:
	
	CLOTHING_ATTRIBUTE("attribute",
			"Applies a modifier to an attribute.",
			"attribute",
			"modifier_circle_arcane",
			Colour.GENERIC_ATTRIBUTE,
			Rarity.UNCOMMON),

	CLOTHING_ENSLAVEMENT("enslavement",
			"Makes this piece of clothing enslave the wearer.",
			"enslavement",
			"modifier_circle_enslavement",
			Colour.BASE_PURPLE_DARK,
			Rarity.LEGENDARY),
	
	CLOTHING_SEALING("sealing",
			"Makes this piece of clothing seal itself onto the wearer.",
			"sealing",
			"modifier_circle_sealing",
			Colour.SEALED,
			Rarity.LEGENDARY),
	
	
	// Racial parts:
	
	TF_ANTENNA("antennae",
			"Applies a transformative effect to your antennae.",
			"antennae",
			"modifier_circle_tf_antenna",
			Colour.TRANSFORMATION_PARTIAL,
			Rarity.UNCOMMON),
	
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
			"modifier_circle_tf_core",
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
			"modifier_circle_tf_skin",
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
	
	TF_MILK("milk",
			"Applies a transformative effect to your milk.",
			"milk",
			"modifier_circle_tf_milk",
			Colour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_CUM("cum",
			"Applies a transformative effect to your cum.",
			"cum",
			"modifier_circle_tf_cum",
			Colour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	TF_GIRLCUM("girlcum",
			"Applies a transformative effect to your girlcum.",
			"girlcum",
			"modifier_circle_tf_girlcum",
			Colour.TRANSFORMATION_SEXUAL,
			Rarity.UNCOMMON),
	
	
	// Body part modifiers:
	
	TF_TYPE_1("transformative I",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_1",
			Colour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_2("transformative II",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_2",
			Colour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_3("transformative III",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_3",
			Colour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_4("transformative IV",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_4",
			Colour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_TYPE_5("transformative V",
			"Transforms the related body part into that of a different race.",
			"transformative",
			"modifier_circle_tf_5",
			Colour.TRANSFORMATION_GENERIC,
			Rarity.COMMON),
	
	TF_MOD_FEMININITY("femininity",
			"Applies an effect to change the user's femininity.",
			"feminine",
			"modifier_circle_femininity",
			Colour.ANDROGYNOUS,
			Rarity.COMMON),
	
	TF_MOD_COUNT("count",
			"Applies an effect related to adding or removing extra body parts.",
			"counting",
			"modifier_circle_count",
			Colour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_COUNT_SECONDARY("secondary count",
			"Applies an effect related to adding or removing extra body parts.",
			"counting",
			"modifier_circle_count_secondary",
			Colour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_BODY_HAIR("hairiness",
			"Applies an effect related to adding or removing body hair.",
			"hairy",
			"modifier_circle_bodyHair",
			Colour.BASE_TAN,
			Rarity.COMMON),
	
	TF_MOD_INTERNAL("internal",
			"Applies an effect related to making a body part internal.",
			"internal",
			"modifier_circle_internal",
			Colour.BASE_BLUE_STEEL,
			Rarity.COMMON),
	
	TF_MOD_SIZE("size",
			"Applies an effect related to the change in size of a certain body part.",
			"resizing",
			"modifier_circle_size",
			Colour.BASE_LILAC,
			Rarity.COMMON),
	
	TF_MOD_SIZE_SECONDARY("secondary size",
			"Applies an effect related to the change in size of a different body part.",
			"resizing",
			"modifier_circle_size_secondary",
			Colour.BASE_LILAC_LIGHT,
			Rarity.COMMON),

	TF_MOD_SIZE_TERTIARY("tertiary size",
			"Applies an effect related to the change in size of yet another body part.",
			"resizing",
			"modifier_circle_size_tertiary",
			Colour.BASE_ROSE,
			Rarity.COMMON),
	

	TF_MOD_REGENERATION("regeneration",
			"Applies an effect related to the regeneration rate of fluids.",
			"refilling",
			"modifier_circle_regeneration",
			Colour.BASE_GREEN_LIGHT,
			Rarity.COMMON),
	
	// Orifices:
	
	TF_MOD_CAPACITY("capacity",
			"Applies an effect related to changing an orifice's capacity.",
			"capacity",
			"modifier_circle_capacity",
			Colour.BASE_PINK_LIGHT,
			Rarity.COMMON),

	TF_MOD_CAPACITY_2("capacity II",
			"Applies an effect related to changing an orifice's capacity.",
			"capacity",
			"modifier_circle_capacity",
			Colour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_WETNESS("wetness",
			"Applies an effect related to changing the wetness of an orifice, or the fluid production of a body part.",
			"wet",
			"modifier_circle_wetness",
			Colour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_ELASTICITY("elasticity",
			"Applies an effect related to changing the elasticity of an orifice.",
			"elastic",
			"modifier_circle_elasticity",
			Colour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_ELASTICITY_2("elasticity II",
			"Applies an effect related to changing the elasticity of an orifice.",
			"elastic",
			"modifier_circle_elasticity",
			Colour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_PLASTICITY("plasticity",
			"Applies an effect related to changing the plasticity of an orifice.",
			"plastic",
			"modifier_circle_plasticity",
			Colour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_PLASTICITY_2("plasticity II",
			"Applies an effect related to changing the plasticity of an orifice.",
			"plastic",
			"modifier_circle_plasticity",
			Colour.BASE_BLUE,
			Rarity.COMMON),
	
	// modifiers:
	
	TF_MOD_ORIFICE_PUFFY("puffiness",
			"Applies an effect related to making an orifice puffy.",
			"puffy",
			"modifier_circle_orifice_puffy",
			Colour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_RIBBED("ribbing",
			"Applies an effect related to making an orifice internally-ribbed.",
			"ribbed",
			"modifier_circle_orifice_ribbed",
			Colour.BASE_PINK_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_TENTACLED("tentacled",
			"Applies an effect related to making an orifice internally-tentacled.",
			"tentacled",
			"modifier_circle_orifice_tentacled",
			Colour.BASE_CRIMSON,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_MUSCLED("muscled",
			"Applies an effect related to making an orifice internally-muscled.",
			"muscled",
			"modifier_circle_orifice_muscled",
			Colour.BASE_MAGENTA,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_PUFFY_2("puffiness II",
			"Applies an effect related to making an orifice puffy.",
			"puffy",
			"modifier_circle_orifice_puffy",
			Colour.BASE_BLUE,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_RIBBED_2("ribbing II",
			"Applies an effect related to making an orifice internally-ribbed.",
			"ribbed",
			"modifier_circle_orifice_ribbed",
			Colour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_TENTACLED_2("tentacled II",
			"Applies an effect related to making an orifice internally-tentacled.",
			"tentacled",
			"modifier_circle_orifice_tentacled",
			Colour.BASE_RED,
			Rarity.COMMON),
	
	TF_MOD_ORIFICE_MUSCLED_2("muscled II",
			"Applies an effect related to making an orifice internally-muscled.",
			"muscled",
			"modifier_circle_orifice_muscled",
			Colour.BASE_PURPLE,
			Rarity.COMMON),
	
	// eye shapes:
	
	TF_MOD_EYE_IRIS_CIRCLE("circular irises",
			"Applies an effect related to making irises shaped like normal circles.",
			"circular irises",
			"modifier_circle_eye_iris_normal",
			Colour.BASE_PURPLE,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_HORIZONTAL("horizontal irises",
			"Applies an effect related to making irises take a more horizontal shape.",
			"horizontal irises",
			"modifier_circle_eye_iris_horizontal",
			Colour.BASE_LILAC_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_VERTICAL("vertical irises",
			"Applies an effect related to making irises take a more vertical shape.",
			"vertical irises",
			"modifier_circle_eye_iris_vertical",
			Colour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_HEART("heart-shaped irises",
			"Applies an effect related to making irises shaped like hearts.",
			"heart-shaped irises",
			"modifier_circle_eye_iris_heart",
			Colour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_EYE_IRIS_STAR("star-shaped irises",
			"Applies an effect related to making irises shaped like stars.",
			"star-shaped irises",
			"modifier_circle_eye_iris_star",
			Colour.BASE_YELLOW,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_CIRCLE("circular pupils",
			"Applies an effect related to making pupils shaped like normal circles.",
			"circular pupils",
			"modifier_circle_eye_iris_normal",
			Colour.BASE_BLACK,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_HORIZONTAL("horizontal pupils",
			"Applies an effect related to making pupils take a more horizontal shape.",
			"horizontal pupils",
			"modifier_circle_eye_iris_horizontal",
			Colour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_VERTICAL("vertical pupils",
			"Applies an effect related to making pupils take a more vertical shape.",
			"vertical pupils",
			"modifier_circle_eye_iris_vertical",
			Colour.BASE_TEAL,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_HEART("heart-shaped pupils",
			"Applies an effect related to making pupils shaped like hearts.",
			"heart-shaped pupils",
			"modifier_circle_eye_iris_heart",
			Colour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_EYE_PUPIL_STAR("star-shaped pupils",
			"Applies an effect related to making pupils shaped like stars.",
			"star-shaped pupils",
			"modifier_circle_eye_iris_star",
			Colour.BASE_BLUE,
			Rarity.COMMON),
	
	// breast shapes:
	
	TF_MOD_BREAST_SHAPE_ROUND("round breasts",
			"Applies an effect related to making breasts take on a round shape.",
			"round",
			"modifier_circle_breastShape_round",
			Colour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_NARROW("narrow breasts",
			"Applies an effect related to making breasts take on a narrow shape.",
			"narrow",
			"modifier_circle_breastShape_narrow",
			Colour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_WIDE("wide breasts",
			"Applies an effect related to making breasts take on a wide shape.",
			"wide",
			"modifier_circle_breastShape_wide",
			Colour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_POINTY("pointy breasts",
			"Applies an effect related to making breasts take on a pointy shape.",
			"pointy",
			"modifier_circle_breastShape_pointy",
			Colour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_PERKY("perky breasts",
			"Applies an effect related to making breasts take on a perky shape.",
			"perky",
			"modifier_circle_breastShape_perky",
			Colour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_BREAST_SHAPE_SIDESET("side-set breasts",
			"Applies an effect related to making breasts take on a side-set shape.",
			"side-set",
			"modifier_circle_breastShape_sideset",
			Colour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	// nipple shapes:
	
	TF_MOD_NIPPLE_NORMAL("normal nipples",
			"Applies an effect related to making nipples look normal.",
			"normal nipples",
			"modifier_circle_nipple_normal",
			Colour.BASE_PURPLE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_NIPPLE_VAGINA("nipple cunts",
			"Applies an effect related to making nipples look like pussies.",
			"nipple cunts",
			"modifier_circle_nipple_vagina",
			Colour.BASE_PINK_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_NIPPLE_LIPS("lipples",
			"Applies an effect related to making nipples look like a pair of lips.",
			"lipples",
			"modifier_circle_nipple_lips",
			Colour.BASE_MAGENTA,
			Rarity.COMMON),
	
	// areolae shapes:
	
	TF_MOD_AREOLAE_CIRCLE("circular areolae",
			"Applies an effect related to making areolae shaped like normal circles.",
			"circular areolae",
			"modifier_circle_areolae_normal",
			Colour.BASE_PURPLE,
			Rarity.COMMON),
	
	TF_MOD_AREOLAE_HEART("heart-shaped areolae",
			"Applies an effect related to making areolae shaped like hearts.",
			"heart-shaped areolae",
			"modifier_circle_areolae_heart",
			Colour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_AREOLAE_STAR("star-shaped areolae",
			"Applies an effect related to making areolae shaped like stars.",
			"star-shaped areolae",
			"modifier_circle_areolae_star",
			Colour.BASE_YELLOW,
			Rarity.COMMON),
	
	// tongue modifiers:
	
	TF_MOD_TONGUE_RIBBED("ribbing",
			"Applies an effect related to adding ribbing to a tongue.",
			"ribbed",
			"modifier_circle_orifice_ribbed",
			Colour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_TENTACLED("tentacled",
			"Applies an effect related to adding tentacles to a tongue.",
			"tentacled",
			"modifier_circle_orifice_tentacled",
			Colour.BASE_VIOLET,
			Rarity.COMMON),
	
	TF_MOD_TONGUE_BIFURCATED("bifurcated",
			"Applies an effect related to making a tongue bifurcated.",
			"bifurcated",
			"modifier_circle_tongue_bifurcated",
			Colour.BASE_CRIMSON,
			Rarity.COMMON),
	
	// penis modifiers:
	
	TF_MOD_PENIS_SHEATHED("sheathing",
			"Applies an effect related to making a penis sheathed.",
			"sheathed",
			"modifier_circle_penis_sheathed",
			Colour.BASE_ORANGE,
			Rarity.COMMON),
	
	TF_MOD_PENIS_RIBBED("ribbing",
			"Applies an effect related to making a penis ribbed.",
			"ribbed",
			"modifier_circle_penis_ribbed",
			Colour.BASE_PINK,
			Rarity.COMMON),
	
	TF_MOD_PENIS_TENTACLED("tentacled",
			"Applies an effect related to making a penis tentacled.",
			"tentacled",
			"modifier_circle_orifice_tentacled",
			Colour.BASE_VIOLET,
			Rarity.COMMON),
	
	TF_MOD_PENIS_KNOTTED("knotting",
			"Applies an effect related to making a penis knotted.",
			"knotted",
			"modifier_circle_penis_knotted",
			Colour.BASE_CRIMSON,
			Rarity.COMMON),
	
	TF_MOD_PENIS_TAPERED("tapering",
			"Applies an effect related to making a penis tapered.",
			"tapered",
			"modifier_circle_penis_tapered",
			Colour.BASE_LILAC,
			Rarity.COMMON),
	
	TF_MOD_PENIS_FLARED("flaring",
			"Applies an effect related to making a penis flared.",
			"flared",
			"modifier_circle_penis_flared",
			Colour.BASE_BROWN,
			Rarity.COMMON),
	
	TF_MOD_PENIS_BARBED("barbing",
			"Applies an effect related to making a penis barbed.",
			"barbed",
			"modifier_circle_penis_barbed",
			Colour.BASE_RED,
			Rarity.COMMON),
	
	TF_MOD_PENIS_VEINY("veins",
			"Applies an effect related to making a penis veiny.",
			"veiny",
			"modifier_circle_penis_veiny",
			Colour.BASE_VIOLET,
			Rarity.COMMON),
	
	TF_MOD_PENIS_PREHENSILE("prehensile",
			"Applies an effect related to making a penis prehensile.",
			"prehensile",
			"modifier_circle_penis_prehensile",
			Colour.BASE_TEAL,
			Rarity.COMMON),
	
	// vagina:
	

	TF_MOD_VAGINA_SQUIRTER("squirter",
			"Applies an effect related to making someone a squirter.",
			"squirting",
			"modifier_circle_squirter",
			Colour.BASE_AQUA,
			Rarity.COMMON),
	
	// fluid modifiers:
	
	TF_MOD_FLUID_MUSKY("musky",
			"Applies an effect related to changing a fluid.",
			"musk",
			"modifier_circle_fluid_modifier",
			Colour.BASE_BROWN,
			Rarity.COMMON),
	
	TF_MOD_FLUID_VISCOUS("viscous",
			"Applies an effect related to changing a fluid.",
			"viscous",
			"modifier_circle_fluid_modifier",
			Colour.BASE_BLACK,
			Rarity.COMMON),
	
	TF_MOD_FLUID_STICKY("sticky",
			"Applies an effect related to changing a fluid.",
			"sticky",
			"modifier_circle_fluid_modifier",
			Colour.BASE_YELLOW_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_FLUID_SLIMY("slimy",
			"Applies an effect related to changing a fluid.",
			"slimy",
			"modifier_circle_fluid_modifier",
			Colour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_FLUID_BUBBLING("bubbling",
			"Applies an effect related to changing a fluid.",
			"bubbling",
			"modifier_circle_fluid_modifier",
			Colour.BASE_AQUA,
			Rarity.COMMON),
	
	TF_MOD_FLUID_ALCOHOLIC("alcoholic",
			"Applies an effect related to changing a fluid.",
			"alcoholic",
			"modifier_circle_fluid_modifier",
			Colour.BASE_ORANGE,
			Rarity.COMMON),
	
	TF_MOD_FLUID_ADDICTIVE("addictive",
			"Applies an effect related to changing a fluid.",
			"addictive",
			"modifier_circle_fluid_modifier",
			Colour.BASE_PINK_DEEP,
			Rarity.COMMON),
	
	TF_MOD_FLUID_HALLUCINOGENIC("psychoactive",
			"Applies an effect related to changing a fluid.",
			"psychoactive",
			"modifier_circle_fluid_modifier",
			Colour.BASE_MAGENTA,
			Rarity.COMMON),
	
	// fluid flavours:
	
	TF_MOD_FLAVOUR_CUM("cum-flavour",
			"Applies an effect related to changing a fluid's flavour.",
			"cum-flavour",
			"modifier_circle_flavour",
			Colour.BASE_YELLOW_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_FLAVOUR_MILK("milk-flavour",
			"Applies an effect related to changing a fluid's flavour.",
			"milk-flavour",
			"modifier_circle_flavour",
			Colour.BASE_WHITE,
			Rarity.COMMON),
	
	TF_MOD_FLAVOUR_GIRLCUM("girlcum-flavour",
			"Applies an effect related to changing a fluid's flavour.",
			"milk-flavour",
			"modifier_circle_flavour",
			Colour.BASE_PINK_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_FLAVOUR_SLIME("slime-flavour",
			"Applies an effect related to changing a fluid's flavour.",
			"slime-flavour",
			"modifier_circle_flavour",
			Colour.BASE_BLUE_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_FLAVOUR_BEER("beer-flavour",
			"Applies an effect related to changing a fluid's flavour.",
			"beer-flavour",
			"modifier_circle_flavour",
			Colour.BASE_ORANGE,
			Rarity.COMMON),
	
	TF_MOD_FLAVOUR_VANILLA("vanilla-flavour",
			"Applies an effect related to changing a fluid's flavour.",
			"vanilla-flavour",
			"modifier_circle_flavour",
			Colour.BASE_YELLOW,
			Rarity.COMMON),
	
	TF_MOD_FLAVOUR_STRAWBERRY("strawberry-flavour",
			"Applies an effect related to changing a fluid's flavour.",
			"strawberry-flavour",
			"modifier_circle_flavour",
			Colour.BASE_RED,
			Rarity.COMMON),
	
	TF_MOD_FLAVOUR_CHOCOLATE("chocolate-flavour",
			"Applies an effect related to changing a fluid's flavour.",
			"chocolate-flavour",
			"modifier_circle_flavour",
			Colour.BASE_BROWN_DARK,
			Rarity.COMMON),
	
	TF_MOD_FLAVOUR_PINEAPPLE("pineapple-flavour",
			"Applies an effect related to changing a fluid's flavour.",
			"pineapple-flavour",
			"modifier_circle_flavour",
			Colour.BASE_YELLOW_LIGHT,
			Rarity.COMMON),
	
	TF_MOD_FLAVOUR_HONEY("honey-flavour",
			"Applies an effect related to changing a fluid's flavour.",
			"honey-flavour",
			"modifier_circle_flavour",
			Colour.BASE_ORANGE,
			Rarity.COMMON),
	
	TF_MOD_FLAVOUR_MINT("mint-flavour",
			"Applies an effect related to changing a fluid's flavour.",
			"mint-flavour",
			"modifier_circle_flavour",
			Colour.BASE_GREEN_LIME,
			Rarity.COMMON),
	
	
	// Fetishes:
	
	TF_MOD_FETISH_BODY_PART("body part fetishes",
			"Applies an effect related to a body part fetish.",
			"fetish",
			"modifier_circle_desires",
			Colour.BASE_PINK,
			Rarity.COMMON),

	TF_MOD_FETISH_BEHAVIOUR("behavioural fetishes",
			"Applies an effect related to a behavioural fetish.",
			"fetish",
			"modifier_circle_desires",
			Colour.BASE_PURPLE,
			Rarity.COMMON),
	
	
	TF_MOD_FETISH_ANAL_GIVING(Fetish.FETISH_ANAL_GIVING),
	TF_MOD_FETISH_ANAL_RECEIVING(Fetish.FETISH_ANAL_RECEIVING),
	TF_MOD_FETISH_VAGINAL_GIVING(Fetish.FETISH_VAGINAL_GIVING),
	TF_MOD_FETISH_VAGINAL_RECEIVING(Fetish.FETISH_VAGINAL_RECEIVING),
	TF_MOD_FETISH_BREASTS_OTHERS(Fetish.FETISH_BREASTS_OTHERS),
	TF_MOD_FETISH_BREASTS_SELF(Fetish.FETISH_BREASTS_SELF),
	TF_MOD_FETISH_ORAL_GIVING(Fetish.FETISH_ORAL_GIVING),
	TF_MOD_FETISH_ORAL_RECEIVING(Fetish.FETISH_ORAL_RECEIVING),
	TF_MOD_FETISH_LEG_LOVER(Fetish.FETISH_LEG_LOVER),
	TF_MOD_FETISH_STRUTTER(Fetish.FETISH_STRUTTER),
	TF_MOD_FETISH_LACTATION_OTHERS(Fetish.FETISH_LACTATION_OTHERS),
	TF_MOD_FETISH_LACTATION_SELF(Fetish.FETISH_LACTATION_SELF),
	
	TF_MOD_FETISH_DOMINANT(Fetish.FETISH_DOMINANT),
	TF_MOD_FETISH_SUBMISSIVE(Fetish.FETISH_SUBMISSIVE),
	TF_MOD_FETISH_BROODMOTHER(Fetish.FETISH_BROODMOTHER),
	TF_MOD_FETISH_CROSS_DRESSER(Fetish.FETISH_CROSS_DRESSER),
	TF_MOD_FETISH_CUM_ADDICT(Fetish.FETISH_CUM_ADDICT),
	TF_MOD_FETISH_CUM_STUD(Fetish.FETISH_CUM_STUD),
	TF_MOD_FETISH_DEFLOWERING(Fetish.FETISH_DEFLOWERING),
	TF_MOD_FETISH_DENIAL(Fetish.FETISH_DENIAL),
	TF_MOD_FETISH_DENIAL_SELF(Fetish.FETISH_DENIAL_SELF),
	TF_MOD_FETISH_EXHIBITIONIST(Fetish.FETISH_EXHIBITIONIST),
	TF_MOD_FETISH_VOYEURIST(Fetish.FETISH_VOYEURIST),
	TF_MOD_FETISH_IMPREGNATION(Fetish.FETISH_IMPREGNATION),
	TF_MOD_FETISH_INCEST(Fetish.FETISH_INCEST),
	TF_MOD_FETISH_MASOCHIST(Fetish.FETISH_MASOCHIST),
	TF_MOD_FETISH_MASTURBATION(Fetish.FETISH_MASTURBATION),
	TF_MOD_FETISH_NON_CON_DOM(Fetish.FETISH_NON_CON_DOM),
	TF_MOD_FETISH_NON_CON_SUB(Fetish.FETISH_NON_CON_SUB),
	TF_MOD_FETISH_PREGNANCY(Fetish.FETISH_PREGNANCY),
	TF_MOD_FETISH_PURE_VIRGIN(Fetish.FETISH_PURE_VIRGIN),
	TF_MOD_FETISH_SADIST(Fetish.FETISH_SADIST),
	TF_MOD_FETISH_SEEDER(Fetish.FETISH_SEEDER),
	TF_MOD_FETISH_TRANSFORMATION_GIVING(Fetish.FETISH_TRANSFORMATION_GIVING),
	TF_MOD_FETISH_TRANSFORMATION_RECEIVING(Fetish.FETISH_TRANSFORMATION_RECEIVING),
	TF_MOD_FETISH_BIMBO(Fetish.FETISH_BIMBO),
	TF_MOD_FETISH_KINK_GIVING(Fetish.FETISH_KINK_GIVING),
	TF_MOD_FETISH_KINK_RECEIVING(Fetish.FETISH_KINK_RECEIVING),
	;

	private static List<TFModifier> TFModStrengthList = new ArrayList<>();
	private static List<TFModifier> TFModIntelligenceList = new ArrayList<>();
	private static List<TFModifier> TFModCorruptionList = new ArrayList<>();
	private static List<TFModifier> TFModSexualList = new ArrayList<>();
	private static List<TFModifier> TFAttributeList = new ArrayList<>();
	private static List<TFModifier> TFRacialBodyPartsList = new ArrayList<>();
	private static List<TFModifier> TFBodyPartFetishList = new ArrayList<>();
	private static List<TFModifier> TFBehaviouralFetishList = new ArrayList<>();

	private static List<TFModifier> clothingPrimaryList = new ArrayList<>();
	private static List<TFModifier> clothingAttributeList = new ArrayList<>();
	
	static {

		TFModStrengthList.add(NONE);
		TFModIntelligenceList.add(NONE);
		TFModCorruptionList.add(NONE);
		TFModSexualList.add(NONE);
		
		for(TFModifier tfMod : TFModifier.values()) {
			if(tfMod.getAttributeCategory()!=null) {
				switch(tfMod.getAttributeCategory()) {
					case CORRUPTION:
						TFModCorruptionList.add(tfMod);
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
		
		TFModSexualList.add(FERTILITY);
		TFModSexualList.add(VIRILITY);
		TFModSexualList.add(DAMAGE_LUST);
		TFModSexualList.add(RESISTANCE_LUST);
		
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
		TFRacialBodyPartsList.add(TF_ANTENNA);
		TFRacialBodyPartsList.add(TF_HORNS);
		TFRacialBodyPartsList.add(TF_TAIL);
		TFRacialBodyPartsList.add(TF_WINGS);
		
		TFRacialBodyPartsList.add(TF_ASS);
		TFRacialBodyPartsList.add(TF_BREASTS);
		TFRacialBodyPartsList.add(TF_PENIS);
		TFRacialBodyPartsList.add(TF_VAGINA);
		
		TFRacialBodyPartsList.add(TF_MILK);
		TFRacialBodyPartsList.add(TF_CUM);
		TFRacialBodyPartsList.add(TF_GIRLCUM);
		
		TFAttributeList.add(NONE);
		TFAttributeList.add(ARCANE_BOOST);
		

		TFBodyPartFetishList.add(TF_MOD_FETISH_ANAL_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ANAL_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_BREASTS_OTHERS);
		TFBodyPartFetishList.add(TF_MOD_FETISH_BREASTS_SELF);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ORAL_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_ORAL_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_VAGINAL_GIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_VAGINAL_RECEIVING);
		TFBodyPartFetishList.add(TF_MOD_FETISH_LEG_LOVER);
		TFBodyPartFetishList.add(TF_MOD_FETISH_STRUTTER);
		TFBodyPartFetishList.add(TF_MOD_FETISH_LACTATION_OTHERS);
		TFBodyPartFetishList.add(TF_MOD_FETISH_LACTATION_SELF);

		TFBehaviouralFetishList.add(TF_MOD_FETISH_DOMINANT);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_SUBMISSIVE);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_CUM_STUD);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_CUM_ADDICT);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_DEFLOWERING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_PURE_VIRGIN);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_IMPREGNATION);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_PREGNANCY);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_SEEDER);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_BROODMOTHER);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_TRANSFORMATION_GIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_TRANSFORMATION_RECEIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_SADIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_MASOCHIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_NON_CON_DOM);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_NON_CON_SUB);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_DENIAL);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_DENIAL_SELF);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_VOYEURIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_EXHIBITIONIST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_BIMBO);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_CROSS_DRESSER);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_MASTURBATION);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_INCEST);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_KINK_GIVING);
		TFBehaviouralFetishList.add(TF_MOD_FETISH_KINK_RECEIVING);
		
		clothingPrimaryList.add(TFModifier.CLOTHING_ATTRIBUTE);
		clothingPrimaryList.add(TFModifier.CLOTHING_SEALING);
		clothingPrimaryList.add(TFModifier.CLOTHING_ENSLAVEMENT);
		clothingPrimaryList.add(TFModifier.TF_MOD_FETISH_BODY_PART);
		clothingPrimaryList.add(TFModifier.TF_MOD_FETISH_BEHAVIOUR);
		clothingPrimaryList.add(TF_FACE);
		clothingPrimaryList.add(TF_CORE);
		clothingPrimaryList.add(TF_HAIR);
		clothingPrimaryList.add(TF_ASS);
		clothingPrimaryList.add(TF_BREASTS);
		clothingPrimaryList.add(TF_PENIS);
		clothingPrimaryList.add(TF_VAGINA);
		
		clothingAttributeList.add(TFModifier.RESISTANCE_FIRE);
		clothingAttributeList.add(TFModifier.RESISTANCE_ICE);
		clothingAttributeList.add(TFModifier.RESISTANCE_LUST);
		clothingAttributeList.add(TFModifier.RESISTANCE_PHYSICAL);
		clothingAttributeList.add(TFModifier.RESISTANCE_POISON);
		clothingAttributeList.add(TFModifier.DAMAGE_FIRE);
		clothingAttributeList.add(TFModifier.DAMAGE_ICE);
		clothingAttributeList.add(TFModifier.DAMAGE_LUST);
		clothingAttributeList.add(TFModifier.DAMAGE_UNARMED);
		clothingAttributeList.add(TFModifier.DAMAGE_MELEE_WEAPON);
		clothingAttributeList.add(TFModifier.DAMAGE_RANGED_WEAPON);
		clothingAttributeList.add(TFModifier.DAMAGE_PHYSICAL);
		clothingAttributeList.add(TFModifier.DAMAGE_POISON);
		clothingAttributeList.add(TFModifier.DAMAGE_SPELLS);
		clothingAttributeList.add(TFModifier.SPELL_COST_MODIFIER);
		clothingAttributeList.add(TFModifier.CRITICAL_CHANCE);
		clothingAttributeList.add(TFModifier.CRITICAL_DAMAGE);
	}
	
	
	private enum AttributeCategory {
		STRENGTH,
		INTELLIGENCE,
		CORRUPTION;
	}
	
	private AttributeCategory attributeCategory;
	private Attribute associatedAttribute;
	private String name, description, descriptor, SVGString;
	private Colour colour;
	private Rarity rarity;
	private Fetish fetish;
	
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
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/crafting/" + SVGString + ".svg");
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
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/crafting/" + SVGString + ".svg");
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
	
	private TFModifier(Fetish f) {
		this.name = f.getName(null);
		this.description = "Applies an effect related to the "+name+" fetish. ("+f.getShortDescriptor()+")";
		this.descriptor = name;
		this.rarity = Rarity.EPIC;
		this.colour = Colour.FETISH;
		this.fetish = f;
		this.SVGString = f.getSVGString();
		
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

	public static List<TFModifier> getTFModCorruptionList() {
		return TFModCorruptionList;
	}
	
	public static List<TFModifier> getTFModSexualList() {
		return TFModSexualList;
	}

	public static List<TFModifier> getTFRacialBodyPartsList() {
		return TFRacialBodyPartsList;
	}

	public static List<TFModifier> getTFAttributeList() {
		return TFAttributeList;
	}

	public static List<TFModifier> getTFBodyPartFetishList() {
		return TFBodyPartFetishList;
	}
	
	public static List<TFModifier> getTFBehaviouralFetishList() {
		return TFBehaviouralFetishList;
	}

	public Fetish getFetish() {
		return fetish;
	}

	public static List<TFModifier> getClothingAttributeList() {
		return clothingAttributeList;
	}

	public static List<TFModifier> getClothingPrimaryList() {
		return clothingPrimaryList;
	}
}
